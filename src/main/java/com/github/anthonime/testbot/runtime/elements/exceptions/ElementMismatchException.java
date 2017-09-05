package com.github.anthonime.testbot.runtime.elements.exceptions;

/**
 * Created by schio on 9/10/2017.
 */
public class ElementMismatchException extends RuntimeException {
    public ElementMismatchException(String message) {
        super(message);
    }

    public ElementMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
