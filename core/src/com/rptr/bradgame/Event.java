package com.rptr.bradgame;

import net.dermetfan.utils.Pair;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Event
{
    String type;
    ArrayList<Reward> rewards;

    Event (JSONObject obj)
    {
        rewards = new ArrayList<>();

        if (obj.has("reward"))
        {
            type = "reward";

            JSONArray rewards = obj.getJSONArray("reward");

            for (int i = 0; i < rewards.length(); i ++)
            {
                JSONObject reward = (JSONObject)rewards.get(i);

                this.rewards.add(new Reward(
                        reward.getString("piece"),
                        reward.getInt("amount")));
            }
        }
    }

    void applyRewards (Session session)
    {
        for (Reward reward : rewards)
        {
            session.givePiece(reward.piece, reward.amount);
        }
    }
}
