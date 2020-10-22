package com.developer.david.apprestaurant.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.developer.david.apprestaurant.R;
import com.developer.david.apprestaurant.recurso;
import com.developer.david.apprestaurant.utils.EndPoinds;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class DashboardFragment extends Fragment implements View.OnClickListener {

    //private DashboardViewModel dashboardViewModel;

    EditText Nombre,Nit,Propietario,Calle,Telefono;
    Button btn;
    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        /*final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        Nombre=root.findViewById(R.id.dash_Nombre);
        Nit=root.findViewById(R.id.dash_Nit);
        Propietario=root.findViewById(R.id.dash_Propietario);
        Calle=root.findViewById(R.id.dash_Calle);
        Telefono=root.findViewById(R.id.dash_Telefono);
        btn=root.findViewById(R.id.dash_Btn);

        btn.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.dash_Btn){
            load();
        }
    }

    private void load() {
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams req=new RequestParams();
        req.put("Nombre",Nombre.getText().toString());
        req.put("Nit",Nit.getText().toString());
        req.put("Propietario",Propietario.getText().toString());
        req.put("Calle",Calle.getText().toString());
        req.put("Telefono",Telefono.getText().toString());


        client.post(EndPoinds.ip+"/restaurant", req, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String res=response.getString("message");
                    Toast.makeText(context,"",+Toast.LENGTH_LONG).show();
                }catch (Exception e){

                }
            }
        });
    }
}