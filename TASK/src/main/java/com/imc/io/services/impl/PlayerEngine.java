package com.imc.io.services.impl;

import com.imc.io.data.Hand;
import com.imc.io.data.Result;
import com.imc.io.services.Player;

import java.util.List;

public class PlayerEngine {
    private static final int NUMBER_OF_PLAYERS = 2;


    public Player determineWinner(List<Player> players) {
        if (players == null || players.size() != NUMBER_OF_PLAYERS) {
            throw new IllegalArgumentException("You need to have two players to use this engine.");
        }

        Hand player1Hand = players.get(0).draw();
        Hand player2Hand = players.get(1).draw();

        Result result = player1Hand.result(player2Hand);

        switch (result) {
            case Win:
                return players.get(0);
            case Lose:
                return players.get(1);
            default:
                return null;
        }
    }
}
