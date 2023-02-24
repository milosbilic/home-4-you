package home.four.you.model.dto;

import home.four.you.model.entity.Ad;

import java.time.Instant;

/**
 * DTO Representation of fully-detailed Ad model.
 *
 * @param type           Ad type.
 * @param title          Ad title.
 * @param description    Full ad description.
 * @param price          Property price.
 * @param createdAt      Ad creation date and time.
 * @param expirationDate Ad expiration date.
 * @param property       Property being advertised.
 * @param ownerId        Owner ID.
 */
public record AdDetailsDto(Ad.Type type,
                           String title,
                           String description,
                           Integer price,
                           Instant createdAt,
                           Instant expirationDate,
                           PropertyDetailsDto property,
                           Long ownerId) {
}
