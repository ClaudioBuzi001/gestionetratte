package it.prova.gestionetratte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.repository.AirbusRepository;

@Service
public class AirbusServiceImpl implements AirbusService{
	
	@Autowired
	private AirbusRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Airbus> listAllElements(boolean eager) {
		if(eager) 
			return (List<Airbus>) repository.findAllEager();
		
		return (List<Airbus>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Airbus caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Airbus caricaSingoloElementoEager(Long id) {
		return repository.findSingleAirbusEager(id);
	}

	@Override
	@Transactional
	public Airbus aggiorna(Airbus airbusInstance) {
		return repository.save(airbusInstance);
	}

	@Override
	@Transactional
	public Airbus inserisciNuovo(Airbus airbusInstance) {
		return repository.save(airbusInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Airbus airbusInstance) {
		repository.delete(airbusInstance);		
	}

	@Override
	public Airbus trovaPerCodice(String codice) {
		return repository.findByCodice(codice);
	}

}
