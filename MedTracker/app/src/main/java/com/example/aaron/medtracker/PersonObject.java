package com.example.aaron.medtracker;

import java.util.ArrayList;

/**
 * Created by Laura on 2/19/17.
 */

public class PersonObject {
    ArrayList<MedObject> MedList = new ArrayList<MedObject>();

    public PersonObject(){

    }

    public ArrayList getPersonList(){
        return MedList;
    }
}
