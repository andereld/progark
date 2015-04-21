package com.mygdx.seabattle;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.seabattle.controllers.GameNetworkController;
import com.mygdx.seabattle.views.*;

public class SeaBattle extends Game {
    public static final int VIRTUAL_WIDTH = 405, VIRTUAL_HEIGHT = 720;
    private MainMenuScreen mainMenuScreen;
    private WaitingScreen waitingScreen;
    private GameOverScreen gameOverScreen;
    private GameScreen gameScreen;
    private TutorialScreen tutorialScreen;
    private CreatorScreen creatorScreen;
    private int border;
    private Image background, shipImg, titleImg;
    private int width, height;
    private Skin skin;
    private TextButton.TextButtonStyle style;
    private float fontScalingRatio;
    private GameNetworkController gameNetworkController;
    private Music music;

    @Override
    public void create() {
        gameNetworkController = new GameNetworkController(this);

        width = VIRTUAL_WIDTH;
        height = VIRTUAL_HEIGHT;

        background = new Image(new Texture("background.jpg"));
        titleImg = new Image(new Texture("toptext.png"));
        shipImg = new Image(new Texture("explosion.png"));

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        fontScalingRatio = 1;
        if(Gdx.app.getType().equals(Application.ApplicationType.Android)) {

            fontScalingRatio = (skin.getFont("default-font").getScaleY()/ SeaBattle.VIRTUAL_HEIGHT) * Gdx.graphics.getHeight();
            width = Gdx.graphics.getWidth();
            height = Gdx.graphics.getHeight();
            skin.getFont("default-font").setScale(fontScalingRatio);
        }

        border = width/24;
        style = new TextButton.TextButtonStyle();

        /** Set size and position for Title and Ship sprites **/
        float shipWidth = width - (2*border);
        float shipHeight = (shipWidth/shipImg.getWidth()) * shipImg.getHeight();
        shipImg.setSize(shipWidth, shipHeight);

        float titleWidth = width - (2 * border);
        float titleHeight = (titleWidth/titleImg.getWidth()) * titleImg.getHeight();
        titleImg.setSize(titleWidth, titleHeight);

        float titleImgPosX = (width/2) - (titleImg.getWidth()/2);
        float titleImgPosY = height - border - titleImg.getHeight();
        titleImg.setPosition(titleImgPosX, titleImgPosY);

        float shipImgPosX = border;
        float shipImgPosY = titleImgPosY - border - shipHeight;
        shipImg.setPosition(shipImgPosX, shipImgPosY);

        shipImg.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setCreatorScreen();
            }
        });

        creatorScreen = new CreatorScreen(this);
        tutorialScreen = new TutorialScreen(this);
        mainMenuScreen = new MainMenuScreen(this);
        gameOverScreen = new GameOverScreen(this);
        waitingScreen = new WaitingScreen(this);
        playMusic();
        setScreen(mainMenuScreen);
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

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public void setGameOverScreen() {
        setScreen(gameOverScreen);
    }

    public void setTutorialScreen() {
        tutorialScreen = new TutorialScreen(this);
        setScreen(tutorialScreen);
    }

    public void setCreatorScreen() {
        creatorScreen = new CreatorScreen(this);
        setScreen(creatorScreen);
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
        gameNetworkController.cancelWaitForOpponent();
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

    public Image getShipImg() {
        return shipImg;
    }

    public Image getTitleImg() {
        return titleImg;
    }

    public void playMusic() {
        music = Gdx.audio.newMusic(Gdx.files.internal("teknotanks.mp3"));
        music.setVolume(0.5f);
        music.setLooping(true);
        music.play();
    }

    public void stopMusic() {
        music.stop();
        music.dispose();
    }
}