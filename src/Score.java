import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Score {
   private String name;
   private int QuestionsCorrect;
   private int timetaken;
   private int lives;
   private int score;
   private int roomsCompleted;
   
   public Score(Player player1) {
	this.name = player1.getName();
	this.QuestionsCorrect = player1.getQuestionsAnswered();
	this.timetaken = player1.getTotaltime();
	this.lives = player1.getLives();
	this.roomsCompleted = player1.getRoom();
	
	   
   }
   public int computeScore() {
	   float tt = timetaken;
	   float lv = lives;
	   float qn = QuestionsCorrect;
	   float cas =  ((qn / 25) * 70);
	   float time_score =  ((1 - ((tt - 5) / 50)) * 20);	
	  
	   float lrs = (lv / 3) * 10;
	   score =  (int) (cas + time_score + lrs);
	   System.out.println(time_score +  " " + lrs);
	   //score = (int) (((QuestionsCorrect - (0.5*timetaken) +(0.25*lives))/25.75)*100);
	   return score;	   
   }
   public String toString() {
		return name + "," + this.computeScore() + "," + timetaken;
	}
   public String toString2() {
		return name + "," + this.computeScore() + "," + timetaken + "," + roomsCompleted + "," + lives;
	}
   public static void exportPlayerToCSV(String toString, String filePath) {
       try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, true))) {
    	   String[] playerData = {toString};
    	   if (!playerData[0].split(",")[0].equalsIgnoreCase("gameMod")) {
        	   writer.writeNext(playerData);
    	   }
           //String[] playerData = {toString};
           System.out.println(playerData);
           //writer.writeNext(playerData);
           System.out.println("Player data exported to CSV successfully.");
       } catch (IOException e) {
           System.err.println("An error occurred while writing to CSV: " + e.getMessage());
       }
   }
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
}

