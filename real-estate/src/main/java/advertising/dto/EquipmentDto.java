package advertising.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class EquipmentDto {
	
	private Long id;
	@NotNull
	@NotEmpty
	private String name;

	public EquipmentDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "EquipmentDto [id=" + id + ", name=" + name + "]";
	}

	
}
