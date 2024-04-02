import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import com.opencsv.exceptions.CsvValidationException;

/**
 * The {@code Room2Panel} class represents the second room or level in a quiz game.
 * It extends {@link JPanel} and implements the {@link GameRoom} interface,
 * providing a graphical interface for displaying questions, answers, and handling game logic.
 * This class manages the gameplay mechanics, such as displaying questions, checking answers,
 * handling lives, and managing timers for question deadlines.
 *
 * @see JPanel
 * @see GameRoom
 */
public class Room2Panel extends JPanel implements GameRoom {
	private static OutlineLabel livesLabel; // Label to display lives remaining
	private Start startFrame;
	private Image background;
	private JPanel topPanel;
    private JFrame frame;
    private String playerName;
    private int currentQuestionIndex = 0;
    private OutlineLabel questionLabel, timerLabel;
    private JPanel optionsPanel;
    private Timer questionTimer;
    private int timeLeft =15;
    private Player player1;
    private static int lives;
    private boolean timerInitialized = false;
    public String[][] questions = {
            {"Which country has the largest population in the world?", "A. India", "B. United States", "C. China", "D. Indonesia"},
            {"In which city would you find the Colosseum?", "A. Athens", "B. Rome", "C. Istanbul", "D. Paris"},
            {"The Great Barrier Reef is located off the coast of which country?", "A. Brazil", "B. Australia", "C. South Africa", "D. Mexico"},
            {"What is the official language of Brazil?", "A. Spanish", "B. Portuguese", "C. French", "D. English"},
            {"Which country is both an island and a continent?", "A. Greenland", "B. Anatarctica", "C. Madagascar", "D. Australia"}
        };
    public int[] answers = {1,2,2,2,4};
    /**
     * Constructs a new {@code Room2Panel} instance for displaying and managing the second room of the game.
     * It initializes the game UI components, loads the background, and sets up the initial game state.
     *
     * @param startFrame The start frame of the game.
     * @param frame The main game frame.
     * @param player1 The player instance.
     */
    public Room2Panel(Start startFrame,JFrame frame, Player player1) {
    	this.startFrame = startFrame;
        this.frame = frame;
        this.player1 = player1;
        this.playerName = player1.getName();
        this.lives = player1.getLives();
        System.out.println(lives);
        setupRoom();
    }
    /**
     * Sets up the room layout and initializes UI components such as labels for welcome message, timer, and lives.
     * It also handles loading the background image and displaying the first question.
     */
    private void setupRoom() {
        // Welcome label
    	setLayout(new BorderLayout());
    	topPanel = new JPanel(new BorderLayout());
    	
        OutlineLabel welcomeLabel = new OutlineLabel("Welcome to Room 2, " + playerName, SwingConstants.CENTER);
        timerLabel = new OutlineLabel("Time left: 15", SwingConstants.CENTER);
        livesLabel = new OutlineLabel("Lives: " + lives + "  ", SwingConstants.CENTER);
        Font welcomeFont = new Font("Serif", Font.BOLD, 24);
        welcomeLabel.setFont(welcomeFont);
        Color textColor = new Color(255, 255, 255); // White color for text
        welcomeLabel.setForeground(textColor);

        topPanel.add(welcomeLabel, BorderLayout.NORTH);
        topPanel.add(timerLabel, BorderLayout.CENTER);
        topPanel.add(livesLabel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);
        
        try {
            background = ImageIO.read(new File("photos/bg1.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error - perhaps set a default background color or image
        }
        
        questionLabel = new OutlineLabel("Question text", SwingConstants.CENTER);
        add(questionLabel, BorderLayout.CENTER);

        optionsPanel = new JPanel(new GridLayout(2, 2, 0, 0));
        add(optionsPanel, BorderLayout.SOUTH);


        JButton settingsButton = createSettingsButton();
        settingsButton.setBounds(100, 100, 50, 50);
        topPanel.add(settingsButton, BorderLayout.WEST); // Add the settings button to the top panel
        customizeComponents();
        displayQuestion();

        // Define the pause action
        Action pauseGameAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSettingsPanel(); // The operation triggered by pressing the pause button
            }
        };

// Bind the Escape key to the pauseGameAction for the panel
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "pauseGame");
        this.getActionMap().put("pauseGame", pauseGameAction);

    }
    /**
     * Customizes the UI components, such as fonts, colors, and borders.
     */
    private void customizeComponents() {
        Font questionFont = new Font("Serif", Font.BOLD, 30);
        Font optionFont = new Font("Serif", Font.PLAIN, 26);
        Color textColor = new Color(255, 255, 255); // White color for text
        Color buttonColor = new Color(0, 0, 0, 100); // Semi-transparent black for buttons

        optionsPanel.setOpaque(false); // Make options panel transparent
        //optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50)); // Adjust as needed

        topPanel.setOpaque(false); // Make top panel transparent

        questionLabel.setFont(questionFont);
        questionLabel.setForeground(textColor);
        timerLabel.setFont(optionFont);
        timerLabel.setForeground(textColor);
        livesLabel.setFont(optionFont);
        livesLabel.setForeground(textColor);

        for (Component comp : optionsPanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.setFont(optionFont);
                button.setForeground(textColor);
                button.setBackground(buttonColor);
                button.setOpaque(false);
                button.setBorder(new RoundedBorder(10)); // Optional rounded border
            }
        }
    }

    /**
     * The {@code RoundedBorder} class implements the {@link Border} interface
     * to create a custom rounded border for components. It allows for the customization
     * of the radius of the corners, providing a more aesthetically pleasing appearance
     * for buttons or other components within the game's UI.
     */
    private class RoundedBorder implements Border {
        private int radius;
        /**
         * Constructs a {@code RoundedBorder} with the specified corner radius.
         *
         * @param radius The radius of the corners of the border.
         */
        RoundedBorder(int radius) {
            this.radius = radius;
        }
        /**
         * Returns the insets of the border.
         *
         * @param c The component for which this border insets value applies.
         * @return An {@link Insets} object containing the border insets.
         */
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }
        /**
         * Indicates whether or not the border is opaque.
         *
         * @return {@code false} as this custom border does not fill all pixels (due to rounded corners).
         */
        public boolean isBorderOpaque() {
            return false;
        }
        /**
         * Paints the border for the specified component with the specified position and size.
         *
         * @param c The component on which to paint the border.
         * @param g The {@link Graphics} object used for painting.
         * @param x The x position of the border.
         * @param y The y position of the border.
         * @param width The width of the border.
         * @param height The height of the border.
         */
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }

    /**
     * Creates and returns a {@link JButton} that serves as the settings button.
     * The button is customized with a settings icon, intended for opening the settings panel
     * when clicked. The appearance of the button is adjusted to blend well with the game's theme,
     * including removing the border and content area to emphasize the icon.
     *
     * @return The customized settings {@link JButton}.
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }
    /**
     * Creates and returns a {@link JButton} that serves as the settings button.
     * The button is customized with a settings icon, intended for opening the settings panel
     * when clicked. The appearance of the button is adjusted to blend well with the game's theme,
     * including removing the border and content area to emphasize the icon.
     *
     * @return The customized settings {@link JButton}.
     */
    private JButton createSettingsButton() {
        ImageIcon originalIcon = new ImageIcon("photos/pause.png"); // Replace with your actual icon path

        // Resize the icon
        Image resizedImage = originalIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH); // Adjust width and height as needed
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Create the button with the resized icon
        JButton settingsButton = new JButton(resizedIcon);
        settingsButton.addActionListener(e -> openSettingsPanel());
        // Optional: remove the button border and content area if you want just the icon visible
        settingsButton.setBorderPainted(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setFocusPainted(false);
        settingsButton.setOpaque(false);

        return settingsButton;
    }
    /**
     * Opens the settings panel by replacing the current content pane with a {@link GameSettingsPanel}.
     * This method facilitates the transition to the game's settings view from the current room,
     * allowing players to adjust settings such as sound or to pause the game.
     */
    private void openSettingsPanel() {
        GameSettingsPanel settingsPanel = new GameSettingsPanel(startFrame,frame,this,player1);
        frame.setContentPane(settingsPanel);
        frame.revalidate();
        frame.repaint();
    }
    /**
     * Displays the current question along with its answer options on the UI.
     * export player data to csv files
     */
    public void displayQuestion() {
        if (currentQuestionIndex >= questions.length) {
            ImageIcon icon = new ImageIcon("photos/tick.png");
            Image scaledIcon = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(scaledIcon);
            JOptionPane.showMessageDialog(frame, "Congratulations, you've completed Room 2!", "Room 2 Cleared", JOptionPane.PLAIN_MESSAGE, resizedIcon);
            System.out.println("Room: " + player1.getRoom());
            player1.setRoom(2);
            proceedToRoom3();
            return;
        }

        String[] question = questions[currentQuestionIndex];
        questionLabel.setText(question[0]);

        optionsPanel.removeAll(); // Clear previous options
        ImageIcon backgroundIcon = new ImageIcon("photos/WoodenMain.png"); // Load the PNG image
// Resize the icon if necessary
        Image img = backgroundIcon.getImage().getScaledInstance(350, 130, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);

        for (int i = 1; i < question.length; i++) {
            JButton optionButton = new JButton(question[i], resizedIcon); // Set the text
            optionButton.setFont(new Font("Arial", Font.BOLD, 18));
            optionButton.setIcon(resizedIcon); // Set the PNG background
            optionButton.setHorizontalTextPosition(JButton.CENTER); // Center text over the image
            optionButton.setVerticalTextPosition(JButton.CENTER); // Center text vertically
            optionButton.setForeground(Color.WHITE); // Set text color to white (adjust as needed)

            optionButton.setOpaque(false);
            optionButton.setContentAreaFilled(false);
            optionButton.setBorderPainted(false);
            optionButton.setFocusPainted(false);

            optionButton.setMargin(new Insets(0, 0, 0, 0));

            optionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (questionTimer != null) {
                        questionTimer.stop();
                    }
                    checkAnswer(e.getActionCommand());
                }
            });
            optionsPanel.add(optionButton);
        }
        
        resetAndStartTimer(); // Reset and start timer for new questions
        frame.validate();
        frame.repaint();
    }
    /**
     * Handles the logic for pausing the game, such as stopping timers.
     */
    public void pauseGame() {
        if (questionTimer != null) {
            questionTimer.stop(); // Stop the timer
            // Consider setting a flag if you need to differentiate between pausing and stopping for game logic
        }
    }
    /**
     * proceeds to the next room 
     */
    private void proceedToRoom3() {
        Room3Panel room3Panel = new Room3Panel(startFrame, frame, player1); // Initialize Room2Panel
        frame.setContentPane(room3Panel); // Set Room2Panel as the current content pane
        frame.revalidate();
        frame.repaint();
    }
    /**
     * Checks whether the selected answer is correct, updates game state, and moves to the next question or handles game over.
     *
     * @param selectedAnswer The answer selected by the player.
     */
    public void checkAnswer(String selectedAnswer) {
        if (selectedAnswer.equals(questions[currentQuestionIndex][answers[currentQuestionIndex]])) {
            currentQuestionIndex++;
            ImageIcon icon = new ImageIcon("photos/tick.png");
            Image scaledIcon = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(scaledIcon);
            JOptionPane.showMessageDialog(frame, "You have: " + lives + " lives left.", "Your answer is correct!", JOptionPane.PLAIN_MESSAGE, resizedIcon);
            player1.setQuestionsAnswered();
            player1.setTotaltime(15 - timeLeft);
            displayQuestion();
        } else {
            lives--;
            updateLivesDisplay(player1);
            if (lives <= 0) {
                JOptionPane.showMessageDialog(frame, "You've lost all your lives.", "Game Over", JOptionPane.ERROR_MESSAGE);
                // Show a button or action to return to the main menu
                player1.setTotaltime(15 - timeLeft);
                System.out.println("Time : "+player1.getTotaltime());
                gameOver();
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong answer. You have " + lives + " lives left.", "Try Again", JOptionPane.ERROR_MESSAGE);
                displayQuestion(); // Optionally reset the current question or proceed to next
            }
        }
    }


    /**
     * Resets and starts the timer for the current question.
     */
    private void resetAndStartTimer() {
        if (questionTimer != null) {
            questionTimer.stop(); // Stop the existing timer
        }
        timeLeft = 15; // Reset time for a new question
        timerLabel.setText("Time left: " + timeLeft); // Update the timer label immediately
        timerInitialized = false; // Allow timer to be reinitialized
        startTimer(); // Start or resume the timer
    }
    /**
     * Starts or resumes the question timer.
     */
    private void startTimer() {
        if (!timerInitialized) {
            questionTimer = new Timer(1000, e -> {
                timeLeft--;
                timerLabel.setText("Time left: " + timeLeft);
                if (timeLeft <= 0) {
                    questionTimer.stop();
                    timerInitialized = false;
                    onTimeOut();
                }
            });
            questionTimer.start();
            timerInitialized = true;
        }
    }
    /**
     * Handles the event when the time for a question runs out.
     * Decreases the lives and goes to the next question or ends the game
     * if the number of lives is lesser than 0
     */
    private void onTimeOut() {
        lives--;
        updateLivesDisplay(player1);
        if (lives <= 0) {
            gameOver();
        } else {
            JOptionPane.showMessageDialog(frame, "Time's up! You have " + lives + " lives left.", "Hurry Up", JOptionPane.WARNING_MESSAGE);
             // Move to the next question if time runs out
            displayQuestion(); // Display the next question
        }
    }

    /**
     * Updates the display of lives on the UI.
     *
     * @param player1 The player instance to update lives for.
     */
    public static void updateLivesDisplay(Player player1) {
    	player1.setLives(lives);
    	livesLabel.setText("Lives: " + lives + "  "); // Update the lives label
        
        
    }
    /**
     * Handles the game over logic, such as stopping timers and showing a game over message.
     * Exports player data to the score and complete csv files 
     */
    private void gameOver() {
        // Stop the timer to avoid multiple pop-ups or actions
    	System.out.println(player1.getQuestionsAnswered());
        if (questionTimer != null) {
            questionTimer.stop();
        }
        updateLivesDisplay(player1);

        JOptionPane.showMessageDialog(startFrame, "Game Over! You've lost all your lives.", "Game Over", JOptionPane.ERROR_MESSAGE);
        Score score=new Score(player1);
        System.out.print(score.toString());
        startFrame.showMainPanel();
        score.exportPlayerToCSV(score.toString(), "./score.csv");
        score.exportPlayerToCSV(score.toString2(), "./complete.csv");
        try {
			score.removeDataFromCSV(playerName);
		} catch (CsvValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}