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
    private Label boardLabel;
    private int border;
    private float btnWidth, btnHeight;
    private String playerName;
    private String opponentName;
    private boolean opponentIsMainView;

    /**
     * Constructor
     * @param game connects the GameGUI to a Battleship instance
     */
    public GameScreen(Battleship game) {

        this.game = game;
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = game.getSkin();
        playerName = game.getGameNetworkController().getPlayerController().getPlayer().getUsername();
        opponentName = game.getGameNetworkController().getPlayerController().getOpponent().getUsername();
        opponentIsMainView = true;
        stage.addActor(game.getBackground());
        game.getBackground().setFillParent(true);
        border = game.getBorder();

        bigBoard = new BoardGUI(this, true, false, game.getGameNetworkController().getPlayerController().getOpponent().getBoard());
        smallBoard = new BoardGUI(this, false, true, game.getGameNetworkController().getPlayerController().getPlayer().getBoard());

        boardLabel = new Label("", skin);
        drawButtons();
        drawBoards();
        drawLabel(opponentName);
        waitForTurn();
    }

    public void waitForTurn() {
        btnFire.setText("Wait for turn");
        btnFire.setTouchable(Touchable.disabled);
        game.getGameNetworkController().getPlayerController().waitForTurn();
    }

    public void myTurn() {
        btnFire.setText("Fire");
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
            public void clicked(InputEvent e, float x, float y) {
                btnQuitClicked();
            }
        });

        // "Switch board" button
        final TextButton btnSwitch = new TextButton("Switch board", skin);
        btnSwitch.setPosition(border, 2 * border + btnHeight);
        btnSwitch.setSize(btnWidth, btnHeight);
        btnSwitch.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent e, float x, float y) {
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
            public void clicked(InputEvent event, float x, float y) {
                btnFireClicked();
            };
        });

        stage.addActor(btnFire);
        stage.addActor(btnSwitch);
        stage.addActor(btnQuit);
    }

    public void drawLabel(String text) {
        boardLabel.setText(text);
        float boardLabelPosX = game.getWidth()/2;
        float boardLabelPosY = bigBoard.getY() - border - boardLabel.getHeight();
        boardLabel.setPosition(boardLabelPosX, boardLabelPosY);
        boardLabel.setAlignment(0);
        stage.addActor(boardLabel);
    }

    public void btnQuitClicked() {
        game.setMainMenuScreen();
    }

    public void btnFireClicked() {
        int x = getOpponentBoard().getMarkedColumn();
        int y = getOpponentBoard().getMarkedRow();
        game.getGameNetworkController().getPlayerController().fireAtLocation(x, y);
    }

    public void changeBoardLabel() {
        if (opponentIsMainView) {
            boardLabel.setText(opponentName);
        } else {
            boardLabel.setText(playerName);
        }
    }

    // For switching between what player and opponent board
    // Maybe this should be done automatically, depending on who's turn it is??
    public void btnSwitchClicked() {
        opponentIsMainView = !opponentIsMainView;
        changeBoardLabel();
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

    /** Returns the board of this player **/
    public BoardGUI getMainBoard() {
        if (bigBoard.isMainBoard()) {
            return bigBoard;
        }
        return smallBoard;
    }

    /** Returns the board of the opponent **/
    public BoardGUI getOpponentBoard() {
        if (!bigBoard.isMainBoard()) {
            return bigBoard;
        }
        return smallBoard;
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
    }

    public int getBorder() {
        return border;
    }

    public Skin getSkin() {
        return skin;
    }
}
