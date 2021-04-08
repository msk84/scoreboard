package net.msk.scoreboard.service;

import net.msk.scoreboard.mapper.MatchMapper;
import net.msk.scoreboard.model.Match;
import net.msk.scoreboard.model.Party;
import net.msk.scoreboard.persistence.model.GameEntity;
import net.msk.scoreboard.persistence.model.MatchEntity;
import net.msk.scoreboard.persistence.repo.MatchRepository;
import net.msk.scoreboard.web.exception.MatchNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MatchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchService.class);

    @Autowired
    private MatchRepository matchRepository;

    public List<Match> getMatchOverview() {
        final Iterable<MatchEntity> dbMatches = this.matchRepository.findByModifiedGreaterThan(OffsetDateTime.now().minusHours(3));

        List<MatchEntity> matchEntityList = StreamSupport
                .stream(dbMatches.spliterator(), false)
                .collect(Collectors.toList());

        return MatchMapper.INSTANCE.matchEntityToMatch(matchEntityList);
    }

    public Match getMatch(final Long matchId) {
        final MatchEntity dbMatch = this.matchRepository.findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException(matchId));
        return MatchMapper.INSTANCE.matchEntityToMatch(dbMatch);
    }

    @Transactional
    public Match saveMatch(final Match match) {
        final MatchEntity matchEntity = MatchMapper.INSTANCE.matchToMatchEntity(match);

        Integer i = 0;
        for (final GameEntity game : matchEntity.getGames()) {
            game.setIndex(i);
            i++;
        }

        final MatchEntity dbMatch = this.matchRepository.save(matchEntity);
        GlobalRevisionCounter.increment();

        return MatchMapper.INSTANCE.matchEntityToMatch(dbMatch);
    }

    @Transactional
    public Match incrementGameScore(final Long matchId, final Long gameId, final Party party) {
        final MatchEntity matchEntity = this.matchRepository.findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException(matchId));
        matchEntity.incrementGameScore(gameId, party);

        return MatchMapper.INSTANCE.matchEntityToMatch(matchEntity);
    }

    @Transactional
    public Match decrementGameScore(final Long matchId, final Long gameId, final Party party) {
        final MatchEntity matchEntity = this.matchRepository.findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException(matchId));
        matchEntity.decrementGameScore(gameId, party);

        return MatchMapper.INSTANCE.matchEntityToMatch(matchEntity);
    }

    public Boolean matchHasUpdate(final Long matchId, final Long clientRevision) {
        final MatchEntity matchEntity = this.matchRepository.findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException(matchId));

        if (clientRevision < matchEntity.getRevision()) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
