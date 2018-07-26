package advertising.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import advertising.model.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

}
