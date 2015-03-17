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
    String assetFolder;

    @Override
    public void create() {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            assetFolder = ""; // When launching with ADB or android phone
        } else {
            assetFolder = "android/assets/"; // When launching desktop launcher (core)
        }
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
        String filePath = assetFolder + filename;
        //Gdx.app.log("Accessing file:", filePath);
        return Gdx.files.internal(filePath);
    }
}