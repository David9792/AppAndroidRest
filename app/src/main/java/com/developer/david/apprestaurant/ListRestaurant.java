package com.developer.david.apprestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.david.apprestaurant.ui.adapter.AdapterRestaurant;
import com.developer.david.apprestaurant.ui.adapter.Item;
import com.developer.david.apprestaurant.utils.EndPoinds;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ListRestaurant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_restaurant);

        loadDescripcion();
    }

    private void loadDescripcion() {
        AsyncHttpClient restaurant = new AsyncHttpClient();
        RequestParams datos = new RequestParams();
        final ListView lista = (ListView)this.findViewById(R.id.listaRestaurante);
        final ArrayList<itemRestaurant> list_des = new ArrayList<itemRestaurant>();
        restaurant.get(EndPoinds.ip+"/restaurant", datos, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONArray response){
                // Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                try {
                    //JSONArray data = response.getJSONArray("data");
                    for(int i=0;i<response.length();i++){
                        itemRestaurant restaur = new itemRestaurant();
                        JSONObject obj = response.getJSONObject(i);
                        //Toast.makeText(MainActivity.this,""+response.length(),Toast.LENGTH_SHORT).show();
                        restaur.id = i;
                        restaur.Name= obj.getString("Nombre");
                        restaur.Nid = obj.getString("Nit");
                        restaur.Owner = obj.getString("Propietario");
                        restaur.Streed = obj.getString("Calle");
                        restaur.Phone = obj.getString("Telefono");

                        list_des.add(restaur);
                    }
                    DescripcionRestaurant descrip = new DescripcionRestaurant(ListRestaurant.this, list_des);
                    lista.setAdapter(descrip);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            public void OnFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse){

            }
        });
    }

    /*private Activity root = this;
    Button nuevo;
    FloatingActionButton nuevito;
    RecyclerView rec;
    LinearLayoutManager lnm;
    AdapterRestaurant adp;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_restaurant);
        load();
        listRest();
        nuevo = findViewById(R.id.nuenoRest);
        nuevito = findViewById(R.id.floatingActionButton);
        nuevito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListRestaurant.this, CrearRestaurant.class);

                startActivity(intent);
            }
        });

    }

    private void listRest() {
        AsyncHttpClient client = new AsyncHttpClient();
        TextView Nombre = root.findViewById(R.id.Nombre_home);
        TextView Nit = root.findViewById(R.id.Nit_home);
        TextView Propietario = root.findViewById(R.id.Propietario_home);
        TextView Calle = root.findViewById(R.id.Calle_home);
        TextView Telefono = root.findViewById(R.id.Telefono_home);

        RequestParams params=new RequestParams();


        params.add("Nombre", Nombre.getText().toString());
        params.add("Nit", Nit.getText().toString() );
        params.add("Propietario", Propietario.getText().toString() );
        params.add("Calle", Calle.getText().toString() );
        params.add("Telefono", Telefono.getText().toString() );

        client.get(EndPoinds.REST, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                   // String res=response.getString("message");
                    //Toast.makeText(ListRestaurant.this,""+response.getString("token"),Toast.LENGTH_LONG).show();

                    String msn = response.getString("msn");
                    Toast.makeText(root, response.getString("token"), Toast.LENGTH_LONG).show();
                }catch (Exception e){

                }
            }
        });*/

    }
    /*public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_list_restaurant, container, false);

        rec=root.findViewById(R.id.rest_recycler);
        lnm = new LinearLayoutManager(context);
        adp = new AdapterRestaurant(context);
        rec.setLayoutManager(lnm);
        rec.setAdapter(adp);


        return root;
    }*/
   /* private void load() {

        rec=root.findViewById(R.id.rest_recycler);
        lnm = new LinearLayoutManager(context);
        adp = new AdapterRestaurant(context);
        rec.setLayoutManager(lnm);
        rec.setAdapter(adp);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(EndPoinds.ip+"/restaurant", null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);

                for (int i=0; i<response.length();i++){
                    try {
                        JSONObject ob=response.getJSONObject(i);
                        Item item = new Item(ob.getString("_id"),ob.getString("Nombre"),
                                ob.getString("Nit"),ob.getString("Propietario"),
                                ob.getString("Calle"),ob.getString("Telefono"),1);
                        adp.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }*/




