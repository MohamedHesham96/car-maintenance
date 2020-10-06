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

import com.blue.soft.store.entity.BillSell;
import com.blue.soft.store.entity.BillSellItem;
import com.blue.soft.store.entity.Client;
import com.blue.soft.store.entity.Item;
import com.blue.soft.store.entity.User;
import com.blue.soft.store.service.BankService;
import com.blue.soft.store.service.BillSellItemsService;
import com.blue.soft.store.service.BillSellService;
import com.blue.soft.store.service.ClientService;
import com.blue.soft.store.service.ItemService;
import com.blue.soft.store.service.UserService;

@Controller
public class BillSellController {

	@Autowired
	ItemService itemService;

	@Autowired
	BillSellService billSellService;

	@Autowired
	ClientService clientService;

	@Autowired
	BillSellItemsService billSellItemsService;

	@Autowired
	BankService bankService;

	@Autowired
	BankController bankController;

	@Autowired
	UserService userService;

	@Autowired
	HttpSession httpSession;

	// عرض الفورم بتاعت ادخال بيانات الفاتورة
	@RequestMapping("/show-sell-bill-info")
	public String showSellBillInfo(Model theModel) {

		String userId = httpSession.getAttribute("id").toString();

		BillSell billSell = new BillSell();

		billSell.setDate(LocalDate.now().toString());

		BillSell lastBillSell = billSellService.getBillSellBySaverId(userId);

		if (lastBillSell == null) {

			theModel.addAttribute("clientsList", clientService.getAllClients());

			return "sell-bill-info";
		}

		else {

			return "redirect:/show-add-to-sell-bill";
		}

	}

	// حفظ ببيانات الفاتورة
	@RequestMapping("/save-sell-bill-info")
	public String saveSellBillInfo(@RequestParam(name = "late", defaultValue = "off") String late,
			@RequestParam(name = "clientId") String clientid) {

		String userId = httpSession.getAttribute("id").toString();

		User theUser = userService.getUserById(userId);

		BillSell lastBillSell = billSellService.getBillSellBySaverId(theUser.getId());

		if (lastBillSell == null) {

			BillSell billSell = new BillSell();

			billSell.setDate(LocalDate.now().toString());

			billSell.setClient(clientService.getClientById(clientid));

			billSell.setUser(theUser);

			billSell.setLate("on".equals(late) ? true : false);

			billSell.setSaver(theUser);

			billSellService.saveSellBill(billSell);

		}

		return "redirect:/show-add-to-sell-bill";

	}

	@RequestMapping("/show-add-to-sell-bill")
	public String showAddToSellBill(Model theModel) {

		theModel.addAttribute("item", new Item());

		String userId = httpSession.getAttribute("id").toString();

		BillSell billSell = billSellService.getBillSellBySaverId(userId);

		float total = billSell.getTotal();

		theModel.addAttribute("total", total);

		// علشان اختار منها
		theModel.addAttribute("itemsList", itemService.getAllItems());
		theModel.addAttribute("billSell", billSell);

		return "sell-bill";
	}

	// اضافة فاتورة شراء
	@RequestMapping("/add-item-to-sell-bill")
	public String addSellBill(@ModelAttribute(name = "item") Item item, Model theModel) throws Exception {

		BillSellItem billSellItem = new BillSellItem();
		Item theItem = itemService.getItemById(item.getId());

		if (item.getQuantity() < theItem.getQuantity() && item.getQuantity() > 0) {

			String userId = httpSession.getAttribute("id").toString();

			BillSell billSell = billSellService.getBillSellBySaverId(userId);

			billSellItem.setItem(theItem);
			billSellItem.setBillSell(billSell);
			billSellItem.setSellPrice(theItem.getSellPrice());
			billSellItem.setBuyPrice(theItem.getBuyPrice());
			billSellItem.setQuantity(item.getQuantity());
			billSellItem.setDate();

			billSellItemsService.addBillSellItem(billSellItem);

		} else {

			throw new Exception("Quantity is Not Good !");
		}

		return "redirect:/show-add-to-sell-bill";
	}

	// بيعرض صفحة التعديل على الفاتورة
	@RequestMapping("/view-sell-bill")
	public String viewSellBill(@RequestParam("sellBillId") String sellBillId, Model theModel) {

		BillSell billSell = billSellService.getBillSellById(sellBillId);

		float total = billSell.getTotal();

		theModel.addAttribute("view", true);
		theModel.addAttribute("total", total);
		theModel.addAttribute("item", new Item());
		theModel.addAttribute("billSell", billSell);
		theModel.addAttribute("updateItem", new BillSellItem());
		theModel.addAttribute("itemsList", itemService.getAllItems());

		return "update-sell-bill";
	}

	@RequestMapping("/show-sell-bill-list")
	public String showSellBillList(Model theModel, RedirectAttributes attributes) {
		String userId = httpSession.getAttribute("id").toString();

		BillSell billSell = billSellService.getBillSellByUpdaterId(userId);

		if (billSell != null) {

			attributes.addAttribute("sellBillId", billSell.getId());

			return "redirect:/show-update-sell-bill";

		}

		theModel.addAttribute("clientsList", clientService.getAllClients());

		theModel.addAttribute("billSellList", billSellService.getAllSellBills());

		return "sell-bill-list";
	}

	@RequestMapping("/delete-sellBill")
	public String deleteSellBill(@RequestParam(name = "sellBillId") String sellBillId) {

		billSellService.deleteSellBill(sellBillId);

		return "redirect:/show-sell-bill-info";

	}

	@RequestMapping("/delete-sellBillItem")
	public String deleteSellBillItem(@RequestParam(name = "sellBillItemId") String sellBillItemId) {

		billSellItemsService.deleteBillSellItem(sellBillItemId);

		return "redirect:/show-add-to-sell-bill";

	}

	@RequestMapping("/save-sellBill")
	public String saveSellBillItem(@RequestParam(name = "sellBillId") String sellBillId) {

		BillSell billSell = billSellService.getBillSellById(sellBillId);

		List<BillSellItem> billSellItemsList = billSell.getBillSellItems();

		if (billSellItemsList.isEmpty()) {

			return "redirect:/show-add-to-sell-bill";
		}

		float total = 0;

		for (BillSellItem billSellItem : billSellItemsList) {

			Item item = billSellItem.getItem();

			// تعديل كمية الصنف
			item.setQuantity(item.getQuantity() - billSellItem.getQuantity());

			itemService.addNewItem(item);

			total += billSellItem.getSellPrice() * billSellItem.getQuantity();
		}

		if (billSell.isLate()) {

			Client client = billSell.getClient();
			client.setDrawee(client.getDrawee() + total);
			billSell.setClient(client);

		} else {

			// update the Bank
			bankController.updateBankBalance("add", total);
		}

		billSell.setSaver(null);

		billSellService.saveSellBill(billSell);

		return "redirect:/items-list";

	}

	@RequestMapping("/search-sell-bill-by-clientId")
	public String searchForSellBillByClientId(@RequestParam(name = "clientId") String clientId, Model theModel) {

		theModel.addAttribute("clientsList", clientService.getAllClients());

		theModel.addAttribute("billSellList", clientService.getClientById(clientId).getBillSellList());

		return "sell-bill-list";
	}

	@RequestMapping("/search-sell-bill-by-id")
	public String searchForSellBillById(@RequestParam(name = "billId") String billId, Model theModel) {

		theModel.addAttribute("clientsList", clientService.getAllClients());

		theModel.addAttribute("billSellList", billSellService.getBillSellContainingId(billId));

		return "sell-bill-list";
	}

	@RequestMapping("/show-printView")
	public String showPrintView(@RequestParam(name = "sellBillId") String sellBillId, Model theModel) {

		BillSell billSell = billSellService.getBillSellById(sellBillId);

		theModel.addAttribute("billSell", billSell);

		theModel.addAttribute("total", billSell.getTotal());

		return "print-view";
	}

}
