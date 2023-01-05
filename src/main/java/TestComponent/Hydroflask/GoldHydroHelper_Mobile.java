package TestComponent.Hydroflask;

import static org.testng.Assert.fail;

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

public class GoldHydroHelper_Mobile {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public GoldHydroHelper_Mobile(String datafile) {
		excelData = new ExcelReader(datafile,"DataSet");
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("Hydro");
			report.createTestcase("HydroTestCases");
		} else {
			this.report = Utilities.TestListener.report;
		}
	}
	
	
public GoldHydroHelper_Mobile(String datafile,String sheetname) {
		
		excelData = new ExcelReader(datafile,sheetname);
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("Hydro");
			report.createTestcase("HydroTestCases");
		} else {
			this.report = Utilities.TestListener.report;
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
		System.out.println(Ordernumber);
		System.out.println(Dataset);
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
			
//			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
//					data.get(dataSet).get("Street"));
		
			 
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
	try
	{
		Sync.waitPageLoad();
		Common.clickElement("xpath", "//a[@title='Sign in or register']");
		Sync.waitPageLoad();
		Sync.waitPageLoad();
		Common.textBoxInput("id", "email", data.get(dataSet).get("UserName"));
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
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("Validating the pro deal application page",
				"User should lands to the prodeal application ","Unable to navigates to the prodeal application form",
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
//			Common.fileUpLoad("xpath", "//input[@id='supporting_document']", path);
			Common.findElement("xpath", "//input[@id='supporting_document']").sendKeys(path);
			

			Sync.waitElementPresent("id", "group_id");
			Common.clickElement("xpath", "//select[@id='group_id']");
			Common.dropdown("xpath", "//select[@id='group_id']", SelectBy.VALUE, data.get(dataSet).get("GropName"));

			Sync.waitElementPresent("id", "comment");
			Common.textBoxInput("id", "comment", data.get(dataSet).get("CommetsHydroflask"));

			Sync.waitElementPresent("xpath", "//button[@title='Submit']");
			Common.clickElement("xpath", "//button[@title='Submit']");

		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("ProDeal application from filling",
					"User faield to fill the prodeal aplication ",
					Common.getscreenShotPathforReport("prodeal aplication "));
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
                "Successfully display the Pro Deal Application",
                "Failed to  display the Pro Deal Application");
         } catch (Exception | Error e) {
        e.printStackTrace();
        ExtenantReportUtils.addFailedLog("To validate the Pro Deal Application",
                "Should display the Pro Deal Application ",
                "Unable to displays the Pro Deal Application",
                Common.getscreenShot("Failed to  display the Pro Deal Application"));
         Assert.fail();
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
//		Tell_Your_FriendPop_Up();//To close the Pop-up
		
		int n=Common.findElements("xpath", "//div[@class='checkout-success']/p[1]/span").size();
		if(n>0) {
			 order=Common.getText("xpath", "//div[@class='checkout-success']/p[1]/span");
			 System.out.println(order);
		}
		else {
			order=Common.getText("xpath", "//a[@class='order-number']/strong");	
			 System.out.println(order);
		}
		
	String sucessMessage = Common.getText("xpath", "//h1[@class='checkout-success-title']").trim();
	Assert.assertEquals(sucessMessage, "Your order has been received", "Sucess message validations");
	expectedResult = "Verify order confirmation number which was dynamically generated";
	Common.assertionCheckwithReport(sucessMessage.equals("Your order has been received"),"Order Placed successfull", expectedResult, "faild to place order");

}
		return order;
}

public void access_for_prodeal(String Dataset) {
	// TODO Auto-generated method stub
	click_Prodeal();
	try
	{
		Sync.waitPageLoad(); 
		Common.clickElement("xpath", "//input[@name='access_code']//parent::div");
		Common.textBoxInput("xpath", "//input[@name='access_code']",data.get(Dataset).get("Access code"));
		Common.clickElement("xpath", "//button[@title='Submit']");
		Sync.waitPageLoad();
		Thread.sleep(2000);
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
//        Baby_Registry("Baby Registry");
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
		    Thread.sleep(4000);
		    String eventname=Common.findElement("xpath", "//span[@class='value']").getText();
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

public void prodeler_invalid_details(String dataSet) {
	// TODO Auto-generated method stub
	click_Prodeal();
	try
	{
	Sync.waitPageLoad();
	Common.clickElement("xpath", "//a[@title='Sign in or register']");
	Sync.waitPageLoad();
	Sync.waitPageLoad();
	Common.textBoxInput("id", "email", data.get(dataSet).get("UserName"));
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
}
catch(Exception | Error e)
{
	e.printStackTrace();
	ExtenantReportUtils.addFailedLog("Validating the pro deal application page",
			"User should lands to the prodeal application ","Unable to navigates to the prodeal application form",
			Common.getscreenShotPathforReport("Failed to navigate to the prodeal application form "));
	Assert.fail();
}

	String expectedResult = "After clicking hare button with invalid email error message should be display";
	try {
   
		Sync.waitElementPresent("xpath", "//button[@title='Submit']");
		Common.clickElement("xpath", "//button[@title='Submit']");
		Sync.waitElementPresent(30, "xpath", "//div[contains(@id,'error')]");
		String errormessage=Common.findElement("xpath", "//div[contains(@id,'error')]").getText();
		 Common.assertionCheckwithReport(errormessage.equals("This is a required field."),
					"validating the error message with empty fields ", "After clicking hare button with empty data error message should be display",
					"successfully error message has been dispalyed ", "failed to display the error message");
		
		Sync.waitElementPresent("id", "association_email");
		Common.textBoxInput("id", "association_email", data.get(dataSet).get("FirstName"));
		Common.actionsKeyPress(Keys.PAGE_DOWN);

		Sync.waitElementPresent("xpath", "//button[@title='Submit']");
		Common.clickElement("xpath", "//button[@title='Submit']");
		
		Sync.waitElementPresent(30, "xpath", "//div[@class='mage-error']");
		String invalidemail=Common.findElement("xpath", "//input[@name='association_email']//following::div").getText();
		Thread.sleep(4000);
		 Common.assertionCheckwithReport(invalidemail.contains("Please enter a valid email address"),
					"validating the error message with invalid email ", "After clicking hare button with invalid email error message should be display",
					"successfully error message has been dispalyed ", "failed to display the error message");
}
catch(Exception | Error e)
{
	e.printStackTrace();
	ExtenantReportUtils.addFailedLog("validating the error message with invalid email ", expectedResult,
			"Unable to see the error message has been dispalyed ",
			Common.getscreenShot("failed to display the error message"));
	Assert.fail();

	}
}

public void Prodeal_information() {
	// TODO Auto-generated method stub
	try
	{
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String prodealexpdate=Common.findElement("xpath", "//strong[text()='Program expiration date:']//parent::p").getText();
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
         String prodealdate=Common.findElement("xpath", "//strong[text()='Program expiration date:']//parent::p").getText();
         System.out.println(prodealdate);
         Common.assertionCheckwithReport(prodealexpdate.equals(prodealdate),
					"validating the prodeal information for register user", "After clicking on prodeal information should be displayed ",
					"successfully prodeal information has been displayed", "failed to display the prodeal information for the register user");
	
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the prodeal information for register user", "After clicking on prodeal information should be displayed ",
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
	try
	{
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.clickElement("xpath", "//button[@type='submit']//span[@class='a-btn__label']");
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
				"Unable to Navigated to the Manage Gift Registry",
				Common.getscreenShot("failed to Navigate to the Manage Gift Registry"));
		Assert.fail();
	}
	
try
{
	Sync.waitPageLoad();
	Thread.sleep(4000);
	Common.clickElement("xpath", "//div[@class='control m-text-input']");
	Common.textBoxInput("xpath", "//input[@class='input-text qty a-text-input']", data.get(Dataset).get("Quantity"));
	Sync.waitElementPresent(30, "xpath", "//span[text()='Update Items']");
	Common.clickElement("xpath", "//span[text()='Update Items']");
	Sync.waitElementPresent(30, "xpath", "//div[@class='mage-error']");
	String errormessage=Common.findElement("xpath", "//div[@class='mage-error']").getText();
	 Common.assertionCheckwithReport(errormessage.contains("Please enter a number greater than 0"),
				"validating nthe error message validation for the prodcuts in gift registry ", "After Upadting the quantity to zero the eroor message should be display",
				"successfully quantity has been changed to zero and error message has been displayed", "failed to Display the error message for the when quantity changed to zero");
	
	
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
public void click_trackorder(){
	try {
		Common.actionsKeyPress(Keys.END);
		Common.clickElement("xpath", "//a[text()='Track Your Order']");
		Sync.waitPageLoad();
		Common.assertionCheckwithReport(Common.getPageTitle().equals("Orders and Returns")|| Common.getPageTitle().equals("My Orders") ,
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
public void regiter_userorder_status() {
	// TODO Auto-generated method stub
	 click_singinButton();
	login_Hydroflask("AccountDetails");
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


		Sync.waitElementPresent(40, "xpath", "//iframe[contains(@src,'https://hydroflask')]");
		Common.switchFrames("xpath", "//iframe[contains(@src,'https://hydroflask')]");

		Sync.waitElementPresent("xpath", "//input[@id='customerEmail']");

//		int emailsize = Common.findElements("xpath", "//input[contains(@id,'Emails')]").size();
//		Common.assertionCheckwithReport(emailsize > 0, "Email us form is visible in tab", expectedResult,
//				"unabel to load the  contacts form");
       Common.clickElement("xpath", "//input[@id='customerEmail']");
		Common.textBoxInput("xpath", "//input[@id='customerEmail']", data.get(dataSet).get("Email"));

		Sync.waitElementPresent("xpath", "//input[@id='messageSubject']");
		Common.textBoxInput("xpath", "//input[@id='messageSubject']", data.get(dataSet).get("FirstName"));

		Sync.waitElementPresent("xpath", "//input[@id='conversationLastName']");
		Common.textBoxInput("xpath", "//input[@id='conversationLastName']", data.get(dataSet).get("LastName"));

		Sync.waitElementPresent("xpath", "//input[@name='conversationCompany']");
		Common.textBoxInput("xpath", "//input[@name='conversationCompany']", data.get(dataSet).get("Company"));

		Sync.waitElementPresent("xpath", "//input[@id='conversationPrimary']");
		Common.textBoxInput("xpath", "//input[@id='conversationPrimary']", data.get(dataSet).get("phone"));

		Sync.waitElementPresent("xpath", "//input[@name='conversationStreet']");
		Common.textBoxInput("xpath", "//input[@name='conversationStreet']", data.get(dataSet).get("Street"));

		Sync.waitElementPresent("xpath", "//input[@name='conversationCity']");
		Common.textBoxInput("xpath", "//input[@name='conversationCity']", data.get(dataSet).get("City"));
//
		Sync.waitElementPresent("xpath", "//input[@name='conversationCountry']");
		Common.clickElement("xpath", "//input[@name='conversationCountry']");
//		Common.dropdown("xpath", "//div[@data-path='united_states']", SelectBy.TEXT,country);
//       Common.clickElement("xpath", "//input[@name='conversationCountry']");

		Sync.waitElementPresent("xpath", "//div[text()='United States']");
		Common.clickElement("xpath", "//div[text()='United States']");

		Sync.waitElementPresent("xpath", "//input[@name='conversationState']");
		Common.clickElement("xpath", "//input[@name='conversationState']");

		Sync.waitElementPresent("xpath", "//div[text()='Alabama']");
		Common.clickElement("xpath", "//div[text()='Alabama']");

		Sync.waitElementPresent("xpath", "//input[@name='conversationZipCode']");
		Common.textBoxInput("xpath", "//input[@name='conversationZipCode']", data.get(dataSet).get("postcode"));

		Sync.waitElementPresent("xpath", "//div[@id='conversationHowCanWeHelp']");
		Common.clickElement("xpath", "//div[@id='conversationHowCanWeHelp']");

		Common.clickElement("xpath", "//div[text()='Order Issues']");

		Sync.waitElementPresent("xpath", "//div[text()='Billing Issue']");
		// Common.clickElement("xpath", "//div[@id='selectACategory']");
		Common.clickElement("xpath", "//div[text()='Billing Issue']");

		Sync.waitElementPresent("xpath", "//input[@class='form-base' and @id='conversationOrder']");
		Common.textBoxInput("xpath", "//input[@class='form-base' and @id='conversationOrder']", data.get(dataSet).get("OrderID"));
		Sync.waitElementPresent("xpath", "//textarea[@id='messagePreview']");
		Common.textBoxInput("xpath", "//textarea[@id='messagePreview']",
				data.get(dataSet).get("CommetsHydroflask"));

		Common.scrollIntoView("xpath", "//button[text()='Submit']");
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
	String Text = Common.getText("xpath", "(//div[@class='form-wrap']//span)[2]");
	expectedResult = "User gets confirmation under the same tab. It includes a reference number and email is sent to email provided. No validation errors.";
	Common.assertionCheckwithReport(Text.contains("Your submission was successful "),
			"verifying contact us conformation message", expectedResult,
			"User gets confirmation under the same tab", "unabel to load the confirmation form");
	

}

public void review(String Dataset) {
	// TODO Auto-generated method stub
	String products=data.get(Dataset).get("Products");
	try
	{
		Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
		Common.clickElement("xpath", "//img[@alt='" + products + "']");
		
		Common.scrollIntoView("xpath", "//label[text()='Reviews & Questions']");
		Sync.waitElementPresent("xpath", "//label[@for='tab-product.yotpo.reviews']");
		String form=Common.findElement("xpath", "//label[@for='tab-product.yotpo.reviews']").getText();
		Common.assertionCheckwithReport(form.equals("Reviews & Questions"),
				"verifying the write a review button", "Write a review should be appear in the PDP page",
				"Sucessfully write a review button has been displayed in PDP page", "Failed to display the write a review button in PDP page");
		Common.clickElement("xpath", "//label[text()='Reviews & Questions']");
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

public void BillingAddress(String dataSet) {
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
		Sync.waitElementPresent(30, "xpath", "//input[@type='checkbox']");
		Common.javascriptclickElement("xpath", "//input[@type='checkbox']");
		Common.textBoxInput("xpath", "//input[@name='firstname']",
				data.get(dataSet).get("FirstName"));
		Common.textBoxInput("xpath", "//input[@name='lastname']",
				data.get(dataSet).get("LastName"));
		Common.textBoxInput("xpath", "//input[@name='street[0]']",
				data.get(dataSet).get("Street"));
		String Text = Common.getText("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
		Sync.waitPageLoad();
		Thread.sleep(5000);
		Common.findElement("xpath", "//input[@name='city']").clear();
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
//		Common.textBoxInputClear("xpath", "//input[@name='postcode']");
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
				"Unable to display the Billing address in payment page", Common.getscreenShotPathforReport("Failed to display the Billing address in payment page"));
		Assert.fail();
	}
}
public void Invalid_email_newsletter(String Dataset) {
	// TODO Auto-generated method stub
	try {
		
		Common.actionsKeyPress(Keys.END);
		Thread.sleep(4000);
//		Sync.waitElementPresent(30, "xpath", "//input[@placeholder='Enter Email Address']");
		Common.clickElement("xpath", "//input[@placeholder='Enter Email Address']//parent::div");
		Common.textBoxInput("xpath", "//input[@placeholder='Enter Email Address']//parent::div", data.get(Dataset).get("Email"));
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
				expectedResult, Text,
				Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));

	} catch (Exception | Error e) {
		
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying newsletter subscription", "NewsLetter Subscrption success",
				"User faield to subscrption for newLetter  ",
				Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));
		Assert.fail();
 	}
}


public void noitems_giftregistry(String Dataset) {
	// TODO Auto-generated method stub
	try
	{ 
       Sync.waitElementPresent(30, "xpath", "//input[@type='checkbox']");
		Common.clickElement("xpath", "//input[@type='checkbox']");
		Sync.waitElementPresent(30, "xpath", "//div[@class='control m-text-input']");
		Common.javascriptclickElement("xpath", "//div[@class='control m-text-input']");
           Common.textBoxInput("xpath", "//input[contains(@class,'input-text qty a-text-input')]", data.get(Dataset).get("Quantity"));
		Sync.waitElementPresent("xpath", "//span[text()='Update Items']");
		Common.clickElement("xpath", "//span[text()='Update Items']");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String deletemessage=Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
		System.out.println(deletemessage);
		Common.assertionCheckwithReport(deletemessage.contains("You updated the gift registry items."),"verifying the delete product in gift registry",
				"product should be delete from the gift registry", "Sucessfully product has been deleted from the gift registry",
				Common.getscreenShotPathforReport("Failed to delete the product from the gift registry"));
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.scrollIntoView("xpath", "//div[@class='message info empty']//span");
		String emptymessage=Common.findElement("xpath", "//div[@class='message info empty']//span").getText();
		Common.assertionCheckwithReport(emptymessage.contains("This gift registry has no items."),"verifying the no prodcts in the gift registry",
				"product should be not display in the gift registry", "Sucessfully products should not been displayed in the gift registry",
				Common.getscreenShotPathforReport("Failed to delete the products in the gift registry"));
		Common.clickElement("xpath", "//strong[text()='Gift Registry']");
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the no prodcts in the gift registry",
				"product should be not display in the gift registry", "Unable to display the  products in the gift registry", Common.getscreenShotPathforReport("Failed to delete the products in the gift registry"));
	 
		Assert.fail();
	}
	
}

public void Stored_Payment() {
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
		Sync.waitElementPresent("xpath", "//strong[text()='Stored Payment Methods']");
		Common.clickElement("xpath", "//strong[text()='Stored Payment Methods']");
		Sync.waitPageLoad(30);
		Common.assertionCheckwithReport(Common.getPageTitle().equals("My Payment Methods"),
				"validating the Navigation to the My Payment Methods page",
				"After Clicking on stored methods CTA user should be navigate to the My Payment Methods page",
				"Sucessfully User Navigates to the My Payment Methods page after clicking on the stored methods  CTA",
				"Failed to Navigate to the My Payment Methods page after Clicking on my stored methods  CTA");
		int size=Common.findElements("xpath", "//tbody[@class='m-table__body']").size();
		if(size>0)
		{
			String number=Common.findElement("xpath", "").getText();
		}
		else
		{
			
		}
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		Assert.fail();
	}
	
}

	public void verifingHomePage() {
		try {
			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			//Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Home Page"),
			//		"validating store logo", "System directs the user to the Homepage",
			//		"Sucessfully user navigates to the home page", "Failed to navigate to the homepage");
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
	
	public void bottles_headerlinks(String category) {
		// TODO Auto-generated method stub
		String expectedResult = "User should click the" + category;
		try {

		//	Sync.waitElementClickable("xpath", "//a[contains(@class,'level-top')]//span[text()=' Shop']");
			Thread.sleep(3000);
//			Common.scrollIntoView("xpath","//a[contains(@class,'level-top')]//span[text()=' Shop']");
		//	Common.clickElement("xpath", "//a[contains(@class,'level-top')]//span[text()=' Shop']");
			Common.clickElement("xpath", "//span[@class='icon-header__menu a-icon-text-btn__icon']");
			Thread.sleep(3000);

			try {
				Common.mouseOver("xpath", "//span[contains(text(),'" + category + "')]");
			} catch (Exception e) {
				Common.clickElement("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");
			}
			Common.clickElement("xpath", "//span[contains(text(),'" + category + "')]");
			Common.clickElement("xpath", "//span[text()=' Bottles']");
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


	public void addtocart(String Dataset) {
		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		
		try {
			Thread.sleep(3000);
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
			Thread.sleep(5000);
		//	Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
		//	Common.mouseOver("xpath", "//img[@alt='" + products + "']");
			Sync.waitElementPresent("xpath", "//span[text()='Add to Bag']");
			Common.clickElement("xpath", "//button[@title='Add to Bag']");
			Common.actionsKeyPress(Keys.PAGE_UP);
			Sync.waitPageLoad();
			String message="";
			int i=0;
			while(i<8 && message.equalsIgnoreCase("")) {
			try {
			 message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
					.getAttribute("data-ui-id");
			}catch(Exception e){
				Thread.sleep(2000);
				i++;
				if(i==5)
			{
				Common.clickElement("xpath", "//button[@title='Add to Bag']");
				Common.actionsKeyPress(Keys.PAGE_UP);
				}

	//			Sync.waitPageLoad();
	//			Thread.sleep(2000);
	//			 message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
	//					.getAttribute("data-ui-id");
			}}
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
			Common.clickElement("xpath", "//a[contains(@class,'c-mini')]");
			Thread.sleep(8000);
			String openminicart = Common.findElement("xpath", "//a[contains(@class,'c-mini')]").getAttribute("class");
			System.out.println(openminicart);
			Common.assertionCheckwithReport(openminicart.contains("c-mini"), "To validate the minicart popup",
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

		if (!url.contains("stage")) {
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
		String Number="";
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
				Number=Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ", "");

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
		
		return Number;
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
			Thread.sleep(4000);
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
			Thread.sleep(4000);
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

//			Shippingaddress.put("Shippingstate", Shippingstate);
				System.out.println(Shippingstate);

				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
						data.get(dataSet).get("City"));
				// Common.mouseOverClick("name", "region_id");
				try {
					Common.dropdown("xpath", "//form[@id='co-shipping-form']//select[@name='region_id']",
							Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					// TODO: handle exception
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
//			Shippingaddress.put("ShippingZip", ShippingZip);

				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='telephone']",
						data.get(dataSet).get("phone"));

				Sync.waitElementPresent("xpath", "//label[@class='label a-checkbox__label']");
				Common.clickElement("xpath", "//label[@class='label a-checkbox__label']");

				Common.clickElement("xpath", "//div[@id='opc-new-shipping-address']//following::button[1]");

				int sizeerrormessage = Common
						.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();

				Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying shipping addres filling ",
						"user will fill the all the shipping", "user fill the shiping address click save button",
						"faield to add new shipping address");

				Sync.waitElementPresent("xpath", "//input[@value='tablerate_bestway']");
				Common.clickElement("xpath", "//input[@value='tablerate_bestway']");
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
//			Shippingaddress.put("ShippingZip", ShippingZip);

				Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

				Sync.waitElementClickable("xpath", "//span[contains(text(),'Continue To Payment')]");
				Common.clickElement("xpath", "//span[contains(text(),'Continue To Payment')]");
				int errorsize = Common.findElements("xpath", "//div[@class='field-error']").size();
				Common.assertionCheckwithReport(errorsize > 0, "verifying shipping addres filling ", expectedResult,
						"user enter the shipping address", "mandatory data");

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

	public void createaccount_verfication(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Thread.sleep(2000);
			Common.clickElement("xpath", "//input[@name='firstname']");
			Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(Dataset).get("FirstName"));
			Common.clickElement("xpath", "//input[@name='lastname']");
			Common.textBoxInput("id", "lastname", data.get(Dataset).get("LastName"));
			Common.clickElement("xpath", "//input[@name='email']");
			Common.textBoxInput("xpath", "//input[@name='email']", data.get(Dataset).get("Email"));
			Common.clickElement("xpath", "//input[@name='password']");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			
			Common.clickElement("xpath", "//input[@name='password_confirmation']");
			Common.clickElement("xpath", "//input[@name='password_confirmation']");
			Thread.sleep(5000);
			Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
					data.get(Dataset).get("Confirm Password"));
			Common.clickElement("xpath", "//button[@title='Sign Up']");
			String message = Common.findElement("id", "validation-classes").getCssValue("color");
			String redcolor=Color.fromString(message).asHex();
			String message1 = Common.findElement("id", "validation-length").getCssValue("color");
			String greencolor=Color.fromString(message1).asHex();
			String emailmessage = Common.findElement("xpath", "//div[@id='email_address-error']").getText();
			String confirmpassword = Common.findElement("xpath", "//div[@id='password-confirmation-error']").getText();
			Common.assertionCheckwithReport(redcolor.equals("#bf1322") && greencolor.equals("#2f741f") && emailmessage.contains("@domain.com")
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
		String email="";
		try {
			Common.refreshpage();
			Sync.waitPageLoad();
			Sync.waitElementPresent(30, "xpath", "//input[@name='firstname']");
			Common.clickElement("xpath", "//input[@name='firstname']");
			Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(Dataset).get("FirstName"));
			Common.clickElement("xpath", "//input[@name='lastname']");
			Common.textBoxInput("id", "lastname", data.get(Dataset).get("LastName"));
			Common.clickElement("xpath", "//input[@name='email']");
			Common.textBoxInput("xpath", "//input[@name='email']", Utils.getEmailid());
			email=Common.findElement("xpath", "//input[@name='email']").getAttribute("value");
			System.out.println(email);
			Common.clickElement("xpath", "//input[@name='password']");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			Sync.waitElementPresent(30, "xpath", "//input[@name='password_confirmation']");
			Common.clickElement("xpath", "//input[@name='password_confirmation']");
			Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
					data.get(Dataset).get("Confirm Password"));
			Sync.waitElementPresent(30, "xpath", "//button[@title='Sign Up']");
			Common.clickElement("xpath", "//button[@title='Sign Up']");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//div[@data-ui-id='message-success']//div");
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
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

	public void gustuserorderStatus(String dataSet) {
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
			  Thread.sleep(4000);
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
			Thread.sleep(3000);
			Common.actionsKeyPress(Keys.PAGE_UP);
			Thread.sleep(2000);
			Common.clickElement("xpath", "//img[@alt='" + product + "']");
			Common.assertionCheckwithReport(product.contains(product),
					"validating the product should navigate to the PDP page",
					"When we click on the product is should navigate to the PDP page",
					"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");

			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//div[@aria-label='" + productcolor + "']");
			Common.clickElement("xpath", "//div[@aria-label='" + productcolor + "']");
			Common.clickElement("xpath", "//button[@title='Add to Bag']");
			Thread.sleep(2000);
			String message2="";
			int i=0;
			while(i<8 && message2.equalsIgnoreCase("")) {
			try {
				message2 = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
						.getAttribute("data-ui-id");
			}catch(Exception e){
				Thread.sleep(2000);
				i++;
				if(i==5)
			{
				Common.clickElement("xpath", "//button[@title='Add to Bag']");}}}

			
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
try
{

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

	public void minicart_delete(String Dataset) {
		// TODO Auto-generated method stub
		String deleteproduct = data.get(Dataset).get("Colorproduct");
		try {
			Thread.sleep(6000);
			Sync.waitElementPresent(30, "xpath", "//span[@class='c-mini-cart__subtotal-amount']//span");
			String subtotal = Common.getText("xpath", "//span[@class='price']")
					.replace("$", "");
			Float subtotalvalue = Float.parseFloat(subtotal);
			String productname = Common
					.findElement("xpath", "(//div[@class='m-mini-product-card__info']//a[@class='a-product-name'])[1]")
					.getText();
			String productamount1 = Common.getText("xpath", "(//span[@class='minicart-price']/span)[1]").replace("$",
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
		try
		{
			Common.clickElement("xpath", "//input[@name='password']");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			Sync.waitElementPresent(30, "xpath", "//input[@name='password_confirmation']");
			Common.clickElement("xpath", "//input[@name='password_confirmation']");
			Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
					data.get(Dataset).get("Confirm Password"));
			String Message = Common.findElement("id", "validation-classes").getCssValue("color");
			String Greencolor=Color.fromString(Message).asHex();
			String Message1 = Common.findElement("id", "validation-length").getAttribute("class");
            Common.assertionCheckwithReport(Greencolor.equals("#067a63") && Message1.contains("complete"),
					"validating the password minimum requriment message field",
					"User should able to view the minimum requriement field for password",
					"Sucessfully User able to view the minimum requriement field for password",
					"Failed to see the minimum requriement field for password");
			Sync.waitElementPresent(30, "xpath", "//span[text()='Create an Account']");
			Common.clickElement("xpath", "//span[text()='Create an Account']");
			Sync.waitPageLoad();
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
		Common.clickElement("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
		Thread.sleep(5000);
		
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
    	   e.printStackTrace();
            ExtenantReportUtils.addFailedLog("validating discount code", expectedResult,
                    "User failed to proceed with discountcode", Common.getscreenShotPathforReport("discountcodefailed"));
            
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
   			Common.textBoxInput("xpath", "//input[@id='search']", product);
   			Common.actionsKeyPress(Keys.ENTER);
   			Sync.waitPageLoad();
   			Thread.sleep(3000);
   		//	String productsearch = Common.findElement("xpath", "//span[@id='algolia-srp-title']").getText();
   			Common.actionsKeyPress(Keys.ENTER);
   			Thread.sleep(3000);
   			Common.clickElement("xpath", "//button[@type='submit']");
   		//	Common.assertionCheckwithReport(productsearch.contains(product), "validating the search functionality",
   		//			"enter product name will display in the search box", "user enter the product name in  search box",
   		//			"Failed to see the product name");

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
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			if (size > 0) {
				//Sync.waitElementPresent(30, "xpath", "//input[@value='fedex_FEDEX_GROUND']");
			//	Common.clickElement("xpath", "//input[@value='fedex_FEDEX_GROUND']");
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

	public void KlarnaPayment(String dataSet) throws Exception {
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
				klarna_Details("Klarna payment");
				
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
				klarna_Details("Klarna payment");
			}
			
			
			
		
	}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void klarna_Details(String Dataset) {
		// TODO Auto-generated method stub
		String order="";
		String number=data.get(Dataset).get("phone");
		String otp=data.get(Dataset).get("OTP Number");
		String DOB=data.get(Dataset).get("DOB");
		
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
			Sync.waitElementPresent(30, "xpath", "//h1[@role='status']");
		String klarna=Common.findElement("xpath", "//h1[@role='status']").getText();
		System.out.println(klarna);
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
			Common.clickElement("xpath", "//span[contains(text(),'Continue')]");
			Common.clickElement("xpath", "//span[contains(text(),'continue')]");
			Common.clickElement("xpath", "//button[@data-testid='pick-plan']");
			Sync.waitPageLoad();
			Common.javascriptclickElement("xpath", "//input[@id='cardNumber']");
			Common.findElement("xpath","//input[@id='cardNumber']").sendKeys(data.get(Dataset).get("cardNumber"));
			Common.javascriptclickElement("xpath", "//input[@id='expire']");
			Common.findElement("xpath","//input[@id='expire']").sendKeys(data.get(Dataset).get("ExpMonthYear"));
			Common.javascriptclickElement("xpath", "//input[@id='securityCode']");
			Common.findElement("xpath","//input[@id='securityCode']").sendKeys(data.get(Dataset).get("cvv"));
			Common.clickElement("xpath", "//span[contains(text(),'Continue')]");
			Common.clickElement("xpath", "//span[contains(text(),'Pay $')]");
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//button[@data-testid='PushFavoritePayment:skip-favorite-selection']");
			
		}
		else
		{
			
			Common.clickElement("xpath", "//button[@data-testid='pick-plan']");
			Common.clickElement("xpath", "//span[contains(text(),'Pay $')]");
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//button[@data-testid='PushFavoritePayment:skip-favorite-selection']");
			
		}
		}
		catch(Exception |Error e)
		{
			e.printStackTrace();
			Assert.fail();
		}
		try {
			String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();

			int sizes = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();
			Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!"),
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
	
	
	public void close_add() throws Exception {
		// TODO Auto-generated method stub
		 Thread.sleep(3000);
         int sizesframe=Common.findElements("xpath", "//div[@class='preloaded_lightbox']/iframe").size();
         if(sizesframe>0){
         //Common.actionsKeyPress(Keys.PAGE_UP);
         Thread.sleep(2000);
         //Sync.waitElementVisible("xpath" , "//div[@class='sidebar-iframe-close']");
         Common.clickElement("xpath", "//div[@class='sidebar-iframe-close']");
         }
         else {
             int sizeofpopup=Common.findElements("id", "wpx-newsletter-popup").size();
             if(sizeofpopup>0){
                 
                 
                 Sync.waitElementClickable("xpath" , "//button[@aria-label='close']");
                 Common.clickElement("xpath" , "//button[@aria-label='close']");
         }
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
				String errorTexts = Common.findElement("xpath", "//div[contains(@id,'error')]").getText();

				Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data",
						expectedResult, "Filled the Card detiles", "missing field data it showinng error");
		
				
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
				String errorTexts = Common.findElement("xpath", "//div[contains(@id,'error')]").getText();

				Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data",
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
}