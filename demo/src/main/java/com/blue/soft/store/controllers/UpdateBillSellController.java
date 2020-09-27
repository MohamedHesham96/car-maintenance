package com.blue.soft.store.controllers;

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

	// بيشوف لو في فاتورة بتتعدل بالفعل وبيدخل عليها لو كده
	@RequestMapping("/change-sell-bill-to-update")
	public String changeSellBillToUpdate(@RequestParam(name = "sellBillId") String sellBillId, Model theModel) {

		BillSell billSell = billSellService.getBillSellById(sellBillId);

		if (billSell.isUpdateNow())
			return "redirect:/show-update-sell-bill";

		List<BillSellItem> billSellItemsList = billSell.getBillSellItems();

		billSell.setUpdateNow(true);

		int counter = 1;

		billSellService.saveSellBill(billSell);

		httpSession.setAttribute("itemsSize", billSellItemsList.size());

		for (BillSellItem billSellItem : billSellItemsList) {

			httpSession.setAttribute(billSell.getId() + counter++, billSellItem.getItem().getId() + "-"
					+ billSellItem.getQuantity() + "-" + billSellItem.getSellPrice());
		}

		String[] names = httpSession.getValueNames();

		for (String name : names) {

			System.out.println(
					"SHOW_HTTPS_SESSION >> " + "name >> " + name + " : " + httpSession.getAttribute(name).toString());

		}

		return "redirect:/show-update-sell-bill";
	}

	// بيعرض صفحة التعديل على الفاتورة
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

	// بيضيف صنف للفاتورة اللي بتتعدل
	@RequestMapping("/add-item-to-update-sell-bill")
	public String addToUpdateSellBill(@ModelAttribute(name = "item") Item item, Model theModel) throws Exception {

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

		} else {

			throw new Exception("Quantity is Not Good !");
		}

		return "redirect:/show-update-sell-bill";
	}

	// بيسمح اصناف من الفاتورة اللي بتتعدل
	@RequestMapping("/delete-sellBillItemUpdate")
	public String deleteSellBillItemUpdate(@RequestParam(name = "sellBillItemId") String sellBillItemId) {

		billSellItemsService.deleteBillSellItem(sellBillItemId);

		return "redirect:/show-update-sell-bill";

	}

	// بيسمح اصناف من الفاتورة اللي بتتعدل
	@RequestMapping("/update-sellBill")
	public String updateSellBill(@RequestParam(name = "sellBillId") String sellBillId, RedirectAttributes attributes) {

		BillSell billSell = billSellService.getBillSellById(sellBillId);
		billSell.setUpdateNow(false);
		billSellService.saveSellBill(billSell);

		clearHttpSession(sellBillId);

		return "redirect:/show-sell-bill-list";

	}

	// بيجيب الداتا من الفورم بتاعت التعديل علشان يعدل على الاصناف
	@RequestMapping("/update-sellBillItem")
	public String deleteSellBillItemUpdate(@ModelAttribute(name = "updateItem") Item sellBillItem) {

		BillSellItem oldBSItem = billSellItemsService.getBillSellItem(sellBillItem.getId());

		oldBSItem.setQuantity(sellBillItem.getQuantity());
		oldBSItem.setSellPrice(sellBillItem.getSellPrice());

		billSellItemsService.addBillSellItem(oldBSItem);

		return "redirect:/show-update-sell-bill";

	}

	@RequestMapping("/retrive-UpdateSellBill")
	public String retriveUpdateSellBill(@RequestParam(name = "sellBillId") String sellBillId, Model theModel) {

		BillSell billSell = billSellService.getBillSellById(sellBillId);

		List<String> ids = billSell.getBillSellItemsIDS();

		int size = billSell.getBillSellItems().size();

		for (int i = size - 1; i >= 0; i--) {

			billSell.removeItem(billSell.getBillSellItems().get(i));
		}

		for (String id : ids) {

			billSellItemsService.deleteBillSellItem(id);

		}

		int i = (int) httpSession.getAttribute("itemsSize");

		for (; i > 0; i--) {

			String[] splitItem = httpSession.getAttribute(billSell.getId() + i).toString().split("-");

			BillSellItem billSellItem = new BillSellItem(billSell, itemService.getItemById(splitItem[0]),
					Integer.parseInt(splitItem[1]), Float.parseFloat(splitItem[2]));

			billSellItemsService.addBillSellItem(billSellItem);

		}

		clearHttpSession(sellBillId);

		return "redirect:/show-update-sell-bill";
	}

	@RequestMapping("/delete-updateSellBill")
	public String deleteSellBill(@RequestParam(name = "sellBillId") String sellBillId) {

		billSellService.deleteSellBill(sellBillId);

		clearHttpSession(sellBillId);

		return "redirect:/show-sell-bill-info";

	}

	public void clearHttpSession(String sellBillId) {

		int size = Integer.parseInt(httpSession.getAttribute("itemsSize").toString());

		for (int i = 1; i <= size; i++) {

			httpSession.removeAttribute(sellBillId + i);
		}

		httpSession.removeAttribute("itemsSize");

		String[] names = httpSession.getValueNames();

		for (String name : names) {

			System.out.println(
					"ClEAR_HTTPS_SESSION >> " + "name >> " + name + " : " + httpSession.getAttribute(name).toString());

		}

	}
}
