package com.blue.soft.store.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.context.Theme;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blue.soft.store.entity.BillSell;
import com.blue.soft.store.entity.BillSellItem;
import com.blue.soft.store.entity.Item;
import com.blue.soft.store.service.BillSellItemsService;
import com.blue.soft.store.service.BillSellService;
import com.blue.soft.store.service.ItemService;
import com.blue.soft.store.service.clientService;

@Controller
public class BillSellController {

	@Autowired
	private HttpSession httpSession;

	@Autowired
	ItemService itemService;

	@Autowired
	BillSellService billSellService;

	@Autowired
	clientService clientService;

	@Autowired
	BillSellItemsService billSellItemsService;

	// عرض الفورم بتاعت ادخال بيانات الفاتورة
	@RequestMapping("/show-sell-bill-info")
	public String showSellBillInfo(Model theModel) {

		BillSell billSell = new BillSell();

		billSell.setDate(LocalDate.now().toString());

		BillSell lastBillSell = billSellService.getLast();

		if (lastBillSell == null || lastBillSell.isSaved()) {

			theModel.addAttribute("clientsList", clientService.getAllClients());

			return "sell-bill-info";
		}

		else {

			httpSession.setAttribute("billSellId", lastBillSell.getId());
			httpSession.setAttribute("clientName", lastBillSell.getClient().getName());

			return "redirect:/show-add-sell-bill";
		}

	}

	@RequestMapping("/show-add-sell-bill")
	public String showAddSellBill(Model theModel) {

		theModel.addAttribute("item", new Item());

		String billId = httpSession.getAttribute("billSellId").toString();

		BillSell billSell = billSellService.getBillSellById(billId);

		List<BillSellItem> billSellItemsList = billSell.getBillSellItems();

		float total = 0;

		for (BillSellItem billSellItem : billSellItemsList) {

			total += billSellItem.getSellPrice() * billSellItem.getQuantity();
		}

		theModel.addAttribute("total", total);

		// علشان اختار منها
		theModel.addAttribute("itemsList", itemService.getAllItems());
		theModel.addAttribute("billSellItems", billSellItemsList);

		return "sell-bill";
	}

	// حفظ ببيانات الفاتورة
	@RequestMapping("/save-sell-bill-info")
	public String saveSellBillInfo(@RequestParam(name = "late", defaultValue = "off") String late,
			@RequestParam(name = "clientId") String clientid) {

		BillSell billSell = new BillSell();

		billSell.setDate(LocalDate.now().toString());
		billSell.setClient(clientService.getClientById(clientid));
		billSell.setLate("on".equals(late) ? true : false);

		BillSell lastBillSell = billSellService.getLast();

		if (lastBillSell == null || lastBillSell.isSaved()) {

			billSellService.addNewSellBill(billSell);

		}

		else {

			billSell = lastBillSell;

		}

		// redirectAttributes.addAttribute("sellBill", billSell);
		httpSession.setAttribute("billSellId", billSell.getId());
		httpSession.setAttribute("clientName", billSell.getClient().getName());

		return "redirect:/show-add-sell-bill";

	}

	// اضافة فاتورة شراء
	@RequestMapping("/add-item-to-sell-bill")
	public String addSellBill(@ModelAttribute(name = "item") Item item, Model theModel) throws Exception {

		BillSellItem billSellItem = new BillSellItem();
		Item theItem = itemService.getItemById(item.getId());

		if (item.getQuantity() < theItem.getQuantity()) {

			billSellItem.setItem(theItem);

			String billId = httpSession.getAttribute("billSellId").toString();

			BillSell billSell = billSellService.getBillSellById(billId);

			billSellItem.setBillSell(billSell);

			billSellItem.setBuyPrice(theItem.getBuyPrice());
			billSellItem.setSellPrice(theItem.getSellPrice());
			billSellItem.setQuantity(item.getQuantity());

			billSellItemsService.addBillSellItem(billSellItem);

		} else {

			throw new Exception("Quantity is Not Good !");
		}

		return "redirect:/show-add-sell-bill";
	}

	@RequestMapping("/show-sell-bill-list")
	public String showSellBillList(Model theModel) {

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

		return "redirect:/show-add-sell-bill";

	}

	@RequestMapping("/save-sellBill")
	public String saveSellBillItem(@RequestParam(name = "sellBillId") String sellBillItemId) {

		BillSell billSell = billSellService.getBillSellById(sellBillItemId);

		List<BillSellItem> billSellItemsList = billSell.getBillSellItems();

		if (billSell.getBillSellItems().isEmpty()) {

			return "redirect:/show-add-sell-bill";
		}

		for (BillSellItem billSellItem : billSellItemsList) {

			Item item = billSellItem.getItem();

			item.setQuantity(item.getQuantity() - billSellItem.getQuantity());

			itemService.addNewItem(item);
		}

		billSell.setSaved(true);

		billSellService.addNewSellBill(billSell);

		return "redirect:/items-list";

	}

	@RequestMapping("/search-sell-bill-by-clientId")
	public String searchForSellBillByClientId(@RequestParam(name = "clientId") String clientId, Model theModel) {

		theModel.addAttribute("clientsList", clientService.getAllClients());

		theModel.addAttribute("billSellList", clientService.getClientById(clientId).getBillSell());

		return "sell-bill-list";
	}

	@RequestMapping("/search-sell-bill-by-id")
	public String searchForSellBillById(@RequestParam(name = "billId") String billId, Model theModel) {

		theModel.addAttribute("clientsList", clientService.getAllClients());

		theModel.addAttribute("billSellList", billSellService.getBillSellContainingId(billId));

		return "sell-bill-list";
	}

}
