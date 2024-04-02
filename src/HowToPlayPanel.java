import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.table.*;

/**
 * The {@code HowToPlayPanel} class extends {@link JPanel} and provides a user interface
 * to display the game's rules and instructions. It features a welcome message, a set of humorous
 * rules for how to play the game, and a "BACK" button to return to the main menu.
 */
public class HowToPlayPanel extends JPanel {
	/**
     * Constructs a new {@code HowToPlayPanel} that displays the game's rules and a "BACK" button
     * to navigate back to the start frame or main menu. The panel is designed with a heading label
     * at the top and a rules label that contains the game instructions in a formatted HTML string
     * for easy readability.
     *
     * @param startFrame The {@code Start} frame that this panel can return to. Can be {@code null}
     *                   if used outside the context of a {@code Start} frame for testing or standalone purposes.
     */
	private Image background;
	
	public HowToPlayPanel(Start startFrame) {
        setLayout(new BorderLayout());
        
        try {
            background = ImageIO.read(new File("photos/bg7.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error - perhaps set a default background color or image
        }

        JPanel headerPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image, scaled to fill the entire panel
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        Font customFont = CustomFontLoader.getFont("photos/RichspookyBold.ttf",26f);
        Font customFont1 = CustomFontLoader.getFont("photos/RichspookyBold.ttf",15f);
        
        headerPanel.setOpaque(false);
        headerPanel.setPreferredSize(new Dimension(getWidth(), 60)); // Set the preferred height for the header

        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Tahoma", Font.BOLD, 16)); // Set the font for the button
        backButton.setForeground(new Color(0x8B4513)); // Dark brown color for the text

        headerPanel.add(backButton, BorderLayout.WEST);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel heading = new JLabel("Welcome to Enigma Expedition", SwingConstants.CENTER);
        heading.setFont(customFont); // Customized font for the heading
        heading.setForeground(new Color(0x1E0E04)); // Dark brown color for the text

        headerPanel.add(heading, BorderLayout.CENTER);

        JTextArea rules = new JTextArea(
            "  1. \"Your goal is to escape from a series of rooms by correctly answering trivia    questions.\"\n\n" +
            "  2. Each room presents a unique set of questions ranging from general knowledge,   literature, mathematics, to science.\n\n" +
            "  3. Answer correctly to move forward or face the consequences of a wrong answer.\n\n" +
            "  4. You have 3 lives a correct answer will allow you to progress, while a wrong answer will cost you a life.\n\n" +
            "  5. You can save your game progress at any time. Simply select \\\"Save Game\\\" from the pause menu or press S on your keyboard. To continue where you left off, select \\\"Load Game\\\" from the main menu. \n\n" +
            "  6. To adjust the volume, click pause to open the settings page and adjust the slider to suit your needs\n\n" +
            "  7. To quit your current game session, simply go to settings and click on \\\"Exit\\\". or press E on your keyboard \n\n" +
            "  8. Can you make it to the end and achieve the ultimate escape?\n"
        );
        rules.setEditable(false);
        rules.setWrapStyleWord(true);
        rules.setLineWrap(true);
        rules.setOpaque(false);
        rules.setFont(customFont1); // Customized font for the rules
        rules.setForeground(new Color(0xFFD5A77C, true)); // Dark brown color for the text

        JScrollPane scrollPane = new JScrollPane(rules);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        backButton.addActionListener(e -> {
            startFrame.showMainPanel(); // Switch back to the main panel
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image, scaled to fill the entire panel
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
	/**
     * The entry point for a standalone test of the {@code HowToPlayPanel}. It initializes the panel
     * within a Swing application frame to preview its layout and functionality independently of the
     * main application. This method is useful for testing and design purposes.
     *
     * @param args The command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(2000, 768);
            frame.setVisible(true);
        });
    }
}