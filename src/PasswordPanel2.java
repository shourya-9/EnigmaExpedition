import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
/**
 * The {@code PasswordPanel2} class extends {@link JPanel} to create a UI panel that prompts the user
 * for a password to access restricted parts of the application, such as a game modification panel.
 * It includes a text field for the password, confirm and back buttons for navigation, and uses a custom
 * font for text display.
 */

public class PasswordPanel2 extends JPanel {
    private JTextField nameField;
    private JButton confirmButton, backButton;
    private static ArrayList<Player> players = new ArrayList<>();
    private JFrame frame;
    private Start startFrame;
    private Player player1;
    /**
     * Constructs a {@code PasswordPanel2} with specified parameters for navigation and layout setup.
     * Initializes the panel with a text field for password input and buttons for user interaction.
     *
     * @param startFrame The start frame of the application for navigation purposes.
     * @param frame The main application frame for updating the displayed panel.
     * @param mainPanel The main panel of the application for returning from the password panel.
     */

    public PasswordPanel2(Start startFrame, JFrame frame, JPanel mainPanel) {
    	this.startFrame = new Start();
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

        JLabel nameLabel = new JLabel("Enter the Password:");
        nameLabel.setFont(TitleFont);
        nameLabel.setForeground(Color.ORANGE);
        nameLabel.setBounds(350, 200, 300, 30); // Adjust label position

        add(nameLabel);
        add(nameField);
        add(confirmButton);
        add(backButton);

        // Listeners for buttons are similar, handling panel switch
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = nameField.getText().trim();
                if (!password.isEmpty() && password.equals("HELLO123")) {
                    gameMod gameMod  = new gameMod(startFrame);
                    // Switch back to the main panel or proceed to the next part of the game
                    frame.setContentPane(gameMod);
                    frame.revalidate();
                    frame.repaint();
                    System.out.println("You're into the system"); // For debug
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        nameField.addActionListener(e -> confirmButton.doClick());

        backButton.addActionListener(e -> {
            frame.setContentPane(mainPanel);
            frame.revalidate();
            frame.repaint();
        });

        Action goBackAction = new AbstractAction() {
        	/**
             * Overrides the {@code paintComponent} method to draw a custom background image on the panel.
             *
             * @param g The {@link Graphics} object used for drawing operations.
             */
            

            @Override
            public void actionPerformed(ActionEvent e) {
                startFrame.showMainPanel(); // The operation you want to perform
            }
        };

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "goBack");
        this.getActionMap().put("goBack", goBackAction);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image to fill the entire panel
        Image backgroundImage = new ImageIcon("photos/bg6.jpeg").getImage();
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
    /**
     * Provides access to the static list of players. While not used within the context of password verification,
     * this method allows for future extensions where player data might be required.
     *
     * @return A static {@code ArrayList<Player>} containing the players.
     */

    public static ArrayList<Player> getPlayers() {
        return players;
    }
}