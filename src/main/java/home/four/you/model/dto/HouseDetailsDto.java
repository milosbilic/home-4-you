package home.four.you.model.dto;

/**
 * DTO representation of house details.
 *
 * @param id             House ID.
 * @param numberOfFloors Number of floors the house has.
 * @param courtyardArea  House's courtyard area.
 */
public record HouseDetailsDto(Long id, Integer numberOfFloors, Integer courtyardArea) {

}
