package com.blue.soft.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blue.soft.store.entity.Item;
import com.blue.soft.store.service.CompanyService;
import com.blue.soft.store.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	ItemService itemService;

	@Autowired
	CompanyService companyService;

	@RequestMapping("/items-list")
	public String showItems(Model theModel) {

		theModel.addAttribute("item", new Item());
		theModel.addAttribute("itemsList", itemService.getAllItems());
		theModel.addAttribute("companiesList", companyService.getAllCompanies());

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

//	@RequestMapping("/add-sell-bill-item")
//	public String addNewItem(@ModelAttribute(name = "item") Item item, Model theModel) {
//		
//		itemService.addNewItem(item);
//
//		return "redirect:/items-list";
//	}
}
