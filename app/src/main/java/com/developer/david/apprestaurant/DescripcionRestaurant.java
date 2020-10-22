package com.developer.david.apprestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class DescripcionRestaurant extends BaseAdapter {

    private Context contex;
    private List<itemRestaurant> item;

    public DescripcionRestaurant(Context context, ArrayList<itemRestaurant> item){
        this.contex= context;
        this.item = item;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return this.item.get(i).getId​();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflate = (LayoutInflater)
                    this.contex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflate.inflate(R.layout.activity_list_restaurant,null);
        }
        TextView nombre = (TextView)view.findViewById(R.id.restnom);
        TextView nit = (TextView)view.findViewById(R.id.restNitt);
        TextView propietario = (TextView)view.findViewById(R.id.restProp);
        TextView calle = (TextView)view.findViewById(R.id.restCall);
        TextView telefono = (TextView)view.findViewById(R.id.restTelef);



        nombre.setText(this.item.get(i).getNombre());
        nit.setText(this.item.get(i).getNit());
        propietario.setText(this.item.get(i).getPropietario());
        calle.setText(this.item.get(i).getCalle());
        telefono.setText(this.item.get(i).getTelefono​());


        return view;
    }
}
