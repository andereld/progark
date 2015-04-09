/**
 * Created by andybb on 18.03.15.
 */


import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import com.mygdx.game.controllers.GameNetworkController;
import com.mygdx.game.controllers.PlayerNetworkController;

import java.util.concurrent.TimeUnit;


public class NetworkTest {

    private GameNetworkController game1;
    private GameNetworkController game2;
    private String player1;
    private String player2;

    @Before
    public void setup() {
        game1 = new GameNetworkController();
        game2 = new GameNetworkController();
        player1 = "andybb" + (int)(Math.random() * 10000);
        player2 = "mats" + (int)(Math.random() * 10000);
    }

    @Test
    public void testGameInitialization() throws InterruptedException {

        game1.startGame(player1);
        assertEquals("Username for player 1 given and in model should be equal ", player1, game1.getPlayerController().getPlayer().getUsername());

        TimeUnit.SECONDS.sleep(1);
        game2.startGame(player2);
        assertEquals("Username for player 2 given and in model should be equal ", player2, game2.getPlayerController().getPlayer().getUsername());

        TimeUnit.SECONDS.sleep(15);
        assertEquals("The opponent of player 1 should player 2", player2, game1.getPlayerController().getOpponent().getUsername());
        assertEquals("The opponent of player 2 should player 1", player1, game2.getPlayerController().getOpponent().getUsername());

    }


    @Test
    public void testFireOperation(){
        int x = 2;
        int y = 4;
        PlayerNetworkController playerNetworkController = game1.getPlayerController();
        playerNetworkController.fireAtLocation(x, y);
        assertEquals(true, playerNetworkController.getPlayer().getBoard().getCell(x, y).isHit());
    }
}
