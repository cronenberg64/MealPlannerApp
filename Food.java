/* A class that simulates a food class, it is used as the main superclass
 * @author Jonathan Setiawan (26002404663)
 * @date Dec 15, 2024
 */

public class Food {
    // initializing fields name, calories, protein, and serving size
    private String name;
    private int calories;
    private int protein;

    // basic constructor
    public Food(String name, int calories, int protein) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
    }

    // get the getters
    public String getName(){
        return name;
    }

    public int getCalories(){
        return calories;
    }

    public int getProtein(){
        return protein;
    }

    // override the toString method to return all the macros of the food
    @Override
    public String toString() {
        return name + " | Calories: " + calories + " | Protein: " + protein + "g";
    }
}
