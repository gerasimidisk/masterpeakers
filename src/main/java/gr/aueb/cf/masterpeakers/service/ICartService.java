package gr.aueb.cf.masterpeakers.service;

import gr.aueb.cf.masterpeakers.model.Cart;

public interface ICartService {
    Cart getCart();
    void addGameToCart(Long gameId);
    void removeGameFromCart(Long gameId);
    void clearCart();
    double getTotal();
}
