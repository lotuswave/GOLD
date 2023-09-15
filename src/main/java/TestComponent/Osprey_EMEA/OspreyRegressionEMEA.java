package TestComponent.Osprey_EMEA;
 
import static org.testng.Assert.fail;

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

import org.openqa.selenium.Alert;
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
	private String String;
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
		try {
			Sync.waitPageLoad();
			Close_Geolocation();
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			System.out.println(size);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Osprey"),
					"validating store logo on the homwpage",
					"System directs the user to the Homepage and store logo should display",
					"Sucessfully user navigates to the home page and logo has been displayed",
					"Failed to navigate to the homepage and logo is not displayed");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating store logo on the homwpage",
					"System directs the user to the Homepage and store logo should display",
					"Unable to navigate to the homepage and logo is not displayed",
					"Failed to navigate to the homepage and logo is not displayed");

			Assert.fail();
		}

	}

	public String Create_Account(String Dataset) {
		// TODO Auto-generated method stub
		String email = "";
		String Store= data.get(Dataset).get("Store");
		try {

			Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(Dataset).get("LastName"));
			Common.textBoxInput("xpath", "//input[@name='email']", Utils.getEmailid());
			email = Common.findElement("xpath", "//input[@name='email']").getAttribute("value");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
					data.get(Dataset).get("Confirm Password"));
			Thread.sleep(4000);
			Common.clickElement("xpath", "//button[@class='action submit primary a-btn a-btn--primary']");
			Sync.waitImplicit(30);
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			System.out.println(message);
			Common.assertionCheckwithReport(
					message.contains("Osprey")
							&& Common.getCurrentURL().contains("account") ,
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

	public void createaccount_exitingemail(String Dataset) {
		// TODO Auto-generated method stub
		try {
			click_Createaccount();
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
			Thread.sleep(4000);
			Common.clickElement("xpath", "//button[@class='action submit primary a-btn a-btn--primary']");
			String exsitingemail = Common.findElement("xpath", "//div[@data-ui-id='message-error']//div").getText();
            String exsiting=Common.findElement("xpath", "//div[@data-ui-id='message-error']").getAttribute("data-ui-id");		
            		Common.assertionCheckwithReport(
					exsitingemail.contains("There is already an account with this email address") || exsiting.contains("message-error"),
					"validating the error messages for existing email",
					"User should able to get error message when we given exsiting email",
					"Sucessfully error message has been displayed when user use the existing email",
					"Failed to get an error message when user used the existing email");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the error messages for existing email",
					"User should able to get error message when we given exsiting email",
					"Unable to get an error message when user used the existing email",
					Common.getscreenShotPathforReport(
							"Failed to get an error message when user used the existing email"));
			Assert.fail();

		}
	}

	public void click_singinButton() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//li[@class='m-account-nav__log-in']//a");
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

	public void Forgot_password(String Dataset) {
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
			if (Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("na.osprey")) {
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
					Common.getPageTitle().contains("Home page") || Common.getPageTitle().contains("My Wish List")
							|| Common.getPageTitle().contains("Osprey"),
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
		if (Common.getCurrentURL().contains("stage")|| Common.getCurrentURL().contains("preprod")) {
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
					Common.assertionCheckwithReport(
							title.contains(Account[i]) || title.contains("My Wish Lists")
									|| title.contains("My Payment Methods") || title.contains("Newsletter Subscription")
									|| title.contains("Pro deal information"),
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
					Common.assertionCheckwithReport(title.contains(Account[i]) || title.contains("My Payment Methods")
							|| title.contains("Newsletter Subscription") || title.contains("Pro deal information"),
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
				System.out.println(title);
				System.out.println(breadcrumbs);
				System.out.println(Links[i]);
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
	
	public void header_Shopbyactivity(String Dataset) {

		String names = data.get(Dataset).get("Featured");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'Featured')]");
				Common.clickElement("xpath", "//span[contains(text(),'Featured')]");
				Common.clickElement("xpath", "//span[contains(text(),'Shop by Activity')]");
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
						"verifying the header link " + Links[i] + "Under the Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under the Fearued",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}
	

	
	
	public void header_Shopbycollection(String Dataset) {

		String names = data.get(Dataset).get("Featured");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'Featured')]");
				Common.clickElement("xpath", "//span[contains(text(),'Featured')]");
				Common.clickElement("xpath", "//span[contains(text(),'Shop by Activity')]");
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
						"verifying the header link " + Links[i] + "Under the Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "Under the Fearued",
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

	public void Bagpack_ShopAll(String Dataset) {

		String names = data.get(Dataset).get("Shop all");
		String[] Links = names.split(",");
		int i = 0;
		try {

			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'Backpacks & Bags')]");
				Common.clickElement("xpath", "//span[contains(text(),'Backpacks & Bags')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//span[contains(text(),' " + Links[i] + "')]");
				Common.clickElement("xpath", "//span[contains(text(),' " + Links[i] + "')]");
				Common.clickElement("xpath", "//a[contains(@aria-label,'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				Common.assertionCheckwithReport(title.contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}
	
	public void Travel_ShopAll(String Dataset) {

		String names = data.get(Dataset).get("Shop all");
		String[] Links = names.split(",");
		int i = 0;
		try {

			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'Travel')]");
				Common.clickElement("xpath", "//span[contains(text(),'Travel')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//span[contains(text(),' " + Links[i] + "')]");
				Common.clickElement("xpath", "//span[contains(text(),' " + Links[i] + "')]");
				Common.clickElement("xpath", "//a[contains(@aria-label,'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				Common.assertionCheckwithReport(title.contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
			Assert.fail();
		}

	}
	
	public void Featured_ShopAll(String Dataset) {

		String names = data.get(Dataset).get("Shop all");
		String[] Links = names.split(",");
		int i = 0;
		try {

			for (i = 0; i < Links.length; i++) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'Featured')]");
				Common.clickElement("xpath", "//span[contains(text(),'Featured')]");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//span[contains(text(),' " + Links[i] + "')]");
				Common.clickElement("xpath", "//span[contains(text(),' " + Links[i] + "')]");
				Common.clickElement("xpath", "//a[contains(@aria-label,'" + Links[i] + "')]");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//h1[contains(@class,'c')]").getText();
				System.out.println(title);
				System.out.println(Links[i]);
				Common.assertionCheckwithReport(title.contains(Links[i]),
						"verifying the header link " + Links[i] + "Under Featured",
						"user should navigate to the " + Links[i] + " page",
						"user successfully Navigated to the " + Links[i], "Failed to navigate to the " + Links[i]);

			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the header link " + Links[i] + "",
					"User should navigate to the " + Links[i] + "pages",
					" unable to navigate to the " + Links[i] + "pages",
					Common.getscreenShot("Failed to navigate to the " + Links[i] + "pages"));
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
				if(Common.getCurrentURL().contains("stage3"))
				{
					Common.dropdown("xpath","//select[@id='event_country_region']", SelectBy.TEXT,
							data.get(Dataset).get("Region"));
				}
				else
				{
				Common.textBoxInput("xpath", "//input[@id='event_country_region_text']",
						data.get(Dataset).get("Region"));
			
				}
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
			// Baby_Registry("Baby Registry");
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

	public void more_Quantity(String Dataset) {

		String Quantity = data.get(Dataset).get("Quantity");
		System.out.println(Quantity);
		String MoreQuantity = data.get(Dataset).get("MoreQuantity");
		System.out.println(MoreQuantity);
		String products = data.get(Dataset).get("Products");

		try {
			Common.findElement("xpath", "//select[@class='a-select-menu']");
			Common.dropdown("xpath", "//select[@class='a-select-menu']", Common.SelectBy.VALUE, Quantity);
			Thread.sleep(4300);
			// Common.textBoxInputClear("xpath", "//input[@class='a-number-input
			// m-add-to-cart__input']");
			Common.findElement("xpath", "//input[@class='a-number-input m-add-to-cart__input']").sendKeys(MoreQuantity);
			// Common.textBoxInput("xpath", "//input[@class='a-number-input
			// m-add-to-cart__input']", "1000");

			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			Sync.waitElementVisible("xpath", "//div[@data-ui-id='message-error']//div[@class='a-message__container-inner']");
			String Error = Common.findElement("xpath",
					"//div[@data-ui-id='message-error']//div[@class='a-message__container-inner']").getText();
			System.out.println(Error);
			System.out.println("We don’t have as many " + products + " as your requested");
			Common.assertionCheckwithReport(Error.contains("We don’t have as many " + products + " as your requested"),
					"validating the error message if item is not not avliable request quantity",
					"Error message should be dispaly if the requested quantity is not avaliable",
					"Sucessfully Error message ha been displayed when reuested quantity is not avaliable",
					"failed to display the error message if requested quantity is not avaliable");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the error message if item is not not avliable request quantity",
					"Error message should be dispaly if the requested quantity is not avaliable",
					"Unable to display the error message if requested quantity is not avaliable",
					Common.getscreenShot("failed to display the error message if requested quantity is not avaliable"));
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

	public void My_Favorites() {
		// TODO Auto-generated method stub
		try {
			if(Common.getCurrentURL().contains("stage3"))
			{
				Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
				Sync.waitElementPresent(30, "xpath", "//a[text()='My Favorites']");
				Common.clickElement("xpath", "//a[text()='My Favorites']");
				Common.assertionCheckwithReport(Common.getPageTitle().equals("My Favorites"),
						"validating the Navigation to the My Favorites page",
						"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
						"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA",
						"Failed to Navigate to the My Favorites page after Clicking on My Favorites button");
			}
			else
			{
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Sync.waitElementPresent(30, "xpath", "//a[text()='My Wish Lists']");
			Common.clickElement("xpath", "//a[text()='My Wish Lists']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Wish List"),
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

	public void Addtocart_From_MyFavorites(String Dataset) {
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
			if (MyFavorites != 0) {
				search_product("Product");
				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product + "']");
				Common.clickElement("xpath", "//img[@alt='" + product + "']");
				Sync.waitPageLoad();
				Sync.waitElementPresent("xpath", "//div[@aria-label='" + productcolor + "']");
				Common.clickElement("xpath", "//div[@aria-label='" + productcolor + "']");
				Sync.waitElementPresent("xpath", "//div[@data-option-label='" + Productsize + "']");
				Common.clickElement("xpath", "//div[@data-option-label='" + Productsize + "']");

				Sync.waitElementPresent(30, "xpath", "//button[@data-action='add-to-wishlist']");
				Common.clickElement("xpath", "//button[@data-action='add-to-wishlist']");
				Sync.waitPageLoad(30);
				Thread.sleep(3000);
				if(Common.getCurrentURL().contains("stage3"))
                {
                    Sync.waitPageLoad();
                    String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
                    System.out.println(message);
                    Common.assertionCheckwithReport(message.contains("has been added to your Favorites"),
                            "validating the  product add to the Favorites", "Product should be add to Favorites",
                            "Sucessfully product added to the Favorites ", "failed to add product to the Favorites");

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
				Common.assertionCheckwithReport(Common.getPageTitle().equals("My Wish List") ||Common.getPageTitle().equals("My Favorites") ,
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
				String Whishlistproduct = Common
						.findElement("xpath", "//div[contains(@class,'m-product-card__name')]//a").getText();
				System.out.println(Whishlistproduct);

				if (Whishlistproduct.equals(product)) {
					Sync.waitElementPresent(30, "xpath", "//a[contains(@title,'" + product + "')]//img");
					Common.mouseOver("xpath", "//a[contains(@title,'" + product + "')]//img");
					Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
					Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
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
					minicart_Checkout();
				
				} else {
					Assert.fail();
				}

			} else {
				Sync.waitPageLoad();

				Common.scrollIntoView("xpath", "//img[contains(@class,'lazy m-product-card')]");
				Sync.waitElementPresent(30, "xpath", "//img[contains(@class,'lazy m-product-card')]");
				Common.mouseOver("xpath", "//img[contains(@class,'lazy m-product-card')]");
				Sync.waitElementPresent("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
				see_options("Product");
				

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
		System.out.println(products);
		String productcolor = data.get(Dataset).get("Color");
		System.out.println(productcolor);
		String Productsize = data.get(Dataset).get("Size");
		System.out.println(Productsize);
		try {
			Thread.sleep(4000);
			String seeoption = Common.findElement("xpath", "//fieldset[@class='fieldset m-product-card__cta']//span")
					.getText();
			System.out.println(seeoption);
			if (seeoption.equals("SEE OPTIONS")) {
				Sync.waitElementPresent("xpath", "//label[@for='wishlist-select-all']");
				Common.clickElement("xpath", "//label[@for='wishlist-select-all']");
				Common.clickElement("xpath", "//span[text()='Remove From My Favorites']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String emptymessage = Common.findElement("xpath", "//div[contains(@class,'message in')]//span")
						.getText();
				Common.assertionCheckwithReport(emptymessage.contains("You have no items in your favorites."),
						"validating the empty products in my favrioutes page ",
						"Products should not appear in the my favoritoes page",
						"Sucessfully product are empty in my favorites page",
						"failed to see empty products in my favorites page");
				search_product("Product");
				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
				Common.clickElement("xpath", "//img[@alt='" + products + "']");
				Sync.waitPageLoad();
				Sync.waitElementPresent("xpath", "//div[@aria-label='" + productcolor + "']");
				Common.clickElement("xpath", "//div[@aria-label='" + productcolor + "']");
				Sync.waitElementPresent("xpath", "//div[@data-option-label='" + Productsize + "']");
				Common.clickElement("xpath", "//div[@data-option-label='" + Productsize + "']");
				Sync.waitElementPresent(30, "xpath", "//button[@data-action='add-to-wishlist']");
				Common.clickElement("xpath", "//button[@data-action='add-to-wishlist']");
				Sync.waitPageLoad();
				Thread.sleep(3000);
				if(Common.getCurrentURL().contains("stage3"))
                {
                    Sync.waitPageLoad();
                    String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
                    System.out.println(message);
                    Common.assertionCheckwithReport(message.contains("has been added to your Favorites"),
                            "validating the  product add to the Favorites", "Product should be add to Favorites",
                            "Sucessfully product added to the Favorites ", "failed to add product to the Favorites");

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
				Common.assertionCheckwithReport(Common.getPageTitle().equals("My Wish List"),
						"validating the Navigation to the My Favorites page",
						"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
						"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA",
						"Failed to Navigate to the My Favorites page after Clicking on My Favorites button");
				}
				Common.scrollIntoView("xpath", "(//img[contains(@class,'m-produc')])[1]");
				Sync.waitElementPresent(30, "xpath", "(//img[contains(@class,'m-produc')])[1]");
				Common.mouseOver("xpath", "(//img[contains(@class,'m-produc')])[1]");

				Sync.waitElementPresent(30, "xpath", "//button[@id='product-addtocart-button']");
				Common.clickElement("xpath", "//button[@id='product-addtocart-button']");

				Sync.waitPageLoad();
				Thread.sleep(4000);
				String message1 = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
						.getAttribute("data-ui-id");
				System.out.println(message1);
				Common.assertionCheckwithReport(message1.contains("success"), "validating the  product add to the cart",
						"Product should be add to cart", "Sucessfully product added to the cart ",
						"failed to add product to the cart");
				minicart_Checkout();

			} else {
				Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
				Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String message1 = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
						.getAttribute("data-ui-id");
				System.out.println(message1);
				Common.assertionCheckwithReport(message1.contains("success"), "validating the  product add to the cart",
						"Product should be add to cart", "Sucessfully product added to the cart ",
						"failed to add product to the cart");
				minicart_Checkout();

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
					checkout.equals(minicart) && Common.getCurrentURL().contains("checkout/#shipping") || Common.getCurrentURL().contains("/checkout/#payment") ,
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

	public void RegaddDeliveryAddress(String dataSet) {
		// TODO Auto-generated method stub
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

//				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
						data.get(dataSet).get("City"));
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

				if(Common.getCurrentURL().contains("stage3") || Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("na.osprey"))
                {
				  Thread.sleep(4000);
                    Common.scrollIntoView("xpath", "//select[@name='region_id']");
                    Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
                    Thread.sleep(3000);
                    String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']")
                            .getAttribute("value");
                    System.out.println(Shippingvalue);
                }
			else
			{
			Common.scrollIntoView("xpath", "//input[@placeholder='State/Province']");
			Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", data.get(dataSet).get("Region"));
			
			Thread.sleep(3000);
			String Shippingvalue = Common.findElement("xpath", "//input[@placeholder='State/Province']")
					.getAttribute("value");
			System.out.println(Shippingvalue);
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

	public void selectshippingmethod(String Dataset) {
		// TODO Auto-generated method stub4
		String method = data.get(Dataset).get("methods");
		System.out.println(method);

		try {
			Thread.sleep(3000);
			int size = Common.findElements("xpath", "//label[@class='a-radio-button__label']").size();
			System.out.println(size);
			if (size > 0) {
				// Sync.waitElementPresent(30, "xpath", "//td[contains(text(),'" + method +
				// "')]");
				Common.clickElement("xpath", "(//strong[contains(text(),'" + method + "')])[1]");
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
			Thread.sleep(4000);
			Common.clickElement("xpath", "//button[@data-role='opc-continue']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("checkout/#payment"),
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
		try {
			Common.actionsKeyPress(Keys.END);
			Common.scrollIntoView("xpath", "//div[contains(@class,'ugc instagram')]");
			Sync.waitElementPresent("xpath", "//div[@class='y-image-overlay ']");
			Common.scrollIntoView("xpath", "//div[@class='y-image-overlay ']");
			Common.clickElement("xpath", "//div[@class='y-image-overlay ']");
			// Thread.sleep(6000);
			String yopto = Common.findElement("xpath", "//a[@class='yotpo-logo-link-new']").getAttribute("aria-label");
			// Thread.sleep(6000);
			System.out.println(yopto);
			WebElement UGC = Common.findElement("xpath", "//a[@class='yotpo-logo-link-new']//span");
			Thread.sleep(6000);
			Common.scrollIntoView(UGC);
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

	public void Find_a_delear() {
		// TODO Auto-generated method stub
		try {
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
			// Common.clickElement("xpath", "//div[contains(@aria-label,"DICK'S Sporting
			// ")]");

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
			// Common.switchFrames("xpath",
			// "//iframe[contains(@id,'lcly-embedded-iframe')]");
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
			Common.textBoxInput("xpath", "//input[@name='username']", data.get(dataSet).get("UserName"));
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//span[text()='Toggle password visibility']");
			String shipping = Common.findElement("xpath", "(//span[text()='Shipping'])[1]").getText();
			System.out.println(shipping);
			Common.clickElement("xpath", "//span[text()='Items in Cart']");
			String QTY = Common.findElement("xpath", "(//span[@class='a-product-attribute__value'])[1]").getText();
			System.out.println(QTY);
			String Price = Common.findElement("xpath", "(//span[@class='a-product-attribute__value'])[2]").getText();
			System.out.println(Price);
			Common.clickElement("xpath", "(//span[text()='View Details'])[2]");
			String Color = Common.findElement("xpath", "(//span[@class='a-product-attribute__value'])[3]").getText();
			System.out.println(Color);
			String Size = Common.findElement("xpath", "(//span[@class='a-product-attribute__value'])[4]").getText();
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
		String Productsize = data.get(Dataset).get("Size");
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
			Sync.waitElementPresent("xpath", "//div[@data-option-label='" + Productsize + "']");
			Common.clickElement("xpath", "//div[@data-option-label='" + Productsize + "']");

			product_quantity(Dataset);
			// click_UGC();
			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");

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

	public void Bagpacks_headerlinks(String Dataset) {
		// TODO Auto-generated method stub
		String expectedResult = "User should click the" + Dataset;
		String out = data.get(Dataset).get("outdoor");
		String Trail = data.get(Dataset).get("Trail");
		String header=data.get(Dataset).get("header");
		try {

			Thread.sleep(3000);
			Sync.waitElementPresent("xpath",
					"//a[contains(@class,'level-top')]//span[contains(text(),'"+ header +"')]");
			
			Common.clickElement("xpath", "//a[contains(@class,'level-top')]//span[contains(text(),'" + header + "')]");

			Thread.sleep(3000);

			try {
				Common.mouseOver("xpath", "//span[contains(text(),'"+ header +"')]");
			} catch (Exception e) {
				Common.clickElement("xpath", "//a[@class='level-top ui-corner-all']//span[text()='"+ header +"']");
			}
			Common.clickElement("xpath", "//span[contains(text(),' " + out + "')]");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//span[contains(text(),'" + Trail + "')]");
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

	public void Signin_Checkoutpage(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementVisible("xpath", "//input[@type='email']");
			Common.textBoxInput("xpath", "//input[@type='email']", data.get(Dataset).get("Email"));
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//input[@name='password']");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			Common.clickElement("xpath", "//span[text()='Sign In']");
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

	public void share_whishlist(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			int MyFavorites = Common.findElements("xpath", "//form[@class='form-wishlist-items']//div[contains(@class,'message')]//span").size();

			if (MyFavorites != 0) {
				search_product("Product");
				Common.mouseOver("xpath", "//button[@data-action='add-to-wishlist']");
				Sync.waitElementPresent(30, "xpath", "//button[@data-action='add-to-wishlist']");
				Common.javascriptclickElement("xpath", "//button[@data-action='add-to-wishlist']");
				if(Common.getCurrentURL().contains("stage3"))
                {
                    Sync.waitPageLoad();
                    String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
                    System.out.println(message);
                    Common.assertionCheckwithReport(message.contains("has been added to your Favorites"),
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

		if (!url.contains("stage") && !url.contains("na.osprey")) {
		}

		else {
			try {
				String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();

				// Tell_Your_FriendPop_Up();
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
				if (Common.getCurrentURL().contains("na.osprey.com") || Common.getCurrentURL().contains("stage") ) {

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

	public void Register_userorder_status() {
		// TODO Auto-generated method stub
		click_singinButton();
		Login_Account("Account");
		click_Myorders();

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

	public void click_Myorders() {
		try {
			Sync.waitElementVisible(40, "xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "(//ul[@class='m-account-nav__links']//a)[3]");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Orders and Returns") || Common.getPageTitle().equals("My Orders") || Common.getCurrentURL().contains("order/history/"),
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

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the order summary and UGC carasol ",
					"After Clicking on view Order it should be navigate to the order page and UGC carasol should be displayed",
					"Unable to Navigate to the orders page and UGC Casrol is not displayed ",
					Common.getscreenShot("Failed to Navigate to the orders page and UGC Casrol is not displayed "));
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
				System.out.println(number);
				System.out.println(Dataset);
				Thread.sleep(4000);
				Common.assertionCheckwithReport(number.contains("4242") && Dataset.contains("4242"),
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
			
			  if(Common.getCurrentURL().contains("stage3"))
              {
                  Common.scrollIntoView("xpath", "//select[@name='region_id']");
                  Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
                  Thread.sleep(3000);
                  String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']")
                          .getAttribute("value");
                  System.out.println(Shippingvalue);
              }
			  else
			  {
				Common.scrollIntoView("xpath", "//input[@placeholder='State/Province']");
				Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", data.get(dataSet).get("Region"));
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

	public void Paymentcreditcard_WithInvalidData(String dataSet) throws Exception {
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String cardnumber = data.get(dataSet).get("cardNumber");
		System.out.println(cardnumber);
		String expectedResult = "land on the payment section";
		// Common.refreshpage();

		try {
			Sync.waitPageLoad();

			Sync.waitElementPresent("xpath", "//label[@for='stripe_payments']");
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
					Common.clickElement("xpath", "//button[@class='action primary checkout']");
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
					Common.clickElement("xpath", "//button[@class='action primary checkout']");
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

	public void gustuserorderStatus(String dataSet) {
		// TODO Auto-generated method stub
		click_trackorder();
		String ordernumber = data.get(dataSet).get("OrderID");
		String prodordernumber = data.get(dataSet).get("prod order");

		try {
			if (Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("stage3") ) {
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
			Sync.waitElementPresent(30, "xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//a[text()='Track my order']");
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

//			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			if(Common.getCurrentURL().contains("stage3"))
            {
			  Thread.sleep(4000);
                Common.scrollIntoView("xpath", "//select[@name='region_id']");
                Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
                Thread.sleep(3000);
                String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']")
                        .getAttribute("value");
                System.out.println(Shippingvalue);
            }
			else
			{
				Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", data.get(dataSet).get("Region"));
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

	}

	public void createAccountFromOrderSummaryPage(String Dataset) {
		// TODO Auto-generated method stub
		try {

			Common.clickElement("xpath", "//input[@name='password']");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			Common.clickElement("xpath", "(//span[text()='Toggle Password Visibility'])[1]");
			Sync.waitElementPresent(10, "xpath", "//input[@name='password_confirmation']");
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
					Greencolor.equals("#004496") && Message1.contains("validation icon")
							&& confirmpassword.equals("text") && password.equals("text")
							&& accounttext.contains("Create an Account"),
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

	public void clickontheproduct_and_image(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		System.out.println(product);
		String color = data.get(Dataset).get("Colorproduct");
		System.out.println(color);
		try {
			String minicartproduct = Common
					.findElement("xpath", "//a[@class='a-product-name' and @title='" + product + "']").getText();
			Common.clickElement("xpath", "//a[@class='a-product-name' and @title='" + product + "']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			System.out.println(minicartproduct);
			Common.assertionCheckwithReport(product.contains(minicartproduct),
					"validating the product navigating to the PDP page",
					"The product Should be navigates to the PDP page", "Successfully product navigates to the PDP page",
					"Failed to Navigates Product to the PDP page");
			click_minicart();
			String minicartimage = Common.findElement("xpath", "//img[contains(@alt,'" + color + "')]")
					.getAttribute("alt");
			Common.clickElement("xpath", "//img[contains(@alt,'" + color + "')]");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.assertionCheckwithReport(minicartimage.contains(color),
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

	public void minicart_delete(String Dataset) {
		// TODO Auto-generated method stub
		String deleteproduct = data.get(Dataset).get("Colorproduct");
		String symbol=data.get(Dataset).get("Symbol");
		try {
			Sync.waitElementPresent(30, "xpath", "//span[@class='c-mini-cart__subtotal-amount']//span");
			String subtotal = Common.getText("xpath", "//span[@class='c-mini-cart__subtotal-amount']//span")
					.replace(symbol, "");
			Float subtotalvalue = Float.parseFloat(subtotal);
			String productname = Common
					.findElement("xpath", "(//div[@class='m-mini-product-card__info']//a[@class='a-product-name'])[1]")
					.getText();
			String productamount1 = Common.getText("xpath", "(//span[@class='minicart-price']//span)[1]").replace(symbol,
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
					.replace(symbol, "");
			Float subtotal1value = Float.parseFloat(subtotal1);
			Thread.sleep(8000);
			String productamount = Common.getText("xpath", "//span[@class='minicart-price']//span").replace(symbol, "");
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
		String UpdataedQuntityinminicart = data.get(Dataset).get("Quantity");
		String symbol=data.get(Dataset).get("Symbol");
		try {

			String Subtotal = Common.getText("xpath", "//span[@class='c-mini-cart__subtotal-amount']//span")
					.replace(symbol, "");
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
					.replace(symbol, "");
			Float subtotalvalue2 = Float.parseFloat(Subtotal2);
			Float Total = subtotalvalue * 2;
			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			System.out.println(UpdataedQuntityinminicart);
			System.out.println(cart);
			System.out.println(ExpectedTotalAmmount2);
			System.out.println(Subtotal2);
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

	public void review(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.clickElement("xpath", "//img[@alt='" + products + "']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			more_Quantity("review");
			Common.scrollIntoView("xpath", "//a[text()='Reviews']");
			Sync.waitElementPresent("xpath", "//a[@id='tab-label-product.yotpo.reviews-title']");
			Thread.sleep(3000);
			String form = Common.getText("xpath", "//a[@id='tab-label-product.yotpo.reviews-title']");
			System.out.println(form);
			Common.assertionCheckwithReport(form.equals("REVIEWS"), "verifying the write a review button",
					"Write a review should be appear in the PDP page",
					"Sucessfully write a review button has been displayed in PDP page",
					"Failed to display the write a review button in PDP page");
			Common.clickElement("xpath", "//a[text()='Reviews']");
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
			// Common.clickElement("xpath", "//div[@aria-label='Next']");

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
		String Question = data.get(Dataset).get("CommetsOsprey");
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
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@placeholder='State/Province']",
							data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					Thread.sleep(3000);
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@placeholder='State/Province']",
							data.get(dataSet).get("Region"));
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
			Common.clickElement("xpath", "//span[contains(text(),'Change Billing Address')]");

			try {
				Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//input[@title='Phone Number']", data.get(dataSet).get("phone"));
				Common.textBoxInput("xpath", "//input[@title='Address Line 1']", data.get(dataSet).get("Street"));

				try {
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@placeholder='State/Province']",
							data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {

					Thread.sleep(3000);
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@placeholder='State/Province']",
							data.get(dataSet).get("Region"));
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

	public void Add_NewAddress(String dataSet) {
		// TODO Auto-generated method stub

		try {
			Sync.waitElementVisible(30, "xpath", "//button[@title='Add New Address']");
			Common.clickElement("xpath", "//button[@title='Add New Address']");
			String newaddress = Common.findElement("xpath", "//h1[@class='page-title-wrapper h2']").getText();
			if (newaddress.contains("Add New Address")) {
				Common.textBoxInput("xpath", "//input[@title='Phone Number']", data.get(dataSet).get("phone"));
				Common.textBoxInput("xpath", "//input[@title='Address Line 1']", data.get(dataSet).get("Street"));
				Common.textBoxInput("xpath", "//input[@title='City']", data.get(dataSet).get("City"));
				try {
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@placeholder='State/Province']",
							data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					Thread.sleep(3000);
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@placeholder='State/Province']",
							data.get(dataSet).get("Region"));
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
			} else {
				Assert.fail();
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the No address in the address book",
					"After saving the address for Register user no extra address should be there in address book",
					"Unable to see no address in the address book",
					Common.getscreenShotPathforReport("Failed to see no address in the address book"));
			Assert.fail();
		}
	}

	public void Add_Address(String dataSet) {
		// TODO Auto-generated method stub

		try {
			Sync.waitElementVisible(30, "xpath", "//a[text()='Address Book']");
			Common.clickElement("xpath", "//a[text()='Address Book']");
			Thread.sleep(4000);
			Common.textBoxInput("xpath", "//input[@title='Phone Number']", data.get(dataSet).get("phone"));
			Common.textBoxInput("xpath", "//input[@title='Address Line 1']", data.get(dataSet).get("Street"));
			Common.textBoxInput("xpath", "//input[@title='City']", data.get(dataSet).get("City"));
			Thread.sleep(4000);
			 if(Common.getCurrentURL().contains("stage3"))
             {
                 
                 Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
                 Thread.sleep(3000);
                 String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']")
                         .getAttribute("value");
                 System.out.println(Shippingvalue);
             }
			 else
			 {
				Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", data.get(dataSet).get("Region"));
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

			String newaddress = Common.findElement("xpath", "//div[@class='block-content']//P").getText();
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
				Thread.sleep(4000);
				if(Common.getCurrentURL().contains("stage3"))
	             { 
	                 Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(Dataset).get("Region"));  
	             }
				else
				{
				Common.clickElement("xpath", "//input[@name='region']");
				Common.textBoxInput("xpath", "//input[@name='region']", region);
				}
				Common.clickElement("xpath", "//input[@name='postcode']");
				Common.textBoxInput("xpath", "//input[@name='postcode']", zipcode);
				Common.clickElement("xpath", "//label[@for='primary_shipping']");
				Common.clickElement("xpath", "//button[@title='Save Address']");
				String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();

				Common.assertionCheckwithReport(message.equals("You saved the address."),
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
			Common.clickElement("xpath", "//a[@title='Change Shipping Address']");
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
			if(Common.getCurrentURL().contains("stage3"))
            { 
                Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(Dataset).get("Region"));  
            }
			else
			{
			Common.clickElement("xpath", "//input[@placeholder='State/Province']");
			Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", region);
			}
			Common.clickElement("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']", zipcode);
			String checkbox = Common.findElement("xpath", "//input[@id='primary_billing']").getAttribute("type");
			String text = Common.findElement("xpath", "//div[@class='message info']//span").getText();
			Common.assertionCheckwithReport(
					checkbox.equals("checkbox") && text.equals("This is your default shipping address."),
					"validating the checkbox for billing address and text for the shipping address",
					"Checkbox should be display for the billing address and text should be display for the shipping address",
					"Sucessfully checkbox is displayed for the billing address and text is displayed for the shipping address",
					"Failed to display checkbox for billing address and fail to display text" + text
							+ "for shipping address");
			Common.clickElement("xpath", "//input[@id='primary_billing']");
			Common.clickElement("xpath", "//button[@title='Save Address']");
			Sync.waitElementPresent("xpath", "//div[@data-ui-id='message-success']//div");
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
			Common.assertionCheckwithReport(message.equals("You saved the address."),
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

			String newaddress = Common.findElement("xpath", "//div[@class='block-content']//P").getText();
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
				if(Common.getCurrentURL().contains("stage3"))
	             { 
	                 Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(Dataset).get("Region"));  
	             }
				else
				{
				Common.clickElement("xpath", "//input[@placeholder='State/Province']");
				Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", region);
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
			Common.clickElement("xpath", "//a[@title='Change Billing Address']");
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
			if(Common.getCurrentURL().contains("stage3"))
            { 
                Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(Dataset).get("Region"));  
            }
			else
			{
			Common.clickElement("xpath", "//input[@placeholder='State/Province']");
			Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", region);
			}
			Common.clickElement("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']", zipcode);
			// String checkbox = Common.findElement("xpath",
			// "//input[@id='primary_shipping']").getAttribute("type");
			String text = Common.findElement("xpath", "//div[@class='message info']//span").getText();
			System.out.println(text);
			Common.assertionCheckwithReport(
					/* checkbox.equals("checkbox") && */ text.equals("This is your default billing address."),
					"validating the checkbox for billing address and text for the shipping address",
					"Checkbox should be display for the billing address and text should be display for the shipping address",
					"Sucessfully checkbox is displayed for the billing address and text is displayed for the shipping address",
					"Failed to display checkbox for billing address and fail to display text" + text
							+ "for shipping address");
			Common.clickElement("xpath", "//button[@title='Save Address']");
			Sync.waitElementPresent("xpath", "//div[@data-ui-id='message-success']//div");
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
			String shippingaddress = Common
					.findElement("xpath", "//div[contains(@class,'box box-address-bil')]//address").getText();
			System.out.println(shippingaddress);
			System.out.println(shipping);
			Common.assertionCheckwithReport(
					shippingaddress.equals(shipping) && message.equals("You saved the address."),
					"validating the checkbox for shipping address and text for the billing address",
					"Checkbox should be display for the shipping address and text should be display for the billing address",
					"Sucessfully checkbox is displayed for the shipping address and text is displayed for the billing address",
					"Failed to display checkbox for shipping address and fail to display text" + text
							+ "for billing address");
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
			String addressbook = Common.findElement("xpath", "//span[@class='toolbar-number']").getText();
			System.out.println(addressbook);
			if (addressbook.contains("1 Item")) {
				Common.clickElement("xpath", "//span[text()='Edit']");
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
				if(Common.getCurrentURL().contains("stage3"))
	             { 
	                 Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(Dataset).get("Region"));  
	             }
				else
				{
				Common.clickElement("xpath", "//input[@placeholder='State/Province']");
				Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", region);
				}
				Common.clickElement("xpath", "//input[@name='postcode']");
				Common.textBoxInput("xpath", "//input[@name='postcode']", zipcode);
				// Common.clickElement("xpath", "//label[@for='primary_shipping']");
				Common.clickElement("xpath", "//button[@title='Save Address']");
				String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();

				Common.assertionCheckwithReport(message.equals("You saved the address."),
						"validating the saved message after saving address in address book",
						"Save address message should be displayed after the address saved in address book",
						"Sucessfully address has been saved in the address book",
						"Failed to save the address in the address book");
				Thread.sleep(4000);
				Common.scrollIntoView("xpath", "//span[text()='Delete']");
				Sync.waitElementPresent("xpath", "//span[text()='Delete']");
				Common.clickElement("xpath", "//span[text()='Delete']");
				Thread.sleep(4000);
				String popmessage = Common.findElement("xpath", "//div[contains(text(),'Are you ')]").getText();
				if (popmessage.contains("Are you sure you want to delete this address?")) {
					Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");
					Common.clickElement("xpath", "//span[contains(text(),'OK')]");
					String Delmessage = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div")
							.getText();

					Common.assertionCheckwithReport(Delmessage.equals("You deleted the address."),
							"validating the Delete message after Deleting address in address book",
							"Delete address message should be displayed after the address delete in address book",
							"Sucessfully address has been Deleted in the address book",
							"Failed to Delete the address in the address book");
				} else {
					Assert.fail();
				}

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
			if (Common.getCurrentURL().contains("stage")) {
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
				Common.assertionCheckwithReport(productprice.equals(PLPprice),
						"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
						"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//a[text()='Notify me when this product is in stock']");
				Sync.waitPageLoad(40);
				Thread.sleep(5000);
				String newsubcribe = Common.findElement("xpath", "//div[@class='a-message__container-inner']")
						.getText();
				System.out.println(newsubcribe);
				Common.assertionCheckwithReport(
						newsubcribe.contains("Alert subscription has been saved.")
								|| newsubcribe.contains("Thank you! You are already subscribed to this product."),
						"verifying the out of stock subcription",
						"after click on subcribe button message should be appear",
						"Sucessfully message has been displayed when we click on the subcribe button ",
						"Failed to display the message after subcribtion");
				Common.actionsKeyPress(Keys.END);
				Common.clickElement("xpath", "//a[text()='Notify me when this product is in stock']");
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

	public void My_order_subcribtion(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("oss Product");
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

	public void remove_outofstock_subcribtion(String Dataset) {
		// TODO Auto-generated method stub
		try {
			String price = Common.findElement("xpath", "//span[@data-price-type='finalPrice']")
					.getAttribute("data-price-amount");
			if (price.equals(Dataset)) {
				Thread.sleep(3000);
				Common.clickElement("xpath", "(//span[text()='Remove'])[2]");
				Common.maximizeImplicitWait();
				Common.alerts("Cancel");
				Thread.sleep(4000);
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
			if (Common.getCurrentURL().contains("stage")) {
				Thread.sleep(3000);
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

	public void Navigate_back_to_Shoppingcart_page(String Dataset) {
		// TODO Auto-generated method stub

		try {
			Sync.waitElementVisible(30, "xpath", "//span[text()='Back to Cart']");
			Common.clickElement("xpath", "//span[text()='Back to Cart']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Shopping Cart"),
					"validating the navigates to the shopping cart page",
					"After clicking on the reorder it should navigate to the shopping cart page",
					"Successfully navigated to the shopping cart page", "Failed to Navigate to the shopping cart page");

			String Cart = Common.findElement("xpath", "//span[@class='t-cart__items-count']").getText()
					.replace(" Item(s)", "");
			System.out.println(Cart);
			Sync.waitElementVisible(30, "xpath", "//button[@data-role='proceed-to-checkout']");
			Common.clickElement("xpath", "//button[@data-role='proceed-to-checkout']");
			Sync.waitPageLoad();
			Thread.sleep(7000);
			Sync.waitElementPresent(30, "xpath", "//strong[@role='heading']");
			String checkout = Common.findElement("xpath", "//span[contains(@data-bind,'text: getC')]").getText();
			System.out.println(checkout);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(
					checkout.equals(Cart) && Common.getCurrentURL().contains("checkout/#shipping"),
					"validating the navigation to the shipping page when we click on the checkout",
					"User should able to navigate to the shipping  page", "Successfully navigate to the shipping page",
					"Failed to navigate to the shipping page");
			selectshippingmethod(Dataset);
			clickSubmitbutton_Shippingpage();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the navigation to the shipping page when we click on the checkout ",
					"User should able to navigate to the shipping  page", "unable to navigate to the shipping page",
					Common.getscreenShot("Failed to navigate to the shipping page"));
			Assert.fail();

		}
	}

	public void Continue_Shopping() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementVisible("xpath", "//span[@class='a-btn-tertiary__label']");
			Common.clickElement("xpath", "//span[@class='a-btn-tertiary__label']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			verifingHomePage();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating store logo on the homwpage",
					"System directs the user to the Homepage and store logo should display",
					"Unable to navigate to the homepage and logo is not displayed",
					"Failed to navigate to the homepage and logo is not displayed");
			Assert.fail();
		}

	}

	public void MyFavorites_Guestuser(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		System.out.println(product);
		String productcolor = data.get(Dataset).get("Color");
		System.out.println(productcolor);
		String Productsize = data.get(Dataset).get("Size");
		System.out.println(Productsize);
		try

		{
			search_product("Product");
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product + "']");
			Common.clickElement("xpath", "//img[@alt='" + product + "']");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//div[@aria-label='" + productcolor + "']");
			Common.clickElement("xpath", "//div[@aria-label='" + productcolor + "']");
			Sync.waitElementPresent("xpath", "//div[@data-option-label='" + Productsize + "']");
			Common.clickElement("xpath", "//div[@data-option-label='" + Productsize + "']");
			Sync.waitElementPresent(30, "xpath", "//button[@data-action='add-to-wishlist']");
			Common.clickElement("xpath", "//button[@data-action='add-to-wishlist']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Customer Login")
							&& message.contains("You must login or register to add items"),
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

	public void click_Createaccount() {

	
		try {
			Sync.waitElementPresent("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "(//li[@class='nav item']//a)[1]");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("customer"),
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

	public String create_account_with_fav(String Dataset) {
		// TODO Auto-generated method stub
		String email = "";
		String product = data.get(Dataset).get("Products");
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
			
			
			if(Common.getCurrentURL().contains("stage3")) {
				String message = Common.findElement("xpath", "(//div[@class='a-message__container-inner'])[1]").getText();
				String favmessage = Common.findElement("xpath", "(//div[@class='a-message__container-inner'])[2]")
						.getText();
				System.out.println("favmessage"+favmessage);
				System.out.println("Register message"+ message);
			
				Thread.sleep(4000);
				Common.assertionCheckwithReport(
						Common.getPageTitle().equals("My Favorites")
								&& message.contains("Thank you for registering with Osprey") 
								&& favmessage.contains(product + " has been added to your Favorites. Click "),
						"validating the  My Favorites page Navigation when user clicks on signin button",
						"User should able to navigate to the My Favorites page after clicking on Signin button",
						"Sucessfully navigate to the My Favorites page after clicking on signin button ",
						"Failed to navigates to My Favorites Page after clicking on Signin button");
		
			}
			else {
				String message = Common.findElement("xpath", "(//div[@class='a-message__container-inner'])[1]").getText();
				String favmessage = Common.findElement("xpath", "(//div[@class='a-message__container-inner'])[2]")
						.getText();
				System.out.println(favmessage);
				Thread.sleep(4000);
				Common.assertionCheckwithReport(
						Common.getPageTitle().equals("My Wish List")
								&& message.contains("Thank you for registering with Osprey") 
								&& favmessage.contains(product + " has been added to your Wish List. Click"),
						"validating the  My Favorites page Navigation when user clicks on signin button",
						"User should able to navigate to the My Favorites page after clicking on Signin button",
						"Sucessfully navigate to the My Favorites page after clicking on signin button ",
						"Failed to navigates to My Favorites Page after clicking on Signin button");
			}
			
			
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

	public void minicart_products(String minicart) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
			Common.javascriptclickElement("xpath", "//span[contains(@class,'c-mini-cart__icon')]");

			Sync.waitElementPresent(30, "xpath", "//span[@class='c-mini-cart__counter']");
			String cartproducts = Common.findElement("xpath", "//span[@class='c-mini-cart__counter']").getText();

			Common.assertionCheckwithReport(cartproducts.equals(minicart),
					"validating the products in the cart after creating new account ",
					"Products should be displayed in the mini cart after Create account with Cart",
					"Sucessfully after create account with cart products should be display in mini cart",
					"failed to display the products in mini cart after the create account with cart");

			Sync.waitElementVisible("xpath", "//div[contains(@class,'c-mini-cart__close-btn')]");
			Common.clickElement("xpath", "//div[contains(@class,'c-mini-cart__close-btn')]");

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

	public String BillingAddress(String dataSet) {
		// TODO Auto-generated method stub
		String update = "";
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//label[@for='stripe_payments']");
			Common.clickElement("xpath", "//label[@for='stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='stripe_payments']").size();
			Common.clickElement("xpath", "//label[@for='stripe_payments']");
			Common.assertionCheckwithReport(sizes > 0, "Validating the payment section page",
					"payment section should be displayed", "sucessfully payment section has been displayed",
					"Failed to displayed the payment section");
			Sync.waitElementPresent(30, "xpath", "//label[contains(@for,'billing-address')]//span");
			Common.clickElement("xpath", "//label[contains(@for,'billing-address')]//span");
			Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
			Thread.sleep(4000);
			String text = Common.findElement("xpath", "//input[@name='street[0]']").getAttribute("value");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
			System.out.println(data.get(dataSet).get("City"));

//			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			 if(Common.getCurrentURL().contains("stage3"))
             {
				  Thread.sleep(4000);
                 Common.scrollIntoView("xpath", "//select[@name='region_id']");
                 Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
                 Thread.sleep(3000);
                 String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']")
                         .getAttribute("value");
                 System.out.println(Shippingvalue);
             }
			 else
			 {
				 Common.scrollIntoView("xpath", "//input[@placeholder='State/Province']");
				Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			// Common.textBoxInputClear("xpath", "//input[@name='postcode']");
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
					update.contains("Connecticut") || text.contains("Wallingford"),
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
		return update;
	}

	public void verify_BillingAddress(String Dataset) {
		// TODO Auto-generated method stub

		try {
			Sync.waitElementPresent("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Sync.waitElementPresent("xpath", "//a[text()='My Account']");
			Common.clickElement("xpath", "//a[text()='My Account']");
			Common.scrollIntoView("xpath", "(//div[contains(@class,'box box-billing')]//br)[1]");
			String Address = Common.findElement("xpath", "(//div[contains(@class,'box box-billing')]//br)[1]")
					.getText();
			System.out.println(Address);
			System.out.println(Dataset);
			Common.assertionCheckwithReport(Address.equals(Dataset) || Dataset.contains("935 The Horsley Dr"),
					"verifying the Billing address form in Address book",
					"Billing address should be saved in the Address book",
					"Sucessfully Billing address form should be Displayed in the Address book",
					"Failed to display the Billing address in Address book");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the Billing address form in Address book",
					"Billing address should be saved in the Address book",
					"Unable to display the Billing address in Address book",
					Common.getscreenShotPathforReport("Failed to display the Billing address in Address book"));
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

				if(Common.getCurrentURL().contains("stage3"))
                {
				  Thread.sleep(4000);
                    Common.scrollIntoView("xpath", "//select[@name='region_id']");
                    Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
                    Thread.sleep(3000);
                    String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']")
                            .getAttribute("value");
                    System.out.println(Shippingvalue);
                }
			else
			{
			Common.scrollIntoView("xpath", "//input[@placeholder='State/Province']");
			Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", data.get(dataSet).get("Region"));
			Thread.sleep(3000);
			String Shippingvalue = Common.findElement("xpath", "//input[@placeholder='State/Province']")
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

	public void Edit_Address_verify(String dataSet) {
		// TODO Auto-generated method stub
		String firstname = data.get(dataSet).get("FirstName");
		System.out.println(firstname);
		try {
			Thread.sleep(4000);

			Common.javascriptclickElement("xpath",
					"//div[contains(@class,'shipping-address-item n')]//span[text()='Edit Address']");
			String expectedResult = "shipping address is entering in the fields";

			try {
				Common.clickElement("xpath", "//div[@class='new-address-popup']//button");
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

				Common.scrollIntoView("xpath", "//input[@placeholder='State/Province']");
				Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", data.get(dataSet).get("Region"));
				Thread.sleep(3000);
				String Shippingvalue = Common.findElement("xpath", "//input[@placeholder='State/Province']")
						.getAttribute("value");

				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));

				try {
					Common.textBoxInput("xpath", "//input[@placeholder='State/Province']",
							data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {

					Thread.sleep(2000);
					Common.textBoxInput("xpath", "//input[@placeholder='State/Province']",
							data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);
				Common.textBoxInputClear("xpath", "//input[@name='postcode']");
				Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
				String ShippingZip = Common.findElement("name", "postcode").getAttribute("value");
				System.out.println("*****" + ShippingZip + "*******");

				Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath",
						"//footer[@class='modal-footer']//span[contains(text(),'Save Address')]");

				Common.javascriptclickElement("xpath",
						"//footer[@class='modal-footer']//span[contains(text(),'Save Address')]");

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
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating adding  address", "User should able to add the address",
					"User unabel add shipping address",
					Common.getscreenShotPathforReport("failed to save the shipping address"));
			Assert.fail();

		}

	}

	public void Verify_Address(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//img[@alt='Logo']");
			Sync.waitElementPresent(30, "xpath", "//button[@aria-controls='desktop-account-nav']");
			Common.clickElement("xpath", "//button[@aria-controls='desktop-account-nav']");
			Common.clickElement("xpath", "//a[text()='My Account']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"),
					"verifying the My account navigation",
					"after clicking on the my account it should navigate to the My Account page",
					"Sucessfully Navigated to the My Account page", "Failed to navigate to the my account page");
			Common.clickElement("xpath", "//a[text()='Address Book']");
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Address Book"),
					"verifying the Address Book page navigation",
					"after clicking on the Address Book it should navigate to the Address Book page",
					"Sucessfully Navigated to the Address Book page", "Failed to navigate to the Address Book page");
			Common.scrollIntoView("xpath", "//td[@data-header-title='Street Address:']");
			String shippingaddress = Common.findElement("xpath", "//td[@data-header-title='Street Address:']")
					.getText();
			System.out.println(shippingaddress);
			System.out.println(Dataset);
			Common.assertionCheckwithReport(
					shippingaddress.equals(Dataset) || shippingaddress.contains("844 N Colony Rd"),
					"verifying the address added to the address book",
					"after saving the address in shiiping page it should save in the address book",
					"Sucessfully Address ha been saved in the address book",
					"Failed to save the address in the address book");
			Common.scrollIntoView("xpath", "//span[text()='Delete']");
			Sync.waitElementPresent("xpath", "//span[text()='Delete']");
			Common.clickElement("xpath", "//span[text()='Delete']");
			Thread.sleep(4000);
			String popmessage = Common.findElement("xpath", "//div[contains(text(),'Are you ')]").getText();
			if (popmessage.contains("Are you sure you want to delete this address?")) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");
				Common.clickElement("xpath", "//span[contains(text(),'OK')]");
				String Delmessage = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();

				Common.assertionCheckwithReport(Delmessage.equals("You deleted the address."),
						"validating the Delete message after Deleting address in address book",
						"Delete address message should be displayed after the address delete in address book",
						"Sucessfully address has been Deleted in the address book",
						"Failed to Delete the address in the address book");
			} else {
				Assert.fail();
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Delete message after Deleting address in address book",
					"Delete address message should be displayed after the address delete in address book",
					"Unable to Delete the address in the address book",
					Common.getscreenShotPathforReport("Failed to Delete the address in the address book"));
			Assert.fail();
		}

	}

	public void outofstock_subcription(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		System.out.println(products);
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
			if (Common.getCurrentURL().contains("stage")) {
				Sync.waitElementPresent(30, "xpath", "//img[contains(@alt,'" + products + "')]");
				String productprice = Common.findElement("xpath", "//span[@class='price-wrapper']")
						.getAttribute("data-price-amount");
				System.out.println(productprice);
				Common.clickElement("xpath", "//img[contains(@alt,'" + products + "')]");
				Sync.waitPageLoad();
				Thread.sleep(3000);
				String PLPprice = Common
						.findElement("xpath",
								"//div[@class='m-product-overview__prices']//span[@class='price-wrapper ']")
						.getAttribute("data-price-amount");
				System.out.println(PLPprice);
				String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
				System.out.println(name);
				Common.assertionCheckwithReport(
						Common.getPageTitle().contains(products) && productprice.equals(PLPprice)
								|| Common.getPageTitle().contains(prod) && productprice.equals(PLPprice),
						"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
						"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
				Common.clickElement("xpath", "//span[text()=' Notify Me When Available']");
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
				Common.clickElement("xpath",
						"//div[@class='sticky-atc__cta-container']//span[text()=' Notify Me When Available']");
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

	public void Loginpage_validation(String dataSet) {
		// TODO Auto-generated method stub

		try {
			Thread.sleep(5000);
			click_singinButton();
			Sync.waitElementPresent("xpath", "//button[contains(@class,'action login')]");
			Common.clickElement("xpath", "//button[contains(@class,'action login')]");
			Sync.waitElementPresent(30, "xpath", "//div[@id='pass-error']");
			String errormessage = Common.findElement("xpath", "//div[@id='pass-error']").getText();
			Common.assertionCheckwithReport(errormessage.contains("This is a required field."),
					"verifying the error message validation with empty fileds",
					"after click on signin button with empty blanks error message should appear",
					"Sucessfully error messsage should be display ", "Failed to display the error message");
			if (Common.getCurrentURL().contains("stage")) {
				Sync.waitPageLoad();
				Common.textBoxInput("id", "email", data.get(dataSet).get("UserName"));
			} else {
				Common.textBoxInput("id", "email", data.get(dataSet).get("Prod UserName"));
			}
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//button[contains(@class,'action login')]");
			Sync.waitPageLoad(40);
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//div[@class='a-message__container-inner']");
			String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			Sync.waitPageLoad(40);
			Thread.sleep(4000);
			Common.assertionCheckwithReport(message.contains("The account sign-in was incorrect"),
					"verifying the error message for invalid password",
					"after click on signin button with empty invalid password error message should appear",
					"Sucessfully error messsage should be display ", "Failed to display the error message");
			if (Common.getCurrentURL().contains("stage")) {
				Sync.waitPageLoad();
				Common.textBoxInput("id", "email", data.get(dataSet).get("unregisterd Username"));
			} else {
				Common.textBoxInput("id", "email", data.get(dataSet).get("Prod UserName"));
			}
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//button[contains(@class,'action login')]");
			Sync.waitPageLoad(40);
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//div[@class='a-message__container-inner']");
			String message1 = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
			Sync.waitPageLoad(40);
			Thread.sleep(4000);
			Common.assertionCheckwithReport(message1.contains("The account sign-in was incorrect"),
					"verifying the error message for invalid password",
					"after click on signin button with un registered email error message should appear",
					"Sucessfully error messsage should be display ", "Failed to display the error message");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the error message for invalid password",
					"after click on signin button with un registered email error message should appear",
					"Unable to display the error message", Common.getscreenShot("Failed to display the error message"));
			Assert.fail();

		}

	}

	public void Sticky_Add_to_Cart(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		String Productsize = data.get(Dataset).get("Size");
		String productcolor = data.get(Dataset).get("Color");
		System.out.println(products);

		String results = Common.findElement("xpath", "//span[@id='algolia-srp-title']").getText();
		System.out.println(results);
		try {

			if (results.contains("Atmos AG 50")) { // need to implement from the Header links After the configurations
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
				Common.actionsKeyPress(Keys.END);
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath",
						"//div[@class='sticky-atc__inner']//div[@aria-label='" + productcolor + "']");
				Common.clickElement("xpath",
						"//div[@class='sticky-atc__inner']//div[@aria-label='" + productcolor + "']");
				Sync.waitElementPresent("xpath",
						"//div[@class='sticky-atc__inner']//div[@data-option-label='" + Productsize + "']");
				Common.clickElement("xpath",
						"//div[@class='sticky-atc__inner']//div[@data-option-label='" + Productsize + "']");
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
				Thread.sleep(4000);
				Common.actionsKeyPress(Keys.HOME);
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
				Common.clickElement("xpath", "//button[@id='product-sticky-addtocart-button']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath", "//div[@data-ui-id='message-success']");
				String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
						.getAttribute("data-ui-id");
				System.out.println(message);
				Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
						"Product should be add to cart", "Sucessfully product added to the cart ",
						"failed to add product to the cart");
				Common.actionsKeyPress(Keys.HOME);
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));

			Assert.fail();
		}
	}

	public void Edit_Name(String Dataset) {
		// TODO Auto-generated method stub

		 String account=data.get(Dataset).get("account");
		try {
			Sync.waitElementPresent("xpath", "//ul[@class='nav items reset-list']//a[text()='"+ account +"']");
			Common.clickElement("xpath", "//ul[@class='nav items reset-list']//a[text()='"+ account +"']");
			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account") || Common.getCurrentURL().contains("account"),
					"validating the my account page navigation",
					"After clicking on my account it should navigate to the My account page",
					"Sucessfully Navigated to the my account page", "failed to Navigate to the My account page");
			Sync.waitElementPresent("xpath", "(//span[@class='a-btn-tertiary__label'])[1]");
			Common.clickElement("xpath", "(//span[@class='a-btn-tertiary__label'])[1]");
			String editaccount = Common.findElement("xpath", "//h1[@class='page-title-wrapper h2']").getText();
			String url=Common.getCurrentURL();
			if (editaccount.contains("Edit Account Information") || url.contains("edit")) {
				Sync.waitElementPresent("xpath", "//button[@class='m-accordion__title name']//span[@class='a-btn-tertiary__label']");
				Common.clickElement("xpath", "//button[@class='m-accordion__title name']//span[@class='a-btn-tertiary__label']");
				Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(Dataset).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(Dataset).get("LastName"));
				Common.clickElement("xpath", "//button[@class='action save primary a-btn a-btn--primary']");
				Sync.waitPageLoad(40);
				Thread.sleep(4000);
				String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
				String message1 = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getAttribute("class");
				Common.assertionCheckwithReport(message.contains("You saved the account information.") || message1.contains("a-message"),
						"validating the edit account information",
						"After clicking oon save changes sucess message should appear",
						"Sucessfully save address suceess message should display",
						"failed to save the data and success message is not displayed");

			} else {
				Assert.fail();
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the edit account information",
					"After clicking on save changes sucess message should appear",
					"Unable to save the data and success message is not displayed",
					Common.getscreenShot("failed to save the data and success message is not displayed"));
			Assert.fail();
		}

	}

	public void Add_Whishlist_PLP(String string) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			int MyFavorites = Common.findElements("xpath", "//div[contains(@class,'message')]//span").size();

			if (MyFavorites != 0) {
				search_product("Product");
				Common.mouseOver("xpath", "//button[@data-action='add-to-wishlist']");
				Sync.waitElementPresent(30, "xpath", "//button[@data-action='add-to-wishlist']");
				Common.javascriptclickElement("xpath", "//button[@data-action='add-to-wishlist']");
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
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the Whishlist",
					"Product should be add to whishlist", "Unable to add product to the Whishlist",
					Common.getscreenShot("failed to add product to the Whishlist"));
			Assert.fail();
		}

	}

	public void Add_Whishlist_PDP(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		System.out.println(product);
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String MyFavorites = Common.findElement("xpath", "//a[@id='wishlist-create-button']//span").getText();
			System.out.println(MyFavorites);

			if (MyFavorites.contains("CREATE NEW WISH LIST")) {
				Bagpacks_headerlinks("Backpacks & Bags");
				Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product + "']");
				Common.clickElement("xpath", "//img[@alt='" + product + "']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath", "//button[@data-action='add-to-wishlist']");
				Common.clickElement("xpath", "//button[@data-action='add-to-wishlist']");
				Sync.waitPageLoad(30);
				Thread.sleep(4000);
				Sync.waitElementVisible(30, "xpath", "//h4");
				String whishlistpopup = Common.findElement("xpath", "//h4").getText();
				System.out.println(whishlistpopup);
				if (whishlistpopup.contains("Add to Wishlist")) {

					Sync.waitElementPresent(30, "xpath", "//label[text()='Create New Wish List']");
					Common.clickElement("xpath", "//label[text()='Create New Wish List']");
					Common.textBoxInput("xpath", "//input[@name='name']", data.get(Dataset).get("whishlist name"));
					Sync.waitElementPresent(30, "xpath", "//button[@title='Add To List']");
					Common.clickElement("xpath", "//button[@title='Add To List']");
				} else {
					Assert.fail();
				}
				Sync.waitPageLoad(40);
				Thread.sleep(4000);
				Common.assertionCheckwithReport(Common.getPageTitle().equals("My Wish List"),
						"validating the Navigation to the My Favorites page",
						"After Clicking on My Favorites CTA user should be navigate to the My Favorites page",
						"Sucessfully User Navigates to the My Favorites page after clicking on the My Favorites CTA",
						"Failed to Navigate to the My Favorites page after Clicking on My Favorites button");
				Common.findElements("xpath", "//span[contains(@class,'a-wishlist')]");
				Sync.waitPageLoad();
				String message = Common.findElement("xpath", "(//div[@data-ui-id='message-success']//div)[4]")
						.getText();
				System.out.println(message);
				String newwhishlist = Common.findElement("xpath", "(//div[@data-ui-id='message-success']//div)[2]")
						.getText();
				System.out.println(newwhishlist);
				Common.assertionCheckwithReport(
						message.contains(product + " has been added to your Wish List. Click ")
								&& newwhishlist.contains("Wish list"),
						"validating the  product add to the Whishlist", "Product should be add to whishlist",
						"Sucessfully product added to the Whishlist ", "failed to add product to the Whishlist");

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the Whishlist",
					"Product should be add to whishlist", "Unable to add product to the Whishlist",
					Common.getscreenShot("failed to add product to the Whishlist"));
			Assert.fail();
		}
	}

	public void Delete_Whishlist() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent(30, "xpath", "//label[@for='wishlist-select-all']");
			Common.clickElement("xpath", "//label[@for='wishlist-select-all']");
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//button[@title='Delete Wish List']");
			Common.clickElement("xpath", "//button[@title='Delete Wish List']");
			Thread.sleep(4000);
			String popup = Common
					.findElement("xpath", "//div[@class='modal-popup confirm _show']//div[@class='modal-content']//div")
					.getText();
			if (popup.contains("Are you sure you want to delete")) {
				Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");
				Common.clickElement("xpath", "//span[contains(text(),'OK')]");
				Sync.waitElementVisible(40, "xpath", "//div[@class='a-message__container-inner']");
				String message = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();

				Common.assertionCheckwithReport(message.contains("Wish List"), "validating the whishlist deletion",
						"new whishlist should be delete sucessfully", "Sucessfully new whishlist has been deleted",
						"failed to delete the new whishlist");
			} else {
				Assert.fail();
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the copy whishlist items to another whishlist",
					"Product should be added to the whishlist",
					"Unable to to copy the whishlist to the exsiting whishlist",
					Common.getscreenShot("Failed to to copy the whishlist to the exsiting whishlist"));
			Assert.fail();
		}
	}

	public void Copy_Whishlist() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(4000);
			Common.clickElement("xpath", "//label[@for='wishlist-select-all']");
			Sync.waitElementPresent(30, "xpath", "//span[text()='Copy Selected to Wish List']");
			Common.clickElement("xpath", "//span[text()='Copy Selected to Wish List']");
			Sync.waitElementPresent(30, "xpath", "//li[@class='item m-dropdown__item']//span[text()='Wish List']");
			Common.clickElement("xpath", "//li[@class='item m-dropdown__item']//span[text()='Wish List']");
			Thread.sleep(4000);
			String copied = Common.findElement("xpath",
					"//div[@data-ui-id='message-success']//div[@class='a-message__container-inner']").getText();
			System.out.println(copied);
			Common.assertionCheckwithReport(copied.contains("1 items were copied"),
					"validating the copy whishlist items to another whishlist",
					"Product should be added to the whishlist",
					"Sucessfully product copied form one whishlist to another",
					"Failed to to copy the whishlist to the exsiting whishlist");
			Delete_Whishlist();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the copy whishlist items to another whishlist",
					"Product should be added to the whishlist",
					"Unable to to copy the whishlist to the exsiting whishlist",
					Common.getscreenShot("Failed to to copy the whishlist to the exsiting whishlist"));
			Assert.fail();
		}

	}

	public void Move_Whishlist(String Dataset) {
		// TODO Auto-generated method stub

		try {
			Thread.sleep(4000);
			Common.clickElement("xpath", "//label[@for='wishlist-select-all']");
			Sync.waitElementPresent(30, "xpath", "//span[text()='Move Selected to Wish List']");
			Common.clickElement("xpath", "//span[text()='Move Selected to Wish List']");
			Common.clickElement("xpath", "//span[@title='Create New Wish List']");
			Common.textBoxInput("xpath", "//input[@name='name']", data.get(Dataset).get("whishlist name"));
			Sync.waitElementPresent("xpath", "//button[@title='Save']");
			Common.clickElement("xpath", "//button[@title='Save']");
			Thread.sleep(4000);
			String whishlist = Common.findElement("xpath", "(//div[@class='a-message__container-inner'])[1]").getText();
			System.out.println(whishlist);
			String message = Common.findElement("xpath", "(//div[@class='a-message__container-inner'])[2]").getText();
			System.out.println(message);
			Common.assertionCheckwithReport(
					message.contains("2 items were moved to Testing") && whishlist.contains("Wish list"),
					"validating the  product add to the Whishlist", "Product should be add to whishlist",
					"Sucessfully product added to the Whishlist ", "failed to add product to the Whishlist");
			int MyFavorites = Common.findElements("xpath", "//div[contains(@class,'message')]//span").size();

			if (MyFavorites != 0) {
				Sync.waitElementPresent(30, "xpath", "//a[text()='Testing']");
				Common.clickElement("xpath", "//a[text()='Testing']");
				Delete_Whishlist();
			} else {
				Assert.fail();
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the copy whishlist items to another whishlist",
					"Product should be added to the whishlist",
					"Unable to to copy the whishlist to the exsiting whishlist",
					Common.getscreenShot("Failed to to copy the whishlist to the exsiting whishlist"));
			Assert.fail();
		}
	}

	public void country_selctor(String Dataset) {
		// TODO Auto-generated method stub
		String Country;
		try {
			Common.actionsKeyPress(Keys.END);
			List<WebElement> country = Common.findElements("xpath", "//label[contains(@class,'a-radio-button')]");
			List<WebElement> Countryselector = Common.findElements("xpath",
					"//label[contains(@class,'a-radio-button')]");
			ArrayList<String> CountryNames = new ArrayList<String>();
			Thread.sleep(4000);
			Sync.waitElementPresent(50, "xpath", "//button[@data-trigger='country_selector']");
			Common.clickElement("xpath", "//button[@data-trigger='country_selector']");
			Thread.sleep(4000);
			for (WebElement Countryselections : Countryselector) {
				CountryNames.add(Countryselections.getText());
				System.out.println(CountryNames);
			}
			String[] items = data.get(Dataset).get("Countrynames").split(",");
			System.out.println(items);
			Common.clickElement("xpath", "//span[contains(@class,'icon-modal__close')]");
			for (int j = 0; j < items.length; j++) {
				if (CountryNames.contains(items[j])) {

					System.out.println(country.size());

					for (int i = 0; i < country.size(); i++) {

						List<WebElement> select = Common.findElements("xpath",
								"//label[contains(@class,'a-radio-button')]");
						Sync.waitPageLoad();
						Sync.waitElementPresent(50, "xpath", "//button[@data-trigger='country_selector']");
						Common.clickElement("xpath", "//button[@data-trigger='country_selector']");
						Thread.sleep(3000);
						Country = select.get(i).getText();
						System.out.println(Country);
						select.get(i).click();
						if (Country.contains("United Kingdom")) {

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
				}
			}
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the country selection page navigation",
					"After Clicking on the selected country it should navigate to the respective country page",
					"Unable to navigate to the respective country page after clicking on the selected country",
					Common.getscreenShot(
							"Failed to navigate to the respective country page after clicking on the selected country"));
			Assert.fail();
		}
	}

	public void Change_Shippingmethods(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//button[@data-bind='click: backToShippingMethod']");
			Common.clickElement("xpath", "//button[@data-bind='click: backToShippingMethod']");
			Sync.waitPageLoad(40);
			Thread.sleep(2000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Checkout"),
					"validating the navigation to the checkout page",
					"after click on edit button from payment page it should navigate to the shiiping page",
					"Sucessfully Navigated to the shipping page ", "failed to Navigate to the shipping page");
			selectshippingmethod(Dataset);
			clickSubmitbutton_Shippingpage();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the navigation to the checkout page",
					"after click on edit button from payment page it should navigate to the shiiping page",
					"Unable  to Navigate to the shipping page",
					Common.getscreenShot("failed to Navigate to the shipping page"));
			Assert.fail();
		}

	}

	public void Gift_message(String Dataset) {
		// TODO Auto-generated method stub
		String message = data.get(Dataset).get("message");
		try {
			Common.scrollIntoView("xpath", "//span[text()='Add Gift Message']");
			Sync.waitElementPresent(40, "xpath", "//span[text()='Add Gift Message']");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//span[text()='Add Gift Message']");
			int gift = Common.findElements("xpath", "//button[contains(@class,'action action')]").size();
			System.out.println(gift);
			if (gift >= 3) {
				Thread.sleep(4000);
				Common.clickElement("xpath", "//span[text()='Delete']");
				Sync.waitPageLoad(40);
				Sync.waitElementPresent(40, "xpath", "//span[text()='Add Gift Message']");
				Common.clickElement("xpath", "//span[text()='Add Gift Message']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//span[text()='Gift Receipt']");
				Sync.waitElementPresent(40, "id", "gift-message-whole-to-giftOptionsCart");
				Common.textBoxInput("id", "gift-message-whole-to-giftOptionsCart", data.get(Dataset).get("FirstName"));
				Common.textBoxInput("id", "gift-message-whole-from-giftOptionsCart", data.get(Dataset).get("LastName"));
				Common.textBoxInput("id", "gift-message-whole-message-giftOptionsCart", message);
				Common.clickElement("xpath", "//button[@title='Add']");
				Sync.waitPageLoad(40);
				Thread.sleep(2000);
				Sync.waitElementPresent(40, "xpath", "//div[@class='gift-message-summary']");
				String Messgae = Common.findElement("xpath", "//div[@class='gift-message-summary']").getText()
						.replace("Message: ", "");
				System.out.println(Messgae);
				Common.assertionCheckwithReport(Messgae.equals(message), "validating the Gift cart message",
						"Gift card message should be applied", "Sucessfully gift message has been applied ",
						"failed to apply the gift message");

			} else {
				if(Common.getCurrentURL().contains("stage3"))
				{
					Sync.waitElementPresent(40, "id", "gift-message-whole-to-giftOptionsCart");
					Common.textBoxInput("id", "gift-message-whole-to-giftOptionsCart", data.get(Dataset).get("FirstName"));
					Common.textBoxInput("id", "gift-message-whole-from-giftOptionsCart", data.get(Dataset).get("LastName"));
					Common.textBoxInput("id", "gift-message-whole-message-giftOptionsCart", message);
					Common.clickElement("xpath", "//button[@title='Add']");
					Sync.waitPageLoad(40);
					Sync.waitElementPresent(40, "xpath", "//div[@class='gift-message-summary']");
					String Messgae = Common.findElement("xpath", "//div[@class='gift-message-summary']").getText()
							.replace("Message: ", "");
					System.out.println(Messgae);
					Common.assertionCheckwithReport(Messgae.equals(message), "validating the Gift cart message",
							"Gift card message should be applied", "Sucessfully gift message has been applied ",
							"failed to apply the gift message");
				}
				else
				{
				Common.clickElement("xpath", "//span[text()='Gift Receipt']");
				Sync.waitElementPresent(40, "id", "gift-message-whole-to-giftOptionsCart");
				Common.textBoxInput("id", "gift-message-whole-to-giftOptionsCart", data.get(Dataset).get("FirstName"));
				Common.textBoxInput("id", "gift-message-whole-from-giftOptionsCart", data.get(Dataset).get("LastName"));
				Common.textBoxInput("id", "gift-message-whole-message-giftOptionsCart", message);
				Common.clickElement("xpath", "//button[@title='Add']");
				Sync.waitPageLoad(40);
				Sync.waitElementPresent(40, "xpath", "//div[@class='gift-message-summary']");
				String Messgae = Common.findElement("xpath", "//div[@class='gift-message-summary']").getText()
						.replace("Message: ", "");
				System.out.println(Messgae);
				Common.assertionCheckwithReport(Messgae.equals(message), "validating the Gift cart message",
						"Gift card message should be applied", "Sucessfully gift message has been applied ",
						"failed to apply the gift message");
				}
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Gift cart message", "Gift card message should be applied",
					"Unabled to apply the gift message", Common.getscreenShot("failed to apply the gift message"));
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

	public void Remove_Product(String dataSet) {
		// TODO Auto-generated method stub
		String Symbol = data.get(dataSet).get("Symbol");
		String products = data.get(dataSet).get("Products");

		try {
			Thread.sleep(4000);
			String subtotal = Common.findElement("xpath", "//span[@data-th='Subtotal']").getText().replace(Symbol, "");
			Float subtotalvalue = Float.parseFloat(subtotal);
			System.out.println(subtotalvalue);
			String Productprice = Common.getText("xpath", "(//td[@data-th='Subtotal']//span[@class='price'])[2]")
					.replace(Symbol, "");
			Float pricevalue = Float.parseFloat(Productprice);
			System.out.println(pricevalue);
			Float Total = subtotalvalue - pricevalue;
			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			System.out.println(ExpectedTotalAmmount2);
			Sync.waitElementPresent("xpath", "//span[text()='Remove " + products + "']");
			Common.clickElement("xpath", "//span[text()='Remove " + products + "']");
			Sync.waitPageLoad(30);
			Thread.sleep(5000);
			String ordertotal = Common.getText("xpath", "//td[@data-th='Order Total']//span[@class='price']")
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

	public void simple_addtocart(String Dataset) {
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
			Sync.waitPageLoad(30);
			Thread.sleep(6000);
			// int Amount=Common.findElements("xpath",
			// "//div[@data-role='priceBox']").size();
			// for (int i = 0; i < Amount; i++) {
			// int value = i + 1;
			// Thread.sleep(2000);
			// List<WebElement> Price=Common.findElements("xpath",
			// "(//span[@data-price-type='finalPrice']//span[@class='price'])["+value+"]");
			// System.out.println(Price);
			// for(WebElement amount : Price)
			// {
			// String priceamount=amount.getText().replace("£", "");
			// Thread.sleep(2000);
			// Float PRICE =Float.parseFloat(priceamount);
			// System.out.println(PRICE);
			//
			// if(PRICE>0)
			// {
			// Common.mouseOver("xpath",
			// "(//span[@data-price-type='finalPrice']//span[@class='price'])["+value+"]");
			// Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			// Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			// Sync.waitPageLoad();
			// Thread.sleep(4000);
			// Sync.waitElementPresent(30, "xpath", "//div[@data-ui-id='message-success']");
			// String message = Common.findElement("xpath",
			// "//div[@data-ui-id='message-success']")
			// .getAttribute("data-ui-id");
			// System.out.println(message);
			// Common.assertionCheckwithReport(message.contains("success"), "validating the
			// product add to the cart",
			// "Product should be add to cart", "Sucessfully product added to the cart ",
			// "failed to add product to the cart");
			// }
			// else
			// {
			//
			// }
			// }
			// }
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.clickElement("xpath", "//img[@alt='" + products + "']");
//			Sync.waitPageLoad(30);
//			Thread.sleep(6000);
//			Sync.waitElementVisible(30, "xpath", "//div[@class='m-product-overview__info-top']//h1");
//			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
//			System.out.println(name);
//			Thread.sleep(4000);
//			String product = data.get(Dataset).get("Products").toUpperCase();
//			System.out.println(product);
//			Common.assertionCheckwithReport(name.contains(product) || Common.getPageTitle().contains(product),
//					"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
//					"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
//			product_quantity(Dataset);
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
//			Sync.waitPageLoad();
//			Thread.sleep(4000);
//			Sync.waitElementPresent(30, "xpath", "//div[@data-ui-id='message-success']");
//			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//					.getAttribute("data-ui-id");
//			System.out.println(message);
//			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
//					"Product should be add to cart", "Sucessfully product added to the cart ",
//					"failed to add product to the cart");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"unable to add product to the cart", Common.getscreenShot("failed to add product to the cart"));

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
			Sync.waitPageLoad(30);
			Thread.sleep(6000);
			Sync.waitElementVisible(30, "xpath", "//div[@class='m-product-overview__info-top']//h1");
			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
			Thread.sleep(4000);
			String product = data.get(Dataset).get("Products").toUpperCase();
			Common.assertionCheckwithReport(name.contains(product) || Common.getPageTitle().contains(product),
					"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
					"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
//			click_UGC();
			Locally_PDP();
			Common.actionsKeyPress(Keys.UP);

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the PDP page", "In PDP fav ugc all should be appear",
					"Unable to see few things in the PDP",
					Common.getscreenShot("Failed to see few things in the PDP page"));
			Assert.fail();
		}

	}

	public void Locally_PDP() {
		// TODO Auto-generated method stub
		try {
			Common.scrollIntoView("xpath", "//span[@class='lcly-city-name']");
			Sync.waitElementPresent(30, "xpath", "//span[@class='lcly-city-name']");
			Common.clickElement("xpath", "//span[@class='lcly-city-name']");
			Thread.sleep(4000);
			Common.switchFrames("xpath", "//iframe[@id='lcly-iframe-inner-0']");
			Thread.sleep(4000);
			Sync.waitElementPresent(40, "xpath", "//a[@id='dealer-navigation-retailers']//span");
			String retail = Common.findElement("xpath", "//a[@id='dealer-navigation-retailers']//span").getText();
			System.out.println(retail);
			Common.assertionCheckwithReport(retail.contains("RETAILERS"), "validating the Find Locally in the PDP ",
					"when we click on the locally maps should be opened", "Sucessfully google maps has been opend",
					"failed to open the google maps when we click on the Find Locally");
			Common.switchToDefault();
			Common.clickElement("xpath", "//a[@id='lcly-iframe-closer-0']");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Find Locally in the PDP ",
					"when we click on the locally maps should be opened",
					"Unable to open the google maps when we click on the Find Locally",
					Common.getscreenShot("Failed to open the google maps when we click on the Find Locally"));
			Assert.fail();
		}

	}

	public void Configurable_PDP(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		String Productsize = data.get(Dataset).get("Size");
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
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.clickElement("xpath", "//img[@alt='" + products + "']");
			Sync.waitElementPresent("xpath", "//div[@data-option-label='" + Productsize + "']");
			Common.clickElement("xpath", "//div[@data-option-label='" + Productsize + "']");
			Sync.waitPageLoad(30);
			Thread.sleep(6000);
			Sync.waitElementVisible(30, "xpath", "//div[@class='m-product-overview__info-top']//h1");
			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();

			Common.assertionCheckwithReport(name.contains(products) || Common.getPageTitle().contains(products),
					"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
					"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
//			click_UGC();
			Locally_PDP();
			Common.actionsKeyPress(Keys.UP);
//			add_simplarproducts("configurable product");
			PDP_Tabs("Tabs");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the PDP page", "In PDP fav ugc all should be appear",
					"Unable to see few things in the PDP",
					Common.getscreenShot("Failed to see few things in the PDP page"));

			Assert.fail();
		}

	}

	public void add_simplarproducts(String Dataset) {
		// TODO Auto-generated method stub
		String colorproduct = data.get(Dataset).get("Products");
		String productcolor = data.get(Dataset).get("Color");
		String Productsize = data.get(Dataset).get("Size");

		try {
			Thread.sleep(4000);
			Common.actionsKeyPress(Keys.PAGE_UP);
			String simlar = Common.findElement("xpath", "//div[@class='m-product-upsell__cta']//a").getText();
			System.out.println(simlar);
			if (simlar.contains("SHOP NOW")) {
				Common.scrollIntoView("xpath", "//a[text()='" + colorproduct + "']");
				Common.clickElement("xpath", "//a[text()='" + colorproduct + "']");
				Sync.waitPageLoad(30);
				Thread.sleep(4000);
				Sync.waitElementVisible(30, "xpath", "//div[@class='m-product-overview__info-top']//h1");
				String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
				Common.assertionCheckwithReport(
						name.contains(colorproduct) || Common.getPageTitle().contains(colorproduct),
						"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
						"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
				Common.navigateBack();
				Sync.waitPageLoad(30);
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//div[@class='m-product-upsell__cta']//a");
				Common.clickElement("xpath", "//div[@class='m-product-upsell__cta']//a");
				Sync.waitPageLoad(30);
				Thread.sleep(4000);
				Common.assertionCheckwithReport(
						name.contains(colorproduct) || Common.getPageTitle().contains(colorproduct),
						"validating the  product navigates to PDP page", "It should be navigate to the PDP page",
						"Sucessfully Navigates to the PDP page", "failed to Navigate to the PDP page");
				Sync.waitElementPresent("xpath", "//div[@aria-label='" + productcolor + "']");
				Common.clickElement("xpath", "//div[@aria-label='" + productcolor + "']");
				Sync.waitElementPresent("xpath", "//div[@data-option-label='" + Productsize + "']");
				Common.clickElement("xpath", "//div[@data-option-label='" + Productsize + "']");
				Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
				Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
				Thread.sleep(4000);
				String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
						.getAttribute("data-ui-id");
				Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
						"Product should be add to cart", "Sucessfully product added to the cart ",
						"failed to add product to the cart");

			} else {
				Assert.fail();
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the similar products functionality",
					"Products should be add from the similar products from PDP page",
					"Unable to add the products from the similar products",
					Common.getscreenShot("Failed to add the products from the similar products"));
			Assert.fail();
		}

	}

	public void PDP_Tabs(String Dataset) {
		// TODO Auto-generated method stub
		String names = data.get(Dataset).get("names");
		String[] Links = names.split(",");
		int i = 0;
		try {
			for (i = 0; i < Links.length; i++) {
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//a[@class='data switch' and text()='" + Links[i] + "']");
				Common.clickElement("xpath", "//a[@class='data switch' and text()='" + Links[i] + "']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				String title = Common.findElement("xpath", "//a[text()='" + Links[i] + "']//parent::div")
						.getAttribute("aria-expanded");
				Common.assertionCheckwithReport(title.contains("true"), "verifying the tabs in PDP ",
						"After clicking on the " + Links[i] + "It should display the related content",
						"sucessfully after clicking on the " + Links[i] + "it has been displayed related content",
						"Failed to display related content" + Links[i]);

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

	public void Click_My_Return() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//div[@class='m-account-nav__content']");
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			Sync.waitElementPresent("xpath", "//a[text()='My Account']");
			Common.clickElement("xpath", "//a[text()='My Account']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"),
					"validating the user navigated to the my account page",
					"After clicking on my account it should navigate to my account",
					"Sucessfully Navigated to the my account page", "failed to Navigated to the my account page");

			Sync.waitElementPresent("xpath", "//a[text()='Spares, Repairs & Reservoirs']");
			Common.clickElement("xpath", "//a[text()='Spares, Repairs & Reservoirs']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Returns"),
					"validating the user navigated to the my Return page",
					"After clicking on my account it should navigate to my Returns page",
					"Sucessfully Navigated to the my Returns page", "failed to Navigated to the my Returns page");
			view_RMA();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the user navigated to the my Return page",
					"After clicking on my account it should navigate to my Returns page",
					"Unable to Navigated to the my Returns page",
					Common.getscreenShot("Failed to Navigated to the my Returns page"));

			Assert.fail();
		}

	}

	public void view_RMA() {
		String Print_OrderNumber = null;
		try {

			List<WebElement> RMA_Status = Common.findElements("xpath", "//span[text()='Approved']");

			if (!RMA_Status.isEmpty()) {

				for (WebElement element : RMA_Status) {
					String statusText = element.getText();

					System.out.println("Status:" + statusText);
					// Check if the text is equal to "Approved"
					if (statusText.equals("Approved")) {

						Common.clickElement("xpath", "//a[text()='View']");
						break;
					} else {
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
				// Need to write code to close the Pop-up

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

				Common.assertionCheckwithReport(
						ProductName.equals(Print_ProductName) && ProductQTY.equals(Print_ProductQTY)
								&& OrderNo.equals(Print_OrderNumber),
						"validating the user navigated to the my Return page",
						"After clicking on RMA Packin slip it should navigate to RMA Print page",
						"Sucessfully Navigated to the RMA Print page", "failed to Navigated to the RMA Print page");

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

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the user navigated to RMA Print page",
					"After clicking on my account it should navigate to RMA Print page",
					"Unable to Navigated to RMA Print page",
					Common.getscreenShot("Failed to Navigated to RMA Print page"));
			Assert.fail();
		}

	}

	public void LogoutExistingUser() {
		// TODO Auto-generated method stub
		try {

			// Common.clickElement("xpath", "//a[@class='a-logo']");

			String URL = automation_properties.getInstance().getProperty(automation_properties.BASEURL);
			Common.oppenURL(URL);
			signout();
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void discountCode(String dataSet) throws Exception {
		String expectedResult = "It should opens textbox input to enter discount.";
		String Symbol = data.get(dataSet).get("Symbol");
		System.out.println(Symbol);

		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);

			Sync.waitElementClickable("xpath", "//span[text()='Add Discount Code']");
			Common.clickElement("xpath", "//span[text()='Add Discount Code']");
			if (Common.getCurrentURL().contains("stage")) {
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
			else
			{
			Thread.sleep(4000);
			Common.scrollIntoView("xpath", "//tr[@class='totals sub']//span[@class='price']");
			String Subtotal = Common.getText("xpath", "//tr[@class='totals sub']//span[@class='price']").replace(Symbol,
					"");
			Float subtotalvalue = Float.parseFloat(Subtotal);
			String shipping = Common.getText("xpath", "//tr[@class='totals shipping incl']//span[@class='price']")
					.replace(Symbol, "");
			Float shippingvalue = Float.parseFloat(shipping);
			String Tax = Common.getText("xpath", "//tr[@class='totals-tax']//span[@class='price']").replace(Symbol, "");
			Float Taxvalue = Float.parseFloat(Tax);
			Thread.sleep(4000);
			String Discount = Common.getText("xpath", "//tr[@class='totals discount']//span[@class='price']")
					.replace(Symbol, "");
			Float Discountvalue = Float.parseFloat(Discount);
			System.out.println(Discountvalue);

			String ordertotal = Common.getText("xpath", "//tr[@class='grand totals']//span[@class='price']")
					.replace(Symbol, "");
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

		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating discount code", expectedResult,
					"User failed to proceed with discountcode",
					Common.getscreenShotPathforReport("discountcodefailed"));

			Assert.fail();

		}
	}

	public void Compare_Products() throws Exception {

		try {

			List<WebElement> compareLinks = Common.findElements("xpath", "//*[text()='Add to Compare']");

			if (compareLinks.size() >= 2) {

				compareLinks.get(2).click();
				Thread.sleep(3000);
				compareLinks.get(6).click();
				Sync.waitElementPresent("xpath", "//a[text()='comparison list']");
				Common.clickElement("xpath", "//a[text()='comparison list']");

				Common.clickElement("xpath", "(//button[@id='product-addtocart-button'])[2]");
				Sync.waitElementVisible("xpath", "//a[text()='shopping cart']");

				String Shoppping = Common.findElement("xpath", "//a[text()='shopping cart']").getText();

				System.out.println(Shoppping);
				Assert.assertEquals(Shoppping, "shopping cart");

			} else {
				System.out.println("Insufficient number of products for comparison.");

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating Compare Products",
					"Products added to Compare list successfull", "failed to add product to compareList",
					Common.getscreenShotPathforReport("CompareProductfail"));
			Assert.fail();
		}

	}

	public void Add_Wishlist() throws Exception {
		// TODO Auto-generated method stub
		try {

			Sync.waitElementPresent("xpath", "(//button[@data-action='add-to-wishlist'])[1]");
			Common.javascriptclickElement("xpath", "(//button[@data-action='add-to-wishlist'])[1]");
			int Size = Common.findElements("xpath", "(//div[@class='m-modal__box']//div[1]//h4)[1]").size();
			System.out.println(Size);
			if (Size > 0) {

				Sync.waitElementPresent("xpath", "(//*[text()='Add To List'])[1]");
				Common.javascriptclickElement("xpath", "(//*[text()='Add To List'])[1]");

			} else {
				String Error = Common.findElement("xpath", "//div[@class='a-message__container-inner']").getText();
				if (Error.contains("You must login")) {
					Login_Account("Account");
				} else {
					System.out.println("no Error message displayed");

				}
			}
			Thread.sleep(3000);
			int WishlistMSG = Common.findElements("xpath", "//div[@data-ui-id='message-success']").size();
			System.out.println("Wishlist" + WishlistMSG);
			Common.assertionCheckwithReport(WishlistMSG > 0, "validating the My Wish List",
					"My Wish List should be display", "Sucessfully navigated to My Wish List ",
					"failed to navigate to My Wish List");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating product added to wishlist ",
					"Products added to Compare list successfull", "failed to add product to wishlist",
					Common.getscreenShotPathforReport("Wishlistfail"));
			Assert.fail();
		}
	}

	public void AddtoCart_Wishlist() throws Exception {
		// TODO Auto-generated method stub
		try {
			String Wishlist = Common.findElement("xpath", "//h1[text()='My Wish Lists']").getText();
			if (Wishlist.equals("My Wish Lists"))
				Sync.waitElementPresent(30, "xpath", "//button[@id='product-addtocart-button']");
			Common.mouseOverClick("xpath", "//button[@id='product-addtocart-button']");
			minicart_Checkout();
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating Adding to cart from wishlist ",
					"Products added to cart successfull", "failed to add Products to cart",
					Common.getscreenShotPathforReport("AddtoCartfail"));
			Assert.fail();
		}

	}

	public void Shoppingcart_page() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementVisible(30, "xpath", "//span[text()='Back to Cart']");
			Common.clickElement("xpath", "//span[text()='Back to Cart']");
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
			Sync.waitElementPresent("xpath", "//span[text()='Add Discount Code']");
			Common.clickElement("xpath", "//span[text()='Add Discount Code']");

			Sync.waitElementPresent("xpath", "//input[@name='coupon_code']");
			if (Common.getCurrentURL().contains("stage")) {
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
			if (Common.getCurrentURL().contains("Stage")) {
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
			Float Total = (subtotalvalue + shippingvalue + Taxvalue) + Discountvalue;
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

	public void invalid_Discount(String Dataset) {
		// TODO Auto-generated method stub
		String invalidcode = data.get(Dataset).get("invalidcode");
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//span[text()='Add Discount Code']");
			Common.clickElement("xpath", "//span[text()='Add Discount Code']");
			Sync.waitElementPresent("id", "discount-code");
			Common.textBoxInput("id", "discount-code", invalidcode);
			Common.clickElement("xpath", "//button[@value='Apply Discount']");
			Thread.sleep(2000);
			String errormessage = Common.findElement("xpath", "//div[@class='message message-error error']//div")
					.getText();
			Common.assertionCheckwithReport(errormessage.contains("The coupon code isn't valid."),
					"validating the discount error message in the payment page",
					"Error message should be display in the payment page",
					"Successfully error message should be displayed",
					"Failed to display the error message in the payment page");
			Tool_Tip();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the discount error message in the payment page",
					"Error message should be display in the payment page",
					"unable to display the  error message should in the payment page",
					Common.getscreenShot("Failed to display the error message in the payment page"));
			Assert.fail();
		}

	}

	public void Tool_Tip() {
		try {
			Sync.waitElementPresent("xpath", "//span[@class='a-tooltip__trigger-text' and text()='Shipping']");
			Common.clickElement("xpath", "//span[@class='a-tooltip__trigger-text' and text()='Shipping']");
			String message = Common.findElement("xpath", "//div[@data-content-type='text']//p")
					.getText();
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("Shipping"),
					"validating the shiiping tooltip in the payment page",
					"after clicking on the shipping tooltip message should appear",
					"Successfully shipping tooltip is displayed", "Failed to display the Tooltip for shipping");
			Sync.waitElementPresent("xpath", "//span[@class='a-tooltip__trigger-text' and text()='Tax']");
			Common.clickElement("xpath", "//span[@class='a-tooltip__trigger-text' and text()='Tax']");
			String taxtooltip = Common.findElement("xpath", "//button[@aria-describedby='formShippingTotalTaxTooltip']")
					.getText();
			System.out.println(taxtooltip);
			Common.assertionCheckwithReport(taxtooltip.contains("Tax"),
					"validating the Tax tooltip in the payment page",
					"after clicking on the Tax tooltip message should appear", "Successfully Tax tooltip is displayed",
					"Failed to display the Tooltip for Tax");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the tooltip in the payment page",
					"after clicking on the particular tooltip message should appear",
					"Unable to display the Tooltip in the payment page",
					Common.getscreenShot("Failed to display the Tooltip in the payment page"));
			Assert.fail();
		}
	}

	public void Edit_Gift_Message(String Dataset) {
		// TODO Auto-generated method stub
		String message = data.get(Dataset).get("Discountcode");
		try {
			Sync.waitElementPresent("xpath", "//button[@type='submit']//span[text()='Edit']");
			Common.clickElement("xpath", "//button[@type='submit']//span[text()='Edit']");
			Common.textBoxInput("id", "gift-message-whole-message-giftOptionsCart", message);
			Common.clickElement("xpath", "//button[@title='Add']");
			String Messgae = Common.findElement("xpath", "//div[@class='gift-message-summary']").getText()
					.replace("Message: ", "");
			System.out.println(Messgae);
			Common.assertionCheckwithReport(Messgae.equals(message), "validating the Gift cart message",
					"Gift card message should be applied", "Sucessfully gift message has been applied ",
					"failed to apply the gift message");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Gift cart message", "Gift card message should be applied",
					"Unable to dapply the gift message", Common.getscreenShot("Failed to apply the gift message"));
			Assert.fail();
		}
	}

	public void Invalid_search_product(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("InvalidProductName");
		System.out.println(product);
		try {
			Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
			String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
			Thread.sleep(4000);
			Common.assertionCheckwithReport(open.contains("active"), "User searches using the search field",
					"User should able to click on the search button", "Search expands to the full page",
					"Sucessfully search bar should be expand");
			Common.textBoxInput("xpath", "//input[@id='autocomplete-0-input']",
					data.get(Dataset).get("InvalidProductName"));
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String productsearch = Common.findElement("xpath", "//h3[@class='c-srp-title__no-results']").getText();
			System.out.println(productsearch);
			Common.assertionCheckwithReport(productsearch.contains("Sorry, your search for"),
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

	public void Sort_By(String Dataset) throws InterruptedException {
		// TODO Auto-generated method stub
		String symbol = data.get(Dataset).get("Price_Symbol");
		String PriceFilter = data.get(Dataset).get("Sortby_Dropdown");
		try {
			Sync.waitPageLoad();

			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.scrollIntoView("xpath",
					"//div[@class='product-info-main m-product-card__price ']//span[@data-price-type='finalPrice']//span[@class='price']");

			List<WebElement> BeforeFilterprice = Common.findElements("xpath",
					"//div[@class='product-info-main m-product-card__price ']//span[@data-price-type='finalPrice']//span[@class='price']");
			List<String> Beforefilterpricelist = new ArrayList<String>();

			for (WebElement p : BeforeFilterprice) {
				// Beforefilterpricelist.add(Double.valueOf(p.getText().replace("£", " ")));
				Beforefilterpricelist.add(p.getText().replace(symbol, " "));
				System.out.println("Beforefilterpricelist" + Beforefilterpricelist);
			}
			Common.dropdown("xpath", "//select[@id='srp-sort-by']", SelectBy.TEXT,
					data.get(Dataset).get("Sortby_Dropdown"));

			Thread.sleep(5000);
			Common.scrollIntoView("xpath",
					"//div[@class='product-info-main m-product-card__price ']//span[@data-price-type='finalPrice']//span[@class='price']");
			List<WebElement> AfterFilterprice = Common.findElements("xpath",
					"//div[@class='product-info-main m-product-card__price ']//span[@data-price-type='finalPrice']//span[@class='price']");
			List<String> Afterfilterpricelist = new ArrayList<String>();

			for (WebElement p : AfterFilterprice) {
				// Afterfilterpricelist.add(Double.valueOf(p.getText().replace("£", " ")));
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

	public void Filter() throws InterruptedException {
		// TODO Auto-generated method stub
		try {

			Common.scrollIntoView("xpath", "//input[@id='blue']");
			Sync.waitElementPresent("xpath", "//input[@id='blue']");
			Common.clickElement("xpath", "//input[@value='Blue']");
			Common.scrollIntoView("xpath", "//input[@id='blue']");

			String SelectedFilter = Common.findElement("xpath", "//span[text()='Blue']").getText();
			System.out.println("SelectedFilter:" + SelectedFilter);
			String RetrivedValue = "blue";
			if (SelectedFilter.equals("Blue")) {

				List<WebElement> Series_Filters = Common.findElements("xpath",
						"//div[@class='m-product-card__image-wrapper']//a//img");

				for (WebElement Filter : Series_Filters) {
					// System.out.println(Filter);
					String AttributeValue = Filter.getAttribute("src");

					if (AttributeValue != null && AttributeValue.contains(RetrivedValue)) {

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

	public void webpagelinks_validation(String Dataset) throws Exception, IOException {
		// TODO Auto-generated method stub
		String links = data.get(Dataset).get("Links");
		int j = 0;

		String[] strArray = links.split("\\r?\\n");
		for (int i = 0; i < strArray.length; i++) {
			System.out.println(strArray[i]);

			if (Common.getCurrentURL().contains("stage")) {

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

			} else if (Common.getCurrentURL().contains("https://hele.digital/")) {

				Common.oppenURL(strArray[i].replace("emea-preprod", "www"));

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

	public void Gift_cards(String Dataset) {
		// TODO Auto-generated method stub
		String GiftCard = data.get(Dataset).get("Osprey");
		try
		{
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Gift Cards')]");
			Common.clickElement("xpath", "//span[contains(text(),'Gift Cards')]");
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Shop Gift Cards"),
					"To validate Gift card Navigation to the PLP",
					"After clicking on the Giftcard for the header links it should navigate to the Gift card PLP page",
					"Sucessfully It has been navigated to the Gift card PLP ", "Failed to Navigate to the Gift card PLP");
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
			Sync.waitElementPresent(30, "xpath", "//img[contains(@alt,'" + GiftCard + "')]");
			Common.clickElement("xpath", "//img[contains(@alt,'" + GiftCard + "')]");
			Sync.waitPageLoad();
			Thread.sleep(6000);
			String GiftcardName=Common.findElement("xpath", "//h1[@data-ui-id='page-title-wrapper']").getText();
			System.out.println(GiftcardName);
			String Card = data.get(Dataset).get("Osprey").toUpperCase();
			Common.assertionCheckwithReport(GiftcardName.contains(Card),
					"validating the Gift card Navigation to the PDP page",
					"After clicking on the gift card it should navigate to the PDP",
					"Successfully Gift card is Navigated to the PDP",
					"Failed to Navigate the Gift card to the PDP page");
		
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Gift card Navigation to the PDP page",
					"After clicking on the gift card it should navigate to the PDP",
					"Unable to Navigate the Gift card to the PDP page",
					Common.getscreenShot("Failed to Navigate the Gift card to the PDP page"));
			Assert.fail();
			
		
		}
	}
	
	
	
	public void SendLater_Card_Value(String Dataset) {
		// TODO Auto-generated method stub
		String amount=data.get(Dataset).get("Card Amount");
		try
		{
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//span[text()='"+ amount +"']");
			Common.clickElement("xpath", "//span[text()='"+ amount +"']");
			String Price=Common.findElement("xpath", "//span[@data-price-type='finalPrice']//span[@class='price']").getText();
			Common.assertionCheckwithReport(Price.contains(amount),
					"validating gift card amount value in PDP",
					"After clicking on the value amount should be appear in PDP",
					"Successfully selected amount is matched for the gift card",
					"Failed to appear amount for the gift card");
			Common.clickElement("xpath", "(//img[@class='amcard-image'])[2]");
			String SmallCard=Common.findElement("xpath", "//img[@class='amcard-image -active']").getAttribute("src");
			String MainCard=Common.findElement("xpath", "//img[@class='fotorama__img']").getAttribute("src");
			Common.assertionCheckwithReport(SmallCard.equals(MainCard),
					"validating the selected gift card",
					"After clicking on the card design gift card should be match",
					"Successfully Gift card design has been matched",
					"Failed to match the Gift card design");
			SendLater_Giftcard_details("Gift Details");
			product_quantity("Product Qunatity");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//div[@data-ui-id='message-success']");
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
					.getAttribute("data-ui-id");
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");

		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"Unable to add the product to the cart", Common.getscreenShot("Failed the product Add to cart from the PDP"));
			Assert.fail();
		}

	}
	public void SendLater_Giftcard_details(String Dataset) {
		// TODO Auto-generated method stub
		String Giftmessage=data.get(Dataset).get("message");
		try
		{
			Common.textBoxInput("xpath", "//input[@name='am_giftcard_sender_name']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='am_giftcard_recipient_name']", data.get(Dataset).get("LastName"));
			Common.textBoxInput("xpath", "//input[@name='am_giftcard_recipient_email']", data.get(Dataset).get("Email"));
			Common.textBoxInput("xpath", "//textarea[@name='am_giftcard_message']", Giftmessage);
			Thread.sleep(3000);
			String Message=Common.findElement("xpath", "//textarea[@name='am_giftcard_message']").getAttribute("value");
			System.out.println(Message);
			Common.assertionCheckwithReport(Message.equals(Giftmessage),
					"validating the message for the Gift card",
					"Message should be dispaly for the Gift card",
					"Successfully message has been dispalyed for the Gift card",
					"Failed to display the gift message for the Gift Card");
			Common.clickElement("xpath", "//label[text()='Send later']");

			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate currentDate = LocalDate.now();
			String formattedDate = currentDate.format(dateFormatter);


			LocalDate nextDay = currentDate.plusDays(1);
			String formattednextDay = nextDay.format(dateFormatter);

			System.out.println("Current Date: " + formattedDate);
			System.out.println("NextDay Date: "+ formattednextDay);


			Common.textBoxInput("xpath", "//input[@id='am_giftcard_date_delivery']", formattednextDay);
			Common.dropdown("id", "am_giftcard_date_delivery_timezone", Common.SelectBy.INDEX, "1");

		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the message for the Gift card",
					"Message should be dispaly for the Gift card",
					"Unable to display the gift message for the Gift Card",
					Common.getscreenShot("Failed to display the gift message for the Gift Card"));
			Assert.fail();
		}
	}
	
	public void DeliveryAddress_Guestuser_Gift(String dataSet) throws Exception {
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

		int Size =Common.findElements("xpath", "//span[text()='Review & Payments']").size();

			try {
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//label[@for='stripe_payments']");
				Common.clickElement("xpath", "//label[@for='stripe_payments']");
				Thread.sleep(3000);
				Common.textBoxInput("xpath", "//div[@class='billing-address-form']//input[@name='firstname']",
						data.get(dataSet).get("FirstName"));
				int size = Common.findElements("xpath", "//input[@type='email']").size();
				Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,
						"Filled Email address", "unable to fill the email address");
				Common.textBoxInput("xpath", "//div[@class='billing-address-form']//input[@name='lastname']",
						data.get(dataSet).get("LastName"));
				Common.clickElement("xpath", "//div[@class='billing-address-form']//input[@name='street[0]']");
				Common.textBoxInput("xpath", "//div[@class='billing-address-form']//input[@name='street[0]']", address);
				Sync.waitPageLoad();
				Thread.sleep(5000);
				Common.findElement("xpath", "//div[@class='billing-address-form']//input[@name='city']").clear();
				Common.textBoxInput("xpath", "//div[@class='billing-address-form']//input[@name='city']",
						data.get(dataSet).get("City"));
				System.out.println(data.get(dataSet).get("City"));

				
				Thread.sleep(3000);
				try {
					Common.scrollIntoView("xpath", "//input[@placeholder='State/Province']");
					Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					Thread.sleep(3000);
					Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", data.get(dataSet).get("Region"));
				}

				Thread.sleep(3000);
//				Common.textBoxInputClear("xpath", "//div[@class='billing-address-form']//input[@name='postcode']");
				String id=Common.findElement("xpath", "(//input[@name='postcode'])[2]").getAttribute("id");
				System.out.println(id);
				Common.textBoxInput("xpath", "//div[@class='billing-address-form']//input[@id='"+ id +"']", data.get(dataSet).get("postcode"));
				Thread.sleep(5000);

				Common.textBoxInput("xpath", "//div[@class='billing-address-form']//input[@name='telephone']", data.get(dataSet).get("phone"));
				
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//span[text()='Update']");
				
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
	

	public void Card_Value(String Dataset) {
		// TODO Auto-generated method stub
		String amount=data.get(Dataset).get("Card Amount");
		try
		{
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//label[@class='amcard-label-block -price']//span[text()='"+ amount +"']");
			Common.clickElement("xpath", "//label[@class='amcard-label-block -price']//span[text()='"+ amount +"']");
			String Price=Common.findElement("xpath", "//span[@data-price-type='finalPrice']//span[@class='price']").getText();
			Common.assertionCheckwithReport(Price.contains(amount),
					"validating gift card amount value in PDP",
					"After clicking on the value amount should be appear in PDP",
					"Successfully selected amount is matched for the gift card",
					"Failed to appear amount for the gift card");
			Common.clickElement("xpath", "(//img[@class='amcard-image'])[2]");
			String SmallCard=Common.findElement("xpath", "//img[@class='amcard-image -active']").getAttribute("src");
			String MainCard=Common.findElement("xpath", "//img[@class='fotorama__img']").getAttribute("src");
			Common.assertionCheckwithReport(SmallCard.equals(MainCard),
					"validating the selected gift card",
					"After clicking on the card design gift card should be match",
					"Successfully Gift card design has been matched",
					"Failed to match the Gift card design");
			Giftcard_details("Gift Details");
			product_quantity("Product Qunatity");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//div[@data-ui-id='message-success']");
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
					.getAttribute("data-ui-id");
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"Unable to add the product to the cart", Common.getscreenShot("Failed the product Add to cart from the PDP"));
			Assert.fail();
		}
		
	}

	public void Giftcard_details(String Dataset) {
		// TODO Auto-generated method stub
		String Giftmessage=data.get(Dataset).get("message");
		try
		{
			Common.textBoxInput("xpath", "//input[@name='am_giftcard_sender_name']", data.get(Dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='am_giftcard_recipient_name']", data.get(Dataset).get("LastName"));
			Common.textBoxInput("xpath", "//input[@name='am_giftcard_recipient_email']", data.get(Dataset).get("Email"));
			Common.textBoxInput("xpath", "//textarea[@name='am_giftcard_message']", Giftmessage);
			Thread.sleep(3000);
			String Message=Common.findElement("xpath", "//textarea[@name='am_giftcard_message']").getAttribute("value");
			System.out.println(Message);
			Common.assertionCheckwithReport(Message.equals(Giftmessage),
					"validating the message for the Gift card",
					"Message should be dispaly for the Gift card",
					"Successfully message has been dispalyed for the Gift card",
					"Failed to display the gift message for the Gift Card");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the message for the Gift card",
					"Message should be dispaly for the Gift card",
					"Unable to display the gift message for the Gift Card",
					Common.getscreenShot("Failed to display the gift message for the Gift Card"));
			Assert.fail();
		}
		
	}
	
	public void Gift_card(String dataSet) {
		
		try
		{
			Thread.sleep(3000);
	Common.scrollIntoView("xpath", "//input[@name='amcard-field -datalist']");
		Common.textBoxInput("xpath","//input[@name='amcard-field -datalist']", data.get(dataSet).get("GiftCard"));
		
		Common.clickElement("xpath","//span[text()='Add Code']");
		Thread.sleep(2000);
		String successmsg=Common.findElement("xpath", "//div[@role='alert']").getText();
	  System.out.println(successmsg);
		
		Common.assertionCheckwithReport(successmsg.contains("added"),
				"validating the success message after applying gift card",
				"Success message should be displayed after the applying of gift card",
				"Sucessfully gift card has been applyed","Failed to apply the gift card");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the gift card",
					"Success message should be displayed after the applying of gift card",
					"Sucessfully gift card has been applyed",
					Common.getscreenShotPathforReport("Failed to apply the gift card"));
			Assert.fail();
		}
	}

	public void invalid_Gift_card(String dataSet) {
		try
		{
		Thread.sleep(4000);
	    Common.scrollIntoView("xpath", "//input[@name='amcard-field -datalist']");
		Common.textBoxInput("xpath","//input[@name='amcard-field -datalist']", data.get(dataSet).get("InvalidGC"));
		
		Common.clickElement("xpath","//span[text()='Add Code']");
		Thread.sleep(3000);
		String errormsg=Common.findElement("xpath", "//div[@role='alert']").getText();
	  System.out.println(errormsg);
		
		Common.assertionCheckwithReport(errormsg.contains("not found"),
				"validating the error message after applying gift card",
				"error message should be displayed after the applying of gift card",
				"Sucessfully gift card has not been applyed","Failed to apply the gift card");
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the gift card",
					"error message should be displayed after the applying of gift card",
					"Sucessfully gift card has been not applyed",
					Common.getscreenShotPathforReport("Failed to apply the gift card"));
			Assert.fail();
		}
		
	}

	public String giftCardSubmitOrder() throws Exception {
		// TODO Auto-generated method stub

		String order = "";
		String expectedResult = "It redirects to order confirmation page";
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		int placeordercount = Common.findElements("xpath", "//button[@class='action primary checkout']").size();
			Thread.sleep(4000);

			Common.clickElement("xpath", "//button[@class='action primary checkout']");
			//Common.refreshpage();
		Thread.sleep(3000);

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
		return order;
	}

	public void Other_Amount(String Dataset) {
		// TODO Auto-generated method stub
		String Enter_amount=data.get(Dataset).get("Another Amount");
		System.out.println(Enter_amount);
		try
		{
			Sync.waitPageLoad();
//			Sync.waitElementPresent("xpath", "//input[@type='number']");
			Common.clickAndtextBoxInput("xpath", "//input[@type='number']", data.get(Dataset).get("Another Amount"));
//			Common.textBoxInput("xpath", "//input[@type='number']", data.get(Dataset).get("Enter_amount"));
			Common.clickElement("xpath", "//button[@class='amcard-button']");
			String Price=Common.findElement("xpath", "//span[@data-price-type='finalPrice']//span[@class='price']").getText();
			System.out.println(Price);
			Common.assertionCheckwithReport(Price.contains(Enter_amount),
					"validating gift card amount value in PDP",
					"After clicking on the value amount should be appear in PDP",
					"Successfully selected amount is matched for the gift card",
					"Failed to appear amount for the gift card");
			Common.clickElement("xpath", "(//img[@class='amcard-image'])[2]");
			String SmallCard=Common.findElement("xpath", "//img[@class='amcard-image -active']").getAttribute("src");
			String MainCard=Common.findElement("xpath", "//img[@class='fotorama__img']").getAttribute("src");
			Common.assertionCheckwithReport(SmallCard.equals(MainCard),
					"validating the selected gift card",
					"After clicking on the card design gift card should be match",
					"Successfully Gift card design has been matched",
					"Failed to match the Gift card design");
			Giftcard_details("Gift Details");
			product_quantity("Product Qunatity");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//div[@data-ui-id='message-success']");
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
					.getAttribute("data-ui-id");
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");

		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  product add to the cart", "Product should be add to cart",
					"Unable to add the product to the cart", Common.getscreenShot("Failed the product Add to cart from the PDP"));
			Assert.fail();
		}

	}

	public void whishlist_share_Button(String Dataset) {
		// TODO Auto-generated method stub
		
		try
		{
			Thread.sleep(4000);
			if(Common.getCurrentURL().contains("stage3"))
			{
				Thread.sleep(4000);
				Common.clickElement("xpath", "//button[@title='Share Favorites']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.textBoxInput("xpath", "//textarea[@name='emails']", data.get(Dataset).get("Email"));
				Common.textBoxInput("xpath", "//textarea[@name='message']", data.get(Dataset).get("message"));
				Common.javascriptclickElement("xpath", "//button[@title='Share Favorites']");
				Thread.sleep(4000);
				String message1 = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				System.out.println(message1);
				Common.assertionCheckwithReport(message1.contains("Your Favorites has been shared."),
						"validating the shared whishlist functionality",
						"sucess message should display after share whishlist",
						"Sucessfully message has been displayed for whishlist",
						"failed to display the message for whishlist");
			
			}
			else
			{
				Thread.sleep(4000);
				Common.javascriptclickElement("xpath", "//button[@title='Share Wish List']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Common.textBoxInput("xpath", "//textarea[@name='emails']", data.get(Dataset).get("Email"));
				Common.textBoxInput("xpath", "//textarea[@name='message']", data.get(Dataset).get("message"));
				Common.javascriptclickElement("xpath", "//button[@title='Share Wish List']");
				Thread.sleep(4000);
				String message1 = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
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

	public String Check_money_order() throws Exception {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//label[@for='checkmo']");
			Common.clickElement("xpath", "//label[@for='checkmo']");
			String check=Common.findElement("xpath", "(//label[@for='checkmo']//parent::div//parent::div)[1]").getAttribute("class");
			Common.assertionCheckwithReport(check.contains("active"),
					"validating the check money order in payment page",
					"Check money order radio button should be selected",
					"Sucessfully check money order has been selected",
					"failed to select the check mony order");
			
			
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the check money order in payment page",
					"Check money order radio button should be selected",
					"Unable to select the check mony order", Common.getscreenShot("Failed to select the check mony order"));
			Assert.fail();
		}
		String order = "";
		String expectedResult = "It redirects to order confirmation page";
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		int placeordercount = Common.findElements("xpath", "//button[@class='action primary checkout']").size();
			Thread.sleep(4000);

			Common.clickElement("xpath", "//button[@class='action primary checkout']");
			//Common.refreshpage();
		Thread.sleep(3000);

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
		return order;
	}

	public void FUll_Payment(String dataSet) {
		
		String Symbl = data.get(dataSet).get("Symbol");
		try {
			String  GiftCard=data.get(dataSet).get("GiftCard");
			String Total_Incl_Tax =Common.getText("xpath", "//td[contains(@data-th,'Order Total Incl. Tax')]").replace(Symbl,"");
			String Total_Excld_Tax =Common.getText("xpath", "//td[contains(@data-th,'Order Total Incl. Tax')]").replace(Symbl,"");
			
			System.out.println("Total_Incl_Tax :"+Total_Incl_Tax);
			System.out.println("Total_Excld_Tax :"+Total_Excld_Tax);
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
			
			
			Sync.waitElementPresent("xpath", "//input[@id='amcard-code-input']");
			
		
			Common.textBoxInput("xpath", "//input[@id='amcard-code-input']", data.get(dataSet).get("GiftCard2"));
			System.out.println(data.get(dataSet).get("GiftCard2"));
			Common.clickElement("xpath", "//span[text()='Add']");
			
		
			Thread.sleep(6000);
		String Applied_Code = Common.findElement("xpath", "//td[@class='col code']").getText();
		
		Common.assertionCheckwithReport(Applied_Code.equals(data.get(dataSet).get("GiftCard2")),
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
		String Giftcard = data.get(dataSet).get("GiftCard2");
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
			FUll_Payment("Giftcard");
		}
		
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Gift code Selected in Payment page",
					"Check Gift code Selected in Payment page",
					"Unable add the Giftcode in Payment page", Common.getscreenShot("Failed to add Giftcoder in Payment page"));
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

	public void Verify_Price(String Dataset) {
		
		String products = data.get(Dataset).get("Products");
		System.out.println(products);
		try {
	
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.clickElement("xpath", "//img[@alt='" + products + "']");
		        	
			String Price = Common.findElement("xpath", "//div[@class='product-info-price']//span[contains(@id,'product-price')]//span").getText();
			System.out.println(Price);
			
			if(Common.getCurrentURL().contains("stage")) {

				double productPrice = Double.parseDouble(Price.replace("£", ""));
				System.out.println(productPrice);
			}
			else {
			double productPrice = Double.parseDouble(Price.replace("$", ""));
			System.out.println(productPrice);
			}
			
			double productPrice = 0;
			if (productPrice <= 50.0) {

//		            	Common.scrollIntoView("xpath", "//button[@title='Add to Cart']");
				Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
				Common.clickElement("xpath", "//button[@id='product-addtocart-button']");

				System.out.println("Product added to cart.");
		            }
			
			Thread.sleep(4000);
			Sync.waitElementPresent(30, "xpath", "//div[@data-ui-id='message-success']");
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
					.getAttribute("data-ui-id");
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");
		}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Price in PLP page",
					"Check Price In PLp page",
					"Unable validate the Price in PLP ", Common.getscreenShot("Failed toValidate  price in PLP page"));
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
			            }
				else {
					System.out.println("Order Total is Not Less than 50");
				}
			}
			else {
			double Order_Total = Double.parseDouble(Ordertotal.replace("$", ""));
			System.out.println(Order_Total);
					
			if (Order_Total <= 50.0) {

				System.out.println("Order Total is Less than or Equal to 50");
		            }
			else {
				System.out.println("Order Total is Not Less than or Equal to 50");
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
	
	
	
	public void After_Pay_payment(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String order = "";
		Sync.waitPageLoad();
		Thread.sleep(3000);	
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
				Common.clickElement("xpath", "//button[@class='a-btn a-btn--tertiary']");

				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Sync.waitElementPresent(30, "xpath", "//button[@id='afterpay_clearpay-tab']");
				Common.javascriptclickElement("xpath", "//button[@id='afterpay_clearpay-tab']");
//				
				Common.switchToDefault();
				Sync.waitElementPresent(30, "xpath", "//button[@class='action primary checkout']");
				Common.clickElement("xpath", "//button[@class='action primary checkout']");
				Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");

			} else {
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Sync.waitElementPresent(30, "xpath", "//button[@value='afterpay_clearpay']");
				Common.clickElement("xpath", "//button[@value='afterpay_clearpay']");
//				
				Common.switchToDefault();
				Common.clickElement("xpath", "//button[@class='action primary checkout']");
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

	
	
	
	
	public void Kalrna_Payment(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> Paymentmethod = new HashMap<String, String>();
		Sync.waitPageLoad();
		Thread.sleep(4000);
	
		String fullname=data.get(dataSet).get("FirstName");
		String expectedResult = "land on the payment section";

		try {
//			Sync.waitPageLoad();
			int sizes = Common.findElements("xpath", "//label[@for='stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unable to land o n the paymentpage");
			System.out.println(sizes);
			Common.clickElement("xpath", "//label[@for='stripe_payments']");

			Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
			int payment = Common.findElements("xpath", "//div[@class='stripe-dropdown-selection']").size();
			System.out.println(payment);
			if (payment > 0) {
				Thread.sleep(2000);
				
				Common.refreshpage();
				Common.scrollIntoView("xpath", "//div[@class='stripe-dropdown-selection']");
				Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//button[@class='a-btn a-btn--tertiary']");
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				System.out.println("Switch to Frames");
				Common.scrollToElementAndClick("xpath", "//div[@class='p-PaymentMethodSelector']//button[@id='klarna-tab']");
//				Sync.waitElementPresent(30, "xpath", "//div[@class='p-PaymentMethodSelector']//button[@id='klarna-tab']");
//				Common.clickElement("xpath", "//div[@class='p-PaymentMethodSelector']//button[@id='klarna-tab']");

				Common.switchToDefault();
				System.out.println("Switch to Default");
				if(Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage") )
				{
					Sync.waitElementClickable("xpath", "//button[@class='action primary checkout']");
				Common.clickElement("xpath", "//button[@class='action primary checkout']");
				Sync.waitPageLoad();
				klarna_Details(dataSet);
				}
				else
				{
					Thread.sleep(4000);
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String klarna=Common.findElement("xpath", "//button[@value='klarna']//span").getText();
					System.out.println(klarna);
					Common.assertionCheckwithReport(
							klarna.contains("Klarna"),
							"validating the selection of the klarna method",
							"klarna should be selected ","klarna is selected",
							"Failed to select the klarna method in the production environment");
					Common.switchToDefault();
					
				}
				
				
			}
			else
			{
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Common.clickElement("xpath", "//button[@value='klarna']");
				Common.switchToDefault();
				
				if(Common.getCurrentURL().contains("preprod") || Common.getCurrentURL().contains("stage") )
				{
				Common.clickElement("xpath", "//button[@class='action primary checkout']");
				Sync.waitPageLoad();
				klarna_Details(dataSet);
				}
				else
				{
					Thread.sleep(4000);
					Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
					String klarna=Common.findElement("xpath", "//button[@value='klarna']//span").getText();
					System.out.println(klarna);
					Common.assertionCheckwithReport(
							klarna.contains("Klarna"),
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
			Common.switchFrames("xpath", "//iframe[@id='klarna-apf-iframe']");
			Sync.waitElementPresent("xpath", "//input[@name='phone']");
		/*	Common.clickElement("xpath", "//input[@name='phone']");
			
			int number=Common.genrateRandomNumber();
			System.out.println(number);
			String mobile=Integer.toString(number);
			String phone="+91"+"95862"+mobile;*/
			WebElement clear=Common.findElement("xpath", "//input[@name='phone']");
		    clear.sendKeys(Keys.CONTROL+"a");
		    clear.sendKeys(Keys.DELETE);
			System.out.println(phone);
			Common.textBoxInput("xpath", "//input[@name='phone']", phone);
			Common.clickElement("xpath", "//button[@id='onContinue']");
			Sync.waitPageLoad();
			Sync.waitElementPresent(30, "xpath", "//input[@id='otp_field']");
			Common.textBoxInput("xpath", "//input[@id='otp_field']", otp);
			Thread.sleep(6000);
			Sync.waitPageLoad();
//			Sync.waitElementPresent(30, "xpath", "//h2[@role='status']");
			
			String klarna=Common.findElement("xpath", "//h2[contains(text(),'How do you want to pay')]").getText();
			
			if(klarna.contains("How do you want to pay"))
			{
				
				Common.clickElement("xpath", "(//span[contains(text(),'Continue')])[2]");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//span[contains(text(),'Pay "+Symbol+"')]");
				Sync.waitPageLoad();
				
					
				
			}
			else
			{
				
//				String klarna1=Common.findElement("xpath", "//h2[@role='status']").getText();
				
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
			express_paypal_shipping("Paypal Shipping");
			if(Common.getCurrentURL().contains("stage") || Common.getCurrentURL().contains("stage3") || Common.getCurrentURL().contains("na.osprey"))
			{
				Common.scrollIntoView("xpath", "//button[@value='Place Order']");
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

	public void Paypal_Address_Verification(String Dataset) {
		// TODO Auto-generated method stub
		
		try
		{
			Sync.waitElementPresent("xpath", "//p[@data-testid='ship-to-address']");
			String address=Common.findElement("xpath", "//p[@data-testid='ship-to-address']").getText();
			if(address.contains("united states"))
			{
				Sync.waitElementPresent("xpath", "//button[@data-testid='change-shipping']");
				Common.clickElement("xpath", "//button[@data-testid='change-shipping']");
//				Common.clickElement("xpath", "//select[@data-testid='shipping-dropdown']");
				Common.dropdown("xpath", "//select[@data-testid='shipping-dropdown']", SelectBy.TEXT, data.get(Dataset).get("Street"));
				Thread.sleep(3000);
				String Ukaddress=Common.findElement("xpath", "//p[@data-testid='ship-to-address']").getText();
				System.out.println(Ukaddress);
				String UK=data.get(Dataset).get("Street").replace("QA TEST - ", "");
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

	public String Reg_BillingAddress(String dataSet) {
		// TODO Auto-generated method stub
		String update = "";
		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//label[@for='stripe_payments']");
			Common.clickElement("xpath", "//label[@for='stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='stripe_payments']").size();
			Common.clickElement("xpath", "//label[@for='stripe_payments']");
			Common.assertionCheckwithReport(sizes > 0, "Validating the payment section page",
					"payment section should be displayed", "sucessfully payment section has been displayed",
					"Failed to displayed the payment section");
			Thread.sleep(6000);
			Sync.waitElementPresent(30, "xpath", "//label[contains(@for,'billing-address')]//span");
			Common.clickElement("xpath", "//label[contains(@for,'billing-address')]//span");
			Thread.sleep(5000);
			Common.dropdown("xpath", "//select[@aria-label='Billing Address']", SelectBy.TEXT, "New Address");
			Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
			Thread.sleep(4000);
			String text = Common.findElement("xpath", "//input[@name='street[0]']").getAttribute("value");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
			System.out.println(data.get(dataSet).get("City"));

//			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			 if(Common.getCurrentURL().contains("stage3"))
             {
				  Thread.sleep(4000);
                 Common.scrollIntoView("xpath", "//select[@name='region_id']");
                 Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
                 Thread.sleep(3000);
                 String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']")
                         .getAttribute("value");
                 System.out.println(Shippingvalue);
             }
			 else
			 {
				 Common.scrollIntoView("xpath", "//input[@placeholder='State/Province']");
				Common.textBoxInput("xpath", "//input[@placeholder='State/Province']", data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			// Common.textBoxInputClear("xpath", "//input[@name='postcode']");
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
					update.contains("Connecticut") || text.contains("Wallingford") || text.contains("Aberdeen"),
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
		return update;
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

	public void Giropay_payment(String Dataset) throws Exception {
		// TODO Auto-generated method stub
		String order = "";
		Sync.waitPageLoad();
		Thread.sleep(3000);	
		String expectedResult = "User should able to proceed the giropay payment method";

		try {
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//label[@for='stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unable to land o n the paymentpage");
			Common.clickElement("xpath", "//label[@for='stripe_payments']");

			Sync.waitElementPresent("xpath", "//div[@class='stripe-payments-elements no-wrap']");
			int payment = Common.findElements("xpath", "//div[@class='stripe-payments-elements no-wrap']").size();
			System.out.println(payment);
			if (payment > 0) {
				Sync.waitElementPresent("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//div[@class='stripe-dropdown-selection']");
				Common.clickElement("xpath", "//button[@class='a-btn a-btn--tertiary']");

				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
				Common.switchFrames("xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
				Sync.waitElementPresent(30, "xpath", "//button[@id='giropay-tab']");
				Common.javascriptclickElement("xpath", "//button[@id='giropay-tab']");
//				
				Common.switchToDefault();
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath", "//button[@class='action primary checkout']");
				Common.clickElement("xpath", "//button[@class='action primary checkout']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");

			} else {
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
				Common.switchFrames("xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
//				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Secure payment input frame']");
//				Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
				Sync.waitElementPresent(30, "xpath", "//button[@value='giropay']");
				Common.clickElement("xpath", "//button[@value='giropay']");
//				
				Common.switchToDefault();
				Thread.sleep(4000);
				Common.clickElement("xpath", "//button[@class='action primary checkout']");
				Thread.sleep(4000);
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
				Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!")
						|| Common.getCurrentURL().contains("success") || Common.getText("xpath",
								"//h1[@class='page-title-wrapper']").contains(sucessMessage),
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

	public void Sofort_payment(String Dataset) throws Exception {
		// TODO Auto-generated method stub
		String order = "";
		Sync.waitPageLoad();
		Thread.sleep(3000);	
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
				Common.clickElement("xpath", "//button[@class='a-btn a-btn--tertiary']");

				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
				Common.switchFrames("xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
				Thread.sleep(4000);
				String paymenttype=Common.findElement("xpath", "(//div[@class='p-PaymentMethodSelector']//button)[4]").getAttribute("id");
				System.out.println(paymenttype);
				if(paymenttype.contains("ideal-tab"))
				{
					Common.dropdown("xpath", "//select[@aria-label='Autres moyens de paiement']", Common.SelectBy.TEXT,"Sofort");
					Sync.waitElementPresent(30, "xpath", "//button[@id='sofort-tab']");
					Common.javascriptclickElement("xpath", "//button[@id='sofort-tab']");	
					Common.switchToDefault();
					Thread.sleep(4000);
					Sync.waitElementPresent(30, "xpath", "//button[@class='action primary checkout']");
					Common.clickElement("xpath", "//button[@class='action primary checkout']");
					Thread.sleep(4000);
					Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
				}
				else
				{
				Sync.waitElementPresent(30, "xpath", "//button[@id='sofort-tab']");
				Common.javascriptclickElement("xpath", "//button[@id='sofort-tab']");	
				Common.switchToDefault();
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath", "//button[@class='action primary checkout']");
				Common.clickElement("xpath", "//button[@class='action primary checkout']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
				}
			} else {
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
				Common.switchFrames("xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
				String paymenttype=Common.findElement("xpath", "(//div[@class='p-PaymentMethodSelector']//button)[4]").getAttribute("id");
				System.out.println(paymenttype);
				if(paymenttype.contains("ideal-tab"))
				{
				Common.dropdown("xpath", "//select[@aria-label='Autres moyens de paiement']", Common.SelectBy.TEXT,"Sofort");
				Sync.waitElementPresent(30, "xpath", "//button[@value='sofort']");
				Common.clickElement("xpath", "//button[@value='sofort']");			
				Common.switchToDefault();
				Thread.sleep(4000);
				Common.clickElement("xpath", "//button[@class='action primary checkout']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
				}
				else
				{
					Common.clickElement("xpath", "//button[@value='sofort']");			
					Common.switchToDefault();
					Thread.sleep(4000);
					Common.clickElement("xpath", "//button[@class='action primary checkout']");
					Thread.sleep(4000);
					Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
				}
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

	public void iDeal_payment(String Dataset) throws Exception {
		// TODO Auto-generated method stub
		String order = "";
		Sync.waitPageLoad();
		Thread.sleep(3000);	
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
				Common.clickElement("xpath", "//button[@class='a-btn a-btn--tertiary']");

				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
				Common.switchFrames("xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
				Thread.sleep(4000);
				String paymenttype=Common.findElement("xpath", "(//div[@class='p-PaymentMethodSelector']//button)[4]").getAttribute("id");
				System.out.println(paymenttype);
				if(paymenttype.contains("sofort-tab"))
				{
					Common.dropdown("xpath", "//select[@aria-label='Autres moyens de paiement']", Common.SelectBy.TEXT,"iDEAL");
					Sync.waitElementPresent(30, "xpath", "//button[@id='ideal-tab']");
					Common.javascriptclickElement("xpath", "//button[@id='ideal-tab']");	
					Common.switchToDefault();
					Thread.sleep(4000);
					Sync.waitElementPresent(30, "xpath", "//button[@class='action primary checkout']");
					Common.clickElement("xpath", "//button[@class='action primary checkout']");
					Thread.sleep(4000);
					Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
				}
				else
				{
				Sync.waitElementPresent(30, "xpath", "//button[@id='ideal-tab']");
				Common.javascriptclickElement("xpath", "//button[@id='ideal-tab']");	
				Common.switchToDefault();
				Thread.sleep(4000);
				Sync.waitElementPresent(30, "xpath", "//button[@class='action primary checkout']");
				Common.clickElement("xpath", "//button[@class='action primary checkout']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
				}
			} else {
				Sync.waitElementPresent(30, "xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
				Common.switchFrames("xpath", "//iframe[@title='Cadre de saisie sécurisé pour le paiement']");
				String paymenttype=Common.findElement("xpath", "(//div[@class='p-PaymentMethodSelector']//button)[4]").getAttribute("id");
				System.out.println(paymenttype);
				if(paymenttype.contains("sofort-tab"))
				{
				Common.dropdown("xpath", "//select[@aria-label='Autres moyens de paiement']", Common.SelectBy.TEXT,"iDEAL");
				Sync.waitElementPresent(30, "xpath", "//button[@value='ideal']");
				Common.clickElement("xpath", "//button[@value='ideal']");			
				Common.switchToDefault();
				Thread.sleep(4000);
				Common.clickElement("xpath", "//button[@class='action primary checkout']");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
				}
				else
				{
					Common.clickElement("xpath", "//button[@value='ideal']");			
					Common.switchToDefault();
					Thread.sleep(4000);
					Common.clickElement("xpath", "//button[@class='action primary checkout']");
					Thread.sleep(4000);
					Common.clickElement("xpath", "//a[contains(text(),'Authorize Test Payment')]");
				}
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

	public void PDP_video_validation(String Dataset) {
		// TODO Auto-generated method stub
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
			Common.clickElement("xpath", "//img[@alt='" + product + "']");
			Thread.sleep(4000);
			System.out.println(product);
			String name = Common.findElement("xpath", "//div[@class='m-product-overview__info-top']//h1").getText();
			String products = data.get(Dataset).get("Products").toUpperCase();
			System.out.println(product);
			Common.assertionCheckwithReport(name.contains(products),
					"validating the product should navigate to the PDP page",
					"When we click on the product is should navigate to the PDP page",
					"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//p[text()='SUSTAINABLE DESIGN']");
			Common.switchFrames("xpath", "//iframe[@title='YouTube video player']");
			Thread.sleep(4000);
			
			Sync.waitElementPresent(40, "xpath", "//button[@aria-label='Play']");
			Common.clickElement("xpath", "//button[@aria-label='Play']");
			Sync.waitForLoad();
			String video = Common.findElement("xpath", "//button[contains(@class,'ytp-play-button')]")
					.getAttribute("data-title-no-tooltip");
			System.out.println(video);
			Common.assertionCheckwithReport(video.equals("Pause"), "validating the video in PDP page",
					"video should be play in the PDP page", "Sucessfully the video has been played on the PDP page",
					"failed to play the video in PDP page");
		}
		
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the video in PDP page", "video should be play in the PDP page",
					"Unable to play the the video on the PDP page",
					Common.getscreenShot("failed to play the video in PDP page"));
			Assert.fail();
		}
	}

}

