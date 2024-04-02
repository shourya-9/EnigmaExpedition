import javax.swing.*;
import java.awt.*;
/**
 * The {@code OutlineLabel} class extends {@link JLabel} to provide a label with an outline effect around its text.
 * This effect is achieved by painting the label's text multiple times in adjacent positions around the original text
 * position with a specified outline color, then painting the text itself over these in its normal color.
 */

public class OutlineLabel extends JLabel {

    private Color outlineColor = Color.black;
    private boolean isPaintingOutline = false;
    private boolean forceTransparent = false;
    /**
     * Constructs an empty {@code OutlineLabel}.
     */

    public OutlineLabel() {
        super();
    }
    /**
     * Constructs an {@code OutlineLabel} with the specified text.
     *
     * @param text The text to be displayed by the label.
     */

    public OutlineLabel(String text) {
        super(text);
    }
    /**
     * Constructs an {@code OutlineLabel} with the specified text and horizontal alignment.
     *
     * @param text The text to be displayed by the label.
     * @param horizontalAlignment One of the following constants defined in {@code SwingConstants}:
     *                            {@code LEFT}, {@code CENTER}, {@code RIGHT}, {@code LEADING}, or {@code TRAILING}.
     */

    public OutlineLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
    }
    /**
     * Returns the color used for the text outline.
     *
     * @return The {@code Color} used for the outline.
     */

    public Color getOutlineColor() {
        return outlineColor;
    }
    /**
     * Sets the color to be used for the text outline.
     *
     * @param outlineColor The {@code Color} to use for the outline.
     */

    public void setOutlineColor(Color outlineColor) {
        this.outlineColor = outlineColor;
        this.invalidate();
    }

    /**
     * Overrides {@code JLabel}'s {@code getForeground} method to use the outline color when painting the outline.
     *
     * @return The {@code Color} of the foreground text or the outline, depending on the painting context.
     */
    @Override

    public Color getForeground() {
        if ( isPaintingOutline ) {
            return outlineColor;
        } else {
            return super.getForeground();
        }
    }
    /**
     * Determines whether the label should be painted with an opaque background, overridden to support forced transparency.
     *
     * @return {@code true} if the label background is opaque and transparency is not forced, otherwise {@code false}.
     */

    @Override
    public boolean isOpaque() {
        if ( forceTransparent ) {
            return false;
        } else {
            return super.isOpaque();
        }
    }
    /**
     * Paints the label's text with an outline effect, then the text itself on top.
     *
     * @param g The {@code Graphics} object used for painting operations.
     */

    @Override
    public void paint(Graphics g) {

        String text = getText();
        if ( text == null || text.length() == 0 ) {
            super.paint(g);
            return;
        }

        // 1 2 3
        // 8 9 4
        // 7 6 5

        if ( isOpaque() )
            super.paint(g);

        forceTransparent = true;
        isPaintingOutline = true;
        g.translate(-1, -1); super.paint(g); // 1
        g.translate( 1,  0); super.paint(g); // 2
        g.translate( 1,  0); super.paint(g); // 3
        g.translate( 0,  1); super.paint(g); // 4
        g.translate( 0,  1); super.paint(g); // 5
        g.translate(-1,  0); super.paint(g); // 6
        g.translate(-1,  0); super.paint(g); // 7
        g.translate( 0, -1); super.paint(g); // 8
        g.translate( 1,  0); // 9
        isPaintingOutline = false;

        super.paint(g);
        forceTransparent = false;

    }
    /**
     * Main method for testing the {@code OutlineLabel}. Creates a window displaying a sample {@code OutlineLabel}.
     *
     * @param args Command-line arguments (not used).
     */

    public static void main(String[] args) {
        JFrame w = new JFrame();
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        OutlineLabel label = new OutlineLabel("Test", OutlineLabel.CENTER);
        label.setOpaque(true);
        w.setContentPane(new JPanel(new BorderLayout()));
        w.add(label, BorderLayout.CENTER);
        w.pack();
        w.setVisible(true);
    }
}