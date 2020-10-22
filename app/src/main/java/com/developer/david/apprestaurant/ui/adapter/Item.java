package com.developer.david.apprestaurant.ui.adapter;

public class Item {
    private String id;
    private String Nombre;
    private String Nit;
    private String Propietario;
    private String Calle;
    private String Telefono;
    private int type;

    public Item(String id, String Nombre, String Nit, String Propietario, String Calle, String Telefono, int type){
        this.id=id;
        this.Nombre=Nombre;
        this.Nit=Nit;
        this.Propietario=Propietario;
        this.Calle=Calle;
        this.Telefono=Telefono;
        this.type=type;

    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getNit() {
        return Nit;
    }

    public String getPropietario() {
        return Propietario;
    }

    public String getCalle() {
        return Calle;
    }

    public String getTelefono() {
        return Telefono;
    }

    public int getType() {
        return type;
    }
}
