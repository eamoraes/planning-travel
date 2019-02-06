package br.com.planningtravel.service;

import org.springframework.stereotype.Service;

import br.com.planningtravel.model.User;
import br.com.planningtravel.service.base.CrudService;

@Service
public class UserService extends CrudService<User, Long> {

}
