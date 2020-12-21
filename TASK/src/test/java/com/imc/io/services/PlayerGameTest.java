package com.imc.io.services;

import com.imc.io.exceptions.GameException;
import com.imc.io.services.impl.PlayerEngine;
import com.imc.io.services.impl.PlayerGame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.stream.IntStream;

public class PlayerGameTest {
    private PlayerGame playerGame;

    @Before
    public void setup() {
        playerGame = new PlayerGame();
    }

    @Test
    public void testAddOnePlayer() throws Exception {
        Player player = Mockito.mock(Player.class);
        playerGame.addPlayer(player);
        Assert.assertEquals(1, playerGame.getPlayers().size());
        Assert.assertTrue(playerGame.getPlayers().contains(player));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullPlayer() throws Exception {
        playerGame.addPlayer(null);
    }

    @Test
    public void testAddTwoPlayers() throws Exception {
        Player player1 = Mockito.mock(Player.class);
        Player player2 = Mockito.mock(Player.class);
        playerGame.addPlayer(player1);
        playerGame.addPlayer(player2);
        Assert.assertEquals(2, playerGame.getPlayers().size());
        Assert.assertTrue(playerGame.getPlayers().contains(player1));
        Assert.assertTrue(playerGame.getPlayers().contains(player2));
    }

    @Test
    public void testAddThreePlayers() throws Exception {
        Player player1 = Mockito.mock(Player.class);
        Player player2 = Mockito.mock(Player.class);
        Player player3 = Mockito.mock(Player.class);
        playerGame.addPlayer(player1);
        playerGame.addPlayer(player2);
        try {
            playerGame.addPlayer(player3);
            Assert.fail("Managed to add too many players to the game");
        } catch (GameException e) {
            // test passed
        }
    }

    @Test
    public void testAddSamePlayerTwice() throws Exception {
        Player player = Mockito.mock(Player.class);
        playerGame.addPlayer(player);
        playerGame.addPlayer(player);
        Assert.assertEquals(1, playerGame.getPlayers().size());
        Assert.assertTrue(playerGame.getPlayers().contains(player));
    }

    @Test
    public void testSetNumberOfRound() {
        playerGame.setNumberOfRound(1);
        Assert.assertEquals(1, playerGame.getNumberOfRounds());
    }

    @Test
    public void testSetNumberOfRoundEven() {
        playerGame.setNumberOfRound(4);
        Assert.assertEquals(4, playerGame.getNumberOfRounds());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNumberOfRoundZero() {
        playerGame.setNumberOfRound(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNumberOfRoundNegative() {
        playerGame.setNumberOfRound(-1);
    }

    @Test
    public void testPlay() throws Exception {
        // initialise
        Player player1 = Mockito.mock(Player.class);
        Player player2 = Mockito.mock(Player.class);

        PlayerEngine engine = Mockito.mock(PlayerEngine.class);
        Result reporter = Mockito.mock(Result.class);

        playerGame.getPlayers().add(player1);
        playerGame.getPlayers().add(player2);
        playerGame.setNumberOfRound(3);
        playerGame.setEngine(engine);
        playerGame.setResultReporter(reporter);

        Mockito.when(engine.determineWinner(playerGame.getPlayers())).thenReturn(player1);

        // play
        playerGame.play();

        // check that the game played correctly.
        Mockito.verify(player1).init();
        Mockito.verify(player2).init();
        Mockito.verify(player1, Mockito.times(3)).choose();
        Mockito.verify(player2, Mockito.times(3)).choose();
        IntStream.range(1, playerGame.getNumberOfRounds()+1).forEach(i -> {
            Mockito.verify(reporter).report(i, player1);
        });
        Mockito.verify(reporter, Mockito.times(3)).reportChoices(playerGame.getPlayers());
        Mockito.verify(reporter).summarize();
    }

    @Test(expected = GameException.class)
    public void testPlayNoPlayers() throws Exception {
        playerGame.getPlayers().clear();
        playerGame.play();
    }

    @Test(expected = GameException.class)
    public void testPlayOnePlayers() throws Exception {
        Player player = Mockito.mock(Player.class);
        playerGame.getPlayers().add(player);
        playerGame.play();
    }
}
