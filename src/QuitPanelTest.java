import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

class QuitPanelTest {

    private boolean actionPerformed;
    private QuitPanel quitPanel;

    @BeforeEach
    void setUp() {
        actionPerformed = false;
        quitPanel = new QuitPanel(() -> actionPerformed = true, () -> actionPerformed = false);
    }

    @Test
    void testYesButtonAction() {
        findButtonAndClick("YES");
        assertTrue(actionPerformed, "Yes action should set actionPerformed to true");
    }

    @Test
    void testNoButtonAction() {
        findButtonAndClick("NO");
        assertFalse(actionPerformed, "No action should set actionPerformed to false");
    }

    private void findButtonAndClick(String buttonText) {
        for (Component comp : quitPanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                if (button.getText().equalsIgnoreCase(buttonText)) {
                    button.doClick();
                    break;
                }
            }
        }
    }
}

