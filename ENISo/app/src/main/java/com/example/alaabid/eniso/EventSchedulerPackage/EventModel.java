package com.example.alaabid.eniso.EventSchedulerPackage;

/**
 * Created by Ala Abid on 23/03/2018.
 */

public class EventModel {
    private int id;
    private String title, hour, date, descr, author;

    public EventModel(int id, String title, String hour, String date, String descr, String author) {
        this.id = id;
        this.title = title;
        this.hour = hour;
        this.date = date;
        this.descr = descr;
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;

    }

    public String getTitle() {
        return title;
    }

    public String getHour() {
        return hour;
    }

    public String getDate() {
        return date;
    }

    public String getDescr() {
        return descr;
    }

    public String getAuthor() {
        return author;
    }
}
