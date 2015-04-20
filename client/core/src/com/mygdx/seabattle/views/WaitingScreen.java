package com.mygdx.seabattle.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.seabattle.SeaBattle;

/**
 * Created by Torstein on 17.03.2015.
 */
public class WaitingScreen implements Screen{
    private SpriteBatch batch;
    private Stage stage;
    private TextButton cancelButton;
    private Label waitingLabel;
    private SeaBattle seaBattleGame;
    private Skin skin;
    private int width, height;

    public WaitingScreen (SeaBattle seaBattleGame) {
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
        waitingLabel = new Label("Finding match...", skin);
        waitingLabel.setAlignment(Align.center);
        cancelButton = new TextButton("Cancel", skin);

        /** Set element sizes **/
        waitingLabel.setSize(width / 18, height / 64);
        double bW = width/2.4, bH = bW/3;
        float btnSizeW = (float) bW;
        float btnSizeH = (float) bH;
        cancelButton.setSize(btnSizeW,btnSizeH);

        /** Set element positions **/
        float waitingLabelPosX = (width/2);
        float waitingLabelPosY = seaBattleGame.getShipImg().getY() - seaBattleGame.getBorder() - waitingLabel.getHeight();
        waitingLabel.setPosition(waitingLabelPosX, waitingLabelPosY);
        waitingLabel.setAlignment(0);

        float cancelButtonPosX = (width/2) - (btnSizeW/2);
        float cancelButtonPosY = waitingLabelPosY - seaBattleGame.getBorder() - cancelButton.getHeight();
        cancelButton.setPosition(cancelButtonPosX, cancelButtonPosY);

        /** Add groups and elements to the stage **/
        stage.addActor(seaBattleGame.getBackground());
        seaBattleGame.getBackground().setFillParent(true);
        stage.addActor(seaBattleGame.getTitleImg());
        stage.addActor(seaBattleGame.getShipImg());
        stage.addActor(waitingLabel);
        stage.addActor(cancelButton);

        /** Add listeners **/
        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                seaBattleGame.cancelFindMatch();
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
