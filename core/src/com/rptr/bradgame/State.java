package com.rptr.bradgame;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class State
{
    private String name;
    private ArrayList<Event> events;

    State (JSONObject obj)
    {
        events = new ArrayList<>();

        if (obj.has("events"))
        {
            JSONArray events = obj.getJSONArray("events");
            for (int i = 0; i < events.length(); i ++)
            {
                JSONObject evt = (JSONObject)events.get(i);
                this.events.add(new Event(evt));
            }
        }

        name = obj.getString("name");
    }

    String getName ()
    {
        return name;
    }

    ArrayList<Event> getEvents ()
    {
        return events;
    }
}
