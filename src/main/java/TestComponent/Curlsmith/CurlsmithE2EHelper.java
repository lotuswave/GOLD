package TestComponent.Curlsmith;

import static org.testng.Assert.fail;

import java.io.*;
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

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
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
  
public class CurlsmithE2EHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public CurlsmithE2EHelper(String datafile, String sheetname) {

		excelData = new ExcelReader(datafile, sheetname);
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("curlsmith");
			report.createTestcase("CurlsmithTestCases");
		} else {
			this.report = Utilities.TestListener.report;
		}

	}

	public void verify_Homepage() {
		// TODO Auto-generated method stub

		try {
			Sync.waitPageLoad();
			Thread.sleep(2000);
			AcceptPrivacy();
			int size = Common.findElements("xpath", "//a[@class='site-header__logo-link']").size();
			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Curlsmith USA Dev"),
					"validating the Navigation to the Home page", "System directs the user to the Homepage",
					"Sucessfully user navigates to the home page", "Failed to navigate to the homepage");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Navigation to the Home page",
					"System directs the user to the Homepage", " user unable navigates to the home page",
					"Failed to navigate to the homepage");
			Assert.fail();
		}

	}
	
	
	public void AcceptPrivacy() {
		try {

			int size = Common.findElements("id", "truste-consent-button").size();
			if (size > 0) {

				Sync.waitElementPresent("id", "truste-consent-button");
				Common.clickElement("id", "truste-consent-button");
			} else {

				System.out.println("Accpect Privacy not displayed");
			}
		} catch (Exception | Error e) {

		}
	}

	public void search_product(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		System.out.println(product);
		try {
			Common.clickElement("xpath", "//span[text()='Search']//parent::a");
			String open = Common.findElement("xpath", "//div[@class='site-header__search']//form").getAttribute("class");
			Thread.sleep(4000);
			Common.assertionCheckwithReport(open.contains("is-active"), "User searches using the search field",
					"User should able to click on the search button", "Search expands to the full page",
					"Sucessfully search bar should be expand");
			WebElement serachbar = Common.findElement("xpath", "//input[@class='site-header__search-input']");
			serachbar.sendKeys(product);
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
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

	public void Configurable_addtocart(String Dataset) {
		// TODO Auto-generated method stub
		
		String products = data.get(Dataset).get("Products");
		String Productsize= data.get(Dataset).get("size");
//		String scent=data.get(Dataset).get("scent");
		System.out.println(Productsize);
		System.out.println(products);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[contains(@class,'grid-product__image lazyautosizes')]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@class,'grid-product__image lazyautosizes')]");

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
//				Sync.waitElementPresent("xpath", "//div[@data-option-label='" + scent + "']");
//				Common.clickElement("xpath", "//div[@data-option-label='" + scent + "']");
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
			}
			else
			{
				
			Sync.waitPageLoad(30);
			Thread.sleep(3000);
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + Productsize + "']");
			Common.javascriptclickElement("xpath", "//img[@alt='" + Productsize + "']");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//label[contains(text(),'" + Productsize + "')]");
			Common.clickElement("xpath", "//label[contains(text(),'" + Productsize + "')]");
			Thread.sleep(8000);
			String size=Common.findElement("xpath", "(//div[@class='variant-input']/label)[1]").getText().toUpperCase();
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
			
			Sync.waitElementPresent("xpath", "//button[contains(@id,'AddToCart')]");
			Common.clickElement("xpath", "//button[contains(@id,'AddToCart')]");
			Sync.waitPageLoad();
			Thread.sleep(6000);
//			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//					.getAttribute("data-ui-id");
//			System.out.println(message);
//			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
//					"Product should be add to cart", "Sucessfully product added to the cart ",
//					"failed to add product to the cart");
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

	private void product_quantity(String Dataset) {
		// TODO Auto-generated method stub
		String Quantity = data.get(Dataset).get("Quantity");
		System.out.println(Quantity);
		
		try {
			Common.findElement("xpath", "//div[@class='js-qty__wrapper']");
			Thread.sleep(5000);
			if(Quantity.equals("10+"))
			{
				
				Sync.waitElementPresent("xpath","//select[@name='qty']");
//				Common.clickElement("xpath","//select[@name='qty']");
//				Common.dropdown("xpath", "//select[@name='qty']", Common.SelectBy.VALUE, Quantity);
				Common.clickElement("xpath", "//select[@name='qty']//option[@value='10']");
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
				WebElement clear=Common.findElement("xpath", "//input[@class='js-qty__num']");
				clear.sendKeys(Keys.CONTROL+"a");
				Thread.sleep(2000);
				clear.sendKeys(Keys.DELETE);
				Thread.sleep(4000);
				Common.textBoxInput("xpath", "//input[@class='js-qty__num']", Quantity);
				Thread.sleep(4000);
				String value = Common.findElement("xpath", "//input[@class='js-qty__num']").getAttribute("value");
				System.out.println(value);
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
		
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//div[@class='cart__item-sub cart__item-row']//div[2]");
			String minicart = Common.findElement("xpath", "//div[@class='cart__item-sub cart__item-row']//div[2]").getText();
			System.out.println(minicart);
			Sync.waitElementPresent(30, "xpath", "//button[@class='btn cart__checkout']");
			Common.clickElement("xpath", "//button[@class='btn cart__checkout']");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(
					 Common.getCurrentURL().contains("checkout"),
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

	public void addDeliveryAddress_Guestuser(String dataSet) throws Exception {
		String address = data.get(dataSet).get("Street");
		String symbol = data.get(dataSet).get("Symbol");

		try {
			Sync.waitElementPresent("xpath", "//input[@name='email']");
			Common.textBoxInput("xpath", "//input[@name='email']", data.get(dataSet).get("Email"));
			Sync.waitElementPresent("xpath", "//input[@name='firstName' and @placeholder]");
			Common.textBoxInput("xpath", "//input[@name='firstName' and @placeholder]",
					data.get(dataSet).get("FirstName"));
			Sync.waitElementPresent("xpath", "//input[@name='lastName' and @placeholder]");
			Common.textBoxInput("xpath", "//input[@name='lastName' and @placeholder]",
					data.get(dataSet).get("LastName"));
			Sync.waitElementPresent("xpath", "//input[@name='address1' and @placeholder]");
			Common.textBoxInput("xpath", "//input[@name='address1' and @placeholder]", address);
			Sync.waitElementPresent("xpath", "//input[@name='city' and @placeholder]");
			Common.textBoxInput("xpath", "//input[@name='city' and @placeholder]", data.get(dataSet).get("City"));
			Sync.waitElementPresent("xpath", "//select[@id='Select1']");
			Common.dropdown("xpath", "//select[@id='Select1']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			Sync.waitElementPresent("xpath", "//input[@name='postalCode' and @placeholder]");
			Common.textBoxInput("xpath", "//input[@name='postalCode' and @placeholder]",
					data.get(dataSet).get("postcode"));
			Common.clickElement("xpath", "//button[@type='submit']//span[text()='Continue to shipping']");
			Sync.waitPageLoad();
			Thread.sleep(2000);

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
}


			


		



			





		


		



			





		

	