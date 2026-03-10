package com.betacom.jpa.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.inputs.UtenteReq;
import com.betacom.jpa.dto.outputs.UtenteDTO;
import com.betacom.jpa.enums.Roles;
import com.betacom.jpa.exceptions.AcademyException;
import com.betacom.jpa.models.Utente;
import com.betacom.jpa.repositories.IMessaggiRepository;
import com.betacom.jpa.repositories.IUtenteRepository;
import com.betacom.jpa.services.interfaces.IMessagioServices;
import com.betacom.jpa.services.interfaces.IUtenteServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UtenteImpl implements IUtenteServices{

	private final IUtenteRepository utR;
	private final IMessagioServices msgS;
	
	
	@Override
	public void create(UtenteReq req) throws Exception {
		log.debug("create {}", req);	
		if (utR.existsById(req.getUserName()))
			throw new AcademyException(msgS.get("user_exist"));
		Utente ut = new Utente();
		ut.setUserName(req.getUserName());
		ut.setPwd(req.getPwd());
		ut.setEmail(req.getEmail());
		ut.setRole(Roles.valueOf(req.getRole()));
		utR.save(ut);
		
	}

	@Override
	public void update(UtenteReq req) throws Exception {
		log.debug("update {}", req);	
		Utente ut = utR.findById(req.getUserName())
				.orElseThrow(() -> new AcademyException(msgS.get("user_ntfnd")));
		if (req.getPwd() != null)
			ut.setPwd(req.getPwd());
		if (req.getEmail() != null)
			ut.setEmail(req.getEmail());
		if (req.getRole() != null)
			ut.setRole(Roles.valueOf(req.getRole()));
		utR.save(ut);
		
	}

	@Override
	public void delete(String userName) throws Exception {
		log.debug("delete {}", userName);
		Utente ut = utR.findById(userName)
				.orElseThrow(() -> new AcademyException(msgS.get("user_ntfnd")));

		utR.delete(ut);
	}

	@Override
	public List<UtenteDTO> list() {
		log.debug("list");
		List<Utente> lU = utR.findAll();
		return lU.stream()
				.map((u -> UtenteDTO.builder()
						.userName(u.getUserName())
						.pwd(u.getPwd())
						.role(u.getRole().toString())
						.email(u.getEmail())
						.build())
						).toList();
						
	}

	@Override
	public UtenteDTO getByUserName(String userName) throws Exception {
		log.debug("getByUserName {}", userName);
		Utente u = utR.findById(userName)
				.orElseThrow(() -> new AcademyException(msgS.get("user_ntfnd")));
		
		return UtenteDTO.builder()
				.userName(u.getUserName())
				.pwd(u.getPwd())
				.role(u.getRole().toString())
				.email(u.getEmail())
				.build();
	}

}
