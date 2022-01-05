package net.msk.scoreboard.web;

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
        final Match result = this.matchService.incrementGameScore(matchId, gameId, party);
        return result;
    }

    @PostMapping("/{matchId}/game/{gameId}/decrementScore/{party}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Match decrementGameScore(@PathVariable final Long matchId, @PathVariable final Long gameId, @PathVariable final Party party) {
        final Match result =  this.matchService.decrementGameScore(matchId, gameId, party);
        return result;
    }

    @GetMapping("/{matchId}/hasUpdate")
    public Boolean matchHasUpdate(@PathVariable final Long matchId, @RequestParam("clientRevision") Long clientRevision) {
        return this.matchService.matchHasUpdate(matchId, clientRevision);
    }
}
