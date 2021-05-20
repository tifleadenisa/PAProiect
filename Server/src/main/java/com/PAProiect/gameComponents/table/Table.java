package com.PAProiect.gameComponents.table;

import com.PAProiect.gameComponents.Player;
import com.PAProiect.utils.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Table {
    //a table has 24 arcs
    //on every arc there is a player(or not) with a number of chips
    public static Integer NOOFARCS = 24;
    private List<Pair<Player, Integer>> arcs;
    private Map<Player, Integer> hittedBlots;

    private void initializeArcs(){
        arcs = new ArrayList<>();
        Pair<Player, Integer> pair = new Pair<>(Player.WHITE, 2);
        arcs.add(pair);

        pair = new Pair<>(Player.WHITE, 2);
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
}
