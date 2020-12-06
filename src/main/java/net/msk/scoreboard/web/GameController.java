package net.msk.scoreboard.web;

import net.msk.scoreboard.model.Game;
import net.msk.scoreboard.model.GameScore;
import net.msk.scoreboard.service.GameService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private GameService gameService;

    @GetMapping("/{id}")
    public Game findOne(@PathVariable Long id) {
        LOGGER.info("Try loading game with id: {}", id);
        return this.gameService.getGame(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Game create(@RequestBody Game game) {
        return this.gameService.saveGame(game);
    }

    @PostMapping("/{gameId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Game updateScore(@PathVariable final Long gameId, @RequestBody final GameScore score) {
        return this.gameService.updateScore(gameId, score);
    }
}