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

/**
 * Starts the main listening socket. For each client, it creates a separate connection thread
 */
public class BankServer{

    private ServerSocket serverSocket;
    private final int port;
    private Bank bank;
    private static final Logger logger = LogManager.getLogger(BankServer.class);

    /**
     * Constructor for the BankServer. It uses the config file to load the port and timeout
     * @throws IOException Gets thrown if an error occurred during loading the properties
     */
    public BankServer() throws IOException {
        Properties prop = new Properties();

        FileInputStream ip = new FileInputStream("config.properties");

        prop.load(ip);

        this.port = Integer.parseInt(prop.getProperty("PORT"));
    }

    /**
     * Starts the BankServer. It now listens to user connections
     */
    public void start(){
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getLocalHost());
            String ip_address = InetAddress.getLocalHost().getHostAddress();

            logger.info("Bank server is running on ip address {} and port {}", ip_address, port);

            bank = new Bank(ip_address);
            bank.readAccounts();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    bank.saveAccounts();
                    if(serverSocket != null) serverSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }));

            while(true) new Thread(new ClientHandler(serverSocket.accept())).start();

        } catch (SocketException e){
            logger.info("The application was shut down");
        } catch (IOException e) {
            logger.fatal("There has been a problem with the server: {}", e.getMessage());
        } catch (Exception e){
            logger.fatal("An unexpected fatal error has occurred: {}", e.getMessage());
        }
    }

    /**
     * Represents a user connection. It is meant to run in a thread
     */
    private class ClientHandler implements Runnable{

        private final Socket clientSocket;
        private final String clientIpAddress;
        private PrintWriter out;
        private BufferedReader in;
        private final CommandController commandController;

        public ClientHandler(Socket socket) throws SocketException {
            this.clientSocket = socket;
            this.clientIpAddress = clientSocket.getRemoteSocketAddress().toString();
            commandController = new CommandController();
        }

        /**
         * Simply registers all the commands with their code
         */
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

                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    try {
                        clientSocket.close();
                        out.close();
                        in.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }));

                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    try {
                        logger.info("The user thread {} executed this command: {}", clientIpAddress, inputLine);

                        if ("END".equals(inputLine)) {
                            out.println("Terminating connection");
                            break;
                        }

                        String[] inputLineSplit = inputLine.split(" ");
                        String output = commandController.executeCommand(inputLineSplit[0], Arrays.copyOfRange(inputLineSplit, 1, inputLineSplit.length));
                        out.println(output);

                        logger.info("The reply to user thread {} was: {}", clientIpAddress, output);
                    } catch (Exception e){
                        String errorMessage = "ER" + e.getMessage();
                        out.println(errorMessage);
                        logger.warn("The reply to user thread {} was: {}", clientIpAddress, errorMessage);
                    }
                }

            } catch (SocketException e){
                logger.info("The user thread {} was terminated", clientIpAddress);
            } catch (IOException e) {
                logger.error("The user thread {} has ran into an error: {}", clientIpAddress, e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                    out.close();
                    in.close();
                    logger.info("The user thread {} was closed and terminated", clientIpAddress);
                } catch (IOException e) {
                    logger.error("The user thread {} was not properly closed: {}", clientIpAddress, e.getMessage());
                }
            }
        }
    }
}
