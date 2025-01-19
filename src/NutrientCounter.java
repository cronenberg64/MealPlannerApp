/* An interface that simulates a counter for nutrients
 * @author Jonathan Setiawan (26002404663)
 * @date Dec 15, 2024
 */

public interface NutrientCounter {
    void addValue(int value);
    // adds whatever value is given

    int getTotal();
    // gets total value of whatever is given

    void reset();
    // resets the value of whatever is given
}
