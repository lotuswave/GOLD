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

	
}

	