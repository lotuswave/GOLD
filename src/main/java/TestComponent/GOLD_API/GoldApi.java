package TestComponent.GOLD_API;
   
import static org.testng.Assert.fail;

import java.awt.Robot;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;

import com.sun.glass.events.KeyEvent;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Common.SelectBy;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.Utils;
import groovyjarjarantlr.CommonAST;
import groovyjarjarantlr.CommonASTWithHiddenTokens;
import groovyjarjarantlr4.v4.parse.ANTLRParser.action_return;

public class GoldApi {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	private String String;
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public GoldApi(String datafile, String sheetname) {

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
	
	public void verifingHomePage() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			if(Common.getCurrentURL().contains("osprey.com/gb/"))
			{
				Close_Geolocation();
			     acceptPrivacy();
				int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
				System.out.println(size);
				System.out.println(Common.getPageTitle());
				Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Osprey"),
						"validating store logo on the homwpage",
						"System directs the user to the Homepage and store logo should display",
						"Sucessfully user navigates to the home page and logo has been displayed",
						"Failed to navigate to the homepage and logo is not displayed");
			}
			else if(Common.getCurrentURL().contains("stage3") || Common.getCurrentURL().contains("preprod"))
			{
				close_add();
				int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
				System.out.println(size);
				System.out.println(Common.getPageTitle());
				Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Home page"),
						"validating store logo on the homwpage",
						"System directs the user to the Homepage and store logo should display",
						"Sucessfully user navigates to the home page and logo has been displayed",
						"Failed to navigate to the homepage and logo is not displayed");
			}
			else if(Common.getCurrentURL().contains("preprod.osprey.com/gb/"))
			{
				
				 acceptPrivacy();
				 Close_Geolocation();
				int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
				System.out.println(size);
				System.out.println(Common.getPageTitle());
				Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Home page"),
						"validating store logo on the homwpage",
						"System directs the user to the Homepage and store logo should display",
						"Sucessfully user navigates to the home page and logo has been displayed",
						"Failed to navigate to the homepage and logo is not displayed");
			}
			else
			{
//			Close_Geolocation();
//			close_add();
		     acceptPrivacy();
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			System.out.println(size);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Osprey") || size > 0,
					"validating store logo on the homwpage",
					"System directs the user to the Homepage and store logo should display",
					"Sucessfully user navigates to the home page and logo has been displayed",
					"Failed to navigate to the homepage and logo is not displayed");
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating store logo on the homwpage",
					"System directs the user to the Homepage and store logo should display",
					"Unable to navigate to the homepage and logo is not displayed",
					"Failed to navigate to the homepage and logo is not displayed");

			Assert.fail();
		}

	}
	
	
	
	
	public void Login_Account(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Common.openNewTab();
			Common.oppenURL("https://identity.getpostman.com/login?addAccount=1");
			Thread.sleep(4000);
			if (Common.getCurrentURL().contains("getpostman")) {
				Sync.waitPageLoad();
				Common.textBoxInput("id", "username", data.get(dataSet).get("UserName"));
			} 
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//button[contains(@class,'primary sign-in-btn')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("Postman"),
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
	
	
	public void WorkSpace() {
		
		try {
			int  Recent_workSpace= Common.findElements("xpath", "//h2[contains(text(),'Recently visited workspaces')]").size();
	        System.out.println(Recent_workSpace);
	        if (Recent_workSpace > 0) {
	           
	            Thread.sleep(4000);
	            Sync.waitElementPresent("xpath", "//span[contains(text(),'My Workspace')]");
	            Common.clickElement("xpath", "//span[contains(text(),'My Workspace')]");
	        }
	        
	        
	        else {

	           
	            Sync.waitElementPresent("xpath", "//span[contains(text(),'My Workspace')]");
	            Common.clickElement("xpath", "//span[contains(text(),'My Workspace')]");
	          
	            }
	        Sync.waitElementPresent("xpath", "//div[text()='Integration2']");
	        Common.clickElement("xpath", "//div[text()='Integration2']");
		
	} catch (Exception e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To validate the user Navigate to Home page after successfull login",
				"After clicking on the signin button it should navigate to the Home page",
				"Unable to navigate the user to the home after clicking on the SignIn button",
				Common.getscreenShotPathforReport("Failed to signIn and not navigated to the Home page "));

		Assert.fail();
	}

}		
		
	public void Close_Geolocation() {
		// TODO Auto-generated method stub
		try {
			
			Sync.waitElementPresent("xpath", "(//button[@data-role='closeBtn'])[2]");
			Common.clickElement("xpath", "(//button[@data-role='closeBtn'])[2]");
			
		
	}catch(Exception | Error e)
	{
		e.printStackTrace();
		Assert.fail();
	}
	

	}
	
	public void acceptPrivacy() {

		Common.clickElement("id", "truste-consent-button");
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

            Common.switchFrames("xpath", "//div[@class='preloaded_lightbox']/iframe");
            Sync.waitElementPresent("xpath", "//button[contains(@aria-label,'Close') and @id='button3']");
            Common.clickElement("xpath", "//button[contains(@aria-label,'Close') and @id='button3']");
            Common.switchToDefault();
            }

 

    }

	public void select_Website(String Dataset) {
		// TODO Auto-generated method stub
		String website=data.get(Dataset).get("preprod"); 
		try
		{
			Common.clickElement("xpath", "//div[@title='"+ website +"']");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			Assert.fail();
		}
		
	}

	public String Generate_Token(String Dataset) {
		// TODO Auto-generated method stub
		
		String API=data.get(Dataset).get("API");
		System.out.println(API);
		String Token_Url=data.get(Dataset).get("Token");
		System.out.println(Token_Url);
		String Token_name=data.get(Dataset).get("Details");
		System.out.println(Token_name);
		String remove=data.get(Dataset).get("Remove");
		System.out.println(remove);
		String fulltoken="";
		try
		{
			Thread.sleep(4000);
			Sync.waitElementPresent(30,"xpath", "//div[@title='"+ API +"']");
			Common.clickElement("xpath", "//div[@title='"+ API +"']");
			Thread.sleep(8000);
			Sync.waitForLoad();
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("API KEY"),
					"ATo validate it is navigated to the API page",
					"After clicking on the API  key button it should navigate to api key",
					"user Sucessfully navigate to the API Key page",
					"Failed to Nvaigate to the API key page");
			Thread.sleep(3000);
			Common.textBoxInputClear("xpath", "//div[contains(@class,'public-DraftSt')]");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//div[contains(@class,'public-DraftSt')]");
			Common.findElement("xpath", "//div[contains(@class,'public-DraftSt')]").sendKeys(Token_Url);
		/*	Sync.waitElementPresent(30, "xpath", "//span[text()='Body']");
			Common.clickElement("xpath", "//span[text()='Body']");
			Thread.sleep(4000);
			WebElement clear=Common.findElement("xpath", "//div[@class='view-lines']");
			clear.click();
			clear.sendKeys(Keys.CONTROL + "a"+ Keys.DELETE); 
			clear.sendKeys(Keys.DELETE);
			Common.findElement("xpath", "//div[@class='view-lines']").sendKeys(Token_name);
//			Common.textBoxInput("xpath", "//div[@class='view-lines']", Token_name);
			Thread.sleep(4000);*/
			Common.clickElement("xpath", "//span[text()='Send']");
			Thread.sleep(6000);
			String token1=Common.getText("xpath", "((//div[@class='view-line'])[1]//span)[2]").replace(remove, "");
			String token2=Common.getText("xpath", "((//div[@class='view-line'])[1]//span)[3]");
			String token3=Common.getText("xpath", "((//div[@class='view-line'])[1]//span)[4]");
//			String token4=Common.getText("xpath", "((//div[@class='view-line'])[2]//span)[2]");
			Sync.waitElementPresent(30, "xpath", "((//div[@class='view-line'])[2]//span)[3]");
			String token5=Common.getText("xpath", "((//div[@class='view-line'])[2]//span)[3]").replace(remove, "");
			fulltoken=token1+token2+token3+token5;
		
			System.out.println(fulltoken);
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			
			Assert.fail();
			
		}
		return fulltoken;
		
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
		String Productsize = data.get(Dataset).get("Size");
		System.out.println(products);
		System.out.println(Productsize);
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
			
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.clickElement("xpath", "//img[@alt='" + products + "']");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			Sync.waitElementPresent("xpath", "//div[@data-option-label='" + productcolor + "']");
			Common.clickElement("xpath", "//div[@data-option-label='" + productcolor + "']");
			Sync.waitElementPresent("xpath", "//div[@data-option-label='" + Productsize + "']");
			Common.clickElement("xpath", "//div[@data-option-label='" + Productsize + "']");
			Sync.waitPageLoad(30);
			Thread.sleep(6000);
			Common.scrollIntoView("xpath", "//div[@class='m-product-overview__info-top']//h1");
			Sync.waitElementVisible(30, "xpath", "//div[@class='m-product-overview__info-top']//h1");
			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
			Common.assertionCheckwithReport(name.contains(products) || Common.getPageTitle().contains(products),
					"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
					"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
			product_quantity(Dataset);
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			Sync.waitPageLoad();
			Thread.sleep(6000);
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

	public void product_quantity(String Dataset) {
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
			Thread.sleep(3000);
			Sync.waitElementPresent(30, "xpath", "//button[@id='top-cart-btn-checkout']");
			Common.clickElement("xpath", "//button[@id='top-cart-btn-checkout']");
			Sync.waitPageLoad();
			Thread.sleep(7000);
			Sync.waitElementPresent(30, "xpath", "//strong[@role='heading']");
			String checkout = Common.findElement("xpath", "//span[contains(@data-bind,'text: getC')]").getText();
			System.out.println(checkout);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(
					/*checkout.equals(minicart) &&*/ Common.getCurrentURL().contains("checkout/#shipping") || Common.getCurrentURL().contains("/checkout/#payment") ,
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
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.PAGE_UP);
			Sync.waitElementPresent("xpath", "//a[contains(@class,'c-mini-cart__btn')]");
			Common.clickElement("xpath", "//a[contains(@class,'c-mini-cart__btn')]");
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
	
	public void addDeliveryAddress_Guestuser(String dataSet) throws Exception {
		String address = data.get(dataSet).get("Street");

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
			// Common.clickElement("xpath", "//label[@for='stripe_payments']");
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
				Common.clickElement("xpath", "//button[@class='a-btn a-btn--tertiary']");
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

					Common.clickElement("xpath", "//button[@class='action primary checkout']");
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

					Common.clickElement("xpath", "//button[@class='action primary checkout']");
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
		int placeordercount = Common.findElements("xpath", "//button[@class='action primary checkout']").size();
		if (placeordercount > 1) {
			Thread.sleep(4000);

			Common.clickElement("xpath", "//button[@class='action primary checkout']");
			Common.refreshpage();
		}

		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);

		if (!url.contains("stage") && !url.contains("preprod")) {
		}

		else {
			try {
				String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();

				// Tell_Your_FriendPop_Up();
				int sizes = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();
				Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!")|| Common.getCurrentURL().contains("success")&& sizes>0 ,
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
	
	public void Orders(String Dataset) {
		// TODO Auto-generated method stub
		try {
			String filter=Common.findElement("xpath", "//div[contains(@class,'admin__data-grid-filters-current')]").getAttribute("class");
			if(filter.contains("_show"))
			{
			Clearfilter();
			Sync.waitElementPresent("xpath", "//button[@data-action='grid-filter-expand']");
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			Common.clickElement("xpath", "//input[@name='increment_id']");
			Common.textBoxInput("xpath", "//input[@name='increment_id']", Dataset);
			Common.actionsKeyPress(Keys.ENTER);
			}
			else
			{
				Sync.waitElementPresent("xpath", "//button[@data-action='grid-filter-expand']");
				Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
				Common.clickElement("xpath", "//input[@name='increment_id']");
				Common.textBoxInput("xpath", "//input[@name='increment_id']", Dataset);
				Common.actionsKeyPress(Keys.ENTER);
			}
			Sync.waitPageLoad();
			String orderrcords = Common.findElement("xpath", "//div[@class='admin__control-support-text']").getText();
			System.out.println(orderrcords);
			if (orderrcords.equals("0  records found")) {
				Common.clickElement("xpath", "//span[text()='Reset Filter']");

			} else {
				String ordernumber = Common.findElement("xpath", "//input[@name='increment_id']").getAttribute("value");
				System.out.println(ordernumber);
				String number = Dataset;
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
	public void selectshippingmethod(String Dataset) {
		// TODO Auto-generated method stub4
		String method = data.get(Dataset).get("methods");
		System.out.println(method);

		try {
			Thread.sleep(3000);
			int size = Common.findElements("xpath", "//label[@class='a-radio-button__label']").size();
			System.out.println(size);
			if (size > 0  ) {
				// Sync.waitElementPresent(30, "xpath", "//td[contains(text(),'" + method +
				// "')]");
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
			Thread.sleep(4000);
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
	
	
	public void Admin_signin(String dataSet) {
		String magentoURL=data.get(dataSet).get("Magento");
    Common.oppenURL(magentoURL);
        try {
            if (Common.getCurrentURL().contains("preprod")) {
                Sync.waitElementClickable("xpath", "//a[@class='action login primary']");
                Common.javascriptclickElement("xpath", "//a[@class='action login primary']");
            } 
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
            Thread.sleep(8000);
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

	public String getorder_id(String Dataset) {
		// TODO Auto-generated method stub
		
		String order_Url=data.get(Dataset).get("Magento");
		String id="";
		try
		{
			String orderrcords = Common.findElement("xpath", "//div[@class='admin__control-support-text']").getText();
			System.out.println(orderrcords);
			if (orderrcords.equals("1 records found"))
			{
				Sync.waitElementPresent(30, "xpath", "//a[text()='View']");
				Common.clickElement("xpath", "//a[text()='View']");
				Thread.sleep(4000);
				Common.assertionCheckwithReport(Common.getPageTitle().contains("Orders / Operations /"),
	                    "To Validate the navigated to the orders page",
	                    "After clicking on view button should navigate to the orders page",
	                    "Admin Sucessfully navigate to the orders page after clicking on the view button",
	                    "Failed to Navigate to the order page after clicking on the view button");
				 id=Common.getCurrentURL().replace(order_Url, "");
				
			}
			
			else
			{
				Assert.fail();
			}

		}
	
		catch(Exception | Error e)
		{
			e.printStackTrace();
			Assert.fail();
		}
		return id;
	}

	public void Get_order(String Dataset) {
		// TODO Auto-generated method stub
		String Order=data.get(Dataset).get("Order");
		String Order_name=data.get(Dataset).get("Magento");
		System.out.println(Order_name);
		try
		{
		   Sync.waitElementPresent("xpath", "//div[@title='"+ Order +"']");
		   Common.clickElement("xpath", "//div[@title='"+ Order +"']");
		   Thread.sleep(8000);
			Sync.waitForLoad();
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("Get Order"),
					"To validate it is navigated to the Get Order page",
					"After clicking on the Get Order  key button it should navigate to Get Order",
					"user Sucessfully navigate to the Get Order page",
					"Failed to Navigate to the Order page");
			Common.clickElement("xpath", "(//div[contains(@class,'public-DraftSt')])[1]");
			Thread.sleep(4000);
			Common.textBoxInputClear("xpath", "(//div[contains(@class,'public-DraftSt')])[1]");
			Thread.sleep(4000);
			Common.findElement("xpath", "(//div[contains(@class,'public-DraftSt')])[1]").sendKeys(Order_name);
			Thread.sleep(4000);
			String get=Common.getText("xpath", "//span[@data-text='true']");
			System.out.println(get);
			Common.assertionCheckwithReport(get.equals(Order_name),
                    "To Validate the order url entered in the text box",
                    "After clicking on textbox order url should be entered",
                    "Successfully order url has been entered in the textbox",
                    "Failed to Enter the order URL in the text box");
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate it is navigated to the Get Order page",
					"After clicking on the Get Order  key button it should navigate to Get Order",
					"Unable to Navigate to the Order page",
                    "Failed to Navigate to the Order page");
			Assert.fail();
		}
		
	}

	public void order_Url(String Dataset) {
		// TODO Auto-generated method stub
		try
		{
			Sync.waitElementPresent(30, "xpath","//div[contains(@class,'public-DraftSt')]");
			Common.findElement("xpath", "//div[contains(@class,'public-DraftSt')]").sendKeys(Dataset);
			String get=Common.getText("xpath", "//span[@data-text='true']");
			System.out.println(get);
			Common.assertionCheckwithReport(get.contains(Dataset),
                    "To Validate the order url entered in the text box",
                    "After clicking on textbox order url should be entered",
                    "Successfully order url has been entered in the textbox",
                    "Failed to Enter the order URL in the text box");
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To Validate the order url entered in the text box",
                    "After clicking on textbox order url should be entered",
                    "Unbale to enter order url in the text box",
                    "Failed to Enter the order URL in the text box");
			Assert.fail();
			
		}
	}

	public void Ship_Authorization(String Dataset) {
		// TODO Auto-generated method stub
		try
		{
		
			Sync.waitElementPresent("xpath", "//div[@title='Ship Items']");
			Common.clickElement("xpath", "//div[@title='Ship Items']");
			Sync.waitElementPresent("xpath", "//div[@class='tab-text-wrapper']//span[text()='Headers']");
			Common.clickElement("xpath", "//div[@class='tab-text-wrapper']//span[text()='Headers']");
			String Authorization=Common.getText("xpath", "(//span[contains(@class,'key-value')])[3]");
			System.out.println(Authorization);
			Common.assertionCheckwithReport(Authorization.contains("Authorization"),
                    "To Validate navigated to the headers in the get order",
                    "After clicking on header it should navigate to the headers",
                    "Successfully Navigated to the headers",
                    "Failed to Navigated to the header");
			Thread.sleep(5000);
			Sync.waitElementPresent(30,"xpath", "(//span[contains(@class,'key-value')])[4]");
			WebElement clear=Common.findElement("xpath", "(//span[contains(@class,'key-value')])[4]");
			Thread.sleep(4000);
			clear.click();
			Thread.sleep(3000);
			WebElement clear1=Common.findElement("xpath", "//div[@data-testid='auto-suggest']");
			String Value=clear1.getAttribute("data-testid");
			System.out.println(Value);
			Common.maximizeImplicitWait();
			Thread.sleep(4000);
			Common.JSTexboxclear("xpath", "(//div[@class='key-value-form-column'])[8]");
			Thread.sleep(4000);
			Common.javascriptTextBoxInput("xpath", "(//div[@class='key-value-form-column'])[8]", "Bearer "+Dataset);
//			Thread.sleep(4000);
//			Common.clickElement("xpath", "(//span[@class='key-value-cell__multiline__item'])[3]");
//			Thread.sleep(4000);

		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			
			Assert.fail();
		}
		
	}


	public HashMap<String, String> getorderDetails() throws Exception {
		// TODO Auto-generated method stub
		
		  HashMap<String,String> GetOrderDetails=new HashMap<String,String>();
		  
		  Thread.sleep(4000);
//		  Common.clickElement("xpath", "//div[@class='btn btn-secondary btn-small']");
//		  Thread.sleep(4000);
//		  Common.clickElement("xpath", "//span[text()='HTML']");
		try
		{
			Common.clickElement("xpath", "//div[@class='view-line']");
			Common.scrollIntoView("xpath", "(//span[@class='mtk6'])[2]");
			Sync.waitElementPresent(30, "xpath", "(//span[@class='mtk6'])[2]");
			String customer_email=Common.findElement("xpath", "(//span[@class='mtk6'])[2]").getText();
			System.out.println(customer_email);
			GetOrderDetails.put("customer email", customer_email);
			
			Sync.waitElementPresent(30, "xpath", "(//span[@class='mtk6'])[3]");
			String customer_Firstname=Common.findElement("xpath", "(//span[@class='mtk6'])[3]").getText();
			System.out.println(customer_Firstname);
			GetOrderDetails.put("customer_Firstname", customer_Firstname);
			
			Sync.waitElementPresent(30, "xpath", "(//span[@class='mtk6'])[4]");
			String customer_Lastname=Common.findElement("xpath", "(//span[@class='mtk6'])[4]").getText();
			System.out.println(customer_Lastname);
			GetOrderDetails.put("customer_Lastname", customer_Lastname);
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			Assert.fail();
		}
		return GetOrderDetails;
	}

	public void Ship_Items(String Dataset) {
		// TODO Auto-generated method stub
		String Ship=data.get(Dataset).get("Order");
		String ship_name=data.get(Dataset).get("Magento");
		System.out.println(ship_name);
		try
		{
		   Sync.waitElementPresent("xpath", "//div[@title='"+ Ship +"']");
		   Common.clickElement("xpath", "//div[@title='"+ Ship +"']");
		   Thread.sleep(8000);
			Sync.waitForLoad();
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("Ship Items"),
					"To validate it is navigated to the ship items page",
					"After clicking on the ship items  key button it should navigate to ship items",
					"user Sucessfully navigate to the ship items page",
					"Failed to Navigate to the ship items page");
			Common.clickElement("xpath", "(//div[contains(@class,'public-DraftSt')])[2]");
			Thread.sleep(4000);
			Common.textBoxInputClear("xpath", "(//div[contains(@class,'public-DraftSt')])[2]");
			Thread.sleep(4000);
			Common.clickElement("xpath", "(//div[contains(@class,'public-DraftSt')])[2]");
			Common.findElement("xpath", "(//div[contains(@class,'public-DraftSt')])[2]").sendKeys(ship_name);
			Thread.sleep(4000);
			String get=Common.getText("xpath", "(//div[contains(@class,'public-DraftSt')])[2]");
			System.out.println(get);
//			Common.assertionCheckwithReport(get.equals(ship_name),
//                    "To Validate the order url entered in the text box",
//                    "After clicking on textbox order url should be entered",
//                    "Successfully order url has been entered in the textbox",
//                    "Failed to Enter the order URL in the text box");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate it is navigated to the Get Order page",
					"After clicking on the Get Order  key button it should navigate to Get Order",
					"Unable to Navigate to the Order page",
                    "Failed to Navigate to the Order page");
			Assert.fail();
		}
		
	}

	public void Ship_URL(String Dataset) {
		// TODO Auto-generated method stub
		
		try
		{
			Sync.waitElementPresent(30, "xpath","(//div[contains(@class,'public-DraftSt')])[2]");
			Common.findElement("xpath", "(//div[contains(@class,'public-DraftSt')])[2]").sendKeys(Dataset+"ship");
			String get=Common.getText("xpath", "(//div[contains(@class,'public-DraftSt')])[2]");
			System.out.println(get);
//			Common.assertionCheckwithReport(get.contains(Dataset),
//                    "To Validate the order url entered in the text box",
//                    "After clicking on textbox order url should be entered",
//                    "Successfully order url has been entered in the textbox",
//                    "Failed to Enter the order URL in the text box");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate it is navigated to the Get Order page",
					"After clicking on the Get Order  key button it should navigate to Get Order",
					"Unable to Navigate to the Order page",
                    "Failed to Navigate to the Order page");
			Assert.fail();
		}
	}

	public String order_item_id(String Dataset) {
		// TODO Auto-generated method stub
		String orderid="";
		try
		{
			Common.switchToFirstTab();
			Thread.sleep(4000);
			Common.scrollIntoView("xpath", "//div[@class='product-title']");
			String order_item_id=Common.findElement("xpath", "//div[@class='product-title']").getAttribute("id").replace("order_item_", "").replace("_title", "");
			System.out.println(order_item_id);
			Common.switchToSecondTab();
		    Thread.sleep(4000);
		    Sync.waitElementPresent(30, "xpath", "(//span[text()='Body'])[3]");
		    Common.clickElement("xpath", "(//span[text()='Body'])[3]");
		    Thread.sleep(4000);
			WebElement id=Common.findElement("xpath", "//span[@class='mtk8']");
			id.click();
			Thread.sleep(3000);
			Common.javascriptTextBoxInput("xpath", "//span[@class='mtk8']", order_item_id);
			Thread.sleep(3000);
			Common.scrollIntoView("xpath", "//span[contains(text(),'tracks')]");
			WebElement Track_ID=Common.findElement("xpath", "//span[contains(text(),'track_number')]");
			Common.scrollIntoView(Track_ID);
			Thread.sleep(4000);
			WebElement Tracking_Number=Common.findElement("xpath", "(//span[@class='mtk6'])[1]");
			Tracking_Number.click();
			Thread.sleep(4000);
			Common.switchToFirstTab();
			Thread.sleep(4000);
			String Number=Common.getText("xpath", "//h1[@class='page-title']").replace("#ZSOPREPD", "");
			System.out.println(Number);
			Thread.sleep(4000);
			Common.switchToSecondTab();
			Thread.sleep(4000);
			Common.javascriptTextBoxInput("xpath", "(//span[@class='mtk6'])[1]", Number+"111");
			Thread.sleep(4000);
			System.out.println(Tracking_Number);
//			Common.scrollIntoView("xpath", "//span[@class='mtk6']");
			WebElement delivery_number=Common.findElement("xpath", "(//span[@class='mtk6'])[4]");
			delivery_number.click();
			Thread.sleep(4000);
			int number = Common.genrateRandomNumber();
			System.out.println(number);
			String Delivery = Integer.toString(number);
			String DelNumber="252"+Delivery;
			System.out.println(DelNumber);
			Common.javascriptTextBoxInput("xpath", "(//span[@class='mtk6'])[4]", DelNumber);
			Thread.sleep(4000);
//			Common.clickElement("xpath", "//span[text()='Send']");
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Ship Items"),
                    "To Validate the response code for the shipment",
                    "After clicking on the send button response code should appear",
                    "Successfully Response code has been displayed",
                    "Failed to Displayed the Response code");
			 orderid=Common.getText("xpath", "(//span[@class='mtk32'])[5]");
			
			
			}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To Validate the response code for the shipment",
                    "After clicking on the send button response code should appear",
                    "unable to Displayed the Response code when we click on the send button",
                    "Failed to Displayed the Response code");
			
			Assert.fail();
		}
		return orderid;
		
	}

	public void Magento_Order_Id(String Dataset) {
		// TODO Auto-generated method stub
		
		try
		{
			Common.switchToFirstTab();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//a[@title='Order Shipments']");
			Common.clickElement("xpath", "//a[@title='Order Shipments']");
			Common.refreshpage();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//a[@title='Order Shipments']");
			Common.clickElement("xpath", "//a[@title='Order Shipments']");
			Thread.sleep(4000);
			String records=Common.getText("xpath", "(//span[text()='records found']//parent::div)[3]");
			System.out.println(records);
			if(records.equals("1"))
			{
				Sync.waitElementPresent(30, "xpath", "//a[text()='View']");
				Common.clickElement("xpath", "//a[text()='View']");
				Thread.sleep(4000);
				Common.assertionCheckwithReport(Common.getPageTitle().contains("shipment_id") && Common.getPageTitle().contains(Dataset),
	                    "To Validate the shipment id in the magento url",
	                    "After clicking on the view button is should navigate to the shippment page",
	                    "Successfully Navigated to the shippment page and ship id also appeared in the url",
	                    "Failed to Displayed the shipment id in the Magneto url");
				
			}
			else
			{
				ExtenantReportUtils.addFailedLog(
						"To Validate the records in the shipment",
	                    "After clicking on the shipment button records should be appear ",
	                    "Unable to Displayed the records in the shipment page",
	                    "Failed to Displayed the records in the shipment page");
				Assert.fail();
			}
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To Validate the shipment id in the magento url",
                    "After clicking on the view button is should navigate to the shippment page",
                    "Unable to Displayed the shipment id in the Magneto url",
                    "Failed to Displayed the shipment id in the Magneto url");
			Assert.fail();
		}
	}

	public String Delivery_Details() {
		// TODO Auto-generated method stub
		
		String Delivery="";
		try
		{
			Common.switchToSecondTab();
			Thread.sleep(4000);
			Common.scrollIntoView("xpath", "//span[@class='mtk6']");
			WebElement tracknumber=Common.findElement("xpath", "//span[@class='mtk6']");
			tracknumber.click();
			Delivery=tracknumber.getText();
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			Assert.fail();
			
		}
		return Delivery;
		
	}

	public void Invoice(String Dataset) {
		// TODO Auto-generated method stub
		String Ship=data.get(Dataset).get("Order");
		String ship_name=data.get(Dataset).get("Magento");
		System.out.println(ship_name);
		try
		{
		   Sync.waitElementPresent("xpath", "//div[@title='"+ Ship +"']");
		   Common.clickElement("xpath", "//div[@title='"+ Ship +"']");
		   Thread.sleep(8000);
			Sync.waitForLoad();
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("Invoice"),
					"To validate it is navigated to the Invoice page",
					"After clicking on the Invoice items  key button it should navigate to Invoice items",
					"user Sucessfully navigate to the Invoice items page",
					"Failed to Navigate to the ship Invoice page");
			Common.clickElement("xpath", "(//div[contains(@class,'public-DraftSt')])");
			Thread.sleep(4000);
			Common.textBoxInputClear("xpath", "(//div[contains(@class,'public-DraftSt')])");
			Thread.sleep(4000);
			Common.findElement("xpath", "(//div[contains(@class,'public-DraftSt')])").sendKeys(ship_name);
			Thread.sleep(4000);
			String get=Common.getText("xpath", "//span[@data-text='true']");
			System.out.println(get);
			Common.assertionCheckwithReport(get.equals(ship_name),
                    "To Validate the order url entered in the text box",
                    "After clicking on textbox order url should be entered",
                    "Successfully order url has been entered in the textbox",
                    "Failed to Enter the order URL in the text box");
		}
		catch(Exception | Error e)
        {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate it is navigated to the Get Order page",
					"After clicking on the Get Order  key button it should navigate to Get Order",
					"Unable to Navigate to the Order page",
                    "Failed to Navigate to the Order page");
			Assert.fail();
			
         }
	}

	public void Invoice_URL(String Dataset) {
		// TODO Auto-generated method stub
		
		try
		{
			Sync.waitElementPresent(30, "xpath","(//div[contains(@class,'public-DraftSt')])");
			Common.findElement("xpath", "(//div[contains(@class,'public-DraftSt')])").sendKeys(Dataset+"invoice");
			String get=Common.getText("xpath", "//span[@data-text='true']");
			System.out.println(get);
			Common.assertionCheckwithReport(get.contains(Dataset),
                    "To Validate the order url entered in the text box",
                    "After clicking on textbox order url should be entered",
                    "Successfully order url has been entered in the textbox",
                    "Failed to Enter the order URL in the text box");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate it is navigated to the Get Order page",
					"After clicking on the Get Order  key button it should navigate to Get Order",
					"Unable to Navigate to the Order page",
                    "Failed to Navigate to the Order page");
			Assert.fail();
			
		}
		
	}

	public void Invoice_Body(String Dataset) {
		// TODO Auto-generated method stub
		
		try
		{
			WebElement order=Common.findElement("xpath", "//span[@class='mtk8']");
			order.click();
			order.sendKeys(Keys.CONTROL+"a");
			order.sendKeys(Keys.DELETE);
			Thread.sleep(4000);
			order.sendKeys(Dataset);
	
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	public String Invoice_Delivery(String Dataset) {
	    // TODO Auto-generated method stub
		String Invoice="";
		try
		{
			WebElement order=Common.findElement("xpath", "//span[@class='mtk6']");
			Common.scrollIntoView(order);
			order.click();
			order.sendKeys(Keys.CONTROL+"a");
			order.sendKeys(Keys.DELETE);
			Thread.sleep(4000);
			order.sendKeys(Dataset);
			Common.clickElement("xpath", "//span[text()='Send']");
			Thread.sleep(6000);
			Invoice=Common.getText("xpath", "//span[@class='mtk32']");
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Invoice"),
			"To validate Invoice number in the postman",
			"After clicking on the send button invoice should be generated",
			"Successfully invoice has been generated",
			"Failed to generate the invoice when we click on the send button");
			
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate Invoice number in the postman",
					"After clicking on the send button invoice should be generated",
					"Unable to generate the invoice when we click on the send button",
                    "Failed to generate the invoice when we click on the send button");
			Assert.fail();
			
		}
		return Invoice;
	}

	public void Magento_InvoiceID(String Dataset) {
		// TODO Auto-generated method stub
		
		try
		{
			Common.switchToSecondTab();
			Thread.sleep(4000);
			Common.navigateBack();
			Thread.sleep(4000);
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//a[@title='Order Invoices']");
			String records=Common.getText("xpath", "(//span[text()='records found']//parent::div)[1]");
			System.out.println(records);
			if(records.equals("1"))
			{
				Sync.waitElementPresent(30, "xpath", "//a[text()='View']");
				Common.clickElement("xpath", "//a[text()='View']");
				Thread.sleep(4000);
				Common.assertionCheckwithReport(Common.getPageTitle().contains("order_invoice") && Common.getPageTitle().contains(Dataset),
	                    "To Validate the invoice id in the magento url",
	                    "After clicking on the view button is should navigate to the invoice page",
	                    "Successfully Navigated to the invoice page and invoice id also appeared in the url",
	                    "Failed to Displayed the invoice id in the Magneto url");
				
			}
			else
			{
				ExtenantReportUtils.addFailedLog(
						"To Validate the records in the shipment",
	                    "After clicking on the shipment button records should be appear ",
	                    "Unable to Displayed the records in the shipment page",
	                    "Failed to Displayed the records in the shipment page");
				Assert.fail();
			}
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To Validate the Invoice id in the magento url",
                    "After clicking on the view button is should navigate to the Invoice page",
                    "Unable to Displayed the Invoice id in the Magneto url",
                    "Failed to Displayed the Invoice id in the Magneto url");
			Assert.fail();
		}
		
	}
	      	
	
}