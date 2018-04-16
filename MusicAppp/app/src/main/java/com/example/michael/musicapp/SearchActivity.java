package com.example.michael.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hanif on 10/04/2018.
 */



public class SearchActivity extends AppCompatActivity {

    private LinearLayout btnHome, btnSettings, btnChat, btnStar;
    private EditText searchBox;
    private ImageButton searchButton;
    private Spinner searchGenre;
    private String searchString, genresearchString;
    View Heading;
    DatabaseReference databaseEvents;

    ListView v;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        databaseEvents = FirebaseDatabase.getInstance().getReference("Events");
        v = (ListView) findViewById(R.id.listViewEvents);



        btnSettings = (LinearLayout) findViewById(R.id.setting_button);
        //If this button is clicked then it activates the sign up class
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, SettingsActivity.class));

            }
        });
        btnStar = (LinearLayout) findViewById(R.id.star_button);
        //If this button is clicked then it activates the sign up class
        btnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, StarredActivity.class));

            }
        });
        btnHome = (LinearLayout) findViewById(R.id.home_button);
        //If this button is clicked then it activates the sign up class
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, MainActivity.class));

            }
        });
        btnChat= (LinearLayout) findViewById(R.id.chat_button);
        //If this button is clicked then it activates the sign up class
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, ChatActivity.class));
            }
        });


        searchButton = (ImageButton) findViewById(R.id.search_button2);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View x) {
                databaseEvents.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        searchBox = (EditText) findViewById(R.id.search_box);
                        searchGenre = (Spinner) findViewById(R.id.search_by_genre);
                        TextView results = (TextView) findViewById(R.id.result);
                        List<Event> eventList= new ArrayList<>();;


                        for(DataSnapshot eventSnapshot: dataSnapshot.getChildren()){
                            Event event = eventSnapshot.getValue(Event.class);

                            String name = eventSnapshot.child("eventName").getValue(String.class);
                            String genre = eventSnapshot.child("eventGenre").getValue(String.class);
                            searchString = searchBox.getText().toString();
                            genresearchString = searchGenre.getSelectedItem().toString();

                            if (name.contains(searchString) && genre.contentEquals(genresearchString)){
                                eventList.add(event);
                                results.setText("RESULTS");
                                results.setVisibility(View.VISIBLE);

                            }
                            else if (name.contains(searchString) && genresearchString.contentEquals("Select Genre")){
                                eventList.add(event);
                                results.setText("RESULTS");
                                results.setVisibility(View.VISIBLE);
                            }
                            else {
                                Toast.makeText(SearchActivity.this, "No Result Found", Toast.LENGTH_SHORT).show();
                                results.setVisibility(View.VISIBLE);
                                results.setText("No Results Found");

                            }

                        }

                        EventList adapter = new EventList(SearchActivity.this, eventList);
                        v.setAdapter(adapter);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();



    }


}
