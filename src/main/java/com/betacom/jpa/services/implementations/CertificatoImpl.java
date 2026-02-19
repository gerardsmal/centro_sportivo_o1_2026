package com.betacom.jpa.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.CertificatoReq;
import com.betacom.jpa.dto.outputs.CertificatoDTO;
import com.betacom.jpa.dto.outputs.SocioDTO;
import com.betacom.jpa.exceptions.AcademyException;
import com.betacom.jpa.models.Certificato;
import com.betacom.jpa.models.Socio;
import com.betacom.jpa.repositories.ICertificatoRepository;
import com.betacom.jpa.repositories.ISocioRepository;
import com.betacom.jpa.services.interfaces.ICertificatoServices;

import static com.betacom.jpa.utilities.Utils.stringToDate;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CertificatoImpl implements ICertificatoServices{
	private final ICertificatoRepository repoC;
	private final ISocioRepository repoS;
	
	public CertificatoImpl(ICertificatoRepository repoC, ISocioRepository repoS) {
		this.repoC = repoC;
		this.repoS = repoS;
	}
	
	@Transactional (rollbackFor = AcademyException.class)
	@Override
	public void create(CertificatoReq req) throws Exception {
		log.debug("create {}", req);
		Socio soc = repoS.findById(req.getSocioID())
				.orElseThrow(() -> new AcademyException("Socio non trovato :" + req.getSocioID()));
		Certificato cert = new Certificato();
		cert.setTipo((req.getTipo() == null) ? false : true);
		cert.setDataCertificato(stringToDate(req.getDataCertificato()));
		cert.setSocio(soc);
		
		repoC.save(cert);
		
	}

	@Override
	public List<SocioDTO> listSocio() throws Exception {
		log.debug("listSocio");
		List<Certificato> lC = repoC.findAll();
		return lC.stream()
				.map((c -> SocioDTO.builder()
						.id(c.getSocio().getId())
						.nome(c.getSocio().getNome())
						.cognome(c.getSocio().getCognome())
						.codiceFiscale(c.getSocio().getCodiceFiscale())
						.email(c.getSocio().getEmail())
						.certificato(CertificatoDTO.builder()
								.id(c.getId())
								.tipo(c.getTipo())
								.dataCertificato(c.getDataCertificato())
								.build())
						.build()))
				.toList();
	}



}
