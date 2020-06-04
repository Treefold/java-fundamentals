package MyLog;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

// singleton
public class Log {
    private static Log log = new Log("Logging.csv");
    private FileWriter csvWriter;
    private Log() {}
    private Log(String FileName) {
        try {
            csvWriter = new FileWriter(FileName, true);
        } catch (IOException e) {
            // FileNotFoundException: no problem (it will be created)
            e.printStackTrace();
        }
    }
    public static void logData (String data) {
        try {
            log.csvWriter.append((new Timestamp(System.currentTimeMillis())).toString());
            log.csvWriter.append("," + Thread.currentThread().getName());
            log.csvWriter.append("," + data);
            log.csvWriter.append("\n");
            log.csvWriter.flush();

        } catch (IOException e) {
            System.out.println("Error while logging: " + data);
            e.printStackTrace();
        }
    }
}
