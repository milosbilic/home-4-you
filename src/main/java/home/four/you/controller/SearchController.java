package home.four.you.controller;

import home.four.you.model.dto.SearchDto;
import home.four.you.service.AdService;
import home.four.you.service.LocationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/search")
//@SessionAttributes("searchCritirea")
public class SearchController {

    private final LocationService locationService;
    private final AdService adService;

    @PostMapping
    public String search(@ModelAttribute("search") @Valid SearchDto searchDto,
                         BindingResult bindingResult, HttpSession httpSession) throws BindException {
        log.debug("Searching [{}]", searchDto);

        if (bindingResult.hasErrors()) {
            return "index";
        }
        httpSession.setAttribute("searchCritirea", searchDto);
        return "redirect:/search/show";
    }

    @GetMapping("/show")
    public ModelAndView showSearchedAds(@ModelAttribute("searchCritirea") SearchDto searchDto,
                                        @PageableDefault Pageable pageable) {
        log.debug("Showing search results for search criteria [{}]", searchDto);

        var ads = adService.findAll(pageable);

        return new ModelAndView("/ads/search_results")
                .addObject("page", ads);
    }
}

