package it.prova.gestionetratte.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.repository.AirbusRepository;
import it.prova.gestionetratte.web.api.exception.TratteAssociateException;

@Service
public class AirbusServiceImpl implements AirbusService{
	
	@Autowired
	private AirbusRepository repository;
	
	@Autowired
	private EntityManager entityManager;

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
		if(airbusInstance.getTratte().size() > 0)
			throw new TratteAssociateException("Tratte associate, impossibile eliminare questo Airbus");
		
		repository.delete(airbusInstance);		
	}

	@Override
	@Transactional(readOnly = true)
	public Airbus trovaPerCodice(String codice) {
		return repository.findByCodice(codice);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Airbus> findByExample(Airbus example) {
		// TODO Auto-generated method stub
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("SELECT a FROM Airbus a WHERE a.id = a.id ");

		if (StringUtils.isNotEmpty(example.getCodice())) {
			whereClauses.add(" a.codice  LIKE :codice ");
			paramaterMap.put("codice", "%" + example.getCodice() + "%");
		}
		if (StringUtils.isNotEmpty(example.getDescrizione())) {
			whereClauses.add(" a.descrizione LIKE :descrizione ");
			paramaterMap.put("descrizione", "%" + example.getDescrizione() + "%");
		}
		if (example.getDataInizioServizio() != null) {
			whereClauses.add("a.dataInizioServizio >= :dataInizioServizio ");
			paramaterMap.put("dataInizioServizio", example.getDataInizioServizio());
		}
		if (example.getNumeroPasseggeri() != null && example.getNumeroPasseggeri() > 1) {
			whereClauses.add(" a.numeroPasseggeri >= :numeroPasseggeri ");
			paramaterMap.put("numeroPasseggeri", example.getNumeroPasseggeri());
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Airbus> typedQuery = entityManager.createQuery(queryBuilder.toString(), Airbus.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}
