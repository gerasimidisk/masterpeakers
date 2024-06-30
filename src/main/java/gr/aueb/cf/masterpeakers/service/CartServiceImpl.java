package gr.aueb.cf.masterpeakers.service;

import gr.aueb.cf.masterpeakers.model.Cart;
import gr.aueb.cf.masterpeakers.model.Game;
import gr.aueb.cf.masterpeakers.repository.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import lombok.RequiredArgsConstructor;

@Service
@SessionScope
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {
    private final Cart cart = new Cart();
    private final GameRepository gameRepository;

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public void addGameToCart(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));
        cart.addGame(game);
        System.out.println("Game added: " + game);
        System.out.println("Current cart state after addition: " + cart.getGames());
    }

    @Override
    public void removeGameFromCart(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));
        System.out.println("Game found: " + game);
        cart.removeGame(game);
        System.out.println("Game removed from cart");
        System.out.println("Current cart state after removal: " + cart.getGames());
    }

    @Override
    public void clearCart() {
        cart.clear();
        System.out.println("Cart cleared");
    }

    @Override
    public double getTotal() {
        return cart.getTotal();
    }
}
