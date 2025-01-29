package com.BankServer;

import com.BankServer.BankCommands.Command;

import java.util.HashMap;

public class CommandController {

    private final HashMap<String, Command> commands;

    public CommandController(){
        commands = new HashMap<>();
    }

    public void registerCommand(String code, Command command){
        commands.put(code, command);
    }

    public String executeCommand(String code, String[] args){
        String output = null;

        for(String currentCode : commands.keySet()){
            if(currentCode.equals(code)) output = commands.get(currentCode).execute(args);
        }

        if(output == null) throw new RuntimeException("This command was not found");

        return output;
    }
}
