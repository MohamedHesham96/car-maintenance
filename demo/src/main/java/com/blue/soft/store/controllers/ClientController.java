package com.blue.soft.store.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blue.soft.store.entity.Client;
import com.blue.soft.store.entity.Company;
import com.blue.soft.store.entity.Client;
import com.blue.soft.store.service.clientService;

@Controller
public class ClientController {

	@Autowired
	private HttpSession httpSession;

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

}
