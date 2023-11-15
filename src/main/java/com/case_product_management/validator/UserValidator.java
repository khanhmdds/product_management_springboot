package com.case_product_management.validator;

import com.case_product_management.model.User;
import com.case_product_management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private IUserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        // check email used ?
        if (userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "error.email", "Email already taken!");
        }
        // check match pass
        if (!
        user.getConfirmPassword().equals(user.getPassword())){
            errors.rejectValue("confirmPassword",
                    "error.confirmPassword", "Confirm password is not match");
        }
    }
}
