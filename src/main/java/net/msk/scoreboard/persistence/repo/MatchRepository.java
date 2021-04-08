package net.msk.scoreboard.persistence.repo;

import net.msk.scoreboard.persistence.model.MatchEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface MatchRepository extends CrudRepository<MatchEntity, Long> {

    List<MatchEntity> findByModifiedGreaterThan(OffsetDateTime fromDate);

}