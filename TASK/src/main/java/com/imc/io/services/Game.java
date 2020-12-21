package com.imc.io.services;

import com.imc.io.exceptions.GameException;

public interface Game {
    void addPlayer(Player player) throws GameException;
    void setNumberOfRound(int numberOfRounds);
    void play() throws GameException;
}
