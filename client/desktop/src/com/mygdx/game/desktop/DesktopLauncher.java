package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Battleship;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Battleship.VIRTUAL_WIDTH;
		config.height = Battleship.VIRTUAL_HEIGHT;
		new LwjglApplication(new Battleship(), config);
	}
}
