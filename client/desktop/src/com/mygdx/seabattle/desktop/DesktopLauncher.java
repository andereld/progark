package com.mygdx.seabattle.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.seabattle.SeaBattle;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = SeaBattle.VIRTUAL_WIDTH;
		config.height = SeaBattle.VIRTUAL_HEIGHT;
		new LwjglApplication(new SeaBattle(), config);
	}
}
