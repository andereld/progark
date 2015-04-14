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
    private SpriteBatch batch;
    private Stage stage;
    private Image shipImg;
    private TextField nickNameField;
    private TextButton playButton;
    private TextButton exitButton;
    private Label titleLabel;
    private Label nickNameLabel;
    private Battleship battleshipGame;
    private Skin skin;
    private TextButton.TextButtonStyle style;
    private int width, height;

    public MainMenuScreen(Battleship battleshipGame) {
        this.battleshipGame = battleshipGame;
        width = battleshipGame.getWidth();
        height = battleshipGame.getHeight();
        skin = battleshipGame.getSkin();
    }

    @Override
    public void show() {
        style = battleshipGame.getStyle();
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage); // So that the stage can receive input-events like button-clicks

        stage.addActor(battleshipGame.getBackground());
        battleshipGame.getBackground().setFillParent(true);

        // Create menu elements
        titleLabel = new Label("Sea Battle", skin, "default-font", Color.RED);
        shipImg = new Image(new Texture("explosion_2.png"));
        nickNameLabel = new Label("Enter nickname:", skin);
        nickNameField = new TextField("", skin);
        playButton = new TextButton("Find match", skin);
        exitButton = new TextButton("Exit", skin);

        // Set element sizes
        float shipWidth = width - (2*battleshipGame.getBorder());
        float shipHeight = (shipWidth/shipImg.getWidth()) * shipImg.getHeight();
        shipImg.setSize(shipWidth, shipHeight);
        titleLabel.setFontScale(3 * battleshipGame.getFontScalingRatio());
        double fieldWidth = width/4.5, fieldHeight = height/38.4;
        nickNameField.setSize((float) fieldWidth, (float) fieldHeight);
        double bW = width/2.4, bH = bW/3;
        float btnSizeW = (float) bW;
        float btnSizeH = (float) bH;
        playButton.setSize(btnSizeW,btnSizeH);
        exitButton.setSize(btnSizeW, btnSizeH);

        // Set element positions
        titleLabel.setPosition((width / 2) - (titleLabel.getWidth() / 2), height - titleLabel.getHeight() - battleshipGame.getBorder());
        titleLabel.setAlignment(0);
        shipImg.setPosition(battleshipGame.getBorder(), height - (2*battleshipGame.getBorder()) - titleLabel.getHeight() - shipHeight);
        nickNameLabel.setPosition((width/2)-nickNameLabel.getWidth() - (battleshipGame.getBorder()/2), height - (3*battleshipGame.getBorder()) - titleLabel.getHeight() - shipHeight - nickNameLabel.getHeight());
        nickNameField.setPosition(width/2 + (battleshipGame.getBorder()/2), height - (3*battleshipGame.getBorder()) - titleLabel.getHeight() - shipHeight - nickNameLabel.getHeight() + 5);
        playButton.setPosition(battleshipGame.getBorder(), battleshipGame.getBorder());
        exitButton.setPosition(width - btnSizeW - battleshipGame.getBorder(), battleshipGame.getBorder());

        // Add groups and elements to the stage
        stage.addActor(titleLabel);
        stage.addActor(shipImg);
        stage.addActor(nickNameLabel);
        stage.addActor(nickNameField);
        stage.addActor(playButton);
        stage.addActor(exitButton);

        nickNameField.setMessageText("DefaultPlayer");
        // Create listeners
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String t = nickNameField.getText();
                if (t.length() == 0) {
                    t = "DefaultPlayer";
                }
                battleshipGame.findMatch(t);
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
        batch.enableBlending();
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
