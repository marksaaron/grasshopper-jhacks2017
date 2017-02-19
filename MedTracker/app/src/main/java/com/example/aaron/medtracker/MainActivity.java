package com.example.aaron.medtracker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarm_manager;
    TimePicker pickTime;
    TextView update_text;
    Context context;
    PendingIntent pending_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.context =this;
        alarm_manager =(AlarmManager)getSystemService(ALARM_SERVICE);
        update_text=(TextView)findViewById(R.id.updateAlarm);
        final Calendar calendar = Calendar.getInstance();
        final Intent my_intent =new Intent(this.context,AlarmReceiver.class);
        Button StartAlarm = (Button)findViewById(R.id.alarm_on);
        StartAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int tillNext = 0;
                int tillNext;
                Calendar currentTime = Calendar.getInstance();
                EditText num = (EditText) findViewById(R.id.inputnumber);
                Log.e("l", "hey");
                if (!num.getText().toString().equals("")) {
                    tillNext = currentTime.get(Calendar.HOUR_OF_DAY) + Integer.parseInt(num.getText().toString());
                } else {
                    tillNext = currentTime.get(Calendar.HOUR_OF_DAY);
                }
                //int tillNext = (int)R.id.hourSchedule;

                calendar.set(Calendar.HOUR_OF_DAY, tillNext);
                calendar.set(Calendar.MINUTE, currentTime.get(Calendar.MINUTE)-1);
                int hourTime = calendar.get(Calendar.HOUR_OF_DAY);
                if(hourTime>12){
                    hourTime -=12;
                }
                EditText name = (EditText) findViewById(R.id.med_name);
                String medName = name.getText().toString();

                if(!name.getText().toString().equals("")) {
                    String alarmText = "Take " + medName + " at " + hourTime + ":" + calendar.get(Calendar.MINUTE);
                    set_alarm_text(alarmText);
                    pending_intent = PendingIntent.getBroadcast(MainActivity.this, 0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);
                }
            }
        });
        Button Restart = (Button)findViewById(R.id.restart);
        Restart.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           //int tillNext = 0;
                                           int tillNext;
                                           Calendar currentTime = Calendar.getInstance();
                                           EditText num = (EditText) findViewById(R.id.inputnumber);
                                           Log.e("l", "hey");
                                           if (!num.getText().toString().equals("")) {
                                               tillNext = currentTime.get(Calendar.HOUR_OF_DAY) + Integer.parseInt(num.getText().toString());
                                           } else {
                                               tillNext = currentTime.get(Calendar.HOUR_OF_DAY);
                                           }
                                           //int tillNext = (int)R.id.hourSchedule;

                                           calendar.set(Calendar.HOUR_OF_DAY, tillNext);
                                           calendar.set(Calendar.MINUTE, currentTime.get(Calendar.MINUTE)-1 );

                                           String alarmText = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
                                           set_alarm_text(alarmText);
                                           pending_intent = PendingIntent.getBroadcast(MainActivity.this, 0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);
                                           alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);
                                           int hourTime = calendar.get(Calendar.HOUR_OF_DAY);
                                           if(hourTime>12){
                                               hourTime -=12;
                                           }
                                           EditText name = (EditText) findViewById(R.id.med_name);
                                           String medName = name.getText().toString();

                                           if(!name.getText().toString().equals("")) {
                                               alarmText = "Take " + medName + " at " + hourTime + ":" + calendar.get(Calendar.MINUTE);
                                               set_alarm_text(alarmText);
                                               pending_intent = PendingIntent.getBroadcast(MainActivity.this, 0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);
                                               alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);
                                           }
                                       }
                                   });
        Button EndAlarm = (Button)findViewById(R.id.alarm_off);
        EndAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                set_alarm_text("Alarm off");
                alarm_manager.cancel(pending_intent);
            }
        });

    }

    private void set_alarm_text(String output) {
        update_text.setText(output);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
