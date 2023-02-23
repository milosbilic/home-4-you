package home.four.you.model.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO used for creating an apartment.
 *
 * @param floor Floor on which apartment is.
 */
public record CreateApartmentDto(@NotBlank Integer floor) {
}
