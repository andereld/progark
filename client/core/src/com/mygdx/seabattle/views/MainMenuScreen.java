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
public class MainMenuScreen implements Screen{
    private SpriteBatch batch;
    private Stage stage;
    private TextField nickNameField;
    private TextButton playButton, exitButton, howToPlayButton;
    private Label nickNameLabel;
    private SeaBattle seaBattleGame;
    private Skin skin;
    private int width, height;

    public MainMenuScreen(SeaBattle seaBattleGame) {
        this.seaBattleGame = seaBattleGame;
        width = seaBattleGame.getWidth();
        height = seaBattleGame.getHeight();
        skin = seaBattleGame.getSkin();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage); // So that the stage can receive input-events like button-clicks

        /** Create menu elements **/
        nickNameLabel = new Label("Enter nickname:", skin);
        nickNameField = new TextField("", skin);
        playButton = new TextButton("Find match", skin);
        exitButton = new TextButton("Exit", skin);
        howToPlayButton = new TextButton("How to play", skin);

        /** Calculate and set element sizes **/
        double nickNameFieldWidth = nickNameLabel.getWidth(), nickNameFieldHeight = height/38.4;
        nickNameField.setSize((float) nickNameFieldWidth, (float) nickNameFieldHeight);

        double bW = width/2.4, bH = (height - (7* seaBattleGame.getBorder()) - seaBattleGame.getTitleImg().getHeight() - seaBattleGame.getShipImg().getHeight() - nickNameLabel.getHeight())/3;
        float btnSizeW = (float) bW;
        float btnSizeH = (float) bH;
        playButton.setSize(btnSizeW,btnSizeH);
        exitButton.setSize(btnSizeW, btnSizeH);
        howToPlayButton.setSize(btnSizeW, btnSizeH);

        /** Calculate and set element positions **/
        float nickNameLabelPosX = (width/2) - nickNameLabel.getWidth() - (seaBattleGame.getBorder()/2);
        float nickNameLabelPosY = seaBattleGame.getShipImg().getY() - seaBattleGame.getBorder() - nickNameLabel.getHeight();
        nickNameLabel.setPosition(nickNameLabelPosX, nickNameLabelPosY);

        float nickNameFieldPosX = (width/2) + (seaBattleGame.getBorder()/2);
        float nickNameFieldPosY = nickNameLabelPosY + 5;
        nickNameField.setPosition(nickNameFieldPosX, nickNameFieldPosY);

        float playButtonPosX = (width/2) - (btnSizeW/2);
        float playButtonPosY = nickNameLabelPosY - (seaBattleGame.getBorder()) - btnSizeH;
        playButton.setPosition(playButtonPosX, playButtonPosY);

        float howToPlayButtonPosX = playButtonPosX;
        float howToPlayButtonPosY = playButtonPosY - seaBattleGame.getBorder() - btnSizeH;
        howToPlayButton.setPosition(howToPlayButtonPosX, howToPlayButtonPosY);

        float exitButtonPosX = playButtonPosX;
        float exitButtonPosY = howToPlayButtonPosY - seaBattleGame.getBorder() - btnSizeH;
        exitButton.setPosition(exitButtonPosX, exitButtonPosY);

        /** Add groups and elements to the stage **/
        stage.addActor(seaBattleGame.getBackground());
        seaBattleGame.getBackground().setFillParent(true);
        stage.addActor(seaBattleGame.getTitleImg());
        stage.addActor(seaBattleGame.getShipImg());
        stage.addActor(nickNameLabel);
        stage.addActor(nickNameField);
        nickNameField.setMessageText("DefaultPlayer");
        stage.addActor(playButton);
        stage.addActor(howToPlayButton);
        stage.addActor(exitButton);

        /** Add listeners **/
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String nickName = nickNameField.getText();
                if (nickName.length() == 0) {
                    nickName = "DefaultPlayer";
                }
                seaBattleGame.findMatch(nickName);
            }
        });
        howToPlayButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                seaBattleGame.setTutorialScreen();
            }
        });
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                seaBattleGame.exit();
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
