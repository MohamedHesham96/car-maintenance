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

import com.blue.soft.store.entity.Client;
import com.blue.soft.store.entity.Company;
import com.blue.soft.store.entity.CompanyBillReturn;
import com.blue.soft.store.entity.CompanyBillReturn;
import com.blue.soft.store.entity.CompanyBillReturnItem;
import com.blue.soft.store.entity.Item;
import com.blue.soft.store.entity.TempBillItem;
import com.blue.soft.store.entity.User;
import com.blue.soft.store.service.ClientService;
import com.blue.soft.store.service.CompanyBillReturnItemsService;
import com.blue.soft.store.service.CompanyBillReturnService;
import com.blue.soft.store.service.CompanyBillReturnItemsService;
import com.blue.soft.store.service.CompanyBillReturnService;
import com.blue.soft.store.service.ItemService;
import com.blue.soft.store.service.TempBillItemsService;
import com.blue.soft.store.service.UserService;

@Controller
public class UpdateCompanyBillReturnController {

	@Autowired
	HttpSession httpSession;

	@Autowired
	ItemService itemService;

	@Autowired
	CompanyBillReturnService companyBillReturnService;

	@Autowired
	ClientService clientService;

	@Autowired
	UserService userService;

	@Autowired
	CompanyBillReturnItemsService companyBillReturnItemsService;

	@Autowired
	TempBillItemsService tempBillItemsService;

	// بيشوف لو في فاتورة بتتعدل بالفعل وبيدخل عليها لو كده
	@RequestMapping("/change-company-return-bill-to-update")
	public String changeCompanyBillReturnToUpdate(
			@RequestParam(name = "companyReturnBillId") String companyBillReturnId, Model theModel) throws Exception {

		String userId = httpSession.getAttribute("id").toString();

		CompanyBillReturn companyBillReturnToUpdate = companyBillReturnService.getCompanyBillReturnByUpdaterId(userId);

		if (companyBillReturnToUpdate != null) {

			throw new Exception("انت بالفعل تعدل فاتورة اخرى !!");
		}

		CompanyBillReturn companyBillReturn = companyBillReturnService.getCompanyBillReturnById(companyBillReturnId);

		if (companyBillReturn.getUpdater() != null)
			return "redirect:/show-update-company-return-bill";

		User theUser = userService.getUserById(userId);

		companyBillReturn.setUpdater(theUser);

		List<CompanyBillReturnItem> companyBillReturnItemsList = companyBillReturn.getCompanyBillReturnItems();

		float total = 0;

		for (CompanyBillReturnItem companyBillReturnItem : companyBillReturnItemsList) {

			Item item = companyBillReturnItem.getItem();

			item.setQuantity(item.getQuantity() + companyBillReturnItem.getQuantity());

			itemService.addNewItem(item);

			total += companyBillReturnItem.getReturnPrice() * companyBillReturnItem.getQuantity();
		}

		Company company = companyBillReturn.getCompany();

		company.setDrawee(company.getDrawee() - total);

		companyBillReturnService.saveCompanyReturnBill(companyBillReturn);

		tempBillItemsService.addCompanyBillReturnItems(companyBillReturnItemsList);

		return "redirect:/show-update-company-return-bill";
	}

	// بيعرض صفحة التعديل على الفاتورة
	@RequestMapping("/show-update-company-return-bill")
	public String showUpdateCompanyBillReturn(Model theModel) {

		String userId = httpSession.getAttribute("id").toString();

		CompanyBillReturn companyBillReturn = companyBillReturnService.getCompanyBillReturnByUpdaterId(userId);

		float total = companyBillReturn.getTotal();

		companyBillReturnService.saveCompanyReturnBill(companyBillReturn);

		theModel.addAttribute("total", total);
		theModel.addAttribute("item", new Item());
		theModel.addAttribute("companyBillReturn", companyBillReturn);
		theModel.addAttribute("updateItem", new CompanyBillReturnItem());
		theModel.addAttribute("itemsList", itemService.getAllItems());
		theModel.addAttribute("buys", "active");

		return "update-company-return-bill";
	}

	// بيضيف صنف للفاتورة اللي بتتعدل
	@RequestMapping("/add-item-to-update-company-return-bill")
	public String addToUpdateCompanyBillReturn(@ModelAttribute(name = "item") Item item, Model theModel)
			throws Exception {

		CompanyBillReturnItem companyBillReturnItem = new CompanyBillReturnItem();
		Item theItem = itemService.getItemById(item.getId());

		if (item.getQuantity() <= theItem.getQuantity() && item.getQuantity() > 0) {

			String userId = httpSession.getAttribute("id").toString();

			CompanyBillReturn companyBillReturn = companyBillReturnService.getCompanyBillReturnByUpdaterId(userId);

			companyBillReturnItem.setItem(theItem);
			companyBillReturnItem.setCompanyBillReturn(companyBillReturn);
			companyBillReturnItem.setReturnPrice(theItem.getSellPrice());
			companyBillReturnItem.setQuantity(item.getQuantity());
			companyBillReturnItem.setDate(LocalDate.now().toString());

			companyBillReturnItemsService.addCompanyBillReturnItem(companyBillReturnItem);

		} else {

			throw new Exception("Quantity is Not Good !");
		}

		return "redirect:/show-update-company-return-bill";
	}

	// بيسمح اصناف من الفاتورة اللي بتتعدل
	@RequestMapping("/delete-companyReturnBillItemUpdate")
	public String deleteCompanyBillReturnItemUpdate(
			@RequestParam(name = "companyReturnBillItemId") String companyReturnBillItemId) {

		companyBillReturnItemsService.deleteCompanyBillReturnItem(companyReturnBillItemId);

		return "redirect:/show-update-company-return-bill";

	}

	@RequestMapping("/update-companyReturnBill")
	public String updateCompanyBillReturn(@RequestParam(name = "companyReturnBillId") String companyReturnBillId,
			RedirectAttributes attributes) {

		CompanyBillReturn companyBillReturn = companyBillReturnService.getCompanyBillReturnById(companyReturnBillId);

		List<CompanyBillReturnItem> companyBillReturnItems = companyBillReturn.getCompanyBillReturnItems();

		float total = 0;

		for (CompanyBillReturnItem companyBillReturnItem : companyBillReturnItems) {

			Item item = companyBillReturnItem.getItem();

			item.setQuantity(item.getQuantity() - companyBillReturnItem.getQuantity());

			itemService.addNewItem(item);

			total += companyBillReturnItem.getReturnPrice() * companyBillReturnItem.getQuantity();
		}

		companyBillReturn.setUpdater(null);

		companyBillReturnService.saveCompanyReturnBill(companyBillReturn);

		clearTempBillItems(companyBillReturn);

		return "redirect:/show-company-return-bill-list";

	}

	// بيجيب الداتا من الفورم بتاعت التعديل علشان يعدل على الاصناف
	@RequestMapping("/update-companyReturnBillItem")
	public String deleteCompanyBillReturnItemUpdate(
			@ModelAttribute(name = "updateItem") CompanyBillReturnItem companyBillReturnItem) {

		CompanyBillReturnItem oldBRItem = companyBillReturnItemsService
				.getCompanyBillReturnItem(companyBillReturnItem.getId());

		oldBRItem.setQuantity(companyBillReturnItem.getQuantity());
		oldBRItem.setReturnPrice(companyBillReturnItem.getReturnPrice());

		companyBillReturnItemsService.addCompanyBillReturnItem(oldBRItem);

		return "redirect:/show-update-company-return-bill";

	}

	@RequestMapping("/retrieve-updateCompanyReturnBill")
	public String retriveUpdateCompanyBillReturn(@RequestParam(name = "companyReturnBillId") String companyBillReturnId,
			Model theModel) {

		CompanyBillReturn companyBillReturn = companyBillReturnService.getCompanyBillReturnById(companyBillReturnId);

		List<String> ids = companyBillReturn.getCompanyBillReturnItemsIDS();

		int size = companyBillReturn.getCompanyBillReturnItems().size();

		for (int i = size - 1; i >= 0; i--) {

			companyBillReturn.removeItem(companyBillReturn.getCompanyBillReturnItems().get(i));
		}

		for (String id : ids) {

			companyBillReturnItemsService.deleteCompanyBillReturnItem(id);

		}

		List<TempBillItem> tempBillItemsList = tempBillItemsService.getTempBillItems(companyBillReturn.getId(),
				"companyReturnBill");

		for (TempBillItem tempBillItem : tempBillItemsList) {

			Item theItem = itemService.getItemById(tempBillItem.getItemId());

			companyBillReturn.addCompanyBillReturnItem(new CompanyBillReturnItem(companyBillReturn, theItem,
					tempBillItem.getQuantity(), tempBillItem.getPrice()));

		}

		companyBillReturnService.saveCompanyReturnBill(companyBillReturn);

		return "redirect:/show-update-company-return-bill";
	}

	@RequestMapping("/delete-updateCompanyReturnBill")
	public String deleteCompanyBillReturn(@RequestParam(name = "companyReturnBillId") String companyBillReturnId) {

		CompanyBillReturn companyBillReturn = companyBillReturnService.getCompanyBillReturnById(companyBillReturnId);

		companyBillReturnService.deleteCompanyReturnBill(companyBillReturnId);

		clearTempBillItems(companyBillReturn);

		return "redirect:/show-company-return-bill-info";

	}

	public void clearTempBillItems(CompanyBillReturn companyBillReturn) {

		List<TempBillItem> tempBillItemsList = tempBillItemsService.getTempBillItems(companyBillReturn.getId(),
				"companyReturnBill");

		for (TempBillItem tempBillItem : tempBillItemsList) {

			tempBillItemsService.deleteTempBillItems(tempBillItem.getId());

		}
	}
}
