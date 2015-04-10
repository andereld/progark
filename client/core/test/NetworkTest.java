/**
 * Created by andybb on 18.03.15.
 */


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.controllers.GameNetworkController;
import com.mygdx.game.controllers.PlayerNetworkController;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import json.JsonHelper;
import network.NetworkHelper;


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

    public void startGames() throws InterruptedException {
        game1.startGame(player1);
        TimeUnit.SECONDS.sleep(1);
        game2.startGame(player2);

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
    public void testNetworkedWaitForTurn() throws InterruptedException {
        startGames();

        NetworkHelper.sendGetRequest("/turn/"+ game1.getPlayerController().getPlayer().getUsername(), new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                JsonValue jsonResponse = JsonHelper.parseJson(httpResponse.getResultAsString());
                ArrayList<String> usernames = new ArrayList<String>();
                usernames.add(game1.getPlayerController().getPlayer().getUsername());
                usernames.add(game2.getPlayerController().getPlayer().getUsername());

                assertTrue("Username from server is one of the players", usernames.contains(jsonResponse.get("next")));
            }

            @Override
            public void failed(Throwable t) {

            }

            @Override
            public void cancelled() {

            }
        });
    }




}
