package com.developer.david.apprestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.david.apprestaurant.utils.EndPoinds;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class CrearRestaurant extends AppCompatActivity {
    private Activity root = this;
    Button RegistroRestaurant;
    TextView NombreRes, NitRes, PropietarioRes,CalleRes, TelefonoRes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_restaurant);
        getSupportActionBar();
        NombreRes = findViewById(R.id.restnom);
        NitRes = findViewById(R.id.restNitt);
        PropietarioRes = findViewById(R.id.restProp);
        CalleRes = findViewById(R.id.restCall);
        TelefonoRes = findViewById(R.id.restTelef);
        RegistroRestaurant = findViewById(R.id.restbtn);

        RegistroRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CrearRestaurant.this, MainActivity.class);
                senResgistrar();
                startActivity(intent);
            }
        });
    }

    public void senResgistrar(){
        AsyncHttpClient client = new AsyncHttpClient();
        final RequestParams req = new RequestParams();
        req.put("Nombre", NombreRes.getText().toString());
        req.put("Nit", NitRes.getText().toString());
        req.put("Propietario", PropietarioRes.getText().toString());
        req.put("Calle", CalleRes.getText().toString());
        req.put("Telefono", TelefonoRes.getText().toString());
        //req.put("Roles", Roles.getText().toString());

        client.post(EndPoinds.REST, req, new JsonHttpResponseHandler(){
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