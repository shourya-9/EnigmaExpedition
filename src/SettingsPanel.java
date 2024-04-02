import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
/**
 * The {@code SettingsPanel} class extends {@link JPanel} to provide a user interface for adjusting game settings.
 * It includes controls for adjusting music and sound effects (SFX) volume, selecting cursor design, and navigating back
 * to the main menu. The panel layout is manually managed for precise control positioning.
 */

public class SettingsPanel extends JPanel {

    private JSlider musicVolumeSlider;
    private JSlider sfxVolumeSlider;
    private JComboBox<String> cursorDesignDropdown;
    private JFrame mainFrame; // Reference to the main frame to switch back to the main panel
    private Image backgroundImage;
    /**
     * Constructs a {@code SettingsPanel} with references to the main application frame.
     * Initializes UI components for the settings menu including sliders, labels, and a dropdown menu.
     *
     * @param mainFrame The main application frame to which the settings panel belongs.
     */

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

    /**
     * Paints the panel component, including drawing the background image over the entire panel.
     *
     * @param g The {@link Graphics} object used for drawing operations.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    /**
     * Adds a labeled slider to the panel for settings like music and SFX volume. This utility method
     * simplifies the addition of sliders with associated labels.
     *
     * @param slider The {@link JSlider} to add to the panel.
     * @param label The text label for the slider.
     * @param xPos X position of the label.
     * @param yPos Y position of the label.
     * @param x X position of the slider.
     * @param y Y position of the slider.
     * @param width Width of the slider.
     * @param height Height of the slider.
     */

    private void addLabeledSlider(JSlider slider, String label, int xPos, int yPos,int x, int y, int width, int height) {
        OutlineLabel sliderLabel = new OutlineLabel(label);
        sliderLabel.setBounds(xPos, yPos, 200, 30); // Adjust position and size
        sliderLabel.setForeground(Color.ORANGE);
        sliderLabel.setFont(new Font("Arial", Font.BOLD, 18));
        slider.setBounds(x, y, width, height); // Adjust position and size based on label
        add(sliderLabel);
        add(slider);
    }

    /**
     * Updates the cursor of the window based on the selection from a dropdown menu.
     * This method retrieves the selected cursor design from a dropdown menu,
     * obtains the corresponding Cursor object from the CursorManager, and sets
     * the cursor for the entire window.
     */
    private void updateCursor() {
        String selectedCursor = (String) cursorDesignDropdown.getSelectedItem();
        Cursor cursor = CursorManager.getInstance().getCursor(selectedCursor);
        SwingUtilities.getWindowAncestor(this).setCursor(cursor); // Set cursor for the whole window
    }

    /**
     * Navigates back to the main panel. This method is typically called in response to the user
     * clicking the "Back" button.
     */

    private void goBack() {
        // Navigate back to the main panel
        if (mainFrame instanceof Start) {
            ((Start) mainFrame).showMainPanel();
        }
    }
}
