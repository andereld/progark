package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.models.Board;
import com.mygdx.game.models.Cell;

/**
 * @author Haakon Meyer Toernquist <haakon.t@gmail.com>
 *         Date: 12.03.2015 14:23.
 */
public class BoardGUI extends Table {

    private GameScreen gameScreen;

    // Textures and images
    private Texture shipTex, hitTex, missTex, oceanTex, oceanMarkedTex;
    private Image   ship, hit, miss, ocean, oceanMarked;

    private Actor[][] cells;
    private int cellSize;
    private int markedRow, markedColumn;
    private int cellSpacing;
    private final String[] letters = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private boolean opponentBoard, smallBoard;
    private Board board;

    /**
     * Constructor
     * @param gameScreen connects this BoardGUI with a GameGUI
     * @param opponentBoard says if this BoardGUI is your or your opponent's board
     */
    public BoardGUI(final GameScreen gameScreen, boolean opponentBoard, boolean smallBoard) {
        this.gameScreen = gameScreen;
        this.opponentBoard = opponentBoard;
        this.smallBoard = smallBoard;

        shipTex = new Texture("ship64x64.png");
        hitTex = new Texture("hit64x64.png");
        missTex = new Texture("miss64x64.png");
        oceanTex = new Texture("ocean64x64.png");
        oceanMarkedTex = new Texture("oceanMarked64x64.png");

        ship = new Image(shipTex);
        hit = new Image(hitTex);
        miss = new Image(missTex);
        ocean = new Image(oceanTex);
        oceanMarked = new Image(oceanMarkedTex);

        // TODO: Fetch board.
        // this.board = SOMETHING;
        cells = new Actor[10][10];
        cellSpacing = 3;
        cellSize = (Gdx.graphics.getWidth() - (2 * gameScreen.getBorder()))/11 - cellSpacing;

        if(smallBoard) {
            cellSize /=2;
        }
        drawCells(0, 0, false, false);
    }

    /**
     * Decides what happens when a cell is clicked (or touched)
     * @param row = row of the clicked cell
     * @param column = column of the clicked cell
     */
    public void cellClicked(int row, int column) {
        drawCells(row, column, true, false);
        markedColumn = column;
        markedRow = row;
    }

    public boolean isMainBoard() {
        return !opponentBoard;
    }


    public int getMarkedRow() {
        return markedRow;
    }

    public int getMarkedColumn() {
        return markedColumn;
    }

    /**
     * Responsible for drawing and updating the cells when needed
     * @param x = if a single cell is being changed, this is its row
     * @param y = this is its column
     * @param marker = what?
     * @param change = what?
     */
    public void drawCells(int x, int y, boolean marker, boolean change) {
        if(!this.smallBoard && change) {
            this.smallBoard = true;
            cellSize /= 2;
        }
        else if(this.smallBoard && change) {
            this.smallBoard = false;
            cellSize *= 2;
        }

        this.clearChildren();

        Label label = new Label("", gameScreen.getSkin());
        label.setAlignment(1);
        this.add(label).width(cellSize).height(cellSize).space(cellSpacing);

        // Make labels for the numbers on top of the board
        for(int i = 1; i < 11; i++) {
            label = new Label(Integer.toString(i), gameScreen.getSkin());
            label.setAlignment(1);
            this.add(label).width(cellSize).height(cellSize).space(cellSpacing);
        }
        this.row();

        // Make labels for the letters on the left side of the board and draw the cells
        for(int i = 0; i < 10; i++) {
            label = new Label(letters[i], gameScreen.getSkin());
            this.add(label).width(cellSize).height(cellSize).space(cellSpacing);
            for(int j = 0; j < 10; j++) {
                for(Cell cell : board.getCells()) {
                    if(cell.getX() == i && cell.getY() == j) {
                        if(cell.isContainsShip()) {
                            if(opponentBoard) {
                                if(cell.isHit()) {
                                    cells[i][j] = hit;
                                }
                                else {
                                    cells[i][j] = ocean;
                                }
                            }
                            else {
                                cells[i][j] = ship;
                            }
                        }
                        else {
                            cells[cell.getX()][cell.getY()] = ocean;
                        }
                    }
                    Actor cellActor = cells[i][j];
                    final int row = i, column = j;
                    cellActor.addListener(new ClickListener() {
                        @Override
                        public void touchUp(InputEvent e, float x, float y, int point, int button) {
                            cellClicked(row, column);
                        }
                    });
                    this.add(cellActor).width(cellSize).height(cellSize).space(cellSpacing);
                }
                if(i == x && j == y && marker && cells[i][j].equals(ocean)) {
                    cells[x][y] = oceanMarked;
                }
            }
            this.row();
        }
    }

    public int getCellSize() {
        return cellSize;
    }
}