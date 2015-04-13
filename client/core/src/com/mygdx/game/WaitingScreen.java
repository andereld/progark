package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Torstein on 17.03.2015.
 */
public class WaitingScreen implements Screen{
    private SpriteBatch batch;
    private Stage stage;
    private Image shipImg;
    private TextButton cancelButton;
    private Label waitingLabel;
    private Label titleLabel;
    private Battleship battleshipGame;
    private Skin skin;
    private int width, height;
    private TextButton.TextButtonStyle style;

    public WaitingScreen (Battleship battleshipGame) {
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
        shipImg = new Image(new Texture("explosion_2.png"));
        titleLabel = new Label("Sea Battle", skin, "default-font", Color.RED);
        waitingLabel = new Label("Finding match...", skin);
        waitingLabel.setAlignment(Align.center);
        cancelButton = new TextButton("Cancel", skin);

        // Set element sizes
        float shipWidth = width - (2*battleshipGame.getBorder());
        float shipHeight = (shipWidth/shipImg.getWidth()) * shipImg.getHeight();
        shipImg.setSize(shipWidth, shipHeight);
        titleLabel.setFontScale(3 * battleshipGame.getFontScalingRatio());
        waitingLabel.setSize(width / 18, height / 64);
        double bW = width/2.4, bH = bW/3;
        float btnSizeW = (float) bW;
        float btnSizeH = (float) bH;
        cancelButton.setSize(btnSizeW,btnSizeH);

        // Set element positions
        titleLabel.setPosition((width/2) - (titleLabel.getWidth()/2), height - titleLabel.getHeight() - battleshipGame.getBorder());
        titleLabel.setAlignment(0);
        shipImg.setPosition(battleshipGame.getBorder(), height - (2*battleshipGame.getBorder()) - titleLabel.getHeight() - shipHeight);
        waitingLabel.setPosition(width/2, height - (3 * battleshipGame.getBorder()) - titleLabel.getHeight() - shipHeight - waitingLabel.getHeight());
        waitingLabel.setAlignment(0);
        cancelButton.setPosition(width/2-btnSizeW/2,height - waitingLabel.getHeight() - (4 * battleshipGame.getBorder()) - cancelButton.getHeight() - shipHeight - titleLabel.getHeight());

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
