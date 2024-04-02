import javax.swing.*;
import java.awt.*;

class Start extends JFrame {

    private JPanel panel;
    private AudioPlayer backgroundMusicPlayer;

    public Start() {
        super("Enigma Expedition: Path to Enlightenment");
        setSize(1000, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        // Creating a JPanel with null layout for absolute positioning
        panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("photos/bg4.jpeg").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

                Image myImage = new ImageIcon("photos/Header.png").getImage();
                // Adjust (100, 100) to the X, Y coordinates where you want to place the image
                // and (myImage.getWidth(null), myImage.getHeight(null)) to scale the image as needed
                g.drawImage(myImage, 385, 70, 260, 100, this);
            }
        };

        Font customFont = CustomFontLoader.getFont("photos/goblin.ttf", 26f);
        Font customFont1 = CustomFontLoader.getFont("photos/RichspookyBold.ttf",18f);
        Font customFont2 = CustomFontLoader.getFont("photos/RichspookyBold.ttf", 14f);

        backgroundMusicPlayer = new AudioPlayer("photos/music.wav");
        backgroundMusicPlayer.loop();


        // Add and position the title label
//        OutlineLabel titleLabel = new OutlineLabel("ENIGMA EXPEDITION", JLabel.CENTER);
//        titleLabel.setFont(customFont);
//        titleLabel.setForeground(Color.ORANGE);
//        titleLabel.setBounds(70, 50, 890, 30); // Adjust bounds as needed
//        panel.add(titleLabel);

//        OutlineLabel names1 = new OutlineLabel("By: Shourya Nundy, Dipraman Ghosh");
//        names1.setFont(customFont2);
//        names1.setForeground(Color.ORANGE);
//        names1.setBounds(10, 490, 350, 30); // Adjust bounds as needed
//        panel.add(names1);
//
//        OutlineLabel names2 = new OutlineLabel(" Darsh Shah, Armaan Sharma, Ethan Carvalho");
//        names2.setFont(customFont2);
//        names2.setForeground(Color.ORANGE);
//        names2.setBounds(10, 510, 400, 30); // Adjust bounds as needed
//        panel.add(names2);
//
////        OutlineLabel names3 = new OutlineLabel(" Ethan Carvalho");
////        names3.setFont(customFont2);
////        names3.setForeground(Color.ORANGE);
////        names3.setBounds(10, 530, 350, 30); // Adjust bounds as needed
////        panel.add(names3);
//
//        OutlineLabel br1 = new OutlineLabel("CS 2212, Western University");
//        br1.setFont(customFont2);
//        br1.setForeground(Color.ORANGE);
//        br1.setBounds(10, 530, 350, 30); // Adjust bounds as needed
//        panel.add(br1);
//
//        OutlineLabel br2 = new OutlineLabel("Team 36, Fall 2024");
//        br2.setFont(customFont2);
//        br2.setForeground(Color.ORANGE);
//        br2.setBounds(10, 550, 350, 30); // Adjust bounds as needed
//        panel.add(br2);

        // Play button setup
        JButton playButton = createImageButton("PLAY", "photos/WoodenMAIN.png", 250, 180, 240, 120, customFont1);
        panel.add(playButton);

        // Load game button setup
        JButton loadButton = createImageButton("LOAD", "photos/WoodenMAIN.png", 550, 180, 240, 120, customFont1);
        panel.add(loadButton);

        // How to play button setup
        JButton howToPlayButton = createImageButton("HOW TO PLAY", "photos/WoodenMAIN.png", 250, 330, 240, 120, customFont1);
        panel.add(howToPlayButton);

        // Quit button setup
        JButton quitButton = createImageButton("Quit", "photos/WoodenMAIN.png", 550, 330, 240, 120, customFont1);
        panel.add(quitButton);


        // Adding icon buttons without GridBagLayout constraints
        JButton leaderBoardButton = addIconButton(panel, "photos/leaderboard.png", 350, 450, 50, 50);
        panel.add(leaderBoardButton);
        
        JButton settingsButton = addIconButton(panel, "photos/settings.png", 450, 450, 50, 50);
        panel.add(settingsButton);
        
        JButton instDashButton = addIconButton(panel, "photos/instructor.png", 650, 450, 50, 50);
        panel.add(instDashButton);
        
        JButton gameMod = addIconButton(panel, "photos/feedback.png", 550, 450, 50, 50);
        panel.add(gameMod);
        
        
        playButton.addActionListener(e -> {
            EnterNamePanel enterNamePanel = new EnterNamePanel(this,this, panel);
            setContentPane(enterNamePanel);
            revalidate();
        });

        loadButton.addActionListener(e -> {
            NameLoad load = new NameLoad(this,this, panel);
            setContentPane(load);
            revalidate();
        });

        leaderBoardButton.addActionListener(e -> {
            LeaderboardPanel leaderboardPanel = new LeaderboardPanel(this,"./score.csv" ); // Pass 'this' reference
            setContentPane(leaderboardPanel);
            revalidate();
            repaint();
        });

        
        howToPlayButton.addActionListener(e -> {
            HowToPlayPanel HowToPlayPanel = new HowToPlayPanel(this); // Pass 'this' reference
            setContentPane(HowToPlayPanel);
            revalidate();
            repaint();
        });

        instDashButton.addActionListener(e -> {
            PasswordPanel pass = new PasswordPanel(this, this, panel); // Pass 'this' reference
            setContentPane(pass);
            revalidate();
            repaint();
        });
        gameMod.addActionListener(e -> {
            PasswordPanel2 pass2 = new PasswordPanel2(this, this, panel); // Pass 'this' reference
            setContentPane(pass2);
            revalidate();
            repaint();
        });
        
        settingsButton.addActionListener(e -> {
            SettingsPanel settingsPanel = new SettingsPanel(this); // Pass 'this' reference
            setContentPane(settingsPanel);
            revalidate();
            repaint();
        });
        
        


        // To change the background of the JOptionPane
        UIManager.put("Panel.background", Color.LIGHT_GRAY);
        UIManager.put("OptionPane.background", Color.LIGHT_GRAY);

        // To change the button colors
        UIManager.put("Button.background", new Color(35, 35, 35));
        UIManager.put("Button.foreground", new Color(19, 19, 19));
        UIManager.put("Button.focus", new Color(75, 83, 83)); // To remove the focus highlight

        // Displaying the JOptionPane after the UIManager changes
        quitButton.addActionListener(e -> {
            // Load your custom icon
            ImageIcon icon = new ImageIcon("photos/gamelogo.png");
            Image scaledIcon = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(scaledIcon);

            // Custom button texts
            Object[] options = {"Quit", "Cancel"};

            // Since 'this' inside the lambda refers to the ActionListener, you need to refer to the JFrame explicitly.
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(quitButton);

            // Display custom dialog
            int response = JOptionPane.showOptionDialog(
                    frame,
                    "Are you sure you want to quit the game?", // Message
                    "Quit Game", // Title
                    JOptionPane.YES_NO_OPTION, // Option type
                    JOptionPane.QUESTION_MESSAGE, // Message type
                    resizedIcon, // Use custom icon
                    options, // The titles of buttons
                    options[1]); // Default button title

            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Add the main panel to the frame
        add(panel);


        // Center the frame on the screen
        setLocationRelativeTo(null);

        CursorManager.getInstance().getCursor("Default");

    }

    private JButton createImageButton(String text, String imagePath, int x, int y, int width, int height, Font font) {
        JButton button = new JButton(text);
        ImageIcon icon = new ImageIcon(imagePath); // Load the image to an ImageIcon
        Image img = icon.getImage(); // Transform it
        Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // Scale it to fit the button size
        icon = new ImageIcon(newimg); // Transform it back

        button.setIcon(icon);
        button.setHorizontalTextPosition(JButton.CENTER); // Center text horizontally over the icon
        button.setVerticalTextPosition(JButton.CENTER); // Center text vertically over the icon
        button.setFont(font); // Set the custom font
        button.setForeground(Color.ORANGE); // Set text color

        button.setBounds(x, y, width, height);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        Hand.setHandCursor(button);

        return button;
    }


    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new RoundButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
//        button.setBackground(Color.orange);
//        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setBounds(x, y, width, height);
        return button;
    }

    private JButton addIconButton(JPanel panel, String imagePath, int x, int y, int width, int height) {
        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        JButton button = new JButton(icon);
        button.setBounds(x, y, width, height);
        button.setBackground(Color.white);
        Hand.setHandCursor(button);
//        button.setOpaque(true);
//        button.setBorderPainted(false);
//        button.setFocusPainted(false);
//        button.setContentAreaFilled(false);
        return button;
    }

    public void setMusicVolume(float volume) { // Volume: 0.0 - 1.0
        backgroundMusicPlayer.setVolume(volume);
    }

    public void showMainPanel() {
        setContentPane(panel);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Start().setVisible(true));
    }
}
