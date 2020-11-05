package net.msk.scoreboard.service;

import net.msk.scoreboard.model.Game;
import net.msk.scoreboard.persistence.model.GameEntity;
import net.msk.scoreboard.persistence.repo.GameRepository;
import net.msk.scoreboard.web.exception.GameNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    @Autowired
    private GameRepository gameRepository;

    public Game getGame(final Long id) {
        final GameEntity dbGame = this.gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);
        return new Game(dbGame);
    }

    public List<Game> getGameOverview() {

        final Iterable<GameEntity> dbGames = this.gameRepository.findAll();

        return StreamSupport.stream(dbGames.spliterator(), false)
                .map(g -> new Game(g))
                .collect(Collectors.toList());
    }

    public Game saveGame(final Game game) {
        final GameEntity dbGame = this.gameRepository.save(new GameEntity(game));
        return new Game(dbGame);
    }
}
