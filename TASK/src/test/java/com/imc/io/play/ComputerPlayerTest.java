package com.imc.io.play;

import com.imc.io.data.Hand;
import com.imc.io.services.impl.ComputerPlayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ComputerPlayerTest {
    private ComputerPlayer computerPlayer;

    @Before
    public void setup() {
        computerPlayer = new ComputerPlayer("Comp1");
    }

    @Test
    public void testChoose() {
        computerPlayer.choose();
        Assert.assertNotNull(computerPlayer.getChosenHand());
    }

    @Test
    public void testDraw() {
        Hand hand = Mockito.mock(Hand.class);
        computerPlayer.setChosenHand(hand);
        Assert.assertEquals(hand, computerPlayer.draw());
    }
}
