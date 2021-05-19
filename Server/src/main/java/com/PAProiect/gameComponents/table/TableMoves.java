package com.PAProiect.gameComponents.table;

import com.PAProiect.gameComponents.Dice;
import com.PAProiect.gameComponents.Player;
import com.PAProiect.utils.Pair;

import java.util.List;

public class TableMoves {
    private Table table;
    private List<Pair<Player, Integer>> noOfHittedBlots;

    public TableMoves() {
        table = new Table();
    }

    public TableMoves(Table table) {
        this.table = table;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    //0 can land because there's no chip on that arc
    //1 can land because there's chips of the player on the arc
    //-1 cannot land on position
    //2 can land but point is occupied by a single opposing checker
    private Integer isLandingPositionOk(Player player, Integer position){
        if(table.getArcs().get(position).getKey() == Player.NOPLAYER){
            return 0;
        }else if(table.getArcs().get(position).getKey()==player){
            return 1;
        }else if(table.getArcs().get(position).getValue() == 1){
            return 2;
        }else{
            return -1;
        }
    }

    //-1 invalid move
    private Integer expectedMove(Integer actualPosition, Pair<Player, Integer> pair, Dice dice){
        Player player = pair.getKey();
        Integer noOfChips = pair.getValue();
        if(player == Player.NOPLAYER){
            return -1;
        }else if(player == Player.BLACK){
                //scade
                if(actualPosition - dice.getDice() < 0){
                    return -1;
                }else if(noOfChips == 0){
                        return -1;
                }else {
                    if(isLandingPositionOk(player, actualPosition - dice.getDice()) != -1){
                        return actualPosition - dice.getDice();
                    }else{
                        return -1;
                    }
                }
        }else{
            //white player
            //creste
            if(actualPosition + dice.getDice() >= Table.NOOFARCS){
                return -1;
            }else if(noOfChips == 0){
                return -1;
            }else {
                if(isLandingPositionOk(player, actualPosition + dice.getDice()) != -1){
                    return actualPosition + dice.getDice();
                }else{
                    return -1;
                }
            }
        }
    }

    private Boolean validateMove(Integer actualPosition, Integer landingPosition, Pair<Player, Integer> pair, Dice dice){
        return landingPosition.equals(expectedMove(actualPosition, pair, dice));
    }

    public Table makeMove(Player player, Integer actualPosition, Integer landingPosition, Dice dice){
        Pair<Player, Integer> arc = table.getArc(actualPosition);
        if(validateMove(actualPosition, landingPosition,arc, dice)){
            //add the first chip on that arc
            if(isLandingPositionOk(player, landingPosition) == 0){
               if(arc.getValue() == 1){
                   arc.setKey(Player.NOPLAYER);
                   arc.setValue(0);
               }else{
                   arc.setValue(arc.getValue() - 1);
               }
               table.getArc(landingPosition).setValue(table.getArc(landingPosition).getValue() + 1);
            }else if(isLandingPositionOk(player, landingPosition) == 1){
                //pune pe asteptare o piesa
            }
        }
        return table;
    }
}
