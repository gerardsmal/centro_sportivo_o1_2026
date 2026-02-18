package com.betacom.jpa.dto.outputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class SocioDTO {
	private Integer id;
	private String cognome;
	private String nome;
	private String codiceFiscale;
	private String email;

}
