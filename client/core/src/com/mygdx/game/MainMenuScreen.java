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
public class MainMenuScreen implements Screen{
    SpriteBatch batch;
    Stage stage;
    Image shipImg;
    TextField nickNameField;
    String nickName;
    TextButton playButton;
    TextButton exitButton;
    Label nickNameLabel;
    VerticalGroup group;
    Battleship battleshipGame;
    Skin skin;
    int width, height;

    public MainMenuScreen(Battleship battleshipGame) {
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
        nickNameLabel = new Label("Enter nickname:", skin, "default-font", Color.BLACK);
        nickNameLabel.setAlignment(Align.center);
        nickNameField = new TextField("", skin);
        playButton = new TextButton("Find match", skin);
        exitButton = new TextButton("Exit", skin);

        // Add them to menu group
        group.addActor(shipImg);
        group.addActor(nickNameLabel);
        group.addActor(nickNameField);
        group.addActor(playButton);
        group.addActor(exitButton);

        // Add scene to stage
        stage.addActor(group);

        // Create listeners
        nickNameField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char key) {
                nickName = textField.getText();
            }
        });
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event,
                                float x,
                                float y) {
                battleshipGame.findMatch();
            }
        });
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event,
                                float x,
                                float y) {
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
