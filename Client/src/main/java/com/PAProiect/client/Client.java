package com.PAProiect.client;

import com.PAProiect.GUI.LaunchGame;
import com.PAProiect.gameComponents.Player;
import com.PAProiect.gameComponents.table.Table;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

public class Client {

    private static Table settedTable = new Table();
    private static Table table;
    private static Table objectReceived = new Table();

    public Table getObjectReceived() {
        return objectReceived;
    }

    public void setTable(Table table) {
        Client.table = table;
    }

    public static void sendTable(Socket socket, Table table) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(table);
        out.flush();
    }

    public static void connectToServer() throws IOException, ClassNotFoundException {
        String serverAddress = "127.0.0.1";
        int PORT = 8100;
        try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader (new InputStreamReader(socket.getInputStream()))
        ) {
            String commandLine;
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Connected!");


            String playerNo = in.readLine();
            while (true) {
                /*//read the command
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

                }*/

                ObjectInputStream inn = new ObjectInputStream(socket.getInputStream());
                objectReceived = (Table) inn.readObject();


                if(playerNo.equals("1")){
                    LaunchGame.setPlayer(Player.BLACK);
                }else {
                    LaunchGame.setPlayer(Player.WHITE);
                }

                LaunchGame.main(new String[0]);

                if(!table.equals(settedTable)){
                    sendTable(socket,table);
                }

            }

        } catch (ConnectException e){
            System.out.println("There are too much players in game! Come back later!");
        } /*catch (ClassNotFoundException e){
            System.out.println("Message: " + e.getMessage());
        }*/
    }

    public static  void main(String[] args) throws IOException, ClassNotFoundException {
        connectToServer();
    }
}
