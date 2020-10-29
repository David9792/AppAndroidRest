package com.developer.david.apprestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.developer.david.apprestaurant.utils.UserDataServer;

public class cardAdd extends AppCompatActivity {

    TextView nomMenu,cantidad, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_add);

        nomMenu = findViewById(R.id.nombre_menu);
        cantidad =findViewById(R.id.cantidad);
        total = findViewById(R.id.total);

        final ListView lista = (ListView)this.findViewById(R.id.listaMenu);
    }
}