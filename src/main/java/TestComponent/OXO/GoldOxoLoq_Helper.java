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

public class GoldOxoLoq_Helper {

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
	
	
public GoldOxoLoq_Helper(String datafile,String sheetname) {
		
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
	
	
	
	
	
	
	
	
	

	
public void acceptPrivacy() {
		
		Common.clickElementStale("id", "truste-consent-button");
	}






	
	
	
	public void verifingHomePage() {
		try {
			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("OXO"),
					"validating store logo", "System directs to the Homepage", "Sucessfully navigate to home page",
					"faield to naviagte to homepage");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating store logo", "System directs to the Homepage",
					"Unable to navigate Home page",
					Common.getscreenShotPathforReport("failed to get back to homepage"));
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
	
	public void Usersignin(String dataSet)  {

		try
		{
		Sync.waitPageLoad();
		Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
		Common.clickElement("xpath", "//button[contains(@class,'action lo')]");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String login=Common.findElement("xpath", "//div[@class='m-account-nav__welcome']//span[@class='a-icon-text-btn__label']").getText();
		
		
		
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
	public void AddNewAddress_AddressBook(String dataSet) throws Exception {
        String address=data.get(dataSet).get("Street");
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
     
        
        String expectedResult = "email field will have email address";
        try {
        	Common.clickElementStale("xpath", "//span[contains(text(),'Add New Address')]");
            Common.textBoxInput("xpath", "//form[@id='form-validate']//input[@name='firstname']",
                    data.get(dataSet).get("FirstName"));
            int size = Common.findElements("id", "customer-email").size();
            Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,
                    "Filled Email address", "unable to fill the email address");
            Common.textBoxInput("xpath", "//form[@id='form-validate']//input[@name='lastname']",
                    data.get(dataSet).get("LastName"));
            Common.clickElement("xpath","(//input[@name='street[]'])[1]");
            Common.textBoxInput("xpath","(//input[@name='street[]'])[1]",
                    address);
            Common.actionsKeyPress(Keys.BACK_SPACE);
            Common.actionsKeyPress(Keys.BACK_SPACE);
            Thread.sleep(5000);
            Common.findElement("xpath","(//input[@name='street[]'])[1]").sendKeys("Rd");
            Thread.sleep(5000);
            Sync.waitElementPresent(30, "xpath", "//div[@class='pcaitem']");
            String newadd=Common.findElement("xpath", "//div[@class='pcaitem']").getAttribute("title");
            System.out.println(newadd);
            Common.clickElement("xpath","//input[@name='city']");
            Common.clickElement("xpath","(//input[@name='street[]'])[1]");
            Thread.sleep(6000);
            List<WebElement> listofaddresss=Common.findElements("xpath", "(//div[@class='pcaitem'])[1]");
            ArrayList<WebElement> listaddress=new ArrayList<WebElement>();
            for(WebElement addresscheck:listofaddresss)
            {
                
             listaddress.add(addresscheck);
             String add=addresscheck.getAttribute("title");
             System.out.println(add);
             System.out.println(address);
             Thread.sleep(4000);
             Common.assertionCheckwithReport(add.equals(address) ,
                        "validating the dropdown in the shipping address page ",
                        "User should able to see the Locate dropdown for respective address", "Successfully Locate dropdown has been displayed in shipping address page",
                        "Failed to display dropdown in the shipping address page");
             
            }
        
            String Text = Common.getText("xpath", "(//input[@name='street[]'])[1]");
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
            Common.textBoxInputClear("name", "postcode");
            Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
            Thread.sleep(5000);



           Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
           Thread.sleep(2000);
			
			
			Common.clickElement("xpath", "//button[@title='Save Address']");


           Sync.waitPageLoad();
        }



       catch (Exception | Error e) {
            e.printStackTrace();
            ExtenantReportUtils.addFailedLog("validating shipping address",
                    "shipping address is filled in to the fields", "user faield to fill the shipping address",
                    Common.getscreenShotPathforReport("shipingaddressfaield"));
            Assert.fail();


       }
	}
       
	public void Edit_Address(String dataSet) throws Exception {
		String address=data.get(dataSet).get("Street");
		try {
			//Thread.sleep(5000);
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			//Common.clickElement("xpath", "//div[@class='m-account-nav__welcome']//span[@class='a-icon-text-btn__label']");
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
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(5000);
		Common.clickElement("xpath", "(//span[text()='Edit Address'])[1]");
		//Common.clickElement("xpath", "//a[text()='Address Book']");
		Sync.waitPageLoad();
		Common.assertionCheckwithReport(Common.getPageTitle().equals("Edit Address"),
				"validating the Navigation to the Address Book page",
				"After Clicking on Address Book CTA user should be navigate to the Address Book page",
				"Sucessfully User Navigates to the Address Book page after clicking on the Address Book CTA",
				"Failed to Navigate to the Address Book page after Clicking on Address Book CTA");
		
		String expectedResult = "email field will have email address";
		try {
			//Common.clickElementStale("xpath", "//span[contains(text(),'Add New Address')]");
		    Common.textBoxInput("xpath", "//form[@id='form-validate']//input[@name='firstname']",
		            data.get(dataSet).get("FirstName"));
		    int size = Common.findElements("id", "customer-email").size();
		    Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,
		            "Filled Email address", "unable to fill the email address");
		    Common.textBoxInput("xpath", "//form[@id='form-validate']//input[@name='lastname']",
		            data.get(dataSet).get("LastName"));
		    Common.clickElement("xpath","(//input[@name='street[]'])[1]");
		    Common.textBoxInput("xpath","(//input[@name='street[]'])[1]",
		            address);
		    Common.actionsKeyPress(Keys.BACK_SPACE);
		    Common.actionsKeyPress(Keys.BACK_SPACE);
		    Thread.sleep(5000);
		    Common.findElement("xpath","(//input[@name='street[]'])[1]").sendKeys("Rd");
		    Thread.sleep(5000);
		    Sync.waitElementPresent(30, "xpath", "//div[@class='pcaitem']");
		    String newadd=Common.findElement("xpath", "//div[@class='pcaitem']").getAttribute("title");
		    System.out.println(newadd);
		    Common.clickElement("xpath","//input[@name='city']");
		    Common.clickElement("xpath","(//input[@name='street[]'])[1]");
		    Thread.sleep(6000);
		    List<WebElement> listofaddresss=Common.findElements("xpath", "(//div[@class='pcaitem'])[1]");
		    ArrayList<WebElement> listaddress=new ArrayList<WebElement>();
		    for(WebElement addresscheck:listofaddresss)
		    {
		        
		     listaddress.add(addresscheck);
		     String add=addresscheck.getAttribute("title");
		     System.out.println(add);
		     System.out.println(address);
		     Thread.sleep(4000);
		     Common.assertionCheckwithReport(add.equals(address) ,
		                "validating the dropdown in the shipping address page ",
		                "User should able to see the Locate dropdown for respective address", "Successfully Locate dropdown has been displayed in shipping address page",
		                "Failed to display dropdown in the shipping address page");
		     
		    }

		    String Text = Common.getText("xpath", "(//input[@name='street[]'])[1]");
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
		    Common.textBoxInputClear("name", "postcode");
		    Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		    Thread.sleep(5000);



		   Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		   Thread.sleep(2000);
			
			
			Common.clickElement("xpath", "//button[@title='Save Address']");


		   Sync.waitPageLoad();
		}



		catch (Exception | Error e) {
		    e.printStackTrace();
		    ExtenantReportUtils.addFailedLog("validating shipping address",
		            "shipping address is filled in to the fields", "user faield to fill the shipping address",
		            Common.getscreenShotPathforReport("shipingaddressfaield"));
		    Assert.fail();


		}
		}

	public void minicart_Checkout() {

		try {
			click_minicart();
			String minicart = Common.findElement("xpath", "//p[@class='c-mini-cart__total-counter']//strong").getText();
			Sync.waitElementPresent("xpath", "//button[@title='Checkout']");
			Common.clickElement("xpath", "//button[@title='Checkout']");
			Thread.sleep(4000);
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
	
	
	
	public void Addnew_Address(String dataSet) {
		 String address=data.get(dataSet).get("Street");
			// TODO Auto-generated method stub
			String expectedResult = "shipping address is entering in the fields";
		
			int size = Common.findElements(By.xpath("//span[contains(text(),'Add New Address')]")).size();
			if (size > 0) {
				try {
					Common.clickElement("xpath", "//span[contains(text(),'Add New Address')]");
					Common.clickElementStale("xpath", "//span[contains(text(),'Add New Address')]");
					
		         /*   Common.textBoxInput("xpath", "//form[@id='form-validate']//input[@name='firstname']",
		                    data.get(dataSet).get("FirstName"));
		            int size1 = Common.findElements("id", "customer-email").size();
		            Common.assertionCheckwithReport(size1 > 0, "validating the email address field", expectedResult,
		                    "Filled Email address", "unable to fill the email address");
		            Common.textBoxInput("xpath", "//form[@id='form-validate']//input[@name='lastname']",
//		                    data.get(dataSet).get("LastName"));*/
		            Common.clickElement("xpath","//form[@id='co-shipping-form']//input[@name='street[0]']");
		            Common.textBoxInput("xpath","//form[@id='co-shipping-form']//input[@name='street[0]']",
		                    address);
		            Common.actionsKeyPress(Keys.BACK_SPACE);
		            Common.actionsKeyPress(Keys.BACK_SPACE);
		            Thread.sleep(5000);
		            Common.findElement("xpath","//form[@id='co-shipping-form']//input[@name='street[0]']").sendKeys("Rd");
		            Thread.sleep(5000);
		            Sync.waitElementPresent(50, "xpath", "(//div[@class='pcaitem'])[1]");
		            String newadd=Common.findElement("xpath", "(//div[@class='pcaitem'])[1]").getAttribute("title");
		            System.out.println(newadd);
		            Common.clickElement("xpath","//form[@id='co-shipping-form']//input[@name='city']");
		            Common.clickElement("xpath","//form[@id='co-shipping-form']//input[@name='street[0]']");
		            Thread.sleep(5000);
		            List<WebElement> listofaddresss=Common.findElements("xpath", "(//div[@class='pcaitem'])[1]");
		            ArrayList<WebElement> listaddress=new ArrayList<WebElement>();
		            for(WebElement addresscheck:listofaddresss)
		            {
		                
		             listaddress.add(addresscheck);
		             String add=addresscheck.getAttribute("title");
		             System.out.println(add);
		             System.out.println(address);
		             Thread.sleep(4000);
		             Common.assertionCheckwithReport(add.equals(address) ,
		                        "validating the dropdown in the shipping address page ",
		                        "User should able to see the Locate dropdown for respective address", "Successfully Locate dropdown has been displayed in shipping address page",
		                        "Failed to display dropdown in the shipping address page");
		             
		            }
		        
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
		            Thread.sleep(4000);
		            Common.findElement("name", "postcode").clear();
		            Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		            Thread.sleep(5000);



		           Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		           Thread.sleep(3000);
					
					
					Common.clickElement("xpath", "(//span[@class='a-btn__label'])[3]");


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
		           Thread.sleep(4000);
					int sizes = Common.findElements("xpath", "//input[@class='a-radio-button__input']").size();
					if (sizes > 0) {
//						Sync.waitElementPresent(30, "xpath", "//input[@value='tablerate_bestway']");
//						Common.clickElement("xpath", "//input[@value='tablerate_bestway']");
					}
					Thread.sleep(4000);
					expectedResult = "shipping address is filled in to the fields";
					Common.clickElement("xpath", "//button[@data-role='opc-continue']");
					

				}

				catch (Exception | Error e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("validating the shipping address field with valid Datas", expectedResult,
							"failed to add a addres in the filled",
							Common.getscreenShotPathforReport("failed to add a address"));

					Assert.fail();
				}
			}
		}
	
	public void click_minicart() {
		try {
			Thread.sleep(8000);
//		    Common.scrollIntoView("xpath", "//a[contains(@class,'c-mini')]");
			Sync.waitElementPresent("xpath", "//a[contains(@class,'c-mini')]");
			Common.mouseOverClick("xpath", "//a[contains(@class,'c-mini')]");
			String openminicart = Common.findElement("xpath", "//div[@data-block='minicart']").getAttribute("class");
			System.out.println(openminicart);
			Common.assertionCheckwithReport(openminicart.contains("active"), "To validate the minicart popup",
					"Should display the mini cart", "Mini cart is displayed", "mini cart is not displayed");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the minicart popup", "Should display the mini cart",
					"unable to  display the mini cart", Common.getscreenShot("Failed to display the mini cart"));
			Assert.fail();

		}
	}

	
	public void addDeliveryAddress(String dataSet) throws Exception {
        String address=data.get(dataSet).get("Street");
        
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
                    address);
            Common.actionsKeyPress(Keys.BACK_SPACE);
            Common.actionsKeyPress(Keys.BACK_SPACE);
            Thread.sleep(5000);
            Common.findElement("xpath","//form[@id='co-shipping-form']//input[@name='street[0]']").sendKeys("Rd");
            Thread.sleep(5000);
            Sync.waitElementPresent(30, "xpath", "//div[@class='pcaitem']");
            String newadd=Common.findElement("xpath", "//div[@class='pcaitem']").getAttribute("title");
            System.out.println(newadd);
            Common.clickElement("xpath", "//form[@id='co-shipping-form']//input[@name='city']");
            Common.clickElement("xpath","//form[@id='co-shipping-form']//input[@name='street[0]']");
            Thread.sleep(6000);
            List<WebElement> listofaddresss=Common.findElements("xpath", "(//div[@class='pcaitem'])[1]");
            ArrayList<WebElement> listaddress=new ArrayList<WebElement>();//(//div[@class='pcaitem pcaselected'])[1]
            for(WebElement addresscheck:listofaddresss)
            {
                
             listaddress.add(addresscheck);
             String add=addresscheck.getAttribute("title");
             System.out.println(add);
             System.out.println(address);
             Thread.sleep(9000);
             Common.assertionCheckwithReport(add.equals(address) ,
                        "validating the dropdown in the shipping address page ",
                        "User should able to see the Locate dropdown for respective address", "Successfully Locate dropdown has been displayed in shipping address page",
                        "Failed to display dropdown in the shipping address page");
             
            }
        
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
           Thread.sleep(4000);
            int size = Common.findElements("xpath", "//input[@class='a-radio-button__input']").size();
            if (size > 0) {
                Sync.waitElementPresent(30, "xpath", "//input[@value='tablerate_bestway']");
                Common.clickElement("xpath", "//input[@value='tablerate_bestway']");
            }
            Thread.sleep(4000);
            expectedResult = "shipping address is filled in to the fields";
            Common.clickElement("xpath", "//button[@data-role='opc-continue']");
            



       }



       catch (Exception | Error e) {
            e.printStackTrace();
            ExtenantReportUtils.addFailedLog("validating the shipping address field with valid Datas", expectedResult,
                    "failed to add a addres in the filled",
                    Common.getscreenShotPathforReport("failed to add a address"));



           Assert.fail();
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
			
//			Common.clickElement("xpath", "//img[@alt='" + products + "']");
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
	
	public void minicart_Checkout1() {

		try {
			click_minicart();
			String minicart = Common.findElement("xpath", "//p[@class='c-mini-cart__total-counter']//strong").getText();
			Sync.waitElementPresent("xpath", "//button[@title='Checkout']");
			Common.clickElement("xpath", "//button[@title='Checkout']");
			Sync.waitPageLoad();
			Sync.waitElementPresent(30, "xpath", "(//span[@class='shipping-method__radio'])[1]");
			Common.clickElement("xpath", "(//span[@class='shipping-method__radio'])[1]");
			Thread.sleep(7000);
			Sync.waitElementPresent(30, "xpath", "//button[@data-role='opc-continue']");
			Common.clickElement("xpath", "//button[@data-role='opc-continue']");
			Thread.sleep(4000);
			String checkout = Common.findElement("xpath", "//strong[@role='heading']//span").getText();
			Common.assertionCheckwithReport(
					checkout.equals(minicart) && Common.getCurrentURL().contains("checkout/#payment"),
					"validating the navigation to the Payment page when we click on the Checkout",
					"User should navigate to the Payment page", "Successfully navigate to the Payment page",
					"Failed to navigate to the Payment page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the navigation to the Payment page when we click on the Checkout",
					"User should navigate to the Payment page", "unable to navigate to the Payment page",
					Common.getscreenShot("Failed to navigate to the Payment page"));

			Assert.fail();
		}

	}
	public void BillingAddress1(String dataSet) {
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
			//Common.clickElement("xpath", "//span[contains(text(),'My billing and shipping address are the same')]");
	        Thread.sleep(2000);
		   
			Common.clickElement("xpath", "//span[contains(text(),'My billing and shipping address are the same')]");
			
			Sync.waitElementPresent(30, "xpath", "//select[@name='billing_address_id']");
			Common.clickElement("xpath", "//select[@name='billing_address_id']");
	       Thread.sleep(2000);
			
			Sync.waitElementPresent(30, "xpath", "//option[text()='New Address']");
			Common.clickElement("xpath", "//option[text()='New Address']");
			
			
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
		
		if(!url.contains("na.oxo") && !url.contains("preprod")){
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
			Thread.sleep(3000);
			order=Common.getText("xpath", "//div[@class='checkout-success']/p/span");
			System.out.println(order);
		}
		if(Common.findElements("xpath","//a[@class='order-number']/strong").size()>0) {
			Thread.sleep(3000);
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
			Number=Common.findElement("id", "Field-numberInput").getAttribute("value").replace(" ", "");
			System.out.println(Number);
			
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
}





