package advertising.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import advertising.helper.validation.annotation.PasswordMatches;


//TODO perhaps create a separate UserRegistration class for that purpose
@PasswordMatches
public class UserDto {

	private Long id;
	
	@NotNull
	@Size(min = 3, message = "Username should have at least 3 chars")
	private String username;
	
	@JsonIgnore
	@NotNull
	@Size(min = 5, message = "Password shoud have at least 5 chars")
	private String password;
	
	@JsonIgnore
	private String matchingPassword;
	
	@NotNull
	@Size(min = 2, message = "First name should have at least 2 chars")
	private String firstName;
	
	@NotNull
	@Size(min = 2, message = "First name should have at least 2 chars")
	private String lastName;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	@NotEmpty
	private String phone;
	
	private List<AdDto> ads;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<AdDto> getAds() {
		return ads;
	}

	public void setAds(List<AdDto> ads) {
		this.ads = ads;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

}
