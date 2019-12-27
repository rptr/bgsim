package com.rptr.bradgame;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

class State implements Eventful
{
    private String name;
    private Events events;

    State (JSONObject obj)
    {
        events = new Events();

        if (obj.has("events"))
        {
            events.parse(obj.getJSONArray("events"));
        }

        name = obj.getString("name");
    }

    public void performEvents (Session session, Player player)
    {
        for (Event e : events.events)
        {
            e.apply(session, player);
        }
    }

    String getName ()
    {
        return name;
    }

    ArrayList<Event> getEvents ()
    {
        return events.events;
    }
}
