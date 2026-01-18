package com.airtribe.ridewise.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    private IdGenerator(){}

    public static int generateId() {
        return COUNTER.getAndIncrement();
    }
}
