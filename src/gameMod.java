import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.AbstractBorder;

public class gameMod extends JPanel {
    private JFrame mainFrame; // Reference to the main frame for navigation
    private Image backgroundImage;
    private Player player1;
    private Start start;


    public gameMod(Start start) {
        this.mainFrame = mainFrame;
        this.start=start;
        setLayout(null); // Use null layout for absolute positioning
        
        // Back button for navigation
        JButton backButton = createStyledButton("BACK", 10, 10, 100, 35);
        backButton.addActionListener(e -> goBack());
        add(backButton);

        // Title for the TestDebug panel
        JLabel titleLabel = new JLabel("TEST DEBUG", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(100, 20, 300, 35);
        titleLabel.setOpaque(false);
        add(titleLabel);

     // Individual buttons for each room
        JButton room1Button = createStyledButton("Room 1", 150, 100, 180, 50);
        room1Button.addActionListener(e -> connectToRoom("Room 1"));
        add(room1Button);

        JButton room2Button = createStyledButton("Room 2", 150, 160, 180, 50);
        room2Button.addActionListener(e -> connectToRoom("Room 2"));
        add(room2Button);

        JButton room3Button = createStyledButton("Room 3", 150, 220, 180, 50);
        room3Button.addActionListener(e -> connectToRoom("Room 3"));
        add(room3Button);

        JButton room4Button = createStyledButton("Room 4", 150, 280, 180, 50);
        room4Button.addActionListener(e -> connectToRoom("Room 4"));
        add(room4Button);

        JButton room5Button = createStyledButton("Room 5", 150, 340, 180, 50);
        room5Button.addActionListener(e -> connectToRoom("Room 5"));
        add(room5Button);

        // Add the buttons to the panel
        add(room1Button);
        add(room2Button);
        add(room3Button);
        add(room4Button);
        add(room5Button);
    }

    private JButton createStyledButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBounds(x, y, width, height);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setBackground(new Color(100, 100, 100));
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorder(new RoundedBorder(10)); 
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(130, 130, 130)); // Lighter shade on hover
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(100, 100, 100)); // Original color
            }
        });

        // Custom rounded border
        button.setBorder(new RoundedBorder(10)); // 10 is the radius for the rounded corners
        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image backgroundImage = new ImageIcon("photos/bg2.jpeg").getImage();
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public void connectToRoom(String roomName) {
    	player1=new Player("gameMod",10000);
        if (roomName.equals("Room 1")){
        	player1.setRoom(0);
        	Room1Panel room1=new Room1Panel(start,start,player1);
        	
        	start.setContentPane(room1); // Set Room2Panel as the current content pane
            start.revalidate();
            start.repaint();	
        }
        else if (roomName.equals("Room 2")){
        	Room2Panel room2=new Room2Panel(start,start,player1);
        	
        	start.setContentPane(room2); // Set Room2Panel as the current content pane
            start.revalidate();
            start.repaint();
        }
        else if (roomName.equals("Room 3")){
        	Room3Panel room3=new Room3Panel(start,start,player1);
        	start.setContentPane(room3); // Set Room2Panel as the current content pane
            start.revalidate();
            start.repaint();
        }
        else if (roomName.equals("Room 4")){
        	Room4Panel room4=new Room4Panel(start,start,player1);
        	start.setContentPane(room4); // Set Room2Panel as the current content pane
            start.revalidate();
            start.repaint();
        }else if (roomName.equals("Room 5")){
        	Room5Panel room5=new Room5Panel(start,start,player1);
        	start.setContentPane(room5); // Set Room2Panel as the current content pane
            start.revalidate();
            start.repaint();
        }
        System.out.println("Connecting to " + roomName);
        // Replace with actual room connection logic
    }

    private void goBack() {
        // Navigate back to the main panel or another relevant panel
    	start.showMainPanel();
    }

    private static class RoundedBorder extends AbstractBorder {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.GRAY);
            g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2d.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = this.radius+1;
            insets.right = this.radius+1;
            insets.top = this.radius+1;
            insets.bottom = this.radius+2;
            return insets;
        }
    }
}