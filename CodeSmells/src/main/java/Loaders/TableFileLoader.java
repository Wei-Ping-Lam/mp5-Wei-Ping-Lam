package Loaders;

import Entities.SingleTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TableFileLoader {

    private final static String[] HEADERS = {"Table Number", "Number of Seats"};
    private String fileName;
    public TableFileLoader(String fileName){
        this.fileName = fileName;
    }

    public File getDefaultFile() {
        return new File("./tables.txt");
    }


    public List<SingleTable> load(){
        int numberOfColumns = HEADERS.length;
        BufferedReader breader = null;
        File file;
        List<List<String>> result = new ArrayList<List<String>>();
        try {
            file = new File(fileName);
            if (!file.exists()) {
                file = getDefaultFile(); }
            breader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line = breader.readLine();
            while(line != null){
                String[] entries = line.split(",");
                List<String> lineEntry = new ArrayList<String>();
                if(entries.length != numberOfColumns){
                    throw new IllegalArgumentException("The specified Columns are incorrect.");
                }
                for(String entry : entries){
                    lineEntry.add(entry); }
                result.add(lineEntry);
                line = breader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (breader != null) {
                    breader.close();
                }
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
        return helperOne(result);
    }

    public List<SingleTable> helperOne(List<List<String>> result){
        List<SingleTable> tables = new ArrayList<SingleTable>();
        for(List<String> line : result ){
            int tableIndex = Integer.parseInt(line.get(0).trim());
            int tableSeats = Integer.parseInt(line.get(1).trim());
            SingleTable table = new SingleTable(tableIndex,tableSeats);
            tables.add(table);
        }
        return tables;
    }
}
