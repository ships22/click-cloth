package com.clickndcloth.server_side.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:8100")
public class ClientController {
	
	@Autowired
	private ClientManager clientManager;

	@GetMapping(value = "/clients")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public List<ClientDto> getAllClient() {
		return clientManager.getAllClient();
	}
	
	
	@GetMapping(value = "/client_by_id/{id}")
//	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN') or hasRole('ROLE_CLIENT')")
	public ClientDto getById(@PathVariable ("id") Integer id) {
		return clientManager.findClientById(id);
	}
	
	@GetMapping(value = "/client_by_email/{email}")
//	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN') or hasRole('ROLE_CLIENT')")
	public ClientDto getByEmail(@PathVariable ("email") String email) {
		return clientManager.findClientByEmail(email);
	}
	
	@PostMapping(value = "/add_client/{password}", produces = "application/json")
	public String addClient(@RequestBody Client client, @PathVariable ("password") String password) {
		return clientManager.addClient(client, password);
	}
	
	@PutMapping(value = "/update_client")
//	@PreAuthorize("hasRole('ROLE_CLIENT')")
	public ClientDto updateClient(@RequestBody Client client) {
		return clientManager.updateClient(client);
	}
	
	@DeleteMapping(value = "/delete_client/{id}")
//	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN') or hasRole('ROLE_CLIENT')")
	public String deleteClient(@PathVariable ("id") Integer id) {
		return clientManager.deleteClient(id);
	}
}
