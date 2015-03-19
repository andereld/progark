package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Battleship extends Game {
    MainMenuScreen mainMenuScreen;
    WaitingScreen waitingScreen;
    //GameScreen gameScreen;
    GameOverScreen gameOverScreen;
    int width, height;
    public Skin skin;
    public TextButton.TextButtonStyle style;

    @Override
    public void create() {
        //width = Gdx.graphics.getWidth();
        //height = Gdx.graphics.getHeight();
        width = 1080;
        height = 1920;
        createStyle();
        Gdx.graphics.setDisplayMode(1080,1920,false);
        mainMenuScreen = new MainMenuScreen(this);
        gameOverScreen = new GameOverScreen(this);
        waitingScreen = new WaitingScreen(this);
        setScreen(gameOverScreen);
    }

    private void createStyle() {
        //BitmapFont font = new BitmapFont();
        skin = new Skin(getFile("uiskin.json"), new TextureAtlas(getFile("uiskin.atlas")));
        style = new TextButton.TextButtonStyle();
        //Create a texture
        Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background",new Texture(pixmap));
        style.up = skin.newDrawable("background", Color.GRAY);
        style.down = skin.newDrawable("background", Color.DARK_GRAY);
        style.checked = skin.newDrawable("background", Color.DARK_GRAY);
        style.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        style.font = skin.getFont("default-font");
        style.font.setScale(2);
        skin.add("default", style);
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