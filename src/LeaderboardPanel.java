import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
/**
 * The LeaderboardPanel class represents a JPanel that displays a leaderboard with player scores and times.
 * It loads data from a CSV file and presents it in a table format. It also includes a background image and a "BACK" button.
 */
public class LeaderboardPanel extends JPanel {
    private DefaultTableModel model;
    private Image backgroundImage;
    /**
     * Constructs a LeaderboardPanel with the specified Start frame and CSV file path.
     *
     * @param startFrame   The Start frame of the application.
     * @param csvFilePath  The file path of the CSV file containing leaderboard data.
     */
    public LeaderboardPanel(Start startFrame, String csvFilePath) {
        super(new BorderLayout());
        setSize(1317, 768);
        setLayout(new BorderLayout());

        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("photos/bg5.jpeg")); // Replace with your image path
        } catch (IOException e) {
            e.printStackTrace();
            backgroundImage = null;
        }

        // Header panel with "BACK" button and "LEADERBOARD" title
        JPanel headerPanel = new JPanel(new BorderLayout());
        JButton backButton = new JButton("BACK");
        OutlineLabel leaderboardLabel = new OutlineLabel("LEADERBOARD", SwingConstants.CENTER);
        leaderboardLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JLabel nullLabel = new JLabel("                   ");
        leaderboardLabel.setForeground(Color.WHITE);
        headerPanel.add(backButton, BorderLayout.WEST);
        headerPanel.add(leaderboardLabel, BorderLayout.CENTER);
        headerPanel.add(nullLabel, BorderLayout.EAST);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        headerPanel.setOpaque(false); // Make header panel non-opaque

        this.model = new DefaultTableModel(new String[]{"Rank", "Player", "Score", "Time"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // This makes the table non-editable
            }
        };

        JTable leaderboardTable = new JTable(this.model);
        leaderboardTable.setRowHeight(30);
        leaderboardTable.setFont(new Font("Arial", Font.PLAIN, 18));
        leaderboardTable.setShowGrid(false); // Hide the grid lines if you want
        leaderboardTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        leaderboardTable.setFillsViewportHeight(true);
        leaderboardTable.setOpaque(false); // Make the table non-opaque
        ((DefaultTableCellRenderer)leaderboardTable.getDefaultRenderer(Object.class)).setOpaque(false);

        // Center text in cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setOpaque(false); // Make the cell renderers non-opaque
        leaderboardTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        leaderboardTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        leaderboardTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        // Customize cell renderer for both header and cells to set text color to white
        DefaultTableCellRenderer whiteRenderer = new DefaultTableCellRenderer();
        whiteRenderer.setHorizontalAlignment(JLabel.CENTER);
        whiteRenderer.setForeground(Color.WHITE); // Set text color to white
        whiteRenderer.setOpaque(false);

        // Apply this renderer to all columns
        for (int i = 0; i < leaderboardTable.getColumnCount(); i++) {
            leaderboardTable.getColumnModel().getColumn(i).setCellRenderer(whiteRenderer);
        }

        // Apply the same renderer to the header
        JTableHeader tableHeader = leaderboardTable.getTableHeader();
        tableHeader.setOpaque(false);
        tableHeader.setDefaultRenderer(centerRenderer);
        tableHeader.setReorderingAllowed(false); // Optional: To prevent reordering

        // Scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        scrollPane.setOpaque(false); // Make the JScrollPane non-opaque
        scrollPane.getViewport().setOpaque(false); // Make the viewport non-opaque

        // Add components to the frame
        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        loadCsvData(csvFilePath);

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
     * Loads data from a CSV file into the leaderboard table.
     *
     * @param csvFilePath The file path of the CSV file containing leaderboard data.
     */
	private void loadCsvData(String csvFilePath) {
	    try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
	        List<String[]> allEntries = reader.readAll();
	        
	        // Remove the header row if your CSV has one
	        if (!allEntries.isEmpty() && allEntries.get(0)[0].equals("Player")) {
	            allEntries.remove(0);
	        }

	        // Sort entries by score in descending order
	        allEntries.sort((entry1, entry2) -> {
	            // Initialize scores
	            int score1 = 0;
	            int score2 = 0;
	           
	            
	            // Parse scores safely
	            if (entry1[0].length() > 1) score1 = Integer.parseInt((entry1[0].split(","))[1]);
	            if (entry2[0].length() > 1) score2 = Integer.parseInt((entry2[0].split(","))[1]);
	            
	            return Integer.compare(score2, score1);
	        });
	        

	        // Clear the existing model before adding new data
	        model.setRowCount(0);

	        // Add the top 20 or less, depending on available data
	        for (int i = 0; i < Math.min(10, allEntries.size()); i++) {
	            String[] entry = allEntries.get(i);
	            if (entry[0].split(",").length == 3) { // Ensure there are enough parts in the row
	                String name = (entry[0].split(","))[0];
	                String score = (entry[0].split(","))[1];
	                String timeInSeconds = (entry[0].split(","))[2];
	                String timeFormatted = formatTime(timeInSeconds);
	                if(!name.equals("gameMod")) {
	                	model.addRow(new Object[]{i + 1, name, score, timeFormatted});
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
     * Formats time from seconds to "minutes : seconds" format.
     *
     * @param timeInSeconds The time in seconds to be formatted.
     * @return The formatted time string.
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
     * Overrides the paintComponent method to paint the background image.
     *
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
     * The main method to start the LeaderboardPanel as a standalone application.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LeaderboardPanel(null, "./score.csv").setVisible(true));
    }
}

