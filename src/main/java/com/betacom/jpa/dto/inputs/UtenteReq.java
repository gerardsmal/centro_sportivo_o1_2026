package com.betacom.jpa.dto.inputs;

import com.betacom.jpa.enums.Roles;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UtenteReq {
	private String userName;
	private String pwd;
	private String email;
	private String role; 

}
