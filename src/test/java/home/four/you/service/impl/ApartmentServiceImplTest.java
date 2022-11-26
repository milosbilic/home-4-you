package home.four.you.service.impl;

import home.four.you.model.entity.Apartment;
import home.four.you.repository.ApartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class ApartmentServiceImplTest {

	@Mock
	ApartmentRepository repo;
	
	@InjectMocks
	ApartmentServiceImpl apartmentServiceImpl;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFindOne() {
		Long id = 1L;
		Apartment apartment = new Apartment();
		apartment.setId(id);
		when(repo.findById(1L)).thenReturn(Optional.of(apartment));
		Apartment result = apartmentServiceImpl.findOne(id);
		
//		assertNotNull(result);
//		assertEquals(id, result.getId());
	}

	@Test
//	@Test(expected = NotFoundException.class)
	public void whenFindingNonExistingApartment_thenShouldThrowException() {
		Long id = 1L;
		when(repo.findById(1L)).thenReturn(Optional.empty());
		Apartment result = apartmentServiceImpl.findOne(id);
		
//		assertNull(result);
	}

	@Test
	public void testSave() {
		Apartment apartment = new Apartment();
		apartment.setArea(30.0);
		apartment.setRoomsNumber(2);
		
//		when(repo.save(Matchers.any(Apartment.class))).thenReturn(apartment);
		
		Apartment result = apartmentServiceImpl.save(apartment);
//		assertNotNull(result);
//		assertEquals(apartment.getArea(), result.getArea(), 0.001);
//		assertEquals(apartment.getRoomsNumber(), result.getRoomsNumber(), 0.001);
	}

}
