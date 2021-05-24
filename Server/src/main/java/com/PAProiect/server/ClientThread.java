package com.PAProiect.server;

import com.PAProiect.game.Game;
import com.PAProiect.gameComponents.Player;
import com.PAProiect.gameComponents.table.Table;
import com.PAProiect.gameComponents.table.TableSingleton;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clasa ClientThread reprezinta un fir de executie creat de server pentru a realiza comunicarea intre server si 2 clienti simultan
 *
 * @version May 2021
 * @author Denisa Tiflea
 */

public class ClientThread extends Thread{

    private final Socket socket;
    private final ServerSocket serverSocket;

    public ClientThread (Socket socket, ServerSocket serverSocket) {
        this.socket = socket ;
        this.serverSocket = serverSocket;
    }

    public void run() {
        try{
            TableSingleton.getInstance().setTable(new Table());
            if (Server.playersCounter == 1) {
                TableSingleton.getInstance().getTable().setPlayingUser(Player.BLACK);
            } else {
                TableSingleton.getInstance().getTable().setPlayingUser(Player.WHITE);
            }
            do {
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(TableSingleton.getInstance().getTable());
                out.flush();

                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                TableSingleton.getInstance().setTable((Table) in.readObject());

            } while (Game.winner(TableSingleton.getInstance().getTable()).equals(Player.NOPLAYER));

        } catch (IOException e) {
            e.printStackTrace();
            Server.playersCounter--;
        } catch ( ClassNotFoundException e){
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
