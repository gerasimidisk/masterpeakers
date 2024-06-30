package gr.aueb.cf.masterpeakers.controller;

import gr.aueb.cf.masterpeakers.service.IGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final IGameService gameService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("games", gameService.findAllGames());
        return "home";
    }
}
