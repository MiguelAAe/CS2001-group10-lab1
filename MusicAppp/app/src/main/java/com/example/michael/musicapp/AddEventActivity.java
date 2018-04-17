package com.example.michael.musicapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hanif on 10/04/2018.
 */

public class AddEventActivity extends AppCompatActivity{

    EditText add_event_name;
    EditText add_event_address;
    EditText add_event_time;
    EditText add_event_date;
    EditText add_event_about;
    Spinner add_event_genre;
    Spinner add_event_day;
    Button add_button;

    DatabaseReference databaseEvents;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevent);
        //connect to firebase database /events
        databaseEvents = FirebaseDatabase.getInstance().getReference("Events");

        add_event_name = (EditText) findViewById(R.id.add_event_name);
        add_event_address = (EditText) findViewById(R.id.add_event_address);
        add_event_time = (EditText) findViewById(R.id.add_event_time);
        add_event_date = (EditText) findViewById(R.id.add_event_date);
        add_event_about = (EditText) findViewById(R.id.add_about_event);
        add_event_genre = (Spinner) findViewById(R.id.add_event_genre);
        add_event_day = (Spinner) findViewById(R.id.add_event_day);
        add_button = (Button) findViewById(R.id.addevent);

        //when clicked runs addEvent function
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEvent();
            }
        });
    }

    private void addEvent(){
        //converts inputed event data into strings
        String name = add_event_name.getText().toString().trim();
        String address = add_event_address.getText().toString().trim();
        String time = add_event_time.getText().toString().trim();
        String date = add_event_date.getText().toString().trim();
        String about = add_event_about.getText().toString().trim();
        String genre = add_event_genre.getSelectedItem().toString();
        String day = add_event_day.getSelectedItem().toString();

        //if just one textboxes isnt filled, then this if statment runs.
        if (TextUtils.isEmpty(day) || TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(time) || TextUtils.isEmpty(date) || TextUtils.isEmpty(about) || TextUtils.isEmpty(genre)) {
            Toast.makeText(this, "Make Sure All is filled", Toast.LENGTH_SHORT).show();

        }
        //else it will add the data to the database, using the event constructor class
        else{
            String id = databaseEvents.push().getKey();
            Event event = new Event(id, name, address, time, date, about, genre, day);

            databaseEvents.child(id).setValue(event);

            Toast.makeText(this, "Event is Added", Toast.LENGTH_SHORT).show();
        }
    }
}
