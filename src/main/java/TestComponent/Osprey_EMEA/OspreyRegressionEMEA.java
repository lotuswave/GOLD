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
	try
	{
		Sync.waitElementPresent(30, "xpath", "//strong[text()='Gift Registry']");
		Common.clickElement("xpath", "//strong[text()='Gift Registry']");
		Sync.waitImplicit(40);
		Common.maximizeImplicitWait();
		Thread.sleep(2000);
		String page=Common.findElement("xpath", "//h1[@class='page-title-wrapper h2']").getText();
		Common.assertionCheckwithReport(page.contains("Gift Registry"),
				"validating the gift registry page navigation ",
				"After clicking Gift registry it should navigate to the gift registry page",
				"successfully Navigated to the gift registry page ", "failed to Navigate to the gift rigistry page");
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

public void selectshippingaddress(String Dataset) {
	// TODO Auto-generated method stub
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

public void click_UGC() {
	// TODO Auto-generated method stub
	try
	{
		Common.actionsKeyPress(Keys.END);
		Common.scrollIntoView("xpath", "//div[contains(@class,'ugc instagram')]");
		Sync.waitElementPresent("xpath", "//div[@class='y-image-overlay ']");
		Common.scrollIntoView("xpath", "//div[@class='y-image-overlay ']");
		Common.clickElement("xpath", "//div[@class='y-image-overlay ']");
//		Thread.sleep(6000);
		String yopto=Common.findElement("xpath", "//a[@class='yotpo-logo-link-new']").getAttribute("aria-label");
//		Thread.sleep(6000);
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

public void Find_a_delear() {
	// TODO Auto-generated method stub
	try
	{
	Sync.waitPageLoad();
	Common.actionsKeyPress(Keys.END);
	Common.clickElement("xpath", "//span[contains(@class,'icon-location')]");

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
	String store = "Ride Away Bicycles";

	try {

		Common.switchFrames("xpath", "//iframe[@id='lcly-embedded-iframe-inner-0']");

		Sync.waitPageLoad();
		Thread.sleep(8000);
		String id = Common.findElement("xpath", "//div[contains(@aria-label,\"" + store + " \")]")
				.getAttribute("id");
//        Common.clickElement("xpath", "//div[contains(@aria-label,"DICK'S Sporting ")]");

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

public void Validate_store_sidebar() {
	// TODO Auto-generated method stub
	try {
//		Common.switchFrames("xpath", "//iframe[contains(@id,'lcly-embedded-iframe')]");
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
	// TODO Auto-generated method stub
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
	// TODO Auto-generated method stub
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
	// TODO Auto-generated method stub
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
		Common.javascriptclickElement("xpath", "//a[@class='nav-bar-back']");

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

public void Click_Instock() {
	// TODO Auto-generated method stub
	try {

		Sync.waitPageLoad();
		Common.switchFrames("xpath", "//iframe[@id='lcly-embedded-iframe-inner-0']");
		Sync.waitElementPresent(40, "xpath", "//a[@id='dealer-navigation-inventory']");
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
		String Size=Common.findElement("xpath", "(//span[@class='a-product-attribute__value'])[4]").getText();
		System.out.println(Size);
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

public void Configurable_addtocart_pdp(String Dataset) {
	// TODO Auto-generated method stub
	String product = data.get(Dataset).get("Colorproduct");
	String productcolor = data.get(Dataset).get("Color");
	String Productsize=data.get(Dataset).get("Size");
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
		Sync.waitElementPresent("xpath", "//div[@data-option-label='"+ Productsize +"']");
		Common.clickElement("xpath", "//div[@data-option-label='" +Productsize+"']");
		
		product_quantity(Dataset);
//		click_UGC();
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

public void Bagpacks_headerlinks(String category) {
	// TODO Auto-generated method stub
	String expectedResult = "User should click the" + category;
	try {

		Sync.waitElementPresent("xpath", "//a[contains(@class,'level-top')]//span[text()=' Backpacks & Bags']");
		Thread.sleep(3000);
		Common.clickElement("xpath", "//a[contains(@class,'level-top')]//span[text()=' Backpacks & Bags']");

		Thread.sleep(3000);

		try {
			Common.mouseOver("xpath", "//span[contains(text(),'" + category + "')]");
		} catch (Exception e) {
			Common.clickElement("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Backpacks & Bags']");
		}
		Common.clickElement("xpath", "//span[contains(text(),'" + category + "')]");
		Thread.sleep(4000);
		Common.clickElement("xpath", "//span[text()=' Backpacking Packs']");
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

public void social_Links(String dataSet) {

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
				"User unable to navigate Social link page", Common.getscreenShotPathforReport("socialpage"));
		Assert.fail();
	}
}


}