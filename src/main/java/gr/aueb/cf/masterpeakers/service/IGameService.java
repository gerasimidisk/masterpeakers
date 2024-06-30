package gr.aueb.cf.masterpeakers.service;

import gr.aueb.cf.masterpeakers.dto.GameDTO;
import gr.aueb.cf.masterpeakers.model.Game;
import java.util.List;

public interface IGameService {
    List<GameDTO> findAllGames();
    GameDTO findGameById(Long id);
    void purchaseGame(GameDTO gameDTO, String username);
    void addGame(GameDTO gameDTO);
    void updateGame(GameDTO gameDTO);
    void deleteGame(Long id);
    Game getGameEntityById(Long id);
}
