package com.clickndcloth.server_side.services;

import java.util.List;
import java.util.Optional;

import com.clickndcloth.server_side.models.Client;

public interface ClientDomain {
	
	List<Client> getAllClient();
	
	Client addClient(Client client);
	
	Client updateClient(Client client);
	
	Optional<Client> findClientById(Integer id);
	
	void deleteClient(Integer id);
	
	Client findByEmail(String email);

}
