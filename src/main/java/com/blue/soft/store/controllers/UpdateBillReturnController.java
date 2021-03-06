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
import com.blue.soft.store.entity.TempBillItem;
import com.blue.soft.store.entity.User;
import com.blue.soft.store.service.BillReturnItemsService;
import com.blue.soft.store.service.BillReturnService;
import com.blue.soft.store.service.ClientService;
import com.blue.soft.store.service.ItemService;
import com.blue.soft.store.service.TempBillItemsService;
import com.blue.soft.store.service.UserService;

@Controller
public class UpdateBillReturnController {

	@Autowired
	HttpSession httpSession;

	@Autowired
	ItemService itemService;

	@Autowired
	BillReturnService billReturnService;

	@Autowired
	ClientService clientService;

	@Autowired
	UserService userService;

	@Autowired
	BillReturnItemsService billReturnItemsService;

	@Autowired
	TempBillItemsService tempBillItemsService;

	// بيشوف لو في فاتورة بتتعدل بالفعل وبيدخل عليها لو كده
	@RequestMapping("/change-return-bill-to-update")
	public String changeReturnBillToUpdate(@RequestParam(name = "returnBillId") String returnBillId, Model theModel)
			throws Exception {

		String userId = httpSession.getAttribute("id").toString();

		BillReturn billReturnToUpdate = billReturnService.getBillReturnByUpdaterId(userId);

		if (billReturnToUpdate != null) {

			throw new Exception("انت بالفعل تعدل فاتورة اخرى !!");
		}

		BillReturn billReturn = billReturnService.getBillReturnById(returnBillId);

		if (billReturn.getUpdater() != null)
			return "redirect:/show-update-return-bill";

		User theUser = userService.getUserById(userId);

		billReturn.setUpdater(theUser);

		List<BillReturnItem> billReturnItemsList = billReturn.getBillReturnItems();

		float total = 0;

		for (BillReturnItem billReturnItem : billReturnItemsList) {

			Item item = billReturnItem.getItem();

			item.setQuantity(item.getQuantity() - billReturnItem.getQuantity());

			itemService.addNewItem(item);

			total += billReturnItem.getReturnPrice() * billReturnItem.getQuantity();
		}

		Client theClient = billReturn.getClient();

		theClient.setDrawee(theClient.getDrawee() - total);

		billReturnService.saveReturnBill(billReturn);

		tempBillItemsService.addBillReturnItems(billReturnItemsList);

		return "redirect:/show-update-return-bill";
	}

	// بيعرض صفحة التعديل على الفاتورة
	@RequestMapping("/show-update-return-bill")
	public String showUpdateReturnBill(Model theModel) {

		String userId = httpSession.getAttribute("id").toString();

		BillReturn billReturn = billReturnService.getBillReturnByUpdaterId(userId);

		float total = billReturn.getTotal();

		billReturnService.saveReturnBill(billReturn);

		theModel.addAttribute("total", total);
		theModel.addAttribute("item", new Item());
		theModel.addAttribute("billReturn", billReturn);
		theModel.addAttribute("updateItem", new BillReturnItem());
		theModel.addAttribute("itemsList", itemService.getAllItems());
		theModel.addAttribute("sales", "active");

		return "update-return-bill";
	}

	// بيضيف صنف للفاتورة اللي بتتعدل
	@RequestMapping("/add-item-to-update-return-bill")
	public String addToUpdateReturnBill(@ModelAttribute(name = "item") Item item, Model theModel) throws Exception {

		BillReturnItem billReturnItem = new BillReturnItem();
		Item theItem = itemService.getItemById(item.getId());

		if (item.getQuantity() > 0) {

			String userId = httpSession.getAttribute("id").toString();

			BillReturn billReturn = billReturnService.getBillReturnByUpdaterId(userId);

			List<BillReturnItem> buyItemsList = billReturn.getBillReturnItems();

			for (BillReturnItem billItemTemp : buyItemsList) {

				if (billItemTemp.getItem().getId().equals(item.getId())) {

					billItemTemp.setQuantity(billItemTemp.getQuantity() + item.getQuantity());

					billReturnItemsService.addBillReturnItem(billItemTemp);

					return "redirect:/show-update-return-bill";

				}
			}

			billReturnItem.setItem(theItem);
			billReturnItem.setBillReturn(billReturn);
			billReturnItem.setReturnPrice(theItem.getSellPrice());
			billReturnItem.setQuantity(item.getQuantity());
			billReturnItem.setDate(LocalDate.now().toString());

			billReturnItemsService.addBillReturnItem(billReturnItem);

		} else {

			throw new Exception("Quantity is Not Good !");
		}

		return "redirect:/show-update-return-bill";
	}

	// بيسمح اصناف من الفاتورة اللي بتتعدل
	@RequestMapping("/delete-returnBillItemUpdate")
	public String deleteReturnBillItemUpdate(@RequestParam(name = "returnBillItemId") String returnBillItemId) {

		billReturnItemsService.deleteBillReturnItem(returnBillItemId);

		return "redirect:/show-update-return-bill";

	}

	@RequestMapping("/update-returnBill")
	public String updateReturnBill(@RequestParam(name = "returnBillId") String returnBillId,
			RedirectAttributes attributes) {

		BillReturn billReturn = billReturnService.getBillReturnById(returnBillId);

		List<BillReturnItem> billReturnItems = billReturn.getBillReturnItems();

		float total = 0;

		for (BillReturnItem billReturnItem : billReturnItems) {

			Item item = billReturnItem.getItem();

			item.setQuantity(item.getQuantity() + billReturnItem.getQuantity());

			itemService.addNewItem(item);

			total += billReturnItem.getReturnPrice() * billReturnItem.getQuantity();
		}

		billReturn.setUpdater(null);

		billReturnService.saveReturnBill(billReturn);

		clearTempBillItems(billReturn);

		return "redirect:/show-return-bill-list";

	}

	// بيجيب الداتا من الفورم بتاعت التعديل علشان يعدل على الاصناف
	@RequestMapping("/update-returnBillItem")
	public String updateReturnBillItemUpdate(@ModelAttribute(name = "updateItem") BillReturnItem returnBillItem) {

		BillReturnItem oldBRItem = billReturnItemsService.getBillReturnItem(returnBillItem.getId());

		oldBRItem.setQuantity(returnBillItem.getQuantity());
		oldBRItem.setReturnPrice(returnBillItem.getReturnPrice());

		billReturnItemsService.addBillReturnItem(oldBRItem);

		return "redirect:/show-update-return-bill";

	}

	@RequestMapping("/retrieve-UpdateReturnBill")
	public String retriveUpdateReturnBill(@RequestParam(name = "returnBillId") String returnBillId, Model theModel) {

		BillReturn billReturn = billReturnService.getBillReturnById(returnBillId);

		List<String> ids = billReturn.getBillReturnItemsIDS();

		int size = billReturn.getBillReturnItems().size();

		for (int i = size - 1; i >= 0; i--) {

			billReturn.removeItem(billReturn.getBillReturnItems().get(i));
		}

		for (String id : ids) {

			billReturnItemsService.deleteBillReturnItem(id);

		}

		List<TempBillItem> tempBillItemsList = tempBillItemsService.getTempBillItems(billReturn.getId(), "returnBill");

		for (TempBillItem tempBillItem : tempBillItemsList) {

			Item theItem = itemService.getItemById(tempBillItem.getItemId());

			billReturn.addBillReturnItem(
					new BillReturnItem(billReturn, theItem, tempBillItem.getQuantity(), tempBillItem.getPrice()));

		}

		billReturnService.saveReturnBill(billReturn);

		return "redirect:/show-update-return-bill";
	}

	@RequestMapping("/delete-updateReturnBill")
	public String deleteReturnBill(@RequestParam(name = "returnBillId") String returnBillId) {

		BillReturn billReturn = billReturnService.getBillReturnById(returnBillId);

		billReturnService.deleteReturnBill(returnBillId);

		clearTempBillItems(billReturn);

		return "redirect:/show-return-bill-info";

	}

	public void clearTempBillItems(BillReturn billReturn) {

		List<TempBillItem> tempBillItemsList = tempBillItemsService.getTempBillItems(billReturn.getId(), "returnBill");

		for (TempBillItem tempBillItem : tempBillItemsList) {

			tempBillItemsService.deleteTempBillItems(tempBillItem.getId());

		}
	}
}
