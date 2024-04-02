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
            {"What is the capital of France?", "A. Berlin", "B. Madrid", "C. Paris", "D. Lisbon"},
            {"What is 2 + 2?", "A. 3", "B. 4", "C. 22", "D. 5"},
            {"Who wrote 'Hamlet'?", "A. Charles Dickens", "B. William Shakespeare", "C. Leo Tolstoy", "D. Mark Twain"},
            {"What is the largest planet in our solar system?", "A. Earth", "B. Jupiter", "C. Mars", "D. Neptune"},
            {"What is the chemical symbol for gold?", "A. Au", "B. Ag", "C. Fe", "D. O"}
        };
    public int[] answers = {3,2,2,2,1};
    public Room2Panel(Start startFrame,JFrame frame, Player player1) {
    	this.startFrame = startFrame;
        this.frame = frame;
        this.player1 = player1;
        this.playerName = player1.getName();
        this.lives = player1.getLives();
        System.out.println(lives);
        setupRoom();
    }

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

    // A method to create a rounded border if desired
    private class RoundedBorder implements Border {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        public boolean isBorderOpaque() {
            return false;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
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
    private void openSettingsPanel() {
        GameSettingsPanel settingsPanel = new GameSettingsPanel(startFrame,frame,this,player1);
        frame.setContentPane(settingsPanel);
        frame.revalidate();
        frame.repaint();
    }
    
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
    public void pauseGame() {
        if (questionTimer != null) {
            questionTimer.stop(); // Stop the timer
            // Consider setting a flag if you need to differentiate between pausing and stopping for game logic
        }
    }
    
    private void proceedToRoom3() {
        Room3Panel room3Panel = new Room3Panel(startFrame, frame, player1); // Initialize Room2Panel
        frame.setContentPane(room3Panel); // Set Room2Panel as the current content pane
        frame.revalidate();
        frame.repaint();
    }
    
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


 // Room1Panel
    private void resetAndStartTimer() {
        if (questionTimer != null) {
            questionTimer.stop(); // Stop the existing timer
        }
        timeLeft = 15; // Reset time for a new question
        timerLabel.setText("Time left: " + timeLeft); // Update the timer label immediately
        timerInitialized = false; // Allow timer to be reinitialized
        startTimer(); // Start or resume the timer
    }

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

    
    public static void updateLivesDisplay(Player player1) {
    	player1.setLives(lives);
    	livesLabel.setText("Lives: " + lives + "  "); // Update the lives label
        
        
    }

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