import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class LoadGameTest {

    private static final Path TEST_CSV_PATH = Paths.get("./incomplete.csv");
    private static final String TEST_DATA = "TestPlayer,1,3,10,100";

    @BeforeEach
    void setUp() throws Exception {
        // Ensure any existing file is backed up and create a new one for testing
        if (Files.exists(TEST_CSV_PATH)) {
            Files.copy(TEST_CSV_PATH, TEST_CSV_PATH.resolveSibling("incomplete_backup.csv"));
            Files.deleteIfExists(TEST_CSV_PATH);
        }
        Files.write(TEST_CSV_PATH, TEST_DATA.getBytes());
    }

    @Test
    void testPlayerDataLoadedCorrectly() {
        Start startMock = new Start(); // Adjust this to your Start class instantiation
        loadGame loadGame = new loadGame("TestPlayer", startMock);
        assertNotNull(loadGame.getPlayerData(), "Player data should be loaded");
        assertTrue(loadGame.getPlayerData().contains("TestPlayer"), "Loaded data should contain 'TestPlayer'");
    }

    @AfterEach
    void tearDown() throws Exception {
        // Cleanup: Delete the test file and restore the backup if it existed
        Files.deleteIfExists(TEST_CSV_PATH);
        Path backupPath = TEST_CSV_PATH.resolveSibling("incomplete_backup.csv");
        if (Files.exists(backupPath)) {
            Files.move(backupPath, TEST_CSV_PATH);
        }
    }
}
