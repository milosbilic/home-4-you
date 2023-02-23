package home.four.you.model.dto;

import home.four.you.model.entity.Ad;

import java.time.Instant;

/**
 * Brief details DTO for ad entity.
 */
public record AdBriefDetailsDto(Long id,
                                Ad.Type type,
                                String title,
                                Integer area,
                                Integer numberOfRooms,
                                Instant createdAt,
                                Integer price) {
}
