package net.msk.scoreboard.model;

public class Game {

    private long id;

    private GameStatus status;

    private String partyA;
    private String partyB;

    private Integer scoreA;
    private Integer scoreB;

    public Game() {
        this.status = GameStatus.PLANNED;
        this.scoreA = 0;
        this.scoreB = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public String getPartyA() {
        return partyA;
    }

    public void setPartyA(String partyA) {
        this.partyA = partyA;
    }

    public String getPartyB() {
        return partyB;
    }

    public void setPartyB(String partyB) {
        this.partyB = partyB;
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

    public String getScore() {
        return this.scoreA.toString() + " : " + this.scoreB.toString();
    }
}
