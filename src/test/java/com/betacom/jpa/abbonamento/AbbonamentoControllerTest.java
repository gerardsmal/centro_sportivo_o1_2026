package com.betacom.jpa.abbonamento;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.betacom.jpa.controllers.AbbonamentoController;
import com.betacom.jpa.dto.inputs.AbbonamentoReq;
import com.betacom.jpa.dto.outputs.AbbonamentoDTO;
import com.betacom.jpa.response.Resp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AbbonamentoControllerTest {

	@Autowired
	AbbonamentoController abboC;
	
	@Test
	@Order(1)
	public void create() {
		log.debug("create Abbonamento");
		AbbonamentoReq req = new AbbonamentoReq();
		req.setDataInscizione(LocalDate.now());
		req.setSocioID(1);
		
		ResponseEntity<Resp> resp = abboC.create(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		
		Assertions.assertThat(r.getMsg()).isEqualTo("rest_created");
		
	}
	@Test
	@Order(2)
	public void getById() {
		ResponseEntity<?> resp = abboC.findById(1);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		AbbonamentoDTO abb = (AbbonamentoDTO)resp.getBody();
		Assertions.assertThat(abb.getId()).isEqualTo(1);
	}
	@Test
	@Order(3)
	public void getByIdError() {
		ResponseEntity<?> resp = abboC.findById(99);
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
		Assertions.assertThat(resp.getBody()).isEqualTo("Abbonamento non trovato");
	}

}
