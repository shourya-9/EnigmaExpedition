import javax.swing.*;

import com.opencsv.exceptions.CsvValidationException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GameSettingsPanel extends JPanel {

    private JSlider musicVolumeSlider;
    private JSlider sfxVolumeSlider;
    private JComboBox<String> cursorDesignDropdown;
    private JFrame frame; // Reference to the main frame to switch back to the main panel
    private JPanel roomPanel;
    private GameRoom gameRoom;
    private Player player1;
    private Start startFrame;
    private Image backgroundImage;


    public GameSettingsPanel(Start startFrame,JFrame frame, GameRoom gameRoom, Player player1) {
        this.frame = frame;
        this.gameRoom = gameRoom;
        this.player1 = player1;
        this.startFrame = startFrame;

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

        JButton saveButton = new JButton("SAVE");
        saveButton.addActionListener(e -> saveprogress());
        saveButton.setBounds(900, 10, 80, 25); // Set position and size
        add(saveButton);

        JButton exitButton = new JButton("EXIT");
        exitButton.addActionListener(e -> exitProgress());
        exitButton.setBounds(900, 40, 80, 25); // Set position and size
        add(exitButton);

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

        setupKeyBindings();
    }

    private void setupKeyBindings() {
        // Back Button Binding
        bindKeyStrokeToAction("ESCAPE", KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });

        // Save Button Binding
        bindKeyStrokeToAction("SAVE", KeyStroke.getKeyStroke('s'), new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveprogress();
            }
        });

        // Exit Button Binding
        bindKeyStrokeToAction("EXIT", KeyStroke.getKeyStroke('e'), new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitProgress();
            }
        });
    }

    private void bindKeyStrokeToAction(String name, KeyStroke keyStroke, Action action) {
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(keyStroke, name);
        actionMap.put(name, action);
    }


    private void saveprogress() {
        // TODO Auto-generated method stub
        Incompletesave inc =new Incompletesave(player1);
        System.out.print(inc.toString());
        try {
            inc.removeDataFromCSV(player1.getName());
        } catch (CsvValidationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        inc.exportPlayerToCSV(inc.toString(), "./incomplete.csv");
        JOptionPane.showMessageDialog(frame, "Your data has successfully been saved!");
        gameRoom.pauseGame();
        startFrame.showMainPanel();

    }

    private void exitProgress() {
        JOptionPane.showMessageDialog(frame, "You're exiting the game");
        gameRoom.pauseGame();
        startFrame.showMainPanel();

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    private void updateCursor() {
        String selectedCursor = (String) cursorDesignDropdown.getSelectedItem();
        Cursor cursor = CursorManager.getInstance().getCursor(selectedCursor);
        SwingUtilities.getWindowAncestor(this).setCursor(cursor); // Set cursor for the whole window
    }

    private void goBack() {
        frame.setContentPane((Container) gameRoom); // Cast to Component for Swing compatibility
        frame.revalidate();

        frame.repaint();
    }
}
