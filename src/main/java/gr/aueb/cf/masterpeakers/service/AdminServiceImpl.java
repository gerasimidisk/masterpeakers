package gr.aueb.cf.masterpeakers.service;

import gr.aueb.cf.masterpeakers.dto.GameDTO;
import gr.aueb.cf.masterpeakers.dto.RegistrationDTO;
import gr.aueb.cf.masterpeakers.model.Game;
import gr.aueb.cf.masterpeakers.model.Role;
import gr.aueb.cf.masterpeakers.model.User;
import gr.aueb.cf.masterpeakers.repository.GameRepository;
import gr.aueb.cf.masterpeakers.repository.RoleRepository;
import gr.aueb.cf.masterpeakers.repository.UserRepository;
import gr.aueb.cf.masterpeakers.service.exceptions.GameNotFoundException;
import gr.aueb.cf.masterpeakers.service.exceptions.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements IAdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final GameRepository gameRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(RegistrationDTO registrationDTO) throws UserAlreadyExistsException {
        if (userRepository.findByUsername(registrationDTO.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User with username " + registrationDTO.getUsername() + " already exists");
        }

        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        user.setEmail(registrationDTO.getEmail());

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("Role not found"));
        user.setRole(userRole);
        user.setActive(true);

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<GameDTO> getAllGames() {
        return gameRepository.findAll().stream()
                .map(game -> new GameDTO(game.getId(), game.getTitle(), game.getPlatform(), game.getPrice(), game.getImageName()))
                .collect(Collectors.toList());
    }

    @Override
    public Game addGame(GameDTO gameDTO) {
        Game game = new Game();
        game.setTitle(gameDTO.getTitle());
        game.setPlatform(gameDTO.getPlatform());
        game.setPrice(gameDTO.getPrice());
        game.setImageName(gameDTO.getImageName());
        return gameRepository.save(game);
    }

    @Override
    public Game updateGame(GameDTO gameDTO) throws GameNotFoundException {
        Game game = gameRepository.findById(gameDTO.getId())
                .orElseThrow(() -> new GameNotFoundException("Game not found with id: " + gameDTO.getId()));

        game.setTitle(gameDTO.getTitle());
        game.setPlatform(gameDTO.getPlatform());
        game.setPrice(gameDTO.getPrice());
        game.setImageName(gameDTO.getImageName());
        return gameRepository.save(game);
    }

    @Override
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
}
