package com.PAProiect.game;

import com.PAProiect.gameComponents.Dice;
import com.PAProiect.gameComponents.table.Table;

//Game class is Singleton
public class Game {
    private static Game instance;

    private Table table;
    private Dice dice1;
    private Dice dice2;

    private Game(){
        table = new Table();
        dice1 = new Dice();
        dice2 = new Dice();
    }

    public static Game getInstance(){
        if(instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void reinitializeTable(){
        table = new Table();
    }

}
