package com.developer.david.apprestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.david.apprestaurant.ui.delete.DeleteFragment;
import com.developer.david.apprestaurant.utils.EndPoinds;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class CrearMenu extends AppCompatActivity {
    private Activity root = this;
    Button RegistroMenu;
    TextView NombreMenu, PrecioMenu, DescripcionMenu;
    /*//get the spinner from the xml.
    Spinner dropdown = findViewById(R.id.spinner1);
    //create a list of items for the spinner.
    String[] items = new String[]{"1", "2", "three"};
    //create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
dropdown.setAdapter(adapter);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_menu);

        NombreMenu = findViewById(R.id.menuNombre);
        PrecioMenu = findViewById(R.id.menuPrecio);
        DescripcionMenu = findViewById(R.id.menuDescripcion);
        RegistroMenu = findViewById(R.id.menuRegistro);

        RegistroMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CrearMenu.this, ListRestaurant.class);
                senResgistrar();
                startActivity(intent);

            }
        });
    }
    public void senResgistrar(){
        AsyncHttpClient client = new AsyncHttpClient();
        final RequestParams req = new RequestParams();

        String restaurant_id = getIntent().getStringExtra("restaurant_id");

        req.put("Nombre", NombreMenu.getText().toString());
        req.put("Precio", PrecioMenu.getText().toString());
        req.put("Descripcion", DescripcionMenu.getText().toString());

        req.put("restaurant_id", restaurant_id);




        client.post(EndPoinds.MENU, req, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String msn = response.getString("msn");
                    Toast.makeText(root, response.getString("msn"), Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

//PERMISOS DE CAMARA

/*
    static final int PERMISION_CODE = 10;
    static final int code_camera = 99;

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

 private void callCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (camera.resolveActivity(this.getPackageManager()) != null) {
            this.startActivityForResult(camera, code_camera);

        }

* */



