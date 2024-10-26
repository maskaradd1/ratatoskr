package com.maskarad.ratatoskr;

import javafx.concurrent.Task;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static MessageListener messageListener;

    public static void initialize(int port, MessageListener listener) {
        messageListener = listener;

        Task<Void> backgroundTask = new Task<>() {
            @Override
            protected Void call() {
                try (ServerSocket serverSocket = new ServerSocket(port)) {
                    System.out.println("Server running on port " + port + "...");

                    while (true) {
                        Socket clientSocket = serverSocket.accept();
                        System.out.println("Client connected: " + clientSocket.getInetAddress());
                        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                            String message;
                            while ((message = in.readLine()) != null) {
                                if (messageListener != null) {
                                    messageListener.onMessageReceived(message); // Notify listener
                                }
                            }
                        } catch (IOException e) {
                            System.out.println("Error handling client: " + e.getMessage());
                        } finally {
                            try {
                                clientSocket.close();
                            } catch (IOException e) {
                                System.out.println("Error closing client socket: " + e.getMessage());
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error while running the server: " + e.getMessage());
                }
                return null;
            }
        };

        Thread thread = new Thread(backgroundTask);
        thread.setDaemon(true);
        thread.start();
    }
}
