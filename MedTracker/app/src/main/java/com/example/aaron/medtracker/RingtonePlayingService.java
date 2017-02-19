package com.example.aaron.medtracker;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.security.Provider;

/**
 * Created by Aaron on 2/19/17.
 */

public class RingtonePlayingService extends Service {
    MediaPlayer media_song;
    Context context;
    PendingIntent pending_intent;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


        /**
         * Class for clients to access.  Because we know this service always
         * runs in the same process as its clients, we don't need to deal with
         * IPC.
         */
        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Log.i("LocalService", "Received start id " + startId + ": " + intent);
            media_song = MediaPlayer.create(this, R.raw.scifi);
            media_song.start();
            final Intent my_intent =new Intent(this.context,MainActivity.class);
            pending_intent = PendingIntent.getBroadcast(RingtonePlayingService.this, 0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

            return START_NOT_STICKY;
        }

        @Override
        public void onDestroy() {

            // Tell the user we stopped.
            Toast.makeText(this, "onDestroy called ", Toast.LENGTH_SHORT).show();
        }

}

