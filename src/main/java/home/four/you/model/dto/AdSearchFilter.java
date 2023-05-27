package home.four.you.model.dto;

import home.four.you.model.PropertyType;
import home.four.you.model.entity.Ad;

/**
 * Represents a search filter for Ad entities.
 *
 * @param type             The type of Ad.
 * @param minPrice         The minimum price of the Ad.
 * @param maxPrice         The maximum price of the Ad.
 * @param propertyType     The type of property.
 * @param minArea          The minimum area of the property.
 * @param maxArea          The maximum area of the property.
 * @param minNumberOfRooms The minimum number of rooms in the property.
 * @param maxNumberOfRooms The maximum number of rooms in the property.
 */
public record AdSearchFilter(Ad.Type type,
                             Integer minPrice,
                             Integer maxPrice,
                             PropertyType propertyType,
                             Integer minArea,
                             Integer maxArea,
                             Integer minNumberOfRooms,
                             Integer maxNumberOfRooms) {
}
