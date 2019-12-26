package com.rptr.bradgame;

import java.util.ArrayList;

public class Player
{
    String name;
    boolean ready, alive;
    ArrayList<Piece> pieces;

    static String[] names = {"Rasmus", "Paul", "Goon", "Darmor"};
    static int nameCount = 0;

    Player ()
    {
        name = names[nameCount++];
        ready = false;
        alive = true;
        pieces = new ArrayList<>();
    }

    void addPieces (ArrayList<Piece> pieces)
    {
        this.pieces.addAll(pieces);
    }

    void givePiece (Piece piece)
    {
        pieces.add(piece);
    }

    @Override
    public String toString ()
    {
        String s = name + " ";

        s += ready ? "| ready " : "| not ready ";
        s += alive ? "| alive " : "| defeated ";

        return s;
    }

    void setReady (boolean set)
    {
        ready = set;
    }
}
