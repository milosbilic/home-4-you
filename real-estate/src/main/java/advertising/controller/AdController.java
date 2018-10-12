package advertising.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import advertising.dto.AdDto;
import advertising.helper.converter.ConvertToAdDto;
import advertising.helper.converter.ConvertToAdEntity;
import advertising.model.Ad;
import advertising.service.AdService;
import advertising.service.RealEstateService;

@RestController
@RequestMapping(value = "/ads")
public class AdController {

	@Autowired
	private AdService adService;
	
	@Autowired
	private RealEstateService realEstateService;
	
	@Autowired
	private ConvertToAdDto toDto;
	
	@Autowired
	private ConvertToAdEntity toEntity;
	
	private static final String HAS_ANY_ROLE = "hasAnyRole('USER', 'ADMIN')";
	
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
	
	@GetMapping("/{realEstateId}/image")
	public void renderImage(@PathVariable Long realEstateId, HttpServletResponse response) throws IOException {
		byte[] image = realEstateService.findOne(realEstateId).getImage();
		response.setContentType("image/jpeg");
		InputStream is = new ByteArrayInputStream(image);
		IOUtils.copy(is, response.getOutputStream());
	}
	
	@GetMapping("/new")
	public ModelAndView newAdd() {
		ModelAndView mav = new ModelAndView("ads/new");
		mav.addObject("newAd", new AdDto());
		return mav;
	}
	
	@PostMapping(consumes = "application/json")
	@PreAuthorize(HAS_ANY_ROLE)
	public ResponseEntity<AdDto> create(@ModelAttribute("newAd") AdDto adDto,
			@RequestParam("image") MultipartFile image) {
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
