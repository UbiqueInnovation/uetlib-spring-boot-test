package ch.ubique.backend.test.enabledif;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import ch.ubique.backend.test.tracker.TestExecutionTracker;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles({"profile1", "profile2"})
@ExtendWith(TestExecutionTracker.class)
class EnabledIfProfileShouldNotRunWithDevTest {

    @BeforeAll
    static void resetExecutionTracker() {
        TestExecutionTracker.reset();
    }

    @AfterAll
    static void verifyNotExecuted() {
        assertFalse(
                TestExecutionTracker.wasExecutedByMethodName("blockedByDevTest"),
                "blockedByDevTest should NOT have executed");
    }

    @Test
    @EnabledIfProfile("profile1 & !profile2")
    void blockedByDevTest() {
        fail("This test should have been disabled");
    }
}
