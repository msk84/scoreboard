package net.msk.scoreboard.persistence.model;

import net.msk.scoreboard.model.Party;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameEntityTest {

    @Test
    void incrementScore() {
        final GameEntity game = new GameEntity();
        assertEquals(0, game.getScoreHome());
        assertEquals(0, game.getScoreGuest());

        game.incrementScore(Party.Home);
        assertEquals(1, game.getScoreHome());
        assertEquals(0, game.getScoreGuest());

        game.incrementScore(Party.Guest);
        assertEquals(1, game.getScoreHome());
        assertEquals(1, game.getScoreGuest());

        game.incrementScore(Party.Home);
        game.incrementScore(Party.Home);
        assertEquals(3, game.getScoreHome());
        assertEquals(1, game.getScoreGuest());

        game.incrementScore(Party.Home);
        assertEquals(3, game.getScoreHome());
        assertEquals(1, game.getScoreGuest());

        game.incrementScore(Party.Guest);
        assertEquals(3, game.getScoreHome());
        assertEquals(1, game.getScoreGuest());
    }

    @Test
    void decrementScore() {
        final GameEntity game = new GameEntity();
        assertEquals(0, game.getScoreHome());
        assertEquals(0, game.getScoreGuest());

        game.decrementScore(Party.Home);
        assertEquals(0, game.getScoreHome());
        assertEquals(0, game.getScoreGuest());

        game.incrementScore(Party.Home);
        assertEquals(1, game.getScoreHome());
        assertEquals(0, game.getScoreGuest());

        game.decrementScore(Party.Home);
        assertEquals(0, game.getScoreHome());
        assertEquals(0, game.getScoreGuest());

        game.incrementScore(Party.Home);
        game.incrementScore(Party.Home);
        game.incrementScore(Party.Guest);
        game.incrementScore(Party.Guest);
        game.incrementScore(Party.Home);
        assertEquals(3, game.getScoreHome());
        assertEquals(2, game.getScoreGuest());

        game.decrementScore(Party.Guest);
        assertEquals(3, game.getScoreHome());
        assertEquals(1, game.getScoreGuest());
    }
}