package net.msk.scoreboard.model;

public class Game {

    private long id;

    private GameStatus status;
    private Integer index;

    private String partyA;
    private String partyB;

    private Integer scoreA;
    private Integer scoreB;

    public Game() {
        this.status = GameStatus.PLANNED;
        this.scoreA = 0;
        this.scoreB = 0;
    }

    public Game(final Integer index) {
        this();
        this.index = index;
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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
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
        return this.scoreA;
    }

    public String getScoreAString() { return this.scoreA == null ? "0" : this.scoreA.toString(); }

    public void setScoreA(Integer scoreA) {
        this.scoreA = scoreA;
    }

    public Integer getScoreB() {
        return this.scoreB;
    }

    public String getScoreBString() { return this.scoreB == null ? "0" : this.scoreB.toString(); }

    public void setScoreB(Integer scoreB) {
        this.scoreB = scoreB;
    }

    public String getScoreString() {
        if (this.status == GameStatus.PLANNED) {
            return "- : -";
        } else {
            return this.getScoreA() + " : " + this.getScoreB();
        }
    }
}
