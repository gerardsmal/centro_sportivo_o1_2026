package com.betacom.jpa.socio;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.jpa.dto.inputs.SocioReq;
import com.betacom.jpa.dto.outputs.SocioDTO;
import com.betacom.jpa.services.interfaces.ISocioServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SocioServicesTest {
	
	@Autowired
	private ISocioServices socioS;
	
	@Test
	@Order(1)
	public void createSocioTest() {
		log.debug("create socio");
		try {
			SocioReq req = new SocioReq();
			req.setNome("Paolo");
			req.setCognome("Verde");
			req.setCodiceFiscale("PV001");
			req.setEmail("p.verde@gmail.com");
		
			socioS.create(req);
			
			List<SocioDTO> lS = socioS.find(null, null, null, null);
			SocioDTO createSocio = lS.stream()
					.filter(s -> "PV001".equals(s.getCodiceFiscale()))
					.findFirst()
					.orElseThrow(() -> new AssertionError("Socio non trovato"));

			Assertions.assertThat(createSocio.getCognome()).isEqualTo("Verde");
			
			req = new SocioReq();
			req.setNome("Andrea");
			req.setCognome("Rossi");
			req.setCodiceFiscale("AR001");
			req.setEmail("a.rossi@gmail.com");
		
			socioS.create(req);
			
			lS = socioS.find(null, null, null, null);
			createSocio = lS.stream()
					.filter(s -> "AR001".equals(s.getCodiceFiscale()))
					.findFirst()
					.orElseThrow(() -> new AssertionError("Socio non trovato:AR001" ));

			Assertions.assertThat(createSocio.getCognome()).isEqualTo("Rossi");
			
			lS.forEach(s -> log.debug(s.toString()));
			
		} catch (Exception e) {
			
			log.error(e.getMessage());
		}
		
	}
	
	@Test
	@Order(2)
	public void createSocioErrorTest() {
		log.debug("create socio in error");
		
		SocioReq req = new SocioReq();
		req.setNome("Paolo");
		req.setCognome("Verde");
		req.setCodiceFiscale("PV001");
		req.setEmail("p.verde@gmail.com");
	
		assertThrows(Exception.class, () -> {
			socioS.create(req);
		});
		
	}
}
