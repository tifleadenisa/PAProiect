package com.PAProiect.gameComponents.table;

import com.PAProiect.gameComponents.Dice;
import com.PAProiect.gameComponents.Player;
import com.PAProiect.utils.Pair;

public class TableMovesOutside {
    private Table table;

    public TableMovesOutside() {
        table = new Table();
    }

    public TableMovesOutside(Table table) {
        this.table = table;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    private boolean canExtractChecker(Player player){
        //all checkers must be in the 1-6 arcs
        if(player == Player.BLACK){
            for (int arcNumber = 6; arcNumber < Table.NOOFARCS; arcNumber++) {
                if(table.getArc(arcNumber).getKey() == player)
                    return false;
            }
            return true;
        }else{
            //all checkers must be in the 19-24 arcs
            for (int arcNumber = 0; arcNumber < Table.NOOFARCS-6; arcNumber++) {
                if(table.getArc(arcNumber).getKey() == player)
                    return false;
            }
            return true;
        }
    }

    //-1 invalid move
    //0 can extract(out)
    //else: can make move without extracting checkers
    private Integer expectedMove(Integer actualPosition, Pair<Player, Integer> pair, Dice dice){
        Player player = pair.getKey();
        Integer noOfChips = pair.getValue();
        if(noOfChips == 0){
            return -1;
        }else{
            if(canExtractChecker(player)){
                return -1;
            }else{
                if(player == Player.BLACK){
                    return Math.max(actualPosition - dice.getDice(), 0);
                }else{
                    if(actualPosition + dice.getDice() > Table.NOOFARCS -1 ){
                        return 0;
                    }else{
                        return actualPosition + dice.getDice();
                    }
                }
            }
        }
    }

    //0 can land because there's no chip on that arc or there's chips of the player on the arc
    //-1 cannot land on position
    //1 can land but point is occupied by a single opposing checker
    private Integer isLandingPositionOk(Player player, Integer position){
        if(table.getArcs().get(position).getKey() == Player.NOPLAYER || table.getArcs().get(position).getKey()==player){
            return 0;
        }else if(table.getArcs().get(position).getValue() == 1){
            return 1;
        }else{
            return -1;
        }
    }

    //in case a player can extract checkers
    public Table extractChecker(Player player, Integer actualPosition, Dice dice){
        Integer landingPosition = expectedMove(actualPosition, table.getArc(actualPosition), dice);
        if(landingPosition == 0){
            Pair<Player, Integer> pair = new Pair<>(player, table.getArc(actualPosition).getValue()-1);
            table.setArc(actualPosition, pair);
        }else if(landingPosition == -1){
            return table;
        }else{
            Pair<Player, Integer> arc = table.getArc(actualPosition);
            if(isLandingPositionOk(player, landingPosition) == 0){
                if(arc.getValue() == 1){
                    arc.setKey(Player.NOPLAYER);
                    arc.setValue(0);
                }else{
                    arc.setValue(arc.getValue() - 1);
                }
                table.getArc(landingPosition).setValue(table.getArc(landingPosition).getValue() + 1);
            }else if(isLandingPositionOk(player, landingPosition) == 1){
                table.setPlayerHittedBlots(table.getArc(landingPosition).getKey(), table.getPlayerHittedBlots(table.getArc(landingPosition).getKey())+1);
                //make move
                table.getArc(landingPosition).setKey(player);
                table.getArc(landingPosition).setValue(1);
            }
        }
        return table;
    }
}
