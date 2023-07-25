package TestComponent.Osprey_EMEA;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.Utils;

public class GoldOspreyRMAHelper {
	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public GoldOspreyRMAHelper(String datafile, String sheetname) {

		excelData = new ExcelReader(datafile, sheetname);
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("Osprey_EMEA");
			report.createTestcase("Osprey_EMEATestCases");
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
		// TODO Auto-generated method stub
		try
		{
			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "//a[@aria-label='Home page link']").size();
			Common.assertionCheckwithReport(
					size > 0 && Common.getPageTitle().contains("Home page"),
					"validating store logo on the homwpage", "System directs the user to the Homepage and store logo should display",
					"Sucessfully user navigates to the home page and logo has been displayed", "Failed to navigate to the homepage and logo is not displayed");	
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating store logo on the homwpage", "System directs the user to the Homepage and store logo should display",
					"Unable to navigate to the homepage and logo is not displayed", "Failed to navigate to the homepage and logo is not displayed");
			
			Assert.fail();
		}
		
	}

	public void Create_Account(String Dataset) {
		// TODO Auto-generated method stub
		try
		{
			Sync.waitElementPresent(30, "xpath", "//button[@aria-controls='desktop-account-nav']");
			Common.clickElement("xpath", "//button[@aria-controls='desktop-account-nav']");
			Common.clickElement("xpath", "//li[@class='nav item']//a[text()='Create an Account']");
			Sync.waitImplicit(30);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Create New Customer Account"),
					"validating navigation to the create new account page",
					"User should navigate to the create account page",
					"Sucessfully user navigates to the Create account page",
					"Failed to navigate to the Create account page");
			Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(Dataset).get("LastName"));
			Common.textBoxInput("xpath", "//input[@name='email']", Utils.getEmailid());
			Common.textBoxInput("xpath", "//input[@name='password']",data.get(Dataset).get("Password"));
			Common.textBoxInput("xpath", "//input[@name='password_confirmation']",data.get(Dataset).get("Confirm Password"));
			Thread.sleep(4000);
			Common.clickElement("xpath", "//span[text()='Sign Up']");
			Sync.waitImplicit(30);
			Thread.sleep(4000);
			String message=Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			Common.assertionCheckwithReport(message.contains("Thank you for registering with Osprey UK Store.")&&Common.getPageTitle().contains("My Account"),
					"validating navigation to the account page after clicking on sign up button",
					"User should navigate to the My account page after clicking on the Signup",
					"Sucessfully user navigates to the My account page after clickng on thr signup button",
					"Failed to navigate to the My account page after clicking on the signup button");
		
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating navigation to the account page after clicking on sign up button",
					"User should navigate to the My account page after clicking on the Signup",
					"Unable to navigate to the My account page after clicking on the signup button",
					"Failed to navigate to the My account page after clicking on the signup button");
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
			Thread.sleep(4000);
			Common.assertionCheckwithReport(open.contains("active"), "User searches using the search field",
					"User should able to click on the search button", "Search expands to the full page",
					"Sucessfully search bar should be expand");
			Common.textBoxInput("xpath", "//input[@id='autocomplete-0-input']", data.get(Dataset).get("Products"));
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String productsearch = Common.findElement("xpath", "//span[@id='algolia-srp-title']").getText();
			System.out.println(productsearch);
			Common.assertionCheckwithReport(productsearch.contains(product), "validating the search functionality",
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

	public void addtocart(String Dataset) {
		// TODO Auto-generated method stub
		
		String products = data.get(Dataset).get("Products");
		String productcolor = data.get(Dataset).get("Color");
		String Productsize=data.get(Dataset).get("Size");
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
			Sync.waitPageLoad(30);
			Thread.sleep(6000);
			Sync.waitElementPresent("xpath", "//img[@alt='" + products + "']");
			Common.clickElement("xpath", "//img[@alt='" + products + "']");
			Sync.waitElementPresent("xpath", "//div[@data-option-label='"+ productcolor +"']");
			Common.clickElement("xpath", "//div[@data-option-label='" +productcolor+"']");
			Sync.waitElementPresent("xpath", "//div[@data-option-label='"+ Productsize +"']");
			Common.clickElement("xpath", "//div[@data-option-label='" +Productsize+"']");
			Sync.waitPageLoad(30);
			Thread.sleep(6000);
			Sync.waitElementVisible(30, "xpath", "//div[@class='m-product-overview__info-top']//h1");
			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
			Common.assertionCheckwithReport(name.contains(products) || Common.getPageTitle().contains(products), "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"failed to Navigate to the PDP page");
			product_quantity(Dataset);
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//span[text()='Add to Cart']");
			Common.clickElement("xpath", "//span[text()='Add to Cart']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//div[@data-ui-id='message-success']");
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

	private void product_quantity(String Dataset) {
		// TODO Auto-generated method stub
		String Quantity = data.get(Dataset).get("Quantity");
		try {
			Common.findElement("xpath", "//select[@class='a-select-menu']");
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

	public void minicart_Checkout() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
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
	public void click_minicart() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(4000);
			Common.actionsKeyPress(Keys.PAGE_UP);
			Sync.waitElementPresent("xpath", "//a[contains(@class,'c-mini')]");
			Common.clickElement("xpath", "//a[contains(@class,'c-mini')]");
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

	
		
	

	public void selectshippingmethod(String Dataset) {
		// TODO Auto-generated method stub
		 String method = data.get(Dataset).get("methods");
		 System.out.println(method);

		try {
			Thread.sleep(3000);
			int size = Common.findElements("xpath", "//label[@class='a-radio-button__label']").size();
			System.out.println(size);
			if (size > 0) {
//				Sync.waitElementPresent(30, "xpath", "//td[contains(text(),'" + method + "')]");
				Common.clickElement("xpath", "//td[contains(text(),'" + method + "')]");
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

	public void clickSubmitbutton_Shippingpage() {
		// TODO Auto-generated method stub
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

	public void updatePaymentAndSubmitOrder(String DataSet) throws Exception {
		// TODO Auto-generated method stub
		String order = "";
		addPaymentDetails(DataSet);
		String expectedResult = "It redirects to order confirmation page";

		if (Common.findElements("xpath", "//div[@class='message message-error']").size() > 0) {
			Thread.sleep(4000);
			addPaymentDetails(DataSet);
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

//				Tell_Your_FriendPop_Up();
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
		//return order;
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
//			Common.clickElement("xpath", "//label[@for='stripe_payments']");
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
				System.out.println(Number);

				Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

				Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {

					Common.clickElement("xpath", "//span[text()='Place Order']");
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

					Common.clickElement("xpath", "//span[text()='Place Order']");
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
		String errorTexts = Common.findElement("xpath", "//div[contains(@class,'error')]").getText();

		Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data",
				expectedResult, "Filled the Card detiles", "missing field data it showinng error");

		return Number;
	}

	public void Admin_signin(String DataSet) throws Exception {
		// TODO Auto-generated method stub
		Thread.sleep(4000);
		
		Common.openNewTab();
		Common.oppenURL("https://mcloud-emea-staging.hele.digital/ospreyeuadmin/sales/order/index/key/6ceb8706dd5d43dfab93bff12766511e5edb2458deaa706924f9b15431925a18/");
		Sync.waitPageLoad();
		try {
			//Common.oppenURL("https://mcloud-emea-staging.hele.digital/ospreyeuadmin/sales/order/index/key/6ceb8706dd5d43dfab93bff12766511e5edb2458deaa706924f9b15431925a18/");
	        if (Common.getCurrentURL().contains("emea")) {
	            Sync.waitElementClickable("xpath", "//a[@class='action login primary']");
	            Common.javascriptclickElement("xpath", "//a[@class='action login primary']");
	        } 
	        Sync.waitPageLoad(30);
	        Sync.waitElementPresent("name", "loginfmt");
	        Common.textBoxInput("name", "loginfmt", data.get(DataSet).get("UserName"));
	        Common.clickElement("id", "idSIButton9");
	        Sync.waitPageLoad();
	        Thread.sleep(3000);
	        Sync.waitElementPresent(30, "name", "passwd");
	        Common.textBoxInput("name", "passwd", data.get(DataSet).get("Password"));
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

	public void click_Sales() {
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


public void addDeliveryAddress(String DataSet) throws Exception {
	String address = data.get(DataSet).get("Street");

	
	try {
		
		Common.clickElement("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
		Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']", address);
		Sync.waitPageLoad();
		Thread.sleep(5000);
		Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='city']").clear();
		Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
				data.get(DataSet).get("City"));
		System.out.println(data.get(DataSet).get("City"));

		
		Thread.sleep(3000);
		
		try {
			Common.scrollIntoView("xpath", "//input[@placeholder='State/Province']");
			Common.textBoxInput("xpath", "//input[@placeholder='State/Province']",
					data.get(DataSet).get("Region"));
		} catch (ElementClickInterceptedException e) {
			Thread.sleep(3000);
			Common.textBoxInput("xpath", "//input[@placeholder='State/Province']",
					data.get(DataSet).get("Region"));
		}
		Thread.sleep(3000);
		
		Common.clickElement("xpath", "(//input[@name='postcode'])[2]");
		Common.textBoxInput("xpath", "(//input[@name='postcode'])[2]", data.get(DataSet).get("postcode"));
		Thread.sleep(5000);

		Common.clickElement("xpath", "(//input[@name='telephone'])[2]");
		Common.textBoxInput("xpath", "(//input[@name='telephone'])[2]", data.get(DataSet).get("phone"));

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



public String Verify_order() throws Exception {
	// TODO Auto-generated method stub
	String Orderid="";
    Thread.sleep(5000);
   
    String expectedResult = "It redirects to order confirmation page";
    try{
    Sync.waitPageLoad();
    Thread.sleep(5000);
   
   
    
   
    String sucessMessage=Common.getText("xpath", "//h1[@class='page-title-wrapper']");
    System.out.println(sucessMessage);
    //String Orderid="";
    int size=Common.findElements("xpath", "(//a[@class='action print'])").size();
    if(size>0) {
    	
    Orderid=Common.getText("xpath", "(//div[@class='checkout-success']//p//strong)[1]");
    }
    else{
    Orderid=Common.getText("xpath", "((//div[@class='column main'])//strong)[1]");
    }
  
    
	System.out.println(Orderid);
    System.out.println("Your order number is:  "+Orderid);
    Common.assertionCheckwithReport(sucessMessage.equals("Thank you for your purchase!"),"verifying the product confirmation", expectedResult,"Successfully It redirects to order confirmation page Order Placed","User unabel to go orderconformation page");
       
    }
    catch (Exception | Error e) {
        e.printStackTrace();
        ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
                "User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));
        Assert.fail();
        
    }
   
return Orderid;
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
				"New Invoice page is displayed after a click on the Invoic button", "Failed to display New Invoice page");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To Validate the New Invoice page",
				"should display the New Invoice page after clicking on the Invoice",
				"unable to display New Invoice page after a click on the Invoice button",
				"Failed to display New Invoice page");
		Assert.fail();
	}

}

public void Invoice_Payment(String DataSet) {
	// TODO Auto-generated method stub
	try {
		Thread.sleep(2000);
        Common.textBoxInput("xpath", "//textarea[@id='invoice_comment_text']", data.get(DataSet).get("InvoiceComments"));

		Common.clickElement("xpath", "//button[@title='Submit Invoice']");
		String InvoiceMessage = Common.findElement("xpath", "//div[text()='The invoice has been created.']").getText();
		
	
	    Common.assertionCheckwithReport(
				InvoiceMessage.contains("The invoice has been created."),
				"To Validate the Invoice payment",
				"should display the Order details page after clicking on the Submit Invoice",
				"Order details page is displayed after a click on the Submit Invoic button", "Failed to display Order details page");

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
				"New Shipment page is displayed after a click on the Ship button", "Failed to display New Shipment page");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To Validate the New Shipment page",
				"should display the New Shipment page after clicking on the Ship",
				"unable to display New Shipment page after a click on the Ship button",
				"Failed to display New Shipment page");
		Assert.fail();
	}

}

public void Ship_Payment(String DataSet) {
	// TODO Auto-generated method stub
	try {
		Thread.sleep(2000);
        Common.textBoxInput("xpath", "//textarea[@id='shipment_comment_text']", data.get(DataSet).get("ShipmentComments"));

		Common.clickElement("xpath", "//button[@title='Submit Shipment']");

		
		String ShipmentsMessage = Common.findElement("xpath", "//div[text()='The shipment has been created.']").getText();
		
		
	    Common.assertionCheckwithReport(
				ShipmentsMessage.contains("The shipment has been created."),
				"To Validate the Shipments payment",
				"should display the Order details page after clicking on the Submit Ship",
				"Order details page is displayed after a click on the Submit Ship button", "Failed to display Order details page");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To Validate the Shipment Payment",
				"should display the Order details page after clicking on the Submit Shipment",
				"unable to display Order details page after a click on the Submit Shipment button",
				"Failed to display Order details page");
		Assert.fail();
	}

}

public void Navigate_Order_Details_Page(String order) {
	// TODO Auto-generated method stub
	try {
		Thread.sleep(180000);
		Common.findElement("xpath", "//li[@id='menu-magento-sales-sales']").click();

		Thread.sleep(5000);

		Common.clickElement("xpath", "(//span[text()='Orders'])[1]");
		Sync.waitPageLoad();
		Thread.sleep(2000);
		Common.textBoxInput("xpath", "(//input[@id='fulltext'])[1]", order);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.ENTER);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.findElement("xpath", "(//div[text()='"+order+"'])");
		Thread.sleep(4000);
        Common.mouseOverClick("xpath", "(//div[text()='"+order+"'])");
        Sync.waitPageLoad();
		Common.assertionCheckwithReport(Common.getPageTitle().contains(order),
				"Validating Order page in admin", "User must land on Order details page in admin",
				"user sucessfully navigating to Order details page ", "fail to navigate to order details page");
      
	} catch (Exception | Error e) {
        e.printStackTrace();
       ExtenantReportUtils.addFailedLog("Validating Order page in admin","User must land on Order details page in admin","fail to navigate to order details page");
        Assert.fail();
    }
	
}

public void RMA() {
	// TODO Auto-generated method stub
	try {
		Thread.sleep(2000);
		Sync.waitElementPresent("xpath", "//button[@id='order_review']//span[text()='RMA']");
		Common.clickElement("xpath", "//button[@id='order_review']//span[text()='RMA']");
		
		Common.assertionCheckwithReport(
				Common.getPageTitle().contains("New RMA / RMA / Sales / Magento Admin"),
				"To Validate the New RMA page",
				"should display the New RMA page page after clicking on the RMA",
				"New RMA page is displayed after a click on the RMA button", "Failed to display New RMA page");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To Validate the New RMA page",
				"should display the New RMA page after clicking on the RMA",
				"unable to display New RMA page after a click on the RMA button",
				"Failed to display New RMA page");
		Assert.fail();
	}
	
	
}

public void NEWRMA(String DataSet) throws Exception {
	// TODO Auto-generated method stub
	
	try {
		Thread.sleep(2000);
		
		Sync.waitElementPresent("xpath", "//select[@id='status_id']");
		Common.dropdown("xpath", "//select[@id='status_id']", Common.SelectBy.TEXT,
				data.get(DataSet).get("Status"));
		Common.clickElement("xpath", "//input[@name='items[1][qty_requested]']");
		Common.textBoxInput("xpath", "//input[@name='items[1][qty_requested]']", data.get(DataSet).get("Quantity"));

		
		Sync.waitElementPresent("xpath", "//select[@name='items[1][reason_id]']");
		Common.dropdown("xpath", "//select[@name='items[1][reason_id]']", Common.SelectBy.TEXT,
				data.get(DataSet).get("ReasontoReturn"));
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//select[@name='items[1][condition_id]']");
		Common.dropdown("xpath", "//select[@name='items[1][condition_id]']", Common.SelectBy.TEXT,
				data.get(DataSet).get("Itemcondition"));
		
		Sync.waitElementPresent("xpath", "//select[@name='items[1][resolution_id]']");
		Common.dropdown("xpath", "//select[@name='items[1][resolution_id]']", Common.SelectBy.TEXT,
				data.get(DataSet).get("Resolution"));
		
		
		Common.assertionCheckwithReport(
				Common.getPageTitle().contains("New RMA / RMA / Sales / Magento Admin"),
				"To Validate the New RMA page",
				"should display the New RMA page page after clicking on the RMA",
				"New RMA page is displayed after a click on the RMA button", "Failed to display New RMA page");

			
			
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To Validate the New RMA page",
				"should display the New RMA page after clicking on the RMA",
				"unable to display New RMA page after a click on the RMA button",
				"Failed to display New RMA page");
		Assert.fail();
	}
	
	
}
public void Click_Save() {
	// TODO Auto-generated method stub
	try {
		
		Sync.waitElementPresent("xpath", "//button[@id='update-split-button-button']");
		Common.clickElement("xpath","//button[@id='update-split-button-button']");
		String message = Common.findElement("xpath", "//div[@data-ui-id='messages-message-success']").getText();
		System.out.println(message);

		Common.assertionCheckwithReport(
				message.contains("RMA was successfully saved"),
				"Validating  RMA Success message",
				"after clicking save  it will navigate RMA page with Success message",
				"Unable to  navigate to the  RMA Page ", "Failed to display New RMA page");
		} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("Validating  RMA Success message",
				"after clicking save  it will navigate RMA page with Success message",
				"Unable to  navigate to the  RMA Page  ",
				Common.getscreenShotPathforReport("Failed to navigate to the  RMA Page with Success message"));
		Assert.fail();
	}

	
}

public void My_Account() {
	// TODO Auto-generated method stub
	  Common.switchToFirstTab();   
	try {
		Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
		Sync.waitElementPresent("xpath", "  //div[@class=\"m-account-nav__content\"]//a[text()='My Account']");
		Common.clickElement("xpath", "  //div[@class=\"m-account-nav__content\"]//a[text()='My Account']");
		Common.assertionCheckwithReport(Common.getPageTitle().equals("My Account"),
				"validating the Navigation to the My Account page",
				"After Clicking on My Account CTA user should be navigate to the My Account page",
				"Sucessfully User Navigates to the My Account page after clicking on the My Account CTA",
				"Failed to Navigate to the My Account page after Clicking onMy Account button");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the Navigation to the My Account page",
				"After Clicking on My Account CTA user should be navigate to the My Account page",
				"Unable to Navigates the user to My Account page after clicking on the My Account CTA",
				Common.getscreenShot(
						"Failed to Navigate to the My Account page after Clicking on My Account CTA"));
		Assert.fail();
	}

}

public void click_Spares_Repairs_Reservoirs() {
	// TODO Auto-generated method stub
	try {
		Common.clickElement("xpath", "//a[text()='Spares, Repairs & Reservoirs']");
		
	    
	
		Common.assertionCheckwithReport(Common.getPageTitle().contains("My Returns"),
				"validating the Navigation to the SPARES_REPAIRS_RESERVOIRS page",
				"After Clicking on SPARES_REPAIRS_RESERVOIRS CTA user should be navigate to the SPARES_REPAIRS_RESERVOIRS page",
				"Sucessfully User Navigates to the SPARES_REPAIRS_RESERVOIRS page after clicking on the SPARES_REPAIRS_RESERVOIRS CTA",
				"Failed to Navigate to the SPARES_REPAIRS_RESERVOIRS page after Clicking on SPARES_REPAIRS_RESERVOIRS button");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the Navigation to the SPARES_REPAIRS_RESERVOIRS page",
				"After Clicking on SPARES_REPAIRS_RESERVOIRS CTA user should be navigate to the SPARES_REPAIRS_RESERVOIRS page",
				"Unable to Navigates the user to SPARES_REPAIRS_RESERVOIRS page after clicking on the SPARES_REPAIRS_RESERVOIRS CTA",
				Common.getscreenShot(
						"Failed to Navigate to the SPARES_REPAIRS_RESERVOIRS page after Clicking on SPARES_REPAIRS_RESERVOIRS CTA"));
		Assert.fail();
	}
	
}

public void View_Details() {
	// TODO Auto-generated method stub
	
	String Print_OrderNumber = null;
	try
	{
		
	
		List<WebElement> RMA_Status= Common.findElements("xpath", "//span[text()='Approved']");
		
		
		if (!RMA_Status.isEmpty()) {
		   
		    for (WebElement element : RMA_Status) {
		        String statusText = element.getText();
		        
		        System.out.println("Status:"  +statusText);
		        // Check if the text is equal to "Approved"
		        if (statusText.equals("Approved")) {
		      
		            Common.clickElement("xpath", "//a[text()='View']");
		            break; 
		        }
		        else {
		        	System.out.println("No Approved Status Found");
		        }
		    }
		
		
		
	String ProductName = Common.findElementBy("xpath", "//td[@data-th='Product Name']").getText();
	String ProductQTY = Common.findElementBy("xpath", "//td[@data-th='QTY']").getText();
	String OrderNo = Common.findElementBy("xpath", "//td[@data-th='Order']").getText();
	
	System.out.println(ProductName);
	System.out.println(ProductQTY);
	System.out.println(OrderNo);
   
	Common.clickElement("xpath", "(//a[text()='RMA Packing Slip'])[1]");
	Thread.sleep(4000);
//	Need to write code to close the Pop-up
   
    String Print_ProductName = Common.findElementBy("xpath", "//td[@data-th='Product Name']").getText();
	String Print_ProductQTY = Common.findElementBy("xpath", "//td[@data-th='Qty']").getText();
	
	String Print_OrderNo = Common.findElementBy("xpath", "(//p[@class='order-date'])[2]").getText();
	Pattern pattern = Pattern.compile("#(\\d+)");
     Matcher matcher = pattern.matcher(Print_OrderNo);
     
     
   if (matcher.find()) {
         // Extract the order number from the first match
          Print_OrderNumber = matcher.group(1);
         System.out.println("Order Number: " + Print_OrderNumber);
     } else {
         System.out.println("Order number not found.");
     }
	
	System.out.println(Print_ProductName);
	System.out.println(Print_ProductQTY);
	System.out.println(Print_OrderNumber);
	
	Common.assertionCheckwithReport(ProductName.equals(Print_ProductName) && ProductQTY.equals(Print_ProductQTY) && OrderNo.equals(Print_OrderNumber) , "validating the user navigated to the my Return page",
			"After clicking on RMA Packin slip it should navigate to RMA Print page", "Sucessfully Navigated to the RMA Print page",
			"failed to Navigated to the RMA Print page");
	
	
	} else {
		
		String No_returns = Common.findElement("xpath", "//span[text()='There are no returns']").getText();
		System.out.println(No_returns);
		Common.assertionCheckwithReport(No_returns.contains("There are no returns"),
				"validating the My Return Page",
				"There are no returns should display after Clicking  my Returns",
				"Sucessfully There re no returns displayed in  My returns page",
				"failed to display There are no returns ");
	}
	}
	
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the user navigated to RMA Print page",
				"After clicking on my account it should navigate to RMA Print page", "Unable to Navigated to RMA Print page", Common.getscreenShot("Failed to Navigated to RMA Print page"));
		
	
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

public void enter_orderNumber(String DataSet) {
	// TODO Auto-generated method stub
	try {
		Thread.sleep(3000);
		Common.clickElement("xpath",
				"//div[@class='admin__current-filters-actions-wrap']//button[text()='Clear all']");
		Thread.sleep(5000);
		Common.textBoxInput("xpath", "(//input[text()='Search by keyword'])[1]", DataSet);
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

			Click_View_order(DataSet);

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
	
}
	


	





	


	

	
	

	

	


		
	

		
	

		
		
	


		
	

		
	


