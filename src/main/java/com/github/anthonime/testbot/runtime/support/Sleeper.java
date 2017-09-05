package com.github.anthonime.testbot.runtime.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Created by schio on 9/15/2017.
 */
public class Sleeper {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sleeper.class);

    public static void sleep(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            LOGGER.warn("Could not sleep {}", duration.toString(), e);
        }
    }
}
