package com.mygdx.game.models;

import com.badlogic.gdx.utils.Json;
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
    public Cell getCell(int x, int y){
        for (Cell c : cells){
            if (c.getX() == x && c.getY() == y){
                return c;
            }
        }
        return null;
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
}
