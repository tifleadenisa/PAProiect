package com.PAProiect.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class Client {

    private static void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int PORT = 8100;
        try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader (new InputStreamReader(socket.getInputStream()))
        ) {
            String commandLine;
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                //read the command
                System.out.print("client>");
                commandLine = console.readLine();

                if (!commandLine.equals("")) {

                    // Send a request to the server
                    out.println(commandLine);

                    // Wait the response from the server ("Hello World!")
                    String response = in.readLine();
                    System.out.println(response);
                    //if stop or exit, stop the client
                    if (commandLine.equals("exit") || commandLine.equals("stop")) {
                        socket.close();
                        break;
                    }

                }
            }
        } catch (ConnectException e){
            System.out.println("There are too much players in game! Come back later!");
        }
    }

    public static  void main(String[] args) throws IOException {
        connectToServer();
    }
}
