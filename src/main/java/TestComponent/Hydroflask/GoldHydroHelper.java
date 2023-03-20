package TestComponent.Hydroflask;

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

public class GoldHydroHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public GoldHydroHelper(String datafile, String sheetname) {

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
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			Common.assertionCheckwithReport(
					size > 0 && Common.getPageTitle().contains("Home Page")
							|| Common.getPageTitle().contains("Hydro Flask"),
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

	public void bottles_headerlinks(String category) {
		// TODO Auto-generated method stub
		String expectedResult = "User should click the" + category;
		try {

			Sync.waitElementPresent("xpath", "//a[contains(@class,'level-top')]//span[text()=' Shop']");
			Thread.sleep(3000);
//			Common.scrollIntoView("xpath","//a[contains(@class,'level-top')]//span[text()=' Shop']");
			Common.clickElement("xpath", "//a[contains(@class,'level-top')]//span[text()=' Shop']");

			Thread.sleep(3000);

			try {
				Common.mouseOver("xpath", "//span[contains(text(),'" + category + "')]");
			} catch (Exception e) {
				Common.clickElement("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");
			}
			Common.clickElement("xpath", "//span[contains(text(),'" + category + "')]");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//span[text()=' Bottles']");
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
			Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"failed to Navigate to the PDP page");
			product_quantity(Dataset);
			Thread.sleep(4000);
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
			Common.actionsKeyPress(Keys.UP);
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
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));
			int size = Common.findElements("xpath", "//input[@type='email']").size();
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
			Common.textBoxInputClear("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
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

	public void addDeliveryAddress_Guestuser(String dataSet) throws Exception {
		String address = data.get(dataSet).get("Street");

		try {
			Thread.sleep(5000);
			if (Common.getCurrentURL().contains("preprod")) {
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
//		Common.actionsKeyPress(Keys.BACK_SPACE);
//		Common.actionsKeyPress(Keys.BACK_SPACE);
//		Thread.sleep(5000);
//		Common.findElement("xpath","//form[@id='co-shipping-form']//input[@name='street[0]']").sendKeys("Rd");
//		Thread.sleep(5000);
//		Sync.waitElementPresent(30, "xpath", "//div[@class='pcaitem']");
//		String newadd=Common.findElement("xpath", "//div[@class='pcaitem']").getAttribute("title");
//		System.out.println(newadd);
//		Thread.sleep(6000);
//		List<WebElement> listofaddresss=Common.findElements("xpath", "(//div[@class='pcaitem'])[1]");
//		ArrayList<WebElement> listaddress=new ArrayList<WebElement>();
//		for(WebElement addresscheck:listofaddresss)
//		{
//			
//	     listaddress.add(addresscheck);
//		 String add=addresscheck.getAttribute("title"); 
//		 System.out.println(add);
//		 System.out.println(address);
//		 Thread.sleep(4000);
//		 Common.assertionCheckwithReport(add.equals(address) ,
//					"validating the dropdown in the shipping address page ",
//					"User should able to see the Locate dropdown for respective address", "Successfully Locate dropdown has been displayed in shipping address page",
//					"Failed to display dropdown in the shipping address page");

//		}
//		String Text = Common.getText("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");

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
			Thread.sleep(3000);
			Common.textBoxInputClear("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
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
		Thread.sleep(4000);
		String email = data.get(dataSet).get("Email");
		String fullname = data.get(dataSet).get("FirstName");
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
//				Common.clickElement("xpath", "//div[@class='p-Input']//input[@name='email']");
//				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='email']", email);
//				Common.clickElement("xpath", "//div[@class='p-Input']//input[@name='name']");
//				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='name']", fullname);
//				Common.dropdown("xpath", "//select[@name='country']", Common.SelectBy.TEXT, data.get(dataSet).get("Country"));
//				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='addressLine1']", data.get(dataSet).get("Street"));
//				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='locality']", data.get(dataSet).get("City"));
//				Common.dropdown("xpath", "//select[@name='administrativeArea']", Common.SelectBy.TEXT, data.get(dataSet).get("State"));
//				String details=Common.findElement("xpath", "//div[@class='p-Input']//input[@name='email']").getAttribute("value");
//				Common.assertionCheckwithReport(
//						details.equals(email),
//						"validating the email field for the Afterpay Payment Method",
//						"Email should be entered in the email field in Afterpay payment method","Email has been dispalyed in the Afterpay payment",
//						"Failed to enter email in the Afterpay Payment");
				Common.switchToDefault();
				Common.clickElement("xpath", "//span[text()='Place Order']");
				Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");

			} else {
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Sync.waitElementPresent(30, "xpath", "//button[@value='afterpay_clearpay']");
				Common.clickElement("xpath", "//button[@value='afterpay_clearpay']");
//				Common.clickElement("xpath", "//div[@class='p-Input']//input[@name='email']");
//				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='email']", email);
//				Common.clickElement("xpath", "//div[@class='p-Input']//input[@name='name']");
//				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='name']", fullname);
//				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='addressLine1']", data.get(dataSet).get("Street"));
//				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='locality']", data.get(dataSet).get("City"));
//				Common.dropdown("xpath", "//select[@name='administrativeArea']", Common.SelectBy.TEXT, data.get(dataSet).get("State"));
//				String details=Common.findElement("xpath", "//div[@class='p-Input']//input[@name='email']").getAttribute("value");
//				Common.assertionCheckwithReport(
//						details.equals(email),
//						"validating the email field for the Afterpay Payment Method",
//						"Email should be entered in the email field in Afterpay payment method","Email has been dispalyed in the Afterpay payment",
//						"Failed to enter email in the Afterpay Payment");
				Common.switchToDefault();
				Common.clickElement("xpath", "//span[text()='Place Order']");
				Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
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
				String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();

				int size = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();
				Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
						"verifying the product confirmation", expectedResult,
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

	public void selectshippingaddress(String Dataset) {
		String method = data.get(Dataset).get("methods");

		try {
			Thread.sleep(4000);
			int size = Common.findElements("xpath", "//input[@class='a-radio-button__input']").size();
			if (size > 0) {
				Sync.waitElementPresent(30, "xpath", "//td[contains(text(),'" + method + "')]");
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

	public void validatingErrormessageShippingpage_negative() {
		int errorsize = Common.findElements("xpath", "//div[contains(@id,'error')]").size();
		String expectedResult = "Error message will dispaly when we miss the data in fields ";
		if (errorsize >= 0) {
			ExtenantReportUtils.addPassLog("validating the shippingPage error message", expectedResult,
					"sucessfully  dispaly error message", Common.getscreenShotPathforReport("errormessagenegative"));
		} else {

			ExtenantReportUtils.addFailedLog("validating the shippingPage error message", expectedResult,
					"failed to display error message", Common.getscreenShotPathforReport("failederrormessage"));

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

				Tell_Your_FriendPop_Up();
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

	public void click_singinButton() {
		try {
			Sync.waitElementPresent("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//li[@class='m-account-nav__log-in']//a[text()='Sign In']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(
					Common.getText("xpath", "//h1[@id='block-customer-login-heading']").equals("Sign In"),
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
			Common.clickElement("xpath", "//button[contains(@class,'action login')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
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
			String message = Common.findElement("id", "validation-classes").getCssValue("color");
			String redcolor = Color.fromString(message).asHex();
			String message1 = Common.findElement("id", "validation-length").getCssValue("color");
			String greencolor = Color.fromString(message1).asHex();
			String emailmessage = Common.findElement("xpath", "//div[@id='email_address-error']").getText();
			String confirmpassword = Common.findElement("xpath", "//div[@id='password-confirmation-error']").getText();
			Common.assertionCheckwithReport(
					redcolor.equals("#bf1322") && greencolor.equals("#2f741f") && emailmessage.contains("@domain.com")
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

	public void click_Createaccount() {

		try {
			Sync.waitElementPresent("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//li[@class='nav item']//a[text()='Create an Account']");
			Sync.waitPageLoad();
			Thread.sleep(5000);
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

	public String create_account(String Dataset) {
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
			Common.textBoxInput("xpath", "//input[@name='email']", Utils.getEmailid());
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
			Thread.sleep(4000);
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("My Account")
							&& message.contains("Thank you for registering with Hydro Flask."),
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
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}

			}
			Common.clickElement("xpath", "//img[@alt='" + product + "']");
			Thread.sleep(4000);
			System.out.println(product);
			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
			Common.assertionCheckwithReport(name.contains(product),
					"validating the product should navigate to the PDP page",
					"When we click on the product is should navigate to the PDP page",
					"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");

			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//div[@aria-label='" + productcolor + "']");
			Common.clickElement("xpath", "//div[@aria-label='" + productcolor + "']");
			product_quantity(Dataset);
//			click_UGC();
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

	public void minicart_validation(String Dataset) {
		// TODO Auto-generated method stub
		String UpdataedQuntityinminicart = data.get(Dataset).get("Quantity");
		try {

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

	public void minicart_delete(String Dataset) {
		// TODO Auto-generated method stub
		String deleteproduct = data.get(Dataset).get("Colorproduct");
		try {
			Sync.waitElementPresent(30, "xpath", "//span[@class='c-mini-cart__subtotal-amount']//span");
			String subtotal = Common.getText("xpath", "//span[@class='c-mini-cart__subtotal-amount']//span")
					.replace("$", "");
			Float subtotalvalue = Float.parseFloat(subtotal);
			String productname = Common
					.findElement("xpath", "(//div[@class='m-mini-product-card__info']//a[@class='a-product-name'])[1]")
					.getText();
			String productamount1 = Common.getText("xpath", "(//span[@class='minicart-price']//span)[1]").replace("$",
					"");
			Float productamount1value = Float.parseFloat(productamount1);
			if (productname.equals(deleteproduct)) {
				Sync.waitElementPresent(30, "xpath",
						"(//div[@class='m-mini-product-card__info']//span[contains(@class,'icon-cart__remove')])[1]");
				Common.clickElement("xpath",
						"(//div[@class='m-mini-product-card__info']//span[contains(@class,'icon-cart__remove')])[1]");
				Sync.waitElementPresent("xpath", "//button[contains(@class,'a-btn a-btn--primary action-p')]//span");
				Common.clickElement("xpath", "//button[contains(@class,'a-btn a-btn--primary action-p')]//span");
			} else {
				Assert.fail();
			}
			Thread.sleep(6000);
			String subtotal1 = Common.getText("xpath", "//span[@class='c-mini-cart__subtotal-amount']//span")
					.replace("$", "");
			Float subtotal1value = Float.parseFloat(subtotal1);
			Thread.sleep(8000);
			String productamount = Common.getText("xpath", "//span[@class='minicart-price']//span").replace("$", "");
			Float productamountvalue = Float.parseFloat(productamount);
			Float Total = subtotalvalue - productamount1value;
			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(ExpectedTotalAmmount2.equals(subtotal1),
					"validating the delete operation and subtotal",
					"The product should be delete from mini cart and subtotal should change",
					"Successfully product delete from the mini cart and subtotal has been changed",
					"Failed to delete the product from cart and subtotal not changed");

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
			Common.clickElement("xpath", "(//span[text()='Toggle Password Visibility'])[1]");
			Sync.waitElementPresent(30, "xpath", "//input[@name='password_confirmation']");
			Common.clickElement("xpath", "//input[@name='password_confirmation']");
			Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
					data.get(Dataset).get("Confirm Password"));
			Common.clickElement("xpath", "(//span[text()='Toggle Password Visibility'])[2]");
			String accounttext = Common.findElement("xpath", "//h3[text()='Create an Account']").getText();
			String confirmpassword = Common.findElement("xpath", "//input[@name='password_confirmation']")
					.getAttribute("type");
			String password = Common.findElement("xpath", "//input[@name='password_confirmation']")
					.getAttribute("type");
			String Message = Common.findElement("id", "validation-classes").getCssValue("color");
			String Greencolor = Color.fromString(Message).asHex();
			String Message1 = Common.findElement("id", "validation-length").getAttribute("class");
			Common.assertionCheckwithReport(
					Greencolor.equals("#056d59") && Message1.contains("complete") && confirmpassword.equals("text")
							&& password.equals("text") && accounttext.contains("Create an Account"),
					"validating the order confirmation page",
					"User should able to view all details in the order confirmation page",
					"Sucessfully all details has been displayed in the order confirmation",
					"Failed to display all details in the order confirmation page");
			Sync.waitElementPresent(30, "xpath", "(//span[text()='Toggle Password Visibility'])[1]");
			Common.clickElement("xpath", "(//span[text()='Toggle Password Visibility'])[1]");
			Sync.waitElementPresent(30, "xpath", "(//span[text()='Toggle Password Visibility'])[2]");
			Common.clickElement("xpath", "(//span[text()='Toggle Password Visibility'])[2]");
			String confirmpassword1 = Common.findElement("xpath", "//input[@name='password_confirmation']")
					.getAttribute("type");
			String password1 = Common.findElement("xpath", "//input[@name='password_confirmation']")
					.getAttribute("type");
			Sync.waitElementPresent("xpath", "//label[@for='is_subscribed']");
			Common.clickElement("xpath", "//label[@for='is_subscribed']");
			Common.findElement("xpath", "//label[@for='is_subscribed']").isSelected();
			Common.assertionCheckwithReport(confirmpassword1.equals("password") && password1.equals("password"),
					"validating the password field changed to dots",
					"After clicking on the eye icon it should be change to dots",
					"Sucessfully passwords has been changed to dots after clicking on eye icon",
					"Failed change passwords into dots after clicking on eye icon");

			Sync.waitElementPresent(30, "xpath", "//span[text()='Create an Account']");
			Common.clickElement("xpath", "//span[text()='Create an Account']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath",
					"//div[@data-ui-id='message-success']//div[@class='a-message__container-inner']");
			String message = Common.findElement("xpath",
					"//div[@data-ui-id='message-success']//div[@class='a-message__container-inner']").getText();
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("My Account") && message.contains("Thank you for registering"),
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

	public String minicart_items() {
		// TODO Auto-generated method stub
		String items = "";
		try {
			Sync.waitElementPresent("xpath", "//span[@class='c-mini-cart__counter']");
			items = Common.findElement("xpath", "//span[@class='c-mini-cart__counter']").getText();
			System.out.println(items);
			Common.clickElement("xpath", "//div[@class='c-mini-cart js-mini-cart']");
			Sync.waitElementPresent("xpath", "//p[@class='c-mini-cart__total-counter']//strong");
			String miniitems = Common.findElement("xpath", "//p[@class='c-mini-cart__total-counter']//strong")
					.getText();
			System.out.println(miniitems);
			Common.assertionCheckwithReport(items.contains(miniitems),
					"Vaildating the products count in the mini cart ",
					"Products count shsould be display in the mini cart",
					"Sucessfully products count has displayed in the mini cart",
					"failed to display products count in the mini cart");
			Sync.waitElementPresent("xpath", "//div[@class='c-mini-cart__close-btn']");
			Common.clickElement("xpath", "//div[@class='c-mini-cart__close-btn']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Vaildating the products count in the mini cart ",
					"Products count shsould be display in the mini cart",
					"Unable to display the  products count in the mini cart",
					Common.getscreenShot("failed to display products count in the mini cart"));

			Assert.fail();

		}
		return items;

	}

	public void minicart_products(String minicart) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
			Common.mouseOverClick("xpath", "//span[contains(@class,'c-mini-cart__icon')]");

			Sync.waitElementPresent(30, "xpath", "//span[@class='c-mini-cart__counter']");
			String cartproducts = Common.findElement("xpath", "//span[@class='c-mini-cart__counter']").getText();

			Common.assertionCheckwithReport(cartproducts.equals(minicart),
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

			Sync.waitElementClickable("id", "block-discount-heading");
			Common.clickElement("id", "block-discount-heading");
			if (Common.getCurrentURL().contains("preprod")) {
				Sync.waitElementPresent("id", "discount-code");

				Common.textBoxInput("id", "discount-code", data.get(dataSet).get("Discountcode"));
			} else {
				Sync.waitElementPresent("id", "discount-code");

				Common.textBoxInput("id", "discount-code", data.get(dataSet).get("prodDiscountcode"));
			}

			int size = Common.findElements("id", "discount-code").size();
			Common.assertionCheckwithReport(size > 0, "verifying the Discount Code label", expectedResult,
					"Successfully open the discount input box", "User unable enter Discount Code");
			Sync.waitElementClickable("xpath", "//button[@value='Apply Discount']");
			Common.clickElement("xpath", "//button[@value='Apply Discount']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.scrollIntoView("xpath", "//div[contains(@data-ui-id,'checkout-cart-validation')]");
			expectedResult = "It should apply discount on your price.If user enters invalid promocode it should display coupon code is not valid message.";
			String discountcodemsg = Common.getText("xpath", "//div[contains(@data-ui-id,'checkout-cart-validation')]");
			System.out.println(discountcodemsg);
			Common.assertionCheckwithReport(discountcodemsg.contains("Your coupon was successfully"),
					"verifying pomocode", expectedResult, "promotion code working as expected",
					"Promation code is not applied");
			Thread.sleep(4000);
			Common.scrollIntoView("xpath", "//tr[@class='totals sub']//span[@class='price']");
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
			Thread.sleep(4000);
			Float Total = (subtotalvalue + shippingvalue + Taxvalue) + Discountvalue;
			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			Thread.sleep(4000);
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

	public void newuseraddDeliveryAddress(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5000);
			Sync.waitElementVisible("xpath", "//input[@type='email']");
			Common.textBoxInput("xpath", "//input[@type='email']", Utils.getEmailid());
		} catch (NoSuchElementException e) {
			minicart_Checkout();
			Common.textBoxInput("xpath", "//input[@type='email']", Utils.getEmailid());

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
			Common.textBoxInputClear("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
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
				Thread.sleep(4000);
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
				Common.clickElement("xpath", "//span[text()='Place Order']");
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
			Common.switchFrames("xpath", "//iframe[@id='klarna-apf-iframe']");
			Sync.waitElementPresent("xpath", "//input[@name='phone']");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//span[text()='Phone number']");
			WebElement clear = Common.findElement("xpath", "//input[@name='phone']");
			clear.sendKeys(Keys.CONTROL + "a");
			clear.sendKeys(Keys.DELETE);
			Thread.sleep(4000);
			Common.findElement("xpath", "//input[@name='phone']").sendKeys(number);
			Common.clickElement("xpath", "//div[@id='onContinue__icon-wrapper']");
			Sync.waitPageLoad();
			Sync.waitElementPresent(30, "xpath", "//input[@id='otp_field']");
			Common.textBoxInput("xpath", "//input[@id='otp_field']", otp);
			Thread.sleep(6000);
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//button[@data-testid='pick-plan']");
			int paymenttype = Common.findElements("xpath", "//p[@id='funding-source-card-issuer']").size();
			if (paymenttype > 0) {
				Common.clickElement("xpath", "//p[@id='funding-source-card-number']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.clickElement("xpath", "//span[text()='Add new']");
				Sync.waitPageLoad();
				Sync.waitElementPresent(30, "xpath", "//iframe[@id='payment-gateway-frame']");
				Common.switchFrames("xpath", "//iframe[@id='payment-gateway-frame']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//input[@id='cardNumber']//parent::div");
				Common.findElement("xpath", "//input[@id='cardNumber']//self::input").sendKeys(Cardnumber);
				Common.javascriptclickElement("xpath", "//input[@id='expire']//parent::div");
				Common.findElement("xpath", "//input[@id='expire']").sendKeys(data.get(Dataset).get("ExpMonthYear"));
				Common.javascriptclickElement("xpath", "//input[@id='securityCode']//parent::div");
				Common.findElement("xpath", "//input[@id='securityCode']").sendKeys(data.get(Dataset).get("cvv"));
				Common.switchToDefault();
				Common.switchFrames("xpath", "//iframe[@id='klarna-apf-iframe']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//span[contains(text(),'Continue')]");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//span[contains(text(),'Pay $')]");
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//button[@data-testid='PushFavoritePayment:skip-favorite-selection']");
			} else {
				Common.clickElement("xpath", "//button[@data-testid='pick-plan']");
				Sync.waitPageLoad();
				Sync.waitElementPresent(30, "xpath", "//iframe[@id='payment-gateway-frame']");
				Common.switchFrames("xpath", "//iframe[@id='payment-gateway-frame']");
				Common.clickElement("xpath", "//input[@id='cardNumber']//parent::div");
				Common.findElement("xpath", "//input[@id='cardNumber']//self::input").sendKeys(Cardnumber);
				Common.javascriptclickElement("xpath", "//input[@id='expire']//parent::div");
				Common.findElement("xpath", "//input[@id='expire']").sendKeys(data.get(Dataset).get("ExpMonthYear"));
				Common.javascriptclickElement("xpath", "//input[@id='securityCode']//parent::div");
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

	public void close_add() throws Exception {
		// TODO Auto-generated method stub
		Thread.sleep(3000);
		int sizesframe = Common.findElements("xpath", "//div[@class='preloaded_lightbox']/iframe").size();
		if (sizesframe > 0) {
			Common.actionsKeyPress(Keys.PAGE_UP);

			Thread.sleep(4000);
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

	public void acceptPrivacy() {

		Common.clickElementStale("id", "truste-consent-button");
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
				if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {

					Thread.sleep(1000);
					Common.clickElement("xpath", "//span[text()='Place Order']");
					expectedResult = "credit card fields are filled with the data";
					String errorTexts = Common.findElement("xpath", "//div[contains(@class,'error')]").getText();
					Common.assertionCheckwithReport(
							errorTexts.isEmpty() || errorTexts.contains("Please complete your payment details."),
							"validating the credit card information with valid data", expectedResult,
							"Filled the Card detiles", "missing field data it showinng error");
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

					Thread.sleep(1000);
					Common.clickElement("xpath", "//span[text()='Place Order']");
					expectedResult = "credit card fields are filled with the data";
					String errorTexts = Common.findElement("xpath", "//div[contains(@class,'error')]").getText();
					Common.assertionCheckwithReport(
							errorTexts.isEmpty() || errorTexts.contains("Please complete your payment details."),
							"validating the credit card information with valid data", expectedResult,
							"Filled the Card detiles", "missing field data it showinng error");
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
					Common.getscreenShot("Failed to Navigate to the MY account page after Clicking on my account CTA"));
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
				Common.textBoxInput("xpath", "//input[@title='Phone Number']", data.get(dataSet).get("phone"));
				Common.textBoxInput("xpath", "//input[@title='Address Line 1']", data.get(dataSet).get("Street"));

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
			Thread.sleep(4000);
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
					Common.getscreenShot("Failed to Navigate to the MY account page after Clicking on my account CTA"));
			Assert.fail();
		}
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//a[text()='My Orders']");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Orders"),
					"validating the Navigation to the My Orders page",
					"After Clicking on My Orders CTA user should be navigate to the My Orders page",
					"Sucessfully User Navigates to the My Orders page after clicking on the My Orders CTA",
					"Failed to Navigate to the My Orders page after Clicking on My Orders CTA");
			String Ordernumber = Common.findElement("xpath", "(//div[@class='order-data order-data__info']//a)[1]")
					.getText();
			System.out.println(Ordernumber);
			System.out.println(Dataset);
			Common.assertionCheckwithReport(Ordernumber.equals(Dataset),
					"validating the Order Number in My Myorders page",
					"Order Number should be display in the MY Order page",
					"Sucessfully Order Number is displayed in the My orders page",
					"Failed to Display My order Number in the My orders page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Order Number in My Myorders page",
					"Order Number should be display in the MY Order page",
					"Unable to Display the Order Number in the My orders page",
					Common.getscreenShot("Failed to Display My order Number in the My orders page"));
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
		String product = data.get(Dataset).get("Products");
		try {
			Sync.waitPageLoad();
			int MyFavorites = Common.findElements("xpath", "//div[contains(@class,'message')]//span").size();

			if (MyFavorites != 0) {
				search_product("Product");
				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product + "']");
				Common.clickElement("xpath", "//img[@alt='" + product + "']");
				Sync.waitElementPresent(30, "xpath", "//button[@data-action='add-to-wishlist']");
				Common.clickElement("xpath", "//button[@data-action='add-to-wishlist']");
				Sync.waitPageLoad();
				Thread.sleep(3000);
				Common.assertionCheckwithReport(Common.getPageTitle().equals("My Favorites"),
						"validating the Navigation to the My Favorites page",
						"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
						"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA",
						"Failed to Navigate to the My Favorites page after Clicking on My Favorites button");
				Common.findElements("xpath", "//span[contains(@class,'a-wishlist')]");
				Sync.waitPageLoad();
				String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				System.out.println(message);
				Common.assertionCheckwithReport(message.contains("has been added to your Wish List"),
						"validating the  product add to the Whishlist", "Product should be add to whishlist",
						"Sucessfully product added to the Whishlist ", "failed to add product to the Whishlist");
				String Whishlistproduct = Common
						.findElement("xpath", "//div[contains(@class,'m-product-card__name')]//a").getText();
				System.out.println(Whishlistproduct);

				if (Whishlistproduct.equals(product)) {
					Sync.waitElementPresent(30, "xpath", "//a[contains(@title,'" + product + "')]//img");
					Common.mouseOver("xpath", "//a[contains(@title,'" + product + "')]//img");
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
					int minicart = Common.findElements("xpath", "//span[@class='c-mini-cart__counter']").size();
					System.out.println(minicart);
					if (minicart > 0) {
						minicart_Checkout();
					} else {
						Assert.fail();
					}
				} else {
					Assert.fail();
				}

			} else {
				Sync.waitPageLoad();

				Common.scrollIntoView("xpath", "(//img[contains(@class,'m-produc')])[1]");
				Sync.waitElementPresent(30, "xpath", "(//img[contains(@class,'m-produc')])[1]");
				Common.mouseOver("xpath", "(//img[contains(@class,'m-produc')])[1]");
				Sync.waitElementPresent("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
				see_options("Product");
				int minicart = Common.findElements("xpath", "//span[@class='c-mini-cart__counter']").size();
				System.out.println(minicart);
				if (minicart > 0) {
					minicart_Checkout();
				}

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"Unable to add  product to the cart ", Common.getscreenShot("failed to add product to the cart"));
			Assert.fail();
		}

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

	public void RegaddDeliveryAddress(String dataSet) {
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

				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
						data.get(dataSet).get("Street"));

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
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
						data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
						data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
						data.get(dataSet).get("Street"));

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
					Common.dropdown("xpath", "//form[@id='co-shipping-form']//select[@name='region_id']",
							Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					// TODO: handle exception
					Thread.sleep(3000);
					Common.dropdown("xpath", "//form[@id='co-shipping-form']//select[@name='region_id']",
							Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);
				Common.textBoxInputClear("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']");
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']",
						data.get(dataSet).get("postcode"));

				String ShippingZip = Common
						.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']")
						.getAttribute("value");
				System.out.println("*****" + ShippingZip + "*******");

				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='telephone']",
						data.get(dataSet).get("phone"));

			} catch (Exception | Error e) {
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

	public void edit_Account_info(String dataSet) {
		// TODO Auto-generated method stub
		Accont_Information();
		try {

			Sync.waitElementPresent("xpath", "//span[@class='m-accordion__title-label']");

			Common.clickElement("xpath", "//span[@class='m-accordion__title-label']");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//div//input[@id='current-password']");
			Common.textBoxInput("xpath", "//input[@id='current-password']", data.get(dataSet).get("Password"));
			Common.textBoxInput("xpath", "//input[@id='password']", data.get(dataSet).get("Confirm Password"));
			Common.textBoxInput("xpath", "//input[@id='password-confirmation']",
					data.get(dataSet).get("Confirm Password"));
			String message = Common.findElement("id", "validation-classes").getCssValue("color");
			String greencolor = Color.fromString(message).asHex();
			String message1 = Common.findElement("id", "validation-length").getAttribute("class");

			Common.assertionCheckwithReport(greencolor.equals("#2f741f") && message1.contains("complete"),
					"validating the cureent password and new password fields",
					"User should able enter data in current password and new password",
					"Sucessfully the data has been entered in new password and current password",
					"Failed to enter data in current password and new password fields");

			Common.clickElement("xpath", "//button[@title='Save']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			String sucessmessage = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
			Thread.sleep(4000);
			System.out.println(sucessmessage);
			Common.assertionCheckwithReport(sucessmessage.contains("You saved the account"),
					"Validating the saved account information", "Account information should be saved for the user",
					"Sucessfully account information has been saved ", "failed to save the account information");

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

	public void Prodeler_Application_Page(String dataSet) {
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

		String expectedResult = "User is redirected to Pro Deal application page";
		try {

			Sync.waitElementPresent("id", "first_name");
			int fistnamesize = Common.findElements("id", "first_name").size();
			Common.assertionCheckwithReport(fistnamesize > 0,
					"Successfully User is redirected to Pro Deal application page", expectedResult,
					"User able to redirected to Pro Deal application page");
			Thread.sleep(3000);
			Common.textBoxInput("id", "first_name", data.get(dataSet).get("FirstName"));
			Sync.waitElementPresent("id", "last_name");
			Common.textBoxInput("id", "last_name", data.get(dataSet).get("LastName"));
			Sync.waitElementPresent("id", "association");
			Common.textBoxInput("id", "association", data.get(dataSet).get("Company"));
			Sync.waitElementPresent("id", "association_email");
			Common.textBoxInput("id", "association_email", data.get(dataSet).get("Email"));
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(6000);
			String path = System.getProperty("user.dir")
					+ ("\\src\\test\\resources\\TestData\\Hydroflask\\TestScreen.png");
			Sync.waitElementPresent(40, "xpath", "//input[@id='supporting_document']");
//				Common.fileUpLoad("xpath", "//input[@id='supporting_document']", path);
			Common.findElement("xpath", "//input[@id='supporting_document']").sendKeys(path);

			Sync.waitElementPresent("id", "group_id");
			Common.clickElement("xpath", "//select[@id='group_id']");
			Common.dropdown("xpath", "//select[@id='group_id']", SelectBy.VALUE, data.get(dataSet).get("GropName"));

			Sync.waitElementPresent("id", "comment");
			Common.textBoxInput("id", "comment", data.get(dataSet).get("CommetsHydroflask"));

			Sync.waitElementPresent("xpath", "//button[@title='Submit']");
			Common.clickElement("xpath", "//button[@title='Submit']");

			Common.switchToDefault();
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@class='prodeal']//h4").getText();
			Common.assertionCheckwithReport(message.contains("Thank you for applying"),
					"To validate the success message for the prodeal",
					"After clicking on the submit button it should navigate to the Success page",
					"user Sucessfully navigate to the Success page after clicking on the submit button",
					"Failed to get the success message for the pro deal submission");

		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("ProDeal application form filling",
					"User field to fill the prodeal aplication ",
					"user to get the success message for the pro deal submission",
					Common.getscreenShotPathforReport("Failed to get the success message for the pro deal submission"));
			Assert.fail();

		}
	}

	public void click_Prodeal() {
		// TODO Auto-generated method stub
		try {
			Common.scrollIntoView("xpath", "//a[text()='Pro Deal']");
			Sync.waitElementPresent("xpath", "//a[text()='Pro Deal']");
			Common.clickElement("xpath", "//a[text()='Pro Deal']");
			Sync.waitElementVisible("xpath", "//h1[@class='page-title-wrapper']");
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Pro Deal Application"),
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

	public String payPal_Payment(String dataSet) throws Exception {

		String order = "";

		String expectedResult = "It should open paypal site window.";
		try {
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//input[@id='paypal_express']");
			Thread.sleep(2000);
			Common.clickElement("xpath", "//input[@id='paypal_express']");
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Common.switchFrames("xpath", "//iframe[contains(@class,'component-frame visible')]");

			// Common.refreshpage();
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

			Common.clickElement("id", "login_emaildiv");
			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.clickElement("id", "btnNext");
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			int sizeemail = Common.findElements("id", "email").size();

			Common.assertionCheckwithReport(sizeemail > 0, "verifying the paypal payment ", expectedResult,
					"open paypal site window", "faild to open paypal account");

			try {
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
						"User failed to proceed with paypal payment",
						Common.getscreenShotPathforReport(expectedResult));
				Assert.fail();
			}
//			Tell_Your_FriendPop_Up();//To close the Pop-up
			String url1 = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
			if (!url1.contains("stage") && !url1.contains("preprod")) {
			}

			else {
				try {
					Thread.sleep(6000);
					String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();
					System.out.println(sucessMessage);

					int size = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();
					Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
							"verifying the product confirmation", expectedResult,
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
		return order;
	}

	public void access_for_prodeal(String Dataset) {
		// TODO Auto-generated method stub
		click_Prodeal();
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//input[@name='access_code']//parent::div");
			Common.textBoxInput("xpath", "//input[@name='access_code']", data.get(Dataset).get("Access code"));
			Common.clickElement("xpath", "//button[@title='Submit']");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			String successmessage = Common.findElement("xpath", "//div[contains(@class,'message-notice')]//div")
					.getText();

			System.out.println(successmessage);
			Common.assertionCheckwithReport(successmessage.contains("Enjoy Pro Deal pricing on select products."),
					"validating the Pro Deal success message ", "should display the success message",
					"successfully display the success message", "failed to display the success message");
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
			String eventname = Common.findElement("xpath", "//span[@class='value']").getText();
			if (eventname.equals("Birthday")) {
				Common.dropdown("xpath", "//select[@id='event_country_region']", SelectBy.TEXT,
						data.get(Dataset).get("Region"));
				Common.textBoxInput("xpath", "//input[@id='event_date']", data.get(Dataset).get("Date"));
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
//	        Baby_Registry("Baby Registry");
			Registrant_Information("Birthday");
			String shipping = Common.findElement("xpath", "(//select[@name='address_type_or_id']//option)[2]")
					.getAttribute("value");
			Common.dropdown("xpath", "//select[@name='address_type_or_id']", Common.SelectBy.VALUE, shipping);
			Common.clickElement("id", "submit.save");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
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
			Sync.waitElementPresent("xpath", "//a[text()='Gift Registry']");
			Common.clickElement("xpath", "//a[text()='Gift Registry']");
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
			Sync.waitElementPresent("xpath", "//span[text()='Create New Registry']");
			Common.clickElement("xpath", "//span[text()='Create New Registry']");
			Sync.waitPageLoad();
			Common.dropdown("id", "type_id", SelectBy.TEXT, data.get(Dataset).get("Type"));
			Common.clickElement("id", "submit.next");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//span[@class='value']");
			String eventname = Common.findElement("xpath", "//span[@class='value']").getText();
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
		String eventname = Common.findElement("xpath", "//span[@class='value']").getText();
		try {
			if (eventname.equals("Birthday")) {
				Common.textBoxInput("xpath", "//input[@name='registrant[0][firstname]']",
						data.get(Dataset).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='registrant[0][lastname]']",
						data.get(Dataset).get("LastName"));
				Common.textBoxInput("xpath", "//input[@name='registrant[0][email]']", data.get(Dataset).get("Email"));
				Common.clickElement("id", "add-registrant-button");
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
			String registry = Common.findElement("xpath", "(//fieldset[@class='recipients section']//span)[1]")
					.getText();
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

			Common.clickElement("xpath", "//span[text()='Edit']");
			Sync.waitPageLoad();
			Common.scrollIntoView("xpath", "//input[@title='Zip/Postal Code']");
			Common.textBoxInput("xpath", "//input[@title='Zip/Postal Code']", data.get(Dataset).get("postcode"));
			Common.clickElement("id", "submit.save");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
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
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
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
			Common.clickElement("xpath", "//a[@title='Delete']");
			Common.clickElement("xpath", "//button[@class='a-btn a-btn--primary action-primary action-accept']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
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
			Sync.waitElementPresent(30, "xpath", "//a[@title='Share']");
			Common.clickElement("xpath", "//a[@title='Share']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//button[@type='submit']");
			Common.clickElement("xpath", "//button[@type='submit']");
			Sync.waitElementPresent(30, "xpath", "//div[contains(@id,'error')]");
			String errormessage = Common.findElement("xpath", "//div[contains(@id,'error')]").getText();
			Common.assertionCheckwithReport(errormessage.equals("This is a required field."),
					"validating the error message with empty fields ",
					"After clicking hare button with empty data error message should be display",
					"successfully error message has been dispalyed ", "failed to display the error message");
			Common.textBoxInput("xpath", "//input[@name='recipients[0][name]']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='recipients[0][email]']", data.get(Dataset).get("LastName"));
			Common.clickElement("xpath", "//button[@type='submit']");
			Sync.waitElementPresent(30, "xpath", "//div[@class='mage-error']");
			String invalidemail = Common.findElement("xpath", "//div[@class='mage-error']").getText();
			Common.assertionCheckwithReport(invalidemail.contains("Please enter a valid email address"),
					"validating the error message with invalid email ",
					"After clicking hare button with invalid email error message should be display",
					"successfully error message has been dispalyed ", "failed to display the error message");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the error message with invalid email ",
					"After clicking hare button with invalid email error message should be display",
					"Unable to see the error message has been dispalyed ",
					Common.getscreenShot("failed to display the error message"));
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
			Sync.waitElementPresent("xpath", "//p[@class='c-mini-cart__total-counter']//strong");
			String minicart = Common.findElement("xpath", "//p[@class='c-mini-cart__total-counter']//strong").getText();
			Sync.waitElementPresent("xpath", "//span[text()='View Cart']");
			Common.clickElement("xpath", "//span[text()='View Cart']");
			String viewcart = Common.findElement("xpath", "//span[@class='t-cart__items-count']").getText();
			Sync.waitPageLoad();
			Thread.sleep(8000);
//			Common.assertionCheckwithReport(
//					viewcart.contains(minicart) && Common.getCurrentURL().contains("/checkout/cart/"),
//					"validating the navigation to the view cart", "User should able to navigate to the view cart page",
//					"Successfully navigates to the view cart page",
//					"Failed to navigate to the view and edit cart page");

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
			Thread.sleep(4000);
			Common.clickElement("xpath", "//button[@type='submit']//span[@class='a-btn__label']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Manage Gift Registry"),
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
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//div[@class='control m-text-input']");
			Common.clickElement("xpath", "//div[@class='control m-text-input']");
			Common.textBoxInput("xpath", "//input[@class='input-text qty a-text-input']",
					data.get(Dataset).get("Quantity"));
			Sync.waitElementPresent(30, "xpath", "//span[text()='Update Items']");
			Common.clickElement("xpath", "//span[text()='Update Items']");
			Sync.waitElementPresent(30, "xpath", "//div[@class='mage-error']");
			String errormessage = Common.findElement("xpath", "//div[@class='mage-error']").getText();
			Common.assertionCheckwithReport(errormessage.contains("Please enter a number greater than 0"),
					"validating nthe error message validation for the prodcuts in gift registry ",
					"After Upadting the quantity to zero the eroor message should be display",
					"successfully quantity has been changed to zero and error message has been displayed",
					"failed to Display the error message for the when quantity changed to zero");

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
			if (Common.getCurrentURL().contains("preprod")) {
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

			Sync.waitElementPresent("xpath", "//button[@title='Search']");
			Common.clickElement("xpath", "//button[@title='Search']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String orderid = Common.findElement("xpath", "//h1[@class='page-title-wrapper h2']").getText();
			System.out.println(orderid);
			Common.assertionCheckwithReport(Common.getPageTitle().contains(orderid), "verifying order status form",
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

	public void click_trackorder() {
		try {
			Common.actionsKeyPress(Keys.END);
			Common.clickElement("xpath", "//a[text()='Track Your Order']");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Orders and Returns") || Common.getPageTitle().equals("My Orders"),
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
		click_trackorder();

		try {
			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "//tbody[@class='m-table__body']").size();
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

	public void Kalrna_Payment(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> Paymentmethod = new HashMap<String, String>();
		Sync.waitPageLoad();
		Thread.sleep(4000);

		String fullname = data.get(dataSet).get("FirstName");
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
				String email = Common.findElement("xpath", "//div[@class='p-Input']//input[@name='email']")
						.getAttribute("value");
				Common.clickElement("xpath", "//div[@class='p-Input']//input[@name='name']");
				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='name']", fullname);
				String details = Common.findElement("xpath", "//div[@class='p-Input']//input[@name='email']")
						.getAttribute("value");
				Common.assertionCheckwithReport(details.equals(email),
						"validating the email field for the Klarana Payment Method",
						"Email should be entered in the email field in Klarana payment method",
						"Email has been dispalyed in the Klarna payment",
						"Failed to enter email in the Klarna Payment");
				Common.switchToDefault();
				Common.clickElement("xpath", "//span[text()='Place Order']");
				klarna_Details(dataSet);

			} else {
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Common.clickElement("xpath", "//button[@value='klarna']");
				Common.clickElement("xpath", "//div[@class='p-Input']//input[@name='email']");
				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='email']", Utils.getEmailid());
				String email = Common.findElement("xpath", "//div[@class='p-Input']//input[@name='email']")
						.getAttribute("value");
				Common.clickElement("xpath", "//div[@class='p-Input']//input[@name='name']");
				Common.textBoxInput("xpath", "//div[@class='p-Input']//input[@name='name']", fullname);
				String details = Common.findElement("xpath", "//div[@class='p-Input']//input[@name='email']")
						.getAttribute("value");
				Common.assertionCheckwithReport(details.equals(email),
						"validating the email field for the Klarana Payment Method",
						"Email should be entered in the email field in Klarana payment method",
						"Email has been dispalyed in the Klarna payment",
						"Failed to enter email in the Klarna Payment");
				Common.switchToDefault();
				Common.clickElement("xpath", "//span[text()='Place Order']");
				klarna_Details(dataSet);
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
			String Subtotal = Common.getText("xpath", "//tr[@class='totals sub']//span[@class='price']").replace("$",
					"");
			Float subtotalvalue = Float.parseFloat(Subtotal);
			String shipping = Common.getText("xpath", "//tr[@class='totals shipping excl']//span[@class='price']")
					.replace("$", "");
			Float shippingvalue = Float.parseFloat(shipping);
			Thread.sleep(3000);
			String Tax = Common.getText("xpath", "//tr[@class='totals-tax']//span[@class='price']").replace("$", "");
			Float Taxvalue = Float.parseFloat(Tax);
			Thread.sleep(3000);
			String ordertotal = Common.getText("xpath", "//tr[@class='grand totals']//span[@class='price']")
					.replace("$", "");
			Float ordertotalvalue = Float.parseFloat(ordertotal);
			Float Total = subtotalvalue + shippingvalue + Taxvalue;
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

	public void clickContact() throws Exception {
		String expectedResult = "It should land successfully on the contact page";

		Common.actionsKeyPress(Keys.END);
		try {

			Sync.waitElementPresent("xpath", "//a[text()='Contact Us']");
			Common.clickElement("xpath", "//a[text()='Contact Us']");
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

			Common.clickElement("xpath", "//span[text()='Write to Us']");
			Sync.waitElementPresent(40, "xpath", "//iframe[contains(@src,'https://hydroflask')]");
			Common.switchFrames("xpath", "//iframe[contains(@src,'https://hydroflask')]");

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

			Sync.waitElementPresent("xpath", "//input[@name='conversationStreetforforms']");
			Common.textBoxInput("xpath", "//input[@name='conversationStreetforforms']",
					data.get(dataSet).get("Street"));

			Sync.waitElementPresent("xpath", "//input[@name='conversationCityForForms']");
			Common.textBoxInput("xpath", "//input[@name='conversationCityForForms']", data.get(dataSet).get("City"));

			Sync.waitElementPresent("xpath", "//input[@name='conversationCountry']");
			Common.clickElement("xpath", "//input[@name='conversationCountry']");

			Sync.waitElementPresent("xpath", "//div[text()='United States']");
			Common.clickElement("xpath", "//div[text()='United States']");

			Sync.waitElementPresent("xpath", "//input[@name='conversationState']");
			Common.clickElement("xpath", "//input[@name='conversationState']");

			Sync.waitElementPresent("xpath", "//div[text()='Alabama']");
			Common.clickElement("xpath", "//div[text()='Alabama']");

			Sync.waitElementPresent("xpath", "//input[@name='conversationZipCodeforforms']");
			Common.textBoxInput("xpath", "//input[@name='conversationZipCodeforforms']",
					data.get(dataSet).get("postcode"));

			Sync.waitElementPresent("xpath", "//input[@name='conversationHowCanWeHelp']");
			Common.clickElement("xpath", "//input[@name='conversationHowCanWeHelp']");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//div[@data-path='order_issues']");

			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//div[@id='conversationOrderIssues']");
			Common.clickElement("xpath", "//div[@id='conversationOrderIssues']");

			Sync.waitElementPresent("xpath", "//div[text()='Billing Issue']");
			Common.clickElement("xpath", "//div[text()='Billing Issue']");

			Sync.waitElementPresent("xpath", "//input[@class='form-base' and @id='conversationOrder']");
			Common.textBoxInput("xpath", "//input[@class='form-base' and @id='conversationOrder']",
					data.get(dataSet).get("OrderID"));
			Sync.waitElementPresent("xpath", "//textarea[@id='messagePreview']");
			Common.textBoxInput("xpath", "//textarea[@id='messagePreview']",
					data.get(dataSet).get("CommetsHydroflask"));

			Common.scrollIntoView("xpath", "//button[text()='Submit']");
			Common.clickElement("xpath", "//button[text()='Submit']");

			Sync.waitElementPresent("xpath", "//div[@class='form-wrap']");
			int Contactussuccessmessage = Common.findElements("xpath", "//h1[@data-content-type='heading']").size();
			System.out.println(Contactussuccessmessage);
			Common.assertionCheckwithReport(Contactussuccessmessage > 0 || Contactussuccessmessage >= 0,
					"verifying Contact us Success message ", "Success message should be Displayed",
					"Contact us Success message displayed ", "failed to dispaly success message");
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying email us from",
					"contact us form data enter without any error message", "Contact us page getting error ",
					Common.getscreenShotPathforReport("Contact us page"));
			Assert.fail();

		}

		Common.actionsKeyPress(Keys.PAGE_UP);
		String Text = Common.getText("xpath", "(//div[@class='form-wrap']//span)[2]");
		expectedResult = "User gets confirmation under the same tab. It includes a reference number and email is sent to email provided. No validation errors.";
		Common.assertionCheckwithReport(Text.contains("Your submission was successful "),
				"verifying contact us conformation message", expectedResult, "Failed to submit the contact us form");

	}

	public void review(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.clickElement("xpath", "//img[@alt='" + products + "']");

			Common.scrollIntoView("xpath", "//a[text()='Reviews & Questions']");
			Sync.waitElementPresent("xpath", "//a[@id='tab-label-product.yotpo.reviews-title']");
			Thread.sleep(3000);
			String form = Common.getText("xpath", "//a[@id='tab-label-product.yotpo.reviews-title']");
			System.out.println(form);
			Common.assertionCheckwithReport(form.equals("Reviews & Questions"), "verifying the write a review button",
					"Write a review should be appear in the PDP page",
					"Sucessfully write a review button has been displayed in PDP page",
					"Failed to display the write a review button in PDP page");
			Common.clickElement("xpath", "//a[text()='Reviews & Questions']");
			Sync.waitElementPresent("xpath", "//span[text()='Write A Review']");
			Common.clickElement("xpath", "//span[text()='Write A Review']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the Write a review button", "select the write review option",
					"User Unable to click write review option  ",
					Common.getscreenShotPathforReport("User Failed to click write review option "));
			Assert.fail();
		}
		try {
			String expectedResult = "Sucessfully title input box has been displayed";
			Common.clickElement("xpath", "//input[@value='Post']");
			String errormessage = Common.findElement("xpath", "//span[@class='form-input-error']").getText();
			System.out.println(errormessage);
			Common.assertionCheckwithReport(errormessage.contains("Please enter a star rating for this review"),
					"verifying the error message in invalid fields",
					"error message should be display in the invalid fields",
					"Sucessfully Error message has been displayed in invalid fileds ",
					"Failed to display the error meesage in invalid fields ");
			score(data.get(Dataset).get("score"));
			Sync.waitElementPresent("xpath", "//input[@name='review_title']");
			int title = Common.findElements("xpath", "//input[@name='review_title']").size();
			Common.assertionCheckwithReport(title > 0, "verifying the title page",
					"title input box should be displayed", expectedResult, "User Unable to display the title box");
			Common.textBoxInput("xpath", "//input[@name='review_title']", data.get(Dataset).get("title"));
			Common.textBoxInput("xpath", "//textarea[@name='review_content']", data.get(Dataset).get("Review"));
			Common.textBoxInput("xpath", "//input[@name='display_name']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='email']", data.get(Dataset).get("UserName"));
			Common.clickElement("xpath", "//input[@value='Post']");
			String emailerror = Common.findElement("xpath", "//span[@class='form-input-error']").getText();
			Common.assertionCheckwithReport(emailerror.contains("Invalid email"),
					"verifying the invaild email for the product review",
					"error message should be display for invaild email",
					"Sucessfully error message has been displayed for invalid email",
					"Failed to display the error message for invaild email");
			Thread.sleep(4000);
			Common.textBoxInput("xpath", "//input[@name='email']", data.get(Dataset).get("Email"));
			Common.clickElement("xpath", "//input[@value='Post']");
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@class='yotpo-thank-you']").getAttribute("aria-label");
			Common.assertionCheckwithReport(message.equals("Thank you for posting a review"),
					"verifying the post for the product review",
					"product review should be submit after clicking on post",
					"Sucessfully Thank you message has been displayed ", "Failed to display the Thank you message ");
			Common.clickElement("xpath", "//div[@aria-label='Next']");

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
			Sync.waitElementPresent("xpath", "//span[@aria-label='score 1']");
			Common.clickElement("xpath", "//span[@aria-label='score 1']");
			break;
		case "2":
			Sync.waitElementPresent("xpath", "//span[@aria-label='score 2']");
			Common.clickElement("xpath", "//span[@aria-label='score 2']");
			break;
		case "3":
			Sync.waitElementPresent("xpath", "//span[@aria-label='score 3']");
			Common.clickElement("xpath", "//span[@aria-label='score 3']");
			;
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

	public void BillingAddress(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//label[@for='stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='stripe_payments']").size();
			Common.clickElement("xpath", "//label[@for='stripe_payments']");
			Common.assertionCheckwithReport(sizes > 0, "Validating the payment section page",
					"payment section should be displayed", "sucessfully payment section has been displayed",
					"Failed to displayed the payment section");
			Sync.waitElementPresent(30, "xpath", "//input[@type='checkbox']");
			Common.javascriptclickElement("xpath", "//input[@type='checkbox']");
			Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
			Thread.sleep(4000);
			String text = Common.findElement("xpath", "//input[@name='street[0]']").getAttribute("value");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
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
//			Common.textBoxInputClear("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//div[@class='field _required']//input[@name='postcode']",
					data.get(dataSet).get("postcode"));
			Thread.sleep(5000);

			Common.textBoxInput("xpath", "//div[@class='field _required']//input[@name='telephone']",
					data.get(dataSet).get("phone"));
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
			Common.switchFrames("xpath", "//iframe[@aria-label='Modal Overlay Box Frame']");
			Sync.waitElementPresent(30, "xpath", "//label[@for='form_input_email']");
			Common.clickElement("xpath", "//label[@for='form_input_email']");
			Common.textBoxInput("xpath", "//input[@placeholder='Enter Email Address']", data.get(Dataset).get("Email"));
			Common.clickElement("xpath", "//button[@aria-label='Submit Modal Form']");
			Sync.waitElementPresent(30, "xpath", "//div[@class='form_inputs_error_text']");
			String Errormessage = Common.findElement("xpath", "//div[@class='form_inputs_error_text']").getText();
			System.out.println(Errormessage);
			Common.assertionCheckwithReport(Errormessage.equals("Your Email is in an invalid format."),
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

			Common.textBoxInputClear("xpath", "//input[@placeholder='Enter Email Address']");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//button[@aria-label='Submit Modal Form']");
			Sync.waitElementPresent(30, "xpath", "//div[@class='form_inputs_error_text']");
			String Errormessage = Common.findElement("xpath", "//div[@class='form_inputs_error_text']").getText();
			System.out.println(Errormessage);
			Common.assertionCheckwithReport(Errormessage.equals("Your Email is required."),
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
		String order = "";
		String otp = data.get(Dataset).get("OTP Number");
		String DOB = data.get(Dataset).get("DOB");
		String Cardnumber = data.get(Dataset).get("cardNumber");
		System.out.println(Cardnumber);

		try {
			Sync.waitPageLoad();
			Common.switchWindows();
			Common.switchFrames("xpath", "//iframe[@id='klarna-apf-iframe']");
			Sync.waitElementPresent("xpath", "//input[@name='phone']");
			Thread.sleep(4000);
			WebElement clear = Common.findElement("xpath", "//input[@name='phone']");
			clear.sendKeys(Keys.CONTROL + "a");
			clear.sendKeys(Keys.DELETE);
			Thread.sleep(4000);
			int number = Common.genrateRandomNumber();
			System.out.println(number);
			String mobile = Integer.toString(number);
			String phone = "+91" + "95862" + mobile;
			System.out.println(phone);
			Common.textBoxInput("xpath", "//input[@name='phone']", phone);
			Common.clickElement("xpath", "//div[@id='onContinue__icon-wrapper']");
			Sync.waitPageLoad();
			Sync.waitElementPresent(30, "xpath", "//input[@id='otp_field']");
			Common.textBoxInput("xpath", "//input[@id='otp_field']", otp);
			Thread.sleep(6000);
			Sync.waitPageLoad();
			Sync.waitElementPresent(30, "xpath", "//h1[@role='status']");
			String klarna = Common.findElement("xpath", "//h1[@role='status']").getText();
			if (klarna.equals("What's your email?")) {
				Common.clickElement("xpath", "//div[@id='onContinue__icon-wrapper']");
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//div[@id='addressCollector-date_of_birth__container']");
				Common.findElement("xpath", "//input[@id='addressCollector-date_of_birth']").sendKeys(DOB);
				Common.clickElement("xpath", "//div[@id='addressCollector-street_address__label']");
				Common.findElement("xpath", "//input[@name='street_address']")
						.sendKeys(data.get(Dataset).get("Street"));
				Common.clickElement("xpath", "//div[@id='addressCollector-city__label']");
				Common.findElement("xpath", "//input[@name='city']").sendKeys(data.get(Dataset).get("City"));
				Common.clickElement("xpath", "//div[@id='addressCollector-region__label']");
				Common.findElement("xpath", "//input[@name='region']").sendKeys(data.get(Dataset).get("Region"));
				Common.clickElement("xpath", "//div[@id='addressCollector-postal_code__label']");
				Common.findElement("xpath", "//input[@name='postal_code']").sendKeys(data.get(Dataset).get("postcode"));
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
				Common.findElement("xpath", "//input[@id='cardNumber']//self::input").sendKeys(Cardnumber);
				Common.javascriptclickElement("xpath", "//input[@id='expire']//parent::div");
				Common.findElement("xpath", "//input[@id='expire']").sendKeys(data.get(Dataset).get("ExpMonthYear"));
				Common.javascriptclickElement("xpath", "//input[@id='securityCode']//parent::div");
				Common.findElement("xpath", "//input[@id='securityCode']").sendKeys(data.get(Dataset).get("cvv"));
				Common.switchToDefault();
				Common.switchFrames("xpath", "//iframe[@id='klarna-apf-iframe']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//span[contains(text(),'Continue')]");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//span[contains(text(),'Pay $')]");
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//button[@data-testid='PushFavoritePayment:skip-favorite-selection']");
			} else {
				Common.clickElement("xpath", "//span[contains(text(),'Continue')]");
				Thread.sleep(4000);
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
				Thread.sleep(4000);
				Sync.waitElementPresent(60, "xpath", "//h1[@class='page-title-wrapper']");
				String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();
				System.out.println(sucessMessage);

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

	public void noitems_giftregistry(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent(30, "xpath", "//input[@type='checkbox']");
			Common.clickElement("xpath", "//input[@type='checkbox']");
			Sync.waitElementPresent(30, "xpath", "//div[@class='control m-text-input']");
			Common.javascriptclickElement("xpath", "//div[@class='control m-text-input']");
			Common.textBoxInput("xpath", "//input[contains(@class,'input-text qty a-text-input')]",
					data.get(Dataset).get("Quantity"));
			Sync.waitElementPresent("xpath", "//span[text()='Update Items']");
			Common.clickElement("xpath", "//span[text()='Update Items']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String deletemessage = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
			System.out.println(deletemessage);
			Common.assertionCheckwithReport(deletemessage.contains("You updated the gift registry items."),
					"verifying the delete product in gift registry", "product should be delete from the gift registry",
					"Sucessfully product has been deleted from the gift registry",
					Common.getscreenShotPathforReport("Failed to delete the product from the gift registry"));
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.scrollIntoView("xpath", "//div[@class='message info empty']//span");
			String emptymessage = Common.findElement("xpath", "//div[@class='message info empty']//span").getText();
			Common.assertionCheckwithReport(emptymessage.contains("This gift registry has no items."),
					"verifying the no prodcts in the gift registry",
					"product should be not display in the gift registry",
					"Sucessfully products should not been displayed in the gift registry",
					Common.getscreenShotPathforReport("Failed to delete the products in the gift registry"));
			Common.clickElement("xpath", "//strong[text()='Gift Registry']");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the no prodcts in the gift registry",
					"product should be not display in the gift registry",
					"Unable to display the  products in the gift registry",
					Common.getscreenShotPathforReport("Failed to delete the products in the gift registry"));

			Assert.fail();
		}

	}

	public void Stored_Payment(String Dataset) {
		// TODO Auto-generated method stub

		try {
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Sync.waitElementPresent(30, "xpath", "//a[text()='My Account']");
			Common.clickElement("xpath", "//a[text()='My Account']");
			Thread.sleep(4000);
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
					Common.getscreenShot("Failed to Navigate to the MY account page after Clicking on my account CTA"));
			Assert.fail();
		}
		try {
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//a[text()='Stored Payment Methods']");
			Common.clickElement("xpath", "//a[text()='Stored Payment Methods']");
			Sync.waitPageLoad(30);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Payment Methods"),
					"validating the Navigation to the My Payment Methods page",
					"After Clicking on stored methods CTA user should be navigate to the My Payment Methods page",
					"Sucessfully User Navigates to the My Payment Methods page after clicking on the stored methods  CTA",
					"Failed to Navigate to the My Payment Methods page after Clicking on my stored methods  CTA");
			int size = Common.findElements("xpath", "//tbody[@class='m-table__body']").size();
			if (size > 0) {
				String number = Common.findElement("xpath", "//td[@data-th='Payment Method']//label").getText()
						.replace("•••• ", "");
				Thread.sleep(4000);
				Common.assertionCheckwithReport(number.contains("4242") && Dataset.contains("4242"),
						"validating the card details in the my orders page",
						"After Clicking on My payments methods and payment method should be appear in payment methods",
						"Sucessfully payment method is appeared in my payments methods",
						"Failed to display the payment methods in the my payments methods");
//				Thread.sleep(4000);
//				Sync.waitElementPresent("xpath", "//a[contains(@class,'stripe-payments')]");
//				Common.clickElement("xpath", "//a[contains(@class,'stripe-payments')]");
//				Common.switchToTopFrame();
//				Common.clickElement("xpath", "//span[contains(text(),'OK')]");
//				Common.switchToDefault();
//				Thread.sleep(4000);
//				String message=Common.findElement("xpath", "//div[@data-ui-id='message-error']//div[@class='a-message__container-inner']").getText();
//				Common.assertionCheckwithReport(message.contains("Sorry, it is not possible to delete this payment method"),
//						"validating the error message for delete card",
//						"After Clicking the delete button we need to get the error message",
//						"Sucessfully the error has been displayed when we click on the delete",
//						"Failed to display the error message when we clcik on the delete message");
//				
			} else {
				Assert.fail();
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the error message for delete card",
					"After Clicking the delete button we need to get the error message",
					"Unable to display the error message when we clcik on the delete message",
					Common.getscreenShot("Failed to display the error message when we clcik on the delete message"));
			Assert.fail();
		}

	}

	public void view_order() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			String number = Common.findElement("xpath", "//a[@title='View Order']").getText();
			Sync.waitElementPresent("xpath", "//span[text()='View Order']");
			Common.clickElement("xpath", "//span[text()='View Order']");
			Sync.waitPageLoad();
			Sync.waitElementPresent(40, "xpath", "//h1[@data-ui-id='page-title-wrapper']");
			String Ordernumber = Common.findElement("xpath", "//h1[@data-ui-id='page-title-wrapper']").getText();
			Common.findElement("xpath", "//span[contains(@class,'order-status ')]");
			String reorder = Common.findElement("xpath", "//a[contains(@class,'action or')]//span").getText();
			String backCTA = Common.findElement("xpath", "//a[contains(@class,'action back')]//span[2]").getText();
			String orderdate = Common.findElement("xpath", "//div[@class='order-info']/p").getText();
			String shippingAdd = Common.findElement("xpath", "//div[contains(@class,'shipping-address')]").getText();
			String billingAdd = Common.findElement("xpath", "//div[contains(@class,'billing-address')]").getText();
			String shippingmethod = Common.findElement("xpath", "//div[contains(@class,'shipping-method')]").getText();
			String ordersummary = Common.findElement("xpath", "//div[contains(@class,'shipping-method')]").getText();
			String itemsordered = Common.findElement("xpath", "//div[@class='product-name-wrapper']").getText();
			System.out.println(itemsordered);

			Common.assertionCheckwithReport(
					reorder.contains("Reorder") && backCTA.contains("Back") && orderdate.contains("Date")
							&& reorder.contains("Reorder"),
					"validating the order details ",
					"After Clicking on view Order it should be navigate to the order details page ",
					"Sucessfully navigated to the orders detail page", "Failed to Navigate to the orders detail page");
//			
//
//			Common.clickElement("xpath", "//div[@aria-label='Next']");
//			Common.assertionCheckwithReport(reorder.contains("Reorder"),
//					"validating the order summary and UGC carasol ",
//					"After Clicking on view Order it should be navigate to the order page and UGC carasol should be displayed",
//					"Sucessfully UGC Carsol is displayed and navigated to the orders page",
//					"Failed to Navigate to the orders page and UGC Casrol is not displayed ");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the order summary and UGC carasol ",
					"After Clicking on view Order it should be navigate to the order page and UGC carasol should be displayed",
					"Unable to Navigate to the orders page and UGC Casrol is not displayed ",
					Common.getscreenShot("Failed to Navigate to the orders page and UGC Casrol is not displayed "));
			Assert.fail();

		}

	}

	public void CLP_Page(String category) {
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
			verifying_sub_category();
			verifying_shop_Best_Sellers();

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

	public void verifying_shop_Best_Sellers() {
		// TODO Auto-generated method stub
		String product;

		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			List<WebElement> sub_category = Common.findElements("xpath",
					"//div[contains(@class,'product-item-info m-')]");
			System.out.println(sub_category.size());
			for (int i = 0; i < sub_category.size() - 4; i++) {
				List<WebElement> Image = Common.findElements("xpath", "//span[@class='product-image-wrapper']");
				Sync.waitPageLoad();
				Sync.waitImplicit(10);
//			Thread.sleep(4000);
				product = Image.get(i).getText();
				Thread.sleep(3000);
				Image.get(i).click();
				Thread.sleep(4000);
				ExtenantReportUtils.addPassLog("Validating" + product + "Page  ",
						"click on the shop best sellers should navigate to the  " + product + "Page",
						"successfully page navigating to " + product + "PAGE",
						Common.getscreenShotPathforReport(product));
				Common.navigateBack();

			}
			Sync.waitElementPresent("xpath", "//nav[@class='m-breadcrumb u-container']");
//			Common.clickElement("xpath", "//a[text()='Shop']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			String title = Common.findElement("xpath", "//h1[@class='c-clp-hero__headline']").getText();
			Common.assertionCheckwithReport(title.contains("Bottles & Drinkware"),
					"validating the breadcrumbs navigating to the" + title,
					"It should be navigate sucessfully to the" + title, "Sucessfully navigated to the" + title,
					"Failed to navigate to the" + title);
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
		// TODO Auto-generated method stub
		String Country;
		try {
			Common.actionsKeyPress(Keys.END);
			List<WebElement> country = Common.findElements("xpath", "//label[contains(@class,'a-radio-button')]");
			System.out.println(country.size());
			for (int i = 0; i < country.size(); i++) {

				List<WebElement> select = Common.findElements("xpath", "//label[contains(@class,'a-radio-button')]");
				Sync.waitPageLoad();
				Sync.waitElementPresent(50, "xpath", "//img[contains(@class,'a-icon-text-btn')]");
				Common.clickElement("xpath", "//img[contains(@class,'a-icon-text-btn')]");
				Thread.sleep(3000);
				Country = select.get(i).getText();
				System.out.println(Country);
				select.get(i).click();
				if (Country.contains("United States")) {

					Common.clickElement("xpath", "//button[@data-role='closeBtn']");
					ExtenantReportUtils.addPassLog("Validating" + Country + "Page  ",
							"click on the country should navigate to the  " + Country + "Page",
							"successfully page navigating to " + Country + "PAGE",
							Common.getscreenShotPathforReport(Country));
				} else {
					Common.clickElement("xpath", "//span[contains(text(),'Confirm')]");
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
			Sync.waitElementPresent("xpath", "//a[text()='Dealer Central']");
			Common.clickElement("xpath", "//a[text()='Dealer Central']");
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
//			Sync.waitElementPresent(40, "xpath", "//iframe[contains(@src,'https://hydroflask')]");
//			Common.switchFrames("xpath", "//iframe[contains(@src,'https://hydroflask')]");

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

			Common.clickElement("xpath", "//div[@id='conversationAreYouAnAsiPpaiIndustryMem']");
			Thread.sleep(4000);
			Common.javascriptclickElement("xpath", "//div[@data-path='no']");

//			Sync.waitElementPresent("xpath", "//div[@id='conversationCustomOrder']");
//			Common.clickElement("xpath", "//div[@id='conversationCustomOrder']");
//			Thread.sleep(4000);
//			Common.javascriptclickElement("xpath", "//div[@data-path='no']");

			Sync.waitElementPresent("xpath", "//input[@name='conversationInHandDate']");

			Common.textBoxInput("xpath", "//input[@name='conversationInHandDate']", data.get(dataSet).get("date"));

			Common.clickElement("xpath", "//div[@id='conversationSellThruWebsite']");
			Thread.sleep(4000);
			Common.javascriptclickElement("xpath", "//div[@data-path='no']");

			Common.textBoxInput("xpath", "//input[@id='whatOfYourSalesComeThroughYourWebsite']",
					data.get(dataSet).get("salesPercentage"));

			Common.textBoxInput("xpath", "//input[@id='numberOfStores']", data.get(dataSet).get("numberOfStores"));

			Common.clickElement("xpath", "//div[@id='conversationStoreSize']");
			Common.clickElement("xpath", "//div[text()='" + storesize + "']");

			Common.textBoxInput("xpath", "//input[@id='annualRevenue']", data.get(dataSet).get("annualRevenue"));

			Common.textBoxInput("xpath", "//input[contains(@id,'WhatIsTheEstimatedNumberOfUnits')]",
					data.get(dataSet).get("NumberOfUnits"));

			Common.textBoxInput("xpath", "//input[@id='yearsInBusiness']", data.get(dataSet).get("yearsInBusiness"));

			Sync.waitElementPresent("xpath", "//input[@id='storeAddress']");
			Common.textBoxInput("xpath", "//input[@id='storeAddress']", data.get(dataSet).get("Street"));

			Common.textBoxInput("xpath", "//input[@name='suiteUnit']", data.get(dataSet).get("yearsInBusiness"));

			Sync.waitElementPresent("xpath", "//div[@id='conversationCountry']");
			Common.clickElement("xpath", "//div[@id='conversationCountry']");

			Sync.waitElementPresent("xpath", "//div[text()='" + country + "']");
			Common.clickElement("xpath", "//div[text()='" + country + "']");

			Sync.waitElementPresent("xpath", "//div[@id='conversationState']");
			Common.clickElement("xpath", "//div[@id='conversationState']");

			Sync.waitElementPresent("xpath", "//div[text()='" + state + "']");
			Common.clickElement("xpath", "//div[text()='" + state + "']");

			Sync.waitElementPresent("xpath", "//input[@data-label='City']");
			Common.textBoxInput("xpath", "//input[@data-label='City']", data.get(dataSet).get("City"));

			Sync.waitElementPresent("xpath", "//input[@data-label='Zip / Code']");
			Common.textBoxInput("xpath", "//input[@data-label='Zip / Code']", data.get(dataSet).get("postcode"));

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

			Common.textBoxInput("xpath", "//input[@data-label='Phone']", data.get(dataSet).get("phone"));
			Common.textBoxInput("xpath", "//input[@name='customerEmail']", data.get(dataSet).get("Email"));

			Common.textBoxInput("xpath", "//input[@name='inquirySubmittedBy']", data.get(dataSet).get("submittedby"));

			Common.clickElement("xpath", "//button[text()='Submit']");
			Sync.waitElementPresent("xpath", "//div[@class='form-wrap']");
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
		String Text = Common.getText("xpath", "//div[@class='form-wrap']");
		expectedResult = "User gets confirmation under the same tab. It includes a reference number and email is sent to email provided. No validation errors.";
		Common.assertionCheckwithReport(Text.contains("Your submission was successful "),
				"verifying contact us confirmation message", expectedResult,
				"User gets confirmation under the same tab", "unable to load the confirmation form");

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
				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + colorproduct + "']");
				Common.clickElement("xpath", "//img[@alt='" + colorproduct + "']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.actionsKeyPress(Keys.END);
				Common.actionsKeyPress(Keys.PAGE_UP);
				Sync.waitElementPresent("xpath",
						"//div[contains(@class,'sticky-atc')]//div[@aria-label='" + colorname + "']");
				Common.clickElement("xpath",
						"//div[contains(@class,'sticky-atc')]//div[@aria-label='" + colorname + "']");
				Sync.waitElementPresent("xpath", "//span[text()='Add to Cart']");
				Common.clickElement("xpath", "//span[text()='Add to Cart']");
				Common.clickElement("xpath", "//button[@id='product-sticky-addtocart-button']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
						.getAttribute("data-ui-id");
				System.out.println(message);
				Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
						"Product should be add to cart", "Sucessfully product added to the cart ",
						"failed to add product to the cart");
				Common.actionsKeyPress(Keys.UP);
			} else {
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
				Common.actionsKeyPress(Keys.END);
				Sync.waitElementPresent("xpath", "//span[text()='Add to Cart']");
				Common.clickElement("xpath", "//span[text()='Add to Cart']");

				Common.clickElement("xpath", "//button[@id='product-sticky-addtocart-button']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
						.getAttribute("data-ui-id");
				System.out.println(message);
				Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
						"Product should be add to cart", "Sucessfully product added to the cart ",
						"failed to add product to the cart");
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
				Common.clickElement("xpath", "//span[text()='" + socallinksarry[i] + "']");
				Common.switchWindows();
				System.out.println(Common.getCurrentURL());

				if (socallinksarry[i].equals("Instagram")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("instagram"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				}

				else if (socallinksarry[i].equals("Facebook")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("www.facebook.com"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				}

				else if (socallinksarry[i].equals("Twitter")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("twitter"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				} else if (socallinksarry[i].equals("YouTube")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("youtube"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				} else if (socallinksarry[i].equals("Pinterest")) {
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

//	            for(int j=1;j<5;j++)
//	            {
//	                Common.scrollIntoView("xpath", "//a[contains(@class,'u-hidden--md-d')]");
//	                Common.clickElement("xpath", "//a[contains(@class,'u-hidden--md-d')]");
//	                Thread.sleep(4000);
//	                
//	            }

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
			Sync.waitElementPresent("xpath", "//img[@alt='" + Product + "']");
			Common.clickElement("xpath", "//img[@alt='" + Product + "']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			validating_BundleProducts();
			product_quantity(Dataset);
			bundle_color("White");
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
		Common.actionsKeyPress(Keys.PAGE_UP);
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
			Sync.waitElementPresent("xpath", "//a[text()='Warranty']");
			Common.clickElement("xpath", "//a[text()='Warranty']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//a[@class='btn btn-primary']");
			Common.clickElement("xpath", "//a[@class='btn btn-primary']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Warranty"),
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

			Sync.waitElementPresent("xpath", "//input[@data-label='Street']");
			Common.textBoxInput("xpath", "//input[@data-label='Street']",
					data.get(dataSet).get("Street"));

			Sync.waitElementPresent("xpath", "//input[@data-label='City']");
			Common.textBoxInput("xpath", "//input[@data-label='City']", data.get(dataSet).get("City"));

			Sync.waitElementPresent("xpath", "//input[@data-label='State / Province']");
			Common.clickElement("xpath", "//input[@data-label='State / Province']");

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
			Common.assertionCheckwithReport(productsearch.contains("Sorry, your search"),
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

		try {
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
		}

	}

	public void popular_searches() {
		String search;

		try {

			List<WebElement> Search = Common.findElements("xpath",
					"(//div[@id='algolia-cms-block-popular-search'])//h4[@class='c-popular-searches__title']/a");
			System.out.println(Search);
			System.out.println(Search.size());
			for (int i = 0; i < Search.size(); i++) {

				List<WebElement> select = Common.findElements("xpath",
						"(//div[@id='algolia-cms-block-popular-search'])//h4[@class='c-popular-searches__title']/a");
				Sync.waitPageLoad();
				Sync.waitElementPresent(50, "xpath",
						"(//div[@id='algolia-cms-block-popular-search'])//h4[@class='c-popular-searches__title']/a");
				Thread.sleep(3000);
				search = select.get(i).getText();
				System.out.println(search);
				select.get(i).click();
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.navigateBack();
				Common.navigateBack();
				ExtenantReportUtils.addPassLog("Validating" + search + "Page  ",
						"click on the Popular search should navigate to the  " + search + "Page",
						"successfully page navigating to " + search + "PAGE",
						Common.getscreenShotPathforReport(search));

			}
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
			Thread.sleep(2000);

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
			Common.scrollIntoView("xpath", "//a[text()='Reviews & Questions']");
			Sync.waitElementPresent("xpath", "//a[@id='tab-label-product.yotpo.reviews-title']");
			Thread.sleep(3000);
			String form = Common.getText("xpath", "//a[@id='tab-label-product.yotpo.reviews-title']");
			System.out.println(form);
			Common.assertionCheckwithReport(form.equals("Reviews & Questions"), "verifying the write a review button",
					"Write a review should be appear in the PDP page",
					"Sucessfully write a review button has been displayed in PDP page",
					"Failed to display the write a review button in PDP page");
			Common.clickElement("xpath", "//a[text()='Reviews & Questions']");
			Sync.waitElementPresent("xpath", "//span[text()='Write A Review']");
			Common.clickElement("xpath", "//span[text()='Write A Review']");

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
			Common.dropdown("xpath", "//select[@class='a-select-menu']", Common.SelectBy.VALUE, productquantity);
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//span[text()='Add to Cart']");
			Common.clickElement("xpath", "//span[text()='Add to Cart']");
			Sync.waitForLoad();
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
			Common.textBoxInput("xpath", "//input[@name=\"username\"]", data.get(dataSet).get("UserName"));
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//span[text()='Toggle password visibility']");
			String shipping = Common.findElement("xpath", "(//span[text()='Shipping'])[1]").getText();
			System.out.println(shipping);
			Common.clickElement("xpath", "//span[text()='Item in Cart']");
			String QTY = Common.findElement("xpath", "(//span[@class='a-product-attribute__value'])[1]").getText();
			System.out.println(QTY);
			String Price = Common.findElement("xpath", "(//span[@class='a-product-attribute__value'])[2]").getText();
			System.out.println(Price);
			Common.clickElement("xpath", "(//span[text()='View Details'])[2]");
			String Color = Common.findElement("xpath", "(//span[@class='a-product-attribute__value'])[3]").getText();
			System.out.println(Color);
			Common.assertionCheckwithReport(shipping.equals("Shipping"),
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
			Common.clickElement("xpath", "//span[contains(text(),'Forgot')]");
			String forgotpassword = Common.findElement("xpath", "//h1[text()='Forgot Your Password?']").getText();
			System.out.println(forgotpassword);
			Common.textBoxInput("xpath", "//input[@name='email']", Utils.getEmailid());
			Thread.sleep(4000);
			Common.findElement("xpath", "//input[@name='email']").getAttribute("value");
			Common.clickElement("xpath", "//span[text()='Reset My Password']");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			Sync.waitElementPresent(30, "xpath", "//div[contains(@data-ui-id,'message')]//div");
			String message = Common.findElement("xpath", "//div[contains(@data-ui-id,'message')]//div").getText();
			Thread.sleep(4000);
			System.out.println(message);
			Common.assertionCheckwithReport(
					message.contains("We received too many requests for password resets")
							|| message.contains("If there is an account associated"),
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

			} else if (Common.getCurrentURL().contains("https://mcloud-na-preprod.hydroflask.com/")) {

				Common.oppenURL(strArray[i].replace("mcloud-na-stage", "www"));

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

	public void validateChatboxOptions(String DataSet) {
		// TODO Auto-generated method stub
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
			Common.javascriptclickElement("xpath", "//div[contains(@class,'footer__chatContainer')]");
//			Sync.waitElementClickable(30, "xpath", "//button[contains(@class,'CLMcd button')]");
//			Common.mouseOverClick("xpath", "//button[contains(@class,'CLMcd button')]");
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

	public void view_PLP_page() {
		try {
			String title = Common.findElement("xpath", "//h1[@data-element='title']").getAttribute("Class");
			String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'m-breadcrumb u-container')]")
					.getAttribute("aria-label");
			String filter = Common.findElement("xpath", "//div[@class='c-filter__block']").getText();
			String Sort = Common
					.findElement("xpath",
							"//div[@class='m-list-toolbar__sorter']//div[@class='m-select-menu m-form-elem'] ")
					.getText();
			Common.assertionCheckwithReport(
					breadcrumbs.contains("Breadcrumb") && title.contains("c-clp-hero__headline")
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
			Thread.sleep(3000);
			Common.clickElement("xpath", "//a[text()='" + category + "']");
//			Common.clickElement("xpath", "//span[text()='Load more']");
			String text = Common.findElement("xpath", "//a[text()='" + category + "']//span").getText();
			int textValue = Integer.parseInt(text);
			String categoryvalue = Integer.toString(textValue);
			Thread.sleep(6000);
//			Common.clickElement("xpath", "//span[text()='Load more']");
			String textValueAfterFilter = Common.findElement("xpath", "//span[@class='a-toolbar-info__number']")
					.getText();
			int noOfItems = Common.findElements("xpath", "//li[@class='ais-InfiniteHits-item']").size();
			String items = Integer.toString(noOfItems);
			System.out.println(text);
			System.out.println(textValue);
			System.out.println(categoryvalue);

			System.out.println(textValueAfterFilter);
			System.out.println(noOfItems);

			System.out.println(items);

			Common.assertionCheckwithReport(categoryvalue.equals(items),
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
		try {

			Common.clickElement("xpath", "//select[@class='ais-SortBy-select']");
			Common.dropdown("xpath", "//select[@class='ais-SortBy-select']", Common.SelectBy.TEXT,
					data.get(dataSet).get("Sort"));

			int size = Common
					.findElements("xpath", "//span[@data-price-type='finalPrice']//span[1]//span[@class='price']")
					.size();
			System.out.println(size);
			float[] float_array = new float[size];
			for (int i = 0; i < size; i++) {
				String text = Common
						.findElements("xpath", "//span[@data-price-type='finalPrice']//span[1]//span[@class='price']")
						.get(i).getText();
				String price = text.replace("$", "");
				Float priceValue = Float.parseFloat(price);
				System.out.println(priceValue);
				float_array[i] = priceValue;
			}
			Arrays.sort(float_array);
			String firstItemPriceText = Common
					.findElements("xpath", "//span[@data-price-type='finalPrice']//span[1]//span[@class='price']")
					.get(0).getText();
			String firstItemPrice = firstItemPriceText.replace("$", "");
			Float firstItemPriceValue = Float.parseFloat(firstItemPrice);
			if (data.get(dataSet).get("Sort").equals("Lowest Price")) {
				Common.assertionCheckwithReport(firstItemPriceValue.equals(float_array[0]),
						"To validate the Sort in Product Listing Page",
						"User should able to Sort in Product Listing Page",
						"Sucessfully Sorts in the Product Listing Page", "Failed to Sort  in Product Listing Page");
			} else if (data.get(dataSet).get("Sort").equals("Highest Price")) {
				Common.assertionCheckwithReport(firstItemPriceValue.equals(float_array[size - 1]),
						"To validate the Sort in Product Listing Page",
						"User should able to Sort in Product Listing Page",
						"Sucessfully Sorts in the Product Listing Page", "Failed to Sort  in Product Listing Page");
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
			Common.clickElement("xpath", "//a[text()='Find a Store']");

			String find = Common.findElement("xpath", "//h1[@class='u-container']").getText();
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

		String store = "DICK'S Sporting Goods  - San Antonio | Curbside Contactless Pickup Available - 5.3 mi";

		try {

			Common.switchFrames("xpath", "//iframe[@id='lcly-embedded-iframe-inner-0']");

			Sync.waitPageLoad();
			Thread.sleep(8000);
			String id = Common.findElement("xpath", "//div[contains(@aria-label,\"" + store + " \")]")
					.getAttribute("id");
//            Common.clickElement("xpath", "//div[contains(@aria-label,"DICK'S Sporting ")]");

			Common.findElement("xpath", "//div[@id='" + id + "']").click();
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
			Thread.sleep(5000);
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
			Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//a[@id='current-location-detector']");
			Common.mouseOverClick("xpath", "//a[@id='current-location-detector']");
			Sync.waitPresenceOfElementLocated("id", "current-location-indicator");
			Common.scrollIntoView("id", "current-location-indicator");
			int currentlocation = Common.findElements("id", "current-location-indicator").size();

			String address = Common.findElement("xpath", "//h5[contains(@class,'store-address')]").getText();
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
			Common.scrollIntoView("xpath", "//a[contains(@class,'tab-retailers')]");

			Common.mouseOverClick("xpath", "//a[contains(@class,'tab-retailers')]");
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
			String Storename = Common.findElement("xpath", "//h2[contains(@class,'store-name-inner')]").getText();
			System.out.println(Storename);
			Common.javascriptclickElement("xpath", "//a[contains(@class,'tab-locations')]");

			int storecount = Common.findElements("xpath", "//a[contains(@class,'conv-section-store')]/div/h3").size();
			for (int i = 1; i <= storecount; i++) {
				Thread.sleep(3000);
				String relatedstores = Common
						.findElement("xpath", "(//a[contains(@class,'conv-section-store')]/div/h3)[" + i + "]")
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
			Thread.sleep(3000);
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
			Sync.waitElementPresent("xpath", "//div[@class='cat-active-filter-container']");
			int filterSize = Common.findElements("xpath", "//div[@class='cat-active-filter-container']").size();

			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
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

			int stock = Common.findElements("xpath", "//div[@id='dealer-tab-inventory-grid-container-desktop']").size();
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

			Sync.waitElementPresent(40, "xpath", "//h5[text()='" + Productname + "']");
			Common.scrollIntoView("xpath", "//h5[text()='" + Productname + "']");
			Common.javascriptclickElement("xpath", "//h5[text()='" + Productname + "']");
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

	public void Myhydro_quantity(String Dataset) {
		// TODO Auto-generated method stub
		String Quantity = data.get(Dataset).get("Quantity");
		try {
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
			Sync.waitElementClickable("xpath", "//label[@for='stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='stripe_payments']").size();
			Common.clickElement("xpath", "//label[@for='stripe_payments']");
			Common.assertionCheckwithReport(sizes > 0, "Validating the payment section page",
					"payment section should be displayed", "sucessfully payment section has been displayed",
					"Failed to displayed the payment section");
			Sync.waitPageLoad();
			Sync.waitElementPresent(30, "xpath", "//span[text()='Edit']");
			Common.clickElement("xpath", "//span[text()='Edit']");
			Common.clickElement("xpath", "//select[@name='billing_address_id']");
			Common.dropdown("xpath", "//select[@name='billing_address_id']", Common.SelectBy.TEXT, "New Address");
			Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
			Thread.sleep(4000);
			String Text = Common
					.findElement("xpath", "//div[@class='checkout-billing-address']//input[@name='street[0]']")
					.getAttribute("value");
			System.out.println(Text);

			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
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
			Common.textBoxInput("xpath", "//div[@class='field _required']//input[@name='postcode']",
					data.get(dataSet).get("postcode"));
			Thread.sleep(5000);

			Common.textBoxInput("xpath", "//div[@class='field _required']//input[@name='telephone']",
					data.get(dataSet).get("phone"));
			Common.clickElement("xpath", "//span[text()='Update']");
			Sync.waitPageLoad();
			Thread.sleep(4000);

			String update = Common.findElement("xpath", "(//div[@class='billing-address-details']//p)[2]").getText();
			System.out.println(update);
			Common.assertionCheckwithReport(
					update.contains("6 Walnut Valley Dr") || Text.contains("6 Walnut Valley Dr"),
					"verifying the Billing address form in payment page",
					"Billing address should be saved in the payment page",
					"Sucessfully Billing address form should be Display ",
					"Failed to display the Billing address in payment page");

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
		String email = data.get(Dataset).get("Notifyme");
		String prod = data.get(Dataset).get("prod product");
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
			if (Common.getCurrentURL().contains("preprod")) {
				Sync.waitElementPresent(30, "xpath", "//img[contains(@alt,'" + products + "')]");
				String productprice = Common.findElement("xpath", "//span[@class='price-wrapper']")
						.getAttribute("data-price-amount");
				Common.clickElement("xpath", "//img[contains(@alt,'" + products + "')]");
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
				Common.clickElement("xpath", "//span[text()='Notify Me When Available']");
				Common.textBoxInput("xpath", "//input[@placeholder='Insert your email']", email);
				Common.clickElement("xpath", "//span[text()='Subscribe']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String newsubcribe = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				Common.assertionCheckwithReport(
						newsubcribe.contains("Alert subscription has been saved.")
								|| newsubcribe.contains("Thank you! You are already subscribed to this product."),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");
				Common.actionsKeyPress(Keys.END);
				Common.clickElement("xpath", "//span[text()='Notify Me When Available']");
				Common.textBoxInput("xpath", "//input[@placeholder='Insert your email']", email);
				Common.clickElement("xpath", "//span[text()='Subscribe']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String oldsubcribe = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
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
				Common.clickElement("xpath", "//span[text()='Notify Me When Available']");
				Common.textBoxInput("xpath", "//input[@placeholder='Insert your email']", email);
				Common.clickElement("xpath", "//span[text()='Subscribe']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String newsubcribe = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				Common.assertionCheckwithReport(
						newsubcribe.contains("Alert subscription has been saved.")
								|| newsubcribe.contains("Thank you! You are already subscribed to this product."),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");
				Common.actionsKeyPress(Keys.END);
				Common.clickElement("xpath", "//span[text()='Notify Me When Available']");
				Common.textBoxInput("xpath", "//input[@placeholder='Insert your email']", email);
				Common.clickElement("xpath", "//span[text()='Subscribe']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String oldsubcribe = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
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
			if (Common.getCurrentURL().contains("preprod")) {
				Sync.waitElementPresent(30, "xpath", "//img[contains(@alt,'" + products + "')]");
				String productprice = Common.findElement("xpath", "//span[@class='price-wrapper']")
						.getAttribute("data-price-amount");
				Common.clickElement("xpath", "//img[contains(@alt,'" + products + "')]");
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
				Common.clickElement("xpath", "//a[text()='Notify Me When Available']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String newsubcribe = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				System.out.println(newsubcribe);
				Common.assertionCheckwithReport(
						newsubcribe.contains("Alert subscription has been saved.")
								|| newsubcribe.contains("Thank you! You are already subscribed to this product."),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");
				Common.actionsKeyPress(Keys.END);
				Common.clickElement("xpath", "//a[text()='Notify Me When Available']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String oldsubcribe = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
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
				Common.clickElement("xpath", "//a[text()='Notify Me When Available']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String newsubcribe = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				System.out.println(newsubcribe);
				Common.assertionCheckwithReport(
						newsubcribe.contains("Alert subscription has been saved.")
								|| newsubcribe.contains("Thank you! You are already subscribed to this product."),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");
				Common.actionsKeyPress(Keys.END);
				Common.clickElement("xpath", "//a[text()='Notify Me When Available']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String oldsubcribe = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
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
				Common.mouseOver("xpath", "//button[@data-action='add-to-wishlist']");
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
				Common.assertionCheckwithReport(message.contains("has been added to your Wish List"),
						"validating the  product add to the Whishlist", "Product should be add to whishlist",
						"Sucessfully product added to the Whishlist ", "failed to add product to the Whishlist");
				Common.clickElement("xpath", "//button[@title='Share Favorites']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.textBoxInput("xpath", "//textarea[@name='emails']", data.get(Dataset).get("Email"));
				Common.textBoxInput("xpath", "//textarea[@name='message']", data.get(Dataset).get("message"));
				Common.clickElement("xpath", "//button[@title='Share Favorites']");
				Thread.sleep(4000);
				String message1 = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				System.out.println(message1);
				Common.assertionCheckwithReport(message1.contains("Your Favorites have been shared"),
						"validating the shared whishlist functionality",
						"sucess message should display after share whishlist",
						"Sucessfully message has been displayed for whishlist",
						"failed to display the message for whishlist");
			} else {
				Common.clickElement("xpath", "//button[@title='Share Favorites']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.textBoxInput("xpath", "//textarea[@name='emails']", data.get(Dataset).get("Email"));
				Common.textBoxInput("xpath", "//textarea[@name='message']", data.get(Dataset).get("message"));
				Common.clickElement("xpath", "//button[@title='Share Favorites']");
				Thread.sleep(4000);
				String message1 = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				System.out.println(message1);
				Common.assertionCheckwithReport(message1.contains("Your Favorites have been shared"),
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
			Common.clickElement("xpath", "//span[text()='Customize Yours!']");
			Thread.sleep(3000);
			Myhydro_bottle("40 oz");
			hydro_bottle_color("Black");
			hydro_cap_color("White");
			hydro_strap_color("Black");
			hydro_boot_color("White");
//				Myhydro_Engraving("Myhydro Product");
			Sync.waitElementPresent("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Common.clickElement("xpath", "//button[@class='nav-buttons__btn next-btn']");
			Myhydro_quantity(Dataset);
			Sync.waitElementPresent(20, "xpath", "//button[@class='ATC__btn']");
			Common.clickElement("xpath", "//button[@class='ATC__btn']");
//				Sync.waitElementPresent("xpath", "//button[contains(text(),'Agree &')]");
//				Common.clickElement("xpath", "//button[contains(text(),'Agree &')]");
			Thread.sleep(6000);
			Sync.waitElementPresent(40, "xpath", "//div[@class='a-message__container-inner']");
			String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("You added"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");

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
			Sync.waitElementPresent(30, "xpath", "//span[text()='Customize Yours!']");
			Common.clickElement("xpath", "//span[text()='Customize Yours!']");
			Thread.sleep(3000);
			Myhydro_bottle("40 oz");
			hydro_bottle_color("Black");
			hydro_cap_color("White");
			hydro_strap_color("Black");
			hydro_boot_color("White");
			Myhydro_Engraving("Myhydro Product");
			Myhydro_quantity(Dataset);
			Sync.waitElementPresent(20, "xpath", "//button[@class='ATC__btn']");
			Common.clickElement("xpath", "//button[@class='ATC__btn']");
			Sync.waitElementPresent("xpath", "//button[contains(text(),'Agree &')]");
			Common.clickElement("xpath", "//button[contains(text(),'Agree &')]");
			Thread.sleep(6000);
			Sync.waitElementPresent(40, "xpath", "//div[@class='a-message__container-inner']");
			String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("You added"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");

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
			String Cap = Common.findElement("xpath", "//h1[@class='menu__category-title']").getText();
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
			Common.assertionCheckwithReport(productcolor.contains(Color), "validating the color selection for cap",
					"color should be select for the cap", "Sucessfully color has been selected for the cap",
					"failed to select the color for the selected cap");

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
			String Strap = Common.findElement("xpath", "//h1[@class='menu__category-title']").getText();
			Common.assertionCheckwithReport(Strap.contains("Strap"), "validating the color selection for bottle",
					"color should be select for the bottle", "Sucessfully color has been selected for the bottle",
					"failed to select the color for the selected bottle");
			hydro_select_strap("Flex Strap - Long");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@aria-label='" + Color + "']");
			Common.clickElement("xpath", "//button[@aria-label='" + Color + "']");
			String productcolor = Common.findElement("xpath", "//label[@class='color-feature__selection__label']")
					.getText();
			System.out.println(productcolor);
			Thread.sleep(3000);
			Common.assertionCheckwithReport(productcolor.contains(Color), "validating the color selection for strap",
					"color should be select for the strap", "Sucessfully color has been selected for the strap",
					"failed to select the color for the selected strap");

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
			String boot = Common.findElement("xpath", "//h1[@class='menu__category-title']").getText();
			Common.assertionCheckwithReport(boot.contains("Boot"), "validating the color selection for bottle",
					"color should be select for the bottle", "Sucessfully color has been selected for the bottle",
					"failed to select the color for the selected bottle");
			Sync.waitElementPresent("xpath", "//button[@aria-label='" + Color + "']");
			Common.clickElement("xpath", "//button[@aria-label='" + Color + "']");
			String productcolor = Common.findElement("xpath", "//label[@class='color-feature__selection__label']")
					.getText();
			System.out.println(productcolor);
			Common.assertionCheckwithReport(productcolor.contains(Color), "validating the color selection for boot",
					"color should be select for the boot", "Sucessfully color has been selected for the boot",
					"failed to select the color for the selected boot");

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
			String Engraving = Common.findElement("xpath", "//h1[@class='menu__category-title']").getText();
			Common.assertionCheckwithReport(Engraving.contains("Engraving"), "validating the Engraving for the bottle",
					"Engraving should be select for the bottle",
					"Sucessfully Engraving  has been selected for the bottle",
					"failed to select the Engraving for the selected bottle");
			Sync.waitElementPresent("xpath", "//textarea[@class='text-engraving__input']");
			Common.findElement("xpath", "//textarea[@class='text-engraving__input']").sendKeys(engravingtext);
			String Text = Common.findElement("xpath", "//textarea[contains(@class,'text-engraving__input')]")
					.getAttribute("class");
			System.out.println(Text);
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
			Thread.sleep(4000);
			System.out.println(product);
			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
			Common.assertionCheckwithReport(name.contains(product),
					"validating the product should navigate to the PDP page",
					"When we click on the product is should navigate to the PDP page",
					"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the product should navigate to the PDP page",
					"When we click on the product is should navigate to the PDP page",
					"Unable to Navigate the product to the PDP page",
					Common.getscreenShot("Failed to Navigate the product to the PDP page"));
			Assert.fail();
		}

		try {
			List<WebElement> pdpcolors = Common.findElements("xpath",
					"//div[@id='product-options-wrapper']//div[contains(@class,'m-swatch m-swatch-group__optio')]");
			for (int i = 0; i < pdpcolors.size(); i++) {

				pdpcolors.get(i).click();
				Thread.sleep(4000);

				String clicked_Color = pdpcolors.get(i).getAttribute("aria-label");
				System.out.println(clicked_Color + "selected color");

				System.out.println(Common.getCurrentURL());
				Common.assertionCheckwithReport(Common.getCurrentURL().contains(clicked_Color),
						"Validating PDP page url Color name is passing to url",
						"select the color of product is " + clicked_Color + " it must pass throw url",
						" Selected color " + clicked_Color + "passing throw url",
						"Failed to clicked colr is passing throw URL" + clicked_Color);

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the color and url in PDP page",
					"When we click on the color the color name should reflect in url",
					"Unable to display thee color name in the url",
					Common.getscreenShot("Failed to display thee color name in the url"));

			Assert.fail();
		}

	}

	public void update_shoppingcart(String Dataset) {
		// TODO Auto-generated method stub
		String quantity = data.get(Dataset).get("Quantity");
		try {
			Common.clickElement("xpath", "//select[@class='a-form-elem a-select-menu']");
			Common.dropdown("xpath", "//select[@class='a-form-elem a-select-menu']", Common.SelectBy.VALUE, quantity);
			Common.clickElement("xpath", "//span[text()='Update']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String productquantity = Common.findElement("xpath", "//select[@class='a-form-elem a-select-menu']")
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

	public void addtocart_PLP(String Dataset) {
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
			Common.mouseOver("xpath", "//img[@alt='" + products + "']");
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
			Thread.sleep(4000);
			System.out.println(product);
			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
			Common.assertionCheckwithReport(name.contains(product),
					"validating the product should navigate to the PDP page",
					"When we click on the product is should navigate to the PDP page",
					"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");
			Common.scrollIntoView("xpath", "//button[contains(@class,'video')]");
			Common.clickElement("xpath", "//span[text()='Play video']");
			Sync.waitElementPresent("xpath", "//button[@title='Play Video']");
			Common.clickElement("xpath", "//button[@title='Play Video']");
			Sync.waitForLoad();
			String video = Common.findElement("xpath", "//button[contains(@class,'vjs-play-con')]")
					.getAttribute("title");
			System.out.println(video);
			Common.assertionCheckwithReport(video.equals("Pause"), "validating the video in PDP page",
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

	public void minicart_ordersummary_discount(String Dataset) {
		// TODO Auto-generated method stub.
		String expectedResult = "It should opens textbox input to enter discount.";
		try {
			Sync.waitElementPresent("xpath", "//button[@class='m-accordion__title']");
			Common.clickElement("xpath", "//button[@class='m-accordion__title']");

			Sync.waitElementPresent("xpath", "//input[@name='coupon_code']");
			if (Common.getCurrentURL().contains("preprod")) {
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
			if (Common.getCurrentURL().contains("preprod")) {
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
			String Subtotal = Common.getText("xpath", "//tr[@class='totals sub']//span[@class='price']").replace("$",
					"");
			Float subtotalvalue = Float.parseFloat(Subtotal);
			String shipping = Common.getText("xpath", "//tr[@class='totals shipping excl']//span[@class='price']")
					.replace("$", "");
			Float shippingvalue = Float.parseFloat(shipping);
			String Discount = Common.getText("xpath", "//tr[@class='totals discount']//span[@class='price']")
					.replace("$", "");
			Float Discountvalue = Float.parseFloat(Discount);

			String ordertotal = Common.getText("xpath", "//tr[@class='grand totals']//span[@class='price']")
					.replace("$", "");
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

	public void Account_page_Validation(String Dataset) throws Exception {
		// TODO Auto-generated method stub
		Sync.waitElementPresent("xpath", "//div[@class='m-account-nav__content']");
		Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
		Sync.waitElementPresent("xpath", "//a[text()='My Account']");
		Common.clickElement("xpath", "//a[text()='My Account']");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		if (Common.getCurrentURL().contains("preprod")) {
			String Accountlinks = data.get(Dataset).get("Account Links");
			String[] Account = Accountlinks.split(",");
			int i = 0;
			try {
				for (i = 0; i < Account.length; i++) {
					Sync.waitElementPresent("xpath",
							"//div[@class='content account-nav-content']//a[text()='" + Account[i] + "']");
					Common.clickElement("xpath",
							"//div[@class='content account-nav-content']//a[text()='" + Account[i] + "']");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title = Common.findElement("xpath", "//h1[@class='page-title-wrapper h2']").getText();
					System.out.println(title);
					Common.assertionCheckwithReport(title.contains(Account[i]) || title.contains("My Payment Methods") || title.contains("Newsletter Subscription"),
							"verifying Account page links " + Account[i],
							"user should navigate to the " + Account[i] + " page",
							"user successfully Navigated to the " + Account[i], "Failed click on the " + Account[i]);

				}
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
							"//div[@class='content account-nav-content']//a[text()='" + Account[i] + "']");
					Common.clickElement("xpath",
							"//div[@class='content account-nav-content']//a[text()='" + Account[i] + "']");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title = Common.findElement("xpath", "//h1[@class='page-title-wrapper h2']").getText();
					System.out.println(title);
					Common.assertionCheckwithReport(title.contains(Account[i]) || title.contains("My Payment Methods") || title.contains("Newsletter Subscription"),
							"verifying Account page links " + Account[i],
							"user should navigate to the " + Account[i] + " page",
							"user successfully Navigated to the " + Account[i], "Failed click on the " + Account[i]);

				}
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

			String originalprice = Common.getText("xpath", "//span[contains(@id,'old-price')]//span").replace("$", "");
			Float originalvalue = Float.parseFloat(originalprice);
			String Newprice = Common.getText("xpath", "//span[contains(@id,'product')]//span").replace("$", "");
			Float pricevalue = Float.parseFloat(Newprice);
			Thread.sleep(4000);
			float discount = originalvalue - (originalvalue * 65 / 100);
			String discountvalue = String.valueOf(discount).replace("$", "");
			Float value = Float.parseFloat(discountvalue);
			Common.assertionCheckwithReport(discountvalue.contains(Newprice),
					"verifying the discount for the employee discount ",
					"user should able to see the discount for the employee",
					"user successfully able to apply the discount", "Failed to apply the discount for the employee");

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
			List<WebElement> ListOfSubproducts = Common.findElements("xpath",
					"//div[@class='product-options-wrapper']//div[@class='m-swatch m-swatch-group__option swatch-option color']");
			System.out.println(ListOfSubproducts.size());
			for (int i = 0; i < ListOfSubproducts.size(); i++) {
				ListOfSubproducts.get(i).click();

				Thread.sleep(5000);
				String colorname = Common.getText("xpath",
						"//div[@class='swatch-opt']//span[contains(@class,'m-swatch')]");
				System.out.println(colorname);
				String color = Common
						.findElement("xpath", "(//div[@class='m-product-gallery__wrapper gallery-grid']//img)[1]")
						.getAttribute("alt").replace(product, "").replace(" - ", "");
				System.out.println(color);
				String s = Common.findElement("xpath", "//img[contains(@class,'m-product-gallery__media')]")
						.getAttribute("src");
				System.out.println(s);

				Common.assertionCheckwithReport(colorname.equals(color), "validating the  product add to the cart",
						"Product should be add to cart", "Sucessfully product added to the cart ",
						"failed to add product to the cart");

			}
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
			Sync.waitElementPresent(30, "xpath", "//div[@aria-label='" + color + "']");
			Common.clickElement("xpath", "//div[@aria-label='" + color + "']");
			Common.clickElement("xpath", "//button[@data-role='engrave-btn']");
			engraving_color();
			engraving_Text("Horizontal Text");
			Common.clickElement("xpath", "//button[@class='ATC__btn']");
			Sync.waitElementPresent("xpath", "//button[contains(text(),'Agree &')]");
			Common.clickElement("xpath", "//button[contains(text(),'Agree &')]");
			Thread.sleep(6000);
			Sync.waitImplicit(30);
			String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("You added"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");

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
			Common.assertionCheckwithReport(engrave.contains("focus-visible"),
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
			Sync.waitElementPresent(30, "xpath", "//div[@aria-label='" + color + "']");
			Common.clickElement("xpath", "//div[@aria-label='" + color + "']");
			Common.clickElement("xpath", "//button[@data-role='engrave-btn']");
			engraving_color();
			engraving_graphic("Graphic");
			Common.clickElement("xpath", "//button[@class='ATC__btn']");
			Sync.waitElementPresent("xpath", "//button[contains(text(),'Agree &')]");
			Common.clickElement("xpath", "//button[contains(text(),'Agree &')]");
			Thread.sleep(6000);
			Sync.waitImplicit(30);
			String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("You added"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");

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
			Sync.waitElementPresent(30, "xpath", "//span[text()='Graphic']");
			Common.clickElement("xpath", "//span[text()='Graphic']");
			int subproductsList = Common.findElements("xpath", "//div[@class='graphic-engraving__wrapper']//button")
					.size();
			for (int i = 0; i < subproductsList; i++) {
				int value = i + 1;
				List<WebElement> ListOfSubproducts = Common.findElements("xpath",
						"//div[@class='graphic-engraving__selections-container']//div[" + value + "]//button");

				WebElement Graphicnames = Common.findElement("xpath", "//span[@class='graphic-engraving__label']");

				WebElement Graphic = Common.findElement("xpath",
						"//div[@class='graphic-engraving__selections-container']//div[" + value + "]//button");

				for (int j = 0; j < ListOfSubproducts.size(); j++) {

					String attributevalue = ListOfSubproducts.get(j).getAttribute("disabled");

					if (attributevalue != null) {
					} else {

						if (ListOfSubproducts.get(j).getAttribute("class").contains("graphic-engraving__")
								|| ListOfSubproducts.get(j).getAttribute("class")
										.contains("graphic-engraving__selection__btn active")) {
							Thread.sleep(4000);
							System.out.println(ListOfSubproducts);
							ListOfSubproducts.get(j).click();
							Thread.sleep(4000);

							Common.assertionCheckwithReport(
									Graphicnames.getText().contains(Graphic.getAttribute("aria-label")),
									"Verifying the  swatch Graphics button " + Graphicnames.getText(),
									"after click graphic swatch button" + Graphicnames.getText()
											+ "it must dispaly swatch graphic image",
									"successfully graphic swatch image is dispalying",
									"Failed load graphic swatch image");
						}
					}
				}
			}
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
			Thread.sleep(2000);
			click_minicart();
			Thread.sleep(2000);
			Sync.waitElementPresent(30, "xpath", "//span[contains(@data-bind,'getEngravingText(item)')]");
			String engraving = Common.findElement("xpath", "//span[contains(@data-bind,'getEngravingText(item)')]")
					.getText();
			System.out.println(engraving);
			System.out.println(text);
			Common.assertionCheckwithReport(
					engraving.equals(Graphic) || engraving.equals(text)
							|| engraving.contains("Happy Birthday! Happy!!! Birthday!!"),
					"Validating the " + engraving + "for the bottle", engraving + "should apply for the bottle ",
					"Sucessfully" + engraving + "has been applied for the bottle",
					"failed apply the" + engraving + "for the bottle");
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

	public void Myhydro_Graphic(String Dataset) {
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
			Common.clickElement("xpath", "//span[text()='Customize Yours!']");
			Thread.sleep(3000);
			Myhydro_bottle("40 oz");
			hydro_bottle_color("Black");
			hydro_cap_color("White");
			hydro_strap_color("Black");
			hydro_boot_color("White");
			Myhydrographic("Graphic");
			Myhydro_quantity(Dataset);
			Common.clickElement("xpath", "//button[@class='ATC__btn']");
			Sync.waitElementPresent("xpath", "//button[contains(text(),'Agree &')]");
			Common.clickElement("xpath", "//button[contains(text(),'Agree &')]");
			Thread.sleep(6000);
			Sync.waitElementPresent(40, "xpath", "//div[@class='a-message__container-inner']");
			String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("You added"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");
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
			String Engraving = Common.findElement("xpath", "//h1[@class='menu__category-title']").getText();
			Common.assertionCheckwithReport(Engraving.contains("Engraving"), "validating the Engraving for the bottle",
					"Engraving should be select for the bottle",
					"Sucessfully Engraving  has been selected for the bottle",
					"failed to select the Engraving for the selected bottle");
			Sync.waitElementPresent(30, "xpath", "//span[text()='Graphic']");
			Common.clickElement("xpath", "//span[text()='Graphic']");
			int subproductsList = Common.findElements("xpath", "//div[@class='graphic-engraving__wrapper']//button")
					.size();
			for (int i = 0; i < subproductsList; i++) {
				int value = i + 1;
				List<WebElement> ListOfSubproducts = Common.findElements("xpath",
						"//div[@class='graphic-engraving__selections-container']//div[" + value + "]//button");

				WebElement Graphicnames = Common.findElement("xpath", "//span[@class='graphic-engraving__label']");

				WebElement Graphic = Common.findElement("xpath",
						"//div[@class='graphic-engraving__selections-container']//div[" + value + "]//button");

				for (int j = 0; j < ListOfSubproducts.size(); j++) {

					String attributevalue = ListOfSubproducts.get(j).getAttribute("disabled");

					if (attributevalue != null) {
					} else {

						if (ListOfSubproducts.get(j).getAttribute("class").contains("graphic-engraving__")
								|| ListOfSubproducts.get(j).getAttribute("class")
										.contains("graphic-engraving__selection__btn active")) {
							Thread.sleep(4000);
							System.out.println(ListOfSubproducts);
							ListOfSubproducts.get(j).click();
							Thread.sleep(4000);

							Common.assertionCheckwithReport(
									Graphicnames.getText().contains(Graphic.getAttribute("aria-label")),
									"Verifying the  swatch Graphics button " + Graphicnames.getText(),
									"after click graphic swatch button" + Graphicnames.getText()
											+ "it must dispaly swatch graphic image",
									"successfully graphic swatch image is dispalying",
									"Failed load graphic swatch image");
						}
					}
				}
			}
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
			Common.clickElement("xpath", "//span[text()='Customize Yours!']");
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
			Sync.waitElementPresent("xpath", "//button[contains(@aria-label,'ask a question')]");
			Common.clickElement("xpath", "//button[contains(@aria-label,'ask a question')]");
			Sync.waitElementPresent(30, "xpath", "//textarea[contains(@id,'yotpo_input_q')]");
			Common.textBoxInput("xpath", "//textarea[contains(@id,'yotpo_input_q')]", Question);
			Sync.waitElementPresent(30, "xpath", "//input[@name='display_name']");
			Common.textBoxInput("xpath", "//input[@name='display_name']", Name);
			Sync.waitElementPresent(30, "xpath", "//input[@name='email']");
			Common.textBoxInput("xpath", "//input[@name='email']", Email);
			Common.clickElement("xpath", "//input[@data-button-type='submit']");
			Thread.sleep(4000);
			String question = Common
					.findElement("xpath", "//div[@class='yotpo-thank-you']//span[contains(text(),'Thank you')]")
					.getText();
			System.out.println(question);
			Common.assertionCheckwithReport(question.contains("THANK YOU FOR POSTING A QUESTION!"),
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
			Sync.waitElementPresent(30, "xpath", "//div[@aria-label='" + color + "']");
			Common.clickElement("xpath", "//div[@aria-label='" + color + "']");
			Common.clickElement("xpath", "//button[@data-role='engrave-btn']");
			engraving_color();
			engraving_Text("Multiline Horizontal");
			product_quantity(Dataset);
			Common.clickElement("xpath", "//button[@class='ATC__btn']");
			Sync.waitElementPresent("xpath", "//button[contains(text(),'Agree &')]");
			Common.clickElement("xpath", "//button[contains(text(),'Agree &')]");
			Thread.sleep(6000);
			Sync.waitImplicit(30);
			String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("You added"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");

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
			Thread.sleep(4000);
			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
			System.out.println(name);
			Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"failed to Navigate to the PDP page");
			Common.clickElement("xpath", "//span[text()='Customize Yours!']");
			Thread.sleep(3000);
			Myhydro_bottle("40 oz");
			hydro_bottle_color("Black");
			hydro_cap_color("White");
			hydro_strap_color("Black");
			hydro_boot_color("White");
			Myhydro_Engraving("Multiline Horizontal");
			Myhydro_quantity(Dataset);
			Common.clickElement("xpath", "//button[@class='ATC__btn']");
			Sync.waitElementPresent("xpath", "//button[contains(text(),'Agree &')]");
			Common.clickElement("xpath", "//button[contains(text(),'Agree &')]");
			Thread.sleep(6000);
			Sync.waitElementPresent(40, "xpath", "//div[@class='a-message__container-inner']");
			String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("You added"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");

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

	public void bottles_validation(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("bottles");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()=' Bottles & Drinkware']");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				String breadcrumbs = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
				Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]),
						"verifying the header link " + Links[i] + "Under bottles and drinkware",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under bottles and drinkware",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
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
					Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
					Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
					Common.clickElement("xpath", "//span[text()=' Kitchenware']");
					Thread.sleep(3000);
					Sync.waitElementPresent("xpath",
							"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
					Common.clickElement("xpath",
							"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
					String breadcrumbs = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
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

				for (i = 0; i < ProdLink.length; i++) {
					Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
					Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
					Common.clickElement("xpath", "//span[text()=' Kitchenware']");
					Thread.sleep(3000);
					Sync.waitElementPresent("xpath",
							"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
					Common.clickElement("xpath",
							"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
					String breadcrumbs = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
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

	public void Accessories_validation(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("Accessories");
		String[] Links = names.split(",");
		int i = 0;
		try {

			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()=' Accessories']");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				String breadcrumbs = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
				System.out.println(title);
				System.out.println(Links[i]);
				Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Accessories",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Accessories",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}

	public void featured_validation(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("Featured links");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[text()=' Featured']");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				String breadcrumbs = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
				Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

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

	public void customize_shopall(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("shopall links");
		String[] Links = names.split(",");
		int i = 0;
		try {
			Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
			Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
			Sync.waitElementPresent("xpath", "//span[text()=' Customize']");
			Common.clickElement("xpath", "//span[text()=' Customize']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String cutomize = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
			Common.assertionCheckwithReport(cutomize.contains("Customize"), "verifying the customize under shop links",
					"user should navigate to the cutomize link", "user successfully Navigated to the customize",
					"Failed to navigate to the cutomize");
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//span[contains(text(),' " + Links[i] + "')]");
				Common.clickElement("xpath", "//span[contains(text(),' " + Links[i] + "')]");
				Common.clickElement("xpath", "//a[contains(@aria-label,'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				Common.assertionCheckwithReport(title.contains(Links[i]) || Common.getPageTitle().contains("Shop New Colors"),
						"verifying the header link " + Links[i] + "Under Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

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
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'Explore')]");
				Common.clickElement("xpath", "//span[contains(text(),'Explore')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//a[@class='ui-corner-all']//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath", "//a[@class='ui-corner-all']//span[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.assertionCheckwithReport(Common.getPageTitle().contains("We are Hydro Flask")
						|| Common.getPageTitle().contains("Frequently Asked Questions | Hydro Flask")
						|| Common.getPageTitle().contains("Contact") || Common.getPageTitle().contains("Let’s Go!")
						|| Common.getPageTitle().contains("Refill For Good")
						|| Common.getPageTitle().contains("Parks For All"), "verifying the explore links navigation",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
				Thread.sleep(3000);
				if (Common.getPageTitle().contains("Frequently Asked Questions | Hydro Flask")) {
					Common.clickElement("xpath", "//div[@class='nav-flex']//a[@id='top']");
				} else {
					Common.clickElement("xpath", "//img[@alt='Logo']");
				}

			}
			Sync.waitElementPresent("xpath", "//span[text()=' Customize']");
			Common.clickElement("xpath", "//span[text()=' Customize']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String cutomize = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
			Common.assertionCheckwithReport(cutomize.contains("Customize"), "verifying the customize under shop links",
					"user should navigate to the cutomize link", "user successfully Navigated to the customize",
					"Failed to navigate to the cutomize");

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
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Sync.waitElementPresent(30, "xpath", "//a[text()='My Account']");
			Common.clickElement("xpath", "//a[text()='My Account']");
			Thread.sleep(4000);
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
					Common.getscreenShot("Failed to Navigate to the MY account page after Clicking on my account CTA"));
			Assert.fail();
		}
		try {
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//a[text()='Stored Payment Methods']");
			Common.clickElement("xpath", "//a[text()='Stored Payment Methods']");
			Sync.waitPageLoad(30);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Payment Methods"),
					"validating the Navigation to the My Payment Methods page",
					"After Clicking on stored methods CTA user should be navigate to the My Payment Methods page",
					"Sucessfully User Navigates to the My Payment Methods page after clicking on the stored methods  CTA",
					"Failed to Navigate to the My Payment Methods page after Clicking on my stored methods  CTA");
			String card = Common.findElement("xpath", "//div[@class='message info empty']//span").getText();
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
						"//ul[@class='m-footer-links__list']//a[contains(text(),'" + Kustomerlinks[i] + "')]");
				Thread.sleep(3000);
				Common.findElement("xpath",
						"//ul[@class='m-footer-links__list']//a[contains(text(),'" + Kustomerlinks[i] + "')]");
				Common.clickElement("xpath",
						"//ul[@class='m-footer-links__list']//a[contains(text(),'" + Kustomerlinks[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(3000);
				Common.assertionCheckwithReport(
						Common.getPageTitle().contains(Kustomerlinks[i])
								|| Common.getPageTitle().contains("Frequently Asked Questions"),
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

	public void Footer_Links(String Dataset) {
		// TODO Auto-generated method stub
		String footer = data.get(Dataset).get("Footer Links");
		String[] footerlinks = footer.split(",");
		int i = 0;
		try {
			for (i = 0; i < footerlinks.length; i++) {
				Sync.waitElementPresent(30, "xpath",
						"//ul[@class='m-footer-links__list']//a[contains(text(),'" + footerlinks[i] + "')]");
				Thread.sleep(3000);
				Common.findElement("xpath",
						"//ul[@class='m-footer-links__list']//a[contains(text(),'" + footerlinks[i] + "')]");
				Common.clickElement("xpath",
						"//ul[@class='m-footer-links__list']//a[contains(text(),'" + footerlinks[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(3000);

				Common.assertionCheckwithReport(
						Common.getPageTitle().contains(footerlinks[i])
								|| Common.getPageTitle().contains("We are Hydro Flask")
								|| Common.getPageTitle().contains("Store Locator")
								|| Common.getPageTitle().contains("Corporate Purchasing")
								|| Common.getPageTitle().contains("Trade In"),
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

	public void Footer_validation(String Dataset) {
		// TODO Auto-generated method stub
		String footer = data.get(Dataset).get("Footer Links");
		String[] footerlinks = footer.split(",");
		int i = 0;
		try {
			for (i = 0; i < footerlinks.length; i++) {
				Sync.waitElementPresent(30, "xpath",
						"//ul[@class='m-footer-links__list']//a[contains(text(),'" + footerlinks[i] + "')]");
				Thread.sleep(3000);
				Common.findElement("xpath",
						"//ul[@class='m-footer-links__list']//a[contains(text(),'" + footerlinks[i] + "')]");
				Common.clickElement("xpath",
						"//ul[@class='m-footer-links__list']//a[contains(text(),'" + footerlinks[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(3000);
				String Bread = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
				Common.assertionCheckwithReport(
						Common.getPageTitle().contains(footerlinks[i])
								|| Common.getPageTitle().contains("We are Hydro Flask")
								|| Common.getPageTitle().contains("Store Locator")
								|| Common.getPageTitle().contains("Corporate Purchasing"),
						"validating the links navigation from footer Links",
						"After Clicking on" + footerlinks[i] + "it should navigate to the",
						footerlinks[i] + "Sucessfully Navigated to the" + footerlinks[i] + "Links",
						"Unable to Navigated to the" + footerlinks[i] + "Links");
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
			Common.clickElement("xpath", "//span[contains(text(),' Water ')]");
			String search = Common.findElement("xpath", "//span[contains(text(),' Water ')]").getText();
			for (int i = 0; i <= 10 - 6; i++) {
				List<WebElement> webelementslist = Common.findElements("xpath", "//span[@class='highlight-text']");

				String s = webelementslist.get(i).getText();
				System.out.println(s);
				Common.assertionCheckwithReport(s.contains("water"), "validating the filter reviews",
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
			Sync.waitElementPresent("xpath", "//p//a[contains(text(),'Privacy Policy')]");
			Common.clickElement("xpath", "//p//a[contains(text(),'Privacy Policy')]");
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

	public void image_button(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("shopall");
		String image = data.get(Dataset).get("Image Button Link");
		String[] Links = names.split(",");
		String[] Link = image.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				System.out.println(Links.length);
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//span[contains(text(),' " + Links[i] + "')]");
				Common.clickElement("xpath", "//span[contains(text(),' " + Links[i] + "')]");
				Sync.waitElementPresent("xpath","//div[@data-content-type='button-item']//span[text() ='" + Link[i] + "']");
				Common.clickElement("xpath","//div[@data-content-type='button-item']//span[text() ='" + Link[i] + "']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.assertionCheckwithReport(Common.getPageTitle().contains(Link[i]) || Common.getPageTitle().contains("Shop New Colors") ,
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

	public void clickontheproduct_and_image(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		String color = data.get(Dataset).get("Colorproduct");
		try {
			String minicartproduct = Common
					.findElement("xpath", "//a[@class='a-product-name' and @title='" + product + "']").getText();
			Common.clickElement("xpath", "//a[@class='a-product-name' and @title='" + product + "']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains(minicartproduct),
					"validating the product navigating to the PDP page",
					"The product Should be navigates to the PDP page", "Successfully product navigates to the PDP page",
					"Failed to Navigates Product to the PDP page");
			click_minicart();
			String minicartimage = Common.findElement("xpath", "//img[contains(@alt,'" + color + "')]")
					.getAttribute("alt");
			Common.clickElement("xpath", "//img[contains(@alt,'" + color + "')]");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains(color),
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
			String Freeshipping = Common
					.findElement("xpath", "//div[@class='m-progress-bar false']//div[contains(@class,'label-')]")
					.getText();
			Common.assertionCheckwithReport(Freeshipping.equals("Good news: your order will be delivered for Free."),
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

	public void minicart_product_close() {
		// TODO Auto-generated method stub
		try {

			Common.clickElement("xpath", "//span[contains(@class,'icon-cart__r')]");
			Sync.waitElementPresent("xpath", "//div[@class='modal-popup confirm _show']");
			String minicartpopup = Common.findElement("xpath", "//div[@class='modal-popup confirm _show']")
					.getAttribute("class");
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(minicartpopup.contains("_show"),
					"validating the popup when you click on delete", "The Popup should be displayed",
					"Successfully popup is displayed when we click on the delete button",
					"Failed to Display the popup");
			String popup = Common.findElement("xpath", "//h2[contains(text(),'Remove')]").getText();
			if (popup.equals("Remove Item")) {
				Common.clickElement("xpath", "//button[contains(@class,'a-btn a-btn--secondary acti')]");
			} else {
				Assert.fail();
			}
			Common.clickElement("xpath", "//span[contains(@class,'icon-cart__r')]");
			Sync.waitElementPresent("xpath", "//div[@class='modal-popup confirm _show']");
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(minicartpopup.contains("_show"),
					"validating the popup when you click on delete", "The Popup should be displayed",
					"Successfully popup is displayed when we click on the delete button",
					"Failed to Display the popup");
			if (popup.equals("Remove Item")) {

				Common.clickElement("xpath", "//button[@data-role='closeBtn' and @aria-label='Close']");
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
			Sync.waitElementPresent("xpath", "//a[text()='Group Customization']");
			Common.clickElement("xpath", "//a[text()='Group Customization']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Corporate Purchasing"),
					"Validating the Corporate Purchasing page navigation",
					"User should able to land on the Corporate Purchasing page",
					"successfully land to Corporate Purchasing page", "unable to load the  Corporate Purchasing page");

			List<WebElement> corporate = Common.findElements("xpath",
					"//div[contains(@class,'pagebuilder')]//a[contains(@class,'pagebuilder')]");
			System.out.println(corporate.size());
			for (int i = 0; i < corporate.size(); i++) {
				List<WebElement> Image = Common.findElements("xpath",
						"//div[contains(@class,'pagebuilder')]//a[contains(@class,'pagebuilder')]//span[@class='a-btn__label']");
				Thread.sleep(6000);
				name = Image.get(i).getText();
				Common.assertionCheckwithReport(
						name.equals("Register") || name.equals("Existing Customers")
								|| name.equals("ASI/PPAi Customers"),
						"Validating the" + name + "in the corporate purchasing",
						"User should able to see the " + name + "in the corporate purchasing page",
						"successfully " + name + "able to see in the coorparate purchasing page",
						"unable see the" + name + "in the coorparate purchasing page");
			}
			Sync.waitElementPresent("xpath", "//a[@class='pagebuilder-button-primary']//span[text()='Register']");
			Common.clickElement("xpath", "//a[@class='pagebuilder-button-primary']//span[text()='Register']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("New Account Inquiry Form Page"),
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

			Sync.waitElementPresent("xpath", "//span[text()='Write to Us']");
			Common.clickElement("xpath", "//span[text()='Write to Us']");

			Sync.waitElementPresent(40, "xpath", "//iframe[contains(@src,'https://hydroflask')]");
			Common.switchFrames("xpath", "//iframe[contains(@src,'https://hydroflask')]");

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

			Common.clickElement("xpath", "//div[@id='conversationAreYouAnAsiPpaiIndustryMem']");
			Thread.sleep(4000);
			Common.javascriptclickElement("xpath", "//div[@data-path='no']");

//			Sync.waitElementPresent("xpath", "//div[@id='conversationCustomOrder']");
//			Common.clickElement("xpath", "//div[@id='conversationCustomOrder']");
//			Thread.sleep(4000);
//			Common.javascriptclickElement("xpath", "//div[@data-path='no']");

			Sync.waitElementPresent("xpath", "//input[@name='conversationInHandDate']");

			Common.textBoxInput("xpath", "//input[@name='conversationInHandDate']", data.get(dataSet).get("date"));

			Common.clickElement("xpath", "//div[@id='conversationSellThruWebsite']");
			Thread.sleep(4000);
			Common.javascriptclickElement("xpath", "//div[@data-path='no']");

			Common.textBoxInput("xpath", "//input[@id='whatOfYourSalesComeThroughYourWebsite']",
					data.get(dataSet).get("salesPercentage"));

			Common.textBoxInput("xpath", "//input[@id='numberOfStores']", data.get(dataSet).get("numberOfStores"));

			Common.clickElement("xpath", "//div[@id='conversationStoreSize']");
			Common.clickElement("xpath", "//div[text()='" + storesize + "']");

			Common.textBoxInput("xpath", "//input[@id='annualRevenue']", data.get(dataSet).get("annualRevenue"));

			Common.textBoxInput("xpath", "//input[contains(@id,'WhatIsTheEstimatedNumberOfUnits')]",
					data.get(dataSet).get("NumberOfUnits"));

			Common.textBoxInput("xpath", "//input[@id='yearsInBusiness']", data.get(dataSet).get("yearsInBusiness"));

			Sync.waitElementPresent("xpath", "//input[@id='storeAddress']");
			Common.textBoxInput("xpath", "//input[@id='storeAddress']", data.get(dataSet).get("Street"));

			Common.textBoxInput("xpath", "//input[@name='suiteUnit']", data.get(dataSet).get("yearsInBusiness"));

			Sync.waitElementPresent("xpath", "//div[@id='conversationCountry']");
			Common.clickElement("xpath", "//div[@id='conversationCountry']");

			Sync.waitElementPresent("xpath", "//div[text()='" + country + "']");
			Common.clickElement("xpath", "//div[text()='" + country + "']");

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

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying email us from",
					"contact us form data enter without any error message", "Contact us page getting error ",
					Common.getscreenShotPathforReport("Contact us page"));
			Assert.fail();

		}

		Common.actionsKeyPress(Keys.PAGE_UP);
		String Text = Common.getText("xpath", "//div[@class='form-wrap']");
		expectedResult = "User gets confirmation under the same tab. It includes a reference number and email is sent to email provided. No validation errors.";
		Common.assertionCheckwithReport(Text.contains("Your submission was successful "),
				"verifying contact us confirmation message", expectedResult,
				"User gets confirmation under the same tab", "unable to load the confirmation form");

	}

	public void color_validation(String Dataset) {
		// TODO Auto-generated method stub
		String colorname = data.get(Dataset).get("Color");
		try {
			Sync.waitElementPresent("xpath", "//button[@aria-label='Colors']");
			Common.clickElement("xpath", "//button[@aria-label='Colors']");
			Thread.sleep(3000);
			String expand = Common.findElement("xpath", "//button[@aria-label='Colors']").getAttribute("aria-expanded");
			Common.assertionCheckwithReport(expand.contains("true"), "verifying the color bar has been expand",
					"When we click on the color it should be expand",
					"Successfully the color has been expand when we click on the colors ",
					"unable to expand the colors in PLP page");
			Sync.waitElementPresent("xpath",
					"//label[contains(@class,'ais-RefinementList')]//span[@data-color='" + colorname + "']");
			Common.clickElement("xpath",
					"//label[contains(@class,'ais-RefinementList')]//span[@data-color='" + colorname + "']");
			Thread.sleep(3000);
			String colorcount = Common.findElement("xpath",
					"//label[@class='ais-RefinementList-label checked']//span[@class='ais-RefinementList-count']")
					.getText();
			String bottlecount = Common.findElement("xpath", "//span[@class='a-toolbar-info__number']").getText();
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

	public void price_filter_validation() {
		// TODO Auto-generated method stub
		String name = "";
		try {
			Thread.sleep(4000);
			String lastvalue = Common.findElement("xpath", "//div[@class='value end active']").getText()
					.replace("$", "").replace(".00", "");
			System.out.println(lastvalue);
			Sync.waitElementPresent("xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='1']");
			WebElement price = Common.findElement("xpath",
					"//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='1']");
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
					Float namevlaue1 = Float.parseFloat(name1);
					if (namevlaue1 <= 20) {
						Thread.sleep(3000);
						String value1 = Common.findElement("xpath", "//span[@class='price-wrapper']")
								.getAttribute("data-price-amount");
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
					if (namevlaue <= 20) {
						Thread.sleep(3000);
						String value = Common.findElement("xpath", "//span[@class='price-wrapper']")
								.getAttribute("data-price-amount");
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
			Thread.sleep(4000);
			String seeoption = Common.findElement("xpath", "//fieldset[@class='fieldset m-product-card__cta']//span")
					.getText();
			System.out.println(seeoption);
			if (seeoption.equals("See options")) {
				Sync.waitElementPresent("xpath", "//label[@for='wishlist-select-all']");
				Common.clickElement("xpath", "//label[@for='wishlist-select-all']");
				Common.clickElement("xpath", "//span[text()='Remove From My Favorites']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String emptymessage = Common.findElement("xpath", "//form[@class='form-wishlist-items']//div//span")
						.getText();
				Common.assertionCheckwithReport(emptymessage.contains("You have no items in your favorites."),
						"validating the empty products in my favrioutes page ",
						"Products should not appear in the my favoritoes page",
						"Sucessfully product are empty in my favorites page",
						"failed to see empty products in my favorites page");
				search_product("Product");
				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
				Common.clickElement("xpath", "//img[@alt='" + products + "']");
				Sync.waitElementPresent(30, "xpath", "//button[@data-action='add-to-wishlist']");
				Common.clickElement("xpath", "//button[@data-action='add-to-wishlist']");
				Sync.waitPageLoad();
				Common.assertionCheckwithReport(Common.getPageTitle().equals("My Favorites"),
						"validating the Navigation to the My Favorites page",
						"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
						"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA",
						"Failed to Navigate to the My Favorites page after Clicking on My Favorites button");
				Common.scrollIntoView("xpath", "(//img[contains(@class,'m-produc')])[1]");
				Sync.waitElementPresent(30, "xpath", "(//img[contains(@class,'m-produc')])[1]");
				Common.mouseOver("xpath", "(//img[contains(@class,'m-produc')])[1]");

				Sync.waitElementPresent(30, "xpath", "//span[text()='Add to Cart']");
				Common.clickElement("xpath", "//span[text()='Add to Cart']");

				Sync.waitPageLoad();
				Thread.sleep(4000);
				String message1 = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
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
				Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
				Common.clickElement("xpath", "//span[contains(text(),' New Colors')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath",
						"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				String breadcrumbs = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
				Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

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

	public void Coolers_validation(String Dataset) {
		// TODO Auto-generated method stub

		String names = data.get(Dataset).get("Kitchen");
		String[] Links = names.split(",");
		int i = 0;
		try {
		         	Thread.sleep(4000);
		         	for (i = 0; i < Links.length; i++) {
					Sync.waitElementPresent("xpath", "//span[contains(text(),' Shop')]");
					Common.clickElement("xpath", "//span[contains(text(),' Shop')]");
					Common.clickElement("xpath", "//span[text()=' Coolers']");
					Thread.sleep(3000);
					Sync.waitElementPresent("xpath",
							"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
					Common.clickElement("xpath",
							"//li[contains(@class,'level2 ')]//a//span[contains(text(),'" + Links[i] + "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
					String breadcrumbs = Common.findElement("xpath", "(//a[@class='m-breadcrumb__text'])[2]").getText();
					System.out.println(title);
					System.out.println(Links[i]);
					Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) || Common.getPageTitle().contains(Links[i]),
							"verifying the header link " + Links[i] + "Under coolers",
							"user should navigate to the " + Links[i] + " page",
							"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
		         	}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link for coolers ",
					"user should navigate to the coolers page", "user Unable to Navigated to the coolers",
					Common.getscreenShot(" Failed to Navigated to the coolers"));
			Assert.fail();
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
		String oldemail = "";

		try {
			Sync.waitElementPresent(20, "xpath", "//span[text()='Edit']//parent::a");
			String name = Common.findElement("xpath", "//span[text()='Edit']//parent::a").getAttribute("aria-label");
			Common.clickElement("xpath", "//span[text()='Edit']//parent::a");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			String editaccount = Common.findElement("xpath", "//h1[@class='page-title-wrapper h2']").getText();
			Common.assertionCheckwithReport(name.contains(editaccount),
					"verifying the page navigated to the edit account ",
					"user should navigate to the Edit account page",
					"user successfully Navigated to the edit account page",
					"Failed to navigate to the edit account page");
			oldemail = Common.findElement("xpath", "//p[@class='text-email']").getText();
			Common.clickElement("xpath", "//button[@aria-label='Edit Account Email']//span[text()='Edit']");
			Common.textBoxInputAndVerify("xpath", "//input[@name='email']", Utils.getEmailid());
			Common.textBoxInput("xpath", "//input[@name='current_password']", data.get(Dataset).get("Password"));
			Common.clickElement("xpath", "//span[text()='Save Changes']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String errormessage = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			Common.assertionCheckwithReport(errormessage.contains("The password doesn't match this account"),
					"verifying the error message for the password",
					"user should get the error message if he enter the different password",
					"Successfully user gets the error message",
					"Failed to get the error message if the user gives an different password");
			Common.refreshpage();
			Sync.waitPageLoad();
			if (Common.getCurrentURL().contains("preprod")) {
				Common.clickElement("xpath", "//button[@aria-label='Edit Account Email']//span[text()='Edit']");
				Common.textBoxInputAndVerify("xpath", "//input[@name='email']", data.get(Dataset).get("Email"));
				Common.textBoxInput("xpath", "//input[@name='current_password']",
						data.get(Dataset).get("Confirm Password"));
			} else {
				Common.clickElement("xpath", "//button[@aria-label='Edit Account Email']//span[text()='Edit']");
				Common.textBoxInputAndVerify("xpath", "//input[@name='email']", data.get(Dataset).get("Prod Email"));
				Common.textBoxInput("xpath", "//input[@name='current_password']",
						data.get(Dataset).get("Confirm Password"));
			}
			Common.clickElement("xpath", "//span[text()='Save Changes']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String emailerrormessage = Common.findElement("xpath", "//div[@class='a-message__container-inner']")
					.getText();
			Common.assertionCheckwithReport(
					emailerrormessage.contains("A customer with the same email address already exists"),
					"verifying the error message for the existing account email",
					"user should get the error message if he enter the existing email",
					"Successfully user gets the error message",
					"Failed to get the error message if the user gives an existing email id");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.clickElement("xpath", "//button[@aria-label='Edit Account Email']//span[text()='Edit']");
			Common.textBoxInputAndVerify("xpath", "//input[@name='email']", Utils.getEmailid());
			String newemail = Common.findElement("xpath", "//input[@name='email']").getAttribute("value");
			Common.textBoxInput("xpath", "//input[@name='current_password']",
					data.get(Dataset).get("Confirm Password"));
			Common.clickElement("xpath", "//span[text()='Save Changes']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String successmessage = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			Common.assertionCheckwithReport(
					successmessage.contains("You saved the account information.")
							&& Common.getPageTitle().contains("Customer Login"),
					"verifying the Success message for the Change email",
					"user should get the success message and navigate back to the Login page",
					"Successfully user gets the success message and navigated to the Login page",
					"Failed to get the success message and unable to navigate to the login page");
			Sync.waitPageLoad();
			Common.textBoxInput("id", "email", newemail);
			Common.textBoxInput("id", "pass", data.get(Dataset).get("Confirm Password"));
			Common.clickElement("xpath", "//button[contains(@class,'action login')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"),
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
		return oldemail;
	}

	public void Change_to_Existingemail(String Dataset) {
		// TODO Auto-generated method stub
		try {

			String name = Common.findElement("xpath", "//span[text()='Edit']//parent::a").getAttribute("aria-label");
			Common.clickElement("xpath", "//span[text()='Edit']//parent::a");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String editaccount = Common.findElement("xpath", "//h1[@class='page-title-wrapper h2']").getText();
			Common.assertionCheckwithReport(name.contains(editaccount),
					"verifying the page navigated to the edit account ",
					"user should navigate to the Edit account page",
					"user successfully Navigated to the edit account page",
					"Failed to navigate to the edit account page");
			Common.clickElement("xpath", "//button[@aria-label='Edit Account Email']//span[text()='Edit']");
			Common.textBoxInputAndVerify("xpath", "//input[@name='email']", Dataset);
			Common.textBoxInput("xpath", "//input[@name='current_password']", "Lotuswave@1234");
			Common.clickElement("xpath", "//span[text()='Save Changes']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String successmessage = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			Common.assertionCheckwithReport(
					successmessage.contains("You saved the account information.")
							&& Common.getPageTitle().contains("Customer Login"),
					"verifying the Success message for the Change email",
					"user should get the success message and navigate back to the Login page",
					"Successfully user gets the success message and navigated to the Login page",
					"Failed to get the success message and unable to navigate to the login page");
			Sync.waitPageLoad();
			Common.textBoxInput("id", "email", Dataset);
			Common.textBoxInput("id", "pass", "Lotuswave@1234");
			Common.clickElement("xpath", "//button[contains(@class,'action login')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"),
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
			Sync.waitElementPresent(30, "xpath", "//button[@data-action='add-to-wishlist']");
			Common.clickElement("xpath", "//button[@data-action='add-to-wishlist']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
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
			Sync.waitElementPresent("xpath", "//a[text()='Newsletter Subscriptions']");
			Common.clickElement("xpath", "//a[text()='Newsletter Subscriptions']");
			String newsletter = Common.findElement("xpath", "//h1[@class='page-title-wrapper h2']").getText();
			Common.assertionCheckwithReport(newsletter.contains("Newsletter Subscription"),
					"validating the Navigation to the Newsletter Subscription page",
					"After Clicking on Newsletter Subscription CTA user should be navigate to the Newsletter Subscription page",
					"Sucessfully User Navigates to the Newsletter Subscription page after clicking on the Newsletter Subscription CTA",
					"Failed to Navigate to the Newsletter Subscription page after Clicking on Newsletter Subscription button");
			Common.switchFrames("xpath", "//iframe[@title='Hydroflask Website Preference Center']");
			WebElement Checkbox = Common.findElement("xpath", "//input[@id='field1']");
			if (!Checkbox.isSelected()) {
				Checkbox.click();
				Sync.waitElementPresent(20, "xpath", "//button[text()='UPDATE PREFERENCES']");
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
			Common.textBoxInput("xpath", "//input[@name='email']", Utils.getEmailid());
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
			Common.clickElement("xpath", "//span[text()='Customize Yours!']");
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
				Sync.waitElementPresent(40, "xpath", "//div[@class='a-message__container-inner']");
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
			Sync.waitElementPresent(30, "xpath", "//span[text()='Customize Yours!']");
			Common.clickElement("xpath", "//span[text()='Customize Yours!']");
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
			Common.clickElement("xpath", "//span[text()='Customize Yours!']");
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
			Thread.sleep(4000);
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
		
	}
		


