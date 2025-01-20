/* A class that simulates a class to log meals
 * @author Jonathan Setiawan
 * @date Jan 18, 2025
 */

package course.programminglanguageproject;

// github copilot suggested using maps and treemaps
// instead of sortedList so that sorting is easier and I followed
// I didn't know what it was until I read week 13's slides, maps are a type of collections
// so I think it's okay to implement it since it is going to be more conveient

import java.util.Map;
import java.util.TreeMap;

public abstract class Logger {
    // fields, the map that will store the logs
    protected Map<String, StringBuilder> logs;

    // basic constructor
    public Logger() {
        this.logs = new TreeMap<>();
    }

    // Abstract method to log data
    public abstract void log(String key, String value);

    // Method to get all logs as a formatted string
    public String getAllLogs() {
        StringBuilder allLogs = new StringBuilder();
        for (Map.Entry<String, StringBuilder> entry : logs.entrySet()) {
            allLogs.append("Date: ").append(entry.getKey()).append("\n");
            allLogs.append(entry.getValue()).append("\n");
        }
        return allLogs.toString();
    }
}