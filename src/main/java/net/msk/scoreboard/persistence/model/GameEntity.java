package net.msk.scoreboard.persistence.model;

import net.msk.scoreboard.model.Game;

import javax.persistence.*;

@Entity
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String author;

    public GameEntity() {
    }

    public GameEntity(final Game game) {
        this.id = game.getId();
        this.title = game.getTitle();
        this.author = game.getAuthor();
    }

    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }
}
