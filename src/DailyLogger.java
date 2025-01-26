/* A class that simulates a daily logger to house all the meals that have been logged
 * @author Jonathan Setiawan
 * @date Jan 18, 2025
 */

package course.programminglanguageproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class DailyLogger extends Logger implements Saveable {
    // A class that extends Logger and implements its abstract methods.
    // Adds a log entry under the specified date
    // if there are no entries for the date then it creates a new entry
    private final TreeMap<String, List<String>> logs;

    // basic constructor
    public DailyLogger() {
        logs = new TreeMap<>();
    }

    public void log(String date, String entry) {
        // a method to add a log for a specific date
        logs.computeIfAbsent(date, k -> new ArrayList<>()).add(entry);
    }

    public List<String> getLogsForDate(String date) {
        // a method that gets the logs for a specified date
        return logs.getOrDefault(date, new ArrayList<>());
    }

    public String getAllLogs() {
        // a method that gets all logs and compiles it all into one StringBuilder
        StringBuilder allLogs = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : logs.entrySet()) {
            for (String log : entry.getValue()) {
                allLogs.append(entry.getKey()).append(": ").append(log).append("\n");
            }
        }
        return allLogs.toString();
    }

    // writes the logs data to the specified file
    @Override
    public void saveToFile(String fileName) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(getAllLogs());
        }
    }

    // a method that loads the data from the specified file
    // debugged so that when users click load log, it doesn't dupe
    // and so that when they update the logs from the home, the logs tab will
    // show the updated version of the log instead of stacking it again
    public void loadFromFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ", 2);
                if (parts.length == 2) {
                    String date = parts[0];
                    String entry = parts[1];

                    // Check for duplicates before adding
                    if (!getLogsForDate(date).contains(entry)) {
                        log(date, entry);
                    }
                }
            }
        }
    }
}