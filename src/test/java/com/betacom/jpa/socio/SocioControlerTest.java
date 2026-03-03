package com.betacom.jpa.socio;

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

import com.betacom.jpa.controllers.SocioController;
import com.betacom.jpa.dto.inputs.SocioReq;
import com.betacom.jpa.dto.outputs.SocioDTO;
import com.betacom.jpa.models.Socio;
import com.betacom.jpa.response.Resp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SocioControlerTest {
	@Autowired
	private SocioController socioC;
	
	
	@Test
	@Order(1)		
	public void getSocio() {
		log.debug("Test getSocio");
		ResponseEntity<?> resp = socioC.findById(1);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		SocioDTO soc = (SocioDTO)resp.getBody();
		Assertions.assertThat(soc.getNome()).isEqualTo("Paolo");
	}
	@Test
	@Order(2)	
	public void getSocioError() {
		log.debug("Test getSocio error");
		ResponseEntity<?> resp = socioC.findById(99);
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
		Assertions.assertThat(resp.getBody()).isEqualTo("Socio non trovato in DB:99");
	}
	@Test
	@Order(3)	
	public void createSocio() {

		log.debug("Create socio");
		SocioReq req = new SocioReq();
		req.setNome("Anna");
		req.setCognome("LaBella");
		req.setCodiceFiscale("AB009");
		req.setEmail("a.bella@gmail.com");
		
		ResponseEntity<Resp> resp = socioC.create(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		
		Assertions.assertThat(r.getMsg()).isEqualTo("rest_created");
		
	}

	@Test
	@Order(4)	
	public void updateSocio() {
		log.debug("******* Update socio  *******");
		
		SocioReq req = new SocioReq();
		req.setId(3);
		req.setCognome("LaBrutta");
		req.setEmail("update@gmail.com");
		req.setNome("Paola");
		req.setCodiceFiscale("UPD001");
		
		ResponseEntity<Resp> resp = socioC.update(req);
	
		
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg()).isEqualTo("rest_updated");
		
				
	}
	@Test
	@Order(5)	
	public void updateSocioError() {
		log.debug("******* Update socio error  *******");
		
		SocioReq req = new SocioReq();
		req.setId(30);
		req.setCognome("LaBrutta");
		req.setEmail("update@gmail.com");
		req.setNome("Paola");
		req.setCodiceFiscale("UPD001");
		
		ResponseEntity<Resp> resp = socioC.update(req);
	
		
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
		
				
	}
	@Test
	@Order(6)	
	public void deleteSocio() {
		log.debug("******* delete socio  *******");
		
		
		ResponseEntity<Resp> resp = socioC.delete(3);
	
		
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg()).isEqualTo("rest_deleted");
		
				
	}
	
	@Test
	@Order(7)	
	public void deleteSocioError() {
		log.debug("******* delete socio  *******");
		
		
		ResponseEntity<Resp> resp = socioC.delete(30);
	
		
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());		
				
	}
	
	@Test
	@Order(8)	
	public void list() {
		log.debug("Test list socio");
		
		ResponseEntity<?> resp = socioC.list(null, null, null, null);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Object body = resp.getBody();
		
		List<SocioDTO> lS = (List<SocioDTO>) body;
		
		Assertions.assertThat(lS.size()).isGreaterThan(0);
	//	Assertions.assertThat(lS.get(0).getCognome()).isEqualTo("Rossi");
		lS.forEach(s -> log.debug(s.toString()));
		// updateSocio();
	}

}
