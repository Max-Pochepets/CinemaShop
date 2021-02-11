package cinema.shop.lib.validators;


import cinema.shop.lib.annotations.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordValidator implements ConstraintValidator<ValidPassword, Object> {
    private String password;
    private String repeatPassword;

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        this.password = constraintAnnotation.field();
        this.repeatPassword = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        Object passwordValue = new BeanWrapperImpl(object)
                .getPropertyValue(password);
        Object repeatPasswordValue = new BeanWrapperImpl(object)
                .getPropertyValue(repeatPassword);

        if (passwordValue == null) {
            return false;
        }
        return passwordValue.equals(repeatPasswordValue);
    }
}
