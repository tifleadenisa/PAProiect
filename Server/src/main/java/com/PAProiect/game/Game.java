package com.PAProiect.game;

import com.PAProiect.gameComponents.Player;
import com.PAProiect.gameComponents.table.Table;

/**
 * Clasa Game este un utility class pentru a verifica daca exista castigatori in momentul apelarii metodei winner
 *
 * @version May 2021
 * @author Denisa Tiflea
 */

public class Game {

    public static Player winner(Table table){
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
