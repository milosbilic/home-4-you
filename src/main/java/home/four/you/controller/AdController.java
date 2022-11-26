package home.four.you.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import home.four.you.dto.AdDto;
import home.four.you.dto.ApartmentAdDto;
import home.four.you.dto.HouseAdDto;
import home.four.you.model.AdType;
import home.four.you.model.HeatType;
import home.four.you.model.RealEstateType;
import home.four.you.exception.NotFoundException;
import home.four.you.exception.UnsupportedTypeException;
import home.four.you.factory.AdDtoFactory;
import home.four.you.converter.ConvertToEquipmentDto;
import home.four.you.converter.enums.AdTypeConverter;
import home.four.you.converter.enums.HeatTypeConverter;
import home.four.you.converter.enums.RealEstateTypeConverter;
import home.four.you.model.entity.Ad;
import home.four.you.service.AdService;
import home.four.you.service.ApartmentService;
import home.four.you.service.EquipmentService;
import home.four.you.service.HouseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import home.four.you.model.entity.RealEstate;

@Controller
@RequestMapping(value = "/ads")
public class AdController {

	@Autowired
	private AdService adService;
	
	@Autowired
	private HouseService houseService;
	
	@Autowired
	private ApartmentService apartmentService;
	
	@Autowired
	private EquipmentService equipmentService;

//	@Autowired
//	private ConvertToAdDto toDto;

	
	@Autowired
	private ConvertToEquipmentDto toEquipmentDto;

	private static final String HAS_ANY_ROLE = "hasAnyRole('USER', 'ADMIN')";

	private static final String UPLOAD_LOCATION = "E:/temp/";
	
	@InitBinder
	public void initBinder(final WebDataBinder webdataBinder) {
		webdataBinder.registerCustomEditor(AdType.class, new AdTypeConverter());
		webdataBinder.registerCustomEditor(RealEstateType.class, new RealEstateTypeConverter());
		webdataBinder.registerCustomEditor(HeatType.class, new HeatTypeConverter());
	}

	@GetMapping
	public List<AdDto> findAll() {
//		return toDto.convert(adService.findAll());
		return new ArrayList<>();
	}

	@GetMapping("/{id}")
	public ModelAndView findOne(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("ads/show");
//		mav.addObject("ad", toDto.convert(adService.findOne(id)));
		return mav;
	}

	@GetMapping("/{realEstateId}/image")
	public void renderImage(@PathVariable Long realEstateId, HttpServletResponse response) throws IOException {
		RealEstate re = houseService.findOne(realEstateId);
		if (re == null) {
			re = apartmentService.findOne(realEstateId);
		} 
		if (re == null) {
			throw new NotFoundException("No real estate with id of " + realEstateId);
		}
		
		byte[] image = re.getImage();
		response.setContentType("image/jpeg");
		InputStream is = new ByteArrayInputStream(image);
		IOUtils.copy(is, response.getOutputStream());
	}

	@GetMapping("/new")
	public ModelAndView newAdd(@RequestParam RealEstateType realEstateType) {
		ModelAndView mav = new ModelAndView("ads/new");
		mav.addObject("newAd", AdDtoFactory.getInstance(realEstateType));
		mav.addObject("adTypes", AdType.values());
		mav.addObject("realEstateType", realEstateType);
		mav.addObject("heatTypes", HeatType.values());
		//TODO implement caching on query methods for these kind of queries
		mav.addObject("equipment", toEquipmentDto.convert(equipmentService.findAll()));
		return mav;
	}

	@PostMapping
	@PreAuthorize(HAS_ANY_ROLE)
	public ResponseEntity<AdDto> create(@ModelAttribute("newAd") @Valid AdDto adDto,
			@RequestParam(name="equipment", required = false) List<Long> equipmentIds,
			Authentication auth, BindingResult result) throws IOException{
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
		} else {
			try {
				FileCopyUtils.copy(adDto.getFile().getFile().getBytes(),
				new File(UPLOAD_LOCATION + adDto.getFile().getFile().getOriginalFilename()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			adService.save(adDto, equipmentIds, auth.getName());
		}
		return null;
	}

	@DeleteMapping("/{id}")
	@PreAuthorize(HAS_ANY_ROLE)//TODO user should be allowed to delete his ads only
	public ResponseEntity<AdDto> delete(@PathVariable Long id) {
		Ad ad = adService.findOne(id);
		adService.delete(ad);
//		return new ResponseEntity<>(toDto.convert(ad), HttpStatus.NO_CONTENT);
		return null;
	}

	@ModelAttribute("newAd")
	public AdDto getInstance(final HttpServletRequest request) {
		AdDto adDto = null;
		String type = request.getParameter("realEstateType");
		if (type != null) {
			if (type.equalsIgnoreCase("house")) {
				adDto = new HouseAdDto();
			} else if (type.equalsIgnoreCase("apartment")) {
				adDto = new ApartmentAdDto();
			} else {
				throw new UnsupportedTypeException("The passed type doesn't exist!");
			}
		}
		return adDto;
	}

}
