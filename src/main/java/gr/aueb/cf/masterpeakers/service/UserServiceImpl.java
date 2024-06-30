package gr.aueb.cf.masterpeakers.service;

import gr.aueb.cf.masterpeakers.dto.RegistrationDTO;
import gr.aueb.cf.masterpeakers.model.Role;
import gr.aueb.cf.masterpeakers.model.User;
import gr.aueb.cf.masterpeakers.repository.RoleRepository;
import gr.aueb.cf.masterpeakers.repository.UserRepository;
import gr.aueb.cf.masterpeakers.service.exceptions.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " was not found"));
    }

    @Override
    public User registerUser(RegistrationDTO registrationDTO) throws UserAlreadyExistsException {
        return createUser(registrationDTO);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(RegistrationDTO registrationDTO) throws UserAlreadyExistsException {
        return createUser(registrationDTO);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private User createUser(RegistrationDTO registrationDTO) throws UserAlreadyExistsException {
        if (userRepository.findByUsername(registrationDTO.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User with username " + registrationDTO.getUsername() + " already exists");
        }

        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        user.setEmail(registrationDTO.getEmail());

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("ROLE_USER not found"));
        user.setRole(userRole);
        user.setActive(true);

        return userRepository.save(user);
    }
}

