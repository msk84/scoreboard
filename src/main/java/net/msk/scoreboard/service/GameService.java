package net.msk.scoreboard.service;

import net.msk.scoreboard.mapper.GameMapper;
import net.msk.scoreboard.model.Game;
import net.msk.scoreboard.model.GameScore;
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
        return GameMapper.INSTANCE.gameEntityToGame(dbGame);
    }

    public List<Game> getGameOverview() {
        final Iterable<GameEntity> dbGames = this.gameRepository.findAll();

        final List<GameEntity> gameEntityList = StreamSupport
                .stream(dbGames.spliterator(), false)
                .collect(Collectors.toList());

        return GameMapper.INSTANCE.gameEntityToGame(gameEntityList);
    }

    public List<Game> saveGames(final List<Game> games) {
        final List<GameEntity> gameEntities = GameMapper.INSTANCE.gameToGameEntity(games);
        final Iterable<GameEntity> dbGames = this.gameRepository.saveAll(gameEntities);
        final List<GameEntity> gameEntityList = StreamSupport
                .stream(dbGames.spliterator(), false)
                .collect(Collectors.toList());
        return GameMapper.INSTANCE.gameEntityToGame(gameEntityList);
    }

    public Game saveGame(final Game game) {
        final GameEntity gameEntity = GameMapper.INSTANCE.gameToGameEntity(game);
        final GameEntity dbGame = this.gameRepository.save(gameEntity);
        return GameMapper.INSTANCE.gameEntityToGame(dbGame);
    }

    public Game updateScore(final Long gameId, final GameScore score) {
        final GameEntity dbGame = this.gameRepository.findById(gameId)
                .orElseThrow(GameNotFoundException::new);

        final Game game = GameMapper.INSTANCE.gameEntityToGame(dbGame);
        game.setScore(score);

        this.gameRepository.save(GameMapper.INSTANCE.gameToGameEntity(game));

        return game;
    }
}
