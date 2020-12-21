package com.imc.io.data.impl;

import com.imc.io.data.AbstractHand;

public class PaperHand extends AbstractHand {
    private static final String NAME = "paper";

    public PaperHand() {
        this.addToBeatableHands(RockHand.class);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
