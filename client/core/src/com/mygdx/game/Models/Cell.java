package com.mygdx.game.Models;

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


    public void createFromJson(String json){
        JsonValue jsonObject = JsonHelper.getJSonObject(json);
        isHit = jsonObject.getBoolean("isHit");
        isShip = jsonObject.getBoolean("isShip");
    }


}
