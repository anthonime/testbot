package com.github.anthonime.testbot.w3c.urls;

/**
 * Created by schio on 9/16/2017.
 */
public class Port {
    public static Port NONE = new Port(-1);

    public static Port of(int port) {
        return new Port(port);
    }

    private Integer value;

    private Port(Integer value) {
        this.value = value;
    }
}
