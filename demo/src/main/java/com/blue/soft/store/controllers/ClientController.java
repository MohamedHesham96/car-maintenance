package com.blue.soft.store.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blue.soft.store.entity.Client;
import com.blue.soft.store.entity.Collect;
import com.blue.soft.store.service.clientService;

@Controller
public class ClientController {

	@Autowired
	clientService clientService;

	@RequestMapping("clients-list")
	public String showClientsList(Model theModel) {

		theModel.addAttribute("client", new Client());
		theModel.addAttribute("clientsList", clientService.getAllClients());

		return "clients-list";
	}

	@RequestMapping("/add-new-client")
	public String addNewClient(@ModelAttribute(name = "client") Client theClient, Model theModel) {

		clientService.addNewClient(theClient);

		return "redirect:/clients-list";
	}

	@RequestMapping("/search-for-client")
	public String searchForClient(@ModelAttribute(name = "clientName") String theClientName, Model theModel) {

		theModel.addAttribute("client", new Client());
		System.out.println(clientService.searchForClient(theClientName).get(0).getName());
		theModel.addAttribute("clientsList", clientService.searchForClient(theClientName));

		return "clients-list";
	}

	@RequestMapping("/show-collects-client")
	public String showCollectsClient(@RequestParam(name = "clientId") String clientId, Model theModel) {

		theModel.addAttribute("client", clientService.getClientById(clientId));

		return "client-collect";
	}

	@RequestMapping("/add-client-collect")
	public String addClientCollect(@RequestParam(name = "clientId") String clientId,
			@RequestParam(name = "amount") float amount, Model theModel) throws Exception {
		Client client = clientService.getClientById(clientId);

		if (amount > client.getDrawee())
			throw new Exception("مبلغ التحصيل اكبر من الدين");

		Collect collect = new Collect();
		collect.setAmount(amount);
		collect.setDate(LocalDate.now().toString());
		collect.setClient(client);
		collect.setAmount(amount);
		client.addCollect(collect);
		client.setDrawee(client.getDrawee() - amount);
		clientService.addNewClient(client);

		theModel.addAttribute("client", client);

		return "client-collect";
	}

}
