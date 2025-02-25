package models.admin;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
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
public class GoldAdminHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public GoldAdminHelper(String datafile, String DataSet) {
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

	public void Admin_signin(String dataSet) {

		try {
			
			Sync.waitPageLoad();
			if (Common.getCurrentURL().contains("emea-preprod")) {
				
				Thread.sleep(12000);
				Common.assertionCheckwithReport(Common.getCurrentURL().contains("admin/dashboard/"),
						"To Validate the Admin is landing on the Dashboard after successfull Signin",
						"After clicking on sigin button admin should navigate to the dashboard",
						"Admin Sucessfully navigate to the dashboard after clicking on the signin button",
						"Admin failed to display the dashboard after clicking on the signin button");
			}
			else {
				Sync.waitPageLoad(30);
				/*
				 * Sync.waitElementPresent("xpath", "//input[@name='loginfmt']");
				 * Common.clickAndtextBoxInput("xpath", "//input[@name='loginfmt']",
				 * data.get(dataSet).get("UserName")); Common.clickElement("id", "idSIButton9");
				 * Sync.waitPageLoad(); Thread.sleep(3000); Sync.waitElementPresent(30, "name",
				 * "passwd"); Common.textBoxInput("name", "passwd",
				 * data.get(dataSet).get("Password")); Common.clickElement("id", "idSIButton9");
				 */
//	            Sync.waitElementVisible(30, "xpath", "//div[@id='lightbox']");
//	            if (Common.isElementDisplayed("id", "KmsiCheckboxField")) {
//	                Common.javascriptclickElement("id", "KmsiCheckboxField");
//	            }
				// Sync.waitElementClickable("id", "idSIButton9");
				// Common.mouseOverClick("id", "idSIButton9");

				Sync.waitElementPresent(30, "xpath", "//a[text()='Login via Identity Provider']");
				Common.clickElement("xpath", "//a[text()='Login via Identity Provider']");
				Thread.sleep(15000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Dashboard / Magento Admin"),
						"To Validate the Admin is landing on the Dashboard after successfull Signin",
						"After clicking on sigin button admin should navigate to the dashboard",
						"Admin Sucessfully navigate to the dashboard after clicking on the signin button",
						"Admin failed to display the dashboard after clicking on the signin button");
			}
		
			

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

	public void click_website_categoty(String Dataset) {
		// TODO Auto-generated method stub

	try {
		/*Common.switchToFirstTab();
	
		Search_previous_page_Magento(Dataset);
		Contentpage();
		hot_elements();
		PLPCMS_content();
		dragndrop_heroBanner();
		edit_Herobanner();*/
		
		String site = data.get(Dataset).get("Website");
		
		Common.dropdown("xpath", "//select[@class='admin__control-select url-input-select']", Common.SelectBy.TEXT,
				data.get(Dataset).get("heading"));
		Thread.sleep(2000);
		Common.clickElement("xpath", "//div[@class='admin__action-multiselect-text']");

		/*String text = Common
				.findElement("xpath", "(//input[@class='admin__control-text admin__action-multiselect-search'])")
				.getAttribute("id");
		Common.textBoxInput("xpath", "//input[@id='" + text + "']", data.get(Dataset).get("productnames"));*/
			
       if(site.equals("OXO"))
		
		{
			Common.findElement("xpath", "//label[text()='More OXO']");
			Thread.sleep(1000);
			Common.clickElement("xpath", "//label[text()='More OXO']");
		}
		
		
		else if(site.equals("Hydroflask"))
		
		{
			Common.findElement("xpath", "//label[text()='Customize']");
			Thread.sleep(1000);
			Common.clickElement("xpath", "//label[text()='Customize']");
		}
		
		else if(site.equals("Osprey"))
		{
			
			Common.findElement("xpath", "//label[text()='Backpacks & Bags']");
			Thread.sleep(1000);
			Common.clickElement("xpath", "//label[text()='Backpacks & Bags']");
		}
       
       else if(site.equals("Drybar"))
		{
			Common.findElement("xpath", "//label[text()='Hair Products']");
			Thread.sleep(1000);
			Common.clickElement("xpath", "//label[text()='Hair Products']");
		}
       
       else if(site.equals("OspreyEMEA"))
		{
			
			Common.findElement("xpath", "//label[text()='Backpacks & Bags']");
			Thread.sleep(1000);
			Common.clickElement("xpath", "//label[text()='Backpacks & Bags']");
		}
	
      // Configure_padding_marins(Dataset);
       
       
       Common.textBoxInput("xpath", "(//input[@name='link_text'])", data.get(Dataset).get("Buttontext"));
		Common.clickElement("xpath", "//input[@name='link_url']");
	
	//Sync.waitElementPresent("xpath", "//button[@id='save']");
	//Common.clickElement("xpath", "//button[@id='save']");
	}
	catch (Exception e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("To validate the CTA link navigation",
				"CTA link should navigate to the respective page", "CTA link navigation Failed",
				"CTA link navigation unsuccessfull");

		Assert.fail();
		
	}

	}
	public void herobanner_images(String DataSet) {
		
		/*String image = data.get(DataSet).get("image");
		String image2 = data.get(DataSet).get("image2");
		String altatt = data.get(DataSet).get("alterantivetext");*/
		try {
			Common.scrollIntoView("xpath", "//strong[@class='admin__collapsible-title']//span[text()='Image']");

			Sync.waitElementPresent("xpath", "(//div[@class='admin__field']//label[text()='Select from Gallery'])[1]");
			Common.javascriptclickElement("xpath",
					"(//div[@class='admin__field']//label[text()='Select from Gallery'])[1]");

			// Sync.waitElementInvisible("xpath", "//div[@class='loading-mask' and
			// @style='display: none;']");
			// Common.isElementVisibleOnPage(30, "xpath", "//div[@id='contents']");
			String site = data.get(DataSet).get("Website");
			
			if(site.equals("OXO"))
			{
			
			Common.scrollIntoView("xpath", "//img[@alt='handdone_1.jpg']");
			Common.javascriptclickElement("xpath", "//img[@alt='handdone_1.jpg']");
			}
			else if(site.equals("Hydroflask"))
			{
				Common.scrollIntoView("xpath", "//img[@alt='demo-desktop.png']");
				Common.javascriptclickElement("xpath", "//img[@alt='demo-desktop.png']");
			}
			else if(site.equals("Osprey"))
			{
				Common.scrollIntoView("xpath", "//img[@alt='Climbing_ALP_Collage_Portrait_1.jpeg']");
				Common.javascriptclickElement("xpath", "//img[@alt='Climbing_ALP_Collage_Portrait_1.jpeg']");
			}
			else if(site.equals("Drybar"))
			{
				Common.scrollIntoView("xpath", "//img[@alt='COVER_3.jpg']");
				Common.javascriptclickElement("xpath", "//img[@alt='COVER_3.jpg']");
			}
			
			else if(site.equals("OspreyEMEA"))
			{
				Common.scrollIntoView("xpath", "//img[@alt='hero_banner2.png']");
				Common.javascriptclickElement("xpath", "//img[@alt='hero_banner2.png']");
			}
			
			Sync.waitElementPresent("xpath", "//span[text()='Add Selected']");
			Common.javascriptclickElement("xpath", "//span[text()='Add Selected']");
			Common.scrollIntoView("xpath", "(//a/img[@class='preview-image'])[1]");
			String imagename = Common.findElement("xpath", "(//a/img[@class='preview-image'])[1]").getAttribute("alt");

			/*Common.assertionCheckwithReport(imagename.equals(image),
					"validation the image uploading on content for Fallback image ",
					"Image should be upload for background image", "Successfully image uploaded in background image ",
					"Failed to upload image on the background image");

			Sync.waitElementPresent("xpath", "(//div[@class='admin__field']//label[text()='Select from Gallery'])[2]");
			Common.javascriptclickElement("xpath",
					"(//div[@class='admin__field']//label[text()='Select from Gallery'])[2]");
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
			System.out.println(altatt);*/

		} catch (Exception | Error e) {
			e.printStackTrace();

			report.addFailedLog("validation Image upload  ", "Image should be uploaded",
					"Successfully image is uploaded",
					Common.getscreenShotPathforReport("Failed to navigate to edit promoBlocker page"));
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
		String website = data.get(Dataset).get("website");
		System.out.println(website);
		try {
			int filters = Common.findElements("xpath", "//div[@class='admin__data-grid-filters-current _show']").size();
			if (filters > 0) {
				Common.clickElement("xpath",
						"//div[@class='admin__data-grid-filters-current _show']//button[text()='Clear all']");
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//button[@data-action='grid-filter-expand']");
				Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
				Common.textBoxInput("xpath", "//input[@name='email']", data.get(Dataset).get("Email"));
				Common.clickElement("xpath",
						"//div[@class='admin__form-field-control']//option[text()='" + website + "']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//button[@class='action-secondary']//span[text()='Apply Filters']");
				Thread.sleep(3000);
//				Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
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

			} else {
				Sync.waitElementPresent("xpath", "//button[@data-action='grid-filter-expand']");
				Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
				Common.textBoxInput("xpath", "//input[@name='email']", data.get(Dataset).get("Email"));
				Common.clickElement("xpath",
						"//div[@class='admin__form-field-control']//option[text()='" + website + "']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//button[@class='action-secondary']//span[text()='Apply Filters']");
				Thread.sleep(3000);
//			Common.clickElement("x
//			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
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
			}
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
		String email = data.get(Dataset).get("Email");
		System.out.println(email);
		try {

			Common.clickElement("xpath", "//a[text()='Edit']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//span[text()='Account Information']");
			Common.clickElement("xpath", "//span[text()='Account Information']");
			Thread.sleep(4000);
			String customeremail = Common.findElement("xpath", "//input[@name='customer[email]']")
					.getAttribute("value");
			System.out.println(customeremail);
			if (customeremail.equals(email)) {
				Sync.waitElementPresent("xpath", "//button[@title='Delete Customer']");
				Common.javascriptclickElement("xpath", "//button[@title='Delete Customer']");
				Thread.sleep(4000);
				String message = Common
						.findElement("xpath", "//aside[contains(@class,'confirm _show')]//div[@class='modal-content']")
						.getText();
				if (message.contains("Are you sure you want to do this?")) {
					Thread.sleep(3000);
					Common.clickElement("xpath", "//span[text()='OK']");

				} else {
					Assert.fail();
				}
				Sync.waitPageLoad();
				Sync.waitElementPresent("xpath", "//div[@data-ui-id='messages-message-success']");
				String savemessage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']")
						.getText();
				Common.assertionCheckwithReport(
						Common.getPageTitle().equals("Customers / Customers / Magento Admin")
								&& savemessage.equals("You deleted the customer."),
						"Validating customers filed page navigation and customer deleted message",
						"after clicking save button it will navigate Customer filed page and message should be displayed",
						"Successfully navigate to Customer filed page and message is dispalyed",
						"Failed to navigate to Customer filed page");
			} else {
				Assert.fail();
			}
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
			String websitename = Common.findElement("xpath", "//select[@name='customer[website_id]']")
					.getAttribute("value");
			System.out.println(websitename);
			if (websitename.equals("Hydroflask Website") || websitename.equals("OXO Website")
					|| websitename.equals("Osprey US") || websitename.equals("Drybar Website")) {
				Common.clickElement("xpath", "//select[contains(@name,'customer[ex')]");
				Common.dropdown("xpath", "//select[contains(@name,'customer[ex')]", Common.SelectBy.TEXT,
						data.get(Dataset).get("State"));
			}

			Common.clickElement("xpath", "//select[@name='customer[gender]']");
			Common.dropdown("xpath", "//select[@name='customer[gender]']", Common.SelectBy.TEXT,
					data.get(Dataset).get("Gender"));
			Common.clickElement("xpath", "//select[@name='customer[sendemail_store_id]']");
//			Common.dropdown("xpath", "//select[@name='customer[sendemail_store_id]']", Common.SelectBy.TEXT,
//					data.get(Dataset).get("Welcome Email"));

			Common.actionsKeyPress(Keys.END);
			Sync.waitElementPresent("xpath", "//select[@name='customer[prodeal_status]']");
			Common.clickElement("xpath", "//select[@name='customer[prodeal_status]']");

			if (websitename.equals("Hydroflask Website")) {
				Common.dropdown("xpath", "//select[@name='customer[prodeal_status]']", Common.SelectBy.TEXT,
						data.get(Dataset).get("ProDeal"));
				Common.clickElement("xpath", "//button[@title='Save Customer']");
				Sync.waitPageLoad();
				Sync.waitElementPresent("xpath", "//div[@data-ui-id='messages-message-success']");
				String savemessage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']")
						.getText();
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
				Common.assertionCheckwithReport(
						Common.getPageTitle().equals("Customers / Customers / Magento Admin")
								&& savemessage.equals("You saved the customer."),
						"To validate the customers Field page navigation and saved message ",
						"After clicking save button it will navigate Customer Field page and it should be display save message",
						"Successfully navigate to Customer field page and save message has displayed",
						"Unable to navigate to the Customer field page and save message is not displayed");
			} else {

//			Sync.waitElementPresent("xpath", "//input[@name='customer[prodeal_accept_date]']");
//			Common.clickElement("xpath", "//input[@name='customer[prodeal_accept_date]']");
//			Common.dropdown("xpath", "//input[@name='customer[prodeal_accept_date]']", Common.SelectBy.TEXT,
//					data.get(Dataset).get("DOB"));

				Common.clickElement("xpath", "//button[@title='Save Customer']");
				Sync.waitPageLoad();
				Sync.waitElementPresent("xpath", "//div[@data-ui-id='messages-message-success']");
				String savemessage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']")
						.getText();
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
				Common.assertionCheckwithReport(
						Common.getPageTitle().equals("Customers / Customers / Magento Admin")
								&& savemessage.equals("You saved the customer."),
						"To validate the customers Field page navigation and saved message ",
						"After clicking save button it will navigate Customer Field page and it should be display save message",
						"Successfully navigate to Customer field page and save message has displayed",
						"Unable to navigate to the Customer field page and save message is not displayed");
			}
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
		String website = data.get(Dataset).get("website");
		System.out.println(website);
		try {
			Common.clickElement("xpath",
					"//div[@class='admin__current-filters-actions-wrap']//button[text()='Clear all']");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@data-action='grid-filter-expand']");
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			Common.textBoxInput("xpath", "//input[@name='email']", data.get(Dataset).get("Email"));
			Common.clickElement("xpath", "//div[@class='admin__form-field-control']//option[text()='" + website + "']");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//button[@class='action-secondary']//span[text()='Apply Filters']");
			Thread.sleep(3000);
			String records = Common.findElement("xpath", "//div[@class='admin__control-support-text']").getText();
			System.out.println(records);
			if (records.equals("1 records found")) {
				Common.clickElement("xpath", "//a[text()='Edit']");
				Common.assertionCheckwithReport(Common.getPageTitle().contains("Testing Qa / Customers"),
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
			Thread.sleep(4000);
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
			Thread.sleep(4000);
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
			Thread.sleep(4000);
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
			Thread.sleep(4000);
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
			Thread.sleep(3000);
			String newsletter = Common.findElement("xpath", "//label[@class='label admin__field-label']").getText();
			System.out.println(newsletter);
			if (newsletter.equals("Subscribed to Newsletter")) {
				Sync.waitElementPresent("xpath", "//label[@class='label admin__field-label']");
				Common.clickElement("xpath", "//label[@class='label admin__field-label']");
				String checkbox = Common.findElement("xpath", "//input[contains(@name,'subscription_status')]")
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
			Thread.sleep(4000);
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
		String email = data.get(Dataset).get("Email");
		System.out.println(email);
		String website = data.get(Dataset).get("website");
		System.out.println(website);
		try {
			int filters = Common.findElements("xpath", "//div[@class='admin__data-grid-filters-current _show']").size();
			if (filters > 0) {
				Common.clickElement("xpath",
						"//div[@class='admin__data-grid-filters-current _show']//button[text()='Clear all']");
				Thread.sleep(4000);
			}

			Sync.waitElementPresent("xpath", "//button[@data-action='grid-filter-expand']");
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			Common.textBoxInput("xpath", "//input[@name='email']", data.get(Dataset).get("Email"));
			Common.clickElement("xpath", "//div[@class='admin__form-field-control']//option[text()='" + website + "']");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//button[@class='action-secondary']//span[text()='Apply Filters']");
			Thread.sleep(4000);
			String records = Common.findElement("xpath", "//div[@class='admin__control-support-text']").getText();
			if (records.equals("1 records found")) {
				Common.clickElement("xpath", "//a[text()='Edit']");
			} else {
				Assert.fail();
			}
			Sync.waitPageLoad();
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//span[text()='Account Information']");
			Common.clickElement("xpath", "//span[text()='Account Information']");
			Thread.sleep(4000);
			String customeremail = Common.findElement("xpath", "//input[@name='customer[email]']")
					.getAttribute("value");
			System.out.println(customeremail);
			if (customeremail.equals(email)) {
				Sync.waitElementPresent("xpath", "//button[@title='Delete Customer']");
				Common.clickElement("xpath", "//button[@title='Delete Customer']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String message = Common.findElement("xpath", "//div[@class='modal-content']").getText();
				if (message.equals("Are you sure you want to do this?")) {
					Thread.sleep(4000);
					Common.clickElement("xpath", "//span[text()='OK']");

				} else {
					Assert.fail();
				}
				Sync.waitPageLoad();
				Sync.waitElementPresent("xpath", "//div[@data-ui-id='messages-message-success']");
				String savemessage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']")
						.getText();
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
				Common.assertionCheckwithReport(
						Common.getPageTitle().equals("Customers / Customers / Magento Admin")
								&& savemessage.equals("You deleted the customer."),
						"Validating customers filed page navigation and customer deleted message",
						"after clicking save button it will navigate Customer filed page and message should be dispalyed",
						"Successfully navigate to Customer filed page and message is dispalyed",
						"Failed to navigate to Customer filed page");
			} else {
				Assert.fail();
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating customers filed page navigation ",
					"after clicking save button it will navigate Customer filed page",
					"Unable to  navigate to the Customer filed page and message not dispalyed",
					Common.getscreenShotPathforReport("Failed to navigate to Customer filed page"));
			Assert.fail();
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
					"Validating the customer segment field navigation",
					"It should navigate to the customers segment fields",
					"Sucessfully navigates to the customer segment fields",
					"Failed to Navigate customer segments fields ");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the customer segment field navigation",
					"It should navigate to the customers segment fields",
					"Sucessfully navigates to the customer segment fields",
					Common.getscreenShotPathforReport("Failed to navigate to the customer segment fields"));
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

//			System.out.println(Common.getPageTitle());
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
					"Validating the Navigation to the customer segement page and save customer message",
					"It should navigate to the customer segment page and save message should be displayed",
					"Sucessfully navigated to the customer segement page and able to display the save message",
					"Failed to navigate to the customer segment page and not able to see the save message");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"Validating the Navigation to the customer segement page and save customer message",
					"It should navigate to the customer segment page and save message should be displayed",
					"Unable to navigated to the customer segement page and able to display the save message",
					Common.getscreenShotPathforReport(
							"Failed to navigate to the customer segment page and not able to see the save message"));
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

//	        Common.clickElement("xpath", "//textarea[@id='segment_description']");
			Common.textBoxInput("xpath", "//textarea[@id='segment_description']", data.get(dataSet).get("Description"));
			Common.scrollIntoView("xpath", "//select[@id='segment_is_active']");
			// Common.clickElement("xpath", "//select[@id='segment_is_active']");

			Common.dropdown("xpath", "//select[@id='segment_is_active']", Common.SelectBy.TEXT,
					data.get(dataSet).get("AssignedStatus"));

			Common.clickElement("id", "save");
			String message = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
			System.out.println(message);

			Common.assertionCheckwithReport(Common.getPageTitle().contains("Customer Segments"),
					"Validating the save edit customer segment message",
					"It should able to save edited customer segment", "Sucessfully  saves the Edited customer segment",
					"Failed to save the Edited Customer segment");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the save edit customer segment message",
					"It should able to save edited customer segment", "Unable to  save the Edited customer segment",
					Common.getscreenShotPathforReport("Failed to save the Edited Customer segment"));
			Assert.fail();
		}

	}

	public void Delete_customer_segment(String dataSet) {

		try {
			String segmentName = data.get(dataSet).get("SegmentName");
//			
			Common.clickElement("xpath", "//td[contains(text(),'" + segmentName + "')]");
//			Common.clickElement("xpath", "//td[text()='"+segmentName+ "']");
			Sync.waitPageLoad();
			System.out.println(segmentName);
			System.out.println(Common.getPageTitle());

			Common.assertionCheckwithReport(Common.getPageTitle().contains(segmentName),
					"validating the  delete existing customer segment field",
					"It should able to select the customer segment", "Sucessfully selects to the customer segment",
					"Failed to select the customer segment");

			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Delete')]");
			Common.clickElement("xpath", "//span[contains(text(),'Delete')]");
			Sync.waitElementClickable(50, "xpath", "(//div[@class='modal-content'])[2]");
			String message = Common.getText("xpath", "(//div[@class='modal-content'])[2]");
			if (message.equals("Are you sure you want to do this?")) {

				Common.clickElement("xpath", "//span[text()='OK']");
			} else {
				Assert.fail();
			}

			String message1 = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
			System.out.println(message1);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Customer Segments"),
					"Validating the  delete customer segment field", "It should able to delete the customer segment",
					"Sucessfully deletes the customer segment", " Failed to delete customer segment");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the  delete customer segment field",
					"It should able to delete the customer segment", "Unable to delete the customer segment",
					Common.getscreenShotPathforReport("Failed to delete the customer segment"));
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
			Common.assertionCheckwithReport(Common.getPageTitle().equals(pagetitle),
					"Validate the customer groups page", "Should navigate to the Customer groups page",
					"Successfully navigated to customer groups page", "Failed navigation to customer groups page");

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
			Apply_filter("UpdatecustomerGroup");
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
 if(Common.getCurrentURL().contains("emea")) {
	 System.out.println("All Stores will Select which are included in Dropdown");
 }else {
	 Select_website("Websites");
 }
			

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

	public void Select_website(String dataSet) throws Exception {

		if(Common.getCurrentURL().contains("emea")) {
			String[] websites = data.get(dataSet).get("emeaWebsite").split(",");

			for (int i = 0; i < websites.length; i++) {
				System.out.println(websites[i]);
				Sync.waitElementClickable("xpath", "//option[text()='" + websites[i] + "']");
				Common.clickElement("xpath", "//option[text()='" + websites[i] + "']");
			}
			
		}else {
			
		
		String[] websites = data.get(dataSet).get("website").split(",");

		for (int i = 0; i < websites.length; i++) {
			System.out.println(websites[i]);
			Sync.waitElementClickable("xpath", "//option[text()='" + websites[i] + "']");
			Common.clickElement("xpath", "//option[text()='" + websites[i] + "']");
		}
		
	}
	}
	public WebElement Cardtile_content() {

		WebElement element = Common.findElement("xpath", "//span[text()='Card Tiles']");
		Common.clickElement("xpath", "//span[text()='Card Tiles']");

		return element;

	}

	public void dragndrop_Cardtile_Content() {
		try {
			WebElement element = Common.findElement("xpath", "//span[text()='Card Tiles']");
			draganddropContentBlock(element);
			String blockname = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']/div")
					.getAttribute("data-content-type");
			Common.assertionCheckwithReport(blockname.equals("hot_card_tiles"),
					"Validating cardtiles Dragndrop operation", "card tiles dragndrop to content with options",
					"successfully dragndrop the card tile with options ", "fail to dragndrop the card tile");
		} catch (Exception e) {

			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("Validating card tile Dragndrop operation",
					"User should able Dragndrop cardtile", "Unable to Dragndrop the cardtile",
					Common.getscreenShotPathforReport("Failed to Dragndrop the cardtile"));
			Assert.fail();

		}
	}

	public void featuredcardconfiguration_image(String dataSet) {
		try {
			Sync.waitElementPresent("xpath", "//input[@placeholder='Enter title here']");
			Common.textBoxInput("xpath", "//input[@placeholder='Enter title here']",
					data.get(dataSet).get("CardTitle"));

			String selectimage = Common.findElement("xpath", "//li[@name='image']").getAttribute("class");
			if (selectimage.contains("active")) {
				System.out.println("image is selected");
			} else {
				Common.javascriptclickElement("xpath", "//li[@name='image']");
			}
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			edit_cardtile_image("ProductcardTile", 1);
			edit_cardtile_image("ProductcardTile", 2);
			Common.scrollIntoView("xpath", "//input[@name='alt']");
			Common.textBoxInput("xpath", "//input[@name='alt']", data.get(dataSet).get("alterantivetext"));
			Common.textBoxInput("xpath", "//input[@name='title_attribute']", data.get(dataSet).get("titleaatribute"));

			Common.switchFrames("id", "hot_card_tiles_category_form_description_text_ifr");
			Common.findElement("xpath", "//body[@class='mce-content-body ']/p")
					.sendKeys(data.get(dataSet).get("Description"));
			Common.switchToDefault();
			Thread.sleep(2000);
			buttontype("ProductcardTile");
			Common.textBoxInput("xpath", "//input[@name='button_text']", data.get(dataSet).get("Buttontext"));
			CTALink("ProductcardTile");
			Common.javascriptclickElement("xpath", "//label[text()='Open in new tab']");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate the admin is able to configure  the feautred images in the card tile",
					"Card tile content should be configured ", "Successfully configured the card tile content",
					Common.getscreenShotPathforReport("Failed to configure the card tile content"));

			Assert.fail();
		}
	}

	public void buttontype(String dataSet) {
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.dropdown("xpath", "//Select[@name='button_type']", SelectBy.TEXT, data.get(dataSet).get("Buttontype"));
	}

	public void edit_cardtile_image(String dataSet, int i) {

		String image = data.get(dataSet).get("image");
		try {
			System.out.println(i);
			Sync.waitElementPresent("xpath", "(//label[text()='Select from Gallery'])[" + i + "]");
			Common.scrollIntoView("xpath", "(//label[text()='Select from Gallery'])[" + i + "]");
			Common.javascriptclickElement("xpath", "(//label[text()='Select from Gallery'])[" + i + "]");

			Sync.waitElementInvisible("xpath", "//div[@class='loading-mask' and @style='display: none;']");
			Common.isElementVisibleOnPage(30, "xpath", "//div[@id='contents']");
			Sync.waitElementPresent("xpath", "(//small[contains(text(),'" + image + "')])[2]");
			Common.scrollIntoView("xpath", "(//small[contains(text(),'" + image + "')])[2]");
			Common.javascriptclickElement("xpath", "(//small[contains(text(),'" + image + "')])[2]");
			Common.actionsKeyPress(Keys.HOME);
			Sync.waitElementPresent("xpath", "//span[text()='Add Selected']");
			Common.javascriptclickElement("xpath", "//span[text()='Add Selected']");
			Sync.waitElementInvisible(30, "xpath", "//div[@class='loading-mask' and @style='display: none;']");
			Thread.sleep(6000);
			Common.scrollIntoView("xpath", "(//img[@class='preview-image'])[" + i + "]");
			String imagename = Common.findElement("xpath", "(//img[@class='preview-image'])[" + i + "]")
					.getAttribute("alt");

			Common.assertionCheckwithReport(imagename.contains(image), "validation the image uploading on content  ",
					"Image should be upload for background image", "Successfully image uploaded in background image ",
					"Failed to upload image on the background image");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("validation the image uploading on content for fallback image ",
					"Image should be upload for fallback image", "Successfully image uploaded the fallback image ",
					Common.getscreenShotPathforReport("Failed to upload image for fallback image"));
			Assert.fail();

		}

	}

	public void verifycardtileimage_frontend(String dataSet) {

		String configuredalternativetext = data.get(dataSet).get("alterantivetext");
		String configuredtitleattribute = data.get(dataSet).get("titleaatribute");

		try {
			String ImageName = data.get(dataSet).get("image");
			System.out.println(ImageName);
			Sync.waitElementVisible("xpath", "//ol[@class='m-breadcrumb__list']");

			Common.scrollIntoView("xpath", "//picture[@class='m-media-card__image']/source");
			String desktopimage = Common.findElement("xpath", "//picture[@class='m-media-card__image']/source")
					.getAttribute("srcset");
			System.out.println(desktopimage);
			String alternativetext = Common.findElement("xpath", "//picture[@class='m-media-card__image']/img")
					.getAttribute("alt");
			System.out.println(alternativetext);
			String titleattribute = Common.findElement("xpath", "//picture[@class='m-media-card__image']/img")
					.getAttribute("title");
			System.out.println(titleattribute);

			Common.assertionCheckwithReport(
					desktopimage.contains(ImageName) && alternativetext.contains(configuredalternativetext)
							&& titleattribute.contains(configuredtitleattribute),
					"To validate the desktop image is configured on the front end",
					"desktop image is dispalyed on the frotend", "desktop image dispalyed",
					"Failed to upload image on the background image");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Admin configured image on the front end ",
					"Admin configured image should be displayed on the frontend",
					"Desktop image displayed Successfully on the frontend",
					Common.getscreenShotPathforReport("Failed to display the image on the front end"));
			Assert.fail();

		}

	}

	public void category_cards_config(String dataSet) throws Exception {

		try {
			Common.scrollIntoView("xpath", "//div[@data-index='category_product_cards']");

			int existingcategories = Common.findElements("xpath", "//span[contains(@class,'multiselect-crumb')]/button")
					.size();
			if (existingcategories > 0) {
				for (int i = 0; i < existingcategories; i++) {
					Sync.waitElementClickable("xpath", "//span[contains(@class,'multiselect-crumb')]/button");
					Common.javascriptclickElement("xpath", "//span[contains(@class,'multiselect-crumb')]/button");
				}
			} else {
				System.out.println("No existing categories selected");
			}

			String opencategoryconfigurationmenu = Common
					.findElement("xpath", "//div[@data-index='category_product_cards']").getAttribute("class");
			if (opencategoryconfigurationmenu.contains("show")) {
				System.out.println("Category configuration menu is active");
			} else {
				Common.javascriptclickElement("xpath", "//span[text()='Category Cards Configuration']");
			}

			Common.clickElement("xpath", "(//div[contains(@class,'admin__action-multiselect-text')])[2]");
			Sync.waitElementVisible("xpath", "(//div[@class='action-menu _active'])");

			String[] categories = data.get(dataSet).get("Categorydisplay").split(",");

			for (int i = 1; i <= categories.length; i++) {

				System.out.println(categories[i - 1]);

				Common.scrollIntoView("xpath", "//label[text()='" + categories[i - 1] + "']");
				Sync.waitElementPresent("xpath", "//label[text()='" + categories[i - 1] + "']");
				Common.javascriptclickElement("xpath", "//label[text()='" + categories[i - 1] + "']");

				String configuredcategory_dispaly = Common
						.findElement("xpath", "(//span[contains(@class,'multiselect-crumb')]/span)[" + i + "]")
						.getText();

				Common.assertionCheckwithReport(configuredcategory_dispaly.contains(categories[i - 1]),
						"To validate the category selected is " + configuredcategory_dispaly,
						categories[i - 1] + "category should be selected",
						configuredcategory_dispaly + "category is selected", "Failed to add the " + categories[i - 1]);
			}

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the category selected", "category should be selected",
					"category is selected", "Failed to add the categories");
			Assert.fail();
		}

	}

	public void verifycategoriesdisplay(String dataSet) throws Exception {
		try {
			int categories = Common.findElements("xpath", "//div[contains(@data-element,'card_tiles')]/a/span").size();
			System.out.println(categories);

			String[] categoryselected = data.get(dataSet).get("Categorydisplay").split(",");

			for (int i = 1; i < categories; i++) {

				Sync.waitElementVisible("xpath", "(//div[contains(@data-element,'card_tiles')]/a/span)[" + i + "]");
				Common.scrollIntoView("xpath", "(//div[contains(@data-element,'card_tiles')]/a/span)[" + i + "]");
				String categorytile = Common
						.findElement("xpath", "(//div[contains(@data-element,'card_tiles')]/a/span)[" + i + "]")
						.getText();
				Common.mouseOverClick("xpath", "(//div[contains(@data-element,'card_tiles')]/a/span)[" + i + "]");
				Sync.waitPageLoad();

				Sync.waitElementPresent("xpath", "//ol[@class='m-breadcrumb__list']");
				Common.scrollIntoView("xpath", "//ol[@class='m-breadcrumb__list']");

				Common.assertionCheckwithReport(
						categorytile.contains(categoryselected[i - 1])
								&& Common.getPageTitle().contains(categoryselected[i - 1]),
						"To validate the user is navigating to the respective category when clicked on the category in new page",
						"Usre should navigate to the Respective category" + categoryselected[i - 1],
						"Usre navigated to respective category" + categoryselected[i - 1],
						"User failed to navigate to the category" + categoryselected[i - 1]);

				Common.navigateBack();
				Sync.waitPageLoad();
				Thread.sleep(5000);
			}
		} catch (Exception e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog(
					"To validate the user is navigating to the respective category when clicked on the category in new page",
					"Usre should navigate to the Respective category", "User failed to navigate to the category",
					Common.getscreenShotPathforReport("Failed to navigate to the category"));
			Assert.fail();

		}
	}

	public void Websiteverification_productcard(String dataSet) {
		String video = data.get(dataSet).get("VideoURL");
		String title = data.get(dataSet).get("CardTitle");
		String buttontext = data.get(dataSet).get("Buttontext");

		try {

			Sync.waitElementPresent(40, "xpath", "//div[contains(@class,'c-content-tiles ')]");
			String videoverification = Common.findElement("xpath", "//div[contains(@class,'m-video-player ')]")
					.getAttribute("data-video-src");
			System.out.println(videoverification);
			String Title = Common.findElement("xpath", "//h3[@class='c-content-tiles__heading']").getText();
			String Buttontext = Common.findElement("xpath", "//span[contains(@data-element,'button')]").getText();

			int products = Common.findElements("xpath", "//img[contains(@class,'m-cms-tile')]").size();
			System.out.println(products);

			String numberofproducts = data.get(dataSet).get("No.ofproductsdisplay");
			int number = Integer.valueOf(numberofproducts);
			System.out.println(number);

			String productnames[] = data.get(dataSet).get("productnames").split(",");

			for (int i = 1; i <= productnames.length; i++) {
				System.out.println(productnames[i - 1]);

				Sync.waitElementClickable("xpath", "(//img[contains(@class,'m-cms-tile')])[" + i + "]");

				String producturl = Common.findElement("xpath", "(//a[contains(@class,'m-cms-tile')])[" + i + "]")
						.getAttribute("href");
				System.out.println(producturl);
				String productname = Common.findElement("xpath", "(//span[contains(@class,'m-cms-tile')])[" + i + "]")
						.getText();

				System.out.println(productname);
				Common.mouseOverClick("xpath", "(//img[contains(@class,'m-cms-tile')])[" + i + "]");
				Sync.waitPageLoad();
				Sync.waitElementVisible("xpath", "//h1[contains(@class,'overview__title')]");

				String productnamePDP = Common.findElement("xpath", "//h1[contains(@class,'overview__title')]")
						.getText();
				System.out.println(productnamePDP);

				Common.assertionCheckwithReport(
						producturl.contains(productnames[i - 1]) || productname.contains(productnamePDP),
						"To validate the product navigation to the respectiuve PDP page",
						"When clicked on the product image it should navigate to the PDP page",
						"Successfully landed on the respective PDP page",
						"Failed to land on the pdp page of the product");

				Common.navigateBack();
			}

			Common.assertionCheckwithReport(
					videoverification.contains(video) && Title.contains(title) && Buttontext.contains(buttontext)
							&& number == products,
					"validating the video upload in the forntend website ", "video should de appear on fornt end page",
					"Successfully video is appeared on the frondend",
					"Failed to navigate to edit Product card tile page");

			Common.switchToFirstTab();

		}

		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validation video upload in the forntend website ",
					"Video should de appear on fornt end page", "Unable to upload the video on the front end",
					Common.getscreenShotPathforReport("Failed to navigate to edit cardtile page"));
			Assert.fail();

		}

	}

	public void tile_buttontext(String dataSet) throws Exception {
		String cardtitle = data.get(dataSet).get("CardTitle");
		String buttontext = data.get(dataSet).get("textbutton");
		try {
			Sync.waitElementClickable("xpath", "//h3[contains(@class,'tiles__heading')]");
			Common.findElement("xpath", "//h3[contains(@class,'tiles__heading')]").sendKeys(cardtitle);
			Common.scrollToElementAndClick("xpath", "//span[@class='placeholder-text']");
			Sync.waitElementClickable("xpath", "//span[@class='placeholder-text']");
			// Common.javascriptclickElement("xpath", "//span[@class='placeholder-text']");
			Common.findElement("xpath", "//span[@class='placeholder-text']").sendKeys(buttontext);
			savecontent("ProductcardTile");
			openwebsite("ProductcardTile");

			validate_tile_buttontext("ProductcardTile");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating button text in product tile",
					"User should able to add the button text", "Admin failed to add the button text",
					Common.getscreenShotPathforReport("Failed to edit the content of the page"));

			Assert.fail();

		}
	}

	public void validate_tile_buttontext(String dataSet) {
		String title = data.get(dataSet).get("CardTitle");
		String buttontext = data.get(dataSet).get("textbutton");

		try {

			String Title = Common.findElement("xpath", "//h3[@class='c-content-tiles__heading']").getText();
			System.out.println(Title);
			Common.scrollIntoView("xpath", "//span[contains(@data-element,'button')]");
			String Buttontext = Common.findElement("xpath", "//span[contains(@data-element,'button')]").getText();
			System.out.println(Buttontext);

			Common.assertionCheckwithReport(Title.contains(title) && Buttontext.contains(buttontext),
					"validation title and button text are updated in the forntend website ",
					"Button text and Title  should be  appear on fornt end page",
					"Successfully the button text and card title  is updated on the frondend",
					"Failed to navigate to edit Product card tile page");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating tile button text", "User should able to edit the button text",
					"button text validation is failed",
					Common.getscreenShotPathforReport("Failed to validate the button text"));
			Assert.fail();

		}
	}

	public void productcardconfiguration(String dataSet) {
		try {

			Sync.waitElementPresent("xpath", "//li[@name='product']");
			String Selectproduct = Common.findElement("xpath", "//li[@name='product']").getAttribute("class");

			if (Selectproduct.contains("active")) {
				System.out.println("Products tile is selected");
			} else {
				Common.javascriptclickElement("xpath", "//li[@name='product']");
			}
			Sync.waitElementInvisible("xpath", "//div[@class='loading-mask' and @style='display: none;']");
			Common.scrollIntoView("xpath", "//span[text()='Product Cards Configuration']");

			String Selectproductby = data.get(dataSet).get("CategorySelect");

			if (Selectproductby.contains("category_ids")) {
				Sync.waitElementClickable("xpath", "//div[contains(@class,'-multiselect-tree')]/div");
				Common.mouseOverClick("xpath", "//div[contains(@class,'-multiselect-tree')]/div");
				String active = Common.findElement("xpath", "//div[contains(@class,'-multiselect-tree')]")
						.getAttribute("class");
				Common.assertionCheckwithReport(active.contains("active"),
						"Validate the category dropdown is dispalyed", "Category dropdown should display",
						"Category dropdown is displayed", "Failed to display the category dropdown");
				Common.scrollIntoView("xpath", "(//label[text()='Shop'])[1]");
				Sync.waitElementClickable("xpath", "(//label[text()='Shop'])[1]");
				Common.javascriptclickElement("xpath", "(//label[text()='Shop'])[1]");
				Common.dropdown("xpath", "//select[@name='sort_order']", SelectBy.TEXT,
						data.get(dataSet).get("Filter"));
				Common.textBoxInput("xpath", "//input[@name='products_count']",
						data.get(dataSet).get("No.ofproductsdisplay"));

			} else if (Selectproductby.contains("sku")) {
				Common.javascriptclickElement("xpath", "//li[@name='" + Selectproductby + "']");
				Sync.waitElementClickable("xpath", "//input[@name='sku']");
				Common.textBoxInput("xpath", "//input[@name='sku']", data.get(dataSet).get("SKU"));
				Common.dropdown("xpath", "//select[@name='sort_order']", SelectBy.TEXT,
						data.get(dataSet).get("Filter"));
				Common.textBoxInput("xpath", "//input[@name='products_count']",
						data.get(dataSet).get("No.ofproductsdisplay"));

			} else if (Selectproductby.contains("condition")) {
				Common.javascriptclickElement("xpath", "//li[@name='" + Selectproductby + "']");
				Sync.waitElementClickable("xpath", "//img[@class='rule-param-add v-middle']");
				Common.clickElement("xpath", "//img[@class='rule-param-add v-middle']");
				Common.dropdown("name", "parameters[condition_source][1][new_child]", SelectBy.TEXT,
						data.get(dataSet).get("Condition"));
				Sync.waitElementPresent("xpath", "//ul[@class='rule-param-children']/li");
				Common.clickElement("xpath", "//ul[@class='rule-param-children']/li/span[2]");
				Common.textBoxInput("xpath", "parameters[condition_source][1--1][value]",
						data.get(dataSet).get("productcategory"));
				Common.clickElement("xpath", "//img[@title='Apply']");
				Common.dropdown("xpath", "//select[@name='sort_order']", SelectBy.TEXT,
						data.get(dataSet).get("Filter"));
				Common.textBoxInput("xpath", "//input[@name='products_count']",
						data.get(dataSet).get("No.ofproductsdisplay"));

			}

			Common.scrollIntoView("xpath", "//div[contains(@class,'header')]//span[text()='Save']");
			Sync.waitElementClickable("xpath", "//div[contains(@class,'header')]//span[text()='Save']");
			Thread.sleep(5000);
			Common.mouseOverClick("xpath", "//div[contains(@class,'header')]//span[text()='Save']");

			Sync.waitPageLoad();

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating product cards configuration	",
					"Admin should able to add the prodcut configuration", "Product card configuration failed",
					Common.getscreenShotPathforReport("Failed to add the product configuration"));

			Assert.fail();

		}

	}

	public void featurescardconfiguration_Video(String dataSet) {
		try {

			Common.switchToFirstTab();
			Contentpage();
			edit_block_content("ProductcardTile");

			Sync.waitElementPresent("xpath", "//input[@placeholder='Enter title here']");
			Common.textBoxInput("xpath", "//input[@placeholder='Enter title here']",
					data.get(dataSet).get("CardTitle"));

			String selectvideo = Common.findElement("xpath", "//li[contains(@class,'visual') and @name='video']")
					.getAttribute("class");
			if (selectvideo.contains("active")) {
				System.out.println("Video is selected");
			} else {
				Common.javascriptclickElement("xpath", "//li[contains(@class,'visual') and @name='video']");
			}
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Common.textBoxInput("xpath", "//input[@name='video_source']", data.get(dataSet).get("VideoURL"));
			Editcardtile_color("ProductcardTile");
			edit_cardtile_image("ProductcardTile", 1);

			Common.switchFrames("id", "hot_card_tiles_category_form_description_text_ifr");
			Common.findElement("xpath", "//body[@class='mce-content-body ']/p")
					.sendKeys(data.get(dataSet).get("Description"));
			Common.switchToDefault();
			Thread.sleep(2000);
			buttontype("ProductcardTile");
			Common.textBoxInput("xpath", "//input[@name='button_text']", data.get(dataSet).get("Buttontext"));
			CTALink("ProductcardTile");
			Common.javascriptclickElement("xpath", "//label[text()='Open in new tab']");

			Common.actionsKeyPress(Keys.HOME);

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("to validate featured configutaion",
					"User should be able to validate the featured coinfiguration ",
					"Admin failed to validate the featured confiration",
					Common.getscreenShotPathforReport("Failed featured configuration "));

			Assert.fail();

		}

	}

	public void Editcardtile_color(String Dataset) {
		String color = data.get(Dataset).get("Color");
		try {
			Common.scrollIntoView("xpath", "//div[@class='sp-preview-inner sp-clear-display']");
			Common.clickElement("xpath", "//div[@class='sp-preview-inner sp-clear-display']");
			// String color=data.get(Dataset).get(Dataset);
			Common.clickElement("xpath", "//span[@title='" + color + "']");
			Common.clickElement("xpath", "//button[text()='Apply']");
			String appliedcolor = Common.findElement("xpath", "//input[@class='colorpicker-spectrum']")
					.getAttribute("value");
			Common.assertionCheckwithReport(appliedcolor.equals(color), "validation of the color applied in the card ",
					"after Clicking on the color the color should be applied ",
					"Successfully color of card is applied ", "Failed to apply color");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("validation of the color applied", "after Clicking on the color it should be applied ",
					"Failed to apply colour ", Common.getscreenShotPathforReport("Failed to apply color"));
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

	public void click_content() {

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
			Sync.waitElementVisible(30, "xpath", "//div[@class='file-uploader-area']/label");
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

			String pagebuilderoptions = Common.findElement("xpath", "//div[contains(@class,'options-middle')]")
					.getAttribute("class");

			if (pagebuilderoptions.contains("show")) {
				Sync.waitElementVisible("xpath", "//i[contains(@class,'pagebuilder-systems')]");
				Common.javascriptclickElement("xpath", "//i[contains(@class,'pagebuilder-systems')]");
				Sync.waitPageLoad();
			} else {
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
			Sync.waitElementPresent("xpath", "//input[@name='title']");
			Common.switchFrames("id","hot_hero_banner_form_title_ifr");
			Common.clickAndtextBoxInput("xpath","//body[@data-id='hot_hero_banner_form_title']", data.get(DataSet).get("title"));
//			Common.textBoxInput("xpath","//body[@data-id='hot_hero_banner_form_title']", data.get(DataSet).get("title"));
			Common.switchToDefault();
//			Common.dropdown("xpath", "(//button[@title='Format Paragraph'])[1]", Common.SelectBy.TEXT,
//					data.get(DataSet).get("headingtype"));
			Thread.sleep(3000);
			Common.switchFrames("id","hot_hero_banner_form_subtitle_ifr");
			Sync.waitElementPresent("xpath", "//body[@data-id='hot_hero_banner_form_subtitle']");
			Common.clickAndtextBoxInput("xpath", "//body[@data-id='hot_hero_banner_form_subtitle']", data.get(DataSet).get("subtitle"));
//			Common.dropdown("xpath", "(//button[@title='Format Paragraph'])[2]", Common.SelectBy.TEXT,
//					data.get(DataSet).get("headingtype"));
			Common.switchToDefault();
			Common.scrollIntoView("xpath", "//span[text()='Description']");
			Common.switchFrames("id", "hot_hero_banner_form_description_ifr");
			Common.findElement("id", "html-body").sendKeys(data.get(DataSet).get("Description"));
			Common.switchToDefault();
			// Thread.sleep(2000);
			Thread.sleep(3000);

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
			Common.assertionCheckwithReport(image.equals("Lotusqa.png"),
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
			// Common.clickElement("xpath", "//span[text()='Background Repeat']");

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

			Common.assertionCheckwithReport(
					imageverification.contains("Lotusqa") && imageverification2.contains("Lotusqa1"),
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
		String video = data.get(Dataset).get("VideoURL");
		try {

			Common.switchToFirstTab();
			Search_previous_page_Magento(Dataset);
			Contentpage();
			edit_Herobanner();
			Common.findElement("xpath", "(//button[@class='action-remove'])[1]");
			Common.clickElement("xpath", "(//button[@class='action-remove'])[1]");

			Sync.waitElementPresent("xpath", "//input[@name='video_element[video_url]']");

			Common.textBoxInput("xpath", "//input[@name='video_element[video_url]']",
					data.get(Dataset).get("VideoURL"));
			Common.clickElement("xpath", "(//div[contains(@class,'sp-preview-')])[2]");

			Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Sync.waitElementPresent("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");

		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validation of video upload ",
					"after Clicking on thesave the video should be applied ", "Unable to save the  video url ",
					Common.getscreenShotPathforReport("Failed to save the video url"));
			Assert.fail();
		}
	}

	public void website_verification_video_Herobanner() {

		try {

			Thread.sleep(4000);

			Sync.waitElementPresent(50, "xpath", "//div[@data-element='video_element']");

			int video = Common.findElements("xpath", "//div[@data-element='video_element']").size();
			System.out.println(video);
			Common.assertionCheckwithReport(video > 0, "validation video upload in the forntend website ",
					"video should  appear on front end pag", "Successfully video is appeared on the frond end",
					"Failed to navigate to appear on fornt end page");

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
			Search_previous_page_Magento(Dataset);
			Contentpage();
			hot_elements();
			edit_Herobanner();
			Common.dropdown("xpath", "//select[@class='admin__control-select url-input-select']", Common.SelectBy.TEXT,
					data.get(Dataset).get("heading"));
			Common.clickElement("xpath", "//div[@class='admin__action-multiselect-wrap action-select-wrap']");

			String text = Common
					.findElement("xpath", "(//input[@class='admin__control-text admin__action-multiselect-search'])")
					.getAttribute("id");
			Common.textBoxInput("xpath", "//input[@id='" + text + "']", data.get(Dataset).get("productnames"));
			Sync.waitElementPresent("xpath", "//span[text()='32 oz Wide Mouth - Laguna']");
			Common.mouseOverClick("xpath", "//span[text()='32 oz Wide Mouth - Laguna']");

			Common.textBoxInput("xpath", "(//input[@name='link_text'])", data.get(Dataset).get("Buttontext"));
			Common.clickElement("xpath", "//input[@name='link_url']");
			int saved = Common.findElements("xpath", "(//div[@class='admin__fieldset-wrapper-content _hide'])").size();
			System.out.println(saved);
			Common.assertionCheckwithReport(saved > 0, "Enter the categroy details ",
					"Category details enterd succesfully", "Successfully v", "Failed to enterd the category details");
			Configure_padding_marins(Dataset);
			Sync.waitElementPresent("xpath", "//button[@id='save']");
			Common.clickElement("xpath", "//button[@id='save']");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(" To Validate the User needs to save the page",
					"User should able to save the page", "Unable to saves the page",
					Common.getscreenShotPathforReport("Failed to save the page"));
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
			
			Sync.waitElementClickable("xpath", "//div[contains(@class,'pagebuilder-emp')]");
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
			Configure_padding_marins(Dataset);
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
		System.out.println(pagetitle);
		try {
			Sync.waitPageLoad(60);

			String currentAdminURL = Common.getCurrentURL();
			String urlkey = pagetitle.toLowerCase();
			System.out.println(urlkey);
			Common.openNewTab();
			if (currentAdminURL.contains("preprod")) {
				Common.oppenURL(data.get(Dataset).get("preprodURL") + urlkey);
			} else {
				Common.oppenURL(data.get(Dataset).get("prodURL") + urlkey);

				Common.oppenURL(data.get(Dataset).get("prodURL") + urlkey);

			}
			Sync.waitPageLoad(40);
			String uname = Common.getPageTitle();
			System.out.println(uname);
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
			Search_previous_page_Magento("promocontent");
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
		String title = data.get(Dataset).get("pageTitle");
		try {
			Common.closeCurrentWindow();
			Common.switchToFirstTab();
			Search_previous_page_Magento(Dataset);

			Sync.waitElementPresent(40, "xpath", "//h1[@class='page-title']");
			String name1 = Common.findElement("xpath", "//h1[@class='page-title']").getText();
			if (name1.equals(title)) {
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

			String style = Common.findElement("xpath", "//main[@id='maincontent']//style").getAttribute("innerHTML");
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
			// Common.switchWindows();
			Thread.sleep(5000);
			String headingverification = Common.getText("xpath", "//div[@class='c-product-overview u-container']");
			System.out.println(headingverification);
			Common.assertionCheckwithReport(headingverification.contains("32 oz Wide Mouth - Laguna"),
					"validation of PDP page in the forntend website ", "PDP should be appear on fornt end page",
					"Successfully PDP is appeared on the frondend", "Failed to navigate to PDP page");

			Common.switchToFirstTab();
		} catch (Exception | Error e) {
			e.printStackTrace();

			report.addFailedLog("validation of PDP page in the forntend website ",
					"PDP should de appear on fornt end page", "Successfully image is appeared on the frondend",
					Common.getscreenShotPathforReport("Failed to navigate to PDP page"));
			Assert.fail();
		}

	}

	public void clone_Hero_banner_backgroundvideo(String Dataset) {
		// TODO Auto-generated method stub
		try {

			Sync.waitElementPresent("xpath", "(//input[@name='title'])[2]");
			Common.textBoxInput("xpath", "(//input[@name='title'])[2]", data.get(Dataset).get("tilte2"));

			Common.findElement("xpath", "(//input[@name='video_element[video_url]'])");
			Common.textBoxInputClear("xpath", "(//input[@name='video_element[video_url]'])");

			Sync.waitElementPresent("xpath", "(//li//span[text()='Video'])[2]");

			Common.clickElement("xpath", "(//li//span[text()='Video'])[2]");

			Common.textBoxInput("xpath", "//input[@name='video_source']", data.get(Dataset).get("VideoB"));

			Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Sync.waitElementPresent("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the User need to save the page",
					"User should able to save the page", "Unable to save the page",
					Common.getscreenShotPathforReport("Unable to save the page"));
			Assert.fail();

		}

	}

	public void clone_Hero_banner() {
		// TODO Auto-generated method stub

		try {

			Common.switchToFirstTab();
			Search_previous_page_Magento("Herobanner");
			Contentpage();
			Sync.waitElementPresent(30, "xpath", "//div[contains(@class,'c-hero-block__image')]");
			Common.mouseOver("xpath", "//div[contains(@class,'c-hero-block__image')]");
			Sync.waitElementPresent("xpath", "//a[@title='Duplicate']");
			Common.clickElement("xpath", "//a[@title='Duplicate']");
			edit_Herobanner();

			clone_Hero_banner_backgroundvideo("Herobanner");
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

	public void herobanner_clone_frontend(String Datset) {
		// TODO Auto-generated method stub
		String video = data.get(Datset).get("VideoB");
		String title = data.get(Datset).get("tilte2");

		try {
			Sync.waitPageLoad(40);

			String Video = Common.findElement("xpath", "//div[contains(@data-video-src,'665626770')]")
					.getAttribute("data-video-src");
			System.out.println(Video);
			String headingname = Common.getText("xpath", "(//h2[@class='c-hero-block__headline'])[1]");
			System.out.println(headingname);
			Common.assertionCheckwithReport(Video.contains("665626770") && headingname.contains(title),
					"validating the text on clone hero banner on fornt end",
					"Text should be add for the clone hero baneer on fornt end",
					"Successfully text added to the  clone hero banner on front end",
					"Failed to add text on the clone hero banner on front end");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the text on clone hero banner on fornt end",
					"Text should be add for the clone hero baneer on fornt end",
					"Unable to add the text on the clone hero banner on front end",
					Common.getscreenShotPathforReport("Failed to add text on the clone hero banner on front end"));
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
					title.contains(Title) && Heading.contains(heading) && CTAtext.contains(ctatext),
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
		try {
			String textcolor = Common.findElement("xpath", "//div[contains(@class,'block__content-')]")
					.getAttribute("class");
			Common.assertionCheckwithReport(textcolor.contains("dark"), "To validate the text color is dark",
					"The text color should be Dark", "The text color is Dark", "Failed to update the text color dark");
		} catch (Exception e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("To validate the text color is dark", "The text color should be dark",
					"Failed to update the text color dark", "Text color dark unsuccessfull");
			Assert.fail();
		}
	}

	public void valiadtetextcolorlight() {
		try {
			String textcolor = Common.findElement("xpath", "//div[contains(@class,'block__content-')]")
					.getAttribute("class");
			Common.assertionCheckwithReport(textcolor.contains("light"), "To validate the text color is Light",
					"The text color should be Light", "The text color is Light",
					"Failed to update the text color Light");
		} catch (Exception e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("To validate the text color is Light", "The text color should be Light",
					"Failed to update the text color light", "Text color light unsuccessfull");
			Assert.fail();
		}
	}

	public void CTA_LinksValidationFontend(String DataSet) throws Exception {
		String CTAnavigation = data.get(DataSet).get("ButtonLinknavigation");
		String ctatext = data.get(DataSet).get("Buttontext");

		try {
			Common.clickElement("xpath", "//span[contains(text(),'" + ctatext + "')]");
			Common.switchWindows();
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Sync.waitElementVisible("xpath", "//p[@class='m-breadcrumb__text']");
			String pagetitle = Common.getPageTitle();
			System.out.println(pagetitle);

			if (CTAnavigation.equals("Product")) {
				String product = data.get(DataSet).get("Buttonlinkproduct");
				System.out.println(product);

				Thread.sleep(4000);

				Common.assertionCheckwithReport(pagetitle.contains(product), "To validate the CTA Product link",
						"CTA link should navigate to the product page", "CTA link navigated to product page",
						"Failed CTA Product Link navigation");

			} else if (CTAnavigation.equals("Page")) {
				String page = data.get(DataSet).get("Buttonlinkpage");

				Common.assertionCheckwithReport(pagetitle.contains(page), "To validate the CTA Product link",
						"CTA link should navigate to the product page", "CTA link navigated to product page",
						"Failed CTA Product Link navigation");

			} else if (CTAnavigation.equals("Category")) {
				String Category = data.get(DataSet).get("Buttonlinkcategory");

				Common.assertionCheckwithReport(pagetitle.contains(Category), "To validate the CTA Product link",
						"CTA link should navigate to the product page", "CTA link navigated to product page",
						"Failed CTA Product Link navigation");

			} else if (CTAnavigation.equals("URL")) {
				String url = data.get(DataSet).get("ButtonlinkURL");
				Common.assertionCheckwithReport(Common.getCurrentURL().contains(url),
						"To validate the CTA link navigate to URL", "CTA link should navigate to the url",
						"CTA link navigated to url", "Failed CTA Link navigation to URL");

			}
			Common.navigateBack();
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//p[@class='m-breadcrumb__text']");

		} catch (Exception e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("To validate the CTA link navigation",
					"CTA link should navigate to the respective page", "CTA link navigation Failed",
					"CTA link navigation unsuccessfull");

			Assert.fail();
		}
	}

	public void Clone(String dataSet) {
		String component = data.get(dataSet).get("component");
		try {
			Sync.waitElementPresent("xpath", "//div[contains(@class,'" + component + "')]");
			Common.mouseOver("xpath", "//div[contains(@class,'" + component + "')]");
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
			Sync.waitElementClickable("xpath", "//div[contains(@class,'" + attribute + "')]");
			int components = Common.findElements("xpath", "//div[contains(@class,'" + attribute + "')]").size();
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

			Common.javascriptclickElement("xpath", "//i[@class='icon-pagebuilder-hide']");
			Common.isElementDisplayedonPage(10, "xpath", "//i[@class='icon-pagebuilder-show']");
			/*
			 * String hide = Common.findElement("xpath", "")
			 * Common.assertionCheckwithReport(visible.contains("-options-visible"),
			 * "validation Edit option  in the page builder ",
			 * "after mouse over on the page builder edit option should be appear ",
			 * "Successfully edit option appeared in the pagebuilder",
			 * "Failed to appear edit option when we mouse over");
			 */savecontent("ProductcardTile");
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
			openwebsite("OXOCLPHerobanner");
			verifydeletefunctionality_website(dataSet);

		} catch (Exception e) {
			e.printStackTrace();
			// invalidformkey("ProductcardTile");
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

			int blocks = Common.findElements("xpath", "//div[@data-content-type='" + attribute + "']").size();
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

			int textcolorupdate = Common.findElements("xpath", "//li[@class='active']//span[text()='Dark']").size();
			Common.assertionCheckwithReport(textcolorupdate > 0, "To validate the text colour selected is Dark",
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

			Sync.waitElementPresent("xpath", "(//div[@class='admin__field']//label[text()='Select from Gallery'])[1]");
			Common.javascriptclickElement("xpath",
					"(//div[@class='admin__field']//label[text()='Select from Gallery'])[1]");

			// Sync.waitElementInvisible("xpath", "//div[@class='loading-mask' and
			// @style='display: none;']");
			// Common.isElementVisibleOnPage(30, "xpath", "//div[@id='contents']");
			Thread.sleep(30000);
			Common.scrollIntoView("xpath", "//img[@alt='" + image + "']");
			Common.javascriptclickElement("xpath", "//img[@alt='" + image + "']");		
			Sync.waitElementPresent("xpath", "//span[text()='Add Selected']");
			Common.javascriptclickElement("xpath", "//span[text()='Add Selected']");
			Common.scrollIntoView("xpath", "(//a/img[@class='preview-image'])[1]");
			String imagename = Common.findElement("xpath", "(//a/img[@class='preview-image'])[1]").getAttribute("alt");

			Common.assertionCheckwithReport(imagename.equals(image),
					"validation the image uploading on content for Fallback image ",
					"Image should be upload for background image", "Successfully image uploaded in background image ",
					"Failed to upload image on the background image");

			Sync.waitElementPresent("xpath", "(//div[@class='admin__field']//label[text()='Select from Gallery'])[2]");
			Common.javascriptclickElement("xpath",
					"(//div[@class='admin__field']//label[text()='Select from Gallery'])[2]");
			
			Thread.sleep(60000);
			Common.scrollIntoView("xpath", "//small[text()='" + image2 + "']");
		      Common.scrollToElementAndClick("xpath", "//small[text()='" + image2 + "']");
        
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
			Common.scrollIntoView("xpath", "//select[contains(@class,'select ')]");
			Common.dropdown("xpath", "//select[contains(@class,'select ')]", SelectBy.TEXT, buttonlinknavugation);

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
			String blocknames = Common
					.findElement("xpath",
							"//div[@class='pagebuilder-content-type pagebuilder-hot-category-product-slider']")
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

		} catch (Exception | Error e) {
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

			String id = Common.findElement("xpath", "(//input[@name='title'])[2]").getAttribute("id");
			Common.textBoxInput("xpath", "//input[@id='" + id + "']", data.get(Dataset).get("title"));
			Common.textBoxInput("xpath", "//input[@name='subtitle']", data.get(Dataset).get("subtitle"));
			Thread.sleep(2000);
			Common.textBoxInput("xpath", "//input[@name='link_url[default]']", data.get(Dataset).get("CTAurl"));
			Common.clickElement("xpath", "//div[@class='admin__field admin__field-option url-input-setting']");
			Common.textBoxInput("xpath", "//input[@name='button_text']", data.get(Dataset).get("Buttontext"));

			int saved = Common.findElements("xpath", "(//div[@class='admin__fieldset-wrapper-content _hide'])[2]")
					.size();
			System.out.println(saved);
			Common.assertionCheckwithReport(saved > 0, "Enter the categroy details ",
					"Category details enterd succesfully", "Successfully v", "Failed to enterd the category details");
			Common.clickElement("xpath", "//button[@id='save']");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(" To Validate the User needs to save the page",
					"User should able to save the page", "Unable to saves the page",
					Common.getscreenShotPathforReport("Failed to save the page"));
			Assert.fail();
		}
	}

	public void website_verification_categroeyslider() {
		// TODO Auto-generated method stub
		try {

			Sync.waitElementPresent(40, "xpath",
					"//a[@class='c-product-carousel-transparent__cta pagebuilder-button-primary btn-visible']");
			Common.clickElement("xpath",
					"//a[@class='c-product-carousel-transparent__cta pagebuilder-button-primary btn-visible']");
			Thread.sleep(2000);
			// Common.switchWindows();
			Sync.waitPageLoad();
			Common.refreshpage();
			int logo = Common.findElements("xpath", "//div[@class='c-header__logo']").size();
			System.out.println();
			Common.assertionCheckwithReport(logo > 0, "Validation of Homepage ",
					"Homepage should appear on the front end", "Successfully homepage is appeared on the frontend",
					"Failed to navigate to homepage");
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

			String text = Common
					.findElement("xpath", "(//input[@class='admin__control-text admin__action-multiselect-search'])[2]")
					.getAttribute("id");
			Common.textBoxInput("xpath", "//input[@id='" + text + "']", data.get(Dataset).get("productnames"));
			Sync.waitElementPresent("xpath", "//span[text()='32 OZ WIDE MOUTH STAINLESS']");
			Common.mouseOverClick("xpath", "//span[text()='32 OZ WIDE MOUTH STAINLESS']");

			Common.textBoxInput("xpath", "//input[@name='button_text']", data.get(Dataset).get("Buttontext"));
			int saved = Common.findElements("xpath", "(//div[@class='admin__fieldset-wrapper-content _hide'])[2]")
					.size();
			System.out.println(saved);
			Common.assertionCheckwithReport(saved > 0, "Enter the categroy details ",
					"Category details enterd succesfully", "Successfully v", "Failed to enterd the category details");
			Common.clickElement("xpath", "//button[@id='save']");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(" To Validate the User needs to save the page",
					"User should able to save the page", "Unable to saves the page",
					Common.getscreenShotPathforReport("Failed to save the page"));
			Assert.fail();
		}
	}

	public void Websiteverification_product() {
// TODO Auto-generated method stub
		try {
			// Common.refreshpage();
			Sync.waitElementPresent(40, "xpath",
					"//a[@class='c-product-carousel-transparent__cta pagebuilder-button-primary btn-visible']");
			Common.clickElement("xpath",
					"//a[@class='c-product-carousel-transparent__cta pagebuilder-button-primary btn-visible']");
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
			String id = Common.findElement("xpath", "(//input[@name='title'])[2]").getAttribute("id");
			Common.textBoxInput("xpath", "//input[@id='" + id + "']", data.get(Dataset).get("title"));

			Common.dropdown("xpath", "//select[@class='admin__control-select url-input-select']", Common.SelectBy.TEXT,
					data.get(Dataset).get("CategorySelect"));
			Common.clickElement("xpath", "(//div[@class='action-menu-item'])[1]");
			Common.textBoxInput("xpath", "//input[@name='button_text']", data.get(Dataset).get("Buttontext"));
			int saved = Common.findElements("xpath", "(//div[@class='admin__fieldset-wrapper-content _hide'])[2]")
					.size();
			System.out.println(saved);
			Common.assertionCheckwithReport(saved > 0, "Enter the categroy details ",
					"Category details enterd succesfully", "Successfully v", "Failed to enterd the category details");
			Common.clickElement("xpath", "//button[@id='save']");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(" To Validate the User needs to save the page",
					"User should able to save the page", "Unable to saves the page",
					Common.getscreenShotPathforReport("Failed to save the page"));
			Assert.fail();
		}
	}

	public void Websiteverification_category() {
// TODO Auto-generated method stub
		try {
			// Common.refreshpage();
			Sync.waitElementPresent(40, "xpath",
					"//a[@class='c-product-carousel-transparent__cta pagebuilder-button-primary btn-visible']");
			Common.clickElement("xpath",
					"//a[@class='c-product-carousel-transparent__cta pagebuilder-button-primary btn-visible']");
			Common.switchWindows();
			Thread.sleep(2000);
			Common.scrollIntoView("xpath", "//p[@class='m-breadcrumb__text']");
			Thread.sleep(1000);
			String shop = Common.getText("xpath", "//p[@class='m-breadcrumb__text']");
			Thread.sleep(2000);
			System.out.println(shop);
			Common.assertionCheckwithReport(shop.contains("Shop"), "validation shop categroy in the forntend website ",
					"Shop category should be appear on fornt end page",
					"Successfully Shop category is appeared on the frondend",
					"Failed to navigate to Shop category page");
		} catch (Exception | Error e) {
			e.printStackTrace();

			report.addFailedLog("validation Shop category in the forntend website ",
					"Shop category should be appear on fornt end page",
					"Successfully Shop category is appeared on the frondend",
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
			Common.textBoxInput("xpath", "//input[@id='" + id + "']", data.get(Dataset).get("title"));
			Common.dropdown("xpath", "//select[@class='admin__control-select url-input-select']", Common.SelectBy.TEXT,
					data.get(Dataset).get("ButtonLinknavigation"));

			Common.clickElement("xpath", "//span[text()='503 Service Unavailable']");

			Common.textBoxInput("xpath", "//input[@name='button_text']", data.get(Dataset).get("Buttontext"));
			int saved = Common.findElements("xpath", "(//div[@class='admin__fieldset-wrapper-content _hide'])[2]")
					.size();
			System.out.println(saved);
			Common.assertionCheckwithReport(saved > 0, "Enter the categroy details ",
					"Category details enterd succesfully", "Successfully v", "Failed to enterd the category details");
			Common.clickElement("xpath", "//button[@id='save']");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(" To Validate the User needs to save the page",
					"User should able to save the page", "Unable to saves the page",
					Common.getscreenShotPathforReport("Failed to save the page"));
			Assert.fail();
		}
	}

	public void Websiteverification_page() {
// TODO Auto-generated method stub

		try {
			// Common.refreshpage();
			Sync.waitElementPresent(40, "xpath",
					"//a[@class='c-product-carousel-transparent__cta pagebuilder-button-primary btn-visible']");
			Common.clickElement("xpath",
					"//a[@class='c-product-carousel-transparent__cta pagebuilder-button-primary btn-visible']");
			Common.switchWindows();
			Thread.sleep(2000);
			String page = Common.getText("xpath", "//p[@class='m-breadcrumb__text']");
			System.out.println(page);
			Common.assertionCheckwithReport(page.contains("503 Service Unavailable"),
					"validation 503 Service Unavailable page in the forntend website ",
					"503 Service Unavailable page should be appear on fornt end page",
					"Successfully 503 Service Unavailable page is appeared on the frondend",
					"Failed to navigate to 503 Service Unavailable page");
		} catch (Exception | Error e) {
			e.printStackTrace();

			report.addFailedLog("validation 503 Service Unavailable page in the forntend website ",
					"503 Service Unavailable page should be appear on fornt end page",
					"Successfully 503 Service Unavailable page is appeared on the frondend",
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
			Common.assertionCheckwithReport(page > 0, " To Validate the User needs to save the page",
					"User should able to save the page", "Sucessfully User saves the page", "Failed to save the page");

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

			// Common.clickElement("xpath", "//h2[@class='m-heading__text
			// placeholder-text']");
			String id = Common.findElement("xpath", "(//input[@class='admin__control-text'])[6]").getAttribute("id");

			Common.textBoxInput("xpath", "//input[@id='" + id + "']", data.get(Dataset).get("title"));

			String att = Common.findElement("xpath", "(//input[@class='admin__control-text'])[7]").getAttribute("id");

			Common.textBoxInput("xpath", "//input[@id='" + att + "']", data.get(Dataset).get("SubTitle"));

			int page = Common.findElements("xpath", "(//fieldset[@class='admin__fieldset'])[5]").size();
			System.out.println(page);
			Common.assertionCheckwithReport(page > 0, " To Validate the User needs to save the page",
					"User should able to save the page", "Sucessfully User saves the page", "Failed to save the page");

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
			String headingverification = Common.getText("xpath",
					"//div[@class='u-container c-product-carousel-transparent__carousel']");
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
			String id = Common.findElement("xpath", "(//input[@class='admin__control-text'])[4]").getAttribute("id");

			Common.textBoxInput("xpath", "//input[@id='" + id + "']", data.get(Dataset).get("SKU"));
			Common.dropdown("xpath", "(//select[@class='admin__control-select'])[1]", Common.SelectBy.TEXT,
					data.get(Dataset).get("Sortby"));

			int page = Common.findElements("xpath", "(//fieldset[@class='admin__fieldset'])[4]").size();
			System.out.println(page);
			Common.assertionCheckwithReport(page > 0, " To Validate the User needs to save the page",
					"User should able to save the page", "Sucessfully User saves the page", "Failed to save the page");
			Common.clickElement("xpath", "(//span[text()='Save'])[2]");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(" To Validate the User needs to save the page",
					"User should able to save the page", "Unable to saves the page",
					Common.getscreenShotPathforReport("Failed to save the page"));
			Assert.fail();
		}
	}

	public void website_SKU_verification() {
// TODO Auto-generated method stub
		try {

			Common.refreshpage();
			Sync.waitElementPresent("xpath", "//div[@class='u-container c-product-carousel-transparent__carousel']");
			Thread.sleep(2000);

			String headingverification = Common.getText("xpath",
					"//div[@class='u-container c-product-carousel-transparent__carousel']");

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
			Common.textBoxInput("xpath", "//input[@id='condition_source__1--1__value']",
					data.get(Dataset).get("mrgtop"));
			int page = Common.findElements("xpath", "(//fieldset[@class='admin__fieldset'])[4]").size();
			System.out.println(page);
			Common.assertionCheckwithReport(page > 0, " To Validate the User needs to save the page",
					"User should able to save the page", "Sucessfully User saves the page", "Failed to save the page");

			Common.clickElement("xpath", "(//span[text()='Save'])[2]");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(" To Validate the User needs to save the page",
					"User should able to save the page", "Unable to saves the page",
					Common.getscreenShotPathforReport("Failed to save the page"));
			Assert.fail();
		}
	}

	public void website_condition_verification() {
// TODO Auto-generated method stub
		try {

			Common.refreshpage();
			Sync.waitElementPresent("xpath", "//div[@class='u-container c-product-carousel-transparent__carousel']");
			Thread.sleep(2000);
			String headingverification = Common.getText("xpath",
					"//div[@class='u-container c-product-carousel-transparent__carousel']");
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

			// Common.clickElement("xpath", "//h2[@class='m-heading__text
			// placeholder-text']");
			String id = Common.findElement("xpath", "(//input[@class='admin__control-text'])[4]").getAttribute("id");
			Common.textBoxInput("xpath", "//input[@id='" + id + "']", data.get(Dataset).get("title"));
			String att = Common.findElement("xpath", "(//input[@class='admin__control-text'])[5]").getAttribute("id");
			Common.textBoxInput("xpath", "//input[@id='" + att + "']", data.get(Dataset).get("SubTitle"));

			int title = Common.findElements("xpath", "(//fieldset[@class='admin__fieldset'])[5]").size();
			System.out.println(title);
			Common.assertionCheckwithReport(title > 0, " To Validate the Edit Category/Product Slider page",
					"User should able to navigate Edit Category/Product Slider page",
					"Sucessfully User lands on Edit Category/Product Slider",
					"Failed to navigate to Edit Category/Product Slider");

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
			
			Sync.waitElementClickable(30, "id", "menu-magento-catalog-catalog");
			Common.clickElement("id", "menu-magento-catalog-catalog");
			Thread.sleep(3000);

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
			ExtenantReportUtils.addFailedLog("To Validate the search filters", "should display the Search filter",
					"unable to display Search filte", "Failed to display Search filte");
			Assert.fail();
		}

	}

	public void Click_SearchProduct() {
		try {

			Thread.sleep(5000);
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

	public void Update_Name_Price_Stock_Categories(String dataSet) {
		// TODO Auto-generated method stub
		String Updateproductname = data.get(dataSet).get("Updateproductname");
		try {
			Thread.sleep(2000);
			Common.textBoxInput("xpath", "//input[@name='product[name]']", Updateproductname);
			Common.textBoxInput("xpath", "//input[@name='product[price]']", data.get(dataSet).get("Price"));

			Common.dropdown("name", "product[quantity_and_stock_status][is_in_stock]", Common.SelectBy.TEXT,
					data.get(dataSet).get("Stock Status"));
			// Common.waitAndClick("xpath",
			// "//button[@data-action='remove-selected-item']");
			Common.clickElement("xpath",
					"//fieldset[@data-index='container_category_ids']//div[contains(@class,'multiselect-wrap')]");
			Thread.sleep(3000);
			Sync.waitElementVisible("xpath", "//div[contains(@class,'active')]//input[contains(@class,'search')]");
			// Common.actionsKeyPress(Keys.PAGE_DOWN);

			String[] categories = data.get(dataSet).get("Categorydisplay").split(",");

			for (int i = 0; i < categories.length; i++) {
				System.out.println(categories[i]);
				Common.textBoxInput("xpath", "//div[contains(@class,'active')]//input[contains(@class,'search')]",
						categories[i]);

				// Common.scrollIntoView("xpath", "//label[text()='" + categories[i] + "']");
				Sync.waitElementPresent("xpath", "//span[text()='" + categories[i] + "']");
				Common.javascriptclickElement("xpath", "//span[text()='" + categories[i] + "']");
				Common.clickElement("xpath",
						"//fieldset[@data-index='container_category_ids']//div[contains(@class,'multiselect-wrap')]");

			}
			Common.scrollIntoView("xpath", "//button[@id='save-button']");
			Common.clickElement("xpath", "//button[@id='save-button']");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//div[@class='message message-success success']");
			String Successmessage = Common.findElement("xpath", "//div[@class='message message-success success']")
					.getText();
			System.out.println(Successmessage);

			Common.assertionCheckwithReport(
					Successmessage.contains("You saved the product.")
							&& Common.getPageTitle().contains(Updateproductname),
					"To Validate the Successmessage is displayed",
					"should display the Successmessage after clicking on the save button",
					"Successmessage is displayed after a click on the save button", "Failed to display Successmessage");

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
		try {
			 Sync.waitPageLoad(60);
			if (Common.getCurrentURL().contains("emea")) {
				Common.openNewTab();
				Common.oppenURL(data.get(Dataset).get("preprodURL"));

				Sync.waitPageLoad(40);
			} else {
				Common.openNewTab();
				Common.oppenURL(data.get(Dataset).get("preprodURL"));

				Sync.waitPageLoad(40);
			}

			String uname = Common.getPageTitle();
			Common.assertionCheckwithReport(
					uname.contains("Osprey: Backpacks, Luggage & Travel Gear Since 1974 | Osprey")
							|| uname.contains("Osprey UK | Premium Outdoor Backpacks & Bags Since 1974 (English)")
							|| uname.contains("Hydro Flask | Vacuum Insulated Stainless Steel Water Bottles")
							|| uname.contains("OXO Good Grips, Brew, SteeL, Tot")
							|| uname.contains("Osprey | Premium Outdoor Backpacks & Bags Since 1974")
							|| uname.contains("Drybar | Premium Hair Care Created for the Perfect Blowout"),
					"Validating the User lands to the Hydroflask page",
					"User should able to land on the Hydroflask page", "Sucessfully User lands on the Hydroflask page",
					"Failed to navigate to the hydroflask page");

			ClosADD();
			// AcceptAll();

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
			// Sync.waitPageLoad();
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

			Common.assertionCheckwithReport(pagetitle.contains("Shop Bottles"), "To Validate the plp page is displayed",
					"should display the plp page after clicking on the bottles",
					"plp page is displayed after a click on the bottles", "Failed to display plp page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the plp page is displayed",
					"should display the plp page after clicking on the bottles",
					"unable to display plp page after a click on the bottles", "Failed to display plp page");
			Assert.fail();
		}

	}

	public void click_sortby(String dataset) {
		String Actualproductprice = data.get(dataset).get("Price");
		try {
			Thread.sleep(2000);
			Common.clickElement("xpath", "//select[@class='ais-SortBy-select']");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//select[@class='ais-SortBy-select']//option[text()='Newest']");
			// Thread.sleep(6000);
			// Common.textBoxInput("xpath", "//input[@id='search']",
			// data.get(dataset).get("Updateproductname"));
			// Sync.waitElementPresent("xpath",
			// "//form[@id='search_mini_form']//span[text()='Search']");
			// Common.clickElement("xpath",
			// "//form[@id='search_mini_form']//span[text()='Search']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.refreshpage();
			Sync.waitElementPresent("xpath", "//span[text()='10 QATEST product ']");
			Common.scrollIntoView("xpath", "//span[text()='10 QATEST product ']");
			Common.mouseOver("xpath", "//span[text()='10 QATEST product ']");

			Thread.sleep(2000);
			String productname = Common.findElement("xpath", "//span[text()='10 QATEST product ']").getText();
			System.out.println(productname);
			String plpproductprice = Common.findElement("xpath", "//span[text()='$5.00']").getText();
			System.out.println(plpproductprice);

			Common.assertionCheckwithReport(
					productname.contains("10 QATEST") && plpproductprice.contains(Actualproductprice),
					"To Validate the update product is displayed",
					"should display the update product after clicking on the search",
					"update product is displayed after a click on the search", "Failed to display update product");
			Common.clickElement("xpath", "//span[text()='10 QATEST product ']");

			String pdpproductname = Common.findElement("xpath", "//h1[text()='10 QATEST product ']").getText();
			System.out.println(pdpproductname);
			String productprice = Common.getText("xpath", "//span[text()='$5.00']");
			System.out.println(productprice);

			Common.assertionCheckwithReport(
					productprice.contains(Actualproductprice) && pdpproductname.contains("10 QATEST"),
					"To Validate the update product is displayed",
					"should display the update product after clicking on the search",
					"update product is displayed after a click on the search", "Failed to display update product");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the update product is displayed",
					"should display the update product after clicking on the search",
					"unable to display update product after a click on the search", "Failed to display update product");
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
					"Validating the User lands to the OXO page", "User should able to land on the OXO page",
					"Sucessfully User lands on the OXO page", "Failed to navigate to the OXO page");

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
			// Sync.waitPageLoad();
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
					"plp page is displayed after a click on the POP Containers", "Failed to display plp page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the plp page is displayed",
					"should display the plp page after clicking on the POP Containers",
					"unable to display plp page after a click on the POP Containers", "Failed to display plp page");
			Assert.fail();
		}

	}

	public void Backto_magento_admin() {
		try {

			Common.switchToFirstTab();
			Thread.sleep(2000);

			// String text=Common.getText("xpath", "//h1[@class='page-title']");
			String text = Common.getPageTitle();

			System.out.println(text);
			Thread.sleep(2000);

			Common.assertionCheckwithReport(text.contains("Catalog") || text.contains("QA Testing Product"),
					"To Validate the pagetitle is displayed",
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

	public void Actual_Name_Price_Stock_Categories(String dataSet) {
		// TODO Auto-generated method stub
		String Productname = data.get(dataSet).get("Productname");
		try {
			Thread.sleep(2000);
			Common.textBoxInput("xpath", "//input[@name='product[name]']", Productname);
			Common.textBoxInput("xpath", "//input[@name='product[price]']", data.get(dataSet).get("Updateprice"));

			Common.dropdown("name", "product[quantity_and_stock_status][is_in_stock]", Common.SelectBy.TEXT,
					data.get(dataSet).get("updateStock Status"));
			// Common.waitAndClick("xpath",
			// "//button[@data-action='remove-selected-item']");
			Common.clickElement("xpath",
					"//fieldset[@data-index='container_category_ids']//div[contains(@class,'multiselect-wrap')]");
			Sync.waitElementVisible("xpath", "//div[contains(@class,'active')]//input[contains(@class,'search')]");
			// Common.actionsKeyPress(Keys.PAGE_DOWN);

			String[] categories = data.get(dataSet).get("ActualCategorydisplay").split(",");

			for (int i = 0; i < categories.length; i++) {
				System.out.println(categories[i]);
				Common.textBoxInput("xpath", "//div[contains(@class,'active')]//input[contains(@class,'search')]",
						categories[i]);

				// Common.scrollIntoView("xpath", "//label[text()='" + categories[i] + "']");
				Sync.waitElementPresent("xpath", "//span[text()='" + categories[i] + "']");
				Common.javascriptclickElement("xpath", "//span[text()='" + categories[i] + "']");
				Common.clickElement("xpath",
						"//fieldset[@data-index='container_category_ids']//div[contains(@class,'multiselect-wrap')]");

			}
			Common.scrollIntoView("xpath", "//button[@id='save-button']");
			Common.clickElement("xpath", "//button[@id='save-button']");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//div[@class='message message-success success']");
			String Successmessage = Common.findElement("xpath", "//div[@class='message message-success success']")
					.getText();
			System.out.println(Successmessage);

			Common.assertionCheckwithReport(
					Successmessage.contains("You saved the product.") && Common.getPageTitle().contains(Productname),
					"To Validate the Successmessage is displayed",
					"should display the Successmessage after clicking on the save button",
					"Successmessage is displayed after a click on the save button", "Failed to display Successmessage");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the Successmessage is displayed",
					"should display the Successmessage after clicking on the save button",
					"unable to display Successmessage after a click on the save button",
					"Failed to display Successmessage");
			Assert.fail();
		}

	}

	public void click_Country_Language_Selector_Management() {

		try {
			Sync.waitPageLoad();

			Common.scrollToElementAndClick("xpath", "//span[text()=' Country & Language Selector Management']");

			String countrySelector = Common.findElement("xpath", "//strong[contains(text(),'Content')]").getText();
			System.out.println(countrySelector);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Country & Language Selector Management / Magento Admin"),
					"Validating Country Languge Selector page navigation ",
					"Navigate to Country Language Selector Page ",
					"Successfully navigate to the Country Language Selector Page ",
					"Failed to navigate to the Country Language Selector Page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Country Languge Selector page navigation ",
					"Navigate to Country Language Selector Page",
					"Unable navigate to the Country Language Selector Page ",
					Common.getscreenShotPathforReport("Failed to navigate to the Country Language Selector Page"));
			Assert.fail();
		}

	}

	public void views_List_of_Countries_languages() {

		try {
			List<WebElement> viewlineoptions = Common.findElements("xpath",
					"//div[@id='container']/div/div[5]/table/thead/tr/th");
			for (WebElement j : viewlineoptions) {
				System.out.println(j.getText());
			}

			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Country & Language Selector Management / Magento Admin"),
					"Validating Country Language Selector Management items  ",
					"To verify all the Country Language Selector Management items ",
					"Successfully verified Country Language Selector Management items ",
					"Failed to verify Country Language Selector Management items");
		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("Validating Country Selector Management items ",
					"To verify all the Country Language Selector Management items ",
					"Unable to verify Country  Language Selector Management items ",
					Common.getscreenShotPathforReport("Failed to verify Country Language Selector Management items"));
			Assert.fail();

		}

	}

	public void add_New_Countries_languages(String dataSet) {
		String url = data.get(dataSet).get("URL");
		try {
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//button[@data-action='grid-filter-expand']");
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			Common.textBoxInput("xpath", "//input[@name='url']", url);
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(8000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			String records = Common.findElement("xpath", "//div[@class='admin__control-support-text']").getText();
			System.out.println(records);
			if (records.equals("0 records found")) {

				Sync.waitPageLoad(30);
				Sync.waitElementVisible("xpath", "//span[text()='Add New Item']");
				Common.clickElement("xpath", "//span[text()='Add New Item']");
				Sync.waitPageLoad();

			} else {
				delete_Existing_Country_Selector("Address");

				Sync.waitElementVisible("xpath", "//span[text()='Add New Item']");
				Common.clickElement("xpath", "//span[text()='Add New Item']");
			}

			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

			Common.assertionCheckwithReport(Common.getPageTitle().contains("New Country Selector Item / Magento Admin"),
					"Validating Country Languge Selector page navigation ",
					"Navigate to Country Language Selector Page ",
					"Successfully navigate to the Country Language Selector Page ",
					"Failed to navigate to the Country Language Selector Page");
			Select_website("Address");
			Common.dropdown("xpath", "//select[@name='region']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			Common.dropdown("xpath", "//select[@name='country']", Common.SelectBy.TEXT,
					data.get(dataSet).get("Country"));
			Common.clickElement("xpath", "//input[@name='language']");
			Common.textBoxInput("xpath", "//input[@name='language']", data.get(dataSet).get("Language"));

			Common.clickElement("xpath", "//input[@name='url']");
			Common.textBoxInput("xpath", "//input[@name='url']", data.get(dataSet).get("URL"));
			Common.dropdown("xpath", "//select[@name='is_enabled']", Common.SelectBy.TEXT,
					data.get(dataSet).get("IsEnabled"));

			Common.clickElement("id", "save");
			String message = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("Country & Language Selector Management / Magento Admin")
							&& message.contains("You saved the Country Selector Item."),
					"Validating Country Languge Selector page navigation and save message",
					"Navigate to Country Language Selector Page and message should be displayed",
					"Sucessfully navigate to the Country Language Selector Page and message is displayed",
					"Failed to navigate to the Country Language Selector Page");

		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("Validating Country Languge Selector page navigation and save message",
					"Navigate to Country Language Selector Page and message should be displayed",
					"Unable to navigate to the Country Language Selector Page and message is not displayed",
					Common.getscreenShotPathforReport("Failed to navigate to the Country Language Selector Page"));
			Assert.fail();

		}

	}

	public void delete_Existing_Country_Selector(String dataSet) {
		String url = data.get(dataSet).get("URL");
		try {

			Sync.waitPageLoad();
			Common.clickElement("xpath", "//button[text()='Select']");

			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementVisible("xpath", "//a[text()='Edit']");
			Common.clickElement("xpath", "//a[text()='Edit']");
			Sync.waitPageLoad();

			String editselector = Common.findElement("xpath", "//span[text()='Manage Country Selector']").getText();
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

			Common.assertionCheckwithReport(editselector.contains("Manage Country Selector"),
					"Validating the selects edit button",
					"After clicking edit button it should navigate to the selected page",
					"Successfully navigate to  the selected page", "Failed to navigate to the selected page");

			Common.clickElement("id", "delete");
			Sync.waitPageLoad();
			System.out.println(Common.getPageTitle());
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

			Common.assertionCheckwithReport(editselector.contains("Manage Country Selector"),
					"To verfy the delete existing customer  Country Selector",
					"It should able to select the  delete existing country selector and popup should display",
					"Sucessfully selects the  delete existing country selector  and popup is displayed",
					"Failed to select the delete existing country selector ");

			Sync.waitPageLoad();

			String message = Common.getText("xpath", "(//div[@class='modal-content'])[2]");
			if (message.equals("Are you sure you want to do this?")) {

				Common.clickElement("xpath", "//span[text()='OK']");
			} else {
				Assert.fail();
			}

			String message1 = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
			System.out.println(message1);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

			Common.assertionCheckwithReport(Common.getPageTitle().contains("Country & Language Selector Management"),
					"To verify the delete Country & Language Selector Managementt field and deleted message",
					"It should able to delete the Country & Language Selector Management and delete message should be displayed",
					"Sucessfully deletes the Country & Language Selector Management and delete message will displayed",
					" Failed to delete Country & Language Selector Management");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To verify the delete Country & Language Selector Managementt field and deleted message",
					"It should able to delete the Country & Language Selector Management and delete message should be displayed",
					"Unable to deletes the Country & Language Selector Management and delete message is not displayed",
					Common.getscreenShotPathforReport(" Failed to delete Country & Language Selector Management"));
			Assert.fail();
		}

	}

	public void delete_Country_Selector(String dataSet) {
		String url = data.get(dataSet).get("URL");
		try {

			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//button[@data-action='grid-filter-expand']");
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			Common.textBoxInput("xpath", "//input[@name='url']", url);
			Common.actionsKeyPress(Keys.ENTER);

			Thread.sleep(6000);
			String records = Common.findElement("xpath", "//div[@class='admin__control-support-text']").getText();
			System.out.println(records);
			if (records.equals("1 records found")) {

				Common.clickElement("xpath", "//button[text()='Select']");

				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Sync.waitElementVisible("xpath", "//a[text()='Edit']");
				Common.clickElement("xpath", "//a[text()='Edit']");
				Sync.waitPageLoad();

				String editselector = Common.findElement("xpath", "//span[text()='Manage Country Selector']").getText();
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

				Common.assertionCheckwithReport(editselector.contains("Manage Country Selector"),
						"Validating the selects edit button",
						"After clicking edit button it should navigate to the selected page",
						"Successfully navigate to  the selected page", "Failed to navigate to the selected page");

				Common.clickElement("id", "delete");
				Sync.waitPageLoad();
				System.out.println(Common.getPageTitle());

				Common.assertionCheckwithReport(editselector.contains("Manage Country Selector"),
						"To verify the delete  Country Selector",
						"It should able to select the  delete Country Selector and popup should display",
						"Sucessfully selects the  delete Country Selector and popup is displayed",
						"Failed to select the delete Country Selector");

				Sync.waitPageLoad();

				String message = Common.getText("xpath", "(//div[@class='modal-content'])[2]");
				if (message.equals("Are you sure you want to do this?")) {

					Common.clickElement("xpath", "//span[text()='OK']");
				} else {
					Assert.fail();
				}

				String message1 = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']")
						.getText();
				System.out.println(message1);
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

				Common.assertionCheckwithReport(
						Common.getPageTitle().contains("Country & Language Selector Management"),
						"To verify the delete Country & Language Selector Managementt field and deleted message",
						"It should able to delete the Country & Language Selector Management and delete message should be displayed",
						"Sucessfully deletes the Country & Language Selector Management and delete message will displayed",
						" Failed to delete Country & Language Selector Management");

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To verify the delete Country & Language Selector Managementt field and deleted message",
					"It should able to delete the Country & Language Selector Management and delete message should be displayed",
					"Unable to deletes the Country & Language Selector Management and delete message is not displayed",
					Common.getscreenShotPathforReport(" Failed to delete Country & Language Selector Management"));
			Assert.fail();
		}

	}

	public void edit_Country_Languages(String dataSet) {
		String url = data.get(dataSet).get("URL");
		try {
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//button[@data-action='grid-filter-expand']");
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			Common.textBoxInput("xpath", "//input[@name='url']", url);
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(8000);

			String records = Common.findElement("xpath", "//div[@class='admin__control-support-text']").getText();
			System.out.println(records);
			if (records.equals("1 records found")) {

				Common.clickElement("xpath", "//button[text()='Select']");

				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Sync.waitElementVisible("xpath", "//a[text()='Edit']");
				Common.clickElement("xpath", "//a[text()='Edit']");
				Sync.waitPageLoad();

				String editselector = Common.findElement("xpath", "//span[text()='Manage Country Selector']").getText();
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

				Common.assertionCheckwithReport(editselector.contains("Manage Country Selector"),
						"Validating user selects the edit button",
						"After clicking edit button it should navigate to the selected page",
						"Successfully navigate to the selected page", "Failed to navigate to the selected page");

			} else {
				Assert.fail();
			}

			Common.clickElement("xpath", "//input[@name='language']");
			Common.textBoxInput("xpath", "//input[@name='language']", data.get(dataSet).get("Language"));
			Common.clickElement("id", "save");
			Sync.waitPageLoad();

			String message = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
			System.out.println(message);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("Country & Language Selector Management / Magento Admin")
							&& message.contains("You saved the Country Selector Item."),
					"To verify the save updated Country & Language Selector",
					"It should able to save updated Country & Language Selector",
					"Sucessfully saves the updated Country & Language Selector",
					"Failed to update Country & Language Selector");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To verify the save updated Country & Language Selector",
					"It should able to save updated Country & Language Selector",
					"Unable to  save the updated Country & Language Selector",
					Common.getscreenShotPathforReport("Failed to update Country & Language Selector"));
			Assert.fail();
		}

	}

	public void dragndrop_CLP_Hero_Content() {
		try {
			WebElement element = Common.findElement("xpath", "//span[text()='CLP Hero Banner']");
			draganddrop_CLP_ContentBlock(element);
			Sync.waitElementPresent(60,"xpath", "(//div[@class='pagebuilder-content-type-wrapper']/div)[1]");
			String blockname = Common.findElement("xpath", "(//div[@class='pagebuilder-content-type-wrapper']/div)[1]")
					.getAttribute("data-content-type");
			Common.assertionCheckwithReport(blockname.equals("hot_clp_banner"),
					"Validating cardtiles Dragndrop operation", "CLP banner dragndrop to content with options",
					"successfully dragndrop the CLP banner with options ", "fail to dragndrop the CLP banner");
		} catch (Exception e) {

			e.printStackTrace();

			report.addFailedLog("Validating card tile Dragndrop operation", "User should able Dragndrop cardtile",
					"Sucessfully Dragndrop cardtile",
					Common.getscreenShotPathforReport("Failed to Dragndrop cardtile"));
			Assert.fail();

		}
	}

	public void draganddrop_CLP_ContentBlock(WebElement source) {
		try {
			
			Sync.waitElementClickable("xpath", "(//div[contains(@class,'pagebuilder-image-uploader')])[1]");
			Common.dragdrop(source, "xpath", "(//div[contains(@class,'pagebuilder-image-uploader')])[1]");

		} catch (Exception | Error e) {

		}
	}
	public void edit_CLP_block_content(String dataSet) {
		String Editpagetitle = data.get(dataSet).get("Editpagetitle");
		String component = data.get(dataSet).get("component");

		try {

			Sync.waitElementPresent("xpath", "//div[@class='" + component + "']");
			Common.mouseOver("xpath", "//div[@class='" + component + "']");
			Common.clickElement("xpath", "//i[@class='icon-admin-pagebuilder-systems']");

			/*
			 * String visible = Common.findElement("xpath",
			 * "//div[contains(@class,'pagebuilder-options ')]") .getAttribute("class");
			 * System.out.println(visible);
			 * Common.assertionCheckwithReport(visible.contains("options-visible"),
			 * "validation Edit option  in the page builder ",
			 * "after mouse over on the page builder edit option should be appear ",
			 * "Successfully edit option appeared in the pagebuilder",
			 * "Failed to appear edit option when we mouse over");
			 */
			// Common.clickElement("xpath", "//i[@class='icon-admin-pagebuilder-systems']");
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

	public void Configure_content(String dataSet) {
		try {
			Common.scrollIntoView("name", "background_color");
			Sync.waitElementClickable("name", "background_color");
			Common.textBoxInput("name", "background_color", data.get(dataSet).get("Color"));

			Common.scrollIntoView("xpath", "//div[@class='admin__field']//input[@name='title']");
			Sync.waitElementClickable("xpath", "//div[@class='admin__field']//input[@name='title']");
			Common.textBoxInput("xpath", "//div[@class='admin__field']//input[@name='title']",
					data.get(dataSet).get("title"));
			Common.dropdown("xpath", "//select[@name='heading_type']", SelectBy.TEXT,
					data.get(dataSet).get("headingtype"));
			Common.textBoxInput("xpath", "//input[@name='description']", data.get(dataSet).get("Description"));
			Common.dropdown("xpath", "//select[@name='description_type']", SelectBy.TEXT,
					data.get(dataSet).get("Description Type"));

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Content is configured for CLP Hero Banner",
					"CLP Hero Banner content Should be configured", "Failed to configure the CLP Hero Banner Content",
					Common.getscreenShotPathforReport("Failed to configure the content"));
			Assert.fail();

		}
	}

	public WebElement Testimonials_Product_Carousel() {

		WebElement element = Common.findElement("xpath", "//span[text()='Testimonials Product Carousel']");
		Common.clickElement("xpath", "//span[text()='Testimonials Product Carousel']");

		return element;

	}

	public void dragndrop_Testimonials_Product_Carousel() {
		try {
			WebElement element = Common.findElement("xpath", "//span[text()='Testimonials Product Carousel']");
			draganddropContentBlock(element);
			String blockname = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']/div")
					.getAttribute("data-content-type");

			Common.assertionCheckwithReport(blockname.equals("hot_testimonials_product_carousel"),
					"Validating Testimonials Product Carousel Dragndrop operation",
					"Testimonials Product Carousel dragndrop to content with options",
					"successfully dragndrop the Testimonials Product Carousel with options ",
					"fail to dragndrop the Testimonials Product Carousel");
		} catch (Exception e) {

			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("Validating card tile Dragndrop operation",
					"User should able Dragndrop Testimonials Product Carousel",
					"Unable to Dragndrop the Testimonials Product Carousel",
					Common.getscreenShotPathforReport("Failed to Dragndrop the Testimonials Product Carousel"));
			Assert.fail();

		}
	}

	public void edit_Testimonial_one() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent(30, "xpath", "//div[@class='pagebuilder-content-type-wrapper']");
			String id = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']")
					.getAttribute("id");

			Common.mouseOverClick("xpath", "(//div[@id='" + id + "']//i[@class='icon-admin-pagebuilder-systems'])[2]");

			String EditTestimonials = Common.findElement("xpath", "//h1[@class='modal-title']").getText();

			Common.assertionCheckwithReport(EditTestimonials.contains("Edit Testimonials Product Card"),
					"To validate the  Navigation to the Edit Testimonials Product Card page ",
					"After Click on the edit button it should be navigate to the Edit Testimonials Product Card page ",
					"Successfully it is navigated to the Edit Testimonials Product Card page ",
					"Failed to navigate to the Edit Testimonials Product Card page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the  Navigation to the Edit Testimonials Product Card page ",
					"After Click on the edit button it should be navigate to the Edit Testimonials Product Card page ",
					"Unable to click on the edit button and it is not navigated to the Edit Testimonials Product Card page ",
					Common.getscreenShotPathforReport("Failed to navigate to the Edit Testimonials Product Card page"));

			Assert.fail();

		}

	}

	public void SKUvalue(String dataSet) {
		try {
			String SKU = Common.findElement("xpath", "//span[text()='Product SKU']").getText();
			Common.javascriptclickElement("xpath", "//input[@name='product_sku']");
			Sync.waitElementClickable("xpath", "//input[@name='product_sku']");
			Common.textBoxInput("xpath", "//input[@name='product_sku']", data.get(dataSet).get("SKU"));

			Common.assertionCheckwithReport(SKU.equals("Product SKU"), "To validate the Product SKU",
					"Should able to add Product SKU value ", "Product SKU value is added successfully",
					"Product SKU value failed");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Product SKU", "Admin should able to enter Product SKU value",
					"Product SKU failed", Common.getscreenShotPathforReport("Failed to enter Product SKU"));

			Assert.fail();

		}

	}

	public void Content_Dark(String dataSet) {
		try {

			String activecolor = Common.findElement("name", "text__content--light").getAttribute("class");

			if (activecolor.contains("active")) {
				Common.javascriptclickElement("name", "text__content--dark");

			} else {
				System.out.println("Dark color is selected");
			}
			String textcolor = Common.findElement("xpath", "//span[text()='Dark']").getText();

			Sync.waitElementClickable("xpath", "(//input[@name='title'])[2]");
			Common.textBoxInput("xpath", "(//input[@name='title'])[2]", data.get(dataSet).get("Author"));

			Sync.waitElementPresent("xpath", "//select[@name='rating']");
			Common.dropdown("xpath", "//select[@name='rating']", Common.SelectBy.TEXT, data.get(dataSet).get("Rating"));

			Common.javascriptclickElement("xpath", "//textarea[@name='description']");
			Sync.waitElementClickable("xpath", "//textarea[@name='description']");
			Common.textBoxInput("xpath", "//textarea[@name='description']", data.get(dataSet).get("Quote"));

			Common.assertionCheckwithReport(textcolor.equals("Dark"), "To validate the Content values",
					"Should able to add Content values ", "Content values is added successfully",
					"Content values failed");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Content feildset", "Admin should able to Content values",
					"Content values failed", Common.getscreenShotPathforReport("Failed to add Content values"));

			Assert.fail();

		}

	}

	public void CSSclassvalue(String dataSet) {
		try {

			Common.scrollIntoView("xpath", "//span[text()='Advanced']");
			String Advanced = Common.findElement("xpath", "//span[text()='Advanced']").getText();
			Sync.waitElementClickable("xpath", "//input[@name='css_classes']");
			Common.textBoxInput("xpath", "//input[@name='css_classes']", data.get(dataSet).get("CSSclasses"));

			Common.assertionCheckwithReport(Advanced.equals("Advanced"), "To validate the CSS classes value",
					"Should able to add CSS classes value ", "CSS classes value is added successfully",
					"CSS classes value failed");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Testimonials product cards configuration	",
					"Admin should able to add CSS classes value", "failed to add CSS classes value",
					Common.getscreenShotPathforReport("Failed to add CSS clasess"));

			Assert.fail();

		}

	}

	public void edit_Testimonial_Two() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent(30, "xpath", "//div[@class='pagebuilder-content-type-wrapper']");
			String id = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']")
					.getAttribute("id");

			Common.mouseOverClick("xpath", "(//div[@id='" + id + "']//i[@class='icon-admin-pagebuilder-systems'])[3]");

			String EditTestimonials = Common.findElement("xpath", "//h1[@class='modal-title']").getText();

			Common.assertionCheckwithReport(EditTestimonials.contains("Edit Testimonials Product Card"),
					"To validate the  Navigation to the Edit Testimonials Product Card page ",
					"After Click on the edit button it should be navigate to the Edit Testimonials Product Card page ",
					"Successfully it is navigated to the Edit Testimonials Product Card page ",
					"Failed to navigate to the Edit Testimonials Product Card page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the  Navigation to the Edit Testimonials Product Card page ",
					"After Click on the edit button it should be navigate to the Edit Testimonials Product Card page ",
					"Unable to click on the edit button and it is not navigated to the Edit Testimonials Product Card page ",
					Common.getscreenShotPathforReport("Failed to navigate to the Edit Testimonials Product Card page"));

			Assert.fail();

		}

	}

	public void Content_Light(String dataSet) {
		try {

			String activecolor = Common.findElement("name", "text__content--light").getAttribute("class");

			if (activecolor.contains("active")) {
				System.out.println("Light Color is selected");

			} else {
				Common.javascriptclickElement("name", "text__content--light");

			}

			String textcolor = Common.findElement("xpath", "//span[text()='Light']").getText();

			Common.javascriptclickElement("xpath", "(//input[@name='title'])[2]");
			Sync.waitElementClickable("xpath", "(//input[@name='title'])[2]");
			Common.textBoxInput("xpath", "(//input[@name='title'])[2]", data.get(dataSet).get("Author"));

			Common.javascriptclickElement("xpath", "//textarea[@name='description']");
			Sync.waitElementClickable("xpath", "//textarea[@name='description']");
			Common.textBoxInput("xpath", "//textarea[@name='description']", data.get(dataSet).get("Quote"));

			Common.assertionCheckwithReport(textcolor.equals("Light"), "To validate the Content values",
					"Should able to add Content values ", "Content values is added successfully",
					"Content values failed");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Content feildset", "Admin should able to Content values",
					"Content values failed", Common.getscreenShotPathforReport("Failed to add Content values"));

			Assert.fail();

		}

	}

	public void edit_Testimonial_Three() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent(30, "xpath", "//div[@class='pagebuilder-content-type-wrapper']");
			String id = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']")
					.getAttribute("id");

			Common.mouseOverClick("xpath", "(//div[@id='" + id + "']//i[@class='icon-admin-pagebuilder-systems'])[4]");

			String EditTestimonials = Common.findElement("xpath", "//h1[@class='modal-title']").getText();

			Common.assertionCheckwithReport(EditTestimonials.contains("Edit Testimonials Product Card"),
					"To validate the  Navigation to the Edit Testimonials Product Card page ",
					"After Click on the edit button it should be navigate to the Edit Testimonials Product Card page ",
					"Successfully it is navigated to the Edit Testimonials Product Card page ",
					"Failed to navigate to the Edit Testimonials Product Card page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the  Navigation to the Edit Testimonials Product Card page ",
					"After Click on the edit button it should be navigate to the Edit Testimonials Product Card page ",
					"Unable to click on the edit button and it is not navigated to the Edit Testimonials Product Card page ",
					Common.getscreenShotPathforReport("Failed to navigate to the Edit Testimonials Product Card page"));

		}
	}

	public void Verify_frontend_Testimonial(String Dataset, int i) {
		String Description = data.get(Dataset).get("Quote");
		String Author = data.get(Dataset).get("Author");
		String Rating[] = data.get(Dataset).get("Rating").split(" ");
		String SKU = data.get(Dataset).get("SKU");
		try {

			Sync.waitPageLoad();

			Sync.waitElementPresent("xpath", "(//div[@class='m-media-card-testimonial__testimonial'])[1]");

			Common.scrollIntoView("xpath", "(//div[contains(@class,'_testimonial')])[" + i + "]");
			String description = Common.findElement("xpath", "(//div[contains(@class,'_testimonial')])[" + i + "]")
					.getText();
			System.out.println(description);
			System.out.println(Description);
			Common.scrollIntoView("xpath", "(//div[contains(@class,'__author')])[" + i + "]");
			String author = Common.findElement("xpath", "(//div[contains(@class,'__author')])[" + i + "]").getText();
			System.out.println(author);
			System.out.println(Author);

			int number = Integer.parseInt(Rating[0]);
			Common.scrollIntoView("xpath", "(//div[contains(@class,'rating_')]/span)[" + i + "]");
			String rating = Common.findElement("xpath", "(//div[contains(@class,'rating_')]/span)[" + i + "]")
					.getText();
			System.out.println(rating);
			int Rating1 = Integer.parseInt(Rating[0]);

			System.out.println(Rating[0]);
			String sku = Common.findElement("xpath", "(//div[contains(@class,'background-image')])")
					.getAttribute("data-bg-desktop");

			Common.assertionCheckwithReport(
					description.contains(Description) && Rating1 == number && author.contains(Author)
							&& sku.contains("jpg"),
					"validating the description author name and rating on the frontend ",
					"Description Description author name and rating should be display on the frontend ",
					"Successfully Description author name and rating is displayed on the frontend",
					"Failed to dispaly Description author name and rating is displayed on the frontend ");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the description author name and rating on the frontend ",
					"Description Description author name and rating should be display on the frontend ",
					"Unable to display the Description author name and rating on the frontend",
					Common.getscreenShotPathforReport(
							"Failed to dispaly Description author name and rating is displayed on the fronten"));
			Assert.fail();
		}

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
			Common.assertionCheckwithReport(categories.equals("Hydro Flask (ID: 2)"), "To verify the categories menu ",
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

		try {
			Sync.waitPageLoad();
			Thread.sleep(2000);
			Common.refreshpage();
			Common.mouseOverClick("xpath", "(//a[@class='level-top ui-corner-all'])[1]");
			Common.mouseOverClick("xpath", "//span[contains(text(),'" + Newcategory + "')]");
			String website_subcategory = Common
					.findElement("xpath", "//span[contains(text(),'" + Newsubcategory + "')]").getText();
			Thread.sleep(2000);
			Common.assertionCheckwithReport(website_subcategory.contains(Newsubcategory),
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

	public void Validate_deletcategory_Frontend(String dataSet) {
		// TODO Auto-generated method stub
		String Newcategory = data.get(dataSet).get("Newcategory");
		String Newsubcategory = data.get(dataSet).get("Newsubcategory");

		try {
			Thread.sleep(3000);
			Common.refreshpage();
			Common.mouseOverClick("xpath", "(//a[@class='level-top ui-corner-all'])[1]");
			int testcategory = Common.findElements("xpath", "//span[contains(text(),'" + Newcategory + "')]").size();
			Thread.sleep(2000);
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

	public void delete_ExistingCategory(String Dataset) {
		String category = data.get(Dataset).get("Newcategory");
		String shop = data.get(Dataset).get("Shop");
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

					Common.assertionCheckwithReport(
							deletesuccessmessage.contains("You deleted the category.") && Common.getPageTitle()
									.equals(shop + " / Categories / Inventory / Catalog / Magento Admin"),
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

	public void Validate_Category_Update_Frontend(String dataSet) {
		// TODO Auto-generated method stub
		String Newcategory = data.get(dataSet).get("Newcategory");
		String Updatesubcategory = data.get(dataSet).get("Updatesubcategory");
		try {
			Sync.waitPageLoad();
			Common.refreshpage();
			Thread.sleep(5000);
			Common.mouseOverClick("xpath", "(//a[@class='level-top ui-corner-all'])[1]");
			Common.mouseOverClick("xpath", "//span[contains(text(),'" + Newcategory + "')]");
			String website_subcategory = Common
					.findElement("xpath", "//span[contains(text(),'" + Updatesubcategory + "')]").getText();
			Common.assertionCheckwithReport(website_subcategory.contains(Updatesubcategory),
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

	public void Navigate_Adminpage() {
		Common.switchToFirstTab();
	}

	public void open_Website(String dataSet) {
		String pagetitle = data.get(dataSet).get("pageTitle");
		try {
			Sync.waitPageLoad(60);

			Common.openNewTab();
			if (Common.getCurrentURL().contains("stage")) {
				Common.oppenURL(data.get(dataSet).get("URL"));
			} else {
				Common.oppenURL(data.get(dataSet).get("preprodURL"));
			}

			Sync.waitPageLoad(40);

			String uname = Common.getPageTitle();
			Common.assertionCheckwithReport(uname.contains("Home Page "),
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

	public void Click_Shop(String DataSet) {
		String Shop = data.get(DataSet).get("Shop");
		try {
			Common.switchToFirstTab();
			Common.scrollIntoView("id", "expandAll");
			Common.clickElement("id", "expandAll");
			Sync.waitElementVisible("xpath", "//span[contains(text(),'" + Shop + "')]");
			Common.clickElement("xpath", "//span[contains(text(),'" + Shop + "')]");
			Common.clickElement("xpath", "//span[contains(text(),'" + Shop + "')]");
			Sync.waitPageLoad();
			String Shop_Page = Common.findElement("xpath", "//h1[@class='page-title']").getText();

			Common.assertionCheckwithReport(Common.getPageTitle().contains(Shop) && Shop_Page.contains(Shop),
					"To validate the Admin is on Shop category page", "Admin should be on Shop category page",
					"Admin is on Shop category page", "Admin Failed to land on the Shop category");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void Content_Alignment(String dataSet) {
		try {
			Sync.waitElementVisible("xpath", "//div[@data-index='content_alignment']//li[@name='left']");
			if (dataSet.contains("left")) {
				Common.clickElement("xpath", "//div[@data-index='content_alignment']//li[@name='left']");
				String active = Common.findElement("xpath", "//div[@data-index='content_alignment']//li[@name='left']")
						.getAttribute("class");
				Common.assertionCheckwithReport(active.contains("active"),
						"To validate the left text alignment is selected", "The left text alignment should be selected",
						"The left text alignment is selected", "failed to Left text alignment");

			} else if (dataSet.contains("Centre")) {
				Common.clickElement("xpath", "//div[@data-index='content_alignment']//li[@name='center']");
				String active = Common
						.findElement("xpath", "//div[@data-index='content_alignment']//li[@name='center']")
						.getAttribute("class");
				Common.assertionCheckwithReport(active.contains("active"),
						"To validate the center text alignment is selected",
						"The center text alignment should be selected", "The center text alignment is selected",
						"failed to center text alignment");

			} else if (dataSet.contains("right")) {
				Common.clickElement("xpath", "//div[@data-index='content_alignment']//li[@name='right']");
				String active = Common.findElement("xpath", "//div[@data-index='content_alignment']//li[@name='right']")
						.getAttribute("class");
				Common.assertionCheckwithReport(active.contains("active"),
						"To validate the right text alignment is selected",
						"The right text alignment should be selected", "The right text alignment is selected",
						"failed to right text alignment");

			}
		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Configure the text alignment" + dataSet,
					dataSet + "text alignment should be selected", "Failed to select text alignment" + dataSet,
					dataSet + "text alignment selecteion unsuccessfull");
			Assert.fail();
		}
	}

	public void Validate_CLP_Content_Frontend(String dataSet) {
		String title = data.get(dataSet).get("title");
		String headingtype = data.get(dataSet).get("headingtype").toLowerCase();
		String Description = data.get(dataSet).get("Description");
		String descriptiontype = data.get(dataSet).get("Description Type").toLowerCase();
		String image = data.get(dataSet).get("image");
		String image2 = data.get(dataSet).get("image2");
		String color = data.get(dataSet).get("Color");

		try {
			String Title = Common.findElement("xpath", "//*[@class='c-clp-hero__headline']").getText();

			String HeadingType = Common.findElement("xpath", "//*[@class='c-clp-hero__headline']").getTagName();

			String description = Common.findElement("xpath", "//*[@class='c-clp-hero__description']").getText();

			String DescriptionType = Common.findElement("xpath", "//*[@class='c-clp-hero__description']").getTagName();

			String desktopimage = Common.findElement("xpath", "//img[@data-element='image_element_desktop']")
					.getAttribute("src");

			String mobileimage = Common.findElement("xpath", "//img[@data-element='image_element_mobile']")
					.getAttribute("src");

			String style = Common.findElement("xpath", "//main[@id='maincontent']//style").getAttribute("innerHTML");

			Common.assertionCheckwithReport(
					style.contains("background-color:" + color) && Title.contains(title)
							&& HeadingType.contains(headingtype) && description.contains(Description)
							&& DescriptionType.contains(descriptiontype) && desktopimage.contains(image)
							&& mobileimage.contains(image2),
					"To validate the CLP Content on the front end",
					"Configured content should be dispalyed on the front end", "Configured content is on the fornt end",
					"failed to configuire the content");
		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the CLP Content on the front end",
					"Configured content should be dispalyed on the front end",
					"Configured content is not reflected the fornt end", "failed to configuire the content");
			Assert.fail();
		}
	}

	public void validate_content_alignment(String dataSet) {

		try {
			String textalignment = Common.findElement("xpath", "//div[@data-content-type='hot_clp_banner']")
					.getAttribute("data-content-alignment");
			Common.assertionCheckwithReport(textalignment.contains(dataSet),
					"To validate the text alignment is" + dataSet, "The text alignment should be " + dataSet,
					"The text alignment is " + dataSet, "failed text alignmnet " + dataSet);

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the text alignment is" + dataSet,
					"The text alignment should be " + dataSet, "The text alignment is " + dataSet,
					"failed text alignmnet " + dataSet);
			Assert.fail();
		}
	}

	public void validate_text_color(String dataSet) {

		try {
			String textcolor = Common.findElement("xpath", "//div[@data-content-type='hot_clp_banner']")
					.getAttribute("data-text-color");

			Common.assertionCheckwithReport(textcolor.contains(dataSet), "To validate the text color is" + dataSet,
					"The text color should be " + dataSet, "The text color is " + dataSet,
					"failed to setup text color " + dataSet);

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the text color is" + dataSet,
					"The text color should be " + dataSet, "The text color is " + dataSet,
					"failed to setup text color " + dataSet);
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

			String path = System.getProperty("user.dir") + ("\\src\\test\\resources\\TestData\\Admin\\Lotusqa.png");
			Sync.waitElementPresent(40, "xpath", "(//input[@type='file'])[1]");
			Common.findElement("xpath", "(//input[@type='file'])[1]").sendKeys(path);
			Common.textBoxInput("name", "category_color_picker", "testingQACOlorpicker");
			Common.dropdown("name", "category_hero_block", SelectBy.TEXT, "Footer Hydro Flask");
			Common.textBoxInput("name", "short_description", "This content id for testing");
			String path1 = System.getProperty("user.dir") + ("\\src\\test\\resources\\TestData\\Admin\\Lotusqa1.png");
			Sync.waitElementPresent(40, "xpath", "(//input[@type='file'])[2]");
			Common.findElement("xpath", "(//input[@type='file'])[2]").sendKeys(path1);

			Sync.waitElementPresent("xpath", "(//label[text()='Select from Gallery'])[4]");
			Common.javascriptclickElement("xpath", "(//label[text()='Select from Gallery'])[4]");

			Sync.waitElementInvisible("xpath", "//div[@class='loading-mask' and @style='display: none;']");
			Sync.waitElementVisible("id", "contents");
			// Common.isElementVisibleOnPage(30, "xpath", "//div[@id='contents']");
			String image = "Screenshot_2.png"; // data.get(DataSet).get("");
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

			Common.scrollIntoView("xpath", "//div[contains(@class,'floating-header')]/button[@id='save']");
			Common.javascriptclickElement("xpath", "//div[contains(@class,'floating-header')]/button[@id='save']");

			/*
			 * Common.scrollIntoView("xpath", "//button[@class='action-default action']");
			 * Sync.waitElementPresent(30, "xpath",
			 * "//button[@class='action-default action']"); Common.mouseOverClick("xpath",
			 * "//button[@class='action-default action']"); Sync.waitElementPresent(40,
			 * "xpath", "//div[contains(@data-bind,'pageBuilderEditButtonClick')]"); String
			 * pagebuilder = Common.findElement("xpath",
			 * "//div[contains(@data-bind,'pageBuilderEditButtonClick')]")
			 * .getAttribute("class"); System.out.println(pagebuilder);
			 * Common.assertionCheckwithReport(pagebuilder.contains(
			 * "pagebuilder-wysiwyg-overlay"), "Validating edit page bulider navigation ",
			 * "After clicking on edit page builder it Should navigate to edit page builder field "
			 * , "Successfully navigate to the edit page builder field",
			 * "Failed to navigate to the edit page builder field");
			 */

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Configure a Schedule category update in the admin panel",
					"Scedule catgeory Should be configured in admin panel",
					"Scedule catgeory is not configured in admin panel",
					"Failed to schedule the category in the Admin panel");
			Assert.fail();
		}

	}

	public void editpromocontent() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
			String id = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']")
					.getAttribute("id");

//			Common.mouseOverClick("xpath", "//div[@id='"+id+"']//div[3]//i");
			Common.mouseOverClick("xpath", "//div[@id='" + id + "']");
			Common.clickElement("xpath", "//i[@class='icon-admin-pagebuilder-systems']");

			String editpromo = Common.findElement("xpath", "(//h1[@class='modal-title'])[1]").getText();

			Common.assertionCheckwithReport(editpromo.contains("Edit Promo Content (Product)"),
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

	public void addproducts(String string) {

		try {
			Common.scrollIntoView("xpath", "//span[text()='Products in Category']");
			Common.clickElement("xpath", "//span[text()='Products in Category']");
			String content = Common.findElement("xpath", "//div[@class='fieldset-wrapper-title']")
					.getAttribute("data-state-collapsible");
			Common.assertionCheckwithReport(content.contains("open"),
					"To validate the products in category menu is dispalyed",
					"products in category menu should be dispalyed", "products in category menu is dispalyed",
					"Failed to display the category menu");
			Common.clickElement("id", "catalog_category_add_product_tabs");

			Sync.waitElementVisible("xpath", "//aside[@data-type='popup']");
			String modalpopup = Common.findElement("xpath", "//aside[@data-type='popup']").getAttribute("class");
			Common.assertionCheckwithReport(modalpopup.contains("show"),
					"To validate the products modal popup is dispalyed", "products modal popup should be dispalyed",
					"products modal popup is dispalyed", "Failed to display products modal popup");
			Sync.waitElementClickable("id", "catalog_category_add_product_sku_tab");
			Common.javascriptclickElement("id", "catalog_category_add_product_sku_tab");
			int skubox = Common.findElements("id", "add_product_sku").size();
			Common.assertionCheckwithReport(skubox > 0, "To validate the Add sku tab is dispalyed",
					"Add sku tab should be dispalyed", "Add sku tab is dispalyed", "Failed to display add sku tab");

			Common.textBoxInput("id", "add_product_sku", "W32075");
			Common.actionsKeyPress(Keys.ENTER);
			Common.textBoxInput("id", "add_product_sku", "w32410");
			Common.actionsKeyPress(Keys.ENTER);
			Common.textBoxInput("id", "add_product_sku", "w32520");
			Common.actionsKeyPress(Keys.ENTER);

			Common.scrollIntoView("id", "add_product_sku_add_button");
			Common.javascriptclickElement("id", "add_product_sku_add_button");
			Thread.sleep(3000);
			Common.javascriptclickElement("name", "add_products_save_button");

		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("To validate the products in category menu is dispalyed",
					"products in category menu should be dispalyed", "products in category menu is not dispalyed",
					Common.getscreenShotPathforReport("Failed to display the category menu"));
			Assert.fail();

		}

	}

	public void edit_promoContentProduct_ContentSection(String DataSet) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
			String name = Common.getText("xpath", "(//span[text()='Content'])[3]");
			System.out.println(name);
			Sync.waitElementPresent("xpath", "(//input[@name='title'])[2]");
			Common.textBoxInput("xpath", "(//input[@name='title'])[2]", data.get(DataSet).get("title"));
			Thread.sleep(4000);

			Common.scrollIntoView("xpath", "//span[text()='Description']");
			Common.switchFrames("id", "hot_product_promo_form_description_ifr");
			Common.findElement("id", "html-body").sendKeys(data.get(DataSet).get("Description"));
			Common.switchToDefault();
			// Thread.sleep(2000);

			Thread.sleep(5000);
			report.addPassLog("validation to the edit content promo Blocker",
					"after giving data it should show the data", "Successfully data is populated",
					Common.getscreenShotPathforReport("Succesfully data entered"));
		} catch (Exception | Error e) {
			e.printStackTrace();

			report.addFailedLog("validation to the edit content promo Blocker ",
					"after giving data it should show the data", "Successfully data is populated",
					Common.getscreenShotPathforReport("Failed to show the data"));
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

	public void Click_Promo_Product(String Dataset) {
		// TODO Auto-generated method stub

		try {
			/*
			 * Common.switchToFirstTab(); Contentpage(); hot_elements();
			 * //editCategory_Productslider(); editpromocontent();
			 */
			Common.dropdown("xpath", "//select[@class='admin__control-select url-input-select']", Common.SelectBy.TEXT,
					data.get(Dataset).get("heading"));
			Common.clickElement("xpath", "//div[@class='admin__action-multiselect-wrap action-select-wrap']");

			String text = Common
					.findElement("xpath", "(//input[@class='admin__control-text admin__action-multiselect-search'])")
					.getAttribute("id");
			Common.textBoxInput("xpath", "//input[@id='" + text + "']", data.get(Dataset).get("productnames"));
			Sync.waitElementPresent("xpath", "//span[text()='32 OZ WIDE MOUTH STAINLESS']");
			Common.mouseOverClick("xpath", "//span[text()='32 OZ WIDE MOUTH STAINLESS']");

			Common.textBoxInput("xpath", "(//input[@name='link_text'])", data.get(Dataset).get("Buttontext"));
			Common.clickElement("xpath", "//input[@name='link_url']");
			int saved = Common.findElements("xpath", "(//div[@class='admin__fieldset-wrapper-content _hide'])").size();
			System.out.println(saved);
			Common.assertionCheckwithReport(saved > 0, "Enter the categroy details ",
					"Category details enterd succesfully", "Successfully v", "Failed to enterd the category details");
			// Configure_padding_marins(Dataset);
			Sync.waitElementPresent("xpath", "//button[@id='save']");
			Common.clickElement("xpath", "//button[@id='save']");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(" To Validate the User needs to save the page",
					"User should able to save the page", "Unable to saves the page",
					Common.getscreenShotPathforReport("Failed to save the page"));
			Assert.fail();
		}
	}

	public void dragndrop_promocontentBlock() {
		// TODO Auto-generated method stub
		try {
			Common.scrollIntoView("xpath", "//span[text()='Promo Content (Product)']");
			WebElement element = Common.findElement("xpath", "//span[text()='Promo Content (Product)']");
			draganddropContentBlock(element);
			String blocknames = Common.findElement("xpath", "//div[@class='pagebuilder-content-type']")
					.getAttribute("data-content-type");
			Common.assertionCheckwithReport(blocknames.equals("hot_product_promo"),
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

	public void ValidateScedulecategoryupdate(String string) {
		try {
			Common.clickElement(By.xpath("//span[text()=' TestSubcategoryHF']"));
			Sync.waitPageLoad();
			Sync.waitElementVisible("xpath", "//img[@title='TestSubcategoryHF']");
			String heroimage = Common.findElement("xpath", "//img[@title='TestSubcategoryHF']").getAttribute("src");
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("//span[text()=' TestSubcategoryHF']")
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
			Common.textBoxInput("name", "description", data.get(dataSet).get("Description"));
			Common.clickElement("xpath", "//input[@class='admin__actions-switch-checkbox']");
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
		String HFCategory = data.get(dataSet).get("HFCategory");
		String HFsubcategory = data.get(dataSet).get("HFsubcategory");
		String OxoCategory = data.get(dataSet).get("OxoCategory");
		String Oxosubcategory = data.get(dataSet).get("Oxosubcategory");
		try {

			Sync.waitElementPresent("xpath", "(//img[contains(@class,'elbow-end-plus')])[1]");
			Common.javascriptclickElement("xpath", "(//img[contains(@class,'elbow-end-plus')])[1]");
			// "//span[contains(text(),'" + Newcategory + "')]");
			Thread.sleep(3000);
			Common.doubleClick("xpath", "//span[contains(text(),'" + HFCategory + "')]");
			Sync.waitElementPresent(30, "xpath", "//span[contains(text(),'" + HFsubcategory + "')]");

			Common.javascriptclickElement("xpath", "//span[contains(text(),'" + HFsubcategory + "')]");
			Thread.sleep(3000);
			Common.scrollIntoView("xpath", "(//img[contains(@class,'x-tree-elbow-plus')])[5]");
			Common.javascriptclickElement("xpath", "(//img[contains(@class,'x-tree-elbow-plus')])[5]");
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//span[contains(text(),'" + OxoCategory + "')]");
			Common.doubleClick("xpath", "//span[contains(text(),'" + OxoCategory + "')]");
			Sync.waitElementPresent("xpath", "//span[contains(text(),'" + Oxosubcategory + "')]");
			Common.javascriptclickElement("xpath", "//span[contains(text(),'" + Oxosubcategory + "')]");

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
			Thread.sleep(200000);
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
		String HFCategory = data.get(dataSet).get("HFCategory");
		String HFsubcategory = data.get(dataSet).get("HFsubcategory");
		String discount = data.get(dataSet).get("Discount");

		try {

			Common.mouseOverClick("xpath", "//span[text()=' Shop']");
			Common.mouseOverClick("xpath", "//span[contains(text(),'" + HFCategory + "')]");

			Sync.waitElementPresent(30, "xpath", "//span[contains(text(),'" + HFsubcategory + "')]");
			Common.javascriptclickElement("xpath", "//span[contains(text(),'" + HFsubcategory + "')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementVisible("xpath", "//span[text()=' Shop']");
			Common.javascriptclickElement("xpath", "//span[text()=' Shop']");
			Common.mouseOverClick("xpath", "//span[contains(text(),'" + HFCategory + "')]");

			Sync.waitElementPresent(30, "xpath", "//span[contains(text(),'" + HFsubcategory + "')]");
			Common.javascriptclickElement("xpath", "//span[contains(text(),'" + HFsubcategory + "')]");
			Sync.waitPageLoad();
			Sync.waitElementVisible("xpath", "(//span[contains(@class,'old-price')]/span)");
			for (int i = 1; i <= 5; i++) {
				Common.scrollIntoView("xpath", "(//span[contains(@class,'old-price')]/span)[" + i + "]");
				String Oldprice = Common.findElement("xpath", "(//span[contains(@class,'old-price')]/span)[" + i + "]")
						.getText().replace("$", "");
				System.out.println("website old price" + Oldprice);
				String Website_discountprice = Common
						.findElement("xpath", "(//span[contains(@class,'normal-price')]/span)[" + i + "]").getText()
						.replace("$", "");

				float OldPrice = Float.parseFloat(Oldprice);
				float Website_Discountprice = Float.parseFloat(Website_discountprice);
				System.out.println("Website_Discountprice" + Website_Discountprice);

				float discountpercent = Float.parseFloat(discount);

				float discountamount = OldPrice * discountpercent / 100;
				System.out.println(discountamount);

				String calculatednewprice = new BigDecimal(discountamount).setScale(2, BigDecimal.ROUND_HALF_UP)
						.toString();
				System.out.println(calculatednewprice);
				float CalculatedNewPrice = Float.parseFloat(calculatednewprice);
				System.out.println("calculatednewprice " + CalculatedNewPrice);

				float specialrpice = OldPrice - CalculatedNewPrice;
				System.out.println("calculatedspecialprice" + specialrpice);
				String discountprice = new BigDecimal(specialrpice).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				float calculateddiscountprice = Float.parseFloat(discountprice);
				System.out.println("calculateddiscountprice" + calculateddiscountprice);

				Common.assertionCheckwithReport(Website_Discountprice == calculateddiscountprice,
						"To validate the catalog price is applied on the hf store front",
						"Catalog price rule should be applied on the store hf fromnt",
						"Catalog price rule is applied on the hf store fromnt",
						"failed to apply the catalog price rule on hf front end");

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the catalog price is applied on the hf store front",
					"Catalog price rule should be applied on the hf store fromnt",
					"catalog price rule  is not updated on the frontend",
					Common.getscreenShotPathforReport("failed to apply the catalog price rule on the hf frontend"));
			Assert.fail();
		}
	}

	public void Validate_Catalog_Pricerule_OXO_Frontend(String dataSet) {
		// TODO Auto-generated method stub
		String OxoCategory = data.get(dataSet).get("OxoCategory");
		String Oxosubcategory = data.get(dataSet).get("Oxosubcategory");
		String discount = data.get(dataSet).get("Discount");
		try {
			Common.mouseOverClick("xpath", "(//a[@class='level-top ui-corner-all'])[1]");
			Common.mouseOverClick("xpath", "//span[contains(text(),'" + OxoCategory + "')]");
			Sync.waitElementPresent(30, "xpath", "//span[contains(text(),'" + Oxosubcategory + "')]");
			Common.javascriptclickElement("xpath", "//span[contains(text(),'" + Oxosubcategory + "')]");
			Common.javascriptclickElement("xpath", "//a[@aria-label='Shop All   Coffee & Tea ']");
			Sync.waitPageLoad();
			Sync.waitElementVisible("xpath", "//span[contains(@class,'before_special')]/span");
			for (int i = 1; i <= 5; i++) {
				Common.scrollIntoView("xpath", "(//span[contains(@class,'before_special')]/span)[" + i + "]");
				String Oldprice = Common
						.findElement("xpath", "(//span[contains(@class,'before_special')]/span)[" + i + "]").getText()
						.replace("$", "");
				System.out.println("website old price" + Oldprice);
				String Website_discountprice = Common
						.findElement("xpath", "(//span[contains(@class,'after_special')]/span)[" + i + "]").getText()
						.replace("$", "");
				System.out.println("website discount price" + Website_discountprice);

				float OldPrice = Float.parseFloat(Oldprice);
				float Website_Discountprice = Float.parseFloat(Website_discountprice);
				float discountpercent = Float.parseFloat(discount);

				float discountamount = OldPrice * discountpercent / 100;
				System.out.println(discountamount);

				String calculatednewprice = new BigDecimal(discountamount).setScale(2, BigDecimal.ROUND_HALF_UP)
						.toString();
				System.out.println(calculatednewprice);
				float CalculatedNewPrice = Float.parseFloat(calculatednewprice);
				System.out.println("calculatednewprice " + CalculatedNewPrice);

				float specialrpice = OldPrice - CalculatedNewPrice;
				System.out.println("calculatedspecialprice" + specialrpice);
				String discountprice = new BigDecimal(specialrpice).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				float calculateddiscountprice = Float.parseFloat(discountprice);
				System.out.println(calculateddiscountprice);

				Common.assertionCheckwithReport(Website_Discountprice == calculateddiscountprice,
						"To validate the catalog price is applied on the oxo store front",
						"Catalog price rule should be applied on the store oxo fromnt",
						"Catalog price rule is applied on the oxo store fromnt",
						"failed to apply the catalog price rule on oxo front end");

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the catalog price is applied on the oxo store front",
					"Catalog price rule should be applied on the oxo store fromnt",
					"catalog price rule  is not updated on the oxo frontend",
					Common.getscreenShotPathforReport("failed to apply the catalog price rule on the oxo frontend"));
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

	public void SelectCustomer_Edit(String dataset) {
		String email = data.get(dataset).get("Email");
		String storeview = data.get(dataset).get("Store");
		String website = data.get(dataset).get("Website");
		System.out.println(website);
		try {
			System.out.println(email);
			Thread.sleep(4000);
			int filters=Common.findElements("xpath", "//div[@class='admin__data-grid-filters-current _show']").size();
			if(filters>0)
			{
				Common.clickElement("xpath", "//div[@class='admin__data-grid-filters-current _show']//button[text()='Clear all']");
				Thread.sleep(4000);
			}
				Sync.waitElementPresent("xpath", "//button[@data-action='grid-filter-expand']");
				Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
				Common.textBoxInput("xpath", "//input[@name='email']", data.get(dataset).get("Email"));
				Common.clickElement("xpath", "//div[@class='admin__form-field-control']//option[text()='"+ website +"']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//button[@class='action-secondary']//span[text()='Apply Filters']");

			Thread.sleep(5000);
			String records = Common.findElement("xpath", "//div[@class='admin__control-support-text']").getText();
			System.out.println(records);

			String name = Common.findElement("xpath", "(//div[@class='data-grid-cell-content'])[2]").getText();
			if (records.equals("1 records found")) {
				Common.clickElement("xpath", "//a[text()='Edit']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.assertionCheckwithReport(Common.getPageTitle().contains(name + " / Customers / "),
						"Validating the edit button on the customers page",
						"After clicking edit button it should navigate to the selected page",
						"Successfully navigate to the selected page when we click on edit button",
						"Failed to navigate to the selected page");

			} else {

				// Common.findElement("xpath", "//div[text()='" + storeview + "']");
				Sync.waitElementPresent("xpath", "(//a[text()='Edit'])[1]");
				Common.clickElement("xpath", "(//a[text()='Edit'])[1]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.assertionCheckwithReport(Common.getPageTitle().contains(name + " / Customers / "),
						"Validating the edit button on the customers page",
						"After clicking edit button it should navigate to the selected page",
						"Successfully navigate to the selected page when we click on edit button",
						"Failed to navigate to the selected page");
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

	public void Edit_Customer_StoreCredit(String dataset) {

		try {
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//span[text()='Store Credit']");
			Common.clickElement("xpath", "//span[text()='Store Credit']");
			Thread.sleep(6000);
			Common.textBoxInput("id", "_customerbalanceamount_delta", data.get(dataset).get("UpdateBalance"));
			Thread.sleep(2000);
			Sync.waitElementPresent("id", "_customerbalancenotify_by_email");
			Common.clickElement("id", "_customerbalancenotify_by_email");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//button[@title='Save Customer']");
			Common.clickElement("xpath", "//button[@title='Save Customer']");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			String successmsg = Common.findElement("xpath", "//div[@class='message message-success success']")
					.getText();
			System.out.println(successmsg);

			Common.assertionCheckwithReport(successmsg.contains("customer"),
					"update the store Credit balance  on the customers page",
					" store credit  balance should update to the customer",
					"Successfully updated the store credit balance for the selected customer",
					"Passed to update the balance");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("update the store Credit balance  on the customers page",
					"store credit  balance should update to the customer",
					"failed to updated the store credit balance for the selected customer",
					Common.getscreenShotPathforReport("Failed to update the Store Credit"));
			Assert.fail();

		}
	}

	public void click_Sales() {
		// TODO Auto-generated method stub

		try {
			Sync.waitPageLoad();
			Sync.waitElementPresent("id", "menu-magento-sales-sales");
			Common.clickElement("id", "menu-magento-sales-sales"); // this line clicks on sale option in magento
			Thread.sleep(2000);
			String Sales = Common.findElement("xpath", "//li[@class='item-sales-order    level-2']").getText();
			System.out.println(Sales);
		
			Common.assertionCheckwithReport(Sales.equals("Orders"), "To verify the sales menu ",
					"After clicking the sales menu it will display menu options ",
					"Successfully clicked the sales menu and it displayed the Catalog options",
					"Failed to click the sales menu");
			Common.clickElement("xpath", "//li[@class='item-sales-order    level-2']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To verify the sales menu ",
					"After clicking the sales menu it will display menu options ",
					"Successfully clicked the sales menu and it displayed the sales options",
					Common.getscreenShotPathforReport("Failed to click on the sales menu"));
			Assert.fail();
		}

	}


	public void Click_Orders_Salesmenu() {
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
			Sync.waitElementPresent("xpath", "//span[text()='Create New Order']");
			Common.clickElement("xpath", "//span[text()='Create New Order']");
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

	public void Enter_email(String dataSet) {
		// TODO Auto-generated method stub
		String storeview = data.get(dataSet).get("Store");
		try {
			Thread.sleep(5000);
			Common.textBoxInput("xpath", "//input[@name='email']", data.get(dataSet).get("Email"));
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(5000);
//			Sync.waitElementPresent("xpath", "//td[text()='QA TEST']");
//			Common.clickElement("xpath", "//td[text()='QA TEST']");
			Sync.waitElementPresent("xpath", "//td[contains(text(),'" + storeview + "')]");
			Common.clickElement("xpath", "//td[contains(text(),'" + storeview + "')]");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			String title = Common.findElement("xpath", "//h1[@id='order-header']").getText();
			System.out.println(title);
			Common.assertionCheckwithReport(title.contains("Create New Order for"),
					"To Validate the Create New Order for QA TESTr page is displayed",
					"should display the Create New Order for QA TESTr page after clicking on the create new order",
					"Create New Order for QA TEST page is displayed after a click on the create new order button",
					"Failed to display Create New Order for New Customer page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the Create New Order for QA TEST page is displayed",
					"should display the Create New Order for QA TEST page after clicking on the create new order",
					"unable to display Create New Order for QA TEST page after a click on the create new order button",
					"Failed to display Create New Order for QA TEST page");
			Assert.fail();
		}

	}

	public void Select_astore() {
		// TODO Auto-generated method stub

		try {
			Thread.sleep(2000);

			Sync.waitElementPresent("xpath", "//label[text()='Hydroflask Store View']");
			Common.clickElement("xpath", "//label[text()='Hydroflask Store View']");
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

	public void Select_store(String dataSet) {
		// TODO Auto-generated method stub
		String Stores = data.get(dataSet).get("Store");
		try {
			Thread.sleep(2000);

			Sync.waitElementPresent("xpath", "//label[text()='" + Stores + "']");
			Common.clickElement("xpath", "//label[text()='" + Stores + "']");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			String title = Common.findElement("xpath", "//strong[text()='Items Ordered']").getText();
			System.out.println(title);
//		//	Common.assertionCheckwithReport(title.contains("Orders"),
//					"To Validate the Items Ordered page is displayed",
//					"should display the Items Orderedpage after clicking on the store",
//					"Items Ordered page is displayed after a click on the store button",
//					"Failed to display Items Ordered page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the Items Ordered page is displayed",
					"should display the Items OrderedT page after clicking on the store",
					"unable to display Items OrderedT page after a click on the store button",
					"Failed to display Items Ordered page");
			Assert.fail();
		}

	}

	public void payment_method(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);

			Sync.waitElementPresent("xpath", "//input[@title='Default Payment']");
			Common.clickElement("xpath", "//input[@title='Default Payment']");
			Thread.sleep(3000);

			Sync.waitElementPresent("xpath", "//div[@id='stripe-card-element']");
			Common.clickElement("xpath", "//div[@id='stripe-card-element']");
			Thread.sleep(2000);
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

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the sucess message is displayed",
					"should display the sucess message after clicking on the sudmitorder",
					"unable to display sucess message after a click on the sudmitorder button",
					"Failed to display sucess message");
			Assert.fail();
		}

	}

	public void Select_Shipping_method(String dataSet) {
		// TODO Auto-generated method stub

		String Website = data.get(dataSet).get("Store");
		 String Shipping=data.get(dataSet).get("Shipping");
		 System.out.println(Website);
		 System.out.println(Shipping);
		try {

			// Common.scrollIntoView("xpath",
			// "//div[@id='order-shipping-method-summary']/a");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//div[@id='order-shipping-method-summary']/a");

			Common.doubleClick("xpath", "//span[contains(text(), 'Get shipping methods and rates')]");
			Thread.sleep(2000);

			if (Website.contains("OXO Store View")) {
				Thread.sleep(3000);
				
			         System.out.println("Shipping"+Shipping);
//						Common.switchToFirstTab();
						int size =Common.findElements("xpath", "//span[text()='Click to change shipping method']").size();
						if(size>0) {
							Sync.waitElementVisible("xpath", "//span[text()='Click to change shipping method']");
							Common.doubleClick("xpath", "//span[text()='Click to change shipping method']");
						}
						else {
							Thread.sleep(3000);
							Sync.waitElementVisible("xpath", "//label[text()='"+Shipping+"']");
							Common.doubleClick("xpath", "//label[text()='"+Shipping+"']");
						}
						
				Sync.waitPageLoad();
			}
			else if (Website.contains("Osprey US English")) {
				int size =Common.findElements("xpath", "//span[text()='Click to change shipping method']").size();
				if(size>0) {
					Sync.waitElementVisible("xpath", "//span[text()='Click to change shipping method']");
					Common.doubleClick("xpath", "//span[text()='Click to change shipping method']");
					Common.doubleClick("xpath", "//label[text()='"+Shipping+"']");
				}
				else {
					Thread.sleep(3000);
					Sync.waitElementVisible("xpath", "//label[text()='"+Shipping+"']");
					Common.doubleClick("xpath", "//label[text()='"+Shipping+"']");
				}
				
			} 
			else if(Website.contains("Osprey UK English Store"))
			{
				int size =Common.findElements("xpath", "//span[text()='Click to change shipping method']").size();
				if(size>0) {
					Sync.waitElementVisible("xpath", "//span[text()='Click to change shipping method']");
					Common.doubleClick("xpath", "//span[text()='Click to change shipping method']");
					Common.doubleClick("xpath", "//label[text()='"+Shipping+"']");
				}
				else {
					Thread.sleep(3000);
					Sync.waitElementVisible("xpath", "//label[text()='"+Shipping+"']");
					Common.doubleClick("xpath", "//label[text()='"+Shipping+"']");
				}
			}
			else if (Website.contains("Drybar Store View"))
			{
				 System.out.println("Shipping"+Shipping);
//					Common.switchToFirstTab();
					int size =Common.findElements("xpath", "//span[text()='Click to change shipping method']").size();
					if(size>0) {
						Sync.waitElementVisible("xpath", "//span[text()='Click to change shipping method']");
						Common.doubleClick("xpath", "//span[text()='Click to change shipping method']");
					}
					else {
						Thread.sleep(3000);
						Sync.waitElementVisible("xpath", "//label[text()='"+Shipping+"']");
						Common.doubleClick("xpath", "//label[text()='"+Shipping+"']");
					}
				
			}
			else if (Website.contains("Hydroflask Store View"))
			{
				 System.out.println("Shipping"+Shipping);
//					Common.switchToFirstTab();
					int size =Common.findElements("xpath", "//span[text()='Click to change shipping method']").size();
					if(size>0) {
						Sync.waitElementVisible("xpath", "//span[text()='Click to change shipping method']");
						Common.doubleClick("xpath", "//span[text()='Click to change shipping method']");
					}
					else {
						Thread.sleep(3000);
						Sync.waitElementVisible("xpath", "//label[text()='"+Shipping+"']");
						Common.doubleClick("xpath", "//label[text()='"+Shipping+"']");
					}
				
			}
			else
			{
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//input[@id='s_method_tablerate_bestway']");
				Common.clickElement("xpath", "//input[@id='s_method_tablerate_bestway']");
				Sync.waitPageLoad();
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
				int selectedshippingmethods = Common.findElements("xpath", "//dd[contains(text(),'Ground')]").size();
				System.out.println(selectedshippingmethods);
				int changeshippingmethods = Common
						.findElements("xpath", "//span[text()='Click to change shipping method']").size();
				System.out.println(changeshippingmethods);
				Common.assertionCheckwithReport(selectedshippingmethods >= 1 && changeshippingmethods > 0,
						"To validate the shipping methods is selected",
						"Shipping methods should be selected and change the shipping methods link should be displayed",
						"Shipping methods are selected and change the shipping methods link is displayed",
						"Failed to select Shipping methods and change the shipping methods link is not displayed");
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the shipping methods is selected",
					"Shipping methods should be selected and change the shipping methods link should be displayed",
					"Shipping methods are not selected and change the shipping methods link is not displayed",
					"Failed to select Shipping methods and change the shipping methods link is not displayed");
			Assert.fail();
		}

	}

	public void Select_Storecredit_payment_method(String DataSet) {
		// TODO Auto-generated method stub
		String Symbol = data.get(DataSet).get("Symbol");
		try {
			Common.switchToFirstTab();
			Common.refreshpage();
			Thread.sleep(5000);
			Sync.waitElementPresent("id", "p_method_use_customerbalance");
			Common.clickElement("id", "p_method_use_customerbalance");
			Thread.sleep(5000);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

			int appliedstorecredit = Common.findElements("xpath", "//label[text()='No Payment Information Required']")
					.size();
			Common.scrollIntoView("xpath", "//td[@class='admin__total-amount']/strong");
			String grandtotal = Common.findElement("xpath", "//td[@class='admin__total-amount']/strong").getText()
					.replace(Symbol, "");
			System.out.println(grandtotal);
			float GrandTotal = Float.parseFloat(grandtotal);
			Common.assertionCheckwithReport(appliedstorecredit > 0 && GrandTotal == 0.00,
					"To Validate the payment method is selected", "should select the customer balane Payment method",
					"Successfully selected the Payment method", "passed to Select");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the payment method is selected",
					"should select the customer balane Payment method", "unable to select the Payment method",
					"Failed to Select");
			Assert.fail();
		}

	}

	public String Submit_RetailOrder_Success() {
		// TODO Auto-generated method stub
		String ordernumber = "";
		try {

			Sync.waitElementPresent("xpath", "//span[text()='Submit Retail']");
			Common.clickElement("xpath", "//span[text()='Submit Retail']");
			Sync.waitPageLoad();

			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

			Sync.waitElementVisible("xpath", "//div[contains(@class,'success')]/div");
			Thread.sleep(8000);
			String successmessage = Common.findElement("xpath", "//div[contains(@class,'success')]/div").getText();

			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("Orders / Operations / Sales / Magento Admin")
							&& successmessage.contains("You created the order."),
					"To Validate the orders page is displayed",
					"should display the orders page after clicking on the orders",
					"orders page is displayed after a click on the order button", "Failed to display orders page");
			Thread.sleep(4000);
			ordernumber = Common.findElement("xpath", "//h1[@class='page-title']").getText();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the orders page is displayed",
					"should display the orders page after clicking on the orders",
					"unable to display orders page after a click on the orders button",
					"Failed to display orders page");
			Assert.fail();
		}

		return ordernumber;
	}

	public void Click_Edit() {
		// TODO Auto-generated method stub
		String expectedResult = "to edit and navigate to next page";

		try {
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//a[text()='Edit']");
			Common.clickElement("xpath", "//a[text()='Edit']");
			Thread.sleep(6000);

			int pages = Common.findElements("xpath", "//h1[text()='QA Testing Product']").size();
			System.out.println(pages);

			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("Products / Inventory / Catalog / Magento Admin"),
					"To Validate the QATEST product is displayed",
					"should display the QATEST product after clicking on the prouct",
					"QATEST product is displayed after a click on the product", "Failed to display QATEST product");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate the page is deleted successfully and success message is dispalyed",
					"Should display successmessage after deleteing the catalog price rule",
					"Failed to delet the categroy price rule", "Catalog price rule deletion unsuccessfull");
			Assert.fail();

		}
	}

	public void QAtest_Advanced_Pricing(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String expectedResult = "to edit and navigate to next page";

		try {
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//span[text()='Advanced Pricing']");
			Common.clickElement("xpath", "//span[text()='Advanced Pricing']");
			Thread.sleep(3000);
			Common.textBoxInput("xpath", "(//input[@name='product[special_price]'])",
					data.get(dataSet).get("SpecialPrice"));
			Thread.sleep(3000);

			Thread.sleep(2000);
			Common.dropdown("xpath", "(//select[@name='product[tier_price][0][cust_group]'])", Common.SelectBy.TEXT,
					data.get(dataSet).get("CustomerGroup"));

			Thread.sleep(3000);
			Common.textBoxInput("xpath", "(//input[@name='product[tier_price][0][price_qty]'])",
					data.get(dataSet).get("Qty"));

			// Thread.sleep(3000);
			// Common.textBoxInput("xpath",
			// "(//select[@name='product[tier_price][0][value_type]'])",data.get(dataSet).get("QtyPrice"));

			Thread.sleep(3000);
			Common.textBoxInput("xpath", "(//input[@name='product[tier_price][0][price]'])",
					data.get(dataSet).get("Price"));

			Common.clickElement("xpath", "(//span[text()='Done'])[4]");
			Thread.sleep(3000);

			int pages = Common.findElements("xpath", "//div[@class='message message-success success']").size();
			System.out.println(pages);

			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("Products / Inventory / Catalog / Magento Admin"),
					"To Validate the QATEST product is displayed",
					"should display the QATEST product after clicking on the prouct",
					"QATEST product is displayed after a click on the product", "Failed to display QATEST product");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the page price successfully and success message is dispalyed",
					"Should display successmessage after deleteing the catalog price rule",
					"Failed to delet the categroy price rule", "Catalog price rule deletion unsuccessfull");
			Assert.fail();

		}

	}


	public void Homepage_searchproduct(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String expectedResult = "to search product in homepage";
		String Search= data.get(dataSet).get("search");

		try {
			Thread.sleep(4000);
			Common.clickElement("xpath", "//button[@id='menu-search-icon']"); 
			String open = Common.findElement("xpath", "//button[@id='menu-search-icon']").getAttribute("id");
			Thread.sleep(4000);
			Common.textBoxInput("xpath", "//input[@id='autocomplete-0-input']", data.get(dataSet).get("search"));
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String productsearch = Common.findElement("xpath", "//span[@id='algolia-srp-title']").getText();
			System.out.println(productsearch);
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To saearch product in the  the home page successfully and success message is dispalyed",
					"Should display successmessage after deleteing the catalog price rule",
					"Failed to delet the categroy price rule", "Catalog price rule deletion unsuccessfull");
			Assert.fail();

		}
	}

	public void click_product() {
		// TODO Auto-generated method stub
		String expectedResult = "to validate product PDP";

		try {
			Thread.sleep(3000);
			Common.clickElement("xpath", "//mark[text()='QA']");
			Thread.sleep(2000);
			int pages = Common.findElements("xpath", "//p[text()='QATEST product']").size();
			System.out.println(pages);

			ExtenantReportUtils.addPassLog("verifying the PDP functionality",
					"Should naviagte the  product name in search field",
					"user successfully Entered the product name in search field",
					Common.getscreenShotPathforReport("searched productname successfully"));

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To saearch product to PDP successfully and success message is dispalyed",
					"Should display successmessage after deleteing the catalog price rule",
					"Failed to delet the categroy price rule", "Catalog price rule deletion unsuccessfull");
			Assert.fail();

		}
	}

	public void QAtestproduct_Advanced_Pricing(String dataSet) {
		// TODO Auto-generated method stub

		String expectedResult = "to edit and navigate to next page";

		try {
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//span[text()='Advanced Pricing']");
			Common.clickElement("xpath", "//span[text()='Advanced Pricing']");
			Thread.sleep(6000);

			// int pages = Common.findElements("xpath", "//h1[text()='QATEST
			// product']").size();
			// System.out.println(pages);

			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//span[text()='Add']");
			Common.clickElement("xpath", "//span[text()='Add']");

			Sync.waitElementPresent("xpath", "//span[text()='Advanced Pricing']");
			Common.clickElement("xpath", "//span[text()='Advanced Pricing']");
			Thread.sleep(3000);
			Common.textBoxInput("xpath", "(//input[@name='product[special_price]'])",
					data.get(dataSet).get("SpecialPrice"));

			// Sync.waitElementPresent("xpath", "//span[text()='Add']");
			// Common.clickElement("xpath", "//span[text()='Add']");

			Thread.sleep(2000);
			Common.dropdown("xpath", "(//select[@name='product[tier_price][1][cust_group]'])", Common.SelectBy.TEXT,
					data.get(dataSet).get("CustomerGroup"));

			Thread.sleep(3000);
			Common.textBoxInput("xpath", "(//input[@name='product[tier_price][1][price_qty]'])",
					data.get(dataSet).get("Qty"));
			Common.textBoxInput("xpath", "(//input[@class='admin__control-text'])[24]", data.get(dataSet).get("Qty"));
			Thread.sleep(5000);
			// Common.textBoxInput("xpath",
			// "(//select[@name='product[tier_price][1][value_type]'])",data.get(dataSet).get("QtyPrice1"));

			Common.dropdown("xpath", "(//select[@name='product[tier_price][1][value_type]'])", Common.SelectBy.TEXT,
					data.get(dataSet).get("QtyPrice1"));

			Thread.sleep(3000);
			Common.textBoxInput("xpath", "(//input[@name='product[tier_price][1][percentage_value]'])",
					data.get(dataSet).get("percentage"));

			Common.clickElement("xpath", "(//span[text()='Done'])[4]");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//button[@id='save-button']");

			int pages = Common.findElements("xpath", "//div[@class='message message-success success']").size();
			System.out.println(pages);

			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("Products / Inventory / Catalog / Magento Admin"),
					"To Validate the QATEST product is displayed",
					"should display the QATEST product after clicking on the prouct",
					"QATEST product is displayed after a click on the product", "Failed to display QATEST product");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the page price successfully and success message is dispalyed",
					"Should display successmessage after deleteing the catalog price rule",
					"Failed to delet the categroy price rule", "Catalog price rule deletion unsuccessfull");
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

	public void Select_Store(String dataSet) {
		// TODO Auto-generated method stub

		String Website = data.get(dataSet).get("Store");
System.out.println(Website);
		try {
			Common.findElement("xpath", "//label[text()='" + Website + "']");
			Common.clickElement("xpath", "//label[text()='" + Website + "']");

			Thread.sleep(8000);
			Sync.waitPageLoad();
	/*		String page = Common.findElement("xpath", "//h1[@class='page-title']").getText();
			Thread.sleep(10000);
			
			Common.assertionCheckwithReport(page.contains(Website),
					"To Validate the create new order page is displayed",
					"should display the create new order page after clicking on the store",
					"create new order page is displayed after a click on the store button",
					"Failed to display create new order page");
*/
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the create new order page page is displayed",
					"should display the create new order page after clicking on the store",
					"unable to display create new order page after a click on the store button",
					"Failed to display create new order page");
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

				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//div[@class='actions']//span[text()='Add Products By SKU']");
				Common.clickElement("xpath", "//div[@class='actions']//span[text()='Add Products By SKU']");
				Thread.sleep(2000);
				Sync.waitElementPresent("xpath", "//td[@class='col-sku']//input[@name='sku']");
				Common.textBoxInput("xpath", "//td[@class='col-sku']//input[@name='sku']", SKUnumber[i]);

				Common.textBoxInput("xpath", "//div[@class='input-box']//input[@name='qty']", Quantity[i]);

				Sync.waitElementPresent("xpath", "//button[@title='Add to Order']");
				Common.clickElement("xpath", "//button[@title='Add to Order']");
				Common.implicitWait();
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

	public void shippingaddress(String dataSet) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
			// Sync.waitElementPresent("xpath",
			// "//label[@for='order-billing_address_firstname']");
			// Common.textBoxInputClear("xpath",
			// "//input[@id='order-billing_address_firstname']");
			Common.textBoxInput("xpath", "//input[@id='order-billing_address_firstname']",
					data.get(dataSet).get("FirstName"));

			Sync.waitElementPresent("xpath", "//input[@id='order-billing_address_lastname']");
			Common.textBoxInput("xpath", "//input[@id='order-billing_address_lastname']",
					data.get(dataSet).get("LastName"));

			Sync.waitElementPresent("xpath", "//input[@id='order-billing_address_street0']");
			Common.textBoxInput("xpath", "//input[@id='order-billing_address_street0']",
					data.get(dataSet).get("streetaddress"));

			Thread.sleep(4000);
			if(Common.getCurrentURL().contains("emea"))
			{
				Sync.waitElementPresent("xpath", "//select[@id='order-billing_address_country_id']");
				Common.dropdown("xpath", "//select[@id='order-billing_address_country_id']", Common.SelectBy.TEXT,
						data.get(dataSet).get("Country"));
				
				
				Sync.waitElementPresent("xpath", "//input[@id='order-billing_address_region']");
				Common.textBoxInput("xpath", "//input[@id='order-billing_address_region']",
						data.get(dataSet).get("State"));
			}
			else {
			Sync.waitElementPresent("xpath", "//select[@id='order-billing_address_country_id']");
			Common.dropdown("xpath", "//select[@id='order-billing_address_country_id']", Common.SelectBy.TEXT,
					data.get(dataSet).get("Country"));
			Sync.waitElementPresent("xpath", "//select[@id='order-billing_address_region_id']");
			Common.dropdown("xpath", "//select[@id='order-billing_address_region_id']", Common.SelectBy.TEXT,
					data.get(dataSet).get("State"));
			}
			

			Sync.waitElementPresent("xpath", "//input[@id='order-billing_address_city']");
			Common.textBoxInput("xpath", "//input[@id='order-billing_address_city']", data.get(dataSet).get("City"));

			Sync.waitElementPresent("xpath", "//input[@id='order-billing_address_postcode']");
			Common.textBoxInput("xpath", "//input[@id='order-billing_address_postcode']",
					data.get(dataSet).get("Postcode"));

			Sync.waitElementPresent("xpath", "//input[@id='order-billing_address_telephone']");
			Common.textBoxInput("xpath", "//input[@id='order-billing_address_telephone']",
					data.get(dataSet).get("Phonenumber"));
			Thread.sleep(3000);

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the displayed click to change shipping method",
					"should display the click to change shipping method after clicking on the standard shipping",
					"unable to display click to change shipping method after a click on the standard shipping",
					"Failed to display click to change shipping method");
			Assert.fail();

		}
	}

	public void Configure_child_Products(String dataSet) {
		// TODO Auto-generated method stub
		String[] product_quantity = data.get(dataSet).get("quantity").split(",");

		try {
			Common.clickElement("xpath", "//span[text()='Configure']");
			int productcount = Common.findElements("xpath", "//td[@class='col-name']").size();

			for (int i = 1; i <= productcount; i++) {
				Sync.waitElementClickable("xpath", "(//input[contains(@name,'super_group')])[" + i + "]");
				Common.textBoxInput("xpath", "(//input[contains(@name,'super_group')])[" + i + "]",
						product_quantity[i - 1]);
				Common.scrollIntoView("xpath", "(//input[contains(@name,'super_group')])[" + i + "]");

			}
			Common.scrollIntoView("xpath", "//button[@class='action-primary']");
			Common.javascriptclickElement("xpath", "//button[@class='action-primary']");

			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//table[@id='sku_errors_table']");
			int noticemessage = Common.findElements("xpath", "//div[@class='message message-notice']").size();
			System.out.println(noticemessage);
			Common.assertionCheckwithReport(noticemessage <= 0,
					"To Validate the selected group product child items added with sku,productname ,qty",
					"should add the selected group product child items with sku,productname ,qty",
					"Selected group product child items added successfuly with sku,productname ,qty",
					"Failed to add group product child items with sku,productname ,qty");
			Common.javascriptclickElement("xpath", "//button[@title='Add Products to Order']");
			Common.getscreenShotPathforReport("Adding Products to Order");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To Validate the selected group product child items added with sku,productname ,qty",
					"should add the selected group product child items with sku,productname ,qty",
					"unable to add the selected group product child items with sku,productname ,qty",
					"Failed to add group product child items with sku,productname ,qty");
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
		

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the displayed click to change shipping method",
					"should display the click to change shipping method after clicking on the standard shipping",
					"unable to display click to change shipping method after a click on the standard shipping",
					"Failed to display click to change shipping method");
			Assert.fail();

		}

	}

	public void Click_Marketing() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);

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

	public void Click_AddNewRule() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);

			Sync.waitElementPresent("xpath", "//button[@title='Add New Rule']");
			Common.clickElement("xpath", "//button[@title='Add New Rule']");
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
			Thread.sleep(2000);
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

			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//div//strong//span[text()='Actions']");
			Common.javascriptclickElement("xpath", "//div//span[text()='Actions']");
			Common.clickElementStale("xpath", "//div//span[text()='Actions']");
			

			Sync.waitElementPresent("xpath", "//select[@name='simple_action']");
			Common.clickElement("xpath", "//select[@name='simple_action']");
			

			// Sync.waitElementPresent("xpath", "//option[@data-title='Fixed amount
			// discount']");
			Common.dropdown("xpath", "//select[@name='simple_action']", Common.SelectBy.TEXT,
					data.get(dataSet).get("Apply"));

			Sync.waitElementPresent("xpath", "//input[@name='discount_amount']");
			Common.clickElement("xpath", "//input[@name='discount_amount']");
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
		String website = data.get(dataSet).get("website");
		System.out.println(website);

		String[] websites = website.split(",");

		for (int i = 0; i < websites.length; i++) {
			System.out.println(websites[i]);
			Sync.waitElementClickable("xpath", "//option[text()='" + websites[i] + "']");

			Common.clickElement("xpath", "//option[text()='" + websites[i] + "']");

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

			Sync.waitElementPresent("xpath", "//option[text()='Hydroflask Website']");
			Common.scrollIntoView("xpath", "//option[text()='Hydroflask Website']");

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

			// Click_AddNewRule();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the new cart price rule page is displayed",
					"should display the New cart price rules page after clicking on the Add New rule",
					"unable to display New cart price rules after a click on the Add new rule",
					"Failed to display New cart price rules page");
			Assert.fail();
		}

	}

	public void Payment_method(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);

			Sync.waitElementPresent("xpath", "//input[@title='Default Payment']");
			Common.clickElement("xpath", "//input[@title='Default Payment']");
			Thread.sleep(3000);

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

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the sucess message is displayed",
					"should display the sucess message after clicking on the sudmitorder",
					"unable to display sucess message after a click on the sudmitorder button",
					"Failed to display sucess message");
			Assert.fail();
		}

	}

	public void Default_Payment_method(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
			Common.scrollIntoView("xpath", "//input[@id='p_method_stripe_payments']");
			Sync.waitElementPresent("xpath", "//input[@id='p_method_stripe_payments']");
			Common.clickElement("xpath", "//input[@id='p_method_stripe_payments']");
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

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the sucess message is displayed",
					"should display the sucess message after clicking on the sudmitorder",
					"unable to display sucess message after a click on the sudmitorder button",
					"Failed to display sucess message");
			Assert.fail();
		}

	}

	public void Delet_existing_coupon(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//input[@name='coupon_code']");
			Common.clickElement("xpath", "//input[@name='coupon_code']");
			Common.textBoxInput("xpath", "//input[@name='coupon_code']", data.get(dataSet).get("Coupon Code"));
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(2000);

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
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the delete message is displayed",
					"should display the delete message page after clicking on the delete button",
					"delete message  page is displayed after a click on the delete button",
					Common.getscreenShotPathforReport("Failed to display delete message "));
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
//					Tell_Your_FriendPop_Up();

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

	public void discountCode(String dataSet) throws Exception {
		String expectedResult = "It should opens textbox input.";

		try {

			Sync.waitElementClickable("id", "block-discount-heading");
			Common.clickElement("id", "block-discount-heading");

			Sync.waitElementPresent("id", "discount-code");

			Common.textBoxInput("id", "discount-code", data.get(dataSet).get("Coupon Code"));

			int size = Common.findElements("id", "discount-code").size();
			Common.assertionCheckwithReport(size > 0, "verifying the Discount Code label", expectedResult,
					"Successfully open the discount input box", "User unable enter Discount Code");
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
			String Subtotal = Common.getText("xpath", "//tr[@class='totals sub']//span[@class='price']").replace("$",
					"");
			Float subtotalvalue = Float.parseFloat(Subtotal);
			String shipping = Common.getText("xpath", "//tr[@class='totals shipping excl']//span[@class='price']")
					.replace("$", "");
			Float shippingvalue = Float.parseFloat(shipping);
			String Tax = Common.getText("xpath", "//tr[@class='totals-tax']//span[@class='price']").replace("$", "");
			Float Taxvalue = Float.parseFloat(Tax);
			Thread.sleep(4000);
			String Discount = Common.getText("xpath", "//tr[@class='totals discount']//span[@class='price']")
					.replace("$", "");
			Float Discountvalue = Float.parseFloat(Discount);
			System.out.println(Discountvalue);

			String ordertotal = Common.getText("xpath", "//tr[@class='grand totals']//span[@class='price']")
					.replace("$", "");
			Float ordertotalvalue = Float.parseFloat(ordertotal);
			Float Total = (subtotalvalue + shippingvalue + Taxvalue) + Discountvalue;
			System.out.println(Total);
			Thread.sleep(4000);
			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			System.out.println(ExpectedTotalAmmount2);
			System.out.println(ordertotal);
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

	public void addDeliveryAddress_Guestuser(String dataSet) throws Exception {
		String address = data.get(dataSet).get("Street");

		try {
			Thread.sleep(5000);
			Sync.waitElementVisible("id", "username");
			Common.textBoxInput("id", "username", data.get(dataSet).get("Email"));

			Thread.sleep(3000);
			String expectedResult = "email field will have email address";

			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));
			int size = Common.findElements("id", "customer-email").size();
//			Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,
//					"Filled Email address", "unable to fill the email address");
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Common.clickElement("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']", address);
			Common.actionsKeyPress(Keys.BACK_SPACE);
			Common.actionsKeyPress(Keys.BACK_SPACE);
			Thread.sleep(5000);
			Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']").sendKeys("Rd");
			Thread.sleep(5000);
			Sync.waitElementPresent(30, "xpath", "//div[@class='pcaitem']");
			String newadd = Common.findElement("xpath", "//div[@class='pcaitem']").getAttribute("title");
			System.out.println(newadd);
			Thread.sleep(6000);
			List<WebElement> listofaddresss = Common.findElements("xpath", "(//div[@class='pcaitem'])[1]");
			ArrayList<WebElement> listaddress = new ArrayList<WebElement>();
			for (WebElement addresscheck : listofaddresss) {

				listaddress.add(addresscheck);
				String add = addresscheck.getAttribute("title");
				System.out.println(add);
				System.out.println(address);
				Thread.sleep(4000);
				Common.assertionCheckwithReport(add.equals(address),
						"validating the dropdown in the shipping address page ",
						"User should able to see the Locate dropdown for respective address",
						"Successfully Locate dropdown has been displayed in shipping address page",
						"Failed to display dropdown in the shipping address page");

			}
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

		}
	}

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
			// Common.scrollIntoView("xpath", "//img[@alt='9-Cup Coffee Maker']");
			Common.scrollIntoView("xpath", "//img[@alt='" + product + "']");
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product + "']");
			Common.clickElement("xpath", "//img[@alt='" + product + "']");
			// Common.clickElement("xpath", "//img[@alt='9-Cup Coffee Maker']");
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

	public void coffee_headerlinks(String category) {
		// TODO Auto-generated method stub
		String expectedResult = "User should click the" + category;
		try {

			Sync.waitElementClickable("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");
			Thread.sleep(3000);
			Common.mouseOverClick("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");
			Thread.sleep(3000);

			try {
				Common.mouseOver("xpath", "//span[contains(text(),'" + category + "')]");
			} catch (Exception e) {
				Common.clickElement("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");
			}
			Common.clickElement("xpath", "//span[contains(text(),'" + category + "')]");
//				Common.clickElement("xpath", "//span[text()='Shop All']");
			Common.clickElement("xpath", "//a[contains(@aria-label,'Shop All   Coffee & B')]");
			expectedResult = "User should select the " + category + "category";
			int sizebotteles = Common.findElements("xpath", "//span[contains(text(),'" + category + "')]").size();
			Common.assertionCheckwithReport(sizebotteles > 0,
					"validating the product category as" + category + "from navigation menu ", expectedResult,
					"Selected the " + category + " category", "User unabel to click" + category + "");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the product category as" + category + "from navigation menu ",
					expectedResult, "Unable to Selected the " + category + " category",
					Common.getscreenShot("Failed to click on the" + category + ""));

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
			Common.textBoxInput("xpath", "//input[@id='search']", data.get(Dataset).get("Products"));
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

	public void Click_AddNewRule(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//input[@name='coupon_code']");
			Common.clickElement("xpath", "//input[@name='coupon_code']");
			Common.textBoxInput("xpath", "//input[@name='coupon_code']", data.get(dataSet).get("Coupon Code"));
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(2000);

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

	public void click_minicart() {
		try {
			Thread.sleep(8000);
			Common.actionsKeyPress(Keys.ARROW_UP);
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

	public void Flush_Magneto_cache(String Dataset) {
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

	public void AddProduct_Group_Product(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//span[text()='Add Products']");
			Common.textBoxInput("xpath", "//input[@id='sales_order_create_search_grid_filter_sku']",
					data.get(dataSet).get("SKUNumber"));
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//a[@class='action-configure ']");
			Common.clickElement("xpath", "//a[@class='action-configure ']");
			Thread.sleep(2000);
			String pagetitle = Common.findElement("xpath", "//h1[contains(text(),'Configure Product')]").getText();
			System.out.println(pagetitle);
			Common.assertionCheckwithReport(pagetitle.contains("Configure Product"),
					"To validate Product configure Tab is opened when we click on the configure link",
					"Product Configure tab should open when we click on the Configure link",
					"Product Configure tab is opened when we click on the configure link",
					"Failed to opne the product configure tab when we click on the Configure link");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate Product configure Tab is opened when we click on the configure link",
					"Product Configure tab should open when we click on the Configure link",
					"Product Configure tab is not opened when we click on the configure link",
					"failed to open the product configure Tab when we click on the configure link");
			Assert.fail();
		}

	}

	public void AddProduct_Configure_child_items(String dataSet) {
		// TODO Auto-generated method stub
		String[] product_quantity = data.get(dataSet).get("quantity").split(",");
		try {
			Thread.sleep(2000);
			Common.clickElement("xpath", "//span[text()='Add Selected Product(s) to Order']");
			int productcount = Common.findElements("xpath", "//td[@class='col-name']").size();

			for (int i = 1; i <= productcount; i++) {
				Sync.waitElementClickable("xpath", "(//input[contains(@name,'super_group')])[" + i + "]");
				Common.textBoxInput("xpath", "(//input[contains(@name,'super_group')])[" + i + "]",
						product_quantity[i - 1]);
			}
			Sync.waitElementVisible("xpath", "//button[@class='action-primary']");
			Common.javascriptclickElement("xpath", "//button[@class='action-primary']");
			Sync.waitPageLoad();
			Common.scrollIntoView("xpath", "//span[text()='Add Selected Product(s) to Order']");
			Common.javascriptclickElement("xpath", "//span[text()='Add Selected Product(s) to Order']");

			Sync.waitPageLoad();
			int noofproducts = Common.findElements("xpath", "//td[@class='col-product']").size();
			Common.assertionCheckwithReport(noofproducts > 1,
					"To Validate the selected group product child items added with sku,productname ,qty",
					"should add the selected group product child items with sku,productname ,qty",
					"Selected group product child items added successfuly with sku,productname ,qty",
					"Failed to add group product child items with sku,productname ,qty");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To Validate the selected group product child items added with sku,productname ,qty",
					"should add the selected group product child items with sku,productname ,qty",
					"unable to add the selected group product child items with sku,productname ,qty",
					"Failed to add group product child items with sku,productname ,qty");
			Assert.fail();
		}

	}

	public void AddProduct_By_SKU_Group_Product(String dataSet) {
		// TODO Auto-generated method stub
		String Noticemessage = data.get(dataSet).get("message");
		try {
			Sync.waitElementPresent("xpath", "//div[@class='actions']//span[text()='Add Products By SKU']");
			Common.clickElement("xpath", "//div[@class='actions']//span[text()='Add Products By SKU']");
			Thread.sleep(2000);

			Sync.waitElementPresent("xpath", "//td[@class='col-sku']//input[@name='sku']");
			Common.textBoxInput("xpath", "//td[@class='col-sku']//input[@name='sku']",
					data.get(dataSet).get("SKUNumber"));

			Common.textBoxInput("xpath", "//div[@class='input-box']//input[@name='qty']",
					data.get(dataSet).get("Quantity"));

			Sync.waitElementPresent("xpath", "//button[@title='Add to Order']");
			Common.clickElement("xpath", "//button[@title='Add to Order']");
			Sync.waitPageLoad();
			String noticemessage = Common.findElement("xpath", "//div[@class='message message-notice']").getText();
			Common.assertionCheckwithReport(noticemessage.equals(Noticemessage),
					"To Validate the group product item sku is added and display a notice message to configure child products",
					"should add group product item sku and displays a notice message to configure child products",
					"group product item sku is added successfully and display a notice message to configure child products",
					"Failed to add group product item sku and not dispalying notice message to configure child products");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To Validate the group product item sku is added and display a notice message to configure child products",
					"To Validate the group product item sku is added and display a notice message to configure child products ",
					"unable to add group product item sku and not displays a notice message to configure child products",
					"Failed to add group product item sku and not dispalying notice message to configure child products");
			Assert.fail();
		}

	}

	public void AddProduct_By_SKU_Configure_child_Products(String dataSet) {
		// TODO Auto-generated method stub
		String[] product_quantity = data.get(dataSet).get("quantity").split(",");

		try {
			Common.clickElement("xpath", "//span[text()='Configure']");
			int productcount = Common.findElements("xpath", "//td[@class='col-name']").size();

			for (int i = 1; i <= productcount; i++) {
				Sync.waitElementClickable("xpath", "(//input[contains(@name,'super_group')])[" + i + "]");
				Common.textBoxInput("xpath", "(//input[contains(@name,'super_group')])[" + i + "]",
						product_quantity[i - 1]);
				Common.scrollIntoView("xpath", "(//input[contains(@name,'super_group')])[" + i + "]");

			}
			Common.scrollIntoView("xpath", "//button[@class='action-primary']");
			Common.javascriptclickElement("xpath", "//button[@class='action-primary']");

			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//table[@id='sku_errors_table']");
			int noticemessage = Common.findElements("xpath", "//div[@class='message message-notice']").size();
			System.out.println(noticemessage);
			Common.assertionCheckwithReport(noticemessage <= 0,
					"To Validate the selected group product child items added with sku,productname ,qty",
					"should add the selected group product child items with sku,productname ,qty",
					"Selected group product child items added successfuly with sku,productname ,qty",
					"Failed to add group product child items with sku,productname ,qty");
			Common.javascriptclickElement("xpath", "//button[@title='Add Products to Order']");
			Common.getscreenShotPathforReport("Adding Products to Order");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To Validate the selected group product child items added with sku,productname ,qty",
					"should add the selected group product child items with sku,productname ,qty",
					"unable to add the selected group product child items with sku,productname ,qty",
					"Failed to add group product child items with sku,productname ,qty");
			Assert.fail();
		}

	}

	public void Search_previous_page_Magento(String Dataset) {
		// TODO Auto-generated method stub
		String title = data.get(Dataset).get("pageTitle");
		try {
//			click_content();
//			pages();
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//button[@data-action='grid-filter-expand']");
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			Common.textBoxInput("xpath", "//input[@name='title']", title);
			Common.clickElement("xpath", "//button[@class='action-secondary']");
			String name = Common.findElement("xpath", "(//div[@class='data-grid-cell-content'])[2]").getText();
			Common.assertionCheckwithReport(name.equals(title), "Validating the title name should be match",
					"title name should be appear in the pages", "Sucessfully title should be appear in the pages",
					"Unable to see the title in the page");
			Common.clickElement("xpath", "//tr//button[@class='action-select']");
			Common.clickElement("xpath", "//a[text()='Edit']");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			String name1 = Common.findElement("xpath", "//h1[@class='page-title']").getText();
			Common.assertionCheckwithReport(name1.equals(title), "Validating the page navigation",
					"title name should be appear in the pages", "Sucessfully title  appear in the pages",
					"Unable to see the title in the page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the page navigation",
					"title name should be appear in the pages", "Unable to see the title in the page",
					"Failed to see the title in the page");
			Assert.fail();
		}
	}

	public void Add_simpleproduct_SKU(String dataSet) {
		// TODO Auto-generated method stub
		String simpleproduct = data.get(dataSet).get("SKUNumberoxosimple");
		System.out.println(simpleproduct);
		try {
			Sync.waitElementPresent("xpath", "//div[@class='actions']//span[text()='Add Products By SKU']");
			Common.clickElement("xpath", "//div[@class='actions']//span[text()='Add Products By SKU']");

			Sync.waitElementPresent("xpath", "//td[@class='col-sku']//input[@name='sku']");
			Common.textBoxInput("xpath", "//td[@class='col-sku']//input[@name='sku']",
					data.get(dataSet).get("SKUNumberoxosimple"));

			Common.textBoxInput("xpath", "//div[@class='input-box']//input[@name='qty']",
					data.get(dataSet).get("oxoQuantity"));

			Sync.waitElementPresent("xpath", "//button[@title='Add to Order']");
			Common.clickElement("xpath", "//button[@title='Add to Order']");

			String searchsimpleproduct = Common.findElement("xpath", "(//div[@class='product-sku-block'])").getText();
			System.out.println(searchsimpleproduct);
			Common.assertionCheckwithReport(searchsimpleproduct.contains(simpleproduct),
					"To validate the products are added to order", "products should be added to order",
					"products is added to order", "failed to add products to order");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the products are added to order",
					"products should be added to order", "products is added to order",
					"failed to add products to order");
			Assert.fail();
		}
	}

	public void Add_configurableproduct_SKU(String dataSet) {
		// TODO Auto-generated method stub
		String configurableproduct = data.get(dataSet).get("SKUNumberoxoconfigurable");
		System.out.println(configurableproduct);
		String skunum = data.get(dataSet).get("SKUnumb");
		try {
			Sync.waitElementPresent("xpath", "//div[@class='actions']//span[text()='Add Products By SKU']");
			Common.clickElement("xpath", "//div[@class='actions']//span[text()='Add Products By SKU']");

			Sync.waitElementPresent("xpath", "//td[@class='col-sku']//input[@name='sku']");
			Common.textBoxInput("xpath", "//td[@class='col-sku']//input[@name='sku']",
					data.get(dataSet).get("SKUNumberoxoconfigurable"));

			Common.textBoxInput("xpath", "//div[@class='input-box']//input[@name='qty']",
					data.get(dataSet).get("oxoQuantity"));

			Sync.waitElementPresent("xpath", "//button[@title='Add to Order']");
			Common.clickElement("xpath", "//button[@title='Add to Order']");

			Common.actionsKeyPress(Keys.PAGE_DOWN);
			// Common.findElement("xpath", "//div[@id='sku_errors']");
			Common.scrollIntoView("xpath", "//div[@id='sku_errors']");

			Sync.waitElementPresent("xpath", "(//button[@title='Configure'])");
			Common.clickElement("xpath", "(//button[@title='Configure'])");
			Sync.waitPageLoad();
			Thread.sleep(2000);

			// Sync.waitElementPresent("xpath", "//div[@class='control admin__field-control
			// last']");
			Common.clickElement("xpath", "//select[contains(@name,'super_attribute')]");
			Thread.sleep(2000);
			Common.dropdown("xpath", "//select[contains(@name,'super_attribute')]", Common.SelectBy.TEXT,
					data.get(dataSet).get("Choosecolor"));
			Common.clickElement("xpath", "//button[@data-role='action']");

			// Common.clickElement("xpath", "//button[@data-role='action']");
			Sync.waitElementPresent("xpath", "//span[text()='Add Products to Order']");
			Common.clickElement("xpath", "//span[text()='Add Products to Order']");
			Thread.sleep(4000);

			Common.actionsKeyPress(Keys.PAGE_UP);
			Thread.sleep(3000);
			Common.scrollIntoView("xpath", "(//div[@class='product-sku-block'])[2]");
			String searchconfigproduct = Common.findElement("xpath", "(//div[@class='product-sku-block'])[2]")
					.getText();
			System.out.println(searchconfigproduct);
			Common.assertionCheckwithReport(searchconfigproduct.contains(skunum),
					"To validate the products are added to order", "products should be added to order",
					"products is added to order", "failed to add products to order");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the products are added to order",
					"products should be added to order", "products is added to order",
					"failed to add products to order");
			Assert.fail();
		}
	}

	public void Add_bundleproduct_SKU(String dataSet) {
		// TODO Auto-generated method stub
		String bundleproduct = data.get(dataSet).get("SKUNumberoxobundle");
		System.out.println(bundleproduct);
		try {
			Sync.waitElementPresent("xpath", "//div[@class='actions']//span[text()='Add Products By SKU']");
			Common.clickElement("xpath", "//div[@class='actions']//span[text()='Add Products By SKU']");

			Sync.waitElementPresent("xpath", "//td[@class='col-sku']//input[@name='sku']");
			Common.textBoxInput("xpath", "//td[@class='col-sku']//input[@name='sku']",
					data.get(dataSet).get("SKUNumberoxobundle"));

			Common.textBoxInput("xpath", "//div[@class='input-box']//input[@name='qty']",
					data.get(dataSet).get("oxoQuantity"));

			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//button[@title='Add to Order']");
			Common.clickElement("xpath", "//button[@title='Add to Order']");
			Thread.sleep(6000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			// Common.findElement("xpath", "//div[@id='sku_errors']");
			Common.scrollIntoView("xpath", "//div[@id='sku_errors']");
			Sync.waitElementPresent("xpath", "//div[@id='sku_errors']");

			Common.findElement("xpath", "(//button[@title='Configure'])[2]");
			Common.clickElement("xpath", "(//button[@title='Configure'])[2]");
			Sync.waitPageLoad();
			Thread.sleep(2000);

			Common.clickElement("xpath", "//button[@data-role='action']");

			Sync.waitElementPresent("xpath", "//span[text()='Add Products to Order']");
			Common.clickElement("xpath", "//span[text()='Add Products to Order']");
			Thread.sleep(4000);

			Common.actionsKeyPress(Keys.PAGE_UP);
			Thread.sleep(3000);
			Common.scrollIntoView("xpath", "(//div[@class='product-sku-block'])[3]");
			String searchbundleproduct = Common.findElement("xpath", "(//div[@class='product-sku-block'])[3]")
					.getText();
			System.out.println(searchbundleproduct);
			Common.assertionCheckwithReport(searchbundleproduct.contains(bundleproduct),
					"To validate the products are added to order", "products should be added to order",
					"products is added to order", "failed to add products to order");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the products are added to order",
					"products should be added to order", "products is added to order",
					"failed to add products to order");
			Assert.fail();
		}
	}

	public String update_customprice(String dataSet) throws Exception {
		String updatedhighprice = "";
		String symbol= data.get(dataSet).get("Symbol");
		
		try {

			Thread.sleep(3000);

			Sync.waitElementPresent("xpath", "//span[text()='Custom Price*']");

			int customprices = Common.findElements("xpath", "//span[text()='Custom Price*']").size();

			for (int i =1; i <= customprices; i++) {
				String newhighprice[] = data.get(dataSet).get("lowprice").split(",");
				Float New_Price = Float.valueOf(newhighprice[i - 1]);
				System.out.println(New_Price);
				Common.clickElement("xpath", "(//input[contains(@id,'use_custom_price')])[" + i + "]");

				Common.textBoxInput("xpath", "(//input[contains(@name,'custom_price')])[" + i + "]",
						newhighprice[i - 1]);

				Common.clickElement("xpath", "//span[text()='Update Items and Quantities']");
				Thread.sleep(5000);
				Sync.waitElementVisible("xpath", "(//td[@class='col-price']/span)[" + i + "]");

				updatedhighprice = Common.findElement("xpath", "(//td[@class='col-price']/span)[" + i + "]").getText()
						.replace(symbol, "");
				Float updated_highprice = Float.valueOf(updatedhighprice);
				System.out.println(updated_highprice);
				Thread.sleep(3000);
				Common.assertionCheckwithReport(updated_highprice.equals(New_Price),
						"To Validate the custom price override with High Value",
						"The original price should be Override to higher value",
						"The original price is Overrided to higher value", "Failed to override the custom price");
			
			
			
			
			}
	
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the custom price override with High Value",
					"The original price should be Override to higher value",
					"The original price is not Overrided to higher value", "Failed to override the custom price");
			Assert.fail();

		}
		return updatedhighprice;

	}

	public HashMap<String, String> validate_updated_Highpricevalue_in_Order(HashMap<String, String> updatedprice) throws InterruptedException {

		HashMap<String, String> CustomPrice=new HashMap();
		String NewPrice= new String();
		try {
			Thread.sleep(3000);
		/*int Length=updatedprice.size();
		    String Lengthprice=Integer.toString(Length);
			int size= Common.findElements("xpath", "//td[@class='col-price']//div[@class='price-excl-tax'][1]").size();
            String Size=Integer.toString(size);
           
           if(Lengthprice.equals(Size))
            {*/
        	  int size= Common.findElements("xpath", "//td[@class='col-price']//div[@class='price-excl-tax'][1]").size();
             for(int i=0;i<size;i++)
             {
            	 Sync.waitElementPresent("xpath", "(//td[@class='col-price']//div[@class='price-excl-tax']//span)[" + (i + 1) + "]");
            	 String updatedprice1 = Common.findElement("xpath", "(//td[@class='col-price']//div[@class='price-excl-tax']//span)[" + (i + 1) + "]").getText()
     					.replace("$", "");
		
			Float updated_price1 = Float.valueOf(updatedprice1);
			System.out.println(updated_price1);
			Thread.sleep(3000);
			NewPrice=NewPrice.concat(",").trim();
			NewPrice=NewPrice.concat(updatedprice1);
			System.out.println(" " +NewPrice);
			CustomPrice.put("Updated Price", NewPrice);
             }
            // }
             System.out.println(updatedprice);
             System.out.println(CustomPrice);
           
			if (updatedprice.equals(CustomPrice)) {
				System.out.println("The price is updated");
			} else {
				System.out.println("The price is not updated");
			}
			Common.assertionCheckwithReport(updatedprice.equals(CustomPrice),
					"Comparing whether the price is updated or not", "The price should be updated",
					"The price is not updated", "Failed to updated the price");
			

		} 
		catch (Exception e) {

			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the price is updated", "The price should be updated",
					"The price is not updated", "Failed to updated the price");
		}
		
		return CustomPrice;
	}

	public String update_customprice_withLowprice(String dataSet) throws Exception {
		String updatedprice = "";
		try {

			Thread.sleep(3000);

			Sync.waitElementPresent("xpath", "(//span[text()='Custom Price*'])[1]");
			Common.clickElement("xpath", "(//span[text()='Custom Price*'])[1]");
			Thread.sleep(2000);
			Common.textBoxInput("xpath", "(//input[@class='input-text item-price admin__control-text'])[1]",
					data.get(dataSet).get("lowprice"));
			Thread.sleep(3000);
			/*
			 * Sync.waitElementPresent("xpath", "(//span[text()='Custom Price*'])[2]");
			 * Common.clickElement("xpath", "(//span[text()='Custom Price*'])[2]");
			 * Thread.sleep(3000); Common.textBoxInput("xpath",
			 * "(//input[@class='input-text item-price admin__control-text'])[2]",
			 * data.get(dataSet).get("highprice")); Thread.sleep(3000);
			 * Sync.waitElementPresent("xpath", "(//span[text()='Custom Price*'])[3]");
			 * Common.clickElement("xpath", "(//span[text()='Custom Price*'])[3]");
			 * Thread.sleep(3000); Common.textBoxInput("xpath",
			 * "(//input[@class='input-text item-price admin__control-text'])[3]",
			 * data.get(dataSet).get("highprice"));
			 */
			Thread.sleep(3000);
			Common.clickElement("xpath", "//span[contains(text(),'Update Items and Quantities')]");
			Thread.sleep(3000);
			updatedprice = Common.getText("xpath", "(//span[contains(text(),'$12.95')])[1]").replace("$", "");
			Float updated_price = Float.valueOf(updatedprice);
			System.out.println(updated_price);
			Thread.sleep(3000);

			Common.assertionCheckwithReport(updatedprice.contains("12.95"),
					"To Validate the custom price override with lowvalue", "should able to update the Custom price",
					"Successfully price updated", "passed to update custom price");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the update the price of product",
					"sku price should be updated", "update the sku price", "failed to update the price");
			Assert.fail();

		}
		return updatedprice;

	}

	public void validate_updated_Lowpricevalue_in_Order(String updatedprice) throws InterruptedException {

		try {
			Thread.sleep(3000);

			String updatedprice1 = Common.findElement("xpath", "(//span[contains(text(),'$12.95')])[1]").getText()
					.replace("$", "");
			Float updated_price1 = Float.valueOf(updatedprice1);
			System.out.println(updated_price1);
			Thread.sleep(3000);
			if (updatedprice == updatedprice1) {
				System.out.println("The price is updated");
			} else {
				System.out.println("The price is not updated");
			}
			Common.assertionCheckwithReport(updatedprice.equals(updatedprice1),
					"To validate  the price is updated or not", "The price should be updated",
					"The price is not updated", "Failed to updated the price");

		} catch (Exception e) {

			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validate the price is updated or not", "The price should be updated",
					"The price is not updated", "Failed to updated the price");

			Assert.fail();
		}

	}

	public void validate_Guestuser_shippingaddress(String dataSet) {
		// TODO Auto-generated method stub

		String address = data.get(dataSet).get("streetaddress");
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
			Common.textBoxInput("xpath", "//input[@id='order-billing_address_street0']", address);
			Thread.sleep(4000);
			Common.actionsKeyPress(Keys.BACK_SPACE);
			Common.actionsKeyPress(Keys.BACK_SPACE);
			Thread.sleep(5000);
			Common.findElement("xpath", "//input[@id='order-billing_address_street0']").sendKeys("Rd");
			Thread.sleep(5000);

			List<WebElement> listofaddresss = Common.findElements("xpath", "(//div[@class='pcaitem'])[1]");
			ArrayList<WebElement> listaddress = new ArrayList<WebElement>();
			for (WebElement addresscheck : listofaddresss) {

				listaddress.add(addresscheck);
				Thread.sleep(4000);
				String add = addresscheck.getAttribute("title");
				System.out.println(add);
				System.out.println(address);
				Thread.sleep(4000);
				Common.assertionCheckwithReport(add.equals(address),
						"validating the dropdown in the shipping address page ",
						"User should able to see the Locate dropdown for respective address",
						"Successfully Locate dropdown has been displayed in shipping address page",
						"Failed to display dropdown in the shipping address page");

				Sync.waitElementPresent("xpath", "//select[@id='order-billing_address_country_id']");
				Common.dropdown("xpath", "//select[@id='order-billing_address_country_id']", Common.SelectBy.TEXT,
						data.get(dataSet).get("Country"));

				Sync.waitElementPresent("xpath", "//select[@id='order-billing_address_region_id']");
				Common.dropdown("xpath", "//select[@id='order-billing_address_region_id']", Common.SelectBy.TEXT,
						data.get(dataSet).get("State"));

				Sync.waitElementPresent("xpath", "//input[@id='order-billing_address_city']");
				Common.textBoxInput("xpath", "//input[@id='order-billing_address_city']",
						data.get(dataSet).get("City"));

				Sync.waitElementPresent("xpath", "//input[@id='order-billing_address_postcode']");
				Common.textBoxInput("xpath", "//input[@id='order-billing_address_postcode']",
						data.get(dataSet).get("Postcode"));

				Sync.waitElementPresent("xpath", "//input[@id='order-billing_address_telephone']");
				Common.textBoxInput("xpath", "//input[@id='order-billing_address_telephone']",
						data.get(dataSet).get("Phonenumber"));
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//div[@id='order-shipping-method-summary']/a");

				Common.doubleClick("xpath", "//span[contains(text(), 'Get shipping methods and rates')]");
				Thread.sleep(2000);

				Thread.sleep(8000);
				Common.clickElement("xpath", "//input[@id='s_method_tablerate_bestway']");

				Sync.waitPageLoad();
				// Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and
				// @style='display: none;']");
				String text = Common.findElement("xpath", "//span[text()='Click to change shipping method']").getText();
				System.out.println(text);
				Common.assertionCheckwithReport(text.contains("Click to change shipping method"),
						"To Validate the displayed click to change shipping method",
						"should display the click to change shipping method after clicking on the standard shipping",
						"click to change shipping method is displayed after a click on the standard shipping",
						"Failed to display click to change shipping method");

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the displayed click to change shipping method",
					"should display the click to change shipping method after clicking on the standard shipping",
					"unable to display click to change shipping method after a click on the standard shipping",
					"Failed to display click to change shipping method");
			Assert.fail();
		}
	}

	public void Update_StoreCredit_ExistingCustomer(String DataSet) throws Exception {

		String Symbol = data.get(DataSet).get("Symbol");
		System.out.println(Symbol);
		Thread.sleep(6000);
		String grandtotal = Common.findElement("xpath", "//td[@class='admin__total-amount']/strong").getText()
				.replace(Symbol, "");
		System.out.println("Website" + grandtotal);
		float GrandTotal = Float.parseFloat(grandtotal);

		try {
			String URL = Common.findElement("xpath", "//li[contains(@class,'customer-manage')]/a").getAttribute("href");
			System.out.println(URL);

			Common.openNewTab();
			Common.oppenURL(URL);
			Sync.waitPageLoad();
			Sync.waitElementClickable("xpath", "//h1[@class='page-title']");
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Customers / Customers / Magento Admin"),
					"To validate the customer is landing on the all customers page",
					"Admin should land on the All customers page", "Admin landed on the All customers page",
					"Admin failed to land on the All customers page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the customer is landing on the all customers page",
					"Admin should land on the All customers page", "Admin not landed on the All customers page",
					"Admin failed to land on the All customers page");
			Assert.fail();
		}

		SelectCustomer_Edit(DataSet);

		try {

			Sync.waitElementPresent("xpath", "//span[text()='Store Credit']");
			Common.clickElement("xpath", "//span[text()='Store Credit']");
			Thread.sleep(4000);
			// Sync.waitElementPresent("xpath",
			// "//div[@data-area-active='true']//span[text()='Store Credit Balance']");

			Thread.sleep(10000);
			Sync.waitElementPresent("xpath", "(//td[@class=' col-amount a-left  '])[2]");

			String defaultstorecredit = Common.findElement("xpath", "(//td[@class=' col-amount a-left  '])[2]")
					.getText().replace(Symbol, "");

			float Defaultstoreblnce = Float.parseFloat(defaultstorecredit);
			System.out.println("saved store credit :" + Defaultstoreblnce);

			if (Defaultstoreblnce == 0.00) {
				Common.scrollIntoView("xpath", "//input[@id='_customerbalanceamount_delta']");
				Common.textBoxInput("xpath", "//input[@id='_customerbalanceamount_delta']", grandtotal);
				Thread.sleep(3000);
				Common.scrollIntoView("xpath", "//span[text()='Save Customer']");
				Common.javascriptclickElement("xpath", "//span[text()='Save Customer']");
				Sync.waitPageLoad();
				Sync.waitElementVisible("xpath", "//div[contains(@class,'success')]");
				String successmessage = Common.findElement("xpath", "//div[contains(@class,'success')]/div").getText();
				Common.assertionCheckwithReport(
						successmessage.contains("You saved the customer.")
								&& Common.getPageTitle().contains("Customers / Customers"),
						"To validate the Store credit balance is updated and the customer is landing on the All Customers page",
						"Store credit balance should be updated and it should land on the All Customers page",
						"Store credit balance is updated and it is landing on the All Customers page",
						"Failed to land on the All customers page");

			} else if (Defaultstoreblnce > GrandTotal) {
				Float updateprice = GrandTotal - Defaultstoreblnce;
				String Minusbalance = Float.toString(updateprice);
				Common.textBoxInput("id", "_customerbalanceamount_delta", Minusbalance);
				Common.scrollIntoView("xpath", "//span[text()='Save Customer']");
				Common.javascriptclickElement("xpath", "//span[text()='Save Customer']");
				Sync.waitPageLoad();
				Sync.waitElementVisible("xpath", "//div[contains(@class,'success')]");
				String successmessage = Common.findElement("xpath", "//div[contains(@class,'success')]/div").getText();
				Common.assertionCheckwithReport(
						successmessage.contains("You saved the customer.")
								&& Common.getPageTitle().contains("Customers / Customers"),
						"To validate the Store credit balance is updated and the customer is landing on the All Customers page",
						"Store credit balance should be updated and it should land on the All Customers page",
						"Store credit balance is updated and it is landing on the All Customers page",
						"Failed to land on the All customers page");

			} else if (Defaultstoreblnce < GrandTotal) {
				Float updateprice = GrandTotal + Defaultstoreblnce;
				String additionalbalance = Float.toString(updateprice);
				Common.textBoxInput("id", "_customerbalanceamount_delta", additionalbalance);

				Common.scrollIntoView("xpath", "//span[text()='Save Customer']");
				Common.javascriptclickElement("xpath", "//span[text()='Save Customer']");
				Sync.waitPageLoad();
				Sync.waitElementVisible("xpath", "//div[contains(@class,'success')]/div");
				String successmessage = Common.findElement("xpath", "//div[contains(@class,'success')]/div").getText();
				Common.assertionCheckwithReport(
						successmessage.contains("You saved the customer.")
								&& Common.getPageTitle().contains("Customers / Customers"),
						"To validate the Store credit balance is updated and the customer is landing on the All Customers page",
						"Store credit balance should be updated and it should land on the All Customers page",
						"Store credit balance is updated and it is landing on the All Customers page",
						"Failed to land on the All customers page");

			} else if (Defaultstoreblnce == GrandTotal) {
				System.out.println("The default store crdeit is avilable");
				Sync.waitElementPresent("xpath", "//span[text()='Back']");
				Common.scrollIntoView("xpath", "//span[text()='Back']");
				Common.javascriptclickElement("xpath", "//span[text()='Back']");
				Sync.waitPageLoad();
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Store credit is reflected on the Admin order page",
					"Store credit should be displayed on the Create New order page",
					"Store credit is not displayed on the Create New order page",
					"Store credit failed to displayed on the Create New order page");
			Assert.fail();
		}

	}

	public void Select_ExistingUser_email(String dataSet) {
		// TODO Auto-generated method stub

		String storeview = data.get(dataSet).get("Store");
		String email = data.get(dataSet).get("Email");
		String website = data.get(dataSet).get("Website");
		try {
			Common.textBoxInput("xpath", "//input[@name='email']", email);
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(2000);
			Sync.waitElementVisible("xpath", "//td[text()='" + email + "']");
			int numofemails = Common.findElements("xpath", "//td[text()='" + email + "']").size();

			if (numofemails > 0) {
				Common.findElement("xpath", "//td[text()='" + storeview + "']");
				Common.clickElement("xpath", "//td[text()='" + storeview + "']");
			} else {
				Assert.fail();
			}
			Thread.sleep(3000);

			Sync.waitPageLoad();

			Sync.waitElementVisible("id", "order-header");
			String page = Common.findElement("id", "order-header").getText();

			Common.assertionCheckwithReport(page.contains("Create New Order for"),
					"To validate the customer is on the select store page", "Select store page should be displayed",
					"Select store page is displayed", "Failed to display Select store page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the customer is on the select store page",
					"Select store page should be displayed", "Select store page is not displayed",
					"Failed to display select store page");
			Assert.fail();
		}

	}

	public HashMap<String, String> update_customprice_withhighprice(String dataSet) throws Exception {
		String updatedprice = "";
		String[] high = data.get(dataSet).get("highprice").split(",");
		HashMap<String, String> CustomPrice=new HashMap();
		String prices=new String();
		try {

		for (int i = 0; i < high.length; i++)   //for(String lenght:high)
		{
			System.out.println(high[i]);

			Thread.sleep(3000);

			Sync.waitElementPresent("xpath", "(//span[text()='Custom Price*'])[" + (i + 1) + "]");
			Common.clickElement("xpath", "(//span[text()='Custom Price*'])[" + (i + 1) + "]");
			Thread.sleep(2000);
			Common.textBoxInput("xpath", "(//input[@class='input-text item-price admin__control-text'])[" + (i + 1) + "]",
					high[i]);
			Thread.sleep(3000);
			/*
			 * Sync.waitElementPresent("xpath", "(//span[text()='Custom Price*'])[2]");
			 * Common.clickElement("xpath", "(//span[text()='Custom Price*'])[2]");
			 * Thread.sleep(3000); Common.textBoxInput("xpath",
			 * "(//input[@class='input-text item-price admin__control-text'])[2]",
			 * data.get(dataSet).get("highprice")); Thread.sleep(3000);
			 * Sync.waitElementPresent("xpath", "(//span[text()='Custom Price*'])[3]");
			 * Common.clickElement("xpath", "(//span[text()='Custom Price*'])[3]");
			 * Thread.sleep(3000); Common.textBoxInput("xpath",
			 * "(//input[@class='input-text item-price admin__control-text'])[3]",
			 * data.get(dataSet).get("highprice"));
			 */
			Thread.sleep(3000);
			Common.clickElement("xpath", "//span[contains(text(),'Update Items and Quantities')]");
			Thread.sleep(3000);
			updatedprice = Common.getText("xpath", "(//span[contains(text(),'" + high[i] + "')])[" + (i + 1) + "]").replace("$", "");
			Float updated_price = Float.valueOf(updatedprice);
			System.out.println(updated_price);
			Thread.sleep(3000);
			
			prices=prices.concat(",").trim();
			prices=prices.concat(updatedprice);
			System.out.println("---------------" +prices);
			CustomPrice.put("Updated Price", prices);
			Common.assertionCheckwithReport(updatedprice.contains(high[i]),
					"To Validate the custom price override with High Value", "should able to update the Custom price ",
					"Successfully price updated", "passed to update custom price");
		}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the increasing the price of product",
					"sku price should be increased", "increasing the sku price", "failed to increase the price");
			Assert.fail();

		}
		return CustomPrice;

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
			String page = Common.findElement("xpath", "//td[text()='Page Cache']").getText();
			if (page.equals("Page Cache")) {
				Sync.waitElementPresent("xpath", "//td[text()='Page Cache']");
				Common.clickElement("xpath", "//td[text()='Page Cache']");
			} else {
				Assert.fail();
			}
			String records = Common.findElement("xpath", "//span[@class='mass-select-info']//strong").getText();
			if (records.equals("1")) {
				Sync.waitElementPresent("xpath", "//span[text()='Submit']");
				Common.clickElement("xpath", "//span[text()='Submit']");
			} else {
				Assert.fail();
			}
			String message = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
			Common.assertionCheckwithReport(message.equals("1 cache type(s) refreshed."),
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

	public void dragndrop_promoBlock() {
		try {
			Common.scrollIntoView("xpath", "//span[text()='Promo Block']");
			WebElement element = Common.findElement("xpath", "//span[text()='Promo Block']");
			draganddropContentBlock(element);
			String blockname = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']/div")
					.getAttribute("data-content-type");
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(blockname.equals("hot_promo_block"),
					"Validating Promoblocker Dragndrop operation", "promoblocker dragndrop to content with options",
					"successfully dragndrop the promoblocker with options ", "fail to dragndrop the promobaner");
		} catch (Exception e) {

			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("Validating Promoblocker Dragndrop operation",
					"User should able Dragndrop Promoblocker", "Sucessfully Dragndrop Promoblocker",
					Common.getscreenShotPathforReport("Failed to Dragndrop Promoblocker"));
			Assert.fail();

		}
	}

	public void edit_promoBlock_one() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent(30, "xpath", "//div[@class='pagebuilder-content-type-wrapper']");
			String id = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']")
					.getAttribute("id");

			Common.mouseOverClick("xpath", "//div[@id='" + id + "']//div[3]//i");

			String editpromo = Common.findElement("xpath", "//h1[@class='modal-title']").getText();
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

			Common.assertionCheckwithReport(editpromo.contains("Edit Promo Media Card"),
					"To validate the admin is able to edit  Promo Media Card content",
					"After Clicking on the edit button admin should navigate to the edit promoBlock page ",
					"Admin Successfully navigated to edit promoBlock page ",
					"Failed to navigate to edit promoBlock page");

		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("To validate the admin is able to edit  Promo Media Card content",
					"After Clicking on the edit button admin should navigate to the edit promoBlock page ",
					"Admin is not able to  navigate to the edit Blocker page ",
					Common.getscreenShotPathforReport("Failed to navigate to edit Blocker page"));
			Assert.fail();

		}
	}

	public void edit_promoBlock_two() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent(30, "xpath", "//div[@class='pagebuilder-content-type-wrapper']");
			String id = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']")
					.getAttribute("id");

			Common.mouseOver("xpath", "//div[@id='" + id + "']//div[4]//i");
			Common.clickElement("xpath", "//div[@id='" + id + "']//div[4]//i");

			String editpromo = Common.findElement("xpath", "//h1[@class='modal-title']").getText();

			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

			Common.assertionCheckwithReport(editpromo.contains("Edit Promo Media Card"),
					"validating the Navigation to the edit  Promo Media Card page ",
					"After Click on edit button it should navigate to the edit promo content page ",
					"Successfully it is navigated to edit promo content page ",
					"Failed to navigate to the edit promo content page");

		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating the Navigation to the edit  Promo Media Card page ",
					"After Click on edit button it should navigate to the edit promo content page ",
					"Unable to navigated to the edit Blocker page ",
					Common.getscreenShotPathforReport("Failed to navigate to the edit promo content page"));
			Assert.fail();

		}

	}

	public void editpromocontent_video(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//li[@name='video']");
			Common.clickElement("xpath", "//li[@name='video']");
			Common.clickElement("xpath", "//input[@name='video_source']");
			Common.textBoxInput("xpath", "//input[@name='video_source']", data.get(Dataset).get("VideoURL"));
			/*
			 * Common.clickElement("xpath", "(//div[contains(@class,'sp-preview-')])[2]");
			 * String color = data.get(Dataset).get("Color"); Common.clickElement("xpath",
			 * "//span[@title='" + color + "']"); Common.clickElement("xpath",
			 * "//button[text()='Apply']"); String appliedcolor =
			 * Common.findElement("xpath", "//input[@class='colorpicker-spectrum']")
			 * .getAttribute("value");
			 * Common.assertionCheckwithReport(appliedcolor.equals(color),
			 * "validation of the color applied in the overlay color ",
			 * "after Clicking on the color the overlay color should be applied ",
			 * "Successfully overlay color is applied ", "Failed to apply overlay color");
			 */
		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validation of the color applied in the overlay color ",
					"after Clicking on the color the overlay color should be applied ",
					"Unable to applied the overlay color  ",
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
					"validating the image uploading on content for fallback image ",
					"Image should be upload for Fallback image", "Successfully image uploaded on Fallback image ",
					"Failed to upload image on the Fallback image");

			Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the image uploading on content for Fallback image ",
					"Image should be upload for Fallback image", "unable to upload on Fallback image ",
					Common.getscreenShotPathforReport("Failed to upload image on the Fallback image"));
			Assert.fail();

		}

	}

	public void video_promo_component() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//span[text()='Play video']");
			Common.clickElement("xpath", "//span[text()='Play video']");
			String pause = Common
					.findElement("xpath", "//button[contains(@class,'a-video-btn a-video-btn--icon-only js-v')]")
					.getAttribute("class");
			System.out.println(pause);
			Common.assertionCheckwithReport(pause.contains(" video-playing"),
					"validating the video uploading on fornt end  ", "video should be upload for front end ",
					"Successfully video uploaded in front end  ", "Failed to upload video on the frond end ");

		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating the video uploading on fornt end  ",
					"video should be upload for front end ", "Unable to upload video on front end  ",
					"Failed to upload video on the frond end ");
		}

	}

	public void clone_page_promo_block(String string) {
		// TODO Auto-generated method stub
		try {
			Common.switchToFirstTab();
			Contentpage();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//div[@data-content-type='hot_promo_block']");
			Common.mouseOver("xpath", "//div[@data-content-type='hot_promo_block']");
			Sync.waitElementPresent("xpath", "//a[@title='Duplicate']");
			Common.clickElement("xpath", "//a[@title='Duplicate']");
			edit_promoBlock_one();
			editpromocontent_image1();
			Sync.waitElementPresent(30, "xpath", "//i[@title='Close Full Screen']");
			Common.clickElement("xpath", "//i[@title='Close Full Screen']");
			Sync.waitElementPresent("xpath", "//button[@id='save-button']");
			Common.clickElement("xpath", "//button[@id='save-button']");
			Sync.waitPageLoad();
			Thread.sleep(8000);
			String savethepage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
			Common.assertionCheckwithReport(savethepage.equals("You saved the page."),
					"Validating the User need to save the page", "User should able to save the page",
					"Sucessfully User saves the page", "Unable to save the page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the User need to save the page",
					"User should able to save the page", "Unable to saves the page",
					Common.getscreenShotPathforReport("Failed to save the page"));
			Assert.fail();

		}

	}

	private void editpromocontent_image1() {
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
					"validating the image uploading on content for background image ",
					"Image should be upload for background image", "Successfully image uploaded in background image ",
					"Failed to upload image on the background image");
			Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validation the image uploading on content for background image ",
					"Image should be upload for background image", "Unable to upload the image in background image ",
					Common.getscreenShotPathforReport("Failed to upload image on the background image"));
			Assert.fail();

		}

	}

	public void clone_page_valueprop_forntend() {
		// TODO Auto-generated method stub
		try {
			Common.switchToSecondTab();
			Common.refreshpage();
			Sync.waitPageLoad();
			Common.scrollIntoView("xpath", "//div[@class='c-promo']//div[@data-background-type='image']");
			Thread.sleep(4000);
			String id = Common.findElement("xpath", "//div[@class='c-promo']//div[@data-background-type='image']")
					.getAttribute("data-pb-style");
			Thread.sleep(4000);
			String imagefrontend = Common.findElement("xpath", "//div[@data-pb-style='" + id + "']")
					.getAttribute("data-background-images");
			System.out.println(imagefrontend);
			Thread.sleep(3000);
			Common.assertionCheckwithReport(imagefrontend.contains("Lotusqa"),
					"validating the image uploading on content for fornt end image ",
					"Image should be upload for front end image", "Successfully image uploaded in front end image ",
					"Failed to upload image on the frond end image");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the image uploading on content for fornt end image ",
					"Image should be upload for front end image", "Unable to upload image on front end ",
					"Failed to upload image on the frond end image");
			Assert.fail();

		}

	}

	public void page_Cache() {
		// TODO Auto-generated method stub
		// String Magento = data.get(Dataset).get("cache");
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
			String page = Common.findElement("xpath", "//td[text()='Page Cache']").getText();
			if (page.equals("Page Cache")) {
				Sync.waitElementPresent("xpath", "//td[text()='Page Cache']");
				Common.clickElement("xpath", "//td[text()='Page Cache']");
			} else {
				Assert.fail();
			}
			String records = Common.findElement("xpath", "//span[@class='mass-select-info']//strong").getText();
			if (records.equals("1")) {
				Sync.waitElementPresent("xpath", "//span[text()='Submit']");
				Common.clickElement("xpath", "//span[text()='Submit']");
			} else {
				Assert.fail();
			}
			String message = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
			Common.assertionCheckwithReport(message.equals("1 cache type(s) refreshed."),
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

	public void Quantityincrease(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		try {

			if(Common.getCurrentURL().contains("na-preprod")) {
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//input[@name='product[quantity_and_stock_status][qty]']");
			Common.textBoxInput("xpath", "//input[@name='product[quantity_and_stock_status][qty]']",
					data.get(dataSet).get("Products"));
			}
			else {
				Sync.waitElementPresent("xpath", "//input[@name='sources[assigned_sources][0][quantity]']");
				Common.textBoxInput("xpath", "//input[@name='sources[assigned_sources][0][quantity]']",
						data.get(dataSet).get("Products"));
				Common.textBoxInput("xpath", "//input[@name='sources[assigned_sources][1][quantity]']",
						data.get(dataSet).get("Products"));
				Common.textBoxInput("xpath", "//input[@name='sources[assigned_sources][2][quantity]']",
						data.get(dataSet).get("Products"));
				
			}

			ExtenantReportUtils.addFailedLog("To validate the quantity", "the quantity to increase",
					"to increase the qty the product", Common.getscreenShot("Failed to display"));

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the quantity", "the quantity to increase",
					"to increase the qty the product", Common.getscreenShot("Failed to display"));
			Assert.fail();

		}

	}

	public void stockstatus(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String expectedResult = " should navigate to Out of stock";
		try {

			Thread.sleep(3000);
			if(Common.getCurrentURL().contains("na-preprod")) {
				Common.dropdown("xpath", "//select[@name='product[quantity_and_stock_status][is_in_stock]']",
						Common.SelectBy.TEXT, data.get(dataSet).get("Instock"));
			}
			else {
				
				Common.scrollIntoView("xpath", "//strong//span[text()='Sources']");
				Common.dropdown("xpath", "//select[@name='sources[assigned_sources][0][status]']",
						Common.SelectBy.TEXT, data.get(dataSet).get("Instock"));
				Common.dropdown("xpath", "//select[@name='sources[assigned_sources][1][status]']",
						Common.SelectBy.TEXT, data.get(dataSet).get("Instock"));
				Common.dropdown("xpath", "//select[@name='sources[assigned_sources][2][status]']",
						Common.SelectBy.TEXT, data.get(dataSet).get("Instock"));
				
							}
		
	
			ExtenantReportUtils.addFailedLog("verifying In stock productsin magento", expectedResult,
					"User failed to login in account  ", Common.getscreenShotPathforReport("login faield"));
		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("verifying login page with credentials", expectedResult,
					"User failed to login in account  ", Common.getscreenShotPathforReport("login faield"));

			Assert.fail();

		}
	}

	public void edit_Testimonial(String Dataset) {
		// TODO Auto-generated method stub
		String title = data.get(Dataset).get("Tiletext");
		try {
			Common.mouseOver("xpath", "//div[contains(@class,'c-testimonials-carousel__h')]");
			Sync.waitElementPresent("xpath", "(//a[@title='Edit'])[1]");
			Common.clickElement("xpath", "(//a[@title='Edit'])[1]");
			Thread.sleep(3000);
			String EditTestimonials = Common.findElement("xpath", "//h1[@class='modal-title']").getText();

			System.out.println(EditTestimonials);

			Common.assertionCheckwithReport(EditTestimonials.contains("Edit Testimonials Product Carousel"),
					"To validate the  Navigation to the Edit Testimonials Product Card page ",
					"After Click on the edit button it should be navigate to the Edit Testimonials Product Card page ",
					"Successfully it is navigated to the Edit Testimonials Product Card page ",
					"Failed to navigate to the Edit Testimonials Product Card page");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "(//input[@name='title'])[2]");
			Common.textBoxInput("xpath", "(//input[@name='title'])[2]", title);

			Configure_padding_marins(Dataset);
			Editandsavepage();
			String heading = Common.findElementBy("xpath", "//h2[@class='m-heading__text']").getText();
			System.out.println(heading);
			Common.assertionCheckwithReport(heading.equals(title), "Validatring the header title name for testinomial ",
					"After save button header titile should be display for the testinomial ",
					"Successfully header title is displayed for the testinomial ",
					"Failed to display the header title is displayed for the testinomial");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validatring the header title name for testinomial ",
					"After save button header titile should be display for the testinomial ",
					"Unable to display the header title is displayed for the testinomial ",
					Common.getscreenShot("Failed to display the header title is displayed for the testinomial"));
			Assert.fail();
		}

	}

	public void backgroundicon_image_galary(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "(//label[text()='Select from Gallery'])[1]");
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
//				String newid = Common.findElement("xpath", "//input[@name='alt']").getAttribute("id");
//				Common.textBoxInput("xpath", "//input[@id='" + newid + "']", data.get(Dataset).get("Attachment"));
			Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Sync.waitElementPresent("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");

			Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			String imageupload = Common.findElement("xpath", "(//div[@class='m-media-card-testimonial__author'])[2]")
					.getText();
			Common.assertionCheckwithReport(imageupload.contains("tester"),
					"validating the image uploading on testimonials ", "Image should be upload for testimonails",
					"Successfully image uploaded in the testimonials ", "Failed to upload image on the testimonials");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validation the image uploading on content in value prop banner ",
					"Image should be upload for value prop baneer", "Unable to upload image on the value prop banner ",
					Common.getscreenShotPathforReport("Failed to upload image on the value prop banner"));
			Assert.fail();

		}

	}

	public void hide_Testimonials() {
		// TODO Auto-generated method stub

		try {

			Sync.waitElementPresent(30, "xpath", "//div[contains(@class,'c-testimonials-carousel__h')]");
			Common.mouseOver("xpath", "//div[contains(@class,'c-testimonials-carousel__h')]");
			Sync.waitElementPresent("xpath", "//a[@class='hide-show-content-type']");
			Common.clickElement("xpath", "//a[@class='hide-show-content-type']");

			String hide = Common.findElement("xpath", "//a[@class='hide-show-content-type']").getAttribute("title");
			Common.assertionCheckwithReport(hide.equals("Show"), "Validating the User hide the content",
					"User should able to hide the content", "Sucessfully User hide the content",
					"Unable to save the content");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the User need to save the page",
					"User should able to save the page", "Unable to save the page",
					Common.getscreenShotPathforReport("Unable to save the page"));
			Assert.fail();

		}

	}

	public void clone_Testimonials() {
		// TODO Auto-generated method stub

		try {

			Common.switchToFirstTab();
			Search_previous_page_Magento("TestmonialProductcard");
			Contentpage();
			Sync.waitElementPresent(30, "xpath", "//div[contains(@class,'c-testimonials-carousel__h')]");
			Common.mouseOver("xpath", "//div[contains(@class,'c-testimonials-carousel__h')]");
			Sync.waitElementPresent("xpath", "(//a[@title='Duplicate'])[1]");
			Common.clickElement("xpath", "(//a[@title='Duplicate'])[1]");
			hide_Testimonials();

			Sync.waitElementPresent("xpath", "(//a[@title='Duplicate'])[8]");
			Common.clickElement("xpath", "(//a[@title='Duplicate'])[8]");

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

	public void clone_testimonials_frontend(String Datset) {
		// TODO Auto-generated method stub
		String SKU = data.get(Datset).get("SKU");

		try {
			Sync.waitPageLoad(40);

			String hide = Common
					.findElement("xpath",
							"(//div[contains(@class,'c-testimonials-carousel__item testing1 slick-slide ')])[1]")
					.getAttribute("aria-label");
			System.out.println(hide);

			String clone = Common.findElement("xpath", "//div[@aria-label='Slider 2 slide 4']")
					.getAttribute("data-product-sku");
			System.out.println(clone);
			System.out.println(SKU);

			Common.assertionCheckwithReport(hide.contains("Slider 1 slide 1") && clone.contains(SKU),
					"validating the text on clone hero banner on fornt end",
					"Text should be add for the clone hero baneer on fornt end",
					"Successfully text added to the  clone hero banner on front end",
					"Failed to add text on the clone hero banner on front end");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the text on clone hero banner on fornt end",
					"Text should be add for the clone hero baneer on fornt end",
					"Unable to add the text on the clone hero banner on front end",
					Common.getscreenShotPathforReport("Failed to add text on the clone hero banner on front end"));
			Assert.fail();

		}

	}

	public void Submit_Replacement_Success() {
		// TODO Auto-generated method stub
		try {

			Sync.waitElementPresent("xpath", "//span[text()='Submit Replacement']");
			Common.clickElement("xpath", "//span[text()='Submit Replacement']");
			Sync.waitPageLoad();

			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

			Sync.waitElementVisible("xpath", "//div[contains(@class,'success')]");
			String successmessage = Common.findElement("xpath", "//div[contains(@class,'success')]/div").getText();

			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("Orders / Operations / Sales / Magento Admin")
							&& successmessage.contains("You created the order."),
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

	public void Customer_StoreCredit_Blanace(String dataset) {
		// TODO Auto-generated method stub

		String Updatebalance = data.get(dataset).get("UpdateBalance");
		try {
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//span[text()='Store Credit']");
			Common.clickElement("xpath", "//span[text()='Store Credit']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String balance = Common.getText("xpath", "(//td[contains(@class,'col-amount a-left')])[2]").replace("$",
					"");
			Common.textBoxInput("id", "_customerbalanceamount_delta", Updatebalance);
			Thread.sleep(2000);
			Sync.waitElementPresent("id", "_customerbalancenotify_by_email");
			Common.clickElement("id", "_customerbalancenotify_by_email");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//button[@title='Save Customer']");
			Common.clickElement("xpath", "//button[@title='Save Customer']");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			String successmsg = Common.findElement("xpath", "//div[@class='message message-success success']")
					.getText();
			System.out.println(successmsg);

			Common.assertionCheckwithReport(successmsg.contains("customer"),
					"update the store Credit balance  on the customers page",
					" store credit  balance should update to the customer",
					"Successfully updated the store credit balance for the selected customer",
					"Passed to update the balance");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//button[text()='Clear all']");
			SelectCustomer_Edit("RetailOrder");
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//span[text()='Store Credit']");
			Common.clickElement("xpath", "//span[text()='Store Credit']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String updatedbalance = Common.getText("xpath", "(//td[contains(@class,'col-amount a-left')])[2]")
					.replace("$", "");
			Float previousbalancevalue = Float.parseFloat(balance);
			Float balancevalue = Float.parseFloat(Updatebalance);
			Float Total = previousbalancevalue + balancevalue;
			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			Common.assertionCheckwithReport(ExpectedTotalAmmount2.equals(updatedbalance),
					"validating the updated store credit balance", "store credit balance should be update ",
					"Successfully store credit balance has been updated", "Failed to update the store credit balance");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the updated store credit balance",
					"store credit balance should be update ", "unable to update the store credit balance",
					Common.getscreenShotPathforReport("Failed to update the store credit balance"));
			Assert.fail();

		}
	}

	public void reorder_from_customers(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//button[@title='Create Order']");
			Common.clickElement("xpath", "//button[@title='Create Order']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("New Order"),
					"validating the create new order from customers",
					"After clicking on the create new order it should navigate to the new orders page ",
					"Successfully Navigated to the new orders page after clicking on the create new order",
					"Failed to Navigated to thr new orders page");
			select_a_store("RetailOrder");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the create new order from customers",
					"After clicking on the create new order it should navigate to the new orders page ",
					"Unable to Navigated to thr new orders page",
					Common.getscreenShotPathforReport("Failed to Navigated to thr new orders page"));
			Assert.fail();
		}

	}

	public void select_a_store(String Dataset) {
		// TODO Auto-generated method stub
		String storeview = data.get(Dataset).get("Store");
		try {
			Thread.sleep(2000);

			Sync.waitElementPresent("xpath", "//label[text()='" + storeview + "']");
			Common.clickElement("xpath", "//label[text()='" + storeview + "']");
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

	public void Address_registeruser(String Dataset) {
		// TODO Auto-generated method stub
		String Street = data.get(Dataset).get("streetaddress");
		String newaddress = data.get(Dataset).get("Address");
		try {
			Sync.waitElementPresent("xpath", "//select[contains(@id,'order-billing_address')]");
			Common.dropdown("xpath", "//select[contains(@id,'order-billing_address')]", Common.SelectBy.TEXT,
					newaddress);
			Sync.waitElementPresent("xpath", "//input[@name='order[billing_address][firstname]']");
			Common.textBoxInput("xpath", "//input[@name='order[billing_address][firstname]']",
					data.get(Dataset).get("FirstName"));
			Sync.waitElementPresent("xpath", "//input[@name='order[billing_address][lastname]']");
			Common.textBoxInput("xpath", "//input[@name='order[billing_address][lastname]']",
					data.get(Dataset).get("LastName"));
			Sync.waitElementPresent("xpath", "//input[@name='order[billing_address][street][0]']");
			Common.textBoxInput("xpath", "//input[@name='order[billing_address][street][0]']",
					data.get(Dataset).get("streetaddress"));
			Sync.waitElementPresent("xpath", "//input[@name='order[billing_address][city]']");
			Common.textBoxInput("xpath", "//input[@name='order[billing_address][city]']",
					data.get(Dataset).get("City"));
			Sync.waitElementPresent("xpath", "//select[@name='order[billing_address][region_id]']");
			Common.dropdown("xpath", "//select[@name='order[billing_address][region_id]']", Common.SelectBy.TEXT,
					data.get(Dataset).get("State"));
			Sync.waitElementPresent("xpath", "//input[@name='order[billing_address][postcode]']");
			Common.textBoxInput("xpath", "//input[@name='order[billing_address][postcode]']",
					data.get(Dataset).get("Postcode"));
			Sync.waitElementPresent("xpath", "//input[@name='order[billing_address][telephone]']");
			Common.textBoxInput("xpath", "//input[@name='order[billing_address][telephone]']",
					data.get(Dataset).get("Phonenumber"));
			String address = Common.findElement("xpath", "//input[@name='order[billing_address][street][0]']")
					.getAttribute("value");
			Common.assertionCheckwithReport(address.equals(Street), "validating the address in magneto",
					"Address should be added in the magento", "Sucessfully address added to the magento",
					"Failed to add address to the magneto order");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the address in magneto",
					"Address should be added in the magento", "Unable  to add address to the magneto order",
					"Failed to add address to the magneto order");
			Assert.fail();
		}
	}

	public void Shipping_method(String Dataset) {
		// TODO Auto-generated method stub
		String method = data.get(Dataset).get("Shipping method");
		try {

			// Common.scrollIntoView("xpath",
			// "//div[@id='order-shipping-method-summary']/a");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//div[@id='order-shipping-method-summary']/a");

			Common.doubleClick("xpath", "//div[@id='order-shipping-method-summary']/a");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//label[contains(text(),'" + method + "')]");
			Common.clickElement("xpath", "//label[contains(text(),'" + method + "')]");

			Sync.waitPageLoad();
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			int selectedshippingmethods = Common.findElements("xpath", "//dd[contains(text(),'Ground')]").size();
			System.out.println(selectedshippingmethods);
			int changeshippingmethods = Common.findElements("xpath", "//span[text()='Click to change shipping method']")
					.size();
			System.out.println(changeshippingmethods);
			Common.assertionCheckwithReport(selectedshippingmethods >= 1 && changeshippingmethods > 0,
					"To validate the shipping methods is selected",
					"Shipping methods should be selected and change the shipping methods link should be displayed",
					"Shipping methods are selected and change the shipping methods link is displayed",
					"Failed to select Shipping methods and change the shipping methods link is not displayed");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the shipping methods is selected",
					"Shipping methods should be selected and change the shipping methods link should be displayed",
					"Shipping methods are not selected and change the shipping methods link is not displayed",
					"Failed to select Shipping methods and change the shipping methods link is not displayed");
			Assert.fail();
		}

	}

	public String Address_and_Sku_Validation(String Dataset) {
		// TODO Auto-generated method stub
		String Address = "";

		try {
			Address = Common.findElement("xpath", "//address[@class='admin__page-section-item-content']").getText();
			System.out.println(Address);
			List<WebElement> sku = Common.findElements("xpath", "//div[@class='product-sku-block']");
			ArrayList<String> skuids = new ArrayList<String>();
			for (WebElement Allskus : sku) {
				skuids.add(Allskus.getText().replace("SKU:", "").trim());
				System.out.println(skuids);

			}

			String[] SKUnumber = data.get(Dataset).get("SKUNumber").split(",");

			for (int i = 0; i < SKUnumber.length; i++) {

				if (skuids.contains(SKUnumber[i])) {
//					 System.out.println(skuids);
					System.out.println(SKUnumber[i]);

				} else {

					System.out.println(SKUnumber[i]);
//					ExtenantReportUtils.addFailedLog("Validating the sku ids in magento",
//							"the skuids should be equal to the SKUnumber" ,
//							"The displayed skuids is not equal to the SKUnumber",
//							Common.getscreenShotPathforReport("Failed to match the skuids to the Skunumber"));
//					Assert.fail();
				}
//				Common.assertionCheckwithReport(skuids.equals(SKUnumber), "Validating the sku ids in magento",
//						"the " + skuids + "should be equal to the " + SKUnumber[i],
//						"The displayed" + skuids + "is equal to the" + SKUnumber[i],
//						"Failed to match the" + skuids + "to the" + SKUnumber[i]);
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the sku ids in magento",
					"the skuids should be equal to the SKUnumber", "The displayed skuids is not equal to the SKUnumber",
					Common.getscreenShotPathforReport("Failed to match the skuids to the SKUnumber"));
			Assert.fail();
		}
		return Address;
	}

	public void Login_Kustomerwebsite(String Dataset) {
		// TODO Auto-generated method stub
		String url = data.get(Dataset).get("URL");
		try {
			Common.openNewTab();
			Common.oppenURL(url);
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//img[@class='samlAuthButton']");
			Common.clickElement("xpath", "//img[@class='samlAuthButton']");
			Thread.sleep(4000);
//		          Sync.waitElementPresent("name", "loginfmt");
//		         Common.textBoxInput("name", "loginfmt", data.get(Dataset).get("UserName"));
//		         Common.clickElement("id", "idSIButton9");
//		         Sync.waitPageLoad();
//		         Thread.sleep(3000);
//		         Sync.waitElementPresent(30, "name", "passwd");
//		         Common.textBoxInput("name", "passwd", data.get(Dataset).get("Password"));
//		         Common.clickElement("id", "idSIButton9");
//		         Sync.waitPageLoad();
//		         Thread.sleep(3000);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("Kustomer | Hydro Flask")
							|| Common.getPageTitle().contains("Kustomer | oxo"),
					"Validating the kustomer login",
					"After clicking on the login button it should navigate to the kustomer page",
					"sucessfully it is navigated to the Kustomer page", "Failed to navigate to the Kustomer page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the kustomer login",
					"After clicking on the login button it should navigate to the kustomer page",
					"Unable to navigate to the Kustomer page",
					Common.getscreenShotPathforReport("Failed to navigate to the Kustomer page"));
			Assert.fail();
		}

	}

	public void search_ordernumber(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//input[@type='search']");
			Common.clickElement("xpath", "//input[@type='search']");
			Common.textBoxInput("xpath", "//input[@type='search']", Dataset);
			List<WebElement> listofordernumber = Common.findElements("xpath",
					"//mark[contains(@class,'headerSearch')]");
			ArrayList<WebElement> listordernumber = new ArrayList<WebElement>();
			for (WebElement Kustomerordernumber : listofordernumber) {

				listordernumber.add(Kustomerordernumber);
				String add = Kustomerordernumber.getText();
				Common.assertionCheckwithReport(add.equals(Dataset), "validating the dropdown in the order number ",
						"User should able to see the  dropdown for respective order number",
						"Successfully order dropdown has been displayed", "Failed to display order dropdown ");
				Kustomerordernumber.click();

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the dropdown in the order number ",
					"User should able to see the  dropdown for respective order number",
					"unable to displayed the order dropdown",
					Common.getscreenShotPathforReport("Failed to display the order dropdown"));
			Assert.fail();
		}
	}

	public HashMap<String, String> productprice_and_Quantity() {
		// TODO Auto-generated method stub
		HashMap<String, String> data = new HashMap<String, String>();
		try {
			List<WebElement> totalproducts = Common.findElements("xpath",
					"//tbody//td[@class='col-price']//span[@class='price']");
			List<WebElement> quantity = Common.findElements("xpath",
					"//tbody//td[@class='col-qty']//input[contains(@class,'input-text')]");

			List<WebElement> rowsubtotal = Common.findElements("xpath",
					"//td[@class='col-price col-row-subtotal']//span");

			for (int i = 0; i < totalproducts.size(); i++) {
				List<WebElement> productprice = Common.findElements("xpath",
						"//tbody//td[@class='col-price']//span[@class='price']");

				String Price = productprice.get(i).getText().replace("$", "");

				Float Priceammountvalue = Float.valueOf(Price);

				List<WebElement> productqty = Common.findElements("xpath",
						"//tbody//td[@class='col-qty']//input[contains(@class,'input-text')]");

				String QTY = productqty.get(i).getAttribute("value");

				Float QTYsvalue = Float.parseFloat(QTY);
				Float ExpectedTotalAmmount = Priceammountvalue * QTYsvalue;

				String ExpectedTotalAmmount2 = new BigDecimal(ExpectedTotalAmmount)
						.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				String ExpectedTotalAmount = ExpectedTotalAmmount2;

				data.put("ExpectedTotalAmmountvalue", ExpectedTotalAmmount2);

				List<WebElement> rowtotal = Common.findElements("xpath",
						"//td[@class='col-price col-row-subtotal']//span");

				String row = rowtotal.get(i).getText().replace("$", "");

				data.put("rowtotalvalue", row);
				System.out.println(data);
				Common.assertionCheckwithReport(row.matches(ExpectedTotalAmount),
						"validating the product qty and amount equals to the subtotal ",
						"User should able to product qty and amount equals to the subtotal",
						"Successfully after multiply the product amount and quantity is equal to the subtotal",
						"Failed to equal to the subtotal for product amount and quantity ");

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the product qty and amount equals to the subtotal ",
					"User should able to product qty and amount equals to the subtotal",
					"unable to equal to the subtotal for product amount and quantity",
					Common.getscreenShotPathforReport(
							"Failed to equal to the subtotal for product amount and quantity"));
			Assert.fail();
		}
		return data;
	}

	public void clickPLP_product(String dataset) {
		// TODO Auto-generated method stub
		String expectedResult = "to validate product PDP";
		String Symbols = data.get(dataset).get("Symbol");
		String ActualPrice = data.get(dataset).get("SpecialPrice");
		String Search= data.get(dataset).get("search");

		try {
			Thread.sleep(3000);
			Common.clickElement("xpath", "//mark[text()='QA']");
			Thread.sleep(2000);
			int pages = Common.findElements("xpath", "//p[text()='"+Search+"']").size();
			System.out.println(pages);

			String Special_Price = Common.getText("xpath", "(//span[@class='price-wrapper']//span[@class='price'])[1]");
			String Price = Special_Price.replace(Symbols, " ");

			int Addtocart = Common
					.findElements("xpath", "//button[@id='product-addtocart-button']").size();
			if (Addtocart > 0) {
				System.out.println("Add to cart Button is displaying");
			} else {
				Assert.fail();
			}
			
			System.out.println(Special_Price);
			System.out.println(Price);
			Common.assertionCheckwithReport(Price.contains(ActualPrice), "To Validate the QATEST product Special Price",
					"should display the QATEST product Special Price ",

					"QATEST product special price is displayed after a click on the product",
					"Failed to display QATEST product special price");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To saearch product to PDP successfully and success message is dispalyed",
					"Should display successmessage after deleteing the catalog price rule",
					"Failed to delet the categroy price rule", "Catalog price rule deletion unsuccessfull");
			Assert.fail();

		}
	}

	public void Website_Selection(String dataSet) {
		// TODO Auto-generated method stub
		String Stores = data.get(dataSet).get("Store");
		try {
			if(Common.getCurrentURL().contains("na-preprod")) {
			Thread.sleep(2000);
			Common.scrollIntoView("xpath", "//span[text()='Product in Websites']");
			Common.actionsKeyPress(Keys.ARROW_UP);
			Sync.waitElementPresent("xpath", "//span[text()='Product in Websites']");
			Common.clickElement("xpath", "//span[text()='Product in Websites']");

			Thread.sleep(2000);
			WebElement checkbox = Common.findElementBy("xpath", "//label[text()='" + Stores + "']//parent::div//input");
			// Verify if the checkbox is selected
			boolean isSelected = checkbox.isSelected();
			System.out.println(isSelected);

			if (isSelected) {
				System.out.println("The checkbox is already selected.");
			} 
			else {
				Sync.waitElementPresent("xpath", "//label[text()='" + Stores + "']//parent::div//input");
				Common.clickElement("xpath", "//label[text()='" + Stores + "']//parent::div//input");
			}

			Sync.waitPageLoad();
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//span[text()='Save']");
			Common.clickElement("xpath", "//span[text()='Save']");
			Thread.sleep(9000);

			int pages = Common.findElements("xpath", "//div[@class='message message-success success']").size();
			System.out.println(pages);
			}
			else {
				Thread.sleep(2000);
				Common.scrollIntoView("xpath", "//span[text()='Product in Websites']");
				Common.actionsKeyPress(Keys.ARROW_UP);
				Sync.waitElementPresent("xpath", "//span[text()='Product in Websites']");
				Common.clickElement("xpath", "//span[text()='Product in Websites']");

				Thread.sleep(2000);
				WebElement checkbox = Common.findElementBy("xpath", "//label[text()='" + Stores + "']//parent::div//input");
				// Verify if the checkbox is selected
				boolean isSelected = checkbox.isSelected();
				System.out.println(isSelected);

				if (isSelected) {
					System.out.println("The checkbox is already selected.");
				} 
				else {
					Sync.waitElementPresent("xpath", "//label[text()='" + Stores + "']//parent::div//input");
					Common.clickElement("xpath", "//label[text()='" + Stores + "']//parent::div//input");
				}

				Sync.waitPageLoad();
				Thread.sleep(2000);
				Sync.waitElementPresent("xpath", "//span[text()='Save']");
				Common.clickElement("xpath", "//span[text()='Save']");
				Thread.sleep(9000);

				int pages = Common.findElements("xpath", "//div[@class='message message-success success']").size();
				System.out.println(pages);
				
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the Items Ordered page is displayed",
					"should display the Items OrderedT page after clicking on the store",
					"unable to display Items OrderedT page after a click on the store button",
					"Failed to display Items Ordered page");
			Assert.fail();
		}

	}

	public void prepareTaxData(String fileName) {
		// TODO Auto-generated method stub
		
			try{
				
				File file=new File(System.getProperty("user.dir")+"/src/test/resources/TestData/Admin/"+fileName);
				XSSFWorkbook workbook;
				XSSFSheet sheet;
				Row row;
				Cell cell;
				int rowcount;
				if(!(file.exists()))
				{
				workbook = new XSSFWorkbook();
				sheet = workbook.createSheet("Order ID");
				CellStyle cs = workbook.createCellStyle();
				cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				cs.setFillForegroundColor(IndexedColors.GOLD.getIndex());
				Font f = workbook.createFont();
				f.setBold(true);
				cs.setFont(f);
				cs.setAlignment(HorizontalAlignment.RIGHT);
				row = sheet.createRow(0);
				cell = row.createCell(0);
				cell.setCellStyle(cs);
				cell.setCellValue("Invoice_OrderDetails");


				row = sheet.createRow(1);
				cell = row.createCell(0);
				cell.setCellStyle(cs);
				cell.setCellValue("S.No");
				cell = row.createCell(1);
				cell.setCellStyle(cs);
				cell.setCellValue("Order Status");
				cell=row.createCell(2);
				cell.setCellStyle(cs);
				cell.setCellValue("Invoice Number");
				cell=row.createCell(3);
				cell.setCellStyle(cs);
				cell.setCellValue("Shipment Number");
				rowcount=2;
				}

				else
				{
				workbook = new XSSFWorkbook(new FileInputStream(file));
				sheet=workbook.getSheet("Order ID");
				rowcount=sheet.getLastRowNum()+1;
				}
				/*row = sheet.createRow(rowcount);
				cell = row.createCell(0);*/



				FileOutputStream fileOut = new FileOutputStream(file);
				workbook.write(fileOut);
				fileOut.flush();
				fileOut.close();



				} catch (Exception e) {
				e.printStackTrace();
				}
				}

	public String Invoice_Number(String Dataset) {
		// TODO Auto-generated method stub
		String orderNumber=data.get(Dataset).get("OrderNumber");
		String Invoice_Number="";
		try
		{
			Thread.sleep(4000);
			String Number=Common.findElement("xpath", "(//div[@class='data-grid-cell-content'])[1]").getText();
			System.out.println(Number);
			if(Number.equals(orderNumber))
			 {
						Thread.sleep(3000);
						Common.clickElement("xpath", "//td//a[@class='action-menu-item']");	
						Sync.waitPageLoad();
						Thread.sleep(4000);
						Common.assertionCheckwithReport(Common.getCurrentURL().contains("/sales/order/view"), "To Validate the navigatd to the order details page",
								"It should navigate to order details page",
								"Sucessfully navigate to the order details page",
								"Failed to Navigate to the order deatils page");
			 }
			Common.clickElement("xpath", "//li[@id='sales_order_view_tabs_order_invoices']");
			 Invoice_Number=Common.findElement("xpath", "(//tr[@class='data-row']//td//div)[1]").getText().trim();
			System.out.println(Invoice_Number);
		    	
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the navigatd to the order details page",
					"It should navigate to order details page",
					"Unable navigate to the order details page",
					"Failed to Navigate to the order details page");
			Assert.fail();
		}
		return Invoice_Number;
		
	}

	public String Shipment_Number() {
		// TODO Auto-generated method stub
		String Shipment_Number="";
		try
		{
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//a[@id='sales_order_view_tabs_order_shipments']");
			Common.clickElement("xpath", "//a[@id='sales_order_view_tabs_order_shipments']");
			Thread.sleep(2000);
			Common.assertionCheckwithReport(Common.findElement("xpath", "(//th[@class='data-grid-th _sortable _draggable _ascend']//span[@class='data-grid-cell-content'])[2]").getText().contains("Shipment"), "To Validate the navigatd to the shipment page",
					"It should navigate to Shipment details page",
					"Sucessfully navigate to the Shipment details page",
					"Failed to Navigate to the order Shipment page");
			 Shipment_Number=Common.findElement("xpath", "((//tr[@class='data-row'])[2]//td//div)[1]").getText().trim();
			System.out.println(Shipment_Number);
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the navigatd to the Shipment details page",
					"It should navigate to Shipment details page",
					"Unable navigate to the Shipment details page",
					"Failed to Navigate to the Shipment details page");
			Assert.fail();
		}
		return Shipment_Number;
		
	}

	public void Information_Page() {
		// TODO Auto-generated method stub
		try
		{
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//a[@id='sales_order_view_tabs_order_info']");
			Common.clickElement("xpath", "//a[@id='sales_order_view_tabs_order_info']");
			int size=Common.findElements("xpath", "//table[@class='data-table admin__table-primary edit-order-table']//tbody//table//tr").size();
			System.out.println(size);
			for(int i=0;i<size;i++)
			{
				int value=i+1;
				String details=Common.findElement("xpath", "(//table[@class='data-table admin__table-primary edit-order-table']//tbody//table//tr)['"+ value +"']").getText().trim();
				System.out.println(details);
				Common.assertionCheckwithReport(details.contains("Ordered") || details.contains("Invoiced") || details.contains("Shipped") , "To Validate the shipment and invoice is displayed",
						"It should display shippment and invoice for the order",
						"Sucessfully shipment and invoice has been displayed for thee orders",
						"Failed to display the shipment and invoice for the orders");
				
			}
		Common.clickElement("xpath", "//button[@title='Back']");
		Sync.waitPageLoad();
		Thread.sleep(5000);
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("/sales/order/"), "To Validate navigated to the order page",
				"should navigate sucessfully to the orders page ","Successfullt user navigated to the orders page",
				"Failed to Navigate to the order details page");
		
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the navigatd to the order details page",
					"It should navigate to order details page",
					"Unable navigate to the order details page",
					"Failed to Navigate to the order details page");
			Assert.fail();
		}
		
	}
	
	
	public void writeOrderNumber(String Status,String Invoice,String Shipment)
			throws FileNotFoundException, IOException {
		// String fileOut="";
		try {

			File file = new File(
					System.getProperty("user.dir") + "/src/test/resources//TestData//Admin//Admin_Invoice_OrderNumbers.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet;
			Row row;
			Cell cell;
			int rowcount;
			sheet = workbook.getSheet("Order ID");

			if ((workbook.getSheet("Order ID")) == null) {
				sheet = workbook.createSheet("OrderDetails");
				CellStyle cs = workbook.createCellStyle();
				cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				cs.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
				Font f = workbook.createFont();
				f.setBold(true);
				cs.setFont(f);
				cs.setAlignment(HorizontalAlignment.RIGHT);
				row = sheet.createRow(0);
				cell = row.createCell(0);
				cell.setCellStyle(cs);
				cell.setCellValue("Order ID");

				row = sheet.createRow(1);
				cell = row.createCell(0);
				cell.setCellStyle(cs);
				cell.setCellValue("Web Order Number");
				rowcount = 2;

			}

			else {

				sheet = workbook.getSheet("Order ID");
				rowcount = sheet.getLastRowNum() + 1;
			}
			row = sheet.createRow(rowcount);
			cell = row.createCell(0);
			cell.setCellType(CellType.NUMERIC);
			int SNo=rowcount-1;
			cell.setCellValue(SNo);
			cell = row.createCell(1);
			cell.setCellType(CellType.STRING);
			
			cell.setCellValue(Status);
			
			cell = row.createCell(2);
			cell.setCellType(CellType.STRING);
			cell.setCellValue(Invoice);
			
			cell = row.createCell(3);
			cell.setCellType(CellType.STRING);
			cell.setCellValue(Shipment);
			
			
			System.out.println(Shipment);
			FileOutputStream fileOut = new FileOutputStream(file);
			
			workbook.write(fileOut);
		
			fileOut.flush();
			fileOut.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
	
	public String ordersearch_status(String Dataset) {
		// TODO Auto-generated method stub
		String orderNumber=data.get(Dataset).get("OrderNumber");
		String Status="";
		try
		{
			int filters = Common.findElements("xpath", "//div[@class='admin__data-grid-filters-current _show']").size();
			if (filters > 0) {
				Common.clickElement("xpath",
						"//div[@class='admin__data-grid-filters-current _show']//button[text()='Clear all']");
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
				Thread.sleep(8000);
				Common.findElement("xpath", "//input[@aria-label='Search by keyword']");
				Thread.sleep(6000);
				Common.clickElement("xpath", "//input[@aria-label='Search by keyword']");
				Thread.sleep(6000);
				Common.scrollIntoView("xpath", "//input[@aria-label='Search by keyword']");
				Common.textBoxInput("xpath", "//input[@aria-label='Search by keyword']", orderNumber);
				Common.actionsKeyPress(Keys.ENTER);
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
				Thread.sleep(8000);
			    Status=Common.findElement("xpath", "(//tr[@class='data-row']//child::td)[9]//div").getText();
				System.out.println(Status);
				Common.assertionCheckwithReport(Common.getCurrentURL().contains("/sales/order/"), "To Validate navigated to the order page",
						"should navigate sucessfully to the orders page ","Successfullt user navigated to the orders page",
						"Failed to Navigate to the order details page");
		}
		}
			catch(Exception | Error e)
			{
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("To Validate the navigatd to the order details page",
						"It should navigate to order details page",
						"Unable navigate to the order details page",
						"Failed to Navigate to the order details page");
				Assert.fail();
			}
	
		return Status;
	

}
	

}
