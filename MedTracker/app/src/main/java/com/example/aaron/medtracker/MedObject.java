package com.example.aaron.medtracker;

/**
 * Created by Laura on 2/19/17.
 */

public class MedObject {

        private int hour;
        private int minute;
        private String name;
        private boolean medHourOrDay;
        private String rxnormID;

        public MedObject(){

        }

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }

        public int getMinute(){
            return minute;
        }

        public void setMinute(int minute){
            this.minute = minute;
        }

        public int getHour(){
            return hour;
        }

        public void setHour(int hour){
            this.hour = hour;
        }

        public boolean getMedHourOrDay(){
            return medHourOrDay;
        }

        public void setMedHourOrDay(boolean medHourOrDay){
            this.medHourOrDay = medHourOrDay;
        }

        public String getRxnormID(){
            return rxnormID;
        }

        public void setRxnormID(){
            this.rxnormID = "";//getId(name);
        }

}
