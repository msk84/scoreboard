package net.msk.scoreboard.service;

public class GlobalRevisionCounter {

    private static volatile long revision;

    public static synchronized void increment() {
        revision++;
    }

    public static synchronized long getRevision() {
        return revision;
    }
}
