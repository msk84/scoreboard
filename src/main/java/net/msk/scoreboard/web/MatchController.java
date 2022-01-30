package net.msk.scoreboard.web;

import net.msk.dartscoreValidation.ScoreValidator;
import net.msk.scoreboard.model.GameHighlight;
import net.msk.scoreboard.model.Match;
import net.msk.scoreboard.model.Party;
import net.msk.scoreboard.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchController.class);

    private final MatchService matchService;

    public MatchController(final MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/add")
    public ModelAndView create(@ModelAttribute Match match, BindingResult errors, Model model) {
        LOGGER.debug(match.toString());
        this.matchService.saveMatch(match);

        return new ModelAndView("redirect:/match/overview");
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@ModelAttribute Match match, @PathVariable Long id, BindingResult errors, Model model) {
        LOGGER.debug(match.toString());
        this.matchService.saveMatch(match);

        return new ModelAndView("redirect:/match/overview");
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable Long id) {
        return this.matchService.deleteMatch(id);
    }

    @PostMapping("/{matchId}/game/{gameId}/incrementScore/{party}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Match incrementGameScore(@PathVariable final Long matchId, @PathVariable final Long gameId, @PathVariable final Party party) {
        return this.matchService.incrementGameScore(matchId, gameId, party);
    }

    @PostMapping("/{matchId}/game/{gameId}/decrementScore/{party}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Match decrementGameScore(@PathVariable final Long matchId, @PathVariable final Long gameId, @PathVariable final Party party) {
        return this.matchService.decrementGameScore(matchId, gameId, party);
    }

    @PostMapping(value = {"/{matchId}/game/{gameId}/addHighlight/{party}/{highlight}", "/{matchId}/game/{gameId}/addHighlight/{party}/{highlight}/{highlightValue}"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Match addGameHighlight(@PathVariable final Long matchId, @PathVariable final Long gameId, @PathVariable final Party party,
                                                 @PathVariable final String highlight, @PathVariable(required = false) final Integer highlightValue) throws Exception {

        final GameHighlight.Type gameHighlightType = GameHighlight.Type.valueOf(highlight);

        if ((gameHighlightType == GameHighlight.Type.OneEighty) || (highlightValue != null &&
                (gameHighlightType == GameHighlight.Type.HighFinish && ScoreValidator.isValidHighfinish(highlightValue)) ||
                (gameHighlightType == GameHighlight.Type.ShortGame && ScoreValidator.isValidShortgame(highlightValue)))) {

            final GameHighlight gameHighlight = new GameHighlight(gameHighlightType, highlightValue);
            return this.matchService.addGameHighlight(matchId, gameId, party, gameHighlight);
        }

        LOGGER.error("Rejected invalid game highlight. :: HighlightType: {}; HighlightValue: {}", highlight, highlightValue);
        throw new Exception("Rejected invalid game highlight.");
    }

    @GetMapping("/{matchId}/hasUpdate")
    public Boolean matchHasUpdate(@PathVariable final Long matchId, @RequestParam("clientRevision") Long clientRevision) {
        return this.matchService.matchHasUpdate(matchId, clientRevision);
    }

}
