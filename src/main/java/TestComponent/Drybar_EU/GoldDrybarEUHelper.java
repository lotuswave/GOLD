package TestComponent.Drybar_EU;

import static org.testng.Assert.fail;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import groovyjarjarantlr.CommonAST;
  
public class GoldDrybarEUHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public GoldDrybarEUHelper(String datafile, String sheetname) {

		excelData = new ExcelReader(datafile, sheetname);
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("Drybar_EU");
			report.createTestcase("Drybar_EUTestCases");
		} else {
			this.report = Utilities.TestListener.report;
		}
 
	}

	public void close_add() throws Exception {
        // TODO Auto-generated method stub
        Thread.sleep(3000);
        int sizesframe = Common.findElements("xpath", "//div[@data-testid='POPUP']").size();
        System.out.println(sizesframe);
        if (sizesframe > 0) {
            Common.actionsKeyPress(Keys.PAGE_UP);
            Thread.sleep(4000);
            Sync.waitElementPresent("xpath", "//button[contains(@class,'needsclick klaviyo-close-form kl-private-reset-css-Xuajs1')]");
            Common.clickElement("xpath", "//button[contains(@class,'needsclick klaviyo-close-form kl-private-reset-css-Xuajs1')]");
        }
        else {

//            Common.switchFrames("xpath", "//div[@class='preloaded_lightbox']/iframe");
            Sync.waitElementPresent("xpath", "(//button[@data-role='closeBtn'])[2]");
            Common.clickElement("xpath", "(//button[@data-role='closeBtn'])[2]");
//            Common.switchToDefault();
            }

 

    }
	

	
	public void Verify_Homepage() {
		// TODO Auto-generated method stub
		try
		{
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Close_Geolocation();
			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			Common.assertionCheckwithReport(
					size > 0 && Common.getPageTitle().contains("Home Drybar")
							|| Common.getPageTitle().contains("Home Drybar") || Common.getPageTitle().contains("Drybar Home"),
					"validating store logo", "System directs the user to the Homepage",
					"Sucessfully user navigates to the home page", "Failed to navigate to the homepage");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating store logo", "System directs the user to the Homepage",
					" user unable navigates to the home page", "Failed to navigate to the homepage");
			Assert.fail();
		}
		
		
	}
			
	public void Close_Geolocation() {
		// TODO Auto-generated method stub
		try {
			if (Common.getCurrentURL().contains("/eu"))
			{
				System.out.println(Common.getCurrentURL());
			}
			else
			{
			Sync.waitElementPresent("xpath", "(//button[@data-role='closeBtn'])[5]");
			Common.clickElement("xpath", "(//button[@data-role='closeBtn'])[5]");
			}
		
	}catch(Exception | Error e)
	{
		e.printStackTrace();
		Assert.fail();
	}
	

			

	}

	public void HairTools_headerlinks(String Dataset) {
		// TODO Auto-generated method stub
		String expectedResult = "User should click the" + Dataset;
		String Brushes = data.get(Dataset).get("Brushes");
		String Detangling = data.get(Dataset).get("Detangling Brushes");
		String header=data.get(Dataset).get("headers");
		try {

			Thread.sleep(3000);
			Sync.waitElementPresent("xpath",
					"//a[contains(@class,'level-top')]//span[contains(text(),'"+ header +"')]");
			
			Common.clickElement("xpath", "//a[contains(@class,'level-top')]//span[contains(text(),'" + header + "')]");

			Thread.sleep(3000);

			try {
				Common.mouseOver("xpath", "//span[contains(text(),'"+ header +"')]");
			} catch (Exception e) {
				Common.clickElement("xpath", "//a[@class='level-top ui-corner-all']//span[text()='"+ header +"']");
			}
			Common.clickElement("xpath", "//span[contains(text(),'" + Brushes + "')]");
			Thread.sleep(4000);
//			Common.clickElement("xpath", "//span[contains(text(),'" + Detangling + "')]");
//			Sync.waitPageLoad();
			Thread.sleep(6000);
			expectedResult = "User should select the " + Dataset + "category";
			int sizebotteles = Common.findElements("xpath", "//span[contains(text(),'"+ header +"')]").size();
			Common.assertionCheckwithReport(sizebotteles > 0,
					"validating the product category as" + Dataset + "from navigation menu ", expectedResult,
					"Selected the " + Dataset + " category", "User unabel to click" + Dataset + "");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the product category as" + Dataset + "from navigation menu ",
					expectedResult, "Unable to Selected the " + Dataset + " category",
					Common.getscreenShot("Failed to click on the" + Dataset + ""));

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
			Thread.sleep(6000);
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.clickElement("xpath", "//img[@alt='" + products + "']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
			
			System.out.println(name);
			Thread.sleep(4000);
			Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"failed to Navigate to the PDP page");
			product_quantity(Dataset);
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//span[text()='Add to Cart']");
			Common.clickElement("xpath", "//span[text()='Add to Cart']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
//			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//					.getAttribute("data-ui-id");
//			System.out.println(message);
//			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
//					"Product should be add to cart", "Sucessfully product added to the cart ",
//					"failed to add product to the cart");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));

			Assert.fail();
		}
	}

	
	public void product_quantity(String Dataset) {
		// TODO Auto-generated method stub
		String Quantity = data.get(Dataset).get("Quantity");
		System.out.println(Quantity);
		try {
			Common.findElement("xpath", "//select[@class='a-select-menu']");
			Common.clickElement("xpath", "//select[@class='a-select-menu']");
			if(Quantity.equals("10+"))
			{
				Common.dropdown("xpath", "//select[@class='a-select-menu']", Common.SelectBy.VALUE, Quantity);
				Thread.sleep(3000);
				String value = Common.findElement("xpath", "//input[@name='qty']").getAttribute("value");
				Common.assertionCheckwithReport(value.contains("10") || value.contains(Quantity) ,
						"validating the  product the product quantity in PDP page",
						"Product quantity should be update in the PDP page",
						"Sucessfully product Qunatity has been updated ",
						"failed to Update the prodcut quantity in PDP page");
				
			}
			else
			{
				Common.dropdown("xpath", "//select[@class='a-select-menu']", Common.SelectBy.VALUE, Quantity);
				Thread.sleep(3000);
				String value = Common.findElement("xpath", "//select[@class='a-select-menu']").getAttribute("value");
				Common.assertionCheckwithReport(value.equals(Quantity),
						"validating the  product the product quantity in PDP page",
						"Product quantity should be update in the PDP page",
						"Sucessfully product Qunatity has been updated ",
						"failed to Update the prodcut quantity in PDP page");
			}
			

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product the product quantity in PDP page",
					"Product quantity should be update in the PDP page", "unable to change the  product Qunatity",
					Common.getscreenShot("failed to update the product quantity"));
			Assert.fail();
		}

	}
	
	
	
	
	
	
	public void minicart_Checkout() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
			click_minicart();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//span[@class='c-mini-cart__total-counter']//strong");
			String minicart = Common.findElement("xpath", "//span[@class='c-mini-cart__total-counter']//strong").getText();
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
					checkout.equals(minicart) || Common.getCurrentURL().contains("checkout/#shipping"),
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
	
	public void click_minicart() {
		try {
			Thread.sleep(8000);
			Common.actionsKeyPress(Keys.UP);
			Sync.waitElementPresent("xpath", "//a[contains(@class,'c-mini')]");
			Common.clickElement("xpath", "//a[contains(@class,'c-mini')]");
			
			String openminicart = Common.findElement("xpath", "//div[@data-block='minicart']").getAttribute("class");
			System.out.println(openminicart);
			if(openminicart.contains("active")) {
			Common.assertionCheckwithReport(openminicart.contains("active"), "To validate the minicart popup",
					"the mini cart is displayed", "Should display the mini cart", "mini cart is not displayed");
			} else {
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//a[contains(@class,'c-mini')]");
			Common.clickElement("xpath", "//a[contains(@class,'c-mini')]");
			String openminicart1 = Common.findElement("xpath", "//div[@data-block='minicart']").getAttribute("class");
			System.out.println(openminicart1);
			Common.assertionCheckwithReport(openminicart1.contains("active"), "To validate the minicart popup",
					"the mini cart is displayed", "Should display the mini cart", "mini cart is not displayed");
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the minicart popup", "the mini cart is displayed",
					"unable to  dislay the mini cart", Common.getscreenShot("Failed to display the mini cart"));
			Assert.fail();

		}
	}

	
	public void addDeliveryAddress_Guestuser(String dataSet) throws Exception {
		String address = data.get(dataSet).get("Street");
		String symbol=data.get(dataSet).get("Symbol");

		try {
			Thread.sleep(5000);
			if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
				Sync.waitElementVisible("xpath", "//input[@type='email']");
				Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Email"));
			} else {
				Sync.waitElementVisible("xpath", "//input[@type='email']");
				Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Prod Email"));
			}

		} catch (NoSuchElementException e) {
			minicart_Checkout();
			Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Email"));
		}
		String expectedResult = "email field will have email address";
		try {
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));
			int size = Common.findElements("xpath", "//input[@type='email']").size();
			Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,
					"Filled Email address", "unable to fill the email address");
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Common.clickElement("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']", address);
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='city']").clear();
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
					data.get(dataSet).get("City"));
			System.out.println(data.get(dataSet).get("City"));

			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			  if(Common.getCurrentURL().contains("gb"))
              {
				  Common.scrollIntoView("xpath", "//input[@placeholder='State/Province']");
					Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", data.get(dataSet).get("Region"));
              }
			  else
			  {
				
				Common.scrollIntoView("xpath", "//select[@name='region_id']");
                Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
                Thread.sleep(3000);
                String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']")
                        .getAttribute("value");
                System.out.println(Shippingvalue);
			}
			Thread.sleep(3000);
			Common.textBoxInputClear("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
			Thread.sleep(5000);
	
			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
			
			String subtotal=Common.findElement("xpath", "//tr[@class='totals sub']//span[@class='price']").getText().replace(symbol, "").replace(".", "");
			System.out.println(subtotal);
			subtotal = subtotal.trim();
			subtotal = subtotal.substring(0,subtotal.length() - 2);
		    System.out.println(subtotal);  
			int amount=Integer.parseInt(subtotal);
			System.out.println(amount);
			if(amount>199 && symbol.equals("$"))
			{
				Sync.waitElementPresent(30, "xpath", "//div[@class='ampromo-close']");
				Common.clickElement("xpath", "//div[@class='ampromo-close']");
				Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
			}
			else
			{
				Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
			}

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
	
	public void selectshippingmethod(String Dataset) {
		// TODO Auto-generated method stub4
		String method = data.get(Dataset).get("methods");
		System.out.println(method);

		try {
			Thread.sleep(20000);
			int size = Common.findElements("xpath", "//label[@class='a-radio-button__label']").size();
			System.out.println(size);
			if (size > 0  ) {
				
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//td[text()='"+ method +"']");
				Common.clickElement("xpath", "//td[text()='"+ method +"']");
			}
			else
			{
				Assert.fail();
				
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Standed shipping method",
					"Select the Standed shipping method in shipping page ",
					"failed to select the Standed shipping method ",
					Common.getscreenShotPathforReport("failed select Standed shipping method"));

			Assert.fail();
		}

	}

	public void clickSubmitbutton_Shippingpage() {
		// TODO Auto-generated method stub
		String expectedResult = "click the submit button to navigate to payment page";
		try {
			Thread.sleep(5000);
			Common.clickElement("xpath", "//button[@data-role='opc-continue']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("checkout/#payment")||Common.getCurrentURL().contains("checkout/#shipping"),
					"validating the navigates to the Checkout page",
					"After clicking on the next button it should navigate to the Checkout page",
					"Successfully navigated to the Checkout page", "Failed to Navigate to the Checkout page");
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the shipping page submitbutton", expectedResult,
					"failed to click the submitbutton",
					Common.getscreenShotPathforReport("failed submitbuttonshippingpage"));
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
//		System.out.println(placeordercount);
//		if (placeordercount > 1) {
//			Thread.sleep(4000);
//
//			Common.clickElement("xpath", "//span[text()='Place Order']");
//			Common.refreshpage();
//		}

		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);

		if (!url.contains("stage") && !url.contains("preprod")) {
		}

		else {
			try {
				Thread.sleep(6000);
				Sync.waitElementPresent(30,"xpath", "//h1[@class='page-title-wrapper']");
				String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();

				//Tell_Your_FriendPop_Up();
				int sizes = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();
				Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
						"verifying the product confirmation", expectedResult,
						"Successfully It redirects to order confirmation page Order Placed",
						"User unabel to go orderconformation page");

				if (Common.findElements("xpath", "//div[@class='checkout-success']//p//span").size() > 0) {
					Thread.sleep(4000);
					order = Common.getText("xpath", "//div[@class='checkout-success']//p//span");
					System.out.println(order);
				} else {
					Thread.sleep(4000);
					order = Common.getText("xpath", "//div[@class='checkout-success']//p//strong");
					System.out.println(order);
				}

				if (Common.findElements("xpath", "//div[@class='checkout-success']//span").size() > 0) {
					Common.getText("xpath", "//div[@class='checkout-success']//span");
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
		String cardnumber = data.get(dataSet).get("cardNumber");
		System.out.println(cardnumber);
		String expectedResult = "land on the payment section";
		// Common.refreshpage();

		try {
			Sync.waitPageLoad();

			Sync.waitElementPresent("xpath", "//label[@for='stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unabel to land opaymentpage");
			Common.clickElement("xpath", "//label[@for='stripe_payments']");

			Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
			int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
			System.out.println(payment);
			if (payment > 0) {
//				Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
//				Common.clickElement("xpath", "//div[@class='stripe-dropdown-selection']");
//				Common.clickElement("xpath", "//span[text()='New payment method']");
				Thread.sleep(4000);
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Thread.sleep(5000);
				Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
				Common.clickElement("xpath", "//label[@for='Field-numberInput']");
				Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);
				Number = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ", "");
				System.out.println(Number);

				Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

				Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {

					Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
             	   Common.clickElement("xpath", "//button[@class='action primary checkout']");
             	   Thread.sleep(10000);
             	  if(Common.getCurrentURL().contains("/checkout/#payment"))
           	   {
           		   Sync.waitElementPresent("xpath", "//label[@for='stripe-new-payments']");
           		   Common.clickElement("xpath", "//label[@for='stripe-new-payments']");
           		   Thread.sleep(5000);
           		   Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
               	   Common.clickElement("xpath", "//button[@class='action primary checkout']");
           		   
           	   }
           	   else if(Common.getCurrentURL().contains("/success/"))
           	   {
           	    String sucessmessage=Common.getText("xpath", "//h1[@class='page-title-wrapper']");
           	    System.out.println(sucessmessage);
           	   }
           	   else
           	   {
           		   Assert.fail();
           	   }
             	   
				} else {
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String Cardnumber = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ",
							"");
					System.out.println(Cardnumber);
					Common.assertionCheckwithReport(Cardnumber.equals(cardnumber),
							"To validate the card details entered in the production environment",
							"user should able to see the card details in the production environment",
							"User Successfully able to see the card details enterd in the production environment ",
							"User Failed to see the card deails in prod environemnt");
					Common.switchToDefault();

				}

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
				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
					Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
	             	   Common.clickElement("xpath", "//button[@class='action primary checkout']");
	             	   Thread.sleep(10000);
	             	  if(Common.getCurrentURL().contains("/checkout/#payment"))
	              	   {
	              		   Sync.waitElementPresent("xpath", "//label[@for='stripe-new-payments']");
	              		   Common.clickElement("xpath", "//label[@for='stripe-new-payments']");
	              		   Thread.sleep(5000);
	              		   Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
	                  	   Common.clickElement("xpath", "//button[@class='action primary checkout']");
	                  	   Thread.sleep(8000);
	                  	 String sucessmessage=Common.getText("xpath", "//h1[@class='page-title-wrapper']");
		              	    System.out.println(sucessmessage);
	              		   
	              	   }
	              	   else if(Common.getCurrentURL().contains("/success/"))
	              	   {
	              	    String sucessmessage=Common.getText("xpath", "//h1[@class='page-title-wrapper']");
	              	    System.out.println(sucessmessage);
	              	   }
	              	   else
	              	   {
	              		   Assert.fail();
	              	   }
	             	   
				} else {
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String Cardnumber = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ",
							"");
					System.out.println(Cardnumber);
					Common.assertionCheckwithReport(Cardnumber.equals(cardnumber),
							"To validate the card details entered in the production environment",
							"user should able to see the card details in the production environment",
							"User Successfully able to see the card details enterd in the production environment ",
							"User Failed to see the card deails in prod environemnt");
					Common.switchToDefault();

				}

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
//		String errorTexts = Common.findElement("xpath", "//div[contains(@class,'error')]").getText();
//
//		Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data",
//				expectedResult, "Filled the Card detiles", "missing field data it showinng error");

		return Number;
	}

		public void search_product(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		System.out.println(product);
		try
		{
        Common.clickElement("xpath", "//span[contains(@class,'drybar-icon-search')]");
     	String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
     	Thread.sleep(4000);
     	Common.assertionCheckwithReport(open.contains("active"), "User searches using the search field",
     	"User should able to click on the search button", "Search expands to the full page",
     	"Sucessfully search bar should be expand"); 
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
	
		
		public void Configurable_addtocart(String Dataset) {
			// TODO Auto-generated method stub
			
			String products = data.get(Dataset).get("Products");
			String Productsize= data.get(Dataset).get("size");
			String scent=data.get(Dataset).get("scent");
			System.out.println(Productsize);
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
				if(Common.getCurrentURL().contains("/gb"))
				{
					Sync.waitPageLoad(30);
					Thread.sleep(6000);
					Thread.sleep(4000);
					Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
					Common.clickElement("xpath", "//img[@alt='" + products + "']");
					Sync.waitElementPresent("xpath", "//div[@data-option-label='" + scent + "']");
					Common.clickElement("xpath", "//div[@data-option-label='" + scent + "']");
					Sync.waitElementPresent("xpath", "(//div[@data-option-label='" + Productsize + "'])[1]");
					Common.clickElement("xpath", "(//div[@data-option-label='" + Productsize + "'])[1]");
					String size=Common.findElement("xpath", "(//span[contains(@class,'m-swatch-group__header s')])[2]").getText().toUpperCase();
					System.out.println(size);
					String size1= data.get(Dataset).get("size").toUpperCase();
					System.out.println(size1);
					Common.assertionCheckwithReport(
							size.equals(size1),
							"Verifying the the size of the product is selected in the PDP",
							"after clicking on the size product size should be selected",
							"successfully Product size has been selected on the PDP",
							"Failed to select the product price on the PDP");
					product_quantity(Dataset);
					Thread.sleep(4000);
					
					Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
					Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
					Sync.waitPageLoad();
					Thread.sleep(6000);
//					String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//							.getAttribute("data-ui-id");
//					System.out.println(message);
//					Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
//							"Product should be add to cart", "Sucessfully product added to the cart ",
//							"failed to add product to the cart");
				}
				else
				{
					
				Sync.waitPageLoad(30);
				Thread.sleep(6000);
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
				Common.clickElement("xpath", "//img[@alt='" + products + "']");
				Sync.waitElementPresent("xpath", "//div[@data-option-label='" + Productsize + "']");
				Common.clickElement("xpath", "//div[@data-option-label='" + Productsize + "']");
				String size=Common.findElement("xpath", "(//span[contains(@class,'m-swatch-group__header s')])[2]").getText().toUpperCase();
				System.out.println(size);
				String size1= data.get(Dataset).get("size").toUpperCase();
				System.out.println(size1);
				Common.assertionCheckwithReport(
						size.equals(size1),
						"Verifying the the size of the product is selected in the PDP",
						"after clicking on the size product size should be selected",
						"successfully Product size has been selected on the PDP",
						"Failed to select the product price on the PDP");
				product_quantity(Dataset);
				Thread.sleep(4000);
				
				Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
				Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
				Sync.waitPageLoad();
				Thread.sleep(6000);
//				String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//						.getAttribute("data-ui-id");
//				System.out.println(message);
//				Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
//						"Product should be add to cart", "Sucessfully product added to the cart ",
//						"failed to add product to the cart");
			}
			}
			catch(Exception | Error e)
			{
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
						"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));
				Assert.fail();
			}
		}

		
		
		public void minicart_viewcart() {
			// TODO Auto-generated method stub
			try {
				Sync.waitElementPresent("xpath", "//span[@class='c-mini-cart__total-counter']//strong");
				String minicart = Common.findElement("xpath", "//span[@class='c-mini-cart__total-counter']//strong").getText();
				Sync.waitElementPresent("xpath", "//span[text()='View Cart']");
				Common.clickElement("xpath", "//span[text()='View Cart']");
				String viewcart = Common.findElement("xpath", "//span[@class='t-cart__items-count']").getText();
				Sync.waitPageLoad();
				Thread.sleep(8000);
				Common.assertionCheckwithReport(
						viewcart.contains(minicart) || Common.getCurrentURL().contains("/checkout/cart/"),
						"validating the navigation to the view cart", "User should able to navigate to the view cart page",
						"Successfully navigates to the view cart page",
						"Failed to navigate to the view and edit cart page");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the navigation to the view cart",
						"User should able to navigate to the view cart page", "unable to  navigates to the view cart page",
						Common.getscreenShot("Failed to navigate to the view cart page"));

				Assert.fail();

			}

		}
		
		
		
		public void Remove_Product(String dataSet) {
			// TODO Auto-generated method stub
			String Symbol = data.get(dataSet).get("Symbol");
			String products = data.get(dataSet).get("Products");

			try {
				Thread.sleep(4000);
				String subtotal = Common.findElement("xpath", "//span[@data-th='Subtotal']").getText().replace(Symbol, "");
				Float subtotalvalue = Float.parseFloat(subtotal);
				System.out.println(subtotalvalue);
				String Productprice = Common.getText("xpath", "(//td[@data-th='Subtotal']//span[@class='price'])[2]")
						.replace(Symbol, "");
				Float pricevalue = Float.parseFloat(Productprice);
				System.out.println(pricevalue);
				Float Total = subtotalvalue - pricevalue;
				String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				System.out.println(ExpectedTotalAmmount2);
				Sync.waitElementPresent("xpath", "//span[text()='Remove " + products + "']");
				Common.clickElement("xpath", "//span[text()='Remove " + products + "']");
				Sync.waitPageLoad(30);
				Thread.sleep(5000);
				String ordertotal = Common.getText("xpath", "//td[@data-th='Order Total']//span[@class='price']")
						.replace(Symbol, "");
				Thread.sleep(4000);
				Float ordervalue = Float.parseFloat(ordertotal);
				System.out.println(ExpectedTotalAmmount2);
				System.out.println(ordertotal);
				Common.assertionCheckwithReport(ExpectedTotalAmmount2.equals(ordertotal),
						"validating the remove prodcut form shopping cart page",
						"Product should be remove form the shopping cart page",
						"Sucessfully Product removed from the shopping cart page",
						"Failed to remove the product from the shopping cart page");
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the remove prodcut form shopping cart page",
						"Product should be remove form the shopping cart page",
						"Unable to remove the product from the shopping cart page",
						Common.getscreenShot("Failed to remove the product from the shopping cart page"));
				Assert.fail();
			}

		}

		
		
		public void update_shoppingcart(String Dataset) {
			// TODO Auto-generated method stub
			String quantity = data.get(Dataset).get("Quantity");
			try {
				
				Sync.waitElementClickable("xpath", "//select[@class='a-form-elem a-select-menu']");
				Common.clickElement("xpath", "//select[@class='a-form-elem a-select-menu']");
				Common.dropdown("xpath", "//select[@class='a-form-elem a-select-menu']", Common.SelectBy.VALUE, quantity);
				Common.clickElement("xpath", "//span[text()='Update']");
				Sync.waitPageLoad();
				Thread.sleep(14000);
				String productquantity = Common.findElement("xpath", "//select[@class='a-form-elem a-select-menu']")
						.getAttribute("value");
				System.out.println(productquantity);
				String items=Common.findElement("xpath", "//span[@class='t-cart__items-count']").getText().replace("  Items  in your cart", "");
				System.out.println(items);
				Common.assertionCheckwithReport(productquantity.equals(quantity) || productquantity.equals(items),
						"validating the update quantity in shopping cart page",
						"Quantity should be update in the shopping cart page",
						"Qunatity has been updated in the shopping cart page",
						"Failed to update the product quantity in the shopping cart page");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the update quantity in shopping cart page",
						"Quantity should be update in the shopping cart page",
						"Unable to update the product quantity in the shopping cart page",
						Common.getscreenShot("Failed to update the product quantity in the shopping cart page"));
				Assert.fail();
			}

		}

		public void Shoppingcart_page() {
			// TODO Auto-generated method stub
			try {
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Sync.waitElementVisible(30, "xpath", "//span[text()='Back to Cart']");
				Common.clickElement("xpath", "//span[text()='Back to Cart']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.assertionCheckwithReport(Common.getPageTitle().equals("Shopping Cart"),
						"validating the navigates to the shopping cart page",
						"After clicking on the reorder it should navigate to the shopping cart page",
						"Successfully navigated to the shopping cart page", "Failed to Navigate to the shopping cart page");
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the navigates to the shopping cart page",
						"After clicking on the reorder it should navigate to the shopping cart page",
						"Unable to Navigate to the shopping cart page",
						Common.getscreenShotPathforReport("Failed to Navigate to the shopping cart page"));
				Assert.fail();
			}

		}

		public void minicart_ordersummary_discount(String Dataset) {
			// TODO Auto-generated method stub.
			String expectedResult = "It should opens textbox input to enter discount.";
			String Symbol = data.get(Dataset).get("Symbol");
			try {
				Sync.waitElementPresent("xpath", "//span[text()='Add Discount Code']");
				Common.clickElement("xpath", "//span[text()='Add Discount Code']");

				Sync.waitElementPresent("xpath", "//input[@name='coupon_code']");
				if (Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("preprod") ) {
					Common.textBoxInput("xpath", "//input[@name='coupon_code']", data.get(Dataset).get("Discountcode"));
				} else {
					Common.textBoxInput("xpath", "//input[@name='coupon_code']", data.get(Dataset).get("prodDiscountcode"));
				}
				int size = Common.findElements("xpath", "//input[@name='coupon_code']").size();
				Common.assertionCheckwithReport(size > 0, "verifying the Discount Code label", expectedResult,
						"Successfully open the discount input box", "User unable enter Discount Code");
				Sync.waitElementClickable("xpath", "//button[@value='Add']");
				Common.clickElement("xpath", "//button[@value='Add']");
				Sync.waitPageLoad();
				expectedResult = "It should apply discount on your price.If user enters invalid promocode it should display coupon code is not valid message.";
				if (Common.getCurrentURL().contains("Stage") || Common.getCurrentURL().contains("preprod")) {
					String discountcodemsg = Common.getText("xpath", "//div[@data-ui-id='message-success']");
					Common.assertionCheckwithReport(discountcodemsg.contains("You used coupon code"), "verifying pomocode",
							expectedResult, "promotion code working as expected", "Promation code is not applied");
				} else {
					String discountcodemsg = Common.getText("xpath", "//div[@data-ui-id='message-success']//div");
					Common.assertionCheckwithReport(discountcodemsg.contains("You used coupon code"), "verifying pomocode",
							expectedResult, "promotion code working as expected", "Promation code is not applied");
				}
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the promocode in the shopping cart page",
						"Promocode should be apply in the shopping cart page",
						"Unable to display the promocode in the shopping cart page",
						Common.getscreenShot("Failed to display the promocode in the shopping cart page"));
				Assert.fail();
			}
			try {
				if(Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("preprod") )
				{
				Thread.sleep(6000);
				String Subtotal = Common.getText("xpath", "//tr[@class='totals sub']//span[@class='price']").replace(Symbol,
						"");
				Float subtotalvalue = Float.parseFloat(Subtotal);
				if(Common.getCurrentURL().contains("/gb")|| Common.getCurrentURL().contains("/eu")) {
					
					String shipping = Common.getText("xpath", "//tr[@class='totals shipping incl']//span[@class='price']")
							.replace(Symbol, "");
					Float shippingvalue = Float.parseFloat(shipping);
					
					System.out.println("Shipping:"+  shippingvalue);
					String Discount = Common.getText("xpath", "//tr[@class='totals discount']//span[@class='price']")
							.replace(Symbol, "");
					Float Discountvalue = Float.parseFloat(Discount);
					System.out.println("Discount:"+ Discountvalue);
					String Tax = Common.getText("xpath", "//tr[@class='totals-tax']//span[@class='price']").replace(Symbol, "");
					Float Taxvalue = Float.parseFloat(Tax);
					System.out.println("Tax:"+  Taxvalue);
					String ordertotal = Common.getText("xpath", "//tr[@class='grand totals']//span[@class='price']")
							.replace(Symbol, "");
					Float ordertotalvalue = Float.parseFloat(ordertotal);
					System.out.println("Order Total"+   ordertotalvalue);
					Float Total = (subtotalvalue + shippingvalue) + Discountvalue;
					
					String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					System.out.println(ExpectedTotalAmmount2);
					System.out.println(ordertotal);
					Common.assertionCheckwithReport(ExpectedTotalAmmount2.equals(ordertotal),
							"validating the order summary in the payment page",
							"Order summary should be display in the payment page and all fields should display",
							"Successfully Order summary is displayed in the payment page and fields are displayed",
							"Failed to display the order summary and fileds under order summary");
				}
				else {
					String shipping = Common.getText("xpath", "//tr[@class='totals shipping excl']//span[@class='price']")
							.replace(Symbol, "");
					Float shippingvalue = Float.parseFloat(shipping);
					String Discount = Common.getText("xpath", "//tr[@class='totals discount']//span[@class='price']")
							.replace(Symbol, "");
					Float Discountvalue = Float.parseFloat(Discount);
					String Tax = Common.getText("xpath", "//tr[@class='totals-tax']//span[@class='price']").replace(Symbol, "");
					Float Taxvalue = Float.parseFloat(Tax);
					String ordertotal = Common.getText("xpath", "//tr[@class='grand totals']//span[@class='price']")
							.replace(Symbol, "");
					Float ordertotalvalue = Float.parseFloat(ordertotal);
					Float Total = (subtotalvalue + shippingvalue + Taxvalue) + Discountvalue;
					String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					System.out.println(ExpectedTotalAmmount2);
					System.out.println(ordertotal);
					Common.assertionCheckwithReport(ExpectedTotalAmmount2.equals(ordertotal),
							"validating the order summary in the payment page",
							"Order summary should be display in the payment page and all fields should display",
							"Successfully Order summary is displayed in the payment page and fields are displayed",
							"Failed to display the order summary and fileds under order summary");
				}
				
				
				}
				else
				{
					String Subtotal = Common.getText("xpath", "//tr[@class='totals sub']//span[@class='price']").replace(Symbol,
							"");
					Float subtotalvalue = Float.parseFloat(Subtotal);
					String shipping = Common.getText("xpath", "//tr[@class='totals shipping excl']//span[@class='price']")
							.replace(Symbol, "");
					Float shippingvalue = Float.parseFloat(shipping);
					String Discount = Common.getText("xpath", "//tr[@class='totals discount']//span[@class='price']")
							.replace(Symbol, "");
					Float Discountvalue = Float.parseFloat(Discount);
					String Tax = Common.getText("xpath", "//tr[@class='totals-tax']//span[@class='price']").replace(Symbol, "");
					Float Taxvalue = Float.parseFloat(Tax);
					String ordertotal = Common.getText("xpath", "//tr[@class='grand totals']//span[@class='price']")
							.replace(Symbol, "");
					Float ordertotalvalue = Float.parseFloat(ordertotal);
					Float Total = (subtotalvalue + shippingvalue) + Discountvalue;
					String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					System.out.println(ExpectedTotalAmmount2);
					System.out.println(ordertotal);
					Common.assertionCheckwithReport(ExpectedTotalAmmount2.equals(ordertotal),
							"validating the order summary in the payment page",
							"Order summary should be display in the payment page and all fields should display",
							"Successfully Order summary is displayed in the payment page and fields are displayed",
							"Failed to display the order summary and fileds under order summary");
				}

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the order summary in the payment page",
						"Order summary should be display in the payment page and all fields should display",
						"Unabel to display the Order summary and fields are not displayed in the payment page",
						Common.getscreenShot("Failed to display the order summary and fileds under order summary"));
				Assert.fail();
			}

			
		}
		
		
}


			


		



			





		


		



			





		

	