package trainingportal.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import trainingportal.model.User;

@Component
public class UpdateValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "userName", "error.userName", "Name can't be blank");
        ValidationUtils.rejectIfEmpty(errors, "email", "error.email", "Email can't be blank");
        ValidationUtils.rejectIfEmpty(errors, "enabled", "error.enabled", "Field can't be blank");
        ValidationUtils.rejectIfEmpty(errors, "roleId", "error.roleId", "Field can't be blank");
    }
}
