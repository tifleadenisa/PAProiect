package com.PAProiect.gameComponents;

import com.PAProiect.gameComponents.table.Table;

import java.util.HashMap;
import java.util.Map;

//Game class is Singleton
public class GameLogic {
    private static GameLogic instance;

    private Table table;
    private Dice dice1;
    private Dice dice2;
    private Map<Player, Boolean> canPlayerMove;
    private Map<Player, Integer> noOfMoves;

    //black is starting the game
    private GameLogic(){
        table = new Table();
        canPlayerMove = new HashMap<>();
        canPlayerMove.put(Player.BLACK, true);
        canPlayerMove.put(Player.WHITE, false);
        noOfMoves = new HashMap<>();
        noOfMoves.put(Player.BLACK, 0);
        noOfMoves.put(Player.WHITE, 0);
        //generateDices();
    }

    public static GameLogic getInstance(){
        if(instance == null) {
            instance = new GameLogic();
        }
        return instance;
    }

    public void reinitializeTable(){
        table = new Table();
    }

    public void generateDices(){
        dice1 = new Dice();
        dice2 = new Dice();
    }

    public Player canMove(){
        if(canPlayerMove.get(Player.BLACK)){
            return Player.BLACK;
        }
        return Player.WHITE;
    }

    public Dice getDice1() {
        return dice1;
    }

    public Dice getDice2() {
        return dice2;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Boolean tryToExecuteMove(Player player, Integer actualPosition, Dice dice){
        if(table.isAllCheckersInHouse(player)){
            TableMovesOutside tableMovesOutside = new TableMovesOutside(table);
            return tableMovesOutside.extractChecker(player, actualPosition, dice);
        }else{
            TableMovesInside tableMovesInside = new TableMovesInside(table);
            return tableMovesInside.extractChecker(player, actualPosition, dice);
        }
    }

    public Boolean executeMoves(Player player, Integer actualPosition, Dice dice){
        if(!canPlayerMove.get(player) || table.getArc(actualPosition).getKey() != player){
            System.out.println("Wait for your turn!");
            return false;
        }else{
            if(tryToExecuteMove(player, actualPosition, dice)){
                if(noOfMoves.get(player) % 2 == 0){
                    canPlayerMove.put(Player.BLACK, !canPlayerMove.get(Player.BLACK));
                    canPlayerMove.put(Player.WHITE, !canPlayerMove.get(Player.WHITE));
                }
                noOfMoves.replace(player, noOfMoves.get(player)+1);
                return true;
            }else{
                System.out.println("You cannot move with this checker! Choose another!");
                return false;
            }
        }
    }

    public Player winner(){
        int whitesChips = 0;
        int blackChips = 0;
        for (int arc = 0; arc < Table.NOOFARCS; arc++) {
            if(table.getArc(arc).getKey() == Player.BLACK){
                blackChips += table.getArc(arc).getValue();
            }else if(table.getArc(arc).getKey() == Player.WHITE){
                whitesChips += table.getArc(arc).getValue();
            }
        }
        if(whitesChips == 0){
            return Player.WHITE;
        }else if(blackChips == 0){
            return Player.BLACK;
        }
        return Player.NOPLAYER;
    }
}
