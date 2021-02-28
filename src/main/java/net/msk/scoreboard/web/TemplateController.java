package net.msk.scoreboard.web;

import net.msk.scoreboard.model.Game;
import net.msk.scoreboard.model.Match;
import net.msk.scoreboard.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TemplateController {

    @Value("${spring.application.name}")
    String appName;

    @Autowired
    private MatchService matchService;

    @GetMapping("/")
    public String homePage(final Model model) {
        model.addAttribute("appName", this.appName);
        return "index";
    }

    @GetMapping("/match/overview")
    public String matchOverview(final Model model) {
        final List<Match> matches = this.matchService.getMatchOverview();
        model.addAttribute("matches", matches);
        return "matchOverview";
    }

    @GetMapping("/match/create")
    public String matchCreate(final Model model) {
        final Match match = new Match();
        //match.setGames(Stream.generate(Game::new).limit(2).collect(Collectors.toList()));

        final List<Game> gamesList = new ArrayList<>(2);
        for (int i = 0; i < 2; i++) {
            gamesList.add(new Game(i));
        }
        match.setGames(gamesList);
        model.addAttribute("action", "add");
        model.addAttribute("match", match);
        return "matchEdit";
    }

    @GetMapping("/match/{id}/edit")
    public String matchEdit(@PathVariable("id") final String matchId, final Model model) {
        final Long id = Long.parseLong(matchId);
        final Match match = this.matchService.getMatch(id);
        model.addAttribute("action", "edit");
        model.addAttribute("match", match);
        return "matchEdit";
    }
}
