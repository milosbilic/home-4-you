package home.four.you.service;

import home.four.you.model.entity.House;

/**
 * Service for {@link House} entity related operations.
 */
public interface HouseService {

    /**
     * Finds a house by provided ID.
     *
     * @param id House ID.
     * @return House.
     */
    House findOne(Long id);

    /**
     * Saves a house.
     *
     * @param house House.
     * @return House.
     */
    House save(House house);

}
