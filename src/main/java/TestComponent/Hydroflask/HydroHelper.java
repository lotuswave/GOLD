package TestComponent.Hydroflask;

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

public class HydroHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public HydroHelper(String datafile) {
		excelData = new ExcelReader(datafile);
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("Hydro");
			report.createTestcase("HydroTestCases");
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
					Common.getText("xpath", "//h1[@class='page-title-wrapper']").equals("Customer Login"),
					"To validate the signIn-buton",
					"Validate the signIn-button", "Successfully click singIn button",
					"User unabel to click singup button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validate the signIn-button ", "User navigating signin page",
					"Successfully click singIn button ",
					Common.getscreenShotPathforReport("User unabel to click singup button"));
			Assert.fail();
		}
	}

	public void validateCreateAccountpageNavigation() {

		try {
			Sync.waitElementClickable("xpath", "//div[@class='m-account-nav__content']/button");
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']/button");
			Sync.waitPageLoad();

			Common.javascriptclickElement("xpath", "//ul[@class='m-account-nav__links']/li[2]/a");
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Create New Customer Account"),
					"Validating Create New Customer Account page navigation",
					"after clinking Create New Customer Account page it will navigate account creation page",
					"Successfully navigate to create account page", "Failed to navigate account create page ");
		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating Create New Customer Account page navigation ",
					"after clinking Create New Customer Account page it will navigate account creation page",
					"Successfully click singIn button ",
					Common.getscreenShotPathforReport("Failed to navigate account create page"));
			Assert.fail();
		}
	}

	public void validatingTrackmyOrderNavigation() {
		try {

			Sync.waitElementClickable("xpath", "//div[@class='m-account-nav__content']/button");
			Common.clickElement("xpath", "//div[@class='m-account-nav__content']/button");
			Common.javascriptclickElement("xpath", "//ul[@class='m-account-nav__links']/li[3]/a");
			Sync.waitPageLoad();
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Orders and Returns"),
					"Validating Orders and Returns page navigation",
					"after clinking Orders and Returns page it will navigate order retun page",
					"Successfully navigate to order retuns page", "Failed to navigate order retun page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating Create New Customer Account page navigation ",
					"after clinking Create New Customer Account page it will navigate account creation page",
					"Successfully click singIn button ",
					Common.getscreenShotPathforReport("Failed to navigate account create page"));
			Assert.fail();
		}

	}

	public void validate_accountoptions() {

		WebElement account = Common.findElement("xpath", "//div[@class='m-account-nav__content']/button");
		account.click();
		List<WebElement> accountoptions = Common.findElements("xpath", "//ul[@class='m-account-nav__links']/li/a");

		ArrayList<String> names = new ArrayList<String>();
		for (int i = 0; i < accountoptions.size(); i++) {
			names.add(accountoptions.get(i).getText());
		}

		for (int i = 0; i < names.size(); i++) {
			Sync.waitElementClickable("xpath", "//div[@class='m-account-nav__content']/button");

			Common.javascriptclickElement("xpath", "//div[@class='m-account-nav__content']/button");
			if (names.get(i).equals("Sign In")) {

				Common.javascriptclickElement("xpath", "//ul[@class='m-account-nav__links']/li[1]/a");
				Sync.waitPageLoad();
				Common.assertionCheckwithReport(Common.getPageTitle().contains("Customer Login"),
						"Validating sign In page navigation", "after clinking sigin in page it will navigate loginpage",
						"Successfully navigate to signIn page", "Failed to navigate login page ");
			}

			else if (names.get(i).equals("CREATE AN ACCOUNT")) {
				Sync.waitElementClickable("xpath", "//ul[@class='m-account-nav__links']/li[1]/a");
				Common.javascriptclickElement("xpath", "//ul[@class='m-account-nav__links']/li[2]/a");
				Sync.waitPageLoad();
				Common.assertionCheckwithReport(Common.getPageTitle().contains("Create New Customer Account"),
						"Validating Create New Customer Account page navigation",
						"after clinking Create New Customer Account page it will navigate account creation page",
						"Successfully navigate to create account page", "Failed to navigate account create page ");
			}

			else if (names.get(i).equals("TRACK MY ORDER")) {
				Sync.waitElementClickable("xpath", "//ul[@class='m-account-nav__links']/li[1]/a");
				Common.javascriptclickElement("xpath", "//ul[@class='m-account-nav__links']/li[3]/a");
				Sync.waitPageLoad();

				Common.assertionCheckwithReport(Common.getPageTitle().contains("Orders and Returns"),
						"Validating Orders and Returns page navigation",
						"after clinking Orders and Returns page it will navigate order retun page",
						"Successfully navigate to order retuns page", "Failed to navigate order retun page");
			}

		}

	}

	public void login_Hydroflask() {

		try {
			Sync.waitPageLoad();
			Common.textBoxInput("id", "email", "qatesting.lotuswave+1@gmail.com");
			Common.textBoxInput("id", "pass", "Lotuswave@123");
			Common.clickElement("xpath", "//button[@id='send2']");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Validating_search() {
		// TODO Auto-generated method stub
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
			report.addFailedLog("Validate the User searches using the search field ",
					"Clicking on search bar it should expand and click on close button",
					" sucessfully clicks on close button ",
					Common.getscreenShotPathforReport("User unable to close the search button"));

			Assert.fail();
		}

	}

	public void minicart() {
		try {
			Sync.waitPageLoad();
			Common.javascriptclickElement("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
			String openminicart = Common.findElement("xpath", "//div[@data-block='minicart']").getAttribute("class");

			Common.assertionCheckwithReport(openminicart.contains("active"),"To validate the minicart popup" ,"the mini cart is displayed",
					"Should dislay the mini cart", "mini cart is not displayed");

			Sync.waitElementPresent(30, "xpath", "//span[contains(@class,'minicart__close')]");
			Common.javascriptclickElement("xpath", "//span[contains(@class,'minicart__close')]");

			Common.isElementNotDisplayed("xpath", "//div[contains(@class,'mini-cart--active')]");
			int closeminicart = Common.findElements("xpath", "//div[contains(@class,'mini-cart--active')]").size();
			System.out.println(closeminicart);
			Common.assertionCheckwithReport(closeminicart <= 0, "To verify the mini cart is closed or not",
					"the mini cart is closed", "Should close the mini cart", "mini cart is not Closed");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();

		}
	}

	public void Invalid_email_newsletter(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			//Common.scrollToElementAndClick("xpath", "//input[@id='newsletter-signup_email']");
			Common.actionsKeyPress(Keys.END);
			Sync.waitElementClickable(30, "xpath", "//input[@id='newsletter-signup_email']");
			Common.textBoxInput("xpath", "//input[@id='newsletter-signup_email']", data.get(Dataset).get("Email"));
			Common.clickElement("xpath", "//div[contains(@class,'m-n')]//button[@type='submit']");
			String Errormessage = Common.findElement("xpath", "//div[@class='newsletter-error']").getText();
			System.out.println(Errormessage);
			Common.assertionCheckwithReport(Errormessage.equals("Error: Please enter a valid email address."),
					"To validate the error message for Invalid Email",
					"Should display error Please enter a valid email address.",
					Errormessage, "Failed to display the error message for invaild email");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("To validate the error message for Invalid Email", "Should display error Please enter a valid email address.",
					"Failed to display the error message",
					Common.getscreenShotPathforReport("User unable to see an error message"));

			Assert.fail();

		}
	}

	public void Empty_Email() {
		try {
			
			Common.textBoxInputClear("xpath", "//input[@id='newsletter-signup_email']");
			String Errormessage = Common.findElement("xpath", "//div[@class='newsletter-error']").getText();
			System.out.println(Errormessage);
			Common.assertionCheckwithReport(Errormessage.equals("Error: Please enter a valid email address."),
					"To validate the error message for missing email fields",
					"Should display Error Please enter a valid email address.",
					Errormessage, "Error message dispaly unsuccessfull");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validate the Error message ", "Should display Error: Please enter a valid email address.",
					"Failed to dispaly the Error message ",
					Common.getscreenShotPathforReport("User unable to see an error message"));
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

}
