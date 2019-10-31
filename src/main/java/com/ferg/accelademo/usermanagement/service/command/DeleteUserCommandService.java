package com.ferg.accelademo.usermanagement.service.command;

import org.springframework.stereotype.Service;

@Service(CommandType.DELETE_BEAN_NAME)
public class DeleteUserCommandService implements CommandService {
    @Override
    public void execute() {

    }
}
