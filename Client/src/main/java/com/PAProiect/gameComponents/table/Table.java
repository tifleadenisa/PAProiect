package com.PAProiect.gameComponents.table;

import com.PAProiect.utils.Pair;
import com.PAProiect.gameComponents.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    //a table has 24 arcs
    //on every arc there is a player(or not) with a number of chips
    public static Integer NOOFARCS = 24;
    private List<Pair<Player, Integer>> arcs;
    private Map<Player, Integer> hittedBlots;

    private void initializeArcs(){
        arcs = new ArrayList<>();
        Pair<Player, Integer> pair = new Pair<>(Player.WHITE, 2);
        arcs.add(pair);

        pair = new Pair<>(Player.NOPLAYER, 0);
        arcs.add(pair);

        pair = new Pair<>(Player.NOPLAYER, 0);
        arcs.add(pair);

        pair = new Pair<>(Player.NOPLAYER, 0);
        arcs.add(pair);

        pair = new Pair<>(Player.NOPLAYER, 0);
        arcs.add(pair);

        pair = new Pair<>(Player.BLACK, 5);
        arcs.add(pair);

        pair = new Pair<>(Player.NOPLAYER, 0);
        arcs.add(pair);

        pair = new Pair<>(Player.BLACK, 3);
        arcs.add(pair);

        pair = new Pair<>(Player.NOPLAYER, 0);
        arcs.add(pair);

        pair = new Pair<>(Player.NOPLAYER, 0);
        arcs.add(pair);

        pair = new Pair<>(Player.NOPLAYER, 0);
        arcs.add(pair);

        pair = new Pair<>(Player.WHITE, 5);
        arcs.add(pair);

        pair = new Pair<>(Player.BLACK, 5);
        arcs.add(pair);

        pair = new Pair<>(Player.NOPLAYER, 0);
        arcs.add(pair);

        pair = new Pair<>(Player.NOPLAYER, 0);
        arcs.add(pair);

        pair = new Pair<>(Player.NOPLAYER, 0);
        arcs.add(pair);

        pair = new Pair<>(Player.WHITE, 3);
        arcs.add(pair);

        pair = new Pair<>(Player.NOPLAYER, 0);
        arcs.add(pair);

        pair = new Pair<>(Player.WHITE, 5);
        arcs.add(pair);

        pair = new Pair<>(Player.NOPLAYER, 0);
        arcs.add(pair);

        pair = new Pair<>(Player.NOPLAYER, 0);
        arcs.add(pair);

        pair = new Pair<>(Player.NOPLAYER, 0);
        arcs.add(pair);

        pair = new Pair<>(Player.NOPLAYER, 0);
        arcs.add(pair);

        pair = new Pair<>(Player.BLACK, 2);
        arcs.add(pair);
    }

    public Table(){
        initializeArcs();
        hittedBlots = new HashMap<>();
        hittedBlots.put(Player.BLACK,0);
        hittedBlots.put(Player.WHITE, 0);
        hittedBlots.put(Player.NOPLAYER, 0);
    }

    public List<Pair<Player, Integer>> getArcs() {
        return arcs;
    }

    public void setArcs(List<Pair<Player, Integer>> arcs) {
        this.arcs = arcs;
    }

    public Pair<Player, Integer> getArc(Integer arc){
        return arcs.get(arc);
    }

    public void setArc(Integer position, Pair<Player, Integer> arc){
        arcs.set(position, arc);
    }

    public Map<Player, Integer> getHittedBlots() {
        return hittedBlots;
    }

    public void setHittedBlots(Map<Player, Integer> hittedBlots) {
        this.hittedBlots = hittedBlots;
    }

    public void setPlayerHittedBlots(Player player, Integer noOfBlots){
        hittedBlots.replace(player, noOfBlots);
    }

    public Integer getPlayerHittedBlots(Player player){
        return hittedBlots.get(player);
    }

    public boolean isAllCheckersInHouse(Player player){
        //all checkers must be in the 1-6 arcs
        if(hittedBlots.get(player) == 0){
            if(player == Player.BLACK){
                for (int arcNumber = 6; arcNumber < Table.NOOFARCS; arcNumber++) {
                    if(this.getArc(arcNumber).getKey() == player)
                        return false;
                }
                return true;
            }else{
                //all checkers must be in the 19-24 arcs
                for (int arcNumber = 0; arcNumber < Table.NOOFARCS-6; arcNumber++) {
                    if(this.getArc(arcNumber).getKey() == player)
                        return false;
                }
                return true;
            }
        }else{
            return false;
        }

    }

    //0 can land because there's no chip on that arc or there's chips of the player on the arc
    //-1 cannot land on position
    //1 can land but point is occupied by a single opposing checker
    public Integer isLandingPositionOk(Player player, Integer position){
        if(getArcs().get(position).getKey() == Player.NOPLAYER || getArcs().get(position).getKey()==player){
            return 0;
        }else if(getArcs().get(position).getValue() == 1){
            return 1;
        }else{
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Table{" +
                "arcs=" + arcs +
                ", hittedBlots=" + hittedBlots +
                '}';
    }
}
