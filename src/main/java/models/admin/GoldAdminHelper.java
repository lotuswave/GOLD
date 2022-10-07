package models.admin;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;

public class GoldAdminHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();
	
	
	public GoldAdminHelper(String datafile,String DataSet) {
        excelData = new ExcelReader(datafile,DataSet);
        data = excelData.getExcelValue();
        this.data = data;
        if (Utilities.TestListener.report == null) {
            report = new ExtenantReportUtils("Admin");
            report.createTestcase("AdminTestCases");
        } else {
            this.report = Utilities.TestListener.report;
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

			Common.assertionCheckwithReport(Common.getPageTitle().contains("Dashboard / Magento Admin"),
					"To Validate the Admin is landing on the Dashboard after successfull Signin",
					"After clicking on sigin button admin should navigate to the dashboard",
					"Admin Sucessfully navigate to the dashboard after clicking on the signin button",
					"Admin failed to display the dashboard after clicking on the signin button");

		} catch (Exception e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog(
					"To Validate the Admin is landing on the Dashboard after successfull Signin",
					"After clicking on sigin button admin should navigate to the dashboard",
					"Admin failed to navigate to the dashboard after click on signin button",
					"Admin failed to land on the dashboard after clicking on the signin button");
			Assert.fail();

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
			Common.assertionCheckwithReport(customers.equals("Customers"),
					"To Validate the customers menu is displayed",
					"should display the customer menu after clicking on the customers",
					"Customers field menu is displayed after a click on the customers button",
					"Failed to display customers menu");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the customers menu is displayed",
					"should display the customer menu after clicking on the customers",
					"unable to display Customers field menu after a click on the customers button",
					"Failed to display customers field menu");
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
					"Validating customers field page navigation ",
					"After clicking all customers it will navigate to the Customer field page",
					"Successfully navigate to the Customer field page ",
					"Failed to navigate to the Customer field page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating customers field page navigation ",
					"After clicking all customers it will navigate Customer field page",
					"Unable to navigate to the Customer filed page",
					Common.getscreenShotPathforReport("Failed to navigate Customer filed page"));
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
				Delete_exiting_customer(Dataset);
				Sync.waitElementPresent("xpath", "//button[@title='Add New Customer']");
				Common.clickElement("xpath", "//button[@title='Add New Customer']");
			}
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("New Customer / Customers / Customers / Magento Admin"),
					"Validating the new customer page  ",
					"Click on the new customer it should  navigate to the new customer page",
					"Successfully navigate to new Customer page", "Failed to navigate to New Customer page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the new customer page  ",
					"Click on the new customer it should  navigate to the new customer page",
					"unable to navigate to the new Customer page",
					Common.getscreenShotPathforReport("Failed to navigate to New Customer page"));
			Assert.fail();

		}
	}
	public void Delete_exiting_customer(String Dataset) {
		// TODO Auto-generated method stub
		try {

			Common.clickElement("xpath", "//a[text()='Edit']");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//button[@title='Delete Customer']");
			Common.javascriptclickElement("xpath", "//button[@title='Delete Customer']");
			String message = Common.findElement("xpath", "//aside[contains(@class,'confirm _show')]//div[@class='modal-content']").getText();
			if (message.contains("Are you sure you want to do this?")) {
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
			ExtenantReportUtils.addFailedLog("Validating customers filed page navigation ",
					"after clicking save button it will navigate Customer filed page",
					"Unable to  navigate to the Customer filed page",
					Common.getscreenShotPathforReport("Failed to navigate to the Customer filed page"));
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
					"To validate the customers Field page navigation and saved message ",
					"After clicking save button it will navigate Customer Field page and it should be display save message",
					"Successfully navigate to Customer field page and save message has displayed",
					"Unable to navigate to the Customer field page and save message is not displayed");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate customers field page navigation and saved message ",
					"After clicking save button it will navigate Customer field page and it should be display save message",
					"Unable to  navigate to the  Customer field page and save message is not displayed",
					Common.getscreenShotPathforReport(
							"Failed to navigate to Customer field page and save message not displayed"));
			Assert.fail();
		}
	}

	public void Updatedetails(String Dataset) {
		
		try {
			int existingfilters = Common.findElements("xpath", "//div[@class='sticky-header']//ul[contains(@class,'current-filters-list')]").size();
			if(existingfilters>0) {
				Common.javascriptclickElement("xpath", "//div[@class='sticky-header']//button[@class='action-remove']");
			}
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
						"Validating the edit button on the customers page",
						"After clicking edit button it should navigate to the selected page",
						"Successfully navigate to the selected page when we click on edit button",
						"Failed to navigate to the selected page");

			} else {
				Assert.fail();
			}

			
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the edit button on the customers page",
					"After clicking edit button it should navigate to the selected page",
					"Unable to navigate to the selected page when we click on edit button",
					Common.getscreenShotPathforReport("Failed to navigate to the Customer filed page"));
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
			Common.assertionCheckwithReport(clear.contains("current"), "To Validate the Clear filters ",
					"Should able to clear all the filters ", "Successfully all the filters are cleared ",
					"Failed to Clear all the filters");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the Clear filters ", "Should able to clear all the filters ",
					"Unable to clear all the filters ",
					Common.getscreenShotPathforReport("Failed to Clear all the filters"));
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
			Common.assertionCheckwithReport(records.equals("1 records found"),
					"Validating the new saved address with records",
					"User should be able save address and record should be found ",
					"Successfully address has been saved and record found", "Failed to save the address");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the new saved address with records",
					"User should be able save address and record should be found ",
					"unable to save address and record not found",
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
						"Validating the order number on orders page", "User should be able see the order number  ",
						"Successfully order number is displayed on the orders page",
						"Failed to see order number on order page");

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the order number on orders page",
					"User should be able see the order number  ",
					"Unable to dispaly the order number on the orders page",
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
						"Validating the order number on Returns page",
						"User should be able see order number on the Returns page  ",
						"Successfully order number is dispalyed on the Returns page",
						"Failed to see order number on the Returns page");
			}

		} catch (Exception | Error e)

		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the order number on Returns page",
					"User should be able see order number on the Returns page  ",
					"unable to display order number on the Returns page",
					Common.getscreenShotPathforReport("Failed to see order number on the  Returns page"));
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
							"Validating the productname on the whishlist page",
							"User should be able see product name on the whishlist page  ",
							"Successfully productname is displayed on the whishlist page",
							"Failed to seed productname on the whishlist page");

				}

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("Validating the productname on the whishlist page",
						"User should be able see product name on the whishlist page  ",
						"Unable to display the productname on the whishlist page",
						Common.getscreenShotPathforReport("Failed to see productname on the whishlist page"));
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
					Common.assertionCheckwithReport(checkbox.equals("true"), "Validating the newsletter checkbox clicked",
							"newsletter checkbox should be selected the checkbox",
							"Successfully newsletter checkbox is selected", "Failed to select newsletter checkbox");

				} else {
					Assert.fail();
				}

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("Validating the newsletter checkbox clicked",
						"newsletter checkbox should be selected the checkbox", "unable to select the newsletter checkbox",
						Common.getscreenShotPathforReport("Failed to select newsletter checkbox"));
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
						"Successfully navigate to Customer filed page", "Failed to navigate to the Customer filed page");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("Validating customers filed page navigation ",
						"after clicking save button it will navigate Customer filed page",
						"Unable to navigate to the Customer filed page",
						Common.getscreenShotPathforReport("Failed to navigate to the Customer filed page"));
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
						"after clicking save button it will navigate Customer filed page and message should be dispalyed",
						"Successfully navigate to Customer filed page and message is dispalyed",
						"Failed to navigate to Customer filed page");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("Validating customers filed page navigation ",
						"after clicking save button it will navigate Customer filed page",
						"Unable to  navigate to the Customer filed page and message not dispalyed",
						Common.getscreenShotPathforReport("Failed to navigate to Customer filed page"));
				Assert.fail();
			}

		}

		public void click_content() {
			// TODO Auto-generated method stub
			try {
				Common.switchToFirstTab();
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//li[@id='menu-magento-backend-content']");
				Sync.waitElementPresent("xpath", "//li[@id='menu-magento-backend-content']");
				String content = Common.findElement("xpath", "//strong[contains(text(),'Content')]").getText();
				System.out.println(content);
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
				Common.assertionCheckwithReport(content.equals("Content"),
						"To validate the content menu after admin clicks on the content from the main menu",
						"After clicking the content Admin should display the content menu options",
						"Admin successfully clicked the content and it displayed the Content Menu",
						"Admin failed to click the Content menu");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog(
						"To validate the content menu after admin clicks on the content from the main menu",
						"After clicking the content Admin should display the content menu options",
						"Admin failed to click the content from the mail menu",
						Common.getscreenShotPathforReport("Admin failed to click on the content menu"));
				Assert.fail();
			}

		}
		public void pages() {

			try {
				Sync.waitElementPresent("xpath", "//span[text()='Pages']");
				Common.clickElement("xpath", "//span[text()='Pages']");
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
				Common.assertionCheckwithReport(Common.getPageTitle().equals("Pages / Magento Admin"),
						"Validating content field page navigation ", "After clicking on pages it will navigate page field ",
						"Successfully navigate to the page field ", "Failed to navigate to the page filed");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("Validating content filed page navigation ",
						"after clicking on pages it will navigate page filed ", "Unable to navigate to the page filed ",
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
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

				Common.assertionCheckwithReport(
						Common.getPageTitle().equals("New Page / Pages / Elements / Content / Magento Admin"),
						"Validating edit page bulider navigation ",
						"after clicking on the edit page builder it will navigate to the edit page builder field ",
						"Successfully navigate to the edit page builder field ",
						"Failed to navigate to the edit page builder field");
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("Validating edit page bulider navigation ",
						"after clicking on the edit page builder it will navigate to the edit page builder field ",
						"Unable to  navigate to the edit page builder field ",
						Common.getscreenShotPathforReport("Failed to navigate to the edit page builder filed"));
				Assert.fail();
			}
		}
		public void Contentpage() {
			// TODO Auto-generated method stub
			try {
				Common.scrollIntoView("xpath", "//strong//span[text()='Content']");
				Common.javascriptclickElement("xpath", "//strong//span[text()='Content']");
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Sync.waitElementPresent(30, "xpath", "//button[@class='action-default action']");
				Common.mouseOverClick("xpath", "//button[@class='action-default action']");
				Sync.waitElementPresent(40, "xpath", "//div[contains(@data-bind,'pageBuilderEditButtonClick')]");
				String pagebuilder = Common.findElement("xpath", "//div[contains(@data-bind,'pageBuilderEditButtonClick')]")
						.getAttribute("class");
				System.out.println(pagebuilder);
				Common.assertionCheckwithReport(pagebuilder.contains("pagebuilder-wysiwyg-overlay"),
						"Validating edit page bulider navigation ",
						"After clicking on edit page builder it Should navigate to edit page builder field ",
						"Successfully navigate to the edit page builder field",
						"Failed to navigate to the edit page builder field");
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("Validating edit page bulider navigation ",
						"After clicking on edit page builder it Should navigate to edit page builder field ",
						"Unable navigate to the edit page builder field",
						Common.getscreenShotPathforReport("Failed to navigate to the edit page builder field"));

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
			String deletemessage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
			System.out.println(deletemessage);
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Pages / Magento Admin")
							&& deletemessage.equals("The page has been deleted."),
					"Validating page filed  navigation and customer deleted message",
					"after clicking save button it will navigate page filed and message should be displayed",
					"Successfully navigate to page filed and message is dispalyed", "Failed to navigate to page filed");
		}
		
		public void hot_elements() {
			try {
				Sync.waitElementPresent("xpath", "//h4[text()='HoT Elements']");
				Common.clickElement("xpath", "//h4[text()='HoT Elements']");
				String hotelements = Common.findElement("xpath", "//li[@id='menu-section-hot_elements']")
						.getAttribute("class");

				Common.assertionCheckwithReport(hotelements.contains("active"),
						"To Verify the Hot element drop down contents is displayed ",
						"After clicking on hot elements contents should display",
						"Successfully hot elements contents displayed ", "Failed to display hot elements contents");
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("To Verify the Hot element drop down contents is displayed ",
						"After clicking on hot elements contents should display",
						"Unable to display the hot elements contents",
						Common.getscreenShotPathforReport("Failed to display hot elements contents"));
				Assert.fail();
			}
		}
		
		public WebElement Promo_Content() {
			// TODO Auto-generated method stub

			WebElement element = Common.findElement("xpath", "//span[text()='Promo Content (Product)']");
			Common.clickElement("xpath", "//span[text()='Promo Content (Product)']");

			return element;

		}
		public void dragndrop_valueprop_Banner() {
			// TODO Auto-generated method stub
			try {
				Common.scrollIntoView("xpath", "//span[text()='Value Prop Banner']");
				WebElement elements = Common.findElement("xpath", "//span[text()='Value Prop Banner']");
				draganddropContentBlock(elements);
				String blocknames = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']/div")
						.getAttribute("data-content-type");
				Common.assertionCheckwithReport(blocknames.equals("hot_value_prop_banner"),
						"Validating the value prop banner Dragndrop operation",
						"value prop banner dragndrop to content with options",
						"successfully dragndrop the value prop banner with options ",
						"fail to dragndrop the value prop banner");
			} catch (Exception e) {

				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("Validating value prop banner Dragndrop operation",
						"value prop banner dragndrop to content with options",
						"Unable to  dragndrop the value prop banner with options ",
						Common.getscreenShotPathforReport("fail to dragndrop the value prop banner"));
				Assert.fail();

			}

		}
		public void draganddropContentBlock(WebElement source) {
			try {
				Common.dragdrop(source, "xpath", "//div[contains(@class,'pagebuilder-emp')]");

			} catch (Exception | Error e) {

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
						"after Clicking on the edit button it should be navigate to the edit promo page ",
						"Successfully it is navigated to the edit promo page ",
						"Failed to navigate to the edit promo page");

			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validation Navigation to the edit promo page ",
						"after Click on edit button it should be navigate to the edit promo page ",
						"Unablr to navigate to the edit promo page ",
						Common.getscreenShotPathforReport("Failed to navigate to the edit promo page"));
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
						"To validate the color is applied in the Promo content background ",
						"After clicking on the color the background color should be applied ",
						"Successfully Background color is applied ", "Failed to apply backgroud color");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("To validate the color is applied in the Promo content background ",
						"After clicking on the color the background color should be applied ",
						"Unable to apply the  Background color ",
						Common.getscreenShotPathforReport("Failed to apply backgroud color"));
				Assert.fail();

			}
		}
		public void edit_heading_mobile_valueprop_banner(String Dataset) {
			// TODO Auto-generated method stub
			try {
				Common.clickElement("xpath", "//select[@name='mobile_layout']");
				Common.dropdown("xpath", "//select[@name='mobile_layout']", Common.SelectBy.TEXT,
						data.get(Dataset).get("mobilelayout"));
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
				String colortext = Common.findElement("xpath", "//h2[@class='m-heading__text']").getText();
				System.out.println(colortext);
				Common.assertionCheckwithReport(colortext.equals("Text"),
						"validating  the color applied on the backgroud Text ",
						"after Clicking on the color the background Text color should be applied ",
						"Successfully Background Text color  is applied ", "Failed to apply backgroud Text color");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validation of the color applied in the backgroud Text ",
						"after Clicking on the color the background Text color should be applied ",
						"Unable to apply the Background Text color ",
						Common.getscreenShotPathforReport("Failed to apply backgroud Text color"));
				Assert.fail();
			}
		}
		
		public void edit_valueprop_banner_one() {
			// TODO Auto-generated method stub
			try {
				Sync.waitElementPresent(30, "xpath", "//div[@class='pagebuilder-content-type-wrapper']");
				String id = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']")
						.getAttribute("id");

				Common.mouseOverClick("xpath", "(//div[@id='" + id + "']//i[@class='icon-admin-pagebuilder-systems'])[2]");

				String editvlauepropcard = Common.findElement("xpath", "//h1[@class='modal-title']").getText();

				Common.assertionCheckwithReport(editvlauepropcard.contains("Edit Value Prop Card"),
						"validating Navigation to the edit  value prop Card page ",
						"after Click on edit button it should be navigate to the edit value prop card page ",
						"Successfully it is navigated to edit value prop card  page ",
						"Failed to navigate to edit value prop card page");

			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validati Navigation to the edit  value prop Card page ",
						"after Click on edit button it should bnge navigate to the edit value prop card page ",
						"Unable to  navigated to the edit value prop card  page ",
						Common.getscreenShotPathforReport("Failed to navigate to the edit value prop card page"));
				Assert.fail();

			}
		}
		
		public void icon_image_one(String Dataset) {
			// TODO Auto-generated method stub

			String path = System.getProperty("user.dir") + ("\\src\\test\\resources\\TestData\\Admin\\Lotusqa.png");
			try {

				Sync.waitElementPresent(30, "xpath", "(//input[@type='file'])[4]");
				String id = Common.findElement("xpath", "(//input[@type='file'])[4]").getAttribute("id");
				Common.findElement("xpath", "//input[@id='" + id + "']").sendKeys(path);
				String image = Common.findElement("xpath", "//div[@class='file-uploader-filename']").getText();
				System.out.println(image);
				Common.assertionCheckwithReport(image.equals("Lotusqa.png"),
						"validating  the image uploading on content for icon image ",
						"Image should be upload for icon image", "Successfully image uploaded in icon image ",
						"Failed to upload image on the icon image");
				String newid = Common.findElement("xpath", "//input[@name='alt']").getAttribute("id");
				Common.textBoxInput("xpath", "//input[@id='" + newid + "']", data.get(Dataset).get("Attachment"));
				Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
				Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");

			}

			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validation the image uploading on content for icon image ",
						"Image should be upload for icon image", "Unable to upload the image for icon image ",
						Common.getscreenShotPathforReport("Failed to upload image for icon image"));
				Assert.fail();

			}

		}
		
		public void edit_valueprop_banner_Two() {
			// TODO Auto-generated method stub
			try {
				Sync.waitPageLoad(40);
				Sync.waitElementPresent(30, "xpath", "//div[@class='pagebuilder-content-type-wrapper']");
				String id = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']")
						.getAttribute("id");
//				Sync.waitElementPresent(30, "xpath","//div[@id='" + id+ "']//div[@class='pagebuilder-content-type-wrapper']");
				Common.mouseOver("xpath", "(//div[@id='" + id + "']//i[@class='icon-admin-pagebuilder-systems'])[3]");
				Common.clickElement("xpath", "(//div[@id='" + id + "']//i[@class='icon-admin-pagebuilder-systems'])[3]");

				String editvlauepropcard = Common.findElement("xpath", "//h1[@class='modal-title']").getText();

				Common.assertionCheckwithReport(editvlauepropcard.contains("Edit Value Prop Card"),
						"validating the Navigation to the edit  value prop Card page ",
						"after Click on edit button it should be navigate to the edit value prop card page ",
						"Successfully it is navigated to the edit value prop card  page ",
						"Failed to navigate to edit value prop card page");

			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validation Navigation to the edit  value prop Card page ",
						"after Click on edit button it should be navigate to the edit value prop card page ",
						"Unable to navigate to the edit value prop card  page ",
						Common.getscreenShotPathforReport("Failed to navigate to the edit value prop card page"));
				Assert.fail();

			}
		}
		
		public void icon_image_two(String Dataset) {
			// TODO Auto-generated method stub
			String path = System.getProperty("user.dir") + ("\\src\\test\\resources\\TestData\\Admin\\Lotusqa1.png");
			try {

				Sync.waitElementPresent(30, "xpath", "(//input[@type='file'])[4]");
				String id = Common.findElement("xpath", "(//input[@type='file'])[4]").getAttribute("id");
				Common.findElement("xpath", "//input[@id='" + id + "']").sendKeys(path);
				String image = Common.findElement("xpath", "//div[@class='file-uploader-filename']").getText();
				System.out.println(image);
				Common.assertionCheckwithReport(image.equals("Lotusqa1.png"),
						"validating the image uploading on content for icon image ",
						"Image should be upload for icon image", "Successfully image uploaded for the icon image ",
						"Failed to upload image for the icon image");
				String newid = Common.findElement("xpath", "//input[@name='alt']").getAttribute("id");
				Common.textBoxInput("xpath", "//input[@id='" + newid + "']", data.get(Dataset).get("Attachment"));
				Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
				Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");

			}

			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the image uploading on content for icon image ",
						"Image should be upload for icon image", "Unable to upload the  image for icon image ",
						Common.getscreenShotPathforReport("Failed to upload image for the icon image"));
				Assert.fail();

			}

		}
		public void edit_valueprop_banner_Three() {
			// TODO Auto-generated method stub
			try {
				Sync.waitElementPresent(30, "xpath", "//div[@class='pagebuilder-content-type-wrapper']");
				String id = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']")
						.getAttribute("id");

				Common.mouseOver("xpath", "(//div[@id='" + id + "']//i[@class='icon-admin-pagebuilder-systems'])[4]");
				Common.clickElement("xpath", "(//div[@id='" + id + "']//i[@class='icon-admin-pagebuilder-systems'])[4]");

				String editvlauepropcard = Common.findElement("xpath", "//h1[@class='modal-title']").getText();

				Common.assertionCheckwithReport(editvlauepropcard.contains("Edit Value Prop Card"),
						"validating the Navigation to the edit  value prop Card page ",
						"after Click on edit button it should be navigate to the edit value prop card page ",
						"Successfully it is navigated to edit value prop card  page ",
						"Failed to navigate to the edit value prop card page");

			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validation Navigation to the edit  value prop Card page ",
						"after Click on edit button it should be navigate to the edit value prop card page ",
						"Unable to navigate to the edit value prop card  page ",
						Common.getscreenShotPathforReport("Failed to navigate to the edit value prop card page"));
				Assert.fail();

			}

		}
		public void icon_image_galary(String Dataset) {
			// TODO Auto-generated method stub
			try {
				Sync.waitPageLoad();
				Common.clickElement("xpath", "(//label[text()='Select from Gallery'])[7]");
				Sync.waitPageLoad();
				String gallery = Common.findElement("xpath", "//span[contains(@class,'fileinput')]//span").getText();
				Common.assertionCheckwithReport(gallery.equals("Upload Images"),
						"To validate the page navigated to the insert pages when we click on the upload from gallery ",
						"After Click on the upload from the gallery it should navigate to the insert pages",
						"Successfully It is navigated to the insert pages after clicking on the upload from gallery ",
						"Failed to Navigate to the insert pages after clicking on the upload from the gallery");

				Common.scrollIntoView("xpath", "(//small[text()='cms-corporate-purcha...'])[2]");
				Sync.waitElementPresent(30, "xpath", "(//small[text()='cms-corporate-purcha...'])[2]");
				Common.clickElement("xpath", "(//small[text()='cms-corporate-purcha...'])[2]");
				Common.scrollIntoView("xpath", "//span[text()='Add Selected']");
				Sync.waitElementPresent(30, "xpath", "//span[text()='Add Selected']");
				Common.clickElement("xpath", "//span[text()='Add Selected']");
				Sync.waitPageLoad();
				String newid = Common.findElement("xpath", "//input[@name='alt']").getAttribute("id");
				Common.textBoxInput("xpath", "//input[@id='" + newid + "']", data.get(Dataset).get("Attachment"));
				Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
				Sync.waitElementPresent("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
				Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
				String imageupload = Common.findElement("xpath", "(//div[contains(@data-bind,'visible: hasData()')])[3]")
						.getAttribute("data-bind");
				Common.assertionCheckwithReport(imageupload.contains("isShowImageUploadOptions"),
						"validating the image uploading on content in value prop banner ",
						"Image should be upload for value prop baneer",
						"Successfully image uploaded in the value prop banner ",
						"Failed to upload image on the value prop banner");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validation the image uploading on content in value prop banner ",
						"Image should be upload for value prop baneer", "Unable to upload image on the value prop banner ",
						Common.getscreenShotPathforReport("Failed to upload image on the value prop banner"));
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

				Sync.waitElementVisible("xpath", "//div[@data-ui-id='messages-message-success']");
				String savethepage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();

				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
				Common.assertionCheckwithReport(savethepage.contains("You saved the page."),
						" To Validate the User needs to save the page", "User should able to save the page",
						"Sucessfully User saves the page", "Failed to save the page");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog(" To Validate the User needs to save the page",
						"User should able to save the page", "Unable to saves the page",
						Common.getscreenShotPathforReport("Failed to save the page"));
				Assert.fail();
			}

		}
		public void openwebsite(String Dataset) {
			String pagetitle = data.get(Dataset).get("pageTitle");
			try {
				Sync.waitPageLoad(60);

				String currentAdminURL = Common.getCurrentURL();
				String urlkey = pagetitle.toLowerCase();
				System.out.println(urlkey);
				Common.openNewTab();
				if (currentAdminURL.contains("stage")) {
					Common.oppenURL(data.get(Dataset).get("URL") + urlkey);
				} else {
					Common.oppenURL(data.get(Dataset).get("preprodURL") + urlkey);

				}
				Sync.waitPageLoad(40);
				String uname = Common.getPageTitle();
				Common.assertionCheckwithReport(uname.contains(pagetitle),
						"Validating the User lands to the Hydroflask page",
						"User should able to land on the Hydroflask page", "Sucessfully User lands on the Hydroflask page",
						"Failed to navigate to the hydroflask page");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("Validating the User lands to the Hydroflask page",
						"User should able to land on the Hydroflask page", "Unable to Navigate to the Hydroflask page",
						Common.getscreenShotPathforReport("Failed to navigate to the hydroflask page"));

				Assert.fail();
			}
		}
		public void valueprop_website() {
			// TODO Auto-generated method stub
			try {
				Sync.waitElementPresent(30, "xpath", "(//img[@alt='Fixed'])[1]");
				String id1 = Common.findElement("xpath", "(//img[@alt='Fixed'])[1]").getAttribute("data-pb-style");
				System.out.println(id1);
				String image1 = Common.findElement("xpath", "//img[@data-pb-style='" + id1 + "']").getAttribute("src");
				System.out.println(image1);
				String id2 = Common.findElement("xpath", "(//img[@alt='Fixed'])[2]").getAttribute("data-pb-style");
				System.out.println(id2);
				String image2 = Common.findElement("xpath", "//img[@data-pb-style='" + id2 + "']").getAttribute("src");
				System.out.println(image2);
				String id3 = Common.findElement("xpath", "(//img[@alt='Fixed'])[3]").getAttribute("data-pb-style");
				System.out.println(id3);
				String image3 = Common.findElement("xpath", "//img[@data-pb-style='" + id3 + "']").getAttribute("src");
				System.out.println(image3);
				Common.assertionCheckwithReport(
						image1.contains("Lotusqa") && image2.contains("Lotusqa1")
								&& image3.contains("cms-corporate-purchasing"),
						"validating the image uploading on content in frontend website ",
						"Image should be upload on the frontend website",
						"Successfully image uploaded on the frond end image ", "Failed to upload image on the frond end");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the image uploading on content in frontend website ",
						"Image should be upload for frontend website", "Unable to upload the image on the frond end ",
						Common.getscreenShotPathforReport("Failed to upload image on the frond end"));
				Assert.fail();
			}
		}
		public void clone_valueprop_banner() {
			// TODO Auto-generated method stub
			try {

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
				Thread.sleep(6000);
				String savethepage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
				Common.assertionCheckwithReport(savethepage.equals("You saved the page."),
						"Validating the User need to save the page", "User should able to save the page",
						"Sucessfully User saves the page", "Unable to save the page");
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("Validating the User need to save the page",
						"User should able to save the page", "Unable to save the page",
						Common.getscreenShotPathforReport("Unable to save the page"));
				Assert.fail();

			}

		}
		public void vlaueprop_clone_frontend(String Datset) {
			// TODO Auto-generated method stub
			try {
				Sync.waitPageLoad(40);
				Common.switchToSecondTab();
				Common.refreshpage();
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String image = Common.findElement("xpath", "(//img[@data-element='desktop_image'])[8]")
						.getAttribute("data-pb-style");
				System.out.println();
				String headingname = Common.findElement("xpath", "//img[@data-pb-style='" + image + "']")
						.getAttribute("src");
				Common.assertionCheckwithReport(headingname.contains("cms-corporate-purchasing"),
						"validating the text on clone value prop banner on fornt end",
						"Text should be add for the clone value prop baneer on fornt end",
						"Successfully text added to the  clone value prop banner on front end",
						"Failed to add text on the clone value prop banner on front end");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the text on clone value prop banner on fornt end",
						"Text should be add for the clone value prop baneer on fornt end",
						"Unable to add the text on the clone value prop banner on front end",
						Common.getscreenShotPathforReport(
								"Failed to add text on the clone value prop banner on front end"));
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
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
				Common.assertionCheckwithReport(
						Common.getPageTitle().equals("Pages / Magento Admin")
								&& deletemessage.equals("The page has been deleted."),
						"To Validate Page field navigation and Page deleted message",
						"It should click on the Delete page it will navigate to Page field and page deleted message should be displayed",
						"Successfully navigate to page field and Page delete message is displayed",
						"Failed to navigate to page filed and message is not displayed");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("To Validate Page field navigation and Page deleted message",
						"It should click on the Delete page it will navigate to Page field and page deleted message should be displayed",
						"Unable to navigat to page field and Page delete message is not displayed",
						Common.getscreenShotPathforReport("Failed to navigate to page filed and message is not displayed"));
				Assert.fail();
			}
		}
	
}
