package home.four.you.service.impl;

import home.four.you.model.entity.Equipment;
import home.four.you.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Implementation of {@link EquipmentService}.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

   /* private final EquipmentRepository equipmentRepository;

    @Override
    public Optional<Equipment> findById(Long id) {
        log.debug("Finding equipment with id {}", id);

        return equipmentRepository.findById(id);
    }

    @Override
    public List<Equipment> findAll() {
        log.debug("Finding all equipment");

        return equipmentRepository.findAll();
    }

    @Override
    public List<Equipment> findByIds(Set<Long> ids) {
        log.debug("Finding equipment with ids [{}]", ids);

        return equipmentRepository.findByIdIn(ids);
    }*/


}
