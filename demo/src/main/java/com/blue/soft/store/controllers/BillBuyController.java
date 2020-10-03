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
import com.blue.soft.store.entity.User;
import com.blue.soft.store.service.BankService;
import com.blue.soft.store.service.BillBuyItemsService;
import com.blue.soft.store.service.BillBuyService;
import com.blue.soft.store.service.CompanyService;
import com.blue.soft.store.service.ItemService;
import com.blue.soft.store.service.UserService;

@Controller
public class BillBuyController {

	@Autowired
	ItemService itemService;

	@Autowired
	BillBuyService billBuyService;

	@Autowired
	CompanyService companyService;

	@Autowired
	BillBuyItemsService billBuyItemsService;

	@Autowired
	BankService bankService;

	@Autowired
	UserService userService;

	@Autowired
	HttpSession httpSession;

	// عرض الفورم بتاعت ادخال بيانات الفاتورة
	@RequestMapping("/show-buy-bill-info")
	public String showBuyBillInfo(Model theModel) {

		BillBuy billBuy = new BillBuy();

		billBuy.setDate(LocalDate.now().toString());

		BillBuy lastBillBuy = billBuyService.getLast();

		if (lastBillBuy == null || lastBillBuy.isSaved()) {

			theModel.addAttribute("companiesList", companyService.getAllCompanies());

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
	public String saveBuyBillInfo(@RequestParam(name = "companyId") String companyId) {

		User theUser = (User) httpSession.getAttribute("user");

		theUser = userService.getUserById(theUser.getId());

		BillBuy billBuy = new BillBuy();

		billBuy.setDate(LocalDate.now().toString());
		billBuy.setCompany(companyService.getCompanyById(companyId));
		billBuy.setUser(theUser);

		BillBuy lastBillBuy = billBuyService.getLast();

		if (lastBillBuy == null || lastBillBuy.isSaved()) {

			billBuyService.saveBuyBill(billBuy);

		}

		else {

			billBuy = lastBillBuy;

		}

		return "redirect:/show-add-to-buy-bill";

	}

	@RequestMapping("/view-buy-bill")
	public String viewbuyBill(@RequestParam("buyBillId") String buyBillId, Model theModel) {

		BillBuy billBuy = billBuyService.getBillBuyById(buyBillId);

		float total = billBuy.getTotal();

		theModel.addAttribute("view", true);
		theModel.addAttribute("total", total);
		theModel.addAttribute("item", new Item());
		theModel.addAttribute("billBuy", billBuy);
		theModel.addAttribute("updateItem", new BillBuyItem());
		theModel.addAttribute("itemsList", itemService.getAllItems());

		return "update-buy-bill";
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

//			float avgBuyPrice = (item.getBuyPrice() + theItem.getBuyPrice()) / 2;

			billBuyItem.setBuyPrice(item.getBuyPrice());
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

		theModel.addAttribute("companiesList", companyService.getAllCompanies());

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

			item.setQuantity(item.getQuantity() + billBuyItem.getQuantity());

			itemService.addNewItem(item);

			total += billBuyItem.getBuyPrice() * billBuyItem.getQuantity();
		}

		Company company = billBuy.getCompany();
		company.setDrawee(company.getDrawee() + total);
		billBuy.setCompany(company);

		billBuy.setSaved(true);

		billBuyService.saveBuyBill(billBuy);

		return "redirect:/items-list";

	}

	@RequestMapping("/search-buy-bill-by-companyId")
	public String searchForBuyBillByCompanyId(@RequestParam(name = "companyId") String companyId, Model theModel) {

		theModel.addAttribute("companiesList", companyService.getAllCompanies());

		theModel.addAttribute("billBuyList", companyService.getCompanyById(companyId).getBillBuyList());

		return "buy-bill-list";
	}

	@RequestMapping("/search-buy-bill-by-id")
	public String searchForBuyBillById(@RequestParam(name = "billId") String billId, Model theModel) {

		theModel.addAttribute("companiesList", companyService.getAllCompanies());

		theModel.addAttribute("billBuyList", billBuyService.getBillBuyContainingId(billId));

		return "buy-bill-list";
	}

	@RequestMapping("/show-print-buy-bill")
	public String showPrintBuyBill(@RequestParam(name = "buyBillId") String buyBillId, Model theModel) {

		BillBuy billBuy = billBuyService.getBillBuyById(buyBillId);

		theModel.addAttribute("billBuy", billBuy);

		theModel.addAttribute("total", billBuy.getTotal());

		return "print-buy-bill";
	}

}
