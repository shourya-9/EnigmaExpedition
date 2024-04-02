import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class SaveTest {
    private Player player;
    private Score score;
    private File tempFile;

    @BeforeEach
    void setUp(@TempDir Path tempDir) throws IOException {
        // Initialize player with sample data
        player = new Player("Test Player", 3);
        player.setQuestionsAnswered(); // Simulating answering a question correctly
        player.setTotaltime(25); // Assuming the player took 100 units of time
        player.setLives(2); // Assuming the player ended the game with 2 lives

        // Create a temporary CSV file for testing
        tempFile = tempDir.resolve("testScore.csv").toFile();
        
        // Initialize score with the player
        score = new Score(player);
    }

    @Test
    void testExportPlayerToCSV() throws IOException {
        // Export the player's score to the CSV file
        Score.exportPlayerToCSV(score.toString2(), tempFile.getAbsolutePath());
        
        // Read the content from the CSV file
        String content = new String(java.nio.file.Files.readAllBytes(tempFile.toPath()));
        
        // Verify that the CSV file contains the player's data
        assertTrue(content.contains(player.getName()), "CSV should contain the player's name");
        assertTrue(content.contains(String.valueOf(score.computeScore())), "CSV should contain the correct score");
        assertTrue(content.contains(String.valueOf(player.getTotaltime())), "CSV should contain the total time");
        assertTrue(content.contains(String.valueOf(player.getLives())), "CSV should contain the lives count");
        assertTrue(content.contains(String.valueOf(player.getRoom())), "CSV should contain the room completed");
    }

    @AfterEach
    void tearDown() {
        // Cleanup is handled by the @TempDir annotation
    }
}
