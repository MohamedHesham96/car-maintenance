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
import com.blue.soft.store.service.BankService;
import com.blue.soft.store.service.ClientService;
import com.blue.soft.store.service.CollectService;

@Controller
public class ClientController {

	@Autowired
	ClientService clientService;

	@Autowired
	BankService bankService;

	@Autowired
	CollectService collectService;

	@Autowired
	BankController bankController;

	@RequestMapping("clients-list")
	public String showClientsList(Model theModel) {

		theModel.addAttribute("client", new Client());
		theModel.addAttribute("clientsList", clientService.getAllClients());
		theModel.addAttribute("clients_list", "active");

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
		theModel.addAttribute("clientsList", clientService.searchForClient(theClientName));
		theModel.addAttribute("clients_list", "active");

		return "clients-list";
	}

	@RequestMapping("/show-collects-client")
	public String showCollectsClient(@RequestParam(name = "clientId") String clientId, Model theModel) {

		theModel.addAttribute("client", clientService.getClientById(clientId));
		theModel.addAttribute("clients_list", "active");

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
		collect.setBalanceNow(client.getDrawee() - amount);
		collect.setDate(LocalDate.now().toString());
		collect.setClient(client);

		client.addCollect(collect);
		client.setDrawee(client.getDrawee() - amount);

//		Bank bank = bankService.getBank();
//		bank.setBalance(bank.getBalance() + collect.getAmount());
//		bank.setBalanceToday(bank.getBalanceToday() + collect.getAmount());
//		bankService.saveBank(bank);

		bankController.updateBankBalance("add", collect.getAmount());

		clientService.addNewClient(client);

		theModel.addAttribute("client", client);
		theModel.addAttribute("clients_list", "active");

		return "client-collect";
	}

	@RequestMapping("/delete-client-collect")
	public String deleteClientCollect(@RequestParam(name = "clientId") String clientId,
			@RequestParam(name = "collectId") String collectId, Model theModel) throws Exception {

		Client client = clientService.getClientById(clientId);

		Collect theCollect = collectService.getCollectById(collectId);

		client.setDrawee(client.getDrawee() + theCollect.getAmount());
		bankController.updateBankBalance("less", theCollect.getAmount());

		collectService.deleteCollectById(collectId);

		theModel.addAttribute("client", client);
		theModel.addAttribute("clients_list", "active");

		return "client-collect";
	}

	@RequestMapping("/show-client-bills-list")
	public String showClientBillsList(@RequestParam(name = "clientId") String ClientId, Model theModel) {

		theModel.addAttribute("client", clientService.getClientById(ClientId));
		theModel.addAttribute("clients_list", "active");

		return "client-sell-bill-list";
	}

}
