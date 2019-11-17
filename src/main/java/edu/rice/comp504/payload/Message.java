package edu.rice.comp504.payload;

public class Message {
    private String type;
    private Object info;

    public Message(String type, Object info) {
        this.type = type;
        this.info = info;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
