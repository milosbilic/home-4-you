package home.four.you.model.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO used for creating a house.
 *
 * @param numberOfFloors Number of floors in the house.
 * @param courtyardArea  Courtyard area.
 */
public record CreateHouseDto(@NotBlank Integer numberOfFloors,
                             @NotBlank Integer courtyardArea) {
}
