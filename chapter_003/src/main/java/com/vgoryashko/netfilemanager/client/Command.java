package com.vgoryashko.netfilemanager.client;

import java.io.Serializable;

/**
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/17/2017
 */
public class Command implements Serializable {


    public Command(String aCommandName, String aCommand) {
        this.commandName = aCommandName;
        this.command = aCommand;
    }

    private String commandName;

    private String command;

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }



}
