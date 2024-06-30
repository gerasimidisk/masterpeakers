package gr.aueb.cf.masterpeakers.service;

import gr.aueb.cf.masterpeakers.dto.RegistrationDTO;
import gr.aueb.cf.masterpeakers.model.User;
import gr.aueb.cf.masterpeakers.service.exceptions.UserAlreadyExistsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IUserService {
    User getUserByUsername(String username) throws UsernameNotFoundException;
    User registerUser(RegistrationDTO registrationDTO) throws UserAlreadyExistsException;
    List<User> getAllUsers();
    User addUser(RegistrationDTO registrationDTO) throws UserAlreadyExistsException;
    void deleteUser(Long id);
}
