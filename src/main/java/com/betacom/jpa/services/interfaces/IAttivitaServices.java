package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.inputs.AttivitaReq;
import com.betacom.jpa.dto.outputs.AbbonamentoDTO;
import com.betacom.jpa.dto.outputs.AttivitaDTO;

public interface IAttivitaServices {
	void create(AttivitaReq req) throws Exception;
	void update(AttivitaReq req) throws Exception;
	void delete(Integer id) throws Exception;

	void createAttivitaAbbonamento(AttivitaReq req) throws Exception;
	void deleteAttivitaAbbonamento(Integer idAbbo, Integer idAttivita) throws Exception;
	
	List<AttivitaDTO> list();
	
	List<AbbonamentoDTO> getByIdAttivita(Integer id) throws Exception;
}
