package com.rptr.bradgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import groovy.util.Eval;

public class GameLoader {
    static GameType load (String filepath) {
        GameType game = new GameType();

        FileHandle file = Gdx.files.internal(filepath);
        String raw = file.readString();

        System.out.println(Eval.me("33*4"));

        return game;
    }
}
