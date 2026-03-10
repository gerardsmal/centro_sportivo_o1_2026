package com.betacom.jpa.dto.outputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UtenteDTO {

	private String userName;
	private String pwd;
	private String email;
	private String role; 

}
