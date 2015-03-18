/**
 * Created by andybb on 18.03.15.
 */


import org.junit.Test;
import static org.junit.Assert.*;

import com.mygdx.game.controllers.GameNetworkController;
import com.mygdx.game.controllers.PlayerNetworkController;

import java.util.concurrent.TimeUnit;


public class NetworkTest {
    @Test
    public void testGameInitialization() throws InterruptedException {
        GameNetworkController game1 = new GameNetworkController();
        GameNetworkController game2 = new GameNetworkController();

        game1.startGame("andybb");
        assertEquals("Username for player 1 given and in model should be equal ", "andybb", game1.getPlayerController().getPlayer().getUsername());

        TimeUnit.SECONDS.sleep(1);
        game2.startGame("mats");
        assertEquals("Username for player 2 given and in model should be equal ", "mats", game2.getPlayerController().getPlayer().getUsername());

    }

    @Test
    public void testFireOperation() throws InterruptedException{

    }
}
