package net.msk.scoreboard.model;

import net.msk.scoreboard.persistence.model.GameEntity;

public class Game {

    private long id;

    private String title;

    private String author;

    public Game() {
    }

    public Game(final GameEntity gameEntity) {
        this.id = gameEntity.getId();
        this.title = gameEntity.getTitle();
        this.author = gameEntity.getAuthor();
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
