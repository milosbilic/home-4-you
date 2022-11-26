package home.four.you.service.impl;

import home.four.you.model.entity.Equipment;
import home.four.you.repository.EquipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

public class EquipmentServiceImplTest {

	@Mock
	EquipmentRepository repo;
	
	@InjectMocks
	EquipmentServiceImpl equipmentServiceImpl;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFindOne() {
		Equipment e = new Equipment();
		e.setId(1L);
		e.setName("TV");
		
		when(repo.findById(1L)).thenReturn(Optional.of(e));
		
		Equipment result = equipmentServiceImpl.findOne(1L);
		
//		assertNotNull(result);
//		assertEquals(e.getName(), result.getName());
	}

	@Test
	public void testFindAll() {
		List<Equipment> equipment = Arrays.asList(new Equipment(), new Equipment(), new Equipment());
		
		when(repo.findAll()).thenReturn(equipment);
		
		List<Equipment> result = equipmentServiceImpl.findAll();
		
//		assertNotNull(result);
//		assertEquals(3, result.size());
	}

	@Test
	public void testFindByIds() {
		List<Long> ids = Arrays.asList(1L, 2L, 3L);
		Equipment eq1 = new Equipment("TV");
		Equipment eq2 = new Equipment("Internet");
		Equipment eq3 = new Equipment("Telephone");
		
		when(repo.findById(1L)).thenReturn(Optional.of(eq1));
		when(repo.findById(2L)).thenReturn(Optional.of(eq2));
		when(repo.findById(3L)).thenReturn(Optional.of(eq3));
		
		Set<Equipment> result = equipmentServiceImpl.findByIds(ids);
		
//		assertNotNull(result);
//		assertEquals(3, result.size());
	}

}
