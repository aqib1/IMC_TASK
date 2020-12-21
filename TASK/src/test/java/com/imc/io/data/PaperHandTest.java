package com.imc.io.data;

import com.imc.io.data.impl.PaperHand;
import com.imc.io.data.impl.RockHand;
import com.imc.io.data.impl.ScissorsHand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PaperHandTest {
    private PaperHand paperHand;

    @Before
    public void setup() {
        paperHand = new PaperHand();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBeatNull() {
        paperHand.result(null);
    }

    @Test
    public void testBeatsRock() {
        RockHand rock = new RockHand();
        Assert.assertEquals(Result.Win, paperHand.result(rock));
    }

    @Test
    public void testBeatsPaper() {
        PaperHand paper = new PaperHand();
        Assert.assertEquals(Result.Draw, paperHand.result(paper));
    }

    @Test
    public void testBeatsScissors() {
        ScissorsHand scissors = new ScissorsHand();
        Assert.assertEquals(Result.Lose, paperHand.result(scissors));
    }
}
