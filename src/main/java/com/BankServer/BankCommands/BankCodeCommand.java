package com.BankServer.BankCommands;

import java.net.Socket;

public class BankCodeCommand implements Command{

    Socket clientSocket;

    public BankCodeCommand(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public String execute(String[] args) {
        return clientSocket.getLocalSocketAddress().toString();
    }
}
