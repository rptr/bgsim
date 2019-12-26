package com.rptr.bradgame;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class PieceType
{
    private String id = "";
    private String category = "";
    private String name = "";
    private String file = "";
    private String type = "";
    private JSONObject settings;

    HashMap<String, Integer> costs;

    PieceType (JSONObject data)
    {
        costs = new HashMap<>();
        settings = data;

        for (String key : data.keySet())
        {
            switch (key)
            {
                case "id":
                    id = data.getString(key);
                    break;

                case "name":
                    name = data.getString(key);
                    break;

                case "category":
                    category = data.getString(key);
                    break;

                case "file":
                    file = data.getString(key);
                    Gfx.loadTexture(file);
                    break;

                case "type":
                    type = data.getString(key);
                    break;

                case "cost":
                    parseCosts(data.getJSONArray("cost"));
                    break;

                default:
                    System.out.format("Unsupported piece setting: %s\n", key);
                    break;
            }
        }
    }

    private void parseCosts (JSONArray costs)
    {
        for (int i = 0; i < costs.length(); i++)
        {
            JSONObject obj = (JSONObject)costs.get(i);

            this.costs.put(obj.getString("piece"),
                    obj.getInt("amount"));
        }
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getFile ()
    {
        return file;
    }

    public String getType ()
    {
        return type;
    }


}
