package models.admin;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Common.SelectBy;
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
					delete_existing_page(Dataset);
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
			Common.switchToFirstTab();
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
		
		public void dragndrop_heroBanner() {
			try {
				Common.scrollIntoView("xpath", "//span[text()='Hero Banner']");
				WebElement element = Common.findElement("xpath", "//span[text()='Hero Banner']");
				draganddropContentBlock(element);
				String bannercontent = Common.findElement("xpath", "//div[@class='file-uploader-area']/label").getText();
				System.out.println(bannercontent);
				String blockname = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']/div")
						.getAttribute("data-content-type");

				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
				Common.assertionCheckwithReport(blockname.equals("hot_hero_banner"),
						"Validating Hero Banner Dragndrop operation", "Hero Banner dragndrop to content with options",
						"successfully dragndrop the Hero Banner with options ", "Fail to dragndrop the Hero Banner");
			} catch (Exception e) {

				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("Validating Hero Banner Dragndrop operation",
						"User should able Dragndrop Hero Banner", "Sucessfully Dragndrop the Hero Banner",
						Common.getscreenShotPathforReport("Failed to Dragndrop Hero Banner"));
				Assert.fail();

			}
		}
		public void edit_Herobanner() {
			try {
				Sync.waitElementPresent(30, "xpath", "//div[@class='pagebuilder-content-type-wrapper']");
				
				Sync.waitPageLoad();
				Common.mouseOver("xpath", "//div[contains(@class,'c-hero-block')]");
				Sync.waitElementVisible("xpath", "//div[contains(@class,'options-middle')]");
				 

				String pagebuilderoptions = Common.findElement("xpath", "//div[contains(@class,'options-middle')]").getAttribute("class");
				
				if(pagebuilderoptions.contains("show")) {
					Sync.waitElementVisible("xpath", "//i[contains(@class,'pagebuilder-systems')]");
					Common.javascriptclickElement("xpath", "//i[contains(@class,'pagebuilder-systems')]");
					Sync.waitPageLoad();
				}
				else {
					Common.mouseOver("xpath", "//div[contains(@class,'c-hero-block')]");
					Sync.waitElementVisible("xpath", "//div[contains(@class,'options-middle')]");
					Common.javascriptclickElement("xpath", "//i[contains(@class,'pagebuilder-systems')]");
					Sync.waitPageLoad();
				}
				
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
				String editbanner = Common.findElement("xpath", "(//h1[@class='modal-title'])[1]").getText();
				
				
				Common.assertionCheckwithReport(editbanner.contains("Edit Hero Banner"),
						"To validate the admin is able to edit  Hero Banner",
						"After Clicking on the edit button admin should navigate to the edit Hero Banner page ",
						"Admin Successfully navigated to edit Hero Banner page ",
						"Failed to navigate to edit Hero Banner page");

			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("To validate the admin is able to edit  Hero Banner",
						"After Clicking on the edit button admin should navigate to the edit Hero Banner page ",
						"Admin is not able to  navigate to the edit Hero Banner page ",
						Common.getscreenShotPathforReport("Failed to navigate to edit Hero Banner page"));
				Assert.fail();

			}
		}
		public void edit_Herobanner_ContentSection(String DataSet) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(1000);
				String name = Common.getText("xpath", "(//span[text()='Content'])[3]");
				System.out.println(name);
				Sync.waitElementPresent("xpath", "(//input[@name='title'])[2]");
				Common.textBoxInput("xpath", "(//input[@name='title'])[2]", data.get(DataSet).get("title"));
				Common.dropdown("xpath", "//select[@name='heading_type']", Common.SelectBy.TEXT,
						data.get(DataSet).get("headingtype"));
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//input[@name='subtitle']");
				Common.textBoxInput("xpath", "//input[@name='subtitle']", data.get(DataSet).get("subtitle"));
				Common.dropdown("xpath", "//select[@name='subtitle_heading']", Common.SelectBy.TEXT,
						data.get(DataSet).get("headingtype"));

				Common.scrollIntoView("xpath", "//span[text()='Description']");
				Common.switchFrames("id", "hot_hero_banner_form_description_ifr");
				Common.findElement("id", "html-body").sendKeys(data.get(DataSet).get("Description"));
				Common.switchToDefault();
				// Thread.sleep(2000);
				Thread.sleep(5000);

				Common.assertionCheckwithReport(name.contains("Content"), "validation to the edit content promo Blocker",
						"after giving data it should show the data", "Successfully data is populated",
						"Succesfully data entered");
			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validation to the edit content promo Blocker ",
						"after giving data it should show the data", "Successfully data is populated",
						Common.getscreenShotPathforReport("Failed to show the data"));
				Assert.fail();

			}
		}
		public void herobanner_image() {
			// TODO Auto-generated method stub

			String path = System.getProperty("user.dir") + ("\\src\\test\\resources\\TestData\\Admin\\Lotusqa1.png");
			try {
				Sync.waitElementPresent("xpath", "(//input[@name='image_element_desktop'])[2]");
				Thread.sleep(6000);
				
				Common.findElement("xpath", "(//input[@name='image_element_desktop'])[2]").sendKeys(path);
				
				String image = Common.findElement("xpath", "(//div[@class='file-uploader-filename'])[1]").getText();
				System.out.println(image);
				Thread.sleep(5000);
				Common.assertionCheckwithReport(image.equals("Lotusqa1.png"),
						"To validate the image uploading on content for background image ",
						"Image should be upload on the background image",
						"Successfully image uploaded on the background image ",
						"Failed to upload image on the background image");

			}

			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("To validate the image uploading on content for background image ",
						"Image should be upload on the background image", "unable to upload image on the background image ",
						Common.getscreenShotPathforReport("Failed to upload image on the background image"));
				Assert.fail();

			}

		}
		
		public void edit_HeroBanner_ContentAlignment(String Dataset) {

			String alignment = data.get(Dataset).get("Alignment");
			String textcolor = data.get(Dataset).get("TextColor");
			String style = data.get(Dataset).get("Style");
			try {

				Common.scrollIntoView("xpath", "//span[text()='" + alignment + "']");
				Sync.waitElementPresent("xpath", "//span[text()='" + alignment + "']");
			
				Common.clickElement("xpath", "//span[text()='" + textcolor + "']");
				Common.clickElement("xpath", "//span[text()='" + style + "']");
				Common.assertionCheckwithReport(textcolor.contains("Dark"),
						"validation of the Content Alignment, Color, Style selection ",
						"Content Alignment, Color, Style should be open and it should be selected",
						"Successfully Content Alignment, Color, Style opend and it has been selected ",
						"Failed to open Content Alignment, Color, Style to select");

			} catch (Exception | Error e) {

				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validation of the Content Alignment, Color, Style selection ",
						"Content Alignment, Color, Style should be open and it should be selected",
						"Unable to open the Content Alignment, Color, Style and  has not been selected ",
						Common.getscreenShotPathforReport("Failed to open Content Alignment, Color, Style to select"));
				Assert.fail();
			}
		}
		public void edit_Hero_Banner_content_color(String Dataset) {
			// TODO Auto-generated method stub
			try {

				Common.scrollIntoView("xpath", "//div[contains(@class,'sp-preview-inner sp-clear-display')]");
				Common.clickElement("xpath", "//div[contains(@class,'sp-preview-inner sp-clear-display')]");
				String color = data.get(Dataset).get("Color");
				System.out.println(color);
				Common.clickElement("xpath", "//span[@title='" + color + "']");
				Common.clickElement("xpath", "//button[text()='Apply']");

				String appliedcolor = Common.findElement("xpath", "(//input[@class='colorpicker-spectrum'])[2]")
						.getAttribute("value");

				System.out.println(appliedcolor);

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
		public void editherobanner_Background_image() {
			// TODO Auto-generated method stub

			String path = System.getProperty("user.dir") + ("\\src\\test\\resources\\TestData\\Admin\\Lotusqa.png");
			try {
				Sync.waitElementPresent("xpath", "//input[@name='background_image']");
				Common.findElement("xpath", "//input[@name='background_image']").sendKeys(path);
				
				String image = Common.findElement("xpath", "(//div[@class='file-uploader-filename'])[2]").getText();
				
				
				
				System.out.println(image);
				Thread.sleep(5000);
				Common.assertionCheckwithReport(image.equals("Lotusqa.png") ,
						"To validate the image uploading on content for background image ",
						"Image should be upload on the background image",
						"Successfully image uploaded on the background image ",
						"Failed to upload image on the background image");

			}

			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("To validate the image uploading on content for background image ",
						"Image should be upload on the background image", "unable to upload image on the background image ",
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
				String background = Common.findElement("xpath", "(//select[@name='background_attachment']//option)[2]")
						.getText();
				Common.assertionCheckwithReport(background.equals("Fixed"),
						"validation of the back ground attachemnt drop down ",
						"back groud attachment drop down should be open and its should be select",
						"Successfully dropdown should be opend and text has been selected ",
						"Failed to open drop down and text to select");
				//Common.clickElement("xpath", "//span[text()='Background Repeat']");

				Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
				Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
				

			} catch (Exception | Error e) {

				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validation of the back ground attachemnt drop down ",
						"back groud attachment drop down should be open and its should be select",
						"Unable to open the dropdown and text has not been selected ",
						Common.getscreenShotPathforReport("Failed to open drop down and text to select"));
				Assert.fail();
			}
		}
		public void website_verification_Herobanner() {

			try {

				Sync.waitElementPresent(40, "xpath", "//div[@data-content-type='hot_hero_banner']");

				Thread.sleep(5000);
				String headingverification = Common.getText("xpath", "//h2[@class='c-hero-block__headline']");
				System.out.println(headingverification);
				Common.assertionCheckwithReport(headingverification.contains("QA test Hero Banner"),
						"validation Image upload in the forntend website ", "Image should  appear on fornt end page",
						"Successfully image is appeared on the frondend", "Failed to navigate to appear on fornt end page");
				
				
				
				
				
			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validation Image upload in the forntend website ",
						"Image should  appear on front end page", "Successfully image is appeared on the frond end",
						Common.getscreenShotPathforReport("Failed to navigate to appear on fornt end page"));
				Assert.fail();

			}

		}
		public void website_image_verification_herobanner() {
			// TODO Auto-generated method stub
			try {

				Sync.waitElementPresent(40, "xpath", "//div[contains(@class,'c-hero-block__image')]");
				String imageverification = Common.findElement("xpath", "//div[contains(@class,'c-hero-block__image')]")
						.getAttribute("data-background-images");
				System.out.println(imageverification);
				
				Sync.waitElementPresent(40, "xpath", "//img[@data-element='image_element_desktop']");
				String imageverification2 = Common.findElement("xpath", "//img[@data-element='image_element_desktop']")
						.getAttribute("src");
				System.out.println(imageverification2);
				
				
				Common.assertionCheckwithReport(imageverification.contains("Lotusqa") && imageverification2.contains("Lotusqa1"),
						"validating the  Image upload on the forntend website ",
						"Image should de appear on the fornt end page", "Successfully image is appeared on the frond end",
						"Failed to appear image on the front end");
			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validation Image upload in the fornt end website ",
						"Image should de appear on fornt end page", "unable to appear image on the frond end",
						Common.getscreenShotPathforReport("Failed to appear image on the front end"));
				Assert.fail();

			}

		}
		public void editherobanner_video(String Dataset) {
			// TODO Auto-generated method stub
	      String video= data.get(Dataset).get("VideoURL");
			try {
				
				Common.switchToFirstTab();
				Contentpage();
				edit_Herobanner();
				Common.findElement("xpath", "(//button[@class='action-remove'])[1]");
				Common.clickElement("xpath", "(//button[@class='action-remove'])[1]");
				
				Sync.waitElementPresent("xpath", "//input[@name='video_element[video_url]']");
		
				Common.textBoxInput("xpath", "//input[@name='video_element[video_url]']", data.get(Dataset).get("VideoURL"));
				Common.clickElement("xpath", "(//div[contains(@class,'sp-preview-')])[2]");
				
				Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
				Sync.waitElementPresent("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
				Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");


			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validation of video upload ",
						"after Clicking on thesave the video should be applied ",
						"Unable to save the  video url ",
						Common.getscreenShotPathforReport("Failed to save the video url"));
				Assert.fail();
			}
		}
		
		public void website_verification_video_Herobanner() {

			try {

				
				Thread.sleep(4000);
				Common.switchFrames("xpath", "//iframe[@id='VideoWorker-0']");			
				Sync.waitElementPresent(50,"xpath", "//video[@class='video-stream html5-main-video']");
			
				int video = Common.findElements("xpath", "//video[@class='video-stream html5-main-video']").size();
				System.out.println(video);
				Common.assertionCheckwithReport(video>0,
						"validation video upload in the forntend website ", "video should  appear on front end pag",
						"Successfully video is appeared on the frond end", "Failed to navigate to appear on fornt end page");
				
				
				
				
				
				
			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validation video upload in the forntend website ",
						"video should  appear on front end page", "Successfully video is appeared on the frond end",
						Common.getscreenShotPathforReport("Failed to navigate to appear on fornt end page"));
				Assert.fail();

			}

		}
		public void click_hero_product(String Dataset) {
			// TODO Auto-generated method stub
			try {
				Common.switchToFirstTab();	
				Contentpage();
		        hot_elements(); 
		        edit_Herobanner();
			
		        Common.dropdown("xpath", "//select[@class='admin__control-select url-input-select']", Common.SelectBy.TEXT,
						data.get(Dataset).get("heading"));
	 	       Common.clickElement("xpath", "//div[@class='admin__action-multiselect-wrap action-select-wrap']");
				
	    		String text= Common.findElement("xpath", "(//input[@class='admin__control-text admin__action-multiselect-search'])").getAttribute("id");
	    		Common.textBoxInput("xpath", "//input[@id='"+ text +"']", data.get(Dataset).get("productnames"));
	           Sync.waitElementPresent("xpath", "//span[text()='32 OZ WIDE MOUTH STAINLESS']");
	            Common.mouseOverClick("xpath", "//span[text()='32 OZ WIDE MOUTH STAINLESS']");
			
			Common.textBoxInput("xpath", "(//input[@name='link_text'])", data.get(Dataset).get("Buttontext"));
			Common.clickElement("xpath", "//input[@name='link_url']");
			int saved = Common.findElements("xpath", "(//div[@class='admin__fieldset-wrapper-content _hide'])").size();
			System.out.println(saved);
			Common.assertionCheckwithReport(saved>0,
					"Enter the categroy details ", "Category details enterd succesfully",
					"Successfully v", "Failed to enterd the category details");
			Configure_padding_marins(Dataset);
			 Sync.waitElementPresent("xpath", "//button[@id='save']");
			Common.clickElement("xpath", "//button[@id='save']");
		
		} catch (Exception e) {
	e.printStackTrace();
	ExtenantReportUtils.addFailedLog(" To Validate the User needs to save the page",
			"User should able to save the page", "Unable to saves the page",
			Common.getscreenShotPathforReport("Failed to save the page"));
	Assert.fail();
		}}
		
		
		
		
		
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
						"validating the close and cross button funtionality ",
						"After clickng close and cross button the page should be close",
						"Successfully page has been closed after clicking on close and cross button",
						"Failed to close the page after clicking on close and cross buttton");
			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validation the close and cross button funtionality ",
						"After clickng close and cross button the page should be close",
						"Unable to close the page after clicking on close and cross button",
						Common.getscreenShotPathforReport(
								"Failed to close the page after clicking on close and cross buttton"));
				Assert.fail();
			}

		}
		public void close_individual_page_one() {
			// TODO Auto-generated method stub
			try {
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
						"validating the close and cross button funtionality ",
						"After clickng on the close and cross button the page should be close",
						"Successfully page has been closed after clicking on close and cross button",
						"Failed to close the page after clicking on close and cross buttton");
			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validation the close and cross button funtionality ",
						"After clickng close and cross button the page should be close",
						"Unable to  close the page after clicking on close and cross button",
						Common.getscreenShotPathforReport(
								"Failed to close the page after clicking on close and cross buttton"));
				Assert.fail();
			}

		}
		
		public void close_individual_page_Two() {
			// TODO Auto-generated method stub
			try {
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
						"Failed to close the page after clicking on close and cross buttton");
			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validation the close and cross button funtionality ",
						"After clickng close and cross button the page should be close",
						"Unable to close the page after clicking on close and cross button",
						Common.getscreenShotPathforReport(
								"Failed to close the page after clicking on close and cross buttton"));
				Assert.fail();
			}

		}
		public void close_individual_page_Three() {
			// TODO Auto-generated method stub
			try {
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
						"validating the close and cross button funtionality ",
						"After clickng close and cross button the page should be close",
						"Successfully page has been closed after clicking on close and cross button",
						"Failed to close the page after clicking on close and cross buttton");
			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validation the close and cross button funtionality ",
						"After clickng close and cross button the page should be close",
						"Unable to close the page after clicking on close and cross button",
						Common.getscreenShotPathforReport(
								"Failed to close the page after clicking on close and cross buttton"));
				Assert.fail();
			}

		}
		
	
		public void Configure_textcolor_light() {

			try {

				int textcolor = Common.findElements("xpath", "//li[@class='active']//span[text()='Light']").size();
				if (textcolor > 0) {
					System.out.println("Dark color is selected");
				} else {
					Sync.waitElementClickable("xpath", "//span[text()='Light']");
					Common.mouseOverClick("xpath", "//span[text()='Light']");
				}
				int TextColor = Common.findElements("xpath", "//li[@class='active']//span[text()='Light']").size();
				

				Common.assertionCheckwithReport(TextColor > 0, "To validate the text colour selected is Light",
						" should select the text colour Light", "Sucessfully select the Light text color",
						"Failed to select the text colour Light");
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("To validate the text colour selected is Light",
						" should select the text colour Light", "Unable select the Dark text Light",
						Common.getscreenShotPathforReport("Failed to select the text colour Light"));
				e.printStackTrace();
				Assert.fail();

			}
		}

		public void Configure_padding_marins(String DataSet) {
			// TODO Auto-generated method stub
			String cssclss = data.get(DataSet).get("CSSclasses");
			String mrgtop = data.get(DataSet).get("mrgtop");
			String mrgright = data.get(DataSet).get("mrgright");
			String mrgbottom = data.get(DataSet).get("mrgbottom");
			String mrgleft = data.get(DataSet).get("mrgleft");
			String paddingtop = data.get(DataSet).get("paddingtop");
			String paddingright = data.get(DataSet).get("paddingright");
			String paddingbottom = data.get(DataSet).get("paddingbottom");
			String paddingleft = data.get(DataSet).get("paddingleft");
			try {
				Common.scrollIntoView("xpath", "//div[contains(@class,'fieldset-wrapper') and @data-index='advanced']");
				String advancedsection = Common
						.findElement("xpath", "//div[contains(@class,'fieldset-wrapper') and @data-index='advanced']")
						.getAttribute("class");
				if (advancedsection.contains("show")) {
					System.out.println("The Advanvced section is visible");
				} else {
					Common.mouseOverClick("xpath", "//span[text()='Advanced']");
				}

				Sync.waitElementVisible("xpath", "//div[@data-index='css_classes']");
				Common.textBoxInput("xpath", "//input[@name='css_classes']", cssclss);
				Common.scrollIntoView("name", "marginTop");
				Common.textBoxInput("name", "marginTop", mrgtop);
				Common.textBoxInput("name", "marginRight", mrgright);
				Common.textBoxInput("name", "marginBottom", mrgbottom);
				Common.textBoxInput("name", "marginLeft", mrgleft);
				Common.textBoxInput("name", "paddingTop", paddingtop);
				Common.textBoxInput("name", "paddingRight", paddingright);
				Common.textBoxInput("name", "paddingBottom", paddingbottom);
				Common.textBoxInput("name", "paddingLeft", paddingleft);

				Common.assertionCheckwithReport(advancedsection.contains("show"), "to validate the Padding and margins",
						"Pad" + "paddding and margin should apply successfully", "Paddding and margion applied",
						"Failed to apply padding");

			} catch (Exception e) {
				e.printStackTrace();
				report.addFailedLog("To validate the Padding and margins all the fields are populated",
						"paddding and margin should apply successfully", "Failed to apply the Paddding and margion",
						"Failed to apply padding and margin");

				Assert.fail();
			}

		}

		public void verify_Padding_fronytend(String DataSet) throws Exception {

			String mrgtop = data.get(DataSet).get("mrgtop");
			String mrgright = data.get(DataSet).get("mrgright");
			String mrgbottom = data.get(DataSet).get("mrgbottom");
			String mrgleft = data.get(DataSet).get("mrgleft");
			String paddingtop = data.get(DataSet).get("paddingtop");
			String paddingright = data.get(DataSet).get("paddingright");
			String paddingbottom = data.get(DataSet).get("paddingbottom");
			String paddingleft = data.get(DataSet).get("paddingleft");
			try {
				Sync.waitElementVisible(30, "xpath", "//p[@class='m-breadcrumb__text']");

				String style = Common.findElement("xpath", "//main[@id='maincontent']/div[2]/div/style")
						.getAttribute("innerHTML");
				System.out.println(style);
				if (style.contains("margin-top")) {

					Common.assertionCheckwithReport(
							style.contains("margin-top:" + mrgtop + "px;margin-right:" + mrgright + "px;margin-bottom:"
									+ mrgbottom + "px;margin-left:" + mrgleft + "px;padding-top:" + paddingtop
									+ "px;padding-right:" + paddingright + "px;padding-bottom:" + paddingbottom
									+ "px;padding-left:" + paddingleft + "px"),
							"to validate the Padding and margins", "Padding and margin should apply successfully",
							"Paddding and margion applied", "Failed to apply padding");
				} else {

					// margin:40px 27px 55px 48px;padding:100px 278px 300px 129px
					// margin:40px 27px 55px 48px;padding:100px 278px 300px 129px
					System.out.println("margin:" + mrgtop + "px " + mrgright + "px " + mrgbottom + "px " + mrgleft
							+ "px;padding:" + paddingtop + "px " + paddingright + "px " + paddingbottom + "px "
							+ paddingleft + "px");
					Thread.sleep(4000);
					Common.assertionCheckwithReport(
							style.contains("margin:" + mrgtop + "px " + mrgright + "px " + mrgbottom + "px " + mrgleft
									+ "px;padding:" + paddingtop + "px " + paddingright + "px " + paddingbottom + "px "
									+ paddingleft + "px"),
							"to validate the Padding and margins", "Padding and margin should apply successfully",
							"Paddding and margion applied", "Failed to apply padding");
				}
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail();
			}
		}
		public void Websiteverification_hero_product() {
			// TODO Auto-generated method stub
			try {
	            Common.refreshpage();
	            Common.scrollIntoView("xpath", "//a[@class='a-btn pagebuilder-button-primary']");
	            Sync.waitElementPresent(40, "xpath", "//a[@class='a-btn pagebuilder-button-primary']");
				Common.clickElement("xpath", "//a[@class='a-btn pagebuilder-button-primary']");
				//Common.switchWindows();
				Thread.sleep(5000);
				String headingverification = Common.getText("xpath", "//div[@class='c-product-overview u-container']");
				System.out.println(headingverification);
				Common.assertionCheckwithReport(headingverification.contains("32 OZ WIDE MOUTH STAINLESS"),
						"validation of PDP page in the forntend website ", "PDP should be appear on fornt end page",
						"Successfully PDP is appeared on the frondend", "Failed to navigate to PDP page");
				
				Common.navigateBack();
			} catch (Exception | Error e) {
				e.printStackTrace();

				report.addFailedLog("validation of PDP page in the forntend website ",
						"PDP should de appear on fornt end page", "Successfully image is appeared on the frondend",
						Common.getscreenShotPathforReport("Failed to navigate to PDP page"));
				Assert.fail();
			}

			
	}

		public void Editandsavepage() throws Exception {
			// TODO Auto-generated method stub
			Common.scrollIntoView("xpath", "//div[contains(@class,'header')]//span[text()='Save']");
			Sync.waitElementClickable("xpath", "//div[contains(@class,'header')]//span[text()='Save']");
			Thread.sleep(5000);
			Common.mouseOverClick("xpath", "//div[contains(@class,'header')]//span[text()='Save']");

			Sync.waitPageLoad();

		}

		public void Frontendverification(String DataSet) {
			String Title = data.get(DataSet).get("titleaatribute");
			String Description = data.get(DataSet).get("Description");
			String Video = data.get(DataSet).get("VideoURL");
			String heading = data.get(DataSet).get("headingtype").toLowerCase();
			String ctalinkpage = data.get(DataSet).get("Buttonlinkpage");
			String desktopimage = data.get(DataSet).get("image");
			String mobileimage = data.get(DataSet).get("image2");
			String altatt = data.get(DataSet).get("alterantivetext");
			String ctatext = data.get(DataSet).get("CTAText");

			try {
				Common.scrollIntoView("xpath", "//p[contains(@class,'plp-cms-block')]");
				String title = Common.findElement("xpath", "//p[contains(@class,'plp-cms-block')]").getText();

				int video = Common.findElements("xpath", "//div[contains(@class,'pagebuilder-video')]").size();
				if (video > 0) {

					String videoverification = Common.findElement("xpath", "//div[contains(@class,'pagebuilder-video')]")
							.getAttribute("data-video-src");
					
					
					Assert.assertEquals(videoverification, Video);
				} else {
					String imageverificationdesktop = Common
							.findElement("xpath", "//img[@data-element='image_element_desktop']").getAttribute("src");
					String imageverificationmobile = Common
							.findElement("xpath", "//img[@data-element='image_element_mobile']").getAttribute("src");
					String imagealtattribute = Common.findElement("xpath", "//img[@data-element='image_element_mobile']")
							.getAttribute("alt");

					Common.assertionCheckwithReport(
							imageverificationdesktop.contains(desktopimage) && imageverificationmobile.contains(mobileimage)
									&& imagealtattribute.contains(altatt),
							"To validate configured image in the PLP CMS block components are reflecting on the store front",
							"PLP CMS block images should reflect on the store front",
							"PLP CMS block images reflect on the store front",
							"Failed to reflect the plp cms block images on the store front");

				}

				String Heading = Common.findElement("xpath", "//p[contains(@class,'plp-cms-block')]")
						.getAttribute("data-heading-type");
				String CTAtext = Common.findElement("xpath", "//div[contains(@class,'cta')]//span[@class='a-btn__label']")
						.getText();

				String description = Common.findElement("xpath", "//div[contains(@class,'block__copy')]").getText();
				

				Common.assertionCheckwithReport(
						 title.contains(Title) && Heading.contains(heading)
								&& CTAtext.contains(ctatext),
						"To validate the PLP CMS block components are reflecting on the store front",
						"PLP CMS block components should reflect on the store front",
						"PLP CMS block components reflect on the store front",
						"Failed to reflect the plp cms bvlock components on the store front");
				
			} catch (Exception e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog(
						"To validate the PLP CMS block components are reflecting on the store front",
						"PLP CMS block components should reflect on the store front",
						"PLP CMS block components failed to reflect on the store front",
						"plp cms bvlock components reflect on the store front unsuccessfull");

				Assert.fail();
			}

		}
		public void valiadtetextcolordark() {
			String textcolor = Common.findElement("xpath", "//div[contains(@class,'block__content-')]")
					.getAttribute("class");
			Common.assertionCheckwithReport(textcolor.contains("dark"), "To validate the text color is dark", "The text color should be Dark", "The text color is Dark", "Failed to update the text color dark");
		}
		public void valiadtetextcolorlight() {
			String textcolor = Common.findElement("xpath", "//div[contains(@class,'block__content-')]")
					.getAttribute("class");
			Common.assertionCheckwithReport(textcolor.contains("light"), "To validate the text color is Light", "The text color should be Light", "The text color is Light", "Failed to update the text color Light");
		}

		public void CTA_LinksValidationFontend(String DataSet) throws Exception {
			String CTAnavigation = data.get(DataSet).get("ButtonLinknavigation");
			

			try {
				Common.clickElement("xpath", "//div[contains(@class,'cta')]//span[@class='a-btn__label']");
				Common.switchWindows();
				Sync.waitPageLoad();
				Sync.waitElementPresent("xpath", "//p[@class='m-breadcrumb__text']");
				String pagetitle = Common.getPageTitle();
				System.out.println(pagetitle);

				if (CTAnavigation.equals("Product")) {
					String product = data.get(DataSet).get("Buttonlinkproduct");
					System.out.println(product);
					
					Common.assertionCheckwithReport(pagetitle.contains(product), "To validate the CTA Product link", "CTA link should navigate to the product page", "CTA link navigated to product page", "Failed CTA Product Link navigation");
				
				} else if (CTAnavigation.equals("Page")) {
					String page = data.get(DataSet).get("Buttonlinkpage");
					
					
					Common.assertionCheckwithReport(pagetitle.contains(page), "To validate the CTA Product link", "CTA link should navigate to the product page", "CTA link navigated to product page", "Failed CTA Product Link navigation");
					

				} else if (CTAnavigation.equals("Category")) {
					String Category = data.get(DataSet).get("Buttonlinkcategory");
					
					Common.assertionCheckwithReport(pagetitle.contains(Category), "To validate the CTA Product link", "CTA link should navigate to the product page", "CTA link navigated to product page", "Failed CTA Product Link navigation");
					
					

				} else if (CTAnavigation.equals("URL")) {
					String url = data.get(DataSet).get("ButtonlinkURL");
					Common.assertionCheckwithReport(Common.getCurrentURL().contains(url), "To validate the CTA link navigate to URL", "CTA link should navigate to the url", "CTA link navigated to url", "Failed CTA Link navigation to URL");

				}
				Common.navigateBack();
				Sync.waitPageLoad();
				Sync.waitElementPresent("xpath", "//p[@class='m-breadcrumb__text']");
				


			} catch (Exception e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog(
						"To validate the CTA link navigation",
						"CTA link should navigate to the respective page",
						"CTA link navigation Failed",
						"CTA link navigation unsuccessfull");

				Assert.fail();
			}
		}
		

		public void Clone(String dataSet) {
			String component = data.get(dataSet).get("component");
			try {
				Sync.waitElementPresent("xpath", "//div[contains(@class,'" + component + "')]");
				Common.mouseOverClick("xpath", "//div[contains(@class,'" + component + "')]");
				String visible = Common.findElement("xpath", "//div[contains(@class,'pagebuilder-options ')]")
						.getAttribute("class");
				System.out.println(visible);
				Common.assertionCheckwithReport(visible.contains("-options-visible"),
						"validation Edit option  in the page builder ",
						"after mouse over on the page builder edit option should be appear ",
						"Successfully edit option appeared in the pagebuilder",
						"Failed to appear edit option when we mouse over");
				Sync.waitElementClickable("xpath", "//i[@class='icon-pagebuilder-copy']");
				Common.javascriptclickElement("xpath", "//i[@class='icon-pagebuilder-copy']");

				Sync.waitPageLoad();

				String classattribute = data.get(dataSet).get("attribute");
				int components = Common.findElements("xpath", "//div[contains(@class,'" + component + "')]").size();
				System.out.println(components);

				Common.assertionCheckwithReport(components > 0, "To validate the clone functionality",
						"Should clone the product tile ", "Clone of the card tile is successfull",
						"clone unctionality failed");
				savecontent(dataSet);
				Thread.sleep(3000);
				openwebsite(dataSet);
				verify_clonefunctionality_website(dataSet);
				// openwebsite("OXOproducttile");
				// verify_clonefunctionality_website(dataSet);

			} catch (Exception e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("Validating clone functionality",
						"User should able to clone the blocks or content", "Admin failed to clone the content",
						Common.getscreenShotPathforReport("Failed to clone the content of the page"));

				Assert.fail();

			}

		}

		public void verify_clonefunctionality_website(String dataSet) {
			String attribute = data.get(dataSet).get("attribute");
			try {
				Sync.waitElementClickable("xpath", "//div[contains(@class,'"+attribute+"')]");
				int components = Common.findElements("xpath", "//div[contains(@class,'"+attribute+"')]").size();
				System.out.println(components);
				Common.assertionCheckwithReport(components >= 2, "To validate the PLP block clone functionality",
						"Should display two PLP blocks", "2 PLP blocks are displayed in website",
						"Failed to clone the PLP Block ");
			} catch (Exception e) {
				e.printStackTrace();
				report.addFailedLog("To validate the product card clone functionality",
						"User should able to validate the product card clone functionality",
						"Admin failed to validate the product card clone functionality",
						Common.getscreenShotPathforReport("product card clone functionality unsuccessfull"));

				Assert.fail();
			}

		}

		public void Hide_functinality() {
			try {
				Sync.waitElementPresent("xpath", "//div[contains(@class,'m-media-card')]");
				Common.mouseOver("xpath", "//div[contains(@class,'m-media-card')]");
				String visible = Common.findElement("xpath", "//div[contains(@class,'pagebuilder-options ')]")
						.getAttribute("class");
				System.out.println(visible);
				Common.assertionCheckwithReport(visible.contains("-options-visible"),
						"validation Edit option  in the page builder ",
						"after mouse over on the page builder edit option should be appear ",
						"Successfully edit option appeared in the pagebuilder",
						"Failed to appear edit option when we mouse over");
				Sync.waitElementClickable("xpath", "//i[@class='icon-pagebuilder-hide']");
				Common.javascriptclickElement("xpath", "//i[@class='icon-pagebuilder-hide']");
				Common.isElementDisplayedonPage(10, "xpath", "//i[@class='icon-pagebuilder-show']");
				savecontent("ProductcardTile");
				openwebsite("ProductcardTile");
				Verify_websitehidefunctionality();
				openwebsite("OXOproducttile");
				Verify_websitehidefunctionality();

			} catch (Exception e) {
				e.printStackTrace();
				report.addFailedLog("Validating hide functionality", "User should able to hide the blocks or content",
						"Admin failed to hide the content",
						Common.getscreenShotPathforReport("Failed to hide the content of the page"));

				Assert.fail();

			}

		}

		public void Verify_websitehidefunctionality() {
			try {
				Sync.waitElementVisible("xpath", "//ol[@class='m-breadcrumb__list']");
				String text = Common.findElement("xpath", "(//div[@data-content-type='hot_card_tiles'])[1]")
						.getAttribute("data-pb-style");
				Common.assertionCheckwithReport(text != null, "To validate the magento hide functionality",
						"The Card tile block should be hidden", "The card tile is hidden", "Failed to hide the block");

			} catch (Exception e) {
				e.printStackTrace();
				report.addFailedLog("Validating clone functionality on the front end",
						"User should able to clone the blocks or content on the front end",
						"Admin failed to clone the content on the fornt end",
						Common.getscreenShotPathforReport("Failed to clone the content of the page"));

				Assert.fail();

			}

		}

		public void Delete_hotcomponent(String dataSet) throws Exception {
			String component = data.get(dataSet).get("datacontenttype");

			try {

				Sync.waitElementPresent("xpath", "//div[@data-content-type='" + component + "']");
				int no_ofcomponents = Common.findElements("xpath", "//div[@data-content-type='" + component + "']").size();
				System.out.println(no_ofcomponents);

				for (int i = 1; i < no_ofcomponents; i++) {

					Common.actionsKeyPress(Keys.HOME);
					Thread.sleep(3000);
					Common.mouseOverClick("xpath", "//div[@data-content-type='" + component + "']");
					String visible = Common.findElement("xpath", "//div[contains(@class,'pagebuilder-options ')]")
							.getAttribute("class");
					System.out.println(visible);
					Common.assertionCheckwithReport(visible.contains("-options-visible"),
							"validation Edit option  in the page builder ",
							"after mouse over on the page builder edit option should be appear ",
							"Successfully edit option appeared in the pagebuilder",
							"Failed to appear edit option when we mouse over");
					Sync.waitElementClickable("xpath", "//i[contains(@class,'remove')]");
					Common.mouseOverClick("xpath", "//i[contains(@class,'remove')]");
					Sync.waitElementVisible("xpath", "//aside[contains(@class,'show')]");
					Sync.waitElementVisible("xpath", "//span[text()='OK']");
					Common.clickElement("xpath", "//span[text()='OK']");
					Sync.waitPageLoad();

				}
				Thread.sleep(3000);
				int finalno_ofcomponents = Common.findElements("xpath", "//div[contains(@class,'" + component + "')]")
						.size();
				System.out.println(finalno_ofcomponents);

				Common.assertionCheckwithReport(finalno_ofcomponents == 0, "To validate the component is deleted",
						"Component should be deleted", "Component has beem deleted", "Failed to delete the component");
				savecontent(dataSet);
				openwebsite(dataSet);
				verifydeletefunctionality_website(dataSet);
				//openwebsite("OXOPLPBLOCK");
				//verifydeletefunctionality_website(dataSet);

			} catch (Exception e) {
				e.printStackTrace();
				//invalidformkey("ProductcardTile");
				report.addFailedLog("Validating content delete functionality",
						"User should able to delete the blocks or content", "Admin failed to delete the content",
						Common.getscreenShotPathforReport("Failed to delete the content of the page"));

				Assert.fail();

			}
		}

		public void invalidformkey(String dataSet) throws Exception {

			try {
				String pagetitle = Common.getPageTitle();
				if (pagetitle.contains("Dashboard")) {
					String formkey = Common.findElement("xpath", "//div[@data-ui-id='messages-message-error']").getText();
					if (formkey.contains("Invalid security or form key. Please refresh the page.")) {
						Common.refreshpage();
					}

					click_content();
					Sync.waitElementPresent("xpath", "//span[text()='Pages']");
					Common.clickElement("xpath", "//span[text()='Pages']");
					Sync.waitElementPresent(30, "xpath", "//input[@placeholder='Search by keyword']");
					Thread.sleep(3000);
					if (Common.isElementDisplayedonPage(30, "xpath", "//button[@class='action-remove']")) {
						Common.mouseOverClick("xpath", "//button[@class='action-remove']");
						Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
					} else {
						System.out.println("no Active filters found");
					}
					String pagename = data.get(dataSet).get("pageTitle");
					Common.findElement("xpath", "//input[@placeholder='Search by keyword']").sendKeys(pagename);
					Common.actionsKeyPress(Keys.ENTER);
					Sync.waitElementVisible(30, "xpath", "//div[contains(@class,'filters-current')]");
					String active = Common.findElement("xpath", "//div[contains(@class,'filters-current')]")
							.getAttribute("class");
					Common.assertionCheckwithReport(active.contains("show"), "To validate the search filters",
							"Should apply Search filter", "Search filetr is applied", "failed to apply for search filter");
					Thread.sleep(5000);
					int pages = Common.findElements("xpath", "//tr[contains(@class,'data-row')]").size();
					System.out.println(pages);

					Sync.waitElementVisible(30, "xpath", "//button[text()='Select']");
					Common.javascriptclickElement("xpath", "//button[text()='Select']");
					Sync.waitElementVisible(30, "xpath", "//a[text()='Edit']");
					Common.mouseOverClick("xpath", "//a[text()='Edit']");
					Sync.waitPageLoad();
					Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

					Common.assertionCheckwithReport(Common.getPageTitle().contains(pagename),
							"To validate the admin lands on the existingpage", "User Should land on the existing page",
							"Admin lands on the page", "Failed to land on the existing page");
					Contentpage();
					Delete_hotcomponent(dataSet);

				}
			} catch (Exception e) {
				e.printStackTrace();
				report.addFailedLog("Validating invalid form key error message in admin ",
						"User should able to validate the error message in the admin",
						"Admin failed to validate the error message",
						Common.getscreenShotPathforReport("Failed to validate the error messaage "));

				Assert.fail();
			}

		}

		public void verifydeletefunctionality_website(String DataSet) {
	String attribute = data.get(DataSet).get("datacontenttype");
			
			try {

				int blocks = Common.findElements("xpath", "//div[@data-content-type='"+attribute+"']").size();
				System.out.println(blocks);
				Common.assertionCheckwithReport(blocks <= 1, "To validate the PLP block clone functionality",
						"Should display two PLP blocks", "2 PLP blocks are displayed in website",
						"Failed to clone the PLP Block ");

			} catch (Exception e) {
				e.printStackTrace();
				report.addFailedLog("Validating delete functionality in the website",
						"User should able to clone the blocks or content",
						"Admin failed to validate the delete functionality on the website",
						Common.getscreenShotPathforReport("Failed to validate the delete functinality in the website"));
				Assert.fail();
			}
		}
		public void dragndrop_PLPCMS_Content() {
			try {
				WebElement element = Common.findElement("xpath", "//span[text()='PLP Block']");
				draganddropContentBlock(element);
				String blockname = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']/div")
						.getAttribute("data-content-type");
				Common.assertionCheckwithReport(blockname.equals("hot_plp_block"),
						"Validating cardtiles Dragndrop operation", "card tiles dragndrop to content with options",
						"successfully dragndrop the card tile with options ", "fail to dragndrop the card tile");

				Sync.waitElementPresent("xpath", "//div[contains(@class,'plp-block')]");
				Common.mouseOverClick("xpath", "//div[contains(@class,'plp-block')]");
				String visible = Common.findElement("xpath", "//div[contains(@class,'pagebuilder-options ')]")
						.getAttribute("class");
				System.out.println(visible);
				int action = Common.findElements("xpath", "//li[@class='pagebuilder-options-link']/a").size();

				for (int i = 1; i < action; i++) {
					String Action = Common.findElement("xpath", "(//li[@class='pagebuilder-options-link']/a)[" + i + "]")
							.getAttribute("title");
					System.out.println(Action);
					if (Action.contains("Move")) {
						Common.mouseOver("xpath", "//i[@class='icon-admin-pagebuilder-handle']");

						Common.assertionCheckwithReport(Action.contains("Move"),
								"To validate the elements in the PLP block",
								"Should validate the Elements in the Page builder options",
								"Page builder options are present", "Failed to validate the page builder element");
					} else if (Action.contains("Edit")) {
						Common.mouseOver("xpath", "//i[@class='icon-admin-pagebuilder-systems']");
						Common.assertionCheckwithReport(Action.contains("Edit"),
								"To validate the elements in the PLP block",
								"Should validate the Elements in the Page builder options",
								"Page builder options are present", "Failed to validate the page builder element");
					} else if (Action.contains("Duplicate")) {
						Common.mouseOver("xpath", "//i[@class='icon-pagebuilder-copy']");
						Common.assertionCheckwithReport(Action.contains("Duplicate"),
								"To validate the elements in the PLP block",
								"Should validate the Elements in the Page builder options",
								"Page builder options are present", "Failed to validate the page builder element");
					} else if (Action.contains("Remove")) {
						Common.mouseOver("xpath", "//i[@class='icon-admin-pagebuilder-remove']");
						Common.assertionCheckwithReport(Action.contains("Remove"),
								"To validate the elements in the PLP block",
								"Should validate the Elements in the Page builder options",
								"Page builder options are present", "Failed to validate the page builder element");
					}

				}
				Common.isElementDisplayedonPage(30, "xpath", "//span[@class='a-btn__label']");

			} catch (Exception e) {

				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("Validating PLP CMS Block Dragndrop operation",
						"User should able Dragndrop PLP CMS Block", "Sucessfully Dragndrop PLP CMS Block",
						Common.getscreenShotPathforReport("Failed to Dragndrop PLP CMS Block"));
				Assert.fail();

			}
		}
		public void edit_block_content(String dataSet) {
			String Editpagetitle = data.get(dataSet).get("Editpagetitle");
			String component = data.get(dataSet).get("component");

			try {
				Sync.waitElementPresent("xpath", "//div[contains(@class,'" + component + "')]");
				Common.mouseOverClick("xpath", "//div[contains(@class,'" + component + "')]");
				String visible = Common.findElement("xpath", "//div[contains(@class,'pagebuilder-options ')]")
						.getAttribute("class");
				System.out.println(visible);
				Common.assertionCheckwithReport(visible.contains("options-visible"),
						"validation Edit option  in the page builder ",
						"after mouse over on the page builder edit option should be appear ",
						"Successfully edit option appeared in the pagebuilder",
						"Failed to appear edit option when we mouse over");

				Common.clickElement("xpath", "//i[@class='icon-admin-pagebuilder-systems']");
				Sync.waitElementPresent("xpath", "//h1[@class='modal-title']");

				String editPLPBlock = Common.findElement("xpath", "//h1[@class='modal-title']").getText();
				System.out.println(editPLPBlock);
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
				Common.assertionCheckwithReport(editPLPBlock.contains(Editpagetitle),
						"validation Navigation to the edit PLP page ",
						"after Click on edit button it should be navigate to the edit PLP  page ",
						"Successfully it is navigated to edit PLP page ", "Failed to navigate to edit PLP page");

			} catch (Exception | Error e) {
				e.printStackTrace();

				report.addFailedLog("validation Navigation to the edit PLP page ",
						"after Click on edit button it should be navigate to the edit PLP page ",
						"Successfully it is navigated to edit PLP page ",
						Common.getscreenShotPathforReport("Failed to navigate to edit PLP page"));
				Assert.fail();

			}
		}

		public void close_Editblock(String dataSet) {
			try {
				edit_block_content(dataSet);
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
				Sync.waitElementClickable("xpath", "//button[@id='close']");
				Common.javascriptclickElement("xpath", "//button[@id='close']");
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
				edit_block_content(dataSet);
				int close = Common.findElements("xpath", "//button[@class='action-close']").size();
				for (int i = 1; i < close; i++) {
					if (Common.isElementDisplayedonPage(30, "xpath", "(//button[@class='action-close'])[" + i + "]")) {
						Common.mouseOverClick("xpath", "(//button[@class='action-close'])[" + i + "]");
						break;
					}

				}
			} catch (Exception | Error e) {
				e.printStackTrace();

				report.addFailedLog("To validate the PLP Block edit and close functionality",
						"Admin should edit and close the PLP CMS Block", "Admin failed to Edit and CLose the PLP CMS Block",
						Common.getscreenShotPathforReport("Failed to edit and close the PLP CMS Blockpage"));
				Assert.fail();

			}
		}
		public void ClosADD() throws Exception {
			Thread.sleep(3000);
			int sizesframe = Common.findElements("xpath", "//div[@class='preloaded_lightbox']/iframe").size();
			if (sizesframe > 0) {
				Common.actionsKeyPress(Keys.PAGE_UP);

				// Common.switchFrames("xpath", "//div[@class='preloaded_lightbox']/iframe");
				Sync.waitElementVisible("xpath", "//div[@class='sidebar-iframe-close']");
				Common.clickElement("xpath", "//div[@class='sidebar-iframe-close']");
			} else {
				int sizeofpopup = Common.findElements("id", "wpx-newsletter-popup").size();
				if (sizeofpopup > 0) {

					Sync.waitElementClickable("xpath", "//button[@aria-label='close']");
					Common.clickElement("xpath", "//button[@aria-label='close']");
				}
			}
		}
		
		public WebElement PLPCMS_content() {

			WebElement element = Common.findElement("xpath", "//span[text()='PLP Block']");
			Common.clickElement("xpath", "//span[text()='PLP Block']");

			return element;

		}

	
public void AcceptAll() {

	try {

		Thread.sleep(5000);
		if (Common.findElement("xpath", "//button[@id='truste-consent-button']") != null) {

			Common.clickElement("xpath", "//button[@id='truste-consent-button']");
		}
	} catch (Exception e) {

		e.printStackTrace();
		Assert.fail();

	}
}

public void UpdatePLPCMScontent(String dataSet) {
	try {
		Configure_textcolor_dark();
		Common.dropdown("name", "heading_type", SelectBy.TEXT, data.get(dataSet).get("headingtype"));
		Common.textBoxInput("xpath", "(//input[@name='title'])[2]", data.get(dataSet).get("titleaatribute"));
		Common.textBoxInput("name", "description", data.get(dataSet).get("Description"));
		Common.scrollIntoView("name", "link_text");
		Common.textBoxInput("name", "link_text", data.get(dataSet).get("CTAText"));
		Common.dropdown("name", "button_type", SelectBy.TEXT, data.get(dataSet).get("Buttontype"));
		

		CTALink(dataSet);
		Common.javascriptclickElement("xpath", "//label[text()='Open in new tab']");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog(
				"To validate the admin is able to populate all the fields in the Edit PLP CMS Block page",
				"User should able to populate all the fields in the PLP CMS Block page",
				"Unable to populate the data in the Edit CMS Block page",
				Common.getscreenShotPathforReport("Failed to populate the data in the Edit PLP CMS Block page"));
		Assert.fail();
	}

}
public void Configure_textcolor_dark() {

	try {

		int textcolor = Common.findElements("xpath", "//li[@class='active']//span[text()='Dark']").size();
		if (textcolor > 0) {
			System.out.println("Dark color is selected");
		} else {
			Sync.waitElementClickable("xpath", "//span[text()='Dark']");
			Common.mouseOverClick("xpath", "//span[text()='Dark']");
		}

		Common.assertionCheckwithReport(textcolor > 0, "To validate the text colour selected is Dark",
				" should select the text colour Dark", "Sucessfully select the Dark text color",
				"Failed to select the text colour dark");
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To validate the text colour selected is Dark",
				" should select the text colour Dark", "Unable select the Dark text color",
				Common.getscreenShotPathforReport("Failed to select the text colour dark"));
		e.printStackTrace();
		Assert.fail();

	}
}
public void Configure_Video(String DataSet) {
	try {
		String expndvideoelement = Common.findElement("xpath", "//div[@data-index='video_element']")
				.getAttribute("class");

		if (expndvideoelement.contains("show")) {
			System.out.println("The Video element section is expanded");

		} else {
			Common.javascriptclickElement("xpath", "//div[@data-index='video_element']//span[text()='Video']");

		}
		Sync.waitElementClickable("name", "video_element[video_url]");
		Common.textBoxInput("name", "video_element[video_url]", data.get(DataSet).get("VideoURL"));
		String videocontols = Common.findElement("name", "video_element[show_controls]").getAttribute("value");

		if (videocontols.contains("false")) {
			Common.clickElement("name", "video_element[show_controls]");
		} else {
			System.out.println("The video controls are on");
		}
		String videocontolson = Common.findElement("name", "video_element[show_controls]").getAttribute("value");

		Common.assertionCheckwithReport(videocontolson.contains("true"), "To turn on the video controls",
				"video controls should be opn", "Video controls are on", "failed to turn on the Video controls");

	} catch (Exception | Error e) {
		e.printStackTrace();

		report.addFailedLog("To turn on the video controls", "video controls should be on",
				"Video control trun on unsuccessfull",
				Common.getscreenShotPathforReport("Failed to turn on the video controls"));
		Assert.fail();

	}

}
public void Navigate_adminpage() {
	Common.switchToFirstTab();
	Contentpage();
}

public void CMS_ImageElement(String DataSet) {
	// TODO Auto-generated method stub
	String image = data.get(DataSet).get("image");
	String image2 = data.get(DataSet).get("image2");
	String altatt = data.get(DataSet).get("alterantivetext");
	try {
		Common.scrollIntoView("xpath", "//span[text()='Image Element']");

		Sync.waitElementPresent("xpath", "(//label[text()='Select from Gallery'])[1]");
		Common.javascriptclickElement("xpath", "(//label[text()='Select from Gallery'])[1]");

		// Sync.waitElementInvisible("xpath", "//div[@class='loading-mask' and
		// @style='display: none;']");
		// Common.isElementVisibleOnPage(30, "xpath", "//div[@id='contents']");
		Common.scrollIntoView("xpath", "//small[text()='" + image + "']");
		Common.javascriptclickElement("xpath", "//small[text()='" + image + "']");
		Sync.waitElementPresent("xpath", "//span[text()='Add Selected']");
		Common.javascriptclickElement("xpath", "//span[text()='Add Selected']");
		Common.scrollIntoView("xpath", "(//a/img[@class='preview-image'])[1]");
		String imagename = Common.findElement("xpath", "(//a/img[@class='preview-image'])[1]").getAttribute("alt");

		Common.assertionCheckwithReport(imagename.equals(image),
				"validation the image uploading on content for Fallback image ",
				"Image should be upload for background image", "Successfully image uploaded in background image ",
				"Failed to upload image on the background image");

		Sync.waitElementPresent("xpath", "(//label[text()='Select from Gallery'])[2]");
		Common.javascriptclickElement("xpath", "(//label[text()='Select from Gallery'])[2]");
		Common.scrollIntoView("xpath", "//small[text()='" + image2 + "']");
		Common.javascriptclickElement("xpath", "//small[text()='" + image2 + "']");
		Sync.waitElementPresent("xpath", "//span[text()='Add Selected']");
		Common.javascriptclickElement("xpath", "//span[text()='Add Selected']");

		Common.scrollIntoView("xpath", "(//a/img[@class='preview-image'])[2]");
		String imagename2 = Common.findElement("xpath", "(//a/img[@class='preview-image'])[2]").getAttribute("alt");

		Common.assertionCheckwithReport(imagename2.equals(image2),
				"validation the image uploading on content for Fallback image ",
				"Image should be upload for background image", "Successfully image uploaded in background image ",
				"Failed to upload image on the background image");

		Sync.waitElementPresent("xpath", "//input[@name='alt']");
		Common.findElement("xpath", "//input[@name='alt']").sendKeys(altatt);

		Thread.sleep(5000);
		System.out.println(altatt);

		String expndvideoelement = Common.findElement("xpath", "//div[@data-index='video_element']")
				.getAttribute("class");

		if (expndvideoelement.contains("show")) {
			System.out.println("The Video element section is expanded");

		} else {
			Common.javascriptclickElement("xpath", "//div[@data-index='video_element']//span[text()='Video']");

		}
		Sync.waitElementClickable("name", "video_element[video_url]");
		Common.textBoxInputClear("name", "video_element[video_url]");

	} catch (Exception | Error e) {
		e.printStackTrace();

		report.addFailedLog("validation Image upload  ", "Image should be uploaded",
				"Successfully image is uploaded",
				Common.getscreenShotPathforReport("Failed to navigate to edit promoBlocker page"));
		Assert.fail();

	}
}



public void CTALink(String dataSet) throws Exception {
	String buttonlinknavugation = data.get(dataSet).get("ButtonLinknavigation");
	try {
		Common.scrollIntoView("xpath", "//select[@name='link_url']");
		Common.dropdown("xpath", "//select[@name='link_url']", SelectBy.TEXT, buttonlinknavugation);
		if (buttonlinknavugation.contains("URL")) {
			String URL = data.get(dataSet).get("ButtonlinkURL");
			Common.textBoxInput("name", "button_link[default]", URL);

		} else if (buttonlinknavugation.contains("Product")) {
			String Product = data.get(dataSet).get("Buttonlinkproduct").toUpperCase().replace(" -", "");
			
			
			Sync.waitElementVisible("xpath", "//div[@data-role='selected-option']");
			Common.clickElement("xpath", "//div[@data-role='selected-option']");
			Common.textBoxInput("xpath", "//input[@placeholder='Product Name or SKU']", Product);
			Sync.waitElementVisible("xpath", "//span[contains(text(),'" + Product + "')]");
			Common.clickElement("xpath", "//span[contains(text(),'" + Product + "')]");
			String configuredproduct = Common.findElement("xpath", "//div[contains(@class,'multiselect-text')]")
					.getText();

			Common.assertionCheckwithReport(configuredproduct.contains(configuredproduct),
					"To validate the product selected is " + configuredproduct,
					configuredproduct + " should be selected", configuredproduct + "Product is selected",
					"Failed to add the " + configuredproduct);

		} else if (buttonlinknavugation.contains("category")) {
			String category = data.get(dataSet).get("Buttonlinkcategory");
			Common.clickElement("xpath", "//div[@class='admin__action-multiselect-text']");
			Common.textBoxInput("xpath", "//div[@class='admin__action-multiselect-search-wrap']", category);
			Sync.waitElementVisible("xpath", "//span[text()='" + category + "']");
			Common.clickElement("xpath", "//span[text()='" + category + "']");
			String configuredcategory = Common.findElement("xpath", "//div[contains(@class,'multiselect-text')]")
					.getText();

			Common.assertionCheckwithReport(category.contains(configuredcategory),
					"To validate the category selected is " + category, category + "category should be selected",
					configuredcategory + "category is selected", "Failed to add the " + configuredcategory);

		} else if (buttonlinknavugation.contains("Page")) {
			String Page = data.get(dataSet).get("Buttonlinkpage");
			Sync.waitElementClickable("xpath", "(//div[@class='admin__action-multiselect-text'])[1]");

			Common.mouseOverClick("xpath", "(//div[@class='admin__action-multiselect-text'])[1]");
			Sync.waitElementVisible("xpath", "(//div[@class='action-menu _active'])");

			Common.scrollIntoView("xpath", "//span[contains(text(),'" + Page + "')]");
			Common.clickElement("xpath", "//span[contains(text(),'" + Page + "')]");
			String configuredpage = Common.findElement("xpath", "//div[contains(@class,'multiselect-text')]")
					.getText();

			Common.assertionCheckwithReport(configuredpage.contains(Page),
					"To validate the page selected is " + configuredpage,
					configuredpage + " page should be selected", configuredpage + "page is selected",
					"Failed to add the page " + configuredpage);

		}
	} catch (Exception e) {
		e.printStackTrace();
		Assert.fail();
	}
}
public void dragndrop_Category_ProductSlider() {
	// TODO Auto-generated method stub
	try {
		Common.scrollIntoView("xpath", "//span[text()='Category/Product Slider']");
		WebElement element = Common.findElement("xpath", "//span[text()='Category/Product Slider']");
		draganddropContentBlock(element);
		String blocknames = Common.findElement("xpath", "//div[@class='pagebuilder-content-type pagebuilder-hot-category-product-slider']")
				.getAttribute("data-content-type");
		Common.assertionCheckwithReport(blocknames.equals("hot_entity_slider"),
				"Validating Promocontent Dragndrop operation", "promocontent dragndrop to content with options",
				"successfully dragndrop the promocontent with options ", "fail to dragndrop the promobaner");
	} catch (Exception e) {

		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("Validating Promocontent Dragndrop operation",
				"User should able Dragndrop Promoblocker", "Unable to Dragndrop the Promocontent",
				Common.getscreenShotPathforReport("Failed to Dragndrop Promocontent"));

		Assert.fail();

	}
}

public void editCategory_Productslider() {
	// TODO Auto-generated method stub
	
	try {
		Thread.sleep(2000);
		String id = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']")
				.getAttribute("id");

		Common.mouseOverClick("xpath", "//div[@id='" + id + "']");
		Common.clickElement("xpath", "//i[@class='icon-admin-pagebuilder-systems']");

		String editpromo = Common.findElement("xpath", "(//h1[@class='modal-title'])[1]").getText();

		Common.assertionCheckwithReport(editpromo.contains("Edit Category/Product Slider"),
				"validating the Navigation to the edit  Promo Media Card page ",
				"after Click on edit button it should be navigate to the edit promoBlocker page ",
				"Successfully it is navigated to edit promoBlocker page ",
				"Failed to navigate to edit promoBlocker page");

	} catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validation Navigation to the edit promo Blocker ",
				"after Click on edit button it should be navigate to the edit Blocker page ",
				"Unable to navigate  to the edit Blocker page ",
				Common.getscreenShotPathforReport("Failed to navigate to the edit Blocker page"));
		Assert.fail();

	}
}

public void category_productslider(String DataSet) {
try {
	

// TODO Auto-generated method stub
Common.clickElement("xpath", "//div[@class='admin__action-multiselect-text']");
Thread.sleep(1000);
Common.clickElement("xpath", "//label[text()='Bottles & Drinkware']");

}
catch (Exception | Error e) {
	e.printStackTrace();

	report.addFailedLog("validation Image upload in the forntend website ",
			"Image should de appear on fornt end page", "Successfully image is appeared on the frondend",
			Common.getscreenShotPathforReport("Failed to navigate to edit promoBlocker page"));
	Assert.fail();

}

}

public void editcategorypage(String Dataset) {
	// TODO Auto-generated method stub
	try {
		
 	
			Common.dropdown("xpath", "//select[@name='heading_type']", Common.SelectBy.TEXT,
					data.get(Dataset).get("headingtype"));
			
			String id = Common.findElement("xpath", "(//input[@name='title'])[2]")
					.getAttribute("id");
			Common.textBoxInput("xpath", "//input[@id='" + id + "']",data.get(Dataset).get("title"));				
			Common.textBoxInput("xpath", "//input[@name='subtitle']",data.get(Dataset).get("subtitle"));
			Thread.sleep(2000);
			Common.textBoxInput("xpath", "//input[@name='link_url[default]']", data.get(Dataset).get("CTAurl"));				
			Common.clickElement("xpath", "//div[@class='admin__field admin__field-option url-input-setting']");		
			Common.textBoxInput("xpath", "//input[@name='button_text']", data.get(Dataset).get("Buttontext"));
					
			int saved = Common.findElements("xpath", "(//div[@class='admin__fieldset-wrapper-content _hide'])[2]").size();
			System.out.println(saved);
			Common.assertionCheckwithReport(saved>0,
					"Enter the categroy details ", "Category details enterd succesfully",
					"Successfully v", "Failed to enterd the category details");
			Common.clickElement("xpath", "//button[@id='save']");
		
		} catch (Exception e) {
	e.printStackTrace();
	ExtenantReportUtils.addFailedLog(" To Validate the User needs to save the page",
			"User should able to save the page", "Unable to saves the page",
			Common.getscreenShotPathforReport("Failed to save the page"));
	Assert.fail();
	}}

public void website_verification_categroeyslider() {
	// TODO Auto-generated method stub
	try {

		Sync.waitElementPresent(40, "xpath", "//a[@class='c-product-carousel-transparent__cta pagebuilder-button-primary btn-visible']");
		Common.clickElement("xpath", "//a[@class='c-product-carousel-transparent__cta pagebuilder-button-primary btn-visible']");
		Thread.sleep(2000);
		//Common.switchWindows();
		Sync.waitPageLoad();
		Common.refreshpage();
		int logo = Common.findElements("xpath", "//div[@class='c-header__logo']").size();
		System.out.println();
		Common.assertionCheckwithReport(logo>0,
				"Validation of Homepage ", "Homepage should appear on the front end",
				"Successfully homepage is appeared on the frontend", "Failed to navigate to homepage");
	} catch (Exception | Error e) {
		e.printStackTrace();

		report.addFailedLog("validation of homepage in the forntend website ",
				"Homepage should be appear on fornt end page", "Successfully homepage is appeared on the frontend",
				Common.getscreenShotPathforReport("Failed to navigate to homepage page"));
		Assert.fail();

	}

}

public void click_product(String Dataset) {
	// TODO Auto-generated method stub
	try {
		Common.switchToFirstTab();	
		Contentpage();
        hot_elements(); 
        editCategory_Productslider();
	
        Common.dropdown("xpath", "//select[@class='admin__control-select url-input-select']", Common.SelectBy.TEXT,
				data.get(Dataset).get("heading"));
	       Common.clickElement("xpath", "//div[@class='admin__action-multiselect-wrap action-select-wrap']");
		
		String text= Common.findElement("xpath", "(//input[@class='admin__control-text admin__action-multiselect-search'])[2]").getAttribute("id");
		Common.textBoxInput("xpath", "//input[@id='"+ text +"']", data.get(Dataset).get("productnames"));
       Sync.waitElementPresent("xpath", "//span[text()='32 OZ WIDE MOUTH STAINLESS']");
        Common.mouseOverClick("xpath", "//span[text()='32 OZ WIDE MOUTH STAINLESS']");
	
	Common.textBoxInput("xpath", "//input[@name='button_text']", data.get(Dataset).get("Buttontext"));
	int saved = Common.findElements("xpath", "(//div[@class='admin__fieldset-wrapper-content _hide'])[2]").size();
	System.out.println(saved);
	Common.assertionCheckwithReport(saved>0,
			"Enter the categroy details ", "Category details enterd succesfully",
			"Successfully v", "Failed to enterd the category details");
	Common.clickElement("xpath", "//button[@id='save']");

} catch (Exception e) {
e.printStackTrace();
ExtenantReportUtils.addFailedLog(" To Validate the User needs to save the page",
	"User should able to save the page", "Unable to saves the page",
	Common.getscreenShotPathforReport("Failed to save the page"));
Assert.fail();
}}
public void Websiteverification_product() {
// TODO Auto-generated method stub
try {
    //Common.refreshpage();
    Sync.waitElementPresent(40, "xpath", "//a[@class='c-product-carousel-transparent__cta pagebuilder-button-primary btn-visible']");
	Common.clickElement("xpath", "//a[@class='c-product-carousel-transparent__cta pagebuilder-button-primary btn-visible']");
	Common.switchWindows();
	Thread.sleep(2000);
	String headingverification = Common.getText("xpath", "//div[@class='c-product-overview u-container']");
	Thread.sleep(2000);
	System.out.println(headingverification);
	Common.assertionCheckwithReport(headingverification.contains("32 OZ WIDE MOUTH STAINLESS"),
			"validation of PDP page in the forntend website ", "PDP should be appear on fornt end page",
			"Successfully PDP is appeared on the frondend", "Failed to navigate to PDP page");
} catch (Exception | Error e) {
	e.printStackTrace();

	report.addFailedLog("validation of PDP page in the forntend website ",
			"PDP should de appear on fornt end page", "Successfully image is appeared on the frondend",
			Common.getscreenShotPathforReport("Failed to navigate to PDP page"));
	Assert.fail();
}


}
public void click_categoty(String Dataset) {
// TODO Auto-generated method stub
try {
	Common.switchToFirstTab();	
	Contentpage();
    hot_elements(); 
    editCategory_Productslider();
    String id = Common.findElement("xpath", "(//input[@name='title'])[2]")
			.getAttribute("id");
	Common.textBoxInput("xpath", "//input[@id='" + id + "']",data.get(Dataset).get("title"));

    Common.dropdown("xpath", "//select[@class='admin__control-select url-input-select']", Common.SelectBy.TEXT,
			data.get(Dataset).get("CategorySelect"));
	Common.clickElement("xpath", "(//div[@class='action-menu-item'])[1]");	
Common.textBoxInput("xpath", "//input[@name='button_text']", data.get(Dataset).get("Buttontext"));
int saved = Common.findElements("xpath", "(//div[@class='admin__fieldset-wrapper-content _hide'])[2]").size();
System.out.println(saved);
Common.assertionCheckwithReport(saved>0,
		"Enter the categroy details ", "Category details enterd succesfully",
		"Successfully v", "Failed to enterd the category details");
Common.clickElement("xpath", "//button[@id='save']");
	
} catch (Exception e) {
e.printStackTrace();
ExtenantReportUtils.addFailedLog(" To Validate the User needs to save the page",
	"User should able to save the page", "Unable to saves the page",
	Common.getscreenShotPathforReport("Failed to save the page"));
Assert.fail();
}}

public void Websiteverification_category() {
// TODO Auto-generated method stub
try {
    //Common.refreshpage();
    Sync.waitElementPresent(40, "xpath", "//a[@class='c-product-carousel-transparent__cta pagebuilder-button-primary btn-visible']");
	Common.clickElement("xpath", "//a[@class='c-product-carousel-transparent__cta pagebuilder-button-primary btn-visible']");
	Common.switchWindows();
	Thread.sleep(2000);
	Common.scrollIntoView("xpath", "//p[@class='m-breadcrumb__text']");
	Thread.sleep(1000);
	String shop = Common.getText("xpath", "//p[@class='m-breadcrumb__text']");
	Thread.sleep(2000);
	System.out.println(shop);
	Common.assertionCheckwithReport(shop.contains("Shop"),
			"validation shop categroy in the forntend website ", "Shop category should be appear on fornt end page",
			"Successfully Shop category is appeared on the frondend", "Failed to navigate to Shop category page");
} catch (Exception | Error e) {
	e.printStackTrace();

	report.addFailedLog("validation Shop category in the forntend website ",
			"Shop category should be appear on fornt end page", "Successfully Shop category is appeared on the frondend",
			Common.getscreenShotPathforReport("Failed to navigate to Shop category page"));
	Assert.fail();
}	
}

public void Click_Page(String Dataset) {
// TODO Auto-generated method stub
try {
	Common.switchToFirstTab();	
	Contentpage();
    hot_elements(); 
    editCategory_Productslider();
    String id = Common.findElement("xpath", "(//input[@name='title'])[2]").getAttribute("id");
	Common.textBoxInput("xpath", "//input[@id='" + id + "']",data.get(Dataset).get("title"));	        
    Common.dropdown("xpath", "//select[@class='admin__control-select url-input-select']", Common.SelectBy.TEXT,
			data.get(Dataset).get("ButtonLinknavigation"));
	

	Common.clickElement("xpath", "//span[text()='503 Service Unavailable']");
	
Common.textBoxInput("xpath", "//input[@name='button_text']", data.get(Dataset).get("Buttontext"));
int saved = Common.findElements("xpath", "(//div[@class='admin__fieldset-wrapper-content _hide'])[2]").size();
System.out.println(saved);
Common.assertionCheckwithReport(saved>0,
		"Enter the categroy details ", "Category details enterd succesfully",
		"Successfully v", "Failed to enterd the category details");
Common.clickElement("xpath", "//button[@id='save']");

	
} catch (Exception e) {
e.printStackTrace();
ExtenantReportUtils.addFailedLog(" To Validate the User needs to save the page",
	"User should able to save the page", "Unable to saves the page",
	Common.getscreenShotPathforReport("Failed to save the page"));
Assert.fail();
}}

public void Websiteverification_page() {
// TODO Auto-generated method stub

try {
    //Common.refreshpage();
    Sync.waitElementPresent(40, "xpath", "//a[@class='c-product-carousel-transparent__cta pagebuilder-button-primary btn-visible']");
	Common.clickElement("xpath", "//a[@class='c-product-carousel-transparent__cta pagebuilder-button-primary btn-visible']");
	Common.switchWindows();
	Thread.sleep(2000);
	String page = Common.getText("xpath", "//p[@class='m-breadcrumb__text']");
	System.out.println(page);
	Common.assertionCheckwithReport(page.contains("503 Service Unavailable"),
			"validation 503 Service Unavailable page in the forntend website ", "503 Service Unavailable page should be appear on fornt end page",
			"Successfully 503 Service Unavailable page is appeared on the frondend", "Failed to navigate to 503 Service Unavailable page");
} catch (Exception | Error e) {
	e.printStackTrace();

	report.addFailedLog("validation 503 Service Unavailable page in the forntend website ",
			"503 Service Unavailable page should be appear on fornt end page", "Successfully 503 Service Unavailable page is appeared on the frondend",
			Common.getscreenShotPathforReport("Failed to navigate to 503 Service Unavailable page"));
	Assert.fail();
}	
}

public void produts() {
// TODO Auto-generated method stub
Common.switchToFirstTab();				
Contentpage();
hot_elements(); 
editCategory_Productslider();

}

public void cick_products() {
// TODO Auto-generated method stub
try {
	Common.clickElement("xpath", "//li[@class='admin__field-visual-select-small']");
	Common.clickElement("xpath", "//div[@class='admin__action-multiselect-text']");
	
	Sync.waitElementPresent("xpath", "(//label[text()='Shop'])[1]");
	Common.clickElement("xpath", "(//label[text()='Shop'])[1]");
	
String category = Common.findElement("xpath", "//li[@name='category_ids']").getText();
System.out.println(category);			
String sku = Common.findElement("xpath", "//li[@name='sku']").getText();
System.out.println(sku);
String condition = Common.findElement("xpath", "//li[@name='condition']").getText();
System.out.println(condition);

int page = Common.findElements("xpath", "(//fieldset[@class='admin__fieldset'])[4]").size();
System.out.println(page);
Common.assertionCheckwithReport(page>0,
	" To Validate the User needs to save the page", "User should able to save the page",
	"Sucessfully User saves the page", "Failed to save the page");


} catch (Exception | Error e) {
	e.printStackTrace();
	report.addFailedLog("Validating the User need to delete the page", "User should able to delete the page",
			"Sucessfully User delete the page", Common.getscreenShotPathforReport("Failed to delete the page"));
	Assert.fail();

}
}
public void Savecategorypage(String Dataset) {
// TODO Auto-generated method stub

try {
	
	//Common.clickElement("xpath", "//h2[@class='m-heading__text placeholder-text']");
String id =	Common.findElement("xpath", "(//input[@class='admin__control-text'])[6]").getAttribute("id");
	
Common.textBoxInput("xpath", "//input[@id='"+id+"']", data.get(Dataset).get("title"));


		String att =	Common.findElement("xpath", "(//input[@class='admin__control-text'])[7]").getAttribute("id");
		
		Common.textBoxInput("xpath", "//input[@id='"+att+"']", data.get(Dataset).get("SubTitle"));
       	
       	
       	int page = Common.findElements("xpath", "(//fieldset[@class='admin__fieldset'])[5]").size();
		System.out.println(page);
		Common.assertionCheckwithReport(page>0,
				" To Validate the User needs to save the page", "User should able to save the page",
				"Sucessfully User saves the page", "Failed to save the page");
		
		Common.clickElement("xpath", "(//span[text()='Save'])[2]");

} catch (Exception | Error e) {
	e.printStackTrace();
	ExtenantReportUtils.addFailedLog(" To Validate the User needs to save the page",
			"User should able to save the page", "Unable to saves the page",
			Common.getscreenShotPathforReport("Failed to save the page"));
	Assert.fail();
}

}
public void website_category_verification() {
// TODO Auto-generated method stub

try {

    Common.refreshpage();
	Sync.waitElementPresent("xpath", "//div[@class='u-container c-product-carousel-transparent__carousel']");
	Thread.sleep(2000);
	String headingverification = Common.getText("xpath", "//div[@class='u-container c-product-carousel-transparent__carousel']");
	System.out.println(headingverification);
	Common.assertionCheckwithReport(headingverification.contains("HYDROFLASK QA TESTING"),
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

public void Click_SKU(String Dataset) {
// TODO Auto-generated method stub

try {
	Common.switchToFirstTab();				
	Contentpage();
    hot_elements(); 
    editCategory_Productslider();
	
	Common.clickElement("xpath", "//span[text()='SKU']");
	String id= Common.findElement("xpath", "(//input[@class='admin__control-text'])[4]").getAttribute("id");
	
	Common.textBoxInput("xpath", "//input[@id='"+id+"']", data.get(Dataset).get("SKU"));
	Common.dropdown("xpath", "(//select[@class='admin__control-select'])[1]", Common.SelectBy.TEXT,
			data.get(Dataset).get("Sortby"));
	
	int page = Common.findElements("xpath", "(//fieldset[@class='admin__fieldset'])[4]").size();
	System.out.println(page);
	Common.assertionCheckwithReport(page>0,
			" To Validate the User needs to save the page", "User should able to save the page",
			"Sucessfully User saves the page", "Failed to save the page");
	Common.clickElement("xpath", "(//span[text()='Save'])[2]");
	
} catch (Exception e) {
e.printStackTrace();
ExtenantReportUtils.addFailedLog(" To Validate the User needs to save the page",
	"User should able to save the page", "Unable to saves the page",
	Common.getscreenShotPathforReport("Failed to save the page"));
Assert.fail();
}}

public void website_SKU_verification() {
// TODO Auto-generated method stub
try {

    Common.refreshpage();
	Sync.waitElementPresent("xpath", "//div[@class='u-container c-product-carousel-transparent__carousel']");
	Thread.sleep(2000);
	
	String headingverification = Common.getText("xpath", "//div[@class='u-container c-product-carousel-transparent__carousel']");
	
	System.out.println(headingverification);
	Common.assertionCheckwithReport(headingverification.contains("HYDROFLASK QA TESTING"),
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

public void click_condition(String Dataset) {
// TODO Auto-generated method stub

try {
	Common.switchToFirstTab();	
	Contentpage();
    hot_elements(); 
    editCategory_Productslider();
	
	Common.clickElement("xpath", "//span[text()='Condition']");	
	Common.clickElement("xpath", "//span[@class='rule-param rule-param-new-child']");
	Common.dropdown("xpath", "//select[@id='condition_source__1__new_child']", Common.SelectBy.TEXT,
			data.get(Dataset).get("ChooseCondition"));		
	Common.clickElement("xpath", "//a[text()='is']");
	Common.clickElement("xpath", "//select[@id='condition_source__1--1__operator']");
	Common.dropdown("xpath", "//select[@id='condition_source__1--1__operator']", Common.SelectBy.TEXT,
			data.get(Dataset).get("Price"));
	
	
	Common.clickElement("xpath", "//a[text()='...']");
Common.textBoxInput("xpath", "//input[@id='condition_source__1--1__value']",data.get(Dataset).get("mrgtop"));
int page = Common.findElements("xpath", "(//fieldset[@class='admin__fieldset'])[4]").size();
System.out.println(page);
Common.assertionCheckwithReport(page>0,
		" To Validate the User needs to save the page", "User should able to save the page",
		"Sucessfully User saves the page", "Failed to save the page");

	Common.clickElement("xpath", "(//span[text()='Save'])[2]");
	
} catch (Exception e) {
e.printStackTrace();
ExtenantReportUtils.addFailedLog(" To Validate the User needs to save the page",
	"User should able to save the page", "Unable to saves the page",
	Common.getscreenShotPathforReport("Failed to save the page"));
Assert.fail();
}}

public void website_condition_verification() {
// TODO Auto-generated method stub
try {

    Common.refreshpage();
	Sync.waitElementPresent("xpath", "//div[@class='u-container c-product-carousel-transparent__carousel']");
	Thread.sleep(2000);
	String headingverification = Common.getText("xpath", "//div[@class='u-container c-product-carousel-transparent__carousel']");
	System.out.println(headingverification);
	Common.assertionCheckwithReport(headingverification.contains("HYDROFLASK QA TESTING"),
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

public void savecategory_product(String Dataset) {
	// TODO Auto-generated method stub
try {
		
		//Common.clickElement("xpath", "//h2[@class='m-heading__text placeholder-text']");
	String id =	Common.findElement("xpath", "(//input[@class='admin__control-text'])[4]").getAttribute("id");			
  	Common.textBoxInput("xpath", "//input[@id='"+id+"']", data.get(Dataset).get("title"));		
  	String att =	Common.findElement("xpath", "(//input[@class='admin__control-text'])[5]").getAttribute("id");				
	Common.textBoxInput("xpath", "//input[@id='"+att+"']", data.get(Dataset).get("SubTitle"));
	
	int title = Common.findElements("xpath", "(//fieldset[@class='admin__fieldset'])[5]").size();
	System.out.println(title);
	Common.assertionCheckwithReport(title>0 ,
			" To Validate the Edit Category/Product Slider page", "User should able to navigate Edit Category/Product Slider page",
			"Sucessfully User lands on Edit Category/Product Slider", "Failed to navigate to Edit Category/Product Slider");
	
	Common.clickElement("xpath", "(//span[text()='Save'])[2]");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog(" To Validate the User needs to save the page",
				"User should able to save the page", "Unable to saves the page",
				Common.getscreenShotPathforReport("Failed to save the page"));
		Assert.fail();
	}

}





public void Click_Catalog() {
   
try {
    Sync.waitPageLoad();
    Thread.sleep(2000);
    Common.clickElement("id", "menu-magento-catalog-catalog");
    Thread.sleep(5000);

    String catalogmenu = Common.findElement("xpath", "//li[contains(@class,'active')]").getAttribute("class");
    System.out.println(catalogmenu);
    Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
    Common.assertionCheckwithReport(catalogmenu.contains("show"),
            "To Validate the catalog menu is displayed",
            "should display the catalog menu after clicking on the catalog",
            "catalog menu is displayed after a click on the catalog button",
            "Failed to display catalog menu");



} catch (Exception | Error e) {
    e.printStackTrace();
    ExtenantReportUtils.addFailedLog("To Validate the catalog menu is displayed",
            "should display the catalog menu after clicking on the catalog",
            "unable to display catalog field menu after a click on the catalog button",
            "Failed to display catalog field menu");
    Assert.fail();
}



}
public void Click_Products_Catalogmenu() {
	// TODO Auto-generated method stub
        try {
        Thread.sleep(2000);
            Common.clickElement("xpath", "//li[@class='item-catalog-products    level-2']");
            Sync.waitPageLoad();
            Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
            Common.assertionCheckwithReport(Common.getPageTitle().contains("Products / Inventory / Catalog / Magento Admin"),
                    "To Validate the Catalogmenu is displayed",
                    "should display the Catalogmenu after clicking on the customers",
                    "Catalog menu is displayed after a click on the Catalog button",
                    "Failed to display Catalogmenu");



       } catch (Exception | Error e) {
            e.printStackTrace();
            ExtenantReportUtils.addFailedLog("To Validate the Catalogmenu is displayed",
                    "should display the Catalogmenu after clicking on the Catalog",
                    "unable to display Catalog menu after a click on the Catalog button",
                    "Failed to display Catalog menu");
            Assert.fail();
        }


}
public void Search_products(String dataSet) {
	 // TODO Auto-generated method stub
	 try {
	 Sync.waitElementPresent(30, "xpath", "//input[@placeholder='Search by keyword']");
       Thread.sleep(3000);
       if (Common.isElementDisplayedonPage(30, "xpath", "//button[@class='action-remove']")) {
           Common.mouseOverClick("xpath", "//button[@class='action-remove']");
           Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
       } else {
           System.out.println("no Active filters found");
       }
       Common.textBoxInput("id", "fulltext", data.get(dataSet).get("Productname"));

//         Common.findElement("xpath", "//input[@placeholder='Search by keyword']").sendKeys(pagename);
       Common.actionsKeyPress(Keys.ENTER);
       Sync.waitElementVisible(30, "xpath", "//div[contains(@class,'filters-current')]");
       String active = Common.findElement("xpath", "//div[contains(@class,'filters-current')]")
               .getAttribute("class");
       Common.assertionCheckwithReport(active.contains("show"), "To validate the search filters",
               "Should apply Search filter", "Search filetr is applied", "failed to apply for search filter");
       Thread.sleep(5000);
       int pages = Common.findElements("xpath", "//tr[contains(@class,'data-row')]").size();
       System.out.println(pages);



      if (pages > 0) {
   	   
      }
	 }

      catch (Exception | Error e) {
           e.printStackTrace();
           ExtenantReportUtils.addFailedLog("To Validate the search filters",
                   "should display the Search filter",
                   "unable to display Search filte",
                   "Failed to display Search filte");
           Assert.fail();
       }

}
public void Click_SearchProduct() {
	 try {
           
           Thread.sleep(2000);
           Common.clickElement("xpath", "//tr[contains(@class,'data-row')]");
           Thread.sleep(5000);
      
          
       //    Sync.waitElementInvisible(30, "xpath", "//div[@class='page-title-wrapper complex']");
           Common.assertionCheckwithReport(Common.getPageTitle().contains("10 QATEST product / Products / Inventory / Catalog / Magento Admin"),
                   "To Validate the QATEST product is displayed",
                   "should display the QATEST product after clicking on the prouct",
                   "QATEST product is displayed after a click on the product",
                   "Failed to display QATEST product");



      } catch (Exception | Error e) {
           e.printStackTrace();
           ExtenantReportUtils.addFailedLog("To Validate the QATEST product is displayed",
                   "should display the QATEST product after clicking on the product",
                   "unable to display QATEST productafter a click on the product",
                   "Failed to display QATEST product");
           Assert.fail();
       }



  }
public void Update_Name_Price_Stock_Categories(String dataSet) {
    // TODO Auto-generated method stub
 String Updateproductname = data.get(dataSet).get("Updateproductname");
    try {
    Thread.sleep(2000);
    Common.textBoxInput("xpath", "//input[@name='product[name]']",Updateproductname);
    Common.textBoxInput("xpath", "//input[@name='product[price]']", data.get(dataSet).get("Price"));
  
    Common.dropdown("name", "product[quantity_and_stock_status][is_in_stock]", Common.SelectBy.TEXT,  data.get(dataSet).get("Stock Status"));
  //  Common.waitAndClick("xpath", "//button[@data-action='remove-selected-item']");
    Common.clickElement("xpath", "//fieldset[@data-index='container_category_ids']//div[contains(@class,'multiselect-wrap')]");
    Sync.waitElementVisible("xpath", "//div[contains(@class,'active')]//input[contains(@class,'search')]");
   // Common.actionsKeyPress(Keys.PAGE_DOWN);


   String[] categories = data.get(dataSet).get("Categorydisplay").split(",");



   for (int i = 0; i < categories.length; i++) {
        System.out.println(categories[i]);
        Common.textBoxInput("xpath", "//div[contains(@class,'active')]//input[contains(@class,'search')]",categories[i] );

      // Common.scrollIntoView("xpath", "//label[text()='" + categories[i] + "']");
        Sync.waitElementPresent("xpath", "//span[text()='" + categories[i] + "']");
        Common.javascriptclickElement("xpath", "//span[text()='" + categories[i] + "']");
        Common.clickElement("xpath", "//fieldset[@data-index='container_category_ids']//div[contains(@class,'multiselect-wrap')]");

        
       
   }
    Common.scrollIntoView("xpath", "//button[@id='save-button']");
    Common.clickElement("xpath", "//button[@id='save-button']");
    Sync.waitPageLoad();
    Sync.waitElementPresent("xpath", "//div[@class='message message-success success']");
    String Successmessage=Common.findElement("xpath", "//div[@class='message message-success success']").getText();
     System.out.println(Successmessage);


     Common.assertionCheckwithReport(Successmessage.contains("You saved the product.") && Common.getPageTitle().contains(Updateproductname),
             "To Validate the Successmessage is displayed",
             "should display the Successmessage after clicking on the save button",
             "Successmessage is displayed after a click on the save button",
             "Failed to display Successmessage");


    } catch (Exception | Error e) {
         e.printStackTrace();
         ExtenantReportUtils.addFailedLog("To Validate the Successmessage is displayed",
                 "should display the Successmessage after clicking on the save button",
                 "unable to display Successmessage after a click on the save button",
                 "Failed to display Successmessage");
         Assert.fail();
     }



}

public void open_website(String Dataset) {
    String pagetitle = data.get(Dataset).get("pageTitle");
    try {
        Sync.waitPageLoad(60);

Common.openNewTab();
        if (Common.getCurrentURL().contains("stage")) {
            Common.oppenURL(data.get(Dataset).get("URL"));
        } else {
            Common.oppenURL(data.get(Dataset).get("preprodURL"));
       }
        
        Sync.waitPageLoad(40);
        
        String uname = Common.getPageTitle();
        Common.assertionCheckwithReport(uname.contains("Home Page "), "Validating the User lands to the Hydroflask page",
                "User should able to land on the Hydroflask page", "Sucessfully User lands on the Hydroflask page",
                "Failed to navigate to the hydroflask page");
        
        ClosADD();
        AcceptAll();
        



   } catch (Exception | Error e) {
        e.printStackTrace();
        ExtenantReportUtils.addFailedLog("Validating the User lands to the Hydroflask page",
                "User should able to land on the Hydroflask page", "Unable to Navigate to the Hydroflask page",
                Common.getscreenShotPathforReport("Failed to navigate to the hydroflask page"));



       Assert.fail();
    }
}
public void bottles_headerlinks() {
	// TODO Auto-generated method stub
	
 try {
      //  Sync.waitPageLoad();
        Thread.sleep(2000);
        Sync.waitElementPresent("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");
        Common.clickElement("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");
        Thread.sleep(2000);
        Sync.waitElementPresent("xpath", "//span[text()=' Bottles & Drinkware']");
        Common.clickElement("xpath", "//span[text()=' Bottles & Drinkware']");
        Thread.sleep(2000);
        Sync.waitElementPresent("xpath", "//a[@class='ui-corner-all']//span[text()=' Bottles']");
        Common.clickElement("xpath", "//a[@class='ui-corner-all']//span[text()=' Bottles']");
        String pagetitle = Common.getPageTitle();
        System.out.println(pagetitle);
        
        Common.assertionCheckwithReport(pagetitle.contains("Shop Bottles"),
                "To Validate the plp page is displayed",
                "should display the plp page after clicking on the bottles",
                "plp page is displayed after a click on the bottles",
                "Failed to display plp page");



   } catch (Exception | Error e) {
        e.printStackTrace();
        ExtenantReportUtils.addFailedLog("To Validate the plp page is displayed",
                "should display the plp page after clicking on the bottles",
                "unable to display plp page after a click on the bottles",
                "Failed to display plp page");
        Assert.fail();
    }



}

public void click_sortby(String dataset) {
   
    try {
        Thread.sleep(2000);
        Common.clickElement("xpath", "//select[@class='ais-SortBy-select']");
    	Thread.sleep(3000);
        Common.clickElement("xpath", "//select[@class='ais-SortBy-select']//option[text()='Newest']");
        //Thread.sleep(6000);
     //   Common.textBoxInput("xpath", "//input[@id='search']",  data.get(dataset).get("Updateproductname"));
       // Sync.waitElementPresent("xpath", "//form[@id='search_mini_form']//span[text()='Search']");
     //   Common.clickElement("xpath", "//form[@id='search_mini_form']//span[text()='Search']");
      Sync.waitPageLoad();
       Thread.sleep(3000);
        Common.mouseOver("xpath", "//img[@class='m-product-card__image product-image-photo']");
        Thread.sleep(2000);
   
        String productlist = Common.getText("xpath", "//div[@class='m-product-card m-product-card--enhanced product-item product-item-info']");
        System.out.println(productlist);
       
        Common.assertionCheckwithReport(productlist.contains("10 QATEST"),
                "To Validate the update product is displayed",
                "should display the update product after clicking on the search",
                "update product is displayed after a click on the search",
                "Failed to display update product");



   } catch (Exception | Error e) {
        e.printStackTrace();
        ExtenantReportUtils.addFailedLog("To Validate the update product is displayed",
                "should display the update product after clicking on the search",
                "unable to display update product after a click on the search",
                "Failed to display update product");
        Assert.fail();
    }



}
public void openwebsiteoxo(String Dataset) {
    String pagetitle = data.get(Dataset).get("pageTitle");
    try {
        Sync.waitPageLoad(60);

Common.openNewTab();
        if (Common.getCurrentURL().contains("stage")) {
            Common.oppenURL(data.get(Dataset).get("URL"));
        } else {
            Common.oppenURL(data.get(Dataset).get("preprodURL"));
       }
        
        Sync.waitPageLoad(40);
        
        String uname = Common.getPageTitle();
        Common.assertionCheckwithReport(uname.contains("Home Page (OXO)"),
                "Validating the User lands to the OXO page",
                "User should able to land on the OXO page", "Sucessfully User lands on the OXO page",
                "Failed to navigate to the OXO page");

   } catch (Exception | Error e) {
        e.printStackTrace();
        ExtenantReportUtils.addFailedLog("Validating the User lands to the OXO page",
                "User should able to land on the OXO page", "Unable to Navigate to the OXO page",
                Common.getscreenShotPathforReport("Failed to navigate to the OXO page"));



       Assert.fail();
   }
}
public void POPContainers_headerlinks() {
	// TODO Auto-generated method stub
	
 try {
      //  Sync.waitPageLoad();
        Thread.sleep(2000);
        Sync.waitElementPresent("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");
        Common.clickElement("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");
        Thread.sleep(2000);
        Sync.waitElementPresent("xpath", "//span[text()=' Kitchenware']");
        Common.clickElement("xpath", "//span[text()=' Kitchenware']");
        Thread.sleep(2000);
        Sync.waitElementPresent("xpath", "//a[@class='ui-corner-all']//span[text()=' POP Containers']");
        Common.clickElement("xpath", "//a[@class='ui-corner-all']//span[text()=' POP Containers']");
        String pagetitle = Common.getPageTitle();
        System.out.println(pagetitle);
        
        Common.assertionCheckwithReport(pagetitle.contains("Shop POP Containers"),
                "To Validate the plp page is displayed",
                "should display the plp page after clicking on the POP Containers",
                "plp page is displayed after a click on the POP Containers",
                "Failed to display plp page");
   } catch (Exception | Error e) {
        e.printStackTrace();
        ExtenantReportUtils.addFailedLog("To Validate the plp page is displayed",
                "should display the plp page after clicking on the POP Containers",
                "unable to display plp page after a click on the POP Containers",
                "Failed to display plp page");
        Assert.fail();
    }



}
public void Backto_magento_admin() {
	 try {
		 
		 Common.switchToFirstTab();
	Thread.sleep(2000);

	 //String text=Common.getText("xpath", "//h1[@class='page-title']");
	String text=Common.getPageTitle();
	
       System.out.println(text);
      Thread.sleep(2000);

       Common.assertionCheckwithReport(text.contains("Catalog "),
               "To Validate the pagetitle is displayed",
               "should display the pagetitle after clicking on the switchtofirsttab",
               "pagetitle is displayed after a click on the switchtofirsttab",
               "Failed to display pagetitle");


      } catch (Exception | Error e) {
           e.printStackTrace();
           ExtenantReportUtils.addFailedLog("To Validate the pagetitle is displayed",
                   "should display the pagetitle after clicking on the switchtofirsttab",
                   "unable to display pagetitle after a click on the switchtofirsttab",
                   "Failed to display pagetitle");
           Assert.fail();
       }



  }

public void Actual_Name_Price_Stock_Categories(String dataSet) {
    // TODO Auto-generated method stub
 String Productname = data.get(dataSet).get("Productname");
    try {
    Thread.sleep(2000);
    Common.textBoxInput("xpath", "//input[@name='product[name]']",Productname);
    Common.textBoxInput("xpath", "//input[@name='product[price]']", data.get(dataSet).get("Updateprice"));
  
    Common.dropdown("name", "product[quantity_and_stock_status][is_in_stock]", Common.SelectBy.TEXT,  data.get(dataSet).get("updateStock Status"));
  //  Common.waitAndClick("xpath", "//button[@data-action='remove-selected-item']");
    Common.clickElement("xpath", "//fieldset[@data-index='container_category_ids']//div[contains(@class,'multiselect-wrap')]");
    Sync.waitElementVisible("xpath", "//div[contains(@class,'active')]//input[contains(@class,'search')]");
   // Common.actionsKeyPress(Keys.PAGE_DOWN);


   String[] categories = data.get(dataSet).get("Categorydisplay").split(",");



   for (int i = 0; i < categories.length; i++) {
        System.out.println(categories[i]);
        Common.textBoxInput("xpath", "//div[contains(@class,'active')]//input[contains(@class,'search')]",categories[i] );

      // Common.scrollIntoView("xpath", "//label[text()='" + categories[i] + "']");
        Sync.waitElementPresent("xpath", "//span[text()='" + categories[i] + "']");
        Common.javascriptclickElement("xpath", "//span[text()='" + categories[i] + "']");
        Common.clickElement("xpath", "//fieldset[@data-index='container_category_ids']//div[contains(@class,'multiselect-wrap')]");

        
       
   }
    Common.scrollIntoView("xpath", "//button[@id='save-button']");
    Common.clickElement("xpath", "//button[@id='save-button']");
    Sync.waitPageLoad();
    Sync.waitElementPresent("xpath", "//div[@class='message message-success success']");
   String Successmessage=Common.findElement("xpath", "//div[@class='message message-success success']").getText();
    System.out.println(Successmessage);


    Common.assertionCheckwithReport(Successmessage.contains("You saved the product.") && Common.getPageTitle().contains(Productname),
            "To Validate the Successmessage is displayed",
            "should display the Successmessage after clicking on the save button",
            "Successmessage is displayed after a click on the save button",
            "Failed to display Successmessage");


   } catch (Exception | Error e) {
        e.printStackTrace();
        ExtenantReportUtils.addFailedLog("To Validate the Successmessage is displayed",
                "should display the Successmessage after clicking on the save button",
                "unable to display Successmessage after a click on the save button",
                "Failed to display Successmessage");
        Assert.fail();
    }



}

    }





 
