/* A class that simulates a calorie counter, it implements the NutrientCounter interface
 * @author Jonathan Setiawan (26002404663)
 * @date Dec 15, 2024
 */

public class CalorieCounter implements NutrientCounter {
    // field to tally up the amount of calories consumed during a given day
    protected int totalCalories;

    // methods to add, get total, and reset the value of totalCalories
    // all these methods here are overriding the methods from the interface
    @Override
    public void addValue(int value) {
        totalCalories += value;
    }

    @Override
    public int getTotal() {
        return totalCalories;
    }

    @Override
    public void reset() {
        totalCalories = 0;
    }
}

