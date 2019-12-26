package com.rptr.bradgame;

public class Piece
{
    private PieceType type;
    private String id = "";
    private int value = 0;

    Piece (String id, PieceType type, int value)
    {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    String getId ()
    {
        return id;
    }

    String getTexture ()
    {
        return type == null ? "" : type.getFile();
    }

    int getValue ()
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

    void increment (int amount)
    {
        value += amount;
    }
}
