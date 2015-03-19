

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.mygdx.game.models.Board;
import json.JsonHelper;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Created by andybb on 19.03.15.
 */
public class ModelTest {

    private Board board;
    private String gameString = "{\"game\":{\"player1\":\"an3d4343as\",\"player2\":\"an3d4343asd33yddb3b\",\"next\":\"an3d4343asd33yddb3b\",\"board1\":{\"board\":[{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false}]},\"board2\":{\"board\":[{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":true,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false},{\"isShip\":false,\"isHit\":false}]}}}";
    private JsonValue game;


    @Before
    public void setup() {
        this.board = new Board();
        this.game = JsonHelper.parseJson(gameString);

    }

    @Test
    public void testBoardCreation() {
        JsonValue.PrettyPrintSettings settings = new JsonValue.PrettyPrintSettings();
        settings.outputType = JsonWriter.OutputType.json;
        board.createFromJson(game.get("game").get("board1").prettyPrint(settings));


    }

}
