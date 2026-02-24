package com.betacom.jpa.utilities;

import java.util.List;
import java.util.stream.Collectors;

import com.betacom.jpa.dto.outputs.AbbonamentoDTO;
import com.betacom.jpa.dto.outputs.AttivitaDTO;
import com.betacom.jpa.dto.outputs.CertificatoDTO;
import com.betacom.jpa.dto.outputs.SocioDTO;
import com.betacom.jpa.models.Abbonamento;
import com.betacom.jpa.models.Attivita;
import com.betacom.jpa.models.Socio;

public class Mapper {

	public static List<AbbonamentoDTO> buildAbbonamentoDTO(List<Abbonamento> lA){
		return lA.stream()
				.map(a -> AbbonamentoDTO.builder()
						.id(a.getId())
						.dataInscizione(a.getDataInscizione())
						.attivita(buildAttivitaDTO(a.getAttivitas()))
						.build()						
						).collect(Collectors.toList());
	}

	public static AbbonamentoDTO buildAbbonamentoDTO(Abbonamento a){
		return AbbonamentoDTO.builder()
						.id(a.getId())
						.dataInscizione(a.getDataInscizione())
						.attivita(buildAttivitaDTO(a.getAttivitas()))
						.build();						
	}
	

	
	public static List<AttivitaDTO> buildAttivitaDTO(List<Attivita> lA){
		return lA.stream()
				.map(a -> AttivitaDTO.builder()
						.id(a.getId())
						.description(a.getDescription())
						.build()
						).collect(Collectors.toList());
		
	}
	
	public static List<SocioDTO> buildSocioDTO(List<Socio> lS){
		return lS.stream()
				.map(s -> SocioDTO.builder()
						.id(s.getId())
						.cognome(s.getCognome())
						.nome(s.getNome())
						.codiceFiscale(s.getCodiceFiscale())
						.email(s.getEmail())
						.certificato((s.getCertificato() == null) ? null :CertificatoDTO.builder()
								.id(s.getCertificato().getId())
								.tipo(s.getCertificato().getTipo())
								.dataCertificato(s.getCertificato().getDataCertificato())
								.build())
						.abbonamentos(buildAbbonamentoDTO(s.getAbbonementos()))
						.build()				
						).collect(Collectors.toList());
	}

	public static SocioDTO buildSocioDTO(Socio s){
		return SocioDTO.builder()
						.id(s.getId())
						.cognome(s.getCognome())
						.nome(s.getNome())
						.codiceFiscale(s.getCodiceFiscale())
						.email(s.getEmail())
						.certificato((s.getCertificato() == null) ? null :CertificatoDTO.builder()
								.id(s.getCertificato().getId())
								.tipo(s.getCertificato().getTipo())
								.dataCertificato(s.getCertificato().getDataCertificato())
								.build())
						.abbonamentos(buildAbbonamentoDTO(s.getAbbonementos()))
						.build();				
	}


}
