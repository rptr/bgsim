package com.rptr.bradgame;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;

public class Widget
{
    String type, category, clickAction;
    int x, y;

    Widget (String type, String category, int x, int y, String click)
    {
        this.type = type;
        this.category = category;
        this.clickAction = click;
        this.x = x;
        this.y = y;
    }

    void draw (Batch batch, ArrayList<Piece> pieces)
    {
        int i = 0;

        for (Piece p : pieces)
        {
            if (!p.getType().getCategory().equals(category))
                continue;

            Gfx.drawPiece(batch, p, x + i * 20, y);
            i ++;
        }
    }
}
