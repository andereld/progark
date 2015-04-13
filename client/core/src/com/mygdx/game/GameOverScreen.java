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
 * Created by Torstein on 16.03.2015.
 */
public class GameOverScreen implements Screen {
    private SpriteBatch batch;
    private Stage stage;
    private Image shipImg;
    private Label gameOverLabel;
    private Label titleLabel;
    private Label playerWonLabel;
    private TextButton mainMenuButton;
    private TextButton exitButton;
    private VerticalGroup group;
    private Battleship battleshipGame;
    private Skin skin;
    private TextButton.TextButtonStyle style;
    private int width, height;
    private boolean thisPlayerWon;

    public GameOverScreen(Battleship battleshipGame) {
        this.battleshipGame = battleshipGame;
        width = battleshipGame.getWidth();
        height = battleshipGame.getHeight();
        style = battleshipGame.getStyle();
    }

    @Override
    public void show() {
        skin = battleshipGame.getSkin();
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage); // So that the stage can receive input-events like button-clicks

        stage.addActor(battleshipGame.getBackground());
        battleshipGame.getBackground().setFillParent(true);

        // Create menu elements
        shipImg = new Image(new Texture("explosion_2.png"));
        titleLabel = new Label("Sea Battle", skin, "default-font", Color.RED);
        gameOverLabel = new Label("Game over!", skin);
        String whoWon;
        if (thisPlayerWon) {
            whoWon = "You win!";
        } else {
            whoWon = "You lose!";
        }
        playerWonLabel = new Label(whoWon, skin);
        gameOverLabel.setAlignment(Align.center);
        mainMenuButton = new TextButton("Main Menu", skin);
        exitButton = new TextButton("Exit", skin);

        // Set element sizes
        float shipWidth = width - (2*battleshipGame.getBorder());
        float shipHeight = (shipWidth/shipImg.getWidth()) * shipImg.getHeight();
        shipImg.setSize(shipWidth, shipHeight);
        titleLabel.setFontScale(3 * battleshipGame.getFontScalingRatio());
        gameOverLabel.setSize(width/18, height/64);
        playerWonLabel.setSize(width/18, height/64);
        double bW = width/2.4, bH = bW/3;
        float btnSizeW = (float) bW;
        float btnSizeH = (float) bH;
        mainMenuButton.setSize(btnSizeW, btnSizeH);
        exitButton.setSize(btnSizeW, btnSizeH);

        // Set element positions
        titleLabel.setPosition((width/2) - (titleLabel.getWidth()/2), height - titleLabel.getHeight() - battleshipGame.getBorder());
        titleLabel.setAlignment(0);
        shipImg.setPosition(battleshipGame.getBorder(), height - (2 * battleshipGame.getBorder()) - titleLabel.getHeight() - shipHeight);
        gameOverLabel.setPosition(width / 2, height - (3 * battleshipGame.getBorder()) - gameOverLabel.getHeight() - shipHeight - titleLabel.getHeight());
        gameOverLabel.setAlignment(0);
        playerWonLabel.setPosition(width/2, height - (4 * battleshipGame.getBorder()) - gameOverLabel.getHeight() - playerWonLabel.getHeight() - shipHeight - titleLabel.getHeight());
        playerWonLabel.setAlignment(0);
        mainMenuButton.setPosition(battleshipGame.getBorder(), battleshipGame.getBorder());
        exitButton.setPosition(width - battleshipGame.getBorder() - btnSizeW, battleshipGame.getBorder());

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

    public void setWinningPlayer(boolean thisPlayerWon) {
        this.thisPlayerWon = thisPlayerWon;
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
