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

import com.blue.soft.store.entity.CompanyBillReturn;
import com.blue.soft.store.entity.CompanyBillReturnItem;
import com.blue.soft.store.entity.Company;
import com.blue.soft.store.entity.Item;
import com.blue.soft.store.entity.ItemMove;
import com.blue.soft.store.entity.User;
import com.blue.soft.store.service.BankService;
import com.blue.soft.store.service.CompanyBillReturnItemsService;
import com.blue.soft.store.service.CompanyBillReturnService;
import com.blue.soft.store.service.CompanyService;
import com.blue.soft.store.service.ItemMoveService;
import com.blue.soft.store.service.ItemService;
import com.blue.soft.store.service.UserService;

@Controller
public class CompanyBillReturnController {

	@Autowired
	ItemService itemService;

	@Autowired
	ItemMoveService itemMoveService;

	@Autowired
	CompanyBillReturnService CompanyBillReturnService;

	@Autowired
	CompanyService companyService;

	@Autowired
	CompanyBillReturnItemsService billReturnItemsService;

	@Autowired
	BankService bankService;

	@Autowired
	BankController bankController;

	@Autowired
	UserService userService;

	@Autowired
	HttpSession httpSession;

	// عرض الفورم بتاعت ادخال بيانات الفاتورة
	@RequestMapping("/show-company-return-bill-info")
	public String showCompanyReturnBillInfo(Model theModel) {

		String userId = httpSession.getAttribute("id").toString();

		CompanyBillReturn billReturn = new CompanyBillReturn();

		billReturn.setDate(LocalDate.now().toString());

		CompanyBillReturn lastCompanyBillReturn = CompanyBillReturnService.getCompanyBillReturnBySaverId(userId);

		if (lastCompanyBillReturn == null) {

			theModel.addAttribute("companiesList", companyService.getAllCompanies());

			return "company-return-bill-info";
		}

		else {

			return "redirect:/show-add-to-company-return-bill";
		}

	}

	@RequestMapping("/show-add-to-company-return-bill")
	public String showAddToCompanyReturnBill(Model theModel) {

		theModel.addAttribute("item", new Item());

		String userId = httpSession.getAttribute("id").toString();

		CompanyBillReturn companyBillReturn = CompanyBillReturnService.getCompanyBillReturnBySaverId(userId);

		float total = companyBillReturn.getTotal();

		theModel.addAttribute("total", total);

		// علشان اختار منها
		theModel.addAttribute("itemsList", itemService.getAllItems());
		theModel.addAttribute("companyBillReturn", companyBillReturn);

		return "company-return-bill";
	}

	// حفظ ببيانات الفاتورة
	@RequestMapping("/save-company-return-bill-info")
	public String saveCompanyReturnBillInfo(@RequestParam(name = "companyId") String companyId) {

		String userId = httpSession.getAttribute("id").toString();

		User theUser = userService.getUserById(userId);

		CompanyBillReturn lastCompanyBillReturn = CompanyBillReturnService.getCompanyBillReturnBySaverId(userId);

		if (lastCompanyBillReturn == null) {

			CompanyBillReturn companyBillReturn = new CompanyBillReturn();

			companyBillReturn.setDate(LocalDate.now().toString());

			companyBillReturn.setUser(theUser);

			companyBillReturn.setCompany(companyService.getCompanyById(companyId));

			companyBillReturn.setSaver(theUser);

			CompanyBillReturnService.saveCompanyReturnBill(companyBillReturn);
		}

		return "redirect:/show-add-to-company-return-bill";

	}

	@RequestMapping("/view-company-return-bill")
	public String viewreturnBill(@RequestParam("companyReturnBillId") String companyReturnBillId, Model theModel) {

		CompanyBillReturn billReturn = CompanyBillReturnService.getCompanyBillReturnById(companyReturnBillId);

		float total = billReturn.getTotal();

		theModel.addAttribute("view", true);
		theModel.addAttribute("total", total);
		theModel.addAttribute("item", new Item());
		theModel.addAttribute("companyBillReturn", billReturn);
		theModel.addAttribute("updateItem", new CompanyBillReturnItem());
		theModel.addAttribute("itemsList", itemService.getAllItems());

		return "update-company-return-bill";
	}

	// اضافة فاتورة شراء
	@RequestMapping("/add-item-to-company-return-bill")
	public String addItemToCompanyReturnBill(@ModelAttribute(name = "item") Item item, Model theModel)
			throws Exception {

		CompanyBillReturnItem billReturnItem = new CompanyBillReturnItem();
		Item theItem = itemService.getItemById(item.getId());

		if (item.getQuantity() > 0 && item.getSellPrice() > 0) {

			String userId = httpSession.getAttribute("id").toString();

			CompanyBillReturn billReturn = CompanyBillReturnService.getCompanyBillReturnBySaverId(userId);

			billReturnItem.setItem(theItem);
			billReturnItem.setReturnPrice(item.getSellPrice());

			System.out.println("billReturnItem >> Return Price >> " + billReturnItem.getReturnPrice());

			billReturnItem.setQuantity(item.getQuantity());

			billReturnItem.setCompanyBillReturn(billReturn);

			billReturnItemsService.addCompanyBillReturnItem(billReturnItem);

		}

		else if (item.getSellPrice() <= 0) {

			throw new Exception("من فضلك ادخل السعر بشكل صحيح");
		}

		else if (item.getQuantity() <= 0) {

			throw new Exception("من فضلك ادخل الكمية بشكل صحيح");
		}

		return "redirect:/show-add-to-company-return-bill";
	}

	@RequestMapping("/show-company-return-bill-list")
	public String showCompanyReturnBillList(Model theModel, RedirectAttributes attributes) {

		String userId = httpSession.getAttribute("id").toString();

		CompanyBillReturn companyReturnBill = CompanyBillReturnService.getCompanyBillReturnByUpdaterId(userId);

		if (companyReturnBill != null) {

			attributes.addAttribute("companyReturnBillId", companyReturnBill.getId());

			return "redirect:/show-update-company-return-bill";

		}

		theModel.addAttribute("companiesList", companyService.getAllCompanies());

		theModel.addAttribute("companyBillReturnList", CompanyBillReturnService.getAllCompanyReturnBills());

		return "company-return-bill-list";
	}

	@RequestMapping("/delete-companyReturnBill")
	public String deleteCompanyReturnBill(@RequestParam(name = "companyReturnBillId") String companyReturnBillId) {

		CompanyBillReturnService.deleteCompanyReturnBill(companyReturnBillId);

		return "redirect:/show-company-return-bill-info";

	}

	@RequestMapping("/delete-companyReturnBillItem")
	public String deleteCompanyReturnBillItem(@RequestParam(name = "companyReturnBillItemId") String returnBillItemId) {

		billReturnItemsService.deleteCompanyBillReturnItem(returnBillItemId);

		return "redirect:/show-add-to-company-return-bill";

	}

	@RequestMapping("/save-companyReturnBill")
	public String saveCompanyReturnBillItem(@RequestParam(name = "companyReturnBillId") String companyReturnBillId) {

		CompanyBillReturn companyBillReturn = CompanyBillReturnService.getCompanyBillReturnById(companyReturnBillId);

		List<CompanyBillReturnItem> billReturnItemsList = companyBillReturn.getCompanyBillReturnItems();

		if (billReturnItemsList.isEmpty()) {

			return "redirect:/show-add-to-company-return-bill";
		}

		float total = 0;

		for (CompanyBillReturnItem billReturnItem : billReturnItemsList) {

			Item item = billReturnItem.getItem();

			item.setQuantity(item.getQuantity() + billReturnItem.getQuantity());

			itemService.addNewItem(item);

			total += billReturnItem.getReturnPrice() * billReturnItem.getQuantity();

			itemMoveService.addItemMove(new ItemMove(item, "مرتجع مورد", companyBillReturn.getId(),
					billReturnItem.getQuantity(), item.getQuantity(), billReturnItem.getReturnPrice(), 0));
		}

		Company company = companyBillReturn.getCompany();
		company.setDrawee(company.getDrawee() + total);
		companyBillReturn.setCompany(company);

		companyBillReturn.setSaver(null);

		CompanyBillReturnService.saveCompanyReturnBill(companyBillReturn);

		return "redirect:/show-company-return-bill-list";

	}

	@RequestMapping("/search-company-return-bill-by-companyId")
	public String searchForCompanyReturnBillByCompanyId(@RequestParam(name = "companyId") String companyId,
			Model theModel) {

		theModel.addAttribute("companiesList", companyService.getAllCompanies());

		theModel.addAttribute("companyBillReturnList", companyService.getCompanyById(companyId).getBillReturnList());

		return "company-return-bill-list";
	}

	@RequestMapping("/search-company-return-bill-by-id")
	public String searchForCompanyReturnBillById(@RequestParam(name = "billId") String billId, Model theModel) {

		theModel.addAttribute("companiesList", companyService.getAllCompanies());

		theModel.addAttribute("companyBillReturnList",
				CompanyBillReturnService.getCompanyBillReturnContainingId(billId));

		return "company-return-bill-list";
	}

	@RequestMapping("/show-print-company-return-bill")
	public String showPrintCompanyReturnBill(@RequestParam(name = "companyBillReturnId") String companyBillReturnId,
			Model theModel) {

		CompanyBillReturn companyBillReturn = CompanyBillReturnService.getCompanyBillReturnById(companyBillReturnId);

		theModel.addAttribute("companyBillReturn", companyBillReturn);

		theModel.addAttribute("total", companyBillReturn.getTotal());

		return "print-company-return-bill";
	}
}
