import javax.swing.*;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.awt.FontFormatException;

public class CustomFontLoader {

    //private Font customFont = null;

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