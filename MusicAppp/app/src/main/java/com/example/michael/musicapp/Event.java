package com.example.michael.musicapp;

/**
 * Created by Hanif on 10/04/2018.
 */

public class Event {

    String eventID;
    String eventName;
    String eventAddress;
    String eventGenre;
    String eventDate;
    String eventTime;

    public Event(){

    }

    public Event(String eventID, String eventName, String eventAddress, String eventGenre, String eventDate, String eventTime) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventAddress = eventAddress;
        this.eventGenre = eventGenre;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
    }

    public String getEventID() {
        return eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public String getEventGenre() {
        return eventGenre;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }
}
