package com.mygdx.seabattle.android;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.seabattle.AndroidNotification;
import com.mygdx.seabattle.SeaBattle;

public class AndroidLauncher extends AndroidApplication implements AndroidNotification {
	private Intent intent;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new SeaBattle(this), config);
	}

	@Override
	public void invokeService(String username) {
		intent = new Intent(this, SeaBNotificationService.class);
		intent.putExtra("username", username);
		startService(intent);

	}

	@Override
	public void purgeService() {
		stopService(intent);
	}
}
