package ch.uetlib.spring.boot.test.enabledif;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.core.env.Profiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public class EnabledIfSpringProfileCondition implements ExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(final ExtensionContext context) {
        final var annotation =
                context.getElement().map(el -> el.getAnnotation(EnabledIfProfile.class));

        if (annotation.isEmpty()) {
            return ConditionEvaluationResult.enabled("No @EnabledIfProfile present");
        }

        final var expression = annotation.get().value();

        try {
            final var applicationContext = SpringExtension.getApplicationContext(context);
            final var environment = applicationContext.getEnvironment();

            if (environment.acceptsProfiles(Profiles.of(expression))) {
                return ConditionEvaluationResult.enabled(
                        "Spring profile expression matched: " + expression);
            }
            return ConditionEvaluationResult.disabled(
                    "Spring profile expression did not match: " + expression);
        } catch (final Exception e) {
            return ConditionEvaluationResult.disabled(
                    "Could not evaluate profile expression: " + e.getMessage());
        }
    }
}
