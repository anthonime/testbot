package com.github.anthonime.testbot.runtime.elements.exceptions;

/**
 * Created by schio on 9/12/2017.
 */
public class NoSuchElementException extends RuntimeException {

    public NoSuchElementException() {
        super();
    }

    public NoSuchElementException(Throwable cause) {
        super(cause);
    }

    public NoSuchElementException(String message) {
        super(message);
    }

    public NoSuchElementException(String message, Throwable cause) {
        super(message, cause);
    }
}
