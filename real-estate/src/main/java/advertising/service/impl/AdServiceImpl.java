package advertising.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import advertising.model.Ad;
import advertising.repository.AdRepository;
import advertising.service.AdService;

@Service
@Transactional
public class AdServiceImpl implements AdService {

	@Autowired
	private AdRepository adRepository;
	
	@Override
	public List<Ad> findAll() {
		return adRepository.findAll();
	}

	@Override
	public Ad findOne(Long id) {
		Ad ad = adRepository.findOne(id);
		return ad;
	}

	@Override
	public Ad save(Ad ad) {
		return adRepository.save(ad);
	}

	@Override
	public void delete(Ad ad) {
		adRepository.delete(ad);
	}

}
