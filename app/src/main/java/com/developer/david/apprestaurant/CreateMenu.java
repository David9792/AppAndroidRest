package com.developer.david.apprestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateMenu extends AppCompatActivity implements View.OnClickListener {
    Button buttonImage, buttonCreateMenu ;
    private Activity root = this;
    static final int PERMISION_CODE = 10;
    static final int code_camera = 99;

    EditText nombre, precio, descripcion;
    ListView listMenu, listdelete;
    ArrayList<String> listaMenu;
    ArrayAdapter adapter;

    ArrayList<String> deleteMenu;
    ArrayAdapter adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_menu);

        nombre = (EditText) findViewById(R.id.nombreMenu);
        precio = (EditText) findViewById(R.id.precioMenu);
        descripcion = (EditText) findViewById(R.id.descripcionMenu);

        listaMenu = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,listaMenu);
        listMenu = (ListView) findViewById(R.id.listViewMenu);
        listMenu.setAdapter(adapter);

        deleteMenu = new ArrayList<>();
        adapter2 = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1, deleteMenu);
        listdelete = (ListView) findViewById(R.id.deleteListViewMenu);
        listdelete.setAdapter(adapter2);

        listdelete.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Toast.makeText(CreateMenu.this, "LOS DATOS FUERON ELIMIDOS", Toast.LENGTH_LONG).show();
                listaMenu.remove(i);
                deleteMenu.remove(i);
                adapter.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
            }
        });



        loadComponents();
    }

    private void loadComponents() {
        buttonImage = this.findViewById(R.id.buttonInsertMenu);
        buttonImage.setOnClickListener(this);

        /*buttonCreateMenu = findViewById(R.id.createMenu);
        buttonCreateMenu.setOnClickListener(this);
        ListaMenu = findViewById(R.id.listViewMenu);
        ListaMenu.setOnClickListener(this);
        Nombre = findViewById(R.id.Nombre_menu);
        Precio = findViewById(R.id.Precio_menu);
        Descripcion = findViewById(R.id.DescripcionMenu);
        FechaReg = findViewById(R.id.FechaRegMenu);

        buttonCreateMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.createMenu: String nombre = Nombre.getText().toString().trim();
                        mLista.add(nombre);
                    Nombre.getText().clear();
                    mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mLista);
                        ListaMenu.setAdapter(mAdapter);

                    case R.id.createMenu: String precio = Precio.getText().toString().trim();
                    mLista.add(precio);
                    Precio.getText().clear();
                    mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mLista);

                    case R.id.createMenu: String description = Descripcion.getText().toString().trim();
                    mLista.add(description);
                    Descripcion.getText().clear();
                    mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mLista);

                    case R.id.createMenu: String date = FechaReg.getText().toString().trim();
                    mLista.add(date);
                    FechaReg.getText().clear();
                    mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mLista);
                    ListaMenu.setAdapter(mAdapter);


                }
            }
        });*/
    }

    public void Agregar (View view){
        if(nombre.getText().toString().length()!=0 && precio.getText().toString().length()!=0 && descripcion.getText().toString().length()!=0 ){
            listaMenu.add(nombre.getText().toString()+"\n"+precio.getText().toString()+"\n"+descripcion.getText().toString()+"\n");
            deleteMenu.add("\n \n BORRAR\n");
        }else{
            Toast.makeText(this, "Ingrese todos los datos por favor", Toast.LENGTH_LONG).show();
        }
        adapter.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();

        nombre.setText("");
        precio.setText("");
        descripcion.setText("");
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
