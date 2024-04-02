import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame implements ActionListener {
    private int currentQuestionIndex = 0;
    private final List<Question> questions = new ArrayList<>();
    private final JLabel questionLabel = new JLabel();
    private final JPanel buttonPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    public Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setTitle("Escape Room Game");

        setLayout(new BorderLayout());

        // Example questions
        questions.add(new Question("What is the capital of France?", new String[]{"Berlin", "Paris", "Madrid", "Rome"}, 1));
        questions.add(new Question("What is 2 + 2?", new String[]{"3", "4", "22", "5"}, 1));

        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(questionLabel, BorderLayout.NORTH);

        buttonPanel.setLayout(cardLayout);
        add(buttonPanel, BorderLayout.CENTER);

        setupQuestionView();
    }

    private void setupQuestionView() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        questionLabel.setText(currentQuestion.question);
        buttonPanel.removeAll();

        JPanel panel = new JPanel(new GridLayout(2, 2));
        for (int i = 0; i < currentQuestion.options.length; i++) {
            JButton button = new JButton(currentQuestion.options[i]);
            button.addActionListener(this);
            button.setActionCommand(String.valueOf(i));
            panel.add(button);
        }

        buttonPanel.add(panel, "Question" + currentQuestionIndex);
        cardLayout.show(buttonPanel, "Question" + currentQuestionIndex);
        validate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedOption = Integer.parseInt(e.getActionCommand());
        Question currentQuestion = questions.get(currentQuestionIndex);

        if (selectedOption == currentQuestion.correctAnswer) {
            currentQuestionIndex++;
            if (currentQuestionIndex < questions.size()) {
                setupQuestionView();
            } else {
                JOptionPane.showMessageDialog(this, "Congratulations! You've completed the game!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Wrong answer. Try again!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }

    static class Question {
        String question;
        String[] options;
        int correctAnswer;

        public Question(String question, String[] options, int correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }
}