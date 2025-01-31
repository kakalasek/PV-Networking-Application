package com.BankServer;

import com.BankServer.Bank.Bank;
import com.BankServer.CommandController.BankCommands.*;
import com.BankServer.CommandController.CommandController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;

public class BankServer{

    private ServerSocket serverSocket;
    private Bank bank;

    public void start(int port){
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getLocalHost());
            System.out.println("Bank server is running on ip address " + InetAddress.getLocalHost() + " and port " + port);
            bank = new Bank(InetAddress.getLocalHost().toString());

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    if(serverSocket != null) serverSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }));

            while(true) new Thread(new ClientHandler(serverSocket.accept())).start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(serverSocket != null) serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ClientHandler implements Runnable{

        private final Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private final CommandController commandController;

        public ClientHandler(Socket socket) throws SocketException {
            this.clientSocket = socket;
            commandController = new CommandController();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    if(clientSocket != null) clientSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }));
        }

        private void registerCommands(){
            commandController.registerCommand("BC", new BankCodeCommand(BankServer.this.bank));
            commandController.registerCommand("AC", new CreateAccountCommand(BankServer.this.bank));
            commandController.registerCommand("AD", new DepositAccountCommand(BankServer.this.bank));
            commandController.registerCommand("AW", new WithdrawalAccountCommand(BankServer.this.bank));
            commandController.registerCommand("AB", new BalanceAccountCommand(BankServer.this.bank));
            commandController.registerCommand("AR", new RemoveAccountCommand(BankServer.this.bank));
            commandController.registerCommand("BA", new BankTotalCommand(BankServer.this.bank));
            commandController.registerCommand("BN", new BankClientsCommand(BankServer.this.bank));
        }

        @Override
        public void run() {
            try {
                registerCommands();
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;

                while((inputLine = in.readLine()) != null){
                    if("END".equals(inputLine)) {
                        out.println("Terminating connection");
                        break;
                    }

                    String[] inputLineSplit = inputLine.split(" ");
                    String output = commandController.executeCommand(inputLineSplit[0], Arrays.copyOfRange(inputLineSplit, 1, inputLineSplit.length));
                    out.println(output);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                    out.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
