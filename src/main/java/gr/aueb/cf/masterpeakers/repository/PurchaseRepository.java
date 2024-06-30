package gr.aueb.cf.masterpeakers.repository;

import gr.aueb.cf.masterpeakers.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
