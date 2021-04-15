package net.msk.scoreboard.model;

import java.util.List;

public class Match {

    private long id;

    private Status status;

    private String partyHome;
    private String partyGuest;

    private Integer scoreHome;
    private Integer scoreGuest;

    private List<Game> games;

    public Match() {
        this.status = Status.PLANNED;
        this.scoreHome = 0;
        this.scoreGuest = 0;
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
        return scoreHome;
    }

    public void setScoreHome(Integer scoreHome) {
        this.scoreHome = scoreHome;
    }

    public Integer getScoreGuest() {
        return scoreGuest;
    }

    public void setScoreGuest(Integer scoreGuest) {
        this.scoreGuest = scoreGuest;
    }

    public String getScoreDisplayString() {
        return this.scoreHome + " : " + this.scoreGuest;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", status=" + status +
                ", partyHome='" + partyHome + '\'' +
                ", partyGuest='" + partyGuest + '\'' +
                ", scoreHome=" + scoreHome +
                ", scoreGuest=" + scoreGuest +
                ", games=" + games +
                '}';
    }
}
