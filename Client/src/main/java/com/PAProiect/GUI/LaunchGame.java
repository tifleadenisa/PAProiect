package com.PAProiect.GUI;

import com.PAProiect.client.Client;
import com.PAProiect.gameComponents.GameLogic;
import com.PAProiect.gameComponents.Player;
import com.PAProiect.gameComponents.table.Table;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Clasa LauncGame se ocupa de pornirea interfetei grafice a jocului
 *
 * @version May 2021
 * @author Denisa Tiflea
 */

public class LaunchGame  extends Application {
    private static Player player = Player.BLACK;
    private static Table table = new Table();
    //private static Client client;

    public static void main(String[] args) {
        launch(args);
    }

    public static void setPlayer(Player player) {
        LaunchGame.player = player;
    }

    public static Player getPlayer() {
        return player;
    }

    public static Table getTable() {
        return table;
    }

    public static void setTable(Table table) {
        LaunchGame.table = table;
    }

    @Override
    public void start(Stage primaryStage) {
        GameLogic.getInstance().setTable(table);
        TableGUI tableGUI = new TableGUI(player);
        primaryStage = TableGUI.constructBaseTable();
        primaryStage.show();
    }
}
