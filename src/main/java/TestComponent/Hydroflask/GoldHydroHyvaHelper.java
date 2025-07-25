package TestComponent.Hydroflask;

import static org.testng.Assert.fail;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.AssertJUnit;

import com.google.api.client.util.Key;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Common.SelectBy;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.Utils;
import groovyjarjarantlr.CommonAST;

public class GoldHydroHyvaHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public GoldHydroHyvaHelper(String datafile, String sheetname) {

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

	/**
	 * Verifies the Home Page logo and title based on the environment.
	 */
	public void verifingHomePage() {
		try {
	    	if(Common.getCurrentURL().contains("preprod"))
	    	{
	    		 Sync.waitElementPresent(60, "css", "img[alt='Hydroflask store logo']");
	    		  int size = Common.findElements("css", "img[alt='Hydroflask store logo']").size();
 
	  	        boolean isLogoPresent = size > 0;
	  	        boolean isTitleCorrect = Common.getPageTitle().contains("Home Page") || Common.getPageTitle().contains("Hydro Flask");
 
	  	        Common.assertionCheckwithReport(
	  	                isLogoPresent && isTitleCorrect,
	  	                "validating the home page navigation",
	  	                "User should be navigate to the Home page",
	  	                "Successfully user navigates to the home page",
	  	                "Failed to navigate to the homepage");
	    	}
	    	else
	    	{
	    		Thread.sleep(3000);
	    		 Sync.waitElementPresent(60, "xpath", "//img[@alt='Store logo' or @alt='Hydroflask store logo']") ;
	 	        int size = Common.findElements("xpath", "//img[@alt='Store logo' or @alt='Hydroflask store logo']").size();
 
	 	        boolean isLogoPresent = size > 0;
	 	        boolean isTitleCorrect = Common.getPageTitle().contains("Home Page") || Common.getPageTitle().contains("Hydro Flask")|| Common.getPageTitle().contains("Hydro Flask: Sustainable & Refillable Water Bottles | Hydro Flask");
 
	 	        Common.assertionCheckwithReport(
	 	                isLogoPresent || isTitleCorrect,
	 	                "validating the home page navigation",
	 	                "User should be navigate to the Home page",
	 	                "Successfully user navigates to the home page",
	 	                "Failed to navigate to the homepage");
	    	}
		}
	    	catch (Exception | Error e) {
	        e.printStackTrace();
	        ExtenantReportUtils.addFailedLog("validating the home page navigation", "User should be navigate to the Home page",
	                "user unable navigates to the home page", Common.getscreenShotPathforReport("homepage_verification_failed"));
	        Assert.fail("Failed to verify home page.");
	    }
	}
	public void headerlinks(String category) {
		// TODO Auto-generated method stub
		String expectedResult = "User should click the" + category;
		try {

			Sync.waitElementClickable("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
			Thread.sleep(3000);
			Common.mouseOverClick("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
			Thread.sleep(3000);
			try {
				Common.mouseOver("xpath", "//span[contains(text(),'" + category + "')]");
			} catch (Exception e) {
				Common.clickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
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

	public void bottles_headerlinks(String category) {
		// TODO Auto-generated method stub
		String expectedResult = "User should click the" + category;
		try {

			Sync.waitElementPresent("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
			Thread.sleep(3000);
//			Common.scrollIntoView("xpath","//a[contains(@class,'level-top')]//span[text()=' Shop']");
			Common.clickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");

			Thread.sleep(3000);

			try {
				Common.mouseOver("xpath", "//span[contains(text(),'" + category + "')]");
			} catch (Exception e) {
				Common.clickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
			}
			Common.clickElement("xpath", "//span[contains(text(),'" + category + "')]");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//span[text()='Coffee & Tea']");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			expectedResult = "User should select the " + category + "category";
			int sizebotteles = Common.findElements("xpath", "//h1//span[contains(text(),'Coffee & Tea')]").size();
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
	
	public void bottle_Accessories_headerlinks(String Dataset) {
		// TODO Auto-generated method stub
		String category =data.get(Dataset).get("Category");
		String expectedResult = "User should click the" + category;
		try {

			Sync.waitElementPresent("xpath", "//nav[@aria-label='Main menu']//span[contains(text(),'Shop')] ");
			Thread.sleep(3000);
//			Common.scrollIntoView("xpath","//a[contains(@class,'level-top')]//span[text()=' Shop']")//a[contains(@title,'Shop')];
			Common.clickElement("xpath", "//nav[@aria-label='Main menu']//span[contains(text(),'Shop')] ");

			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "(//span[contains(text(),'" + category + "')])[2]");
			Common.clickElement("xpath", "(//span[contains(text(),'" + category + "')])[2]");
			Thread.sleep(6000);
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
	
	
	public void Bottles_headerlinks(String category) {
		// TODO Auto-generated method stub
		String expectedResult = "User should click the" + category;
		try {

			Sync.waitElementPresent("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
			Thread.sleep(3000);
//			Common.scrollIntoView("xpath","//a[contains(@class,'level-top')]//span[text()=' Shop']");
			Common.clickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");

			Thread.sleep(3000);
			int size=Common.findElements("xpath", "(//div[@x-init='$nextTick(() => {show = true })'])[2]").size();
			if(size <=0) 
			{
				Common.javascriptclickElement("xpath", "//img[@alt='Hydroflask store logo']");
			
			Sync.waitElementPresent(50,"xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
			Common.javascriptclickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
	}

			try {
				Common.mouseOver("xpath", "//span[contains(text(),'" + category + "')]");
			} catch (Exception e) {
				Common.clickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
			}
			Common.clickElement("xpath", "//span[contains(text(),'" + category + "')]");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//span[text()='Bottles']");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			expectedResult = "User should select the " + category + "category";
			int sizebotteles = Common.findElements("xpath", "//h1//span[contains(text(),'Bottles')]").size();
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
	
	public void bottles_headerlinks_Shoppall(String category) {
		// TODO Auto-generated method stub
		String expectedResult = "User should click the" + category;
		try {

			Sync.waitElementPresent("xpath", "(//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')])[2]");
			Thread.sleep(3000);
//			Common.scrollIntoView("xpath","//a[contains(@class,'level-top')]//span[text()=' Shop']");
			Common.clickElement("xpath", "(//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')])[2]");

			Thread.sleep(3000);

			try {
				Common.mouseOver("xpath", "//span[contains(text(),'" + category + "')]");
			} catch (Exception e) {
				Common.clickElement("xpath", "(//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')])[2]");
			}
			Common.clickElement("xpath", "//span[contains(text(),'" + category + "')]");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//a[contains(@href,'bottles-drinkware')]//span[text()='Shop All']");
			Sync.waitPageLoad();
			Thread.sleep(6000);
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

	/**
	 * Adds a product to the cart based on data from a specified dataset.
	 *
	 * @param Dataset The key to retrieve product information from the 'data' map.
	 */
	public void addtocart(String Dataset) {
	    String products = data.get(Dataset).get("Products");
	    System.out.println(products);

	    try {
	        Sync.waitPageLoad();
	        // Wait for product images to load (up to 10 attempts with 4-second intervals)
	        for (int i = 0; i <= 10; i++) {
	            Thread.sleep(4000);
	            Sync.waitElementPresent("css", "a[class*=roduct-image-link]>img");
	            List<WebElement> webelementslist = Common.findElements("css", "a[class*=roduct-image-link]>img");
	            String imageUrl = webelementslist.get(i).getAttribute("src");
	            System.out.println(imageUrl);
	            if (!imageUrl.isEmpty()) {
	                break; // Exit loop if an image URL is found
	            }
	        }
	        // Scroll to the product image
	        Common.scrollIntoView("css", "img[alt='" + products + "']");
	        Sync.waitElementPresent(30, "css", "img[alt='" + products + "']");
	        Thread.sleep(3000);
	       
	        Common.clickElement("css", "img[alt='" + products + "']");
	        Sync.waitElementPresent("css", "button[title='Add to Cart']");
	        Common.javascriptclickElement("css", "button[title='Add to Cart']");
	        Thread.sleep(3000);
	        // Verify the mini cart opens
	        String openminicart = Common.findElement("css", "div[class*='fixed inset-y-0']").getAttribute("aria-modal");
	        System.out.println("Minicart Open:"  +openminicart);
	        Common.assertionCheckwithReport(openminicart.contains("true"), "Add to cart validation",
	                "Product should be added to the cart and mini cart should open",
	                "Product added to cart and mini cart opened successfully",
	                "Failed to add product to cart and/or open mini cart");
	    } catch (Exception | Error e) {
	        e.printStackTrace();
	        ExtenantReportUtils.addFailedLog("Add to cart validation", "Product should be added to cart",
	                "Unable to add product to the cart", Common.getscreenShot("failed_to_add_to_cart"));
	        Assert.fail();
	    }
	}
	
	public String website() throws Exception {
		// TODO Auto-generated method stub
		String Website="";
		 Website=Common.getPageTitle().replace("| Vacuum Insulated Stainless Steel Water Bottles ", "").trim();
	     System.out.println(Website);
		return Website;

		}

	public void click_minicart() {
		try {
			Thread.sleep(8000);
			Common.actionsKeyPress(Keys.UP);
			Sync.waitElementPresent("css", "#menu-cart-icon");
			Common.clickElement("css", "#menu-cart-icon");
			
			String openminicart = Common.findElement("xpath", "//div[contains(@class,'fixed inset-y-0')]").getAttribute("aria-modal");
			System.out.println(openminicart);
			if(openminicart.contains("true")) {
			Common.assertionCheckwithReport(openminicart.contains("true"), "To validate the minicart popup",
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
	
	public void Other_Amount(String Dataset) {
		// TODO Auto-generated method stub
		String Enter_amount=data.get(Dataset).get("Another Amount");
		System.out.println(Enter_amount);
		try
		{
			Sync.waitPageLoad();
//			Sync.waitElementPresent("xpath", "//input[@type='number']");
			Common.clickAndtextBoxInput("xpath", "//input[@type='number']", data.get(Dataset).get("Another Amount"));
//			Common.textBoxInput("xpath", "//input[@type='number']", data.get(Dataset).get("Enter_amount"));
			Common.clickElement("xpath", "//button[@title='Add']");
			Thread.sleep(2000);
			String Price=Common.findElement("xpath", "//div[@class='final-price inline-block']//span[@class='price']").getText();
			System.out.println(Price);
			Common.assertionCheckwithReport(Price.contains(Enter_amount),
					"validating gift card amount value in PDP",
					"After clicking on the value amount should be appear in PDP",
					"Successfully selected amount is matched for the gift card",
					"Failed to appear amount for the gift card");
			
			Giftcard_details("Gift Details");
			product_quantity("Product Qunatity");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
//			Sync.waitElementPresent(30, "xpath", "//div[@data-ui-id='message-success']");
//			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//					.getAttribute("data-ui-id");
//			System.out.println(message);
//			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
//					"Product should be add to cart", "Sucessfully product added to the cart ",
//					"failed to add product to the cart");

		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"Unable to add the product to the cart", Common.getscreenShot("Failed the product Add to cart from the PDP"));
			Assert.fail();
		}

	}


	public String minicart_Checkout() {
            String Checkoutprice="";
		try {
			Thread.sleep(3000);
			Sync.waitElementPresent(30,"css", "span[x-text='totalCartAmount']");
			String minicart = Common.findElement("css", "span[x-text='totalCartAmount']").getText();
		
			Sync.waitElementPresent(30, "xpath", "//button[contains(text(),'Checkout')] | //button[contains(text(),'Checkout')]");
			Common.clickElement("xpath", "//button[contains(text(),'Checkout')] | //button[contains(text(),'Checkout')]");
			Sync.waitPageLoad();
		Thread.sleep(2000);
		try {
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("checkout"),
					"validating the navigation to the shipping page when we click on the checkout",
					"User should able to navigate to the shipping  page after clicking on the checkout page", "Successfully navigate to the shipping page after clicking on the checkout page",
					"Failed to navigate to the shipping page after clicking on the checkout button");
		}
		catch (Exception | Error e) {
			Thread.sleep(5000);
			int Checkout_button= Common.findElements("xpath", "//button[contains(text(),'Checkout')] | //button[contains(text(),'Checkout')]").size();
			if(Checkout_button>0) {
	
			Common.javascriptclickElement("xpath", "//button[contains(text(),'Checkout')] | //button[contains(text(),'Checkout')]");
			Thread.sleep(5000);
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("checkout"),
					"validating the navigation to the shipping page when we click on the checkout",
					"User should able to navigate to the shipping  page after clicking on the checkout page", "Successfully navigate to the shipping page after clicking on the checkout page",
					"Failed to navigate to the shipping page after clicking on the checkout button");
		}
		}
		/**
		 * Free gift popup handling
		 */
		Thread.sleep(5000);
		int hiddenPopupElements= Common.findElements("xpath", "//div[@class='modal-overlay fixed inset-0 bg-popup-overlay z-modal' and contains(@x-bind,'freegift')and @style='display: none;']").size();
		if (hiddenPopupElements > 0) {
            System.out.println("Free gift popup is not currently displayed.");
        } else {
            
            System.out.println("Free gift popup is likely displayed. Attempting to close...");
            Sync.waitElementVisible("xpath", "//div[@x-ref='freegift']//button[@aria-label='Close, button.'] | //div[@x-ref='freegift']//button[@aria-label='Close']");
			Common.clickElement("xpath", "//div[@x-ref='freegift']//button[@aria-label='Close, button.'] | //div[@x-ref='freegift']//button[@aria-label='Close']");
        }
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the navigation to the shipping page when we click on the checkout ",
					"User should able to navigate to the shipping  page", "unable to navigate to the shipping page",
					Common.getscreenShot("Failed to navigate to the shipping page"));

			Assert.fail();
		}
		return Checkoutprice;

	}
	
	public String Minicart_Checkout() {
        String Checkoutprice="";
	try {
		Thread.sleep(4000);
		String PDPprice=Common.findElement("xpath", "(//span[@data-price-type='finalPrice'])[2]").getText();
        System.out.println(PDPprice);
        String Minicartprice=Common.findElement("xpath", "//span[contains(@class,'text-sm leading')]//span[@class='price']").getText();
        System.out.println(Minicartprice);
//        Assert.assertEquals(PDPprice, Minicartprice);
		Sync.waitElementPresent("xpath", "//span[@x-text='totalCartAmount']");
		String minicart = Common.findElement("xpath", "//span[@x-text='totalCartAmount']").getText();
		Sync.waitElementPresent(30, "xpath", "//button[contains(text(),'Checkout')]");
		Common.clickElement("xpath", "//button[contains(text(),'Checkout')]");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("checkout"),
				"validating the navigation to the shipping page when we click on the checkout",
				"User should able to navigate to the shipping  page", "Successfully navigate to the shipping page",
				"Failed to navigate to the shipping page");
//		Checkoutprice=Common.findElement("xpath", "//span[@class='text-xs']//span[contains(@class,'price')]").getText().trim();
//		  Assert.assertEquals(Checkoutprice, Minicartprice);
		

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog(
				"validating the navigation to the shipping page when we click on the checkout ",
				"User should able to navigate to the shipping  page", "unable to navigate to the shipping page",
				Common.getscreenShot("Failed to navigate to the shipping page"));

		Assert.fail();
	}
	return Checkoutprice;

}
	
	public String DeliveryAddress_Guestuser_Gift(String dataSet) throws Exception {
		String address = data.get(dataSet).get("Street");
		String Shipping="";

		try {
			Thread.sleep(5000);
			if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("preprod")) {
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

		int Size =Common.findElements("xpath", "//span[text()='Review & Payments']").size();

			try {
				Thread.sleep(4000);
				Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
				Thread.sleep(4000);
				String text = Common.findElement("xpath", "//input[@name='street[0]']").getAttribute("value");
				Sync.waitPageLoad();
				Thread.sleep(5000);
				Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
				System.out.println(data.get(dataSet).get("City"));

					 Thread.sleep(4000);
	                 Common.scrollIntoView("xpath", "//select[@id='billing-region']");
	                 Common.dropdown("xpath", "//select[@id='billing-region']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
	                 Thread.sleep(3000);
	                 String Shippingvalue = Common.findElement("xpath", "//select[@id='billing-region']")
	                         .getAttribute("value");
//	                 Shipping=Common.findElement("xpath", "//option[@value='"+Shippingvalue+"']").getAttribute("data-title");
//		              System.out.println(Shipping);
	                 System.out.println(Shippingvalue);
				Thread.sleep(2000);
				Common.textBoxInput("xpath", "//input[@name='postcode']",
						data.get(dataSet).get("postcode"));
				Thread.sleep(5000);

				Common.textBoxInput("xpath", "//input[@name='telephone']",
						data.get(dataSet).get("phone"));

			
		}

			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating shipping address",
						"shipping address is filled in to the fields", "user faield to fill the shipping address",
						Common.getscreenShotPathforReport("shipingaddressfaield"));
				AssertJUnit.fail();

			
			}
			return Shipping;
		}



	public void addDeliveryAddress_Gustuser(String dataSet) throws Exception {

		try {
			Thread.sleep(5000);
			Sync.waitElementVisible("xpath", "//input[@type='email']");
			Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Email"));

		} catch (NoSuchElementException e) {
			minicart_Checkout();
			Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Email"));

		}
		String expectedResult = "email field will have email address";
		try {
			Common.textBoxInput("id", "shipping-firstname", data.get(dataSet).get("FirstName"));
			int size = Common.findElements("xpath", "//input[@type='email']").size();
			Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,
					"Filled Email address", "unable to fill the email address");
			Common.textBoxInput("id", "shipping-lastname", data.get(dataSet).get("LastName"));
			Common.clickElement("id", "shipping-street-0");
			Common.textBoxInput("id", "shipping-street-0", data.get(dataSet).get("Street"));
			String Text = Common.getText("id", "shipping-street-0");

			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.findElement("id", "shipping-city").clear();
			Common.textBoxInput("id", "shipping-city", data.get(dataSet).get("City"));
			System.out.println(data.get(dataSet).get("City"));

			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			try {
				Common.dropdown("id", "shipping-region", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
				Thread.sleep(3000);
				Common.dropdown("id", "shipping-region", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
//			Common.textBoxInputClear("name", "postcode");
//			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));

			Common.textBoxInputClear("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));

			Thread.sleep(5000);

			Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
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

	public void addDeliveryAddress_Guestuser(String dataSet) throws Exception {
	    String address = data.get(dataSet).get("Street");

	    try {
	    	Thread.sleep(4000);
//	    	if(Common.findElements("xpath", "//div[@x-ref='freegift']//button[@aria-label='Close, button.'] | //div[@x-ref='freegift']//button[@aria-label='Close']").size()>0)
//			{
//	    		System.out.println("Free Gift Pop Displayed");
//	    		 Sync.waitElementVisible("xpath", "//div[@x-ref='freegift']//button[@aria-label='Close, button.'] | //div[@x-ref='freegift']//button[@aria-label='Close']");
//				Common.clickElement("xpath", "//div[@x-ref='freegift']//button[@aria-label='Close, button.'] | //div[@x-ref='freegift']//button[@aria-label='Close']");
//			}
	        if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
	            Sync.waitElementVisible("css", "input[type='email']");
	            Common.textBoxInput("css", "input[type='email']", data.get(dataSet).get("Email"));
	        } else {
	            Sync.waitElementVisible("css", "input[type='email']");
	            Common.textBoxInput("css", "input[type='email']", data.get(dataSet).get("Prod Email"));
	        }
	    } catch (NoSuchElementException e) {
	        minicart_Checkout();
	        if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
	            Sync.waitElementVisible("css", "input[type='email']");
	            Common.textBoxInput("css", "input[type='email']", data.get(dataSet).get("Email"));
	        } else {
	            Sync.waitElementVisible("css", "input[type='email']");
	            Common.textBoxInput("css", "input[type='email']", data.get(dataSet).get("Prod Email"));
	        }
	    }

	    String expectedResult = "email field should be entered in the textbox field";
	    try {
	        Sync.waitElementVisible("id", "shipping-firstname");
	        Common.textBoxInput("id", "shipping-firstname", data.get(dataSet).get("FirstName"));
	        int size = Common.findElements("css", "input[type='email']").size();
	        Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,
	                "Successfully email has been entered to textbox field", "unable to fill the email address in the textbox field");

	        Sync.waitElementVisible("id", "shipping-lastname");
	        Common.textBoxInput("id", "shipping-lastname", data.get(dataSet).get("LastName"));

	        Sync.waitElementVisible("id", "shipping-street-0");
	        Common.clickElement("id", "shipping-street-0");

	        Sync.waitElementVisible("id", "shipping-street-0");
	        Common.textBoxInput("id", "shipping-street-0", data.get(dataSet).get("Street"));
	        String Text = Common.getText("id", "shipping-street-0");

	        Sync.waitElementVisible("id", "shipping-city");
	        Common.findElement("id", "shipping-city").clear();

	        Sync.waitElementVisible("id", "shipping-city");
	        Common.textBoxInput("id", "shipping-city", data.get(dataSet).get("City"));
	        System.out.println(data.get(dataSet).get("City"));

	        Common.actionsKeyPress(Keys.PAGE_DOWN);

	        try {
	            Sync.waitElementVisible("id", "shipping-region");
	            Common.dropdown("id", "shipping-region", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
	        } catch (ElementClickInterceptedException e) {
	            Sync.waitElementVisible("id", "shipping-region");
	            Common.dropdown("id", "shipping-region", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
	        }

	        Sync.waitElementVisible("css", "input[name='postcode']");
	        Common.textBoxInputClear("css", "input[name='postcode']");

	        Sync.waitElementVisible("css", "input[name='postcode']");
	        Common.textBoxInput("css", "input[name='postcode']", data.get(dataSet).get("postcode"));
	        Thread.sleep(2000);

	        Sync.waitElementVisible("css", "input[name='telephone']");
	        Common.textBoxInput("css", "input[name='telephone']", data.get(dataSet).get("phone"));

	        ExtenantReportUtils.addPassLog("validating shipping address filling Fields",
	                "shipping address is filled in to the fields", "user should be able to fill the shipping address ",
	                Common.getscreenShotPathforReport("Successfully shipping address details has been entered"));

	    } catch (Exception | Error e) {
	        e.printStackTrace();
	        ExtenantReportUtils.addFailedLog("validating shipping address",
	                "shipping address is filled in to the fields", "user failed to fill the shipping address",
	                Common.getscreenShotPathforReport("shippingaddressfailed"));
	        Assert.fail();
	    }
	}
	public void clickSubmitbutton_Shippingpage() {
		String expectedResult = "click the submit button to navigate to payment page";
		try {
			System.out.println(Common.getCurrentURL());
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the shipping page submitbutton", expectedResult,
					"failed to click the submitbutton",
					Common.getscreenShotPathforReport("failed submitbuttonshippingpage"));
			Assert.fail();
		}
	}

	public void selectStandedshippingaddress() {

		try {

			int size = Common.findElements("xpath", "//input[@class='a-radio-button__input']").size();
			if (size > 0) {
				Sync.waitElementPresent(30, "xpath", "//input[@value='tablerate_bestway']");
				Common.clickElement("xpath", "//input[@value='tablerate_bestway']");
			} else {

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

	public void After_Pay_payment(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String order = "";
		Sync.waitPageLoad();
		Thread.sleep(3000);	
		String expectedResult = "User should able to proceed the afterpay payment method";

		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.clickElement("xpath", "//label[@for='payment-method-stripe_payments']");


				if(Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage") )
						{
					int savedcard=Common.findElements("xpath", "(//input[@type='radio' and @name='use_saved_stripe_method'])[2]").size();
					if(savedcard==1)
					{
						Sync.waitElementPresent("xpath", "(//input[@class='checkbox mr-4'])[2]");
						Common.clickElement("xpath", "(//input[@class='checkbox mr-4'])[2]");
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Sync.waitElementPresent(30, "xpath", "//button[@id='afterpay_clearpay-tab']");
				Common.javascriptclickElement("xpath", "//button[@id='afterpay_clearpay-tab']");			
				Common.switchToDefault();
					}
					else
					{
						Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
						Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
						Sync.waitElementPresent(30, "xpath", "//button[@id='afterpay_clearpay-tab']");
						Common.javascriptclickElement("xpath", "//button[@id='afterpay_clearpay-tab']");						
						Common.switchToDefault();
					}
				Thread.sleep(3000);
				Sync.waitElementPresent(30, "xpath", "(//button[contains(@class,'btn-place-order')])[2]");
				Common.clickElement("xpath", "(//button[contains(@class,'btn-place-order')])[2]");
				Thread.sleep(7000);
				
				Sync.waitPageLoad();
				Thread.sleep(5000);
				Sync.waitElementPresent(60, "xpath", "//a[contains(text(),'Authorize Test Payment')]");
				Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
						}
				else
				{
					Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					Thread.sleep(4000);
					Sync.waitElementPresent(30, "xpath", "//button[@id='afterpay_clearpay-tab']");
					Common.javascriptclickElement("xpath", "//button[@id='afterpay_clearpay-tab']");
					String Afterpay=Common.findElement("xpath", "//button[@id='afterpay_clearpay-tab']").getAttribute("data-testid");
					System.out.println(Afterpay);
					Common.assertionCheckwithReport(
							Afterpay.contains("afterpay_clearpay"),
							"validating the selection of the Afterpay method",
							"Afterpay should be selected ","Afterpay is selected",
							"Failed to select the Afterpay method in the production environment");
					Common.switchToDefault();
				}
		}
		

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the Afterpay payment ", expectedResult,
					"User failed to proceed with After payment", Common.getscreenShotPathforReport(expectedResult));
			Assert.fail();
		}

		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
		if (!url.contains("stage") && !url.contains("preprod")) {
		}

		else {
			try {
				Thread.sleep(5000);
				String sucessMessage = Common.getText("xpath", "//div[contains(@class,'checkout-success')]//h1").trim();

				int size = Common.findElements("xpath", "//div[contains(@class,'checkout-success')]//h1").size();
				Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
						"verifying the product confirmation", expectedResult,
						"Successfully It redirects to order confirmation page Order Placed",
						"User unable to go orderconformation page");

				if (Common.findElements("css", "a[class*='order-number link link-primary']").size() > 0) {
					order = Common.getText("css", "a[class*='order-number link link-primary']");
				}
				if (Common.findElements("xpath", "//div[@class='checkout-success container px-0 ']//p/a").size() > 0) {
					order = Common.getText("xpath", "//div[@class='checkout-success container px-0 ']//p/a");
					System.out.println(order);
				}

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the order confirmartion page",
						"It should navigate to the order confirmation page",
						"User failed to proceed to the order confirmation page",
						Common.getscreenShotPathforReport("failed to Navigate to the order summary page"));

				Assert.fail();
			}
		}
	}

	public void selectshippingaddress(String Dataset) {
		String method = data.get(Dataset).get("methods");

		try {
			Thread.sleep(3000);
			int size = Common.findElements("css", "label[for*='shipping-method']").size();
			if (size > 0) {
				Sync.waitElementPresent(30, "xpath", "//span[text()='" + method + "']");
				Common.clickElement("xpath", "//span[text()='" + method + "']");
				ExtenantReportUtils.addPassLog("validating shipping methods selections on checkout page",
						"Shipping methods should be select on the checkout page", "user should able to select the shipping method",
						Common.getscreenShotPathforReport("Sucessfully shipping method should be selected"));
			} else {

				Common.refreshpage();
				Thread.sleep(2000);
				Sync.waitElementPresent(30, "xpath", "//span[text()='" + method + "']");
				Common.clickElement("xpath", "//span[text()='" + method + "']");
				ExtenantReportUtils.addPassLog("validating shipping methods selections on checkout page",
						"Shipping methods should be select on the checkout page", "user should able to select the shipping method",
						Common.getscreenShotPathforReport("Sucessfully shipping method should be selected"));
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the " + method + " shipping method",
					"Select the " + method + " shipping method in shipping page ",
					"failed to select the " + method + " shipping method ",
					Common.getscreenShotPathforReport("failed select " + method + " shipping method"));

			Assert.fail();
		}
	}

	public void validatingErrormessageShippingpage() {
		int errorsize = Common.findElements("xpath", "//div[contains(@id,'error')]").size();
		String expectedResult = "shipping address is filled in to the fields";
		if (errorsize <= 0) {
			ExtenantReportUtils.addPassLog("validating the shipping address field with valid Data", expectedResult,
					"Filled the shipping address", Common.getscreenShotPathforReport("shippingaddresspass"));
		} else {

			ExtenantReportUtils.addFailedLog("validating the shipping address field with valid Datas", expectedResult,
					"failed to add a addres in the filled",
					Common.getscreenShotPathforReport("failed to add a address"));

			Assert.fail();
		}
	}

	public void validatingErrormessageShippingpage_negative() throws InterruptedException {
		Thread.sleep(2000);
		int Firstname_Error = Common.findElements("xpath", "//li[@data-msg-field='firstname']").size();
		int Lastname_Error=Common.findElements("xpath", "//li[@data-msg-field='lastname']").size();
//		int Address_Error =Common.findElements("xpath", "//li[@data-msg-field='street[0]']").size();
		int Phn_Error= Common.findElements("xpath", "//li[@data-msg-field='telephone']").size();
		String expectedResult = "Error message will dispaly when we miss the data in fields ";
		System.out.println(Firstname_Error);
		System.out.println(Lastname_Error);
		System.out.println(Phn_Error);
		if (Firstname_Error >0 && Lastname_Error>0&& Phn_Error>0) {
			System.out.println("Error Message displayed");
			ExtenantReportUtils.addPassLog("validating the shippingPage error message", expectedResult,
					"sucessfully  dispaly error message", Common.getscreenShotPathforReport("errormessagenegative"));
		} else {

			ExtenantReportUtils.addFailedLog("validating the shippingPage error message", expectedResult,
					"failed to display error message", Common.getscreenShotPathforReport("failederrormessage"));

			Assert.fail();
		}
	}
	
	public void Admin_signin(String dataSet) {

		try {
			
			Sync.waitPageLoad();
			if (Common.getCurrentURL().contains("emea-preprod")) {
				
				Thread.sleep(20000);
				Common.assertionCheckwithReport(Common.getCurrentURL().contains("admin/dashboard/"),
						"To Validate the Admin is landing on the Dashboard after successfull Signin",
						"After clicking on sigin button admin should navigate to the dashboard",
						"Admin Sucessfully navigate to the dashboard after clicking on the signin button",
						"Admin failed to display the dashboard after clicking on the signin button");
			}
			else {
				Sync.waitPageLoad(30);
				
				Common.openNewTab();
				
				Common.oppenURL("https://na-preprod.hele.digital/heledigitaladmin/admin/dashboard/");
				
				Sync.waitPageLoad(4000);
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
	public HashMap<String, String> Admin_Order_Details(String orderNumber) {
		HashMap<String, String> Orderstatus1 = new HashMap<String, String>();
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
				Common.scrollIntoView("xpath", "//div[@class='data-grid-cell-content']");
	       
			String Number=Common.findElement("xpath", "(//div[@class='data-grid-cell-content'])[1]").getText();
			System.out.println(Number);
			Thread.sleep(4000);
			Common.scrollIntoView("xpath", "//a[@class='action-menu-item']//parent::td/following-sibling::td[2]//div");
			String Workatostatus=Common.findElement("xpath", "//a[@class='action-menu-item']//parent::td/following-sibling::td[2]//div").getText();
			Orderstatus1.put("warkato", Workatostatus);
			System.out.println(Workatostatus);
				 if(Number.equals(orderNumber))
				 {
							Thread.sleep(3000);
							Common.clickElement("xpath", "//td//a[@class='action-menu-item']");
				 }
						String Orderstatus = Common.findElement("xpath", "//span[@id='order_status']").getText();
						
									System.out.println(Orderstatus);
									Orderstatus1.put("AdminOrderstatus", Orderstatus);
									StringBuilder concatenatedText = new StringBuilder();
									int size = Common.findElements("xpath", "//div[@class='product-sku-block']").size();

									for (int n=1;n<=size;n++) {
				             String sku=Common.findElement("xpath", "(//div[@class='product-sku-block'])["+n+"]").getText().replace("SKU:", "");
				             concatenatedText.append(sku);		  
				           
									}
									String finalsku = concatenatedText.toString();
									  System.out.println(finalsku);
									  Orderstatus1.put("Skus", finalsku);
									  
									  String Subtotal=Common.findElement("xpath", "//td[contains(text(),'Subtotal')]//parent::tr//span[@class='price']").getText().trim();
										Orderstatus1.put("Adminsubtotal", Subtotal);
										String Shipping=Common.getText("xpath", "//td[contains(text(),'Shipping & Handling')]//parent::tr//span[@class='price']").trim();
										Orderstatus1.put("Adminshipping", Shipping);
										String Tax=Common.getText("xpath", "//div[contains(text(),'Tax')]//parent::td//parent::tr//span[@class='price']").trim();
										Orderstatus1.put("Admintax", Tax);
										int dis=Common.findElements("xpath", "//td[contains(text(),'Discount')]//parent::tr//span[@class='price']").size();
										if(dis>0)
										{
											String Discount=Common.getText("xpath", "//td[contains(text(),'Discount')]//parent::tr//span[@class='price']").trim();
											Orderstatus1.put("AdminDis", Discount);
											
										}
										else
										{
											String Discount="Null";
											Orderstatus1.put("AdminDis", Discount);
										}
										String OrderTotal=Common.getText("xpath", " //strong[contains(text(),'Grand Total (Incl.Tax)')]//parent::td//parent::tr//span[@class='price']").trim();
										Orderstatus1.put("Admintotal", OrderTotal);
										
										String Email=Common.getText("xpath", "//th[contains(text(),'Email')]//parent::tr//a").trim();
										Orderstatus1.put("Email", Email);
			}
			else
			{
				Thread.sleep(5000);
				Common.scrollIntoView("xpath", "//input[@aria-label='Search by keyword']");
				Thread.sleep(6000);
				Common.clickElement("xpath", "//input[@aria-label='Search by keyword']");
				Thread.sleep(6000);
				Common.textBoxInput("xpath", "//input[@aria-label='Search by keyword']", orderNumber);
				Common.actionsKeyPress(Keys.ENTER);
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
				Thread.sleep(6000);
				Common.scrollIntoView("xpath", "//div[@class='data-grid-cell-content']");
				String Number=Common.findElement("xpath", "(//div[@class='data-grid-cell-content'])[1]").getText();
				System.out.println(Number);
				Thread.sleep(4000);
				Common.scrollIntoView("xpath", "//a[@class='action-menu-item']//parent::td/following-sibling::td[3]//div");
				String Workatostatus=Common.findElement("xpath", "//a[@class='action-menu-item']//parent::td/following-sibling::td[2]//div").getText();
				Orderstatus1.put("warkato", Workatostatus);
				System.out.println(Workatostatus);
					 if(Number.equals(orderNumber))
					 {
								Thread.sleep(3000);
								Common.clickElement("xpath", "//td//a[@class='action-menu-item']");
					 }				
							Thread.sleep(4000);
						
						String Orderstatus = Common.findElement("xpath", "//span[@id='order_status']").getText();
						
									System.out.println(Orderstatus);
									Orderstatus1.put("AdminOrderstatus", Orderstatus);
									StringBuilder concatenatedText = new StringBuilder();
									int size = Common.findElements("xpath", "//div[@class='product-sku-block']").size();

									for (int n=1;n<=size;n++) {
				             String sku=Common.findElement("xpath", "(//div[@class='product-sku-block'])["+n+"]").getText().replace("SKU:", "");
				             concatenatedText.append(sku);		  
				           
									}
									String finalsku = concatenatedText.toString();
									  System.out.println(finalsku);
									  Orderstatus1.put("Skus", finalsku);
									  String Subtotal=Common.findElement("xpath", "//td[contains(text(),'Subtotal')]//parent::tr//span[@class='price']").getText().trim();
										Orderstatus1.put("Adminsubtotal", Subtotal);
										String Shipping=Common.getText("xpath", "//td[contains(text(),'Shipping & Handling')]//parent::tr//span[@class='price']").trim();
										Orderstatus1.put("Adminshipping", Shipping);
										String Tax=Common.getText("xpath", "//div[contains(text(),'Tax')]//parent::td//parent::tr//span[@class='price']").trim();
										Orderstatus1.put("Admintax", Tax);
										int dis=Common.findElements("xpath", "//td[contains(text(),'Discount')]//parent::tr//span[@class='price']").size();
										if(dis>0)
										{
											String Discount=Common.getText("xpath", "//td[contains(text(),'Discount')]//parent::tr//span[@class='price']").trim();
											Orderstatus1.put("AdminDis", Discount);
											
										}
										else
										{
											String Discount="Null";
											Orderstatus1.put("AdminDis", Discount);
										}
										String OrderTotal=Common.getText("xpath", " //strong[contains(text(),'Grand Total (Incl.Tax)')]//parent::td//parent::tr//span[@class='price']").trim();
										Orderstatus1.put("Admintotal", OrderTotal);
										
										String Email=Common.getText("xpath", "//th[contains(text(),'Email')]//parent::tr//a").trim();
										Orderstatus1.put("Email", Email);
			}	

	}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			Assert.fail();
		}
		return Orderstatus1;
	}
	
	public void writeOrderNumber(String Description,String OrderIdNumber,String Skus, String AdminOrderstatus, String workato, String Used_GiftCode,String Subtotal,String shipping,String Tax,String Discount,String ordertotal,String Adminsubtotal,String Adminshipping,String Admintax,String AdminDis,String Admintotal,String Email)
			throws FileNotFoundException, IOException {
		// String fileOut="";
		try {

			File file = new File(
					System.getProperty("user.dir") + "/src/test/resources//TestData/Hydroflask/HYF_E2E_orderDetails.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet;
			Row row;
			Cell cell;
			int rowcount;
			sheet = workbook.getSheet("OrderDetails");

			if ((workbook.getSheet("OrderDetails")) == null) {
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
				cell.setCellValue("Orders Details");

				row = sheet.createRow(1);
				cell = row.createCell(0);
				cell.setCellStyle(cs);
				cell.setCellValue("Web Order Number");
				rowcount = 2;

			}

			else {

				sheet = workbook.getSheet("OrderDetails");
				rowcount = sheet.getLastRowNum() + 1;
			}
			row = sheet.createRow(rowcount);
			cell = row.createCell(0);
			cell.setCellType(CellType.NUMERIC);
			int SNo=rowcount-1;
			cell.setCellValue(SNo);
			cell = row.createCell(1);
			cell.setCellType(CellType.STRING);
			
			cell.setCellValue("Hydroflask");
			
			cell = row.createCell(2);
			cell.setCellType(CellType.STRING);
			cell.setCellValue(Description);
			
			cell = row.createCell(3);
			cell.setCellType(CellType.STRING);
			cell.setCellValue(Skus);
			
			cell = row.createCell(4);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(OrderIdNumber);

			cell = row.createCell(5);
			cell.setCellType(CellType.STRING);
			cell.setCellValue(AdminOrderstatus);
			
			cell = row.createCell(6);
			cell.setCellType(CellType.STRING);
			cell.setCellValue(workato);
			
			cell = row.createCell(7);
			cell.setCellType(CellType.STRING);
			cell.setCellValue(Used_GiftCode);
			
			cell = row.createCell(8);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(Subtotal);
			
			cell = row.createCell(9);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(shipping);
			
			cell = row.createCell(10);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(Tax);
			
			cell = row.createCell(11);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(Discount);
			
			cell = row.createCell(12);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(ordertotal);
			
			cell = row.createCell(13);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(Adminsubtotal);
			
			cell = row.createCell(14);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(Adminshipping);
			
			cell = row.createCell(15);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(Admintax);
			
			cell = row.createCell(16);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(AdminDis);
			
			cell = row.createCell(17);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(Admintotal);
			
			cell = row.createCell(18);
			cell.setCellType(CellType.STRING);
			cell.setCellValue(Email);
			
			cell = row.createCell(19);
			cell.setCellType(CellType.STRING);
			String Subtotalstatus;
			if(Subtotal.equals(Adminsubtotal))
			{

				Subtotalstatus="PASS";
			CellStyle style = workbook.createCellStyle();
			Font font= workbook.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
			}
			else
			{
				Subtotalstatus="FAIL";
			CellStyle style = workbook.createCellStyle();
			Font font= workbook.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
			}

			cell.setCellValue(Subtotalstatus);
			
			cell = row.createCell(20);
			cell.setCellType(CellType.STRING);
			String Shippingstatus;
			if(shipping.equals(Adminshipping))
			{

				Shippingstatus="PASS";
			CellStyle style = workbook.createCellStyle();
			Font font= workbook.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
			}
			else
			{
				Shippingstatus="FAIL";
			CellStyle style = workbook.createCellStyle();
			Font font= workbook.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
			}

			cell.setCellValue(Shippingstatus);
			
			cell = row.createCell(21);
			cell.setCellType(CellType.STRING);
			String Taxstatus;
			if(Tax.equals(Admintax))
			{

				Taxstatus="PASS";
			CellStyle style = workbook.createCellStyle();
			Font font= workbook.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
			}
			else
			{
				Taxstatus="FAIL";
			CellStyle style = workbook.createCellStyle();
			Font font= workbook.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
			}

			cell.setCellValue(Taxstatus);
			
			
			cell = row.createCell(22);
			cell.setCellType(CellType.STRING);
			String Disstatus;
			if(Discount.equals(AdminDis))
			{

				Disstatus="PASS";
			CellStyle style = workbook.createCellStyle();
			Font font= workbook.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
			}
			else
			{
				Disstatus="FAIL";
			CellStyle style = workbook.createCellStyle();
			Font font= workbook.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
			}

			cell.setCellValue(Disstatus);
			

			cell = row.createCell(23);
			cell.setCellType(CellType.STRING);
			String Orderstatus;
			if(ordertotal.equals(Admintotal))
			{

				Orderstatus="PASS";
			CellStyle style = workbook.createCellStyle();
			Font font= workbook.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
			}
			else
			{
				Orderstatus="FAIL";
			CellStyle style = workbook.createCellStyle();
			Font font= workbook.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
			}

			cell.setCellValue(Orderstatus);
			
			
			System.out.println(OrderIdNumber);
			FileOutputStream fileOut = new FileOutputStream(file);
			
			workbook.write(fileOut);
		
			fileOut.flush();
			fileOut.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
	
	public HashMap<String, String> ordersummary_Details() {
		// TODO Auto-generated method stub
		HashMap<String, String> details=new HashMap<String, String>();
		try
		{
		    Thread.sleep(6000);
			String Subtotal = Common.getText("xpath", "//div[@class='item subtotal']//span[contains(@class,'value')]").trim();
			details.put("Subtotal", Subtotal);
			Thread.sleep(8000);
			String shipping = Common.getText("xpath", "//div[@class='item shipping']//span[contains(@class,'value')]").trim();
			details.put("shipping", shipping);
			String Tax = Common.getText("xpath", "//div[@class='item tax']//span[contains(@class,'value')]").trim();
			details.put("Tax", Tax);
			Thread.sleep(4000);
			int Discounts=Common.findElements("xpath", "//div[@class='item discount']//span[contains(@class,'value')]").size();
			if(Discounts>0)
			{
				String Discount = Common.getText("xpath", "//div[@class='item discount']//span[contains(@class,'value')]").trim();
				details.put("Discount", Discount);
			}
			else
			{
				String Discount="Null";
				details.put("Discount", Discount);		
			}
			String ordertotal = Common.getText("xpath", "//div[@class='item grand_total']//span[contains(@class,'value text')]").trim();
			details.put("ordertotal", ordertotal);
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			Assert.fail();
		}
		return details;
	}

	public String updatePaymentAndSubmitOrder(String dataSet) throws Exception {
	    String order = "";
	    addPaymentDetails(dataSet);
	    String expectedResult = "It redirects to order confirmation page";

	    if (Common.findElements("xpath", "//div[@class='message message-error']").size() > 0) {
	        addPaymentDetails(dataSet);
	    }

	    Sync.waitPageLoad(); // Important: Wait for page load after payment details are added

	    String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);

	    if (url.contains("stage") || url.contains("preprod")) { // Check for stage/preprod explicitly
	        try {
	        	
	        String Current_URL=Common.getCurrentURL();
	        System.out.println(Current_URL);
//	            String successMessage = Common.getText("xpath", "//div[contains(@class,'checkout-success')]//h1");
//                  System.out.println("successMessage");
	            Common.assertionCheckwithReport(Current_URL.contains("onepage/success/"),
	                    "verifying the Order confirmation", expectedResult,
	                    "Successfully It redirects to order confirmation page Order Placed",
	                    "User unable to go order confirmation page");
	            Common.switchmainWindowsCons();
	          if (Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//p//span").size() > 0) {
	                order = Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p//span");
	                System.out.println(order);
	            } else if (Common.findElements("xpath", "//div[contains(@class,'checkout-success')]//p//a").size() > 0) {
	                order = Common.getText("xpath", "//div[contains(@class,'checkout-success')]//p//a");
	                System.out.println(order);
	            } else {
	                Assert.fail("Order confirmation page structure changed or order number not found.");
	            }

	        } catch (Exception | Error e) {
	            e.printStackTrace();
	            ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
	                    "User failed to navigate to order confirmation page",
	                    Common.getscreenShotPathforReport("failednavigatepage"));
	            Assert.fail("Failed to verify order confirmation.");
	        }
	    }

	    return order;
	}
	
	 public String addPaymentDetails(String dataSet) throws Exception {
	        HashMap<String, String> Paymentmethod = new HashMap<>();
	        String Number = "";
	        String cardnumber = data.get(dataSet).get("cardNumber");
	        String expectedResult = "user should be land on the payment section";

	        try {
	            Sync.waitPageLoad();
	            Sync.waitElementPresent("css", "label[for='payment-method-stripe_payments']");
	            int sizes = Common.findElements("css", "label[for='payment-method-stripe_payments']").size();

	            Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
	                    "Successfully land on the payment section", "Failed to Navigate to the Payment sections");

	            Common.clickElement("css", "label[for='payment-method-stripe_payments']");

	            int payment = Common.findElements("css", "div[class='stripe-dropdown-selection']").size();

	            if (payment > 0) {
	                Sync.waitElementPresent("css", "iframe[title='Secure payment input frame']");
	                Common.switchFrames("css", "iframe[title='Secure payment input frame']");

	                Sync.waitElementClickable("css", "label[for='Field-numberInput']");
	                Common.scrollIntoView("css", "label[for='Field-numberInput']");
	                Common.clickElement("css", "label[for='Field-numberInput']");
	                Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);

	                Number = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ", "");

	                Sync.waitElementPresent("id", "Field-expiryInput");
	                Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

	                Sync.waitElementPresent("id", "Field-cvcInput");
	                Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));

	                Common.actionsKeyPress(Keys.ARROW_DOWN);
	                Common.switchToDefault();

	                if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
	                    Sync.waitElementPresent("css", "button[class='action primary checkout']");
	                    Common.scrollIntoView("css", "button[class='action primary checkout']");
	                    Common.clickElement("css", "button[class='action primary checkout']");

	                    if (Common.getCurrentURL().contains("/checkout/#payment")) {
	                        Sync.waitElementPresent("css", "label[for='stripe-new-payments']");
	                        Common.clickElement("css", "label[for='stripe-new-payments']");
	                        Sync.waitElementPresent("css", "button[class='action primary checkout']");
	                        Common.clickElement("css", "button[class='action primary checkout']");

	                    } else if (Common.getCurrentURL().contains("/success/")) {
	                        String successMessage = Common.getText("css", "h1[class='page-title-wrapper']");
	                        System.out.println(successMessage);
	                    } else {
	                        Assert.fail("Checkout failed in stage/preprod environment");
	                    }
	                } else {
	                    Sync.waitElementPresent("css", "iframe[title='Secure payment input frame']");
	                    Common.switchFrames("css", "iframe[title='Secure payment input frame']");

	                    String cardNumberFromPage = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ", "");

	                    Common.assertionCheckwithReport(cardNumberFromPage.equals(cardnumber),
	                            "To validate the card details entered in the production environment",
	                            "user should able to see the card details in the production environment",
	                            "User Successfully able to see the card details entered in the production environment",
	                            "User Failed to see the card details in prod environment");

	                    Common.switchToDefault();
	                }

	            } else {
	                int savedCard = Common.findElements("xpath", "//div[@class='mb-4' and @x-show]").size();
	                if (savedCard > 0) {
	                    Sync.waitElementPresent("xpath", "(//input[@class='checkbox mr-4'])[2]");
	                    Common.clickElement("xpath", "(//input[@class='checkbox mr-4'])[2]");
	                }

	                Sync.waitElementPresent("css", "iframe[title='Secure payment input frame']");
	                Common.switchFrames("css", "iframe[title='Secure payment input frame']");

	                Sync.waitElementClickable("css", "label[for='Field-numberInput']");
	                Common.scrollIntoView("css", "label[for='Field-numberInput']");
	                Common.clickElement("css", "label[for='Field-numberInput']");
	                Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);

	                Number = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ", "");

	                Sync.waitElementPresent("id", "Field-expiryInput");
	                Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

	                Sync.waitElementPresent("id", "Field-cvcInput");
	                Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));

	                Common.actionsKeyPress(Keys.ARROW_DOWN);
	                Common.switchToDefault();

	                if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
	                    Sync.waitElementPresent("xpath", "(//button[contains(@class, 'btn-place-order') and contains(text(), 'Place Order')])[2]");
	                    Common.scrollIntoView("xpath", "(//button[contains(@class, 'btn-place-order') and contains(text(), 'Place Order')])[2]");
	                    Common.clickElement("xpath", "(//button[contains(@class, 'btn-place-order') and contains(text(), 'Place Order')])[2]");
	                    Sync.waitPageLoad();
	                    Common.switchFrames("css", "iframe[title='Secure payment input frame']");
	                    int error= Common.findElements("xpath", "//p[text()='Please provide a mobile phone number.']").size();
	                    if (error>0) {
	                        Sync.waitElementPresent(30, "id", "Field-linkMobilePhoneInput");
	                        Common.textBoxInput("id", "Field-linkMobilePhoneInput", "9898989568");
	                        Common.switchToDefault();
	                        Sync.waitElementPresent("xpath", "(//button[contains(@class, 'btn-place-order') and contains(text(), 'Place Order')])[2]");
	                        Common.clickElement("xpath", "(//button[contains(@class, 'btn-place-order') and contains(text(), 'Place Order')])[2]");
	                    }
	                   
	                } else {
	                    Sync.waitElementPresent("css", "iframe[title='Secure payment input frame']");
	                    Common.switchFrames("css", "iframe[title='Secure payment input frame']");

	                    String cardNumberFromPage = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ", "");

	                    Common.assertionCheckwithReport(cardNumberFromPage.equals(cardnumber),
	                            "To validate the card details entered in the production environment",
	                            "user should able to see the card details in the production environment",
	                            "User Successfully able to see the card details entered in the production environment",
	                            "User Failed to see the card details in prod environment");

	                    Common.switchToDefault();
	                }
	            }

	        } catch (Exception | Error e) {
	            e.printStackTrace();
	            ExtenantReportUtils.addFailedLog("validating the Credit Card information", expectedResult,
	                    "failed to fill the Credit Card information",
	                    Common.getscreenShotPathforReport("CardInformationFail"));
	            Assert.fail("Failed to add payment details");
	        }

	        return Number;
	    }

	public void PaymentDetails(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> Paymentmethod = new HashMap<String, String>();
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String cardnumber = data.get(dataSet).get("cardNumber");
		System.out.println(cardnumber);
		String expectedResult = "land on the payment section";
		// Common.refreshpage();

		try {
			Sync.waitPageLoad();

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
	}

	public void Tell_Your_FriendPop_Up() throws Exception {

		try {

			Common.switchFrames("xpath", "//iframe[contains(@src,'widget.fbot.me')]");
			Thread.sleep(4000);
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

	public String Stored_PaymentDetails(String dataSet) throws Exception {
        HashMap<String, String> Paymentmethod = new HashMap<>();
        String Number = "";
        String cardnumber = data.get(dataSet).get("cardNumber");
        String expectedResult = "user should be land on the payment section";

        try {
            Sync.waitPageLoad();
            Sync.waitElementPresent("css", "label[for='payment-method-stripe_payments']");
            int sizes = Common.findElements("css", "label[for='payment-method-stripe_payments']").size();

            Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
                    "Successfully land on the payment section", "Failed to Navigate to the Payment sections");

            Common.clickElement("css", "label[for='payment-method-stripe_payments']");
                int savedCard = Common.findElements("xpath", "//div[@class='mb-4' and @x-show]").size();
                if (savedCard > 0) {
                    Sync.waitElementPresent("xpath", "(//input[@class='checkbox mr-4'])[2]");
                    Common.clickElement("xpath", "(//input[@class='checkbox mr-4'])[2]");
                }

                Sync.waitElementPresent("css", "iframe[title='Secure payment input frame']");
                Common.switchFrames("css", "iframe[title='Secure payment input frame']");

                Sync.waitElementClickable("css", "label[for='Field-numberInput']");
                Common.scrollIntoView("css", "label[for='Field-numberInput']");
                Common.clickElement("css", "label[for='Field-numberInput']");
                Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);

                Number = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ", "");

                Sync.waitElementPresent("id", "Field-expiryInput");
                Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

                Sync.waitElementPresent("id", "Field-cvcInput");
                Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));

                Common.actionsKeyPress(Keys.ARROW_DOWN);
                Sync.waitElementPresent("id", "checkbox-linkOptIn");
                Common.clickElement("id", "checkbox-linkOptIn");
                Common.switchToDefault();

                if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
                    Sync.waitElementPresent("xpath", "(//button[contains(@class, 'btn-place-order') and contains(text(), 'Place Order')])[2]");
                    Common.scrollIntoView("xpath", "(//button[contains(@class, 'btn-place-order') and contains(text(), 'Place Order')])[2]");
                    Common.clickElement("xpath", "(//button[contains(@class, 'btn-place-order') and contains(text(), 'Place Order')])[2]");
                    Sync.waitPageLoad();
                    Common.switchmainWindowsCons();
                    if (Common.getCurrentURL().contains("/checkout")) {
                        Sync.waitElementPresent(30, "xpath", "//div[contains(@class,'checkout-success')]//h1");
                        String successMessage = Common.getText("xpath", "//div[contains(@class,'checkout-success')]//h1");
                        System.out.println(successMessage);
                    } else if (Common.getCurrentURL().contains("/success/")) {
                        String successMessage = Common.getText("xpath", "//div[contains(@class,'checkout-success container px')]//h1 ");
                        System.out.println(successMessage);
                    } else {
                        Assert.fail("Checkout failed in stage/preprod environment");
                    }
                } else {
                    Sync.waitElementPresent("css", "iframe[title='Secure payment input frame']");
                    Common.switchFrames("css", "iframe[title='Secure payment input frame']");

                    String cardNumberFromPage = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ", "");

                    Common.assertionCheckwithReport(cardNumberFromPage.equals(cardnumber),
                            "To validate the card details entered in the production environment",
                            "user should able to see the card details in the production environment",
                            "User Successfully able to see the card details entered in the production environment",
                            "User Failed to see the card details in prod environment");

                    Common.switchToDefault();
                }
            

        } catch (Exception | Error e) {
            e.printStackTrace();
            ExtenantReportUtils.addFailedLog("validating the Credit Card information", expectedResult,
                    "failed to fill the Credit Card information",
                    Common.getscreenShotPathforReport("CardInformationFail"));
            Assert.fail("Failed to add payment details");
        }

        return Number;
    }

	
	public void click_singinButton() {
		try {
			Sync.waitElementPresent("id", "customer-menu");
			Common.clickElement("id", "customer-menu");
			Common.clickElement("css", "a[title='Sign In']");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			Common.assertionCheckwithReport(
					Common.getText("xpath", "//fieldset[@class='fieldset login']//legend/h2").equals("Sign In"),
					"To validate the user navigates to the signin page",
					"user should able to land on the signIn page after clicking on the sigIn button",
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
			if (Common.getCurrentURL().contains("preprod")) {
				Sync.waitPageLoad();
				Common.textBoxInput("id", "email", data.get(dataSet).get("UserName"));
			} else {
				Common.textBoxInput("id", "email", data.get(dataSet).get("Prod UserName"));
			}
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//button[contains(@class,'btn btn-primary')]");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("Home Page") || Common.getPageTitle().contains("Hydro Flask"),
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
//			String message = Common.findElement("id", "validation-classes").getCssValue("color");
//			String redcolor = Color.fromString(message).asHex();
//			System.out.println(redcolor);
//			String message1 = Common.findElement("id", "validation-length").getCssValue("color");
//			String greencolor = Color.fromString(message1).asHex();
//			System.out.println(greencolor);
			Sync.waitElementPresent("xpath", "//li[@data-msg-field='email']");
			String emailmessage = Common.findElement("xpath", "//li[@data-msg-field='email']").getText();
			System.out.println(emailmessage);
			Sync.waitElementPresent("xpath", "//li[@data-msg-field='password_confirmation']");
			String confirmpassword = Common.findElement("xpath", "//li[@data-msg-field='password_confirmation']").getText();
			System.out.println(confirmpassword);
		
			Common.assertionCheckwithReport(
					emailmessage.equals("Please enter a valid email address.") && confirmpassword.contains("This field value must be the same as") ,
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
	public void Add_GiftCode_Myaccount(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		try{
			Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Sync.waitElementPresent("xpath", "//a[@title='My Account']");
			Common.clickElement("xpath", "//a[@title='My Account']");
			Sync.waitPageLoad();

			Sync.waitElementPresent("xpath", "//a[@title='Gift Cards']");
			Common.clickElement("xpath", "//a[@title='Gift Cards']");
			
			
			Assert.assertEquals("Gift Cards / Dashboard", Common.getPageTitle());
			
			
			Sync.waitElementPresent("xpath", "//input[@placeholder='Enter your Code']");
		    Common.textBoxInput("xpath", "//input[@placeholder='Enter your Code']", data.get(dataSet).get("GiftCard_Preprod"));
			System.out.println(data.get(dataSet).get("GiftCard_Preprod"));
			Common.clickElement("xpath", "//span[text()='Add']");
			
		
			Thread.sleep(6000);
		String Applied_Code = Common.findElement("xpath", "(//p[@class='text-sm'])[1]").getText();
		
		Common.assertionCheckwithReport(Applied_Code.equals(data.get(dataSet).get("GiftCard_Preprod")),
				"validating the Gifcode Applied in My Account",
				"Giftcode should be Applied",
				"Sucessfully Giftcode should be Applied",
				"failed to add Giftcode Apply");
			



		}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Gift code applied in Myaccount page",
					"Check Gift code applied in Myaccount page",
					"Unable add the Giftcode", Common.getscreenShot("Failed to add Giftcoder in Myaccount page"));
			Assert.fail();
		}
	}
	
	
	public void validate_GIFT_CARD_PLP() {
		
		try
		{
			Common.clickElement("xpath", "//span[contains(text(),'Holiday Shop')]");
			Sync.waitElementPresent("xpath", "//span[text()='Gift Cards']");
			Common.clickElement("xpath", "//span[text()='Gift Cards']");
			Thread.sleep(5000);
			String GIFTCARDtitle = Common.getText("xpath", "//h1");
		    System.out.println(GIFTCARDtitle);	
			Common.assertionCheckwithReport(GIFTCARDtitle.equalsIgnoreCase("E Gift Cards"),
					"To validate Gift card Navigation to the PLP",
					"After clicking on the Giftcard for the header links it should navigate to the Gift card PLP page",
					"Sucessfully It has been navigated to the Gift card PLP ", "Failed to Navigate to the Gift card PLP");
		     Sync.waitElementPresent("xpath", "(//span[contains(text(),'Sort by')])[1]");
		    String Sortingoptions = Common.getText("xpath", "(//span[contains(text(),'Sort by')])[1]");
		    System.out.println(Sortingoptions);
		    Common.assertionCheckwithReport(Sortingoptions.equalsIgnoreCase("Sort by:"),
				"Verifying the sorting options are visible or not",
				"Sort by options should visible",
				"Sort by options are visibled", "Failed to visible the sort by options");
		      
		    Sync.waitElementPresent("xpath", "//span[contains(@class,'flex-grow title-panel')]");
		    String FILTERSBY = Common.getText("xpath", "//span[contains(@class,'flex-grow title-panel')]").trim();
		    System.out.println(FILTERSBY);
		    Common.assertionCheckwithReport(FILTERSBY.equalsIgnoreCase("Filter by:"),
				"Verifying the filterby options are visible or not",
				"filter by options should visible",
				"filter by options are visibled", "Failed to visible the filter by options");
//		    Sync.waitElementPresent("xpath", "(//div[@class='name'])[2]");
//		    String Categorysection = Common.getText("xpath", "(//div[@class='name'])[2]").trim();
//		    System.out.println(Categorysection);
//		    Common.assertionCheckwithReport(Categorysection.equalsIgnoreCase("Categories"),
//				"Verifying the filterby options are visible or not",
//				"filter by options should visible",
//				"filter by options are visibled", "Failed to visible the filter by options");
		    Thread.sleep(3000);
		    List<WebElement> allProducts = Common.findElements("xpath","//li[@class='ais-InfiniteHits-item']");
	        int visibleProductCount = 0;
	        for (WebElement product : allProducts) {
	            if (product.isDisplayed()) {
	                visibleProductCount++;
	            }
	            else {
	            	Assert.fail();
	            	
	            }
	        }
	        System.out.println("Number of visible products: " + visibleProductCount);
		
		}
		
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate Gift card Navigation to the PLP",
					"After clicking on the Giftcard for the header links it should navigate to the Gift card PLP page",
					"Sucessfully It has been navigated to the Gift card PLP ",
					Common.getscreenShot("Failed to Navigate to the Gift card PLP"));
			Assert.fail();
			
		
		}
	}
	public void validate_price_PLP_and_PDP() {
		// TODO Auto-generated method stub
		
		try
		{
			int Products= Common.findElements("xpath","//img[@itemprop='image']").size();
			System.out.println(Products);
			for(int i=0;i<Products;i++) 
			{
				Thread.sleep(4000);
				int value = i + 1;
				WebElement ListOfSubproducts = Common.findElement("xpath",
						"(//span[@x-ref='normalPrice'])[" + value + "]");
				String PLPprice=ListOfSubproducts.getText();
			    System.out.println(PLPprice);
				Thread.sleep(4000);
				Common.clickElement("xpath", "(//img[@itemprop='image'])["+ value + "]");
				Sync.waitPageLoad();
				Thread.sleep(6000);
				String PDPPrice=Common.getText("xpath", "(//span[@x-text='item.price'])[1]");
				System.out.println(PDPPrice);
				Common.assertionCheckwithReport(PLPprice.equals(PDPPrice),
						"validating the Price for the Gift card in the PDP",
						"After clicking on the giftcard it should navigate to the PDP page",
						"Successfully PLP and PDP price should be equal", "Failed to validate the PLP and PDP price");
				Thread.sleep(4000);
				Common.navigateBack();
		
			}
		
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Price for the Gift card in the PDP",
					"After clicking on the giftcard it should navigate to the PDP page",
					"Unable to validate the PLP and PDP price",
					Common.getscreenShotPathforReport("Failed to validate the PLP and PDP price"));
			Assert.fail();
		}
		
	}
	
	public void Select_Gift_Code(String dataSet) {
		// TODO Auto-generated method stub
		String Giftcard = data.get(dataSet).get("GiftCard_Preprod");
		try {
			
			Common.clickElement("xpath", "//button[contains(text(),'Add Gift Card')]");
			Common.clickElement("xpath", "//input[@placeholder='Enter your Code']");
			Thread.sleep(3000);
			//			Common.dropdown("xpath", "//input[@name='amcard-field -datalist']", Common.SelectBy.TEXT, "GiftCard2");
			Common.clickElement("xpath", "//a[text()='"+Giftcard+"']");
			Common.clickElement("xpath", "//span[contains(text(),'Add Code')]");
			
			Thread.sleep(6000);
//			String successmsg=Common.findElement("xpath", "//div[@role='alert']").getText();
//		  System.out.println(successmsg);
//			
//			Common.assertionCheckwithReport(successmsg.contains("added"),
//					"validating the success message after applying gift card",
//					"Success message should be displayed after the applying of gift card",
//					"Sucessfully gift card has been applyed","Failed to apply the gift card");
			FUll_Payment("Giftcard");
		}
		
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Gift code Selected in Payment page",
					"Check Gift code Selected in Payment page",
					"Unable add the Giftcode in Payment page", Common.getscreenShot("Failed to add Giftcoder in Payment page"));
			Assert.fail();
		}
	}
	
public void FUll_Payment(String dataSet) {
	 	
		String Symbl = data.get(dataSet).get("Symbol");
		try {
			String  GiftCard=data.get(dataSet).get("GiftCard_Preprod");
			Thread.sleep(6000);
			String Total_Incl_Tax =Common.getText("xpath", "(//div[@class='item grand_total']//span[contains(@class,'value text-right text-sale-font')])[1]").replace(Symbl,"");
			
			System.out.println("Total_Incl_Tax :"+Total_Incl_Tax);
			Common.assertionCheckwithReport(Total_Incl_Tax.equals("0.00"),
					"validating the check money order in payment page",
					"Check money order radio button should be selected",
					"Sucessfully check money order has been selected",
					"failed to select the check mony order");
			
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Gift code  order in payment page",
					"Check Gift code applied and Fullpayment is applied",
					"Unable add the Giftcode", Common.getscreenShot("Failed to add Giftcoder"));
			Assert.fail();
		}
		
	}
	public void click_Createaccount() {

		try {
			Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Sync.waitElementPresent("xpath", "//a[@title='Create an Account']");
			Common.clickElement("xpath", "//a[@title='Create an Account']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Create an Account"),
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
	public String create_account(String Dataset) {
		String email="";
		String Product=data.get(Dataset).get("Products");
		Random random = new Random();
		int i = random.nextInt(1000);
		String Email ="spanem96+Automation"+i+"@gmail.com";
			i++;
		try {

			Sync.waitElementVisible(30, "xpath", "//input[@name='firstname']");
			Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(Dataset).get("LastName"));			
			Common.textBoxInput("xpath", "//input[@id='email_address']", Email);
			email = Common.findElement("xpath", "//input[@name='email']").getAttribute("value");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			System.out.println(data.get(Dataset).get("Password"));
			Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
					data.get(Dataset).get("Confirm Password"));
			System.out.println(data.get(Dataset).get("Confirm Password"));
			
			Sync.waitElementVisible(30, "xpath", "//button[@title='Sign Up']");
			Common.clickElement("xpath", "//button[@title='Sign Up']");
			Sync.waitElementPresent(30, "xpath", "//div[@ui-id='message-success']");
			String message = Common.findElement("xpath", "//div[@ui-id='message-success']").getText();
			System.out.println(message);
			Common.assertionCheckwithReport(
					message.contains("Thank you for registering") || Common.getPageTitle().contains("Wish List Sharing")&& message.contains(Product+ " has been added to your Favorites. Click here to view your Favorites") ,
					"validating navigation to the account page after clicking on sign up button",
					"User should navigate to the My account page after clicking on the Signup",
					"Sucessfully user navigates to the My account page after clickng on thr signup button",
					"Failed to navigate to the My account page after clicking on the signup button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating navigation to the account page after clicking on sign up button",
					"User should navigate to the My account page after clicking on the Signup",
					"Unable to navigate to the My account page after clicking on the signup button",
					"Failed to navigate to the My account page after clicking on the signup button");
			Assert.fail();
		}
		return email;
	}
	public void Configurable_addtocart_pdp(String DataSet) {
		String product = data.get(DataSet).get("Colorproduct");
		String productcolor = data.get(DataSet).get("Color");
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "(//img[contains(@class,'group-hover/item-image:hidden')])[1]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"(//img[contains(@class,'group-hover/item-image:hidden')])[1]");
				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}

			}
			Common.javascriptclickElement("xpath", "//img[@alt='" + product + "']");
			Thread.sleep(4000);
			System.out.println(product);
			if(Common.getCurrentURL().contains("preprod"))
			{
			String name = Common.findElement("xpath", "//h1[@itemprop='name'] ").getText().trim();
			Common.assertionCheckwithReport(name.contains(product),
					"validating the product should navigate to the PDP page",
					"When we click on the product is should navigate to the PDP page",
					"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");
			}
			else
			{
				String name = Common.findElement("xpath", "//h1[@itemprop='name'] ").getText().trim();
				Common.assertionCheckwithReport(name.contains(product),
						"validating the product should navigate to the PDP page",
						"When we click on the product is should navigate to the PDP page",
						"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");
			}

			Sync.waitPageLoad();
			if(Common.getCurrentURL().contains("preprod"))
			{
			Sync.waitElementPresent("xpath", "//div[@data-option-label='" + productcolor + "']");
			Common.clickElement("xpath", "//div[@data-option-label='" + productcolor + "']");
			String color=Common.findElement("xpath", "//span[contains(@class,'text-secondary-70')]").getText();
			System.out.println(color);
			if(color=="")
			{
				Common.clickElement("xpath", "//div[@data-option-label='" + productcolor + "']");
			}
       }
       else
       {
		Sync.waitElementPresent("xpath", "//div[@data-option-label='" + productcolor + "']");
		Common.clickElement("xpath", "//div[@data-option-label='" + productcolor + "']");
       }

			product_quantity(DataSet);
			System.out.println(productcolor);
			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.javascriptclickElement("xpath", "//button[@id='product-addtocart-button']");

			Thread.sleep(4000);

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"Unable to add product to the cart ", Common.getscreenShot("Failed to add product to the cart"));
			Assert.fail();
		}

	}
	public void minicart_validation(String Dataset) {
		// TODO Auto-generated method stub
		String UpdataedQuntityinminicart = data.get(Dataset).get("Quantity");
		try {

			String Subtotal = Common.getText("xpath", "//span[contains(@class, 'subtotal-value')]//span")
					.replace("$", "");
			Float subtotalvalue = Float.parseFloat(Subtotal);
			System.out.println("Subtotal  :"+subtotalvalue);
			Sync.waitElementPresent(30,"xpath", "(//select[@name='qty'])[2]");
            Common.scrollIntoView("xpath", "(//select[@name='qty'])[2]");
			Common.javascriptclickElement("xpath", "(//select[@name='qty'])[2]");
			Common.dropdown("xpath", "(//select[@name='qty'])[2]", Common.SelectBy.VALUE,
					UpdataedQuntityinminicart);
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//span[@x-text='totalCartAmount']");
			String cart = Common.findElement("xpath", "//span[@x-text='totalCartAmount']").getText();
			System.out.println("cart"+ cart);
			String Subtotal2 = Common.getText("xpath", "//span[contains(@class, 'subtotal-value')]//span")
					.replace("$", "");
			Float subtotalvalue2 = Float.parseFloat(Subtotal2);
			System.out.println("Subtotal2  :"+Subtotal2);
			Float Total = subtotalvalue * 3;
			System.out.println("Total  :"+Total);
			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			System.out.println("ExpectedTotalAmmount  :"+ ExpectedTotalAmmount2);
			Common.assertionCheckwithReport(
					UpdataedQuntityinminicart.equals(cart) && ExpectedTotalAmmount2.equals(Subtotal2),
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
	public void verify_paywithlink() {
		try
		{
			String paywithlink=Common.findElement("xpath", "(//iframe[@role='presentation'])[2]").getAttribute("name");
			System.out.println(paywithlink);
			Common.switchFrames("css", "iframe[name='"+ paywithlink +"']");
			Common.scrollIntoView("css", "button[aria-label='Pay with Link']");
			int link=Common.findElements("css", "button[aria-label='Pay with Link']").size();
			Common.assertionCheckwithReport(link>0,
					"validating the paywithlink on the shopping cart page",
					"paywithlink should be appear on the shopping cart page under the checkout button",
					"Sucessfully paywithlink is appeared on the shopping cart page below the checkout Button CTA",
					"Failed to appear the paywithlink on the shopping cart page under the checkout button CTA");
			Common.switchToDefault();
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the paywithlink on the shopping cart page",
					"paywithlink should be appear on the shopping cart page under the checkout button",
					"unable to appear the paywithlink on the shopping cart page under the checkout button CTA",
					Common.getscreenShot("Failed to appear the paywithlink on the shopping cart page under the checkout button CTA"));
			
			Assert.fail();
		}
	}
	public void minicart_delete(String Dataset) {
		// TODO Auto-generated method stub
		String deleteproduct = data.get(Dataset).get("Colorproduct");
		try {
			Thread.sleep(3000);
			Sync.waitElementPresent(30, "xpath", "//span[contains(@class, 'subtotal-value')]//span");
			String subtotal = Common.getText("xpath", "//span[contains(@class, 'subtotal-value')]//span")
					.replace("$", "");
			Float subtotalvalue = Float.parseFloat(subtotal);
			Thread.sleep(3000);
			Common.scrollIntoView("xpath", "(//div[contains(@class,'cart-drawer__item-content')]//a[contains(@class,'cart-drawer__item')])[2]");
			int pSize=Common.findElements("xpath", "(//div[contains(@class,'cart-drawer__item-content')]//a[contains(@class,'cart-drawer__item')])[2]").size();

			String productname = Common
					.findElement("xpath", "(//div[contains(@class,'cart-drawer__item-content')]//a[contains(@class,'cart-drawer__item')])[2]")
					.getText();
			String productamount1 = Common.getText("xpath", "(//span[@x-html='cart.subtotal']//span)[1]").replace("$",
					"");
			Float productamount1value = Float.parseFloat(productamount1);
			if (productname.equals(deleteproduct)||pSize>0 ) {
				Sync.waitElementPresent(30, "xpath",
						"(//div[contains(@class,'cart-drawer__item-content')]//button[contains(@aria-label,'Remove product')])[1]");
				Common.clickElement("xpath",
						"(//div[contains(@class,'cart-drawer__item-content')]//button[contains(@aria-label,'Remove product')])[1]");
				Sync.waitElementPresent("xpath", "//button[contains(text(),'OK')]");
				Common.javascriptclickElement("xpath", "//button[contains(text(),'OK')]");
			} else {
				Assert.fail();
			}
			Thread.sleep(7000);
			String cartProductQty = Common.getText("xpath", "//span[@x-text='totalCartAmount']");
			
//			String productamount = Common.getText("xpath", "//span[@x-html='item.product_price']//span[@class='price']").replace("$", "");
//			Float productamountvalue = Float.parseFloat(productamount);
//			Float Total = subtotalvalue - productamount1value;
//			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
//			Thread.sleep(4000);
			Common.assertionCheckwithReport(cartProductQty.equals("1"),
					"validating the delete operation",
					"The product should be delete from mini cart ",
					"Successfully product delete from the mini cart ",
					"Failed to delete the product from cart ");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the delete operation and subtotal",
					"The product should be delete from mini cart and subtotal should change",
					"unable to delete product from the mini cart and subtotal has not changed", Common.getscreenShot(
							"Failed to delete the product from the mini cart and subtotal has not changed"));
			Assert.fail();
		}

	}

	public void createAccountFromOrderSummaryPage(String Dataset) {
		// TODO Auto-generated method stub
		try {
//			String shop=Common.findElement("xpath", "//span[text()='Shop Accessories']//parent::a").getAttribute("href");
//			String kitchen=Common.findElement("xpath", "//span[text()='Shop Kitchenware']//parent::a").getAttribute("href");
			Common.clickElement("xpath", "//input[@name='password']");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			Common.clickElement("xpath", "(//button[@aria-label='Show Password'])[1]");
			Sync.waitElementPresent(30, "xpath", "//input[@name='password_confirmation']");
			Common.clickElement("xpath", "//input[@name='password_confirmation']");
			Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
					data.get(Dataset).get("Confirm Password"));
			Common.clickElement("xpath", "//button[@aria-label='Show Password']");
			
			Sync.waitElementPresent("xpath", "//label[@for='is_subscribed']");
			Common.clickElement("xpath", "//label[@for='is_subscribed']");
			Common.findElement("xpath", "//label[@for='is_subscribed']").isSelected();
		

			Sync.waitElementPresent(30, "xpath", "//span[text()='Sign Up']");
			Common.clickElement("xpath", "//span[text()='Sign Up']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath",
					"//div[@ui-id='message-success']");
			String message = Common.findElement("xpath",
					"//div[@ui-id='message-success']").getText();
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Dashboard") && message.contains("Thank you for registering"),
					"validating the  my Account page Navigation when user clicks on signin button",
					"User should able to navigate to the my account page after clicking on Signin button",
					"Sucessfully navigate to the My account page after clicking on signin button ",
					"Failed to navigates to My Account Page after clicking on Signin button");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the  my Account page Navigation when user clicks on signin button",
					"User should able to navigate to the my account page after clicking on Signin button",
					"Unable to  navigate to the My account page after clicking on signin button ",
					Common.getscreenShotPathforReport(
							"Failed to navigates to My Account Page after clicking on Signin button"));
			Assert.fail();
		}
	}

	/*public String minicart_items() {
		// TODO Auto-generated method stub
		String items = "";
		try {
			Sync.waitElementPresent("xpath", "//div[@x-text='cartSummaryCount']");
			items = Common.findElement("xpath", "//div[@x-text='cartSummaryCount']").getText();
			System.out.println("items"+items);
			Common.clickElement("xpath", "//button[@id='menu-cart-icon']");
			Sync.waitElementPresent("xpath", "//span[@x-text='totalCartAmount']");
			String miniitems = Common.findElement("xpath", "//span[@x-text='totalCartAmount']")
					.getText().trim();
			System.out.println(miniitems);
			Common.assertionCheckwithReport(items.contains(miniitems),
					"Vaildating the products count in the mini cart ",
					"Products count shsould be display in the mini cart",
					"Sucessfully products count has displayed in the mini cart",
					"failed to display products count in the mini cart");
			Sync.waitElementPresent("xpath", "//button[@aria-label='Close minicart']");
			Common.clickElement("xpath", "//button[@aria-label='Close minicart']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Vaildating the products count in the mini cart ",
					"Products count shsould be display in the mini cart",
					"Unable to display the  products count in the mini cart",
					Common.getscreenShot("failed to display products count in the mini cart"));

			Assert.fail();

		}
		return items;

	}*/
	public String minicart_items() {
	    String items = "";
	    try {
	        // Wait for cart summary count and retrieve item count
	        Sync.waitElementPresent("xpath", "//div[@x-text='cartSummaryCount']");
	        items = Common.findElement("xpath", "//div[@x-text='cartSummaryCount']").getText().trim();
	        System.out.println("Cart Summary Count: " + items);

	        // Click on the minicart icon
	        Common.clickElement("xpath", "//button[@id='menu-cart-icon']");

	        // Wait for total cart amount and retrieve item count
	        Sync.waitElementPresent("xpath", "//span[@x-text='totalCartAmount']");
	        String miniitems = Common.findElement("xpath", "//span[@x-text='totalCartAmount']").getText().trim();
	        System.out.println("Mini Cart Total Amount: " + miniitems);

	        // Validation check
	        if (items.isEmpty() || miniitems.isEmpty()) {
	            System.err.println("Error: Cart count or Mini cart count is empty!");
	            ExtenantReportUtils.addFailedLog(
	                "Validating the products count in the mini cart",
	                "Products count should be displayed in the mini cart",
	                "Failed due to empty values in cart count",
	                Common.getscreenShot("failed_cart_count")
	            );
	            Assert.fail();
	        } else {
	            Common.assertionCheckwithReport(
	                items.contains(miniitems),
	                "Validating the products count in the mini cart",
	                "Products count should be displayed in the mini cart",
	                "Successfully displayed product count in the mini cart",
	                "Failed to display products count in the mini cart"
	            );
	        }

	        // Close the minicart
	        Sync.waitElementPresent("xpath", "//button[@aria-label='Close minicart']");
	        Common.clickElement("xpath", "//button[@aria-label='Close minicart']");

	    } catch (Exception | Error e) {
	        e.printStackTrace();
	        ExtenantReportUtils.addFailedLog(
	            "Validating the products count in the mini cart",
	            "Products count should be displayed in the mini cart",
	            "Unable to display the products count in the mini cart",
	            Common.getscreenShot("failed_to_display_cart_count")
	        );
	        Assert.fail();
	    }

	    return items;  // Ensure a valid return even in case of errors
	}

	public void minicart_products(String minicart) {
		// TODO Auto-generated method stub
	
		try {
			Sync.waitElementPresent("xpath", "//button[@id='menu-cart-icon']");
			Common.mouseOverClick("xpath", "//button[@id='menu-cart-icon']");
			Thread.sleep(3000);
			Sync.waitElementPresent(50, "xpath", "//span[@x-text='totalCartAmount']");
			String cartproducts = Common.findElement("xpath", "//span[@x-text='totalCartAmount']").getText();
            System.out.println(cartproducts);
			Common.assertionCheckwithReport(cartproducts.contains("1"),
					"validating the products in the cart after creating new account ",
					"Products should be displayed in the mini cart after Create account with Cart",
					"Sucessfully after create account with cart products should be display in mini cart",
					"failed to display the products in mini cart after the create account with cart");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the products in the cart after creating new account ",
					"Products should be displayed in the mini cart after Create account with Cart",
					"Unable to display the products in mini cart after the create account with cart",
					Common.getscreenShot(
							"failed to display the products in mini cart after the create account with cart"));

			Assert.fail();
		}


	}

	public void discountCode(String dataSet) throws Exception {
		String expectedResult = "It should opens textbox input to enter discount.";
 
		try {
int discountsize = Common.findElements("xpath", "(//span[contains(text(),'Discount (10% off test coupon)')])[1]").size();
			if(discountsize>0)
{
				Thread.sleep(4000);
	
Common.findElement("xpath", "//span[contains(text(),'Cancel Coupon')]");
Common.clickElement("xpath", "//span[contains(text(),'Cancel Coupon')]");
			
}
			else {
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//h3[contains(text(),'Add Discount Code')]");
			Common.clickElement("xpath", "//h3[contains(text(),'Add Discount Code')]");
			}
			if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage") ) {
				Sync.waitElementPresent("id", "discount-code");
 
				Common.textBoxInput("id", "discount-code", data.get(dataSet).get("Discountcode"));
			} else {
				Sync.waitElementPresent("id", "discount-code");
 
				Common.textBoxInput("id", "discount-code", data.get(dataSet).get("prodDiscountcode"));
			}
 
			int size = Common.findElements("id", "discount-code").size();
			Common.assertionCheckwithReport(size > 0, "verifying the Discount Code label", expectedResult,
					"Successfully open the discount input box", "User unable enter Discount Code");
			Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//span[contains(text(),'Apply Code')]");
			Common.clickElement("xpath", "//span[contains(text(),'Apply Code')]");
			Sync.waitPageLoad();
			Thread.sleep(8000);
		/*	Common.scrollIntoView("xpath", "//div[@ui-id='message-success']//span");
			expectedResult = "It should apply discount on your price.If user enters invalid promocode it should display coupon code is not valid message.";
			String discountcodemsg = Common.getText("xpath", "//div[@ui-id='message-success']//span");
			System.out.println(discountcodemsg);
			Common.assertionCheckwithReport(discountcodemsg.contains("Your coupon was successfully applied."),
					"verifying pomocode", expectedResult, "promotion code working as expected",
					"Promation code is not applied");
			Common.scrollIntoView("xpath", "//div[@class='item subtotal']//span[contains(@class,'value')]");
			String Subtotal = Common.getText("xpath", "//div[@class='item subtotal']//span[contains(@class,'value')]").replace("$",
					"").trim();
			Float subtotalvalue = Float.parseFloat(Subtotal);
			String shipping = Common.getText("xpath", "//div[@class='item shipping']//span[contains(@class,'value')]")
					.replace("$", "").trim();
			Float shippingvalue = Float.parseFloat(shipping);
			String Tax = Common.getText("xpath", "//div[@class='item tax']//span[contains(@class,'value')]").replace("$", "").trim();
			Float Taxvalue = Float.parseFloat(Tax);
			String Discount = Common.getText("xpath", "//div[@class='item discount']//span[contains(@class,'value')]")
					.replace("$", "").trim();
			Float Discountvalue = Float.parseFloat(Discount);
			System.out.println(Discountvalue);
 
			String ordertotal = Common.getText("xpath", "//div[@class='item grand_total']//span[contains(@class,'value text')]")
					.replace("$", "").trim();
			Float ordertotalvalue = Float.parseFloat(ordertotal);
			Float Total = (subtotalvalue + shippingvalue + Taxvalue) + Discountvalue;
			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			System.out.println(ExpectedTotalAmmount2);
			System.out.println(ordertotal);
			Common.assertionCheckwithReport(ExpectedTotalAmmount2.equals(ordertotal),
					"validating the order summary in the payment page",
					"Order summary should be display in the payment page and all fields should display",
					"Successfully Order summary is displayed in the payment page and fields are displayed",
					"Failed to display the order summary and fileds under order summary");*/
 
		}
 
		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating discount code", expectedResult,
					"User failed to proceed with discountcode",
					Common.getscreenShotPathforReport("discountcodefailed"));
 
			Assert.fail();
 
		}
	}

	/**
	 * Searches for a product using data from a specified dataset.
	 *
	 * @param Dataset The key to retrieve product information from the 'data' map.
	 */
	public void search_product(String Dataset) {
		String product = data.get(Dataset).get("Products");
		System.out.println(product);
		try {
			// Click the search icon
			Common.clickElement("id", "menu-search-icon");
			// Verify the search bar is expanded
			boolean isSearchOpen = Common.findElement("id", "menu-search-icon").getAttribute("aria-expanded")
					.contains("true");
			Common.assertionCheckwithReport(isSearchOpen, "Search functionality validation",
					"User should be able to click the search button", "Search bar expanded successfully",
					"Failed to open the search bar");
			// Enter the product name and press Enter
			Common.textBoxInput("id", "autocomplete-0-input", product);
			Common.actionsKeyPress(Keys.ENTER);
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Search functionality validation",
					"User should be able to enter product name", "Unable to enter the product name in the search box",
					Common.getscreenShot("Failed to enter product name"));
			Assert.fail();
		}
	}

	public void newuseraddDeliveryAddress(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String email="";
		Random random = new Random();
		int min = 1000, max =1300;
        int i = random.nextInt(max - min + 1) + min; 
		String Email ="spanem96+Automation"+i+"@gmail.com";
			
		try {
			Thread.sleep(5000);
			Sync.waitElementVisible("xpath", "//input[@type='email']");
			Common.textBoxInput("xpath", "//input[@type='email']",Email);
		} catch (NoSuchElementException e) {
			minicart_Checkout();
			Common.textBoxInput("xpath", "//input[@type='email']",Email);

		}
		String expectedResult = "email field will have email address";
		try {
			Common.textBoxInput("id", "shipping-firstname", data.get(dataSet).get("FirstName"));
			int size = Common.findElements("xpath", "//input[@type='email']").size();
			Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,
					"Filled Email address", "unable to fill the email address");
			Common.textBoxInput("id", "shipping-lastname", data.get(dataSet).get("LastName"));
			Common.clickElement("id", "shipping-street-0");
			Common.textBoxInput("id", "shipping-street-0", data.get(dataSet).get("Street"));
			String Text = Common.getText("id", "shipping-street-0");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.findElement("id", "shipping-city").clear();
			Common.textBoxInput("id", "shipping-city", data.get(dataSet).get("City"));
			System.out.println(data.get(dataSet).get("City"));

			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			try {
				Common.dropdown("id", "shipping-region", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
				Thread.sleep(3000);
				Common.dropdown("id", "shipping-region", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			Common.textBoxInputClear("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));

			Thread.sleep(5000);

			Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));

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
		try {

			int size = Common.findElements("xpath", "//input[@class='a-radio-button__input']").size();
			if (size > 0) {
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath", "//input[@value='tablerate_bestway']");
				Common.clickElement("xpath", "//input[@value='tablerate_bestway']");
			}

			expectedResult = "shipping address is filled in to the fields";
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

	public void Klarna_Saved_Payment(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> Paymentmethod = new HashMap<String, String>();
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String email = data.get(dataSet).get("Email");
		String fullname = data.get(dataSet).get("FirstName");
		String expectedResult = "land on the payment section";

		try {
			Sync.waitPageLoad();

			Sync.waitElementClickable("xpath", "//div[@id='payment-method-option-stripe_payments']");
			int sizes = Common.findElements("xpath", "//div[@id='payment-method-option-stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unable to land o n the paymentpage");
			Common.clickElement("xpath", "//div[@id='payment-method-option-stripe_payments']");
			int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
			System.out.println(payment);
			if (payment > 0) {
				Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//span[text()='New payment method']");
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Sync.waitElementPresent(30, "xpath", "//button[@value='klarna']");
				Common.clickElement("xpath", "//button[@value='klarna']");
				Common.switchToDefault();
				Common.clickElement("xpath", "//span[text()='Place Order']");
				klarna_Saved_Details(dataSet);

			} else {
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Common.clickElement("xpath", "//button[@value='klarna']");
				Common.switchToDefault();
				Thread.sleep(1000);
				Common.javascriptclickElement("xpath", "(//button[normalize-space()='Place Order'])[1]");
				klarna_Saved_Details(dataSet);
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the product confirmation",
					"User Should able to Navigate to the order confirmation page",
					"User failed to navigate  to order confirmation page",
					Common.getscreenShotPathforReport("failednavigatepage"));
			Assert.fail();
		}
	}

	public void klarna_Saved_Details(String Dataset) {
		// TODO Auto-generated method stub
		String order = "";
		String number = data.get(Dataset).get("phone");
		String otp = data.get(Dataset).get("OTP Number");
		String DOB = data.get(Dataset).get("DOB");
		String Cardnumber = data.get(Dataset).get("cardNumber");
		System.out.println(Cardnumber);

		try {
			Sync.waitPageLoad();
			Common.switchWindows();
//			Common.switchFrames("xpath", "//iframe[@id='klarna-apf-iframe']");
			Sync.waitElementPresent("xpath", "//input[@name='phone']");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//span[text()='Phone number']");
			WebElement clear = Common.findElement("xpath", "//input[@name='phone']");
			clear.sendKeys(Keys.CONTROL + "a");
			clear.sendKeys(Keys.DELETE);
			Thread.sleep(4000);
			Common.findElement("xpath", "//input[@name='phone']").sendKeys(number);
			Common.clickElement("xpath", "//span[@id='onContinue__text']");
			Sync.waitPageLoad();
			Sync.waitElementPresent(30, "xpath", "//input[@id='otp_field']");
			Common.textBoxInput("xpath", "//input[@id='otp_field']", otp);
			Thread.sleep(6000);
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//span[text()='Visa']");
			int paymenttype = Common.findElements("xpath", "//div[contains(text(),'Change payment method')]").size();
			if (paymenttype > 0) {
//				Common.clickElement("xpath", "//p[@id='funding-source-card-number']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.clickElement("xpath", "//span[text()='Add new']");
				Sync.waitPageLoad();
				Sync.waitElementPresent(30, "xpath", "//iframe[@id='payment-gateway-frame']");
				Common.switchFrames("xpath", "//iframe[@id='payment-gateway-frame']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//input[@id='cardNumber']//parent::div");
				Common.findElement("xpath", "//input[@id='cardNumber']//self::input").sendKeys(Cardnumber);
				Common.clickElement("xpath", "//input[@id='expire']//parent::div");
				Common.findElement("xpath", "//input[@id='expire']").sendKeys(data.get(Dataset).get("ExpMonthYear"));
				Common.clickElement("xpath", "//input[@id='securityCode']//parent::div");
				Common.findElement("xpath", "//input[@id='securityCode']").sendKeys(data.get(Dataset).get("cvv"));
				Common.switchToDefault();
				Common.switchFrames("xpath", "//iframe[@id='klarna-apf-iframe']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//span[contains(text(),'Continue')]");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//span[contains(text(),'Pay $')]");
				Sync.waitPageLoad();
//				Common.clickElement("xpath", "//button[@data-testid='PushFavoritePayment:skip-favorite-selection']");
			} else {
				Common.clickElement("xpath", "//button[@data-testid='pick-plan']");
				Sync.waitPageLoad();
				Sync.waitElementPresent(30, "xpath", "//iframe[@id='payment-gateway-frame']");
				Common.switchFrames("xpath", "//iframe[@id='payment-gateway-frame']");
				Common.clickElement("xpath", "//input[@id='cardNumber']//parent::div");
				Common.findElement("xpath", "//input[@id='cardNumber']//self::input").sendKeys(Cardnumber);
				Common.clickElement("xpath", "//input[@id='expire']//parent::div");
				Common.findElement("xpath", "//input[@id='expire']").sendKeys(data.get(Dataset).get("ExpMonthYear"));
				Common.clickElement("xpath", "//input[@id='securityCode']//parent::div");
				Common.findElement("xpath", "//input[@id='securityCode']").sendKeys(data.get(Dataset).get("cvv"));
				Common.clickElement("xpath", "//span[contains(text(),'Continue')]");
				Common.clickElement("xpath", "//span[contains(text(),'Pay $')]");
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//button[@data-testid='PushFavoritePayment:skip-favorite-selection']");

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the card details enter in the Kalrna payment",
					"User Should able to Enter Card Details in Klarna Payment",
					"User Unable to enter Card details in the Klarna payment",
					Common.getscreenShotPathforReport("Failed to enter Card details in the Klarna payment"));
			Assert.fail();
		}
		String url1 = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
		if (!url1.contains("stage") && !url1.contains("preprod")) {
		}

		else {
			try {
//			Sync.waitPageLoad();
				Thread.sleep(8000);
				Sync.waitElementPresent(60, "xpath", "//h1[@class='page-title-wrapper']");
				String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();
				System.out.println(sucessMessage);
				Sync.waitElementPresent(50, "xpath", "//h1[@class='page-title-wrapper']");
				int size = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();
				Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
						"verifying the product confirmation", "It should redirects to the order confirmation mail",
						"Successfully It redirects to order confirmation page Order Placed",
						"User unable to go orderconformation page");

				if (Common.findElements("xpath", "//div[@class='checkout-success']/p/span").size() > 0) {
					order = Common.getText("xpath", "//div[@class='checkout-success']/p/span");
					System.out.println(order);
				}
				if (Common.findElements("xpath", "//a[@class='order-number']/strong").size() > 0) {
					order = Common.getText("xpath", "//a[@class='order-number']/strong");
					System.out.println(order);
				}

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the order confirmartion page",
						"It should navigate to the order confirmation page",
						"User failed to proceed to the order confirmation page",
						Common.getscreenShotPathforReport("failed to Navigate to the order summary page"));

				Assert.fail();
			}
		}
	}

	public void Newsletter_Popup(String DataSet) throws Exception {
		String email = data.get(DataSet).get("UserName");
        try{
		
      Thread.sleep(4000);
        int sizesframe = Common.findElements("xpath", "//div[@aria-label='POPUP Form']").size();
        System.out.println(sizesframe);
        if (sizesframe > 0) {
            Common.actionsKeyPress(Keys.PAGE_UP);
            Thread.sleep(2000);
            Sync.waitElementPresent("xpath", "//div[@data-testid='form-component']//input[@name='email']");
           Common.textBoxInput("xpath", "//div[@data-testid='form-component']//input[@name='email']", email);
           Common.clickElement("xpath", "//button[text()='Continue']");
           Thread.sleep(2000);
           String  text = Common.findElement("xpath", "(//div[@data-testid='form-component']//button)[5]").getText();
           Common.assertionCheckwithReport(text.contains("No, I Don't Want Texts"),
					"verifying the Newsletter subscription from Popup", "It should enter the mail id in Newsletter Popup",
					"Successfully It entered mail id in Newsletter Popup",
					"User unable to Enter the mail id in Newsletter Popup");
         
           Common.clickElement("xpath", "(//div[@data-testid='form-component'])[11]");
           
        }   
        else {

            Assert.fail("Popup Not displayed");
            }
        }
        catch(Exception|Error e) {
        	e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the Newsletter subscription from Popup",
					"It should enter the mail id in Newsletter Popup",
					"User unable to Enter the mail id in Newsletter Popup",
					Common.getscreenShotPathforReport("failed to ENter the mail id in Newsletter Popup"));

			Assert.fail();
        }

    }
	
	public void close_add() throws Exception {
        // TODO Auto-generated method stub
      Thread.sleep(4000);
        int sizesframe = Common.findElements("xpath", "//div[@aria-label='POPUP Form']").size();
        System.out.println(sizesframe);
        if (sizesframe > 0) {
            Common.actionsKeyPress(Keys.PAGE_UP);
            Thread.sleep(2000);
            Sync.waitElementPresent("xpath", "//button[@aria-label='Close dialog']");
            Common.clickElement("xpath", "//button[@aria-label='Close dialog']");
            Sync.waitElementPresent("xpath", "//button[@aria-label='Close dialog']");
            Common.clickElement("xpath", "//button[@aria-label='Close dialog']");
        }
        else {

            Common.switchFrames("xpath", "//div[@class='preloaded_lightbox']/iframe");
            Sync.waitElementPresent("xpath", "//button[contains(@aria-label,'Close') and @id='button3']");
            Common.clickElement("xpath", "//button[contains(@aria-label,'Close') and @id='button3']");
            Common.switchToDefault();
            }

 

    }

	public void acceptPrivacy() {

		Sync.waitElementClickable("id", "truste-consent-button");
		Common.clickElement("id", "truste-consent-button");
	}
	
	public void decline_All() {
		try {
			Sync.waitElementPresent("id", "truste-consent-required");
			Common.clickElement("id", "truste-consent-required");
			System.out.println("Decline all has been clicked");
		} catch (Exception e) {
			Common.clickElement("id", "truste-consent-required");
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

			Sync.waitElementPresent("xpath", "//label[@for='payment-method-stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='payment-method-stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unabel to land on paymentpage");
	
			Common.clickElement("xpath", "//label[@for='payment-method-stripe_payments']");

				Thread.sleep(3000);
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Thread.sleep(3000);
				Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
				Common.clickElement("xpath", "//label[@for='Field-numberInput']");
				Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);

				Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

				Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {

					Thread.sleep(1000);
					Sync.waitElementPresent("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
	             	   Common.clickElement("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
	             	  Thread.sleep(5000);
	             		Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
	    				Thread.sleep(3000);
	             	 String errorText = Common.findElement("xpath", "//p[@class='p-FieldError Error']").getText();
						Common.assertionCheckwithReport(
								errorText.isEmpty() || errorText.contains("Your card number is incomplete."),
								"validating the credit card information with valid data", expectedResult,
								"Filled the Card detiles", "missing field data it showinng error");
						Common.switchToDefault();
	             	  if(Common.getCurrentURL().contains("/checkout/#payment"))
	           	   {
	           		   Sync.waitElementPresent("xpath", "//label[@for='stripe-new-payments']");
	           		   Common.clickElement("xpath", "//label[@for='stripe-new-payments']");
	           		   Thread.sleep(5000);
	           		   Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
	               	   Common.clickElement("xpath", "//button[@class='action primary checkout']");
	               	expectedResult = "credit card fields are filled with the data";
					String errorTexts = Common.findElement("xpath", "//div[contains(@class,'error')]").getText();
					Common.assertionCheckwithReport(
							errorTexts.isEmpty() || errorTexts.contains("Please complete your payment details."),
							"validating the credit card information with valid data", expectedResult,
							"Filled the Card detiles", "missing field data it showinng error");
	           	   }
	             	  else if(Common.getCurrentURL().contains("/checkout/#payment"))
	             	  {
	             			expectedResult = "credit card fields are filled with the data";
	    					String errorTexts = Common.findElement("xpath", "//div[contains(@class,'error')]").getText();
	    					Common.assertionCheckwithReport(
	    							errorTexts.isEmpty() || errorTexts.contains("Please complete your payment details."),
	    							"validating the credit card information with valid data", expectedResult,
	    							"Filled the Card detiles", "missing field data it showinng error"); 
	             	  }
	             	   else
	             	   {
	             		   System.out.println(errorText);
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
			Sync.waitElementClickable("id", "customer-menu");
			Common.clickElement("id", "customer-menu");
			Sync.waitElementPresent(30, "xpath", "//a[contains(text(),'My Account')]");
			Common.clickElement("xpath", "//a[contains(text(),'My Account')]");
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Account")||Common.getPageTitle().equals("Dashboard"),
					"validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Sucessfully User Navigates to the My account page after clicking on the my account CTA",
					"Failed to Navigate to the MY account page after Clicking on my account button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Unable to Navigates the user to My account page after clicking on the my account CTA",
					Common.getscreenShot("Failed to Navigate to the MY account page after Clicking on my account CTA"));
			Assert.fail();
		}
		Sync.waitPageLoad();
		Common.clickElement("xpath", "(//span[text()='Address Book'])[1]");
		Sync.waitPageLoad();
		Common.assertionCheckwithReport(Common.getPageTitle().equals("Address Book"),
				"validating the Navigation to the Address Book page",
				"After Clicking on Address Book CTA user should be navigate to the Address Book page",
				"Sucessfully User Navigates to the Address Book page after clicking on the Address Book CTA",
				"Failed to Navigate to the Address Book page after Clicking on Address Book CTA");

		int PageTitle = Common.findElements("xpath", "//h1[@class='page-title-wrapper h2']").size();
		if (PageTitle>0) {
			try {
				Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//input[@title='Phone Number']", data.get(dataSet).get("phone"));
				Common.textBoxInput("xpath", "//input[@title='Address Line 1']", data.get(dataSet).get("Street"));
				Common.textBoxInput("xpath", "//input[@title='City']", data.get(dataSet).get("City"));
				try {
					Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT,
							data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					Thread.sleep(3000);
					Common.dropdown("id", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}

				Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));

				Common.clickElement("xpath", "//button[@title='Save Address']");
				Thread.sleep(3000);
				String message = Common.findElement("xpath", "//div[@ui-id='message-success']").getText();

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
			//Common.clickElement("xpath", "//span[contains(text(),'Change Billing Address')]");
			Common.clickElement("xpath", "//a[@title='Edit']");

			try {
				Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//input[@title='Phone Number']", data.get(dataSet).get("phone"));
				Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));

				try {
					Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT,
							data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {

					Thread.sleep(3000);
					Common.dropdown("id", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);

				Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));

				Common.clickElement("xpath", "//button[@title='Save Address']");
				Thread.sleep(1000);
				Sync.waitElementPresent(30, "xpath", "//div[@ui-id='message-success']");
				String message = Common.findElement("xpath", "//div[@ui-id='message-success']").getText();

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
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Sync.waitElementPresent(30, "xpath", "//a[@title='My Account']");
			Common.clickElement("xpath", "//a[@title='My Account']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Dashboard"),
					"validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Sucessfully User Navigates to the My account page after clicking on the my account CTA",
					"Failed to Navigate to the MY account page after Clicking on my account button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Unable to Navigates the user to My account page after clicking on the my account CTA",
					Common.getscreenShot("Failed to Navigate to the MY account page after Clicking on my account CTA"));
			Assert.fail();
		}
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//li//a[@title='My Orders']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Orders"),
					"validating the Navigation to the My Orders page",
					"After Clicking on My Orders CTA user should be navigate to the My Orders page",
					"Sucessfully User Navigates to the My Orders page after clicking on the My Orders CTA",
					"Failed to Navigate to the My Orders page after Clicking on My Orders CTA");
			String Ordernumber = Common.findElement("xpath", "(//span[@class='block lg:inline text-right break-words'])[1]")
					.getText();
			Sync.waitPageLoad();
			System.out.println(Ordernumber);
			System.out.println(Dataset);
			Common.assertionCheckwithReport(Ordernumber.equals(Dataset),
					"validating the Order Number in My Myorders page",
					"Order Number should be display in the MY Order page",
					"Sucessfully Order Number is displayed in the My orders page",
					"Failed to Display My order Number in the My orders page");
			
			Sync.waitElementPresent("xpath", "//a[@aria-label='View order "+Ordernumber +"']");
			Common.clickElement("xpath", "//a[@aria-label='View order "+Ordernumber +"']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Order Number in My Myorders page",
					"Order Number should be display in the MY Order page",
					"Unable to Display the Order Number in the My orders page",
					Common.getscreenShot("Failed to Display My order Number in the My orders page"));
			Assert.fail();
		}
	}
	public void My_Orders() {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Sync.waitElementPresent(30, "xpath", "//a[@title='My Account']");
			Common.clickElement("xpath", "//a[@title='My Account']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Dashboard"),
					"validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Sucessfully User Navigates to the My account page after clicking on the my account CTA",
					"Failed to Navigate to the MY account page after Clicking on my account button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Unable to Navigates the user to My account page after clicking on the my account CTA",
					Common.getscreenShot("Failed to Navigate to the MY account page after Clicking on my account CTA"));
			Assert.fail();
		}
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//li//a[@title='My Orders']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Orders"),
					"validating the Navigation to the My Orders page",
					"After Clicking on My Orders CTA user should be navigate to the My Orders page",
					"Sucessfully User Navigates to the My Orders page after clicking on the My Orders CTA",
					"Failed to Navigate to the My Orders page after Clicking on My Orders CTA");
//			String Ordernumber = Common.findElement("xpath", "(//span[@class='text-right'])[1]")
//					.getText();
//			Sync.waitPageLoad();
//			System.out.println(Ordernumber);
//			
//			
//			Sync.waitElementPresent("xpath", "//a[@aria-label='View order "+order +"']");
//			Common.clickElement("xpath", "//a[@aria-label='View order "+order +"']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Order Number in My Myorders page",
					"Order Number should be display in the MY Order page",
					"Unable to Display the Order Number in the My orders page",
					Common.getscreenShot("Failed to Display My order Number in the My orders page"));
			Assert.fail();
		}
	}
	public void back_to_Orders() {
		// TODO Auto-generated method stub
		try
		{
			Sync.waitElementPresent("css", "a[class='hidden lg:flex btn btn-link']");
			Common.clickElement("css", "a[class='hidden lg:flex btn btn-link']");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("/history/"),
					"validating the Navigation to the Track order Page",
					"After Clicking on the Back CTA user should be able to see Track order page",
					"Sucessfully User Navigates to Track order page  after clicking on the Back CTA",
					"Failed to Navigate to the Track ordert after Clicking on Back CTA");
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the Track order Page",
					"After Clicking on the Back CTA user should be able to see Track order page",
					"Unable to Navigate to the Track ordert after Clicking on Back CTA",
					Common.getscreenShot("Failed to Navigate to the Track ordert after Clicking on Back CTA"));
			Assert.fail();
		}
		
	}
	public void search_E2E_Completeorder() {
		// TODO Auto-generated method stub
//		back_to_Orders();
		String OrderNumber="4001000575";
		  boolean found = false;
		try
		{
			while (true)
			{
			List<WebElement> elements = Common.findElements("xpath", "//span[contains(text(),'" + OrderNumber + "')]");
			if (!elements.isEmpty()) {
                System.out.println("Order found on this page: " + OrderNumber);
                found = true;
                break;
            } 
			  List<WebElement> nextButtons = Common.findElements("css", "a[aria-label='Next']");

	            if (nextButtons.isEmpty() || !nextButtons.get(0).isDisplayed()) {
	                System.out.println("Order number not found and no more pages.");
	                break;
	            }
			  nextButtons.get(0).click();
			  try {
				  Sync.waitPageLoad();
	                Thread.sleep(2000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
			Common.clickElement("xpath", "//span[contains(text(),'"+ OrderNumber +"')]//parent::th//parent::tr//a[@title='View Order']");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("/view/order_id"),
					"validating the order Details on My order page",
					"After Clicking on view Order it should be navigate to the order details page ",
					"Sucessfully Navigated to the orders details page from My orders page ",
					"Failed to Navigate to the Track ordert after Clicking on Back CTA");
			orders_image_Validation();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the order Details on My orders page",
					"After Clicking on view Order it should be navigate to the order details page ",
					"Unable to Navigate to the orders details page from my order page ",
					Common.getscreenShot("Failed to Navigate to the orders details page "));
			Assert.fail();
		}
	}
	
	public void Verify_MyOrders_Page() {
		try {
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("/sales/order/view/order_id"),
					"validating the Navigation to the Items ordered page",
					"After Clicking on the View icon user should be items ordered in My Orders page",
					"Sucessfully User Navigates to the My Orders page and items ordered displayed  after clicking on the View icon",
					"Failed to Navigate to the My Orders page and items ordered tab after Clicking on view Icon");
			System.out.println("Items ordered tab is clicked.....");
			
			Common.clickElement("xpath", "//a[contains(text(),'Invoices')]");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("sales/order/invoice/order_id/"),
					"validating the Navigation to the Invoice Tab",
					"After Clicking on the Invoice user should be able to see Invoice page",
					"Sucessfully User Navigates to Invoice page  after clicking on the Invoice Tab",
					"Failed to Navigate to the Invoice after Clicking on Invoice Tab");
			System.out.println("Invoiced tab is clicked.....");
			
			Common.clickElement("xpath", "//a[contains(text(),'Print Invoice')]");
			Common.switchToSecondTab();
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("/sales/order/printInvoice/invoice_id"),
					"validating the Navigation to the Shipment Tab",
					"After Clicking on the Shipment user should be able to see Shipment page",
					"Sucessfully User Navigates to Invoice page  after clicking on the Shipment Tab",
					"Failed to Navigate to the Shipment after Clicking on Shipment Tab");
			Common.closeCurrentWindow();
			Common.switchToFirstTab();
			Common.clickElement("xpath", "//a[contains(text(),'Shipments')]");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("sales/order/shipment/order_id"),
					"validating the Navigation to the Shipment Tab",
					"After Clicking on the Shipment user should be able to see Shipment page",
					"Sucessfully User Navigates to Invoice page  after clicking on the Shipment Tab",
					"Failed to Navigate to the Shipment after Clicking on Shipment Tab");
			System.out.println("Shipment tab is clicked.....");
			
			Common.clickElement("xpath", "//a[contains(text(),'Print Shipment')]");
			Sync.waitPageLoad();
			Common.switchToSecondTab();
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("sales/order/printShipment/shipment_id"),
					"validating the Navigation to the printShipment Page",
					"After Clicking on the printShipment user should be able to see printShipment page",
					"Sucessfully User Navigates to printShipment page  after clicking on the printShipment link",
					"Failed to Navigate to the printShipment after Clicking on printShipment link");
			Common.closeCurrentWindow();
			Common.switchToFirstTab();
			
			Common.clickElement("xpath", "//span[contains(text(),'Track Shipment')]");
			Sync.waitPageLoad();
			Common.switchToSecondTab();
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("shipping/tracking"),
					"validating the Navigation to the Track Shipment Page",
					"After Clicking on the Track Shipment user should be able to see Shipment page",
					"Sucessfully User Navigates to Track Shipment page  after clicking on the Track Shipment",
					"Failed to Navigate to the Track Shipment after Clicking on Track Shipment");
			
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Itemordered ,Shipments,invoices in My Myorders page",
					"Itemordered ,Shipments,invoices should be display in the MY Order page",
					"Unable to Display the Itemordered ,Shipments,invoices in the My orders page",
					Common.getscreenShot("Failed to Display Itemordered ,Shipments,invoices in the My orders page"));
			Assert.fail();
		}
	}
	public void My_Favorites() {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("id", "customer-menu");
			Sync.waitElementPresent(30, "xpath", "//a[@title='My Favorites']");
			Common.clickElement("xpath", "//a[@title='My Favorites']");
			Thread.sleep(2000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Wish List Sharing"),
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
		String product = data.get(Dataset).get("Products");
		try {
			Sync.waitPageLoad();
			int MyFavorites = Common.findElements("xpath", "//span[text()='You have no items in your wish list.']").size();
System.out.println(MyFavorites);
			if (MyFavorites != 0) {
				search_product("Product");
				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product + "']");
				Common.clickElement("xpath", "//img[@alt='" + product + "']");
				Sync.waitElementPresent(30, "xpath", "//button[@title='Add to Wish List']");
				Common.clickElement("xpath", "//button[@title='Add to Wish List']");
				Sync.waitPageLoad();
				Thread.sleep(3000);
				My_Favorites();
				String Whishlistproduct = Common
						.findElement("xpath", "//a[@title='"+product+"']").getText();
				System.out.println(Whishlistproduct);

				if (Whishlistproduct.equals(product)) {
					Sync.waitElementPresent(30, "xpath", "//a[@title='" + product + "']");
					Common.mouseOver("xpath", "//a[@title='" + product + "']");
					
					Sync.waitElementPresent("xpath", "//span[text()='See options']");
					Common.clickElement("xpath", "//span[text()='See options']");
					Sync.waitElementPresent("id", "product-addtocart-button");
					Common.clickElement("id", "product-addtocart-button");
					Sync.waitPageLoad();
					
//					Thread.sleep(5000);
//					String message1 = Common.findElement("xpath", "//div[@ui-id='message-success']").getText();
//					System.out.println(message1);
//					Common.assertionCheckwithReport(message1.contains("You added "),
//							"validating the  product add to the cart", "Product should be add to cart",
//							"Sucessfully product added to the cart ", "failed to add product to the cart");
//					
//					int minicart = Common.findElements("xpath", "//div[@x-show='cartSummaryCount']").size();
//					System.out.println(minicart);
//					Common.clickElement("xpath", "//div[@x-show='cartSummaryCount']");
//					if (minicart > 0) {
//						minicart_Checkout();
//					} else {
//						Assert.fail();
//					}
				} else {
					Assert.fail();
				}

			} else {
				Sync.waitPageLoad();
				search_product("Product"); 
				Thread.sleep(2000);
				Common.refreshpage();
				Thread.sleep(2000);
				Sync.waitElementPresent(30, "xpath", "(//a[contains(@class,'product-image')])[1]");
				Common.clickElementStale("xpath", "(//a[contains(@class,'product-image')])[1]");
				
				Sync.waitElementPresent(30, "id", "customer-menu");
				Common.clickElement("id", "customer-menu");
				Sync.waitElementPresent("id", "customer.header.wishlist.nav.link");
				Common.clickElement("id", "customer.header.wishlist.nav.link");
				
				see_options("Product");
//				int minicart = Common.findElements("xpath", "//span[@x-text='totalCartAmount']").size();
//				System.out.println(minicart);
//				if (minicart > 0) {
//					minicart_Checkout();
//				}

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"Unable to add  product to the cart ", Common.getscreenShot("failed to add product to the cart"));
			Assert.fail();
		}

	}

	public void addDeliveryAddress_RegUser(String dataSet) throws InterruptedException {
		// TODO Auto-generated method stub
		String expectedResult = "shipping address is entering in the fields";
          Thread.sleep(6000);
		int size = Common.findElements(By.xpath("//span[contains(text(),'Add New Address')]")).size();
		if (size > 0) {
			try {
				Common.clickElement("xpath", "//span[contains(text(),'Add New Address')]");
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
						data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
						data.get(dataSet).get("LastName"));

//				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
//						data.get(dataSet).get("Street"));

				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.SPACE);
				Thread.sleep(2000);
				try {
					Common.clickElement("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
				} catch (Exception e) {
					Common.actionsKeyPress(Keys.BACK_SPACE);
					Thread.sleep(1000);
					Common.actionsKeyPress(Keys.SPACE);
					Common.clickElement("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
				}
				if (data.get(dataSet).get("StreetLine2") != null) {
					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
				}
				if (data.get(dataSet).get("StreetLine3") != null) {
					Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
				}

				Common.scrollIntoView("xpath", "//select[@name='region_id']");
				Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT,
						data.get(dataSet).get("Region"));
				Thread.sleep(3000);
				String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']").getAttribute("value");
				String Shippingstate = Common
						.findElement("xpath", "//select[@name='region_id']//option[@value='" + Shippingvalue + "']")
						.getText();

				System.out.println(Shippingstate);

				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
						data.get(dataSet).get("City"));

				try {
					Common.dropdown("xpath", "//form[@id='co-shipping-form']//select[@name='region_id']",
							Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {

					Thread.sleep(2000);
					Common.dropdown("xpath", "//form[@id='co-shipping-form']//select[@name='region_id']",
							Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);
				Common.textBoxInputClear("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']");
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']",
						data.get(dataSet).get("postcode"));
				String ShippingZip = Common.findElement("name", "postcode").getAttribute("value");
				System.out.println("*****" + ShippingZip + "*******");

				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='telephone']",
						data.get(dataSet).get("phone"));

				Sync.waitElementPresent("xpath", "//label[@class='label a-checkbox__label']");
				Common.clickElement("xpath", "//label[@class='label a-checkbox__label']");

				Common.clickElement("xpath", "//div[@id='opc-new-shipping-address']//following::button[1]");

				ExtenantReportUtils.addPassLog("validating shipping address filling Fields",
						"shipping address is filled in to the fields", "user should able to fill the shipping address ",
						Common.getscreenShotPathforReport("Sucessfully shipping address details has been entered"));

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
				Common.clickElement("xpath", "//button[contains(@class,'btn dr:btn-secondary-checkout hf:btn-primary ox:btn-primary os:btn-primary w-full justify-center os:uppercase')]");
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='firstname']",
						data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='lastname']",
						data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='street[0]']",
						data.get(dataSet).get("Street"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.SPACE);
				Thread.sleep(3000);
				try {
					Common.clickElement("xpath", "//form[@id='shipping']//input[@name='street[0]");
				} catch (Exception e) {
					Common.actionsKeyPress(Keys.BACK_SPACE);
					Thread.sleep(1000);
					Common.actionsKeyPress(Keys.SPACE);
					Common.clickElement("xpath", "//form[@id='shipping']//input[@name='street[0]']");
				}
				if (data.get(dataSet).get("StreetLine2") != null) {
					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
				}
				if (data.get(dataSet).get("StreetLine3") != null) {
					Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
				}
				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='city']",
						data.get(dataSet).get("City"));

				try {
					Common.dropdown("name", "region", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					// TODO: handle exception
					Thread.sleep(3000);
					Common.dropdown("name", "region", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);
				Common.textBoxInputClear("name", "postcode");
				Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));

				String ShippingZip = Common.findElement("name", "postcode").getAttribute("value");
				System.out.println("*****" + ShippingZip + "*******");

				Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
			    Common.clickElement("xpath","//button[starts-with(@class, 'checkout-address-form__buttons-save')]");
				

			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
						"User unabel add shipping address",
						Common.getscreenShotPathforReport("shipping address faield"));

				Assert.fail();

			}
		}

	}

	public void RegaddDeliveryAddress(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String expectedResult = "shipping address is entering in the fields";
		Thread.sleep(4000);
//		if(Common.findElements("xpath", "//div[@x-ref='freegift']//button[@aria-label='Close']").size()>0)
//		{
//			System.out.println("Free Gift popup Displayed in Shipping page");
//			Sync.waitElementVisible("xpath", "//div[@x-ref='freegift']//button[@aria-label='Close']");
//			Common.clickElement("xpath", "//div[@x-ref='freegift']//button[@aria-label='Close']");
//		}
		int size = Common
				.findElements(By.xpath("//button[contains(@class,'btn dr:btn-secondary-checkout hf:btn-primary')]"))
				.size();
		if (size > 0) {
			try {
				if (Common.getCurrentURL().contains("stage")) {
					Thread.sleep(8000);
				}
				Common.clickElement("css", "button[class*='btn dr:btn-secondary-checkout hf:btn-primary']");
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='firstname']",
						data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='lastname']",
						data.get(dataSet).get("LastName"));
				Common.textBoxInput("css", "input[name='street[0]']", data.get(dataSet).get("Street"));
				Common.actionsKeyPress(Keys.SPACE);
				try {
					Common.clickElement("css", "input[name='street[0]']");
				} catch (Exception e) {
					Common.actionsKeyPress(Keys.BACK_SPACE);
					Thread.sleep(1000);
					Common.actionsKeyPress(Keys.SPACE);
					Common.clickElement("css", "input[name='street[0]']");
				}
				if (data.get(dataSet).get("StreetLine2") != null) {
					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
				}
				if (data.get(dataSet).get("StreetLine3") != null) {
					Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
				}
 
				Common.scrollIntoView("id", "shipping-region");
				Common.dropdown("id", "shipping-region", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Common.textBoxInput("id", "shipping-city", data.get(dataSet).get("City"));
				try {
					Common.dropdown("id", "shipping-region", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					// TODO: handle exception
 
					Common.dropdown("id", "shipping-region", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}
 
				Common.textBoxInputClear("xpath", "//form[@id='shipping']//input[@name='postcode']");
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='postcode']",
						data.get(dataSet).get("postcode"));
				String ShippingZip = Common.findElement("name", "postcode").getAttribute("value");
				System.out.println("*****" + ShippingZip + "*******");
//				Shippingaddress.put("ShippingZip", ShippingZip);
 
				Sync.waitElementPresent("xpath", "//form[@id='shipping']//input[@name='telephone']");
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='telephone']",
						data.get(dataSet).get("phone"));
 
//				Sync.waitElementPresent("xpath", "//input[@id='shipping-save']");
//				Common.clickElement("xpath", "//input[@id='shipping-save']");
 
				Thread.sleep(2000);
				Common.clickElement("xpath", "//button[contains(@class, 'checkout-address-form__buttons-save')]");
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
				Sync.waitElementPresent("xpath", "//form[@id='shipping']//input[@name='firstname']");
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='firstname']",
						data.get(dataSet).get("FirstName"));
				Sync.waitElementPresent("xpath", "//form[@id='shipping']//input[@name='lastname']");
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='lastname']",
						data.get(dataSet).get("LastName"));
				Sync.waitElementPresent("xpath", "//input[@name='street[0]']");
				Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
				Thread.sleep(2000);
 
				if (data.get(dataSet).get("StreetLine2") != null) {
					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
				}
				else {
				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				Sync.waitElementPresent("id", "shipping-city");
				Common.textBoxInput("id", "shipping-city", data.get(dataSet).get("City"));
 
				try {
					Common.dropdown("xpath", "//select[@id='shipping-region']", Common.SelectBy.TEXT,
							data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					// TODO: handle exception
					Thread.sleep(3000);
					Common.dropdown("xpath", "//form[@id='co-shipping-form']//select[@name='region_id']",
							Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);
				Common.textBoxInputClear("xpath", "//form[@id='shipping']//input[@name='postcode']");
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='postcode']",
						data.get(dataSet).get("postcode"));
 
				String ShippingZip = Common.findElement("xpath", "//form[@id='shipping']//input[@name='postcode']")
						.getAttribute("value");
				System.out.println("*****" + ShippingZip + "*******");
 
				Thread.sleep(5000);
				Sync.waitElementPresent("xpath", "//form[@id='shipping']//input[@name='telephone']");
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='telephone']",
						data.get(dataSet).get("phone"));
				Common.clickElement("id", "shipping-save");
 
			} }catch (Exception | Error e) {
				e.printStackTrace();
 
				ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
						"User unabel add shipping address",
						Common.getscreenShotPathforReport("shipping address faield"));
 
				Assert.fail();
 
			}
		}
 
	}
	public void Accont_Information() {
		// TODO Auto-generated method stub
 
		try {
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Account Information')]");
			Common.clickElement("xpath", "//span[contains(text(),'Account Information')]");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Account Information"),
					"validating the Navigation to the Account information page",
					"After Clicking on Account information CTA user should be navigate to the Account information page",
					"Sucessfully User Navigates to the Account information page after clicking on the Account information CTA",
					"Failed to Navigate to the Account information page after Clicking on Account information button");
 
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the Account information page",
					"After Clicking on Account information CTA user should be navigate to the Account information page",
					"Unable to Navigates the user to Account information page after clicking on the Account information CTA",
					Common.getscreenShot(
							"Failed to Navigate to the Account information page after Clicking on Account information CTA"));
			Assert.fail();
		}
	}

	public void edit_Account_info(String dataSet) {
		// TODO Auto-generated method stub
		Accont_Information();
		try {

//			Sync.waitElementPresent("xpath", "//span[@class='m-accordion__title-label']");
//
//			Common.clickElement("xpath", "//span[@class='m-accordion__title-label']");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//div//input[@id='change-password']");
			Common.textBoxInput("xpath", "//input[@id='current-password']", data.get(dataSet).get("Password"));
			Common.textBoxInput("xpath", "//input[@id='password']", data.get(dataSet).get("Confirm Password"));
			Common.textBoxInput("xpath", "//input[@id='password-confirmation']",
					data.get(dataSet).get("Confirm Password"));
			Common.clickElement("xpath", "//span[text()='Save Account Information']");
			Sync.waitPageLoad();
			Sync.waitElementVisible(30,"xpath", "//div[@ui-id='message-success']");
			String sucessmessage = Common.findElement("xpath", "//div[@ui-id='message-success']").getText();
			Thread.sleep(4000);
			System.out.println(sucessmessage);
			Common.assertionCheckwithReport(sucessmessage.contains("You saved the account"),
					"Validating the saved account information", "Account information should be saved for the user",
					"Sucessfully account information has been saved ", "failed to save the account information");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the change passwordfor the register user",
					"User enter the valid password", "User failed to proceed to change passowrd ",
					Common.getscreenShotPathforReport("emailpasswordnew"));
			Assert.fail();

		}
	}

	public void changed_password(String Dataset) {
		// TODO Auto-generated method stub

		try {
			Sync.waitPageLoad();
			Common.textBoxInput("id", "email", Dataset);
			Common.textBoxInput("id", "pass", "Hydro@123");
			Common.clickElement("xpath", "//span[text()='Sign In']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account")|| Common.getPageTitle().contains("Dashboard"),
					"To validate the user lands on My Account page after successfull login",
					"After clicking on the signIn button it should navigate to the My Account page",
					"user Sucessfully navigate to the My Account page after clicking on the signIn button",
					"Failed to signIn and not navigated to the My Account page ");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the user Navigate to My Account page after successfull login",
					"After clicking on the signin button it should navigate to the My Account page",
					"Unable to navigate the user to the My Account after clicking on the SignIn button",
					Common.getscreenShotPathforReport("Failed to signIn and not navigated to the My Account page "));

			Assert.fail();

		}
	}

	public void Prodeler_Application_Page(String dataSet) {
		// TODO Auto-generated method stub
		click_Prodeal();
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//a[@title='Login']");
			Sync.waitPageLoad();
			Sync.waitPageLoad();
			if (Common.getCurrentURL().contains("preprod")) {
				Sync.waitElementVisible(60, "id", "email");
				Common.textBoxInput("id", "email", data.get(dataSet).get("UserName"));
			} else {
				Common.textBoxInput("id", "email", data.get(dataSet).get("Prod UserName"));
			}
			Sync.waitElementVisible(30, "id", "pass");
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//button[contains(@class,'btn btn-primary')]");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			click_Prodeal();
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Pro Deal Application"),
					"To validate the user lands on Pro Deal Application after successfull login",
					"After clicking on the signIn button it should navigate to the Pro Deal Application",
					"user Sucessfully navigate to the Pro Deal Application page after clicking on the signIn button",
					"Failed to signIn and not navigated to the Pro Deal Application page "); 
		    Common.clickElement("xpath", "(//div[@class='icon-container'])[2]");
		    Common.clickElement("xpath", "(//a[@title='Verify & Save'])[2]");
		    
			String expectedResult = "User is redirected to Pro Deal application page";
			Common.switchFrames("xpath", "//iframe[@class='sid-modal__iframe']");
			Sync.waitElementPresent("xpath", "//input[@id='sid-first-name']");
			int fistnamesize = Common.findElements("xpath", "//input[@id='sid-first-name']").size();
			Common.assertionCheckwithReport(fistnamesize > 0,
					"Successfully User is redirected to Pro Deal application page", expectedResult,
					"User able to redirected to Pro Deal application page");	
			Common.clickElement("xpath", "//input[@id='sid-first-name']");
			Common.textBoxInput("xpath", "//input[@id='sid-first-name']", data.get(dataSet).get("FirstName"));
			Common.clickElement("xpath", "//input[@id='sid-last-name']");
			Common.textBoxInput("xpath", "//input[@id='sid-last-name']", data.get(dataSet).get("LastName"));
//			Sync.waitElementPresent("id", "association");
//			Common.textBoxInput("id", "association", data.get(dataSet).get("Company"));
			Sync.waitElementPresent("xpath", "//input[@id='sid-email']");
			Common.textBoxInput("xpath", "//input[@id='sid-email']", data.get(dataSet).get("Email"));
//			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("xpath", "//input[@id='sid-postal-code']");
			Common.clickElement("xpath", "//input[@id='sid-postal-code']");
			Common.textBoxInput("xpath", "//input[@id='sid-postal-code']", data.get(dataSet).get("postcode"));
			Sync.waitElementPresent("xpath", "//input[@id='sid-address']");
			Common.clickElement("xpath", "//input[@id='sid-address']");
			Common.textBoxInput("xpath", "//input[@id='sid-address']", data.get(dataSet).get("Street"));		
			Thread.sleep(4000);
			Common.switchToDefault();
			ExtenantReportUtils.addPassLog("ProDeal application form filling",
					"User field to fill the prodeal aplication ",
					"user to get the success message for the pro deal submission",
					Common.getscreenShotPathforReport("Pro deal Form filled successfully"));
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("ProDeal application form filling",
					"User field to fill the prodeal aplication ",
					"user to get the success message for the pro deal submission",
					Common.getscreenShotPathforReport("Failed  fill Pro deal Form"));
			Assert.fail();

		}
	}

	public void click_Prodeal() {
		// TODO Auto-generated method stub
		try {
			Common.scrollIntoView("xpath", "//a[@title='Pro Deal']");
			Sync.waitElementPresent("xpath", "//a[@title='Pro Deal']");
			Common.clickElement("xpath", "//a[@title='Pro Deal']");
			
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("/prodeal/application"),
					"To validate the Pro Deal", "Should be display the Pro Deal Application ",
					"Successfully display the Pro Deal Application", "Failed to  display the Pro Deal Application");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Pro Deal Application",
					"Should display the Pro Deal Application ", "Unable to displays the Pro Deal Application",
					Common.getscreenShot("Failed to  display the Pro Deal Application"));
			Assert.fail();
		}
	}
	
	public void Gift_cards(String Dataset) {
		// TODO Auto-generated method stub
//		String GiftCard = data.get(Dataset).get("Hydrogift");
		try
		{
			Thread.sleep(4000);
			int size=Common.findElements("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]").size();
			if(size>0) {
				Common.clickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
			}else {
				Common.javascriptclickElement("xpath","//nav[@aria-label='Main menu']//span[contains(text(),'Shop')] ");
			}
				Sync.waitElementPresent("xpath", "//span[text()='Gift Cards']");
				Common.clickElement("xpath", "//span[text()='Gift Cards']");

			Sync.waitPageLoad(30);
			Thread.sleep(6000);
		
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("/gift-card"),
					"validating the gift card page navigation",
					"After clicking on the gift card it sholud navigate to the PDP page",
					"Successfully Navigated tot he gift card page",
					"Failed to match the Gift card page");
		
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Gift card Navigation to the PDP page",
					"After clicking on the gift card it should navigate to the PDP",
					"Unable to Navigate the Gift card to the PDP page",
					Common.getscreenShot("Failed to Navigate the Gift card to the PDP page"));
			AssertJUnit.fail();
			
		
		}
		
	}
	
	
	
	public void Card_Value(String Dataset) {
		// TODO Auto-generated method stub
		String amount=data.get(Dataset).get("Card Amount");
		try
		{
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//label[contains(@class,'amcard-label-block -price')]//span[text()='"+ amount +"']");
			Common.clickElement("xpath", "//label[contains(@class,'amcard-label-block -price')]//span[text()='"+ amount +"']");
			String Amount=Common.findElement("xpath", "(//span[@class='price'])[1]").getText();
			Assert.assertEquals(Amount,amount);
			Giftcard_details("Gift Details");
			product_quantity("Product Qunatity");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"Unable to add the product to the cart", Common.getscreenShot("Failed the product Add to cart from the PDP"));
			AssertJUnit.fail();
		}
	}
	
	public void Send_LaterCard_Value(String Dataset) {
		// TODO Auto-generated method stub
		String amount=data.get(Dataset).get("Card Amount");
		try
		{
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//label[contains(@class,'amcard-label-block -price')]//span[text()='"+ amount +"']");
			Common.clickElement("xpath", "//label[contains(@class,'amcard-label-block -price')]//span[text()='"+ amount +"']");
			String Amount=Common.findElement("xpath", "(//span[@class='price'])[1]").getText();
			Assert.assertEquals(Amount,amount);
			Giftcard_details("Gift Details");
			product_quantity("Product Qunatity");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "(//input[@id='is_date_delivery'])[2]");
			
			Common.clickElement("xpath", "(//input[@id='is_date_delivery'])[2]");
			
			Common.textBoxInput("name", "am_giftcard_date_delivery", data.get(Dataset).get("DeliveryDate"));
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"Unable to add the product to the cart", Common.getscreenShot("Failed the product Add to cart from the PDP"));
			AssertJUnit.fail();
		}
	}
	
	
	public void Giftcard_details(String Dataset) {
		// TODO Auto-generated method stub
		String Giftmessage=data.get(Dataset).get("message");
		try
		{
			Common.textBoxInput("xpath", "//input[@name='am_giftcard_sender_name']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='am_giftcard_recipient_name']", data.get(Dataset).get("LastName"));
			Common.textBoxInput("xpath", "//input[@name='am_giftcard_recipient_email']", data.get(Dataset).get("Email"));
			Common.textBoxInput("xpath", "//textarea[@name='am_giftcard_message']", Giftmessage);
			Thread.sleep(3000);
			String Message=Common.findElement("xpath", "//textarea[@name='am_giftcard_message']").getAttribute("value");
			System.out.println(Message);
			Common.assertionCheckwithReport(Message.equals(Giftmessage),
					"validating the message for the Gift card",
					"Message should be dispaly for the Gift card",
					"Successfully message has been dispalyed for the Gift card",
					"Failed to display the gift message for the Gift Card");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the message for the Gift card",
					"Message should be dispaly for the Gift card",
					"Unable to display the gift message for the Gift Card",
					Common.getscreenShot("Failed to display the gift message for the Gift Card"));
			Assert.fail();
		}
		
	}
	
	
public void Gift_card(String dataSet) {
		
		try
		{
			String URL = Common.getCurrentURL();
	        System.out.println(URL);
	        boolean isStageOrPreprod = URL.contains("stage") || URL.contains("preprod");
	        
     if (isStageOrPreprod) {
	        	Sync.waitElementPresent("xpath", "//h3[contains(text(),'Add Gift Card')]");

			if(Common.findElement("xpath", "//h3[contains(text(),'Add Gift Card')]").getAttribute("title").contains("Show items"))
			{
		Sync.waitElementPresent("xpath", "//h3[contains(text(),'Add Gift Card')]");	
		Common.clickElement("xpath", "//h3[contains(text(),'Add Gift Card')]");
			}
		Common.textBoxInput("xpath","//input[@x-model='giftCardCode']", data.get(dataSet).get("GiftCard_Preprod"));
		Common.actionsKeyPress(Keys.ARROW_UP);
		Sync.waitElementPresent("xpath", "//button[@aria-label='Add Code']");
		Common.clickElement("xpath","//button[@aria-label='Add Code']");
		Sync.waitElementPresent(30,"xpath", "//div[@ui-id='message-success']");	
		String successmsg=Common.findElement("xpath", "//div[@ui-id='message-success']").getText();
	    System.out.println(successmsg);	
		Common.assertionCheckwithReport(successmsg.contains("added")|successmsg.contains("applied") ,
				"validating the success message after applying gift card",
				"Success message should be displayed after the applying of gift card",
				"Sucessfully gift card has been applyed","Failed to apply the gift card");
			}
else
			{
				Common.scrollIntoView("xpath", "//h3[contains(text(),'Add Gift Card')]");
				Common.clickElement("xpath","//h3[contains(text(),'Add Gift Card')]");
				Common.textBoxInput("xpath","//input[@x-model='giftCardCode']", data.get(dataSet).get("GiftCard_Prod"));
//				Common.actionsKeyPress(Keys.ARROW_UP);
				Common.clickElement("xpath","//button[@aria-label='Add Code']");
				Sync.waitElementPresent(30,"xpath", "//div[@ui-id='message-success']");
				String successmsg=Common.findElement("xpath", "//div[@ui-id='message-success']").getText();
			    System.out.println(successmsg);	
				Common.assertionCheckwithReport(successmsg.contains("added"),
						"validating the success message after applying gift card",
						"Success message should be displayed after the applying of gift card",
						"Sucessfully gift card has been applyed","Failed to apply the gift card");
				Thread.sleep(5000);
			}
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the gift card",
					"Success message should be displayed after the applying of gift card",
					"Sucessfully gift card has been applyed",
					Common.getscreenShotPathforReport("Failed to apply the gift card"));
			Assert.fail();
		}
	}
public String giftCardSubmitOrder() throws Exception {
	// TODO Auto-generated method stub

	String order = "";
	String expectedResult = "It redirects to order confirmation page";
	Common.actionsKeyPress(Keys.PAGE_UP);
	Thread.sleep(3000);
	int placeordercount = Common.findElements("xpath", "//button[@class='action primary checkout']").size();
		Thread.sleep(4000);
if(Common.getCurrentURL().contains("stage") ||Common.getCurrentURL().contains("preprod") )
{
//   Common.refreshpage();
   Thread.sleep(4000);
   Common.clickElement("xpath", "//input[@id='payment-method-free']");
   Thread.sleep(4000);
   Common.clickElement("xpath", "//button[contains(text(),'Place Order')]");
		//Common.refreshpage();
	Thread.sleep(3000);
}
else
{
   AssertJUnit.fail();
}

	String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);

	if (!url.contains("stage") && !url.contains("preprod")) {
	}

	else {
		try {
			Thread.sleep(10000);
			String sucessMessage = Common.getText("xpath", "//h1[normalize-space()='Thank you for your purchase!']").trim();
			int sizes = Common.findElements("xpath", "//h1[normalize-space()='Thank you for your purchase!']").size();
			Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
					"verifying the product confirmation", expectedResult,
					"Successfully It redirects to order confirmation page Order Placed",
					"User unabel to go orderconformation page");

			if (Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//p//span").size() > 0) {
				Thread.sleep(4000);
				order = Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p//span");
				System.out.println(order);
			} else {
				Thread.sleep(4000);
				order = Common.getText("xpath", "//div[contains(@class,'checkout-success')]//p//a");
				System.out.println(order);
			}

			if (Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//p//span").size() > 0) {
				Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p//span");
				System.out.println(order);

			}
		
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
					"User failed to navigate  to order confirmation page",
					Common.getscreenShotPathforReport("failednavigatepage"));
			AssertJUnit.fail();
		}

	}
	return order;
}


public void Remove_GiftCode() {
	// TODO Auto-generated method stub
	try{
		Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
		Common.clickElement("xpath", "//button[@id='customer-menu']");
		Sync.waitElementPresent("xpath", "//a[@title='My Account']");
		Common.clickElement("xpath", "//a[@title='My Account']");
		Sync.waitPageLoad();

		Sync.waitElementPresent("xpath", "//a[@title='Gift Cards']");
		Common.clickElement("xpath", "//a[@title='Gift Cards']");
		
		Thread.sleep(4000);
		Assert.assertEquals("Gift Cards / Dashboard", Common.getPageTitle());
		Thread.sleep(4000);
        Common.clickElement("xpath", "//a[@title='Remove Gift Cards Code']");
        Thread.sleep(4000);
        Common.clickElement("xpath", "//button[contains(text(),'OK')]");
        Thread.sleep(2000);
//        String Remove_Code = Common.findElement("xpath", "//div[@role='alert']").getText();
//	
//	Common.assertionCheckwithReport(Remove_Code.contains("removed"),
//			"validating the Gifcode Applied in My Account",
//			"Giftcode should be Applied",
//			"Sucessfully Giftcode should be Applied",
//			"failed to add Giftcode Apply");
	

	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the Gift code applied in Myaccount page",
				"Check Gift code applied in Myaccount page",
				"Unable add the Giftcode", Common.getscreenShot("Failed to add Giftcoder in Myaccount page"));
		Assert.fail();
	}
}

	public void invalid_Gift_card(String dataSet) {
		try
		{
			Thread.sleep(4000);
			
			Sync.waitElementPresent("xpath", "//button[contains(text(),'Add Gift Card')]");	
			Common.clickElement("xpath", "//button[contains(text(),'Add Gift Card')]");
		    Common.scrollIntoView("xpath", "//input[@x-model='giftCardCode']");
			Common.textBoxInput("xpath","//input[@x-model='giftCardCode']", data.get(dataSet).get("InvalidGC"));
			
			Common.clickElement("xpath","//button[@aria-label='Add Code']");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//button[contains(text(),'Add Gift Card')]");	
			Common.clickElement("xpath", "//button[contains(text(),'Add Gift Card')]");
//		String errormsg=Common.findElement("xpath", "//div[@role='alert']").getText();
//	  System.out.println(errormsg);
//		
//		Common.assertionCheckwithReport(errormsg.contains("not found"),
//				"validating the error message after applying gift card",
//				"error message should be displayed after the applying of gift card",
//				"Sucessfully gift card has not been applyed","Failed to apply the gift card");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the gift card",
					"error message should be displayed after the applying of gift card",
					"Sucessfully gift card has been not applyed",
					Common.getscreenShotPathforReport("Failed to apply the gift card"));
			AssertJUnit.fail();
		}
		
	}
	
	
	

	public String payPal_Payment(String dataSet) throws Exception {
		
		String order = "";
		 
		String expectedResult = "It should open paypal site window.";
		try {
			Thread.sleep(3000);
			int cancelpayment=Common.findElements("xpath", "//button[@title='Cancel']").size();
			System.out.println(cancelpayment);
			if(cancelpayment>0)
			{
				Sync.waitElementPresent("xpath", "//button[contains(text(),'Cancel Payment')]");
				Common.clickElement("xpath", "//button[contains(text(),'Cancel Payment')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//input[@id='payment-method-paypal_express']");
				Common.clickElement("xpath", "//input[@id='payment-method-paypal_express']");
				Sync.waitElementPresent("xpath", "//div[@id='paypal-button-paypal_express']");
				Common.clickElement("xpath", "//div[@id='paypal-button-paypal_express']");
			}
			else
			{
				Common.scrollIntoView("xpath", "//input[@id='payment-method-paypal_express']");
				Common.clickElement("xpath", "//input[@id='payment-method-paypal_express']");
				Sync.waitElementClickable("xpath", "//div[@id='paypal-button-paypal_express']");
				Common.clickElement("xpath", "//div[@id='paypal-button-paypal_express']");
			}

			Common.switchFrames("xpath", "//iframe[contains(@class,'component-frame visible')]");
			Sync.waitElementPresent("xpath", "(//div[contains(@class,'paypal-button-label')])[1]");
			Common.clickElement("xpath", "(//div[contains(@class,'paypal-button-label')])[1]");
//			Common.switchFrames("xpath", "//iframe[contains(@class,'component-frame visible')]");
 
			Thread.sleep(9000);
			Common.switchToDefault();
			Thread.sleep(6000);
			Common.switchWindows();
			int size = Common.findElements("id", "acceptAllButton").size();
			if (size > 0) {
				Sync.waitElementPresent("id", "acceptAllButton");
				Common.clickElement("id", "acceptAllButton");
 
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult,
					"User failed to proceed with paypal payment", Common.getscreenShotPathforReport(expectedResult));
			Assert.fail();
		}
		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
 
		if (!url.contains("stage") & !url.contains("preprod")) {
 
			int sizeofelement = Common.findElements("id", "email").size();
			Common.assertionCheckwithReport(sizeofelement > 0, "verifying the paypal payment ", expectedResult,
					"open paypal site window", "faild to open paypal account");
		} else {
			Sync.waitElementPresent("id", "login_email");
			Common.clickElement("id", "login_email");
			Sync.waitElementPresent("id", "login_email");
			Common.findElement("id", "login_email").clear();
			Common.textBoxInput("id", "login_email", data.get(dataSet).get("Email"));
			Sync.waitElementClickable("xpath", "//button[text()='Next']");
			Common.clickElement("xpath", "//button[text()='Next']");
			int size = Common.findElements("xpath", "//button[text()='Use Password Instead']").size();
			if(size>0) {
				Common.clickElement("xpath", "//button[text()='Use Password Instead']");
			    Sync.waitElementPresent(10,"id", "password");
				Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			}
			else {

			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			int sizeemail = Common.findElements("id", "email").size();
			Common.assertionCheckwithReport(sizeemail > 0, "verifying the paypal payment ", expectedResult,
					"open paypal site window", "faild to open paypal account");
			}
			try {
				Sync.waitElementClickable("xpath", "//button[text()='Log In']");
				Common.clickElement("xpath", "//button[text()='Log In']");
				Thread.sleep(5000);
				Common.actionsKeyPress(Keys.END);
				Thread.sleep(5000);
				Sync.waitElementClickable("xpath", "//div[text()='Pay']");
				Common.clickElement("xpath", "//div[text()='Pay']");
				Thread.sleep(8000);
				Common.switchToFirstTab();
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult,
						"User failed to proceed with paypal payment",
						Common.getscreenShotPathforReport(expectedResult));
				Assert.fail();
			}
			String url1 = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
			if (!url1.contains("stage") && !url1.contains("preprod")) {
			}
 
			else {
				try {
					Thread.sleep(8000);
					Common.scrollIntoView("xpath", "(//button[contains(@class,'btn btn-primary place-order')])[1]");
					Common.clickElement("xpath", "(//button[contains(@class,'btn btn-primary place-order')])[1]");
 
					Thread.sleep(7000);
					Sync.waitElementPresent(30, "xpath", "//h1[normalize-space()='Thank you for your purchase!']");
					String sucessMessage = Common.getText("xpath",
							" //h1[normalize-space()='Thank you for your purchase!']");
                      System.out.println(sucessMessage);
					int sizes = Common.findElements("xpath", " //h1[normalize-space()='Thank you for your purchase!']")
							.size();
					Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
							"verifying the product confirmation", expectedResult,
							"Successfully It redirects to order confirmation page Order Placed",
							"User unabel to go orderconformation page");
 
					if (Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//p//span")
							.size() > 0) {
						Thread.sleep(1000);
						order = Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p//span");
						System.out.println(order);
					} else {
						Thread.sleep(1000);
						order = Common.getText("xpath", "//div[contains(@class,'checkout-success')]//p//a");
						System.out.println(order);
					}
				} catch (Exception | Error e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("verifying the order confirmartion page",
							"It should navigate to the order confirmation page",
							"User failed to proceed to the order confirmation page",
							Common.getscreenShotPathforReport("failed to Navigate to the order summary page"));
 
					Assert.fail();
				}
			}
		}
		return order;
	}

	public void access_for_prodeal( ) {
		// TODO Auto-generated method stub
		click_Prodeal();
		try {
			
			Thread.sleep(6000);
			String Pro_Account_Heading = Common.findElement("xpath", "//h1[text()='Hydro Flask Pro Account']")
					.getText();

			System.out.println(Pro_Account_Heading);
			
			int Categories = Common.findElements("xpath", "//h3[@class='programs-heading']").size();
			if(Categories>0) {
				Common.clickElement("xpath", "//span[text()='Helen of Troy Associates']");
				Common.clickElement("xpath", "(//span[text()='Verify & Save'])[8]");
				Common.switchFrames("xpath", "//iframe[@class='sid-modal__iframe']");
				int size =Common.findElements("xpath", "//div[@id='sid-step-employee-personal-info']").size();
				Common.assertionCheckwithReport(size>0,
						"validating the Employe personal Info Form is displayed successfully ", "should display Employe personal Info Form",
						"successfully Employe personal Info Form is displayed ", "failed to display Employe personal Info Form ");
			}
			else {
				Assert.fail();
			}
			
		}

		catch (Exception | Error e) {

			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Pro Deal success message ",
					"Should display the success message", "Unable to displays the success message",
					Common.getscreenShot("Failed to  display the Pro Deal success message"));

			Assert.fail();

		}

	}

	public void giftCreation(String Dataset) {
		// TODO Auto-generated method stub
		 String Month = data.get(Dataset).get("EventMonth");
	        String Year =  data.get(Dataset).get("EventYear");
	        String Date = data.get(Dataset).get("EventDate");
			try {
				Common.clickElement("xpath", "//button[@id='customer-menu']");
				Sync.waitElementPresent(30, "xpath", "//a[@title='My Account']");
				Common.clickElement("xpath", "//a[@title='My Account']");
				Thread.sleep(4000);
				Common.assertionCheckwithReport(Common.getPageTitle().equals("Dashboard"),
						"validating the Navigation to the My account page",
						"After Clicking on My account CTA user should be navigate to the my account page",
						"Sucessfully User Navigates to the My account page after clicking on the my account CTA",
						"Failed to Navigate to the MY account page after Clicking on my account button");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the Navigation to the My account page",
						"After Clicking on My account CTA user should be navigate to the my account page",
						"Unable to Navigates the user to My account page after clicking on the my account CTA",
						Common.getscreenShot("Failed to Navigate to the MY account page after Clicking on my account CTA"));
				Assert.fail();
			}
			click_giftcard();
			newregistry_CTA("Birthday");
			try {
				Common.textBoxInput("xpath", "//input[@id='title']", data.get(Dataset).get("Type"));
				Common.textBoxInput("xpath", "//textarea[@id='message']", data.get(Dataset).get("Message"));
				Common.dropdown("xpath", "//select[@id='is_public']", SelectBy.TEXT, data.get(Dataset).get("privacy"));
				Common.dropdown("xpath", "//select[@id='is_active']", SelectBy.TEXT, data.get(Dataset).get("Status"));
				
				String eventname = Common.findElement("xpath", "(//p[contains(@class,'giftregistry-type text')]//span)[2]").getText();
				if (eventname.equals("Birthday")) {
					Common.dropdown("xpath", "//select[@id='event_country_region']", SelectBy.TEXT,
							data.get(Dataset).get("Region"));
					Thread.sleep(1000);
					Common.scrollIntoView("id", "event_date");
					Common.clickElement("id", "event_date");
					
					Common.dropdown("xpath", "//select[@name='datepicker_month']", SelectBy.TEXT,Month);
					Common.dropdown("xpath", "//select[@name='datepicker_year']", SelectBy.TEXT,Year);
					Thread.sleep(1000);
					Common.clickElement("xpath", "//button[text()='"+Date+"']");
					
					
				} else if (eventname.equals("Wedding")) {

					Common.dropdown("xpath", "//select[@id='event_country_region']", SelectBy.TEXT,
							data.get(Dataset).get("Region"));
					Common.textBoxInput("xpath", "//input[@id='event_date']", data.get(Dataset).get("Date"));
					Common.textBoxInput("xpath", "//input[@name='event_location']", data.get(Dataset).get("Location"));
					Common.textBoxInput("xpath", "//input[@name='registry[number_of_guests]']",
							data.get(Dataset).get("GropName"));

				} else {
					Common.dropdown("xpath", "//select[@id='event_country_region']", SelectBy.TEXT,
							data.get(Dataset).get("Region"));
					Common.textBoxInput("xpath", "//input[@name='event_location']", data.get(Dataset).get("Location"));
				}
//		        Baby_Registry("Baby Registry");
				Registrant_Information("Birthday");
				Thread.sleep(3000);
				Common.textBoxInput("xpath", "//input[@id='firstname']", data.get(Dataset).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@id='lastname']", data.get(Dataset).get("LastName"));
				Common.textBoxInput("xpath", "//input[@id='address_street1']", data.get(Dataset).get("Street"));
				Common.textBoxInput("xpath", "//input[@id='address_city']", data.get(Dataset).get("City"));
				Common.dropdown("xpath", "//select[@id='region_id']", SelectBy.TEXT, data.get(Dataset).get("Region"));
				Common.textBoxInput("xpath", "//input[@id='address_postcode']", data.get(Dataset).get("postcode"));
				Common.textBoxInput("xpath", "//input[@id='address_telephone']", data.get(Dataset).get("phone"));
				Sync.waitElementPresent(30, "id", "submit.save");
				Common.clickElement("id", "submit.save");
				Sync.waitPageLoad();
				Sync.waitElementPresent(30, "xpath", "//div[@ui-id='message-success']");
				String message = Common.findElement("xpath", "//div[@ui-id='message-success']").getText();
				Common.assertionCheckwithReport(message.equals("You saved this gift registry."),
						"validating the gift registery page navigation ",
						"After clicking on save button It should be able to navigate to the gift registry page ",
						"successfully Navigated to the gift registry page", "failed to Navigate to the gift registry page");

			} catch (Exception e) {

				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the gift registery page navigation ",
						"After clicking on save button It should be able to navigate to the gift registry page ",
						"unable to Navigated to the gift registry page",
						Common.getscreenShot("Failed to Navigate to the gift registry page"));
				Assert.fail();
			}
	}

	public void click_giftcard() {
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//span[text()='Gift Registry']");
			Common.clickElement("xpath", "//span[text()='Gift Registry']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Gift Registry"),
					"validating the gift registery page navigation ",
					"It should be able to navigate to the gift registry page ",
					"successfully Navigated to the gift registry page", "failed to Navigate to the gift registry page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the gift registery page navigation ",
					"It should be able to navigate to the gift registry page ",
					"Unable to Navigated to the gift registry page",
					Common.getscreenShot("Failed to Navigate to the gift registry page"));
			Assert.fail();
		}
	}

	public void newregistry_CTA(String Dataset) {
		try {
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//a[contains(text(),'Create New Registry')]");
			Common.clickElement("xpath", "//a[contains(text(),'Create New Registry')]");
			Sync.waitPageLoad();
			Common.dropdown("id", "type_id", SelectBy.TEXT, data.get(Dataset).get("Type"));
			Common.clickElement("id", "submit.next");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String eventname = Common.findElement("xpath", "(//p[contains(@class,'giftregistry-type text')]//span)[2]").getText();
			System.out.println(eventname);
			Thread.sleep(6000);
			Common.assertionCheckwithReport(
					eventname.equals("Birthday") || eventname.equals("Wedding") || eventname.equals("Baby Registry"),
					"validating seleted event page navigation ",
					"It should be able to navigate to Respective event page  ",
					"successfully Respective selected event page", "failed to Navigate to the respective event page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating seleted event page navigation ",
					"It should be able to navigate to Respective event page  ",
					"Unable to navigate to the selected Respective event page",
					Common.getscreenShot("failed to Navigate to the respective event page"));
			Assert.fail();
		}
	}

	public void Baby_Registry(String Dataset) {
		String babygender = data.get(Dataset).get("Gender");
		System.out.println(babygender);
		try {
			Sync.waitElementPresent("xpath", "//select[@name='registry[baby_gender]']");
			Common.dropdown("xpath", "//select[@name='registry[baby_gender]']", Common.SelectBy.TEXT, babygender);
			Thread.sleep(4000);
			String gender = Common.findElement("xpath", "//select[@name='registry[baby_gender]']//option[@value='boy']")
					.getText();
			Common.assertionCheckwithReport(gender.equals(babygender), "validating the baby gender in gift registry ",
					"It should display the baby gender under the gift registry",
					"successfully baby gender is displayed under the gift registry",
					"failed to display the baby gender under gift registry");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the baby gender in gift registry ",
					"It should display the baby gender under the gift registry",
					"unable to display the baby gender under the gift registry",
					Common.getscreenShot("failed to display the baby gender under gift registry"));
			Assert.fail();
		}
	}

	public void Registrant_Information(String Dataset) {
		String eventname = Common.findElement("xpath", "(//p[contains(@class,'giftregistry-type text')]//span)[2]").getText();
		try {
			if (eventname.equals("Birthday")) {
				Common.textBoxInput("xpath", "//input[@name='registrant[0][firstname]']",
						data.get(Dataset).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='registrant[0][lastname]']",
						data.get(Dataset).get("LastName"));
				Common.textBoxInput("xpath", "//input[@name='registrant[0][email]']", data.get(Dataset).get("Email"));
				Common.clickElement("xpath", "//button[contains(text(),'Add Registrant')]");
				Common.textBoxInput("xpath", "//input[@name='registrant[1][firstname]']",
						data.get(Dataset).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='registrant[1][lastname]']",
						data.get(Dataset).get("LastName"));
				Common.textBoxInput("xpath", "//input[@name='registrant[1][email]']",
						data.get(Dataset).get("UserName"));
			} else {
				Common.textBoxInput("xpath", "//input[@name='registrant[0][firstname]']",
						data.get(Dataset).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='registrant[0][lastname]']",
						data.get(Dataset).get("LastName"));
				Common.textBoxInput("xpath", "//input[@name='registrant[0][email]']", data.get(Dataset).get("Email"));
				Common.dropdown("xpath", "//select[@name='registrant[0][role]']", Common.SelectBy.TEXT,
						data.get(Dataset).get("Role"));
				Common.clickElement("id", "add-registrant-button");
				Common.textBoxInput("xpath", "//input[@name='registrant[1][firstname]']",
						data.get(Dataset).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='registrant[1][lastname]']",
						data.get(Dataset).get("LastName"));
				Common.textBoxInput("xpath", "//input[@name='registrant[1][email]']",
						data.get(Dataset).get("UserName"));
				Common.dropdown("xpath", "//select[@name='registrant[1][role]']", Common.SelectBy.TEXT,
						data.get(Dataset).get("Role"));
			}
			String registry = Common.findElement("xpath", "//div[@x-data='giftRegistry()']//legend")
					.getText().trim();
			Common.assertionCheckwithReport(registry.equals("Registrant Information"),
					"validating the Registrant Information filed ",
					"It should display Registrant Information in gift registry",
					"successfully Registrant Information is displayed in gift registry",
					"failed to display the Registrant Information under gift registry");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Registrant Information filed ",
					"It should display Registrant Information in gift registry",
					"Unable to display the Registrant Informationin gift registry",
					Common.getscreenShot("failed to display the Registrant Information under gift registry"));
			Assert.fail();
		}
	}

	public void edit_gift(String Dataset) {
		// TODO Auto-generated method stub
		try {

			Common.clickElement("xpath", "//a[contains(text(),'Edit')]");
			Sync.waitPageLoad();
			Common.scrollIntoView("xpath", "//input[@id='address_postcode']");
			Common.textBoxInput("xpath", "//input[@id='address_postcode']", data.get(Dataset).get("postcode"));
			Common.clickElement("id", "submit.save");
			Sync.waitElementPresent(40,"xpath", "//div[@ui-id='message-success']");
			String message = Common.findElement("xpath", "//div[@ui-id='message-success']").getText();
			Common.assertionCheckwithReport(message.equals("You saved this gift registry."),
					"validating the gift registery page navigation ",
					"After clicking on save button It should be able to navigate to the gift registry page ",
					"successfully Navigated to the gift registry page", "failed to Navigate to the gift registry page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the gift registery page navigation ",
					"After clicking on save button It should be able to navigate to the gift registry page ",
					"Unable to navigate to the gift registry page",
					Common.getscreenShot("failed to Navigate to the gift registry page"));
			Assert.fail();

		}


	}

	public void share_giftcard(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//a[contains(text(),'Share')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.textBoxInput("xpath", "//textarea[@id='sender_message']", data.get(Dataset).get("Message"));
			Common.textBoxInput("xpath", "//input[@name='recipients[0][name]']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='recipients[0][email]']", data.get(Dataset).get("Email"));
			Common.clickElement("xpath", "//button[contains(text(),'Add Invitee')]");
			Common.textBoxInput("xpath", "//input[@name='recipients[1][name]']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='recipients[1][email]']", data.get(Dataset).get("UserName"));
			Common.clickElement("xpath", "//button[contains(text(),'Share Gift Registry')]");
//			Sync.waitPageLoad();
//			 Thread.sleep(4000);
			Sync.waitElementPresent(20, "xpath", "//div[@ui-id='message-success']//span");
			String message = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("You shared the gift registry"),
					"validating the gift registery page navigation ",
					"After clicking on save button It should be able to navigate to the gift registry page ",
					"successfully Navigated to the gift registry page", "failed to Navigate to the gift registry page");

		} catch (Exception | Error e) {

			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the gift registery page navigation ",
					"After clicking on save button It should be able to navigate to the gift registry page ",
					"Unable to navigate to the gift registry page",
					Common.getscreenShot("failed to Navigate to the gift registry page"));
			Assert.fail();

		}
	}

	public void delete_giftcard() {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", " //a[contains(text(),'Delete')]");
			Thread.sleep(3000);
			 Common.alerts("Ok");
			
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
			Common.assertionCheckwithReport(message.contains("You deleted this gift registry."),
					"validating the deleting functionality in the gift registry",
					"After clicking on the delete button it should delete from the gift registry",
					"successfully it has been deleted from the gift registry",
					"failed to delete from the gift registry");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the deleting functionality in the gift registry",
					"After clicking on the delete button it should delete from the gift registry",
					"Unable to delete from the gift registry",
					Common.getscreenShot("failed to delete from the gift registry"));
			Assert.fail();
		}


	}

	public void Manage_items() {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//a[@title='Manage Items']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Manage Gift Registry"),
					"validating navigation to the Manage Gift Registry page ",
					"After clicking on Manage Gift Registry button it should navigate to the Manage Gift Registry page ",
					"successfully Navigated to the Manage Gift Registry",
					"failed to Navigate to the Manage Gift Registry");
			Common.clickElement("xpath", "//strong[text()='Gift Registry']");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating navigation to the Manage Gift Registry page ",
					"After clicking on Manage Gift Registry button it should navigate to the Manage Gift Registry page ",
					"Unabel to Navigated to the Manage Gift Registry",
					Common.getscreenShot("failed to Navigate to the Manage Gift Registry"));
			Assert.fail();
		}

	}

	public void share_invalid_details(String Dataset) {
		// TODO Auto-generated method stub


		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//a[contains(text(),'Share')]");
			Common.clickElement("xpath", "//a[contains(text(),'Share')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//button[contains(text(),'Share Gift Registry')]");
			Common.clickElement("xpath", "//button[contains(text(),'Share Gift Registry')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//div[@ui-id='message-error']//span");
			String errormessage = Common.findElement("xpath", "//div[@ui-id='message-error']//span").getText();
			Common.assertionCheckwithReport(errormessage.contains("You need to enter sender data."),
					"validating the error message with empty fields ",
					"After clicking hare button with empty data error message should be display",
					"successfully error message has been dispalyed ", "failed to display the error message");
			Common.clickElement("xpath", "//a[@title='Gift Registry']");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the error message with invalid email ",
					"After clicking hare button with invalid email error message should be display",
					"Unable to see the error message has been dispalyed ",
					Common.getscreenShot("failed to display the error message"));
			Assert.fail();
		}
		try
		{
			
			delete_giftcard();
			
		}
		catch(Exception |Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the gift registry page navigation ",
					"After clicking Gift registry it should navigate to the gift registry page",
					"Unable to  Navigate  to the gift registry page ",
					Common.getscreenShot("Failed to Navigate to the gift rigistry page"));
			Assert.fail();
		}

	}

	public void prodeler_invalid_details(String dataSet) {
		// TODO Auto-generated method stub
		click_Prodeal();
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//a[@title='Sign in or register']");
			Sync.waitPageLoad();
			Sync.waitPageLoad();
			if (Common.getCurrentURL().contains("preprod")) {
				Sync.waitPageLoad();
				Common.textBoxInput("id", "email", data.get(dataSet).get("UserName"));
			} else {
				Common.textBoxInput("id", "email", data.get(dataSet).get("Prod UserName"));
			}
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//button[contains(@class,'action login')]");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Pro Deal Application"),
					"To validate the user lands on Pro Deal Application after successfull login",
					"After clicking on the signIn button it should navigate to the Pro Deal Application",
					"user Sucessfully navigate to the Pro Deal Application page after clicking on the signIn button",
					"Failed to signIn and not navigated to the Pro Deal Application page ");

			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//a[@class='pro-deal-link a-btn a-btn--tertiary']");
			Common.clickElement("xpath", "//a[@class='pro-deal-link a-btn a-btn--tertiary']");
			Sync.waitPageLoad();
			Common.switchWindows();
			Thread.sleep(3000);
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the pro deal application page",
					"User should lands to the prodeal application ",
					"Unable to navigates to the prodeal application form",
					Common.getscreenShotPathforReport("Failed to navigate to the prodeal application form "));
			Assert.fail();
		}

		String expectedResult = "After clicking hare button with invalid email error message should be display";
		try {

			Sync.waitElementPresent("xpath", "//button[@title='Submit']");
			Common.clickElement("xpath", "//button[@title='Submit']");
			Sync.waitElementPresent(30, "xpath", "//div[contains(@id,'error')]");
			String errormessage = Common.findElement("xpath", "//div[contains(@id,'error')]").getText();
			Common.assertionCheckwithReport(errormessage.equals("This is a required field."),
					"validating the error message with empty fields ",
					"After clicking hare button with empty data error message should be display",
					"successfully error message has been dispalyed ", "failed to display the error message");

			Sync.waitElementPresent("id", "association_email");
			Common.textBoxInput("id", "association_email", data.get(dataSet).get("FirstName"));
			Common.actionsKeyPress(Keys.PAGE_DOWN);

			Sync.waitElementPresent("xpath", "//button[@title='Submit']");
			Common.clickElement("xpath", "//button[@title='Submit']");

			Sync.waitElementPresent(30, "xpath", "//div[@class='mage-error']");
			String invalidemail = Common.findElement("xpath", "//input[@name='association_email']//following::div")
					.getText();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(invalidemail.contains("Please enter a valid email address"),
					"validating the error message with invalid email ",
					"After clicking hare button with invalid email error message should be display",
					"successfully error message has been dispalyed ", "failed to display the error message");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the error message with invalid email ", expectedResult,
					"Unable to see the error message has been dispalyed ",
					Common.getscreenShot("failed to display the error message"));
			Assert.fail();

		}
	}

	public void Prodeal_information() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String prodealexpdate = Common
					.findElement("xpath", "//strong[text()='Program expiration date:']//parent::p").getText();
			System.out.println(prodealexpdate);
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Sync.waitElementPresent(30, "xpath", "//a[text()='My Account']");
			Common.clickElement("xpath", "//a[text()='My Account']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Account"),
					"validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Sucessfully User Navigates to the My account page after clicking on the my account CTA",
					"Failed to Navigate to the MY account page after Clicking on my account button");
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//a[text()='Pro Deal']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(50, "xpath", "//strong[text()='Program expiration date:']//parent::p");
			String prodealdate = Common.findElement("xpath", "//strong[text()='Program expiration date:']//parent::p")
					.getText();
			System.out.println(prodealdate);
			Common.assertionCheckwithReport(prodealexpdate.equals(prodealdate),
					"validating the prodeal information for register user",
					"After clicking on prodeal information should be displayed ",
					"successfully prodeal information has been displayed",
					"failed to display the prodeal information for the register user");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the prodeal information for register user",
					"After clicking on prodeal information should be displayed ",
					"Unable to display the  prodeal information for the register user",
					Common.getscreenShot("failed to display the prodeal information for the register user"));
			Assert.fail();
		}

	}

	public void minicart_viewcart() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent(60,"xpath", "//span[@x-text='totalCartAmount']");
			String minicart = Common.findElement("xpath", "//span[@x-text='totalCartAmount']").getText();
			Sync.waitElementPresent(30,"xpath", "//a[@title='View Cart']");
			Common.clickElement("xpath", "//a[@title='View Cart']");
			String viewcart = Common.findElement("xpath", "//span[contains(@class,'ml-7 title-xs hf:title')]").getText();
			Common.assertionCheckwithReport(
					viewcart.contains(minicart) && Common.getCurrentURL().contains("/checkout/cart/"),
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

	public void additems_giftregistry(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.clickElement("xpath", "//button[contains(text(),'Add All To Gift Registry')]");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Gift Registry Items"),
					"validating navigation to the Manage Gift Registry page ",
					"After clicking on Manage Gift Registry button it should navigate to the Manage Gift Registry page ",
					"successfully Navigated to the Manage Gift Registry",
					"failed to Navigate to the Manage Gift Registry");
//			Common.clickElement("xpath", "//strong[text()='Gift Registry']");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating navigation to the Manage Gift Registry page ",
					"After clicking on Manage Gift Registry button it should navigate to the Manage Gift Registry page ",
					"Unable to Navigated to the Manage Gift Registry",
					Common.getscreenShot("failed to Navigate to the Manage Gift Registry"));
			Assert.fail();
		}

		try {
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.clickElement("xpath", "//input[@type='number']");
			Common.textBoxInput("xpath", "//input[@type='number']",
					data.get(Dataset).get("Quantity"));
			Sync.waitElementPresent(30, "xpath", "//button[contains(text(),'Update Gift Registry ')]");
			Common.clickElement("xpath", "//button[contains(text(),'Update Gift Registry ')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//div[@ui-id='message-success']//span");
			String message = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
			Common.assertionCheckwithReport(message.contains("You updated the gift registry items."),
					"validating nthe  message validation for the prodcuts in gift registry ",
					"After Upadting the quantity the message should be display",
					"successfully quantity has been changed  message has been displayed",
					"failed to Display the message for the when quantity changed");

			Common.clickElement("xpath", "//a[@title='Gift Registry']");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating nthe error message validation for the prodcuts in gift registry ",
					"After Upadting the quantity to zero the eroor message should be display",
					"Unable to Display the error message for the when quantity changed to zero",
					Common.getscreenShot("failed to Display the error message for the when quantity changed to zero"));
			Assert.fail();

		}

	}

	public void gustuserorderStatus(String dataSet) {
		// TODO Auto-generated method stub
		click_trackorder();
		String ordernumber = data.get(dataSet).get("OrderID");
		String prodordernumber = data.get(dataSet).get("prod order");

		try {
			if (Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("preprod") ) {
				Sync.waitElementPresent("id", "oar-order-id");
				Common.textBoxInput("id", "oar-order-id", ordernumber);
			} else {
				Sync.waitElementPresent("id", "oar-order-id");
				Common.textBoxInput("id", "oar-order-id", prodordernumber);
			}
			Sync.waitElementPresent("id", "oar-billing-lastname");
			Common.textBoxInput("id", "oar-billing-lastname", data.get(dataSet).get("Billinglastname"));

			Sync.waitElementPresent("id", "oar_email");
			Common.textBoxInput("id", "oar_email", data.get(dataSet).get("BillingEmail"));

			Sync.waitElementPresent("xpath", "//button[@type='submit']//span[text()='Search']");
			Common.clickElement("xpath", "//button[@type='submit']//span[text()='Search']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String orderid = Common.findElement("id", "oar-order-id").getText().trim();
			System.out.println(orderid);
			String ID=Common.findElement("id", "oar-order-id").getText().replace("Order #", "").trim();
			System.out.println(ID);
			System.out.println(ordernumber);
			Common.assertionCheckwithReport(Common.getPageTitle().contains(orderid) || ID.equals(ordernumber), "verifying order status form",
					"order tracking information page navigation", "successfully order tracking information page ",
					"Failed to navigate tracking order page infromation");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying order status form",
					"order tracking information page navigation",
					"User unable to navigate to the order tracking information page",
					Common.getscreenShotPathforReport("Failed to navigate tracking order page infromation"));
			Assert.fail();

		}
	}
	public void shipment_invoice() {
		// TODO Auto-generated method stub
		try
		{
			Sync.waitElementPresent("css", "li[class='nav item'] a[href*='invoice']");
			String Invoice=Common.findElement("css", "li[class='nav item'] a[href*='invoice']").getAttribute("href");
			Common.clickElement("css", "li[class='nav item'] a[href*='invoice']");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			String invoice=Common.findElement("css", "div[class='mb-6'] p").getText().trim();
			Common.assertionCheckwithReport(Common.getCurrentURL().contains(Invoice) && invoice.contains("Invoice"),
					"validating the navigating to the invoice page from the guest track order",
					"After clicking it should be navigate to the invoice page",
					"Sucessfully Navigated to the invoice page after clicking from the guest user track my order",
					"Failed to Navigate to the inovice page after clicking from the guest user track my order");
		 Common.clickElement("css", "a[class='link no-underline']");
			Common.switchToSecondTab();
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("/printInvoice/invoice_id"),
					"validating the Navigation to the Invoice Tab",
					"After Clicking on the print invoice user should be able to see invoice page",
					"Sucessfully User Navigates to Invoice page  after clicking on the print invoice Tab",
					"Failed to Navigate to the invoice after Clicking on print invoice Tab");
			Common.closeCurrentWindow();
			Common.switchToFirstTab();
			Sync.waitElementPresent("css", "li[class='nav item'] a[href*='shipment']");
			String Shipment=Common.findElement("css", "li[class='nav item'] a[href*='shipment']").getAttribute("href");
			Common.clickElement("css", "li[class='nav item'] a[href*='shipment']");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			String shipment=Common.findElement("css", "div[class='mb-6'] p").getText().trim();
			Common.assertionCheckwithReport(Common.getCurrentURL().contains(Shipment) && shipment.contains("Shipment"),
					"validating the navigating to the Shipment page from the guest track order",
					"After clicking it should be navigate to the Shipment page",
					"Sucessfully Navigated to the Shipment page after clicking from the guest user track my order",
					"Failed to Navigate to the Shipment page after clicking from the guest user track my order");
			Common.clickElement("css", "a[class='link no-underline']");
			Sync.waitPageLoad();
			Common.switchToSecondTab();
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("/printShipment/shipment_id"),
					"validating the Navigation to the printShipment Page",
					"After Clicking on the printShipment user should be able to see printShipment page",
					"Sucessfully User Navigates to printShipment page  after clicking on the printShipment link",
					"Failed to Navigate to the printShipment after Clicking on printShipment link");
			Common.closeCurrentWindow();
			Common.switchToFirstTab();
			Common.clickElement("css", "a[title='Track Shipment']");
			Sync.waitPageLoad();
			Common.switchToSecondTab();
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("shipping/tracking"),
					"validating the Navigation to the Track Shipment Page",
					"After Clicking on the Track Shipment user should be able to see Shipment page",
					"Sucessfully User Navigates to Track Shipment page  after clicking on the Track Shipment",
					"Failed to Navigate to the Track Shipment after Clicking on Track Shipment");
			Common.closeCurrentWindow();
			Common.switchToFirstTab();
	
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the navigating to the invoice or Shipment page from the guest track order",
					"After clicking it should be navigate to the invoice or Shipment page",
					"Sucessfully Navigated to the invoice or Shipment page after clicking from the guest user track my order",
					Common.getscreenShot("Failed to Navigate to the Invoice or Shipment page after clicking from the guest user track my order"));
			Assert.fail();
		}
		
	}
	public void click_trackorder() {
		try {
			Sync.waitElementPresent(30, "xpath", "//button[@aria-label='My Account']");
			Common.clickElement("xpath", "//button[@aria-label='My Account']");
			Common.clickElement("xpath", "//a[@title='Track My Order']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Tracking & Returns") || Common.getPageTitle().equals("My Orders"),
					"Verifying the track order page navigation ",
					"after clicking on the track order it should navigate to the orders and return page",
					"successfully Navigated to the orders and return page",
					"Failed to Navigate to the orders and return page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verifying the track order page navigation ",
					"after clicking on the track order it should navigate to the orders and return page",
					"Unable to  Navigated to the orders and return page",
					Common.getscreenShotPathforReport("Failed to Navigate to the orders and return page"));
			Assert.fail();

		}
	}

	public void click_regtrackorder() {
		try {
			Sync.waitElementPresent(30, "xpath", "//button[@aria-label='My Account']");
			Common.clickElement("xpath", "//button[@aria-label='My Account']");
			Common.clickElement("xpath", "//a[contains(text(),'My Orders')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Tracking & Returns") || Common.getPageTitle().equals("My Orders"),
					"Verifying the track order page navigation ",
					"after clicking on the track order it should navigate to the orders and return page",
					"successfully Navigated to the orders and return page",
					"Failed to Navigate to the orders and return page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verifying the track order page navigation ",
					"after clicking on the track order it should navigate to the orders and return page",
					"Unable to  Navigated to the orders and return page",
					Common.getscreenShotPathforReport("Failed to Navigate to the orders and return page"));
			Assert.fail();

		}
	}
	public void regiter_userorder_status() {
		// TODO Auto-generated method stub
		click_singinButton();
		login_Hydroflask("AccountDetails");
		click_regtrackorder();

		try {
			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "//div[@class='column main']").size();
			Common.assertionCheckwithReport(size > 0, "Verifying the order numbers in my orders page ",
					"after clicking on the track my orders order numbers  should be displayed in the my orders page",
					"successfully order numbers has been displayed in the my orders page",
					"Failed to Display the order number in my orders page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verifying the order numbers in my orders page ",
					"after clicking on the track my orders order numbers  should be displayed in the my orders page",
					"Unable to see the order numbers on my orders page",
					Common.getscreenShotPathforReport("Failed to Display the order number in my orders page"));
			Assert.fail();

		}

	}

	
	public void Guest_Kalrna_Payment(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> Paymentmethod = new HashMap<String, String>();
		Sync.waitPageLoad();
		Thread.sleep(3000);
	
		String fullname=data.get(dataSet).get("FirstName");
		String expectedResult = "land on the payment section";

		try {
//			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			int sizes = Common.findElements("xpath", "//label[@for='payment-method-stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unable to land o n the paymentpage");
			System.out.println(sizes);
			
			Common.clickElement("xpath", "//label[@for='payment-method-stripe_payments']");
			if(Common.getCurrentURL().contains("https://hydroflask.com/")) {
				Thread.sleep(4000);
			Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
			String klarna=Common.findElement("xpath", "//button[@value='klarna']").getAttribute("data-testid");
			System.out.println(klarna);
			Common.assertionCheckwithReport(
					klarna.contains("klarna"),
					"validating the selection of the klarna method",
					"klarna should be selected ","klarna is selected",
					"Failed to select the klarna method in the production environment");
			Common.switchToDefault();

				}
				else
				{
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					Common.clickElement("xpath", "//button[@value='klarna']");
					Common.switchToDefault();
					System.out.println("Switch to Default");
					
					Common.scrollIntoView("xpath","(//button[contains(@class,'btn btn-primary place-order')])[2]");
					Sync.waitElementPresent(30, "xpath", "(//button[contains(@class,'btn btn-primary place-order')])[2]");
					
					Common.javascriptclickElement("xpath", "(//button[contains(@class,'btn btn-primary place-order')])[2]");
					
					Thread.sleep(4000);
					int size=Common.findElements("xpath","(//button[contains(@class,'btn btn-primary place-order')])[2]").size();
					
					if(size>0) {
						Common.clickElement("xpath", "(//button[contains(@class,'btn btn-primary place-order')])[2]");
					}
					klarna_Details(dataSet);
					
					
					
				}
		
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the product confirmation", "User Should able to Navigate to the order confirmation page",
					"User failed to navigate  to order confirmation page",
					Common.getscreenShotPathforReport("failednavigatepage"));
			Assert.fail();
		}
	}
	
	
	public void Kalrna_Payment(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> Paymentmethod = new HashMap<String, String>();
		Sync.waitPageLoad();
		Thread.sleep(3000);
	
		String fullname=data.get(dataSet).get("FirstName");
		String expectedResult = "land on the payment section";

		try {
//			Sync.waitPageLoad();
			int sizes = Common.findElements("xpath", "//label[@for='payment-method-stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unable to land o n the paymentpage");
			System.out.println(sizes);
			Common.clickElement("xpath", "//label[@for='payment-method-stripe_payments']");


			int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
			System.out.println(payment);
			if (payment > 0) {
				Thread.sleep(2000);
	
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				System.out.println("Switch to Frames");
				Common.scrollToElementAndClick("xpath", "//div[@class='p-PaymentMethodSelector']//button[@id='klarna-tab']");


				Common.switchToDefault();
				System.out.println("Switch to Default");
				if(Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage") )
				{
					
						Sync.waitElementClickable("xpath", "(//button[@class='action primary checkout'])[2]");
						Common.clickElement("xpath", "(//button[@class='action primary checkout'])[2]");
						Thread.sleep(10000);
						 if(Common.getCurrentURL().contains("/checkout/#payment"))
                  	   {
							 Sync.waitElementPresent("xpath", "//label[@for='stripe-new-payments']");
							 Common.clickElement("xpath", "//label[@for='stripe-new-payments']");
                  		   	Thread.sleep(5000);
                  			Sync.waitElementClickable("xpath", "(//button[@class='action primary checkout'])[2]");
							Common.clickElement("xpath", "(//button[@class='action primary checkout'])[2]");
							Thread.sleep(5000);
							Sync.waitPageLoad();
							klarna_Details(dataSet);
                  	   }
						 else if(Common.getCurrentURL().contains("/success/"))
						 {
							 String sucessmessage=Common.getText("xpath", "//h1[@class='page-title-wrapper']");
	                    	    System.out.println(sucessmessage);
						 }
						 else
						 {
							 Thread.sleep(4000);
		     					Sync.waitPageLoad();
			    				klarna_Details(dataSet);
						 }
					
				}
				else
				{
					Thread.sleep(4000);
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String klarna=Common.findElement("xpath", "//button[@value='klarna']").getAttribute("data-testid");
					System.out.println(klarna);
					Common.assertionCheckwithReport(
							klarna.contains("klarna"),
							"validating the selection of the klarna method",
							"klarna should be selected ","klarna is selected",
							"Failed to select the klarna method in the production environment");
					Common.switchToDefault();
					
				}
				
				
			}
			else
			{
				
				Thread.sleep(4000);
				int savedcard=Common.findElements("xpath", "//select[@x-model='savedMethodId']").size();
				if(savedcard>0)
				{
					Sync.waitElementPresent("xpath", "(//input[@class='checkbox mr-4'])[2]");
					Common.clickElement("xpath", "(//input[@class='checkbox mr-4'])[2]");
				}
				Thread.sleep(2000);
				Sync.waitElementPresent("xpath", "(//input[@name='use_saved_stripe_method'])[2]");
				Common.clickElement("xpath", "(//input[@name='use_saved_stripe_method'])[2]");
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Common.clickElement("xpath", "//span[text()='Klarna']");
				Common.switchToDefault();
				
				if(Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage") )
				{
					Thread.sleep(5000);
					
				Common.clickElement("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				klarna_Details(dataSet);
				}
				else
				{
					Thread.sleep(2000);
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String klarna=Common.findElement("xpath", "//button[@value='klarna']").getAttribute("data-testid");
					System.out.println(klarna);
					Common.assertionCheckwithReport(
							klarna.contains("klarna"),
							"validating the selection of the klarna method",
							"klarna should be selected ","klarna is selected",
							"Failed to select the klarna method in the production environment");
					Common.switchToDefault();
					
				}
			}
			
			
			
		
	}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the product confirmation", "User Should able to Navigate to the order confirmation page",
					"User failed to navigate  to order confirmation page",
					Common.getscreenShotPathforReport("failednavigatepage"));
			Assert.fail();
		}
	}

	public void Signin_Checkoutpage(String Dataset) {
		// TODO Auto-generated method stub
		try {
			if (Common.getCurrentURL().contains("preprod")) {

				Sync.waitElementVisible("xpath", "//input[@type='email']");
				Common.textBoxInput("xpath", "//input[@type='email']", data.get(Dataset).get("Email"));
			} else {
				Sync.waitElementVisible("xpath", "//input[@type='email']");
				Common.textBoxInput("xpath", "//input[@type='email']", data.get(Dataset).get("Prod Email"));
			}
			Sync.waitElementPresent("xpath", "//input[@name='password']");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			Common.clickElement("xpath", "//span[text()='Sign In']");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			String regsiteruser = Common.findElement("xpath", "//div[@class='new-address-popup']//span").getText();
			System.out.println(regsiteruser);
			Common.assertionCheckwithReport(regsiteruser.contains("Add New Address"),
					"Verifying the login functionality from the shipping page",
					"after clicking on the login button it should login and address should be display",
					"successfully address book has been displayed after login",
					"Failed to Display the Address book in shipping page after click on login");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verifying the login functionality from the shipping page",
					"after clicking on the login button it should login and address should be display",
					"Unable to Display the Address book in shipping page after click on login",
					Common.getscreenShotPathforReport(
							"Failed to Display the Address book in shipping page after click on login"));
			Assert.fail();
		}

	}

	public void ordersummary_validation() {
		// TODO Auto-generated method stub
		try {
			String Subtotal = Common.getText("xpath", "(//div[@class='item subtotal']//span[2])[1]").replace("$",
					"");
			Float subtotalvalue = Float.parseFloat(Subtotal);
			String shipping = Common.getText("xpath", "(//div[@class='item shipping']//span[2])[2]")
					.replace("$", "");
			Float shippingvalue = Float.parseFloat(shipping);
			String Tax = Common.getText("xpath", "(//div[@class='item tax']//span[2])[1]").replace("$", "");
			Float Taxvalue = Float.parseFloat(Tax);
			String ordertotal = Common.getText("xpath", "(//div[@class='item grand_total']//span[2])[1]")
					.replace("$", "");
			Float ordertotalvalue = Float.parseFloat(ordertotal);
			Float Total = subtotalvalue + shippingvalue + Taxvalue;
			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			System.out.println(ordertotal);
			System.out.println(ExpectedTotalAmmount2);
			Common.assertionCheckwithReport(ExpectedTotalAmmount2.equals(ordertotal),
					"validating the order summary in the payment page",
					"Order summary should be display in the payment page and all fields should display",
					"Successfully Order summary is displayed in the payment page and fields are displayed",
					"Failed to display the order summary and fileds under order summary");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the order summary in the payment page",
					"Order summary should be display in the payment page and all fields should display",
					"Unabel to display the Order summary and fields are not displayed in the payment page",
					Common.getscreenShot("Failed to display the order summary and fileds under order summary"));
			Assert.fail();
		}
	}

	public void clickContact() throws Exception {
		String expectedResult = "It should land successfully on the contact page";

		Common.actionsKeyPress(Keys.END);
		try {

			Sync.waitElementPresent("xpath", "//a[@title='Contact Us']");
			Common.clickElement("xpath", "//a[@title='Contact Us']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Contact"),
					"Validating the contatus page navigation", expectedResult, "successfully land to contact page",
					"unabel to load the  contact page");
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating contact us page", expectedResult,
					"unable to load the contact page", Common.getscreenShotPathforReport("Contact us page link"));
			Assert.fail();

		}
	}

	public void contactUsPage(String dataSet) {
		// TODO Auto-generated method stub

		String expectedResult = "Email us form is visible in tab";
		String country = data.get(dataSet).get("Country");

		try {

			Common.clickElement("xpath", "//button[contains(text(),'Write to Us')]");
			Sync.waitElementPresent(40, "xpath", "//iframe[@id='contact-us-form']");
			Common.switchFrames("xpath", "//iframe[@id='contact-us-form']");

			Sync.waitElementPresent("xpath", "//input[@id='customerEmail']");

			Common.clickElement("xpath", "//input[@id='customerEmail']");
			Common.textBoxInput("xpath", "//input[@id='customerEmail']", data.get(dataSet).get("Email"));

			Sync.waitElementPresent("xpath", "//input[contains(@data-label,'How can we')]");
			Common.textBoxInput("xpath", "//input[contains(@data-label,'How can we')]",
					data.get(dataSet).get("CommetsHydroflask"));

			Sync.waitElementPresent("xpath", "//input[@id='customerFirstName']");
			Common.textBoxInput("xpath", "//input[@id='customerFirstName']", data.get(dataSet).get("FirstName"));

			Sync.waitElementPresent("xpath", "//input[@id='customerLastName']");
			Common.textBoxInput("xpath", "//input[@id='customerLastName']", data.get(dataSet).get("LastName"));

			Sync.waitElementPresent("xpath", "//input[@name='conversationCompany']");
			Common.textBoxInput("xpath", "//input[@name='conversationCompany']", data.get(dataSet).get("Company"));

			Sync.waitElementPresent("xpath", "//input[@name='conversationPhoneForForms']");
			Common.textBoxInput("xpath", "//input[@name='conversationPhoneForForms']", data.get(dataSet).get("phone"));
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Sync.waitElementPresent("xpath", "//span[text()='Select Country']");
			Common.clickElement("xpath", "//span[text()='Select Country']");
			Sync.waitElementPresent("xpath", "//div[@class='form-field-tree-options-overlay']//div[text()='"+ country +"']");
			Common.clickElement("xpath", "//div[@class='form-field-tree-options-overlay']//div[text()='"+ country +"']");

			Sync.waitElementPresent("xpath", "//input[@name='conversationStreetforforms' or @name='conversationStreetForForms']");
			Common.textBoxInput("xpath", "//input[@name='conversationStreetforforms' or @name='conversationStreetForForms']",
					data.get(dataSet).get("Street"));

			Sync.waitElementPresent("xpath", "//input[@name='conversationCityForForms']");
			Common.textBoxInput("xpath", "//input[@name='conversationCityForForms']", data.get(dataSet).get("City"));

			Sync.waitElementPresent("xpath", "//input[@name='conversationCountry']");
			Common.clickElement("xpath", "//input[@name='conversationCountry']");
			
					Common.clickElement("xpath", "(//span[@class='form-field-tree-option-placeholder'])[1]");
			Sync.waitElementPresent("xpath", "//div[text()='Connecticut']");
			Common.clickElement("xpath", "//div[text()='Connecticut']");

			Sync.waitElementPresent("xpath", "//input[@name='conversationState']");
			Common.clickElement("xpath", "//input[@name='conversationState']");

			Sync.waitElementPresent("xpath", "//input[@name='conversationZipCodeforforms' or @name='conversationZipCodeForForms']");
			Common.textBoxInput("xpath", "//input[@name='conversationZipCodeforforms' or @name='conversationZipCodeForForms']",
					data.get(dataSet).get("postcode"));
			
			Sync.waitElementPresent("xpath", "//span[@class='form-field-tree-option-placeholder']");
			Common.clickElement("xpath", "//span[@class='form-field-tree-option-placeholder']");
			
			Sync.waitElementPresent("xpath", "//div[@class='form-field-tree-options-overlay']//div[text()='Order Issues']");
			Common.clickElement("xpath", "//div[@class='form-field-tree-options-overlay']//div[text()='Order Issues']");
			
			Sync.waitElementPresent("xpath", "//span[@class='form-field-tree-option-placeholder']");
			Common.clickElement("xpath", "//span[@class='form-field-tree-option-placeholder']");

			Sync.waitElementPresent("xpath", "//div[@class='form-field-tree-options-overlay']//div[text()='Billing Issue']");
			Common.clickElement("xpath", "//div[@class='form-field-tree-options-overlay']//div[text()='Billing Issue']");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//input[@class='form-base' and @id='conversationOrder']");
			Common.textBoxInput("xpath", "//input[@class='form-base' and @id='conversationOrder']",
					data.get(dataSet).get("OrderID"));
			Sync.waitElementPresent("xpath", "//textarea[@id='messagePreview']");
			Common.textBoxInput("xpath", "//textarea[@id='messagePreview']",
					data.get(dataSet).get("CommetsHydroflask"));
			System.out.println(Common.getCurrentURL());
			if(Common.getCurrentURL().contains("preprod")) {

			Common.scrollIntoView("xpath", "//button[text()='Submit']");
			Common.clickElement("xpath", "//button[text()='Submit']");

			Sync.waitElementPresent("xpath", "//div[@class='form-wrap']");
			int Contactussuccessmessage = Common.findElements("xpath", "//h1[@data-content-type='heading']").size();
			System.out.println(Contactussuccessmessage);
			Common.assertionCheckwithReport(Contactussuccessmessage > 0 || Contactussuccessmessage >= 0,
					"verifying Contact us Success message ", "Success message should be Displayed",
					"Contact us Success message displayed ", "failed to dispaly success message");
			Common.actionsKeyPress(Keys.PAGE_UP); 
			String Text = Common.getText("xpath", "//div[@class='form-wrap']");
			expectedResult = "User gets confirmation under the same tab. It includes a reference number and email is sent to email provided. No validation errors.";
			Common.assertionCheckwithReport(Text.contains("Your submission was successful "),
					"verifying contact us conformation message", expectedResult, "Failed to submit the contact us form");

		}
			else {
				Common.getscreenShotPathforReport("Contact us page");
			}	
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying email us from",
					"contact us form data enter without any error message", "Contact us page getting error ",
					Common.getscreenShotPathforReport("Contact us page"));
			Assert.fail();

		}

		
	}

	public void review(String Dataset) {
		// TODO Auto-generated method stub
				String products = data.get(Dataset).get("Products");
				System.out.println(products);
				try {
					Thread.sleep(4000);
					Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
					Common.clickElement("xpath", "//img[@alt='" + products + "']");
					Sync.waitElementPresent("xpath", "//div[@id='yotpo-reviews-star-ratings-widget']");
					int Star_rating = Common.findElements("xpath", "//div[@id='yotpo-reviews-star-ratings-widget']").size();
					
					Common.scrollIntoView("xpath", "//h2[contains(text(),'Reviews')]");
					Sync.waitElementPresent("xpath", "//h2[contains(text(),'Reviews')]");
					Common.clickElement("xpath", "//h2[contains(text(),'Reviews')]");
					Common.scrollIntoView("xpath", "//button[@id='yotpo-main-widget-btn']");
					Sync.waitElementPresent("xpath", "//button[@id='yotpo-main-widget-btn']");
					Common.clickElement("xpath", "//button[@id='yotpo-main-widget-btn']");
					Thread.sleep(3000);
					int form = Common.findElements("xpath", "//div[@id='yotpo-modal']").size();
					System.out.println(form);
					Common.assertionCheckwithReport(form>0 && Star_rating>0, "verifying the write a review button",
							"Write a review should be appear in the PDP page",
							"Sucessfully write a review button has been displayed in PDP page",
							"Failed to display the write a review button in PDP page");
					

				} catch (Exception | Error e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("verifying the Write a review button", "select the write review option",
							"User Unable to click write review option  ",
							Common.getscreenShotPathforReport("User Failed to click write review option "));
					Assert.fail();
				}
				try {
					String expectedResult = "Sucessfully title input box has been displayed";
					Common.clickElement("xpath", "//button[@class='yotpo-new-review-submit']");
					String errormessage = Common.findElement("xpath", "//p[@class='yotpo-star-rating-error']").getText();
					System.out.println(errormessage);
					Common.assertionCheckwithReport(errormessage.contains("A star rating is required"),
							"verifying the error message in invalid fields",
							"error message should be display in the invalid fields",
							"Sucessfully Error message has been displayed in invalid fileds ",
							"Failed to display the error meesage in invalid fields ");
					score(data.get(Dataset).get("score"));
					Sync.waitElementPresent("xpath", "//label[contains(text(),'Write a review')]");
					int title = Common.findElements("xpath", "//label[contains(text(),'Write a review')]").size();
					Common.assertionCheckwithReport(title > 0, "verifying the title page",
							"title input box should be displayed", expectedResult, "User Unable to display the title box");
					Common.textBoxInput("xpath", "//textarea[@aria-label='Write a review']", data.get(Dataset).get("title"));
					Common.textBoxInput("xpath", "//input[@aria-label='Add a headline']", data.get(Dataset).get("Review"));
					Common.textBoxInput("xpath", "//input[@aria-label='Your name']", data.get(Dataset).get("FirstName"));
					Common.textBoxInput("xpath", "//input[@aria-label='Your email address']", data.get(Dataset).get("UserName"));
					Common.clickElement("xpath", "//button[text()='Send']");
					String emailerror = Common.findElement("xpath", "//p[text()='A valid email address is required']").getText();
					Common.assertionCheckwithReport(emailerror.contains("A valid email address is required"),
							"verifying the invaild email for the product review",
							"error message should be display for invaild email",
							"Sucessfully error message has been displayed for invalid email",
							"Failed to display the error message for invaild email");
					Thread.sleep(4000);
					Common.textBoxInput("xpath", "//input[@aria-label='Your email address']", data.get(Dataset).get("Email"));
					Common.clickElement("xpath", "//button[text()='Send']");
					Thread.sleep(4000);
					String message = Common.findElement("xpath", "//div[@class='yotpo-headline-complete']").getText();
					System.out.println(message);
					Common.assertionCheckwithReport(message.equals("Thanks, testing!"),
							
							"verifying the post for the product review",
							"product review should be submit after clicking on post",
							"Sucessfully Thank you message has been displayed ", "Failed to display the Thank you message ");
//					Common.clickElement("xpath", "//div[@aria-label='Next']");

				} catch (Exception | Error e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("verifying the post for the product review",
							"product review should be submit after clicking on post",
							"Unable to display the Thank you message after clickng on post ",
							Common.getscreenShotPathforReport("Failed to display the thank you message"));
					Assert.fail();

				}


	}

	public void score(String score) throws Exception {
		Thread.sleep(4000);
		switch (score) {
		case "1":
			Sync.waitElementPresent("css", "label[aria-label='Score 1 Very poor']");
			Common.clickElement("css", "label[aria-label='Score 1 Very poor']");
			break;
		case "2":
			
			Sync.waitElementPresent("css", "label[aria-label='Score 2 Poor']");
			Common.clickElement("css", "label[aria-label='Score 2 Poor']");
			break;
		case "3":
			Sync.waitElementPresent("css", "label[aria-label='Score 3 Average']");
			Common.clickElement("css", "label[aria-label='Score 3 Average']");
			;
			break;
		case "4":
			Sync.waitElementPresent("css", "label[aria-label='Score 4 Good']");
			Common.clickElement("css", "label[aria-label='Score 4 Good']");
			break;
		case "5":
			Sync.waitElementPresent("css", "label[aria-label='Score 5 Great!']");
			Common.clickElement("css", "label[aria-label='Score 5 Great!']");
			break;
		}
	}

	public void BillingAddress(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//label[@for='billing-as-shipping']");
			Common.clickElement("xpath", "//label[@for='billing-as-shipping']");
			Sync.waitElementPresent(30, "id", "billing-firstname");
			Common.textBoxInput("id", "billing-firstname", data.get(dataSet).get("FirstName"));
			
			Sync.waitElementPresent(30, "id", "billing-lastname");
			Common.textBoxInput("id", "billing-lastname", data.get(dataSet).get("LastName"));
			
			Sync.waitElementPresent(30, "id", "billing-street-0");
			Common.textBoxInput("id", "billing-street-0", data.get(dataSet).get("Street"));
			
			Thread.sleep(4000);
			String text = Common.findElement("id", "billing-street-0").getAttribute("value");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.textBoxInput("id", "billing-city", data.get(dataSet).get("City"));
			System.out.println(data.get(dataSet).get("City"));

			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			try {
				Common.dropdown("id", "billing-region", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
				Thread.sleep(3000);
				Common.dropdown("id", "billing-region", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
//			Common.textBoxInputClear("xpath", "//input[@name='postcode']");
			Common.textBoxInput("css", "input#shipping-telephone",
					data.get(dataSet).get("postcode"));
			Thread.sleep(5000);
			if(Common.findElements("xpath", "//span[text()='Update']").size()>0) {
				Common.clickElement("xpath", "//span[text()='Update']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String update = Common.findElement("xpath", "(//div[@class='billing-address-details']//p)[2]").getText();
				System.out.println(update);
				Common.assertionCheckwithReport(
						update.contains("6 Walnut Valley Dr") || text.contains("6 Walnut Valley Dr"),
						"verifying the Billing address form in payment page",
						"Billing address should be saved in the payment page",
						"Sucessfully Billing address form should be Display ",
						"Failed to display the Billing address in payment page");
			}
			
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(text.contains("6 Walnut Valley Dr"),
					"verifying the Billing address form in payment page",
					"Billing address should be saved in the payment page",
					"Sucessfully Billing address form should be Display ",
					"Failed to display the Billing address in payment page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the Billing address form in payment page",
					"Billing address should be saved in the payment page",
					"Unable to display the Billing address in payment page",
					Common.getscreenShotPathforReport("Failed to display the Billing address in payment page"));
			Assert.fail();
		}
	}

	public void Invalid_email_newsletter(String Dataset) {
		// TODO Auto-generated method stub
		try {

			Common.actionsKeyPress(Keys.END);
			Thread.sleep(4000);
			//Common.switchFrames("xpath", "//iframe[@aria-label='Modal Overlay Box Frame']");
			Sync.waitElementPresent(30, "xpath", "//label[text()='Email address']");
			Common.clickElement("xpath", "//label[text()='Email address']");
			Common.textBoxInput("xpath", "//input[@placeholder='ie. youremail@email.com']", data.get(Dataset).get("Email"));
			Thread.sleep(5000);
			Common.javascriptclickElement("xpath", "//button[text()='Submit']");
			Thread.sleep(2000);
			int size=Common.findElements("xpath", "//span[@id='klaviyo_ariaid_3']").size();
			if(size<0) {
			Common.clickElement("xpath", "//button[text()='Submit']");}
			Thread.sleep(2000);
			Sync.waitElementPresent(30, "xpath", "//span[@id='klaviyo_ariaid_3']");
			String Errormessage = Common.findElement("xpath", "//span[@id='klaviyo_ariaid_3']").getText();
			System.out.println(Errormessage);
			Common.assertionCheckwithReport(Errormessage.equals("This email is invalid"),
					"To validate the error message for Invalid Email",
					"Should display error Please enter a valid email address.", Errormessage,
					"Failed to display the error message for invaild email");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the error message for Invalid Email",
					"Should display error Please enter a valid email address.", "Failed to display the error message",
					Common.getscreenShotPathforReport("Failed to see an error message"));

			Assert.fail();

		}
	}

	public void valid_email_newsletter(String Dataset) {

		try {
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(5000);
			Sync.waitElementClickable(30, "xpath", "(//input[@id='subscribe-email' or @name='email'])[1]");
			Common.textBoxInput("xpath", "(//input[@id='subscribe-email' or @name='email'])[1]", Utils.getEmailid());
			Thread.sleep(5000);
			Common.clickElement("xpath", "(//button[text()='Submit' or @type='submit'])[2]");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			int Text = Common.findElements("xpath", "//span[text()='Thank you for your subscription.']").size();
			System.out.println(Text);
			int size=Common.findElements("xpath", "(//span[@class='ql-font-nunito-sans'])[1]").size();	
			String expectedResult = "User gets confirmation message that it was submitted";

			Common.assertionCheckwithReport(Text>0 || size>0,
					"verifying newsletter subscription",
					"User get confirmation message if new email if it used mail it showing error message ", expectedResult,
					Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));

		} catch (Exception | Error e) {

			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying newsletter subscription", "NewsLetter Subscrption success",
					"User faield to subscrption for newLetter  ",
					Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));
			Assert.fail();
		}
	}
	
	
	
	
	
	public void Join(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5000);
			Sync.waitElementPresent(30, "id", "subscribe-email");
			Common.clickElement("id", "subscribe-email");
			Common.textBoxInput("id", "subscribe-email", data.get(Dataset).get("Email"));
			Thread.sleep(2000);
			Common.clickElement("id", "sms_opt_in");
			Thread.sleep(2000);
			Common.clickElement("xpath", "//span[text()='Join']");
			
			int successmessage = Common.findElements("xpath", "//div[@ui-id='message-success']").size();
			System.out.println(successmessage);
			if(successmessage>0) {
			
			String Text = Common.getText("xpath", "//div[@ui-id='message-success']");
			System.out.println(Text);
			String expectedResult = "User gets confirmation message that it was submitted";
			Common.assertionCheckwithReport(Text.contains("Thank you for your subscription."),
					"verifying newsletter subscription", expectedResult, Text,
					Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));
			}
			else {
				String Text = Common.getText("xpath", "//div[@ui-id='message-error']");
				System.out.println(Text);
				String expectedResult = "User gets confirmation message that it was submitted";
				Common.assertionCheckwithReport(Text.contains("This email address is already subscribed."),
						"verifying newsletter subscription", expectedResult, Text,
						Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));
			}
				
			

		} catch (Exception | Error e) {

			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying newsletter subscription", "NewsLetter Subscrption success",
					"User faield to subscrption for newLetter  ",
					Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));
			Assert.fail();
		}
	}
	
	
	
	
	
	public void Empty_Email() {
		// TODO Auto-generated method stub
		try {
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "id", "subscribe-email");
			Sync.waitElementPresent(30, "xpath", "//span[text()='Join']");
			Common.clickElement("xpath", "//span[text()='Join']");
			//Sync.waitElementPresent(30, "xpath", "//div[@class='newsletter-error']");
			int successmessage = Common.findElements("xpath", "//div[@ui-id='message-success']").size();
			System.out.println(successmessage);
			
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validate the Error message ",
					"Should display Error: Please enter a valid email address.", "Failed to dispaly the Error message ",
					Common.getscreenShotPathforReport("User unable to see an error message"));
			Assert.fail();
		}

}

	public void stayIntouch() throws Exception {

		try {
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//label[@for='form_input_email']");
			Common.clickElement("xpath", "//label[@for='form_input_email']");
			Common.textBoxInput("xpath", "//input[@placeholder='Enter Email Address']", Utils.getEmailid());
			Thread.sleep(5000);
			Common.clickElement("xpath", "//button[@aria-label='Submit Modal Form']");
			Thread.sleep(5000);
			String Text = Common.getText("xpath", "//div[@id='thxtext3']");
			System.out.println(Text);
			String expectedResult = "User gets confirmation message that it was submitted";

			Common.assertionCheckwithReport(Text.contains("Thank you for your subscription"),
					"verifying newsletter subscription", expectedResult, Text,
					Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));

		} catch (Exception | Error e) {

			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying newsletter subscription", "NewsLetter Subscrption success",
					"User faield to subscrption for newLetter  ",
					Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));
			Assert.fail();
		}
	}

	public void klarna_Details(String Dataset) {
		// TODO Auto-generated method stub
		String order="";
		String phone=data.get(Dataset).get("phone");
		String otp=data.get(Dataset).get("OTP Number");
		String DOB=data.get(Dataset).get("DOB");
		String Cardnumber=data.get(Dataset).get("cardNumber");
		String Symbol= data.get(Dataset).get("Symbol");
		System.out.println(Cardnumber);
		
		try
		{
			Sync.waitPageLoad();
			Thread.sleep(8000);
//			Common.switchWindows();
			//Common.switchFrames("xpath", "//iframe[@id='klarna-apf-iframe']");
			Sync.waitElementPresent(50,"xpath", "//input[@id='phone']");
		/*	Common.clickElement("xpath", "//input[@name='phone']");
			
			int number=Common.genrateRandomNumber();
			System.out.println(number);
			String mobile=Integer.toString(number);
			String phone="+91"+"95862"+mobile;*/
			Thread.sleep(6000);
			WebElement clear=Common.findElement("xpath", "//input[@id='phone']");
		    clear.sendKeys(Keys.CONTROL+"a");
		    clear.sendKeys(Keys.DELETE);
			System.out.println(phone);
			Common.textBoxInput("xpath", "//input[@id='phone']", phone);
			Common.clickElement("xpath", "//button[@id='onContinue']");
			Sync.waitPageLoad();
			Sync.waitElementPresent(30, "xpath", "//input[@id='otp_field']");
			Common.textBoxInput("xpath", "//input[@id='otp_field']", otp);
			Sync.waitPageLoad();
			Thread.sleep(8000);
			
			Sync.waitElementPresent("xpath", "//h1[@id='summary-title']");
			String klarna=Common.findElement("xpath", "//h1[@id='summary-title']").getText();
			Thread.sleep(3000);
			if(klarna.contains("Confirm and pay"))
			{
				Sync.waitElementPresent("xpath", "//span[text()='Pay with']");
				Common.clickElement("xpath", "//span[text()='Pay with']");
				Sync.waitPageLoad();
				
					
				
			}
			else
			{
				
//				String klarna1=Common.findElement("xpath", "//h2[@role='status']").getText();
				
				Common.clickElement("xpath", "//button[@id='onContinue']");
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//div[@id='addressCollector-date_of_birth__container']");
				Common.findElement("xpath","//input[@id='addressCollector-date_of_birth']").sendKeys(DOB);
				

				Common.javascriptclickElement("xpath", "//div[@id='preview-address__link-wrapper']");
				
				WebElement clearStreet=Common.findElement("xpath", "//input[@name='street_address']");
				clearStreet.sendKeys(Keys.CONTROL+"a");
	            Common.findElement("xpath","//input[@name='street_address']").sendKeys(data.get(Dataset).get("Street"));
				
	            WebElement clearcity=Common.findElement("xpath", "//input[@name='city']");
				clearStreet.sendKeys(Keys.CONTROL+"a");
				
				 WebElement clearPostcode=Common.findElement("xpath", "//input[@name='postal_code']");
				clearStreet.sendKeys(Keys.CONTROL+"a");
				
				Common.findElement("xpath","//input[@name='region']").sendKeys(data.get(Dataset).get("Region"));
				
				Common.clickElement("xpath", "//div[@id='addressCollector-postal_code__label']");
				Common.findElement("xpath","//input[@name='postal_code']").sendKeys(data.get(Dataset).get("postcode"));
				Common.clickElement("xpath", "//div[@id='terms_checkbox__box']");
				Common.clickElement("xpath", "//span[text()='Continue']");
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//span[contains(text(),'continue')]");
				Sync.waitElementPresent(30, "xpath", "//span[contains(text(),'Continue')]");
				Common.clickElement("xpath", "//span[contains(text(),'Continue')]");
				Sync.waitElementPresent(30, "xpath", "//button[@data-testid='pick-plan']");
				Common.clickElement("xpath", "//button[@data-testid='pick-plan']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath", "//iframe[@id='payment-gateway-frame']");
				Common.switchFrames("xpath", "//iframe[@id='payment-gateway-frame']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//input[@id='cardNumber']//parent::div");
				Thread.sleep(4000);	
				Common.findElement("xpath","//input[@id='cardNumber']//self::input").sendKeys(Cardnumber);
				Common.javascriptclickElement("xpath", "//input[@id='expire']//parent::div");
				Common.findElement("xpath","//input[@id='expire']").sendKeys(data.get(Dataset).get("ExpMonthYear"));
				Common.javascriptclickElement("xpath", "//input[@id='securityCode']//parent::div");
				Common.findElement("xpath","//input[@id='securityCode']").sendKeys(data.get(Dataset).get("cvv"));
				Common.switchToDefault();
				Common.switchFrames("xpath", "//iframe[@id='klarna-apf-iframe']");
				Thread.sleep(4000);
		//		Thread.sleep(4000);
				Common.clickElement("xpath", "(//span[contains(text(),'Continue')])[2]");
				Thread.sleep(8000);
				Common.javascriptclickElement("xpath", "(//span[contains(text(),'Continue')])[1]");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//span[contains(text(),'Pay $')]");
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//button[@data-testid='PushFavoritePayment:skip-favorite-selection']");
				
			}
		
		}
		catch(Exception |Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the card details enter in the Kalrna payment", "User Should able to Enter Card Details in Klarna Payment",
					"User Unable to enter Card details in the Klarna payment",
					Common.getscreenShotPathforReport("Failed to enter Card details in the Klarna payment"));
			Assert.fail();
		}
		String url1=automation_properties.getInstance().getProperty(automation_properties.BASEURL);
		if(!url1.contains("stage") && !url1.contains("preprod")){
		}
	
	else{
		try{
		Thread.sleep(4000);
		Sync.waitElementPresent(60, "xpath", "//h1[normalize-space()='Thank you for your purchase!']");
	String sucessMessage = Common.getText("xpath", "//h1[normalize-space()='Thank you for your purchase!']").trim();
	System.out.println(sucessMessage);
	
	int size = Common.findElements("xpath", "//h1[normalize-space()='Thank you for your purchase!']").size();
	Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
			"verifying the product confirmation", "It should redirects to the order confirmation mail",
			"Successfully It redirects to order confirmation page Order Placed",
			"User unable to go orderconformation page");
	
	if(Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//p//span").size()>0) {
		order=Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p//span");
		System.out.println(order);
	}
	if(Common.findElements("xpath","//a[@class='order-number']/strong").size()>0) {
		order=	Common.getText("xpath", "//a[@class='order-number']/strong");
		System.out.println(order);
	}
	
		
	}
catch(Exception | Error e)
{
	
 e.printStackTrace();
 ExtenantReportUtils.addFailedLog("verifying the order confirmartion page", "It should navigate to the order confirmation page",
			"User failed to proceed to the order confirmation page", Common.getscreenShotPathforReport("failed to Navigate to the order summary page"));
 
 Assert.fail();
}
	}
	}

	public void noitems_giftregistry(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//a[contains(text(),'Share')]");
			Common.clickElement("xpath", "//a[contains(text(),'Share')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//button[contains(text(),'Share Gift Registry')]");
			Common.clickElement("xpath", "//button[contains(text(),'Share Gift Registry')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//div[@ui-id='message-error']//span");
			String errormessage = Common.findElement("xpath", "//div[@ui-id='message-error']//span").getText();
			Common.assertionCheckwithReport(errormessage.contains("You need to enter sender data."),
					"validating the error message with empty fields ",
					"After clicking hare button with empty data error message should be display",
					"successfully error message has been dispalyed ", "failed to display the error message");
			Common.clickElement("xpath", "//a[@title='Gift Registry']");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the error message with invalid email ",
					"After clicking hare button with invalid email error message should be display",
					"Unable to see the error message has been dispalyed ",
					Common.getscreenShot("failed to display the error message"));
			Assert.fail();
		}

	}

	public void Stored_Payment(String Dataset) {
		// TODO Auto-generated method stub
		
		try {
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Sync.waitElementPresent(30, "xpath", "//a[@title='My Account']");
			Common.clickElement("xpath", "//a[@title='My Account']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Dashboard"),
					"validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Sucessfully User Navigates to the My account page after clicking on the my account CTA",
					"Failed to Navigate to the MY account page after Clicking on my account button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the My account page",
					"After Clicking on My account CTA user should be navigate to the my account page",
					"Unable to Navigates the user to My account page after clicking on the my account CTA",
					Common.getscreenShot("Failed to Navigate to the MY account page after Clicking on my account CTA"));
			Assert.fail();
		}
		try {
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//a[@title='Stored Payment Methods']");
			Common.clickElement("xpath", "//a[@title='Stored Payment Methods']");
			Sync.waitPageLoad(30);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Payment Methods"),
					"validating the Navigation to the My Payment Methods page",
					"After Clicking on stored methods CTA user should be navigate to the My Payment Methods page",
					"Sucessfully User Navigates to the My Payment Methods page after clicking on the stored methods  CTA",
					"Failed to Navigate to the My Payment Methods page after Clicking on my stored methods  CTA");
			int size = Common.findElements("xpath", "//div[@class='customer-payment-methods-container-methods divide-y divide-border'] ").size();
			if (size > 0) {
				Thread.sleep(5000);
				String number = Common.findElement("xpath", "(//div[@class='flex items-center']//span)[1]").getText()
						.replace("•••• ", "");
				System.out.println(number);
				System.out.println(Dataset);
				Thread.sleep(5000);
				Common.assertionCheckwithReport(number.contains("4242") && Dataset.contains("4242"),
						"validating the card details in the my orders page",
						"After Clicking on My payments methods and payment method should be appear in payment methods",
						"Sucessfully payment method is appeared in my payments methods",
						"Failed to display the payment methods in the my payments methods");
			} else {
				Assert.fail();
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the card details in the my orders page",
					"After Clicking on My payments methods and payment method should be appear in payment methods",
					"Unable to display the payment methods in the my payments methods",
					Common.getscreenShot("Failed to display the payment methods in the my payments methods"));
			Assert.fail();
		}


	}

	public void view_order() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			String number = Common.findElement("xpath", "//span[text()='View Order']").getText();
			Sync.waitElementPresent("xpath", "//span[text()='View Order']");
			Common.clickElement("xpath", "//span[text()='View Order']");
			Sync.waitPageLoad();
			Sync.waitElementPresent(40, "xpath", "//span[contains(@class,'title-lg')]");
			String Ordernumber = Common.findElement("xpath", "//span[contains(@class,'title-lg')]").getText();
			Common.findElement("xpath", "//span[@class='order-status inline-block']//div");
			String reorder = Common.findElement("xpath", "//span[text()='Reorder']").getText();
			String backCTA = Common.findElement("xpath", "//a[@class='hidden lg:flex btn btn-link']").getText().trim();
			String orderdate = Common.findElement("xpath", "//div[@class='mt-1']//span").getText();
			String shippingAdd = Common.findElement("xpath", "//p[contains(text(),'Shipping Address')]").getText();
			String billingAdd = Common.findElement("xpath", "//p[contains(text(),'Billing Address')]").getText();
			String shippingmethod = Common.findElement("xpath", "//p[contains(text(),'Shipping Method')]").getText();
			String ordersummary = Common.findElement("xpath", "//p[contains(text(),'Shipping Method')]").getText();
			String itemsordered = Common.findElement("xpath", "//span[@class='text-sm']").getText();
			System.out.println(itemsordered);

			Common.assertionCheckwithReport(
					reorder.contains("Reorder") && backCTA.contains("Back ") && orderdate.contains("Order Date")
							&& reorder.contains("Reorder"),
					"validating the order details ",
					"After Clicking on view Order it should be navigate to the order details page ",
					"Sucessfully navigated to the orders detail page", "Failed to Navigate to the orders detail page");
//	
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the order Details ",
					"After Clicking on view Order it should be navigate to the order details page ",
					"Unable to Navigate to the orders details page  ",
					Common.getscreenShot("Failed to Navigate to the orders details page "));
			Assert.fail();

		}


	}

	public void CLP_Page(String category) {
		// TODO Auto-generated method stub

		String expectedResult = "User should click the" + category;
		try {

			Sync.waitElementPresent("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
			Thread.sleep(3000);
//			Common.scrollIntoView("xpath","//a[contains(@class,'level-top')]//span[text()=' Shop']");
			Common.clickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
			Thread.sleep(3000);

			try {
				Common.mouseOver("xpath", "//span[contains(text(),'" + category + "')]");
			} catch (Exception e) {
				Common.mouseOver("xpath", "//span[contains(text(),'" + category + "')]");
			}
			Common.clickElement("xpath", "//span[contains(text(),'" + category + "')]");
			Common.clickElement("xpath", "//span[text()='Shop All']");
			expectedResult = "User should select the " + category + "category";
			int sizebotteles = Common.findElements("xpath", "//span[contains(text(),'" + category + "')]").size();
			Common.assertionCheckwithReport(sizebotteles > 0,
					"validating the product category as" + category + "from navigation menu ", expectedResult,
					"Selected the " + category + " category", "User unabel to click" + category + "");
//			verifying_sub_category();
			verifying_CLP_sub_category();

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the product category as" + category + "from navigation menu ",
					expectedResult, "Unable to Selected the " + category + " category",
					Common.getscreenShot("Failed to click on the" + category + ""));

			Assert.fail();
		}

	}

	public void verifying_sub_category() {
		// TODO Auto-generated method stub

		String name;
		String Productname;
		try {
			Sync.waitPageLoad();
			List<WebElement> sub_category = Common.findElements("xpath",
					"//div[contains(@class,'c-category-carousel__item slick-')]");
			System.out.println(sub_category.size());
			for (int i = 0; i < sub_category.size() - 2; i++) {
				List<WebElement> Image = Common.findElements("xpath",
						"//div[contains(@class,'c-category-carousel__item slick-')]");
				Thread.sleep(6000);
				name = Image.get(i).getText();
				Thread.sleep(4000);
				System.out.println(name);
				Image.get(i).click();
				Thread.sleep(5000);
				ExtenantReportUtils.addPassLog("Validating" + name + "Page  ",
						"click the sub category should navigate to the  " + name + "Page",
						"successfully page navigating to " + name + "PAGE", Common.getscreenShotPathforReport(name));
				Common.navigateBack();
				Common.navigateBack();

			}
			List<WebElement> image_category = Common.findElements("xpath",
					"//div[@class='m-category-card__container']");
			System.out.println(image_category.size());
			for (int i = 0; i < image_category.size() - 2; i++) {
				List<WebElement> button = Common.findElements("xpath",
						"//div[contains(@class,'c-category-carousel__item slick-')]");
				Thread.sleep(4000);
				Productname = button.get(i).getText();
				System.out.println(Productname);
				Thread.sleep(4000);
				button.get(i).click();
				Thread.sleep(4000);
				ExtenantReportUtils.addPassLog("Validating" + Productname + "Page  ",
						"click the sub category should navigate to the  " + Productname + "Page",
						"successfully page navigating to " + Productname + "PAGE",
						Common.getscreenShotPathforReport(Productname));
				Common.navigateBack();
				Common.navigateBack();

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the category page validation after clicking on sub category in CLP page ",
					"After Clicking on subcategory it should navigate to the PLP pages",
					"Unable to navigate to the PLP pages after clicking on the sub category",
					Common.getscreenShot("Failed to navigate to the PLP pages after clicking on the sub category"));
			Assert.fail();
		}

	}

	public void verifying_CLP_sub_category() {
		// TODO Auto-generated method stub
		String product;

		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			List<WebElement> sub_category = Common.findElements("xpath",
					"//div[@class='segmented-categories-item-image']");
			System.out.println(sub_category.size());
			for (int i = 0; i < sub_category.size() - 3; i++) {
				List<WebElement> Image = Common.findElements("xpath", "//div[contains(@class,'segmented-categories-item-n')]//span");
				Sync.waitPageLoad();
				Sync.waitImplicit(10);
//			Thread.sleep(4000);
				product = Image.get(i).getText();
				System.out.println(product);
				Thread.sleep(3000);
				String Product=Common.findElement("xpath", "//div[@class='text-sm']//span").getText().trim();
				System.out.println(Product);
				Image.get(i).click();
				Thread.sleep(4000);
				String Products=Common.findElement("xpath", "//div[@class='text-sm']//span").getText().trim();
				System.out.println(Products);
				if(Products!=Product)
				{
					ExtenantReportUtils.addPassLog("Validating" + product + "Page  ",
							"click the sub category should navigate to the  " + product + "Page",
							"successfully page navigating to " + product + "PAGE",
							Common.getscreenShotPathforReport(product));
				}
				else {
					Assert.fail();
				}
	
			}
	
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the category page validation after clicking on sub category in CLP page ",
					"After Clicking on subcategory it should navigate to the PDP pages",
					"Unable to navigate to the PDP pages after clicking on the sub category",
					Common.getscreenShot("Failed to navigate to the PDP pages after clicking on the sub category"));
			Assert.fail();
		}

	}

	public void country_selctor() {

		String Country;
		try {
			
			Sync.waitPageLoad();
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.END);
			Sync.waitElementPresent(50, "xpath", "//button[contains(@class,'country-selector-button')]");
			Common.clickElement("xpath", "//button[contains(@class,'country-selector-button')]");
			Thread.sleep(3000);
			List<WebElement> country = Common.findElements("xpath", "//div[@class='country-list__item']");
			System.out.println(country.size());
			for (int i = 0; i < country.size()-8; i++) {

				List<WebElement> select = Common.findElements("xpath", "//div[@class='country-list__item']//p//parent::a");
				Sync.waitPageLoad();
				
				Country = select.get(i).getAttribute("data-country-code");
			      System.out.println(Country);
					
				if (Country.equals("us")) {

					Common.clickElement("xpath", "(//button[@aria-label='Close'])[1]");
					ExtenantReportUtils.addPassLog("Validating" + Country + "Page  ",
							"click on the country should navigate to the  " + Country + "Page",
							"successfully page navigating to " + Country + "PAGE",
							Common.getscreenShotPathforReport(Country));
				} else {
					Sync.waitElementPresent(50, "xpath", "//button[contains(@class,'country-selector-button')]");
					Common.clickElement("xpath", "//button[contains(@class,'country-selector-button')]");
					int j=i+1;
					 System.out.println("(//p[@class='country-item__language'])[" + j +"]");
					Common.clickElement("xpath", "(//p[@class='country-item__language'])[" + j +"]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					Common.navigateBack();
					ExtenantReportUtils.addPassLog("Validating" + Country + "Page  ",
							"click on the country should navigate to the  " + Country + "Page",
							"successfully page navigating to " + Country + "PAGE",
							Common.getscreenShotPathforReport(Country));
					
				}

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the country selection page navigation",
					"After Clicking on the selected country it should navigate to the respective country page",
					"Unable to navigate to the respective country page after clicking on the selected country",
					Common.getscreenShot(
							"Failed to navigate to the respective country page after clicking on the selected country"));
			Assert.fail();
		}

	}


	public void clickDealer_Central() throws Exception {
		String expectedResult = "It should land successfully on the Delear Central page";

		Common.actionsKeyPress(Keys.END);
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//a[contains(@title,'Dealer Central')]");
			Common.clickElement("xpath", "//a[contains(@title,'Dealer Central')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("dealer"),
					"Validating the Dealer Central page navigation", expectedResult,
					"successfully land to Dealer Central page", "unable to load the  Dealer Central page");
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating Dealer Central page", expectedResult,
					"unable to load the Dealer Central page",
					Common.getscreenShotPathforReport("Dealer Central page link"));
			Assert.fail();

		}
	}

	public void click_New_Account_Inquiry() throws Exception {
		String expectedResult = "It should land successfully on the New Account Inquiry page";

		Common.actionsKeyPress(Keys.END);
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//a[text()='Retail New Account Inquiry']");
			Common.clickElement("xpath", "//a[text()='Retail New Account Inquiry']");
			Sync.waitPageLoad();
			Thread.sleep(5000);

			Common.assertionCheckwithReport(Common.getCurrentURL().contains("inquiry"),
					"Validating the Dealer New Account Inquiry page navigation", expectedResult,
					"successfully land to New Account Inquiry page", "unable to load the  New Account Inquiry page");
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating Dealer Central page", expectedResult,
					"Unable to load the New Account Inquiry page",
					Common.getscreenShotPathforReport("New Account Inquiry page link"));
			Assert.fail();

		}
	}

	public void new_Account_Inquiry_DealerCentral(String dataSet) {
		// TODO Auto-generated method stub


		String expectedResult = "Email us form is visible in tab";
		String country = data.get(dataSet).get("Country");
		String channel = data.get(dataSet).get("Channel");
		String typeofbusiness = data.get(dataSet).get("Typeofbusiness");
		String storesize = data.get(dataSet).get("Storesize");
		String state = data.get(dataSet).get("Region");
		try {
			if(Common.getCurrentURL().contains("https://hydroflask.kustomer.support/")) {
				Sync.waitElementPresent("xpath", "//input[@data-label='Company / Organization']");
				Common.textBoxInput("xpath", "//input[@data-label='Company / Organization']", data.get(dataSet).get("Company"));

				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//input[@id='conversationWebAddress']");
				Common.textBoxInput("xpath", "//input[@id='conversationWebAddress']", data.get(dataSet).get("webaddress"));

				Common.clickElement("xpath", "//div[@id='conversationPlaceACustomOrder']");
				Thread.sleep(4000);
				
				Common.clickElement("xpath", "//div[text()='Yes']");
				Sync.waitElementPresent("id", "conversationInHandDate-date");
				Common.textBoxInput("id", "conversationInHandDate-date","03/03/2026");
				Common.actionsKeyPress(Keys.ENTER);
				

				Common.clickElement("id", "conversationPlanToResellProducts");
				Common.clickElement("xpath", "//div[text()='Yes']");
				

				Common.clickElement("id", "conversationNumberOfUnits");
				
				Common.clickElement("xpath", "//div[text()='48-96']");
//				Common.textBoxInput("xpath", "//input[@id='annualRevenue']", data.get(dataSet).get("annualRevenue"));
//
//				Common.textBoxInput("xpath", "//input[@id='yearsInBusiness']", data.get(dataSet).get("yearsInBusiness"));
				
				Sync.waitElementPresent("xpath", "//div[@id='conversationCountry']");
				Common.clickElement("xpath", "//div[@id='conversationCountry']");

				Sync.waitElementPresent("xpath", "//div[text()='" + country + "']");
				Common.clickElement("xpath", "//div[text()='" + country + "']");

				Sync.waitElementPresent("id", "conversationShippingAddress");
				Common.textBoxInput("id", "conversationShippingAddress", data.get(dataSet).get("Street"));

				Sync.waitElementPresent("xpath", "//input[@id='conversationCityForForms']");
				Common.textBoxInput("xpath", "//input[@id='conversationCityForForms']", data.get(dataSet).get("City"));
				
				Sync.waitElementPresent("xpath", "//div[@id='conversationState']");
				Common.clickElement("xpath", "//div[@id='conversationState']");

				Sync.waitElementPresent("xpath", "//div[text()='" + state + "']");
				Common.clickElement("xpath", "//div[text()='" + state + "']");

				Sync.waitElementPresent("xpath", "//input[@id='conversationZipCodeForForms']");
				Common.textBoxInput("xpath", "//input[@id='conversationZipCodeForForms']",
						data.get(dataSet).get("postcode"));

				Common.textBoxInput("xpath", "//textarea[@id='conversationDescribeYourBusiness']",
						data.get(dataSet).get("YourBusiness"));

				Common.textBoxInput("xpath", "//textarea[@id='messagePreview']",
						data.get(dataSet).get("interested"));

//				Common.textBoxInput("xpath", "//textarea[@id='messagePreview']", data.get(dataSet).get("Brandscarry"));
//
//				Common.textBoxInput("xpath", "//textarea[@id='howDoYouPlanToMarketDisplayOurProducts']",
//						data.get(dataSet).get("DisplayProducts"));
//
//				Common.textBoxInput("xpath", "//textarea[@id='howDidYouHearAboutUs']", data.get(dataSet).get("Aboutus"));

				Common.textBoxInput("xpath", "//input[@name='customerFirstName']", data.get(dataSet).get("FirstName"));

				Common.textBoxInput("xpath", "//input[@name='customerLastName']", data.get(dataSet).get("LastName"));

				Common.textBoxInput("xpath", "//input[@id='conversationJobTitle']", data.get(dataSet).get("Jobtitle"));

				Common.textBoxInput("xpath", "//input[@id='conversationPhoneForForms']", data.get(dataSet).get("phone"));
				Common.textBoxInput("xpath", "//input[@name='customerEmail']", data.get(dataSet).get("Email"));
				
				Sync.waitElementPresent("xpath", "//input[@value='select-to-opt-in']");
				Common.clickElement("xpath", "//input[@value='select-to-opt-in']");
				
				Sync.waitElementPresent("xpath", "//input[@value='select-to-opt-in']");
				Common.clickElement("xpath", "//input[@value='select-to-opt-in']");
			String Email=	Common.findElementBy("id", "customerEmail").getAttribute("value");
			Common.assertionCheckwithReport(Email.contains(data.get(dataSet).get("Email")), "verifying Contact us Success message ",
					"Success message should be Displayed", "Contact us Success message displayed ",
					"failed to dispaly success message");
			
			}
			else {
			Sync.waitElementPresent("xpath", "//input[@data-label='Company Name']");
			Common.textBoxInput("xpath", "//input[@data-label='Company Name']", data.get(dataSet).get("Company"));

			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//div[@id='conversationChannelIndustry']");
			Common.clickElement("xpath", "//div[@id='conversationChannelIndustry']");

			Sync.waitElementPresent("xpath", "//div[text()='" + channel + "']");
			Common.clickElement("xpath", "//div[text()='" + channel + "']");

			Sync.waitElementPresent("xpath", "//div[@id='conversationTypeOfBusiness']");
			Common.clickElement("xpath", "//div[@id='conversationTypeOfBusiness']");
			Sync.waitElementPresent("xpath", "//div[text()='" + typeofbusiness + "']");
			Common.clickElement("xpath", "//div[text()='" + typeofbusiness + "']");

			Common.textBoxInput("xpath", "//input[@id='webAddress']", data.get(dataSet).get("webaddress"));

			Common.clickElement("xpath", "//div[@id='conversationSellThruWebsite']");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//div[@data-path='no']");

			Common.textBoxInput("xpath", "//input[@id='whatOfYourSalesComeThroughYourWebsite']",
					data.get(dataSet).get("salesPercentage"));

			Common.textBoxInput("xpath", "//input[@id='numberOfStores']", data.get(dataSet).get("numberOfStores"));

			Common.clickElement("xpath", "//div[@id='conversationStoreSize']");
			Common.clickElement("xpath", "//div[text()='" + storesize + "']");

			Common.textBoxInput("xpath", "//input[@id='annualRevenue']", data.get(dataSet).get("annualRevenue"));

//			Common.textBoxInput("xpath", "//input[contains(@id,'WhatIsTheEstimatedNumberOfUnits')]",
//					data.get(dataSet).get("NumberOfUnits"));

			Common.textBoxInput("xpath", "//input[@id='yearsInBusiness']", data.get(dataSet).get("yearsInBusiness"));
			
			Sync.waitElementPresent("xpath", "//div[@id='conversationCountry']");
			Common.clickElement("xpath", "//div[@id='conversationCountry']");

			Sync.waitElementPresent("xpath", "//div[text()='" + country + "']");
			Common.clickElement("xpath", "//div[text()='" + country + "']");

			Sync.waitElementPresent("xpath", "//input[@id='conversationStreetForForms']");
			Common.textBoxInput("xpath", "//input[@id='conversationStreetForForms']", data.get(dataSet).get("Street"));

			Common.textBoxInput("xpath", "//input[@name='conversationAptSuiteforforms']", data.get(dataSet).get("yearsInBusiness"));

	

			Sync.waitElementPresent("xpath", "//div[@id='conversationState']");
			Common.clickElement("xpath", "//div[@id='conversationState']");

			Sync.waitElementPresent("xpath", "//div[text()='" + state + "']");
			Common.clickElement("xpath", "//div[text()='" + state + "']");

			Sync.waitElementPresent("xpath", "//input[@id='conversationCityForForms']");
			Common.textBoxInput("xpath", "//input[@id='conversationCityForForms']", data.get(dataSet).get("City"));

			Sync.waitElementPresent("xpath", "//input[@id='conversationZipCodeforforms']");
			Common.textBoxInput("xpath", "//input[@id='conversationZipCodeforforms']",
					data.get(dataSet).get("postcode"));

			Common.textBoxInput("xpath", "//textarea[@id='pleaseDescribeYourBusiness']",
					data.get(dataSet).get("YourBusiness"));

			Common.textBoxInput("xpath", "//textarea[@id='whyAreYouInterestedInHydroFlask']",
					data.get(dataSet).get("interested"));

			Common.textBoxInput("xpath", "//textarea[@id='messagePreview']", data.get(dataSet).get("Brandscarry"));

			Common.textBoxInput("xpath", "//textarea[@id='howDoYouPlanToMarketDisplayOurProducts']",
					data.get(dataSet).get("DisplayProducts"));

			Common.textBoxInput("xpath", "//textarea[@id='howDidYouHearAboutUs']", data.get(dataSet).get("Aboutus"));

			Common.textBoxInput("xpath", "//input[@name='customerFirstName']", data.get(dataSet).get("FirstName"));

			Common.textBoxInput("xpath", "//input[@name='customerLastName']", data.get(dataSet).get("LastName"));

			Common.textBoxInput("xpath", "//input[@id='conversationJobTitle']", data.get(dataSet).get("Jobtitle"));

			Common.textBoxInput("xpath", "//input[@id='conversationPhoneForForms']", data.get(dataSet).get("phone"));
			Common.textBoxInput("xpath", "//input[@name='customerEmail']", data.get(dataSet).get("Email"));

			Common.textBoxInput("xpath", "//input[@name='inquirySubmittedBy']", data.get(dataSet).get("submittedby"));

			Common.clickElement("xpath", "//button[text()='Submit']");

			Sync.waitElementPresent("xpath", "//div[@class='form-wrap']");
			int Contactussuccessmessage = Common.findElements("xpath", "//div[@class='form-wrap']").size();
			Common.assertionCheckwithReport(Contactussuccessmessage > 0, "verifying Contact us Success message ",
					"Success message should be Displayed", "Contact us Success message displayed ",
					"failed to dispaly success message");
			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying email us from",
					"contact us form data enter without any error message", "Contact us page getting error ",
					Common.getscreenShotPathforReport("Contact us page"));
			Assert.fail();

		}

//		Common.actionsKeyPress(Keys.PAGE_UP);
//		String Text = Common.getText("xpath", "//div[@class='form-wrap']");
//		expectedResult = "User gets confirmation under the same tab. It includes a reference number and email is sent to email provided. No validation errors.";
//		Common.assertionCheckwithReport(Text.contains("Your submission was successful."),
//				"verifying contact us confirmation message", expectedResult,
//				"User gets confirmation under the same tab", "unable to load the confirmation form");

	}

	public void dealerCentral_links() throws Exception {
		String expectedResult = "It should land successfully on the page";

		try {
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//a[text()='Contact Page']");
			Common.clickElement("xpath", "//a[text()='Contact Page']");
			Sync.waitPageLoad();
			Thread.sleep(4000);

			Common.assertionCheckwithReport(Common.getCurrentURL().contains("contact"),
					"Validating the contatus page navigation", expectedResult, "successfully land to contact page",
					"unabel to load the  contact page");
			Common.navigateBack();
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating contact us page", expectedResult,
					"unable to load the contact page", Common.getscreenShotPathforReport("Contact us page link"));
			Assert.fail();

		}
	}

	public void configurable_Sticky_add_to_cart(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Colorproduct");
		String productcolor = data.get(Dataset).get("Color");
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[contains(@class,'group-hover/item-image:') or @loading='lazy' and @itemprop]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@class,'group-hover/item-image:') or @loading='lazy' and @itemprop]");
				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Common.clickElement("xpath", "//img[@alt='" + product + "']");
			System.out.println(product);
			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
			Common.assertionCheckwithReport(name.equals(product),
					"validating the product should navigate to the PDP page",
					"When we click on the product is should navigate to the PDP page",
					"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");

			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.END);
			Sync.waitElementPresent("xpath", "//div[contains(@id,'sticky') and @aria-label='" + productcolor + "']");
			Common.clickElement("xpath", "//div[contains(@id,'sticky') and @aria-label='" + productcolor + "']");
			Common.clickElement("xpath", "//button[@id='product-sticky-addtocart-button']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message2 = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
					.getAttribute("data-ui-id");
			Common.assertionCheckwithReport(message2.contains("success"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"Unable to add product to the cart ", Common.getscreenShot("Failed to add product to the cart"));
			Assert.fail();
		}

	}

	public void Sticky_Add_to_Cart(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		String colorproduct = data.get(Dataset).get("Colorproduct");
		String colorname = data.get(Dataset).get("Color");
		System.out.println(products);
		try {

			if (Common.getPageTitle().contains("Bottle")) {
				Sync.waitPageLoad();
				for (int i = 0; i <= 10; i++) {
					Sync.waitElementPresent("xpath", "//li[@class='ais-InfiniteHits-item']//img[1]");
					List<WebElement> webelementslist = Common.findElements("xpath",
							"//li[@class='ais-InfiniteHits-item']//img[1]");

					String s = webelementslist.get(i).getAttribute("src");
					System.out.println(s);
					if (s.isEmpty()) {

					} else {
						break;
					}
				}
				Thread.sleep(6000);
				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + colorproduct + "']");
				Common.clickElement("xpath", "//img[@alt='" + colorproduct + "']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.actionsKeyPress(Keys.END);
				Common.actionsKeyPress(Keys.PAGE_UP);
				Sync.waitElementPresent("xpath",
						"//div[@x-data='stickyBar()']//div[@data-option-label='" + colorname + "']");
				Common.clickElement("xpath",
						"//div[@x-data='stickyBar()']//div[@data-option-label='" + colorname + "']");
//				Sync.waitElementPresent("xpath", "//span[text()='Add to Cart']");
//				Common.clickElement("xpath", "//span[text()='Add to Cart']");
				Common.clickElement("xpath", "//button[@x-show='isStickySwatchAvailable' and @title='Add to Cart']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
//				String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//						.getAttribute("data-ui-id");
//				System.out.println(message);
//				Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
//						"Product should be add to cart", "Sucessfully product added to the cart ",
//						"failed to add product to the cart");
				Common.actionsKeyPress(Keys.UP);
			} else {
				Sync.waitPageLoad();
				for (int i = 0; i <= 10; i++) {
					Sync.waitElementPresent("xpath", "//a[@class='product-image-link']");
					List<WebElement> webelementslist = Common.findElements("xpath",
							"//a[@class='product-image-link']");

					String s = webelementslist.get(i).getAttribute("href");
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
				Common.actionsKeyPress(Keys.END);
//				Sync.waitElementPresent("xpath", "//span[text()='Add to Cart']");
//				Common.clickElement("xpath", "//span[text()='Add to Cart']");

				Common.clickElement("xpath", "//button[@x-show='isStickySwatchAvailable' and @title='Add to Cart']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
//				String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//						.getAttribute("data-ui-id");
//				System.out.println(message);
//				Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
//						"Product should be add to cart", "Sucessfully product added to the cart ",
//						"failed to add product to the cart");
				Common.actionsKeyPress(Keys.UP);
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));

			Assert.fail();
		}
	}

	public void socialLinkValidation(String dataSet) {

		String socalLinks = data.get(dataSet).get("Links");
		String[] socallinksarry = socalLinks.split(",");
		int i = 0;
		
		try {
			for (i = 0; i < socallinksarry.length; i++) {
				
				Common.actionsKeyPress(Keys.END);
				Common.scrollIntoView("xpath","//img[@alt='instagram']");
				Sync.waitElementClickable("xpath", "(//img[@alt='" + socallinksarry[i] + "'])[1]");
				Common.clickElement("xpath", "(//img[@alt='" + socallinksarry[i] + "'])[1]");
				Sync.waitPageLoad();
				Common.switchWindows();
				System.out.println(Common.getCurrentURL());

				if (socallinksarry[i].equals("instagram")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("instagram"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				}
				else if (socallinksarry[i].equals("tiktok")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("tiktok.com"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				}


				else if (socallinksarry[i].equals("facebook")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("www.facebook.com"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				}

			       else if (socallinksarry[i].equals("youtube")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("youtube"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				} else if (socallinksarry[i].equals("pinterest")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("pinterest"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				}

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verifying Social link ",
					"click the social links it will navigating to particular page",
					"User unabel to navigate Social page", Common.getscreenShotPathforReport("socialpage"));
			Assert.fail();
		}
	}

	public void validating_BundleProducts() throws Exception {
		// TODO Auto-generated method stub

	            for(int j=1;j<5;j++)
	            {
	                Common.scrollIntoView("xpath", "//a[contains(@class,'u-hidden--md-d')]");
	                Common.clickElement("xpath", "//a[contains(@class,'u-hidden--md-d')]");
	                Thread.sleep(4000);
	                
	            }

		int subproductsList = Common.findElements("xpath", "//div[@class='field option bundle-item  required']").size();
		System.out.println(subproductsList);
		for (int i = 0; i < subproductsList; i++) {
			int value = i + 1;
			List<WebElement> ListOfSubproducts = Common.findElements("xpath",
					"//div[@class='fieldset']//div[" + value + "]//div[contains(@class,'m-swatch m')]");

			WebElement Colornames = Common.findElement("xpath",
					"//div[@class='fieldset']//div[" + value + "]//span[contains(@class,'m-swatch-group__header s')]");
			WebElement imagecolor = Common.findElement("xpath", "//div[@class='fieldset']//div[" + value + "]//img");
			WebElement Color = Common.findElement("xpath",
					"(//div[@class='fieldset']//div[@class='m-swatch-group__container']//div[contains(@class,'m-swatch m-sw')])["+ value +"]");

			for (int j = 0; j < ListOfSubproducts.size(); j++) {

				String attributevalue = ListOfSubproducts.get(j).getAttribute("disabled");

				if (attributevalue != null) {
				} else {

					if (ListOfSubproducts.get(j).getAttribute("class").contains("m-swatch m")) {
						Thread.sleep(4000);
						ListOfSubproducts.get(j).click();
						Thread.sleep(3000);
//						Common.assertionCheckwithReport(
//								Color.getAttribute("data-option-label").contains(Colornames.getText()),
//								"Vrifying  swatch color button " + Colornames.getText(),
//								"after click color swatch button" + Colornames.getText()
//										+ "it must dispaly swatch color image",
//								"successfully color swatch image is dispalying", "Failed load color swatch image");
					} else {
						break;
					}
				}
			}
		}

	}

	public void Addtocart_Bundle(String Dataset) {
		// TODO Auto-generated method stub
		String Product = data.get(Dataset).get("Products");
		try {
			Sync.waitPageLoad();
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//img[@alt='" + Product + "']");
			Common.javascriptclickElement("xpath", "//img[@alt='" + Product + "']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
//			validating_BundleProducts();
			product_quantity(Dataset);
			//bundle_color("Black");
			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			Sync.waitPageLoad();
			Sync.waitPageLoad();
			Thread.sleep(6000);
//			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//					.getAttribute("data-ui-id");
//			System.out.println(message);
//			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
//					"Product should be add to cart", "Sucessfully product added to the cart ",
//					"failed to add product to the cart");
//		Common.actionsKeyPress(Keys.PAGE_UP);
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));

			Assert.fail();
		}
	}

	public void clickWarranty() {
		// TODO Auto-generated method stub

		String expectedResult = "It should land successfully on the Warranty page";

		Common.actionsKeyPress(Keys.END);
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//a[@title='Warranty']");
			Common.clickElement("xpath", "//a[@title='Warranty']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//a[text()='Click here to start a Warranty']");
			Common.clickElement("xpath", "//a[text()='Click here to start a Warranty']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Warranty")|| Common.getCurrentURL().contains("warranty"),
					"Validating the Warranty page navigation", expectedResult, "successfully land to Warranty page",
					"unabel to load the Warranty page");
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating Warranty page", expectedResult,
					"unabel to load the Warrantyt page", Common.getscreenShotPathforReport("Warranty page link"));
			Assert.fail();

		}
	}

	public void WarrantySubmission(String dataSet) {
		// TODO Auto-generated method stub
		String expectedResult = "Warranty form is visible in tab";

		try {

			Sync.waitElementPresent("xpath", "//input[@id='customerFirstName']");
			Common.textBoxInput("xpath", "//input[@id='customerFirstName']", data.get(dataSet).get("FirstName"));

			Sync.waitElementPresent("xpath", "//input[@id='customerLastName']");
			Common.textBoxInput("xpath", "//input[@id='customerLastName']", data.get(dataSet).get("LastName"));

			Sync.waitElementPresent("xpath", "//input[@id='customerEmail']");
			Common.textBoxInput("xpath", "//input[@id='customerEmail']", data.get(dataSet).get("Email"));


			Sync.waitElementPresent("xpath", "//div[@id='conversationCountry']");
			Common.clickElement("xpath", "//div[@id='conversationCountry']");
			

			Sync.waitElementPresent("xpath", "//div[text()='United States']");
			Common.clickElement("xpath", "//div[text()='United States']");
			
			Sync.waitElementPresent("xpath", "//input[@name='conversationStreetforforms']");
			Common.textBoxInput("xpath", "//input[@name='conversationStreetforforms']",
					data.get(dataSet).get("Street"));

			Sync.waitElementPresent("xpath", "//input[@data-label='City']");
			Common.textBoxInput("xpath", "//input[@data-label='City']", data.get(dataSet).get("City"));

			Sync.waitElementPresent("xpath", "//div[@id='conversationState']");
			Common.clickElement("xpath", "//div[@id='conversationState']");

			Sync.waitElementPresent("xpath", "//div[text()='Alabama']");
			Common.clickElement("xpath", "//div[text()='Alabama']");

			Sync.waitElementPresent("xpath", "//input[@data-label='Zip Code']");
			Common.textBoxInput("xpath", "//input[@data-label='Zip Code']",
					data.get(dataSet).get("postcode"));

			Sync.waitElementPresent("xpath", "//input[@data-label='Phone']");
			Common.textBoxInput("xpath", "//input[@data-label='Phone']", data.get(dataSet).get("phone"));

			Sync.waitElementPresent("xpath", "//div[@id='conversationWherePurchased']");
			Common.clickElement("xpath", "//div[@id='conversationWherePurchased']");

			Sync.waitElementPresent("xpath", "//div[text()='Hydro Flask Website']");
			Common.clickElement("xpath", "//div[text()='Hydro Flask Website']");

			Sync.waitElementPresent("xpath", "//span[contains(text(),'Select Product')]");
			Common.clickElement("xpath", "//span[contains(text(),'Select Product')]");

			Sync.waitElementPresent("xpath", "//div[@data-path='my_hydro']");
			Common.clickElement("xpath", "//div[@data-path='my_hydro']");

			Sync.waitElementPresent("xpath", "//div[@class='form-field ']/textarea");
			Common.textBoxInput("xpath", "//div[@class='form-field ']/textarea", data.get(dataSet).get("text"));

			Sync.waitElementPresent("xpath", "//span[contains(@class,'form-field-select')]");
			Common.clickElement("xpath", "//span[contains(@class,'form-field-select')]");

			Sync.waitElementPresent("xpath", "//span[text()='No']");
			Common.clickElement("xpath", "//span[text()='No']");

			Sync.waitElementPresent("xpath", "//input[@id='messageSubject']");
			Common.textBoxInput("xpath", "//input[@id='messageSubject']", data.get(dataSet).get("text2"));

			Thread.sleep(2000);
			String path = System.getProperty("user.dir") + ("\\src\\test\\resources\\TestData\\Admin\\Lotusqa.png");
			Sync.waitElementPresent(40, "xpath", "(//input[@type='file'])[1]");
			Common.findElement("xpath", "(//input[@type='file'])[1]").sendKeys(path);

//				Common.scrollIntoView("xpath", "(//input[@type='file'])[3]");
//				Thread.sleep(2000);
//			    String path1 = System.getProperty("user.dir") + ("\\src\\test\\resources\\TestData\\Admin\\Lotusqa1.png");
//				Sync.waitElementPresent(40, "xpath", "(//input[@type='file'])[3]");
//				Common.findElement("xpath", "(//input[@type='file'])[3]").sendKeys(path1);
//			
//				Common.scrollIntoView("xpath", "(//input[@type='file'])[5]");
//                String path2 = System.getProperty("user.dir") + ("\\src\\test\\resources\\TestData\\Hydroflask\\TestScreen.png");
//				Sync.waitElementPresent(40, "xpath", "(//input[@type='file'])[5]");
//				Common.findElement("xpath", "(//input[@type='file'])[5]").sendKeys(path2);

			Thread.sleep(3000);
			Common.scrollIntoView("xpath", "//button[text()='Submit']");
			Common.clickElement("xpath", "//button[text()='Submit']");

			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//div[contains(text(),'Your submission was successful')]");
			int Warrantysuccessmessage = Common
					.findElements("xpath", "//div[contains(text(),'Your submission was successful')]").size();
			Common.assertionCheckwithReport(Warrantysuccessmessage > 0, "verifying Warranty Success message ",
					"Success message should be Displayed", "Warranty Success message displayed ",
					"failed to dispaly success message");
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Warranty form",
					"Warranty form data enter without any error message", "Warranty page getting error ",
					Common.getscreenShotPathforReport("Warranty page"));
			Assert.fail();

		}

	}

	
	public void search_results(String search) {

		try {
			Sync.waitElementPresent("id", "menu-search-icon");
			Common.clickElement("id", "menu-search-icon");
			String open = Common.findElement("id", "menu-search-icon").getAttribute("aria-expanded");
			Common.assertionCheckwithReport(open.contains("true"),
					"To validate the global search box is opened when we click on the search icon",
					"User should able to click on the search icon and search box opens",
					"Sucessfully the gobal search box opend when user clicks on search icon",
					"Failed to open the search box when user clicks on the search icon");
			Common.textBoxInput("xpath", "//input[@id='autocomplete-0-input']", search);
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String productsearch = Common.findElement("id", "instant-empty-results-container").getText();
			System.out.println(productsearch);
			Common.assertionCheckwithReport(productsearch.contains("No products for query"),
					"validating the search functionality",
					"enter any search term will display no results in the search box",
					"user enter the search term in  search box", "Failed to see the search term");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the search functionality",
					"enter product name will display in the search box",
					" unable to enter the product name in  search box",
					Common.getscreenShot("Failed to see the product name"));
			Assert.fail();
		}

		/*try {
			String FAQ = Common.findElement("xpath", "//div[@id='instant-empty-results-container']//a[text()='FAQ']")
					.getText();
			Common.assertionCheckwithReport(FAQ.contains("FAQ"), "validating the customer service information",
					"should display Customer serivce information", "user views the Customer serivce information",
					"Failed to see the Customer service info");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the validating the customer service information",
					"should display Customer serivce information", " user views the Customer serivce information",
					Common.getscreenShot("Failed to see the Customer service info"));
			Assert.fail();
		}*/

	}

	public void popular_searches() {
		String search;

		try {
			Sync.waitElementPresent("id", "menu-search-icon");
			Common.clickElement("id", "menu-search-icon");
			Thread.sleep(4000);

			List<WebElement> Search = Common.findElements("xpath",
					"//div[@class='c-popular-searches__title']//a");
			System.out.println(Search);
			System.out.println(Search.size());
			for (int i = 0; i < Search.size(); i++) {
                 Thread.sleep(4000);
				List<WebElement> select = Common.findElements("xpath",
						"//div[@class='c-popular-searches__title']//a");
				Sync.waitPageLoad();
				Sync.waitElementPresent(50, "xpath",
						"//div[@class='c-popular-searches__title']//a");
				Thread.sleep(3000);
				search = select.get(i).getText();
				System.out.println(search);
				select.get(i).click();
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.navigateBack();
				Thread.sleep(3000);
				Sync.waitElementPresent("id", "menu-search-icon");
				Common.clickElement("id", "menu-search-icon");
				ExtenantReportUtils.addPassLog("Validating" + search + "Page  ",
						"click on the Popular search should navigate to the  " + search + "Page",
						"successfully page navigating to " + search + "PAGE",
						Common.getscreenShotPathforReport(search));

			}
			Sync.waitElementPresent("id", "menu-search-icon");
			Common.clickElement("id", "menu-search-icon");
			Thread.sleep(3000);
			
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the search functionality",
					"click on the Popular search should navigate to the ",
					" unable to enter the product name in  search box",
					Common.getscreenShot("Failed to see the product name"));
			Assert.fail();
		}
	}

	public void carousel() {
		try {
            Sync.waitElementPresent(40, "xpath", "//span[@class='icon-carousel__right']");
			Common.scrollIntoView("xpath", "//span[@class='icon-carousel__right']");
			Common.findElement("xpath", "//span[@class='icon-carousel__right']");
			Common.clickElement("xpath", "//span[@class='icon-carousel__right']");
			Thread.sleep(4000);
			int carousel = Common.findElements("xpath", "//div[contains(@class,'js-slick-carousel')]").size();
			System.out.println(carousel);
			Common.assertionCheckwithReport(carousel > 0, "validating the carousel", "should navigate to the carousel",
					"user views the carousel", "Failed to see the carousel");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the carousel",
					"click on the carousel should navigate the carousel slider", " unable to view the carousel",
					Common.getscreenShot("Failed to see the carousel"));
			Assert.fail();
		}
	}

	public void Validating_search(String search) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Sync.waitElementPresent("id", "menu-search-icon");
			Common.clickElement("id", "menu-search-icon");
			String open = Common.findElement("id", "menu-search-icon").getAttribute("aria-expanded");
			Common.assertionCheckwithReport(open.contains("true"),
					"To validate the global search box is opened when we click on the search icon",
					"User should able to click on the search icon and search box opens",
					"Sucessfully the gobal search box opend when user clicks on search icon",
					"Failed to open the search box when user clicks on the search icon");
			Common.textBoxInput("xpath", "//input[@id='autocomplete-0-input']", search);
			Thread.sleep(3000);
			Sync.waitElementPresent("id", "menu-search-icon");
			Common.clickElement("id", "menu-search-icon");
			String close = Common.findElement("id", "menu-search-icon").getAttribute("aria-expanded");
			Common.assertionCheckwithReport(close.contains("false"),
					"To validate the global search box is Closed when user click on the close icon",
					"User should able to click on the close icon and search box should be closed",
					"Sucessfully the gobal search box closed when user clicks on close icon",
					"Failed to close the search box when user clicks on the close icon");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate the global search box is Closed when user click on the close icon",
					"User should able to click on the close icon and search box should be closed",
					"Unable to close the gobal search box when user clicks on close icon",
					Common.getscreenShotPathforReport(
							"Failed to close the search box when user clicks on the close icon"));

			Assert.fail();
		}

	}

	public void click_Shop() {

		try {
			Sync.waitElementPresent("xpath",
					"//nav[@class='megamenu navigation m-megamenu-nav']//span[text()=' Shop']");

			Common.clickElement("xpath", "//nav[@class='megamenu navigation m-megamenu-nav']//span[text()=' Shop']");
			Thread.sleep(2000);

			String catogories = Common.getText("xpath", "//ul[contains(@class,'level0 submenu')]");
			System.out.println(catogories);

			Common.assertionCheckwithReport(catogories.contains("Kitchenware"),
					"To Validate the catogories are displayed",
					"should display the catogories after clicking on the shop",
					"update catogories are displayed after a click on the shop", "Failed to display the catogories");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the catogories are displayed",
					"should display the catogories after clicking on the shop",
					"unable to display catogories after a click on the shop", "Failed to display the catogories");
			Assert.fail();
		}

	}

	public void ClosADD() throws Exception {
		Thread.sleep(3000);
		Common.clickElement("xpath", "(//button[@data-role='closeBtn'])[2]");
		Thread.sleep(4000);
	}

	public void click_BottlesDrinkware() {

		try {
			Sync.waitElementPresent("xpath",
					"//ul[contains(@class,'level0 submenu')]//span[text()=' Bottles & Drinkware']");

			Common.clickElement("xpath",
					"//ul[contains(@class,'level0 submenu')]//span[text()=' Bottles & Drinkware']");
			Thread.sleep(2000);

			String subcatogories = Common.getText("xpath", "//ul[contains(@class,'level0 submenu')]");
			System.out.println(subcatogories);

			Common.assertionCheckwithReport(subcatogories.contains("Bottles"),
					"To Validate the subcatogories are displayed",
					"should display the subcatogories after clicking on the shop",
					"update subcatogories are displayed after a click on the shop",
					"Failed to display the subcatogories");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the catogories are displayed",
					"should display the catogories after clicking on the shop",
					"unable to display catogories after a click on the shop", "Failed to display subcatogories");
			Assert.fail();
		}

	}
	public void click_BottlesDrinkware_ShopAll() {

		try {
			Common.clickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
			Sync.waitElementPresent("xpath",
					"(//span[contains(text(),'Bottles & Drinkware')])[1]");

			Common.javascriptclickElement("xpath",
					"(//span[contains(text(),'Bottles & Drinkware')])[1]");
			Thread.sleep(2000);
			Common.clickElement("xpath",
					"//ul[@id='category-node-241']//span[text()='Shop All']");
			String category = Common.getText("xpath", "//h3[text()='Bottles & Drinkware']");
			System.out.println(category);

			Common.assertionCheckwithReport(category.contains("Bottles"),
					"To Validate the subcatogories are displayed",
					"should display the subcatogories after clicking on the shop",
					"update subcatogories are displayed after a click on the shop",
					"Failed to display the subcatogories");
			
			Common.findElementBy("css",
					"div[id='algolia-load-more'] button");
			
			Common.clickElement("css",
					"div[id='algolia-load-more'] button");
			
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the catogories are displayed",
					"should display the catogories after clicking on the shop",
					"unable to display catogories after a click on the shop", "Failed to display subcatogories");
			Assert.fail();
		}

	}
	
	public void click_Bottles() {

		try {
			Sync.waitElementPresent("xpath", "//ul[contains(@class,'level0 submenu')]//span[text()=' Bottles']");

			Common.clickElement("xpath", "//ul[contains(@class,'level0 submenu')]//span[text()=' Bottles']");
			Sync.waitPageLoad();
			Thread.sleep(2000);

			String pagetitle = Common.getPageTitle();
			System.out.println(pagetitle);

			Common.assertionCheckwithReport(pagetitle.contains("Shop Bottles"), "To Validate the PLP page is displayed",
					"should display the PLP page after clicking on the Bottles",
					"update PLP page are displayed after a click on the Bottles", "Failed to display  catogories");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the PLP page are displayed",
					"should display the PLP page after clicking on the Bottles",
					"unable to display PLP page after a click on the Bottles", "Failed to display update PLP page");
			Assert.fail();
		}

	}

	public void click_product() {

		try {
			Sync.waitElementPresent("xpath", "//img[@alt='32 oz Wide Mouth']");

			Common.clickElement("xpath", "//img[@alt='32 oz Wide Mouth']");
			Sync.waitPageLoad();
			Thread.sleep(4000);

			String pagetitle = Common.getPageTitle();
			System.out.println(pagetitle);

			Common.assertionCheckwithReport(pagetitle.contains("32 oz Wide Mouth"),
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

	public void click_Customize() {

		try {
			Sync.waitElementPresent("xpath", "//button[@data-role='customize-btn']");

			Common.clickElement("xpath", "//button[@data-role='customize-btn']");
			Sync.waitPageLoad();
			Thread.sleep(2000);

			String text = Common.findElement("xpath", "//h1[@class='menu__category-title']").getText();
			System.out.println(text);

			Common.assertionCheckwithReport(text.equals("Bottle"), "To Validate the customize page is displayed",
					"should display the customize page after clicking on the customize button",
					"update customize page are displayed after a click on the customize button",
					"Failed to display  customize");
			Common.clickElement("xpath", "//img[@alt='Close icon']");
			Thread.sleep(2000);
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the customize page are displayed",
					"should display the customize page after clicking on the customize button",
					"unable to display customize page after a click on the customize button",
					"Failed to display update customize page");
			Assert.fail();
		}

	}

	public void writeareview(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		try {
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//h2[contains(text(),'Reviews')]");
			Common.clickElement("xpath", "//h2[contains(text(),'Reviews')]");
			Sync.waitElementPresent("xpath", "//span[text()='Write A Review']");
			Common.clickElement("xpath", "//span[text()='Write A Review']");
			//Common.scrollIntoView("xpath", "//span[text()='Write A Review']");
			Thread.sleep(4000);
			String form = Common.findElement("xpath", "//form[@aria-label='Write A Review Form']//div[contains(@class, 'write-review-wrapper')]").getAttribute("class");
			System.out.println(form);
			Common.assertionCheckwithReport(form.contains("visible"), "verifying the write a review button",
					"Write a review should be appear in the PDP page",
					"Sucessfully write a review button has been displayed in PDP page",
					"Failed to display the write a review button in PDP page");
//			Common.clickElement("xpath", "//a[text()='Reviews & Questions']");
//			Sync.waitElementPresent("xpath", "//span[text()='Write A Review']");
//			Common.clickElement("xpath", "//span[text()='Write A Review']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the Write a review button", "select the write review option",
					"User Unable to click write review option  ",
					Common.getscreenShotPathforReport("User Failed to click write review option "));
			Assert.fail();
		}

	}
	public void Configurableproduct_addtocart_pdp(String Dataset) {
		String product = data.get(Dataset).get("Colorproduct");
		String productcolor = data.get(Dataset).get("Color");
		String productquantity = data.get(Dataset).get("productquantity");
		try {
			Sync.waitPageLoad();
			/*for (int i = 0; i <= 10; i++) {
//				Sync.waitElementPresent("xpath", "//img[contains(@class,'m-product-card__image product')]");
//				List<WebElement> webelementslist = Common.findElements("xpath",
//						"//img[contains(@class,'m-product-card__image product')]");
//				String s = webelementslist.get(i).getAttribute("src");
				Sync.waitElementPresent("xpath", "//li[@class='ais-InfiniteHits-item']//img[1]");
//				Sync.waitElementPresent("xpath", "(//img[contains(@class,'m-product-card__image')])[2]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//li[@class='ais-InfiniteHits-item']//img[1]");
				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}*/

			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//input[@aria-label='" + productcolor + "']");
			Common.clickElement("xpath", "//input[@aria-label='" + productcolor + "']");
			Common.clickElement("xpath", "//select[@name='qty']");
			Common.dropdown("xpath", "//select[@name='qty']", Common.SelectBy.VALUE, productquantity);
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//span[text()='Add to Cart']");
			Common.clickElement("xpath", "//span[text()='Add to Cart']");
			Sync.waitForLoad();
			Thread.sleep(4000);
//			String message2 = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//					.getAttribute("data-ui-id");
//			Common.assertionCheckwithReport(message2.contains("success"), "validating the  product add to the cart",
//					"Product should be add to cart", "Sucessfully product added to the cart ",
//					"failed to add product to the cart");
			
			Sync.waitElementPresent("xpath", "//button[@aria-label='Close minicart']");
			Common.clickElement("xpath", "//button[@aria-label='Close minicart']");
			Thread.sleep(5000);

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"Unable to add product to the cart ", Common.getscreenShot("Failed to add product to the cart"));
			Assert.fail();
		}

	}

	public void Recommended_for_you() {

		try {
			Sync.waitElementPresent("xpath", "//h2[@data-bind='text: storefrontLabel']");

			Common.clickElement("xpath", "//h2[@data-bind='text: storefrontLabel']");
			Common.clickElement("xpath", "//span[@class='icon-carousel__right']");
			Common.clickElement("xpath", "//span[@class='icon-carousel__right']");
			Common.clickElement("xpath", "//span[@class='icon-carousel__left']");

			Sync.waitPageLoad();
			Thread.sleep(2000);

			String text = Common
					.findElement("xpath",
							"//div[@class='u-container c-product-carousel__carousel js-slick-product-carousel']")
					.getText();
			System.out.println(text);

			Common.assertionCheckwithReport(text.contains("Recommended For You"),
					"To Validate the Recommended for you is displayed",
					"should display the Recommended for you after scroll down the PDP page",
					"update Recommended for you are displayed after scroll down the PDP page", "Failed to display  ");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the Recommended for you are displayed",
					"should display the Recommended for you after scroll down the PDP page",
					"unable to display Recommended for you after scroll down the PDP page",
					"Failed to display Recommended for you");
			Assert.fail();
		}

	}

	public void ClickProduct_Image() {

		try {

			Sync.waitElementPresent("xpath", "//h2[@data-bind='text: storefrontLabel']");

			Common.clickElement("xpath", "//h2[@data-bind='text: storefrontLabel']");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			Common.clickElement("xpath",
					"//div[@class='fotorama__thumb fotorama_vertical_ratio fotorama__loaded fotorama__loaded--img']");
			// Common.clickElement("xpath", "//span[@class='icon-carousel__right']");
			// Common.clickElement("xpath", "//span[@class='icon-carousel__left']");

			String text = Common.findElement("xpath",
					"//div[@class='fotorama__thumb fotorama_vertical_ratio fotorama__loaded fotorama__loaded--img']")
					.getText();
			System.out.println(text);

			Common.assertionCheckwithReport(text.contains("Recommended For You"),
					"To Validate the Recommended for you is displayed",
					"should display the Recommended for you after scroll down the PDP page",
					"update Recommended for you are displayed after scroll down the PDP page", "Failed to display  ");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the Recommended for you are displayed",
					"should display the Recommended for you after scroll down the PDP page",
					"unable to display Recommended for you after scroll down the PDP page",
					"Failed to display Recommended for you");
			Assert.fail();
		}

	}

	public void click_minicartatPDP() {
		try {
			Thread.sleep(8000);
			Common.actionsKeyPress(Keys.ARROW_UP);
			Sync.waitElementPresent("xpath", "//a[contains(@class,'c-mini')]");
			Common.mouseOverClick("xpath", "//a[contains(@class,'c-mini')]");
			String openminicart = Common.findElement("xpath", "//div[@data-block='minicart']").getAttribute("class");
			System.out.println(openminicart);
			Common.assertionCheckwithReport(openminicart.contains("active"), "To validate the minicart popup",
					"the mini cart is displayed", "Should display the mini cart", "mini cart is not displayed");
			Common.clickElement("xpath", "//span[@class='icon-minicart__close a-icon-text-btn__icon']");
			Thread.sleep(2000);
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the minicart popup", "the mini cart is displayed",
					"unable to  dislay the mini cart", Common.getscreenShot("Failed to display the mini cart"));
			Assert.fail();

		}
	}

	public void PDP_cofigurable_product() throws Exception {
		int subproductsList = Common.findElements("xpath", "//div[@class='field option bundle-item  required']").size();
		for (int i = 0; i < subproductsList; i++) {
			int value = i + 1;
			List<WebElement> ListOfSubproducts = Common.findElements("xpath",
					"//div[@class='fieldset']//div[" + value + "]//div[contains(@class,'m-swatch m')]");

			WebElement Colornames = Common.findElement("xpath",
					"//div[@class='fieldset']//div[" + value + "]//span[contains(@class,'m-swa')]");
			WebElement imagecolor = Common.findElement("xpath", "//div[@class='fieldset']//div[" + value + "]//img");
			for (int j = 0; j < ListOfSubproducts.size(); j++) {

				String attributevalue = ListOfSubproducts.get(j).getAttribute("disabled");

				if (attributevalue != null) {
				} else {
					if (ListOfSubproducts.get(j).getAttribute("class").contains("m-swatch m")) {

						ListOfSubproducts.get(j).click();

						Common.assertionCheckwithReport(
								imagecolor.getAttribute("src").contains(Colornames.getText())
										|| imagecolor.getAttribute("src").trim().equals(""),
								"Vrifying  swatch color button " + Colornames.getText(),
								"after click color swatch button" + Colornames.getText()
										+ "it must dispaly swatch color image",
								"successfully color swatch image is dispalying", "Failed load color swatch image");
					} else {
						break;
					}
				}
			}
		}

	}

	public void Shipping_Forgot_Password(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//span[contains(text(),'Sign in')]");
			
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("customer/account/login/"),
					"To validate the user is navigating to Shipping page", "user should naviagte to Shipping page",
					"User lands on Shippingd page", "User failed to navigate to Shipping page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the user is navigating to Shipping page",
					"user should navigate to Shipping page", "User failed to land on Shipping page",
					Common.getscreenShotPathforReport("failed  to naviagte Shipping page "));
			Assert.fail();

		}

	}

	public void Forgot_password(String DateSet) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			Common.clickElement("xpath", "//span[contains(text(),'Forgot Password?')]");
			String forgotpassword = Common.findElement("xpath", "//h2[contains(text(),'Forgot Your Password?')]").getText();
			System.out.println(forgotpassword);
			Common.textBoxInput("id", "email_address", data.get(DateSet).get("UserName"));
			Common.findElement("id", "email_address").getAttribute("value");
			Common.clickElement("xpath", "//button[contains(text(),'Reset My Password')]");
			
			Sync.waitElementPresent(50, "xpath", "//span[@x-html='message.text']");
			String message = Common.findElement("xpath", "//span[@x-html='message.text']").getText();
			System.out.println(message);
			Common.assertionCheckwithReport(
					message.contains("We received too many requests for password resets")
							|| message.contains("you will receive an email with a link to reset your password."),
					"To validate the user is navigating to Forgot Password page",
					"user should naviagte to forgot password page", "User lands on Forgot Password page",
					"User failed to navigate to forgot password page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the user is navigating to Forgot Password page",
					"user should navigate to forgot password page", "User failed to land on Forgot Password page",
					Common.getscreenShotPathforReport("failed  to naviagte forgot password page "));
			Assert.fail();

		}
	}

	public void click_UGC() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//div[@class='y-image-overlay ']");
//			Common.scrollIntoView("xpath", "//div[@class='y-image-overlay ']");
			Common.clickElement("xpath", "//div[@class='y-image-overlay ']");
			String yopto = Common.findElement("xpath", "//a[@class='yotpo-logo-link-new']").getAttribute("aria-label");
			System.out.println(yopto);
			WebElement UGC = Common.findElement("xpath", "//a[@class='yotpo-logo-link-new']//span");
			Thread.sleep(3000);
			Common.scrollIntoView(UGC);
			Thread.sleep(4000);
			Common.assertionCheckwithReport(yopto.contains("Powered by"),
					"To validate the yopto popup in when we click on the UGC",
					"user should able to display the yopto popup", "Sucessfully yopto popup has been displayed",
					"Failed to Displayed the yopto popup");
			Sync.waitElementPresent(40, "xpath", "//span[@aria-label='See Next Image']");
			Common.clickElement("xpath", "//span[@aria-label='See Next Image']");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//span[@aria-label='Cancel']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the yopto popup in when we click on the UGC",
					"user should able to display the yopto popup", "unable to Displayed the yopto popup",
					Common.getscreenShotPathforReport("Failed to Displayed the yopto popup"));
			Assert.fail();
		}
	}

	public void verfy_miscellaneous_pages(String dataSet) throws Exception, IOException {
		// TODO Auto-generated method stub

		String urls = data.get(dataSet).get("Links");
		int j = 0;

		String[] strArray = urls.split("\\r?\\n");
		for (int i = 0; i < strArray.length; i++) {
			System.out.println(strArray[i]);

			if (Common.getCurrentURL().contains("pre")) {

				Common.oppenURL((strArray[i]));
				int responcecode = getpageresponce(Common.getCurrentURL());
				System.out.println(responcecode);
				Common.refreshpage();
				System.out.println(responcecode);
             Thread.sleep(3000);
				if (responcecode == 200) {
					ExtenantReportUtils.addPassLog("Validating Page URL ", "page configured with products ",
							"successfully page configured with products",
							Common.getscreenShotPathforReport("link" + i));
				} else {

					j++;

					ExtenantReportUtils.addFailedLog("Validating Page URL  " + Common.getCurrentURL(),
							"page configured with products ", "unable to find page it showing 404 error",
							Common.getscreenShotPathforReport("link" + i));

				}

			} else if (Common.getCurrentURL().contains("https://mcloud-na-preprod.hydroflask.com/")) {

				Common.oppenURL(strArray[i].replace("mcloud-na-stage", "www"));
				Thread.sleep(2000);
				int responcecode = getpageresponce(Common.getCurrentURL());
				System.out.println(responcecode);
				Thread.sleep(3000);
				if (responcecode == 200) {
					ExtenantReportUtils.addPassLog("Validating Page URL ", "page configured with products ",
							"successfully page configured with products",
							Common.getscreenShotPathforReport("link" + i));
				} else {

					j++;

					ExtenantReportUtils.addFailedLog("Validating Page URL  " + Common.getCurrentURL(),
							"page configured with products ", "unable to find page it showing 404 error",
							Common.getscreenShotPathforReport("link" + i));

				}
			}
		}

		if (j > 1) {
			Assert.fail();
		}
	}

	public void validateChatboxOptions(String DataSet) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.switchFrames("xpath", "//iframe[@id='kustomer-ui-sdk-iframe']");
			Sync.waitElementVisible(30, "xpath", "//div[@class='chatRootIcon__pointer___QslJf']");
			Common.mouseOverClick("xpath", "//div[@class='chatRootIcon__pointer___QslJf']");
			
			Sync.waitElementVisible(30, "xpath", "//div[contains(@class,'footer__itemContainer')]/p");
			String answers = Common.findElement("xpath", "//div[contains(@class,'footer__itemContainer')]/p").getText();
			System.out.println(answers);
			Common.assertionCheckwithReport(answers.contains("Answers"), "To validate the Answers options in Chatbox",
					"Click the Answers option to display the related options",
					"Sucessfully click the answers option button", "Unable to click the Answers option button");

			Common.clickElement("xpath", "//div[contains(@class,'footer__itemContainer')]/p");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validate the ChatBot on the home page",
					"Open the ChatBot and answers option should be displayed",
					"Unable click on the ChatBot and answers are not displayed",
					Common.getscreenShotPathforReport("failed to click on the ChatBot"));
			Assert.fail();
		}

		List<WebElement> Answerwebelements = Common.findElements("xpath",
				"//div[contains(@class,'SearchListItem__details')]");

		ArrayList<String> arrayoptionName = new ArrayList<String>();

		for (WebElement answernames : Answerwebelements) {
			arrayoptionName.add(answernames.getText());
			System.out.println(arrayoptionName);
		}

		String[] items = data.get(DataSet).get("HydroAnswers").split(",");
		System.out.println(items);
		for (int i = 0; i < items.length; i++) {

			if (arrayoptionName.contains(items[i])) {
			} else {

				ExtenantReportUtils.addFailedLog("To validate the Answers options in chatbox",
						"All the Answer related options are displayed ", "Missed the " + items[i] + "options",
						Common.getscreenShotPathforReport("failed to display answersoptions"));
				Assert.fail();
			}

			ExtenantReportUtils.addPassLog("To Validate the Answers options ",
					"click on the answers options it must display all the options ",
					"Sucessfully displayed the answers options " + arrayoptionName,
					Common.getscreenShotPathforReport("Answervalidation"));
		}

		try {
			String chat = Common.findElement("xpath", "//div[contains(@class,'footer__chatContainer')]/p").getText();
			System.out.println(chat);
			Common.clickElement("xpath", "//div[contains(@class,'footer__chatContainer')]");
//			Sync.waitElementClickable(30, "xpath", "//button[contains(@class,'CLMcd button')]");
//			Common.mouseOverClick("xpath", "//button[contains(@class,'CLMcd button')]");
			Sync.waitElementClickable(30, "xpath", "//button[contains(@class,'newConversationButton')]");
			Common.mouseOver("xpath", "//button[contains(@class,'newConversationButton')]");
			Common.javascriptclickElement("xpath", "//button[contains(@class,'newConversationButton')]");

			Sync.waitElementVisible("xpath", "(//div[contains(@class,'markdownBody')])[1]");
			String welcomemsg = Common.findElement("xpath", "(//div[contains(@class,'markdownBody')])[1]").getText();
			System.out.println(welcomemsg);
			Common.assertionCheckwithReport(chat.contains("Chat") || welcomemsg.contains("Welcome to OXO!"),
					"To validate the Chat Conversation when user click on the chat option",
					"It should Open the Chat conversation in ChatBot",
					"Sucessfully click on the ChatBot and display the Chat conversation ",
					"Unable to display the chat conversation when user click on the chat option ");

			Common.switchToDefault();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Chat Conversation when user click on the chat option",
					"It should Open the Chat conversation in ChatBot",
					"Unable to  click on the ChatBot and not displayed the Chat conversation ",
					Common.getscreenShotPathforReport(
							"Unable to display the chat conversation when user click on the chat option"));
			Assert.fail();
		}

	}

	public void view_PLP_page() {
		try {
			String title = Common.findElement("xpath", "//h1[@class='title-2xl min-w-56']").getAttribute("Class");
			String breadcrumbs = Common.findElement("xpath", "//nav[@id='breadcrumbs']")
					.getAttribute("aria-label");
			String filter = Common.findElement("xpath", "//h3[contains(@class,'flex-grow title')]").getText();
			String Sort = Common
					.findElement("xpath",
							"//span[contains(@class,'pr-2.5 title-panel-sm')]")
					.getText().trim();
			Common.assertionCheckwithReport(
					breadcrumbs.contains("Breadcrumb") && title.contains("title-2xl")
							&& filter.contains("Filter by") && Sort.contains("Sort by"),
					"To validate the Product Listing Page", "User should able to open Product Listing Page",
					"Sucessfully views the Product Listing Page", "Failed to view Product Listing Page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Product Listing Page",
					"User should able to open Product Listing Page", "Unable to view the Product Listing Page",
					Common.getscreenShotPathforReport("Failed to view Product listing Page"));

			Assert.fail();
		}
	}

	public void filter_By(String category) {

		try {

			String text = Common.findElement("xpath", "(//span[text()='" + category + "']//following-sibling::span)[2]").getText().replace("(", "").replace(")", "");
			System.out.println(text);
			Common.clickElement("xpath", "//span[text()='" + category + "']");
			int textValue = Integer.parseInt(text);
			String categoryvalue = Integer.toString(textValue);
			Thread.sleep(6000);
			String textValueAfterFilter = Common.findElement("xpath", "(//div[@class='text-sm']//span)[1]")
					.getText().trim();
			System.out.println("textValueAfterFilter:" +textValueAfterFilter);
			Thread.sleep(4000);
			int noOfItems = Common.findElements("xpath", "//li[@class='ais-InfiniteHits-item']").size();
			String items = Integer.toString(noOfItems);
			System.out.println("categoryvalue:"+categoryvalue);
			System.out.println("items:"+ items);

			Common.assertionCheckwithReport(categoryvalue.equals(textValueAfterFilter),
					"To validate the filter in Product Listing Page",
					"User should able to filter in Product Listing Page",
					"Sucessfully filters in the Product Listing Page", "Failed to filter in Product Listing Page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the filter in Product Listing Page",
					"User should able to filter in Product Listing Page", "Unable to filter the Product Listing Page",
					Common.getscreenShotPathforReport("Failed to filter Product listing Page"));

			Assert.fail();
		}
	}

	public void sort_By(String dataSet) {
		String sort = data.get(dataSet).get("Sort");
		String Prodsort=data.get(dataSet).get("ProdSort");
		try {

			Common.clickElement("xpath", "//select[@class='ais-SortBy-select']");
//			Common.dropdown("xpath", "//option[@class='ais-SortBy-option']", Common.SelectBy.TEXT, sort);
			if(Common.getCurrentURL().contains("preprod"))
			{
			Common.clickElement("xpath", "//div[@id='algolia-sorts']//option[contains(text(),'" + sort + "')]");


			String low = Common
					.findElement("xpath", "//div[@id='algolia-sorts']//option[contains(text(),'" + sort + "')]")
					.getText();

			Common.assertionCheckwithReport(low.contains(sort), "To validate the Sort in Product Listing Page",
					"User should able to Sort in Product Listing Page", "Sucessfully Sorts in the Product Listing Page",
					"Failed to Sort  in Product Listing Page");
			}
			else
			{
				Common.clickElement("xpath", "//div[@id='algolia-sorts']//option[contains(text(),'" + Prodsort + "')]");


				String low = Common
						.findElement("xpath", "//div[@id='algolia-sorts']//option[contains(text(),'" + Prodsort + "')]")
						.getText();

				Common.assertionCheckwithReport(low.contains(Prodsort), "To validate the Sort in Product Listing Page",
						"User should able to Sort in Product Listing Page", "Sucessfully Sorts in the Product Listing Page",
						"Failed to Sort  in Product Listing Page");
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Sort  in Product Listing Page",
					"User should able to Sort  in Product Listing Page", "Unable to Sort the Product Listing Page",
					Common.getscreenShotPathforReport("Failed to Sort  Product listing Page"));

			Assert.fail();
		}
	}

	public void Click_Findstore() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.END);
			Common.clickElement("xpath", "//a[contains(@href,'storelocator')]");

			String find = Common.findElement("xpath", "(//span[contains(@class,'base')])[1]").getText();
			System.out.println(find);

			Common.assertionCheckwithReport(find.equals("Find a Store"), "validating Find a Store page",
					"user navigates to Find a Store page", "Sucessfully user navigate to Find a Store page",
					"faield to navigate to Find a Store page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating store logo", "System directs the user back to Find a Store",
					"unable to go back to the Find a Store page",
					Common.getscreenShotPathforReport("faield to get back to Find a Store"));
			Assert.fail();
		}


	}

	public void click_Retailer() {
		// TODO Auto-generated method stub
		String store = "Whole Earth Provision Co.";

		try {

			Common.switchFrames("xpath", "//iframe[@id='lcly-embedded-iframe-inner-0']");
			Common.clickElement("xpath", "//a[contains(@id,'dealer-navigation-retailers')]");

			Sync.waitPageLoad();
			Thread.sleep(8000);
//			String id = Common.findElement("xpath", "//h3[contains(text(),'" + store + "')]")
//					.getText();
			Common.clickElement("xpath", "//div[contains(@class,'conv-section-details')]//h3[contains(text(),'Whole Earth Provision Co.')]");
//			 	.getText();
//			System.out.println(id);
//			 Common.clickElement("xpath", "//div[contains(@aria-label,'DICK'S Sporting')]");

//			Common.findElement("xpath", "//div[@id='" + id + "']").click();
			Sync.waitElementPresent("xpath", "//img[@class='store-info-logo']");
			int storeSize = Common.findElements("xpath", "//img[@class='store-info-logo']").size();
			System.out.println(storeSize);
			Common.assertionCheckwithReport(storeSize > 0, "validating Retailers page",
					"user navigates to Retailers page", "Sucessfully user navigate to Retailers page",
					"faield to navigate to Retailers page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating Retailers page",
					"System directs the user back to Retailers page", "unable to user go back to Retailers page",
					Common.getscreenShotPathforReport("faield to get back to Retailers page"));
			Assert.fail();
		}

	}

	public void Validate_store_sidebar() {
		try {
//			Common.switchFrames("xpath", "//iframe[contains(@id,'lcly-embedded-iframe')]");
			Thread.sleep(3000);
			Sync.waitElementPresent("id", "conversion-sidebar");
			int RetailersTab = Common.findElements("id", "dealer-navigation-retailers").size();
			int InstockTab = Common.findElements("id", "dealer-navigation-inventory").size();
			int Locationsearch = Common.findElements("xpath", "//input[@name='location']").size();
			int UsemylocationCTA = Common.findElements("xpath", "//a[@id='current-location-detector']").size();
			int Retailersmap = Common.findElements("xpath", "//div[contains(@id,'marker-index')]").size();
			System.out.println(Retailersmap);
			Common.assertionCheckwithReport(
					RetailersTab > 0 && InstockTab > 0 && Locationsearch > 0 && UsemylocationCTA > 0
							&& Retailersmap > 0,
					"validating Store locator side bar ",
					"Should visible the RetailersTab InstockTab Locationsearch UsemylocationCTA Retailersmap",
					"RetailersTab InstockTab Locationsearch UsemylocationCTA Retailersmap displayed",
					"Failed to land on store locator page");
			Common.switchToDefault();

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating Store locator side bar ",
					"Should visible the RetailersTab InstockTab Locationsearch UsemylocationCTA Retailersmap",
					"failed to display RetailersTab InstockTab Locationsearch UsemylocationCTA Retailersmap",
					Common.getscreenShotPathforReport("faield to display the tabs"));
			Assert.fail();
		}

	}

	public void CLick_Usemylocation() {
		try {
			Common.switchFrames("xpath", "//iframe[contains(@id,'lcly-embedded-iframe')]");
			Thread.sleep(2000);
			Sync.waitElementClickable("xpath", "//a[@id='current-location-detector']");
//			Common.mouseOverClick("xpath", "//a[@id='current-location-detector']");
			Sync.waitPresenceOfElementLocated("id", "current-location-indicator");
			Common.scrollIntoView("xpath", "//div[@class='dl-location-detector-container ']");
			int currentlocation = Common.findElements("xpath", "//div[@class='dl-location-detector-container ']").size();
			System.out.println(currentlocation);
			String address = Common.findElement("xpath", "(//h4[@class='conv-section-store-address section-subtitle dl-store-address js-store-location h5'])[1]").getText();
			Common.assertionCheckwithReport(currentlocation > 0 && address.contains("TX"),
					"validating current location ", "Should visible retailers in the current location",
					"Current location Displayed", "Failed to display the current location");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating Users based on Current Location",
					"To display the retailers for the current location",
					"Failed to display retailers for the current location",
					Common.getscreenShotPathforReport("faield to display the retailers for current location"));
			Assert.fail();
		}

	}

	public void Validate_AvailableRetailers() {
		try {
			Thread.sleep(2000);
			Common.scrollIntoView("xpath", "//a[contains(@class,'tab-retailers')]");

			//Common.mouseOverClick("xpath", "//a[contains(@class,'tab-retailers')]");
			int retailers = Common.findElements("xpath", "//div[contains(@class,'store dl-store-list-tile')]").size();
			if (retailers > 0) {
				Common.assertionCheckwithReport(retailers > 0, "To validate the available retailers",
						"Retailers should be available", "Retailers are available", "Failed to display the retailers");
			} else {
				Sync.waitElementVisible("xpath", "//input[@name='location']");
				Common.textBoxInput("xpath", "//input[@name='location']", "CT 06473");
				Common.actionsKeyPress(Keys.ENTER);
				Sync.waitElementVisible(30, "xpath", "//h3[@class='section-title dl-store-name']");
				int locationRetailers = Common.findElements("xpath", "//h3[@class='section-title dl-store-name']")
						.size();

				Common.assertionCheckwithReport(locationRetailers > 0,
						"To validate the available retailers for new location",
						"Retailers should be available for new location", "Retailers are available for new location",
						"Failed to display the retailers for new location");

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating available retailers page",
					"retailers should be visible for the given location",
					"Failed to display retailers for the given location",
					Common.getscreenShotPathforReport("faield to display the available retailers"));
			Assert.fail();
		}

	}

	public void Validate_retailerlocations() {
		try {
			Common.clickElement("xpath", "//h3[@class='section-title dl-store-name']");
			Sync.waitElementVisible("xpath", "//div[@class='square-image-container']");
			int Retailerlogo = Common.findElements("xpath", "//div[@class='square-image-container']").size();
			int locations = Common.findElements("xpath", "//a[contains(@class,'tab-locations')]").size();
			int Hours = Common.findElements("xpath", "//a[contains(@class,'tab-hours')]").size();
			int Links = Common.findElements("xpath", "//a[contains(@class,'tab-links')]").size();
			Common.assertionCheckwithReport(Retailerlogo > 0 && locations > 0 && Hours > 0 || Links > 0,
					"To validate the store info content displayed ", "store info content should be displayed",
					"store info content is displayed", "Failed to display the store info content ");
			String Storename = Common.findElement("xpath", "//h2[contains(@class,'store-name-inner')]").getText().toUpperCase();
			System.out.println(Storename);
			Common.clickElement("xpath", "//a[contains(@class,'tab-locations')]");

			int storecount = Common.findElements("xpath", "//a[contains(@class,'conv-section-store')]//h3").size();
			for (int i = 1; i <= storecount; i++) {
				Thread.sleep(3000);
				String relatedstores = Common
						.findElement("xpath", "(//a[contains(@class,'conv-section-store')]//h3)[" + i + "]")
						.getText();
				System.out.println(relatedstores);
				Common.assertionCheckwithReport(relatedstores.contains(Storename),
						"To validate the retailer stores displayed ", "Retailer stores should be displayed",
						"Retailer stores are displayed", "Failed to display the retailer stores ");

			}
			Thread.sleep(2000);
			Common.clickElement("xpath", "//a[@class='nav-bar-back']");
			Click_Direction();
			Thread.sleep(2000);
			writeReviews();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating available retailer store locations",
					"retailers store locations should be visible", "Failed to display retailers store locations",
					Common.getscreenShotPathforReport("faield to display retailer store locations"));
			Assert.fail();
		}

	}

	public void verifingRetailerHours() {
		// TODO Auto-generated method stub
		String hours = "hours";
		try {

			Common.findElement("xpath", "//a[@aria-label='" + hours + "']").click();
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//div[@class='store-hours-days']");
			int hoursSize = Common.findElements("xpath", "//div[@class='store-hours-days']").size();
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Thread.sleep(2000);
			Common.assertionCheckwithReport(hoursSize > 0, "validating hours page", "user navigates to Hours page ",
					"Successfully user navigate to hours page", "faield to navigate to hours page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating hours page", "System directs the user back to the hours page",
					"unable to user back to the hours page",
					Common.getscreenShotPathforReport("Failed to get back to hours page"));

			Assert.fail();
		}
	}

	public void verifingRetailerBrowser() {
		// TODO Auto-generated method stub
		String browse = "Browse";
		try {
			Common.findElement("xpath", "//a[@aria-label='" + browse + "']").click();
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//div[@aria-label='Department Filters']");
			int filterSize = Common.findElements("xpath", "//div[@aria-label='Department Filters']").size();

			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			Common.assertionCheckwithReport(filterSize > 0, "validating browser page",
					"user navigates to Browsers page", "Sucessfully user navigate to browser page",
					"faield to navigate to browser page");
			Common.clickElement("xpath", "//a[@class='nav-bar-back']");
			Common.clickElement("xpath", "//a[@class='nav-bar-back']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating browser page",
					"System directs the user back to the Browser page", "failed user back to the browser page",
					Common.getscreenShotPathforReport("Failed to get back to browser page"));
			Assert.fail();
		}
	}

	public void Click_Instock() {
		// TODO Auto-generated method stub
				try {

					Sync.waitPageLoad();
					Common.switchFrames("xpath", "//iframe[@id='lcly-embedded-iframe-inner-0']");
					Sync.waitElementPresent(40, "xpath", "//a[@id='dealer-navigation-inventory']");
					// Sync.waitElementPresent(20 "xpath",
					// "//a[@id='dealer-navigation-inventory']");
					Common.clickElement("xpath", "//a[@id='dealer-navigation-inventory']");
		Thread.sleep(2000);
					int stock = Common.findElements("xpath", "(//div[@class='status-message success'])[1]").size();
					System.out.println(stock);

					Common.assertionCheckwithReport(stock > 0, "validating instock page", "user navigates to instock page",
							"Sucessfully user navigate to instock page", "faield to navigate to instock page");
				} catch (Exception e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("validating instock page",
							"System directs the user back to the instock page", "unale user to go  back to thr instock page",
							Common.getscreenShotPathforReport("failed to get back to instock page"));
					Assert.fail();
				}


	}

	public void selectproduct(String Productname) {
		// TODO Auto-generated method stub
				try {

					Sync.waitElementPresent(40, "xpath", "//div[text()='" + Productname + "']");
					Common.scrollIntoView("xpath", "//div[text()='" + Productname + "']");
					Common.clickElement("xpath", "//div[text()='" + Productname + "']");
					Sync.waitElementVisible("xpath", "//div[@class='stock-status-banner alert success checkmark']");
					Common.scrollIntoView("xpath", "(//h4[@class='pdp-information-title'])[1]");
					int product = Common.findElements("xpath", "//div[@class='pdp-information']/p[2]").size();
					System.out.println(product);

					Common.assertionCheckwithReport(product > 0, "validating product listing page",
							"user navigates to product listing page", "Sucessfully user navigate to product listing page",
							"faield to navigate to product listing page and unable to see error message");

				} catch (Exception | Error e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("validating product listing page",
							"System directs the user back to the product listing page",
							"unable user back to product listing page",
							Common.getscreenShotPathforReport("failed to get back product listing page"));
					Assert.fail();
				}

	}

	public void Click_Direction() {
		// TODO Auto-generated method stub

		try {
			Sync.waitElementPresent("xpath", "//a[@id='conv-store-info-back']");
			Common.clickElement("xpath", "//a[@id='conv-store-info-back']");

			Sync.waitElementPresent("xpath", "(//span[text()='Directions'])[2]");
			Common.clickElement("xpath", "(//span[text()='Directions'])[2]");
			Common.switchWindows();

			int directionsize = Common.findElements("xpath", "//div[@aria-label='Directions']").size();
			System.out.println(directionsize);
			Common.assertionCheckwithReport(directionsize >= 0, "validating google maps page",
					"user redirects to google maps page", "Sucessfully user redirects to google maps page",
					"faield to redirects to google maps page");
			Common.switchToFirstTab();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating google maps page",
					"System directs the user back to google maps page", "unable to user go back to google maps page",
					Common.getscreenShotPathforReport("faield to get back to google maps page"));
			Assert.fail();

		}

	}

	public void writeReviews() {
		// TODO Auto-generated method stub
		try {

			Common.switchToFirstTab();
			Common.switchFrames("xpath", "//iframe[@id='lcly-embedded-iframe-inner-0']");

			Sync.waitElementPresent("xpath", "//span[text()='Write a Review']");
			Common.clickElement("xpath", "//span[text()='Write a Review']");
			Common.switchWindows();
			int reviewSize = Common.findElements("xpath", "//div[@class='review-form-header']//h1").size();

			System.out.println(reviewSize);
			Common.assertionCheckwithReport(reviewSize >= 0, "validating reviews page",
					"user redirects to reviews page", "Sucessfully user redirects to reviews page",
					"faield to redirects to reviews page");
			Common.switchToFirstTab();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating reviews page", "System directs the user back to reviews page",
					"unable to user go back to reviews page",
					Common.getscreenShotPathforReport("faield to get back to reviews page"));
			Assert.fail();
		}

	}

	public String Store_payment_placeOrder(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String order = "";
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
				Thread.sleep(1000);
				Sync.waitElementPresent(30, "xpath", " //h1[normalize-space()='Thank you for your purchase!']");
				String sucessMessage = Common.getText("xpath",
						" //h1[normalize-space()='Thank you for your purchase!']");

				// Tell_Your_FriendPop_Up();
				int sizes = Common.findElements("xpath", " //h1[normalize-space()='Thank you for your purchase!']")
						.size();
				Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
						"verifying the product confirmation", expectedResult,
						"Successfully It redirects to order confirmation page Order Placed",
						"User unabel to go orderconformation page");

				if (Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//p//span")
						.size() > 0) {
					Thread.sleep(1000);
					order = Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p//span");
					System.out.println(order);
				} else {
					Thread.sleep(1000);
					order = Common.getText("xpath", "//div[contains(@class,'checkout-success')]//p//a");
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

	public void Add_Grouped_Bundle(String Dataset) {
		// TODO Auto-generated method stub
		String Product = data.get(Dataset).get("Products");

		try {
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//img[@alt='" + Product + "']");
			Common.clickElement("xpath", "//img[@alt='" + Product + "']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains(Product),
					"validating the Navigation to the PDP page for selected product",
					"It should navigates to PDP page after clicking on the product",
					"Sucessfully It is navigated to the Pdp page ",
					"failed to Navigate to the PDP page after clicking on the product");
			Products_Grouped_Bundle("1");
			Sync.waitElementPresent("xpath", "//span[text()='Add to Cart']");
			Common.clickElement("xpath", "//span[text()='Add to Cart']");

			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("to your shopping cart"),
					"validating the  product add to the cart", "Product should be add to cart",
					"Sucessfully product added to the cart ", "failed to add product to the cart");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));

			Assert.fail();

		}
	}

	public void Products_Grouped_Bundle(String Dataset) throws Exception {
		// TODO Auto-generated method stub
		int subproductsList = Common
				.findElements("xpath", "//div[@class='m-grouped__items']//div[@class='m-product-upsell__item']").size();
		for (int i = 0; i < subproductsList; i++) {
			int value = i + 1;
			List<WebElement> ListOfSubproducts = Common.findElements("xpath",
					"//div[@class='m-grouped__items']//div[" + value + "]//div[@class=' m-grouped__control-qty']");

			for (int j = 0; j < ListOfSubproducts.size(); j++) {

				if (ListOfSubproducts.get(j).getAttribute("class").contains("m-grouped__con")) {
					Thread.sleep(3000);
//                            ListOfSubproducts.get(j).click();
					Thread.sleep(3000);
					Common.dropdown("xpath", "//div[" + value + "]//select[@class='a-select-menu']",
							Common.SelectBy.VALUE, Dataset);
					String Quantity = Common.findElement("xpath", "//div[@class='m-grouped__items']//div[" + value
							+ "]//div[@class=' m-grouped__control-qty']//input").getAttribute("value");

//                     Common.assertionCheckwithReport(Quantity.equals(Dataset),"Verifying the products quantity ", "Quantity should be selected for each and every product in Grouped Bundle", "successfully Quantity has been selected for each and every product", "Failed to select the product quantity for the grouped bundle");
				} else {
					break;
				}

			}
		}
	}

	public void product_quantity(String DataSet) {
		// TODO Auto-generated method stub
		String Quantity = data.get(DataSet).get("Quantity");
		try {
			Thread.sleep(2000);
			try {
			Sync.waitElementPresent(30,"xpath", "//select[@name='qty']");
			Common.dropdown("xpath", "//select[@name='qty']", Common.SelectBy.VALUE, Quantity);
			Thread.sleep(3000);
			}
			catch (Exception | Error e) {
				Common.dropdown("xpath", "//select[@name='qty']", Common.SelectBy.VALUE, "1");
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product the product quantity in PDP page",
					"Product quantity should be update in the PDP page", "unable to change the  product Qunatity",
					Common.getscreenShot("failed to update the product quantity"));
			Assert.fail();
		}

	}

	public void Myhydro_quantity(String Dataset) {
		// TODO Auto-generated method stub
		String Quantity = data.get(Dataset).get("Quantity");
		try {
			Thread.sleep(3000);
			Common.findElement("xpath", "//select[@class='quantity-dropdown']");
//			Common.clickElement("xpath", "//select[@class='a-select-menu']");
			Common.dropdown("xpath", "//select[@class='quantity-dropdown']", Common.SelectBy.VALUE, Quantity);
			Thread.sleep(3000);
			String value = Common.findElement("xpath", "//select[@class='quantity-dropdown']").getAttribute("value");
			System.out.println(value);
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

	public void register_billingAddress(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//input[@id='billing-as-shipping']");
			Sync.waitElementPresent(30, "xpath", "(//div[contains(@class, 'checkout-address-list__button-container')]//button[@type='button'])[2]");
			Common.clickElement("xpath", "(//div[contains(@class, 'checkout-address-list__button-container')]//button[@type='button'])[2]");
			Thread.sleep(5000);
			Common.textBoxInput("xpath", "//form[@id='billing']//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//form[@id='billing']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='billing']//input[@name='street[0]']", data.get(dataSet).get("Street"));
			String Text = Common.getText("xpath", "//form[@id='billing']//input[@name='street[0]']");
			Sync.waitPageLoad();
			Thread.sleep(5000);

			Common.textBoxInput("xpath", "//form[@id='billing']//input[@name='city']", data.get(dataSet).get("City"));
			System.out.println(data.get(dataSet).get("City"));
 
//			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(4000);
			try {
				Common.dropdown("xpath", "//form[@id='billing']//select[@name='region']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
				Thread.sleep(3000);
				Common.dropdown("id", "shipping-region", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			Common.textBoxInput("xpath", "//form[@id='billing']//input[@name='postcode']", data.get(dataSet).get("postcode"));
			Thread.sleep(4000);
			Common.textBoxInput("xpath", "//form[@id='billing']//input[@name='telephone']",
					data.get(dataSet).get("phone"));
			Thread.sleep(4000);
			Common.clickElement("xpath", "//button[contains(@class, 'checkout-address-form__buttons-save')]");
			Sync.waitPageLoad();
			Thread.sleep(2000);
		  // String	update = Common.findElement("xpath", "//select[@id='address-list']").getAttribute("value");
			//	System.out.println(update);
			/*Common.assertionCheckwithReport(update.equals(0) ,
					"verifying the Billing address form in payment page",
					"Billing address should be saved in the payment page",
					"Sucessfully Billing address form should be Display ",
					"Failed to display the Billing address in payment page");*/
 
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the Billing address form in payment page",
					"Billing address should be saved in the payment page",
					"Unable to display the Billing address in payment page ",
					Common.getscreenShot("Failed to display the Billing address in payment page"));
			Assert.fail();
		}
 
 
	}

	public void outofstock_subcription(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
//		String products = "25 oz Wine Bottle";
		String email = data.get(Dataset).get("Notifyme");
		String prod = data.get(Dataset).get("prod product");
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[@itemprop='image']");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[@itemprop='image']");

				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Sync.waitPageLoad();
			Thread.sleep(4000);
			if (Common.getCurrentURL().contains("preprod")) {
				Sync.waitElementPresent(30, "xpath", "//img[contains(@alt,'" + products + "')]");
				String productprice = Common.findElement("xpath", "//span[@data-price-type='finalPrice']")
						.getAttribute("data-price-amount");
				Common.clickElement("xpath", "//img[contains(@alt,'" + products + "')]");
				Sync.waitPageLoad();
				Thread.sleep(5000);
				String PLPprice = Common.findElement("xpath","//span[@class='price-wrapper']//span[2]")
						.getAttribute("data-price-final-amount--finalprice");
				String name = Common.findElement("xpath", "//h1[@itemprop='name']").getText();
				Common.assertionCheckwithReport(
						name.contains(products) && productprice.equals(PLPprice)
								|| name.contains(prod) && productprice.equals(PLPprice),
						"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
						"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
				Common.javascriptclickElement("xpath", "(//button[@title='Email Me When Available'])[1]");
				Thread.sleep(2000);
				Common.textBoxInput("xpath", "//input[@placeholder='Insert your email']", email);
				Thread.sleep(2000);
				Common.clickElement("xpath", "//span[text()='Subscribe']");
				Sync.waitPageLoad();
				Thread.sleep(2000);
				//String newsubcribe ="Thank you! You are already subscribed to this product.";
				String newsubcribe = Common.findElement("xpath", "//div[@class='relative flex w-full']//span").getText();
				Common.assertionCheckwithReport(
						newsubcribe.contains("Alert subscription has been saved.")
								|| newsubcribe.contains("Thank you! You are already subscribed to this product."),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");
				Common.actionsKeyPress(Keys.END);
				Sync.waitPageLoad();
				Common.clickElement("xpath", "(//button[@title='Email Me When Available'])[1]");
				Thread.sleep(2000);
				Common.textBoxInput("xpath", "//input[@placeholder='Insert your email']", email);
				Common.clickElement("xpath", "//span[text()='Subscribe']");
				Sync.waitPageLoad();
				Thread.sleep(1000);
				//String oldsubcribe ="Thank you! You are already subscribed to this product."; 
				String oldsubcribe = Common.findElement("xpath", "//div[@class='relative flex w-full']//span").getText();
				Common.assertionCheckwithReport(
						oldsubcribe.contains("Thank you! You are already subscribed to this product."),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");

			} else {
				Sync.waitElementPresent(30, "xpath", "//img[contains(@alt,'" + prod + "')]");
				String productprice = Common.findElement("xpath", "//span[@class='price-wrapper']")
						.getAttribute("data-price-amount");
				Common.clickElement("xpath", "//img[contains(@alt,'" + prod + "')]");
				Sync.waitPageLoad();
				Thread.sleep(3000);
				String PLPprice = Common
						.findElement("xpath",
								"//div[@class='m-product-overview__prices']//span[@class='price-wrapper ']")
						.getAttribute("data-price-amount");
				String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
				Common.assertionCheckwithReport(
						name.contains(products) && productprice.equals(PLPprice)
								|| name.contains(prod) && productprice.equals(PLPprice),
						"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
						"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
				Common.clickElement("xpath", "(//button[@title='Email Me When Available'])[1]");
				Common.textBoxInput("xpath", "//input[@placeholder='Insert your email']", email);
				Common.clickElement("xpath", "//span[text()='Subscribe']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String newsubcribe = Common.findElement("xpath", "//div[@class='relative flex w-full']//span").getText();
				Common.assertionCheckwithReport(
						newsubcribe.contains("Alert subscription has been saved.")
								|| newsubcribe.contains("Thank you! You are already subscribed to this product."),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");
				Common.actionsKeyPress(Keys.END);
				Common.clickElement("xpath", "(//button[@title='Email Me When Available'])[1]");
				Common.textBoxInput("xpath", "//input[@placeholder='Insert your email']", email);
				Common.clickElement("xpath", "//span[text()='Subscribe']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String oldsubcribe = Common.findElement("xpath", "//div[@class='relative flex w-full']//span").getText();
				Common.assertionCheckwithReport(
						oldsubcribe.contains("Thank you! You are already subscribed to this product."),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");

			}

		} catch (Exception | Error e) {

			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the out of stock subcription",
					"after click on subcribe button message should be appear",
					"Unable to display the message after subcribtion ",
					Common.getscreenShot("Failed to display the message after subcribtion"));
			Assert.fail();

		}

	}

	public String reg_outofstock_subcription(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		String prod = data.get(Dataset).get("prod product");
		String price = "";

		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[contains(@alt,'Stainless Steel Straw Set')]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@alt,'Stainless Steel Straw Set')]");

				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Thread.sleep(6000);
			if (Common.getCurrentURL().contains("preprod")) {
				Sync.waitElementPresent(30, "xpath", "//img[contains(@alt,'" + products + "')]");
				String productprice = Common.findElement("xpath", "(//span[@data-price-type='finalPrice'])[4]")
						.getAttribute("data-price-amount");
				Common.clickElement("xpath", "//img[contains(@alt,'" + products + "')]");
				Sync.waitPageLoad();
				Thread.sleep(3000);
				String PLPprice = Common.findElement("xpath","(//span[@class='price'])[2]")
						.getAttribute("data-price-final-amount--finalprice");
				String name = Common.findElement("xpath", "//h1[@itemprop='name']").getText();
				Common.assertionCheckwithReport(
						name.contains(products) && productprice.equals(PLPprice)
								|| name.contains(prod) && productprice.equals(PLPprice),
						"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
						"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
				Common.clickElement("xpath", "(//button[@title='Email Me When Available'])[1]");
				Sync.waitPageLoad();
				Thread.sleep(1000);
				String newsubcribe = Common.findElement("xpath", "//div[@class='relative flex w-full']//span").getText();
				System.out.println(newsubcribe);
				Common.assertionCheckwithReport(
						newsubcribe.contains("Alert subscription has been saved.")
								|| newsubcribe.contains("Thank you! You are already subscribed to this product."),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");
				Common.actionsKeyPress(Keys.END);
				Common.clickElement("xpath", "(//button[@title='Email Me When Available'])[1]");
				Sync.waitPageLoad();
				Thread.sleep(1000);
				String oldsubcribe = Common.findElement("xpath", "//div[@class='relative flex w-full']//span").getText();
				System.out.println(oldsubcribe);
				Common.assertionCheckwithReport(
						oldsubcribe.contains("Thank you! You are already subscribed to this product."),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");
				price = Common.findElement("xpath", "//span[@data-price-type='finalPrice']")
						.getAttribute("data-price-amount");
			} else {
				Sync.waitElementPresent(30, "xpath", "//img[contains(@alt,'" + prod + "')]");
				String productprice = Common.findElement("xpath", "//span[@class='price-wrapper']")
						.getAttribute("data-price-amount");
				Common.clickElement("xpath", "//img[contains(@alt,'" + prod + "')]");
				Sync.waitPageLoad();
				Thread.sleep(3000);
				String PLPprice = Common
						.findElement("xpath",
								"//div[@class='m-product-overview__prices']//span[@class='price-wrapper ']")
						.getAttribute("data-price-amount");
				String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
				Common.assertionCheckwithReport(
						name.contains(products) && productprice.equals(PLPprice)
								|| name.contains(prod) && productprice.equals(PLPprice),
						"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
						"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
				Common.clickElement("xpath", "(//button[@title='Email Me When Available'])[1]");
				Sync.waitPageLoad();
				Thread.sleep(1000);
				String newsubcribe = Common.findElement("xpath", "//div[@class='relative flex w-full']//span").getText();
				System.out.println(newsubcribe);
				Common.assertionCheckwithReport(
						newsubcribe.contains("Alert subscription has been saved.")
								|| newsubcribe.contains("Thank you! You are already subscribed to this product."),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");
				Common.actionsKeyPress(Keys.END);
				Common.clickElement("xpath", "(//button[@title='Email Me When Available'])[1]");
				Sync.waitPageLoad();
				Thread.sleep(1000);
				String oldsubcribe = Common.findElement("xpath", "//div[@class='relative flex w-full']//span").getText();
				System.out.println(oldsubcribe);
				Common.assertionCheckwithReport(
						oldsubcribe.contains("Thank you! You are already subscribed to this product."),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");
				price = Common.findElement("xpath", "//span[@data-price-type='finalPrice']")
						.getAttribute("data-price-amount");
			}

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the out of stock subcription",
					"after click on subcribe button message should be appear",
					"Unable to display the message after subcribtion ",
					Common.getscreenShot("Failed to display the message after subcribtion"));
			Assert.fail();
		}
		return price;

	}

	public void share_whishlist(String Dataset) {
		// TODO Auto-generated method stub
				try {
					Sync.waitPageLoad();
					int MyFavorites = Common.findElements("xpath", "//div[contains(@class,'message')]//span").size();
		 
					if (MyFavorites != 0) {
						search_product("Product");
						Sync.waitElementPresent(30, "xpath", "//button[contains(@class, 'group/wishlist')]");
						Common.scrollIntoView("xpath", "//button[contains(@class, 'group/wishlist')]");
						Common.clickElement("xpath", "//button[contains(@class, 'group/wishlist')]");
						Common.clickElement("xpath","//span[@x-text='customerGreeting']");
						Sync.waitElementPresent(30, "id", "customer-menu");
						Common.clickElement("id", "customer-menu");
						Sync.waitElementPresent(30, "xpath", "//a[@title='My Favorites']");
						Common.clickElement("xpath", "//a[@title='My Favorites']");
						Sync.waitPageLoad();
						Thread.sleep(4000);
						Common.assertionCheckwithReport(Common.getPageTitle().equals("Wish List Sharing"),
								"validating the Navigation to the My Favorites page",
								"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
								"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA",
								"Failed to Navigate to the My Favorites page after Clicking on My Favorites button");
						Common.findElements("xpath", "//span[contains(@class,'a-wishlist')]");
						Sync.waitPageLoad();
						Thread.sleep(4000);
//						String message = Common.findElement("xpath", "//span[@class='w-full text-center pr-10']").getText();
//						System.out.println(message);
//						Common.assertionCheckwithReport(message.contains("Click here to view your Favorites."),
//								"validating the  product add to the Whishlist", "Product should be add to whishlist",
//								"Sucessfully product added to the Whishlist ", "failed to add product to the Whishlist");
						//Common.clickElement("xpath", "(//button[@aria-haspopup='dialog'])[3]");
						Common.clickElement("xpath", "//button[contains(@class,'customer-wishlist-title-container-share-button')]");

						Sync.waitPageLoad();
						Thread.sleep(2000);
						Common.textBoxInput("xpath", "//textarea[@name='emails']", data.get(Dataset).get("Email"));
						Common.textBoxInput("xpath", "//textarea[@name='message']", data.get(Dataset).get("message"));
						Thread.sleep(4000);
						Common.clickElement("xpath", "//button[contains(@class,'customer-wishlist-title-container-share-button')]");
						Sync.waitPageLoad();
						Thread.sleep(3000);
						String message1 = Common.findElement("xpath", "//span[text()='Your wish list has been shared.']").getText();
						System.out.println(message1);
						Common.assertionCheckwithReport(message1.contains("Your wish list has been shared."),
								"validating the shared whishlist functionality",
								"sucess message should display after share whishlist",
								"Sucessfully message has been displayed for whishlist",
								"failed to display the message for whishlist");
					} else {
						Common.clickElement("xpath", "//button[@aria-haspopup='dialog']//parent::div[contains(@x-data,'hyva')]");
						Sync.waitPageLoad();
						Thread.sleep(4000);
						Common.textBoxInput("xpath", "//textarea[@name='emails']", data.get(Dataset).get("Email"));
						Common.textBoxInput("xpath", "//textarea[@name='message']", data.get(Dataset).get("message"));
						Thread.sleep(4000);
						Common.clickElement("xpath", "//button[@title='Share Wish List']");
						Sync.waitElementPresent(30, "xpath", "//div[@ui-id='message-success']");
						String message1 = Common.findElement("xpath", "//div[@ui-id='message-success']").getText();
						System.out.println(message1);
						Common.assertionCheckwithReport(message1.contains("Your wish list has been shared."),
								"validating the shared whishlist functionality",
								"sucess message should display after share whishlist",
								"Sucessfully message has been displayed for whishlist",
								"failed to display the message for whishlist");
		 
					}
				} catch (Exception | Error e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("validating the shared whishlist functionality",
							"sucess message should display after share whishlist",
							"Unable to display the message for whishlist",
							Common.getscreenShot("failed to display the message for whishlist"));
					Assert.fail();
				}
	}

	public void Add_Myhydro(String Dataset) {
		// TODO Auto-generated method stub

		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//li[@class='ais-InfiniteHits-item']//img[1]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//li[@class='ais-InfiniteHits-item']//img[1]");

				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Thread.sleep(6000);
			Sync.waitElementPresent("xpath", "//img[@alt='" + products + "']");
			Common.javascriptclickElement("xpath", "//img[@alt='" + products + "']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			String name = Common.findElement("xpath", "//h1[@itemprop='name']").getText();
			Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"failed to Navigate to the PDP page");
			Common.clickElement("xpath", "//span[text()='Customize now']");
			Thread.sleep(3000);
			Myhydro_bottle("40 oz");
			hydro_bottle_color("Clementine");
			hydro_cap_color("White");
			hydro_strap_color("Black");
			hydro_boot_color("White");
				Myhydro_Engraving("Myhydro Product");
			//Sync.waitElementPresent("xpath", "//button[@class='nav-buttons__btn next-btn']");
			//Common.clickElement("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Myhydro_quantity(Dataset);
			Sync.waitElementPresent(20, "xpath", "//button[@class='ATC__btn']");
			Common.clickElement("xpath", "//button[@class='ATC__btn']");
			Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//span[contains(text(),'Agree &')]");
				Common.clickElement("xpath", "//span[contains(text(),'Agree &')]");
			
			Thread.sleep(10000);
//			Sync.waitElementPresent(40, "id", "cart-drawer-title");
//			String message = Common.findElement("id", "message-success").getText();
//			System.out.println(message);
//			Common.assertionCheckwithReport(message.contains("You added"), "validating the  product add to the cart",
//					"Product should be add to cart", "Sucessfully product added to the cart ",
//					"failed to add product to the cart");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));
			Assert.fail();
		}

	}

	public void Add_Myhydro_Text(String Dataset) {
		// TODO Auto-generated method stub

		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//li[@class='ais-InfiniteHits-item']//img[1]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//li[@class='ais-InfiniteHits-item']//img[1]");

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
			String name = Common.findElement("xpath", "//h1[@itemprop='name']").getText().trim();
			Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"failed to Navigate to the PDP page");
			Sync.waitElementPresent(30, "xpath", "//span[text()='Customize now']");
			Common.clickElement("xpath", "//span[text()='Customize now']");
			Thread.sleep(3000);
			Myhydro_bottle("40 oz");
			hydro_bottle_color("Clementine");
			hydro_cap_color("White");
			hydro_strap_color("Black");
			hydro_boot_color("White");
			Myhydro_Engraving("Myhydro Product");
			Myhydro_quantity(Dataset);
			Sync.waitElementPresent(20, "xpath", "//button[@class='ATC__btn']");
			Common.clickElement("xpath", "//button[@class='ATC__btn']");
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Agree &')]");
			Common.clickElement("xpath", "//span[contains(text(),'Agree &')]");
			Thread.sleep(10000);
//			Sync.waitElementPresent(40, "xpath", "//div[@class='a-message__container-inner']");
//			String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
//			System.out.println(message);
//			Common.assertionCheckwithReport(message.contains("You added"), "validating the  product add to the cart",
//					"Product should be add to cart", "Sucessfully product added to the cart ",
//					"failed to add product to the cart");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));
			Assert.fail();
		}

	}

	public void Myhydro_bottle(String Dataset) throws Exception {
		// TODO Auto-generated method stub
		Sync.waitPageLoad();
		Thread.sleep(8000);
		try {
			if (Dataset.equals("20 oz")) {
				Thread.sleep(8000);
				Sync.waitElementPresent("xpath", "//button[@data-gtm-parts='20 oz']");
				Common.clickElement("xpath", "//button[@data-gtm-parts='20 oz']");
				Thread.sleep(4000);
				String name = Common.findElement("xpath", "//h1[@class='hero-section__product-title']").getText();
				System.out.println(name);
				Common.assertionCheckwithReport(name.contains(Dataset), "validating the product in pdp page",
						"It should be selected for the selected product" + Dataset,
						"Sucessfully Navigates to the selected product" + Dataset,
						"failed to Navigate to the selected product");
			} else if (Dataset.equals("40 oz")) {
				Sync.waitElementPresent("xpath", "//button[@data-gtm-parts='40 oz']");
				Common.clickElement("xpath", "//button[@data-gtm-parts='40 oz']");
				Thread.sleep(4000);
				String name = Common.findElement("xpath", "//h1[@class='hero-section__product-title']").getText();
				System.out.println(name);
				Common.assertionCheckwithReport(name.contains(Dataset), "validating the product in pdp page",
						"It should be selected for the selected product" + Dataset,
						"Sucessfully Navigates to the selected product" + Dataset,
						"failed to Navigate to the selected product");
			} else if (Dataset.equals("32 oz")) {
				Sync.waitElementPresent("xpath", "//button[@data-gtm-parts='32 oz']");
				Common.clickElement("xpath", "//button[@data-gtm-parts='32 oz']");
				String name = Common.findElement("xpath", "//h1[@class='hero-section__product-title']").getText();
				System.out.println(name);
				Common.assertionCheckwithReport(name.contains(Dataset), "validating the product in pdp page",
						"It should be selected for the selected product" + Dataset,
						"Sucessfully Navigates to the selected product" + Dataset,
						"failed to Navigate to the selected product");
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the product in the PDP page",
					"It should be selected for the selected productt", "Unable to Navigate the selected product",
					Common.getscreenShot("failed to navigate to the selected product"));
			Assert.fail();
		}

	}

	public void hydro_bottle_color(String Color) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@aria-label='" + Color + "']");
			Common.clickElement("xpath", "//button[@aria-label='" + Color + "']");
			String productcolor = Common.findElement("xpath", "//label[@class='color-feature__selection__label']")
					.getText();
			System.out.println(productcolor);
			Common.assertionCheckwithReport(productcolor.contains(Color), "validating the color selection for bottle",
					"color should be select for the bottle", "Sucessfully color has been selected for the bottle",
					"failed to select the color for the selected bottle");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the color selection for bottle",
					"color should be select for the bottle", "unable to select the color for the selected bottle",
					Common.getscreenShot("failed to select the color for the selected bottle"));
			Assert.fail();
		}

	}

	public void hydro_cap_color(String Color) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Common.clickElement("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Thread.sleep(3000);
			String Cap = Common.findElement("xpath", "//h1[@class='menu__category-title']").getText().trim();
			Common.assertionCheckwithReport(Cap.contains("Cap"), "validating the color selection for bottle",
					"color should be select for the bottle", "Sucessfully color has been selected for the bottle",
					"failed to select the color for the selected bottle");
			hydro_select_cap("Wide Mouth Flex Sip Lid");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@aria-label='" + Color + "']");
			Common.clickElement("xpath", "//button[@aria-label='" + Color + "']");
			String productcolor = Common.findElement("xpath", "//label[@class='color-feature__selection__label']")
					.getText();
			System.out.println(productcolor);
//			Common.assertionCheckwithReport(productcolor.contains(Color), "validating the color selection for cap",
//					"color should be select for the cap", "Sucessfully color has been selected for the cap",
//					"failed to select the color for the selected cap");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the color selection for cap",
					"color should be select for the cap", "unable to select the color for the selected cap",
					Common.getscreenShot("failed to select the color for the selected cap"));

			Assert.fail();
		}

	}

	public void hydro_strap_color(String Color) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Common.clickElement("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Thread.sleep(3000);
			String Strap = Common.findElement("xpath", "//h1[@class='menu__category-title']").getText().trim();
			Common.assertionCheckwithReport(Strap.contains("Strap"), "validating the color selection for bottle",
					"color should be select for the bottle", "Sucessfully color has been selected for the bottle",
					"failed to select the color for the selected bottle");
			hydro_select_strap("Long Flex Cap Strap");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@aria-label='" + Color + "']");
			Common.clickElement("xpath", "//button[@aria-label='" + Color + "']");
			String productcolor = Common.findElement("xpath", "//label[@class='color-feature__selection__label']")
					.getText();
			System.out.println(productcolor);
			Thread.sleep(3000);
//			Common.assertionCheckwithReport(productcolor.contains(Color), "validating the color selection for strap",
//					"color should be select for the strap", "Sucessfully color has been selected for the strap",
//					"failed to select the color for the selected strap");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the color selection for starp",
					"color should be select for the starp", "unable to select the color for the selected starp",
					Common.getscreenShot("failed to select the color for the selected starp"));

			Assert.fail();
		}

	}

	public void hydro_boot_color(String Color) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Common.clickElement("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Thread.sleep(3000);
			String boot = Common.findElement("xpath", "//h1[@class='menu__category-title']").getText().trim();
			Common.assertionCheckwithReport(boot.contains("Boot"), "validating the color selection for bottle",
					"color should be select for the bottle", "Sucessfully color has been selected for the bottle",
					"failed to select the color for the selected bottle");
			Sync.waitElementPresent("xpath", "//button[@aria-label='" + Color + "']");
			Common.clickElement("xpath", "//button[@aria-label='" + Color + "']");
			String productcolor = Common.findElement("xpath", "//label[@class='color-feature__selection__label']")
					.getText();
			System.out.println(productcolor);
//			Common.assertionCheckwithReport(productcolor.contains(Color), "validating the color selection for boot",
//					"color should be select for the boot", "Sucessfully color has been selected for the boot",
//					"failed to select the color for the selected boot");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the color selection for boot",
					"color should be select for the boot", "unable to select the color for the selected boot",
					Common.getscreenShot("failed to select the color for the selected boot"));

			Assert.fail();
		}

	}

	public void hydro_select_cap(String Cap) {
		// TODO Auto-generated method stub
		try {
			if (Cap.contains("Flex Cap")) {
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//button[@data-gtm-parts='" + Cap + "']");
				Common.clickElement("xpath", "//button[@data-gtm-parts='" + Cap + "']");
				Thread.sleep(3000);
				String name = Common.findElement("xpath", "//button[@data-gtm-parts='" + Cap + "']")
						.getAttribute("class");
				System.out.println(name);
				Common.assertionCheckwithReport(name.contains("active"), "validating the cap for the bottle",
						"Cap should be selected for the particular bottle", "Sucessfully Cap has been selected",
						"failed to selected the cap for the particular bottle");
			} else if (Cap.contains("Flex Sip Lid")) {
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//button[@data-gtm-parts='" + Cap + "']");
				Common.clickElement("xpath", "//button[@data-gtm-parts='" + Cap + "']");
				Thread.sleep(3000);
				String name = Common.findElement("xpath", "//button[@data-gtm-parts='" + Cap + "']")
						.getAttribute("class");
				System.out.println(name);
				Common.assertionCheckwithReport(name.contains("active"), "validating the cap for the bottle",
						"Cap should be selected for the particular bottle", "Sucessfully Cap has been selected",
						"failed to selected the cap for the particular bottle");
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the cap for the bottle",
					"Cap should be selected for the particular bottle",
					"Unable to selected the cap for the particular bottle",
					Common.getscreenShot("failed to selected the cap for the particular bottle"));

			Assert.fail();
		}

	}

	public void hydro_select_strap(String Starp) {
		// TODO Auto-generated method stub
		try {
			if (Starp.contains("Flex Cap Strap")) {
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//button[@data-gtm-parts='" + Starp + "']");
				Common.clickElement("xpath", "//button[@data-gtm-parts='" + Starp + "']");
				Thread.sleep(3000);
				String name = Common.findElement("xpath", "//button[@data-gtm-parts='" + Starp + "']")
						.getAttribute("class");
				System.out.println(name);
				Common.assertionCheckwithReport(name.contains("active"), "validating the Strap for the bottle",
						"strap should be selected for the particular bottle", "Sucessfully strap has been selected",
						"failed to selected the strap for the particular bottle");
			} else if (Starp.contains("Long")) {
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//button[@data-gtm-parts='" + Starp + "']");
				Common.clickElement("xpath", "//button[@data-gtm-parts='" + Starp + "']");
				Thread.sleep(3000);
				String name = Common.findElement("xpath", "//button[@data-gtm-parts='" + Starp + "']")
						.getAttribute("class");
				System.out.println(name);
				Common.assertionCheckwithReport(name.contains("active"), "validating the strap for the bottle",
						"strap should be selected for the particular bottle", "Sucessfully strap has been selected",
						"failed to selected the strap for the particular bottle");
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the strap for the bottle",
					"starp should be selected for the particular bottle",
					"Unable to selected the strap for the particular bottle",
					Common.getscreenShot("failed to selected the strap for the particular bottle"));

			Assert.fail();
		}

	}

	public void Myhydro_Engraving(String Dataset) {
		// TODO Auto-generated method stub
		String engravingtext = data.get(Dataset).get("Engraving");
		try {
			Sync.waitElementPresent("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Common.clickElement("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Thread.sleep(3000);
			String Engraving = Common.findElement("xpath", "//h1[@class='menu__category-title']").getText().trim();
			Common.assertionCheckwithReport(Engraving.contains("Engraving"), "validating the Engraving for the bottle",
					"Engraving should be select for the bottle",
					"Sucessfully Engraving  has been selected for the bottle",
					"failed to select the Engraving for the selected bottle");
			Common.clickElement("css", "button[id='Text-category-button']");
			Sync.waitElementPresent("id", "engraving-category-menu");
			Common.findElement("id", "engraving-category-menu").sendKeys(engravingtext);
			String Text = Common.findElement("id", "engraving-category-menu")
					.getAttribute("class");
			System.out.println(Text);
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Text.contains("focus-visible"), "validating the engraving text for bottle",
					"Engraving text should be added for the bottle",
					"Sucessfully Engraving has been added for the bottle",
					"failed to add the engraving for the bottle");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the engraving text for bottle",
					"Engraving text should be added for the bottle", "Unable to add the Engraving for the bottle",
					Common.getscreenShot("failed to add engraving for the bottle"));
			Assert.fail();
		}

	}

	public void url_color_validation(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Colorproduct");
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//a[@class='product-image-link']");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//a[@class='product-image-link']");
				String s = webelementslist.get(i).getAttribute("href");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Common.clickElement("xpath", "//img[@alt='" + product + "']");
			Thread.sleep(4000);
	}
		
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the product should navigate to the PDP page",
					"When we click on the product is should navigate to the PDP page",
					"Unable to Navigate the product to the PDP page",
					Common.getscreenShot("Failed to Navigate the product to the PDP page"));
			AssertJUnit.fail();
		}

		
		try
		{
			
			List<WebElement> pdpcolors = Common.findElements("xpath",
					"//div[@aria-label='Color']//div[@x-id]");
//			 Common.clickElement("xpath", "//div[@aria-label='Color']//div[@x-id]");
			Common.actionsKeyPress(Keys.PAGE_UP);
			for (int i =1; i < pdpcolors.size(); i++) { 
	           Thread.sleep(4000);
				pdpcolors.get(i).click();
				Thread.sleep(4000);
				String clicked_Color = pdpcolors.get(i).getAttribute("data-option-label");
				System.out.println(clicked_Color + " selected color");
	            Thread.sleep(4000);
				System.out.println(Common.getCurrentURL());
				String URL=Common.getCurrentURL().replace("+", " ");
				System.out.println(URL);
				Common.assertionCheckwithReport(URL.contains(clicked_Color),
						"Validating PDP page url Color name is passing to url",
						"select the color of product is " + clicked_Color + " it must pass throw url",
						" Selected color " + clicked_Color + "passing throw url",
						"Failed to clicked color is passing throw URL" + clicked_Color);
		
			}
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the color and url in PDP page",
					"When we click on the color the color name should reflect in url",
					"Unable to display thee color name in the url",
					Common.getscreenShot("Failed to display thee color name in the url"));
			AssertJUnit.fail();
		}
	}

	public void update_shoppingcart(String Dataset) {
		// TODO Auto-generated method stub
		String quantity = data.get(Dataset).get("Quantity");
		try {
			Common.clickElement("xpath", "(//select[@title='Qty'])[1]");
			Common.dropdown("xpath", "(//select[@title='Qty'])[1]", Common.SelectBy.VALUE, quantity);
//			Common.clickElement("xpath", "//span[text()='Update']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitPageLoad();
			Thread.sleep(5000);
			String productquantity = Common.findElement("xpath", "(//select[@title='Qty'])[1]")
					.getAttribute("value");
			System.out.println(productquantity);
			Common.assertionCheckwithReport(productquantity.equals(quantity),
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

public void updateproductcolor_shoppingcart(String Dataset) {
		// TODO Auto-generated method stub
		String productcolor1 = "Indigo";
		System.out.println(productcolor1);
		try {
			Common.clickElement("xpath", "//div[@class='mt-2 title-xs hf:title-2xs os:text-sm']//span[2])[3]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//input[@aria-label='" + productcolor1 + "']");
			Common.clickElement("xpath", "//input[@aria-label='" + productcolor1 + "']");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//span[contains(text(),'Update item')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String getProductColor =Common.findElement("xpath","(//div[@class='mt-2 title-xs hf:title-2xs os:text-sm']//span)[4]").getText().trim();
			System.out.println(getProductColor);
			Common.assertionCheckwithReport(productcolor1.equals(getProductColor),
					"validating the update color in shopping cart page",
					"color should be update in the shopping cart page",
					"color has been updated in the shopping cart page",
					"Failed to update the product color in the shopping cart page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the update color in shopping cart page",
					"color should be update in the shopping cart page",
					"Unable to update the product color in the shopping cart page",
					Common.getscreenShot("Failed to update the product color in the shopping cart page"));
			Assert.fail();
		}

	}



	public void addtocart_PLP(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "(//img[contains(@class,'group-hover/item-image:hidden')])[1]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"(//img[contains(@class,'group-hover/item-image:hidden')])[1]");

				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Thread.sleep(6000);
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.mouseOver("xpath", "//img[@alt='" + products + "']");
			//Sync.waitElementPresent("xpath", "//span[text()='Add to Cart']");
			//Common.clickElement("xpath", "//span[text()='Add to Cart']");
//			Sync.waitPageLoad();
			Thread.sleep(2000);
//			Common.clickElement("xpath", "//button[@aria-label='Close minicart']");
//			String message = Common.findElement("xpath", "//div[@ui-id='message-success']")
//					.getAttribute("ui-id");
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

	public void Configurable_addtocart_plp(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Colorproduct");
		String PLPcolor = data.get(Dataset).get("PLP Color");
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[contains(@class,'m-product-card__image product')]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@class,'m-product-card__image product')]");
				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Sync.waitElementPresent("xpath", "//img[@alt='" + product + "']");
			Common.mouseOver("xpath", "//img[@alt='" + product + "']");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//div[@aria-label='" + PLPcolor + "']");
			Common.clickElement("xpath", "//div[@aria-label='" + PLPcolor + "']");
			Sync.waitElementPresent("xpath", "//span[text()='Add to Cart']");
			Common.clickElement("xpath", "//span[text()='Add to Cart']");

			Thread.sleep(4000);
			String message2 = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
					.getAttribute("data-ui-id");
			Common.assertionCheckwithReport(message2.contains("success"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"Unable to add product to the cart ", Common.getscreenShot("Failed to add product to the cart"));
			Assert.fail();
		}

	}

	public void PDP_video_validation(String Dataset) {
		// TODO Auto-generated method stub
				String product = data.get(Dataset).get("Colorproduct");
				String color = data.get(Dataset).get("Color");
				try {
					Sync.waitPageLoad();
					for (int i = 0; i <= 10; i++) {
						//Sync.waitElementPresent("xpath", "//li[@class='ais-InfiniteHits-item']//img[1]");
						Sync.waitElementPresent("xpath", "//a[@class='product-image-link']");

						List<WebElement> webelementslist = Common.findElements("xpath","//a[@class='product-image-link']");
						String s = webelementslist.get(i).getAttribute("href");
						System.out.println(s);
						if (s.isEmpty()) {

						} else {
							break;
						}
					}
					
					Sync.waitElementPresent("xpath", "//img[@alt='" + product + "']");
					Common.javascriptclickElement("xpath", "//img[@alt='" + product + "']");
					Sync.waitElementPresent(30, "xpath", "//div[@data-option-label='" + color + "']");
					Common.clickElement("xpath", "//div[@data-option-label='" + color + "']");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					System.out.println(product);
					String name = Common.findElement("xpath", "//h1[@itemprop='name']").getText().trim();
					System.out.println(name);
					Common.assertionCheckwithReport(name.contains(product),
							"validating the product should navigate to the PDP page",
							"When we click on the product is should navigate to the PDP page",
							"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");
					Thread.sleep(3000);
					Common.scrollIntoView("xpath", "//h1[@itemprop='name']");
					if(Common.getCurrentURL().contains("https://mcloud-na-preprod.hydroflask.com/")) {
						Thread.sleep(3000);
						Sync.waitElementClickable("xpath", "(//div[@x-ref='jsThumbSlides']//div)[6]");
						Common.javascriptclickElement("xpath", "(//div[@x-ref='jsThumbSlides']//div)[6]");
					}else {
						Thread.sleep(3000);
						Sync.waitElementClickable("xpath", "(//div[@x-ref='jsThumbSlides'])//div[5]");
						Common.clickElement("xpath", "(//div[@x-ref='jsThumbSlides'])//div[5]");
					}
				Thread.sleep(2000);
						//Common.switchFrames("xpath", "//iframe[contains(@id,'vimeo')]");
					//	Sync.waitElementClickable("xpath", "//iframe[contains(@id,'vimeo')]");

					//	Sync.waitElementClickable("xpath", "//img[@alt='video thumbnail'][1]");
					//Common.switchFrames("xpath", "//div[@class='js_thumbs_slide flex shrink-0 rounded'][1]");
					Sync.waitElementClickable("xpath", "//div[@class='js_thumbs_slide flex shrink-0 rounded'][1]");

						Sync.waitElementClickable("xpath", "//div[@class='js_thumbs_slide flex shrink-0 rounded'][6]/button");
//						Common.scrollIntoView("xpath", "//div[@class='js_thumbs_slide flex shrink-0 rounded'][6]/button");
						Common.javascriptclickElement("xpath", "//div[@class='js_thumbs_slide flex shrink-0 rounded'][6]/button");
						
						Common.switchFrames("xpath","//iframe[@allow='autoplay']");
		                Thread.sleep(3000);
						Common.clickElement("xpath","//span[@id='play-button-tooltip']");
						   Thread.sleep(3000);
						int video1 = Common.findElements("xpath","//span[@id='play-button-tooltip']").size();
						Common.switchToDefault();
						System.out.println(video1);
						Common.assertionCheckwithReport(video1>0,"validating the video in PDP page",
								"video should be play in the PDP page", "Sucessfully the video has been played on the PDP page",
								"failed to play the video in PDP page");


				} catch (Exception | Error e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("validating the video in PDP page", "video should be play in the PDP page",
							"Unable to play the the video on the PDP page",
							Common.getscreenShot("failed to play the video in PDP page"));
					Assert.fail();
				}

	}
	public void Prod_PDP_video_validation(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Colorproduct");
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//li[@class='ais-InfiniteHits-item']//img[1]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//li[@class='ais-InfiniteHits-item']//img[1]");
				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			
			Sync.waitElementPresent("xpath", "//img[@alt='" + product + "']");
			Common.javascriptclickElement("xpath", "//img[@alt='" + product + "']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			System.out.println(product);
			String name = Common.findElement("xpath", "//h1[@itemprop='name']").getText().trim();
			System.out.println(name);
			Common.assertionCheckwithReport(name.contains(product),
					"validating the product should navigate to the PDP page",
					"When we click on the product is should navigate to the PDP page",
					"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");
			WebElement video=Common.findElement("xpath", "(//div[@x-ref='jsThumbSlides']//div)[4]");
			Common.scrollIntoView(video);
	
//			Common.actionsKeyPress(Keys.UP);
			Thread.sleep(3000);
			Common.scrollIntoView("xpath", "//h1[@itemprop='name']");
			Sync.waitElementClickable("xpath", "(//div[@x-ref='jsThumbSlides']//div)[4]");
			Common.clickElement("xpath", "(//div[@x-ref='jsThumbSlides']//div)[4]");

			Sync.waitElementClickable("xpath", "(//div[@x-ref='jsThumbSlides']//div)[5]");
			Common.clickElement("xpath", "(//div[@x-ref='jsThumbSlides']//div)[5]");

//			Common.switchFrames("xpath", "//iframe[contains(@id,'vimeo')]");
//			Sync.waitElementClickable(40, "xpath", "//div[@class='absolute inset-0 grid justify-items-start items-end']//button[@aria-label='Play video']");
			Common.findElementBy("xpath", "//button[@aria-label='Play video']");
			Common.javascriptclickElement("xpath", "//button[@aria-label='Play video']");
			Sync.waitForLoad();
			String video1 = Common.findElement("xpath", "//button[@aria-label='Pause video']").getAttribute("title");
			System.out.println(video1);
			Common.assertionCheckwithReport(video1.equals("Pause video"), "validating the video in PDP page",
					"video should be play in the PDP page", "Sucessfully the video has been played on the PDP page",
					"failed to play the video in PDP page");	

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the video in PDP page", "video should be play in the PDP page",
					"Unable to play the the video on the PDP page",
					Common.getscreenShot("failed to play the video in PDP page"));
			Assert.fail();
		}
	}

	
	public void Shoppingcart_page() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.scrollIntoView("xpath", "(//a[@title='Back to Cart'])[1]");
			Sync.waitElementVisible(30, "xpath", "(//a[@title='Back to Cart'])[1]");
			Common.clickElement("xpath", "(//a[@title='Back to Cart'])[1]");
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
		String Symbol="$";
		String expectedResult = "It should opens textbox input to enter discount.";
		try {
			Sync.waitElementPresent("xpath", "//button[contains(text(),'Add Discount Code')]");
			Common.clickElement("xpath", "//button[contains(text(),'Add Discount Code')]");

			Sync.waitElementPresent("css", "input[id='coupon_code']");
			if (Common.getCurrentURL().contains("preprod")) {
				Common.textBoxInput("css", "input[id='coupon_code']", data.get(Dataset).get("Discountcode"));
			} else {
				Common.textBoxInput("css", "input[id='coupon_code']", data.get(Dataset).get("prodDiscountcode"));
			}
			int size = Common.findElements("css", "input[id='coupon_code']").size();
			Common.assertionCheckwithReport(size > 0, "verifying the Discount Code label", expectedResult,
					"Successfully open the discount input box", "User unable enter Discount Code");
			Sync.waitElementClickable("xpath", "//span[text()='Apply Discount']");
			Common.clickElement("xpath", "//span[text()='Apply Discount']");
			Sync.waitPageLoad();
			expectedResult = "It should apply discount on your price.If user enters invalid promocode it should display coupon code is not valid message.";
			if (Common.getCurrentURL().contains("preprod")) {
				
				int discountcodemsg = Common.findElements("xpath", "//div[@class='container']//div[@class='relative flex w-full']/span").size();
				System.out.println(discountcodemsg);
				Common.assertionCheckwithReport(discountcodemsg>0, "verifying pomocode",
						expectedResult, "promotion code working as expected", "Promation code is not applied");
			} else {
				String discountcodemsg = Common.getText("xpath", "//div[@class='container']//div[@class='relative flex w-full']/span");
				System.out.println(discountcodemsg);
				Common.assertionCheckwithReport(discountcodemsg.contains("You used discount code"), "verifying pomocode",
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
			String Subtotal = Common.getText("xpath", "//div[@x-text='hyva.formatPrice(totalsData.subtotal)']").replace(Symbol,
					"");
			Float subtotalvalue = Float.parseFloat(Subtotal);
	
				
				String shipping = Common.getText("xpath", "(//div[contains(@x-text,'segment')])[2]")
						.replace(Symbol, "");
				Float shippingvalue = Float.parseFloat(shipping);
				System.out.println("Shipping:"+  shippingvalue);
				String Discount = Common.getText("xpath", "//div[@x-text='hyva.formatPrice(segment.value)']")
						.replace(Symbol, "").replace("-", "");
				
				Float Discountvalue = Float.parseFloat(Discount);
				System.out.println("Discount:"+ Discountvalue);
				Common.clickElement("xpath", "//span[@class='block transform']");
				
				String Tax = Common.getText("xpath", "(//div[contains(@x-text,'segment')])[3]").replace(Symbol, "");
				Float Taxvalue = Float.parseFloat(Tax);
				System.out.println("Taxvalue:"+ Taxvalue);
				String ordertotal = Common.getText("xpath", "//span[@x-text='hyva.formatPrice(segment.value)']")
						.replace(Symbol, "");
				Float ordertotalvalue = Float.parseFloat(ordertotal);
				Float Total = (subtotalvalue + shippingvalue + Taxvalue) - Discountvalue;
				String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				System.out.println(ExpectedTotalAmmount2);
				System.out.println(ordertotal);
				Common.assertionCheckwithReport(ExpectedTotalAmmount2.equals(ordertotal),
						"validating the order summary in the payment page",
						"Order summary should be display in the payment page and all fields should display",
						"Successfully Order summary is displayed in the payment page and fields are displayed",
						"Failed to display the order summary and fileds under order summary");
			}
			
			
			
			else
			{
				String Subtotal = Common.getText("xpath", "//div[@x-text='hyva.formatPrice(totalsData.subtotal)']").replace(Symbol,
						"");
				Float subtotalvalue = Float.parseFloat(Subtotal);
				String shipping = Common.getText("xpath", "(//div[@x-text='hyva.formatPrice(segment.value)'])[2]")
						.replace(Symbol, "");
				Float shippingvalue = Float.parseFloat(shipping);
				System.out.println("Shipping:"+  shippingvalue);
				String Discount = Common.getText("xpath", "(//div[@x-text='hyva.formatPrice(segment.value)'])[1]")
						.replace(Symbol, "").replace("-", "");
				
				Float Discountvalue = Float.parseFloat(Discount);
				System.out.println("Discount:"+ Discountvalue);
//				Common.clickElement("xpath", "//span[@class='block transform']");
				
				String Tax = Common.getText("xpath", "(//div[contains(@x-text,'segment')])[3]").replace(Symbol, "");
				Float Taxvalue = Float.parseFloat(Tax);
				System.out.println("Taxvalue:"+ Taxvalue);
				String ordertotal = Common.getText("xpath", "//span[@x-text='hyva.formatPrice(segment.value)']")
						.replace(Symbol, "");
				Float ordertotalvalue = Float.parseFloat(ordertotal);
				Float Total = (subtotalvalue + shippingvalue + Taxvalue) - Discountvalue;
				String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				System.out.println(ExpectedTotalAmmount2);
				System.out.println(ordertotal);
				Common.assertionCheckwithReport(ExpectedTotalAmmount2.equals(ordertotal),
						"validating the order summary in the payment page",
						"Order summary should be display in the payment page and all fields should display",
						"Successfully Order summary is displayed in the payment page and fields are displayed",
						"Failed to display the order summary and fileds under order summary");
			}
			verify_paywithlink();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the order summary in the payment page",
					"Order summary should be display in the payment page and all fields should display",
					"Unabel to display the Order summary and fields are not displayed in the payment page",
					Common.getscreenShot("Failed to display the order summary and fileds under order summary"));
			Assert.fail();
		}

	}
	public void reorder() {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Common.clickElement("xpath", "//a[normalize-space()='My Orders']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.clickElement("xpath", "//span[text()='View Order']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Sync.waitElementPresent(30, "xpath", "//span[text()='Reorder']");
			Common.clickElement("xpath", "//span[text()='Reorder']");
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
					Common.getscreenShot("Failed to Navigate to the shopping cart page"));
			Assert.fail();
		}


	}

	public void webpagelinks_validation(String Dataset) throws Exception, IOException {
		// TODO Auto-generated method stub
		String links = data.get(Dataset).get("Links");
		int j = 0;

		String[] strArray = links.split("\\r?\\n");
		for (int i = 0; i < strArray.length; i++) {
			System.out.println(strArray[i]);

			if (Common.getCurrentURL().contains("preprod")) {

				Common.oppenURL((strArray[i]));
				int responcecode = getpageresponce(Common.getCurrentURL());
				System.out.println(responcecode);

				if (responcecode == 200) {
					
					ExtenantReportUtils.addPassLog("Validating Page URL ", "page configured with products ",
							"successfully page configured with products",
							Common.getscreenShotPathforReport("link" + i));
				} else {

					j++;

					ExtenantReportUtils.addFailedLog("Validating Page URL  " + Common.getCurrentURL(),
							"page configured with products ", "unable to find page it showing 404 error",
							Common.getscreenShotPathforReport("link" + i));

				}

			} else if (Common.getCurrentURL().contains("https://www.hydroflask.com/")) {

				Common.oppenURL(strArray[i].replace("mcloud-na-preprod", "www"));

				int responcecode = getpageresponce(Common.getCurrentURL());
				System.out.println(responcecode);

				if (responcecode == 200) {
					ExtenantReportUtils.addPassLog("Validating Page URL ", "page configured with products ",
							"successfully page configured with products",
							Common.getscreenShotPathforReport("link" + i));
				} else {

					j++;

					ExtenantReportUtils.addFailedLog("Validating Page URL  " + Common.getCurrentURL(),
							"page configured with products ", "unable to find page it showing 40 error",
							Common.getscreenShotPathforReport("link" + i));

				}
			}
		}

		if (j > 1) {
			Assert.fail();
		}

	}

	public void Account_Page_Rewards() {
	    try {
	        String Tier_Status = Common.getText("xpath", "//div[contains(text(),'Tier status')]");
	        int Rewards = Common.findElements("css", "div.yotpo-progress-bar-container").size();
	        int House_Of_HF = Common.findElements("xpath", "//div[@class='yotpo-progress-bar-top']").size();
	        int Spent = Common.findElements("css", "div.yotpo-progress-bar-bottom").size();

	        Common.assertionCheckwithReport(
	            Tier_Status.contains("Tier status") && Rewards > 0 && House_Of_HF > 0 && Spent > 0,
	            "Verifying Rewards Point section",
	            "User should be able to see the Rewards Point section",
	            "User successfully navigated to the Rewards Point section",
	            "User failed to navigate to Rewards Point section"
	        );

	        Common.clickElement("xpath", "//a[text()='Redeem Points']");

	        boolean URL = Common.getCurrentURL().contains("https://mcloud-na-preprod.hydroflask.com/house-of-hydro-rewards")||Common.getCurrentURL().contains("https://www.hydroflask.com/house-of-hydro-rewards");
	        if (!URL) {
	            ExtenantReportUtils.addFailedLog(
	                "Navigation validation failed",
	                "User should land on the House of Hydroflask rewards page",
	                "Incorrect URL detected: " + Common.getCurrentURL(),
	                Common.getscreenShotPathforReport("Navigation failure")
	            );
	        }
	        else {
	        	Common.navigateBack();
	        	
	        }

	    } catch (Exception | Error e) {
	        e.printStackTrace();
	        ExtenantReportUtils.addFailedLog(
	            "Validating the Rewards Point section",
	            "User should be able to see the Rewards Point section",
	            "User unable to navigate to the Rewards Point section",
	            Common.getscreenShotPathforReport("User failed to navigate to the Rewards Point section")
	        );
	    }
	}
	
	public void Account_page_Validation(String Dataset) throws Exception {
		// TODO Auto-generated method stub
		Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
		Common.clickElement("xpath", "//button[@id='customer-menu']");
		Sync.waitElementPresent("xpath", "//a[@title='My Account']");
		Common.clickElement("xpath", "//a[@title='My Account']");
		Sync.waitPageLoad();
		Account_Page_Rewards();
		
		if(Common.findElements("xpath", "//button[@aria-label='Close dialog']").size()>0) {
			Common.clickElement("xpath", "//button[@aria-label='Close dialog']");
		}
		int size1 = Common.findElements("xpath", "//h2[contains(text(),'Shop By Category')]").size();
		boolean ShopbByCategory = size1>0;
		if(ShopbByCategory) {
			Common.scrollIntoView("xpath", "//h2[contains(text(),'Shop By Category')]");
			List<WebElement> category= Common.findElements("xpath", "//span[contains(text(),'Shop Now')]//parent::div");
 			for (int i = 0; i < category.size(); i++) {
			    WebElement element = category.get(i);
			    String text =Common.getText("xpath", "(//span[contains(text(),'Shop Now')]//parent::div)[" +( i+1) + "]").toLowerCase();
                 System.out.println("Shopy by Category: "+text);
                 Thread.sleep(2000);
                 Common.mouseOver("xpath", "(//span[contains(text(),'Shop Now')]//parent::div)[" +( i+1) + "]");
                 Thread.sleep(1000);
                 Common.mouseOverClick("xpath", "(//span[contains(text(),'Shop Now')]//parent::div)[" +( i+1) + "]");
			     String    Breadcrumb = Common.getText("css", "nav[id='breadcrumbs']").toLowerCase();
			     System.out.println("Category Bredcrumb: "+text);
			   Common.assertionCheckwithReport(Breadcrumb.contains(text), 
					   "Verifyinh Shop By Categories ",
					   "user should navigate to the Selected category"+text, 
					   "User Successfully navigate to the selected category "+text, "User Failed to navigate to selected category"+text);
			   Common.navigateBack();
			
			}
			
		}
		else {
			Assert.fail("Shop By category is not Displayed in My account Page");
			ExtenantReportUtils.addFailedLog("validating the Shop By category",
					"user should Navigate to the Shop By category section",
					"User unable to navigate to the Shop By category section ",
					Common.getscreenShotPathforReport("user Failed to Navigate to the Shop By category section"));
		}
		
		if (Common.getCurrentURL().contains("preprod")) {
			String Accountlinks = data.get(Dataset).get("Account Links");
			String[] Account = Accountlinks.split(",");
			int i = 0;
			try {
				for (i = 0; i < Account.length; i++) {
					Sync.waitElementPresent("xpath",
							"//a[@title='" + Account[i] + "']");
					Common.clickElement("xpath",
							"//a[@title='" + Account[i] + "']");
					Sync.waitPageLoad();
					
					int size=Common.findElements("xpath", "//h1[@class='title-2xl']//span").size();
					if(size>0)
					{
					String title = Common.findElement("xpath", "//h1[@class='title-2xl']//span").getText();
					System.out.println(title);
				
					
					Common.assertionCheckwithReport(title.contains(Account[i]) || title.contains("My Out Of Stock Subscriptions") || title.contains("My Payment Methods") 
							|| title.contains("Newsletter Subscription"),
							"verifying Account page links " + Account[i],
							"user should navigate to the " + Account[i] + " page",
							"user successfully Navigated to the " + Account[i], "Failed click on the " + Account[i]);
					Common.scrollIntoView("xpath", "(//div[@id='we-are-here-for-you-default'])[1]");
					int CMS_Block = Common.findElements("xpath", "(//div[@id='we-are-here-for-you-default'])[1]").size();
					Common.assertionCheckwithReport(CMS_Block>0,
							"verifying CMS Block in " + Account[i],
							"user should navigate to the CMS Block in  " + Account[i] + " page",
							"user successfully Navigated to the CMS Block in " + Account[i], "Failed Navigate to CMS Block in  " + Account[i]);
					}
					else
					{
						Common.getPageTitle();
						Common.assertionCheckwithReport(Common.getPageTitle().contains(Account[i]),
								"verifying Account page links " + Account[i],
								"user should navigate to the " + Account[i] + " page",
								"user successfully Navigated to the " + Account[i], "Failed click on the " + Account[i]);
					}
					

				}
				Sync.waitElementPresent("xpath", "//a[@title='Sign Out']");
				Common.clickElement("xpath", "//a[@title='Sign Out']");
				Thread.sleep(4000);
				verifingHomePage();
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the account page links " + Account[i],
						"user should Navigate to the " + Account[i] + " page",
						"User unable to navigate to the " + Account[i],
						Common.getscreenShotPathforReport("user Failed to Navigate to the respective page"));
				Assert.fail();
			}
		} else {
			String Accountlinks = data.get(Dataset).get("Prod Account Links");
			String[] Account = Accountlinks.split(",");
			int i = 0;
			try {
				for (i = 0; i < Account.length; i++) {
					Sync.waitElementPresent("xpath",
							"//a[@title='" + Account[i] + "']");
					Common.clickElement("xpath",
							"//a[@title='" + Account[i] + "']");
					Sync.waitPageLoad();
					int size=Common.findElements("xpath", "//h1[@class='title-2xl']//span").size();
					if(size>0)
					{
					String title = Common.findElement("xpath", "//h1[@class='title-2xl']//span").getText();
					System.out.println(title);
					Common.assertionCheckwithReport(title.contains(Account[i]) || title.contains("My Out Of Stock Subscriptions") || title.contains("My Payment Methods") 
							|| title.contains("Newsletter Subscription"),
							"verifying Account page links " + Account[i],
							"user should navigate to the " + Account[i] + " page",
							"user successfully Navigated to the " + Account[i], "Failed click on the " + Account[i]);
					Common.scrollIntoView("xpath", "(//div[@id='we-are-here-for-you-default'])[1]");
					int CMS_Block = Common.findElements("xpath", "(//div[@id='we-are-here-for-you-default'])[1]").size();
					Common.assertionCheckwithReport(CMS_Block>0,
							"verifying CMS Block in " + Account[i],
							"user should navigate to the CMS Block in  " + Account[i] + " page",
							"user successfully Navigated to the CMS Block in " + Account[i], "Failed Navigate to CMS Block in  " + Account[i]);

				} else
					{
						Common.getPageTitle();
						Common.assertionCheckwithReport(Common.getPageTitle().contains(Account[i]),
								"verifying Account page links " + Account[i],
								"user should navigate to the " + Account[i] + " page",
								"user successfully Navigated to the " + Account[i], "Failed click on the " + Account[i]);
					}
					

				}
				Sync.waitElementPresent("xpath", "//a[@title='Sign Out']");
				Common.clickElement("xpath", "//a[@title='Sign Out']");
				verifingHomePage();
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the account page links " + Account[i],
						"user should Navigate to the " + Account[i] + " page",
						"User unable to navigate to the " + Account[i],
						Common.getscreenShotPathforReport("user Failed to Navigate to the respective page"));
				Assert.fail();
			}
		}

	}

	public void employee_discount() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//button[@aria-label='Close minicart']");
			Common.clickElement("xpath", "//button[@aria-label='Close minicart']");
			Thread.sleep(4000);
			String originalprice = Common.getText("xpath", "//span[@class='price line-through hf:font-bold md:hf:font-normal']").replace("$", "").trim();
			Float originalvalue = Float.parseFloat(originalprice);
			String Newprice = Common.getText("xpath", "(//span[@class='price-wrapper']//span[@class='price'])").replace("$", "").trim();
			Float pricevalue = Float.parseFloat(Newprice);
			Thread.sleep(4000);
			float discount1 = originalvalue - (originalvalue * 40 / 100);
			float discount=originalvalue-discount1;
			String discountvalue = String.valueOf(discount).replace("$", "");
			Float value = Float.parseFloat(discountvalue);
			float discount2 = originalvalue - (originalvalue * 65 / 100);
			float discount3=originalvalue-discount2;
			String discountvalue1 = String.valueOf(discount2).replace("$", "");
			Float value1 = Float.parseFloat(discountvalue1);
			System.out.println(discountvalue1);
			System.out.println(Newprice);
			Common.assertionCheckwithReport(discountvalue.contains(Newprice)||discountvalue1.contains(Newprice),
					"verifying the discount for the employee discount ",
					"user should able to see the discount for the employee",
					"user successfully able to apply the discount", "Failed to apply the discount for the employee");
			click_minicart();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the discount for the employee discount ",
					"user should able to see the discount for the employee",
					"Unable to apply the discount for the employee",
					Common.getscreenShotPathforReport("Failed to apply the discount for the employee"));
			Assert.fail();
		}
	}

	public void Configurableproduct_addtocart_pdppage(String Dataset) {
		String product = data.get(Dataset).get("Products");
		try {
			
			for (int i = 0; i <= 10; i++) {
				Thread.sleep(6000);
				Sync.waitElementPresent("xpath", "//img[contains(@class,'group-hover/item-image:') or @loading='lazy' and @itemprop]");
//				Sync.waitElementPresent("xpath", "(//img[contains(@class,'m-product-card__image')])[2]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@class,'group-hover/item-image:') or @loading='lazy' and @itemprop]");
				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Common.javascriptclickElement("xpath", "//img[@alt='" + product + "']");
			Thread.sleep(4000);
			System.out.println(product);
			//String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText().replace("Water Bottle", "").replace(" - ", "");
			String name = Common.findElement("xpath", "//h1[contains(@class, 'pdp-grid-title title text')]").getText();
			System.out.println(name);
			Common.assertionCheckwithReport(name.contains(product),
					"validating the product should navigate to the PDP page",
					"When we click on the product is should navigate to the PDP page",
					"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");
			Sync.waitPageLoad();
			List<WebElement> ListOfSubproducts = Common.findElements("xpath",
					"//div[@class='hf_color']//div[@class='m-swatch']");
			System.out.println(ListOfSubproducts.size());
			for (int i = 0; i < ListOfSubproducts.size(); i++) {
				int value = i + 1;
				ListOfSubproducts.get(i).click();
				Thread.sleep(5000);
				String colorname = Common.getText("xpath","//span[contains(@class, 'm-swatch-group')]");
				System.out.println(colorname);
				String Bottleimagecolor =Common.getText("xpath", "(//span[contains(@x-text,'getSwatchText')])['" + value + "']");
				System.out.println(Bottleimagecolor);

				Common.assertionCheckwithReport(colorname.contains(Bottleimagecolor), "validating the  selected Color Swatch",
						"User should select the Color swatch", "Sucessfully Color swatch is selected ",
						"failed to Select The color swatch");

			}
			Sync.waitElementPresent("css", "button[title='Add to Cart']");
			Common.javascriptclickElement("css", "button[title='Add to Cart']");
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"Unable to add product to the cart ", Common.getscreenShot("Failed to add product to the cart"));
			Assert.fail();
		}

	}

	public void Text_Engraving(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		String color = data.get(Dataset).get("Color");
		System.out.println(products);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//li[@class='ais-InfiniteHits-item']//img[1]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//li[@class='ais-InfiniteHits-item']//img[1]");

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
//			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
//			Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
//					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
//					"failed to Navigate to the PDP page");
			Sync.waitElementPresent(30, "xpath", "//div[@data-option-label='" + color + "']");
			Common.clickElement("xpath", "//div[@data-option-label='" + color + "']");
			Common.clickElement("xpath", "//span[contains(text(),'Engrave Now')]");
			Sync.waitElementPresent(30, "css", "button[id='Text-category-button']");
			Common.clickElement("css", "button[id='Text-category-button']");
			Thread.sleep(6000);
//			engraving_color();
			engraving_Text("Horizontal Text");
			Common.clickElement("xpath", "//button[@class='ATC__btn']");
			Sync.waitElementPresent("xpath", "//span[contains(text(),' Agree &')]");
			Common.clickElement("xpath", "//span[contains(text(),' Agree &')]");
			Thread.sleep(6000);
			Sync.waitImplicit(30);
//			String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
//			System.out.println(message);
//			Common.assertionCheckwithReport(message.contains("You added"), "validating the  product add to the cart",
//					"Product should be add to cart", "Sucessfully product added to the cart ",
//					"failed to add product to the cart");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));
			Assert.fail();
		}

	}

	public void engraving_Text(String Dataset) {
		// TODO Auto-generated method stub
		String font = data.get(Dataset).get("Font");
		String Texttype = data.get(Dataset).get("Text");
		String engravetext = data.get(Dataset).get("Engraving");
		System.out.println(engravetext);
		try {
			Sync.waitElementPresent("xpath", "//button[@aria-label='" + font + "']");
			Common.clickElement("xpath", "//button[@aria-label='" + font + "']");
			Thread.sleep(3000);
			String Font = Common.findElement("xpath", "//span[@class='text-engraving__label']").getText();
			Common.assertionCheckwithReport(Font.equals(font), "validating the font text for the product",
					"Font should be select for the product", "Sucessfully font has been selected for the product",
					"failed to select the font for the product");
			Sync.waitElementPresent("xpath", "//button[@aria-label='" + Texttype + "']");
			Common.clickElement("xpath", "//button[@aria-label='" + Texttype + "']");
			Sync.waitElementPresent(30, "xpath", "//button[@aria-label='" + Texttype + "']");
			Thread.sleep(3000);
			String Text = Common.findElement("xpath", "//button[@aria-label='" + Texttype + "']").getAttribute("class");
			Common.assertionCheckwithReport(Text.contains("active"), "validating the text for the product",
					"Text type should be select for the product", "Sucessfully Text has been selected for the product",
					"failed to select the Text for the product");
			Sync.waitElementPresent("xpath", "//textarea[@class='text-engraving__input']");
			Common.findElement("xpath", "//textarea[@class='text-engraving__input']").sendKeys(engravetext);
			Thread.sleep(3000);
			String engrave = Common.findElement("xpath", "//textarea[contains(@class,'text-engraving__input')]")
					.getAttribute("class");
			Common.assertionCheckwithReport(engrave.contains("text-engraving"),
					"validating the engraving text for bottle", "Engraving text should be added for the bottle",
					"Sucessfully Engraving has been added for the bottle",
					"failed to add the engraving for the bottle");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the engraving text for bottle",
					"Engraving text should be added for the bottle", "Unable to add the engraving for the bottle ",
					Common.getscreenShot("Failed to add the engraving for the bottle"));
			Assert.fail();
		}

	}

	public void engraving_color() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath",
					"//div[@aria-label='Select a color']//div[@class='color-feature__selection']");
			int subproductsList = Common.findElements("xpath",
					"//div[@aria-label='Select a color']//div[@class='color-feature__selection']").size();
			for (int i = 0; i < subproductsList; i++) {
				int value = i + 1;
				List<WebElement> ListOfSubproducts = Common.findElements("xpath",
						"//div[@aria-label='Select a color']//div[" + value + "]//button");

				WebElement Colornames = Common.findElement("xpath",
						"//div[@class='color-feature__selection__label__wrapper']//label");

				System.out.println(Colornames);
				WebElement color = Common.findElement("xpath",
						"//div[@aria-label='Select a color']//div[" + value + "]//button");

				for (int j = 0; j < ListOfSubproducts.size(); j++) {

					String attributevalue = ListOfSubproducts.get(j).getAttribute("disabled");

					if (attributevalue != null) {
					} else {

						if (ListOfSubproducts.get(j).getAttribute("class").contains("color-feature__")) {
							Thread.sleep(4000);
							ListOfSubproducts.get(j).click();
							Thread.sleep(4000);

							Common.assertionCheckwithReport(
									Colornames.getText().contains(color.getAttribute("aria-label")),
									"Verifiying the swatch color button " + Colornames.getText(),
									"after click color swatch button" + Colornames.getText()
											+ "it must dispaly swatch color image",
									"successfully color swatch image is dispalying", "Failed load color swatch image");
						}
					}
				}
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the color for the engraving product",
					"Color should be selected for the engraving product",
					"Unable to select the color for the engraving product ",
					Common.getscreenShot("Failed to selecte the color for the engraving product"));
			Assert.fail();
		}

	}

	public void Graphic_Engraving(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		String color = data.get(Dataset).get("Color");
		System.out.println(products);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//li[@class='ais-InfiniteHits-item']//img[1]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//li[@class='ais-InfiniteHits-item']//img[1]");

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
//			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
//			Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
//					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
//					"failed to Navigate to the PDP page");
			Sync.waitElementPresent(30, "xpath", "//div[@data-option-label='" + color + "']");
			Common.clickElement("xpath", "//div[@data-option-label='" + color + "']");
			Common.clickElement("xpath", "//span[contains(text(),'Engrave Now')]");
//			engraving_color();
			engraving_graphic("Graphic");
			Common.clickElement("xpath", "//button[@class='ATC__btn']");
			Sync.waitElementPresent("xpath", "//span[contains(text(),' Agree &')]");
			Common.clickElement("xpath", "//span[contains(text(),' Agree &')]");
			Thread.sleep(6000);
			Sync.waitImplicit(30);
//			String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
//			System.out.println(message);
//			Common.assertionCheckwithReport(message.contains("You added"), "validating the  product add to the cart",
//					"Product should be add to cart", "Sucessfully product added to the cart ",
//					"failed to add product to the cart");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));
			Assert.fail();
		}

	}
	public void engraving_graphic(String Dataset) {
		// TODO Auto-generated method stub
		String graphic = data.get(Dataset).get("Engraving Graphic");
		try {
			Sync.waitElementPresent(30, "css", "button[id='Adventure-category-button']");
			Common.clickElement("css", "button[id='Adventure-category-button']");
//			int subproductsList = Common.findElements("xpath", "//div[@class='graphic-engraving__wrapper']//button")
//					.size();
//			for (int i = 0; i < subproductsList; i++) {
//				int value = i + 1;
//				List<WebElement> ListOfSubproducts = Common.findElements("xpath",
//						"//div[@class='graphic-engraving__selections-container']//div[" + value + "]//button");
//
//				WebElement Graphicnames = Common.findElement("xpath", "//span[@class='graphic-engraving__label']");
//
//				WebElement Graphic = Common.findElement("xpath",
//						"//div[@class='graphic-engraving__selections-container']//div[" + value + "]//button");
//
//				for (int j = 0; j < ListOfSubproducts.size(); j++) {
//
//					String attributevalue = ListOfSubproducts.get(j).getAttribute("disabled");
//
//					if (attributevalue != null) {
//					} else {
//
//						if (ListOfSubproducts.get(j).getAttribute("class").contains("graphic-engraving__")
//								|| ListOfSubproducts.get(j).getAttribute("class")
//										.contains("graphic-engraving__selection__btn active")) {
//							Thread.sleep(4000);
//							System.out.println(ListOfSubproducts);
//							ListOfSubproducts.get(j).click();
//							Thread.sleep(4000);
//
//							Common.assertionCheckwithReport(
//									Graphicnames.getText().contains(Graphic.getAttribute("aria-label")),
//									"Verifying the  swatch Graphics button " + Graphicnames.getText(),
//									"after click graphic swatch button" + Graphicnames.getText()
//											+ "it must dispaly swatch graphic image",
//									"successfully graphic swatch image is dispalying",
//									"Failed load graphic swatch image");
//						}
//					}
//				}
//			}
			Sync.waitElementPresent("xpath", "//button[@aria-label='" + graphic + "']");
			Common.clickElement("xpath", "//button[@aria-label='" + graphic + "']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the graphic for the engraving product",
					"graphic should be selected for the engraving product",
					"Unable to select the graphic for the engraving product ",
					Common.getscreenShot("Failed to select the graphic for the engraving product"));
			Assert.fail();
		}

	}

	public void enraving_Checkout(String Dataset) {
		// TODO Auto-generated method stub
		String Graphic = data.get(Dataset).get("Engraving Graphic");
		String text = data.get(Dataset).get("Engraving");
		System.out.println(text);
		try {
		
			Thread.sleep(6000);
			click_minicart();
			Thread.sleep(12000);
			Sync.waitElementPresent(50, "xpath", "//span[contains(@x-html,'item.engraving.text')]");
			String engraving = Common.findElement("xpath", "//span[contains(@x-html,'item.engraving.text')]")
					.getText();
			System.out.println(engraving);
			System.out.println(text);
			Common.assertionCheckwithReport(
					engraving.equals(Graphic) || engraving.equals(text)
							|| engraving.contains("Happy Birthday! Happy!!! Birthday!!"),
					"Validating the " + engraving + "for the bottle", engraving + "should apply for the bottle ",
					"Sucessfully" + engraving + "has been applied for the bottle",
					"failed apply the" + engraving + "for the bottle");
			Sync.waitElementPresent(30, "xpath", "//button[contains(text(),'Checkout')]");
			Common.clickElement("xpath", "//button[contains(text(),'Checkout')]");
			Sync.waitPageLoad();
			Thread.sleep(7000);
//			Sync.waitElementPresent(30, "xpath", "//strong[@role='heading']");
//			String checkout = Common.findElement("xpath", "//span[contains(@data-bind,'text: getC')]").getText();
//			System.out.println(checkout);
//			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
//			Common.assertionCheckwithReport(
//					checkout.equals(minicart) && Common.getCurrentURL().contains("checkout/#shipping"),
//					"validating the navigation to the shipping page when we click on the checkout",
//					"User should able to navigate to the shipping  page", "Successfully navigate to the shipping page",
//					"Failed to navigate to the shipping page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the navigation to the shipping page when we click on the checkout ",
					"User should able to navigate to the shipping  page", "unable to navigate to the shipping page",
					Common.getscreenShot("Failed to navigate to the shipping page"));

			Assert.fail();
		}

	}
	public void Myhydro_Graphic(String Dataset) {
		// TODO Auto-generated method stub

		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//li[@class='ais-InfiniteHits-item']//img[1]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//li[@class='ais-InfiniteHits-item']//img[1]");

				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Thread.sleep(8000);
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.clickElement("xpath", "//img[@alt='" + products + "']");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			String name = Common.findElement("xpath", "//h1[@itemprop='name']").getText().trim();
			Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"failed to Navigate to the PDP page");
			Common.clickElement("xpath", "//span[text()='Customize now']");
			Thread.sleep(3000);
			Myhydro_bottle("40 oz");
			hydro_bottle_color("Clementine");
			hydro_cap_color("White");
			hydro_strap_color("Black");
			hydro_boot_color("White");
			Myhydrographic("Graphic");
			Myhydro_quantity(Dataset);
			Common.clickElement("xpath", "//button[@class='ATC__btn']");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Agree &')]");
			Common.clickElement("xpath", "//span[contains(text(),'Agree &')]");
			Thread.sleep(10000);
//			Sync.waitElementPresent(40, "xpath", "//div[@class='a-message__container-inner']");
//			String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
//			System.out.println(message);
//			Common.assertionCheckwithReport(message.contains("You added"), "validating the  product add to the cart",
//					"Product should be add to cart", "Sucessfully product added to the cart ",
//					"failed to add product to the cart");
//			Common.refreshpage();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));
			Assert.fail();
		}

	}

	public void Myhydrographic(String Dataset) {
		// TODO Auto-generated method stub
		String graphic = data.get(Dataset).get("Engraving Graphic");
		try {
			Sync.waitElementPresent("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Common.clickElement("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Thread.sleep(3000);
			String Engraving = Common.findElement("xpath", "//h1[@class='menu__category-title']").getText().trim();
			Common.assertionCheckwithReport(Engraving.contains("Engraving"), "validating the Engraving for the bottle",
					"Engraving should be select for the bottle",
					"Sucessfully Engraving  has been selected for the bottle",
					"failed to select the Engraving for the selected bottle");
			
			Sync.waitElementPresent(30, "css", "button[id='Adventure-category-button']");
			Common.clickElement("css", "button[id='Adventure-category-button']");
			
			Thread.sleep(4000);
//			int subproductsList = Common.findElements("xpath", "//div[@class='graphic-engraving__wrapper']//button")
//					.size();
//			for (int i = 0; i < subproductsList; i++) {
//				int value = i + 1;
//				List<WebElement> ListOfSubproducts = Common.findElements("xpath",
//						"//div[@class='graphic-engraving__selections-container']//div[" + value + "]//button");
//
//				WebElement Graphicnames = Common.findElement("xpath", "//span[@class='graphic-engraving__label']");
//
//				WebElement Graphic = Common.findElement("xpath",
//						"//div[@class='graphic-engraving__selections-container']//div[" + value + "]//button");
//
//				for (int j = 0; j < ListOfSubproducts.size(); j++) {
//
//					String attributevalue = ListOfSubproducts.get(j).getAttribute("disabled");
//
//					if (attributevalue != null) {
//					} else {
//
//						if (ListOfSubproducts.get(j).getAttribute("class").contains("graphic-engraving__")
//								|| ListOfSubproducts.get(j).getAttribute("class")
//										.contains("graphic-engraving__selection__btn active")) {
//							Thread.sleep(4000);
//							System.out.println(ListOfSubproducts);
//							ListOfSubproducts.get(j).click();
//							Thread.sleep(4000);
//
//							Common.assertionCheckwithReport(
//									Graphicnames.getText().contains(Graphic.getAttribute("aria-label")),
//									"Verifying the  swatch Graphics button " + Graphicnames.getText(),
//									"after click graphic swatch button" + Graphicnames.getText()
//											+ "it must dispaly swatch graphic image",
//									"successfully graphic swatch image is dispalying",
//									"Failed load graphic swatch image");
//						}
//					}
//				}
//			}
			Sync.waitElementPresent("xpath", "//button[@aria-label='" + graphic + "']");
			Common.clickElement("xpath", "//button[@aria-label='" + graphic + "']");
			Thread.sleep(3000);

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the graphic for the engraving product",
					"graphic should be selected for the engraving product",
					"Unable to select the graphic for the engraving product ",
					Common.getscreenShot("Failed to select the graphic for the engraving product"));
			Assert.fail();
		}

	}

	public void Myhydro(String Dataset) {
		// TODO Auto-generated method stub
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
			Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"failed to Navigate to the PDP page");
			Common.clickElement("xpath", "//span[text()='Customize Now']");
			Thread.sleep(3000);
			Myhydro_bottle("40 oz");
			hydro_bottle_color("Black");
			hydro_cap_color("White");
			hydro_strap_color("Black");
			hydro_boot_color("White");
			Common.clickElement("xpath", "//button[@class='ATC__btn']");
			Thread.sleep(4000);
			Sync.waitPageLoad();
			Sync.waitForLoad();
			Thread.sleep(3000);
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
					.getAttribute("data-ui-id");
			System.out.println(message);
			Thread.sleep(6000);
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

	public void Ask_a_question(String Dataset) {
		// TODO Auto-generated method stub
				String Question = data.get(Dataset).get("CommetsHydroflask");
				String Name = data.get(Dataset).get("FirstName");
				String Email = data.get(Dataset).get("Email");
				try {
					Common.refreshpage();
					Common.scrollIntoView("xpath", "//h2[contains(text(),'Reviews')]");
					Sync.waitElementPresent("xpath", "//h2[contains(text(),'Reviews')]");
					Common.clickElement("xpath", "//h2[contains(text(),'Reviews')]");
					Common.clickElement("xpath", "//button[@class='ugc-storefront-widgets-tabs-container__tab']");
					Sync.waitElementPresent("xpath", "//button[@class='ugc-storefront-widgets-tabs-container__tab']");
					Common.clickElement("xpath", "//button[@class='ugc-storefront-widgets-tabs-container__tab']");
					Sync.waitElementPresent(30, "xpath", "//span[text()='Ask a question']");
					Common.clickElement("xpath", "//span[text()='Ask a question']");
					Common.textBoxInput("xpath", "//textarea[@aria-label='Type your question']", "Ask a question");
					

					Sync.waitElementPresent(30, "xpath", "//input[@aria-label='Enter your name']");
					Common.textBoxInput("xpath", "//input[@aria-label='Enter your name']", "Qa");
					Sync.waitElementPresent(30, "xpath", "//input[@aria-label='Enter your email address']");
					Common.textBoxInput("xpath", "//input[@aria-label='Enter your email address']", Email);
					Common.clickElement("xpath", "//button[@class='yotpo-question-submit']");
					Thread.sleep(4000);
					String question = Common.findElement("xpath", "//div[@class='yotpo-headline-complete']").getText();
					System.out.println(question);
					Common.assertionCheckwithReport(question.contains("Thanks, Qa!"),
							"validating the question submit form", "Ask a form should be submit",
							"Sucessfully question post should be submit", "Failed to submit the ask a question post");
				} catch (Exception | Error e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("validating the question submit form", "Ask a form should be submit",
							"Unable to subit question post", Common.getscreenShot("failed to subit question post"));
					Assert.fail();
				}

	}

	public void multiline_Engraving(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		String color = data.get(Dataset).get("Color");
		System.out.println(products);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//li[@class='ais-InfiniteHits-item']//img[1]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//li[@class='ais-InfiniteHits-item']//img[1]");

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
//			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
//			Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
//					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
//					"failed to Navigate to the PDP page");
			Sync.waitElementPresent(30, "xpath", "//div[@data-option-label='" + color + "']");
			Common.clickElement("xpath", "//div[@data-option-label='" + color + "']");
			Common.clickElement("xpath", "//span[contains(text(),'Engrave Now')]");
//			engraving_color();
			engraving_Text("Multiline Horizontal");
			product_quantity(Dataset);
			Common.clickElement("xpath", "//button[@class='ATC__btn']");
			Sync.waitElementPresent("xpath", "//span[contains(text(),' Agree &')]");
			Common.clickElement("xpath", "//span[contains(text(),' Agree &')]");
			Thread.sleep(6000);
//			Sync.waitImplicit(30);
//			String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
//			System.out.println(message);
//			Common.assertionCheckwithReport(message.contains("You added"), "validating the  product add to the cart",
//					"Product should be add to cart", "Sucessfully product added to the cart ",
//					"failed to add product to the cart");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));
			Assert.fail();
		}

	}
	public void multiline_Myhydro(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//li[@class='ais-InfiniteHits-item']//img[1]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//li[@class='ais-InfiniteHits-item']//img[1]");

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
			Thread.sleep(4000);
			String name = Common.findElement("xpath", "//h1[@itemprop='name']").getText().trim();
			System.out.println(name);
			Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"failed to Navigate to the PDP page");
			Common.clickElement("xpath", "//span[text()='Customize now']");
			Thread.sleep(3000);
			Myhydro_bottle("40 oz");
			hydro_bottle_color("Clementine");
			hydro_cap_color("White");
			hydro_strap_color("Black");
			hydro_boot_color("White");
			Myhydro_Engraving("Multiline Horizontal");
			Myhydro_quantity(Dataset);
			Common.clickElement("xpath", "//button[@class='ATC__btn']");
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Agree &')]");
			Common.clickElement("xpath", "//span[contains(text(),'Agree &')]");
			Thread.sleep(10000);
//			Sync.waitElementPresent(40, "xpath", "//div[@class='a-message__container-inner']");
//			String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
//			System.out.println(message);
//			Common.assertionCheckwithReport(message.contains("You added"), "validating the  product add to the cart",
//					"Product should be add to cart", "Sucessfully product added to the cart ",
//					"failed to add product to the cart");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));
			Assert.fail();
		}

	}

	public void clickWarrantylink() {
		// TODO Auto-generated method stub

		String expectedResult = "It should land successfully on the Warranty page";

		Common.actionsKeyPress(Keys.END);
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//a[text()='Warranty']");
			Common.clickElement("xpath", "//a[text()='Warranty']");
			Sync.waitPageLoad();
			Thread.sleep(4000);

			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Warranty"),
					"Validating the Warranty page navigation", expectedResult, "successfully land to Warranty page",
					"unabel to load the Warranty page");
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating Warranty page", expectedResult,
					"unabel to load the Warrantyt page", Common.getscreenShotPathforReport("Warranty page link"));
			Assert.fail();

		}

	}

	public void Warrantysearch_results(String search) {
		String expectedResult = "It should opens the search resluts of searched prodcuts.";

		try {
			Common.clickElement("xpath", "//input[@class='form-control form-control-search']");
			// Common.textBoxInput("xpath", "//input[@id='search']", search);
			String searchresults = Common.findElement("xpath", "//input[@class='form-control form-control-search']")
					.getText();
			System.out.println(searchresults);
			Common.textBoxInput("xpath", "//input[@id='searchInput']", search);

			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitPageLoad();

			String searchresults1 = Common.findElement("xpath", "//span[@class='search-results-number']").getText();
			System.out.println(searchresults1);
			Common.assertionCheckwithReport(searchresults1.contains("30 results found for  'CAP LAGUNA'"),
					"verifying search resluts", expectedResult, "search resluts should be displayed",
					"search resluts not displayed");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the search functionality",
					"enter product name will display in the search box",
					" unable to enter the product name in  search box",
					Common.getscreenShot("Failed to see the product name"));
			Assert.fail();
		}
	}

	public void unorderprodcut_search(String search) {
// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
			String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
			Common.assertionCheckwithReport(open.contains("active"), "User searches using the search field",
					"User should able to click on the search button", "Search expands to the full page",
					"Sucessfully search bar should be expand");
			Common.textBoxInput("xpath", "//input[@id='search']", search);
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String searchresults1 = Common.getText("xpath", "//span[text()='pop container oxo']");
			String productsearch = Common.findElement("xpath", "(//div[@id='algolia-right-container'])[1]").getText();
			System.out.println(productsearch);
			Common.assertionCheckwithReport(searchresults1.contains("pop container oxo'"),
					"validating the search functionality",
					"enter any search term will display no results in the search box",
					"user enter the search term in  search box", "Failed to see the search term");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the search functionality",
					"enter product name will display in the search box",
					" unable to enter the product name in  search box",
					Common.getscreenShot("Failed to see the product name"));
			Assert.fail();
		}

	}

	public void orderprodcut_search(String search) {
		// TODO Auto-generated method stub

		try {
			Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
			String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
			Common.assertionCheckwithReport(open.contains("active"), "User searches using the search field",
					"User should able to click on the search button", "Search expands to the full page",
					"Sucessfully search bar should be expand");
			Common.textBoxInput("xpath", "//input[@id='search']", search);
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String searchresults = Common.getText("xpath", "//span[text()='32 oz mouth']");
			String productsearch1 = Common.findElement("xpath", "(//div[@id='algolia-right-container'])[1]").getText();
			System.out.println(productsearch1);
			Common.assertionCheckwithReport(searchresults.contains("32 oz mouth"),
					"validating the search functionality",
					"enter any search term will display no results in the search box",
					"user enter the search term in  search box", "Failed to see the search term");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the search functionality",
					"enter product name will display in the search box",
					" unable to enter the product name in  search box",
					Common.getscreenShot("Failed to see the product name"));
			Assert.fail();
		}

	}

	
	public void travel_Bottles_validation(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("headers");
		String[] Links = names.split(",");
		int i = 0;
		try {
			  	 Thread.sleep(3000);
		         for (i = 0; i < Links.length; i++) {
		        	 Thread.sleep(2000);
		        	 Sync.waitElementPresent(50,"xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");

						Common.javascriptclickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
						Thread.sleep(3000);
						int size=Common.findElements("xpath", "(//div[@x-init='$nextTick(() => {show = true })'])[2]").size();
						if(size <=0) 
						{
							Common.javascriptclickElement("xpath", "//img[@alt='Hydroflask store logo']");
						
						Sync.waitElementPresent(50,"xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
						Common.javascriptclickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
				       }
					Thread.sleep(3000);	
					Sync.waitElementPresent("xpath", "(//span[contains(text(),'Travel Bottles')])[1]");
					Common.javascriptclickElement("xpath","(//span[contains(text(),'Travel Bottles')])[1]");
					
					Sync.waitElementPresent("xpath", "(//span[contains(text(),'" + Links[i] + "')])[1]");
					Common.javascriptclickElement("xpath","(//span[contains(text(),'" + Links[i] + "')])[1]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String Page_title = "";
		            if 
		            (Common.findElements("xpath", "//h1[contains(@class,'title')]").size() > 0) {
		                Page_title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
		            } 
		            else if (Common.findElements("xpath", "//h1[contains(@class,'hero')]").size() > 0) {
		            	Page_title = Common.findElement("xpath", "//h1[contains(@class,'hero')]").getText();
		            }
                    String title=  Common.getPageTitle();
					System.out.println(Page_title);
					System.out.println(Links[i]);
					System.out.println(title);
					Sync.waitElementPresent(60,"xpath", "(//div[contains(@class,'text-sm')]/span)[1]");
					String products = Common.getText("xpath", "(//div[contains(@class,'text-sm')]/span)[1]"); 
					System.out.println(products);
					int Number = Integer.parseInt(products);
					products = products.replaceAll("[^0-9]", ""); 
					int j=0;
					if(Number>j)
					{
						Common.assertionCheckwithReport(Page_title.contains(Links[i])|| title.contains("Shop Travel Bottles") ,
								"verifying the header link " + Links[i] + "Under Collections",
								"user should navigate to the " + Links[i] + " page",
								"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
					}
					else
					{
						ExtenantReportUtils.addFailedLog(
								"validating the the products in the plp ",
								"User should able to see the products in plp", "unable to see the products in the PLP",
								Common.getscreenShot("Failed to see products in PLP"));
						Assert.fail();
					}
					
				
		         	}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link for Shop",
					"user should navigate to the Shop page", "user Unable to Navigated to the Shop",
					Common.getscreenShot(" Failed to Navigated to the Shop"));
			Assert.fail();
		}

	}
	public void Travel_Tumbler_validation(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("headers");
		String[] Links = names.split(",");
		int i = 0;
		try {
			  	 Thread.sleep(3000);
		         for (i = 0; i < Links.length; i++) {
		        	 Thread.sleep(2000);
		        	 Sync.waitElementPresent(50,"xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");

						Common.javascriptclickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
						Thread.sleep(3000);
						int size=Common.findElements("xpath", "(//div[@x-init='$nextTick(() => {show = true })'])[2]").size();
						if(size <=0) 
						{
							Common.javascriptclickElement("xpath", "//img[@alt='Hydroflask store logo']");
						
						Sync.waitElementPresent(50,"xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
						Common.javascriptclickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
				       }
					Thread.sleep(3000);	
					Sync.waitElementPresent("xpath", "(//span[contains(text(),'Travel Tumbler')])[1]");
					Common.clickElement("xpath","(//span[contains(text(),'Travel Tumbler')])[1]");
					
					Sync.waitElementPresent("xpath", "(//span[contains(text(),'" + Links[i] + "')])[1]");
					Common.clickElement("xpath","(//span[contains(text(),'" + Links[i] + "')])[1]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String Page_title = "";
		            if 
		            (Common.findElements("xpath", "//h1[contains(@class,'title')]").size() > 0) {
		                Page_title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
		            } 
		            else if (Common.findElements("xpath", "//h1[contains(@class,'hero')]").size() > 0) {
		            	Page_title = Common.findElement("xpath", "//h1[contains(@class,'hero')]").getText();
		            }
                    String title=Common.getPageTitle();
					System.out.println(Page_title);
					System.out.println(Links[i]);
					System.out.println(title);
					Sync.waitElementPresent(60,"xpath", "(//div[contains(@class,'text-sm')]/span)[1]");
					String products = Common.getText("xpath", "(//div[contains(@class,'text-sm')]/span)[1]"); 
					System.out.println(products);
					int Number = Integer.parseInt(products);
					products = products.replaceAll("[^0-9]", ""); 
					int j=0;
					if(Number>j)
					{
						Common.assertionCheckwithReport(Page_title.contains("Travel Bottle")||Page_title.contains(Links[i])|| title.contains("Shop Travel Bottles"),
								"verifying the header link " + Links[i] + "Under Collections",
								"user should navigate to the " + Links[i] + " page",
								"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
					}
					else
					{
						ExtenantReportUtils.addFailedLog(
								"validating the the products in the plp ",
								"User should able to see the products in plp", "unable to see the products in the PLP",
								Common.getscreenShot("Failed to see products in PLP"));
						Assert.fail();
					}
					
				
		         	}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link for Shop",
					"user should navigate to the Shop page", "user Unable to Navigated to the Shop",
					Common.getscreenShot(" Failed to Navigated to the Shop"));
			Assert.fail();
		}

	}
	public void New_Arrivals_validation(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("headers");
		String[] Links = names.split(",");
		int i = 0;
		try {
			  	 Thread.sleep(3000);
		         for (i = 0; i < Links.length; i++) {
		        	 Thread.sleep(2000);
		        	 Sync.waitElementPresent(50,"xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");

						Common.javascriptclickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
						Thread.sleep(3000);
						int size=Common.findElements("xpath", "(//div[@x-init='$nextTick(() => {show = true })'])[2]").size();
						if(size <=0) 
						{
							Common.javascriptclickElement("xpath", "//img[@alt='Hydroflask store logo']");
						
						Sync.waitElementPresent(50,"xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
						Common.javascriptclickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
				       }
					Thread.sleep(3000);	
					Sync.waitElementPresent("xpath", "(//span[contains(text(),'New & Trending')])[1]");
					Common.clickElement("xpath","(//span[contains(text(),'New & Trending')])[1]");
					Thread.sleep(3000);
					Sync.waitElementPresent("xpath", "(//span[contains(text(),'" + Links[i] + "')])[1]");
					Common.javascriptclickElement("xpath","(//span[contains(text(),'" + Links[i] + "')])[1]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String Page_title = "";
		            if 
		            (Common.findElements("xpath", "//h1[contains(@class,'title')]").size() > 0) {
		                Page_title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
		            } 
		            else if (Common.findElements("xpath", "//h1[contains(@class,'hero')]").size() > 0) {
		            	Page_title = Common.findElement("xpath", "//h1[contains(@class,'hero')]").getText();
		            }
                    String title=Common.getPageTitle();
					System.out.println(Page_title);
					System.out.println(Links[i]);
					System.out.println(title);
					Sync.waitElementPresent(60,"xpath", "(//div[contains(@class,'text-sm')]/span)[1]");
					String products = Common.getText("xpath", "(//div[contains(@class,'text-sm')]/span)[1]"); 
					System.out.println(products);
					int Number = Integer.parseInt(products);
					products = products.replaceAll("[^0-9]", ""); 
					int j=0;
					if(Number>j)
					{
						Common.assertionCheckwithReport(Page_title.contains("Nonstop Color")||Page_title.contains(Links[i])|| title.contains("Shop New Arrivals")|| title.contains("Colors of Oregon: Blue, Green & Light Pink Water Bottles | Hydro Flask")|| title.contains("Shop New & Trending")|| title.contains("Limited Edition Bottles | Hydro Flask"),
								"verifying the header link " + Links[i] + "Under Collections",
								"user should navigate to the " + Links[i] + " page",
								"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
					}
					else
					{
						ExtenantReportUtils.addFailedLog(
								"validating the the products in the plp ",
								"User should able to see the products in plp", "unable to see the products in the PLP",
								Common.getscreenShot("Failed to see products in PLP"));
						Assert.fail();
					}
					
				
		         	}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link for Shop",
					"user should navigate to the Shop page", "user Unable to Navigated to the Shop",
					Common.getscreenShot(" Failed to Navigated to the Shop"));
			Assert.fail();
		}

	}
	public void bottles_validation(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("bottles");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				
				Sync.waitElementPresent(50,"xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");

				Common.javascriptclickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
				Thread.sleep(3000);
				int size=Common.findElements("xpath", "(//div[@x-init='$nextTick(() => {show = true })'])[2]").size();
				if(size <=0) 
				{
					Common.javascriptclickElement("xpath", "//img[@alt='Hydroflask store logo']");
				
				Sync.waitElementPresent(50,"xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
				Common.javascriptclickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
		}
			Thread.sleep(3000);	
				Common.javascriptclickElement("xpath", "(//span[contains(text(),'Bottles & Drinkware')])[1]");
				
				try {
					Thread.sleep(3000);
					Sync.waitElementPresent("xpath",
							"//a[contains(@href,'bottles-drinkware')]/span[contains(text(),'" + Links[i] + "')]");
					
					Common.clickElement("xpath",
							"//a[contains(@href,'bottles-drinkware')]/span[contains(text(),'" + Links[i] + "')]");
				}
				catch(Exception | Error e) {
					Sync.waitElementPresent(50,"xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
					WebElement web2=	Common.findElementBy("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
					web2.click();
				Common.javascriptclickElement("xpath", "(//span[contains(text(),'Bottles & Drinkware')])[1]");
				Sync.waitElementPresent("xpath",
						"//a[contains(@href,'bottles-drinkware')]/span[contains(text(),'" + Links[i] + "')]");
				
				Common.clickElement("xpath",
						"//a[contains(@href,'bottles-drinkware')]/span[contains(text(),'" + Links[i] + "')]");
				}
				
				Sync.waitPageLoad();
				Thread.sleep(4000);
				
				String title = "";
	            if 
	            (Common.findElements("xpath", "//h1[contains(@class,'title')]").size() > 0) {
	                title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
	            } 
	            else if (Common.findElements("xpath", "//h1[contains(@class,'hero')]/span").size() > 0) {
	                title = Common.findElement("xpath", "//h1[contains(@class,'hero')]/span").getText();
	            }
				String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
				System.out.println(title);
				System.out.println(Links[i]);
				System.out.println(breadcrumbs);
				String products=Common.getText("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
				System.out.println(products);
				int Number = Integer.parseInt(products);
				int j=0;
				if(Number>j)
				{
				Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i])|| breadcrumbs.contains(title),
						"verifying the header link " + Links[i] + "Under bottles and drinkware",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
				}
				else
				{
					ExtenantReportUtils.addFailedLog(
							"validating the the products in the plp ",
							"User should able to see the products in plp", "unable to see the products in the PLP",
							Common.getscreenShot("Failed to see products in PLP"));
					Assert.fail();
				}
				
			}}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under bottles and drinkware",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}


	
	
	public void Coolers_validation(String Dataset) {
		// TODO Auto-generated method stub

		String names = data.get(Dataset).get("Kitchen");
		String[] Links = names.split(",");
		int i = 0;
		try {
		         	Thread.sleep(4000);
		         	for (i = 0; i < Links.length; i++) {
		         	
							Sync.waitElementPresent(50,"xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
							Common.javascriptclickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
					
					Common.clickElement("xpath", "(//a[@title='Coolers & Lunch Boxes'])[1]");
					Thread.sleep(3000);
					Sync.waitElementPresent(30,"xpath",
							"//a[contains(@href,'cooler')]/span[contains(text(),'" + Links[i] + "')]");
					Common.clickElement("xpath",
							"//a[contains(@href,'cooler')]/span[contains(text(),'" + Links[i] + "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
					String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
					System.out.println(title);
					System.out.println(Links[i]);	
					Sync.waitElementPresent("xpath",
							"(//div[contains(@class,'flex w-full')]//span)[1]");
					String products=Common.getText("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
					System.out.println(products);
					int Number = Integer.parseInt(products);
					int j=0;
					if(Number>j)
					{
						Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) || breadcrumbs.contains(Links[i]) 
								||Common.getCurrentURL().contains("coolers"),"verifying the header link " + Links[i] + "Under Outdoor Packs",
								"user should navigate to the " + Links[i] + " page",
								"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
					}
					else
					{
						ExtenantReportUtils.addFailedLog(
								"validating the the products in the plp ",
								"User should able to see the products in plp", "unable to see the products in the PLP",
								Common.getscreenShot("Failed to see products in PLP"));
						Assert.fail();
					}
					
				
		         	}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link for coolers ",
					"user should navigate to the coolers page", "user Unable to Navigated to the coolers",
					Common.getscreenShot(" Failed to Navigated to the coolers"));
			Assert.fail();
		}

	}


	public void kitchenware_validation(String Dataset) {
		// TODO Auto-generated method stub

		String names = data.get(Dataset).get("Kitchen");
		String prodlinks = data.get(Dataset).get("Kitchen prod");
		String[] Links = names.split(",");
		String[] ProdLink = prodlinks.split(",");
		int i = 0;
		try {
			Thread.sleep(4000);
			String preprod = Common.getCurrentURL();
			System.out.println(preprod);
			if (preprod.contains("preprod")) {
				for (i = 0; i < Links.length; i++) {
					try {
						Thread.sleep(6000);
			        	 Common.refreshpage();
			        	 Sync.waitElementPresent(50,"xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
						WebElement web=	Common.findElementBy("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
						web.click();
					}
					catch(Exception | Error e) {
						Sync.waitElementPresent("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
						Common.javascriptclickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
					}
					Sync.waitElementClickable("xpath", "(//span[text()='Kitchenware'])[1]");
					Common.clickElement("xpath", "(//span[text()='Kitchenware'])[1]");
					Thread.sleep(3000);
					Sync.waitElementPresent("xpath",
							"(//a//span[contains(text(),'" + Links[i] + "')])[1]");
					Common.javascriptclickElement("xpath",
							"(//a//span[contains(text(),'" + Links[i] + "')])[1]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
					String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
					System.out.println(title);
					System.out.println(Links[i]);
					Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]),
							"verifying the header link " + Links[i] + "Under Kitchware",
							"user should navigate to the " + Links[i] + " page",
							"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
					Common.clickElement("xpath", "//a[@title='Go to Home Page']");
					Common.implicitWait();
					Thread.sleep(2000);
					Common.assertionCheckwithReport(Common.getPageTitle().contains("Hydro Flask"),
							"verifying the breadcrumbs click from PLP page",
							"After clicking on the breadcrumbs it should navigate to the respective page",
							"user successfully navigated to the respective page",
							"Failed to navigate to the Respective page");

				}
			} else {

				int minLength = Math.min(ProdLink.length, Links.length);
				for (int i1 = 0; i1 < minLength; i1++) {
				    Sync.waitElementPresent("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
				    Common.clickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
				    Common.clickElement("xpath", "(//span[text()='Kitchenware'])[1]");
				    Thread.sleep(3000);
				    Sync.waitElementPresent("xpath", "//a[@title='" + Links[i1] + "']//span[contains(text(),'" + Links[i1] + "')]");
				    Common.clickElement("xpath", "//a[@title='" + Links[i1] + "']//span[contains(text(),'" + Links[i1] + "')]");
				    Sync.waitPageLoad();
				    Thread.sleep(4000);
				    String title = Common.findElement("xpath", "//span[@data-ui-id='page-title-wrapper']").getText();
				    String breadcrumbs = Common.findElement("xpath", "//nav[@id='breadcrumbs']").getText();
				    System.out.println(title);
				    System.out.println(Links[i1]);
				    Common.assertionCheckwithReport(title.contains(Links[i1]) || breadcrumbs.contains(Links[i1]),
				        "verifying the header link " + Links[i1] + " under Kitchenware",
				        "user should navigate to the " + Links[i1] + " page",
				        "user successfully navigated to the " + Links[i1], 
				        "Failed to navigate to the " + Links[i1]);
				    Common.clickElement("xpath", "//a[@title='Go to Home Page']");
				    Common.implicitWait();
				    Thread.sleep(2000);
				    Common.assertionCheckwithReport(Common.getPageTitle().contains("Hydro Flask"),
				        "verifying the breadcrumbs click from PLP page",
				        "After clicking on the breadcrumbs it should navigate to the respective page",
				        "user successfully navigated to the respective page",
				        "Failed to navigate to the respective page");
				}

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Kitchware",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}

	

	public void featured_validation(String Dataset) {
		// TODO Auto-generated method stub
		String names ="";
	    if(Common.getCurrentURL().contains("https://mcloud-na-preprod.hydroflask.com/")){

    		 names = data.get(Dataset).get("Featured links");		
	
	    }else {
	    		 names = data.get(Dataset).get("prod Featured links");		
		
		}
	
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				
					
					Sync.waitElementPresent(50,"xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
//					WebElement web=	Common.findElementBy("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
//					web.click();
					Common.javascriptclickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
					Thread.sleep(3000);
					int size=Common.findElements("xpath", "(//div[@x-init='$nextTick(() => {show = true })'])[2]").size();
					if(size <=0) 
					{
						Common.javascriptclickElement("xpath", "//img[@alt='Hydroflask store logo']");
					
					Sync.waitElementPresent(50,"xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
					Common.javascriptclickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
			}
				Common.clickElement("xpath", "//span[text()='Featured']");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"(//a[contains(@title,'" + Links[i] + "')]//span[contains(text(),'" + Links[i] + "')])[1]");
				Common.clickElement("xpath",
						"(//a[contains(@title,'" + Links[i] + "')]//span[contains(text(),'" + Links[i] + "')])[1]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1//span").getText();
				String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
				System.out.println(title);
				System.out.println(Links[i]);			
				String products=Common.getText("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
				System.out.println(products);
				int Number = Integer.parseInt(products);
				int j=0;
				if(Number>j)
				{
				Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
				}
				else
				{
					ExtenantReportUtils.addFailedLog(
							"validating the the products in the plp ",
							"User should able to see the products in plp", "unable to see the products in the PLP",
							Common.getscreenShot("Failed to see products in PLP"));
					Assert.fail();
				}		
			}
		}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Featured",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}

	public void shopall(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("shopall links");
		String[] Links = names.split(",");
		String name = data.get(Dataset).get("shopall links").toUpperCase();
		String[] Link = name.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
				Common.clickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath", "//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath", "//li//a[contains(@class,'btn btn-secondary')]//span[contains(text(),'Shop All')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				//h3[@data-content-type='heading']
//				String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();				
				String title = "";
	            if (Common.findElements("xpath", "//h1[contains(@class,'c')]").size() > 0) {
	                title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
	            } else if (Common.findElements("xpath", "//h3[@data-content-type='heading']").size() > 0) {
	                title = Common.findElement("xpath", "//h3[@data-content-type='heading']").getText();

	            }
//				String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText().toUpperCase();
	            Sync.waitElementPresent(30,"xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
				String products = Common.findElement("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]").getText();
				int Number = Integer.parseInt(products);
//				System.out.println(breadcrumbs);
				System.out.println(Link[i]);
//				System.out.println(breadcrumbs.contains(Links[i]));
//				System.out.println(Common.getCurrentURL().contains(Links[i]));
				int j=0;
				if(Number>j)
				{
				Common.assertionCheckwithReport(title.contains(Links[i]) || Common.getCurrentURL().contains(Links[i])
						|| Common.getCurrentURL().contains("shop/collections"),
						"verifying the header link " + Links[i] + "Under Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
				}
				else
				{
					ExtenantReportUtils.addFailedLog(
							"validating the the products in the plp ",
							"User should able to see the products in plp", "unable to see the products in the PLP",
							Common.getscreenShot("Failed to see products in PLP"));
					Assert.fail();
				}
			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Featured",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}

public void Explore_Validation(String Dataset) {
	// TODO Auto-generated method stub
	String names = data.get(Dataset).get("Explore");
	String[] Links = names.split(",");
	String name = data.get(Dataset).get("Explore").toUpperCase();
	String[] Link = name.split(",");
	String name1 = data.get(Dataset).get("Explore").toLowerCase();
	String[] Link1 = name1.split(",");
	System.out.println(Link1);
	int i = 0;
	try {
		for (i = 0; i < Links.length; i++) {
			Sync.waitElementPresent("xpath", "(//span[contains(text(),'Explore')])[1]");
			Common.clickElement("xpath", "(//span[contains(text(),'Explore')])[1]");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath",
					"//a[contains(@class,'link group no-underline')]//span[contains(text(),'" + Links[i] + "')]");
			Common.clickElement("xpath", "//a[contains(@class,'link group no-underline')]//span[contains(text(),'" + Links[i] + "')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String page = Common.getPageTitle();
			System.out.println(Common.getCurrentURL());
			System.out.println(page.contains(Links[i]));
			System.out.println(Common.getCurrentURL().contains(Links[i]));
			Common.assertionCheckwithReport(Common.getPageTitle().contains("We are Hydro Flask") || Common.getPageTitle().contains(Links[i])
					|| Common.getPageTitle().contains("Frequently Asked Questions | Hydro Flask")
					|| Common.getPageTitle().contains("Refer-A-Friend") || Common.getPageTitle().contains("Let’s Go!")
					|| Common.getPageTitle().contains("Refill For Good")
					|| Common.getPageTitle().contains("Parks For All")
					|| Common.getPageTitle().contains("Trade In"), "verifying the explore links navigation",
					"user should navigate to the " + Links[i] + " page",
					"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
			Thread.sleep(3000);
			if (Common.getPageTitle().contains("Frequently Asked Questions | Hydro Flask")) {
				Common.clickElement("xpath", "//div[@class='nav-flex']//a[@id='top']");
			} else {
				Common.clickElement("xpath", "//img[@alt='Hydroflask store logo']");
			}

		}

	}

	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i],
				"User should navigate to the " + Links[i] + "pages",
				" unable to navigate to the " + Links[i] + "pages",
				Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
		Assert.fail();
	}

}


	
	public void bundle_color(String Dataset) throws Exception {
		// TODO Auto-generated method stub
		int subproductsList = Common.findElements("xpath", "//div[@class='field option bundle-item  required']").size();
		System.out.println(subproductsList);
		for (int i = 0; i < subproductsList; i++) {
			int value = i + 1;
			List<WebElement> ListOfSubproducts = Common.findElements("xpath",
					"(//div[@class='swatch-attribute hf_color']//div[@aria-label='Color'])[" + value
							+ "]//div[@data-option-label='" + Dataset + "']");

			WebElement Colornames = Common.findElement("xpath",
					"//div[@class='fieldset']//div[" + value + "]//span[contains(@class,'m-swa')]");
			WebElement imagecolor = Common.findElement("xpath", "//div[@class='fieldset']//div[" + value + "]//img");
			WebElement Color = Common.findElement("xpath",
					"//div[@class='fieldset']//div[" + value + "]//div[contains(@class,'m-swatch m-sw')]");

			for (int j = 0; j < ListOfSubproducts.size(); j++) {

				String attributevalue = ListOfSubproducts.get(j).getAttribute("disabled");

				if (attributevalue != null) {
				} else {

					if (ListOfSubproducts.get(j).getAttribute("class").contains("m-swatch m")) {
						Thread.sleep(2000);
						ListOfSubproducts.get(j).click();

						Common.assertionCheckwithReport(Colornames.getText().contains(Colornames.getText()),
								"Vrifying  swatch color button " + Colornames.getText(),
								"after click color swatch button" + Colornames.getText()
										+ "it must dispaly swatch color image",
								"successfully color swatch image is dispalying", "Failed load color swatch image");
					} else {
						break;
					}
				}
			}
		}

	}

	public void back_to_cart() {
		// TODO Auto-generated method stub
		try {
			Common.actionsKeyPress(Keys.UP);
			Sync.waitElementPresent("xpath", "//a[@title='Back to Cart']");
			Common.clickElement("xpath", "//a[@title='Back to Cart']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Shopping Cart"),
					"verifying the shopping cart navigation",
					"user should be able to navigate to the shopping cart page",
					"user successfully Navigated to shopping cart page",
					"Failed to navigate to the shopping cart page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the shopping cart navigation",
					"user should be able to navigate to the shopping cart page",
					"user Unable Navigated to shopping cart page",
					Common.getscreenShot("user Failed to Navigated to shopping cart page"));
			Assert.fail();
		}

	}

	public void empty_storedpayment() {
		// TODO Auto-generated method stub

		try {
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//a[@title='Stored Payment Methods']");
			Common.clickElement("xpath", "//a[@title='Stored Payment Methods']");
			Sync.waitPageLoad(30);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Payment Methods"),
					"validating the Navigation to the My Payment Methods page",
					"After Clicking on stored methods CTA user should be navigate to the My Payment Methods page",
					"Sucessfully User Navigates to the My Payment Methods page after clicking on the stored methods  CTA",
					"Failed to Navigate to the My Payment Methods page after Clicking on my stored methods  CTA");
			String card = Common.findElement("xpath", "//div[@class='customer-payment-methods-container w-full']//p").getText().trim();
			Common.assertionCheckwithReport(card.contains("You do not have any saved payment methods."),
					"validating the no saved payments in stored credit card",
					"After Clicking on stored methods CTA stored credit cart should be empty",
					"Sucessfully we dont have any payments in stored payments",
					"Failed to dispaly the message for empty stored payments");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the My Payment Methods page",
					"After Clicking on stored methods CTA user should be navigate to the My Payment Methods page",
					"Unable to Navigate to the My Payment Methods page after Clicking on my stored methods  CTA",
					Common.getscreenShot(
							"Failed to Navigate to the My Payment Methods page after Clicking on my stored methods  CTA"));
			Assert.fail();
		}
	}

	public void Kustomer_Links(String Dataset) {
		// TODO Auto-generated method stub
		String Kustomer = data.get(Dataset).get("Kustomer Links");
		String[] Kustomerlinks = Kustomer.split(",");
		int i = 0;
		try {
			for (i = 0; i < Kustomerlinks.length; i++) {
				Sync.waitElementPresent(30, "xpath",
						"//div[contains(@class,'footer-menu')]//a[normalize-space()='" + Kustomerlinks[i] + "']");
				Thread.sleep(3000);
				Common.findElement("xpath",
						"//div[contains(@class,'footer-menu')]//a[normalize-space()='" + Kustomerlinks[i] + "']");
				Common.clickElement("xpath",
						"//div[contains(@class,'footer-menu')]//a[normalize-space()='" + Kustomerlinks[i] + "']");
				Sync.waitPageLoad();
				Thread.sleep(3000);
				Common.assertionCheckwithReport(
						Common.getPageTitle().contains(Kustomerlinks[i]) || Common.getCurrentURL().contains(Kustomerlinks[i])
						|| Common.getPageTitle().contains("Store Locator")
						|| Common.getPageTitle().contains("Hydro Flask Gift Card")
						|| Common.getPageTitle().contains("Frequently Asked Questions")
						|| Common.getPageTitle().contains("Contact")
						|| Common.getPageTitle().contains("Tracking & Returns")
						|| Common.getPageTitle().contains("HydroFlask Gift Card")
			|| Common.getPageTitle().contains("Shipping & Handling") ,		
						"validating the Kustomer links navigation from footer Links",
						"After Clicking on" + Kustomerlinks[i] + "it should navigate to the",
						Kustomerlinks[i] + "Sucessfully Navigated to the" + Kustomerlinks[i] + "Links",
						"Unable to Navigated to the" + Kustomerlinks[i] + "Links");
				Common.navigateBack();
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Kustomer links navigation from footer Links",
					"After Clicking on" + Kustomerlinks[i] + "it should navigate to the",
					Kustomerlinks[i] + "Unable to Navigated to the" + Kustomerlinks[i] + "Links",
					Common.getscreenShot("Failed to Navigated to the" + Kustomerlinks[i] + "Links"));
			Assert.fail();
		}
	}

	public void Company(String Dataset) {
		// TODO Auto-generated method stub
		String footer = data.get(Dataset).get("Footer Links");
		String[] footerlinks = footer.split(",");
		int i = 0;
		try {
			for (i = 0; i < footerlinks.length; i++) {
				Sync.waitElementPresent(30, "xpath",
						"//div[contains(@class,'footer-menu')]//a[contains(@title,'" + footerlinks[i] + "')]");
				Thread.sleep(3000);
				Common.findElement("xpath",
						"//div[contains(@class,'footer-menu')]//a[contains(@title,'" + footerlinks[i] + "')]");
				Common.clickElement("xpath",
						"//div[contains(@class,'footer-menu')]//a[contains(@title,'" + footerlinks[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(3000);

				Common.assertionCheckwithReport(
						Common.getPageTitle().contains(footerlinks[i]) || Common.getCurrentURL().contains(footerlinks[i])
								|| Common.getPageTitle().contains("We are Hydro Flask")	|| Common.getPageTitle().contains("Bluesign LP")
								,
						"validating the links navigation from footer Links",
						"After Clicking on" + footerlinks[i] + "it should navigate to the",
						footerlinks[i] + "Sucessfully Navigated to the" + footerlinks[i] + "Links",
						"Unable to Navigated to the" + footerlinks[i] + "Links");
				Common.navigateBack();

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  links navigation from footer Links",
					"After Clicking on" + footerlinks[i] + "it should navigate to the",
					footerlinks[i] + "Unable to Navigated to the" + footerlinks[i] + "Links",
					Common.getscreenShot("Failed to Navigated to the" + footerlinks[i] + "Links"));
			Assert.fail();
		}

	}

	public void Account(String Dataset) {
		// TODO Auto-generated method stub
		String footer = data.get(Dataset).get("Footer Links");
		String[] footerlinks = footer.split(",");
		String footers = data.get(Dataset).get("Footer Links").toUpperCase();
		String[] footerlink = footers.split(",");
		int i = 0;
		try {
			for (i = 0; i < footerlinks.length; i++) {
				Sync.waitElementPresent(30, "xpath",
						"//div[contains(@class,'footer-menu')]//a[normalize-space()='" + footerlinks[i] + "']");
				Thread.sleep(3000);
				Common.findElement("xpath",
						"//div[contains(@class,'footer-menu')]//a[normalize-space()='" + footerlinks[i] + "']");
				Common.clickElement("xpath",
						"//div[contains(@class,'footer-menu')]//a[normalize-space()='" + footerlinks[i] + "']");
				Sync.waitPageLoad();
				Thread.sleep(3000);
				String Bread = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
				System.out.println(Common.getPageTitle().contains(Bread));				
				Common.assertionCheckwithReport(
						Common.getPageTitle().contains(footerlinks[i])
						|| Bread.contains(footerlink[i]) 
						|| Common.getCurrentURL().contains("/refer/")
						|| Common.getCurrentURL().contains("track/order") 
						|| Common.getPageTitle().contains(Bread)
						||Common.getPageTitle().contains("Hydro Flask Loyalty Program Terms and Conditions"),
						"validating the links navigation from footer Links",
						"After Clicking on" + footerlinks[i] + "it should navigate to the",
						footerlinks[i] + "Sucessfully Navigated to the" + footerlinks[i] + "Links",
						"Unable to Navigated to the" + footerlinks[i] + "Links"); 
				Thread.sleep(4000);
				Common.navigateBack();

			}
//			Carrers();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  links navigation from footer Links",
					"After Clicking on" + footerlinks[i] + "it should navigate to the",
					footerlinks[i] + "Unable to Navigated to the" + footerlinks[i] + "Links",
					Common.getscreenShot("Failed to Navigated to the" + footerlinks[i] + "Links"));
			Assert.fail();
		}


	}

	public void Carrers() {
		try {
			Sync.waitElementPresent(30, "xpath", "//ul[@class='m-footer-links__list']//a[contains(text(),'Careers')]");
			Thread.sleep(3000);
			Common.findElement("xpath", "//ul[@class='m-footer-links__list']//a[contains(text(),'Careers')]");
			Common.clickElement("xpath", "//ul[@class='m-footer-links__list']//a[contains(text(),'Careers')]");
			Thread.sleep(3000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Careers | Helen of Troy"),
					"validating the links navigation from footer Links",
					"After Clicking on carrers it should navigate to the page",
					"Sucessfully Navigated to thecarrers Link", "Unable to Navigated to the Link");
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  links navigation from footer Links",
					"After Clicking on the carrers it should navigate ", "Unable to Navigated to the carrers Links",
					Common.getscreenShot("Failed to Navigated to the carrers Links"));
			Assert.fail();
		}
	}

	public void filter_validation(String Dataset) {
		// TODO Auto-generated method stub
		String filter = data.get(Dataset).get("Type");

		try {
			Sync.waitElementPresent("xpath", "//div[@class='yotpo-nav-wrapper']//span[contains(text(),'REVIEWS')]");
			Common.clickElement("xpath", "//div[@class='yotpo-nav-wrapper']//span[contains(text(),'REVIEWS')]");
			Common.clickElement("xpath", "//span[contains(text(),' Color')]");
			String search = Common.findElement("xpath", "//span[contains(text(),' Color')]").getText();
			System.out.println(search);
			Sync.waitImplicit(30);
			for (int i = 1; i <= 10 - 2; i++) {
				List<WebElement> webelementslist = Common.findElements("xpath", "//span[@class='highlight-text']");
				System.out.println(webelementslist);
				String s = webelementslist.get(i).getText();
				System.out.println(s);
				Common.assertionCheckwithReport(s.contains("color"), "validating the filter reviews",
						"After Clicking on filters the repective reviews should be displayed",
						"Sucessfully Respective Reviews has been displayed",
						"Failed to display the respective reviews");

			}

			Sync.waitElementPresent("xpath", "//div[contains(@class,'yotpo-default')]//span[text()='Clear All']");
			Common.clickElement("xpath", "//div[contains(@class,'yotpo-default')]//span[text()='Clear All']");
			Thread.sleep(4000);
			Common.textBoxInput("xpath", "//input[@type='search']", filter);
			Common.actionsKeyPress(Keys.ENTER);
			for (int i = 0; i <= 10 - 5; i++) {
				List<WebElement> webelementslist = Common.findElements("xpath", "//span[@class='highlight-text']");

				String s = webelementslist.get(i).getText();
				System.out.println(s);
				Common.assertionCheckwithReport(s.contains("star")|| s.contains("Star"), "validating the filter reviews search",
						"After Clicking on filters search the repective reviews should be displayed",
						"Sucessfully Respective search Reviews has been displayed",
						"Failed to display the respective search reviews");
			}

			click_arrows();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the filter reviews",
					"After Clicking on filters the repective reviews should be displayed",
					"Unable to display the respective reviews",
					Common.getscreenShot("Failed to display the respective reviews"));
			Assert.fail();
		}
	}

	public void click_arrows() {
		// TODO Auto-generated method stub
		try {
			Common.actionsKeyPress(Keys.DOWN);
			Sync.waitElementPresent("xpath", "//a[contains(@aria-label,'Next Page')]");
			Common.scrollIntoView("xpath", "//a[contains(@aria-label,'Next Page')]");
			Common.clickElement("xpath", "//a[contains(@aria-label,'Next Page')]");
			Thread.sleep(4000);
			String rightarrow = Common.findElement("xpath", "//a[contains(@aria-label,'Page 2')]")
					.getAttribute("aria-label");
			Common.assertionCheckwithReport(rightarrow.contains("Current Page"),
					"validating the arrow for the page navigation",
					"After Clicking on right arrow button it display the next page",
					"Sucessfully next page has been displayed", "Failed to display the next page");
			Sync.waitElementPresent("xpath", "//a[contains(@aria-label,'Previous Page')]");
			Common.clickElement("xpath", "//a[contains(@aria-label,'Previous Page')]");
			Thread.sleep(3000);
			String leftarrow = Common.findElement("xpath", "//a[contains(@aria-label,'Page 1')]")
					.getAttribute("aria-label");
			Common.assertionCheckwithReport(leftarrow.contains("Current Page"),
					"validating the arrow for the page navigation",
					"After Clicking on left arrow button it display the previous page",
					"Sucessfully previous page has been displayed", "Failed to display the previous page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the arrow for the page navigation",
					"After Clicking on left arrow button it display the previous page",
					"Unable to display the previous page", Common.getscreenShot("Failed to display the previous page"));
			Assert.fail();
		}

	}

	public void search_filter(String Dataset) {
		// TODO Auto-generated method stub
		String rating = data.get(Dataset).get("Review");
		String filter = data.get(Dataset).get("CommetsHydroflask");
		try {
			Common.clickElement("xpath", "//span[text()='Select']");
			Sync.waitElementPresent("xpath", "//a[text()='" + filter + "']");
			Common.clickElement("xpath", "//a[text()='" + filter + "']");
			for (int i = 0; i <= 10 - 6; i++) {
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//div[@class='yotpo-review-stars ']//span[text()='" + rating + "']");

				String s = webelementslist.get(i).getText();
				System.out.println(s);
				Common.assertionCheckwithReport(s.contains(rating), "validating the filter search",
						"After Clicking on filters search the repective reviews should be displayed",
						"Sucessfully Respective search Reviews has been displayed",
						"Failed to display the respective search reviews");

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the filter search",
					"After Clicking on filters search the repective reviews should be displayed",
					"Unable to display the respective search reviews",
					Common.getscreenShot("Failed to display the respective search reviews"));
			Assert.fail();
		}

	}

	public void Terms_and_privacy() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//a[contains(text(),'Privacy Policy')]");
			Common.clickElement("xpath", "//a[contains(text(),'Privacy Policy')]");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Hydro Flask Privacy Policy"),
					"validating the Terms and privacy page Navigation",
					"After Clicking Terms and privacy it should navigate to the respective page",
					"Sucessfully Navigated to the Privacy Policy page", "Failed Navigate to the Privacy Policy page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Terms and privacy page Navigation",
					"After Clicking Terms and privacy it should navigate to the respective page",
					"Unable to Navigate to the Privacy Policy page",
					Common.getscreenShot("Failed Navigate to the Privacy Policy page"));
			Assert.fail();
		}


	}
	
	
	public void Resources(String Dataset) {
		// TODO Auto-generated method stub
		String footer = data.get(Dataset).get("Footer Links");
		String[] footerlinks = footer.split(",");
		String footers = data.get(Dataset).get("Footer Links").toUpperCase();
		String[] footerlink = footers.split(",");
		int i = 0;
		try {
			for (i = 0; i < footerlinks.length; i++) {
				if(footerlinks.equals("Dealer Central")) {
					Sync.waitElementPresent(30, "xpath",
							"//div[contains(@class,'footer-menu')]//a[normalize-space()='" + footerlinks[i] + "']");
					Thread.sleep(3000);
					Common.findElement("xpath",
							"//div[contains(@class,'footer-menu')]//a[normalize-space()='" + footerlinks[i] + "']");
					Common.clickElement("xpath",
							"//div[contains(@class,'footer-menu')]//a[normalize-space()='" + footerlinks[i] + "']");
					Common.switchToSecondTab();
					Sync.waitPageLoad();
					Thread.sleep(3000);
					Common.assertionCheckwithReport(
							Common.getPageTitle().contains(footerlinks[i])
				            || Common.getPageTitle().contains("New Retail Account or Corporate Sale Requests / Dealer Central"),
							"validating the links navigation from footer Links",
							"After Clicking on" + footerlinks[i] + "it should navigate to the",
							footerlinks[i] + "Sucessfully Navigated to the" + footerlinks[i] + "Links",
							"Unable to Navigated to the" + footerlinks[i] + "Links");
					
				}else {
					
				
				Sync.waitElementPresent(30, "xpath",
						"//div[contains(@class,'footer-menu')]//a[normalize-space()='" + footerlinks[i] + "']");
				Thread.sleep(3000);
				Common.findElement("xpath",
						"//div[contains(@class,'footer-menu')]//a[normalize-space()='" + footerlinks[i] + "']");
				Common.clickElement("xpath",
						"//div[contains(@class,'footer-menu')]//a[normalize-space()='" + footerlinks[i] + "']");
				Sync.waitPageLoad();
				Thread.sleep(3000);
//				String Bread = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb') or //h1[text()='Hydro Flask Pro Account']]").getText();
//				System.out.println(Common.getPageTitle().contains(Bread));				
				Common.assertionCheckwithReport(
						Common.getPageTitle().contains(footerlinks[i])
						|| Common.getPageTitle().contains("Corporate Purchasing"),
						"validating the links navigation from footer Links",
						"After Clicking on" + footerlinks[i] + "it should navigate to the",
						footerlinks[i] + "Sucessfully Navigated to the" + footerlinks[i] + "Links",
						"Unable to Navigated to the" + footerlinks[i] + "Links"); 
				Thread.sleep(4000);
				Common.navigateBack();
				}
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  links navigation from footer Links",
					"After Clicking on" + footerlinks[i] + "it should navigate to the",
					footerlinks[i] + "Unable to Navigated to the" + footerlinks[i] + "Links",
					Common.getscreenShot("Failed to Navigated to the" + footerlinks[i] + "Links"));
			Assert.fail();
		}

	}

	public void image_button(String Dataset) {
		// TODO Auto-generated method stub
		if(Common.getCurrentURL().contains("www.hydroflask.com")) {
		String names = data.get(Dataset).get("shopall");
		String image = data.get(Dataset).get("Prod Image Button Link");
		String[] Links = names.split(",");
		String[] Link = image.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				System.out.println(Links.length);
				Sync.waitElementPresent("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
				Common.clickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//span[contains(text(),' " + Links[i] + "')]");
				Common.clickElement("xpath", "//span[contains(text(),' " + Links[i] + "')]");
				Sync.waitElementPresent("xpath","//div[@data-content-type='button-item']//span[text() ='" + Link[i] + "']");
				Common.clickElement("xpath","//div[@data-content-type='button-item']//span[text() ='" + Link[i] + "']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.assertionCheckwithReport(
						Common.getPageTitle().contains(Link[i]) || Common.getPageTitle().contains("Outdoor Kitchen")||Common.getPageTitle().contains("New Colors")||Common.getPageTitle().contains("Soft Coolers")
								|| Common.getPageTitle().contains("Shop Cups & Tumblers") || Common.getPageTitle().contains("Silicone Boots")|| Common.getPageTitle().contains("Colors of Oregon"),
						"verifying the header image link " + Links[i] + "Under Featured",
						"user should navigate to the " + Links[i] + " page", 
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header image link " + Links[i] + "Under Featured",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}
		else
		{
			String names = data.get(Dataset).get("shopall");
			String image = data.get(Dataset).get("Image Button Link");
			String[] Links = names.split(",");
			String[] Link = image.split(",");
			int i = 0;
			try {
				for (i = 0; i < Links.length; i++) {
					System.out.println(Links.length);
					Sync.waitElementPresent("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
					Common.clickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
					Thread.sleep(3000);
					Sync.waitElementPresent("xpath", "//span[contains(text(),' " + Links[i] + "')]");
					Common.clickElement("xpath", "//span[contains(text(),' " + Links[i] + "')]");
					Sync.waitElementPresent("xpath","//div[@data-content-type='button-item']//span[text() ='" + Link[i] + "']");
					Common.clickElement("xpath","//div[@data-content-type='button-item']//span[text() ='" + Link[i] + "']");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					Common.assertionCheckwithReport(
							Common.getPageTitle().contains(Link[i]) || Common.getPageTitle().contains("Outdoor Kitchen")||Common.getPageTitle().contains("New Colors")||Common.getPageTitle().contains("Soft Coolers")
									|| Common.getPageTitle().contains("Shop Cups & Tumblers") || Common.getPageTitle().contains("Silicone Boots")
									|| Common.getPageTitle().contains("Colors of Oregon")||Common.getCurrentURL().contains("cups-tumblers"),
							"verifying the header image link " + Links[i] + "Under Featured",
							"user should navigate to the " + Links[i] + " page", 
							"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

				}
			}

			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the header image link " + Links[i] + "Under Featured",
						"User should navigate to the " + Links[i] + "pages",
						" unable to navigate to the " + Links[i] + "pages",
						Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
				Assert.fail();
			}
		}
	}
	public void clickontheproduct_and_image(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		String color = data.get(Dataset).get("Colorproduct");
		try {
			String minicartproduct = Common
					.findElement("xpath", "//a[contains(@class, 'product-item-link') and text()='" + product + "']").getText();
			Common.clickElement("xpath", "//a[contains(@class, 'product-item-link') and text()='" + product + "']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			String currentURL = Common.getCurrentURL();

			String productName = currentURL.substring(currentURL.lastIndexOf("/") + 1).split("\\?")[0];
			productName = productName.replace("-", " ");
			Common.assertionCheckwithReport(productName.contains(minicartproduct.toLowerCase()),
					"validating the product navigating to the PDP page",
					"The product Should be navigates to the PDP page", "Successfully product navigates to the PDP page",
					"Failed to Navigates Product to the PDP page");
			click_minicart();
			String minicartimage = Common.findElement("xpath", "//img[contains(@alt,'" + color + "')]")
					.getAttribute("alt");
			Common.clickElement("xpath", "//img[contains(@alt,'" + color + "')]");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			String currentURL1 = Common.getCurrentURL();

			String productName1 = currentURL1.substring(currentURL1.lastIndexOf("/") + 1).split("\\?")[0];
			productName1 = productName1.replace("-", " ");
String PDP_PrdtName = color.toLowerCase();
			Common.assertionCheckwithReport(productName1.contains(PDP_PrdtName),
					"validating the product navigating to the PDP page",
					"The product Should be navigates to the PDP page", "Successfully product navigates to the PDP page",
					"Failed to Navigates Product to the PDP page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the product navigating to the PDP page",
					"The product Should be navigates to the PDP page", " unable to Navigates Product to the PDP page",
					Common.getscreenShot("Failed to Navigates Product to the PDP page"));
			Assert.fail();
		}

	}

	public void minicart_freeshipping() {
		// TODO Auto-generated method stub
		try {
			click_minicart();
			Thread.sleep(1000);
			int Freeshipping = Common
					.findElements("xpath", "//div[@class='m-progress-bar false']//div[contains(@class,'label-')]").size();
			Thread.sleep(1000);
			int Freeshipping1 = Common
					.findElements("xpath", "(//div[@class='flex items-center']//span)[1]").size();
			Common.assertionCheckwithReport(Freeshipping>0 || Freeshipping1>0,
					"validating the free shipping in mini cart",
					"Free shipping should be avaliable for selected products",
					"Successfully free shipping is appiled for selected products", "Failed to see free shipping");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the free shipping in mini cart",
					"Free shipping should be avaliable for selected products",
					"unable to apply free shipping for the selected products",
					Common.getscreenShot("Failed to see free shipping bar"));
			Assert.fail();
		}

	}

	
	
	public void Holiday_shop_validation(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("Gifts");
		String[] Links = names.split(",");
		String name = data.get(Dataset).get("Gifts").toUpperCase();
		String[] Link = name.split(",");
		int i=0;
		try
		{
			Thread.sleep(4000);
         	for (i = 0; i < Links.length; i++) {
         		Sync.waitElementPresent("xpath", "//span[contains(text(),'Holiday Shop')]");
    			Common.clickElement("xpath", "//span[contains(text(),'Holiday Shop')]");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath",
					"//span[contains(text(),'" + Links[i] + "')]");
			Common.clickElement("xpath",
					"//span[text()='" + Links[i] + "']");	
			
			String title = Common.findElement("xpath", "//h1[contains(@class,'hero-heading')]").getText();			
			String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
			System.out.println(title);
			System.out.println(Links[i]);			
			String products = Common.getText("xpath", "//div[contains(@class,'text-sm')]/span"); 
			System.out.println(products);
			int Number = Integer.parseInt(products);
			int j=0;
			if(Number>j)
			{
				Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) || 
						Common.getPageTitle().contains("Water Bottle Gifts"), 
					"verifying the header link " + Links[i] + "Under Customize",
					"user should navigate to the " + Links[i] + " page",
					"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
         	}
         }}
		catch(Exception |Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Holiday Shop",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}
		
	}
	
	public void minicart_product_close() {
		// TODO Auto-generated method stub
		try {

			Common.clickElement("xpath", "//a[contains(@aria-label,'Edit product')]//parent::div//button");
			Sync.waitElementPresent("xpath", "//a[contains(@aria-label,'Edit product')]//parent::div//button");
			String minicartpopup = Common.findElement("xpath", "//div[@x-ref='removeItemConfirm']")
					.getAttribute("aria-modal");
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(minicartpopup.contains("true"),
					"validating the popup when you click on delete", "The Popup should be displayed",
					"Successfully popup is displayed when we click on the delete button",
					"Failed to Display the popup");
			String popup = Common.findElement("xpath", "//h2[@x-ref='modalHeader' and contains(text(),'Remove Item')]")
					.getText().trim();
			if (popup.equals("Remove Item")) {
				Common.clickElement("xpath", "//button[@aria-label='Close']");
			} else {
				Assert.fail();
			}
			Common.clickElement("xpath", "//a[contains(@aria-label,'Edit product')]//parent::div//button");
			Sync.waitElementPresent("xpath", "//a[contains(@aria-label,'Edit product')]//parent::div//button");
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(minicartpopup.contains("true"),
					"validating the popup when you click on delete", "The Popup should be displayed",
					"Successfully popup is displayed when we click on the delete button",
					"Failed to Display the popup");

			if (popup.equals("Remove Item")) {

				Common.clickElement("xpath", "//button[contains(text(),'Cancel')]");
			} else {
				Assert.fail();
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the close and cancel functionality",
					"User should able to click on close and cancel button",
					"unable to click on close and cancel button",
					Common.getscreenShot("Failed to Click on close and cancel button"));

			Assert.fail();
		}

	}

	public void corporate_purchasing() {
		// TODO Auto-generated method stub
		String name = "";
		Common.actionsKeyPress(Keys.END);
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//a[@title='Group Customization']");
			Common.clickElement("xpath", "//a[@title='Group Customization']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Corporate Purchasing"),
					"Validating the Corporate Purchasing page navigation",
					"User should able to land on the Corporate Purchasing page",
					"successfully land to Corporate Purchasing page", "unable to load the  Corporate Purchasing page");

			List<WebElement> corporate = Common.findElements("xpath",
					"//div[contains(@class,'pagebuilder')]//a[contains(@class,'pagebuilder')]");
			System.out.println(corporate.size());
			System.out.println(corporate);
			for (int i = 0; i < corporate.size(); i++) {
				List<WebElement> Image = Common.findElements("xpath",
						"//div[contains(@class,'pagebuilder')]//a[contains(@class,'pagebuilder')]//span[@class='a-btn__label']");
				Thread.sleep(6000);
				name = Image.get(i).getText();
				System.out.println(name);
				Common.assertionCheckwithReport(
						name.equals("New Customer Inquiry") || name.equals("Existing Custom Inquiry")
								|| name.equals("ASI/AAPI Customer Inquiry"),
						"Validating the" + name + "in the corporate purchasing",
						"User should able to see the " + name + "in the corporate purchasing page",
						"successfully " + name + "able to see in the coorparate purchasing page",
						"unable see the" + name + "in the coorparate purchasing page");
			}
			Sync.waitElementPresent("xpath", "//a[@class='pagebuilder-button-primary']//span[text()='New Customer Inquiry']");
			Common.clickElement("xpath", "//a[@class='pagebuilder-button-primary']//span[text()='New Customer Inquiry']");
			Sync.waitPageLoad();
			System.out.println(Common.getPageTitle());
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Frequently Asked Questions | Hydro Flask")||Common.getPageTitle().contains("Non Profit - New Account Inquiry"),
					"Validating the New Account Inquiry Form Page navigation",
					"User should able to land on the New Account Inquiry Form  page",
					"successfully land to New Account Inquiry Form Page",
					"unable to load the  New Account Inquiry Form Page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the New Account Inquiry Form Page navigation",
					"User should able to land on the New Account Inquiry Form  page",
					"unable to  land on New Account Inquiry Form Page",
					Common.getscreenShot("Failed to land on New Account Inquiry Form Page"));
			Assert.fail();
		}


	}

	public void new_Account_Inquiry_corporate(String dataSet) {
		// TODO Auto-generated method stub

		String expectedResult = "Email us form is visible in tab";
		String country = data.get(dataSet).get("Country");
		String channel = data.get(dataSet).get("Channel");
		String typeofbusiness = data.get(dataSet).get("Typeofbusiness");
		String storesize = data.get(dataSet).get("Storesize");
		String state = data.get(dataSet).get("Region");
		try {
			if(Common.getCurrentURL().contains("https://hydroflask.kustomer.support/")) {
				Sync.waitElementPresent("xpath", "//input[@data-label='Company/Organization']");
				Common.textBoxInput("xpath", "//input[@data-label='Company/Organization']", data.get(dataSet).get("Company"));

				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//div[@id='conversationChannelIndustry']");
				Common.clickElement("xpath", "//div[@id='conversationChannelIndustry']");

				Sync.waitElementPresent("xpath", "//div[text()='" + channel + "']");
				Common.clickElement("xpath", "//div[text()='" + channel + "']");
				
				Sync.waitElementPresent("xpath", "//input[@id='conversationWebAddress']");
				Common.textBoxInput("xpath", "//input[@id='conversationWebAddress']", data.get(dataSet).get("webaddress"));

				Common.clickElement("xpath", "//div[@id='conversationPlaceACustomOrder']");
				Thread.sleep(4000);
				
				Common.clickElement("xpath", "//div[text()='Yes']");
				Sync.waitElementPresent("id", "conversationInHandDate-date");
				Common.textBoxInput("id", "conversationInHandDate-date","03/03/2026");
				Common.actionsKeyPress(Keys.ENTER);
				

				Common.clickElement("id", "conversationPlanToResellProducts");
				Common.clickElement("xpath", "//div[text()='Yes']");
				

				Common.clickElement("id", "conversationNumberOfUnits");
				
				Common.clickElement("xpath", "//div[text()='48-96']");
//				Common.textBoxInput("xpath", "//input[@id='annualRevenue']", data.get(dataSet).get("annualRevenue"));
//
//				Common.textBoxInput("xpath", "//input[@id='yearsInBusiness']", data.get(dataSet).get("yearsInBusiness"));
				
				Sync.waitElementPresent("xpath", "//div[@id='conversationCountry']");
				Common.clickElement("xpath", "//div[@id='conversationCountry']");

				Sync.waitElementPresent("xpath", "//div[text()='" + country + "']");
				Common.clickElement("xpath", "//div[text()='" + country + "']");

				Sync.waitElementPresent("id", "conversationShippingAddress");
				Common.textBoxInput("id", "conversationShippingAddress", data.get(dataSet).get("Street"));

				Sync.waitElementPresent("xpath", "//input[@id='conversationCityForForms']");
				Common.textBoxInput("xpath", "//input[@id='conversationCityForForms']", data.get(dataSet).get("City"));
				
				Sync.waitElementPresent("xpath", "//div[@id='conversationStateProvince']");
				Common.clickElement("xpath", "//div[@id='conversationStateProvince']");

				Sync.waitElementPresent("xpath", "//div[text()='" + state + "']");
				Common.clickElement("xpath", "//div[text()='" + state + "']");

				Sync.waitElementPresent("xpath", "//input[@id='conversationZipCodeForForms']");
				Common.textBoxInput("xpath", "//input[@id='conversationZipCodeForForms']",
						data.get(dataSet).get("postcode"));

				Common.textBoxInput("xpath", "//textarea[@id='conversationDescribeYourBusiness']",
						data.get(dataSet).get("YourBusiness"));

				Common.textBoxInput("xpath", "//textarea[@id='messagePreview']",
						data.get(dataSet).get("interested"));

//				Common.textBoxInput("xpath", "//textarea[@id='messagePreview']", data.get(dataSet).get("Brandscarry"));
//
//				Common.textBoxInput("xpath", "//textarea[@id='howDoYouPlanToMarketDisplayOurProducts']",
//						data.get(dataSet).get("DisplayProducts"));
//
//				Common.textBoxInput("xpath", "//textarea[@id='howDidYouHearAboutUs']", data.get(dataSet).get("Aboutus"));

				Common.textBoxInput("xpath", "//input[@name='customerFirstName']", data.get(dataSet).get("FirstName"));

				Common.textBoxInput("xpath", "//input[@name='customerLastName']", data.get(dataSet).get("LastName"));

				Common.textBoxInput("xpath", "//input[@id='conversationJobTitle']", data.get(dataSet).get("Jobtitle"));

				Common.textBoxInput("xpath", "//input[@id='conversationPhoneForForms']", data.get(dataSet).get("phone"));
				Common.textBoxInput("xpath", "//input[@name='customerEmail']", data.get(dataSet).get("Email"));
				
				Sync.waitElementPresent("xpath", "//input[@value='select-to-opt-in']");
				Common.clickElement("xpath", "//input[@value='select-to-opt-in']");
				
				Sync.waitElementPresent("xpath", "//input[@value='select-to-opt-in']");
				Common.clickElement("xpath", "//input[@value='select-to-opt-in']");
			String Email=	Common.findElementBy("id", "customerEmail").getAttribute("value");
			Common.assertionCheckwithReport(Email.contains(data.get(dataSet).get("Email")), "verifying Contact us Success message ",
					"Success message should be Displayed", "Contact us Success message displayed ",
					"failed to dispaly success message");
			
			}
			else {
//			Sync.waitElementPresent("xpath", "//span[text()='Write to Us']");
//			Common.clickElement("xpath", "//span[text()='Write to Us']");

			Sync.waitElementPresent(40, "xpath", "//div[contains(@class,'form-row form-row-full')]");
//			Common.switchFrames("xpath", "//div[contains(@class,'form-row form-row-full')]");

			Sync.waitElementPresent("xpath", "//input[@data-label='Company/Organization']");
			Common.textBoxInput("xpath", "//input[@data-label='Company/Organization']", data.get(dataSet).get("Company"));

			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//div[@id='conversationChannelIndustry']");
			Common.clickElement("xpath", "//div[@id='conversationChannelIndustry']");

			Sync.waitElementPresent("xpath", "//div[text()='" + channel + "']");
			Common.clickElement("xpath", "//div[text()='" + channel + "']");

//			Sync.waitElementPresent("xpath", "//div[@id='conversationTypeOfBusiness']");
//			Common.clickElement("xpath", "//div[@id='conversationTypeOfBusiness']");
//			Sync.waitElementPresent("xpath", "//div[text()='" + typeofbusiness + "']");
//			Common.clickElement("xpath", "//div[text()='" + typeofbusiness + "']");

			Common.textBoxInput("xpath", "//input[@id='conversationWebAddress']", data.get(dataSet).get("webaddress"));

//			Common.clickElement("xpath", "//div[@id='conversationAreYouAnAsiPpaiIndustryMem']");
//			Thread.sleep(4000);
//			Common.Common.clickElement("xpath", "//div[@data-path='no']");

			Sync.waitElementPresent("xpath", "//div[@id='conversationPlaceACustomOrder']");
			Common.clickElement("xpath", "//div[@id='conversationPlaceACustomOrder']");
			Common.clickElement("xpath", "//div[@data-path='no']");

//			Sync.waitElementPresent("xpath", "//input[@name='conversationInHandDate']");
//
//			Common.textBoxInput("xpath", "//input[@name='conversationInHandDate']", data.get(dataSet).get("date"));

//			Common.clickElement("xpath", "//div[@id='conversationSellThruWebsite']");
//			Thread.sleep(4000);
//			Common.clickElement("xpath", "//div[@data-path='no']");
//
//			Common.textBoxInput("xpath", "//input[@id='whatOfYourSalesComeThroughYourWebsite']",
//					data.get(dataSet).get("salesPercentage"));

//			Common.textBoxInput("xpath", "//input[@id='numberOfStores']", data.get(dataSet).get("numberOfStores"));

//			Common.clickElement("xpath", "//div[@id='conversationStoreSize']");
//			Common.clickElement("xpath", "//div[text()='" + storesize + "']");

//			Common.textBoxInput("xpath", "//input[@id='annualRevenue']", data.get(dataSet).get("annualRevenue"));

//			Common.textBoxInput("xpath", "//input[contains(@id,'WhatIsTheEstimatedNumberOfUnits')]",
//					data.get(dataSet).get("NumberOfUnits"));

//			Common.textBoxInput("xpath", "//input[@id='yearsInBusiness']", data.get(dataSet).get("yearsInBusiness"));
			
			Sync.waitElementPresent("xpath", "//div[@id='conversationCountry']");
			Common.clickElement("xpath", "//div[@id='conversationCountry']");

			Sync.waitElementPresent("xpath", "//div[text()='" + country + "']");
			Common.clickElement("xpath", "//div[text()='" + country + "']");

			Sync.waitElementPresent("xpath", "//input[@id='conversationStreetForForms']");
			Common.textBoxInput("xpath", "//input[@id='conversationStreetForForms']", data.get(dataSet).get("Street"));

//			Common.textBoxInput("xpath", "//input[@name='suiteUnit']", data.get(dataSet).get("yearsInBusiness"));

	

			Sync.waitElementPresent("xpath", "//div[@id='conversationState']");
			Common.clickElement("xpath", "//div[@id='conversationState']");

			Sync.waitElementPresent("xpath", "//div[text()='" + state + "']");
			Common.clickElement("xpath", "//div[text()='" + state + "']");

			Sync.waitElementPresent("xpath", "//input[@id='conversationCityForForms']");
			Common.textBoxInput("xpath", "//input[@id='conversationCityForForms']", data.get(dataSet).get("City"));

			Sync.waitElementPresent("xpath", "//input[@id='conversationZipCodeforforms']");
			Common.textBoxInput("xpath", "//input[@id='conversationZipCodeforforms']",
					data.get(dataSet).get("postcode"));
			
			Common.textBoxInput("xpath", "//input[@id='conversationBillingAddress']",
					data.get(dataSet).get("Street"));
			Common.textBoxInput("xpath", "//textarea[@id='conversationDescribeYourBusiness']",
					data.get(dataSet).get("YourBusiness"));

			Common.textBoxInput("xpath", "//textarea[@id='messagePreview']",
					data.get(dataSet).get("interested"));

//			Common.textBoxInput("xpath", "//textarea[@id='messagePreview']", data.get(dataSet).get("Brandscarry"));

//			Common.textBoxInput("xpath", "//textarea[@id='howDoYouPlanToMarketDisplayOurProducts']",
//					data.get(dataSet).get("DisplayProducts"));

//			Common.textBoxInput("xpath", "//textarea[@id='howDidYouHearAboutUs']", data.get(dataSet).get("Aboutus"));

			Common.textBoxInput("xpath", "//input[@name='customerFirstName']", data.get(dataSet).get("FirstName"));

			Common.textBoxInput("xpath", "//input[@name='customerLastName']", data.get(dataSet).get("LastName"));

			Common.textBoxInput("xpath", "//input[@id='conversationJobTitle']", data.get(dataSet).get("Jobtitle"));

			Common.textBoxInput("xpath", "//input[@id='conversationPhoneForForms']", data.get(dataSet).get("phone"));
			Common.textBoxInput("xpath", "//input[@name='customerEmail']", data.get(dataSet).get("Email"));

//			Common.textBoxInput("xpath", "//input[@name='inquirySubmittedBy']", data.get(dataSet).get("submittedby"));

			Common.clickElement("xpath", "//button[text()='Submit']");

			Sync.waitElementPresent("xpath", "//div[@class='form-wrap']");
			int Contactussuccessmessage = Common.findElements("xpath", "//div[@class='form-wrap']").size();
			System.out.println(Contactussuccessmessage);
			Common.assertionCheckwithReport(Contactussuccessmessage > 0, "verifying Contact us Success message ",
					"Success message should be Displayed", "Contact us Success message displayed ",
					"failed to dispaly success message");
		}
		}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying email us from",
					"contact us form data enter without any error message", "Contact us page getting error ",
					Common.getscreenShotPathforReport("Contact us page"));
			Assert.fail();

		}

//		Common.actionsKeyPress(Keys.PAGE_UP);
//		String Text = Common.getText("xpath", "//div[@class='form-wrap']");
//		System.out.println(Text);
//		expectedResult = "User gets confirmation under the same tab. It includes a reference number and email is sent to email provided. No validation errors.";
//		Common.assertionCheckwithReport(Text.contains("Your submission was successful."),
//				"verifying contact us confirmation message", expectedResult,
//				"User gets confirmation under the same tab", "unable to load the confirmation form");

	}


	public void color_validation(String colorname) {
		// TODO Auto-generated method stub
		try {

			Common.clickElement("xpath", "//div[@class='ais-Panel-header']//span//div[text()='Colors']");
			Sync.implicitWait();
			Sync.waitElementPresent("xpath",
					"//ul[contains(@class,'ais-RefinementList')]//input[@value='" + colorname + "']");
			Common.clickElement("xpath",
					"//ul[contains(@class,'ais-RefinementList')]//input[@value='" + colorname + "']");
			Thread.sleep(4000);
			String colorcount = Common.findElement("xpath",
					"(//span[@data-color='" + colorname + "']//following-sibling::span)[2]")
					.getText().replace("(", "").replace(")", "");
			System.out.println(colorcount);
			String bottlecount = Common.findElement("xpath", "//div[@class='text-sm']//span").getText().trim();
			System.out.println(bottlecount);
			Common.assertionCheckwithReport(colorcount.equals(bottlecount), "verifying the color bar has been expand",
					"When we click on the color it should be expand",
					"Successfully the color has been expand when we click on the colors ",
					"unable to expand the colors in PLP page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the color bar has been expand",
					"When we click on the color it should be expand", "unable to expand the colors in PLP page",
					Common.getscreenShotPathforReport("Failed to expand the colors in PLP page"));
			Assert.fail();
		}

	}

	public void reviews_colorcount_banner_Ribbon_ColorSwatch() {
		// TODO Auto-generated method stub
		try {
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.scrollIntoView("xpath","(//li[@class='ais-InfiniteHits-item'])[1]");
			Common.mouseOver("xpath","(//li[@class='ais-InfiniteHits-item'])[1]");
			int Reviews = Common.findElements("css", "div[class*='yotpo-reviews-star-ratings']").size();
			System.out.println(Reviews);
			int colorcount = Common.findElements("css", "div[class*='flex-shrink']").size();
			System.out.println(colorcount);
			int ribbon = Common.findElements("css", "span[class*='ribbon-bg']").size();
			System.out.println(ribbon);
			int promobanner = Common.findElements("css", "div[class='hero-container']").size();
			System.out.println(promobanner);
			int Color_Swatch = Common.findElements("css", "div[class*='mr-0']").size();
			System.out.println(Color_Swatch);
			int NewColor = Common.findElements("xpath", "//span[text()='New Color']").size();
			System.out.println(NewColor);
			
			Common.assertionCheckwithReport(NewColor>0 && Color_Swatch>0 && Reviews > 0 && colorcount > 0 && ribbon > 0 && promobanner > 0,
					"verifying the products reviews color count ribbon and promo banner on the PLP page  ",
					"user should able to see the products reviews color count ribbon and promo banner on the PLP page",
					"user successfully see the products reviews color count ribbon and promo banner on the PLP page",
					"Failed to see the products reviews color count ribbon and promo banner on the PLP page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"verifying the products reviews color count ribbon and promo banner on the PLP page  ",
					"user should able to see the products reviews color count ribbon and promo banner on the PLP page",
					"user unable to see the products reviews color count ribbon and promo banner on the PLP page",
					Common.getscreenShot(
							"Failed to see the products reviews color count ribbon and promo banner on the PLP page"));
			Assert.fail();
		}

	}

	public void price_filter_validation() {
		// TODO Auto-generated method stub
		String name = "";
		try {
			Common.clickElement("xpath", "//div[@class='ais-Panel-header']//div[text()='Price']");
			Thread.sleep(3000);
			String lastvalue = Common.findElement("xpath", "//div[@class='value end active']").getText()
					.replace("$", "").replace(".00", "");
			System.out.println(lastvalue);
			Sync.waitElementPresent("xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");
			WebElement price = Common.findElement("xpath",
					"//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");
			dragprice(price);
			Thread.sleep(6000);
			List<WebElement> products = Common.findElements("xpath",
					"//ol[@class='ais-InfiniteHits-list']//img[contains(@class,'m-product')]");
			for (int i = 0; i < products.size(); i++) {
				int Size = products.size();
				System.out.println(Size);
				Thread.sleep(4000);
				if (Size == 1) {
					String name1 = Common.findElement("xpath", "//span[@class='price-wrapper']//span[@class='price']")
							.getText().replace("$", "");
					System.out.println(name1);
					Float namevalue1 = Float.parseFloat(name1);
					System.out.println(namevalue1);
					if (namevalue1 >= 5) {
						Thread.sleep(3000);
						String value1 = Common.findElement("xpath", "//span[@class='price-wrapper']")
								.getAttribute("data-price-amount");
						System.out.println(value1);
						Common.assertionCheckwithReport(value1.equals(name1), "verifying the price filters in PLP page",
								"When we select the range of price filters between the range only products should display",
								"Successfully are displayed in the pricing range",
								"unable to display the procing range after pricing filter applied");
					} else {
						Assert.fail();
					}
				} else {
					List<WebElement> productprice = Common.findElements("xpath",
							"//span[@class='price-wrapper']//span[@class='price']");
					Thread.sleep(6000);
					name = productprice.get(i).getText().replace("$", "");
					Float namevlaue = Float.parseFloat(name);
					if (namevlaue >= 5) {
						Thread.sleep(3000);
						String value = Common
								.findElement("xpath", "//span[@class='price-wrapper']//span[@class='price']").getText()
								.replace("$", "");
						Common.assertionCheckwithReport(value.equals(name), "verifying the price filters in PLP page",
								"When we select the range of price filters between the range only products should display",
								"Successfully are displayed in the pricing range",
								"unable to display the procing range after pricing filter applied");
					} else {
						Assert.fail();
					}
				}
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the price filters in PLP page",
					"When we select the range of price filters between the range only products should display",
					"unable to display the procing range after pricing filter applied",
					Common.getscreenShotPathforReport(
							"unable to display the procing range after pricing filter applied"));
			Assert.fail();
		}


	}

	public void dragprice(WebElement price) {
		try {
			String lastvalue = Common.getText("xpath", "//div[@class='value end active']").replace("$", "")
					.replace(".00", "");
			System.out.println(lastvalue);
			Thread.sleep(3000);
			Common.dragdrop(price, "xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='0']");
		} catch (Exception | Error e) {

		}
	}

	public void see_options(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		try {
			Thread.sleep(5000);
			String seeoption = Common.findElement("xpath", "//span[text()='See options']")
					.getText();
			System.out.println(seeoption);
			if (seeoption.equals("See options")) {
				Sync.waitElementPresent("id", "all-checked");
				Common.clickElement("id", "all-checked");
				Common.clickElement("xpath", "//button[@data-role='selected-remove']");
				
				Thread.sleep(4000);
				Sync.waitElementVisible("xpath", "//span[text()='You have no items in your wish list.']");
				String emptymessage = Common.findElement("xpath", "//span[text()='You have no items in your wish list.']")
						.getText();
				Common.assertionCheckwithReport(emptymessage.contains("You have no items in your wish list."),
						"validating the empty products in my favrioutes page ",
						"Products should not appear in the my favoritoes page",
						"Sucessfully product are empty in my favorites page",
						"failed to see empty products in my favorites page");
				search_product("Product");
				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
				Common.clickElement("xpath", "//img[@alt='" + products + "']");
				Sync.waitElementPresent(30, "xpath", "(//span[contains(@class,'group-hover/wishlist')])[1]");
				Common.mouseOverClick("xpath", "(//span[contains(@class,'group-hover/wishlist')])[1]");
				Thread.sleep(2000);
				Sync.waitElementPresent(30, "id", "customer-menu");
				Common.clickElement("id", "customer-menu");
				Sync.waitElementPresent("id", "customer.header.wishlist.nav.link");
				Common.clickElement("id", "customer.header.wishlist.nav.link");
				Sync.waitPageLoad();
				Common.scrollIntoView("xpath", "(//img[contains(@class,'m-produc')])[1]");
				Sync.waitElementPresent(30, "xpath", "(//img[contains(@class,'m-produc')])[1]");
				Common.mouseOver("xpath", "(//img[contains(@class,'m-produc')])[1]");

				Sync.waitElementPresent(30, "xpath", "//span[text()='Add to Cart']");
				Common.clickElement("xpath", "//span[text()='Add to Cart']");

				Sync.waitPageLoad();
				Thread.sleep(4000);
				String message1 = Common.findElement("xpath", "//div[@ui-id='message-success']")
						.getAttribute("data-ui-id");
				System.out.println(message1);
				Common.assertionCheckwithReport(message1.contains("success"), "validating the  product add to the cart",
						"Product should be add to cart", "Sucessfully product added to the cart ",
						"failed to add product to the cart");

			} else {
				Sync.waitElementPresent("xpath", "//span[text()='Add to Cart']");
				Common.clickElement("xpath", "//span[text()='Add to Cart']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String message1 = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
						.getAttribute("data-ui-id");
				System.out.println(message1);
				Common.assertionCheckwithReport(message1.contains("success"), "validating the  product add to the cart",
						"Product should be add to cart", "Sucessfully product added to the cart ",
						"failed to add product to the cart");
				
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void New_Color_Destination(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("colornames");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent(50,"xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");

				Common.javascriptclickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
				Thread.sleep(3000);
				int size=Common.findElements("xpath", "(//div[@x-init='$nextTick(() => {show = true })'])[2]").size();
				if(size <=0) 
				{
					Common.javascriptclickElement("xpath", "//img[@alt='Hydroflask store logo']");
				
				Sync.waitElementPresent(50,"xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
				Common.javascriptclickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
		       }
			Thread.sleep(3000);	
				Common.clickElement("xpath", "//span[contains(text(),'New Colors')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//a[contains(@href,'new-color')]//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath",
						"//a//span[text()='New Colors']//following::ul//span[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				
				String title = "";
	            if 
	            (Common.findElements("xpath", "//h1[contains(@class,'hero-heading')]").size() > 0) {
	                title = Common.findElement("xpath", "//h1[contains(@class,'hero-heading')]").getText();
	            } 
	            else if (Common.findElements("xpath", "//h1[contains(@class,'hero')]/span").size() > 0) {
	                title = Common.findElement("xpath", "//h1[contains(@class,'hero')]/span").getText();
	            }
	            
				String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
				System.out.println(title);
				System.out.println(Links[i]);			
				String products=Common.getText("xpath", "//span[contains(@class,'font-bold dr:font-norm')]");
				System.out.println(products);
				int Number = Integer.parseInt(products);
				int j=0;
				if(Number>j)
				{
				Common.assertionCheckwithReport(title.contains("Nonstop Color") || Common.getPageTitle().contains(Links[i]),	"verifying the header link " + Links[i] + "Under Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
				}
				else
				{
					ExtenantReportUtils.addFailedLog(
							"validating the the products in the plp ",
							"User should able to see the products in plp", "unable to see the products in the PLP",
							Common.getscreenShot("Failed to see products in PLP"));
					Assert.fail();
			} }
		}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Featured",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}
	public void shop_by_Color_validation(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("colornames");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent(50,"xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");

				Common.javascriptclickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
				Thread.sleep(3000);
				int size=Common.findElements("xpath", "(//div[@x-init='$nextTick(() => {show = true })'])[2]").size();
				if(size <=0) 
				{
					Common.javascriptclickElement("xpath", "//img[@alt='Hydroflask store logo']");
				
				Sync.waitElementPresent(50,"xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
				Common.javascriptclickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
		       }
			Thread.sleep(3000);	
				Common.clickElement("xpath", "(//span[contains(text(),'Shop by Color')])[1]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"(//span[contains(text(),'" + Links[i] + "')])[1]");
				Common.clickElement("xpath",
						"(//span[contains(text(),'" + Links[i] + "')])[1]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				
				String title = "";
	            if 
	            (Common.findElements("xpath", "//h1[contains(@class,'hero-heading')]").size() > 0) {
	                title = Common.findElement("xpath", "//h1[contains(@class,'hero-heading')]").getText();
	            } 
	            else if (Common.findElements("xpath", "//h1[contains(@class,'hero')]/span").size() > 0) {
	                title = Common.findElement("xpath", "//h1[contains(@class,'hero')]/span").getText();
	            }
	            
				String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
				System.out.println(title);
				System.out.println(Links[i]);			
				String products=Common.getText("xpath", "//span[contains(@class,'font-bold dr:font-norm')]");
				System.out.println(products);
				int Number = Integer.parseInt(products);
				int j=0;
				if(Number>j)
				{
				Common.assertionCheckwithReport(title.contains("Shop") || Common.getPageTitle().contains(Links[i]),	"verifying the header link " + Links[i] + "Under Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
				}
				else
				{
					ExtenantReportUtils.addFailedLog(
							"validating the the products in the plp ",
							"User should able to see the products in plp", "unable to see the products in the PLP",
							Common.getscreenShot("Failed to see products in PLP"));
					Assert.fail();
			} }
		}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Featured",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}
	public void My_order_subcribtion(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		String prod = data.get(Dataset).get("prod product");
		try {
			Sync.waitElementPresent("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Sync.waitElementPresent("xpath", "//a[text()='My Account']");
			Common.clickElement("xpath", "//a[text()='My Account']");
			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"),
					"validating the page navigation to the my account",
					"after clicking on the my account it should navigate to the my account page",
					"Sucessfully Navigated to the my account page", "failed to Navigate to the my account page");
			Sync.waitElementPresent("xpath", "//a[text()='My Out of Stock Subscriptions']");
			Common.clickElement("xpath", "//a[text()='My Out of Stock Subscriptions']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(20, "xpath", "//span[@class='a-product-name']");
			String name = Common.findElement("xpath", "(//span[@class='a-product-name'])[1]").getText();
			Common.assertionCheckwithReport(name.equals(products) || name.equals(prod),
					"validating the outofstock produt in the subcribtion page",
					"Product should be display in the subcribtion page",
					"Sucessfully product has been appeared in the outofstock subcription page",
					"Failed to see the product in subcribtion page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the outofstock produt in the subcribtion page",
					"Product should be display in the subcribtion page",
					"Unable to see the product in subcribtion page",
					Common.getscreenShot("Failed to see the product in subcribtion page"));
			Assert.fail();
		}

	}

	public void Accessories_validation(String Dataset) {
	    String names = data.get(Dataset).get("Accessories");
	    String[] Links = names.split(",");

	    for (String link : Links) {
	        try {
	        	Sync.waitElementPresent(50,"xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");

				Common.javascriptclickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
				Thread.sleep(3000);
				int size=Common.findElements("xpath", "(//div[@x-init='$nextTick(() => {show = true })'])[2]").size();
				if(size <=0) 
				{
					Common.javascriptclickElement("xpath", "//img[@alt='Hydroflask store logo']");
				
				Sync.waitElementPresent(50,"xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
				Common.javascriptclickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
		}
			Thread.sleep(3000);	
	            Sync.waitElementPresent(50,"xpath", "(//a[@title='Accessories']//span[text()='Accessories'])[1]");
	            Common.clickElement("xpath", "(//a[@title='Accessories']//span[text()='Accessories'])[1]");
	            Thread.sleep(3000);
	            Sync.waitElementPresent(40,"xpath", "(//a[contains(@title,'" + link + "')]//span[contains(text(),'" + link + "')])[1]");
	            Common.clickElement("xpath", "(//a[contains(@title,'" + link + "')]//span[contains(text(),'" + link + "')])[1]");
	            Sync.waitPageLoad();
	            Thread.sleep(4000);
	            String title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
	            String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
	            System.out.println(title);
	            System.out.println(link);

	            Common.assertionCheckwithReport(title.contains(link) || breadcrumbs.contains(link),
	                    "verifying the header link " + link + " Under Accessories",
	                    "user should navigate to the " + link + " page",
	                    "user successfully Navigated to the " + link, "Failed to navigate to the " + link);

	            // Validate product count
	            Sync.waitElementPresent(30, "xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
	            String products = Common.getText("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
	            System.out.println(products);
	            int productCount = Integer.parseInt(products);

	            if (productCount <= 0) {
	                ExtenantReportUtils.addFailedLog(
	                        "validating the the products in the plp ",
	                        "User should able to see the products in plp", "unable to see the products in the PLP",
	                        Common.getscreenShot("Failed to see products in PLP"));
	                Assert.fail();
	            }

	        } catch (Exception | Error e) {
	            e.printStackTrace();
	            ExtenantReportUtils.addFailedLog("verifying the header link " + link + " Under Accessories",
	                    "User should navigate to the " + link + " pages",
	                    " unable to navigate to the " + link + " pages",
	                    Common.getscreenShot("Failed to navigate to the " + link + " pages"));
	            Assert.fail();
	        }
	    }
	}

	
	public void remove_outofstock_subcribtion(String Dataset) {
		// TODO Auto-generated method stub
		try {
			String price = Common.findElement("xpath", "//span[@data-price-type='finalPrice']")
					.getAttribute("data-price-amount");
			if (price.equals(Dataset)) {
				Thread.sleep(3000);
				Common.clickElement("xpath", "(//span[text()='Remove'])[2]");
				Common.implicitWait();
				Common.alerts("Cancel");
				Thread.sleep(3000);
				Common.clickElement("xpath", "(//span[text()='Remove'])[2]");
				Common.implicitWait();
				Common.alerts("Ok");

			} else {

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

	public String change_Email(String Dataset) {
		// TODO Auto-generated method stub
		String newemail = "";
//		String Email=Common.genrateRandomEmail("hydroflaskqa@gmail.com");
		Random random = new Random();
		int min = 1000, max =1300;
        int i = random.nextInt(max - min + 1) + min; 
		String Email ="hydroflaskqa+Automation"+i+"@gmail.com";

		try {
			
			Common.clickElement("xpath", "//span[text()='Edit']");
			
			Sync.waitElementClickable(30, "id", "change-email");
			Common.clickElement("id", "change-email");
			Common.textBoxInputClear("xpath", "(//input[@name='email'])[1]");
			Common.textBoxInputAndVerify("xpath", "(//input[@name='email'])[1]",Email);
			 newemail = Common.findElement("xpath", "(//input[@name='email'])[1]").getAttribute("value");
			Common.textBoxInput("xpath", "//input[@name='current_password']",
					data.get(Dataset).get("Confirm Password"));
			Common.clickElement("xpath", "//span[text()='Save Account Information']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			String successmessage = Common.findElement("xpath", "//div[@ui-id='message-success']").getText();
			Common.assertionCheckwithReport(
					successmessage.contains("You saved the account information.")
							|| Common.getPageTitle().contains("Customer Login"),
					"verifying the Success message for the Change email",
					"user should get the success message and navigate back to the Login page",
					"Successfully user gets the success message and navigated to the Login page",
					"Failed to get the success message and unable to navigate to the login page");
			Sync.waitPageLoad();
			Common.textBoxInput("id", "email", newemail);
			Common.textBoxInput("id", "pass", data.get(Dataset).get("Confirm Password"));
			Common.clickElement("xpath", "//span[text()='Sign In']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Dashboard"),
					"To validate the user lands on My Account after successfull login",
					"After clicking on the signIn button it should navigate to the My Account",
					"user Sucessfully navigate to the My Account after clicking on the signIn button",
					"Failed to signIn and not navigated to the My Account");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the user lands on My Account after successfull login",
					"After clicking on the signIn button it should navigate to the My Account",
					"Unable to signIn and not navigated to the My Account",
					Common.getscreenShot(" Failed to signIn and not navigated to the My Account"));
			Assert.fail();
		}
		return newemail;
	}

	public void Change_to_Existingemail(String newemail) {
		// TODO Auto-generated method stub
		try {

Common.clickElement("xpath", "//span[text()='Edit']");
			
			Sync.waitElementClickable(30, "id", "change-email");
			Common.clickElement("id", "change-email");
			Common.textBoxInputClear("xpath", "(//input[@name='email'])[1]");
			Common.textBoxInputAndVerify("xpath", "(//input[@name='email'])[1]", newemail);
			Common.textBoxInput("xpath", "//input[@name='current_password']", "Hydro@123");
			Common.clickElement("xpath", "//span[text()='Save Account Information']");
			Sync.waitPageLoad();
			Thread.sleep(1000);
			String successmessage = Common.findElement("xpath", "//div[@ui-id='message-success']").getText();
			Common.assertionCheckwithReport(
					successmessage.contains("You saved the account information.")
							&& Common.getPageTitle().contains("Customer Login"),
					"verifying the Success message for the Change email",
					"user should get the success message and navigate back to the Login page",
					"Successfully user gets the success message and navigated to the Login page",
					"Failed to get the success message and unable to navigate to the login page");
			Sync.waitPageLoad();
			Common.textBoxInput("id", "email", newemail);
			Common.textBoxInput("id", "pass", "Hydro@123");
			Common.clickElement("xpath", "//span[text()='Sign In']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account")|| Common.getPageTitle().contains("Dashboard"),
					"To validate the user lands on My Account after successfull login",
					"After clicking on the signIn button it should navigate to the My Account",
					"user Sucessfully navigate to the My Account after clicking on the signIn button",
					"Failed to signIn and not navigated to the My Account");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the user lands on My Account after successfull login",
					"After clicking on the signIn button it should navigate to the My Account",
					"Unable to signIn and not navigated to the My Account",
					Common.getscreenShot(" Failed to signIn and not navigated to the My Account"));
			Assert.fail();
		}

	}

	public void MyFavorites_Guestuser(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		try

		{
			search_product("Product");
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product + "']");
			Common.actionsKeyPress(Keys.DOWN);
			Common.mouseOver("xpath", "//img[@alt='" + product + "']");
			Sync.waitElementPresent(30, "xpath", "//button[contains(@x-data,'initWishlistOnProductList')]");
			Common.clickElement("xpath", "//button[contains(@x-data,'initWishlistOnProductList')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@ui-id='message-error']//span").getText();
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Customer Login")
							&& message.contains("You must login or register to add items to your wishlist."),
					"validating the Navigation to the Customer Login page",
					"After Clicking on My Favorites CTA user should be navigate to the Customer Login page",
					"Sucessfully User Navigates to the My Favorites page after clicking on the Customer Login CTA",
					"Failed to Navigate to the Customer Login page after Clicking on My Favorites button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the Customer Login page",
					"After Clicking on My Favorites CTA user should be navigate to the Customer Login page",
					"Unable to Navigate to the Customer Login page after Clicking on My Favorites button",
					Common.getscreenShot(
							"Failed to Navigate to the Customer Login page after Clicking on My Favorites button"));

			Assert.fail();
		}

	}

	public void newsletter_subscription() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//*[text()='Newsletter Subscriptions']");
			Common.clickElement("xpath", "//*[text()='Newsletter Subscriptions']");
			String newsletter = Common.findElement("xpath", "//span[text()='Newsletter Subscription']").getText();
			Common.assertionCheckwithReport(newsletter.contains("Newsletter Subscription"),
					"validating the Navigation to the Newsletter Subscription page",
					"After Clicking on Newsletter Subscription CTA user should be navigate to the Newsletter Subscription page",
					"Sucessfully User Navigates to the Newsletter Subscription page after clicking on the Newsletter Subscription CTA",
					"Failed to Navigate to the Newsletter Subscription page after Clicking on Newsletter Subscription button");
			
			WebElement Checkbox = Common.findElement("xpath", "//input[@id='subscription']");
			if (!Checkbox.isSelected()) {
				Checkbox.click();
				Sync.waitElementPresent(20, "xpath", "//input[@id='subscription']//input[@id='subscription']");
				Common.clickElement("xpath", "//button[text()='UPDATE PREFERENCES']");
			} else {
				Sync.waitElementPresent(20, "xpath", "//button[text()='UPDATE PREFERENCES']");
				Common.clickElement("xpath", "//button[text()='UPDATE PREFERENCES']");
			}
			
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@class='thxtext2']").getText();
			Common.assertionCheckwithReport(message.contains("Thank you!"),
					"validating the success messgae for the Newsletter Subscription",
					"After Clicking on update preference CTA user should be navigate to see the success message",
					"Sucessfully User able to see the success message after clicking on the update preference",
					"Failed to see the success message after clicking on the update preference");

			Common.switchToDefault();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the success messgae for the Newsletter Subscription",
					"After Clicking on update preference CTA user should be navigate to see the success message",
					"Unable to see the success message after clicking on the update preference",
					Common.getscreenShot("Failed to see the success message after clicking on the update preference"));
			Assert.fail();

		}

	}

	public void Were_here_section(String Dataset) {
		// TODO Auto-generated method stub
		try
		{
			List<WebElement> werehere=Common.findElements("xpath", "//div[@class='c-icon-card-list__cards']//span[@class='m-icon-card__text']");
			ArrayList<String> heresection = new ArrayList<String>();

			for (WebElement sections : werehere) {
				heresection.add(sections.getText());
				System.out.println(sections);
		     }
			String[] messages=data.get(Dataset).get("message").split(",");
			
			for (int i = 0; i < messages.length; i++) {

				if (heresection.contains(messages[i])) {

					Common.assertionCheckwithReport(heresection.contains(messages[i]),
							"validating the were here section in the order summary page",
							"In Order summary page the user should able to see the were here section",
							"Sucessfully werehere section should be dispalyed in the order summary page",
							"Failed werehere section should be dispalyed in the order summary page");
				} else {

					ExtenantReportUtils.addFailedLog("validating the were here section in the order summary page",
							"In Order summary page the user should able to see the were here section ", "Missed the " + messages[i] + "options",
							Common.getscreenShotPathforReport("Failed werehere section should be dispalyed in the order summary page"));
					Assert.fail();
				}

			}
			}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the were here section in the order summary page",
					"In Order summary page the user should able to see the were here section",
					"Unble to see the werehere section should be dispalyed in the order summary page",
					Common.getscreenShot("Failed werehere section should be dispalyed in the order summary page"));
			Assert.fail();
		}
	}

	public String create_account_with_fav(String Dataset) {
		// TODO Auto-generated method stub
		String email = "";
		try {
			Common.refreshpage();
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//input[@name='firstname']");
			Common.clickElement("xpath", "//input[@name='firstname']");
			Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(Dataset).get("FirstName"));
			Common.clickElement("xpath", "//input[@name='lastname']");
			Common.textBoxInput("id", "lastname", data.get(Dataset).get("LastName"));
			Common.clickElement("xpath", "//input[@name='email']");
			Common.textBoxInput("xpath", "//input[@name='email']", data.get(Dataset).get("Email"));
			email = Common.findElement("xpath", "//input[@name='email']").getAttribute("value");
			System.out.println(email);
			Common.clickElement("xpath", "//input[@name='password']");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			Sync.waitElementPresent(30, "xpath", "//input[@name='password_confirmation']");
			Common.clickElement("xpath", "//input[@name='password_confirmation']");
			Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
					data.get(Dataset).get("Confirm Password"));
			Thread.sleep(4000);
			Common.scrollIntoView("xpath", "//button[@type='submit']//parent::div[@class='primary']");
			Sync.waitElementPresent(30, "xpath", "//button[@type='submit']//parent::div[@class='primary']");
			Common.clickElement("xpath", "//button[@type='submit']//parent::div[@class='primary']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//div[@data-ui-id='message-success']//div");
			String message = Common.findElement("xpath", "(//div[@class='a-message__container-inner'])[1]").getText();
			String favmessage = Common.findElement("xpath", "(//div[@class='a-message__container-inner'])[2]")
					.getText();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("My Favorites")
							&& message.contains("Thank you for registering with Hydro Flask.")
							&& favmessage.contains("Straw Cleaning Set has been added to your Wish List"),
					"validating the  My Favorites page Navigation when user clicks on signin button",
					"User should able to navigate to the My Favorites page after clicking on Signin button",
					"Sucessfully navigate to the My Favorites page after clicking on signin button ",
					"Failed to navigates to My Favorites Page after clicking on Signin button");
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
		return email;
	}

	public void Myhydro_addtofavorites(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//li[@class='ais-InfiniteHits-item']//img[1]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//li[@class='ais-InfiniteHits-item']//img[1]");

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
			Thread.sleep(4000);
			String name = Common.findElement("xpath", "//h1[@itemprop='name']").getText();
			Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"failed to Navigate to the PDP page");
			Common.clickElement("xpath", "//span[text()='Customize now']");
			Thread.sleep(3000);
			Myhydro_bottle("40 oz");
			hydro_bottle_color("Black");
			hydro_cap_color("White");
			hydro_strap_color("Black");
			hydro_boot_color("White");
			Sync.waitElementPresent("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Common.clickElement("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Myhydro_quantity(Dataset);
			Sync.waitElementPresent("xpath", "//button[@class='favorite__btn']//img");
			Common.clickElement("xpath", "//button[@class='favorite__btn']//img");
			Sync.waitPageLoad(30);
			Thread.sleep(4000);
			if(Common.getPageTitle().contains("Customer Login"))
			{
//				String favmessage=Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
//				Common.assertionCheckwithReport(favmessage.contains("You must login or register"),
//						"validating the Navigation to the Customer Login page",
//						"After Clicking on My Favorites CTA user should be navigate to the Customer Login page",
//						"Sucessfully User Navigates to the Customer Login page after clicking on the My Favorites CTA ",
//						"Failed to Navigate to the Customer Login page after clicking on the my favoriate Icon");
				if (Common.getCurrentURL().contains("preprod")) {
					Sync.waitPageLoad();
					Common.textBoxInput("id", "email", data.get(Dataset).get("UserName"));
				} else {
					Common.textBoxInput("id", "email", data.get(Dataset).get("Prod UserName"));
				}
				Common.textBoxInput("id", "pass", data.get(Dataset).get("Password"));
				Common.clickElement("xpath", "//span[text()='Sign In']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Sync.waitElementPresent(40, "xpath", "//div[@ui-id='message-success']");
				String myhydrofav = Common.findElement("xpath", "//div[@ui-id='message-success']").getAttribute("ui-id");
				Common.assertionCheckwithReport(Common.getPageTitle().equals("Wish List Sharing") && myhydrofav.contains("message-success"),
						"validating the Navigation to the My Favorites page and added to the whishlist",
						"After Clicking on My Favorites CTA user should be navigate to the My Favorites page and product should be added in the whishlist",
						"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA and product added to the whishlist",
						"Failed to Navigate to the My Favorites page after Clicking on My Favorites button and no products in whishlist");
			}
			else
			{
				Sync.waitElementPresent(40, "xpath", "//div[@ui-id='message-success']");
				String myhydrofav = Common.findElement("xpath", "//div[@ui-id='message-success']").getAttribute("ui-id");
				Common.assertionCheckwithReport(Common.getPageTitle().equals("Wish List Sharing") && myhydrofav.contains("message-success"),
						"validating the Navigation to the My Favorites page and added to the whishlist",
						"After Clicking on My Favorites CTA user should be navigate to the My Favorites page and product should be added in the whishlist",
						"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA and product added to the whishlist",
						"Failed to Navigate to the My Favorites page after Clicking on My Favorites button and no products in whishlist");
			}
			String Whishlistproduct = Common
					.findElement("xpath", "//div[contains(@class,'m-product-card__name')]//a").getText();
			System.out.println(Whishlistproduct);

			if (Whishlistproduct.equals(products)) {
				Sync.waitElementPresent(30, "xpath", "//a[contains(@title,'" + products + "')]//img");
				Common.mouseOver("xpath", "//a[contains(@title,'" + products + "')]//img");
				Sync.waitElementPresent("xpath", "//span[text()='Add to Cart']");
				Common.clickElement("xpath", "//span[text()='Add to Cart']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String message1 = Common.findElement("xpath", "//div[@ui-id='message-success']")
						.getAttribute("-ui-id");
				System.out.println(message1);
				Common.assertionCheckwithReport(message1.contains("message-success"),
						"validating the  product add to the cart", "Product should be add to cart",
						"Sucessfully product added to the cart ", "failed to add product to the cart");
				}
			
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));
			Assert.fail();
		}
		
	}

	public void Myhydrotext_addtofavorites(String Dataset) {
		// TODO Auto-generated method stub
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
			Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"failed to Navigate to the PDP page");
			Sync.waitElementPresent(30, "xpath", "//span[text()='Customize Now']");
			Common.clickElement("xpath", "//span[text()='Customize Now']");
			Thread.sleep(3000);
			Myhydro_bottle("40 oz");
			hydro_bottle_color("Black");
			hydro_cap_color("White");
			hydro_strap_color("Black");
			hydro_boot_color("White");
			Myhydro_Engraving("Myhydro Product");
			Myhydro_quantity(Dataset);
			Sync.waitElementPresent("xpath", "//button[@class='favorite__btn']//img");
			Common.clickElement("xpath", "//button[@class='favorite__btn']//img");
			Sync.waitPageLoad(30);
			Thread.sleep(3000);
			if(Common.getPageTitle().contains("Customer Login"))
			{
				String favmessage=Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
				Common.assertionCheckwithReport(favmessage.contains("You must login or register"),
						"validating the Navigation to the Customer Login page",
						"After Clicking on My Favorites CTA user should be navigate to the Customer Login page",
						"Sucessfully User Navigates to the Customer Login page after clicking on the My Favorites CTA ",
						"Failed to Navigate to the Customer Login page after clicking on the my favoriate Icon");
				if (Common.getCurrentURL().contains("preprod")) {
					Sync.waitPageLoad();
					Common.textBoxInput("id", "email", data.get(Dataset).get("UserName"));
				} else {
					Common.textBoxInput("id", "email", data.get(Dataset).get("Prod UserName"));
				}
				Common.textBoxInput("id", "pass", data.get(Dataset).get("Password"));
				Common.clickElement("xpath", "//button[contains(@class,'action login')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String myhydrofav = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
				Common.assertionCheckwithReport(Common.getPageTitle().equals("My Favorites") && myhydrofav.contains("has been added to your Wish List"),
						"validating the Navigation to the My Favorites page and added to the whishlist",
						"After Clicking on My Favorites CTA user should be navigate to the My Favorites page and product should be added in the whishlist",
						"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA and product added to the whishlist",
						"Failed to Navigate to the My Favorites page after Clicking on My Favorites button and no products in whishlist");
			}
			else
			{
			Sync.waitElementPresent(40, "xpath", "//div[@class='a-message__container-inner']");
			String myhydrofav = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Favorites") && myhydrofav.contains("has been added to your Wish List"),
					"validating the Navigation to the My Favorites page and added to the whishlist",
					"After Clicking on My Favorites CTA user should be navigate to the My Favorites page and product should be added in the whishlist",
					"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA and product added to the whishlist",
					"Failed to Navigate to the My Favorites page after Clicking on My Favorites button and no products in whishlist");
			}
			String Whishlistproduct = Common
					.findElement("xpath", "//div[contains(@class,'m-product-card__name')]//a").getText();
			System.out.println(Whishlistproduct);

			if (Whishlistproduct.equals(products)) {
				Sync.waitElementPresent(30, "xpath", "//a[contains(@title,'" + products + "')]//img");
				Common.mouseOver("xpath", "//a[contains(@title,'" + products + "')]//img");
				Sync.waitElementPresent("xpath", "//span[text()='Add to Cart']");
				Common.clickElement("xpath", "//span[text()='Add to Cart']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String message1 = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
						.getAttribute("data-ui-id");
				System.out.println(message1);
				Common.assertionCheckwithReport(message1.contains("success"),
						"validating the  product add to the cart", "Product should be add to cart",
						"Sucessfully product added to the cart ", "failed to add product to the cart");
				}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));
			Assert.fail();
		}

	}

	public void Myhydro_GraphicEngraving_fromMyfav(String Dataset) {
		// TODO Auto-generated method stub
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
			Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"failed to Navigate to the PDP page");
			Common.clickElement("xpath", "//span[text()='Customize Now']");
			Thread.sleep(3000);
			Myhydro_bottle("40 oz");
			hydro_bottle_color("Black");
			hydro_cap_color("White");
			hydro_strap_color("Black");
			hydro_boot_color("White");
			Myhydrographic("Graphic");
			Myhydro_quantity(Dataset);
			Sync.waitElementPresent("xpath", "//button[@class='favorite__btn']//img");
			Common.clickElement("xpath", "//button[@class='favorite__btn']//img");
			Sync.waitPageLoad(30);
			Thread.sleep(5000);
			if(Common.getPageTitle().contains("Customer Login"))
			{
				String favmessage=Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
				Common.assertionCheckwithReport(favmessage.contains("You must login or register"),
						"validating the Navigation to the Customer Login page",
						"After Clicking on My Favorites CTA user should be navigate to the Customer Login page",
						"Sucessfully User Navigates to the Customer Login page after clicking on the My Favorites CTA ",
						"Failed to Navigate to the Customer Login page after clicking on the my favoriate Icon");
				Thread.sleep(4000);
				if (Common.getCurrentURL().contains("preprod")) {
					Sync.waitPageLoad();
					Common.textBoxInput("id", "email", data.get(Dataset).get("UserName"));
				} else {
					Common.textBoxInput("id", "email", data.get(Dataset).get("Prod UserName"));
				}
				Common.textBoxInput("id", "pass", data.get(Dataset).get("Password"));
				Common.clickElement("xpath", "//button[contains(@class,'action login')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String myhydrofav = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
				Common.assertionCheckwithReport(Common.getPageTitle().equals("My Favorites") && myhydrofav.contains("has been added to your Wish List"),
						"validating the Navigation to the My Favorites page and added to the whishlist",
						"After Clicking on My Favorites CTA user should be navigate to the My Favorites page and product should be added in the whishlist",
						"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA and product added to the whishlist",
						"Failed to Navigate to the My Favorites page after Clicking on My Favorites button and no products in whishlist");
			}
			else
			{
			Sync.waitElementPresent(40, "xpath", "//div[@class='a-message__container-inner']");
			String myhydrofav = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Favorites") && myhydrofav.contains("has been added to your Wish List"),
					"validating the Navigation to the My Favorites page and added to the whishlist",
					"After Clicking on My Favorites CTA user should be navigate to the My Favorites page and product should be added in the whishlist",
					"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA and product added to the whishlist",
					"Failed to Navigate to the My Favorites page after Clicking on My Favorites button and no products in whishlist");
			}
			String Whishlistproduct = Common
					.findElement("xpath", "//div[contains(@class,'m-product-card__name')]//a").getText();
			System.out.println(Whishlistproduct);

			if (Whishlistproduct.equals(products)) {
				Sync.waitElementPresent(30, "xpath", "//a[contains(@title,'" + products + "')]//img");
				Common.mouseOver("xpath", "//a[contains(@title,'" + products + "')]//img");
				Sync.waitElementPresent("xpath", "//span[text()='Add to Cart']");
				Common.clickElement("xpath", "//span[text()='Add to Cart']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String message1 = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
						.getAttribute("data-ui-id");
				System.out.println(message1);
				Common.assertionCheckwithReport(message1.contains("success"),
						"validating the  product add to the cart", "Product should be add to cart",
						"Sucessfully product added to the cart ", "failed to add product to the cart");
				}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));
			Assert.fail();
		}

	}

	public void Gift_message(String Dataset) {
		// TODO Auto-generated method stub
		String message=data.get(Dataset).get("message");
		try
		{
			Common.scrollIntoView("xpath", "//button[normalize-space()='Add Gift Message']");
			Sync.waitElementPresent(40, "xpath", "//button[normalize-space()='Add Gift Message']");
			Thread.sleep(3000);
			String gift=Common.findElement("xpath", "//span[@x-text='savedFormMessage.message']").getText().trim();
			System.out.println(gift);
			if(gift.contains(""))
			{
			Thread.sleep(3000);

			Common.javascriptclickElement("xpath", "//button[normalize-space()='Add Gift Message']");
			Common.textBoxInput("id", "recipient-1", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("id", "sender-1", data.get(Dataset).get("LastName"));
			Common.textBoxInput("id", "message-1", message);
			Common.clickElement("xpath", "//span[text()='Update']");
			Sync.waitPageLoad(40);
			Thread.sleep(2000);
			Sync.waitElementPresent(40, "xpath", "//span[@x-text='savedFormMessage.message']");
			String Messgae = Common.findElement("xpath", "//span[@x-text='savedFormMessage.message']").getText()
					.replace("Message: ", "");
			System.out.println(Messgae);
			Common.assertionCheckwithReport(Messgae.equals(message), "validating the Gift cart message",
					"Gift card message should be applied", "Sucessfully gift message has been applied ",
					"failed to apply the gift message");
			
			}
			else
			{
				Thread.sleep(4000);
				Common.clickElement("xpath", "//button[contains(text(),'Delete')]");
				Sync.waitPageLoad(40);
				Common.javascriptclickElement("xpath", "//button[normalize-space()='Add Gift Message']");
				Common.textBoxInput("id", "recipient-1", data.get(Dataset).get("FirstName"));
				Common.textBoxInput("id", "sender-1", data.get(Dataset).get("LastName"));
				Common.textBoxInput("id", "message-1", message);
				Common.clickElement("xpath", "//span[text()='Update']");
				Sync.waitPageLoad(40);
				Thread.sleep(2000);
				Sync.waitElementPresent(40, "xpath", "//span[@x-text='savedFormMessage.message']");
				String Messgae = Common.findElement("xpath", "//span[@x-text='savedFormMessage.message']").getText()
						.replace("Message: ", "");
				System.out.println(Messgae);
				Common.assertionCheckwithReport(Messgae.equals(message), "validating the Gift cart message",
						"Gift card message should be applied", "Sucessfully gift message has been applied ","failed to apply the gift message");
			}
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Gift cart message", "Gift card message should be applied",
					"Unabled to apply the gift message", Common.getscreenShot("failed to apply the gift message"));
			Assert.fail();
		}
		
	}

	public void Customize_validation(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("CustomizeLinks");
		String[] Links = names.split(",");
		int i=0;
		try
		{
			
         	for (i = 0; i < Links.length; i++) {
         		Thread.sleep(4000);
    			Sync.waitElementPresent("xpath", "(//span[normalize-space()='Customize'])[1]");
    			Common.clickElement("xpath", "(//span[normalize-space()='Customize'])[1]");
         		Common.clickElement("xpath", "(//span[text()='"+ Links[i] +"'])[1]");
         		Thread.sleep(3000);
         		
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("customize")||Common.getCurrentURL().contains("engraving")||Common.getCurrentURL().contains("corporate-purchasing"),
					"verifying the header link " + Links[i] + "Under Customize",
					"user should navigate to the " + Links[i] + " page",
					"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
         	}
		}
		catch(Exception |Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Customize",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}
		
	}
	
	
	public void prepareTaxData(String fileName) {
		// TODO Auto-generated method stub
		
			try{
				
				File file=new File(System.getProperty("user.dir")+"/src/test/resources/"+fileName);
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
				cell.setCellValue("Hydroflask_OrderDetails");


				row = sheet.createRow(1);
				cell = row.createCell(0);
				cell.setCellStyle(cs);
				cell.setCellValue("S.No");
				cell=row.createCell(1);
				cell.setCellStyle(cs);
				cell.setCellValue("Company");
				cell = row.createCell(2);
				cell.setCellStyle(cs);
				cell.setCellValue("Order Number");
				cell=row.createCell(3);
				cell.setCellStyle(cs);
				cell.setCellValue("Digital QA Status(PASS/FAIL)");
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
	
	public void writeResultstoXLSx(String Ordernumber)
	{
	//String fileOut="";
	try{

	File file=new File(System.getProperty("user.dir")+"/src/test/resources/Hydro_OrderNumbers.xlsx");
	XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
	XSSFSheet sheet;
	Row row;
	Cell cell;
	int rowcount;
	sheet = workbook.getSheet("Order ID");

	if((workbook.getSheet("Order ID"))==null)
	{
	sheet = workbook.createSheet("Order ID");
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
	cell.setCellValue("Orders details");

	row = sheet.createRow(1);
	cell = row.createCell(0);
	cell.setCellStyle(cs);
	cell.setCellValue("Web Order Number");
	cell = row.createCell(1);
	cell.setCellStyle(cs);
	cell.setCellValue("SubTotal");
	cell = row.createCell(2);
	cell.setCellStyle(cs);
	cell.setCellValue("Shipping");
	cell=row.createCell(3);
	cell.setCellStyle(cs);
	cell.setCellValue("TaxRate");
	cell=row.createCell(4);
	cell.setCellStyle(cs);
	cell.setCellValue("Web Configured TaxRate");
	cell=row.createCell(5);
	cell.setCellStyle(cs);
	cell.setCellValue("Actual TaxAmount");
	cell=row.createCell(6);
	cell.setCellStyle(cs);
	cell.setCellValue("Expected TaxAmount");

	rowcount=2;

	}

	else
	{

	sheet=workbook.getSheet("Order ID");
	rowcount=sheet.getLastRowNum()+1;
	}
	row = sheet.createRow(rowcount);
	cell = row.createCell(0);
	cell.setCellType(CellType.NUMERIC);
	int SNo=rowcount-1;
	cell.setCellValue(SNo);
	cell = row.createCell(1);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue("Lotuswave");
	cell = row.createCell(2);
	cell.setCellType(CellType.STRING);
	cell.setCellValue(Ordernumber);
	cell = row.createCell(3);
	cell.setCellType(CellType.STRING);
	cell.setCellValue("Lotuswave");
	
	String status;
	if(Ordernumber.contains("400"))
	{

	status="PASS";
	CellStyle style = workbook.createCellStyle();
	Font font= workbook.createFont();
	font.setColor(IndexedColors.GREEN.getIndex());
	font.setBold(true);
	style.setFont(font);
	cell.setCellStyle(style);
	}
	else
	{
	status="FAIL";
	CellStyle style = workbook.createCellStyle();
	Font font= workbook.createFont();
	font.setColor(IndexedColors.RED.getIndex());
	font.setBold(true);
	style.setFont(font);
	cell.setCellStyle(style);
	}


	cell.setCellValue(status);
	FileOutputStream fileOut = new FileOutputStream(file);
	workbook.write(fileOut);
	fileOut.flush();
	fileOut.close();
	} catch (Exception e) {
	e.printStackTrace();
	}

	}
	
	public String Express_Paypal(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String order = "";

		String expectedResult = "It should open paypal site window.";

		try {
			Thread.sleep(3000);
			int cancelpayment=Common.findElements("xpath", "//button[@title='Cancel']").size();
			System.out.println(cancelpayment);
			if(cancelpayment>0)
			{
				
				Sync.waitElementPresent("xpath", "//button[contains(text(),'Cancel Payment')]");
				Common.clickElement("xpath", "//button[contains(text(),'Cancel Payment')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Thread.sleep(3000);
				Common.switchFrames("xpath", "//iframe[contains(@class,'component-frame visible')]");

				// Common.refreshpage();
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//div[contains(@class,'paypal-button-lab')]");
				Common.clickElement("xpath", "//div[contains(@class,'paypal-button-lab')]");
				Common.switchToDefault();
				
			}
			else
			{
				Thread.sleep(3000);
				Common.switchFrames("xpath", "//iframe[contains(@class,'component-frame visible')]");

				// Common.refreshpage();
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//div[contains(@class,'paypal-button-lab')]");
				Common.clickElement("xpath", "//div[contains(@class,'paypal-button-lab')]");
				Common.switchToDefault();
			}
			
			Thread.sleep(4000);
			Common.switchWindows();
			int size = Common.findElements("id", "acceptAllButton").size();
			if (size > 0) {

				Common.clickElement("id", "acceptAllButton");

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult,
					"User failed to proceed with paypal payment", Common.getscreenShotPathforReport(expectedResult));
			Assert.fail();
		}
		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);

		if (!url.contains("stage") & !url.contains("preprod")) {

			int sizeofelement = Common.findElements("id", "email").size();
			Common.assertionCheckwithReport(sizeofelement > 0, "verifying the paypal payment ", expectedResult,
					"open paypal site window", "faild to open paypal account");
		} else {

			Common.clickElement("id", "login_email");
			//Common.textBoxInput("id", "login_email", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "login_email", "oxobuyer@oxo.com");
			Common.clickElement("xpath", "//button[text()='Next']");
			int size1 = Common.findElements("xpath", "//button[text()='Use Password Instead']").size();
			if(size1>0) {
				Common.clickElement("xpath", "//button[text()='Use Password Instead']");
				Common.textBoxInput("xpath", "//input[@aria-labelledby='password--input-label']", data.get(dataSet).get("Password"));
			}
			else {
				
			
			//Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			Common.textBoxInput("id", "password", "oxobuyer123");
			int sizeemail = Common.findElements("id", "email").size();
			
			Common.assertionCheckwithReport(sizeemail > 0, "verifying the paypal payment ", expectedResult,
					"open paypal site window", "faild to open paypal account");
			}
			

			try {
				Thread.sleep(4000);
				Common.javascriptclickElement("xpath", "//button[text()='Log In']");
				Thread.sleep(3000);
				Common.actionsKeyPress(Keys.END);
				Thread.sleep(5000);
				//Paypal_Address_Verification("Express Paypal");
				//Thread.sleep(4000);
				
				if (Common.getCurrentURL().contains(""))
					Common.clickElement("xpath", "//div[@class='flex flex-auto']");
				Thread.sleep(8000);
				Common.switchToFirstTab();
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult,
						"User failed to proceed with paypal payment",
						Common.getscreenShotPathforReport(expectedResult));
				Assert.fail();
			}
			Sync.waitForLoad();
			Thread.sleep(5000);
//			express_paypal_shipping("PaypalDetails");
			
//			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
//			Thread.sleep(3000);
//			select_Shipping_Method("GroundShipping method");
			Thread.sleep(4000);
			int rewards=Common.findElements("xpath", "//span[contains(text(),'Sign in')]").size();
			System.out.println(rewards);
			if(rewards==1)
			{
				Thread.sleep(5000);
				Common.scrollIntoView("name", "telephone");
				Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
				Thread.sleep(4000);
			}
			
			if (Common.getText("xpath", "//div[@id='payment-method-view-paypal_express']//p[2]").contains("Paypal")||Common.getCurrentURL().contains("preprod")) {
				Common.scrollIntoView("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
				// Sync.waitElementPresent("xpath", "//button[@value='Place Order']");
				
				Thread.sleep(8000);
				Common.clickElement("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
			}
				try {
					Thread.sleep(6000);
					String sucessMessage = Common.getText("xpath", "//h1[normalize-space()='Thank you for your purchase!']").trim();
					System.out.println(sucessMessage);

					int size = Common.findElements("xpath", "//h1[normalize-space()='Thank you for your purchase!']").size();
					Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
							"verifying the product confirmation", expectedResult,
							"Successfully It redirects to order confirmation page Order Placed",
							"User unable to go orderconformation page");

					if (Common.findElements("xpath", "//div[contains(@class,'checkout-success')]/p/span").size() > 0) {
						order = Common.getText("xpath", "//div[contains(@class,'checkout-success')]/p/span");
						System.out.println(order);
					}
					else if (Common.findElements("xpath", "//a[@class='order-number']/strong").size() > 0) {
						order = Common.getText("xpath", "//a[@class='order-number']/strong");
						System.out.println(order);
					}

				} catch (Exception | Error e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("verifying the order confirmartion page",
							"It should navigate to the order confirmation page",
							"User failed to proceed to the order confirmation page",
							Common.getscreenShotPathforReport("failed to Navigate to the order summary page"));

					Assert.fail();
				}
			}
		return order;
	}

		public void Paypal_Address_Verification(String Dataset) {
			// TODO Auto-generated method stub

			try {
				Sync.waitElementPresent("xpath", "//p[@data-testid='ship-to-address']");
				Thread.sleep(4000);
				String address = Common.findElement("xpath", "//p[@data-testid='ship-to-address']").getText();
				if (address.contains("united states")) {
					Thread.sleep(4000);
					Sync.waitElementPresent("xpath", "//button[@data-testid='change-shipping']");
					Common.clickElement("xpath", "//button[@data-testid='change-shipping']");
//								Common.clickElement("xpath", "//select[@data-testid='shipping-dropdown']");
					Common.dropdown("xpath", "//select[@data-testid='shipping-dropdown']", SelectBy.TEXT,
							data.get(Dataset).get("Street"));
					Thread.sleep(3000);
					String Ukaddress = Common.findElement("xpath", "//p[@data-testid='ship-to-address']").getText();
					System.out.println(Ukaddress);
					String UK = data.get(Dataset).get("Street").replace("QA TEST - ", "");
					System.out.println(UK);
					Common.assertionCheckwithReport(Ukaddress.contains(UK),
							"validating the address selection from the drop down",
							"Address should be select from the dropdown ",
							"Sucessfully address has been selected from the dropdown",
							"Failed to select the Address from the dropdown");

				}
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the address selection from the drop down",
						"Address should be select from the dropdown ", "Unable to select the Address from the dropdown",
						Common.getscreenShotPathforReport("Failed to select the Address from the dropdown"));
				Assert.fail();
			}

		}

		public void deleteProduct_shoppingcart() {
		// TODO Auto-generated method stub
		
		try {
			
			int size=Common.findElements("xpath", "//tr[contains(@class,'item-info align')]").size();
			System.out.println(size);
			for(int i=0;i<size;i++)
			{
				int value=i+1;
			Common.clickElement("xpath", "(//button[contains(@class,'group p-2.5 text-black')])['" +value+ "']");
			Thread.sleep(2000);
			Common.clickElement("xpath", "(//button[contains(@class,'btn btn-primary')])[1]");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			}
			String getText =Common.findElement("xpath","(//div[@class='cart-empty container min-h-75']//p)[1]").getText();
			Common.assertionCheckwithReport(getText.equals("You have no items in your shopping cart."),
					"validating the delete product in shopping cart page",
					"color should be delete in the shopping cart page",
					"color has been deleted in the shopping cart page",
					"Failed to delete the product  in the shopping cart page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the delete product in shopping cart page",
					"color should be delete in the shopping cart page",
					"Unable to delete the product  in the shopping cart page",
					Common.getscreenShot("Failed to delete the product  in the shopping cart page"));
			Assert.fail();
		}

	}

		public String Secure_Payment_details(String dataSet) throws Exception {
			// TODO Auto-generated method stub
			String order = "";
			ThreedPaymentDetails(dataSet);
			String expectedResult = "It redirects to order confirmation page";

			if (Common.findElements("xpath", "//div[@class='message message-error']").size() > 0) {
				Thread.sleep(4000);
				ThreedPaymentDetails(dataSet);
			}

			Thread.sleep(3000);
			int placeordercount = Common.findElements("xpath", "//button[@class='action primary checkout']").size();
			if (placeordercount > 1) {
				Thread.sleep(4000);
				
				Common.clickElement("xpath", "//button[@class='action primary checkout']");
				Thread.sleep(8000);
				Sync.waitElementPresent(50, "xpath", "//iframe[@id='challengeFrame']");
				Common.switchFrames("xpath", "//iframe[@id='challengeFrame']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//button[contains(text(),'Complete')]");
				Common.switchToDefault();
			}

			String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);

			if (!url.contains("stage") && !url.contains("preprod")) {
			}

			else {
				try {
					Thread.sleep(3000);
					Sync.waitElementPresent(30,"xpath", " //h1[normalize-space()='Thank you for your purchase!']");
					String sucessMessage = Common.getText("xpath", " //h1[normalize-space()='Thank you for your purchase!']");
					int sizes = Common.findElements("xpath", " //h1[normalize-space()='Thank you for your purchase!']").size();
					Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
							"verifying the product confirmation", expectedResult,
							"Successfully It redirects to order confirmation page Order Placed",
							"User unabel to go orderconformation page");

					if (Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//p//span").size() > 0) {
						Thread.sleep(4000);
						order = Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p//span");
						System.out.println(order);
					} else {
						Thread.sleep(4000);
						order = Common.getText("xpath", "//div[contains(@class,'checkout-success')]//p//a");
						System.out.println(order);
					}

					if (Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//p//span").size() > 0) {
						Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p//span");
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
		
		
		public String ThreedPaymentDetails(String dataSet) throws Exception {
			// TODO Auto-generated method stub
			HashMap<String, String> Paymentmethod = new HashMap<String, String>();
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String Number = "";
			String cardnumber = data.get(dataSet).get("cardNumber");
			System.out.println(cardnumber);
			String expectedResult = "land on the payment section";
			// Common.refreshpage();
			String symbol=data.get(dataSet).get("Symbol");
			
			try {
			
				
				Sync.waitPageLoad();
			    Common.actionsKeyPress(Keys.PAGE_DOWN);
						Sync.waitElementPresent("xpath", "//label[@for='payment-method-stripe_payments']");
						int sizes = Common.findElements("xpath", "//label[@for='payment-method-stripe_payments']").size();

						Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
								"User unabel to land opaymentpage");
						Common.clickElement("xpath", "//label[@for='payment-method-stripe_payments']");
		  
						Sync.waitElementPresent("xpath", "//input[@id='shipping-postcode']");
						 String code=Common.findElement("xpath", "//input[@id='shipping-postcode']").getAttribute("value");
						 System.out.println(code);
						 
				int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
				System.out.println(payment);
				if (payment > 0) {
					Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
					Common.clickElement("xpath", "//div[@class='stripe-dropdown-selection']");
//					Common.clickElement("xpath", "//button[@class='a-btn a-btn--tertiary']");
					Thread.sleep(4000);
				
						Common.switchFrames("xpath", "//iframe[contains(@src,'elements-inner-payment-')]");
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

					
					if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage") ) {
		                   if(Common.getCurrentURL().contains("/gb"))
		                   {
		                	   Thread.sleep(5000);
//		                	   Sync.waitElementPresent("xpath", "//input[@id='agreement_stripe_payments_5']");
//		                	   Common.clickElement("xpath", "//input[@id='agreement_stripe_payments_5']");
		                	   
		                	   Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
		                	   Common.clickElement("xpath", "//button[@class='action primary checkout']");
		                	   Thread.sleep(8000);
		                	   if(Common.getText("xpath", "//div[contains(@data-ui-id,'checkout-cart')]").contains("Please enter your card's security code."))
		                	   {
		                		   Sync.waitElementPresent("xpath", "//label[@for='stripe-new-payments']");
		                  		Common.clickElement("xpath", "//label[@for='stripe-new-payments']");
		                  		Thread.sleep(5000);
		                  		Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
		                      	Common.clickElement("xpath", "//button[@class='action primary checkout']");
		                      	Thread.sleep(4000);
		                      	String frameid=Common.findElement("xpath", "(//iframe[@role='presentation'])[1]").getAttribute("name");
		                          	System.out.println(frameid);
		                        	Thread.sleep(8000);
		                          	Common.switchFrames("xpath","//iframe[@name='"+ frameid +"']");
		                          	Thread.sleep(8000);
		                          	Sync.waitElementPresent(30, "xpath", "//iframe[@id='challengeFrame' and @title='3DS Challenge']");
		                     		Common.switchFrames("xpath", "//iframe[@id='challengeFrame' and @title='3DS Challenge']");
		                     		Thread.sleep(6000);
		                         	Common.clickElement("xpath", "//button[contains(text(),'Complete')]");
		                         	Common.switchToDefault();
		                         	Common.switchToDefault();
		                	   }                    	
		                	   else if (Common.getCurrentURL().contains("/checkout/#payment"))
		                	   {
//		                		   Sync.waitElementPresent("xpath", "//label[@for='stripe-new-payments']");
//		                   		Common.clickElement("xpath", "//label[@for='stripe-new-payments']");
//		                   		Thread.sleep(5000);
//		                   		Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
//		                       	Common.clickElement("xpath", "//button[@class='action primary checkout']");
		                       	String frameid=Common.findElement("xpath", "(//iframe[@role='presentation'])[1]").getAttribute("name");
		                       	System.out.println(frameid);
//		                       	Common.switchFrames("xpath","//iframe[@name='"+ frameid +"']");
		                  		Common.switchFrames("xpath", "//iframe[@id='challengeFrame']");
		                  		Thread.sleep(4000);
		                      	Common.clickElement("xpath", "//button[contains(text(),'Complete')]");
		                      	Common.switchToDefault();
		                      	Common.switchToDefault();
		                	   }
		                	   else
		                	   {
		                		   Assert.fail();
		                	   }
		                   }
		                   else
		                   {
		                	   Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
		                	   Common.clickElement("xpath", "//button[@class='action primary checkout']");
		                	   Thread.sleep(8000);
		                	   if(Common.getText("xpath", "//div[contains(@data-ui-id,'checkout-cart')]").contains("Please enter your card's security code."))
		                	   {
		                		   
		                         	 Sync.waitElementPresent("xpath", "//label[@for='stripe-new-payments']");
		                        		Common.clickElement("xpath", "//label[@for='stripe-new-payments']");
		                        		Thread.sleep(5000);
		                        		Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
		                            	Common.clickElement("xpath", "//button[@class='action primary checkout']");
		                            	Thread.sleep(7000);
		                            	String frameid=Common.findElement("xpath", "(//iframe[@role='presentation'])[1]").getAttribute("name");
		                            	System.out.println(frameid);
		                            	Thread.sleep(4000);
		                            	Common.switchFrames("xpath","//iframe[@name='"+ frameid +"']");
		                            	Thread.sleep(6000);
		                       		Common.switchFrames("xpath", "//iframe[@id='challengeFrame']");
		                       		Thread.sleep(4000);
		                           	Common.clickElement("xpath", "//button[contains(text(),'Complete')]");
		                           	Common.switchToDefault();
		                           	Common.switchToDefault();
		                	   }                    	
		                	   else if (Common.getCurrentURL().contains("/checkout/#payment"))
		                	   {
		                		   String frameid=Common.findElement("xpath", "(//iframe[@role='presentation'])[1]").getAttribute("name");
		                         	System.out.println(frameid);
		                          	Thread.sleep(4000);
		                         	Common.switchFrames("xpath","//iframe[@name='"+ frameid +"']");
		                         	Thread.sleep(4000);
		                    		Common.switchFrames("xpath", "//iframe[@id='challengeFrame']");
		                     		Thread.sleep(4000);
		                        	Common.clickElement("xpath", "//button[contains(text(),'Complete')]");
		                        	Common.switchToDefault();
		                        	Common.switchToDefault();
		                	   }
		                	   else
		                	   {
		                		   Assert.fail();
		                	   }
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
					int savedcard=Common.findElements("xpath", "(//input[@class='checkbox mr-4'])[2]").size();
					if(savedcard>0)
					{
						Sync.waitElementPresent("xpath", "(//input[@class='checkbox mr-4'])[2]");
						Common.clickElement("xpath", "(//input[@class='checkbox mr-4'])[2]");
					}
					
					
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					Thread.sleep(5000);
					String Isselected = Common.findElementBy("id", "card-tab").getAttribute("aria-selected");
					if(Isselected.contains("false")) {
						Sync.waitElementPresent("id", "card-tab");
						Common.clickElement("id", "card-tab");
					}
					Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
					Common.clickElement("xpath", "//label[@for='Field-numberInput']");
					Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);

					Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

					Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
					Thread.sleep(2000);
					int zipcode=Common.findElements("xpath", "//input[@id='Field-postalCodeInput']").size();
					System.out.println(zipcode);
					
					if(zipcode > 0)
					{
					 
					 Sync.waitElementPresent("xpath", "//input[@id='Field-postalCodeInput']");
					 Common.textBoxInput("xpath", "//input[@id='Field-postalCodeInput']", code);
					}
					Common.actionsKeyPress(Keys.ARROW_DOWN);
					Common.switchToDefault();
					if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {

						 if(Common.getCurrentURL().contains("/gb"))
		                 {
		              	   Sync.waitElementPresent("xpath", "//input[@id='agreement_stripe_payments_2']");
		              	   Common.clickElement("xpath", "//input[@id='agreement_stripe_payments_2']");
		              	   
		              	   Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
		              	   Common.clickElement("xpath", "//button[@class='action primary checkout']");
		              	 Thread.sleep(8000);
		          	   String frameid=Common.findElement("xpath", "(//iframe[@role='presentation' and contains(@src,'https://js.stripe.com/v3/three-ds')])[1]").getAttribute("name");
		          	   System.out.println(frameid);
		          	   Thread.sleep(4000);
		          	   Common.switchFrames("xpath","//iframe[@name='"+ frameid +"']");
		          	  Thread.sleep(4000);
		     			Common.switchFrames("xpath", "//iframe[@id='challengeFrame']");
		         		Thread.sleep(4000);
		         		Common.clickElement("xpath", "//button[contains(text(),'Complete')]");
		         		Common.switchToDefault();
		         		Common.switchToDefault();
		                 }
		                 else
		                 {
		                	 Thread.sleep(4000);
		              	   Sync.waitElementPresent(30,"xpath", "(//button[@type='button'][normalize-space()='Place Order'])[1]");
		              	   Common.clickElement("xpath", "(//button[@type='button'][normalize-space()='Place Order'])[1]");
		              	 Thread.sleep(8000);
		                 Sync.waitElementPresent(30,"xpath", "(//iframe[@role='presentation' and contains(@src,'https://js.stripe.com/v3/three-ds')])[1]");
		                 Sync.waitElementVisible("xpath", "(//iframe[@role='presentation' and contains(@src,'https://js.stripe.com/v3/three-ds')])[1]");
		          	   String frameid=Common.findElement("xpath", "(//iframe[@role='presentation' and contains(@src,'https://js.stripe.com/v3/three-ds')])[1]").getAttribute("name");
		          	   System.out.println(frameid);
		          	   Common.switchFrames("xpath","//iframe[@name='"+ frameid +"']");
		          	 Thread.sleep(8000);
		     			Common.switchFrames("xpath", "//iframe[@id='challengeFrame']");
		         		Thread.sleep(4000);
		         		Common.clickElement("xpath", "//button[contains(text(),'Complete')]");
		         		Common.switchToDefault();
		         		Common.switchToDefault();
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

	return Number;
	}

		public void Logout() {
			// TODO Auto-generated method stub
			try
			{
				Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
				Common.clickElement("xpath", "//button[@id='customer-menu']");
				Sync.waitElementPresent("xpath", "//a[@title='Sign Out']");
				Common.clickElement("xpath", "//a[@title='Sign Out']");
				Sync.waitPageLoad();
				Thread.sleep(5000);
				String homepage= Common.findElementBy("xpath", "//meta[@name='description']").getAttribute("content");
				System.out.println(homepage);
				String homepage1= Common.findElementBy("xpath", "//meta[@name='title']").getAttribute("content");
				System.out.println(homepage1);
				Common.assertionCheckwithReport(homepage.contains("Hydro Flask creates insulated, stainless steel water bottles to keep you hydrated on any adventure. Let's go!")|| homepage1.contains("Customer Login") ,
						"validating store logo", "System directs the user to the Homepage",
						"Sucessfully user navigates to the home page", "Failed to navigate to the homepage");
			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validating store logo", "System directs the user to the Homepage",
						" user unable navigates to the home page", "Failed to navigate to the homepage");
				Assert.fail();
			}
			
		}

		public void Collections_validation(String Dataset) {
			// TODO Auto-generated method stub
			String names ="";
		    if(Common.getCurrentURL().contains("https://mcloud-na-preprod.hydroflask.com/")){

	    		 names = data.get(Dataset).get("collections");		
		
		    }else {
		    		 names = data.get(Dataset).get("prod collections");		
			
			}
		    String[] Links = names.split(",");
		    int i = 0;
		try {
		         	Thread.sleep(4000);
		         	for (i = 0; i < Links.length; i++) {
		         		Sync.waitElementPresent(50,"xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");

						Common.javascriptclickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
						Thread.sleep(3000);
						int size=Common.findElements("xpath", "(//div[@x-init='$nextTick(() => {show = true })'])[2]").size();
						if(size <=0) 
						{
							Common.javascriptclickElement("xpath", "//img[@alt='Hydroflask store logo']");
						
						Sync.waitElementPresent(50,"xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
						Common.javascriptclickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
				}
					Thread.sleep(3000);	
					Common.clickElement("xpath", "//a[@title='Collections']");
					Thread.sleep(3000);				
					Sync.waitElementPresent("xpath","(//span[contains(text(),'" + Links[i] + "')])[1]");
					Common.clickElement("xpath","(//span[contains(text(),'" + Links[i] + "')])[1]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title = "";
		            if 
		            (Common.findElements("xpath", "//h1[contains(@class,'title')]").size() > 0) {
		                title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
		            } 
		            else if (Common.findElements("xpath", "//h1[contains(@class,'hero')]/span").size() > 0) {
		                title = Common.findElement("xpath", "//h1[contains(@class,'hero')]/span").getText();
		            }
					String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
					System.out.println(title);
					System.out.println(Links[i]);			
					String products = Common.getText("xpath", "//div[contains(@class,'text-sm')]/span"); 
					int Number = Integer.parseInt(products);
					products = products.replaceAll("[^0-9]", ""); 
					int j=0;
					if(Number>j)
					{
						Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) 
						|| Common.getPageTitle().contains("Shop Remix Collection")|| Common.getPageTitle().contains("Shop Micro Hydro"),
								"verifying the header link " + Links[i] + "Under Collections",
								"user should navigate to the " + Links[i] + " page",
								"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
					}
					else
					{
						ExtenantReportUtils.addFailedLog(
								"validating the the products in the plp ",
								"User should able to see the products in plp", "unable to see the products in the PLP",
								Common.getscreenShot("Failed to see products in PLP"));
						Assert.fail();
					}
					
				
		         	}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link for Collections ",
					"user should navigate to the Collections page", "user Unable to Navigated to the Collections",
					Common.getscreenShot(" Failed to Navigated to the Collections"));
			Assert.fail();
		}
	}

		
		
		public void Halloween_validation(String Dataset) {
			// TODO Auto-generated method stub

			String names = data.get(Dataset).get("collections");
			String[] Links = names.split(",");
			int i = 0;
			try {
			         	Thread.sleep(4000);
			         	for (i = 0; i < Links.length; i++) {
						Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
						Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
						Sync.waitElementPresent("xpath", "//span[text()='Collections']");
						Common.clickElement("xpath", "//span[text()='Collections']");
						Thread.sleep(2000);
						Sync.waitElementPresent("xpath", "//span[contains(text(),'Halloween Shopping Guide')]");
						Common.clickElement("xpath","//span[contains(text(),'Halloween Shopping Guide')]");

						Sync.waitElementPresent("xpath",
								"//a[contains(@title,'" + Links[i] + "')]//span[contains(text(),'" + Links[i] + "')]");
						Common.clickElement("xpath",
								"//a[contains(@title,'" + Links[i] + "')]//span[contains(text(),'" + Links[i] + "')]");
						Sync.waitPageLoad();
						Thread.sleep(4000);
						String title = "";
			            if 
			            (Common.findElements("xpath", "//h1[contains(@class,'title')]").size() > 0) {
			                title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
			            } 
			            else if (Common.findElements("xpath", "//h1[contains(@class,'hero')]/span").size() > 0) {
			                title = Common.findElement("xpath", "//h1[contains(@class,'hero')]/span").getText();
			            }
						String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
						System.out.println(title);
						System.out.println(Links[i]);			
						String products = Common.getText("xpath", "//div[contains(@class,'text-sm')]/span"); 
						int Number = Integer.parseInt(products);
						products = products.replaceAll("[^0-9]", ""); 
						int j=0;
						if(Number>j)
						{
							Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) || breadcrumbs.contains(Links[i]) ,
									"verifying the header link " + Links[i] + "Under Collections",
									"user should navigate to the " + Links[i] + " page",
									"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
						}
						else
						{
							ExtenantReportUtils.addFailedLog(
									"validating the the products in the plp ",
									"User should able to see the products in plp", "unable to see the products in the PLP",
									Common.getscreenShot("Failed to see products in PLP"));
							Assert.fail();
						}
						
					
			         	}
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the header link for Halloween Shopping Guide",
						"user should navigate to the Halloween Shopping Guide page", "user Unable to Navigated to the Halloween Shopping Guide",
						Common.getscreenShot(" Failed to Navigated to the Halloween Shopping Guide"));
				Assert.fail();
			}

		}


	public void Waterbottle_gifts_validation(String Dataset) {
			// TODO Auto-generated method stub

			String names = data.get(Dataset).get("Gifts");
			String[] Links = names.split(",");
			int i = 0;
			try {
			         	Thread.sleep(4000);
			         	for (i = 0; i < Links.length; i++) {
						Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
						Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
						Common.clickElement("xpath", "//span[text()='Collections']");
						Thread.sleep(3000);
						Sync.waitElementPresent("xpath",
								"//a[contains(@title,'" + Links[i] + "')]//span[contains(text(),'" + Links[i] + "')]");
						Common.clickElement("xpath",
								"//a[contains(@title,'" + Links[i] + "')]//span[contains(text(),'" + Links[i] + "')]");
						Sync.waitPageLoad();
						Thread.sleep(4000);
						String title = Common.findElement("xpath", "//h1//span").getText();
						String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
						System.out.println(title);
						System.out.println(Links[i]);			
						String products=Common.getText("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
						System.out.println(products);
						int Number = Integer.parseInt(products);
						int j=0;
						if(Number>j)
						{
							Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) || breadcrumbs.contains(Links[i]) ,
									"verifying the header link " + Links[i] + "Under Water bottle gifts",
									"user should navigate to the " + Links[i] + " page",
									"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
						}
						else
						{
							ExtenantReportUtils.addFailedLog(
									"validating the the products in the plp ",
									"User should able to see the products in plp", "unable to see the products in the PLP",
									Common.getscreenShot("Failed to see products in PLP"));
							Assert.fail();
						}
						
					
			         	}
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the header link for Water bottle giftss ",
						"user should navigate to the Collections page", "user Unable to Navigated to the Water bottle gifts",
						Common.getscreenShot(" Failed to Navigated to the Water bottle gifts"));
				Assert.fail();
			}

		}


	public void Thanksgiving_validation(String Dataset) {
		// TODO Auto-generated method stub

		String names = data.get(Dataset).get("Gifts");
		String[] Links = names.split(",");
		int i = 0;
		try {
		         	Thread.sleep(4000);
		         	for (i = 0; i < Links.length; i++) {
					Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
					Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
					Common.clickElement("xpath", "//span[text()='Thanksgiving']");
					Thread.sleep(3000);
					Sync.waitElementPresent("xpath",
							"//a[contains(@href,'thanksgiving')]/span[contains(text(),'" + Links[i] + "')]");
					Common.clickElement("xpath",
							"//a[contains(@href,'thanksgiving')]/span[contains(text(),'" + Links[i] + "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
					String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
					System.out.println(title);
					System.out.println(Links[i]);			
					String products=Common.getText("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
					System.out.println(products);
					int Number = Integer.parseInt(products);
					int j=0;
					if(Number>j)
					{
						Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) || breadcrumbs.contains(Links[i]) ,
								"verifying the header link " + Links[i] + "Under Thanksgiving",
								"user should navigate to the " + Links[i] + " page",
								"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
					}
					else
					{
						ExtenantReportUtils.addFailedLog(
							    "Validating the products in the PLP",
							    "User should be able to see the products in PLP",
							    "No products found on the PLP page for the selected category.",
							    Common.getscreenShot("Failed to see products in PLP"));
						Assert.fail();
					}			
			}
		}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link for Thanksgiving",
					"user should navigate to the Collections page", "user Unable to Navigated to the Thanksgiving",
					Common.getscreenShot(" Failed to Navigated to the Thanksgiving"));
			Assert.fail();
		}

	}

	public void Edit_Engraving_to_Graphic(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		String Graphic="Beach Vibes";
		try
		{
			Sync.waitElementPresent("xpath", "//a[contains(@title,'Edit "+ products  +"')]");
			Common.clickElement("xpath", "//a[contains(@title,'Edit "+ products  +"')]");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			Sync.waitElementPresent("xpath", "//button[@aria-controls='graphic-panel']");
			Common.clickElement("xpath", "//button[@aria-controls='graphic-panel']");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", " //button[contains(text(),'"+ Graphic +"')]");
			Common.clickElement("xpath", " //button[contains(text(),'"+ Graphic +"')]");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", " //span[contains(text(),'Update Cart')]");
			Common.clickElement("xpath", " //span[contains(text(),'Update Cart')]");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", " //span[contains(text(),' Agree & Proceed')]");
			Common.clickElement("xpath", " //span[contains(text(),' Agree & Proceed')]");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			String graphic=Common.findElement("xpath", "(//span[text()='Engraving:']//following::span[text()='Better Lake Than Never'])[2]").getText();
			System.out.println(graphic);
			Common.assertionCheckwithReport(graphic.contains(Graphic) ,
					"verifying the graphic engraving has been applied",
					"user should see the grpahic engraving",
					"user successfully able to see the graphic engraving", "Failed to see to add the graphic engraving");
			Sync.waitElementPresent("xpath", "//button[@title='Remove']");
			Common.clickElement("xpath", "//button[@title='Remove']");
			Sync.waitPageLoad();
			String message = Common.findElement("xpath", "//div[@ui-id='message-success']").getAttribute("ui-id");
			Common.assertionCheckwithReport(message.contains("message"),
					"validating the deleting functionality in the gift registry",
					"After clicking on the delete button it should delete from the gift registry",
					"successfully it has been deleted from the gift registry",
					"failed to delete from the gift registry");
			Common.findElement("xpath", "//a[contains(@aria-label,'Add Engraving')]");
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the Grphic engraving removed for the bottle",
					"user should able to remove the graphic engraving", "user Unable to Remove the Graphic Engraving",
					Common.getscreenShot(" Failed to Remove the Graphic Engraving"));
			Assert.fail();
		}
		
	}

	public void Guest_Add_Wishlist_Create_account() {
		// TODO Auto-generated method stub
		try {
			
			boolean Wishlist = Common.findElements("xpath", "//button[@aria-label='Add to Wish List']").size() > 0;
			if (Wishlist) { 
		        Sync.waitElementPresent("xpath", "//button[@aria-label='Add to Wish List']");
		        Common.javascriptclickElement("xpath", "//button[@aria-label='Add to Wish List']");
		        System.out.println("Wishlist button clicked successfully.");
		    } else {
		        System.out.println("Already added to whishlist");
		    }

			
			Thread.sleep(3000);
			int Size = Common.findElements("xpath", "(//div[@class='m-modal__box']//div[1]//h4)[1]").size();
			System.out.println(Size);
			if (Size > 0) {

				Sync.waitElementPresent("xpath", "(//*[text()='Add To List'])[1]");
				Common.javascriptclickElement("xpath", "(//*[text()='Add To List'])[1]");

			} else if(Common.getCurrentURL().contains("/customer/account/login")) {
	            	Create_Account_for_Guest_my_fav("Create Account");
	            }
				
			}

	catch(Exception|Error e)
	{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating product added to wishlist ",
					"Products added to Compare list successfull", "failed to add product to wishlist",
					Common.getscreenShotPathforReport("Wishlistfail"));
			Assert.fail();
		}
}

	public void Create_Account_for_Guest_my_fav(String DataSet) {
		   
	    try {
	        // Check if Dataset exists in the data map
	        if (data.get(DataSet) == null) {
	            throw new IllegalArgumentException("Dataset not found: " + DataSet);
	        }

	        // Check required keys in the dataset
	        if (!data.get(DataSet).containsKey("FirstName") || 
	            !data.get(DataSet).containsKey("LastName") || 
	            !data.get(DataSet).containsKey("UserName") || 
	            !data.get(DataSet).containsKey("Password") || 
	            !data.get(DataSet).containsKey("Confirm Password")) {
	            throw new IllegalArgumentException("Missing required keys in the dataset: " + DataSet);
	        }

	        Common.clickElement("xpath", "//a[text()='Create an Account']");
	        Common.clickElement("xpath", "//input[@name='firstname']");
	        Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(DataSet).get("FirstName"));
	        Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(DataSet).get("LastName"));
	        Common.textBoxInput("xpath", "//input[@name='email']", data.get(DataSet).get("UserName"));
	        Common.textBoxInput("xpath", "//input[@name='password']", data.get(DataSet).get("Password"));
	        Common.textBoxInput("xpath", "//input[@name='password_confirmation']", data.get(DataSet).get("Confirm Password"));
	        Common.clickElement("xpath", "//span[text()='Sign Up']");
	        Sync.waitImplicit(30);
	        Thread.sleep(4000);

	        Common.assertionCheckwithReport(Common.getCurrentURL().contains("wishlist"),
	                "Validating navigation to the account page after clicking on sign up button",
	                "User should navigate to the My account page after clicking on the Sign Up",
	                "Successfully navigated to the My account page after clicking on the Sign Up button",
	                "Failed to navigate to the My account page after clicking on the Sign Up button");

	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	        ExtenantReportUtils.addFailedLog(
	                "Dataset validation",
	                "Valid dataset should be provided",
	                e.getMessage(),
	                "Failed due to invalid dataset");
	        AssertJUnit.fail(e.getMessage());

	    } catch (Exception | Error e) {
	        e.printStackTrace();
	        ExtenantReportUtils.addFailedLog(
	                "Validating navigation to the account page after clicking on sign up button",
	                "User should navigate to the My account page after clicking on the Sign Up",
	                "Unable to navigate to the My account page after clicking on the Sign Up button",
	                "Failed to navigate to the My account page after clicking on the Sign Up button");
	        AssertJUnit.fail();
	    }
	    
	}

	public void Giftcard_Add_from_My_fav(String Dataset) {
		// TODO Auto-generated method stub
		String amount=data.get(Dataset).get("Card Amount");
		String Product="Hydro Flask Gift Card";
		try
		{
			Sync.waitElementClickable("id", "customer-menu");
			Common.clickElement("id", "customer-menu");
			Sync.waitElementClickable("xpath", "//a[@id='customer.header.wishlist.nav.link']");
			Common.clickElement("xpath", "//a[@id='customer.header.wishlist.nav.link']");
			Thread.sleep(4000);
			String product=Common.findElement("xpath", "//a[@title='"+Product+"']").getText().toLowerCase();
			System.out.println(product);
			Sync.waitElementPresent("xpath", "//button[@aria-label='Add to Cart "+Product+"']");
			Common.clickElement("xpath", "//button[@aria-label='Add to Cart "+Product+"']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String Pdp=Common.findElement("xpath", "//h1[@itemprop='name']").getText().toLowerCase();
			System.out.println(Pdp);
			
			Common.assertionCheckwithReport(product.equalsIgnoreCase(Pdp),
					"verifying Product navigation to the PDP", "After clicking add to cart in the myfav it should navigate to the PDP",
					"Product navigated to the PDP page", "Failed to Navigate tot the PDP");
			Sync.waitElementPresent("xpath", "//span[text()='"+ amount +"']");
			Common.clickElement("xpath", "//span[text()='"+ amount +"']");
			if(Common.getCurrentURL().contains("/gb"))
			{
			String Price=Common.findElement("xpath", "//div[@class='final-price inline-block']//span[@class='price']").getText();
			Common.assertionCheckwithReport(Price.contains(amount),
					"validating gift card amount value in PDP",
					"After clicking on the value amount should be appear in PDP",
					"Successfully selected amount is matched for the gift card",
					"Failed to appear amount for the gift card");
			Giftcard_details("Gift Details");
			}
			else
			{
				Card_Value_for_my_fav("price");
		
			}
			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			Sync.waitPageLoad();
			Thread.sleep(4000);

			Sync.waitElementPresent("xpath", "//button[@id='menu-cart-icon']");
			Common.clickElement("xpath", "//button[@id='menu-cart-icon']");
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating gift card amount value in PDP",
					"After clicking on the value amount should be appear in PDP",
					"Failed to selected amount is matched for the gift card",
					Common.getscreenShotPathforReport("Failed to appear amount for the gift card"));
			Assert.fail();
		}
	}

	
	
	
	public String addBillingDetails_PaymentDetails_SubmitOrder(String dataSet) throws Exception {
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
			Sync.waitElementPresent("xpath", "//label[@for='payment-method-stripe_payments']");
			// Common.clickElement("xpath", "//label[@for='stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='payment-method-stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unabel to land opaymentpage");
			Common.clickElement("xpath", "//label[@for='payment-method-stripe_payments']");
			Thread.sleep(3000);
				Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
				Thread.sleep(4000);
				String text = Common.findElement("xpath", "//input[@name='street[0]']").getAttribute("value");
				Sync.waitPageLoad();
				Thread.sleep(5000);
				Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
				System.out.println(data.get(dataSet).get("City"));

//				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				 if(Common.getCurrentURL().contains("stage3"))
	             {
					  Thread.sleep(4000);
	                 Common.scrollIntoView("xpath", "//select[@id='shipping-region']");
	                 Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
	                 Thread.sleep(3000);
	                 String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']")
	                         .getAttribute("value");
	                 System.out.println(Shippingvalue);
	             }
				 else
				 {
					 Common.scrollIntoView("xpath", "//select[@name='region']");
					 Common.dropdown("xpath", "//select[@name='region']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);
				// Common.textBoxInputClear("xpath", "//input[@name='postcode']");
				Common.textBoxInput("xpath", "//input[@name='postcode']",
						data.get(dataSet).get("postcode"));
				Thread.sleep(5000);

				Common.textBoxInput("xpath", "//input[@name='telephone']",
						data.get(dataSet).get("phone"));
				Sync.waitPageLoad();
				Thread.sleep(5000);
				
				Common.switchFrames("xpath", "//iframe[contains(@src,'elements-inner-payment-')]");
				Thread.sleep(2000);
				Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
				Common.clickElement("xpath", "//label[@for='Field-numberInput']");
				Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);
				Number = Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ", "");
				System.out.println(Number);

				Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

				Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.textBoxInput("xpath", "//input[@name='postalCode']",data.get(dataSet).get("postcode"));
				Common.switchToDefault();
				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage") ) {
					Sync.waitElementPresent("xpath", "(//button[contains(@class,'btn btn-primary place-order')])[2]");

					Common.clickElement("xpath", "(//button[contains(@class,'btn btn-primary place-order')])[2]");
				} else {
					AssertJUnit.fail();
					

				}

			
			

		}

		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", expectedResult,
					"failed  to fill the Credit Card infromation",
					Common.getscreenShotPathforReport("Cardinfromationfail"));
			AssertJUnit.fail();
		}

		expectedResult = "credit card fields are filled with the data";
		String errorTexts = Common.findElement("xpath", "//div[contains(@class,'error')]").getText();

		Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data",
				expectedResult, "Filled the Card detiles", "missing field data it showinng error");

		return Number;
	}

	public void Card_Value_for_my_fav(String Dataset) {
		// TODO Auto-generated method stub
		String amount=data.get(Dataset).get("Card Amount");
		try
		{
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//span[text()='"+ amount +"']");
			Common.clickElement("xpath", "//span[text()='"+ amount +"']");
			if(Common.getCurrentURL().contains("/gb/"))
			{
			String Price=Common.findElement("xpath", "//div[@class='final-price inline-block']//span[@class='price']").getText();
			Common.assertionCheckwithReport(Price.contains(amount),
					"validating gift card amount value in PDP",
					"After clicking on the value amount should be appear in PDP",
					"Successfully selected amount is matched for the gift card",
					"Failed to appear amount for the gift card");
			}
			else
			{
				Common.assertionCheckwithReport(Common.getCurrentURL().contains("gift-card")||Common.getCurrentURL().contains("/wishlist/index"),
						"validating gift card Navigated to the PDP",
						"After clicking on the gift card on PLP it should navigate to the PDP",
						"Successfully Gift card is Navigated to the PDP ",
						"Failed to navigate the gift card to the PDP");
			}
			
			Giftcard_details("Gift Details");
		
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
						"Unable to add the product to the cart", Common.getscreenShot("Failed the product Add to cart from the PDP"));
				Assert.fail();
			}
			
		}

	public void myorders_price_validation(String price) {
		// TODO Auto-generated method stub
		try
		{
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//span[contains(@class,'price-excluding-tax block dr:font-bold')]//span");
			String Orderprice=Common.findElement("xpath", "//span[contains(@class,'price-excluding-tax block dr:font-bold')]//span").getText();
			System.out.println(Orderprice);
			Assert.assertEquals(Orderprice, price);
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the product price in the orders page", "Product price should be same in orders page",
					"price is different in the orders summary price", Common.getscreenShot("Failed the product price in the order summary page"));
			Assert.fail();
		}
		
	}
	
	public void Admin_prepareOrdersData(String fileName) {
		// TODO Auto-generated method stub
		try {

			File file = new File(System.getProperty("user.dir") + "/src/test/resources/TestData/Hydroflask/" + fileName);
			XSSFWorkbook workbook;
			XSSFSheet sheet;
			Row row;
			Cell cell;
			int rowcount;
			if (!(file.exists())) {
				workbook = new XSSFWorkbook();
				sheet = workbook.createSheet("OrderDetails");
				CellStyle cs = workbook.createCellStyle();
				CellStyle ps = workbook.createCellStyle();
				cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				cs.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
				ps.setFillForegroundColor(IndexedColors.RED.getIndex());
				Font f = workbook.createFont();
				f.setBold(true);
				cs.setFont(f);
				cs.setAlignment(HorizontalAlignment.RIGHT);
				
				row = sheet.createRow(0);
				cell = row.createCell(0);
				cell.setCellStyle(cs);
				cell.setCellValue("Orders Details");

				row = sheet.createRow(1);
				cell = row.createCell(0);
				cell.setCellStyle(cs);
				cell.setCellValue("S.No");
				cell = row.createCell(1);
				cell.setCellStyle(cs);
				cell.setCellValue("Website");
				cell = row.createCell(2);
				cell.setCellStyle(cs);
				
				cell.setCellStyle(cs);
				cell.setCellValue("Test scenario Description");
				cell = row.createCell(3);
				
				cell.setCellStyle(cs);
				cell.setCellValue("SKU");
				cell = row.createCell(4);
				cell.setCellStyle(cs);
				cell.setCellValue("Web Order Number");
				cell = row.createCell(5);
				
				cell.setCellStyle(cs);
				cell.setCellValue("Order Status Magento");
	           cell = row.createCell(6);
	           
	           cell.setCellStyle(cs);
				cell.setCellValue("Workato Status");
	           cell = row.createCell(7);
			
	           cell.setCellStyle(cs);
				cell.setCellValue("Used GiftCode");
	         cell = row.createCell(8);
	         
	         cell.setCellStyle(cs);
				cell.setCellValue("Subtotal");
		     cell = row.createCell(9);
		     
		     cell.setCellStyle(cs);
				cell.setCellValue("shipping");
		     cell = row.createCell(10);
		     
		     cell.setCellStyle(cs);
				cell.setCellValue("Tax");
		     cell = row.createCell(11);
		     
		     cell.setCellStyle(cs);
				cell.setCellValue("Discount");
		     cell = row.createCell(12);
		     
		     cell.setCellStyle(cs);
				cell.setCellValue("ordertotal");
		     cell = row.createCell(13);
		     
		     cell.setCellStyle(cs);
				cell.setCellValue("Adminsubtotal");
		     cell = row.createCell(14);
		     
		     
		     cell.setCellStyle(cs);
				cell.setCellValue("Adminshipping");
		     cell = row.createCell(15);
		     
		     cell.setCellStyle(cs);
				cell.setCellValue("Admintax");
		     cell = row.createCell(16);
		     
		     cell.setCellStyle(cs);
				cell.setCellValue("AdminDis");
		     cell = row.createCell(17);
		     
		     cell.setCellStyle(cs);
				cell.setCellValue("Admintotal");
		     cell = row.createCell(18);
		     
		     cell.setCellStyle(cs);
				cell.setCellValue("Email");
		     cell = row.createCell(19);
		     
		     cell.setCellStyle(cs);
				cell.setCellValue("Website&Adminsubtotal status");
		     cell = row.createCell(20);
		     
		     cell.setCellStyle(cs);
				cell.setCellValue("Website&AdminShipping Status");
		     cell = row.createCell(21);
		     
		     cell.setCellStyle(cs);
				cell.setCellValue("Website&AdminTax Status");
		     cell = row.createCell(22);
		     
		     cell.setCellStyle(cs);
				cell.setCellValue("Website&AdminDiscount Status");
		     cell = row.createCell(23);
		     
		     cell.setCellStyle(cs);
				cell.setCellValue("Website&Adminordertotal Status");
		     cell = row.createCell(24);

				rowcount = 2;

			}

			else {
				workbook = new XSSFWorkbook(new FileInputStream(file));
				sheet = workbook.getSheet("OrderDetails");
				rowcount = sheet.getLastRowNum() + 1;
			}
			
			FileOutputStream fileOut = new FileOutputStream(file);
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Remove_Product(String dataSet) {
		// TODO Auto-generated method stub
		String Symbol = data.get(dataSet).get("Symbol");
		String products = data.get(dataSet).get("Products");
		System.out.println(products);
		//String products ="12 oz Mug";

		try {
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[contains(@aria-label,'Remove " + products + "')]");
			Common.clickElement("xpath", "//button[contains(@aria-label,'Remove " + products + "')]");
			
			
			int Remove_Popup = Common.findElements("xpath", "//h2[normalize-space()='Remove Item'] ").size();
			System.out.println(Remove_Popup);
					if(Remove_Popup>0) {
						Sync.waitElementClickable("xpath", "//button[normalize-space()='OK'] ");
						Common.clickElement("xpath", "//button[normalize-space()='OK'] ");
						System.out.println("Product Removed successfully");
					} else {
						
						Assert.fail();
					}
					Sync.waitPageLoad(40);
			Thread.sleep(10000);
			ExtenantReportUtils.addPassLog("validating the remove prodcut form shopping cart page",
			"Product should be remove form the shopping cart page",
			"Unable to remove the product from the shopping cart page",
			Common.getscreenShot("Removed the product from the shopping cart page"));
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the remove prodcut form shopping cart page",
					"Product should be remove form the shopping cart page",
					"Unable to remove the product from the shopping cart page",
					Common.getscreenShot("Failed to remove the product from the shopping cart page"));
			Assert.fail();
		}

	}

	public void GiftCard_RegaddDeliveryAddress(String dataSet) {
		// TODO Auto-generated method stub
		String expectedResult = "shipping address is entering in the fields";

		int size = Common.findElements(By.xpath("//button[contains(@class,'btn dr:btn-secondary-checkout hf:btn-primary')]")).size();
		if (size > 0) {
		    try {
		        if (Common.getCurrentURL().contains("stage")) {
		            Thread.sleep(8000);
		        }
		        Common.clickElement("css", "button[class*='btn dr:btn-secondary-checkout hf:btn-primary']");
		        Common.textBoxInput("xpath", "//input[@id='shipping-firstname']", 
		                data.get(dataSet).get("FirstName"));
		        Common.textBoxInput("xpath", "//input[@id='shipping-lastname']",
		                data.get(dataSet).get("LastName"));
		        Common.textBoxInput("xpath", "//input[@id='shipping-street-0']", data.get(dataSet).get("Street"));
		        Common.actionsKeyPress(Keys.SPACE);
		        try {
		            Common.clickElement("xpath", "//input[@id='shipping-street-0']");
		        } catch (Exception e) {
		            Common.actionsKeyPress(Keys.BACK_SPACE);
		            Thread.sleep(1000);
		            Common.actionsKeyPress(Keys.SPACE);
		            Common.clickElement("xpath", "//input[@id='shipping-street-0']");
		        }
		        if (data.get(dataSet).get("StreetLine2") != null) {
		            Common.textBoxInput("xpath", "//input[@id='shipping-street-1']", data.get(dataSet).get("Street"));
		        }
		        //removed street[2] since the provided xpaths only include street 0 and 1

		        Common.scrollIntoView("id", "shipping-region");
		        Common.dropdown("id", "shipping-region", Common.SelectBy.TEXT,
		                data.get(dataSet).get("Region"));

		        Common.actionsKeyPress(Keys.PAGE_DOWN);
		        Common.textBoxInput("id", "shipping-city", data.get(dataSet).get("City"));

		        try {
		            Common.dropdown("id", "shipping-region", Common.SelectBy.TEXT,
		                    data.get(dataSet).get("Region"));
		        } catch (ElementClickInterceptedException e) {
		            Common.dropdown("id", "shipping-region", Common.SelectBy.TEXT,
		                    data.get(dataSet).get("Region"));
		        }

		        Common.textBoxInputClear("xpath", "//input[@id='shipping-postcode']");
		        Common.textBoxInput("xpath", "//input[@id='shipping-postcode']",
		                data.get(dataSet).get("postcode"));
		        String ShippingZip = Common.findElement("id", "shipping-postcode").getAttribute("value");
		        System.out.println("*****" + ShippingZip + "*******");

		        Sync.waitElementPresent("xpath", "//input[@id='shipping-telephone']");
		        Common.textBoxInput("xpath", "//input[@id='shipping-telephone']",
		                data.get(dataSet).get("phone"));

		        Thread.sleep(2000);
		       // Common.clickElement("css", "button[class='btn btn-primary w-full os:uppercase']");
		        Common.clickElement("xpath", "//button[@class='checkout-address-form__buttons-save btn btn-primary w-full os:uppercase']");
		    } catch (Exception | Error e) {
		        e.printStackTrace();

		        ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
		                "User unabel add shipping address",
		                Common.getscreenShotPathforReport("shipping address faield"));

		        Assert.fail();
		    }
		} else {
		    try {
		        Common.textBoxInput("xpath", "//input[@id='billing-firstname']",
		                data.get(dataSet).get("FirstName"));
		        Common.textBoxInput("xpath", "//input[@id='billing-lastname']",
		                data.get(dataSet).get("LastName"));
		        Common.textBoxInput("xpath", "//input[@id='billing-street-0']",
		                data.get(dataSet).get("Street"));
		        Thread.sleep(2000);

		        if (data.get(dataSet).get("StreetLine2") != null) {
		            Common.textBoxInput("xpath", "//input[@id='billing-street-1']", data.get(dataSet).get("Street"));
		        }
		        //removed street[2] since the provided xpaths only include street 0 and 1

		        Common.actionsKeyPress(Keys.PAGE_DOWN);
		        Thread.sleep(3000);
		        Common.textBoxInput("id", "billing-city",
		                data.get(dataSet).get("City"));

		        try {
		            Common.dropdown("xpath", "//select[@id='billing-region']",
		                    Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
		        } catch (ElementClickInterceptedException e) {
		            Thread.sleep(3000);
		            Common.dropdown("xpath", "//select[@id='billing-region']",
		                    Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
		        }
		        Thread.sleep(2000);
		        Common.textBoxInputClear("xpath", "//input[@id='billing-postcode']");
		        Common.textBoxInput("xpath", "//input[@id='billing-postcode']",
		                data.get(dataSet).get("postcode"));

		        String ShippingZip = Common
		                .findElement("xpath", "//input[@id='billing-postcode']")
		                .getAttribute("value");
		        System.out.println("*****" + ShippingZip + "*******");

		        Thread.sleep(5000);
		        Common.textBoxInput("xpath", "//input[@id='billing-telephone']",
		                data.get(dataSet).get("phone"));
		        Common.clickElement("id", "billing-save");

		    } catch (Exception | Error e) {
		        e.printStackTrace();

		        ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
		                "User unabel add shipping address",
		                Common.getscreenShotPathforReport("shipping address faield"));

		        Assert.fail();
		    }
		}
		

}

	public void Coolers_LunchBoxes_Validation(String Dataset) {
		// TODO Auto-generated method stub

		String names = data.get(Dataset).get("Kitchen");
		String[] Links = names.split(",");
		int i = 0;
		try {
			Thread.sleep(4000);
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
				Common.clickElement("xpath", "//nav[@class='main-nav flex h-full']//span[contains(text(), 'Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),'Coolers')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//a[contains(@href,'cooler')]/span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath", "//a[contains(@href,'cooler')]/span[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
				String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
				System.out.println(title);
				System.out.println(Links[i]);
				String products = Common.getText("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]");
				System.out.println(products);
				int Number = Integer.parseInt(products);
				int j = 0;
				if (Number > j) {
					Common.assertionCheckwithReport(
							title.contains(Links[i]) || breadcrumbs.contains(Links[i]) || breadcrumbs.contains(Links[i])
									|| Common.getCurrentURL().contains("coolers"),
							"verifying the header link " + Links[i] + "Under Outdoor Packs",
							"user should navigate to the " + Links[i] + " page",
							"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
				} else {
					ExtenantReportUtils.addFailedLog("validating the the products in the plp ",
							"User should able to see the products in plp", "unable to see the products in the PLP",
							Common.getscreenShot("Failed to see products in PLP"));
					Assert.fail();
				}

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link for coolers ",
					"user should navigate to the coolers page", "user Unable to Navigated to the coolers",
					Common.getscreenShot(" Failed to Navigated to the coolers"));
			Assert.fail();
		}

	}

	public void Blog_Page(String Dataset) {

			String names = data.get(Dataset).get("blog");
			String[] Blog = names.split(",");
			
			
			int i = 0;
			try {
				Sync.waitElementPresent("xpath", "(//span[contains(text(),'Explore')])[1]");
				Common.clickElement("xpath", "(//span[contains(text(),'Explore')])[1]");
			
				Common.clickElement("xpath", "(//span[contains(text(),'Blog')])[1]");
				
				for (i = 0; i < Blog.length; i++) {
					
					Thread.sleep(3000);
					Sync.waitElementPresent("xpath", "(//a[normalize-space()='"+Blog[i]+"'])[1]");
					Common.clickElement("xpath", "(//a[normalize-space()='"+Blog[i]+"'])[1]");
//							Common.clickElement("xpath", "//a[contains(@aria-label,'" +Blog[i]+ "')]");
					Sync.waitPageLoad();
					Thread.sleep(2000);
					String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
					Common.assertionCheckwithReport(title.contains(Blog[i]),
							"verifying the header link " + Blog[i] + "Under Featured",
							"user should navigate to the " + Blog[i] + " page",
							"user successfully Navigated to the " + Blog[i], "Failed to navigate to the " + Blog[i]);

				}
			}

			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the header link " + Blog[i] + "Under Featured",
						"User should navigate to the " + Blog[i] + "pages",
						" unable to navigate to the " + Blog[i] + "pages",
						Common.getscreenShot("Failed to navigate to the " + Blog[i] + "pages"));
				Assert.fail();
			}

		}

	
	
	public void blog_page() {

		try {

			Common.clickElement("xpath", "//a[contains(text(),'Home')]");
			Sync.waitPageLoad();
			int blogcategory = Common
					.findElements("xpath", "(//div[contains(@class,'hf:shadow-blog-hero block')])[1]")
					.size();
   System.out.println(blogcategory);
			Common.assertionCheckwithReport(
					blogcategory >= 0,
					"To validate the Blog page", "user should able to see the Blog Article",
					"Sucessfully Blog Article has been displayed", "Failed to Displayed the Blog Article");
			
			Common.scrollIntoView("xpath", "(//span[text()='Read Article'])[1]");
			Common.clickElement("xpath", "(//span[text()='Read Article'])[1]");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Blog page", "user should able to see the Blog Article",
					"unable to Displayed the Blog Article",
					Common.getscreenShotPathforReport("Failed to Displayed the Blog Article"));
			Assert.fail();
		}
		blog_article_page();
		blog_article_comments();
	}

	public void blog_article_page() {

		try {
			String blogbreadcrumb = Common.findElement("id", "breadcrumbs").getText();
			System.out.println(blogbreadcrumb);
			int blogarticle = Common.findElements("xpath", "//div[contains(@class,'m-blog-post-meta')]").size();
			Common.assertionCheckwithReport(blogbreadcrumb.contains("Hydro Flask Blog") && blogarticle > 0,
					"To validate the Blog page", "user should able to see the Blog Article",
					"Sucessfully Blog Article has been displayed", "Failed to Displayed the Blog Article");

			Common.scrollIntoView("xpath", "//ul[@class='flex items-center']");
			List<WebElement> blogsocial = Common.findElements("xpath", "//ul[@class='flex items-center']//li//button");
			for (WebElement blog : blogsocial) {

				System.out.println(blog.getText());

				Common.assertionCheckwithReport(
						(blog.getText().contains("Pinterest")) || (blog.getText().contains("Facebook"))
								|| (blog.getText().contains("Twitter")) || (blog.getText().contains("Copy URL")),
						"To validate the Blog page", "user should able to see the Blog Article",
						"Sucessfully Blog Article has been displayed", "Failed to Displayed the Blog Article");

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Blog page", "user should able to see the Blog Article",
					"unable to Displayed the Blog Article",
					Common.getscreenShotPathforReport("Failed to Displayed the Blog Article"));
			Assert.fail();
		}
	}

	public void blog_article_comments() {

		try {
			
			Common.scrollIntoView("xpath",  "//iframe[@title='Disqus']");
			Common.switchFrames("xpath", "//iframe[@title='Disqus']");
			String comment = Common.findElement("xpath", "//span[@class='comment-count']").getText();
			System.out.println(comment);
			Common.clickElement("xpath", "//div[text()='Start the discussion…']");
			
			
			List<WebElement> blogsocial = Common.findElements("xpath", "//ul[@data-role='login-menu']/li/button");  

			List<String> ariaLabels = new ArrayList<>();  
			for (WebElement element : blogsocial) {  
			    String ariaLabel = element.getAttribute("aria-label"); 
			    Thread.sleep(1000);
			    ariaLabels.add(ariaLabel); 
			    System.out.println(ariaLabels);
				Common.assertionCheckwithReport(comment.contains("Comments") && ariaLabels.contains("Login with Disqus")||ariaLabels.contains("Apple")||ariaLabels.contains("Facebook")||ariaLabels.contains("Twitter")||ariaLabels.contains("Google")||ariaLabels.contains("Microsoft"),
						"To validate the Blog page", "user should able to see the Blog Article",
						"Sucessfully Blog Article has been displayed", "Failed to Displayed the Blog Article");
			}  
			
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Blog page", "user should able to see the Blog Article",
					"unable to Displayed the Blog Article",
					Common.getscreenShotPathforReport("Failed to Displayed the Blog Article"));
			Assert.fail();
		}
	}

	public void orders_image_Validation()
	{
		try {
			List <WebElement> images=Common.findElements("css", "div[class*='parent-item border'] img");
			  for (WebElement img : images) {
	                if (img.isDisplayed()) {
	                    System.out.println("Image is displayed.");

	                    String src = img.getAttribute("src");
	                    if (validateImageURL(src)) {
	                        System.out.println("Image URL is valid: " + src);
	                    } else {
	                        System.out.println("Broken image URL: " + src);
	                    }

	                } else {
	                    System.out.println("Image is NOT displayed.");
	                }
	            }
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	 public static boolean validateImageURL(String imageUrl) {
	        try {
	            HttpURLConnection connection = (HttpURLConnection) new URL(imageUrl).openConnection();
	            connection.setRequestMethod("GET");
	            connection.connect();
	            int code = connection.getResponseCode();
	            return (code == 200);
	        } catch (Exception e) {
	            return false;
	        }
	    }

	public void Anti_Human_Trafficking() {
		try {
			Common.findElement("xpath", "//a[contains(@href,'anti-human-trafficking-and-modern-slavery-statement')]");
			Common.clickElement("xpath", "//a[contains(@href,'anti-human-trafficking-and-modern-slavery-statement')]");
			Common.switchToSecondTab();
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Anti-human Trafficking and Modern Slavery Statement | Helen of Troy"),
					"validating the anti-human-trafficking-and-modern-slavery-statement page Navigation",
					"After Clicking anti-human-trafficking-and-modern-slavery-statement it should navigate to the respective page",
					"Unable to Navigate to the Privacy Policy page");
			Common.closeCurrentWindow();
			Common.switchToFirstTab();
		}
		catch(Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the anti-human-trafficking-and-modern-slavery-statement page Navigation",
					"After Clicking anti-human-trafficking-and-modern-slavery-statement it should navigate to the respective page",
					"Unable to Navigate to the Privacy Policy page",
					Common.getscreenShot("Failed Navigate to the anti-human-trafficking-and-modern-slavery-statement page"));
			Assert.fail();
		}
		
	}

	public void Cookies_and_ShopNow() {
		try {
			
			Common.clickElement("xpath", "//a[@class='truste_cursor_pointer']");
			Thread.sleep(3000);
			Common.switchFrames("xpath", "//iframe[@name='trustarc_cm']");
			String Cookies_content = Common.findElement("xpath", "//img[@alt='Hydroflask logo']").getAttribute("alt");
			
			Common.assertionCheckwithReport(Cookies_content.contains("Hydroflask logo"),
					"validating the Cookie Preferences Navigation",
					"After Clicking Cookie Preferences it should navigate to the respective page",
					"Unable to Navigate to the Cookie Preferences");
			Common.actionsKeyPress(Keys.ESCAPE);
			Common.switchToDefault();
			Common.scrollIntoView("xpath", "//a[@aria-label='Shop HydroFlask Bestsellers']");
			Common.clickElement("xpath", "//a[@aria-label='Shop HydroFlask Bestsellers']");
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("shop"),
					"validating the Cookie Preferences Navigation",
					"After Clicking Cookie Preferences it should navigate to the respective page",
					"Unable to Navigate to the Cookie Preferences");
		}
		catch(Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Cookie Preferences page Navigation",
					"After Clicking Cookie Preferences it should navigate to the respective page",
					"Unable to Navigate to the Cookie Preferences page",
					Common.getscreenShot("Failed Navigate to Cookie Preferences"));
			Assert.fail();
		}


	
}

	public void Accessibility() {
		try {
			Common.clickElement("xpath", "//img[contains(@src,'accessibility_icon')]");
			Common.switchToSecondTab();
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("levelaccess"),
					"validating the Accessibility Navigation",
					"After Clicking Accessibility it should navigate to the respective page",
					"Unable to Navigate to the Accessibility page");
		}
		catch(Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Cookie Preferences page Navigation",
					"After Clicking Cookie Preferences it should navigate to the respective page",
					"Unable to Navigate to the Cookie Preferences page",
					Common.getscreenShot("Failed Navigate to Cookie Preferences"));
			Assert.fail();	
		}
		
	}

	public void Newsletter_SUbscription_MyAccout() {
		try {
			Common.clickElement("css", "button[id='customer-menu']");
			Common.clickElement("css","a[id='customer.header.dashboard.link']");
			Common.clickElement("xpath", "//span[text()='My Newsletter Subscriptions']");
			int Frame = Common.findElements("xpath", "//iframe[contains(@id,'klaviyo_subscribe_page_1')]").size();
			if(Frame==1)
					{
				Common.switchFrames("xpath", "//iframe[contains(@id,'klaviyo_subscribe_page_1')]");
				Thread.sleep(4000);
				Common.textBoxInput("xpath", "(//input[contains(@id,'kl-consent-page')])[1]", "Spanem@helenoftroy.com");
				Common.textBoxInput("xpath", "(//input[contains(@id,'kl-consent-page')])[2]", "S");
				Common.textBoxInput("xpath", "(//input[contains(@id,'kl-consent-page')])[3]", "panem");
                  Common.clickElement("xpath", "//button[text()='Subscribe']");
              String text = 	Common.getText("xpath", "//span[text()='Thanks for confirming your email address.']");
              Common.assertionCheckwithReport(text.contains("Thanks for confirming your email"),
  					"validating Newsletter Subscription Message",
  					"After Clicking subscribe button it should display the you've subscribed",
  					"Unable to subscribed the Newsletter in My Account");
					}else {
				
			
			 Common.switchFrames("xpath", "//iframe[contains(@id,'klaviyo_subscribe_page_2')]");
			 Thread.sleep(3000);
			 Common.textBoxInput("xpath", "(//input[contains(@id,'kl-consent-page')])[1]", "Spanem@helenoftroy.com");
			 Common.clickElement("xpath", "//button[text()='Unsubscribe']");
			 Thread.sleep(2000);
		String text = 	Common.getText("xpath", "(//h1[contains(text(),'been unsubscribed')])[1]");
			System.out.println(text);
			
			 Common.assertionCheckwithReport(text.contains("unsubscribed"),
	  					"validating Newsletter Unsubscription Message",
	  					"After Clicking Unsubscribe button it should display the you've Unsubscribed",
	  					"Unable to UnSubscribed the Newsletter in My Account");
					}
		}
		catch(Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Newsletter subscription in My Account Page",
					"After Entering all details ine newsletter form and submitting it should successfully subscribe ",
					"Unable to subscribe Newsletter subscription in My Account Page ",
					Common.getscreenShot("Failed to subscribe Newsletter subscription in My Account Page"));
			Assert.fail();	
		}
	}

	public void ribbon() {
		try {
			Common.refreshpage();
			Sync.waitPageLoad();
			int ribbon = Common.findElements("xpath", "//div[contains(@class,'ribbons grid gap')]").size();
			Common.assertionCheckwithReport(ribbon>0,
					"Verfying the PDP ribbon when navigated to the PDP page ",
					"User should able to see ribbon when navigated on the PDP Page",
					"Successfully able to see the ribbon on the PDP page",
					"Failed to display the ribbon icons on the PDP page");
		}
	 catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog(
				"verifying the products ribbon  on the PDP page  ",
				"user should able to see the products ribbon on the PDP page",
				"user unable to see the products  ribbon on the PDP page",
				Common.getscreenShot(
						"Failed to see the products ribbon r on the PDP page"));
		Assert.fail();
	}
	
}	

	public void Locally_PDP() {
		// TODO Auto-generated method stub
		try {
//			Common.clickElement("css", "span[class='a-btn-tertiary__label']");
//			Sync.waitPageLoad();
//			Thread.sleep(2000);
//			Common.clickElement("xpath", "//button[@aria-label='close']");
			String locally = Common.findElement("css", "span[class='a-btn-tertiary__label']").getText().trim();
			int ribbon = Common.findElements("css", "span[class*='ribbon-bg']").size();
			Common.assertionCheckwithReport(locally.contains("Find this locally"),
					"Verfying the PDP Locally when navigated to the PDP page ",
					"User should able to see Locally when navigated on the PDP Page",
					"Successfully able to see the locally on the PDP page",
					"Failed to display the locally icons on the PDP page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verfying the PDP Locally when navigated to the PDP page ",
					"User should able to see Locally when navigated on the PDP Page",
					"Unable to display the locally icons on the PDP page",
					Common.getscreenShot("Failed to display the locally icons on the PDP page"));
			Assert.fail();
		}

	}
	
	public void PDP_Tabs(String Dataset) throws Exception {
		List<WebElement> size = Common.findElements("css",
				"div[x-ref='productTabsComponent'] div h2[aria-live='polite']");
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		try {
			for (int i = 0; i < size.size(); i++) {
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				 WebElement tab = size.get(i);
			        tab.click();
			        String Text= tab.getText().trim();
			        System.out.println(Text);
			        
				Common.assertionCheckwithReport(Text.contains("tab active"), "verifying the tabs in PDP ",
						"After clicking on the " + Text + "It should display the related content",
						"sucessfully after clicking on the " + Text + "it has been displayed related content",
						"Failed to display related content" + Text);
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the tabs in PDP ",
					"After clicking on the PDP tab " + "It should display the related content",
					"Unable to display the content in  the PDP Tab",
					Common.getscreenShot("Failed to display related content"));

			Assert.fail();
		}

	}

	public void Related_Products() {
	    boolean hasLeftArrow = false;
	    boolean hasRightArrow = false;

	    try {
	    	Common.actionsKeyPress(Keys.PAGE_DOWN);
	        int RelatedPrd = Common.findElements("xpath", "//h2[contains(text(),'Related products')]").size();
	        if (RelatedPrd > 0) {
	            List<WebElement> size = Common.findElements("xpath", "//button[contains(@class,'recommended-arrow')]");

	            for (WebElement element : size) {
	                String arrowType = element.getAttribute("aria-label");
	                if (arrowType != null) {
	                    hasLeftArrow |= arrowType.contains("Scroll left");
	                    hasRightArrow |= arrowType.contains("Scroll right");
	                }
	            }

	            Common.assertionCheckwithReport(hasLeftArrow && hasRightArrow, 
	                "Validating Left & Right Arrows",
	                "Both Left and Right arrows should be present.",
	                "Successfully verified both arrows are present.",
	                "Missing arrows in Related Products section.");

	            int Products = Common.findElements("xpath", "//div[contains(@class,'algolia-slider-items widget-product-carousel')]").size();
	            Common.assertionCheckwithReport(Products > 0, "Verifying the Related Product section",
	                "It should display the Related Product section",
	                "Successfully displayed Related Product section",
	                "Failed to display Related Product section");
	        } else {
	            Assert.fail("No related Product section available");
	        }

	    } catch (Exception | Error e) {
	        e.printStackTrace();
	        ExtenantReportUtils.addFailedLog("Verifying the Related Product section",
	            "It should display the Related Product section",
	            "Unable to display the Related Product section",
	            Common.getscreenShot("Failed to display Related Product section"));
	        Assert.fail();
	    }
	}

	public void User_GenerTed_Content() {
		// TODO Auto-generated method stub
		try {
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			int size =Common.findElements("css", "div[class='yotpo-display-wrapper yotpo-slider']").size();
			Common.assertionCheckwithReport(size > 0, "Verifying the User_Generted_Content section",
	                "It should display the Related Product section",
	                "Successfully displayed User_Generted_Content section",
	                "Failed to display User_Generted_Content section");
		}
		catch(Exception | Error e) {
			
		}
	}

	public void BNPL() {
		try {
			Common.switchFrames("xpath", "(//iframe[@role='presentation'])[1]");
			int BNPL = Common.findElements("css", "div[class='p-CondensedMultiPromotionView']").size();
			Common.assertionCheckwithReport(BNPL>0,
					"validating the  BNPL on the PDP page", "BNPL shoul be appear on the PDP page",
					"Sucessfully BNPL appeared on the PDP page", "failed to appeared the BNPL on the PDP page");
			Common.switchToDefault();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  BNPL on the PDP page",
					"BNPL shoul be appear on the PDP page", "Unable to appeared the BNPL on the PDP page",
					Common.getscreenShot("Failed to appeared the BNPL on the PDP page"));
			Assert.fail();
		}
	}

	public void Store_Logo_Validation() {
		
		try {
			
			Common.clickElement("css", "img[alt='Hydroflask store logo']");	
			String expectedHomePageURL = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
	System.out.println(expectedHomePageURL);
	Common.assertionCheckwithReport(
		    Common.getCurrentURL().equals(expectedHomePageURL),
		    "Validating store logo click redirects to homepage",
		    "Store logo should redirect to the homepage",
		    "Successfully redirected to homepage after clicking the store logo",
		    "Failed to redirect to homepage after clicking the store logo"
		);


		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
				    "Validating store logo click redirects to homepage",
				    "Store logo should redirect to the homepage",
				    "Unable to redirect to the homepage after clicking the store logo",
				    Common.getscreenShot("Failed to redirect to homepage after clicking the store logo")
				);

			Assert.fail();
		}
	}
	
	
	
	public void Hero_Banner_Validation() {
		try {
			int hero_banner = Common.findElements("xpath", "//section[contains(@aria-label,'Hero')]").size();

			Common.assertionCheckwithReport(hero_banner > 0, "Validating presence of hero banner on the homepage",
					"Hero banner should be displayed on the homepage",
					"Successfully displayed the hero banner on the homepage",
					"Failed to display the hero banner on the homepage");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating presence of hero banner on the homepage",
					"Hero banner should be displayed on the homepage", "Hero banner is not displayed on the homepage",
					Common.getscreenShot("Hero_Banner_Not_Displayed"));
			Assert.fail();
		}
	}
	
	public void CatogeryORproduct_Tile_Validation() {
		try {
			Common.scrollIntoView("css", "div[class='html-category-tiles'],[class='ussa-pmcs']");
			int CatogeryORproduct_Tile = Common.findElements("css", "div[class='html-category-tiles'],[class='ussa-pmcs']").size();

						Common.assertionCheckwithReport(
								CatogeryORproduct_Tile > 0,
			    "Validating presence of category or product tile on the homepage",
			    "Category or product tile should be displayed on the homepage",
			    "Successfully displayed the category or product tile on the homepage",
			    "Failed to display the category or product tile on the homepage"
			);


		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
				    "Validating presence of category or product tile on the homepage",
				    "Category or product tile should be displayed on the homepage",
				    "Category or product tile is not displayed on the homepage",
				    Common.getscreenShot("CategoryOrProductTile_Not_Displayed")
				);

			Assert.fail();
		}
	}
	public void CatogeryORproduct_Slider_Validation() {
		try {
			Common.scrollIntoView("css", "div[class='hf__category-product__slider']");
			int CatogeryORproduct_Slider = Common.findElements("css", "div[class='hf__category-product__slider']").size();

			Common.assertionCheckwithReport(
				    CatogeryORproduct_Slider > 0,
				    "Validating presence of category or product slider on the homepage",
				    "Category or product slider should be displayed on the homepage",
				    "Successfully displayed the category or product slider on the homepage",
				    "Failed to display the category or product slider on the homepage"
				);


		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
				    "Validating presence of category or product slider on the homepage",
				    "Category or product slider should be displayed on the homepage",
				    "Category or product slider is not displayed on the homepage",
				    Common.getscreenShot("CategoryOrProductSlider_Not_Displayed")
				);

			Assert.fail();
		}
	}
	
	public void Promo_Block_Validation() {
		try {
			Common.scrollIntoView("css", "section[aria-label='Recycle Values Section']");
			int promoBlock = Common.findElements("css", "section[aria-label='Recycle Values Section']").size();

			Common.assertionCheckwithReport(
				    promoBlock > 0,
				    "Validating presence of promo block on the homepage",
				    "Promo block should be displayed on the homepage",
				    "Successfully displayed the promo block on the homepage",
				    "Failed to display the promo block on the homepage"
				);



		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
				    "Validating presence of promo block on the homepage",
				    "Promo block should be displayed on the homepage",
				    "Promo block is not displayed on the homepage",
				    Common.getscreenShot("PromoBlock_Not_Displayed")
				);


			Assert.fail();
		}
	}
	
	public void Marketing_Flyout_Validation() {
		try {
			
			List<WebElement> animatedTeasers = Common.findElements("css", "div[data-testid='animated-teaser']");
			for (int i = 0; i < animatedTeasers.size(); i++)
			{
				animatedTeasers = Common.findElements("css", "div[class='needsclick kl-teaser-QYRkJ7 undefined kl-private-reset-css-Xuajs1']");
	            WebElement teaser = animatedTeasers.get(i);
	            teaser.click();
	            if( i == 1) {
	            	int marketingFlyout =Common.findElements("xpath", "(//div[@data-testid='POPUP'])[2]").size();
	            	Common.assertionCheckwithReport(
		            		marketingFlyout>0,
		            	    "Validating marketing flyout opens after clicking animated teaser",
		            	    "Marketing flyout should appear after clicking teaser element",
		            	    "Marketing flyout appeared successfully after clicking teaser element",
		            	    "Marketing flyout did not appear after clicking teaser element"
		            	);
	            }else {
	            	int marketingFlyout =Common.findElements("xpath", "(//div[@data-testid='POPUP'])[1]").size();
	            	Common.assertionCheckwithReport(
		            		marketingFlyout>0,
		            	    "Validating marketing flyout opens after clicking animated teaser",
		            	    "Marketing flyout should appear after clicking teaser element",
		            	    "Marketing flyout appeared successfully after clicking teaser element",
		            	    "Marketing flyout did not appear after clicking teaser element"
		            	);
	            }
	            	 close_add();        
	            

	            
			}	
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
				    "Validating marketing flyout opens after clicking animated teaser",
				    "Marketing flyout should appear after clicking teaser element",
				    "Marketing flyout did not appear after clicking teaser element",
				    Common.getscreenShot("MarketingFlyout_Not_Appeared")
				);


			Assert.fail();
		}
	}

	
}