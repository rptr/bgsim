package com.rptr.bradgame;

import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.MathUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

class GameType
{
//    private int maxTurns,
//        minPlayers, maxPlayers;
//    private String name;
    private ArrayList<PieceType> pieceTypes;
    private JSONObject settings;
    private ArrayList<State> states;
    private ArrayList<Transition> transitions;
    private HashMap<String, ArrayList<PieceType>> categories;

    // highly TEMP
    private ArrayList<Widget> layout;

    GameType ()
    {
        states = new ArrayList<>();
        transitions = new ArrayList<>();
        pieceTypes = new ArrayList<>();
        categories = new HashMap<>();
        layout = new ArrayList<>();
    }

    void save ()
    {
        System.out.println("Setting up piece categories...");

        for (PieceType type : pieceTypes)
        {
            String category = type.getCategory();

            if (!category.isEmpty())
            {
                if (!categories.containsKey(category))
                    categories.put(category, new ArrayList<PieceType>());

                categories.get(category).add(type);
            }
        }
    }

    String getSettingsValue (String key)
    {
        return settings.getString(key);
    }

    void parseSettings (String rawData)
    {
        System.out.println(rawData);
        settings = new JSONObject(rawData);

        for (String key : settings.keySet())
        {
            switch (key)
            {
//                case "max_turns":
//                    maxTurns = settings.getInt(key);
//                    break;
//
//                case "max_players":
//                    maxPlayers = settings.getInt(key);
//                    break;
//
//                case "min_players":
//                    minPlayers = settings.getInt(key);
//                    break;
//
//                case "name":
//                    name = settings.getString(key);
//                    break;

                case "states":
                    parseStates(settings.getJSONArray("states"));
                    break;

                case "transitions":
                    parseTransitions(settings.getJSONArray("transitions"));
                    break;

                case "personalLayout":
                    parsePlayerLayout(settings.getJSONArray("personalLayout"));
                    break;

                default:
                    System.out.format("Unsupported setting: %s\n", key);
                    break;
            }
        }
    }

    private void parseStates (JSONArray states)
    {
        for (int i = 0; i < states.length(); i ++)
        {
            JSONObject obj = (JSONObject)states.get(i);
            this.states.add(new State(obj));
        }
    }

    State getState (String name)
    {
        for (State s : states)
        {
            if (s.getName().equals(name))
                return s;
        }

        return null;
    }

    State getStartingState ()
    {
        String start = settings.getString("startState");
        return getState(start);
    }

    private void parseTransitions (JSONArray transitions)
    {
        for (int i = 0; i < transitions.length(); i ++)
        {
            JSONObject obj = (JSONObject)transitions.get(i);
            this.transitions.add(new Transition(obj));
        }
    }

    private void parsePlayerLayout (JSONArray layout)
    {
        for (int i = 0; i < layout.length(); i ++)
        {
            JSONObject obj = (JSONObject)layout.get(i);
            this.layout.add(new Widget(
                    obj.getString("widget"),
                    obj.getString("category"),
                    obj.getInt("x"),
                    obj.getInt("y")));
        }
    }

    void parsePieceType (String data)
    {
        parsePieceType(new JSONObject(data));
    }

    void parsePieceType (JSONObject data)
    {
        PieceType newType = new PieceType(data);
        pieceTypes.add(newType);

    }

    PieceType getPieceType (String id)
    {
        for (PieceType type : pieceTypes)
        {
            if (type.getId().equals(id))
                return type;
        }

        return null;
    }

    private ArrayList<Piece> parsePiece (JSONObject obj)
    {
        ArrayList<Piece> pieces = new ArrayList<>();
        String type = obj.has("type") ? obj.getString("type") : "";
        String id = obj.has("id") ? obj.getString("id") : "";
        int num = obj.has("num") ? obj.getInt("num") : 1;
        int value = obj.has("value") ? obj.getInt("value") : 0;
        PieceType pt = getPieceType(type);

        // normal piece
        if (!type.equals("") && pt != null)
        {
            for (int j = 0; j < num; j ++)
            {
                pieces.add(new Piece(pt, value));
            }

            // "global" piece
//        } else if (!id.equals(""))
//        {
//            pieces.add(new Piece(id));
        }

        return pieces;
    }

    ArrayList<Piece> getPlayerPieces ()
    {
        ArrayList<Piece> pieces = new ArrayList<>();
        JSONArray data = settings.getJSONArray("playerSetup");

        for (int i = 0; i < data.length(); i ++)
        {
            JSONObject obj = (JSONObject) data.get(i);
            pieces.addAll(parsePiece(obj));
        }

        return pieces;
    }

    ArrayList<Piece> getBoardPieces ()
    {
        ArrayList<Piece> pieces = new ArrayList<>();
        JSONArray data = settings.getJSONArray("board");

        for (int i = 0; i < data.length(); i ++)
        {
            JSONObject obj = (JSONObject) data.get(i);
            pieces.addAll(parsePiece(obj));
        }

        return pieces;
    }

    State getNextState (State from)
    {
        String next = null;

        for (Transition t : transitions)
        {
            if (t.from.equals(from.getName()))
            {
                next = t.to;
                break;
            }
        }

        return getState(next);
    }

    ArrayList<PieceType> getPieceCategory (String category)
    {
        return categories.get(category);
    }

    Piece getRandomPieceOfCategory (String category)
    {
        if (!categories.containsKey(category))
            return null;

        ArrayList<PieceType> all = getPieceCategory(category);

        PieceType type = all.get(MathUtils.random(all.size() - 1));
        Piece piece = new Piece(type, 0);

        return piece;
    }

    ArrayList<Widget> getPersonalLayout ()
    {
        return layout;
    }
}
