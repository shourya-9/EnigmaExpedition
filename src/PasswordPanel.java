import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
/**
 * The {@code PasswordPanel} class extends {@link JPanel} to create a UI panel for password entry.
 * This panel is used to control access to certain parts of an application, such as an instructor dashboard.
 * It includes a text field for password input, a confirm button to submit the password, and a back button
 * for navigation.
 */

public class PasswordPanel extends JPanel {
    private JTextField nameField;
    private JButton confirmButton, backButton;
    private static ArrayList<Player> players = new ArrayList<>();
    private JFrame frame;
    private Start startFrame;
    private Player player1;
    /**
     * Constructs a {@code PasswordPanel} with references to the main application frame, the start frame,
     * and the main panel. Initializes UI components including the password input field and navigation buttons.
     *
     * @param startFrame The start frame of the application, used for navigation and displaying the main panel.
     * @param frame The main application frame for updating the displayed panel.
     * @param mainPanel The main panel of the application, used for navigating back from the password panel.
     */

    public PasswordPanel(Start startFrame, JFrame frame, JPanel mainPanel) {
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
                    InstructorDashboard instructorDashboard  = new InstructorDashboard(startFrame);
                    // Switch back to the main panel or proceed to the next part of the game
                    frame.setContentPane(instructorDashboard);
                    frame.revalidate();
                    frame.repaint();
                    System.out.println("You're into the system"); // For debug
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(e -> {
            frame.setContentPane(mainPanel);
            frame.revalidate();
            frame.repaint();
        });

        nameField.addActionListener(e -> confirmButton.doClick());

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
     * Overrides the {@code paintComponent} method to draw a custom background image on the panel.
     *
     * @param g The {@link Graphics} object used for drawing operations.
     */
    @Override

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image to fill the entire panel
        Image backgroundImage = new ImageIcon("photos/bg6.jpeg").getImage();
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
    /**
     * Provides access to the static list of players. This method is currently not used within the context
     * of the {@code PasswordPanel} but allows for future extensions where player data may be required.
     *
     * @return A static {@code ArrayList<Player>} containing the players.
     */

    public static ArrayList<Player> getPlayers() {
        return players;
    }
}