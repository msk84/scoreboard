package net.msk.scoreboard.web;

import net.msk.scoreboard.model.Match;
import net.msk.scoreboard.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
}
