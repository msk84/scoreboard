package net.msk.scoreboard.persistence.model;

import net.msk.scoreboard.model.GameStatus;
import net.msk.scoreboard.model.Party;

import javax.persistence.*;

@Entity
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String status;

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

    public GameEntity() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
        if (!this.status.equals(GameStatus.FINISHED.name())) {
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
            this.status = GameStatus.PLANNED.name();
        } else if (this.scoreHome > 2 || scoreGuest > 2) {
            this.status = GameStatus.FINISHED.name();
        } else {
            this.status = GameStatus.RUNNING.name();
        }
    }
}
