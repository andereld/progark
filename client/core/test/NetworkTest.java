/**
 * Created by andybb on 18.03.15.
 */


import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

import com.mygdx.game.controllers.GameNetworkController;
import com.mygdx.game.controllers.PlayerNetworkController;

import java.util.concurrent.TimeUnit;


public class NetworkTest

{
    @Test
    public void testGameInitialization() throws InterruptedException {
        GameNetworkController game1 = new GameNetworkController();
        GameNetworkController game2 = new GameNetworkController();

        PlayerNetworkController player1 = game1.getPlayerController();
        PlayerNetworkController player2 = game2.getPlayerController();

        game1.startGame("andybb");

        assertEquals("andybb", player1.getPlayer().getUsername());

        TimeUnit.SECONDS.sleep(1);
        game2.startGame("mats");
        assertEquals("mats", player2.getPlayer().getUsername());




    }
}
