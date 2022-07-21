package TestComponent.OXO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;

public class OxoHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public OxoHelper(String datafile) {
		excelData = new ExcelReader(datafile);
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("Oxo");
			report.createTestcase("OxoTestCases");
		} else {
			this.report = Utilities.TestListener.report;
		}
	}
	
	public void clickStoreLogo() {
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//a[@class='a-logo']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Home Page"), "validating store logo",
					"System directs the user back to the Homepage", "Sucessfully user back to home page",
					"faield to get back to homepage");
		} catch (Exception | Error e) {
			report.addFailedLog("validating store logo", "System directs the user back to the Homepage",
					"Sucessfully user back to home page",
					Common.getscreenShotPathforReport("faield to get back to homepage"));
			Assert.fail();
		}

	}
	
	public void click_singinButton() {
		try {
			Sync.waitElementClickable("xpath", "//div[@class='m-account-nav__content']/button");
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']/button");
			Sync.waitElementClickable("xpath", "//div[@class='m-account-nav__content']/button");
			Common.clickElement("xpath", "//li[@class='m-account-nav__log-in']/a");
			Common.assertionCheckwithReport(
					Common.getText("xpath", "//h3[@id='block-customer-login-heading']").equals("Sign In"),
					"To validate the signIn-buton",
					"Validate the signIn-button", "Successfully click singIn button",
					"User unabel to click singup button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validate the signIn-button ", "User navigating signin page",
					"Successfully click singIn button ",
					Common.getscreenShotPathforReport("User unable to click singup button"));
			Assert.fail();
		}
	}
	
	
	public void login_Invalidemail_Errormsg_Validation(String dataSet) {
		String errormessage = data.get(dataSet).get("errormessage");

		try {
			Sync.waitElementPresent("name", "login[username]");
			Common.textBoxInput("name", "login[username]", data.get(dataSet).get("Email"));
			Common.javascriptclickElement("xpath", "//button[contains(@class,'action login primary')]");
			Sync.waitElementVisible(30, "id", "email-error");
			String emailerror = Common.findElement("id", "email-error").getText();
			System.out.println(emailerror);
			Common.assertionCheckwithReport(emailerror.equals(errormessage),
					"To validate the error message when given invalid email or password",
					"Should dispaly the error message "+errormessage, emailerror + "error message is displayed",
					"Failed to display error message");
			clickStoreLogo();
			Sync.waitPageLoad();
		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate the error message when given invalid email ",
					"Should dispaly the error message" + errormessage, "Error message dispaly Failed ",
					Common.getscreenShotPathforReport("Failed to display the error message"));
			Assert.fail();
		}

	}

	public void login_Invalidcrdentials_Errormsg_Validation(String dataSet) {

		String errormsg = data.get(dataSet).get("errormessage");

		try {
			click_singinButton();
			Common.textBoxInputClear("name", "login[username]");
			Common.textBoxInput("name", "login[username]", data.get(dataSet).get("Email"));
			Common.textBoxInputClear("name", "login[password]");
			Common.textBoxInput("name", "login[password]", data.get(dataSet).get("Password"));
			Common.javascriptclickElement("xpath", "//button[contains(@class,'action login primary')]");
			Sync.waitPageLoad();
			Sync.waitElementVisible(30, "xpath", "//div[contains(@class,'message-error')]/div");
			String error = Common.findElement("xpath", "//div[contains(@class,'message-error')]/div").getText();
			System.out.println(error);
			Common.assertionCheckwithReport(error.equals(errormsg),
					"To validate the login page for invalid credentials ",
					"Should dispaly the error message" + errormsg, error + "error message is displayed",
					"Failed to display error message");

			clickStoreLogo();
			Sync.waitPageLoad();

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate the login page for invalid credentials ",
					"Should dispaly the error message" + errormsg,
					"Error message dispaly Failed ",
					Common.getscreenShotPathforReport("Failed to display the error message"));
			Assert.fail();

		}

	}

	public void login_Missingfields_Errormsg_Validation() {

		try {
			click_singinButton();
			Common.textBoxInputClear("name", "login[username]");
			Common.textBoxInputClear("name", "login[password]");
			Common.javascriptclickElement("xpath", "//button[contains(@class,'action login primary')]");

			String errormessage = Common.findElement("xpath", "//div[@class='mage-error']").getText();
			System.out.println(errormessage);
			Common.assertionCheckwithReport(errormessage.equals("This is a required field."),
					"verify the error message when any required fields are missinng will throw an error message",
					"Should display the error message as This is a required field.", errormessage,
					"Failed to display error message required fields");

		} catch (Exception e) {

			e.printStackTrace();
			report.addFailedLog(
					"verify the error message when any required fields are missinng will throw an error message",
					"Should display the error message as This is a required field.",
					"Failed to display the required fields Error message",
					Common.getscreenShotPathforReport("Failed to display the error message"));
			Assert.fail();

		}

	}

	
	public void Click_contactUs() {
		try {
			Sync.waitPageLoad();
			Common.scrollToElementAndClick("xpath", "//a[contains(text(),'Contact Us')]");
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Contact"), "validating Contact Us",
					"Navigate to Contact Us Page", "Sucessfully Navigate to Contact Us Page",
					"failed to get back to homepage");
		} catch (Exception | Error e) {
			report.addFailedLog("validating Contact Us", "Navigate to Contact Us Page",
					"Navigate to Contact Us Page",
					Common.getscreenShotPathforReport("faield to get back to homepage"));
			Assert.fail();
		}

	}

	public void ContactUs_data(String dataSet) {
		try {
			Sync.waitPageLoad();
			Common.switchFrames("id", "kustomer-ui-sdk-iframe");
			
			Common.textBoxInput("id", "customerEmail", data.get(dataSet).get("Email"));
			
			
			Common.assertionCheckwithReport(Common.getPageTitle().contains(""), "validating Contact Us errormessage",
					"Validate to Contact Us Page Errormessage", "Sucessfully verify error message in Contact Us Page",
					"failed to validate error message");
			
			Common.switchToDefault();
		} catch (Exception | Error e) {
			report.addFailedLog("validating Contact Us errormessage", "Navigate to Contact Us Page",
					"verify error message in Contact Us Page",
					Common.getscreenShotPathforReport("faield to get back to homepage"));
			Assert.fail();
		}

	}
	
	public void click_ChatBot() {

		try {
		
			Common.switchFrames("id", "kustomer-ui-sdk-iframe");
			
			Common.findElement("xpath", "//div[@class='chatRootIcon__pointer___QslJf']");
			Common.clickElement("xpath", "//div[@class='chatRootIcon__pointer___QslJf']");
		String chat = Common.findElement("xpath", "//div[@id='kustomer-ui-modal-root']/div[2]/div[1]/div[1]/p").getText();
			
			Common.assertionCheckwithReport(chat.equals("Answers"), "validate the ChatBot",
					"Open the ChatBot", "Sucessfully click on the ChatBot",
					"Unable to click the ChatBot");
			
			Common.findElement("xpath", "//div[@id='kustomer-ui-modal-root']/div[2]/div[1]/div[2]/div/p");
			Common.clickElement("xpath", "//div[@id='kustomer-ui-modal-root']/div[2]/div[1]/div[2]/div/p");
			
			Common.clickElement("xpath", "//button[@aria-label='New Conversation']");
			
			String newchat=Common.findElement("xpath", "//button[@aria-label='New Conversation']").getText();
			Common.assertionCheckwithReport(newchat.equals("New Conversation"), "validate the ChatBot",
					"Open the ChatBot", "Sucessfully click on the ChatBot",
					"Unable to click the ChatBot");
			
//			Common.clickElement("xpath", "//button[@aria-label='minimize chat widget']");
			
			Common.switchToDefault();
			
		} catch (Exception | Error e) {
			report.addFailedLog("validate the ChatBot", "Open the ChatBot",
					"Unable click on the ChatBot",
					Common.getscreenShotPathforReport("failed to click on the ChatBot"));
			Assert.fail();
		}

	}
	

	


}
