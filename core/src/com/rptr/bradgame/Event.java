package com.rptr.bradgame;

import net.dermetfan.utils.Pair;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

class Event
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
                Reward newReward = null;

                if (reward.has("amount"))
                {
                    newReward = new CategoryReward(
                            reward.getString("category"),
                            reward.getInt("amount"));

                } else if (reward.has("income"))
                {
                    newReward = new IncomeReward(
                            reward.getString("piece"),
                            reward.getString("income"));
                }

                if (newReward != null)
                    this.rewards.add(newReward);
            }
        }
    }

    void applyRewards (Session session)
    {
        for (Reward reward : rewards)
        {
            reward.give(session);
        }
    }
}
