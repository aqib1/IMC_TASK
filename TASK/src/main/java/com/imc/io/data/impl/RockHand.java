package com.imc.io.data.impl;

import com.imc.io.data.AbstractHand;

public class RockHand extends AbstractHand {
    private static final String NAME = "ROCK";

    public RockHand() {
        this.addToBeatableHands(ScissorsHand.class);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
