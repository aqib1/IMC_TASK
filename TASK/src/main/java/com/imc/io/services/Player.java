package com.imc.io.services;

import com.imc.io.data.Hand;
import com.imc.io.exceptions.PlayerException;

public interface Player {
    void init();
    void choose() throws PlayerException;
    Hand draw();
    String getName();
}
