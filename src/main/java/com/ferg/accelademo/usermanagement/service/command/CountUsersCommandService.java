package com.ferg.accelademo.usermanagement.service.command;

import org.springframework.stereotype.Service;

@Service(CommandType.COUNT_BEAN_NAME)
public class CountUsersCommandService implements CommandService {
    @Override
    public void execute() {

    }
}
