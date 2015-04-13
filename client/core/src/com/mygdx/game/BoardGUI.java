package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * @author Håkon Meyer Tørnquist <haakon.t@gmail.com>
 *         Date: 12.03.2015 14:23.
 */
public class BoardGUI extends Table {

    private GameScreen gameScreen;
    private Texture hit, miss, ocean, oceanMarked;
    private Actor[][] cells;
    private int cellSize;
    private int markedRow, markedColumn;
    private int cellSpacing;
    private final String[] letters = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private boolean isMainBoard;

    /**
     * Constructor
     * @param gameScreen connects this BoardGUI with a GameGUI
     * @param mainBoard says if this BoardGUI is a big or a small board
     */
    public BoardGUI(final GameScreen gameScreen, boolean mainBoard) {
        this.gameScreen = gameScreen;
        isMainBoard = mainBoard;
        hit = new Texture("hit64x64.png");
        miss = new Texture("miss64x64.png");
        ocean = new Texture("ocean64x64.png");
        oceanMarked = new Texture("oceanMarked64x64.png");

        cells = new Actor[10][10];
        cellSpacing = 3;
        cellSize = (Gdx.graphics.getWidth() - (2 * gameScreen.getBorder()))/11 - cellSpacing;

        if(!mainBoard) {
            cellSize /=2;
        }
        drawCells(0, 0, true, ocean);
    }

    /**
     * Decides what happens when a cell is clicked (or touched)
     * @param row = row of the clicked cell
     * @param column = column of the clicked cell
     */
    public void cellClicked(int row, int column) {
        drawCells(row, column, false, oceanMarked);
        markedColumn = column;
        markedRow = row;
        //System.out.println((row + 1) + ", " + (column + 1));
    }

    public boolean isMainBoard() {
        return isMainBoard;
    }

    public void fireAtCell(int x, int y, boolean isHit) {
        Texture texture;
        if (isHit) {
            texture = hit;
        } else {
            texture = miss;
        }
        drawCells(x, y, false, texture);
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
     * @param constructor is
     * @param texture = texture of the cell that is being chan
     */
    public void drawCells(int x, int y, boolean constructor, Texture texture) {
        this.clearChildren();

        Label label = new Label("", gameScreen.getSkin());
        label.setAlignment(1);
        this.add(label).width(cellSize).height(cellSize).space(cellSpacing);

        for(int i = 1; i < 11; i++) {
            label = new Label(Integer.toString(i), gameScreen.getSkin());
            label.setAlignment(1);
            this.add(label).width(cellSize).height(cellSize).space(cellSpacing);
        }
        this.row();

        for(int i = 0; i < 10; i ++) {
            label = new Label(letters[i], gameScreen.getSkin());
            this.add(label).width(cellSize).height(cellSize).space(cellSpacing);
            for(int j = 0; j < 10; j++) {
                if(i == x && j == y && !constructor) {
                    cells[i][j] = new Image(texture);
                }
                else {
                    cells[i][j] = new Image(ocean);
                }
                Actor cell = cells[i][j];
                final int row = i, column = j;
                cell.addListener(new ClickListener() {
                    @Override
                    public void touchUp(InputEvent e, float x, float y, int point, int button) {
                        cellClicked(row, column);
                    }
                });
                this.add(cell).width(cellSize).height(cellSize).space(cellSpacing);
            }
            this.row();
        }
    }

    public int getCellSize() {
        return cellSize;
    }
}
