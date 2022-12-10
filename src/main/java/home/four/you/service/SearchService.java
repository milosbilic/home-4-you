package home.four.you.service;

import home.four.you.model.entity.Ad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import home.four.you.model.dto.SearchDto;

public interface SearchService {

	Page<Ad> search(SearchDto searchDto, Pageable pageable);
}
