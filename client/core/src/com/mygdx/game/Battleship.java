package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.*;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Battleship extends ApplicationAdapter {
	SpriteBatch batch;
	Image shipImg;
    TextField nickNameField;
    String nickName;
    TextButton playButton;
    TextButton exitButton;
    Label nickNameLabel;
    Stage stage;
    int width, height;

	@Override
	public void create () {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage); // So that the stage can receive input-events like button-clicks
		batch = new SpriteBatch();
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        // Set up layout
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"),
                new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
        VerticalGroup group = new VerticalGroup();
        group.setBounds(0, 0, width, height);
        group.space(10);
        group.pad(50);
        group.fill();

        // Create menu elements
        shipImg = new Image(new Texture(Gdx.files.internal("battleship.jpg")));
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
                // TODO: FIND MATCH
            }
        });
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event,
                                float x,
                                float y) {
                // TODO: EXIT GAME
            }
        });

    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        stage.draw();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

}