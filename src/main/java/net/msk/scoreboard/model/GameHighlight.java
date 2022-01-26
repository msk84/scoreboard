package net.msk.scoreboard.model;

public record GameHighlight(Type type, Integer value) {

    public GameHighlight {
        // Todo: Validation
    }

    public enum Type {
        OneEighty,
        HighFinish,
        ShortGame
    }
}
