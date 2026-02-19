package com.betacom.jpa.services.implementations;

import static com.betacom.jpa.utilities.Utils.stringToDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.AbbonamentoReq;
import com.betacom.jpa.exceptions.AcademyException;
import com.betacom.jpa.models.Abbonamento;
import com.betacom.jpa.models.Socio;
import com.betacom.jpa.repositories.IAbbonamentoRepository;
import com.betacom.jpa.repositories.ISocioRepository;
import com.betacom.jpa.services.interfaces.IAbbonamentoServices;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class AbbonamentoImpl implements IAbbonamentoServices{

	private final IAbbonamentoRepository abbR;
	private final ISocioRepository socioR;
	
	
	public AbbonamentoImpl(IAbbonamentoRepository abbR, ISocioRepository socioR) {
		this.abbR = abbR;
		this.socioR = socioR;
	}
	
	@Transactional (rollbackFor = AcademyException.class)
	@Override
	public void create(AbbonamentoReq req) throws Exception {
		log.debug("create {}", req);
		Socio soc = socioR.findById(req.getSocioID())
				.orElseThrow(() -> new AcademyException("Socio non trovato in DB:" + req.getSocioID()));
		Abbonamento abb = new Abbonamento();
		abb.setDataInscizione( stringToDate(req.getDataInscizione()));
		abb.setSocio(soc);
		
		abbR.save(abb);
		
	}

	@Override
	public void delete(Integer id) throws Exception {
		log.debug("delete {}", id);

		Abbonamento abb = abbR.findById(id)
				.orElseThrow(() -> new AcademyException("Abbonamento non trovato"));

		if (!abb.getAttivitas().isEmpty())
			throw new AcademyException("Ci sono attivita per quest'abbonamento");
		
		abbR.delete(abb);
	}



}
