package br.com.cardif.sms.services;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

@Configurable
@Service
public class TestService {
	public void init()
	{
		System.out.println("TestService Iniciado");
	}
}
