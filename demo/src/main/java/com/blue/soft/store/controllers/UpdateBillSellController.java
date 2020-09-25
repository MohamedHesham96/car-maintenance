package com.blue.soft.store.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class UpdateBillSellController {

	@Autowired
	ItemService itemService;

	@Autowired
	BillSellService billSellService;

	@Autowired
	clientService clientService;

	@Autowired
	BillSellItemsService billSellItemsService;

	@RequestMapping("/show-update-sell-bill")
	public String showUpdateSellBill(@RequestParam(name = "sellBillId") String sellBillId, Model theModel) {

		theModel.addAttribute("item", new Item());

		BillSell billSell = billSellService.getBillSellById(sellBillId);

		billSell.setUpdateNow(true);

		billSellService.saveSellBill(billSell);

		List<BillSellItem> billSellItemsList = billSell.getBillSellItems();

		float total = 0;

		for (BillSellItem billSellItem : billSellItemsList) {

			total += billSellItem.getSellPrice() * billSellItem.getQuantity();

		}

		theModel.addAttribute("total", total);

		// علشان اختار منها
		theModel.addAttribute("itemsList", itemService.getAllItems());
		theModel.addAttribute("billSellItems", billSellItemsList);
		theModel.addAttribute("billSell", billSell);

		return "update-sell-bill";
	}

	// اضافة فاتورة شراء
	@RequestMapping("/add-item-to-update-sell-bill")
	public String addToUpdateSellBill(@ModelAttribute(name = "item") Item item, Model theModel,
			RedirectAttributes attributes) throws Exception {

		BillSellItem billSellItem = new BillSellItem();
		Item theItem = itemService.getItemById(item.getId());

		if (item.getQuantity() < theItem.getQuantity() && item.getQuantity() > 0) {

			// String billId = httpSession.getAttribute("billSellId").toString();
			// BillSell billSell = billSellService.getBillSellById(billId);

			BillSell billSell = billSellService.getLast();

			billSellItem.setItem(theItem);
			billSellItem.setBillSell(billSell);
			billSellItem.setSellPrice(theItem.getSellPrice());
			billSellItem.setQuantity(item.getQuantity());

			billSellItemsService.addBillSellItem(billSellItem);
			attributes.addAttribute("sellBillId", billSell.getId());

		} else {

			throw new Exception("Quantity is Not Good !");
		}

		return "redirect:/show-update-sell-bill";
	}

	@RequestMapping("/show-add-to-update-sell-bill")
	public String showAddToUpdateSellBill(Model theModel) {

		theModel.addAttribute("item", new Item());

		BillSell billSell = billSellService.getBillSellByUpdateNow();

		List<BillSellItem> billSellItemsList = billSell.getBillSellItems();

		float total = 0;

		for (BillSellItem billSellItem : billSellItemsList) {

			total += billSellItem.getSellPrice() * billSellItem.getQuantity();
		}

		theModel.addAttribute("total", total);

		// علشان اختار منها
		theModel.addAttribute("itemsList", itemService.getAllItems());
		theModel.addAttribute("billSellItems", billSellItemsList);
		theModel.addAttribute("billSell", billSell);

		return "update-sell-bill";
	}

	@RequestMapping("/delete-sellBillItemUpdate")
	public String updateSellBillItemUpdate(@RequestParam(name = "sellBillItemId") String sellBillItemId,
			RedirectAttributes attributes) {

		billSellItemsService.deleteBillSellItem(sellBillItemId);

		return "redirect:/show-add-to-update-sell-bill";

	}

	@RequestMapping("/update-sellBillItemUpdate")
	public String deleteSellBillItemUpdate(@RequestParam(name = "sellBillItemId") String sellBillItemId,
			RedirectAttributes attributes) {

		BillSellItem billSellItem = billSellItemsService.getBillSellItem(sellBillItemId);

		return "redirect:/show-add-to-update-sell-bill";

	}

}
