import javax.swing.*;
import java.awt.*;
/**
 * The {@code s} class provides a simple GUI framework for the "Enigma Expedition: Path to Enlightenment" game.
 * This class includes a method to create and display the main game window, including the title label and buttons
 * for game navigation such as "Play", "Load", "How to Play", and "Feedback".
 */

public class s {
	/**
	 * Initializes and displays the GUI for the "Enigma Expedition: Path to Enlightenment" game.
	 * 
	 * This method sets up the main window frame, title label, button panel, and individual buttons
	 * for the game's user interface. It defines the layout, size, color, and visibility of each
	 * component and adds them to the frame.
	 * 
	 * The GUI includes:
	 * - A title label with the game's name, set in a large, bold font and centered at the top.
	 * - A main panel with a pink background that holds four buttons: "Play", "Load", "How to Play",
	 *   and "Feedback", each with a yellow background and sized uniformly.
	 * 
	 * The frame is packed to fit the preferred size of its components and made visible to the user.
	 * 
	 * Note: This method should be invoked on the Event Dispatch Thread (EDT) to ensure thread safety
	 * when updating the GUI components.
	 */
    public static void createAndShowGUI(){

        JFrame frame = new JFrame("Enigma Expedition: Path to Enlightenment");

        JLabel title = new JLabel("ENIGMA EXPEDITION: PATH TO ENLIGHTENMENT", JLabel.CENTER);
        Font heading = new Font("Times New Roman", Font.BOLD, 24);
        title.setFont(heading);
        title.setPreferredSize(new Dimension(1000, 100));
        title.setOpaque(true);
        title.setBackground(Color.BLACK);
        frame.getContentPane().add(title, BorderLayout.PAGE_START);

        JPanel pane = new JPanel(new FlowLayout());
        pane.setPreferredSize(new Dimension(300, 400));
        pane.setBackground(Color.PINK);
        pane.setOpaque(true);
        frame.getContentPane().add(pane, BorderLayout.CENTER);

        JButton Play = new JButton("Play");
        JButton Load = new JButton("Load");
        JButton htp = new JButton("How to Play");
        JButton fb = new JButton("Feedback");

        Play.setPreferredSize(new Dimension(100, 50));
        Play.setBackground(Color.YELLOW);

        Load.setPreferredSize(new Dimension(100, 50));
        Load.setBackground(Color.YELLOW);

        htp.setPreferredSize(new Dimension(100, 50));
        htp.setBackground(Color.YELLOW);

        fb.setPreferredSize(new Dimension(100, 50));
        fb.setBackground(Color.YELLOW);

        pane.add(Play);
        pane.add(Load);
        pane.add(htp);
        pane.add(fb);

        frame.pack();
        frame.setVisible(true);


    }
    /**
     * 
     * The method uses `SwingUtilities.invokeLater` to ensure that the GUI creation is performed in
     * the Event Dispatch Thread (EDT), which is the proper way to start a Swing application.
     * 
     * @param args Command-line arguments passed to the application (not used).
     *
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
