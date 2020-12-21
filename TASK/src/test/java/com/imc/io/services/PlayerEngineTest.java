package com.imc.io.services;

import com.imc.io.data.Hand;
import com.imc.io.data.Result;
import com.imc.io.services.impl.PlayerEngine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class PlayerEngineTest {
    private PlayerEngine playerEngine;

    @Before
    public void init() {
        playerEngine = new PlayerEngine();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDetermineWinnerNull() {
        playerEngine.determineWinner(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDetermineWinnerEmptyList() {
        playerEngine.determineWinner(new ArrayList<Player>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDetermineWinner1Player() {
        List<Player> players = new ArrayList<>();
        players.add(Mockito.mock(Player.class));
        playerEngine.determineWinner(players);
    }

    @Test
    public void testDetermineWinnerPlayer1Wins() {
        List<Player> players = new ArrayList<>();
        Player p1 = Mockito.mock(Player.class);
        Player p2 = Mockito.mock(Player.class);
        players.add(p1);
        players.add(p2);
        Hand p1Hand = Mockito.mock(Hand.class);
        Hand p2Hand = Mockito.mock(Hand.class);
        Mockito.when(p1.draw()).thenReturn(p1Hand);
        Mockito.when(p2.draw()).thenReturn(p2Hand);
        Mockito.when(p1Hand.result(p2Hand)).thenReturn(Result.Win);
        Player winner = playerEngine.determineWinner(players);
        Assert.assertEquals(p1, winner);
    }

    @Test
    public void testDetermineWinnerPlayer2Wins() {
        List<Player> players = new ArrayList<Player>();
        Player p1 = Mockito.mock(Player.class);
        Player p2 = Mockito.mock(Player.class);
        players.add(p1);
        players.add(p2);
        Hand p1Hand = Mockito.mock(Hand.class);
        Hand p2Hand = Mockito.mock(Hand.class);
        Mockito.when(p1.draw()).thenReturn(p1Hand);
        Mockito.when(p2.draw()).thenReturn(p2Hand);
        Mockito.when(p1Hand.result(p2Hand)).thenReturn(Result.Lose);
        Player winner = playerEngine.determineWinner(players);
        Assert.assertEquals(p2, winner);
    }

    @Test
    public void testDetermineWinnerDraw() {
        List<Player> players = new ArrayList<Player>();
        Player p1 = Mockito.mock(Player.class);
        Player p2 = Mockito.mock(Player.class);
        players.add(p1);
        players.add(p2);
        Hand p1Hand = Mockito.mock(Hand.class);
        Hand p2Hand = Mockito.mock(Hand.class);
        Mockito.when(p1.draw()).thenReturn(p1Hand);
        Mockito.when(p2.draw()).thenReturn(p2Hand);
        Mockito.when(p1Hand.result(p2Hand)).thenReturn(Result.Draw);
        Player winner = playerEngine.determineWinner(players);
        Assert.assertNull(winner);
    }
}
