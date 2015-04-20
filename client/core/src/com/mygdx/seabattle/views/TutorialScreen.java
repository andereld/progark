package com.mygdx.seabattle.views;

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
import com.mygdx.seabattle.SeaBattle;

/**
 * @author H�kon Meyer T�rnquist <haakon.t@gmail.com>
 *         Date: 20.04.2015 11:22.
 */
public class TutorialScreen implements Screen {

    private SeaBattle game;
    private Stage stage;
    private int width, height;
    private Skin skin;
    private Image howToPlayImg, hitImg, missImg, oceanImg, shipImg;
    private Label introLabel, hitLabel, missLabel, oceanLabel, shipLabel;
    private TextButton mainMenuButton;
    private SpriteBatch batch;
    private Table container, introContainer;
    private float imageSize, cellSpacing, labelWidth, labelHeight, introLabelWidth, introLabelHeight;

    public TutorialScreen(SeaBattle game) {
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
        howToPlayImg = new Image(new Texture("howtoplay.png"));
        hitImg = new Image(new Texture("hit64x64.png"));
        missImg = new Image(new Texture("miss64x64.png"));
        oceanImg = new Image(new Texture("ocean64x64.png"));
        shipImg = new Image(new Texture("ship64x64.png"));

        introLabel = new Label("Welcome to Sea Battle. The purpose of this game is to sink all of your enemy's ships before he or she can sink yours. This is done by firing shots at a grid of squares of sea that may or may not contain a ship. For each shot, you will be told whether it was a miss or a hit. The game is over when one player has destroyed all of the opponent's ships.", skin);
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
        labelWidth = width - (6*game.getBorder()) - imageSize;

        float howToPlayImgWidth = width - (2*game.getBorder());
        float howToPlayImgHeight = (howToPlayImgWidth/ howToPlayImg.getWidth()) * howToPlayImg.getHeight();
        howToPlayImg.setSize(howToPlayImgWidth, howToPlayImgHeight);

        float btnWidth = width - 2* game.getBorder();
        float btnHeight = btnWidth/6;
        mainMenuButton.setSize(btnWidth, btnHeight);

        populateContainer();

        float containerHeight = (10*cellSpacing) + oceanLabel.getHeight() + shipLabel.getHeight() + hitLabel.getHeight() + missLabel.getHeight();
        float containerWidth = width - (2*game.getBorder());
        container.setSize(containerWidth, containerHeight);

        float introContainerWidth = width - (2*game.getBorder());
        float introContainerHeight = height - howToPlayImgHeight - containerHeight - btnHeight - (5*game.getBorder());

        introContainer.setSize(introContainerWidth, introContainerHeight);
        introContainer.add(introLabel).width(introContainerWidth).height(introContainerHeight);

        /** Calculate and set element positions **/
        float howToPlayImgPosX = game.getBorder();
        float howToPlayImgPosY = height - game.getBorder() - howToPlayImgHeight;
        howToPlayImg.setPosition(howToPlayImgPosX, howToPlayImgPosY);

        float containerPosX = game.getBorder();
        float containerPosY = (2 * game.getBorder()) + btnHeight;
        container.setPosition(containerPosX, containerPosY);

        float introContainerPosX = game.getBorder();
        float introContainerPosY = containerPosY + container.getHeight() + game.getBorder();
        introContainer.setPosition(introContainerPosX, introContainerPosY);

        float buttonPosX = game.getBorder();
        float buttonPosY = game.getBorder();
        mainMenuButton.setPosition(buttonPosX, buttonPosY);

        /** Add elements to screen **/
        stage.addActor(game.getBackground());
        game.getBackground().setFillParent(true);

        stage.addActor(howToPlayImg);
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
        container.add(oceanImg).size(imageSize, imageSize).space(cellSpacing);
        container.add(oceanLabel).width(labelWidth).space(cellSpacing);
        container.row();
        container.add(shipImg).size(imageSize, imageSize).space(cellSpacing);
        container.add(shipLabel).width(labelWidth).space(cellSpacing);
        container.row();
        container.add(hitImg).size(imageSize, imageSize).space(cellSpacing);
        container.add(hitLabel).width(labelWidth).space(cellSpacing);
        container.row();
        container.add(missImg).size(imageSize, imageSize).space(cellSpacing);
        container.add(missLabel).width(labelWidth).space(cellSpacing);
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
