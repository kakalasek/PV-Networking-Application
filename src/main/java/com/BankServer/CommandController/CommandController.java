package com.BankServer.CommandController;

import com.BankServer.CommandController.BankCommands.BankCommand;

import java.util.HashMap;

public class CommandController {

    private final HashMap<String, BankCommand> commands;

    public CommandController(){
        commands = new HashMap<>();
    }

    public void registerCommand(String code, BankCommand command){
        commands.put(code, command);
    }

    public String executeCommand(String code, String[] args){
        String output = null;

        for(String currentCode : commands.keySet()){
            if(currentCode.equals(code)) output = commands.get(currentCode).execute(args);
        }

        if(output == null) return "Such a command does not exist";

        return output;
    }
}
