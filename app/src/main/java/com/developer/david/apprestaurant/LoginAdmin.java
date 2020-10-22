package com.developer.david.apprestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginAdmin extends AppCompatActivity {
    Button buttonLogin ;
    private Activity root = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        loadcomponents();
    }

    private void loadcomponents() {
        buttonLogin = this.findViewById(R.id.logAdmin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root, ListRestaurant.class);
                root.startActivity(intent);
            }
        });

    }
}