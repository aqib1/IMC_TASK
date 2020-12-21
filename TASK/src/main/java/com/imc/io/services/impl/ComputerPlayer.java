package com.imc.io.services.impl;

import com.imc.io.data.Hand;
import com.imc.io.data.impl.HandFactory;
import com.imc.io.services.Player;
import java.util.Random;

public class ComputerPlayer implements Player {
    private Random rand;

    protected Hand chosenHand;

    protected HandFactory handFactory;

    private String name;

    public ComputerPlayer(String name) {
        rand = new Random();
        handFactory = new HandFactory();
        this.name = name;
    }

    @Override
    public void init() {

    }

    public void choose() {
        this.chosenHand = handFactory.choices().get(
                rand.nextInt(handFactory.choices().size()));
    }


    public Hand draw() {
        return this.chosenHand;
    }


    public String getName() {
        return this.name;
    }

    public Hand getChosenHand() {
        return chosenHand;
    }

    public void setChosenHand(Hand chosenHand) {
        this.chosenHand = chosenHand;
    }
}
