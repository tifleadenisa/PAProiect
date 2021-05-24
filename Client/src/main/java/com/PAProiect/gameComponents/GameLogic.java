package com.PAProiect.gameComponents;

import com.PAProiect.GUI.LaunchGame;
import com.PAProiect.GUI.TableGUI;
import com.PAProiect.client.Client;
import com.PAProiect.gameComponents.table.Table;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Clasa GameLogic se ocupa de validarea si realizare mutarilor de pe tabla de joc
 *
 * @version May 2021
 * @author Denisa Tiflea
 */

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
        canPlayerMove.put(Player.BLACK, false);
        canPlayerMove.put(Player.WHITE, false);
        canPlayerMove.replace(LaunchGame.getPlayer(), true);
        noOfMoves = new HashMap<>();
        noOfMoves.put(Player.BLACK, 0);
        noOfMoves.put(Player.WHITE, 0);
    }

    public static GameLogic getInstance(){
        if(instance == null) {
            instance = new GameLogic();
        }
        return instance;
    }

    public Map<Player, Boolean> getCanPlayerMove() {
        return canPlayerMove;
    }

    public void setCanPlayerMove(Map<Player, Boolean> canPlayerMove) {
        this.canPlayerMove = canPlayerMove;
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
        canPlayerMove.put(Player.BLACK, false);
        canPlayerMove.put(Player.WHITE, false);
        canPlayerMove.put(table.playingUser, true);
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

    public Boolean executeMoves(Player player, Integer actualPosition, Dice dice) throws IOException, ClassNotFoundException {
        if(!canPlayerMove.get(player) || table.getArc(actualPosition).getKey() != player){
            if(player == Player.NOPLAYER){
                System.out.println("NO PLAYER SELECTED");
            }
            System.out.println("Player:" + player);
            System.out.println(canPlayerMove);
            System.out.println("Wait for your turn!");
            return false;
        }else{
            if(tryToExecuteMove(player, actualPosition, dice)){
                if(noOfMoves.get(player) % 2 == 1 && noOfMoves.get(player)>0){
                    canPlayerMove.put(Player.BLACK, !canPlayerMove.get(Player.BLACK));
                    canPlayerMove.put(Player.WHITE, !canPlayerMove.get(Player.WHITE));
                }
                noOfMoves.replace(player, noOfMoves.get(player)+1);
                Client.sendTable(GameLogic.getInstance().getTable());
                GameLogic.getInstance().setTable(Client.receiveTable());
                LaunchGame.setTable(GameLogic.getInstance().getTable());
                TableGUI.resetChips();
                return true;
            }else{
                System.out.println("You cannot move with this checker! Choose another!");
                return false;
            }
        }
    }
}
