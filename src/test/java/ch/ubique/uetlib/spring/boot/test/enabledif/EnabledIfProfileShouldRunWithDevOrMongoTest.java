package ch.ubique.uetlib.spring.boot.test.enabledif;

import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.ubique.uetlib.test.tracker.TestExecutionTracker;

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

    @AfterAll
    static void verifyExecuted() {
        assertTrue(
                TestExecutionTracker.wasExecutedByMethodName("devTest"),
                "devTest should have executed");
    }

    @Test
    @EnabledIfProfile("profile1 | profile2")
    void devTest() {}
}
