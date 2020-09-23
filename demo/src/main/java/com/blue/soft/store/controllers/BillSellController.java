package com.blue.soft.store.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blue.soft.store.DAO.BillSellRepository;
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

		theModel.addAttribute("clientsList", clientService.getAllClients());

		if (lastBillSell == null) {

			return "sell-bill-info";

		}

		if (lastBillSell.isSaved()) {

			return "sell-bill-info";

		} else {

			return "redirect:/show-add-sell-bill";

		}

	}

	@RequestMapping("/show-add-sell-bill")
	public String showAddSellBill(Model theModel) {

		theModel.addAttribute("item", new Item());

		String billId = httpSession.getAttribute("billSellId").toString();

		BillSell billSell = billSellService.getBillSellById(billId);

		// علشان اختار منها
		theModel.addAttribute("itemsList", itemService.getAllItems());
		theModel.addAttribute("billSellItems", billSell.getBillSellItems());

		return "sell-bill";
	}

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

	@RequestMapping("/save-sell-bill-info")
	public String addSellBillInfo(@ModelAttribute(name = "sellBill") BillSell billSell,
			RedirectAttributes redirectAttributes) {

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

		redirectAttributes.addAttribute("sellBill", billSell);

		httpSession.setAttribute("billSellId", billSell.getId());
		httpSession.setAttribute("clientName", clientService.getClientById(billSell.getClientId()).getName());

		return "redirect:/show-add-sell-bill";
	}

	@RequestMapping("/delete-sellBill")
	public String deleteSellBill(@RequestParam(name = "sellBillId") String sellBillId) {

		billSellService.deleteSellBill(sellBillId);

		return "redirect:/show-sell-bill-info";

	}

}
