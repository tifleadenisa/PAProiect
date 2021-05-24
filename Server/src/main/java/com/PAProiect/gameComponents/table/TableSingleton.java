package com.PAProiect.gameComponents.table;

/**
 * Pentru a pastra aceeasi tabla intre jucatori, TableSingleton este o clasa de tip singleton care va fi
 *          setata de fiecare data cand serverul primeste o tabla de la client
 *
 * @version May 2021
 * @author Denisa Tiflea
 */

public class TableSingleton {
    private static TableSingleton instance;
    private Table table;

    private TableSingleton(){

    }

    public static TableSingleton getInstance(){
        if(instance == null) {
            instance = new TableSingleton();
        }
        return instance;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
