package net.msk.scoreboard.model;

public class GameScore {

    private Integer scoreA;
    private Integer scoreB;

    GameScore(final Integer scoreA, final Integer scoreB) {
        this.scoreA = scoreA;
        this.scoreB = scoreB;
    }

    public Integer getScoreA() {
        return scoreA;
    }

    public void setScoreA(Integer scoreA) {
        this.scoreA = scoreA;
    }

    public Integer getScoreB() {
        return scoreB;
    }

    public void setScoreB(Integer scoreB) {
        this.scoreB = scoreB;
    }
}
