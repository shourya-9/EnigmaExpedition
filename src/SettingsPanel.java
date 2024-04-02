import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SettingsPanel extends JPanel {

    private JSlider musicVolumeSlider;
    private JSlider sfxVolumeSlider;
    private JComboBox<String> cursorDesignDropdown;
    private JFrame mainFrame; // Reference to the main frame to switch back to the main panel
    private Image backgroundImage;

    public SettingsPanel(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null); // Use null layout for absolute positioning

        Font customFont = CustomFontLoader.getFont("photos/goblin.ttf", 26f);

        // Load the background image
        ImageIcon icon = new ImageIcon("photos/settings.jpeg"); // Replace with your image path
        backgroundImage = icon.getImage();

        // Back button
        JButton backButton = new JButton("BACK");
        backButton.setBounds(10, 10, 80, 25); // Set position and size
        backButton.addActionListener(e -> goBack());
        add(backButton);

        // Settings title
        OutlineLabel settingsTitle = new OutlineLabel("SETTINGS");
        settingsTitle.setFont(customFont);
        settingsTitle.setForeground(Color.ORANGE);
        settingsTitle.setBounds(400, 175, 300, 30); // Adjust position and size
        add(settingsTitle);

        // Music volume slider
        musicVolumeSlider = new JSlider(0, 100);
        //musicVolumeSlider.setBounds(400, 210, 200, 50); // Adjust position and size
        addLabeledSlider(musicVolumeSlider, "Music", 400, 220,390, 230, 220, 50);

        // SFX volume slider
//        sfxVolumeSlider = new JSlider(0, 100);
//        addLabeledSlider(sfxVolumeSlider, "SFX", 400, 270,390, 280, 220, 50); // Adjust Y position

        // Cursor design dropdown
        cursorDesignDropdown = new JComboBox<>(new String[]{"Default", "Magnifying glass", "Finger", "Key"});
        OutlineLabel cursorDesignLabel = new OutlineLabel("Cursor Design");
        cursorDesignLabel.setFont(new Font("Arial", Font.BOLD, 18));
        cursorDesignLabel.setForeground(Color.ORANGE);
        cursorDesignLabel.setBounds(400, 270, 200, 30); // Adjust position and size
        cursorDesignDropdown.setBounds(390, 300, 220, 30); // Adjust position and size
        add(cursorDesignLabel);
        add(cursorDesignDropdown);

        cursorDesignDropdown.addActionListener(e -> updateCursor());

        musicVolumeSlider.addChangeListener(e -> {
            if (!musicVolumeSlider.getValueIsAdjusting()) {
                float volume = musicVolumeSlider.getValue() / 100f;
                // Assuming 'mainFrame' is an instance of 'Start'
                ((Start) mainFrame).setMusicVolume(volume);
            }
        });

        Action goBackAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack(); // This method contains the logic to go back to the main panel.
            }
        };

// Bind the Escape key to the goBackAction
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "goBack");
        this.getActionMap().put("goBack", goBackAction);


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    private void addLabeledSlider(JSlider slider, String label, int xPos, int yPos,int x, int y, int width, int height) {
        OutlineLabel sliderLabel = new OutlineLabel(label);
        sliderLabel.setBounds(xPos, yPos, 200, 30); // Adjust position and size
        sliderLabel.setForeground(Color.ORANGE);
        sliderLabel.setFont(new Font("Arial", Font.BOLD, 18));
        slider.setBounds(x, y, width, height); // Adjust position and size based on label
        add(sliderLabel);
        add(slider);
    }

    private void updateCursor() {
        String selectedCursor = (String) cursorDesignDropdown.getSelectedItem();
        Cursor cursor = CursorManager.getInstance().getCursor(selectedCursor);
        SwingUtilities.getWindowAncestor(this).setCursor(cursor); // Set cursor for the whole window
    }


    private void goBack() {
        // Navigate back to the main panel
        if (mainFrame instanceof Start) {
            ((Start) mainFrame).showMainPanel();
        }
    }
}
