package advertising.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import advertising.model.Equipment;
import advertising.repository.EquipmentRepository;

public class EquipmentServiceImplTest {

	@Mock
	EquipmentRepository repo;
	
	@InjectMocks
	EquipmentServiceImpl equipmentServiceImpl;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFindOne() {
		Equipment e = new Equipment();
		e.setId(1L);
		e.setName("TV");
		
		when(repo.findOne(1L)).thenReturn(e);
		
		Equipment result = equipmentServiceImpl.findOne(1L);
		
		assertNotNull(result);
		assertEquals(e.getName(), result.getName());
	}

	@Test
	public void testFindAll() {
		List<Equipment> equipment = Arrays.asList(new Equipment(), new Equipment(), new Equipment());
		
		when(repo.findAll()).thenReturn(equipment);
		
		List<Equipment> result = equipmentServiceImpl.findAll();
		
		assertNotNull(result);
		assertEquals(3, result.size());
	}

	@Test
	public void testFindByIds() {
		List<Long> ids = Arrays.asList(1L, 2L, 3L);
		Equipment eq1 = new Equipment("TV");
		Equipment eq2 = new Equipment("Internet");
		Equipment eq3 = new Equipment("Telephone");
		
		when(repo.findOne(1L)).thenReturn(eq1);
		when(repo.findOne(2L)).thenReturn(eq2);
		when(repo.findOne(3L)).thenReturn(eq3);
		
		Set<Equipment> result = equipmentServiceImpl.findByIds(ids);
		
		assertNotNull(result);
		assertEquals(3, result.size());
	}

}
