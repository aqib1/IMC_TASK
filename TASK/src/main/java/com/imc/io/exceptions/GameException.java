package com.imc.io.exceptions;

public class GameException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -1616396589910638070L;

    public GameException(String message) {
        super(message);
    }

    public GameException(String message, Throwable t) {
        super(message, t);
    }

}
