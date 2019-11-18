package edu.rice.comp504.payload.request;


/**
 * Message in the websocket.
 */
public class Message {
    private String type;
    private String info;

    /**
     * constructor.
     * @param type msg type.
     * @param info the actual information in this msg.
     */
    public Message(String type, String info) {
        this.type = type;
        this.info = info;
    }

    /**
     * getter.
     * @return type.
     */
    public String getType() {
        return type;
    }

    /**
     * getter.
     * @return info
     */
    public String getInfo() {
        return info;
    }

}
