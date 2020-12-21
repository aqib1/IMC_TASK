package com.imc.io.data;

import com.imc.io.data.impl.PaperHand;
import com.imc.io.data.impl.RockHand;
import com.imc.io.data.impl.ScissorsHand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RockHandTest {
    private RockHand rockHand;

    @Before
    public void setup() {
        rockHand = new RockHand();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBeatsNull() {
        rockHand.result(null);
    }

    @Test
    public void testBeatsRock() {
        RockHand rock = new RockHand();
        Assert.assertEquals(Result.Draw, rockHand.result(rock));
    }

    @Test
    public void testBeatsPaper() {
        PaperHand paper = new PaperHand();
        Assert.assertEquals(Result.Lose, rockHand.result(paper));
    }

    @Test
    public void testBeatsScissors() {
        ScissorsHand scissors = new ScissorsHand();
        Assert.assertEquals(Result.Win, rockHand.result(scissors));
    }
}
