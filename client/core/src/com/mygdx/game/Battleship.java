package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.controllers.GameNetworkController;

public class Battleship extends Game {
    public static final int VIRTUAL_WIDTH = 405, VIRTUAL_HEIGHT = 720;
    private MainMenuScreen mainMenuScreen;
    private WaitingScreen waitingScreen;
    private GameOverScreen gameOverScreen;
    private GameScreen gameScreen;
    private int border;
    private Image background;
    private int width, height;
    private Skin skin;
    private TextButton.TextButtonStyle style;
    private float fontScalingRatio;
    private GameNetworkController gameNetworkController;

    @Override
    public void create() {
        gameNetworkController = new GameNetworkController(this);
        width = VIRTUAL_WIDTH;
        height = VIRTUAL_HEIGHT;

        Texture backgroundTexture = new Texture("background.jpg");
        background = new Image(backgroundTexture);

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        fontScalingRatio = (skin.getFont("default-font").getScaleY()/Battleship.VIRTUAL_HEIGHT) * Gdx.graphics.getHeight();

        if(Gdx.app.getType().equals(Application.ApplicationType.Android)) {
            width = Gdx.graphics.getWidth();
            height = Gdx.graphics.getHeight();
            skin.getFont("default-font").setScale(fontScalingRatio);
        }

        border = width/24;
        style = new TextButton.TextButtonStyle();

        mainMenuScreen = new MainMenuScreen(this);
        gameOverScreen = new GameOverScreen(this);
        waitingScreen = new WaitingScreen(this);
        gameScreen = new GameScreen(this);
        setScreen(gameOverScreen);
    }

    public Skin getSkin() {
        return skin;
    }

    public GameNetworkController getGameNetworkController() {
        return gameNetworkController;
    }

    public void setMainMenuScreen() {
        setScreen(mainMenuScreen);
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public void setGameScreen() {
        setScreen(gameScreen);
    }

    public void setGameOver(boolean thisPlayerWon) {
        gameOverScreen.setWinningPlayer(thisPlayerWon);
        setGameOverScreen();
    }

    public void setGameOverScreen() {
        setScreen(gameOverScreen);
    }

    public float getFontScalingRatio() {
        return fontScalingRatio;
    }

    public int getBorder() {
        return border;
    }

    public Image getBackground() {
        return background;
    }

    public void exit() {
        // @todo Close network connections etc
        Gdx.app.exit();
    }

    public void findMatch(String userName) {
        setScreen(waitingScreen);
        gameNetworkController.startGame(userName);
    }

    public void cancelFindMatch() {
        // TODO Cancel network requests etc?
        setMainMenuScreen();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public TextButton.TextButtonStyle getStyle() {
        return style;
    }

    public com.badlogic.gdx.files.FileHandle getFile(String filename) {
        String path = "" + filename;
        return Gdx.files.internal(path);
    }


}