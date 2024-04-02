import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The NameLoad class represents a JPanel for loading player names in a game application.
 * It provides text fields and buttons for entering and confirming player names.
 * The entered player names are stored in an ArrayList of Player objects.
 * This panel is typically invoked from a Start frame and allows players to input their names.
 * Upon confirmation, the player names are processed for further actions within the game.
 */

public class NameLoad extends JPanel {
    private JTextField nameField;
    private JButton confirmButton,backButton;
    private static ArrayList<Player> players = new ArrayList<>();
    private JFrame frame;
    private Start startFrame;
    private Player player1;
    /**
     * Constructs a NameLoad panel with specified parameters.
     *
     * @param startFrame The Start frame that contains this panel.
     * @param frame      The JFrame where this panel is displayed.
     * @param mainPanel  The main content panel to be displayed.
     */
    public NameLoad(Start startFrame, JFrame frame, JPanel mainPanel) {
        this.frame = frame;
        setLayout(null);

        Font TitleFont = CustomFontLoader.getFont("photos/RichSpookyBold.ttf", 24f);

        // Text Field
        nameField = new JTextField(20);
        nameField.setFont(TitleFont);
        nameField.setForeground(Color.orange);
        nameField.setOpaque(false);
        nameField.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
        nameField.setBounds(350, 230, 300, 30); // Example bounds

        // Confirm Button
        confirmButton = new JButton("Confirm");
        confirmButton.setFont(TitleFont);
        confirmButton.setForeground(Color.ORANGE);
        confirmButton.setOpaque(false);
        confirmButton.setContentAreaFilled(false);
        confirmButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
        //confirmButton.setBorder(BorderFactory.createLineBorder(new Color(144, 238, 144), 1));
        confirmButton.setBounds(503, 265, 147, 30); // Adjust position and size as needed

        // Back Button
        backButton = new JButton("Back");
        backButton.setFont(TitleFont);
        backButton.setForeground(Color.ORANGE);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
        backButton.setBounds(350, 265, 147, 30); // Adjust position and size as needed
        backButton.addActionListener(e -> goBack());

        OutlineLabel nameLabel = new OutlineLabel("Enter Your Name:");
        nameLabel.setFont(TitleFont);
        nameLabel.setForeground(Color.ORANGE);
        nameLabel.setBounds(375, 200, 300, 30); // Adjust label position

        add(nameLabel);
        add(nameField);
        add(confirmButton);
        add(backButton);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = nameField.getText().trim();
                if (!playerName.isEmpty()) {
                	loadGame load = new loadGame(playerName,startFrame);
                	System.out.println(load.getPlayerData());

                	//player1 = new Player(playerName, 3); // Assuming constructor Player(String name, int someValue)
                    //players.add(player1);

                    // Switch back to the main panel or proceed to the next part of the game
                    //frame.setContentPane(room1Panel);
                    //frame.revalidate();
                    //frame.repaint();
                    //System.out.println("Player added: " + playerName); // For debug
                } else {
                    JOptionPane.showMessageDialog(frame, "Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    /**
     * Overrides the paintComponent method to draw the background image on the panel.
     *
     * @param g The Graphics context to paint on.
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image to fill the entire panel
        Image backgroundImage = new ImageIcon("photos/nameRoom.jpeg").getImage();
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
    /**
     * Action performed when the "Back" button is clicked. It switches back to the main panel.
     */
    private void goBack() {
        // Assuming 'Start' class has a method called 'showMainPanel()' to switch back to the main content panel
        // This will dispose of the current panel and show the main panel again.
        if (frame instanceof Start) {
            ((Start) frame).showMainPanel();
        }
    }
    /**
     * Gets the list of players.
     *
     * @return An ArrayList of Player objects containing the players.
     */
    public static ArrayList<Player> getPlayers() {
        return players;
    }
}

