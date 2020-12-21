package com.imc.io.data;

import com.imc.io.data.impl.HandFactory;
import com.imc.io.data.impl.PaperHand;
import com.imc.io.data.impl.RockHand;
import com.imc.io.data.impl.ScissorsHand;
import com.imc.io.exceptions.HandChoiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class HandFactoryTest {
    private HandFactory handFactory;

    @Before
    public void setup() {
        handFactory = new HandFactory();
    }

    @Test
    public void testCreateHandRock() throws Exception {
        Assert.assertTrue(handFactory.createHand("rock") instanceof RockHand);
    }

    @Test
    public void testCreateHandPaper() throws Exception {
        Assert.assertTrue(handFactory.createHand("paper") instanceof PaperHand);
    }

    @Test
    public void testCreateHandScissors() throws Exception {
        Assert.assertTrue(handFactory.createHand("scissors") instanceof ScissorsHand);
    }

    @Test(expected = HandChoiceException.class)
    public void testCreateHandNull() throws Exception {
        handFactory.createHand(null);
    }

    @Test(expected = HandChoiceException.class)
    public void testCreateHandEmptyString() throws Exception {
        handFactory.createHand("");
    }

    @Test(expected = HandChoiceException.class)
    public void testCreateHandEmptyBadChoice() throws Exception {
        handFactory.createHand("alpha");
    }
}
