package it.prova.gestionetratte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionetratte.model.Airbus;


public interface AirbusRepository extends CrudRepository<Airbus, Long> {
	@Query("from Airbus a join fetch a.tratte where a.id = ?1")
	Airbus findSingleAirbusEager(Long id);
	
	@Query("select a from Airbus a join fetch a.tratte")
	List<Airbus> findAllEager();

	Airbus findByCodice(String codice);

}
