package com.PAProiect.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientThread extends Thread{

    private final Socket socket;
    private final ServerSocket serverSocket;

    public ClientThread (Socket socket, ServerSocket serverSocket) {
        this.socket = socket ;
        this.serverSocket = serverSocket;
    }

    private void printAndFlush(PrintWriter printWriter, String response){
        printWriter.println(response);
        printWriter.flush();
    }

    public void run() {
        try{
            while(true) {
                // Get the request from the input stream: client → server
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                String request = in.readLine();
                // Send the response to the output stream: server → client
                PrintWriter out = new PrintWriter(socket.getOutputStream());

                if(request.startsWith("exit")){
                    printAndFlush(out, "Server received the request ...exiting...");
                    socket.close();
                    break;
                }else{
                    printAndFlush(out, "Server received the message: " + request);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
