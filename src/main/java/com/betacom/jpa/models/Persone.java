package com.betacom.jpa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name="persone")
public class Persone {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column (length = 100, nullable = false)
	private String nome;

	@Column (length = 100, nullable = false)
	private String cognome;
	@Column (length = 100, nullable = false)
	private String email;

	@Column (length = 10)
	private String colore;

}
