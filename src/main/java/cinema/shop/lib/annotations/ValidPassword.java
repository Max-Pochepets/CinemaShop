package cinema.shop.lib.annotations;

import cinema.shop.lib.validators.PasswordValidator;
import jakarta.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Invalid password repetition. Please try again.";

    String field();

    String fieldMatch();
}
