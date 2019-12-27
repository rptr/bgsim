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

            String type = evt.getString("type");
            Reward newEvent = null;

            if (type.equals("reward"))
            {

                if (evt.has("category")) {
                    newEvent = new CategoryReward(
                            evt.getString("category"),
                            evt.getInt("amount"));

                } else if (evt.has("income")) {
                    newEvent = new IncomeReward(
                            evt.getString("piece"),
                            evt.getString("income"));

                } else if (evt.has("piece") && evt.has("amount")) {
                    newEvent = new PieceReward(
                            evt.getString("piece"),
                            evt.getInt("amount"));
                }
            }

            this.events.add(newEvent);
        }
    }
}
