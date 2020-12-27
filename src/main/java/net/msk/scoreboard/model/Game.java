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
        this.status = GameStatus.PLANNED;
        this.index = index;
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

    public void setScore(final GameScore score) {
        if (score.getScoreA() != null && score.getScoreB() != null && score.getScoreA() >= 0 && score.getScoreB() >= 0 && (score.getScoreA() + score.getScoreB()) < 6) {
            this.scoreA = score.getScoreA();
            this.scoreB = score.getScoreB();
        }
    }

    public Integer getScoreA() {
        return this.scoreA;
    }

    public Integer getScoreB() {
        return this.scoreB;
    }

    public String getScoreString() {
        if (this.status == GameStatus.PLANNED) {
            return "- : -";
        } else {
            return this.scoreA.toString() + " : " + this.scoreB.toString();
        }
    }
}
