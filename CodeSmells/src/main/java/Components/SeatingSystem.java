package Components;

import Loaders.TableFileLoader;
import Entities.SingleTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatingSystem {

    private String tableConfigFilePath;
    private Map<SingleTable,Boolean> tables;

    public SeatingSystem(String tableConfigFilePath){
        this.tableConfigFilePath = tableConfigFilePath;
        populateSeats();
    }

    private void populateSeats(){
        TableFileLoader loader = new TableFileLoader(tableConfigFilePath);
        List<SingleTable> tableList = loader.load();
        tables = new HashMap<SingleTable,Boolean>();
        for (SingleTable i : tableList) {
            tables.put(i, false);
        }
    }

    public int[] getNumberOfSeatsForAllTable(){
        int[] seats = new int[tables.size()];
        int i = 0;
        for(SingleTable table : tables.keySet()){
            seats[i] = table.getNumberOfSeats();
            i++;
        }
        return seats;
    }

    public int[] getIndexForAllTable(){
        int[] indices = new int[tables.size()];
        int i = 0;
        for(SingleTable table : tables.keySet()){
            indices[i] = table.getIndex();
            i++;
        }
        return indices;
    }

    public SingleTable getAvailableTable(int numberOfPeople){
        SingleTable result = null;
        for( Map.Entry<SingleTable, Boolean> entry : tables.entrySet()){
            boolean flagOne = entry.getValue();
            int flagTwo = entry.getKey().getNumberOfSeats();
            boolean flagThree = result == null;
            int flagFour = entry.getKey().getNumberOfSeats();
            boolean flagFive = flagTwo >= numberOfPeople;
            if(!flagOne && flagFive && (flagThree || flagFour < result.getNumberOfSeats())){
                result = entry.getKey();
            }
        }
        return result;
    }

    public boolean occupy(SingleTable table){
        boolean flag;
        if(tables.get(table)){
            flag = false;
        }else{
            tables.put(table, true);
            flag = true;
        }
        return flag;
    }

    public boolean vacate(SingleTable table){
        boolean flag;
        if(!tables.get(table)){
            flag = false;
        }else{
            tables.put(table,false);
            flag = true;
        }
        return flag;
    }



}
