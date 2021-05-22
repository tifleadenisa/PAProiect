package com.PAProiect.GUI;

import com.PAProiect.client.Client;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LaunchGame  extends Application {

    public static void main(String[] args) {
        /*try {
            Client.connectToServer();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = Table.constructBaseTable();
        primaryStage.show();
    }
}
