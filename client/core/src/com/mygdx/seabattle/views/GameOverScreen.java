package com.mygdx.seabattle.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.seabattle.SeaBattle;


/**
 * Created by Torstein on 16.03.2015.
 */
public class GameOverScreen implements Screen {
    private SpriteBatch batch;
    private Stage stage;
    private Label gameOverLabel;
    private Label playerWonLabel;
    private TextButton mainMenuButton;
    private TextButton exitButton;
    private SeaBattle seaBattleGame;
    private Skin skin;
    private int width, height;
    private boolean thisPlayerWon;

    public GameOverScreen(SeaBattle seaBattleGame) {
        this.seaBattleGame = seaBattleGame;
        width = seaBattleGame.getWidth();
        height = seaBattleGame.getHeight();
    }

    @Override
    public void show() {
        skin = seaBattleGame.getSkin();
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage); // So that the stage can receive input-events like button-clicks

        /** Create menu elements **/
        gameOverLabel = new Label("Game over!", skin);
        playerWonLabel = new Label(thisPlayerWon ? "You win!" : "You lose!", skin);
        mainMenuButton = new TextButton("Main Menu", skin);
        exitButton = new TextButton("Exit", skin);

        /** Set element sizes **/
        gameOverLabel.setSize(width/18, height/64);
        playerWonLabel.setSize(width/18, height/64);

        double bW = width/2.4, bH = bW/3;
        float btnSizeW = (float) bW;
        float btnSizeH = (float) bH;
        mainMenuButton.setSize(btnSizeW, btnSizeH);
        exitButton.setSize(btnSizeW, btnSizeH);

        /** Set element positions **/
        float gameOverLabelPosX = width/2;
        float gameOverLabelPosY = seaBattleGame.getShipImg().getY() - seaBattleGame.getBorder() - gameOverLabel.getHeight();
        gameOverLabel.setPosition(gameOverLabelPosX, gameOverLabelPosY);
        gameOverLabel.setAlignment(0);

        float playerWonLabelPosX = width/2;
        float playerWonLabelPosY = gameOverLabel.getY() - seaBattleGame.getBorder() - playerWonLabel.getHeight();
        playerWonLabel.setPosition(playerWonLabelPosX, playerWonLabelPosY);
        playerWonLabel.setAlignment(0);

        float mainMenuButtonPosX = seaBattleGame.getBorder();
        float mainMenuButtonPosY = seaBattleGame.getBorder();
        mainMenuButton.setPosition(mainMenuButtonPosX, mainMenuButtonPosY);

        float exitButtonPosX = width - seaBattleGame.getBorder() - btnSizeW;
        float exitButtonPosY = seaBattleGame.getBorder();
        exitButton.setPosition(exitButtonPosX, exitButtonPosY);

        /** Add groups and elements to the stage **/
        stage.addActor(seaBattleGame.getBackground());
        seaBattleGame.getBackground().setFillParent(true);
        stage.addActor(seaBattleGame.getTitleImg());
        stage.addActor(seaBattleGame.getShipImg());
        stage.addActor(gameOverLabel);
        stage.addActor(playerWonLabel);
        stage.addActor(mainMenuButton);
        stage.addActor(exitButton);

        /** Add listeners **/
        mainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                seaBattleGame.setMainMenuScreen();
            }
        });
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                seaBattleGame.exit();
            }
        });
    }

    public void setWinningPlayer(boolean thisPlayerWon) {
        this.thisPlayerWon = thisPlayerWon;
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