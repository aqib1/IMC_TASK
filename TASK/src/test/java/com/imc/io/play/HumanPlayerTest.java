package com.imc.io.play;

import com.imc.io.data.Hand;
import com.imc.io.data.impl.HandFactory;
import com.imc.io.data.impl.RockHand;
import com.imc.io.exceptions.HandChoiceException;
import com.imc.io.exceptions.PlayerException;
import com.imc.io.services.impl.HumanPlayer;
import com.imc.io.services.impl.IORunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class HumanPlayerTest {

    private HumanPlayer humanPlayer;

    @Before
    public void setup() {
        humanPlayer = new HumanPlayer();
    }

    @Test
    public void testChoose() throws PlayerException, IOException, HandChoiceException {
        IORunner cio = Mockito.mock(IORunner.class);
        HandFactory handFactory = Mockito.mock(HandFactory.class);
        RockHand rockHand = Mockito.mock(RockHand.class);

        humanPlayer.setCommandLineIO(cio);
        humanPlayer.setHandFactory(handFactory);

        String choice = "rock";
        Mockito.when(cio.readLine()).thenReturn(choice);
        Mockito.when(handFactory.createHand(choice)).thenReturn(rockHand);

        humanPlayer.choose();
        Assert.assertNotNull(humanPlayer.getChosenHand());
        Assert.assertEquals(rockHand, humanPlayer.getChosenHand());
    }

    @Test
    public void testDraw() {
        Hand hand = Mockito.mock(Hand.class);
        humanPlayer.setChosenHand(hand);
        Assert.assertEquals(hand, humanPlayer.draw());
    }
}
