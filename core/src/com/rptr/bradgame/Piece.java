package com.rptr.bradgame;

public class Piece
{
    private PieceType type;
    private String id = "";
    private int value = 0;

    Piece (PieceType type, int value)
    {
        this.type = type;
        this.value = value;
    }

    // "global" piece
    Piece (String id)
    {
        this.id = id;
    }

    String getId ()
    {
        return id;
    }

    String getTexture ()
    {
        return type == null ? "" : type.getFile();
    }

    float getValue ()
    {
        return value;
    }

    @Override
    public String toString ()
    {
        String s = type == null ? id : type.getId();

        if (type.getType().equals("number"))
            s += " : "+Integer.toString(value);

        return s;
    }

    PieceType getType ()
    {
        return type;
    }
}
