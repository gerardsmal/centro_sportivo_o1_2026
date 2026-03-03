package com.betacom.jpa;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.betacom.jpa.abbonamento.AbbonamentoControllerTest;
import com.betacom.jpa.attivita.AttivitaAbbonamentoTest;
import com.betacom.jpa.attivita.AttivitaTest;
import com.betacom.jpa.cetificato.CertificatoControllerTest;
import com.betacom.jpa.socio.SocioControlerTest;
import com.betacom.jpa.socio.SocioServicesTest;

@Suite
@SelectClasses({
	SocioServicesTest.class,
	SocioControlerTest.class,
	CertificatoControllerTest.class,
	AbbonamentoControllerTest.class,
	AttivitaTest.class,
	AttivitaAbbonamentoTest.class
})

public class SuiteTest {
}
