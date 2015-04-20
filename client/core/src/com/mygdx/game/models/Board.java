package com.mygdx.game.models;

import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;

import json.JsonHelper;

/**
 * Created by esso on 13.03.15.
 */
public class Board {
    private ArrayList<Cell> cells = new ArrayList<Cell>();

    /**
     * getCell
     * @param x
     * @param y
     * @return Cell and that location
     */
    public Cell getCell(int x, int y) {
        return cells.get(y*10 + x);
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    /**
     * createFromJson
     * @param json
     * @description Takes string of board json and creates cells from it. Adds cells to board
     */
    public void createFromJson(String json){
        cells = new ArrayList<Cell>();
        JsonValue jsonObject = JsonHelper.parseJson(json);
        JsonValue jsonBoard = jsonObject.get("cells");
        int X = 0;
        int Y = 0;
        for (int i = 0; i < jsonBoard.size; i++){
            JsonValue jsonCell = jsonBoard.get(i);
            Cell c = new Cell(X, Y);
            c.createFromJson(JsonHelper.prettyPrint(jsonCell));

            cells.add(c);
            X += 1;

            if (X%10 == 0){
                X = 0;
                Y += 1;
            }
         }
    }

    /**
     * toString
     * @return String representation of the board
     */
    public String toString(){
        String toReturn = "";
        for (Cell c: cells){
            toReturn += c + "\n";
        }
        return toReturn;
    }

    // A more natural representation of the board
    public String toString2() {
        int boardDimension = 10;
        String toReturn = "";
        for (int i=0; i< cells.size(); i++) {
            String spacing = " ";
            if (i % boardDimension == 0) {
                spacing = "\n";
            }
            toReturn += cells.get(i).toString2() + spacing;
        }
        return toReturn;
    }
}
