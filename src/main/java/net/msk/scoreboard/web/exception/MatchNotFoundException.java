package net.msk.scoreboard.web.exception;

public class MatchNotFoundException extends RuntimeException {

    private final Long matchId;

    public MatchNotFoundException(final Long matchId) {
        super();
        this.matchId = matchId;
    }

    public Long getMatchId() {
        return this.matchId;
    }

    public String getErrorMessage() {
        return String.format("Match with id '%s' couldn't be found.", this.matchId);
    }
}
