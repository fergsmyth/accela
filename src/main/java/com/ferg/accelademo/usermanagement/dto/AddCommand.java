package com.ferg.accelademo.usermanagement.dto;

import javax.validation.constraints.Size;

public class AddCommand {

    @Size(min = 3, max = 3)
    private String commandArgs[];

    public AddCommand(String[] commandArgs) {
        this.commandArgs = commandArgs;
    }

    public String[] getCommandArgs() {
        return commandArgs;
    }

    public void setCommandArgs(String[] commandArgs) {
        this.commandArgs = commandArgs;
    }
}
