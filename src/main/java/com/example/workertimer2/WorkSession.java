package com.example.workertimer2;

public class WorkSession {
    private static int secondsPassed = 0; // shared across controllers

    public static int getSecondsPassed() {
        return secondsPassed;
    }

    public static void setSecondsPassed(int seconds) {
        secondsPassed = seconds;
    }

    public static int getHours() {
        return secondsPassed / 3600;
    }

    public static int getMinutes() {
        return (secondsPassed % 3600) / 60;
    }

    public static int getSeconds() {
        return secondsPassed % 60;
    }

    public static void increment() {
        secondsPassed++;
    }

    public static void reset() {
        secondsPassed = 0;
    }
}
