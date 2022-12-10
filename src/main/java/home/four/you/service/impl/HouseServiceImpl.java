package home.four.you.service.impl;

import home.four.you.model.entity.House;
import home.four.you.repository.HouseRepository;
import home.four.you.service.HouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link HouseService}.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;

    @Override
    public House findOne(Long id) {
        log.debug("Finding a house with id {}", id);

        return houseRepository.findById(id).orElseThrow();
    }

    @Override
    public House save(House house) {
        log.debug("Saving house [{}]", house);

        return houseRepository.save(house);
    }

}
