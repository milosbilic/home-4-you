package advertising.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import advertising.dto.AdDto;
import advertising.helper.converter.ConvertToAdDto;
import advertising.helper.converter.ConvertToAdEntity;
import advertising.model.Ad;
import advertising.service.AdService;
import advertising.service.UserService;

@RestController
@RequestMapping(value = "/ads")
public class AdController {

	@Autowired
	private AdService adService;
	
	@Autowired
	private ConvertToAdDto toDto;
	
	@Autowired
	private ConvertToAdEntity toEntity;
	
	@Autowired
	private UserService userService;
	
	private static final String HAS_ANY_ROLE = "hasAnyRole('USER', 'ADMIN')";
	/*
	@PostConstruct
	public void test() throws ParseException  {
		House h = new House();
		
		Equipment equipment = new Equipment();
		equipment.setName("Cable");
		Set<Equipment> eqSet = new HashSet<>();
		eqSet.add(equipment);
		
		Location location = new Location();
		location.setName("Ruma");
		location.setZipCode("22400");
		
		h.setArea(55);
		h.setBooked(true);
		h.setHeatType(HeatType.CENTRAL);
		h.setLocation(location);
		h.setRoomsNumber(4);
		h.setEquipment(eqSet);
		
		Price p = new Price();
		p.setAmount(new BigDecimal(555));
		p.setCurrency(Currency.getInstance("EUR"));
		Ad ad = new Ad();
		ad.setAdOwnerType(AdOwnerType.SELLER);
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String dstring = "08/23/2434";
		Date d = df.parse(dstring);
		ad.setExpirationDate(d);
		ad.setTitle("title");
		ad.setUser(userService.findOne(1L));
		ad.setPrice(p);
		ad.setRealEstate(h);
		adService.save(ad);
	}*/
	
	@GetMapping
	public List<AdDto> findAll() {
		return toDto.convert(adService.findAll());
	}
	
	@GetMapping("/{id}")
	public ModelAndView findOne(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("ads/show");
		mav.addObject("ad", toDto.convert(adService.findOne(id)));
		return mav;
	}
	
	@PostMapping(consumes = "application/json")
	@PreAuthorize(HAS_ANY_ROLE)
	public ResponseEntity<AdDto> create(@RequestBody AdDto adDto) {
		Ad persistant = adService.save(toEntity.convert(adDto));
		return new ResponseEntity<>(toDto.convert(persistant), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}", consumes = "applicatin/json")
	@PreAuthorize(HAS_ANY_ROLE)
	public ResponseEntity<AdDto> edit(@PathVariable Long id, @RequestBody AdDto adDto) {
		Ad persistant = adService.save(toEntity.convert(adDto));
		return new ResponseEntity<>(toDto.convert(persistant), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize(HAS_ANY_ROLE)
	public ResponseEntity<AdDto> delete(@PathVariable Long id) {
		Ad ad = adService.findOne(id);
		adService.delete(ad);
		return new ResponseEntity<>(toDto.convert(ad), HttpStatus.NO_CONTENT);
	}
	
}
