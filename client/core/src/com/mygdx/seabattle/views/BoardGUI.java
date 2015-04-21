package com.mygdx.seabattle.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.seabattle.models.Board;
import com.mygdx.seabattle.models.Cell;

/**
 * @author Haakon Meyer Toernquist <haakon.t@gmail.com>
 *         Date: 12.03.2015 14:23.
 */
public class BoardGUI extends Table {

    private GameScreen gameScreen;
    private BoardTexture shipTex, hitTex, missTex, oceanTex, oceanMarkedTex;
    private Actor[][] cells;
    private int cellSize;
    private int markedRow, markedColumn;
    private int cellSpacing;
    private final String[] letters = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private boolean opponentBoard, smallBoard;
    private Board board;
    private boolean init; // First draw
    private boolean markCell; // Whether or not to mark a cell on draw
    private boolean cellIsMarked; // Whether or not a cell is marked on the board

    /**
     * Constructor
     * @param gameScreen connects this BoardGUI with a GameGUI
     * @param opponentBoard says if this BoardGUI is your or your opponent's board
     */
    public BoardGUI(final GameScreen gameScreen, boolean opponentBoard, boolean smallBoard, Board board) {
        this.gameScreen = gameScreen;
        this.opponentBoard = opponentBoard;
        this.smallBoard = smallBoard;

        shipTex = new BoardTexture("ship64x64.png");
        hitTex = new BoardTexture("hit64x64.png");
        missTex = new BoardTexture("miss64x64.png");
        oceanTex = new BoardTexture("ocean64x64.png");
        oceanMarkedTex = new BoardTexture("oceanMarked64x64.png");

        this.board = board;
        cells = new Actor[10][10];
        cellSpacing = 3;
        cellSize = (Gdx.graphics.getWidth() - (2 * gameScreen.getBorder()))/11 - cellSpacing;

        if(smallBoard) {
            cellSize /=2;
        }
        cellIsMarked = false;
        markCell = false;
        markedRow = -1;
        markedColumn = -1;
        init = true;
        drawCells();
        init = false;
    }

    /**
     * Decides what happens when a cell is clicked (or touched)
     * @param row = row of the clicked cell
     * @param column = column of the clicked cell
     */
    public void cellClicked(int row, int column) {
        if (cells[row][column].getName().equals("ocean")) {
            markedRow = row;
            markedColumn = column;
            markCell = true;
            drawCells();
        }
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

    // Change the size the board is drawn
    public void toggleSize() {
        if(smallBoard) {
            cellSize *= 2;
        } else {
            cellSize /= 2;
        }
        smallBoard = !smallBoard;
        if (cellIsMarked) {
            markCell = true;
        }
        drawCells();
    }

    /**
     * Responsible for drawing and updating the cells when needed
     */
    public void drawCells() {
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

        cellIsMarked = false;
        // Make labels for the letters on the left side of the board and draw the cells
        for(int i = 0; i < 10; i++) {
            label = new Label(letters[i], gameScreen.getSkin());
            this.add(label).width(cellSize).height(cellSize).space(cellSpacing);
            for(int j = 0; j < 10; j++) {
                Cell cell = board.getCell(j,i);
                if(opponentBoard && cell.containsShip() && !cell.isHit() && !markCell) {
                    drawCell(i,j, oceanTex);
                }
                else if (!opponentBoard && cell.containsShip() && !cell.isHit()) {
                    drawCell(i,j, shipTex);
                }
                else if(cell.isHit() && cell.containsShip()) {
                    drawCell(i,j, hitTex);
                }
                else if(!cell.containsShip() && cell.isHit()) {
                    drawCell(i,j, missTex);
                }
                else if(!init && (i == markedRow && j == markedColumn && markCell)) {
                    drawCell(i, j, oceanMarkedTex);
                    markCell = false;
                    cellIsMarked = true;
                }
                else {
                    drawCell(i,j, oceanTex);
                }

                Actor cellActor = cells[i][j];
                // There should only be listeners on the opponents board (you never fire at your own board!)
                if (opponentBoard) {
                    cellActor.addListener(cellListener(i, j));
                }
                this.add(cellActor).width(cellSize).height(cellSize).space(cellSpacing);

            }
            this.row();
        }
        if (!cellIsMarked) {
            markedColumn = -1;
            markedRow = -1;
        }
    }

    private ClickListener cellListener(int row, int column) {
        final int fRow = row, fColumn = column;
        return (new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y) {
                cellClicked(fRow, fColumn);
            }
        });
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setBoard(Board board) {
        this.board = board;
        drawCells();
    }

    public void drawCell(int i, int j, BoardTexture texture) {
        cells[i][j] = new Image(texture);
        cells[i][j].setName(texture.getName());
    }

}