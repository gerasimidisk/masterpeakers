package gr.aueb.cf.masterpeakers.controller;

import gr.aueb.cf.masterpeakers.dto.GameDTO;
import gr.aueb.cf.masterpeakers.dto.RegistrationDTO;
import gr.aueb.cf.masterpeakers.model.User;
import gr.aueb.cf.masterpeakers.service.IAdminService;
import gr.aueb.cf.masterpeakers.service.exceptions.GameNotFoundException;
import gr.aueb.cf.masterpeakers.service.exceptions.UserAlreadyExistsException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final IAdminService adminService;

    @GetMapping
    public String adminDashboard() {
        return "admin/admin";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = adminService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("registrationDTO", new RegistrationDTO());
        return "admin/users";
    }

    @PostMapping("/users")
    public String addUser(@ModelAttribute @Valid RegistrationDTO registrationDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<User> users = adminService.getAllUsers();
            model.addAttribute("users", users);
            return "admin/users";
        }
        try {
            adminService.addUser(registrationDTO);
        } catch (UserAlreadyExistsException e) {
            bindingResult.rejectValue("username", "error.registrationDTO", e.getMessage());
            List<User> users = adminService.getAllUsers();
            model.addAttribute("users", users);
            return "admin/users";
        }
        return "redirect:/admin/users";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/games")
    public String getAllGames(Model model) {
        List<GameDTO> games = adminService.getAllGames();
        model.addAttribute("games", games);
        model.addAttribute("gameDTO", new GameDTO());
        return "admin/games";
    }

    @PostMapping("/games")
    public String addGame(@ModelAttribute @Valid GameDTO gameDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<GameDTO> games = adminService.getAllGames();
            model.addAttribute("games", games);
            return "admin/games";
        }
        adminService.addGame(gameDTO);
        return "redirect:/admin/games";
    }

    @PostMapping("/games/update")
    public String updateGame(@ModelAttribute @Valid GameDTO gameDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<GameDTO> games = adminService.getAllGames();
            model.addAttribute("games", games);
            return "admin/games";
        }
        try {
            adminService.updateGame(gameDTO);
        } catch (GameNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            List<GameDTO> games = adminService.getAllGames();
            model.addAttribute("games", games);
            return "admin/games";
        }
        return "redirect:/admin/games";
    }

    @PostMapping("/games/delete/{id}")
    public String deleteGame(@PathVariable Long id) {
        adminService.deleteGame(id);
        return "redirect:/admin/games";
    }
}
