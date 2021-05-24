package com.PAProiect.GUI;

import com.PAProiect.gameComponents.*;
import com.PAProiect.utils.FieldValidation;
import com.PAProiect.utils.Pair;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.List;

/**
 * Clasa TableGUI se ocupa de afisarea, actualizarea interfetei grafice pentru jucator
 * De asemenea, cererea pentru o mutare se realizeaza cu ajutorul butoanelor din GUI
 *
 * @version May 2021
 * @author Denisa Tiflea
 */

public class TableGUI {
    private static Scene scene;
    private static StackPane stackPane = new StackPane();
    private static Stage stage;

    private static Player actualPlayer;

    public TableGUI(Player actualPlayer) {
        TableGUI.actualPlayer = actualPlayer;
    }

    private static Polygon createDownTriangle(Point2D origin, String color){

        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(origin.getX(), origin.getY(),
                                        origin.getX()-25, origin.getY()-200,
                                        origin.getX()+25, origin.getY()-200);
        triangle.setFill(javafx.scene.paint.Color.web(color, 1.0));
        return triangle;
    }

    private static void addDownTriangles(GridPane grid){
        Polygon triangle;
        for (int column = 0; column < 13; column++) {
            if(column < 6){
                if(column % 2 == 0) {
                    triangle = createDownTriangle(new Point2D(75 + (50 * column), 200), "#ba975b");
                }else{
                    triangle = createDownTriangle(new Point2D(75+(50*column), 200), "#870505");
                }
            }else if(column ==6){
                triangle = createDownTriangle(new Point2D(75+(50*column), 200), "#176610");
            }else{
                if(column % 2 == 0) {
                    triangle = createDownTriangle(new Point2D(75 + (50 * column), 200), "#870505");
                }else{
                    triangle = createDownTriangle(new Point2D(75+(50*column), 200), "#ba975b");
                }
            }
            GridPane.setConstraints(triangle, column+1, 0);
            grid.getChildren().add(triangle);
        }
    }

    private static Polygon createUpTriangle(Point2D origin, String color){
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(origin.getX(), origin.getY(),
                origin.getX()-25, origin.getY()+200,
                origin.getX()+25, origin.getY()+200);
        triangle.setFill(javafx.scene.paint.Color.web(color, 1.0));
        return triangle;
    }

    private static void addUpTriangles(GridPane grid){
        Polygon triangle;
        for (int column = 0; column < 13; column++) {
            if(column < 6){
                if(column % 2 == 0) {
                    triangle = createUpTriangle(new Point2D(75 + (50 * column), 500), "#870505");
                }else{
                    triangle = createUpTriangle(new Point2D(75+(50*column), 500), "#ba975b");
                }
            }else if(column ==6){
                triangle = createUpTriangle(new Point2D(75+(50*column), 500), "#176610");
            }else{
                if(column % 2 == 0) {
                    triangle = createUpTriangle(new Point2D(75 + (50 * column), 500), "#ba975b");
                }else{
                    triangle = createUpTriangle(new Point2D(75+(50*column), 500), "#870505");
                }
            }
            GridPane.setConstraints(triangle, column+1, 2);
            grid.getChildren().add(triangle);
        }
    }

    private static GridPane setTable(){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(30);
        grid.setStyle("-fx-background-color: #176610;");

        Label nameLabel = new Label("Welcome!");
        GridPane.setConstraints(nameLabel, 0, 0);
        grid.getChildren().add(nameLabel);

        Button button = new Button("Close game");
        GridPane.setConstraints(nameLabel, 0, 1);
        button.setOnAction(e -> closeProgram());
        grid.getChildren().add(button);

        addDownTriangles(grid);
        addUpTriangles(grid);

        return grid;
    }

    private static Circle createCircle(String color){
        Circle circle = new Circle();
        circle.setCenterX(750);
        circle.setCenterY(500);
        circle.setRadius(15);
        circle.setFill(javafx.scene.paint.Color.web(color, 1.0));

        return circle;
    }

    private static Circle createTransparentCircle(){
        Circle circle = new Circle();
        circle.setCenterX(750);
        circle.setCenterY(500);
        circle.setRadius(15);
        circle.setFill(javafx.scene.paint.Color.web("#ffffff", 0.0));

        return circle;
    }

    public static void resetChips(){
        stackPane.getChildren().remove(1);
        constructBaseTable();
    }

    private static GridPane play(){

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(9);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(7);
        grid.getColumnConstraints().addAll(
                column1,
                column2, column2,
                column2, column2,
                column2, column2,
                column2, column2,
                column2, column2,
                column2, column2,
                column2);

        Circle circle;

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 14; j++) {
                circle = createTransparentCircle();
                GridPane.setConstraints(circle, j, i);
                GridPane.setMargin(circle, new Insets(0, 0, 0, 12));
                grid.getChildren().add(circle);
            }
        }

        final String[] result = {"Dices: "};
        Button button = new Button("Set dices!");

        Label nameLabel = new Label("hello");

        button.setOnAction(event -> {
            GameLogic.getInstance().generateDices();
            Dice dice1 = GameLogic.getInstance().getDice1();
            Dice dice2 = GameLogic.getInstance().getDice2();
            result[0] += dice1.getDice();
            result[0] += ", ";
            result[0] += dice2.getDice();

            StringBuilder sb = new StringBuilder();
            for (String s : result) {
                sb.append(s);
            }
            String str = sb.toString();
            nameLabel.setText(str);
        });

        GridPane.setConstraints(button, 0, 1);
        grid.getChildren().add(button);

        GridPane.setConstraints(nameLabel, 0, 2);
        grid.getChildren().add(nameLabel);

        TextField actualPositionInput = new TextField();
        actualPositionInput.setPromptText("From");
        GridPane.setConstraints(actualPositionInput, 0, 3);
        grid.getChildren().add(actualPositionInput);

        TextField diceNo = new TextField();
        diceNo.setPromptText("Dice NO");
        GridPane.setConstraints(diceNo, 0, 4);
        grid.getChildren().add(diceNo);

        Button button1 = new Button("Move!");
        button1.setOnAction( e -> {
            if(FieldValidation.isInt(actualPositionInput) && FieldValidation.isInt(diceNo)){
                if(Integer.parseInt(diceNo.getText()) == 1 || Integer.parseInt(diceNo.getText())==2){
                    Dice dice1 = GameLogic.getInstance().getDice1();
                    Dice dice2 = GameLogic.getInstance().getDice2();
                    System.out.println("dice 1 is: " + dice1.getDice());
                    System.out.println("dice 2 is: " + dice2.getDice());
                    if(Integer.parseInt(diceNo.getText()) == 1){
                        try {
                            if(GameLogic.getInstance().executeMoves(actualPlayer,Integer.parseInt(actualPositionInput.getText()),dice1)){
                                System.out.println("Could execute with Dice1");
                                System.out.println(GameLogic.getInstance().getTable());
                                resetChips();
                            }else{
                                System.out.println("Choose other chip!");
                            }
                        } catch (IOException | ClassNotFoundException ioException) {
                            ioException.printStackTrace();
                        }
                    }else{
                        try {
                            if (GameLogic.getInstance().executeMoves(actualPlayer, Integer.parseInt(actualPositionInput.getText()), dice2)) {
                                System.out.println("Could execute with Dice2");
                                System.out.println(GameLogic.getInstance().getTable());
                                resetChips();
                            }else{
                                System.out.println("Choose other chip!");
                            }
                        } catch (IOException | ClassNotFoundException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }else{
                    System.out.println("Chose valid dice:1 or 2");
                }
            }
        });
        GridPane.setConstraints(button1, 0, 5);
        grid.getChildren().add(button1);

        Button button2 = new Button("Close");
        button2.setOnAction(e -> closeProgram());
        GridPane.setConstraints(button2, 0, 6);
        grid.getChildren().add(button2);

        //putCheckers(grid);
        List<Pair<Player, Integer>> arcs = GameLogic.getInstance().getTable().getArcs();
        for (int i = 0; i < arcs.size(); i++) {
            Pair<Player, Integer> arc = arcs.get(i);
            int noOfChips = arc.getValue();
            if (i < 12) {
                while (noOfChips != 0 && !arc.getKey().equals(Player.NOPLAYER)) {
                    if (arc.getKey().equals(Player.BLACK)) {
                        circle = createCircle("#1c0407");
                    } else {
                        circle = createCircle("#faf7f2");
                    }
                    if (i < 6) {
                        GridPane.setConstraints(circle, 13 - i, 15 - (arc.getValue() - noOfChips));
                    } else {
                        GridPane.setConstraints(circle, 12 - i, 15 - (arc.getValue() - noOfChips));
                    }
                    GridPane.setMargin(circle, new Insets(0, 0, 0, 12));
                    grid.getChildren().add(circle);
                    noOfChips--;
                }
            } else {
                while (noOfChips != 0 && !arc.getKey().equals(Player.NOPLAYER)) {
                    if (arc.getKey().equals(Player.BLACK)) {
                        circle = createCircle("#1c0407");
                    } else {
                        circle = createCircle("#faf7f2");
                    }
                    if (i < 18) {
                        GridPane.setConstraints(circle, i - 11, arc.getValue() - noOfChips);
                    } else {
                        GridPane.setConstraints(circle, i - 10, arc.getValue() - noOfChips);
                    }
                    GridPane.setMargin(circle, new Insets(0, 0, 0, 12));
                    grid.getChildren().add(circle);
                    noOfChips--;
                }
            }
        }

        return grid;
    }

    public static Stage constructBaseTable(){
        stage = new Stage();
        stage.setTitle("Backgammon Game");

        stage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        stackPane.getChildren().add(setTable());

        stackPane.getChildren().add(play());

        scene = new Scene(stackPane, 750, 500);
        stage.setScene(scene);
        return stage;
    }

    private static void closeProgram(){
        boolean answer = ConfirmBox.display("Exit menu", "Sure you want to exit?");
        if(answer){
            stage.close();
        }
    }
}
