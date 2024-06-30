package gr.aueb.cf.masterpeakers.mapper;

import gr.aueb.cf.masterpeakers.dto.RegistrationDTO;
import gr.aueb.cf.masterpeakers.model.Role;
import gr.aueb.cf.masterpeakers.model.User;

public class Mapper {

    private Mapper() {}

    public static User extractUserFromRegistrationDTO(RegistrationDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setRole(Role.ROLE_USER);
        return user;
    }
}

