package home.four.you.model.dto;

import home.four.you.model.entity.Ad;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Response DTO for creating an ad.
 *
 * @param id             Ad ID.
 * @param type           Ad type.
 * @param title          Ad title.
 * @param description    Ad full description.
 * @param price          Property price.
 * @param createdAt      Ad creation date and time.
 * @param expirationDate Ad expiration date and time.
 * @param property       Property being advertised in the ad.
 */
public record CreateAdResponseDto(Long id,
                                  Ad.Type type,
                                  String title,
                                  String description,
                                  BigDecimal price,
                                  Instant createdAt,
                                  Instant expirationDate,
                                  PropertyDetailsDto property) {
}
