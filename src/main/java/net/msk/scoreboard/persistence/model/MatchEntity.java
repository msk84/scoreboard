package net.msk.scoreboard.persistence.model;

import net.msk.scoreboard.model.GameHighlight;
import net.msk.scoreboard.model.Status;
import net.msk.scoreboard.model.Party;
import net.msk.scoreboard.service.GlobalRevisionCounter;
import net.msk.scoreboard.web.exception.GameNotFoundException;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private String partyHome;

    @Column(nullable = false)
    private String partyGuest;

    @Column(nullable = false)
    private Integer scoreHome;

    @Column(nullable = false)
    private Integer scoreGuest;

    @Column(nullable = false)
    private long revision;

    @Column(nullable = false)
    private OffsetDateTime modified;

    @OneToMany(cascade = CascadeType.ALL)
    @OrderBy("index asc")
    private List<GameEntity> games;

    public MatchEntity() {
        this.modified = OffsetDateTime.now();
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

    public String getPartyHome() {
        return partyHome;
    }

    public void setPartyHome(String partyA) {
        this.partyHome = partyA;
    }

    public String getPartyGuest() {
        return partyGuest;
    }

    public void setPartyGuest(String partyB) {
        this.partyGuest = partyB;
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

    public long getRevision() {
        return revision;
    }

    public List<GameEntity> getGames() {
        return games;
    }

    public void setGames(List<GameEntity> games) {
        this.games = games;
        this.updateMatchStatus();
    }

    public void incrementGameScore(final Long gameId, final Party party) {
        final GameEntity game = this.games.stream()
                .filter(searchGame -> searchGame.getId() == gameId)
                .findFirst()
                .orElseThrow(GameNotFoundException::new);
        game.incrementScore(party);
        this.updateMatchScore();
    }

    public void decrementGameScore(final Long gameId, final Party party) {
        final GameEntity game = this.games.stream()
                .filter(searchGame -> searchGame.getId() == gameId)
                .findFirst()
                .orElseThrow(GameNotFoundException::new);
        game.decrementScore(party);
        this.updateMatchScore();
    }

    public void addGameHighlight(final Long gameId, final Party party, final GameHighlight gameHighlight) {
        final GameEntity game = this.games.stream()
                .filter(searchGame -> searchGame.getId() == gameId)
                .findFirst()
                .orElseThrow(GameNotFoundException::new);
        game.addGameHighlight(party, gameHighlight);
        this.updateMatchScore();
    }

    private void updateMatchScore() {
        this.scoreHome = (int) this.games.stream()
                .filter(game -> Status.FINISHED == game.getStatus() && game.getScoreHome() > 2)
                .count();

        this.scoreGuest = (int) this.games.stream()
                .filter(game -> Status.FINISHED == game.getStatus() && game.getScoreGuest() > 2)
                .count();

        this.incrementRevision();
        this.updateMatchStatus();
    }

    private void updateMatchStatus() {
        if (this.games.stream().allMatch(game -> Status.FINISHED == game.getStatus())) {
            this.status = Status.FINISHED;
        }
        else if(this.games.stream().allMatch(game -> Status.PLANNED == game.getStatus())){
            this.status = Status.PLANNED;
        }
        else {
            this.status = Status.RUNNING;
        }
        this.modified = OffsetDateTime.now();
    }

    private void incrementRevision() {
        this.revision++;
        GlobalRevisionCounter.increment();
    }
}
