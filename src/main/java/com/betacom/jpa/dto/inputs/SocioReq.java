package com.betacom.jpa.dto.inputs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SocioReq {
	private Integer id;
	private String cognome;
	private String nome;
	private String codiceFiscale;
	private String email;
}
