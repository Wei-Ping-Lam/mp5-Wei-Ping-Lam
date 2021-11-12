package Loaders;

import Entities.MenuItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MenuFileLoader {

    private final static String[] headers = {"MenuItem Name", "Type" ,"Price", "Calorie"};
    private String fileName;
    public MenuFileLoader(String fileName){
        this.fileName = fileName;
    }

    public File getDefaultFile() {
        return new File("./menu.txt");
    }


    public List<MenuItem> load(){
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
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
             String dishName = line.get(0).trim();String dishType = line.get(1).toUpperCase().trim();
             double dishPrice = Double.parseDouble(line.get(2).trim());double dishCalorie = Double.parseDouble(line.get(3).trim());
             MenuItem menuItem = new MenuItem(dishName,dishType,dishPrice,dishCalorie);
             menuItems.add(menuItem);
        }
        return menuItems;
    }
}
