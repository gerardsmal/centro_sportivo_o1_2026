package com.betacom.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.models.MessageID;
import com.betacom.jpa.models.Messaggi;

public interface IMessaggiRepository extends JpaRepository<Messaggi, MessageID>{

}
