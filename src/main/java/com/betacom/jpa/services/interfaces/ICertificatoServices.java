package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.inputs.CertificatoReq;
import com.betacom.jpa.dto.outputs.CertificatoDTO;
import com.betacom.jpa.dto.outputs.SocioDTO;

public interface ICertificatoServices {
	void create(CertificatoReq req) throws Exception;
	void update(CertificatoReq req) throws Exception;
	
	List<SocioDTO> listSocio() throws Exception;
	CertificatoDTO findById(Integer id) throws Exception;
	
}
