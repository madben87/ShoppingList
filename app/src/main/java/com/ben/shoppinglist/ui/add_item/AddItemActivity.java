package com.ben.shoppinglist.ui.add_item;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.ben.shoppinglist.R;
import com.ben.shoppinglist.core.App;
import com.ben.shoppinglist.util.ImageUtil;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddItemActivity extends AppCompatActivity implements AddItemView {

    private static final int CAMERA_REQUEST = 0;
    private static final int GALLERY_REQUEST = 1;

    private static final String STATE_NAME = "name";
    private static final String STATE_DESCR = "description";
    private static final String STATE_IMAGE = "image";

    @BindView(R.id.addItemName)
    EditText addItemName;
    @BindView(R.id.addItemDescr)
    EditText addItemDescr;
    @BindView(R.id.addItemImage)
    ImageView addItemImage;
    @BindView(R.id.addImageCamera)
    FrameLayout addImageCamera;
    @BindView(R.id.addImageGallery)
    FrameLayout addImageGallery;
    @BindView(R.id.addItemSave)
    Button addItemSave;

    @Inject
    AddItemPresenterImpl presenter;
    private Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        ButterKnife.bind(this);
        App.getScreenInjector().inject(this);
        presenter.attachView(this);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(STATE_NAME)) {
                addItemName.setText(savedInstanceState.getString(STATE_NAME));
            }
            if (savedInstanceState.containsKey(STATE_DESCR)) {
                addItemDescr.setText(savedInstanceState.getString(STATE_DESCR));
            }
            if (savedInstanceState.containsKey(STATE_IMAGE)) {
                image = ImageUtil.byteToBitmap(savedInstanceState.getByteArray(STATE_IMAGE));
                addItemImage.setImageBitmap(image);
            }
        }
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.addItemSave, R.id.addImageCamera, R.id.addImageGallery})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.addItemSave:
                presenter.addNewItem(addItemName.getText().toString(), addItemDescr.getText().toString(), image);
                break;
            case R.id.addImageCamera:
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                break;
            case R.id.addImageGallery:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            image = (Bitmap) data.getExtras().get("data");
            addItemImage.setImageBitmap(image);
        }else if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            try {
                InputStream imageStream = null;
                if (imageUri != null) {
                    imageStream = getContentResolver().openInputStream(imageUri);
                }
                image = BitmapFactory.decodeStream(imageStream);
                addItemImage.setImageBitmap(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!addItemName.getText().toString().isEmpty()) {
            outState.putString(STATE_NAME, addItemName.getText().toString());
        }
        if (!addItemDescr.getText().toString().isEmpty()) {
            outState.putString(STATE_DESCR, addItemDescr.getText().toString());
        }

        Bitmap bitmap = null;
        try {
            bitmap = ((BitmapDrawable) addItemImage.getDrawable()).getBitmap();
        }catch (Exception e) {
            e.printStackTrace();
        }
        if (bitmap != null) {
            outState.putByteArray(STATE_IMAGE, ImageUtil.bitmapToByte(bitmap));
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
