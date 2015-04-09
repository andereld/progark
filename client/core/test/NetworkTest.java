/**
 * Created by andybb on 18.03.15.
 */


import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import com.mygdx.game.controllers.GameNetworkController;

import java.util.concurrent.TimeUnit;


public class NetworkTest {

    private GameNetworkController game1;
    private GameNetworkController game2;

    @Before
    public void setup() {
        game1 = new GameNetworkController();
        game2 = new GameNetworkController();
    }

    @Test
    public void testGameInitialization() throws InterruptedException {

        game1.startGame("andybb");
        assertEquals("Username for player 1 given and in model should be equal ", "andybb", game1.getPlayerController().getPlayer().getUsername());

        TimeUnit.SECONDS.sleep(1);
        game2.startGame("mats");
        assertEquals("Username for player 2 given and in model should be equal ", "mats", game2.getPlayerController().getPlayer().getUsername());

        TimeUnit.SECONDS.sleep(15);
        assertEquals("The opponent of player 1 should player 2", "mats", game1.getPlayerController().getOpponent().getUsername());
        assertEquals("The opponent of player 2 should player 1", "andybb", game2.getPlayerController().getOpponent().getUsername());


    }


    @Test
    public void testFireOperation(){

    }
}
