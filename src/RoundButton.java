import javax.swing.JButton;
import java.awt.*;
import java.awt.geom.Ellipse2D;

class RoundButton extends JButton {
    private static final int ARC_WIDTH = 30; // Adjust for rounded corner width
    private static final int ARC_HEIGHT = 30; // Adjust for rounded corner height

    public RoundButton(String label) {
        super(label);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false); // You might want to paint your own border in paintBorder
    }

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

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);
        g2.dispose();
    }
}

