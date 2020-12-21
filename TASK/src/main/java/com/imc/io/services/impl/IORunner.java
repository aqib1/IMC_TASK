package com.imc.io.services.impl;

import com.imc.io.data.Hand;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class IORunner {
    public void printChoices(List<Hand> choices) {
        StringBuffer message = new StringBuffer("Please choose [");
        for (Hand h : choices) {
            message.append(h.getName() + " ");
        }
        message.append("]");
        System.out.println(message);
    }

    public void printHelpMessage(List<Hand> choices) {
        System.out.println("Invalid choices. Please try again.");
        printChoices(choices);
    }

    public String readLine() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));

        return reader.readLine();
    }

    public void printAskForName() {
        System.out.println("Enter your name?");
    }

    public void print(String message) {
        System.out.println(message);
    }
}
