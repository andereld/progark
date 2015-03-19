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
 * Created by Torstein on 17.03.2015.
 */
public class WaitingScreen implements Screen{
    SpriteBatch batch;
    Stage stage;
    Image shipImg;
    TextButton cancelButton;
    Label waitingLabel;
    Label titleLabel;
    Battleship battleshipGame;
    Skin skin;
    int width, height;
    TextButton.TextButtonStyle style;

    public WaitingScreen (Battleship battleshipGame) {
        this.battleshipGame = battleshipGame;
        width = battleshipGame.width;
        height = battleshipGame.height;
        skin = battleshipGame.skin;
    }

    @Override
    public void show() {
        style = battleshipGame.style;
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage); // So that the stage can receive input-events like button-clicks

        // Create menu elements
        shipImg = new Image(new Texture(battleshipGame.getFile("battleship.jpg")));
        titleLabel = new Label("Sea Battle", skin, "default-font", Color.RED);
        waitingLabel = new Label("Finding match...", skin, "default-font", Color.BLACK);
        waitingLabel.setAlignment(Align.center);
        cancelButton = new TextButton("Cancel", skin);

        // Set element sizes
        shipImg.setSize(width-100, 300);
        titleLabel.setFontScale(3);
        titleLabel.setSize(width-100, 200);
        waitingLabel.setFontScale(2);
        waitingLabel.setSize(60, 30);
        int btnSizeW = 300;
        int btnSizeH = 100;
        cancelButton.setSize(btnSizeW,btnSizeH);

        // Set element positions
        titleLabel.setPosition(width/2-100, height-350);
        shipImg.setPosition(45,height-600);
        waitingLabel.setPosition(width/2-35, height/2);
        cancelButton.setPosition(width/2-btnSizeW/2,height/2-130);

        // Add groups and elements to the stage
        stage.addActor(titleLabel);
        stage.addActor(shipImg);
        stage.addActor(waitingLabel);
        stage.addActor(cancelButton);

        // Create listeners
        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                battleshipGame.cancelFindMatch();
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
