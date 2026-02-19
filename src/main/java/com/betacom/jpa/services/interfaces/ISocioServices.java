package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.inputs.SocioReq;
import com.betacom.jpa.dto.outputs.SocioDTO;
import com.betacom.jpa.exceptions.AcademyException;

public interface ISocioServices {

	Integer create(SocioReq req) throws AcademyException;
	void update(SocioReq req) throws AcademyException;
	void delete(Integer id) throws AcademyException;

	
	List<SocioDTO> findAll() throws Exception;
	SocioDTO findById(Integer id) throws Exception;
}
