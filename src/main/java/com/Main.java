package com;

import com.BankServer.BankServer;

public class Main {
    public static void main(String[] args) {
        try {
            BankServer bankServer = new BankServer();
            bankServer.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}