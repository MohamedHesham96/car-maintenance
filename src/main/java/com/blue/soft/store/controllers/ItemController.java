package com.blue.soft.store.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blue.soft.store.entity.Item;
import com.blue.soft.store.entity.ItemMove;
import com.blue.soft.store.service.CompanyService;
import com.blue.soft.store.service.ItemMoveService;
import com.blue.soft.store.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	ItemService itemService;

	@Autowired
	ItemMoveService itemMoveService;

	@Autowired
	CompanyService companyService;

	@RequestMapping("/items-list")
	public String showItems(Model theModel) {

		theModel.addAttribute("itemsCount", itemService.getItemsCount());
		theModel.addAttribute("totalItemsBuys", itemService.getTotalItemsBuys());
		theModel.addAttribute("totalItemsSales", itemService.getTotalItemsSales());

		theModel.addAttribute("item", new Item());
		theModel.addAttribute("itemsList", itemService.getAllItems());
		theModel.addAttribute("items_list", "active");

		return "items-list";
	}

	@RequestMapping("/add-new-item")
	public String addNewItem(@ModelAttribute(name = "item") Item item, Model theModel) {

		itemService.addNewItem(item);

		return "redirect:/items-list";
	}

	@RequestMapping("/update-item")
	public String updateItem(@ModelAttribute(name = "item") Item newItem, Model theModel) {

		Item oldItem = itemService.getItemById(newItem.getId());

		String moveType = "";

		if (!oldItem.getName().equals(newItem.getName())) {
			moveType = "تعديل الاسم";

		}

		if (oldItem.getQuantity() != newItem.getQuantity()) {

			moveType = "تعديل الكمية";

		}

		if (oldItem.getSellPrice() != newItem.getSellPrice()) {

			moveType = "تعديل (س.ب)";

		}

		if (oldItem.getBuyPrice() != newItem.getBuyPrice())
			moveType = "تعديل (س.ش)";

		oldItem.setName(newItem.getName());
		oldItem.setQuantity(newItem.getQuantity());
		oldItem.setBuyPrice(newItem.getBuyPrice());
		oldItem.setSellPrice(newItem.getSellPrice());

		itemService.addNewItem(newItem);

		if (!moveType.equals("")) {

			itemMoveService.addItemMove(new ItemMove(oldItem, moveType, "0", oldItem.getQuantity(),
					oldItem.getQuantity(), oldItem.getBuyPrice(), oldItem.getSellPrice()));

		}

		return "redirect:/items-list";
	}

	@RequestMapping("/item-moves")
	public String showItemsMovePage(@RequestParam(name = "itemId", required = false) String itemId,
			@RequestParam(name = "dateFrom", required = false, defaultValue = "") String dateFrom,
			@RequestParam(name = "dateTo", required = false, defaultValue = "") String dateTo,
			@RequestParam(name = "moveType", required = false, defaultValue = "") String moveType, Model theModel) {

		String tempDateFrom = dateFrom.equals("") ? "2020-01-01" : dateFrom;
		String tempDateTo = dateTo.equals("") ? LocalDate.now().toString() : dateTo;

		theModel.addAttribute("movesList",
				itemMoveService.getAllItemMovesByDateAndType(itemId, tempDateFrom, tempDateTo, moveType));

		theModel.addAttribute("moveType", moveType);
		theModel.addAttribute("dateTo", dateTo);
		theModel.addAttribute("dateFrom", dateFrom);

		theModel.addAttribute("item", new Item());
		theModel.addAttribute("selectedItemId", itemId);
		theModel.addAttribute("itemsList", itemService.getAllItems());

		theModel.addAttribute("item_moves", "active");

		return "item-moves";
	}

//	@RequestMapping("/add-sell-bill-item")
//	public String addNewItem(@ModelAttribute(name = "item") Item item, Model theModel) {
//		
//		itemService.addNewItem(item);
//
//		return "redirect:/items-list";
//	}

}
