package com.rptr.bradgame;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.HashMap;

public class Gfx
{
    private static HashMap<String, Texture> textures = new HashMap<>();

    public static void loadTexture (String file)
    {
        textures.put(file, new Texture(file));
    }

    public static Texture getTexture (String file)
    {
        return textures.get(file);
    }
}
