package net.msk.scoreboard.service;

import net.msk.scoreboard.mapper.GameMapper;
import net.msk.scoreboard.model.Game;
import net.msk.scoreboard.persistence.model.GameEntity;
import net.msk.scoreboard.persistence.repo.GameRepository;
import net.msk.scoreboard.web.exception.GameNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Game saveGame(final Game game) {
        final GameEntity gameEntity = GameMapper.INSTANCE.gameToGameEntity(game);
        final GameEntity dbGame = this.gameRepository.save(gameEntity);
        GlobalRevisionCounter.increment();

        return GameMapper.INSTANCE.gameEntityToGame(dbGame);
    }

}
