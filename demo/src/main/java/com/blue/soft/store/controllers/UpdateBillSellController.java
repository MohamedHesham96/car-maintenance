package com.blue.soft.store.controllers;

import java.util.Arrays;
import java.util.Enumeration;
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
import com.blue.soft.store.entity.Item;
import com.blue.soft.store.service.BillSellItemsService;
import com.blue.soft.store.service.BillSellService;
import com.blue.soft.store.service.ItemService;
import com.blue.soft.store.service.clientService;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

@Controller
public class UpdateBillSellController {

	@Autowired
	HttpSession httpSession;

	@Autowired
	ItemService itemService;

	@Autowired
	BillSellService billSellService;

	@Autowired
	clientService clientService;

	@Autowired
	BillSellItemsService billSellItemsService;

	@RequestMapping("/change-sell-bill-to-update")
	public String changeSellBillToUpdate(@RequestParam(name = "sellBillId") String sellBillId, Model theModel) {

		BillSell billSell = billSellService.getBillSellById(sellBillId);

		if (billSell.isUpdateNow())
			return "redirect:/show-update-sell-bill";

		List<BillSellItem> billSellItemsList = billSell.getBillSellItems();

		billSell.setUpdateNow(true);
		int counter = 1;

		billSellService.saveSellBill(billSell);

		for (BillSellItem billSellItem : billSellItemsList) {

			httpSession.setAttribute(billSell.getId() + counter++, billSellItem.getItem().getId() + "-"
					+ billSellItem.getQuantity() + "-" + billSellItem.getSellPrice());
			System.out.println("Done >>> ");
		}

		return "redirect:/show-update-sell-bill";
	}

	@RequestMapping("/show-update-sell-bill")
	public String showUpdateSellBill(Model theModel) {

		BillSell billSell = billSellService.getBillSellByUpdateNow();

		List<BillSellItem> billSellItemsList = billSell.getBillSellItems();

		float total = 0;

		for (BillSellItem billSellItem : billSellItemsList) {

			total += billSellItem.getSellPrice() * billSellItem.getQuantity();

		}

		billSellService.saveSellBill(billSell);

		theModel.addAttribute("total", total);
		theModel.addAttribute("item", new Item());
		theModel.addAttribute("updateItem", new BillSellItem());
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

	@RequestMapping("/delete-sellBillItemUpdate")
	public String updateSellBillItemUpdate(@RequestParam(name = "sellBillItemId") String sellBillItemId,
			RedirectAttributes attributes) {

		billSellItemsService.deleteBillSellItem(sellBillItemId);

		return "redirect:/show-update-sell-bill";

	}

	@RequestMapping("/update-sellBillItem")
	public String deleteSellBillItemUpdate(@ModelAttribute(name = "updateItem") Item sellBillItem) {

		BillSellItem oldBSItem = billSellItemsService.getBillSellItem(sellBillItem.getId());

		oldBSItem.setQuantity(sellBillItem.getQuantity());
		oldBSItem.setSellPrice(sellBillItem.getSellPrice());

		System.out.println(oldBSItem.getSellPrice());

		billSellItemsService.addBillSellItem(oldBSItem);

		return "redirect:/show-update-sell-bill";

	}

}
