package com.github.anthonime.testbot.w3c;

import org.openqa.selenium.Keys;

/**
 * Created by schio on 9/10/2017.
 */
public enum ArrowKey {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public Keys toWebDriverKeys() {
        switch (this) {
            case UP:
                return Keys.ARROW_UP;
            case DOWN:
                return Keys.ARROW_DOWN;
            case LEFT:
                return Keys.ARROW_LEFT;
            case RIGHT:
                return Keys.ARROW_RIGHT;
            default:
                throw new IllegalStateException("Arrow not implemented!" + this);
        }
    }
}
