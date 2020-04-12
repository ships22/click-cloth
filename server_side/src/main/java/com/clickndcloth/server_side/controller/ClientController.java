package com.clickndcloth.server_side.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clickndcloth.server_side.application.ClientManager;
import com.clickndcloth.server_side.dto.ClientDto;

@RestController
@RequestMapping(value="/api")
public class ClientController {
	
	@Autowired
	private ClientManager clientManager;

	@GetMapping(value="/clients")
	public List<ClientDto> getAllClient() {
		return clientManager.getAllClient();
	}
}
