package Entities;

import Entities.MenuItem;

public class Dish {
    MenuItem menuItem;

    public Dish(MenuItem item){
        menuItem = item;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }
}
