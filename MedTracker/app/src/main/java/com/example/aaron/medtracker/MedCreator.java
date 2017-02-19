package com.example.aaron.medtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * Created by Laura on 2/19/17.
 */

public class MedCreator extends AppCompatActivity {
    EditText nameInputTxt;

    //private TextView swStat;
    private Switch inputDaily;
    private Switch inputHourly;
    private TimePicker dtime;
    private NumberPicker htime;
    private String addName;
    private boolean isDaily;


    protected void create(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        nameInputTxt = (EditText) findViewById(R.id.med_name);

        final MedObject med = new MedObject();

        final Button addButton = (Button) findViewById(R.id.button1);

        nameInputTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                addButton.setEnabled(!charSequence.equals(""));
            }

            @Override
            public void afterTextChanged(Editable editable) {
                    addName = nameInputTxt.toString();
                    med.setName(addName);
            }
        });

        //swStat = (TextView) findViewById(R.id.swStat);
        inputDaily = (Switch) findViewById(R.id.daily);
        inputHourly = (Switch) findViewById(R.id.hourly);
        //set the swtich on
        inputDaily.setChecked(true);
        inputHourly.setChecked(false);
        //attach a listener to check for changes in the state
        inputDaily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                       // swStat.setText("Switch is currently ON");
                        dtime = (TimePicker) findViewById(R.id.dailyTimePicker);
                        med.setHour((int)dtime.getCurrentHour());
                        med.setMinute((int)dtime.getCurrentMinute());

                        //boolean to check if it is hourly or daily
                        //if daily ==true
                        isDaily = true;
                        med.setMedHourOrDay(isDaily);
                        //if hourly ==false

                        //then create a new medicine
                    }
                    else{
                        inputHourly.setChecked(true);
                        //swStat.setText("Switch is currently OFF");
                    }
            }
        });

        inputHourly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    htime = (NumberPicker) findViewById(R.id.hourlyTimePicker);
                    med.setHour((int)htime.getValue());
                    med.setMinute(0);
                    //if hourly ==false
                    isDaily = false;
                    med.setMedHourOrDay(isDaily);
                }
                else{
                    inputHourly.setChecked(false);
                }

            }
        });

        //check the current state before we display the screen

    }
}
