package net.msk.scoreboard.persistence.model;

import net.msk.scoreboard.model.GameStatus;
import net.msk.scoreboard.model.Party;
import net.msk.scoreboard.web.exception.GameNotFoundException;

import javax.persistence.*;
import java.util.List;

@Entity
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String partyHome;

    @Column(nullable = false)
    private String partyGuest;

    @Column(nullable = false)
    private Integer scoreHome;

    @Column(nullable = false)
    private Integer scoreGuest;

    @OneToMany(cascade = CascadeType.ALL)
    @OrderBy("index asc")
    private List<GameEntity> games;

    public MatchEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<GameEntity> getGames() {
        return games;
    }

    public void setGames(List<GameEntity> games) {
        this.games = games;
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

    private void updateMatchScore() {
        this.scoreHome = (int)this.games.stream()
                .filter(game -> GameStatus.FINISHED.name().equals(game.getStatus()) && game.getScoreHome() > 2)
                .count();

        this.scoreGuest = (int)this.games.stream()
                .filter(game -> GameStatus.FINISHED.name().equals(game.getStatus()) && game.getScoreGuest() > 2)
                .count();
    }
}
