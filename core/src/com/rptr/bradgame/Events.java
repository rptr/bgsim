package com.rptr.bradgame;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Events
{
    ArrayList<Event> events;

    Events ()
    {
        events = new ArrayList<>();
    }

    void parse (JSONArray array)
    {
        for (int i = 0; i < array.length(); i ++)
        {
            JSONObject evt = (JSONObject)array.get(i);
            this.events.add(new Event(evt));
        }
    }
}
