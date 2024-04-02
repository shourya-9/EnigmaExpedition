import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class EnterNamePanel extends JPanel {
    private JTextField nameField;
    private JButton confirmButton, backButton;
    private static ArrayList<Player> players = new ArrayList<>();
    private JFrame frame;
    private Start startFrame;
    private Player player1;

    public EnterNamePanel(Start startFrame, JFrame frame, JPanel mainPanel) {
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

        OutlineLabel nameLabel = new OutlineLabel("Enter Your Name:");
        nameLabel.setFont(TitleFont);
        nameLabel.setForeground(Color.ORANGE);
        nameLabel.setBounds(375, 200, 300, 30); // Adjust label position

        add(nameLabel);
        add(nameField);
        add(confirmButton);
        add(backButton);

        // Listeners for buttons are similar, handling panel switch
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

        nameField.addActionListener(e -> confirmButton.doClick());

        backButton.addActionListener(e -> {
            frame.setContentPane(mainPanel);
            frame.revalidate();
            frame.repaint();
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

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image to fill the entire panel
        Image backgroundImage = new ImageIcon("photos/nameRoom.jpeg").getImage();
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }
}