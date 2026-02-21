package com.betacom.jpa.services.interfaces;

import com.betacom.jpa.dto.inputs.AbbonamentoReq;
import com.betacom.jpa.dto.outputs.AbbonamentoDTO;

public interface IAbbonamentoServices {

	void create(AbbonamentoReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	AbbonamentoDTO getById(Integer id) throws Exception;
}
