package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * @author Haakon Meyer Tørnquist <haakon.t@gmail.com>
 *         Date: 12.03.2015 14:23.
 */
public class GameScreen implements Screen {

    private Battleship game;
    private BoardGUI mainBoard, secondaryBoard;
    private Stage stage;
    private Skin skin;
    TextButton btnFire;
    private int border;
    private float btnWidth, btnHeight;

    /**
     * Constructor
     * @param game connects the GameGUI to a Battleship instance
     */
    public GameScreen(Battleship game) {
        this.game = game;

        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = game.getSkin();
        Texture backgroundTexture = new Texture("background.jpg");
        Image background = new Image(backgroundTexture);

        stage.addActor(background);
        background.setFillParent(true);

        border = Gdx.graphics.getWidth()/24;

        mainBoard = new BoardGUI(this, true);
        secondaryBoard = new BoardGUI(this, false);

        drawButtons();
        drawBoards();
        //stage.addActor(mainBoard);
        //stage.addActor(secondaryBoard);
        drawLabel("QK");

        waitForTurn();
    }

    public void waitForTurn() {
        btnFire.setText("Wait for turn");
        btnFire.setTouchable(Touchable.disabled);
        game.getGameNetworkController().getPlayerController().waitForTurn();
        btnFire.setText("Fire!");
        btnFire.setTouchable(Touchable.enabled);
    }

    /**
     * This method is responsible for drawing the big board and the small board
     */
    public void drawBoards() {
        mainBoard.setPosition(border, Gdx.graphics.getHeight() - (11 * mainBoard.getCellSize()) - border - 30);
        int mainBoardSize = Gdx.graphics.getWidth() - (2 * border) - 30;
        mainBoard.setSize(mainBoardSize, mainBoardSize);

        secondaryBoard.setPosition((2 * border) + btnWidth, border);
        int secondaryBoardSize = Gdx.graphics.getWidth() - (3 * border) - Math.round(btnWidth);
        secondaryBoard.setSize(secondaryBoardSize, secondaryBoardSize);

        stage.addActor(mainBoard);
        stage.addActor(secondaryBoard);
    }

    /**
     * This method is responsible for drawing the two buttons
     */
    public void drawButtons() {
        double bW = Gdx.graphics.getWidth()/2.4, bH = (4 * secondaryBoard.getCellSize()) - border;
        btnWidth = (float) bW;
        btnHeight = (float) bH;

        // "Quit" button
        TextButton btnQuit = new TextButton("Quit", skin);
        btnQuit.setPosition(border, border);
        btnQuit.setSize(btnWidth, btnHeight);
        btnQuit.addListener(new ClickListener() {

            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button) {
                btnQuitClicked();
            }
        });

        // "Switch board" button
        final TextButton btnSwitch = new TextButton("Switch board", skin);
        btnSwitch.setPosition(border, 2 * border + btnHeight);
        btnSwitch.setSize(btnWidth, btnHeight);
        btnSwitch.addListener(new ClickListener() {

            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button) {
                btnSwitchClicked();
            }
        });

        // "Fire" button
        btnFire = new TextButton("Fire", skin);
        btnFire.setPosition(border, 3 * border + 2 * btnHeight);
        btnFire.setSize(btnWidth, btnHeight);
        btnFire.addListener(new ClickListener() {

            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button) {
                btnFireClicked();
            }
        });

        stage.addActor(btnFire);
        stage.addActor(btnSwitch);
        stage.addActor(btnQuit);

        btnFire.setText("balle");
    }

    public void drawLabel(String text) {
        Label label = new Label(text, skin);
        label.setSize(300, 50);
        float labelHeight = (Gdx.graphics.getHeight() - border - 11 * (secondaryBoard.getCellSize() + mainBoard.getCellSize()))/2 + (11 * secondaryBoard.getCellSize());
        label.setPosition(Gdx.graphics.getWidth()/2, labelHeight);
        stage.addActor(label);
    }

    public void btnQuitClicked() {
        game.setScreen(new MainMenuScreen(game));
    }

    public void btnFireClicked() {
        BoardGUI board = getThisPlayersBoard();
        int x = board.getMarkedRow();
        int y = board.getMarkedColumn();
        game.getGameNetworkController().getPlayerController().fireAtLocation(x, y);
        waitForTurn();
    }

    //
    /**
     * When a shot has been fired, either at this players board or the opponents board,
     * this method should call the necessary methods to update the correct board
     * @param x = coordinate
     * @param y = coordinate
     * @param isMainBoard = if its the board of this player or opponent
     * @param shipWasHit = if the fire hit a ship or not
     */
    public void incomingFire(int x, int y, boolean isMainBoard, boolean shipWasHit) {
        BoardGUI board;
        if (isMainBoard) {
            board = getThisPlayersBoard();
        } else {
            board = getOtherPlayersBoard();
        }
        board.fireAtCell(x,y,shipWasHit);
    }

    public void btnSwitchClicked() {
        changeBoards();
    }

    // Should return the board of this player
    private BoardGUI getThisPlayersBoard() {
        if (mainBoard.isMainBoard()) {
            return mainBoard;
        }
        return secondaryBoard;
    }

    // Should return the board of the opponent
    private BoardGUI getOtherPlayersBoard() {
        if (!mainBoard.isMainBoard()) {
            return mainBoard;
        }
        return secondaryBoard;
    }

    /**
     * This method moves the small board up to the big board screen, and vice versa
     */
    public void changeBoards() {
        BoardGUI mainBoardOld = mainBoard;
        mainBoard = secondaryBoard;
        secondaryBoard = mainBoardOld;

        drawBoards();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
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
        skin.dispose();
    }

    public int getBorder() {
        return border;
    }

    public Skin getSkin() {
        return skin;
    }
}
