package com.blue.soft.store.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blue.soft.store.entity.Bank;
import com.blue.soft.store.entity.User;
import com.blue.soft.store.service.BankService;
import com.blue.soft.store.service.BillBuyItemsService;
import com.blue.soft.store.service.BillBuyService;
import com.blue.soft.store.service.BillReturnItemsService;
import com.blue.soft.store.service.BillReturnService;
import com.blue.soft.store.service.BillSellItemsService;
import com.blue.soft.store.service.BillSellService;
import com.blue.soft.store.service.ClientService;
import com.blue.soft.store.service.CompanyBillReturnItemsService;
import com.blue.soft.store.service.CompanyService;
import com.blue.soft.store.service.ItemService;
import com.blue.soft.store.service.SpendService;
import com.blue.soft.store.service.UserService;
import com.smattme.MysqlExportService;

@Controller
public class LoginController {

	@Autowired
	ItemService itemService;

	@Autowired
	SpendService spendService;

	@Autowired
	ClientService clientSerivce;

	@Autowired
	CompanyService companyService;

	@Autowired
	BillSellService billSellService;

	@Autowired
	BillBuyService billBuyService;

	@Autowired
	BillReturnService billReturnService;

	@Autowired
	ClientService clientService;

	@Autowired
	BillSellItemsService billSellItemsService;

	@Autowired
	BillBuyItemsService billBuyItemsService;

	@Autowired
	BillReturnItemsService billReturnItemsService;

	@Autowired
	CompanyBillReturnItemsService companyBillReturnItemsService;

	@Autowired
	BankService bankService;

	@Autowired
	BankController bankController;

	@Autowired
	UserService userService;

	@Autowired
	HttpSession httpSession;

	@RequestMapping("/show-login")
	public String showLogin(Model theModle) {

		theModle.addAttribute("user", new User());
		return "login";

	}

	@RequestMapping("/login")
	public String login(@ModelAttribute(name = "user") User theUser, Model theModle) {

		boolean isUser = userService.checkUser(theUser.getName(), theUser.getPassword());

		if (isUser) {

			return "redirect:/home";

		} else {

			return "login";

		}

	}

	@RequestMapping("/home")
	public String gotToHome(Model theModel) {

		String date = LocalDate.now().toString();

		theModel.addAttribute("bank", bankController.getTheBank());

		theModel.addAttribute("clientDraweeTotal", clientSerivce.getDraweeTotal());
		theModel.addAttribute("companyDraweeTotal", companyService.getDraweeTotal());

		theModel.addAttribute("totalPayedSales", billSellItemsService.getTotalPayedSalesByDate(date, date));
		theModel.addAttribute("totalLateSales", billSellItemsService.getTotalLateSalesByDate(date, date));

		theModel.addAttribute("totalReturns", billReturnItemsService.getTotalClientsReturnsByDate(date, date));
		theModel.addAttribute("totalCompaniesReturns",
				companyBillReturnItemsService.getTotalCompaniesReturnsByDate(date, date));

		theModel.addAttribute("totalBuys", billBuyItemsService.getTotalBuysByDate(date, date));

		theModel.addAttribute("totalGain", billSellItemsService.getTotalGainsByDate(date, date));

		theModel.addAttribute("spendTotal", spendService.getSpendTotalByDate(date, date));

		theModel.addAttribute("home", "active");

		return "home";

	}

	@RequestMapping("/logout")
	public String logout(Model theModle) throws ClassNotFoundException, IOException, SQLException {

		Properties properties = new Properties();
		properties.setProperty(com.smattme.MysqlExportService.DB_NAME, "warehouse");
		properties.setProperty(com.smattme.MysqlExportService.DB_USERNAME, "admin");
		properties.setProperty(com.smattme.MysqlExportService.DB_PASSWORD, "1234");
		properties.setProperty(com.smattme.MysqlExportService.TEMP_DIR, new java.io.File("E:/external/").getPath());
		properties.setProperty(MysqlExportService.PRESERVE_GENERATED_ZIP, "true");

		com.smattme.MysqlExportService mysqlExportService = new com.smattme.MysqlExportService(properties);
		mysqlExportService.export();

//		Backupdbtosql();

		httpSession.removeAttribute("name");
		theModle.addAttribute("user", new User());

		return "login";

	}

	public static void Backupdbtosql() {
		try {

//			String executeCmd = "‪C:\\xampp\\mysql\\bin\\mysqldump.exe --user=root --password= warehouse > c:\\warehouse_backup1.sql";
			String executeCmd = "mysqldump --user=root --password= warehouse > c:/warehouse_backup1.sql";
//			Process runtimeProcess = Runtime.getRuntime().exec(new String[] { "cmd.exe",
//					"cd \"C:\\xampp\\mysql\\bin\" && mysqldump --user=root --password= warehouse > c:/warehouse_backup1.sql" });
//
//			Process runtimeProcess = Runtime.getRuntime().exec(
//					"cd C:/xampp/mysql/bin/ mysqldump --user=root --password= warehouse > c:/warehouse_backup1.sql");

			String commandArray[] = { "cmd", "cd C:\\xampp\\mysql\\bin\\",
					"mysqldump --user=root --password= warehouse > warehouse_backup.sql" };
			Process process = Runtime.getRuntime().exec(commandArray);
			int processComplete = process.waitFor();

			/*
			 * NOTE: processComplete=0 if correctly executed, will contain other values if
			 * not
			 */
			if (processComplete == 0) {
				System.out.println("Backup Complete");
			} else {
				System.out.println("Backup Failure");
			}

		} catch (IOException | InterruptedException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
