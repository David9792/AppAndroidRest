package com.developer.david.apprestaurant.ui.notifications;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.david.apprestaurant.R;
import com.developer.david.apprestaurant.ui.adapter.AdapterRestaurant;
import com.developer.david.apprestaurant.ui.adapter.Item;
import com.developer.david.apprestaurant.utils.EndPoinds;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class NotificationsFragment extends Fragment implements AdapterRestaurant.SendData{

    RecyclerView rec;
    LinearLayoutManager lnm;
    AdapterRestaurant adp;
    Context context;
    View redir;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        rec = root.findViewById(R.id.recycler_not);
        lnm = new LinearLayoutManager(context);
        adp = new AdapterRestaurant(context, this);
        rec.setLayoutManager(lnm);
        rec.setAdapter(adp);

        load();

        redir = root;
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
                                ob.getString("Calle"),ob.getString("Telefono"),2);
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
        Navigation.findNavController(redir).navigate(R.id.action_navigation_notifications_to_navigation_details,data);
    }
}