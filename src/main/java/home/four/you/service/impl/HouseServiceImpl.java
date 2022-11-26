package home.four.you.service.impl;

import home.four.you.model.entity.House;
import home.four.you.repository.HouseRepository;
import home.four.you.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseRepository repo;

    @Override
    public House findOne(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public House save(House house) {
        return repo.save(house);
    }

}
