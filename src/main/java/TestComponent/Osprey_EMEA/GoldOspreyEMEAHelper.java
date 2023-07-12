package TestComponent.Osprey_EMEA;

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

public class GoldOspreyEMEAHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public GoldOspreyEMEAHelper(String datafile, String sheetname) {

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

public void click_SignIn() {
	try {
		Sync.waitElementPresent("xpath", "//div[@class='m-account-nav__content']");
		Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
		Sync.waitElementPresent("xpath", "//li[@class='m-account-nav__log-in']//a[text()='Sign In']");
		String SignIn = Common.getText("xpath", "//li[@class='m-account-nav__log-in']//a[text()='Sign In']");
		System.out.println(SignIn);
		Thread.sleep(1000);
		//Temp
		Common.assertionCheckwithReport(SignIn.contains("SIGN IN"),
				"To validate the user Click on the Account icon Display the SignIn Button",
				"User Should View the SignIn Button after clicking on the Account icon button",
				"User Successfully clicked on the Account icon button and Views to the SignIn button",
				"User Failed to click on the Account icon button and not displays signIn Button");
		
		Common.clickElement("xpath", "//li[@class='m-account-nav__log-in']//a[text()='Sign In']");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.assertionCheckwithReport(
				Common.getText("xpath", "//h1[@id='block-customer-login-heading']").equals("Sign In"),
				"To validate the user navigates to the SignIn page",
				"User Should Navigate to the SignIn page after clicking on the SigIn button",
				"User Successfully clicked on the singIn button and Navigate to the signIn page",
				"User Failed to click the signin button and not navigated to signIn page");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To validate the user navigates to the SignIn page",
				"user should able to land on the SignIn page after clicking on the SignIn button",
				"Unable to click on the SignIn button and not Navigated to the signIn page",
				Common.getscreenShotPathforReport(
						"Failed to click signIn button and not Navigated to the signIn page"));
		Assert.fail();
	}
}

public void login(String dataSet) {

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

public void login_Missingfields_Errormsg() {

	try {
		
		Common.textBoxInputClear("name", "login[username]");
		Common.textBoxInputClear("name", "login[password]");
		Common.javascriptclickElement("xpath", "//button[contains(@class,'action login primary')]");

		String errormessage = Common.findElement("xpath", "//div[@class='mage-error']").getText();
		System.out.println(errormessage);
		Common.assertionCheckwithReport(errormessage.equals("This is a required field."),
				"verify the error message when any required fields are missing will throw an error message",
				"Should display the error message as This is a required field.", "Error message"+ errormessage,
				"Failed to display error message required fields");

	} catch (Exception e) {

		e.printStackTrace();
		ExtenantReportUtils.addFailedLog(
				"verify the error message when any required fields are missinng will throw an error message",
				"Should display the error message as This is a required field.",
				"Failed to display the required fields Error message",
				Common.getscreenShotPathforReport("Failed to display the error message"));
		Assert.fail();

	}

}

public void login_Invalid_Email_Errormsg(String dataSet) {
	String errormessage = data.get(dataSet).get("message");

	try {
		Sync.waitElementPresent("name", "login[username]");
		Common.textBoxInput("name", "login[username]", data.get(dataSet).get("Email"));
		Common.javascriptclickElement("xpath", "//button[contains(@class,'action login primary')]");
		Sync.waitElementVisible(30, "id", "email-error");
		String emailerror = Common.findElement("id", "email-error").getText();
		System.out.println(emailerror);
		Common.assertionCheckwithReport(emailerror.equals(errormessage),
				"To validate the error message when given invalid email or password",
				"Should dispaly the error message Please enter a valid email address (Ex johndoe@domain.com).",
				"Please enter a valid email address (Ex johndoe@domain.com).error message is displayed",
				"Failed to display error message");
		
		Sync.waitPageLoad();
	} catch (Exception e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To validate the error message when given invalid email ",
				"Should dispaly the error message Please enter a valid email address (Ex johndoe@domain.com).",
				"Error message dispaly Failed ",
				Common.getscreenShotPathforReport("Failed to display the error message"));
		Assert.fail();
	}

}

public void login_Invalidcrdentials_Errormsg(String dataSet) {

	String errormsg = data.get(dataSet).get("message");

	try {
		
		Common.textBoxInputClear("name", "login[username]");
		Common.textBoxInput("name", "login[username]", data.get(dataSet).get("Email"));
		Common.textBoxInputClear("name", "login[password]");
		Common.textBoxInput("name", "login[password]", data.get(dataSet).get("Password"));
		Common.clickElement("xpath", "//span[contains(@class,'toggle-icon icon-sign-in__hide')]");
		Common.javascriptclickElement("xpath", "//button[contains(@class,'action login primary')]");
		Sync.waitPageLoad();
		Sync.waitElementVisible(30, "xpath", "//div[contains(@class,'message-error')]/div");
		String error = Common.findElement("xpath", "//div[contains(@class,'message-error')]/div").getText();
		System.out.println(error);
		Common.assertionCheckwithReport(error.equals(errormsg),
				"To validate the login page for invalid credentials ",
				"Should dispaly the error message" + errormsg, error + "error message is displayed",
				"Failed to display error message");

		
		Sync.waitPageLoad();

	} catch (Exception e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To validate the login page for invalid credentials ",
				"Should dispaly the error message" + errormsg, "Error message dispaly Failed ",
				Common.getscreenShotPathforReport("Failed to display the error message"));
		Assert.fail();

	}

}

public void clickStoreLogo() {
	try {
		Sync.waitPageLoad();
		Common.clickElement("xpath", "//a[@class='a-logo']");
		Common.assertionCheckwithReport(Common.getPageTitle().contains("Home page"), "validating store logo",
				"System directs the user back to the Homepage", "Sucessfully user back to home page",
				"failed to get back to homepage");
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating store logo", "System directs the user back to the Homepage",
				"unable to navigate to home page",
				Common.getscreenShotPathforReport("failed to get back to homepage"));
		Assert.fail();
	}

}

public void clickcountryselector() {

	try {

		Common.scrollIntoView("xpath", "//button[contains(@class,'selector action')]");

		//Sync.waitElementClickable("xpath", "//button[contains(@class,'selector action')]");
		Common.clickElement("xpath", "//button[contains(@class,'selector action')]");
		Sync.waitElementVisible("xpath", "//h2[@class='heading heading--page m-modal__headline']");
		String text = Common.findElement("xpath", "//h2[@class='heading heading--page m-modal__headline']")
				.getText();

		Common.assertionCheckwithReport(text.contains("Choose Your Location"),
				"To validate the Country Selector Pop up", "Country Selector Pop up should be dispalyed",
				"Different Countries Should be displayed", "Failed to display the Different Countries");
	}

	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the Country Selector Pop up",
				"User successfully open Country Selector Pop up", "User unable to open country Selector Pop up",
				Common.getscreenShotPathforReport("user failed to open the Country Selector Pop up"));
		Assert.fail();
	}

}

public void Selectscountries_CountrySelector(String dataSet) {

	String available = data.get(dataSet).get("CountryOptions");
	String[] Select = available.split(",");
	String[] url = data.get(dataSet).get("url").split(",");

	int i = 0;

	try {

		for (i = 1; i <= Select.length && i <= url.length; i++) {
			System.out.println(Select[i - 1]);
			Thread.sleep(3000);
			Common.scrollIntoView("xpath", "(//input[contains(@name,'countrySelector')])[" + i + "]");

			String Country = Common
					.findElement("xpath", "(//label[contains(@class,'country-item__country-label')])[" + i + "]")
					.getText();

			Sync.waitElementClickable("xpath",
					"(//label[contains(@class,'country-item__country-label')])[" + i + "]");
			Common.mouseOverClick("xpath", "(//label[contains(@class,'country-item__country-label')])[" + i + "]");
			System.out.println(Country);
			Common.assertionCheckwithReport(Country.contains(Select[i - 1]),
					"verifying the Country" + Select[i - 1], "user Selects " + Select[i - 1] + "Country",
					"user successfully Selects the country " + Select[i - 1],
					"Failed to select the country " + Select[i - 1]);

			Common.scrollIntoView("xpath", "//button[contains(@class,'primary action')]");
			Common.mouseOverClick("xpath", "//button[contains(@class,'primary action')]");
			Sync.waitPageLoad();

			String Websiteurl = Common.getCurrentURL();
			System.out.println(url[i - 1]);
			System.out.println(Websiteurl);

			Common.assertionCheckwithReport(Websiteurl.contains(url[i - 1]),
					"To validate the store logo" + Select[i - 1],
					"Should Navigated to the Osprey website" + Select[i - 1],
					"Navigated to the Osprey Denmark website" + Select[i - 1],
					"Failed to navigate to Osprey Website " + Select[i - 1]);
			Common.openNewTab();
			Common.oppenURL(data.get(dataSet).get("WebsiteURL"));
			Sync.waitPageLoad();
			clickcountryselector();

		}

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the Country Selector" + Select[i - 1],
				"user Selects the " + Select[i - 1] + "Country", "User unabel to Select Country " + Select[i - 1],
				Common.getscreenShotPathforReport("user failed to Select the Country"));
		System.out.println(Select[i - 1] + " is Missing");
		Assert.fail();

	}
	
	
																																												
	List<WebElement> countrywebelements = Common.findElements("xpath",
		"//label[@class='a-radio-button__label country-item__country-label']");

ArrayList<String> arraycountryName = new ArrayList<String>();

for (WebElement countrynames : countrywebelements) {
	arraycountryName.add(countrynames.getText());
	System.out.println("Websitedata" +arraycountryName);
}

String[] items = data.get(dataSet).get("CountryOptions").split(",");

for (int j = 0; j < items.length; j++) {
	
	System.out.println(items[j]);

	if (arraycountryName.contains(items[j])) {
	} else {

		ExtenantReportUtils.addFailedLog("To validate the Countries in Country Selector",
				"All countries are displayed ", " Selects the " + items[j] + "options",
				Common.getscreenShotPathforReport("failed to display countries"));
		Assert.fail();
	}

ExtenantReportUtils.addPassLog("To validate the Countries in Country Selector ",
			"click on the Country Selector it must display all the country options ",
			"Sucessfully displayed the countries " + arraycountryName,
			Common.getscreenShotPathforReport("countries validation"));


	
}}
}