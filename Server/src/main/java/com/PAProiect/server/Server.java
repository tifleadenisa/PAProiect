package com.PAProiect.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int MAX_NUMBER_PLAYERS = 2;
    public static boolean isRunning = true;
    public static final int PORT = 8100;

    public Server() {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            int playersCounter = 0;
            while (isRunning && playersCounter < MAX_NUMBER_PLAYERS) {
                System.out.println("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                new ClientThread(socket, serverSocket).start();
                playersCounter++;
            }
        } catch (IOException e) {
            System.err.println("Ooops... " + e);
        }
    }


    public static void main (String[] args){
        Server server = new Server ();
    }
}
