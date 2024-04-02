import javax.swing.JPanel;

public class GameState {
    private JPanel currentRoom;
    private int currentQuestionIndex;

    public GameState(JPanel currentRoom, int currentQuestionIndex) {
        this.currentRoom = currentRoom;
        this.currentQuestionIndex = currentQuestionIndex;
    }

    public JPanel getCurrentRoom() {
        return currentRoom;
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public void setCurrentRoom(JPanel currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void setCurrentQuestionIndex(int currentQuestionIndex) {
        this.currentQuestionIndex = currentQuestionIndex;
    }
}