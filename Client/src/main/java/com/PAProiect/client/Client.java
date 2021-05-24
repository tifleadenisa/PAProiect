package com.PAProiect.client;

import com.PAProiect.GUI.LaunchGame;
import com.PAProiect.gameComponents.table.Table;

import java.io.*;
import java.net.Socket;

/**
 * Clasa Client se ocupa de comunicarea unui player cu serverul, primind si trimitand instante ale tablei
 *                  pentru a actualiza tabla in functie de ceilalti jucatori
 *
 * @version May 2021
 * @author Denisa Tiflea
 */

public class Client {

    private static Table settedTable = new Table();
    private static Table table = new Table();
    private static Table objectReceived = new Table();
    public static Boolean sendTable = false;
    public static Socket socket;

    static {
        try {
            socket = new Socket("127.0.0.1",  8100);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Table getObjectReceived() {
        return objectReceived;
    }

    public static void setTable(Table table) {
        Client.table = table;
    }

    public static void sendTable(Table table) throws IOException {
        System.out.println("IEEEI SEND TABLE");
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(table);
        out.flush();
    }

    public static Table receiveTable() throws IOException, ClassNotFoundException {
        System.out.println("IN");
        ObjectInputStream inn = new ObjectInputStream(socket.getInputStream());
        objectReceived = (Table) inn.readObject();
        LaunchGame.setPlayer(objectReceived.getPlayingUser());
        return objectReceived;
    }

    public static void connectToServer() throws IOException, ClassNotFoundException {

        System.out.println("Connected!");

        while (true) {
            LaunchGame.setTable(receiveTable());

            LaunchGame.setPlayer(objectReceived.getPlayingUser());

            LaunchGame.main(new String[0]);

            System.out.println("OUT");
            if(!table.equals(settedTable) || sendTable){
                System.out.println("Am trimis");
                sendTable(table);
            }

        }
    }

    public static  void main(String[] args) throws IOException, ClassNotFoundException {
        connectToServer();
    }
}
