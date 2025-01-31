package com;

import com.BankServer.BankServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            BankServer bankServer = new BankServer();
            bankServer.start();
        }catch (IOException e){
            logger.fatal("The configuration file was not found: {}", e.getMessage());
        } catch (Exception e){
            logger.fatal("An unknown fatal error has occurred: {}", e.getMessage());
        }
    }
}