package net.msk.scoreboard.web;

import net.msk.scoreboard.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimpleController {

	@Value("${spring.application.name}")
	String appName;

	@Autowired
	private MatchService matchService;

	@GetMapping("/")
	public String homePage(final Model model) {
		
		model.addAttribute("appName", this.appName);
		return "index";
	}

	@GetMapping("/matches")
	public String matchOverview(final Model model) {

		model.addAttribute("matches", this.matchService.getMatchOverview());
		return "matches";
	}
}
