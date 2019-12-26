package com.rptr.bradgame;

import java.util.ArrayList;

abstract class Reward
{
    abstract void give(Session session);
}

class IncomeReward extends Reward
{
    String piece, incomePieceId;

    IncomeReward (String category, String income)
    {
        this.piece = category;
        this.incomePieceId = income;
    }

    void give (Session session)
    {
        ArrayList<Player> players = session.players;

        for (Player p : players)
        {
            int amount = p.getPiece(incomePieceId).getValue();
            p.incrementPiece(piece, amount);
        }
    }
}

class CategoryReward extends Reward
{
    String category;
    int amount;

    CategoryReward (String category, int amount)
    {
        this.category = category;
        this.amount = amount;
    }

    void give (Session session)
    {
        session.givePiecesOfCategory(category, amount);
    }
}