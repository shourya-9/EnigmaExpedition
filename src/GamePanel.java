import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    private int currentQuestionIndex = 0;
    private int lives = 3;
    private Timer questionTimer;
    private final String[][] questions;
    private final String[] answers;
    private final JFrame frame;
    private final JPanel mainPanel;

    public GamePanel(JFrame frame, JPanel mainPanel, String[][] questions, String[] answers) {
        this.frame = frame;
        this.mainPanel = mainPanel;
        this.questions = questions; // Each item: {question, optionA, optionB, optionC, optionD}
        this.answers = answers; // Each item: correct option letter (e.g., "A")
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        displayQuestion();
    }

    public void displayQuestion() {
        removeAll(); // Clear the panel for the next question
        if (currentQuestionIndex >= questions.length) {
            proceedToNextRoom(); // Now calls an overridable method
            return;
        }
        String[] question = questions[currentQuestionIndex];
        OutlineLabel questionLabel = new OutlineLabel("<html><body style='width: 200px'>" + question[0] + "</body></html>");
        add(questionLabel);

        ActionListener answerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (questionTimer != null) {
                    questionTimer.stop();
                }
                if (e.getActionCommand().equals(answers[currentQuestionIndex])) {
                    currentQuestionIndex++;
                    displayQuestion();
                } else {
                    lives--;
                    if (lives <= 0) {
                        // Game over, lost all lives
                        JOptionPane.showMessageDialog(frame, "You've lost all 3 lives.", "Game Over", JOptionPane.ERROR_MESSAGE);
                        // Add button to return to main menu
                        int result = JOptionPane.showConfirmDialog(frame, "Return to main menu?", "Game Over", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            frame.setContentPane(mainPanel);
                            frame.revalidate();
                        }
                    } else {
                        displayQuestion(); // Redisplay current question or next question logic
                    }
                }
            }
        };

        for (int i = 1; i < question.length; i++) {
            JButton optionButton = new JButton(question[i]);
            optionButton.setActionCommand(String.valueOf((char)('A' + i - 1)));
            optionButton.addActionListener(answerListener);
            add(optionButton);
        }

        startTimer();
        revalidate();
        repaint();
    }

    private void startTimer() {
        if (questionTimer != null) {
            questionTimer.stop();
        }
        questionTimer = new Timer(15000, e -> {
            lives--;
            if (lives <= 0) {
                // Handle game over due to timer
                JOptionPane.showMessageDialog(frame, "Time's up! You've lost all 3 lives.", "Game Over", JOptionPane.ERROR_MESSAGE);
                // Return to main menu code here, similar to above
                int result = JOptionPane.showConfirmDialog(frame, "Return to main menu?", "Game Over", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    frame.setContentPane(mainPanel);
                    frame.revalidate();
                }
            } else {
                displayQuestion(); // Move to the next question or repeat logic
            }
        });
        questionTimer.setRepeats(false);
        questionTimer.start();
    }
    public void proceedToNextRoom() {
        // Placeholder for override in subclasses
    }
}
