package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Battleship extends Game {
    MainMenuScreen mainMenuScreen;
    WaitingScreen waitingScreen;
    //GameScreen gameScreen;
    GameOverScreen gameOverScreen;
    int width, height;

    @Override
    public void create() {
        //width = Gdx.graphics.getWidth();
        //height = Gdx.graphics.getHeight();
        width = 1080;
        height = 1920;
        Gdx.graphics.setDisplayMode(1080,1920,false);
        mainMenuScreen = new MainMenuScreen(this);
        gameOverScreen = new GameOverScreen(this);
        waitingScreen = new WaitingScreen(this);
        setScreen(mainMenuScreen);
    }

    public void setMainMenuScreen() {
        setScreen(mainMenuScreen);
    }

    public void setGameOverScreen() {
        setScreen(gameOverScreen);
    }

    public void exit() {
        // TODO Close network connections etc
        Gdx.app.exit();
    }

    public void findMatch() {
        // TODO Find match
        setScreen(waitingScreen);
    }

    public void cancelFindMatch() {
        // TODO Cancel network requests etc
        setMainMenuScreen();
    }

    public com.badlogic.gdx.files.FileHandle getFile(String filename) {
        String path = "" + filename;
        return Gdx.files.internal(path);
    }
}