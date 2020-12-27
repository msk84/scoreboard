package net.msk.scoreboard.persistence.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String partyA;

    @Column(nullable = false)
    private String partyB;

    @OneToMany(cascade = CascadeType.PERSIST)
    @OrderBy("index asc")
    private Set<GameEntity> games;

    public MatchEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Set<GameEntity> getGames() {
        return games;
    }

    public void setGames(Set<GameEntity> games) {
        this.games = games;
    }
}
