package advertising.service.impl;

import static org.hamcrest.Matchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import advertising.exception.NotFoundException;
import advertising.model.Apartment;
import advertising.repository.ApartmentRepository;

public class ApartmentServiceImplTest {

	@Mock
	ApartmentRepository repo;
	
	@InjectMocks
	ApartmentServiceImpl apartmentServiceImpl;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFindOne() {
		Long id = 1L;
		Apartment apartment = new Apartment();
		apartment.setId(id);
		when(repo.findOne(1L)).thenReturn(apartment);
		Apartment result = apartmentServiceImpl.findOne(id);
		
		assertNotNull(result);
		assertEquals(id, result.getId());
	}
	
	@Test(expected = NotFoundException.class)
	public void whenFindingNonExistingApartment_thenShouldThrowException() {
		Long id = 1L;
		when(repo.findOne(1L)).thenReturn(null);
		Apartment result = apartmentServiceImpl.findOne(id);
		
		assertNull(result);
	}

	@Test
	public void testSave() {
		Apartment apartment = new Apartment();
		apartment.setArea(30.0);
		apartment.setRoomsNumber(2);
		
		when(repo.save(Matchers.any(Apartment.class))).thenReturn(apartment);
		
		Apartment result = apartmentServiceImpl.save(apartment);
		assertNotNull(result);
		assertEquals(apartment.getArea(), result.getArea(), 0.001);
		assertEquals(apartment.getRoomsNumber(), result.getRoomsNumber(), 0.001);
	}

}
