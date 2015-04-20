package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * @author Håkon Meyer Tørnquist <haakon.t@gmail.com>
 *         Date: 20.04.2015 11:22.
 */
public class TutorialScreen implements Screen {

    private Battleship game;
    private Stage stage;
    private int width, height;
    private Skin skin;
    private Image howtoplayImg, hitImg, missImg, oceanImg, shipImg;
    private Label introLabel, hitLabel, missLabel, oceanLabel, shipLabel;
    private TextButton mainMenuButton;
    private SpriteBatch batch;
    private Table container, introContainer;
    private float imageSize, cellSpacing, labelWidth, labelHeight, introLabelWidth, introLabelHeight;

    public TutorialScreen(Battleship game) {
        this.game = game;
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        width = game.getWidth();
        height = game.getHeight();
        skin = game.getSkin();
        batch = new SpriteBatch();
    }
    @Override
    public void show() {
        /** Create screen elements **/
        container = new Table();
        introContainer = new Table();
        howtoplayImg = new Image(new Texture("howtoplay.png"));
        hitImg = new Image(new Texture("hit64x64.png"));
        missImg = new Image(new Texture("miss64x64.png"));
        oceanImg = new Image(new Texture("ocean64x64.png"));
        shipImg = new Image(new Texture("ship64x64.png"));

        introLabel = new Label("Welcome to Sea Battle. The purpose of this game is to sink all of your enemy's ships before he or she can sink yours. This is done by firing shots at a grid of squares of sea that may or may not contain a ship. For each shot, you will be told whether it was a miss or a hit. The game is over when one player has destroyed all of the opponent's ship.", skin);
        introLabel.setAlignment(Align.center);
        hitLabel = new Label("This image means that the shot fired at this cell was a hit.", skin);
        missLabel = new Label("This image means that the shot fired at this cell was a miss.", skin);
        oceanLabel = new Label("This image means that the cell has not yet been shot at.", skin);
        shipLabel = new Label("This image means that the cell contains a ship that has not yet been shot at. Will only be shown on your own board.", skin);

        introLabel.setWrap(true);
        hitLabel.setWrap(true);
        missLabel.setWrap(true);
        oceanLabel.setWrap(true);
        shipLabel.setWrap(true);

        mainMenuButton = new TextButton("Main Menu", skin);

        /** Calculate and set element sizes **/
        imageSize = height/20;
        cellSpacing = game.getBorder();

        float howToPlayImgWidth = width - (2*game.getBorder());
        float howToPlayImgHeight = (howToPlayImgWidth/howtoplayImg.getWidth()) * howtoplayImg.getHeight();
        howtoplayImg.setSize(howToPlayImgWidth, howToPlayImgHeight);

        labelWidth = width - (3*game.getBorder()) - hitImg.getWidth();
        labelHeight = hitImg.getHeight();
        hitLabel.setSize(labelWidth, labelHeight);
        missLabel.setSize(labelWidth, labelHeight);
        oceanLabel.setSize(labelWidth, labelHeight);
        shipLabel.setSize(labelWidth, labelHeight);

        float btnWidth = width - 2* game.getBorder();
        float btnHeight = btnWidth/6;
        mainMenuButton.setSize(btnWidth, btnHeight);

        float containerHeight = oceanLabel.getHeight() + shipLabel.getHeight() + hitLabel.getHeight() + missLabel.getHeight() + (3* game.getBorder());
        float containerWidth = width - (2*game.getBorder());
        container.setSize(containerWidth, containerHeight);

        introLabelWidth = width - (2*game.getBorder());
        introLabelHeight = height - containerHeight - howToPlayImgHeight - btnHeight - (5*game.getBorder());
        introLabel.setSize(introLabelWidth, introLabelHeight);

        introContainer.setSize(introLabelWidth, introLabelHeight);

        introContainer.add(introLabel).width(introLabelWidth).height(introLabelHeight).space(cellSpacing);
        introContainer.row();
        populateContainer();

        /** Calculate and set element positions **/
        float howToPlayImgPosX = game.getBorder();
        float howToPlayImgPosY = height - game.getBorder() - howToPlayImgHeight;
        howtoplayImg.setPosition(howToPlayImgPosX, howToPlayImgPosY);

        float introContainerPosX = game.getBorder();
        float introContainerPosY = howToPlayImgPosY - game.getBorder() - introContainer.getHeight();
        introContainer.setPosition(introContainerPosX, introContainerPosY);

        float containerPosX = game.getBorder();
        float containerPosY = introContainerPosY - game.getBorder() - container.getHeight();
        container.setPosition(containerPosX, containerPosY);

        float buttonPosX = game.getBorder();
        float buttonPosY = game.getBorder();
        mainMenuButton.setPosition(buttonPosX, buttonPosY);

        /** Add elements to screen **/
        stage.addActor(game.getBackground());
        game.getBackground().setFillParent(true);

        stage.addActor(howtoplayImg);
        stage.addActor(introContainer);
        stage.addActor(container);
        stage.addActor(mainMenuButton);

        /** Add listeners **/
        mainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setMainMenuScreen();
            }
        });
    }

    public void populateContainer() {
        container.add(oceanImg).width(imageSize).height(imageSize).space(cellSpacing);
        container.add(oceanLabel).width(labelWidth).height(labelHeight).space(cellSpacing);
        container.row();
        container.add(shipImg).width(imageSize).height(imageSize).space(cellSpacing);
        container.add(shipLabel).width(labelWidth).height(labelHeight).space(cellSpacing);
        container.row();
        container.add(hitImg).width(imageSize).height(imageSize).space(cellSpacing);
        container.add(hitLabel).width(labelWidth).height(labelHeight).space(cellSpacing);
        container.row();
        container.add(missImg).width(imageSize).height(imageSize).space(cellSpacing);
        container.add(missLabel).width(labelWidth).height(labelHeight).space(cellSpacing);
        container.row();
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

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }
}
