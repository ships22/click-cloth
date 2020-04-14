package com.clickndcloth.server_side.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clickndcloth.server_side.application.ClientManager;
import com.clickndcloth.server_side.dto.ClientDto;
import com.clickndcloth.server_side.models.Client;


@RestController
@RequestMapping(value = "/api")
public class ClientController {
	
	@Autowired
	private ClientManager clientManager;

	@GetMapping(value = "/clients")
	public List<ClientDto> getAllClient() {
		return clientManager.getAllClient();
	}
	
	@GetMapping(value = "/client_by_id/{id}")
	public ClientDto getById(@PathVariable ("id") Integer id) {
		return clientManager.getById(id);
	}
	
	@PostMapping(value = "/add_client/{password}", produces = "application/json")
	public ClientDto addClient(@RequestBody Client client, @PathVariable ("password") String password) {
		return clientManager.addClient(client, password);
		
	}
	
	@PutMapping(value = "/update_client")
	public ClientDto updateClient(@RequestBody Client client) {
		return clientManager.updateClient(client);
	}
	
	@DeleteMapping(value = "/delete_client/{id}")
	public String deleteClient(@PathVariable ("id") Integer id) {
		return clientManager.deleteClient(id);
	}
}
