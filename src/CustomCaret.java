import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class CustomCaret extends DefaultCaret {
    public CustomCaret() {
        setBlinkRate(500); // Blink rate in milliseconds
    }
}