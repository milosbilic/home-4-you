package home.four.you.model.dto;

import home.four.you.model.entity.Property;

import java.util.Set;

/**
 * DTO representation of a property.
 *
 * @param id            Property ID.
 * @param location      Location.
 * @param area          Area.
 * @param numberOfRooms Number of rooms.
 * @param heatType      Heat type.
 * @param booked        Property booking.
 * @param equipment     Equipment.
 * @param house         House.
 * @param apartment     Apartment.
 */
public record PropertyDetailsDto(Long id,
                                 LocationDetailsDto location,
                                 Double area,
                                 Double numberOfRooms,
                                 Property.HeatType heatType,
                                 Boolean booked,
                                 Set<EquipmentDto> equipment,
                                 HouseDetailsDto house,
                                 ApartmentDetailsDto apartment) {
}
