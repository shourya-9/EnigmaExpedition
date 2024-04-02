import java.awt.*;

public class Hand {
    /**
     * Sets the cursor for the specified component to a hand cursor.
     * @param component The component to change the cursor for.
     */
    public static void setHandCursor(Component component) {
        component.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
