package home.four.you.service;

import home.four.you.model.entity.Apartment;

/**
 * Service for {@link Apartment} entity related operations.
 */
public interface ApartmentService {

    /**
     * Finds an apartment by provided ID.
     *
     * @param id Apartment id.
     * @return Apartment.
     */
    Apartment findOne(Long id);

    /**
     * Saves the provided apartment.
     *
     * @param apartment Apartment.
     * @return Updated apartment.
     */
    Apartment save(Apartment apartment);

}
