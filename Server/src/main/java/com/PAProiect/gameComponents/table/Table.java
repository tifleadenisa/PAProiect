package com.PAProiect.gameComponents.table;

import com.PAProiect.gameComponents.Player;
import com.PAProiect.utils.Pair;

import java.util.ArrayList;
import java.util.List;


public class Table {
    //a table has 24 arcs
    //on every arc there is a player(or not) with a number of chips
    public static Integer NOOFARCS = 24;
    private List<Pair<Player, Integer>> arcs;

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
}
