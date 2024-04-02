import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel for displaying and managing quiz questions during gameplay.
 */
public class GamePanel extends JPanel {
    private int currentQuestionIndex = 0; // Index of the current question
    private int lives = 3; // Number of lives remaining
    private Timer questionTimer; // Timer for each question
    private final String[][] questions; // Array of questions and options
    private final String[] answers; // Array of correct answers
    private final JFrame frame; // Main frame of the application
    private final JPanel mainPanel; // Main panel of the application

    /**
     * Constructs the GamePanel with the specified parameters.
     * @param frame The main frame of the application.
     * @param mainPanel The main panel of the application.
     * @param questions Array of questions and options.
     * @param answers Array of correct answers.
     */
    public GamePanel(JFrame frame, JPanel mainPanel, String[][] questions, String[] answers) {
        this.frame = frame;
        this.mainPanel = mainPanel;
        this.questions = questions; // Each item: {question, optionA, optionB, optionC, optionD}
        this.answers = answers; // Each item: correct option letter (e.g., "A")
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Vertical layout for displaying questions
        displayQuestion(); // Display the first question
    }

    /**
     * Displays the current question on the panel.
     */
    public void displayQuestion() {
        removeAll(); // Clear the panel for the next question
        if (currentQuestionIndex >= questions.length) {
            proceedToNextRoom(); // Proceed to the next room if all questions are answered
            return;
        }
        String[] question = questions[currentQuestionIndex];
        JLabel questionLabel = new JLabel("<html><body style='width: 200px'>" + question[0] + "</body></html>"); // HTML formatting for multiline questions
        add(questionLabel); // Add question label to the panel

        // ActionListener for processing user answers
        ActionListener answerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (questionTimer != null) {
                    questionTimer.stop();
                }
                if (e.getActionCommand().equals(answers[currentQuestionIndex])) { // Correct answer
                    currentQuestionIndex++; // Move to the next question
                    displayQuestion(); // Display the next question
                } else { // Incorrect answer
                    lives--; // Decrease the number of lives
                    if (lives <= 0) { // Game over, lost all lives
                        JOptionPane.showMessageDialog(frame, "You've lost all 3 lives.", "Game Over", JOptionPane.ERROR_MESSAGE);
                        // Prompt to return to the main menu
                        int result = JOptionPane.showConfirmDialog(frame, "Return to main menu?", "Game Over", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            frame.setContentPane(mainPanel); // Set main panel as the current content pane
                            frame.revalidate(); // Revalidate the frame
                        }
                    } else {
                        displayQuestion(); // Redisplay current question or proceed to the next question
                    }
                }
            }
        };

        // Add option buttons for each option of the question
        for (int i = 1; i < question.length; i++) {
            JButton optionButton = new JButton(question[i]);
            optionButton.setActionCommand(String.valueOf((char)('A' + i - 1))); // Assign action command as option letter (e.g., "A")
            optionButton.addActionListener(answerListener); // Add ActionListener to process user answer
            add(optionButton); // Add option button to the panel
        }

        startTimer(); // Start the timer for the question
        revalidate(); // Revalidate the panel to update changes
        repaint(); // Repaint the panel to reflect changes
    }

    /**
     * Starts the timer for the current question.
     */
    private void startTimer() {
        if (questionTimer != null) {
            questionTimer.stop();
        }
        // Timer to handle the timeout for each question
        questionTimer = new Timer(15000, e -> {
            lives--; // Decrease the number of lives
            if (lives <= 0) { // Game over, lost all lives
                JOptionPane.showMessageDialog(frame, "Time's up! You've lost all 3 lives.", "Game Over", JOptionPane.ERROR_MESSAGE);
                // Prompt to return to the main menu
                int result = JOptionPane.showConfirmDialog(frame, "Return to main menu?", "Game Over", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    frame.setContentPane(mainPanel); // Set main panel as the current content pane
                    frame.revalidate(); // Revalidate the frame
                }
            } else {
                displayQuestion(); // Redisplay current question or proceed to the next question
            }
        });
        questionTimer.setRepeats(false); // Timer should not repeat
        questionTimer.start(); // Start the timer
    }

    /**
     * Method to be overridden by subclasses for proceeding to the next room.
     */
    public void proceedToNextRoom() {
        // Placeholder for override in subclasses
    }
}
