package com.mygdx.seabattle.models;

import com.badlogic.gdx.utils.JsonValue;

import com.mygdx.seabattle.json.JsonHelper;

/**
 * Created by esso on 13.03.15.
 */
public class Cell {
    private int x, y;
    private boolean isHit, containsShip;

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

    public boolean containsShip() {
        return containsShip;
    }

    public void setContainsShip(boolean isShip) {
        this.containsShip = isShip;
    }

    /**
     * createFromJson
     * @param json
     * @description Takes com.mygdx.seabattle.json data and puts it into this cell object
     */

    public void createFromJson(String json){
        JsonValue jsonObject = JsonHelper.parseJson(json);
        isHit = jsonObject.getBoolean("hasBeenHit");
        containsShip = jsonObject.getBoolean("containsShip");
    }

    /**
     * toString
     * @return String representation of the cell
     */
    public String toString(){
        return "X: " + Integer.toString(x) + " Y: " + Integer.toString(y) + " isHit: " + isHit + " containsShip: " + containsShip;
    }

    // When printing the whole board this can be useful
    public String toString2() {
        return isHit + "/" + containsShip;
    }

}
