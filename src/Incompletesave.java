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

public class Incompletesave {
   private String name;
   private int QuestionsCorrect;
   private int timetaken;
   private int lives;
   private int roomcompleted;
   private ArrayList<String> names = new ArrayList<String>();
   private boolean flag;
   
   public Incompletesave(Player player1) {
	this.name = player1.getName();
	this.QuestionsCorrect = player1.getQuestionsAnswered();
	this.timetaken = player1.getTotaltime();
	this.lives = player1.getLives();
	this.roomcompleted = player1.getRoom();
	
	   
   }
   public String toString() {
		return name + "," + roomcompleted + "," + lives + "," + QuestionsCorrect +","+timetaken;
	}

   public static void removeDataFromCSV(String name) throws CsvValidationException {
       List<String[]> filteredData = new ArrayList<>();
       
       // Step 1: Read and filter data
       try (CSVReader reader = new CSVReader(new FileReader("./incomplete.csv"))) {
           String[] nextLine;
           while ((nextLine = reader.readNext()) != null) {
               if (nextLine[0].split(",").length>0 && !nextLine[0].split(",")[0].equalsIgnoreCase(name)) {
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
   
   public static void exportPlayerToCSV(String toString, String filePath) {
	   
	   try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, true))) {
           String[] playerData = {toString};
           System.out.println(playerData);
           writer.writeNext(playerData);
           System.out.println("Player data exported to CSV successfully.");
       } catch (IOException e) {
           System.err.println("An error occurred while writing to CSV: " + e.getMessage());
       }
   }
}