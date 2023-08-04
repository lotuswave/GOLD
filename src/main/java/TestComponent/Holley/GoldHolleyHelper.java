package TestComponent.Holley;

import static org.testng.Assert.fail;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
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

public class GoldHolleyHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public GoldHolleyHelper(String datafile, String sheetname) {

		excelData = new ExcelReader(datafile, sheetname);
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("Hydro");
			report.createTestcase("HydroTestCases");
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

	public void verifingHomePage() {
		try {
			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "//a[@class='nav_logo']").size();
			System.out.println(size);
			Common.assertionCheckwithReport(
					size >= 0 && Common.getPageTitle().contains("Holley"),
					"validating the Holley home page", "System directs the user to the Homepage",
					"Sucessfully user navigates to the Holley home page", "Failed to navigate to the Holley homepage");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Holley home page", "System directs the user to the Homepage",
					"Unable to navigate to the Holley homepage", "Failed to navigate to the Holley homepage");
			Assert.fail();
		}

	}

	public void Ac_and_Heating_Category(String category) {
		// TODO Auto-generated method stub
		String expectedResult = "User should click the" + category;
		try {

			Common.clickElement("xpath", "//a[contains(text(),'Categories')]");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//a[contains(text(),'AC and Heating')]");
			Common.clickElement("xpath", "//div[text()='" + category +"']");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			expectedResult = "User should select the " + category + "category";
			int sizebotteles = Common.findElements("xpath", "//span[contains(text(),'AC and Heating')]").size();
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
				Sync.waitElementPresent("xpath", "//img[contains(@class,'grid-product-image')]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@class,'grid-product-image')]");

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
			String name = Common.findElement("xpath", "//div[@class='product-detail']//h1").getText();
			System.out.println(name);
			String product = data.get(Dataset).get("Products").toUpperCase();
			System.out.println(products);
			Common.assertionCheckwithReport(name.contains(product), "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"Failed to Navigate to the PDP page");
//			product_quantity(Dataset);
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[text()='Add To Cart']");
			Common.clickElement("xpath", "//button[text()='Add To Cart']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@id='cartDialogShippingMsg']//p")
					.getText();
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("Congratulations"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));

			Assert.fail();
		}
	}

	public void shoppingcart_Checkout() {
		// TODO Auto-generated method stub
		try
		{
			Sync.waitElementPresent("xpath", "//button[text()='Cart & Checkout']");
			Common.clickElement("xpath", "//button[text()='Cart & Checkout']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String checkout=Common.findElement("xpath", "//div[@class='checkoutInfoTitle']").getText();
			System.out.println(checkout);
			Common.assertionCheckwithReport(checkout.contains("SHOPPING CART"), "validating the page navigated to the shopping cart",
					"After clicking checkout it should navigate to the shopping cart page", "Sucessfully Navigated to the shopping cart page",
					"failed to Navigate to the shopping cart page");
			Sync.waitElementPresent("xpath", "//div[text()='Proceed to Checkout']");
			Common.clickElement("xpath", "//div[text()='Proceed to Checkout']");
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Holley Shopping Cart"), "Validating the page navigated to the checkout cart",
					"After clicking checkout it should navigate to the checkout page", "Sucessfully Navigated to the checkout  page",
					"Failed to Navigate to the checkout cart page");
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the page navigated to the checkout cart",
					"After clicking checkout it should navigate to the checkout page", "Unable to Navigate to the checkout cart page", Common.getscreenShot("Failed to Navigate to the checkout cart page"));
           Assert.fail();
		}
		
	}

	public void Guest_delivery_Address(String dataSet) {
		// TODO Auto-generated method stub
		
			String address = data.get(dataSet).get("Street");
			String expectedResult = "email field will have email address";
			try {
				Common.textBoxInput("xpath", "//div[@class='halfcontainer labeloverlay']//input[@name='ShippingFirstName']",
						data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Email"));
				int size = Common.findElements("xpath", "//input[@type='email']").size();
				Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,
						"Filled Email address", "unable to fill the email address");
				Common.textBoxInput("xpath", "//div[@class='halfcontainer labeloverlay']//input[@name='ShippingLastName']",
						data.get(dataSet).get("LastName"));
				Common.clickElement("xpath", "//div[@class='halfcontainer labeloverlay']//input[@name='ShippingAddress']");
				Common.textBoxInput("xpath", "//div[@class='halfcontainer labeloverlay']//input[@name='ShippingAddress']", address);
				Sync.waitPageLoad();
				Thread.sleep(5000);
				Common.findElement("xpath", "//div[@class='thirdcontainer labeloverlay']//input[@name='ShippingCity']").clear();
				Common.textBoxInput("xpath", "//div[@class='thirdcontainer labeloverlay']//input[@name='ShippingCity']",
						data.get(dataSet).get("City"));
				System.out.println(data.get(dataSet).get("City"));

//				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				try {
					Common.scrollIntoView("xpath", "//select[@id='ShippingState']");
					Common.dropdown("xpath", "//select[@id='ShippingState']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));					
				} catch (ElementClickInterceptedException e) {
					Thread.sleep(3000);
					Common.dropdown("xpath", "//select[@id='ShippingState']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));	
				}
				Thread.sleep(3000);
				Common.textBoxInputClear("xpath", "//input[@name='ShippingPostalCode']");
				Common.textBoxInput("xpath", "//input[@name='ShippingPostalCode']", data.get(dataSet).get("postcode"));
				Thread.sleep(5000);

				Common.textBoxInput("name", "ShippingPhone", data.get(dataSet).get("phone"));

				Sync.waitPageLoad();
				ExtenantReportUtils.addPassLog("validating shipping address filling Fileds",
						"shipping address is filled in to the fields", "user should able to fill the shipping address ",
						Common.getscreenShotPathforReport("Sucessfully shipping address details has been entered"));

			}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating shipping address in the shipping form",
					"shipping address is filled in to the fields", "user Failed to fill the shipping address in the shipping form",
					Common.getscreenShotPathforReport("Failed to fill the address in the shipping form"));
			Assert.fail();
		}
		
	}

	public void selectshippingmethod(String Dataset) {
//		 TODO Auto-generated method stub4	
		 String method = data.get(Dataset).get("methods");
		 System.out.println(method);

		try {
			Thread.sleep(3000);
			int size = Common.findElements("xpath", "//div[@class='deliveryoptions']").size();
			System.out.println(size);
			if (size > 0) {

				Common.clickElement("xpath", "//p[contains(text(),'" + method + "')]");
				String delivery=Common.findElement("xpath", "(//p[@class='deliverycost'])[2]").getText();
				 System.out.println(delivery);
				 Thread.sleep(4000);
				String Shipping=Common.findElement("xpath", "//span[@id='shHolder']").getText();
				 System.out.println(Shipping);
				Thread.sleep(4000);
				
				Common.assertionCheckwithReport(delivery.contains(Shipping), "Validating the shipping method in the payment page",
						"After clicking delivery options shipping method should selected", "Sucessfully shipping method is selected int payment page",
						"Failed to select the shipping method in the payment page");
				
			} else {

				Assert.fail();

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Next day shipping method",
					"Select the Next day shipping method in shipping page ",
					"Unable to select the Next day shipping method ",
					Common.getscreenShotPathforReport("Failed select Standed shipping method"));

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
				Common.switchFrames("xpath", "//iframe[@class='i-framecenpos']");
				Thread.sleep(5000);
				Common.scrollIntoView("xpath", "//input[@name='cardNumber']");
				Common.clickElement("xpath", "//input[@name='cardNumber']");
				Common.findElement("id", "creditCardNumber").sendKeys(cardnumber);

				Common.textBoxInput("xpath", "//input[@name='month']", data.get(dataSet).get("ExpMonth"));
				Common.textBoxInput("xpath", "//input[@name='year']", data.get(dataSet).get("ExpYear"));

				Common.textBoxInput("xpath", "//input[@name='cvv']", data.get(dataSet).get("cvv"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Thread.sleep(4000);
				String error=Common.findElement("xpath", "//label[@class='errormsg']").getText();
				System.out.println(error);
				Common.assertionCheckwithReport(error.contains("Please enter at least 3 characters."), "Validating the error message in the payment page",
						"error message should be appear", "Sucessfully error message has been appeared",
						"Failed to appear the error message in the payment page");
				Common.switchToDefault();
				
}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			Assert.fail();
		}
	}
}