package edu.rice.comp504.payload.response;

import com.google.gson.Gson;

public interface ResponseAdapter {
    public String getJsonRepresentation(Gson gson);
}
