public class Main {
    public static void main(String[] args) {
        // Create a DayTracker instance and a Menu instance
        DailyLog dailyLog = new DailyLog();
        Menu menu = new Menu();

        // Add predefined food items to the Menu
        System.out.println("=== Adding Default Foods to the Menu ===");
        menu.addFood(new Food("Chicken Nanban", 250, 30));
        menu.addFood(new Food("Salad", 150, 5));
        menu.addFood(new Food("Steamed Rice", 200, 4));
        menu.addFood(new Food("Onsen egg", 100, 6));
        menu.displayMenu();

        // Add the meals for the day
        System.out.println("\n=== Logging Meals for the Day ===");
        dailyLog.addMeal(menu.getFoodByIndex(0)); // Chicken Nanban
        dailyLog.addMeal(menu.getFoodByIndex(1)); // Salad
        dailyLog.addMeal(menu.getFoodByIndex(2)); // Steamed Rice
        dailyLog.addMeal(new Food("cake", 1000, 20)); // Custom food not in menu

        // Log water intake
        System.out.println("\n=== Logging Water Intake ===");
        dailyLog.logWater(2);
        dailyLog.logWater(1);

        // Display the daily summary
        System.out.println("\n=== Displaying Daily Summary ===");
        dailyLog.displayDailySummary();

        // Save the log to a file
        System.out.println("\n=== Saving Log to File ===");
        dailyLog.saveToFile("daily_log_test.txt");
    }
}
