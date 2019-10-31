package com.ferg.accelademo.usermanagement.service.command;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum CommandType {

    ADD(CommandType.ADD_BEAN_NAME),
    UPDATE(CommandType.UPDATE_BEAN_NAME),
    DELETE(CommandType.DELETE_BEAN_NAME),
    COUNT(CommandType.COUNT_BEAN_NAME),
    LIST(CommandType.LIST_BEAN_NAME),
    XML_ADD(CommandType.XML_ADD_BEAN_NAME);

    public static final String ADD_BEAN_NAME = "Add";
    public static final String UPDATE_BEAN_NAME = "Update";
    public static final String DELETE_BEAN_NAME = "Delete";
    public static final String COUNT_BEAN_NAME = "Count";
    public static final String LIST_BEAN_NAME = "List";
    public static final String XML_ADD_BEAN_NAME = "XmlAdd";

    private String commandName;
    private static Map<Integer, CommandType> commandOptionToCommandType;
    static {
        Map<Integer, CommandType> map = new HashMap<>();
        map.put(1, ADD);
        map.put(2, UPDATE);
        map.put(3, DELETE);
        map.put(4, COUNT);
        map.put(5, LIST);
        map.put(6, XML_ADD);
        commandOptionToCommandType = Collections.unmodifiableMap(map);
    }

    CommandType(String commandName) {
        this.commandName = commandName;
    }

    public static Map<Integer, CommandType> commandOptionToCommandTypeMappings(){
        return commandOptionToCommandType;
    }


}
