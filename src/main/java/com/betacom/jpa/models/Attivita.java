package com.betacom.jpa.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table (name="attivita")
public class Attivita {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column (
			name="descrizione",
			nullable = false,
			length = 100,
			unique = true
			)
	private String description;
	
	@ManyToMany (
			mappedBy = "attivitas",
			fetch = FetchType.EAGER
			)
	List<Abbonamento> abbonamentos;
}
