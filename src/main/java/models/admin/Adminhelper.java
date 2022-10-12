package models.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;

import TestLib.Automation_properties;
import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Common.SelectBy;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import groovyjarjarantlr.CommonAST;

public class Adminhelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();



public Adminhelper(String datafile,String DataSet) {
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
					"Validating Testimonials Product Carousel Dragndrop operation", "Testimonials Product Carousel dragndrop to content with options",
					"successfully dragndrop the Testimonials Product Carousel with options ", "fail to dragndrop the Testimonials Product Carousel");
		} catch (Exception e) {

			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("Validating card tile Dragndrop operation",
					"User should able Dragndrop Testimonials Product Carousel", "Unable to Dragndrop the Testimonials Product Carousel",
					Common.getscreenShotPathforReport("Failed to Dragndrop the Testimonials Product Carousel"));
			Assert.fail();

		}
	}
	public void Edit_Testimonials_Product_Carousel() {
		try {
		       int TestimonialSize=Common.findElements("xpath","//div[@class='m-media-card-testimonial__content text__content--dark']").size();
		        System.out.println(TestimonialSize);
			Common.mouseOver("xpath","(//div[@class='m-media-card-testimonial__image'])[1]");
		
			Sync.waitElementClickable("xpath", "(//i[@class='icon-admin-pagebuilder-systems'])[2]");
			Common.clickElement("xpath","(//i[@class='icon-admin-pagebuilder-systems'])[2]");
			
	//		String Title = Common.findElement("xpath", "(//h1[@class='modal-title'])[1]").getText();
			
		
			Common.assertionCheckwithReport(TestimonialSize>0,
					"Validating Edit Testimonias Product Card Page", "Edit Testimonials Product Card page with options",
					"successfully navigate to Edit Testimonials Product Card page ", "fail to navigate to Edit Testimonials Product Card page");
		} catch (Exception e) {

			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("Validating Edit Testimonials Product Card page",
					"User should able to navigate Edit Testimonials Product Card page", "Unable to navigate Edit Testimonials Product Card page",
					Common.getscreenShotPathforReport("Failed to navigate to Edit Testimonials Product Card page"));
			Assert.fail();

		}
	}
	public void SKUvalue(String dataSet) {
		try {
			String SKU = Common.findElement("xpath", "//span[text()='Product SKU']").getText();
			Common.javascriptclickElement("xpath", "//input[@name='product_sku']");
		    Sync.waitElementClickable("xpath","//input[@name='product_sku']" );
			Common.textBoxInput("xpath", "//input[@name='product_sku']", data.get(dataSet).get("SKU"));

		
			Common.assertionCheckwithReport(SKU.equals("Product SKU"), "To validate the Product SKU",
					"Should able to add Product SKU value ", "Product SKU value is added successfully",
					"Product SKU value failed");
		
		
					} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Product SKU",
					"Admin should able to enter Product SKU value", "Product SKU failed",
					Common.getscreenShotPathforReport("Failed to enter Product SKU"));

			Assert.fail();

		}

	}
	
	public void Content_Dark(String dataSet) {
		try {
 
			String textcolor = Common.findElement("xpath","//span[text()='Dark']").getText();
			Common.javascriptclickElement("xpath", "(//input[@name='title'])[2]");
			Sync.waitElementClickable("xpath","(//input[@name='title'])[2]");
			Common.textBoxInput("xpath", "(//input[@name='title'])[2]", data.get(dataSet).get("Author"));

			Common.javascriptclickElement("xpath", "//textarea[@name='description']");
			Sync.waitElementClickable("xpath","//textarea[@name='description']");
			Common.textBoxInput("xpath", "//textarea[@name='description']", data.get(dataSet).get("Quote"));

		
			Common.assertionCheckwithReport(textcolor.equals("Dark"), "To validate the Content values",
					"Should able to add Content values ", "Content values is added successfully",
					"Content values failed");
		
		
					} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Content feildset",
					"Admin should able to Content values", "Content values failed",
					Common.getscreenShotPathforReport("Failed to add Content values"));

			Assert.fail();

		}

	}

	public void CSSclassvalue(String dataSet) {
		try {

					Common.scrollIntoView("xpath", "//span[text()='Advanced']");
					String Advanced = Common.findElement("xpath","//span[text()='Advanced']").getText();
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
	
	public void frontendView_Dark() {
		// TODO Auto-generated method stub                     
		try {
			
			//div[@data-product-sku='W32075']
			
			ClosADD();
			String SKU_value = Common.findElement("xpath","//div[@data-product-sku='W32075']").getAttribute("data-product-sku");
			Assert.assertEquals(SKU_value, "W32075");
			
			Sync.waitElementPresent("xpath", "(//div[contains(@class,'text__content--dark')])[1]");
			String textview = Common.findElement("xpath", "(//div[contains(@class,'text__content--dark')])[1]").getAttribute("class");
			Common.assertionCheckwithReport(textview.contains("dark"),
					"Validating the textview on frontend ",
					"Able to display the text as Dark view on the frontend",
					"Successfully text is dispalyed as Dark",
					"Failed to Display dark view on frontend");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the textview on frontend",
					"Able to display the text as Dark view on the frontend ",
					"unable to display the text as dark view on the frontend",
					Common.getscreenShotPathforReport("Failed to Display view Selector CTA on customers page"));
			Assert.fail();

		}

	}
	
	public void Content_Light() {
		try {
 
			
			String textcolor = Common.findElement("xpath","//span[text()='Light']").getText();
			Common.javascriptclickElement("xpath", "//span[text()='Light']");

			Common.assertionCheckwithReport(textcolor.equals("Light"), "To validate the Content values",
					"Should able to add Content values ", "Content values is added successfully",
					"Content values failed");
		
		
					} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Content feildset",
					"Admin should able to Content values", "Content values failed",
					Common.getscreenShotPathforReport("Failed to add Content values"));

			Assert.fail();

		}

	}
	public void frontendView_Light() {
		// TODO Auto-generated method stub                     
		try {
			
			ClosADD();
		
			String SKU_value = Common.findElement("xpath","//div[@data-product-sku='W32075']").getAttribute("data-product-sku");
			Assert.assertEquals(SKU_value, "W32075");
			
			
			Sync.waitElementPresent("xpath", "(//div[contains(@class,'text__content--light')])[1]");
			String textview = Common.findElement("xpath", "(//div[contains(@class,'text__content--light')])[1]").getAttribute("class");
			Common.assertionCheckwithReport(textview.contains("dark"),
					"Validating the textview on frontend ",
					"Able to display the text as Dark view on the frontend",
					"Successfully text is dispalyed as Dark",
					"Failed to Display dark view on frontend");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the textview on frontend",
					"Able to display the text as Dark view on the frontend ",
					"unable to display the text as dark view on the frontend",
					Common.getscreenShotPathforReport("Failed to Display view Selector CTA on customers page"));
			Assert.fail();

		}

	}
	public void shifttab() throws InterruptedException {
		// TODO Auto-generated method stub
	
		Common.switchToSecondTab();
		Sync.waitPageLoad(5000);
		Thread.sleep(8000);
		Common.refreshpage();	
		Common.refreshpage();
		Thread.sleep(4000);
		Common.refreshpage();
		Common.refreshpage();
		
	}
	public void ClosADD() throws Exception{
		   Thread.sleep(3000);
		    int sizesframe=Common.findElements("xpath", "//div[@class='preloaded_lightbox']/iframe").size();
		    if(sizesframe>0){
		    Common.actionsKeyPress(Keys.PAGE_UP);
		   
		   // Common.switchFrames("xpath", "//div[@class='preloaded_lightbox']/iframe");
		    Sync.waitElementVisible("xpath" , "//div[@class='sidebar-iframe-close']");
		    Common.clickElement("xpath", "//div[@class='sidebar-iframe-close']");
		    }
		    else {
		        int sizeofpopup=Common.findElements("id", "wpx-newsletter-popup").size();
		        if(sizeofpopup>0){
		            
		            
		            Sync.waitElementClickable("xpath" , "//button[@aria-label='close']");
		            Common.clickElement("xpath" , "//button[@aria-label='close']");
		    }
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

	public void NewcustomerCTA() {
		// TODO Auto-generated method stub

		try {
			Sync.waitElementPresent("xpath", "//span[text()='All Customers']");
			Common.clickElement("xpath", "//span[text()='All Customers']");
			String newcustomer = Common.findElement("xpath", "//button[@id='add']").getAttribute("title");
			System.out.println(newcustomer);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(newcustomer.equals("Add New Customer"),
					"validating new customer CTA Button in Customers page", "Able to display Add new Customer CTA ",
					"successfully displayed Add New Customer CTA", "Failed to display Add New Customer CTA");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating new customer CTA Button in Customers page",
					"Able to display Add new Customer CTA", "Unable to  display Add New Customer CTA",
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
			Common.assertionCheckwithReport(Search.equals("Search by keyword"),
					"validating search by keyword input on customers page",
					"Able to display search by keyword input on customer page",
					"successfully display search by keyword on customers page",
					"Failed to display search by keyword on customers page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating search by keyword input on customers page",
					"Able to display search by keyword input on customer page",
					"unable to display the search by keyword on customers page",
					Common.getscreenShotPathforReport("Failed to display search by keyword on customers page"));
			Assert.fail();

		}

	}

	public void ActionCTA() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//div[@class='action-select-wrap']");
			Common.clickElement("xpath", "//div[@class='action-select-wrap']");
			String Action = Common.findElement("xpath", "//div[@class='action-menu-items']//ul").getAttribute("class");
			Common.assertionCheckwithReport(Action.contains("active"),
					"Validating Action dropdown CTA on customers page",
					"Able to display Action dropdown CTA on customers page",
					"Successfully action dropdown CTA displayed on customers page",
					"Failed to Display action dropdown CTA on customers page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Action dropdown CTA on customers page",
					"Able to display Action dropdown CTA on customers page",
					"Unable to display action dropdown CTA on customers page",
					Common.getscreenShotPathforReport("Failed to Display action dropdown CTA on customers page"));
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
			Common.assertionCheckwithReport(Filters.contains("active"),
					"Validating the Filters Selector on customers page",
					"Able to display the Filters Selector on customers page",
					"Successfully filters selector is diplayed on customers page",
					"Failed to Display Filters selector on customers page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the Filters Selector on customers page",
					"Able to display the Filters Selector on customers page",
					"unable to display thr  filters selector on customers page",
					Common.getscreenShotPathforReport("Failed to Display Filters selector on customers page"));
			Assert.fail();

		}
	}

	public void ColumnsCTA() {
		// TODO Auto-generated method stub
		try {

			Sync.waitElementPresent("xpath", "//span[text()='Columns']");
			Common.clickElement("xpath", "//span[text()='Columns']");
			String Columns = Common.findElement("xpath", "(//div[@data-bind='collapsible'])[2]").getAttribute("class");
			Common.assertionCheckwithReport(Columns.contains("active"),
					"Validating the columns configuration CTA on customers page",
					"Able to display the columns Configuration CTA on customers page",
					"Successfully displayed Columns Configuration CTA on customers page",
					"Failed to display the Columns Configuration CTA on customers page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the columns configuration CTA on customers page",
					"Able to display the columns Configuration CTA on customers page",
					"Unable to  display the  Columns Configuration CTA on customers page",
					Common.getscreenShotPathforReport(
							"Failed to display the Columns Configuration CTA on customers page"));
			Assert.fail();

		}

	}

	public void DefaultView() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//span[text()='Default View']");
			Common.clickElement("xpath", "//span[text()='Default View']");
			String Defaultview = Common.findElement("xpath", "//div[@data-bind='collapsible']").getAttribute("class");
			Common.assertionCheckwithReport(Defaultview.contains("active"),
					"Validating the Default view Selector CTA on customers page ",
					"Able to display the Default view Selector CTA on customers page",
					"Successfully Default view selector CTA on customers page",
					"Failed to Display view Selector CTA on customers page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the Default view Selector CTA on customers page",
					"Able to display the Default view Selector CTA on customers page ",
					"unable to display Default view selector CTA on customers page",
					Common.getscreenShotPathforReport("Failed to Display view Selector CTA on customers page"));
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

	public void EditCTA() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//a[text()='Edit']");
			String Edit = Common.findElement("xpath", "//a[text()='Edit']").getText();
			System.out.println(Edit);
			Common.assertionCheckwithReport(Edit.equals("Edit"), "Validating the Edit CTA on customers page",
					"Able to display the Edit CTA on customers page ",
					"Successfully Dispaly Edit CTA on customers page", "Failed to Display Edit CTA on customers page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the Edit CTA on customers page ",
					"Able to display the Edit CTA on customers page ", "unable to Dispaly Edit CTA on customers page",
					Common.getscreenShotPathforReport("Failed to Display Edit CTA on customers page"));
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
			ExtenantReportUtils.addFailedLog("Validating customers filed page navigation ",
					"after clicking save button it will navigate Customer filed page",
					"Unable to  navigate to the Customer filed page",
					Common.getscreenShotPathforReport("Failed to navigate to the Customer filed page"));
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
			ExtenantReportUtils.addFailedLog("Validating the Sku ID in Shopping cart page",
					"User should be able see Sku ID in Shopping cart page  ",
					"Unable to display the SKU ID on the Shopping cart page",
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

	public void Click_customer() {
		try {
			Sync.waitPageLoad();
			Common.mouseOverClick("id", "menu-magento-customer-customer");
			String customers = Common.findElement("id", "menu-magento-customer-customer").getAttribute("class");
			Thread.sleep(3000);
			Common.assertionCheckwithReport(customers.contains("show"), "To verify the customer menu Field ",
					"After clicking the Customer menu it will display menu options ",
					"Successfully clicked on the customer menu and it displayed the Customer options",
					"Failed to click on the Customer menu");
			Sync.waitElementPresent("xpath", "//strong[text()='Customers']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To verify the customer menu Field ",
					"After clicking the Customer menu it will display menu options ",
					"Unable to  clicked on the customer menu and it displayed the Customer options",
					Common.getscreenShotPathforReport("Failed to click on the Customer menu"));
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
//		Common.clickElement("xpath", "//td[text()='"+segmentName+ "']");
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

	public void Pages() {

		try {
			Sync.waitElementPresent("xpath", "//span[text()='Pages']");
			Common.clickElement("xpath", "//span[text()='Pages']");
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Pages / Magento Admin"),
					"To validate the Admin is landing on the Content page After clicking on pages in the Content menu",
					"After clicking on pages in the content menu the Admin should navigate to pages",
					"Admin successfully navigate to the pages", "Admin failed to navigate to the pages");
			delete_existingpage("ProductcardTile");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate the Admin is landing on the Content page After clicking on pages in the Content menu",
					"After clicking on pages in the content menu the Admin should navigate to pages",
					"Admin failed to navigate to the pages",
					Common.getscreenShotPathforReport("Admin failed to navigate to the pages"));
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

	public void NewpageCTA() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent(30, "xpath", "//button[@title='Add New Page']");
			Common.clickElement("xpath", "//button[@title='Add New Page']");
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

			Common.assertionCheckwithReport(Common.getPageTitle().contains("New Page / Pages "),
					"Validating edit page bulider navigation ",
					"after clicking on edit page builder it will navigate edit page builder filed ",
					"Successfully navigate to the edit page builder filed ",
					"Failed to navigate to the edit page builder filed");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating edit page bulider navigation ",
					"after clicking on edit page builder it will navigate edit page builder filed ",
					"Unable to  navigate to the edit page builder filed",
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

	public void dragndrop_Promo_Content() {
		try {
			Common.scrollIntoView("xpath", "//span[text()='Promo Content (Product)']");
			WebElement element = Common.findElement("xpath", "//span[text()='Promo Content (Product)']");
			draganddropContentBlock(element);
			String blockname = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']/div")
					.getAttribute("data-content-type");
			Common.assertionCheckwithReport(blockname.equals("hot_product_promo"),
					"Validating promocontent Dragndrop operation", "Promo content dragndrop to content with options",
					"Successfully dragndrop the promocontent with options ", "failed to dragndrop the promocontent");
		} catch (Exception e) {

			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("Validating promocontent Dragndrop operation",
					"promocontent dragndrop to content with options",
					"Unable to dragndrop the promocontent with options ",
					Common.getscreenShotPathforReport("Failed to Dragndrop the promocontent"));
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
					"validating Edit option  on the page builder ",
					"after mouse over on the page builder edit option should be appear ",
					"Successfully edit option appeared in the pagebuilder",
					"Failed to appear edit option when we mouse over");

			Common.clickElement("xpath", "//i[@class='icon-admin-pagebuilder-systems']");
			String editpromo = Common.findElement("xpath", "//h1[@class='modal-title']").getText();
			System.out.println(editpromo);
			Common.assertionCheckwithReport(editpromo.contains("Edit Promo Content (Product)"),
					"validating Navigation to the edit promo page ",
					"after Clicking on the edit button it should be navigate to the edit promo page ",
					"Successfully it is navigated to edit promo page ", "Failed to navigate to the edit promo page");

		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validation Navigation to the edit promo page ",
					"after Clicking on the edit button it should be navigate to the edit promo page ",
					"Unable to navigate to the edit promo page ",
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
			Common.clickElement("xpath", "//span[text()='Background Repeat']");

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
	
	public void savecontent1(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Common.actionsKeyPress(Keys.PAGE_UP);
			Common.actionsKeyPress(Keys.PAGE_UP);
			Common.actionsKeyPress(Keys.PAGE_UP);
			Common.actionsKeyPress(Keys.PAGE_UP);
			Sync.waitElementPresent(30, "xpath", "//i[@title='Close Full Screen']");
			Common.clickElement("xpath", "//i[@title='Close Full Screen']");
			Common.clickElement("xpath", "//input[@name='title']");
			Common.textBoxInput("xpath", "//input[@name='title']", data.get(Dataset).get("pageTitle"));
			Common.actionsKeyPress(Keys.PAGE_UP);
			Common.clickElement("xpath", "(//span[text()='Save'])[2]");
			Common.clickElement("xpath", "//button[@id='save-button']");
			Sync.waitPageLoad(70);

			Sync.waitElementVisible("xpath", "//div[@data-ui-id='messages-message-success']");
			String savethepage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();

			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(savethepage.contains("You saved the page."),
					" To Validate the User needs to save the page", "User should able to save the page",
					"Sucessfully User saves the page", "Failed to save the page");

		} catch (Exception | Error e) {
			
			ExtenantReportUtils.addFailedLog(" To Validate the User needs to save the page",
					"User should able to save the page", "Unable to saves the page",
					Common.getscreenShotPathforReport("Failed to save the page"));
			Assert.fail();
			e.printStackTrace();
			
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

	public WebElement Cardtile_content() {

		WebElement element = Common.findElement("xpath", "//span[text()='Card Tiles']");
		Common.clickElement("xpath", "//span[text()='Card Tiles']");

		return element;

	}

	public WebElement PLPCMS_content() {

		WebElement element = Common.findElement("xpath", "//span[text()='PLP Block']");
		Common.clickElement("xpath", "//span[text()='PLP Block']");

		return element;

	}

	public WebElement CLPHero_content() {

		WebElement element = Common.findElement("xpath", "//span[text()='CLP Hero Banner']");
		Common.clickElement("xpath", "//span[text()='CLP Hero Banner']");

		return element;

	}

	public void draganddropContentBlock(WebElement source) {
		try {
			Common.dragdrop(source, "xpath", "//div[contains(@class,'pagebuilder-emp')]");

		} catch (Exception | Error e) {

		}
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

	public void website_image_verification_promocontent() {
		// TODO Auto-generated method stub
		try {

			Sync.waitElementPresent(40, "xpath", "//div[contains(@class,'c-promo-block__rig')]");
			String imageverification = Common.findElement("xpath", "//div[contains(@class,'c-promo-block__rig')]")
					.getAttribute("data-background-images");
			System.out.println(imageverification);
			Common.assertionCheckwithReport(imageverification.contains("Lotusqa"),
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
					"Successfully page has been closed after clicking on the close and cross button",
					"Failed to close the page after clicking on close and cross buttton");
		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validation the close and cross button funtionality ",
					"After clickng close and cross button the page should be close",
					"Unable to close the page after clicking on the close and cross button",
					Common.getscreenShotPathforReport(
							"Failed to close the page after clicking on close and cross buttton"));
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
			Thread.sleep(8000);
			String savethepage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
			Common.assertionCheckwithReport(savethepage.equals("You saved the page."),
					"Validating the User need to save the page", "User should able to save the page",
					"Sucessfully User saves the page", "Unable to save the page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the User need to save the page",
					"User should able to save the page", "Unable to save the page",
					Common.getscreenShotPathforReport("Failed to save the page"));
			Assert.fail();

		}

	}

	public void clone_image_frontend() {
		// TODO Auto-generated method stub
		try {
			Common.switchToSecondTab();
			Common.refreshpage();
			Sync.waitPageLoad();
			String id = Common.findElement("xpath", "(//div[contains(@class,'c-promo-block__right')])[1]")
					.getAttribute("data-pb-style");
			String image = Common.findElement("xpath", "//div[@data-pb-style='" + id + "']")
					.getAttribute("data-background-images");
			Common.assertionCheckwithReport(image.contains("Lotusqa1"),
					"validating the image uploading on front end page ",
					"Image should be upload for the front end page",
					"Successfully image uploaded on the front end page",
					"Failed to upload image on the front end page");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the image uploading on front end page ",
					"Image should be upload for front end page", "unable to upload image on the front end page",
					Common.getscreenShotPathforReport("Failed to upload image on the front end page"));
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

	public void edit_valueprop_banner_Two() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad(40);
			Sync.waitElementPresent(30, "xpath", "//div[@class='pagebuilder-content-type-wrapper']");
			String id = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']")
					.getAttribute("id");
//			Sync.waitElementPresent(30, "xpath","//div[@id='" + id+ "']//div[@class='pagebuilder-content-type-wrapper']");
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
			String image = Common.findElement("xpath", "(//img[@data-element='desktop_image'])[5]")
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

	public void close_promo_block() {

		// TODO Auto-generated method stub
		try {
			edit_promoBlock_one();
			Sync.waitElementPresent("xpath", "//button[@class='action-close']");
			Common.clickElement("xpath", "//button[@class='action-close']");
			Sync.waitPageLoad();
			String crossbutton = Common.findElement("xpath", "//aside[contains(@class,'modal-slide pa')]")
					.getAttribute("class");
			edit_promoBlock_one();
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

			ExtenantReportUtils.addFailedLog("validating the close and cross button funtionality ",
					"After clickng close and cross button the page should be close",
					"Unable to close after clicking on close and cross button", Common.getscreenShotPathforReport(
							"Failed to close the page after clicking on close and cross buttton"));
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

	public void website_verification_promoblock() {
		// TODO Auto-generated method stub

		try {
			String id = Common.findElement("xpath", "//div[@class='c-promo']//div[@data-background-type='image']")
					.getAttribute("data-pb-style");
			String websiteverification = Common.findElement("xpath", "//div[@data-pb-style='" + id + "']")
					.getAttribute("data-background-images");
			System.out.println(websiteverification);
			Common.assertionCheckwithReport(websiteverification.contains("Lotusqa"),
					"validating the images are displaying on fornt end ",
					"after Clicking on the link  it should be navigate to the respective page contains with 2 images",
					"Successfully it is navigated to respective page and contains 2 images ",
					"Failed to navigate to respective page and images are not appeared");
		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating the images are displaying on fornt end ",
					"after Click on link  it should be navigate to the respective page contains with 2 images",
					"Unable to navigated to the respective page and 2 images are not displayed",
					Common.getscreenShotPathforReport(
							"Failed to navigate to respective page and images are not appeared"));
			Assert.fail();

		}

	}

	public void clone_page_promo_block(String Dataset) {
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
			String id = Common.findElement("xpath", "//div[@class='c-promo']//div[@data-background-type='image']")
					.getAttribute("data-pb-style");
			Thread.sleep(4000);
			String imagefrontend = Common.findElement("xpath", "//div[@data-pb-style='" + id + "']")
					.getAttribute("data-background-images");
			System.out.println(imagefrontend);
			Common.assertionCheckwithReport(imagefrontend.contains("Lotusqa1"),
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
					"validating the image uploading on content for background image ",
					"Image should be upload for background image", "Successfully image uploaded in background image ",
					"Failed to upload image on the background image");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validation the image uploading on content for background image ",
					"Image should be upload for background image",
					"Unable to upload the image on the background image ",
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
		String deletemessage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
		System.out.println(deletemessage);
		Common.assertionCheckwithReport(
				Common.getPageTitle().equals("Pages / Magento Admin")
						&& deletemessage.equals("The page has been deleted."),
				"Validating page filed  navigation and customer deleted message",
				"after clicking save button it will navigate page filed and message should be displayed",
				"Successfully navigate to page filed and message is dispalyed", "Failed to navigate to page filed");
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
					"//div[@id=\"container\"]/div/div[5]/table/thead/tr/th");
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

	public void buttontext(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//span[@data-placeholder='Button text']");
			Common.clickElement("xpath", "//span[@data-placeholder='Button text']");
			Common.findElement("xpath", "//span[@data-placeholder='Button text']")
					.sendKeys(data.get(Dataset).get("Attachment"));
			String buttontext = Common.findElement("xpath", "//span[@data-placeholder='Button text']").getText();
			Common.assertionCheckwithReport(buttontext.equals("Fixed"),
					"Validating text entered in the button text button",
					"Text should be entered in the button text button",
					"Successfully text entered in the button text button",
					" user unable enter text in button text button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating text entered in the button text button",
					"Text should be entered in the button text button",
					"Failed to enter text in the button text button",
					Common.getscreenShotPathforReport("user unable enter text in button text button"));
			Assert.fail();

		}

	}

	public void CTA_content(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5000);
			String cta = Common.findElement("xpath", "(//div[contains(@class,'admin__fieldset-wrapper-content a')])[8]")
					.getAttribute("class");
			System.out.println(cta);
			if (cta.contains("show")) {
				Common.clickElement("xpath", "//input[@name='link_text']");
				Common.textBoxInput("xpath", "//input[@name='link_text']", data.get(Dataset).get("mobilelayout"));
				Common.clickElement("xpath", "//select[@name='button_type']");
				Common.dropdown("xpath", "//select[@name='button_type']", Common.SelectBy.TEXT,
						data.get(Dataset).get("CTA Type"));
				Common.clickElement("xpath", "//select[@name='link_url']");
				Common.dropdown("xpath", "//select[@name='link_url']", Common.SelectBy.TEXT,
						data.get(Dataset).get("VideoURL"));
				Common.clickElement("xpath", "//div[contains(@class,'url-input-e')]");
				Common.textBoxInput("xpath", "//input[@name='link_url[default]']", data.get(Dataset).get("URL"));
			} else {
				Common.javascriptclickElement("xpath", "//span[text()='CTA Elements']");
				Common.clickElement("xpath", "//input[@name='link_text']");
				Common.textBoxInput("xpath", "//input[@name='link_text']", data.get(Dataset).get("mobilelayout"));
				Common.clickElement("xpath", "//select[@name='button_type']");
				Common.dropdown("xpath", "//select[@name='button_type']", Common.SelectBy.TEXT,
						data.get(Dataset).get("CTA Type"));
				Common.clickElement("xpath", "//select[@name='link_url']");
				Common.dropdown("xpath", "//select[@name='link_url']", Common.SelectBy.TEXT,
						data.get(Dataset).get("VideoURL"));
				Common.clickElement("xpath", "//div[contains(@class,'url-input-e')]");
				Common.textBoxInput("xpath", "//input[@name='link_url[default]']", data.get(Dataset).get("URL"));
			}

			Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Sync.waitElementPresent("xpath", "//span[@data-placeholder='Button text']");
			Common.scrollIntoView("xpath", "//span[@data-placeholder='Button text']");
			String editpagebuilder = Common.findElement("xpath", "//span[@data-placeholder='Button text']").getText();
			Common.assertionCheckwithReport(editpagebuilder.equals("Stacked"),
					"Validating the text entered on the text button", "Text should be entered on the text button",
					"Successfully text entered on the text button", " user unable enter text on the text button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the text entered on the text button",
					"Text should be entered on the text button", "Failed to enter text on the text button",
					Common.getscreenShotPathforReport("user unable to enter text on the text button"));
			Assert.fail();
		}

	}

	public void CTA_product_content(String Dataset) {
		// TODO Auto-generated method stub
		try {
			
			Thread.sleep(5000);
			String cta = Common.findElement("xpath", "(//div[contains(@class,'admin__fieldset-wrapper-content a')])[8]")
					.getAttribute("class");
			System.out.println(cta);
			if (cta.contains("show")) {
				Common.clickElement("xpath", "//input[@name='link_text']");
				Common.textBoxInput("xpath", "//input[@name='link_text']", data.get(Dataset).get("CTA Link"));
				Common.clickElement("xpath", "//select[@name='button_type']");
				Common.dropdown("xpath", "//select[@name='button_type']", Common.SelectBy.TEXT,
						data.get(Dataset).get("CTA Type"));
				Common.clickElement("xpath", "//select[@name='link_url']");
				Common.dropdown("xpath", "//select[@name='link_url']", Common.SelectBy.TEXT,
						data.get(Dataset).get("heading"));
				Common.clickElement("xpath", "//div[@data-role='selected-option']");
				Common.scrollIntoView("xpath", "//div[@data-role='selected-option']");
				Common.clickElement("xpath", "//div[contains(@class,'admin__action-multiselect-s')]");
				Common.textBoxInput("xpath", "//input[@placeholder='Product Name or SKU']",
						data.get(Dataset).get("SKU"));
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//span[@data-bind='text: option.label']");
			} else {
				Sync.waitElementPresent("xpath", "//span[text()='CTA Elements']");
				Common.javascriptclickElement("xpath", "//span[text()='CTA Elements']");
				Common.clickElement("xpath", "//input[@name='link_text']");
				Common.textBoxInput("xpath", "//input[@name='link_text']", data.get(Dataset).get("CTA Link"));
				Common.clickElement("xpath", "//select[@name='button_type']");
				Common.dropdown("xpath", "//select[@name='button_type']", Common.SelectBy.TEXT,
						data.get(Dataset).get("CTA Type"));
				Common.clickElement("xpath", "//select[@name='link_url']");
				Common.dropdown("xpath", "//select[@name='link_url']", Common.SelectBy.TEXT,
						data.get(Dataset).get("heading"));
				Common.clickElement("xpath", "//div[@data-role='selected-option']");
				Common.scrollIntoView("xpath", "//div[@data-role='selected-option']");
				Common.clickElement("xpath", "//div[contains(@class,'admin__action-multiselect-s')]");
				Common.textBoxInput("xpath", "//input[@placeholder='Product Name or SKU']",
						data.get(Dataset).get("SKU"));
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//span[@data-bind='text: option.label']");
			}

			Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Sync.waitElementPresent("xpath", "(//span[@data-placeholder='Button text'])[2]");
			Common.scrollIntoView("xpath", "(//span[@data-placeholder='Button text'])[2]");
			String editpagebuilder = Common.findElement("xpath", "(//span[@data-placeholder='Button text'])[2]")
					.getText();
			Common.assertionCheckwithReport(editpagebuilder.equals("URL"),
					"To Validate the text is entered in the Button Text", "Text should be entered in the Button Text",
					"Successfully text entered in the Button Text", "Failed to enter text on the Button Text");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the text is entered in the Button Text",
					"Text should be entered in the Button Text", "Unable to enter text in the Button Text",
					Common.getscreenShotPathforReport("Failed to enter text on Button Text"));
			Assert.fail();

		}

	}

	public void website_button_verification_promoblock(String Dataset) {
		// TODO Auto-generated method stub
		try {
			String button1 = data.get(Dataset).get("URL");
//			String button2 = data.get(Dataset).get("SKU");
			Sync.waitElementPresent("xpath", "(//a[@class='a-btn pagebuilder-button-primary'])[1]");
			Common.scrollIntoView("xpath", "(//a[@class='a-btn pagebuilder-button-primary'])[1]");
			String websitebutton = Common.findElement("xpath", "(//a[@class='a-btn pagebuilder-button-primary'])[1]")
					.getAttribute("href");
			Common.assertionCheckwithReport(websitebutton.equals(button1),
					"To Validate the button text is present in front end",
					"Button text should be display in the front end",
					"Successfully button text is displayed in the front end",
					"Failed to Display button text on front end");

			Common.clickElement("xpath", "(//a[@class='a-btn pagebuilder-button-primary'])[2]");
			Sync.waitPageLoad();

//			String sku = Common.findElement("xpath", "//span[@class='a-product-attribute__value']").getText();
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("32-oz-wide-mouth-copper-brown.html"),
					"To validate button text is dispalyed in fornt end when we click on button text it navigates to the PDP ",
					"It should navigate to the PDP  when we click on the button text ",
					"Successfully naviagtes to the PDP when we click on the button text",
					"Failed to navigates to the PDP when we click on the button text");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate button text is dispalyed in fornt end when we click on button text it navigates to the PDP ",
					"It should navigate to the PDP  when we click on the button text ",
					"Unable to naviagte to the PDP when we click on the button text", Common.getscreenShotPathforReport(
							"Failed to navigates to the PDP when we click on the button text"));
			Assert.fail();

		}

	}

	public void newpageCTA() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent(30, "xpath", "//button[@title='Add New Page']");
			Common.clickElement("xpath", "//button[@title='Add New Page']");
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("New Page / Pages / Elements / Content / Magento Admin"),
					"Validating edit page bulider navigation ",
					"after clicking on edit page builder it will navigate edit page builder filed ",
					"Successfully navigate to the edit page builder filed ",
					"Failed to navigate to the edit page builder filed");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating edit page bulider navigation ",
					"after clicking on edit page builder it will navigate edit page builder filed ",
					"Unable to  navigate to the edit page builder filed",
					Common.getscreenShotPathforReport("Failed to navigate to the edit page builder filed"));
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

	public void Apply_filterpromo(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//button[@data-action='grid-filter-expand']");
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			Common.textBoxInput("xpath", "(//input[@id='fulltext'])[1]", data.get(Dataset).get("pageTitle"));
			Common.actionsKeyPress(Keys.ENTER);
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			String records = Common.findElement("xpath", "//div[@class='admin__control-support-text']").getText();
			if (records.equals("1 records found")) {
				Sync.waitElementPresent("xpath", "(//button[@class='action-select'])[3]");
				Common.clickElement("xpath", "(//button[@class='action-select'])[3]");

				Sync.waitElementPresent("xpath", "//a[text()='Edit']");
				Common.clickElement("xpath", "//a[text()='Edit']");

			} else {
				Assert.fail();
			}
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("New Customer / Customers / Customers / Magento Admin"),
					"Validating New customer page navigation ", "after clicking on it will navigate new Customer page",
					"Successfully navigate to new Customer page", "Failed to navigate to New Customer page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating New customer page navigation ",
					"after clicking on it will navigate new Customer page", "unable to navigate the new Customer page",
					"Failed to navigate to New Customer page");
			Assert.fail();

		}
	}

	public void clickAlternative() {
		// TODO Auto-generated method stub
		try {
			Common.switchToFirstTab();
			Contentpage();
			editpromocontent();

			Sync.waitElementPresent(20, "xpath", "//span[text()='Alternative']");
			Common.clickElement("xpath", "//span[text()='Alternative']");
			Sync.waitElementPresent("xpath", "//button[@id='save']");
			Common.clickElement("xpath", "//button[@id='save']");
			String alter = Common.findElement("xpath", "//li[@name='c-promo-block__container--alternate']").getText();
			Common.assertionCheckwithReport(alter.equals("Alternative"), "Validating alternative", "Click Alternatitve",
					"successfully clicked alternative", "fail to click alternative");
			Sync.waitElementPresent("xpath", "//button[@id='save']");
			Common.clickElement("xpath", "//button[@id='save']");
			// button[@id='save']
		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validation Navigation to the edit promo Blocker ",
					"after Click on edit button it should be navigate to the edit Blocker page ",
					"Successfully it is navigated to edit Blocker page ",
					Common.getscreenShotPathforReport("Failed to navigate to edit Blocker page"));
			Assert.fail();

		}
	}

	
	
	public void validate_block_buttontext(String Dataset) {
		// TODO Auto-generated method stub

		String buttontext = data.get(Dataset).get("Attachment");
		try {
			Sync.waitElementPresent("xpath", "//a[@class='a-btn pagebuilder-button-primary']");
			Common.scrollIntoView("xpath", "//a[@class='a-btn pagebuilder-button-primary']");
			String websitebutton = Common.findElement("xpath", "//a[@class='a-btn pagebuilder-button-primary']//span")
					.getText();
			Common.assertionCheckwithReport(websitebutton.equals(buttontext),
					"To Validate the button text is present in front end",
					"Button text should be display in the front end",
					"Successfully button text is displayed in the front end",
					"Failed to Display button text on front end");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the button text is present in front end",
					"Button text should be display in the front end",
					"Unable to see button text is displayed on the front end",
					Common.getscreenShotPathforReport("Failed to Display button text on front end"));
			Assert.fail();
		}

	}

	public WebElement Testimonials() {
		// TODO Auto-generated method stub

		WebElement element = Common.findElement("xpath", "//span[text()='Testimonials Product Carousel']");
		Common.clickElement("xpath", "//span[text()='Testimonials Product Carousel']");

		return element;

	}

	public void dragndrop_Testimonials() {
		try {
			Common.scrollIntoView("xpath", "//span[text()='Testimonials Product Carousel']");
			WebElement element = Common.findElement("xpath", "//span[text()='Testimonials Product Carousel']");
			draganddropContentBlock(element);
			String blockname = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']/div")
					.getAttribute("data-content-type");
			Common.assertionCheckwithReport(blockname.equals("hot_testimonials_product_carousel"),
					"Validating testimonials Dragndrop operation", "Testimonials dragndrop to content with options",
					"Successfully dragndrop the Testimonials with options ", "failed to dragndrop the Testimonials");
		} catch (Exception e) {

			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("Validating Testimonials Dragndrop operation",
					"Testimonials dragndrop to content with options",
					"Unable to dragndrop the Testimonials with options ",
					Common.getscreenShotPathforReport("Failed to Dragndrop the Testimonials"));
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

	public void Testimonial_author_one(String Dataset) {
		// TODO Auto-generated method stub
		String color = data.get(Dataset).get("Color");
		String title = data.get(Dataset).get("Author");
		String description = data.get(Dataset).get("Quote");

		try {
			Sync.waitElementPresent("xpath", "//span[text()='" + color + "']");
			Common.clickElement("xpath", "//span[text()='" + color + "']");
			Sync.waitElementPresent("xpath", "//div[@class='admin__field']//input[@name='title']");
			Common.textBoxInput("xpath", "//div[@class='admin__field']//input[@name='title']", title);
			Sync.waitElementPresent("xpath", "//select[@name='rating']");
			Common.dropdown("xpath", "//select[@name='rating']", Common.SelectBy.TEXT, data.get(Dataset).get("Rating"));
			Sync.waitElementPresent("xpath", "//textarea[@name='description']");
			Common.textBoxInput("xpath", "//textarea[@name='description']", description);
			image_Testimonial_one("promocontent");
			Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			String imagedescription = Common.findElement("xpath", "(//div[@data-element='description'])[1]").getText();
			String imagetitle = Common.findElement("xpath", "(//div[@data-element='title'])[1]").getText();
			Common.assertionCheckwithReport(imagedescription.equals(description) && imagetitle.equals(title),
					"To validate the description and the title of Testimonials Product Card one ",
					"After Click on the save button it should be navigate to the Edit Testimonials Product Card one and title and description should be display ",
					"Successfully Title and Description displayed on the Edit Testimonials Product Card one ",
					"Failed to navigate to the Edit Testimonials Product Card one and description and title is not displayed");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate the description and the title of Testimonials Product Card one ",
					"After Click on the save button it should be navigate to the Edit Testimonials Product Card one  and title and description should be display ",
					"Unable to navigate  Edit Testimonials Product Card one and description and title is not displayed",
					Common.getscreenShotPathforReport(
							"Failed to navigate to the Edit Testimonials Product Card one page and description and title is not displayed"));
			Assert.fail();
		}

	}

	public void image_Testimonial_one(String Dataset) {
		// TODO Auto-generated method stub
		String path = System.getProperty("user.dir") + ("\\src\\test\\resources\\TestData\\Admin\\Lotusqa.png");

		try {

			Sync.waitElementPresent(30, "xpath", "//input[@type='file']");
			String id = Common.findElement("xpath", "//input[@type='file']").getAttribute("id");
			Common.findElement("xpath", "//input[@id='" + id + "']").sendKeys(path);
			String image = Common.findElement("xpath", "//div[@class='file-uploader-filename']").getText();
			System.out.println(image);
			Common.assertionCheckwithReport(image.equals("Lotusqa.png"),
					"validating  the image uploading on content for Main image ",
					"Image should be upload for Main image", "Successfully image uploaded in Main image ",
					"Failed to upload image on the Main image");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validation the image uploading on content for Main image ",
					"Image should be upload for Main image", "Unable to upload the image for Main image ",
					Common.getscreenShotPathforReport("Failed to upload image for Main image"));
			Assert.fail();
		}

	}

	public void image_Testimonial_Two(String Dataset) {
		// TODO Auto-generated method stub
		String path = System.getProperty("user.dir") + ("\\src\\test\\resources\\TestData\\Admin\\Lotusqa1.png");
		try {

			Sync.waitElementPresent(30, "xpath", "//input[@type='file']");
			String id = Common.findElement("xpath", "//input[@type='file']").getAttribute("id");
			Common.findElement("xpath", "//input[@id='" + id + "']").sendKeys(path);
			String image = Common.findElement("xpath", "//div[@class='file-uploader-filename']").getText();
			System.out.println(image);
			Common.assertionCheckwithReport(image.equals("Lotusqa1.png"),
					"validating  the image uploading on content for Main image ",
					"Image should be upload for Main image", "Successfully image uploaded in Main image ",
					"Failed to upload image on the Main image");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validation the image uploading on content for Main image ",
					"Image should be upload for Main image", "Unable to upload the image for Main image ",
					Common.getscreenShotPathforReport("Failed to upload image for Main image"));
			Assert.fail();
		}
	}

	public void Testimonial_author_Two(String Dataset) {
		// TODO Auto-generated method stub
		String color = data.get(Dataset).get("Color");
		String title = data.get(Dataset).get("Author");
		String description = data.get(Dataset).get("Quote");

		try {
			Sync.waitElementPresent("xpath", "//span[text()='" + color + "']");
			Common.clickElement("xpath", "//span[text()='" + color + "']");
			Sync.waitElementPresent("xpath", "//div[@class='admin__field']//input[@name='title']");
			Common.textBoxInput("xpath", "//div[@class='admin__field']//input[@name='title']", title);
			Sync.waitElementPresent("xpath", "//select[@name='rating']");
			Common.dropdown("xpath", "//select[@name='rating']", Common.SelectBy.TEXT, data.get(Dataset).get("Rating"));
			Sync.waitElementPresent("xpath", "//textarea[@name='description']");
			Common.textBoxInput("xpath", "//textarea[@name='description']", description);
			image_Testimonial_Two("promocontent");
			Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Sync.waitElementPresent("xpath", "(//div[@data-element='description'])[2]");
			String imagedescription = Common.findElement("xpath", "(//div[@data-element='description'])[2]").getText();
			String imagetitle = Common.findElement("xpath", "(//div[@data-element='title'])[2]").getText();
			Common.assertionCheckwithReport(imagedescription.equals(description) && imagetitle.equals(title),
					"To validate the description and the title of Testimonials Product Card Three ",
					"After Click on the save button it should be navigate to the Edit Testimonials Product Card Three and title and description should be display ",
					"Successfully Title and Description displayed on the Edit Testimonials Product Card Three ",
					"Failed to navigate to the Edit Testimonials Product Card Three and description and title is not displayed");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate the description and the title of Testimonials Product Card Three ",
					"After Click on the save button it should be navigate to the Edit Testimonials Product Card Three  and title and description should be display ",
					"Unable to navigate  Edit Testimonials Product Card Three and description and title is not displayed",
					Common.getscreenShotPathforReport(
							"Failed to navigate to the Edit Testimonials Product Card Three page and description and title is not displayed"));
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

	public void Testimonial_author_Three(String Dataset) {
		// TODO Auto-generated method stub
		String color = data.get(Dataset).get("Color");
		String title = data.get(Dataset).get("Author");
		String description = data.get(Dataset).get("Quote");

		try {
			Sync.waitElementPresent("xpath", "//span[text()='" + color + "']");
			Common.clickElement("xpath", "//span[text()='" + color + "']");
			Sync.waitElementPresent("xpath", "//div[@class='admin__field']//input[@name='title']");
			Common.textBoxInput("xpath", "//div[@class='admin__field']//input[@name='title']", title);
			Sync.waitElementPresent("xpath", "//select[@name='rating']");
			Common.dropdown("xpath", "//select[@name='rating']", Common.SelectBy.TEXT, data.get(Dataset).get("Rating"));
			Sync.waitElementPresent("xpath", "//textarea[@name='description']");
			Common.textBoxInput("xpath", "//textarea[@name='description']", description);
			image_Testimonial_Three("promocontent");
			Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Common.clickElement("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");
			Sync.waitElementPresent("xpath", "(//div[@data-element='description'])[3]");
			String imagedescription = Common.findElement("xpath", "(//div[@data-element='description'])[3]").getText();
			String imagetitle = Common.findElement("xpath", "(//div[@data-element='title'])[3]").getText();
			Common.assertionCheckwithReport(imagedescription.equals(description) && imagetitle.equals(title),
					"To validate the description and the title of Testimonials Product Card Two ",
					"After Click on the save button it should be navigate to the Edit Testimonials Product Card Two and title and description should be display ",
					"Successfully Title and Description displayed on the Edit Testimonials Product Card Two ",
					"Failed to navigate to the Edit Testimonials Product Card Two and description and title is not displayed");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate the description and the title of Testimonials Product Card Two ",
					"After Click on the save button it should be navigate to the Edit Testimonials Product Card Two  and title and description should be display ",
					"Unable to navigate  Edit Testimonials Product Card Two and description and title is not displayed",
					Common.getscreenShotPathforReport(
							"Failed to navigate to the Edit Testimonials Product Card Two page and description and title is not displayed"));
			Assert.fail();
		}

	}

	public void image_Testimonial_Three(String Dataset) {
		// TODO Auto-generated method stub

		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//label[text()='Select from Gallery']");
			Sync.waitPageLoad();
			String gallery = Common.findElement("xpath", "//span[contains(@class,'fileinput')]//span").getText();
			Common.assertionCheckwithReport(gallery.equals("Upload Images"),
					"To validate the page navigated to the insert pages when we click on the upload from gallery ",
					"After Click on the upload from the gallery it should navigate to the insert pages",
					"Successfully It is navigated to the insert pages after clicking on the upload from gallery ",
					"Failed to Navigate to the insert pages after clicking on the upload from the gallery");
			Common.clickElement("xpath", "//img[@alt='demo-desktop.png']");
			Common.clickElement("xpath", "//span[text()='Add Selected']");
			Sync.waitElementPresent("xpath", "//div[@class='file-uploader-filename']");
			String imageupload = Common.findElement("xpath", "//div[@class='file-uploader-filename']").getText();
			Common.assertionCheckwithReport(imageupload.equals("demo-desktop.png"),
					"validating the image uploading on content in Main image from the gallery ",
					"Image should be upload for Main image from the gallery",
					"Successfully image uploaded for the Main image from the gallery ",
					"Failed to upload image from the gallery");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the image uploading on content in Main image from the gallery ",
					"Image should be upload for Main image from the gallery",
					"Unable to uplaod the image for the Main image from the gallery ",
					Common.getscreenShotPathforReport("Failed to upload image from the gallery"));
			Assert.fail();

		}

	}

	public void Testimonial_frontend(String Dataset) {
		// TODO Auto-generated method stub
		String Description = data.get(Dataset).get("Quote");
		try {
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "(//div[@class='m-media-card-testimonial__testimonial'])[1]");
			String description = Common
					.findElement("xpath", "(//div[@class='m-media-card-testimonial__testimonial'])[1]").getText();
			String Title = Common.findElement("xpath", "(//div[@class='m-media-card-testimonial__author'])[2]")
					.getText();
			String Rating = Common.findElement("xpath", "(//span[@class='m-readonly-star-rating__rating'])[3]")
					.getText();
			Common.assertionCheckwithReport(
					description.equals(Description) && Rating.contains("1") && Title.equals("Lotusqa"),
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

	public void verficationAlternative() {
		// TODO Auto-generated method stub
		try {

			Common.switchToSecondTab();
			Common.refreshpage();
			Sync.waitPageLoad();

			String Block = Common.findElement("xpath", "//div[@class='c-promo-block__left']").getText();

			Common.assertionCheckwithReport(Block.equals("qaFlask"), "Validating promo block operation",
					"promoblocker content", "successfully left promoblock", "fail to move left promoblock");

			// Common.clickElement("xpath", "(//button[@class='action-select'])[3]");
			// Sync.waitElementPresent("xpath\", \"(//button[@class='action-select'])[3]");

		} catch (Exception | Error e) {

			e.printStackTrace();

			report.addFailedLog("validation Navigation to the edit promo Blocker ",
					"after Click on edit button it should be navigate to the edit Blocker page ",
					"Successfully it is navigated to edit Blocker page ",
					Common.getscreenShotPathforReport("Failed to navigate to edit Blocker page"));
			Assert.fail();
		}

	}

	public void edit_promoContentProduct() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(3000);

			Common.mouseOverClick("xpath", "//a[@class='edit-content-type']");

			Thread.sleep(5000);
		} catch (Exception | Error e) {
			e.printStackTrace();

			report.addFailedLog("validation Navigation to the edit promo Blocker ",
					"after Click on edit button it should be navigate to the edit Blocker page ",
					"Successfully it is navigated to edit Blocker page ",
					Common.getscreenShotPathforReport("Failed to navigate to edit Blocker page"));
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

	public void edit_promoContentProduct_CTAElements(String DataSet) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
			String name = Common.getText("xpath", "//span[text()='CTA Elements']");
			System.out.println(name);

			Common.scrollIntoView("xpath", "//span[text()='Background Repeat']");
			Sync.waitElementPresent("xpath", "//span[text()='CTA Elements']");
			Thread.sleep(1000);
			Common.clickElement("xpath", "//span[text()='CTA Elements']");
			Common.clickElement("xpath", "//span[text()='CTA Elements']");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//input[@name='link_text']");
			Common.textBoxInput("xpath", "//input[@name='link_text']", data.get(DataSet).get("CTAText"));
			Thread.sleep(2000);
			Common.textBoxInput("xpath", "//input[@name='link_url[default]']", data.get(DataSet).get("CTAurl"));
			Thread.sleep(4000);
			// Sync.waitElementClickable("xpath",
			// "(//label[@class='admin__field-label'])[6]");
			Common.clickElement("xpath", "(//label[@class='admin__field-label'])[6]");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "(//span[text()='Save'])[2]");
			Common.clickElement("xpath", "(//span[text()='Save'])[2]");
			Thread.sleep(5000);

			report.addPassLog("validation to the edit content promo Blocker",
					"after giving data it should show the data", "Successfully data is populated and saved",
					Common.getscreenShotPathforReport("Succesfully data saved"));
		} catch (Exception | Error e) {
			e.printStackTrace();

			report.addFailedLog("validation to the edit content promo Blocker ",
					"after giving data it should show the data", "Successfully data is populated and Saved",
					Common.getscreenShotPathforReport("Failed to save the data"));
			Assert.fail();

		}
	}

	public void promoContentProduct_Save(String DataSet) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//i[@title='Close Full Screen']");
			Common.clickElement("xpath", "//i[@title='Close Full Screen']");
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "(//input[@name='title'])[1]");
			Common.textBoxInput("xpath", "(//input[@name='title'])[1]", data.get(DataSet).get("pageTitle"));
			Thread.sleep(3000);

			Sync.waitElementPresent("xpath", "(//button[@title='Save'])[1]");
			Common.clickElement("xpath", "(//button[@title='Save'])[1]");
			Thread.sleep(5000);

			Thread.sleep(5000);
			report.addPassLog("validation to the edit content promo Blocker",
					"after giving data it should show the data", "Successfully data is populated",
					Common.getscreenShotPathforReport("Succesfully data entered"));

		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validation to the edit content promo Blocker ",
					"after giving data it should save the data", "Successfully data is Saved",
					Common.getscreenShotPathforReport("Failed to Save the data"));
			Assert.fail();

		}
	}

	public void website_verification_CTAButton() {
		// TODO Auto-generated method stub
		try {

			Sync.waitElementPresent(40, "xpath", "//a[@class='a-btn pagebuilder-button-primary']");
			Common.clickElement("xpath", "//a[@class='a-btn pagebuilder-button-primary']");
			Thread.sleep(5000);
			String headingverification = Common.getText("xpath", "//h2[@class='c-promo-block__heading']");
			System.out.println(headingverification);
			Common.assertionCheckwithReport(headingverification.contains("QA Test/Promo Block-Content"),
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

	public void close_Editblock(String dataSet) {
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
			Buttonlink("ProductcardTile");
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

	public void buttontype(String dataSet) {
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.dropdown("xpath", "//Select[@name='button_type']", SelectBy.TEXT, data.get(dataSet).get("Buttontype"));
	}

	public void Buttonlink(String dataSet) throws Exception {
		String buttonlinknavugation = data.get(dataSet).get("ButtonLinknavigation");
		try {
			Common.scrollIntoView("xpath", "//Select[@name='button_link']");
			Common.dropdown("xpath", "//Select[@name='button_link']", SelectBy.TEXT, buttonlinknavugation);
			if (buttonlinknavugation.contains("URL")) {
				String URL = data.get(dataSet).get("ButtonlinkURL");
				Common.textBoxInput("xpath", "button_link[default]", URL);

			} else if (buttonlinknavugation.contains("Product")) {
				String Product = data.get(dataSet).get("Buttonlinkproduct").toUpperCase();
				Sync.waitElementVisible("xpath", "(//div[text()='Select...'])[1]");
				Common.clickElement("xpath", "(//div[text()='Select...'])[1]");
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

	public void edit_cardtile_image(String dataSet, int i) {

		String image = data.get(dataSet).get("image");
		try {
			System.out.println(i);
			Sync.waitElementPresent("xpath", "(//label[text()='Select from Gallery'])["+i+"]");
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

			Common.assertionCheckwithReport(imagename.contains(image),
					"validation the image uploading on content  ",
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

	public void verifycategoriesdisplay(String dataSet) throws Exception {
try {
		int categories = Common.findElements("xpath", "//div[contains(@data-element,'card_tiles')]/a/span").size();
		System.out.println(categories);

		String[] categoryselected = data.get(dataSet).get("Categorydisplay").split(",");

		for (int i = 1; i < categories && i < categoryselected.length; i++) {

			Sync.waitElementVisible("xpath", "(//div[contains(@data-element,'card_tiles')]/a/span)[" + i + "]");
			Common.scrollIntoView("xpath", "(//div[contains(@data-element,'card_tiles')]/a/span)[" + i + "]");
			String categorytile = Common
					.findElement("xpath", "(//div[contains(@data-element,'card_tiles')]/a/span)[" + i + "]").getText();
			Common.mouseOverClick("xpath", "(//div[contains(@data-element,'card_tiles')]/a/span)[" + i + "]");
			Sync.waitPageLoad();

			Sync.waitElementPresent("xpath", "//ol[@class='m-breadcrumb__list']");
			Common.scrollIntoView("xpath", "//ol[@class='m-breadcrumb__list']");

			
			
			Common.assertionCheckwithReport(
					categorytile.contains(categoryselected[i-1])
							&& Common.getPageTitle().contains(categoryselected[i - 1]),
					"To validate the user is navigating to the respective category when clicked on the category in new page",
					"Usre should navigate to the Respective category"+ categoryselected[i - 1], "Usre navigated to respective category"+ categoryselected[i - 1], "User failed to navigate to the category"+ categoryselected[i - 1]);
			BaseDriver.getDriver().navigate().back();
		}}
catch(Exception e) {
e.printStackTrace();

ExtenantReportUtils.addFailedLog("To validate the user is navigating to the respective category when clicked on the category in new page",
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
				String productnamePDP = Common.findElement("xpath", "//h1[@class='m-product-overview__title']")
						.getText();

				Sync.waitElementVisible("xpath", "//h1[@class='m-product-overview__title']");
				Common.assertionCheckwithReport(
						producturl.contains(productnames[i - 1]) && productname.contains(productnamePDP),
						"To validate the product navigation to the respectiuve PDP page",
						"When clicked on the product image it should navigate to the PDP page",
						"Successfully landed on the respective PDP page",
						"Failed to land on the pdp page of the product");

				BaseDriver.getDriver().navigate().back();

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

	public void Navigate_adminpage() {
		Common.switchToFirstTab();
		Contentpage();

	}

	public void Clone(String string) {
		String component = data.get(string).get("component");
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

			String classattribute = data.get(string).get("attribute");
			int components = Common.findElements("xpath", "//div[contains(@class,'" + classattribute + "')]").size();
			System.out.println(components);
			Common.scrollIntoView("xpath", "//div[contains(@class,'" + classattribute + "')]");

			Common.assertionCheckwithReport(components > 0, "To validate the clone functionality",
					"Should clone the product tile ", "Clone of the card tile is successfull",
					"clone unctionality failed");
			savecontent(string);
			Thread.sleep(3000);
			openwebsite("ProductcardTile");
			verify_Productcard_clonefunctionality_website("ProductcardTile");
			openwebsite("OXOproducttile");
			verify_Productcard_clonefunctionality_website("OXOproducttile");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating clone functionality",
					"User should able to clone the blocks or content", "Admin failed to clone the content",
					Common.getscreenShotPathforReport("Failed to clone the content of the page"));

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
			openwebsite("ProductcardTile");
			verifydeletefunctionality_website();
			openwebsite("OXOproducttile");
			verifydeletefunctionality_website();

		} catch (Exception e) {
			e.printStackTrace();
			invalidformkey("ProductcardTile");
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

	public void verifydeletefunctionality_website() {

		try {

			int blocks = Common.findElements("xpath", "//div[@data-content-type='hot_card_tiles']").size();
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

	public void edit_titlepromocontent_color(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Common.textBoxInput("xpath", "(//input[@name='title'])[2]", data.get(Dataset).get("pageTitle"));

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

	public void dragndrop_CLP_Hero_Content() {
		try {
			WebElement element = Common.findElement("xpath", "//span[text()='CLP Hero Banner']");
			draganddropContentBlock(element);
			String blockname = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']/div")
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

	public void CLP_herocomponents(String dataSet) {

		String path = System.getProperty("user.dir") + ("\\src\\test\\resources\\TestData\\Admin\\Lotusqa.png");

		try {

			Sync.waitElementClickable("xpath", "//h2[contains(@class,'laceholder-text')]");
			Common.textBoxInput("xpath", "//h2[contains(@class,'laceholder-text')]", data.get(dataSet).get("Tiletext"));
			Common.textBoxInput("xpath", "//h3[contains(@class,'description')]", data.get(dataSet).get("Description"));
			Common.findElement("xpath", "//div[contains(@class,'image-uploader-container')]").sendKeys(path);
			String image = Common.findElement("xpath", "//div[@class='pagebuilder-image']/img").getText();
			System.out.println(image);
			Common.assertionCheckwithReport(image.equals("Lotusqa.png"),
					"validation the image uploading on content for background image ",
					"Image should be upload for background image", "Successfully image uploaded in background image ",
					"Failed to upload image on the background image");

			savecontent("CLPHerobanner");
			openwebsite("CLPHerobanner");
			frontend_Verification("CLPHerobanner");
			openwebsite("OXOCLPHerobanner");
			frontend_Verification("CLPHerobanner");
		} catch (Exception e) {
			e.printStackTrace();
			report.addFailedLog("To validate the image element in the CLP hero banner",
					"Admin should be able to add the image in the CLP hero banner",
					"Admin failed to add the image in the CLP hero banner",
					Common.getscreenShotPathforReport("Failed to add the image in the content of the page"));

			Assert.fail();

		}

	}

	public void frontend_Verification(String dataSet) {
		String title = data.get(dataSet).get("Tiletext");
		String Description = data.get(dataSet).get("Description");
		String images = data.get(dataSet).get("image");

		try {
			Sync.waitElementVisible("xpath", "//h2[@class='c-clp-hero__headline']");
			String Title = Common.findElement("xpath", "//h2[@class='c-clp-hero__headline']").getText();
			Sync.waitElementVisible("xpath", "//h3[@class='c-clp-hero__description']");
			String description = Common.findElement("xpath", "//h3[@class='c-clp-hero__description']").getText();
			Sync.waitElementVisible("xpath", "//figure[@class='c-clp-hero__image']/img");
			String image = Common.findElement("xpath", "//figure[@class='c-clp-hero__image']/img").getAttribute("src");

			Common.assertionCheckwithReport(
					Title.contains(title) && description.contains("Description") && image.contains(images),
					"To validate the CLP hero banner", "All the elements Should be visible on the front end",
					"Elemnts are visible on the front end", "Failed to display the CLP hero elements");

		} catch (Exception e) {
			e.printStackTrace();
			report.addFailedLog("To validate the CLP Hero baner components",
					"User should able to validate the CLP hero banner components",
					"Admin failed to validate the CLP hero banner components",
					Common.getscreenShotPathforReport("CLP hero banner components unsuccessfull"));

			Assert.fail();
		}

	}

	public void inputcontent(String string) {
		try {
			Sync.waitElementClickable("xpath", "(//input[@name='title'])[2]");
			Common.textBoxInput("xpath", "(//input[@name='title'])[2]", data.get(string).get("title"));
			Common.dropdown("xpath", "//select[@name='heading_type']", SelectBy.TEXT,
					data.get(string).get("headingtype"));
			Common.textBoxInput("xpath", "//input[@name='description']", data.get(string).get("Description"));
			Common.dropdown("xpath", "//select[@name='description_type']", SelectBy.TEXT,
					data.get(string).get(" Description Type"));
			Common.scrollIntoView("xpath", "//div[@class='page-actions floating-header']//span[text()='Save']");
			Sync.waitElementClickable("xpath", "//div[@class='page-actions floating-header']//span[text()='Save']");
			Thread.sleep(5000);
			Common.mouseOverClick("xpath", "//div[@class='page-actions floating-header']//button[@id='save']");

		} catch (Exception e) {
			e.printStackTrace();
			report.addFailedLog("To validate the CLP Hero baner content",
					"User should able to validate the CLP hero banner content",
					"Admin failed to validate the CLP hero banner content",
					Common.getscreenShotPathforReport("CLP hero banner content unsuccessfull"));

			Assert.fail();

		}
	}

	public void Clone_CLPHERO_Banner(String string) {
		String component = data.get(string).get("component");
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

			int components = Common.findElements("xpath", "//div[@data-content-type='hot_clp_banner']").size();
			System.out.println(components);
			Common.scrollIntoView("xpath", "//div[@data-content-type='hot_clp_banner']");

			Common.assertionCheckwithReport(components > 0, "To validate the clone functionality",
					"Should clone the product tile ", "Clone of the card tile is successfull",
					"clone unctionality failed");
			savecontent(string);
			openwebsite("CLPHerobanner");
			verify_Productcard_clonefunctionality_website("CLPHerobanner");
			openwebsite("OXOCLPHerobanner");
			verify_Productcard_clonefunctionality_website("OXOCLPHerobanner");

		} catch (Exception e) {
			e.printStackTrace();
			report.addFailedLog("To validate the CLP Hero baner components clone functionality",
					"User should able to validate the CLP hero banner clone functionality",
					"Admin failed to validate the CLP hero banner components clone functionality",
					Common.getscreenShotPathforReport("CLP hero banner components clone functionality unsuccessfull"));

			Assert.fail();

		}

	}

	public void verify_Productcard_clonefunctionality_website(String dataSet) {
		// String value = data.get(dataSet).get("attribute");
		try {
			Sync.waitElementClickable("xpath", "//div[@data-content-type='hot_card_tiles']");
			int components = Common.findElements("xpath", "//div[@data-content-type='hot_card_tiles']").size();
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

	public void delete_existingpage(String dataSet) {
		String pagename = data.get(dataSet).get("pageTitle");
		try {

			Sync.waitElementPresent(30, "xpath", "//input[@placeholder='Search by keyword']");
			Thread.sleep(3000);
			if (Common.isElementDisplayedonPage(30, "xpath", "//button[@class='action-remove']")) {
				Common.mouseOverClick("xpath", "//button[@class='action-remove']");
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			} else {
				System.out.println("no Active filters found");
			}
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

			if (pages > 0) {

				for (int i = 1; i <= pages; i++) {
					Sync.waitElementVisible(30, "xpath", "(//button[text()='Select'])[" + i + "]");
					Common.javascriptclickElement("xpath", "(//button[text()='Select'])[" + i + "]");
					Sync.waitElementVisible(30, "xpath", "(//a[text()='Delete'])[" + i + "]");
					Common.mouseOverClick("xpath", "(//a[text()='Delete'])[" + i + "]");
					Sync.waitElementVisible(30, "xpath", "//button[@class='action-primary action-accept']");
					Common.mouseOverClick("xpath", "//button[@class='action-primary action-accept']");
					Sync.waitPageLoad();
					String successmessage = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']")
							.getText();
					Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

					Common.assertionCheckwithReport(successmessage.equals(data.get(dataSet).get("Adminsuccessmesaage")),
							"To validate the Admin deletes the page and the success message is displayed",
							"Admin Should successfully deletes the page and success message is displayed",
							"Admin deleted the page successfully and success message is displayed",
							"Admin failed to delete the page");

				}

			} else {

				String nopages = Common.findElement("xpath", "//tr[@class='data-grid-tr-no-data']/td").getText();
				System.out.println(nopages);
			}

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate the Admin deletes the page and the success message is displayed",
					"Admin Should successfully deletes the page and success message is displayedUser should able Dragndrop cardtile",
					"Admin failed to delete the page", Common.getscreenShotPathforReport("Failed to delete the page"));

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

			ExtenantReportUtils.addFailedLog("Validating card tile Dragndrop operation",
					"User should able Dragndrop cardtile", "Sucessfully Dragndrop cardtile",
					Common.getscreenShotPathforReport("Failed to Dragndrop cardtile"));
			Assert.fail();

		}
	}

	public void CMS_ImageElement(String DataSet) {
		// TODO Auto-generated method stub
		String image = data.get(DataSet).get("image");
		String image2 = data.get(DataSet).get("image2");
		String altatt = data.get(DataSet).get("Productname");
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

			Common.scrollIntoView("xpath", "(//button//span[contains(text(),'Save')])[2]");
			Common.clickElement("xpath", "(//button//span[contains(text(),'Save')])[2]");

		} catch (Exception | Error e) {
			e.printStackTrace();

			report.addFailedLog("validation Image upload  ", "Image should be uploaded",
					"Successfully image is uploaded",
					Common.getscreenShotPathforReport("Failed to navigate to edit promoBlocker page"));
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
			Common.textBoxInput("xpath", "(//input[@type='text'])[2]", data.get(DataSet).get("categoryname"));
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
			String delete = Common.findElement("xpath", "//div[@class='message message-success success']").getText();
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

	/*
	 * 12-08-22-Manoj & Saikumar
	 *
	 *
	 *
	 */

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
			Common.assertionCheckwithReport(advancedsection.contains("show"), "to validate the Padding and margins",
					"Pad" + "paddding and margin should apply successfully", "Paddding and margion applied",
					"Failed to apply padding");

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
			
		} catch (Exception e) {
			e.printStackTrace();
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

	/*
	 * 12-08-22- Saikumar
	 *
	 *
	 *
	 */

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

	public void edit_productcard_image(String datSet) {

		String image = data.get(datSet).get("image");
		try {
			Sync.waitElementPresent("xpath", "(//label[text()='Select from Gallery'])[7]");
			Common.javascriptclickElement("xpath", "(//label[text()='Select from Gallery'])[7]");

			Sync.waitElementInvisible("xpath", "//div[@class='loading-mask' and @style='display: none;']");
			Common.isElementVisibleOnPage(30, "xpath", "//div[@id='contents']");
			Common.scrollIntoView("xpath", "//small[text()='" + image + "']");
			Common.javascriptclickElement("xpath", "//small[text()='" + image + "']");
			Common.actionsKeyPress(Keys.HOME);
			Sync.waitElementPresent("xpath", "//span[text()='Add Selected']");
			Common.javascriptclickElement("xpath", "//span[text()='Add Selected']");
			Sync.waitElementInvisible(30, "xpath", "//div[@class='loading-mask' and @style='display: none;']");
			Thread.sleep(6000);
			Common.scrollIntoView("xpath", "//a[@class='image-uploader-preview-link']/img");
			String imagename = Common.findElement("xpath", "//a[@class='image-uploader-preview-link']/img")
					.getAttribute("alt");

			Common.assertionCheckwithReport(imagename.equals(image),
					"validation the image uploading for the value prop card ",
					"Image should be upload for value prop card", "Successfully image uploaded for value prop card",
					"Failed to upload image on the value prop card");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("validation the image uploading on content for fallback image ",
					"Image should be upload for fallback image", "Successfully image uploaded the fallback image ",
					Common.getscreenShotPathforReport("Failed to upload image for fallback image"));
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
			String id = Common.findElement("xpath", "//div[@class='pagebuilder-content-type-wrapper']")
					.getAttribute("id");
			Sync.waitPageLoad();
			Common.mouseOverClick("xpath", "//div[@id='" + id + "']/div/div/div/ul/li[3]/a/i");

			String editbanner = Common.findElement("xpath", "//h1[@class='modal-title']").getText();
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");

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

	public void edit_HeroBanner_ContentAlignment(String Dataset) {

		String alignment = data.get(Dataset).get("Alignment");
		String textcolor = data.get(Dataset).get("TextColor");
		String style = data.get(Dataset).get("Style");
		try {

			Common.scrollIntoView("xpath", "//span[text()='" + alignment + "']");
			Sync.waitElementPresent("xpath", "//span[text()='" + alignment + "']");
			Common.clickElement("xpath", "//span[text()='" + alignment + "']");
			Common.clickElement("xpath", "//span[text()='" + textcolor + "']");
			Common.clickElement("xpath", "//span[text()='" + style + "']");
			Common.assertionCheckwithReport(alignment.contains("Alignment"),
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

	public void website_verification_Herobanner() {

		try {

			Sync.waitElementPresent(40, "xpath", "//div[@data-content-type='hot_hero_banner']");
//		Common.clickElement("xpath", "//a[@class='a-btn pagebuilder-button-primary']");
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
			Buttonlink("ProductcardTile");
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

	public void verifybuttonlink_product(String dataSet) {
		try {
			Common.scrollIntoView("xpath", "//span[@data-element='feature_card_button_text']");
			Common.javascriptclickElement("xpath", "//span[@data-element='feature_card_button_text']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.switchWindows();
			Sync.waitElementPresent("xpath", "//p[@class='m-breadcrumb__text']");
			String pagetitle = Common.getPageTitle();
			System.out.println(pagetitle);
			String title = data.get(dataSet).get("Buttonlinkproduct");
			System.out.println(title);
			
			Common.assertionCheckwithReport(pagetitle.contains(title) || pagetitle.contains(title.toLowerCase()),
					"To validate the button link is navigating to a product page",
					"Button link should navigate to product page", "Button link navigated to product",
					"Failed button link navigation to product");


		} catch (Exception e) {
			e.printStackTrace();
			
			ExtenantReportUtils.addFailedLog("To validate the button link is navigating to a product page",
					"Button link should navigate to product page", "Button link is navigating to product page",
					Common.getscreenShotPathforReport("Failed to button link navigation to product page"));

			Assert.fail();

		}
	}

	public void verifybuttonlink_page(String dataSet) {
		try {
			Common.scrollIntoView("xpath", "//span[@data-element='feature_card_button_text']");
			Common.javascriptclickElement("xpath", "//span[@data-element='feature_card_button_text']");
			Sync.waitPageLoad();
			Sync.waitElementVisible("xpath", "//p[@class='m-breadcrumb__text']");
			Common.switchWindows();
			Common.assertionCheckwithReport(Common.getPageTitle().contains(data.get(dataSet).get("Buttonlinkpage")),
					"To validate the button link is navigating to a page", "Button link should navigate to page",
					"Button link navigated to page", "Failed to button link navigation to page");
	
			
		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the button link is navigating to a page",
					"Button link should navigate to page", "Button link failed navigating to page",
					Common.getscreenShotPathforReport("Failed to button link navigation to page"));
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

			Sync.waitElementPresent(40, "xpath", "//a[@class='a-btn pagebuilder-button-primary']");
			Common.clickElement("xpath", "//a[@class='a-btn pagebuilder-button-primary']");
			Thread.sleep(5000);
			Common.switchWindows();
			Sync.waitPageLoad();
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
            Common.refreshpage();
            Sync.waitElementPresent(40, "xpath", "//a[@class='c-product-carousel-transparent__cta pagebuilder-button-primary btn-visible']");
			Common.clickElement("xpath", "//a[@class='c-product-carousel-transparent__cta pagebuilder-button-primary btn-visible']");
			Common.switchWindows();
			Thread.sleep(5000);
			String headingverification = Common.getText("xpath", "//div[@class='c-product-overview u-container']");
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
            Common.refreshpage();
            Sync.waitElementPresent(40, "xpath", "//a[@class='c-product-carousel-transparent__cta pagebuilder-button-primary btn-visible']");
			Common.clickElement("xpath", "//a[@class='c-product-carousel-transparent__cta pagebuilder-button-primary btn-visible']");
			Common.switchWindows();
			Thread.sleep(5000);
			String shop = Common.getText("xpath", "//h1[@id='page-title-heading']");
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
            Common.refreshpage();
            Sync.waitElementPresent(40, "xpath", "//a[@class='c-product-carousel-transparent__cta pagebuilder-button-primary btn-visible']");
			Common.clickElement("xpath", "//a[@class='c-product-carousel-transparent__cta pagebuilder-button-primary btn-visible']");
			Common.switchWindows();
			Thread.sleep(5000);
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

	public void webiste_title() {
		// TODO Auto-generated method stub
		
		try {

			Sync.waitElementPresent("xpath", "//div[@class='u-container c-product-carousel-transparent__carousel']");
			
			String headingverification = Common.getText("xpath", "//div[@class='u-container c-product-carousel-transparent__carousel']");
			System.out.println(headingverification);
			Common.assertionCheckwithReport(headingverification.contains("HYDROFLASK QA TESTING"),
					"validation of title in the forntend website ", "Title and subtitle should be appear on fornt end page",
					"Successfully Title and subtitle is appeared on the frondend", "Failed to display Title and subtitle");
		} catch (Exception | Error e) {
			e.printStackTrace();

			report.addFailedLog("validation of title in the forntend website ",
					"Title and subtitle should be appear on fornt end page", "Successfully Title and subtitle is appeared on the frondend",
					Common.getscreenShotPathforReport("Failed to display Title and subtitle"));
			Assert.fail();

		}
	}

	public void imageupload() {
		// TODO Auto-generated method stub
		String path = System.getProperty("user.dir") + ("\\src\\test\\resources\\TestData\\Admin\\Lotusqa.png");
		try {
			Sync.waitElementPresent("xpath", "(//input[@name='image_element_desktop'])[2]");
			Common.findElement("xpath", "(//input[@name='image_element_desktop'])[2]").sendKeys(path);
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

	public void Mobileupload(String Dataset) {
		// TODO Auto-generated method stub
		try {
			
			String id = Common.findElement("xpath", "(//label[text()='Select from Gallery'])[7]").getAttribute("id");
			 Common.clickElement("xpath", "//label[@id='"+ id +"']");
			Sync.waitElementVisible("xpath", "//span[text()='Upload Images']");
			
			String alt = data.get(Dataset).get("image");       
			Common.scrollIntoView("xpath", "//img[@alt='"+ alt +"']");
	    	Common.clickElement("xpath", "//img[@alt='"+ alt +"']");
	    	Common.clickElement("xpath", "//button[@id='insert_files']");
		String imagename = Common.findElement("xpath", "(//img[@class='preview-image'])[2]").getAttribute("alt");
		Common.assertionCheckwithReport(imagename.equals(alt),
				"validation the image uploading on content for Mobile image ",
				"Image should be upload for Mobile image", "Successfully image uploaded in Mobile image ",
				"Failed to upload the mobile image");
		
		} catch (Exception e) {
	e.printStackTrace();
	ExtenantReportUtils.addFailedLog(" To Validate the User upload the mobile image",
			"User should able to upload the mobile image", "Unable to upload the mobile image",
			Common.getscreenShotPathforReport("Failed upload the mobile image"));
	Assert.fail();
	}}

	public void image_Alt_Attribute(String Dataset) {
		// TODO Auto-generated method stub
		
		try {
			
		    Common.scrollIntoView("xpath", "//input[@name='alt']");
			Common.textBoxInput("xpath", "//input[@name='alt']",data.get(Dataset).get("title"));
			String saved = Common.findElement("xpath", "//span[text()='Image Alt Attribute']").getText();
			System.out.println(saved);
			Common.assertionCheckwithReport(saved.equals("Image Alt Attribute"),
					"Enter the Image Alt Attribute details ", "Image Alt Attribute details enterd succesfully",
					"Successfully entered Image Alt Attribute details", "Failed to enterd the Image Alt Attribute details");    
			Common.clickElement("xpath", "//button[@id='save']");
				
			} catch (Exception e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog(" To Validate the User needs to save the page",
				"User should able to save the page", "Unable to saves the page",
				Common.getscreenShotPathforReport("Failed to save the page"));
		Assert.fail();
		}}

	public void website_image() {
		// TODO Auto-generated method stub
		
		try {
			String image = Common.findElement("xpath", "//div[@class='c-promo-block__raised-image']").getText();
								
			System.out.println(image);
			Common.assertionCheckwithReport(image.contains("HYDROFLASK QA TESTING"),
					"validating the images are displaying on fornt end ",
					"after Clicking on the link  it should be navigate to the respective page contains with 2 images",
					"Successfully it is navigated to respective page and contains 2 images ",
					"Failed to navigate to respective page and images are not appeared");
		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating the images are displaying on fornt end ",
					"after Click on link  it should be navigate to the respective page contains with 2 images",
					"Unable to navigated to the respective page and 2 images are not displayed",
					Common.getscreenShotPathforReport(
							"Failed to navigate to respective page and images are not appeared"));
			Assert.fail();

		}
	}

		public void Configure_textcolor_dark() {
			
			try {
		
				int textcolor = Common.findElements("xpath", "//li[@class='active']//span[text()='Dark']").size();
				if(textcolor>0) {
					System.out.println("Dark color is selected");
				}
				else {
					Sync.waitElementClickable("xpath", "//span[text()='Dark']");
					Common.mouseOverClick("xpath", "//span[text()='Dark']");
				}
			
				
				Common.assertionCheckwithReport(textcolor>0, "To validate the text colour selected is Dark", " should select the text colour Dark",
						"Sucessfully select the Dark text color", "Failed to select the text colour dark");
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("To validate the text colour selected is Dark", " should select the text colour Dark",
						"Unable select the Dark text color", Common.getscreenShotPathforReport("Failed to select the text colour dark"));
				e.printStackTrace();
				Assert.fail();

			}
		}
		public void Prmocontent_Content(String dataSet) {
			String Description = data.get(dataSet).get("Description");
			String Title = data.get(dataSet).get("title");
			try {
			Common.scrollIntoView("xpath", "(//input[@name='title'])[2]");

			Common.textBoxInput("xpath","(//input[@name='title'])[2]",Title);
			Common.dropdown("xpath", "//select[@name='heading_type']", SelectBy.TEXT, "H2");
			Common.switchFrames("id", "hot_product_promo_form_description_ifr");
			Common.textBoxInputClear("id", "html-body");
			Common.findElement("id", "html-body").sendKeys(Description);
			Common.switchToDefault();
			Common.scrollIntoView("xpath", "//button[@id='save']");
			Common.clickElement("xpath","//button[@id='save']");
//				Thread.sleep(2000);
//				Editcardtile_color("#599b3d");

			Sync.waitElementVisible("xpath","//h3[@class='c-promo-block__heading']");
			String title = Common.findElement("xpath","//h3[@class='c-promo-block__heading']").getText();

			Sync.waitElementVisible("xpath","//h3[@class='c-promo-block__heading']");
			String description = Common.findElement("xpath","//div[@data-element='description']").getText();

			Common.assertionCheckwithReport(title.contains(Title) && description.contains(Description),
					"validate the title and description are updated in the magento preview page",
					"Should update title and description in the magento preview page","Successfully updated title and description in the magento preview page",
					"Failed to update title and description in the magento preview page");


			} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validate the title and description are updated in the magento preview page",
					"Should update title and description in the magento preview page",
					"Unable to update title and description in the magento preview page",
							 Common.getscreenShotPathforReport("Failed to update title and description in the magento preview page"));
			e.printStackTrace();
			Assert.fail();

			}
			}

		public void promocontent_text_colour_dark_website_verification() {


			try {
			WebElement color = Common.findElement(By.tagName("h2"));
			String s = color.getCssValue("color");
			  String c = Color.fromString(s).asHex();
			  System.out.println("Color is :" + s);
			  System.out.println("Hex code for color:" + c);
			Sync.waitElementPresent("xpath", "//P[@class='m-breadcrumb__text']");
			String colour = Common.findElement("xpath", "//div[@class='c-promo-block__content']").getCssValue("color");
			System.out.println(colour);

			Common.assertionCheckwithReport(colour.contains("rgba(0, 0, 0, 1)") || c.contains("#000") || colour.contains("rgba(4, 4, 5, 1)") || c.contains("#040405") ,
				"validate the Dark text colour display in the website", "Text colour should display Dark in website ","Successfully display text color dark in the website","Failed to display the text color dark in the website");

			}catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validate the Dark text colour display in the website",
				"Text colour should display Dark in website ",
				"Unable to display text color dark in the website",
				Common.getscreenShotPathforReport("Failed to display the text color dark in the website"));
			Assert.fail();
			}}

		public void Configure_textcolor_light() {
			try {
			Common.switchToFirstTab();
			Contentpage();
			editpromocontent();

				
				int textcolor = Common.findElements("xpath", "//li[@class='active']//span[text()='Light']").size();
				if(textcolor>0) {
					System.out.println("light color is selected");
				}
				else {
					Sync.waitElementClickable("xpath", "//span[text()='Light']");
					Common.mouseOverClick("xpath", "//span[text()='Light']");

					Common.clickElement("xpath", "//span[text()='Light']");
					
					
				}

				int textcolorlight = Common.findElements("xpath", "//li[@class='active']//span[text()='Light']").size();

				Common.assertionCheckwithReport(textcolorlight>0, "To validate the text colour selected is Light", " should select the text colour light",
						"Successfully select text color is light ", "Faileed to select the text colour light");
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("To validate the text colour selected is Light", " should select the text colour light",
						"unable to select text color is light ", Common.getscreenShotPathforReport("Faileed to select the text colour light"));
				e.printStackTrace();
				Assert.fail();
			}
			}
		
		public void promocontent_text_colour_light_website_verification() {


			try {
			WebElement color = Common.findElement(By.tagName("h2"));
			String s = color.getCssValue("color");
			  String c = Color.fromString(s).asHex();
			  System.out.println("Color is :" + s);
			  System.out.println("Hex code for color:" + c);
			Sync.waitElementPresent("xpath", "//P[@class='m-breadcrumb__text']");
			String colour = Common.findElement("xpath", "//div[contains(@class,'c-promo-block__content')]").getCssValue("color");
			System.out.println(colour);

			Common.assertionCheckwithReport(colour.contains("rgba(255, 255, 255, 1)") || c.contains("#ffff"),"To validate the light text colour display in website", "The text colour should display Light in website",
				"Sucessfully display the text color is light in website", "Faileed to display the text colour dark");
			} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the light text colour display in website", "The text colour should display Light in website",
				"unable to display the text color is light in website", Common.getscreenShotPathforReport("failed to display light text color"));
			e.printStackTrace();
			Assert.fail();
			}}

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


		public void Click_Promo_Product(String Dataset) {
			// TODO Auto-generated method stub
			
			try {
			/*	Common.switchToFirstTab();	
				Contentpage();
		         hot_elements(); 
		        //editCategory_Productslider();
		        editpromocontent();*/
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
           // Configure_padding_marins(Dataset);
             Sync.waitElementPresent("xpath", "//button[@id='save']");
            Common.clickElement("xpath", "//button[@id='save']");

		} catch (Exception e) {
	e.printStackTrace();
	ExtenantReportUtils.addFailedLog(" To Validate the User needs to save the page",
			"User should able to save the page", "Unable to saves the page",
			Common.getscreenShotPathforReport("Failed to save the page"));
	Assert.fail();
		}}


		public void Websiteverification_hero_product() {
			// TODO Auto-generated method stub
			
			 try {
	                Common.refreshpage();
	                Common.scrollIntoView("xpath", "//a[@class='a-btn pagebuilder-button-primary']");
	                Sync.waitElementPresent(40, "xpath", "//a[@class='a-btn pagebuilder-button-primary']");
	                Common.clickElement("xpath", "//a[@class='a-btn pagebuilder-button-primary']");
	                Common.switchWindows();
	                Thread.sleep(7000);
	                Sync.waitElementPresent("xpath", "//div[@class='c-product-overview u-container']");
	                String headingverification = Common.getText("xpath", "//div[@class='c-product-overview u-container']");
	                System.out.println(headingverification);
	                Common.assertionCheckwithReport(headingverification.contains("32 OZ WIDE MOUTH STAINLESS"),
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


		public void website_verification_Promocontent() {
			// TODO Auto-generated method stub
			
			try {

				Sync.waitElementPresent(40, "xpath", "//a[@class='a-btn pagebuilder-button-primary']");
				Common.clickElement("xpath", "//a[@class='a-btn pagebuilder-button-primary']");
				Thread.sleep(5000);
				Common.switchWindows();
				Sync.waitPageLoad();
				Thread.sleep(3000);
				int logo = Common.findElements("xpath", "//div[@class='t-cms-404__container u-container']").size();
				System.out.println(logo);
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
}
	
	
