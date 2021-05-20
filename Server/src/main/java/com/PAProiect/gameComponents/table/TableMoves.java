package com.PAProiect.gameComponents.table;

import com.PAProiect.gameComponents.Dice;
import com.PAProiect.gameComponents.Player;
import com.PAProiect.utils.Pair;

public class TableMoves {
    private Table table;

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

    //-1 invalid move
    private Integer expectedMove(Integer actualPosition, Pair<Player, Integer> pair, Dice dice){
        Player player = pair.getKey();
        Integer noOfChips = pair.getValue();
        if(table.getHittedBlots().get(player) != 0){
            if(player == Player.BLACK){
                if(isLandingPositionOk(player, Table.NOOFARCS - dice.getDice()) != -1){
                    return Table.NOOFARCS - dice.getDice();
                }else{
                    return -1;
                }
            }else if(player == Player.WHITE){
                if(isLandingPositionOk(player, dice.getDice()-1) != -1){
                    return dice.getDice()-1;
                }else{
                    return -1;
                }
            }else{
                return -1;
            }
        }else{
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
    }

    //0 - is ok
    //1 - not ok
    //-1 - has hittedBlots
    private Integer validateMove(Integer actualPosition, Integer landingPosition, Pair<Player, Integer> pair, Dice dice){
        if(table.getHittedBlots().get(pair.getKey()) != 0){
            return -1;
        }else if(landingPosition.equals(expectedMove(actualPosition, pair, dice))){
            return 0;
        }else{
            return 1;
        }
    }

    public Table makeMove(Player player, Integer actualPosition, Integer landingPosition, Dice dice){
        Pair<Player, Integer> arc = table.getArc(actualPosition);
        if(validateMove(actualPosition, landingPosition,arc, dice) == 0){
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
                //adauga o piesa pe arcul cu piese existente de-ale userului
                //adauga piesa celuilalt user pe mijloc
                table.setPlayerHittedBlots(table.getArc(landingPosition).getKey(), table.getPlayerHittedBlots(table.getArc(landingPosition).getKey())+1);
                //make move
                table.getArc(landingPosition).setKey(player);
                table.getArc(landingPosition).setValue(1);
            }
        }else if (validateMove(actualPosition, landingPosition,arc, dice) == -1){
            if(isLandingPositionOk(player, landingPosition) == 0){
                table.setPlayerHittedBlots(player, table.getPlayerHittedBlots(player)-1);
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
