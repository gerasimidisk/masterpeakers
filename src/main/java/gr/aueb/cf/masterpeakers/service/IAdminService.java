package gr.aueb.cf.masterpeakers.service;

import gr.aueb.cf.masterpeakers.dto.GameDTO;
import gr.aueb.cf.masterpeakers.dto.RegistrationDTO;
import gr.aueb.cf.masterpeakers.model.Game;
import gr.aueb.cf.masterpeakers.model.User;
import gr.aueb.cf.masterpeakers.service.exceptions.GameNotFoundException;
import gr.aueb.cf.masterpeakers.service.exceptions.UserAlreadyExistsException;

import java.util.List;

public interface IAdminService {
    List<User> getAllUsers();
    User addUser(RegistrationDTO registrationDTO) throws UserAlreadyExistsException;
    void deleteUser(Long id);
    List<GameDTO> getAllGames();
    Game addGame(GameDTO gameDTO);
    Game updateGame(GameDTO gameDTO) throws GameNotFoundException;
    void deleteGame(Long id);
}
