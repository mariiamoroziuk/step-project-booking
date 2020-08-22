package airport.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AirportLogger {
    public static void info (String message) {
        saveLogToFile(createLogMessage(message, "DEBUG"));
    }

    public static void error (String message) {
        saveLogToFile(createLogMessage(message, "ERROR"));
    }

    private static String createLogMessage(String message, String level) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return dateFormat.format(new Date())+ " [" + level + "] " + message;
    }

    private static void saveLogToFile(String logMessage) {
        String filename = "application.log";
        try {
            FileWriter writer = new FileWriter(filename, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write("\n"+logMessage);
            bufferWriter.close();
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }

}
