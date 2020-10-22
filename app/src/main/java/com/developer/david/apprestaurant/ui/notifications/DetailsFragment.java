package com.developer.david.apprestaurant.ui.notifications;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.developer.david.apprestaurant.R;
import com.developer.david.apprestaurant.recurso;
import com.developer.david.apprestaurant.utils.EndPoinds;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class DetailsFragment extends Fragment implements View.OnClickListener {

    String a, b, c, d, e, f;
    EditText Nombre, Nit, Propietario, Calle, Telefono;
    Button Btn;
    Context context;
    View redir;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            a = getArguments().getString("Nombre");
            b = getArguments().getString("Nit");
            c = getArguments().getString("Propietario");
            d = getArguments().getString("Calle");
            e = getArguments().getString("Telefono");
            f = getArguments().getString("id");
        }
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_details, container, false);

        Nombre = root.findViewById(R.id.not_Nombre);
        Nit = root.findViewById(R.id.not_Nit);
        Propietario = root.findViewById(R.id.not_Propietario);
        Calle = root.findViewById(R.id.not_Calle);
        Telefono = root.findViewById(R.id.not_Telefono);
        Btn = root.findViewById(R.id.not_Btn);

        Nombre.setText(a);
        Nit.setText(b);
        Propietario.setText(c);
        Calle.setText(d);
        Telefono.setText(e);

        //Toast.makeText(context, ""+a+" "+b+" "+c+" "+d+" "+e, Toast.LENGTH_SHORT).show();
        redir = root;

        Btn.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.not_Btn) {
            load();
        }
    }

    private void load() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams req=new RequestParams();
        req.put("Nombre",Nombre.getText().toString());
        req.put("Nit",Nit.getText().toString());
        req.put("Propietario",Propietario.getText().toString());
        req.put("Calle",Calle.getText().toString());
        req.put("Telefono",Telefono.getText().toString());

//cambie de get a patch
        client.put(EndPoinds.ip+"/restaurant?id="+f, req, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String res=response.getString("message");
                    Toast.makeText(context,""+res,+Toast.LENGTH_LONG).show();
                    Navigation .findNavController(redir).navigate(R.id.action_navigation_details_to_navigation_notifications);
                }catch (Exception e){

                }
            }
        });
    }
}