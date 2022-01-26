package net.msk.scoreboard.persistence.model;

import net.msk.scoreboard.model.GameHighlight;
import net.msk.scoreboard.model.Status;
import net.msk.scoreboard.model.Party;
import net.msk.scoreboard.service.GlobalRevisionCounter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private Status status;

    @Column
    private Integer index;

    @Column(nullable = false)
    private String partyHome;

    @Column(nullable = false)
    private String partyGuest;

    @Column
    private Integer scoreHome;

    @Column
    private Integer scoreGuest;

    @Convert(converter = GameHighlightListConverter.class)
    private List<GameHighlight> gameHighlightsHome;

    @Convert(converter = GameHighlightListConverter.class)
    private List<GameHighlight> gameHighlightsGuest;

    @Column(nullable = false)
    private long revision;

    public GameEntity() {
        this.scoreHome = 0;
        this.scoreGuest = 0;
        this.gameHighlightsHome = new ArrayList<>();
        this.gameHighlightsGuest = new ArrayList<>();
    }

    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
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

    public List<GameHighlight> getGameHighlightsHome() {
        return gameHighlightsHome;
    }

    public void setGameHighlightsHome(List<GameHighlight> gameHighlights) {
        this.gameHighlightsHome = gameHighlights;
    }

    public List<GameHighlight> getGameHighlightsGuest() {
        return gameHighlightsGuest;
    }

    public void setGameHighlightsGuest(List<GameHighlight> gameHighlightsGuest) {
        this.gameHighlightsGuest = gameHighlightsGuest;
    }

    public void incrementScore(final Party party) {
        if (this.status != Status.FINISHED) {
            if (Party.Home == party) {
                if (this.scoreHome < 3) {
                    this.scoreHome++;
                }
            } else {
                if (this.scoreGuest < 3) {
                    this.scoreGuest++;
                }
            }
            this.updateGameStatus();
        }
    }

    public void decrementScore(final Party party) {
        if (Party.Home == party) {
            if (this.scoreHome > 0) {
                this.scoreHome--;
            }
        } else {
            if (this.scoreGuest > 0) {
                this.scoreGuest--;
            }
        }
        this.updateGameStatus();
    }

    public void addGameHighlight(final Party party, final GameHighlight gameHighlight) {
        if(Party.Home == party) {
            this.gameHighlightsHome.add(gameHighlight);
        }
        else {
            this.gameHighlightsGuest.add(gameHighlight);
        }
        this.updateGameStatus();
    }

    private void updateGameStatus() {
        if (this.scoreHome < 1 && this.scoreGuest < 1 && this.gameHighlightsHome.isEmpty() && this.gameHighlightsGuest.isEmpty()) {
            this.status = Status.PLANNED;
        } else if (this.scoreHome > 2 || scoreGuest > 2) {
            this.status = Status.FINISHED;
        } else {
            this.status = Status.RUNNING;
        }

        this.incrementRevision();
    }

    private void incrementRevision() {
        this.revision++;
        GlobalRevisionCounter.increment();
    }
}
