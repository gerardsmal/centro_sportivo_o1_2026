package com.betacom.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.models.Attivita;

public interface IAttivitaRepository extends JpaRepository<Attivita, Integer>{
	Boolean existsByDescription(String desc);
	
}
