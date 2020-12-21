package com.imc.io.data;

import com.imc.io.data.impl.PaperHand;
import com.imc.io.data.impl.RockHand;
import com.imc.io.data.impl.ScissorsHand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ScissorsHandTest {

    private ScissorsHand scissorsHand;

    @Before
    public void setup() {
        scissorsHand = new ScissorsHand();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBeatsNull() {
        scissorsHand.result(null);
    }

    @Test
    public void testBeatsRock() {
        RockHand rock = new RockHand();
        Assert.assertEquals(Result.Lose, scissorsHand.result(rock));
    }

    @Test
    public void testBeatsPaper() {
        PaperHand paper = new PaperHand();
        Assert.assertEquals(Result.Win, scissorsHand.result(paper));
    }

    @Test
    public void testBeatsScissors() {
        ScissorsHand scissors = new ScissorsHand();
        Assert.assertEquals(Result.Draw, scissorsHand.result(scissors));
    }
}
