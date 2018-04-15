package com.example.michael.musicapp;

import android.content.Intent;
import android.location.Address;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.michael.musicapp.AccountActivity.LoginActivity;
import com.example.michael.musicapp.AccountActivity.StartUpActivity;
import com.example.michael.musicapp.SettingsActivity;
import com.example.michael.musicapp.AccountActivity.SignupActivity;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private LinearLayout btnSettings;
    private LinearLayout btnSearch;

    private LinearLayout btnMini_Event;
    private ArrayList<TextView> event_address = new ArrayList<TextView>();
    private ArrayList<TextView> event_name = new ArrayList<TextView>();
    private ArrayList<TextView> event_genre = new ArrayList<TextView>();
    private ArrayList<TextView> event_date = new ArrayList<TextView>();
    private ArrayList<TextView> event_time = new ArrayList<TextView>();
    public ArrayList<String> eventkey = new ArrayList<String>();
    private int count = 0;
    private View mini_event;
    private View b;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            // user auth state is changed - user is null
            // launch login activity
            startActivity(new Intent(MainActivity.this, StartUpActivity.class));
            finish();
        }

        ArrayList <View> event = new ArrayList<View>();

        for (i = 0; i < 10; i++) {


            mini_event = getLayoutInflater().inflate(R.layout.mini_event, null);
            LinearLayout f = (LinearLayout) findViewById(R.id.main);
            f.addView(mini_event);

            //btnMini_Event = (LinearLayout) findViewById(R.id.mini_event_1);
            //TextView rr = (TextView)v.findViewById(R.id.Event_Name);
            //rr.setText("jhdsfgysdfg");

            event_address.add((TextView) mini_event.findViewById(R.id.Event_Address));
            event_time.add((TextView) mini_event.findViewById(R.id.Event_Time));
            event_name.add((TextView) mini_event.findViewById(R.id.Event_Name));
            event_genre.add((TextView) mini_event.findViewById(R.id.Event_Genre));
            event_date.add((TextView) mini_event.findViewById(R.id.Event_Date));

            LinearLayout eventb = (LinearLayout) mini_event.findViewById(R.id.mini_event);
            eventb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, EventDetailActivity.class));
                }
            });


        }

        Firebase finding_events = new Firebase("https://musicapp-f8eb2.firebaseio.com/Events");
        finding_events.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });




        btnSettings = (LinearLayout) findViewById(R.id.setting_button);
        //If this button is clicked then it activates the sign up class
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));

            }
        });

        btnSearch = (LinearLayout) findViewById(R.id.search_button);
        //If this button is clicked then it activates the sign up class
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));

            }
        });



    }



}
