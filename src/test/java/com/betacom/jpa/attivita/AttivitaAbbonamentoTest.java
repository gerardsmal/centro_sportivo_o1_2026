package com.betacom.jpa.attivita;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.betacom.jpa.controllers.AbbonamentoController;
import com.betacom.jpa.controllers.AttivitaController;
import com.betacom.jpa.dto.inputs.AttivitaReq;
import com.betacom.jpa.response.Resp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AttivitaAbbonamentoTest {

	@Autowired
	AttivitaController attivC;
	
	@Autowired
	AbbonamentoController abboC;
	
	@Test
	@Order(1)
	public void createAttivitaAbbonamentoTest() {
		AttivitaReq req = new AttivitaReq();
		req.setId(1);
		req.setAbbonamentID(1);
		ResponseEntity<Resp> resp = attivC.createAttivitaAbbonamento(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
	}
	
	@Test
	@Order(2)
	public void createAttivitaAbbonamento1Test() {
		AttivitaReq req = new AttivitaReq();
		req.setId(2);
		req.setAbbonamentID(1);
		ResponseEntity<Resp> resp = attivC.createAttivitaAbbonamento(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

	@Test
	@Order(3)
	public void createAttivitaAbbonamento1ErroreTest() {
		AttivitaReq req = new AttivitaReq();
		req.setId(2);
		req.setAbbonamentID(1);
		ResponseEntity<Resp> resp = attivC.createAttivitaAbbonamento(req);
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
	}

	@Test
	@Order(4)
	public void deleteAttivitaAbbonamento1ErroreTest() {
		ResponseEntity<Resp> resp = attivC.deleteAttivitaAbbonamento(1, 2);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

	@Test
	@Order(5)
	public void deleteAbbonamentoTest() {
		ResponseEntity<Resp> resp = abboC.delete(1);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
	}
	
}
