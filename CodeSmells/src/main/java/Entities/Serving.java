package Entities;

import Entities.SingleTable;
import Entities.Dish;

public class Serving {

    private SingleTable toTable;
    private Dish dish;

    public Serving(SingleTable toTable, Dish dish) {
        this.toTable = toTable;
        this.dish = dish;
    }

    public Dish getDish() {
        return dish;
    }

    public SingleTable getToTable() {
        return toTable;
    }
}
