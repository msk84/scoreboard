package net.msk.scoreboard.web;

import net.msk.scoreboard.model.Game;
import net.msk.scoreboard.model.Match;
import net.msk.scoreboard.service.GameService;
import net.msk.scoreboard.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchController.class);

    @Autowired
    private MatchService matchService;

    @Autowired
    private GameService gameService;

    @GetMapping("/{id}")
    public Match findOne(@PathVariable Long id) {

        /*
        Match result = new Match();
        result.setId(1);
        result.setPartyA("PartyA");
        result.setPartyB("PartyB");

        Game game1 = new Game();
        game1.setAuthor("Abcd");
        game1.setTitle("Title");

        List<Game> games = new ArrayList<>();
        games.add(game1);

        result.setGames(games);
        */

        return this.matchService.getMatch(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Match create(@RequestBody Match match) {

        final List<Game> persistedGames = this.gameService.saveGames(match.getGames());
        match.setGames(persistedGames);

        return this.matchService.saveMatch(match);
    }
}
