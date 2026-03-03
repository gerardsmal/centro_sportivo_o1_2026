package com.betacom.jpa.attivita;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.betacom.jpa.controllers.AttivitaController;
import com.betacom.jpa.dto.inputs.AttivitaReq;
import com.betacom.jpa.dto.outputs.AttivitaDTO;
import com.betacom.jpa.dto.outputs.SocioDTO;
import com.betacom.jpa.response.Resp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AttivitaTest {

	@Autowired
	AttivitaController attivC;
	
	@Test
	@Order(1)
	public void create() {
		List<String> lAttiv = List.of("attiv-1", "attiv-2", "attiv-3", "attiv-4");
		for (String a:lAttiv) {
			AttivitaReq req = new AttivitaReq();
			req.setDescription(a);
			ResponseEntity<Resp> resp = attivC.create(req);
			assertEquals(HttpStatus.OK, resp.getStatusCode());
		}
	}
	@Test
	@Order(2)
	public void createError() {
		AttivitaReq req = new AttivitaReq();
		req.setDescription("attiv-4");
		ResponseEntity<Resp> resp = attivC.create(req);
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		Assertions.assertThat(r.getMsg()).isEqualTo("Attivita presente in db:attiv-4");
	}

	@Test
	@Order(3)
	public void update() {
		AttivitaReq req = new AttivitaReq();
		req.setDescription("attiv_updated");
		req.setId(3);
		ResponseEntity<Resp> resp = attivC.update(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		
	}
	
	@Test
	@Order(4)
	public void updateError() {
		AttivitaReq req = new AttivitaReq();
		req.setDescription("attiv_updated");
		req.setId(30);
		ResponseEntity<Resp> resp = attivC.update(req);
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
		
	}
	@Test
	@Order(5)
	public void delete() {
		AttivitaReq req = new AttivitaReq();
		ResponseEntity<Resp> resp = attivC.delete(3);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		
	}

	@Test
	@Order(6)
	public void deleteError() {
		AttivitaReq req = new AttivitaReq();
		ResponseEntity<Resp> resp = attivC.delete(30);
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
		
	}
	
	@Test
	@Order(7)
	public void list() {
		ResponseEntity<?> resp = attivC.list();
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Object body = resp.getBody();
		
		List<AttivitaDTO> lS = (List<AttivitaDTO>) body;
		
		Assertions.assertThat(lS.size()).isGreaterThan(0);
		lS.forEach(s -> log.debug(s.toString()));
	}
}
