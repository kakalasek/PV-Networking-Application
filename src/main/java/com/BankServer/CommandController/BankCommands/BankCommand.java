package com.BankServer.CommandController.BankCommands;

@FunctionalInterface
public interface BankCommand {
    String execute(String[] args);
}
