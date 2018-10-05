package advertising.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import advertising.dto.AdDto;
import advertising.dto.SearchDto;
import advertising.enums.AdType;
import advertising.enums.RealEstateType;
import advertising.helper.converter.ConvertToAdDto;
import advertising.helper.converter.enums.AdTypeConverter;
import advertising.helper.converter.enums.RealEstateTypeConverter;
import advertising.service.AdService;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

	@Autowired
	private AdService adService;
	
	@Autowired
	private ConvertToAdDto toAdDto;
	
	@InitBinder
	public void initBinder(final WebDataBinder webdataBinder) {
		webdataBinder.registerCustomEditor(AdType.class, new AdTypeConverter());
		webdataBinder.registerCustomEditor(RealEstateType.class, new RealEstateTypeConverter());
	}
	//TODO add adType and re type properties to the search dto class
	@PostMapping
	@ResponseBody
	public List<AdDto> search(@ModelAttribute("search") @Valid SearchDto searchDto,
			BindingResult bindingResult) throws BindException {
		if (bindingResult.hasErrors()) {
			throw new BindException(bindingResult);
		}
		System.out.println(searchDto);
		return new ArrayList<>();
	}
}

