package it.prova.gestionetratte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.repository.TrattaRepository;

@Service
public class TrattaServiceImpl implements TrattaService {

	@Autowired
	private TrattaRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Tratta> listAllElements(boolean eager) {
		if (eager)
			return (List<Tratta>) repository.findAllEager();

		return (List<Tratta>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Tratta caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Tratta caricaSingoloElementoEager(Long id) {
		return repository.findSingleTrattaEager(id);
	}

	@Override
	@Transactional
	public Tratta aggiorna(Tratta trattaInstance) {
		return repository.save(trattaInstance);
	}

	@Override
	@Transactional
	public Tratta inserisciNuovo(Tratta trattaInstance) {
		return repository.save(trattaInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Tratta trattaInstance) {
		repository.delete(trattaInstance);
	}
}
