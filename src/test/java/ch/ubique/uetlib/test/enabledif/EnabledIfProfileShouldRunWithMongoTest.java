package ch.ubique.uetlib.test.enabledif;

import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.ubique.uetlib.test.tracker.TestExecutionTracker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("profile1")
@ExtendWith(TestExecutionTracker.class)
class EnabledIfProfileShouldRunWithMongoTest {

    @BeforeAll
    static void resetExecutionTracker() {
        TestExecutionTracker.reset();
    }

    @AfterAll
    static void verifyExecuted() {
        assertTrue(
                TestExecutionTracker.wasExecutedByMethodName("mongoTest"),
                "mongoTest should have executed");
    }

    @Test
    @EnabledIfProfile("profile1")
    void mongoTest() {}
}
