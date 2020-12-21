package com.imc.io;


import com.imc.io.exceptions.GameException;
import com.imc.io.services.Player;
import com.imc.io.services.Result;
import com.imc.io.services.impl.ComputerPlayer;
import com.imc.io.services.impl.HumanPlayer;
import com.imc.io.services.impl.PlayerGame;
import com.imc.io.services.impl.ResultImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Runner {
    public static void main(String[] args) throws GameException, IOException {
        System.out.println("Welcome to the Rock,Scissors,Paper game");
        System.out
                .println("You can either player human v computer or computer v computer.");

        PlayerGame playerGame = new PlayerGame();
        Result reporter = new ResultImpl();
        playerGame.setResultReporter(reporter);

        Player player1 = getPlayer(1);
        Player player2 = null;
        if (player1 instanceof HumanPlayer) {
            System.out.println("Player one is a human, so player two will be the computer");
            player2 = new ComputerPlayer("Player2");
        } else {
            player2 = getPlayer(2);
        }

        playerGame.addPlayer(player1);
        playerGame.addPlayer(player2);

        System.out.println("Now choose how many rounds");
        int rounds = getRounds();

        playerGame.setNumberOfRound(rounds);
        playerGame.play();
    }


    private static Player getPlayer(int playerNum) throws IOException {
        System.out.println("Please choose your type for player "+playerNum+":");
        System.out.println("1: Computer, 2: Human, 0: exit");
        Player player = null;
        while (player == null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    System.in));
            String input = reader.readLine();
            int val = -1;
            try {
                val = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.err.println("Not a valid choice, try again.");
                continue;
            }

            //exit
            if (val == 0) {
                System.exit(-1);
            }

            if (val == 1) {
                player = new ComputerPlayer("Player"+playerNum);
            } else if (val == 2) {
                player = new HumanPlayer();
            } else {
                System.err.println("Not a valid choice, try again.");
                continue;
            }
        }

        return player;
    }

    private static int getRounds() throws IOException, IOException {
        System.out.println("Please choose the number of rounds to play [0 to exit]");

        int rounds = -1;
        while (rounds == -1) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    System.in));
            String input = reader.readLine();
            try {
                rounds = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.err.println("Not a valid choice, try again.");
                continue;
            }
        }

        return rounds;
    }
}
