package com.ferg.accelademo.usermanagement.service.command;

import com.ferg.accelademo.usermanagement.service.UserService;
import com.ferg.accelademo.usermanagement.validator.UserArgumentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(CommandType.ADD_BEAN_NAME)
public class AddUserCommandService implements CommandService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserArgumentValidator userArgumentValidator;

    @Override
    public void execute() {
        if(userArgumentValidator.validateCreateUser(args)) {
            Long userId = Long.parseLong(args[1]);
            System.out.println(String.format("Creating user with id %d", userId));
            userService.createUser(userId, args[2], args[3]);
        }
    }
}
