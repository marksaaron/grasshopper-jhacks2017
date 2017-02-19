package com.example.aaron.medtracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Aaron on 2/19/17.
 */

public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("We are in the reciever.","Yay!");
        Intent service_intent = new Intent(context,RingtonePlayingService.class);
        context.startService(service_intent);
    }
}
