package TestComponent.Osprey_EMEA;

import static org.testng.Assert.fail;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import groovyjarjarantlr.CommonASTWithHiddenTokens;
import groovyjarjarantlr4.v4.parse.ANTLRParser.action_return;

public class OspreyRegressionEMEA {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public OspreyRegressionEMEA(String datafile, String sheetname) {

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
			Common.assertionCheckwithReport(message.contains("Thank you for registering with Osprey Store.")&&Common.getPageTitle().contains("My Account"),
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

	public void click_singinButton() {
		// TODO Auto-generated method stub
		try
		{
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

		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the user navigates to the signin page",
					"user should able to land on the signIn page after clicking on the sigin button",
					"Unable to click on the singIn button and not Navigated to the signIn page",
					Common.getscreenShotPathforReport(
							"Failed to click signIn button and not Navigated to the signIn page"));
			Assert.fail();
		}
		
	}

	public void Forgot_password(String Dataset) {
		// TODO Auto-generated method stub
		try
		{
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

	public void Empty_Email() {
		// TODO Auto-generated method stub
		try {

			Common.textBoxInputClear("xpath", "//input[@placeholder='Enter Email Address']");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//span[@class='icon-arrow a-icon_icon']");
			Sync.waitElementPresent(30, "xpath", "//div[@class='newsletter-error']");
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

	public void stayIntouch() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//form[@class='m-newsletter-signup__form']");
			Common.clickElement("xpath", "//form[@class='m-newsletter-signup__form']");
			Common.textBoxInput("xpath", "//input[@placeholder='Enter Email Address']", Utils.getEmailid());
			Thread.sleep(5000);
			Common.clickElement("xpath", "//span[@class='icon-arrow a-icon_icon']");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			String Text = Common.getText("xpath", "//div[@class='a-message__container-inner']");
			System.out.println(Text);
			String expectedResult = "User gets confirmation message that it was submitted";
			Common.assertionCheckwithReport(Text.contains("Thank you for your subscription."),
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

	public void Invalid_email_newsletter(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//form[@class='m-newsletter-signup__form']");
			Common.clickElement("xpath", "//form[@class='m-newsletter-signup__form']");
			Common.textBoxInput("xpath", "//input[@placeholder='Enter Email Address']", data.get(Dataset).get("Email"));
			Common.clickElement("xpath", "//span[@class='icon-arrow a-icon_icon']");
			Sync.waitElementPresent(30, "xpath", "//div[@class='newsletter-error']");
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

	public void Login_Account(String dataSet) {
		// TODO Auto-generated method stub
		try {
			if (Common.getCurrentURL().contains("stage")) {
				Sync.waitPageLoad();
				Common.textBoxInput("id", "email", data.get(dataSet).get("UserName"));
			} else {
				Common.textBoxInput("id", "email", data.get(dataSet).get("Prod UserName"));
			}
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//button[contains(@class,'action login')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("Home page") || Common.getPageTitle().contains("Osprey"),
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

	public void Account_page_Validation(String Dataset) throws Exception {
		// TODO Auto-generated method stub
		Sync.waitElementPresent("xpath", "//div[@class='m-account-nav__content']");
		Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
		Sync.waitElementPresent("xpath", "//a[text()='My Account']");
		Common.clickElement("xpath", "//a[text()='My Account']");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		if (Common.getCurrentURL().contains("stage")) {
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
					Common.assertionCheckwithReport(title.contains(Account[i]) || title.contains("My Payment Methods") || title.contains("Newsletter Subscription") || title.contains("Pro deal information"),
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
					Common.assertionCheckwithReport(title.contains(Account[i]) || title.contains("My Payment Methods") || title.contains("Newsletter Subscription") || title.contains("Pro deal information"),
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
	
	public void header_OutdoorPacks(String Dataset) {
		
		String names = data.get(Dataset).get("Outdoorpacks");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'Backpacks & Bags')]");
				Common.clickElement("xpath", "//span[contains(text(),'Backpacks & Bags')]");
				Common.clickElement("xpath", "//span[contains(text(),'Outdoor Packs')]");
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
						"verifying the header link " + Links[i] + "Under Outdoor Packs",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Outdoor Packs",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}
	
	public void header_KidsPacks(String Dataset) {
		
		String names = data.get(Dataset).get("KidsPacksCarriers");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'Backpacks & Bags')]");
				Common.clickElement("xpath", "//span[contains(text(),'Backpacks & Bags')]");
				Common.clickElement("xpath", "//span[contains(text(),'Kids Packs & Carriers')]");
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
						"verifying the header link " + Links[i] + "Under Kids Packs",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Kids Packs",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}
public void header_DayPacks(String Dataset) {
		
		String names = data.get(Dataset).get("DayPacks");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'Backpacks & Bags')]");
				Common.clickElement("xpath", "//span[contains(text(),'Backpacks & Bags')]");
				Common.clickElement("xpath", "//span[contains(text(),'Day Packs')]");
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
						"verifying the header link " + Links[i] + "Under Day Packs",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Day Packs",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}
public void header_Travel(String Dataset) {
	
	String names = data.get(Dataset).get("Travel");
	String[] Links = names.split(",");
	int i = 0;
	try {
		for (i = 0; i < Links.length; i++) {
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Travel')]");
			Common.clickElement("xpath", "//span[contains(text(),'Travel')]");

			Thread.sleep(3000);
			Sync.waitElementPresent("xpath",
					"//li[contains(@class,'level1 ')]//a//span[contains(text(),'" + Links[i] + "')]");
			Common.clickElement("xpath",
					"//li[contains(@class,'level1 ')]//a//span[contains(text(),'" + Links[i] + "')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
			String breadcrumbs = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
			Common.assertionCheckwithReport(title.contains(Links[i]) || breadcrumbs.contains(Links[i]),
					"verifying the header link " + Links[i] + "Under Travel",
					"user should navigate to the " + Links[i] + " page",
					"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

		}
	}

	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under Travel",
				"User should navigate to the " + Links[i] + "pages",
				" unable to navigate to the " + Links[i] + "pages",
				Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
		Assert.fail();
	}

}
public void header_Accessories(String Dataset) {
	
	String names = data.get(Dataset).get("Accessories");
	String[] Links = names.split(",");
	int i = 0;
	try {
		for (i = 0; i < Links.length; i++) {
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Accessories')]");
			Common.clickElement("xpath", "//span[contains(text(),'Accessories')]");

			Thread.sleep(3000);
			Sync.waitElementPresent("xpath",
					"//li[contains(@class,'level1 ')]//a//span[contains(text(),'" + Links[i] + "')]");
			Common.clickElement("xpath",
					"//li[contains(@class,'level1 ')]//a//span[contains(text(),'" + Links[i] + "')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
			String breadcrumbs = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
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

public void header_Featured(String Dataset) {
	
	String names = data.get(Dataset).get("Featured");
	String[] Links = names.split(",");
	int i = 0;
	try {
		for (i = 0; i < Links.length; i++) {
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Featured')]");
			Common.clickElement("xpath", "//span[contains(text(),'Featured')]");

			Thread.sleep(3000);
			Sync.waitElementPresent("xpath",
					"//li[contains(@class,'level1 ')]//a//span[contains(text(),'" + Links[i] + "')]");
			Common.clickElement("xpath",
					"//li[contains(@class,'level1 ')]//a//span[contains(text(),'" + Links[i] + "')]");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
			String breadcrumbs = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
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

public void header_ShopAll(String Dataset) {
	
	String names=data.get(Dataset).get("Shop all");
	String[] Links=names.split(",");
	int i=0;
	try
{
		
			for(i=0;i<Links.length;i++){
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Backpacks & Bags')]");
			Common.clickElement("xpath", "//span[contains(text(),'Backpacks & Bags')]");
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
		ExtenantReportUtils.addFailedLog("verifying the header link "+Links[i]+ "",
				"User should navigate to the "+Links[i]+"pages",
				" unable to navigate to the "+Links[i]+"pages",
				Common.getscreenShot("Failed to navigate to the "+Links[i]+"pages"));
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
//        Baby_Registry("Baby Registry");
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

public void Baby_Registry(String Dataset) {
	// TODO Auto-generated method stub
	
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

public void newregistry_CTA(String Dataset) {
	// TODO Auto-generated method stub
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
}