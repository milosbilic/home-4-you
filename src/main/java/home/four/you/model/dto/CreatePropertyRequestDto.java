package home.four.you.model.dto;

import home.four.you.model.entity.Equipment;
import home.four.you.model.entity.Property;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

/**
 * DTO representation of property creation request.
 *
 * @param heatType      Heat type.
 * @param locationId    Location ID.
 * @param area          Area.
 * @param numberOfRooms Number of rooms.
 * @param booked        Property booking.
 * @param equipment     Property equipment.
 * @param house         House creation request DTO.
 * @param apartment     Apartment creation request DTO.
 */
public record CreatePropertyRequestDto(@NotNull Property.HeatType heatType,
                                       @NotNull Long locationId,
                                       @NotNull Integer area,
                                       @NotNull Double numberOfRooms,
                                       @NotNull Boolean booked,
                                       Set<Equipment> equipment,
                                       CreateHouseDto house,
                                       CreateApartmentDto apartment) {
}
