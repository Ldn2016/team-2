package bhf.commerce.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
    private EditText etName;
    private EditText etDescription;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        askPermission();
    }

    private void initView(){
        setContentView(R.layout.activity_add_item);
        imageChooser = (ImageView) findViewById(R.id.image);
        etName = (EditText) findViewById(R.id.name);
        etDescription = (EditText) findViewById(R.id.description);

        imageChooser.setOnClickListener(this);
        findViewById(R.id.submit).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.image:
                openCamera();
                break;
            case R.id.submit:
                break;
        }
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
