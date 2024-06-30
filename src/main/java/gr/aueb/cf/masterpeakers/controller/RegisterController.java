package gr.aueb.cf.masterpeakers.controller;

import gr.aueb.cf.masterpeakers.dto.RegistrationDTO;
import gr.aueb.cf.masterpeakers.service.IUserService;
import gr.aueb.cf.masterpeakers.service.exceptions.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final IUserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationDTO", new RegistrationDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("registrationDTO") RegistrationDTO registrationDTO,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            userService.registerUser(registrationDTO);
        } catch (UserAlreadyExistsException e) {
            bindingResult.rejectValue("username", "error.registrationDTO", e.getMessage());
            return "register";
        }
        return "redirect:/login";
    }
}
