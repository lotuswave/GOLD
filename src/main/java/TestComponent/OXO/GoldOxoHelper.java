package TestComponent.OXO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.AssertJUnit;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Common.SelectBy;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.Utils;

public class GoldOxoHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	
public GoldOxoHelper(String datafile,String sheetname) {
		
		excelData = new ExcelReader(datafile,sheetname);
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("Oxo");
			report.createTestcase("OxoTestCases");
		} else {
			this.report = Utilities.TestListener.report;
		}
		
	}
	
	public void verifingHomePage() {
		try {
			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Home Page"),
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
//			Common.clickElement("xpath", "//span[text()='Shop All']");
			Common.clickElement("xpath", "//a[contains(@aria-label,'Shop All  Coffee & B')]");
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
	public void addtocart(String Dataset) {
		String products = data.get(Dataset).get("Products");
		System.out.println(products);
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
			Common.scrollIntoView("xpath", "//img[@alt='" + products + "']");
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.mouseOver("xpath", "//img[@alt='" + products + "']");
			Sync.waitElementPresent("xpath", "//span[text()='Add to Bag']");
           Common.clickElement("xpath", "//span[text()='Add to Bag']");
		
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
					.getAttribute("data-ui-id");
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));

			Assert.fail();
		}
	}
	public void minicart_Checkout() {

		try {
			click_minicart();
			String minicart = Common.findElement("xpath", "//p[@class='c-mini-cart__total-counter']//strong").getText();
			Sync.waitElementPresent("xpath", "//button[@title='Checkout']");
			Common.clickElement("xpath", "//button[@title='Checkout']");
			String checkout = Common.findElement("xpath", "//strong[@role='heading']//span").getText();
			Common.assertionCheckwithReport(
					checkout.equals(minicart) && Common.getCurrentURL().contains("checkout/#shipping"),
					"validating the navigation to the shipping page when we click on the Checkout",
					"User should navigate to the shipping  page", "Successfully navigate to the shipping page",
					"Failed to navigate to the shipping page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the navigation to the shipping page when we click on the Checkout",
					"User should navigate to the shipping  page", "unable to navigate to the shipping page",
					Common.getscreenShot("Failed to navigate to the shipping page"));

			Assert.fail();
		}

	}
	
	public void click_minicart() {
		try {
			Thread.sleep(8000);
			Common.scrollIntoView("xpath", "//a[contains(@class,'c-mini')]");
			Sync.waitElementPresent("xpath", "//a[contains(@class,'c-mini')]");
			Common.mouseOverClick("xpath", "//a[contains(@class,'c-mini')]");
			String openminicart = Common.findElement("xpath", "//div[@data-block='minicart']").getAttribute("class");
			System.out.println(openminicart);
			Common.assertionCheckwithReport(openminicart.contains("active"), "To validate the minicart popup",
					"Should display the mini cart", "Mini cart is displayed", "mini cart is not displayed");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the minicart popup", "Should display the mini cart",
					"unable to  display the mini cart", Common.getscreenShot("Failed to display the mini cart"));
			Assert.fail();

		}
	}
	
	public String addDeliveryAddress(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String method="";
		
		try {
			Thread.sleep(5000);
			Sync.waitElementVisible("id", "customer-email");
			Common.textBoxInput("id", "customer-email", data.get(dataSet).get("Email"));
		} catch (NoSuchElementException e) {
			minicart_Checkout();
			Common.textBoxInput("id", "customer-email", data.get(dataSet).get("Email"));

		}
		String expectedResult = "email field will have email address";
		try {
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
            int size = Common.findElements("id", "customer-email").size();
            Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,"Filled Email address", "unable to fill the email address");
            Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",data.get(dataSet).get("Street"));
			String Text=Common.getText("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='city']").clear();
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",data.get(dataSet).get("City"));
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
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
			Thread.sleep(5000);
			
			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
            
			Sync.waitPageLoad();
		}

		catch (Exception | Error e) {
     e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating shipping address",
					"shipping address is filled in to the fields", "user faield to fill the shipping address",
					Common.getscreenShotPathforReport("shipingaddressfaield"));
			Assert.fail();

		}
		try
		{
		
		int size=Common.findElements("xpath", "//input[@class='a-radio-button__input']").size();
		if(size>0){
			
			Sync.waitElementPresent(30, "xpath", "//tr[@class='row']//td[@class='col col-method' and @id]");
			 method=Common.findElement("xpath", "//tr[@class='row']//td[@class='col col-method' and @id]").getText();
			Common.clickElement("xpath", "//tr[@class='row']//td[@class='col col-method' and @id]");
			
//			Sync.waitElementPresent(30, "xpath", "//input[@value='flatrate_flatrate']");
//			Common.clickElement("xpath", "//input[@value='flatrate_flatrate']");
		}
	

		expectedResult = "shipping address is filled in to the fields";
		Common.clickElement("xpath", "//button[@data-role='opc-continue']");

		int errorsize = Common.findElements("xpath", "//div[contains(@id,'error')]").size();

		if (errorsize >= 0) {
			ExtenantReportUtils.addPassLog("validating the shipping address field with valid Data", expectedResult,
					"Filled the shipping address", Common.getscreenShotPathforReport("shippingaddresspass"));
		} else {
			
			ExtenantReportUtils.addFailedLog("validating the shipping address field with valid Datas", expectedResult,
					"failed to add a addres in the filled",
					Common.getscreenShotPathforReport("failed to add a address"));
			
			Assert.fail();
		}
	}
	
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the shipping address field with valid Datas", expectedResult,
				"failed to add a addres in the filled",
				Common.getscreenShotPathforReport("failed to add a address"));
		
		Assert.fail();
	}
		return method;
	}
	
	public String updatePaymentAndSubmitOrder(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String order="";
		addPaymentDetails(dataSet);
		String expectedResult = "It redirects to order confirmation page";
		
		  if (Common.findElements("xpath","//div[@class='message message-error']").size() > 0)
		  {
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

		String url=automation_properties.getInstance().getProperty(automation_properties.BASEURL);
		
		if(!url.contains("stage") && !url.contains("preprod")){
			}
		
		else{
			try{
		String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();

		
		int sizes = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();
		Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
				"verifying the product confirmation", expectedResult,
				"Successfully It redirects to order confirmation page Order Placed",
				"User unabel to go orderconformation page");
		
		if(Common.findElements("xpath", "//div[@class='checkout-success']/p/span").size()>0) {
			order=Common.getText("xpath", "//div[@class='checkout-success']/p/span");
			System.out.println(order);
		}
		if(Common.findElements("xpath","//a[@class='order-number']/strong").size()>0) {
			order=	Common.getText("xpath", "//a[@class='order-number']/strong");
			System.out.println(order);
		}
         
			}
			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
						"User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));
				Assert.fail();
			}
			
	
	}
		return order;
	}
	
	public void addPaymentDetails(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String,String> Paymentmethod=new HashMap<String,String>();
        Sync.waitPageLoad();
		Thread.sleep(4000);
		String cardnumber=data.get(dataSet).get("cardNumber");
		System.out.println(cardnumber);
		String expectedResult = "land on the payment section";
		//Common.refreshpage();
	
		try {
			Sync.waitPageLoad();
		 
	  	Sync.waitElementClickable("xpath", "//label[@for='stripe_payments']");
		int sizes=Common.findElements("xpath", "//label[@for='stripe_payments']").size();

	 Common.assertionCheckwithReport(sizes>0, "Successfully land on the payment section", expectedResult,"User unabel to land opaymentpage");
		Common.clickElement("xpath", "//label[@for='stripe_payments']");
		
		Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
		int payment=Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
		System.out.println(payment);
		if(payment>0)
		{
			Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
			Common.clickElement("xpath", "//div[@class='stripe-dropdown-selection']");
			Common.clickElement("xpath", "//span[text()='New payment method']");
			Thread.sleep(4000);
			Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
			Thread.sleep(5000);
			Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
			Common.clickElement("xpath", "//label[@for='Field-numberInput']");
			Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);
		
			Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));
			
			Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.switchToDefault();
			Thread.sleep(1000);
		    Common.clickElement("xpath", "//span[text()='Place Order']");
		}
       else
        {
			Thread.sleep(4000);
			Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
			Thread.sleep(5000);
			Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
			Common.clickElement("xpath", "//label[@for='Field-numberInput']");
			Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);
		
			Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));
			
			Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
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
		Sync.waitPageLoad();
		expectedResult = "credit card fields are filled with the data";
		String errorTexts = Common.findElement("xpath", "//div[contains(@class,'error')]").getText();
		System.out.println(errorTexts);
		
		Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data",
			expectedResult, "Filled the Card detiles", "missing field data it showinng error");
	}
	
	public void click_singinButton() {
		try {
			Sync.waitElementPresent("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//li[@class='m-account-nav__log-in']//a[text()='Sign In']");
			Common.assertionCheckwithReport(
			     Common.getText("xpath", "//h3[@id='block-customer-login-heading']").equals("Sign In"),
					"To validate the user navigates to the signin page",
					"user should able to land on the signIn page after clicking on the sigin button",
					"User Successfully clicked on the singIn button and Navigate to the signIn page",
					"User Failed to click the signin button and not navigated to signIn page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the user navigates to the signin page",
					"user should able to land on the signIn page after clicking on the sigin button",
					"Unable to click on the singIn button and not Navigated to the signIn page",
					Common.getscreenShotPathforReport(
							"Failed to click signIn button and not Navigated to the signIn page"));
			Assert.fail();
		}
	}
	public void Usersignin(String dataSet) throws Exception {

		try
		{
		Sync.waitPageLoad();
		Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
		Common.clickElement("xpath", "//button[contains(@class,'action lo')]");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String login=Common.findElement("xpath", "//div[@class='m-account-nav__welcome']//span[@class='a-icon-text-btn__label']").getText();
		
		
		
		Common.assertionCheckwithReport(login.contains("Welcome"),
				"Validating My Account page navigation", "user sign in and navigate to my account page",
					"Successfully navigate to my account page", "Failed to navigate my account page ");
	
	}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating My Account page navigation", "user sign in and navigate to my account page",
					"Unable to navigate to the my account page",
					Common.getscreenShotPathforReport(
							"Failed to navigate to the my account page "));
			Assert.fail();
		}

	}
	
	public void addDeliveryAddress_registerUser(String dataSet) {
		// TODO Auto-generated method stub
		String expectedResult = "shipping address is entering in the fields";
        int size = Common.findElements(By.xpath("//span[contains(text(),'Add New Address')]")).size();
		if (size > 0) {
        	try { 
				Common.clickElement("xpath", "//span[contains(text(),'Add New Address')]");
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",	data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",data.get(dataSet).get("Street"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.SPACE);
				Thread.sleep(2000);
				try {
					Common.clickElement("xpath",
							"//form[@id='co-shipping-form']//input[@name='street[0]']");
				} catch (Exception e) {
					Common.actionsKeyPress(Keys.BACK_SPACE);
					Thread.sleep(1000);
					Common.actionsKeyPress(Keys.SPACE);
					Common.clickElement("xpath",
							"//form[@id='co-shipping-form']//input[@name='street[0]']");
				}
				if (data.get(dataSet).get("StreetLine2") != null) {
					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
				}
				if (data.get(dataSet).get("StreetLine3") != null) {
					Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
				}
				
				Common.scrollIntoView("xpath", "//select[@name='region_id']");
				Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT,data.get(dataSet).get("Region"));
				Thread.sleep(3000);
				String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']").getAttribute("value");
				String Shippingstate = Common.findElement("xpath", "//select[@name='region_id']//option[@value='" + Shippingvalue + "']").getText();

//				Shippingaddress.put("Shippingstate", Shippingstate);
				System.out.println(Shippingstate);

				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
						data.get(dataSet).get("City"));
				// Common.mouseOverClick("name", "region_id");
				try {
					Common.dropdown("xpath", "//form[@id='co-shipping-form']//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					// TODO: handle exception
					Thread.sleep(2000);
					Common.dropdown("xpath", "//form[@id='co-shipping-form']//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);
				Common.textBoxInputClear("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']");
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']",
						data.get(dataSet).get("postcode"));
				String ShippingZip = Common.findElement("name", "postcode").getAttribute("value");
				System.out.println("*****" + ShippingZip + "*******");
//				Shippingaddress.put("ShippingZip", ShippingZip);
				
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='telephone']",
						data.get(dataSet).get("phone"));
				
				Sync.waitElementPresent("xpath", "//label[@class='label a-checkbox__label']");
				Common.clickElement("xpath", "//label[@class='label a-checkbox__label']");

                Common.clickElement("xpath", "//div[@id='opc-new-shipping-address']//following::button[1]");

				int sizeerrormessage = Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();

				Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying shipping addres filling ",
						"user will fill the all the shipping", "user fill the shiping address click save button",
						"faield to add new shipping address");
				

				Common.clickElement("xpath", "//tr[@class='row']//td[@class='col col-method' and @id]");
				Common.clickElement("xpath", "//button[@data-role='opc-continue']");
				
			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
						"User unabel add shipping address",
						Common.getscreenShotPathforReport("shipping address faield"));
			
				Assert.fail();

			}
        	
		}

		else

		{
			try {
				Common.clickElement("xpath", "//a[@class='action action-show-popup checkout-add-address-popup-link']");
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
						data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
						data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
						data.get(dataSet).get("Street"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.SPACE);
				Thread.sleep(3000);
				try {
					Common.clickElement("xpath",
							"//form[@id='co-shipping-form']//input[@name='street[0]");
				} catch (Exception e) {
					Common.actionsKeyPress(Keys.BACK_SPACE);
					Thread.sleep(1000);
					Common.actionsKeyPress(Keys.SPACE);
					Common.clickElement("xpath",
							"//form[@id='co-shipping-form']//input[@name='street[0]");
				}
				if (data.get(dataSet).get("StreetLine2") != null) {
					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
				}
				if (data.get(dataSet).get("StreetLine3") != null) {
					Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
				}
				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
						data.get(dataSet).get("City"));
				
				try {
					Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					// TODO: handle exception
					Thread.sleep(3000);
					Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);
				Common.textBoxInputClear("name", "postcode");
				Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
				
				String ShippingZip = Common.findElement("name", "postcode").getAttribute("value");
				System.out.println("*****" + ShippingZip + "*******");
//				Shippingaddress.put("ShippingZip", ShippingZip);
				
				Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

				Sync.waitElementClickable("xpath", "//span[contains(text(),'Continue To Payment')]");
				Common.clickElement("xpath", "//span[contains(text(),'Continue To Payment')]");
                int errorsize=Common.findElements("xpath", "//div[@class='field-error']").size();
				Common.assertionCheckwithReport(errorsize>0, "verifying shipping addres filling ", expectedResult, "user enter the shipping address", "mandatory data");			
	
				expectedResult = "shipping address is filled in to the fields";
				Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");
				Thread.sleep(3000);

			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
						"User unabel add shipping address",
						Common.getscreenShotPathforReport("shipping address faield"));
				
				Assert.fail();

			}
		}
	

	}

	
	public void click_Createaccount() {

		try {
			Sync.waitElementPresent("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//li[@class='nav item']//a[text()='Create an Account']");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Create New Customer Account"),
					"Validating Create New Customer Account page navigation",
					"after Clicking on Create New Customer Account page it will navigate account creation page",
					"Successfully navigate to the create account page",
					"Failed to Navigate to the account create page ");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Create New Customer Account page navigation ",
					"after Clicking on Create New Customer Account page it will navigate account creation page",
					"unable to navigate to the craete account page",
					Common.getscreenShotPathforReport("Failed to navigate to the account create page"));
			Assert.fail();
		}
	}

	public void create_account(String Dataset) {
		try {
			Common.refreshpage();
			Sync.waitPageLoad();
			Sync.waitElementPresent(30, "xpath", "//input[@name='firstname']");
			Common.clickElement("xpath", "//input[@name='firstname']");
			Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(Dataset).get("FirstName"));
			Common.clickElement("xpath", "//input[@name='lastname']");
			Common.textBoxInput("id", "lastname", data.get(Dataset).get("LastName"));
			Common.clickElement("xpath", "//input[@name='email']");
			Common.textBoxInput("xpath", "//input[@name='email']", Utils.getEmailid());
			Common.clickElement("xpath", "//input[@name='password']");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			Sync.waitElementPresent(30, "xpath", "//input[@name='password_confirmation']");
			Common.clickElement("xpath", "//input[@name='password_confirmation']");
			Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
					data.get(Dataset).get("Confirm Password"));
			Sync.waitElementPresent(30, "xpath", "//button[@title='Sign Up']");
			Common.clickElement("xpath", "//button[@title='Sign Up']");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//div[@data-ui-id='message-success']//div");
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("My Account") && message.contains("Thank you for registering"),
					"validating the  my Account page Navigation when user clicks on signin button",
					"User should able to navigate to the my account page after clicking on Signin button",
					"Sucessfully navigate to the My account page after clicking on signin button ",
					"Failed to navigates to My Account Page after clicking on Signin button");
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the  my Account page Navigation when user clicks on signin button",
					"User should able to navigate to the my account page after clicking on Signin button",
					"Unable to navigate to the My account page after clicking on signin button ",
					Common.getscreenShot("Failed to navigates to My Account Page after clicking on Signin button"));
			Assert.fail();

		}
	}

	public void createaccount_verfication(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//input[@name='firstname']");
			Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(Dataset).get("FirstName"));
			Common.clickElement("xpath", "//input[@name='lastname']");
			Common.textBoxInput("id", "lastname", data.get(Dataset).get("LastName"));
			Common.clickElement("xpath", "//input[@name='email']");
			Common.textBoxInput("xpath", "//input[@name='email']", data.get(Dataset).get("Email"));
			Common.clickElement("xpath", "//input[@name='password']");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			Common.clickElement("xpath", "//input[@name='password_confirmation']");
			Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
					data.get(Dataset).get("Confirm Password"));
			Common.clickElement("xpath", "//button[@title='Sign Up']");
			String message = Common.findElement("id", "validation-classes").getCssValue("color");
			String redcolor=Color.fromString(message).asHex();
			String message1 = Common.findElement("id", "validation-length").getCssValue("color");
			String greencolor=Color.fromString(message1).asHex();
			String emailmessage = Common.findElement("xpath", "//div[@id='email_address-error']").getText();
			String confirmpassword = Common.findElement("xpath", "//div[@id='password-confirmation-error']").getText();
			Common.assertionCheckwithReport(redcolor.equals("#b51a18") && greencolor.equals("#2f741f") && emailmessage.contains("@domain.com")
							&& confirmpassword.contains("enter the same value again"),
					"validating the error messages with invalid date in accout creation form",
					"User should able to get error message when used the invaild data",
					"Sucessfully error message has been displayed when user use the invalid data",
					"Failed to get an error message when user used the invalid data");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the error messages with invalid date in accout creation form",
					"User should able to get error message when used the invaild data",
					"Unable to get error message has been displayed when user use the invalid data",
					Common.getscreenShotPathforReport(
							"Failed to get an error message when user used the invalid data"));
			Assert.fail();

		}
	}
	
	public void minicart_validation(String Dataset) {
		// TODO Auto-generated method stub
		String UpdataedQuntityinminicart = data.get(Dataset).get("Quantity");
try
{

		String Subtotal = Common.getText("xpath", "//span[@class='c-mini-cart__subtotal-amount']//span")
				.replace("$", "");
		Float subtotalvalue = Float.parseFloat(Subtotal);
		Sync.waitElementPresent("xpath", "//select[@class='a-select-menu cart-item-qty']");
		Common.clickElement("xpath", "//select[@class='a-select-menu cart-item-qty']");
		Common.dropdown("xpath", "//select[@class='a-select-menu cart-item-qty']", Common.SelectBy.VALUE,
				UpdataedQuntityinminicart);
		Common.clickElement("xpath", "//span[text()='Update']");
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//p[@class='c-mini-cart__total-counter']//strong");
		String cart = Common.findElement("xpath", "//p[@class='c-mini-cart__total-counter']//strong").getText();
		System.out.println(cart);
		String Subtotal2 = Common.getText("xpath", "//span[@class='c-mini-cart__subtotal-amount']//span")
				.replace("$", "");
		Float subtotalvalue2 = Float.parseFloat(Subtotal2);
		Float Total = subtotalvalue * 3;
		String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		Common.assertionCheckwithReport(UpdataedQuntityinminicart.equals(cart) && ExpectedTotalAmmount2.equals(Subtotal2),
				"validating the product update quantity and subtotal",
				"The product Quantity should be update in mini cart and subtotal should change",
				"Successfully product quantity updated and subtotal has been changed",
				"Failed to update the product quantity from cart and subtotal not changed");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the product update quantity and subtotal",
				"The product Quantity should be update in mini cart and subtotal should change",
				"unable to update the product quantity and subtotal has not be changed",
				Common.getscreenShot("Failed to update the product quantity from cart and subtotal not changed"));
		Assert.fail();
	}


	}

	public void discountCode(String dataSet) throws Exception {
        String expectedResult = "It should opens textbox input.";



       try {

            Sync.waitElementClickable("id", "block-discount-heading");
            Common.clickElement("id", "block-discount-heading");

             Sync.waitElementPresent("id", "discount-code");

             Common.textBoxInput("id", "discount-code", data.get(dataSet).get("Discountcode"));

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

       }


       catch (Exception | Error e) {
            ExtenantReportUtils.addFailedLog("validating discount code", expectedResult,
                    "User failed to proceed with discountcode", Common.getscreenShotPathforReport("discountcodefailed"));
            
            Assert.fail();

       }
	}
	
	public void updtePayementcrditcard_WithInvalidData(String dataSet) throws Exception {
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String cardnumber = data.get(dataSet).get("cardNumber");
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

				Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

				Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
				Thread.sleep(1000);
				Common.clickElement("xpath", "//span[text()='Place Order']");
				expectedResult = "credit card fields are filled with the data";
				String errorTexts = Common.findElement("xpath", "//div[contains(@id,'error')]").getText();

				Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data",
						expectedResult, "Filled the Card details", "missing field data its showing error");
		
				
			} else {
				Thread.sleep(4000);
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Thread.sleep(5000);
				Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
				Common.clickElement("xpath", "//label[@for='Field-numberInput']");
				Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);

				Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

				Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
				Thread.sleep(1000);
				Common.clickElement("xpath", "//span[text()='Place Order']");
				expectedResult = "credit card fields are filled with the data";
				String errorTexts = Common.findElement("xpath", "//div[contains(@id,'error')]").getText();

				Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data",
						expectedResult, "Filled the Card detiles", "missing field data it showinng error");
				

			}
			
		}

		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", expectedResult,
					"failed  to fill the Credit Card infromation",
					Common.getscreenShotPathforReport("Cardinfromationfail"));
			Assert.fail();
		}

		
	}
	
	public void ChangeAddress_AddressBook(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Sync.waitElementPresent(30, "xpath", "//a[text()='My Account']");
			Common.clickElement("xpath", "//a[text()='My Account']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Account"),
					"validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Sucessfully User Navigates to the My account page after clicking on the my account CTA",
					"Failed to Navigate to the MY account page after Clicking on my account button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Unable to Navigates the user to My account page after clicking on the my account CTA",
					Common.getscreenShot(
							"Failed to Navigate to the MY account page after Clicking on my account CTA"));
			Assert.fail();
		}
		Sync.waitPageLoad();
		Common.clickElement("xpath", "//a[text()='Address Book']");
		Sync.waitPageLoad();
		Common.assertionCheckwithReport(Common.getPageTitle().equals("Address Book"),
				"validating the Navigation to the Address Book page",
				"After Clicking on Address Book CTA user should be navigate to the Address Book page",
				"Sucessfully User Navigates to the Address Book page after clicking on the Address Book CTA",
				"Failed to Navigate to the Address Book page after Clicking on Address Book CTA");
		
		String PageTitle = Common.getText("xpath", "//h1[@class='page-title-wrapper h2']");
		if (PageTitle.contains("New")) {
			try {
				Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//input[@title='Phone Number']",data.get(dataSet).get("phone"));
				Common.textBoxInput("xpath", "//input[@title='Address Line 1']", data.get(dataSet).get("Street"));
				Common.textBoxInput("xpath", "//input[@title='City']", data.get(dataSet).get("City"));
				try {
					Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					Thread.sleep(3000);
					Common.dropdown("id", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}
				
				Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
	
				Common.clickElement("xpath", "//button[@title='Save Address']");
				String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				
				 Common.assertionCheckwithReport(message.equals("You saved the address."),
									"validating the saved message after saving address in address book",
									"Save address message should be displayed after the address saved in address book",
									"Sucessfully address has been saved in the address book",
									"Failed to save the address in the address book");
				
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the saved message after saving address in address book",
						"Save address message should be displayed after the address saved in address book",
						"unable to save the adreess in the address book",
						Common.getscreenShotPathforReport("Failed to save the address in the address book"));
				
				Assert.fail();

			}
		}

		else {
			Common.clickElementStale("xpath", "//span[contains(text(),'Change Billing Address')]");
		

			try {
				Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//input[@title='Phone Number']",data.get(dataSet).get("phone"));
				Common.textBoxInput("xpath", "//input[@title='Address Line 1']", data.get(dataSet).get("Street"));
				
				try {
					Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					
					Thread.sleep(3000);
					Common.dropdown("id", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);
				
				Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
				
				Common.clickElement("xpath", "//button[@title='Save Address']");
				String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				
				 Common.assertionCheckwithReport(message.equals("You saved the address."),
									"validating the saved message after saving address in address book",
									"Save address message should be displayed after the address saved in address book",
									"Sucessfully address has been saved in the address book",
									"Failed to save the address in the address book");
			}

			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating my address book with data",
						"enter the valid address without any validation",
						"User failed to enter data in my address book",
						Common.getscreenShotPathforReport("faield to save the address in addressbook"));
				Assert.fail();

			}
		}
	}
	
	public void My_Orders_Page(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Sync.waitElementPresent(30, "xpath", "//a[text()='My Account']");
			Common.clickElement("xpath", "//a[text()='My Account']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Account"),
					"validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Sucessfully User Navigates to the My account page after clicking on the my account CTA",
					"Failed to Navigate to the MY account page after Clicking on my account button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Unable to Navigates the user to My account page after clicking on the my account CTA",
					Common.getscreenShot(
							"Failed to Navigate to the MY account page after Clicking on my account CTA"));
			Assert.fail();
		}
		try
		{
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//a[text()='My Orders']");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Orders"),
					"validating the Navigation to the My Orders page",
					"After Clicking on My Orders CTA user should be navigate to the My Orders page",
					"Sucessfully User Navigates to the My Orders page after clicking on the My Orders CTA",
					"Failed to Navigate to the My Orders page after Clicking on My Orders CTA");
			String Ordernumber=Common.findElement("xpath", "(//div[@class='order-data order-data__info']//a)[1]").getText();
			
			Common.assertionCheckwithReport(Ordernumber.equals(Dataset),
					"validating the Order Number in My Myorders page",
					"Order Number should be display in the MY Order page",
					"Sucessfully Order Number is displayed in the My orders page",
					"Failed to Display My order Number in the My orders page");
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Order Number in My Myorders page",
			"Order Number should be display in the MY Order page",
			"Unable to Display the Order Number in the My orders page",
					Common.getscreenShot(
							"Failed to Display My order Number in the My orders page"));
			Assert.fail();
		}
	}
	
	public void newuseraddDeliveryAddress(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5000);
			Sync.waitElementVisible("id", "customer-email");
			Common.textBoxInput("id", "customer-email", Utils.getEmailid());
		} catch (NoSuchElementException e) {
			minicart_Checkout();
			Common.textBoxInput("id", "customer-email",Utils.getEmailid());

		}
		String expectedResult = "email field will have email address";
		try {
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));
			int size = Common.findElements("id", "customer-email").size();
			Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,
					"Filled Email address", "unable to fill the email address");
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
					data.get(dataSet).get("Street"));
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
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
			Thread.sleep(5000);

			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

			Sync.waitPageLoad();
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating shipping address",
					"shipping address is filled in to the fields", "user faield to fill the shipping address",
					Common.getscreenShotPathforReport("shipingaddressfaield"));
			Assert.fail();

		}
		try {

			int size = Common.findElements("xpath", "//input[@class='a-radio-button__input']").size();
			if (size > 0) {
				Sync.waitElementPresent(30, "xpath", "//input[@value='tablerate_bestway']");
				Common.clickElement("xpath", "//input[@value='tablerate_bestway']");
			}

			expectedResult = "shipping address is filled in to the fields";
			Common.clickElement("xpath", "//button[@data-role='opc-continue']");

			int errorsize = Common.findElements("xpath", "//div[contains(@id,'error')]").size();

			if (errorsize >= 0) {
				ExtenantReportUtils.addPassLog("validating the shipping address field with valid Data", expectedResult,
						"Filled the shipping address", Common.getscreenShotPathforReport("shippingaddresspass"));
			} else {

				ExtenantReportUtils.addFailedLog("validating the shipping address field with valid Datas",
						expectedResult, "failed to add a addres in the filled",
						Common.getscreenShotPathforReport("failed to add a address"));

				Assert.fail();
			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the shipping address field with valid Datas", expectedResult,
					"failed to add a addres in the filled",
					Common.getscreenShotPathforReport("failed to add a address"));

			Assert.fail();
		}

	}
	
	public void createAccountFromOrderSummaryPage(String Dataset) {
		// TODO Auto-generated method stub
		try
		{
			Common.clickElement("xpath", "//input[@name='password']");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			Sync.waitElementPresent(30, "xpath", "//input[@name='password_confirmation']");
			Common.clickElement("xpath", "//input[@name='password_confirmation']");
			Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
					data.get(Dataset).get("Confirm Password"));
			String Message = Common.findElement("id", "validation-classes").getCssValue("color");
			String Greencolor=Color.fromString(Message).asHex();
			String Message1 = Common.findElement("id", "validation-length").getAttribute("class");
			Common.assertionCheckwithReport(Greencolor.equals("#4d8b40") && Message1.contains("complete"),
					"validating the password minimum requriment message field",
					"User should able to view the minimum requriement field for password",
					"Sucessfully User able to view the minimum requriement field for password",
					"Failed to see the minimum requriement field for password");
			Sync.waitElementPresent(30, "xpath", "//span[text()='Create an Account']");
			Common.clickElement("xpath", "//span[text()='Create an Account']");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//div[@data-ui-id='message-success']//div");
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("My Account") && message.contains("Thank you for registering"),
					"validating the  my Account page Navigation when user clicks on signin button",
					"User should able to navigate to the my account page after clicking on Signin button",
					"Sucessfully navigate to the My account page after clicking on signin button ",
					"Failed to navigates to My Account Page after clicking on Signin button");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  my Account page Navigation when user clicks on signin button",
					"User should able to navigate to the my account page after clicking on Signin button",
					"Unable to  navigate to the My account page after clicking on signin button ",
					Common.getscreenShotPathforReport("Failed to navigates to My Account Page after clicking on Signin button"));
			Assert.fail();
		}
	}


	public String minicart_items() {
		// TODO Auto-generated method stub
		String items="";
		try
		{
			Sync.waitElementPresent("xpath", "//span[@class='c-mini-cart__counter']");
			items=Common.findElement("xpath", "//span[@class='c-mini-cart__counter']").getText();
			System.out.println(items);
			Common.clickElement("xpath", "//div[@class='c-mini-cart js-mini-cart']");
			Sync.waitElementPresent("xpath", "//p[@class='c-mini-cart__total-counter']//strong");
			String miniitems=Common.findElement("xpath", "//p[@class='c-mini-cart__total-counter']//strong").getText();
			System.out.println(miniitems);
			Common.assertionCheckwithReport(items.contains(miniitems), "Vaildating the products count in the mini cart ",
					"Products count shsould be display in the mini cart", "Sucessfully products count has displayed in the mini cart",
					"failed to display products count in the mini cart");
			Sync.waitElementPresent("xpath", "//div[@class='c-mini-cart__close-btn']");
			Common.clickElement("xpath", "//div[@class='c-mini-cart__close-btn']");
		
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Vaildating the products count in the mini cart ",
					"Products count shsould be display in the mini cart", "Unable to display the  products count in the mini cart",
					Common.getscreenShot("failed to display products count in the mini cart"));
			
			Assert.fail();
			
		}
		return items;
		
	}
	
	public void minicart_products(String minicart) {
		// TODO Auto-generated method stub
		try
		{
		Sync.waitElementPresent("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
		Common.mouseOverClick("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
		
           Sync.waitElementPresent(30,"xpath", "//span[@class='c-mini-cart__counter']");
			String cartproducts=Common.findElement("xpath", "//span[@class='c-mini-cart__counter']").getText();
		    
			Common.assertionCheckwithReport(cartproducts.equals(minicart), "validating the products in the cart after creating new account ",
					"Products should be displayed in the mini cart after Create account with Cart", "Sucessfully after create account with cart products should be display in mini cart",
					"failed to display the products in mini cart after the create account with cart");
		
			
		}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the products in the cart after creating new account ",
				"Products should be displayed in the mini cart after Create account with Cart", "Unable to display the products in mini cart after the create account with cart",
				Common.getscreenShot("failed to display the products in mini cart after the create account with cart"));
		
		Assert.fail();
	}
		
		
	}	

	public void My_Favorites() {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Sync.waitElementPresent(30, "xpath", "//a[text()='My Favorites']");
			Common.clickElement("xpath", "//a[text()='My Favorites']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Favorites"),
					"validating the Navigation to the My Favorites page",
					"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
					"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA",
					"Failed to Navigate to the My Favorites page after Clicking on My Favorites button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the My Favorites page",
					"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
					"Unable to Navigates the user to My Favorites page after clicking on the My Favorites CTA",
					Common.getscreenShot(
							"Failed to Navigate to the My Favorites page after Clicking on My Favorites CTA"));
			Assert.fail();
		}
		
		
	}
	
	public void Addtocart_From_MyFavorites(String Dataset) {
		// TODO Auto-generated method stub
	
		try
		{
			Sync.waitPageLoad();
			int MyFavorites=Common.findElements("xpath", "//div[contains(@class,'message')]//span").size();
			
			if(MyFavorites!=0)
			{
				search_product("Product");
                Sync.waitElementPresent(30, "xpath", "//button[@data-action='add-to-wishlist']");
                Common.clickElement("xpath", "//button[@data-action='add-to-wishlist']");
                Sync.waitPageLoad();
                Common.assertionCheckwithReport(Common.getPageTitle().equals("My Favorites"),
    					"validating the Navigation to the My Favorites page",
    					"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
    					"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA",
    					"Failed to Navigate to the My Favorites page after Clicking on My Favorites button");
                Common.findElements("xpath", "//span[contains(@class,'a-wishlist')]");
                Sync.waitPageLoad();
                String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
    			System.out.println(message);
    			Common.assertionCheckwithReport(message.contains("has been added to your Wish List"), "validating the  product add to the Whishlist",
    					"Product should be add to whishlist", "Sucessfully product added to the Whishlist ",
    					"failed to add product to the Whishlist");
                String Whishlistproduct=Common.findElement("xpath", "//div[contains(@class,'m-product-card__name')]//a").getText();
                System.out.println(Whishlistproduct);
                String product=data.get(Dataset).get("Products");
                if(Whishlistproduct.equals(product))
                {
                	Sync.waitElementPresent(30, "xpath", "//img[@alt='" +product+ "']");
        			Common.mouseOver("xpath", "//img[@alt='" +product+ "']");
                	Common.clickElement("xpath", "//span[text()='Add to Bag']");
                	Sync.waitPageLoad();
        			Thread.sleep(4000);
        			String message1 = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
        					.getAttribute("data-ui-id");
        			System.out.println(message1);
        			Common.assertionCheckwithReport(message1.contains("success"), "validating the  product add to the cart",
        					"Product should be add to cart", "Sucessfully product added to the cart ",
        					"failed to add product to the cart");
        			int minicart=Common.findElements("xpath", "//span[@class='c-mini-cart__counter']").size();
        			System.out.println(minicart);
        			if(minicart>0)
        			{
        				minicart_Checkout();
        			}
        			else
        			{
        			Common.refreshpage();
        			Sync.waitPageLoad();
                	minicart_Checkout();
        			}
                }
                else
                {
                	Assert.fail();
                }
                 
			}
			else
			{
				Sync.waitPageLoad();
				for (int i = 0; i <= 10; i++) {
					Sync.waitElementPresent("xpath", "//li[@class='product-item']//a");
					List<WebElement> webelementslist = Common.findElements("xpath",
							"//li[@class='product-item']//a");

					String s = webelementslist.get(i).getAttribute("href");
					System.out.println(s);
				
			        Common.scrollIntoView("xpath", "(//img[contains(@class,'m-produc')])[1]");
					Common.mouseOver("xpath", "(//img[contains(@class,'m-produc')])[1]");
					Sync.waitElementPresent("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
					List<WebElement> element = Common.findElements("xpath", "//span[text()='Add to Bag']");
					Thread.sleep(6000);
					element.get(0).click();
					String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
        					.getAttribute("data-ui-id");
        			System.out.println(message);
        			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
        					"Product should be add to cart", "Sucessfully product added to the cart ",
        					"failed to add product to the cart");
        			int minicart=Common.findElements("xpath", "//span[@class='c-mini-cart__counter']").size();
        			System.out.println(minicart);
        			if(minicart>0)
        			{
        				minicart_Checkout();
        			}
        			else
        			{
        				Common.refreshpage();
    					Sync.waitPageLoad();
    					minicart_Checkout();
        			}
					
						
			}
		}
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart",
					"Product should be add to cart", "Unable to add  product to the cart ",
					Common.getscreenShot(
							"failed to add product to the cart"));
			Assert.fail();
		}
		
	}
	
	public void search_product(String Dataset) {
   		// TODO Auto-generated method stub
   		String product = data.get(Dataset).get("Products");
   		try {
   			Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
   			String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
   			Common.assertionCheckwithReport(open.contains("active"), "User searches using the search field",
   					"User should able to click on the search button", "Search expands to the full page",
   					"Sucessfully search bar should be expand");
   			Common.textBoxInput("xpath", "//input[@id='search']", data.get(Dataset).get("Products"));
   			Common.actionsKeyPress(Keys.ENTER);
   			Sync.waitPageLoad();
   			String productsearch = Common.findElement("xpath", "//span[@id='algolia-srp-title']").getText();
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
 
	
}



