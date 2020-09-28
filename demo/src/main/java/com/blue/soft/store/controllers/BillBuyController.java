package com.blue.soft.store.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blue.soft.store.entity.BillBuy;
import com.blue.soft.store.entity.BillBuyItem;
import com.blue.soft.store.entity.Client;
import com.blue.soft.store.entity.Item;
import com.blue.soft.store.service.BillBuyItemsService;
import com.blue.soft.store.service.BillBuyService;
import com.blue.soft.store.service.ItemService;
import com.blue.soft.store.service.clientService;

@Controller
public class BillBuyController {

	@Autowired
	ItemService itemService;

	@Autowired
	BillBuyService billBuyService;

	@Autowired
	clientService clientService;

	@Autowired
	BillBuyItemsService billBuyItemsService;

	// عرض الفورم بتاعت ادخال بيانات الفاتورة
	@RequestMapping("/show-buy-bill-info")
	public String showBuyBillInfo(Model theModel) {

		BillBuy billBuy = new BillBuy();

		billBuy.setDate(LocalDate.now().toString());

		BillBuy lastBillBuy = billBuyService.getLast();

		if (lastBillBuy == null || lastBillBuy.isSaved()) {

			theModel.addAttribute("clientsList", clientService.getAllClients());

			return "buy-bill-info";
		}

		else {

			return "redirect:/show-add-to-buy-bill";
		}

	}

	@RequestMapping("/show-add-to-buy-bill")
	public String showAddToBuyBill(Model theModel) {

		theModel.addAttribute("item", new Item());
// TO-DO 
		// Change the method
		BillBuy billBuy = billBuyService.getLast();

		float total = billBuy.getTotal();

		theModel.addAttribute("total", total);

		// علشان اختار منها
		theModel.addAttribute("itemsList", itemService.getAllItems());
		theModel.addAttribute("billBuy", billBuy);

		return "buy-bill";
	}

	// حفظ ببيانات الفاتورة
	@RequestMapping("/save-buy-bill-info")
	public String saveBuyBillInfo(@RequestParam(name = "late", defaultValue = "off") String late,
			@RequestParam(name = "clientId") String clientid) {

		BillBuy billBuy = new BillBuy();

		billBuy.setDate(LocalDate.now().toString());
		billBuy.setClient(clientService.getClientById(clientid));
		billBuy.setLate("on".equals(late) ? true : false);

		BillBuy lastBillBuy = billBuyService.getLast();

		if (lastBillBuy == null || lastBillBuy.isSaved()) {

			billBuyService.saveBuyBill(billBuy);

		}

		else {

			billBuy = lastBillBuy;

		}

		return "redirect:/show-add-to-buy-bill";

	}

	// اضافة فاتورة شراء
	@RequestMapping("/add-item-to-buy-bill")
	public String addBuyBill(@ModelAttribute(name = "item") Item item, Model theModel) throws Exception {

		BillBuyItem billBuyItem = new BillBuyItem();
		Item theItem = itemService.getItemById(item.getId());

		if (item.getQuantity() < theItem.getQuantity() && item.getQuantity() > 0) {

			// String billId = httpSession.getAttribute("billBuyId").toString();
			// BillBuy billBuy = billBuyService.getBillBuyById(billId);

			BillBuy billBuy = billBuyService.getLast();

			billBuyItem.setItem(theItem);
			billBuyItem.setBillBuy(billBuy);
			billBuyItem.setBuyPrice(theItem.getBuyPrice());
			billBuyItem.setQuantity(item.getQuantity());

			billBuyItemsService.addBillBuyItem(billBuyItem);

		} else {

			throw new Exception("Quantity is Not Good !");
		}

		return "redirect:/show-add-to-buy-bill";
	}

	@RequestMapping("/show-buy-bill-list")
	public String showBuyBillList(Model theModel, RedirectAttributes attributes) {

		BillBuy billBuy = billBuyService.getBillBuyByUpdateNow();

		if (billBuy != null) {

			attributes.addAttribute("buyBillId", billBuy.getId());

			return "redirect:/show-update-buy-bill";

		}

		theModel.addAttribute("clientsList", clientService.getAllClients());

		theModel.addAttribute("billBuyList", billBuyService.getAllBuyBills());

		return "buy-bill-list";
	}

	@RequestMapping("/delete-buyBill")
	public String deleteBuyBill(@RequestParam(name = "buyBillId") String buyBillId) {

		billBuyService.deleteBuyBill(buyBillId);

		return "redirect:/show-buy-bill-info";

	}

	@RequestMapping("/delete-buyBillItem")
	public String deleteBuyBillItem(@RequestParam(name = "buyBillItemId") String buyBillItemId) {

		billBuyItemsService.deleteBillBuyItem(buyBillItemId);

		return "redirect:/show-add-to-buy-bill";

	}

	@RequestMapping("/save-buyBill")
	public String saveBuyBillItem(@RequestParam(name = "buyBillId") String buyBillId) {

		BillBuy billBuy = billBuyService.getBillBuyById(buyBillId);

		List<BillBuyItem> billBuyItemsList = billBuy.getBillBuyItems();

		if (billBuyItemsList.isEmpty()) {

			return "redirect:/show-add-to-buy-bill";
		}

		float total = 0;

		for (BillBuyItem billBuyItem : billBuyItemsList) {

			Item item = billBuyItem.getItem();

			item.setQuantity(item.getQuantity() - billBuyItem.getQuantity());

			itemService.addNewItem(item);

			total += billBuyItem.getBuyPrice() * billBuyItem.getQuantity();
		}

		if (billBuy.isLate()) {

			Client client = billBuy.getClient();
			client.setDrawee(client.getDrawee() + total);
			billBuy.setClient(client);
		}

		billBuy.setSaved(true);

		billBuyService.saveBuyBill(billBuy);

		return "redirect:/items-list";

	}

	@RequestMapping("/search-buy-bill-by-clientId")
	public String searchForBuyBillByClientId(@RequestParam(name = "clientId") String clientId, Model theModel) {

		theModel.addAttribute("clientsList", clientService.getAllClients());

		theModel.addAttribute("billBuyList", clientService.getClientById(clientId).getBillBuyList());

		return "buy-bill-list";
	}

	@RequestMapping("/search-buy-bill-by-id")
	public String searchForBuyBillById(@RequestParam(name = "billId") String billId, Model theModel) {

		theModel.addAttribute("clientsList", clientService.getAllClients());

		theModel.addAttribute("billBuyList", billBuyService.getBillBuyContainingId(billId));

		return "buy-bill-list";
	}

	@RequestMapping("/show-printView")
	public String showPrintView(@RequestParam(name = "buyBillId") String buyBillId, Model theModel) {

		BillBuy billBuy = billBuyService.getBillBuyById(buyBillId);

		theModel.addAttribute("billBuy", billBuy);

		theModel.addAttribute("total", billBuy.getTotal());

		return "print-view";
	}

}
