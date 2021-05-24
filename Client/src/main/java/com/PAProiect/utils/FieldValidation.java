package com.PAProiect.utils;

import javafx.scene.control.TextField;

public class FieldValidation {

    //Validate if input is int
    public static boolean isInt(TextField input){
        try{
            int number = Integer.parseInt(input.getText());
            System.out.println("number is: " + number);
            return number >= 0 && number <= 24;
        }catch(NumberFormatException e){
            System.out.println("Error: " + input.getText() + " is not a number");
            return false;
        }
    }

}
