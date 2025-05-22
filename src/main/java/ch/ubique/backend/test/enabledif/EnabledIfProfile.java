package ch.ubique.backend.test.enabledif;

import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Extension of {@link Profile} that also enables the test if the specified Spring profile is
 * active. Works like {@link EnabledIf} but for spring profile in a {@link SpringBootTest}.
 *
 * @see Profile for the expression syntax
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(EnabledIfSpringProfileCondition.class)
public @interface EnabledIfProfile {
    String value();
}
