package ch.uetlib.spring.boot.test.enabledif;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import ch.uetlib.test.tracker.TestExecutionTracker;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("profile3")
@ExtendWith(TestExecutionTracker.class)
class EnabledIfProfileShouldNotRunWithoutDevOrMongoTest {

    @BeforeAll
    static void resetExecutionTracker() {
        TestExecutionTracker.reset();
    }

    @AfterAll
    static void verifyNotExecuted() {
        assertFalse(
                TestExecutionTracker.wasExecutedByMethodName("noMatchTest"),
                "noMatchTest should NOT have executed");
    }

    @Test
    @EnabledIfProfile("profile1 | profile2")
    void noMatchTest() {
        fail("This test should have been disabled");
    }
}
