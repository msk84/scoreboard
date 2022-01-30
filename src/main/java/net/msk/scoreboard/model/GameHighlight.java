package net.msk.scoreboard.model;

import net.msk.dartscoreValidation.ScoreValidator;

public record GameHighlight(Type type, Integer value) {

    public GameHighlight {
        if(type == Type.HighFinish && !ScoreValidator.isValidHighfinish(value)) {
            throw new ScoreValidationException("No valid HighFinish.");
        }
        else if(type == Type.ShortGame && !ScoreValidator.isValidShortgame(value)) {
            throw new ScoreValidationException("No valid ShortGame.");
        }
    }

    public enum Type {
        OneEighty,
        HighFinish,
        ShortGame
    }
}
