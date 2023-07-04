package models.admin;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Common.SelectBy;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.Utils;
import groovyjarjarantlr4.v4.parse.ANTLRParser.sync_return;

public class OsperyAdminHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public OsperyAdminHelper(String datafile, String DataSet) {
		excelData = new ExcelReader(datafile, DataSet);
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("Admin");
			report.createTestcase("AdminTestCases");
		} else {
			this.report = Utilities.TestListener.report;
		}
	}
	
	
	public int getpageresponce(String url) throws MalformedURLException, IOException {
		HttpURLConnection c = (HttpURLConnection) new URL(url).openConnection();
		c.setRequestMethod("HEAD");
		c.connect();
		int r = c.getResponseCode();

		return r;
	}


	public void Admin_signin(String dataSet) {

		 

        try {
            if (Common.getCurrentURL().contains("staging")) {
                Sync.waitElementClickable("xpath", "//a[@class='action login primary']");
                Common.javascriptclickElement("xpath", "//a[@class='action login primary']");
            } 
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

	public void ProDeal_Application() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//span[text()='Application']");
			Common.clickElement("xpath", "//span[text()='Application']");
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Pro Deal Program / Pro Deal / Customers / Magento Admin"),
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
	
	public void Select_CustomerProdeal_Application(String Dataset) {
		// TODO Auto-generated method stub
		try {

			Sync.waitElementPresent("xpath", "//button[@data-action='grid-filter-expand']");
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			Common.textBoxInput("xpath", "(//input[@name='customer_id'])[1]", data.get(Dataset).get("CustomerID"));
			Common.textBoxInput("xpath", "(//input[@name='association_email'])[1]", data.get(Dataset).get("AssociationEmail"));
			Common.actionsKeyPress(Keys.ENTER);
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			String records = Common.findElement("xpath", "//div[@class='admin__control-support-text']").getText();
			if (records.equals("1 records found")) {
				Common.clickElement("xpath", "//a[text()='Edit']");
				
				Common.assertionCheckwithReport(Common.getPageTitle().equals("Edit ProDeal Program / Pro Deal / Customers / Magento Admin"),
						"Validating Edit Prodeal Program page navigation ",
						"after clicking Edit  it will navigate Edit Prodeal Program",
						"Successfully navigate to the  Edit Prodeal Program Page ",
						"Failed to navigate to the  Edit Prodeal Program Page");
				
				/*ExtenantReportUtils.addPassLog("Validating Edit Prodeal Program page navigation ",
						"after clicking Edit  it will navigate Edit Prodeal Program",
						"Able to  navigate to the Edit Prodeal Program Page",
						Common.getscreenShotPathforReport("Passed to navigate to Edit Prodeal Program page"));*/
			} else {
				Assert.fail();
			}
			

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Edit Prodeal Program page navigation ",
					"after clicking Edit it will navigate Edit Prodeal Program page",
					"Unable to  navigate to the Edit Prodeal Program",
					Common.getscreenShotPathforReport("Failed to navigate to Edit Prodeal Program"));
			Assert.fail();
		}

	}
	
	
	public void Reject_ProDeal_Application() {
		// TODO Auto-generated method stub
		try {
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("xpath", "//select[@name='status']");
			Common.dropdown("xpath", "//select[@name='status']", SelectBy.TEXT,"Rejected");
			
			ExtenantReportUtils.addPassLog("Validating Application Status",
					"Status will select Successfully",
					"Able to  select the application status ",
					Common.getscreenShotPathforReport("Passed to select the Application"));
				
				
			

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Application Status",
					"Status will select Successfully",
					"Unable to  select the application status ",
					Common.getscreenShotPathforReport("Failed to select the Application"));
			Assert.fail();
		}

	}
	
	public void Click_Save() {
		// TODO Auto-generated method stub
		try {
			
			Sync.waitElementPresent("xpath", "(//span[contains(text(),'Save')])[2]");
			Common.clickElement("xpath","(//span[contains(text(),'Save')])[2]");
			String message = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
			System.out.println(message);

			Common.assertionCheckwithReport(Common.getPageTitle().equals("Edit ProDeal Program / Pro Deal / Customers / Magento Admin"),
					"Validating  Prodeal Program Success message ",
					"after clicking save  it will navigate Edit Prodeal Program page with Success message",
					"Successfully navigate to the  Edit Prodeal Program Page with Success message",
					"Failed to navigate to the  Edit Prodeal Program Page with Success message");
				
				
			

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating  Prodeal Program Success message",
					"after clicking save  it will navigate Edit Prodeal Program page with Success message",
					"Unable to  navigate to the  Edit Prodeal Program Page  ",
					Common.getscreenShotPathforReport("Failed to navigate to the  Edit Prodeal Program Page with Success message"));
			Assert.fail();
		}

	}

	public void Accept_ProDeal_Application() {
		// TODO Auto-generated method stub
		try {
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("xpath", "//select[@name='status']");
			Common.dropdown("xpath", "//select[@name='status']", SelectBy.TEXT,"Accepted");
			
			ExtenantReportUtils.addPassLog("Validating Application Status",
					"Status will select Successfully",
					"Able to  select the application status ",
					Common.getscreenShotPathforReport("Passed to select the Application"));
				
				
			

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Application Status",
					"Status will select Successfully",
					"Unable to  select the application status ",
					Common.getscreenShotPathforReport("Failed to select the Application"));
			Assert.fail();
		}

	}
	
	public void Click_create_new_prodeal() {
		try {
			Sync.waitPageLoad();
			Thread.sleep(2000);
			Common.clickElement("xpath", "//span[contains(text(),'Create New Pro Deal Application')]");
			Thread.sleep(5000);

			String prodealprogram = Common.findElement("xpath", "//h1[@class='page-title']").getAttribute("class");
			System.out.println(prodealprogram);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(prodealprogram.contains("page-title"), "To Validate the prodeal program page is displayed",
					"should display the prodeal program after clicking on the application",
					"prodeal program page menu is displayed after a click on the application button", "Failed to display prodeal program page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the prodeal program page is displayed",
					"should display the prodeal program after clicking on the application",
					"prodeal program page menu is displayed after a click on the application button",
					"Failed to display prodeal program page");
			Assert.fail();
		}
	}


	public void Prodeal_program_application_form(String DataSet) throws Exception{
		try {
	Thread.sleep(5000);
	Common.textBoxInput("xpath", "//input[@name='first_name']",data.get(DataSet).get("FirstName"));	
	Common.textBoxInput("xpath", "//input[@name='last_name']",data.get(DataSet).get("LastName"));
	Common.textBoxInput("xpath", "//input[@name='association']",data.get(DataSet).get("Association"));
	Common.textBoxInput("xpath", "//input[@name='association_email']",data.get(DataSet).get("AssociationEmail"));
	Thread.sleep(3000);
	Common.dropdown("xpath", "//select[@name='group_id']", SelectBy.TEXT,data.get(DataSet).get("Group"));
	Common.textBoxInput("xpath", "//textarea[@name='comment']",data.get(DataSet).get("Comment"));
	Common.textBoxInput("xpath", "//input[@name='customer_id']",data.get(DataSet).get("CustomerID"));
	Thread.sleep(3000);
	Common.dropdown("xpath", "//select[@name='status']", SelectBy.TEXT,data.get(DataSet).get("Status"));
	Thread.sleep(5000);
	Common.clickElement("xpath", "//button[@id='save']");
	Thread.sleep(5000);
	Sync.waitElementPresent("xpath", "//div[@class='message message-success success']");
	String successmessage = Common.getText("xpath", "//div[@class='message message-success success']");
	System.out.println(successmessage);
	Common.assertionCheckwithReport(successmessage.contains("successfully"), "To Validate the new prodeal program form is filled",
			"new prodeal program form should poppulate with required fields and it saved without any errors",
			"New prodeal program form is populated and successfully submitted", "Failed to submit the new prodeal program");



		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the new prodeal program form is filled",
					"new prodeal program form should poppulate with required fields and it saved without any errors",
					"New prodeal program form is populated and successfully submitted",
					"Failed to submit the new prodeal program");
			Assert.fail();
	}
		
		

	}


	public void Click_Marketing() {
		// TODO Auto-generated method stub
		try {
			

			Sync.waitElementPresent("xpath", "//li[@id='menu-magento-backend-marketing']");
			Common.clickElement("xpath", "//li[@id='menu-magento-backend-marketing']");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			String text = Common.findElement("xpath", "//li[@data-ui-id='menu-magento-salesrule-promo-quote']")
					.getText();
			System.out.println(text);
			Common.assertionCheckwithReport(text.contains("Cart Price Rules"),
					"To Validate the sub-categories is displayed under marketing page",
					"should display the sub-categories under marketing page after clicking on the marketing",
					"sub-categories page is displayed after a click on the marketing button",
					"Failed to display sub-categories under marketing");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the sub-categories is displayed under marketing page",
					"should display the sub-categories under marketing page after clicking on the marketing",
					"unable to display sub-categories under marketing after a click on the marketing",
					"Failed to display sub-categories under marketing");
			Assert.fail();
		}

			
	}

	
	public void select_cart_price_rule() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);

			Sync.waitElementPresent("xpath", "//li[@data-ui-id='menu-magento-salesrule-promo-quote']");
			Common.clickElement("xpath", "//li[@data-ui-id='menu-magento-salesrule-promo-quote']");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			String title = Common.getPageTitle();
			System.out.println(title);
			Common.assertionCheckwithReport(title.contains("Cart Price Rules / Promotions / Marketing / Magento Admin"),
					"To Validate the cart price rule page is displayed",
					"should display the cart price rules page after clicking on the cart price rule",
					"cart price rules page is displayed after a click on the cart price rule button",
					"Failed to display cart price rules page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the cart price rule page is displayed",
					"should display the cart price rules page after clicking on the cart price rule",
					"unable to display cart price rules after a click on the cart price rule",
					"Failed to display cart price rules page");
			Assert.fail();
		}

	}
	
	
	
	
	
	public void Click_AddNewRule(String dataSet) {
		// TODO Auto-generated method stub
		try {
			
			Sync.waitElementPresent("xpath", "//input[@name='coupon_code']");
			Common.clickElement("xpath", "//input[@name='coupon_code']");
			Common.textBoxInput("xpath", "//input[@name='coupon_code']", data.get(dataSet).get("Coupon Code"));
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			

			int sizesframe = Common.findElements("xpath", "//td[@class=' col-is_active  ']").size();
			if (sizesframe > 0) {
				Sync.waitElementVisible("xpath", "//a[text()='Edit']");
				Common.clickElement("xpath", "//a[text()='Edit']");
				Sync.waitPageLoad();
				Thread.sleep(5000);
				Sync.waitElementPresent("xpath", "//button[@id='delete']");
				Common.clickElement("xpath", "//button[@id='delete']");
				Thread.sleep(2000);

				Sync.waitElementPresent("xpath", "//button[@class='action-primary action-accept']");
				Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
				Sync.waitPageLoad();
				Thread.sleep(2000);

				String deletmsg = Common.findElement("xpath", "//div[text()='You deleted the rule.']").getText();
				System.out.println(deletmsg);
				Common.assertionCheckwithReport(deletmsg.contains("You deleted the rule."),
						"To Validate the delete message is displayed",
						"should display the delete message page after clicking on the delete button",
						"delete message  page is displayed after a click on the delete button",
						"Failed to display delete message ");

				Sync.waitElementPresent("xpath", "//button[@title='Add New Rule']");
				Common.clickElement("xpath", "//button[@title='Add New Rule']");
			} else {

				Sync.waitElementPresent("xpath", "//button[@title='Add New Rule']");
				Common.clickElement("xpath", "//button[@title='Add New Rule']");
			}
			Sync.waitPageLoad();
			Thread.sleep(2000);
			String text = Common.findElement("xpath", "//h1[@class='page-title']").getText();
			System.out.println(text);
			Common.assertionCheckwithReport(text.contains("New Cart Price Rule"),
					"To Validate the New cart price rule page is displayed",
					"should display the New cart price rules page after clicking on the Add New rule",
					"New cart price rules page is displayed after a click on the Add New rule button",
					"Failed to display New cart price rules page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the new cart price rule page is displayed",
					"should display the New cart price rules page after clicking on the Add New rule",
					"unable to display New cart price rules after a click on the Add new rule",
					"Failed to display New cart price rules page");
			Assert.fail();
		}

	}
	
	
	
	
	public void Rule_information(String dataSet) {
		// TODO Auto-generated method stub

		try {
		
			Sync.waitElementPresent("xpath", "//input[@name='name']");
			Common.textBoxInput("xpath", "//input[@name='name']", data.get(dataSet).get("RuleName"));

			Sync.waitElementPresent("xpath", "//textarea[@name='description']");
			Common.textBoxInput("xpath", "//textarea[@name='description']", data.get(dataSet).get("Description"));


			Select_websites(dataSet);
			
			Sync.waitElementPresent("xpath", "//option[text()='NOT LOGGED IN']");
			Common.clickElement("xpath", "//option[text()='NOT LOGGED IN']");

			Sync.waitElementPresent("xpath", "//select[@name='coupon_type']");
			Common.clickElement("xpath", "//select[@name='coupon_type']");
			Common.dropdown("xpath", "//select[@name='coupon_type']", Common.SelectBy.TEXT,
					data.get(dataSet).get("Coupon"));

			Sync.waitElementPresent("xpath", "//input[@name='coupon_code']");
			Common.clickElement("xpath", "//input[@name='coupon_code']");
			Common.textBoxInput("xpath", "//input[@name='coupon_code']", data.get(dataSet).get("Coupon Code"));

			Sync.waitElementPresent("xpath", "//select[@name='finance_category']");
			Common.dropdown("xpath", "//select[@name='finance_category']", Common.SelectBy.TEXT,
					data.get(dataSet).get("FinanceCategory"));
			
			Thread.sleep(3000);
//			Common.scrollIntoView("xpath", "//span[text()='Actions']");
			Sync.waitElementPresent("xpath", "//span[text()='Actions']");
			Common.clickElement("xpath", "//span[text()='Actions']");
			
			
		/*	Sync.waitElementPresent("xpath", "//div[@class='admin__field-control']//select[@name='simple_action']");
			Common.javascriptclickElement("xpath", "//div[@class='admin__field-control']//select[@name='simple_action']");*/
			
		
			Thread.sleep(5000);
			String open1=Common.findElement("xpath","(//div[@class='fieldset-wrapper-title'])[5]").getAttribute("data-state-collapsible");
			if(open1.contains("open")) {
				Thread.sleep(2000);
			//Common.clickElement("xpath", "//select[@name='simple_action']");
			Common.dropdown("xpath", "//select[@name='simple_action']", Common.SelectBy.TEXT, data.get(dataSet).get("Apply"));
			}else {
				Sync.waitElementPresent("xpath", "//span[text()='Actions']");
				Common.clickElement("xpath", "//span[text()='Actions']");
				Thread.sleep(5000);
				Common.dropdown("xpath", "//select[@name='simple_action']", Common.SelectBy.TEXT, data.get(dataSet).get("Apply"));
			}
		
			
			Sync.waitElementPresent("xpath", "//input[@name='discount_amount']");
			Common.textBoxInput("xpath", "//input[@name='discount_amount']", data.get(dataSet).get("Discount Amount"));


			Sync.waitElementPresent("xpath", "//button[@id='save']");
			Common.clickElement("xpath", "//button[@id='save']");

			Sync.waitPageLoad();
			Thread.sleep(2000);
			String text = Common.findElement("xpath", "//div[@class='message message-success success']").getText();
			System.out.println(text);
			Common.assertionCheckwithReport(text.contains("You saved the rule."),
					"To Validate the sucess message is displayed",
					"should display the sucess message after clicking on the sudmitorder",
					"sucess message is displayed after a click on the sudmitorder button",
					"Failed to display sucess message");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the sucess message is displayed",
					"should display the sucess message after clicking on the sudmitorder",
					"unable to display sucess message after a click on the sudmitorder button",
					"Failed to display sucess message");
			Assert.fail();
		}

	}

	public void Select_websites(String dataSet) throws Exception {
		String website=data.get(dataSet).get("website");
		System.out.println(website);
		
		String[] websites = website.split(",");

		for (int i = 0; i < websites.length; i++) {
			System.out.println(websites[i]);
			Sync.waitElementClickable("xpath", "//option[text()='" + websites[i] + "']");
			
			Common.clickElement("xpath", "//option[text()='" + websites[i] + "']");

		}
	}
	
	
	
	public void open_Website(String dataSet) {

		try {
			Sync.waitPageLoad(60);

			if (Common.getCurrentURL().contains("emea")) {
				Common.openNewTab();
				Common.oppenURL(data.get(dataSet).get("URL"));
			} else {
				Common.oppenURL(data.get(dataSet).get("Preprod"));
			}

			Sync.waitPageLoad(40);

			String uname = Common.getPageTitle();
			Common.assertionCheckwithReport(uname.contains("Home page"),
					"Validating the User lands to the Osprey_EU page", "User should able to land on the Osprey_EU page",
					"Sucessfully User lands on the Osprey_EU page", "Failed to navigate to the Osprey_EU page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the User lands to the Osprey_EU page",
					"User should able to land on the Osprey_EU page", "Unable to Navigate to the Osprey_EU page",
					Common.getscreenShotPathforReport("Failed to navigate to the Osprey_EU page"));

			Assert.fail();
		}
	}

	
	
	
	
	public void search_product(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		System.out.println(product);
		try {
			Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
			String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
			Common.assertionCheckwithReport(open.contains("active"), "User searches using the search field",
					"User should able to click on the search button", "Search expands to the full page",
					"Sucessfully search bar should be expand");
			Common.textBoxInput("xpath", "//input[@type='search']", data.get(Dataset).get("Products"));
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String productsearch = Common.findElement("xpath", "//span[@id='algolia-srp-title']").getText();
			System.out.println(productsearch);
			Common.assertionCheckwithReport(productsearch.contains(product), "validating the search functionality",
					"enter product name will display in the search box", "user enter the product name in  search box",
					"Failed to see the product name");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the search functionality",
					"enter product name will display in the search box",
					" unable to enter the product name in  search box",
					Common.getscreenShot("Failed to see the product name"));
			Assert.fail();
		}

	}

	
	public void addtocart(String Dataset) {
		String product = data.get(Dataset).get("Products");
		System.out.println(product);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[contains(@class,'m-product-card__image')]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@class,'m-product-card__image')]");

				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Thread.sleep(6000);
			Common.scrollIntoView("xpath", "//img[@alt='" + product + "']");
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product + "']");
			Common.clickElement("xpath", "//img[@alt='" + product + "']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
			System.out.println(name);
			Common.assertionCheckwithReport(name.equals(product), "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"failed to Navigate to the PDP page");
			product_quantity(Dataset);
			Sync.waitElementPresent("xpath", "//span[text()='Add to Cart']");
			Common.clickElement("xpath", "//span[text()='Add to Cart']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
					.getAttribute("data-ui-id");
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");
			Common.refreshpage();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));

			Assert.fail();
		}
	}
	
	public void click_minicart() {
		try {
			Thread.sleep(8000);
			Common.actionsKeyPress(Keys.PAGE_UP);
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.ARROW_UP);
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//a[contains(@class,'c-mini')]");
			Common.mouseOverClick("xpath", "//a[contains(@class,'c-mini')]");
			String openminicart = Common.findElement("xpath", "//div[@data-block='minicart']").getAttribute("class");
			System.out.println(openminicart);
			Common.assertionCheckwithReport(openminicart.contains("active"), "To validate the minicart popup",
					"the mini cart is displayed", "Should display the mini cart", "mini cart is not displayed");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the minicart popup", "the mini cart is displayed",
					"unable to  dislay the mini cart", Common.getscreenShot("Failed to display the mini cart"));
			Assert.fail();

		}
	}

	public void addDeliveryAddress_Guestuser(String dataSet) throws Exception {
		String address = data.get(dataSet).get("Street");

		try {
			Thread.sleep(5000);
			Sync.waitElementVisible("name", "username");
			Common.textBoxInput("name", "username", data.get(dataSet).get("Email"));

			Thread.sleep(3000);
			String expectedResult = "email field will have email address";

			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));
			int size = Common.findElements("id", "customer-email").size();
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Common.clickElement("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']", address);
			Common.actionsKeyPress(Keys.BACK_SPACE);
			Common.actionsKeyPress(Keys.BACK_SPACE);
			Thread.sleep(5000);
			Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']").sendKeys("Rd");
			Thread.sleep(5000);
			Sync.waitElementPresent(30, "xpath", "//li[@class='typeahead-dropdown-menu-li']");

	
			String add= Common.findElement("xpath", "(//a[@class='dropdown-item list-item'])[1]").getText();
			
				System.out.println(add);
				System.out.println(address);
				Thread.sleep(4000);
				Common.assertionCheckwithReport(add.contains(address),
						"validating the dropdown in the shipping address page ",
						"User should able to see the Locate dropdown for respective address",
						"Successfully Locate dropdown has been displayed in shipping address page",
						"Failed to display dropdown in the shipping address page");

			
			String Text = Common.getText("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");

			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='city']").clear();
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
					data.get(dataSet).get("City"));
			System.out.println(data.get(dataSet).get("City"));

			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			try {
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
				Thread.sleep(3000);
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			Common.textBoxInputClear("name", "postcode");
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("Postcode"));
			Thread.sleep(5000);

			Common.textBoxInput("name", "telephone", data.get(dataSet).get("Phone"));

			Sync.waitPageLoad();
			ExtenantReportUtils.addPassLog("validating shipping address filling Fileds",
					"shipping address is filled in to the fields", "user should able to fill the shipping address ",
					Common.getscreenShotPathforReport("Sucessfully shipping address details has been entered"));

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating shipping address",
					"shipping address is filled in to the fields", "user faield to fill the shipping address",
					Common.getscreenShotPathforReport("shipingaddressfaield"));
			Assert.fail();

		}}
	
	
	public void minicart_Checkout() {
		// TODO Auto-generated method stub
		try {
			click_minicart();
			Sync.waitElementPresent("xpath", "//p[@class='c-mini-cart__total-counter']//strong");
			String minicart = Common.findElement("xpath", "//p[@class='c-mini-cart__total-counter']//strong").getText();
			System.out.println(minicart);
			Sync.waitElementPresent(30, "xpath", "//button[@title='Checkout']");
			Common.clickElement("xpath", "//button[@title='Checkout']");
			Sync.waitPageLoad();
			Thread.sleep(7000);
			Sync.waitElementPresent(30, "xpath", "//strong[@role='heading']");
			String checkout = Common.findElement("xpath", "//span[contains(@data-bind,'text: getC')]").getText();
			System.out.println(checkout);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(
					checkout.equals(minicart) && Common.getCurrentURL().contains("checkout/#shipping"),
					"validating the navigation to the shipping page when we click on the checkout",
					"User should able to navigate to the shipping  page", "Successfully navigate to the shipping page",
					"Failed to navigate to the shipping page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the navigation to the shipping page when we click on the checkout ",
					"User should able to navigate to the shipping  page", "unable to navigate to the shipping page",
					Common.getscreenShot("Failed to navigate to the shipping page"));

			Assert.fail();
		}
}

	public void product_quantity(String Dataset) {
		// TODO Auto-generated method stub
		String Quantity = data.get(Dataset).get("Quantity");
		try {
			Common.findElement("xpath", "//select[@class='a-select-menu']");
//			Common.clickElement("xpath", "//select[@class='a-select-menu']");
			Common.dropdown("xpath", "//select[@class='a-select-menu']", Common.SelectBy.VALUE, Quantity);
			Thread.sleep(3000);
			String value = Common.findElement("xpath", "//select[@class='a-select-menu']").getAttribute("value");
			Common.assertionCheckwithReport(value.equals(Quantity),
					"validating the  product the product quantity in PDP page",
					"Product quantity should be update in the PDP page",
					"Sucessfully product Qunatity has been updated ",
					"failed to Update the prodcut quantity in PDP page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product the product quantity in PDP page",
					"Product quantity should be update in the PDP page", "unable to change the  product Qunatity",
					Common.getscreenShot("failed to update the product quantity"));
			Assert.fail();
		}

	}

	
	
	public void clickSubmitbutton_Shippingpage() {
		String expectedResult = "click the submit button to navigate to payment page";
		try {
			
			
			Common.clickElement("xpath", "//button[@data-role='opc-continue']");
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the shipping page submitbutton", expectedResult,
					"failed to click the submitbutton",
					Common.getscreenShotPathforReport("failed submitbuttonshippingpage"));
			Assert.fail();
		}
	}



	public void discountCode(String dataSet) throws Exception {
		String expectedResult = "It should opens textbox input.";

		try {

			Thread.sleep(4000);
			Sync.waitElementPresent("id", "block-discount-heading");
			Common.clickElement("id", "block-discount-heading");

			Sync.waitElementPresent("id", "discount-code");

			Common.textBoxInput("id", "discount-code", data.get(dataSet).get("Coupon Code"));

			int size = Common.findElements("id", "discount-code").size();
			Common.assertionCheckwithReport(size > 0, "verifying the Discount Code label", expectedResult,
					"Successfully open the discount input box", "User unable enter Discount Code");
			Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//button[@value='Apply Discount']");
			Common.clickElement("xpath", "//button[@value='Apply Discount']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
	      	expectedResult = "It should apply discount on your price.If user enters invalid promocode it should display coupon code is not valid message.";
			String discountcodemsg = Common.getText("xpath", "//div[contains(@data-ui-id,'checkout-cart-validation')]");
			Common.assertionCheckwithReport(discountcodemsg.contains("Your coupon was successfully"),
					"verifying pomocode", expectedResult, "promotion code working as expected",
					"Promation code is not applied");
			Thread.sleep(4000);
			String Subtotal = Common.getText("xpath", "//tr[@class='totals sub']//span[@class='price']").replace("£",
					"");
			Float subtotalvalue = Float.parseFloat(Subtotal);
			System.out.println("subtotalvalue="+ subtotalvalue);
			String shipping = Common.getText("xpath", "//tr[@class='totals shipping excl']//span[@class='price']")
					.replace("£", "");
			Float shippingvalue = Float.parseFloat(shipping);
//			String Tax = Common.getText("xpath", "//tr[@class='totals-tax']//span[@class='price']").replace("£", "");
			System.out.println("shippingvalue="+ shippingvalue);
//			Float Taxvalue = Float.parseFloat(Tax);
			Thread.sleep(4000);
			String Discount = Common.getText("xpath", "//tr[@class='totals discount']//span[@class='price']").replace("£", "");
			Float Discountvalue = Float.parseFloat(Discount);
//			System.out.println(Discountvalue);
			System.out.println("Discountvalue="+ Discountvalue);
			String ordertotal = Common.getText("xpath", "//tr[@class='grand totals']//span[@class='price']")
					.replace("£", "");
			Float ordertotalvalue = Float.parseFloat(ordertotal);
			Float Total = (subtotalvalue + shippingvalue) + Discountvalue;
			System.out.println("Total="+ Total);
			Thread.sleep(4000);
			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			System.out.println("ExpectedTotalAmmount2="+ExpectedTotalAmmount2);
			System.out.println("ordertotal="+ordertotal);
			Common.assertionCheckwithReport(ExpectedTotalAmmount2.equals(ordertotal),
					"validating the order summary in the payment page",
					"Order summary should be display in the payment page and all fields should display",
					"Successfully Order summary is displayed in the payment page and fields are displayed",
					"Failed to display the order summary and fileds under order summary");

		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating discount code", expectedResult,
					"User failed to proceed with discountcode",
					Common.getscreenShotPathforReport("discountcodefailed"));
	e.printStackTrace();
			Assert.fail();

		}
	}



	public String updatePaymentAndSubmitOrder(String dataSet) throws Exception {
		// TODO Auto-generated method stub

		String order = "";
		addPaymentDetails(dataSet);
		String expectedResult = "It redirects to order confirmation page";

		if (Common.findElements("xpath", "//div[@class='message message-error']").size() > 0) {
			Thread.sleep(4000);
			addPaymentDetails(dataSet);
		}

		Thread.sleep(3000);
		int placeordercount = Common.findElements("xpath", "//span[text()='Place Order']").size();
		if (placeordercount > 1) {
			Thread.sleep(4000);

			Common.clickElement("xpath", "//span[text()='Place Order']");
			Common.refreshpage();
		}

		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);

		if (!url.contains("stage") && !url.contains("preprod")) {
		}

		else {
			try {
				String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();

				int sizes = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();
				Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
						"verifying the product confirmation", expectedResult,
						"Successfully It redirects to order confirmation page Order Placed",
						"User unabel to go orderconformation page");

				if (Common.findElements("xpath", "//div[@class='checkout-success']//p//strong").size() > 0) {
					order = Common.getText("xpath", "//div[@class='checkout-success']//p//strong");
					System.out.println(order);
				}

				if (Common.findElements("xpath", "//div[@class='checkout-success']//span").size() > 0) {
					order = Common.getText("xpath", "//div[@class='checkout-success']//span");
					System.out.println(order);
				}

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
						"User failed to navigate  to order confirmation page",
						Common.getscreenShotPathforReport("failednavigatepage"));
				Assert.fail();
			}

		}
		return order;
	}

	
	
	
	public String addPaymentDetails(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> Paymentmethod = new HashMap<String, String>();
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String Number = "";
		String cardnumber = data.get(dataSet).get("CardNumber");
		System.out.println(cardnumber);
		String expectedResult = "land on the payment section";
		// Common.refreshpage();

		try {
			Sync.waitPageLoad();

			Sync.waitElementClickable("xpath", "//label[@for='stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unabel to land opaymentpage");
			Common.clickElement("xpath", "//label[@for='stripe_payments']");

			Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
			int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
			System.out.println(payment);
			if (payment > 0) {
				Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//span[text()='New payment method']");
				Thread.sleep(4000);
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Thread.sleep(5000);
				Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
				Common.clickElement("xpath", "//label[@for='Field-numberInput']");
				Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);
				Number = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ", "");

				Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonth"));

				Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("CVV"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
				Thread.sleep(1000);
				Common.clickElement("xpath", "//span[text()='Place Order']");

			} else {
				Thread.sleep(4000);
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Thread.sleep(5000);
				Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
				Common.clickElement("xpath", "//label[@for='Field-numberInput']");
				Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);

				Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonth"));

				Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("CVV"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
				Thread.sleep(1000);
				Common.clickElement("xpath", "//span[text()='Place Order']");

			}

		}

		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", expectedResult,
					"failed  to fill the Credit Card infromation",
					Common.getscreenShotPathforReport("Cardinfromationfail"));
			Assert.fail();
		}

		expectedResult = "credit card fields are filled with the data";
		String errorTexts = Common.findElement("xpath", "//div[contains(@class,'error')]").getText();

		Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data",
				expectedResult, "Filled the Card detiles", "missing field data it showinng error");

		return Number;

	}
	
	
	
	
	
	public void Backto_magento_admin() {
		try {

			Common.switchToFirstTab();
			Thread.sleep(2000);

			// String text=Common.getText("xpath", "//h1[@class='page-title']");
			String text = Common.getPageTitle();
			

			System.out.println(text);
			Thread.sleep(2000);

			Common.assertionCheckwithReport(text.contains("Cart Price Rules") || text.contains("QATEST product"), "To Validate the pagetitle is displayed",
					"should display the pagetitle after clicking on the switchtofirsttab",
					"pagetitle is displayed after a click on the switchtofirsttab", "Failed to display pagetitle");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the pagetitle is displayed",
					"should display the pagetitle after clicking on the switchtofirsttab",
					"unable to display pagetitle after a click on the switchtofirsttab", "Failed to display pagetitle");
			Assert.fail();
		}

	}

	
	public void delet_existing_Coupon(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);

			Sync.waitElementPresent("xpath", "//input[@name='coupon_code']");
			Common.clickElement("xpath", "//input[@name='coupon_code']");
			Common.textBoxInput("xpath", "//input[@name='coupon_code']", data.get(dataSet).get("Coupon Code"));
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(2000);

			Sync.waitElementPresent("xpath", "//input[@name='name']");
			Common.scrollIntoView("xpath", "//input[@name='name']");
			
			Sync.waitElementVisible("xpath", "//a[text()='Edit']");
			Common.clickElement("xpath", "//a[text()='Edit']");
			Sync.waitPageLoad();
			Thread.sleep(5000);

			Sync.waitElementPresent("xpath", "//textarea[@name='description']");
			Common.scrollIntoView("xpath", "//textarea[@name='description']");

			Sync.waitElementPresent("xpath", "//option[text()='Osprey UK']");
			Common.scrollIntoView("xpath", "//option[text()='Osprey UK']");

			Sync.waitElementPresent("xpath", "//option[text()='General']");
			Common.scrollIntoView("xpath", "//option[text()='General']");

			Sync.waitElementPresent("xpath", "//select[@name='coupon_type']");
			Common.scrollIntoView("xpath", "//select[@name='coupon_type']");

			Sync.waitElementPresent("xpath", "//input[@name='coupon_code']");
			Common.scrollIntoView("xpath", "//input[@name='coupon_code']");

			Sync.waitElementPresent("xpath", "//select[@name='finance_category']");
			Common.scrollIntoView("xpath", "//select[@name='finance_category']");

			Sync.waitElementPresent("xpath", "//button[@id='delete']");
			Common.clickElement("xpath", "//button[@id='delete']");

			Sync.waitElementPresent("xpath", "//button[@class='action-primary action-accept']");
			Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			String deletmsg = Common.findElement("xpath", "//div[text()='You deleted the rule.']").getText();
			System.out.println(deletmsg);
			Common.assertionCheckwithReport(deletmsg.contains("You deleted the rule."),
					"To Validate the delete message is displayed",
					"should display the delete message page after clicking on the delete button",
					"delete message  page is displayed after a click on the delete button",
					"Failed to display delete message ");

			//Click_AddNewRule();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the new cart price rule page is displayed",
					"should display the New cart price rules page after clicking on the Add New rule",
					"unable to display New cart price rules after a click on the Add new rule",
					"Failed to display New cart price rules page");
			Assert.fail();
		}

	}
	
	
	
	
	public void Admin_marketing() {
		// TODO Auto-generated method stub
		try {

			Common.clickElement("xpath", "//li[@id='menu-magento-backend-marketing']");
			Sync.waitElementPresent("xpath", "//li[@id='menu-magento-backend-marketing']");
			String marketing = Common.findElement("xpath", "//strong[contains(text(),'Marketing')]").getText();
			System.out.println(marketing);

			Common.assertionCheckwithReport(marketing.equals("Marketing"),
					"To validate the marketing menu is displayed after admin clicks on the marketing from the main menu",
					"After clicking the marketing  should display the marketing menu options",
					"Admin successfully clicked the marketing and it displayed the marketing Menu",
					"Admin failed to click the marketing menu");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate the marketing menu after admin clicks on the marketing from the main menu",
					"After clicking the marketing should display the marketing options",
					"Admin failed to click the marketing from the  menu",
					Common.getscreenShotPathforReport("Admin failed to click on the marketing menu"));
			Assert.fail();
		}
	}

	public void Admin_CatalogPriceRule() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//span[text()='Catalog Price Rule']");
			Common.clickElement("xpath", "//span[text()='Catalog Price Rule']");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Catalog Price Rule / Promotions / Marketing / Magento Admin"),
					"To validate the Admin is landing on the Catalog Price Rule page After clicking on Catalog Price Rule menu",
					"After clicking on Catalog Price Rule menu it should navigate to Catalog Price Rule page",
					"Admin successfully navigate to the Catalog Price Rule page",
					"Admin failed to navigate to the Catalog Price Rule page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate the Admin is landing on the Catalog Price Rule page After clicking on Catalog Price Rule menu",
					"After clicking on Catalog Price Rule menu it should navigate to Catalog Price Rule page",
					"Admin failed to navigate to the Catalog Price Rule page",
					Common.getscreenShotPathforReport("Admin failed to navigate to the Catalog Price Rule page"));
			Assert.fail();
		}
	}




	public void deleteexistingcatalogrule(String dataSet) {
		String pricerulename = data.get(dataSet).get("RuleName");

		try {
			Sync.waitElementVisible("id", "promo_catalog_grid_filter_name");
			Common.textBoxInput("id", "promo_catalog_grid_filter_name", pricerulename);
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(6000);
			int results = Common.findElements("xpath", "//td[text()='" + pricerulename + "']").size();
			if (results > 0) {
				Common.clickElement("xpath", "//td[text()='" + pricerulename + "']");
				Sync.waitPageLoad();
				String pagetitle = Common.findElement("xpath", "//h1[text()='" + pricerulename + "']").getText();

				Common.assertionCheckwithReport(pagetitle.equals(pricerulename),
						"To validate the user land on the selected price rule page",
						"Should land on the selected price rule page",
						"Catalog price rule is applied on the oxo store fromnt",
						"failed to apply the catalog price rule on oxo front end");
				Common.javascriptclickElement("xpath", "//button[@title='Delete Rule']");
				Sync.waitElementVisible("xpath", "//button[contains(@class,'action-accept')]");
				Common.javascriptclickElement("xpath", "//button[contains(@class,'action-accept')]");
				Sync.waitPageLoad();
				String successmessage = Common.findElement("xpath", "//div[contains(@class,'message-success')]/div")
						.getText();

				Common.assertionCheckwithReport(successmessage.equals("You deleted the rule."),
						"To validate the page is deleted successfully and success message is dispalyed",
						"Should display successmessage after deleteing the catalog price rule",
						"Success message is displayed", "Failed to display the successmessage");

			} else {

				System.out.println("no search results found");
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate the page is deleted successfully and success message is dispalyed",
					"Should display successmessage after deleteing the catalog price rule",
					"Failed to delet the categroy price rule", "Catalog price rule deletion unsuccessfull");
			Assert.fail();

		}
	}

	public void Admin_Create_New_Rule(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("id", "add");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//h1[@class='page-title']");
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals(
							"New Catalog Price Rule / Catalog Price Rule / Promotions / Marketing / Magento Admin"),
					"To validate the admin is landing on create new catalog price rule page",
					"Admin should land on Create new catalog price rule page",
					"Admin landed on the create new catalog price rule page",
					"Admin failed to navigate to the New Catalog Price Rule page");

			Common.textBoxInput("name", "name", data.get(dataSet).get("RuleName"));
			Common.textBoxInput("name", "description", data.get(dataSet).get("Description"));
			Common.clickElement("xpath", "//label[@class='admin__actions-switch-label']");
			// String selectAll = Keys.chord(Keys.CONTROL, "a");
			// Common.findElement(By.name("website_ids")).sendKeys(selectAll);

			WebElement Select_Website = Common.findElement("name", "website_ids");
			Select_Website.sendKeys(Keys.CONTROL + "a");
			Thread.sleep(2000);
			WebElement Select_Customergroups = Common.findElement("name", "customer_group_ids");
			Select_Customergroups.sendKeys(Keys.CONTROL + "a");

			Common.textBoxInput("xpath", "//input[@name='sort_order']", data.get(dataSet).get("Priority"));

			Common.dropdown("xpath", "//select[@name='finance_category']", Common.SelectBy.TEXT,
					data.get(dataSet).get("Financecategory"));
			Common.clickElement("xpath", " //span[text()='Conditions']");
			Common.scrollIntoView("xpath", "//img[@title='Add']");
			Sync.waitElementVisible(30, "xpath", "//img[@title='Add']");
			Common.javascriptclickElement("xpath", "//img[@title='Add']");
			Sync.waitElementVisible(50, "id", "conditions__1__new_child");

			Common.dropdown("id", "conditions__1__new_child", Common.SelectBy.TEXT, data.get(dataSet).get("Attribute"));
			Sync.waitElementVisible("xpath", "//a[contains(text(),'...')]");
			Common.javascriptclickElement("xpath", "//a[contains(text(),'...')]");
			Sync.waitElementPresent(30, "xpath", "//img[@title='Open Chooser']");
			Common.clickElement("xpath", "//img[@title='Open Chooser']");
			Sync.waitElementPresent("xpath", "//div[@class='rule-chooser']");
			int categorydispaly = Common.findElements("xpath", "//div[@class='rule-chooser']/div").size();
			Common.assertionCheckwithReport(categorydispaly > 0,
					"To validate the category selection feild is displayed",
					"category selection feild should be dispaleyd", "category selection feild is displayed",
					"failed to dispaly the category selection feild");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the category selection feild is displayed",
					"category selection feild should be dispaleyd", "category selection feild is displayed",
					Common.getscreenShotPathforReport("Admin failed to display the category selction"));
			Assert.fail();
		}
	}

	public void Admin_Select_Category(String dataSet) {
		// TODO Auto-generated method stub
		String EUCategory = data.get(dataSet).get("EUCategory");
		String EUsubcategory = data.get(dataSet).get("EUsubcategory");
		try {

//			Sync.waitElementPresent("xpath", "(//img[contains(@class,'elbow-end-plus')])[1]");
//			Common.javascriptclickElement("xpath", "(//img[contains(@class,'elbow-end-plus')])[1]");
			
			Thread.sleep(3000);
//			Common.doubleClick("xpath", "//span[contains(text(),'" + EUCategory + "')]");
			Common.javascriptclickElement("xpath", "//span[contains(text(),'" + EUCategory + "')]");
		/*	Sync.waitElementPresent(30, "xpath", "//span[contains(text(),'" + EUsubcategory + "')]");

			Common.javascriptclickElement("xpath", "//span[contains(text(),'" + EUsubcategory + "')]");
			Common.scrollIntoView("xpath", "(//img[contains(@class,'x-tree-elbow-plus')])[5]");
			Common.javascriptclickElement("xpath", "(//img[contains(@class,'x-tree-elbow-plus')])[5]");
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//span[contains(text(),'" + OxoCategory + "')]");
			Common.doubleClick("xpath", "//span[contains(text(),'" + OxoCategory + "')]");
			Sync.waitElementPresent("xpath", "//span[contains(text(),'" + Oxosubcategory + "')]");
			Common.javascriptclickElement("xpath", "//span[contains(text(),'" + Oxosubcategory + "')]");*/

			Sync.waitElementPresent("xpath", "//img[@title='Apply']");
			Common.javascriptclickElement("xpath", "//img[@title='Apply']");
			Thread.sleep(3000);

			Common.scrollIntoView("xpath", "//span[text()='Actions']");
			Common.javascriptclickElement("xpath", "//span[text()='Actions']");

			Common.dropdown("xpath", "//select[@name='simple_action']", Common.SelectBy.TEXT,
					data.get(dataSet).get("Apply"));
			Thread.sleep(2000);
			Common.textBoxInput("name", "discount_amount", data.get(dataSet).get("Discount"));

			Common.dropdown("xpath", "//select[@name='stop_rules_processing']", Common.SelectBy.TEXT,
					data.get(dataSet).get("Discard subsequent rules"));
			Common.clickElement("xpath", "//span[text()='Save']");
			Sync.waitPageLoad();
			String saved_message = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']")
					.getText();

			Common.assertionCheckwithReport(
					saved_message.contains("You saved the rule.") && Common.getPageTitle()
							.contains("Catalog Price Rule / Promotions / Marketing / Magento Admin"),
					"To validate the New catalog price rule is saved",
					"After filling the catalog price rule details and click on save button, catalog promotion rule should save",
					"Successfully catalog price rule saved with a success message",
					"failed to save catalog price rule");
	      Thread.sleep(500000);
	            
		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("To validate the catalog price rule is saved in admin",
					"After filling the catalog price rule details and click on save button, catalog promotion rule should save in admin",
					"catalog price rule is not saved in admin",
					Common.getscreenShotPathforReport("Failed to save catalog price rule"));
			Assert.fail();
		}
	}
	
	public void Validate_Catalog_Pricerule_HF_Frontend(String dataSet) {
		// TODO Auto-generated method stub
		String EUCategory = data.get(dataSet).get("EUCategory");
		String discount = data.get(dataSet).get("Discount");

		try {

			Common.mouseOverClick("xpath", "//span[text()=' Test Category']");
	     	Sync.waitPageLoad();
			Thread.sleep(4000);

			Sync.waitElementVisible("xpath", "(//span[contains(@class,'old-price')]/span)");
			for (int i = 1; i <= 2; i++) {
				Common.scrollIntoView("xpath", "(//span[contains(@class,'old-price')]/span)[" + i + "]");
				String Oldprice = Common
						.findElement("xpath", "(//span[contains(@class,'old-price')]/span)[" + i + "]").getText()
						.replace("£", "");
				System.out.println("website old price" + Oldprice);
				String Website_discountprice = Common
						.findElement("xpath", "(//span[contains(@class,'normal-price')]/span)[" + i + "]").getText()
						.replace("£", "");
				
				float OldPrice = Float.parseFloat(Oldprice);
				
				System.out.println(OldPrice);
				float Website_Discountprice = Float.parseFloat(Website_discountprice);
				System.out.println("Website_Discountprice" + Website_Discountprice);

				float discountpercent = Float.parseFloat(discount);
				System.out.println("discountpercent:"+ discountpercent);
				
				float discountamount = OldPrice * discountpercent / 100;
				System.out.println("discountamount:"+ discountamount);

				String calculatednewprice = new BigDecimal(discountamount).setScale(2, BigDecimal.ROUND_HALF_UP)
						.toString();
			
				float CalculatedNewPrice = Float.parseFloat(calculatednewprice);
				System.out.println("calculatednewprice " + CalculatedNewPrice);

				float specialrpice = OldPrice - CalculatedNewPrice;
				System.out.println("calculatedspecialprice" + specialrpice);
				String discountprice = new BigDecimal(specialrpice).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				float calculateddiscountprice = Float.parseFloat(discountprice);
				System.out.println("calculateddiscountprice"+ calculateddiscountprice);

				Common.assertionCheckwithReport(Website_Discountprice == calculateddiscountprice,
						"To validate the catalog price is applied on the Osprey store front",
						"Catalog price rule should be applied on the store Osprey front",
						"Catalog price rule is applied on the Osprey store fromnt",
						"failed to apply the catalog price rule on Osprey front end");

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the catalog price is applied on the Osprey store front",
					"Catalog price rule should be applied on the Osprey store fromnt",
					"catalog price rule  is not updated on the frontend",
					Common.getscreenShotPathforReport("failed to apply the catalog price rule on the Osprey frontend"));
			Assert.fail();
		}
	}
	
	public void Navigate_Adminpage() {
		Common.switchToFirstTab();
	}

	
	public void click_catlog() {
		// TODO Auto-generated method stub

		try {
			Sync.waitPageLoad();
			Common.clickElement("id", "menu-magento-catalog-catalog");
			Sync.waitElementPresent("id", "menu-magento-catalog-catalog");
			String catlog = Common.findElement("xpath", "//strong[contains(text(),'Catalog')]").getText();
			System.out.println(catlog);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(catlog.equals("Catalog"), "To verify the Catalog menu ",
					"After clicking the Catalog menu it will display menu options ",
					"Successfully clicked the Catalog menu and it displayed the Catalog options",
					"Failed to click the Catalog menu");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("To verify the Catalog menu ",
					"After clicking the Catalog menu it will display menu options ",
					"Successfully clicked the Catalog menu and it displayed the Catalog options",
					Common.getscreenShotPathforReport("Failed to click on the Catalog menu"));
			Assert.fail();
		}

	}

	public void click_categories() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//li[@class='item-catalog-categories    level-2']");
			Sync.waitElementPresent("xpath", "//li[@class='item-catalog-categories    level-2']");
			String categories = Common.findElement("xpath", "//h1[@class='page-title']").getText();
			System.out.println(categories);
			// Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and
			// @style='display: none;']");
			Common.assertionCheckwithReport(categories.equals("Default Category (ID: 2)"),
					"To verify the categories menu ",
					"After clicking the categories menu it will navigate to categories page ",
					"Successfully navigate to categories page", "Failed to navigate to categories page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("To verify the categories menu ",
					"After clicking the Catalog menu it will navigate to categories pag ",
					"Successfully navigate to categories pags",
					Common.getscreenShotPathforReport("Failed to navigate to categories pag"));
			Assert.fail();
		}
	}

	public void Verify_CTALinks() {
		// TODO Auto-generated method stub
		try {

			String category = Common.findElement("xpath", "//div[@class='admin__field _required']").getText();
			System.out.println(category);
			Common.assertionCheckwithReport(category.equals("Category Name"), "validation the category name ",
					"validating the scope ", "Successfully validate the category name ", "validate the category name");

			String Scope = Common.findElement("xpath", "//div[@class='store-switcher store-view']").getText();
			System.out.println(Scope);
			Common.assertionCheckwithReport(Scope.contains("Scope:"), "validation of the scope ",
					"validating the scope ", "Successfully validate scope ", "validate the scope");

			String save = Common.findElement("xpath", "//button[@id='save']").getText();
			System.out.println(save);
			Common.assertionCheckwithReport(save.equals("Save"), "validation of the save button ",
					"validating the save button ", "Successfully validate save ", "validate save");

			String addroot = Common.findElement("xpath", "//button[@id='add_root_category_button']").getText();
			System.out.println(addroot);
			Common.assertionCheckwithReport(addroot.equals("Add Root Category"), "validation of the Root Category ",
					"validating of the Root Category ", "Successfully validate the Root Category ",
					"validate the Root Category");

			String subcategory = Common.findElement("xpath", "//button[@id='add_subcategory_button']").getText();
			System.out.println(subcategory);
			Common.assertionCheckwithReport(subcategory.equals("Add Subcategory"), "validation of the Subcategory ",
					"validating of the Subcategory ", "Successfully validate the Subcategory ",
					"validate the Subcategory");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("validation of the Subcategory ", "aftervalidate the Subcategory ",
					"Successfully validate the Subcategory ",
					Common.getscreenShotPathforReport("Failed to validate the Subcategory"));
			Assert.fail();

		}
	}
	
	
	public void categorylist() {
		// TODO Auto-generated method stub
		try {
			String colapse = Common.findElement("xpath", "//a[@id='colapseAll']").getText();
			System.out.println(colapse);
			Common.assertionCheckwithReport(colapse.equals("Collapse All"), "validation to validate collapse ",
					"validation to validate collapse ", "Successfully validated to validate collapse ",
					"validated collapse ");

			String expand = Common.findElement("xpath", "//a[@id='expandAll']").getText();
			System.out.println(expand);
			Common.assertionCheckwithReport(expand.equals("Expand All"), "validation of the expand all ",
					"validation of the expand all ", "Successfully validated of the expand all ",
					"validated collapse ");

			String tree = Common.findElement("xpath", "//div[@class='categories-side-col']").getText();
			System.out.println(tree);
			Common.assertionCheckwithReport(tree.contains("Collapse All"), "validation of category tree ",
					"validating cateory tree ", "Successfully validating cateory tree ", "validated category tree");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("validation of the color applied in the backgroud color ",
					"after Clicking on the color the background color should be applied ",
					"Successfully Background color is applied ",
					Common.getscreenShotPathforReport("Failed to validate"));
			Assert.fail();

		}
	}

	public void category_content() {
		// TODO Auto-generated method stub

		try {
			String enable = Common.findElement("xpath", "//div[@class='admin__field-label']").getText();
			System.out.println(enable);
			Common.assertionCheckwithReport(enable.equals("Enable Category"),
					"validation of the color applied in the backgroud color ",
					"after Clicking on the color the background color should be applied ",
					"Successfully Background color is applied ", "Failed to apply backgroud color");

			String include = Common.findElement("xpath", "(//div[@class='admin__field-label'])[2]").getText();
			System.out.println(include);
			Common.assertionCheckwithReport(include.equals("Include in Menu"),
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

	
	
	
	public void Validate_Content() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent(30, "xpath", "//strong//span[text()='Content']");
			Common.clickElement("xpath", "//strong//span[text()='Content']");

			// common.asser
			String include = Common
					.findElement("xpath", "//div[@class='fieldset-wrapper admin__collapsible-block-wrapper _show']")
					.getText();
			System.out.println(include);
			Common.assertionCheckwithReport(include.contains("Content"),
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
	public void click_Display() {
		// TODO Auto-generated method stub
		try {

			Sync.waitElementPresent(30, "xpath", "//strong//span[text()='Display Settings']");
			Common.clickElement("xpath", "//strong//span[text()='Display Settings']");

			Common.scrollIntoView("xpath", "(//fieldset[@class='admin__fieldset'])[3]");
			String include = Common.findElement("xpath", "(//fieldset[@class='admin__fieldset'])[3]").getText();
			System.out.println(include);
			Common.assertionCheckwithReport(include.contains("Display Mode"),
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
	public void click_search_engine() {
		// TODO Auto-generated method stub

		try {

			Sync.waitElementPresent(30, "xpath",
					"(//div[@class='fieldset-wrapper admin__collapsible-block-wrapper'])[2]");

			Common.clickElement("xpath", "(//div[@class='fieldset-wrapper admin__collapsible-block-wrapper'])[2]");

			Common.scrollIntoView("xpath",
					"(//div[@class='fieldset-wrapper admin__collapsible-block-wrapper _show'])[3]");
			String engine = Common.findElement("xpath",
					"(//div[@class='fieldset-wrapper admin__collapsible-block-wrapper _show'])[3]").getText();
			System.out.println(engine);
			Common.assertionCheckwithReport(engine.contains("Search Engine Optimization"),
					"validation of the color applied in the backgroud color ",
					"after Clicking on the color the background color should be applied ",
					"Successfully Background color is applied ", "Failed to apply backgroud color");
			// fieldset[@class='admin__fieldset'])[4]

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("validation of the color applied in the backgroud color ",
					"after Clicking on the color the background color should be applied ",
					"Successfully Background color is applied ",
					Common.getscreenShotPathforReport("Failed to apply backgroud color"));
			Assert.fail();
		}
	}

	public void products_category() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//span[text()='Products in Category']");
			Sync.waitElementPresent("xpath", "//span[text()='Products in Category']");
			Common.scrollIntoView("xpath", "//div[@id='merchandiser-app']");
			String products = Common.findElement("xpath",
					"(//div[@class='fieldset-wrapper admin__collapsible-block-wrapper _show'])[4]").getText();
			System.out.println(products);
			// Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and
			// @style='display: none;']");
			Common.assertionCheckwithReport(products.contains("Products in Category"), "To verify the Catalog menu ",
					"After clicking the Catalog menu it will display menu options ",
					"Successfully clicked the Catalog menu and it displayed the Catalog options",
					"Failed to click the Catalog menu");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("To verify the Catalog menu ",
					"After clicking the Catalog menu it will display menu options ",
					"Successfully clicked the Catalog menu and it displayed the Catalog options",
					Common.getscreenShotPathforReport("Failed to click on the Catalog menu"));
			Assert.fail();
		}
	}
	
	
	public void Click_design() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Common.scrollIntoView("xpath", "(//span[text()='Design'])[2]");
			Common.clickElement("xpath", "(//span[text()='Design'])[2]");
			// Sync.waitElementPresent("xpath", "//span[text()='Design");
			Common.scrollIntoView("xpath",
					"(//div[@class='fieldset-wrapper admin__collapsible-block-wrapper _show'])[5]");
			String products = Common.findElement("xpath",
					"(//div[@class='fieldset-wrapper admin__collapsible-block-wrapper _show'])[5]").getText();
			System.out.println(products);
			// Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and
			// @style='display: none;']");
			Common.assertionCheckwithReport(products.contains("Design"), "To verify the Catalog menu ",
					"After clicking the Catalog menu it will display menu options ",
					"Successfully clicked the Catalog menu and it displayed the Catalog options",
					"Failed to click the Catalog menu");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("To verify the Catalog menu ",
					"After clicking the Catalog menu it will display menu options ",
					"Successfully clicked the Catalog menu and it displayed the Catalog options",
					Common.getscreenShotPathforReport("Failed to click on the Catalog menu"));
			Assert.fail();
		}
	}
	
	
	public void click_Add_Root_Category() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Common.clickElement("id", "add_root_category_button");
			Sync.waitElementPresent("id", "add_root_category_button");
			String button = Common.findElement("xpath", "//button[@title='Add Root Category']").getText();
			System.out.println(button);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(button.equals("Add Root Category"),
					"To verify the Add Root Category button ",
					"After clicking the Add Root Category it should navigate to page ",
					"Successfully navigate to Add Root Category", "Failed navigate to Add Root Category");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("To verify the Add Root Category ",
					"After clicking the Add Root Category it should navigate to page ",
					"Successfully navigate to Add Root Category",
					Common.getscreenShotPathforReport("Failed navigate to Add Root Category"));
			Assert.fail();
		}
	}

	public void savecategory(String DataSet) {
		// TODO Auto-generated method stub

		try {
			// Sync.waitElementPresent(30, "xpath", "//i[@title='Close Full Screen']");
			// Common.clickElement("xpath", "//i[@title='Close Full Screen']");
			Common.textBoxInput("xpath", "(//input[@type='text'])[2]", data.get(DataSet).get("RootCategory"));
			Common.clickElement("xpath", "//button[@id='save']");
			Sync.waitPageLoad(70);
			String savethepage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
			Common.assertionCheckwithReport(savethepage.equals("You saved the category."),
					"Validating the User need to save the page", "User should able to save the page",
					"Sucessfully User saves the page", "Unable to save the page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating the User need to save the page", "User should able to save the page",
					"Sucessfully User saves the page", Common.getscreenShotPathforReport("Failed to save the page"));
			Assert.fail();

		}
	}

	public void deletecategory() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//button[@id='delete']");
			Common.clickElement("xpath", "//button[@id='delete']");
			Sync.waitElementPresent("xpath", "//span[text()='OK']");
			Common.clickElement("xpath", "//span[text()='OK']");
			Sync.waitPageLoad(70);
			Sync.waitElementVisible("xpath", "//div[@class='message message-success success']/div");
			String delete = Common.findElement("xpath", "//div[@class='message message-success success']/div")
					.getText();
			System.out.println(delete);
			Common.assertionCheckwithReport(delete.contains("You deleted the category."),
					"Validating the User need to save the page", "User should able to save the page",
					"Sucessfully User saves the page", "Unable to save the page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating the User need to delete the page", "User should able to delete the page",
					"Sucessfully User delete the page", Common.getscreenShotPathforReport("Failed to delete the page"));
			Assert.fail();

		}
	}

	public void delete_ExistingCategory(String Dataset) {
		String category = data.get(Dataset).get("NewMaincategory");
//    		String shop =  data.get(Dataset).get("Shop");
		try {
			Sync.waitElementClickable("id", "expandAll");
			Common.scrollIntoView("id", "expandAll");
			Common.javascriptclickElement("id", "expandAll");
			Sync.waitPageLoad();
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='loader' and @style='display: none;']");

			int catgoriesexpand = Common.findElements("xpath", "//ul[@class='x-tree-node-ct' and @style='']").size();
			int existingcategory = Common.findElements("xpath", "//span[contains(text(),'" + category + "')]").size();

			if (catgoriesexpand > 18 && existingcategory > 0) {
				System.out.println("Categories expanded");
				Sync.waitElementClickable("xpath", "//span[contains(text(),'" + category + "')]");
				Common.scrollIntoView("xpath", "//span[contains(text(),'" + category + "')]");
				Common.javascriptclickElement("xpath", "//span[contains(text(),'" + category + "')]");

				Sync.waitPageLoad();
				Sync.waitElementVisible("xpath", "//h1[@class='page-title']");
				String categoryname = Common.findElement("xpath", "//h1[@class='page-title']").getText();
				if (categoryname.contains(category) && Common.getPageTitle().contains(category)) {

					Common.assertionCheckwithReport(
							categoryname.contains(category) && Common.getPageTitle().contains(category),
							"To validate the admin is on Existing category page",
							"Admin should be on Existing category page", "Admin is on Existing category page",
							"failed to land on the existing category page");
					Thread.sleep(15000);
					Sync.waitElementClickable("xpath", "//span[text()='Delete']");
					Common.javascriptclickElement("xpath", "//span[text()='Delete']");
					Sync.waitElementVisible("xpath", "//div[text()='Are you sure you want to delete this category?']");
					int modalpopup = Common
							.findElements("xpath", "//div[text()='Are you sure you want to delete this category?']")
							.size();

					if (modalpopup > 0) {
						Sync.waitElementClickable("xpath", "//span[text()='OK']");
						Common.javascriptclickElement("xpath", "//span[text()='OK']");
						Sync.waitPageLoad();
					} else {
						System.out.println("Modal popup not dispalyed");
						Assert.fail();
					}

					Sync.waitElementVisible("xpath", "//div[contains(@class,'message-success')]/div");
					String deletesuccessmessage = Common
							.findElement("xpath", "//div[contains(@class,'message-success')]/div").getText();

					Common.assertionCheckwithReport(deletesuccessmessage.contains("You deleted the category."),
							"To validate the page is successfully deleted", "Page should be deleted successfully",
							"Successfully navigate to the edit page builder field ",
							"Failed to navigate to the edit page builder field");

				} else {
					System.out.println("The admin is not on the selected page");
					Assert.fail();
				}

			} else {
				System.out.println("Categories not expanded or the existing new category is not present");

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the existing test sub category is deleted",
					"Existing test sub category should be deleted", "Failed to delete the Existing sub catgeory",
					Common.getscreenShotPathforReport("Existing sub category deletion unsuccessfull"));
			Assert.fail();
		}

	}
	public void Click_Defaultcategory(String DataSet) {
		String Category = data.get(DataSet).get("DefaultCategory ");
		try {
			Sync.waitElementVisible("xpath", "//span[contains(text(),'" + Category + "')]");
			Common.clickElement("xpath", "//span[contains(text(),'" + Category + "')]");
			Common.clickElement("xpath", "//span[contains(text(),'" + Category + "')]");
			Sync.waitPageLoad();
			String Defaultategory_Page = Common.findElement("xpath", "//h1[@class='page-title']").getText();

			System.out.println(Defaultategory_Page);

			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("Default") && Defaultategory_Page.contains("Default"),
					"To validate the Admin is on Default category page", "Admin should be on Default category page",
					"Admin is on Test category page", "Admin Failed to land on the Test category");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void Create_NewMaincategory(String dataSet) {
		// TODO Auto-generated method stub

		String NewMaincategory = data.get(dataSet).get("NewMaincategory");

		try {

			Sync.waitPageLoad();
			Common.clickElement("xpath", "//span[text()='Add Subcategory']");
			Common.textBoxInput("name", "name", data.get(dataSet).get("NewMaincategory"));

			Thread.sleep(5000);
			Common.clickElement("xpath", "//span[text()='Save']");

			String successmessage = Common.findElement("xpath", "//div[@class='message message-success success']")
					.getText();
			Thread.sleep(2000);
			String Newcategory_Page = Common.findElement("xpath", "//h1[@class='page-title']").getText();
			Common.assertionCheckwithReport(
					Newcategory_Page.contains(NewMaincategory) && successmessage.contains("You saved the category."),
					"To validate the admin has created a new category", "Admin should  create a new Category",
					"Successfully cretaed a new category", "Failed to create a new  category");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the admin has created a new category",
					"Admin should  create a new Category", "Failed to create a new category",
					Common.getscreenShotPathforReport("new category creation un successfull"));
			Assert.fail();
		}
	}

	public void Create_Newcategory(String dataSet) {
		// TODO Auto-generated method stub

		String Newcategory = data.get(dataSet).get("Newcategory");

		try {

			Sync.waitPageLoad();
			Common.clickElement("xpath", "//span[text()='Add Subcategory']");
			Common.textBoxInput("name", "name", data.get(dataSet).get("Newcategory"));

			Thread.sleep(5000);
			Common.clickElement("xpath", "//span[text()='Save']");

			String successmessage = Common.findElement("xpath", "//div[@class='message message-success success']")
					.getText();
			Thread.sleep(2000);
			String Newcategory_Page = Common.findElement("xpath", "//h1[@class='page-title']").getText();
			Common.assertionCheckwithReport(
					Newcategory_Page.contains(Newcategory) && successmessage.contains("You saved the category."),
					"To validate the admin has created a new category", "Admin should  create a new Category",
					"Successfully cretaed a new category", "Failed to create a new  category");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the admin has created a new category",
					"Admin should  create a new Category", "Failed to create a new category",
					Common.getscreenShotPathforReport("new category creation un successfull"));
			Assert.fail();
		}
	}

	public void Create_Newsubcategory(String dataSet) {
		// TODO Auto-generated method stub
		String Newsubcategory = data.get(dataSet).get("Newsubcategory");
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//span[text()='Add Subcategory']");
			Common.textBoxInput("name", "name", data.get(dataSet).get("Newsubcategory"));
			Thread.sleep(5000);
			Common.clickElement("xpath", "//span[text()='Save']");

			String successmessage = Common.findElement("xpath", "//div[@class='message message-success success']")
					.getText();
			Thread.sleep(2000);
			String Newsubcategory_Page = Common.findElement("xpath", "//h1[@class='page-title']").getText();
			Common.assertionCheckwithReport(
					Newsubcategory_Page.contains(Newsubcategory) && successmessage.contains("You saved the category."),
					"To validate the admin has created a new sub category", "Admin should  create a new sub  Category",
					"Successfully cretaed a new sub category", "Failed to create a new sub category");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the admin has created a new sub category",
					"Admin should  create a new sub  Category", "Failed to create a new sub category",
					Common.getscreenShotPathforReport("new sub category creation successfull"));
			Assert.fail();
		}
	}

	public void Validate_Category_subcategory_frontend(String dataSet) {
		// TODO Auto-generated method stub
		String Newcategory = data.get(dataSet).get("Newcategory");
		String Newsubcategory = data.get(dataSet).get("Newsubcategory");
		String Updatesubcategory = data.get(dataSet).get("Updatesubcategory");

		try {
			Sync.waitPageLoad();
			Thread.sleep(2000);
			Common.refreshpage();
			Common.mouseOverClick("xpath", "(//a[@class='level-top ui-corner-all'])[6]");
			Common.mouseOverClick("xpath", "//span[contains(text(),'" + Newcategory + "')]");

			String website_subcategory = Common
					.findElement("xpath", "//span[contains(text(),'" + Newsubcategory + "')]").getText();

			System.out.println(website_subcategory);

			Common.assertionCheckwithReport(website_subcategory.contains("TEST SUBCATEGORY"),
					"To validate the New category and sub category on the store front",
					"New category and sub category should be reflected on the front end",
					"New category and sub category reflected on the store front",
					"Failed to cretae New category and sub category");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the New category and sub category on the store front",
					"New category and sub category should be reflected on the front end",
					"New category and sub category reflect on the store front unsuccesssfull",
					Common.getscreenShotPathforReport("Failed to cretae New category and sub category"));
			Assert.fail();
		}
	}

	
	public void ValidateScedulecategoryupdate(String string) {
		try {
			Common.clickElement(By.xpath("//span[text()=' Test Subcategory']"));
			Sync.waitPageLoad();
			Sync.waitElementVisible("xpath", "//img[@title='Test Subcategory']");
			String heroimage = Common.findElement("xpath", "//img[@title='Test Subcategory']").getAttribute("src");
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("//span[text()=' Test Subcategory']")
							&& heroimage.contains("Screenshot_2.png"),
					"Validating schedule catgeory update frontend",
					"schedule catgeory update on the frontend should be updated",
					"schedule catgeory update is updated on the frontend", "failed schedule category update");

		} catch (Exception e) {

			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("Validating schedule catgeory update frontend",
					"schedule catgeory update on the frontend should be updated",
					"schedule catgeory update is not updated on the frontend",
					Common.getscreenShotPathforReport("Failed to schedule the catgeory update"));

			Assert.fail();

		}

	}

	
	
	public void Validate_Category_Updatesubcategory_frontend(String dataSet) {
		// TODO Auto-generated method stub
		String Newcategory = data.get(dataSet).get("Newcategory");
		String Newsubcategory = data.get(dataSet).get("Newsubcategory");
		String Updatesubcategory = data.get(dataSet).get("Updatesubcategory");

		try {
			Sync.waitPageLoad();
			Thread.sleep(2000);
			Common.refreshpage();
			Common.mouseOverClick("xpath", "(//a[@class='level-top ui-corner-all'])[6]");
			Common.mouseOverClick("xpath", "//span[contains(text(),'" + Newcategory + "')]");

			String website_Updatesubcategory = Common
					.findElement("xpath", "//span[contains(text(),'" + Updatesubcategory + "')]").getText();

			System.out.println(website_Updatesubcategory);

			Common.assertionCheckwithReport(website_Updatesubcategory.contains("UPDATESUBCATEGORY"),
					"To validate the New category and sub category on the store front",
					"New category and sub category should be reflected on the front end",
					"New category and sub category reflected on the store front",
					"Failed to cretae New category and sub category");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the New category and sub category on the store front",
					"New category and sub category should be reflected on the front end",
					"New category and sub category reflect on the store front unsuccesssfull",
					Common.getscreenShotPathforReport("Failed to cretae New category and sub category"));
			Assert.fail();
		}
	}

	public void Configure_scheduleupdate(String DataSet) {
		try {
			Common.switchToFirstTab();
			Sync.waitElementVisible("id", "staging_update_new");
			Common.javascriptclickElement("id", "staging_update_new");
			Sync.waitElementClickable("xpath", "//div[contains(@class,'fieldset-schedule')]");
			String modalpopup = Common.findElement("xpath", "//aside[contains(@class,'modal-slide scheduled')]")
					.getAttribute("class");

			Common.assertionCheckwithReport(modalpopup.contains("show"),
					"To validate the Schedule modal popup is displayed", "The Schedule modal popup should be displayed",
					"Schedule modal popup is displayed", "Schedule modal popup is not displayed");

			Common.textBoxInput("name", "staging[name]", "Testsceduledupdate");
			Common.textBoxInput("name", "staging[description]", "this update is for testing");

			Calendar calendar = Calendar.getInstance();

			int DD = calendar.getTime().getDate();
			int MM = calendar.getTime().getMonth() + 1;
			int YY = calendar.getTime().getYear() + 1900;
			int HH = calendar.getTime().getHours();
			int strtmin = calendar.getTime().getMinutes() + 4;
			int Closemin = calendar.getTime().getMinutes() + 8;

			String Strttime = MM + "/" + DD + "/" + YY + " " + HH + ":" + strtmin;
			String closetime = MM + "/" + DD + "/" + YY + " " + HH + ":" + Closemin;
			System.out.println(Strttime);
			System.out.println(closetime);
			Common.textBoxInput("name", "staging[start_time]", Strttime);
			Common.textBoxInput("name", "staging[end_time]", closetime);

			Common.scrollIntoView("xpath", "(//div[@data-index='content'])[2]/div");
			Common.clickElement("xpath", "(//div[@data-index='content'])[2]/div");
			String showcontent = Common.findElement("xpath", "(//div[@data-index='content'])[2]/div")
					.getAttribute("data-state-collapsible");
			Common.assertionCheckwithReport(showcontent.contains("open"), "To validate the Content opions is displayed",
					"The content options should be displayed", "The content options is displayed",
					"The Content options are not displayed");

			String path = System.getProperty("user.dir")
					+ ("\\src\\test\\resources\\TestData\\Osprey_Admin\\Lotusqa.png");
			Sync.waitElementPresent(40, "xpath", "(//input[@type='file'])[1]");
			Common.findElement("xpath", "(//input[@type='file'])[1]").sendKeys(path);
			Common.textBoxInput("name", "category_color_picker", "testingQACOlorpicker");
			Common.dropdown("name", "category_hero_block", SelectBy.TEXT, "Footer Osprey");
			Common.textBoxInput("name", "short_description", "This content id for testing");

			Sync.waitElementPresent("xpath", "//label[text()='Select from Gallery']");
			Common.javascriptclickElement("xpath", "//label[text()='Select from Gallery']");

			Sync.waitElementInvisible("xpath", "//div[@class='loading-mask' and @style='display: none;']");
			Sync.waitElementVisible("id", "contents");
			// Common.isElementVisibleOnPage(30, "xpath", "//div[@id='contents']");
			String image = "hiking-cat-sm-bg.png"; // data.get(DataSet).get("");
			Common.scrollIntoView("xpath", "//small[text()='" + image + "']");
			Common.javascriptclickElement("xpath", "//small[text()='" + image + "']");
			Sync.waitElementPresent("xpath", "//span[text()='Add Selected']");
			Common.javascriptclickElement("xpath", "//span[text()='Add Selected']");
			Common.scrollIntoView("xpath", "(//a/img[@class='preview-image'])[1]");
			String imagename = Common.findElement("xpath", "(//a/img[@class='preview-image'])[1]").getAttribute("alt");
			System.out.println(imagename);
			Common.assertionCheckwithReport(imagename.equals(image),
					"validation the image uploading on content for Fallback image ",
					"Image should be upload for background image", "Successfully image uploaded in background image ",
					"Failed to upload image on the background image");

			Common.scrollIntoView("xpath", "//div[contains(@class,'floating-header')]/button[@id='save']");
			Common.javascriptclickElement("xpath", "//div[contains(@class,'floating-header')]/button[@id='save']");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Configure a Schedule category update in the admin panel",
					"Scedule catgeory Should be configured in admin panel",
					"Scedule catgeory is not configured in admin panel",
					"Failed to schedule the category in the Admin panel");
			Assert.fail();
		}

	}
	public void Update_SubCategory(String dataSet) {
		String Newcategory = data.get(dataSet).get("Newcategory");
		String Updatesubcategory = data.get(dataSet).get("Updatesubcategory");
		String Newsubcategory = data.get(dataSet).get("Newsubcategory");

		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//span[contains(text(),'" + Newcategory + "')]");
			Sync.waitPageLoad();
			Sync.waitElementClickable("xpath", "//span[contains(text(),'" + Newsubcategory + "')]");
			Common.clickElement("xpath", "//span[contains(text(),'" + Newsubcategory + "')]");
			Common.textBoxInput("name", "name", data.get(dataSet).get("Updatesubcategory"));
			Thread.sleep(5000);
			Common.clickElement("xpath", "//span[text()='Save']");
			String successmessage = Common.findElement("xpath", "//div[@class='message message-success success']")
					.getText();
			Thread.sleep(2000);
			String Updatesubcategory_Page = Common.findElement("xpath", "//h1[@class='page-title']").getText();
			Common.assertionCheckwithReport(
					Updatesubcategory_Page.contains(Updatesubcategory)
							&& successmessage.contains("You saved the category."),
					"To validate the Updated category is refelected on the store front",
					"Updated category should be reflected on the store front",
					"Updated category is reflected on the store front",
					"Failed to reflect the category on the store front");

			Thread.sleep(3000);
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Updated category is refelected on the store front",
					"Updated category should be reflected on the store front",
					"Failed to update the category on the store front",
					Common.getscreenShotPathforReport("category update on the store front is unsuccessfull"));
			Assert.fail();
		}
	}

	public void Flush_Magneto_cache(String Dataset) {
		// TODO Auto-generated method stub
		String Magento = data.get(Dataset).get("cache");
		try {
			Sync.waitElementPresent("xpath", "//a//span[text()='System']");
			Common.clickElement("xpath", "//a//span[text()='System']");
			Sync.waitElementPresent("xpath", "//a//span[text()='Cache Management']");
			Common.clickElement("xpath", "//span[text()='Cache Management']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Cache Management"),
					"validating the page navigates to the cache management",
					"It should Navigates to the Cache management page",
					"Sucessfully it is navigated to the cache managment page",
					"failed to Navigate to the cache managment page");
			Sync.waitElementPresent("xpath", "//button[@title='" + Magento + "']");
			Common.clickElement("xpath", "//button[@title='" + Magento + "']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String Cachemessage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']")
					.getText();
			Common.assertionCheckwithReport(Cachemessage.equals("The Magento cache storage has been flushed."),
					"validating the cache management sucess message",
					"cache message should be display after clicking on the flush magento",
					"Sucessfullythe message has been displayed after clicking on the flush magneto",
					"failed to display the sucess message after clicking on the flush magneto");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the cache management sucess message",
					"cache message should be display after clicking on the flush magento",
					"unable to display the sucess message after clicking on the flush magneto",
					Common.getscreenShot("failed to display the sucess message after clicking on the flush magneto"));
			Assert.fail();
		}

	}
	public void Validate_deletcategory_Frontend(String dataSet) {
		// TODO Auto-generated method stub
		String Newcategory = data.get(dataSet).get("Newcategory");
		String Newsubcategory = data.get(dataSet).get("Newsubcategory");
		String Category = data.get(dataSet).get("NewMaincategory");

		try {
			Thread.sleep(3000);
			Common.refreshpage();

			int testcategory = Common.findElements("xpath", "//span[contains(text(),'" + Category + "')]").size();
			Thread.sleep(2000);
			System.out.println(testcategory);
			Common.assertionCheckwithReport(testcategory <= 0,
					"To validate the category on the store front is not displayed",
					"Category should not be dispalyed on the store fornt", "Catgeory is deleted successfully",
					"Failed to create new category on the store front");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the category on the store front is not displayed",
					"Category should not be dispalyed on the store fornt",
					"The test category is not deleted form the shop menu",
					Common.getscreenShotPathforReport("Failed to delete the category on the front end"));
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
				Common.assertionCheckwithReport(catalogmenu.contains("show"), "To Validate the catalog menu is displayed",
						"should display the catalog menu after clicking on the catalog",
						"catalog menu is displayed after a click on the catalog button", "Failed to display catalog menu");

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
					Common.assertionCheckwithReport(
							Common.getPageTitle().contains("Products / Inventory / Catalog / Magento Admin"),
							"To Validate the Catalogmenu is displayed",
							"should display the Catalogmenu after clicking on the customers",
							"Catalog menu is displayed after a click on the Catalog button", "Failed to display Catalogmenu");

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

//			         Common.findElement("xpath", "//input[@placeholder='Search by keyword']").sendKeys(pagename);
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
						ExtenantReportUtils.addFailedLog("To Validate the search filters", "should display the Search filter",
								"unable to display Search filte", "Failed to display Search filte");
						Assert.fail();
					}

				}

				public void Click_SearchProduct() {
					try {

						Thread.sleep(2000);
						Common.clickElement("xpath", "//tr[contains(@class,'data-row')]");
						Thread.sleep(5000);

						// Sync.waitElementInvisible(30, "xpath", "//div[@class='page-title-wrapper
						// complex']");
						Common.assertionCheckwithReport(
								Common.getPageTitle().contains("Products / Inventory / Catalog / Magento Admin"),
								"To Validate the QATEST product is displayed",
								"should display the QATEST product after clicking on the prouct",
								"QATEST product is displayed after a click on the product", "Failed to display QATEST product");

					} catch (Exception | Error e) {
						e.printStackTrace();
						ExtenantReportUtils.addFailedLog("To Validate the QATEST product is displayed",
								"should display the QATEST product after clicking on the product",
								"unable to display QATEST productafter a click on the product", "Failed to display QATEST product");
						Assert.fail();
					}
				}
				public void PaginationCTA() {
					// TODO Auto-generated method stub
					try {
						Common.clickElement("xpath", "//button[@title='Next Page']");
						String pagination = Common.findElement("xpath", "//button[@title='Next Page']").getAttribute("class");
						Common.assertionCheckwithReport(pagination.contains("next"),
								"Validating the pagination CTA on customers page",
								"Able to display the pagination CTA on customers page",
								"Successfully Dispaly Pageination CTA on customers page",
								"Failed to Display Pagination CTA on customers page");
						Common.clickElement("xpath", "//button[@title='Previous Page']");

					} catch (Exception | Error e) {
						e.printStackTrace();
						ExtenantReportUtils.addFailedLog("Validating the pagination CTA on customers page ",
								"Able to display the pagination CTA on customers page",
								"unable to Display Pageination CTA on customers page",
								Common.getscreenShotPathforReport("Failed to Display Pagination CTA on customers page"));
						Assert.fail();

					}

				}
					public void Click_Edit() {
						// TODO Auto-generated method stub
						String expectedResult= "to edit and navigate to next page";

						try {
							Thread.sleep(5000);
						        Sync.waitElementPresent("xpath", "//a[text()='Edit']");
							Common.clickElement("xpath", "//a[text()='Edit']");
							Thread.sleep(6000);
							
							int pages = Common.findElements("xpath", "//h1[text()='AETHER™ 55']").size();
							System.out.println(pages);
							
							Common.assertionCheckwithReport(
									Common.getPageTitle().contains("AETHER™ 55 / Products / Inventory / Catalog / Magento Admin"),
									"To Validate the AETHER™ 55 product is Edit",
									"should display the AETHER™ 55 product Edit page",
									"AETHER™ 55 product name displayed after a click on the edit", "Failed to display AETHER™ 55 product edit page");
							
					}

						catch (Exception | Error e) {
							e.printStackTrace();
				ExtenantReportUtils.addFailedLog("To validate the AETHER™ 55 product Edit page",
										"Should display AETHER™ 55 product Edit page rule", "Failed to Navigate to edit page", "Failed to display AETHER™ 55 product edit page ");
							Assert.fail();

						}
					}
					

					
					public void Product_listing_page_size(String dataSet) {
						try {

			
							Common.clickElement("xpath", "//div[@class='selectmenu']");
							Thread.sleep(5000);
							Common.clickElement("xpath", "(//li//button[text()='Custom'])[2]");
							Thread.sleep(5000);
						//	Common.clickElement("xpath", "(//div//input[contains(@data-bind,'hasFocus: isCustomVisible(), ')])[2]");
							Common.textBoxInput("xpath", "(//div//input[contains(@data-bind,'hasFocus: isCustomVisible(), ')])[2]", data.get(dataSet).get("number"));
							Thread.sleep(2000);
							Common.actionsKeyPress(Keys.ENTER);

							
						Common.assertionCheckwithReport(
									Common.getPageTitle().contains("Products / Inventory / Catalog / Magento Admin"),
									"To Validate the product listing page size",
									"should display the product listing page size after clicking on the prouct listing page size num",
									"product listing page size is displayed after a click on the product page size num", "Failed to display product listing page size");

						} catch (Exception | Error e) {
							e.printStackTrace();
							ExtenantReportUtils.addFailedLog("To Validate the product listing page size is displayed",
									"should display the product listing page size after selecting on the product page size num",
									"unable to display product listing page size after a click on the product page sizs num", "Failed to display product listing page size");
							Assert.fail();
						}
					}
						
						public void Click_Addproduct() {
							try {
								
								Common.findElement("xpath", "//span[text()='Add Product']");
								Common.clickElement("xpath", "//button[contains(@data-ui-id,'products-list-add-new-product-button-')]");
								Thread.sleep(5000);
								Common.clickElement("xpath", "//span[text()='Simple Product']");
								
								Common.assertionCheckwithReport(
										Common.getPageTitle().contains("New Product / Products / Inventory / Catalog / Magento Admin"),
										"To Validate Add product page",
										"should display the product page after clicking on the Add prouct dropdown",
										"Add product page is displayed after a click on the Add product dropdown", "Failed to display Add product page");

							} catch (Exception | Error e) {
								e.printStackTrace();
								ExtenantReportUtils.addFailedLog("To Validate Add product page",
										"should display the product page after clicking on the Add prouct dropdown",
										"unable to display Add product page after click on the Add product dropdown", "Failed to display Add product page");
								Assert.fail();
								
							}
						}
						public void Add_New_product(String dataSet) {
							try {
								Common.textBoxInput("xpath", "//div//input[@name='product[name]']", data.get(dataSet).get("Productname"));
								Common.textBoxInput("xpath", "//div//input[@name='product[sku]']", data.get(dataSet).get("SKU"));
								Common.textBoxInput("xpath", "//div//input[@name='product[price]']", data.get(dataSet).get("Price"));
								Common.textBoxInput("xpath", "//div//input[contains(@name,'product[quantity')]", data.get(dataSet).get("Product quantity"));
								
								Common.clickElement("xpath", "(//div[text()='Select...'])[1]");
							//	Common.clickElement("xpath", "//div[@id='D3HPRRI']");
								Common.clickElement("xpath", "//label[text()='Test Category']");
								Common.clickElement("xpath", "//div//button[@class='action-default']");
								Common.clickElement("xpath", "//select[@name='product[customizable]']");
								Common.clickElement("xpath", "//option[@data-title='TEST-Option']");
								Common.textBoxInput("xpath", "//input[@name='product[customize_sku]']", data.get(dataSet).get("Customazition SKU"));
								Common.textBoxInput("xpath", "//input[@name='product[engrave_sku]']", data.get(dataSet).get("Engraving SKU"));
						
								Common.clickElement("xpath", "//button//span[text()='Save']");
								 String Saveproduct = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']")
						                    .getText();
								
								 Common.assertionCheckwithReport(Saveproduct.contains("You saved the product."),
										"To Validate Add new product page",
										"should display the Added product after saving the product",
										"Add product Should display in product page", "Failed to display saved new product");

							} catch (Exception | Error e) {
								e.printStackTrace();
								ExtenantReportUtils.addFailedLog("To Validate Add new product page",
										"should display the Added product after saving the product",
										"unable to display Add new product in product listing page", "Failed to display Add new product");
								Assert.fail();
							}
						}
						
						public void product_Reindex(String dataSet) {
							
						try {	
							Common.clickElement("xpath", "//a//span[text()='Stores']");
							 Sync.waitElementPresent("xpath", "//a//span[text()='Reindex SKU(s)']");
							Common.clickElement("xpath", "//a//span[text()='Reindex SKU(s)']");
							Common.clickElement("xpath", "//textarea[@name='skus']");
							Common.textBoxInput("xpath", "//textarea[@name='skus']", data.get(dataSet).get("SKU"));
							Common.clickElement("xpath", "//button//span[text()='Reindex SKU(s)']");
							
							 String Reindex = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']")
					                    .getText();
							
							  Common.assertionCheckwithReport(Reindex.contains("has been reindexed for store \"Osprey UK / Osprey UK Store / Osprey UK English Store"),
					                    "validating the Reindex sucess message",
					                    "success message should be display after clicking on the Reindex SKU",
					                    "Sucessfully the message has been displayed after clicking on Reindex SKU",
					                    "failed to display the sucess message after clicking on the Reindex SKU");
						}  catch (Exception | Error e) {
				            e.printStackTrace();
				            ExtenantReportUtils.addFailedLog("validating the Reindex SKU sucess message",
				                    "cache message should be display after clicking on Reindex SKU",
				                    "unable to display the sucess message after clicking on the Reindex SKU",
				                    Common.getscreenShot("failed to display the sucess message after clicking on Reindex SKU"));
				            Assert.fail();
							
							
						}
						
					}
						public void Cache_Management(String dataSet) {
						// TODO Auto-generated method stub
				        String Magento = data.get(dataSet).get("cache");
				        try {
				            Sync.waitElementPresent("xpath", "//a//span[text()='System']");
				            Common.clickElement("xpath", "//a//span[text()='System']");
				            Sync.waitElementPresent("xpath", "//a//span[text()='Cache Management']");
				            Common.clickElement("xpath", "//a//span[text()='Cache Management']");
				            Sync.waitPageLoad();
				            Thread.sleep(4000);
				            Common.assertionCheckwithReport(Common.getPageTitle().contains("Cache Management"),
				                    "validating the page navigates to the cache management",
				                    "It should Navigates to the Cache management page",
				                    "Sucessfully it is navigated to the cache managment page",
				                    "failed to Navigate to the cache managment page");
				          //  Sync.waitElementPresent("xpath", "//button[@title='" + Magento + "']");
				            Common.clickElement("xpath", "//button[@id='flush_magento']");
				            Sync.waitPageLoad();
				            Thread.sleep(4000);
				            String Cachemessage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']")
				                    .getText();
				            Common.assertionCheckwithReport(Cachemessage.equals("The Magento cache storage has been flushed."),
				                    "validating the cache management sucess message",
				                    "cache message should be display after clicking on the flush magento",
				                    "Sucessfullythe message has been displayed after clicking on the flush magneto",
				                    "failed to display the sucess message after clicking on the flush magneto");

				 

				        } catch (Exception | Error e) {
				            e.printStackTrace();
				            ExtenantReportUtils.addFailedLog("validating the cache management sucess message",
				                    "cache message should be display after clicking on the flush magento",
				                    "unable to display the sucess message after clicking on the flush magneto",
				                    Common.getscreenShot("failed to display the sucess message after clicking on the flush magneto"));
				            Assert.fail();
				        }

				 

				    }
					
						public void Delete_product(String dataset) {
							String product = data.get(dataset).get("Productname");
							System.out.println(product);
							
							try {	
								Common.clickElement("xpath", "//label[contains(@class,'data-grid-check')]");
						
								String Adproduct = Common.findElement("xpath", "(//td//div[@class='data-grid-cell-content white-space-preserved'])[1]").getText();
								String records = Common.findElement("xpath",  "//div[@class='admin__control-support-text']").getText();
								if ((Adproduct.equals(product)) && (records.contains("1 records found"))){
								//	Common.clickElement("xpath", "//label[contains(@class,'data-grid-check')]");
									Common.clickElement("xpath", "//button[@class='action-select']//span[text()='Actions']");
									Common.clickElement("xpath", "//li//span[text()='Delete']");
								} 
								else  {
									Assert.fail();
								}
								Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
								
								 String Successmessage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']")
						                    .getText();
								
								  Common.assertionCheckwithReport(Successmessage.contains("A total of 1 record(s) have been deleted."),
						                    "validating the sucess message for delete product",
						                    "success message should be display after deleting the product",
						                    "Sucessfully the message has been displayed after deleting the product",
						                    "failed to display the sucess message after deleting the product");
							}  catch (Exception | Error e) {
					            e.printStackTrace();
					            ExtenantReportUtils.addFailedLog("validating the sucess message for delete product",
					                    "success message should be display after deleting the product",
					                    "unable to display the sucess message after deleting the product",
					                    Common.getscreenShot("failed to display the sucess message after deleting the product"));
					            Assert.fail();
							}}
								
								
							

						
						public void Backto_magento_Cache_admin() {
							try {

								Common.switchToFirstTab();
								Thread.sleep(2000);

								// String text=Common.getText("xpath", "//h1[@class='page-title']");
								String text = Common.getPageTitle();
								

								System.out.println(text);
								Thread.sleep(2000);


								  Common.assertionCheckwithReport(text.contains("Cache Management / Tools / System / Magento Admin"),
						                    "validating the switchtofirsttab",	
										"should display the pagetitle after clicking on the switchtofirsttab",
										"pagetitle is displayed after a click on the switchtofirsttab", "Failed to display pagetitle");

							} catch (Exception | Error e) {
								e.printStackTrace();
								ExtenantReportUtils.addFailedLog("To Validate the pagetitle is displayed",
										"should display the pagetitle after clicking on the switchtofirsttab",
										"unable to display pagetitle after a click on the switchtofirsttab", "Failed to display pagetitle");
								Assert.fail();
							}

						


						}
						public void search_addAdminproduct(String dataset) {
							// TODO Auto-generated method stub
							String product = data.get(dataset).get("Productname");
							System.out.println(product);
							try {
								Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
								String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
								Thread.sleep(4000);
								Common.assertionCheckwithReport(open.contains("active"), "User searches using the search field",
										"User should able to click on the search button", "Search expands to the full page",
										"Sucessfully search bar should be expand");
								Common.textBoxInput("xpath", "//input[@type='search']", data.get(dataset).get("Productname"));
								Common.actionsKeyPress(Keys.ENTER);
								Sync.waitPageLoad();
								Thread.sleep(4000);
								String searchError = Common.findElement("xpath", "//span[@class='js-search-query-no-results']").getText();
								System.out.println(searchError);
								Common.assertionCheckwithReport(searchError.contains("Lotustest"), "validating the search functionality",
										"enter product name will display in the search box", "user enter the product name in  search box",
										"Failed to see the product name");
								Thread.sleep(8000);

							} catch (Exception | Error e) {
								e.printStackTrace();
								ExtenantReportUtils.addFailedLog("validating the search functionality",
										"enter product name will display in the search box",
										" unable to enter the product name in  search box",
										Common.getscreenShot("Failed to see the product name"));
								Assert.fail();
							}
							
						}
						public void Adds_Related_Product(String dataset) {
							
							try {
								Common.actionsKeyPress(Keys.UP);
								Common.clickElement("xpath", "//span[contains(text(),'Related Products,')]");
								String open = Common.findElement("xpath", "//span[text()='Related Products']").getAttribute("text");
								Thread.sleep(4000);
								System.out.println( open);
								
								
							//	Common.assertionCheckwithReport(open.contains("Related"), "User should open the Releted Product dropdown",
							//			"User should able to click on  Releted Product dropdown", "expands to  Releted Product dropdown ",
							//			"Sucessfully  expand Releted Product dropdown ");
								Common.clickElement("xpath", "//button//span[text()='Add Related Products']");
								String text = Common.getPageTitle();
								Common.assertionCheckwithReport(text.contains("AETHER™ 55 / Products / Inventory / Catalog / Magento Admin"),
					                    "validating  Add Releated products grid",	
									"should display Add Releated products grid after clicking on Add Releated products",
									"pagetitle is displayed after a clicking on Add Releated products grid", "Failed to display Add Releated products grid");
							
								Common.findElement("xpath", "//div[text()='POCO® CARRYING CASE']");
								Common.clickElement("xpath", "//input[@id= 'idscheck19']");
								
								Common.clickElement("xpath", "//div//span[text()='Add Selected Products']");
								String addproduct = Common.getPageTitle();
								Common.clickElement("xpath", "//button[@id='save-button']");
								Sync.waitPageLoad();
								Thread.sleep(4000);
								String Successmessage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
								System.out.println( Successmessage);
								Common.assertionCheckwithReport( Successmessage.contains("You saved the product."), "validating the sucess message for saved product",
									"success message should be display after Adding the product ", "Sucessfully the message has been displayed after saving the product",
										"Failed to display the sucess message after saving the product");
								Thread.sleep(8000);

							} catch (Exception | Error e) {
					            e.printStackTrace();
					            ExtenantReportUtils.addFailedLog("validating the sucess message for Saved product",
					                    "success message should be display after saving the product",
					                    "unable to display the sucess message after saving the product",
					                    Common.getscreenShot("failed to display the sucess message after deleting the product"));
					            Assert.fail();
							}
							
						}
						public void PDP_Releted_search(String dataset) {

						    // TODO Auto-generated method stub

							String product = data.get(dataset).get("Productname");
							System.out.println(product);
						    try {
						        Sync.waitPageLoad();
						        for (int i = 0; i <= 10; i++) {
						            Sync.waitElementPresent("xpath", "//img[contains(@class,'m-product-card__image')]");
						            List<WebElement> webelementslist = Common.findElements("xpath",
						                    "//img[contains(@class,'m-product-card__image')]");
	                          String s = webelementslist.get(i).getAttribute("src");

						            System.out.println(s);

						            if (s.isEmpty()) {

						            } else {

						                break;

						            }

						        }

						        Sync.waitPageLoad(30);

						        Thread.sleep(6000);

						        Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product + "']");

						        Common.clickElement("xpath", "//img[@alt='" + product + "']");
					        Sync.waitPageLoad(30);
	                        Thread.sleep(6000);
						        Sync.waitElementVisible(30, "xpath", "//div[@class='m-product-overview__info-top']//h1");
						        String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
					        Common.assertionCheckwithReport(name.contains(product), "validating the  product navigates to PDP page",

						                "It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",

						                "failed to Navigate to the PDP page");
						        Sync.waitPageLoad();
						        Thread.sleep(4000);
						       // Common.actionsKeyPress(Keys.DOWN);
						        Common.scrollIntoView("xpath", "//h2[text()='Compare Similar Products']");
						      //  Common.findElement("xpath", "//h2[text()='Compare Similar Products']");
						      
						        
						        String message = Common.findElement("xpath", "//a[@title='POCO® CARRYING CASE']")

						                .getAttribute("text");
						        Common.assertionCheckwithReport(message.contains("POCO® CARRYING CASE"), "Compare Similar Products",

						                "Product should be in Compare Similar Products", " product Should display is in Compare Similar Products ",

						                "failed to see");

						    } catch (Exception | Error e) {

						        e.printStackTrace();

						        ExtenantReportUtils.addFailedLog("validating Compare Similar Products section", "Product should be in Compare Similar Products",

						                "unable to display product in Compare Similar Products", Common.getscreenShot("failed to display product in Compare Similar Products"));

						 

						        Assert.fail();

						    }
							
						}

	        public void delete_Related_Product(String dataset) {
	        	String product = data.get(dataset).get("Productname");
				System.out.println(product);
							
							try {
								Common.actionsKeyPress(Keys.UP);
								Common.clickElement("xpath", "//span[contains(text(),'Related Products,')]");
								String open = Common.findElement("xpath", "//span[text()='Related Products']").getAttribute("text");
								Thread.sleep(4000);
								System.out.println( open);
								String deleteproduct = Common.findElement("xpath",  "(//div//span[text()='POCO® CARRYING CASE'])[1]").getText();
//								String RelatedProduct = Common.findElement("xpath",  "//span[text()='Related Products']").getText();
								if ((deleteproduct.equals(product))){  
									
										Common.clickElement("xpath", "//button[@Class='action-delete']");
										Common.clickElement("xpath", "//button[@id='save-button']");
									} 
									else  {
										Assert.fail();
									}
								String Successmessage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
								System.out.println( Successmessage);
								Common.assertionCheckwithReport( Successmessage.contains("You saved the product."), "validating the sucess message for saved product",
									"success message should be display after Adding the product ", "Sucessfully the message has been displayed after saving the product",
										"Failed to display the sucess message after saving the product");
								Thread.sleep(8000);
								
								
							} catch (Exception | Error e) {
					            e.printStackTrace();
					            ExtenantReportUtils.addFailedLog("validating the sucess message for delete product",
					                    "success message should be display after deleting the product",
					                    "unable to display the sucess message after deleting the product",
					                    Common.getscreenShot("failed to display the sucess message after deleting the product"));
					            Assert.fail();

							 
							}
							

	        }
	    	public void PDP_deleteReleted_search(String dataset) {

			    // TODO Auto-generated method stub

				String product = data.get(dataset).get("Productname");
				System.out.println(product);
			    try {
			        Sync.waitPageLoad();
			        for (int i = 0; i <= 10; i++) {
			            Sync.waitElementPresent("xpath", "//img[contains(@class,'m-product-card__image')]");
			            List<WebElement> webelementslist = Common.findElements("xpath",
			                    "//img[contains(@class,'m-product-card__image')]");
	              String s = webelementslist.get(i).getAttribute("src");

			            System.out.println(s);

			            if (s.isEmpty()) {

			            } else {

			                break;

			            }

			        }

			        Sync.waitPageLoad(30);

			        Thread.sleep(6000);

			        Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product + "']");

			        Common.clickElement("xpath", "//img[@alt='" + product + "']");
		        Sync.waitPageLoad(30);
	            Thread.sleep(6000);
			        Sync.waitElementVisible(30, "xpath", "//div[@class='m-product-overview__info-top']//h1");
			        String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
		        Common.assertionCheckwithReport(name.contains(product), "validating the  product navigates to PDP page",

			                "It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",

			                "failed to Navigate to the PDP page");
			        Sync.waitPageLoad();
			        Thread.sleep(4000);
			      
			        Common.scrollIntoView("xpath", "//h2[text()='Compare Similar Products']");
			        String message = Common.findElement("xpath", "//a[@title='POCO® CARRYING CASE']")

			                .getAttribute("text");
			 
			        if (message.contains(product)) {
						
						
					
						Assert.fail();
					}
			        
			     
			     

			    } catch (Exception | Error e) {

			        e.printStackTrace();

			        ExtenantReportUtils.addFailedLog("validating Compare Similar Products section", "Product should not display in Compare Similar Products",

			                "should to display product in Compare Similar Products", Common.getscreenShot("failed if product is displad in Compare Similar Products"));

			 

			        Assert.fail();

			    }
				
			}
	    	public void Click_Products_Catalogmenu1() {
	    		// TODO Auto-generated method stub
	    		try {
	    			Thread.sleep(2000);
	    			Common.clickElement("xpath", "//li[@data-ui-id='menu-magento-catalog-catalog-products']");
	    			Sync.waitPageLoad();
	    			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
	    			Common.assertionCheckwithReport(
	    					Common.getPageTitle().contains("Products / Inventory / Catalog / Magento Admin"),
	    					"To Validate the Catalogmenu is displayed",
	    					"should display the Catalogmenu after clicking on the customers",
	    					"Catalog menu is displayed after a click on the Catalog button", "Failed to display Catalogmenu");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the Catalogmenu is displayed",
	    					"should display the Catalogmenu after clicking on the Catalog",
	    					"unable to display Catalog menu after a click on the Catalog button",
	    					"Failed to display Catalog menu");
	    			Assert.fail();
	    		}


	    	}
	    	public void Click_SKU(String Dataset) {
	    		// TODO Auto-generated method stub

	    				try {
	    					
	                 Thread.sleep(2000);
	    			Sync.waitElementPresent("xpath", "//input[@name='sku']");
	    			Common.textBoxInput("xpath", "//input[@name='sku']", data.get(Dataset).get("SKU"));
	    			Thread.sleep(2000);
	    			Sync.waitElementPresent("xpath", "//button//span[text()='Apply Filters']");
	                Common.clickElement("xpath", "//button//span[text()='Apply Filters']");
	                Thread.sleep(2000);
	                	String SKU = Common.findElement("xpath", "//ul[@class='admin__current-filters-list']").getText();
	    					System.out.println(SKU);
	    					Common.assertionCheckwithReport(SKU.contains("23164"), " To Validate the selected product SKU page",
	    							"User should sucessfully Apply the ApplyFilters", "Sucessfully User saves the page", "Failed to save the page");
	    					

	    				} catch (Exception e) {
	    					e.printStackTrace();
	    					ExtenantReportUtils.addFailedLog(" To Validate the User needs to save the page",
	    							"User should able to save the page", "Unable to saves the page",
	    							Common.getscreenShotPathforReport("Failed to save the page"));
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
	    	public void Click_Filters() {
	    		try {
	    			Sync.waitPageLoad();
	    			Thread.sleep(2000);
	    			
		    			if (Common.isElementDisplayed("xpath", "//ul[@class='admin__current-filters-list']")) {
	                        Common.javascriptclickElement("xpath", "//button[@class='action-tertiary action-clear']");
	                    }
	    			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
	    			Thread.sleep(5000);

	    			String Filtersmenu = Common.findElement("xpath", "//div[contains(@class,'data-grid-filters-wrap')]").getAttribute("class");
	    			System.out.println(Filtersmenu);
	    		//	Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
	    			Common.assertionCheckwithReport(Filtersmenu.contains("data-grid-filters-wrap"), "To Validate the Filters menu is displayed",
	    					"should display the Filters menu after clicking on the Filters",
	    					"Filters menu is displayed after a click on the Filters button", "Failed to display Filters menu");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the Filters menu is displayed",
	    					"should display the Filters menu after clicking on the Filters",
	    					"unable to display Filters field menu after a click on the Filters button",
	    					"Failed to display Filters field menu");
	    			Assert.fail();
	    		}


	    	}
	    	public void Click_Stores() {
	    		try {
	    			Sync.waitPageLoad();
	    			Thread.sleep(2000);
	    			Common.clickElement("xpath", "//span[text()='Stores']");
	    			Thread.sleep(5000);

	    			String catalogmenu = Common.findElement("xpath", "//li[contains(@class,'active')]").getAttribute("class");
	    			System.out.println(catalogmenu);
	    			Common.assertionCheckwithReport(catalogmenu.contains("active"), "To Validate the catalog menu is displayed",
	    					"should display the catalog menu after clicking on the catalog",
	    					"catalog menu is displayed after a click on the catalog button", "Failed to display catalog menu");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the catalog menu is displayed",
	    					"should display the catalog menu after clicking on the catalog",
	    					"unable to display catalog field menu after a click on the catalog button",
	    					"Failed to display catalog field menu");
	    			Assert.fail();
	    		}


	    	}
	    	public void Click_Products_Storemenu() {
	    		try {
	    			Sync.waitPageLoad();
	    			Thread.sleep(2000);
	    			Common.clickElement("id", "menu-magento-catalog-catalog");
	    			Thread.sleep(5000);

	    			String catalogmenu = Common.findElement("xpath", "//li[contains(@class,'active')]").getAttribute("class");
	    			System.out.println(catalogmenu);
	    			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
	    			Common.assertionCheckwithReport(catalogmenu.contains("show"), "To Validate the catalog menu is displayed",
	    					"should display the catalog menu after clicking on the catalog",
	    					"catalog menu is displayed after a click on the catalog button", "Failed to display catalog menu");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the catalog menu is displayed",
	    					"should display the catalog menu after clicking on the catalog",
	    					"unable to display catalog field menu after a click on the catalog button",
	    					"Failed to display catalog field menu");
	    			Assert.fail();
	    		}


	    	}
	    	public void Click_Addnewattribute() {
	    		try {
	    			Sync.waitPageLoad();
	    			Thread.sleep(2000);
	    			Common.clickElement("id", "menu-magento-catalog-catalog");
	    			Thread.sleep(5000);

	    			String catalogmenu = Common.findElement("xpath", "//li[contains(@class,'active')]").getAttribute("class");
	    			System.out.println(catalogmenu);
	    			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
	    			Common.assertionCheckwithReport(catalogmenu.contains("show"), "To Validate the catalog menu is displayed",
	    					"should display the catalog menu after clicking on the catalog",
	    					"catalog menu is displayed after a click on the catalog button", "Failed to display catalog menu");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the catalog menu is displayed",
	    					"should display the catalog menu after clicking on the catalog",
	    					"unable to display catalog field menu after a click on the catalog button",
	    					"Failed to display catalog field menu");
	    			Assert.fail();
	    		}


	    	}
	    	
	    	public void Click_Defaultlabel(String dataset) {
	    		try {
	    			Sync.waitPageLoad();
	    			Thread.sleep(2000);
	    			Common.clickElement("id", "menu-magento-catalog-catalog");
	    			Thread.sleep(5000);

	    			String catalogmenu = Common.findElement("xpath", "//li[contains(@class,'active')]").getAttribute("class");
	    			System.out.println(catalogmenu);
	    			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
	    			Common.assertionCheckwithReport(catalogmenu.contains("show"), "To Validate the catalog menu is displayed",
	    					"should display the catalog menu after clicking on the catalog",
	    					"catalog menu is displayed after a click on the catalog button", "Failed to display catalog menu");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the catalog menu is displayed",
	    					"should display the catalog menu after clicking on the catalog",
	    					"unable to display catalog field menu after a click on the catalog button",
	    					"Failed to display catalog field menu");
	    			Assert.fail();
	    		}


	    	}
	    	public void Click_DeleteAttribute(String dataset) {
	    		try {
	    			Sync.waitPageLoad();
	    			Thread.sleep(2000);
	    			Common.clickElement("id", "menu-magento-catalog-catalog");
	    			Thread.sleep(5000);

	    			String catalogmenu = Common.findElement("xpath", "//li[contains(@class,'active')]").getAttribute("class");
	    			System.out.println(catalogmenu);
	    			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
	    			Common.assertionCheckwithReport(catalogmenu.contains("show"), "To Validate the catalog menu is displayed",
	    					"should display the catalog menu after clicking on the catalog",
	    					"catalog menu is displayed after a click on the catalog button", "Failed to display catalog menu");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the catalog menu is displayed",
	    					"should display the catalog menu after clicking on the catalog",
	    					"unable to display catalog field menu after a click on the catalog button",
	    					"Failed to display catalog field menu");
	    			Assert.fail();
	    		}
	    	}
	    	public void product_search(String DataSet) throws Exception {
	    		String expectedResult=" should select the product from search";
	    		try{
	    			if (Common.isElementDisplayed("xpath", "//ul[@class='admin__current-filters-list']")) {
                        Common.javascriptclickElement("xpath", "//button[@class='action-tertiary action-clear']");
                    }
	    	Thread.sleep(3000);
	    	Common.textBoxInput("xpath","(//input[@id='fulltext'])[1]",data.get(DataSet).get("product"));
	    	Common.actionsKeyPress(Keys.ENTER);
	    	Thread.sleep(5000);
	    	Common.clickElement("xpath", "//a[@class='action-menu-item']");
	    	Thread.sleep(3000);
	    	  String Text =   Common.findElement("xpath", "//h1[@class='page-title']").getText();
	    	
	    	Common.assertionCheckwithReport(Text.equalsIgnoreCase("TEST Product!"),
	    	"verifying the product", "User navigate to product details",
	    	"user successfully open the product details", "user faield to click product");

	    		}
	    		catch (Exception | Error e) {
	    			e.printStackTrace();

	    			ExtenantReportUtils.addFailedLog("verifying the products search", expectedResult,
	    					"User failed to navigate products list ", Common.getscreenShotPathforReport("failed to click products search"));

	    			Assert.fail();


	    		}
	    	}
	    	public void Addproduct_upsells() throws InterruptedException
	    	{
	    		String expectedResult="should click on Related Products, Up-Sells, and Cross-Sells";
	    		try
	    		{
	    		Thread.sleep(4000);
	    	String text=Common.findElement("xpath", "//span[contains(text(),'Related Products, Up-Sells, and Cross-Sells')]").getText();
	    	Thread.sleep(5000);
	    		Common.clickElement("xpath", "//span[contains(text(),'Related Products, Up-Sells, and Cross-Sells')]");
	    		
	    		Common.assertionCheckwithReport(text.equalsIgnoreCase("Related Products, Up-Sells, and Cross-Sells"),
	    				"verifying Related Products, Up-Sells, and Cross-Sells", "User navigate to Related Products, Up-Sells, and Cross-Sells page",
	    				"user successfully open the Related Products, Up-Sells, and Cross-Sells page", "user faield to click on Related Products, Up-Sells, and Cross-Sells");
	    		
	    		if (Common.isElementDisplayed("xpath", "//span[text()='Configurable TEST']")) {
	    		//	Sync.waitElementVisible("xpath", "//span[text()='Configurable TEST']");
	    			Thread.sleep(3000);
	    			Common.scrollIntoView("xpath", "//span[text()='Configurable TEST']");
	    			Sync.waitElementVisible("xpath", "//span[text()='Configurable TEST']");
	    		//	Common.clickElement("xpath", "(//button[@data-action='remove_row'])[5]");
	    			Thread.sleep(3000);
	    			Common.scrollIntoView("xpath", "//span[contains(text(),'Add Up-Sell Products')]");
	    		
	    			
	    		Common.clickElement("xpath", "//span[contains(text(),'Add Up-Sell Products')]");	

	    		} else {
	    			
	    			Thread.sleep(3000);
	    		Common.scrollIntoView("xpath", "//span[contains(text(),'Add Up-Sell Products')]");

//	    	String text1 = 	Common.findElement("xpath", "//span[contains(text(),'Add Up-Sell Products')]").getText();
//	    	System.out.println(text1);
	    	Thread.sleep(5000);
	    Common.clickElement("xpath", "//span[contains(text(),'Add Up-Sell Products')]");
	    		}
	    		String text1 = 	Common.findElement("xpath", "//span[contains(text(),'Add Up-Sell Products')]").getText();
	    		System.out.println(text1);
	    Thread.sleep(5000);
	    Common.scrollIntoView("xpath", "//div[text()='Configurable TEST']");
	    Thread.sleep(5000);
	    Sync.waitElementPresent("xpath","//label//input[@id='idscheck28']");
	    Common.clickElement("xpath", "//label//input[@id='idscheck28']");
	    Common.assertionCheckwithReport(text1.equals("Add Up-Sell Products"),
	    		"verifying the add the selected product", "User navigate to add the select product",
	             "user successfully open the add selected product", "user faield to add select the product");
	    		
	    	}
	    	
	    catch (Exception | Error e) {
	       e.printStackTrace();
	    	ExtenantReportUtils.addFailedLog("verifying the add selected products", expectedResult,
	    			"User should add selected products ", Common.getscreenShotPathforReport("failed to add selected products"));
	    	Assert.fail();
	    }


	    			
	    	}	
//	    	public void addupsellproduct() {
//	    		String expectedResult="should add the selected product";
//	    		try{
//	    			
//	    	Thread.sleep(3000);
//	    	Sync.waitElementPresent("xpath","//label//input[@id='idscheck4']");
//	    	Common.clickElement("xpath", "//label//input[@id='idscheck4']");
	    //	
//	    	Common.assertionCheckwithReport(.equals("Add Up-Sell Products"),
//	    			"verifying the add the selected product", "User navigate to add the select product",
//	                 "user successfully open the add selected product", "user faield to add select the product");
//	    			}
//	    		}
//	    		
//	    	catch (Exception | Error e) {
//	    	   e.printStackTrace();
//	    		ExtenantReportUtils.addFailedLog("verifying the add selected products", expectedResult,
//	    				"User should add selected products ", Common.getscreenShotPathforReport("failed to add selected products"));
//	    		Assert.fail();
//	    	}

	    	//}
	    	public void addselectedproduct() {
	    		String expectedResult="should add the selected product";
	    		try{
	    			Thread.sleep(3000);
//	    			Sync.waitElementPresent("xpath","//label//input[@id='idscheck28']");
//	    			Common.clickElement("xpath", "//label//input[@id='idscheck28']");
	    		String text = 	Common.findElement("xpath", "(//span[text()='Add Selected Products'])[2]").getText();
	    	Common.clickElement("xpath", "(//span[contains(text(),'Add Selected Products')])[2]");
	    	Common.assertionCheckwithReport(text.equals("Add Selected Products"),
	    			"verifying the add the selected product", "User navigate to add the select product",
	                 "user successfully open the add selected product", "user faield to add select the product");
	    	Thread.sleep(3000);
	    	Sync.waitElementPresent("xpath","//button[@id='save-button']");
	    	Common.clickElement("xpath", "//button[@id='save-button']");
	    	
	    	Sync.waitElementPresent(20, "xpath", "//div[text()='You saved the product.']");
	    	String sucessMessage = Common.getText("xpath", "//div[text()='You saved the product.']");
	    	Thread.sleep(3000);
	    	System.out.println(sucessMessage);
	    	
	    	Thread.sleep(3000);
	    		
	    		}
	    	catch (Exception | Error e) {
	    	   e.printStackTrace();
	    		ExtenantReportUtils.addFailedLog("verifying the add selected products", expectedResult,
	    				"User should add selected products ", Common.getscreenShotPathforReport("failed to add selected products"));
	    		Assert.fail();
	    	}

	    	}
	    	public void page_Cache(String Dataset) {
	    		// TODO Auto-generated method stub
	    		String Magento = data.get(Dataset).get("cache");
	    		try {
	    			Sync.waitElementPresent("xpath", "//a//span[text()='System']");
	    			Common.clickElement("xpath", "//a//span[text()='System']");
	    			Sync.waitElementPresent("xpath", "//a//span[text()='Cache Management']");
	    			Common.clickElement("xpath", "//a//span[text()='Cache Management']");
	    			Sync.waitPageLoad();
	    			Thread.sleep(4000);
	    			Common.assertionCheckwithReport(Common.getPageTitle().contains("Cache Management"),
	    					"validating the page navigates to the cache management",
	    					"It should Navigates to the Cache management page",
	    					"Sucessfully it is navigated to the cache managment page",
	    					"failed to Navigate to the cache managment page");
	    			String page=Common.findElement("xpath", "//td[text()='Page Cache']").getText();
	    			if(page.equals("Page Cache"))
	    			{
	    				Sync.waitElementPresent("xpath","//td[text()='Page Cache']");
	    				Common.clickElement("xpath", "//td[text()='Page Cache']");
	    			}
	    			else
	    			{
	    				Assert.fail();
	    			}
	    			String records=Common.findElement("xpath", "//span[@class='mass-select-info']//strong").getText();
	    			if(records.equals("1"))
	    			{
	    				Sync.waitElementPresent("xpath","//span[text()='Submit']");
	    				Common.clickElement("xpath", "//span[text()='Submit']");
	    			}
	    			else
	    			{
	    				Assert.fail();
	    			}
	    			String message=Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
	    			Common.assertionCheckwithReport(message.equals("1 cache type(s) refreshed."),
	    					"validating the cache management sucess message",
	    					"cache message should be display after clicking on the flush magento",
	    					"Sucessfullythe message has been displayed after clicking on the flush magneto",
	    					"failed to display the sucess message after clicking on the flush magneto");
	    			
	    			}
	    		catch(Exception | Error e)
	    		{
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("validating the cache management sucess message",
	    					"cache message should be display after clicking on the flush magento",
	    					"unable to display the sucess message after clicking on the flush magneto",
	    					Common.getscreenShot("failed to display the sucess message after clicking on the flush magneto"));
	    			Assert.fail();
	    		}
	    		
	    	}
	    	public void openwebsite(String Dataset) {
	    		String pagetitle = data.get(Dataset).get("URL");
	    		System.out.println(pagetitle);
	    		try {
	    			Sync.waitPageLoad(60);

	    			String currentAdminURL = Common.getCurrentURL();
	    			//String urlkey = pagetitle.toLowerCase();
	    			//System.out.println(urlkey);
	    			Common.openNewTab();
	    			if (currentAdminURL.contains("staging")) {
	    				Common.oppenURL(data.get(Dataset).get("URL"));
	    				
	    			} else {
	    				Common.oppenURL(data.get(Dataset).get("preprodURL"));

	    			}
	    			Sync.waitPageLoad(40);
	    			String uname = Common.getPageTitle();
	    			Common.assertionCheckwithReport(uname.contains("Home page"),
	    					"Validating the User lands to the osprey page",
	    					"User should able to land on the osprey page", "Sucessfully User lands on the osprey page",
	    					"Failed to navigate to the osprey page");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("Validating the User lands to the osprey page",
	    					"User should able to land on the osprey page", "Unable to Navigate to the osprey page",
	    					Common.getscreenShotPathforReport("Failed to navigate to the osprey page"));

	    			Assert.fail();
	    		}
	    	}
	    	public void verifingHomePage() {
	    		try {
	    			Sync.waitPageLoad();
	    			Thread.sleep(5000);
	    			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
	    			String title= Common.getPageTitle();
	    			System.out.println(title);
	    			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Home page"),
	    					"validating store logo", "System directs to the Homepage", "Sucessfully navigate to home page",
	    					"faield to naviagte to homepage");
	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("validating store logo", "System directs to the Homepage",
	    					"Unable to navigate Home page",
	    					Common.getscreenShotPathforReport("failed to get back to homepage"));
	    			Assert.fail();
	    		}

	    	}
	    	public void search_product1(String Dataset) {
	    		// TODO Auto-generated method stub
//	    		String product = data.get(Dataset).get("product");
//	    		System.out.println(product);
	    		try
	    		{
	            Common.clickElement("xpath", "(//div[contains(@class,'m-utilities-nav__item--search')])[2]");
	         	String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
	         	Thread.sleep(4000);
	         	Common.assertionCheckwithReport(open.contains("active"), "User searches using the search field",
	         	"User should able to click on the search button", "Search expands to the full page",
	         	"Sucessfully search bar should be expand");
	         	String product = data.get(Dataset).get("product");
	    		System.out.println(product);
	         	WebElement serachbar=Common.findElement("xpath", "//input[@id='autocomplete-0-input']");
	            serachbar.sendKeys(product);
	            Common.actionsKeyPress(Keys.ENTER);
	        	Sync.waitPageLoad();
	        	Thread.sleep(4000);
	    			String productsearch = Common.findElement("xpath", "//span[@id='algolia-srp-title']").getText();
	    			System.out.println(productsearch);
	    			Common.assertionCheckwithReport(productsearch.contains(product), "validating the search functionality",
	    					"enter product name will display in the search box", "user enter the product name in  search box",
	    					"Failed to see the product name");
	    			Thread.sleep(8000);
	                 }  
	    		 catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("validating the search functionality",
	    					"enter product name will display in the search box",
	    					" unable to enter the product name in  search box",
	    					Common.getscreenShot("Failed to see the product name"));
	    			Assert.fail();
	    		}

	    	}
	    	
	    	public void click_product() {

	    		try {
	    			Sync.waitElementPresent("xpath", "//img[@alt='TEST Product!']");

	    			Common.clickElement("xpath", "//img[@alt='TEST Product!']");
	    			Sync.waitPageLoad();
	    			Thread.sleep(4000);
	    			Common.refreshpage();
	    			Thread.sleep(5000);

	    			String pagetitle = Common.getPageTitle();
	    			System.out.println(pagetitle);
	    Thread.sleep(2000);
	    			Common.assertionCheckwithReport(pagetitle.contains("TEST Product"),
	    					"To Validate the PDP page is displayed",
	    					"should display the PDP page after clicking on the product image",
	    					"update PDP page are displayed after a click on the product image", "Failed to display  PDP page");
	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the PDP page are displayed",
	    					"should display the PDP page after clicking on the product image",
	    					"unable to display PDP page after a click on the product image",
	    					"Failed to display update PDP page");
	    			Assert.fail();
	    		}

	    	}
	    	public void verifyselectedproduct() throws InterruptedException {
	    		String expectedResult="should add the added selected product";
	    		try{
	    		Thread.sleep(4000);
	    		Common.scrollIntoView("xpath", "//img[@alt='Configurable TEST']");
	    		Thread.sleep(3000);
	    		String selectedproduct = Common.getText("xpath", "//a[text()='Configurable TEST']");
	    		System.out.println(selectedproduct);
	    		Thread.sleep(4000);
//	    	    String producttitle=Common.getText("xpath", "//a[@title='24K Gold One-Step Blowout And Volumizer']");
//	    	    System.out.println(producttitle);
	    	    Common.assertionCheckwithReport(selectedproduct.contains("Configurable TEST"),
	    	    		"verifying the added selected product", "User navigate to added select product",
	    	    		"user successfully open the added selected product", "user faield to added select product");
	    	    }
	    	    catch (Exception | Error e) {
	    	       e.printStackTrace();
	    	    	ExtenantReportUtils.addFailedLog("verifying the added selected products", expectedResult,
	    	    			"User should added selected products ", Common.getscreenShotPathforReport("failed to added selected products"));
	    	    	Assert.fail();
	    	    }
	    	}
	    	public void back_to_Magento_Admin() {
	    		String expectedResult=" should navigate to magento homepage";
	    		try{
	    		
//	    	Common.oppenURL("https://jetrails-m2-stag.hottools.com/PUjbcJfzYQqqP6BeP");
	    			Common.switchToFirstTab();
	    	}
	    	catch (Exception | Error e) {
	    		e.printStackTrace();

	    		ExtenantReportUtils.addFailedLog("verifying magento homepage", expectedResult,
	    				"User should navigate to magento homepage", Common.getscreenShotPathforReport("failed to login magento"));

	    		Assert.fail();
	    	}		
	    	}
	    	public void Removeproduct_upsells() throws InterruptedException
	    	{
	    		String expectedResult="should click on Related Products, Up-Sells, and Cross-Sells";
	    		try
	    		{
	    		Thread.sleep(4000);
	    	String text=Common.findElement("xpath", "//span[contains(text(),'Related Products, Up-Sells, and Cross-Sells')]").getText();
	    	Thread.sleep(5000);
	    		Common.clickElement("xpath", "//span[contains(text(),'Related Products, Up-Sells, and Cross-Sells')]");
	    		
	    		Common.assertionCheckwithReport(text.equalsIgnoreCase("Related Products, Up-Sells, and Cross-Sells"),
	    				"verifying Related Products, Up-Sells, and Cross-Sells", "User navigate to Related Products, Up-Sells, and Cross-Sells page",
	    				"user successfully open the Related Products, Up-Sells, and Cross-Sells page", "user faield to click on Related Products, Up-Sells, and Cross-Sells");
	    		
	    			Sync.waitElementVisible("xpath", "//span[text()='Configurable TEST']");
	    			Thread.sleep(3000);
	    			Common.clickElement("xpath", "(//button[@data-action='remove_row'])[5]");
	    			Thread.sleep(3000);
	    			Sync.waitElementPresent("xpath","//button[@id='save-button']");
	    			Common.clickElement("xpath", "//button[@id='save-button']");
	    			
	    			Sync.waitElementPresent(20, "xpath", "//div[text()='You saved the product.']");
	    			String sucessMessage = Common.getText("xpath", "//div[text()='You saved the product.']");
	    			Thread.sleep(3000);
	    			System.out.println(sucessMessage);
	    			
	    			Thread.sleep(3000);
	    				
	    				}
	    			catch (Exception | Error e) {
	    			   e.printStackTrace();
	    				ExtenantReportUtils.addFailedLog("verifying the add selected products", expectedResult,
	    						"User should add selected products ", Common.getscreenShotPathforReport("failed to add selected products"));
	    				Assert.fail();
	    			}

	    			}
	    	
	    	public void opensecondtab() {
	    		try {
	    			Sync.waitPageLoad();
	    			Thread.sleep(5000);
	    			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
	    			String title= Common.getPageTitle();
	    			System.out.println(title);
	    			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Home page"),
	    					"validating store logo", "System directs to the Homepage", "Sucessfully navigate to home page",
	    					"faield to naviagte to homepage");
	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("validating store logo", "System directs to the Homepage",
	    					"Unable to navigate Home page",
	    					Common.getscreenShotPathforReport("failed to get back to homepage"));
	    			Assert.fail();
	    		}

	    	}
	    	public void verifyremoveproduct() throws InterruptedException {
	    		String expectedResult="should add the added selected product";
	    		try{
	    		Thread.sleep(4000);
	    		Common.scrollIntoView("xpath", "//div[@class='c-product-carousel c-product-carousel--slick upsell']");
	    		Thread.sleep(3000);
	    		String deletedtedproduct = Common.findElement("xpath", "//div[@class='c-product-carousel c-product-carousel--slick upsell']").getAttribute("class");
	    		System.out.println(deletedtedproduct);
	    		Thread.sleep(4000);
//	    	    String producttitle=Common.getText("xpath", "//a[@title='24K Gold One-Step Blowout And Volumizer']");
//	    	    System.out.println(producttitle);
	    	    Common.assertionCheckwithReport(deletedtedproduct.contains("c-product-carousel c-product-carousel--slick upsel"),
	    	    		"verifying the deleted selected product", "User navigate to deleted select product",
	    	    		"user successfully open the deleted selected product", "user faield to deleted select product");
	    	    }
	    	    catch (Exception | Error e) {
	    	       e.printStackTrace();
	    	    	ExtenantReportUtils.addFailedLog("verifying the deleted selected products", expectedResult,
	    	    			"User should deleted selected products ", Common.getscreenShotPathforReport("failed to deleted selected products"));
	    	    	Assert.fail();
	    	    }
	    	}
	    	
	    	public void click_sales() {
	    		// TODO Auto-generated method stub
	    		try {
	    			Sync.waitPageLoad();
	    			Sync.waitElementPresent("id", "menu-magento-sales-sales");
	    			Common.clickElement("id", "menu-magento-sales-sales");
	    			Thread.sleep(2000);
	    			String Sales = Common.findElement("xpath", "//li[@class='item-sales-order    level-2']").getText();
	    			System.out.println(Sales);
	    			// Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and
	    			// @style='display: none;']");
	    			Common.assertionCheckwithReport(Sales.equals("Orders"), "To verify the sales menu ",
	    					"After clicking the sales menu it will display menu options ",
	    					"Successfully clicked the sales menu and it displayed the Catalog options",
	    					"Failed to click the sales menu");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			report.addFailedLog("To verify the sales menu ",
	    					"After clicking the sales menu it will display menu options ",
	    					"Successfully clicked the sales menu and it displayed the sales options",
	    					Common.getscreenShotPathforReport("Failed to click on the sales menu"));
	    			Assert.fail();
	    		}

	    	}
	    	
	    	public void click_Orders_Salesmenu() {
	    		// TODO Auto-generated method stub

	    		try {
	    			Thread.sleep(2000);
	    			Sync.waitElementPresent("xpath", "//li[@class='item-sales-order    level-2']");
	    			Common.clickElement("xpath", "//li[@class='item-sales-order    level-2']");
	    			Sync.waitPageLoad();
	    			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
	    			Common.assertionCheckwithReport(
	    					Common.getPageTitle().contains("Orders / Operations / Sales / Magento Admin"),
	    					"To Validate the orders page is displayed",
	    					"should display the orders page after clicking on the orders",
	    					"orders page is displayed after a click on the order button", "Failed to display orders page");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the orders page is displayed",
	    					"should display the orders page after clicking on the orders",
	    					"unable to display orders page after a click on the orders button",
	    					"Failed to display orders page");
	    			Assert.fail();
	    		}

	    	}
	    	
	    	public void Click_CreatNewOrders() {
	    		// TODO Auto-generated method stub
	    		try {
	    			Thread.sleep(2000);
	    			Sync.waitElementPresent("xpath", "//button[@class='action- scalable primary']");
	    			Common.clickElement("xpath", "//button[@class='action- scalable primary']");
	    			Sync.waitPageLoad();
	    			String text = Common.findElement("xpath", "//h1[@class='page-title']").getText();
	    			Common.assertionCheckwithReport(text.contains("Create New Order for New Customer"),
	    					"To Validate the Create New Order for New Customer page is displayed",
	    					"should display the Create New Order for New Customer page after clicking on the create new order",
	    					"Create New Order for New Customer page is displayed after a click on the create new order button",
	    					"Failed to display Create New Order for New Customer page");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the Create New Order for New Customer page is displayed",
	    					"should display the Create New Order for New Customer page after clicking on the create new order",
	    					"unable to display Create New Order for New Customer page after a click on the create new order button",
	    					"Failed to display Create New Order for New Customer page");
	    			Assert.fail();
	    		}

	    	}
	    	
	    	public void Click_CreatNewCustomer() {
	    		// TODO Auto-generated method stub
	    		try {
	    			Thread.sleep(2000);
	    			Sync.waitElementPresent("xpath", "//button[@title='Create New Customer']");
	    			Common.clickElement("xpath", "//button[@title='Create New Customer']");
	    			Sync.waitPageLoad();
	    			String title = Common.findElement("xpath", "//h1[@id='order-header']").getText();
	    			System.out.println(title);
	    			Common.assertionCheckwithReport(title.contains("Create New Order for New Customer"),
	    					"To Validate the Create New customer for Create New Order for New Customer page is displayed",
	    					"should display the Create New customer for Create New Order for New Customer page after clicking on the create new customer",
	    					"Create New customer for Create New Order for New Customer page is displayed after a click on the create new ocustomerrder button",
	    					"Failed to display Create New customer for New Customer page");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog(
	    					"To Validate the Create New customer for Create New Order for New Customer page is displayed",
	    					"should display the Create New customer for Create New Order for New Customer page after clicking on the create new customer",
	    					"unable to display Create New customer for Create New Order for New Customer page after a click on the create new customer button",
	    					"Failed to display Create New customer for Create New Order for New Customer page");
	    			Assert.fail();
	    		}

	    	}
	    	
	    	public void Select_astore() {
	    		// TODO Auto-generated method stub
	    		try {
	    			Thread.sleep(2000);

	    			Sync.waitElementPresent("xpath", "//label[text()='Osprey UK English Store']");
	    			Common.clickElement("xpath", "//label[text()='Osprey UK English Store']");
	    			Sync.waitPageLoad();
	    			Thread.sleep(2000);
	    			String title = Common.findElement("xpath", "//strong[text()='Items Ordered']").getText();
	    			System.out.println(title);
	    			Common.assertionCheckwithReport(title.contains("Items Ordered"),
	    					"To Validate the Items Ordered page is displayed",
	    					"should display the Items Orderedpage after clicking on the store",
	    					"Items Ordered page is displayed after a click on the store button",
	    					"Failed to display Items Ordered page");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the Items Ordered page is displayed",
	    					"should display the Items OrderedT page after clicking on the store",
	    					"unable to display Items OrderedT page after a click on the store button",
	    					"Failed to display Items Ordered page");
	    			Assert.fail();
	    		}

	    	}
	    	
	    	public void Add_product_SKU(String dataSet) {
	    		// TODO Auto-generated method stub
	    		try {

	    			String[] SKUnumber = data.get(dataSet).get("SKUNumber").split(",");
	    			String[] Quantity = data.get(dataSet).get("Quantity").split(",");

	    			for (int i = 0; i < SKUnumber.length; i++) {
	    				System.out.println(SKUnumber[i]);

	    				Sync.waitElementPresent("xpath", "//div[@class='actions']//span[text()='Add Products By SKU']");
	    				Common.clickElement("xpath", "//div[@class='actions']//span[text()='Add Products By SKU']");
	    				Thread.sleep(2000);
	    				Sync.waitElementPresent("xpath", "//td[@class='col-sku']//input[@name='sku']");
	    				Common.textBoxInput("xpath", "//td[@class='col-sku']//input[@name='sku']", SKUnumber[i]);

	    				Common.textBoxInput("xpath", "//div[@class='input-box']//input[@name='qty']", Quantity[i]);

	    				Sync.waitElementPresent("xpath", "//button[@title='Add to Order']");
	    				Common.clickElement("xpath", "//button[@title='Add to Order']");

	    				Sync.waitElementVisible("xpath", "//div[contains(text(),'" + SKUnumber[i] + "')]");
	    				String[] SKU = Common.findElement("xpath", "//div[contains(text(),'" + SKUnumber[i] + "')]").getText()
	    						.split(" ");
	    				System.out.println(SKU[1]);
	    				Common.assertionCheckwithReport(SKU[1].contains(SKUnumber[i]), "To validate the sku is added to order",
	    						"sku should be added to order", "SKU is added to order", "failed to add SKU to order");

	    				Common.scrollIntoView("xpath", "//div[@class='actions']//span[text()='Add Products By SKU']");

	    			}
	    			// Common.actionsKeyPress(Keys.ENTER);

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To validate the sku is added to order", "sku should be added to order",
	    					"SKU is added to order", "failed to add SKU to order");
	    			Assert.fail();
	    		}

	    	}
	    	
	    	public void Guestuser_shippingaddress(String dataSet) {
	    		// TODO Auto-generated method stub

	    		try {
	    			Thread.sleep(2000);

	    			Sync.waitElementPresent("xpath", "//input[@name='order[account][email]']");
	    			Common.textBoxInput("xpath", "//input[@name='order[account][email]']", Utils.getEmailid());

	    			Thread.sleep(4000);
	    			Common.textBoxInputClear("xpath", "//input[@id='order-billing_address_firstname']");
	    			Common.textBoxInput("xpath", "//input[@id='order-billing_address_firstname']",
	    					data.get(dataSet).get("FirstName"));

	    			Sync.waitElementPresent("xpath", "//input[@id='order-billing_address_lastname']");
	    			Common.textBoxInput("xpath", "//input[@id='order-billing_address_lastname']",
	    					data.get(dataSet).get("LastName"));

	    			Sync.waitElementPresent("xpath", "//input[@id='order-billing_address_street0']");
	    			Common.textBoxInput("xpath", "//input[@id='order-billing_address_street0']",
	    					data.get(dataSet).get("streetaddress"));

	    			Sync.waitElementPresent("xpath", "//select[@id='order-billing_address_country_id']");
	    			Common.dropdown("xpath", "//select[@id='order-billing_address_country_id']", Common.SelectBy.TEXT,
	    					data.get(dataSet).get("Country"));

	    			Sync.waitElementPresent("xpath", "//select[@id='order-billing_address_region_id']");
	    			Common.dropdown("xpath", "//select[@id='order-billing_address_region_id']", Common.SelectBy.TEXT,
	    					data.get(dataSet).get("State"));

	    			Sync.waitElementPresent("xpath", "//input[@id='order-billing_address_city']");
	    			Common.textBoxInput("xpath", "//input[@id='order-billing_address_city']", data.get(dataSet).get("City"));

	    			Sync.waitElementPresent("xpath", "//input[@id='order-billing_address_postcode']");
	    			Common.textBoxInput("xpath", "//input[@id='order-billing_address_postcode']",
	    					data.get(dataSet).get("Postcode"));

	    			Sync.waitElementPresent("xpath", "//input[@id='order-billing_address_telephone']");
	    			Common.textBoxInput("xpath", "//input[@id='order-billing_address_telephone']",
	    					data.get(dataSet).get("Phonenumber"));
	    			Thread.sleep(3000);
	    			Sync.waitElementPresent("xpath", "//div[@id='order-shipping-method-summary']/a");

	    			Common.doubleClick("xpath", "//span[contains(text(), 'Get shipping methods and rates')]");

	    			Thread.sleep(8000);
	    			Common.clickElement("xpath", "//label[contains(text(), 'RMA Free Shipping - ')]");

	    			Sync.waitPageLoad();
	    			// Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and
	    			// @style='display: none;']");
	    			String text = Common.findElement("xpath", "//label[contains(text(), 'RMA Free Shipping - ')]").getText();
	    			System.out.println(text);
	    			Common.assertionCheckwithReport(text.contains("RMA Free Shipping - £0.00"),
	    					"To Validate the displayed click to change shipping method",
	    					"should display the click to change shipping method after clicking on the standard shipping",
	    					"click to change shipping method is displayed after a click on the standard shipping",
	    					"Failed to display click to change shipping method");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the displayed click to change shipping method",
	    					"should display the click to change shipping method after clicking on the standard shipping",
	    					"unable to display click to change shipping method after a click on the standard shipping",
	    					"Failed to display click to change shipping method");
	    			Assert.fail();

	    		}

	    	}
	    	
	    	public String Default_Payment_method(String dataSet) {
	    		// TODO Auto-generated method stub
	    		String order = "";
	    		try {
	    			Thread.sleep(2000);

	    			Sync.waitElementPresent("xpath", "//input[@title='Payment Method']");
	    			Common.clickElement("xpath", "//input[@title='Payment Method']");
	    			Thread.sleep(3000);

	    			Sync.waitPageLoad();

	    			int sizesframe = Common.findElements("xpath", "//label[@for='new_card']").size();
	    			if (sizesframe > 0) {
	    				Sync.waitElementVisible("xpath", "//label[@for='new_card']");
	    				Common.clickElement("xpath", "//label[@for='new_card']");
	    			} else {
	    				Sync.waitElementPresent("xpath", "//div[@id='stripe-card-element']");
	    				Common.clickElement("xpath", "//div[@id='stripe-card-element']");
	    				// Common.switchFrames("xpath", "//iframe[@title='Secure card payment input
	    				// frame']");
	    			}
	    			Common.switchFrames("xpath", "//iframe[@title='Secure card payment input frame']");
	    			Sync.waitElementPresent("xpath", "//input[@data-elements-stable-field-name='cardNumber']");
	    			Common.textBoxInput("xpath", "//input[@data-elements-stable-field-name='cardNumber']",
	    					data.get(dataSet).get("CardNumber"));

	    			Sync.waitElementPresent("xpath", "//input[@placeholder='MM / YY']");
	    			Common.textBoxInput("xpath", "//input[@placeholder='MM / YY']", data.get(dataSet).get("ExpMonth"));

	    			Sync.waitElementPresent("xpath", "//input[@placeholder='CVC']");
	    			Common.textBoxInput("xpath", "//input[@placeholder='CVC']", data.get(dataSet).get("CVV"));
	    			Common.switchToDefault();

	    			Thread.sleep(3000);
	    			Common.findElement("xpath", "//button[@data-ui-id='order-content-submit-order-top-button-button']").click();

	    			String Sucess = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
	    			System.out.println(Sucess);
	    			Common.assertionCheckwithReport(Sucess.contains("You created the order."),
	    					"To Validate the sucess message is displayed",
	    					"should display the sucess message after clicking on the sudmitorder",
	    					"sucess message is displayed after a click on the sudmitorder button",
	    					"Failed to display sucess message");
	    			order = Common.findElement("xpath", "//h1[@class='page-title']").getText().replace("#", "");
	    			System.out.println(order);

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the sucess message is displayed",
	    					"should display the sucess message after clicking on the sudmitorder",
	    					"unable to display sucess message after a click on the sudmitorder button",
	    					"Failed to display sucess message");
	    			Assert.fail();
	    		}
	    		return order;
	    	}
	    	
	    	public void enter_orderNumber(String dataSet) {
	    		// TODO Auto-generated method stub

	    		try {
	    			Thread.sleep(3000);
	    			Common.clickElement("xpath",
	    					"//div[@class='admin__current-filters-actions-wrap']//button[text()='Clear all']");
	    			Thread.sleep(5000);
	    			Common.textBoxInput("xpath", "(//input[text()='Search by keyword'])[1]", dataSet);
	    			Thread.sleep(2000);
	    			Common.clickElement("xpath", "//button[@aria-label='Search']");
	    			Thread.sleep(4000);

	    			String orderID = Common.findElement("xpath", "//span[@data-bind='text: preview']").getText();

	    			String orderNumber = Common.findElement("xpath", "(//div[@data-bind='text: $col.getLabel($row())'])[1]")
	    					.getText();
	    			String records = Common.findElement("xpath", "//div[@class='admin__control-support-text']").getText();
	    			if ((orderNumber.equals(orderID)) && (records.contains("1 records found"))) {

	    				Sync.waitPageLoad();
	    				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
	    				Common.assertionCheckwithReport(
	    						Common.getPageTitle().contains("Orders / Operations / Sales / Magento Admin"),
	    						"To Validate the order number is populated in search field",
	    						"should display the order number in search field",
	    						"order number should be displayed after entering in search field",
	    						"Failed to display order number");

	    				Click_View_order(dataSet);

	    			} else {
	    				Assert.fail();
	    			}

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the order number is populated in search field",
	    					"should display the order number in search field",
	    					"unable to display order number after entering in search field", "Failed to display order number");
	    			Assert.fail();
	    		}

	    	}
	    	
	    	public void Click_View_order(String Dataset) {
	    		// TODO Auto-generated method stub

	    		try {
	    			Thread.sleep(4000);
	    			Sync.waitElementPresent("xpath", "//td[@class='data-grid-actions-cell']//a[text()='View']");
	    			Common.clickElement("xpath", "//td[@class='data-grid-actions-cell']//a[text()='View']");
	    			Sync.waitPageLoad();
	    			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

	    			System.out.println(Dataset);

	    			String orderNumber = Common
	    					.findElement("xpath", "//div[@class='page-title-wrapper']//h1[@class='page-title']").getText()
	    					.replace("#", "");
	    			System.out.println(orderNumber);

	    			Common.assertionCheckwithReport(
	    					Common.getPageTitle().contains("Orders / Operations / Sales / Magento Admin")
	    							&& orderNumber.equals(Dataset),
	    					"To Validate the order details page is displayed",
	    					"should display the order details page after clicking on the view order link",
	    					"order details page is displayed after a click on the view order link",
	    					"Failed to display order details page");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the order details page is displayed",
	    					"should display the order details page after clicking on the view order link",
	    					"unable to display order details page after a click on the view order link",
	    					"Failed to display order details page");
	    			Assert.fail();
	    		}

	    	}
	    	
	    	public void Update_an_OrderBilling(String dataset) throws Exception {
	    		try {
//	    			Thread.sleep(2000);
//	    			Sync.waitElementPresent("xpath", "//a[@class='action-menu-item']");
//	    			Common.clickElement("xpath", "//a[@class='action-menu-item']");
//	    			Thread.sleep(8000);
	    			Sync.waitPageLoad();
	    			Common.clickElement("xpath", "//button[@id='order-view-cancel-button']");
	    			Thread.sleep(2000);
	    			Common.clickElement("xpath", "//button[@class='action-secondary action-dismiss']");
	    			Thread.sleep(6000);

	    			Sync.waitElementPresent("xpath", "//span[text()='Hold']");
	    			Common.clickElement("xpath", "//span[text()='Hold']");
	    //String Hold = Common.findElement("xpath", "//div[@class='message message-success success']").getText();
	    // System.out.println(Hold);
	    			Thread.sleep(5000);
	    			Common.clickElement("xpath", "//button[@class='action-default scalable unhold']");
	    			Thread.sleep(8000);
	    			Sync.waitPageLoad();
	    			Sync.waitElementPresent("xpath", "(//a[text()='Edit'])[1]");
	    			Common.clickElement("xpath", "(//a[text()='Edit'])[1]");
	    			String Text = Common.findElement("xpath", "//span[text()='Order Address Information']").getText();
	    			System.out.println(Text);
	    			Sync.waitPageLoad();
	    			Thread.sleep(4000);
	    			Common.findElement("xpath", "//span[text()='Street Address']");
	    			Common.scrollIntoView("xpath", "//span[text()='Street Address']");
	    			Thread.sleep(3000);
	    			Common.textBoxInputClear("xpath", "//input[@name='street[0]']");

	    			Common.textBoxInput("xpath", "//form[@id='edit_form']//input[@name='street[0]']",
	    					data.get(dataset).get("Street"));

	    			Thread.sleep(5000);
	    			Common.findElement("xpath", "//span[text()='State/Province']");
	    			Common.dropdown("xpath", "//select[@name='region_id']", SelectBy.TEXT, data.get(dataset).get("Region"));
	    			Thread.sleep(4000);
	    			Common.findElement("xpath", "//span[text()='City']");
	    			Common.textBoxInput("xpath", "//form[@id='edit_form']//input[@id='city']", data.get(dataset).get("City"));
	    			Thread.sleep(2000);
	    			Common.findElement("xpath", "//span[text()='Zip/Postal Code']");

	    			Common.textBoxInput("xpath", "//form[@id='edit_form']//input[@name='postcode']",
	    					data.get(dataset).get("postcode"));
	    			Sync.waitElementPresent("xpath", "//input[@id='telephone']");
	    			Common.textBoxInput("xpath", "//input[@id='telephone']", data.get(dataset).get("Phonenumber"));
	    			Thread.sleep(3000);
	    			Common.findElement("xpath", "//button[@title='Save Order Address']");
	    			Common.clickElement("xpath", "//button[@title='Save Order Address']");
	    			String Sucess = Common.findElement("xpath", "//div[text()='You updated the order address.']").getText();
	    			System.out.println(Sucess);

	    			Common.assertionCheckwithReport(Sucess.contains("You updated the order address."),
	    					"To Validate the sucess message is displayed",
	    					"should display the sucess message after clicking on the save order address",
	    					"sucess message is displayed after a click on the sudmitorder button",
	    					"Failed to display sucess message");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the sucess message is displayed",
	    					"should display the sucess message after clicking on the sudmitorder",
	    					"unable to display sucess message after a click on the sudmitorder button",
	    					"Failed to display sucess message");
	    			Assert.fail();
	    		}
	    	}
	    	
	    	public void Update_an_OrderShipping(String dataset) throws Exception {
	    		try {
	    			Thread.sleep(2000);
	    			Sync.waitPageLoad();
	    			Thread.sleep(5000);
	    			Sync.waitElementPresent("xpath", "(//a[text()='Edit'])[2]");
	    			Common.clickElement("xpath", "(//a[text()='Edit'])[2]");
	    			String Text = Common.findElement("xpath", "//span[text()='Order Address Information']").getText();
	    			System.out.println(Text);
	    			Sync.waitPageLoad();
	    			Thread.sleep(4000);
	    			Common.findElement("xpath", "//span[text()='Street Address']");
	    			Common.scrollIntoView("xpath", "//span[text()='Street Address']");
	    			Thread.sleep(3000);
	    			Common.textBoxInputClear("xpath", "//input[@name='street[0]']");

	    			Common.textBoxInput("xpath", "//form[@id='edit_form']//input[@name='street[0]']",
	    					data.get(dataset).get("Street"));

	    			Thread.sleep(5000);
	    			Common.findElement("xpath", "//span[text()='State/Province']");
	    			Common.dropdown("xpath", "//select[@name='region_id']", SelectBy.TEXT, data.get(dataset).get("Region"));
	    			Thread.sleep(4000);
	    			Common.findElement("xpath", "//span[text()='City']");
	    			Common.textBoxInput("xpath", "//form[@id='edit_form']//input[@id='city']", data.get(dataset).get("City"));
	    			Thread.sleep(2000);
	    			Common.findElement("xpath", "//span[text()='Zip/Postal Code']");

	    			Common.textBoxInput("xpath", "//form[@id='edit_form']//input[@name='postcode']",
	    					data.get(dataset).get("postcode"));
	    			Sync.waitElementPresent("xpath", "//input[@id='telephone']");
	    			Common.textBoxInput("xpath", "//input[@id='telephone']", data.get(dataset).get("Phonenumber"));
	    			Thread.sleep(3000);
	    			Common.findElement("xpath", "//button[@title='Save Order Address']");
	    			Common.clickElement("xpath", "//button[@title='Save Order Address']");
	    			String Sucess = Common.findElement("xpath", "//div[text()='You updated the order address.']").getText();
	    			System.out.println(Sucess);

	    			Common.assertionCheckwithReport(Sucess.contains("You updated the order address."),
	    					"To Validate the sucess message is displayed",
	    					"should display the sucess message after clicking on the save order address",
	    					"sucess message is displayed after a click on the sudmitorder button",
	    					"Failed to display sucess message");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the sucess message is displayed",
	    					"should display the sucess message after clicking on the sudmitorder",
	    					"unable to display sucess message after a click on the sudmitorder button",
	    					"Failed to display sucess message");
	    			Assert.fail();
	    		}
	    	}
	    	
	    	public void Click_Void() {
	    		// TODO Auto-generated method stub
	    		try {
	    			Thread.sleep(2000);
	    			Sync.waitElementPresent("xpath", "//button[@id='void_payment']");
	    			Common.clickElement("xpath", "//button[@id='void_payment']");
	    			Sync.waitPageLoad();

	    			Common.clickElement("xpath", "//button[@data-role='action']//span[text()='OK']");

	    			Common.assertionCheckwithReport(
	    					Common.getPageTitle().contains("Orders / Operations / Sales / Magento Admin"),
	    					"To Validate the Void payment",
	    					"should display the Void order details page after clicking on the Void",
	    					"Void order details page is displayed after a click on the Void button",
	    					"Failed to display Void order details page");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the Void payment",
	    					"should display the Void Order details page after clicking on the Void",
	    					"unable to display Void order details page after a click on the Void button",
	    					"Failed to display Void order details page");
	    			Assert.fail();
	    		}

	    	}
	    	
	    	public void Click_Invoice() {
	    		// TODO Auto-generated method stub
	    		try {
	    			Thread.sleep(2000);
	    			Sync.waitElementPresent("xpath", "//button[@id='order_invoice']");
	    			Common.clickElement("xpath", "//button[@id='order_invoice']");
	    			Sync.waitPageLoad();
	    			Common.assertionCheckwithReport(
	    					Common.getPageTitle().contains("New Invoice / Invoices / Operations / Sales / Magento Admin"),
	    					"To Validate the New Invoice page",
	    					"should display the New Invoice page after clicking on the Invoice",
	    					"New Invoice page is displayed after a click on the Invoic button",
	    					"Failed to display New Invoice page");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the New Invoice page",
	    					"should display the New Invoice page after clicking on the Invoice",
	    					"unable to display New Invoice page after a click on the Invoice button",
	    					"Failed to display New Invoice page");
	    			Assert.fail();
	    		}

	    	}
	    	
	    	public void Invoice_Payment(String dataSet) {
	    		// TODO Auto-generated method stub
	    		try {
	    			Thread.sleep(2000);
	    			Common.textBoxInput("xpath", "//textarea[@id='invoice_comment_text']",
	    					data.get(dataSet).get("InvoiceComments"));

	    			Common.clickElement("xpath", "//button[@title='Submit Invoice']");

	    			Common.assertionCheckwithReport(
	    					Common.getPageTitle().contains("Orders / Operations / Sales / Magento Admin"),
	    					"To Validate the Invoice payment",
	    					"should display the Order details page after clicking on the Submit Invoice",
	    					"Order details page is displayed after a click on the Submit Invoic button",
	    					"Failed to display Order details page");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the Invoice payment",
	    					"should display the Order details page after clicking on the Submit Invoice",
	    					"unable to display Order details page after a click on the Submit Invoice button",
	    					"Failed to display Order details page");
	    			Assert.fail();
	    		}

	    	}
	    	
	    	public void Click_Ship() {
	    		// TODO Auto-generated method stub
	    		try {
	    			Thread.sleep(2000);
	    			Sync.waitElementPresent("xpath", "//button[@id='order_ship']");
	    			Common.clickElement("xpath", "//button[@id='order_ship']");

	    			Common.assertionCheckwithReport(
	    					Common.getPageTitle().contains("New Shipment / Shipments / Operations / Sales / Magento Admin"),
	    					"To Validate the New Shipment page",
	    					"should display the New Shipment page page after clicking on the Ship",
	    					"New Shipment page is displayed after a click on the Ship button",
	    					"Failed to display New Shipment page");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the New Shipment page",
	    					"should display the New Shipment page after clicking on the Ship",
	    					"unable to display New Shipment page after a click on the Ship button",
	    					"Failed to display New Shipment page");
	    			Assert.fail();
	    		}

	    	}

	    	public void Ship_Payment(String dataSet) {
	    		// TODO Auto-generated method stub
	    		try {
	    			Thread.sleep(2000);
	    			Common.textBoxInput("xpath", "//textarea[@id='shipment_comment_text']",
	    					data.get(dataSet).get("ShipmentComments"));

	    			Common.clickElement("xpath", "//button[@title='Submit Shipment']");

	    			Common.assertionCheckwithReport(
	    					Common.getPageTitle().contains("Orders / Operations / Sales / Magento Admin"),
	    					"To Validate the Shipment Payment",
	    					"should display the Order details page after clicking on the Submit Shipment",
	    					" Order details page is displayed after a click on the Submit Shipment button",
	    					"Failed to display Order details page");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the Shipment Payment",
	    					"should display the Order details page after clicking on the Submit Shipment",
	    					"unable to display Order details page after a click on the Submit Shipment button",
	    					"Failed to display Order details page");
	    			Assert.fail();
	    		}

	    	}

	    	public void Click_Creditmemo() {
	    		// TODO Auto-generated method stub
	    		try {
	    			Thread.sleep(2000);
	    			Sync.waitElementPresent("xpath", "//button[@id='order_creditmemo']");
	    			Common.clickElement("xpath", "//button[@id='order_creditmemo']");
	    			Thread.sleep(2000);
	    			Common.clickElement("xpath", "//button[@data-role='action']//span[text()='OK']");
	    			Sync.waitPageLoad();
	    			Common.assertionCheckwithReport(
	    					Common.getPageTitle().contains("New Memo / Credit Memos / Operations / Sales / Magento Admin"),
	    					"To Validate the New Memo page",
	    					"should display the New Memo page page after clicking on the Credit Memo",
	    					"New Memo page is displayed after a click on the Credit Memo button",
	    					"Failed to display New Memo page");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the New Memo page",
	    					"should display the New Memo page after clicking on the Credit Memo",
	    					"unable to display New Memo page after a click on the Credit Memo button",
	    					"Failed to display New Memo page");
	    			Assert.fail();

	    		}

	    	}

	    	public void Creditmemo_create(String dataSet) {
	    		// TODO Auto-generated method stub
	    		try {
	    			Thread.sleep(2000);
	    			Common.textBoxInput("xpath", "//textarea[@id='creditmemo_comment_text']",
	    					data.get(dataSet).get("CreditmemoComments"));

	    			Common.clickElement("xpath", "//button[@title='Refund Offline']");

	    			Common.assertionCheckwithReport(
	    					Common.getPageTitle().contains("Orders / Operations / Sales / Magento Admin"),
	    					"To Validate the Order details page",
	    					"should display the Refund Offline page after clicking on the Refund Offline",
	    					"Order details page is displayed after a click on the Refund Offline button",
	    					"Failed to display Order details page");

	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the Order page",
	    					"should display the Order details page after clicking on the Refund Offline",
	    					"unable to display Order details page after a click on the Refund Offline button",
	    					"Failed to display Order details page");
	    			Assert.fail();
	    		}

	    	}

	    	public void Click_Product_Storemenu() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(2000);
					Common.clickElement("xpath", "//li[@class='item-catalog-attributes-attributes    level-2']");
					Sync.waitPageLoad();
				//	Sync.waitElementInvisible(30, "xpath", "//div[@class='page-wrapper']");
					Common.assertionCheckwithReport(
							Common.getPageTitle().contains("Product Attributes / Attributes / Stores / Magento Admin"),
							"To Validate the Storegmenu is displayed",
							"should display the Storegmenu after clicking on the customers",
							"Storegmenu  is displayed after a click on the Store button", "Failed to display Storegmenu");

				} catch (Exception | Error e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("To Validate the Storegmenu is displayed",
							"should display the Storegmenu after clicking on the Catalog",
							"unable to display Store menu after a click on the Store button",
							"Failed to display Store menu");
					Assert.fail();
				}
			}
	    	public void Creat_Newattribute(String dataset) {
	    		try {
	    			Sync.waitPageLoad();
	    			Thread.sleep(2000);
	    		//	Common.clickElement("id", "menu-magento-catalog-catalog");
	    			Common.textBoxInput("xpath", "//input[@id='attribute_label']", data.get(dataset).get("Attribute"));
	    			Thread.sleep(5000);
	    			Common.clickElement("xpath", "//button[@id='save']");
                     Thread.sleep(2000);
                     Sync.waitElementPresent(20, "xpath", "//div[text()='You saved the product attribute.']");
         	    	String sucessMessage = Common.getText("xpath", "//div[text()='You saved the product attribute.']");
         	    	Thread.sleep(3000);
         	    	System.out.println(sucessMessage);
         	    	Common.assertionCheckwithReport(sucessMessage.contains("You saved the product"),
        					"To Validate the sucess message is displayed",
        					"should display the sucess message after clicking on the sudmitorder",
        					"sucess message is displayed after a click on the sudmitorder button",
        					"Failed to display sucess message");
	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the catalog menu is displayed",
	    					"should display the catalog menu after clicking on the catalog",
	    					"unable to display catalog field menu after a click on the catalog button",
	    					"Failed to display catalog field menu");
	    			Assert.fail();
	    		}


	    	}
	    	public void updates_productAttribute(String dataset) {
	    		try {
	    			
	    			Sync.waitPageLoad();
	    			Thread.sleep(2000);
	    			Common.textBoxInput("xpath", "//input[@name='frontend_label']", data.get(dataset).get("Attribute"));
	    			Common.clickElement("xpath", "//span[text()='Search']");
	    			Thread.sleep(3000);
	    			
	    				Common.clickElement("xpath", "//td[contains(text(),'attr')]");
	    				Sync.waitPageLoad();
	    				Thread.sleep(4000);
	    			Common.textBoxInput("xpath", "//input[@id='attribute_label']", data.get(dataset).get("update Attribute"));
	    			Thread.sleep(5000);
	    			Common.clickElement("xpath", "//button[@id='save']");
                     Thread.sleep(2000);
                     Sync.waitElementPresent(20, "xpath", "//div[text()='You saved the product attribute.']");
         	    	String sucessMessage = Common.getText("xpath", "//div[text()='You saved the product attribute.']");
         	    	Thread.sleep(3000);
         	    	System.out.println(sucessMessage);
         	    	Common.assertionCheckwithReport(sucessMessage.contains("You saved the product"),
        					"To Validate the sucess message is displayed",
        					"should display the sucess message after clicking on the sudmitorder",
        					"sucess message is displayed after a click on the sudmitorder button",
        					"Failed to display sucess message");
	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the catalog menu is displayed",
	    					"should display the catalog menu after clicking on the catalog",
	    					"unable to display catalog field menu after a click on the catalog button",
	    					"Failed to display catalog field menu");
	    			Assert.fail();
	    		}


	    	}
	    	public void Delete_productAttribute(String dataset) {
	    		try {
	    			Sync.waitPageLoad();
	    			Thread.sleep(2000);
	    			Common.textBoxInput("xpath", "//input[@name='frontend_label']", data.get(dataset).get("update Attribute"));
	    			Common.clickElement("xpath", "//span[text()='Search']");
	    			Thread.sleep(3000);
	    			
	    				Common.clickElement("xpath", "//td[contains(text(),'attr')]");
	    				Sync.waitPageLoad();
	    				Thread.sleep(4000);
	    		Common.findElement("xpath", "//input[@id='attribute_label']");
	    			Common.clickElement("xpath", "//button[@title='Delete Attribute']");
	    			Thread.sleep(2000);
	    			Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
                     Thread.sleep(2000);
                     Sync.waitElementPresent(20, "xpath", "//div[text()='You deleted the product attribute.']");
         	    	String sucessMessage = Common.getText("xpath", "//div[text()='You deleted the product attribute.']");
         	    	Thread.sleep(3000);
         	    	System.out.println(sucessMessage);
         	    	Common.assertionCheckwithReport(sucessMessage.contains("You deleted the product"),
        					"To Validate the sucess message is displayed",
        					"should display the sucess message after clicking on the sudmitorder",
        					"sucess message is displayed after a click on the sudmitorder button",
        					"Failed to display sucess message");
	    		} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the catalog menu is displayed",
	    					"should display the catalog menu after clicking on the catalog",
	    					"unable to display catalog field menu after a click on the catalog button",
	    					"Failed to display catalog field menu");
	    			Assert.fail();
	    		}


	    	}
	    	public void Click_Addnewattribute(String Dataset) {
	    		try {
	    			Sync.waitPageLoad();
	    			Thread.sleep(2000);
	    			Common.textBoxInput("xpath", "//input[@name='frontend_label']", data.get(Dataset).get("Attribute"));
	    			Common.clickElement("xpath", "//span[text()='Search']");
	    			Thread.sleep(3000);
	    			if (Common.isElementDisplayed("xpath", "//td[contains(text(),'attr')]")) {
	    				Common.clickElement("xpath", "//td[contains(text(),'attr')]");
	    				Sync.waitPageLoad();
	    				Common.clickElement("xpath", "//button[@title='Delete Attribute']");
	    				Thread.sleep(2000);
	    				Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
	    				Thread.sleep(2000);
	    				Common.clickElement("xpath", "//button[@title='Add New Attribute']");
	    				Thread.sleep(4000);
	    				
	    			} else {
	    				
	    			Common.clickElement("xpath", "//button[@title='Add New Attribute']");
	    			Thread.sleep(5000);
	    			}
	    			String NewproductAttribute = Common.findElement("xpath", "//h1[@class='page-title']").getText();
	    			System.out.println(NewproductAttribute);
	    		Thread.sleep(2000);
	    			Common.assertionCheckwithReport(NewproductAttribute.contains("New Product Attribute"), "To Validate the New product Attribute page is displayed",
	    					"should display the New product Attribute page after clicking on the Add New Attribute",
	    					"New product Attribute page is displayed after a click on the Add New Attribute button", "Failed to display New product Attribute page");

	    				} catch (Exception | Error e) {
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("To Validate the New product Attribute page is displayed",
	    					"should display the New product Attribute page after clicking on the Add New Attribute",
	    					"unable to display New product Attribute field page after a click on the Add New Attribute button",
	    					"Failed to display New product Attribute field menu");
	    			Assert.fail();
	    		}


	    	}
}



	