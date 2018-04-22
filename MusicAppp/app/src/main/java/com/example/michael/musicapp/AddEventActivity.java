package com.example.michael.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.support.v7.app.AlertDialog;
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
    EditText add_event_genre;
    EditText add_description;
    Button add_button;
    private View view;
    DatabaseReference databaseEvents;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevent);

    }

    private void addEvent(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddEventActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        view = inflater.inflate(R.layout.activity_addevent, null);

        databaseEvents = FirebaseDatabase.getInstance().getReference("Events");

        add_event_name = (EditText) view.findViewById(R.id.add_event_name);
        add_event_address = (EditText) view.findViewById(R.id.add_event_address);
        add_event_time = (EditText) view.findViewById(R.id.add_event_time);
        add_event_date = (EditText) view.findViewById(R.id.add_event_date);
        add_event_genre = (EditText) view.findViewById(R.id.add_event_genre);
        add_description = (EditText) view.findViewById(R.id.description) ;
        add_button = (Button) findViewById(R.id.addevent);
        Intent intent = new Intent(AddEventActivity.this, EventDetailActivity.class);
        intent.putExtra("GetName", add_event_name.getText().toString());
        intent.putExtra("GetDate", add_event_date.getText().toString());
        intent.putExtra("GetTime", add_event_time.getText().toString());
        intent.putExtra("GetDescription", add_description.getText().toString());
        intent.putExtra("GetGenre", add_event_genre.getText().toString());


        String name = add_event_name.getText().toString().trim();
        String address = add_event_address.getText().toString().trim();
        String time = add_event_time.getText().toString().trim();
        String date = add_event_date.getText().toString().trim();
        String genre = add_event_genre.getText().toString().trim();
        String description = add_description.getText().toString().trim();
        String id = databaseEvents.push().getKey();
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEvent();
            }
        });
        Event event = new Event (id, name, address, time, date, genre, description);

        databaseEvents.child(id).setValue(event);

        Toast.makeText(this, "Event is Added", Toast.LENGTH_SHORT).show();
    }
}
