package com.example.aaron.medtracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Aaron on 2/19/17.
 */

public class RestartReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service_intent = new Intent(context,RingtonePlayingService.class);
        context.startService(service_intent);
    }
}
