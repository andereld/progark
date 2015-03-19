package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
    Label titleLabel;
    Label nickNameLabel;
    Battleship battleshipGame;
    Skin skin;
    TextButton.TextButtonStyle style;
    int width, height;

    public MainMenuScreen(Battleship battleshipGame) {
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

        // Set up layout

        // Create menu elements
        titleLabel = new Label("Sea Battle", skin, "default-font", Color.RED);
        shipImg = new Image(new Texture(battleshipGame.getFile("battleship.jpg")));
        nickNameLabel = new Label("Enter nickname:", skin, "default-font", Color.BLACK);
        nickNameField = new TextField("", skin);
        playButton = new TextButton("Find match", skin);
        exitButton = new TextButton("Exit", skin);
        //exitButton.align(Align.right);

        // Set element sizes
        shipImg.setSize(width-100, 300);
        titleLabel.setFontScale(3);
        titleLabel.setSize(width-100, 200);
        nickNameLabel.setFontScale(2);
        nickNameLabel.setSize(60, 30);
        nickNameField.setSize(240,50);
        int btnSizeW = 300;
        int btnSizeH = 100;
        playButton.setSize(btnSizeW,btnSizeH);
        exitButton.setSize(btnSizeW, btnSizeH);

        // Set element positions
        titleLabel.setPosition(width/2-100, height-350);
        shipImg.setPosition(45,height-600);
        nickNameLabel.setPosition(width/2-250, height/2);
        nickNameField.setPosition(width/2, height/2-5);
        playButton.setPosition(width-btnSizeW-50,100);
        exitButton.setPosition(50,100);

        // Add groups and elements to the stage
        stage.addActor(titleLabel);
        stage.addActor(shipImg);
        stage.addActor(nickNameLabel);
        stage.addActor(nickNameField);
        stage.addActor(playButton);
        stage.addActor(exitButton);

        // Create listeners
        nickNameField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char key) {
                nickName = textField.getText();
            }
        });
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                battleshipGame.findMatch();
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
        //skin.dispose();
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