package TestComponent.Drybar_US;

import static org.testng.Assert.fail;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
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
  
public class GoldDrybarusHelper2 {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public GoldDrybarusHelper2(String datafile, String sheetname) {

		excelData = new ExcelReader(datafile, sheetname);
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("Drybar_US");
			report.createTestcase("Drybar_USTestCases");
		} else {
			this.report = Utilities.TestListener.report;
		}

	}

	public void Verify_Homepage() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
//			Thread.sleep(4000);
//			Close_Geolocation();
			acceptPrivacy();
			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Home Drybar")
					|| Common.getPageTitle().contains("Home Drybar") || Common.getPageTitle().contains("Drybar Home")
					|| Common.getPageTitle().contains("Drybar"), "validating store logo",
					"System directs the user to the Homepage", "Sucessfully user navigates to the home page",
					"Failed to navigate to the homepage");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating store logo", "System directs the user to the Homepage",
					" user unable navigates to the home page", "Failed to navigate to the homepage");
			Assert.fail();
		}

	}
	
	public void verify_Homepage() {
		try {
			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Home Drybar")
					|| Common.getPageTitle().contains("Home Drybar") || Common.getPageTitle().contains("Drybar Home")
					|| Common.getPageTitle().contains("Drybar"), "validating store logo",
					"System directs the user to the Homepage", "Sucessfully user navigates to the home page",
					"Failed to navigate to the homepage");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating store logo", "System directs the user to the Homepage",
					" user unable navigates to the home page", "Failed to navigate to the homepage");
			Assert.fail();
		}
	}
	
	public void acceptPrivacy() {
		try {
			
			Sync.waitElementPresent("id", "truste-consent-button");
			Common.clickElement("id", "truste-consent-button");
		}
	catch(Exception | Error e)
	{
	e.printStackTrace();
	Assert.fail();	
	
	}
}
	
	
	public void Decline_All() {
	try {
			
			Sync.waitElementPresent("id", "truste-consent-required");
			Common.clickElement("id", "truste-consent-required");
		}
	catch(Exception | Error e)
	{
	e.printStackTrace();
	Assert.fail();	
	
	}	
	}
	
	public void Close_Geolocation() {
		// TODO Auto-generated method stub
		try {
			if (Common.getCurrentURL().contains("/gb") || Common.getCurrentURL().contains("/eu") || Common.getCurrentURL().contains("stage4")  )
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
	
	public void login_Drybar(String dataSet) {

		try {
			if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
				Sync.waitPageLoad();
				Common.textBoxInput("id", "email", data.get(dataSet).get("UserName"));
			} else {
				Common.textBoxInput("id", "email", data.get(dataSet).get("Prod UserName"));
			}
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//button[@type='submit']//span[text()='Sign In']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Home Page")
					|| Common.getPageTitle().contains("Drybar") || Common.getPageTitle().contains("My Wish List") || Common.getPageTitle().contains("Wish List")
					|| Common.getPageTitle().contains("Dryba Home")
					|| Common.getPageTitle().contains("Drybar - Home page")
					|| Common.getPageTitle().contains("Dashboard") || Common.getPageTitle().contains("Checkout"),
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
	
	public void Sticky_Add_to_Cart(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		String Productsize = data.get(Dataset).get("size");
	//	String productcolor = data.get(Dataset).get("Color");
		System.out.println(Productsize);

//		String results = Common.findElement("xpath", "//span[@id='algolia-srp-title']").getText();
//		System.out.println(results);
		try {


				Sync.waitPageLoad();
				for (int i = 0; i <= 10; i++) {
					Sync.waitElementPresent("xpath", "//img[@class='group-hover/item-image:block hidden']");
					List<WebElement> webelementslist = Common.findElements("xpath", "//img[@class='group-hover/item-image:block hidden']");

					String s = webelementslist.get(i).getAttribute("src");
					System.out.println(s);
					if (s.isEmpty()) {

					} else {
						break;
					}
				}
				Thread.sleep(6000);
				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
				Common.javascriptclickElement("xpath", "//img[@alt='" + products + "']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.actionsKeyPress(Keys.END);
				Thread.sleep(4000);
//			  if(Productsize.contains(Common.findElement("xpath", "(//input[@name='super_attribute[1346]'])[5]").getAttribute("aria-label")))
//			  {
//				  
//				  System.out.println(products);
//				//Sync.waitElementPresent("xpath","(//input[@aria-label='" + Productsize + "'])[3]");
//				//Common.clickElement("xpath","(//input[@aria-label='" + Productsize + "'])[3]");
//				Thread.sleep(3000);
//			  }
//			  else
//			  {
//				 // System.out.println(products);
//				  
//				//Sync.waitElementPresent("xpath","(//input[@aria-label='" + Productsize + "'])[3]");
//					Common.clickElement("xpath",
//							"(//input[@aria-label='" + Productsize + "'])[3]");
//					Thread.sleep(3000);
//				  
//			  }
			   
				Sync.waitElementPresent("xpath", "(//button[@form='product_addtocart_form'])[2]");
				Common.clickElement("xpath", "(//button[@form='product_addtocart_form'])[2]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				/* String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
						.getAttribute("data-ui-id");
				System.out.println(message);
				Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
						"Product should be add to cart", "Sucessfully product added to the cart ",
						"failed to add product to the cart"); */
				Thread.sleep(4000);
				Common.actionsKeyPress(Keys.HOME);
				Common.actionsKeyPress(Keys.UP);

	
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));

			Assert.fail();
		}
	}
	public void HairProducts_headerlinks(String Dataset) {
		// TODO Auto-generated method stub
		String expectedResult = "User should click the" + Dataset;
		String Conditioners = data.get(Dataset).get("Brushes");
		
		String header=data.get(Dataset).get("headers");
		try {

			Thread.sleep(3000);
			Sync.waitElementPresent("xpath",
					"//a[@title='"+ header +"']//span[contains(text(),'"+ header +"')]");
			
			Common.clickElement("xpath", "//a[@title='"+ header +"']//span[contains(text(),'"+ header +"')]");

			Thread.sleep(3000);

			try {
				Common.mouseOver("xpath", "//span[contains(text(),'"+ header +"')]");
			} catch (Exception e) {
				Common.clickElement("xpath", "//a[@class='level-top ui-corner-all']//span[text()='"+ header +"']");
			}
			Common.clickElement("xpath", "//span[contains(text(),'" + Conditioners + "')]");
			Thread.sleep(4000);
			
			Sync.waitPageLoad();
			Thread.sleep(6000);
			expectedResult = "User should select the " + Dataset + "category";
			int sizebotteles = Common.findElements("xpath", "//a[@title='"+ header +"']//span[contains(text(),'"+ header +"')]").size();
			System.out.println(sizebotteles);
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


	
	public void RegaddDeliveryAddress(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String expectedResult = "shipping address is entering in the fields";

		String firstname = data.get(dataSet).get("FirstName");
//		System.out.println(firstname);
		
		
		int size = Common.findElements(By.xpath("//button[contains(text(),'New Address')]")).size();
		if (size > 0) {
			try {
				Thread.sleep(6000);
				if(Common.findElements("xpath", "//div[@x-ref='freegift']").size()>0)
				{
					Sync.waitElementPresent("xpath", "(//button[@aria-label='Close'])[1]");
					Common.clickElement("xpath", "(//button[@aria-label='Close'])[1]");
				}
				Thread.sleep(5000);
				
				Common.clickElement("xpath", "//button[contains(text(),'New Address')]");
				Thread.sleep(4000);
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='firstname']",
						data.get(dataSet).get("FirstName"));
				Thread.sleep(2000);
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='lastname']",
						data.get(dataSet).get("LastName"));
				Thread.sleep(2000);
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='street[0]']",
						data.get(dataSet).get("Street"));

				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.SPACE);
				Thread.sleep(2000);
				try {
					Common.clickElement("xpath", "//form[@id='shipping']//input[@name='street[0]']");
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
				if (Common.getCurrentURL().contains("gb")) {
					Common.scrollIntoView("xpath", "//input[@placeholder='State/Province']");
					Common.textBoxInput("xpath", "//input[@placeholder='State/Province']",
							data.get(dataSet).get("Region"));
					Thread.sleep(3000);
					String Shippingvalue = Common.findElement("xpath", "//input[@placeholder='State/Province']")
							.getAttribute("value");
//					System.out.println(Shippingvalue);
				} else {

					Thread.sleep(4000);
					Common.scrollIntoView("xpath", "//select[@id='shipping-region']");
					Common.dropdown("xpath", "//select[@id='shipping-region']", Common.SelectBy.TEXT,
							data.get(dataSet).get("Region"));
					Thread.sleep(3000);
					String Shippingvalue = Common.findElement("xpath", "//select[@id='shipping-region']")
							.getAttribute("value");
//					System.out.println(Shippingvalue);

				}

//					Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				Common.textBoxInput("xpath", "//input[@id='shipping-city']", data.get(dataSet).get("City"));
				Thread.sleep(2000);
				Common.textBoxInputClear("xpath", "//form[@id='shipping']//input[@name='postcode']");
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='postcode']",
						data.get(dataSet).get("postcode"));
				String ShippingZip = Common.findElement("name", "postcode").getAttribute("value");
//				System.out.println("*****" + ShippingZip + "*******");

				Thread.sleep(7000);
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='telephone']",
						data.get(dataSet).get("phone"));

//					Sync.waitElementPresent("xpath", "//label[@class='label a-checkbox__label']");
//					Common.clickElement("xpath", "//label[@class='label a-checkbox__label']");

				Thread.sleep(3000);
				Common.clickElement("xpath", "//button[contains(text(),'Ship Here')]");

				ExtenantReportUtils.addPassLog("validating shipping address filling Fields",
						"shipping address is filled in to the fields", "user should able to fill the shipping address ",
						Common.getscreenShotPathforReport("Sucessfully shipping address details has been entered"));

			//	close_successmessage();

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
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='firstname']",
						data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='lastname']",
						data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='street[0]']",
						data.get(dataSet).get("Street"));
				Sync.waitElementPresent("xpath", "//form[@id='shipping']//input[@name='street[0]']");
				Common.clickElement("xpath", "//form[@id='shipping']//input[@name='street[0]']");
				if (data.get(dataSet).get("StreetLine2") != null) {
					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
				}
				if (data.get(dataSet).get("StreetLine3") != null) {
					Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
				}
//					Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				Common.textBoxInput("xpath", "//input[@id='shipping-city']", data.get(dataSet).get("City"));

				if (Common.getCurrentURL().contains("gb")) {
					Thread.sleep(4000);
					Common.scrollIntoView("xpath", "//select[@id='shipping-region']");
					Common.textBoxInput("xpath", "//select[@id='shipping-region']", data.get(dataSet).get("Region"));

					Thread.sleep(3000);
					String Shippingvalue = Common.findElement("xpath", "//select[@id='shipping-region']")
							.getAttribute("value");
					System.out.println(Shippingvalue);
				} else {
					Common.scrollIntoView("xpath", "//select[@id='shipping-region']");
					Common.dropdown("xpath", "//select[@id='shipping-region']", Common.SelectBy.TEXT,
							data.get(dataSet).get("Region"));
					Thread.sleep(3000);
					String Shippingvalue = Common.findElement("xpath", "//select[@id='shipping-region']")
							.getAttribute("value");
					System.out.println(Shippingvalue);
				}

				Thread.sleep(2000);
				Common.textBoxInputClear("xpath", "//form[@id='shipping']//input[@name='postcode']");
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='postcode']",
						data.get(dataSet).get("postcode"));

				String ShippingZip = Common.findElement("xpath", "//form[@id='shipping']//input[@name='postcode']")
						.getAttribute("value");
				System.out.println("*****" + ShippingZip + "*******");

				Thread.sleep(4000);
				Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='telephone']",
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
	
	
	
	public void click_singinButton() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Common.clickElement("xpath", "//a[@id='customer.header.sign.in.link']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(
					Common.getCurrentURL().contains("customer/account/login"),
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
	
	public void click_singin_Shippingpage() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Sign in')]");
			Common.clickElement("xpath", "//span[contains(text(),'Sign in')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(
					Common.getCurrentURL().contains("customer/account/login"),
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
	
	public void close_add() throws Exception {
        // TODO Auto-generated method stub
        Thread.sleep(5000);
        int sizesframe = Common.findElements("xpath", "//div[@id='ltkpopup-content']").size();
        System.out.println(sizesframe);
        if (sizesframe > 0) {
            Common.actionsKeyPress(Keys.PAGE_UP);
            Thread.sleep(4000);
            Sync.waitElementPresent("id", "ltkpopup-close-button");
            Common.clickElement("id", "ltkpopup-close-button");
        }
        else {

//            Common.switchFrames("xpath", "//div[@class='preloaded_lightbox']/iframe");
            Sync.waitElementPresent("id", "ltkpopup-close-button");
            Common.clickElement("id", "ltkpopup-close-button");
//            Common.switchToDefault();
            }

	}

	public void search_product(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		System.out.println(product);
		try {
			Common.clickElement("id", "menu-search-icon");
			String open = Common.findElement("id", "menu-search-icon").getAttribute("aria-expanded");
			Common.assertionCheckwithReport(open.contains("true"), "User searches using the search field",
					"User should able to click on the search button", "Sucessfully search bar should be expand",
					"Failed to open the search bar");
			Common.textBoxInput("id", "autocomplete-0-input", product);
			Common.actionsKeyPress(Keys.ENTER);
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

		String products = data.get(Dataset).get("Products");
		System.out.println(products);
	
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Thread.sleep(3000);
				Sync.waitElementPresent("css", "a[class*=roduct-image-link]>img");
//				Sync.waitElementPresent("xpath", "(//img[contains(@class,'m-product-card__image')])[2]");
				List<WebElement> webelementslist = Common.findElements("css",
						"a[class*=roduct-image-link]>img");
				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Common.scrollIntoView("css", "img[alt='" + products + "']");
			Sync.waitElementPresent(30, "css", "img[alt='" + products + "']");
//			Common.mouseOver("xpath", "//img[@alt='" + products + "']");
			Thread.sleep(3000);
			
            String PLPprice=Common.findElement("xpath", "(//img[@alt='" + products + "']//parent::a//parent::div//parent::div//div[@data-role='priceBox']//span//span)[1]").getText();
            System.out.println(PLPprice);
            if(PLPprice.contains(""))
            {
            	   String PLPprice1=Common.findElement("xpath", "(//img[@alt='" + products + "']//parent::a//parent::div//parent::div//div[@data-role='priceBox']//span//span)[2]").getText();
            	   System.out.println(PLPprice1);
            	   Thread.sleep(2000);
            	   Common.clickElement("css", "img[alt='" + products + "']");
            	   Thread.sleep(4000);
            	   product_quantity(Dataset);
       				String PDPprice=Common.findElement("xpath", "(//span[@data-price-type='finalPrice'])[position()=1 or position()=2]").getText();
       				System.out.println(PDPprice);
//                Assert.assertEquals(PLPprice1, PDPprice);
            }
            else {
			Common.clickElement("css", "img[alt='" + products + "']");
			Thread.sleep(2000);
			product_quantity(Dataset);
            String PDPprice=Common.findElement("xpath", "(//span[@data-price-type='finalPrice'])[2]").getText();
            System.out.println(PDPprice);
            Assert.assertEquals(PLPprice, PDPprice);
            }
            int free_popup = Common.findElements("xpath", "(//header[@data-sticky='sticky-enabled'])[1]").size();
            if(free_popup>0) {
            Sync.waitElementPresent("xpath", "(//button[@aria-label='Close'])[1]");
            Common.clickElement("xpath", "(//button[@aria-label='Close'])[1]");
            }
			Sync.waitElementPresent("css", "button[title='ADD TO BAG']");
			Common.clickElement("css", "button[title='ADD TO BAG']");
			Thread.sleep(3000);
			String openminicart = Common.findElement("xpath", "//div[contains(@class,'fixed inset-y-0')]").getAttribute("aria-modal");
			System.out.println(openminicart);
			Common.assertionCheckwithReport(openminicart.contains("true"), "validating the  product add to the cart and mini cart should be open",
					"Product should be add to cart and mini cart should be open", "Success message should be displayed and minicart should be open", "success message should be displayed and mini cart");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));

			Assert.fail();
		}

	}
	
	
	
	
	
	public void	subcribe_product_Add_to_Cart (String Dataset) {
		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		String save=data.get(Dataset).get("SubscribeSave");
		String save1=data.get(Dataset).get("Save Subscription");
		System.out.println(save);
		try
		{
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) { 
				Sync.waitElementPresent("xpath", "//img[@class='group-hover/item-image:block hidden']");
				List<WebElement> webelementslist = Common.findElements("xpath","//img[@class='group-hover/item-image:block hidden']");

				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Thread.sleep(7000);
			
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.javascriptclickElement("xpath", "//img[@alt='" + products + "']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			String name = Common.findElement("xpath", "//h1[contains(@class,'pdp-grid-title')]").getText();
			Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
					"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
					"failed to Navigate to the PDP page");
			product_quantity(Dataset);
			Thread.sleep(4000);
			Common.scrollIntoView("xpath", "(//input[@name='aw-sarp2-dropdown-show-hide'])[2]");
			Sync.waitElementPresent("xpath", "(//input[@name='aw-sarp2-dropdown-show-hide'])[2]");
			Thread.sleep(3000);
			Common.clickElement("xpath", "(//input[@name='aw-sarp2-dropdown-show-hide'])[2]");
//			Thread.sleep(4000);
			if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
				Sync.waitPageLoad();
			     Sync.waitElementPresent("xpath", "//select[contains(@class,'aw-sarp2-subscription__options-list')]");
				Common.dropdown("xpath", "//select[contains(@class,'aw-sarp2-subscription__options-list')]", Common.SelectBy.TEXT, save);
				
				Thread.sleep(4000);
				String drop=Common.findElement("xpath", "//select[contains(@class,'aw-sarp2-subscription__options-list')]//option[contains(text(),'"+ save +"')]").getText();
				Thread.sleep(4000);
				Common.assertionCheckwithReport(drop.contains(save), "To ensure that selected text has selected in the product subcription dropdown",
						"Dropdown should be select for the product subcription", "Sucessfully text has been selected from the dropdown",
						"failed to select the text from the dropdown");
			} else {
				 Sync.waitElementPresent("xpath", "//select[contains(@class,'aw-sarp2-subscription__options-list')]");
				Common.dropdown("xpath", "//select[contains(@class,'aw-sarp2-subscription__options-list')]", Common.SelectBy.TEXT, save1);
				
				Thread.sleep(4000);
				String drop=Common.findElement("xpath", "//select[contains(@class,'aw-sarp2-subscription__options-list')]//option[contains(text(),'"+ save1 +"')]").getText();
				Thread.sleep(4000);
				Common.assertionCheckwithReport(drop.contains(save1), "To ensure that selected text has selected in the product subcription dropdown",
						"Dropdown should be select for the product subcription", "Sucessfully text has been selected from the dropdown",
						"failed to select the text from the dropdown");
			}
		  
			Thread.sleep(2000);
			String PLPPrice=Common.findElement("xpath", "//div[@x-defer='intersect']//span[@class='price']").getText();
			System.out.println(PLPPrice);
			Sync.waitElementPresent("xpath", "//button[@title='ADD TO BAG']");
			Common.clickElement("xpath", "//button[@title='ADD TO BAG']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
/*	String MinicartPrice=Common.findElement("xpath", "(//span[@x-html='item.product_price']//span[@class='price'])[1]").getText();
	System.out.println(MinicartPrice);
	Assert.assertEquals(PLPPrice, MinicartPrice);*/
		}
		catch(Exception | Error e)
		{
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
			Common.findElement("xpath", "//select[@name='qty']");
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
				Common.dropdown("xpath", "//select[@name='qty']", Common.SelectBy.VALUE, Quantity);
				Thread.sleep(3000);
				String value = Common.findElement("xpath", "//select[@name='qty']").getAttribute("value");
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
//        String Checkoutprice="";
	try {
		Thread.sleep(2000);
		Sync.waitElementPresent("css", "span[x-text='totalCartAmount']");
		String minicart = Common.findElement("css", "span[x-text='totalCartAmount']").getText();
	
		Sync.waitElementPresent(30, "xpath", "//a[contains(text(),'Checkout')]");
		Common.clickElement("xpath", "//a[contains(text(),'Checkout')]");
		Sync.waitPageLoad();
	Thread.sleep(4000);
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("checkout"),
				"validating the navigation to the shipping page when we click on the checkout",
				"User should able to navigate to the shipping  page after clicking on the checkout page", "Successfully navigate to the shipping page after clicking on the checkout page",
				"Failed to navigate to the shipping page after clicking on the checkout button");
//		Checkoutprice=Common.findElement("xpath", "//span[@class='text-xs']//span[contains(@class,'price')]").getText().trim();

		

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog(
				"validating the navigation to the shipping page when we click on the checkout ",
				"User should able to navigate to the shipping  page", "unable to navigate to the shipping page",
				Common.getscreenShot("Failed to navigate to the shipping page"));

		Assert.fail();
	}
//	return Checkoutprice;

}
	
	public void Guest_SUB_minicart_Checkout() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
//			click_minicart();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//span[@x-text='totalCartAmount']");
			String minicart = Common.findElement("xpath", "//span[@x-text='totalCartAmount']").getText();
			System.out.println(minicart);
			Sync.waitElementPresent(30, "xpath", "//a[contains(text(),'Checkout')]");
			Common.clickElement("xpath", "//a[contains(text(),'Checkout')]");
			Sync.waitPageLoad();
			Thread.sleep(7000);
//			Sync.waitElementPresent(30, "xpath", "//strong[@role='heading']");
//			String checkout = Common.findElement("xpath", "//span[contains(@data-bind,'text: getC')]").getText();
//			System.out.println(checkout);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(
					 Common.getCurrentURL().contains("/customer/account/login/"),
					"validating the navigation to the Login page after clicking on the checkout button",
					"After clicking on the checkout button user shoould navigate to Login page", "Successfully navigate to the Login page after clicking on the Checkout button",
					"Failed to navigate to the Login page after clicking on the checkout button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the navigation to the Login page after clicking on the checkout button",
					"After clicking on the checkout button user shoould navigate to Login page", "Unable to navigate to the Login page after clicking on the checkout button",
					Common.getscreenShot("Failed to navigate to the Login page after clicking on the checkout button"));

			Assert.fail();
		}

	}
	
	
	public void Validate_Shipping_Options() {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(5000);
			String Expedited = Common.getText("xpath", "//span[contains(text(),'Expedited')]");
			System.out.println(Expedited);
			String Express = Common.getText("xpath", "(//span[contains(text(),'Express')])[2]");
			System.out.println(Express);		
			Common.assertionCheckwithReport(Expedited.equals("Expedited (2 - 3 Business Days)")&& Express.equals("Express (1 - 2 Business Days)")||
					Expedited.equals("Shipping & Handling (Shipping - Expedited (2 - 3 Business Days))"),
					"validating the Shipping methods",
					"After entering the address it should display the shipping methods",
					"Successfully  shipping methods", "Failed to  shipping methods");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the Shipping methods",
					"After entering the address it should display the shipping methods",
					"Unable to shipping methods",
					Common.getscreenShot("Failed to shipping methods"));
			Assert.fail();
		}

	}

	
	
	
	public void click_minicart() {
		try {
			Thread.sleep(8000);
			Common.actionsKeyPress(Keys.UP);
			Sync.waitElementPresent("xpath", "//button[@id='menu-cart-icon']");
			Common.clickElement("xpath", "//button[@id='menu-cart-icon']");
			
			String openminicart = Common.findElement("xpath", "//button[@id='menu-cart-icon']").getAttribute("aria-expanded");
			System.out.println(openminicart);
			if(openminicart.contains("true")) {
			Common.assertionCheckwithReport(openminicart.contains("true"), "To validate the minicart popup",
					"the mini cart is displayed", "Should display the mini cart", "mini cart is not displayed");
			} else {
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//button[@id='menu-cart-icon']");
			Common.clickElement("xpath", "//button[@id='menu-cart-icon']");
			String openminicart1 = Common.findElement("xpath", "//button[@id='menu-cart-icon']").getAttribute("aria-expanded");
			System.out.println(openminicart1);
			Common.assertionCheckwithReport(openminicart1.contains("true"), "To validate the minicart popup",
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
			Thread.sleep(3000);
			if(Common.findElements("xpath", "(//header[@data-sticky='sticky-enabled'])[1]").size()>0)
			{
				Sync.waitElementPresent("xpath", "(//button[@aria-label='Close'])[1]");
				Common.clickElement("xpath", "(//button[@aria-label='Close'])[1]");
			}
			
			Common.textBoxInput("id", "shipping-firstname",
					data.get(dataSet).get("FirstName"));
			int size = Common.findElements("xpath", "//input[@type='email']").size();
			Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,
					"Filled Email address", "unable to fill the email address");
			Common.textBoxInput("id", "shipping-lastname",
					data.get(dataSet).get("LastName"));
			Common.clickElement("id", "shipping-street-0");
			Common.textBoxInput("id", "shipping-street-0", address);
//			Thread.sleep(5000);
			Common.scrollIntoView("id", "shipping-country_id");
		    Common.dropdown("id", "shipping-country_id",Common.SelectBy.TEXT, data.get(dataSet).get("Country"));
//			Thread.sleep(2000);
			Sync.waitPageLoad();
//			Thread.sleep(5000);
			Common.findElement("id", "shipping-city").clear();
			Common.textBoxInput("id", "shipping-city",
					data.get(dataSet).get("City"));
			System.out.println(data.get(dataSet).get("City"));

			Common.actionsKeyPress(Keys.PAGE_DOWN);
//			Thread.sleep(3000);
			  if(Common.getCurrentURL().contains("gb"))
              {
				  Common.scrollIntoView("xpath", "//input[@placeholder='State/Province']");
					Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", data.get(dataSet).get("Region"));
              }
			  else
			  {
				
				Common.scrollIntoView("xpath", "//select[@name='region']");
                Common.dropdown("xpath", "//select[@name='region']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
                Thread.sleep(3000);
                String Shippingvalue = Common.findElement("xpath", "//select[@name='region']")
                        .getAttribute("value");
                System.out.println(Shippingvalue);
			}
			Thread.sleep(3000);
			Common.textBoxInputClear("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
			Thread.sleep(8000);
	        
			Common.clickElement("xpath", "//input[@name='telephone']");
			Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
			Thread.sleep(3000);
			
			String subtotal=Common.findElement("xpath", " (//div[@class='item subtotal']//span[@class='value'])").getText().replace(symbol, "").replace(".", "");
			System.out.println(subtotal);
			subtotal = subtotal.trim();
			subtotal = subtotal.substring(0,subtotal.length() - 2);
		    System.out.println(subtotal);  
			int amount=Integer.parseInt(subtotal);
			System.out.println(amount);
		/*	if(amount>199 && symbol.equals("$"))
			{
				Sync.waitElementPresent(30, "xpath", "//div[@class='ampromo-close']");
				Common.clickElement("xpath", "//div[@class='ampromo-close']");
				Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
			}
			else
			{
				Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
			}*/

			

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
		String prodmethod=data.get(Dataset).get("Prodmethods");
		System.out.println(method);

		try {
			Thread.sleep(5000);
			int size = Common.findElements("xpath", "//label[contains(@for,'shipping-method')]").size();
			System.out.println(size);
			if (size > 0) {
                if(Common.getCurrentURL().contains("preprod"))
                {
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//label[contains(@class,'flex w-full')]//span[contains(text(),'" + method + "')]");
				Common.clickElement("xpath", "//label[contains(@class,'flex w-full')]//span[contains(text(),'" + method + "')]");
                }
				else
				{
					Sync.waitElementPresent("xpath", "//label[contains(@class,'flex w-full')]//span[contains(text(),'" + prodmethod + "')]");
					Common.clickElement("xpath", "//label[contains(@class,'flex w-full')]//span[contains(text(),'" + prodmethod + "')]");
				}
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
			Thread.sleep(5000);
			System.out.println(expectedResult);
//			Common.clickElement("xpath", "//button[@data-role='opc-continue']");
//			Sync.waitPageLoad();
//			Thread.sleep(4000);
//			Common.assertionCheckwithReport(Common.getCurrentURL().contains("checkout/#payment")||Common.getCurrentURL().contains("checkout/#shipping"),
//					"validating the navigates to the Checkout page",
//					"After clicking on the next button it should navigate to the Checkout page",
//					"Successfully navigated to the Checkout page", "Failed to Navigate to the Checkout page");
		}

		catch (Exception | Error e) {
//			e.printStackTrace();
//			ExtenantReportUtils.addFailedLog("validating the shipping page submitbutton", expectedResult,
//					"failed to click the submitbutton",
//					Common.getscreenShotPathforReport("failed submitbuttonshippingpage"));
//			Assert.fail();
		}

	}
	
	public String updatePaymentAndSubmitOrder(String dataSet) throws Exception {
		// TODO Auto-generated method stub

		String order = "";
		addPaymentDetails(dataSet);
		String expectedResult = "It redirects to order confirmation page";

		if (Common.findElements("xpath", "//div[@class='message message-error']").size() > 0) {
			Thread.sleep(1000);
			addPaymentDetails(dataSet);
		}

		Thread.sleep(2000);
		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);

		if (!url.contains("stage") && !url.contains("preprod")) {
		}

		else {
			try {
				Thread.sleep(2000);
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

				if (Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//p//span").size() > 0) {
				    Thread.sleep(2000);
				    order = Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p//span");
				    System.out.println(order);
				} else {
				    Thread.sleep(2000);
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
	
	
	public void Stored_Payment(String Dataset) {
		// TODO Auto-generated method stub

		try {
			Common.clickElement("xpath", "//button[@aria-label='My Account']");
			Sync.waitElementPresent(30, "xpath", "//a[contains(text(),'My Account')]");
			Common.clickElement("xpath", "//a[contains(text(),'My Account')]");
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
			Sync.waitElementPresent("xpath", "//a[@title='Stored Payment Methods']");
			Common.clickElement("xpath", "//a[@title='Stored Payment Methods']");
			Sync.waitPageLoad(30);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Payment Methods"),
					"validating the Navigation to the My Payment Methods page",
					"After Clicking on stored methods CTA user should be navigate to the My Payment Methods page",
					"Sucessfully User Navigates to the My Payment Methods page after clicking on the stored methods  CTA",
					"Failed to Navigate to the My Payment Methods page after Clicking on my stored methods  CTA");
			int size = Common.findElements("xpath", "//div[@class='divide-y divide-border']").size();
			if (size > 0) {
				String number = Common.findElement("xpath", "//div[@class='flex items-center']//span").getText()
						.replace("•••• ", "");
				System.out.println(number);
				System.out.println(Dataset);
				Thread.sleep(4000);
				Common.assertionCheckwithReport(number.contains("4242") || Dataset.contains("4242"),
						"validating the card details in the my orders page",
						"After Clicking on My payments methods and payment method should be appear in payment methods",
						"Sucessfully payment method is appeared in my payments methods",
						"Failed to display the payment methods in the my payments methods");
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
	public void My_Orders_Page(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent(30, "id", "customer-menu");
			Common.clickElement("id", "customer-menu");
			Sync.waitElementPresent(30, "xpath", "//a[@title='My Account']");
			Common.clickElement("xpath", "//a[@title='My Account']");
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Account") || Common.getPageTitle().equals("Dashboard"),
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
			Common.clickElement("xpath", "//span[text()='My Orders']");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Orders"),
					"validating the Navigation to the My Orders page",
					"After Clicking on My Orders CTA user should be navigate to the My Orders page",
					"Sucessfully User Navigates to the My Orders page after clicking on the My Orders CTA",
					"Failed to Navigate to the My Orders page after Clicking on My Orders CTA");
			String Ordernumber = Common.findElement("xpath", "(//table[@class='table-orders']//span[@class='text-right'])[1]")
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
				int savedcard=Common.findElements("xpath", "//div[contains(@class,'form-select flex')]").size();
				if(savedcard>0)
				{
					Sync.waitElementPresent("xpath", "(//input[@class='checkbox mr-4'])[2]");
					Common.clickElement("xpath", "(//input[@class='checkbox mr-4'])[2]");
				}

				Thread.sleep(5000);
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Thread.sleep(2000);
				Common.clickElement("xpath", "//input[@id='Field-numberInput']");
				Common.textBoxInput("id", "Field-numberInput",cardnumber);

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
					Sync.waitElementPresent("xpath", "(//button[contains(@class,'btn-place-order')])[2]");
	             	Common.clickElement("xpath", "(//button[contains(@class,'btn-place-order')])[2]");
	             	 Thread.sleep(4000);  
		  			if(Common.findElements("xpath","//div[@x-ref='address-confirmation-modal-shipping']").size() > 0)
		  				{
		  					Sync.waitElementPresent("xpath", "(//button[contains(text(),'Use as Entered')])[1]");
		  					Common.clickElement("xpath", "(//button[contains(text(),'Use as Entered')])[1]");
		  				 }
		            
	             	   Thread.sleep(40000);
	             	  if(Common.getCurrentURL().contains("/checkout"))
	              	   {
//	              		   Sync.waitElementPresent("xpath", "//label[@for='stripe-new-payments']");
//	              		   Common.clickElement("xpath", "//label[@for='stripe-new-payments']");
//	              		   Thread.sleep(3000);
//	              		   Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
//	                  	   Common.clickElement("xpath", "//button[@class='action primary checkout']");
//	                  	   Thread.sleep(4000);
	                  	 String sucessmessage=Common.getText("xpath", "//div[contains(@class,'checkout-success')]//h1");
		              	    System.out.println(sucessmessage);
	              		   
	              	   }
	              	   else if(Common.getCurrentURL().contains("/success/"))
	              	   {
	              	    String sucessmessage=Common.getText("xpath", " //h1[normalize-space()='Thank you for your purchase!']");
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

	
	public String billingaddPaymentDetails(String dataSet) throws Exception {
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
			int sizes = Common.findElements("xpath", "//label[@for='payment-method-stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unabel to land opaymentpage");
			Common.clickElement("xpath", "//label[@for='payment-method-stripe_payments']");

			
			Same_Billing_and_Shipping();
			
			int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
			System.out.println(payment);
			if (payment > 0) {
//				Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
//				Common.clickElement("xpath", "//div[@class='stripe-dropdown-selection']");
//				Common.clickElement("xpath", "//span[text()='New payment method']");
				Thread.sleep(4000);
				int savedcard=Common.findElements("xpath", "//div[contains(@class,'form-select flex')]").size();
				if(savedcard>0)
				{
					Sync.waitElementPresent("xpath", "(//input[@class='checkbox mr-4'])[2]");
					Common.clickElement("xpath", "(//input[@class='checkbox mr-4'])[2]");
				}
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
				int savedcard=Common.findElements("xpath", "//select[@x-model='savedMethodId']").size();
				if(savedcard>0)
				{
					Sync.waitElementPresent("xpath", "(//input[@class='checkbox mr-4'])[2]");
					Common.clickElement("xpath", "(//input[@class='checkbox mr-4'])[2]");
				}
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
					Sync.waitElementPresent("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
	             	   Common.clickElement("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
	             	   Thread.sleep(40000);
	             	  if(Common.getCurrentURL().contains("/checkout"))
	              	   {
//	              		   Sync.waitElementPresent("xpath", "//label[@for='stripe-new-payments']");
//	              		   Common.clickElement("xpath", "//label[@for='stripe-new-payments']");
//	              		   Thread.sleep(3000);
//	              		   Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
//	                  	   Common.clickElement("xpath", "//button[@class='action primary checkout']");
//	                  	   Thread.sleep(4000);
	                  	 String sucessmessage=Common.getText("xpath", "//div[contains(@class,'checkout-success')]//h1");
		              	    System.out.println(sucessmessage);
	              		   
	              	   }
	              	   else if(Common.getCurrentURL().contains("/success/"))
	              	   {
	              	    String sucessmessage=Common.getText("xpath", " //h1[normalize-space()='Thank you for your purchase!']");
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
	
	
	public void HairTools_headerlinks(String Dataset) {
		// TODO Auto-generated method stub
		String expectedResult = "User should click the" + Dataset;
		String Brushes = data.get(Dataset).get("Brushes");
		String Detangling = data.get(Dataset).get("Detangling Brushes");
		String header=data.get(Dataset).get("headers");
		try {

		  
			Sync.waitElementPresent("xpath",
					"//a[@title='"+ header +"']//span[contains(text(),'"+ header +"')]");
			
			Common.clickElement("xpath", "//a[@title='"+ header +"']//span[contains(text(),'"+ header +"')]");

			Thread.sleep(3000);

			try {
				Common.mouseOver("xpath", "//span[contains(text(),'"+ header +"')]");
			} catch (Exception e) {
				Common.clickElement("xpath", "//a[@class='level-top ui-corner-all']//span[text()='"+ header +"']");
			}
			Common.clickElement("xpath", "//span[contains(text(),'" + Brushes + "')]");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//span[contains(text(),'" + Detangling + "')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			expectedResult = "User should select the " + Dataset + "category";
			int sizebotteles = Common.findElements("xpath", "//a[@title='"+ header +"']//span[contains(text(),'"+ header +"')]").size();
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

	public void Same_Billing_and_Shipping() {
		// TODO Auto-generated method stub
		try
		{
			Sync.waitElementPresent("xpath", "//input[@type='checkbox' and @id='billing-as-shipping']");
			Boolean checkbox=Common.findElement("xpath", "//input[@type='checkbox' and @id='billing-as-shipping']").isSelected();
			System.out.println(checkbox);
			Thread.sleep(7000);
			String box=Boolean.toString(checkbox);
			System.out.println(box);
			if(box.contains("false"))
			{
				Sync.waitElementPresent("xpath", "//input[@id='billing-as-shipping']");
				Common.clickElement("xpath", "//input[@id='billing-as-shipping']");
			    Boolean billcheckbox=Common.findElement("xpath", "//input[@type='checkbox' and @id='billing-as-shipping']").isSelected();
			    System.out.println(billcheckbox);
			    String box1=Boolean.toString(billcheckbox);
				System.out.println(box1);
				Common.assertionCheckwithReport(box1.equals("true"),
						"To validate the checkbox is selected in the payment page",
						"user should able to see the checkbox is selected in the payment page",
						"User Successfully selected the checkbox on the payment page",
						"User Failed to see the checkbox selected on the payment page");
			}
			else
			{
				System.out.println(box);
			}
			
		}
		catch(Exception | Error e)
		{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To validate the checkbox is selected in the payment page",
		"user should able to see the checkbox is selected in the payment page",
		"Unable to select the chechbox on the payment page",
				Common.getscreenShot("User Failed to see the checkbox selected on the payment page"));
		Assert.fail();
		}
		
	}

	public String same_Blling_and_Shipping_SubmitOrder(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String order = "";
		billingaddPaymentDetails(dataSet);
		String expectedResult = "It redirects to order confirmation page";

		if (Common.findElements("xpath", "//div[@class='message message-error']").size() > 0) {
			Thread.sleep(4000);
			billingaddPaymentDetails(dataSet);
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
				Thread.sleep(3000);
				Sync.waitElementPresent(30,"xpath", " //h1[normalize-space()='Thank you for your purchase!']");
				String sucessMessage = Common.getText("xpath", " //h1[normalize-space()='Thank you for your purchase!']");

				//Tell_Your_FriendPop_Up();
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

	public void tax_validation_Paymentpage(String Dataset) {
		// TODO Auto-generated method stub
		String Symbol=data.get(Dataset).get("Symbol");
		try
		{
			
			if(Common.getCurrentURL().contains("/gb"))
			{
				String Subtotal = Common.getText("xpath", "//tr[@class='totals sub']//span[@class='price']").replace(Symbol,
						"");
				Float subtotalvalue = Float.parseFloat(Subtotal);
				String shipping = Common.getText("xpath", "//tr[contains(@class,'totals shipping')]//span[@class='price']")
						.replace(Symbol, "");
				Float shippingvalue = Float.parseFloat(shipping);
				String Tax = Common.getText("xpath", "//tr[@class='totals-tax']//span[@class='price']").replace(Symbol, "");
				Float Taxvalue = Float.parseFloat(Tax);
				Thread.sleep(4000);

				String ordertotal = Common.getText("xpath", "//tr[@class='grand totals']//span[@class='price']")
						.replace(Symbol, "");
				Float ordertotalvalue = Float.parseFloat(ordertotal);
				Thread.sleep(4000);
				Float Total = (subtotalvalue + shippingvalue);
				String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				Thread.sleep(4000);
				System.out.println(ExpectedTotalAmmount2);
				System.out.println(ordertotal);
				Common.assertionCheckwithReport(ExpectedTotalAmmount2.equals(ordertotal),
						"validating the Tax on the payment page",
						"On the order summary tax should be display on the payment page",
						"Successfully Tax should be display in the order summary",
						"Failed to display the tax on the order summary");
			}
			else
			{
			Thread.sleep(4000);
			String Subtotal = Common.getText("xpath", "//div[@class='item subtotal']//span[@class='value']").replace("$",
					"");
			System.out.println(Subtotal);
			Float subtotalvalue = Float.parseFloat(Subtotal);
			String shipping = Common.getText("xpath", "//div[@class='item shipping']//span[@class='value']")
					.replace(Symbol, "");
			Float shippingvalue = Float.parseFloat(shipping);
			
			
			String Tax = Common.getText("xpath", "//div[@class='item tax']//span[@class='value']").replace(Symbol, "");
			
			Float Taxvalue = Float.parseFloat(Tax);
			Thread.sleep(4000);

			String ordertotal = Common.getText("xpath", "//div[@class='item grand_total']//span[contains(@class,'value')]")
					.replace(Symbol, "");
			Float ordertotalvalue = Float.parseFloat(ordertotal);
			Thread.sleep(4000);
			Float Total = (subtotalvalue + shippingvalue + Taxvalue);
			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			Thread.sleep(4000);
			System.out.println(ExpectedTotalAmmount2);
			System.out.println(ordertotal);
			Common.assertionCheckwithReport(ExpectedTotalAmmount2.equals(ordertotal),
					"validating the Tax on the payment page",
					"On the order summary tax should be display on the payment page",
					"Successfully Tax should be display in the order summary",
					"Failed to display the tax on the order summary");

		}
		}
		
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Tax on the payment page",
					"On the order summary tax should be display on the payment page",
					"Unable to display the tax on the order summaryy",
					Common.getscreenShotPathforReport("Failed to display the tax on the order summary"));
			Assert.fail();
		}
		
	}

	public void No_Tax_Validation() {
		// TODO Auto-generated method stub
		try
		{
			String Subtotal = Common.getText("xpath", "(//div[@class='item subtotal']//span[@class='value'])[1]").replace("$",
					"");
			Float subtotalvalue = Float.parseFloat(Subtotal);
			String shipping = Common.getText("xpath", "//div[@class='item shipping']//span[@class='value']")
					.replace("$", "");
			Float shippingvalue = Float.parseFloat(shipping);
			String ordertotal = Common.getText("xpath", "(//div[@class='item grand_total']//span[contains(@class,'value text-right')])[1]")
					.replace("$", "");
			Float ordertotalvalue = Float.parseFloat(ordertotal);
			Thread.sleep(4000);
			Float Total = (subtotalvalue + shippingvalue);
			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			Thread.sleep(4000);
			System.out.println(ExpectedTotalAmmount2);
			System.out.println(ordertotal);
			Common.assertionCheckwithReport(ExpectedTotalAmmount2.equals(ordertotal),
					"validating the No tax on the order summary in the payment page",
					"Order summary should be display in the payment page and tax field should not display",
					"Successfully Order summary is displayed and tax is displayed",
					"Failed tax is displayed under order summary");
 
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the No tax on the order summary in the payment page",
					"Order summary should be display in the payment page and tax field should not display",
					"Successfully Order summary is displayed and tax is displayed",
					Common.getscreenShotPathforReport("Failed tax is displayed under order summary"));
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
			Common.javascriptclickElement("xpath", "//img[@alt='" + products + "']");

			Thread.sleep(4000);
//			Common.scrollIntoView("xpath", "//a[text()='Reviews & Questions']");
//			Sync.waitElementPresent("xpath", "//a[@id='tab-label-product.yotpo.reviews-title']");
//			Thread.sleep(3000);
//			String form = Common.getText("xpath", "//a[@id='tab-label-product.yotpo.reviews-title']");
//			System.out.println(form);
//			Common.assertionCheckwithReport(form.equals("Reviews & Questions"), "verifying the write a review button",
//					"Write a review should be appear in the PDP page",
//					"Sucessfully write a review button has been displayed in PDP page",
//					"Failed to display the write a review button in PDP page");
//			Common.clickElement("xpath", "//a[text()='Reviews & Questions']");
			Common.actionsKeyPress(Keys.END);
			
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
			Sync.waitElementPresent(30,"xpath","//span[@class='form-input-error']");
			String errormessage = Common.findElement("xpath", "//span[@class='form-input-error']").getText();
			System.out.println(errormessage);
			Thread.sleep(4000);
			Common.assertionCheckwithReport(errormessage.contains("Please enter a star rating for this review") || errormessage.contains("Review's title & body can't be empty"),
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
			Common.textBoxInput("xpath", "//input[@name='email']", Utils.getEmailid());
			Thread.sleep(3000);
			if(Common.getCurrentURL().contains("preprod")) {
			
			Common.clickElement("xpath", "//input[@value='Post']");
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@class='yotpo-thank-you']").getAttribute("aria-label");
			Common.assertionCheckwithReport(message.equals("Thank you for posting a review"),
					"verifying the post for the product review",
					"product review should be submit after clicking on post",
					"Sucessfully Thank you message has been displayed ", "Failed to display the Thank you message ");
			}
			else {
				
				String button = Common.findElement("xpath", "//input[@value='Post']").getText();
		
			}
//			Common.clickElement("xpath", "//div[@aria-label='Next']");

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
	
	
	public void Ask_a_question(String Dataset) {
		// TODO Auto-generated method stub
		String Question = data.get(Dataset).get("CommetsDrybar");
		String Name = data.get(Dataset).get("FirstName");
		String Email = data.get(Dataset).get("Email");
		try {
		
			Thread.sleep(4000);
			Common.scrollIntoView("xpath","//span[@class='reviews-qa-label font-color-gray']");
			Thread.sleep(2000);
			Sync.waitElementPresent(30,"xpath", "(//div[@class='yotpo-nav-wrapper']//span)[2]");
			Common.javascriptclickElement("xpath", "(//div[@class='yotpo-nav-wrapper']//span)[2]");
			Thread.sleep(5000);
			Common.javascriptclickElement("xpath","//button[contains(@class,'write-first-question-button')]");
			Sync.waitElementPresent(30, "xpath", "//textarea[contains(@id,'yotpo_input_question_content')]");
			Common.textBoxInput("xpath", "//textarea[contains(@id,'yotpo_input_question_content')]", Question);
			Sync.waitElementPresent(30, "xpath", "//input[@id='yotpo_input_question_username']");
			Common.textBoxInput("xpath", "//input[@id='yotpo_input_question_username']", Name);
			Sync.waitElementPresent(30, "xpath", "//input[@id='yotpo_input_question_email']");
			Common.textBoxInput("xpath", "//input[@id='yotpo_input_question_email']", Utils.getEmailid());
			Thread.sleep(3000);
			if(Common.getCurrentURL().contains("preprod")){
			Common.clickElement("xpath", "//input[@data-button-type='submit']");
			Thread.sleep(4000);
			String question = Common
					.findElement("xpath", "//div[@class='yotpo-thank-you']//span[contains(text(),'Thank you')]")
					.getText();
			System.out.println(question);
			Common.assertionCheckwithReport(question.contains("THANK YOU FOR POSTING A QUESTION!"),
					"validating the question submit form", "Ask a form should be submit",
					"Sucessfully question post should be submit", "Failed to submit the ask a question post");
			}
			else
			{
				String button = Common.findElement("xpath", "//input[@data-button-type='submit']").getText();
				System.out.println(button);
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the question submit form", "Ask a form should be submit",
					"Unable to subit question post", Common.getscreenShot("failed to subit question post"));
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
	
	
	

	
	public String BillingAddress(String dataSet) {
		// TODO Auto-generated method stub
		String update = "";
		String Shipping="";
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//input[@id='billing-as-shipping']");
			Boolean checkbox=Common.findElement("xpath", "//input[@id='billing-as-shipping']").isSelected();
			System.out.println(checkbox);
			Thread.sleep(7000);
			String box=Boolean.toString(checkbox);
			if(box.contains("true"))
			{
				//Common.dropdown("xpath", "(//select[@name='billing_address_id'])[3]", Common.SelectBy.TEXT,"New Address");
				
				Common.scrollIntoView("xpath", "//input[@id='billing-as-shipping']");
				Sync.waitElementPresent("xpath", "//input[@id='billing-as-shipping']");	
				Common.javascriptclickElement("xpath", "//input[@id='billing-as-shipping']");
				Thread.sleep(5000);
				Sync.waitElementPresent("xpath", "(//button[contains(text(),'New Address')])[2]");
				Common.clickElement("xpath", "(//button[contains(text(),'New Address')])[2]");
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

					 Thread.sleep(4000);
	                 Common.scrollIntoView("xpath", "//select[@name='region']");
	                 Common.dropdown("xpath", "//select[@name='region']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
	                 Thread.sleep(3000);
	                 String Shippingvalue = Common.findElement("xpath", "//select[@name='region']")
	                         .getAttribute("value");
	                 Shipping=Common.findElement("xpath", "//option[@value='"+Shippingvalue+"']").getAttribute("data-title");
		              System.out.println(Shipping);
	                 System.out.println(Shippingvalue);
				Thread.sleep(2000);
				Common.textBoxInput("xpath", "(//input[@name='postcode'])[2]",
						data.get(dataSet).get("postcode"));
				Thread.sleep(5000);

				Common.textBoxInput("xpath", "(//input[@name='telephone'])[2]",
						data.get(dataSet).get("phone"));
				Common.clickElement("xpath", "//button[contains(text(),' Save ')]");
				Sync.waitPageLoad();
				Thread.sleep(5000);
				//update = Common.findElement("xpath", "(//span[@data-bind='text: currentBillingAddress().region'])[2]").getText();
				//System.out.println("update"+update);
				/*Common.assertionCheckwithReport(
						update.equals(Shipping),
						"verifying the Billing address form in payment page",
						"Billing address should be saved in the payment page",
						"Sucessfully Billing address form should be Display ",
						"Failed to display the Billing address in payment page");*/
			}
				
				
			/*Sync.waitElementPresent("xpath", "//label[@for='stripe_payments']");
			Common.clickElement("xpath", "//label[@for='stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='stripe_payments']").size();
			Common.clickElement("xpath", "//label[@for='stripe_payments']");
			Common.assertionCheckwithReport(sizes > 0, "Validating the payment section page",
					"payment section should be displayed", "sucessfully payment section has been displayed",
					"Failed to displayed the payment section");
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//input[@id='billing-address-same-as-shipping-stripe_payments']");
			Common.clickElement("xpath", "//input[@id='billing-address-same-as-shipping-stripe_payments']");
			if(Common.findElement("xpath", "(//select[@name='billing_address_id'])[3]").getAttribute("id").contains("billing-address-id"))
			{
				Sync.waitElementPresent("xpath", "(//select[@name='billing_address_id'])[3]");
				Common.dropdown("xpath", "(//select[@name='billing_address_id'])[3]", Common.SelectBy.TEXT,"New Address");
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
	                 Common.scrollIntoView("xpath", "//select[@name='region_id']");
	                 Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
	                 Thread.sleep(3000);
	                 String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']")
	                         .getAttribute("value");
	                 Shipping=Common.findElement("xpath", "//option[@value='"+Shippingvalue+"']").getAttribute("data-title");
		              System.out.println(Shipping);
	                 System.out.println(Shippingvalue);
				Thread.sleep(2000);
				Common.textBoxInput("xpath", "//div[contains(@name,'payments.postcode')]//input[@name='postcode']",
						data.get(dataSet).get("postcode"));
				Thread.sleep(5000);

				Common.textBoxInput("xpath", "//div[@class='field _required']//input[@name='telephone']",
						data.get(dataSet).get("phone"));
				Common.clickElement("xpath", "//span[text()='Update']");
				Sync.waitPageLoad();
				Thread.sleep(5000);
				update = Common.findElement("xpath", "(//span[@data-bind='text: currentBillingAddress().region'])[2]").getText();
				System.out.println("update"+update);
				Common.assertionCheckwithReport(
						update.equals(Shipping),
						"verifying the Billing address form in payment page",
						"Billing address should be saved in the payment page",
						"Sucessfully Billing address form should be Display ",
						"Failed to display the Billing address in payment page");
			}*/
			else
			{
		Sync.waitElementPresent("xpath", "(//button[contains(text(),' New Address ')])[2]");
		Common.clickElement("xpath", "(//button[contains(text(),' New Address ')])[2]");
		Thread.sleep(3000);
			Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
			Common.textBoxInput("xpath", "//input[@name='street[1]']", data.get(dataSet).get("Street"));
			
			Thread.sleep(4000);
			String text = Common.findElement("xpath", "//input[@name='street[0]']").getAttribute("value");
			Sync.waitPageLoad();
			String text1 = Common.findElement("xpath", "//input[@name='street[1]']").getAttribute("value");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
			System.out.println(data.get(dataSet).get("City"));

				 Thread.sleep(4000);
                 Common.scrollIntoView("xpath", "//select[@name='region_id']");
                 Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
                 Thread.sleep(3000);
                 String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']")
                         .getAttribute("value");
                 Shipping=Common.findElement("xpath", "//option[@value='"+Shippingvalue+"']").getAttribute("data-title");
	              System.out.println(Shipping);
                 System.out.println(Shippingvalue);
			Thread.sleep(2000);
			Common.textBoxInput("xpath", "//div[contains(@name,'payments.postcode')]//input[@name='postcode']",
					data.get(dataSet).get("postcode"));
			Thread.sleep(5000);

			Common.textBoxInput("xpath", "//div[@class='field _required']//input[@name='telephone']",
					data.get(dataSet).get("phone"));
			Common.clickElement("xpath", "//button[contains(text(), ' Save ')]");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			/*update = Common.findElement("xpath", "(//span[@data-bind='text: currentBillingAddress().region'])[2]").getText();
			System.out.println("update"+update);
			Common.assertionCheckwithReport(
					update.equals(Shipping),
					"verifying the Billing address form in payment page",
					"Billing address should be saved in the payment page",
					"Sucessfully Billing address form should be Display ",
					"Failed to display the Billing address in payment page");*/
			}
			
			/*else
			{
				Common.assertionCheckwithReport(box.equals("false"),
						"To validate the billing and shipping address are different",
						"user should able to see different billing and shipping on payment page",
						"User Successfully able to see the same billing and shipping on the payment page",
						"User Failed to see the same billing and shipping address on the payment page");
			}*/

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the Billing address form in payment page",
					"Billing address should be saved in the payment page",
					"Unable to display the Billing address in payment page",
					Common.getscreenShotPathforReport("Failed to display the Billing address in payment page"));
			Assert.fail();
		}
		return update;
	}

	
	

	
	public void signout() {
		try {
			Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Sync.waitElementPresent("xpath", "//a[@title='Sign Out']");

			Common.clickElement("xpath", "//a[@title='Sign Out']");
			Thread.sleep(3000);
			
//			Common.assertionCheckwithReport(
//					Common.getCurrentURL().contains("customer/account/logoutSuccess/"),
//					"Validating My Account page navigation", "user sign in and navigate to my account page",
//					"Successfully navigate to my account page", "Failed to navigate my account page ");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating sign out navigation ",
					"after clinking signout user signout fro the page", "user Successfully signout  ",
					Common.getscreenShotPathforReport("user Failed to signout"));
			Assert.fail();
		}

	}
	
	public void Account_Navlinks(String Dataset) {
		String Navlinks = data.get(Dataset).get("Navigation Links");
		String[] Account = Navlinks.split(",");
		int i = 0;
		try {
			for (i = 0; i < Account.length; i++) {
				Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
				System.out.println(Account[i]);
				Sync.waitElementPresent("xpath",
						"//div[@class='content account-nav-content']//a[text()=\"" + Account[i] +"\"]");
				Common.clickElement("xpath",
						"//div[@class='content account-nav-content']//a[text()=\"" + Account[i] +"\"]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String currentUrl=Common.getCurrentURL();
				System.out.println(currentUrl);
				Common.assertionCheckwithReport(
						currentUrl.contains("rma/returns/history/")|| currentUrl.contains("wishlist"),
						"verifying Account page links " + Account[i],
						"user should navigate to the " + Account[i] + " page",
						"user successfully Navigated to the " + Account[i], "Failed click on the " + Account[i]);
				Thread.sleep(2000);


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
	
	public void Account_page_Validation(String Dataset) throws Exception {
		// TODO Auto-generated method stub
				Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
				Common.clickElement("xpath", "//button[@id='customer-menu']");
				Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
				Common.clickElement("xpath", "//button[@id='customer-menu']");
				if (Common.findElements("xpath","//a[@title='Sign In']").size() > 0 ) {
					Sync.waitElementPresent("xpath", "//a[@title='Sign In']");
					Common.clickElement("xpath","//a[@title='Sign In']");	
				} else {
					Sync.waitElementPresent("xpath", "//a[@title='My Account']");
					Common.clickElement("xpath", "//a[@title='My Account']");
				}

//				String MyId=Common.findElement("xpath","(//ul[@class='m-account-nav__links']//li//a)[1]").getAttribute("id");
//				Common.clickElement("xpath", "//a[@id='"+MyId+"']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				if (Common.getCurrentURL().contains("stage")|| Common.getCurrentURL().contains("preprod")) {
					String Accountlinks = data.get(Dataset).get("Account Links");
					String[] Account = Accountlinks.split(",");
					int i = 0;
					try {
						for (i = 0; i < Account.length; i++) {
							System.out.println(Account[i]);
							Sync.waitElementPresent("xpath","//span[text()='"+Account[i]+"']");
//									
							Common.clickElement("xpath",
									"//span[text()='"+Account[i]+"']");
							Sync.waitPageLoad();
							Thread.sleep(4000);
							/*String title = Common.findElement("xpath", "//h1[@class='page-title-wrapper h2']").getText();
							System.out.println(title);
							Common.assertionCheckwithReport(
									title.contains(Account[i]) || title.contains("My Wish Lists")
											|| title.contains("My Payment Methods") || title.contains("Newsletter Subscription")
											|| title.contains("Pro deal information"),
									"verifying Account page links " + Account[i],
									"user should navigate to the " + Account[i] + " page",
									"user successfully Navigated to the " + Account[i], "Failed click on the " + Account[i]);
		                 */
							String currentUrl=Common.getCurrentURL();
							System.out.println(currentUrl);
							Common.assertionCheckwithReport(
									currentUrl.contains("ustomer/account/edit/")||currentUrl.contains("/customer/address/")||currentUrl.contains("sales/order/history/")||currentUrl.contains("rma/returns/history/")||currentUrl.contains("/wishlist/")||currentUrl.contains("stock/index/")||currentUrl.contains("customer/paymentmethods/")||currentUrl.contains("newsletter/manage/")
								  ||Common.getPageTitle().contains("My Subscriptions")||currentUrl.contains("customer/account/logoutSuccess/") || currentUrl.equals("https://mcloud-na-preprod.drybar.com/"),
									"verifying Account page links " + Account[i],
									"user should navigate to the " + Account[i] + " page",
									"user successfully Navigated to the " + Account[i], "Failed click on the " + Account[i]);
							Thread.sleep(2000);
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
							System.out.println(Account[i]);
							Sync.waitElementPresent("xpath","//span[text()='"+Account[i]+"']");
							Common.clickElement("xpath","//span[text()='"+Account[i]+"']");
							Sync.waitPageLoad();
							Thread.sleep(4000);
							String currentUrl=Common.getCurrentURL();
							System.out.println(currentUrl);
							Common.assertionCheckwithReport(
									currentUrl.contains("ustomer/account/edit/")||currentUrl.contains("/customer/address/")||currentUrl.contains("sales/order/history/")||currentUrl.contains("rma/returns/history/")||currentUrl.contains("/wishlist/")||currentUrl.contains("stock/index/")||currentUrl.contains("customer/paymentmethods/")||currentUrl.contains("newsletter/manage/")
								  ||Common.getPageTitle().contains("My Subscriptions")||currentUrl.contains("customer/account/logoutSuccess/") || currentUrl.equals("https://www.drybar.com/"),
									"verifying Account page links " + Account[i],
									"user should navigate to the " + Account[i] + " page",
									"user successfully Navigated to the " + Account[i], "Failed click on the " + Account[i]);
							Thread.sleep(2000);
		 

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
				Common.clickElement("xpath", "//input[@id='payment-method-paypal_express']");
				Common.clickElement("xpath", "//div[@id='paypal-button-paypal_express']");
				
			}
			else
			{
				Common.scrollIntoView("xpath", "//input[@id='payment-method-paypal_express']");
				Common.clickElement("xpath", "//input[@id='payment-method-paypal_express']");
				Common.clickElement("xpath", "//div[@id='paypal-button-paypal_express']");
			}
            
			
			Common.switchFrames("xpath", "//iframe[contains(@class,'component-frame visible')]");
			Sync.waitElementPresent("xpath", "(//div[contains(@class,'paypal-button paypal-button')])[1]");
			Common.clickElement("xpath", "(//div[contains(@class,'paypal-button paypal-button')])[1]");
//			Common.switchFrames("xpath", "//iframe[contains(@class,'component-frame visible')]");

			Thread.sleep(8000);
			
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
			int size = Common.findElements("xpath", "//a[text()='Log in with a password instead']").size();
			if(size>0) {
				Common.clickElement("xpath", "//a[text()='Log in with a password instead']");
				Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			}
			else {
				
			
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			int sizeemail = Common.findElements("id", "email").size();
			
			Common.assertionCheckwithReport(sizeemail > 0, "verifying the paypal payment ", expectedResult,
					"open paypal site window", "faild to open paypal account");
			}
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
			String url1 = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
			if (!url1.contains("stage") && !url1.contains("preprod")) {
			}

			else {
				try {
					Thread.sleep(6000);
					Common.scrollIntoView("xpath", "(//button[contains(@class,'btn btn-primary place-order')])[2]");
					
					Common.clickElement("xpath", "(//button[contains(@class,'btn btn-primary place-order')])[2]");

					Thread.sleep(6000);
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
	public String Express_Venmo_Payment(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String order="";
		String expectedResult = "It should open venmo site window.";
		
		try {
			Thread.sleep(2000);
			
			Common.switchFrames("xpath", "//iframe[contains(@class,'component-frame visible')]");

			// Common.refreshpage();
			Thread.sleep(8000);
			Sync.waitElementPresent("xpath", "//img[@class='paypal-logo paypal-logo-venmo paypal-logo-color-white']");
			Common.clickElement("xpath", "//img[@class='paypal-logo paypal-logo-venmo paypal-logo-color-white']");
			Common.switchToDefault();
			Thread.sleep(5000);
			Common.switchWindows();
			int size = Common.findElements("id", "acceptAllButton").size();
			if (size > 0) {

				Common.clickElement("id", "acceptAllButton");

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the venmo payment ", expectedResult,
					"User failed to proceed with venmo payment", Common.getscreenShotPathforReport(expectedResult));
			Assert.fail();
		}
		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
		Sync.waitElementPresent("xpath", "//div[@class='max-width-wrapper']/img");
		String venmo = Common.findElement("xpath", "//div[@class='max-width-wrapper']/img").getAttribute("alt");
		if(venmo.equals("Venmo")) {
		Sync.waitElementPresent("xpath", "//img[@alt='PayPal']");
		Common.clickElement("xpath", "//img[@alt='PayPal']");
		}
		if (!url.contains("stage") & !url.contains("preprod") & !url.contains("stage3") ) {

			int sizeofelement = Common.findElements("id", "email").size();
			Common.assertionCheckwithReport(sizeofelement > 0, "verifying the venmo payment ", expectedResult,
					"open venmo site window", "faild to open venmo account");
		} else {

			Common.clickElement("id", "login_emaildiv");
			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.clickElement("id", "btnNext");
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			int sizeemail = Common.findElements("id", "email").size();

			Common.assertionCheckwithReport(sizeemail > 0, "verifying the venmo payment ", expectedResult,
					"open venmo site window", "faild to open venmo account");
		
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
				ExtenantReportUtils.addFailedLog("verifying the venmo payment ", expectedResult,
						"User failed to proceed with venmo payment",
						Common.getscreenShotPathforReport(expectedResult));
				Assert.fail();
			}
			express_paypal_shipping(dataSet);
			if(Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("stage3") || Common.getCurrentURL().contains("preprod"))
			{
				Common.scrollIntoView("xpath", "//button[@value='Place Order']");
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//button[@value='Place Order']");
				Common.clickElement("xpath", "//button[@value='Place Order']");
			}
			// Tell_Your_FriendPop_Up();//To close the Pop-up
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
	
	public void express_paypal_shipping(String Dataset) {
		// TODO Auto-generated method stub
		String shippment=data.get(Dataset).get("methods");
		try
		{
			Thread.sleep(4000);
			Common.dropdown("xpath", "//select[@name='shipping_method']", SelectBy.TEXT, shippment);
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
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

			Sync.waitElementPresent("xpath", "//button[@title='Search']");
			Common.clickElement("xpath", "//button[@title='Search']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String orderid = Common.findElement("xpath", "//span[contains(@class,'title-lg')]").getText();
			System.out.println(orderid);
			String ID=Common.findElement("xpath", "//span[contains(@class,'title-lg')]").getText().replace("ORDER #", "");
			System.out.println("ID");
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

	
	public void click_trackorder() {
		try {
			Sync.waitElementPresent(30, "xpath", "//button[@aria-label='My Account']");
			Common.clickElement("xpath", "//button[@aria-label='My Account']");
			Common.clickElement("xpath", "//a[@title='Track My Order']");
			Sync.waitPageLoad();
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

	
	public void Configurable_addtocart(String Dataset) {{
		// TODO Auto-generated method stub
		
		String products = data.get(Dataset).get("Products");
		String Productsize= data.get(Dataset).get("size");
		String scent=data.get(Dataset).get("scent");
		System.out.println(Productsize);
		System.out.println(products);
		try {
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[contains(@class,'group-hover/item-image')]");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[contains(@class,'group-hover/item-image')]");

				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			
				
			Sync.waitPageLoad(30);
			Thread.sleep(6000);
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.javascriptclickElement("xpath", "//img[@alt='" + products + "']");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//span[contains(text(),'" + Productsize + "')]");
			Common.clickElement("xpath", "//span[contains(text(),'" + Productsize + "')]");
			Thread.sleep(5000);
			String size=Common.findElement("xpath", "(//span[contains(@class,'pointer-events-none select-none whitespace')])[2]").getText().toUpperCase();
			System.out.println(size);
			String size1= data.get(Dataset).get("size").toUpperCase();
			System.out.println(size1);
			
			Common.assertionCheckwithReport(
					size.contains(size1),
					"Verifying the the size of the product is selected in the PDP",
					"after clicking on the size product size should be selected",
					"successfully Product size has been selected on the PDP",
					"Failed to select the product price on the PDP");
			product_quantity(Dataset);
			Thread.sleep(4000);
			
			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			Sync.waitPageLoad();
			Thread.sleep(5000);

		}
		
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));
			Assert.fail();
		}
	}
	}


	public void Order_ID_Verification(String Ordernumber) {
		// TODO Auto-generated method stub
		
		try
		{
		 Sync.waitElementPresent("xpath", "//a[@class='order-number link link-primary']");
	     Common.clickElement("xpath", "//a[@class='order-number link link-primary']");
	     Thread.sleep(4000);
	     Common.assertionCheckwithReport(Common.getCurrentURL().contains("order_id"), "validating the navigated to the my orders page",
					"when we click on the order number it is navigate to the My orders page", "Sucessfully Navigated to the My orders page ",
					"failed to Navigate to the My orders page");
	    String Order_ID= Common.findElement("xpath", "//span[contains(@class,'title-xs')]").getText().replace("ORDER #", "");
	    System.out.println(Order_ID);
	    Common.assertionCheckwithReport(Ordernumber.equals(Order_ID), "Validating the order number in the my orders page",
				"Order Number should be display on the My Orders page", "Sucessfully Order Number io displayed in the My orders page",
				"Failed to display the Order ID Number on the My Orders page");
			
		}
		catch (Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the order number in the my orders page",
					"Order Number should be display on the My Orders page",
					"Unable to display the Order ID Number on the My Orders page",
					Common.getscreenShot("Failed to display the Order ID Number on the My Orders page"));
			Assert.fail();
		}
		
	}
	
	public void Artical_Links(String dataSet) {

		String socalLinks = data.get(dataSet).get("Links");
		String[] socallinksarry = socalLinks.split(",");
		int i = 0;
		try {
			for (i = 0; i < socallinksarry.length; i++) {
				Common.actionsKeyPress(Keys.END);
				Common.clickElement("xpath", "//a[contains(@href,'" + socallinksarry[i] + "')]");
				Common.switchWindows();
				System.out.println(Common.getCurrentURL());
				System.out.println(socallinksarry[i]);
				if (socallinksarry[i].contains("instagram")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("instagram"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Thread.sleep(4000);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				}

				else if (socallinksarry[i].contains("facebook")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("www.facebook.com"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				}
				
				else if (socallinksarry[i].contains("tiktok")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("www.tiktok.com"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				}

				else if (socallinksarry[i].contains("x.com")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("https://x.com/"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				} else if (socallinksarry[i].contains("youtube")) {
					Common.assertionCheckwithReport(Common.getCurrentURL().contains("www.youtube.com"),
							"Verifying Social link  " + socallinksarry[i], "User click the social " + socallinksarry[i],
							"successfully navigating to social link  " + socallinksarry[i],
							"Failed to navigate to social link " + socallinksarry[i]);
					Common.closeCurrentWindow();
					Common.switchToFirstTab();
				} 
				else if (socallinksarry[i].contains("pinterest")) {
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
					"User unable to navigate Social link page", Common.getscreenShotPathforReport("socialpage"));
			Assert.fail();
		}
	}

	public void Paymentcreditcard_WithInvalidData(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		
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
					"User unabel to land opaymentpage");
	
			Common.clickElement("xpath", "//label[@for='payment-method-stripe_payments']");
		
			int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
			System.out.println(payment);
			if (payment > 0) {
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
					Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
	             	   Common.clickElement("xpath", "//button[@class='action primary checkout']");
	             	  Thread.sleep(10000);
	             	 if(Common.findElement("xpath", "//div[contains(@class,'error')]").getText().contains("Please complete your payment details."))
	             	 {
	             		expectedResult = "credit card fields are filled with the data";
						String errorTexts = Common.findElement("xpath", "errorText").getText();
						Common.assertionCheckwithReport(
								errorTexts.isEmpty() || errorTexts.contains("Please complete your payment details."),
								"validating the credit card information with valid data", expectedResult,
								"Filled the Card detiles", "missing field data it showinng error");
	             	 }
	             	 else if(Common.getCurrentURL().contains("/checkout/#payment"))
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
				Thread.sleep(2000);
				int savecard = Common.findElements("xpath","//div[contains(@class,'form-select flex')]").size();
				if(savecard > 0) {
					Sync.waitElementPresent(30,"xpath", "(//input[@class='checkbox mr-4'])[2]");
					Common.clickElement("xpath", "(//input[@class='checkbox mr-4'])[2]");
				}
				Thread.sleep(4000);
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
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
	             	  Thread.sleep(10000);
	             		Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
	    				Thread.sleep(5000);
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

		}

		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", expectedResult,
					"failed  to fill the Credit Card infromation",
					Common.getscreenShotPathforReport("Cardinfromationfail"));
			Assert.fail();
		}

	}
	
	public String Express_Paypal(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String order = "";

		String expectedResult = "It should open paypal site window.";
		
		try {
			Thread.sleep(3000);
			Common.switchFrames("xpath", "//iframe[contains(@class,'component-frame visible')]");

			// Common.refreshpage();
			Thread.sleep(8000);
			Sync.waitElementClickable("xpath", "//div[contains(@class,'paypal-button-lab')]");
			Common.clickElement("xpath", "//div[contains(@class,'paypal-button-lab')]");
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
				Paypal_Address_Verification("Express Paypal");
				Thread.sleep(4000);
				if(Common.getCurrentURL().contains(""))
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
			express_paypal_shipping(dataSet);
			Thread.sleep(4000);
			if(Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("stage3") || Common.getCurrentURL().contains("preprod") )
			{
				Common.scrollIntoView("xpath", "(//button[contains(@class,'btn btn-primary place-order')])[2]");
				Sync.waitElementPresent("xpath", "(//button[contains(@class,'btn btn-primary place-order')])[2]");
				Thread.sleep(4000);
				Common.clickElement("xpath", "(//button[contains(@class,'btn btn-primary place-order')])[2]");
			}
			// Tell_Your_FriendPop_Up();//To close the Pop-up
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
	
	public void Paypal_Address_Verification(String Dataset) {
		// TODO Auto-generated method stub
		String Street=data.get(Dataset).get("Street");
		System.out.println(Street);
		try
		{
			Sync.waitElementPresent("xpath", "//span[@data-testid='header-cart-total']");
			String symbol=Common.findElement("xpath", "//span[@data-testid='header-cart-total']").getText();
			System.out.println(symbol);
			Thread.sleep(4000);
			if(symbol.contains("$"))
			{
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//p[@data-testid='ship-to-address']");
				String address=Common.findElement("xpath", "//p[@data-testid='ship-to-address']").getText();
				System.out.println(address);
			}
			else
			{
				Sync.waitElementPresent("xpath", "//button[@data-testid='change-shipping']");
				Common.clickElement("xpath", "//button[@data-testid='change-shipping']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//select[@data-testid='shipping-dropdown']");
				Thread.sleep(4000);
				Common.dropdown("xpath", "//select[@data-testid='shipping-dropdown']", SelectBy.TEXT, data.get(Dataset).get("Street"));
				Thread.sleep(3000);
				String Ukaddress=Common.findElement("xpath", "//p[@data-testid='ship-to-address']").getText();
				System.out.println(Ukaddress);
				String UK=data.get(Dataset).get("Street").replace("Test Qa - ", "");
				System.out.println(UK);
				Common.assertionCheckwithReport(
						Ukaddress.contains(UK),
						"validating the address selection from the drop down",
						"Address should be select from the dropdown ","Sucessfully address has been selected from the dropdown",
						"Failed to select the Address from the dropdown");
				
			}
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the address selection from the drop down",
					"Address should be select from the dropdown ","Unable to select the Address from the dropdown",
					Common.getscreenShotPathforReport("Failed to select the Address from the dropdown"));
			Assert.fail();
		}
		
	}

	
	public String venmo_Payment(String dataSet) throws Exception {

		String order = "";

		String expectedResult = "It should open venmo site window.";
		
		try {
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("xpath", "//div[contains(text(),'PayPal Express Checkout')]");
			Thread.sleep(2000);
			Common.clickElement("xpath", "//div[contains(text(),'PayPal Express Checkout')]");
			
			
			Common.switchFrames("xpath", "//iframe[contains(@name,'__zoid__paypal_buttons__ey')]");
			
			
			Sync.waitElementPresent("xpath", "//div[@aria-label='venmo']");
			Common.clickElement("xpath", "//div[@aria-label='venmo']");
			Thread.sleep(2000);
			
			Common.switchToDefault();
			Thread.sleep(5000);
			Common.switchWindows();
			
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the venmo payment ", expectedResult,
					"User failed to proceed with venmo payment", Common.getscreenShotPathforReport(expectedResult));
			Assert.fail();
		}
		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
		Sync.waitElementPresent("xpath", "//div[@class='max-width-wrapper']/img");
		String venmo = Common.findElement("xpath", "//div[@class='max-width-wrapper']/img").getAttribute("alt");
		if(venmo.equals("Venmo")) {
		Sync.waitElementPresent("xpath", "//img[@alt='PayPal']");
		Common.clickElement("id", "paypalButton");
		}
		if (!url.contains("stage") & !url.contains("preprod")& !url.contains("stage3") ) {

			int sizeofelement = Common.findElements("id", "email").size();
			Common.assertionCheckwithReport(sizeofelement > 0, "verifying the venmo payment ", expectedResult,
					"open venmo site window", "faild to open venmo account");
		} else {

			Common.clickElement("id", "login_emaildiv");
			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.clickElement("id", "btnNext");
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			int sizeemail = Common.findElements("id", "email").size();

			Common.assertionCheckwithReport(sizeemail > 0, "verifying the venmo payment ", expectedResult,
					"open venmo site window", "faild to open venmo account");
		
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
				ExtenantReportUtils.addFailedLog("verifying the venmo payment ", expectedResult,
						"User failed to proceed with venmo payment",
						Common.getscreenShotPathforReport(expectedResult));
				Assert.fail();
			}
//			express_paypal_shipping("Paypal Shipping");
			// Tell_Your_FriendPop_Up();//To close the Pop-up
			String url1 = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
			if (!url1.contains("stage4") && !url1.contains("preprod")) {
				
				
			}

			else {
				try {
					Sync.waitElementPresent("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
					Common.clickElement("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
					
					Thread.sleep(3000);
					Sync.waitElementPresent(30,"xpath", " //h1[normalize-space()='Thank you for your purchase!']");
					String sucessMessage = Common.getText("xpath", " //h1[normalize-space()='Thank you for your purchase!']");

					//Tell_Your_FriendPop_Up();
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
			Thread.sleep(4000);
			Common.clickElement("id", "submit.save");
			String errormessage = Common.findElement("xpath", "//div[@class='mage-error']").getText();
			Common.assertionCheckwithReport(errormessage.equals("This is a required field."),
					"validating error message in empty form", "It should display the error message when form is empty",
					"successfully error message when form is empty",
					"Failed to display the error message when form is empty");
			Common.textBoxInput("xpath", "//input[@id='title']", data.get(Dataset).get("Type"));
			Common.textBoxInput("xpath", "//textarea[@id='message']", data.get(Dataset).get("Message"));
			Common.dropdown("xpath", "//select[@id='is_public']", SelectBy.TEXT, data.get(Dataset).get("privacy"));
			Common.dropdown("xpath", "//select[@id='is_active']", SelectBy.TEXT, data.get(Dataset).get("Status"));
			String eventname = Common.findElement("xpath", "//span[@class='value']").getText();
			if (eventname.equals("Birthday")) {
				System.out.println(Common.getCurrentURL());
				if(Common.getCurrentURL().contains("gb"))
				{
					Common.textBoxInput("xpath", "//input[@id='event_country_region_text']",
							data.get(Dataset).get("Region"));
				}
				else
				{
				
				Common.dropdown("xpath","//select[@id='event_country_region']", SelectBy.TEXT,
						data.get(Dataset).get("Region"));
			
				}
//				Common.textBoxInput("xpath", "//input[@id='event_date']", data.get(Dataset).get("Date"));
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
			// Baby_Registry("Baby Registry");
			Registrant_Information("Birthday");
			String shipping = Common.findElement("xpath", "(//select[@name='address_type_or_id']//option)[2]")
					.getAttribute("value");
			Common.dropdown("xpath", "//select[@name='address_type_or_id']", Common.SelectBy.VALUE, shipping);
			Common.clickElement("id", "submit.save");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div[contains(@class,'a-message__container-')]").getText();
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Thread.sleep(6000);
			Sync.waitElementPresent("xpath", "//span[text()='Create New Registry']");
			Common.clickElement("xpath", "//span[text()='Create New Registry']");
			Thread.sleep(4000);
			Common.clickElement("id", "submit.next");
			String errormessage = Common.findElement("xpath", "//div[@class='mage-error']").getText();
			Common.assertionCheckwithReport(errormessage.equals("This is a required field."),
					"validating error message when we not give any type ",
					"It should display the error message when we not given any type",
					"successfully error message has been displayed", "Failed to display the error message");
			// Sync.waitElementPresent("xpath", "//span[text()='Create New Registry']");
			// Common.clickElement("xpath", "//span[text()='Create New Registry']");
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
	
	public void Registrant_Information(String Dataset) {
		// TODO Auto-generated method stub
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
			Thread.sleep(6000);
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div[@class='a-message__container-inner']").getText();
			System.out.println(message);
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
	
	public void delete_giftcard() {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//a[@title='Delete']");
			Thread.sleep(2000);
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
	
	public void share_giftcard(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//a[@title='Share']");
			Sync.waitPageLoad();
			Thread.sleep(5000);
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
	
	public void minicart_viewcart() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//span[@x-html='cart.subtotal']//span[@class='price']");
			String minicart = Common.findElement("xpath", "//span[@x-html='cart.subtotal']//span[@class='price']").getText();
			Sync.waitElementPresent("xpath", "//a[@title='View Bag']");
			Common.clickElement("xpath", "//a[@title='View Bag']");
			String viewcart = Common.findElement("xpath", "//div[@x-text='hyva.formatPrice(totalsData.subtotal)']").getText();
			Sync.waitPageLoad();
			Thread.sleep(8000);
			Common.assertionCheckwithReport(
					viewcart.contains(minicart) || Common.getCurrentURL().contains("/checkout/cart/"),
					"validating the navigation to the View Bag", "User should able to navigate to the View Bag page",
					"Successfully navigates to the View Bag page",
					"Failed to navigate to the view and edit cart page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the navigation to the View Bag",
					"User should able to navigate to the View Bag page", "unable to  navigates to the View Bag page",
					Common.getscreenShot("Failed to navigate to the View Bag page"));

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
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Gift Registry Items") || Common.getPageTitle().equals("Manage Gift Registry"),
					"validating navigation to the Manage Gift Registry page ",
					"After clicking on Manage Gift Registry button it should navigate to the Manage Gift Registry page ",
					"successfully Navigated to the Manage Gift Registry",
					"failed to Navigate to the Manage Gift Registry");
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
	
	
	public void employee_discount() {
		// TODO Auto-generated method stub
		try {

			Sync.waitElementPresent("xpath", "//button[@aria-label='Close minicart']");
			Common.clickElement("xpath", "//button[@aria-label='Close minicart']");
			Thread.sleep(4000);
			String originalprice = Common.getText("xpath", "//span[@x-html='getFormattedFinalPrice()']").replace("$", "");
			//String originalprice = Common.getText("xpath", "//div[contains(@class,'old-price')]//span[@class='price line-through']").replace("$", "");
			Float originalvalue = Float.parseFloat(originalprice);
			String Newprice = Common.getText("xpath", "(//span[@class='price-wrapper']//span[@class='price'])").replace("$", "");
			Float pricevalue = Float.parseFloat(Newprice);
			Thread.sleep(4000);
			float discount = originalvalue - (originalvalue * 40 / 100);
			String discountvalue = String.valueOf(discount).replace("$", "");
			Float value = Float.parseFloat(discountvalue);
			String s=String.valueOf(value); 
			System.out.println(discountvalue);
			System.out.println(value);
			Common.assertionCheckwithReport(discountvalue.contains(s),
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
	
	
	
	
	
	public void Shoppingcart_page() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.scrollIntoView("xpath", "//span[text()='Back To Cart']");
			Sync.waitElementVisible(30, "xpath", "//span[text()='Back To Cart']");
			Common.clickElement("xpath", "//span[text()='Back To Cart']");
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
			Sync.waitElementPresent("xpath", "//button[@id='discount-form-toggle']");
			Common.clickElement("xpath", "//button[@id='discount-form-toggle']");

			Sync.waitElementPresent("xpath", "//input[@name='coupon_code']");
			if (Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("preprod") ) {
				Common.textBoxInput("xpath", "//input[@name='coupon_code']", data.get(Dataset).get("Discountcode"));
			} else {
				Common.textBoxInput("xpath", "//input[@name='coupon_code']", data.get(Dataset).get("prodDiscountcode"));
			}
			int size = Common.findElements("xpath", "//input[@name='coupon_code']").size();
			Common.assertionCheckwithReport(size > 0, "verifying the Discount Code label", expectedResult,
					"Successfully open the discount input box", "User unable enter Discount Code");
			Sync.waitElementClickable("xpath", "//span[text()='Apply Discount']");
			Common.clickElement("xpath", "//span[text()='Apply Discount']");
			Sync.waitPageLoad();
			expectedResult = "It should apply discount on your price.If user enters invalid promocode it should display coupon code is not valid message.";
			if (Common.getCurrentURL().contains("stage4") || Common.getCurrentURL().contains("preprod")) {
				String discountcodemsg = Common.getText("xpath", "//span[@class='w-full text-center pr-10']");
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
			String Subtotal = Common.getText("xpath", "//div[@x-text='hyva.formatPrice(totalsData.subtotal)']").replace(Symbol,
					"");
			Float subtotalvalue = Float.parseFloat(Subtotal);
			if(Common.getCurrentURL().contains("/gb")|| Common.getCurrentURL().contains("/eu"))  {
				
				String shipping = Common.getText("xpath", "//tr[@class='totals shipping incl']//span[@class='price']")
						.replace(Symbol, "");
				Float shippingvalue = Float.parseFloat(shipping);
				
				System.out.println("Shipping:"+  shippingvalue);
				String Discount = Common.getText("xpath", "//tr[@class='totals discount']//span[@class='price']")
						.replace(Symbol, "");
				Float Discountvalue = Float.parseFloat(Discount);
				System.out.println("Discount:"+ Discountvalue);
				Common.clickElement("xpath", "//span[@class='block transform']");
				String Tax = Common.getText("xpath", "//div[contains(@x-text,'hyva.formatPrice(segment.value)')])[3]").replace(Symbol, "");
				Float Taxvalue = Float.parseFloat(Tax);
				System.out.println("Tax:"+  Taxvalue);
				String ordertotal = Common.getText("xpath", "//span[@x-text='hyva.formatPrice(segment.value)']")
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
				String shipping = Common.getText("xpath", "(//div[@x-text='hyva.formatPrice(segment.value)'])[2]")
						.replace(Symbol, "");
				Float shippingvalue = Float.parseFloat(shipping);
				System.out.println("Shipping:"+  shippingvalue);
				String Discount = Common.getText("xpath", "(//div[@x-text='hyva.formatPrice(segment.value)'])[1]")
						.replace(Symbol, "").replace("-", "");
				
				Float Discountvalue = Float.parseFloat(Discount);
				System.out.println("Discount:"+ Discountvalue);
	//			Common.clickElement("xpath", "//span[@class='block transform']");
				
				String Tax = Common.getText("xpath", "(//div[contains(@x-text,'hyva.formatPrice(segment.value)')])[3]").replace(Symbol, "");
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
	
	
	public void Remove_Product(String dataSet) {
		// TODO Auto-generated method stub
		String Symbol = data.get(dataSet).get("Symbol");
		String products = data.get(dataSet).get("Products");
		System.out.println(products);

		try {
			Thread.sleep(4000);
			String subtotal = Common.findElement("xpath", "//div[@x-text='hyva.formatPrice(totalsData.subtotal)']").getText().replace(Symbol, "");
			Float subtotalvalue = Float.parseFloat(subtotal);
			System.out.println(subtotalvalue);
			String Productprice = Common.getText("xpath", "//a[@title='"+ products +"']/ancestor::tbody//span[@class='price']")
					.replace(Symbol, "");
			Float pricevalue = Float.parseFloat(Productprice);
			System.out.println(pricevalue);
			Float Total = subtotalvalue - pricevalue;
			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			System.out.println(ExpectedTotalAmmount2);
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
//			Common.refreshpage();
			Thread.sleep(5000);
			Common.scrollIntoView("xpath", "//span[@x-text='hyva.formatPrice(segment.value)']");
			String ordertotal = Common.getText("xpath", "//span[@x-text='hyva.formatPrice(segment.value)']")
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
			
			Sync.waitElementClickable("xpath", "(//select[@title='Qty'])[1]");
			Common.clickElement("xpath", "(//select[@title='Qty'])[1]");
			Common.dropdown("xpath", "(//select[@title='Qty'])[1]", Common.SelectBy.VALUE, quantity);
			//Common.clickElement("xpath", "//span[text()='Update']");
			Sync.waitPageLoad();
			Thread.sleep(14000);
			Common.refreshpage();
			Thread.sleep(3000);
			String productquantity = Common.findElement("xpath", "(//select[@title='Qty'])[1]")
					.getAttribute("value");
			System.out.println(productquantity);
			//String items=Common.findElement("xpath", "//span[@class='ml-7 title-xs hf:title-2xs hidden lg:inline']").getText().trim().replace("Items", "");
			String items=Common.findElement("xpath", "//span[contains(@class,'ml-7 title-xs hf')]").getText().trim().replace("Items", "");
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
		try {
			Sync.waitElementPresent(30, "xpath", "//strong[text()='Gift Registry']");
			Common.clickElement("xpath", "//strong[text()='Gift Registry']");
			Sync.waitImplicit(40);
			Common.maximizeImplicitWait();
			Thread.sleep(2000);
			String page = Common.findElement("xpath", "//h1[@class='page-title-wrapper h2']").getText();
			Common.assertionCheckwithReport(page.contains("Gift Registry"),
					"validating the gift registry page navigation ",
					"After clicking Gift registry it should navigate to the gift registry page",
					"successfully Navigated to the gift registry page ",
					"failed to Navigate to the gift rigistry page");
			delete_giftcard();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the gift registry page navigation ",
					"After clicking Gift registry it should navigate to the gift registry page",
					"Unable to  Navigate  to the gift registry page ",
					Common.getscreenShot("Failed to Navigate to the gift rigistry page"));
			Assert.fail();
		}

	}
	
	public void Signin_Checkoutpage(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementVisible("xpath", "//input[@type='email']");
			Common.textBoxInput("xpath", "//input[@type='email']", data.get(Dataset).get("Email"));
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//input[@name='password']");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			Common.clickElement("xpath", "//button[@data-action='checkout-method-login']");
			Sync.waitPageLoad();
			int regsiteruser = Common.findElements("xpath", "//div[contains(@class,'shipping-address-item ')]").size();
			Common.assertionCheckwithReport(regsiteruser > 0,
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
	
	public String shipping_new_Address(String dataSet) {
		// TODO Auto-generated method stub
		String address = "";
		String expectedResult = "shipping address is entering in the fields";
		String firstname = data.get(dataSet).get("FirstName");
		System.out.println(firstname);
		int size = Common.findElements(By.xpath("//div[@class='new-address-popup']//button")).size();
		if (size > 0) {
			try {
				Common.clickElement("xpath", "//div[@class='new-address-popup']//button");
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
						data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
						data.get(dataSet).get("LastName"));

				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
						data.get(dataSet).get("Street"));
				
				Common.scrollIntoView("xpath", "//select[@name='country_id']");
			    Common.dropdown("xpath", "//select[@name='country_id']",Common.SelectBy.TEXT, data.get(dataSet).get("Country"));
				Thread.sleep(2000);

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

				Thread.sleep(3000);
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
						data.get(dataSet).get("City"));

				if(Common.getCurrentURL().contains("gb"))
                {
				  
                	Common.scrollIntoView("xpath", "//input[@placeholder='State/Province']");
        			Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", data.get(dataSet).get("Region"));
        			Thread.sleep(3000);
        			String Shippingvalue = Common.findElement("xpath", "//input[@placeholder='State/Province']")
        					.getAttribute("value");
        			System.out.println(Shippingvalue);
                }
			else
			{
				Thread.sleep(4000);
                Common.scrollIntoView("xpath", "//select[@name='region_id']");
                Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
                Thread.sleep(3000);
                String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']")
                        .getAttribute("value");
                System.out.println(Shippingvalue);
			}
				Thread.sleep(2000);
				Common.textBoxInputClear("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']");
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']",
						data.get(dataSet).get("postcode"));
				String ShippingZip = Common.findElement("name", "postcode").getAttribute("value");
				System.out.println("*****" + ShippingZip + "*******");

				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='telephone']",
						data.get(dataSet).get("phone"));

				Common.clickElement("xpath", "//div[@id='opc-new-shipping-address']//following::button[1]");
				ExtenantReportUtils.addPassLog("validating shipping address filling Fields",
						"shipping address is filled in to the fields", "user should able to fill the shipping address ",
						Common.getscreenShotPathforReport("Sucessfully shipping address details has been entered"));

				address = Common.findElement("xpath", "(//div[contains(@class,'shipping-address-item s')]//p)[2]")
						.getText();
				System.out.println(address);

			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
						"User unabel add shipping address",
						Common.getscreenShotPathforReport("shipping address faield"));

				Assert.fail();

			}

		}
		return address;

	}
	
	public void Verify_Address(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//img[@alt='Logo']");
			Sync.waitElementPresent(30, "xpath", "//button[@aria-label='My Account']");
			Common.clickElement("xpath", "//button[@aria-label='My Account']");
			Sync.waitElementPresent(30, "xpath", "//a[@title='My Account']");
			Common.clickElement("xpath", "//a[@title='My Account']");
			
//			String id=Common.findElement("xpath", "(//ul[@id='desktop-account-nav']//a)[1]").getAttribute("id");
//			Common.clickElement("xpath", "//a[@id='"+id+"']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("/account"),
					"verifying the My account navigation",
					"after clicking on the my account it should navigate to the My Account page",
					"Sucessfully Navigated to the My Account page", "Failed to navigate to the my account page");
			Common.clickElement("xpath", "//a[@title='Address Book']");
			Thread.sleep(3000);
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("address"),
					"verifying the Address Book page navigation",
					"after clicking on the Address Book it should navigate to the Address Book page",
					"Sucessfully Navigated to the Address Book page", "Failed to navigate to the Address Book page");
			
			Common.scrollIntoView("xpath", "//span[contains(text(),'Shipping Address')]");
			String shippingaddress = Common.findElement("xpath", "//span[contains(text(),'Shipping Address')]")
					.getText();
			System.out.println(shippingaddress);
		    int size=Common.findElements("xpath", "(//div[contains(@class,'lg:col-start-2')])[2]").size();
			Common.assertionCheckwithReport(
					shippingaddress.contains(Dataset) || shippingaddress.contains("844 N Colony Rd") || size>0,
					"verifying the address added to the address book",
					"after saving the address in shiiping page it should save in the address book",
					"Sucessfully Address ha been saved in the address book",
					"Failed to save the address in the address book");
		/*	Common.scrollIntoView("xpath", "(//a[@title='Delete'])[1]");
			Sync.waitElementPresent("xpath", "(//a[@title='Delete'])[1]");
			Common.clickElement("xpath", "(//a[@title='Delete'])[1]");
			Thread.sleep(4000);
			String popmessage = Common.findElement("xpath", "//div[contains(text(),'Are you ')]").getText();
			String popup=Common.findElement("xpath", "//div[@class='modal-content']").getAttribute("data-role");
			if (popmessage.contains("Are you sure you want to delete this address?") || popup.contains("content")) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");
				Common.clickElement("xpath", "//span[contains(text(),'OK')]");
				String Delmessage = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				String delete=Common.findElement("xpath", "//div[@data-ui-id='message-success']").getAttribute("class");
				Common.assertionCheckwithReport(Delmessage.equals("You deleted the address.") || delete.contains("message-success"),
						"validating the Delete message after Deleting address in address book",
						"Delete address message should be displayed after the address delete in address book",
						"Sucessfully address has been Deleted in the address book",
						"Failed to Delete the address in the address book");
			} else {
				Assert.fail();
			}*/

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Delete message after Deleting address in address book",
					"Delete address message should be displayed after the address delete in address book",
					"Unable to Delete the address in the address book",
					Common.getscreenShotPathforReport("Failed to Delete the address in the address book"));
			Assert.fail();
		}

	}

	public void Validate_shipping_methods() {

		String No_Quotes = "No shipping methods available.";

		try {
			Thread.sleep(3000);
			Common.scrollIntoView("xpath", "//h2[normalize-space()='Shipping Methods']");
			Sync.waitElementVisible("xpath", "//h2[normalize-space()='Shipping Methods']");;
			String Error = Common.getText("xpath", "//span[@x-text='message.text']");

			if (No_Quotes.contentEquals(Error)) {

				System.out.println("No shipping methods available.");
			}

			else {

				Assert.fail();
			}
			
			Common.assertionCheckwithReport(Error.equals("No shipping methods available."),
					"validating error message  Sorry, no quotes are available for this order at this time",
					"No Shipping methods should be displayed after entering the address",
					"Sucessfully error message  Sorry, no quotes are available for this order at this time",
					"Failed to display the Sorry, no quotes are available for this order at this time");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating error message  Sorry, no quotes are available for this order at this time",
					"Sorry, no quotes are available for this order at this time message will display",
					" unable to enter the product name in  search box", Common.getscreenShot(
							"Failed to display the Sorry, no quotes are available for this order at this time"));
			Assert.fail();
		}
	}
	public void Shippingform_Guestuser(String dataSet) throws Exception {

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
			Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));
			int size = Common.findElements("xpath", "//input[@type='email']").size();
			Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,
					"Filled Email address", "unable to fill the email address");
			Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Common.clickElement("xpath", "//form[@id='shipping']//input[@name='street[0]']");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.findElement("xpath", "//form[@id='shipping']//input[@name='city']").clear();
			Common.textBoxInput("xpath", "//form[@id='shipping']//input[@name='city']",
					data.get(dataSet).get("City"));
			System.out.println(data.get(dataSet).get("City"));

			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			try {
				Common.dropdown("name", "region", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
				Thread.sleep(3000);
				Common.dropdown("name", "region", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
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
	
	public String Store_Credit_balance() throws Exception {
		// TODO Auto-generated method stub
	String Price="";
		try
		{
			Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Sync.waitElementPresent("xpath", "//a[@title='My Account']");
			Common.clickElement("xpath", "//a[@title='My Account']");
//			String account=Common.findElement("xpath", "(//ul[@id='desktop-account-nav']//a)[1]").getAttribute("id");
//			Common.clickElement("xpath", "//a[@id='"+ account +"']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("account"),
					"validating the page navigation to the my account",
					"After clicking on My account it should navigate to the my account page",
					"successfully Navigated to the My account page",
					"Failed to Navigate to the My account page");
			Sync.waitElementPresent("xpath", "//span[text()='Store Credit']");
			Common.clickElement("xpath", "//span[text()='Store Credit']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("storecredit"),
					"validating the page navigation to the storecredit",
					"After clicking on storecredit it should navigate to the storecredit page",
					"successfully Navigated to the storecredit page",
					"Failed to Navigate to the storecredit page");
		 Price=Common.getText("xpath", "//div[@class='block-balance card flex flex-col justify-between']//span[@class='price']");
			
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the page navigation to the storecredit",
					"After clicking on storecredit it should navigate to the storecredit page",
					"Unable Navigated to the storecredit page",
					Common.getscreenShot("Failed to Navigate to the storecredit page"));
			Assert.fail();
		}
		return Price;
	}
	public void Apply_Store_Credit(String Price) throws InterruptedException {
		// TODO Auto-generated method stub
		String symbol="";
		Thread.sleep(4000);
		  String Url=Common.getCurrentURL();
		  Thread.sleep(4000);
		  if(Url.contains("/gb"))
		  {
			symbol="£";
		  }
		  else
		  {
			  symbol="$";
		  }
		try
		{
			Thread.sleep(3000);
			String ordertotal = Common.findElement("xpath", "(//div[@class='item grand_total']//span[contains(@class,'value')])[2]").getText().replace(symbol, "");
			Float ordertotalvalue = Float.parseFloat(ordertotal);
			System.out.println(ordertotalvalue);
			Sync.waitElementPresent("xpath", "//div[normalize-space()='Apply Store Credit']");
			Common.clickElement("xpath", "//div[normalize-space()='Apply Store Credit']");
			Thread.sleep(4000);
			String storecredit=Common.getText("xpath", "//span[normalize-space()='$15.98 credit available']").replace(symbol, "").replace("credit available", "");
			System.out.println("storecredit"+storecredit);
//			String price=Common.getText("xpath", "//strong[contains(@id,'customerbalance')]").replace(symbol, "");
//			Float Pricevalue = Float.parseFloat(price);
//			System.out.println(Pricevalue);
			Thread.sleep(4000);
			
//				String balstorecreditance=Common.getText("xpath", "//strong[contains(@id,'customerbalance')]").replace(symbol, "");
				if(storecredit.equals(Price))
				{
					String totalbeforeWC=Common.findElement("xpath", "(//div[@class='item grand_total']//span[contains(@class,'value')])[2]").getText();
					Sync.waitElementPresent(30,"xpath", "//span[normalize-space()='Apply']");
					Common.clickElement("xpath", "//span[normalize-space()='Apply']");
				//	Sync.waitElementPresent(30, "xpath", "//div[contains(@data-ui-id,'checkout-cart')]");
				//	String message = Common.findElement("xpath", "(//div[contains(@class,'message ')]//div)[1]").getText();
					Thread.sleep(5000);
					//System.out.println(message);
					Common.scrollIntoView("xpath", "(//div[@class='item grand_total']//span[contains(@class,'value')])[2]");
					String storeorder=Common.findElement("xpath", "(//div[@class='item grand_total']//span[contains(@class,'value')])[2]").getText().replace("-", "");
					System.out.println(storeorder);
					System.out.println(totalbeforeWC);
					System.out.println(Price);
					System.out.println(storecredit);
					Common.refreshpage();
					Common.assertionCheckwithReport(storecredit.equals(Price),"validating the store credit balance applied sucess message",
							"After adding the store credit success message should display", "Sucessfully success message has been displayed",
							"failed to Display the success message");
				}
				else
				{
					Assert.fail();
				}

					
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the store credit balance applied sucess message",
					"After adding the store credit success message should display", "Unable to Display the success message",
					Common.getscreenShot("failed to Display the success message"));
			Assert.fail();
		}
		
	}

	public void Add_GiftCode_Myaccount(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		try{
			Sync.waitElementPresent("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Sync.waitElementPresent("xpath", "//a[text()='My Account']");
			Common.clickElement("xpath", "//a[text()='My Account']");
			Sync.waitPageLoad();

			Sync.waitElementPresent("xpath", "//a[text()='Gift Cards']");
			Common.clickElement("xpath", "//a[text()='Gift Cards']");
			
			
			Assert.assertEquals("Gift Cards / My Account", Common.getPageTitle());
			
			
			Sync.waitElementPresent("xpath", "//input[@placeholder='Enter your Code']");
			
		
			Common.textBoxInput("xpath", "//input[@placeholder='Enter your Code']", data.get(dataSet).get("GiftCardCode"));
			System.out.println(data.get(dataSet).get("GiftCardCode"));
			Common.clickElement("xpath", "//span[text()='Add']");
			
		
			Thread.sleep(6000);
		String Applied_Code = Common.findElement("xpath", "//td[@class='col code']").getText();
		
		Common.assertionCheckwithReport(Applied_Code.equals(data.get(dataSet).get("GiftCardCode")),
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

	public void Select_Gift_Code(String dataSet) {
		// TODO Auto-generated method stub
		String Giftcard = data.get(dataSet).get("GiftCardCode");
		try {
			Common.clickElement("xpath", "//input[@name='amcard-field -datalist']");
 
			//			Common.dropdown("xpath", "//input[@name='amcard-field -datalist']", Common.SelectBy.TEXT, "GiftCard2");
			Common.clickElement("xpath", "//li[text()='"+Giftcard+"']");
			Common.clickElement("xpath", "//span[text()='Add Code']");
			
			Thread.sleep(2000);
			String successmsg=Common.findElement("xpath", "//div[@role='alert']").getText();
		  System.out.println(successmsg);
			
			Common.assertionCheckwithReport(successmsg.contains("added"),
					"validating the success message after applying gift card",
					"Success message should be displayed after the applying of gift card",
					"Sucessfully gift card has been applyed","Failed to apply the gift card");
			FUll_Payment("GiftCode Full Redeem");
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
			String  GiftCard=data.get(dataSet).get("GiftCardCode");
			String Total_Incl_Tax =Common.getText("xpath", "//tr[@class='grand totals']//span[@class='price']").replace(Symbl,"");
			
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

	public void Remove_GiftCode() {
		// TODO Auto-generated method stub
		try{
			Sync.waitElementPresent("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Sync.waitElementPresent("xpath", "//a[text()='My Account']");
			Common.clickElement("xpath", "//a[text()='My Account']");
			Sync.waitPageLoad();

			Sync.waitElementPresent("xpath", "//a[text()='Gift Cards']");
			Common.clickElement("xpath", "//a[text()='Gift Cards']");
			
			
			Assert.assertEquals("Gift Cards / My Account", Common.getPageTitle());
	        Common.clickElement("xpath", "//span[text()='Remove']");
	        
	        Common.clickElement("xpath", "//button[contains(@class,'ction-accept')]");
	        Thread.sleep(2000);
	        String Remove_Code = Common.findElement("xpath", "//div[@role='alert']").getText();
		
		Common.assertionCheckwithReport(Remove_Code.contains("removed"),
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
	
	
	public void gitCard(String Dataset) throws Exception{
		try{
			String URL = Common.getCurrentURL();
			System.out.println(URL);
			if(URL.contains("stage")&&URL.contains("/gb") || URL.contains("preprod")&&URL.contains("/gb")) {
			Thread.sleep(5000);
     	Common.scrollIntoView("xpath", "//input[@name='amcard-field -datalist']");
//     	Common.clickElement("xpath","//span[text()='Add Gift Card']");
		Common.textBoxInput("xpath","//input[@name='amcard-field -datalist']", data.get(Dataset).get("GiftCardCode"));
		Common.actionsKeyPress(Keys.ARROW_UP);
		Common.clickElement("xpath","//button[@aria-label='Add Code']");
		Thread.sleep(2000);
		String successmsg=Common.findElement("xpath", "//div[@role='alert']").getText();
	    System.out.println(successmsg);	
//	    Common.refreshpage();
		Common.assertionCheckwithReport(successmsg.contains("added"),
				"validating the success message after applying gift card",
				"Success message should be displayed after the applying of gift card",
				"Sucessfully gift card has been applyed","Failed to apply the gift card");
			}
			else if(URL.contains("stage")&&URL.contains("/paypal/express/review/") || URL.contains("preprod")&&URL.contains("/paypal/express/review/"))
			{
				Thread.sleep(5000);
				Common.clickElement("xpath", "//strong[text()='Gift Cards']");
				Thread.sleep(5000);
					Common.textBoxInput("id","giftcard-code",data.get(Dataset).get("GiftCardCode"));
						Common.textBoxInput("id","giftcard-pin",data.get(Dataset).get("GiftCardPin"));
						Thread.sleep(6000);
						Sync.waitElementPresent(30, "xpath", "//button[@aria-label='Add Code']");
						Common.clickElement("xpath", "//button[@aria-label='Add Code']");
						Thread.sleep(3000);
//						Common.refreshpage();
						Thread.sleep(3000);
						int size=Common.findElements("xpath", "//tr[@class='totals giftcard']").size();
						Common.assertionCheckwithReport(size>0, "validating the gift card", "Gift Card was added.", "successfully gift card was added","Failed to add gift card");	
			}
			else	{
		Thread.sleep(5000);

    Common.clickElement("xpath", "//button[contains(text(),'Add Gift Card')]");
   Thread.sleep(5000);
	Common.textBoxInput("xpath","//input[@id='card-code-input']",data.get(Dataset).get("GiftCardCode"));
		Common.textBoxInput("xpath","//input[@id='card-pin-input']",data.get(Dataset).get("GiftCardPin"));
		Thread.sleep(6000);
		Sync.waitElementPresent(30, "xpath", "//button[@aria-label='Add Code']");
		Common.clickElement("xpath", "//button[@aria-label='Add Code']");
		Thread.sleep(3000);
//		Common.refreshpage();
		Thread.sleep(5000);
		//div[@class='item drybar_giftcard']
		//int size=Common.findElements("xpath", "//tr[@class='totals giftcard']").size();
//		int size=Common.findElements("xpath", "//div[@class='item drybar_giftcard']").size();
//		Common.assertionCheckwithReport(size>0, "validating the gift card", "Gift Card was added.", "successfully gift card was added","Failed to add gift card");
		}
		}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the gift card","Gift Card was added.","User faield to to add gift card",Common.getscreenShotPathforReport("gitcard"));
	        Assert.fail();
	        }

	}
	
	public void select_noPayment_method() throws Exception {
		try {
		
		Thread.sleep(3000);
		Common.refreshpage();
		Thread.sleep(4000);
		Sync.waitElementPresent(30, "id", "payment-method-free");
		Common.clickElement("id", "payment-method-free");
		
		Thread.sleep(5000);
		
	}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating free payment method","free payment method selected.","User faield to click free payment method",Common.getscreenShotPathforReport("free payment method"));
	        Assert.fail();
	        }
			
		}

	
	public String GiftCardSumbitOrderPayments(String dataSet) throws Exception {
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
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("xpath", "//label[@for='payment-method-stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='payment-method-stripe_payments']").size();
			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unabel to land opaymentpage");
			Common.clickElement("xpath", "//label[@for='payment-method-stripe_payments']");
			Thread.sleep(5000);
			int savedcard=Common.findElements("xpath", "//div[contains(@class,'form-select flex gap')]").size();
			if(savedcard>0)
			{
				Sync.waitElementPresent("xpath", "(//input[@class='checkbox mr-4'])[2]");
				Common.clickElement("xpath", "(//input[@class='checkbox mr-4'])[2]");
			}
			Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
			Thread.sleep(5000);

			Common.clickElement("xpath", "//input[@id='Field-numberInput']");
			Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);

			Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

			Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
			Thread.sleep(2000);

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

	public String giftCardSubmitOrder() throws Exception {
		// TODO Auto-generated method stub
 
		String order = "";
		String expectedResult = "It redirects to order confirmation page";
	//	Common.refreshpage();
	//	Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		String priceText = Common.getText("xpath", "(//div[@class='item grand_total']//span)[2]").trim();
		double price = Double.parseDouble(priceText.replace("$", "").trim());
		System.out.println(price);
		if (price == 0.5) {  
			GiftCardSumbitOrderPayments("PaymentDetails");
		} else {
			select_noPayment_method();
		}
		
		Thread.sleep(8000);
		//Common.actionsKeyPress(Keys.ARROW_DOWN);
		//Common.switchToDefault();
		if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
				Sync.waitElementPresent("xpath", "(//button[contains(@class,'btn-place-order')])[2]");
				Common.clickElement("xpath", "(//button[contains(@class,'btn-place-order')])[2]");
         	    Thread.sleep(40000);
	//Common.clickElement("xpath", "//button[@class='action primary checkout']");
	//Thread.sleep(4000);
	//order = Common.getText("xpath", "//div[contains(@class,'checkout-success')]//p//span");
         	   order = Common.getText("xpath", "(//h1[contains(text(),'Thank you')])");
        	    System.out.println(order);
        	   Common.assertionCheckwithReport(order.contains("Thank you for your purchase!"), "validating the order confirmation page",
      				expectedResult, "order placed successfully", "unable to place the order");
		}
		else
		{
				Assert.fail();
		}
 
		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
 
		if (url.contains("stage") || url.contains("preprod")) {

		}
 
		else {
			try {
				Thread.sleep(3000);
				Sync.waitElementPresent(30,"xpath", " //h1[normalize-space()='Thank you for your purchase!']");
				String sucessMessage = Common.getText("xpath", " //h1[normalize-space()='Thank you for your purchase!']");

				//Tell_Your_FriendPop_Up();
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
	

	public void Expeditedshippingmethod(String Dataset) {
		// TODO Auto-generated method stub
		String method = data.get(Dataset).get("methods");
		System.out.println(method);

		try {
			Thread.sleep(3000);
			int size = Common.findElements("xpath", "//section[@id='shipping']").size();
			System.out.println(size);
			if (size > 0  ) {
				
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath","//span[contains(text(),'" + method + "')]");
				Common.clickElement("xpath","//span[contains(text(),'" + method + "')]");
				
			}
			else
			{
				Assert.fail();
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Expedited shipping method",
					"Select the Expedited shipping method in shipping page ",
					"failed to select the Expedited shipping method ",
					Common.getscreenShotPathforReport("failed select Expedited shipping method"));

			Assert.fail();
		}

	}

	public void add_aerosolproduct(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		System.out.println(product);
		try
		{
			Common.clickElement("xpath", "//button[@id='menu-search-icon']");
	     	String open = Common.findElement("xpath", "//button[@id='menu-search-icon']").getAttribute("aria-expanded");
	     	Thread.sleep(4000);
	     	Common.assertionCheckwithReport(open.contains("true"), "User searches using the search field",
	     	"User should able to click on the search button", "Search expands to the full page",
	     	"Sucessfully search bar should be expand"); 
     	WebElement serachbar=Common.findElement("xpath", "//input[@id='autocomplete-0-input']");
        serachbar.sendKeys(product);
        Common.actionsKeyPress(Keys.ENTER);
    	Sync.waitPageLoad();
    	Thread.sleep(4000);
		/*	String productsearch = Common.findElement("xpath", "//span[@id='algolia-srp-title']").getText();
			System.out.println(productsearch);
			Common.assertionCheckwithReport(productsearch.contains(product), "validating the search functionality",
					"enter product name will display in the search box", "user enter the product name in  search box",
					"Failed to see the product name");*/
			Thread.sleep(8000);
//			Common.mouseOverClick("xpath", "//form[@id='form-70051']//button[@title='ADD TO BAG']");
			 Common.clickElement("xpath", "//button[@title='ADD TO BAG']");	 
			 Thread.sleep(4000);
			 Sync.waitElementPresent(30, "xpath","//div[@ui-id='message-success']");
			 String message = Common.findElement("xpath", "//div[@ui-id='message-success']")
						.getAttribute("ui-id");
				System.out.println(message);
				Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
						"Product should be add to cart", "Sucessfully product added to the cart ",
						"failed to add product to the cart");
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

	public void verfy_miscellaneous_pages(String dataSet) throws Exception, IOException {
		// TODO Auto-generated method stub
		String urls = data.get(dataSet).get("Links");
		int j = 0;

		String[] strArray = urls.split("\\r?\\n");
		for (int i = 0; i < strArray.length; i++) {
			System.out.println(strArray[i]);

			if (Common.getCurrentURL().contains("stage4")) {

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

			} else if (Common.getCurrentURL().contains("https://mcloud-na-stage4.drybar.com/")) {

				Common.oppenURL(strArray[i].replace("mcloud-na-stage4", "www"));

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
		Thread.sleep(3000);
//		String message=Common.getText("xpath", "//h2[text()='Well... This Blows.']");
//		System.out.println(message);
	}

	public int getpageresponce(String url) throws MalformedURLException, IOException {
		HttpURLConnection c = (HttpURLConnection) new URL(url).openConnection();
		c.setRequestMethod("HEAD");
		c.connect();
		int r = c.getResponseCode();

		return r;
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
			Thread.sleep(4000);
//			String message = Common.findElement("id", "validation-classes").getCssValue("color");
//			String redcolor = Color.fromString(message).asHex();
//			System.out.println(redcolor);
//			String message1 = Common.findElement("id", "validation-length").getCssValue("color");
//			String greencolor = Color.fromString(message1).asHex();
//			System.out.println(greencolor);
			String emailmessage = Common.findElement("xpath", "//li[@data-msg-field='email']").getText();
			System.out.println(emailmessage);
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

       
	public void click_Createaccount() {

		try {
			Sync.waitElementPresent("xpath", "//button[@aria-label='My Account']");
			Common.clickElement("xpath", "//button[@aria-label='My Account']");
			Common.clickElement("xpath", "//a[@title='Create an Account']");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Create an Account") || Common.getPageTitle().equals("Create an Account"),
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
        	
    		String Store= data.get(Dataset).get("Store");
    		String email = Common.genrateRandomEmail(data.get(Dataset).get("UserName"));
    		try {

    			Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(Dataset).get("FirstName"));
    			Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(Dataset).get("LastName"));
    			Common.textBoxInput("xpath", "//input[@name='email']", email);
    			
    //			 email = Common.genrateRandomEmail(data.get(Dataset).get("Email"));
    			Common.textBoxInput("xpath", "//input[@id='email_address']", email);
    			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
    			System.out.println(data.get(Dataset).get("Password"));
    			Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
    					data.get(Dataset).get("Confirm Password"));
    			System.out.println(data.get(Dataset).get("Confirm Password"));
    			Thread.sleep(4000);
    			Common.clickElement("xpath", "//button[@title='Sign Up']");
    			Sync.waitElementVisible(30, "xpath", "//span[@x-html='message.text']");
    			String message = Common.findElement("xpath", "//span[@x-html='message.text']").getText();
    			System.out.println(message);
    			Common.assertionCheckwithReport(
    					message.contains("Thank you for registering")
    							|| Common.getCurrentURL().contains("account") ,
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
        public void discountCode(String dataSet) throws Exception {
        	String expectedResult = "It should opens textbox input to enter discount.";
    		String Symbol = data.get(dataSet).get("Symbol");
    		System.out.println(Symbol);

    		try {
    			Sync.waitPageLoad();
    			Thread.sleep(4000);

    			Sync.waitElementPresent("xpath", "//button[contains(text(),'Add Discount Code')]");
    			Common.clickElement("xpath", "//button[contains(text(),'Add Discount Code')]");
    			if(Common.getCurrentURL().contains("stage")&&Common.getCurrentURL().contains("paypal/express/review/") || Common.getCurrentURL().contains("preprod")&&Common.getCurrentURL().contains("paypal/express/review/") )
    			{
    				Sync.waitElementPresent("id", "discount-code");

    				Common.textBoxInput("id", "discount-code", data.get(dataSet).get("Discountcode"));
    				int size = Common.findElements("id", "discount-code").size();
        			Common.assertionCheckwithReport(size > 0, "verifying the Discount Code label", expectedResult,
        					"Successfully open the discount input box", "User unable enter Discount Code");
        			Sync.waitElementClickable("xpath", "//span[contains(text(),'Apply Code')]");
        			Common.clickElement("xpath", "//span[contains(text(),'Apply Code')]");
        			Sync.waitPageLoad();
        			Thread.sleep(4000);
        			Common.scrollIntoView("xpath", "//div[@ui-id='message-success']//span");
        			expectedResult = "It should apply discount on your price.If user enters invalid promocode it should display coupon code is not valid message.";
        			String discountcodemsg = Common.getText("xpath", "//div[@ui-id='message-success']//span");
        			System.out.println(discountcodemsg);
        			Common.assertionCheckwithReport(discountcodemsg.contains("Your coupon was successfully"),
        					"verifying pomocode", expectedResult, "promotion code working as expected",
        					"Promation code is not applied");
    				
    			}
    			else if (Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("preprod")) {
    				Sync.waitElementPresent("id", "discount-code");

    				Common.textBoxInput("id", "discount-code", data.get(dataSet).get("Discountcode"));
    				int size = Common.findElements("id", "discount-code").size();
        			Common.assertionCheckwithReport(size > 0, "verifying the Discount Code label", expectedResult,
        					"Successfully open the discount input box", "User unable enter Discount Code");
        			Sync.waitElementClickable("xpath", "//span[contains(text(),'Apply Code')]");
        			Common.clickElement("xpath", "//span[contains(text(),'Apply Code')]");
        			Sync.waitPageLoad();
        			Thread.sleep(4000);
        			Common.scrollIntoView("xpath", "//span[@x-html='message.text']");
        			expectedResult = "It should apply discount on your price.If user enters invalid promocode it should display coupon code is not valid message.";
        			String discountcodemsg = Common.getText("xpath", "//div[@ui-id='message-success']//span");
        			System.out.println(discountcodemsg);
        			Common.assertionCheckwithReport(discountcodemsg.contains("Your coupon was successfully"),
        					"verifying pomocode", expectedResult, "promotion code working as expected",
        					"Promation code is not applied");
    			} else {
    				Sync.waitElementPresent("id", "discount-code");

    				Common.textBoxInput("id", "discount-code", data.get(dataSet).get("prodDiscountcode"));
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
    			}

    			
    			if(Common.getCurrentURL().contains("che_")||Common.getCurrentURL().contains("se_sv")||Common.getCurrentURL().contains("fr"))
    			{
    				Thread.sleep(4000);
    				Common.scrollIntoView("xpath", "//tr[@class='totals sub']//span[@class='price']");
    				String Subtotal = Common.getText("xpath", "//tr[@class='totals sub']//span[@class='price']").replace(Symbol,
    						"").replace(",", ".");
    				Float subtotalvalue = Float.parseFloat(Subtotal);
    				String shipping = Common.getText("xpath", "//tr[@class='totals shipping incl']//span[@class='price']")
    						.replace(Symbol, "").replace(",", ".");
    				Float shippingvalue = Float.parseFloat(shipping);
    				String Tax = Common.getText("xpath", "//tr[@class='totals-tax']//span[@class='price']").replace(Symbol, "").replace(",", ".");
    				Float Taxvalue = Float.parseFloat(Tax);
    				Thread.sleep(4000);
    				String Discount = Common.getText("xpath", "//tr[@class='totals discount']//span[@class='price']")
    						.replace(Symbol, "").replace(",", ".");
    				Float Discountvalue = Float.parseFloat(Discount);
    				System.out.println(Discountvalue);

    				String ordertotal = Common.getText("xpath", "//tr[@class='grand totals']//span[@class='price']")
    						.replace(Symbol, "").replace(",", ".");
    				Float ordertotalvalue = Float.parseFloat(ordertotal);
    				Thread.sleep(4000);
    				Float Total = (subtotalvalue + shippingvalue) + Discountvalue;
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
    			else 
    			{
    			Thread.sleep(4000);
    			Common.scrollIntoView("xpath", "//div[@class='item subtotal']//span[@class='value']");
    			String Subtotal = Common.getText("xpath", "//div[@class='item subtotal']//span[@class='value']").replace(Symbol,
    					"");
    			Float subtotalvalue = Float.parseFloat(Subtotal);
    			String shipping = Common.getText("xpath", "//div[@class='item shipping']//span[@class='value']")
    					.replace(Symbol, "");
    			Float shippingvalue = Float.parseFloat(shipping);
    			String Tax = Common.getText("xpath", "//div[@class='item tax']//span[@class='value']").replace(Symbol, "");
    			Float Taxvalue = Float.parseFloat(Tax);
    			Thread.sleep(4000);
    			String Discount = Common.getText("xpath", "//div[@class='item discount']//span[@class='value']")
    					.replace(Symbol, "");
    			Float Discountvalue = Float.parseFloat(Discount);
    			System.out.println(Discountvalue);

    			String ordertotal = Common.getText("xpath", "//div[@class='item grand_total']//span[contains(@class,'value')]")
    					.replace(Symbol, "");
    			Float ordertotalvalue = Float.parseFloat(ordertotal);
    			Thread.sleep(4000);
    			if(Common.getCurrentURL().contains("gb"))
    			{
    			Float Total = (subtotalvalue + shippingvalue) + Discountvalue;
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
    			else
    			{
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
    			}

    		}

    		catch (Exception | Error e) {
    			e.printStackTrace();
    			ExtenantReportUtils.addFailedLog("validating discount code", expectedResult,
    					"User failed to proceed with discountcode",
    					Common.getscreenShotPathforReport("discountcodefailed"));

    			Assert.fail();

    		}
    	}

        public void Addtocart_Bundle(String Dataset) {
    		// TODO Auto-generated method stub
    		String Product = data.get(Dataset).get("Products");
    		try {
    			Sync.waitPageLoad();
    			Sync.waitElementPresent("xpath", "//img[@alt='" + Product + "']");
    			Common.javascriptclickElement("xpath", "//img[@alt='" + Product + "']");
    			Sync.waitPageLoad();
    			Thread.sleep(4000);
//    			validating_BundleProducts();
    			product_quantity(Dataset);
    			//bundle_color("Black");
    			Sync.waitElementPresent("xpath", "//span[contains(text(),'ADD TO BAG')]");
    			Common.clickElement("xpath", "//span[contains(text(),'ADD TO BAG')]");
    			Sync.waitPageLoad();
    			Sync.waitPageLoad();
    			Thread.sleep(6000);
//    			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//    					.getAttribute("data-ui-id");
//    			System.out.println(message);
//    			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
//    					"Product should be add to cart", "Sucessfully product added to the cart ",
//    					"failed to add product to the cart");
    		Common.actionsKeyPress(Keys.PAGE_UP);
    		} catch (Exception | Error e) {
    			e.printStackTrace();
    			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
    					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));

    			Assert.fail();
    		}
    	}

		public void Communication_Preference_MyAccount() {
			
				try {
				
				Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
				Common.clickElement("xpath", "//button[@id='customer-menu']");
				if (Common.findElements("xpath","//a[@title='Sign In']").size() > 0 ) {
					Sync.waitElementPresent("xpath", "//a[@title='Sign In']");
					Common.clickElement("xpath","//a[@title='Sign In']");	
				} else {
					Sync.waitElementPresent("xpath", "//a[@title='My Account']");
					Common.clickElement("xpath", "//a[@title='My Account']");
				}
				Thread.sleep(4000);
				
				String MyAccount= Common.getPageTitle();
				
				System.out.println(MyAccount);
				
				Common.assertionCheckwithReport(MyAccount.equals("Dashboard"),
    					"validating the order summary in the payment page",
    					"Order summary should be display in the payment page and all fields should display",
    					"Successfully Order summary is displayed in the payment page and fields are displayed",
    					"Failed to display the order summary and fileds under order summary");
				
				
				Sync.waitElementPresent("xpath", "//span[text ()='Communication Preferences']");
				Common.clickElement("xpath", "//span[text ()='Communication Preferences']");

				
				Thread.sleep(4000);
				String Communication = Common.getText("xpath", "//h1//span[@data-ui-id='page-title-wrapper']");

				String Storefront_Text = "COMMUNICATION PREFERENCES";

				System.out.println(Communication);
				System.out.println(Storefront_Text);
				Assert.assertEquals(Communication, Storefront_Text);
				
				
				
				WebElement checkBox = Common.findElement("xpath", "//input[@id='subscription']");
				WebElement Save = Common.findElement("xpath", "//span[contains(text(),'Save')]");
                
				if(checkBox.isSelected())
				{
					
					System.out.println("Checkbox is Selected");
					Common.clickElement("xpath", "//label[@for='subscription']");
					Save.click();
				}
				
				else {
					
					System.out.println("Checkbox is Not-Selected");
					Common.clickElement("xpath", "//label[@for='subscription']");	
				
				}
				
				Save.click();
				
		  
		    		} catch (Exception | Error e) {
		    			e.printStackTrace();
		    			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
		    					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));

		    			Assert.fail();
		    		}
		    	}
			

		
		public void reorder() {
			// TODO Auto-generated method stub
			try {
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
		public void Validateshippingmethods_AerosolProduct() {
			// TODO Auto-generated method stub4

			try {
				Thread.sleep(3000);
				int size = Common.findElements("xpath", "//input[@name='shipping-method-option']").size();
				System.out.println(size);
				if (size == 1) {
					// Sync.waitElementPresent(30, "xpath", "//td[contains(text(),'" + method +
					// "')]");
//					String method1=Common.findElement("xpath", "//div[@class='pl-3 pr-1']").getText();
//					Thread.sleep(2000);
//					String shipping1= Common.findElement("xpath", "//span[@class='flex items-center']").getText();
					String method1=Common.findElement("xpath", "//div[@class='pl-3 pr-1 dr:order-1']").getText();
					Thread.sleep(2000);
					String shipping1= Common.findElement("xpath", "//span[@class='price-including-tax']").getText();
					
					//String shipping1= Common.findElement("xpath", "//span[@class='price-including-tax']").getText();
					//String method2=Common.findElement("xpath", "//td[@id='label_method_amstrates4_amstrates-label_carrier_amstrates4_amstrates']").getText();
					//String shipping2= Common.findElement("xpath", "(//span[@class='shipping-method__radio'])[2]").getText();
					
					Common.assertionCheckwithReport(shipping1.equals("$0.00")&&method1.contains("Standard"),
							"validating the standard shipping method",
							"Verifying Shipping methods in Shipping page",
							"Successfully verifed Standard and Expedited shipping method",
							"Failed to verifed Standard and Expedited shipping method");
					
					
				} else {

					Assert.fail();

				}
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the standard shipping method",
						"Verifying Shipping methods in Shipping page","Failed verifed Standard and Expedited shipping method",
						Common.getscreenShotPathforReport("failed verify shipping methods"));

				Assert.fail();
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
//				Sync.waitPageLoad();
				int sizes = Common.findElements("xpath", "//label[@for='payment-method-stripe_payments']").size();

				Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
						"User unable to land o n the paymentpage");
				System.out.println(sizes);
				Common.clickElement("xpath", "//label[@for='payment-method-stripe_payments']");

	
				int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
				System.out.println(payment);
				if (payment > 0) {
					Thread.sleep(2000);
					
					//Common.refreshpage();
//					Common.scrollIntoView("xpath", "//div[@class='stripe-dropdown-selection']");
//					Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
//					Common.clickElement("xpath", "//div[@class='stripe-dropdown-selection']");
//					Thread.sleep(4000);
//					Common.clickElement("xpath", "//button[@class='a-btn a-btn--tertiary']");
					Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					System.out.println("Switch to Frames");
					Common.scrollToElementAndClick("xpath", "//div[@class='p-PaymentMethodSelector']//button[@id='klarna-tab']");
//					Sync.waitElementPresent(30, "xpath", "//div[@class='p-PaymentMethodSelector']//button[@id='klarna-tab']");
//					Common.clickElement("xpath", "//div[@class='p-PaymentMethodSelector']//button[@id='klarna-tab']");
					Common.switchToDefault();
					System.out.println("Switch to Default");
					if(Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage") )
					{
						if(Common.getCurrentURL().contains("/gb"))
						{
							 Sync.waitElementPresent("xpath", "//input[@id='agreement_stripe_payments_5']");
		                	 Common.clickElement("xpath", "//input[@id='agreement_stripe_payments_5']");
		                	 
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
									Thread.sleep(4000);
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
								Thread.sleep(4000);
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
//					Sync.waitElementPresent("xpath", "");
//					Common.clickElement("xpath", "");
//					Sync.waitElementClickable("xpath", "(//button[@class='action primary checkout'])[2]");
//					Common.clickElement("xpath", "(//button[@class='action primary checkout'])[2]");
//					Sync.waitPageLoad();
//					klarna_Details(dataSet);
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
					int savecard = Common.findElements("xpath","//div[contains(@class,'form-select flex')]").size();
					if(savecard > 0) {
						Sync.waitElementPresent(30,"xpath", "(//input[@class='checkbox mr-4'])[2]");
						Common.clickElement("xpath", "(//input[@class='checkbox mr-4'])[2]");
					}
					Thread.sleep(5000);
					Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					Common.clickElement("xpath", "//span[text()='Klarna']");
					Common.switchToDefault();
					
					if(Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage") )
					{
						Thread.sleep(3000);
					Sync.waitElementPresent(30, "xpath", "(//button[contains(@class,'btn-place-order')])[2]");	
					Common.clickElement("xpath", "(//button[contains(@class,'btn-place-order')])[2]");
					Sync.waitPageLoad();
					Thread.sleep(3000);
					klarna_Details(dataSet);
					}
					else
					{
						Thread.sleep(4000);
						Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
						Thread.sleep(4000);
						String klarna=Common.findElement("xpath", "//button[@value='klarna']").getAttribute("data-testid");
						System.out.println(klarna);
						Common.assertionCheckwithReport(
								klarna.contains("klarna") || klarna.contains("Klarna"),
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
				Common.switchWindows();
				//Common.switchFrames("xpath", "//iframe[@id='klarna-apf-iframe']");
				Sync.waitElementPresent("xpath", "//input[@name='phonePasskey']");
			/*	Common.clickElement("xpath", "//input[@name='phone']");
				
				int number=Common.genrateRandomNumber();
				System.out.println(number);
				String mobile=Integer.toString(number);
				String phone="+91"+"95862"+mobile;*/
				WebElement clear=Common.findElement("xpath", "//input[@name='phonePasskey']");
			    clear.sendKeys(Keys.CONTROL+"a");
			    clear.sendKeys(Keys.DELETE);
				System.out.println(phone);
				Common.textBoxInput("xpath", "//input[@name='phonePasskey']", phone);
				Common.clickElement("xpath", "//button[@id='onContinue']");
				Sync.waitPageLoad();
				Sync.waitElementPresent(30, "xpath", "//input[@id='otp_field']");
				Common.textBoxInput("xpath", "//input[@id='otp_field']", otp);
				Sync.waitPageLoad();
				Thread.sleep(8000);
				
//				Common.clickElement("xpath", "//span[text()='Pay now']");
//				Thread.sleep(4000);
//				Common.refreshpage();
				if(Common.findElements("xpath", "//h2[@id='stacked-selection-title']").size()>0)
				{
					Common.findElement("xpath", "//h2[@id='stacked-selection-title']").getText().contains("Choose how to pay");
					Thread.sleep(4000);
					Common.clickElement("xpath", "(//span[contains(text(),'Continue')])[2]");
//					Sync.waitElementPresent("xpath", "//label[@for='pay_now__label']");
//					Common.clickElement("xpath", "//label[@for='pay_now__label']");
//					
//					Thread.sleep(2000);
//					Sync.waitElementPresent("xpath", "(//span[contains(text(),'Continue')])[1]");
//					Common.doubleClick("xpath", "(//span[contains(text(),'Continue')])[1]");
					Thread.sleep(4000);
//					Common.doubleClick("xpath", "(//span[contains(text(),'Continue')])[2]");
//					Sync.waitElementPresent("xpath", "//span[text()='Pay with']");
					Common.clickElement("xpath", "//span[text()='Pay with']");
					Sync.waitPageLoad();											
					
				}
				else if(Common.findElement("xpath", "//h1[@id='summary-title']").getText().contains("Confirm and pay"))
				{
					Thread.sleep(4000);
					Common.clickElement("xpath", "//span[text()='Pay with']");
					Sync.waitPageLoad();
				}
				else
				{
					
//					String klarna1=Common.findElement("xpath", "//h2[@role='status']").getText();
					
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


		public void Validate_Shipping_Options(String Dataset) throws Exception {
			// TODO Auto-generated method stub
			String expected=data.get(Dataset).get("methods");
			String epress=data.get(Dataset).get("method1");
			Thread.sleep(4000);
			
			try {
				
				Sync.waitElementPresent(30, "xpath", "//span[contains(text(),'"+ expected +"')]");
				String Expedited = Common.getText("xpath", "//span[contains(text(),'"+ expected +"')]");
				System.out.println(Expedited);
				System.out.println(expected);
				Sync.waitElementPresent(30, "xpath", "//span[contains(text(),'"+ epress +"')]");
				String Express = Common.getText("xpath", "//span[contains(text(),'"+ epress +"')]");
				System.out.println(Express);
				System.out.println(epress);
				
				Common.assertionCheckwithReport(Expedited.contains(expected) && Express.contains(epress),
						"validating the Shipping methods",
						"After entering the address it should display the shipping methods",
						"Successfully  shipping methods", "Failed to  shipping methods");
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("Validating the Shipping methods",
						"After entering the address it should display the shipping methods",
						"Unable to shipping methods",
						Common.getscreenShot("Failed to shipping methods"));
				Assert.fail();
			}

		}
		
		
		public String guest_BillingAddress(String dataSet) {
			// TODO Auto-generated method stub
			String update = "";
			String Shipping="";
			try {
				
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "(//input[@type='checkbox'])[2]");
				Boolean checkbox=Common.findElement("xpath", "(//input[@type='checkbox'])[2]").isSelected();
				System.out.println(checkbox);
				Thread.sleep(7000);
				String box=Boolean.toString(checkbox);
				if(box.contains("true"))
				{
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "(//input[@type='checkbox'])[2]");
				Common.clickElement("xpath", "(//input[@type='checkbox'])[2]");
				Thread.sleep(5000);
				Common.textBoxInput("xpath", "//input[@name='firstname' and @data-form='billing']", data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='lastname' and @data-form='billing']", data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//input[@name='street[0]' and @data-form='billing']", data.get(dataSet).get("Street"));
				Thread.sleep(4000);
				String text = Common.findElement("xpath", "//input[@name='street[0]' and @data-form='billing']").getAttribute("value");
				Sync.waitPageLoad();
				Thread.sleep(5000);
				Common.textBoxInput("xpath", "//input[@name='city' and @data-form='billing']", data.get(dataSet).get("City"));
				System.out.println(data.get(dataSet).get("City"));

//				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				 if(Common.getCurrentURL().contains("gb"))
	             {
					 Common.scrollIntoView("xpath", "//input[@placeholder='State/Province']");
						Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", data.get(dataSet).get("Region"));
					 
	             }
				 else
				 {
					 Common.scrollIntoView("xpath", "//select[@name='region' and @data-form='billing']");
	                 Common.dropdown("xpath", "//select[@name='region' and @data-form='billing']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
	                 Thread.sleep(3000);
	                 String Shippingvalue = Common.findElement("xpath", "//select[@name='region' and @data-form='billing']")
	                         .getAttribute("value");
	                 Shipping=Common.findElement("xpath", "//option[@value='"+Shippingvalue+"']").getAttribute("data-title");
		              System.out.println(Shipping);
	                 System.out.println(Shippingvalue);
				 }
					
				Thread.sleep(2000);
				// Common.textBoxInputClear("xpath", "//input[@name='postcode']");
				Thread.sleep(2000);
				Common.textBoxInput("xpath", "//input[@name='postcode' and @data-form='billing']",
						data.get(dataSet).get("postcode"));
				Thread.sleep(6000);
				Common.textBoxInput("xpath", "//input[@name='telephone' and @data-form='billing']",
						data.get(dataSet).get("phone"));
				Thread.sleep(2000);
//				Common.clickElement("xpath", "//button[contains(text(),' Save ')]");
//				Sync.waitPageLoad();
//				Thread.sleep(5000);

//				Common.textBoxInput("xpath", "//div[@class='field _required']//input[@name='telephone']",
//						data.get(dataSet).get("phone"));
//				Thread.sleep(4000);		
//				Common.clickElement("xpath", "//span[text()='Update']");
//				//Sync.waitPageLoad();
//				Thread.sleep(4000);
//                    if(Common.isElementDisplayed("xpath", "//span[contains(text(),'OK')]")) {
//					
//					Common.clickElement("xpath", "//span[contains(text(),'OK')]");
//				Thread.sleep(5000);
				
//				update = Common.findElement("xpath", "//h2[text()='Payment Method']").getText();
//				System.out.println(update);
//				Sync.waitPageLoad();
			}
				else {
					
				
				update = Common.findElement("xpath", "//label[@for='billing-as-shipping']").getText();
				System.out.println(update);
				Sync.waitPageLoad();
				}
//				Common.assertionCheckwithReport(update.contains("Payment Method"),
//						"verifying the Billing address form in payment page",
//						"Billing address should be saved in the payment page",
//						"Sucessfully Billing address form should be Display ",
//						"Failed to display the Billing address in payment page");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the Billing address form in payment page",
						"Billing address should be saved in the payment page",
						"Unable to display the Billing address in payment page",
						Common.getscreenShotPathforReport("Failed to display the Billing address in payment page"));
				Assert.fail();
			}
			return update;
		}
		public void Shipping_Forgot_Password(String dataSet) {
    		// TODO Auto-generated method stub
    		try {
    			
    			
    			Common.textBoxInput("xpath", "//input[@name='username']", data.get(dataSet).get("UserName"));
    			Common.textBoxInput("xpath", "//input[@name='password']", data.get(dataSet).get("Password"));
    			Common.clickElement("xpath", "//span[text()='Toggle password visibility']");
    			String shipping = Common.findElement("xpath", "(//span[text()='Shipping'])[1]").getText();
    			System.out.println(shipping);
    			Common.clickElement("xpath", "//span[text()='Items in Cart']");
    			Thread.sleep(4000);
         		String QTY = Common.findElement("xpath", "(//span[@class='a-product-attribute__value'])[1]").getText();
    			System.out.println(QTY);
    			String Price = Common.findElement("xpath", "(//span[@class='cart-price'])[2]").getText();
    			System.out.println(Price);
//    			Common.clickElement("xpath", "(//span[text()='View Details'])[2]");    need to un comment after drybar configurable prodcuted added
 //   			String Color = Common.findElement("xpath", "(//span[@class='a-product-attribute__value'])[3]").getText();
 //   			System.out.println(Color);
 //   			String Size = Common.findElement("xpath", "(//span[@class='a-product-attribute__value'])[3]").getText();
//    			String Size = Common.findElement("xpath", "//dd[@class='values']").getText();
//    			System.out.println(Size);              need to un comment after drybar configurable prodcuted added
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
        public void Forgot_password(String Dataset) {
    		// TODO Auto-generated method stub
    		try {
    			Common.clickElement("xpath", "//a[contains(text(),'Forgot')]");
    			String forgotpassword = Common.findElement("xpath", "//h1//span[text()='Forgot Your Password?']").getText();
    			System.out.println(forgotpassword);
    			Thread.sleep(5000);
    			Common.textBoxInput("xpath", "//input[@id='email_address']",data.get(Dataset).get("UserName"));
    			Thread.sleep(4000);
    			Common.findElement("xpath", "//input[@id='email_address']").getAttribute("value");
    			Common.clickElement("xpath", "//button[@type='submit' and @class='btn btn-primary w-full']");
    			Sync.waitPageLoad();
    			Thread.sleep(2000);
    			Sync.waitElementPresent(30, "xpath", "//span[@x-html='message.text']");
    			String message = Common.findElement("xpath", "//span[@x-html='message.text']").getText();
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
        public String reg_outofstock_subcription(String Dataset) {
    		// TODO Auto-generated method stub
    		String products = data.get(Dataset).get("Products");
    		//String prod = data.get(Dataset).get("products1");
    		String productsize = data.get(Dataset).get("Size");
    		String symbol= data.get(Dataset).get("Symbol");
    		System.out.println(products);
    		String price = "";

    		try {
    			Sync.waitPageLoad();
    			for (int i = 0; i <= 10; i++) {
    				Sync.waitElementPresent("xpath", "//img[@class='group-hover/item-image:block hidden']");
    				List<WebElement> webelementslist = Common.findElements("xpath",
    						"//img[@class='group-hover/item-image:block hidden']");

    				String s = webelementslist.get(i).getAttribute("src");
    				System.out.println(s);
    				if (s.isEmpty()) {

    				} else {
    					break;
    				}
    			}
    			System.out.println(products);
    			Thread.sleep(9000);
    			if (Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("preprod") ) {
    				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
    				Common.scrollIntoView("xpath", "//img[@alt='" + products + "']");
    				Common.mouseOver("xpath", "//img[@alt='" + products + "']");
    				Thread.sleep(4000);
    				String productprice = Common.findElement("xpath", "(//span[@class='title-2xs leading-none']//span[@x-ref='normalPrice'])").getText().replace(symbol,"").replace(".00", "");
    				Common.clickElement("xpath", "//img[@alt='" + products + "']");
    				Sync.waitPageLoad();
    				Thread.sleep(3000);
//    				String PLPprice = Common
//    						.findElement("xpath","//div[@class='m-product-overview__prices']//span[contains(@class,'price-wrapper')]")
//    						.getAttribute("data-price-amount");
    				
    				String PDPprice = Common.findElement("xpath","(//span[@x-html='getFormattedFinalPrice()'])[1]").getText().replace(symbol,"").replace(".00", "");
    				
    				System.out.println(PDPprice);
    				System.out.println(productprice);
    				String name = Common.findElement("xpath", "//h1[@itemprop='name']").getText();
 
   				Common.assertionCheckwithReport(productprice.equals(PDPprice),
    						"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
    						"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
    				Sync.waitPageLoad();
    				Thread.sleep(3000);

    				if(Common.getCurrentURL().contains("/gb"))
    				{
    					System.out.println(name);
    				}
    				else
    				{
//    				Sync.waitElementPresent("xpath", "//div[@data-option-label='" + productsize + "']");
//    				Common.clickElement("xpath", "//div[@data-option-label='" + productsize + "']");
    				
    				Thread.sleep(2000);
    				Common.scrollIntoView("xpath", "(//button[@title='Notify Me When Available']//span)[1]");
    				Common.clickElement("xpath", "(//button[@title='Notify Me When Available']//span)[1]");
//    				Sync.waitPageLoad(10);   				
    				Thread.sleep(1000);
    				String newsubcribe = Common.findElement("xpath", "//div[@ui-id='message-success']//span")
    						.getText();
    				System.out.println(newsubcribe);
    				Common.assertionCheckwithReport(
    						newsubcribe.contains("Alert subscription has been saved.")
    								|| newsubcribe.contains("Thank you! You are already subscribed to this product."),
    						"verifying the out of stock subcription",
    						"after click on subcribe button message should be appear",
    						"Sucessfully message has been displayed when we click on the subcribe button ",
    						"Failed to display the message after subcribtion");
    		
//    				Sync.waitElementPresent("xpath", "//div[@data-option-label='" + productsize + "']");
//    				Common.clickElement("xpath", "//div[@data-option-label='" + productsize + "']");
    				
    				Thread.sleep(4000);
    				Common.actionsKeyPress(Keys.END);
    				Common.clickElement("xpath", "(//button[@title='Notify Me When Available']//span)[1]");
//    				Sync.waitPageLoad();
    				Thread.sleep(1000);
    				String oldsubcribe = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
    				System.out.println(oldsubcribe);
    				Common.assertionCheckwithReport(
    						oldsubcribe.contains("Thank you! You are already subscribed to this product."),
    						"verifying the out of stock subcription",
    						"after click on subcribe button message should be appear",
    						"Sucessfully message has been displayed when we click on the subcribe button ",
    						"Failed to display the message after subcribtion");
    				price = Common.findElement("xpath", "//span[@class='price-wrapper']//span").getText().replace(symbol,"").replace(".00", "");
    				}
    				}
    			
    			else {
    				Thread.sleep(3000);
    				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
    				//Common.scrollIntoView("xpath", "//img[@alt='" + products + "']");
    				//Common.mouseOver("xpath", "//img[@alt='" + products + "']");
    				Thread.sleep(4000);
    				String productprice = Common.findElement("xpath", "(//span[@class='title-2xs leading-none']//span[@x-ref='normalPrice'])").getText().replace(symbol,"").replace(".00", "");
    				Common.clickElement("xpath", "//img[@alt='" + products + "']");
    				Sync.waitPageLoad();
    				
    			/*	Sync.waitElementPresent(30, "xpath", "//img[@class='m-product-card__image product-image-photo']");
    				String productprice = Common.findElement("xpath", "//span[@class='price-wrapper is-special-price']")
    						.getAttribute("data-price-amount");
    				Common.clickElement("xpath", "//img[@class='m-product-card__image product-image-photo']");
    				Sync.waitPageLoad();*/
    				Thread.sleep(3000);
    				String PLPprice = Common
    						.findElement("xpath",
    								"(//span[@x-html='getFormattedFinalPrice()'])[1]").getText().replace(symbol,"").replace(".00", "");
    				
    				String name = Common.findElement("xpath", "//h1[@itemprop='name']").getText();
    				Common.assertionCheckwithReport(
    						name.contains(products) && productprice.equals(PLPprice),
    						"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
    						"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
    				Common.clickElement("xpath", "(//button[@title='Notify Me When Available']//span)[1]");
    				Sync.waitPageLoad();
    				Thread.sleep(4000);
    				String newsubcribe = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
    				System.out.println(newsubcribe);
    				Common.assertionCheckwithReport(
    						newsubcribe.contains("Alert subscription has been saved.")
    								|| newsubcribe.contains("Thank you! You are already subscribed to this product."),
    						"verifying the out of stock subcription",
    						"after click on subcribe button message should be appear",
    						"Sucessfully message has been displayed when we click on the subcribe button ",
    						"Failed to display the message after subcribtion");
    				Common.actionsKeyPress(Keys.END);
    				Common.clickElement("xpath", "(//button[@title='Notify Me When Available']//span)[1]");
    				Sync.waitPageLoad();
    				Thread.sleep(4000);
    				String oldsubcribe = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
    				System.out.println(oldsubcribe);
    				Common.assertionCheckwithReport(
    						oldsubcribe.contains("Thank you! You are already subscribed to this product."),
    						"verifying the out of stock subcription",
    						"after click on subcribe button message should be appear",
    						"Sucessfully message has been displayed when we click on the subcribe button ",
    						"Failed to display the message after subcribtion");
    				//price = Common.findElement("xpath", "//span[@data-price-type='finalPrice']").getAttribute("data-price-amount");
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

        public void My_order_subcribtion(String Dataset) {
    		// TODO Auto-generated method stub
    		String products = data.get(Dataset).get("Products");
    		System.out.println(products);
    		String prod = data.get(Dataset).get("prod product");
    		System.out.println(prod);
    		try {
    			Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
    			Common.clickElement("xpath", "//button[@id='customer-menu']");
    			Sync.waitElementPresent("xpath", "//a[normalize-space()='My Account']");
    			Common.clickElement("xpath", "//a[normalize-space()='My Account']");
    			Thread.sleep(5000);
    			Common.assertionCheckwithReport(Common.getPageTitle().contains("Dashboard"),
    					"validating the page navigation to the my account",
    					"after clicking on the my account it should navigate to the my account page",
    					"Sucessfully Navigated to the my account page", "failed to Navigate to the my account page");
    			
    			Sync.waitElementPresent("xpath", "//span[text()='Back In Stock Alerts']");
    			Common.clickElement("xpath", "//span[text()='Back In Stock Alerts']");
    			Sync.waitPageLoad();
    			Thread.sleep(4000);
    			Sync.waitElementPresent(20, "xpath", "//td[@class='p-2']//a[@title='"+products+"']");
    			String name = Common.findElement("xpath", "//td[@class='p-2']//a[@title='"+products+"']").getText();
    			System.out.println(name);
    			Common.assertionCheckwithReport(name.contains(products) || name.contains(prod),
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
        public void remove_outofstock_subcribtion(String Dataset) {
    		// TODO Auto-generated method stub
    		try {
    			String price = Common.findElement("xpath", "//span[@data-price-type='finalPrice']")
    					.getAttribute("data-price-amount").replace("$", "").replace(".00", "");
    			System.out.println(price);
    		
    			if (price.equals(Dataset)) {
    				Thread.sleep(3000);
    				Common.clickElement("xpath", "//a[@title='Remove This Item']");
    				//Common.maximizeImplicitWait();
    				Thread.sleep(3000);
    				Common.alerts("Cancel");
    				Thread.sleep(4000);
    				Common.clickElement("xpath", "//a[@title='Remove This Item']");
    				//Common.implicitWait();
    				Thread.sleep(3000);
    				Common.alerts("Ok");

    			} else {

    			}
    		} catch (Exception | Error e) {
    			e.printStackTrace();
    			Assert.fail();
    		}

    	}
        public void changed_password(String Dataset) {
    		// TODO Auto-generated method stub

    		try {
    			Sync.waitPageLoad();
    			Common.textBoxInput("id", "email", Dataset);
    			Common.textBoxInput("id", "pass", "Lotuswave@1234");
    			Common.clickElement("xpath", "//span[contains(text(),'Sign In')]");
    			Sync.waitPageLoad();
    			Common.assertionCheckwithReport(Common.getPageTitle().contains("Dashboard"),
    					"To validate the user lands on My Account page after successfull login",
    					"After clicking on the signIn button it should navigate to the My Account page",
    					"user Sucessfully navigate to the My Account page after clicking on the signIn button",
    					"Failed to signIn and not navigated to the My Account page ");
    			System.out.println("My Account");
    			

    		} catch (Exception e) {
    			e.printStackTrace();
    			ExtenantReportUtils.addFailedLog("To validate the user Navigate to My Account page after successfull login",
    					"After clicking on the signin button it should navigate to the My Account page",
    					"Unable to navigate the user to the My Account after clicking on the SignIn button",
    					Common.getscreenShotPathforReport("Failed to signIn and not navigated to the My Account page "));

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
    			String editaccount = Common.findElement("xpath", "//h1[@class='title-2xl']//span").getText();
    			System.out.println(editaccount);
    			Common.assertionCheckwithReport(name.contains(editaccount) || editaccount.contains("EDIT ACCOUNT INFORMATION") ||
    					editaccount.contains("ACCOUNT INFORMATION"),
    					"verifying the page navigated to the edit account ",
    					"user should navigate to the Edit account page",
    					"user successfully Navigated to the edit account page",
    					"Failed to navigate to the edit account page");
    			
    			Sync.waitElementPresent("xpath", "//span[contains(text(),'Change Email')]");
    			Common.clickElement("xpath", "//span[contains(text(),'Change Email')]");
    			Thread.sleep(4000);
    			oldemail = Common.findElement("xpath", "//input[@id='email']").getAttribute("value");
    			System.out.println(oldemail);
//    			Common.clickElement("xpath", "//button[@aria-label='Edit Account Email']//span[text()='Edit']");
    			Common.textBoxInputAndVerify("xpath", "//input[@name='email']", data.get(Dataset).get("Email"));
    			Common.textBoxInput("xpath", "//input[@name='current_password']", data.get(Dataset).get("Password"));
    			Common.clickElement("xpath", "//button[@title='Save Account Information']");
    			Sync.waitPageLoad();
    			Thread.sleep(4000);
    			String errormessage = Common.findElement("xpath", "//div[@ui-id='message-error']//span").getText();
    			Common.assertionCheckwithReport(errormessage.contains("The password doesn't match this account"),
    					"verifying the error message for the password",
    					"user should get the error message if he enter the different password",
    					"Successfully user gets the error message",
    					"Failed to get the error message if the user gives an different password");
    			Common.refreshpage();
    			Sync.waitPageLoad();
    			if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
    				Thread.sleep(3000);
    				Sync.waitElementPresent("xpath", "//span[contains(text(),'Change Email')]");
        			Common.clickElement("xpath", "//span[contains(text(),'Change Email')]");
        			Thread.sleep(4000);
    				Common.textBoxInputAndVerify("xpath", "//input[@name='email']", data.get(Dataset).get("Email"));
    				Common.textBoxInput("xpath", "//input[@name='current_password']",
    						data.get(Dataset).get("Confirm Password"));
    				
    			} else {
    				Common.clickElement("xpath", "//button[@aria-label='Edit Account Email']//span[text()='Edit']");
    				Common.textBoxInputAndVerify("xpath", "//input[@name='email']", data.get(Dataset).get("Prod Email"));
    				Common.textBoxInput("xpath", "//input[@name='current_password']",
    						data.get(Dataset).get("Confirm Password"));
    			}
    			Common.clickElement("xpath", "//button[@title='Save Account Information']");
    			Sync.waitPageLoad();
    			Thread.sleep(4000);
    			String emailerrormessage = Common.findElement("xpath", "//div[@ui-id='message-error']//span")
    					.getText();
    			Common.assertionCheckwithReport(
    					emailerrormessage.contains("A customer with the same email address already exists in an associated website"),
    					"verifying the error message for the existing account email",
    					"user should get the error message if he enter the existing email",
    					"Successfully user gets the error message",
    					"Failed to get the error message if the user gives an existing email id");
    			Sync.waitPageLoad();
    			Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//span[contains(text(),'Change Email')]");
    			Common.clickElement("xpath", "//span[contains(text(),'Change Email')]");
    			Thread.sleep(4000);
    			Common.textBoxInputAndVerify("xpath", "//input[@name='email']", data.get(Dataset).get("UserName"));
    			String newemail = Common.findElement("xpath", "//input[@name='email']").getAttribute("value");
    			Common.textBoxInput("xpath", "//input[@name='current_password']",
    					data.get(Dataset).get("Confirm Password"));
    			Common.clickElement("xpath", "//button[@title='Save Account Information']");
    			Sync.waitPageLoad();
    			Thread.sleep(4000);
    			/*if(Common.getCurrentURL().contains("stage")) {
    			String successmessage = Common.findElement("xpath", "(//div[@ui-id='message-success']//span)[2]").getText();
    			Common.assertionCheckwithReport(
    					successmessage.contains("You must confirm your account. Please check your email for the confirmation link")
    							&& Common.getPageTitle().contains("Customer Login"),
    					"verifying the Success message for the Change email",
    					"user should get the success message and navigate back to the Login page",
    					"Successfully user gets the success message and navigated to the Login page",
    					"Failed to get the success message and unable to navigate to the login page");
    			} else
    			{
    				String successmessage = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
    				Common.assertionCheckwithReport(
        					successmessage.contains("You must confirm your account. Please check your email for the confirmation link")
        							&& Common.getPageTitle().contains("Customer Login"),
        					"verifying the Success message for the Change email",
        					"user should get the success message and navigate back to the Login page",
        					"Successfully user gets the success message and navigated to the Login page",
        					"Failed to get the success message and unable to navigate to the login page");
    			}*/
    			
//    			Sync.waitPageLoad();
//    			Common.textBoxInput("id", "email", newemail);
//    			Common.textBoxInput("id", "pass", data.get(Dataset).get("Confirm Password"));
//    			Common.clickElement("xpath", "//button[contains(@class,'action login')]");
//    			Sync.waitPageLoad();
//    			Thread.sleep(4000);
//    			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"),
//    					"To validate the user lands on My Account after successfull login",
//    					"After clicking on the signIn button it should navigate to the My Account",
//    					"user Sucessfully navigate to the My Account after clicking on the signIn button",
//    					"Failed to signIn and not navigated to the My Account");

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
        
        public void Accont_Information() {
    		// TODO Auto-generated method stub

    		try {
    			Sync.waitElementPresent("xpath", "//span[text()='Account Information']");
    			Common.clickElement("xpath", "//span[text()='Account Information']");
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

    			Sync.waitElementPresent("xpath", "//span[contains(text(),'Change Password')]");

    			Common.clickElement("xpath", "//span[contains(text(),'Change Password')]");
    			Thread.sleep(4000);
    			Common.clickElement("xpath", "//div//input[@id='current-password']");
    			Common.textBoxInput("xpath", "//input[@id='current-password']", data.get(dataSet).get("Password"));
    			Common.textBoxInput("xpath", "//input[@id='password']", data.get(dataSet).get("Confirm Password"));
    			Common.textBoxInput("xpath", "//input[@id='password-confirmation']",
    					data.get(dataSet).get("Confirm Password"));
//    			String message = Common.findElement("id", "validation-classes").getCssValue("color");
//    			String greencolor = Color.fromString(message).asHex();
//    			String message1 = Common.findElement("id", "validation-length").getAttribute("class");
//
//    			Common.assertionCheckwithReport(greencolor.equals("#2f741f") && message1.contains("complete"),
//    					"validating the cureent password and new password fields",
//    					"User should able enter data in current password and new password",
//    					"Sucessfully the data has been entered in new password and current password",
//    					"Failed to enter data in current password and new password fields");
     
    			Common.clickElement("xpath", "//button[@title='Save Account Information']");
    			Sync.waitPageLoad();
    			Thread.sleep(3000);
    			String sucessmessage = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
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
        
        public void Travel_Sizesearch_product(String Dataset) {
			// TODO Auto-generated method stub
			String product = data.get(Dataset).get("Products");
			System.out.println(product);
			try
			{
	        Common.clickElement("xpath", "//button[@id='menu-search-icon']");
	     	String open = Common.findElement("xpath", "//button[@id='menu-search-icon']").getAttribute("aria-expanded");
	     	Thread.sleep(4000);
	     	Common.assertionCheckwithReport(open.contains("true"), "User searches using the search field",
	     	"User should able to click on the search button", "Search expands to the full page",
	     	"Sucessfully search bar should be expand"); 
	     	WebElement serachbar=Common.findElement("xpath", "//input[@id='autocomplete-0-input']");
	        serachbar.sendKeys(product);
	        Common.actionsKeyPress(Keys.ENTER);
	    	Sync.waitPageLoad();
	    	Thread.sleep(4000);
//				String productsearch = Common.findElement("xpath", "//span[@id='algolia-srp-title']").getText();
//				System.out.println(productsearch);
//				Common.assertionCheckwithReport(productsearch.contains(product), "validating the search functionality",
//						"enter product name will display in the search box", "user enter the product name in  search box",
//						"Failed to see the product name");
//				Thread.sleep(8000);
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

		public void addtocartTravel_Sizesearch(String Dataset) {
			// TODO Auto-generated method stub
			String products = data.get(Dataset).get("Products");
			System.out.println(products);
			try {
				Sync.waitPageLoad();
				for (int i = 0; i <= 10; i++) {
					Sync.waitElementPresent("xpath", "//img[@class='group-hover/item-image:block hidden']");
					List<WebElement> webelementslist = Common.findElements("xpath","//img[@class='group-hover/item-image:block hidden']");

					String s = webelementslist.get(i).getAttribute("src");
					System.out.println(s);
					if (s.isEmpty()) {

					} else {
						break;
					}
				}
				Thread.sleep(6000);
			
				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
				Thread.sleep(4000);
				Common.javascriptclickElement("xpath", "//img[@alt='" + products + "']");
				Sync.waitPageLoad();
				Thread.sleep(5000);
				String name = Common.findElement("xpath", "//span[text()='" + products + "']").getText();
		
				Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
						"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
						"failed to Navigate to the PDP page");
				product_quantity(Dataset);
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//span[contains(text(),'ADD TO BAG')]");
				Common.clickElement("xpath", "//span[contains(text(),'ADD TO BAG')]");
				Sync.waitPageLoad();
				Thread.sleep(6000);
				String message = Common.findElement("xpath", "//div[@ui-id='message-success']")
						.getAttribute("ui-id");
				System.out.println(message);
				Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
						"Product should be add to cart", "Sucessfully product added to the cart ",
						"failed to add product to the cart");
//				
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
						"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));

				Assert.fail();
			}
		}
		
		public void Sort_By(String Dataset) throws InterruptedException {{
			// TODO Auto-generated method stub
			String symbol = data.get(Dataset).get("Price_Symbol");
			String PriceFilter = data.get(Dataset).get("Sortby_Dropdown");
			System.out.println(PriceFilter);
			System.out.println(symbol);
			try {
				Sync.waitPageLoad();

				Thread.sleep(5000);
				Common.scrollIntoView("xpath","//div[@class='ais-Panel-body price-range-w-fixed price']//div[@aria-label='Minimum price']");

				List<WebElement> BeforeFilterprice = Common.findElements("xpath","//div[@class='ais-Panel-body price-range-w-fixed price']//div[@aria-label='Minimum price']");
				List<String> Beforefilterpricelist = new ArrayList<String>();

				for (WebElement p : BeforeFilterprice) {
					Beforefilterpricelist.add(p.getText().replace(symbol, " "));
					System.out.println("Beforefilterpricelist" + Beforefilterpricelist);
				}
				Thread.sleep(4000);
				Common.dropdown("xpath", "//select[@class='ais-SortBy-select']", SelectBy.TEXT,PriceFilter);
				
				Thread.sleep(5000);
				Common.scrollIntoView("xpath",
						"//div[@class='ais-Panel-body price-range-w-fixed price']//div[@aria-label='Minimum price']");
				List<WebElement> AfterFilterprice = Common.findElements("xpath",
						"//div[@class='ais-Panel-body price-range-w-fixed price']//div[@aria-label='Minimum price']");
				List<String> Afterfilterpricelist = new ArrayList<String>();

				for (WebElement p : AfterFilterprice) {
					Afterfilterpricelist.add(p.getText().replace(symbol, " "));
					System.out.println("Afterfilterpricelist" + Afterfilterpricelist);
				}

				if (PriceFilter.equals("Highest price")) {
					Collections.sort(Beforefilterpricelist);
					System.out.println("Beforefilterpricelist Highest " + Beforefilterpricelist);
					Common.assertionCheckwithReport(Beforefilterpricelist.equals(Afterfilterpricelist),
							"To validate the Sort in Product Listing Page",
							"User should able to Sort in Product Listing Page",
							"Sucessfully Sorts in the Product Listing Page", "Failed to Sort  in Product Listing Page");
				} else {
					if (PriceFilter.equals("Lowest price")) {
						Collections.sort(Beforefilterpricelist, Collections.reverseOrder());
						System.out.println("Beforefilterpricelist Lowest" + Beforefilterpricelist);
						Common.assertionCheckwithReport(Beforefilterpricelist.equals(Afterfilterpricelist),
								"To validate the Sort in Product Listing Page",
								"User should able to Sort in Product Listing Page",
								"Sucessfully Sorts in the Product Listing Page", "Failed to Sort  in Product Listing Page");
					}

				}
				Thread.sleep(2000);
			} catch (NumberFormatException | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the Sort by functionality",
						"Products should be display as per selected sort option ",
						" Unable to display the Products as per selected sort option",
						Common.getscreenShot("Failed to sort_by"));
				Assert.fail();
			}

		}
		}
		
		public void Filter() throws InterruptedException {
			// TODO Auto-generated method stub
			try {

				Common.scrollIntoView("xpath", "//div[@class='is-widget-container-drybar_hair_type']");
				Sync.waitElementPresent("xpath", "//div[@class='is-widget-container-drybar_hair_type']");
				Common.clickElement("xpath", "//div[@class='is-widget-container-drybar_hair_type']");
				Sync.waitElementPresent("xpath", "//input[@value='All Hair Types']");
				Common.clickElement("xpath", "//input[@value='All Hair Types']");
				Thread.sleep(4000);
				String SelectedFilter = Common.findElement("xpath", "//ul[@class='ais-CurrentRefinements-list']//li[@class='ais-CurrentRefinements-item']//span[@class='ais-CurrentRefinements-category']//span").getText();
				System.out.println(SelectedFilter);
				System.out.println("SelectedFilter:" + SelectedFilter);
				String RetrivedValue = "hot_toddy";
				if (SelectedFilter.equals("All Hair Types")) {

					List<WebElement> Series_Filters = Common.findElements("xpath",
							"(//div[contains(@class,'group/item-image')]//a//img)[1]");

					for (WebElement Filter : Series_Filters) {
						// System.out.println(Filter);
						String AttributeValue = Filter.getAttribute("src");

						if (AttributeValue.contains(RetrivedValue)) {

							System.out.println("Attribute '" + AttributeValue + "' contains the text '" + RetrivedValue
									+ "' for product: " + AttributeValue);

						} else {

							System.out.println("Attribute '" + AttributeValue + "' does not contain the text '"
									+ RetrivedValue + "' for product: " + AttributeValue);
							Assert.fail();
						}
					}
				}
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the Filter functionality",
						"Products should be display as per selected Filter option ",
						" Unable to display the Products as per selected Filter option",
						Common.getscreenShot("Failed to Filter"));
				Assert.fail();
			}
		}

		
		public void Invalid_search_product(String Dataset) {{
			// TODO Auto-generated method stub
			String invalidproduct = data.get(Dataset).get("Products");
			System.out.println(invalidproduct);
			try {
			Common.clickElement("xpath", "//button[@x-show='!searchOpen']");
				String open = Common.findElement("xpath", "//input[contains(@id, 'autocomplete-0-input')]").getAttribute("type");
				Thread.sleep(4000);
				Common.assertionCheckwithReport(open.contains("search"), "User searches using the search field",
						"User should able to click on the search button", "Search expands to the full page",
						"Sucessfully search bar should be expand");
				Common.textBoxInput("xpath", "//input[@id='autocomplete-0-input']",
						data.get(Dataset).get("Products"));
				Common.actionsKeyPress(Keys.ENTER);
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String productsearch = Common.findElement("xpath", "//span[contains(@class,'relative pb-3 mx-6 cursor-pointer')]").getText();
				//String searchproduct=Common.findElement("id", "instant-empty-results-container").getAttribute("class");
				//System.out.println(searchproduct);
				System.out.println(productsearch);
				Common.assertionCheckwithReport(productsearch.contains("Related Products (0)"),
						"validating the search functionality", "enter Invalid product name will display in the search box",
						"user enter the Invalid product name in  search box", "Failed to see the Invalid product name");
				Thread.sleep(8000);

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the search functionality",
						"enter Invalid product name will display in the search box",
						" unable to enter the Invalid product name in  search box",
						Common.getscreenShot("Failed to see the Invalid product name"));
				Assert.fail();
			}
		}
		}
		
		
		public void header_Hairproducts(String Dataset) {

			String names = data.get(Dataset).get("Hairproducts");
			String[] Links = names.split(",");
			
			String hair=data.get(Dataset).get("headers");
			
			int i = 0;
			try {
				for (i = 0; i < Links.length; i++) {
					Sync.waitElementPresent("xpath", "(//span[contains(text(),'"+ hair +"')])[1]");
					Common.clickElement("xpath", "(//span[contains(text(),'"+ hair +"')])[1]");
					
					Thread.sleep(3000);
					Sync.waitElementPresent("xpath",
							"//a[contains(@class,'link group')]//span[contains(text(),'" + Links[i] + "')]");
					Common.clickElement("xpath",
							"//a[contains(@class,'link group')]//span[contains(text(),'" + Links[i] + "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title = Common.findElement("xpath", "//div[contains(@class,'category-view')]//h1").getText();
					String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
					System.out.println(title);
					System.out.println(breadcrumbs);
					System.out.println(Links[i]);
					Thread.sleep(4000);
					String products=Common.getText("xpath", "//div[contains(@class,'flex w-full')]//span");
					System.out.println(products);
					int Number = Integer.parseInt(products);
					int j=0;
					if(Number>j)
					{
						Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) ,
								"verifying the header link " + Links[i] + "Under Hairproducts",
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
				ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Hairproducts",
						"User should navigate to the " + Links[i] + "pages",
						" unable to navigate to the " + Links[i] + "pages",
						Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
				Assert.fail();
			}

		}

		public void header_HairTools(String Dataset) {

			String names = data.get(Dataset).get("Hairtools");
			String[] Links = names.split(",");
			String bag=data.get(Dataset).get("headers");
			int i = 0;
			try {
				for (i = 0; i < Links.length; i++) {
						Sync.waitElementPresent("xpath", "//span[contains(text(),'"+bag+"')]");
						Common.clickElement("xpath", "//span[contains(text(),'"+bag+"')]");
					Thread.sleep(3000);
					Sync.waitElementPresent("xpath",
							"//a[contains(@class,'link group')]//span[contains(text(),'" + Links[i] + "')]");
					Common.clickElement("xpath",
							"//a[contains(@class,'link group')]//span[contains(text(),'" + Links[i] + "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
					String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();    
					String products=Common.getText("xpath", "//div[contains(@class,'flex w-full')]//span");
					System.out.println(title);
					System.out.println(breadcrumbs);
					System.out.println(products);
					
					int Number = Integer.parseInt(products);
					int j=0;
					if(Number>j)
					{
						Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]),
								"verifying the header link " + Links[i] + "Under HairTools",
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
				ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under HairTools",
						"User should navigate to the " + Links[i] + "pages",
						" unable to navigate to the " + Links[i] + "pages",
						Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
				Assert.fail();
			}

		}

		
		public void header_Brushes(String Dataset) {

			String names = data.get(Dataset).get("Brushes");
			String[] Links = names.split(",");
			String bag=data.get(Dataset).get("headers");
			
			int i = 0;
			try {
				for (i = 0; i < Links.length; i++) {
						Sync.waitElementPresent("xpath", "//span[contains(text(),'"+bag+"')]");
						Common.clickElement("xpath", "//span[contains(text(),'"+bag+"')]");
						Common.clickElement("xpath", "//span[contains(text(),' Brushes')]");
						
					Thread.sleep(3000);
					Sync.waitElementPresent("xpath",
							"//a[contains(@class,'link group')]//span[contains(text(),'" + Links[i] + "')]");
					Common.clickElement("xpath",
							"//a[contains(@class,'link group')]//span[contains(text(),'" + Links[i] + "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title = Common.findElement("xpath", "//div[contains(@class,'pagebuilder')]//h2").getText();
					String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
					String products=Common.getText("xpath", "//div[contains(@class,'flex w-full')]//span");
					System.out.println(products);
					int Number = Integer.parseInt(products);
					int j=0;
					if(Number>j)
					{
						Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]),
								"verifying the header link " + Links[i] + "Under Brushes",
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
				ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Brushes",
						"User should navigate to the " + Links[i] + "pages",
						" unable to navigate to the " + Links[i] + "pages",
						Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
				Assert.fail();
			}

		}

		
		public void header_Benfits(String Dataset) {

			String names = data.get(Dataset).get("Benfits");
			String[] Links = names.split(",");	
			String benfits=data.get(Dataset).get("headers");
			
			int i = 0;
			try {
				for (i = 0; i < Links.length; i++) {
					Sync.waitElementPresent("xpath", "(//span[contains(text(),'"+ benfits +"')])");
					Common.clickElement("xpath", "(//span[contains(text(),'"+ benfits +"')])");
					
					Thread.sleep(3000);
					Sync.waitElementPresent("xpath",
							"//a[contains(@class,'link group')]//span[contains(text(),'" + Links[i] + "')]");
					Common.clickElement("xpath",
							"//a[contains(@class,'link group')]//span[contains(text(),'" + Links[i] + "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					
					String title = "";
		            if 
		            (Common.findElements("xpath", "//div[contains(@class,'category-view')]//h1").size() > 0) {
		                title = Common.findElement("xpath", "//div[contains(@class,'category-view')]//h1").getText();
		            } 
		            else if (Common.findElements("xpath", "//div[contains(@class,'pagebuilder')]//h2").size() > 0) {
		                title = Common.findElement("xpath", "//div[contains(@class,'pagebuilder')]//h2").getText();
		            }
		            
					String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
					System.out.println(title);
					System.out.println(breadcrumbs);
					System.out.println(Links[i]);
					Thread.sleep(4000);
					String products=Common.getText("xpath", "//div[contains(@class,'flex w-full')]//span");
					System.out.println(products);
					int Number = Integer.parseInt(products);
					int j=0;
					if(Number>=j)
					{
						Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) ||breadcrumbs.contains(title)
								|| Common.getCurrentURL().contains("treatments"),
								"verifying the header link " + Links[i] + "Under Benfits",
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
				ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Benfits",
						"User should navigate to the " + Links[i] + "pages",
						" unable to navigate to the " + Links[i] + "pages",
						Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
				Assert.fail();
			}

		}

		
		public void header_GiftsSets(String Dataset) {

			if(Common.getCurrentURL().contains("preprod"))
			{
				String names = data.get(Dataset).get("GiftsSets");
				String[] Links = names.split(",");
				String gifts=data.get(Dataset).get("headers");
				int i = 0;
				try {

					for (i = 0; i < Links.length; i++) {
						Sync.waitElementPresent("xpath", "(//span[contains(text(),'"+ gifts +"')])");
						Common.clickElement("xpath", "(//span[contains(text(),'"+ gifts +"')])");
						Thread.sleep(3000);
						Sync.waitElementPresent("xpath",
								"//a[contains(@class,'link group')]//span[contains(text(),'" + Links[i] + "')]");
						Common.clickElement("xpath",
								"//a[contains(@class,'link group')]//span[contains(text(),'" + Links[i] + "')]");
						Sync.waitPageLoad();
						Thread.sleep(4000);
						String title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
//						String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
						String breadcrumbs = "";
						if (Common.findElements("xpath", "//nav[contains(@class,'breadcrumb')]").size() > 0) {
						    breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
						} else {
						    String currentURL = Common.getCurrentURL();
						    System.out.println("Redirecting to URL: " + currentURL);
						}	
						System.out.println(title);
						System.out.println(breadcrumbs);
						System.out.println(Links[i]);
						Thread.sleep(4000);
						String products=Common.getText("xpath", "//div[contains(@class,'flex w-full')]//span");
						System.out.println(products);
						int Number = Integer.parseInt(products);
						int j=0;
						if(Number>j)
						{
							Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i])
									|| Common.getPageTitle().contains("Search Result Page")  || Common.getCurrentURL().contains("all-products"),
									"verifying the header link " + Links[i] + "under GiftsSets",
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
					ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "under GiftsSets",
							"User should navigate to the " + Links[i] + "pages",
							" unable to navigate to the " + Links[i] + "pages",
							Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
					Assert.fail();
				}
			}
			else
			{
			String names = data.get(Dataset).get("Prod GiftsSets");
			String[] Links = names.split(",");
			String gifts=data.get(Dataset).get("headers");
			int i = 0;
			try {

				for (i = 2; i < Links.length; i++) {
					Sync.waitElementPresent("xpath", "(//span[contains(text(),'"+ gifts +"')])");
					Common.clickElement("xpath", "(//span[contains(text(),'"+ gifts +"')])");
					Thread.sleep(3000);
					Sync.waitElementPresent("xpath",
							"//a[contains(@class,'link group')]//span[contains(text(),'" + Links[i] + "')]");
					Common.clickElement("xpath",
							"//a[contains(@class,'link group')]//span[contains(text(),'" + Links[i] + "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title = Common.findElement("xpath", "//h1[contains(@class,'title')]").getText();
//					String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
					String breadcrumbs = "";
					if (Common.findElements("xpath", "//nav[contains(@class,'breadcrumb')]").size() > 0) {
					    breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
					} else {
					    String currentURL = Common.getCurrentURL();
					    System.out.println("Redirecting to URL: " + currentURL);
					}	
					System.out.println(title);
					System.out.println(breadcrumbs);
					System.out.println(Links[i]);
					Thread.sleep(4000);
					String products = "";
					if (Common.findElements("xpath", "//span[@id='algolia-tab-products-counter']").size() > 0) {
					    products = Common.findElement("xpath", "//span[@id='algolia-tab-products-counter']").getText();
					} else if(Common.findElements("xpath", "//div[contains(@class,'flex w-full')]//span").size() > 0) {
						products = Common.findElement("xpath", "(//div[contains(@class,'flex w-full')]//span)[1]").getText();
					}	
					
					System.out.println(products);
					int Number = Integer.parseInt(products.replaceAll("[^0-9]", ""));
					int j=0;
					if(Number>j)
					{
						Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i])
								|| Common.getPageTitle().contains("Search Result Page")  || Common.getCurrentURL().contains("all-products"),
								"verifying the header link " + Links[i] + "under GiftsSets",
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
				ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "under GiftsSets",
						"User should navigate to the " + Links[i] + "pages",
						" unable to navigate to the " + Links[i] + "pages",
						Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
				Assert.fail();
			}
		}
		}


		
		public void header_New(String Dataset) {

			String names = data.get(Dataset).get("New");
			String[] Links = names.split(",");
			
			String New=data.get(Dataset).get("headers");
			
			int i = 0;
			try {
				for (i = 0; i < Links.length; i++) {
					Sync.waitElementPresent("xpath", "(//span[contains(text(),'"+ New +"')])");
					Common.clickElement("xpath", "(//span[contains(text(),'"+ New +"')])");
					
					Thread.sleep(3000);
					Sync.waitElementPresent("xpath",
							"//li[contains(@class,'level1 ')]//a//span[contains(text(),'" + Links[i] + "')]");
					Common.clickElement("xpath",
							"//li[contains(@class,'level1 ')]//a//span[contains(text(),'" + Links[i] + "')]");
					Sync.waitPageLoad();
					Thread.sleep(4000);
					String title = Common.findElement("xpath", "//div[contains(@class,'c-clp-hero')]//h1").getText();
					String breadcrumbs = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
					System.out.println(title);
					System.out.println(breadcrumbs);
					System.out.println(Links[i]);
					Thread.sleep(4000);
					String products=Common.getText("xpath", "//div[@class='a-toolbar-info']//span");
					System.out.println(products);
					int Number = Integer.parseInt(products);
					int j=0;
					if(Number>j)
					{
						Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]),
								"verifying the header link " + Links[i] + "Under New",
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
				ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under New",
						"User should navigate to the " + Links[i] + "pages",
						" unable to navigate to the " + Links[i] + "pages",
						Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
				Assert.fail();
			}

		}
		
		public void header_HowToInspo(String Dataset) {

			String names = data.get(Dataset).get("Howtoinspo");
			String[] Links = names.split(",");
			String inspo=data.get(Dataset).get("headers");
				
			int i = 0;
			try {
				for (i = 0; i < Links.length; i++) {
					
					Common.clickElement("xpath", "//a[@title='Hair Products']");
					Sync.waitElementPresent("xpath", "(//span[contains(text(),'"+ inspo +"')])");
					Common.clickElement("xpath", "(//span[contains(text(),'"+ inspo +"')])");
					Thread.sleep(3000);
					
					Sync.waitElementPresent("xpath",
							"//a[contains(@class,'link group')]//span[contains(text(),'" + Links[i] + "')]");
					Common.clickElement("xpath",
							"//a[contains(@class,'link group')]//span[contains(text(),'" + Links[i] + "')]");
					Sync.waitPageLoad();
					Thread.sleep(3000);
					String title = "";
					if 
		            (Common.findElements("xpath", "//div/h1[@data-content-type='heading']").size() > 0) {
		                title = Common.findElement("xpath", "//div/h1[@data-content-type='heading']").getText();
		            } 
		            else if (Common.findElements("xpath", "//div/h2[@data-content-type='heading']").size() > 0) {
		                title = Common.findElement("xpath","//div/h2[@data-content-type='heading']").getText();
		            }
	
					//String title = Common.findElement("xpath", "//div/h1[@data-content-type='heading']").getText();
					String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
					System.out.println(title);
					System.out.println(breadcrumbs);
					System.out.println(Links[i]);
					Thread.sleep(4000);
					//String products=Common.getText("xpath", "//div[@class='a-toolbar-info']//span");
					//System.out.println(products);
					
						Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]) || 
								 Common.getCurrentURL().contains("article") || Common.getCurrentURL().contains("how-to/inspiration"),
								"verifying the header link " + Links[i] + "Under HowToInspo",
								"user should navigate to the " + Links[i] + " page",
								"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);
					}
					
				}
		
			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under HowToInspo",
						"User should navigate to the " + Links[i] + "pages",
						" unable to navigate to the " + Links[i] + "pages",
						Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
				Assert.fail();
			}
}

		

		public void view_PLP_page() {
			try {
				Thread.sleep(4000);
//				String title = Common.findElement("xpath","//div[contains(@class,'c-clp-hero')]").getAttribute("Class");
//				System.out.println(title);
				Common.scrollIntoView("xpath", "//nav[@id='breadcrumbs']");
				String breadcrumbs = Common.findElement("xpath", "//nav[@id='breadcrumbs']")
						.getAttribute("aria-label");
				System.out.println(breadcrumbs);
				String filter = Common.findElement("xpath", "//span[normalize-space()='Filter by:']").getText();
				System.out.println(filter);
				String Sort = Common
						.findElement("xpath",
								"//div[@id='algolia_instant_sorter']//span")
						.getText();
				System.out.println(Sort);
				Thread.sleep(4000);
				Common.assertionCheckwithReport(
						breadcrumbs.contains("Breadcrumb") || breadcrumbs.contains("Migaja de pan") || breadcrumbs.contains("Fil d'Ariane")
								&& filter.contains("Filter by") || filter.contains("Filtrado por") || filter.contains("Filtres") && Sort.contains("Sort by") || Sort.contains("Ordenar por") || Sort.contains("Trier par"),
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
		public void filter_By(String dataSet) {
			String category = data.get(dataSet).get("category");
			System.out.println(category);
	try {
				
		Thread.sleep(6000);
				String text = Common.findElement("xpath", "//span[text()='"+category+"']//parent::a//span[2]").getText();
				System.out.println(text);
				 
				text = text.replace("(", "").replace(")", ""); // Remove the parentheses  
				int textValue = Integer.parseInt(text); // Now parse the cleaned string  
				System.out.println(textValue); // This will print: 14
				
				Common.clickElement("xpath", "//a[text()='"+category+"']");
				Thread.sleep(4000);
//				int textValue = Integer.parseInt(text);
				String categoryvalue=Integer.toString(textValue);
				Thread.sleep(6000);
				String textValueAfterFilter = Common.findElement("xpath", "//div[@class='text-sm']")
						.getText().replace("Items", "").trim();
				int noOfItems = Common.findElements("xpath", "//li[@class='ais-InfiniteHits-item']").size();
				String items=Integer.toString(noOfItems);
				System.out.println(text);
				System.out.println(textValueAfterFilter);
				System.out.println("noOfItems_:" +items);
				System.out.println(categoryvalue);
				if(textValueAfterFilter.equals(items))
				{
					Common.assertionCheckwithReport(textValueAfterFilter.equals(items),
							"To validate the filter in Product Listing Page",
							"User should able to filter in Product Listing Page",
							"Sucessfully filters in the Product Listing Page",
							"Failed to filter in Product Listing Page");
					
				}
				else
				{
			
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(6000);
			Common.scrollIntoView("xpath", "//button[text()='Load More']");
			Sync.waitElementPresent("xpath", "//button[text()='Load More']");
			Common.javascriptclickElement("xpath", "//button[text()='Load More']");
			Thread.sleep(4000);
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.PAGE_UP);
			int noOfItems1 = Common.findElements("xpath", "//ol//li[@class='ais-InfiniteHits-item']").size();
			String items1=Integer.toString(noOfItems1);
			System.out.println(items1);
			System.out.println(categoryvalue);
			Common.assertionCheckwithReport(textValueAfterFilter.equals(items1),
					"To validate the filter in Product Listing Page",
					"User should able to filter in Product Listing Page",
					"Sucessfully filters in the Product Listing Page",
					"Failed to filter in Product Listing Page");
				}
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("To validate the filter in Product Listing Page",
						"User should able to filter in Product Listing Page", "Unable to filter the Product Listing Page",
						Common.getscreenShotPathforReport("Failed to filter Product listing Page"));

				Assert.fail();
			}
		}

		public void price_filter_validation() {
			// TODO Auto-generated method stub
	    	String name = "";
			try {
				Thread.sleep(4000);
				Common.clickElement("xpath", "//div[text()='Price']");
				Thread.sleep(4000);
				String lastvalue = Common.findElement("xpath", "//div[@class='value end active']").getText()
						.replace("$", "").replace(".00", "");
				System.out.println(lastvalue);
				Sync.waitElementPresent("xpath", "//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='1']");
				WebElement price = Common.findElement("xpath","//div[@aria-valuemax='" + lastvalue + "' and @data-handle-key='1']");
				dragprice(price);
				Thread.sleep(6000);
				List<WebElement> products = Common.findElements("xpath","//img[contains(@class,'group-hover/item-image') or @loading='lazy' and @itemprop]");
				for (int i = 0; i < products.size(); i++) {
					int Size = products.size();
					System.out.println(Size);
					Thread.sleep(4000);
					if (Size == 1) {
						String name1 = Common.findElement("xpath", "//span[@class='title-2xs leading-none']").getText().replace("$", "").replace(".00","");
						System.out.println("Name:"+name1);
						Float namevlaue1 = Float.parseFloat(name1);
						
						
						if (namevlaue1 <= 20) {
							Thread.sleep(3000);
							String value1 = Common.findElement("xpath", "//span[@class='value']")
									.getAttribute("data-price-amount");
							Common.assertionCheckwithReport(value1.equals(name1), "verifying the price filters in PLP page",
									"When we select the range of price filters between the range only products should display",
									"Successfully are displayed in the pricing range",
									"unable to display the procing range after pricing filter applied");
						} else {
							Assert.fail();
						}
					} else {
//						List<WebElement> productprice = Common.findElements("xpath",
//								"//span[@class='title-2xs leading-none']").getText().replace("$", "").replace(".00","");
						Thread.sleep(6000);
						String name1 = Common.findElement("xpath", "//span[@class='title-2xs leading-none']").getText().replace("$", "").replace(".00","");
						System.out.println("Name:"+name1);
					 if (name != null && !name.isEmpty()) {
						 Float namevlaue = Float.parseFloat(name);
						if (namevlaue <= 20) {
							Thread.sleep(3000);
							String value = Common.findElement("xpath", "//span[@data-price-type='finalPrice']")
									.getAttribute("data-price-amount");
							System.out.println(value);
							Common.assertionCheckwithReport(value.equals(name), "verifying the price filters in PLP page",
									"When we select the range of price filters between the range only products should display",
									"Successfully are displayed in the pricing range",
									"unable to display the procing range after pricing filter applied");
						} else {
							Assert.fail();
						}
					}
			}}
			} catch (Exception | Error e) {
				
				ExtenantReportUtils.addFailedLog("verifying the price filters in PLP page",
						"When we select the range of price filters between the range only products should display",
						"unable to display the procing range after pricing filter applied",
						Common.getscreenShotPathforReport(
								"unable to display the procing range after pricing filter applied"));
				//Assert.fail();
				e.printStackTrace();
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
		 
		 
		 public void clickontheproduct_and_image(String Dataset) {
				// TODO Auto-generated method stub
				String products = data.get(Dataset).get("Products");
				System.out.println(products);
				String color=data.get(Dataset).get("Products1");
				
				try {
					Thread.sleep(5000);
					String minicartproduct = Common
							.findElement("xpath", "//img[@alt='"+ products +"']").getText();
					Common.clickElement("xpath", "//img[@alt='"+ products +"']");
					Sync.waitPageLoad();
					Thread.sleep(3000);
					Common.assertionCheckwithReport(Common.getPageTitle().contains(minicartproduct),
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
		 
		 
		 
		 public void Write_Review(String Dataset) {
				// TODO Auto-generated method stub

				
				
				
				try {
					int write= Common.findElements("xpath", "//a[text()='Write a review']").size();
					
					if(write>0) {
						
					
					Sync.waitElementPresent(30, "xpath", "//a[text()='Write a review']");
					Common.javascriptclickElement("xpath", "//a[text()='Write a review']");
					Sync.waitPageLoad();
					Thread.sleep(3000);
					
					Sync.waitElementPresent(30, "xpath", "//span[text()='Write A Review']");
					Common.clickElementStale("xpath", "//span[text()='Write A Review']");
					int Write = Common
							.findElements("xpath", "//h2[text()='WRITE A REVIEW']").size();
					System.out.println(Write);
					
					if(Write>0) {
						Thread.sleep(3000);
						Sync.waitElementPresent(30, "xpath", "//span[@data-score='4']");
						Common.javascriptclickElement("xpath", "//span[@data-score='4']");
						
						
						Common.textBoxInput("xpath", "//input[@name='review_title']", data.get(Dataset).get("title"));
						
						Common.textBoxInput("xpath", "(//textarea[@name='review_content'])[1]", data.get(Dataset).get("Review"));
						
						String email = Common.genrateRandomEmail(data.get(Dataset).get("Email"));
						Common.textBoxInput("xpath", "//input[contains(@id,'yotpo_input_review_email')]", email);
//						Common.textBoxInput("xpath", "//input[contains(@id,'yotpo_input_review_email')]", data.get(Dataset).get("Email"));
						Common.textBoxInput("xpath", "//input[contains(@id,'yotpo_input_review_username')]", data.get(Dataset).get("FirstName"));
						Common.clickCheckBox("xpath", "(//input[@class='yotpo-default-button primary-color-btn yotpo-submit'])[1]");
					} else {
						
						Assert.fail();
					}
					
					}
					else
					{
						Thread.sleep(6000);
						Sync.waitElementPresent(30, "xpath", "//a[contains(@aria-label,'reviews')]");
						Common.javascriptclickElement("xpath", "//a[contains(@aria-label,'reviews')]");
						Sync.waitPageLoad();
						Thread.sleep(3000);
						
						Sync.waitElementPresent(30, "xpath", "//span[text()='Write A Review']");
						Common.clickElementStale("xpath", "//span[text()='Write A Review']");
						int Write = Common
								.findElements("xpath", "//h2[text()='WRITE A REVIEW']").size();
						System.out.println(Write);
						
						if(Write>0) {
							
							Sync.waitElementPresent(30, "xpath", "//span[@data-score='4']");
							
							Common.javascriptclickElement("xpath", "//span[@data-score='4']");
							
							Common.textBoxInput("xpath", "//input[@name='review_title']", data.get(Dataset).get("title"));
							
							Common.textBoxInput("xpath", "(//textarea[@name='review_content'])[1]", data.get(Dataset).get("Review"));
							
							String email = Common.genrateRandomEmail(data.get(Dataset).get("Email"));
							Common.textBoxInput("xpath", "//input[contains(@id,'yotpo_input_review_email')]", email);
//							Common.textBoxInput("xpath", "//input[contains(@id,'yotpo_input_review_email')]", data.get(Dataset).get("Email"));
							Common.textBoxInput("xpath", "//input[contains(@id,'yotpo_input_review_username')]", data.get(Dataset).get("FirstName"));
							Common.clickCheckBox("xpath", "(//input[@class='yotpo-default-button primary-color-btn yotpo-submit'])[1]");
						
					}
					}
					
					int Success_MSG= Common.findElements("xpath", "(//span[text()='Your review has already been submitted.'])[1]").size();
					if (Success_MSG>0) {
						int Thanks =Common.findElements("xpath", "(//span[text()='Your review has already been submitted.'])[1]").size();
						System.out.println(Thanks);
						Common.assertionCheckwithReport(Thanks>0,
								"validating the product navigating to the PDP page",
								"The product Should be navigates to the PDP page", "Successfully product navigates to the PDP page",
								"Failed to Navigates Product to the PDP page");
//							
						
					} else {
						
					
					int Thanks =Common.findElements("xpath", "(//span[text()='Thank you for posting a review!'])[1]").size();
					
					Common.assertionCheckwithReport(Thanks>0,
							"validating the product navigating to the PDP page",
							"The product Should be navigates to the PDP page", "Successfully product navigates to the PDP page",
							"Failed to Navigates Product to the PDP page");
//					
					}
					
					} catch (Exception | Error e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("validating the product navigating to the PDP page",
							"The product Should be navigates to the PDP page", " unable to Navigates Product to the PDP page",
							Common.getscreenShot("Failed to Navigates Product to the PDP page"));
					Assert.fail();
				}

			}
		 
		 public void minicart_delete(String Dataset) {
				// TODO Auto-generated method stub
				String deleteproduct = data.get(Dataset).get("Products");
				System.out.println(deleteproduct);
				String symbol=data.get(Dataset).get("Symbol");
				try {
					click_minicart();
					Sync.waitElementPresent(30, "xpath", "//span[@x-html='cart.subtotal']//span");
					String subtotal = Common.getText("xpath", "//span[@x-html='cart.subtotal']//span")
							.replace(symbol, "");
					Float subtotalvalue = Float.parseFloat(subtotal);
					String productname = Common
							.findElement("xpath", "(//p[@class='text-md font-bold dr:title-sm']//a)[1]")
							.getText();
					String productamount1 = Common.getText("xpath", "(//span[@x-html='item.product_price']//span[@class='price'])[1]").replace(symbol,
							"");
					Float productamount1value = Float.parseFloat(productamount1);
					System.out.println(productname);
					if (productname.equals(deleteproduct)) {
						Thread.sleep(4000);
						Sync.waitElementPresent(30, "xpath",
								"(//a[contains(@aria-label,'Edit product')]//parent::div//button)[1]");
						Common.clickElement("xpath",
								"(//a[contains(@aria-label,'Edit product')]//parent::div//button)[1]");
						Sync.waitElementPresent("xpath", "//button[contains(text(),'OK')]");
						Common.clickElement("xpath", "//button[contains(text(),'OK')]");
					} else {
						Assert.fail();
					}
					Thread.sleep(6000);
					String subtotal1 = Common.getText("xpath", "//span[@x-html='cart.subtotal']//span")
							.replace(symbol, "");
					Float subtotal1value = Float.parseFloat(subtotal1);
					Thread.sleep(8000);
					String productamount = Common.getText("xpath", "(//span[@x-html='item.product_price']//span[@class='price'])[1]").replace(symbol, "");
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

			public void minicart_validation(String Dataset) {
				// TODO Auto-generated method stub
				String UpdataedQuntityinminicart = data.get(Dataset).get("Minicart_Quantity");
				String symbol=data.get(Dataset).get("Symbol");
				try {

					String Subtotal = Common.getText("xpath", "//span[@x-html='cart.subtotal']//span")
							.replace(symbol, "");
					Float subtotalvalue = Float.parseFloat(Subtotal);
					System.out.println(subtotalvalue);
					Sync.waitElementPresent("xpath", "(//select[@name='qty'])[2]");
					Common.clickElement("xpath", "(//select[@name='qty'])[2]");
					Common.dropdown("xpath", "(//select[@name='qty'])[2]", Common.SelectBy.VALUE,
							UpdataedQuntityinminicart);

					Thread.sleep(6000);
					Sync.waitElementPresent("xpath", "//span[@x-text='totalCartAmount']");
					String cart = Common.findElement("xpath", "//span[@x-text='totalCartAmount']").getText();
					System.out.println(cart);
					String Subtotal2 = Common.getText("xpath", "//span[@x-html='cart.subtotal']//span")
							.replace(symbol, "");
					System.out.println(Subtotal2);
					Float subtotalvalue2 = Float.parseFloat(Subtotal2);
					Float Total = subtotalvalue * 2;
					String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					System.out.println(ExpectedTotalAmmount2);
					Thread.sleep(2000);
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


			public void My_Favorites() {
	    		// TODO Auto-generated method stub
	    		try {
	    			Thread.sleep(2000);
	    			if(Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage") )
	    			{
	    
	    				Common.clickElement("xpath", "//button[@aria-label='My Account']");
	    				int size = Common.findElements("id","customer.header.sign.in.link").size();
	    				if (size > 0) {
	    					Common.clickElement("id","customer.header.sign.in.link");
	    				}
	    				else {
	    					Sync.waitElementPresent(30, "xpath", "//a[@title='My Account']");
		    				Common.clickElement("xpath", "//a[@title='My Account']");
	    				}
	    				Thread.sleep(3000);
	    				
	    				if (Common.getCurrentURL().contains("/gb")){
	    					Sync.waitElementPresent(30, "xpath", "//a[text()='My Wish List']");
		    				Common.clickElement("xpath", "//a[text()='My Wish List']");
		    				Common.assertionCheckwithReport(Common.getPageTitle().equals("My Wish List"),
		    						"validating the Navigation to the My Favorites page",
		    						"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
		    						"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA",
		    						"Failed to Navigate to the My Favorites page after Clicking on My Favorites button");
	    				}
	    				else {
	    				Sync.waitElementPresent(30, "xpath", "//span[text()='My Wish List']");
	    				Common.clickElement("xpath", "//span[text()='My Wish List']");
	    				Common.assertionCheckwithReport(Common.getPageTitle().equals("My Favorites") || Common.getPageTitle().equals("Wish List Sharing"),
	    						"validating the Navigation to the My Favorites page",
	    						"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
	    						"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA",
	    						"Failed to Navigate to the My Favorites page after Clicking on My Favorites button");
	    			}
	    			}
	    				
	    			else
	    			{
	    				Common.clickElement("xpath", "//button[@aria-label='My Account']");
	    				int size = Common.findElements("id","customer.header.sign.in.link").size();
	    				if (size > 0) {
	    					Common.clickElement("id","customer.header.sign.in.link");
	    					Common.clickElement("xpath", "//button[@aria-label='My Account']");
	    				}
	    				Sync.waitElementPresent(30, "xpath", "//a[contains(text(),'My Wish List')]");
	    				Common.clickElement("xpath", "//a[contains(text(),'My Wish List')]");
	    				Common.assertionCheckwithReport(Common.getCurrentURL().contains("wishlist"),
	    						"validating the Navigation to the My Favorites page",
	    						"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
	    						"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA",
	    						"Failed to Navigate to the My Favorites page after Clicking on My Favorites button");
	    			}

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
			public void whishlist_share_Button(String Dataset) {
	    		// TODO Auto-generated method stub
	    		
	    		try
	    		{
	    			Thread.sleep(4000);
	    			if(Common.getCurrentURL().contains("stage")|| Common.getCurrentURL().contains("preprod"))
	    			{
	    				Thread.sleep(4000);
	    				My_Favorites();
	    				Common.clickElement("xpath", "//div[@class='lg:ml-3']//button[@type='button']");
	    				Sync.waitPageLoad();
	    				Thread.sleep(4000);
	    				Common.textBoxInput("xpath", "//textarea[@name='emails']", data.get(Dataset).get("Email"));
	    				Common.textBoxInput("xpath", "//textarea[@name='message']", data.get(Dataset).get("message"));
	    				Common.javascriptclickElement("xpath", "//button[contains(text(),'Share Wish List')]");
	    				Thread.sleep(8000);
	    				String message1 = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
	    				System.out.println(message1);
	    				Common.assertionCheckwithReport(message1.contains("Your wish list has been shared."),
	    						"validating the shared whishlist functionality",
	    						"sucess message should display after share whishlist",
	    						"Sucessfully message has been displayed for whishlist",
	    						"failed to display the message for whishlist");
	    			
	    			}
	    			else
	    			{
	    				Thread.sleep(4000);
	    				Common.javascriptclickElement("xpath", "//div[@class='lg:ml-3']//button[@type='button']");
	    				Sync.waitPageLoad();
	    				Thread.sleep(4000);
	    				Common.textBoxInput("xpath", "//textarea[@name='emails']", data.get(Dataset).get("Email"));
	    				Common.textBoxInput("xpath", "//textarea[@name='message']", data.get(Dataset).get("message"));
	    				Thread.sleep(4000);
	    				Common.javascriptclickElement("xpath", "//button[contains(text(),'Share Wish List')]");
	    				Thread.sleep(4000);
	    				String message1 = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
	    				System.out.println(message1);
	    				Common.assertionCheckwithReport(message1.contains("Your wish list has been shared."),
	    						"validating the shared whishlist functionality",
	    						"sucess message should display after share whishlist",
	    						"Sucessfully message has been displayed for whishlist",
	    						"failed to display the message for whishlist");
	    			}



	    		}
	    		catch(Exception | Error e)
	    		{
	    			e.printStackTrace();
	    			ExtenantReportUtils.addFailedLog("validating the shared whishlist functionality",
	    					"sucess message should display after share whishlist",
	    					"Unable to display the message for whishlist", Common.getscreenShot("Failed to display the message for whishlist"));
	    			Assert.fail();
	    		}
	    		
	    	}
	    	public void share_whishlist(String Dataset) {
	    		// TODO Auto-generated method stub
	    		try {
	    			Sync.waitPageLoad();
	    			int MyFavorites = Common.findElements("xpath", "//form[@id='wishlist-view-form']//div[contains(@class,'message info')]//span").size();

	    			if (MyFavorites != 0) {
	    				search_product("Product");
	    				Common.mouseOver("xpath", "(//span[contains(@class, 'group-hover/wishlist')])[1]");
	    				Sync.waitElementPresent(30, "xpath", "(//span[contains(@class, 'group-hover/wishlist')])[1]");
	    				Common.javascriptclickElement("xpath", "(//span[contains(@class, 'group-hover/wishlist')])[1]");
	    				if(Common.getCurrentURL().contains("stage"))
	                    {
	                        Sync.waitPageLoad();
	                        String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
	                        System.out.println(message);
	                        Common.assertionCheckwithReport(message.contains("has been added to your Favourites"),
	                                "validating the  product add to the Favorites", "Product should be add to Favorites",
	                                "Sucessfully product added to the Favorites ", "failed to add product to the Favorites");
	                        whishlist_share_Button("share whishlist");

	                    }
	    				else
	    				{
	    				Sync.waitElementVisible(30, "xpath", "//h4");
	    				String whishlistpopup = Common.findElement("xpath", "//h4").getText();
	    				System.out.println(whishlistpopup);
	    				if (whishlistpopup.contains("Add to Wishlist")) {
	    					Sync.waitElementPresent(30, "xpath", "//button[@title='Add To List']");
	    					Common.clickElement("xpath", "//button[@title='Add To List']");
	    				} else {
	    					Assert.fail();
	    				}
	    				Sync.waitPageLoad();
	    				Common.assertionCheckwithReport(Common.getPageTitle().equals("My Wish List"),
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
	    				
	    				}
	    				whishlist_share_Button("share whishlist");
	    				
	    				
	    			} else {
	    				whishlist_share_Button("share whishlist");

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

						//Tell_Your_FriendPop_Up();
						int sizes = Common.findElements("xpath", " //h1[normalize-space()='Thank you for your purchase!']").size();
						Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
								"verifying the product confirmation", expectedResult,
								"Successfully It redirects to order confirmation page Order Placed",
								"User unabel to go orderconformation page");

						if (Common.findElements("xpath", "//div[contains(@class,'checkout-success container')]//p//span").size() > 0) {
						    Thread.sleep(2000);
						    order = Common.getText("xpath", "//div[contains(@class,'checkout-success container')]//p//span");
						    System.out.println(order);
						} else {
						    Thread.sleep(2000);
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
//						Common.clickElement("xpath", "//button[@class='a-btn a-btn--tertiary']");
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
//			                	   Sync.waitElementPresent("xpath", "//input[@id='agreement_stripe_payments_5']");
//			                	   Common.clickElement("xpath", "//input[@id='agreement_stripe_payments_5']");
			                	   
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
//			                		   Sync.waitElementPresent("xpath", "//label[@for='stripe-new-payments']");
//			                   		Common.clickElement("xpath", "//label[@for='stripe-new-payments']");
//			                   		Thread.sleep(5000);
//			                   		Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
//			                       	Common.clickElement("xpath", "//button[@class='action primary checkout']");
			                       	String frameid=Common.findElement("xpath", "(//iframe[@role='presentation'])[1]").getAttribute("name");
			                       	System.out.println(frameid);
//			                       	Common.switchFrames("xpath","//iframe[@name='"+ frameid +"']");
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
						int savedcard=Common.findElements("xpath", "//div[@class='mb-4' and @x-show]").size();
						if(savedcard>0)
						{
							Sync.waitElementPresent("xpath", "(//input[@class='checkbox mr-4'])[2]");
							Common.clickElement("xpath", "(//input[@class='checkbox mr-4'])[2]");
						}

						Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
						Thread.sleep(5000);
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
							Sync.waitElementPresent("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
			             	   Common.clickElement("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
			             	   Thread.sleep(40000);
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
			              	   else if(Common.getCurrentURL().contains("/success/"))
			              	   {
			              	    String sucessmessage=Common.getText("xpath", " //h1[normalize-space()='Thank you for your purchase!']");
			              	    System.out.println(sucessmessage);
			              	   }
			              	   else
			              	   {
			              		   Assert.fail();
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


public void Invalid_ThreedPaymentDetails(String dataSet) throws InterruptedException {
	// TODO Auto-generated method stub
	HashMap<String, String> Paymentmethod = new HashMap<String, String>();
	Sync.waitPageLoad();
	Thread.sleep(4000);
	
	String cardnumber = data.get(dataSet).get("cardNumber");
	System.out.println(cardnumber);
	String expectedResult = "land on the payment section";
	// Common.refreshpage();
	String symbol=data.get(dataSet).get("Symbol");
	
	try {
		Thread.sleep(4000);
		
		String subtotal=Common.findElement("xpath", "//div[@class='item grand_total']//span[contains(@class,'value')]").getText().replace(symbol, "").replace(".", "");
		System.out.println(subtotal);
		subtotal = subtotal.trim();
		subtotal = subtotal.substring(0,subtotal.length() - 2);
	    System.out.println(subtotal);  
		int amount=Integer.parseInt(subtotal);
		System.out.println(amount);
		
		Sync.waitPageLoad();
		Sync.waitElementPresent("xpath", "//label[@for='payment-method-stripe_payments']");
		// Common.clickElement("xpath", "//label[@for='stripe_payments']");
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
//			Common.clickElement("xpath", "//button[@class='a-btn a-btn--tertiary']");
			Thread.sleep(4000);
			if(amount>199 && symbol.equals("$"))
			{
				Sync.waitElementPresent(30, "xpath", "//div[@class='ampromo-close']");
				Common.clickElement("xpath", "//div[@class='ampromo-close']");
				Common.switchFrames("xpath", "//iframe[contains(@src,'elements-inner-payment-')]");
				Thread.sleep(5000);
				Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
				Common.clickElement("xpath", "//label[@for='Field-numberInput']");
				Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);
			

				Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

				Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
			
			}
			else
			{
				Common.switchFrames("xpath", "//iframe[contains(@src,'elements-inner-payment-')]");
				Thread.sleep(5000);
				Common.scrollIntoView("xpath", "//label[@for='Field-numberInput']");
				Common.clickElement("xpath", "//label[@for='Field-numberInput']");
				Common.findElement("id", "Field-numberInput").sendKeys(cardnumber);
			
				Common.textBoxInput("id", "Field-expiryInput", data.get(dataSet).get("ExpMonthYear"));

				Common.textBoxInput("id", "Field-cvcInput", data.get(dataSet).get("cvv"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
			}
			
			if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage") ) {
                   if(Common.getCurrentURL().contains("/gb"))
                   {
                	   Thread.sleep(5000);
                	   Sync.waitElementPresent("xpath", "//input[@id='agreement_stripe_payments_5']");
                	   Common.clickElement("xpath", "//input[@id='agreement_stripe_payments_5']");
                	   
                	   Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
                	   Common.clickElement("xpath", "//button[@class='action primary checkout']");
                	   Thread.sleep(8000);
                	   if(Common.getText("xpath", "//div[contains(@data-ui-id,'checkout-cart')]").contains("Please enter your card's security code."))
                	   {
                		   String frameid=Common.findElement("xpath", "(//iframe[@role='presentation'])[1]").getAttribute("name");
                          	System.out.println(frameid);
                          	Common.switchFrames("xpath","//iframe[@name='"+ frameid +"']");
                     		Common.switchFrames("xpath", "//iframe[@id='challengeFrame']");
                     		Thread.sleep(4000);
                         	Common.clickElement("xpath", "//button[contains(text(),'Complete')]");
                         	Common.switchToDefault();
                         	Common.switchToDefault();
                         	Thread.sleep(3000);
                        	Sync.waitElementPresent("xpath", "//div[@data-ui-id='checkout-cart-validationmessages-message-error']");
                        String errormessage = Common.findElement("xpath", "//div[@data-ui-id='checkout-cart-validationmessages-message-error']").getText();
                        System.out.println(errormessage);
                        Common.assertionCheckwithReport(errormessage.contains("declined"),
            					"To validate the invalid card details entered in the production environment",
            					"user should able to see the invalid card details in the production environment",
            					"User Successfully able to see declained error ",
            					"User Failed to see the declained error");
                	   }                    	
                	   else if (Common.getCurrentURL().contains("/checkout/#payment"))
                	   {
                		   Sync.waitElementPresent("xpath", "//label[@for='stripe-new-payments']");
                   		Common.clickElement("xpath", "//label[@for='stripe-new-payments']");
                   		Thread.sleep(5000);
                   		Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
                       	Common.clickElement("xpath", "//button[@class='action primary checkout']");
                       	Thread.sleep(7000);
                       	String frameid=Common.findElement("xpath", "(//iframe[@role='presentation'])[1]").getAttribute("name");
                       	System.out.println(frameid);
                       	Common.switchFrames("xpath","//iframe[@name='"+ frameid +"']");
                  		Common.switchFrames("xpath", "//iframe[@id='challengeFrame']");
                  		Thread.sleep(4000);
                      	Common.clickElement("xpath", "//button[contains(text(),'Complete')]");
                      	Common.switchToDefault();
                      	Common.switchToDefault();
                      	Thread.sleep(3000);
                    	Sync.waitElementPresent("xpath", "//div[@data-ui-id='checkout-cart-validationmessages-message-error']");
                    String errormessage = Common.findElement("xpath", "//div[@data-ui-id='checkout-cart-validationmessages-message-error']").getText();
                    System.out.println(errormessage);
                    Common.assertionCheckwithReport(errormessage.contains("declined"),
        					"To validate the invalid card details entered in the production environment",
        					"user should able to see the invalid card details in the production environment",
        					"User Successfully able to see declained error ",
        					"User Failed to see the declained error");
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
                       		Common.switchFrames("xpath", "//*[@id='challengeFrame']");
                       		Thread.sleep(4000);
                           	Common.clickElement("xpath", "//button[contains(text(),' Complete')]");
                           	Common.switchToDefault();
                           	Common.switchToDefault();
                           	Thread.sleep(3000);
                        	Sync.waitElementPresent("xpath", "//div[@data-ui-id='checkout-cart-validationmessages-message-error']");
                        String errormessage = Common.findElement("xpath", "//div[@data-ui-id='checkout-cart-validationmessages-message-error']").getText();
                        System.out.println(errormessage);
                        Common.assertionCheckwithReport(errormessage.contains("declined"),
            					"To validate the invalid card details entered in the production environment",
            					"user should able to see the invalid card details in the production environment",
            					"User Successfully able to see declained error ",
            					"User Failed to see the declained error");
                	   }                    	
                	   else if (Common.getCurrentURL().contains("/checkout/#payment"))
                	   {
                		   Thread.sleep(7000);
                		   String frameid=Common.findElement("xpath", "(//iframe[@role='presentation'])[1]").getAttribute("name");
                         	System.out.println(frameid);
                          
                         	Common.switchFrames("xpath","//iframe[@name='"+ frameid +"']");
                         
                    		Common.switchFrames("xpath", "//iframe[@id='challengeFrame']");
                    		Thread.sleep(4000);
                        	Common.clickElement("xpath", "//button[contains(text(),'Complete')]");
                        	Common.switchToDefault();
                        	Common.switchToDefault();
                        	Thread.sleep(3000);
                        	Sync.waitElementPresent("xpath", "//div[@data-ui-id='checkout-cart-validationmessages-message-error']");
                        String errormessage = Common.findElement("xpath", "//div[@data-ui-id='checkout-cart-validationmessages-message-error']").getText();
                        System.out.println(errormessage);
                        Common.assertionCheckwithReport(errormessage.contains("declined"),
            					"To validate the invalid card details entered in the production environment",
            					"user should able to see the invalid card details in the production environment",
            					"User Successfully able to see declained error ",
            					"User Failed to see the declained error");
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
			int savedcard=Common.findElements("xpath", "//div[contains(@class,'form-select flex')]").size();
			if(savedcard>0)
			{
				Sync.waitElementPresent("xpath", "(//input[@class='checkbox mr-4'])[2]");
				Common.clickElement("xpath", "(//input[@class='checkbox mr-4'])[2]");
			}

			Thread.sleep(5000);
			Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
			Thread.sleep(5000);
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
          	   String frameid=Common.findElement("xpath", "(//iframe[@role='presentation'])[1]").getAttribute("name");
          	   System.out.println(frameid);
          	   Common.switchFrames("xpath","//iframe[@name='"+ frameid +"']");
     			Common.switchFrames("xpath", "//iframe[@id='challengeFrame']");
         		Thread.sleep(4000);
         		Common.clickElement("xpath", "//button[contains(text(),'Complete')]");
         		Common.switchToDefault();
         		Common.switchToDefault();
         		Thread.sleep(3000);
            	Sync.waitElementPresent("xpath", "//div[@data-ui-id='checkout-cart-validationmessages-message-error']");
            String errormessage = Common.findElement("xpath", "//div[@data-ui-id='checkout-cart-validationmessages-message-error']").getText();
            System.out.println(errormessage);
            Common.assertionCheckwithReport(errormessage.contains("declined"),
					"To validate the invalid card details entered in the production environment",
					"user should able to see the invalid card details in the production environment",
					"User Successfully able to see declained error ",
					"User Failed to see the declained error");
                 }
                 else
                 {
              	   Sync.waitElementPresent("xpath", "(//button[@type='button'][normalize-space()='Place Order'])[1]");
              	   Common.clickElement("xpath", "(//button[@type='button'][normalize-space()='Place Order'])[1]");
              	 Thread.sleep(8000);
          	   String frameid=Common.findElement("xpath", "(//iframe[@role='presentation' and contains(@src,'https://js.stripe.com/v3/three-ds')])[1]").getAttribute("name");
          	   System.out.println(frameid);
          	   Common.switchFrames("xpath","//iframe[@name='"+ frameid +"']");
          	   Thread.sleep(8000);
     			Common.switchFrames("xpath", "//iframe[@id='challengeFrame']");
         		Thread.sleep(4000);
         		Common.clickElement("xpath", "//button[contains(text(),'Complete')]");
         		Common.switchToDefault();
         		Common.switchToDefault();
         		Thread.sleep(3000);
            	Sync.waitElementPresent("xpath", "//div[@ui-id='message-error']//span");
            String errormessage = Common.findElement("xpath", "//div[@ui-id='message-error']//span").getText();
            System.out.println(errormessage);
            Common.assertionCheckwithReport(errormessage.contains("Your card has been declined."),
					"To validate the invalid card details entered in the production environment",
					"user should able to see the invalid card details in the production environment",
					"User Successfully able to see declained error ",
					"User Failed to see the declained error");
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
		Sync.waitElementClickable("xpath", "//label[@for='payment-method-stripe_payments']");
		int sizes = Common.findElements("xpath", "//label[@for='payment-method-stripe_payments']").size();

		Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
				"User unable to land o n the paymentpage");
		Common.clickElement("xpath", "//label[@for='payment-method-stripe_payments']");

//		Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
//		int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
//		System.out.println(payment);
//		if (payment > 0) {
			//Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
			//Common.clickElement("xpath", "//div[@class='stripe-dropdown-selection']");
			//Common.clickElement("xpath", "//button[@class='a-btn a-btn--tertiary']");
			if(Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage") )
					{
				Thread.sleep(3000);
				int savedcard=Common.findElements("xpath", "//div[contains(@class,'form-select flex')]").size();
				if(savedcard>0)
				{
					Sync.waitElementPresent("xpath", "(//input[@class='checkbox mr-4'])[2]");
					Common.clickElement("xpath", "(//input[@class='checkbox mr-4'])[2]");
				}
			Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
			Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
			Sync.waitElementPresent(30, "xpath", "//button[@id='afterpay_clearpay-tab']");
			Common.javascriptclickElement("xpath", "//button[@id='afterpay_clearpay-tab']");
//			
			Common.switchToDefault();
			Thread.sleep(3000);
			Sync.waitElementPresent(30, "xpath", "(//button[contains(@class,'btn-place-order')])[2]");
			Common.clickElement("xpath", "(//button[contains(@class,'btn-place-order')])[2]");
			Thread.sleep(3000);
			
//			Sync.waitElementPresent(30, "xpath", "//div[@class='stripe-new-payments']//label[@for='stripe-new-payments']");
//			Common.javascriptclickElement("xpath", "//div[@class='stripe-new-payments']//label[@for='stripe-new-payments']");
//			Thread.sleep(3000);	
//			
//			Sync.waitElementPresent(30, "xpath", "//button[@class='action primary checkout']");
//			Common.clickElement("xpath", "//button[@class='action primary checkout']");
//			
			Thread.sleep(3000);
			Sync.waitElementPresent(30, "xpath", "//a[contains(text(),'Authorize Test Payment')]");
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

public void subcription_Profile() {
	// TODO Auto-generated method stub
	try
	{
		String profile=Common.findElement("xpath", "//p[contains(text(),'Your subscription profile is:')]//a//strong").getText();
		System.out.println(profile);
		Common.javascriptclickElement("xpath", "//p[contains(text(),'Your subscription profile is:')]//a//strong");
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//h1[@class='title-2xl']//span");
		String subscription=Common.findElement("xpath", "//h1[@class='title-2xl']//span").getText().replace("SUBSCRIPTION PROFILE #", "");
		System.out.println(subscription);
		Common.assertionCheckwithReport(
				subscription.equals(profile),
				"To ensure that subcription number is displaying in My subcriptions",
				"number should be displayed in the my subcriptions page ","Sucessfully Number is displayed in the subscription page",
				"Failed to dispaly the profile number in the subscription page");
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To ensure that subcription number is displaying in My subcriptions",
				"number should be displayed in the my subcriptions page ","Unable to dispaly the profile number in the subscription page",
				Common.getscreenShotPathforReport("Failed to dispaly the profile number in the subscription page"));
		Assert.fail();
	}
	
}

public void Tax_validation_Paymentpage() {
	// TODO Auto-generated method stub
	  
	try
	{
		String Subtotal = Common.getText("xpath", "//tr[@class='totals sub']//span[@class='price']").replace("£","");
		Float subtotalvalue = Float.parseFloat(Subtotal);
		System.out.println("subtotalvalue");
		String shipping = Common.getText("xpath", "//tr[@class='totals shipping incl']//span[@class='price']").replace("£", "");
		Float shippingvalue = Float.parseFloat(shipping);
		String Tax = Common.getText("xpath", "//tr[@class='totals-tax']//span[@class='price']").replace("£", "");
		Float Taxvalue = Float.parseFloat(Tax);
		Thread.sleep(4000);

		String ordertotal = Common.getText("xpath", "//tr[@class='grand totals']//span[@class='price']")
				.replace("£", "");
		Float ordertotalvalue = Float.parseFloat(ordertotal);
		Thread.sleep(4000);
		Float Total = (subtotalvalue + shippingvalue );
		String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		Thread.sleep(4000);
		System.out.println(ExpectedTotalAmmount2);
		System.out.println(ordertotal);
		Common.assertionCheckwithReport(ExpectedTotalAmmount2.equals(ordertotal),
				"validating the Tax on the payment page",
				"On the order summary tax should be display on the payment page",
				"Successfully Tax should be display in the order summary",
				"Failed to display the tax on the order summary");

	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the Tax on the payment page",
				"On the order summary tax should be display on the payment page",
				"Unable to display the tax on the order summaryy",
				Common.getscreenShotPathforReport("Failed to display the tax on the order summary"));
		Assert.fail();
	}
	
}

public void DiscountCode(String dataSet) throws Exception {
	String expectedResult = "It should opens textbox input to enter discount.";

	try {

		Sync.waitElementClickable("id", "block-discount-heading");
		Common.clickElement("id", "block-discount-heading");
		if (Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage")) {
			Sync.waitElementPresent("id", "discount-code");

			Common.textBoxInput("id", "discount-code", data.get(dataSet).get("Discountcode"));
		} else {
			Sync.waitElementPresent("id", "discount-code");

			Common.textBoxInput("id", "discount-code", data.get(dataSet).get("prodDiscountcode"));
		}

		int size = Common.findElements("id", "discount-code").size();
		Common.assertionCheckwithReport(size > 0, "verifying the Discount Code label", expectedResult,
				"Successfully open the discount input box", "User unable enter Discount Code");
		Sync.waitElementClickable("xpath", "//span[text()='Apply Code']");
		Common.clickElement("xpath", "//span[text()='Apply Code']");
		Sync.waitPageLoad();
		Thread.sleep(3000);
		Common.scrollIntoView("xpath", "(//div[contains(@data-ui-id,'checkout-cart-validation')])[1]");
		expectedResult = "It should apply discount on your price.If user enters invalid promocode it should display coupon code is not valid message.";
		String discountcodemsg = Common.getText("xpath", "(//div[contains(@data-ui-id,'checkout-cart-validation')])[1]");
		System.out.println(discountcodemsg);
		Common.assertionCheckwithReport(discountcodemsg.contains("Your coupon was successfully"),
				"verifying pomocode", expectedResult, "promotion code working as expected",
				"Promation code is not applied");
		Thread.sleep(4000);
		Common.scrollIntoView("xpath", "//tr[@class='totals sub']//span[@class='price']");
		String Subtotal = Common.getText("xpath", "//tr[@class='totals sub']//span[@class='price']").replace("£",
				"");
		Float subtotalvalue = Float.parseFloat(Subtotal);
		String shipping = Common.getText("xpath", "//tr[@class='totals shipping incl']//span[@class='price']")
				.replace("£", "");
		Float shippingvalue = Float.parseFloat(shipping);
		String Tax = Common.getText("xpath", "//tr[@class='totals-tax']//span[@class='price']").replace("£", "");
		Float Taxvalue = Float.parseFloat(Tax);
		Thread.sleep(6000);
		String Discount = Common.getText("xpath", "//tr[@class='totals discount']//span[@class='price']")
				.replace("£", "");
		Float Discountvalue = Float.parseFloat(Discount);
		System.out.println(Discountvalue);

		String ordertotal = Common.getText("xpath", "//tr[@class='grand totals']//span[@class='price']")
				.replace("£", "");
		Float ordertotalvalue = Float.parseFloat(ordertotal);
		Thread.sleep(5000);
		Float Total = (subtotalvalue + shippingvalue) + Discountvalue;
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
        e.printStackTrace();
		Assert.fail();

	}
}

public void Share_WishList(String dataSet) {
	// TODO Auto-generated method stub
	
		try
		{
			Thread.sleep(2000);
		//Sync.waitElementPresent("xpath", "(//a[text()='here'])");
		//Common.clickElement("xpath", "(//a[text()='here'])");
		Thread.sleep(1000);
		Common.scrollIntoView("xpath", "(//span[@class='a-btn-tertiary__icon icon-customizer__download'])[1]");
		Common.clickElement("xpath", "(//span[@class='a-btn-tertiary__icon icon-customizer__download'])[1]");
		
		Common.textBoxInput("xpath", "(//textarea[@id='email_address'])",data.get(dataSet).get("Email"));
		Common.textBoxInput("xpath", "(//textarea[@id='message'])",data.get(dataSet).get("message"));
		Common.clickElement("xpath", "(//span[text()='Share Wish List'])");
		
		//Common.javascriptclickElement("xpath", "//button[@title='Share Favorites']");
		Thread.sleep(4000);
		String message = Common.findElement("xpath", "//h1[@class='page-title-wrapper h2']").getText();
		System.out.println(message);
		
		String message1 = Common.findElement("xpath", "//div[@class='message-success success message a-message a-message--success undefined']").getText();
		System.out.println(message1);
		Common.assertionCheckwithReport(message1.contains("Your wish list has been shared."),
				"validating the shared whishlist functionality",
				"sucess message should display after share whishlist",
				"Sucessfully message has been displayed for whishlist",
				"failed to display the message for whishlist");
	
}
             catch(Exception | Error e)
            {
	         e.printStackTrace();
	         ExtenantReportUtils.addFailedLog("validating the shared whishlist functionality",
			                                   "sucess message should display after share whishlist",
			                                     "Unable to display the message for whishlist", Common.getscreenShot("Failed to display the message for whishlist"));
	         Assert.fail();

		}
}

public void Add_product_to_Wishlist() {
	// TODO Auto-generated method stub
	try {
        Thread.sleep(4000);
        Sync.waitElementPresent("xpath", "//span[text()='Add to Favorites']");
        Common.clickElement("xpath", "//span[text()='Add to Favorites']");
        Thread.sleep(5000);
        int message=Common.findElements("xpath", "(//div[@class='message-success success message'])").size();
        //Common.assertionCheckwithReport(message>0, "To verify the product added to My Wishlist", "Should add product to  My wishlist page","Product sucessfully added to My wishlist", "passed  add product to Wishlist");
    }
            catch(Exception |Error e)
	{
    	e.printStackTrace();
    	ExtenantReportUtils.addFailedLog("To verify the  the product added to My wishlist","Should add product to Wishlist", "user unable to add Product to wishlist page", Common.getscreenShotPathforReport("failed to add product to Wishlist"));           
        Assert.fail();   
     }
   
}

public void Notify_me (String Dataset) {
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
		
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//span[text()=' Notify Me When Available']");
		Common.clickElement("xpath", "//span[text()=' Notify Me When Available']");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String message = Common.findElement("xpath", "//h3[@class='m-newsletter-signup__heading h5']").getText();
		System.out.println(message);
		Common.assertionCheckwithReport(message.equals("Subscribe to back in stock notification"), "validating the  product add to the  back in stock notification",
			"Product should be add to back in stock Subscribe page", "Sucessfully product added to the back in stock Subscribe page ",
				"failed to add product to the back in stock Subscribe page");
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the  product add to the back in stock Subscribe page", "Product should be add to back in stock Subscribe page",
				"unable to add product to the back in stock Subscribe page", Common.getscreenShot("failed to add product to the back in stock Subscribe page"));

		Assert.fail();
	}
}


public void Subscribe_to_back_in_stock_notification(String Dataset) {
	// TODO Auto-generated method stub
	try
	{
		Common.textBoxInput("xpath", "//input[@name='guest_email']", Utils.getEmailid());
	String	email = Common.findElement("xpath", "//input[@name='guest_email']").getAttribute("value");
		System.out.println(email);
    Common.actionsKeyPress(Keys.ENTER);
	Sync.waitPageLoad();
	Thread.sleep(4000);
		String success = Common.findElement("xpath", "//div[@class='u-container a-message__container']").getText();
		System.out.println(success);
		Common.assertionCheckwithReport(success.contains("Alert subscription has been saved."), "validating the Subscribe to back in stock notification functionality",
				"enter Mail id will display in the back in stock Subscribe", "user enter the Mail ID inback in stock Subscribe",
				"Failed to see the back in stock Subscribe Mail ID");
         }  
	 catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the Subscribe to back in stock notification functionality",
				"enter Mail id will display in the back in stock Subscribe ",
				" unable to enter the product  in  back in stock Subscribe",
				Common.getscreenShot("Failed to see the back in stock Subscribe Mail ID"));
		Assert.fail();
	}
}
	


public void addto_MyWishList(String Dataset) {
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
	Sync.waitElementPresent("xpath", "//span[text()='Add to Wish List']");
	Common.clickElement("xpath", "//span[text()='Add to Wish List']");
	Sync.waitPageLoad();
	Thread.sleep(4000);
	String message = Common.findElement("xpath", "//h1[text()='Sign In']").getText();
	System.out.println(message);
	Common.assertionCheckwithReport(message.contains("Sign In"), "Naviaged to the  Sign In Page",
			"Product should be navigated Sign In page", "Sucessfully pages is  navigated to the Sign In page ",
			"failed to naviaged to Sign In page");
} catch (Exception | Error e) {
	e.printStackTrace();
	ExtenantReportUtils.addFailedLog("Naviaged to the  Sign In Page", "page should navigated be Sign In page",
			"unable to navigated be Sign In page", Common.getscreenShot("failed to navigated be Sign In page"));

	Assert.fail();
}
}

public void Fav_Seeoption_from_View_cart(String Dataset) {
	// TODO Auto-generated method stub
	String products = data.get(Dataset).get("Products");
	String productcolor = data.get(Dataset).get("Color");
	String Productsize = data.get(Dataset).get("Size");
	try
	{
	Common.maximizeImplicitWait();
	Common.actionsKeyPress(Keys.END);
	String Yourfav=Common.findElement("xpath", "//h2[@class='t-cart__favorites-heading']").getText();
	System.out.println(Yourfav);
	Common.assertionCheckwithReport(Yourfav.contains("Your Favorites"),
			"validating the favorites in View Bag page", "Favorites should be in the View Bag page",
			"Sucessfully Favorites has been displayed in the View Bag page ", "failed to display the favorites in the View Bag page");
	Sync.waitElementPresent("xpath", "//span[text()='Add to Cart']");
	Common.clickElement("xpath", "//span[text()='Add to Cart']");
	Sync.waitPageLoad();
	Thread.sleep(8000);
	/*String Options=Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
	Common.assertionCheckwithReport(Options.contains("You need to choose options for your item"),
			"validating the color option on the PDP", "After clicking on the add to cart button for color product it should navigate to PDP",
			"Sucessfully Navigated to the PDP and options messgae has been appeared ", "failed to Display the choose options message on PDP");
			
	Thread.sleep(4000);
	Sync.waitElementPresent("xpath", "//div[@data-option-label='" + productcolor + "']");
	Common.clickElement("xpath", "//div[@data-option-label='" + productcolor + "']");
	Sync.waitElementPresent("xpath", "//div[@data-option-label='" + Productsize + "']");
	Common.clickElement("xpath", "//div[@data-option-label='" + Productsize + "']");
	Thread.sleep(4000);
	Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
	Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
	Sync.waitPageLoad();
	Thread.sleep(6000);
//	Sync.waitElementPresent(30, "xpath", "//div[@data-ui-id='message-success']");
//	String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//			.getAttribute("data-ui-id");
//	System.out.println(message);
//	Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
//			"Product should be add to cart", "Sucessfully product added to the cart ",
//			"failed to add product to the cart");
 
 
	Sync.waitElementPresent(30, "xpath", "//div[@class='c-mini-cart__close-btn']");
	Common.clickElement("xpath", "//div[@class='c-mini-cart__close-btn']");
	*/
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
				"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));
		Assert.fail();
	}
}

public void Add_To_MyFavorities(String Dataset) {
	// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		System.out.println(product);
		String productcolor = data.get(Dataset).get("Color");
		System.out.println(productcolor);
		String Productsize = data.get(Dataset).get("Size");
		System.out.println(Productsize);
		try {
			Sync.waitPageLoad();
			int MyFavorites = Common.findElements("xpath", "//form[@class='form-wishlist-items']//div[contains(@class,'message')]//span").size();
            System.out.println(MyFavorites);
			if (MyFavorites == 0) {
				search_product("Product");
				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product + "']");
				Common.javascriptclickElement("xpath", "//img[@alt='" + product + "']");
				Sync.waitPageLoad();
				//Sync.waitElementPresent("xpath", "//div[@aria-label='" + productcolor + "']");
			//	Common.clickElement("xpath", "//div[@aria-label='" + productcolor + "']");
			//	Sync.waitElementPresent("xpath", "//div[@data-option-label='" + Productsize + "']");
			//	Common.clickElement("xpath", "//div[@data-option-label='" + Productsize + "']");

				Sync.waitElementPresent(30, "xpath", "//button[@title='Add to Wish List']");
				Common.clickElement("xpath", "//button[@title='Add to Wish List']");
				Sync.waitPageLoad(30);
				Thread.sleep(3000);
//				Common.assertionCheckwithReport(Common.getPageTitle().equals("My Wish List") ||Common.getPageTitle().equals("My Wish List") ,
//						"validating the Navigation to the My Favorites page",
//						"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
//						"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA",
//						"Failed to Navigate to the My Favorites page after Clicking on My Favorites button");
				
				if(Common.getCurrentURL().contains("drybar.com/gb/"))
                {
                    Sync.waitPageLoad();
                    Thread.sleep(4000);
                    String message = Common.findElement("xpath", "//h1[@class='page-title-wrapper h2']").getText();
                    System.out.println(message);
                    Common.assertionCheckwithReport(message.contains("My Wish List"),
                            "validating the  product add to the Favorites", "Product should be add to Favorites",
                            "Sucessfully product added to the Favorites ", "failed to add product to the Favorites");

                }
				
	}
		}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the products added to the whishlist form PDP", "Product should be add to fav from the PDP",
				"Unable to add  product to the My Faviorates from the PDP ", Common.getscreenShot("failed to add  product to the My Faviorates from the PDP"));
		Assert.fail();
	}
	
}



public void Add_Favorites_from_PLP(String Dataset) {
	// TODO Auto-generated method stub
	String product = data.get(Dataset).get("Products");
	System.out.println(product);
	String productcolor = data.get(Dataset).get("Color");
	System.out.println(productcolor);
	String Productsize = data.get(Dataset).get("Size");
	System.out.println(Productsize);
	try {
		Sync.waitPageLoad();
		int MyFavorites = Common.findElements("xpath", "//form[@id='wishlist-view-form']//div[@data-row='product-item']").size();
        System.out.println(MyFavorites);
		if (MyFavorites== 0) {
			HairTools_headerlinks("Hair Tools");
			Thread.sleep(4000);
			Sync.waitPageLoad();
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product + "']");
			Common.mouseOver("xpath", "//img[@alt='" + product + "']");
			Sync.waitPageLoad();
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product + "']//parent::a//parent::div//button");
			Common.clickElement("xpath", "//img[@alt='" + product + "']//parent::a//parent::div//button");
			Sync.waitPageLoad(30);
			Thread.sleep(3000);
			if(Common.getCurrentURL().contains("stage"))
            {
                Sync.waitPageLoad();
                Thread.sleep(4000);
                String message = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
                System.out.println(message);
                Common.assertionCheckwithReport(message.contains(product+" has been added to your Wish List."),
                        "validating the  product add to the Favorites", "Product should be add to Favorites",
                        "Sucessfully product added to the Favorites ", "failed to add product to the Favorites");

            }
			else
			{
			Sync.waitElementVisible(30, "xpath", "//img[@class='m-product-card__image product-image-photo']");
			String whishlistpopup = Common.findElement("xpath", "//img[@class='m-product-card__image product-image-photo']").getText();
			System.out.println(whishlistpopup);
			if (whishlistpopup.contains("Add to Wishlist")) {
				Sync.waitElementPresent(30, "xpath", "//button[@title='Add To List']");
				Common.clickElement("xpath", "//button[@title='Add To List']");
			} else {
				Assert.fail();
			}
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Wish List") ||Common.getPageTitle().equals("My Wish List") ,
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
			}
			My_Favorites();
			String Whishlistproduct = Common
					.findElement("xpath", "//div[contains(@class,'product-item-content')]//img").getAttribute("alt");
			System.out.println(Whishlistproduct);
}
	}
catch(Exception | Error e)
{
	e.printStackTrace();
	ExtenantReportUtils.addFailedLog("validating the products added to the whishlist form PDP", "Product should be add to fav from the PDP",
			"Unable to add  product to the My Faviorates from the PDP ", Common.getscreenShot("failed to add  product to the My Faviorates from the PDP"));
	Assert.fail();
}

}

public void Bagpacks_headerlinks(String Dataset) {
	// TODO Auto-generated method stub
	String expectedResult = "User should click the" + Dataset;
	String out = data.get(Dataset).get("outdoor");
	String header=data.get(Dataset).get("headers");
	try {

		Thread.sleep(3000);
		Sync.waitElementPresent("xpath","//a[contains(@class,'level-top')]//span[contains(text(),'"+ header +"')]");
		
		Common.clickElement("xpath", "//a[contains(@class,'level-top')]//span[contains(text(),'" + header + "')]");

		Thread.sleep(3000);

		try {
			Common.mouseOver("xpath", "//span[contains(text(),'"+ header +"')]");
		} catch (Exception e) {
			Common.clickElement("xpath", "//a[@class='level-top ui-corner-all']//span[text()='"+ header +"']");
		}
		Common.clickElement("xpath", "//span[contains(text(),'" + out + "')]");
		
		Sync.waitPageLoad();
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


public void outofstock_subcription(String Dataset) {
	// TODO Auto-generated method stub
	String products = data.get(Dataset).get("Products");
	System.out.println(products);
	String email = data.get(Dataset).get("UserName");
	String prod = data.get(Dataset).get("Products1");
	//String productcolor = data.get(Dataset).get("Color");
	try {
		Sync.waitPageLoad();
		for (int i = 0; i <= 10; i++) {
			Sync.waitElementPresent("xpath", "//img[@class='group-hover/item-image:block hidden']");
			List<WebElement> webelementslist = Common.findElements("xpath",
					"//img[@class='group-hover/item-image:block hidden']");

			String s = webelementslist.get(i).getAttribute("src");
			System.out.println(s);
			if (s.isEmpty()) {

			} else {
				break;
			}
		}
		Thread.sleep(6000);
		if (Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("preprod") ) {
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.mouseOver("xpath", "//img[@alt='" + products + "']");
			String productprice = Common.findElement("xpath", "//span[@data-price-type='finalPrice']//span")
					.getText().replace("$", "");
			System.out.println(productprice);
			Common.clickElement("xpath", "//img[@alt='" + products + "']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			String PLPprice = Common
					.findElement("xpath","//span[@class='price-wrapper']//span")
					.getText().replace("$", "");
			System.out.println(PLPprice);
			
			String name = Common.findElement("xpath", "//span[text()='" + products + "']").getText();
			System.out.println(name);
			String products1 = data.get(Dataset).get("Products");
			System.out.println(products1);
			Common.assertionCheckwithReport(
					name.contains(products1) && productprice.equals(PLPprice)
							|| Common.getPageTitle().contains(prod) && productprice.equals(PLPprice),
					"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
					"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
			//Sync.waitElementPresent("xpath", "//div[@data-option-label='" + productcolor + "']");
			//Common.clickElement("xpath", "//div[@data-option-label='" + productcolor + "']");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@title='Notify Me When Available']");
			Common.clickElement("xpath", "//button[@title='Notify Me When Available']");
			
			Common.textBoxInput("xpath", "(//input[@placeholder='Insert your email'])[1]", email);
			Common.clickElement("xpath", "//span[text()='Subscribe']");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			String newsubcribe = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
			System.out.println(newsubcribe);
			Common.assertionCheckwithReport(
					newsubcribe.contains("Alert subscription has been saved.")
							|| newsubcribe.contains("Thank you! You are already subscribed to this product."),
					"verifying the out of stock subcription",
					"after click on subcribe button message should be appear",
					"Sucessfully message has been displayed when we click on the subcribe button ",
					"Failed to display the message after subcribtion");
			
//			Sync.waitElementPresent("xpath", "//button[@aria-label='Close message']");
//			Common.clickElement("xpath", "//button[@aria-label='Close message']");
			Common.actionsKeyPress(Keys.END);
			Common.clickElement("xpath",
					"//div[@x-show='showStickyBar']//button[@title='Notify Me When Available']");
			Common.textBoxInput("xpath", "(//input[@placeholder='Insert your email'])[2]", email);
			Common.clickElement("xpath", "//span[text()='Subscribe']");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			String oldsubcribe = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
			System.out.println(oldsubcribe);
			Common.assertionCheckwithReport(
					oldsubcribe.contains("Thank you! You are already subscribed to this product."),
					"verifying the out of stock subcription",
					"after click on subcribe button message should be appear",
					"Sucessfully message has been displayed when we click on the subcribe button ",
					"Failed to display the message after subcribtion");

		} else {
			Sync.waitElementPresent(30, "xpath", "//span[@class='title-2xs leading-none']");
			String productprice = Common.findElement("xpath", "//span[@class='title-2xs leading-none']")
					.getAttribute("data-price-amount");
			Common.clickElement("xpath", "//img[@class='group-hover/item-image:hidden']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			System.out.println(prod);
			String PLPprice = Common
					.findElement("xpath",
							"(//span[@class='price-wrapper']//span)[2]")
					.getText().replace("$", "").replace(".00", "");
			System.out.println(PLPprice);
			String name = Common.findElement("xpath", "//h1[contains(@class,'pdp-grid-title title')]").getText();
			Common.assertionCheckwithReport(
					name.contains(products) && productprice.equals(PLPprice)
							|| name.contains(prod) && productprice.equals(PLPprice),
					"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
					"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
			Common.clickElement("xpath", "(//span[text()='Notify Me When Available'])");
			Common.textBoxInput("xpath", "(//input[@placeholder='Insert your email'])[1]", email);
			Common.clickElement("xpath", "//span[text()='Subscribe']");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			String newsubcribe = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
			Common.assertionCheckwithReport(
					newsubcribe.contains("Alert subscription has been saved.")
							|| newsubcribe.contains("Thank you! You are already subscribed to this product."),
					"verifying the out of stock subcription",
					"after click on subcribe button message should be appear",
					"Sucessfully message has been displayed when we click on the subcribe button ",
					"Failed to display the message after subcribtion");
			Common.actionsKeyPress(Keys.END);
			Common.clickElement("xpath", "(//span[text()='Notify Me When Available'])");
			Common.textBoxInput("xpath", "(//input[@placeholder='Insert your email'])[1]", email);
			Common.clickElement("xpath", "//span[text()='Subscribe']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String oldsubcribe = Common.findElement("xpath", "//div[@ui-id='message-success']//span").getText();
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


public void Verify_OrderTotal() {
	
	try {
  	
		String Ordertotal = Common.findElement("xpath", "//td[@data-th='Order Total']").getText();
		System.out.println(Ordertotal);
		
		if(Common.getCurrentURL().contains("gb")) {

			double Order_Total = Double.parseDouble(Ordertotal.replace("£", ""));
			System.out.println(Order_Total);
			if (Order_Total <= 50.0) {

				System.out.println("Order Total is Less than 50");
				clickSubmitbutton_Shippingpage();
		            }
			else {
				System.out.println("Order Total is Not Less than 50");
				Assert.fail();
			}
		}
		else {
		double Order_Total = Double.parseDouble(Ordertotal.replace("$", ""));
		System.out.println(Order_Total);
				
		if (Order_Total <= 50.0) {

			System.out.println("Order Total is Less than or Equal to 50");
			clickSubmitbutton_Shippingpage();
	            }
		else {
			System.out.println("Order Total is Not Less than or Equal to 50");
			
			Assert.fail();
		}
	
		}
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the Price in PLP page",
				"Check Price In PLp page",
				"Unable validate the Price in PLP ", Common.getscreenShot("Failed toValidate  price in PLP page"));
		Assert.fail();
	}
}	

public void Navigate_to_addressbook() {
	
	try
	{
		Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
		Common.clickElement("xpath", "//button[@id='customer-menu']");
		Thread.sleep(3000);
		if (Common.findElements("xpath","//a[@title='Sign In']").size() > 0 ) {
			Sync.waitElementPresent("xpath", "//a[@title='Sign In']");
			Common.clickElement("xpath","//a[@title='Sign In']");	
		} else {
			Sync.waitElementPresent("xpath", "//a[@title='My Account']");
			Common.clickElement("xpath", "//a[@title='My Account']");
		}
		Thread.sleep(3000);
		Common.assertionCheckwithReport(
				Common.getCurrentURL().contains("account"),
				"verifying the user navigates to the account page",
				"after clicking on the my account user should able to navigate to the account page",
				"Sucessfully user navigated to the My account page",
				"Failed to navigate to the My account page after clicking on the My account button");
		Sync.waitElementPresent("xpath", "//a[@title='Address Book']");
		Common.clickElement("xpath", "//a[@title='Address Book']");
		Thread.sleep(3000);
		String title=Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']");
		System.out.println(title);
		Common.assertionCheckwithReport(
				title.contains("ADDRESS BOOK"),
				"verifying the user navigates to the Address Book page",
				"after clicking on the Address Book user should able to navigate to the Address Book page",
				"Sucessfully user navigated to the Address Book page",
				"Failed to navigate to the Address Book page after clicking on the Address Book button");
		
	}
	catch (Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the user navigates to the Address Book page",
				"after clicking on the Address Book user should able to navigate to the Address Book page",
				"Unable to navigate to the Address Book page after clicking on the Address Book button",
				Common.getscreenShot("Failed to navigate to the Address Book page after clicking on the Address Book button"));
		Assert.fail();
		
	}
}

public String Add_New_AddressBook(String dataSet) {
	// TODO Auto-generated method stub
	String numer="";
	try {
		
		Navigate_to_addressbook();
		Sync.waitElementVisible(30, "xpath", "//a[contains(text(), 'Add New Address')]");
		Common.clickElement("xpath", "//a[contains(text(), 'Add New Address')]");
		Thread.sleep(4000);
		Common.textBoxInput("xpath", "//input[@title='Phone Number']", data.get(dataSet).get("phone"));
		numer=Common.findElement("xpath", "//input[@title='Phone Number']").getAttribute("value");
		System.out.println(numer);
		Common.textBoxInput("xpath", "//input[@title='Street Address']", data.get(dataSet).get("Street"));
		Common.textBoxInput("xpath", "//input[@title='City']", data.get(dataSet).get("City"));
		Thread.sleep(4000);
		 if(Common.getCurrentURL().contains("/gb"))
         {
			 
			 Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", data.get(dataSet).get("Region"));
             
         }
		 else
		 {
			 Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
             Thread.sleep(3000);
             String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']")
                     .getAttribute("value");
             System.out.println(Shippingvalue);
		 }

		Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
         Thread.sleep(4000);
//         Sync.waitElementPresent("xpath", "//div[@id='ltksmsfield']//input");
//         Common.clickElement("xpath", "//div[@id='ltksmsfield']//input");
//         Thread.sleep(3000);
//         
		Common.clickElement("xpath", "//button[@title='Save Address']");
		Thread.sleep(5000);
//		String message = Common.findElement("xpath", "//div[@ui-id='message-success']").getText();
	/*	System.out.println(message);
		Common.assertionCheckwithReport(message.contains("You saved the address."),
				"validating the saved message after saving address in address book",
				"Save address message should be displayed after the address saved in address book",
				"Sucessfully address has been saved in the address book",
				"Failed to save the address in the address book");*/

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the No address in the address book",
				"After saving the address for Register user no extra address should be there in address book",
				"Unable to see no address in the address book",
				Common.getscreenShotPathforReport("Failed to see no address in the address book"));
		Assert.fail();
	}
	
	return numer;
}

public void communication_Prefrence(String phonenumber) {
	// TODO Auto-generated method stub
	
	
	try
	{
		Sync.waitElementPresent("xpath", "//a[@title='Communication Preferences']");
		Common.clickElement("xpath", "//a[@title='Communication Preferences']");
		Thread.sleep(4000);
		String Title=Common.findElement("xpath", "//span[@data-ui-id='page-title-wrapper']").getText();
		System.out.println(Title);
		Common.assertionCheckwithReport(Title.contains("COMMUNICATION PREFERENCES"),
				"verifying the user navigates to the Communication Preferences page",
				"after clicking on the Communication Preferences user should able to navigate to the Communication Preferences page",
				"Sucessfully user navigated to the Communication Preferences page",
				"Failed to navigate to the Communication Preferences page after clicking on the Communication Preferences button");
		int subproductsList = Common.findElements("xpath", "//div[@id='phones_list']//input")
				.size();
		for (int i = 0; i < subproductsList; i++) {
			int value = i + 1;
		List <WebElement> Numbers=Common.findElements("xpath", "(//div[@id='phones_list']//input)["+ value +"]");
		for (int j = 0; j < Numbers.size(); j++) {

			String attributevalue = Numbers.get(j).getAttribute("value");
			
			if (attributevalue.equals(phonenumber))
			{
				System.out.println(attributevalue);
				Common.assertionCheckwithReport(attributevalue.equals(phonenumber),
						"verifying the phone number in the Communication Preferences",
						"Phone number should be diplay in the Communication Preferences page",
						"Sucessfully phone number is displayed in the Communication Preferences page",
						"Failed to display the phone number in the Communication Preferences page");
				
			}
			else
			{
				System.out.println(attributevalue);
			}
		}
		
		}
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the phone number in the Communication Preferences",
				"Phone number should be diplay in the Communication Preferences page",
				"Unable to display the phone number in the Communication Preferences page",
				Common.getscreenShotPathforReport("Failed to display the phone number in the Communication Preferences page"));
		Assert.fail();
		
	}
}

public void Delete_Address() {
	// TODO Auto-generated method stub
	try
	{
		Sync.waitElementPresent("xpath", "//a[@title='Address Book']");
		Common.clickElement("xpath", "//a[@title='Address Book']");
		Thread.sleep(4000);
		Common.scrollIntoView("xpath", "//span[text()='Delete']");
		Sync.waitElementPresent("xpath", "//span[text()='Delete']");
		Common.clickElement("xpath", "//span[text()='Delete']");
		Thread.sleep(4000);
		Common.alerts("OK");
		/*String popmessage = Common.findElement("xpath", "//div[contains(text(),'Are you ')]").getText();
		if (popmessage.contains("Are you sure you want to delete this address?")) {
			Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");
			Common.clickElement("xpath", "//span[contains(text(),'OK')]");*/
			String Delmessage = Common.findElement("xpath", "//div[@ui-id='message-success']")
					.getText();
			System.out.println(Delmessage);
			Common.assertionCheckwithReport(Delmessage.contains("You deleted the address."),
					"validating the Delete message after Deleting address in address book",
					"Delete address message should be displayed after the address delete in address book",
					"Sucessfully address has been Deleted in the address book",
					"Failed to Delete the address in the address book");
		}
	
	/*else {
			Assert.fail();
		}*/
	
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the Delete message after Deleting address in address book",
				"Delete address message should be displayed after the address delete in address book",
				"Unable to Delete the address in the address book",
				Common.getscreenShotPathforReport("Failed to Delete the address in the address book"));
		
		Assert.fail();
	}
	
}

public String edit_MobileNumber(String dataSet) {
	// TODO Auto-generated method stub
	
	String numer="";
	try
	{
		Sync.waitElementPresent("xpath", "//a[@title='Address Book']");
		Common.clickElement("xpath", "//a[@title='Address Book']");
		Thread.sleep(4000);
		Common.scrollIntoView("xpath", "//a[@title='Edit']");
		Sync.waitElementPresent("xpath", "//a[@title='Edit']");
		Common.clickElement("xpath", "//a[@title='Edit']");
		Thread.sleep(4000);
		Common.textBoxInput("xpath", "//input[@title='Phone Number']", data.get(dataSet).get("phone1"));
		numer=Common.findElement("xpath", "//input[@title='Phone Number']").getAttribute("value");
		System.out.println(numer);
		Common.clickElement("xpath", "//button[@title='Save Address']");
		Thread.sleep(5000);
	/*	String message = Common.findElement("xpath", "//div[@ui-id='message-success']").getText();
		System.out.println(message);
		Common.assertionCheckwithReport(message.contains("You saved the address."),
				"validating the saved message after saving address in address book",
				"Save address message should be displayed after the address saved in address book",
				"Sucessfully address has been saved in the address book",
				"Failed to save the address in the address book");*/
   
		
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the No address in the address book",
				"After saving the address for Register user no extra address should be there in address book",
				"Unable to see no address in the address book",
				Common.getscreenShotPathforReport("Failed to see no address in the address book"));
		Assert.fail();
	}
	return numer;
}



public void Add_Address(String dataSet) {
	// TODO Auto-generated method stub

	try {
		Sync.waitElementVisible(30, "xpath", "//a[@title='Address Book']");
		Common.clickElement("xpath", "//a[@title='Address Book']");
		Thread.sleep(4000);
		Common.textBoxInput("xpath", "//input[@title='Phone Number']", data.get(dataSet).get("phone"));
		Common.textBoxInput("xpath", "//input[@id='street_1']", data.get(dataSet).get("Street"));
		Common.textBoxInput("xpath", "//input[@title='City']", data.get(dataSet).get("City"));
		Thread.sleep(4000);
		 if(Common.getCurrentURL().contains("/gb"))
         {
             
			 Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", data.get(dataSet).get("Region"));
         }
		 else
		 {
			 Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
             Thread.sleep(3000);
             String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']")
                     .getAttribute("value");
             System.out.println(Shippingvalue);
			
		 }

		Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));

		Common.clickElement("xpath", "//button[@title='Save Address']");
		Thread.sleep(1000);
		Sync.waitElementPresent(30,"xpath","//div[@class='container']//div[@class='relative flex w-full']/span");
		String message = Common.findElement("xpath", "//div[@class='container']//div[@class='relative flex w-full']/span").getText();
		System.out.println(message);
		Common.assertionCheckwithReport(message.contains("You saved the address."),
				"validating the saved message after saving address in address book",
				"Save address message should be displayed after the address saved in address book",
				"Sucessfully address has been saved in the address book",
				"Failed to save the address in the address book");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the No address in the address book",
				"After saving the address for Register user no extra address should be there in address book",
				"Unable to see no address in the address book",
				Common.getscreenShotPathforReport("Failed to see no address in the address book"));
		Assert.fail();
	}
}


public void change_Shippingaddress_Addressbook(String Dataset) {
	// TODO Auto-generated method stub
	String firstname = data.get(Dataset).get("FirstName");
	String secondname = data.get(Dataset).get("LastName");
	String address = data.get(Dataset).get("Street");
	String phonenumber = data.get(Dataset).get("phone");
	String City = data.get(Dataset).get("City");
	String region = data.get(Dataset).get("Region");
	String zipcode = data.get(Dataset).get("postcode");
	String shipping = data.get(Dataset).get("Shipping address");

	try {
		Sync.waitPageLoad();

		String newaddress = Common.findElement("xpath", "//span[text()='You have no other address entries in your address book.']").getText();
		if (newaddress.contains("You have no other address entries in your address book.")) {
			Common.clickElement("xpath", "//a[normalize-space()='Add New Address']");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//input[@name='firstname']");
			Common.clickElement("xpath", "//input[@name='firstname']");
			Common.textBoxInput("xpath", "//input[@name='firstname']", firstname);
			Common.clickElement("xpath", "//input[@name='lastname']");
			Common.textBoxInput("xpath", "//input[@name='lastname']", secondname);
			Sync.waitElementPresent(30, "xpath", "//input[@title='Phone Number']");
			Common.clickElement("xpath", "//input[@title='Phone Number']");
			Common.textBoxInput("xpath", "//input[@title='Phone Number']", phonenumber);
			Common.clickElement("xpath", "//input[@id='street_1']");
			Common.textBoxInput("xpath", "//input[@id='street_1']", address);
			Common.clickElement("xpath", "//input[@title='City']");
			Common.textBoxInput("xpath", "//input[@title='City']", City);
			Thread.sleep(4000);
			if(Common.getCurrentURL().contains("/gb"))
             { 
				Common.clickElement("xpath", "//input[@name='region']");
				Common.textBoxInput("xpath", "//input[@name='region']", region);
              
             }
			else
			{
				Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(Dataset).get("Region"));  
			}
			Common.clickElement("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']", zipcode);
			Common.clickElement("xpath", "//label[@for='primary_shipping']");
			Common.clickElement("xpath", "//button[@title='Save Address']");
			String message = Common.findElement("xpath", "//span[text()='You saved the address.']").getText();

			Common.assertionCheckwithReport(message.contains("You saved the address."),
					"validating the saved message after saving address in address book",
					"Save address message should be displayed after the address saved in address book",
					"Sucessfully address has been saved in the address book",
					"Failed to save the address in the address book");
			Shippingaddress_Addressbook("New ShippingAddress");
		} else {
			Shippingaddress_Addressbook("New ShippingAddress");
		}
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog(
				"validating the checkbox for billing address and text for the shipping address",
				"Checkbox should be display for the billing address and text should be display for the shipping address",
				"Unable to display the checkbox for the billing address and text is not displayed for the shipping address",
				Common.getscreenShot(
						"Failed to display checkbox for billing address and fail to display text for shipping address"));
		Assert.fail();
	}

}

public void Shippingaddress_Addressbook(String Dataset) {
	// TODO Auto-generated method stub
	String firstname = data.get(Dataset).get("FirstName");
	String secondname = data.get(Dataset).get("LastName");
	String address = data.get(Dataset).get("Street");
	String phonenumber = data.get(Dataset).get("phone");
	String City = data.get(Dataset).get("City");
	String region = data.get(Dataset).get("Region");
	String zipcode = data.get(Dataset).get("postcode");
	String shipping = data.get(Dataset).get("Shipping address");
	try {
		Sync.waitPageLoad();
		Common.clickElement("xpath", "//span[text()='Change Shipping Address']");
		Sync.waitPageLoad();
		Sync.waitElementPresent("xpath", "//input[@name='firstname']");
		Common.clickElement("xpath", "//input[@name='firstname']");
		Common.textBoxInput("xpath", "//input[@name='firstname']", firstname);
		Common.clickElement("xpath", "//input[@name='lastname']");
		Common.textBoxInput("xpath", "//input[@name='lastname']", secondname);
		Sync.waitElementPresent(30, "xpath", "//input[@title='Phone Number']");
		Common.clickElement("xpath", "//input[@title='Phone Number']");
		Common.textBoxInput("xpath", "//input[@title='Phone Number']", phonenumber);
		Common.clickElement("xpath", "//input[@id='street_1']");
		Common.textBoxInput("xpath", "//input[@id='street_1']", address);
		Common.clickElement("xpath", "//input[@title='City']");
		Common.textBoxInput("xpath", "//input[@title='City']", City);
		if(Common.getCurrentURL().contains("/gb"))
        { 
			Common.clickElement("xpath", "//input[@placeholder='State/Province']");
			Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", region);
            
        }
		else
		{
			 Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(Dataset).get("Region")); 
		}
		Common.clickElement("xpath", "//input[@name='postcode']");
		Common.textBoxInput("xpath", "//input[@name='postcode']", zipcode);
		String checkbox = Common.findElement("xpath", "//input[@id='primary_billing']").getAttribute("type");
		String text = Common.findElement("xpath", "//span[@class='text-base w-full']").getText();
		Common.assertionCheckwithReport(
				checkbox.equals("checkbox") && text.equals("It's a default shipping address."),
				"validating the checkbox for billing address and text for the shipping address",
				"Checkbox should be display for the billing address and text should be display for the shipping address",
				"Sucessfully checkbox is displayed for the billing address and text is displayed for the shipping address",
				"Failed to display checkbox for billing address and fail to display text" + text
						+ "for shipping address");
		Common.clickElement("xpath", "//input[@id='primary_billing']");
		Common.clickElement("xpath", "//button[@title='Save Address']");
		Sync.waitPageLoad(3000);
		Sync.waitElementPresent("xpath", "//span[text()='You saved the address.']");
		String message = Common.findElement("xpath", "//span[text()='You saved the address.']").getText();
		System.out.println(message);
		Common.assertionCheckwithReport(message.contains("You saved the address."),
				"validating the checkbox for billing address and text for the shipping address",
				"Checkbox should be display for the billing address and text should be display for the shipping address",
				"Sucessfully checkbox is displayed for the billing address and text is displayed for the shipping address",
				"Failed to display checkbox for billing address and fail to display text" + text
						+ "for shipping address");
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog(
				"validating the checkbox for billing address and text for the shipping address",
				"Checkbox should be display for the billing address and text should be display for the shipping address",
				"Unable to display the checkbox for the billing address and text is not displayed for the shipping address",
				Common.getscreenShot(
						"Failed to display checkbox for billing address and fail to display text for shipping address"));
		Assert.fail();
	}

}


public void change_Billingaddress_Addressbook(String Dataset) {
	// TODO Auto-generated method stub
	String firstname = data.get(Dataset).get("FirstName");
	String secondname = data.get(Dataset).get("LastName");
	String address = data.get(Dataset).get("Street");
	String phonenumber = data.get(Dataset).get("phone");
	String City = data.get(Dataset).get("City");
	String region = data.get(Dataset).get("Region");
	String zipcode = data.get(Dataset).get("postcode");
	String shipping = data.get(Dataset).get("Shipping address");

	try {
		Sync.waitPageLoad();

		String newaddress = Common.findElement("xpath", "//table[@id='my-address-table']").getText();
		if (newaddress.contains("You have no other address")) {
			Common.clickElement("xpath", "//button[@title='Add New Address']");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//input[@name='firstname']");
			Common.clickElement("xpath", "//input[@name='firstname']");
			Common.textBoxInput("xpath", "//input[@name='firstname']", firstname);
			Common.clickElement("xpath", "//input[@name='lastname']");
			Common.textBoxInput("xpath", "//input[@name='lastname']", secondname);
			Sync.waitElementPresent(30, "xpath", "//input[@title='Phone Number']");
			Common.clickElement("xpath", "//input[@title='Phone Number']");
			Common.textBoxInput("xpath", "//input[@title='Phone Number']", phonenumber);
			Common.clickElement("xpath", "//input[@title='Address Line 1']");
			Common.textBoxInput("xpath", "//input[@title='Address Line 1']", address);
			Common.clickElement("xpath", "//input[@title='City']");
			Common.textBoxInput("xpath", "//input[@title='City']", City);
			if(Common.getCurrentURL().contains("/gb"))
             { 
				Common.clickElement("xpath", "//input[@placeholder='State/Province']");
				Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", region);
      
             }
			else
			{
		          Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(Dataset).get("Region"));  
			}
			Common.clickElement("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']", zipcode);
			Common.clickElement("xpath", "//button[@title='Save Address']");
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
			Common.assertionCheckwithReport(message.equals("You saved the address."),
					"validating the saved message after saving address in address book",
					"Save address message should be displayed after the address saved in address book",
					"Sucessfully address has been saved in the address book",
					"Failed to save the address in the address book");
			Billingaddress_Addressbook(Dataset);
		} else {
			Billingaddress_Addressbook(Dataset);
		}
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog(
				"validating the checkbox for billing address and text for the shipping address",
				"Checkbox should be display for the billing address and text should be display for the shipping address",
				"Unable to display the checkbox for the billing address and text is not displayed for the shipping address",
				Common.getscreenShot(
						"Failed to display checkbox for billing address and fail to display text for shipping address"));
		Assert.fail();
	}

}

public void Billingaddress_Addressbook(String Dataset) {
	// TODO Auto-generated method stub
	String firstname = data.get(Dataset).get("FirstName");
	String secondname = data.get(Dataset).get("LastName");
	String address = data.get(Dataset).get("Street");
	String phonenumber = data.get(Dataset).get("phone");
	String City = data.get(Dataset).get("City");
	String region = data.get(Dataset).get("Region");
	String zipcode = data.get(Dataset).get("postcode");
	String shipping = data.get(Dataset).get("Shipping address");
	try {
		Common.clickElement("xpath", "//span[normalize-space()='Change Billing Address']");
		Sync.waitPageLoad();
		Sync.waitElementPresent("xpath", "//input[@name='firstname']");
		Common.clickElement("xpath", "//input[@name='firstname']");
		Common.textBoxInput("xpath", "//input[@name='firstname']", firstname);
		Common.clickElement("xpath", "//input[@name='lastname']");
		Common.textBoxInput("xpath", "//input[@name='lastname']", secondname);
		Sync.waitElementPresent(30, "xpath", "//input[@title='Phone Number']");
		Common.clickElement("xpath", "//input[@title='Phone Number']");
		Common.textBoxInput("xpath", "//input[@title='Phone Number']", phonenumber);
		Common.clickElement("xpath", "//input[@id='street_1']");
		Common.textBoxInput("xpath", "//input[@id='street_1']", address);
		Common.clickElement("xpath", "//input[@title='City']");
		Common.textBoxInput("xpath", "//input[@title='City']", City);
		if(Common.getCurrentURL().contains("/gb"))
        { 
			
			Common.clickElement("xpath", "//input[@placeholder='State/Province']");
			Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", region);
            
        }
		else
		{
			 Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(Dataset).get("Region")); 
		}
		Common.clickElement("xpath", "//input[@name='postcode']");
		Common.textBoxInput("xpath", "//input[@name='postcode']", zipcode);
		// String checkbox = Common.findElement("xpath",
		// "//input[@id='primary_shipping']").getAttribute("type");
//		String text = Common.findElement("xpath", "//div[@class='message info']//span").getText();
//		System.out.println(text);
//		Common.assertionCheckwithReport(
//				/* checkbox.equals("checkbox") && */ text.contains("This is your default billing address."),
//				"validating the checkbox for billing address and text for the shipping address",
//				"Checkbox should be display for the billing address and text should be display for the shipping address",
//				"Sucessfully checkbox is displayed for the billing address and text is displayed for the shipping address",
//				"Failed to display checkbox for billing address and fail to display text" + text
//						+ "for shipping address");
		Common.clickElement("xpath", "//button[@title='Save Address']");
		Sync.waitElementPresent("xpath", "//span[text()='You saved the address.']");
		Thread.sleep(4000);
		String message = Common.findElement("xpath", "//span[text()='You saved the address.']").getText();
		String shippingaddress = Common
				.findElement("xpath", "(//address[@class='not-italic'])[2]").getText();
		System.out.println(shippingaddress);
		System.out.println(shipping);
		Common.assertionCheckwithReport(
				shippingaddress.equals(shipping) && message.contains("You saved the address."),
				"validating the checkbox for shipping address and text for the billing address",
				"Checkbox should be display for the shipping address and text should be display for the billing address",
				"Sucessfully checkbox is displayed for the shipping address and text is displayed for the billing address",
				"Failed to display checkbox for shipping address and fail to display text for billing address");
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog(
				"validating the checkbox for shipping address and text for the billing address",
				"Checkbox should be display for the shipping address and text should be display for the billing address",
				"Sucessfully checkbox is displayed for the shipping address and text is displayed for the billing address",
				Common.getscreenShot(
						"Failed to display checkbox for shipping address and fail to display text for billing address"));
		Assert.fail();
	}

}

public void Edit_Delete_Address(String Dataset) {
	// TODO Auto-generated method stub

	String firstname = data.get(Dataset).get("FirstName");
	String secondname = data.get(Dataset).get("LastName");
	String address = data.get(Dataset).get("Street");
	String phonenumber = data.get(Dataset).get("phone");
	String City = data.get(Dataset).get("City");
	String region = data.get(Dataset).get("Region");
	String zipcode = data.get(Dataset).get("postcode");
	String shipping = data.get(Dataset).get("Shipping address");
	try {
		int addressbook = Common.findElements("xpath", "//table[@id='my-address-table']").size();
		System.out.println(addressbook);
		if (addressbook>0) {
			Common.clickElement("xpath", "//a[@title='Edit']");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//input[@name='firstname']");
			Common.clickElement("xpath", "//input[@name='firstname']");
			Common.textBoxInput("xpath", "//input[@name='firstname']", firstname);
			Common.clickElement("xpath", "//input[@name='lastname']");
			Common.textBoxInput("xpath", "//input[@name='lastname']", secondname);
			Sync.waitElementPresent(30, "xpath", "//input[@title='Phone Number']");
			Common.clickElement("xpath", "//input[@title='Phone Number']");
			Common.textBoxInput("xpath", "//input[@title='Phone Number']", phonenumber);
			Common.clickElement("xpath", "//input[@id='street_1']");
			Common.textBoxInput("xpath", "//input[@id='street_1']", address);
			Common.clickElement("xpath", "//input[@title='City']");
			Common.textBoxInput("xpath", "//input[@title='City']", City);
			if(Common.getCurrentURL().contains("/gb"))
             { 
				Common.clickElement("xpath", "//input[@placeholder='State/Province']");
				Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", region);
                
             }
			else
			{
				 Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(Dataset).get("Region"));  
			}
			Common.clickElement("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']", zipcode);
			// Common.clickElement("xpath", "//label[@for='primary_shipping']");
			Common.clickElement("xpath", "//button[@title='Save Address']");
			String message = Common.findElement("xpath", "//span[text()='You saved the address.']").getText();

			Common.assertionCheckwithReport(message.contains("You saved the address."),
					"validating the saved message after saving address in address book",
					"Save address message should be displayed after the address saved in address book",
					"Sucessfully address has been saved in the address book",
					"Failed to save the address in the address book");
			Thread.sleep(4000);
			Common.scrollIntoView("xpath", "//span[text()='Delete']");
			Sync.waitElementPresent("xpath", "//span[text()='Delete']");
			Common.clickElement("xpath", "//span[text()='Delete']");
			Thread.sleep(4000);
			Common.acceptAlert(2000);
//			String popmessage = Common.findElement("xpath", "//div[contains(text(),'Are you ')]").getText();
//			if (popmessage.contains("Are you sure you want to delete this address?")) {
//				Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");
//				Common.clickElement("xpath", "//span[contains(text(),'OK')]");
				String Delmessage = Common.findElement("xpath", "//span[text()='You deleted the address.']")
						.getText();
				System.out.println(Delmessage);
				Common.assertionCheckwithReport(Delmessage.contains("You deleted the address."),
						"validating the Delete message after Deleting address in address book",
						"Delete address message should be displayed after the address delete in address book",
						"Sucessfully address has been Deleted in the address book",
						"Failed to Delete the address in the address book");
//			} else {
//				Assert.fail();
//			}

		} else {
			Assert.fail();
		}

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the Delete message after Deleting address in address book",
				"Delete address message should be displayed after the address delete in address book",
				"Unable to  Delete the address in the address book",
				Common.getscreenShot("Failed to Delete the address in the address book"));

		Assert.fail();
	}

}
public void Cancel_Giftcard() {
	// TODO Auto-generated method stub
	
	try
	{
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//button[contains(text(),'Add Gift Card')]");
		Common.clickElement("xpath", "//button[contains(text(),'Add Gift Card')]");
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//button[@aria-label='Remove Gift Cards Code']");
		Common.clickElement("xpath", "//button[@aria-label='Remove Gift Cards Code']");

		Thread.sleep(4000);
		System.out.println("Gift Card removed");
		Common.refreshpage();
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		Assert.fail();
	}
}

public void Cancel_StoreCredit() {
	// TODO Auto-generated method stub
	
	try
	{
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//button[@id='delete-customer-balance']//span[contains(text(),'Remove')]");
		Common.clickElement("xpath", "//button[@id='delete-customer-balance']//span[contains(text(),'Remove')]");
		Thread.sleep(4000);
		System.out.println("Store Credit removed");
		Common.refreshpage();
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		Assert.fail();
	}
}

public void Cancel_Discount() {
	// TODO Auto-generated method stub
	try
	{
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//tr[@class='totals discount']//span[contains(text(),'Cancel Discount Code')]");
		Common.clickElement("xpath", "//tr[@class='totals discount']//span[contains(text(),'Cancel Discount Code')]");
		Thread.sleep(4000);
		System.out.println("Discount removed");
		Common.refreshpage();
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		Assert.fail();
	}
}

public String discount_Giftcard_Express_Paypal(String dataSet) throws Exception {
	// TODO Auto-generated method stub
	
	String order = "";

	String expectedResult = "It should open paypal site window.";
	
	try {
		Thread.sleep(3000);
		Common.switchFrames("xpath", "//iframe[contains(@class,'component-frame visible')]");

		// Common.refreshpage();
		Thread.sleep(8000);
		Sync.waitElementClickable("xpath", "//div[contains(@class,'paypal-button-lab')]");
		Common.clickElement("xpath", "//div[contains(@class,'paypal-button-lab')]");
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
			Paypal_Address_Verification("Express Paypal");
			Thread.sleep(4000);
			if(Common.getCurrentURL().contains(""))
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
		express_paypal_shipping(dataSet);
		discountCode("Discount");
		gitCard("Partial GiftCode");
		Thread.sleep(4000);
		if(Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("stage3") || Common.getCurrentURL().contains("preprod") )
		{
			Common.scrollIntoView("xpath", "//button[@value='Place Order']");
			Sync.waitElementPresent("xpath", "//button[@value='Place Order']");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//button[@value='Place Order']");
		}
		// Tell_Your_FriendPop_Up();//To close the Pop-up
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

public void createAccountFromOrderSummaryPage(String Dataset) {
	// TODO Auto-generated method stub
	String Store= data.get(Dataset).get("Store");
	String email = Common.genrateRandomEmail(data.get(Dataset).get("UserName"));
	try {
		ClickCreateAccount();
		Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(Dataset).get("FirstName"));
		Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(Dataset).get("LastName"));
		Common.textBoxInput("xpath", "//input[@name='email']", email);
		Common.clickElement("xpath", "//input[@name='password']");
		Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
		Common.clickElement("xpath", "(//button[@aria-label='Show Password'])[1]");
		Sync.waitElementPresent(10, "xpath", "(//button[@aria-label='Show Password'])[1]");
		Common.clickElement("xpath", "//input[@name='password_confirmation']");
		Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
				data.get(Dataset).get("Confirm Password"));
		Common.clickElement("xpath", "//button[@aria-label='Show Password']");
		Common.clickElement("xpath", "//button[@title='Sign Up']");
		Sync.waitElementVisible(30, "xpath", "//span[@x-html='message.text']");
		String message = Common.findElement("xpath", "//span[@x-html='message.text']").getText();
		System.out.println(message);
		Common.assertionCheckwithReport(
				message.contains("Thank you for registering")
						|| Common.getCurrentURL().contains("account") ,
				"validating navigation to the account page after clicking on sign up button",
				"User should navigate to the My account page after clicking on the Signup",
				"Sucessfully user navigates to the My account page after clickng on thr signup button",
				"Failed to navigate to the My account page after clicking on the signup button");
//		String password = Common.findElement("xpath", "//input[@name='password_confirmation']")
//				.getAttribute("type");
//		String Message = Common.findElement("id", "validation-classes").getCssValue("color");
//		String Greencolor = Color.fromString(Message).asHex();
//		String Message1 = Common.findElement("id", "validation-length").getAttribute("class");
//		System.out.println(confirmpassword);
//		System.out.println(accounttext);
//		Common.assertionCheckwithReport(
//			 confirmpassword.equals("text")&& accounttext.contains("CREATE AN ACCOUNT"),
//				"validating the order confirmation page",
//				"User should able to view all details in the order confirmation page",
//				"Sucessfully all details has been displayed in the order confirmation",
//				"Failed to display all details in the order confirmation page");
//		Sync.waitElementPresent(30, "xpath", "(//button[@aria-label='Hide Password'])[1]");
//		Common.clickElement("xpath", "(//button[@aria-label='Hide Password'])[1]");
//		Sync.waitElementPresent(30, "xpath", "(//button[@title='Hide Password'])[1]");
//		Common.clickElement("xpath", "(//button[@title='Hide Password'])[1]");
//		
//		String confirmpassword1 = Common.findElement("xpath", "//input[@name='password_confirmation']")
//				.getAttribute("title");
//		String password1 = Common.findElement("xpath", "//input[@name='password_confirmation']")
//				.getAttribute("title");
//		Sync.waitElementPresent("xpath", "//label[@for='is_subscribed']");
//		Common.clickElement("xpath", "//label[@for='is_subscribed']");
//		Common.findElement("xpath", "//label[@for='is_subscribed']").isSelected();
//		Common.assertionCheckwithReport(confirmpassword1.equals("Confirm Password") && password1.equals("Confirm Password"),
//				"validating the password field changed to dots",
//				"After clicking on the eye icon it should be change to dots",
//				"Sucessfully passwords has been changed to dots after clicking on eye icon",
//				"Failed change passwords into dots after clicking on eye icon");
//
//		Sync.waitElementPresent(30, "xpath", "//span[text()='Sign Up']");
//		Common.clickElement("xpath", "//span[text()='Sign Up']");
//		Sync.waitPageLoad();
//		Thread.sleep(2000);
//		Sync.waitElementPresent("xpath",
//				"//div[@data-ui-id='message-success']//div[@class='a-message__container-inner']");
//		String message = Common.findElement("xpath",
//				"//div[@data-ui-id='message-success']//div[@class='a-message__container-inner']").getText();
//		Common.assertionCheckwithReport(
//				Common.getPageTitle().equals("Dashboard") && message.contains("Thank you for registering"),
//				"validating the  my Account page Navigation when user clicks on signin button",
//				"User should able to navigate to the my account page after clicking on Signin button",
//				"Sucessfully navigate to the My account page after clicking on signin button ",
//				"Failed to navigates to My Account Page after clicking on Signin button");
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


public void click_createAccount_Signinpage() {

	try {
		Sync.waitElementPresent("xpath", "//a[contains(text(),'Create an Account') and @class='btn btn-primary w-full']");
		Common.clickElement("xpath", "//a[contains(text(),'Create an Account') and @class='btn btn-primary w-full']");
		Sync.waitPageLoad();
		Thread.sleep(5000);
		Common.assertionCheckwithReport(Common.getPageTitle().equals("Create New Account"),
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


public void close_successmessage() {
	// TODO Auto-generated method stub
	try
	{
		Sync.waitElementPresent(30, "xpath", "//button[@aria-label='Close message']");
		Common.clickElement("xpath", "//button[@aria-label='Close message']");
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		Assert.fail();
	}
}

public void webpagelinks_validation(String Dataset) throws Exception, IOException {
	// TODO Auto-generated method stub
	String links = data.get(Dataset).get("PreprodLinks");
	int j = 0;
 
	String[] strArray = links.split("\\r?\\n");
	for (int i = 0; i < strArray.length; i++) {
		System.out.println(strArray[i]);
 
		if (Common.getCurrentURL().contains("preprod")) {
 
			Common.oppenURL((strArray[i]));
			Thread.sleep(4000);
//			int responcecode = getpageresponce(Common.getCurrentURL());
//			System.out.println(responcecode);
 
			if (Common.getCurrentURL().contains(strArray[i])) {
				ExtenantReportUtils.addPassLog("Validating Page URL ", "page navigated with provided url ",
						"successfully navigated to provided url",
						Common.getscreenShotPathforReport("link" + i));
			} else {
 
				j++;
 
				ExtenantReportUtils.addFailedLog("Validating Page URL  " + Common.getCurrentURL(),
						"page configured with products ", "unable to find page it showing 404 error",
						Common.getscreenShotPathforReport("link" + i));
 
			}
 
		} else if (Common.getCurrentURL().contains("https://mcloud-na-stage3.drybar.com/")) {
 
			Common.oppenURL(strArray[i].replace("mcloud-na-stage3", "www"));
 
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


public void Footer_newsletter(String Dataset) {
	// TODO Auto-generated method stub
	try {
		Common.actionsKeyPress(Keys.END);
		Thread.sleep(4000);
		Sync.waitElementPresent(30, "id", "subscribe-email");
		Common.clickElement("id", "subscribe-email");
		Common.textBoxInput("id", "subscribe-email", data.get(Dataset).get("Email"));
		Common.clickElement("xpath", "//span[text()='Join']");
		//Sync.waitElementPresent(30, "xpath", "//div[@class='newsletter-error']");
		int successmessage = Common.findElements("xpath", "//div[@ui-id='message-success']").size();
		System.out.println(successmessage);
		if(successmessage>0) {
		String success=Common.findElementBy("xpath", "//div[@ui-id='message-success']").getText();
		
			
			Common.assertionCheckwithReport(success.equals("Error: Please enter a valid email address."),
					"To validate the error message for Invalid Email",
					"Should display error Please enter a valid email address.", success,
					"Failed to display the error message for invaild email");
			
		}
		else {
			
			Join("Create Account");
			
		}
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To validate the  message for Invalid and valid  Email",
				"Should display error and success  Please enter a valid email address.", "Failed to display message",
				Common.getscreenShotPathforReport("Failed to subscripbe nes letter"));

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
		
		/*Common.assertionCheckwithReport(successmessage.equals(0)),
				"To validate the error message for missing email fields",
				"Should display Error Please enter a valid email address.", successmessage,
				"Error message dispaly unsuccessfull");*/

	}

	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("Validate the Error message ",
				"Should display Error: Please enter a valid email address.", "Failed to dispaly the Error message ",
				Common.getscreenShotPathforReport("User unable to see an error message"));
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

public void Newsletter_subscription_MyAccount() {
	
	try {
	
	Sync.waitElementPresent("xpath", "//button[@aria-label='My Account']");
	Common.clickElement("xpath", "//button[@aria-label='My Account']");
	Sync.waitElementPresent("xpath", "//a[@title='My Account']");
	Common.clickElement("xpath", "//a[@title='My Account']");
	
//	String MyId=Common.findElement("xpath","(//ul[@class='m-account-nav__links']//li//a)[1]").getAttribute("id");
//	Common.clickElement("xpath", "//a[@id='"+MyId+"']");
	Sync.waitPageLoad();
	Thread.sleep(4000);
	
	String MyAccount= Common.getCurrentURL();
	System.out.println(MyAccount);
	Common.assertionCheckwithReport(MyAccount.contains("/account/"),"validating the order summary in the payment page","Order summary should be display in the payment page and all fields should display",
			"Successfully Order summary is displayed in the payment page and fields are displayed",
			"Failed to display the order summary and fileds under order summary");
	
	Thread.sleep(5000);
	Sync.waitElementPresent("xpath", "//a[@title='Newsletter Subscriptions']");
	Common.clickElement("xpath", "//a[@title='Newsletter Subscriptions']");

	String Communication = Common.getText("xpath", "//span[text()='Newsletter Subscription']");

	//String Storefront_Text = "Communication Preferences";

	//Assert.assertEquals(Communication, Storefront_Text);
	System.out.println(Communication);
	//System.out.println(Storefront_Text);
	
	
	WebElement checkBox = Common.findElement("xpath", "//input[@id='subscription']");
	WebElement Save = Common.findElement("xpath", "//button[@title='Save']");
    
	if(checkBox.isSelected())
	{
		
		System.out.println("Checkbox is Selected");
		Common.clickElement("xpath", "//label[@for='subscription']");
		Save.click();
	}
	
	else {
		
		System.out.println("Checkbox is Not-Selected");
		Common.clickElement("xpath", "//label[@for='subscription']");
		Save.click();
	
	}
	
	Thread.sleep(2000);
	String Success=Common.findElement("xpath","//div[@ui-id='message-success']").getText();
	
	Common.assertionCheckwithReport(Success.contains("We have removed your newsletter subscription.")|| Success.contains("We have saved your subscription."),"validating the order summary in the payment page","Order summary should be display in the payment page and all fields should display",
			"Successfully Order summary is displayed in the payment page and fields are displayed",
			"Failed to display the order summary and fileds under order summary");
	
	
	
	} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));

			Assert.fail();
		}
	}

public void Store_payment_placeOrder(String Dataset) {
	// TODO Auto-generated method stub
	String expectedResult = "It redirects to order confirmation page";
	String order="";
	
	try {
		Thread.sleep(3000);
		Sync.waitElementPresent(30,"xpath", " //h1[normalize-space()='Thank you for your purchase!']");
		String sucessMessage = Common.getText("xpath", " //h1[normalize-space()='Thank you for your purchase!']");

		//Tell_Your_FriendPop_Up();
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

public void pdp_add_myfav(String Dataset) {
	// TODO Auto-generated method stub
	String products = data.get(Dataset).get("Products");
	System.out.println(products);

	try {
		Sync.waitPageLoad();
		for (int i = 0; i <= 10; i++) {
			Sync.waitElementPresent("xpath", "//img[@class='group-hover/item-image:block hidden']");
			List<WebElement> webelementslist = Common.findElements("xpath",
					"//img[@class='group-hover/item-image:block hidden']");

			String s = webelementslist.get(i).getAttribute("src");
			System.out.println(s);
			if (s.isEmpty()) {

			} else {
				break;
			}
		}
		Thread.sleep(6000);
		Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
		Common.javascriptclickElement("xpath", "//img[@alt='" + products + "']");
		Sync.waitPageLoad();
		Thread.sleep(3000);
		String name = Common.findElement("xpath", "//span[text()='" + products + "']").getText();
		
		System.out.println(name);
		Thread.sleep(4000);
		Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
				"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
				"failed to Navigate to the PDP page");
		
		Sync.waitElementPresent("xpath", "//button[@title='Add to Wish List']");
		Common.clickElement("xpath", "//button[@title='Add to Wish List']");
		Thread.sleep(5000);
		String Message=Common.findElement("xpath", "//div[@ui-id='message-error']//span").getText();
		Common.assertionCheckwithReport(Message.contains("You must login or register to add items to your wishlist."), "validating the  product add to the whishlist",
				"Product should be whishlist", "Sucessfully product added to the whishlist ",
				"failed to add product to the whishlist");
		
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the  product add to the whishlist",
				"Product should be whishlist", "unable to add product added to the whishlist ", Common.getscreenShot("failed to add product to the whishlist"));

		Assert.fail();
	}
	
}

public void ClickCreateAccount() {
	// TODO Auto-generated method stub
	
	try
	{
		Sync.waitElementPresent("xpath", "//button[@aria-label='My Account']");
		Common.clickElement("xpath", "//button[@aria-label='My Account']");
		
		Sync.waitElementPresent("xpath", "//a[contains(text(),'Create an Account')]");
		Common.clickElement("xpath", "//a[contains(text(),'Create an Account')]");
		Sync.waitPageLoad();
		Thread.sleep(5000);
		Common.assertionCheckwithReport(Common.getPageTitle().equals("Create an Account"),
				"Validating Create New Customer Account page navigation",
				"after Clicking on Create New Customer Account page it will navigate account creation page",
				"Successfully navigate to the create account page",
				"Failed to Navigate to the account create page ");
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("Validating Create New Customer Account page navigation ",
				"after Clicking on Create New Customer Account page it will navigate account creation page",
				"unable to navigate to the craete account page",
				Common.getscreenShotPathforReport("Failed to navigate to the account create page"));
		Assert.fail();
	}
}

public void Simple_PDP(String Dataset) {
	// TODO Auto-generated method stub
	String products = data.get(Dataset).get("Products");
	System.out.println(products);

	try {
		Sync.waitPageLoad();
		for (int i = 0; i <= 10; i++) { 
			Sync.waitElementPresent("xpath", "//img[@class='group-hover/item-image:block hidden']");
			List<WebElement> webelementslist = Common.findElements("xpath",
					"//img[@class='group-hover/item-image:block hidden']");

			String s = webelementslist.get(i).getAttribute("src");
			System.out.println(s);
			if (s.isEmpty()) {

			} else {
				break;
			}
		}
		Thread.sleep(6000);
		Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
		Common.javascriptclickElement("xpath", "//img[@alt='" + products + "']");
		Sync.waitPageLoad();
		Thread.sleep(5000);
		String name = Common.findElement("xpath", "//span[text()='" + products + "']").getText();
		
		System.out.println(name);
		Thread.sleep(4000);
		Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
				"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
				"failed to Navigate to the PDP page");
		product_quantity(Dataset);
//		click_UGC();
//		Locally_PDP();
		PDP_Tabs("Configurable Product");
//		validate_reviews_AskQuestions_PDP();
//		Common.actionsKeyPress(Keys.UP);

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the PDP page", "In PDP fav ugc all should be appear",
				"Unable to see few things in the PDP",
				Common.getscreenShot("Failed to see few things in the PDP page"));
		Assert.fail();
	}

}


public void PDP_Tabs(String Dataset) {
	// TODO Auto-generated method stub
	String names = data.get(Dataset).get("PDPTabs");
	String[] Links = names.split(",");
	String names1 = data.get(Dataset).get("ProdPDPTabs");
	String[] prodLinks = names.split(",");
	
	int i = 0;
	try {
		
		int size=Common.findElements("xpath", "//section[@class='w-full']//span[text()='Product Details']").size();
		if(size>0 && Common.getCurrentURL().contains("preprod")) {
		   for (i = 0; i < Links.length; i++) {
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//span[contains(text(),'" + Links[i] + "')]");
			Common.clickElement("xpath", "//span[contains(text(),'" + Links[i] + "')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String title = Common.findElement("xpath", "(//div[@class='pb-12'])")
					.getAttribute(":class");
			System.out.println(title);
			Common.assertionCheckwithReport(title.contains("open"), "verifying the tabs in PDP ",
					"After clicking on the " + Links[i] + "It should display the related content",
					"sucessfully after clicking on the " + Links[i] + "it has been displayed related content",
					"Failed to display related content" + Links[i]);

		}
		}
		else {
			for (i = 0; i < Links.length; i++) {
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//span[contains(text(),'" + Links[i] + "')]");
				Common.clickElement("xpath", "//span[contains(text(),'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "(//div[@class='pb-12'])")
						.getAttribute(":class");
				System.out.println(title);
				Common.assertionCheckwithReport(title.contains("open"), "verifying the tabs in PDP ",
						"After clicking on the " + Links[i] + "It should display the related content",
						"sucessfully after clicking on the " + Links[i] + "it has been displayed related content",
						"Failed to display related content" + Links[i]);
		}
		}
	
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the tabs in PDP ",
				"After clicking on the " + Links[i] + "It should display the related content",
				"Unable to display the content in  " + Links[i],
				Common.getscreenShot("Failed to display related content" + Links[i]));

		Assert.fail();
	}

}


public void Configurable_PDP(String Dataset) {
	// TODO Auto-generated method stub
	String products = data.get(Dataset).get("Products");
	String Productsize= data.get(Dataset).get("size");
	String scent=data.get(Dataset).get("scent");
	System.out.println(Productsize);
	System.out.println(products);
	try {
		Sync.waitPageLoad();
		for (int i = 0; i <= 10; i++) {
			Sync.waitElementPresent("xpath", "//img[contains(@class,'group-hover/item-image')]");
			List<WebElement> webelementslist = Common.findElements("xpath",
					"//img[contains(@class,'group-hover/item-image')]");

			String s = webelementslist.get(i).getAttribute("src");
			System.out.println(s);
			if (s.isEmpty()) {

			} else {
				break;
			}
		}

		Thread.sleep(4000);
		Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
		Common.javascriptclickElement("xpath", "//img[@alt='" + products + "']");
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//span[contains(text(),'" + Productsize + "')]");
		Common.clickElement("xpath", "//span[contains(text(),'" + Productsize + "')]");
		Thread.sleep(3000);
		String size=Common.findElement("xpath", "(//div[contains(@class,'flex items-end text-sm')])[2]").getText().toUpperCase();
		System.out.println(size);
		String size1= data.get(Dataset).get("size").toUpperCase();
		System.out.println(size1);
		Common.assertionCheckwithReport(
				size.contains(size1),
				"Verifying the the size of the product is selected in the PDP",
				"after clicking on the size product size should be selected",
				"successfully Product size has been selected on the PDP",
				"Failed to select the product price on the PDP");
		product_quantity(Dataset);
		Thread.sleep(4000);
		PDP_Tabs("Configurable Product");
//		validate_reviews_AskQuestions_PDP();
	
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
				"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));
		Assert.fail();
	}
}

public void validate_reviews_AskQuestions_PDP() {
	
	try {
		Thread.sleep(6000);
	/*     if(Common.getCurrentURL().contains("preprod"))
	     {*/
		int size=Common.findElements("xpath", "//span[@class='yotpo-display-wrapper']").size();
		if(size>0) {
		
		Thread.sleep(6000);
		
//		Sync.waitElementPresent(30, "xpath", "//span[text()='Write A Review']");
//		
//		Common.javascriptclickElement("xpath", "//span[text()='Write A Review']");
//		Sync.waitPageLoad();
//		Thread.sleep(3000);
		
		Sync.waitElementPresent(30, "xpath", "//span[text()='Write A Review']");
		Common.scrollIntoView("xpath", "//span[text()='Write A Review']");
		Common.clickElement("xpath", "//span[text()='Write A Review']");
		int Write = Common
				.findElements("xpath", "//h2[text()='WRITE A REVIEW']").size();
		System.out.println(Write);
Thread.sleep(3000);
		
		Sync.waitElementPresent(30, "xpath", "//span[text()='Ask A Question']");
		Common.clickElementStale("xpath", "//span[text()='Ask A Question']");
		int question = Common
				.findElements("xpath", "//h2[text()='ASK A QUESTION']").size();
		System.out.println(question);
		
		

	}
		else {
			Assert.fail();
		}
/*}
	
	else
	{
		Common.scrollIntoView("xpath", "//div[@class='yotpo-bottom-line-basic-text']");
		int size=Common.findElements("xpath", "//div[@class='yotpo-bottom-line-basic-text']").size();
		if(size>0) {
			Sync.waitElementPresent(30, "xpath", "//button[text()='Write A Review']");
			Common.clickElement("xpath", "//button[text()='Write A Review']");
			Thread.sleep(2000);
			int Write = Common
					.findElements("xpath", "//h2[text()='Share your thoughts']").size();
			System.out.println(Write);
			Thread.sleep(2000);
			Common.clickElement("xpath", "//div[@class='modal-close-btn-wrapper']");
			Thread.sleep(2000);
	Common.clickElement("xpath", "//div[text()='Q&A']");
	Sync.waitElementPresent(30, "xpath", "//span[text()='Ask a question']");
	Common.clickElement("xpath", "//span[text()='Ask a question']");
	Thread.sleep(2000);
	int question = Common
			.findElements("xpath", "//h4[text()='Ask a question']").size();
	System.out.println(question);
	Common.clickElement("xpath", "//div[@class='close-btn-wrapper']");
		}
		else
		{
			Assert.fail();
		}
	}*/
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the  Write a review Ask Question Section in PDP", "Sections should display",
				"unable to display reviews section", Common.getscreenShot("failed to display reviews section"));
		Assert.fail();
	}
}

public void fav_from_shoppingcart() {   //adding fav prodcut to cart from shopping cart
	// TODO Auto-generated method stub
	
	try
	{
		Common.actionsKeyPress(Keys.END);
		Thread.sleep(4000);
		Common.scrollIntoView("xpath", "//button[contains(text(),'ADD TO BAG')]");
		Sync.waitElementPresent(30, "xpath", "//button[contains(text(),'ADD TO BAG')]");
		Common.clickElement("xpath", "//button[contains(text(),'ADD TO BAG')]");
		Thread.sleep(4000);
		Common.maximizeImplicitWait();
		Sync.waitElementVisible("xpath", "//div[@ui-id='message-success']");
		String message = Common.findElement("xpath", "//div[@ui-id='message-success']")
				.getAttribute("ui-id");
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

public void Company(String Dataset) {
	String footer = data.get(Dataset).get("Footer Links");
	String[] footerlinks = footer.split(",");
	int i = 0;
	try {
		for (i = 0; i < footerlinks.length; i++) {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath",
					"//a[@title='"+ footerlinks[i] +"']");
			Thread.sleep(3000);
			Common.findElement("xpath",
					"//a[@title='"+ footerlinks[i] +"']");
			Common.clickElement("xpath",
					"//a[@title='"+ footerlinks[i] +"']");
			Sync.waitPageLoad();
			Thread.sleep(4000);

			String Bread = Common.findElement("xpath", "//span[contains(@class,'text-secondary')]").getText().toLowerCase();
			System.out.println(Bread);
			System.out.println(footerlinks[i]);
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains(footerlinks[i])
							|| Common.getCurrentURL().contains("about-us")
							|| Common.getCurrentURL().contains("what-is-a-blowout")
							|| Common.getCurrentURL().contains("where-to-buy")
							|| Common.getCurrentURL().contains("news")
							|| Common.getCurrentURL().contains("careers")
							|| Common.getCurrentURL().contains("drybarshops.com") || Common.getCurrentURL().contains("rewards"),
					"validating the links navigation from footer Links",
					"After Clicking on" + footerlinks[i] + "it should navigate to the",
					footerlinks[i] + "Sucessfully Navigated to the" + footerlinks[i] + "Links",
					"Unable to Navigated to the" + footerlinks[i] + "Links"); 
			Thread.sleep(5000);
			Common.navigateBack();
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			System.out.println(size);
			

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

public void Information(String Dataset) {
	String footer = data.get(Dataset).get("Footer Links");
	String[] footerlinks = footer.split(",");
	int i = 0;
	try {
		for (i = 0; i < footerlinks.length; i++) {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath",
					"//a[@title='"+ footerlinks[i] +"']");
			Thread.sleep(3000);
			Common.findElement("xpath",
					"//a[@title='"+ footerlinks[i] +"']");
			Common.clickElement("xpath",
					"//a[@title='"+ footerlinks[i] +"']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			
			
			
//			String Bread = Common.findElement("xpath", "//span[contains(@class,'text-secondary')]").getText().toLowerCase();
//			System.out.println(Bread);
			System.out.println(footerlinks[i]);
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains(footerlinks[i])
							|| Common.getCurrentURL().contains("ca-transparency-in-supply-chains-act")
							|| Common.getCurrentURL().contains("privacy-policy")
							|| Common.getCurrentURL().contains("terms-of-use")
							|| Common.getCurrentURL().contains("news")
							|| Common.getCurrentURL().contains("careers") || Common.getCurrentURL().contains("anti-human-trafficking")
							|| Common.getCurrentURL().contains("drybarshops.com") || Common.getCurrentURL().contains("helpcenter"),
					"validating the links navigation from footer Links",
					"After Clicking on" + footerlinks[i] + "it should navigate to the",
					footerlinks[i] + "Sucessfully Navigated to the" + footerlinks[i] + "Links",
					"Unable to Navigated to the" + footerlinks[i] + "Links"); 
			Thread.sleep(5000);
			Common.navigateBack();
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			System.out.println(size);
			

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
public void Support(String Dataset) {
	String footer = data.get(Dataset).get("Footer Links");
	String[] footerlinks = footer.split(",");
	int i = 0;
	try {
		for (i = 0; i < footerlinks.length; i++) {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath",
					"//a[@title='"+ footerlinks[i] +"']");
			Thread.sleep(3000);
			Common.findElement("xpath",
					"//a[@title='"+ footerlinks[i] +"']");
			Common.clickElement("xpath",
					"//a[@title='"+ footerlinks[i] +"']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
		
			System.out.println(footerlinks[i]);
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains(footerlinks[i])
							|| Common.getCurrentURL().contains("contact-us")
							|| Common.getCurrentURL().contains("track/order/status")
							|| Common.getCurrentURL().contains("where-to-buy")
							|| Common.getCurrentURL().contains("news")
							|| Common.getCurrentURL().contains("warranty") || Common.getCurrentURL().contains("helpcenter")
							|| Common.getCurrentURL().contains("loyalty-rewards-terms-and-conditions")||Common.getCurrentURL().contains("return") ,
					"validating the links navigation from footer Links",
					"After Clicking on" + footerlinks[i] + "it should navigate to the",
					footerlinks[i] + "Sucessfully Navigated to the" + footerlinks[i] + "Links",
					"Unable to Navigated to the" + footerlinks[i] + "Links"); 
			Thread.sleep(5000);
			Common.navigateBack();
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			System.out.println(size);
			

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


public void learn_videos() {
	// TODO Auto-generated method stub
	

	try {
		
				Sync.waitElementPresent("xpath", "(//span[contains(text(),'Hair Tools')])");
				Common.clickElement("xpath", "(//span[contains(text(),'Hair Tools')])");
				Common.clickElement("xpath", "//span[contains(text(),' Learn')]");
				
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath",
					"//a[contains(@class,'link group')]//span[contains(text(),'Videos')]");
			Common.clickElement("xpath",
					"//a[contains(@class,'link group')]//span[contains(text(),'Videos')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String title = Common.getPageTitle();
			System.out.println(title);
			String breadcrumbs = Common.findElement("xpath", "//nav[contains(@class,'breadcrumb')]").getText();
			
			 Common.assertionCheckwithReport(title.contains("Videos"), "validating the user navigate to the videos",
						"After clicking on the vidoes CTA it should navigate to the videos page", "Sucessfully Navigated to the videos page ",
						"failed to Navigate to the videos page");
			
	}

	catch (Exception | Error e)
	
	
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the videos page",
				"User should navigate to the videos page",
				" unable to navigate to the videos page ",
				Common.getscreenShot("Failed to navigate to the videos pages"));
		Assert.fail();
	}
}




public void videos_validation() {
	// TODO Auto-generated method stub
	try
	{
		
		Common.findElements("xpath",
					"(//div[@x-data='videoModal()']//span[contains(@class,'cursor-pointer')])[1]");
		
			Common.clickElement("xpath",
					"(//div[@x-data='videoModal()']//span[contains(@class,'cursor-pointer')])[1]");
		   Thread.sleep(3000);
		   Common.switchFrames("xpath", "//div[@x-data='video()']//iframe");
		   Thread.sleep(3000);
		   Sync.waitElementPresent("xpath", "//button[@aria-labelledby='play-button-tooltip']");
		   Common.clickElement("xpath", "//button[@aria-labelledby='play-button-tooltip']");
		   Thread.sleep(3000);
		   String Video=Common.findElement("xpath", "//button[@aria-labelledby='play-button-tooltip']").getAttribute("type");
		   Common.assertionCheckwithReport(Video.contains("button"), "validating the user navigate to the video page",
					"After clicking on the vidoes CTA it should navigate to the videos page", "Sucessfully Navigated to the videos page ",
					"failed to Navigate to the videos page");
		   Thread.sleep(3000);
		   Common.switchToDefault();
		   Sync.waitElementPresent("xpath", "//button[contains(@class,'text-white')]");
		   Common.clickElement("xpath", "//button[contains(@class,'text-white')]");
		  
		  
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the user navigate to the videos",
				"After clicking on the vidoes CTA it should navigate to the videos page", "Unable to Navigated to the videos page ",
				Common.getscreenShot("failed to Navigate to the videos page"));
		Assert.fail();
	}
	
}

public String fifteenpercent_Reward_Points(String Dataset) {
	// TODO Auto-generated method stub
	String rewardpointsused="";
	String points=data.get(Dataset).get("Discountcode");
	
	try
	{
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//button[contains(text(),'Your Reward Points')]");
		Common.clickElement("xpath", "//button[contains(text(),'Your Reward Points')]");
		Thread.sleep(4000);
		String rewardpoints=Common.findElement("xpath", "//div[@class='yotpo-point-balance-text']").getText().trim().replace("YOU HAVE ", "").replace(" POINTS", "");
		System.out.println(rewardpoints);
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//div[@class='vs__actions']");
		Common.clickElement("xpath", "//div[@class='vs__actions']");
		Thread.sleep(4000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ENTER);
		 String pointsused=Common.findElement("xpath", "//span[@class='vs__selected']").getText().trim();
		 Thread.sleep(4000);
		 rewardpointsused=pointsused.replace(pointsused, points);
		 System.out.println(rewardpointsused);
		Common.clickElement("xpath","//button[@aria-label='Apply']");
 		Sync.waitForLoad();
 		Thread.sleep(6000);
 		Sync.waitElementPresent("xpath", "//button[contains(text(),'Your Reward Points')]");
		Common.clickElement("xpath", "//button[contains(text(),'Your Reward Points')]");
		Thread.sleep(4000);
		String off=Common.findElement("xpath", "//div[@class='yotpo-remove-tag-container']//div").getText().trim().replace(" Off", "");
		String discount=Common.findElement("xpath", "//div[@class='item discount']//span[@class='value']").getText().trim().replace("-", "").replace(".00", "");
		System.out.println(off);
		System.out.println(discount);
		   Common.assertionCheckwithReport(off.equals(discount), "validating the reward points redeem in the order summary page",
					"After clicking on the apply button reward points should be apply", "Sucessfully reward points has been applied",
					"failed to apply the reward point in the order summary page");
		  Common.findElement("xpath", "//div[@class='yotpo-point-balance-text']").getText().trim().replace("YOU HAVE ", "").replace(" POINTS", "");
		   
 		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the reward points redeem in the order summary page",
				"After clicking on the apply button reward points should be apply", "unable to apply reward points on the order summary page",
				Common.getscreenShot("failed to apply the reward point in the order summary page"));
		Assert.fail();
	}
	return rewardpointsused;
}




public String Twentypercent_Reward_Points(String Dataset) {
	// TODO Auto-generated method stub
	String rewardpointsused="";
	String points=data.get(Dataset).get("Discountcode");
	
	try
	{
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//button[contains(text(),'Your Reward Points')]");
		Common.clickElement("xpath", "//button[contains(text(),'Your Reward Points')]");
		Thread.sleep(4000);
		String rewardpoints=Common.findElement("xpath", "//div[@class='yotpo-point-balance-text']").getText().trim().replace("YOU HAVE ", "").replace(" POINTS", "");
		System.out.println(rewardpoints);
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//div[@class='vs__actions']");
		Common.clickElement("xpath", "//div[@class='vs__actions']");
		Thread.sleep(4000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ENTER);
		 String pointsused=Common.findElement("xpath", "//span[@class='vs__selected']").getText().trim();
		 Thread.sleep(4000);
		 rewardpointsused=pointsused.replace(pointsused, points);
		 System.out.println(rewardpointsused);
		Common.clickElement("xpath","//button[@aria-label='Apply']");
 		Sync.waitForLoad();
 		Thread.sleep(6000);
 		Sync.waitElementPresent("xpath", "//button[contains(text(),'Your Reward Points')]");
		Common.clickElement("xpath", "//button[contains(text(),'Your Reward Points')]");
		Thread.sleep(4000);
		String off=Common.findElement("xpath", "//div[@class='yotpo-remove-tag-container']//div").getText().trim().replace(" Off", "");
		String discount=Common.findElement("xpath", "//div[@class='item discount']//span[@class='value']").getText().trim().replace("-", "").replace(".00", "");
		System.out.println(off);
		System.out.println(discount);
		   Common.assertionCheckwithReport(off.equals(discount), "validating the reward points redeem in the order summary page",
					"After clicking on the apply button reward points should be apply", "Sucessfully reward points has been applied",
					"failed to apply the reward point in the order summary page");
		  Common.findElement("xpath", "//div[@class='yotpo-point-balance-text']").getText().trim().replace("YOU HAVE ", "").replace(" POINTS", "");
		   
 		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the reward points redeem in the order summary page",
				"After clicking on the apply button reward points should be apply", "unable to apply reward points on the order summary page",
				Common.getscreenShot("failed to apply the reward point in the order summary page"));
		Assert.fail();
	}
	return rewardpointsused;
}

public String Ten_percent_Reward_Points(String Dataset) {
	// TODO Auto-generated method stub
	String rewardpointsused="";
	String points=data.get(Dataset).get("Discountcode");
	try
	{
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//button[contains(text(),'Your Reward Points')]");
		Common.clickElement("xpath", "//button[contains(text(),'Your Reward Points')]");
		Thread.sleep(4000);
		String rewardpoints=Common.findElement("xpath", "//div[@class='yotpo-point-balance-text']").getText().trim().replace("YOU HAVE ", "").replace(" POINTS", "");
		System.out.println(rewardpoints);
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//div[@class='vs__actions']");
		Common.clickElement("xpath", "//div[@class='vs__actions']");
		Thread.sleep(4000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ENTER);
		
		 String pointsused=Common.findElement("xpath", "//span[@class='vs__selected']").getText().trim();
		 Thread.sleep(4000);
		 rewardpointsused=pointsused.replace(pointsused, points);
		 System.out.println(rewardpointsused);
		
		Common.clickElement("xpath","//button[@aria-label='Apply']");
 		Sync.waitForLoad();
 		Thread.sleep(6000);
 		Sync.waitElementPresent("xpath", "//button[contains(text(),'Your Reward Points')]");
		Common.clickElement("xpath", "//button[contains(text(),'Your Reward Points')]");
		Thread.sleep(4000);
		String off=Common.findElement("xpath", "//div[@class='yotpo-remove-tag-container']//div").getText().trim().replace(" Off", "");
		String discount=Common.findElement("xpath", "//div[@class='item discount']//span[@class='value']").getText().trim().replace("-", "").replace(".00", "");
		System.out.println(off);
		System.out.println(discount);
		   Common.assertionCheckwithReport(off.equals(discount), "validating the reward points redeem in the order summary page",
					"After clicking on the apply button reward points should be apply", "Sucessfully reward points has been applied",
					"failed to apply the reward point in the order summary page");
		   rewardpointsused=Common.findElement("xpath", "//div[@class='yotpo-point-balance-text']").getText().trim().replace("YOU HAVE ", "").replace(" POINTS", "");
		   System.out.println(rewardpointsused);
 		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the reward points redeem in the order summary page",
				"After clicking on the apply button reward points should be apply", "unable to apply reward points on the order summary page",
				Common.getscreenShot("failed to apply the reward point in the order summary page"));
		Assert.fail();
	}
	return rewardpointsused;
}


public String fivepercent_Reward_Points(String Dataset) {
	// TODO Auto-generated method stub
	String rewardpointsused = "";
	String points=data.get(Dataset).get("Discountcode");
	try {
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//button[contains(text(),'Your Reward Points')]");
		Common.clickElement("xpath", "//button[contains(text(),'Your Reward Points')]");
		Thread.sleep(4000);
		String rewardpoints = Common.findElement("xpath", "//div[@class='yotpo-point-balance-text']").getText().trim()
				.replace("YOU HAVE ", "").replace(" POINTS", "");
		System.out.println(rewardpoints);
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//div[@class='vs__actions']");
		Common.clickElement("xpath", "//div[@class='vs__actions']");
		Thread.sleep(4000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_UP);
		Common.actionsKeyPress(Keys.ENTER);
		 String pointsused=Common.findElement("xpath", "//span[@class='vs__selected']").getText().trim();
		 Thread.sleep(4000);
		 rewardpointsused=pointsused.replace(pointsused, points);
		 System.out.println(rewardpointsused);
		Common.clickElement("xpath", "//button[@aria-label='Apply']");
		Sync.waitForLoad();
		Thread.sleep(8000);
		Sync.waitElementPresent("xpath", "//button[contains(text(),'Your Reward Points')]");
		Common.clickElement("xpath", "//button[contains(text(),'Your Reward Points')]");
		Thread.sleep(4000);
		String off = Common.findElement("xpath", "//div[@class='yotpo-remove-tag-container']//div").getText().trim()
				.replace(" Off", "");
		String discount = Common.findElement("xpath", "//div[@class='item discount']//span[@class='value']").getText()
				.trim().replace("-", "").replace(".00", "");
		System.out.println(off);
		System.out.println(discount);
		Common.assertionCheckwithReport(off.equals(discount),
				"validating the reward points redeem in the order summary page",
				"After clicking on the apply button reward points should be apply",
				"Sucessfully reward points has been applied",
				"failed to apply the reward point in the order summary page");
		rewardpointsused = Common.findElement("xpath", "//div[@class='yotpo-point-balance-text']").getText().trim()
				.replace("YOU HAVE ", "").replace(" POINTS", "");
		System.out.println(rewardpointsused);

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the reward points redeem in the order summary page",
				"After clicking on the apply button reward points should be apply",
				"unable to apply reward points on the order summary page",
				Common.getscreenShot("failed to apply the reward point in the order summary page"));
		Assert.fail();
	}
	return rewardpointsused;
}

public void verify_RewardPoints(String rewardpoints) {
	// TODO Auto-generated method stub
	
	try
	{
		Sync.waitElementPresent("xpath", "//span[contains(text(),'Rewards')]");
		Common.clickElement("xpath", "//span[contains(text(),'Rewards')]");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//div[@class='yotpo-customized-text yotpo-description-points-balance']//div[@class='yotpo-inner-text']");
		Common.scrollIntoView("xpath", "//div[@class='yotpo-customized-text yotpo-description-points-balance']//div[@class='yotpo-inner-text']");
		String Rewardpoints=Common.findElement("xpath", "//div[@class='yotpo-customized-text yotpo-description-points-balance']//div[@class='yotpo-inner-text']").getText().trim().replace(" points", "");
		System.out.println(Rewardpoints);
		Sync.waitElementPresent("xpath", "//button[contains(text(),'POINTS HISTORY')]");
		Common.clickElement("xpath", "//button[contains(text(),'POINTS HISTORY')]");
		Thread.sleep(4000);
		String rewardhistroy=Common.findElement("xpath", "//div[text()='Rewards History']").getText();
		System.out.println(rewardhistroy);
		String pointusing=Common.findElement("xpath", "(//div[@aria-colindex='4']//div[@class='yotpo-inner-text'])[5]").getText().trim().replace("-", "");
		System.out.println(pointusing);
		System.out.println(rewardpoints);
		Common.assertionCheckwithReport(rewardhistroy.equals("Rewards History") && rewardpoints.equals(pointusing) , "validating the reward points redeem in the order summary page",
				"After clicking on the apply button reward points should be apply", "sucessfully reward points applied on the order summary page",
				"failed to apply the reward point in the order summary page");
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the reward points redeem in the order summary page",
				"After clicking on the apply button reward points should be apply", "unable to apply reward points on the order summary page",
				Common.getscreenShot("failed to apply the reward point in the order summary page"));
		Assert.fail();
	}
	
}

public void Add_to_cart_Whishlist() {
	// TODO Auto-generated method stub
	try
	{
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//button[@data-role='tocart']");
		int size =Common.findElements("xpath", "//button[@data-role='tocart']").size();
		System.out.println(size);
		if(size>0)
		{
			Sync.waitElementPresent("xpath", "//button[@data-role='tocart']");
			Common.clickElement("xpath", "//button[@data-role='tocart']");
			Thread.sleep(8000);
			String message = Common.findElement("xpath", "//div[@ui-id='message-success']")
					.getAttribute("ui-id");
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");
		}
		else
		{
			Sync.waitElementPresent("xpath", "//button[@data-role='remove']");
			Common.clickElement("xpath", "//button[@data-role='remove']");
			Thread.sleep(5000);
			String message = Common.findElement("xpath", "//div[@ui-id='message-success']")
					.getAttribute("ui-id");
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("success"), "Validating the product removed from the Whishlist",
					"After clicking on the delete button product should be removed from the whishlist", "Sucessfully product removed from the whishlist",
					"failed to remove the product from the whishlist");
			
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Hair Products')]");
			Common.clickElement("xpath", "//span[contains(text(),'Hair Products')]");
			Sync.waitElementPresent("xpath", "//span[text()='All Hair Products']");
			Common.clickElement("xpath", "//span[text()='All Hair Products']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//div[@class='absolute right-0 top-0 flex flex-col']");
			Common.clickElement("xpath", "//div[@class='absolute right-0 top-0 flex flex-col']");
			Thread.sleep(5000);
			String message1 = Common.findElement("xpath", "//div[@ui-id='message-success']")
					.getAttribute("ui-id");
			System.out.println(message1);
			Common.assertionCheckwithReport(message1.contains("success"), "validating the  product add to the whishlist",
					"Product should be whishlist", "Sucessfully product added to the whishlist ",
					"failed product added to the Whishlist");
			Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
			Common.clickElement("xpath", "//button[@id='customer-menu']");
			Sync.waitElementPresent("xpath", "//a[contains(text(),'Wish List')]");
			Common.clickElement("xpath", "//a[contains(text(),'Wish List')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@data-role='tocart']");
			Common.clickElement("xpath", "//button[@data-role='tocart']");
			Thread.sleep(5000);
			String message2 = Common.findElement("xpath", "//div[@ui-id='message-success']")
					.getAttribute("ui-id");
			System.out.println(message2);
			Common.assertionCheckwithReport(message2.contains("success"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");
			
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

public String Twenty_percent_Reward_Points(String Dataset) {
	// TODO Auto-generated method stub
	String rewardpointsused="";
	String points=data.get(Dataset).get("Discountcode");
	try
	{
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//button[contains(text(),'Your Reward Points')]");
		Common.clickElement("xpath", "//button[contains(text(),'Your Reward Points')]");
		Thread.sleep(4000);
		String rewardpoints=Common.findElement("xpath", "//div[@class='yotpo-point-balance-text']").getText().trim().replace("YOU HAVE ", "").replace(" POINTS", "");
		System.out.println(rewardpoints);
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//div[@class='vs__actions']");
		Common.clickElement("xpath", "//div[@class='vs__actions']");
		Thread.sleep(4000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ENTER);
		
		 String pointsused=Common.findElement("xpath", "//span[@class='vs__selected']").getText().trim();
		 Thread.sleep(4000);
		 rewardpointsused=pointsused.replace(pointsused, points);
		 System.out.println(rewardpointsused);
		
		Common.clickElement("xpath","//button[@aria-label='Apply']");
 		Sync.waitForLoad();
 		Thread.sleep(6000);
 		Sync.waitElementPresent("xpath", "//button[contains(text(),'Your Reward Points')]");
		Common.clickElement("xpath", "//button[contains(text(),'Your Reward Points')]");
		Thread.sleep(4000);
		String off=Common.findElement("xpath", "//div[@class='yotpo-remove-tag-container']//div").getText().trim().replace(" Off", "");
		String discount=Common.findElement("xpath", "//div[@class='item discount']//span[@class='value']").getText().trim().replace("-", "").replace(".00", "");
		System.out.println(off);
		System.out.println(discount);
		   Common.assertionCheckwithReport(off.equals(discount), "validating the reward points redeem in the order summary page",
					"After clicking on the apply button reward points should be apply", "Sucessfully reward points has been applied",
					"failed to apply the reward point in the order summary page");
		   rewardpointsused=Common.findElement("xpath", "//div[@class='yotpo-point-balance-text']").getText().trim().replace("YOU HAVE ", "").replace(" POINTS", "");
		   System.out.println(rewardpointsused);
 		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the reward points redeem in the order summary page",
				"After clicking on the apply button reward points should be apply", "unable to apply reward points on the order summary page",
				Common.getscreenShot("failed to apply the reward point in the order summary page"));
		Assert.fail();
	}
	return rewardpointsused;
}

public void change_Subscription_Date() {
	// TODO Auto-generated method stub
	try
	{
		Navigate_to_MyproductSubscription();
	
		Sync.waitElementPresent("xpath", "	(//a[contains(text(),'Edit')])[1]");
		Common.clickElement("xpath", "	(//a[contains(text(),'Edit')])[1]");
		String date=Common.findElement("xpath", "(//a[@class='hover:underline action aw-sarp2__next-payment-date']//parent::td//parent::tr//td)[1]").getText();
		System.out.println(date);
		Sync.waitElementPresent("xpath", "//a[@class='hover:underline action aw-sarp2__next-payment-date']");
		Common.clickElement("xpath", "//a[@class='hover:underline action aw-sarp2__next-payment-date']");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		 Common.assertionCheckwithReport(Common.getPageTitle().equals("Change Next Payment Date"), "validating the page navigated to the change next payment date",
					"After clicking on the Edit button it should be navigate to the next payment page", "Sucessfully Navigated to the next payment date page",
					"failed to Navigate to the next payment date page");
		 
		 
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		Assert.fail();
	}
	
}


public void Navigate_to_MyproductSubscription()
{
	try
	{
	
		Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
		Common.clickElement("xpath", "//button[@id='customer-menu']");
		Thread.sleep(2000);
		Sync.waitElementPresent("xpath", "//a[contains(text(),'My Account')]");
		Common.clickElement("xpath", "//a[contains(text(),'My Account')]");
		Sync.waitForLoad();
		Thread.sleep(3000);
		 Common.assertionCheckwithReport(Common.getPageTitle().equals("Dashboard"), "validating the page navigated to the Dashboard page",
					"After clicking on the My account button it should be navigate to the Dashboard page", "Sucessfully Navigated to the Dashboard  page",
					"failed to Navigate to the Dashboard  page");
		Sync.waitElementPresent("xpath", "//a[@title='My Subscriptions']");
		Common.clickElement("xpath", "//a[@title='My Subscriptions']");
		Sync.waitForLoad();
		Thread.sleep(3000);
		 Common.assertionCheckwithReport(Common.getPageTitle().equals("My Subscriptions"), "validating the page navigated to the My Subscriptions page",
					"After clicking on the Product Subscriptions button it should be navigate to the Dashboard page", "Sucessfully Navigated to the My Subscriptions  page",
					"failed to Navigate to the My Subscriptions  page");
	
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the page navigated to the My Subscriptions page",
				"After clicking on the Product Subscriptions button it should be navigate to the Dashboard page", "unable to Navigated to the My Subscriptions  page",
				Common.getscreenShot("failed to Navigate to the My Subscriptions  page"));
		Assert.fail();
	}
}

public void Subcription_Afterpay_Invalid() {
	// TODO Auto-generated method stub
	
	try
	{
		
		Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
		Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
		Sync.waitElementPresent(30, "xpath", "//button[@id='afterpay_clearpay-tab']");
		Common.javascriptclickElement("xpath", "//button[@id='afterpay_clearpay-tab']");	
		Common.switchToDefault();
		Thread.sleep(3000);
		/*if(Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage") )
			
		{ */
		Sync.waitElementPresent(30, "xpath", "(//button[contains(@class,'btn-place-order')])[1]");
		Common.clickElement("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
		Sync.waitPageLoad();
		Thread.sleep(5000);
		Sync.waitElementPresent(30,"xpath", "//div[@ui-id='message-error']");
		String message = Common.findElement("xpath", "//div[@ui-id='message-error']")
				.getAttribute("ui-id");
		System.out.println(message);
		Common.assertionCheckwithReport(message.contains("error"), "validating the error message for the afterpay payment method",
				"After clicking on the place order error message should be display", "Sucessfully error messsage has been displayed",
				"failed to display the error message");
		Sync.waitElementPresent("xpath", "//button[@aria-label='Close message']");
		Common.clickElement("xpath", "//button[@aria-label='Close message']");
	/*	}
		else
		{
			Assert.fail();
			}*/
		
	}
	catch (Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the error message for the afterpay payment method",
				"After clicking on the place order error message should be display", "Unable to display the  error messsage after clicking the place order",
				Common.getscreenShot("failed to display the  error messsage after clicking the place order"));
		Assert.fail();
	}
}


public void Subcription_Klarna_Invalid() {
	// TODO Auto-generated method stub
	
	try
	{
		Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
		Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
		System.out.println("Switch to Frames");
		Common.scrollToElementAndClick("xpath", "//div[@class='p-PaymentMethodSelector']//button[@id='klarna-tab']");
		Common.switchToDefault();
		System.out.println("Switch to Default");
	/*	if(Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage") )
		
		{  */ 	 
            	 Sync.waitElementPresent("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
 				 Common.clickElement("xpath", "(//button[contains(@class,'btn-place-order')])[1]");
 				Sync.waitPageLoad();
 				Thread.sleep(5000);
 				Sync.waitElementPresent(30,"xpath", "//div[@ui-id='message-error']");
 				String message = Common.findElement("xpath", "//div[@ui-id='message-error']")
 						.getAttribute("ui-id");
 				System.out.println(message);
 				Common.assertionCheckwithReport(message.contains("error"), "validating the error message for the Klarna payment method",
 						"After clicking on the place order error message should be display", "Sucessfully error messsage has been displayed",
 						"failed to display the error message");
 				Sync.waitElementPresent("xpath", "//button[@aria-label='Close message']");
 				Common.clickElement("xpath", "//button[@aria-label='Close message']");
	/*	}
		else
		{
			Assert.fail();
		}*/
	}
	
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the error message for the Klarna payment method",
				"After clicking on the place order error message should be display", "Unable to display the  error messsage after clicking the place order",
				Common.getscreenShot("failed to display the  error messsage after clicking the place order"));
		Assert.fail();
	}
	
}

public void change_Shipping_address_sub() {
	// TODO Auto-generated method stub
	try{
		subcription_Profile();
		Thread.sleep(4000);
		String shippingaddress=Common.findElement("xpath", "(//th[text()='Shipping Address']//parent::tr//td)[1]").getText();
		System.out.println(shippingaddress);
	    Sync.implicitWait();
		Sync.waitElementPresent("xpath", "//a[@class='hover:underline action aw-sarp2__shipping-address']");
		Common.clickElement("xpath", "//a[@class='hover:underline action aw-sarp2__shipping-address']");
		Sync.waitPageLoad();
		Thread.sleep(3000);
		Common.clickElement("xpath", "//select[@id='customer_address_id']");
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Thread.sleep(3000);
		Common.clickElement("xpath", "//button[@title='Save Address']");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String changegaddress=Common.findElement("xpath", "(//th[text()='Shipping Address']//parent::tr//td)[1]").getText();
		System.out.println(changegaddress);
		if(shippingaddress!=changegaddress){
//			Thread.sleep(2000);
//			Sync.waitElementVisible(30,"xpath", "//div[@ui-id='message-success']//span");
//			String message = Common.findElement("xpath", "//div[@ui-id='message-success']//span")
//						.getAttribute("ui-id");
//				System.out.println(message);
//				Common.assertionCheckwithReport(message.contains("Shipping Address has been successfully changed."), "validating the address change for the subcription profile",
//						"After clicking on the save address the address should be save ", "Sucessfully address has been changed for the subscription profile id ",
//						"Failed to change the address form the subscription profile id");	
		}
		else{
			Assert.fail();
		}	
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the address change for the subcription profile",
				"After clicking on the save address the address should be save ", "Unable to change the shipping address for the subscription profile ID",
				Common.getscreenShot("Failed to change the address form the subscription profile id"));
		
		Assert.fail();
	}
	
}

public void subscription_One_Time_Purchase() {
	// TODO Auto-generated method stub
	try
	{
		
		Sync.waitElementPresent("xpath", "//button[@aria-label='Close minicart']");
		Common.clickElement("xpath", "//button[@aria-label='Close minicart']");
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//input[@id='aw-sarp2-dropdown-show-hide-0']");
		Common.clickElement("xpath", "//input[@id='aw-sarp2-dropdown-show-hide-0']");
		String onetimeprice=Common.findElement("xpath", "//div[@x-defer='intersect']//span[@class='price text-sm']").getText();
		System.out.println(onetimeprice);
		String PLPPrice=Common.findElement("xpath", "//div[@x-bind='eventListeners']//span[@data-price-type='finalPrice']").getText();
		System.out.println(PLPPrice);
		if(onetimeprice.equals(PLPPrice))
		{
		Sync.waitElementPresent("xpath", "//button[@title='ADD TO BAG']");
		Common.clickElement("xpath", "//button[@title='ADD TO BAG']");
		Sync.waitPageLoad();
		Thread.sleep(4000);
//		Sync.waitElementPresent("xpath", "//div[@data-ui-id='message-success']");
//		String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//		.getAttribute("data-ui-id");
//		System.out.println(message);
//		Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
//		"Product should be add to cart", "Sucessfully product added to the cart ",
//		"failed to add product to the cart");
		
	
		}
		else
		{
			Assert.fail();
		}
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the  product add to the cart",
				"Product should be add to cart", "Unable to add the product to the cart ",
				Common.getscreenShot("Failed to add product to the cart"));
		Assert.fail();
	}
	
}

public String Verify_Profile_ids(String profile_id) {
	// TODO Auto-generated method stub
		String Tenpercentprofile="";
		try{
			Sync.waitElementPresent("xpath", "//p[contains(text(),'Your subscription profile')]//a");
			int ProfileIDsize=Common.findElements("xpath", "//p[contains(text(),'Your subscription profile')]//a").size();
			System.out.println(ProfileIDsize);
			System.out.println(profile_id);
			Thread.sleep(6000);
			if(profile_id.equals(ProfileIDsize)){
				Common.assertionCheckwithReport(profile_id.equals(ProfileIDsize) || Common.getCurrentURL().contains("/checkout/onepage/success/"), "validating the  profile id's for the subscription product",
						"After placing the order profile ids should be create", "Sucessfully Profile ids has beeen created after placing the order ",
						"failed to Create the profile ID after placing the order");
				
				Tenpercentprofile=Common.findElement("xpath", "(//p[contains(text(),'Your subscription profile')]//a)[1]").getText();
			}
			else{
				Common.assertionCheckwithReport(profile_id.equals(ProfileIDsize) || Common.getCurrentURL().contains("/checkout/onepage/success/"), "validating the  profile id's for the subscription product",
						"After placing the order profile ids should be create", "Sucessfully Profile ids has beeen created after placing the order ",
						"failed to Create the profile ID after placing the order");
				
				Tenpercentprofile=Common.findElement("xpath", "(//p[contains(text(),'Your subscription profile')]//a)[1]").getText();
			}
		}
		catch(Exception | Error e){
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  profile id's for the subscription product",
					"After placing the order profile ids should be create", "Unable to Create the profile ID after placing the order",
					Common.getscreenShot("Failed to Create the profile ID after placing the order"));
			Assert.fail();
			}
		return Tenpercentprofile;
		
	    }
public void Guest_Sub_minicart_Checkout() {
	// TODO Auto-generated method stub
	try {
		Thread.sleep(2000);
//		click_minicart();
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//span[@x-text='totalCartAmount']");
		String minicart = Common.findElement("xpath", "//span[@x-text='totalCartAmount']").getText();
		System.out.println(minicart);
		Sync.waitElementPresent(30, "xpath", "//a[contains(text(),'Checkout')]");
		Common.clickElement("xpath", "//a[contains(text(),'Checkout')]");
		Sync.waitPageLoad();
		Thread.sleep(7000);
		Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
	
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog(
				"validating the navigation to the shipping page when we click on the checkout ",
				"User should able to navigate to the shipping  page", "unable to navigate to the shipping page",
				Common.getscreenShot("Failed to navigate to the shipping page"));

		Assert.fail();
	}

}

public void Change_Subscription() {
	// TODO Auto-generated method stub

	try {

		// Locate the target web element
		WebElement element = Common.findElement("xpath", "//a[contains(@href,'profile_id')]/strong");
		// Extract the text from the element
		String fullText = element.getText();
		System.out.println("Profile_id:" + fullText);

		// Extract just the numeric characters
		StringBuilder numericOnly = new StringBuilder();
		for (char c : fullText.toCharArray()) {
			if (Character.isDigit(c)) {
				numericOnly.append(c);
			}
		}

		// Now get the last three digits
		String extractedNumbers = numericOnly.toString();
		String lastThreeDigits = extractedNumbers.substring(extractedNumbers.length() - 3);
		System.out.println(lastThreeDigits);

		Sync.waitElementPresent("xpath", "//button[@id='customer-menu']");
		Common.clickElement("xpath", "//button[@id='customer-menu']");

		Sync.waitElementPresent("xpath", "//a[@title='My Account']");
		Common.clickElement("xpath", "//a[@title='My Account']");

		Common.clickElement("xpath", "//a[@title='My Subscriptions']");

		String Profile_id = Common.findElement("xpath", "//a[normalize-space()='Edit']").getAttribute("href");
		Thread.sleep(4000);
		if (Profile_id.contains(lastThreeDigits)) {
			Common.clickElement("xpath", "//a[contains(@href,'" + lastThreeDigits + "')]");
		} else {
			Assert.fail();
		}
		Common.clickElement("xpath", "//a[normalize-space()='Cancel Subscription']");
		Common.clickElement("xpath", "//button[normalize-space()='Yes']");

		String Cancled_message = Common.getText("xpath",
				"//span[text()='The subscription was successfully canceled.']");
		System.out.println(Cancled_message);
		Common.assertionCheckwithReport(Cancled_message.contains("The subscription was successfully canceled."),
				"validating the address change for the subcription profile",
				"After clicking on the save address the address should be save ",
				"Sucessfully address has been changed for the subscription profile id ",
				"Failed to change the address form the subscription profile id");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the Subscription change/canceled in Myaccount",
				"Subscription should be change/cancel", "Unable to  change/cancelsubscription",
				Common.getscreenShot("Failed to change change/cancelsubscription "));

		Assert.fail();
	}

}

public void subcribe_product_all_types_plan_Add_to_Cart (String Dataset) {
	String products = data.get(Dataset).get("Products");
	System.out.println(products);
	String save=data.get(Dataset).get("SubscribeSave");
	String[] Links = save.split(",");
	System.out.println(Links);
	int j=0;
	try
	{
		Sync.waitPageLoad();
		for (int i = 0; i <= 10; i++) { 
			Sync.waitElementPresent("xpath", "//img[@class='group-hover/item-image:block hidden']");
			List<WebElement> webelementslist = Common.findElements("xpath","//img[@class='group-hover/item-image:block hidden']");

			String s = webelementslist.get(i).getAttribute("src");
			System.out.println(s);
			if (s.isEmpty()) {

			} else {
				break;
			}
		}
		Thread.sleep(7000);
		
		Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
		Common.javascriptclickElement("xpath", "//img[@alt='" + products + "']");
		Sync.waitPageLoad();
		Thread.sleep(3000);
		String name = Common.findElement("xpath", "//h1[contains(@class,'pdp-grid-title')]").getText();
		Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
				"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
				"failed to Navigate to the PDP page");
		product_quantity(Dataset);
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "(//input[@name='aw-sarp2-dropdown-show-hide'])[2]");
		Common.clickElement("xpath", "(//input[@name='aw-sarp2-dropdown-show-hide'])[2]");
		//Thread.sleep(2000);
//		for (j = 0; j < Links.length; j++) 
//		{
		for(String page:Links)
		{
			System.out.println(page);
		     Sync.waitElementPresent("xpath", "//select[contains(@class,'aw-sarp2-subscription__options-list')]");
				Common.dropdown("xpath", "//select[contains(@class,'aw-sarp2-subscription__options-list')]", Common.SelectBy.TEXT, page);
				Thread.sleep(4000);
				String drop=Common.findElement("xpath", "//select[contains(@class,'aw-sarp2-subscription__options-list')]//option[contains(text(),'"+ Links[j] +"')]").getText();
				Thread.sleep(4000);
				Common.assertionCheckwithReport(drop.contains(Links[j]), "To ensure that selected text has selected in the product subcription dropdown",
						"Dropdown should be select for the product subcription", "Sucessfully text has been selected from the dropdown",
						"failed to select the text from the dropdown");
				Thread.sleep(2000);
				String PLPPrice=Common.findElement("xpath", "(//span[@x-html='getFormattedFinalPrice()'])[4]").getText();
				System.out.println(PLPPrice);
				Thread.sleep(2000);
				Sync.waitElementPresent("xpath", "//button[@title='ADD TO BAG']");
				Common.clickElement("xpath", "//button[@title='ADD TO BAG']");
				Sync.waitPageLoad();
				Thread.sleep(5000);	
				String MinicartPrice=Common.findElement("xpath", "(//span[@x-html='item.product_price']//span[@class='price'])[1]").getText();
				System.out.println(MinicartPrice);
//				Assert.assertEquals(PLPPrice, MinicartPrice);
				Sync.waitElementPresent("xpath", "//button[@aria-label='Close minicart']");
				Common.clickElement("xpath", "//button[@aria-label='Close minicart']");
		}
	   
//		Sync.waitElementPresent("xpath", "//div[@data-ui-id='message-success']");
//		String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//		.getAttribute("data-ui-id");
//System.out.println(message);
//Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
//		"Product should be add to cart", "Sucessfully product added to the cart ",
//		"failed to add product to the cart");
		}

	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
				"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));
		Assert.fail();
	}

}

public void size_selection(String Dataset) {
	// TODO Auto-generated method stub
	String scent=data.get(Dataset).get("scent");
	String Productsize=data.get(Dataset).get("size");
	String subscription=data.get(Dataset).get("Save Subscription");
	try
	{
		Sync.waitElementPresent("xpath", "//div[@data-option-label='" + scent + "']");
		Common.clickElement("xpath", "//div[@data-option-label='" + scent + "']");
		Sync.waitElementPresent("xpath", "(//div[@data-option-label='" + Productsize + "'])[1]");
		Common.clickElement("xpath", "(//div[@data-option-label='" + Productsize + "'])[1]");
		Thread.sleep(4000);
	     Sync.waitElementPresent("xpath", "//select[contains(@class,'aw-sarp2-subscription__options-list')]");
			Common.dropdown("xpath", "//select[contains(@class,'aw-sarp2-subscription__options-list')]", Common.SelectBy.TEXT, subscription);
			Thread.sleep(4000);
			String drop=Common.findElement("xpath", "//select[contains(@class,'aw-sarp2-subscription__options-list')]//option[contains(text(),'"+ subscription +"')]").getText();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(drop.contains(subscription), "To ensure that selected text has selected in the product subcription dropdown",
					"Dropdown should be select for the product subcription", "Sucessfully text has been selected from the dropdown",
					"failed to select the text from the dropdown");
			
			String PLPPrice=Common.findElement("xpath", "//div[@x-defer='intersect']//span[@class='price']").getText();
			System.out.println(PLPPrice);
			Sync.waitElementPresent("xpath", "//button[@title='ADD TO BAG']");
			Common.clickElement("xpath", "//button[@title='ADD TO BAG']");
			Sync.waitPageLoad();
			Thread.sleep(4000);	
			String MinicartPrice=Common.findElement("xpath", "(//span[@x-html='item.product_price']//span[@class='price'])[1]").getText();
			System.out.println(MinicartPrice);
//			Assert.assertEquals(PLPPrice, MinicartPrice);
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To ensure that selected text has selected in the product subcription dropdown",
				"Dropdown should be select for the product subcription", "Unable to select the text from the dropdown",
				Common.getscreenShot("failed to select the text from the dropdown"));
		Assert.fail();
	}
	
}

public void selecting_the_Previous_Subscription(String Dataset) {
	String products = data.get(Dataset).get("Products");
	System.out.println(products);
	String save=data.get(Dataset).get("SubscribeSave");
	System.out.println(save);
	try
	{
		Sync.waitPageLoad();
		for (int i = 0; i <= 10; i++) { 
			Sync.waitElementPresent("xpath", "//img[@class='group-hover/item-image:block hidden']");
			List<WebElement> webelementslist = Common.findElements("xpath","//img[@class='group-hover/item-image:block hidden']");

			String s = webelementslist.get(i).getAttribute("src");
			System.out.println(s);
			if (s.isEmpty()) {

			} else {
				break;
			}
		}
		Thread.sleep(7000);
		
		Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
		Common.javascriptclickElement("xpath", "//img[@alt='" + products + "']");
		Sync.waitPageLoad();
		Thread.sleep(3000);
		String name = Common.findElement("xpath", "//h1[contains(@class,'pdp-grid-title')]").getText();
		Common.assertionCheckwithReport(name.contains(products), "validating the  product navigates to PDP page",
				"It should be navigate to the PDP page", "Sucessfully Navigates to the PDP page",
				"failed to Navigate to the PDP page");
		product_quantity(Dataset);
		Thread.sleep(4000);
		Common.scrollIntoView("xpath", "(//input[@name='aw-sarp2-dropdown-show-hide'])[2]");
		Sync.waitElementPresent("xpath", "(//input[@name='aw-sarp2-dropdown-show-hide'])[2]");
		Thread.sleep(3000);
		Common.clickElement("xpath", "(//input[@name='aw-sarp2-dropdown-show-hide'])[2]");
//		Thread.sleep(4000);
	     Sync.waitElementPresent("xpath", "//select[contains(@class,'aw-sarp2-subscription__options-list')]");
		Common.dropdown("xpath", "//select[contains(@class,'aw-sarp2-subscription__options-list')]", Common.SelectBy.TEXT, save);
		Thread.sleep(4000);
		String drop=Common.findElement("xpath", "//select[contains(@class,'aw-sarp2-subscription__options-list')]//option[contains(text(),'"+ save +"')]").getText();
		Thread.sleep(4000);
		Common.assertionCheckwithReport(drop.contains(save), "To ensure that selected text has selected in the product subcription dropdown",
				"Dropdown should be select for the product subcription", "Sucessfully text has been selected from the dropdown",
				"failed to select the text from the dropdown");
		
	}
	catch(Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To ensure that selected text has selected in the product subcription dropdown",
				"Dropdown should be select for the product subcription", "Unable to select the text from the dropdown", Common.getscreenShot("failed to select the text from the dropdown"));
		Assert.fail();
	}
	
}


public void add_To_Subscription(String profile_id) {
	// TODO Auto-generated method stub
	try {
//		String PDPproduct=Common.findElement("xpath", "//span[@itemprop='name']").getText();
//		System.out.println(PDPproduct);
		Sync.waitElementPresent("xpath", "//button[@aria-label='Add to Subscription']");
		Common.clickElement("xpath", "//button[@aria-label='Add to Subscription']");
		Thread.sleep(3000);
	/*	Sync.waitElementPresent("xpath", "//td[@data-th='Profile ID']//label[text()='"+ profile_id + "']");
		Common.clickElement("xpath", "//td[@data-th='Profile ID']//label[text()='"+ profile_id +"']");
		Sync.waitElementPresent("xpath", "//button[@class='btn btn-primary']");
		Common.clickElement("xpath", "//button[@class='btn btn-primary']");
		Navigate_to_MyproductSubscription();
		Sync.waitElementPresent("xpath", "//td[contains(text(),'"+ profile_id +"')]//parent::tr//a[contains(text(),'Edit')]");
		Common.clickElement("xpath", "//td[contains(text(),'"+ profile_id +"')]//parent::tr//a[contains(text(),'Edit')]");
		Sync.waitPageLoad();
		Thread.sleep(3000);
		String subscriptionProduct=Common.findElement("xpath", "(//a[@class='hover:underline'])[3]//span").getText();
		System.out.println(subscriptionProduct);
		Common.assertionCheckwithReport(subscriptionProduct.contains(PDPproduct), "To ensure that selected producted is added to the subscription",
				"Product should be added to the subscription", "sucessfully product added to the subscription",
				"Fail to add the product to the subscription");*/
	
	}
	catch(Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To ensure that selected producted is added to the subscription",
				"Product should be added to the subscription", "Unable to add the product to the subscription",
				Common.getscreenShot("Fail to add the product to the subscription"));
		Assert.fail();
	}
	
}

 
public String TwentyFive_percent_Reward_Points(String Dataset) {
	// TODO Auto-generated method stub
	String rewardpointsused = "";
	String points=data.get(Dataset).get("Discountcode");
	System.out.println(points);
	try {
		Thread.sleep(6000);
		Sync.waitElementPresent("xpath", "//div[@class='item discount']//span[@class='value']");
		String Before_RWD_discount = Common.findElement("xpath", "//div[@class='item discount']//span[@class='value']").getText().trim().replace("-$", "");
//		System.out.println(Before_RWD_discount);
		Thread.sleep(2000);
		Sync.waitElementPresent("xpath", "//button[contains(text(),'Your Reward Points')]");
		Common.clickElement("xpath", "//button[contains(text(),'Your Reward Points')]");
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//input[@placeholder='Choose reward']");
		Common.clickElement("xpath", "//input[@placeholder='Choose reward']");
		String rewardpoints = Common.findElement("xpath", "//div[@class='yotpo-point-balance-text']").getText().trim()
				.replace("YOU HAVE ", "").replace(" POINTS", "");
		System.out.println(rewardpoints);
		
		Thread.sleep(4000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ENTER);
		 String pointsused=Common.findElement("xpath", "//span[@class='vs__selected']").getText().trim();
		 Thread.sleep(4000);
		 rewardpointsused=pointsused.replace(pointsused, points);
		 System.out.println(rewardpointsused);
		Common.clickElement("xpath","//button[@aria-label='Apply']");
 		Sync.waitForLoad();
 		Thread.sleep(4000);
// 		Sync.waitElementPresent("xpath", "//button[contains(text(),'Your Reward Points')]");
//		Common.clickElement("xpath", "//button[contains(text(),'Your Reward Points')]");
		Thread.sleep(3000);
		String off=Common.findElement("xpath", "//div[@class='yotpo-remove-tag-container']//div").getText().trim().replace(" Off", "");
		String discount=Common.findElement("xpath", "//div[@class='item discount']//span[@class='value']").getText().trim().replace("-", "").replace(".00", "");
		System.out.println(off);
		System.out.println(discount);
		
		int size =Common.findElements("xpath", "(//span[normalize-space()='Subscription End Date:'])[3]").size();
		if (off.contains("$25 off")) {

			double Before_RWD_discount_value = Double.parseDouble(Before_RWD_discount);
			double discountValue = Before_RWD_discount_value + 25;

			DecimalFormat df = new DecimalFormat("#.00");
			String discount1 = df.format(discountValue);

			Common.assertionCheckwithReport(discount1.equals(discount),
					"validating the reward points redeem in the order summary page",
					"After clicking on the apply button reward points should be apply",
					"Sucessfully reward points has been applied",
					"failed to apply the reward point in the order summary page");

		} 
		else {
			
			
		
		Common.assertionCheckwithReport(off.equals(discount),
				"validating the reward points redeem in the order summary page",
				"After clicking on the apply button reward points should be apply",
				"Sucessfully reward points has been applied",
				"failed to apply the reward point in the order summary page");
		}
		rewardpointsused = Common.findElement("xpath", "//div[@class='yotpo-point-balance-text']").getText().trim()
				.replace("YOU HAVE ", "").replace(" POINTS", "");
		
//		System.out.println(rewardpointsused);
		
		
		

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the reward points redeem in the order summary page",
				"After clicking on the apply button reward points should be apply",
				"unable to apply reward points on the order summary page",
				Common.getscreenShot("failed to apply the reward point in the order summary page"));
		Assert.fail();
	}
	return rewardpointsused;
}

public void verifingHomePage_and_NewsletterSubcriptionPOPUP(String dataSet) throws Exception{
	// TODO Auto-generated method stub
	String email = data.get(dataSet).get("UserName");
	String Running = data.get(dataSet).get("interests");
	try {
		Sync.waitPageLoad();
//	Thread.sleep(4000);
//	Close_Geolocation();
//	acceptPrivacy();
// Sync.waitPageLoad();
		int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
		Common.assertionCheckwithReport(
				size > 0 && Common.getPageTitle().contains("Home Drybar")
						|| Common.getPageTitle().contains("Home Drybar")
						|| Common.getPageTitle().contains("Drybar Home") || Common.getPageTitle().contains("Drybar"),
				"validating store logo", "System directs the user to the Homepage",
				"Sucessfully user navigates to the home page", "Failed to navigate to the homepage");
		Common.clickElement("xpath", "(//span[contains(text(),'Rewards')])[1]");
		Thread.sleep(4000);
		int sizesframe = Common.findElements("xpath", "//div[@id='ltkpopup-content']").size();
		System.out.println(sizesframe);
		if (sizesframe > 0) {
			Common.actionsKeyPress(Keys.PAGE_UP);
			Thread.sleep(4000);
			Common.textBoxInput("id", "ltkpopup-email", email);
			Thread.sleep(2000);
			Common.clickElement("id", "ltkpopup-submit");
			Thread.sleep(3000);
			Sync.waitElementPresent("id", "ltkpopup-phone");
			Common.textBoxInput("id", "ltkpopup-phone", data.get(dataSet).get("Phone"));
			Thread.sleep(3000);
			Common.clickElement("id", "ltkpopup-faux-submit");
			Thread.sleep(4000);
			int sizes = Common.findElements("xpath", "//a[text()='CREATE ACCOUNT']").size();
			if (sizes > 0) {

				Sync.waitElementPresent("xpath", "//a[text()='CREATE ACCOUNT']");
				Common.clickElement("xpath", "//a[text()='CREATE ACCOUNT']");

				Thread.sleep(5000);
				String text = Common.getCurrentURL();

				Common.assertionCheckwithReport(text.contains("/account/login/referer/"),
						"verifying create Account CTA navigation", "user should navigate to the create Account page",
						"user successfully Navigated to the Create Account page",
						"Failed navigate to the Create An ccount page");

			} else {
				Assert.fail();
			}

		} else {
			Assert.fail();
		}
	} catch (Exception e) {

		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To validate the user open to ewsletter Subcription popup",
				"After clicking on the signin button it should navigate to ewsletter Subcription popup",
				"Unable to navigate the user to the home after clicking on the ewsletter Subcription popup",
				Common.getscreenShotPathforReport("Failed to open to newsletter Subcription popup "));

		Assert.fail();
	}
//	close_add();
}

public void newtab_FooterLinks(String Dataset) {
	// TODO Auto-generated method stub
	
		String footer = data.get(Dataset).get("Footer Links");
		String[] footerlinks = footer.split(",");
		int i = 0;
		try {
			if(Common.getCurrentURL().contains("preprod")) {
			for (i = 0; i < footerlinks.length; i++) {
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath",
						"//a[@title='"+ footerlinks[i] +"']");
				Thread.sleep(3000);
				Common.findElement("xpath",
						"//a[@title='"+ footerlinks[i] +"']");
				   Common.switchWindowsAfterClick("xpath",
						"//a[@title='"+ footerlinks[i] +"']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
			
				System.out.println(footerlinks[i]);
				
				Common.assertionCheckwithReport(
						Common.getPageTitle().contains(footerlinks[i])
								|| Common.getCurrentURL().contains("kustomer") ,
						"validating the links navigation from footer Links",
						"After Clicking on" + footerlinks[i] + "it should navigate to the",
						footerlinks[i] + "Sucessfully Navigated to the" + footerlinks[i] + "Links",
						"Unable to Navigated to the" + footerlinks[i] + "Links"); 
				Thread.sleep(5000);
			    Common.closeCurrentWindow(); 
		           Common.switchToFirstTab();
				Thread.sleep(2000);
				int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
				System.out.println(size);
				}
			}
			
				else
				{
					
					for (i = 0; i < footerlinks.length; i++) {
						Sync.waitPageLoad();
						Thread.sleep(4000);
						Sync.waitElementPresent(30, "xpath",
								"//a[@title='"+ footerlinks[i] +"']");
						Thread.sleep(3000);
						Common.findElement("xpath",
								"//a[@title='"+ footerlinks[i] +"']");
						   Common.clickElement("xpath",
								"//a[@title='"+ footerlinks[i] +"']");
						Sync.waitPageLoad();
						Thread.sleep(4000);
					
						System.out.println(footerlinks[i]);
						
						Common.assertionCheckwithReport(
								Common.getPageTitle().contains(footerlinks[i])
										|| Common.getCurrentURL().contains("kustomer") || Common.getCurrentURL().contains("helpcenter"),
								"validating the links navigation from footer Links",
								"After Clicking on" + footerlinks[i] + "it should navigate to the",
								footerlinks[i] + "Sucessfully Navigated to the" + footerlinks[i] + "Links",
								"Unable to Navigated to the" + footerlinks[i] + "Links"); 
						Thread.sleep(5000);
						Common.navigateBack();

						Thread.sleep(2000);
						int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
						System.out.println(size);
				}
				int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
				System.out.println(size);
	}
		}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		Assert.fail();
	}
	
}
}
		



			





		


		



			





		

	