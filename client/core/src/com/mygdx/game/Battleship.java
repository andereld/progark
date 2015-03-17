package com.mygdx.game;

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
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
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
        // TODO Close network connections ETC
        Gdx.app.exit();
    }

    public void findMatch() {
        // TODO Find match
        setScreen(waitingScreen);
    }
}