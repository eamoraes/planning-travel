package br.com.planningtravel.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.planningtravel.controller.base.CrudController;
import br.com.planningtravel.model.Travel;
import br.com.planningtravel.response.Response;
import br.com.planningtravel.utils.ControllerConstants;

@RestController
@RequestMapping(ControllerConstants.TRAVEL_PATH)
@Secured(value={"ROLE_ADMIN", "ROLE_USER"})
public class TravelController extends CrudController<Travel, Long> {
	
	public TravelController() {
		super(ControllerConstants.TRAVEL_PATH);
	}
	
	@Override
	public ResponseEntity<Response<List<Travel>>> findAll() {
		return super.findAll();
	}
	
	@Override
	public ResponseEntity<Response<Travel>> create(@RequestBody Travel entity) {
		return super.create(entity);
	}
	
	@Override
	public ResponseEntity<Response<Travel>> delete(@PathVariable Long id) {
		return super.delete(id);
	}
	
	@Override
	public ResponseEntity<Response<Travel>> findOne(@PathVariable Long id) {
		return super.findOne(id);
	}
	
	@Override
	public ResponseEntity<Response<Travel>> update(@PathVariable Long id, @RequestBody Travel entity) throws Exception {
		return super.update(id, entity);
	}

}
