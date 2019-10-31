package com.ferg.accelademo.usermanagement.validator;

import com.ferg.accelademo.usermanagement.dto.AddCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AddUserValidator implements Validator {

    @Autowired
    private UserArgumentValidator userArgumentValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return AddCommand.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AddCommand addCommand = (AddCommand) target;
        String userId = addCommand.getCommandArgs()[1];
        userArgumentValidator.validateUserIdFormat(userId, errors);
        userArgumentValidator.validateUserExists(userId, errors);
    }
}
