package net.msk.scoreboard.mapper;

import net.msk.scoreboard.model.Game;
import net.msk.scoreboard.persistence.model.GameEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GameMapper {

    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

    GameEntity gameToGameEntity(Game game);
    List<GameEntity> gameToGameEntity(List<Game> games);

    Game gameEntityToGame(GameEntity gameEntity);
    List<Game> gameEntityToGame(List<GameEntity> gameEntities);

}
