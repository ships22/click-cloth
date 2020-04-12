package com.clickndcloth.server_side.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clickndcloth.server_side.models.Client;
import com.clickndcloth.server_side.repository.ClientRepository;

@Component
public class ClientDomainServiceImpl implements ClientDomain {
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	@Override
	public List<Client> getAllClient() {
		return clientRepository.findAll();
	}

}
