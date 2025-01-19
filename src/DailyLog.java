/* A class that simulates a daily logger for the meal planner
 * @author Jonathan Setiawan (26002404663)
 * @date Dec 15, 2024
 */
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DailyLog {
    // initializing fields, make a list to track the meals every day, calorieCounter, proteinCounter,
    // waterIntake, and currentDate that uses the localDate library to track in real life dates
    private ArrayList<Food> meals;
    private CalorieCounter calorieCounter;
    private ProteinCounter proteinCounter;
    private WaterIntake waterIntake;
    private LocalDate currentDate;

    // basic constructor to initiate the log
    public DailyLog() {
        meals = new ArrayList<>();
        calorieCounter = new CalorieCounter();
        proteinCounter = new ProteinCounter();
        waterIntake = new WaterIntake();
        currentDate = LocalDate.now();
        // the currentDate is automatically set to the current date when you log it
    }

    // methods to add meals to the meal list, logWater to log the water intake, and method
    // to display the daily consumption/log
    public void addMeal(Food food) {
        meals.add(food);
        calorieCounter.addValue(food.getCalories());
        proteinCounter.addValue(food.getProtein());
    }

    public void logWater(int liters) {
        waterIntake.addWater(liters);
    }

    public void displayDailySummary() {
        // so when you call this method, you immediately automatically log the date of when you log it,
        // list out all the meals you had for the day,
        System.out.println("Date: " + currentDate);
        System.out.println("Today's Meals:");

        for (Food food : meals) {
            System.out.println(food);
        }

        System.out.println("Total Calories: " + calorieCounter.getTotal());
        System.out.println("Total Protein Intake: " + proteinCounter.getTotal() + "g");
        System.out.println("Water Intake: " + waterIntake.getTotalLiters() + "L");

        // possible idea, how can I make it so that when I have an input, it can get added to the list
        // and next time I run the main method as well, I'll still have the previous log and can refer to
        // them if I were to just call the number in the list
    }

    // method to write the contents of the log to a .txt file
    public void saveToFile(String filename) {
        // try and catch for errors when writing the file, new FileWriter object,
        // write the date, all the meals with their attributes, and water intake to the .txt file
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Date: " + currentDate + "\n");
            for (Food food : meals) {
                writer.write(food.getName() + "," + food.getCalories() + "," + food.getProtein() +  "\n");
            }
            writer.write("Water Intake: " + waterIntake.getTotalLiters() + "L\n");
            System.out.println("Log saved to file: " + filename);
        }

        catch (IOException e) {
            // catch the exception if it returns an error exception then print the error
            System.err.println("Error saving file: " + e.getMessage());
        }
    }

}
