package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


/**
 * Created by Torstein on 16.03.2015.
 */
public class GameOverScreen implements Screen {
    SpriteBatch batch;
    Stage stage;
    Image shipImg;
    Label gameOverLabel;
    Label titleLabel;
    Label playerWonLabel;
    TextButton mainMenuButton;
    TextButton exitButton;
    VerticalGroup group;
    Battleship battleshipGame;
    Skin skin;
    TextButton.TextButtonStyle style;
    int width, height;

    public GameOverScreen(Battleship battleshipGame) {
        this.battleshipGame = battleshipGame;
        width = battleshipGame.width;
        height = battleshipGame.height;
        style = battleshipGame.style;
    }

    @Override
    public void show() {
        skin = battleshipGame.skin;
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage); // So that the stage can receive input-events like button-clicks

        // Create menu elements
        shipImg = new Image(new Texture(battleshipGame.getFile("battleship.jpg")));
        titleLabel = new Label("Sea Battle", skin, "default-font", Color.RED);
        gameOverLabel = new Label("Game over!", skin, "default-font", Color.BLACK);
        playerWonLabel = new Label("Player X won the game", skin, "default-font", Color.BLACK);
        // TODO: Show what player won the game
        gameOverLabel.setAlignment(Align.center);
        mainMenuButton = new TextButton("Main Menu", skin);
        exitButton = new TextButton("Exit", skin);

        // Set element sizes
        shipImg.setSize(width-100, 300);
        titleLabel.setFontScale(3);
        titleLabel.setSize(width-100, 200);
        gameOverLabel.setFontScale(2);
        gameOverLabel.setSize(60, 30);
        playerWonLabel.setFontScale(2);
        playerWonLabel.setSize(60, 30);
        int btnSizeW = 300;
        int btnSizeH = 100;
        mainMenuButton.setSize(btnSizeW,btnSizeH);
        exitButton.setSize(btnSizeW,btnSizeH);

        // Set element positions
        titleLabel.setPosition(width/2-100, height-350);
        shipImg.setPosition(45,height-600);
        gameOverLabel.setPosition(width/2-60, height/2);
        playerWonLabel.setPosition(width/2-150, height/2-60);
        mainMenuButton.setPosition(width-btnSizeW-50,100);
        exitButton.setPosition(50,100);

        // Add groups and elements to the stage
        stage.addActor(titleLabel);
        stage.addActor(shipImg);
        stage.addActor(gameOverLabel);
        stage.addActor(playerWonLabel);
        stage.addActor(mainMenuButton);
        stage.addActor(exitButton);

        // Create listeners
        mainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                battleshipGame.setMainMenuScreen();
            }
        });
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                battleshipGame.exit();
            }
        });
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.draw();
        batch.end();
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        dispose();
    }
}
