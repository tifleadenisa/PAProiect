package com.PAProiect.game;

import com.PAProiect.gameComponents.Dice;
import com.PAProiect.gameComponents.Player;
import com.PAProiect.gameComponents.table.Table;
import com.PAProiect.gameComponents.table.TableMovesInside;
import com.PAProiect.gameComponents.table.TableMovesOutside;

import java.util.HashMap;
import java.util.Map;

//Game class is Singleton
public class Game {
    private static Game instance;

    private Table table;
    private Dice dice1;
    private Dice dice2;
    private Map<Player, Boolean> canPlayerMove;

    //black is starting the game
    private Game(){
        table = new Table();
        canPlayerMove = new HashMap<>();
        canPlayerMove.put(Player.BLACK, true);
        canPlayerMove.put(Player.WHITE, false);
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

    public void generateDices(){
        dice1 = new Dice();
        dice2 = new Dice();
    }

    public Table tryToExecuteMove(Player player, Integer actualPosition, Dice dice){
        Table modifiedTable;
        if(table.isAllCheckersInHouse(player)){
            TableMovesOutside tableMovesOutside = new TableMovesOutside(table);
            modifiedTable = tableMovesOutside.extractChecker(player, actualPosition, dice);
        }else{
            TableMovesInside tableMovesInside = new TableMovesInside(table);
            modifiedTable = tableMovesInside.extractChecker(player, actualPosition, dice);
        }
        return modifiedTable;
    }

    public Boolean executeMoves(Player player, Integer actualPosition, Dice dice){
        if(!canPlayerMove.get(player)){
            System.out.println("Wait for your turn!");
            return false;
        }else{
            if(!tryToExecuteMove(player,actualPosition,dice).equals(table)){
                table = tryToExecuteMove(player, actualPosition, dice);
                canPlayerMove.put(Player.BLACK, !canPlayerMove.get(Player.BLACK));
                canPlayerMove.put(Player.WHITE, !canPlayerMove.get(Player.WHITE));
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
