package com.example.michael.musicapp;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hanif on 10/04/2018.
 */

public class EventList extends ArrayAdapter<Event> {
    private Activity context;
    private List<Event> eventlist;

    public EventList(Activity context, List<Event> eventlist){
        super(context, R.layout.activity_search, eventlist);
        this.context =  context;
        this.eventlist = eventlist;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        //adding an event link
        View v = inflater.inflate(R.layout.mini_event, null, true);

        TextView event_address = (TextView) v.findViewById(R.id.Event_Address);
        TextView event_time = (TextView) v.findViewById(R.id.Event_Time);
        TextView event_name = (TextView) v.findViewById(R.id.Event_Name);
        TextView event_genre = (TextView) v.findViewById(R.id.Event_Genre);
        TextView event_date = (TextView) v.findViewById(R.id.Event_Date);
        TextView event_day = (TextView) v.findViewById(R.id.Event_Day);

        //gains a certain data from an event, determine by "position"
        Event event = eventlist.get(position);

        //inputs event data stored in "event" into textboxs
        event_address.setText(event.getEventAddress());
        event_date.setText(event.getEventDate());
        event_genre.setText(event.getEventGenre());
        event_name.setText(event.getEventName());
        event_time.setText(event.getEventTime());
        event_day.setText(event.getEventDay());

        return v;
    }}
