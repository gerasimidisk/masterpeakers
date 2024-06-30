package gr.aueb.cf.masterpeakers.repository;

import gr.aueb.cf.masterpeakers.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
