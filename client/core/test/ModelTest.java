

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.mygdx.game.models.Board;
import com.mygdx.game.models.Cell;
import com.mygdx.game.models.Player;

import json.JsonHelper;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Created by andybb on 19.03.15.
 */
public class ModelTest {

    private Board board1, board2;
    private Player player1, player2;
    private String gameString = "";
    private JsonValue game;


    @Test
    public void testBoardCreation() {
        this.board1 = new Board();
        this.board2 = new Board();
        this.game = JsonHelper.parseJson(gameString).get("game");

        board1.createFromJson(JsonHelper.prettyPrint(game.get("Boards").get(0)));
        board2.createFromJson(JsonHelper.prettyPrint(game.get("Boards").get(1)));

        assertNotNull(board1);
        assertNotNull(board2);
    }

    @Test
    public void testPlayerCreation(){
        player1 = new Player(game.get("player1").toString(),board1 );
        player2 = new Player(game.get("player2").toString(),board2 );

        assertEquals("Given username is the same as in the model for player 1", player1.getUsername().toString(), game.get("player1").toString());
        assertEquals("Given username is the same as in the model for player 2", player2.getUsername().toString(), game.get("player2").toString());
    }

    @Test
    public void testCreatingCell(){
        // Create some cell from JSON
        String json = "{hasBeenHit: false, containsShip: true}";
        Cell cell = new Cell(2, 3);
        cell.createFromJson(json);

        assertEquals(false, cell.isHit());
        assertEquals(true, cell.isShip());

    }

    @Test
    public void testGettingCell(){

        // Take one cell from each board and see that the values are the same as in the JSON-game
        int x1 = 2;
        int y1 = 6;
        int x2 = 4;
        int y2 = 6;

        Cell cell1 = board1.getCell(x1, y1);
        Cell cell2 = board2.getCell(x2, y2);

        assertNotNull(cell1);
        assertNotNull(cell2);

        JsonValue jsonCell1 = game.get("Boards").get(0).get((y1*10 + x1));
        JsonValue jsonCell2 = game.get("Boards").get(1).get((y2*10) + x2);

        assertEquals("Cell has same isHit value as the JSON given", cell1.isHit(), jsonCell1.get("hasBeenHit").asBoolean());
        assertEquals("Cell has same isShip value as the JSON given", cell1.isShip(), jsonCell1.get("containsShip").asBoolean());

        assertEquals("Cell has same isHit value as the JSON given", cell2.isHit(), jsonCell2.get("hasBeenHit").asBoolean() );
        assertEquals("Cell has same isShip value as the JSON given", cell2.isShip(), jsonCell2.get("containsShip").asBoolean());

    }

}
