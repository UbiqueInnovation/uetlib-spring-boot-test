package ch.ubique.backend.test.enabledif;

import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.ubique.backend.test.tracker.TestExecutionTracker;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("profile2")
@ExtendWith(TestExecutionTracker.class)
class EnabledIfProfileShouldRunWithDevOrMongoTest {

    @BeforeAll
    static void resetExecutionTracker() {
        TestExecutionTracker.reset();
    }

    @Test
    @EnabledIfProfile("profile1 | profile2")
    void devTest() {}

    @AfterAll
    static void verifyExecuted() {
        assertTrue(
                TestExecutionTracker.wasExecutedByMethodName("devTest"),
                "devTest should have executed");
    }
}
