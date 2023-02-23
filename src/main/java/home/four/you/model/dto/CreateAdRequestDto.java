package home.four.you.model.dto;

import home.four.you.model.entity.Ad;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO representation of ad creation request.
 *
 * @param type        Ad type.
 * @param title       Title.
 * @param description Fully-detailed description.
 * @param price       Price.
 * @param property    Property creation DTO.
 */
public record CreateAdRequestDto(@NotNull Ad.Type type,
                                 @NotBlank String title,
                                 @NotBlank String description,
                                 @NotNull Integer price,
                                 @NotNull @Valid CreatePropertyRequestDto property) {
}
