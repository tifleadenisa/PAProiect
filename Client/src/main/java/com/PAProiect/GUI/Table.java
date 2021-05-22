package com.PAProiect.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class Table{
    private static Stage stage;

    private static Polygon createDownTriangle(Point2D origin, String color){

        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(origin.getX(), origin.getY(),
                                        origin.getX()-25, origin.getY()-200,
                                        origin.getX()+25, origin.getY()-200);
        triangle.setFill(javafx.scene.paint.Color.web(color, 1.0));
        return triangle;
    }

    private static Polygon createUpTriangle(Point2D origin, String color){
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(origin.getX(), origin.getY(),
                origin.getX()-25, origin.getY()+200,
                origin.getX()+25, origin.getY()+200);
        triangle.setFill(javafx.scene.paint.Color.web(color, 1.0));
        return triangle;
    }

    private static Circle createCircle(Double x, Double y, String color){
        Circle circle = new Circle();
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(30);
        circle.setFill(javafx.scene.paint.Color.web(color, 1.0));
        return circle;
    }

    private static void addDownTriangles(GridPane grid){
        Polygon triangle;
        for (int column = 0; column < 13; column++) {
            if(column < 6){
                if(column % 2 == 0) {
                    triangle = createDownTriangle(new Point2D(75 + (50 * column), 200), "#F4D885");
                }else{
                    triangle = createDownTriangle(new Point2D(75+(50*column), 200), "#2F1D1D");
                }
            }else if(column ==6){
                triangle = createDownTriangle(new Point2D(75+(50*column), 200), "#B5816D");
            }else{
                if(column % 2 == 0) {
                    triangle = createDownTriangle(new Point2D(75 + (50 * column), 200), "#2F1D1D");
                }else{
                    triangle = createDownTriangle(new Point2D(75+(50*column), 200), "#F4D885");
                }
            }
            GridPane.setConstraints(triangle, column+1, 0);
            grid.getChildren().add(triangle);
        }
    }

    private static void addUpTriangles(GridPane grid){
        Polygon triangle;
        for (int column = 0; column < 13; column++) {
            if(column < 6){
                if(column % 2 == 0) {
                    triangle = createUpTriangle(new Point2D(75 + (50 * column), 500), "#2F1D1D");
                }else{
                    triangle = createUpTriangle(new Point2D(75+(50*column), 500), "#F4D885");
                }
            }else if(column ==6){
                triangle = createUpTriangle(new Point2D(75+(50*column), 500), "#B5816D");
            }else{
                if(column % 2 == 0) {
                    triangle = createUpTriangle(new Point2D(75 + (50 * column), 500), "#F4D885");
                }else{
                    triangle = createUpTriangle(new Point2D(75+(50*column), 500), "#2F1D1D");
                }
            }
            GridPane.setConstraints(triangle, column+1, 2);
            grid.getChildren().add(triangle);
        }
    }

    private static void addBlackChips(GridPane grid){
        
    }

    public static Stage constructBaseTable(){
        stage = new Stage();
        stage.setTitle("Backgammon Game");

        stage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(30);
        grid.setStyle("-fx-background-color: #B5816D;");

        Label nameLabel = new Label("Welcome!");
        GridPane.setConstraints(nameLabel, 0, 0);
        grid.getChildren().add(nameLabel);

        Button button = new Button("Close game");
        GridPane.setConstraints(nameLabel, 0, 1);
        button.setOnAction(e -> closeProgram());
        grid.getChildren().add(button);

        addDownTriangles(grid);
        addUpTriangles(grid);

        Scene scene = new Scene(grid, 750, 500);
        stage.setScene(scene);
        return stage;
        //stage.show();
    }

    private static void closeProgram(){
        boolean answer = ConfirmBox.display("Exit menu", "Sure you want to exit?");
        if(answer){
            stage.close();
        }
    }
}
