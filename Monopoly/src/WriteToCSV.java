import com.opencsv.CSVWriter; 
import java.io.FileWriter;
import java.io.IOException;

public class CSVUtility {
    /**
     * Writes data to a CSV file.
     * filePath -  The path of the CSV file to write to.
     * data - A 1D array of integers to represent score/money/etc values.
     * "data" can be changed to fit our needs. Ex: Write to CSV array of player scores int[] etc.
     */
    public static void appendIntArrayToCSV(String filePath, int[] data) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, true))) { // 'true' for appending
            // Convert the integer array to a String array for writing to CSV
            String[] stringData = new String[data.length];
            for (int i = 0; i < data.length; i++) {
                stringData[i] = String.valueOf(data[i]);
            }
            // Append the converted row to the CSV
            writer.writeNext(stringData);
            System.out.println("Integer array successfully appended to CSV at: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing array to CSV: " + e.getMessage());
        }
    }
}

