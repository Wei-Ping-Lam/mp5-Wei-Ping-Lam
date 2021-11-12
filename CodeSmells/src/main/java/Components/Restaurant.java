package Components;

public class Restaurant {

    private String restaurantName;
    private static Restaurant instance;

    public SeatingSystem getSeatingSystem() {
        return seatingSystem;
    }

    private SeatingSystem seatingSystem;

    public Menu getMenu() {
        return menu;
    }

    private Menu menu;

    private Restaurant(String restaurantName,
                       String tableConfigFilePath,
                       String menuConfigFilePath){
        this.restaurantName = restaurantName;
        this.seatingSystem = new SeatingSystem(tableConfigFilePath);
        this.menu = new Menu(menuConfigFilePath);
        OrderQueue.getOrCreateInstance(10);
        ServingQueue.getOrCreateInstance(10);
    }

    public static Restaurant getInstance() {
        return instance;
    }

    public static Restaurant getOrCreateInstance(String restaurantName,
                                                 String tableConfigFilePath,
                                                 String menuConfigFilePath){
        synchronized (Restaurant.class) {
            if (instance == null) {
                instance = new Restaurant(restaurantName,tableConfigFilePath,menuConfigFilePath);
                System.out.println("First instance of Restaurant created.");
            }
        }
        return instance;
    }

    public static void clearInstance(){
        instance = null;
        OrderQueue.clearInstance();
        ServingQueue.clearInstance();
    }


}
