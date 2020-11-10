package net.msk.scoreboard.persistence.repo;

import net.msk.scoreboard.persistence.model.GameEntity;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<GameEntity, Long> {
}