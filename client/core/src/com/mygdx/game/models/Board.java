package com.mygdx.game.models;

import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;

import json.JsonHelper;

/**
 * Created by esso on 13.03.15.
 */
public class Board {
    private ArrayList<Cell> board;
    /**
     * getCell
     * @param x
     * @param y
     * @return Cell and that location
     */
    public Cell getCell(int x, int y){
        for (Cell c : board){
            if (c.getX() == x && c.getY() == y){
                return c;
            }
        }
        return null;
    }


    /**
     * createFromJson
     * @param json
     * @description Takes string of board json and creates cells from it. Adds cells to board
     */
    public void createFromJson(String json){
        JsonValue jsonObject = JsonHelper.getJSonObject(json);
        JsonValue jsonBoard = jsonObject.get("board");
        int X = 0;
        int Y = 0;
        for (int i = 0; i < jsonBoard.asStringArray().length; i++){
            JsonValue jsonCell = jsonBoard.get(i);
            Cell c = new Cell(X, Y);
            c.createFromJson(jsonCell.toString());
            board.add(c);

            if (i%10 == 0){
                X = 0;
                Y += 1;
            }
         }
    }
}
