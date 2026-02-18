package com.betacom.jpa.dto.outputs;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class AbbonamentoDTO {
	private Integer id;
	private LocalDate dataInscizione;

}
