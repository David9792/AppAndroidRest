package com.developer.david.apprestaurant;

import com.developer.david.apprestaurant.ui.adapter.Item;

public class ItemCart extends itemMenu {

    public itemMenu menu;
    public int cantidad;

    public itemMenu  getMenu() {
        return this.menu;
    }

    public int getCantidad() {
        return this.cantidad;
    }


    public ItemCart(itemMenu menu, int cantidad) {
        this.menu = menu;
        this.cantidad = cantidad;

    }
}
