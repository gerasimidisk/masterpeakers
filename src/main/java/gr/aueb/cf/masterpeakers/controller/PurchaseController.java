package gr.aueb.cf.masterpeakers.controller;

import gr.aueb.cf.masterpeakers.dto.PurchaseDTO;
import gr.aueb.cf.masterpeakers.service.IPurchaseService;
import gr.aueb.cf.masterpeakers.service.IGameService;
import gr.aueb.cf.masterpeakers.authentication.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private IPurchaseService purchaseService;

    @Autowired
    private IGameService gameService;

    @PostMapping
    public String makePurchase(@RequestParam("gameId") Long gameId, @RequestParam("quantity") Integer quantity, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication.getPrincipal() instanceof String && authentication.getPrincipal().equals("anonymousUser"))) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            System.out.println("User is authenticated: " + userDetails.getUsername());
            PurchaseDTO purchaseDTO = new PurchaseDTO();
            purchaseDTO.setGameId(gameId);
            purchaseDTO.setQuantity(quantity);
            purchaseDTO.setPrice(gameService.getGameEntityById(gameId).getPrice() * quantity); // Calculate the total price based on quantity
            purchaseService.createPurchase(purchaseDTO, userDetails.getUsername());
            model.addAttribute("message", "Thank you for purchasing");
            return "purchaseSuccess";
        }
        System.out.println("User is not authenticated, redirecting to login.");
        return "redirect:/login";
    }
}

