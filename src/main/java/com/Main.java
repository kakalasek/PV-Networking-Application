package com;

import com.BankServer.BankServer;

public class Main {
    public static void main(String[] args) {
        BankServer bankServer = new BankServer();
        bankServer.start(64800);
    }
}