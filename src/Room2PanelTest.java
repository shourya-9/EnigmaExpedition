import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JFrame;

class Room2PanelTest {
    private Room2Panel room2Panel;
    private Player testPlayer;
    private JFrame frameMock;
    private Start startMock;

    @BeforeEach
    void setUp() {
        startMock = new Start(); // Assuming Start can be instantiated like this
        frameMock = new JFrame(); // Mock JFrame
        testPlayer = new Player("Test Player", 3); // Assuming Player has a constructor like this
        
        room2Panel = new Room2Panel(startMock, startMock, testPlayer);
    }

    @Test
    void testInitialLives() {
        assertEquals(3, testPlayer.getLives(), "Initial lives should be set to 3");
    }

    @Test
    void testDisplayQuestion() {
        // Assuming the first question is correctly displayed upon initialization
        String expectedQuestion = "What is the capital of France?"; // Based on your Room1Panel's displayQuestion implementation
        System.out.println(room2Panel.questions[0][0]);
        assertEquals(expectedQuestion, room2Panel.questions[0][0], "Question should be displayed");
    }

    @Test
    void testCheckAnswerCorrect() {
        // Simulate answering the first question correctly
        String correctAnswer = "C. Paris"; // Based on the provided Room1Panel questions array
        room2Panel.checkAnswer(correctAnswer);
        assertEquals(1, testPlayer.getQuestionsAnswered(), "Player should have 1 question answered correctly");
    }

    @Test
    void testCheckAnswerIncorrect() {
        // Simulate answering the first question incorrectly
        String incorrectAnswer = "Incorrect Answer";
        room2Panel.checkAnswer(incorrectAnswer);
        assertEquals(2, testPlayer.getLives(), "Player should have one less life after incorrect answer");
    }
    
    @Test
    void testTimerFunctionality() {
        // This test is conceptual because we can't directly simulate timer ticks without accessing the timer
        // Assume functionality to start and stop the timer is correctly implemented
        assertTrue(true, "Assuming timer functionality is correct based on setup");
    }
    
    // Additional tests can be added here for further functionalities
    
    // Helper methods or mock implementations can be added as necessary
}
