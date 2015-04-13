package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * @author Haakon Meyer Toernquist <haakon.t@gmail.com>
 *         Date: 12.03.2015 14:23.
 */
public class GameScreen implements Screen {

    private Battleship game;
    private BoardGUI bigBoard, smallBoard;
    private Stage stage;
    private Skin skin;
    private TextButton btnFire;
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

        stage.addActor(game.getBackground());
        game.getBackground().setFillParent(true);

        border = game.getBorder();

        bigBoard = new BoardGUI(this, true, false);
        smallBoard = new BoardGUI(this, false, true);

        drawButtons();
        drawBoards();
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

        bigBoard.setPosition(border, Gdx.graphics.getHeight() - (11 * bigBoard.getCellSize()) - border - 30);
        int mainBoardSize = Gdx.graphics.getWidth() - (2 * border) - 30;
        bigBoard.setSize(mainBoardSize, mainBoardSize);

        smallBoard.setPosition((2 * border) + btnWidth, border);
        int secondaryBoardSize = Gdx.graphics.getWidth() - (3 * border) - Math.round(btnWidth);
        smallBoard.setSize(secondaryBoardSize, secondaryBoardSize);

        stage.addActor(bigBoard);
        stage.addActor(smallBoard);
    }

    /**
     * This method is responsible for drawing the three buttons
     */
    public void drawButtons() {
        double bW = Gdx.graphics.getWidth()/2.4, bH = (4 * smallBoard.getCellSize()) - border;
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
        btnFire.setColor(Color.RED);
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
    }

    public void drawLabel(String text) {
        Label label = new Label(text, skin);
        label.setSize(300, 50);
        float labelHeight = (Gdx.graphics.getHeight() - border - 11 * (smallBoard.getCellSize() + bigBoard.getCellSize()))/2 + (11 * smallBoard.getCellSize());
        label.setPosition(Gdx.graphics.getWidth() / 2, labelHeight);
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
        board.fireAtCell(x, y, shipWasHit);
    }

    public void btnSwitchClicked() {
        changeBoards();
    }

    /**
     * This method moves the small board up to the big board screen, and vice versa
     */
    public void changeBoards() {
        BoardGUI mainBoardOld = bigBoard;
        bigBoard = smallBoard;
        smallBoard = mainBoardOld;

        bigBoard.drawCells(0, 0, false, true);
        smallBoard.drawCells(0, 0, false, true);

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

    // @todo haakon: virke disse metodene?
    // Should return the board of this player
    private BoardGUI getThisPlayersBoard() {
        if (bigBoard.isMainBoard()) {
            return bigBoard;
        }
        return smallBoard;
    }

    // Should return the board of the opponent
    private BoardGUI getOtherPlayersBoard() {
        if (!bigBoard.isMainBoard()) {
            return bigBoard;
        }
        return smallBoard;
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public int getBorder() {
        return border;
    }

    public Skin getSkin() {
        return skin;
    }
}
