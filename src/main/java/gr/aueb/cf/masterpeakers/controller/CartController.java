package gr.aueb.cf.masterpeakers.controller;

import gr.aueb.cf.masterpeakers.service.ICartService;
import gr.aueb.cf.masterpeakers.service.IEmailService;
import gr.aueb.cf.masterpeakers.authentication.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final ICartService cartService;
    private final IEmailService emailService;

    @GetMapping
    public String viewCart(Model model) {
        System.out.println("Rendering cart view");
        model.addAttribute("cart", cartService.getCart());
        model.addAttribute("total", cartService.getTotal());
        System.out.println("Current cart state: " + cartService.getCart().getGames());
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam("gameId") Long gameId) {
        cartService.addGameToCart(gameId);
        System.out.println("Added game with ID: " + gameId);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam("gameId") Long gameId) {
        System.out.println("Removing game with ID: " + gameId);
        cartService.removeGameFromCart(gameId);
        System.out.println("Current cart state after removal: " + cartService.getCart().getGames());
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        String email = userDetails.getUser().getEmail();
        String username = userDetails.getUsername();
        double total = cartService.getTotal();

        cartService.clearCart();

        try {
            emailService.sendSimpleMessage(email, "Purchase Confirmation",
                    "Dear " + username + ",\n\nThank you for your purchase! The total amount is $" + total + ".\n\nBest regards,\nMasterpeakers Team");
            model.addAttribute("message", "Thank you for the purchase");
        } catch (Exception e) {
            model.addAttribute("error", "There was an issue sending the confirmation email. Please contact support.");
        }

        return "purchaseSuccess";
    }
}
