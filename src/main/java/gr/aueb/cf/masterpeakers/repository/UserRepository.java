package gr.aueb.cf.masterpeakers.repository;

import gr.aueb.cf.masterpeakers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
