package com.clickndcloth.server_side.services;

import java.util.List;
import java.util.Optional;

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
	
	@Override
	public Client addClient(Client client) {
		return clientRepository.save(client);
	}
	
	@Override
	public Client updateClient(Client client) {
		return clientRepository.saveAndFlush(client);
	}
	
	@Override
	public Optional<Client> findClientById(Integer id) {
		return clientRepository.findById(id);
	}
	
	@Override
	public void deleteClient(Integer id) {
		 clientRepository.deleteById(id);
	}

}
