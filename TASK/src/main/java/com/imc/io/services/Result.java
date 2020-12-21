package com.imc.io.services;

import java.util.List;

public interface Result {
    void reportChoices(List<Player> players);
    void report(int round, Player player);
    void summarize();
}
