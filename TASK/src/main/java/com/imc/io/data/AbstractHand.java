package com.imc.io.data;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHand implements Hand {
    private List<Class<? extends Hand>> beatableHands;

    public AbstractHand() {
        beatableHands = new ArrayList<Class<? extends Hand>>();
    }

    @Override
    public Result result(Hand otherHand) {
        if (otherHand == null) {
            throw new IllegalArgumentException("Can't do compare. OtherHand was null");
        }
        if (otherHand.getClass() == this.getClass()) {
            return Result.Draw;
        }
        return beatableHands.contains(otherHand.getClass()) ? Result.Win : Result.Lose;
    }

    protected void addToBeatableHands(Class<? extends Hand> hand) {
        beatableHands.add(hand);
    }
}
