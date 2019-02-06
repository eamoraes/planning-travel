package br.com.planningtravel.service;

import org.springframework.stereotype.Service;

import br.com.planningtravel.model.Travel;
import br.com.planningtravel.service.base.CrudService;

@Service
public class TravelService extends CrudService<Travel, Long> {
	
	@Override
	public Travel create(Travel entity) {
		setParent(entity);
		return super.create(entity);
	}
	
	@Override
	public Travel update(Travel entity, Long id) throws Exception {
		setParent(entity);
		return super.update(entity, id);
	}
	
	private void setParent(Travel entity) {
		entity.getWishes().forEach(wish -> {
			wish.setTravel(entity);
		});
	}
}
