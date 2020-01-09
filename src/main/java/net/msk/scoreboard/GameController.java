package net.msk.scoreboard;

import java.util.List;

import net.msk.scoreboard.persistence.model.GameEntity;
import net.msk.scoreboard.persistence.repo.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/games")
public class GameController {
	
	@Autowired
	private GameRepository gameRepository;
	
	@GetMapping
	public Iterable findAll() {
		return gameRepository.findAll();
	}
	
	@GetMapping("/title/{gameTitle}")
	public List<GameEntity> findByTitle(@PathVariable String gameTitle) {
		return gameRepository.findByTitle(gameTitle);
	}
	
	@GetMapping("/{id}")
	public GameEntity findOne(@PathVariable Long id) {
		return gameRepository.findById(id)
				.orElseThrow(GameNotFoundException::new);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GameEntity create(@RequestBody GameEntity game) {
		return gameRepository.save(game);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		gameRepository.findById(id)
				.orElseThrow(GameNotFoundException::new);
		gameRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public GameEntity updateBook(@RequestBody GameEntity game, @PathVariable Long id) {
		if (game.getId() != id) {
			throw new GameIdMismatchException();
		}
		gameRepository.findById(id)
				.orElseThrow(GameNotFoundException::new);
		return gameRepository.save(game);
	}
}