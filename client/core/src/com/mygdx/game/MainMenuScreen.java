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
    VerticalGroup titleGroup;
    Battleship battleshipGame;
    Skin skin;
    int width, height;
    OrthographicCamera camera;

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
        /// Bottom left = 0, 0
        titleGroup = new VerticalGroup();
        titleGroup.setBounds(0, height-600, width, 400);
        //titleGroup.setBounds(x,y,width,height);
        titleGroup.space(15);
        titleGroup.pad(20);
        titleGroup.fill();

        // Create menu elements
        titleLabel = new Label("Sea Battle", skin, "default-font", Color.RED);
        titleLabel.setAlignment(Align.center);
        shipImg = new Image(new Texture(battleshipGame.getFile("battleship.jpg")));
        nickNameLabel = new Label("Enter nickname:", skin, "default-font", Color.BLACK);
        nickNameField = new TextField("", skin);
        playButton = new TextButton("Find match", skin);
        exitButton = new TextButton("Exit", skin);
        exitButton.align(Align.right);

        // Set element sizes
        titleLabel.setFontScale(3);
        nickNameLabel.setFontScale(2);
        nickNameLabel.setSize(60,30);
        nickNameField.setSize(120,40);
        playButton.setSize(240,80);
        exitButton.setSize(240,80);

        // Set element positions
        nickNameLabel.setPosition(width/2-250, height/2);
        nickNameField.setPosition(width/2, height/2);
        playButton.setPosition(width-290,100);
        exitButton.setPosition(50,100);

        // Add elements to menu groups
        titleGroup.addActor(titleLabel);
        titleGroup.addActor(shipImg);

        // Add groups and elements to the stage
        stage.addActor(titleGroup);
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
