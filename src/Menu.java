/* A class that simulates a menu that is modified by the user
 * @author Jonathan Setiawan (26002404663)
 * @date Dec 15, 2024
 */

import java.util.ArrayList;

public class Menu {
    // fields, list for keeping track of what is in the menu
    private ArrayList<Food> foodMenu;

    // basic constructor
    public Menu() {
        foodMenu = new ArrayList<>();
    }

    // method to add food to the menu
    public void addFood(Food food) {
        foodMenu.add(food);
        System.out.println("Added to menu: " + food);
    }

    // method to get the menu list
    public ArrayList<Food> getFoodMenu() {
        return foodMenu;
    }

    // method to display all that is in the menu list
    public void displayMenu() {
        System.out.println("Available Menu Items:");
        for (int i = 0; i < foodMenu.size(); i++) {
            System.out.println((i + 1) + ". " + foodMenu.get(i));
        }
    }

    // method to get a certain food item from the menu
    public Food getFoodByIndex(int index) {
        if (index >= 0 && index < foodMenu.size()) {
            return foodMenu.get(index);
        }
        return null;
    }
}
