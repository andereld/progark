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
    private Texture shipTex, hitTex, missTex, oceanTex, oceanMarkedTex;
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
    public BoardGUI(final GameScreen gameScreen, boolean opponentBoard, boolean smallBoard, Board board) {
        this.gameScreen = gameScreen;
        this.opponentBoard = opponentBoard;
        this.smallBoard = smallBoard;

        shipTex = new Texture("ship64x64.png");
        hitTex = new Texture("hit64x64.png");
        missTex = new Texture("miss64x64.png");
        oceanTex = new Texture("ocean64x64.png");
        oceanMarkedTex = new Texture("oceanMarked64x64.png");

        this.board = board;
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
     * @param change = If changing the draw size of the board
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

        // @todo Maybe the labels should be drawn in GameScreen instead? Then we only have to draw them once (they are not board-dependent)
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
                Cell cell = board.getCell(j,i);
                if(opponentBoard && cell.containsShip() && !cell.isHit()) {
                    cells[i][j] = new Image(oceanTex);
                    cells[i][j].setName("ocean");
                }
                else if (cell.containsShip() && !cell.isHit()) {
                    cells[i][j] = new Image(shipTex);
                    cells[i][j].setName("ship");
                }
                else if(cell.isHit() && cell.containsShip()) {
                    cells[i][j] = new Image(hitTex);
                    cells[i][j].setName("hit");
                }
                else if(!cell.containsShip() && cell.isHit()) {
                    cells[i][j] = new Image(missTex);
                    cells[i][j].setName("miss");
                }
                else {
                    cells[i][j] = new Image(oceanTex);
                    cells[i][j].setName("ocean");
                }
                if(i == x && j == y && marker && cells[i][j].getName().equals("ocean")) {
                    cells[i][j] = new Image(oceanMarkedTex);
                }
                Actor cellActor = cells[i][j];
                // There should only be listeners on the opponents board (you never fire at your own board!)
                if (opponentBoard) {
                    final int row = i, column = j;
                    cellActor.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent e, float x, float y) {
                            cellClicked(row, column);
                        }
                    });
                }
                this.add(cellActor).width(cellSize).height(cellSize).space(cellSpacing);

            }
            this.row();
        }
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setBoard(Board board) {
        this.board = board;
        drawCells(0,0,false,false);
    }
}