package com.developer.david.apprestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DescripcionMenu extends BaseAdapter {

    private Context contex;
    private ArrayList<itemMenu> item;

    public DescripcionMenu(Context context, ArrayList<itemMenu> item){
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
            view = inflate.inflate(R.layout.get_menu,null);
        }
        TextView nombre = (TextView)view.findViewById(R.id.nombreMenus);
        TextView precio = (TextView)view.findViewById(R.id.precioMenus);
        TextView descripcion = (TextView)view.findViewById(R.id.descripcionMenus);


        final itemMenu  itemMenu = item.get(i);


        nombre.setText(itemMenu.getNombreMenu());
        precio.setText(itemMenu.getPrecio());
        descripcion.setText(itemMenu.getDescripcion());

        nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(contex,""+itemMenu.getIdm​(),Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
