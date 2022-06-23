package it.prova.gestionetratte.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionetratte.model.Tratta;

public interface TrattaRepository extends CrudRepository<Tratta, Long> {

	@Query("from Tratta t join fetch t.airbus where t.id = ?1")
	Tratta findSingleTrattaEager(Long id);

	@Query("from Tratta t join fetch t.airbus")
	List<Tratta> findAllEager();

//Mi serve un metodo che mi Prende una data(Di ora) come parametro e mi prende tutte le date, se la data di atterraggio < di dataOra allora mi imposta
//lo stato ad annullato

	@Modifying
	@Query("update Tratta t set t.stato = 'CONCLUSA' where t.stato = 'ATTIVA' and t.data = ?1 and t.oraAtterraggio <= ?2")
	int chiudiTratte(LocalDate data, LocalTime orario);

	

}
