package com.example.michael.musicapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import com.example.michael.musicapp.AccountActivity.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hanif on 05/04/2018.
 */

public class EventDetailActivity extends Activity{
    DatabaseReference databaseEvents;
    EditText add_event_detail_headings;
    EditText add_event_detail_time;
    EditText add_description;
    EditText add_Genre;
    EditText add_event_detail_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        add_event_detail_headings = (EditText) findViewById(R.id.event_detail_heading);
        add_event_detail_date = (EditText) findViewById(R.id.event_detail_date);
        add_event_detail_time = (EditText) findViewById(R.id.event_detail_time);
        add_description =  (EditText) findViewById(R.id.description);
        add_Genre = (EditText) findViewById(R.id.Genre);
        databaseEvents = FirebaseDatabase.getInstance().getReference("Events");
    }
        }



