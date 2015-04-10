package com.mygdx.game.models;

import com.badlogic.gdx.utils.JsonValue;

import json.JsonHelper;

/**
 * Created by esso on 13.03.15.
 */
public class Cell {
    private int x, y;
    private boolean isHit, isShip;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean isHit) {
        this.isHit = isHit;
    }

    public boolean isShip() {
        return isShip;
    }

    public void setShip(boolean isShip) {
        this.isShip = isShip;
    }


    /**
     * createFromJson
     * @param json
     * @description Takes json data and puts it into this cell object
     */

    public void createFromJson(String json){
        JsonValue jsonObject = JsonHelper.parseJson(json);
        isHit = jsonObject.getBoolean("hasBeenHit");
        isShip = jsonObject.getBoolean("containsShip");
    }


}
