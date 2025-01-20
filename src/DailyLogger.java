/* A class that simulates a daily logger to house all the meals that have been logged
 * @author Jonathan Setiawan
 * @date Jan 18, 2025
 */

package course.programminglanguageproject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

class DailyLogger extends Logger implements Saveable {
    // A class that extends Logger and implements its abstract methods.
    // Adds a log entry under the specified date
    // if there are no entries for the date then it creates a new entry
    @Override
    public void log(String date, String value) {
        logs.putIfAbsent(date, new StringBuilder());
        logs.get(date).append(value).append("\n");
    }

    // writes the logs data to the specified file
    @Override
    public void saveToFile(String fileName) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            for (Map.Entry<String, StringBuilder> entry : logs.entrySet()) {
                fileWriter.write("Date: " + entry.getKey() + "\n");
                fileWriter.write(entry.getValue().toString() + "\n");
            }
        }
    }
}