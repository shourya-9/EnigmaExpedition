import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * The loadGame class represents a JPanel used to load a player's game data from a CSV file.
 * It searches for the specified player name in the CSV file and loads their game data if found.
 * Depending on the room the player is in, it redirects to the corresponding room panel.
 */
public class loadGame extends JPanel {
	private String playerData = null;
	private Start startFrame;
	private JFrame frame;
	/**
     * Constructs a loadGame panel with the specified player name and Start frame.
     *
     * @param playername The name of the player whose data is to be loaded.
     * @param startFrame The Start frame of the game.
     */
	public loadGame(String playername,Start startFrame) {
		
	    try (CSVReader reader = new CSVReader(new FileReader("./incomplete.csv"))) {
	        List<String[]> allEntries = reader.readAll();
	        
	        
            for (String[] entry : allEntries) {
            	//System.out.println(entry[0]);
                if ((entry[0].split(",")).length > 0 && (entry[0].split(","))[0].equalsIgnoreCase(playername)) {
                    playerData = String.join(",", entry);
                    String name = (playerData.split(","))[0];
                    int room =  Integer.parseInt((playerData.split(","))[1]);
                    int lives =  Integer.parseInt((playerData.split(","))[2]);
                    int questions =  Integer.parseInt((playerData.split(","))[3]);
                    int timeTaken =  Integer.parseInt((playerData.split(","))[4]);
                    Player player1 = new Player(name, lives);
                    player1.applyQuestionsAnswered(questions);
                    player1.setTotaltime(timeTaken);
                    
                    if (room==0) {
                    	player1.setRoom(0);
                    	player1.applyQuestionsAnswered(0);
                    	Room1Panel room1Panel = new Room1Panel(startFrame, startFrame, player1); // Initialize Room2Panel
                        startFrame.setContentPane(room1Panel); // Set Room2Panel as the current content pane
                        startFrame.revalidate();
                        startFrame.repaint();	
                    }
                    else if (room==1) {
                    	player1.setRoom(1);
                    	player1.applyQuestionsAnswered(5);
                    	Room2Panel room2Panel = new Room2Panel(startFrame, startFrame, player1); // Initialize Room2Panel
                        startFrame.setContentPane(room2Panel); // Set Room2Panel as the current content pane
                        startFrame.revalidate();
                        startFrame.repaint();	
                     // Stop searching once the player is found
                    }
                    else if (room==2) {
                    	player1.setRoom(2);
                    	player1.applyQuestionsAnswered(10);
                    	Room3Panel room3Panel = new Room3Panel(startFrame, startFrame, player1); // Initialize Room2Panel
                        startFrame.setContentPane(room3Panel); // Set Room2Panel as the current content pane
                        startFrame.revalidate();
                        startFrame.repaint();	
                    }
                    else if (room==3) {
                    	player1.setRoom(3);
                    	player1.applyQuestionsAnswered(15);
                    	Room4Panel room4Panel = new Room4Panel(startFrame, startFrame, player1); // Initialize Room2Panel
                        startFrame.setContentPane(room4Panel); // Set Room2Panel as the current content pane
                        startFrame.revalidate();
                        startFrame.repaint();	
                     // Stop searching once the player is found
                    }
                    else if (room==4) {
                    	player1.setRoom(4);
                    	player1.applyQuestionsAnswered(20);
                    	Room5Panel room5Panel = new Room5Panel(startFrame, startFrame, player1); // Initialize Room2Panel
                        startFrame.setContentPane(room5Panel); // Set Room2Panel as the current content pane
                        startFrame.revalidate();
                        startFrame.repaint();	
                     // Stop searching once the player is found
                    }
                    break;    
                }
                
                
            }
            if (playerData == null) {
                	JOptionPane.showMessageDialog(null, "Error: Player name not found in the game data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException | CsvException e) {
	        System.err.println("An error occurred while reading the CSV file: " + e.getMessage());
	    } catch (NumberFormatException e) {
	        System.err.println("An error occurred while parsing scores: " + e.getMessage());
	    }
	}
	public String getPlayerData() {
        return playerData;
    }
}
