package com.betacom.jpa.process;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.AbbonamentoReq;
import com.betacom.jpa.dto.inputs.AttivitaReq;
import com.betacom.jpa.dto.inputs.CertificatoReq;
import com.betacom.jpa.dto.inputs.SocioReq;
import com.betacom.jpa.dto.outputs.SocioDTO;
import com.betacom.jpa.exceptions.AcademyException;
import com.betacom.jpa.services.interfaces.ICertificatoServices;
import com.betacom.jpa.services.interfaces.ISocioServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MainProcess {
	private final ISocioServices socioS;
	private final ProcessTransaction trans;
	private final ProcessAttivita attiv;
	private final ICertificatoServices certS;

	public MainProcess(ISocioServices socioS, 
			ProcessTransaction trans,
			ICertificatoServices certS,
			ProcessAttivita attiv) {
		this.socioS = socioS;
		this.trans = trans;
		this.certS = certS;
		this.attiv = attiv;
	}
	
	public void executeSocio() {
		log.debug("Begin executeSocio");
		SocioReq req = new SocioReq();
		req.setNome("Aldo");
		req.setCognome("Bonimi");
		req.setCodiceFiscale("ALDB090190");
		req.setEmail("a.bonino@gmail.it");
		int id = 0;
		try {
//			id = trans.aggiornamenti(req);
//			CertificatoReq reqC = new CertificatoReq();
//			reqC.setDataCertificato("01/02/2026");
//			reqC.setSocioID(id);
//			trans.insertCertificato(reqC);
			
//			ListSocioViaCertificato();
//			trans.delete(6);
//			createAbbonamento(8);
			
//			listSocio();
//			listSocioById(8);
//			attiv.createAttivita();
			attiv.list();
//			createAbbonamentoAvvitita(1, 4);
//			attiv.deleteAttivita(4);
			trans.deleteAbbonamento(2);
		} catch (Exception e) {
			log.error("Error found in process: {}", e.getMessage());
		}
		//delete(id);
		
		
	}

	private void createAbbonamento (Integer id) throws Exception{
		AbbonamentoReq req = new AbbonamentoReq();
		req.setDataInscizione("15/12/2025");
		req.setSocioID(id);
		trans.insertAbbonamento(req);
	}
	
	private void listSocio() {
		try {
			List<SocioDTO> lS = socioS.findAll();
			lS.forEach(s -> log.debug(s.toString()));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private void listSocioById(Integer id) {
		try {
			SocioDTO s = socioS.findById(id);
			log.debug(s.toString());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private void ListSocioViaCertificato() {
		List<SocioDTO> lS;
		try {
			lS = certS.listSocio();
			lS.forEach(c -> log.debug(c.toString()));

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private void createAbbonamentoAvvitita(int abbID, int attiId) throws Exception{
		AttivitaReq req = new AttivitaReq();
		req.setId(attiId);
		req.setAbbonamentID(abbID);
		attiv.createAttivitaAbbonamento(req);
	}
}

