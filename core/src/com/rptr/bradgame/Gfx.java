package com.rptr.bradgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.HashMap;

public class Gfx
{
    private static HashMap<String, Texture> textures = new HashMap<>();

    static void drawPiece (Batch batch, Piece piece)
    {
        drawPiece(batch, piece, 0, 0);
    }

    static void drawPiece (Batch batch, Piece piece, int x, int y)
    {
        String file = piece.getType().getFile();

        if (file.isEmpty())
            return;

        Texture tex = Gfx.getTexture(file);
        batch.draw(tex, x, y);
    }

    static void loadTexture (String file)
    {
        textures.put(file, new Texture(file));
    }

    static Texture getTexture (String file)
    {
        return textures.get(file);
    }
}
