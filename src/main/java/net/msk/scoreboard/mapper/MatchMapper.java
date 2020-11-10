package net.msk.scoreboard.mapper;

import net.msk.scoreboard.model.Match;
import net.msk.scoreboard.persistence.model.MatchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MatchMapper {

    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    MatchEntity matchToMatchEntity(Match match);
    Match matchEntityToMatch(MatchEntity matchEntity);
    List<Match> matchEntityToMatch(List<MatchEntity> matchEntities);
}
