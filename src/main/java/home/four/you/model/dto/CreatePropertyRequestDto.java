package home.four.you.model.dto;

import home.four.you.model.entity.Property;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * DTO representation of property creation request.
 *
 * @param heatType      Heat type.
 * @param locationId    Location ID.
 * @param area          Area.
 * @param numberOfRooms Number of rooms.
 * @param booked        Property booking.
 * @param equipmentIds  Set of equipment IDs.
 * @param house         House creation request DTO.
 * @param apartment     Apartment creation request DTO.
 */
public record CreatePropertyRequestDto(@NotNull(message = "Provide heat type") Property.HeatType heatType,
                                       @NotNull(message = "Provide location") Long locationId,
                                       @NotNull Integer area,
                                       @NotNull Double numberOfRooms,
                                       @NotNull Boolean booked,
                                       Set<Long> equipmentIds,
                                       CreateHouseDto house,
                                       CreateApartmentDto apartment) {
}
