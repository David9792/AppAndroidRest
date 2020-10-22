package com.developer.david.apprestaurant.ui.delete;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.developer.david.apprestaurant.R;
import com.developer.david.apprestaurant.recurso;
import com.developer.david.apprestaurant.ui.adapter.AdapterRestaurant;
import com.developer.david.apprestaurant.ui.adapter.Item;
import com.developer.david.apprestaurant.utils.EndPoinds;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class DeleteFragment extends Fragment implements AdapterRestaurant.SendData{

    Context context;

    RecyclerView rec;
    LinearLayoutManager lnm;
    AdapterRestaurant adp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_delete, container, false);

        rec = root.findViewById(R.id.recycler_delete);
        lnm = new LinearLayoutManager(context);
        adp = new AdapterRestaurant(context, this);
        rec.setLayoutManager(lnm);
        rec.setAdapter(adp);

        load();

        return root;
    }

    private void load() {
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
                                ob.getString("Calle"),ob.getString("Telefono"),3);
                        adp.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void dataRestaurant(Bundle data) {
        String id = data.getString("id");
        delete(id);
    }
    private void delete(String id) {
        AsyncHttpClient client = new AsyncHttpClient();

        client.delete(EndPoinds.ip+"/restaurant?id="+id, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    Toast.makeText(context, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
