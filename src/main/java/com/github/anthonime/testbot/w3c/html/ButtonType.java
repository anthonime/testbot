package com.github.anthonime.testbot.w3c.html;

/**
 * Created by schio on 9/12/2017.
 */
public enum ButtonType {

    button, reset, submit;

    public static boolean isButtonType(String identifier) {
        try {
            valueOf(identifier);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
