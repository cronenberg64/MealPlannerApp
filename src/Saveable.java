/* An interface that simulates a saveable characteristic
 * this will be the base for all the "counter" classes
 * @author Jonathan Setiawan (26002404663)
 * @date Jan 18, 2025
 */

package course.programminglanguageproject;

import java.io.IOException;

interface Saveable {
    // saves the data to a file
    void saveToFile(String fileName) throws IOException;
}

