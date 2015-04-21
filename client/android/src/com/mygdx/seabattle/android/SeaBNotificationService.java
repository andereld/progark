package com.mygdx.seabattle.android;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.seabattle.Constants;
import com.mygdx.seabattle.json.JsonHelper;
import com.mygdx.seabattle.network.HttpResponseListener;
import com.mygdx.seabattle.network.NetworkHelper;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by andybb on 20.04.15.
 */
public class SeaBNotificationService extends Service {

    private Timer timer;
    private Boolean turn = true;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand (Intent intent, int flags, int startId) {
        String username = intent.getStringExtra("username");
        timer = new Timer();
        waitForTurn(username);
        return START_STICKY;
    }

    public void myTurn() {
        if(turn) {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setContentTitle("It's your turn!")
                            .setContentText("Fire now!")
                            .setAutoCancel(true)
                            .setWhen(new Date().getTime());
            Intent resultIntent = new Intent(this, AndroidLauncher.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addParentStack(AndroidLauncher.class);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent =
                    stackBuilder.getPendingIntent(
                            0,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int mId = 1;
            mNotificationManager.notify(mId, mBuilder.build());
            mId++;
            turn = false;
        }
    }
    public void waitForTurn(final String username) {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                NetworkHelper.sendGetRequest("/turn/" + username, new HttpResponseListener() {
                    @Override
                    public void handleHttpResponse(Net.HttpResponse httpResponse) {
                        JsonValue jsonResponse = JsonHelper.parseJson(httpResponse.getResultAsString());

                        if (jsonResponse.get("username").asString().equals(username)) {
                            if(turn) {
                                myTurn();
                            }

                        }
                        else {
                            turn = true;
                        }
                    }
                });
            }
        }, 0, 15000);
    }
}
