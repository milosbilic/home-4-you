package home.four.you.service.impl;

import home.four.you.exception.NotFoundException;
import home.four.you.model.entity.Apartment;
import home.four.you.repository.ApartmentRepository;
import home.four.you.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link ApartmentService}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;

    @Override
    public Apartment findOne(Long id) {
        log.debug("Finding an apartment with id of {}", id);

        return apartmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Apartment with id of " + id + " not found!"));
    }

    @Override
    public Apartment save(Apartment apartment) {
        log.debug("Saving apartment [{}]", apartment);

        return apartmentRepository.save(apartment);
    }

}
