package com.developer.david.apprestaurant.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class HomeFragment extends Fragment {

    //private HomeViewModel homeViewModel;
    RecyclerView rec;
    LinearLayoutManager lnm;
    AdapterRestaurant adp;
    Context context;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        rec=root.findViewById(R.id.home_recycler);
        lnm = new LinearLayoutManager(context);
        adp = new AdapterRestaurant(context);
        rec.setLayoutManager(lnm);
        rec.setAdapter(adp);
        
        load();

        return root;
    }

    private void load() {
        AsyncHttpClient  client = new AsyncHttpClient();
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
    }
}