package br.com.planningtravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.planningtravel.model.Travel;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {

}
