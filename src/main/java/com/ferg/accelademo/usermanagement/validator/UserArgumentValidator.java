package com.ferg.accelademo.usermanagement.validator;

import com.ferg.accelademo.usermanagement.dto.AddCommand;
import com.ferg.accelademo.usermanagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Scanner;

@Component
public class UserArgumentValidator implements Validator {

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(UserArgumentValidator.class);

    public boolean validateCreateUser(String args[]){

    }

    public boolean validateUpdateUser(String args[]){
        boolean isValid = false;
        if(validateExpectedArgumentNumber(args, 4)){
            if(validateUserIdFormat(args[1])){
                long userId = Long.parseLong(args[1]);
                if (validateUserExists(userId)){
                    isValid = true;
                }
            }
        }
        return isValid;
    }

    public boolean validateDeleteUser(String args[]){
        boolean isValid = false;
        if(validateExpectedArgumentNumber(args, 2)) {
            if(validateUserIdFormat(args[1])) {
                long userId = Long.parseLong(args[1]);
                if(validateUserExists(userId)){
                    isValid = true;
                }
            }
        }
        return isValid;
    }

    public boolean validateNoUserExists(long userId) {
        boolean isValid = true;
        if (userService.userExists(userId)) {
            System.out.println(String.format("User already exist with the id %d", userId));
            isValid = false;
        }
        return isValid;
    }

    public boolean validateUserExists(String userId) {
        boolean isValid = true;
        long id = Long.parseLong(userId);
        if (!userService.userExists(id)) {
            System.out.println(String.format("User does not exist with the id %d", id));
            isValid = false;
        }
        return isValid;
    }

    public boolean validateCountUsers(String args[]){
        return validateExpectedArgumentNumber(args, 1);
    }

    public boolean validateListUsers(String args[]){
        return validateExpectedArgumentNumber(args, 1);
    }

    public boolean validateExpectedArgumentNumber(String[] args, int expected) {
        boolean isValid = true;
        if (args.length > expected) {
            System.out.println("Too many arguments supplied");
            isValid = false;
        } else if (args.length < expected) {
            System.out.println("Too few arguments");
            isValid = false;
        }
        return isValid;
    }

    public boolean validateUserIdFormat(String userId, Errors errors){
        Scanner scanner = new Scanner(userId.trim());
        if(!scanner.hasNextLong()) {
            errors.reject("user.id.format");
            logger.info("Second argument must be a number representing the user id.");
        }
        return !errors.hasErrors();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AddCommand.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AddCommand command = (AddCommand) target;
        boolean isValid = false;
        if(validateExpectedArgumentNumber(command.getCommandArgs(), 4)) {
            if(validateUserIdFormat(command.getCommandArgs()[1])){
                long userId = Long.parseLong(command.getCommandArgs()[1]);
                if(validateNoUserExists(userId)){
                    isValid = true;
                }
            }
        }
        return isValid;
    }
}
