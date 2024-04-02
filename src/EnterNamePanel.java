import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Panel for entering player's name in the game.
 */
public class EnterNamePanel extends JPanel {
    private JTextField nameField; // Input field for entering player's name
    private JButton confirmButton, backButton; // Buttons for confirming and going back
    private static ArrayList<Player> players = new ArrayList<>(); // List of players
    private JFrame frame; // Main frame of the application
    private Start startFrame; // Reference to the starting frame
    private Player player1; // Player object

    /**
     * Constructs the EnterNamePanel with necessary components and layout.
     * @param startFrame Reference to the starting frame of the application.
     * @param frame Main frame of the application.
     * @param mainPanel Reference to the main panel of the application.
     */
    public EnterNamePanel(Start startFrame, JFrame frame, JPanel mainPanel) {
        this.frame = frame;
        this.startFrame = startFrame;
        setLayout(null);

        Font TitleFont = CustomFontLoader.getFont("photos/RichSpookyBold.ttf", 24f); // Custom font for titles

        // Text Field
        nameField = new JTextField(20);
        // Styling for the name field
        nameField.setFont(TitleFont);
        nameField.setForeground(Color.orange);
        nameField.setOpaque(false);
        nameField.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
        nameField.setBounds(350, 230, 300, 30); // Example bounds

        // Confirm Button
        confirmButton = new JButton("Confirm");
        // Styling for the confirm button
        confirmButton.setFont(TitleFont);
        confirmButton.setForeground(Color.ORANGE);
        confirmButton.setOpaque(false);
        confirmButton.setContentAreaFilled(false);
        confirmButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
        confirmButton.setBounds(503, 265, 147, 30); // Adjust position and size as needed

        // Back Button
        backButton = new JButton("Back");
        // Styling for the back button
        backButton.setFont(TitleFont);
        backButton.setForeground(Color.ORANGE);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
        backButton.setBounds(350, 265, 147, 30); // Adjust position and size as needed

        // Label for instructions
        OutlineLabel nameLabel = new OutlineLabel("Enter Your Name:");
        nameLabel.setFont(TitleFont);
        nameLabel.setForeground(Color.ORANGE);
        nameLabel.setBounds(375, 200, 300, 30); // Adjust label position

        // Adding components to the panel
        add(nameLabel);
        add(nameField);
        add(confirmButton);
        add(backButton);

        // Action listener for confirm button
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = nameField.getText().trim();
                if (!playerName.isEmpty()) {
                    player1 = new Player(playerName, 3); // Assuming constructor Player(String name, int someValue)
                    players.add(player1);
                    Room1Panel room1Panel = new Room1Panel(startFrame, frame, player1);
                    // Switch back to the main panel or proceed to the next part of the game
                    frame.setContentPane(room1Panel);
                    frame.revalidate();
                    frame.repaint();
                    System.out.println("Player added: " + playerName); // For debug
                } else {
                    JOptionPane.showMessageDialog(frame, "Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Automatically confirm when pressing enter in name field
        nameField.addActionListener(e -> confirmButton.doClick());

        // Action listener for back button
        backButton.addActionListener(e -> {
            frame.setContentPane(mainPanel);
            frame.revalidate();
            frame.repaint();
        });

        // Action to go back when pressing escape key
        Action goBackAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startFrame.showMainPanel(); // Go back to the main panel
            }
        };

        // Mapping the escape key to the action of going back
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "goBack");
        this.getActionMap().put("goBack", goBackAction);
    }

    /**
     * Paints the background image of the panel.
     * @param g The Graphics context used for painting.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image to fill the entire panel
        Image backgroundImage = new ImageIcon("photos/nameRoom.jpeg").getImage();
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    /**
     * Retrieves the list of players.
     * @return The list of players.
     */
    public static ArrayList<Player> getPlayers() {
        return players;
    }
}
