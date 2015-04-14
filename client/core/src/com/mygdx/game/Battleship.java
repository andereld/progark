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

        fontScalingRatio = 1;
        if(Gdx.app.getType().equals(Application.ApplicationType.Android)) {

            fontScalingRatio = (skin.getFont("default-font").getScaleY()/Battleship.VIRTUAL_HEIGHT) * Gdx.graphics.getHeight();
            width = Gdx.graphics.getWidth();
            height = Gdx.graphics.getHeight();
            skin.getFont("default-font").setScale(fontScalingRatio);
        }

        border = width/24;
        style = new TextButton.TextButtonStyle();

        mainMenuScreen = new MainMenuScreen(this);
        gameOverScreen = new GameOverScreen(this);
        waitingScreen = new WaitingScreen(this);
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

    public void setGameScreen() {
        gameScreen = new GameScreen(this);
        setScreen(gameScreen);
    }

    public void setGameOverScreen() {
        setScreen(gameOverScreen);
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public void setGameOver(boolean thisPlayerWon) {
        gameOverScreen.setWinningPlayer(thisPlayerWon);
        setGameOverScreen();
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
        closeNetwork();
        Gdx.app.exit();
    }

    public void findMatch(String userName) {
        setScreen(waitingScreen);
        gameNetworkController.startGame(userName);
    }

    public void startGame() {
        setGameScreen();
    }

    public void cancelFindMatch() {
        // Can do other stuff here like cancel network requests
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

    public void closeNetwork() {
        // If needed to close sockets etc
    }

    public com.badlogic.gdx.files.FileHandle getFile(String filename) {
        String path = "" + filename;
        return Gdx.files.internal(path);
    }
}