/* A class that simulates a protein counter, it implements the NutrientCounter interface
 * @author Jonathan Setiawan (26002404663)
 * @date Dec 15, 2024
 */

public class ProteinCounter implements NutrientCounter {
    // field to tally up the amount of protein consumed during a given day
    protected int totalProtein;

    // methods to add, get total, and reset the value of totalProtein
    // all these methods here are overriding the methods from the interface
    @Override
    public void addValue(int value) {
        totalProtein += value;
    }

    @Override
    public int getTotal() {
        return totalProtein;
    }

    @Override
    public void reset() {
        totalProtein = 0;
    }
}
