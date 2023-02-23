package home.four.you.model.dto;

/**
 * DTO representing location details.
 *
 * @param id      Location ID.
 * @param name    Location name.
 * @param zipCode Location ZIP code.
 */
public record LocationDetailsDto(Long id,
                                 String name,
                                 String zipCode) {
}
