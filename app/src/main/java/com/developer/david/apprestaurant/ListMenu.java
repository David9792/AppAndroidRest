package com.developer.david.apprestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.developer.david.apprestaurant.utils.EndPoinds;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ListMenu extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);
        loadDescripcion();
    }

    private void loadDescripcion() {


        AsyncHttpClient menu = new AsyncHttpClient();
        RequestParams datos = new RequestParams("restaurant_id",getIntent().getStringExtra("restaurant_id"));
        final ListView lista = (ListView)this.findViewById(R.id.listaMenu);
        final ArrayList<itemMenu> list_desc = new ArrayList<itemMenu>();
        menu.get(EndPoinds.ip+"/menus", datos, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONArray response){
                //Toast.makeText(ListRestaurant.this, ""+response, Toast.LENGTH_SHORT).show();
                try {
                    //JSONArray data = response.getJSONArray("data");
                    for(int i=0;i<response.length();i++){
                        itemMenu men = new itemMenu();
                        JSONObject obj = response.getJSONObject(i);
                        //Toast.makeText(MainActivity.this,""+response.length(),Toast.LENGTH_SHORT).show();
                        men.idm = obj.getString("_id");
                        men.NameMenu= obj.getString("Nombre");
                        men.Price = obj.getString("Precio")+"  Bs.";
                        men.Description = obj.getString("Descripcion");
                        list_desc.add(men);
                        String id = getIntent().getStringExtra("restaurant_id"); // Este es el id que se recupera
                        Toast.makeText(ListMenu.this, id, Toast.LENGTH_LONG).show();
                    }
                  DescripcionMenu descrip = new DescripcionMenu(ListMenu.this, list_desc);
                    lista.setAdapter(descrip);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            public void OnFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse){

            }
        });
    }

}