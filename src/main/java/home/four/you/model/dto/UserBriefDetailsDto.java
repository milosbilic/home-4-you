package home.four.you.model.dto;

/**
 * DTO with brief user details.
 */
public record UserBriefDetailsDto(Long id,
                                  String username,
                                  String firstName,
                                  String lastName,
                                  String email,
                                  String phone) {
}
