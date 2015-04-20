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
 *         Date: 20.04.2015 13:52.
 */
public class CreatorScreen implements Screen {

    private SeaBattle game;
    private int width, height;
    private Skin skin;
    private SpriteBatch batch;
    private Stage stage;
    private Image creatorImg, andersImg, andreasImg, essoImg, haakonImg, matsImg, torsteinImg;
    private Label andersLabel, andreasLabel, essoLabel, haakonLabel, matsLabel, torsteinLabel;
    private Table container;
    private float imgWidth, imgHeight;
    private TextButton mainMenuButton;

    public CreatorScreen(SeaBattle game) {
        this.game = game;
        width = game.getWidth();
        height = game.getHeight();
        skin = game.getSkin();
    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        /** Create screen elements **/
        creatorImg = new Image(new Texture("creators.png"));
        container = new Table();

        andersImg = new Image(new Texture("anders.jpg"));
        andersImg.setAlign(Align.center);
        andreasImg = new Image(new Texture("andreas.jpg"));
        andreasImg.setAlign(Align.center);
        essoImg = new Image(new Texture("esso.jpg"));
        essoImg.setAlign(Align.center);
        haakonImg = new Image(new Texture("haakon.jpg"));
        haakonImg.setAlign(Align.center);
        matsImg = new Image(new Texture("mats.jpg"));
        matsImg.setAlign(Align.center);
        torsteinImg = new Image(new Texture("torstein.jpg"));
        torsteinImg.setAlign(Align.center);

        andersLabel = new Label("Anders W. Eldhuset", skin);
        andersLabel.setAlignment(Align.center);
        andreasLabel = new Label("Andreas Drivenes", skin);
        andreasLabel.setAlignment(Align.center);
        essoLabel = new Label("Stein-Otto Svorstoel", skin);
        essoLabel.setAlignment(Align.center);
        haakonLabel = new Label("Haakon M. Toernquist", skin);
        haakonLabel.setAlignment(Align.center);
        matsLabel = new Label("Mats Byrkjeland", skin);
        matsLabel.setAlignment(Align.center);
        torsteinLabel = new Label("Torstein Soernes", skin);
        torsteinLabel.setAlignment(Align.center);

        mainMenuButton = new TextButton("Main Menu", skin);

        /** Set element sizes **/
        float creatorImgWidth = width - (2*game.getBorder());
        float creatorImgHeight = (creatorImgWidth/creatorImg.getWidth()) * creatorImg.getHeight();
        creatorImg.setSize(creatorImgWidth, creatorImgHeight);

        float btnWidth = width - 2* game.getBorder();
        float btnHeight = btnWidth/6;
        mainMenuButton.setSize(btnWidth, btnHeight);

        imgHeight = (height - btnHeight - creatorImgHeight - (10*game.getBorder()) - (3*andersLabel.getHeight())) / 3 ;
        imgWidth = (imgHeight/andersImg.getHeight()) * andersImg.getWidth();

        populateContainer();

        float containerWidth = width - (2*game.getBorder());
        float containerHeight = height - creatorImgHeight - btnHeight - (4* game.getBorder());
        container.setSize(containerWidth, containerHeight);
        System.out.println(creatorImgHeight);
        System.out.println(containerHeight);
        System.out.println(3*imgHeight);

        /** Set element positions **/
        float creatorImgPosX = game.getBorder();
        float creatorImgPosY = height - game.getBorder() - creatorImgHeight;
        creatorImg.setPosition(creatorImgPosX, creatorImgPosY);

        float containerPosX = game.getBorder();
        float containerPosY = creatorImgPosY - game.getBorder() - containerHeight;
        container.setPosition(containerPosX, containerPosY);

        float mainMenuButtonPosX = game.getBorder();
        float mainMenuButtonPosY = game.getBorder();
        mainMenuButton.setPosition(mainMenuButtonPosX, mainMenuButtonPosY);

        /** Add actors to screen **/
        stage.addActor(game.getBackground());
        game.getBackground().setFillParent(true);

        stage.addActor(creatorImg);
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
        container.add(andersImg).width(imgWidth).height(imgHeight).space(game.getBorder());
        container.add(andreasImg).width(imgWidth).height(imgHeight).space(game.getBorder());
        container.row();
        container.add(andersLabel).space(game.getBorder());
        container.add(andreasLabel).space(game.getBorder());
        container.row();
        container.add(essoImg).width(imgWidth).height(imgHeight).space(game.getBorder());
        container.add(haakonImg).width(imgWidth).height(imgHeight).space(game.getBorder());
        container.row();
        container.add(essoLabel).space(game.getBorder());
        container.add(haakonLabel).space(game.getBorder());
        container.row();
        container.add(matsImg).width(imgWidth).height(imgHeight).space(game.getBorder());
        container.add(torsteinImg).width(imgWidth).height(imgHeight).space(game.getBorder());
        container.row();
        container.add(matsLabel).space(game.getBorder());
        container.add(torsteinLabel).space(game.getBorder());
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
