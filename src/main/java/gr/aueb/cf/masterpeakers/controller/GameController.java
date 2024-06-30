package gr.aueb.cf.masterpeakers.controller;

import gr.aueb.cf.masterpeakers.dto.GameDTO;
import gr.aueb.cf.masterpeakers.service.IGameService;
import gr.aueb.cf.masterpeakers.authentication.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/games")
public class GameController {

    private final IGameService gameService;
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    @GetMapping
    public String listGames(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        } else {
            model.addAttribute("username", "Guest");
        }
        model.addAttribute("games", gameService.findAllGames());
        return "games";
    }

    @GetMapping("/{id}")
    public String getGame(@PathVariable Long id, Model model) {
        model.addAttribute("game", gameService.findGameById(id));
        return "game";
    }

    @PostMapping("/purchase")
    public String purchaseGame(@RequestParam("gameId") Long gameId, @AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        logger.debug("User is authenticated: " + userDetails.getUsername());
        gameService.purchaseGame(new GameDTO(gameId), userDetails.getUsername());
        return "redirect:/cart";
    }
}


