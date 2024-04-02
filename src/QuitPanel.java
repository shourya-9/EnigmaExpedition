import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
/**
 * A custom JPanel that provides a confirmation dialog for quitting the game.
 * 
 * The QuitPanel class extends JPanel and is designed to ask the user for confirmation
 * before quitting the game. It includes a "QUIT GAME?" label and two buttons: "YES" and "NO".
 * The actions for these buttons are defined by the Runnable parameters passed to the constructor.
 */
public class QuitPanel extends JPanel {
	/**
     * Constructs a new QuitPanel with specified actions for the YES and NO buttons.
     * 
     * @param onYes The action to be performed when the YES button is clicked.
     * @param onNo  The action to be performed when the NO button is clicked.
     * 
     * The panel uses a GridBagLayout to arrange its components. A confirmation label is
     * displayed at the top, and two buttons are displayed below it. The YES button, when
     * clicked, will execute the onYes Runnable, and the NO button will execute the onNo Runnable.
     */
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
