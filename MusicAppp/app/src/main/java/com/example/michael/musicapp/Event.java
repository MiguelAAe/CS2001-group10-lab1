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
    String eventDescription;

    public Event(){

    }
    public void init(){

    }

    public Event(String eventID, String eventName, String eventAddress, String eventGenre, String eventDate, String eventTime, String eventDescription) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventAddress = eventAddress;
        this.eventGenre = eventGenre;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventDescription = eventDescription;

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

    public String getEventDescription(){return eventDescription;}
}
