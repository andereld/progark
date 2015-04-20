package com.mygdx.seabattle.models;

/**
 * Created by esso on 16.03.15.
 */
public class Player {
    private String username;
    private Board board;

    public Player(String username, Board board){
        setBoard(board);
        setUsername(username);
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * toString
     * @return String representation of the player
     */
    public String toString(){
        return "Username is " + username + "\n this player has the following board: \n" + board;
    }

}
