package com.developer.david.apprestaurant.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.david.apprestaurant.R;

import java.util.ArrayList;

public class AdapterRestaurant extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    ArrayList<Item> mArray;
    SendData listen;


    public AdapterRestaurant(Context context, SendData listen){
        this.context=context;
        mArray = new ArrayList<>();
        this.listen = listen;

    }

    public AdapterRestaurant(Context context){
        this.context=context;
        mArray = new ArrayList<>();

    }

    public void add (Item item){
        mArray.add(item);
        notifyItemInserted(mArray.indexOf(item));
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //creamos una vista
        View view;
        if(viewType==1){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_get_restaurant,parent,false);
            //retornamos
            return new HolderGet(view);
        }else if(viewType==2) {
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patch_restaurant,parent,false);
            return new HolderPatch(view);
        }
        else {
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_delete_restaurant,parent,false);
            return new HolderDelete(view);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        //comparamos
        final Item it=mArray.get(position);
        if(getItemViewType(position)==1){
            ((HolderGet)holder).Nombre.setText(it.getNombre());
            ((HolderGet)holder).Nit.setText(it.getNit());
            ((HolderGet)holder).Propietario.setText(it.getPropietario());
            ((HolderGet)holder).Calle.setText(it.getCalle());
            ((HolderGet)holder).Telefono.setText(it.getTelefono());

        }else if(getItemViewType(position)==2){
            ((HolderPatch)holder).Nombre.setText(it.getNombre());
            ((HolderPatch)holder).Nit.setText(it.getNit());
            ((HolderPatch)holder).Propietario.setText(it.getPropietario());
            ((HolderPatch)holder).Calle.setText(it.getCalle());
            ((HolderPatch)holder).Telefono.setText(it.getTelefono());
            ((HolderPatch)holder).view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("Nombre", it.getNombre());
                    bundle.putString("Nit", it.getNit());
                    bundle.putString("Propietario", it.getPropietario());
                    bundle.putString("Calle", it.getCalle());
                    bundle.putString("Telefono", it.getTelefono());
                    bundle.putString("id", it.getId());
                    listen.dataRestaurant(bundle);
                }
            });
        }else {
            ((HolderDelete)holder).Nombre.setText(it.getNombre());
            ((HolderDelete)holder).Nit.setText(it.getNit());
            ((HolderDelete)holder).Propietario.setText(it.getPropietario());
            ((HolderDelete)holder).Calle.setText(it.getCalle());
            ((HolderDelete)holder).Telefono.setText(it.getTelefono());
            ((HolderDelete)holder).btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", it.getId());
                    listen.dataRestaurant(bundle);
                    delete(position);
                }
            });
        }
    }
    private void delete(int position) {
        mArray.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mArray.size();
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        if(mArray.get(position).getType()==1){
            return 1;
        }else if(mArray.get(position).getType()==2){
            return 2;
        }else{
            return 3;
        }
    }

    class HolderGet extends RecyclerView.ViewHolder {
        TextView Nombre, Nit, Propietario, Calle, Telefono;

        public HolderGet(@NonNull View itemView) {

            super(itemView);
            Nombre = itemView.findViewById(R.id.Nombre_home);
            Nit = itemView.findViewById(R.id.Nit_home);
            Propietario = itemView.findViewById(R.id.Propietario_home);
            Calle = itemView.findViewById(R.id.Calle_home);
            Telefono = itemView.findViewById(R.id.Telefono_home);
        }
    }
    class HolderPatch extends RecyclerView.ViewHolder {
        TextView Nombre, Nit, Propietario, Calle, Telefono;
        View view;
        public HolderPatch(@NonNull View itemView) {
            super(itemView);
            Nombre = itemView.findViewById(R.id.Nombre_not);
            Nit = itemView.findViewById(R.id.Nit_not);
            Propietario = itemView.findViewById(R.id.Propietario_not);
            Calle = itemView.findViewById(R.id.Calle_not);
            Telefono = itemView.findViewById(R.id.Telefono_not);
            view = itemView;
        }
    }

    class HolderDelete extends RecyclerView.ViewHolder {
        TextView Nombre, Nit, Propietario, Calle, Telefono;
        Button btn;
        public HolderDelete(@NonNull View itemView) {
            super(itemView);
            Nombre = itemView.findViewById(R.id.delete_Nombre);
            Nit = itemView.findViewById(R.id.delete_Nit);
            Propietario = itemView.findViewById(R.id.delete_Propietario);
            Calle = itemView.findViewById(R.id.delete_Calle);
            Telefono = itemView.findViewById(R.id.delete_Telefono);
            btn = itemView.findViewById(R.id.delete_btn);
        }
    }

    public interface SendData {
        void dataRestaurant(Bundle data);
    }
}
