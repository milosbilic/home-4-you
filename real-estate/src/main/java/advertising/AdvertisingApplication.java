package advertising;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

import advertising.dto.EquipmentDto;
import advertising.helper.converter.ConvertToEquipmentDto;
import advertising.model.Equipment;
import advertising.model.House;
import advertising.repository.EquipmentRepository;
import advertising.repository.HouseRepository;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class AdvertisingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvertisingApplication.class, args);
	}
	
/*	@Autowired
	private HouseRepository houseRepository;
	
	@Autowired
	private EquipmentRepository EquipmentRepository;
	
	@Autowired
	private ConvertToEquipmentDto toDto;
	
	@PostConstruct
	public void test() {
//		House h = houseRepository.findOne(1L);
//		System.out.println(h.getId() + " " + h.getArea());
		List<Equipment> equipments = EquipmentRepository.findAll();
		List<EquipmentDto> dtos = toDto.convert(equipments);
		dtos.forEach(dto -> System.out.println(dto.getName()));
		
	}*/

}
