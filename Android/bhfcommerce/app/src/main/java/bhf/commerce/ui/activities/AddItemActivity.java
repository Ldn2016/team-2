package bhf.commerce.ui.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.io.File;

import bhf.commerce.R;
import bhf.commerce.utils.ImageUtil;

public class AddItemActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int OPEN_CAMERA = 1;
    private static final int OPEN_GALLERY = 2;
    private ImageView imageChooser;
    private EditText etTitle;
    private EditText etDescription;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Item");
        initView();
        askPermission();
    }

    private void initView(){
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageChooser = (ImageView) findViewById(R.id.image);
        etTitle = (EditText) findViewById(R.id.title);
        etDescription = (EditText) findViewById(R.id.description);

        imageChooser.setOnClickListener(this);
        findViewById(R.id.submit).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.image:
                showChooser();
                break;
            case R.id.submit:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showChooser(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Image Source");
        builder.setItems(new CharSequence[] {"Gallery", "Camera"},
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intent, OPEN_GALLERY);
                                break;
                            case 1:
                                openCamera();
                                break;
                        }
                    }
                }
        );

        builder.show();
    }

    private void openCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStorageDirectory(), "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        startActivityForResult(intent, OPEN_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri selectedImage;

        if(requestCode == OPEN_CAMERA){
            selectedImage = imageUri;
        } else{
            selectedImage = data.getData();
        }

        if(selectedImage != null)
            getContentResolver().notifyChange(selectedImage, null);

        Bitmap bitmap = ImageUtil.rotateBitmap(getContentResolver(), selectedImage);

        imageChooser.setImageBitmap(bitmap);
    }

    private void askPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA},
                        0);
            }
        }
    }
}
