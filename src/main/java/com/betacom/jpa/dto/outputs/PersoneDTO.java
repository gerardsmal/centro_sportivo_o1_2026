package com.betacom.jpa.dto.outputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PersoneDTO {
	private Integer id;
	private String nome;
	private String cognome;
	private String email;
	private String colore;
}
