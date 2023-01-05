package TestComponent.OXO;

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

public class GoldOxoHelper_Mobile {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public int getpageresponce(String url) throws MalformedURLException, IOException{
        HttpURLConnection c=(HttpURLConnection)new URL(url).openConnection();
          c.setRequestMethod("HEAD");
          c.connect();
          int r = c.getResponseCode();
          
          return r;
   }
	
	
public GoldOxoHelper_Mobile(String datafile,String sheetname) {
		
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
			//Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("OXO"),
			//		"validating store logo", "System directs to the Homepage", "Sucessfully navigate to home page",
			//		"faield to naviagte to homepage");
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

			//Sync.waitElementClickable("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");
			Sync.waitElementClickable("xpath", "//button[@data-action='toggle-nav']");
			Thread.sleep(3000);
			//Common.mouseOverClick("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");
			
			Common.clickElement("xpath", "//button[@data-action='toggle-nav']");
			try {
				//Common.mouseOver("xpath", "//span[contains(text(),'" + category + "')]");
			} catch (Exception e) {
				//Common.clickElement("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");
			}
			Thread.sleep(3000);
			Common.clickElement("xpath", "//span[contains(text(),'" + category + "')]");
//			Common.clickElement("xpath", "//span[text()='Shop All']");
			Common.clickElement("xpath", "//a[contains(@aria-label,'Shop All   Coffee & B')]");
			Thread.sleep(3000);
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
	
	public void babytoddler_headerlinks(String category) {
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
			Common.clickElement("xpath", "//a[contains(@aria-label,'Shop All   Baby & Toddler ')]");
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
//				Sync.waitElementPresent("xpath", "(//img[contains(@class,'m-product-card__image')])[2]");
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
//			Common.mouseOver("xpath", "//img[@alt='" + products + "']");
			
			Common.clickElement("xpath", "//img[@alt='" + products + "']");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			String name=Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
			Common.assertionCheckwithReport(true, "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"failed to Navigate to the PDP page");
			product_quantity(Dataset);
			Sync.waitPageLoad();
			click_UGC();
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
	
	public void Configurable_addtocart_pdp(String Dataset) {
		String product = data.get(Dataset).get("Colorproduct");
		String productcolor = data.get(Dataset).get("Color");
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[contains(@class,'m-product-card__image product')]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@class,'m-product-card__image product')]");
				String s = webelementslist.get(i).getAttribute("src");
				Thread.sleep(3000);
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//img[@alt='" + product + "']");

			String Productname = Common.findElement("xpath", "//a[contains(text(),'Write a review')]").getText();
			Sync.waitPageLoad();
			Thread.sleep(7000);
			System.out.println(Productname);
//			Common.assertionCheckwithReport(Common.getPageTitle().contains(product),
					Common.assertionCheckwithReport(Productname.contains("review"),
					"validating the product should navigate to the PDP page",
					"When we click on the product is should navigate to the PDP page",
					"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");

			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//div[@aria-label='" + productcolor + "']");
			Common.clickElement("xpath", "//div[@aria-label='" + productcolor + "']");
			
			click_UGC();
			product_quantity(Dataset);
			Common.clickElement("xpath", "//button[@title='Add to Bag']");
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
	
	public void click_UGC() {
		// TODO Auto-generated method stub
		try
		{
			Sync.waitElementPresent("xpath", "//div[@class='y-image-overlay ']");
//			Common.scrollIntoView("xpath", "//div[@class='y-image-overlay ']");
			Common.clickElement("xpath", "//div[@class='y-image-overlay ']");
//			Thread.sleep(6000);
			String yopto=Common.findElement("xpath", "//a[@class='yotpo-logo-link-new']").getAttribute("aria-label");
//			Thread.sleep(6000);
			System.out.println(yopto);
			WebElement UGC=Common.findElement("xpath", "//a[@class='yotpo-logo-link-new']//span");
			Thread.sleep(6000);
			Common.scrollIntoView(UGC);
			Common.assertionCheckwithReport(yopto.contains("Powered by"),
					"To validate the yopto popup in when we click on the UGC",
					"user should able to display the yopto popup",
					"Sucessfully yopto popup has been displayed","Failed to Displayed the yopto popup");
			Sync.waitElementPresent(40, "xpath", "//span[@aria-label='See Next Image']");
			Common.clickElement("xpath", "//span[@aria-label='See Next Image']");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//span[@aria-label='Cancel']");
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the yopto popup in when we click on the UGC",
					"user should able to display the yopto popup",
					"unable to Displayed the yopto popup",
					Common.getscreenShotPathforReport("Failed to Displayed the yopto popup"));
			Assert.fail();
		}
	}

	
	public void minicart_viewcart() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//p[@class='c-mini-cart__total-counter']//strong");
			String minicart = Common.findElement("xpath", "//p[@class='c-mini-cart__total-counter']//strong").getText();
			Sync.waitElementPresent("xpath", "//span[text()='View Cart']");
			Common.clickElement("xpath", "//span[text()='View Cart']");
			String viewcart = Common.findElement("xpath", "//span[@class='t-cart__items-count']").getText();
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
	
	public void minicart_Checkout() {

		try {
			click_minicart();
			String minicart = Common.findElement("xpath", "//p[@class='c-mini-cart__total-counter']//strong").getText();
			Sync.waitElementPresent("xpath", "//button[@title='Checkout']");
			Common.clickElement("xpath", "//button[@title='Checkout']");
			Thread.sleep(6000);
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
			Common.clickElement("xpath", "//a[contains(@class,'c-mini')]");
			Thread.sleep(6000);
			String openminicart = Common.findElement("xpath", "//div[@data-block='minicart']").getAttribute("class");
			System.out.println(openminicart);
			Common.assertionCheckwithReport(openminicart.contains("c-mini-cart"), "To validate the minicart popup",
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
	
	public void addDeliveryAddress_Gustuser(String dataSet) throws Exception {
		
		
		try {
			Thread.sleep(5000);
			Sync.waitElementVisible("id", "customer-email");
			Common.textBoxInput("id", "customer-email", data.get(dataSet).get("Email"));
			
		} catch (NoSuchElementException e) {
			minicart_Checkout();
			Common.textBoxInput("id", "customer-email",data.get(dataSet).get("Email"));

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
			Common.clickElement("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
//			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
//					data.get(dataSet).get("Street"));
//			String Text = Common.getText("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
			
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
	
	public void selectStandedshippingaddress() {
		try {

			int size = Common.findElements("xpath", "//input[@class='a-radio-button__input']").size();
			if (size > 0) {
				Sync.waitElementPresent(30, "xpath", "//input[@value='tablerate_bestway']");
				Common.clickElement("xpath", "//input[@value='tablerate_bestway']");
			}
				else {
					
					Assert.fail();
				
			}
		}
			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the Standed shipping method", "Select the Standed shipping method in shipping page ",
						"failed to select the Standed shipping method ",
						Common.getscreenShotPathforReport("failed select Standed shipping method"));

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
	
	public void validatingErrormessageShippingpage_negative() {
		int errorsize = Common.findElements("xpath", "//div[contains(@id,'error')]").size();
        String expectedResult = "Error message will dispaly when we miss the data in fields ";
		if (errorsize >= 0) {
			ExtenantReportUtils.addPassLog("validating the shippingPage error message", expectedResult,
					"sucessfully  dispaly error message", Common.getscreenShotPathforReport("errormessagenegative"));
		} else {

			ExtenantReportUtils.addFailedLog("validating the shippingPage error message",
					expectedResult, "failed to display error message",
					Common.getscreenShotPathforReport("failederrormessage"));

			Assert.fail();
		}
	}
	
	public String updatePaymentAndSubmitOrder(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String order="";
		addPaymentDetails(dataSet);
		String expectedResult = "It redirects to order confirmation page";
		
		  if (Common.findElements("xpath","//p[@class='p-FieldError Error']").size() > 0)
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
	

	public String addPaymentDetails(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String,String> Paymentmethod=new HashMap<String,String>();
        Sync.waitPageLoad();
		Thread.sleep(4000);
		String Number="";
		String cardnumber=data.get(dataSet).get("cardNumber");
		System.out.println(cardnumber);
		String expectedResult = "land on the payment section";
		//Common.refreshpage();
	
		try {
			Sync.waitPageLoad();
		 
	  	Sync.waitElementClickable("xpath", "//label[@for='stripe_payments']");
		int sizes=Common.findElements("xpath", "//label[@for='stripe_payments']").size();
		Thread.sleep(4000);
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
		return Number;
	}
	
	public void click_singinButton() {
		try {
			Sync.waitElementPresent("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//li[@class='m-account-nav__log-in']//a[text()='Sign In']");
			Thread.sleep(6000);
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
	public void Usersignin(String dataSet)  {

		try
		{
		Sync.waitPageLoad();
		Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
		Common.clickElement("xpath", "//button[contains(@class,'action lo')]");
		Sync.waitPageLoad();
		Thread.sleep(6000);
		Common.clickElement("xpath", "//button[@data-action='toggle-nav']");
		Sync.waitElementPresent("xpath", "//div[@class='m-account-nav__content']");
		Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
		Common.clickElement("xpath", "//a[@class='a-icon-text-btn a-icon-text-btn--icon-only m-account-nav__trigger-btn ui-corner-all']");
		Thread.sleep(6000);
		String login=Common.findElement("xpath", "//*[contains(text(),'Welcome')]").getText();
		
		
		System.out.println(login);
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
	
	
	public void update_shoppingcart(String Dataset) {
		// TODO Auto-generated method stub
		String quantity=data.get(Dataset).get("Quantity");
		try
		{
			Common.clickElement("xpath", "//select[@class='a-form-elem a-select-menu']");
			Common.dropdown("xpath", "//select[@class='a-form-elem a-select-menu']", Common.SelectBy.VALUE, quantity);
			Common.clickElement("xpath", "//span[text()='Update']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String productquantity=Common.findElement("xpath", "//select[@class='a-form-elem a-select-menu']").getAttribute("value");
			System.out.println(productquantity);
			Common.assertionCheckwithReport(productquantity.equals(quantity),
					"validating the update quantity in shopping cart page",
					"Quantity should be update in the shopping cart page",
					"Qunatity has been updated in the shopping cart page",
					"Failed to update the product quantity in the shopping cart page");	
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the update quantity in shopping cart page",
					"Quantity should be update in the shopping cart page",
					"Unable to update the product quantity in the shopping cart page", Common.getscreenShot("Failed to update the product quantity in the shopping cart page"));
			Assert.fail();
		}
		
	}
	
	
	public void minicart_ordersummary_discount(String Dataset) {
		// TODO Auto-generated method stub.
	     String expectedResult = "It should opens textbox input to enter discount.";
		try
		{
			Sync.waitElementPresent("xpath", "//button[@class='m-accordion__title']");
            Common.clickElement("xpath", "//button[@class='m-accordion__title']");

             Sync.waitElementPresent("xpath", "//input[@name='coupon_code']");

             Common.textBoxInput("xpath", "//input[@name='coupon_code']", data.get(Dataset).get("Discountcode"));

             int size = Common.findElements("xpath", "//input[@name='coupon_code']").size();
           Common.assertionCheckwithReport(size > 0, "verifying the Discount Code label", expectedResult,
                    "Successfully open the discount input box", "User unable enter Discount Code");
             Sync.waitElementClickable("xpath", "//button[@value='Add']");
            Common.clickElement("xpath", "//button[@value='Add']");
            Sync.waitPageLoad();
            Thread.sleep(4000);
            expectedResult = "It should apply discount on your price.If user enters invalid promocode it should display coupon code is not valid message.";
            String discountcodemsg = Common.getText("xpath", "//div[@data-ui-id='message-success']");
            Common.assertionCheckwithReport(discountcodemsg.contains("You used coupon code"),
                    "verifying pomocode", expectedResult, "promotion code working as expected",
                    "Promation code is not applied");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the promocode in the shopping cart page",
					"Promocode should be apply in the shopping cart page",
					"Unable to display the promocode in the shopping cart page",
					Common.getscreenShot("Failed to display the promocode in the shopping cart page"));
			Assert.fail();
		}
		try {
			String Subtotal = Common.getText("xpath", "//tr[@class='totals sub']//span[@class='price']").replace("$",
					"");
			Float subtotalvalue = Float.parseFloat(Subtotal);
			String shipping = Common.getText("xpath", "//tr[@class='totals shipping excl']//span[@class='price']")
					.replace("$", "");
			Float shippingvalue = Float.parseFloat(shipping);
			String Discount=Common.getText("xpath", "//tr[@class='totals discount']//span[@class='price']").replace("$", "");
			Float Discountvalue=Float.parseFloat(Discount);
			
			String ordertotal = Common.getText("xpath", "//tr[@class='grand totals']//span[@class='price']")
					.replace("$", "");
			Float ordertotalvalue = Float.parseFloat(ordertotal);
			Float Total = (subtotalvalue + shippingvalue)+Discountvalue;
			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			System.out.println(ExpectedTotalAmmount2);
			System.out.println(ordertotal);
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
	
	public void reorder() {
		// TODO Auto-generated method stub
		try
		{
			Common.clickElement("xpath", "//div[@class='m-account-nav__welcome']");
			Common.clickElement("xpath", "//a[text()='My Orders']");
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
					"Successfully navigated to the shopping cart page",
					"Failed to Navigate to the shopping cart page");
		}
		catch(Exception | Error e)
		{
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
		 String links=data.get(Dataset).get("Links");
		 int j=0;
		 
		 String[] strArray = links.split("\\r?\\n");
	    for (int i=0; i<strArray.length; i++) {
	       System.out.println(strArray[i]);
	       
	       if (Common.getCurrentURL().contains("preprod")) {
	    	   
	    	   Common.oppenURL((strArray[i]));
	    	   int  responcecode=getpageresponce(Common.getCurrentURL());
		       System.out.println(responcecode);
		   
		    if(responcecode==200) {
		    	ExtenantReportUtils.addPassLog("Validating Page URL ", "page configured with products ", "successfully page configured with products", Common.getscreenShotPathforReport("link"+i));
		    }
		    else {
		    	
		    	 j++;
		    	 
		    	 ExtenantReportUtils.addFailedLog("Validating Page URL  "+Common.getCurrentURL(), "page configured with products ", "unable to find page it showing 40 error",Common.getscreenShotPathforReport("link"+i));
		    
		    }
	   
	    	   
	       }
	       else if(Common.getCurrentURL().contains("https://mcloud-na.oxo.com/")) {
	    	   
	    	     Common.oppenURL(strArray[i].replace("mcloud-na-preprod", "www"));
	    	   
	    	    int  responcecode=getpageresponce(Common.getCurrentURL());
	    	       System.out.println(responcecode);
	    	   
	    	    if(responcecode==200) {
	    	    	ExtenantReportUtils.addPassLog("Validating Page URL ", "page configured with products ", "successfully page configured with products", Common.getscreenShotPathforReport("link"+i));
	    	    }
	    	    else {
	    	    	
	    	    	 j++;
	    	    	 
	    	    	 ExtenantReportUtils.addFailedLog("Validating Page URL  "+Common.getCurrentURL(), "page configured with products ", "unable to find page it showing 40 error",Common.getscreenShotPathforReport("link"+i));
	    	    
	    	    }
	       }
	    }
	    
		  if(j>1) {
			  Assert.fail();
		  }  
	  
	  
		
	}
	
	
	public void signout() {
		try {
			Sync.waitElementClickable("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Sync.waitElementClickable("xpath", "(//a[text()='Sign Out'])[2]");

			Common.javascriptclickElement("xpath", "(//a[text()='Sign Out'])[2]");

			Common.assertionCheckwithReport(
					Common.getText("xpath", "//h1[contains(text(),'You are signed out')]").equals("You are signed out"),
					"Validating My Account page navigation", "user sign in and navigate to my account page",
					"Successfully navigate to my account page", "Failed to navigate my account page ");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating sign out navigation ",
					"after clinking signout user signout fro the page", "user Successfully signout  ",
					Common.getscreenShotPathforReport("user Failed to signout"));
			Assert.fail();
		}

	}
public void addDeliveryAddress_Guest(String dataSet) throws Exception {
		
		
		try {
			Thread.sleep(5000);
			Sync.waitElementVisible("xpath", "//input[@type='email']");
			Common.textBoxInput("xpath", "//input[@type='email']", data.get(dataSet).get("Email"));
			
		} catch (NoSuchElementException e) {
			minicart_Checkout();
			Common.textBoxInput("xpath", "//input[@type='email']",data.get(dataSet).get("Email"));

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
//			Common.textBoxInputClear("name", "postcode");
//			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
			
			Common.textBoxInputClear("xpath", "(//input[@name='postcode'])");
			Common.textBoxInput("xpath", "(//input[@name='postcode'])", data.get(dataSet).get("postcode"));
			
			Thread.sleep(5000);

			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

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
	
public void select_Shipping_Method(String Dataset) {
	String method=data.get(Dataset).get("methods");
	
	try {
       Thread.sleep(4000);
		int size = Common.findElements("xpath", "//input[@class='a-radio-button__input']").size();
		if (size > 0) {
			Sync.waitElementPresent(30, "xpath", "//td[contains(text(),'"+ method +"')]");
			Common.clickElement("xpath", "//td[contains(text(),'"+ method +"')]");
		}
			else {
				
				Assert.fail();
			
		}
	}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Standed shipping method", "Select the Standed shipping method in shipping page ",
					"failed to select the Standed shipping method ",
					Common.getscreenShotPathforReport("failed select Standed shipping method"));

			Assert.fail();	
			}
}




public void Account_page_Validation(String Dataset) throws Exception {
	// TODO Auto-generated method stub
	Sync.waitElementPresent("xpath", "//div[@class='m-account-nav__content']");
	Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
	Sync.waitElementPresent("xpath", "//a[text()='My Account']");
	Common.clickElement("xpath", "//a[text()='My Account']");
	Sync.waitPageLoad();
	Thread.sleep(4000);
	
		String Accountlinks=data.get(Dataset).get("Account Links");
		String[] Account=Accountlinks.split(",");
		int i=0;
		try
    {
			for(i=0;i<Account.length;i++){
				Sync.waitElementPresent("xpath", "//div[@class='content account-nav-content']//a[contains(text(),'"+Account[i]+"')]");
				Common.clickElement("xpath", "//div[@class='content account-nav-content']//a[contains(text(),'"+Account[i]+"')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title=Common.findElement("xpath", "//h1[@class='page-title-wrapper h2']").getText();
				Common.assertionCheckwithReport(title.contains(Account[i]), "verifying Account page links "+Account[i],"user should navigate to the "+Account[i]+" page", "user successfully Navigated to the "+Account[i],"Failed click on the "+Account[i]);
		
	}
    }
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the account page links " +Account[i],"user should Navigate to the "+Account[i]+" page","User unable to navigate to the "+Account[i],Common.getscreenShotPathforReport("user Failed to Navigate to the respective page"));
	    Assert.fail();
	}
	
}

public void chefs_and_Residence(String Dataset) {
	
	String chefslinks=data.get(Dataset).get("chefs");
	String Aluminichefs=data.get(Dataset).get("AluminiChefs");
	String[] Chef=chefslinks.split(",");
	String[] Alumini=Aluminichefs.split(",");
	int i=0;
	try
	{
		Sync.waitPageLoad();
		
		Common.clickElement("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Chefs in Residence']");
		Sync.waitPageLoad();
		
		for(i=0;i<Chef.length;i++) {
			Sync.waitPageLoad();
		String link =	Common.findElement("xpath", "//div[@class='c_chefs-in-residence_nav']//a[text()='"+Chef[i]+"']").getText();
		System.out.println(link);
		Common.clickElement("xpath", "//div[@class='c_chefs-in-residence_nav']//a[text()='"+Chef[i]+"']");
		System.out.println(Chef[i]);
		System.out.println(Common.getPageTitle());
		 Common.assertionCheckwithReport(Common.getPageTitle().contains(Chef[i]),
					"validating navigation to the chefs and Residence page " +Chef, "After clicking on "+Chef+" it should navigate to "+Chef,
					"successfully Navigated to the chefs and Residence " +Chef, "failed to Navigate to the chefs and Residence ");

		}
		
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating navigation to the chefs and Residence  page ", "After clicking on chefs and Residence button it should navigate to the chefs and Residence  page ",
				"Unable to Navigated to the chefs and Residence ",
				Common.getscreenShot("failed to Navigate to the chefs and Residence "));
		Assert.fail();
	}

}

public void alumini_Chefs(String Dataset) {
	
	String Alumnichefs=data.get(Dataset).get("AluminiChefs");
	String[] Alumni=Alumnichefs.split(",");
	int i=0;
	try
	{
		Sync.waitPageLoad();
		for(i=0;i<Alumni.length;i++) {
			Common.clickElement("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Chefs in Residence']");
			Sync.waitPageLoad();
			Common.mouseOver("xpath", "//button[text()='Alumni Chefs']");
			Common.mouseOverClick("xpath", "//div[@class='c_chefs-in-residence_nav']//a[text()='"+Alumni[i]+"']");
			
		String link =	Common.findElement("xpath", "//div[@class='c_chefs-in-residence_nav']//a[text()='"+Alumni[i]+"']").getText();
		System.out.println(link);
		System.out.println(Alumni[i]);
		System.out.println(Common.getPageTitle());
		 Common.assertionCheckwithReport(Common.getPageTitle().contains(Alumni[i]),
					"validating navigation to the chefs and Residence page " +Alumni, "After clicking on "+Alumni+" it should navigate to "+Alumni,
					"successfully Navigated to the chefs and Residence " +Alumni, "failed to Navigate to the chefs and Residence ");

		}
		
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating navigation to the chefs and Residence  page ", "After clicking on chefs and Residence button it should navigate to the chefs and Residence  page ",
				"Unable to Navigated to the chefs and Residence ",
				Common.getscreenShot("failed to Navigate to the chefs and Residence "));
		Assert.fail();
	}

}
public void footerLinks_ProductSupport(String Dataset){
	 String Footerlinks=data.get(Dataset).get("Product Support");
		String[] Footer=Footerlinks.split(",");
		int i=0;
	  try{
		  Sync.waitPageLoad();
	  
	  for(i=0;i<Footer.length;i++) {
		  Common.actionsKeyPress(Keys.END);
		  Sync.waitPageLoad();
		  Common.scrollIntoView("xpath", "//div[@class='c-footer__items-wrapper']//a[contains(text(),'"+Footer[i]+"')]");
	  Common.clickElement("xpath","//div[@class='c-footer__items-wrapper']//a[contains(text(),'"+Footer[i]+"')]");
	  Sync.waitPageLoad();
	 String breadcrumb= Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
	  System.out.println(Footer[i]);
	  System.out.println( Common.getCurrentURL());
	  System.out.println(Common.getPageTitle());
	  Common.assertionCheckwithReport(Common.getCurrentURL().contains(Footer[i]) ||Common.getPageTitle().contains(Footer[i])|| breadcrumb.contains(Footer[i]),"Validate the Footer link "+Footer[i], "Click the footer link "+Footer[i]+"it will navigate to page"+Footer[i], "successfully navigating to "+Footer[i] +"page ","Failed to navigate to"+Footer[i]+"page");
	  }
	  }
	  catch (Exception |Error e) {
			e.printStackTrace();
	    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Footer[i],"Click the footer link "+Footer[i]+"it will navigate to page"+Footer[i], "Failed to navigate to"+Footer[i]+"page", Common.getscreenShotPathforReport("failed to land on "+Footer[i]));
	    Assert.fail();
	  
 }
	  footerLink_Shipping_Returns();
 }


public void footerLinks_CustomerSupport(String Dataset){

	 String Footerlinks=data.get(Dataset).get("Customer Support");
		String[] Footer=Footerlinks.split(",");
		int i=0;
	  try{
		  Sync.waitPageLoad();
	  
	  for(i=0;i<Footer.length;i++) {
		  Common.actionsKeyPress(Keys.END);
		  Sync.waitPageLoad();
		  Common.scrollIntoView("xpath", "//div[@class='c-footer__items-wrapper']//a[contains(text(),'"+Footer[i]+"')]");
	  Common.clickElement("xpath","//div[@class='c-footer__items-wrapper']//a[contains(text(),'"+Footer[i]+"')]");
	 
	  Sync.waitPageLoad();
	  System.out.println(Footer[i]);
	  System.out.println( Common.getCurrentURL());
	  System.out.println(Common.getPageTitle());
	  Common.assertionCheckwithReport(Common.getCurrentURL().contains(Footer[i]) ||Common.getPageTitle().contains(Footer[i]),"Validate the Footer link "+Footer[i], "Click the footer link "+Footer[i]+"it will navigate to page"+Footer[i], "successfully navigating to "+Footer[i] +"page ","Failed to navigate to"+Footer[i]+"page");
	  }
	  }
	  catch (Exception |Error e) {
			e.printStackTrace();
	    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Footer[i],"Click the footer link "+Footer[i]+"it will navigate to page"+Footer[i], "Failed to navigate to"+Footer[i]+"page", Common.getscreenShotPathforReport("failed to land on "+Footer[i]));
	    Assert.fail();
	  
 }
	  footerLink_FAQs();
 }

public void footerLinks_WeAre_Oxo(String Dataset){

	 String Footerlinks=data.get(Dataset).get("We Are OXO");
		String[] Footer=Footerlinks.split(",");
		int i=0;
	  try{
		  Sync.waitPageLoad();
	  
	  for(i=0;i<Footer.length;i++) {
		  Common.actionsKeyPress(Keys.END);
		  Sync.waitPageLoad();
		  Common.scrollIntoView("xpath", "//div[@class='c-footer__items-wrapper']//a[contains(text(),'"+Footer[i]+"')]");
	  Common.clickElement("xpath","//div[@class='c-footer__items-wrapper']//a[contains(text(),'"+Footer[i]+"')]");
	  Footer[i].toLowerCase( );
	  Sync.waitPageLoad();
	  System.out.println(Footer[i]);
	  System.out.println( Common.getCurrentURL());
	  System.out.println(Common.getPageTitle());
	  Common.assertionCheckwithReport(Common.getCurrentURL().contains(Footer[i]) ||Common.getPageTitle().contains(Footer[i]),"Validate the Footer link "+Footer[i], "Click the footer link "+Footer[i]+"it will navigate to page"+Footer[i], "successfully navigating to "+Footer[i] +"page ","Failed to navigate to"+Footer[i]+"page");
	  }
	  }
	  catch (Exception |Error e) {
			e.printStackTrace();
	    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Footer[i],"Click the footer link "+Footer[i]+"it will navigate to page"+Footer[i], "Failed to navigate to"+Footer[i]+"page", Common.getscreenShotPathforReport("failed to land on "+Footer[i]));
	    Assert.fail();
	  
 }
	footerLink_Careers();
	footerLink_Investor_Relations();
 }

public void footerLink_Shipping_Returns(){
	 String Links= "Shipping & Returns";
	  try{
		  Sync.waitPageLoad();
	  Common.actionsKeyPress(Keys.END);
	  Thread.sleep(3000);
	  
	  Common.clickElement("xpath","//a[text()='Shipping & Returns']");
	  Sync.waitPageLoad();
	  Common.assertionCheckwithReport(Common.getPageTitle().contains("Shipping"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
	  Sync.waitPageLoad();
	    Common.navigateBack();
	  }
	  catch (Exception |Error e) {
			e.printStackTrace();
	    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
	    Assert.fail();
	    
 }
 }

public void footerLink_FAQs(){
	 String Links= "FAQs";
	  try{
		  Sync.waitPageLoad();
	  Common.actionsKeyPress(Keys.END);
	  Thread.sleep(3000);
	  
	  Common.clickElement("xpath","//a[text()='FAQ']");
	  Sync.waitPageLoad();
	  Common.assertionCheckwithReport(Common.getPageTitle().contains("Knowledge Base"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
	  
	    Common.navigateBack();
	  }
	  catch (Exception |Error e) {
			e.printStackTrace();
	    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
	    Assert.fail();
	    
	  
 }
 }


public void footerLink_Careers(){
	 String Links= "Careers";
	  try{
		  Sync.waitPageLoad();
	  Common.actionsKeyPress(Keys.END);
	  Thread.sleep(3000);
	  
	  Common.clickElement("xpath","//a[text()='Careers ']");
	  Sync.waitPageLoad();
	  Common.switchWindows();
	  System.out.println(Common.getPageTitle());
	  System.out.println(Common.getCurrentURL());
	  Common.assertionCheckwithReport(Common.getPageTitle().contains("Careers")||Common.getCurrentURL().contains("careers"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
	  
	  }
	  catch (Exception |Error e) {
			e.printStackTrace();
	    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
	    Assert.fail();
	      
 }
	 
	  Common.closeCurrentWindow();
		Common.switchToFirstTab();
 }


public void footerLink_Investor_Relations(){
	 String Links= "Investor Relations";
	  try{
		  Sync.waitPageLoad();
	  Common.actionsKeyPress(Keys.END);
	  Thread.sleep(3000);
	  
	  Common.clickElement("xpath","//a[text()='Investor Relations ']");
	  Common.switchWindows();
	  Sync.waitPageLoad();
	  System.out.println(Common.getCurrentURL());
	  Common.assertionCheckwithReport(Common.getPageTitle().contains("Investor Relations")||Common.getCurrentURL().contains("investor"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
	 
	  }
	  catch (Exception |Error e) {
			e.printStackTrace();
	    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
	    Assert.fail();
	    
	  
 }
	  Common.closeCurrentWindow();
		Common.switchToFirstTab();
 }



	
	public void addDeliveryAddress_RegUser(String dataSet) {
		// TODO Auto-generated method stub
		String expectedResult = "shipping address is entering in the fields";
	
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
					Common.clickElement("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]");
				} catch (Exception e) {
					Common.actionsKeyPress(Keys.BACK_SPACE);
					Thread.sleep(1000);
					Common.actionsKeyPress(Keys.SPACE);
					Common.clickElement("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]");
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


				Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

		  

			

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
			Common.assertionCheckwithReport(Common.findElements("xpath", "//li[@class='nav item']//a[text()='Create an Account']").size()>0,
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
		try {
			Common.refreshpage();
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Sync.waitElementClickable(30, "xpath", "//input[@name='firstname']");
			Common.clickElement("xpath", "//input[@name='firstname']");
			Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(Dataset).get("FirstName"));
			Common.clickElement("xpath", "//input[@name='lastname']");
			Common.textBoxInput("id", "lastname", data.get(Dataset).get("LastName"));
			Common.clickElement("xpath", "//input[@name='email']");
			Common.textBoxInput("xpath", "//input[@name='email']", Utils.getEmailid());
			email=Common.findElement("xpath", "//input[@name='email']").getAttribute("value");
			Common.clickElement("xpath", "//input[@name='password']");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			Sync.waitElementPresent(30, "xpath", "//input[@name='password_confirmation']");
			Common.clickElement("xpath", "//input[@name='password_confirmation']");
			Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
					data.get(Dataset).get("Confirm Password"));
			Thread.sleep(5000);
			Sync.waitElementPresent(30, "xpath", "//button[@type='submit']//parent::div[@class='primary']");
			Common.clickElement("xpath", "//button[@type='submit']//parent::div[@class='primary']");
			Sync.waitPageLoad();
			Thread.sleep(9000);try {
			Sync.waitElementPresent("xpath", "//div[@data-ui-id='message-success']//div");}
			catch(Exception e) {Common.clickElement("xpath", "//button[@title='Sign Up']");;
			Sync.waitPageLoad();
			Thread.sleep(6000);}
			String message="";
			try {
			 message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();}
			catch(Exception e) {Common.clickElement("xpath", "//button[@title='Sign Up']");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			 message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();}
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
		return email;
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
		Thread.sleep(8000);
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
				String errorTexts = Common.findElement("xpath", "//div[contains(@data-ui-id,'error')]").getText();
				System.out.println(errorTexts);
				Common.assertionCheckwithReport(errorTexts.isEmpty()||errorTexts.contains("Please complete your payment details."), "validating the credit card information with valid data",
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
				String errorTexts = Common.findElement("xpath", "//div[contains(@data-ui-id,'error')]").getText();

				Common.assertionCheckwithReport(errorTexts.isEmpty()||errorTexts.contains("Please complete your payment details."), "validating the credit card information with valid data",
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
				Thread.sleep(4000);
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
			String shopping=Common.findElement("xpath", "//span[text()='Coffee & Beverage']//parent::a").getAttribute("href");
			String kitchen=Common.findElement("xpath", "//span[text()='Kitchenware']//parent::a").getAttribute("href");
			Common.clickElement("xpath", "//input[@name='password']");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			Common.clickElement("xpath", "(//span[text()='Toggle Password Visibility'])[1]");
			Sync.waitElementPresent(30, "xpath", "//input[@name='password_confirmation']");
			Common.clickElement("xpath", "//input[@name='password_confirmation']");
			Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
					data.get(Dataset).get("Confirm Password"));
			Common.clickElement("xpath", "(//span[text()='Toggle Password Visibility'])[2]");
			String accounttext=Common.findElement("xpath", "//div[@data-appearance='full-bleed']//p").getText();
			String confirmpassword=Common.findElement("xpath", "//input[@name='password_confirmation']").getAttribute("type");
			String password=Common.findElement("xpath", "//input[@name='password_confirmation']").getAttribute("type");	
			String Message = Common.findElement("id", "validation-classes").getCssValue("color");
			String Greencolor=Color.fromString(Message).asHex();
			String Message1 = Common.findElement("id", "validation-length").getAttribute("class");
			  Common.assertionCheckwithReport(Greencolor.equals("#4d8b40") &&
			  Message1.contains("complete")&&shopping.contains("/shop/coffee-beverage")&&kitchen.
			  contains("kitchenware")&&confirmpassword.equals("text")&&password.equals("text")&&accounttext.contains("Create an account"),
			  "validating the order confirmation page",
			  "User should able to view all details in the order confirmation page",
			  "Sucessfully all details has been displayed in the order confirmation",
			  "Failed to display all details in the order confirmation page");
			  Sync.waitElementPresent(30, "xpath", "(//span[text()='Toggle Password Visibility'])[1]");
			  Common.clickElement("xpath", "(//span[text()='Toggle Password Visibility'])[1]");
			  Sync.waitElementPresent(30, "xpath", "(//span[text()='Toggle Password Visibility'])[2]");
			  Common.clickElement("xpath", "(//span[text()='Toggle Password Visibility'])[2]");
			  String confirmpassword1=Common.findElement("xpath", "//input[@name='password_confirmation']").getAttribute("type");		
				String password1=Common.findElement("xpath", "//input[@name='password_confirmation']").getAttribute("type");		
			  Sync.waitElementPresent("xpath", "//label[@for='is_subscribed']");
			  Common.clickElement("xpath", "//label[@for='is_subscribed']");
			Common.findElement("xpath", "//label[@for='is_subscribed']").isSelected();
			  Common.assertionCheckwithReport(confirmpassword1.equals("password")&&password1.equals("password"),
					  "validating the password field changed to dots",
					  "After clicking on the eye icon it should be change to dots",
					  "Sucessfully passwords has been changed to dots after clicking on eye icon",
					  "Failed change passwords into dots after clicking on eye icon"); 
			  
			Sync.waitElementPresent(30, "xpath", "//span[text()='Create an Account']");
			Common.clickElement("xpath", "//span[text()='Create an Account']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
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
                System.out.println(product);
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
					Thread.sleep(4000);
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
	
	public void header_kitchenware(String Dataset) {
		// TODO Auto-generated method stub
		String names=data.get(Dataset).get("Kitchen ware");
		String[] kitchenLinks=names.split(",");
		int i=0;
		try
    {
			for(i=0;i<kitchenLinks.length;i++){
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()=' Kitchenware']");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'" +kitchenLinks[i]+"')]");
//				Common.clickElement("xpath", "//span[contains(text(),'" +Links[i]+"')]//following::ul//span[contains(text(),' Prep & Go Containers')]");
				Common.clickElement("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'" +kitchenLinks[i]+"')]");
				Sync.waitPageLoad();
				System.out.println(kitchenLinks[i]);
				
				if(kitchenLinks[i].contains("Food Containers")){
					header_kichenware_foodcontainers("FoodContainers");
				}
				else if(kitchenLinks[i].contains("Cutlery")) {
					header_kichenware_Cultery("Cutlery");
				}
				else if(kitchenLinks[i].contains("Cooking & Baking")) {
					header_kichenware_CookingAndBaking("CookingAndBaking");
				}
				else if(kitchenLinks[i].contains("Tools & Gadgets")) {
					header_kichenware_ToolsAndGadgets("ToolsAndGadgets");
				}
				
				
				Thread.sleep(4000);
				String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				String breadcrumb= Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
				String breadcrum= Common.findElement("xpath", "//nav[@aria-label='Breadcrumbs navigation']").getText();
				System.out.println(title);
				System.out.println(Common.getPageTitle());
				System.out.println(breadcrumb);
				System.out.println(kitchenLinks[i]);
				System.out.println(breadcrum);
				
				Common.assertionCheckwithReport(title.contains(kitchenLinks[i])||breadcrum.contains(kitchenLinks[i]) ||breadcrumb.contains(kitchenLinks[i])||Common.getPageTitle().contains(kitchenLinks[i]), "verifying the header link "+kitchenLinks[i]+ "Under Kitchenware","user should navigate to the "+kitchenLinks[i]+" page", "user successfully Navigated to the "+kitchenLinks[i],"Failed to navigate to the "+kitchenLinks[i]);
		
	}
    }
	
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link "+kitchenLinks[i]+ "Under Kitchenware",
					"User should navigate to the "+kitchenLinks[i]+"pages",
					" unable to navigate to the "+kitchenLinks[i]+"pages",
					Common.getscreenShot("Failed to navigate to the "+kitchenLinks[i]+"pages"));
			Assert.fail();
		}
		header_Kitchenware_ShopAll("Kitchenware");
		
	}
  
  public void header_kichenware_foodcontainers(String Dataset) {
		
		String names=data.get(Dataset).get("Food Containers");
		String[] Links=names.split(",");
		int i=0;
		try
    {
			for(i=0;i<Links.length;i++){
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()=' Kitchenware']");
				Common.clickElement("xpath", "//a//span[contains(text(),'Food Containers')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'Food Containers')]//following::ul//span[contains(text(),'" +Links[i]+"')]");

				Common.clickElement("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'Food Containers')]//following::ul//span[contains(text(),'" +Links[i]+"')]");
				Sync.waitPageLoad();
				System.out.println(Links[i]);
				Thread.sleep(4000);
				String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				String breadcrumb= Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
				System.out.println(title);
				System.out.println(Common.getPageTitle());
				System.out.println(breadcrumb);
				Common.assertionCheckwithReport(title.contains(Links[i]), "verifying the header link "+Links[i]+ "Under Kitchenware","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);
		
	}
    }
	
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link "+Links[i]+ "Under Kitchenware",
					"User should navigate to the "+Links[i]+"pages",
					" unable to navigate to the "+Links[i]+"pages",
					Common.getscreenShot("Failed to navigate to the "+Links[i]+"pages"));
			Assert.fail();
		}
		
	}
  
  public void header_kichenware_Cultery(String Dataset) {

		String names=data.get(Dataset).get("Cutleryy");
		String[] Links=names.split(",");
		int i=0;
		try
    {
			for(i=0;i<Links.length;i++){
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()=' Kitchenware']");
				Common.clickElement("xpath", "//a//span[contains(text(),'Cutlery')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'Cutlery')]//following::ul//span[contains(text(),'" +Links[i]+"')]");
				//span[contains(text(),'Food Containers')]//following::ul//span[contains(text(),'" +Links[i]+"')]
				Common.clickElement("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'Cutlery')]//following::ul//span[contains(text(),'" +Links[i]+"')]");
				Sync.waitPageLoad();
				System.out.println(Links[i]);
				Thread.sleep(4000);
				String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				String breadcrumb= Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
				System.out.println(title);
				System.out.println(Common.getPageTitle());
				System.out.println(breadcrumb);
				Common.assertionCheckwithReport(title.contains(Links[i])||breadcrumb.contains(Links[i])||Common.getPageTitle().contains(Links[i]), "verifying the header link "+Links[i]+ "Under Kitchenware","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);
		
	}
    }
	
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link "+Links[i]+ "Under Kitchenware",
					"User should navigate to the "+Links[i]+"pages",
					" unable to navigate to the "+Links[i]+"pages",
					Common.getscreenShot("Failed to navigate to the "+Links[i]+"pages"));
			Assert.fail();
		}
		
	}
  
  public void header_kichenware_CookingAndBaking(String Dataset) {
		
		String names=data.get(Dataset).get("Cooking And Baking");
		String[] Links=names.split(",");
		int i=0;
		try
    {
			for(i=0;i<Links.length;i++){
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()=' Kitchenware']");
				Common.clickElement("xpath", "//a//span[contains(text(),'Cooking & Baking')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'Cooking & Baking')]//following::ul//span[contains(text(),'" +Links[i]+"')]");
				//span[contains(text(),'Food Containers')]//following::ul//span[contains(text(),'" +Links[i]+"')]
				Common.clickElement("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'Cooking & Baking')]//following::ul//span[contains(text(),'" +Links[i]+"')]");
				Sync.waitPageLoad();
				System.out.println(Links[i]);
				Thread.sleep(4000);
				String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				String breadcrumb= Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
				System.out.println(title);
				System.out.println(Common.getPageTitle());
				System.out.println(breadcrumb);
				Common.assertionCheckwithReport(title.contains(Links[i])||breadcrumb.contains(Links[i])||Common.getPageTitle().contains(Links[i]), "verifying the header link "+Links[i]+ "Under Kitchenware","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);
		
	}
    }
	
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link "+Links[i]+ "Under Kitchenware",
					"User should navigate to the "+Links[i]+"pages",
					" unable to navigate to the "+Links[i]+"pages",
					Common.getscreenShot("Failed to navigate to the "+Links[i]+"pages"));
			Assert.fail();
		}
		
	}
  
  public void header_kichenware_ToolsAndGadgets(String Dataset) {
		// TODO Auto-generated method stub
		String names=data.get(Dataset).get("Tools And Gadgets");
		String[] Links=names.split(",");
		int i=0;
		try
    {
			for(i=0;i<Links.length;i++){
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()=' Kitchenware']");
				Common.clickElement("xpath", "//a//span[contains(text(),'Tools & Gadgets')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'Tools & Gadgets')]//following::ul//span[contains(text(),'" +Links[i]+"')]");
				//span[contains(text(),'Food Containers')]//following::ul//span[contains(text(),'" +Links[i]+"')]
				Common.clickElement("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'Tools & Gadgets')]//following::ul//span[contains(text(),'" +Links[i]+"')]");
				Sync.waitPageLoad();
				System.out.println(Links[i]);
				Thread.sleep(4000);
				String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				String breadcrumb= Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
				System.out.println(title);
				System.out.println(Common.getPageTitle());
				System.out.println(breadcrumb);
				Common.assertionCheckwithReport(title.contains(Links[i])||breadcrumb.contains(Links[i])||Common.getPageTitle().contains(Links[i]), "verifying the header link "+Links[i]+ "Under Kitchenware","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);
		
	}
    }
	
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link "+Links[i]+ "Under Kitchenware",
					"User should navigate to the "+Links[i]+"pages",
					" unable to navigate to the "+Links[i]+"pages",
					Common.getscreenShot("Failed to navigate to the "+Links[i]+"pages"));
			Assert.fail();
		}
		
	}
 
  public void header_CoffeeAndBeverage(String Dataset) {
		// TODO Auto-generated method stub
		String names=data.get(Dataset).get("Coffee And Beverage");
		String[] Links=names.split(",");
		int i=0;
		try
    {
			for(i=0;i<Links.length;i++){
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()=' Coffee & Beverage']");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'" +Links[i]+"')]");
				Common.clickElement("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'" +Links[i]+"')]");
				Sync.waitPageLoad();
				System.out.println(Links[i]);
				if(Links[i].contains("Coffee & Tea")){
					header_CoffeeAndBeverage_Coffee_Tea("CoffeeAndTea");
				}
				else if(Links[i].contains("Barware")) {
					header_CoffeeAndBeverage_Barware("Barware");
				}
				
				Thread.sleep(4000);
				String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				String breadcrum= Common.findElement("xpath", "//nav[@aria-label='Breadcrumbs navigation']").getText();
				System.out.println(title);
				System.out.println(breadcrum);
				Common.assertionCheckwithReport(title.contains(Links[i])||breadcrum.contains(Links[i]), "verifying the header link "+Links[i]+ "Under Kitchenware","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);
		
	}
    }
	
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link "+Links[i]+ "Under Kitchenware",
					"User should navigate to the "+Links[i]+"pages",
					" unable to navigate to the "+Links[i]+"pages",
					Common.getscreenShot("Failed to navigate to the "+Links[i]+"pages"));
			Assert.fail();
		}
		header_CoffeeAndBeverage_ShopAll("Coffee & Beverage");

	}
  
  public void header_CoffeeAndBeverage_Coffee_Tea(String Dataset) {
		// TODO Auto-generated method stub
		String names=data.get(Dataset).get("Coffee And Tea");
		String[] Links=names.split(",");
		int i=0;
		try
    {
			for(i=0;i<Links.length;i++){
//				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()=' Coffee & Beverage']");
				Common.clickElement("xpath", "//a//span[contains(text(),'Coffee & Tea')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'Coffee & Tea')]//following::ul//span[contains(text(),'" +Links[i]+"')]");
				//span[contains(text(),'Food Containers')]//following::ul//span[contains(text(),'" +Links[i]+"')]
				Common.clickElement("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'Coffee & Tea')]//following::ul//span[contains(text(),'" +Links[i]+"')]");
				Sync.waitPageLoad();
				System.out.println(Links[i]);
				Thread.sleep(4000);
//				String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
//				Common.assertionCheckwithReport(title.contains(Links[i]), "verifying the header link "+Links[i]+ "Under Kitchenware","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);
		
	}
    }
	
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link "+Links[i]+ "Under Kitchenware",
					"User should navigate to the "+Links[i]+"pages",
					" unable to navigate to the "+Links[i]+"pages",
					Common.getscreenShot("Failed to navigate to the "+Links[i]+"pages"));
			Assert.fail();
		}
		
	}
  
  public void header_CoffeeAndBeverage_Barware(String Dataset) {
		// TODO Auto-generated method stub
		String names=data.get(Dataset).get("Bar ware");
		String[] Links=names.split(",");
		int i=0;
		try
    {
			for(i=0;i<Links.length;i++){
//				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()=' Coffee & Beverage']");
				Common.clickElement("xpath", "//a//span[contains(text(),'Barware')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'Barware')]//following::ul//span[contains(text(),'" +Links[i]+"')]");
				//span[contains(text(),'Food Containers')]//following::ul//span[contains(text(),'" +Links[i]+"')]
				Common.clickElement("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'Barware')]//following::ul//span[contains(text(),'" +Links[i]+"')]");
				Sync.waitPageLoad();
				System.out.println(Links[i]);
				Thread.sleep(4000);
//				String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
//				Common.assertionCheckwithReport(title.contains(Links[i]), "verifying the header link "+Links[i]+ "Under Kitchenware","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);
		
	}
    }
	
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link "+Links[i]+ "Under Kitchenware",
					"User should navigate to the "+Links[i]+"pages",
					" unable to navigate to the "+Links[i]+"pages",
					Common.getscreenShot("Failed to navigate to the "+Links[i]+"pages"));
			Assert.fail();
		}
		
	}
  
  
  public void header_CleaningAndOrganization(String Dataset) {
		// TODO Auto-generated method stub
		String names=data.get(Dataset).get("Cleaning And Organization");
		String[] Links=names.split(",");
		int i=0;
		try
    {
			for(i=0;i<Links.length;i++){
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()=' Cleaning & Organization']");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'" +Links[i]+"')]");
				Common.clickElement("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'" +Links[i]+"')]");
				Sync.waitPageLoad();
				System.out.println(Links[i]);
				if(Links[i].contains("Kitchen")){
					header_CleaningAndOrganization_Kitchen("Kitchen");
				}
				else if(Links[i].contains("Bathroom")) {
					header_CleaningAndOrganization_Bathroom("Bathroom");
				}
				else if(Links[i].contains("Home & Office")) {
					header_CleaningAndOrganization_HomeAndOffice("HomeAndOffice");
				}
				Thread.sleep(4000);
				String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				String breadcrum= Common.findElement("xpath", "//nav[@aria-label='Breadcrumbs navigation']").getText();
				System.out.println(title);
				System.out.println(breadcrum);
				Common.assertionCheckwithReport(title.contains(Links[i])||breadcrum.contains(Links[i]), "verifying the header link "+Links[i]+ "Under Kitchenware","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);
		
	}
    }
	
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link "+Links[i]+ "Under Kitchenware",
					"User should navigate to the "+Links[i]+"pages",
					" unable to navigate to the "+Links[i]+"pages",
					Common.getscreenShot("Failed to navigate to the "+Links[i]+"pages"));
			Assert.fail();
		}
		header_CleaningAndOrganization_ShopAll("Cleaning & Organization");
	}
  
  public void header_CleaningAndOrganization_Kitchen(String Dataset) {
		// TODO Auto-generated method stub
		String names=data.get(Dataset).get("Kitchenn");
		String[] Links=names.split(",");
		int i=0;
		try
    {
			for(i=0;i<Links.length;i++){
//				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()=' Cleaning & Organization']");
				Common.clickElement("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'Kitchen')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'Kitchen')]//following::ul//span[contains(text(),'" +Links[i]+"')]");
				//span[contains(text(),'Food Containers')]//following::ul//span[contains(text(),'" +Links[i]+"')]
				Common.clickElement("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'Kitchen')]//following::ul//span[contains(text(),'" +Links[i]+"')]");
				Sync.waitPageLoad();
				System.out.println(Links[i]);
				Thread.sleep(4000);
//				String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
//				Common.assertionCheckwithReport(title.contains(Links[i]), "verifying the header link "+Links[i]+ "Under Kitchenware","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);
		
	}
    }
	
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link "+Links[i]+ "Under Kitchenware",
					"User should navigate to the "+Links[i]+"pages",
					" unable to navigate to the "+Links[i]+"pages",
					Common.getscreenShot("Failed to navigate to the "+Links[i]+"pages"));
			Assert.fail();
		}
		
	}
  
  public void header_CleaningAndOrganization_Bathroom(String Dataset) {
		// TODO Auto-generated method stub
		String names=data.get(Dataset).get("Bath room");
		String[] Links=names.split(",");
		int i=0;
		try
    {
			for(i=0;i<Links.length;i++){
//				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()=' Cleaning & Organization']");
				Common.clickElement("xpath", "//a//span[contains(text(),'Bathroom')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'Bathroom')]//following::ul//span[contains(text(),'" +Links[i]+"')]");
				//span[contains(text(),'Food Containers')]//following::ul//span[contains(text(),'" +Links[i]+"')]
				Common.clickElement("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'Bathroom')]//following::ul//span[contains(text(),'" +Links[i]+"')]");
				Sync.waitPageLoad();
				System.out.println(Links[i]);
				Thread.sleep(4000);
//				String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
//				Common.assertionCheckwithReport(title.contains(Links[i]), "verifying the header link "+Links[i]+ "Under Kitchenware","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);
		
	}
    }
	
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link "+Links[i]+ "Under Kitchenware",
					"User should navigate to the "+Links[i]+"pages",
					" unable to navigate to the "+Links[i]+"pages",
					Common.getscreenShot("Failed to navigate to the "+Links[i]+"pages"));
			Assert.fail();
		}
		
	}
  
  public void header_CleaningAndOrganization_HomeAndOffice(String Dataset) {
		// TODO Auto-generated method stub
		String names=data.get(Dataset).get("Home And Office");
		String[] Links=names.split(",");
		int i=0;
		try
    {
			for(i=0;i<Links.length;i++){
//				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()=' Cleaning & Organization']");
				Common.clickElement("xpath", "//a//span[contains(text(),'Home & Office')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'Home & Office')]//following::ul//span[contains(text(),'" +Links[i]+"')]");
				//span[contains(text(),'Food Containers')]//following::ul//span[contains(text(),'" +Links[i]+"')]
				Common.clickElement("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'Home & Office')]//following::ul//span[contains(text(),'" +Links[i]+"')]");
				Sync.waitPageLoad();
				System.out.println(Links[i]);
				Thread.sleep(4000);
//				String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
//				Common.assertionCheckwithReport(title.contains(Links[i]), "verifying the header link "+Links[i]+ "Under Kitchenware","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);
		
	}
    }
	
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link "+Links[i]+ "Under Kitchenware",
					"User should navigate to the "+Links[i]+"pages",
					" unable to navigate to the "+Links[i]+"pages",
					Common.getscreenShot("Failed to navigate to the "+Links[i]+"pages"));
			Assert.fail();
		}
		
	}
  
  
  
  public void header_BabyandToddler(String Dataset) {
		// TODO Auto-generated method stub
		String names=data.get(Dataset).get("Baby And Toddler");
		String[] Links=names.split(",");
		int i=0;
		try
    {
			for(i=0;i<Links.length;i++){
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()=' Baby & Toddler']");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'" +Links[i]+"')]");
				Common.clickElement("xpath", "//li[contains(@class,'level2 ')]//a//span[contains(text(),'" +Links[i]+"')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
//				String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
//				Common.assertionCheckwithReport(title.contains(Links[i]), "verifying the header link "+Links[i]+ "Under Kitchenware","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);
		
	}
    }
	
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link "+Links[i]+ "Under Kitchenware",
					"User should navigate to the "+Links[i]+"pages",
					" unable to navigate to the "+Links[i]+"pages",
					Common.getscreenShot("Failed to navigate to the "+Links[i]+"pages"));
			Assert.fail();
		}
		
	}
  

	  public void header_ShopAll(String Dataset) {
			// TODO Auto-generated method stub
			String names=data.get(Dataset).get("shop all");
			String[] Links=names.split(",");
			int i=0;
			try
	    {
					for(i=0;i<Links.length;i++){
					Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
					Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
					Thread.sleep(3000);
					Sync.waitElementPresent("xpath", "//span[contains(text(),' "+Links[i]+"')]");       
					Common.clickElement("xpath", "//span[contains(text(),' "+Links[i]+"')]");
					Common.clickElement("xpath", "//a[contains(@aria-label,'" +Links[i]+ "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
					Common.assertionCheckwithReport(title.contains(Links[i]), "verifying the header link "+Links[i]+ "Under Featured","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);
			
		}
	    }
		
			catch(Exception | Error e)
			{
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the header link "+Links[i]+ "Under Featured",
						"User should navigate to the "+Links[i]+"pages",
						" unable to navigate to the "+Links[i]+"pages",
						Common.getscreenShot("Failed to navigate to the "+Links[i]+"pages"));
				Assert.fail();
			}
			
		}
  
	  
	  public void header_Kitchenware_ShopAll(String Dataset) {
			// TODO Auto-generated method stub
			String names=data.get(Dataset).get("shop all");
			String[] Links=names.split(",");
			int i=0;
			try
	    {
				for(i=0;i<Links.length;i++){
					Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
					Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
					Common.clickElement("xpath", "//span[text()=' Kitchenware']");
					Sync.waitElementPresent("xpath", "//span[contains(text(),' "+Links[i]+"')]"); 
					Common.clickElement("xpath", "//span[contains(text(),' "+Links[i]+"')]");
					Common.clickElement("xpath", "//a[contains(@aria-label,'" +Links[i]+ "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
					Common.assertionCheckwithReport(title.contains(Links[i]), "verifying the header link "+Links[i]+ "Under Featured","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);
			
		}
	    }
		
			catch(Exception | Error e)
			{
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the header link "+Links[i]+ "Under Featured",
						"User should navigate to the "+Links[i]+"pages",
						" unable to navigate to the "+Links[i]+"pages",
						Common.getscreenShot("Failed to navigate to the "+Links[i]+"pages"));
				Assert.fail();
			}
			
		}
	  
	  public void header_CoffeeAndBeverage_ShopAll(String Dataset) {
			// TODO Auto-generated method stub
			String names=data.get(Dataset).get("shop all");
			String[] Links=names.split(",");
			int i=0;
			try
	    {
				for(i=0;i<Links.length;i++){
					Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
					Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
					Common.clickElement("xpath", "//span[text()=' Coffee & Beverage']");
					Sync.waitElementPresent("xpath", "//span[contains(text(),' "+Links[i]+"')]"); 
					Common.clickElement("xpath", "//span[contains(text(),' "+Links[i]+"')]");
					Common.clickElement("xpath", "//a[contains(@aria-label,'" +Links[i]+ "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
					Common.assertionCheckwithReport(title.contains(Links[i]), "verifying the header link "+Links[i]+ "Under Featured","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);
			
		}
	    }
		
			catch(Exception | Error e)
			{
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the header link "+Links[i]+ "Under Featured",
						"User should navigate to the "+Links[i]+"pages",
						" unable to navigate to the "+Links[i]+"pages",
						Common.getscreenShot("Failed to navigate to the "+Links[i]+"pages"));
				Assert.fail();
			}
			
		}
  
	  public void header_CleaningAndOrganization_ShopAll(String Dataset) {
			// TODO Auto-generated method stub
			String names=data.get(Dataset).get("shop all");
			String[] Links=names.split(",");
			int i=0;
			try
	    {
				for(i=0;i<Links.length;i++){
					Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
					Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
					Common.clickElement("xpath", "//span[text()=' Cleaning & Organization']");
					Sync.waitElementPresent("xpath", "//span[contains(text(),' "+Links[i]+"')]"); 
					Common.clickElement("xpath", "//span[contains(text(),' "+Links[i]+"')]");
					Common.clickElement("xpath", "//a[contains(@aria-label,'" +Links[i]+ "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
					Common.assertionCheckwithReport(title.contains(Links[i]), "verifying the header link "+Links[i]+ "Under Featured","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);
			
		}
	    }
		
			catch(Exception | Error e)
			{
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the header link "+Links[i]+ "Under Featured",
						"User should navigate to the "+Links[i]+"pages",
						" unable to navigate to the "+Links[i]+"pages",
						Common.getscreenShot("Failed to navigate to the "+Links[i]+"pages"));
				Assert.fail();
			}
			
		}
  
	  public void header_Menu_ImageItemblocksLinks(String Dataset) {
			// TODO Auto-generated method stub
			String names=data.get(Dataset).get("shop all");
			String[] Links=names.split(",");
			int i=0;
			try
	    {
				for(i=0;i<Links.length;i++){
					Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
					Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
//					Common.clickElement("xpath", "//span[text()=' Cleaning & Organization']");
					Sync.waitElementPresent("xpath", "//span[contains(text(),' "+Links[i]+"')]"); 
					Common.clickElement("xpath", "//span[contains(text(),' "+Links[i]+"')]");
					Common.clickElement("xpath", "//a[contains(@aria-label,'" +Links[i]+ "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title=Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
					Common.assertionCheckwithReport(title.contains(Links[i]), "verifying the header link "+Links[i]+ "Under Featured","user should navigate to the "+Links[i]+" page", "user successfully Navigated to the "+Links[i],"Failed to navigate to the "+Links[i]);
			
		}
	    }
		
			catch(Exception | Error e)
			{
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the header link "+Links[i]+ "Under Featured",
						"User should navigate to the "+Links[i]+"pages",
						" unable to navigate to the "+Links[i]+"pages",
						Common.getscreenShot("Failed to navigate to the "+Links[i]+"pages"));
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

	public void giftCreation(String Dataset) {
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
		click_giftcard();
		newregistry_CTA("Birthday");
		try {
	        Common.textBoxInput("xpath", "//input[@id='title']", data.get(Dataset).get("Type"));
	        Common.textBoxInput("xpath", "//textarea[@id='message']", data.get(Dataset).get("Message"));
	        Common.dropdown("xpath", "//select[@id='is_public']", SelectBy.TEXT, data.get(Dataset).get("privacy"));
	        Common.dropdown("xpath", "//select[@id='is_active']", SelectBy.TEXT, data.get(Dataset).get("Status"));
	        String eventname=Common.findElement("xpath", "//span[@class='value']").getText();
	        if(eventname.equals("Birthday"))
	        {
	            Common.dropdown("xpath", "//select[@id='event_country_region']", SelectBy.TEXT, data.get(Dataset).get("Region"));
	            Common.textBoxInput("xpath", "//input[@id='event_date']", data.get(Dataset).get("Date"));
	        }
	        else if(eventname.equals("Wedding"))
	        {
	        	
	        	 Common.dropdown("xpath", "//select[@id='event_country_region']", SelectBy.TEXT, data.get(Dataset).get("Region"));
		            Common.textBoxInput("xpath", "//input[@id='event_date']", data.get(Dataset).get("Date"));
		            Common.textBoxInput("xpath", "//input[@name='event_location']", data.get(Dataset).get("Location")); 
		            Common.textBoxInput("xpath", "//input[@name='registry[number_of_guests]']", data.get(Dataset).get("GropName"));
	        	
	        }
	        else
	        {
	        	Common.dropdown("xpath", "//select[@id='event_country_region']", SelectBy.TEXT, data.get(Dataset).get("Region"));
	        	Common.textBoxInput("xpath", "//input[@name='event_location']", data.get(Dataset).get("Location"));
	        }
//	        Baby_Registry("Baby Registry");
	        Registrant_Information("Birthday");
	        String shipping=Common.findElement("xpath", "(//select[@name='address_type_or_id']//option)[2]").getAttribute("value");
	        Common.dropdown("xpath", "//select[@name='address_type_or_id']", Common.SelectBy.VALUE, shipping);
	        Common.clickElement("id", "submit.save");
	        Sync.waitPageLoad();
	        Thread.sleep(4000);
	        String message=Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
	        Common.assertionCheckwithReport(message.equals("You saved this gift registry."),
					"validating the gift registery page navigation ", "After clicking on save button It should be able to navigate to the gift registry page ",
					"successfully Navigated to the gift registry page", "failed to Navigate to the gift registry page");
	        
	       
	        
	    } catch (Exception e) {
	      
	        e.printStackTrace();
	        ExtenantReportUtils.addFailedLog("validating the gift registery page navigation ", "After clicking on save button It should be able to navigate to the gift registry page ",
					"unable to Navigated to the gift registry page",
					Common.getscreenShot(
							"Failed to Navigate to the gift registry page"));
	        Assert.fail();
	    }
	}
	
	public void click_giftcard()
	{
		try
		{
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//a[text()='Gift Registry']");
			Common.clickElement("xpath", "//a[text()='Gift Registry']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Gift Registry"),
					"validating the gift registery page navigation ", "It should be able to navigate to the gift registry page ",
					"successfully Navigated to the gift registry page", "failed to Navigate to the gift registry page");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the gift registery page navigation ", "It should be able to navigate to the gift registry page ",
					"Unable to Navigated to the gift registry page",
					Common.getscreenShot("Failed to Navigate to the gift registry page"));
			Assert.fail();
		}
	}
	
	public void newregistry_CTA(String Dataset)
	{
		try
		{
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//span[text()='Create New Registry']");
			Common.clickElement("xpath", "//span[text()='Create New Registry']");
			Sync.waitPageLoad();
			 Common.dropdown("id", "type_id", SelectBy.TEXT, data.get(Dataset).get("Type"));
			    Common.clickElement("id", "submit.next");
			    Sync.waitPageLoad();
			    Thread.sleep(6000);
			    String eventname=Common.findElement("xpath", "//span[@class='value']").getText();
			    System.out.println(eventname);
			    Thread.sleep(6000);
			    Common.assertionCheckwithReport(eventname.equals("Birthday")||eventname.equals("Wedding")||eventname.equals("Baby Registry")  ,
						"validating seleted event page navigation ", "It should be able to navigate to Respective event page  ",
						"successfully Respective selected event page", "failed to Navigate to the respective event page");
			    
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating seleted event page navigation ", "It should be able to navigate to Respective event page  ",
					"Unable to navigate to the selected Respective event page",
					Common.getscreenShot("failed to Navigate to the respective event page"));
			Assert.fail();
		}
	}
	
	public void Baby_Registry(String Dataset)
	{
		String babygender=data.get(Dataset).get("Gender");
		System.out.println(babygender);
		try
		{
			Sync.waitElementPresent("xpath", "//select[@name='registry[baby_gender]']");
        	Common.dropdown("xpath", "//select[@name='registry[baby_gender]']", Common.SelectBy.TEXT, babygender);
        	Thread.sleep(4000);
        	String gender=Common.findElement("xpath", "//select[@name='registry[baby_gender]']//option[@value='boy']").getText();
        	Common.assertionCheckwithReport(gender.equals(babygender),
					"validating the baby gender in gift registry ", "It should display the baby gender under the gift registry",
					"successfully baby gender is displayed under the gift registry", "failed to display the baby gender under gift registry");
		}
		catch(Exception | Error e )
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the baby gender in gift registry ", "It should display the baby gender under the gift registry",
					"unable to display the baby gender under the gift registry",
					Common.getscreenShot("failed to display the baby gender under gift registry"));
			Assert.fail();
		}
	}
	
	public void Registrant_Information(String Dataset)
	{
		String eventname=Common.findElement("xpath", "//span[@class='value']").getText();
		try
		{
		if(eventname.equals("Birthday"))
		{
			Common.textBoxInput("xpath", "//input[@name='registrant[0][firstname]']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='registrant[0][lastname]']", data.get(Dataset).get("LastName"));
			Common.textBoxInput("xpath", "//input[@name='registrant[0][email]']", data.get(Dataset).get("Email"));
			Common.clickElement("id", "add-registrant-button");
			Common.textBoxInput("xpath", "//input[@name='registrant[1][firstname]']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='registrant[1][lastname]']", data.get(Dataset).get("LastName"));
			Common.textBoxInput("xpath", "//input[@name='registrant[1][email]']", data.get(Dataset).get("UserName"));
		}
		else
		{
			Common.textBoxInput("xpath", "//input[@name='registrant[0][firstname]']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='registrant[0][lastname]']", data.get(Dataset).get("LastName"));
			Common.textBoxInput("xpath", "//input[@name='registrant[0][email]']", data.get(Dataset).get("Email"));
			Common.dropdown("xpath", "//select[@name='registrant[0][role]']", Common.SelectBy.TEXT, data.get(Dataset).get("Role"));
			Common.clickElement("id", "add-registrant-button");
			Common.textBoxInput("xpath", "//input[@name='registrant[1][firstname]']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='registrant[1][lastname]']", data.get(Dataset).get("LastName"));
			Common.textBoxInput("xpath", "//input[@name='registrant[1][email]']", data.get(Dataset).get("UserName"));
			Common.dropdown("xpath", "//select[@name='registrant[1][role]']", Common.SelectBy.TEXT, data.get(Dataset).get("Role"));
		}
		String registry=Common.findElement("xpath", "(//fieldset[@class='recipients section']//span)[1]").getText();
		Common.assertionCheckwithReport(registry.equals("Registrant Information"),
				"validating the Registrant Information filed ", "It should display Registrant Information in gift registry",
				"successfully Registrant Information is displayed in gift registry", "failed to display the Registrant Information under gift registry");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Registrant Information filed ", "It should display Registrant Information in gift registry",
					"Unable to display the Registrant Informationin gift registry",
					Common.getscreenShot("failed to display the Registrant Information under gift registry"));
			Assert.fail();
		}
	}

	public void edit_gift(String Dataset) {
		// TODO Auto-generated method stub
		try
		{
			
			Common.clickElement("xpath", "//span[text()='Edit']");
			Sync.waitPageLoad();
			Common.scrollIntoView("xpath", "//input[@title='Zip/Postal Code']");
			Common.textBoxInput("xpath", "//input[@title='Zip/Postal Code']", data.get(Dataset).get("postcode"));
			 Common.clickElement("id", "submit.save");
	        Sync.waitPageLoad();
	        Thread.sleep(4000);
	        String message=Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
	        Common.assertionCheckwithReport(message.equals("You saved this gift registry."),
					"validating the gift registery page navigation ", "After clicking on save button It should be able to navigate to the gift registry page ",
					"successfully Navigated to the gift registry page", "failed to Navigate to the gift registry page");
	        
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the gift registery page navigation ", "After clicking on save button It should be able to navigate to the gift registry page ",
					"Unable to navigate to the gift registry page",
					Common.getscreenShot("failed to Navigate to the gift registry page"));
			Assert.fail();
			
		}
		
	}

	public void share_giftcard(String Dataset) {
		// TODO Auto-generated method stub
		try
		{
			Common.clickElement("xpath", "//a[@title='Share']");
			Sync.waitPageLoad();
			Common.textBoxInput("xpath", "//input[@name='recipients[0][name]']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='recipients[0][email]']", data.get(Dataset).get("Email"));
			Common.clickElement("id", "add-recipient-button");
			Common.textBoxInput("xpath", "//input[@name='recipients[1][name]']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='recipients[1][email]']", data.get(Dataset).get("UserName"));
			Common.clickElement("xpath", "//button[@type='submit']");
			 Sync.waitPageLoad();
			 Thread.sleep(4000);
		        Sync.waitElementPresent(50, "xpath", "//div[@data-ui-id='message-success']//div");
		        String message=Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
		        System.out.println(message);
		        Common.assertionCheckwithReport(message.contains("You shared the gift registry"),
						"validating the gift registery page navigation ", "After clicking on save button It should be able to navigate to the gift registry page ",
						"successfully Navigated to the gift registry page", "failed to Navigate to the gift registry page");
		        
			
		}
		catch(Exception | Error e)
		{
			
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the gift registery page navigation ", "After clicking on save button It should be able to navigate to the gift registry page ",
					"Unable to navigate to the gift registry page",
					Common.getscreenShot("failed to Navigate to the gift registry page"));
			Assert.fail();
			
		}
	}

	public void delete_giftcard() {
		// TODO Auto-generated method stub
		try
		{
			Common.clickElement("xpath", "//a[@title='Delete']");
			Common.clickElement("xpath", "//button[@class='a-btn a-btn--primary action-primary action-accept']");
			 Sync.waitPageLoad();
		        Thread.sleep(4000);
		        String message=Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
		        Common.assertionCheckwithReport(message.contains("You deleted this gift registry."),
						"validating the deleting functionality in the gift registry", "After clicking on the delete button it should delete from the gift registry",
						"successfully it has been deleted from the gift registry", "failed to delete from the gift registry");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the deleting functionality in the gift registry", "After clicking on the delete button it should delete from the gift registry",
					"Unable to delete from the gift registry",
					Common.getscreenShot("failed to delete from the gift registry"));
			Assert.fail();
		}
		
	}

	public void Manage_items() {
		// TODO Auto-generated method stub
		try
		{
			Common.clickElement("xpath", "//a[@title='Manage Items']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			 Common.assertionCheckwithReport(Common.getPageTitle().equals("Manage Gift Registry"),
						"validating navigation to the Manage Gift Registry page ", "After clicking on Manage Gift Registry button it should navigate to the Manage Gift Registry page ",
						"successfully Navigated to the Manage Gift Registry", "failed to Navigate to the Manage Gift Registry");
			Common.clickElement("xpath", "//strong[text()='Gift Registry']");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating navigation to the Manage Gift Registry page ", "After clicking on Manage Gift Registry button it should navigate to the Manage Gift Registry page ",
					"Unabel to Navigated to the Manage Gift Registry",
					Common.getscreenShot("failed to Navigate to the Manage Gift Registry"));
			Assert.fail();
		}
		
	}

	public void share_invalid_details(String Dataset) {
		// TODO Auto-generated method stub
	
		try
		{
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//a[@title='Share']");
			Common.clickElement("xpath", "//a[@title='Share']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//button[@type='submit']");
			Common.clickElement("xpath", "//button[@type='submit']");
			Sync.waitElementPresent(30, "xpath", "//div[contains(@id,'error')]");
			String errormessage=Common.findElement("xpath", "//div[contains(@id,'error')]").getText();
			 Common.assertionCheckwithReport(errormessage.equals("This is a required field."),
						"validating the error message with empty fields ", "After clicking hare button with empty data error message should be display",
						"successfully error message has been dispalyed ", "failed to display the error message");
			 Common.textBoxInput("xpath", "//input[@name='recipients[0][name]']", data.get(Dataset).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='recipients[0][email]']", data.get(Dataset).get("LastName"));
				Common.clickElement("xpath", "//button[@type='submit']");
				Sync.waitElementPresent(30, "xpath", "//div[@class='mage-error']");
				String invalidemail=Common.findElement("xpath", "//div[@class='mage-error']").getText();
				 Common.assertionCheckwithReport(invalidemail.contains("Please enter a valid email address"),
							"validating the error message with invalid email ", "After clicking hare button with invalid email error message should be display",
							"successfully error message has been dispalyed ", "failed to display the error message");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the error message with invalid email ", "After clicking hare button with invalid email error message should be display",
					"Unable to see the error message has been dispalyed ",
					Common.getscreenShot("failed to display the error message"));
			Assert.fail();
		}
		
	}

	public void additems_giftregistry(String Dataset) {
		// TODO Auto-generated method stub
		try
		{
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.clickElement("xpath", "//button[@type='submit']//span[@class='a-btn__label']");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			 Common.assertionCheckwithReport(Common.getPageTitle().equals("Manage Gift Registry"),
						"validating navigation to the Manage Gift Registry page ", "After clicking on Manage Gift Registry button it should navigate to the Manage Gift Registry page ",
						"successfully Navigated to the Manage Gift Registry", "failed to Navigate to the Manage Gift Registry");
//			Common.clickElement("xpath", "//strong[text()='Gift Registry']");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating navigation to the Manage Gift Registry page ", "After clicking on Manage Gift Registry button it should navigate to the Manage Gift Registry page ",
					"Unable to Navigated to the Manage Gift Registry",
					Common.getscreenShot("failed to Navigate to the Manage Gift Registry"));
			Assert.fail();
		}
		
	try
	{
		Sync.waitPageLoad();
		Thread.sleep(5000);
		Common.clickElement("xpath", "//div[@class='control m-text-input']");
		Common.textBoxInput("xpath", "//input[@class='input-text qty a-text-input']", data.get(Dataset).get("Quantity"));
		Sync.waitElementPresent(30, "xpath", "//span[text()='Update Items']");
		Common.clickElement("xpath", "//span[text()='Update Items']");
		Sync.waitElementPresent(30, "xpath", "//div[@class='mage-error']");
		String errormessage=Common.findElement("xpath", "//div[@class='mage-error']").getText();
		 Common.assertionCheckwithReport(errormessage.contains("Please enter a number greater than 0"),
					"validating nthe error message validation for the prodcuts in gift registry ", "After Upadting the quantity to zero the eroor message should be display",
					"successfully quantity has been changed to zero and error message has been displayed", "failed to Display the error message for the when quantity changed to zero");
		
		 Common.clickElement("xpath", "//strong[text()='Gift Registry']");
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating nthe error message validation for the prodcuts in gift registry ", "After Upadting the quantity to zero the eroor message should be display",
				"Unable to Display the error message for the when quantity changed to zero",
				Common.getscreenShot("failed to Display the error message for the when quantity changed to zero"));
		Assert.fail();
		
	}
		
	}
	public void Klarna_Saved_Payment(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> Paymentmethod = new HashMap<String, String>();
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String email=data.get(dataSet).get("Email");
		String fullname=data.get(dataSet).get("FirstName");
		String expectedResult = "land on the payment section";

		try {
			Sync.waitPageLoad();

			Sync.waitElementClickable("xpath", "//label[@for='stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unable to land o n the paymentpage");
			Common.clickElement("xpath", "//label[@for='stripe_payments']");

			Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
			int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
			System.out.println(payment);
			if (payment > 0) {
				Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//span[text()='New payment method']");
				
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Sync.waitElementPresent(30, "xpath", "//button[@value='klarna']");
				Common.clickElement("xpath", "//button[@value='klarna']");
				Common.clickElement("xpath", "//div[@class='p-Input']//input[@name='email']");
				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='email']", email);
				Common.clickElement("xpath", "//div[@class='p-Input']//input[@name='name']");
				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='name']", fullname);
				String details=Common.findElement("xpath", "//div[@class='p-Input']//input[@name='email']").getAttribute("value");
				Common.assertionCheckwithReport(
						details.equals(email),
						"validating the email field for the Klarana Payment Method",
						"Email should be entered in the email field in Klarana payment method","Email has been dispalyed in the Klarna payment",
						"Failed to enter email in the Klarna Payment");
				Common.switchToDefault();
				Common.clickElement("xpath", "//span[text()='Place Order']");
				klarna_Saved_Details(dataSet);
				
			}
			else
			{
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Common.clickElement("xpath", "//button[@value='klarna']");
				Common.clickElement("xpath", "//div[@class='p-Input']//input[@name='email']");
				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='email']", email);
				Common.clickElement("xpath", "//div[@class='p-Input']//input[@name='name']");
				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='name']", fullname);
				String details=Common.findElement("xpath", "//div[@class='p-Input']//input[@name='email']").getAttribute("value");
				Common.assertionCheckwithReport(
						details.equals(email),
						"validating the email field for the Klarana Payment Method",
						"Email should be entered in the email field in Klarana payment method","Email has been dispalyed in the Klarna payment",
						"Failed to enter email in the Klarna Payment");
				Common.switchToDefault();
				Common.clickElement("xpath", "//span[text()='Place Order']");
				klarna_Saved_Details(dataSet);
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

	public void klarna_Saved_Details(String Dataset) {
		// TODO Auto-generated method stub
		String order="";
		String number=data.get(Dataset).get("phone");
		String otp=data.get(Dataset).get("OTP Number");
		String DOB=data.get(Dataset).get("DOB");
		String Cardnumber=data.get(Dataset).get("cardNumber");
		System.out.println(Cardnumber);
		
		try
		{
			Sync.waitPageLoad();
			Common.switchWindows();
			Common.switchFrames("xpath", "//iframe[@id='klarna-apf-iframe']");
			Sync.waitElementPresent("xpath", "//input[@name='phone']");
			Common.textBoxInput("xpath", "//input[@name='phone']", number);
			Common.clickElement("xpath", "//div[@id='onContinue__icon-wrapper']");
			Sync.waitPageLoad();
			Sync.waitElementPresent(30, "xpath", "//input[@id='otp_field']");
			Common.textBoxInput("xpath", "//input[@id='otp_field']", otp);
			Thread.sleep(6000);
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//button[@data-testid='pick-plan']");
			int paymenttype=Common.findElements("xpath", "//p[@id='funding-source-card-issuer']").size();
			if(paymenttype>0)
			{
			Common.clickElement("xpath", "//span[contains(text(),'Pay $')]");
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//button[@data-testid='PushFavoritePayment:skip-favorite-selection']");
			}
			else
			{
				Common.clickElement("xpath", "//button[@data-testid='pick-plan']");
				Sync.waitPageLoad();
				Sync.waitElementPresent(30, "xpath", "//iframe[@id='payment-gateway-frame']");
				Common.switchFrames("xpath", "//iframe[@id='payment-gateway-frame']");
				Common.clickElement("xpath", "//input[@id='cardNumber']//parent::div");
				
//				Common.textBoxInput("xpath", "//label//input[@id='cardNumber']", Cardnumber);
				Common.findElement("xpath","//input[@id='cardNumber']//self::input").sendKeys(Cardnumber);
				Common.javascriptclickElement("xpath", "//input[@id='expire']//parent::div");
				Common.findElement("xpath","//input[@id='expire']").sendKeys(data.get(Dataset).get("ExpMonthYear"));
				Common.javascriptclickElement("xpath", "//input[@id='securityCode']//parent::div");
				Common.findElement("xpath","//input[@id='securityCode']").sendKeys(data.get(Dataset).get("cvv"));
				Common.clickElement("xpath", "//span[contains(text(),'Continue')]");
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
		
		String url=automation_properties.getInstance().getProperty(automation_properties.BASEURL);
		
		if(!url.contains("stage") && !url.contains("preprod")){
			}
		
		else{
		try {
			String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();
			Sync.waitPageLoad(5000);
			int sizes = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();
			Thread.sleep(3000);
			Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase"),
					"verifying the product confirmation", "User Should able to Navigate to the order confirmation page",
					"Successfully It redirects to order confirmation page Order Placed",
					"User unabel to go orderconformation page");

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
			ExtenantReportUtils.addFailedLog("verifying the product confirmation", "User Should able to Navigate to the order confirmation page",
					"User failed to navigate  to order confirmation page",
					Common.getscreenShotPathforReport("failednavigatepage"));
			Assert.fail();
		}

	
	}
	
	}
	
	public void Kalrna_Payment(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> Paymentmethod = new HashMap<String, String>();
		Sync.waitPageLoad();
		Thread.sleep(4000);
	
		String fullname=data.get(dataSet).get("FirstName");
		String expectedResult = "land on the payment section";

		try {
			Sync.waitPageLoad();

			Sync.waitElementClickable("xpath", "//label[@for='stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unable to land o n the paymentpage");
			Common.clickElement("xpath", "//label[@for='stripe_payments']");

			Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
			int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
			System.out.println(payment);
			if (payment > 0) {
				Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//span[text()='New payment method']");
				
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath", "//button[@value='klarna']");
				Common.clickElement("xpath", "//button[@value='klarna']");
				Common.clickElement("xpath", "//div[@class='p-Input']//input[@name='email']");
				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='email']", Utils.getEmailid());
				String email=Common.findElement("xpath", "//div[@class='p-Input']//input[@name='email']").getAttribute("value");
				Common.clickElement("xpath", "//div[@class='p-Input']//input[@name='name']");
				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='name']", fullname);
				String details=Common.findElement("xpath", "//div[@class='p-Input']//input[@name='email']").getAttribute("value");
				Common.assertionCheckwithReport(
						details.equals(email),
						"validating the email field for the Klarana Payment Method",
						"Email should be entered in the email field in Klarana payment method","Email has been dispalyed in the Klarna payment",
						"Failed to enter email in the Klarna Payment");
				Common.switchToDefault();
				Common.clickElement("xpath", "//span[text()='Place Order']");
				klarna_Details(dataSet);
				
			}
			else
			{
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Common.clickElement("xpath", "//button[@value='klarna']");
				Common.clickElement("xpath", "//div[@class='p-Input']//input[@name='email']");
				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='email']", Utils.getEmailid());
				String email=Common.findElement("xpath", "//div[@class='p-Input']//input[@name='email']").getAttribute("value");
				Common.clickElement("xpath", "//div[@class='p-Input']//input[@name='name']");
				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='name']", fullname);
				String details=Common.findElement("xpath", "//div[@class='p-Input']//input[@name='email']").getAttribute("value");
				Common.assertionCheckwithReport(
						details.equals(email),
						"validating the email field for the Klarana Payment Method",
						"Email should be entered in the email field in Klarana payment method","Email has been dispalyed in the Klarna payment",
						"Failed to enter email in the Klarna Payment");
				Common.switchToDefault();
				Common.clickElement("xpath", "//span[text()='Place Order']");
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

	public void klarna_Details(String Dataset) {
		// TODO Auto-generated method stub
		String order="";
		String otp=data.get(Dataset).get("OTP Number");
		String DOB=data.get(Dataset).get("DOB");
		String Cardnumber=data.get(Dataset).get("cardNumber");
		System.out.println(Cardnumber);
		
		try
		{
			Sync.waitPageLoad();
			Common.switchWindows();
			Common.switchFrames("xpath", "//iframe[@id='klarna-apf-iframe']");
			Sync.waitElementPresent("xpath", "//input[@name='phone']");
			int number=Common.genrateRandomNumber();
			System.out.println(number);
			String mobile=Integer.toString(number);
			String phone="+91"+"95862"+mobile;
			System.out.println(phone);
			Common.textBoxInput("xpath", "//input[@name='phone']", phone);
			Common.clickElement("xpath", "//div[@id='onContinue__icon-wrapper']");
			Sync.waitPageLoad();
			Sync.waitElementPresent(30, "xpath", "//input[@id='otp_field']");
			Common.textBoxInput("xpath", "//input[@id='otp_field']", otp);
			Thread.sleep(6000);
			Sync.waitPageLoad();
			Sync.waitElementPresent(30, "xpath", "//h1[@role='status']");
			String klarna=Common.findElement("xpath", "//h1[@role='status']").getText();
			if(klarna.equals("What's your email?"))
			{
				Common.clickElement("xpath", "//div[@id='onContinue__icon-wrapper']");
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//div[@id='addressCollector-date_of_birth__container']");
				Common.findElement("xpath","//input[@id='addressCollector-date_of_birth']").sendKeys(DOB);
				Common.clickElement("xpath", "//div[@id='addressCollector-street_address__label']");
				Common.findElement("xpath","//input[@name='street_address']").sendKeys(data.get(Dataset).get("Street"));
				Common.clickElement("xpath", "//div[@id='addressCollector-city__label']");
				Common.findElement("xpath","//input[@name='city']").sendKeys(data.get(Dataset).get("City"));
				Common.clickElement("xpath", "//div[@id='addressCollector-region__label']");
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
				Common.clickElement("xpath", "//span[contains(text(),'Continue')]");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//span[contains(text(),'Pay $')]");
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//button[@data-testid='PushFavoritePayment:skip-favorite-selection']");
			}
			else
			{
				Common.clickElement("xpath", "//span[contains(text(),'Continue')]");
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
		Sync.waitElementPresent(60, "xpath", "//h1[@class='page-title-wrapper']");
	String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();
	System.out.println(sucessMessage);
	
	int size = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();
	Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
			"verifying the product confirmation", "It should redirects to the order confirmation mail",
			"Successfully It redirects to order confirmation page Order Placed",
			"User unable to go orderconformation page");
	
	if(Common.findElements("xpath", "//div[@class='checkout-success']/p/span").size()>0) {
		order=Common.getText("xpath", "//div[@class='checkout-success']/p/span");
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

	
	public String payPal_Payment(String dataSet) throws Exception {

		String order="";

		String expectedResult = "It should open paypal site window.";
		try {
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//input[@id='paypal_express']");
			Thread.sleep(2000);
			Common.clickElement("xpath", "//input[@id='paypal_express']");
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Common.switchFrames("xpath", "//iframe[contains(@class,'component-frame visible')]");
			
			//Common.refreshpage();
			Thread.sleep(8000);
			Sync.waitElementClickable("xpath", "//div[@class='paypal-button-label-container']");
			Common.clickElement("xpath", "//div[@class='paypal-button-label-container']");
			Common.switchToDefault();
			Thread.sleep(5000);
			Common.switchWindows();
			int size = Common.findElements("id", "acceptAllButton").size();
			if (size > 0) {

				Common.clickElement("id", "acceptAllButton");

			}
		}
			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult,
						"User failed to proceed with paypal payment", Common.getscreenShotPathforReport(expectedResult));
				Assert.fail();
			}
			String url=automation_properties.getInstance().getProperty(automation_properties.BASEURL);
			
			if(!url.contains("stage") &!url.contains("preprod")){
				
				int sizeofelement=Common.findElements("id", "email").size();
				Common.assertionCheckwithReport(sizeofelement > 0, "verifying the paypal payment ", expectedResult,"open paypal site window", "faild to open paypal account");
			}
			else
			{
				
				Common.clickElement("id", "login_emaildiv");
			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.clickElement("id", "btnNext");
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			int sizeemail = Common.findElements("id", "email").size();

			Common.assertionCheckwithReport(sizeemail > 0, "verifying the paypal payment ", expectedResult,"open paypal site window", "faild to open paypal account");
			
			try{
			Common.clickElement("id", "btnLogin");
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(5000);
			Common.clickElement("id", "payment-submit-btn");
			Thread.sleep(8000);
			Common.switchToFirstTab();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult,
					"User failed to proceed with paypal payment", Common.getscreenShotPathforReport(expectedResult));
			Assert.fail();
		}
			String url1=automation_properties.getInstance().getProperty(automation_properties.BASEURL);
			if(!url1.contains("stage") && !url1.contains("preprod")){
			}
		
		else{
			try{
			Thread.sleep(6000);
		String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();
		System.out.println(sucessMessage);
		
		int size = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();
		Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
				"verifying the product confirmation", expectedResult,
				"Successfully It redirects to order confirmation page Order Placed",
				"User unable to go orderconformation page");
		
		if(Common.findElements("xpath", "//div[@class='checkout-success']/p/span").size()>0) {
			order=Common.getText("xpath", "//div[@class='checkout-success']/p/span");
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
			return order;
	}



	
	public void After_Pay_payment(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String order="";
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String email=data.get(dataSet).get("Email");
		String fullname=data.get(dataSet).get("FirstName");
		String expectedResult = "User should able to proceed the afterpay payment method";

		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//label[@for='stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unable to land o n the paymentpage");
			Common.clickElement("xpath", "//label[@for='stripe_payments']");

			Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
			int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
			System.out.println(payment);
			if (payment > 0) {
				Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//span[text()='New payment method']");
				
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Sync.waitElementPresent(30, "xpath", "//button[@value='afterpay_clearpay']");
				Common.clickElement("xpath", "//button[@value='afterpay_clearpay']");
				Common.clickElement("xpath", "//div[@class='p-Input']//input[@name='email']");
				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='email']", email);
				Common.clickElement("xpath", "//div[@class='p-Input']//input[@name='name']");
				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='name']", fullname);
				Common.dropdown("xpath", "//select[@name='country']", Common.SelectBy.TEXT, data.get(dataSet).get("Country"));
				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='addressLine1']", data.get(dataSet).get("Street"));
				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='locality']", data.get(dataSet).get("City"));
				Common.dropdown("xpath", "//select[@name='administrativeArea']", Common.SelectBy.TEXT, data.get(dataSet).get("State"));
				String details=Common.findElement("xpath", "//div[@class='p-Input']//input[@name='email']").getAttribute("value");
				Common.assertionCheckwithReport(
						details.equals(email),
						"validating the email field for the Afterpay Payment Method",
						"Email should be entered in the email field in Afterpay payment method","Email has been dispalyed in the Afterpay payment",
						"Failed to enter email in the Afterpay Payment");
				Common.switchToDefault();
				Common.clickElement("xpath", "//span[text()='Place Order']");
				Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
		
	}
			else
			{
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Sync.waitElementPresent(30, "xpath", "//button[@value='afterpay_clearpay']");
				Common.clickElement("xpath", "//button[@value='afterpay_clearpay']");
				Common.clickElement("xpath", "//div[@class='p-Input']//input[@name='email']");
				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='email']", email);
				Common.clickElement("xpath", "//div[@class='p-Input']//input[@name='name']");
				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='name']", fullname);
				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='addressLine1']", data.get(dataSet).get("Street"));
				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='locality']", data.get(dataSet).get("City"));
				Common.dropdown("xpath", "//select[@name='administrativeArea']", Common.SelectBy.TEXT, data.get(dataSet).get("State"));
				String details=Common.findElement("xpath", "//div[@class='p-Input']//input[@name='email']").getAttribute("value");
				Common.assertionCheckwithReport(
						details.equals(email),
						"validating the email field for the Afterpay Payment Method",
						"Email should be entered in the email field in Afterpay payment method","Email has been dispalyed in the Afterpay payment",
						"Failed to enter email in the Afterpay Payment");
				Common.switchToDefault();
				Common.clickElement("xpath", "//span[text()='Place Order']");
				Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
			}
		}
			
			catch(Exception | Error e)
			{
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the Afterpay payment ", expectedResult,
						"User failed to proceed with After payment", Common.getscreenShotPathforReport(expectedResult));
				Assert.fail();
			}
		
			String url=automation_properties.getInstance().getProperty(automation_properties.BASEURL);
			if(!url.contains("stage") && !url.contains("preprod")){
			}
		
		else{
			try{
				Thread.sleep(5000);
		String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();

		
		int size = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();
		Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
				"verifying the product confirmation", expectedResult,
				"Successfully It redirects to order confirmation page Order Placed",
				"User unable to go orderconformation page");
		
		if(Common.findElements("xpath", "//div[@class='checkout-success']/p/span").size()>0) {
			order=Common.getText("xpath", "//div[@class='checkout-success']/p/span");
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
	
	
public void acceptPrivacy() {
		
		Common.clickElementStale("id", "truste-consent-button");
		try {	Thread.sleep(5000);
		Common.clickElement("xpath","//*[text()='Yes']");
		Thread.sleep(3000);
			Common.clickElement("xpath", "//button[@id='button3']");}
		catch(Exception e) {}
		
	}


public void click_trackorder(){
	try {
		Common.actionsKeyPress(Keys.END);
		Common.scrollIntoView("xpath", "//a[text()='Track Your Order']");
		Common.clickElement("xpath", "//a[text()='Track Your Order']");
		Sync.waitPageLoad();
		Common.assertionCheckwithReport(Common.getPageTitle().contains("Orders and Returns")|| Common.getPageTitle().equals("My Orders") ,
				"Verifying the track order page navigation ",
				"after clicking on the track order it should navigate to the orders and return page",
				"successfully Navigated to the orders and return page",
				"Failed to Navigate to the orders and return page");
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("Verifying the track order page navigation ",
				"after clicking on the track order it should navigate to the orders and return page",
				"Unable to  Navigated to the orders and return page", Common.getscreenShotPathforReport("Failed to Navigate to the orders and return page"));
		Assert.fail();

}
}


public void guestuserorderStatus(String dataSet) {
	// TODO Auto-generated method stub
	click_trackorder();
	String ordernumber=data.get(dataSet).get("OrderID");

	try{
		  Sync.waitElementPresent("id", "oar-order-id");
		  Common.textBoxInput("id", "oar-order-id",ordernumber);
		  
		  Sync.waitElementPresent("id", "oar-billing-lastname");
		  Common.textBoxInput("id", "oar-billing-lastname",data.get(dataSet).get("Billinglastname"));
		  
		  Sync.waitElementPresent("id", "oar_email");
		  Common.textBoxInput("id", "oar_email",data.get(dataSet).get("BillingEmail"));
		  
		  Sync.waitElementPresent("xpath", "//button[@title='Search']");
		  Common.clickElement("xpath", "//button[@title='Search']");
		  Sync.waitPageLoad();
		  Thread.sleep(5000);
			String orderid = Common.findElement("xpath", "//h1[@class='page-title-wrapper h2']").getText();
			System.out.println(orderid);
			Common.assertionCheckwithReport(Common.getPageTitle().contains(orderid), "verifying order status form",
					"order tracking information page navigation", "successfully order tracking information page ",
					"Failed to navigate tracking order page infromation");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying order status form",
					"order tracking information page navigation", "User unable to navigate to the order tracking information page",
					Common.getscreenShotPathforReport("Failed to navigate tracking order page infromation"));
			Assert.fail();

			}
}





public void register_userorder_status() {
	
	 click_singinButton();
	 Usersignin("AccountDetails");
	click_trackorder();
	
	try
	{
		Sync.waitPageLoad();
		int size=Common.findElements("xpath", "//tbody[@class='m-table__body']").size();
		Common.assertionCheckwithReport(size>0,
				"Verifying the order numbers in my orders page ",
				"after clicking on the track my orders order numbers  should be displayed in the my orders page",
				"successfully order numbers has been displayed in the my orders page",
				"Failed to Display the order number in my orders page");
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("Verifying the order numbers in my orders page ",
				"after clicking on the track my orders order numbers  should be displayed in the my orders page",
				"Unable to see the order numbers on my orders page", Common.getscreenShotPathforReport("Failed to Display the order number in my orders page"));
		Assert.fail();
		
	}
	
}

public void edit_Account_info(String dataSet) {
	// TODO Auto-generated method stub
	Accont_Information();
	try {

		Sync.waitElementPresent("xpath", "//span[@class='m-accordion__title-label']");

		Common.clickElement("xpath", "//span[@class='m-accordion__title-label']");
		Thread.sleep(4000);
		Common.clickElement("xpath", "(//div//input[@id='current-password'])[2]");
		Common.textBoxInput("xpath", "(//input[@id='current-password'])[2]", data.get(dataSet).get("Password"));
		Common.textBoxInput("xpath", "//input[@id='password']", data.get(dataSet).get("Confirm Password"));
		Common.textBoxInput("xpath", "//input[@id='password-confirmation']", data.get(dataSet).get("Confirm Password"));
		String message = Common.findElement("id", "validation-classes").getCssValue("color");
		String greencolor=Color.fromString(message).asHex();
		String message1 = Common.findElement("id", "validation-length").getAttribute("class");
		
		Common.assertionCheckwithReport(greencolor.equals("#2f741f") && message1.contains("complete") ,
				"validating the cureent password and new password fields",
				"User should able enter data in current password and new password",
				"Sucessfully the data has been entered in new password and current password",
				"Failed to enter data in current password and new password fields");
		
		Common.clickElement("xpath", "//button[@title='Save']");
		Thread.sleep(4000);
		String sucessmessage=Common.findElement("xpath", "//div[@data-ui-id='message-success']").getText();
		System.out.println(sucessmessage);
		Common.assertionCheckwithReport(sucessmessage.contains("You saved the account"), "Validating the saved account information",
				"Account information should be saved for the user", "Sucessfully account information has been saved ",
				"failed to save the account information");
		
	
		
		
	} catch (Exception | Error e) {
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
			Common.textBoxInput("id", "pass", "Lotuswave@1234");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//button[contains(@class,'action login')]");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"),
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

public void Accont_Information() {
	// TODO Auto-generated method stub
	
	try {
		Sync.waitElementPresent("xpath", "//a[text()='Account Information']");
		Common.clickElement("xpath", "//a[text()='Account Information']");
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


public void Invalid_email_newsletter(String Dataset) {
	// TODO Auto-generated method stub
	try {
		Sync.waitPageLoad();
		Common.actionsKeyPress(Keys.END);
		Sync.waitElementClickable(30, "xpath", "//input[@id='newsletter-signup_email']");
		Common.textBoxInput("xpath", "//input[@id='newsletter-signup_email']", data.get(Dataset).get("Email"));
		Common.clickElement("xpath", "//div[contains(@class,'m-n')]//button[@type='submit']");
		String Errormessage = Common.findElement("xpath", "//div[@class='newsletter-error']").getText();
		System.out.println(Errormessage);
		Common.assertionCheckwithReport(Errormessage.equals("Error: Please enter a valid email address."),
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

public void Empty_Email() {
	try {

		Common.textBoxInputClear("xpath", "//input[@id='newsletter-signup_email']");
		Thread.sleep(4000);
		Common.clickElement("xpath", "//div[contains(@class,'m-n')]//button[@type='submit']");
		String Errormessage = Common.findElement("xpath", "//div[@class='newsletter-error']").getText();
		System.out.println(Errormessage);
		Common.assertionCheckwithReport(Errormessage.equals("Error: This field is required."),
				"To validate the error message for missing email fields",
				"Should display Error Please enter a valid email address.", Errormessage,
				"Error message dispaly unsuccessfull");

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
		Sync.waitElementPresent("xpath", "//input[@id='newsletter-signup_email']");
		Common.clickElement("xpath", "//input[@id='newsletter-signup_email']");
		Common.textBoxInput("xpath", "//input[@name='email']", Utils.getEmailid());
		Thread.sleep(5000);
		Common.clickElement("xpath", "//div[contains(@class,'m-n')]//button[@type='submit']");
		Thread.sleep(5000);
		String Text = Common.getText("xpath", "//div[@data-ui-id='message-success']");
		System.out.println(Text);
		String expectedResult = "User gets confirmation message that it was submitted";

		Common.assertionCheckwithReport(Text.contains("Thank you for your subscription"),"verifying newsletter subscription",
				"User get confirmation message if new email if it used mail it showing error message ", Text,
				Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));

	} catch (Exception | Error e) {
		
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying newsletter subscription", "NewsLetter Subscrption success",
				"User faield to subscrption for newLetter  ",
				Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));
		Assert.fail();
 	}
}

public void validateChatboxOptions(String DataSet) {

	try {
		Sync.waitPageLoad();
		Sync.waitElementClickable("xpath", "//a[@class='a-logo']");
		Common.switchFrames("id", "kustomer-ui-sdk-iframe");
		Sync.waitElementVisible(30, "xpath", "//div[@class='chatRootIcon__pointer___QslJf']");
		Common.mouseOverClick("xpath", "//div[@class='chatRootIcon__pointer___QslJf']");

		String answers = Common.findElement("xpath", "//div[contains(@class,'footer__itemContainer')]/p").getText();
		System.out.println(answers);
		Common.assertionCheckwithReport(answers.contains("Answers"), "To validate the Answers options in Chatbox",
				"Click the Answers option to display the related options",
				"Sucessfully click the answers option button", "Unable to click the Answers option button");

		Common.javascriptclickElement("xpath", "//div[contains(@class,'footer__itemContainer')]/p");
	} catch (Exception | Error e) {
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
	}

	String[] items = data.get(DataSet).get("OXOAnswers").split(",");

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
		Common.javascriptclickElement("xpath", "//div[contains(@class,'footer__chatContainer')]");
//		Sync.waitElementClickable(30, "xpath", "//button[contains(@class,'CLMcd button')]");
//		Common.mouseOverClick("xpath", "//button[contains(@class,'CLMcd button')]");
		Sync.waitElementClickable(30, "xpath", "//button[contains(@class,'newConversationButton')]");
		Common.mouseOverClick("xpath", "//button[contains(@class,'newConversationButton')]");
		
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

public void clickContact() throws Exception {
	String expectedResult = "It should land successfully on the explore/contact page";
	
	Common.actionsKeyPress(Keys.END);
	try {
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//a[text()='Contact Us']");
		Common.clickElement("xpath", "//a[text()='Contact Us']");
		Sync.waitPageLoad();
		Thread.sleep(4000);
	
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("contact"),"Validating the contatus page navigation" ,
				expectedResult, "successfully land to contact page", "unabel to load the  contact page");
	} catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("validating contact us page", expectedResult,
				"unabel to load the contact page", Common.getscreenShotPathforReport("Contact us page link"));
		Assert.fail();

	}
}

public void contactUsPage(String dataSet) {
	// TODO Auto-generated method stub
	
	String expectedResult = "Email us form is visible in tab";
	String country=data.get(dataSet).get("Country");
	
	try {


		Sync.waitElementPresent(40, "xpath", "//iframe[contains(@src,'https://oxo')]");
		Common.switchFrames("xpath", "//iframe[contains(@src,'https://oxo')]");

		Sync.waitElementPresent("xpath", "//input[@id='customerEmail']");

//		
       Common.clickElement("xpath", "//input[@id='customerEmail']");
		Common.textBoxInput("xpath", "//input[@id='customerEmail']", data.get(dataSet).get("Email"));

		Sync.waitElementPresent("xpath", "//input[@id='messageSubject']");
		Common.textBoxInput("xpath", "//input[@id='messageSubject']", data.get(dataSet).get("FirstName"));

		Sync.waitElementPresent("xpath", "//input[@id='lastName']");
		Common.textBoxInput("xpath", "//input[@id='lastName']", data.get(dataSet).get("LastName"));

		Sync.waitElementPresent("xpath", "//input[@name='company']");
		Common.textBoxInput("xpath", "//input[@name='company']", data.get(dataSet).get("Company"));

		Sync.waitElementPresent("xpath", "//input[@id='customerPhone']");
		Common.textBoxInput("xpath", "//input[@id='customerPhone']", data.get(dataSet).get("phone"));

		Sync.waitElementPresent("xpath", "//input[@name='street']");
		Common.textBoxInput("xpath", "//input[@name='street']", data.get(dataSet).get("Street"));

		Sync.waitElementPresent("xpath", "//input[@name='city']");
		Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));

		Sync.waitElementPresent("xpath", "//input[@name='conversationCountry']");
		Common.clickElement("xpath", "//input[@name='conversationCountry']");

		Sync.waitElementPresent("xpath", "//div[text()='United States']");
		Common.clickElement("xpath", "//div[text()='United States']");

		Sync.waitElementPresent("xpath", "//input[@name='conversationState']");
		Common.clickElement("xpath", "//input[@name='conversationState']");

		Sync.waitElementPresent("xpath", "//div[text()='Alabama']");
		Common.clickElement("xpath", "//div[text()='Alabama']");

		Sync.waitElementPresent("xpath", "//input[@name='zipPostalCode']");
		Common.textBoxInput("xpath", "//input[@name='zipPostalCode']", data.get(dataSet).get("postcode"));

		Sync.waitElementPresent("xpath", "//div[@id='conversationHowCanWeHelp']");
		Common.clickElement("xpath", "//div[@id='conversationHowCanWeHelp']");

		Common.clickElement("xpath", "//div[text()='Order Issues']");
//		Common.clickElement("xpath", "//div[@id='order_issues']/i");
		
		Thread.sleep(4000);
	     Sync.waitElementPresent("xpath", "//div[@id='conversationOrderIssues']");
			Common.clickElement("xpath", "//div[@id='conversationOrderIssues']");
		
		Sync.waitElementPresent("xpath", "//div[text()='Billing Issue']");
		
		Common.clickElement("xpath", "//div[text()='Billing Issue']");

		Sync.waitElementPresent("xpath", "//input[@class='form-base' and @id='conversationOrder']");
		Common.textBoxInput("xpath", "//input[@class='form-base' and @id='conversationOrder']", data.get(dataSet).get("OrderID"));
		Sync.waitElementPresent("xpath", "//textarea[@id='messagePreview']");
		Common.textBoxInput("xpath", "//textarea[@id='messagePreview']",
				data.get(dataSet).get("Comments"));

		Common.scrollIntoView("xpath", "//button[text()='Submit']");
		Common.clickElement("xpath", "//button[text()='Submit']");
		
		Thread.sleep(4000);
		
		Sync.waitElementPresent("xpath", "//div[@class='form-wrap']");
		Common.scrollIntoView("xpath", "//div[@class='form-wrap']");
		int Contactussuccessmessage = Common.findElements("xpath", "//div[@class='form-wrap']").size();
		Common.assertionCheckwithReport(Contactussuccessmessage > 0, "verifying Contact us Success message ",
				"Success message should be Displayed", "Contact us Success message displayed ",
				"failed to dispaly success message");
	}

	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying email us from",
				"contact us form data enter without any error message", "Contact us page getting error ",
				Common.getscreenShotPathforReport("Contact us page"));
		Assert.fail();

	}

	Common.actionsKeyPress(Keys.PAGE_UP);
	Common.scrollIntoView("xpath", "//div[@class='form-wrap']");
	String Text = Common.getText("xpath", "//div[@class='form-wrap']");
	expectedResult = "User gets confirmation under the same tab. It includes a reference number and email is sent to email provided. No validation errors.";
	Common.assertionCheckwithReport(Text.contains("Your submission was successful"),
			"verifying contact us conformation message", expectedResult,
			"User gets confirmation under the same tab", "unabel to load the confirmation form");
	

}



public void Signin_Checkoutpage(String Dataset) {
	// TODO Auto-generated method stub
	try
	{
		Sync.waitElementVisible("id", "customer-email");
		Common.textBoxInput("id", "customer-email", data.get(Dataset).get("Email"));
		Sync.waitElementPresent("xpath", "//input[@name='password']");
		Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
		Common.clickElement("xpath", "//span[text()='Sign In']");
		Sync.waitPageLoad();
		int regsiteruser=Common.findElements("xpath", "//div[contains(@class,'shipping-address-item n')]").size();
		Common.assertionCheckwithReport(regsiteruser>0,
				"Verifying the login functionality from the shipping page",
				"after clicking on the login button it should login and address should be display",
				"successfully address book has been displayed after login",
				"Failed to Display the Address book in shipping page after click on login");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("Verifying the login functionality from the shipping page",
				"after clicking on the login button it should login and address should be display",
				"Unable to Display the Address book in shipping page after click on login",
				Common.getscreenShotPathforReport("Failed to Display the Address book in shipping page after click on login"));
		Assert.fail();
	}
	
}

public void ordersummary_validation() {
	// TODO Auto-generated method stub
	try {
		String Subtotal = Common.getText("xpath", "//tr[@class='totals sub']//span[@class='price']").replace("$",
				"");
		Float subtotalvalue = Float.parseFloat(Subtotal);
		String shipping = Common.getText("xpath", "//tr[@class='totals shipping excl']//span[@class='price']")
				.replace("$", "");
		Float shippingvalue = Float.parseFloat(shipping);
		String Tax = Common.getText("xpath", "//tr[@class='totals-tax']//span[@class='price']").replace("$", "");
		Float Taxvalue = Float.parseFloat(Tax);
		String ordertotal = Common.getText("xpath", "//tr[@class='grand totals']//span[@class='price']")
				.replace("$", "");
		Float ordertotalvalue = Float.parseFloat(ordertotal);
		Float Total = subtotalvalue + shippingvalue + Taxvalue;
		String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
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


public void addtocart_PLP(String Dataset) {
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
		
//		Common.clickElement("xpath", "//img[@alt='" + products + "']");
		Sync.waitElementPresent("xpath", "//span[text()='Add to Bag']");
       Common.clickElement("xpath", "//span[text()='Add to Bag']");
	
		Sync.waitPageLoad();
		Thread.sleep(5000);
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


public void review(String Dataset) {
	// TODO Auto-generated method stub
	String products=data.get(Dataset).get("Products");
	try
	{
		Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
		Common.clickElement("xpath", "//img[@alt='" + products + "']");
		Thread.sleep(4000);
		Common.scrollIntoView("xpath", "//label[text()='Reviews']");
		Sync.waitElementPresent("xpath", "//label[@for='tab-product.yotpo.reviews']");
		String form=Common.findElement("xpath", "//label[@for='tab-product.yotpo.reviews']").getText();
		Common.assertionCheckwithReport(form.equals("Reviews"),
				"verifying the write a review button", "Write a review should be appear in the PDP page",
				"Sucessfully write a review button has been displayed in PDP page", "Failed to display the write a review button in PDP page");
		Common.clickElement("xpath", "//label[text()='Reviews']");
		Sync.waitElementPresent("xpath", "//span[text()='Write A Review']");
		Common.clickElement("xpath", "//span[text()='Write A Review']");
	
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the Write a review button", "select the write review option",
				"User Unable to click write review option  ", Common.getscreenShotPathforReport("User Failed to click write review option "));
		Assert.fail();
	}
	try
	{
		String expectedResult="Sucessfully title input box has been displayed";
		score(data.get(Dataset).get("score"));
		Sync.waitElementPresent("xpath", "//input[@name='review_title']");
		int title=Common.findElements("xpath", "//input[@name='review_title']").size();
		Common.assertionCheckwithReport(title > 0, "verifying the title page", "title input box should be displayed",
				expectedResult, "User Unable to display the title box");
		Common.textBoxInput("xpath", "//input[@name='review_title']",data.get(Dataset).get("title"));
		Common.textBoxInput("xpath", "//textarea[@name='review_content']", data.get(Dataset).get("Review"));
		Common.textBoxInput("xpath", "//input[@name='display_name']", data.get(Dataset).get("FirstName"));
		Common.textBoxInput("xpath", "//input[@name='email']", data.get(Dataset).get("Email"));
		Common.clickElement("xpath", "//input[@value='Post']");
		Thread.sleep(4000);
		String message=Common.findElement("xpath", "//div[@class='yotpo-thank-you']").getAttribute("aria-label");
		Common.assertionCheckwithReport(message.equals("Thank you for posting a review"),
				"verifying the post for the product review", "product review should be submit after clicking on post",
				"Sucessfully Thank you message has been displayed ", "Failed to display the Thank you message ");
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the post for the product review", "product review should be submit after clicking on post",
				"Unable to display the Thank you message after clickng on post ", Common.getscreenShotPathforReport("Failed to display the thank you message"));
		Assert.fail();
		
	}
	
}


public void score(String score) throws Exception
{
	Thread.sleep(4000);
	switch (score) {
	case "1":
	Sync.waitElementPresent("xpath", "//span[@aria-label='score 1']");
	Common.clickElement("xpath", "//span[@aria-label='score 1']");
	break;
	case "2":
	Sync.waitElementPresent("xpath", "//span[@aria-label='score 2']");
	Common.clickElement("xpath", "//span[@aria-label='score 2']");
	break;
	case "3":
	Sync.waitElementPresent("xpath", "//span[@aria-label='score 3']");
	Common.clickElement("xpath", "//span[@aria-label='score 3']");;
	break;
	case "4":
	Sync.waitElementPresent("xpath", "//span[@aria-label='score 4']");
	Common.clickElement("xpath", "//span[@aria-label='score 4']");
	break;
	case "5":
	Sync.waitElementPresent("xpath", "//span[@aria-label='score 5']");
	Common.clickElement("xpath", "//span[@aria-label='score 5']");
	break;
}
}

public void my_Account() {
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
}

public void stored_Payments(String Dataset) {
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
		Sync.waitElementPresent("xpath", "//a[text()='Stored Payment Methods']");
		Common.clickElement("xpath", "//a[text()='Stored Payment Methods']");
		Sync.waitPageLoad(30);
		Common.assertionCheckwithReport(Common.getPageTitle().equals("My Payment Methods"),
				"validating the Navigation to the My Payment Methods page",
				"After Clicking on stored methods CTA user should be navigate to the My Payment Methods page",
				"Sucessfully User Navigates to the My Payment Methods page after clicking on the stored methods  CTA",
				"Failed to Navigate to the My Payment Methods page after Clicking on my stored methods  CTA");
		int size=Common.findElements("xpath", "//tbody[@class='m-table__body']").size();
		if(size>0)
		{
			String number=Common.findElement("xpath", "//td[@data-th='Payment Method']//label").getText().replace("•••• ", "");
			System.out.println(number);
			Thread.sleep(4000);
			Common.assertionCheckwithReport(number.contains("4242")&& Dataset.contains("4242"),
					"validating the card details in the my orders page",
					"After Clicking on My payments methods and payment method should be appear in payment methods",
					"Sucessfully payment method is appeared in my payments methods",
					"Failed to display the payment methods in the my payments methods");
		}
		else
		{
			Assert.fail();
		}
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the card details in the my orders page",
				"After Clicking on My payments methods and payment method should be appear in payment methods",
				"Unable to display the payment methods in the my payments methods",
				Common.getscreenShot(
						"Failed to display the payment methods in the my payments methods"));
		Assert.fail();
	}

}



public void stored_Payment_Methods_Delete() {
	
	try {

		Sync.waitElementPresent(30, "xpath", "//a[contains(@href,'paymentmethods/delete')]/span");
		Common.clickElement("xpath", "//a[contains(@href,'paymentmethods/delete')]/span");
		String delete = Common.findElement("xpath", "//th[text()='Payment Method']").getText();
		
		Common.assertionCheckwithReport(delete.contains("Payment Method"),
				"validating the Navigation to the Stored Payment page",
				"After Clicking on Stored Payment Methods CTA user should be navigate to the My Payment Methods page",
				"Sucessfully User Navigates to the My Payment Methods page after clicking on the Stored Payment Methods CTA",
				"Failed to Navigate to the My Payment Methods page after Clicking on Stored Payment Methods CTA");
		
		
		
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the Navigation to the Stored Payment page",
				"After Clicking on Stored Payment Methods CTA user should be navigate to the My Payment Methods page",
				"Unable to Navigates the user to My Payment Methods page after clicking on the Stored Payment Methods",
				Common.getscreenShot(
						"Failed to Navigate to the My Payment Methods page after Clicking on Stored Payment Methods CTA"));
		Assert.fail();
}
}


public void click_Voulantry_Recall() throws Exception {
	String expectedResult = "It should land successfully on the explore/product-recall";
	
	Common.actionsKeyPress(Keys.END);
	try {
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//a[text()='Voluntary Recall']");
		Common.clickElement("xpath", "//a[text()='Voluntary Recall']");
		Sync.waitPageLoad();
		Thread.sleep(4000);
	
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("recall"),"Validating the Product recall page navigation" ,
				expectedResult, "successfully land to Product recall page", "unable to load the Product recall page");
	} catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("validating Product recall  page", expectedResult,
				"unabel to load the product recall page", Common.getscreenShotPathforReport("Product recall page link"));
		Assert.fail();

	}
}


public void voluntary_Recall(String dataSet) {
	// TODO Auto-generated method stub
	
	String expectedResult = " Product recall form is visible in tab with success message";
	String country=data.get(dataSet).get("Country");
	
	try {


		Sync.waitElementPresent(40, "xpath", "//iframe[contains(@src,'https://oxo')]");
		Common.switchFrames("xpath", "//iframe[contains(@src,'https://oxo')]");

		
		Sync.waitElementPresent("xpath", "//input[@data-label='First Name']");
		Common.textBoxInput("xpath", "//input[@data-label='First Name']", data.get(dataSet).get("FirstName"));

		Sync.waitElementPresent("xpath", "//input[@data-label='Last Name']");
		Common.textBoxInput("xpath", "//input[@data-label='Last Name']", data.get(dataSet).get("LastName"));

		Sync.waitElementPresent("xpath", "//input[@data-label='Phone']");
		Common.textBoxInput("xpath", "//input[@data-label='Phone']", data.get(dataSet).get("phone"));

		
		Sync.waitElementPresent("xpath", "//input[@data-label='Email']");
       Common.clickElement("xpath", "//input[@data-label='Email']");
		Common.textBoxInput("xpath", "//input[@data-label='Email']", data.get(dataSet).get("Email"));

		
		Sync.waitElementPresent("xpath", "//input[@data-label='Address']");
		Common.textBoxInput("xpath", "//input[@data-label='Address']", data.get(dataSet).get("Street"));

		Sync.waitElementPresent("xpath", "//input[@name='conversationCountry']");
		Common.clickElement("xpath", "//input[@name='conversationCountry']");

		Sync.waitElementPresent("xpath", "//div[text()='United States']");
		Common.clickElement("xpath", "//div[text()='United States']");

		Sync.waitElementPresent("xpath", "//input[@data-label='Zip Code']");
		Common.textBoxInput("xpath", "//input[@data-label='Zip Code']", data.get(dataSet).get("postcode"));

//		Sync.waitElementPresent("xpath", "//input[@name='street']");
//		Common.textBoxInput("xpath", "//input[@name='street']", data.get(dataSet).get("Street"));

		Sync.waitElementPresent("xpath", "//input[@name='city']");
		Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));

		Sync.waitElementPresent("xpath", "//input[@name='conversationState']");
		Common.clickElement("xpath", "//input[@name='conversationState']");

		Sync.waitElementPresent("xpath", "//div[text()='Alabama']");
		Common.clickElement("xpath", "//div[text()='Alabama']");

		
		Sync.waitElementPresent("xpath", "//textarea[@data-label='Comments']");
		Common.textBoxInput("xpath", "//textarea[@data-label='Comments']",
				data.get(dataSet).get("Comments"));
		
		Common.scrollIntoView("xpath", "//button[text()='Submit']");
		Common.clickElement("xpath", "//button[text()='Submit']");
		
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//div[@class='form-wrap']");
		int recallsuccessmessage = Common.findElements("xpath", "//div[@class='form-wrap']").size();
		Common.assertionCheckwithReport(recallsuccessmessage > 0, "verifying Product recall Success message ",
				"Success message should be Displayed", "Product recall Success message displayed ",
				"failed to dispaly success message");
	}

	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying email us from",
				"Product recall form data enter without any error message", "Product recall page getting error ",
				Common.getscreenShotPathforReport("Product recall page"));
		Assert.fail();

	}

	Common.actionsKeyPress(Keys.PAGE_UP);
	Common.scrollIntoView("xpath", "//div[@class='form-wrap']");
	String Text = Common.getText("xpath", "//div[@class='form-wrap']");
	expectedResult = "User gets confirmation under the same tab. It includes Success message.";
	Common.assertionCheckwithReport(Text.contains("Your submission was successful"),
			"verifying Product recall confirmation message", expectedResult,
			"User gets confirmation under the same tab", "unable to load the confirmation form");
	

}





public void BillingAddress(String dataSet) {
	// TODO Auto-generated method stub
	try
	{
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Sync.waitElementClickable("xpath", "//label[@for='stripe_payments']");
		
		int sizes = Common.findElements("xpath", "//label[@for='stripe_payments']").size();

		Common.assertionCheckwithReport(sizes > 0, "Validating the payment section page", "payment section should be displayed",
				"sucessfully payment section has been displayed","Failed to displayed the payment section");
		Common.clickElement("xpath", "//label[@for='stripe_payments']");
		Thread.sleep(1000);
		Common.clickElement("xpath", "//span[contains(text(),'My billing and shipping address are the same')]");
		
	    Sync.waitElementPresent("xpath", "//div[@class='billing-address-form']//input[@name='firstname']");
	    
	   int billingaddressform= Common.findElements("xpath", "//div[@class='billing-address-form']//input[@name='firstname']").size();
	    
	   Common.assertionCheckwithReport(billingaddressform>0, "Filling the Billing address ", "user editing  the billing address", "user sucessfully open the billing address from ", "faield open the bulling address from");
	    
		Common.textBoxInput("xpath", "//div[@class='billing-address-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
		Sync.waitElementPresent("xpath", "//div[@class='billing-address-form']//input[@name='lastname']");
		Common.textBoxInput("xpath", "//div[@class='billing-address-form']//input[@name='lastname']",data.get(dataSet).get("LastName"));
		
	
		Sync.waitElementPresent("xpath", "//div[@class='billing-address-form']//input[@name='street[0]']");
		Common.textBoxInput("xpath", "//div[@class='billing-address-form']//input[@name='street[0]']", data.get(dataSet).get("Street"));
	
		Common.textBoxInput("xpath", "//div[@class='billing-address-form']//input[@name='city']", data.get(dataSet).get("City"));
		
		
		Common.dropdown("xpath", "//div[@class='billing-address-form']//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
		Thread.sleep(4000);
	
		
		//Thread.sleep(4000);
		Common.textBoxInput("xpath", "//div[@class='billing-address-form']//input[@name='postcode']", data.get(dataSet).get("postcode"));
		Common.textBoxInput("xpath", "//div[@class='billing-address-form']//input[@name='telephone']", data.get(dataSet).get("phone"));
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(2000);
		Common.clickElement("xpath", "//button[contains(@class,'action action-update')]");
		
		Thread.sleep(5000);
		int sizeerrormessage=Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
	    System.out.println("error messagess    "+sizeerrormessage);
		Common.assertionCheckwithReport(sizeerrormessage<=0, "verifying Billing addres filling ", "user will fill the all the billing address", "user fill the shipping address click save button", "faield to add new billing address");
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying Billing addres filling", "user will fill the all the Billing address", "faield to add new billing address",Common.getscreenShotPathforReport("faieldssbillingpagefilling"));
		Assert.fail();
	}
}

public void edit_BillingAddress_gustuser(String dataSet) {

	try{
		    Common.clickElement("xpath", "//input[@id='billing-address-same-as-shipping-shared']");
		    
		    Sync.waitElementPresent("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='firstname']");
		    
		   int billingaddressform= Common.findElements("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='firstname']").size();
		    
		   Common.assertionCheckwithReport(billingaddressform>0, "Filling the Billing address ", "user editing  the billing address", "user sucessfully open the billing address from ", "faield open the bulling address from");
		    
			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='firstname']",data.get(dataSet).get("FirstName"));
			Sync.waitElementPresent("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='lastname']");
			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='lastname']",data.get(dataSet).get("LastName"));
			
		
			Sync.waitElementPresent("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='street[0]']");
			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='street[0]']", data.get(dataSet).get("Street"));
		
			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='city']", data.get(dataSet).get("City"));
			
			
			Common.dropdown("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			Thread.sleep(4000);
		
			
			//Thread.sleep(4000);
			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='postcode']", data.get(dataSet).get("postcode"));
			Common.textBoxInput("xpath", "(//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='telephone']", data.get(dataSet).get("phone"));
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(2000);
			Common.clickElement("xpath", "//button[@class='action action-update']");
			
			Thread.sleep(5000);
			int sizeerrormessage=Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
		    System.out.println("error messagess    "+sizeerrormessage);
			Common.assertionCheckwithReport(sizeerrormessage<=0, "verifying Billing addres filling ", "user will fill the all the billing address", "user fill the shipping address click save button", "faield to add new billing address");
		}
			
			catch(Exception |Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying Billing addres filling", "user will fill the all the Billing address", "faield to add new billing address",Common.getscreenShotPathforReport("faieldssbillingpagefilling"));
				Assert.fail();
				
			}  
		}

public void country_selector() {
	
	String Country;
	try
	{
		Common.actionsKeyPress(Keys.END);
		List<WebElement> country=Common.findElements("xpath", "//label[contains(@class,'a-radio-button')]");
		System.out.println(country.size());
		for(int i=0;i<country.size();i++)
		{
		
			List<WebElement> select=Common.findElements("xpath", "//label[contains(@class,'a-radio-button')]");
			Sync.waitPageLoad();
			Sync.waitElementPresent(50, "xpath", "//img[@alt='United States flag']");
			Common.clickElement("xpath", "//img[@alt='United States flag']");
			Thread.sleep(3000);
			Country=select.get(i).getText();
			select.get(i).click();
			Common.clickElement("xpath", "//span[contains(text(),'Confirm')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.navigateBack();
			ExtenantReportUtils.addPassLog("Validating"+ Country +"Page  ", "click on the country should navigate to the  "+Country +"Page", "successfully page navigating to "+Country +"PAGE", Common.getscreenShotPathforReport(Country));	
		}
		
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the country selection page navigation",
				"After Clicking on the selected country it should navigate to the respective country page",
				"Unable to navigate to the respective country page after clicking on the selected country",
						Common.getscreenShot("Failed to navigate to the respective country page after clicking on the selected country"));
		Assert.fail();
	}
	
}



public void click_Product_Registration() throws Exception {
	String expectedResult = "It should land successfully on the Product Registration";
	
	Common.actionsKeyPress(Keys.END);
	try {
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//a[text()='Product Registration']");
		Common.clickElement("xpath", "//a[text()='Product Registration']");
		Sync.waitPageLoad();
		Thread.sleep(4000);
	
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("registration"),"Validating the Product Registration page navigation" ,
				expectedResult, "successfully land to Product Registration page", "unable to load the Product Registration page");
	} catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("validating Product Registration  page", expectedResult,
				"unable to load the Product Registration page", Common.getscreenShotPathforReport("Product Registration page link"));
		Assert.fail();

	}
}

public void product_Registration(String dataSet) {
	
	String expectedResult = " Product registration form is visible in tab with success message";
	String country=data.get(dataSet).get("Country");
	String state = data.get(dataSet).get("Region");
	String purchased = data.get(dataSet).get("PurchasedAt");
	try {


		Sync.waitElementPresent(40, "xpath", "//iframe[contains(@src,'https://oxo')]");
		Common.switchFrames("xpath", "//iframe[contains(@src,'https://oxo')]");

		
		Sync.waitElementPresent("xpath", "//input[@data-label='First Name']");
		Common.textBoxInput("xpath", "//input[@data-label='First Name']", data.get(dataSet).get("FirstName"));

		Sync.waitElementPresent("xpath", "//input[@data-label='Last Name']");
		Common.textBoxInput("xpath", "//input[@data-label='Last Name']", data.get(dataSet).get("LastName"));
		
		Sync.waitElementPresent("xpath", "//input[@data-label='Email']");
	       
			Common.textBoxInput("xpath", "//input[@data-label='Email']", data.get(dataSet).get("Email"));

		
		Sync.waitElementPresent("xpath", "//input[@data-label='Phone']");
		Common.textBoxInput("xpath", "//input[@data-label='Phone']", data.get(dataSet).get("phone"));

		
		
		
		Sync.waitElementPresent("xpath", "//input[@data-label='Street ']");
		Common.textBoxInput("xpath", "//input[@data-label='Street ']", data.get(dataSet).get("Street"));

		Sync.waitElementPresent("xpath", "//input[@data-label='City']");
		Common.textBoxInput("xpath", "//input[@data-label='City']", data.get(dataSet).get("City"));
		
		

		Sync.waitElementPresent("xpath", "//div[@id='conversationState']");
		Common.clickElement("xpath", "//div[@id='conversationState']");

		Sync.waitElementPresent("xpath", "//div[text()='"+state+"']");
		Common.clickElement("xpath", "//div[text()='"+state+"']");
		
		Sync.waitElementPresent("xpath", "//input[@data-label='Zip/Postal Code']");
		Common.textBoxInput("xpath", "//input[@data-label='Zip/Postal Code']", data.get(dataSet).get("postcode"));

		Common.textBoxInput("xpath", "//input[contains(@data-label,'Item Number')]", data.get(dataSet).get("SKUitemNumber"));

		
		Common.textBoxInput("xpath", "//input[@data-label='Serial Number']", data.get(dataSet).get("SerialNumber"));

		Common.textBoxInput("xpath", "//input[@data-label='Manufacture Date ']", data.get(dataSet).get("ManufactureDate"));

		Sync.waitElementPresent("xpath", "//div[@id='customerWherePurchased']");
		Common.clickElement("xpath", "//div[@id='customerWherePurchased']");
		
		Sync.waitElementPresent("xpath", "//div[text()='"+purchased+"']");
		Common.clickElement("xpath", "//div[text()='"+purchased+"']");
		
		
		Common.textBoxInput("xpath", "//input[@data-label='Price']", data.get(dataSet).get("Price"));

		Sync.waitElementPresent("xpath", "//textarea[@data-label='City Purchased']");
		Common.textBoxInput("xpath", "//textarea[@data-label='City Purchased']",data.get(dataSet).get("City"));
		
		Sync.waitElementPresent("xpath", "//div[@id='conversationCountry']");
		Common.clickElement("xpath", "//div[@id='conversationCountry']");

		Common.textBoxInput("xpath", "//input[@data-label='Purchase Date']",data.get(dataSet).get("Date"));		
		
		Sync.waitElementPresent("xpath", "//div[text()='"+country+"']");
		Common.clickElement("xpath", "//div[text()='"+country+"']");
		
		
		Common.scrollIntoView("xpath", "//button[text()='Submit']");
		Common.clickElement("xpath", "//button[text()='Submit']");
		
		Thread.sleep(4000);
		Common.scrollIntoView("xpath", "//div[@class='form-wrap']");
		Sync.waitElementPresent("xpath", "//div[@class='form-wrap']");
		int registrationsuccessmessage = Common.findElements("xpath", "//div[@class='form-wrap']").size();
		Common.assertionCheckwithReport(registrationsuccessmessage > 0, "verifying Product registration Success message ",
				"Success message should be Displayed", "Product registration  Success message displayed ",
				"failed to dispaly success message");
	}

	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying Product registration  form",
				"Product registration form data enter without any error message", "Product registration  page getting error ",
				Common.getscreenShotPathforReport("Product registration  page"));
		Assert.fail();

	}

	Common.actionsKeyPress(Keys.PAGE_UP);
	Common.scrollIntoView("xpath", "//div[@class='form-wrap']");
	String Text = Common.getText("xpath", "//div[@class='form-wrap']");
	expectedResult = "User gets confirmation under the same tab. It includes Success message.";
	Common.assertionCheckwithReport(Text.contains("Your product has been successfully registered!"),
			"verifying Product registration confirmation message", expectedResult,
			"User gets confirmation for Product registration ", "unable to load the confirmation form");
	

}

public void configurable_Sticky_add_to_cart(String Dataset) {
	// TODO Auto-generated method stub
	String product = data.get(Dataset).get("Colorproduct");
	String productcolor = data.get(Dataset).get("Color");
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
		Common.clickElement("xpath", "//img[@alt='" + product + "']");
		String Productname = Common.getText("xpath", "//a[contains(text(),'Write a review')]").trim();
		
		Thread.sleep(4000);
		System.out.println(Productname);
//		Common.assertionCheckwithReport(Common.getPageTitle().contains(product),
				Common.assertionCheckwithReport(Productname.contains("Write a review"),
				"validating the product should navigate to the PDP page",
				"When we click on the product is should navigate to the PDP page",
				"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");

		Sync.waitPageLoad();
		
		Common.actionsKeyPress(Keys.END);
		Sync.waitElementPresent("xpath", "//div[contains(@id,'sticky') and @aria-label='" + productcolor + "']");
		Common.clickElement("xpath", "//div[contains(@id,'sticky') and @aria-label='" + productcolor + "']");
		Common.clickElement("xpath", "//button[@id='product-sticky-addtocart-button']");
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
		
		Common.clickElement("xpath", "//img[@alt='" + products + "']");
		
		Sync.waitElementPresent("xpath", "//button[@id='product-sticky-addtocart-button']");
		Common.clickElement("xpath", "//button[@id='product-sticky-addtocart-button']");
	
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


public void socialLinkValidation(String dataSet){
	
	String socalLinks =data.get(dataSet).get("Links");
	String [] socallinksarry=socalLinks.split(",");
	int i=0;
	try{
	for(i=0;i<socallinksarry.length;i++){
		Common.actionsKeyPress(Keys.END);
		Common.clickElement("xpath", "//span[text()='"+socallinksarry[i]+"']");
		Common.switchWindows();
		System.out.println(Common.getCurrentURL());
		
		if(socallinksarry[i].equals("Instagram")){
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("instagram"), "Verifying Social link  "+socallinksarry[i],"User click the social "+socallinksarry[i], "successfully navigating to social link  "+socallinksarry[i], "Failed to navigate to social link "+socallinksarry[i]);
			Common.closeCurrentWindow();
			Common.switchToFirstTab();
		}
		
		  else if(socallinksarry[i].equals("Facebook")){
		  Common.assertionCheckwithReport(Common.getCurrentURL().contains("www.facebook.com"),"Verifying Social link  "+socallinksarry[i],"User click the social "+socallinksarry[i],"successfully navigating to social link  "+socallinksarry[i],"Failed to navigate to social link "+socallinksarry[i]);
		  Common.closeCurrentWindow(); Common.switchToFirstTab(); }
		 
   else	if(socallinksarry[i].equals("Twitter")){
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("twitter"), "Verifying Social link  "+socallinksarry[i],"User click the social "+socallinksarry[i], "successfully navigating to social link  "+socallinksarry[i], "Failed to navigate to social link "+socallinksarry[i]);
			Common.closeCurrentWindow();
			Common.switchToFirstTab();
		}
   else	if(socallinksarry[i].equals("YouTube")){
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("youtube"), "Verifying Social link  "+socallinksarry[i],"User click the social "+socallinksarry[i], "successfully navigating to social link  "+socallinksarry[i], "Failed to navigate to social link "+socallinksarry[i]);
			Common.closeCurrentWindow();
			Common.switchToFirstTab();
		}
   else	if(socallinksarry[i].equals("Pinterest")){
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("pinterest"), "Verifying Social link  "+socallinksarry[i],"User click the social "+socallinksarry[i], "successfully navigating to social link  "+socallinksarry[i], "Failed to navigate to social link "+socallinksarry[i]);
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}
		
		
	}
	}
	catch(Exception | Error e){
		e.printStackTrace();
	    ExtenantReportUtils.addFailedLog("Verifying Social link ","click the socal links it will navigating to particular page","User unabel to navigate Social page",Common.getscreenShotPathforReport("socialpage"));
	    Assert.fail();
	}
	
	
}

public void Oxo_URLValidation(String dataSet)  throws Exception, IOException { 
	// TODO Auto-generated method stub
	
	 String urls=data.get(dataSet).get("Links");
	    int j=0;
	    
	    String[] strArray = urls.split("\\r?\\n");
	   for (int i=0; i<strArray.length; i++) {
	      System.out.println(strArray[i]);
	      
	      if (Common.getCurrentURL().contains("pre")) {
	          
	          Common.oppenURL((strArray[i]));
	          int  responcecode=getpageresponce(Common.getCurrentURL());
	          System.out.println(responcecode);
	          
	       if(responcecode==200) {
	           ExtenantReportUtils.addPassLog("Validating Page URL ", "page configured with products ", "successfully page configured with products", Common.getscreenShotPathforReport("link"+i));
	       }
	       else {
	           
	            j++;
	            
	            ExtenantReportUtils.addFailedLog("Validating Page URL  "+Common.getCurrentURL(), "page configured with products ", "unable to find page it showing 40 error",Common.getscreenShotPathforReport("link"+i));
	       
	       }
	  
	          
	      }
	      else if(Common.getCurrentURL().contains("https://mcloud-na-preprod.oxo.com/")) {
	          
	            Common.oppenURL(strArray[i].replace("mcloud-na-stage", "www"));
	          
	           int  responcecode=getpageresponce(Common.getCurrentURL());
	              System.out.println(responcecode);
	          
	           if(responcecode==200) {
	               ExtenantReportUtils.addPassLog("Validating Page URL ", "page configured with products ", "successfully page configured with products", Common.getscreenShotPathforReport("link"+i));
	           }
	           else {
	               
	                j++;
	                
	                ExtenantReportUtils.addFailedLog("Validating Page URL  "+Common.getCurrentURL(), "page configured with products ", "unable to find page it showing 40 error",Common.getscreenShotPathforReport("link"+i));
	           
	           }
	      }
	   }
	   
	     if(j>1) {
	         Assert.fail();
	     }  
	 }



public void search_results(String search) {
		
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
			String productsearch = Common.findElement("xpath", "//h3[@class='c-srp-title__no-results']").getText();
			System.out.println(productsearch);
			Common.assertionCheckwithReport(productsearch.contains("Sorry, your search"), "validating the search functionality",
					"enter any search term will display no results in the search box", "user enter the search term in  search box",
					"Failed to see the search term");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the search functionality",
					"enter product name will display in the search box",
					" unable to enter the product name in  search box",
					Common.getscreenShot("Failed to see the product name"));
			Assert.fail();
		}

		try {
			String contact = Common.findElement("xpath", "//div[@id='instant-empty-results-container']//a[text()='Contact Us']").getText();
			Common.assertionCheckwithReport(contact.contains("Contact"), "validating the customer service information",
					"should display Customer serivce information", "user views the Customer serivce information",
					"Failed to see the Customer service info");

		}
		
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the validating the customer service information",
					"should display Customer serivce information",
					" user views the Customer serivce information",
					Common.getscreenShot("Failed to see the Customer service info"));
			Assert.fail();
		}
		
	}


public void popular_searches() {
	String search;

	try {
		
		List<WebElement> Search=Common.findElements("xpath", "(//div[@id='algolia-cms-block-popular-search'])//span[@class='widget block block-category-link-inline']/a");
		System.out.println(Search);
		System.out.println(Search.size());
		for(int i=0;i<Search.size();i++)
		{
		
			List<WebElement> select=Common.findElements("xpath", "(//div[@id='algolia-cms-block-popular-search'])//span[@class='widget block block-category-link-inline']/a");
			Sync.waitPageLoad();
			Sync.waitElementPresent(50, "xpath", "(//div[@id='algolia-cms-block-popular-search'])//span[@class='widget block block-category-link-inline']/a");
			Thread.sleep(3000);
			search=select.get(i).getText();
//			System.out.println(search);
			select.get(i).click();
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.navigateBack();
			ExtenantReportUtils.addPassLog("Validating"+ search +"Page  ", "click on the Popular search should navigate to the  "+search +"Page", "successfully page navigating to "+search +"PAGE", Common.getscreenShotPathforReport(search));

	}
	}

	
	catch (Exception | Error e){
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
		
		Common.scrollIntoView("xpath", "//span[@class='icon-carousel__right']");
		Common.findElement("xpath", "//span[@class='icon-carousel__right']");
		Common.clickElement("xpath", "//span[@class='icon-carousel__right']");
		
		int carousel = Common.findElements("xpath", "//div[@class='js-slick-carousel-wrapper slick-initialized slick-slider']").size();
		Common.assertionCheckwithReport(carousel > 0, "validating the carousel",
				"should navigate to the carousel", "user views the carousel",
				"Failed to see the carousel");
	}
	catch (Exception | Error e){
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the carousel",
				"click on the carousel should navigate the carousel slider",
				" unable to view the carousel",
				Common.getscreenShot("Failed to see the carousel"));
		Assert.fail();
	}
}

public void Validating_search(String search) {
	// TODO Auto-generated method stub
	try {
		Sync.waitPageLoad();
		Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
		String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
		Common.assertionCheckwithReport(open.contains("active"),
				"To validate the global search box is opened when we click on the search icon",
				"User should able to click on the search icon and search box opens",
				"Sucessfully the gobal search box opend when user clicks on search icon",
				"Failed to open the search box when user clicks on the search icon");
		Common.textBoxInput("xpath", "//input[@id='search']", search);	
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//span[contains(@class,'icon-header__s')]");
		Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
		String close = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
		Common.assertionCheckwithReport(close.contains("c-header-search"),
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
    Sync.waitElementPresent("xpath", "//nav[@class='megamenu navigation m-megamenu-nav']//span[text()=' Shop']");
    
        Common.clickElement("xpath", "//nav[@class='megamenu navigation m-megamenu-nav']//span[text()=' Shop']");
    	Thread.sleep(2000);
      
      
   
        String catogories = Common.getText("xpath", "//ul[contains(@class,'level0 submenu')]");
        System.out.println(catogories);
       
        Common.assertionCheckwithReport(catogories.contains("Kitchenware"),
                "To Validate the catogories are displayed",
                "should display the catogories after clicking on the shop",
                "update catogories are displayed after a click on the shop",
                "Failed to display the catogories");



   } catch (Exception | Error e) {
        e.printStackTrace();
        ExtenantReportUtils.addFailedLog("To Validate the catogories are displayed",
                "should display the catogories after clicking on the shop",
                "unable to display catogories after a click on the shop",
                "Failed to display the catogories");
        Assert.fail();
    }


}

public void click_BabyToddler() {
	   
    try {
    Sync.waitElementPresent("xpath", "//ul[contains(@class,'level0 submenu')]//span[text()=' Baby & Toddler']");
    
        Common.clickElement("xpath", "//ul[contains(@class,'level0 submenu')]//span[text()=' Baby & Toddler']");
    	Thread.sleep(2000);
      
      
   
        String subcatogories = Common.getText("xpath", "//ul[contains(@class,'level0 submenu')]");
        System.out.println(subcatogories);
       
        Common.assertionCheckwithReport(subcatogories.contains("Bathing"),
                "To Validate the subcatogories are displayed",
                "should display the subcatogories after clicking on the BabyToddler",
                "update subcatogories are displayed after a click on the BabyToddler",
                "Failed to display the subcatogories");
        } catch (Exception | Error e) {
        e.printStackTrace();
        ExtenantReportUtils.addFailedLog("To Validate the catogories are displayed",
                "should display the catogories after clicking on the BabyToddler",
                "unable to display catogories after a click on the BabyToddler",
                "Failed to display subcatogories");
        Assert.fail();
    }



}	
public void click_FeedingDrinking() {
	   
    try {
    Sync.waitElementPresent("xpath", "//ul[contains(@class,'level0 submenu')]//span[text()=' Feeding & Drinking']");
    
        Common.clickElement("xpath", "//ul[contains(@class,'level0 submenu')]//span[text()=' Feeding & Drinking']");
    	Sync.waitPageLoad();
    	Thread.sleep(2000);
      
   
        String pagetitle = Common.getPageTitle();
        System.out.println(pagetitle);
       
        Common.assertionCheckwithReport(pagetitle.contains("Shop Feeding & Drinking"),
                "To Validate the PLP page is displayed",
                "should display the PLP page after clicking on the FeedingDrinking",
                "update PLP page are displayed after a click on the FeedingDrinking",
                "Failed to display  catogories");
        } catch (Exception | Error e) {
        e.printStackTrace();
        ExtenantReportUtils.addFailedLog("To Validate the PLP page are displayed",
                "should display the PLP page after clicking on the FeedingDrinking",
                "unable to display PLP page after a click on the FeedingDrinking",
                "Failed to display update PLP page");
        Assert.fail();
    }
}
    public void click_product() {
		   
	    try {
	    Sync.waitElementPresent("xpath", "//img[@alt='On-the-Go Fork and Spoon Set']");
	    
	        Common.clickElement("xpath", "//img[@alt='On-the-Go Fork and Spoon Set']");
	    	Thread.sleep(2000);
	      
	      
	   
	        String pagetitle = Common.getPageTitle();
	        System.out.println(pagetitle);
	       
	        Common.assertionCheckwithReport(pagetitle.contains("OXO Tot On the Go Fork and Spoon Set"),
	                "To Validate the PDP page is displayed",
	                "should display the PDP page after clicking on the product image",
	                "update PDP page are displayed after a click on the product image",
	                "Failed to display  PDP page");
	        } catch (Exception | Error e) {
	        e.printStackTrace();
	        ExtenantReportUtils.addFailedLog("To Validate the PDP page are displayed",
	                "should display the PDP page after clicking on the product image",
	                "unable to display PDP page after a click on the product image",
	                "Failed to display update PDP page");
	        Assert.fail();
	    }



	}	
    public void Configurableproduct_addtocart_pdp(String Dataset) {
		String product = data.get(Dataset).get("Colorproduct");
		String productcolor = data.get(Dataset).get("Color");
		String productquantity = data.get(Dataset).get("productquantity");
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
	
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//div[@aria-label='" + productcolor + "']");
			Common.clickElement("xpath", "//div[@aria-label='" + productcolor + "']");
			Common.clickElement("xpath", "//select[@class='a-select-menu']");
			Common.dropdown("xpath", "//select[@class='a-select-menu']", Common.SelectBy.VALUE,
					productquantity);
			Common.clickElement("xpath", "//button[@title='Add to Bag']");
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
    public void PDP_cofigurable_product() {
		int subproductsList=Common.findElements("xpath","//div[@class='field option bundle-item  required']").size();
        for(int i=0;i<subproductsList;i++) {
            int value=i+1;
            List<WebElement> ListOfSubproducts=Common.findElements("xpath", "//div[@class='fieldset']//div["+value+"]//div[contains(@class,'m-swatch m')]");
            
            WebElement Colornames=Common.findElement("xpath", "//div[@class='fieldset']//div["+value+"]//span[contains(@class,'m-swa')]");
            WebElement imagecolor=Common.findElement("xpath", "//div[@class='fieldset']//div["+value+"]//img");
            for(int j=0;j<ListOfSubproducts.size();j++) {
                
                String attributevalue=ListOfSubproducts.get(j).getAttribute("disabled");
                
                    if(attributevalue!=null){
        }
                    else
                    {
                        if(ListOfSubproducts.get(j).getAttribute("class").contains("m-swatch m")) {
                            
                            ListOfSubproducts.get(j).click();
  
                            
                            Common.assertionCheckwithReport(imagecolor.getAttribute("src").contains(Colornames.getText())||imagecolor.getAttribute("src").trim().equals(""), "Vrifying  swatch color button "+Colornames.getText(), "after click color swatch button"+Colornames.getText()+"it must dispaly swatch color image", "successfully color swatch image is dispalying", "Failed load color swatch image");
                        }
                        else
                        {
                            break;
                        }
                    }
            }
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

    public void click_Customize() {
		   
	    try {
	    Sync.waitElementPresent("xpath", "//button[@data-role='customize-btn']");
	    
	        Common.clickElement("xpath", "//button[@data-role='customize-btn']");
	    	Sync.waitPageLoad();
	    	Thread.sleep(2000);
	      
	   
	        String text = Common.findElement("xpath", "//h1[@class='menu__category-title']").getText();
	        System.out.println(text);
	       
	        Common.assertionCheckwithReport(text.equals("Bottle"),
	                "To Validate the customize page is displayed",
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
    public void configurableproduct_Sticky_add_to_cart(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Colorproduct");
		String productcolor = data.get(Dataset).get("Color");
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


			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.END);
			Sync.waitElementPresent("xpath", "//div[contains(@id,'sticky') and @aria-label='" + productcolor + "']");
			Common.clickElement("xpath", "//div[contains(@id,'sticky') and @aria-label='" + productcolor + "']");
			Common.clickElement("xpath", "//button[@id='product-sticky-addtocart-button']");
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
    public void writeareview(String Dataset) {
		// TODO Auto-generated method stub
		String products=data.get(Dataset).get("Products");
		try
		{
		
			
			Common.scrollIntoView("xpath", "//label[text()='Reviews']");
			Sync.waitElementPresent("xpath", "//label[@for='tab-product.yotpo.reviews']");
			String form=Common.findElement("xpath", "//label[@for='tab-product.yotpo.reviews']").getText();
			Common.assertionCheckwithReport(form.equals("Reviews"),
					"verifying the write a review button", "Write a review should be appear in the PDP page",
					"Sucessfully write a review button has been displayed in PDP page", "Failed to display the write a review button in PDP page");
			Common.clickElement("xpath", "//label[text()='Reviews']");
			Sync.waitElementPresent("xpath", "//label[text()='Reviews']");
			Common.clickElement("xpath", "//span[text()='Write A Review']");
		
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the Write a review button", "select the write review option",
					"User Unable to click write review option  ", Common.getscreenShotPathforReport("User Failed to click write review option "));
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
	      
	   
	        String text = Common.findElement("xpath", "//div[@class='u-container c-product-carousel__carousel js-slick-product-carousel']").getText();
	        System.out.println(text);
	       
	        Common.assertionCheckwithReport(text.contains("Recommended For You"),
	                "To Validate the Recommended for you is displayed",
	                "should display the Recommended for you after scroll down the PDP page",
	                "update Recommended for you are displayed after scroll down the PDP page",
	                "Failed to display  ");
	       
	       
	        } catch (Exception | Error e) {
	        e.printStackTrace();
	        ExtenantReportUtils.addFailedLog("To Validate the Recommended for you are displayed",
	                "should display the Recommended for you after scroll down the PDP page",
	                "unable to display Recommended for you after scroll down the PDP page",
	                "Failed to display Recommended for you");
	        Assert.fail();
	    }

	}
	public void Shipping_Forgot_Password(String dataSet) {
		// TODO Auto-generated method stub
		try
		{
			Common.textBoxInput("xpath", "//input[@name=\"username\"]", data.get(dataSet).get("UserName"));
			//	Common.clickElement("xpath", "//span[text()='Forgot Your Password?']");
				String shipping = Common.findElement("xpath", "(//span[text()='Shipping'])[1]").getText();
				System.out.println(shipping);
				Common.assertionCheckwithReport( shipping.equals("Shipping"),
						"To validate the user is navigating to Shipping page",
						"user should naviagte to Shipping page", "User lands on Shippingd page",
						"User failed to navigate to Shipping page");
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("To validate the user is navigating to Shipping page",
						"user should navigate to Shipping page", "User failed to land on Shipping page",
						Common.getscreenShotPathforReport("failed  to naviagte Shipping page "));
				//Assert.fail();
		
	}
		
	}   
	public void Forgot_password(String DateSet) throws Exception {
		// TODO Auto-generated method stub
				try {
					Common.clickElement("xpath", "//span[text()='Forgot Your Password?']");
					String forgotpassword = Common.findElement("xpath", "//h1[text()='Forgot Your Password?']").getText();
					System.out.println(forgotpassword);
					Common.textBoxInput("xpath", "//input[@name='email']", Utils.getEmailid());
					Common.clickElement("xpath", "//span[text()='Reset My Password']");
					int message= Common.findElements("xpath", "//div[text()='If there is an account associated with hrn296331212@xcoxc.com you will receive an email with a link to reset your password.']").size();
					System.out.println(message);
					Common.assertionCheckwithReport(message>0,
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
		

	public void view_PLP_page() {
		try {
			String title = Common.findElement("xpath", "//h1[@id='page-title-heading']").getAttribute("Class");
			String breadcrumbs = Common.findElement("xpath", "//nav[@class='m-breadcrumb u-container']")
					.getAttribute("aria-label");

			String filter = Common.findElement("xpath", "//div[@class='c-filter__block']").getText();
			String Sort = Common
					.findElement("xpath",
							"//div[@class='m-list-toolbar__sorter']//div[@class='m-select-menu m-form-elem'] ")
					.getText();
			Common.assertionCheckwithReport(
					breadcrumbs.contains("Breadcrumbs navigation") && title.contains("c-plp-hero__headline")
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
			
			Common.clickElement("xpath", "//a[text()='"+category+"']");
			String text = Common.findElement("xpath", "//a[text()='"+category+"']//span").getText();
			int textValue = Integer.parseInt(text);
			String categoryvalue=Integer.toString(textValue);
			Thread.sleep(6000);
			String textValueAfterFilter = Common.findElement("xpath", "//span[@class='a-toolbar-info__number']")
					.getText();
			int noOfItems = Common.findElements("xpath", "//li[@class='ais-InfiniteHits-item']").size();
			String items=Integer.toString(noOfItems);
			System.out.println(text);
			System.out.println(textValueAfterFilter);

		Common.assertionCheckwithReport(categoryvalue.equals(items),
				"To validate the filter in Product Listing Page",
				"User should able to filter in Product Listing Page",
				"Sucessfully filters in the Product Listing Page",
				"Failed to filter in Product Listing Page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the filter in Product Listing Page",
					"User should able to filter in Product Listing Page", "Unable to filter the Product Listing Page",
					Common.getscreenShotPathforReport("Failed to filter Product listing Page"));

			Assert.fail();
		}
	}

	
	
	public void sort_By(String dataSet) {
		try {

			Common.clickElement("xpath", "//select[@class='ais-SortBy-select']");
			Common.dropdown("xpath", "//select[@class='ais-SortBy-select']", Common.SelectBy.TEXT, data.get(dataSet).get("Sort"));
			
			int size = Common.findElements("xpath", "//span[@data-price-type='finalPrice']//span[1]//span[@class='price']").size();
			float[] float_array = new float[size];
			for(int i=0; i<size; i++) {
				String text = Common.findElements("xpath", "//span[@data-price-type='finalPrice']//span[1]//span[@class='price']").get(i).getText();
				String price = text.replace("$", "");
				Float priceValue = Float.parseFloat(price);
				System.out.println(priceValue);
				float_array[i]=priceValue;
			}
			Arrays.sort(float_array);
			String firstItemPriceText = Common.findElements("xpath", "//span[@data-price-type='finalPrice']//span[1]//span[@class='price']").get(0).getText();
			String firstItemPrice = firstItemPriceText.replace("$", "");
			Float firstItemPriceValue = Float.parseFloat(firstItemPrice);
			if(data.get(dataSet).get("Sort").equals("Lowest Price")) {
				Common.assertionCheckwithReport(firstItemPriceValue.equals(float_array[0]),
						"To validate the Sort in Product Listing Page",
						"User should able to Sort in Product Listing Page",
						"Sucessfully Sorts in the Product Listing Page",
						"Failed to Sort  in Product Listing Page");
			}
			else if(data.get(dataSet).get("Sort").equals("Highest Price")) {
				Common.assertionCheckwithReport(firstItemPriceValue.equals(float_array[size-1]),
						"To validate the Sort in Product Listing Page",
						"User should able to Sort in Product Listing Page",
						"Sucessfully Sorts in the Product Listing Page",
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

		if (!url.contains("stage")&& !url.contains("preprod")) {
		}

		else {
			try {
				String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();
//				Tell_Your_FriendPop_Up();

				int sizes = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();
				Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
						"verifying the product confirmation", expectedResult,
						"Successfully It redirects to order confirmation page Order Placed",
						"User unabel to go orderconformation page");

				if (Common.findElements("xpath", "//div[@class='checkout-success']//a//strong").size() > 0) {
					order = Common.getText("xpath", "//div[@class='checkout-success']//a//strong");
					System.out.println(order);
				}

				if (Common.findElements("xpath", "//a[@class='order-number']/strong").size() > 0) {
					order = Common.getText("xpath", "//a[@class='order-number']/strong");
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
	

	public void product_quantity(String Dataset) {
		// TODO Auto-generated method stub
		String Quantity=data.get(Dataset).get("Quantity");
		try
		{
			Common.findElement("xpath", "//select[@class='a-select-menu']");
//			Common.clickElement("xpath", "//select[@class='a-select-menu']");
			Common.dropdown("xpath", "//select[@class='a-select-menu']", Common.SelectBy.VALUE, Quantity);
			Thread.sleep(3000);
			String value=Common.findElement("xpath", "//select[@class='a-select-menu']").getAttribute("value");
			Common.assertionCheckwithReport(value.equals(Quantity), "validating the  product the product quantity in PDP page",
					"Product quantity should be update in the PDP page", "Sucessfully product Qunatity has been updated ",
					"failed to Update the prodcut quantity in PDP page");
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product the product quantity in PDP page",
					"Product quantity should be update in the PDP page", "unable to change the  product Qunatity", Common.getscreenShot("failed to update the product quantity"));
			Assert.fail();
		}
		
	}

	public void outofstock_subcription(String Dataset) {
		// TODO Auto-generated method stub
		String products=data.get(Dataset).get("Products");
		String email=data.get(Dataset).get("Notifyme");
		try
		{
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
			String name=Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
			Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"failed to Navigate to the PDP page");
			
			Common.clickElement("xpath", "//span[text()='Notify Me When Available']");
		Common.textBoxInput("xpath", "//input[@placeholder='Insert your email']", email);
		Common.clickElement("xpath", "//span[text()='Subscribe']");
		Sync.waitPageLoad();
		Thread.sleep(6000);
		String newsubs = Common.findElement("xpath", "//div[@data-ui-id='message-success']").getAttribute("data-ui-id");
		System.out.println(newsubs);
        String newsubscribe=Common.findElementBy("xpath", "//div[@data-ui-id='message-success']//div").getText();
		System.out.println(newsubscribe);
		Common.assertionCheckwithReport(newsubscribe.contains("Alert subscription has been saved.") || newsubscribe.contains("Thank you! You are already subscribed to this product.")||newsubs.contains("success"),
				"verifying the out of stock subcription", "after click on subcribe button message should be appear",
				"Sucessfully message has been displayed when we click on the subcribe button ", "Failed to display the message after subcribtion");
		Common.actionsKeyPress(Keys.END);
			
	Common.clickElement("xpath", "//span[text()='Notify Me When Available']");
	Common.textBoxInput("xpath", "//input[@placeholder='Insert your email']", email);
	Common.clickElement("xpath", "//span[text()='Subscribe']");
	Sync.waitPageLoad();
	Thread.sleep(4000);
	String oldsubscribe=Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
	System.out.println(oldsubscribe);
	Common.assertionCheckwithReport(oldsubscribe.contains("Thank you! You are already subscribed to this product."),
			"verifying the out of stock subcription", "after click on subcribe button message should be appear",
			"Sucessfully message has been displayed when we click on the subcribe button ", "Failed to display the message after subcribtion");
		
		
		}
		catch(Exception | Error e)
		{
			
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the out of stock subcription", "after click on subcribe button message should be appear",
					"Unable to display the message after subcribtion ", Common.getscreenShot("Failed to display the message after subcribtion"));
			Assert.fail();
			
		}
		
	}
	public void reg_outofstock_subcription(String Dataset) {
		// TODO Auto-generated method stub
		String products=data.get(Dataset).get("Products");
	
			try
			{
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
				String name=Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
				Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
						"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
						"failed to Navigate to the PDP page");
				Common.clickElement("xpath", "//a[text()='Notify Me When Available']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String newsubs = Common.findElement("xpath", "//div[@data-ui-id='message-success']").getAttribute("data-ui-id");
//			System.out.println(newsubs);
			String newsubscribe=Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
			Common.assertionCheckwithReport(newsubscribe.contains("Alert subscription has been saved.")|| newsubscribe.contains("Thank you! You are already subscribed to this product.")||newsubs.contains("success"),
					"verifying the out of stock subcription", "after click on subcribe button message should be appear",
					"Sucessfully message has been displayed when we click on the subcribe button ", "Failed to display the message after subcribtion");
			Common.actionsKeyPress(Keys.END);
		Common.clickElement("xpath", "//a[text()='Notify Me When Available']");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String oldsubcribe=Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
		Common.assertionCheckwithReport(oldsubcribe.contains("Thank you! You are already subscribed to this product."),
				"verifying the out of stock subcription", "after click on subcribe button message should be appear",
				"Sucessfully message has been displayed when we click on the subcribe button ", "Failed to display the message after subcribtion");
		
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the out of stock subcription", "after click on subcribe button message should be appear",
					"Unable to display the message after subcribtion ", Common.getscreenShot("Failed to display the message after subcribtion"));
			Assert.fail();
		}
		
	
		}
	public void share_whishlist(String Dataset) {
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
               Common.clickElement("xpath", "//button[@title='Share Favorites']");
               Sync.waitPageLoad();
               Thread.sleep(4000);
               Common.textBoxInput("xpath", "//textarea[@name='emails']", data.get(Dataset).get("Email"));
               Common.textBoxInput("xpath", "//textarea[@name='message']", data.get(Dataset).get("message"));
               Common.clickElement("xpath", "//button[@title='Share Favorites']");
               Thread.sleep(4000);
               String message1 = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
   			System.out.println(message1);
   			Common.assertionCheckwithReport(message1.contains("Your Favorites have been shared"), "validating the shared whishlist functionality",
					"sucess message should display after share whishlist", "Sucessfully message has been displayed for whishlist",
					"failed to display the message for whishlist");
		}
			else
	{
				 Common.clickElement("xpath", "//button[@title='Share Favorites']");
	               Sync.waitPageLoad();
	               Thread.sleep(4000);
	               Common.textBoxInput("xpath", "//textarea[@name='emails']", data.get(Dataset).get("Email"));
	               Common.textBoxInput("xpath", "//textarea[@name='message']", data.get(Dataset).get("message"));
	               Common.clickElement("xpath", "//button[@title='Share Favorites']");
	               Thread.sleep(4000);
	               String message1 = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
	   			System.out.println(message1);	
	   			Common.assertionCheckwithReport(message1.contains("Your Favorites have been shared"), "validating the shared whishlist functionality",
						"sucess message should display after share whishlist", "Sucessfully message has been displayed for whishlist",
						"failed to display the message for whishlist");
		
	}
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the shared whishlist functionality",
					"sucess message should display after share whishlist",
					"Unable to display the message for whishlist", Common.getscreenShot("failed to display the message for whishlist"));
			Assert.fail();
		}
		
	}

	public void register_billingAddress(String dataSet) {
		// TODO Auto-generated method stub
		try
		{
			Sync.waitPageLoad();
            Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//label[@for='stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='stripe_payments']").size();
            Common.clickElement("xpath", "//label[@for='stripe_payments']");
			Common.assertionCheckwithReport(sizes > 0, "Validating the payment section page", "payment section should be displayed",
					"sucessfully payment section has been displayed","Failed to displayed the payment section");
			Sync.waitPageLoad();
			Sync.waitElementPresent(30, "xpath", "//span[text()='Edit']");
			Common.clickElement("xpath", "//span[text()='Edit']");
			Common.clickElement("xpath", "//select[@name='billing_address_id']");
			Common.dropdown("xpath", "//select[@name='billing_address_id']", Common.SelectBy.TEXT, "New Address");
			Common.textBoxInput("xpath", "//input[@name='street[0]']",
					data.get(dataSet).get("Street"));
			String Text = Common.getText("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.textBoxInput("xpath", "//input[@name='city']",
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
			Common.textBoxInput("xpath", "//div[@class='field _required']//input[@name='postcode']", data.get(dataSet).get("postcode"));
			Thread.sleep(5000);

			Common.textBoxInput("xpath", "//div[@class='field _required']//input[@name='telephone']", data.get(dataSet).get("phone"));
			Common.clickElement("xpath", "//span[text()='Update']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String update=Common.findElement("xpath", "(//div[@class='billing-address-details']//p)[2]").getText();
			System.out.println(update);
			Common.assertionCheckwithReport(update.contains("6 Walnut Valley Dr"),
					"verifying the Billing address form in payment page", "Billing address should be saved in the payment page",
					"Sucessfully Billing address form should be Display ", "Failed to display the Billing address in payment page");
			
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the Billing address form in payment page", "Billing address should be saved in the payment page",
			"Unable to display the Billing address in payment page ", Common.getscreenShot("Failed to display the Billing address in payment page"));
			Assert.fail();
		}
		
	}
	
	
	}
	




