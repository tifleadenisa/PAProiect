package com.PAProiect.GUI;

import com.PAProiect.client.Client;
import com.PAProiect.gameComponents.GameLogic;
import com.PAProiect.gameComponents.Player;
import com.PAProiect.gameComponents.table.Table;
import javafx.application.Application;
import javafx.stage.Stage;

public class LaunchGame  extends Application {
    private static Player player;
    private static Table table = new Table();
    private Client client;

    public static void main(String[] args) {
        /*try {
            Client client = new Client();
            client.connectToServer();
            table = client.getObjectReceived();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        launch(args);
    }

    public static void setPlayer(Player player) {
        LaunchGame.player = player;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameLogic.getInstance().setTable(table);
        TableGUI tableGUI = new TableGUI(player);
        primaryStage = tableGUI.constructBaseTable();
        primaryStage.show();
        if (!table.equals(GameLogic.getInstance().getTable())){
            client.setTable(GameLogic.getInstance().getTable());
        }
    }
}
