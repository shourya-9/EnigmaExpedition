import javax.swing.JButton;
import java.awt.*;
import java.awt.geom.Ellipse2D;
/**
 * The {@code RoundButton} class extends {@link JButton} to create a button with rounded corners.
 * This custom button enhances the UI by providing a visually appealing alternative to the
 * standard rectangular button. The rounded corners are defined by {@code ARC_WIDTH} and
 * {@code ARC_HEIGHT} constants.
 */

class RoundButton extends JButton {
    private static final int ARC_WIDTH = 30; // Adjust for rounded corner width
    private static final int ARC_HEIGHT = 30; // Adjust for rounded corner height
    /**
     * Constructs a {@code RoundButton} with the specified label.
     * 
     * @param label The text to be displayed on the button.
     */

    public RoundButton(String label) {
        super(label);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false); // You might want to paint your own border in paintBorder
    }

    /**
     * Overrides the {@code paintComponent} method to draw the button with rounded corners
     * and a custom fill color that changes when the button is pressed.
     * 
     * @param g The {@link Graphics} object used for drawing operations.
     */
    @Override

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (getModel().isArmed()) {
            // Button is pressed
            g2.setColor(Color.ORANGE.darker());
        } else {
            // Button is in normal state
            g2.setColor(Color.ORANGE);
        }
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);
        super.paintComponent(g2);
        g2.dispose();
    }

    /**
     * Overrides the {@code paintBorder} method to draw a custom border for the button
     * with rounded corners. The border color matches the button's foreground color.
     * 
     * @param g The {@link Graphics} object used for drawing operations.
     */
    @Override

    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);
        g2.dispose();
    }
}

