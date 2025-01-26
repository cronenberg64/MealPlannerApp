/* Making the home and log page using GUI for the meal planner
 * @author Jonathan Setiawan (26002404663)
 * @date Jan 18, 2025
 */

package course.programminglanguageproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class MealPlannerGUI extends Application {
    // Fields
    private TextField mealNameField, calorieField, proteinField, waterIntakeField;
    // fields for the user inputs on meal name, calorie, protein, and water intake
    private TextArea logArea, summaryArea;
    // area where the logs and log summaries will be displayed
    private DailyLogger dailyLogger;
    // Stores daily logs as date that will be used in the log entries

    @Override
    public void start(Stage primaryStage) {
        dailyLogger = new DailyLogger();
        // we're using TreeMaps to keep the logs sorted by date

        // Main Layout using TabPane so that we can have 2 tabs
        // first tab will be the home page and the second is the logs page
        TabPane tabPane = new TabPane();

        // Home Tab
        Tab homeTab = new Tab("Home");
        homeTab.setClosable(false);
        homeTab.setContent(createHomeLayout());

        // Logs Tab
        Tab logsTab = new Tab("Logs");
        logsTab.setClosable(false);
        logsTab.setContent(createLogsLayout());

        tabPane.getTabs().addAll(homeTab, logsTab);

        // add the tabs to the scene and stage then set them
        Scene scene = new Scene(tabPane, 700, 500);
        primaryStage.setTitle("Meal Planner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createHomeLayout() {
        // home layout using VBox that will house everything in the home tab
        // then create space between the borders
        VBox homeLayout = new VBox(10);
        homeLayout.setPadding(new Insets(10));

        // we make the box to house the input for the meal name
        // it will be displayed when user is not hovering on the box
        HBox mealInput = new HBox(10);
        mealNameField = new TextField();
        mealNameField.setPromptText("Meal Name");

        // make a text field to house the user input for calories
        // "Calories" will be displayed when user is not hovering on the box
        calorieField = new TextField();
        calorieField.setPromptText("Calories");

        // make a text field to house the user input for protein
        // "Protein (g)" will be displayed when user is not hovering on the box
        proteinField = new TextField();
        proteinField.setPromptText("Protein (g)");

        // make a text field to house the user input for water
        // "Water Intake (L)" will be displayed when user is not hovering on the box
        waterIntakeField = new TextField();
        waterIntakeField.setPromptText("Water Intake (L)");

        // put them all in the mealInput HBox
        mealInput.getChildren().addAll(mealNameField, calorieField, proteinField, waterIntakeField);

        // make a button for users to click that'll log the meal they've inputted
        Button logMealButton = new Button("Log Meal");
        logMealButton.setOnAction(e -> logMeal());

        // make a button for users to save the meals that they've logged
        // the saved logs can then be loaded in later in the other tab
        Button saveLogButton = new Button("Save Log");
        saveLogButton.setOnAction(e -> saveLogToFile());

        // add both buttons to a HBox named buttonBox so it's easier to group
        HBox buttonBox = new HBox(10, logMealButton, saveLogButton);

        // summary area that'll detail all the nutrients consumed during that day
        summaryArea = new TextArea();
        summaryArea.setEditable(false);
        summaryArea.setPromptText("Daily Summary");

        // add all the grouped variables to the HomeLayout VBox
        homeLayout.getChildren().addAll(mealInput, buttonBox, summaryArea);
        return homeLayout;
    }

    private VBox createLogsLayout() {
        // logs layout using VBox that will house everything in the logs tab
        // then create space between the borders
        VBox logsLayout = new VBox(10);
        logsLayout.setPadding(new Insets(10));

        // summary area that'll detail all the nutrients consumed thus far
        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPromptText("Logged Meals");

        // create a button to load the logs that have been previously saved
        Button loadLogButton = new Button("Load Log");
        loadLogButton.setOnAction(e -> loadLogFromFile());

        // add them all to the logsLayout VBox
        logsLayout.getChildren().addAll(logArea, loadLogButton);
        return logsLayout;
    }

    private void logMeal() {
        // set the variables here so that they can get the user inputted
        // values for each respective variable
        String mealName = mealNameField.getText();
        String calories = calorieField.getText();
        String protein = proteinField.getText();
        String waterIntake = waterIntakeField.getText();

        // do nothing if any field is empty (debugging)
        if (mealName.isEmpty() || calories.isEmpty() || protein.isEmpty() || waterIntake.isEmpty()) {
            return;
        }

        // get current date so it can be documented
        // Compile meal details into a single log entry
        // IntelliJ suggested I don't use StringBuilder and just use String so I did just that for this case
        String currentDate = getCurrentDate();
        String logEntry = mealName + " - Calories: " + calories + " kcal, Protein: " + protein + " g, Water: " + waterIntake + "L";
        dailyLogger.log(currentDate, logEntry);

        // Update the summary area with all meals logged for today
        summaryArea.setText("Meals logged for today:\n" + String.join("\n", dailyLogger.getLogsForDate(currentDate)));

        // Clear input fields
        mealNameField.clear();
        calorieField.clear();
        proteinField.clear();
        waterIntakeField.clear();
    }

    private void saveLogToFile() {
        // a method to save whatever input is logged into a .txt file so that it can be loaded later
        try {
            dailyLogger.saveToFile("meals.txt");
            System.out.println("Logs saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving log to file: " + e.getMessage());
        }
    }

    private void loadLogFromFile() {
        // a method to load the saved logs
        try {
            dailyLogger.loadFromFile("meals.txt");
            logArea.setText(dailyLogger.getAllLogs());
            System.out.println("Logs loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading log from file: " + e.getMessage());
        }
    }

    // getter for current date
    private String getCurrentDate() {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        return date.format(new Date());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
