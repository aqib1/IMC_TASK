package com.imc.io.services.impl;

import com.imc.io.services.Player;
import com.imc.io.services.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultImpl implements Result {
    private Map<Player, Integer> winRecord;

    public ResultImpl() {
        winRecord = new HashMap<Player, Integer>();
    }

    public void report(int round, Player winner) {
        if (winner == null) {
            System.out.println("Round " + round + " was a draw.");
        } else {
            System.out.println("Round " + round + " was won by player "
                    + winner.getName());
            if (winRecord.containsKey(winner)) {
                winRecord.put(winner, winRecord.get(winner) + 1);
            } else {
                winRecord.put(winner, 1);
            }
        }
    }

    public void summarize() {
        winRecord.entrySet().forEach(e -> {
            System.out.println("Player " + e.getKey().getName() + " won "
                    + e.getValue() + " times");
        });
    }

    public void reportChoices(List<Player> players) {
        players.stream().forEach(p -> {
            System.out.println("Player " + p.getName() + " chose "
                    + p.draw().getName());
        });
    }
}
