package com.imc.io.data.impl;

import com.imc.io.data.Hand;
import com.imc.io.exceptions.HandChoiceException;

import java.util.ArrayList;
import java.util.List;

public class HandFactory {
    // hands that can called.
    private List<Hand> possibleHands;

    /**
     * Creates the hand factory
     */
    public HandFactory() {
        possibleHands = new ArrayList<Hand>();
        possibleHands.add(new RockHand());
        possibleHands.add(new ScissorsHand());
        possibleHands.add(new PaperHand());
    }

    /**
     * Converts a string input to a hand that can be played.
     * @param choice
     * @return
     * @throws HandChoiceException
     */
    public Hand createHand(String choice) throws HandChoiceException {
        if (choice == null) {
            throw new HandChoiceException("Choice was null");
        }

        Hand chosen = null;
        for (Hand h : possibleHands) {
            if (choice.compareToIgnoreCase(h.getName()) == 0) {
                chosen = h;
            }
        }

        if (chosen == null) {
            throw new HandChoiceException("No choice found for " + choice);
        }

        return chosen;
    }

    /**
     * @return All possible hands
     */
    public List<Hand> choices() {
        return possibleHands;
    }
}
