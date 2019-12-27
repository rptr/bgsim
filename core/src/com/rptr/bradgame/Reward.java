package com.rptr.bradgame;

import java.util.ArrayList;

abstract class Reward extends Event
{
}

class IncomeReward extends Reward
{
    String piece, incomePieceId;

    IncomeReward (String category, String income)
    {
        this.piece = category;
        this.incomePieceId = income;
    }

    void apply (Session session, Player player)
    {
        ArrayList<Player> players = session.players;

        for (Player p : players)
        {
            int amount = p.getPiece(incomePieceId).getValue();
            p.incrementPiece(piece, amount);
        }

        System.out.format("Everyone gets %s income!\n", incomePieceId);
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

    void apply (Session session, Player player)
    {
        session.givePiecesOfCategory(category, amount);
    }
}

class PieceReward extends Reward
{
    String pieceType;
    int amount;

    PieceReward (String pieceType, int amount)
    {
        this.pieceType = pieceType;
        this.amount = amount;
    }

    void apply (Session session, Player player)
    {
        player.incrementPiece(pieceType, amount);
    }
}