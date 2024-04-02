import javax.swing.*;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.awt.FontFormatException;

/**
 * Utility class for loading custom fonts in a Java Swing application.
 */
public class CustomFontLoader {

    /**
     * Retrieves a custom font from the specified file path and sets its size.
     * If the font fails to load, it falls back to a default font.
     * @param path The file path to the custom font.
     * @param size The size of the font to be retrieved.
     * @return The custom font if loaded successfully, or a default font if loading fails.
     */
    public static Font getFont(String path, float size) {
        Font customFont = null;
        if (customFont == null) {
            try {
                // Load the custom font
                customFont = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(size);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                // Register the font
                ge.registerFont(customFont);
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
                // Fallback to a default font if the custom font fails to load
                customFont = new JLabel().getFont().deriveFont(size);
            }
        }
        return customFont;
    }
}
