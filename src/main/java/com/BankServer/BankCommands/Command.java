package com.BankServer.BankCommands;

@FunctionalInterface
public interface Command {
    String execute(String[] args);
}
