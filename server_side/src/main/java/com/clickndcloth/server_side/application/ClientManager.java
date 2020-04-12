package com.clickndcloth.server_side.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickndcloth.server_side.dto.ClientDto;
import com.clickndcloth.server_side.models.Client;
import com.clickndcloth.server_side.services.ClientDomainServiceImpl;

@Service
public class ClientManager {

	@Autowired
	private ClientDomainServiceImpl clientDomainService;
	
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
}
