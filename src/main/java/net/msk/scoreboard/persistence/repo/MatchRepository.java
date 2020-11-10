package net.msk.scoreboard.persistence.repo;

import net.msk.scoreboard.persistence.model.MatchEntity;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<MatchEntity, Long> {
}