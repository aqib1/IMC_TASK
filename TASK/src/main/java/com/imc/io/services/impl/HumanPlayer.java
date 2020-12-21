package com.imc.io.services.impl;

import com.imc.io.data.Hand;
import com.imc.io.data.impl.HandFactory;
import com.imc.io.exceptions.HandChoiceException;
import com.imc.io.exceptions.PlayerException;
import com.imc.io.services.Player;
import java.io.IOException;
import static com.imc.io.utils.AppConsts.DEFAULT_NAME;
public class HumanPlayer implements Player {

    private Hand chosenHand;

    private HandFactory handFactory;

    private IORunner commandLineIO;

    private String name;

    public HumanPlayer() {
        handFactory = new HandFactory();
        commandLineIO = new IORunner();
        name = DEFAULT_NAME;
    }

    @Override
    public void init() {
        commandLineIO.printAskForName();
        try {
            this.name = commandLineIO.readLine();
        } catch (IOException e) {
            commandLineIO
                    .print("I didn't catch that, so I'm going to call you player1");
            this.name = DEFAULT_NAME;
        }
    }

    public void choose() throws PlayerException {
        this.chosenHand = null;

        while (this.chosenHand == null) {
            commandLineIO.printChoices(handFactory.choices());
            try {
                String choice = commandLineIO.readLine();
                this.chosenHand = handFactory.createHand(choice);
            } catch (HandChoiceException e) {
                commandLineIO.printHelpMessage(handFactory.choices());
                continue;
            } catch (IOException e) {
                throw new PlayerException("Failed to read choice", e);
            }
        }
    }

    public Hand draw() {
        return this.chosenHand;
    }

    public String getName() {
        return this.name;
    }

    public void setCommandLineIO(IORunner commandLineIO) {
        this.commandLineIO = commandLineIO;
    }

    public void setHandFactory(HandFactory handFactory) {
        this.handFactory = handFactory;
    }

    public Hand getChosenHand() {
        return chosenHand;
    }

    public void setChosenHand(Hand chosenHand) {
        this.chosenHand = chosenHand;
    }
}
