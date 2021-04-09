package net.msk.scoreboard.model;

public class Game {

    private long id;

    private Status status;
    private Integer index;

    private String partyHome;
    private String partyGuest;

    private Integer scoreHome;
    private Integer scoreGuest;

    public Game() {
        this.status = Status.PLANNED;
        this.scoreHome = 0;
        this.scoreGuest = 0;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getPartyHome() {
        return partyHome;
    }

    public void setPartyHome(String partyHome) {
        this.partyHome = partyHome;
    }

    public String getPartyGuest() {
        return partyGuest;
    }

    public void setPartyGuest(String partyGuest) {
        this.partyGuest = partyGuest;
    }

    public Integer getScoreHome() {
        return this.scoreHome;
    }

    public void setScoreHome(Integer scoreHome) {
        this.scoreHome = scoreHome;
    }

    public Integer getScoreGuest() {
        return this.scoreGuest;
    }

    public void setScoreGuest(Integer scoreGuest) {
        this.scoreGuest = scoreGuest;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", status=" + status +
                ", index=" + index +
                ", partyHome='" + partyHome + '\'' +
                ", partyGuest='" + partyGuest + '\'' +
                ", scoreHome=" + scoreHome +
                ", scoreGuest=" + scoreGuest +
                '}';
    }
}
