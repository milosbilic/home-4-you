package home.four.you.model.dto;

import home.four.you.model.entity.Ad;

import java.util.Date;

/**
 * Brief details DTO for ad entity.
 */
public record AdBriefDetailsDto(Long id,
                                Ad.Type type,
                                String title,
                                Integer area,
                                Integer numberOfRooms,
                                Date dateCreated,
                                Integer price) {
}
