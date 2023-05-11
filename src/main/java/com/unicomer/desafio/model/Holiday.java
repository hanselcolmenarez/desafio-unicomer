package com.unicomer.desafio.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Holiday {

    @JsonProperty("date")
    private String date;
    @JsonProperty("title")
    private String title;
    @JsonProperty("type")
    private String type;
    @JsonProperty("inalienable")
    private boolean inalienable;
    @JsonProperty("extra")
    private String extra;

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public boolean isInalienable() {
        return inalienable;
    }
    public void setInalienable(boolean inalienable) {
        this.inalienable = inalienable;
    }
    public String getExtra() {
        return extra;
    }
    public void setExtra(String extra) {
        this.extra = extra;
    }
    @Override
    public String toString() {
        return "Holiday [date=" + date + ", title=" + title + ", type=" + type + ", inalienable=" + inalienable
                + ", extra=" + extra + "]";
    }

    
    
    
}
