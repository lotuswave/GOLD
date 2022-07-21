package models.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;

public class Adminhelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public Adminhelper(String datafile) {
		excelData = new ExcelReader(datafile);
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("Admin");
			report.createTestcase("AdminTestCases");
		} else {
			this.report = Utilities.TestListener.report;
		}
	}

	public void Customers() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Common.clickElement("id", "menu-magento-customer-customer");
			Sync.waitElementPresent("id", "menu-magento-customer-customer");
			String customers = Common.findElement("xpath", "//strong[contains(text(),'Customers')]").getText();
			System.out.println(customers);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(customers.equals("Customers"), "User need to display in customer field",
					"User should able to click on the customers button",
					"customers button expands the customer fields dispalyed", "Failed to expand customer fileds");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("User need to display in customer field",
					"User should able to click on the customers button",
					"customers button expands the customer fileds displayed",
					Common.getscreenShotPathforReport("User unable to expands the customer"));
			Assert.fail();
		}

	}

	public void Newcustomer(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//button[@data-action='grid-filter-expand']");
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			Common.textBoxInput("xpath", "//input[@name='email']", data.get(Dataset).get("Email"));
			Common.actionsKeyPress(Keys.ENTER);
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			String records = Common.findElement("xpath", "//div[@class='admin__control-support-text']").getText();
			if (records.equals("0 records found")) {
				Sync.waitElementPresent("xpath", "//button[@title='Add New Customer']");
				Common.clickElement("xpath", "//button[@title='Add New Customer']");
			} else {
				Delete_exiting_customer("Customer");
				Sync.waitElementPresent("xpath", "//button[@title='Add New Customer']");
				Common.clickElement("xpath", "//button[@title='Add New Customer']");
			}
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("New Customer / Customers / Customers / Magento Admin"),
					"Validating New customer page navigation ", "after clicking on it will navigate new Customer page",
					"Successfully navigate to new Customer page", "Failed to navigate to New Customer page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating New customer page navigation ",
					"after clicking on it will navigate new Customer page",
					"Successfully navigate to new Customer page",
					Common.getscreenShotPathforReport("Failed to navigate to New Customer page"));
			Assert.fail();

		}
	}

	public void Updatedetails(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//button[@data-action='grid-filter-expand']");
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			Common.textBoxInput("xpath", "//input[@name='email']", data.get(Dataset).get("Email"));
			Common.actionsKeyPress(Keys.ENTER);
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			String records = Common.findElement("xpath", "//div[@class='admin__control-support-text']").getText();
			System.out.println(records);
			if (records.equals("1 records found")) {
				Common.clickElement("xpath", "//a[text()='Edit']");
				Common.assertionCheckwithReport(Common.getPageTitle().contains("testing qa / Customers"),
						"Validating user selects the edit button",
						"After clicking edit button user should navigates to the selected page",
						"Successfully navigate to user the selected page", "Failed to navigate to user selected page");

			} else {
				Assert.fail();
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating user selects the edit button",
					"After clicking edit button user should navigates to the selected page",
					"Successfully navigate to user the selected page",
					Common.getscreenShotPathforReport("Failed to navigate to Customer filed page"));
			Assert.fail();

		}
	}

	public void Delete_customer(String Dataset) {
		// TODO Auto-generated method stub
		try {

			Sync.waitElementPresent("xpath", "//button[@data-action='grid-filter-expand']");
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			Common.textBoxInput("xpath", "//input[@name='email']", data.get(Dataset).get("Email"));
			Common.actionsKeyPress(Keys.ENTER);
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			String records = Common.findElement("xpath", "//div[@class='admin__control-support-text']").getText();
			if (records.equals("1 records found")) {
				Common.clickElement("xpath", "//a[text()='Edit']");
			} else {
				Assert.fail();
			}
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//button[@title='Delete Customer']");
			Common.clickElement("xpath", "//button[@title='Delete Customer']");
			Sync.waitPageLoad();
			String message = Common.findElement("xpath", "//div[@class='modal-content']").getText();
			if (message.equals("Are you sure you want to do this?")) {
				Common.clickElement("xpath", "//span[text()='OK']");

			} else {
				Assert.fail();
			}
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//div[@data-ui-id='messages-message-success']");
			String savemessage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Customers / Customers / Magento Admin")
							&& savemessage.equals("You deleted the customer."),
					"Validating customers filed page navigation and customer deleted message",
					"after clicking save button it will navigate Customer filed page and message should be displayed",
					"Successfully navigate to Customer filed page and message is dispalyed",
					"Failed to navigate to Customer filed page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating customers filed page navigation ",
					"after clicking save button it will navigate Customer filed page",
					"Successfully navigate to Customer filed page",
					Common.getscreenShotPathforReport("Failed to navigate to Customer filed page"));
			Assert.fail();
		}

	}

	public void Admin_signin() {
		try {

			Sync.waitElementClickable("xpath", "//a[@class='action login primary']");
			Common.javascriptclickElement("xpath", "//a[@class='action login primary']");
			Sync.waitPageLoad(30);
			Sync.waitElementPresent("name", "loginfmt");
			Common.textBoxInput("name", "loginfmt", "avayugundla@helenoftroy.com");
			Common.clickElement("id", "idSIButton9");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Sync.waitElementPresent(30, "name", "passwd");
			Common.textBoxInput("name", "passwd", "Axnstqjguhqy1!");
			Common.clickElement("id", "idSIButton9");
			Thread.sleep(5000);
			Common.clickElement("id", "idSIButton9");
			Sync.waitElementPresent(30, "xpath", "//h1[@class='page-title']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Dashboard / Magento Admin"),
					"Validate the Customer menu is displayed", "Should display the customer menu",
					"Customer menu is displayed", "Failed to display the customer menu");

		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("Valiadte the Admin is able to login and land on Dashboard",
					"After successfull login should land on Dashboard of admin panel",
					"Admin is able to successfully login", "Failed to signin into Admin panel");

			Assert.fail();
		}

	}

	public void NewcustomerCTA() {
		// TODO Auto-generated method stub

		try {
			Sync.waitElementPresent("xpath", "//span[text()='All Customers']");
			Common.clickElement("xpath", "//span[text()='All Customers']");
			String newcustomer = Common.findElement("xpath", "//button[@id='add']").getAttribute("title");
			System.out.println(newcustomer);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(newcustomer.equals("Add New Customer"),
					"validating new customer CTA Button in Customers page", "able to display Add new Customer CTA ",
					"successfully displayed Add New Customer CTA", "Failed to display Add New Customer CTA");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("validating new customer CTA Button in Customers page",
					"able to display Add new Customer CTA", "successfully display Add New Customer CTA",
					Common.getscreenShotPathforReport("Failed to display Add New Customer CTA"));
			Assert.fail();
		}

	}

	public void Search_by_keyword() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//input[@id='fulltext']");
			Common.clickElement("xpath", "//input[@id='fulltext']");
			String Search = Common.findElement("xpath", "//input[@id='fulltext']").getAttribute("placeholder");
			Common.assertionCheckwithReport(Search.equals("Search by keyword"), "validating search by keyword input",
					"able to display search by keyword input", "successfully display search by keyword",
					"Failed to display search by keyword");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("validating search by keyword input", "able to display search by keyword input",
					"successfully display search by keyword",
					Common.getscreenShotPathforReport("Failed to display search by keyword"));
			Assert.fail();

		}

	}

	public void ActionCTA() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//div[@class='action-select-wrap']");
			Common.clickElement("xpath", "//div[@class='action-select-wrap']");
			String Action = Common.findElement("xpath", "//div[@class='action-menu-items']//ul").getAttribute("class");
			Common.assertionCheckwithReport(Action.contains("active"), "Validating Action dropdown CTA",
					"Able to display Action dropdown CTA", "Successfully action dropdown CTA displayed",
					"Failed to Display action dropdown CTA");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating Action dropdown CTA", "Able to display Action dropdown CTA",
					"Successfully action dropdown CTA displayed",
					Common.getscreenShotPathforReport("Failed to Display action dropdown CTA"));
			Assert.fail();
		}

	}

	public void FiltersCTA() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//button[@data-action='grid-filter-expand']");
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			String Filters = Common.findElement("xpath", "//button[@data-action='grid-filter-expand']")
					.getAttribute("class");
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			Common.assertionCheckwithReport(Filters.contains("active"), "Validating the Filters Selector",
					"Able to display the Filters Selector", "Successfully filters selector is diplayed",
					"Failed to Display Filters selector");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating the Filters Selector", "Able to display the Filters Selector",
					"Successfully filters selector is diplayed",
					Common.getscreenShotPathforReport("Failed to Display Filters selector"));
			Assert.fail();

		}
	}

	public void ColumnsCTA() {
		// TODO Auto-generated method stub
		try {

			Sync.waitElementPresent("xpath", "//span[text()='Columns']");
			Common.clickElement("xpath", "//span[text()='Columns']");
			String Columns = Common.findElement("xpath", "(//div[@data-bind='collapsible'])[2]").getAttribute("class");
			Common.assertionCheckwithReport(Columns.contains("active"), "Validating the columns configuration CTA ",
					"Able to display the columns Configuration CTA", "Successfully displayed Columns Configuration CTA",
					"Failed to display the Columns Configuration CTA");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating the columns configuration CTA ",
					"Able to display the columns Configuration CTA", "Successfully displayed Columns Configuration CTA",
					Common.getscreenShotPathforReport("Failed to display the Columns Configuration CTA"));
			Assert.fail();

		}

	}

	public void DefaultView() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//span[text()='Default View']");
			Common.clickElement("xpath", "//span[text()='Default View']");
			String Defaultview = Common.findElement("xpath", "//div[@data-bind='collapsible']").getAttribute("class");
			Common.assertionCheckwithReport(Defaultview.contains("active"), "Validating the Default view Selector CTA ",
					"Able to display the Default view Selector CTA ", "Successfully Default view selector CTA",
					"Failed to Display view Selector CTA");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating the Default view Selector CTA",
					"Able to display the Default view Selector CTA ", "Successfully Default view selector CTA",
					Common.getscreenShotPathforReport("Failed to Display view Selector CTA"));
			Assert.fail();

		}

	}

	public void PaginationCTA() {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//button[@title='Next Page']");
			String pagination = Common.findElement("xpath", "//button[@title='Next Page']").getAttribute("class");
			Common.assertionCheckwithReport(pagination.contains("next"), "Validating the pagination CTA ",
					"Able to display the pagination CTA ", "Successfully Dispaly Pageination CTA",
					"Failed to Display Pagination CTA");
			Common.clickElement("xpath", "//button[@title='Previous Page']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating the pagination CTA ", "Able to display the pagination CTA ",
					"Successfully Dispaly Pageination CTA",
					Common.getscreenShotPathforReport("Failed to Display Pagination CTA"));
			Assert.fail();

		}

	}

	public void EditCTA() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//a[text()='Edit']");
			String Edit = Common.findElement("xpath", "//a[text()='Edit']").getText();
			System.out.println(Edit);
			Common.assertionCheckwithReport(Edit.equals("Edit"), "Validating the Edit CTA ",
					"Able to display the Edit CTA ", "Successfully Dispaly Edit CTA", "Failed to Display Edit CTA");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating the Edit CTA ", "Able to display the Edit CTA ",
					"Successfully Dispaly Edit CTA", Common.getscreenShotPathforReport("Failed to Display Edit CTA"));
			Assert.fail();

		}

	}

	public void Clearfilter() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[text()='Clear all']");
			Common.javascriptclickElement("xpath", "//button[text()='Clear all']");
			String clear = Common.findElement("xpath", "//div[contains(@class,'admin__data-grid-filters-c')]")
					.getAttribute("class");
			System.out.println(clear);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(clear.contains("current"), "Validating the Clear filters ",
					"Able to clear the all filters ", "Successfully filters has been cleared ",
					"Failed to Clear all filters");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating the Clear filters ", "Able to clear the all filters ",
					"Successfully filters has been cleared",
					Common.getscreenShotPathforReport("Failed to Clear all filters"));
			Assert.fail();

		}

	}

	public void Allcustomers() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//span[text()='All Customers']");
			Common.clickElement("xpath", "//span[text()='All Customers']");
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Customers / Customers / Magento Admin"),
					"Validating customers filed page navigation ",
					"after clicking all customers it will navigate Customer filed page",
					"Successfully navigate to the Customer filed page ",
					"Failed to navigate to the Customer filed page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating customers filed page navigation ",
					"after clicking all customers it will navigate Customer filed page",
					"Successfully navigate to Customer filed page",
					Common.getscreenShotPathforReport("Failed to navigate  Customer filed page"));
			Assert.fail();

		}

	}

	public void Customerdetails(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//select[@name='customer[website_id]']");
			Common.dropdown("xpath", "//select[@name='customer[website_id]']", Common.SelectBy.TEXT,
					data.get(Dataset).get("Website"));
			Common.clickElement("xpath", "//select[@name='customer[group_id]']");
			Common.dropdown("xpath", "//select[@name='customer[group_id]']", Common.SelectBy.TEXT,
					data.get(Dataset).get("Group"));
			Common.scrollIntoView("xpath", "//input[@name='customer[firstname]']");
			Common.textBoxInput("xpath", "//input[@name='customer[firstname]']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='customer[lastname]']", data.get(Dataset).get("LastName"));
			Common.textBoxInput("xpath", "//input[@name='customer[email]']", data.get(Dataset).get("Email"));
			Common.clickElement("xpath", "//input[@name='customer[dob]']");
			Common.textBoxInput("xpath", "//input[@name='customer[dob]']", data.get(Dataset).get("DOB"));
			Sync.waitElementPresent("xpath", "//div[contains(@class,'ui-datepicker-b')]//button");
			Common.clickElement("xpath", "//div[contains(@class,'ui-datepicker-b')]//button");
			Common.clickElement("xpath", "//select[contains(@name,'customer[ex')]");
			Common.dropdown("xpath", "//select[contains(@name,'customer[ex')]", Common.SelectBy.TEXT,
					data.get(Dataset).get("State"));
			Common.clickElement("xpath", "//select[@name='customer[gender]']");
			Common.dropdown("xpath", "//select[@name='customer[gender]']", Common.SelectBy.TEXT,
					data.get(Dataset).get("Gender"));
			Common.clickElement("xpath", "//select[@name='customer[sendemail_store_id]']");
//			Common.dropdown("xpath", "//select[@name='customer[sendemail_store_id]']", Common.SelectBy.TEXT,
//					data.get(Dataset).get("Welcome Email"));

			Common.actionsKeyPress(Keys.END);
			Sync.waitElementPresent("xpath", "//select[@name='customer[prodeal_status]']");
			Common.clickElement("xpath", "//select[@name='customer[prodeal_status]']");
			Common.dropdown("xpath", "//select[@name='customer[prodeal_status]']", Common.SelectBy.TEXT,
					data.get(Dataset).get("ProDeal"));

//			Sync.waitElementPresent("xpath", "//input[@name='customer[prodeal_accept_date]']");
//			Common.clickElement("xpath", "//input[@name='customer[prodeal_accept_date]']");
//			Common.dropdown("xpath", "//input[@name='customer[prodeal_accept_date]']", Common.SelectBy.TEXT,
//					data.get(Dataset).get("DOB"));

			Common.clickElement("xpath", "//button[@title='Save Customer']");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//div[@data-ui-id='messages-message-success']");
			String savemessage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Customers / Customers / Magento Admin")
							&& savemessage.equals("You saved the customer."),
					"Validating customers filed page navigation and saved message ",
					"after clicking save button it will navigate Customer filed page and it should be display save message",
					"Successfully navigate to Customer filed page and save message has displayed",
					"Failed to navigate to Customer filed page and save message not displayed");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating customers filed page navigation and saved message ",
					"after clicking save button it will navigate Customer filed page and it should be display save message",
					"Successfully navigate to Customer filed page and save message has displayed",
					Common.getscreenShotPathforReport(
							"Failed to navigate to Customer filed page and save message not displayed"));
			Assert.fail();
		}
	}

	public void Delete_exiting_customer(String Dataset) {
		// TODO Auto-generated method stub
		try {

			Common.clickElement("xpath", "//a[text()='Edit']");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//button[@title='Delete Customer']");
			Common.clickElement("xpath", "//button[@title='Delete Customer']");
			String message = Common.findElement("xpath", "//div[@class='modal-content']").getText();
			if (message.equals("Are you sure you want to do this?")) {
				Common.clickElement("xpath", "//span[text()='OK']");

			} else {
				Assert.fail();
			}
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//div[@data-ui-id='messages-message-success']");
			String savemessage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Customers / Customers / Magento Admin")
							&& savemessage.equals("You deleted the customer."),
					"Validating customers filed page navigation and customer deleted message",
					"after clicking save button it will navigate Customer filed page and message should be displayed",
					"Successfully navigate to Customer filed page and message is dispalyed",
					"Failed to navigate to Customer filed page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating customers filed page navigation ",
					"after clicking save button it will navigate Customer filed page",
					"Successfully navigate to Customer filed page",
					Common.getscreenShotPathforReport("Failed to navigate to Customer filed page"));
			Assert.fail();
		}

	}

	public void Newsletter() {
		// TODO Auto-generated method stub
		try {

			Common.clickElement("xpath", "//a[@id='tab_newsletter_content']");
			String newsletter = Common.findElement("xpath", "//label[@class='label admin__field-label']").getText();
			if (newsletter.equals("Subscribed to Newsletter")) {
				Sync.waitElementPresent("xpath", "//label[@class='label admin__field-label']");
				Common.clickElement("xpath", "//label[@class='label admin__field-label']");
				String checkbox = Common.findElement("xpath", "//input[@name='subscription_status[1]']")
						.getAttribute("value");
				Common.assertionCheckwithReport(checkbox.equals("true"), "Validating the newsletter checkout",
						"newsletter checkbox should be selected", "Successfully newsletter checkout selected",
						"Failed to select newsletter checkbox");

			} else {
				Assert.fail();
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating the newsletter checkout", "newsletter checkbox should be selected",
					"Successfully newsletter checkout selected",
					Common.getscreenShotPathforReport("Failed to select newsletter checkbox"));
			Assert.fail();
		}

	}

	public void Address(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//a[@id='tab_address']");
			Sync.waitElementPresent("xpath", "//a[@id='tab_address']");
			Common.clickElement("xpath", "//button[contains(@class,'add-new')]");
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//input[@name='street[0]']");
			Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(Dataset).get("Street"));
			Common.actionsKeyPress(Keys.END);
			Sync.waitElementPresent("xpath", "//div[@class='admin__field-control']//select[@name='country_id']");
			Common.clickElement("xpath", "//div[@class='admin__field-control']//select[@name='country_id']");
			Common.dropdown("xpath", "//div[@class='admin__field-control']//select[@name='country_id']",
					Common.SelectBy.TEXT, data.get(Dataset).get("Country"));
			Sync.waitElementPresent("xpath", "//select[@name='region_id']");
			Common.clickElement("xpath", "//select[@name='region_id']");
			Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT,
					data.get(Dataset).get("State"));
			Common.clickElement("xpath", "//input[@name='city']");
			Common.textBoxInput("xpath", "//input[@name='city']", data.get(Dataset).get("City"));
			Common.clickElement("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(Dataset).get("Postcode"));
			Common.clickElement("xpath", "//div[@class='admin__field-control']//input[@name='telephone']");
			Common.textBoxInput("xpath", "//div[@class='admin__field-control']//input[@name='telephone']",
					data.get(Dataset).get("Phonenumber"));
			Common.clickElement("xpath", "//button[@title='Save']");
			Sync.waitPageLoad(40);
			Thread.sleep(8000);
			String records = Common.findElement("xpath", "//div[@class='admin__control-support-text']").getText();
			System.out.println(records);
			Common.assertionCheckwithReport(records.equals("1 records found"), "Validating the address with records",
					"User should be able save address ", "Successfully address has been saved",
					"Failed to save the address");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating the address with records", "User should be able save address ",
					"Successfully address has been saved",
					Common.getscreenShotPathforReport("Failed to save the address"));
			Assert.fail();
		}
	}

	public void Orders(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//a[@id='tab_orders_content']");
			Common.clickElement("xpath", "//a[@id='tab_orders_content']");
			Common.clickElement("xpath", "//input[@name='increment_id']");
			Common.textBoxInput("xpath", "//input[@name='increment_id']", data.get(Dataset).get("ordernumber"));
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			String orderrcords = Common.findElement("xpath", "//div[@class='admin__control-support-text']").getText();
			System.out.println(orderrcords);
			if (orderrcords.equals("0  records found")) {
				Common.clickElement("xpath", "//span[text()='Reset Filter']");

			} else {
				String ordernumber = Common.findElement("xpath", "//input[@name='increment_id']").getAttribute("value");
				System.out.println(ordernumber);
				String number = data.get(Dataset).get("ordernumber");
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
				Common.assertionCheckwithReport(ordernumber.equals(number),
						"Validating the order number in orders page", "User should be able see order number  ",
						"Successfully order number is dispalyed in orders page",
						"Failed to see order number in order page");

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating the order number in orders page", "User should be able see order number  ",
					"Successfully order number is dispalyed in orders page",
					Common.getscreenShotPathforReport("Failed to see order number in order page"));
			Assert.fail();

		}
	}

	public void Returns(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//a[@class='admin__page-nav-link']//span[text()='Returns']");
			Common.clickElement("xpath", "//a[@class='admin__page-nav-link']//span[text()='Returns']");
			Common.clickElement("xpath", "//input[@name='order_increment_id']");
			Common.textBoxInput("xpath", "//input[@name='order_increment_id']", data.get(Dataset).get("ordernumber"));
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			String Returnsrecord = Common.findElement("xpath", "//div[@class='admin__control-support-text']").getText();
			if (Returnsrecord.equals("0  records found")) {
				Common.clickElement("xpath", "//span[text()='Reset Filter']");

			} else {
				String ordernumber = Common.findElement("xpath", "//input[@name='order_increment_id']")
						.getAttribute("value");
				System.out.println(ordernumber);
				String number = data.get(Dataset).get("ordernumber");
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
				Common.assertionCheckwithReport(ordernumber.equals(number),
						"Validating the order number in Returns page",
						"User should be able see order number in Returns page  ",
						"Successfully order number is dispalyed in Returns page",
						"Failed to see order number in Returns page");
			}

		} catch (Exception | Error e)

		{
			e.printStackTrace();
			report.addFailedLog("Validating the order number in Returns page",
					"User should be able see order number in Returns page  ",
					"Successfully order number is dispalyed in Returns page",
					Common.getscreenShotPathforReport("Failed to see order number in Returns page"));
			Assert.fail();

		}

	}

	public void Shoppingcart(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//a[@id='tab_cart_content']");
			Common.clickElement("xpath", "//a[@id='tab_cart_content']");
			Common.clickElement("xpath", "//select[@id='website_filter']");
			Common.dropdown("xpath", "//select[@id='website_filter']", Common.SelectBy.TEXT,
					data.get(Dataset).get("Website"));
			Common.clickElement("xpath", "//input[@name='sku']");
			Common.textBoxInput("xpath", "//input[@name='sku']", data.get(Dataset).get("SKU"));
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			String Shoppingrecord = Common.findElement("xpath", "//div[@class='admin__control-support-text']")
					.getText();
			if (Shoppingrecord.equals("0  records found")) {
				Common.clickElement("xpath", "//span[text()='Reset Filter']");

			} else {
				String SKU = Common.findElement("xpath", "//input[@name='sku']").getAttribute("value");
				System.out.println(SKU);
				String ShoppingSKU = data.get(Dataset).get("SKU");
				Common.assertionCheckwithReport(SKU.equals(ShoppingSKU), "Validating the Sku ID in Shopping cart page",
						"User should be able see Sku ID in Shopping cart page  ",
						"Successfully SKU ID is dispalyed in Shopping cart page",
						"Failed to see SKU ID in Shopping cart page");
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating the Sku ID in Shopping cart page",
					"User should be able see Sku ID in Shopping cart page  ",
					"Successfully SKU ID is dispalyed in Shopping cart page",
					Common.getscreenShotPathforReport("Failed to see SKU ID in Shopping cart page"));
			Assert.fail();

		}

	}

	public void Whishlist(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//a[@id='tab_wishlist_content']");
			Common.clickElement("xpath", "//a[@id='tab_wishlist_content']");
			Common.clickElement("xpath", "//input[@name='product_name']");
			Common.textBoxInput("xpath", "//input[@name='product_name']", data.get(Dataset).get("Productname"));
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			String Whishlist = Common.findElement("xpath", "//div[@class='admin__control-support-text']").getText();
			if (Whishlist.equals("0  records found")) {
				Common.clickElement("xpath", "//span[text()='Reset Filter']");

			} else {
				String product = Common.findElement("xpath", "//input[@name='product_name']").getAttribute("value");
				System.out.println(product);
				String name = data.get(Dataset).get("Productname");
				Common.assertionCheckwithReport(product.equals(name),
						"Validating the productname in the whishlist page",
						"User should be able see product name in the whishlist page  ",
						"Successfully productname is dispalyed in the whishlist page",
						"Failed to productname in the whishlist page");

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating the productname in the whishlist page",
					"User should be able see product name in the whishlist page  ",
					"Successfully productname is dispalyed in the whishlist page",
					Common.getscreenShotPathforReport("Failed to productname in the whishlist page"));
			Assert.fail();

		}

	}

	public void Savecustomer() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//button[@data-ui-id='save-button']");
			Common.clickElement("xpath", "//button[@data-ui-id='save-button']");
			Sync.waitPageLoad();
			String savemessage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
			System.out.println(savemessage);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Customers / Customers / Magento Admin"),
					"Validating customers filed page navigation",
					"after clicking save button it will navigate Customer filed page",
					"Successfully navigate to Customer filed page", "Failed to navigate to Customer filed page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating customers filed page navigation ",
					"after clicking save button it will navigate Customer filed page",
					"Successfully navigate to Customer filed page",
					Common.getscreenShotPathforReport("Failed to navigate to Customer filed page"));
			Assert.fail();

		}

	}

	public void Admin_signin(String dataSet) {

		try {

			Sync.waitElementClickable("xpath", "//a[@class='action login primary']");
			Common.javascriptclickElement("xpath", "//a[@class='action login primary']");
			Sync.waitPageLoad(30);
			Sync.waitElementPresent("name", "loginfmt");
			Common.textBoxInput("name", "loginfmt", data.get(dataSet).get("UserName"));
			Common.clickElement("id", "idSIButton9");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Sync.waitElementPresent(30, "name", "passwd");
			Common.textBoxInput("name", "passwd", data.get(dataSet).get("Password"));
			Common.clickElement("id", "idSIButton9");
			Sync.waitPageLoad();

			Sync.waitElementVisible(30, "xpath", "//div[@id='lightbox']");
			if (Common.isElementDisplayed("id", "KmsiCheckboxField")) {
				Common.javascriptclickElement("id", "KmsiCheckboxField");
			}
			Sync.waitElementClickable("id", "idSIButton9");
			Common.mouseOverClick("id", "idSIButton9");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Sync.waitElementPresent(30, "xpath", "//h1[@class='page-title']");

			Common.assertionCheckwithReport(Common.getPageTitle().equals(data.get(dataSet).get("title")),
					"Validate the Customer menu is displayed", "Should display the customer menu",
					"Customer menu is displayed", "Failed to display the customer menu");

		} catch (Exception e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("Validate the Customer menu is displayed",
					"After successfull login should land on Dashboard of admin panel",
					"Admin is not able to successfully login", "Failed to signin into Admin panel");
			Assert.fail();

		}

	}

	public void Click_customer() {
		try {
			Sync.waitPageLoad();
			Common.mouseOverClick("id", "menu-magento-customer-customer");
			String customers = Common.findElement("id", "menu-magento-customer-customer").getAttribute("class");
			Thread.sleep(3000);
			Common.assertionCheckwithReport(customers.contains("show"), "To verify the customer menu ",
					"After clicking the Customer menu it will display menu options ",
					"Successfully clicked the customer menu and it displayed the Customer options",
					"Failed to click the Customer menu");
			Sync.waitElementPresent("xpath", "//strong[text()='Customers']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("User need to display in customer field",
					"User should able to click on the customers button", "customers button expands the customer fileds",
					Common.getscreenShotPathforReport("User unable to expands the customer"));
			Assert.fail();
		}

	}

	public void Click_customergroups(String dataSet) {
		String pagetitle = data.get(dataSet).get("Customergrouppagetitle");
		try {
			Sync.waitElementVisible(30, "xpath", "//li[contains(@data-ui-id,'customer-customer-group')]/a");
			Common.javascriptclickElement("xpath", "//li[contains(@data-ui-id,'customer-customer-group')]/a");
			Sync.waitPageLoad(30);
			Sync.waitElementVisible("xpath", "//h1[@class='page-title']");
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals(pagetitle), "Validate the customer grops page",
					"Should navigate to the Customer groups page", "Successfully navigated to customer groups page",
					"Failed navigation to customer groups page");

		} catch (Exception e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("Validate the Customer groups page is displayed",
					"Should display the customer groups page", "Customer groups page is not displayed",
					"Failed to display the customer groups page");
			Assert.fail();

		}

	}

	public void Create_newcustomergroup(String dataSet) {
		String deletesuccessmessage = data.get(dataSet).get("Adminsuccessmesaage");
		String customergrouptitle = data.get(dataSet).get("Customergrouppagetitle");
		try {
			Apply_filter("Createcustomergroup");
			Delete_Existingcustomergroup("DeleteCustomergroup");

			Sync.waitElementVisible("xpath", "//button[@title='Add New Customer Group']");
			Common.javascriptclickElement("xpath", "//button[@title='Add New Customer Group']");
			Sync.waitPageLoad(30);
			Sync.waitElementVisible(30, "xpath", "//h1[@class='page-title']");
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals(data.get(dataSet).get("title")),
					"To validate the New customer groups page", "Should navigate to the New Customer groups page",
					"Successfully navigated to New customer groups page",
					"Failed navigation to New customer groups page");

			Sync.waitElementVisible("xpath", "//input[@id='customer_group_code']");
			Common.textBoxInput("xpath", "//input[@id='customer_group_code']",
					data.get(dataSet).get("customergroupname"));

			Select_taxclass("Createcustomergroup");

			Select_website("Websites");

			Sync.waitElementVisible(30, "xpath", "//button[@id='save']");
			Common.clickElement("xpath", "//button[@id='save']");
			Sync.waitPageLoad();
			Sync.waitElementVisible(30, "xpath", "//div[@class='message message-success success']/div");
			String Size = Common.findElement("xpath", "//div[@class='message message-success success']/div").getText();

			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(
					Size.equals(deletesuccessmessage) && Common.getPageTitle().contains(customergrouptitle),
					"To validate the New customer groups is saved",
					"Should create the New Customer groups successfully", "Successfully created the New customer group",
					"Failed to create New customer group");

		} catch (Exception e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("Validate the Customer groups page is displayed",
					"Should display the customer groups page", "Customer groups page is not displayed",
					"Failed to display the customer groups page");
			Assert.fail();

		}
	}

	public void Admin_logout() {
		try {
			Sync.waitElementClickable(30, "xpath", "//span[@class='admin-user-account-text']");
			Common.mouseOverClick("xpath", "//span[@class='admin-user-account-text']");
			Common.mouseOverClick("xpath", "//a[text()='Sign Out']");
			Sync.waitPageLoad(30);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Magento Admin"),
					"To validate the signout functionality", "Admin should successfully logout from the magento",
					"Admin has signout from magento", "Failed to logout from magento");

		} catch (Exception e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("To validate the signout functionality",
					"Admin should successfully logout from the magento", "Admin logout failed",
					"Failed to logout from magento");

			Assert.fail();
		}
	}

	public void Apply_filter(String dataSet) throws Exception {

		String customergroupname = data.get(dataSet).get("customergroupname");
		try {
			if (Common.isElementDisplayedonPage(30, "xpath", "//button[@class='action-remove']")) {
				Common.mouseOverClick("xpath", "//button[@class='action-remove']");
			} else {
				System.out.println("no Active filters found");
			}
			Sync.waitElementVisible("xpath", "//button[@data-action='grid-filter-expand']");
			Common.javascriptclickElement("xpath", "//button[@data-action='grid-filter-expand']");
			String filterexpand = Common.findElement("xpath", "//button[@data-action='grid-filter-expand']")
					.getAttribute("class");
			System.out.println(filterexpand);

			Common.assertionCheckwithReport(filterexpand.contains("active"), "To validate the Filter is expanded",
					"Should expand the Customer groups Filter successfully", "Successfully expanded the Filter",
					"Failed to expand the filter");
			Thread.sleep(3000);
			Sync.waitElementPresent(30, "name", "customer_group_code");
			Common.textBoxInput("name", "customer_group_code", customergroupname);
			Common.clickElement("xpath", "//span[text()='Apply Filters']");
			Sync.waitPageLoad();
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

			Sync.waitElementVisible(30, "xpath", "//ul[@class='admin__current-filters-list']/li");
			String filterresults = Common.findElement("xpath", "//ul[@class='admin__current-filters-list']/li")
					.getText();
			Common.assertionCheckwithReport(filterresults.contains(customergroupname),
					"To validate the filter is applied successfully",
					"Filter should be applied and should show the results for the text", "Filter results displayed",
					"Failed to dispaly the Filter results");

		} catch (Exception e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("Validate the Filter is applied for Customer group ",
					"Should apply the filter for customer groups", "Customer groups is not updated	",
					"Failed to update the customer groups page");
			Assert.fail();
		}
	}

	public void Click_edit(String dataSet) {
		String title = data.get(dataSet).get("title");

		try {
			if (Common.isElementDisplayed("xpath", "//tr[@class='data-row']")) {
				Sync.waitElementClickable("xpath", "//button[text()='Select']");
				Thread.sleep(3000);
				Common.clickElement("xpath", "//button[text()='Select']");

				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Sync.waitElementVisible("xpath", "//a[text()='Edit']");
				Common.clickElement("xpath", "//a[text()='Edit']");
				Sync.waitPageLoad();

				Sync.waitElementVisible(30, "xpath", "//h1[@class='page-title']");
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

				Common.assertionCheckwithReport(Common.getPageTitle().equals(title),
						"To validate the admin is navigated to customer groups page",
						"Should navigate to the existing Customer groups page",
						"Successfully navigated to customer groups page", "Failed navigation to customer groups page");

			} else {
				System.out.println("0 Filter results found");
			}

		} catch (Exception e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("Validate the Customer group is updated",
					"Should update the customer groups", "Customer groups is not updated	",
					"Failed to update the customer groups page");
			Assert.fail();
		}
	}

	public void Update_customergroup(String dataSet) {
		String title = data.get(dataSet).get("Customergrouppagetitle");
		String successmessage = data.get(dataSet).get("Adminsuccessmesaage");
		try {

			Sync.waitElementVisible("xpath", "//input[@id='customer_group_code']");
			// Common.textBoxInputClear("xpath", "//input[@id='customer_group_code']");
			Common.textBoxInput("xpath", "//input[@id='customer_group_code']",
					data.get(dataSet).get("Updatecustomergroupname"));
			Sync.waitElementVisible(30, "xpath", "//button[@id='save']");
			Common.clickElement("xpath", "//button[@id='save']");
			Sync.waitPageLoad();
			Sync.waitElementVisible(30, "xpath", "//div[@class='message message-success success']/div");
			String Size = Common.findElement("xpath", "//div[@class='message message-success success']/div").getText();
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(Size.equals(successmessage) && Common.getPageTitle().contains(title),
					"Validate the Customer group is updated", "Should update the customer groups",
					"Successfully updated the New customer group", "Failed to update the New customer group");

		} catch (Exception e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("Validate the Customer group is updated",
					"Should update the customer groups", "Customer groups is not updated",
					"Failed to update the customer groups page");
			Assert.fail();

		}
	}

	public void Delete_Existingcustomergroup(String dataSet) {

		try {
			int group = Common.findElements("xpath", "//tr[contains(@class,'row')]").size();

			if (group > 0) {
				for (int i = 1; i <= group; i++) {
					Sync.waitElementVisible(30, "xpath", "(//button[@class='action-select'])[" + i + "]");
					Common.javascriptclickElement("xpath", "(//button[@class='action-select'])[" + i + "]");
					Sync.waitElementVisible(30, "xpath", "(//a[text()='Delete'])[" + i + "]");
					Common.mouseOverClick("xpath", "(//a[text()='Delete'])[" + i + "]");
					Sync.waitElementVisible(30, "xpath", "//button[@class='action-primary action-accept']");
					Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
					Sync.waitPageLoad();
					String successmessage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']")
							.getText();
					Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

					Common.assertionCheckwithReport(successmessage.equals(data.get(dataSet).get("Adminsuccessmesaage")),
							"To validate the existing customer is deleted", "The Customer group should be deleted",
							"The Customer group is deleted", "Failed to display the successmessage");
				}
			} else {

				String nogroups = Common.findElement("xpath", "//tr[@class='data-grid-tr-no-data']/td").getText();
				System.out.println(nogroups);
			}

		} catch (Exception e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("To validate the existing customer is deleted",
					"The Customer group should be deleted", "Customer groups page is not deleted",
					"Failed to display the customer groups page");
			Assert.fail();

		}
	}

	public void Select_taxclass(String dataSet) throws Exception {
		try {
			Sync.waitElementPresent("xpath", "//select[@name='tax_class']");
			Common.dropdown("xpath", "//select[@name='tax_class']", Common.SelectBy.TEXT,
					data.get(dataSet).get("taxclass"));
		} catch (Exception e) {

			Assert.fail();
		}
	}

	public void Select_website(String dataSet) throws Exception {

		String[] websites = data.get(dataSet).get("website").split(",");

		for (int i = 0; i < websites.length; i++) {
			System.out.println(websites[i]);
			Sync.waitElementClickable("xpath", "//option[text()='" + websites[i] + "']");
			Common.clickElement("xpath", "//option[text()='" + websites[i] + "']");

		}
	}

	public void Customer_segments() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//span[contains(text(),'Segments')]");
			System.out.println(Common.getPageTitle());
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Customer Segments"),
					"User navigates to customer segment field", "User should navigate to the customers segment fields",
					"User navigates to the customer segment fields", "User unable to display customer segments ");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("User navigates to customer segment field",
					"User should navigate to the customer segment fields",
					"User navigates to the customer segment fields",
					Common.getscreenShotPathforReport("User unable to navigate to customer segment"));
			Assert.fail();
		}
	}

	public void Add_Save_customer_segments(String dataSet) {
		try {
			Sync.waitPageLoad();
			Common.textBoxInput("xpath", "//input[@id='customersegmentGrid_filter_grid_segment_name']",
					data.get(dataSet).get("SegmentName"));
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(8000);

			Sync.waitElementPresent("xpath", "//div[@class='admin__control-support-text']");
			String records = Common.findElement("xpath", "//div[@class='admin__control-support-text']").getText();
			System.out.println(records);
			if (records.equals("0 records found")) {
				Sync.waitElementPresent("xpath", "//button[@title='Add Segment']");
				Common.clickElement("xpath", "//button[@title='Add Segment']");
			} else {
				Delete_customer_segment("Customer Segment");
				Sync.waitElementPresent("xpath", "//button[@title='Add Segment']");
				Common.clickElement("xpath", "//button[@title='Add Segment']");
			}

//		System.out.println(Common.getPageTitle());
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(Common.getPageTitle().contains("New Segment"),
					"User creates New customer segment", "User should Navigate to New segment page ",
					"User Navigated to the New segment page", "Unable to Navigate to New Segment page");

			Sync.waitPageLoad();

			Common.textBoxInput("id", "segment_name", data.get(dataSet).get("SegmentName"));
			Select_website("Customer Segment");

			Common.scrollIntoView("xpath", "//select[@id='segment_is_active']");
			Common.clickElement("xpath", "//select[@id='segment_is_active']");

			Common.dropdown("xpath", "//select[@id='segment_is_active']", Common.SelectBy.TEXT,
					data.get(dataSet).get("AssignedStatus"));

			Common.clickElement("xpath", "//select[@id='segment_apply_to']");
			Common.dropdown("xpath", "//select[@id='segment_apply_to']", Common.SelectBy.TEXT,
					data.get(dataSet).get("ApplyTo"));

			Common.clickElement("id", "save");

			String message = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();

			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("Customer Segments") && message.contains("You saved the segment"),
					"User need to save new customer segment field", "User saves the new customer segment",
					"User saved the new customer segment", "unable to save the New Segment");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("User need to save customer segment field", "User saves the new customer segment",
					"User saved the new customer segment",
					Common.getscreenShotPathforReport("User unable to save the new customer segment"));
			Assert.fail();
		}
	}

	public void Edit_customer_segment(String dataSet) {
		String segmentName = data.get(dataSet).get("SegmentName");
		try {

			Sync.waitPageLoad();
			Common.textBoxInput("xpath", "//input[@id='customersegmentGrid_filter_grid_segment_name']", segmentName);
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Sync.waitElementClickable(30, "xpath", "//td[contains(text(),'" + segmentName + "')]");
			Common.javascriptclickElement("xpath", "//td[contains(text(),'" + segmentName + "')]");
			Sync.waitPageLoad(30);
			System.out.println(Common.getPageTitle());
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(Common.getPageTitle().contains(segmentName),
					"User selects to edit customer segment field", "User should able to edit the customer segment",
					"User Edit the customer segment", "Unable to Edit Customer segment");

//        Common.clickElement("xpath", "//textarea[@id='segment_description']");
			Common.textBoxInput("xpath", "//textarea[@id='segment_description']", data.get(dataSet).get("Description"));
			Common.scrollIntoView("xpath", "//select[@id='segment_is_active']");
			// Common.clickElement("xpath", "//select[@id='segment_is_active']");

			Common.dropdown("xpath", "//select[@id='segment_is_active']", Common.SelectBy.TEXT,
					data.get(dataSet).get("AssignedStatus"));

			Common.clickElement("id", "save");
			String message = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
			System.out.println(message);

			Common.assertionCheckwithReport(Common.getPageTitle().contains("Customer Segments"),
					"User need to save edited customer segment field",
					"User should able to save edited customer segment", "User saves the Edited customer segment",
					"Unable to save the Edited Customer segment");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("User need to save updated customer segment field",
					"User should able to save updated customer segment", "User saves the updated customer segment",
					Common.getscreenShotPathforReport("User unable to save the customer segment"));
			Assert.fail();
		}

	}

	public void Delete_customer_segment(String dataSet) {

		try {
			String segmentName = data.get(dataSet).get("SegmentName");
//		
			Common.clickElement("xpath", "//td[contains(text(),'" + segmentName + "')]");
//		Common.clickElement("xpath", "//td[text()='"+segmentName+ "']");
			Sync.waitPageLoad();
			System.out.println(segmentName);
			System.out.println(Common.getPageTitle());

			Common.assertionCheckwithReport(Common.getPageTitle().contains(segmentName),
					"User selects to delete existing customer segment field",
					"User should able to select the customer segment", "User selects the customer segment",
					"user unable to select customer segment");

			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Delete')]");
			Common.clickElement("xpath", "//span[contains(text(),'Delete')]");
			String message = Common.getText("xpath", "(//div[@class='modal-content'])[2]");
			if (message.equals("Are you sure you want to do this?")) {

				Common.clickElement("xpath", "//span[text()='OK']");
			} else {
				Assert.fail();
			}

			String message1 = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
			System.out.println(message1);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Customer Segments"),
					"User need to delete customer segment field", "User should able to delete the customer segment",
					"User deletes the customer segment", " user unable to delete customer segment");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("User need to delete customer segment field",
					"User should able to delete the customer segment", "User deletes the customer segment",
					Common.getscreenShotPathforReport("User unable to delete the customer segment"));
			Assert.fail();
		}

	}

	public void click_content() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Common.clickElement("id", "menu-magento-backend-content");
			Sync.waitElementPresent("id", "menu-magento-backend-content");
			String content = Common.findElement("xpath", "//strong[contains(text(),'Content')]").getText();
			System.out.println(content);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(content.equals("Content"), "To verify the content menu ",
					"After clicking the content menu it will display menu options ",
					"Successfully clicked the content menu and it displayed the Content options",
					"Failed to click the Content menu");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("To verify the content menu ",
					"After clicking the content menu it will display menu options ",
					"Successfully clicked the content menu and it displayed the Content options",
					Common.getscreenShotPathforReport("Failed to click on the content menu"));
			Assert.fail();
		}

	}

	public void pages() {
		// TODO Auto-generated method stub

		try {
			Sync.waitElementPresent("xpath", "//span[text()='Pages']");
			Common.clickElement("xpath", "//span[text()='Pages']");
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Pages / Magento Admin"),
					"Validating content filed page navigation ", "after clicking on pages it will navigate page filed ",
					"Successfully navigate to the page filed ", "Failed to navigate to the page filed");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating content filed page navigation ",
					"after clicking on pages it will navigate page filed ", "Successfully navigate to the page filed ",
					Common.getscreenShotPathforReport("Failed to navigate to the page filed"));
			Assert.fail();

		}

	}

	public void newpageCTA(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//button[@data-action='grid-filter-expand']");
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			Common.textBoxInput("xpath", "//input[@name='title']", data.get(Dataset).get("pageTitle"));
			Common.actionsKeyPress(Keys.ENTER);
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			String records = Common.findElement("xpath", "//div[@class='admin__control-support-text']").getText();
			if (records.equals("0 records found")) {
				Sync.waitElementPresent(30, "xpath", "//button[@title='Add New Page']");
				Common.clickElement("xpath", "//button[@title='Add New Page']");
			} else {
				Sync.waitElementPresent("xpath", "//button[text()='Select']");
				Common.clickElement("xpath", "//button[text()='Select']");
				Common.clickElement("xpath", "//a[text()='Edit']");
				Sync.waitPageLoad();
				delete_existing_page("promocontent");
				Sync.waitElementPresent(30, "xpath", "//button[@title='Add New Page']");
				Common.clickElement("xpath", "//button[@title='Add New Page']");
			}
			
			
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("New Page / Pages / Elements / Content / Magento Admin"),
					"Validating edit page bulider navigation ",
					"after clicking on edit page builder it will navigate edit page builder filed ",
					"Successfully navigate to the edit page builder filed ",
					"Failed to navigate to the edit page builder filed");
		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating edit page bulider navigation ",
					"after clicking on edit page builder it will navigate edit page builder filed ",
					"Successfully navigate to the edit page builder filed",
					Common.getscreenShotPathforReport("Failed to navigate to the edit page builder filed"));
			Assert.fail();
		}
	}

	public void hot_elements() {
		try {
			Sync.waitElementPresent("xpath", "//h4[text()='HoT Elements']");
			Common.clickElement("xpath", "//h4[text()='HoT Elements']");
			String hotelements = Common.findElement("xpath", "//li[@id='menu-section-hot_elements']")
					.getAttribute("class");

			Common.assertionCheckwithReport(hotelements.equals("active"), "Verify the Hot element drop down contents ",
					"after clicking on hot elemnet contents should be appear ",
					"Successfully hot elements contents appeared ", "Failed to appear hot elements contents");
		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Verify the Hot element drop down contents ",
					"after clicking on hot elemnet contents should be appear ",
					"Successfully hot elements contents appeared",
					Common.getscreenShotPathforReport("Failed to navigate to the edit page builder filed"));
			Assert.fail();
		}
	}

	public WebElement Promo_Content() {
		// TODO Auto-generated method stub

		WebElement element = Common.findElement("xpath", "//span[text()='Promo Content (Product)']");
		Common.clickElement("xpath", "//span[text()='Promo Content (Product)']");

		return element;

	}

	public void draganddropContentBlock(WebElement source) {
		try {
			Common.dragdrop(source, "xpath", "//div[contains(@class,'pagebuilder-emp')]");

		} catch (Exception | Error e) {

		}
	}

	public void dragndrop_Promo_Content() {
		try {
			WebElement element = Common.findElement("xpath", "//span[text()='Promo Content (Product)']");
			draganddropContentBlock(element);
			String blockname = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']/div")
					.getAttribute("data-content-type");
			Common.assertionCheckwithReport(blockname.equals("hot_product_promo"),
					"Validating promocontent Dragndrop operation", "promocontent dragndrop to content with options",
					"successfully dragndrop the promocontent with options ", "fail to dragndrop the promocontent");
		} catch (Exception e) {

			e.printStackTrace();

			report.addFailedLog("Validating promocontent Dragndrop operation",
					"User should able Dragndrop promocontent", "Sucessfully Dragndrop promocontent",
					Common.getscreenShotPathforReport("Failed to Dragndrop promocontent"));
			Assert.fail();

		}
	}

	public void editcontent() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//div[@class='c-promo-block']");
			Common.mouseOver("xpath", "//div[@class='c-promo-block']");
			String visible = Common.findElement("xpath", "//div[contains(@class,'pagebuilder-options ')]")
					.getAttribute("class");
			System.out.println(visible);
			Common.assertionCheckwithReport(visible.contains("-options-visible"),
					"validation Edit option  in the page builder ",
					"after mouse over on the page builder edit option should be appear ",
					"Successfully edit option appeared in the pagebuilder",
					"Failed to appear edit option when we mouse over");

			Common.clickElement("xpath", "//i[@class='icon-admin-pagebuilder-systems']");
			String editpromo = Common.findElement("xpath", "//h1[@class='modal-title']").getText();
			System.out.println(editpromo);
			Common.assertionCheckwithReport(editpromo.contains("Edit Promo Content (Product)"),
					"validation Navigation to the edit promo page ",
					"after Click on edit button it should be navigate to the edit promo page ",
					"Successfully it is navigated to edit promo page ", "Failed to navigate to edit promo page");

		} catch (Exception | Error e) {
			e.printStackTrace();

			report.addFailedLog("validation Navigation to the edit promo page ",
					"after Click on edit button it should be navigate to the edit promo page ",
					"Successfully it is navigated to edit promo page ",
					Common.getscreenShotPathforReport("Failed to navigate to edit promo page"));
			Assert.fail();

		}
	}

	public void editpromocontent_color(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Common.scrollIntoView("xpath", "//div[contains(@class,'sp-preview-inner sp-clear-display')]");
			Common.clickElement("xpath", "//div[contains(@class,'sp-preview-inner sp-clear-display')]");
			String color = data.get(Dataset).get("Color");
			Common.clickElement("xpath", "//span[@title='" + color + "']");
			Common.clickElement("xpath", "//button[text()='Apply']");
			String appliedcolor = Common.findElement("xpath", "//input[@class='colorpicker-spectrum']")
					.getAttribute("value");
			Common.assertionCheckwithReport(appliedcolor.equals(color),
					"validation of the color applied in the backgroud color ",
					"after Clicking on the color the background color should be applied ",
					"Successfully Background color is applied ", "Failed to apply backgroud color");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("validation of the color applied in the backgroud color ",
					"after Clicking on the color the background color should be applied ",
					"Successfully Background color is applied ",
					Common.getscreenShotPathforReport("Failed to apply backgroud color"));
			Assert.fail();

		}
	}

	public void editpromocontent_image() {
		// TODO Auto-generated method stub

		String path = System.getProperty("user.dir") + ("\\src\\test\\resources\\TestData\\Admin\\Lotusqa.png");
		try {
			Sync.waitElementPresent("xpath", "//input[@name='background_image']");
			Common.findElement("xpath", "//input[@name='background_image']").sendKeys(path);
			String image = Common.findElement("xpath", "//div[@class='file-uploader-filename']").getText();
			System.out.println(image);
			Common.assertionCheckwithReport(image.equals("Lotusqa.png"),
					"validation the image uploading on content for background image ",
					"Image should be upload for background image", "Successfully image uploaded in background image ",
					"Failed to upload image on the background image");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("validation the image uploading on content for background image ",
					"Image should be upload for background image", "Successfully image uploaded in background image ",
					Common.getscreenShotPathforReport("Failed to upload image on the background image"));
			Assert.fail();

		}

	}

	public void allbackground(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//span[text()='Contain']");
			Common.clickElement("xpath", "//span[text()='Contain']");
			Common.clickElement("xpath", "//select[@name='background_position']");
			Common.dropdown("xpath", "//select[@name='background_position']", Common.SelectBy.TEXT,
					data.get(Dataset).get("Backgroud position"));
			Common.clickElement("xpath", "//select[@name='background_attachment']");
			Common.dropdown("xpath", "//select[@name='background_attachment']", Common.SelectBy.TEXT,
					data.get(Dataset).get("Attachment"));
			String background=Common.findElement("xpath", "(//select[@name='background_attachment']//option)[2]").getText();
			Common.assertionCheckwithReport(background.equals("Fixed"),
					"validation of the back groun attachemnt drop down ",
					"back groud attachment drop down should be open and its should be select", "Successfully dropdown should be opend and text has been selected ",
					"Failed to open drop down and text to select");
			Common.clickElement("xpath", "//span[text()='Background Repeat']");
			Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			

		} catch (Exception | Error e) {

			e.printStackTrace();
			report.addFailedLog("validation of the back ground attachemnt drop down ",
					"back groud attachment drop down should be open and its should be select", "Successfully dropdown should be opend and text has been selected ",
					Common.getscreenShotPathforReport("Failed to open drop down and text to select"));
			Assert.fail();
		}
	}

	public void savecontent(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent(30, "xpath", "//i[@title='Close Full Screen']");
			Common.clickElement("xpath", "//i[@title='Close Full Screen']");
			Common.clickElement("xpath", "//input[@name='title']");
			Common.textBoxInput("xpath", "//input[@name='title']", data.get(Dataset).get("pageTitle"));
			Common.clickElement("xpath", "//button[@id='save-button']");
			Sync.waitPageLoad(70);
			String savethepage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
			Common.assertionCheckwithReport(savethepage.equals("You saved the page."),
					"Validating the User need to save the page", "User should able to save the page",
					"Sucessfully User saves the page", "Unable to save the page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating the User need to save the page", "User should able to save the page",
					"Sucessfully User saves the page", Common.getscreenShotPathforReport("Failed to save the page"));
			Assert.fail();

		}

	}

	public void openwebsite(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad(60);
			String urlkey = data.get(Dataset).get("pageTitle").toLowerCase();
			System.out.println(urlkey);
			Common.openNewTab();
			Sync.waitPageLoad(40);
			Common.oppenURL(data.get(Dataset).get("URL") + urlkey);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("qaFlask"),
					"Validating the User lands to the hydroflask page", "User should able lands on the hydroflask page",
					"Sucessfully User lands on the hydroflask page", "Failed user navigates to the hydroflask page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating page filed  navigation and customer deleted message",
					"after clicking save button it will navigate page filed and message should be displayed",
					"Successfully navigate to page filed and message is dispalyed",
					Common.getscreenShotPathforReport("Failed to navigate to page filed"));

			Assert.fail();
		}
	}

	public void deletepage(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Common.closeCurrentWindow();
			Common.switchToFirstTab();
			Sync.waitPageLoad(40);
			String title = data.get(Dataset).get("pageTitle");
			Sync.waitElementPresent(40, "xpath", "//h1[@class='page-title']");
			String name = Common.findElement("xpath", "//h1[@class='page-title']").getText();
			if (name.equals(title)) {
				Sync.waitElementPresent(40, "xpath", "//span[text()='Delete Page']");
				Common.clickElement("xpath", "//span[text()='Delete Page']");
			} else {
				Assert.fail();
			}
			String message = Common.findElement("xpath", "//div[@class='modal-content']").getText();
			if (message.equals("Are you sure you want to do this?")) {
				Common.clickElement("xpath", "//span[text()='OK']");

			} else {
				Assert.fail();
			}
			Sync.waitPageLoad();
			String deletemessage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']")
					.getText();
			System.out.println(deletemessage);
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Pages / Magento Admin")
							&& deletemessage.equals("The page has been deleted."),
					"Validating page filed  navigation and customer deleted message",
					"after clicking save button it will navigate page filed and message should be displayed",
					"Successfully navigate to page filed and message is dispalyed", "Failed to navigate to page filed");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating the User lands to the hydroflask page",
					"User should able lands on the hydroflask page", "Sucessfully User lands on the hydroflask page",
					Common.getscreenShotPathforReport("Failed to navigate to page filed"));
			Assert.fail();
		}
	}

	public void Contentpage() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent(30, "xpath", "//strong//span[text()='Content']");
			Common.clickElement("xpath", "//strong//span[text()='Content']");
			Sync.waitElementPresent(70, "xpath", "//button[@class='action-default action']");
			Common.clickElement("xpath", "//button[@class='action-default action']");
			Sync.waitElementPresent(70, "xpath", "//div[contains(@data-bind,'pageBuilderEditButtonClick')]");
			String pagebuilder = Common.findElement("xpath", "//div[contains(@data-bind,'pageBuilderEditButtonClick')]")
					.getAttribute("class");
			System.out.println(pagebuilder);
			Common.assertionCheckwithReport(pagebuilder.equals("pagebuilder-wysiwyg-overlay"),
					"Validating edit page bulider navigation ",
					"after clicking on edit page builder it will navigate edit page builder filed ",
					"Successfully navigate to the edit page builder filed ",
					"Failed to navigate to the edit page builder filed");
		} catch (Exception | Error e) {
			e.printStackTrace();

			Assert.fail();
		}

	}

	public void dragndrop_promoBlock() {
		try {
			WebElement element = Common.findElement("xpath", "//span[text()='Promo Block']");
			draganddropContentBlock(element);
			String blockname = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']/div")
					.getAttribute("data-content-type");
			Common.assertionCheckwithReport(blockname.equals("hot_promo_block"),
					"Validating Promoblocker Dragndrop operation", "promoblocker dragndrop to content with options",
					"successfully dragndrop the promoblocker with options ", "fail to dragndrop the promobaner");
		} catch (Exception e) {

			e.printStackTrace();

			report.addFailedLog("Validating Promoblocker Dragndrop operation",
					"User should able Dragndrop Promoblocker", "Sucessfully Dragndrop Promoblocker",
					Common.getscreenShotPathforReport("Failed to Dragndrop Promoblocker"));
			Assert.fail();

		}
	}

	public void edit_promoBlocker_one() {
		try {
			Sync.waitElementPresent(30, "xpath", "//div[@class='pagebuilder-content-type-wrapper']");
			String id = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']")
					.getAttribute("id");

			Common.mouseOverClick("xpath", "//div[@id='" + id + "']//div[3]//i");

			String editpromo = Common.findElement("xpath", "//h1[@class='modal-title']").getText();

			Common.assertionCheckwithReport(editpromo.contains("Edit Promo Media Card"),
					"validation Navigation to the edit  Promo Media Card page ",
					"after Click on edit button it should be navigate to the edit promoBlocker page ",
					"Successfully it is navigated to edit promoBlocker page ",
					"Failed to navigate to edit promoBlocker page");

		} catch (Exception | Error e) {
			e.printStackTrace();

			report.addFailedLog("validation Navigation to the edit promo Blocker ",
					"after Click on edit button it should be navigate to the edit Blocker page ",
					"Successfully it is navigated to edit Blocker page ",
					Common.getscreenShotPathforReport("Failed to navigate to edit Blocker page"));
			Assert.fail();

		}
	}

	public void website_image_verification_promocontent() {
		// TODO Auto-generated method stub
		try {

			Sync.waitElementPresent(40, "xpath", "//div[contains(@class,'c-promo-block__rig')]");
			String imageverification = Common.findElement("xpath", "//div[contains(@class,'c-promo-block__rig')]")
					.getAttribute("data-background-images");
			System.out.println(imageverification);
			Common.assertionCheckwithReport(imageverification.contains("Lotusqa"),
					"validation Image upload in the forntend website ", "Image should de appear on fornt end page",
					"Successfully image is appeared on the frondend", "Failed to navigate to edit promoBlocker page");
		} catch (Exception | Error e) {
			e.printStackTrace();

			report.addFailedLog("validation Image upload in the forntend website ",
					"Image should de appear on fornt end page", "Successfully image is appeared on the frondend",
					Common.getscreenShotPathforReport("Failed to navigate to edit promoBlocker page"));
			Assert.fail();

		}

	}

	public void close_promo_page() {
		// TODO Auto-generated method stub
		try {
			editcontent();
			Sync.waitElementPresent("xpath", "//button[@class='action-close']");
			Common.clickElement("xpath", "//button[@class='action-close']");
			Sync.waitPageLoad();
			String crossbutton = Common.findElement("xpath", "//aside[contains(@class,'modal-slide pa')]")
					.getAttribute("class");
			editcontent();
			Sync.waitElementPresent("xpath", "//div[@class='page-main-actions']//button[@title='Close']");
			Common.clickElement("xpath", "//div[@class='page-main-actions']//button[@title='Close']");
			Sync.waitPageLoad();
			String closebutton = Common.findElement("xpath", "//aside[contains(@class,'modal-slide pa')]")
					.getAttribute("class");
			Common.assertionCheckwithReport(closebutton.contains(crossbutton),
					"validation the close and cross button funtionality ",
					"After clickng close and cross button the page should be close",
					"Successfully page has been closed after clicking on close and cross button",
					"Failed to the page after clicking on close and cross buttton");
		} catch (Exception | Error e) {
			e.printStackTrace();

			report.addFailedLog("validation the close and cross button funtionality ",
					"After clickng close and cross button the page should be close",
					"Successfully page has been closed after clicking on close and cross button",
					Common.getscreenShotPathforReport("Failed to the page after clicking on close and cross buttton"));
			Assert.fail();
		}

	}

	public void clone_page() {
		// TODO Auto-generated method stub
		try {
			Common.switchToFirstTab();
			Contentpage();
			
			Sync.waitElementPresent(30, "xpath", "//a[@class='duplicate-structural']");
			Common.clickElement("xpath", "//a[@class='duplicate-structural']");
			editcontent();
			 editpromocontent_image_one();
			 Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
				Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			 Sync.waitElementPresent(30, "xpath", "//i[@title='Close Full Screen']");
				Common.clickElement("xpath", "//i[@title='Close Full Screen']");
				Common.clickElement("xpath", "//button[@id='save-button']");
				Sync.waitPageLoad(70);
				Sync.waitPageLoad();
				String savethepage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
				Common.assertionCheckwithReport(savethepage.equals("You saved the page."),
						"Validating the User need to save the page", "User should able to save the page",
						"Sucessfully User saves the page", "Unable to save the page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating the User need to save the page", "User should able to save the page",
					"Sucessfully User saves the page", Common.getscreenShotPathforReport("Failed to save the page"));
			Assert.fail();

		}

	}

	public void clone_image_frontend() {
		// TODO Auto-generated method stub
		try
		{
			Common.switchToSecondTab();
			Common.refreshpage();
			Sync.waitPageLoad();
			String id=Common.findElement("xpath", "(//div[contains(@class,'c-promo-block__right')])[1]").getAttribute("data-pb-style");
			String image=Common.findElement("xpath", "//div[@data-pb-style='" + id +"']").getAttribute("data-background-images");
			Common.assertionCheckwithReport(image.contains("Lotusqa1"),
					"validation the image uploading on front end page ",
					"Image should be upload for fornt end page", "Successfully image uploaded in front end page",
					"Failed to upload image on the front end page");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("validation the image uploading on front end page ",
					"Image should be upload for fornt end page", "Successfully image uploaded in front end page",
					Common.getscreenShotPathforReport("Failed to upload image on the front end page"));
			Assert.fail();

		}
	}

	public void editpromocontent_video(String Dataset) {
		// TODO Auto-generated method stub
		
		try
		{
			Sync.waitElementPresent("xpath", "//li[@name='video']");
			Common.clickElement("xpath", "//li[@name='video']");
			Common.clickElement("xpath", "//input[@name='video_source']");
			Common.textBoxInput("xpath", "//input[@name='video_source']", data.get(Dataset).get("videoUrl"));
			Common.clickElement("xpath", "(//div[contains(@class,'sp-preview-')])[2]");
			String color = data.get(Dataset).get("Color");
			Common.clickElement("xpath", "//span[@title='" + color + "']");
			Common.clickElement("xpath", "//button[text()='Apply']");
			String appliedcolor = Common.findElement("xpath", "//input[@class='colorpicker-spectrum']")
					.getAttribute("value");
			Common.assertionCheckwithReport(appliedcolor.equals(color),
					"validation of the color applied in the overlay color ",
					"after Clicking on the color the overlay color should be applied ",
					"Successfully overlay color is applied ", "Failed to apply overlay color");
			
			
			
			
		}
		catch(Exception | Error e)
		{
            e.printStackTrace();
            
            report.addFailedLog("validation of the color applied in the overlay color ",
			"after Clicking on the color the overlay color should be applied ",
			"Successfully overlay color is applied ",
					Common.getscreenShotPathforReport("Failed to apply overlay color"));
            Assert.fail();
		}
	}

	public void editpromocontent_fallback_image() {
		// TODO Auto-generated method stub
		String path = System.getProperty("user.dir") + ("\\src\\test\\resources\\TestData\\Admin\\Lotusqa.png");
		try {
			Sync.waitElementPresent("xpath", "//input[@name='video_fallback_image']");
			Common.findElement("xpath", "//input[@name='video_fallback_image']").sendKeys(path);
			String image = Common.findElement("xpath", "//div[@class='file-uploader-filename']").getText();
			System.out.println(image);
			Common.assertionCheckwithReport(image.equals("Lotusqa.png"),
					"validation the image uploading on content for fallback image ",
					"Image should be upload for Fallback image", "Successfully image uploaded in Fallback image ",
					"Failed to upload image on the Fallback image");
			
			Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("validation the image uploading on content for Fallback image ",
					"Image should be upload for Fallback image", "Successfully image uploaded in Fallback image ",
					Common.getscreenShotPathforReport("Failed to upload image on the Fallback image"));
			Assert.fail();

		}

	}

	public void dragndrop_valueprop_Banner() {
		// TODO Auto-generated method stub
		try
		{
			WebElement elements = Common.findElement("xpath", "//span[text()='Value Prop Banner']");
			draganddropContentBlock(elements);
			String blocknames = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']/div")
					.getAttribute("data-content-type");
			Common.assertionCheckwithReport(blocknames.equals("hot_value_prop_banner"),
					"Validating value prop banner Dragndrop operation", "value prop banner dragndrop to content with options",
					"successfully dragndrop the value prop banner with options ", "fail to dragndrop the value prop banner");
		} catch (Exception e) {

			e.printStackTrace();

			report.addFailedLog("Validating value prop banner Dragndrop operation", "value prop banner dragndrop to content with options",
					"successfully dragndrop the value prop banner with options ",
					Common.getscreenShotPathforReport("fail to dragndrop the value prop banner"));
			Assert.fail();

		
	}

}

	public void edit_valueprop_banner_one() {
		// TODO Auto-generated method stub
		try
		{
			Sync.waitElementPresent(30, "xpath", "//div[@class='pagebuilder-content-type-wrapper']");
			String id = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']")
					.getAttribute("id");

			Common.mouseOverClick("xpath", "(//div[@id='" + id+ "']//i[@class='icon-admin-pagebuilder-systems'])[2]");
		

			String editvlauepropcard = Common.findElement("xpath", "//h1[@class='modal-title']").getText();

			Common.assertionCheckwithReport(editvlauepropcard.contains("Edit Value Prop Card"),
					"validation Navigation to the edit  value prop Card page ",
					"after Click on edit button it should be navigate to the edit value prop card page ",
					"Successfully it is navigated to edit value prop card  page ",
					"Failed to navigate to edit value prop card page");
			

		} catch (Exception | Error e) {
			e.printStackTrace();

			report.addFailedLog("validation Navigation to the edit  value prop Card page ",
					"after Click on edit button it should be navigate to the edit value prop card page ",
					"Successfully it is navigated to edit value prop card  page ",
					Common.getscreenShotPathforReport("Failed to navigate to edit value prop card page"));
			Assert.fail();

		}
	}

	public void close_individual_page_one() {
		// TODO Auto-generated method stub
		try
		{
			Sync.waitElementPresent("xpath", "//button[@class='action-close']");
			Common.clickElement("xpath", "//button[@class='action-close']");
			Sync.waitPageLoad();
			String crossbutton = Common.findElement("xpath", "//aside[contains(@class,'modal-slide pa')]")
					.getAttribute("class");
			edit_valueprop_banner_one();
			Sync.waitElementPresent("xpath", "//div[@class='page-main-actions']//button[@title='Close']");
			Common.clickElement("xpath", "//div[@class='page-main-actions']//button[@title='Close']");
			Sync.waitPageLoad();
			String closebutton = Common.findElement("xpath", "//aside[contains(@class,'modal-slide pa')]")
					.getAttribute("class");
			Common.assertionCheckwithReport(closebutton.contains(crossbutton),
					"validation the close and cross button funtionality ",
					"After clickng close and cross button the page should be close",
					"Successfully page has been closed after clicking on close and cross button",
					"Failed to the page after clicking on close and cross buttton");
		} catch (Exception | Error e) {
			e.printStackTrace();

			report.addFailedLog("validation the close and cross button funtionality ",
					"After clickng close and cross button the page should be close",
					"Successfully page has been closed after clicking on close and cross button",
					Common.getscreenShotPathforReport("Failed to the page after clicking on close and cross buttton"));
			Assert.fail();
		}

		
	}

	public void edit_valueprop_banner_Two() {
		// TODO Auto-generated method stub
		try
		{
			Sync.waitPageLoad(40);
			Sync.waitElementPresent(30, "xpath", "//div[@class='pagebuilder-content-type-wrapper']");
			String id = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']")
					.getAttribute("id");
//			Sync.waitElementPresent(30, "xpath","//div[@id='" + id+ "']//div[@class='pagebuilder-content-type-wrapper']");
			Common.mouseOver("xpath", "(//div[@id='" + id+ "']//i[@class='icon-admin-pagebuilder-systems'])[3]");
			Common.clickElement("xpath", "(//div[@id='" + id+ "']//i[@class='icon-admin-pagebuilder-systems'])[3]");
		

			String editvlauepropcard = Common.findElement("xpath", "//h1[@class='modal-title']").getText();

			Common.assertionCheckwithReport(editvlauepropcard.contains("Edit Value Prop Card"),
					"validation Navigation to the edit  value prop Card page ",
					"after Click on edit button it should be navigate to the edit value prop card page ",
					"Successfully it is navigated to edit value prop card  page ",
					"Failed to navigate to edit value prop card page");

		} catch (Exception | Error e) {
			e.printStackTrace();

			report.addFailedLog("validation Navigation to the edit  value prop Card page ",
					"after Click on edit button it should be navigate to the edit value prop card page ",
					"Successfully it is navigated to edit value prop card  page ",
					Common.getscreenShotPathforReport("Failed to navigate to edit value prop card page"));
			Assert.fail();

		}
	}

	public void close_individual_page_Two() {
		// TODO Auto-generated method stub
		try
		{
		Sync.waitElementPresent("xpath", "//button[@class='action-close']");
		Common.clickElement("xpath", "//button[@class='action-close']");
		Sync.waitPageLoad();
		String crossbutton = Common.findElement("xpath", "//aside[contains(@class,'modal-slide pa')]")
				.getAttribute("class");
		edit_valueprop_banner_Two();
		Sync.waitElementPresent("xpath", "//div[@class='page-main-actions']//button[@title='Close']");
		Common.clickElement("xpath", "//div[@class='page-main-actions']//button[@title='Close']");
		Sync.waitPageLoad();
		String closebutton = Common.findElement("xpath", "//aside[contains(@class,'modal-slide pa')]")
				.getAttribute("class");
		Common.assertionCheckwithReport(closebutton.contains(crossbutton),
				"validation the close and cross button funtionality ",
				"After clickng close and cross button the page should be close",
				"Successfully page has been closed after clicking on close and cross button",
				"Failed to the page after clicking on close and cross buttton");
	} catch (Exception | Error e) {
		e.printStackTrace();

		report.addFailedLog("validation the close and cross button funtionality ",
				"After clickng close and cross button the page should be close",
				"Successfully page has been closed after clicking on close and cross button",
				Common.getscreenShotPathforReport("Failed to the page after clicking on close and cross buttton"));
		Assert.fail();
	}
	
		
	}

	public void edit_valueprop_banner_Three() {
		// TODO Auto-generated method stub
		try
		{
			Sync.waitElementPresent(30, "xpath", "//div[@class='pagebuilder-content-type-wrapper']");
			String id = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']")
					.getAttribute("id");
			
			Common.mouseOver("xpath", "(//div[@id='" + id+ "']//i[@class='icon-admin-pagebuilder-systems'])[4]");
			Common.clickElement("xpath", "(//div[@id='" + id+ "']//i[@class='icon-admin-pagebuilder-systems'])[4]");
		

			String editvlauepropcard = Common.findElement("xpath", "//h1[@class='modal-title']").getText();

			Common.assertionCheckwithReport(editvlauepropcard.contains("Edit Value Prop Card"),
					"validation Navigation to the edit  value prop Card page ",
					"after Click on edit button it should be navigate to the edit value prop card page ",
					"Successfully it is navigated to edit value prop card  page ",
					"Failed to navigate to edit value prop card page");

		} catch (Exception | Error e) {
			e.printStackTrace();

			report.addFailedLog("validation Navigation to the edit  value prop Card page ",
					"after Click on edit button it should be navigate to the edit value prop card page ",
					"Successfully it is navigated to edit value prop card  page ",
					Common.getscreenShotPathforReport("Failed to navigate to edit value prop card page"));
			Assert.fail();

		}
		
	}

	public void close_individual_page_Three() {
		// TODO Auto-generated method stub
		try
		{
		Sync.waitElementPresent("xpath", "//button[@class='action-close']");
		Common.clickElement("xpath", "//button[@class='action-close']");
		Sync.waitPageLoad();
		String crossbutton = Common.findElement("xpath", "//aside[contains(@class,'modal-slide pa')]")
				.getAttribute("class");
		edit_valueprop_banner_Three();
		Sync.waitElementPresent("xpath", "//div[@class='page-main-actions']//button[@title='Close']");
		Common.clickElement("xpath", "//div[@class='page-main-actions']//button[@title='Close']");
		Sync.waitPageLoad();
		String closebutton = Common.findElement("xpath", "//aside[contains(@class,'modal-slide pa')]")
				.getAttribute("class");
		Common.assertionCheckwithReport(closebutton.contains(crossbutton),
				"validation the close and cross button funtionality ",
				"After clickng close and cross button the page should be close",
				"Successfully page has been closed after clicking on close and cross button",
				"Failed to the page after clicking on close and cross buttton");
	} catch (Exception | Error e) {
		e.printStackTrace();

		report.addFailedLog("validation the close and cross button funtionality ",
				"After clickng close and cross button the page should be close",
				"Successfully page has been closed after clicking on close and cross button",
				Common.getscreenShotPathforReport("Failed to the page after clicking on close and cross buttton"));
		Assert.fail();
	}
	
		
	}

	public void edit_valueprop_banner() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5000);
			Sync.waitElementPresent(30, "xpath", "//h2[@data-element='heading']");
			Common.mouseOver("xpath", "//h2[@data-element='heading']");
			String visible = Common.findElement("xpath", "//div[contains(@class,'pagebuilder-options ')]")
					.getAttribute("class");
			System.out.println(visible);
			Common.assertionCheckwithReport(visible.contains("-options-visible"),
					"validation Edit option  in the page builder ",
					"after mouse over on the page builder edit option should be appear ",
					"Successfully edit option appeared in the pagebuilder",
					"Failed to appear edit option when we mouse over");

			Common.clickElement("xpath", "(//i[@class='icon-admin-pagebuilder-systems'])[1]");
			String editpromo = Common.findElement("xpath", "//h1[@class='modal-title']").getText();
			System.out.println(editpromo);
			Common.assertionCheckwithReport(editpromo.contains("Edit Value Prop Banner"),
					"validation Navigation to the edit promo page ",
					"after Click on edit button it should be navigate to the edit promo page ",
					"Successfully it is navigated to edit promo page ", "Failed to navigate to edit promo page");

		} catch (Exception | Error e) {
			e.printStackTrace();

			report.addFailedLog("validation Navigation to the edit promo page ",
					"after Click on edit button it should be navigate to the edit promo page ",
					"Successfully it is navigated to edit promo page ",
					Common.getscreenShotPathforReport("Failed to navigate to edit promo page"));
			Assert.fail();

		}
		
	}

	public void close_valueprop_page() {
		// TODO Auto-generated method stub
		try {
			
			Sync.waitElementPresent("xpath", "//button[@class='action-close']");
			Common.clickElement("xpath", "//button[@class='action-close']");
			Sync.waitPageLoad();
			String crossbutton = Common.findElement("xpath", "//aside[contains(@class,'modal-slide pa')]")
					.getAttribute("class");
			Sync.waitPageLoad();
			edit_valueprop_banner();
			Sync.waitElementPresent("xpath", "//div[@class='page-main-actions']//button[@title='Close']");
			Common.clickElement("xpath", "//div[@class='page-main-actions']//button[@title='Close']");
			Sync.waitPageLoad();
			String closebutton = Common.findElement("xpath", "//aside[contains(@class,'modal-slide pa')]")
					.getAttribute("class");
			Common.assertionCheckwithReport(closebutton.contains(crossbutton),
					"validation the close and cross button funtionality ",
					"After clickng close and cross button the page should be close",
					"Successfully page has been closed after clicking on close and cross button",
					"Failed to the page after clicking on close and cross buttton");
		} catch (Exception | Error e) {
			e.printStackTrace();

			report.addFailedLog("validation the close and cross button funtionality ",
					"After clickng close and cross button the page should be close",
					"Successfully page has been closed after clicking on close and cross button",
					Common.getscreenShotPathforReport("Failed to the page after clicking on close and cross buttton"));
			Assert.fail();
		}

	}

	public void edit_heading_mobile_valueprop_banner(String Dataset) {
		// TODO Auto-generated method stub
		try
		{
			Common.clickElement("xpath", "//select[@name='mobile_layout']");
			Common.dropdown("xpath", "//select[@name='mobile_layout']", Common.SelectBy.TEXT, data.get(Dataset).get("mobilelayout"));
			Common.clickElement("xpath", "//input[@name='heading']");
			Common.textBoxInput("xpath", "//input[@name='heading']", data.get(Dataset).get("heading"));
			Common.clickElement("xpath", "//div[contains(@class,'sp-preview-inner sp-clear-display')]");
			String color = data.get(Dataset).get("TextColor");
			Common.clickElement("xpath", "//span[@title='" + color + "']");
			Common.clickElement("xpath", "//button[text()='Apply']");
			String appliedcolor = Common.findElement("xpath", "//input[@class='colorpicker-spectrum']")
					.getAttribute("value");
			Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
		String colortext=Common.findElement("xpath", "//h2[@class='m-heading__text']").getText();
		System.out.println(colortext);
		Common.assertionCheckwithReport(colortext.equals("Text"),
				"validation of the color applied in the backgroud Text ",
				"after Clicking on the color the background Text color should be applied ",
				"Successfully Background Text color  is applied ", "Failed to apply backgroud Text color");

		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			report.addFailedLog("validation of the color applied in the backgroud Text ",
					"after Clicking on the color the background Text color should be applied ",
					"Successfully Background Text color  is applied ",
					Common.getscreenShotPathforReport("Failed to apply backgroud Text color"));
			Assert.fail();
		}
	}

	public void icon_image_one(String Dataset) {
		// TODO Auto-generated method stub
		
			String path = System.getProperty("user.dir") + ("\\src\\test\\resources\\TestData\\Admin\\Lotusqa.png");
			try {
				
				Sync.waitElementPresent(30, "xpath", "(//input[@type='file'])[4]");
				String id = Common.findElement("xpath", "(//input[@type='file'])[4]")
						.getAttribute("id");
				Common.findElement("xpath", "//input[@id='" + id + "']").sendKeys(path);
				String image = Common.findElement("xpath", "//div[@class='file-uploader-filename']").getText();
				System.out.println(image);
				Common.assertionCheckwithReport(image.equals("Lotusqa.png"),
						"validation the image uploading on content for icon image ",
						"Image should be upload for icone image", "Successfully image uploaded in icon image ",
						"Failed to upload image on the icon image");
				String newid=Common.findElement("xpath", "//input[@name='alt']").getAttribute("id");
				Common.textBoxInput("xpath", "//input[@id='" + newid + "']", data.get(Dataset).get("Attachment"));
				Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
				Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
				

			}

			catch (Exception | Error e) {
				e.printStackTrace();
				report.addFailedLog("validation the image uploading on content for icon image ",
						"Image should be upload for icon image", "Successfully image uploaded in icon image ",
						Common.getscreenShotPathforReport("Failed to upload image on the icon image"));
				Assert.fail();

			}
		
	}

	public void icon_image_two(String Dataset) {
		// TODO Auto-generated method stub
		String path = System.getProperty("user.dir") + ("\\src\\test\\resources\\TestData\\Admin\\Lotusqa1.png");
		try {
			
			Sync.waitElementPresent(30, "xpath", "(//input[@type='file'])[4]");
			String id = Common.findElement("xpath", "(//input[@type='file'])[4]")
					.getAttribute("id");
			Common.findElement("xpath", "//input[@id='" + id + "']").sendKeys(path);
			String image = Common.findElement("xpath", "//div[@class='file-uploader-filename']").getText();
			System.out.println(image);
			Common.assertionCheckwithReport(image.equals("Lotusqa1.png"),
					"validation the image uploading on content for icon image ",
					"Image should be upload for icone image", "Successfully image uploaded in icon image ",
					"Failed to upload image on the icon image");
			String newid=Common.findElement("xpath", "//input[@name='alt']").getAttribute("id");
			Common.textBoxInput("xpath", "//input[@id='" + newid + "']", data.get(Dataset).get("Attachment"));
			Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("validation the image uploading on content for icon image ",
					"Image should be upload for icon image", "Successfully image uploaded in icon image ",
					Common.getscreenShotPathforReport("Failed to upload image on the icon image"));
			Assert.fail();

		}
		
	}

	public void icon_image_galary(String Dataset) {
		// TODO Auto-generated method stub
		try
		{
			Sync.waitPageLoad();
			Common.clickElement("xpath", "(//label[text()='Select from Gallery'])[7]");
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//img[@alt='demo-desktop.png']");
			Common.clickElement("xpath", "//span[text()='Add Selected']");
			Sync.waitPageLoad();
			String newid=Common.findElement("xpath", "//input[@name='alt']").getAttribute("id");
			Common.textBoxInput("xpath", "//input[@id='" + newid + "']", data.get(Dataset).get("Attachment"));
			Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Sync.waitElementPresent("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			String imageupload=Common.findElement("xpath", "(//div[contains(@data-bind,'visible: hasData()')])[3]").getAttribute("data-bind");
			Common.assertionCheckwithReport(imageupload.contains("isShowImageUploadOptions"), "validation the image uploading on content in value prop banner ",
					"Image should be upload for value prop baneer", "Successfully image uploaded in the value prop banner ",
					"Failed to upload image on the value prop banner");
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			report.addFailedLog("validation the image uploading on content in value prop banner ",
					"Image should be upload for value prop baneer", "Successfully image uploaded in the value prop banner ",
					Common.getscreenShotPathforReport("Failed to upload image on the value prop banner"));
			Assert.fail();
			
		}
		
	}

	public void valueprop_website() {
		// TODO Auto-generated method stub
		try
		{
			Sync.waitElementPresent(30, "xpath", "(//img[@alt='Fixed'])[1]");
			String id1=Common.findElement("xpath", "(//img[@alt='Fixed'])[1]").getAttribute("data-pb-style");
			System.out.println(id1);
			String image1=Common.findElement("xpath", "//img[@data-pb-style='" + id1 + "']").getAttribute("src");
			System.out.println(image1);
			String id2=Common.findElement("xpath", "(//img[@alt='Fixed'])[2]").getAttribute("data-pb-style");
			System.out.println(id2);
			String image2=Common.findElement("xpath", "//img[@data-pb-style='" + id2 + "']").getAttribute("src");
			System.out.println(image2);
			String id3=Common.findElement("xpath", "(//img[@alt='Fixed'])[3]").getAttribute("data-pb-style");
			System.out.println(id3);
			String image3=Common.findElement("xpath", "//img[@data-pb-style='" + id3 + "']").getAttribute("src");
			System.out.println(image3);
			Common.assertionCheckwithReport(image1.contains("Lotusqa") && image2.contains("Lotusqa1") && image3.contains("demo-desktop"),
					"validation the image uploading on content in frontend website ",
					"Image should be upload for frontend website", "Successfully image uploaded in frond end image ",
					"Failed to upload image on the frond end");		
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			report.addFailedLog("validation the image uploading on content in frontend website ",
					"Image should be upload for frontend website", "Successfully image uploaded in frond end image ",
					Common.getscreenShotPathforReport("Failed to upload image on the frond end"));
			Assert.fail();
		}
	}

	public void clone_valueprop_banner() {
		// TODO Auto-generated method stub
		try
		{
			
			Common.switchToFirstTab();
			Contentpage();
			Sync.waitElementPresent(30, "xpath", "//h2[@data-element='heading']");
			Common.mouseOver("xpath", "//h2[@data-element='heading']");
			Sync.waitElementPresent("xpath", "//a[@title='Duplicate']");
		    Common.clickElement("xpath", "//a[@title='Duplicate']");
		    edit_valueprop_banner_one();
		    icon_image_galary("promocontent"); 
		 Sync.waitElementPresent(30, "xpath", "//i[@title='Close Full Screen']");
			Common.clickElement("xpath", "//i[@title='Close Full Screen']");
			Common.clickElement("xpath", "//button[@id='save-button']");
			Sync.waitPageLoad(70);
			Sync.waitPageLoad();
			String savethepage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
			Common.assertionCheckwithReport(savethepage.equals("You saved the page."),
					"Validating the User need to save the page", "User should able to save the page",
					"Sucessfully User saves the page", "Unable to save the page");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			report.addFailedLog("Validating the User need to save the page", "User should able to save the page",
					"Sucessfully User saves the page",
					Common.getscreenShotPathforReport("Unable to save the page"));
			Assert.fail();
			
		}
		
	}

	public void vlaueprop_clone_frontend(String Datset) {
		// TODO Auto-generated method stub
		try
		{
			Sync.waitPageLoad(40);
		    Common.switchToSecondTab();
			Common.refreshpage();
			Sync.waitPageLoad();
			String image=Common.findElement("xpath", "(//img[@data-element='desktop_image'])[6]").getAttribute("data-pb-style");
			System.out.println();
			String headingname=Common.findElement("xpath", "//img[@data-pb-style='" + image +"']").getAttribute("src");
			 Common.assertionCheckwithReport(headingname.contains("demo-desktop"), "validation the text on clone value prop banner on fornt end",
						"Text should be add for the clone value prop baneer on fornt end", "Successfully text added to the  clone value prop banner on front end",
						"Failed to add text on the clone value prop banner on front end"); 
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			report.addFailedLog("validation the text on clone value prop banner on fornt end",
					"Text should be add for the clone value prop baneer on fornt end", "Successfully text added to the  clone value prop banner on front end",
					Common.getscreenShotPathforReport("Failed to add text on the clone value prop banner on front end"));
			Assert.fail();
			
		}
		
	}

	public void close_promo_block() {
		
		// TODO Auto-generated method stub
		try {
			edit_promoBlocker_one();
			Sync.waitElementPresent("xpath", "//button[@class='action-close']");
			Common.clickElement("xpath", "//button[@class='action-close']");
			Sync.waitPageLoad();
			String crossbutton = Common.findElement("xpath", "//aside[contains(@class,'modal-slide pa')]")
					.getAttribute("class");
			edit_promoBlocker_one();
			Sync.waitElementPresent("xpath", "//div[@class='page-main-actions']//button[@title='Close']");
			Common.clickElement("xpath", "//div[@class='page-main-actions']//button[@title='Close']");
			Sync.waitPageLoad();
			String closebutton = Common.findElement("xpath", "//aside[contains(@class,'modal-slide pa')]")
					.getAttribute("class");
			Common.assertionCheckwithReport(closebutton.contains(crossbutton),
					"validation the close and cross button funtionality ",
					"After clickng close and cross button the page should be close",
					"Successfully page has been closed after clicking on close and cross button",
					"Failed to the page after clicking on close and cross buttton");
		} catch (Exception | Error e) {
			e.printStackTrace();

			report.addFailedLog("validation the close and cross button funtionality ",
					"After clickng close and cross button the page should be close",
					"Successfully page has been closed after clicking on close and cross button",
					Common.getscreenShotPathforReport("Failed to the page after clicking on close and cross buttton"));
			Assert.fail();
		}
		
	}

	public void edit_promoBlocker_two() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent(30, "xpath", "//div[@class='pagebuilder-content-type-wrapper']");
			String id = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']")
					.getAttribute("id");

			Common.mouseOver("xpath", "//div[@id='" + id + "']//div[4]//i");
			Common.clickElement("xpath", "//div[@id='" + id + "']//div[4]//i");

			String editpromo = Common.findElement("xpath", "//h1[@class='modal-title']").getText();

			Common.assertionCheckwithReport(editpromo.contains("Edit Promo Media Card"),
					"validation Navigation to the edit  Promo Media Card page ",
					"after Click on edit button it should be navigate to the edit promoBlocker page ",
					"Successfully it is navigated to edit promoBlocker page ",
					"Failed to navigate to edit promoBlocker page");

		} catch (Exception | Error e) {
			e.printStackTrace();

			report.addFailedLog("validation Navigation to the edit promo Blocker ",
					"after Click on edit button it should be navigate to the edit Blocker page ",
					"Successfully it is navigated to edit Blocker page ",
					Common.getscreenShotPathforReport("Failed to navigate to edit Blocker page"));
			Assert.fail();

		}
		
	}

	public void website_verification_promoblock() {
		// TODO Auto-generated method stub
		
		try
		{
			String id=Common.findElement("xpath", "(//div[@data-background-type='image'])[4]").getAttribute("data-pb-style");
	        String websiteverification=Common.findElement("xpath", "//div[@data-pb-style='" + id +"']").getAttribute("data-background-images");
	        Common.assertionCheckwithReport(websiteverification.contains("Lotusqa"),
					"validation of images are displaying on fornt end ",
					"after Click on link  it should be navigate to the respective page contains with 2 images",
					"Successfully it is navigated to respective page and contains 2 images ",
					"Failed to navigate to respective page and imrages are not appeared");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();

			report.addFailedLog("validation of images are displaying on fornt end ",
					"after Click on link  it should be navigate to the respective page contains with 2 images",
					"Successfully it is navigated to respective page and contains 2 images ",
					Common.getscreenShotPathforReport("Failed to navigate to respective page and imrages are not appeared"));
			Assert.fail();
			
		}
		
	}

	public void clone_page_promo_block(String Dataset) {
		// TODO Auto-generated method stub
		try
		{
			Common.switchToFirstTab();
			Contentpage();
			edit_promoBlocker_one();
			editpromocontent_image1();
			Sync.waitElementPresent(30, "xpath", "//i[@title='Close Full Screen']");
			Common.clickElement("xpath", "//i[@title='Close Full Screen']");
			Common.clickElement("xpath", "//input[@name='title']");
			Common.textBoxInput("xpath", "//input[@name='title']", data.get(Dataset).get("pageTitle"));
			Common.clickElement("xpath", "//button[@id='save-button']");
			Sync.waitPageLoad();
			String savethepage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
			Common.assertionCheckwithReport(savethepage.equals("You saved the page."),
					"Validating the User need to save the page", "User should able to save the page",
					"Sucessfully User saves the page", "Unable to save the page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating the User need to save the page", "User should able to save the page",
					"Sucessfully User saves the page", Common.getscreenShotPathforReport("Failed to save the page"));
			Assert.fail();

		}
		
	}
	
	public void editpromocontent_image1() {
		// TODO Auto-generated method stub

		String path = System.getProperty("user.dir") + ("\\src\\test\\resources\\TestData\\Admin\\Lotusqa1.png");
		try {
			Sync.waitElementPresent(30, "xpath", "//button[@title='Delete image']");
			Common.clickElement("xpath", "//button[@title='Delete image']");
			Sync.waitElementPresent("xpath", "//input[@name='background_image']");
			Common.findElement("xpath", "//input[@name='background_image']").sendKeys(path);
			Sync.waitElementPresent("xpath", "//div[@class='file-uploader-filename']");
			Common.scrollIntoView("xpath", "//div[@class='file-uploader-filename']");
			String image = Common.findElement("xpath", "//div[@class='file-uploader-filename']").getText();
			System.out.println(image);
			Common.assertionCheckwithReport(image.equals("Lotusqa1.png"),
					"validation the image uploading on content for background image ",
					"Image should be upload for background image", "Successfully image uploaded in background image ",
					"Failed to upload image on the background image");
			Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("validation the image uploading on content for background image ",
					"Image should be upload for background image", "Successfully image uploaded in background image ",
					Common.getscreenShotPathforReport("Failed to upload image on the background image"));
			Assert.fail();

		}

	}

	public void clone_page_valueprop_forntend() {
		// TODO Auto-generated method stub
		try
		{
			Common.switchToSecondTab();
			Common.refreshpage();
			Sync.waitPageLoad();
			String id=Common.findElement("xpath", "(//div[@data-background-type='image'])[3]").getAttribute("data-pb-style");
			Sync.waitElementPresent("xpath", "//div[@data-pb-style='" + id +"']");
			String imagefrontend=Common.findElement("xpath", "//div[@data-pb-style='" + id +"']").getAttribute("data-background-images");
			Common.assertionCheckwithReport(imagefrontend.contains("Lotusqa1"),
					"validation the image uploading on content for fornt end image ",
					"Image should be upload for front end image", "Successfully image uploaded in front end image ",
					"Failed to upload image on the frond end image");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			
			report.addFailedLog("validation the image uploading on content for fornt end image ",
					"Image should be upload for front end image", "Successfully image uploaded in front end image ",
					Common.getscreenShotPathforReport("Failed to upload image on the frond end image"));
			Assert.fail();
		
		}
		
		
	}

	public void video_promo_component() {
		// TODO Auto-generated method stub
		try
		{
			Sync.waitElementPresent("xpath", "//span[text()='Play video']");
			Common.clickElement("xpath", "//span[text()='Play video']");
			String pause=Common.findElement("xpath", "//button[contains(@class,'a-video-btn a-')]").getAttribute("class");
			System.out.println(pause);
			Common.assertionCheckwithReport(pause.contains(" video-playing"),
					"validation the video uploading on fornt end  ",
					"video should be upload for front end ", "Successfully video uploaded in front end  ",
					"Failed to upload video on the frond end ");
			
		}
		catch(Exception | Error e)
		{
			report.addFailedLog("validation the video uploading on fornt end  ",
					"video should be upload for front end ", "Successfully video uploaded in front end  ",
					Common.getscreenShotPathforReport("Failed to upload video on the frond end "));
		}
		
	}
	
	public void editpromocontent_image_one() {
		// TODO Auto-generated method stub

		String path = System.getProperty("user.dir") + ("\\src\\test\\resources\\TestData\\Admin\\Lotusqa1.png");
		try {
			Sync.waitElementPresent("xpath", "//button[@title='Delete image']");
			Common.clickElement("xpath", "//button[@title='Delete image']");
			Sync.waitElementPresent("xpath", "//input[@name='background_image']");
			Common.findElement("xpath", "//input[@name='background_image']").sendKeys(path);
			String image = Common.findElement("xpath", "//div[@class='file-uploader-filename']").getText();
			System.out.println(image);
			Common.assertionCheckwithReport(image.equals("Lotusqa1.png"),
					"validation the image uploading on content for background image ",
					"Image should be upload for background image", "Successfully image uploaded in background image ",
					"Failed to upload image on the background image");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("validation the image uploading on content for background image ",
					"Image should be upload for background image", "Successfully image uploaded in background image ",
					Common.getscreenShotPathforReport("Failed to upload image on the background image"));
			Assert.fail();

		}
}
	public void delete_existing_page(String Dataset) throws Exception 
	
	{
		String title = data.get(Dataset).get("pageTitle");
		Sync.waitElementPresent(40, "xpath", "//h1[@class='page-title']");
		String name = Common.findElement("xpath", "//h1[@class='page-title']").getText();
		if (name.equals(title)) {
			Sync.waitElementPresent(40, "xpath", "//span[text()='Delete Page']");
			Common.clickElement("xpath", "//span[text()='Delete Page']");
		} else {
			Assert.fail();
		}
		String message = Common.findElement("xpath", "//div[@class='modal-content']").getText();
		if (message.equals("Are you sure you want to do this?")) {
			Common.clickElement("xpath", "//span[text()='OK']");

		} else {
			Assert.fail();
		}
		Sync.waitPageLoad();
		String deletemessage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']")
				.getText();
		System.out.println(deletemessage);
		Common.assertionCheckwithReport(
				Common.getPageTitle().equals("Pages / Magento Admin")
						&& deletemessage.equals("The page has been deleted."),
				"Validating page filed  navigation and customer deleted message",
				"after clicking save button it will navigate page filed and message should be displayed",
				"Successfully navigate to page filed and message is dispalyed", "Failed to navigate to page filed");
	}
}
