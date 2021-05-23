package com.PAProiect.server;

import com.PAProiect.gameComponents.table.Table;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientThread extends Thread{

    private final Socket socket;
    private final ServerSocket serverSocket;

    public ClientThread (Socket socket, ServerSocket serverSocket) {
        this.socket = socket ;
        this.serverSocket = serverSocket;
    }

    private void printAndFlush(PrintWriter printWriter, int response){
        printWriter.println(response);
        printWriter.flush();
    }

    public void run() {
        try{
            PrintWriter printOut = new PrintWriter(socket.getOutputStream());
            printAndFlush(printOut, Server.playersCounter);
            while(true) {
                // Get the request from the input stream: client → server
                /*BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                String request = in.readLine();
                // Send the response to the output stream: server → client
                PrintWriter out = new PrintWriter(socket.getOutputStream());

                if(request.startsWith("connect")){
                    printAndFlush(out, "Server received the request ...connecting...");
                    socket.close();
                    break;
                }else{
                    printAndFlush(out, "Server received the message: " + request);
                }*/
                Table objectToSend=new Table();
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(objectToSend);
                out.flush();
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
