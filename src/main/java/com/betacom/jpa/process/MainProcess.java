package com.betacom.jpa.process;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.CertificatoReq;
import com.betacom.jpa.dto.inputs.SocioReq;
import com.betacom.jpa.dto.outputs.SocioDTO;
import com.betacom.jpa.exceptions.AcademyException;
import com.betacom.jpa.services.interfaces.ISocioServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MainProcess {
	private final ISocioServices socioS;
	private final ProcessTransaction trans;

	public MainProcess(ISocioServices socioS, ProcessTransaction trans) {
		this.socioS = socioS;
		this.trans = trans;
	}
	
	public void executeSocio() {
		log.debug("Begin executeSocio");
		SocioReq req = new SocioReq();
		req.setNome("Paolo");
		req.setCognome("Rossi");
		req.setCodiceFiscale("RSPL12823383");
		req.setEmail("p.rosso@tin.it");
		int id = 0;
		try {
			id = trans.aggiornamenti(req);
			CertificatoReq reqC = new CertificatoReq();
			reqC.setDataCertificato("01/02/2026");
			reqC.setSocioID(id);
			trans.insertCertificato(reqC);
			
			listSocio();

		} catch (Exception e) {
			log.error("Error found in process: {}", e.getMessage());
		}
		//delete(id);
		
		
	}

	public void delete(Integer id) throws Exception{
		socioS.delete(id);
	}

	
	private void listSocio() {
		try {
			List<SocioDTO> lS = socioS.findAll();
			lS.forEach(s -> log.debug(s.toString()));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}

