package com.betacom.jpa.cetificato;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.betacom.jpa.controllers.CertificatoController;
import com.betacom.jpa.dto.inputs.CertificatoReq;
import com.betacom.jpa.dto.outputs.CertificatoDTO;
import com.betacom.jpa.dto.outputs.SocioDTO;
import com.betacom.jpa.response.Resp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class CertificatoControllerTest {
	@Autowired
	private  CertificatoController certC;
	
	@Test
	@Order(1)	
	public void createCertTest() {
		log.debug("create certificato");
		CertificatoReq req = new CertificatoReq();
		req.setDataCertificato(LocalDate.now());
		req.setTipo(false);
		req.setSocioID(1);
		
		ResponseEntity<Resp> resp = certC.create(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		
		Assertions.assertThat(r.getMsg()).isEqualTo("rest_created");
		
	}
	@Test
	@Order(2)
	public void updateCertTest() {
		log.debug("update certificato");
		CertificatoReq req = new CertificatoReq();
		req.setTipo(false);
		req.setId(1);
		
		ResponseEntity<Resp> resp = certC.update(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		
		Assertions.assertThat(r.getMsg()).isEqualTo("rest_updated");
		
	}
	@Test
	@Order(3)
	public void updateCertErrorTest() {
		log.debug("update certificato");
		CertificatoReq req = new CertificatoReq();
		req.setTipo(false);
		req.setDataCertificato(LocalDate.now());
		req.setId(1);
		
		ResponseEntity<Resp> resp = certC.update(req);
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
		
		
	}

	@Test
	@Order(3)
	public void listTest() {
		ResponseEntity<?> resp = certC.list();
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Object body = resp.getBody();
		
		List<SocioDTO> lC = (List<SocioDTO>) body;
		
		Assertions.assertThat(lC.size()).isGreaterThan(0);
		Assertions.assertThat(lC.get(0).getId()).isEqualTo(1);
	}

}
