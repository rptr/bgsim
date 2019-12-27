package com.rptr.bradgame;

import net.dermetfan.utils.Pair;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

abstract class Event
{
    abstract void apply (Session session, Player triggerPlayer);
}
