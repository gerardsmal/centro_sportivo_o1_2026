package com.betacom.jpa.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name="abbonamento_socio")
public class Abbonamento {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column (
			name="data_inscrizione",
			nullable = false
			)
	private LocalDate dataInscizione;
	
	@ManyToOne
	@JoinColumn (name="id_socio")
	private Socio socio;
	
}
