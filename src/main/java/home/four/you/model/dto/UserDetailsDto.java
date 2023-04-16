package home.four.you.model.dto;

/**
 * DTO representing user details.
 *
 * @param id        User ID.
 * @param email     User email.
 * @param firstName User first name.
 * @param lastName  User last name.
 * @param phone     User phone.
 */
public record UserDetailsDto(Long id,
                             String email,
                             String firstName,
                             String lastName,
                             String phone) {
}
