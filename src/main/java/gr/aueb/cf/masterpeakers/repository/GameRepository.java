package gr.aueb.cf.masterpeakers.repository;

import gr.aueb.cf.masterpeakers.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
