package home.four.you.service;

import home.four.you.model.entity.Equipment;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service for {@link Equipment} entity related operations.
 */
public interface EquipmentService {

/*    *//**
     * Finds equipment by provided ID.
     *
     * @param id Equipment ID.
     * @return Matching equipment, or empty {@link Optional}.
     *//*
    Optional<Equipment> findById(Long id);

    *//**
     * Finds all equipment.
     *
     * @return List of all equipment.
     *//*
    List<Equipment> findAll();

    *//**
     * Finds equipment by provided IDs.
     *
     * @param ids Equipment IDs.
     * @return List of matching equipment.
     *//*
    List<Equipment> findByIds(Set<Long> ids);*/
}
