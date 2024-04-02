import java.awt.*;

/**
 * Singleton class for managing custom cursors in a Java GUI application.
 */
public class CursorManager {
    // Singleton instance of CursorManager
    private static final CursorManager instance = new CursorManager();

    // Cursors for different purposes
    private Cursor defaultCursor;
    private Cursor magnifyingGlassCursor;
    private Cursor fingerCursor;
    private Cursor keyCursor;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * Initializes the custom cursors.
     */
    private CursorManager() {
        // Initialize cursors here
        defaultCursor = createCustomCursor("photos/default.png", new Point(0, 0), "defaultCursor");
        magnifyingGlassCursor = createCustomCursor("photos/mg1.png", new Point(0, 0), "magnifyingGlassCursor");
        fingerCursor = createCustomCursor("photos/finger.png", new Point(0, 0), "fingerCursor");
        keyCursor = createCustomCursor("photos/key.png", new Point(0, 0), "keyCursor");
    }

    /**
     * Returns the singleton instance of CursorManager.
     * @return The singleton instance of CursorManager.
     */
    public static CursorManager getInstance() {
        return instance;
    }

    /**
     * Creates a custom cursor from the specified image file.
     * @param imagePath The path to the image file for the cursor.
     * @param hotspot The hotspot point of the cursor.
     * @param cursorName The name of the cursor.
     * @return The custom cursor created from the image file.
     */
    private Cursor createCustomCursor(String imagePath, Point hotspot, String cursorName) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(imagePath);
        Image scaledImage = image.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        return toolkit.createCustomCursor(scaledImage, hotspot, cursorName);
    }

    /**
     * Retrieves the cursor based on the specified cursor name.
     * @param cursorName The name of the cursor to retrieve.
     * @return The cursor corresponding to the specified name.
     */
    public Cursor getCursor(String cursorName) {
        switch (cursorName) {
            case "Magnifying glass":
                return magnifyingGlassCursor;
            case "Finger":
                return fingerCursor;
            case "Key":
                return keyCursor;
            default:
                return defaultCursor;
        }
    }
}
