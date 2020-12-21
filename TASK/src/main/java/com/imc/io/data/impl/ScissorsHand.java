package com.imc.io.data.impl;

import com.imc.io.data.AbstractHand;
import com.imc.io.data.impl.PaperHand;

public class ScissorsHand extends AbstractHand {
    private static final String NAME = "scissors";

    public ScissorsHand() {
        this.addToBeatableHands(PaperHand.class);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
