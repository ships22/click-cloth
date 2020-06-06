package com.clickndcloth.server_side.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickndcloth.server_side.config.SecurityConfig;
import com.clickndcloth.server_side.dto.ClientDto;

import com.clickndcloth.server_side.models.Client;
import com.clickndcloth.server_side.models.User;
import com.clickndcloth.server_side.services.ClientDomainServiceImpl;
import com.clickndcloth.server_side.services.UserDomainServiceImpl;

@Service
public class ClientManager {

	@Autowired
	private ClientDomainServiceImpl clientDomainService;
	
	@Autowired
	private UserDomainServiceImpl userDomainService;
	
	@Autowired
	private SecurityConfig securityConfig;
	
	@Transactional
	public List<ClientDto>getAllClient() {
		List<Client>clientList = clientDomainService.getAllClient();
		List<ClientDto>clientDtos = new ArrayList<ClientDto>();
		clientList.forEach(client -> {
			ClientDto clientDto = new ClientDto();
			clientDto.setId(client.getId());
			clientDto.setFirst_name(client.getFirst_name());
			clientDto.setLast_name(client.getLast_name());
			clientDto.setEmail(client.getEmail());
			clientDto.setPhone(client.getPhone());
			clientDto.setHouse_no(client.getHouse_no());
			clientDto.setStreet(client.getStreet());
			clientDto.setZip_code(client.getZip_code());
			clientDto.setCountry(client.getCountry());
			clientDtos.add(clientDto);
		});
		return clientDtos;
	}
	
	@Transactional
	public ClientDto findClientById(Integer id) {
		Optional<Client> client = clientDomainService.findClientById(id);
		ClientDto clientDto = new ClientDto();
		clientDto.setId(client.get().getId());
		clientDto.setFirst_name(client.get().getFirst_name());
		clientDto.setLast_name(client.get().getLast_name());
		clientDto.setEmail(client.get().getEmail());
		clientDto.setPhone(client.get().getPhone());
		clientDto.setHouse_no(client.get().getHouse_no());
		clientDto.setStreet(client.get().getStreet());
		clientDto.setZip_code(client.get().getZip_code());
		clientDto.setCountry(client.get().getCountry());
		return clientDto;
	}
	
	@Transactional
	public String addClient(Client client, String password) {
		if(userDomainService.getByEmail(client.getEmail()) ==  null) {
			
			Client addedClient = clientDomainService.addClient(client);
			String encodedPassword = securityConfig.passwordEncoder().encode(password);
			
			User newUser = new User();
			newUser.setEmail(addedClient.getEmail());
			newUser.setPassword(encodedPassword);
			newUser.setRoles("ROLE_CLIENT");
			newUser.setIs_active(1);
			newUser.setClient_id_client(addedClient.getId());
			userDomainService.addUser(newUser);
			
			ClientDto clientDto = new ClientDto();
			clientDto.setId(addedClient.getId());
			clientDto.setFirst_name(addedClient.getFirst_name());
			clientDto.setLast_name(addedClient.getLast_name());
			clientDto.setEmail(addedClient.getEmail());
			clientDto.setPhone(addedClient.getPhone());
			clientDto.setHouse_no(addedClient.getHouse_no());
			clientDto.setStreet(addedClient.getStreet());
			clientDto.setZip_code(addedClient.getZip_code());
			clientDto.setCountry(addedClient.getCountry());
			return "account created";
		}
		System.out.println("User exists with mail id : " + client.getEmail());
		return "e-mail déjà utilisé";
	}
	
	@Transactional
	public ClientDto updateClient(Client client) {
		Client updatedClient = clientDomainService.updateClient(client);
		ClientDto clientDto = new ClientDto();
		clientDto.setId(updatedClient.getId());
		clientDto.setFirst_name(updatedClient.getFirst_name());
		clientDto.setLast_name(updatedClient.getLast_name());
		clientDto.setEmail(updatedClient.getEmail());
		clientDto.setPhone(updatedClient.getPhone());
		clientDto.setHouse_no(updatedClient.getHouse_no());
		clientDto.setStreet(updatedClient.getStreet());
		clientDto.setZip_code(updatedClient.getZip_code());
		clientDto.setCountry(updatedClient.getCountry());
		return clientDto;
	}
	
	@Transactional
	public String deleteClient(Integer id) {
		clientDomainService.deleteClient(id);
		return "Client with id : " + id + " has been deleted succesfully";
	}
	
}
