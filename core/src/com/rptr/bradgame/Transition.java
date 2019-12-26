package com.rptr.bradgame;

import org.json.JSONObject;

import java.util.ArrayList;

public class Transition
{
    String from, to;
    ArrayList<String> conditions;

    Transition (JSONObject obj)
    {
        from = obj.getString("from");
        to = obj.getString("to");
    }
}
