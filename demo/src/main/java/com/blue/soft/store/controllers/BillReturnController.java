package com.blue.soft.store.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blue.soft.store.entity.BillReturn;
import com.blue.soft.store.entity.BillReturnItem;
import com.blue.soft.store.entity.Client;
import com.blue.soft.store.entity.Item;
import com.blue.soft.store.entity.ItemMove;
import com.blue.soft.store.entity.User;
import com.blue.soft.store.service.BankService;
import com.blue.soft.store.service.BillReturnItemsService;
import com.blue.soft.store.service.BillReturnService;
import com.blue.soft.store.service.ClientService;
import com.blue.soft.store.service.ItemMoveService;
import com.blue.soft.store.service.ItemService;
import com.blue.soft.store.service.UserService;

@Controller
public class BillReturnController {

	@Autowired
	ItemService itemService;

	@Autowired
	ItemMoveService itemMoveService;

	@Autowired
	BillReturnService billReturnService;

	@Autowired
	ClientService clientService;

	@Autowired
	BillReturnItemsService billReturnItemsService;

	@Autowired
	BankService bankService;

	@Autowired
	BankController bankController;

	@Autowired
	UserService userService;

	@Autowired
	HttpSession httpSession;

	// عرض الفورم بتاعت ادخال بيانات الفاتورة
	@RequestMapping("/show-return-bill-info")
	public String showReturnBillInfo(Model theModel) {

		String userId = httpSession.getAttribute("id").toString();

		BillReturn billReturn = new BillReturn();

		billReturn.setDate(LocalDate.now().toString());

		BillReturn lastBillReturn = billReturnService.getBillReturnBySaverId(userId);

		if (lastBillReturn == null) {

			theModel.addAttribute("clientsList", clientService.getAllClients());

			return "return-bill-info";
		}

		else {

			return "redirect:/show-add-to-return-bill";
		}

	}

	@RequestMapping("/show-add-to-return-bill")
	public String showAddToReturnBill(Model theModel) {

		theModel.addAttribute("item", new Item());

		String userId = httpSession.getAttribute("id").toString();

		BillReturn billReturn = billReturnService.getBillReturnBySaverId(userId);

		float total = billReturn.getTotal();

		theModel.addAttribute("total", total);

		// علشان اختار منها
		theModel.addAttribute("itemsList", itemService.getAllItems());
		theModel.addAttribute("billReturn", billReturn);

		return "return-bill";
	}

	// حفظ ببيانات الفاتورة
	@RequestMapping("/save-return-bill-info")
	public String saveReturnBillInfo(@RequestParam(name = "clientId") String clientId) {

		String userId = httpSession.getAttribute("id").toString();

		User theUser = userService.getUserById(userId);

		BillReturn lastBillReturn = billReturnService.getBillReturnBySaverId(userId);

		if (lastBillReturn == null) {

			BillReturn billReturn = new BillReturn();

			billReturn.setDate(LocalDate.now().toString());

			billReturn.setUser(theUser);

			billReturn.setClient(clientService.getClientById(clientId));

			billReturn.setSaver(theUser);

			billReturnService.saveReturnBill(billReturn);
		}

		return "redirect:/show-add-to-return-bill";

	}

	@RequestMapping("/view-return-bill")
	public String viewreturnBill(@RequestParam("returnBillId") String returnBillId, Model theModel) {

		BillReturn billReturn = billReturnService.getBillReturnById(returnBillId);

		float total = billReturn.getTotal();

		theModel.addAttribute("view", true);
		theModel.addAttribute("total", total);
		theModel.addAttribute("item", new Item());
		theModel.addAttribute("billReturn", billReturn);
		theModel.addAttribute("updateItem", new BillReturnItem());
		theModel.addAttribute("itemsList", itemService.getAllItems());

		return "update-return-bill";
	}

	// اضافة فاتورة شراء
	@RequestMapping("/add-item-to-return-bill")
	public String addItemToReturnBill(@ModelAttribute(name = "item") Item item, Model theModel) throws Exception {

		BillReturnItem billReturnItem = new BillReturnItem();
		Item theItem = itemService.getItemById(item.getId());

		if (item.getQuantity() > 0 && item.getSellPrice() > 0) {

			String userId = httpSession.getAttribute("id").toString();

			BillReturn billReturn = billReturnService.getBillReturnBySaverId(userId);

			billReturnItem.setItem(theItem);
			billReturnItem.setReturnPrice(item.getSellPrice());

			System.out.println("billReturnItem >> Return Price >> " + billReturnItem.getReturnPrice());

			billReturnItem.setQuantity(item.getQuantity());

			billReturnItem.setBillReturn(billReturn);

			billReturnItemsService.addBillReturnItem(billReturnItem);

		}

		else if (item.getSellPrice() <= 0) {

			throw new Exception("من فضلك ادخل السعر بشكل صحيح");
		}

		else if (item.getQuantity() <= 0) {

			throw new Exception("من فضلك ادخل الكمية بشكل صحيح");
		}

		return "redirect:/show-add-to-return-bill";
	}

	@RequestMapping("/show-return-bill-list")
	public String showReturnBillList(Model theModel, RedirectAttributes attributes) {

		String userId = httpSession.getAttribute("id").toString();

		BillReturn billReturn = billReturnService.getBillReturnByUpdaterId(userId);

		if (billReturn != null) {

			attributes.addAttribute("returnBillId", billReturn.getId());

			return "redirect:/show-update-return-bill";

		}

		theModel.addAttribute("clientsList", clientService.getAllClients());

		theModel.addAttribute("billReturnList", billReturnService.getAllReturnBills());

		return "return-bill-list";
	}

	@RequestMapping("/delete-returnBill")
	public String deleteReturnBill(@RequestParam(name = "returnBillId") String returnBillId) {

		billReturnService.deleteReturnBill(returnBillId);

		return "redirect:/show-return-bill-info";

	}

	@RequestMapping("/delete-returnBillItem")
	public String deleteReturnBillItem(@RequestParam(name = "returnBillItemId") String returnBillItemId) {

		billReturnItemsService.deleteBillReturnItem(returnBillItemId);

		return "redirect:/show-add-to-return-bill";

	}

	@RequestMapping("/save-returnBill")
	public String saveReturnBillItem(@RequestParam(name = "returnBillId") String returnBillId) {

		BillReturn billReturn = billReturnService.getBillReturnById(returnBillId);

		List<BillReturnItem> billReturnItemsList = billReturn.getBillReturnItems();

		if (billReturnItemsList.isEmpty()) {

			return "redirect:/show-add-to-return-bill";
		}

		float total = 0;

		for (BillReturnItem billReturnItem : billReturnItemsList) {

			Item item = billReturnItem.getItem();

			item.setQuantity(item.getQuantity() + billReturnItem.getQuantity());

			itemService.addNewItem(item);

			total += billReturnItem.getReturnPrice() * billReturnItem.getQuantity();

			itemMoveService.addItemMove(new ItemMove(item, "مرتجع عميل", billReturn.getId(),
					billReturnItem.getQuantity(), item.getQuantity(), 0, billReturnItem.getReturnPrice()));
		}

		Client client = billReturn.getClient();
		client.setDrawee(client.getDrawee() + total);
		billReturn.setClient(client);

		billReturn.setSaver(null);

		billReturnService.saveReturnBill(billReturn);

		return "redirect:/show-return-bill-list";

	}

	@RequestMapping("/search-return-bill-by-clientId")
	public String searchForReturnBillByClientId(@RequestParam(name = "clientId") String clientId, Model theModel) {

		theModel.addAttribute("clientsList", clientService.getAllClients());

		theModel.addAttribute("billReturnList", clientService.getClientById(clientId).getBillReturnList());

		return "return-bill-list";
	}

	@RequestMapping("/search-return-bill-by-id")
	public String searchForReturnBillById(@RequestParam(name = "billId") String billId, Model theModel) {

		theModel.addAttribute("clientsList", clientService.getAllClients());

		theModel.addAttribute("billReturnList", billReturnService.getBillReturnContainingId(billId));

		return "return-bill-list";
	}

	@RequestMapping("/show-print-return-bill")
	public String showPrintReturnBill(@RequestParam(name = "returnBillId") String returnBillId, Model theModel) {

		BillReturn billReturn = billReturnService.getBillReturnById(returnBillId);

		theModel.addAttribute("billReturn", billReturn);

		theModel.addAttribute("total", billReturn.getTotal());

		return "print-return-bill";
	}
}
