package com.betacom.jpa.utilities;

import java.util.List;
import java.util.stream.Collectors;

import com.betacom.jpa.dto.outputs.AbbonamentoDTO;
import com.betacom.jpa.dto.outputs.AttivitaDTO;
import com.betacom.jpa.models.Abbonamento;
import com.betacom.jpa.models.Attivita;

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

}
