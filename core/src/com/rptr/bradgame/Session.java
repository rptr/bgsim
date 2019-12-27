package com.rptr.bradgame;

import com.badlogic.gdx.Game;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Session
{
    int turn;
    State state;
    ArrayList<Player> players;
    private GameType game;
    ArrayList<Piece> pieces;
    int currentPlayer;

    // XXX TEMP too lazy for observer
    boolean guiUpdated = true;

    Session (GameType game)
    {
        this.game = game;
        players = new ArrayList<>();
        pieces = new ArrayList<>();

        newGame();
    }

    private void newGame ()
    {
        turn = 1;
        state = game.getStartingState();

        // game board pieces
        pieces.addAll(game.getBoardPieces());

        for (int i = 0; i < 2; i ++)
        {
            Player p = new Player();
            p.addPieces(game.getPlayerPieces());
            players.add(p);
        }

        currentPlayer = 0;

        runState();
    }

    private void runState ()
    {
        System.out.format("Entering state %s\n", state.getName());

        for (Player p : players)
        {
            p.setReady(false);
            currentPlayer = 0;
        }

        state.performEvents(this, null);
    }

    private void transition ()
    {
        state = game.getNextState(state);
        guiUpdated = true;

        runState();
    }

    void givePiecesOfCategory (String category, int amount)
    {
        for (Player p : players)
        {
            for (int i = 0; i < amount; i ++)
                p.givePiece(this, game.getRandomPieceOfCategory(category));
        }

        System.out.format("All players get %d %s\n", amount, category);
    }

    void givePiecesOfType (String type, int amount)
    {
        System.out.format("give %d %s\n", amount, type);
    }

    GameType getGame ()
    {
        return game;
    }

    String getState ()
    {
        return state.getName();
    }

    int getTurn ()
    {
        return turn;
    }

    void playerReady (boolean set)
    {
        getPlayer().setReady(set);

        if (allPlayersReady())
        {
            transition();
        }
    }

    void passTurn ()
    {
        currentPlayer = (currentPlayer + 1) % players.size();
    }

    Player getPlayer ()
    {
        return players.get(currentPlayer);
    }

    String getTurnInfo ()
    {
        String s = "";

        s += String.format("current player: %s (%d)\n", getPlayer().name, currentPlayer);

        return s;
    }

    boolean allPlayersReady ()
    {
        boolean all = true;

        for (Player p : players)
        {
            if (!p.ready)
            {
                all = false;
                break;
            }
        }

        return all;
    }
}
