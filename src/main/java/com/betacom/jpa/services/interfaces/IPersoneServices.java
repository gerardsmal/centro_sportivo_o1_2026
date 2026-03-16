package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.inputs.PersoneReq;
import com.betacom.jpa.dto.outputs.PersoneDTO;

public interface IPersoneServices {
	void create(PersoneReq req) throws Exception;
	void update(PersoneReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<PersoneDTO> list();
	PersoneDTO getById(Integer id) throws Exception;
}
