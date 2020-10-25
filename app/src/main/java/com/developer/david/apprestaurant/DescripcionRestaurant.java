package com.developer.david.apprestaurant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        return item.size();
    }

    @Override
    public Object getItem(int i) {
        return item.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflate = (LayoutInflater)
                    this.contex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflate.inflate(R.layout.get_rest,null);
        }
        TextView nombre = (TextView)view.findViewById(R.id.nombres);
        TextView nit = (TextView)view.findViewById(R.id.nits);
        TextView propietario = (TextView)view.findViewById(R.id.propietarios);
        TextView calle = (TextView)view.findViewById(R.id.calles);
        TextView telefono = (TextView)view.findViewById(R.id.telefonos);
        Button btnpedido = (Button)view.findViewById(R.id.btnPedido);

        final itemRestaurant  itemRestaurant = item.get(i);


        nombre.setText(itemRestaurant.getNombre());
        nit.setText(itemRestaurant.getNit());
        propietario.setText(itemRestaurant.getPropietario());
        calle.setText(itemRestaurant.getCalle());
        telefono.setText(itemRestaurant.getTelefono​());

        nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(contex,""+itemRestaurant.getId​(),Toast.LENGTH_LONG).show();
            }
        });
        //Recuperando
        btnpedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contex, ListMenu.class);
                intent.putExtra("id",itemRestaurant.getId​());
                contex.startActivity(intent);
            }
        });

        return view;
    }
}
