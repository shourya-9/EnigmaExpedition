import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class QuitPanel extends JPanel {

    public QuitPanel(Runnable onYes, Runnable onNo) {
        super(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Quit confirmation label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel quitLabel = new JLabel("QUIT GAME?", JLabel.CENTER);
        add(quitLabel, gbc);

        // YES button
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 10, 5);
        JButton yesButton = new JButton("YES");
        yesButton.addActionListener(e -> onYes.run());
        add(yesButton, gbc);

        // NO button
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 10, 10);
        JButton noButton = new JButton("NO");
        noButton.addActionListener(e -> onNo.run());
        add(noButton, gbc);
    }
}
