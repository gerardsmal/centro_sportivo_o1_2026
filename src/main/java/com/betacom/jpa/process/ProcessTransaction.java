package com.betacom.jpa.process;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.AbbonamentoReq;
import com.betacom.jpa.dto.inputs.CertificatoReq;
import com.betacom.jpa.dto.inputs.SocioReq;
import com.betacom.jpa.exceptions.AcademyException;
import com.betacom.jpa.services.interfaces.IAbbonamentoServices;
import com.betacom.jpa.services.interfaces.ICertificatoServices;
import com.betacom.jpa.services.interfaces.ISocioServices;

@Component
public class ProcessTransaction {
	
	private final ISocioServices socioS; 
	private final ICertificatoServices certifS;
	private final IAbbonamentoServices abbS;

	public ProcessTransaction(ISocioServices socioS, 
			ICertificatoServices certifS,
			IAbbonamentoServices abbS
			) {
		this.socioS = socioS;
		this.certifS = certifS;
		this.abbS = abbS;
	}
	
	
	
	@Transactional (rollbackFor = Exception.class)
	public int aggiornamenti(SocioReq req) throws Exception {
		int id = insertSocio(req);
		
//		req = new SocioReq();
//		req.setCodiceFiscale("BSTPIE1232828");
//		req.setId(id);
//		update(req);
		return id;
	}
	
	@Transactional (rollbackFor = Exception.class)
	public void delete(Integer id) throws Exception{
		socioS.delete(id);
	}
	
	@Transactional (rollbackFor = Exception.class)
	public void insertAbbonamento(AbbonamentoReq req) throws Exception{
		abbS.create(req);
	}
	
	private int insertSocio(SocioReq req) throws  Exception{
		int id = 0;
		id = socioS.create(req);
		return id;
	}
	
	private void update(SocioReq req)  throws Exception{
		socioS.update(req);
	}
	@Transactional (rollbackFor = Exception.class)	
	public void insertCertificato(CertificatoReq req) throws Exception{
		certifS.create(req);
	}



}
