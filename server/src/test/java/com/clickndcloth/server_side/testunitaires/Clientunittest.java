package com.clickndcloth.server_side.testunitaires;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.clickndcloth.server_side.application.ClientManager;
import com.clickndcloth.server_side.models.Categories;
import com.clickndcloth.server_side.models.Client;
import com.clickndcloth.server_side.models.Reservation;
import com.clickndcloth.server_side.models.User;
import com.clickndcloth.server_side.services.ClientDomainServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Clientunittest {
	
	@Autowired
	private ClientManager clientManager;
	
	@MockBean
	private ClientDomainServiceImpl clientService;
	
	// test get all category -
			@Test
			void testGetAllClient() {
				Client client = new Client();
				client.setId(99);
				client.setFirst_name("test_f_name");
				client.setLast_name("test_l_name");
				client.setEmail("mail@mail.com");
				client.setPhone("060000000");
				client.setZip_code(95000);
				client.setHouse_no(33);
				client.setStreet("test street");
				client.setCountry("Test Country");
				client.setReservations(null);
				when(clientService.getAllClient()).thenReturn((List<Client>) Stream.of(client).collect(Collectors.toList()));
				assertEquals(1 , clientManager.getAllClient().size());
			}
			
			// test get add category -
			@Test 
			void testAddCleint() {
				Client client = new Client();
				client.setFirst_name("test_f_name");
				client.setLast_name("test_l_name");
				client.setEmail("mail@mail.com");
				client.setPhone("060000000");
				client.setZip_code(95000);
				client.setHouse_no(33);
				client.setStreet("test street");
				client.setCountry("Test Country");
				client.setReservations(null);
				when(clientService.addClient(client)).thenReturn(client);
				assertEquals(client, clientService.addClient(client));
			}
			
			// test get delete category -
			@Test
			void testDeleteClient() {
				int idClient = 5;
				clientManager.deleteClient(idClient);
				verify(clientService, times(1)).deleteClient(idClient);
			}

}
