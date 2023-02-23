package home.four.you.service.impl;

import home.four.you.model.entity.Equipment;
import home.four.you.repository.EquipmentRepository;
import home.four.you.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Implementation of {@link EquipmentService}.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;

    @Override
    public Equipment findOne(Long id) {
        log.debug("Finding equipment with id {}", id);

        return equipmentRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Equipment> findAll() {
        log.debug("Finding all equipment");

        return equipmentRepository.findAll();
    }

    @Override
    public Set<Equipment> findByIds(Set<Long> ids) {
        log.debug("Finding equipment with ids [{}]", ids);

        //TODO Implement a query for this specific case.
        return ids.stream()
                .map(this::findOne)
                .collect(toSet());
    }


}
