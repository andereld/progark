

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
    private String gameString = "{\"game\":{\"player1\":\"an3d4343as\",\"player2\":\"an3d4343asd33yddb3b\",\"next\":\"an3d4343asd33yddb3b\",\"board1\":{\"board\":[{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false}]},\"board2\":{\"board\":[{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false}]}}}";
    private JsonValue game;



    @Before
    public void setup() {
        this.board1 = new Board();
        this.board2 = new Board();
        this.game = JsonHelper.parseJson(gameString).get("game");

    }
    
    @Test
    public void testBoardCreation() {
        board1.createFromJson(JsonHelper.prettyPrint(game.get("board1")));
        board2.createFromJson(JsonHelper.prettyPrint(game.get("board2")));
    }

    @Test
    public void testPlayerCreation(){
        player1 = new Player(game.get("player1").toString(),board1 );
        player2 = new Player(game.get("player2").toString(),board2 );

        assertEquals("Given username is the same as in the model for player 1", player1.getUsername(), game.get("player1").toString());
        assertEquals("Given username is the same as in the model for player 2", player2.getUsername(), game.get("player2").toString());

        assertEquals("Board in model the same as the one given for player 1", board1, player1.getBoard());
        assertEquals("Board in model the same as the one given for player 2", board2, player2.getBoard());
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

        // System.out.println(JsonHelper.prettyPrint(game));

        JsonValue jsonCell1 = game.get("board1").get("board").get((x1*10)+y1-1);
        JsonValue jsonCell2 = game.get("board2").get("board").get((x2*10)+y2-1);


        assertEquals("Cell has same isHit value as the JSON given", cell1.isHit(), jsonCell1.get("isHit").asBoolean() );
        assertEquals("Cell has same isShip value as the JSON given", cell1.isShip(), jsonCell1.get("isShio").asBoolean());

        assertEquals("Cell has same isHit value as the JSON given", cell2.isHit(), jsonCell2.get("isHit").asBoolean() );
        assertEquals("Cell has same isShip value as the JSON given", cell2.isShip(), jsonCell2.get("isShio").asBoolean());

    }

}
