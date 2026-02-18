package com.betacom.jpa.dto.outputs;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Builder
public class CertificatoDTO {
	private Integer id;
	private Boolean tipo; // false normale true agosnistico
	private LocalDate dataCertificato;
	
}
