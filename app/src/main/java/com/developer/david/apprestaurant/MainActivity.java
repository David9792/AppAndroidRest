package com.developer.david.apprestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AsyncNotedAppOp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.developer.david.apprestaurant.utils.EndPoinds;
import com.developer.david.apprestaurant.utils.UserDataServer;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    Button loginButton, registerButton, loginAdminButton;
    private Activity root = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadComponents();
    }

    private void loadComponents() {
        loginButton = this.findViewById(R.id.logUser);
        loginAdminButton = this.findViewById(R.id.admin);
        registerButton = this.findViewById(R.id.register);

        loginAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root, LoginAdmin.class);
                root.startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root, RegisterUser.class);
                root.startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(root, MenuUser.class);
                root.startActivity(intent);*/
                AsyncHttpClient client = new AsyncHttpClient();

                EditText email = root.findViewById(R.id.user);
                EditText password = root.findViewById(R.id.pass);

                RequestParams params = new RequestParams();

                params.add("Nombre", email.getText().toString() );
                params.add("Password", password.getText().toString() );

                client.post(EndPoinds.LOGIN_SERVICE, params, new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            if (response.has("msn")){
                                UserDataServer.MSN = response.getString("msn");
                            }
                            if (response.has("token"))
                            {
                                UserDataServer.TOKEN = response.getString("token");
                            }
                            if (UserDataServer.TOKEN.length() > 150){

                            }else {
                                Toast.makeText(root, response.getString("msn"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });


    }
}