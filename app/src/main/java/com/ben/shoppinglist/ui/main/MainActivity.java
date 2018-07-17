package com.ben.shoppinglist.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ben.shoppinglist.R;
import com.ben.shoppinglist.core.App;
import com.ben.shoppinglist.ui.add_item.AddItemActivity;
import com.ben.shoppinglist.ui.main.adapter.MainPagerAdapter;
import com.ben.shoppinglist.ui.fragments.shopping_history.ShoppingHistoryFragment;
import com.ben.shoppinglist.ui.fragments.shopping_list.ShoppingListFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.mainViewPager)
    ViewPager mainViewPager;
    @BindView(R.id.fab)
    protected FloatingActionButton fab;
    @Inject
    MainPresenterImpl presenter;
    private MainPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getScreenInjector().inject(this);
        presenter.attachView(this);

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

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(AddItemActivity.class);
            }
        });
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

    /*@Override
    public void showList(List<ShoppingItem> list) {

    }

    @Override
    public void updateList() {

    }*/

    /*private void showDialogAddItem() {
        ShoppingItem item = new ShoppingItem();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.new_item_dialog, null);
        EditText addItemName = (EditText) view.findViewById(R.id.addItemName);
        EditText addItemDescr = (EditText) view.findViewById(R.id.addItemDescr);
        final FrameLayout addImageCamera = (FrameLayout) view.findViewById(R.id.addImageCamera);
        final FrameLayout addImageGalery = (FrameLayout) view.findViewById(R.id.addImageGalery);
        Button addItemSave = (Button) view.findViewById(R.id.addItemSave);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.addImageCamera:
                        break;
                    case R.id.addImageGalery:
                        break;
                    case R.id.addItemSave:
                        break;
                }
            }
        };

        addImageCamera.setOnClickListener(clickListener);
        addImageGalery.setOnClickListener(clickListener);
        addItemSave.setOnClickListener(clickListener);
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
