import javax.swing.JPanel;

/**
 * Represents the state of the game, including the current room panel and the index of the current question.
 */
public class GameState {
    private JPanel currentRoom; // The panel representing the current room
    private int currentQuestionIndex; // The index of the current question

    /**
     * Constructs a GameState object with the specified current room panel and current question index.
     * @param currentRoom The panel representing the current room.
     * @param currentQuestionIndex The index of the current question.
     */
    public GameState(JPanel currentRoom, int currentQuestionIndex) {
        this.currentRoom = currentRoom;
        this.currentQuestionIndex = currentQuestionIndex;
    }

    /**
     * Retrieves the panel representing the current room.
     * @return The current room panel.
     */
    public JPanel getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Retrieves the index of the current question.
     * @return The index of the current question.
     */
    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    /**
     * Sets the panel representing the current room.
     * @param currentRoom The panel representing the current room.
     */
    public void setCurrentRoom(JPanel currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Sets the index of the current question.
     * @param currentQuestionIndex The index of the current question.
     */
    public void setCurrentQuestionIndex(int currentQuestionIndex) {
        this.currentQuestionIndex = currentQuestionIndex;
    }
}
