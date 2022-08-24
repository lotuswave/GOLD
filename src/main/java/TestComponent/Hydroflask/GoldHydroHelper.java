package TestComponent.Hydroflask;

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
import org.testng.Assert;
import org.testng.AssertJUnit;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Common.SelectBy;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.Utils;

public class GoldHydroHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public GoldHydroHelper(String datafile) {
		excelData = new ExcelReader(datafile);
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("Hydro");
			report.createTestcase("HydroTestCases");
		} else {
			this.report = Utilities.TestListener.report;
		}
	}
	
	public void verifingHomePage() {
		try {
			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Home Page"),
					"validating store logo", "System directs the user to the Homepage",
					"Sucessfully user navigates to the home page", "Failed to navigate to the homepage");
		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating store logo", "System directs the user to the Homepage",
					" user unable navigates to the home page", "Failed to navigate to the homepage");
			Assert.fail();
		}

	}
	
	public void headerlinks(String category) {
		// TODO Auto-generated method stub
		String expectedResult = "User should click the" + category;
		try {
       
			Sync.waitElementClickable("xpath", "//a[contains(@class,'level-top')]//span[text()=' Shop']");
			Thread.sleep(3000);
//			Common.scrollIntoView("xpath","//a[contains(@class,'level-top')]//span[text()=' Shop']");
			Common.mouseOverClick("xpath", "//a[contains(@class,'level-top')]//span[text()=' Shop']");
			Thread.sleep(3000);

			try {
				Common.mouseOver("xpath", "//span[contains(text(),'" + category + "')]");
			} catch (Exception e) {
				Common.clickElement("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");
			}
			Common.clickElement("xpath", "//span[contains(text(),'" + category + "')]");
			Common.clickElement("xpath", "//a[text()='Shop All']");
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
	public void minicart_Checkout() {
		// TODO Auto-generated method stub
		try {
			click_minicart();
			Sync.waitElementPresent("xpath", "//p[@class='c-mini-cart__total-counter']//strong");
			String minicart = Common.findElement("xpath", "//p[@class='c-mini-cart__total-counter']//strong").getText();
			Sync.waitElementPresent("xpath", "//button[@title='Checkout']");
			Common.clickElement("xpath", "//button[@title='Checkout']");
			Sync.waitPageLoad();
			Sync.waitElementPresent(30, "xpath", "//strong[@role='heading']//span");
			String checkout = Common.findElement("xpath", "//strong[@role='heading']//span").getText();
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
	public void addDeliveryAddress(String dataSet) throws Exception {
		// TODO Auto-generated method stub
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
			Sync.waitElementPresent(30, "xpath", "//input[@value='fedex_FEDEX_GROUND']");
			Common.clickElement("xpath", "//input[@value='fedex_FEDEX_GROUND']");
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
	}

	public String updatePaymentAndSubmitOrder(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		
		String order="";
		addPaymentDetails("PaymentDetails");
		String expectedResult = "It redirects to order confirmation page";
		
		  if (Common.findElements("xpath",
		  "//div[@class='message message-error']").size() > 0) { Thread.sleep(4000);
		  addPaymentDetails("PaymentDetails"); }
		 	
		Thread.sleep(3000);
		int placeordercount = Common.findElements("xpath", "//span[text()='Place Order']").size();
		if (placeordercount > 1) {
			Thread.sleep(4000);
			
			Common.clickElement("xpath", "//span[text()='Place Order']");
			Common.refreshpage();
		}

		String url=automation_properties.getInstance().getProperty(automation_properties.BASEURL);
		
		if(!url.contains("stage")){
			}
		
		else{
			try{
		String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();
		Tell_Your_FriendPop_Up();
		
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

		expectedResult = "credit card fields are filled with the data";
		String errorTexts = Common.findElement("xpath", "//div[contains(@id,'error')]").getText();
		
		
		Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data",
			expectedResult, "Filled the Card detiles", "missing field data it showinng error");
	}

public void Tell_Your_FriendPop_Up() throws Exception {
        
        try {
            
            Common.switchFrames("xpath", "//iframe[contains(@src,'widget.fbot.me')]");
            
//        Sync.waitElementClickable(30, By.xpath("//button[@class='Close-widget-wrapper']"));
        Common.findElement("xpath", "//button[@class='Close-widget-wrapper']").click();
        Common.switchToDefault();
        
    } catch (Exception | Error e) {
        e.printStackTrace();
        ExtenantReportUtils.addFailedLog("verifying Tell_Your_FriendPop_Up", "Tell_Your_FriendPop_Up closed ",
                "User failed to close Tell_Your_FriendPop_Up",
                Common.getscreenShotPathforReport("Failed to close Tell_Your_FriendPop_Up"));
        Assert.fail();



   }
    
	
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

public void login_Hydroflask(String dataSet) {

	try {
		Sync.waitPageLoad();
		Common.textBoxInput("id", "email", data.get(dataSet).get("UserName"));
		Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
		Common.clickElement("xpath", "//button[contains(@class,'action login')]");
		Sync.waitPageLoad();
		Common.assertionCheckwithReport(Common.getPageTitle().contains("Home Page"),
				"To validate the user lands on Home page after successfull login",
				"After clicking on the signIn button it should navigate to the Home page",
				"user Sucessfully navigate to the Home page after clicking on the signIn button",
				"Failed to signIn and not navigated to the Home page ");

	} catch (Exception e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To validate the user Navigate to Home page after successfull login",
				"After clicking on the signin button it should navigate to the Home page",
				"Unable to navigate the user to the home after clicking on the SignIn button",
				Common.getscreenShotPathforReport("Failed to signIn and not navigated to the Home page "));

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

//			Shippingaddress.put("Shippingstate", Shippingstate);
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
//			Shippingaddress.put("ShippingZip", ShippingZip);
			
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='telephone']",
					data.get(dataSet).get("phone"));
			
			Sync.waitElementPresent("xpath", "//label[@class='label a-checkbox__label']");
			Common.clickElement("xpath", "//label[@class='label a-checkbox__label']");

            Common.clickElement("xpath", "//div[@id='opc-new-shipping-address']//following::button[1]");

			int sizeerrormessage = Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();

			Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying shipping addres filling ",
					"user will fill the all the shipping", "user fill the shiping address click save button",
					"faield to add new shipping address");
			

			Common.clickElement("xpath", "//input[@value='fedex_FEDEX_2_DAY']");
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
//			Shippingaddress.put("ShippingZip", ShippingZip);
			
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
}

	