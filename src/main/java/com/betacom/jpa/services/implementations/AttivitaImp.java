package com.betacom.jpa.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.AttivitaReq;
import com.betacom.jpa.dto.outputs.AttivitaDTO;
import com.betacom.jpa.exceptions.AcademyException;
import com.betacom.jpa.models.Abbonamento;
import com.betacom.jpa.models.Attivita;
import com.betacom.jpa.repositories.IAbbonamentoRepository;
import com.betacom.jpa.repositories.IAttivitaRepository;
import com.betacom.jpa.services.interfaces.IAttivitaServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AttivitaImp implements IAttivitaServices{
	private final IAttivitaRepository attivR;
	private final IAbbonamentoRepository abbR;
	
	public AttivitaImp(IAttivitaRepository attivR,
			IAbbonamentoRepository abbR) {
		this.attivR = attivR;
		this.abbR = abbR;
	}

	@Transactional (rollbackFor = AcademyException.class)
	@Override
	public void create(AttivitaReq req) throws Exception {
		log.debug("create []", req);
		if (attivR.existsByDescription(req.getDescription().trim().toUpperCase())){
			throw new AcademyException("Attivita presente in db:" + req.getDescription());	
		}
		Attivita at = new Attivita();
		at.setDescription(req.getDescription().trim().toUpperCase());
		
		attivR.save(at);
				
	}
	@Transactional (rollbackFor = AcademyException.class)
	@Override
	public void update(AttivitaReq req) throws Exception {
		log.debug("update {}", req);
		Attivita at = attivR.findById(req.getId())
				.orElseThrow(() -> new AcademyException("Attivita non trovata"));
		if (req.getDescription() != null) {
			if (attivR.existsByDescription(req.getDescription().trim().toUpperCase())){
				throw new AcademyException("Attivita presente in db:" + req.getDescription());	
			}
			at.setDescription(req.getDescription().trim().toUpperCase());
		}
		attivR.save(at);
	}
	@Transactional (rollbackFor = AcademyException.class)
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("delete {}", id);
		Attivita at = attivR.findById(id)
				.orElseThrow(() -> new AcademyException("Attivita non trovata"));
		
		if (!at.getAbbonamentos().isEmpty())
			throw new AcademyException("Ci sono abbonamenti collegati per quest'attivia");
		
		attivR.delete(at);		
	}

	@Transactional (rollbackFor = AcademyException.class)
	@Override
	public void createAttivitaAbbonamento(AttivitaReq req) throws Exception {
		log.debug("createAttivitaAbbonamento {}", req);
		Attivita at = attivR.findById(req.getId())
				.orElseThrow(() -> new AcademyException("Attivita non trovata"));
		Abbonamento ab = abbR.findById(req.getAbbonamentID())
				.orElseThrow(() -> new AcademyException("Abbonamento non trovato"));
		
		ab.getAttivitas().add(at);  // update whit new attivita
		abbR.save(ab);
	}

	
	
	@Override
	public List<AttivitaDTO> list() {
		log.debug("list");
		List<Attivita> lA = attivR.findAll();
		return lA.stream()
				.map(a -> AttivitaDTO.builder()
						.id(a.getId())
						.description(a.getDescription())
						.build()
						).toList();
	}



}
