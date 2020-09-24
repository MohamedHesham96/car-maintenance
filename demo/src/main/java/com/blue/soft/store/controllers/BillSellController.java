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

		theModel.addAttribute("sellBill", billSell);
//
		theModel.addAttribute("clientsList", clientService.getAllClients());

		if (lastBillSell == null) {

			return "sell-bill-info";

		}

		if (lastBillSell.isSaved()) {

			return "sell-bill-info";
		}

		else {

			httpSession.setAttribute("billSellId", lastBillSell.getId());
			httpSession.setAttribute("clientName", clientService.getClientById(lastBillSell.getClientId()).getName());

			return "redirect:/show-add-sell-bill";

		}

	}

	// حفظ ببيانات الفاتورة
	@RequestMapping("/save-sell-bill-info")
	public String saveSellBillInfo(@ModelAttribute(name = "sellBill") BillSell billSell) {

		billSell.setDate(LocalDate.now().toString());

		BillSell lastBillSell = billSellService.getLast();

		if (lastBillSell != null) {

			if (lastBillSell.isSaved()) {

				billSellService.addNewSellBill(billSell);

			} else {

				billSell = lastBillSell;

			}

		} else {

			billSellService.addNewSellBill(billSell);
		}

		// redirectAttributes.addAttribute("sellBill", billSell);

		httpSession.setAttribute("billSellId", billSell.getId());
		httpSession.setAttribute("clientName", clientService.getClientById(billSell.getClientId()).getName());

		return "redirect:/show-add-sell-bill";
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

}
