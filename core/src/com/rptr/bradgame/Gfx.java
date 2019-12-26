package com.rptr.bradgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.HashMap;

public class Gfx
{
    private static HashMap<String, Texture> textures = new HashMap<>();

    public static void drawPiece (Batch batch, Piece piece)
    {
        String file = piece.getType().getFile();

        if (file.isEmpty())
            return;

        Texture tex = Gfx.getTexture(file);
        batch.draw(tex, 0, 0);
    }

    public static void loadTexture (String file)
    {
        textures.put(file, new Texture(file));
    }

    public static Texture getTexture (String file)
    {
        return textures.get(file);
    }
}
