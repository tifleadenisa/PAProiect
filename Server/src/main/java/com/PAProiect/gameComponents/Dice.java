package com.PAProiect.gameComponents;
import java.util.Random;

public class Dice {

    private final Integer dice;

    public Dice() {
        dice = generateNumber();
    }

    //generates number between 1 and 6
    private static int generateNumber(){
        Random randomGenerator=new Random();
        return randomGenerator.nextInt(6) + 1;
    }

    public Integer getDice() {
        return dice;
    }
}

