package com.clickndcloth.server_side.services;

import java.util.List;
import java.util.Optional;

import com.clickndcloth.server_side.models.Client;

public interface ClientDomain {
	
	public List<Client> getAllClient();
	
	public Client addClient(Client client);
	
	public Client updateClient(Client client);
	
	public Optional<Client> getById(Integer id);
	
	public void deleteClient(Integer id);

}
