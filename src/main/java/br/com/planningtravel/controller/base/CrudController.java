package br.com.planningtravel.controller.base;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.planningtravel.model.EntityBase;
import br.com.planningtravel.response.Response;
import br.com.planningtravel.service.base.CrudService;

public class CrudController<T extends EntityBase<ID>, ID> {

	@Autowired
	private CrudService<T, ID> service;
	
	private String path;
	
	public CrudController(String path) {
		this.path = path;
	}
	
	@GetMapping
	public ResponseEntity<Response<List<T>>> findAll() {
		return ResponseEntity.ok(Response.success(service.findAll()));
	}
	
	@PostMapping
	public ResponseEntity<Response<T>> create(@RequestBody T entity) {
		entity = service.create(entity);
		//return ResponseEntity.created(URI.create("/api/" + path + "/" + entity.getId())).body(Response.success(service.create(entity)));
		return ResponseEntity.created(URI.create(path + "/" + entity.getId())).body(Response.success(service.create(entity)));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<T>> update(@PathVariable ID id, @RequestBody T entity) throws Exception {
		return ResponseEntity.ok(Response.success(service.update(entity, id)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response<T>> delete(@PathVariable ID id) {
		Optional<T> entity = service.delete(id);
		if (!entity.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.error("commons.message.notFound"));
		}
		return ResponseEntity.ok(Response.success(entity.get()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<T>> findOne(@PathVariable ID id) {
		Optional<T> entity = service.findById(id);
		if (!entity.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.error("commons.message.notFound"));
		}
		return ResponseEntity.ok(Response.success(entity.get()));
	}	
}
