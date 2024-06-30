package gr.aueb.cf.masterpeakers.service;

import gr.aueb.cf.masterpeakers.dto.PurchaseDTO;
import gr.aueb.cf.masterpeakers.model.Game;
import gr.aueb.cf.masterpeakers.model.Purchase;
import gr.aueb.cf.masterpeakers.model.User;
import gr.aueb.cf.masterpeakers.repository.PurchaseRepository;
import gr.aueb.cf.masterpeakers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements IPurchaseService {

    @Autowired
    private IGameService gameService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public void createPurchase(PurchaseDTO purchaseDTO, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Game game = gameService.getGameEntityById(purchaseDTO.getGameId());

        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setGame(game);
        purchase.setPrice(purchaseDTO.getPrice());

        purchaseRepository.save(purchase);
    }
}
