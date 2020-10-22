package com.developer.david.apprestaurant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;

public class CreateRestaurant extends AppCompatActivity implements View.OnClickListener {

    Button buttonImage, buttonCreateRestaurant ;
    EditText nombre, nit, propietario, direccion;
    ListView listRes, listdelete;
    ArrayList<String> listaRestaurant;
    ArrayAdapter adapter;

    ArrayList<String> deleteRest;
    ArrayAdapter adapter2;

    static final int PERMISION_CODE = 10;
    static final int code_camera = 99;
    private Activity root = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_restaurant);

        nombre = (EditText) findViewById(R.id.nombreRes);
        nit = (EditText) findViewById(R.id.nitRes);
        propietario = (EditText) findViewById(R.id.propietarioRes);
        direccion = (EditText) findViewById(R.id.direccionRes);

        listaRestaurant = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,listaRestaurant);
        listRes = (ListView) findViewById(R.id.listRestaurate);
        listRes.setAdapter(adapter);

        deleteRest = new ArrayList<>();
        adapter2 = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1, deleteRest);
        listdelete = (ListView) findViewById(R.id.deleteList);
        listdelete.setAdapter(adapter2);

        listdelete.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Toast.makeText(CreateRestaurant.this, "EL RESTAURANT FUE ELIMINADO", Toast.LENGTH_LONG).show();
                listaRestaurant.remove(i);
                deleteRest.remove(i);
                adapter.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
            }
        });

        loadComponents();
    }

    private void loadComponents() {
        buttonImage = this.findViewById(R.id.buttonInsertRes);
        buttonImage.setOnClickListener(this);
        /*buttonCreateRestaurant = this.findViewById(R.id.createRest);

        buttonCreateRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root,CreateRestaurant.class);
                root.startActivity(intent);
            }
        });*/


    }

    public void Agregar (View view){
        if(nombre.getText().toString().length()!=0 && nit.getText().toString().length()!=0 &&
                propietario.getText().toString().length()!=0 && direccion.getText().toString().length()!=0){
            listaRestaurant.add(nombre.getText().toString()+"\n"+nit.getText().toString()+"\n"+
                    propietario.getText().toString()+"\n"+direccion.getText().toString()+"\n");
            deleteRest.add("\n \n \n BORRAR");
        }else{
            Toast.makeText(this, "Ingrese todos los datos por favor", Toast.LENGTH_LONG).show();
        }
        adapter.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();

        nombre.setText("");
        nit.setText("");
        propietario.setText("");
        direccion.setText("");
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

    };

    private void callCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (camera.resolveActivity(this.getPackageManager()) != null) {
            this.startActivityForResult(camera, code_camera);
        }
    };



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == code_camera && resultCode == RESULT_OK) {
            Bundle photo = data.getExtras();
            Bitmap imageBitmap = (Bitmap) photo.get("data");
            ImageView img = this.findViewById(R.id.imageInsertRes);
            img.setImageBitmap(imageBitmap);
        }
    }
}

