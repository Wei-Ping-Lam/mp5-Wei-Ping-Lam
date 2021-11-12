package Loaders;

import Entities.SingleTable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TableFileLoader {

    private final static String[] headers = {"Table Number", "Number of Seats"};
    private String fileName;
    public TableFileLoader(String fileName){
        this.fileName = fileName;
    }

    public File getDefaultFile() {
        return new File("./tables.txt");
    }


    public List<SingleTable> load(){
        List<SingleTable> tables = new ArrayList<SingleTable>();

        int numberOfColumns = headers.length;
        boolean useDefault = true;

        BufferedReader breader = null;File file;
        List<List<String>> result = new ArrayList<List<String>>();


        try {
            file = new File(fileName);
            if (!file.exists()) {
                if(useDefault){
                    file = getDefaultFile();
                }else
                    throw new IllegalArgumentException("The specified Components.Menu File does not exist.");
            }

            breader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));


            String line = breader.readLine();
            while(line != null){
                String[] entries = line.split(",");
                List<String> lineEntry = new ArrayList<String>();

                if(entries.length != numberOfColumns){
                    throw new IllegalArgumentException("The specified Columns are incorrect.");
                }else{

                }
                for(String entry : entries){
                    lineEntry.add(entry);
                }
                result.add(lineEntry);
                line = breader.readLine();
            }
        } catch (IOException e) {


        } finally {
            try {
                if (breader != null)
                    breader.close();


            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
        for(List<String> line : result ){
            int tableIndex = Integer.parseInt(line.get(0).trim());
            int tableSeats = Integer.parseInt(line.get(1).trim());
            SingleTable table = new SingleTable(tableIndex,tableSeats);
            tables.add(table);
        }
        return tables;
    }
}
