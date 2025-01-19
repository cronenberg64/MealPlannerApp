import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MealPlannerGUI {

    private JFrame frame;
    private JPanel homePanel, logsPanel;
    private JTextField mealNameField, calorieField, proteinField, waterIntakeField;
    private JTextArea previousMealsArea, logsArea;
    private List<Food> mealHistory;
    private DayTracker dayTracker;

    public MealPlannerGUI() {
        frame = new JFrame("Nutrition Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        mealHistory = new ArrayList<>();
        dayTracker = new DayTracker();

        createHomePanel();
        createLogsPanel();

        frame.add(homePanel);
        frame.setVisible(true);
    }

    private void createHomePanel() {
        homePanel = new JPanel();
        homePanel.setLayout(new BorderLayout());

        // Top: Add meal section
        JPanel addMealPanel = new JPanel();
        addMealPanel.setLayout(new GridLayout(5, 2));

        mealNameField = new JTextField();
        calorieField = new JTextField();
        proteinField = new JTextField();
        waterIntakeField = new JTextField();

        addMealPanel.add(new JLabel("Meal Name:"));
        addMealPanel.add(mealNameField);
        addMealPanel.add(new JLabel("Calories:"));
        addMealPanel.add(calorieField);
        addMealPanel.add(new JLabel("Protein (g):"));
        addMealPanel.add(proteinField);
        addMealPanel.add(new JLabel("Water Intake (L):"));
        addMealPanel.add(waterIntakeField);

        JButton addMealButton = new JButton("Log Meal");
        addMealButton.addActionListener(e -> logMeal());
        addMealPanel.add(addMealButton);

        homePanel.add(addMealPanel, BorderLayout.NORTH);

        // Center: Previous meals area
        previousMealsArea = new JTextArea(10, 30);
        previousMealsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(previousMealsArea);
        homePanel.add(scrollPane, BorderLayout.CENTER);

        // Bottom: Navigation
        JButton goToLogsButton = new JButton("View Logs");
        goToLogsButton.addActionListener(e -> switchToLogsPanel());

        JPanel navPanel = new JPanel();
        navPanel.add(goToLogsButton);
        homePanel.add(navPanel, BorderLayout.SOUTH);
    }

    private void createLogsPanel() {
        logsPanel = new JPanel();
        logsPanel.setLayout(new BorderLayout());

        // Logs view area
        logsArea = new JTextArea(15, 40);
        logsArea.setEditable(false);
        JScrollPane logsScrollPane = new JScrollPane(logsArea);
        logsPanel.add(logsScrollPane, BorderLayout.CENTER);

        // Navigation
        JButton backToHomeButton = new JButton("Back to Home");
        backToHomeButton.addActionListener(e -> switchToHomePanel());

        JPanel navPanel = new JPanel();
        navPanel.add(backToHomeButton);
        logsPanel.add(navPanel, BorderLayout.SOUTH);
    }

    private void logMeal() {
        String mealName = mealNameField.getText();
        String calorieText = calorieField.getText();
        String proteinText = proteinField.getText();
        String waterText = waterIntakeField.getText();

        if (mealName.isEmpty() || calorieText.isEmpty() || proteinText.isEmpty() || waterText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
            return;
        }

        try {
            int calories = Integer.parseInt(calorieText);
            int protein = Integer.parseInt(proteinText);
            double water = Double.parseDouble(waterText);

            Food meal = new Food(mealName, calories, protein);
            mealHistory.add(meal);
            dayTracker.addMeal(meal);
            dayTracker.addWater(water);

            updatePreviousMealsArea();
            JOptionPane.showMessageDialog(frame, "Meal logged successfully!");

            // Clear fields
            mealNameField.setText("");
            calorieField.setText("");
            proteinField.setText("");
            waterIntakeField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numeric values for calories, protein, and water intake.");
        }
    }

    private void updatePreviousMealsArea() {
        StringBuilder sb = new StringBuilder();
        for (Food meal : mealHistory) {
            sb.append(meal.toString()).append("\n");
        }
        previousMealsArea.setText(sb.toString());
    }

    private void switchToLogsPanel() {
        logsArea.setText(dayTracker.getLogSummary());
        frame.getContentPane().removeAll();
        frame.add(logsPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void switchToHomePanel() {
        frame.getContentPane().removeAll();
        frame.add(homePanel);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MealPlannerGUI::new);
    }
}

// Supporting DayTracker classes
class DayTracker {
    private List<Food> meals;
    private double totalWater;

    public DayTracker() {
        meals = new ArrayList<>();
        totalWater = 0;
    }

    public void addMeal(Food meal) {
        meals.add(meal);
    }

    public void addWater(double liters) {
        totalWater += liters;
    }

    public String getLogSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Daily Log:\n");
        for (Food meal : meals) {
            sb.append(meal.toString()).append("\n");
        }
        sb.append("Total Water Intake: ").append(totalWater).append(" L\n");
        return sb.toString();
    }
}
