

import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.seabattle.models.Board;
import com.mygdx.seabattle.models.Cell;
import com.mygdx.seabattle.models.Player;

import com.mygdx.seabattle.json.JsonHelper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by andybb on 19.03.15.
 */
public class ModelTest {

    private Board board1, board2;
    private Player player1, player2;
    private String gameString = "{\"game\":{\"id\":6,\"player1\":\"esso3\",\"player2\":\"esso2\",\"next\":\"esso3\",\"finished\":false,\"createdAt\":\"2015-04-10T13:46:34.091Z\",\"updatedAt\":\"2015-04-10T13:46:34.091Z\",\"Boards\":[{\"cells\":[{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false}],\"gameOver\":false,\"id\":11,\"cellString\":\"................................SS.........SSS........SSSS.....SSS.....SSS.......SSS................\",\"createdAt\":\"2015-04-10T13:46:34.116Z\",\"updatedAt\":\"2015-04-10T13:46:34.116Z\",\"GameId\":6},{\"cells\":[{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":true,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false},{\"containsShip\":false,\"hasBeenHit\":false}],\"gameOver\":false,\"id\":12,\"cellString\":\"................................SS.........SSS........SSSS.....SSS.....SSS.......SSS................\",\"createdAt\":\"2015-04-10T13:46:34.117Z\",\"updatedAt\":\"2015-04-10T13:46:34.117Z\",\"GameId\":6}]}}";
    private JsonValue game;


    @Before
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

        assertNotNull(player1.getBoard());
        assertNotNull(player2.getBoard());
    }

    @Test
    public void testCreatingCell(){
        // Create some cell from JSON
        String json = "{hasBeenHit: false, containsShip: true}";
        Cell cell = new Cell(2, 3);
        cell.createFromJson(json);

        assertEquals(false, cell.isHit());
        assertEquals(true, cell.isContainsShip());

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

        JsonValue jsonCell1 = game.get("Boards").get(0).get("cells").get((y1*10 + x1));
        JsonValue jsonCell2 = game.get("Boards").get(1).get("cells").get((y2*10) + x2);

        assertEquals("Cell has same isHit value as the JSON given", cell1.isHit(), jsonCell1.getBoolean("hasBeenHit"));
        assertEquals("Cell has same isContainsShip value as the JSON given", cell1.isContainsShip(), jsonCell1.get("containsShip").asBoolean());

        assertEquals("Cell has same isHit value as the JSON given", cell2.isHit(), jsonCell2.getBoolean("hasBeenHit"));
        assertEquals("Cell has same isContainsShip value as the JSON given", cell2.isContainsShip(), jsonCell2.get("containsShip").asBoolean());

    }

}
