package com.rptr.bradgame;

import java.lang.reflect.Array;
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

    ArrayList<Piece> getAllPieces ()
    {
        return pieces;
    }

    /*
     * Increment a "number"-type piece.
     */
    void incrementPiece (String piece, int amount)
    {
        Piece p = getPiece(piece);

        // TODO use a constant, not a string
        if (p.getType().getType().equals("number"))
        {
            p.increment(amount);
        }
    }

    Piece getPiece (String id)
    {
        for (Piece p : pieces)
        {
            if (p.getId().equals(id))
                return p;
        }

        return null;
    }

    void playPiece (Piece piece)
    {
        pieces.remove(piece);
        System.out.format("Play & remove piece %s\n", piece.toString());
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
