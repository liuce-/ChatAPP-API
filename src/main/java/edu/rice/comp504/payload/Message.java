package edu.rice.comp504.payload;

import com.google.gson.Gson;

public class Message {
    private String type;
    private String info;

    public Message(String type, String info) {
        this.type = type;
        this.info = info;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }



}
