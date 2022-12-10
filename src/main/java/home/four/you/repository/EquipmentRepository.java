package home.four.you.repository;

import home.four.you.model.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for {@link Equipment} entity.
 */
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

}
