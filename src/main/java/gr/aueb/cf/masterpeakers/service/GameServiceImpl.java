package gr.aueb.cf.masterpeakers.service;

import gr.aueb.cf.masterpeakers.dto.GameDTO;
import gr.aueb.cf.masterpeakers.model.Game;
import gr.aueb.cf.masterpeakers.repository.GameRepository;
import gr.aueb.cf.masterpeakers.service.exceptions.GameNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements IGameService {

    private final GameRepository gameRepository;

    @Override
    public List<GameDTO> findAllGames() {
        return gameRepository.findAll().stream()
                .map(game -> new GameDTO(game.getId(), game.getTitle(), game.getPlatform(), game.getPrice(), game.getImageName()))
                .collect(Collectors.toList());
    }

    @Override
    public GameDTO findGameById(Long id) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException("Game not found"));
        return new GameDTO(game.getId(), game.getTitle(), game.getPlatform(), game.getPrice(), game.getImageName());
    }

    @Override
    public void purchaseGame(GameDTO gameDTO, String username) {
    }

    @Override
    public void addGame(GameDTO gameDTO) {
        Game game = new Game();
        game.setTitle(gameDTO.getTitle());
        game.setPlatform(gameDTO.getPlatform());
        game.setPrice(gameDTO.getPrice());
        game.setImageName(gameDTO.getImageName());
        gameRepository.save(game);
    }

    @Override
    public void updateGame(GameDTO gameDTO) {
        Game game = gameRepository.findById(gameDTO.getId()).orElseThrow(() -> new GameNotFoundException("Game not found"));
        game.setTitle(gameDTO.getTitle());
        game.setPlatform(gameDTO.getPlatform());
        game.setPrice(gameDTO.getPrice());
        game.setImageName(gameDTO.getImageName());
        gameRepository.save(game);
    }

    @Override
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    @Override
    public Game getGameEntityById(Long id) {
        return gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException("Game not found"));
    }
}
