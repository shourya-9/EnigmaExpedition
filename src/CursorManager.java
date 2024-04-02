import java.awt.*;

public class CursorManager {
    private static final CursorManager instance = new CursorManager();

    private Cursor defaultCursor;
    private Cursor magnifyingGlassCursor;
    private Cursor fingerCursor;
    private Cursor keyCursor;

    private CursorManager() {
        // Initialize cursors here
        defaultCursor = createCustomCursor("photos/default.png", new Point(0, 0), "defaultCursor");
        magnifyingGlassCursor = createCustomCursor("photos/mg1.png", new Point(0, 0), "magnifyingGlassCursor");
        fingerCursor = createCustomCursor("photos/finger.png", new Point(0, 0), "fingerCursor");
        keyCursor = createCustomCursor("photos/key.png", new Point(0, 0), "keyCursor");
    }

    public static CursorManager getInstance() {
        return instance;
    }

    private Cursor createCustomCursor(String imagePath, Point hotspot, String cursorName) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(imagePath);
        Image scaledImage = image.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        return toolkit.createCustomCursor(scaledImage, hotspot, cursorName);
    }

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
