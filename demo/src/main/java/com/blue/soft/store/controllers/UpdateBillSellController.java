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
import com.blue.soft.store.entity.Client;
import com.blue.soft.store.entity.Item;
import com.blue.soft.store.entity.TempBillItem;
import com.blue.soft.store.entity.User;
import com.blue.soft.store.service.BillSellItemsService;
import com.blue.soft.store.service.BillSellService;
import com.blue.soft.store.service.CompanyService;
import com.blue.soft.store.service.ItemService;
import com.blue.soft.store.service.TempBillItemsService;
import com.blue.soft.store.service.UserService;

@Controller
public class UpdateBillSellController {

	@Autowired
	HttpSession httpSession;

	@Autowired
	ItemService itemService;

	@Autowired
	BillSellService billSellService;

	@Autowired
	CompanyService companyService;

	@Autowired
	UserService userService;

	@Autowired
	BankController bankController;

	@Autowired
	BillSellItemsService billSellItemsService;

	@Autowired
	TempBillItemsService tempBillItemsService;

	// بيشوف لو في فاتورة بتتعدل بالفعل وبيدخل عليها لو كده
	@RequestMapping("/change-sell-bill-to-update")
	public String changeSellBillToUpdate(@RequestParam(name = "sellBillId") String sellBillId, Model theModel)
			throws Exception {

		String userId = httpSession.getAttribute("id").toString();

		BillSell sillBillToUpdate = billSellService.getBillSellByUpdaterId(userId);

		// NEW
		if (sillBillToUpdate != null) {

			throw new Exception("انت بالفعل تعدل فاتورة اخرى !!");
		}
		// NEW

		BillSell billSell = billSellService.getBillSellById(sellBillId);

		if (billSell.getUpdater() != null)
			return "redirect:/show-update-sell-bill";

		User theUser = userService.getUserById(userId);

		billSell.setUpdater(theUser);

		List<BillSellItem> billSellItemsList = billSell.getBillSellItems();

		float total = 0;

		for (BillSellItem billSellItem : billSellItemsList) {

			Item item = billSellItem.getItem();

			// تعديل كمية الصنف
			item.setQuantity(item.getQuantity() + billSellItem.getQuantity());

			itemService.addNewItem(item);

			total += billSellItem.getSellPrice() * billSellItem.getQuantity();
		}

		if (billSell.isLate()) {

			Client client = billSell.getClient();
			client.setDrawee(client.getDrawee() - total);
			billSell.setClient(client);

		} else {

			// update the Bank
			bankController.updateBankBalance("less", total);
		}

		billSellService.saveSellBill(billSell);

		tempBillItemsService.addBillSellItems(billSellItemsList);

		return "redirect:/show-update-sell-bill";
	}

	// بيعرض صفحة التعديل على الفاتورة
	@RequestMapping("/show-update-sell-bill")
	public String showUpdateSellBill(Model theModel) throws Exception {

		String userId = httpSession.getAttribute("id").toString();

		User theUser = userService.getUserById(userId);

		BillSell billSell = billSellService.getBillSellByUpdaterId(theUser.getId());

		if (billSell == null) {

			throw new Exception("هناك مستخدم اخر يعدل هذه الفاتورة");
		}

		float total = billSell.getTotal();

		billSellService.saveSellBill(billSell);

		theModel.addAttribute("total", total);
		theModel.addAttribute("item", new Item());
		theModel.addAttribute("billSell", billSell);
		theModel.addAttribute("updateItem", new BillSellItem());
		theModel.addAttribute("itemsList", itemService.getAllItems());
		theModel.addAttribute("sales", "active");

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
			String userId = httpSession.getAttribute("id").toString();

			BillSell billSell = billSellService.getBillSellByUpdaterId(userId);

			billSellItem.setItem(theItem);
			billSellItem.setBillSell(billSell);
			billSellItem.setSellPrice(theItem.getSellPrice());
			billSellItem.setQuantity(item.getQuantity());
			billSellItem.setDate();

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

	@RequestMapping("/update-sellBill")
	public String updateSellBill(@RequestParam(name = "sellBillId") String sellBillId, RedirectAttributes attributes) {

		BillSell billSell = billSellService.getBillSellById(sellBillId);
		List<BillSellItem> billSellItemsList = billSell.getBillSellItems();

		billSell.setUpdater(null);

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

		billSell.setUpdater(null);

		billSellService.saveSellBill(billSell);

		clearTempBillItems(billSell);

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

	@RequestMapping("/retrieve-UpdateSellBill")
	public String retrieveUpdateSellBill(@RequestParam(name = "sellBillId") String sellBillId, Model theModel) {

		BillSell billSell = billSellService.getBillSellById(sellBillId);

		List<String> ids = billSell.getBillSellItemsIDS();

		int size = billSell.getBillSellItems().size();

		for (int i = size - 1; i >= 0; i--) {

			billSell.removeItem(billSell.getBillSellItems().get(i));
		}

		for (String id : ids) {

			billSellItemsService.deleteBillSellItem(id);

		}

		List<TempBillItem> tempBillItemsList = tempBillItemsService.getTempBillItems(billSell.getId(), "sellBill");

		for (TempBillItem tempBillItem : tempBillItemsList) {

			Item theItem = itemService.getItemById(tempBillItem.getItemId());

			billSell.addBillSellItem(
					new BillSellItem(billSell, theItem, tempBillItem.getQuantity(), tempBillItem.getPrice()));

		}

		billSellService.saveSellBill(billSell);

		return "redirect:/show-update-sell-bill";
	}

	@RequestMapping("/delete-updateSellBill")
	public String deleteSellBill(@RequestParam(name = "sellBillId") String sellBillId) {

		BillSell billSell = billSellService.getBillSellById(sellBillId);

		billSellService.deleteSellBill(sellBillId);

		clearTempBillItems(billSell);

		return "redirect:/show-sell-bill-info";

	}

	public void clearTempBillItems(BillSell billSell) {

		List<TempBillItem> tempBillItemsList = tempBillItemsService.getTempBillItems(billSell.getId(), "sellBill");

//		tempBillItemsService.deleteTempBillSellItems(billSell.getId());

		for (TempBillItem tempBillItem : tempBillItemsList) {

			tempBillItemsService.deleteTempBillItems(tempBillItem.getId());

		}
	}

}
