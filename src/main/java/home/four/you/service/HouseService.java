package home.four.you.service;

import home.four.you.model.entity.House;

public interface HouseService {

	House findOne(Long id);

	House save(House house);

}
