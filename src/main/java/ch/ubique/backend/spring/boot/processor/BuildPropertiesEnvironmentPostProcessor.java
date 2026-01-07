package ch.ubique.backend.spring.boot.processor;

import org.jspecify.annotations.NonNull;
import org.springframework.boot.EnvironmentPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * This class is a noop. It just exists to override the BuildPropertiesEnvironmentPostProcessor in
 * src/main, as that one should not run during test execution.
 */
public class BuildPropertiesEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(
            final @NonNull ConfigurableEnvironment environment,
            final @NonNull SpringApplication application) {
        // noop, this just overrides BuildPropertiesEnvironmentPostProcessor in src/main
    }
}
