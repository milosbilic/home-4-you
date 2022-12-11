package home.four.you.model.dto;

import home.four.you.model.entity.Ad;

import java.util.Date;

/**
 * Brief details DTO for ad entity.
 */
public record AdBriefDetailsDto(Long id,
                               String title,
                               String description,
                               Date dateCreated,
                               Date expirationDate,
                               UserDto user,
                               PriceDto price,
                               Ad.Type type) {
}
