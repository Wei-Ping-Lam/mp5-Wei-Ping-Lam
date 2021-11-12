package Terminals;

import Components.Restaurant;
import Components.SeatingSystem;
import Components.ServingQueue;
import Entities.Dish;
import Entities.Serving;
import Entities.SingleTable;
import Entities.TerminalPrintType;
import java.util.ArrayList;
import java.util.List;

public class ServiceDeskTerminal extends Terminal{

    private List<SingleTable> tables;
    private String tab = "Table ";

    public ServiceDeskTerminal(){
        super();
        tables = new ArrayList<SingleTable>();
    }

    public KitchenTerminal grandOpening(String res, String tabl, String men){
        Restaurant.getOrCreateInstance(
                res,
                tabl,
                men
        );
        return new KitchenTerminal();
    }
    public KitchenTerminal grandOpening(){

        return grandOpening("MyRestaurant","tables.txt","menu.txt");
    }

    public void closeBusinesss(){
        printToScreen("Business closed.");
        Restaurant.clearInstance();
    }

    public CustomerTerminal checkIn(int numberOfPeople){
        Restaurant re = Restaurant.getInstance();
        SeatingSystem ss = re.getSeatingSystem();
        SingleTable table = ss.getAvailableTable(numberOfPeople);
        CustomerTerminal flag;
        if(table != null){

            if(ss.occupy(table)){
                tables.add(table);
                String flagOne = " checked in, number of people: ";
                printToScreen("New table " + table.getIndex() + flagOne + numberOfPeople);
                flag = new CustomerTerminal(table);
            }else{
                String flagTwo = " occupied. Check in failed.";
                printToScreen(tab + table.getIndex() + flagTwo, TerminalPrintType.Error);
                flag = null;
            }
        }else{
            printToScreen("Not enough seat", TerminalPrintType.Error);
            flag = null;
        }
        return flag;
    }

    public void checkOut(SingleTable table){
        Restaurant re = Restaurant.getInstance();
        SeatingSystem ss = re.getSeatingSystem();
        if(table.isCheckingOut()){

            if(ss.vacate(table)){
                tables.remove(table);
                printToScreen(tab+table.getIndex()+" checked out.");
            }else {
                String dl = "Vacating table ";
                printToScreen(dl + table.getIndex() + " failed.", TerminalPrintType.Error);
            }
        }
    }

    public void serveDish(){
        ServingQueue sq = ServingQueue.getInstance();
        Serving serving = sq.take();
        Dish dish = serving.getDish();
        SingleTable table = serving.getToTable();
        table.addDish(dish);
        printToScreen(dish.getMenuItem().getDishName()+ " served to table " + table.getIndex());
    }

}
