package com.BankServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BankServer{

    private ServerSocket serverSocket;

    public void start(int port){
        try {
            serverSocket = new ServerSocket(port);

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

    private static class ClientHandler implements Runnable{

        private final Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private CommandController commandController;

        public ClientHandler(Socket socket){
            this.clientSocket = socket;

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    System.out.println("Shutdown");
                    if(clientSocket != null) clientSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }));
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;

                while((inputLine = in.readLine()) != null){
                    if(".".equals(inputLine)) {
                        out.println("bye");
                        break;
                    }
                    out.println(inputLine);
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
