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

import com.blue.soft.store.entity.BillBuy;
import com.blue.soft.store.entity.BillBuyItem;
import com.blue.soft.store.entity.Company;
import com.blue.soft.store.entity.Item;
import com.blue.soft.store.entity.TempBillItem;
import com.blue.soft.store.entity.User;
import com.blue.soft.store.service.BillBuyItemsService;
import com.blue.soft.store.service.BillBuyService;
import com.blue.soft.store.service.CompanyService;
import com.blue.soft.store.service.ItemService;
import com.blue.soft.store.service.TempBillItemsService;
import com.blue.soft.store.service.UserService;

@Controller
public class UpdateBillBuyController {

	@Autowired
	HttpSession httpSession;

	@Autowired
	ItemService itemService;

	@Autowired
	BillBuyService billBuyService;

	@Autowired
	CompanyService companyService;

	@Autowired
	BillBuyItemsService billBuyItemsService;

	@Autowired
	TempBillItemsService tempBillItemsService;

	@Autowired
	UserService userService;

	// بيشوف لو في فاتورة بتتعدل بالفعل وبيدخل عليها لو كده
	@RequestMapping("/change-buy-bill-to-update")
	public String changebuyBillToUpdate(@RequestParam(name = "buyBillId") String buyBillId, Model theModel)
			throws Exception {

		String userId = httpSession.getAttribute("id").toString();

		User theUser = userService.getUserById(userId);

		BillBuy billBuy = billBuyService.getBillBuyById(buyBillId);

		BillBuy buyBillToUpdate = billBuyService.getBillBuyByUpdaterId(theUser.getId());

		// NEW
		if (buyBillToUpdate != null) {

			throw new Exception("انت بالفعل تعدل فاتورة اخرى !!");
		}
		// NEW

		if (billBuy.getUpdater() != null)
			return "redirect:/show-update-buy-bill";

		List<BillBuyItem> billBuyItemsList = billBuy.getBillBuyItems();

		billBuy.setUpdater(theUser);

		float total = 0;

		for (BillBuyItem billBuyItem : billBuyItemsList) {

			Item item = billBuyItem.getItem();

			item.setQuantity(item.getQuantity() - billBuyItem.getQuantity());

			itemService.addNewItem(item);
			total += billBuyItem.getBuyPrice() * billBuyItem.getQuantity();

		}

		Company company = billBuy.getCompany();
		company.setDrawee(company.getDrawee() - total);
		billBuy.setCompany(company);

		billBuyService.saveBuyBill(billBuy);

		tempBillItemsService.addBillBuyItems(billBuyItemsList);

		return "redirect:/show-update-buy-bill";
	}

	// بيعرض صفحة التعديل على الفاتورة
	@RequestMapping("/show-update-buy-bill")
	public String showUpdateBuyBill(Model theModel) {

		String userId = httpSession.getAttribute("id").toString();

		BillBuy billBuy = billBuyService.getBillBuyByUpdaterId(userId);

		float total = billBuy.getTotal();

		billBuyService.saveBuyBill(billBuy);

		theModel.addAttribute("total", total);
		theModel.addAttribute("item", new Item());
		theModel.addAttribute("billBuy", billBuy);
		theModel.addAttribute("updateItem", new BillBuyItem());
		theModel.addAttribute("itemsList", itemService.getAllItems());
		theModel.addAttribute("buys", "active");

		return "update-buy-bill";
	}

	// بيضيف صنف للفاتورة اللي بتتعدل
	@RequestMapping("/add-item-to-update-buy-bill")
	public String addToUpdateBuyBill(@ModelAttribute(name = "item") Item item) throws Exception {

		BillBuyItem billBuyItem = new BillBuyItem();
		Item theItem = itemService.getItemById(item.getId());

		if (item.getQuantity() <= theItem.getQuantity() && item.getQuantity() > 0) {

			String userId = httpSession.getAttribute("id").toString();

			BillBuy billBuy = billBuyService.getBillBuyByUpdaterId(userId);

			List<BillBuyItem> buyItemsList = billBuy.getBillBuyItems();

			for (BillBuyItem billItemTemp : buyItemsList) {

				if (billItemTemp.getItem().getId().equals(item.getId())) {

					billItemTemp.setQuantity(billItemTemp.getQuantity() + item.getQuantity());

					billBuyItemsService.addBillBuyItem(billItemTemp);

					return "redirect:/show-update-buy-bill";

				}
			}

			billBuyItem.setItem(theItem);
			billBuyItem.setBillBuy(billBuy);
			billBuyItem.setBuyPrice(theItem.getBuyPrice());
			billBuyItem.setQuantity(item.getQuantity());
			billBuyItem.setDate(LocalDate.now().toString());

			billBuyItemsService.addBillBuyItem(billBuyItem);

		} else {

			throw new Exception("Quantity is Not Good !");
		}

		return "redirect:/show-update-buy-bill";
	}

	// بيسمح اصناف من الفاتورة اللي بتتعدل
	@RequestMapping("/delete-buyBillItemUpdate")
	public String deleteBuyBillItemUpdate(@RequestParam(name = "buyBillItemId") String buyBillItemId) {

		billBuyItemsService.deleteBillBuyItem(buyBillItemId);

		return "redirect:/show-update-buy-bill";

	}

	// بيسمح اصناف من الفاتورة اللي بتتعدل
	@RequestMapping("/update-buyBill")
	public String updateBuyBill(@RequestParam(name = "buyBillId") String buyBillId, RedirectAttributes attributes) {

		BillBuy billBuy = billBuyService.getBillBuyById(buyBillId);

		List<BillBuyItem> billBuyItemsList = billBuy.getBillBuyItems();

		float total = 0;

		for (BillBuyItem billBuyItem : billBuyItemsList) {

			Item item = billBuyItem.getItem();

			item.setQuantity(item.getQuantity() + billBuyItem.getQuantity());

			itemService.addNewItem(item);

			total += billBuyItem.getBuyPrice() * billBuyItem.getQuantity();
		}

		Company company = billBuy.getCompany();
		company.setDrawee(company.getDrawee() + total);
		billBuy.setCompany(company);

		billBuy.setUpdater(null);

		billBuyService.saveBuyBill(billBuy);

		clearTempBillItems(billBuy);

		return "redirect:/show-buy-bill-list";

	}

	// بيجيب الداتا من الفورم بتاعت التعديل علشان يعدل على الاصناف
	@RequestMapping("/update-buyBillItem")
	public String deleteBuyBillItemUpdate(@ModelAttribute(name = "updateItem") Item buyBillItem) {

		BillBuyItem oldBSItem = billBuyItemsService.getBillBuyItem(buyBillItem.getId());

		oldBSItem.setQuantity(buyBillItem.getQuantity());

		oldBSItem.setBuyPrice(buyBillItem.getBuyPrice());

		billBuyItemsService.addBillBuyItem(oldBSItem);

		return "redirect:/show-update-buy-bill";

	}

	@RequestMapping("/retrieve-UpdateBuyBill")
	public String retrieveUpdateBuyBill(@RequestParam(name = "buyBillId") String buyBillId, Model theModel) {

		BillBuy billBuy = billBuyService.getBillBuyById(buyBillId);

		List<String> ids = billBuy.getBillBuyItemsIDS();

		int size = billBuy.getBillBuyItems().size();

		for (int i = size - 1; i >= 0; i--) {

			billBuy.removeItem(billBuy.getBillBuyItems().get(i));
		}

		for (String id : ids) {

			billBuyItemsService.deleteBillBuyItem(id);

		}

		List<TempBillItem> tempBillItemsList = tempBillItemsService.getTempBillItems(billBuy.getId(), "buyBill");

		for (TempBillItem tempBillItem : tempBillItemsList) {

			Item theItem = itemService.getItemById(tempBillItem.getItemId());

			billBuy.addBillBuyItem(
					new BillBuyItem(billBuy, theItem, tempBillItem.getQuantity(), tempBillItem.getPrice()));

		}

		billBuyService.saveBuyBill(billBuy);

		return "redirect:/show-update-buy-bill";
	}

	@RequestMapping("/delete-updateBuyBill")
	public String deleteBuyBill(@RequestParam(name = "buyBillId") String buyBillId) {

		BillBuy billbuy = billBuyService.getBillBuyById(buyBillId);

		billBuyService.deleteBuyBill(buyBillId);

		clearTempBillItems(billbuy);

		return "redirect:/show-buy-bill-list";

	}

	public void clearTempBillItems(BillBuy billBuy) {

		List<TempBillItem> tempBillItemsList = tempBillItemsService.getTempBillItems(billBuy.getId(), "buyBill");

		for (TempBillItem tempBillItem : tempBillItemsList) {

			tempBillItemsService.deleteTempBillItems(tempBillItem.getId());

		}
	}

}
