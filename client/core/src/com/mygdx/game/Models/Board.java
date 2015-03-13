package com.mygdx.game.Models;

import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;

import json.JsonHelper;

/**
 * Created by esso on 13.03.15.
 */
public class Board {
    private ArrayList<Cell> board;

    public Cell getCell(int x, int y){
        for (Cell c : board){
            if (c.getX() == x && c.getY() == y){
                return c;
            }
        }
    }

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
