package gr.aueb.cf.masterpeakers.controller;

import gr.aueb.cf.masterpeakers.service.IEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final IEmailService emailService;

    @GetMapping("/contact")
    public String contactForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("isLoggedIn", true);
            model.addAttribute("username", userDetails.getUsername());
        } else {
            model.addAttribute("isLoggedIn", false);
        }
        return "contact";
    }

    @PostMapping("/contact/submit")
    public String submitContact(@RequestParam("email") String email, @RequestParam("message") String message, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            emailService.sendSimpleMessage("masterpeakers@gmail.com", "Contact Form Submission", "From: " + email + "\n\n" + message);
            model.addAttribute("message", "Your message has been sent successfully.");
        } catch (Exception e) {
            model.addAttribute("error", "There was an issue sending your message. Please try again later.");
        }
        if (userDetails != null) {
            model.addAttribute("isLoggedIn", true);
            model.addAttribute("username", userDetails.getUsername());
        } else {
            model.addAttribute("isLoggedIn", false);
        }
        return "contact";
    }
}

