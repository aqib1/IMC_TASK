package com.imc.io.services.impl;

import com.imc.io.exceptions.GameException;
import com.imc.io.exceptions.PlayerException;
import com.imc.io.services.Game;
import com.imc.io.services.Player;
import com.imc.io.services.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.imc.io.utils.AppConsts.MAX_PLAYERS;

public class PlayerGame implements Game {

    protected List<Player> players;

    protected int numberOfRounds;

    protected PlayerEngine engine;

    protected Result resultReporter;

    public PlayerGame() {
        players = new ArrayList<Player>();
        engine = new PlayerEngine();
    }

    public void addPlayer(Player player) throws GameException {
        if (player == null) {
            throw new IllegalArgumentException();
        }
        if (players.size() == MAX_PLAYERS) {
            throw new GameException("Game is full. No new players allowed.");
        }

        if (!players.contains(player)) {
            players.add(player);
        }
    }


    public void setNumberOfRound(int numberOfRounds) {
        if (numberOfRounds < 1) {
            throw new IllegalArgumentException(
                    "Number of rounds must be at least 1");
        }
        this.numberOfRounds = numberOfRounds;
    }


    public void play() throws GameException {
        if (players.size() < MAX_PLAYERS) {
            throw new GameException("Not enough players registered to play");
        }


        players.stream().forEach(p -> p.init());


        for (int i = 1; i <= numberOfRounds; i++) {
            for (int j = 0, n = players.size(); j < n; j++) {
                try {
                    players.get(j).choose();
                } catch (PlayerException e) {
                    throw new GameException("Problem occurred running game", e);
                }
            }
            resultReporter.reportChoices(players);
            Player winner = engine.determineWinner(players);
            resultReporter.report(i, winner);
        }

        resultReporter.summarize();
    }

    public void setResultReporter(Result resultReporter) {
        this.resultReporter = resultReporter;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setEngine(PlayerEngine engine) {
        this.engine = engine;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }
}
