package com.ben.shoppinglist.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ben.shoppinglist.R;
import com.ben.shoppinglist.core.App;
import com.ben.shoppinglist.data.room.model.ShoppingItem;
import com.ben.shoppinglist.ui.add_item.AddItemActivity;
import com.ben.shoppinglist.ui.fragments.ListFragment;
import com.ben.shoppinglist.ui.main.adapter.MainPagerAdapter;
import com.ben.shoppinglist.ui.fragments.shopping_history.ShoppingHistoryFragment;
import com.ben.shoppinglist.ui.fragments.shopping_list.ShoppingListFragment;
import com.ben.shoppinglist.util.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final String STATE_MULTI_SELECTED = "multiSelectedMode";

    @BindView(R.id.mainViewPager)
    ViewPager mainViewPager;
    @BindView(R.id.fab)
    protected FloatingActionButton fab;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private MenuItem btnCheckAll;

    @Inject
    MainPresenterImpl presenter;
    private MainPagerAdapter pagerAdapter;

    private boolean multiSelectedMode;

    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getScreenInjector().inject(this);
        presenter.attachView(this);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(STATE_MULTI_SELECTED)) {
                multiSelectedMode = savedInstanceState.getBoolean(STATE_MULTI_SELECTED);
            }
            if (multiSelectedMode) {
                fab.setImageResource(R.drawable.ic_archive_white_24dp);
            }else {
                fab.setImageResource(R.drawable.ic_add_white_24dp);
            }
        }

        EventBus.getDefault().register(this);

        setSupportActionBar(toolbar);

        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new ShoppingListFragment(), "Shopping list");
        pagerAdapter.addFragment(new ShoppingHistoryFragment(), "History list");
        mainViewPager.setAdapter(pagerAdapter);

        mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (multiSelectedMode && currentPage != 0) {
                    EventBus.getDefault().post(new MessageEvent(MessageEvent.PAGE_CHANGE_STATE));
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!multiSelectedMode) {
                    launchActivity(AddItemActivity.class);
                }else {
                    if (currentPage == 0) {
                        ListFragment fragment = (ListFragment) pagerAdapter.getItem(currentPage);
                        presenter.markAsPurchased(fragment.getList());
                    }
                    Toast.makeText(v.getContext(), "Item Mark As Purchased - " + pagerAdapter.getPageTitle(mainViewPager.getCurrentItem()), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        btnCheckAll = menu.findItem(R.id.btnCheckAll);
        if (multiSelectedMode) {
            btnCheckAll.setVisible(true);
        }else {
            btnCheckAll.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.btnCheckAll && multiSelectedMode) {
            EventBus.getDefault().post(new MessageEvent(MessageEvent.SELECT_ALL_ITEMS));
        }
        return true;
    }

    public void launchActivity(Class<? extends Activity> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    @Override
    public void launchActivity(Class<? extends Activity> cls, int flag) {
        Intent intent = new Intent(this, cls);
        intent.addFlags(flag);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.msg == MessageEvent.MULTI_SELECTED_MODE_ON) {
            multiSelectedMode = !multiSelectedMode;
            fab.setImageResource(R.drawable.ic_archive_white_24dp);
            btnCheckAll.setVisible(true);
        }else if (event.msg == MessageEvent.MULTI_SELECTED_MODE_OFF) {
            multiSelectedMode = !multiSelectedMode;
            fab.setImageResource(R.drawable.ic_add_white_24dp);
            btnCheckAll.setVisible(false);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putBoolean(STATE_MULTI_SELECTED, multiSelectedMode);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (!multiSelectedMode) {
            super.onBackPressed();
        }else {
            EventBus.getDefault().post(new MessageEvent(MessageEvent.ON_BACK_PRESSED));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        EventBus.getDefault().unregister(this);
    }
}
