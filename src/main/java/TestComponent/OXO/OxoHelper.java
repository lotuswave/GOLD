package TestComponent.OXO;

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

public class OxoHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	
public OxoHelper(String datafile,String sheetname) {
        
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

	public void clickStoreLogo() {
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//a[@class='a-logo']");
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Home Page"), "validating store logo",
					"System directs the user back to the Homepage", "Sucessfully user back to home page",
					"faield to get back to homepage");
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating store logo", "System directs the user back to the Homepage",
					"Unable to get back to home page",
					Common.getscreenShotPathforReport("failed to get back to homepage"));
			Assert.fail();
		}

	}

	public void verifingHomePage() {
		try {
			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Home Page"),
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

	public void verifingContactUSErrorMessage() {
		try {
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.END);
			Common.clickElement("xpath", "//a[text()='Contact Us']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Contact"), "validating contact us page",
					"User navigates to Contact Us Page",
					"Sucessfully user navigate to contactus page and able to see error message",
					"failed to navigate to contactus page");
			Sync.waitElementPresent(40, "xpath", "//iframe[contains(@src,'https://oxo')]");

			Common.switchFrames("xpath", "//iframe[contains(@src,'https://oxo')]");

			// Common.scrollIntoView("xpath", "//span[@id='recaptcha-anchor']");
			// Sync.waitElementPresent(40, "xpath", "//span[@id='recaptcha-anchor']");
			// Common.clickElement("xpath","//span[@id='recaptcha-anchor']");

			Common.scrollIntoView("xpath", "//button[text()='Submit']");
			Common.clickElement("xpath", "//button[text()='Submit']");

			Common.switchToDefault();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating contact us page", "User navigates to Contact Us Page",
					"Unable to navigate to contactus page",
					Common.getscreenShotPathforReport("failed to navigate to contactus page"));
			Assert.fail();
		}
	}

	public void UserViewsContactUsPageandSubmits(String dataSet) throws Exception {
		String country = "United States";
		try {
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.END);
			Common.clickElement("xpath", "//a[text()='Contact Us']");
			/*
			 * Common.assertionCheckwithReport(Common.getPageTitle().equals("Contact"),
			 * "validating store logo", "System directs the user back to the Homepage",
			 * "Sucessfully user back to home page", "faield to get back to homepage");
			 */
			Sync.waitElementPresent(40, "xpath", "//iframe[contains(@src,'https://oxo')]");
			Common.switchFrames("xpath", "//iframe[contains(@src,'https://oxo')]");

			Sync.waitElementPresent("xpath", "//input[@id='customerEmail']");
			Common.textBoxInput("xpath", "//input[@id='customerEmail']", data.get(dataSet).get("Email"));

			Sync.waitElementPresent("xpath", "//input[@id='messageSubject']");
			Common.textBoxInput("xpath", "//input[@id='messageSubject']", data.get(dataSet).get("FirstName"));

			Sync.waitElementPresent("xpath", "//input[@id='lastName']");
			Common.textBoxInput("xpath", "//input[@id='lastName']", data.get(dataSet).get("LastName"));

			Sync.waitElementPresent("xpath", "//input[@name='company']");
			Common.textBoxInput("xpath", "//input[@name='company']", data.get(dataSet).get("Company"));

			Sync.waitElementPresent("xpath", "//input[@id='primary']");
			Common.textBoxInput("xpath", "//input[@id='primary']", data.get(dataSet).get("phone"));

			Sync.waitElementPresent("xpath", "//input[@name='street']");
			Common.textBoxInput("xpath", "//input[@name='street']", data.get(dataSet).get("Street"));

			Sync.waitElementPresent("xpath", "//input[@name='city']");
			Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));

			Sync.waitElementPresent("xpath", "//input[@name='city']");
			Common.clickElement("xpath", "//div[@id='country']");

			Sync.waitElementPresent("xpath", "//div[@value='united-states']//span[contains(text(),'United States')]");
			Common.clickElement("xpath", "//div[@value='united-states']//span[contains(text(),'United States')]");

			Sync.waitElementPresent("xpath", "//input[@id='zipPostalCode']");
			Common.textBoxInput("xpath", "//input[@id='zipPostalCode']", data.get(dataSet).get("postcode"));

			Sync.waitElementPresent("xpath", "//div[@id='stateOrProvince']");
			Common.clickElement("xpath", "//div[@id='stateOrProvince']");

			Sync.waitElementPresent("xpath", "//span[text()='Alabama']");
			Common.clickElement("xpath", "//span[text()='Alabama']");

			Sync.waitElementPresent("xpath", "//div[@id='howCanWeHelp']");
			Common.clickElement("xpath", "//div[@id='howCanWeHelp']");
			Common.clickElement("xpath", "//span[text()='Order Issues']");

			Sync.waitElementPresent("xpath", "//div[@id='selectACategory']");
			Common.clickElement("xpath", "//div[@id='selectACategory']");
			Common.clickElement("xpath", "//span[text()='Billing Issue']");

			Sync.waitElementPresent("xpath", "//textarea[@id='messagePreview']");
			Common.textBoxInput("xpath", "//textarea[@id='messagePreview']", data.get(dataSet).get("CommetsOXO"));

			Common.scrollIntoView("xpath", "//button[text()='Submit']");
			Common.clickElement("xpath", "//button[text()='Submit']");

			Sync.waitElementPresent("xpath", "//div[@class='form-wrap']");
			int Contactussuccessmessage = Common.findElements("xpath", "//div[@class='form-wrap']").size();
			Common.assertionCheckwithReport(Contactussuccessmessage > 0, "verifying Contact us Success message ",
					"Success message should be Displayed", "Contact us Success message displayed ",
					"failed to dispaly success message");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validation  of Contact us Page", "Success message should be Displayed",
					"Contact us Success message displayed", "failed to dispaly success message");
			AssertJUnit.fail();
		}
	}

	public void Usersignin(String dataSet) throws Exception {

		// Common.findElement("xpath", "");
		Thread.sleep(3000);
		Common.textBoxInput("id", "email", data.get(dataSet).get("Email1"));
		Common.textBoxInput("id", "pass", data.get(dataSet).get("Password1"));
		// Sync.waitElementClickable("xpath", "/button[@class='action login primary
		// a-btn a-btn--primary']");
		Common.clickElement("xpath", "//button[@class='action login primary a-btn a-btn--primary']");
		Sync.waitElementVisible("xpath", "");
		Common.assertionCheckwithReport(
				Common.getText("xpath", "//span[contains(text(),'Welcome, test')]").equals("Welcome, test"),
				"Validating My Account page navigation", "user sign in and navigate to my account page",
				"Successfully navigate to my account page", "Failed to navigate my account page ");
	}

	public void MyFavoritespage(String DataSet) {

		try {
			RegClickaccount();
			Sync.waitPageLoad();
			// Common.scrollIntoView("xpath", "//small[text()='" + image + "']");
			// Common.javascriptclickElement("xpath", "//small[text()='" + image + "']");
			String Fav = data.get(DataSet).get("MYAccountlinks");
			System.out.println(Fav);
			Common.javascriptclickElement("xpath", "(//a[text()='" + Fav + "'])[2]");

			Thread.sleep(2000);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Favorites"),
					"Validating My favorites page navigation",
					"after clinking my favorites page it will navigate My favorites page",
					"Successfully navigate to My favorites page", "Failed to navigate My favorites page ");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating My favorites page navigation ",
					"after clinking my favorites page it will navigate My favorites page",
					"Unable to navigate to My favorites page ",
					Common.getscreenShotPathforReport("Failed to navigate My favorites page"));
			Assert.fail();
		}

	}

	public void Myorderspage(String DataSet) {

		try {
			RegClickaccount();
			Sync.waitPageLoad();
			// Common.javascriptclickElement("xpath", "//small[text()='" + image + "']");
			String order = data.get(DataSet).get("MYAccountlinks");
			Common.javascriptclickElement("xpath", "(//a[text()='" + order + "'])[2]");
			Thread.sleep(2000);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Orders"),
					"Validating My orders page navigation",
					"after clinking my orders page it will navigate My favorites page",
					"Successfully navigate to My orders page", "Failed to navigate My orders page ");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating My orders page navigation ",
					"after clinking my favorites page it will navigate My orders page",
					"Unable to navigate to My orders page ",
					Common.getscreenShotPathforReport("Failed to navigate My orders page"));
			Assert.fail();
		}
	}

	public void MyProductSubscription(String DataSet) {

		try {
			RegClickaccount();
			Sync.waitPageLoad();
			String product = data.get(DataSet).get("MYAccountlinks");
			Common.javascriptclickElement("xpath", "(//a[text()='" + product + "'])[2]");
			Thread.sleep(2000);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Subscriptions"),
					"Validating  My Subscriptions page navigation",
					"after clinking My Subscriptions page it will navigate My Subscriptions page",
					"Successfully navigate to My Subscriptions page", "Failed to navigateMy Subscriptions page ");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating My Subscriptions page navigation ",
					"after clinking My Subscriptions page it will navigate My Subscriptions page",
					"Unable to navigate to My Subscriptions page ",
					Common.getscreenShotPathforReport("Failed to navigate My Subscriptions page"));
			Assert.fail();
		}
	}

	public void validateCreateAccountpageNavigation() {

		try {
			Sync.waitElementClickable("xpath", "//div[@class='m-account-nav__content']/button");
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']/button");
			Sync.waitPageLoad();

			Common.javascriptclickElement("xpath", "//ul[@class='m-account-nav__links']/li[2]/a");
			Thread.sleep(2000);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Create New Customer Account"),
					"Validating Create New Customer Account page navigation",
					"after clinking Create New Customer Account page it will navigate account creation page",
					"Successfully navigate to create account page", "Failed to navigate account create page ");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Create New Customer Account page navigation ",
					"after clinking Create New Customer Account page it will navigate account creation page",
					"Unable to click singIn button ",
					Common.getscreenShotPathforReport("Failed to navigate account create page"));
			Assert.fail();
		}
	}

	public void validatingTrackmyOrderNavigation() {
		try {

			GuestClickaccount();
			Sync.waitElementClickable("xpath", "(//a[text()='Track my order'])[2]");
			Common.javascriptclickElement("xpath", "(//a[text()='Track my order'])[2]");
			Sync.waitPageLoad();

			Thread.sleep(3000);
			System.out.println(Common.getPageTitle());

			Common.assertionCheckwithReport(Common.getPageTitle().equals("Orders and Returns"),
					"Validating Orders and Returns page navigation",
					"after clicking Orders and Returns page it will navigate order retun page",
					"Successfully navigate to order retuns page", "Failed to navigate order retun page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Validating Orders and Returns page navigation",
					"after clicking Orders and Returns page it will navigate order retun page",
					"Unable navigate to order retuns page ",
					Common.getscreenShotPathforReport("Failed to navigate order retun page"));
			Assert.fail();
		}

	}

	public void minicart() {
		try {
			Sync.waitPageLoad();
			Common.javascriptclickElement("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
			String openminicart = Common.findElement("xpath", "//div[@data-block='minicart']").getAttribute("class");

			Common.assertionCheckwithReport(openminicart.contains("active"), "To validate the Minicart popup",
					"Should display the Mini Cart", "Mini Cart is Displayed", "Mini Cart is not displayed");

			Sync.waitElementPresent(30, "xpath", "//span[contains(@class,'minicart__close')]");
			Common.javascriptclickElement("xpath", "//span[contains(@class,'minicart__close')]");

			// Common.isElementNotDisplayed("xpath",
			// "//div[contains(@class,'mini-cart--active')]");
			int closeminicart = Common.findElements("xpath", "//div[contains(@class,'mini-cart--active')]").size();
			System.out.println(closeminicart);
			Common.assertionCheckwithReport(closeminicart >= 0, "To verify the Mini Cart is closed or not",
					"Should close the mini cart", "mini cart is closed ", "Mini cart is not Closed");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To verify the mini cart is closed or not", "Mini cart is closed",
					"Unable to close the  mini cart",
					Common.getscreenShotPathforReport("Failed to navigate account create page"));

			Assert.fail();

		}
	}

	public void ValidateMyAccountpage(String DataSet) {

		try {
			RegClickaccount();
			Sync.waitPageLoad();
			String myacc = data.get(DataSet).get("MYAccountlinks");
			Common.javascriptclickElement("xpath", "(//a[text()='" + myacc + "'])[2]");
			Thread.sleep(2000);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getPageTitle().equals("My Account"),
					"Validating Create New Customer Account page navigation",
					"after clinking Create New Customer Account page it will navigate account creation page",
					"Successfully navigate to create account page", "Failed to navigate account create page ");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Create New Customer Account page navigation ",
					"after clinking Create New Customer Account page it will navigate account creation page",
					"Successfully click singIn button ",
					Common.getscreenShotPathforReport("Failed to navigate account create page"));
			Assert.fail();
		}
	}

	public void Signout(String DataSet) {

		try {
			RegClickaccount();
			Sync.waitPageLoad();
			String signout = data.get(DataSet).get("MYAccountlinks");
			Common.javascriptclickElement("xpath", "(//a[text()='" + signout + "'])[2]");
			// Thread.sleep(2000);
			// System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(
					Common.getText("xpath", "//h1[contains(text(),'You are signed out')]").equals("You are signed out"),
					"Validating My Account page navigation", "user sign in and navigate to my account page",
					"Successfully navigate to my account page", "Failed to navigate my account page ");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating My Subscriptions page navigation ",
					"after clinking my favorites page it will navigate My Subscriptions page",
					"Successfully navigate to My Subscriptions page ",
					Common.getscreenShotPathforReport("Failed to navigate My Subscriptions page"));
			Assert.fail();
		}
	}

	public void toplevelnavigation(String dataSet) {
		try {
			Sync.waitElementVisible("xpath", "//button[contains(@class,'m-account-nav__trigger')]/span[2]");
			String Welcome = Common.findElement("xpath", "//button[contains(@class,'m-account-nav__trigger')]/span[2]")
					.getText();
			String logo = Common.findElement("xpath", "//a[@class='a-logo']/img").getAttribute("alt");
			System.out.println(logo);
			clickStoreLogo();
			Common.assertionCheckwithReport(Welcome.contains("Welcome, QA") && logo.contains("Logo"),
					"To validate the header menu", "Components in header menu should be dispalyed",
					"Componenets in header menu are displayed", "Failed to display the components in header menu");

			int header = Common.findElements("xpath", "//a[contains(@class,'level-top ui-corner-all')]").size();

			for (int i = 1; i <= header; i++) {
				Sync.waitElementVisible("xpath", "(//a[contains(@class,'level-top ui-corner-all')])[" + i + "]");
				Common.mouseOverClick("xpath", "(//a[contains(@class,'level-top ui-corner-all')])[" + i + "]");
				String link = Common
						.findElement("xpath", "(//a[contains(@class,'level-top ui-corner-all')]/span)[" + i + "]")
						.getText();
				System.out.println(link);
				String headerlink[] = data.get(dataSet).get("menu").split(",");
				System.out.println(headerlink[i - 1]);

				Common.assertionCheckwithReport(link.contains(headerlink[i - 1]), "to validate the header menu " + link,
						"it should clik the link" + link, link + "is clicked", "failed to click the link");
			}

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the header menu for Authorised user ",
					" it should clik the Header links and navigate to the My account menu",
					"Top level navigation unsuccessfull", "Failed to navigate through the header menu");
			Assert.fail();

		}
	}

	public void Toplevelnavigation(String DataSet) {

		try {
			int header = Common.findElements("xpath", "//a[contains(@class,'level-top ui-corner-all')]").size();

			for (int i = 1; i <= header; i++) {
				Sync.waitElementVisible("xpath", "(//a[contains(@class,'level-top ui-corner-all')])[" + i + "]");
				Common.scrollIntoView("xpath", "(//a[contains(@class,'level-top ui-corner-all')])[\" + i + \"]");
				Common.mouseOverClick("xpath", "(//a[contains(@class,'level-top ui-corner-all')])[" + i + "]");

				String link = Common
						.findElement("xpath", "(//a[contains(@class,'level-top ui-corner-all')]/span)[" + i + "]")
						.getText();
				System.out.println(link);
				String headerlink[] = data.get(DataSet).get("menu").split(",");
				System.out.println(headerlink[i - 1]);

				Common.assertionCheckwithReport(link.contains(headerlink[i - 1]), "to validate the header menu " + link,
						"it should clik the link" + link, link + "is clicked", "failed to click the link");
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating top level navigation ",
					"after clinking top level page it will navigate CLP page",
					"Successfully navigate to My Subscriptions page ",
					Common.getscreenShotPathforReport("Failed to navigate CLP page"));
			Assert.fail();
		}
	}

	public void Toplevelnavigation2() {

		try {

			// Sync.waitElementClickable("xpath", "//span[text()=' Shop']");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//span[text()=' Shop']");
			Common.clickElement("xpath", "(//span[contains(text(),' Cleaning & Organization')])[1]");
			Common.clickElement("xpath", "//a[@aria-label='Shop All  Cleaning & Organization ']");
			Thread.sleep(2000);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Shop Cleaning & Organization"),
					"Validating  top lavel  page navigation",
					"after clinking Top level page it will navigate to CLP page",
					"Successfully navigate to top level page", "Failed to navigate top level page ");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating top level navigation ",
					"after clinking top level page it will navigate CLP page",
					"Successfully navigate to My Subscriptions page ",
					Common.getscreenShotPathforReport("Failed to navigate CLP page"));
			Assert.fail();
		}
	}

	public void RegClickaccount() {

		try {
			Sync.waitElementClickable("xpath", "//button[contains(@data-bind,'scope: ')]");
			Common.clickElement("xpath", "//button[contains(@data-bind,'scope: ')]");
			String account = Common.findElement("xpath", "//div[contains(@class,'m-account-nav ')]")
					.getAttribute("class");
			System.out.println(account);
			Common.assertionCheckwithReport(account.contains("--active"), "Validating  my account button",
					"after clicking My account should display contents", "Successfully displays content",
					"Failed todisplay content ");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating  my account button",
					"after clinking My my account should display contents", "Successfully displays content",
					Common.getscreenShotPathforReport("Failed to navigate CLP page"));
			Assert.fail();
		}
	}

	public void GuestClickaccount() {

		try {
			Sync.waitElementClickable("xpath", "(//span[@class='icon-header__account a-icon-text-btn__icon'])[2]");
			Common.clickElement("xpath", "(//span[@class='icon-header__account a-icon-text-btn__icon'])[2]");
			String account = Common.findElement("xpath", "//div[contains(@class,'m-account-nav ')]")
					.getAttribute("class");
			System.out.println(account);
			Common.assertionCheckwithReport(account.contains("--active"), "Validating  my account button",
					"after clicking My account should display contents", "Successfully displays content",
					"Failed todisplay content ");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating  my account button",
					"after clinking My my account should display contents", "Successfully displays content",
					Common.getscreenShotPathforReport("Failed to navigate CLP page"));
			Assert.fail();
		}

	}

	public void verifychatbot(String Dataset) {

		try {
			Common.switchFrames("id", "kustomer-ui-sdk-iframe");
			Thread.sleep(3000);
			// Sync.waitElementPresent("xpath", "//p[contains(text(),'Answers')]");
			Common.javascriptclickElement("xpath", "//p[contains(text(),'Answers')]");

			String available = data.get(Dataset).get("Chatbot");
			String[] Select = available.split(",");

			System.out.println(Select.length);
			int i = 0;

			for (i = 1; i <= Select.length; i++) {
				System.out.println(Select[i - 1]);

				String chat = Common
						.findElement("xpath", "(//div[contains(@class,'ListItem__itemContainer')])[" + i + "]")
						.getText();
				System.out.println(chat);
				Common.assertionCheckwithReport(chat.contains(Select[i - 1]), "verifying chatbot of " + Select[i - 1],
						"chatbot  " + Select[i - 1] + " display",
						"chatbot option successfully displays " + Select[i - 1],
						"Failed to display chatbot " + Select[i - 1]);
			}

			Common.switchToDefault();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validate the ChatBot", "Open the ChatBot", "Unable click on the ChatBot",
					Common.getscreenShotPathforReport("failed to click on the ChatBot"));
			Assert.fail();
		}

	}

	public void click_ChatBot() {

		try {

			Common.switchFrames("id", "kustomer-ui-sdk-iframe");

			Common.findElement("xpath", "//div[@class='chatRootIcon__pointer___QslJf']");
			Common.clickElement("xpath", "//div[@class='chatRootIcon__pointer___QslJf']");
			String chat = Common.findElement("xpath", "//div[@id='kustomer-ui-modal-root']/div[2]/div[1]/div[1]/p")
					.getText();

			Common.assertionCheckwithReport(chat.equals("Answers"), "validate the ChatBot", "Open the ChatBot",
					"Sucessfully click on the ChatBot", "Unable to click the ChatBot");

			Common.findElement("xpath", "//div[@id='kustomer-ui-modal-root']/div[2]/div[1]/div[2]/div/p");
			Common.clickElement("xpath", "//div[@id='kustomer-ui-modal-root']/div[2]/div[1]/div[2]/div/p");

			Common.clickElement("xpath", "//button[@aria-label='New Conversation']");

			String newchat = Common.findElement("xpath", "//button[@aria-label='New Conversation']").getText();
			Common.assertionCheckwithReport(newchat.equals("New Conversation"), "validate the ChatBot",
					"Open the ChatBot", "Sucessfully click on the ChatBot", "Unable to click the ChatBot");

//			Common.clickElement("xpath", "//button[@aria-label='minimize chat widget']");

			Common.switchToDefault();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validate the ChatBot", "Open the ChatBot", "Unable click on the ChatBot",
					Common.getscreenShotPathforReport("failed to click on the ChatBot"));
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
					"To validate the signIn-buton", "Validate the signIn-button", "Successfully click singIn button",
					"User unabel to click singup button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validate the signIn-button ", "User navigating signin page",
					"unable to click singIn button ",
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
					"Should dispaly the error message Please enter a valid email address (Ex johndoe@domain.com).",
					"Please enter a valid email address (Ex johndoe@domain.com).error message is displayed",
					"Failed to display error message");
			clickStoreLogo();
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
			ExtenantReportUtils.addFailedLog("To validate the login page for invalid credentials ",
					"Should dispaly the error message" + errormsg, "Error message dispaly Failed ",
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
			ExtenantReportUtils.addFailedLog(
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
			ExtenantReportUtils.addFailedLog("validating Contact Us", "Navigate to Contact Us Page",
					"Navigate to Contact Us Page", Common.getscreenShotPathforReport("faield to get back to homepage"));
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
			ExtenantReportUtils.addFailedLog("validating Contact Us errormessage", "Navigate to Contact Us Page",
					"verify error message in Contact Us Page",
					Common.getscreenShotPathforReport("faield to get back to homepage"));
			Assert.fail();
		}

	}

	public void validateChatboxOptions(String DataSet) {

		try {
			Sync.waitPageLoad();
			Sync.waitElementClickable("xpath", "//a[@class='a-logo']");
			Common.switchFrames("id", "kustomer-ui-sdk-iframe");
			Sync.waitElementVisible(30, "xpath", "//div[@class='chatRootIcon__pointer___QslJf']");
			Common.mouseOverClick("xpath", "//div[@class='chatRootIcon__pointer___QslJf']");

			String answers = Common.findElement("xpath", "//div[contains(@class,'footer__itemContainer')]/p").getText();
			Common.assertionCheckwithReport(answers.contains("Answers"), "To validate the Answers options in Chatbox",
					"Click the Answers option to display the related options",
					"Sucessfully click the answers option button", "Unable to click the Answers option button");

			Common.javascriptclickElement("xpath", "//div[contains(@class,'footer__itemContainer')]/p");
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validate the ChatBot on the home page",
					"Open the ChatBot and answers option should be displayed",
					"Unable click on the ChatBot and answers are not displayed",
					Common.getscreenShotPathforReport("failed to click on the ChatBot"));
			Assert.fail();
		}

		List<WebElement> Answerwebelements = Common.findElements("xpath",
				"//div[contains(@class,'SearchListItem__details')]");

		ArrayList<String> arrayoptionName = new ArrayList<String>();

		for (WebElement answernames : Answerwebelements) {
			arrayoptionName.add(answernames.getText());
		}

		String[] items = data.get(DataSet).get("OXOAnswers").split(",");

		for (int i = 0; i < items.length; i++) {

			if (arrayoptionName.contains(items[i])) {
			} else {

				ExtenantReportUtils.addFailedLog("To validate the Answers options in chatbox",
						"All the Answer related options are displayed ", "Missed the " + items[i] + "options",
						Common.getscreenShotPathforReport("failed to display answersoptions"));
				Assert.fail();
			}

			ExtenantReportUtils.addPassLog("To Validate the Answers options ",
					"click on the answers options it must display all the options ",
					"Sucessfully displayed the answers options " + arrayoptionName,
					Common.getscreenShotPathforReport("Answervalidation"));
		}

		try {
			String chat = Common.findElement("xpath", "//div[contains(@class,'footer__chatContainer')]/p").getText();
			Common.javascriptclickElement("xpath", "//div[contains(@class,'footer__chatContainer')]");
			Sync.waitElementClickable(30, "xpath", "//button[contains(@class,'CLMcd button')]");
			Common.mouseOverClick("xpath", "//button[contains(@class,'CLMcd button')]");

			Sync.waitElementVisible("xpath", "(//div[contains(@class,'markdownBody')])[1]");
			String welcomemsg = Common.findElement("xpath", "(//div[contains(@class,'markdownBody')])[1]").getText();

			Common.assertionCheckwithReport(chat.contains("Chat") || welcomemsg.contains("Welcome to OXO!"),
					"To validate the Chat Conversation when user click on the chat option",
					"It should Open the Chat conversation in ChatBot",
					"Sucessfully click on the ChatBot and display the Chat conversation ",
					"Unable to display the chat conversation when user click on the chat option ");

			Common.switchToDefault();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Chat Conversation when user click on the chat option",
					"It should Open the Chat conversation in ChatBot",
					"Unable to  click on the ChatBot and not displayed the Chat conversation ",
					Common.getscreenShotPathforReport(
							"Unable to display the chat conversation when user click on the chat option"));
			Assert.fail();
		}

	}

	// System.out.println(items[]);

	/*
	 * for (int i = 0; i <= items.length; i++) {
	 * 
	 * 
	 * 
	 * 
	 * } Assert.assertEquals(searchitems, items[i - 1]);
	 * 
	 * }
	 * 
	 * String chat = Common.findElement("xpath",
	 * "//div[contains(@class,'footer__chatContainer')]/p").getText();
	 * Common.javascriptclickElement("xpath",
	 * "//div[contains(@class,'footer__chatContainer')]");
	 * Sync.waitElementClickable(30, "xpath",
	 * "//button[contains(@class,'CLMcd button')]"); Common.mouseOverClick("xpath",
	 * "//button[contains(@class,'CLMcd button')]"); //
	 * Common.isElementDisplayedonPage(30, "xpath", //
	 * "//div[contains(@class,'Thread__message')]");
	 * Sync.waitElementVisible("xpath",
	 * "(//div[contains(@class,'markdownBody')])[1]"); String welcomemsg =
	 * Common.findElement("xpath",
	 * "(//div[contains(@class,'markdownBody')])[1]").getText();
	 * 
	 * Common.assertionCheckwithReport(chat.contains("Chat") ||
	 * welcomemsg.contains("Welcome to Hydro Flask"), "validate the Chat display",
	 * "Open the Chat conversation in ChatBot",
	 * "Sucessfully click on the ChatBot and display the Chat conversation ",
	 * "Unable to dispaly the chat conversation");
	 * 
	 * Common.switchToDefault();
	 * 
	 * } catch (Exception | Error e) {
	 * ExtenantReportUtils.addFailedLog("validate the ChatBot", "Open the ChatBot",
	 * "Unable click on the ChatBot",
	 * Common.getscreenShotPathforReport("failed to click on the ChatBot"));
	 * Assert.fail(); }
	 */

	public void click_ChatBot(String DataSet) {

		try {
			Sync.waitPageLoad();
			Sync.waitElementClickable("xpath", "//a[@class='a-logo']");
			Common.switchFrames("id", "kustomer-ui-sdk-iframe");

			Sync.waitElementVisible(30, "xpath", "//div[@class='chatRootIcon__pointer___QslJf']");
			Common.mouseOverClick("xpath", "//div[@class='chatRootIcon__pointer___QslJf']");

			String answers = Common.findElement("xpath", "//div[contains(@class,'footer__itemContainer')]/p").getText();
			Common.assertionCheckwithReport(answers.contains("Answers"), "validate the ChatBot", "Open the ChatBot",
					"Sucessfully click on the ChatBot", "Unable to click the ChatBot");

			Common.javascriptclickElement("xpath", "//div[contains(@class,'footer__itemContainer')]/p");

			int elements = Common.findElements("xpath", "//div[contains(@class,'SearchListItem__details')]").size();

			String items[] = data.get(DataSet).get("OXOAnswers").split(",");

			for (int i = 1; i <= elements && i <= items.length; i++) {
				System.out.println(items[i - 1]);
				String searchitems = Common
						.findElement("xpath", "(//div[contains(@class,'SearchListItem__title')])[" + i + "]").getText();
				System.out.println(searchitems);
				Assert.assertEquals(searchitems, items[i - 1]);

			}

			String chat = Common.findElement("xpath", "//div[contains(@class,'footer__chatContainer')]/p").getText();
			Common.javascriptclickElement("xpath", "//div[contains(@class,'footer__chatContainer')]");
			Sync.waitElementClickable(30, "xpath", "//button[contains(@class,'CLMcd button')]");
			Common.mouseOverClick("xpath", "//button[contains(@class,'CLMcd button')]");
			// Common.isElementDisplayedonPage(30, "xpath",
			// "//div[contains(@class,'Thread__message')]");
			Sync.waitElementVisible("xpath", "(//div[contains(@class,'markdownBody')])[1]");
			String welcomemsg = Common.findElement("xpath", "(//div[contains(@class,'markdownBody')])[1]").getText();

			Common.assertionCheckwithReport(chat.contains("Chat") || welcomemsg.contains("Welcome to Hydro Flask"),
					"validate the Chat display", "Open the Chat conversation in ChatBot",
					"Sucessfully click on the ChatBot and display the Chat conversation ",
					"Unable to dispaly the chat conversation");

			Common.switchToDefault();

		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validate the ChatBot", "Open the ChatBot", "Unable click on the ChatBot",
					Common.getscreenShotPathforReport("failed to click on the ChatBot"));
			Assert.fail();
		}

	}

	public void clickcountryselector() {

		try {

			Common.scrollIntoView("xpath", "//button[contains(@class,'selector action')]");

			Sync.waitElementClickable("xpath", "//button[contains(@class,'selector action')]");
			Common.clickElement("xpath", "//button[contains(@class,'selector action')]");
			Sync.waitElementVisible("xpath", "//h1[@class='heading heading--page m-modal__headline']");
			String text = Common.findElement("xpath", "//h1[@class='heading heading--page m-modal__headline']")
					.getText();

			Common.assertionCheckwithReport(text.contains("Choose Your Location"),
					"To validate the Country Selector Pop up", "Country Selector Pop up should be dispalyed",
					"Different Countries Should be displayed", "Failed to display the Different Countries");
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Country Selector Pop up",
					"User successfully open Country Selector Pop up", "User unabel to open country Selector Pop up",
					Common.getscreenShotPathforReport("user failed to open the Country Selector Pop up"));
			Assert.fail();
		}

	}

	public void Verify_Available_Selections(String dataSet) {

		String available = data.get(dataSet).get("CountryOptions");
		String[] Select = available.split(",");
		String[] url = data.get(dataSet).get("url").split(",");

		int i = 0;

		try {

			for (i = 1; i <= Select.length && i <= url.length; i++) {
				System.out.println(Select[i - 1]);

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

				// Sync.waitElementClickable("xpath", "//a[@class='logo']/img");
				// String logo = Common.findElement("xpath",
				// "//a[@class='logo']/img").getAttribute("alt");

				Common.assertionCheckwithReport(Websiteurl.contains(url[i - 1]),
						"To validate the store logo" + Select[i - 1],
						"Should Navigated to the Hydroflask website" + Select[i - 1],
						"Navigated to the Hydroflask US website" + Select[i - 1],
						"Failed to navigate to Hydroflask Website " + Select[i - 1]);
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
	}

	public void Promobanner() {
		try {
			Common.clickElement("xpath", "//span[text()='See Details']");
			Thread.sleep(2000);

			Common.assertionCheckwithReport(
					Common.getText("xpath", "//strong[text()='Free Ground Shipping']").equals("Free Ground Shipping"),
					"To validate the Popup of Free Ground Shipping", "Validate the Pop up of Free Ground Shipping ",
					"Successfully displays Free Ground Shipping Pop up",
					"User unabel display Free Ground Shipping Pop up");
		} catch (Exception e) {

			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("Validate the Popup of Free Ground Shipping",
					"User Opens the Free Ground Shipping Pop up", "Successfully displays Free Ground Shipping Pop up",
					Common.getscreenShotPathforReport("User unabel to display Free Ground Shipping Pop up"));

			Assert.fail();

		}
	}

	public void Validating_search() {

		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
			String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
			Common.assertionCheckwithReport(open.contains("active"), "User searches using the search field",
					"User should able to click on the search button", "Search expands to the full page",
					"Sucessfully search bar should be expand");
			Sync.waitElementPresent("xpath", "//span[contains(@class,'icon-header__s')]");
			Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
			String close = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
			Common.assertionCheckwithReport(close.contains("c-header-search"), "User searches using the search field",
					"User should able to click on the close button", "Search box closed",
					"Sucessfully search bar should be expand");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validate the User searches using the search field ",
					"Clicking on search bar it should expand and click on close button",
					" sucessfully clicks on close button ",
					Common.getscreenShotPathforReport("User unable to close the search button"));

			Assert.fail();
		}

	}

	public void headerlinks(String category) {

		String expectedResult = "User should click the" + category;
		try {

			Sync.waitElementClickable("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");
			Thread.sleep(3000);
			Common.mouseOverClick("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");
			Thread.sleep(3000);

			try {
				Common.mouseOver("xpath", "//span[contains(text(),'" + category + "')]");
			} catch (Exception e) {
				Common.clickElement("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");
			}
			Common.clickElement("xpath", "//span[contains(text(),'" + category + "')]");
			Common.clickElement("xpath", "//a[text()='Shop All']");

			expectedResult = "User should select the " + category + " category";
			int sizebotteles = Common.findElements("xpath", "//span[contains(text(),'" + category + "')]").size();
			Common.assertionCheckwithReport(sizebotteles > 0,
					"validating the product category as " + category + " from navigation menu ", expectedResult,
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

	public void minicart_freeshipping() {

		try {
//	        		
			Sync.waitPageLoad();
			for (int i = 0; i <= 10; i++) {
				Sync.waitElementPresent("xpath", "//img[@class='m-product-card__image']");
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//img[@class='m-product-card__image']");
				String s = webelementslist.get(i).getAttribute("src");
				System.out.println(s);
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			Common.mouseOver("xpath", "(//img[@class='m-product-card__image'])[3]");
			Sync.waitElementPresent("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
			List<WebElement> element = Common.findElements("xpath", "//span[text()='Add to Bag']");
			Thread.sleep(6000);
			element.get(2).click();
			Sync.waitPageLoad();
			click_minicart();
			String shipping = Common.findElement("xpath", "//div[contains(@class,'label-')]").getText();
			if (shipping.contains("left for Free Shipping.")) {
				Sync.waitElementPresent(30, "xpath", "//span[contains(@class,'minicart__close')]");
				Common.javascriptclickElement("xpath", "//span[contains(@class,'minicart__close')]");

				Common.scrollIntoView("xpath", "(//img[@class='m-product-card__image'])[9]");
				Common.mouseOver("xpath", "(//img[@class='m-product-card__image'])[9]");
				List<WebElement> elements = Common.findElements("xpath", "//span[text()='Add to Bag']");
				Common.mouseOver("xpath", "//button[@title='Add to Cart']");
				Thread.sleep(5000);
				elements.get(8).click();
				Thread.sleep(5000);
				click_minicart();

			} else {
				Assert.fail();
			}
			String Freeshipping = Common.findElement("xpath", "//div[contains(@class,'label-')]").getText();
			Common.assertionCheckwithReport(Freeshipping.equals("Good news: your order will be delivered for Free."),
					"validating the free shipping in mini cart",
					"Free shipping should be avaliable for selected products",
					"Successfully free shipping is appiled for selected products", "Failed to see free shipping");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the free shipping in mini cart",
					"Free shipping should be avaliable for selected products",
					"Unable to see free shipping is appiled for selected products",
					Common.getscreenShot("Failed to see free shipping"));
			Assert.fail();
		}

	}

	public void click_minicart() {
		try {
			Thread.sleep(8000);
			Common.scrollIntoView("xpath", "//a[contains(@class,'c-mini')]");
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

	public void minicart_delete(String Dataset) {

		String deleteproduct = data.get(Dataset).get("Products");
		try {
			String subtotal = Common.getText("xpath", "//span[@class='c-mini-cart__subtotal-amount']//span")
					.replace("$", "");
			Float subtotalvalue = Float.parseFloat(subtotal);
			String productname = Common.findElement("xpath", "(//div[@class='m-mini-product-card']//a)[2]").getText();
			String productamount1 = Common.getText("xpath", "(//span[@class='minicart-price']//span)[1]").replace("$",
					"");
			Float productamount1value = Float.parseFloat(productamount1);
			if (productname.equals(deleteproduct)) {
				Sync.waitElementPresent("xpath",
						"//div[@class='m-mini-product-card']//span[contains(@class,'icon-cart__remove')]");
				Common.clickElement("xpath",
						"//div[@class='m-mini-product-card']//span[contains(@class,'icon-cart__remove')]");
				Common.clickElement("xpath", "//button[contains(@class,'a-btn a-btn--primary action-p')]//span");
			} else {
				Assert.fail();
			}
			Thread.sleep(6000);
			String subtotal1 = Common.getText("xpath", "//span[@class='c-mini-cart__subtotal-amount']//span")
					.replace("$", "").replace(",", "");
			Float subtotal1value = Float.parseFloat(subtotal1);
			Thread.sleep(8000);
			String productamount = Common.getText("xpath", "//span[@class='minicart-price']//span").replace("$", "")
					.replace(",", "");
			Float productamountvalue = Float.parseFloat(productamount);
			Float Total = subtotalvalue - productamount1value;
			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			System.out.println(Total);
			System.out.println(ExpectedTotalAmmount2);
			Thread.sleep(4000);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(ExpectedTotalAmmount2.equals(productamount),
					"validating the delete operation and subtotal",
					"The product should be delete from mini cart and subtotal should change",
					"Successfully product delete from the mini cart and subtotal has been changed",
					"Failed to delete the product from cart and subtotal not changed");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the delete operation and subtotal",
					"The product should be delete from mini cart and subtotal should be change",
					"unable to delete product from the mini cart and subtotal has not changed", Common.getscreenShot(
							"Failed to delete the product from the mini cart and subtotal has not changed"));
			Assert.fail();
		}

	}

	public void minicart_update(String Dataset) {

		String quantity = data.get(Dataset).get("Products");
		try {

			String Subtotal = Common.getText("xpath", "//span[@class='c-mini-cart__subtotal-amount']//span")
					.replace("$", "");
			Float subtotalvalue = Float.parseFloat(Subtotal);
			System.out.println(subtotalvalue);
			Common.clickElement("xpath", "//select[@class='a-select-menu cart-item-qty']");
			Common.dropdown("xpath", "//select[@class='a-select-menu cart-item-qty']", Common.SelectBy.VALUE,
					data.get(Dataset).get("Products"));
			Common.clickElement("xpath", "//span[text()='Update']");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//p[@class='c-mini-cart__total-counter']//strong");
			String cart = Common.findElement("xpath", "//p[@class='c-mini-cart__total-counter']//strong").getText();
			System.out.println(cart);
			String Subtotal2 = Common.getText("xpath", "//span[@class='c-mini-cart__subtotal-amount']//span")
					.replace("$", "");
			Float subtotalvalue2 = Float.parseFloat(Subtotal2);
			System.out.println(subtotalvalue2);
			Float Total = subtotalvalue * 3;
			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			System.out.println(ExpectedTotalAmmount2);
			System.out.println(Total);
			Common.assertionCheckwithReport(quantity.equals(cart) && ExpectedTotalAmmount2.equals(Subtotal2),
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

	public void minicart_click_productname() {

		try {
			String minicartproduct = Common
					.findElement("xpath", "//div[@class='m-mini-product-card']//a[@class='a-product-name']").getText();
			Common.clickElement("xpath", "//div[@class='m-mini-product-card']//a[@class='a-product-name']");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().contains(minicartproduct),
					"validating the product navigating to the PDP page", "The product Should navigate to the PDP page",
					"Successfully product navigate to the PDP page", "Failed to Navigate Product to the PDP page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the product navigating to the PDP page",
					"The product Should navigate to the PDP page", "unable to navigate product to the PDP page",
					Common.getscreenShot("Failed to Navigate the product to the PDP page"));
			Assert.fail();
		}

	}

	public void minicart_product_close() {

		try {
			click_minicart();
			Common.clickElement("xpath", "//span[contains(@class,'icon-cart__r')]");
			Sync.waitElementPresent("xpath", "//aside[@class='modal-popup confirm _show']");
			String minicartpopup = Common.findElement("xpath", "//aside[@class='modal-popup confirm _show']")
					.getAttribute("class");
			Common.assertionCheckwithReport(minicartpopup.contains("_show"),
					"validating the popup when you click on delete", "The Popup should be displayed",
					"Successfully Popup is displayed when we click on the delete button",
					"Failed to Display the popup");
			String popup = Common.findElement("xpath", "//h1[@data-role='title']").getText();
			if (popup.equals("Remove Item")) {
				Common.clickElement("xpath", "//button[contains(@class,'a-btn a-btn--secondary acti')]");
			} else {
				Assert.fail();
			}
			Common.clickElement("xpath", "//span[contains(@class,'icon-cart__r')]");
			Sync.waitElementPresent("xpath", "//aside[@class='modal-popup confirm _show']");
			Common.assertionCheckwithReport(minicartpopup.contains("_show"),
					"validating the popup when you click on delete", "The Popup should be displayed",
					"Successfully Popup is displayed when we click on the delete button",
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

	public void minicart_viewcart() {

		try {
			Sync.waitElementPresent("xpath", "//p[@class='c-mini-cart__total-counter']//strong");
			String minicart = Common.findElement("xpath", "//p[@class='c-mini-cart__total-counter']//strong").getText();
			Sync.waitElementPresent("xpath", "//div[contains(@class,'sub-section')]/a");
			Common.clickElement("xpath", "//div[contains(@class,'sub-section')]/a");
			String viewcart = Common.findElement("xpath", "//span[@class='t-cart__items-count']").getText();
			Common.assertionCheckwithReport(
					viewcart.contains(minicart) && Common.getCurrentURL().contains("checkout/cart"),
					"validating the navigation to the view cart", "User should navigate to the view cart page",
					"Successfully navigate to the view cart page", "Failed to navigate to the view and edit cart page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the navigation to the view cart",
					"User should navigate to the view cart page", "unable to  navigate to the view cart page",
					Common.getscreenShot("Failed to navigate to the view cart page"));

			Assert.fail();

		}

	}

	public void minicart_Checkout() {

		try {
			click_minicart();
			String minicart = Common.findElement("xpath", "//p[@class='c-mini-cart__total-counter']//strong").getText();
			Sync.waitElementPresent("xpath", "//button[@title='Checkout']");
			Common.clickElement("xpath", "//button[@title='Checkout']");
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

	public void validateaccountcreationpassword(String dataSet) {
		try {

			Sync.waitPageLoad();
			Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));
			Common.textBoxInput("id", "email_address", Utils.getEmailid());
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password1"));
			String classes = Common.findElement("id", "validation-classes").getAttribute("class");
			String textlength = Common.findElement("id", "validation-length").getAttribute("class");
			Common.actionsKeyPress(Keys.PAGE_DOWN);

			Common.assertionCheckwithReport(classes.contains("complete") && textlength.contains("complete"),
					"Password is validated", "password should be validate", "failed to validate password");
			Common.actionsKeyPress(Keys.UP);
			Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password1"));

			Sync.waitElementClickable("xpath", "//button[@title='Sign Up']");
			Common.clickElement("xpath", "//button[@title='Sign Up']");
			Sync.waitPageLoad();
			Sync.waitElementVisible("xpath", "//h1[@class='page-title-wrapper h2']");
			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"),
					"validating the  my Account page",
					"User should able to navigate to the my account page after clicking on submit button",
					"Sucessfully navigate to the My account page ", "failed to navigates to My Account Page");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  my Account page",
					"User should able to navigate to the my account page after clicking on submit button",
					"unable to navigate to the My account page",
					Common.getscreenShot("failed to navigates to My Account Page"));
			Assert.fail();

		}

	}

	public void verifypromobanner() {

		try {
			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "(//div[@class='m-promo-banner__container'])[1]").size();
			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().equals("OXO Home Page"),
					"validating CMS promobanner", "System directs the CMS promobanner",
					"Sucessfully directs the cms promobanner ", "faield to get CMS promobanner");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating CMS promobanner", "System directs the CMS promobanner",
					"unable to direct the cms promobanner",
					Common.getscreenShotPathforReport("faield to get CMS promobanner"));
			Assert.fail();
		}
	}

	public void CMSpromobanner() {

		try {
			Sync.waitElementPresent("xpath", "(//div[@class='slick-initialized slick-slider']//a//span)[2]");
			String message1 = Common
					.findElement("xpath", "(//div[@class='slick-initialized slick-slider']//a//span)[2]").getText();
			System.out.println(message1);
			String message3 = Common.findElement("xpath", "(//div[@class='slick-initialized slick-slider']//a)[2]")
					.getAttribute("href");
			System.out.println(message3);
			if (message3.equals("https://mcloud-na-stage.oxo.com/#") || message1.equals("Marketing Deals")) {
				Common.clickElement("xpath", "//span[text()='Marketing Deals']");
				Common.assertionCheckwithReport(
						Common.getText("xpath", "//strong[text()='Free Ground Shipping']")
								.equals("Free Ground Shipping"),
						"To validate the Popup of Free Ground Shipping", "Validate the Pop up of Free Ground Shipping ",
						"Successfully displays Free Ground Shipping Pop up",
						"User unabel display Free Ground Shipping Pop up");
			} else {
				Assert.fail();
			}

		} catch (Exception | Error e) {

			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("Validate the See Details link",
					"User Opens the Free Ground Shipping Pop up", "unable to display Free Ground Shipping Pop up",
					Common.getscreenShotPathforReport("User failed to display Free Ground Shipping Pop up"));

			Assert.fail();

		}
	}

	public void closepromobanner() {

		try {
			Common.clickElement("xpath", "//span[@aria-label='Close']");
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Home Page"),
					"validating store logo", "System directs the user back to the Homepage",
					"Sucessfully user back to home page", "faield to get back to homepage");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating CMS promobanner", "System directs the CMS promobanner",
					"unable to direct  cms promobanner",
					Common.getscreenShotPathforReport("faield to get CMS promobanner"));
			Assert.fail();
		}
	}

	public void Validate_eyeicon(String DateSet) {
		// TODO Auto-generated method stub
		try {

			Thread.sleep(3000);
			Common.textBoxInput("id", "email", data.get(DateSet).get("Email1"));
			Common.textBoxInput("id", "pass", data.get(DateSet).get("Password1"));
			String eyeicon = Common.findElement("xpath", "//span[contains(@class,'m-password')]").getAttribute("class");
			System.out.println(eyeicon);
			String password = Common.findElement("xpath", "//input[@name='login[password]']").getAttribute("type");

			Common.assertionCheckwithReport(eyeicon.contains("hide") && password.equals("password"),
					"validating eye icon password filed", "Eye icon is in hide", "Sucessfully displays eyeicon hide",
					"faield to display eye icon hide");
			Common.clickElement("xpath", "//span[@class='m-password-input--toggle-icon icon-sign-in__hide']");
			String Eyeicon = Common.findElement("xpath", "//span[contains(@class,'m-password')]").getAttribute("class");
			System.out.println(Eyeicon);
			String Text = Common.findElement("xpath", "//input[@name='login[password]']").getAttribute("type");
			Common.assertionCheckwithReport(Eyeicon.contains("show") && Text.equals("text"),
					"validating eye icon password field", "Eye icon is in hide", "Sucessfully displays eyeicon hide",
					"faield to display eye icon hide");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating login page", "user directs to login page",
					"Sucessfully directs thelogin page", Common.getscreenShotPathforReport("faield to login the page"));
			Assert.fail();

		}
	}

	public void validate_signin() {
		// TODO Auto-generated method stub
		try {
			Common.clickElement("xpath", "//button[@class='action login primary a-btn a-btn--primary']");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().contains("OXO Home Page"),
					"To validate the user lands on Home page after successfull login", "Should land on Home Page",
					"User lands on Home Page", "User failed to login");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating user login ", "user directs to login page",
					"Sucessfully user the login page",
					Common.getscreenShotPathforReport("user faield to login the page"));
			Assert.fail();

		}
	}

	public void addtocart(String Dataset) {
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
			Sync.waitElementPresent("xpath", "//span[text()='Add to Bag']");
			Common.clickElement("xpath", "//span[text()='Add to Bag']");

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

	public void validate_ExistingUser_Login_Checkoutpage(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
			Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']",
					data.get(dataSet).get("Email"));
			Sync.waitElementVisible("xpath", "//div[@class='note note--warning']");
			String validatingtext = Common.findElement("xpath", "//div[@class='note note--warning']").getText();
			System.out.println(validatingtext);
			int password = Common.findElements("xpath", "//span[text()='Password']").size();
			System.out.println(password);
			Sync.waitElementPresent("xpath", "//a[@class='action remind']/span");
			String forgotpassword = Common.findElement("xpath", "//a[@class='action remind']/span").getText();
			System.out.println(forgotpassword);

			Common.textBoxInput("xpath", "//input[@name='password']", data.get(dataSet).get("Password"));
			String passworddots = Common.findElement("xpath", "//input[@name='password']").getAttribute("type");
			System.out.println(passworddots);

			Common.assertionCheckwithReport(
					password > 0
							&& validatingtext
									.contains("You already have an account with us. Sign in or continue as guest.")
							&& passworddots.contains("password") && forgotpassword.contains("Forgot Your Password?"),
					"To Validate the warning message , password , forgot password , password in dots",
					" Should display the warning message , pasword , forgot password link, password in dots",
					"successfully displayed warning message  ,pasword , forgot password , password in dots ",
					"failed to display warning message , pasword , forgot password , password in dots");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"verify the  warning message ,pasword , forgot password , password in dots",
					"Should display warning message ,pasword , forgot password , password in dots ",
					"Unable to display warning message  ,pasword , forgot password , password in dots",
					Common.getscreenShotPathforReport(
							"faield  to display warning message  ,pasword , forgot password , password in dots "));
			Assert.fail();

		}
	}

	public void Validate_invalid_Signin_Checkoutpage() {
		// TODO Auto-generated method stub
		try {

			Common.clickElement("xpath", "//button[contains(@class,'action login primary')]");
			Sync.waitPageLoad();
			Thread.sleep(5000);

			String errormessage = Common.findElementBy("xpath", "//div[contains(@class,'message-error')]").getText();
			Common.assertionCheckwithReport(errormessage.contains("Invalid login or password."),
					"To validate incorrect password in checkout page",
					"Error message should be displayed for incorrect password", "Error message displayed",
					"failed to display error message in checkout page for incorrect password");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate incorrect password in checkout page",
					"Error message should be displayed for incorrect password",
					"failed to display error message in checkout page for incorrect password",
					Common.getscreenShotPathforReport("failed to display error message in checkout page"));
			Assert.fail();

		}
	}

	public void Click_forgotpasswordLink_Checkout() {
		// TODO Auto-generated method stub
		try {
			Common.findElement("xpath", "//span[text()='Forgot Your Password?']");
			Common.clickElement("xpath", "//span[text()='Forgot Your Password?']");
			String forgotpassword = Common.findElement("xpath", "//h1[text()='Forgot Your Password?']").getText();
			Common.assertionCheckwithReport(forgotpassword.contains("Forgot Your Password?"),
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

	public void Validate_Valid_Signin_Checkoutpage() {
		// TODO Auto-generated method stub
		try {

			Common.clickElement("xpath", "//button[contains(@class,'action login primary')]");
			Sync.waitPageLoad();
			// Thread.sleep(3000);
			Sync.waitElementInvisible("id", "customer-email-fieldset");
			System.out.println(Common.getPageTitle());
			int emailsection = Common.findElements("id", "customer-email-fieldset").size();
			System.out.println(emailsection);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Checkout") && emailsection <= 0,
					"To validate register user is able to login and User is on checkout page",
					"User should be ale to login and is on the Checkout page",
					"Register User login sucessfully and the user is on checkout page ",
					"failed to login in checkout page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validate user successfully logins from checkout page",
					"User should login from Checkout page", "User failed logged in from checkout page ",
					Common.getscreenShotPathforReport("faield login from checkout page"));
			Assert.fail();

		}
	}

	public String addDeliveryAddress(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String method = "";

		try {
			Thread.sleep(5000);
			Sync.waitElementVisible("id", "customer-email");
			Common.textBoxInput("id", "customer-email", data.get(dataSet).get("Email"));
		} catch (NoSuchElementException e) {
			minicart_Checkout();
			Common.textBoxInput("id", "customer-email", data.get(dataSet).get("Email"));

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

				Sync.waitElementPresent(30, "xpath", "//tr[@class='row']//td[@class='col col-method' and @id]");
				method = Common.findElement("xpath", "//tr[@class='row']//td[@class='col col-method' and @id]")
						.getText();
				Common.clickElement("xpath", "//tr[@class='row']//td[@class='col col-method' and @id]");

//			Sync.waitElementPresent(30, "xpath", "//input[@value='flatrate_flatrate']");
//			Common.clickElement("xpath", "//input[@value='flatrate_flatrate']");
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
		return method;
	}
	
	public void addtocart_in_PDP_page(String Dataset) {
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
			Common.clickElement("xpath","//img[@alt='" + products + "']");
			Sync.waitElementPresent("xpath", "//span[text()='Add to Bag']");
			Common.clickElement("xpath", "//span[text()='Add to Bag']");

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
	public void Shippingpage_Validation() {
	    try {
	    Boolean logo =    Common.findElement("xpath","//a[@class='a-logo']").isDisplayed();
	    System.out.println(logo);
	    Boolean Backtocart =    Common.findElement("xpath","//a[@title='Back to Cart']").isDisplayed();
	    System.out.println(Backtocart);
	    Boolean Shipping =    Common.findElement("xpath","//li[@class='opc-progress-bar-item _active']").isDisplayed();
	    System.out.println(Shipping);
	Common.assertionCheckwithReport(Common.getPageTitle().contains("Checkout"),"To validate the user lands on Home page after successfull login",
	        "Should land on Home Page","User lands on Home Page", "User failed to login");


	    
	    } catch (Exception e) {
	        e.printStackTrace();
	        ExtenantReportUtils.addFailedLog("To validate the user lands on the shipping page",
	                "User should land on the shipping page", "User failed to land on the shipping page",
	                "shipping navigation failed");
	        Assert.fail();



	   }
	}


	public void Paymentmethods_Validation() {
	    try {
	        Common.switchFrames("xpath", "//iframe[@title='Secure payment input frame']");
	        Common.scrollIntoView("xpath", "//span[text()='Card']");
	        String Card = Common.findElement("xpath","//span[text()='Card']").getText();
	        Assert.assertEquals(Card, "Card");
	        String Klarna = Common.findElementBy("xpath","//span[text()='Klarna']").getText();
	        Assert.assertEquals(Klarna, "Klarna");
	        String Afterpay = Common.findElementBy("xpath","//span[text()='Afterpay']").getText();
	        Assert.assertEquals(Afterpay, "Afterpay");
	        String CardNumber = Common.findElementBy("xpath","//input[@id='Field-numberInput']").getAttribute("id");
	        Assert.assertEquals(CardNumber, "Field-numberInput");
	        String Expiration = Common.findElementBy("xpath","//input[@id='Field-expiryInput']").getAttribute("id");
	        Assert.assertEquals(Expiration, "Field-expiryInput");
	        String CVV = Common.findElementBy("xpath","//input[@id='Field-cvcInput']").getAttribute("id");
	        Assert.assertEquals(CVV, "Field-cvcInput");
	    
	        String Country = Common.findElementBy("xpath","//select[@id='Field-countryInput']").getAttribute("id");
	        Assert.assertEquals(Country, "Field-countryInput");
	        Common.switchToDefault();
	        String Placeorder = Common.findElementBy("xpath","//span[text()='Place Order']").getText();
	        Assert.assertEquals(Placeorder, "Place Order");



	       String Paypal = Common.findElementBy("xpath","//label[@for='paypal_express']").getAttribute("for");
	        Assert.assertEquals(Paypal, "paypal_express");
	        
	        Common.clickElement("xpath","//button[@id='block-giftcard-heading']");
	        String Giftcard = Common.findElementBy("xpath","//form[@id='giftcard-form']").getAttribute("id");
	        Assert.assertEquals(Giftcard, "giftcard-form");
	    



	       
	        
	    }catch (Exception e) {
	        e.printStackTrace();
	        ExtenantReportUtils.addFailedLog("To validate the Payment methods on the payment page",
	                "User should locate different payment methods on the payment page", "User failed to locate different payment methods on the payment page",
	                "failed to locate the payment methods");
	        Assert.fail();

	    }

	   }
	    public void Paymentpage_Validation() {
	    	 try {
	             Boolean Payment_Review = Common.findElement("xpath","//li[@class='opc-progress-bar-item _active']").isDisplayed();
	             System.out.println(Payment_Review);
	             Common.findElement("xpath", "//label[@for='stripe_payments']").isDisplayed();
	             Common.clickElement("xpath", "//label[@for='stripe_payments']");
	            Boolean checkbox  =    Common.findElement("xpath", "//fieldset[@style='display: none;']").isSelected();
	            
	            System.out.println(checkbox);
	            
	            //    int  size =    Common.findElements("xpath", "//input[@name='billing-address-same-as-shipping']").size();
	            //    Common.assertionCheckwithReport(size>0, "validating the My billing and shipping address are same checkbox", "Checkbox is selected", "Failed to check the Mybilling and shiipind address checkbox");
	                    
	                } catch (Exception e) {
	                	 e.printStackTrace();
	                     ExtenantReportUtils.addFailedLog("To validate the user lands on the payment page",
	                             "User should land on the payment page", "User failed to land on the payment page",
	                             "Payment navigation failed");
	                     Assert.fail();



	                }



	            }
	               
	  
	    public void ordersummary_validation(String dataset) throws Exception {
			// TODO Auto-generated method stub
	    	String expectedResult = "Shipping block will have shipping address";
			try{
				Boolean Subtotal = Common.findElement("xpath", "//tr[@class='totals sub']//span[@class='price']").isDisplayed();
				System.out.println(Subtotal);
				Boolean shipping = Common.findElement("xpath", "//tr[@class='totals shipping excl']//span[@class='price']").isDisplayed();
				System.out.println(shipping);
				Boolean Tax = Common.findElement("xpath", "//tr[@class='totals-tax']//span[@class='price']").isDisplayed();
				System.out.println(Tax);
				Boolean ordertotal = Common.findElement("xpath", "//tr[@class='grand totals']//span[@class='price']").isDisplayed();
				System.out.println(ordertotal);
				Boolean item = Common.findElement("xpath", "//div[@class='m-accordion__title']").isDisplayed();
				System.out.println(item);
		
				Common.clickElement("xpath", "//div[@class='m-accordion__title']");
				Boolean image = Common.findElement("xpath", "//img[@title='Pet POP Container (5.8 Qt.)']").isDisplayed();
				System.out.println("img"+image);
				
				String Product= Common.findElement("xpath", "//div[@class='m-mini-product-card__name']").getText();
				System.out.println(Product);
				String productname = data.get(dataset).get("OrderSummaryProduct");
				System.out.println(productname);
				Common.assertionCheckwithReport(Product.equals(productname), "validate the Product name in order summary block",
						"Product name should display order summery block", "Sucessfully display order summary block", "failed to display product name in oder summary block");

				Boolean Qty = Common.findElement("xpath", "(//p[@class='a-product-attribute'])[1]").isDisplayed();
				System.out.println(Qty);
				Boolean Price = Common.findElement("xpath", "(//p[@class='a-product-attribute'])[2]").isDisplayed();
				System.out.println(Price);
				
				int size = Common.findElements("xpath", "//div[@class='shipping-information']").size();
				
				Common.assertionCheckwithReport(size > 0, "validating the Shipping information block", expectedResult,
						"shipping address should display", "failed to display the shipping address");
	   	
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the order summary and shipping information in the payment page",
						"Order summary and shipping information should be display in the payment page and all fields should display",
						"Unable to display the Order summary and shipping information fields are not displayed in the payment page",
						Common.getscreenShot("Failed to display the order summary and shipping information fileds under order summary"));
				Assert.fail();
			}
		}



	public void addPaymentDetails(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> Paymentmethod = new HashMap<String, String>();
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String cardnumber = data.get(dataSet).get("cardNumber");
		System.out.println(cardnumber);
		String expectedResult = "land on the payment section";
		// Common.refreshpage();

		try {

			Sync.waitElementClickable("xpath", "//label[@for='stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unabel to land opaymentpage");
			Common.clickElement("xpath", "//label[@for='stripe_payments']");

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

		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", expectedResult,
					"failed  to fill the Credit Card infromation",
					Common.getscreenShotPathforReport("Cardinfromationfail"));
			Assert.fail();
		}

		expectedResult = "credit card fields are filled with the data";
		int errorTexts = Common.findElements("xpath", "//div[contains(@id,'error')]").size();

		Common.assertionCheckwithReport(errorTexts<=0, "validating the credit card information with valid data",
				expectedResult, "Filled the Card detiles", "missing field data it showinng error");
	}

	public String updatePaymentAndSubmitOrder(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String order = "";
		add_reg_PaymentDetails("PaymentDetails");
		String expectedResult = "It redirects to order confirmation page";

		if (Common.findElements("xpath", "//div[@class='message message-error']").size() > 0) {
			Thread.sleep(4000);
			addPaymentDetails("PaymentDetails");
		}

		Thread.sleep(3000);
		int placeordercount = Common.findElements("xpath", "//span[text()='Place Order']").size();
		if (placeordercount > 1) {
			Thread.sleep(4000);

			Common.clickElement("xpath", "//span[text()='Place Order']");
			Common.refreshpage();
		}

		String url = automation_properties.getInstance().getProperty(automation_properties.BASEURL);

		if (!url.contains("stage") &&!url.contains("preprod") ) {
		}

		else {
			try {
				String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();

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

	public void Validate_shippingaddressform() {
		try {
			Sync.waitElementVisible("name", "firstname");

			int firstname = Common.findElements("name", "firstname").size();
			int lastname = Common.findElements("name", "lastname").size();
			int street1 = Common.findElements("name", "street[0]").size();
			int street2 = Common.findElements("name", "street[1]").size();
			int City = Common.findElements("name", "city").size();
			int state = Common.findElements("name", "region_id").size();
			Common.scrollIntoView("name", "postcode");
			int Zipcode = Common.findElements("name", "postcode").size();
			int Country = Common.findElements("name", "country_id").size();
			int Phonenumber = Common.findElements("name", "telephone").size();
			Common.scrollIntoView("xpath", "//div[@class='checkout-shipping-method']");
			int Shippingmethodsheading = Common.findElements("xpath", "//div[@class='checkout-shipping-method']")
					.size();
			int CartCMSmessaging = Common.findElements("xpath", "//div[contains(@class,'methods-cms_block')]").size();
			int Shippingmethodcontent = Common.findElements("id", "checkout-step-shipping_method").size();

			int shippingprice = Common.findElements("xpath", "//span[@class='shipping-method__radio']").size();
			int shippingmethod = Common.findElements("xpath", "//td[2][@class='col col-method']").size();
			Common.scrollIntoView("xpath", "//button[contains(@class,'continue primary')]");
			int nextbutton = Common.findElements("xpath", "//button[contains(@class,'continue primary')]").size();

			Common.assertionCheckwithReport(
					firstname > 0 && lastname > 0 && street1 > 0 && street2 > 0 && City > 0 && state > 0 && Zipcode > 0
							&& Country > 0 && Phonenumber > 0 && Shippingmethodsheading > 0 && CartCMSmessaging > 0
							&& Shippingmethodcontent > 0 && shippingprice > 0 && shippingmethod > 0 && nextbutton > 0,
					"To validate the Shipping address form for all the required fields",
					"All the required fields should be displayed", "All the required fields are displayed",
					"Failed to display the required fields in shipping address page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Shipping address form for all the required fields",
					"All the required fields should be displayed", "Failed to display the required fields",
					Common.getscreenShotPathforReport("faield required fields display shipping address page"));
			Assert.fail();

		}

	}

	public void populate_Shippingaddress_fields(String dataSet) throws Exception {

		Common.scrollIntoView("name", "firstname");
		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));

		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		Common.scrollIntoView("name", "city");

		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));

		Common.dropdown("name", "region_id", SelectBy.TEXT, data.get(dataSet).get("State"));

		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Common.dropdown("name", "country_id", SelectBy.TEXT, "United States");

		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
	}

	public void selectshippingmethod(String dataSet) {
		String shippingmethod = data.get(dataSet).get("Shippingmethods");
		try {

			Common.scrollIntoView("xpath", "//td[2][text()='" + shippingmethod + "']");

			Sync.waitElementClickable("xpath", "//td[2][text()='" + shippingmethod + "']");
			Common.javascriptclickElement("xpath", "//td[2][text()='" + shippingmethod + "']");
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//input[@class='a-radio-button__input']");

			if (shippingmethod.contains("Fixed")) {
				String selecetdshippingmethod = Common.findElement("xpath", "//input[@value='flatrate_flatrate']")
						.getAttribute("aria-labelledby");
				System.out.println(selecetdshippingmethod);

				Common.assertionCheckwithReport(selecetdshippingmethod.contains("flatrate"),
						"to validate the shipping method selected" + shippingmethod,
						shippingmethod + "Should be selected", shippingmethod + "is selected",
						"Failed to select the" + shippingmethod);
			} else if (shippingmethod.contains("Ground")) {
				String selecetdshippingmethod = Common.findElement("xpath", "//input[@value='fedex_FEDEX_GROUND']")
						.getAttribute("aria-labelledby");
				System.out.println(selecetdshippingmethod);
				Common.assertionCheckwithReport(selecetdshippingmethod.contains("FEDEX_GROUND"),
						"to validate the shipping method selected" + shippingmethod,
						shippingmethod + "Should be selected", shippingmethod + "is selected",
						"Failed to select the" + shippingmethod);

			} else if (shippingmethod.contains("2 Day")) {
				String selecetdshippingmethod = Common.findElement("xpath", "//input[@value='fedex_FEDEX_2_DAY']")
						.getAttribute("aria-labelledby");
				System.out.println(selecetdshippingmethod);
				Common.assertionCheckwithReport(selecetdshippingmethod.contains("FEDEX_2_DAY"),
						"to validate the shipping method selected" + shippingmethod,
						shippingmethod + "Should be selected", shippingmethod + "is selected",
						"Failed to select the" + shippingmethod);
			}

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("to validate the shipping method selected" + shippingmethod,
					shippingmethod + "Should be selected", "Failed to select the shiping method" + shippingmethod,
					shippingmethod + "Not selected");
			Assert.fail();

		}
	}

	public void Validate_Paymentpage() {
		try {
			Sync.waitElementClickable("xpath", "//button[contains(@class,'continue primary')]");
			Common.javascriptclickElement("xpath", "//button[contains(@class,'continue primary')]");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//div[text()='Payment Method']");
			Common.assertionCheckwithReport(
					Common.getCurrentURL().contains("#payment") && Common.getPageTitle().contains("Checkout"),
					"To validate the user lands on the payment page", "User should land on the payment page",
					"User lands on the payment page", "User failed to land on the payment page");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the user lands on the payment page",
					"User should land on the payment page", "User failed to land on the payment page",
					"Payment navigation failed");
			Assert.fail();

		}

	}

	public void verify_ordersummary() {
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

	public void product_verification(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		System.out.println(product);
		try {
			Sync.waitElementPresent("xpath", "//div[@class='m-accordion__title']");
			Common.clickElement("xpath", "//div[@class='m-accordion__title']");
			Sync.waitElementPresent("xpath", "//div[@class='content minicart-items']");
			String expand = Common.findElement("xpath", "//div[@class='content minicart-items']")
					.getAttribute("aria-hidden");
			System.out.println(expand);

			String productname = Common.findElement("xpath", "//div[@class='m-mini-product-card__name']").getText();
			System.out.println(productname);
			Common.assertionCheckwithReport(
					expand.equals("false") && productname.contains("9 CUP")
							|| expand.equals("false") && productname.contains(product),
					"validating the items expand button and product under the order summary on payment page",
					"items should be display under the order summary on the payment page",
					"Successfully items has displayed under the order summary in payment page",
					"Failed to display the items under the order summary in payment page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the items expand button and product under the order summary on payment page",
					"items should be display under the order summary on the payment page",
					"Unable to display the items under the order summary in payment page",
					Common.getscreenShot("Failed to display the order summary and fileds under order summary"));
			Assert.fail();

		}
	}

	public void shipping_method_verification(String method) {
		// TODO Auto-generated method stub
		try {
			Common.scrollIntoView("xpath", "//span[@class='value']");
			Sync.waitElementPresent("xpath", "//span[@class='value']");
			String shippingmethod = Common.findElement("xpath", "//span[@class='value']").getText();
			Common.assertionCheckwithReport(shippingmethod.equals(method),
					"validating the shipping method  on the payment page",
					"Shipping method should be display on the payment page",
					"Successfully Shipping method has displayed on the payment page",
					"Failed to display Shipping method on the payment page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the shipping method  on the payment page",
					"Shipping method should be display on the payment page",
					"Unable to display the Shipping method on the payment page",
					Common.getscreenShot("Failed to display Shipping method on the payment page"));
			Assert.fail();

		}

	}

	public void addtocart_PDP(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		String product = data.get(Dataset).get("ProductQuantity");
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

			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + products + "']");
			Common.clickElement("xpath", "//img[@alt='" + products + "']");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Soap Dispensing"),
					"validating the product should navigate to the PDP page",
					"When we click on the product is should navigate to the PDP page",
					"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//form[@class ='m-add-to-cart ']");
			Common.findElement("xpath", "//form[@class ='m-add-to-cart ']").getAttribute("data-product-sku");

			Common.clickElement("xpath", "//button[@title='Add to Bag']");

			Sync.waitPageLoad();
			String message1 = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
					.getAttribute("data-ui-id");
			Common.assertionCheckwithReport(message1.contains("success"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");

			headerlinks("Cleaning & Organization");
			Sync.waitPageLoad();
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product + "']");
			Common.clickElement("xpath", "//img[@alt='" + product + "']");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().contains("8-Piece POP"),
					"validating the product should navigate to the PDP page",
					"When we click on the product is should navigate to the PDP page",
					"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");
			Sync.waitPageLoad();

			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//form[@class ='m-add-to-cart ']");
			Common.findElement("xpath", "//form[@class ='m-add-to-cart ']").getAttribute("data-product-sku");

			Common.clickElement("xpath", "//button[@title='Add to Bag']");

			Sync.waitPageLoad();
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
					.getAttribute("data-ui-id");
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

	public void coffee_headerlinks(String category) {
		// TODO Auto-generated method stub
		String expectedResult = "User should click the" + category;
		try {

			Sync.waitElementClickable("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");
			Thread.sleep(3000);
			Common.mouseOverClick("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");
			Thread.sleep(3000);

			try {
				Common.mouseOver("xpath", "//span[contains(text(),'" + category + "')]");
			} catch (Exception e) {
				Common.clickElement("xpath", "//a[@class='level-top ui-corner-all']//span[text()=' Shop']");
			}
			Common.clickElement("xpath", "//span[contains(text(),'" + category + "')]");
//			Common.clickElement("xpath", "//span[text()='Shop All']");
			Common.clickElement("xpath", "//a[contains(@aria-label,'Shop All  Coffee & B')]");
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

	public void search_product(String Dataset) {
		// TODO Auto-generated method stub
		String sku3 = "";
		String product = data.get(Dataset).get("Products");
		System.out.println(product);
		try {
			Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
			String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
			Common.assertionCheckwithReport(open.contains("active"), "User searches using the search field",
					"User should able to click on the search button", "Search expands to the full page",
					"Sucessfully search bar should be expand");
			Common.textBoxInput("xpath", "//input[@id='search']", product);
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Common.scrollIntoView("xpath", "//p[@class='m-breadcrumb__text']");
			String productsearch = Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getPageTitle().equals(productsearch),
					"validating the search functionality", "enter product name will display in the search box",
					"user enter the product name in  search box", "Failed to see the product name");
			Common.clickElement("xpath", "//img[@alt='" + product + "']");

			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//form[@class ='m-add-to-cart ']");
			sku3 = Common.findElement("xpath", "//form[@class ='m-add-to-cart ']").getAttribute("data-product-sku");
			System.out.println(sku3);
			Common.clickElement("xpath", "//button[@title='Add to Bag']");
			Sync.waitElementPresent("xpath", "//div[@data-ui-id='message-success']");
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

	public void minicart_scroll() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
			Common.mouseOverClick("xpath", "//span[contains(@class,'c-mini-cart__icon')]");

			Common.scrollIntoView("xpath", "//div[@class='m-mini-product-card__name']//a[@class='a-product-name']");
			List<WebElement> allitems = Common.findElements("xpath",
					"//div[@class='m-mini-product-card__name']//a[@class='a-product-name']");
			System.out.println(allitems);
			WebElement item;
			for (int i = 0; i < allitems.size(); i++) {
				item = allitems.get(i);
				System.out.println(item.getText());
				Common.scrollIntoView(allitems.get(i));

			}
		} catch (Exception | Error e) {
			e.printStackTrace();

			Assert.fail();
		}

	}

	public void add_reg_PaymentDetails(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, String> Paymentmethod = new HashMap<String, String>();
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

//				Shippingaddress.put("Shippingstate", Shippingstate);
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
//				Shippingaddress.put("ShippingZip", ShippingZip);

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

				Common.clickElement("xpath", "//tr[@class='row']//td[@class='col col-method' and @id]");
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
//				Shippingaddress.put("ShippingZip", ShippingZip);

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

	public void validate_registeruser_Shippingaddresspage() {
		try {
			int backtocartCTA = Common.findElements("xpath", "//a[contains(@class,'back-to-cart')]/span").size();
			String Shippingstep = Common.findElement("xpath", "(//li[contains(@class,'bar-item')]/span)[1]").getText();
			String reviewspaymentstep = Common.findElement("xpath", "(//li[contains(@class,'bar-item')]/span)[2]")
					.getText();
			String selectedshippingstep = Common.findElement("xpath", "(//li[contains(@class,'bar-item')])[1]")
					.getAttribute("class");
			int selectedAddress = Common.findElements("xpath", "//div[contains(@class,'-item selected-item')]").size();

			int Existingaddress = Common.findElements("xpath", "//div[contains(@class,'shipping-address-items')]/div")
					.size();

			int newaddressCTA = Common.findElements("xpath", "//button[contains(@class,'action-show-popup')]/span")
					.size();
			validate_Shippingmethods();
			validate_ordersummaryblock_content_Display();
			validate_itemsinthecart_tab();
			Common.scrollIntoView("xpath", "//button[contains(@class,'continue primary')]");
			int NextButton = Common.findElements("xpath", "//button[contains(@class,'continue primary')]").size();
			Common.assertionCheckwithReport(
					Shippingstep.equals("Shipping") && reviewspaymentstep.equals("Review & Payments")
							&& selectedshippingstep.contains("active") && selectedAddress == 1 && backtocartCTA > 1
							&& Existingaddress > 0 && newaddressCTA > 0 && NextButton > 0,
					"To validate the backtocartCTA selectedshippingstep selectedAddress Existingaddress newaddressCTA NextButton are displayed",
					"backtocartCTA selectedshippingstep selectedAddress Existingaddress newaddressCTA NextButton should be displayed",
					"Successfully displayed the backtocartCTA selectedshippingstep selectedAddress Existingaddress newaddressCTA NextButton",
					"Failed to display thebacktocartCTA selectedshippingstep selectedAddress Existingaddress newaddressCTA NextButton");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate the Existing addresses are available and New address button is displayed",
					"Saved Address and new address CTA should be displayed",
					"Exsting address and New address CTA not displayed",
					"Failed to display the Existing address and New address CTA");
			Assert.fail();
		}

	}

	public void validate_Shippingmethods() {
		try {
			Common.scrollIntoView("xpath", "//div[@class='checkout-shipping-method']");
			int Shippingmethodsheading = Common.findElements("xpath", "//div[@class='checkout-shipping-method']")
					.size();
			int CartCMSmessaging = Common.findElements("xpath", "//div[contains(@class,'methods-cms_block')]").size();
			int Shippingmethodcontent = Common.findElements("id", "checkout-step-shipping_method").size();

			int shippingprice = Common.findElements("xpath", "//span[@class='shipping-method__radio']").size();
			int shippingmethod = Common.findElements("xpath", "//td[2][@class='col col-method']").size();

			Common.assertionCheckwithReport(
					Shippingmethodsheading > 0 && CartCMSmessaging > 0 && Shippingmethodcontent > 0
							&& shippingprice == 3 && shippingmethod == 3,
					"To validate the Shipping content is displayed",
					"CartCMSmessaging shippingprice shippingmethod should be displayed",
					"Successfully displayed CartCMSmessaging shippingprice shippingmethod",
					"Failed to display the CartCMSmessaging shippingprice shippingmethod");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Shipping content is displayed",
					"CartCMSmessaging shippingprice shippingmethod should be displayed",
					"Successfully displayed CartCMSmessaging shippingprice shippingmethod",
					"Failed to display the CartCMSmessaging shippingprice shippingmethod");
			Assert.fail();
		}
	}

	public void validate_ordersummaryblock_content_Display() {
		try {
			Sync.waitElementVisible("xpath", "//div[@class='opc-block-summary']");
			int ordersummaryblock = Common.findElements("xpath", "//div[@class='opc-block-summary']").size();
			int subtotal = Common.findElements("xpath", "//div[@class='opc-block-summary']").size();
			Common.scrollIntoView("xpath", "//td[text()='Ground']");
			Common.clickElement("xpath", "//td[text()='Ground']");
			Common.scrollIntoView("xpath", "//span[@class='price' and @data-th='Shipping']");
			int Shippingcost = Common.findElements("xpath", "//span[@class='price' and @data-th='Shipping']").size();
			int Tax = Common.findElements("xpath", "//tr[@class='totals-tax']").size();
			int OrderTotal = Common.findElements("xpath", "//tr[@class='grand totals']").size();
			int XXitemsincart = Common.findElements("xpath", "//div[@class='m-accordion__title']").size();
			int Discountsection = Common.findElements("id", "block-discount-heading").size();

			Common.assertionCheckwithReport(
					ordersummaryblock == 1 && subtotal == 1 && Shippingcost == 1 && Tax == 1 && OrderTotal == 1
							&& XXitemsincart == 1 && Discountsection == 1,
					"To validate the order summary block is displayed with subtotal, shippingcost,tax,Order total, items in the cart, Discount section",
					"subtotal, shippingcost,tax,Order total, items in the cart, Discount section should be displayed",
					"subtotal shippingcost tax Order total  items in the cart, Discount section are displayed",
					"Failed to display the subtotal, shippingcost - tax - Order total - items in the cart-  Discount section");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate the order summary block is displayed with subtotal, shippingcost,tax,Order total, items in the cart, Discount section",
					"subtotal, shippingcost,tax,Order total, items in the cart, Discount section should be displayed",
					"subtotal, shippingcost,tax,Order total, items in the cart, Discount section not displayed",
					"Failed to display the subtotal, shippingcost,tax,Order total, items in the cart, Discount section");
			Assert.fail();

		}

	}

	public void validate_itemsinthecart_tab() {
		try {

			String itemsinthecartexpand = Common.findElement("xpath", "//div[contains(@class,'items-in-cart')]/div")
					.getAttribute("class");
			if (itemsinthecartexpand.contains("active")) {
				System.out.println("items in the cart expanded");
			} else {
				Common.clickElement("xpath", "//div[contains(@class,'items-in-cart')]/div");
				Common.assertionCheckwithReport(itemsinthecartexpand.contains("active"),
						"To validate the items in the cart section is expanded",
						"It should display the items in the cart", "Items in the cart section is displayed",
						"Failed to display the Items in the cart");
			}
			int productimage = Common.findElements("xpath", "//span[@class='product-image-wrapper']/img").size();
			int productname = Common.findElements("xpath", "//div[@class='m-mini-product-card__name']").size();
			int productQty = Common
					.findElements("xpath", "//div[contains(@class,'product-card__attributes')]/p[1]/span[2]").size();
			int productprice = Common.findElements("xpath", "//span[@class='price-excluding-tax']").size();
			Common.assertionCheckwithReport(productimage > 0 && productname > 0 && productQty > 0 && productprice > 0,
					"To validate the product details are displayed in the items in the cart section",
					"Products details should be displayed", "Product details are displayed",
					"Failed to display the product details");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate the product details are displayed in the items in the cart section",
					"Products details should be displayed", "Product details are displayed",
					"Failed to display the product details");
			Assert.fail();

		}

	}

	public void Click_NewAddressCTA() {

		try {
			Sync.waitElementClickable("xpath", "//button[contains(@class,'action-show-popup')]/span");
			Common.clickElement("xpath", "//button[contains(@class,'action-show-popup')]/span");
			Sync.waitElementVisible("xpath", "//div[@id='opc-new-shipping-address' and @style='']");
			int NewAddresspopup = Common.findElements("xpath", "//div[@id='opc-new-shipping-address' and @style='']")
					.size();
			Common.assertionCheckwithReport(NewAddresspopup > 0, "To validate the New addresses popup is displayed",
					"Add New Address popup should be displayed", "Successfully displayed the dd New Address popup",
					"Failed to display the dd New Address popup");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the New addresses popup is displayed",
					"Add New Address popup should be displayed", "Failed to dispaly the Add new address popup",
					"Add new address popup display failed");
			Assert.fail();
		}
	}

	public void Validate_Update_NewAddress_Verification(String dataSet) {
		String FirstName = data.get(dataSet).get("FirstName");
		String LastName = data.get(dataSet).get("LastName");
		String Street = data.get(dataSet).get("Street");
		String City = data.get(dataSet).get("City");
		String Region = data.get(dataSet).get("Region");
		String postcode = data.get(dataSet).get("postcode");
		String phone = data.get(dataSet).get("phone");
		String Country = data.get(dataSet).get("Country");

		try {
			Sync.waitElementClickable("xpath", "//button[contains(@class,'action-save-address')]");
			Common.javascriptclickElement("xpath", "//button[contains(@class,'action-save-address')]");
			Sync.waitElementInvisible("xpath", "//div[@id='opc-new-shipping-address' and @style='']");
			Sync.waitElementVisible("xpath", "//div[@class='shipping-address-item selected-item']");
			String CheckedAddress = Common.findElement("xpath", "//div[@class='shipping-address-item selected-item']")
					.getAttribute("class");

			String fullname = Common.findElement("xpath", "(//div[contains(@class,'item selected-item')]/p/strong)")
					.getText();

			String selectedaddress = Common.findElement("xpath", "(//div[contains(@class,'item selected-item')]/p)[2]")
					.getText();
			Common.assertionCheckwithReport(
					fullname.contains(FirstName + " " + LastName) && CheckedAddress.contains("item selected-item")
							&& selectedaddress.contains(Street + "\n" + City + ", " + Region + " " + postcode + "\n"
									+ Country + "\n" + phone),

					"validate the user is able to update the new address in the shipping page",
					" user should  able to update the new address in the shipping page",
					" user successfuly update the new address in the shipping page",
					"failed to update the new address in the shipping page ");
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validate the user is able to update the new address in the shipping page",
					" user should  able to update the new address in the shipping page",
					" user successfuly update the new address in the shipping page",
					Common.getscreenShot("failed to update the new address in the shipping page "));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void Validate_RegisterUser_shippingaddressform() {
		try {
			Sync.waitElementVisible("name", "firstname");

			int firstname = Common.findElements("name", "firstname").size();
			int lastname = Common.findElements("name", "lastname").size();
			int street1 = Common.findElements("name", "street[0]").size();
			int street2 = Common.findElements("name", "street[1]").size();
			Common.scrollIntoView("name", "city");
			int City = Common.findElements("name", "city").size();
			int state = Common.findElements("name", "region_id").size();
			int Zipcode = Common.findElements("name", "postcode").size();
			int Country = Common.findElements("name", "country_id").size();
			int Phonenumber = Common.findElements("name", "telephone").size();
			int saveaddresscheckbox = Common.findElements("id", "shipping-save-in-address-book").size();
			int CancelCTA = Common.findElements("xpath", "//button[contains(@class,'action-hide-popup')]").size();
			int ShiphereCTA = Common.findElements("xpath", "//button[contains(@class,'action-save-address')]").size();

			Common.assertionCheckwithReport(
					firstname > 0 && lastname > 0 && street1 > 0 && street2 > 0 && City > 0 && state > 0 && Zipcode > 0
							&& Country > 0 && Phonenumber > 0 && saveaddresscheckbox > 0 && CancelCTA > 0
							&& ShiphereCTA > 0,
					"To validate the Edit Shipping address form for all the required fields",
					"All the required fields should be displayed", "All the required fields are displayed",
					"Failed to display the required fields in Edit shipping address form");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Shipping address form for all the required fields",
					"All the required fields should be displayed", "Failed to display the required fields",
					Common.getscreenShotPathforReport(
							"Failed to display the required fields in Edit shipping address form"));
			Assert.fail();

		}
	}

	public void minicart_products(String minicart) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
			Common.mouseOverClick("xpath", "//span[contains(@class,'c-mini-cart__icon')]");

			Sync.waitElementPresent(30, "xpath", "//span[@class='c-mini-cart__counter']");
			String cartproducts = Common.findElement("xpath", "//span[@class='c-mini-cart__counter']").getText();

			Common.assertionCheckwithReport(cartproducts.equals(minicart),
					"validating the products in the cart after creating new account ",
					"Products should be displayed in the mini cart after Create account with Cart",
					"Sucessfully after create account with cart products should be display in mini cart",
					"failed to display the products in mini cart after the create account with cart");

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

	public void user_SignIn(String dataSet) throws Exception {

		try {
			Sync.waitPageLoad();
			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//button[contains(@class,'action lo')]");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			String login = Common.findElement("xpath",
					"//div[@class='m-account-nav__welcome']//span[@class='a-icon-text-btn__label']").getText();
			System.out.println(login);
			Common.assertionCheckwithReport(login.contains("Welcome, " + data.get(dataSet).get("FirstName")),
					"Validating My Account page navigation", "user sign in and navigate to my account page",
					"Successfully navigate to my account page", "Failed to navigate my account page ");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating My Account page navigation",
					"user sign in and navigate to my account page", "Unable to navigate to the my account page",
					Common.getscreenShotPathforReport("Failed to navigate to the my account page "));
			Assert.fail();
		}

	}

	public void navigate_To_MyAccount() {

		try {

			Sync.waitElementClickable("xpath", "//div[@class='m-account-nav__welcome']/button");
			Common.javascriptclickElement("xpath", "//div[@class='m-account-nav__welcome']/button");

			Common.javascriptclickElement("xpath", "//ul[@class='m-account-nav__links']/li[1]/a");
			System.out.println(Common.getPageTitle());
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"),
					"Validating the My account page navigation when user clicks on the My account CTA",
					"After clicking My account CTA it will navigates My Account page",
					"Successfully navigate to My Account page after clciking on the My account CTA",
					"Failed to navigate to my account page after clicking on the My account CTA");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"Validating the My account page navigation when user clicks on the My account CTA ",
					"Able clicking My account CTA it will navigates My Account page ",
					"unable to navigate to My Account page after clciking on the My account CTA",
					Common.getscreenShotPathforReport(
							"Failed to navigate to My Account page after clciking on the My account CTA"));
			Assert.fail();

		}

	}

	public void verify_RewardPoints() {

		try {

			Sync.waitElementClickable("xpath", "//a[text()='Reward Points']");
			Common.clickElement("xpath", "//a[text()='Reward Points']");
			Sync.waitPageLoad();
			Common.findElement("xpath", "//h1[text()='Reward Points']");

			String rewardPointss = Common.findElement("xpath", "//div[@class='block-reward__wrapper']").getText();
			System.out.println(rewardPointss);

			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Reward Points") && rewardPointss.contains("Current exchange rates")
							&& rewardPointss.contains("Learn more"),
					"Validating Reward Points in Account page",
					"After clicking Reward Points in Account page it will navigate Reward Points page",
					"Successfully navigate to Reward Points account page",
					"Failed to navigate Reward Points account page ");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Reward Points in Account page ",
					"After clicking Reward Points in Account page it will navigate Reward Points page",
					"unable to click Reward Points account page",
					Common.getscreenShotPathforReport("Failed to navigate Reward Points account page"));
			Assert.fail();
		}
	}

	public void verify_BalanceHistroy() {

		try {
			Sync.waitElementPresent("xpath", "//div[text()='Balance History']");
			Common.scrollIntoView("xpath", "//div[text()='Balance History']");
			List<WebElement> balancetable = Common.findElements("xpath", "//thead[@class='m-table__head']/tr/th");
			for (WebElement i : balancetable) {
				System.out.println(i.getText());
			}

			String bal = Common.findElement("xpath", "(//thead[@class='m-table__head']/tr/th)[2]").getText();
			System.out.println(bal);

			String balance = Common.findElement("xpath", "//div[text()='Balance History']").getText();

			Common.assertionCheckwithReport(balance.equals("Balance History") && bal.contains("Amount"),
					"Validating the Balance History on Account page",
					"Able to display the Balance History on Account page ",
					"Successfully Dispaly Balance Historyon Account page",
					"Failed to Display Balance History on Account page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the Balance History on Account page ",
					"Able to display the Balance History on Account page ",
					"unable to Dispaly Balance History on Account page",
					Common.getscreenShotPathforReport("Failed to Display Balance History on Account page"));
			Assert.fail();

		}

	}

	public void verify_EmailNotification() {

		try {
			Sync.waitElementPresent("xpath", "//span[text()='Email Notification Settings']");
			Common.scrollIntoView("xpath", "//span[text()='Email Notification Settings']");
			String Email = Common.findElement("xpath", "//fieldset[@class='fieldset']/p").getText();

			boolean Bal_updates = Common.checkBoxIsSelected("xpath", "//input[@id='subscribe_updates']", false);
			System.out.println(Bal_updates);

			boolean Points_notification = Common.checkBoxIsSelected("xpath", "//input[@id='subscribe_warnings']",
					false);
			System.out.println(Points_notification);

			System.out.println(Email);
			Common.assertionCheckwithReport(Email.contains("Email"),
					"Validating the Email Notification Settings on customers page",
					"Able to display the Email Notification Settings on Account page ",
					"Successfully Dispaly Email Notification Settings on Account page",
					"Failed to Display Email Notification Settings on Account page");

			Common.scrollIntoView("xpath", "//span[text()='Save Subscription Settings']");
			Common.clickElement("xpath", "//span[text()='Save Subscription Settings']");
			Sync.waitPageLoad();
			Common.scrollIntoView("xpath", "//div[@class='messages']/div/div");
			String Successmsg = Common.findElement("xpath", "//div[@class='messages']/div/div").getText();
			System.out.println(Successmsg);
			Common.assertionCheckwithReport(Successmsg.contains("You saved the settings"),
					"Validating the Email Notification Settings on Account page",
					"Able to display the Email Notification Settings on Account page ",
					"Successfully Dispaly Email Notification Settings on Account page",
					"Failed to Display Email Notification Settings on Account page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating the Email Notification Settings on Account page ",
					"Able to display the Email Notification Settings Account page ",
					"unable to Dispaly Email Notification Settings on Account page",
					Common.getscreenShotPathforReport("Failed to Display Email Notification Settings on Account page"));
			Assert.fail();

		}

	}

	public void verify_LearnMore_CTA() {

		try {

			Sync.waitElementClickable("xpath", "//a[contains(@href,'reward-points')]");
			Common.scrollIntoView("xpath", "//a[contains(@href,'reward-points')]");
			Common.clickElement("xpath", "//a[contains(@href,'reward-points')]");
			Sync.waitPageLoad();
			Common.findElement("xpath", "//h1[text()='Reward Points']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Reward Points"),
					"Validating Learn More in Reward Points page",
					"After clicking Learn More in Reward Points page it will navigate to Reward Points CMS page",
					"Successfully navigate to Reward Points  page", "Failed to navigate Reward Points page ");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Learn More in Reward Points page ",
					"After clicking Learn More in Reward Points page it will navigate to Reward Points CMS page",
					"unable to click Learn More in Reward Points page",
					Common.getscreenShotPathforReport("Failed to navigate Reward Points page"));
			Assert.fail();
		}
	}

	public void createNewAccount_Required_Fieldsvalidation() {
		String Errofield;
		String requiredfielderrmsg;
		String errormessageID;
		try {

			Common.scrollIntoView("xpath", "//button[contains(@class,'primary a-btn')]");
			Common.javascriptclickElement("xpath", "//button[contains(@class,'primary a-btn')]");
			Sync.waitElementVisible("xpath", "//div[@class='mage-error']");

			List<WebElement> errorfield = Common.findElements("xpath", "//input[contains(@class,'th mage-error')]");

			for (int i = 0; i < errorfield.size(); i++) {

				Errofield = errorfield.get(i).getAttribute("id").toLowerCase();

				List<WebElement> errormessage = Common.findElements("xpath", "//div[@class='mage-error']");
				requiredfielderrmsg = errormessage.get(i).getText();

				errormessageID = errormessage.get(i).getAttribute("id").toLowerCase();

				Common.assertionCheckwithReport(
						errormessageID.contains(Errofield) && requiredfielderrmsg.contains("This is a required field."),
						"To validate the required field error message is displayed for the field " + Errofield,
						requiredfielderrmsg + "should be displayed for field" + Errofield,
						requiredfielderrmsg + "error message is displayed for " + Errofield,
						"Failed to display" + requiredfielderrmsg + "for" + Errofield);

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate the required field error message is displayed for the all the required fields",
					"This is a required field error meessage should be displayed",
					"Failed to display the error message",
					Common.getscreenShotPathforReport("error message display unsuccessfull"));
			Assert.fail();
		}
	}

	public void validate_Createnewaccount_email(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));
			Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			String classes = Common.findElement("id", "validation-classes").getAttribute("class");
			String textlength = Common.findElement("id", "validation-length").getAttribute("class");
			Common.actionsKeyPress(Keys.PAGE_DOWN);

			Common.actionsKeyPress(Keys.UP);
			Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));

			Sync.waitElementClickable("xpath", "//button[@title='Sign Up']");
			Common.clickElement("xpath", "//button[@title='Sign Up']");
			Sync.waitPageLoad();
			Sync.waitElementVisible("id", "email_address-error");
			Common.scrollIntoView("id", "email_address-error");
			String emailerr = Common.findElement("id", "email_address-error").getText();
			Common.assertionCheckwithReport(
					classes.contains("complete") && textlength.contains("complete")
							&& emailerr.contains("Please enter a valid email address (Ex: johndoe@domain.com)."),
					"validating the invalid email error message",
					"User should display the error message for " + emailerr, emailerr,
					"failed to display the error message for invalid email");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the invalid email error message",
					"User should display the error message for invalid email",
					"failed to display the error message for invalid email",
					Common.getscreenShot("Invalid email error message display unsuccessfull"));
			Assert.fail();

		}

	}

	public void validate_Createnewaccount_invalidpasssword(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));
			Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			String classes = Common.findElement("id", "validation-classes").getAttribute("class");
			String textlength = Common.findElement("id", "validation-length").getAttribute("class");
			Common.actionsKeyPress(Keys.PAGE_DOWN);

			Common.actionsKeyPress(Keys.UP);
			Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password1"));

			Sync.waitElementClickable("xpath", "//button[@title='Sign Up']");
			Common.clickElement("xpath", "//button[@title='Sign Up']");
			Sync.waitPageLoad();
			Sync.waitElementVisible("id", "password-confirmation-error");
			String passworderr = Common.findElement("id", "password-confirmation-error").getText();
			Common.assertionCheckwithReport(
					classes.contains("text-error") && textlength.contains("text-error")
							&& passworderr.contains("Please enter the same value again."),
					"validating the invalid password error message",
					"User should display the error message for " + passworderr, passworderr,
					"failed to display the error message for invalid password");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the invalid password error message",
					"User should display the error message for invalid password",
					"failed to display the error message for invalid password",
					Common.getscreenShot("Invalid password error message display unsuccessfull"));
			Assert.fail();

		}

	}

	public void validate_Createnewaccount_passswordFields(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));
			Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			String classes = Common.findElement("id", "validation-classes").getAttribute("class");
			String textlength = Common.findElement("id", "validation-length").getAttribute("class");
			Common.actionsKeyPress(Keys.PAGE_DOWN);

			Common.actionsKeyPress(Keys.UP);
			Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password1"));

			Sync.waitElementClickable("xpath", "//button[@title='Sign Up']");
			Common.clickElement("xpath", "//button[@title='Sign Up']");
			Sync.waitPageLoad();
			Sync.waitElementVisible("id", "password-confirmation-error");
			String passworderr = Common.findElement("id", "password-confirmation-error").getText();
			Common.assertionCheckwithReport(
					classes.contains("complete") && textlength.contains("complete")
							&& passworderr.contains("Please enter the same value again."),
					"validating the error message for two different passwords",
					"User should display the error message for two different passwords " + passworderr, passworderr,
					"failed to display the error message for different password");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the error message for two different passwords",
					"User should display the error message for two different passwords ",
					"failed to display the error message for different password",
					Common.getscreenShot("Two different password error message display unsuccessfull"));
			Assert.fail();

		}

	}

	public void validate_Createnewaccount_existingemail(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));
			Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			String classes = Common.findElement("id", "validation-classes").getAttribute("class");
			String textlength = Common.findElement("id", "validation-length").getAttribute("class");
			Common.actionsKeyPress(Keys.PAGE_DOWN);

			Common.actionsKeyPress(Keys.UP);
			Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));

			Sync.waitElementClickable("xpath", "//button[@title='Sign Up']");
			Common.clickElement("xpath", "//button[@title='Sign Up']");
			Sync.waitPageLoad();
			Sync.waitElementVisible("xpath", "//div[contains(@class,'message-error')]/div");
			String existingemailerr = Common.findElement("xpath", "//div[contains(@class,'message-error')]/div")
					.getText();
			Common.assertionCheckwithReport(
					classes.contains("complete") && textlength.contains("complete") && existingemailerr.contains(
							"There is already an account with this email address. If you are sure that it is your email address, click here to get your password and access your account."),
					"validating the error message in create new account page when given Existing email",
					"User should display error message when given existing email", existingemailerr,
					"failed to display the error message for when given existing email");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"validating the error message in create new account page when given Existing email",
					"User should display error message when given existing email",
					"failed to display the error message when given existing email",
					Common.getscreenShot("Existing email error message display unsuccessfull"));
			Assert.fail();

		}

	}

	public void click_EditAddress() {
		try {

			Common.clickElement("xpath", "//div[contains(@class,'shipping-address-item selected-item')]/div");
			Sync.waitPageLoad();
			Common.isElementVisibleOnPage(30, "xpath", "//h1[contains(text(),'Shipping Address')]");
			String Editaddresspopup = Common.findElement("xpath", "//aside[contains(@class,'shipping-address-modal')]")
					.getAttribute("class");
			System.out.println(Editaddresspopup);
			Common.assertionCheckwithReport(Editaddresspopup.contains("show"),
					"validate shipping address pop up when click on editaddress link in the existing address",
					"it should open a shipping address popup when click on editaddress link",
					"successfully open a shipping address pop up when click on editaddress link",
					"failed to open shipping address pop up when click on editaddress link");

		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validate shipping address pop up when click on editaddress link",
					"it should open a shipping address popup when click on editaddress link",
					"successfully open a shipping address pop up when click on editaddress link",
					Common.getscreenShot("failed to open shipping address pop up when click on editaddress link"));

			Assert.fail();
		}
	}

	public void Validate_invalid_missing_fileds_shippingpage(String dataSet) {
		try {
			Sync.waitPageLoad();
			Common.scrollIntoView("name", "postcode");
			Common.textBoxInput("name", "postcode", "e5fg");
			Common.dropdown("name", "country_id", SelectBy.TEXT, "United States");
			Sync.waitElementVisible("xpath", "//div[@class='message warning']/span");
			String warningmsg = Common.findElement("xpath", "//div[@class='message warning']/span").getText();
			Common.assertionCheckwithReport(warningmsg.contains(
					"Provided Zip/Postal Code seems to be invalid. Example: 12345-6789; 12345. If you believe it is the right one you can ignore this notice."),
					"to validate the warning message for zipcode field", "Warning msg should be displayed",
					"Warning msg is displayed", "failed to display the warning msg");
			Common.textBoxInputClear("name", "postcode");
			Thread.sleep(3000);
			int emailsection = Common.findElements("id", "customer-email-fieldset").size();
			if (emailsection > 0) {
				Common.scrollIntoView("xpath", "//button[contains(@class,'continue primary')]");
				Common.clickElement("xpath", "//button[contains(@class,'continue primary')]");
			} else {

				Common.textBoxInputClear("name", "firstname");
				Common.textBoxInputClear("name", "lastname");
				Common.scrollIntoView("xpath", "//span[contains(text(),'Ship Here')]");
				Common.clickElement("xpath", "//span[contains(text(),'Ship Here')]");

			}
			Sync.waitElementVisible("xpath", "//div[contains(@class,'required _error')]");
			
			List<WebElement> errorfield = Common.findElements("xpath", "//div[contains(@class,'required _error')]");

			for (int i = 1; i <=errorfield.size(); i++) {
				

				String Errofield = errorfield.get(i-1).getAttribute("name");
				System.out.println(Errofield);
				
				List<WebElement> errormessage = Common.findElements("xpath",
						"//div[contains(@class,'mage-error')]/span");
				String requiredfielderrmsg = errormessage.get(i-1).getText();
				String color = Common.findElement("xpath", "(//div[contains(@class,'mage-error')]/span)["+i+"]")
						.getCssValue("color");
				String c = Color.fromString(color).asHex();
				System.out.println("Color is :" + color);
				System.out.println("Hex code for color:" + c);

				String address[] = data.get(dataSet).get("Email").split(",");
				if(address[i-1].contains("region")) {
					String textboxcolor = Common.findElement("xpath", "//select[contains(@name,'"+address[i-1]+"')]").getCssValue("color");
					System.out.println(textboxcolor);
					Assert.assertEquals(textboxcolor, "rgba(181, 26, 24, 1)");
				}
				else {
				String textboxcolor = Common.findElement("xpath", "//input[contains(@name,'"+address[i-1]+"')]").getCssValue("color");
				System.out.println(textboxcolor);
				Assert.assertEquals(textboxcolor, "rgba(181, 26, 24, 1)");
				}
				Common.assertionCheckwithReport(
						Errofield.contains(address[i-1]) && requiredfielderrmsg.contains("This is a required field.")
								&& c.contains("#b51a18") ,
						"To validate the required field error message is displayed for the field " + address[i-1],
						requiredfielderrmsg + "should be displayed for field" + address[i-1],
						requiredfielderrmsg + "error message is displayed for " + address[i-1],
						"Failed to display" + requiredfielderrmsg + "for" + address[i-1]);

			}

			

		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("To validate the required field error message is displayed for the field ",
					"This is a required field error message should be displayed for all the mandatory fields",
					"Error message dispaly unsuccessfull for the mandatory fields",
					Common.getscreenShot("Failed to display the error message for the mandatory fields"));

			Assert.fail();
		}

	}
	public void Click_Addressbook() {
		try {
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//a[contains(text(),'Address Book')]");
			Common.clickElement("xpath", "//a[contains(text(),'Address Book')]");
			String address_book = Common.getText("xpath", "//div[@class='column main']//h1[contains(text(),'Add New Address')]");
			System.out.println(address_book);
			Common.assertionCheckwithReport(address_book.contains(""), "To validate Address book in my account page",
					 "click on address book in my account page", "Successfully navigate to Add new address page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate address book in my account page", "click on address book in my account page",
					"unable to navigate add new address page", Common.getscreenShot("Failed to open add new address page"));
			Assert.fail();

		}
		}

	public void myaccount_addnewaddress(String dataSet) {
		try {
			Click_Addressbook();
			Common.textBoxInput("xpath", "//input[@id='firstname']", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@id='lastname']", data.get(dataSet).get("LastName"));

			Common.textBoxInput("name", "street[]", data.get(dataSet).get("Street"));
			Common.scrollIntoView("name", "city");

			Common.textBoxInput("name", "city", data.get(dataSet).get("City"));

			Common.dropdown("name", "region_id", SelectBy.TEXT, data.get(dataSet).get("State"));

			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
			Common.dropdown("name", "country_id", SelectBy.TEXT, "United States");

			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		  
			Sync.waitElementPresent("xpath", "//span[text()='Save Address']");
			Common.clickElement("xpath", "//span[text()='Save Address']");

			  String message=Common.getText("xpath", "//div[text()='You saved the address.']");
		        System.out.println(message);
		        Assert.assertEquals(message, "You saved the address.");
		        
		      Common.assertionCheckwithReport(message.contains("address"), "To validate Add new address page",
						 "add new address is filled in to the fields", "Successfully fill address details in add new address page");
	
		}catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating add new address",
					"add new address is filled in to the fields", "user faield to fill the add new address",
					Common.getscreenShotPathforReport("addnewaddressfaield"));
			Assert.fail();
		}
	}
	public void Search_PLP_product(String Dataset) {
		String sku3="";
		String product = data.get(Dataset).get("Products");
		try {
			Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
			
			Common.textBoxInput("xpath", "//input[@id='search']", product);
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			
			
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "(//li[@class='ais-InfiniteHits-item']//a[@class='a-product-name'])[1]");
			Common.clickElement("xpath", "(//li[@class='ais-InfiniteHits-item']//a[@class='a-product-name'])[1]");
			
			 Sync.waitPageLoad();
			 Sync.waitElementPresent("xpath", "(//span[text()='Add to Bag'])[1]");
			 sku3=Common.findElement("xpath", "(//span[text()='Add to Bag'])[1]").getAttribute("data-product-sku");
				System.out.println(sku3);
				Common.clickElement("xpath", "//button[@title='Add to Bag']");
			Sync.waitElementPresent("xpath", "//div[@data-ui-id='message-success']");
			String message2 = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
					.getAttribute("data-ui-id");
			Common.assertionCheckwithReport(message2.contains("success"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");
	
} catch (Exception | Error e) {

	e.printStackTrace();
	ExtenantReportUtils.addFailedLog("validating the  product add to the cart",
			"Product should be add to cart", "Unable to add product to the cart ",
			Common.getscreenShot("Failed to add product to the cart"));
	Assert.fail();

}}
	

	public void addshippingAddress(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String expectedResult = "shipping address is entering in the fields";
	    int size = Common.findElements(By.xpath("//span[contains(text(),'New Address')]")).size();
		try {
			Common.clickElement("xpath", "//span[contains(text(),'New Address')]");
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
          
            Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",data.get(dataSet).get("Street"));
			String Text=Common.getText("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='city']").clear();
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",data.get(dataSet).get("City"));
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
            
			Common.clickElement("xpath", "//button[contains(@class,'save-address')]");

			int sizeerrormessage = Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();

			Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying shipping addres filling ",
					"user will fill the all the shipping", "user fill the shiping address click save button",
					"faield to add new shipping address");
		}

		catch (Exception | Error e) {
     e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating shipping address",
					"shipping address is filled in to the fields", "user faield to fill the shipping address",
					Common.getscreenShotPathforReport("shipingaddressfaield"));
			Assert.fail();

		}}
	
	public void addDeliveryAddressShipping(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5000);
			Sync.waitElementVisible("id", "customer-email");
			Common.textBoxInput("id", "customer-email", data.get(dataSet).get("Email"));
		} catch (NoSuchElementException e) {
			minicart_Checkout();
			Common.textBoxInput("id", "customer-email", data.get(dataSet).get("Email"));

		}
		String expectedResult = "email field will have email address";
		try {
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
            int size = Common.findElements("id", "customer-email").size();
            Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,"Filled Email address", "unable to fill the email address");
            Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",data.get(dataSet).get("Street"));
			String Text=Common.getText("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='city']").clear();
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",data.get(dataSet).get("City"));
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
			Thread.sleep(5000);
			Common.clickElement("xpath", "//input[@class='a-radio-button__input']");
			
	
			expectedResult = "shipping address is filled in to the fields";
			Common.clickElement("xpath", "//button[@data-role='opc-continue']");
				ExtenantReportUtils.addPassLog("validating the shipping address field with valid Data", expectedResult,
						"Filled the shipping address", Common.getscreenShotPathforReport("shippingaddresspass"));
		}
			
				catch(Exception | Error e) {
				ExtenantReportUtils.addFailedLog("validating the shipping address field with valid Datas", expectedResult,
						"failed to add a addres in the filled",
						Common.getscreenShotPathforReport("failed to add a address"));
				
				Assert.fail();
		}
		
	}
	
	
	public void valid_DiscountCode(String dataSet) throws Exception {
		String expectedResult = "It should opens textbox input.";
		try {

			Sync.waitElementClickable("id", "block-discount-heading");
//			Common.clickElement("id", "block-discount-heading");

			Sync.waitElementPresent("id", "discount-code");

			Common.textBoxInput("id", "discount-code", data.get(dataSet).get("Discountcode"));

			int size = Common.findElements("id", "discount-code").size();
			Common.assertionCheckwithReport(size > 0, "verifying the Discount Code label", expectedResult,
					"Successfully open the discount input box", "User unable enter Discount Code");
			Sync.waitElementClickable("xpath", "//button[@value='Apply Discount']");
			Common.clickElement("xpath", "//button[@value='Apply Discount']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			expectedResult = "It should apply discount on your price.If user enters invalid Discount code it should display Discount code is not valid message.";
			String discountcodemsg = Common.getText("xpath", "//div[contains(@data-ui-id,'checkout-cart-validation')]");
			System.out.println(discountcodemsg);
			Common.assertionCheckwithReport(discountcodemsg.contains("Your coupon was successfully"),
					"verifying Discount code", expectedResult, "Discount code is working as expected",
					"Discount code is not applied");

		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating discount code", expectedResult,
					"User failed to proceed with discountcode",
					Common.getscreenShotPathforReport("discountcodefailed"));

			Assert.fail();

		}
	}

	public void invalid_DiscountCode(String dataSet) throws Exception {
		String expectedResult = "It should opens textbox input.";
		try {
			
			 Common.scrollIntoView("id", "block-discount-heading");
			Sync.waitElementClickable("id", "block-discount-heading");
			Common.clickElement("id", "block-discount-heading");

			Sync.waitElementPresent("id", "discount-code");
			Thread.sleep(4000);
			Common.textBoxInput("id", "discount-code", data.get(dataSet).get("InvalidDiscountcode"));

			int size = Common.findElements("id", "discount-code").size();
			Common.assertionCheckwithReport(size > 0, "verifying the Discount Code label", expectedResult,
					"Successfully open the discount input box", "User unable enter Discount Code");
			Sync.waitElementClickable("xpath", "//button[@value='Apply Discount']");
			Common.clickElement("xpath", "//button[@value='Apply Discount']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			expectedResult = "It should apply discount on your price.If user enters invalid promocode it should display coupon code is not valid message.";
			String discountcodemsg = Common.getText("xpath", "//div[contains(@data-ui-id,'checkout-cart-validation')]");
			Common.assertionCheckwithReport(discountcodemsg.contains("The coupon code isn't valid"),
					"verifying pomocode", expectedResult, "Discount code is not working as expected",
					"Discount code is not applied");

		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating discount code", expectedResult,
					"User failed to proceed with discountcode",
					Common.getscreenShotPathforReport("discountcodefailed"));

			Assert.fail();

		}
	}

	public void click_MyAccount() {
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
		
	}

	public void change_Shippingaddress_Addressbook(String Dataset) {
		// TODO Auto-generated method stub
		String firstname=data.get(Dataset).get("FirstName");
		String secondname=data.get(Dataset).get("LastName");
		String address=data.get(Dataset).get("Street");
		String phonenumber=data.get(Dataset).get("phone");
		String City=data.get(Dataset).get("City");
		String region=data.get(Dataset).get("Region");
	    String zipcode=data.get(Dataset).get("postcode");	
	    String shipping=data.get(Dataset).get("Shipping address");

	 
		try
		{
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//a[text()='Address Book']");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Address Book"),
					"validating the Navigation to the Address Book page",
					"After Clicking on Address Book CTA user should be navigate to the Address Book page",
					"Sucessfully User Navigates to the Address Book page after clicking on the Address Book CTA",
					"Failed to Navigate to the Address Book page after Clicking on Address Book CTA");
			
			String newaddress=Common.findElement("xpath", "//div[@class='block-content']//P").getText();
			System.out.println(newaddress);
			if(newaddress.contains("You have no other address"))
			{
				Common.clickElement("xpath", "//button[@title='Add New Address']");
				Sync.waitPageLoad();	
			    Sync.waitElementPresent("xpath", "//input[@name='firstname']");
				Common.clickElement("xpath", "//input[@name='firstname']");
				Common.textBoxInput("xpath", "//input[@name='firstname']",firstname);
				Common.clickElement("xpath", "//input[@name='lastname']");
				Common.textBoxInput("xpath", "//input[@name='lastname']",secondname);
				Sync.waitElementPresent(30, "xpath", "//input[@title='Phone Number']");
				Common.clickElement("xpath", "//input[@title='Phone Number']");
				Common.textBoxInput("xpath", "//input[@title='Phone Number']",phonenumber);
				Common.clickElement("xpath", "//input[@title='Address Line 1']");
				Common.textBoxInput("xpath", "//input[@title='Address Line 1']",address);
				Common.clickElement("xpath", "//input[@title='City']");
				Common.textBoxInput("xpath", "//input[@title='City']",City);
				Common.clickElement("xpath", "//select[@name='region_id']");
				Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT, region);
				Common.clickElement("xpath", "//input[@name='postcode']");
				Common.textBoxInput("xpath", "//input[@name='postcode']",zipcode);
				Common.clickElement("xpath", "//label[@for='primary_shipping']");
				Common.clickElement("xpath", "//button[@title='Save Address']");
				String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
				
		 Common.assertionCheckwithReport(message.equals("You saved the address."),
							"validating the saved message after saving address in address book",
							"Save address message should be displayed after the address saved in address book",
							"Sucessfully address has been saved in the address book",
							"Failed to save the address in the address book");
				shippingaddress_Addressbook("New ShippingAddress");
			}
			else
			{
				shippingaddress_Addressbook("New ShippingAddress");
		}
		}
		catch(Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the checkbox for billing address and text for the shipping address",
					"Checkbox should be display for the billing address and text should be display for the shipping address",
					"Unable to display the checkbox for the billing address and text is not displayed for the shipping address",
					Common.getscreenShot(
							"Failed to display checkbox for billing address and fail to display text for shipping address"));
			Assert.fail();
		}
		
	
		
	}

	public void Login_Oxo(String dataSet) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//button[contains(@class,'action login')]");
			Sync.waitPageLoad();
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

	public void shippingaddress_Addressbook(String Dataset) {
		// TODO Auto-generated method stub
		String firstname=data.get(Dataset).get("FirstName");
		String secondname=data.get(Dataset).get("LastName");
		String address=data.get(Dataset).get("Street");
		String phonenumber=data.get(Dataset).get("phone");
		String City=data.get(Dataset).get("City");
		String region=data.get(Dataset).get("Region");
	    String zipcode=data.get(Dataset).get("postcode");	
	    String shipping=data.get(Dataset).get("Shipping address");
	    try
	    {
		Common.clickElement("xpath", "//a[@title='Change Shipping Address']");
		Sync.waitPageLoad();	
	    Sync.waitElementPresent("xpath", "//input[@name='firstname']");
		Common.clickElement("xpath", "//input[@name='firstname']");
		Common.textBoxInput("xpath", "//input[@name='firstname']",firstname);
		Common.clickElement("xpath", "//input[@name='lastname']");
		Common.textBoxInput("xpath", "//input[@name='lastname']",secondname);
		Sync.waitElementPresent(30, "xpath", "//input[@title='Phone Number']");
		Common.clickElement("xpath", "//input[@title='Phone Number']");
		Common.textBoxInput("xpath", "//input[@title='Phone Number']",phonenumber);
		Common.clickElement("xpath", "//input[@title='Address Line 1']");
		Common.textBoxInput("xpath", "//input[@title='Address Line 1']",address);
		Common.clickElement("xpath", "//input[@title='City']");
		Common.textBoxInput("xpath", "//input[@title='City']",City);
		Common.clickElement("xpath", "//select[@name='region_id']");
		Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT, region);
		Common.clickElement("xpath", "//input[@name='postcode']");
		Common.textBoxInput("xpath", "//input[@name='postcode']",zipcode);
	  String checkbox=Common.findElement("xpath", "//input[@id='primary_billing']").getAttribute("type");
	  String text=Common.findElement("xpath", "//div[@class='message info']//span").getText();
	  Common.assertionCheckwithReport(checkbox.equals("checkbox") && text.equals("This is your default shipping address.") ,
				"validating the checkbox for billing address and text for the shipping address",
				"Checkbox should be display for the billing address and text should be display for the shipping address",
				"Sucessfully checkbox is displayed for the billing address and text is displayed for the shipping address",
				"Failed to display checkbox for billing address and fail to display text"+text+"for shipping address");
	  Common.clickElement("xpath", "//button[@title='Save Address']");
	  Sync.waitElementPresent("xpath", "//div[@data-ui-id='message-success']//div");
	  String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
	  String shippingaddress=Common.findElement("xpath", "//div[contains(@class,'box box-address-s')]//address").getText();
	  Common.assertionCheckwithReport(shippingaddress.equals(shipping)&&message.equals("You saved the address."),
				"validating the checkbox for billing address and text for the shipping address",
				"Checkbox should be display for the billing address and text should be display for the shipping address",
				"Sucessfully checkbox is displayed for the billing address and text is displayed for the shipping address",
				"Failed to display checkbox for billing address and fail to display text"+text+"for shipping address");
	    }
	    catch(Exception | Error e)
	    {
	    	e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the checkbox for billing address and text for the shipping address",
					"Checkbox should be display for the billing address and text should be display for the shipping address",
					"Unable to display the checkbox for the billing address and text is not displayed for the shipping address",
					Common.getscreenShot(
							"Failed to display checkbox for billing address and fail to display text for shipping address"));
			Assert.fail();
	    }
	}
	public String ValidateAccountcreationeyeicon(String dataSet) {
		// TODO Auto-generated method stub
		String Email = "";
		try {

			Sync.waitPageLoad();
			Sync.waitElementPresent("id", "firstname");
			Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));
			
			Common.textBoxInput("id", "email_address", Utils.getEmailid());
			Email=Common.findElement("id","email_address" ).getAttribute("value");
			System.out.println(Email);
			
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			String classes = Common.findElement("id", "validation-classes").getAttribute("class");
			String textlength = Common.findElement("id", "validation-length").getAttribute("class");
			Common.actionsKeyPress(Keys.PAGE_DOWN);

			Common.assertionCheckwithReport(classes.contains("complete") && textlength.contains("complete"),
					"Password is validated", "password should be validate", "failed to validate password");
			Common.actionsKeyPress(Keys.UP);
			
			String eyeicon = Common.findElement("xpath", "(//span[contains(@class,'m-password')])[1]").getAttribute("class");
			System.out.println(eyeicon);
			String password = Common.findElement("xpath", "//input[@id='password']").getAttribute("type");

			Common.assertionCheckwithReport(eyeicon.contains("hide") && password.equals("password"),
					"validating eye icon password filed", "Eye icon is in hide", "Sucessfully displays eyeicon hide",
					"faield to display eye icon hide");
			Common.clickElement("xpath", "//span[@class='m-password-input--toggle-icon icon-sign-in__hide']");
			String Eyeicon = Common.findElement("xpath", "//span[contains(@class,'m-password')]").getAttribute("class");
			System.out.println(Eyeicon);
			String Text = Common.findElement("xpath", "//input[@id='password']").getAttribute("type");
			Common.assertionCheckwithReport(Eyeicon.contains("show") && Text.equals("text"),
					"validating eye icon password field", "Eye icon is in hide", "Sucessfully displays eyeicon hide",
					"faield to display eye icon hide");
			

			Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));
			Confirmpassword_eyeicon_validation();
			
			

			
			
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  my Account page",
					"User should able to navigate to the my account page after clicking on submit button",
					"unable to navigate to the My account page",
					Common.getscreenShot("failed to navigates to My Account Page"));
			Assert.fail();
		}
		return Email;
		
	}

	public void Confirmpassword_eyeicon_validation() {
		// TODO Auto-generated method stub

		try {

			String eyeicon = Common.findElement("xpath", "(//span[contains(@class,'m-password')])[2]").getAttribute("class");
			System.out.println(eyeicon);
			String password = Common.findElement("xpath", "//input[@id='password-confirmation']").getAttribute("type");

			Common.assertionCheckwithReport(eyeicon.contains("hide") && password.equals("password"),
					"validating eye icon password filed", "Eye icon is in hide", "Sucessfully displays eyeicon hide",
					"faield to display eye icon hide");
			Common.clickElement("xpath", "//span[@class='m-password-input--toggle-icon icon-sign-in__hide']");
			String Eyeicon = Common.findElement("xpath", "//span[contains(@class,'m-password')]").getAttribute("class");
			System.out.println(Eyeicon);
			String Text = Common.findElement("xpath", "//input[@id='password-confirmation']").getAttribute("type");
			Common.assertionCheckwithReport(Eyeicon.contains("show") && Text.equals("text"),
					"validating eye icon password field", "Eye icon is in hide", "Sucessfully displays eyeicon hide",
					"faield to display eye icon hide");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating confirm password eyeicon",
					"password able to display", "unable to display password",
					Common.getscreenShotPathforReport("faield to gdisplay password"));
			Assert.fail();

		}

	}

	public void Validate_Createaccount_signup_newsletter() {
		// TODO Auto-generated method stub

		try {

			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "//input[@name='is_subscribed']").size();
			Common.assertionCheckwithReport(size > 0 ,
					"validating signup newsletter checkbox", "signup newsletter check box should be display",
					"able to display signup newsletter checkbox", "faield to signup news letter checkbox");

			Common.clickElement("xpath", "//input[@name='is_subscribed']");

			Sync.waitElementClickable("xpath", "//button[@title='Sign Up']");
			Common.clickElement("xpath", "//button[@title='Sign Up']");
			Sync.waitPageLoad();
			Sync.waitElementVisible("xpath", "//h1[@class='page-title-wrapper h2']");
			
			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"),
					"validating the  my Account page",
					"User should able to navigate to the my account page after clicking on submit button",
					"Sucessfully navigate to the My account page ", "failed to navigates to My Account Page");
		

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating signup newsletter checkbox",
					"signup newsletter check box should be display", "unable to display signup newsletter checkbox",
					Common.getscreenShotPathforReport("faield to signup news letter checkbox"));
			Assert.fail();

		}


	}

	public void Admin_signin(String dataSet) {
		// TODO Auto-generated method stub

		try {
			Common.openNewTab();
			Common.oppenURL(data.get(dataSet).get("AdminURL"));
			Sync.waitPageLoad();
			Sync.waitElementClickable("xpath", "//a[@class='action login primary']");
			Common.javascriptclickElement("xpath", "//a[@class='action login primary']");
			Sync.waitPageLoad(30);
			Sync.waitElementPresent("name", "loginfmt");
			Common.textBoxInput("name", "loginfmt", data.get(dataSet).get("Adminusername"));
			Common.clickElement("id", "idSIButton9");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Sync.waitElementPresent(30, "name", "passwd");
			Common.textBoxInput("name", "passwd", data.get(dataSet).get("Adminpassword"));
			Common.clickElement("id", "idSIButton9");
			Sync.waitPageLoad();

			Sync.waitElementVisible(30, "xpath", "//div[@id='lightbox']");
			if (Common.isElementDisplayed("id", "KmsiCheckboxField")) {
				Common.javascriptclickElement("id", "KmsiCheckboxField");
			}
			Sync.waitElementClickable("id", "idSIButton9");
			Common.mouseOverClick("id", "idSIButton9");
			Sync.waitPageLoad();
			Thread.sleep(5000);
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

	public void Customers() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Common.clickElement("id", "menu-magento-customer-customer");
			Sync.waitElementPresent("id", "menu-magento-customer-customer");
			String customers = Common.findElement("xpath", "//strong[contains(text(),'Customers')]").getText();
			System.out.println(customers);
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(customers.equals("Customers"),
					"To Validate the customers menu is displayed",
					"should display the customer menu after clicking on the customers",
					"Customers field menu is displayed after a click on the customers button",
					"Failed to display customers menu");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To Validate the customers menu is displayed",
					"should display the customer menu after clicking on the customers",
					"unable to display Customers field menu after a click on the customers button",
					"Failed to display customers field menu");
			Assert.fail();
		}


	}

	public void Allcustomers() {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//span[text()='All Customers']");
			Common.clickElement("xpath", "//span[text()='All Customers']");
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Customers / Customers / Magento Admin"),
					"Validating customers field page navigation ",
					"After clicking all customers it will navigate to the Customer field page",
					"Successfully navigate to the Customer field page ",
					"Failed to navigate to the Customer field page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating customers field page navigation ",
					"After clicking all customers it will navigate Customer field page",
					"Unable to navigate to the Customer filed page",
					Common.getscreenShotPathforReport("Failed to navigate Customer filed page"));
			Assert.fail();

		}


		
	}
	public void Viewcustomer(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitElementPresent("xpath", "//button[@data-action='grid-filter-expand']");
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			Common.textBoxInput("xpath", "//input[@name='email']",Dataset);
			Common.actionsKeyPress(Keys.ENTER);
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			String records = Common.findElement("xpath", "//div[@class='admin__control-support-text']").getText();
			String EMAIL=Common.findElement("xpath", "(//div[@class='data-grid-cell-content'])[3]").getText();
			
			Common.assertionCheckwithReport(records.contains("1")&& EMAIL.equals(Dataset),
					"Validating customers field page navigation ",
					"After clicking all customers it will navigate to the Customer field page",
					"Successfully navigate to the Customer field page ",
					"Failed to navigate to the Customer field page");
			
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating customers field page navigation ",
					"After clicking all customers it will navigate Customer field page",
					"Unable to navigate to the Customer filed page",
					Common.getscreenShotPathforReport("Failed to navigate Customer filed page"));
			Assert.fail();

		}}
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

		public void Marketing() {
			// TODO Auto-generated method stub
			try {
				Sync.waitPageLoad();
				Common.clickElement("id", "menu-magento-backend-marketing");
				Sync.waitElementPresent("id", "menu-magento-backend-marketing");
				String Marketing = Common.findElement("xpath", "//strong[contains(text(),'Marketing')]").getText();
				System.out.println(Marketing);
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
				Common.assertionCheckwithReport(Marketing.equals("Marketing"),
						"To Validate the Marketing is displayed",
						"should display the Marketing menu after clicking on the Marketing",
						"Marketing field menu is displayed after a click on the Marketing button",
						"Failed to display Marketing menu");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("To Validate the Marketing menu is displayed",
						"should display the Marketing menu after clicking on the Marketing",
						"unable to display Marketing field menu after a click on the Marketing button",
						"Failed to display Marketing field menu");
				Assert.fail();
			}


		}

		public void Admin_newsletter_validation(String Dataset ) {
			// TODO Auto-generated method stub
			try {
				Sync.waitElementPresent("xpath", "//span[text()='Newsletter Subscribers']");
				Common.clickElement("xpath", "//span[text()='Newsletter Subscribers']");
				Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
				Common.assertionCheckwithReport(Common.getPageTitle().contains("Newsletter Subscribers"),
						"Validating customers field page navigation ",
						"After clicking all customers it will navigate to the Customer field page",
						"Successfully navigate to the Customer field page ",
						"Failed to navigate to the Customer field page");
				Common.clickElement("xpath", "//button[@title='Search']");
				
				Common.textBoxInput("xpath", "//input[@name='email']",Dataset);
				Common.actionsKeyPress(Keys.ENTER);
				String subscribe = Common.findElement("xpath", "//td[@class=' col-status col-status  ']").getText();
				
				Common.assertionCheckwithReport(subscribe.equals("Subscribed"),
						"Validating customer subscribtion ",
						"customer should be subscribed for new letter subscrpition",
						"Successfully subscribed new letter subscrption ",
						"Failed to subscribe new letter subscription");
				Common.clickElement("xpath", "//button[@title='Reset Filter']");
				
				

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("Validating customer subscribtion ",
						"customer should be subscribed for new letter subscrpition",
						"unable to subscribe new letter subscrption ",
				
						Common.getscreenShotPathforReport("Failed to navigate newsletter filed page"));
				Assert.fail();

			}


			
		}
		public void change_Billingaddress_Addressbook(String Dataset) {
			// TODO Auto-generated method stub
			String firstname=data.get(Dataset).get("FirstName");
			String secondname=data.get(Dataset).get("LastName");
			String address=data.get(Dataset).get("Street");
			String phonenumber=data.get(Dataset).get("phone");
			String City=data.get(Dataset).get("City");
			String region=data.get(Dataset).get("Region");
		    String zipcode=data.get(Dataset).get("postcode");	
		    String shipping=data.get(Dataset).get("Shipping address");

		 
			try
			{
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//a[text()='Address Book']");
				Sync.waitPageLoad();
				Common.assertionCheckwithReport(Common.getPageTitle().equals("Address Book"),
						"validating the Navigation to the Address Book page",
						"After Clicking on Address Book CTA user should be navigate to the Address Book page",
						"Sucessfully User Navigates to the Address Book page after clicking on the Address Book CTA",
						"Failed to Navigate to the Address Book page after Clicking on Address Book CTA");
				
				String newaddress=Common.findElement("xpath", "//div[@class='block-content']//P").getText();
				if(newaddress.contains("You have no other address"))
				{
					Common.clickElement("xpath", "//button[@title='Add New Address']");
					Sync.waitPageLoad();	
				    Sync.waitElementPresent("xpath", "//input[@name='firstname']");
					Common.clickElement("xpath", "//input[@name='firstname']");
					Common.textBoxInput("xpath", "//input[@name='firstname']",firstname);
					Common.clickElement("xpath", "//input[@name='lastname']");
					Common.textBoxInput("xpath", "//input[@name='lastname']",secondname);
					Sync.waitElementPresent(30, "xpath", "//input[@title='Phone Number']");
					Common.clickElement("xpath", "//input[@title='Phone Number']");
					Common.textBoxInput("xpath", "//input[@title='Phone Number']",phonenumber);
					Common.clickElement("xpath", "//input[@title='Address Line 1']");
					Common.textBoxInput("xpath", "//input[@title='Address Line 1']",address);
					Common.clickElement("xpath", "//input[@title='City']");
					Common.textBoxInput("xpath", "//input[@title='City']",City);
					Common.clickElement("xpath", "//select[@name='region_id']");
					Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT, region);
					Common.clickElement("xpath", "//input[@name='postcode']");
					Common.textBoxInput("xpath", "//input[@name='postcode']",zipcode);
					Common.clickElement("xpath", "//label[@for='primary_billing']");
					Common.clickElement("xpath", "//button[@title='Save Address']");
					String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
					
			 Common.assertionCheckwithReport(message.equals("You saved the address."),
								"validating the saved message after saving address in address book",
								"Save address message should be displayed after the address saved in address book",
								"Sucessfully address has been saved in the address book",
								"Failed to save the address in the address book");
					Billingaddress_Addressbook(Dataset);
				}
				else
				{
					Billingaddress_Addressbook(Dataset);
			}
			}
			catch(Exception | Error e)
			{
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the checkbox for billing address and text for the shipping address",
						"Checkbox should be display for the billing address and text should be display for the shipping address",
						"Unable to display the checkbox for the billing address and text is not displayed for the shipping address",
						Common.getscreenShot(
								"Failed to display checkbox for billing address and fail to display text for shipping address"));
				Assert.fail();
			}
			
		
		}
		public void Billingaddress_Addressbook(String Dataset) {
			// TODO Auto-generated method stub
			String firstname=data.get(Dataset).get("FirstName");
			String secondname=data.get(Dataset).get("LastName");
			String address=data.get(Dataset).get("Street");
			String phonenumber=data.get(Dataset).get("phone");
			String City=data.get(Dataset).get("City");
			String region=data.get(Dataset).get("Region");
		    String zipcode=data.get(Dataset).get("postcode");	
		    String shipping=data.get(Dataset).get("Shipping address");
		    try
		    {
			Common.clickElement("xpath", "//a[@title='Change Billing Address']");
			Sync.waitPageLoad();	
		    Sync.waitElementPresent("xpath", "//input[@name='firstname']");
			Common.clickElement("xpath", "//input[@name='firstname']");
			Common.textBoxInput("xpath", "//input[@name='firstname']",firstname);
			Common.clickElement("xpath", "//input[@name='lastname']");
			Common.textBoxInput("xpath", "//input[@name='lastname']",secondname);
			Sync.waitElementPresent(30, "xpath", "//input[@title='Phone Number']");
			Common.clickElement("xpath", "//input[@title='Phone Number']");
			Common.textBoxInput("xpath", "//input[@title='Phone Number']",phonenumber);
			Common.clickElement("xpath", "//input[@title='Address Line 1']");
			Common.textBoxInput("xpath", "//input[@title='Address Line 1']",address);
			Common.clickElement("xpath", "//input[@title='City']");
			Common.textBoxInput("xpath", "//input[@title='City']",City);
			Common.clickElement("xpath", "//select[@name='region_id']");
			Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT, region);
			Common.clickElement("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']",zipcode);
		  String checkbox=Common.findElement("xpath", "//input[@id='primary_shipping']").getAttribute("type");
		  String text=Common.findElement("xpath", "//div[@class='message info']//span").getText();
		  System.out.println(text);
		  Common.assertionCheckwithReport(checkbox.equals("checkbox") && text.equals("This is your default billing address.") ,
					"validating the checkbox for billing address and text for the shipping address",
					"Checkbox should be display for the billing address and text should be display for the shipping address",
					"Sucessfully checkbox is displayed for the billing address and text is displayed for the shipping address",
					"Failed to display checkbox for billing address and fail to display text"+text+"for shipping address");
		  Common.clickElement("xpath", "//button[@title='Save Address']");
		  Sync.waitElementPresent("xpath", "//div[@data-ui-id='message-success']//div");
		  String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
		String shippingaddress=Common.findElement("xpath", "//div[contains(@class,'box box-address-bil')]//address").getText();
		 System.out.println(shippingaddress);
		 
		Common.assertionCheckwithReport(shippingaddress.equals(shipping)&&message.equals("You saved the address."),
					"validating the checkbox for shipping address and text for the billing address",
					"Checkbox should be display for the shipping address and text should be display for the billing address",
					"Sucessfully checkbox is displayed for the shipping address and text is displayed for the billing address",
					"Failed to display checkbox for shipping address and fail to display text"+text+"for billing address");
		    }
		    catch(Exception | Error e)
		    {
		    	e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the checkbox for shipping address and text for the billing address",
						"Checkbox should be display for the shipping address and text should be display for the billing address",
						"Sucessfully checkbox is displayed for the shipping address and text is displayed for the billing address",
						Common.getscreenShot(
								"Failed to display checkbox for shipping address and fail to display text for billing address"));
				Assert.fail();
		    }
			
		}

public void My_Orders_Page() {
	try
	{
		Sync.waitPageLoad();
		Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
		Sync.waitElementPresent(30, "xpath", "//a[text()='My Orders']");
		Common.clickElement("xpath", "//a[text()='My Orders']");
		Sync.waitPageLoad();
		String MyOrder = Common.findElement("xpath", "//h1[contains(text(),'My Orders')]").getText();
		Common.assertionCheckwithReport(MyOrder.equals("My Orders"),
				"validating the Navigation to the My Orders page",
				"After Clicking on My Orders CTA user should be navigate to the My Orders page",
				"Sucessfully User Navigates to the My Orders page after clicking on the My Orders CTA",
				"Failed to Navigate to the My Orders page after Clicking on My Orders CTA");
		String message1=Common.findElement("xpath", "//span[contains(text(),'You have placed no orders.')]").getText();
		System.out.println(message1);
		 Common.assertionCheckwithReport(message1.equals("You have placed no orders."),
					"validating the  message after navigating to the My Orders page ",
					" message should be displayed in My Orders page ",
					"Sucessfully message should be displayed in My Orders page",
					"Failed to display message in my orders page");
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the  My Myorders page with message",
		"After Clicking on My Orders CTA user should be navigate to the My Orders page with message",
		"Unable to Display the message in the My orders page",
				Common.getscreenShot(
						"Failed to Display message in the My orders page"));
		Assert.fail();
	}
}
public void Validate_Accountinformation_change_email(String Dataset) {
	// TODO Auto-generated method stub
	
	
	try {
		Sync.waitPageLoad();
		Common.clickElement("xpath", "//a[text()='Account Information']");
		Sync.waitPageLoad();

		Common.assertionCheckwithReport(Common.getPageTitle().equals("Account Information"),
				"validating the Navigation to the Account information page",
				"After Clicking on Account information CTA user should be navigate to the AAccount information page",
				"Sucessfully User Navigates to the Account information page after clicking on the Account information CTA",
				"Failed to Navigate to the Account information page after Clicking on Account information CTA");
		
		Sync.waitElementPresent("xpath", "//button[contains(@class,'toggle-email')]//span//span");
	Common.clickElement("xpath", "//button[contains(@class,'toggle-email')]//span//span"); 
	
	 String emailsection=Common.findElement("xpath", "//div[contains(@class,'collapsible-email')]").getAttribute("class");
	  System.out.println(emailsection);
	  
		Common.assertionCheckwithReport(emailsection.contains("active"),
				"validating the email section when we click on edit",
				"email section field should be displayed when we click on edit",
				"Sucessfully emailsection  is displayed when we click on edit",
				"Failed to display section when we click on edit");
	//div[contains(@class,'collapsible-email')]
	  String Newemail=Common.findElement("xpath", "//input[@name='email']").getAttribute("type");
	  System.out.println(Newemail);
	  String Currentpassword=Common.findElement("xpath", "(//input[@name='current_password'])[1]").getAttribute("type");
		System.out.println(Currentpassword);
		Common.assertionCheckwithReport(Newemail.equals("email")&&Currentpassword.equals("password"),
				"validating the email input field and current password field",
				"email input field should be displayed and current password filed should be displayed",
				"Sucessfully email input field is displayed  and current password field is displayed",
				"Failed to display email input field and current password field ");
		validate_password_field(Dataset);
		
		
		
		
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the Navigation to the Account information page",
				"After Clicking on Account information CTA user should be navigate to the AAccount information page",
				"Sucessfully User Navigates to the Account information page after clicking on the Account information CTA",
				Common.getscreenShotPathforReport("Failed to navigate account information page"));
		Assert.fail();
	}
		
	
	
	
}

public void validate_password_field(String Dataset) {
	// TODO Auto-generated method stub
	try {

		Sync.waitElementPresent("xpath", "(//input[@name='current_password'])[1]");
		
		
		Common.textBoxInput("xpath", "(//input[@name='current_password'])[1]", data.get(Dataset).get("Password"));
		
		String passworddots = Common.findElement("xpath", "(//input[@name='current_password'])[1]").getAttribute("type");
		System.out.println(passworddots);

		Common.assertionCheckwithReport(passworddots.contains("password"),"validating the passoword dot display",
				"the password should be displed in dots",
				"Sucessfully password disply in dots",
				"Failed to display password in dots");
		Common.actionsKeyPress(Keys.UP);
		

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the passoword dot display",
				"the password should be displed in dots",
				"Sucessfully password disply in dots",
		Common.getscreenShotPathforReport("Failed to navigate newsletter filed page"));
		Assert.fail();

	}


	
}

public void validate_Accountinformation_invalid_email(String Dataset) {
	// TODO Auto-generated method stub
	
	String errormessage = data.get(Dataset).get("errormessage");
	try {
		Sync.waitElementPresent("xpath", "//input[@title='Email']");
		//Common.textBoxInputClear("xpath", "//input[@title='Email']");
		Common.textBoxInput("xpath", "//input[@title='Email']", data.get(Dataset).get("Email"));
		Common.javascriptclickElement("xpath", "//div[contains(@class,'collapsible-email')]//button[contains(@class,'save primary')]");
		Sync.waitElementVisible(30, "xpath", "//div[@class='mage-error']");
		String emailerror = Common.findElement("xpath", "//div[@class='mage-error']").getText();
		System.out.println(emailerror);
		Common.assertionCheckwithReport(emailerror.equals(errormessage),
				"To validate the error message when given invalid email or password",
				"Should dispaly the error message " + errormessage, emailerror + "error message is displayed",
				"Failed to display error message");
		
		
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To validate the error message when given invalid email ",
				"Should dispaly the error message" + errormessage, "Error message dispaly Failed ",
				Common.getscreenShotPathforReport("Failed to display the error message"));
		Assert.fail();
	}
	return;
}	
public void ChangeEmail_signin(String dataSet) {

	try {
		
		
		Common.textBoxInput("xpath", "//input[@title='Email']", Utils.getEmailid());
		String EMAIL=Common.findElement("xpath","//input[@title='Email']" ).getAttribute("value");
		System.out.println(EMAIL);
		Common.javascriptclickElement("xpath", "//div[contains(@class,'collapsible-email')]//button[contains(@class,'save primary')]");
		
		String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
		
		 Common.assertionCheckwithReport(message.contains("You saved the account information."),
							"validating the saved message after changing email",
							"Save email message should be displayed after the email is saved",
							"Sucessfully changed email has been saved ",
							"Failed to save the change email");
		Sync.waitPageLoad();
		Common.textBoxInput("id", "email", EMAIL);
		Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
		Common.clickElement("xpath", "//button[contains(@class,'action login')]");
		Sync.waitPageLoad();
		Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"),
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
public void AcceptAll() {



    try {



        Thread.sleep(5000);
         if (Common.findElement("xpath", "//button[@id='truste-consent-button']") != null) {



            Common.clickElement("xpath", "//button[@id='truste-consent-button']");
         }
         }catch (Exception e) {



        e.printStackTrace();



    }
 }		
public void Validate_Accountinformation_change_password(String Dataset) {
	// TODO Auto-generated method stub
	
	
	try {
		Sync.waitPageLoad();
		Common.clickElement("xpath", "//a[text()='Account Information']");
		Sync.waitPageLoad();

		Common.assertionCheckwithReport(Common.getPageTitle().equals("Account Information"),
				"validating the Navigation to the Account information page",
				"After Clicking on Account information CTA user should be navigate to the AAccount information page",
				"Sucessfully User Navigates to the Account information page after clicking on the Account information CTA",
				"Failed to Navigate to the Account information page after Clicking on Account information CTA");
		
		Sync.waitElementPresent("xpath", "//button[contains(@class,'toggle-password')]//span//span");
	Common.clickElement("xpath", "//button[contains(@class,'toggle-password')]//span//span"); 
	
	 String emailsection=Common.findElement("xpath", "//div[contains(@class,'collapsible-password')]").getAttribute("class");
	  System.out.println(emailsection);
	  
		Common.assertionCheckwithReport(emailsection.contains("active"),
				"validating the confirm password section when we click on edit",
				"confirm password section field should be displayed when we click on edit",
				"Sucessfully confirm password  is displayed when we click on edit",
				"Failed to display  confirm password section when we click on edit");
	
	  String currentpassword=Common.findElement("xpath", "(//input[@name='current_password'])[2]").getAttribute("name");
	  System.out.println(currentpassword);
	  String Newpassword=Common.findElement("xpath", "(//input[@name='password'])[1]").getAttribute("type");
		System.out.println(Newpassword);
		 String confirmpassword=Common.findElement("xpath", "(//input[@name='password_confirmation'])").getAttribute("name");
			System.out.println(confirmpassword);
		
		Common.assertionCheckwithReport(currentpassword.contains("password")&&Newpassword.contains("password")&&confirmpassword.contains("confirmation"),
				"validating the email input field and current password field",
				"email input field should be displayed and current password filed should be displayed",
				"Sucessfully email input field is displayed  and current password field is displayed",
				"Failed to display email input field and current password field ");
		
		
		
		
		
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the Navigation to the Account information page",
				"After Clicking on Account information CTA user should be navigate to the AAccount information page",
				"Sucessfully User Navigates to the Account information page after clicking on the Account information CTA",
				Common.getscreenShotPathforReport("Failed to navigate account information page"));
		Assert.fail();
	}}
		
	public void validate_Accountinformation_invalid_password(String Dataset) {
		// TODO Auto-generated method stub
		
		String errormessage = data.get(Dataset).get("passworderror");
		String invalidnewpassworderror = data.get(Dataset).get("InvalidnewPassword");
		String confirmpassword = data.get(Dataset).get("NonmatchPassword");
		try {
			
			
			
			Sync.waitElementPresent("xpath", "(//input[@name='current_password'])[2]");
			Common.textBoxInput("xpath", "(//input[@name='current_password'])[2]", data.get(Dataset).get("Password"));
			Sync.waitElementPresent("xpath", "(//input[@name='password'])[1]");
			Common.textBoxInput("xpath", "(//input[@name='password'])[1]", data.get(Dataset).get("Newpassword"));
			Sync.waitElementPresent("xpath", "(//input[@name='password_confirmation'])");
			Common.textBoxInput("xpath", "(//input[@name='password_confirmation'])", data.get(Dataset).get("Newpassword"));
			Common.javascriptclickElement("xpath", "//div[contains(@class,'collapsible-password')]//button[contains(@class,'save primary')]");
			Sync.waitElementVisible(30, "xpath", "//div[contains(@class,'message-error')]");
			String invalidcurrentpassword = Common.findElement("xpath", "//div[contains(@class,'message-error')]/div").getText();
			System.out.println(invalidcurrentpassword);
			Common.assertionCheckwithReport(invalidcurrentpassword.equals(errormessage),
					"To validate the error message when given invalid email or password",
					"Should dispaly the error message " + errormessage, invalidcurrentpassword + "error message is displayed",
					"Failed to display error message");
			
			Sync.waitElementPresent("xpath", "//button[contains(@class,'toggle-password')]//span//span");
			Common.clickElement("xpath", "//button[contains(@class,'toggle-password')]//span//span");
			
			Sync.waitElementPresent("xpath", "(//input[@name='current_password'])[2]");
			Common.textBoxInput("xpath", "(//input[@name='current_password'])[2]", data.get(Dataset).get("CurrentPassword"));
			Sync.waitElementPresent("xpath", "(//input[@name='password'])[1]");
			Common.textBoxInput("xpath", "(//input[@name='password'])[1]", data.get(Dataset).get("Password"));
			Sync.waitElementPresent("xpath", "(//input[@name='password_confirmation'])");
			Common.textBoxInput("xpath", "(//input[@name='password_confirmation'])", data.get(Dataset).get("Newpassword"));
			Common.javascriptclickElement("xpath", "//div[contains(@class,'collapsible-password')]//button[contains(@class,'save primary')]");
			Sync.waitElementVisible(30, "xpath", "//div[@id='password-error']");
			String invalidnewpassword = Common.findElement("xpath", "//div[@id='password-error']").getText();
			System.out.println(invalidnewpassword);
			Common.assertionCheckwithReport(invalidnewpassword.equals(invalidnewpassworderror),
					"To validate the error message when given invalid email or password",
					"Should dispaly the error message " + invalidnewpassworderror, invalidnewpassword + "error message is displayed",
					"Failed to display error message");
			
			
			Sync.waitElementPresent("xpath", "(//input[@name='password'])[1]");
			
			Common.textBoxInput("xpath", "(//input[@name='password'])[1]", data.get(Dataset).get("Newpassword"));
			Sync.waitElementPresent("xpath", "(//input[@name='password_confirmation'])");
			Common.textBoxInput("xpath", "(//input[@name='password_confirmation'])", data.get(Dataset).get("Password"));
			Common.javascriptclickElement("xpath", "//div[contains(@class,'collapsible-password')]//button[contains(@class,'save primary')]");
			Sync.waitElementVisible(30, "xpath", "//div[contains(@class,'message-error')]/div");
			String nonmatchpassword = Common.findElement("xpath", "//div[contains(@class,'message-error')]/div").getText();
			System.out.println(nonmatchpassword);
			Common.assertionCheckwithReport(nonmatchpassword.equals(confirmpassword),
					"To validate the error message when given invalid email or password",
					"Should dispaly the error message " + confirmpassword, nonmatchpassword + "error message is displayed",
					"Failed to display error message");
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the error message when given invalid email ",
					"Should dispaly the error message" + errormessage, "Error message dispaly Failed ",
					Common.getscreenShotPathforReport("Failed to display the error message"));
			Assert.fail();
	
		
	}	
	
	
}
	public void Changepassword_signin(String Dataset) {

		try {
			
			//p[@class='text-email']
			String Email=Common.findElement("xpath", "//p[@class='text-email']").getText();
			System.out.println(Email);
			Sync.waitElementPresent("xpath", "//button[contains(@class,'toggle-password')]//span//span");
		Common.clickElement("xpath", "//button[contains(@class,'toggle-password')]//span//span"); 
		
		Sync.waitElementPresent("xpath", "(//input[@name='current_password'])[2]");
		Common.textBoxInput("xpath", "(//input[@name='current_password'])[2]", data.get(Dataset).get("CurrentPassword"));
		Sync.waitElementPresent("xpath", "(//input[@name='password'])[1]");
		Common.textBoxInput("xpath", "(//input[@name='password'])[1]", data.get(Dataset).get("Newpassword"));
		Sync.waitElementPresent("xpath", "(//input[@name='password_confirmation'])");
		Common.textBoxInput("xpath", "(//input[@name='password_confirmation'])", data.get(Dataset).get("Newpassword"));
		Common.javascriptclickElement("xpath", "//div[contains(@class,'collapsible-password')]//button[contains(@class,'save primary')]");
		
		
		
		
			
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
			
			 Common.assertionCheckwithReport(message.contains("You saved the account information."),
								"validating the saved message after changing email",
								"Save email message should be displayed after the email is saved",
								"Sucessfully changed email has been saved ",
								"Failed to save the change email");
			Sync.waitPageLoad();
			Common.textBoxInput("id", "email", Email);
			Common.textBoxInput("id", "pass", data.get(Dataset).get("Newpassword"));
			Common.clickElement("xpath", "//button[contains(@class,'action login')]");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"),
					"To validate the user lands on Home page after successfull login",
					"After clicking on the signIn button it should navigate to the Home page",
					"user Sucessfully navigate to the Home page after clicking on the signIn button",
					"Failed to signIn and not navigated to the Home page ");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the user Navigate to my account after successfull login",
					"After clicking on the signin button it should navigate to the myaccount page",
					"Unable to navigate the user to the myaccount after clicking on the SignIn button",
					Common.getscreenShotPathforReport("Failed to signIn and not navigated to the myaccount "));

			Assert.fail();
		}
	}			

public void Validate_OrderConfirmationPage(String dataSet) {
	// TODO Auto-generated method stub
	String expectedResult = "It redirects to order confirmation page";

	try {
		String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();
	int sizes = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();
	
	String	order =	Common.findElement("xpath", "//a[@class='order-number']").getAttribute("href");
	System.out.println(order);
	
	String	Right_rail = Common.getText("xpath", "//div[@class='c-icon-card-list__headers']");
	System.out.println(Right_rail);
	

	int ordersuccesspagecategorylinks = Common.findElements("xpath", "//div[@class='actions-toolbar']//a[contains(@class,'button-secondary')]").size();
	
	for(int i=1;i<=ordersuccesspagecategorylinks;i++) {
		String links[] = data.get(dataSet).get("categorylinks").toLowerCase().split(",");
		System.out.println(links[i-1]);
		String URL = System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL));
		String CTAlink = URL+"shop/"+links[i-1]+".html";
		System.out.println(CTAlink);
		String categorylink = Common.findElement("xpath", "(//div[@class='actions-toolbar']//a[contains(@class,'button-secondary')])["+i+"]").getAttribute("href");
		System.out.println(categorylink);
		Common.assertionCheckwithReport(categorylink.equals(CTAlink), "verify the category links in order success page", 
				"category links should peresent in order success page","In order success page successfully displayed category links",
				"failed to display category links in order success page");
	}
	Common.assertionCheckwithReport(sucessMessage.contains("Thank you for your purchase!") && order.contains("view/order_id/") &&
			Right_rail.contains("We are here for you"),
			"verifying the order confirmation confirmation page", expectedResult,
			"Successfully It redirects to order confirmation page Order Placed",
			"User unabel to go orderconformation page");

		
	}catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the order confirmation confirmation page",
				"Successfully It redirects to order confirmation page Order Placed",
				"User unabel to go orderconformation page",
		
				Common.getscreenShotPathforReport("User unabel to go orderconformation page"));
		Assert.fail();

	}	
}

public void Validating_We_are_here_for_you_section_Content(String dataSet) {
	// TODO Auto-generated method stub
	try {
		int content=Common.findElements("xpath", "//span[@class='m-icon-card__text']").size();
			System.out.println(content);
	        for(int i=1;i<=content;i++) {
	        	String text[] = data.get(dataSet).get("Content").split(",");
	        	
	        	//https://mcloud-na-preprod.oxo.com/shop/kitchenware.html
	        	String Rightrail_Content = Common.findElement("xpath", "(//span[@class='m-icon-card__text'])[" +i +"]").getText();
	        	System.out.println(Rightrail_Content);
	        	Common.assertionCheckwithReport(Rightrail_Content.contains(text[i-1]), "verify the Content in the We are here for you section  ", 
						"Content should present in the We are here for you section",
						"Content successfully present in the We are here for you section", 
						"failed to  present content in the We are here for you section");	
	        	
	        }
		
			 
		}catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verify the Content in the We are here for you section  ",
					"Content should present in the We are here for you section",
					"failed to  present content in the We are here for you section",
			
					Common.getscreenShotPathforReport("failed to  present content in the We are here for you section"));
			Assert.fail();
	}

}

public void Validating_We_are_here_for_you_section_hyperlink(String dataSet) {
	// TODO Auto-generated method stub
	try {
		int right_rail=Common.findElements("xpath", "//a[@class='m-icon-card__link']").size();
		System.out.println(right_rail);
        for(int i=1;i<=right_rail;i++) {
        	String link[] = data.get(dataSet).get("hyperlink").split(",");
        	System.out.println(link[i-1]);
        	String Rightrail_hyperlink = Common.findElement("xpath", "(//a[@class='m-icon-card__link'])[" +i +"]").getAttribute("href");
        	System.out.println(Rightrail_hyperlink);
        	Common.assertionCheckwithReport(Rightrail_hyperlink.contains(link[i-1]), "verify the chatnow , telephone , email address links  in We are here for you section", 
					"chatnow , telephone , email address links should present in We are here for you section", 
					"chatnow , telephone , email address links successfully present in We are here for you section", 
					"failed to present chatnow , telephone , email address links in We are here for you section");	
        		
	}
        Common.clickElement("xpath","//span[contains(text(),'Chat Now')]");
        Thread.sleep(5000);
        	Common.switchFrames("id", "kustomer-ui-sdk-iframe");
        Sync.waitElementVisible(30, "xpath", "//div[@class='widget__widget___uExal']");
        int chatbotvisible = Common.findElements("xpath", "//div[@class='widget__widget___uExal']").size();
        System.out.println(chatbotvisible);
        Common.assertionCheckwithReport(chatbotvisible>0, "To validate chatnow link", 
				"chatbot should open after clicking on chat now link", 
				"chatbot successfully open after clicking on chat now link", 
				"failed to open chatbot after clicking on chat now link");
        Common.switchToDefault();
        }
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verify the chatnow , telephone , email address links  in We are here for you section", 
				"chatnow , telephone , email address links should present in We are here for you section", 
				
				"failed to present chatnow , telephone , email address links in We are here for you section",
				
		
				Common.getscreenShotPathforReport("failed to present chatnow , telephone , email address links in We are here for you section"));

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
			String errorTexts = Common.findElement("xpath", "//div[contains(@class,'error')]").getText();

			Common.assertionCheckwithReport(errorTexts.contains("Please complete your payment details."), "validating the credit card information with valid data",
					expectedResult, "Filled the Card details", "missing field data its showing error");
	
			
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
			String errorTexts = Common.findElement("xpath", "//div[contains(@class,'error')]").getText();
			System.out.println(errorTexts);
			Common.assertionCheckwithReport(errorTexts.contains("Please complete your payment details"), "validating the credit card information with valid data",
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
public void My_Orders() {
	try {
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "(//a[contains(text(),'My Orders')])[3]");
		Common.clickElement("xpath", "(//a[contains(text(),'My Orders')])[3]");
		String MyOrders = Common.getText("xpath", "//h1[text()='My Orders']");
		System.out.println(MyOrders);
		Common.assertionCheckwithReport(MyOrders.contains("My Orders"), "To validate MyOrders in my account page",
				 "click on myOrders in my account page", "Successfully navigate to MyOrders page");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To validate MyOrders in my account page", "click on My Orders in my account page",
				"unable to navigate MyOrders page", Common.getscreenShot("Failed to open MyOrders page"));
		Assert.fail();

	}
	
}
public void My_Order_list() {
	try {
	My_Orders();
	Thread.sleep(4000);
	
	Common.actionsKeyPress(Keys.PAGE_DOWN);
    Common.actionsKeyPress(Keys.ARROW_UP);
  
	
		Common.scrollIntoView("xpath", "//div[@class='m-pagination']");
	//	Common.findElement("xpath", "//div[@class='m-pagination']");
		
		Common.clickElement("xpath", "//a[@class='m-pagination__label']");
		Common.actionsKeyPress(Keys.PAGE_DOWN);
        Common.actionsKeyPress(Keys.ARROW_UP);
		Common.clickElement("xpath", "//span[@aria-label='Page 1']");
		
		
		String titles=Common.getText("xpath", "//h1[@class='page-title-wrapper h2']");
		Thread.sleep(5000);
		
		Common.assertionCheckwithReport(titles.equals("My Orders"),"Verifying carosel page","it shoud navigate to  Carosel page", "user click on Carosel first page", 
				"Successfully user navigated to carosel first page");
		Common.getscreenShotPathforReport("succes to navigate to page");
		
		
		} catch (Exception | Error e) {
			 e.printStackTrace();
		ExtenantReportUtils.addFailedLog("Verifying carosel page", "user sucessfully click on Carosel first page", "user navigated to carosel first page",
				Common.getscreenShotPathforReport("failed to navigate to page"));

		Assert.fail();
		}
	
	try {
		Thread.sleep(4000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.clickElement("xpath", "//span[text()='Next page']");
		
		Common.actionsKeyPress(Keys.PAGE_DOWN);
        Common.actionsKeyPress(Keys.ARROW_UP);
		
	    Sync.waitElementPresent("xpath", "//a[@title='Previous page']");
		Common.clickElement("xpath", "//a[@title='Previous page']");
		
		String page = Common.getText("xpath", "//span[text()='Previous page']");
        System.out.println(page);
		
        Common.assertionCheckwithReport(page.contains("page"), "verifying carosel previous page ",
				 "user successfully select carosel previous page", "user successfully navigate to carosel previous page",
				"faield to open carosel previous page");
				
	}

	catch (Exception | Error e) {
		 e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying carosel previous page ",
				 "user successfully navigate to carosel previous page", "user faield to navigate  carousel previous page",
				Common.getscreenShotPathforReport("Failed to open carousel previous page"));
				
		Assert.fail();

	}}

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

public void createAccountFromOrderSummaryPage(String Dataset) {
	// TODO Auto-generated method stub
	try
	{
		String coffee=Common.findElement("xpath", "//span[text()='Coffee & Beverage']//parent::a").getAttribute("href");
		String kitchen=Common.findElement("xpath", "//span[text()='Kitchenware']//parent::a").getAttribute("href");
		Common.clickElement("xpath", "//input[@name='password']");
		Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
		Common.clickElement("xpath", "(//span[@class='sr-only'])[2]");
		Sync.waitElementPresent(30, "xpath", "//input[@name='password_confirmation']");
		Common.clickElement("xpath", "//input[@name='password_confirmation']");
		Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
				data.get(Dataset).get("Confirm Password"));
		Common.clickElement("xpath", "(//span[@class='sr-only'])[3]");
		String accounttext=Common.findElement("xpath", "//div[@data-appearance='full-bleed']//p").getText();
		String confirmpassword=Common.findElement("xpath", "//input[@name='password_confirmation']").getAttribute("type");
		String password=Common.findElement("xpath", "//input[@name='password_confirmation']").getAttribute("type");	
		String Message = Common.findElement("id", "validation-classes").getCssValue("color");
		String Greencolor=Color.fromString(Message).asHex();
		String Message1 = Common.findElement("id", "validation-length").getAttribute("class");
		  Common.assertionCheckwithReport(Greencolor.equals("#4d8b40") &&
		  Message1.contains("complete")&&coffee.contains("/shop/coffee-beverage")&&kitchen.
		  contains("/shop/kitchenware")&&confirmpassword.equals("text")&&password.equals("text")&&accounttext.contains("Create an account"),
		  "validating the order confirmation page",
		  "User should able to view all details in the order confirmation page",
		  "Sucessfully all details has been displayed in the order confirmation",
		  "Failed to display all details in the order confirmation page");
		  Sync.waitElementPresent(30, "xpath", "(//span[@class='sr-only'])[2]");
		  Common.clickElement("xpath", "(//span[@class='sr-only'])[2]");
		  Sync.waitElementPresent(30, "xpath", "(//span[@class='sr-only'])[3]");
		  Common.clickElement("xpath", "(//span[@class='sr-only'])[3]");
		  String confirmpassword1=Common.findElement("xpath", "//input[@name='password_confirmation']").getAttribute("type");		
			String password1=Common.findElement("xpath", "//input[@name='password_confirmation']").getAttribute("type");		
		  Sync.waitElementPresent("xpath", "//label[@for='is_subscribed']");
		  Common.clickElement("xpath", "//label[@for='is_subscribed']");
		Common.findElement("xpath", "//label[@for='is_subscribed']").isSelected();
		  Common.assertionCheckwithReport(confirmpassword1.equals("password")&&password1.equals("password"),
				  "validating the password field changed to dots",
				  "After clicking on the eye icon it should be change to dots",
				  "Sucessfully passwords has been changed to dots after clicking on eye icon",
				  "Failed change passwords into dots after clicking on eye icon"); 
		  
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

}