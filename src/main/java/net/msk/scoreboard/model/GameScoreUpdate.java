package net.msk.scoreboard.model;

public class GameScoreUpdate {

    private String scoreParty;
    private Integer gameScore;

    GameScoreUpdate(final String scoreParty, final Integer gameScore) {
        this.scoreParty = scoreParty;
        this.gameScore = gameScore;
    }

    public String getScoreParty() {
        return scoreParty;
    }

    public void setScoreParty(String scoreParty) {
        this.scoreParty = scoreParty;
    }

    public Integer getGameScore() {
        return gameScore;
    }

    public void setGameScore(Integer gameScore) {
        this.gameScore = gameScore;
    }
}
