package home.four.you.model.dto;

import home.four.you.model.entity.Ad;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Response DTO for creating an ad.
 *
 * @param id
 * @param type
 * @param title
 */
public record CreateAdResponseDto(Long id,
                                  Ad.Type type,
                                  String title,
                                  String description,
                                  BigDecimal price,
                                  LocalDate createdAt,
                                  LocalDate expirationDate,
                                  PropertyDetailsDto property) {
}
