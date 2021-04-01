package net.msk.scoreboard.persistence.model;

import net.msk.scoreboard.model.GameStatus;
import net.msk.scoreboard.model.Party;
import net.msk.scoreboard.service.GlobalRevisionCounter;

import javax.persistence.*;

@Entity
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private GameStatus status;

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

    @Column(nullable = false)
    private long revision;

    public GameEntity() {
        this.scoreHome = 0;
        this.scoreGuest = 0;
    }

    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
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

    public void incrementScore(final Party party) {
        if (this.status != GameStatus.FINISHED) {
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

    private void updateGameStatus() {
        if (this.scoreHome < 1 && this.scoreGuest < 1) {
            this.status = GameStatus.PLANNED;
        } else if (this.scoreHome > 2 || scoreGuest > 2) {
            this.status = GameStatus.FINISHED;
        } else {
            this.status = GameStatus.RUNNING;
        }

        this.incrementRevision();
    }

    private void incrementRevision() {
        this.revision++;
        GlobalRevisionCounter.increment();
    }
}
