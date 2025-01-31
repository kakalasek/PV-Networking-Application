package com.BankServer;

import com.BankServer.Bank.Bank;
import com.BankServer.CommandController.BankCommands.*;
import com.BankServer.CommandController.CommandController;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BankServer{

    private ServerSocket serverSocket;
    private final int port;
    private Bank bank;
    private static final Logger logger = LogManager.getLogger(BankServer.class);

    public BankServer() throws IOException {
        Properties prop = new Properties();

        FileInputStream ip = new FileInputStream("config.properties");

        prop.load(ip);

        this.port = Integer.parseInt(prop.getProperty("PORT"));
    }

    public void start(){
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getLocalHost());
            logger.info("Bank server is running on ip address {} and port {}", InetAddress.getLocalHost(), port);
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
