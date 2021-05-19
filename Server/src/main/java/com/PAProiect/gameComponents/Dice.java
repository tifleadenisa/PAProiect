package com.PAProiect.gameComponents;

import java.util.Random;

public class Dice {
    private final int dice;

    public Dice(){
        dice = generateNumber();
    }

    //generates number between 1 and 6
    private int generateNumber(){
        Random randomGenerator=new Random();
        return randomGenerator.nextInt(6) + 1;
    }

    public int getDice() {
        return dice;
    }
}
