package com.developer.david.apprestaurant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class CreateMenu extends AppCompatActivity implements View.OnClickListener {
    Button buttonImage, buttonCreateMenu ;
    static final int PERMISION_CODE = 10;
    static final int code_camera = 99;
    ListView mListview;
    EditText mText;
    List<String>mLista = new ArrayList<>();
    ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_menu);



        loadComponents();
    }

    private void loadComponents() {
        buttonImage = this.findViewById(R.id.buttonInsert);
        buttonImage.setOnClickListener(this);
        /*buttonCreateMenu = findViewById(R.id.createMenu);
        buttonCreateMenu.setOnClickListener(this);
        mListview = findViewById(R.id.listViewMenu);
        mListview.setOnClickListener(this);
        mText = findViewById(R.id.nombreMenu);*/
    }
        @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISION_CODE) {
            if (permissions.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callCamera();
                } else {
                    Toast.makeText(this, "No se puede continuar se Nesecita la foto", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISION_CODE);
        }
    }

    public Boolean checkPermission(String permission) {
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onClick(View view) {
        if (checkPermission(Manifest.permission.CAMERA)) {
            callCamera();
            //Toast.makeText(this, "Tiene permisos", Toast.LENGTH_LONG).show();
        } else {
            //Toast.makeText(this, "No tiene permisos Necesarios :'(", Toast.LENGTH_LONG).show();
            requestPermission();
        }
        /*switch (view.getId()){
            case R.id.createMenu: String texto = mText.getText().toString().trim();
            mLista.add(texto);
            mText.getText().clear();
            mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mLista);
            mListview.setAdapter(mAdapter);
        }*/

    };

    private void callCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (camera.resolveActivity(this.getPackageManager()) != null) {
            this.startActivityForResult(camera, code_camera);

        }
    };




    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == code_camera && resultCode == RESULT_OK) {
            Bundle photo = data.getExtras();
            Bitmap imageBitmap = (Bitmap) photo.get("data");
            ImageView img = this.findViewById(R.id.imageInsert);
            img.setImageBitmap(imageBitmap);
        }
    }*/


}
