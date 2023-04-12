package home.four.you.model.dto;

/**
 * DTO representing the created user response.
 */
public record CreateUserResponseDto(Long id,
                                    String email,
                                    String firstName,
                                    String lastName,
                                    String phone) {
}
