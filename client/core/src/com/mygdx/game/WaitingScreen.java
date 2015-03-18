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
    VerticalGroup group;
    Battleship battleshipGame;
    Skin skin;
    int width, height;

    public WaitingScreen (Battleship battleshipGame) {
        this.battleshipGame = battleshipGame;
        width = battleshipGame.width;
        height = battleshipGame.height;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage); // So that the stage can receive input-events like button-clicks
        skin = new Skin(battleshipGame.getFile("uiskin.json"),
                new TextureAtlas(battleshipGame.getFile("uiskin.atlas")));
        // Set up layout
        group = new VerticalGroup();
        group.setBounds(0, 0, width, height);
        group.space(15);
        group.pad(50);
        group.fill();

        // Create menu elements
        shipImg = new Image(new Texture(battleshipGame.getFile("battleship.jpg")));
        waitingLabel = new Label("Finding match ...", skin, "default-font", Color.BLACK);
        waitingLabel.setAlignment(Align.center);
        cancelButton = new TextButton("Cancel", skin);

        // Add them to menu group
        group.addActor(waitingLabel);
        group.addActor(shipImg);
        group.addActor(cancelButton);

        // Add scene to stage
        stage.addActor(group);

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
        skin.dispose();
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
