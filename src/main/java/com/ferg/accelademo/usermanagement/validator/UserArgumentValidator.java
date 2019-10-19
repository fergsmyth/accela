package com.ferg.accelademo.usermanagement.validator;

import com.ferg.accelademo.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserArgumentValidator {

    @Autowired
    private UserService userService;

    public boolean validateCreateUser(String args[]){
        boolean isValid = false;
        if(validateExpectedArgumentNumber(args, 4)) {
            if(validateUserIdFormat(args[1])){
                long userId = Long.parseLong(args[1]);
                if(validateNoUserExists(userId)){
                    isValid = true;
                }
            }
        }
        return isValid;
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

    public boolean validateUserExists(long userId) {
        boolean isValid = true;
        if (!userService.userExists(userId)) {
            System.out.println(String.format("User does not exist with the id %d", userId));
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

    private boolean validateUserIdFormat(String userId){
        boolean isValid = true;
        Scanner scanner = new Scanner(userId.trim());
        if(!scanner.hasNextLong()) {
            System.out.println(String.format("Second argument must be a number representing the user id."));
            isValid = false;
        }
        return isValid;
    }
}
