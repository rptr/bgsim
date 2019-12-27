package com.rptr.bradgame;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;

public class Widget
{
    String type, clickAction, id;
    int x, y;

    Widget (String id, String type, int x, int y, String click)
    {
        this.id = id;
        this.type = type;
        this.clickAction = click;
        this.x = x;
        this.y = y;
    }

    void draw (Batch batch, ArrayList<Piece> pieces)
    {
        int i = 0;

        for (Piece p : pieces)
        {
//            if (!p.getType().getCategory().equals(category))
//                continue;

            Gfx.drawPiece(batch, p, x + i * 20, y);
            i ++;
        }
    }
}
