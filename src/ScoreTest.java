import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {
    private Player testPlayer;
    private Score testScore;

    @BeforeEach
    void setUp() {
        // Mock a player with specific attributes
        testPlayer = new Player("Test Player", 3); // Assuming such a constructor
        testPlayer.applyQuestionsAnswered(10); // Assuming 10 questions answered correctly
        testPlayer.setTotaltime(50); // Assuming total time taken is 50
        testPlayer.setLives(2); // Assuming 2 lives remaining
        testPlayer.setRoom(1); // Assuming player is in room 1
        
        testScore = new Score(testPlayer);
    }

    @Test
    void testComputeScore() {
        // The actual score calculation based on the provided formula
        int expectedScore = 36;
        System.out.println("e"+expectedScore);
        System.out.println(testScore.computeScore());
        System.out.println(testPlayer.getTotaltime());
   
        assertEquals(expectedScore, testScore.computeScore(), "Computed score should match expected value");
    }

    @Test
    void testToString() {
        // Example of testing the toString method
        int computedScore = testScore.computeScore(); // Compute the score first
        String expectedString = "Test Player," + computedScore + ",50";
        assertEquals(expectedString, testScore.toString(), "toString output should match expected format");
    }

    @Test
    void testToString2() {
        // Example of testing the toString2 method for additional details
        int computedScore = testScore.computeScore(); // Compute the score first
        String expectedString2 = "Test Player," + computedScore + ",50,1,2";
        assertEquals(expectedString2, testScore.toString2(), "toString2 output should match expected format with additional details");
    }

    // Additional tests can be added here for further functionalities
    
    // Mock implementations or adjustments to the setup may be necessary depending on the actual Player and Score class implementations
}
