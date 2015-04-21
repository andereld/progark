package com.mygdx.seabattle.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.seabattle.AndroidNotification;
import com.mygdx.seabattle.SeaBattle;

public class DesktopLauncher implements AndroidNotification {
	private static DesktopLauncher app;
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = SeaBattle.VIRTUAL_WIDTH;
		config.height = SeaBattle.VIRTUAL_HEIGHT;
		if(app == null) {
			app = new DesktopLauncher();
		}
		new LwjglApplication(new SeaBattle(app), config);
	}

	@Override
	public void invokeService(String username) {

	}

	@Override
	public void purgeService() {

	}
}
