/* A class that simulates the amount of water taken during that day
 * @author Jonathan Setiawan (26002404663)
 * @date Dec 15, 2024
 */

public class WaterIntake {
    // field for tallying the total amount of liters that was drunk that day
    private int totalLiters;

    // method for adding water to the totalLiters counter
    public void addWater(int liters) {
        totalLiters += liters;
    }

    // get total amount of liters drunk that day
    public int getTotalLiters() {
        return totalLiters;
    }

    // method to reset the counter since this is a daily counter
    public void reset() {
        totalLiters = 0;
    }
}
