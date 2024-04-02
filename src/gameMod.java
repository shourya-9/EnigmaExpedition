import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel for selecting game modes and connecting to different rooms in the game.
 */
public class gameMod extends JPanel {
    private Start start; // Reference to the Start frame
    private Image backgroundImage; // Background image of the panel
    private Player player1; // Player object

    /**
     * Constructs the gameMod panel with necessary components and layout.
     * @param start Reference to the Start frame of the application.
     */
    public gameMod(Start start) {
        this.start = start;
        setLayout(null); // Use null layout for absolute positioning
        initUI(); // Initialize UI components
    }

    /**
     * Initializes UI components.
     */
    private void initUI() {
        backgroundImage = new ImageIcon("photos/bg2.jpeg").getImage(); // Load background image

        // Title label
        JLabel titleLabel = new JLabel("TEST DEBUG", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(355, 50, 300, 35); // Adjust bounds as needed
        add(titleLabel);

        // Room buttons
        String[] roomNames = {"Room 1", "Room 2", "Room 3", "Room 4", "Room 5"};
        int startYPos = 150; // Starting Y position for the first button
        int buttonWidth = 180;
        int buttonHeight = 50;
        int spacingY = 30; // Space between buttons vertically
        int startXPos = (1025 - buttonWidth) / 2; // Center buttons horizontally

        for (int i = 0; i < roomNames.length; i++) {
            final int yPos = startYPos + (i * (buttonHeight + spacingY));
            final String roomName = roomNames[i];
            JButton roomButton = createImageButton(roomName, "photos/WoodenMAIN.png", startXPos, yPos, buttonWidth, buttonHeight);
            roomButton.addActionListener(e -> connectToRoom(roomName));
            add(roomButton);
        }

        // Back button
        JButton backButton = createImageButton("BACK", "photos/WoodenMAIN.png", 10, 10, 100, 35); // Placed on the top left
        backButton.addActionListener(e -> goBack());
        add(backButton);
    }

    /**
     * Creates a JButton with an image icon and specified properties.
     * @param text The text displayed on the button.
     * @param imagePath The path to the image used as the button icon.
     * @param x The x-coordinate of the button.
     * @param y The y-coordinate of the button.
     * @param width The width of the button.
     * @param height The height of the button.
     * @return The created JButton.
     */
    private JButton createImageButton(String text, String imagePath, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.ORANGE);
        button.setBounds(x, y, width, height);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }

    /**
     * Paints the background image of the panel.
     * @param g The Graphics context used for painting.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    /**
     * Connects to the specified room.
     * @param roomName The name of the room to connect to.
     */
    public void connectToRoom(String roomName) {
        player1 = new Player("gameMod", 10000); // Create a new player
        if (roomName.equals("Room 1")) {
            player1.setRoom(0); // Set player's room
            Room1Panel room1 = new Room1Panel(start, start, player1); // Create Room1Panel
            start.setContentPane(room1); // Set Room1Panel as the current content pane
            start.revalidate();
            start.repaint();
        } else if (roomName.equals("Room 2")) {
            // Similar logic for other rooms...
        }
        System.out.println("Connecting to " + roomName);
        // Replace with actual room connection logic
    }

    /**
     * Navigates back to the main panel.
     */
    private void goBack() {
        start.showMainPanel(); // Navigate back to the main panel
    }
}
