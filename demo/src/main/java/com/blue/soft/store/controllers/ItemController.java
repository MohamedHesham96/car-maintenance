package com.blue.soft.store.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blue.soft.store.entity.Item;
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

		return "items-list";
	}

	@RequestMapping("/add-new-item")
	public String addNewItem(@ModelAttribute(name = "item") Item item, Model theModel) {

		itemService.addNewItem(item);

		return "redirect:/items-list";
	}

	@RequestMapping("/update-item")
	public String updateItem(@ModelAttribute(name = "item") Item item, Model theModel) {

		Item oldItem = itemService.getItemById(item.getId());

		oldItem.setName(item.getName());
		oldItem.setQuantity(item.getQuantity());
		oldItem.setBuyPrice(item.getBuyPrice());
		oldItem.setSellPrice(item.getSellPrice());

		itemService.addNewItem(item);

		return "redirect:/items-list";
	}

	@RequestMapping("/item-moves")
	public String showItemsMovePage(@RequestParam(name = "itemId", required = false) String itemId,
			@RequestParam(name = "dateFrom", required = false, defaultValue = "") String dateFrom,
			@RequestParam(name = "dateTo", required = false, defaultValue = "") String dateTo, Model theModel) {

		if (dateFrom.equals("") && dateTo.equals("")) {

			theModel.addAttribute("movesList", itemMoveService.getItemMovesByItemId(itemId));
		}

		else if (!dateFrom.equals("") && !dateTo.equals("")) {

			theModel.addAttribute("movesList", itemMoveService.getAllItemMovesByDate(itemId, dateFrom, dateTo));

		} else if (dateFrom.equals("")) {

			dateFrom = "2020-01-01";
			theModel.addAttribute("movesList", itemMoveService.getAllItemMovesByDate(itemId, dateFrom, dateTo));

		} else if (dateTo.equals("")) {

			dateTo = LocalDate.now().toString();
			theModel.addAttribute("movesList", itemMoveService.getAllItemMovesByDate(itemId, dateFrom, dateTo));

		}

		theModel.addAttribute("dateTo", dateTo);
		theModel.addAttribute("dateFrom", dateFrom);

		System.out.println(dateFrom + ": dateFrom" + "\n" + dateTo + ": dateTo");
		theModel.addAttribute("item", new Item());
		theModel.addAttribute("selectedItemId", itemId);
		theModel.addAttribute("itemsList", itemService.getAllItems());

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
