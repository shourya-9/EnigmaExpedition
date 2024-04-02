import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import com.opencsv.CSVReader;

import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Incompletesave} class provides methods to handle saving and removing player data from a CSV file.
 * It includes functionality to construct player data from a {@link Player} object, remove data from a CSV file,
 * and export player data to a CSV file.
 */
public class Incompletesave {
   private String name;
   private int QuestionsCorrect;
   private int timetaken;
   private int lives;
   private int roomcompleted;
   private ArrayList<String> names = new ArrayList<String>();
   private boolean flag;
   
   /**
    * Constructs an {@code Incompletesave} object using player data from a {@link Player} object.
    *
    * @param player1 The {@link Player} object from which to extract player data.
    */
   public Incompletesave(Player player1) {
        this.name = player1.getName();
        this.QuestionsCorrect = player1.getQuestionsAnswered();
        this.timetaken = player1.getTotaltime();
        this.lives = player1.getLives();
        this.roomcompleted = player1.getRoom();
   }
   
   /**
    * Generates a string representation of the player's data.
    *
    * @return A string containing the player's data in CSV format.
    */
   public String toString() {
        return name + "," + roomcompleted + "," + lives + "," + QuestionsCorrect +","+timetaken;
   }

   /**
    * Removes the player's data from a CSV file.
    *
    * @param name The name of the player whose data is to be removed from the CSV file.
    * @throws CsvValidationException If an error occurs during CSV validation.
    */
   public static void removeDataFromCSV(String name) throws CsvValidationException {
       List<String[]> filteredData = new ArrayList<>();
       
       // Step 1: Read and filter data
       try (CSVReader reader = new CSVReader(new FileReader("./incomplete.csv"))) {
           String[] nextLine;
           
           while ((nextLine = reader.readNext()) != null) {
               if (nextLine[0].split(",").length>0 && !nextLine[0].split(",")[0].equalsIgnoreCase(name) && !nextLine[0].split(",")[0].equalsIgnoreCase("gamemod")) {
                   filteredData.add(nextLine);
               }
           }
       } catch (IOException e) {
           System.err.println("An error occurred while reading the CSV: " + e.getMessage());
           return;
       }
       
       // Step 2: Write the filtered data back
       try (CSVWriter writer = new CSVWriter(new FileWriter("./incomplete.csv"))) {
           writer.writeAll(filteredData);
       } catch (IOException e) {
           System.err.println("An error occurred while writing to CSV: " + e.getMessage());
       }
   }
   
   /**
    * Exports the player's data to a CSV file.
    *
    * @param toString The string representation of the player's data in CSV format.
    * @param filePath The file path where the CSV file should be exported.
    */
   public static void exportPlayerToCSV(String toString, String filePath) {
       try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, true))) {
           String[] playerData = {toString};
           if (!playerData[0].split(",")[0].equalsIgnoreCase("gameMod")) {
               writer.writeNext(playerData);
               System.out.println("Player data exported to CSV successfully.");
           }
       } catch (IOException e) {
           System.err.println("An error occurred while writing to CSV: " + e.getMessage());
       }
   }
}
