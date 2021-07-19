package net.msk.scoreboard.web;

import net.msk.scoreboard.model.Game;
import net.msk.scoreboard.model.Match;
import net.msk.scoreboard.service.GlobalRevisionCounter;
import net.msk.scoreboard.service.MatchService;
import net.msk.scoreboard.web.exception.MatchNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TemplateController {

    @Value("${spring.application.name}")
    String appName;

    private final MatchService matchService;

    public TemplateController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/")
    public String homePage(final Model model) {
        model.addAttribute("appName", this.appName);
        return "index";
    }

    @GetMapping("/match/overview")
    public String matchOverview(final Model model) {
        final List<Match> matches = this.matchService.getMatchOverview();
        model.addAttribute("matches", matches);
        model.addAttribute("globalRevision", GlobalRevisionCounter.getRevision());
        return "matchOverview";
    }

    @GetMapping("/match/{matchId}/view")
    public String matchOverview(@PathVariable("matchId") final String matchIdString, final Model model) {
        final Long matchId = Long.parseLong(matchIdString);
        final Match match = this.matchService.getMatch(matchId);
        model.addAttribute("match", match);
        model.addAttribute("globalRevision", GlobalRevisionCounter.getRevision());
        return "matchView";
    }

    @GetMapping("/match/create")
    public String matchCreate(final Model model) {
        final Match match = new Match();
        //match.setGames(Stream.generate(Game::new).limit(2).collect(Collectors.toList()));

        final List<Game> gamesList = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            gamesList.add(new Game(i));
        }
        match.setGames(gamesList);
        model.addAttribute("action", "add");
        model.addAttribute("match", match);
        return "matchEdit";
    }

    @GetMapping("/match/{matchId}/edit")
    public String matchEdit(@PathVariable("matchId") final String matchId, final Model model) {
        final Long id = Long.parseLong(matchId);
        final Match match = this.matchService.getMatch(id);
        model.addAttribute("action", "edit");
        model.addAttribute("match", match);
        return "matchEdit";
    }

    @ExceptionHandler(MatchNotFoundException.class)
    public String handleException(final Model model, final MatchNotFoundException ex) {
        model.addAttribute("scoreboardError", ex.getErrorMessage());
        return "scoreboardError";
    }
}
