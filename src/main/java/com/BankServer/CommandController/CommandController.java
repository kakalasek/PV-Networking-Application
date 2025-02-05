package com.BankServer.CommandController;

import com.BankServer.CommandController.BankCommands.BankCommand;

import java.security.KeyException;
import java.util.HashMap;

/**
 * Takes care of executing and registering different commands
 */
public class CommandController {

    private final HashMap<String, BankCommand> commands;

    public CommandController(){
        commands = new HashMap<>();
    }

    /**
     * Registers a new command
     * @param code The code under which the new command will be called
     * @param command The command which will be called
     */
    public void registerCommand(String code, BankCommand command){
        commands.put(code, command);
    }

    /**
     * Executed the command and returns the output with the command code in front
     * @param code The code of the command you want to execute
     * @param args The arguments for the command you are calling. May as well be empty
     * @return The command code with the output of the command
     * @throws KeyException If the provided code does not match any registered command
     */
    public String executeCommand(String code, String[] args) throws KeyException{
        String output = null;

        for(String currentCode : commands.keySet()){
            if(currentCode.equals(code)) output = commands.get(currentCode).execute(args);
        }

        if(output == null) throw new KeyException("Such a command does not exist");

        return output;
    }
}
