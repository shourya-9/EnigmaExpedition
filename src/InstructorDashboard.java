import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * InstructorDashboard class represents the dashboard for instructors 
 * to view game statistics including player scores, time taken, and game progress.
 * This class extends JPanel to create a graphical user interface.
 */
public class InstructorDashboard extends JPanel {
	private DefaultTableModel model;
	private Image backgroundImage;
	/**
     * Constructor for creating an instance of the InstructorDashboard.
     * @param startFrame The instance of Start frame from where the dashboard is invoked.
     */
	public InstructorDashboard(Start startFrame) {
		super(new BorderLayout());
		// TODO Auto-generated constructor stub
		setSize(1317, 768);
        setLayout(new BorderLayout());
        
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("photos/bg5.jpeg")); // Replace with your image path
        } catch (IOException e) {
            e.printStackTrace();
            backgroundImage = null;
        }
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        JButton backButton = new JButton("BACK");
        JLabel leaderboardLabel = new JLabel("Game Statistics", SwingConstants.CENTER);
        leaderboardLabel.setFont(new Font("Arial", Font.BOLD, 24));
        leaderboardLabel.setForeground(Color.WHITE);
        headerPanel.add(backButton, BorderLayout.WEST);
        headerPanel.add(leaderboardLabel, BorderLayout.CENTER);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        headerPanel.setOpaque(false); // Make header panel non-opaque

        
        this.model = new DefaultTableModel(new String[]{"Index", "Player-name", "Current Score","Time Taken", "Current Room", "Lives Left", "Game Completed"}, 0) {
        	@Override
        	public boolean isCellEditable(int row, int column) {
                return false; // This makes the table non-editable
            }
        };
        
        JTable instructorTable = new JTable(this.model);
        instructorTable.setRowHeight(30);
        instructorTable.setFont(new Font("Arial", Font.PLAIN, 18));
        instructorTable.setShowGrid(false);
        instructorTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        instructorTable.setFillsViewportHeight(true);
        instructorTable.setOpaque(false);
        ((DefaultTableCellRenderer)instructorTable.getDefaultRenderer(Object.class)).setOpaque(false);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        instructorTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
        instructorTable.setFillsViewportHeight(true);
        
     // Create a whiteRenderer to set the text color to white
        DefaultTableCellRenderer whiteRenderer = new DefaultTableCellRenderer();
        whiteRenderer.setHorizontalAlignment(JLabel.CENTER);
        whiteRenderer.setForeground(Color.WHITE); // Set text color to white
        whiteRenderer.setOpaque(false); // Make sure the renderer is transparent

        // Set the whiteRenderer for each column in the table
        for (int i = 0; i < instructorTable.getColumnCount(); i++) {
            instructorTable.getColumnModel().getColumn(i).setCellRenderer(whiteRenderer);
        }

        JTableHeader tableHeader = instructorTable.getTableHeader();
        tableHeader.setOpaque(false);
        tableHeader.setDefaultRenderer(centerRenderer);
        tableHeader.setReorderingAllowed(false); // Optional: To prevent reordering

        // Scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(instructorTable);
        scrollPane.setOpaque(false); // Make the JScrollPane non-opaque
        scrollPane.getViewport().setOpaque(false); // Make the viewport non-opaque
        
        // Add components to the frame
        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        loadCsvData("./complete.csv");
        // Center the frame on the screen
        backButton.addActionListener(e -> {
            startFrame.showMainPanel(); // Switch back to the main panel
        });

		Action goBackAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startFrame.showMainPanel(); // The operation you want to perform
			}
		};

		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "goBack");
		this.getActionMap().put("goBack", goBackAction);
	}
	/**
     * Loads data from CSV files and populates the table with the extracted data.
     * @param csvFilePath The path to the CSV file containing complete game data.
     */
	private void loadCsvData(String csvFilePath) {
		try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
			CSVReader reader2 = new CSVReader(new FileReader("./incomplete.csv"));
			
	        List<String[]> allEntries = reader.readAll();
	        List<String[]> allEntries2 = reader2.readAll();
	        
	        // Remove the header row if your CSV has one
	        if (!allEntries.isEmpty() && allEntries.get(0)[0].equals("Player")) {
	            allEntries.remove(0);
	        }

	        
	    
	        // Clear the existing model before adding new data
	        model.setRowCount(0);

	        // Add the top 20 or less, depending on available data
	        for (int i = 0; i <allEntries.size(); i++) {
	            String[] entry = allEntries.get(i);
	            if (entry[0].split(",").length == 5) { // Ensure there are enough parts in the row
	                String name = (entry[0].split(","))[0];
	                String score = (entry[0].split(","))[1];
	                String timeInSeconds = (entry[0].split(","))[2];
	                String timeFormatted = formatTime(timeInSeconds);
	               
	                int room = Integer.parseInt((entry[0].split(","))[3])+1;
	                int lives = Integer.parseInt((entry[0].split(","))[4]);
	                model.addRow(new Object[]{i + 1, name, score, timeFormatted,room,lives,"Yes"});
	            }
	            
	        }
	       // ------------------------------------------------------------------------------
	        
	        if (!allEntries2.isEmpty() && allEntries2.get(0)[0].equals("Player")) {
	            allEntries2.remove(0);
	        }
	        
	        for (int i = 0; i <allEntries2.size(); i++) {
	            String[] entry2 = allEntries2.get(i);
	            if (entry2[0].split(",").length == 5) { // Ensure there are enough parts in the row
	                String name = (entry2[0].split(","))[0];
	                int room = Integer.parseInt((entry2[0].split(","))[1])+1;
	                int lives = Integer.parseInt((entry2[0].split(","))[2]);
	                String timeInSeconds = (entry2[0].split(","))[4];
	                String timeFormatted = formatTime(timeInSeconds);
	                int score = 0;
	                if (room==1) {
	                	score = (int) ((((0.25*lives))/25.75)*100);
	                }
	                else if (room==2) {
	                	score = (int) (((5 - (0.5*Integer.parseInt(timeInSeconds)) +(0.25*lives))/25.75)*100);
	                }
	                else if (room==3) {
	                	score = (int) (((10 - (0.5*Integer.parseInt(timeInSeconds)) +(0.25*lives))/25.75)*100);
	                }
	                else if (room==4) {
	                	score = (int) (((15 - (0.5*Integer.parseInt(timeInSeconds)) +(0.25*lives))/25.75)*100);
	                }
	                else if (room==5) {
	                	score = (int) (((16 - (0.5*Integer.parseInt(timeInSeconds)) +(0.25*lives))/25.75)*100);	                }
	                
	                if(!name.equals("gameMod")) {
	                	model.addRow(new Object[]{allEntries.size() + i + 1, name, score, timeFormatted,room,lives,"No"});
	                }
	            }
	        }

	    } catch (IOException | CsvException e) {
	        System.err.println("An error occurred while reading the CSV file: " + e.getMessage());
	    } catch (NumberFormatException e) {
	        System.err.println("An error occurred while parsing scores: " + e.getMessage());
	    }
	}
	/**
	 * Formats the given time in seconds into a human-readable format (minutes and seconds).
	 * If the given time is not a valid integer, returns "Invalid time".
	 * @param timeInSeconds The time in seconds to be formatted.
	 * @return A string representing the formatted time in "m s" format (e.g., "5 m 30 s").
	 * If the input is not a valid integer, returns "Invalid time".
	 */
	private String formatTime(String timeInSeconds) {
	    try {
	        int seconds = Integer.parseInt(timeInSeconds);
	        return String.format("%d m %d s", seconds / 60, seconds % 60);
	    } catch (NumberFormatException e) {
	        return "Invalid time";
	    }
	}
	/**
	 * Overrides the default paintComponent method to draw a background image, if available.
	 * This method is responsible for painting the background of the panel.
	 * @param g The Graphics context used for painting.
	 */
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
	/**
	 * The entry point of the application.
	 * Initializes the InstructorDashboard and makes it visible on the screen.
	 * @param args The command-line arguments passed to the application (not used in this case).
	 */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InstructorDashboard(null).setVisible(true));
    }
	
}