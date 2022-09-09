package TestComponent.Hydroflask;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

public class HydroHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public HydroHelper(String datafile, String sheetname) {

		excelData = new ExcelReader(datafile, sheetname);
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
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Home Page"), "validating store logo",
					"System directs the user back to the Homepage", "Sucessfully user back to home page",
					"faield to get back to homepage");
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating store logo", "System directs the user back to the Homepage",
					"unable to navigate to home page",
					Common.getscreenShotPathforReport("failed to get back to homepage"));
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

	public void Validate_Myaccountoptions(String string) {

		try {
			WebElement account = Common.findElement("xpath", "//div[@class='m-account-nav__welcome']/button/span");
			account.click();
			List<WebElement> Myaccountoptions = Common.findElements("xpath",
					"//ul[@class='m-account-nav__links']/li/a");

			ArrayList<String> names = new ArrayList<String>();
			for (int i = 0; i < Myaccountoptions.size(); i++) {
				names.add(Myaccountoptions.get(i).getText());
				System.out.println(names);
			}

			for (int i = 0; i < names.size(); i++) {
				Sync.waitElementPresent("xpath", "//div[@class='m-account-nav__welcome']");

				Common.javascriptclickElement("xpath", "//div[@class='m-account-nav__welcome']");
				if (names.get(i).equals("MY ACCOUNT")) {

					Common.javascriptclickElement("xpath",
							"//ul[@class='m-account-nav__links']//a[text()='My Account']");
					Sync.waitPageLoad();
					Sync.waitElementVisible(30, "xpath", "//h1[@class='page-title-wrapper h2']");
					Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"),
							"Validating the My account page navigation when user clicks on the My account CTA",
							"After clicking My account CTA it will navigates My Account page",
							"Successfully navigate to My Account page after clciking on the My account CTA",
							"Failed to navigate to my account page after clicking on the My account CTA");
				}

				else if (names.get(i).contains("MY FAVORITES")) {
					Sync.waitElementClickable("xpath", "//div[@class='m-account-nav__welcome']");
					Common.javascriptclickElement("xpath", "//a[text()='My Favorites']");
					Sync.waitPageLoad();
					Sync.waitElementVisible(30, "xpath", "//h1[@class='page-title-wrapper h2']");
					Common.assertionCheckwithReport(Common.getPageTitle().contains("My Favorites"),
							"Validating My favourites page navigation when user clicks on the My favorites",
							"After clicking My favourites CTA it should navigate My favourites page",
							"Successfully navigate to My favourites page after clicking My favourites CTA ",
							"Failed to navigate My favourites page After clicking My favourites CTA ");
				}

				else if (names.get(i).contains("MY ORDERS")) {
					Sync.waitElementClickable("xpath", "//div[@class='m-account-nav__welcome']");
					Common.javascriptclickElement("xpath", "//a[text()='My Orders']");
					Sync.waitPageLoad();
					Sync.waitElementVisible(30, "xpath", "//h1[@class='page-title-wrapper h2']");

					Common.assertionCheckwithReport(Common.getPageTitle().contains("My Orders"),
							"Validating MyOrders navigation after clicking on the My Orders CTA",
							"After clicking MyOrders CTA it should navigate MyOrders page",
							"Successfully navigate to MyOrderspage after clicking on the My Orders CTA",
							"Failed to navigate MyOrders page after clicking on the My Orders CTA");
				}
				/*
				 * else if (names.get(i).contains("MY PRODUCTS SUBSCRIPTION")) {
				 * Sync.waitElementClickable("xpath",
				 * "//ul[@class='m-account-nav__links']/li[1]/a");
				 * Common.javascriptclickElement("xpath",
				 * "//ul[@class='m-account-nav__links']/li[4]/a"); Sync.waitPageLoad();
				 * Sync.waitElementVisible(30, "xpath", "//h1[@class='page-title-wrapper h2']");
				 * 
				 * Common.assertionCheckwithReport(Common.getPageTitle().
				 * contains("My Subscriptions"),
				 * "Validating My products subscriptions navigation after clicking on the My subscriptions CTA  "
				 * ,
				 * "After clicking My products subscriptions CTA it should navigate My products subscriptions page"
				 * ,
				 * "Successfully navigate to My products subscriptions page after clicking on the My subscriptions CTA"
				 * ,
				 * "Failed to navigate My products subscriptions page after clicking on the My subscriptions CTA"
				 * ); }
				 */
				else if (names.get(i).contains("SIGN OUT")) {
					Sync.waitElementClickable("xpath", "//div[@class='m-account-nav__welcome']");
					Sync.waitElementPresent(30, "xpath", "//ul[@class='m-account-nav__links']/li[4]/a");
					Common.javascriptclickElement("xpath", "//ul[@class='m-account-nav__links']/li[4]/a");
					// Thread.sleep(6000);
					Sync.waitPageLoad();
					Sync.waitElementVisible("xpath", "//div[contains(@class,'c-hero-block')]");
					System.out.println(Common.getPageTitle());

					Common.assertionCheckwithReport(Common.getPageTitle().contains("Home Page"),
							"Validating Customer logout functionality when user click on the Sign out CTA",
							"After Clicking SignOut CTA it should successfully logout the customer ",
							"Successfully logout from the Account after clicking on the Sign out CTA",
							"Failed to logout from the customer account after clicking on the Sign out CTA");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating options under the My account CTA ",
					"All options should able to click under My account CTA and it should navigate to respective page",
					"unable to click on the options under My account CTA and not able to navigate to the respective page",
					Common.getscreenShotPathforReport("Failed to click options under My account CTA"));

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
					"unable to click on singIn button",
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
			ExtenantReportUtils.addFailedLog("Validating Create New Customer Account page navigation ",
					"after clinking Create New Customer Account page it will navigate account creation page",
					"unable to click on singIn button",
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
			Common.assertionCheckwithReport(open.contains("active"),
					"To validate the global search box is opened when we click on the search icon",
					"User should able to click on the search icon and search box opens",
					"Sucessfully the gobal search box opend when user clicks on search icon",
					"Failed to open the search box when user clicks on the search icon");
			Sync.waitElementPresent("xpath", "//span[contains(@class,'icon-header__s')]");
			Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
			String close = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
			Common.assertionCheckwithReport(close.contains("c-header-search"),
					"To validate the global search box is Closed when user click on the close icon",
					"User should able to click on the close icon and search box should be closed",
					"Sucessfully the gobal search box closed when user clicks on close icon",
					"Failed to close the search box when user clicks on the close icon");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate the global search box is Closed when user click on the close icon",
					"User should able to click on the close icon and search box should be closed",
					"Unable to close the gobal search box when user clicks on close icon",
					Common.getscreenShotPathforReport(
							"Failed to close the search box when user clicks on the close icon"));

			Assert.fail();
		}

	}

	public void minicart() {
		try {
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
			Common.mouseOverClick("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
			String openminicart = Common.findElement("xpath", "//div[@data-block='minicart']").getAttribute("class");
			System.out.println(openminicart);
			Common.assertionCheckwithReport(openminicart.contains("active"),
					"To validate the Minicart popup is open when user click on Mini cart icon",
					"It Should display the Mini cart when user clicks on the Mini cart icon  ",
					"Sucessfully Minicart is displayed when user clicks on the Mini cart icon",
					"Failed to display the mini cart when we click on the Mini cart icon");

			Sync.waitElementPresent(30, "xpath", "//span[contains(@class,'minicart__close')]");
			Common.javascriptclickElement("xpath", "//span[contains(@class,'minicart__close')]");
			Thread.sleep(3000);
			Common.isElementNotDisplayed("xpath", "//div[contains(@class,'mini-cart--active')]");
			int closeminicart = Common.findElements("xpath", "//div[contains(@class,'mini-cart--active')]").size();
			System.out.println(closeminicart);
			Common.assertionCheckwithReport(closeminicart <= 0,
					"To verify the mini cart is closed or not when user clicked on Close icon",
					"Mini cart should be closed when user clicks on the Close icon",
					"Sucessfully Minicart is closed when user clicks on the close icon",
					"Failed to closed the Mini cart when user clicks on the Close icon");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To verify the mini cart is closed or not when user clicked on Close icon",
					"Mini cart should be closed when user clicks on the Close icon",
					"Unable to Close the Minicart when user clicks on the close icon",
					Common.getscreenShotPathforReport(
							"Failed to closed the Mini cart when user clicks on the Close icon"));
			Assert.fail();

		}
	}

	public void Invalid_email_newsletter(String Dataset) {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.END);
			Sync.waitElementClickable(30, "xpath", "//input[@id='newsletter-signup_email']");
			Common.textBoxInput("xpath", "//input[@id='newsletter-signup_email']", data.get(Dataset).get("Email"));
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
			String Errormessage = Common.findElement("xpath", "//div[@class='newsletter-error']").getText();
			System.out.println(Errormessage);
			Common.assertionCheckwithReport(Errormessage.equals("Error: Please enter a valid email address."),
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
					"Should dispaly the error message " + errormessage, emailerror + "error message is displayed",
					"Failed to display error message");
			clickStoreLogo();
			Sync.waitPageLoad();
		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the error message when given invalid email ",
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
					"Unable to display the required fields Error message",
					Common.getscreenShotPathforReport("Failed to display the error message"));
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
					"To validate the Main menu in Header and logo",
					"Components in header menu should be displayed along with logo",
					"Componenets in header menu and logo are displayed ",
					"Failed to display the components and logo in header menu ");

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

				Common.assertionCheckwithReport(link.contains(headerlink[i - 1]), "To validate the header menu " + link,
						"It should click the Header menu link " + link, link + " is clicked on the Header menu",
						"Failed to click the " + link);
			}

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the header menu for Authorised user ",
					" It should click the Header links and navigate to the My account menu",
					"Top level navigation unsuccessfull", "Failed to navigate through the header menu");
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
			ExtenantReportUtils.addFailedLog("Validate the See Details link",
					"User Opens the Free Ground Shipping Pop up", "User unable to display Free Ground Shipping Pop up",
					Common.getscreenShotPathforReport("Failed to display Free Ground Shipping Pop up"));

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
					"User successfully open Country Selector Pop up", "User unable to open country Selector Pop up",
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

	public void verifingHomePage() {
		try {
			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Home Page"),
					"validating store logo", "System directs the user to the Homepage",
					"Sucessfully user navigates to the home page", "Failed to navigate to the homepage");
		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating store logo", "System directs the user to the Homepage",
					" user unable navigates to the home page", "Failed to navigate to the homepage");
			Assert.fail();
		}

	}

	public void UserViewsContactUsPageandSubmits(String DataSet) throws Exception {
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
			Sync.waitElementPresent(40, "xpath", "//iframe[contains(@src,'https://hydroflask')]");
			Common.switchFrames("xpath", "//iframe[contains(@src,'https://hydroflask')]");

			Sync.waitElementPresent("xpath", "//input[@id='customerEmail']");
			Common.textBoxInput("xpath", "//input[@id='customerEmail']", data.get(DataSet).get("Email"));

			Sync.waitElementPresent("xpath", "//input[@id='messageSubject']");
			Common.textBoxInput("xpath", "//input[@id='messageSubject']", data.get(DataSet).get("FirstName"));

			Sync.waitElementPresent("xpath", "//input[@id='conversationLastName']");
			Common.textBoxInput("xpath", "//input[@id='conversationLastName']", data.get(DataSet).get("LastName"));

			Sync.waitElementPresent("xpath", "//input[@name='conversationCompany']");
			Common.textBoxInput("xpath", "//input[@name='conversationCompany']", data.get(DataSet).get("Company"));

			Sync.waitElementPresent("xpath", "//input[@id='conversationPrimary']");
			Common.textBoxInput("xpath", "//input[@id='conversationPrimary']", data.get(DataSet).get("phone"));

			Sync.waitElementPresent("xpath", "//input[@name='conversationStreet']");
			Common.textBoxInput("xpath", "//input[@name='conversationStreet']", data.get(DataSet).get("Street"));

			Sync.waitElementPresent("xpath", "//input[@name='conversationCity']");
			Common.textBoxInput("xpath", "//input[@name='conversationCity']", data.get(DataSet).get("City"));

			Sync.waitElementPresent("xpath", "//input[@name='conversationCountry']");
			Common.clickElement("xpath", "//input[@name='conversationCountry']");

			Sync.waitElementPresent("xpath", "//div[text()='United States']");
			Common.clickElement("xpath", "//div[text()='United States']");

			Sync.waitElementPresent("xpath", "//input[@name='conversationState']");
			Common.clickElement("xpath", "//input[@name='conversationState']");

			Sync.waitElementPresent("xpath", "//div[text()='Alabama']");
			Common.clickElement("xpath", "//div[text()='Alabama']");

			Sync.waitElementPresent("xpath", "//input[@name='conversationZipCode']");
			Common.textBoxInput("xpath", "//input[@name='conversationZipCode']", data.get(DataSet).get("postcode"));

			Sync.waitElementPresent("xpath", "//div[@id='conversationHowCanWeHelp']");
			Common.clickElement("xpath", "//div[@id='conversationHowCanWeHelp']");

			Common.clickElement("xpath", "//div[text()='Order Issues']");

			Sync.waitElementPresent("xpath", "//div[text()='Billing Issue']");
			// Common.clickElement("xpath", "//div[@id='selectACategory']");
			Common.clickElement("xpath", "//div[text()='Billing Issue']");

			Sync.waitElementPresent("xpath", "//textarea[@id='messagePreview']");
			Common.textBoxInput("xpath", "//textarea[@id='messagePreview']",
					data.get(DataSet).get("CommetsHydroflask"));

			Common.scrollIntoView("xpath", "//button[text()='Submit']");
			Common.clickElement("xpath", "//button[text()='Submit']");

			Sync.waitElementPresent("xpath", "//div[@class='form-wrap']");
			int Contactussuccessmessage = Common.findElements("xpath", "//div[@class='form-wrap']").size();
			Common.assertionCheckwithReport(Contactussuccessmessage > 0, "verifying Contact us Success message ",
					"Success message should be Displayed", "Contact us Success message displayed ",
					"failed to dispaly success message");
			ExtenantReportUtils.addPassLog("New Contact us", "Contact us Should be successfully submission",
					"Contact us Successfully", Common.getscreenShotPathforReport("Contact us"));

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating  of Contact us Page", "Expected text should not be obtained",
					"Expected text is not obtained", "Link Validation Contact us");
			AssertJUnit.fail();
		}
	}

	public void Validate_Newsletter_Footer(String DataSet) {
		try {

			Common.scrollIntoView("id", "newsletter-signup_email");
			int newsletter = Common.findElements("id", "newsletter-signup_email").size();
			Common.assertionCheckwithReport(newsletter > 0, "To validate the Footer newsletter is available ",
					"The user should see the Newsletter signup in the footer section", "Footer newsletter is available",
					"Failed to locate the Footer Newsletter");
			// String email = Common.genrateRandomEmail(data.get(DataSet).get("Email"));
			Common.textBoxInput("id", "newsletter-signup_email", Utils.getEmailid());
			Common.mouseOverClick("xpath", "//button[contains(@class,'signup__submit-btn')]");
			Sync.waitPageLoad(30);
			Sync.waitElementPresent("xpath", "//div[contains(@class,' a-message--success')]/div");
			String successmessage = Common.findElement("xpath", "//div[contains(@class,' a-message--success')]/div")
					.getText();
			Common.assertionCheckwithReport(successmessage.contains("Thank you for your subscription."),
					"To validate the Footer newsletter subscription is sucessfull ",
					"The user should successfully subscribe to newsletter    ", "Newsletter subscription successfull",
					"Footer newsletter subscription failed");

		} catch (Exception e) {

			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Footer newsletter subscription",
					"Footer newsletter subscription should be successfull", "Failed footer newsletter subscription ",
					Common.getscreenShot("Failed footer newsletter subscription"));

			Assert.fail();
		}
	}

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
			System.out.println(welcomemsg);

			Common.assertionCheckwithReport(chat.contains("Chat") || welcomemsg.contains("Welcome to Hydro Flask"),
					"validate the Chat display", "Open the Chat conversation in ChatBot",
					"Sucessfully click on the ChatBot and display the Chat conversation ",
					"Unable to dispaly the chat conversation");

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

//		Common.clickElement("xpath", "//button[@aria-label='minimize chat widget']");

			Common.switchToDefault();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validate the ChatBot", "Open the ChatBot", "Unable click on the ChatBot",
					Common.getscreenShotPathforReport("failed to click on the ChatBot"));
			Assert.fail();
		}

	}

	public void verifychatbot(String Dataset) {
		// TODO Auto-generated method stub

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

	public void verifingContactUSErrorMessage() {
		try {
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.END);
			Common.clickElement("xpath", "//a[text()='Contact Us']");

			Sync.waitElementPresent(40, "xpath", "//iframe[contains(@src,'https://hydroflask')]");

			Common.switchFrames("xpath", "//iframe[contains(@src,'https://hydroflask')]");

			Common.scrollIntoView("xpath", "//button[text()='Submit']");
			Common.clickElement("xpath", "//button[text()='Submit']");

			String errorpopup = Common.findElement("xpath", "//script[@id='initial-data']").getAttribute("data-json");
			System.out.println(errorpopup);

			Common.assertionCheckwithReport(
					errorpopup.contains("Please fill out this field") && Common.getPageTitle().equals("Contact"),
					"validating contactus error message and contact us page",
					"user navigates to contactus page user able to see error message",
					"Sucessfully user navigate to contact us page and able to seeerror message",
					"faield to navigate to contactus page and unable to see error message");

//		Common.assertionCheckwithReport(errorpopup.equals("display)),"vlaidating the pop up message after submittion","After submit button user able see the error popup","Sucessfully popup message has been diplayed after subit button","Failed to get error message after click on submit button");

			Common.switchToDefault();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating store logo", "System directs the user back to the Homepage",
					"Sucessfully user back to home page",
					Common.getscreenShotPathforReport("failed to get back to homepage"));
			Assert.fail();
		}
	}

	public void validate_Homepage() {
		try {
			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().contains("Home Page"),
					"validating store logo", "System directs the user back to the Homepage",
					"Sucessfully user back to home page", "faield to get back to homepage");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating store logo", "System directs the user back to the Homepage",
					"Sucessfully user back to home page",
					Common.getscreenShotPathforReport("faield to get back to homepage"));
			Assert.fail();
		}

	}

	public void headerlinks(String category) {
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

	public void minicart_freeshipping(String Dataset) {
		// TODO Auto-generated method stub
		String products = data.get(Dataset).get("Products");
		String product1 = data.get(Dataset).get("ProductQuantity");
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
			Common.mouseOver("xpath", "//img[@alt='" + products + "']");
			Sync.waitElementPresent("xpath", "//span[text()='Add to Bag']");
			Common.clickElement("xpath", "//span[text()='Add to Bag']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
					.getAttribute("data-ui-id");
			Common.assertionCheckwithReport(message.contains("success"), "validating the  product add to the cart",
					"Product should be add to cart", "Sucessfully product added to the cart ",
					"failed to add product to the cart");
//			Common.mouseOver("xpath", "//img[@alt='" + products + "']");
//			Sync.waitElementPresent("xpath", "//span[text()='Add to Bag']");
//			Common.clickElement("xpath", "//span[text()='Add to Bag']");
//			Sync.waitPageLoad();
//			Thread.sleep(4000);
//			String message1 = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
//					.getAttribute("data-ui-id");
//			Common.assertionCheckwithReport(message1.contains("success"), "validating the  product add to the cart",
//					"Product should be add to cart", "Sucessfully product added to the cart ",
//					"failed to add product to the cart");
			click_minicart();
			String shipping = Common.findElement("xpath", "//div[contains(@class,'label-')]").getText();
			if (shipping.contains("left for Free Shipping.")) {
				Sync.waitElementPresent(30, "xpath", "//span[contains(@class,'minicart__close')]");
				Common.javascriptclickElement("xpath", "//span[contains(@class,'minicart__close')]");
				shop_bottle("Bottles & Drinkware");
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//img[@alt='" + product1 + "']");
				Common.assertionCheckwithReport(Common.getPageTitle().contains("18 oz Standard Mouth"),
						"validating the product should navigate to the PDP page",
						"When we click on the product is should navigate to the PDP page",
						"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");
				Sync.waitPageLoad();
				Sync.waitElementPresent("xpath", "//div[@aria-label='" + productcolor + "']");
				Common.clickElement("xpath", "//div[@aria-label='" + productcolor + "']");
				Common.clickElement("xpath", "//button[@title='Add to Bag']");
				Thread.sleep(4000);
				String message2 = Common.findElement("xpath", "//div[@data-ui-id='message-success']")
						.getAttribute("data-ui-id");
				Common.assertionCheckwithReport(message2.contains("success"), "validating the  product add to the cart",
						"Product should be add to cart", "Sucessfully product added to the cart ",
						"failed to add product to the cart");
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
					"unable to apply free shipping for the selected products",
					Common.getscreenShot("Failed to see free shipping"));
			Assert.fail();
		}

	}

	public void click_minicart() {
		try {
			Thread.sleep(8000);
			Common.actionsKeyPress(Keys.ARROW_UP);
			Sync.waitElementPresent("xpath", "//a[contains(@class,'c-mini')]");
			Common.mouseOverClick("xpath", "//a[contains(@class,'c-mini')]");
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

	public void minicart_delete(String Dataset) {
		// TODO Auto-generated method stub
		String deleteproduct = data.get(Dataset).get("Products");
		try {
			Sync.waitElementPresent(30, "xpath",
					"//div[@class='m-mini-product-card']//span[contains(@class,'icon-cart__remove')]");
			Common.clickElement("xpath",
					"//div[@class='m-mini-product-card']//span[contains(@class,'icon-cart__remove')]");
			Sync.waitElementPresent("xpath", "//button[contains(@class,'a-btn a-btn--primary action-p')]//span");
			Common.clickElement("xpath", "//button[contains(@class,'a-btn a-btn--primary action-p')]//span");
			Thread.sleep(4000);
			String subtotal = Common.getText("xpath", "//span[@class='c-mini-cart__subtotal-amount']//span")
					.replace("$", "");
			Float subtotalvalue = Float.parseFloat(subtotal);
			String productname = Common
					.findElement("xpath", "(//div[@class='m-mini-product-card__info']//a[@class='a-product-name'])[1]")
					.getText();
			String productamount1 = Common.getText("xpath", "(//span[@class='minicart-price']//span)[1]").replace("$",
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

		String[] items = data.get(DataSet).get("HydroAnswers").split(",");

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
			Sync.waitElementClickable(30, "xpath", "//button[@aria-label='New Conversation']");
			Common.mouseOverClick("xpath", "//button[@aria-label='New Conversation']");

			Sync.waitElementVisible("xpath", "(//div[contains(@class,'markdownBody')])[1]");
			String welcomemsg = Common.findElement("xpath", "(//div[contains(@class,'markdownBody')])[1]").getText();

			Common.assertionCheckwithReport(chat.contains("Chat") || welcomemsg.contains("Welcome to Hydroflask!"),
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

	public void minicart_update(String Dataset) {
		// TODO Auto-generated method stub
		String quantity = data.get(Dataset).get("ProductQuantity");
		try {

			String Subtotal = Common.getText("xpath", "//span[@class='c-mini-cart__subtotal-amount']//span")
					.replace("$", "");
			Float subtotalvalue = Float.parseFloat(Subtotal);
			Sync.waitElementPresent("xpath", "//select[@class='a-select-menu cart-item-qty']");
			Common.clickElement("xpath", "//select[@class='a-select-menu cart-item-qty']");
			Common.dropdown("xpath", "//select[@class='a-select-menu cart-item-qty']", Common.SelectBy.VALUE,
					data.get(Dataset).get("ProductQuantity"));
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
		// TODO Auto-generated method stub
		try {
			String minicartproduct = Common
					.findElement("xpath", "//div[@class='m-mini-product-card']//a[@class='a-product-name']").getText();
			Common.clickElement("xpath", "//div[@class='m-mini-product-card']//a[@class='a-product-name']");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().contains(minicartproduct),
					"validating the product navigating to the PDP page",
					"The product Should be navigates to the PDP page", "Successfully product navigates to the PDP page",
					"Failed to Navigates Product to the PDP page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the produc navigating to the PDP page",
					"The product Should to the PDP page", "unable to navigate product to the PDP page",
					Common.getscreenShot("Failed to Navigates the product to the PDP page"));
			Assert.fail();
		}

	}

	public void minicart_product_close() {
		// TODO Auto-generated method stub
		try {
			click_minicart();
			Common.clickElement("xpath", "//span[contains(@class,'icon-cart__r')]");
			Sync.waitElementPresent("xpath", "//aside[@class='modal-popup confirm _show']");
			String minicartpopup = Common.findElement("xpath", "//aside[@class='modal-popup confirm _show']")
					.getAttribute("class");
			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Common.assertionCheckwithReport(minicartpopup.contains("_show"),
					"validating the popup when you click on delete", "The Popup should be displayed",
					"Successfully popup is displayed when we click on the delete button",
					"Failed to Display the popup");
			String popup = Common.findElement("xpath", "//h1[@data-role='title']").getText();
			if (popup.equals("Remove Item")) {
				Common.clickElement("xpath", "//button[contains(@class,'a-btn a-btn--secondary acti')]");
			} else {
				Assert.fail();
			}
			Common.clickElement("xpath", "//span[contains(@class,'icon-cart__r')]");
			Sync.waitElementPresent("xpath", "//aside[@class='modal-popup confirm _show']");
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

	public void verifypromobanner() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "(//div[@class='m-promo-banner__container'])[1]").size();
			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().equals("Home Page"),
					"validating CMS promobanner", "System directs the CMS promobanner",
					"Sucessfully directs the cms promobanner ", "Failed to get CMS promobanner");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating CMS promobanner", "System directs the CMS promobanner",
					"Unable to  directs the cms promobanner ", Common.getscreenShot("Failed to get CMS promobanne"));
			Assert.fail();
		}
	}

	public void CMSpromobanner() {
		// TODO Auto-generated method stub

		try {
			Sync.waitElementPresent("xpath", "(//div[@class='slick-initialized slick-slider']//a//span)[1]");
			String message1 = Common
					.findElement("xpath", "(//div[@class='slick-initialized slick-slider']//a//span)[1]").getText();
			System.out.println(message1);
			String message3 = Common.findElement("xpath", "(//div[@class='slick-initialized slick-slider']//a)[1]")
					.getAttribute("href");
			System.out.println(message3);
			if (message3.equals("https://mcloud-na-stage.hydroflask.com/#") || message1.equals("Visit Here!")) {
				Common.clickElement("xpath", "//span[text()='Visit Here!']");
				Common.assertionCheckwithReport(
						Common.getText("xpath", "//strong[text()='Free Ground Shipping']")
								.equals("Free Ground Shipping"),
						"To validate the Popup of Free Ground Shipping", "Validate the Pop up of Free Ground Shipping ",
						"Successfully displays Free Ground Shipping Pop up",
						"Failed to  display the Free Ground Shipping Pop up");
			} else {
				Assert.fail();
			}

		} catch (Exception | Error e) {

			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Popup of Free Ground Shipping",
					"Validate the Pop up of Free Ground Shipping ",
					"Unable to displays the Free Ground Shipping Pop up",
					Common.getscreenShot("Failed to  display the Free Ground Shipping Pop up"));

			Assert.fail();

		}
	}

	public void closepromobanner() {
		// TODO Auto-generated method stub

		try {
			Common.clickElement("xpath", "//span[@aria-label='Close']");
			int size = Common.findElements("xpath", "//a[@class='a-logo']").size();
			Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().equals("Home Page"),
					"validating store logo", "System directs the user to the Homepage",
					"Sucessfully user navigate to home page", "Failed to navigate homepage");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating store logo", "System directs the user to the Homepage",
					" user unable to  navigate to the home page", Common.getscreenShot("Failed to navigate homepage"));
			Assert.fail();
		}
	}

	public void minicart_crosssell(String Dataset) {
		// TODO Auto-generated method stub
		String product = data.get(Dataset).get("Products");
		String sku = data.get(Dataset).get("GropName");
		try {
			Sync.waitPageLoad();
			Common.scrollIntoView("xpath", "//img[@alt='" + product + "']");
			Common.clickElement("xpath", "//img[@alt='" + product + "']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals(product),
					"validating the product should navigate to the PDP page",
					"When we click on the product is should navigate to the PDP page",
					"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");
			Sync.waitPageLoad();
//			Common.clickElement("xpath", "//select[@name='options[3]']");
//			Common.dropdown("xpath", "//select[@name='options[3]']", Common.SelectBy.TEXT,
//					data.get(Dataset).get("ProductQuantity"));
			Common.clickElement("xpath", "//button[@title='Add to Bag']");
			click_minicart();
			int minicartscroll = Common.findElements("xpath", "//div[@class='m-product-upsell__item']").size();
			String subtotal = Common.getText("xpath", "//span[@class='c-mini-cart__subtotal-amount']//span")
					.replace("$", "");
			Float subtotalvalue = Float.parseFloat(subtotal);

			Common.clickElement("xpath", "//form[@data-product-sku='" + sku + "']//button");
			Common.scrollIntoView("xpath", "(//div[@class='m-mini-product-card']//span[@class='price'])[1]");
			Thread.sleep(6000);
			String productamount = Common
					.getText("xpath", "(//div[@class='m-mini-product-card']//span[@class='price'])[1]")
					.replace("$", "");
			Float productamountvalue = Float.parseFloat(productamount);
			Float Total = subtotalvalue + productamountvalue;
			String ExpectedTotalAmmount = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			Thread.sleep(4000);
			String subtotal2 = Common.getText("xpath", "//span[@class='c-mini-cart__subtotal-amount']//span")
					.replace("$", "");
			Common.assertionCheckwithReport(subtotal2.equals(ExpectedTotalAmmount),
					"validating the mini cart crosssell, add to cart and subtotal",
					"User should able to see the crosssell in mincart product should be add to cart and subtotal should be change",
					"Sucessfully crosssell dispalyed product added to cart and subtotal changed ",
					"Failed to see the crossell");

			Common.scrollIntoView("xpath", "//span[contains(@class,'minicart__close')]");
			Sync.waitElementPresent(30, "xpath", "//span[contains(@class,'minicart__close')]");
			Common.javascriptclickElement("xpath", "//span[contains(@class,'minicart__close')]");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the mini cart crosssell, add to cart and subtotal",
					"User should able to see the crosssell in mincart product should be add to cart and subtotal should be change",
					" unable to display the crossell and products", Common.getscreenShot("Failed to see the crossell"));
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
			String productsearch = Common.findElement("xpath", "//span[@id='algolia-srp-title']").getText();
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

	public void shop_bottle(String category) {
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
			Common.clickElement("xpath", "//span[text()=' Bottles']");
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

	public void validateaccountcreationPassword(String dataSet) throws Exception {
		try {

			Sync.waitPageLoad();
			Sync.waitElementPresent("id", "firstname");
			Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));
			Common.textBoxInput("id", "email_address", Utils.getEmailid());
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			String classes = Common.findElement("id", "validation-classes").getAttribute("class");
			String textlength = Common.findElement("id", "validation-length").getAttribute("class");
			Common.actionsKeyPress(Keys.PAGE_DOWN);

			Common.assertionCheckwithReport(classes.contains("complete") && textlength.contains("complete"),
					"Password is validated", "password should be validate", "failed to validate password");
			Common.actionsKeyPress(Keys.UP);
			Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));

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
			Thread.sleep(9000);
			// Common.actionsKeyPress(Keys.PAGE_DOWN);

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
					"To Validate the warning message - pasword - forgot password - password in dots",
					"Should display the warning message - pasword - forgot password link- password in dots",
					"successfully displayed warning message  -pasword - forgot password - password in dots",
					"failed to display warning message - pasword - forgot password - password in dots");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verify the  warning message-pasword-forgot password-password in dots",
					"Should display warning message-pasword-forgot password-password in dots",
					"Unable to display warning message-pasword-forgot password-password in dots",
					Common.getscreenShotPathforReport(
							"faield  to display warning message-pasword-forgot password-password in dots"));
			Assert.fail();

		}
	}

	public void Validate_Signin_Checkoutpage() {
		try {

			Common.clickElement("xpath", "//button[contains(@class,'action login primary')]");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			int errormessage = Common.findElements("xpath", "//div[contains(@class,'message-error')]").size();
			if (errormessage <= 0) {

				Sync.waitElementInvisible("id", "customer-email-fieldset");
				System.out.println(Common.getPageTitle());
				int emailsection = Common.findElements("id", "customer-email-fieldset").size();
				System.out.println(emailsection);
				Common.assertionCheckwithReport(Common.getPageTitle().contains("Checkout") && emailsection <= 0,
						"To validate register user is able to login and User is on checkout page",
						"User should be ale to login and is on the Checkout page",
						"Register User login sucessfull and the user is on checkout page ",
						"failed to login in checkout page");

				// Common.assertionCheckwithReport(errormessage > 0, "To validate incorrect
				// password in checkout page",
				// "Error message should be displayed for incorrect password", "Error message
				// displayed",
				// "failed to display error message in checkout page for incorrect password");
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validate user successfully logins from checkout page",
					"User should login from Checkout page", "User failed logged in from checkout page ",
					Common.getscreenShotPathforReport("faield login from checkout page"));
			Assert.fail();

		}
	}

	public void forgotpassword() {
		// TODO Auto-generated method stub
		try {
			String errormessage = Common.findElement("xpath", "//div[text()='Invalid login or password.']").getText();
			System.out.println(errormessage);
			Common.findElement("xpath", "//span[text()='Forgot Your Password?']");
			Common.clickElement("xpath", "//span[text()='Forgot Your Password?']");
			String forgotpassword = Common.findElement("xpath", "//h1[text()='Forgot Your Password?']").getText();
			Common.assertionCheckwithReport(
					errormessage.contains("Invalid login or password.")
							&& forgotpassword.contains("Forgot Your Password?"),
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

	public void Click_Findstore() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.END);
			Common.clickElement("xpath", "//a[text()='Find a Store']");

			String find = Common.findElement("xpath", "//h1[@class='u-container']").getText();
			System.out.println(find);

			Common.assertionCheckwithReport(find.equals("Find a Store"), "validating Find a Store page",
					"user navigates to Find a Store page", "Sucessfully user navigate to Find a Store page",
					"faield to navigate to Find a Store page");

//			Common.assertionCheckwithReport(errorpopup.equals("display)),"vlaidating the pop up message after submittion","After submit button user able see the error popup","Sucessfully popup message has been diplayed after subit button","Failed to get error message after click on submit button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating store logo", "System directs the user back to Find a Store",
					"unable to go back to the Find a Store page",
					Common.getscreenShotPathforReport("faield to get back to Find a Store"));
			Assert.fail();
		}
	}

	public void Click_Instock() {
		// TODO Auto-generated method stub
		try {

			Sync.waitPageLoad();
			Common.switchFrames("xpath", "//iframe[@id='lcly-embedded-iframe-inner-0']");
			Sync.waitElementPresent(40, "xpath", "//a[@id='dealer-navigation-inventory']");
			// Sync.waitElementPresent(20 "xpath",
			// "//a[@id='dealer-navigation-inventory']");
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

			// Common.textBoxInput("xpath", "//input[@class='locally-search
			// location-autocomplete']", data.get(DataSet).get("EnterLocation"));
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating product listing page",
					"System directs the user back to the product listing page",
					"unable user back to product listing page",
					Common.getscreenShotPathforReport("failed to get back product listing page"));
			Assert.fail();
		}
	}

	public void click_Retailer() {
		// TODO Auto-generated method stub
		String store = "DICK'S Sporting Goods  - San Antonio | Curbside Contactless Pickup Available - 5.3 mi";
		try {

			Common.switchFrames("xpath", "//iframe[@id='lcly-embedded-iframe-inner-0']");

			Sync.waitPageLoad();
			Thread.sleep(5000);
			String id = Common.findElement("xpath", "//div[@aria-label=\"" + store + " \"]").getAttribute("id");

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

	public void click_Links() {
		// TODO Auto-generated method stub
		try {

			Sync.waitElementPresent(40, "xpath", "//span[text()='Links']");
			Common.findElement("xpath", "//span[text()='Links']").click();

			int links = Common.findElements("xpath", "//section[@id='conv-store-tab-links']").size();

			// int browser=Common.findElements("xpath","//div[@id='clp-container']").size();
			System.out.println(links);
			Common.assertionCheckwithReport(links > 0, "validating Links page", "user navigates to Links page",
					"Sucessfully user navigate to Links page", "faield to navigate to Links page");

			// Common.textBoxInput("xpath", "//input[@class='locally-search
			// location-autocomplete']", data.get(DataSet).get("EnterLocation"));
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating Links page", "System directs the user back to the Links page",
					"unable to user go back to the Links page",
					Common.getscreenShotPathforReport("failed to get back to Links page"));
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
			// String inStock = Common.findElement("xpath", "//div[text()='In
			// Stock']").getText(); && inStock.equals("In Stock")

			Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
			Sync.waitPageLoad();
			Thread.sleep(3000);
			Common.assertionCheckwithReport(filterSize > 0, "validating browser page",
					"user navigates to Browsers page", "Sucessfully user navigate to browser page",
					"faield to navigate to browser page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating browser page",
					"System directs the user back to the Browser page", "failed user back to the browser page",
					Common.getscreenShotPathforReport("Failed to get back to browser page"));
			Assert.fail();
		}
	}

	public void Validate_store_sidebar() {
		try {
			Common.switchFrames("xpath", "//iframe[contains(@id,'lcly-embedded-iframe')]");
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
		try {
			Common.switchFrames("xpath", "//iframe[contains(@id,'lcly-embedded-iframe')]");
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
			Common.javascriptclickElement("xpath", "//a[contains(@class,'tab-locations')]");

			int storecount = Common.findElements("xpath", "//a[contains(@class,'conv-section-store')]/div/h3").size();
			System.out.println(storecount);
			for (int i = 1; i <= storecount; i++) {

				String relatedstores = Common
						.findElement("xpath", "(//a[contains(@class,'conv-section-store')]/div/h3)[" + i + "]")
						.getText();
				System.out.println(relatedstores);
				Common.assertionCheckwithReport(relatedstores.contains(Storename),
						"To validate the retailer stores displayed ", "Retailer stores should be displayed",
						"Retailer stores are displayed", "Failed to display the retailer stores ");

			}
			Common.switchToDefault();

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating available retailer store locations",
					"retailers store locations should be visible", "Failed to display retailers store locations",
					Common.getscreenShotPathforReport("faield to display retailer store locations"));
			Assert.fail();
		}

	}

	public void Click_Storelocator() {
		try {
			Common.scrollIntoView("xpath", "//a[text()='Find a Store']");
			Common.mouseOverClick("xpath", "//a[text()='Find a Store']");
			Sync.waitPageLoad(30);
			Sync.waitElementVisible("xpath", "//h1[@class='u-container']");
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("storelocator"),
					"To validate the Store locator page", "User should land on store locator page",
					"Successfully landed on store locator page", "Failed to land on store locator page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating storelocator page", "User should land on Store locator page",
					"User lands on the store locator page",
					Common.getscreenShotPathforReport("faield navigation to store locator page"));
			Assert.fail();
		}
	}

	public void Validate_eyeicon(String DateSet) {
		// TODO Auto-generated method stub
		try {

			Thread.sleep(3000);
			Common.textBoxInput("id", "email", data.get(DateSet).get("UserName"));
			Common.textBoxInput("id", "pass", data.get(DateSet).get("Password"));
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
		try {
			Common.clickElement("xpath", "//button[contains(@class,'action login')]");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Home Page"),
					"To validate the user lands on Home page after successfull login", "Should land on Home Page",
					"User lands on Home Page", "User failed to login");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validate the User is able to login",
					"Should login into user Account sucessfully", " sucessfully login to the account",
					Common.getscreenShotPathforReport("User is unable to login"));

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

	public void addDeliveryAddress(String dataSet) throws Exception {
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
				Sync.waitElementPresent(30, "xpath", "//input[@value='fedex_FEDEX_GROUND']");
				Common.clickElement("xpath", "//input[@value='fedex_FEDEX_GROUND']");
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
	
	public void Shippingpage_Validation() {
		try {
		Boolean logo =	Common.findElement("xpath","//a[@class='a-logo']").isDisplayed();
		System.out.println(logo);
		Boolean Backtocart =	Common.findElement("xpath","//a[@title='Back to Cart']").isDisplayed();
		System.out.println(Backtocart);
		Boolean Shipping =	Common.findElement("xpath","//li[@class='opc-progress-bar-item _active']").isDisplayed();
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
	
	public void Paymentpage_Validation() {
		try {
			Boolean Payment_Review = Common.findElement("xpath","//li[@class='opc-progress-bar-item _active']").isDisplayed();
			System.out.println(Payment_Review);
			Common.findElement("xpath", "//label[@for='stripe_payments']").isDisplayed();
			Common.clickElement("xpath", "//label[@for='stripe_payments']");
	       Boolean checkbox  =	Common.findElement("xpath", "//fieldset[@style='display: none;']").isSelected();
	       System.out.println(checkbox);
			
	//	int  size =	Common.findElements("xpath", "//input[@name='billing-address-same-as-shipping']").size();
	//	Common.assertionCheckwithReport(size>0, "validating the My billing and shipping address are same checkbox", "Checkbox is selected", "Failed to check the Mybilling and shiipind address checkbox");
			
		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the user lands on the payment page",
					"User should land on the payment page", "User failed to land on the payment page",
					"Payment navigation failed");
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
            Boolean image = Common.findElement("xpath", "//img[@title='32 oz Wide Mouth - Acai Purple']").isDisplayed();
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
			Sync.waitPageLoad();

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

		Common.assertionCheckwithReport(errorTexts <= 0, "validating the credit card information with valid data",
				expectedResult, "Filled the Card detiles", "missing field data it showinng error");
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

	public String bottles_addtocart_pdp(String Dataset) {
		// TODO Auto-generated method stub
		String sku = "";
		String product = data.get(Dataset).get("ProductQuantity");
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
			Common.clickElement("xpath", "//img[@alt='" + product + "']");
			Common.assertionCheckwithReport(Common.getPageTitle().contains("18 oz Standard Mouth"),
					"validating the product should navigate to the PDP page",
					"When we click on the product is should navigate to the PDP page",
					"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");

			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//div[@aria-label='" + productcolor + "']");
			Common.clickElement("xpath", "//div[@aria-label='" + productcolor + "']");
			Common.clickElement("xpath", "//button[@title='Add to Bag']");
			sku = Common.findElement("xpath", "//div[contains(@class,'m-select-menu m-fo')]//select")
					.getAttribute("data-cart-item-id");
			System.out.println(sku);
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
		return sku;
	}

	public void minicart_scroll() {
		// TODO Auto-generated method stub

		try {
			Sync.waitElementPresent("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
			Common.mouseOverClick("xpath", "//span[contains(@class,'c-mini-cart__icon')]");

			Common.scrollIntoView("xpath", "//span[@class='a-product-attribute__value']");
			List<WebElement> allitems = Common.findElements("xpath", "//span[@class='a-product-attribute__value']");
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

	public void shop_addtocart(String Dataset) {
		// TODO Auto-generated method stub

		String product = data.get(Dataset).get("Products");
		String product1 = data.get(Dataset).get("ProductQuantity");
		try {
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
			Common.mouseOver("xpath", "//img[@alt='" + product + "']");
			Common.clickElement("xpath", "//form[@class='m-add-to-cart ']");
			Common.mouseOver("xpath", "//img[@alt='" + product1 + "']");
			Common.clickElement("xpath", "//form[@class='m-add-to-cart ']");
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

		if (!url.contains("stage") && !url.contains("preprod")) {
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

	public void shop_QAtest(String category) {
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

	public void QAtest_addtocart_pdp(String Dataset) {
		// TODO Auto-generated method stub
		String sku1 = "";
		String sku2 = "";
		String product = data.get(Dataset).get("ProductQuantity");
		System.out.println(product);
		String product1 = data.get(Dataset).get("Products");
		System.out.println(product1);

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
			Sync.waitElementPresent(30, "xpath", "//img[@alt='" + product1 + "']");
			Common.clickElement("xpath", "//img[@alt='" + product1 + "']");
			Common.assertionCheckwithReport(Common.getPageTitle().contains("32 oz Wide Mouth"),
					"validating the product should navigate to the PDP page",
					"When we click on the product is should navigate to the PDP page",
					"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");

			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//form[@class ='m-add-to-cart ']");
			sku1 = Common.findElement("xpath", "//form[@class ='m-add-to-cart ']").getAttribute("data-product-sku");
			System.out.println(sku1);
			Common.clickElement("xpath", "//button[@title='Add to Bag']");
			shop_QAtest("QA_Testing");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//img[@alt='" + product + "']");
			Common.clickElement("xpath", "//img[@alt='" + product + "']");
			Common.assertionCheckwithReport(Common.getPageTitle().contains("32 oz Wide Mouth"),
					"validating the product should navigate to the PDP page",
					"When we click on the product is should navigate to the PDP page",
					"Sucessfully Product navigate to the PDP page", "Failed product to the PDP page");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//form[@class ='m-add-to-cart ']");
			sku2 = Common.findElement("xpath", "//form[@class ='m-add-to-cart ']").getAttribute("data-product-sku");
			System.out.println(sku2);
			Common.clickElement("xpath", "//button[@title='Add to Bag']");

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
//		return sku2,sku1;
	}

	public void search_product_pdp(String Dataset) {
		// TODO Auto-generated method stub
		String sku3 = "";
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
			String productsearch = Common.findElement("id", "algolia-srp-title").getText();
			Common.assertionCheckwithReport(productsearch.contains(product), "validating the search functionality",
					"enter product name will display in the search box", "user enter the product name in  search box",
					"Failed to see the product name");
			Common.clickElement("xpath", "//img[@alt='" + product + "']");

			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//form[@class ='m-add-to-cart']");
			sku3 = Common.findElement("xpath", "//form[@class ='m-add-to-cart']").getAttribute("data-product-sku");
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

	public void verify_ordersummary() {
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
		try {
			Sync.waitElementPresent("xpath", "//div[@class='m-accordion__title']");
			Common.clickElement("xpath", "//div[@class='m-accordion__title']");
			Sync.waitElementPresent("xpath", "//div[@class='content minicart-items']");
			String expand = Common.findElement("xpath", "//div[@class='content minicart-items']")
					.getAttribute("aria-hidden");
			String productname = Common.findElement("xpath", "//div[@class='m-mini-product-card__name']").getText();
			Common.assertionCheckwithReport(
					expand.equals("false") && productname.contains(product)
							|| expand.equals("false") && productname.contains("32 OZ WIDE"),
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

	public void shipping_method_verification(String Dataset) {
		// TODO Auto-generated method stub
		String Method = data.get(Dataset).get("methods");
		try {
			Common.scrollIntoView("xpath", "//span[@class='value']");
			Sync.waitElementPresent("xpath", "//span[@class='value']");
			String shippingmethod = Common.findElement("xpath", "//span[@class='value']").getText();
			Common.assertionCheckwithReport(shippingmethod.equals(Method),
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

	public void populate_Shippingaddress_fields(String DataSet) throws Exception {

		Common.scrollIntoView("name", "firstname");
		Common.textBoxInput("name", "firstname", data.get(DataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(DataSet).get("LastName"));

		Common.textBoxInput("name", "street[0]", data.get(DataSet).get("Street"));
		Common.scrollIntoView("name", "city");

		Common.textBoxInput("name", "city", data.get(DataSet).get("City"));

		Sync.waitElementPresent("xpath", "//select[@name='region_id']");
		Common.dropdown("xpath", "//select[@name='region_id']", SelectBy.TEXT, data.get(DataSet).get("Region"));

		Sync.waitElementPresent("xpath", "//input[@placeholder='Zip/Postal Code']");
		Common.textBoxInput("xpath", "//input[@placeholder='Zip/Postal Code']", data.get(DataSet).get("postcode"));
		// Common.dropdown("name", "country_id", SelectBy.TEXT, "United States");
		Sync.waitElementPresent("xpath", "//input[@placeholder='Phone Number']");
		Common.textBoxInput("xpath", "//input[@placeholder='Phone Number']", data.get(DataSet).get("phone"));
	}

	public void selectshippingmethod(String DataSet) {
		String shippingmethod = data.get(DataSet).get("Shippingmethods");
		try {

			Common.scrollIntoView("xpath", "//td[contains(text(),'" + shippingmethod + "')]");

			Sync.waitElementClickable("xpath", "//td[contains(text(),'" + shippingmethod + "')]");
			Common.javascriptclickElement("xpath", "//td[contains(text(),'" + shippingmethod + "')]");
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//input[@class='a-radio-button__input']");

			if (shippingmethod.contains("Fixed")) {
				String selecetdshippingmethod = Common.findElement("xpath", "//input[@value='flatrate_flatrate']")
						.getAttribute("aria-labelledby");
				System.out.println(selecetdshippingmethod);

				Common.assertionCheckwithReport(selecetdshippingmethod.contains("Fixed - Flat Rate"),
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

	public void Validate_Paymentpage(String dataSet) {
		try {

			selectshippingmethod(dataSet);

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

				Common.clickElement("xpath", "//input[@value='fedex_FEDEX_2_DAY']");
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

	public void click_AddNewAdress_ShippingPage() {
		// TODO Auto-generated method stub
		try {
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.clickElement("xpath", "//span[contains(text(),'Add New Address')]");

			Common.isElementVisibleOnPage(30, "xpath", "//h1[contains(text(),'Shipping Address')]");
			String Shipping = Common.findElement("xpath", "//h1[contains(text(),'Shipping Address')]").getText();
			System.out.println(Shipping);
			Common.assertionCheckwithReport(Shipping.contains("Shipping Address"),
					"validate shipping address pop up when click on editaddress link",
					"it should open a shipping address popup when click on editaddress link",
					"successfully open a shipping address pop up when click on editaddress link",
					"failed to open shipping address pop up when click on editaddress link");

		} catch (Exception | Error e) {

			ExtenantReportUtils.addFailedLog("validate shipping address pop up when click on editaddress link",
					"it should open a shipping address popup when click on editaddress link",
					"successfully open a shipping address pop up when click on editaddress link",
					Common.getscreenShot("failed to open shipping address pop up when click on editaddress link"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void ShippingAddress(String dataSet) {

		String FirstName = data.get(dataSet).get("FirstName");
		String LastName = data.get(dataSet).get("LastName");
		String Street = data.get(dataSet).get("Street");
		String City = data.get(dataSet).get("City");
		String Region = data.get(dataSet).get("Region");
		String postcode = data.get(dataSet).get("postcode");
		String Country = data.get(dataSet).get("Country");
		String phone = data.get(dataSet).get("phone");

		try {

			Sync.waitElementPresent("xpath", "//input[@name='firstname']");

			Common.textBoxInput("xpath", "//input[@name='firstname']", FirstName);

			Sync.waitElementPresent("xpath", "//input[@name='lastname']");
			Common.textBoxInput("xpath", "//input[@name='lastname']", LastName);

			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Sync.waitElementPresent("xpath", "//input[@name='street[0]']");
			Common.textBoxInput("xpath", "//input[@name='street[0]']", Street);

			Common.textBoxInput("xpath", "//input[@name='city']", City);

			Common.dropdown("xpath", "//select[@name='region_id']", SelectBy.TEXT, Region);

			Sync.waitElementPresent("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']", postcode);

			Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, Country);

			Sync.waitElementPresent("xpath", "//input[@name='telephone']");
			Common.textBoxInput("xpath", "//input[@name='telephone']", phone);

			Common.clickElement("xpath", "//span[contains(text(),' Ship Here')]");

		} catch (Exception | Error e) {

			e.printStackTrace();

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
			String CheckedAddress = Common.findElement("xpath", "//div[@class='shipping-address-item selected-item']")
					.getAttribute("class");
			System.out.println(CheckedAddress);
			String fullname = Common.findElement("xpath", "(//div[contains(@class,'item selected-item')]/p/strong)")
					.getText();
			System.out.println(fullname);

			String selectedaddress = Common.findElement("xpath", "(//div[contains(@class,'item selected-item')]/p)[2]")
					.getText();

			Common.assertionCheckwithReport(
					fullname.equals(FirstName + " " + LastName) && CheckedAddress.contains("item selected-item")
							&& selectedaddress.equals(Street + "\n" + City + ", " + Region + " " + postcode + "\n"
									+ Country + "\n" + phone),

					"validate the user is able to update the new address in the shipping page",
					"user should able to update the new address in the shipping page",
					"user successfuly update the new address in the shipping page",
					"failed to update the new address in the shipping page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validate the user is able to update the new address in the shipping page",
					"user should  able to update the new address in the shipping page",
					"user successfuly update the new address in the shipping page",
					Common.getscreenShot("failed to update the new address in the shipping page"));

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

	public void click_EditAddress() {
		// TODO Auto-generated method stub
		try {

			Common.clickElement("xpath", "//div[@class='shipping-address-item selected-item']/div");
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
			Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password2"));

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
			Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password2"));

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

	public void Click_Browserstock() {
		// TODO Auto-generated method stub

		try {

			Common.clickElement("xpath", "//a[text()='  Browse Stock']");
			int browsersize = Common.findElements("xpath", "//div[@class='conv-inventory-wrapper']").size();
			System.out.println(browsersize);
			Common.assertionCheckwithReport(browsersize > 0, "validating Browse Stock page",
					"user navigates to Browse Stock page", "Sucessfully user navigate to Browse Stock page",
					"faield to navigate to Browse Stock page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating Browse Stock page",
					"System directs the user back to Browse Stock page", "unable to user go back to Browse Stock page",
					Common.getscreenShotPathforReport("faield to get back to Browse Stock page"));
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

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating google maps page",
					"System directs the user back to google maps page", "unable to user go back to google maps page",
					Common.getscreenShotPathforReport("faield to get back to google maps page"));
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

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating reviews page", "System directs the user back to reviews page",
					"unable to user go back to reviews page",
					Common.getscreenShotPathforReport("faield to get back to reviews page"));
			Assert.fail();
		}

	}

	public int getpageresponce(String url) throws MalformedURLException, IOException {
		HttpURLConnection c = (HttpURLConnection) new URL(url).openConnection();
		c.setRequestMethod("HEAD");
		c.connect();
		int r = c.getResponseCode();

		return r;
	}

	public void validate_Shop_Headermenu() throws InterruptedException {

		Thread.sleep(3000);
		String categoryname;
		try {
			List<WebElement> shopcategoryOptions = Common.findElements("xpath",
					"//ul[contains(@class,'level0 ')]//span[2]");

			System.out.println(shopcategoryOptions.size());
			for (int i = 1; i < shopcategoryOptions.size(); i++) {

				Common.mouseOverClick("xpath", "//li[contains(@class,'level0 nav-1')]/a/span");
				Thread.sleep(5000);
				List<WebElement> shopcategoryOption = Common.findElements("xpath",
						"//li[contains(@class,'level1 nav-1')]/a/span[2]");
				categoryname = shopcategoryOption.get(i - 1).getText();
				System.out.println(categoryname);
				shopcategoryOption.get(i - 1).click();

				List<WebElement> shopsubcategorylinks = Common.findElements("xpath",
						"//li[contains(@class,'level1 nav-1-" + i + "')]//li/a");
				System.out.println(shopsubcategorylinks.size());

				for (int j = 1; j <= shopsubcategorylinks.size(); j++) {

					Sync.waitElementVisible("xpath", "//li[contains(@class,'level1 nav-1-" + i + "')]//li/a");
					Thread.sleep(2000);
					// String subcategoryname=shopsubcategorylinks.get(j).getText();

					String subcategoryname = Common
							.findElement("xpath", "(//li[contains(@class,'level1 nav-1-" + i + "')]//li/a)[" + j + "]")
							.getText();
					System.out.println(subcategoryname);
					// shopsubcategorylinks.get(j).click();
					Common.mouseOverClick("xpath",
							"(//li[contains(@class,'level1 nav-1-" + i + "')]//li/a)[" + j + "]");

					Sync.waitPageLoad();
					Sync.waitElementVisible("xpath", "//h1[contains(@class,'hero__headline')]");

					int responcecode = getpageresponce(Common.getCurrentURL());

					System.out.println(responcecode);

					if (responcecode == 200) {
						ExtenantReportUtils.addPassLog("Validating" + subcategoryname + "Page  ",
								"click the shop linka navigating to " + subcategoryname + "Page",
								"successfully page navigating to " + subcategoryname + "PAGE",
								Common.getscreenShotPathforReport(subcategoryname));
					} else {

						ExtenantReportUtils.addFailedLog("Validating Page URL " + subcategoryname + "page",
								"click the shop linka navigating to " + subcategoryname + "Page ",
								"unable to find page it showing 40 error",
								Common.getscreenShotPathforReport(subcategoryname));
						Assert.fail();
					}

					Common.mouseOverClick("xpath", "//li[contains(@class,'level0 nav-1 category-item')]/a/span");

					List<WebElement> categoryOption = Common.findElements("xpath",
							"//li[contains(@class,'level1 nav-1')]/a/span[2]");
					categoryname = categoryOption.get(i - 1).getText();
					System.out.println(categoryname);
					categoryOption.get(i - 1).click();

				}
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Shop links", "user validating shop link ad sub links",
					"faield to load the shop links", Common.getscreenShotPathforReport("shoplinks"));
			Assert.fail();

		}

	}

	public void Validate_Explore_Headermenu() throws InterruptedException {

		Thread.sleep(3000);

		try {
			List<WebElement> ExplorecategoryOptions = Common.findElements("xpath",
					"//li[contains(@class,'level1 nav-2')]/a");

			System.out.println(ExplorecategoryOptions.size());
			for (int i = 1; i <= ExplorecategoryOptions.size(); i++) {

				Common.mouseOverClick("xpath", "//li[contains(@class,'level0 nav-2')]/a/span");
				Thread.sleep(5000);
				List<WebElement> ExplorecategoryOption = Common.findElements("xpath",
						"//li[contains(@class,'level1 nav-2')]/a/span");
				String Explorecategoryname = ExplorecategoryOption.get(i - 1).getText();
				System.out.println(Explorecategoryname);

				Common.mouseOverClick("xpath", "(//li[contains(@class,'level1 nav-2')]/a/span)[" + i + "]");

				Sync.waitPageLoad();
				// Sync.waitElementVisible("xpath", "//h1[contains(@class,'hero__headline')]");

				int responcecode = getpageresponce(Common.getCurrentURL());

				System.out.println(responcecode);

				if (responcecode == 200) {
					ExtenantReportUtils.addPassLog("Validating" + Explorecategoryname + "Page  ",
							"click the shop linka navigating to " + Explorecategoryname + "Page",
							"successfully page navigating to " + Explorecategoryname + "PAGE",
							Common.getscreenShotPathforReport(Explorecategoryname));
				} else {

					ExtenantReportUtils.addFailedLog("Validating Page URL " + Explorecategoryname + "page",
							"click the shop linka navigating to " + Explorecategoryname + "Page ",
							"unable to find page it showing 40 error",
							Common.getscreenShotPathforReport(Explorecategoryname));
					// Assert.fail();
				}

				Common.mouseOverClick("xpath", "//li[contains(@class,'level0 nav-2')]/a/span");

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Shop links", "user validating shop link ad sub links",
					"faield to load the shop links", Common.getscreenShotPathforReport("shoplinks"));
			Assert.fail();

		}

	}

	public void search_product1(String Dataset) throws Exception {
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

			String find = Common.findElement("xpath", "//span[contains(text(),'32 oz Wide Mouth - Acai Purple')]")
					.getText();
			System.out.println(find);

			Common.assertionCheckwithReport(find.equals("32 oz Wide Mouth - Acai Purple"),
					"validating the search functionality", "enter product name will display in the search box",
					"user enter the product name in  search box", "Failed to see the product name");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the search functionality",
					"enter product name will display in the search box",
					" unable to enter the product name in  search box",
					Common.getscreenShot("Failed to see the product name"));
			Assert.fail();

		}
	}

	public void addDeliveryAddress1(String dataSet) throws Exception {
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
			Thread.sleep(5000);
			Common.clickElement("xpath", "//input[@class='a-radio-button__input']");

			expectedResult = "shipping address is filled in to the fields";

			ExtenantReportUtils.addPassLog("validating the shipping address field with valid Data", expectedResult,
					"Filled the shipping address", Common.getscreenShotPathforReport("shippingaddresspass"));
		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating the shipping address field with valid Datas", expectedResult,
					"failed to add a addres in the filled",
					Common.getscreenShotPathforReport("failed to add a address"));

			Assert.fail();
		}

	}

	public void Validate_invalid_Discount_code(String dataset) {

		// String cardnumber=data.get(dataset).get("Invaliddiscountcode");

		try {

			Common.actionsKeyPress(Keys.PAGE_UP);
			Common.scrollIntoView("xpath", "//span[contains(text(),'Add Discount Code')]");
			Common.clickElement("xpath", "//span[contains(text(),'Add Discount Code')]");
			Thread.sleep(5000);

			Common.clickElement("id", "discount-code");
			Thread.sleep(5000);
			Common.textBoxInput("id", "discount-code", data.get(dataset).get("Invaliddiscountcode"));

			Sync.waitElementPresent("xpath", "//button[@value='Apply Discount']");

			Common.clickElement("xpath", "//button[@value='Apply Discount']");

			Thread.sleep(1000);
			String invalidcode = Common.findElement("xpath", "//div[@class='message message-error error']/div")
					.getText();

			System.out.println(invalidcode);
			Common.assertionCheckwithReport(invalidcode.contains("The coupon code isn't valid."),
					"validating the invalid discount code", "invalid code should be entered",
					"Sucessfully showing the error message ", "failed to showing the error message");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the invalid discount code", "invalid code should be entered",
					"Sucessfully showing the error message",
					Common.getscreenShot("failed to showing the error message"));
			Assert.fail();
		}
	}

	public void Validate_valid_Discount_code(String dataset) {

		try {

			Thread.sleep(8000);
			Sync.waitElementPresent("id", "discount-code");
			Common.clickElement("id", "discount-code");
			Thread.sleep(4000);
			Common.textBoxInput("id", "discount-code", data.get(dataset).get("validdiscountcode"));

			Sync.waitElementPresent("xpath", "//button[@value='Apply Discount']");

			Common.clickElement("xpath", "//button[@value='Apply Discount']");
			// Sync.waitPageLoad();
			Thread.sleep(1000);
			String validcode = Common.findElement("xpath", "//div[@class='message message-success success']").getText();

			System.out.println(validcode);
			Common.assertionCheckwithReport(validcode.contains("success"), "validating the valid discount code",
					"valid code should be entered", "Sucessfully showing the success message ",
					"failed to showing the suceess message");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the valid discount code", "valid code should be entered",
					"Sucessfully showing the success message",
					Common.getscreenShot("failed to showing the suceess message"));
			Assert.fail();
		}

	}

	public void verify_ordersummary_valid_discount_code() throws Exception {
		try {
			String Subtotal = Common.getText("xpath", "//tr[@class='totals sub']//span[@class='price']").replace("$",
					"");
			Float subtotalvalue = Float.parseFloat(Subtotal);
			String shipping = Common.getText("xpath", "//tr[@class='totals shipping excl']//span[@class='price']")
					.replace("$", "");
			Float shippingvalue = Float.parseFloat(shipping);
			String discount = Common.getText("xpath", "//tr[@class='totals discount']//span[@class='price']")
					.replace("-$", "");
			Float discountvalue = Float.parseFloat(discount);
			String Tax = Common.getText("xpath", "//tr[@class='totals-tax']//span[@class='price']").replace("$", "");
			Float Taxvalue = Float.parseFloat(Tax);
			String ordertotal = Common.getText("xpath", "//tr[@class='grand totals']//span[@class='price']")
					.replace("$", "");

			Float ordertotalvalue = Float.parseFloat(ordertotal);
			Thread.sleep(4000);
			Float Total = (subtotalvalue + shippingvalue + Taxvalue) - discountvalue;
			String ExpectedTotalAmmount2 = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			Float ExpectedTotalAmmount2lvalue = Float.parseFloat(ExpectedTotalAmmount2);
			System.out.println(ordertotalvalue);
			System.out.println(ExpectedTotalAmmount2lvalue);

			Common.assertionCheckwithReport(ExpectedTotalAmmount2lvalue.equals(ordertotalvalue),
					"validating the order summary in the shipping  page",
					"Order summary should be display in the shipping page and all fields should display",
					"Successfully Order summary is displayed in the shipping page and fields are displayed",
					"Failed to display the order summary and fileds under order summary");

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the order summary in the shipping page",
					"Order summary should be display in the shipping page and all fields should display",
					"Unabel to display the Order summary and fields are not displayed in the shipping page",
					Common.getscreenShot("Failed to display the order summary and fileds under order summary"));
			Assert.fail();
		}

	}

	public void ClosADD() throws Exception {
		Thread.sleep(3000);
		try {
			int sizesframe = Common.findElements("xpath", "//div[@class='preloaded_lightbox']/iframe").size();
			System.out.println(sizesframe);
			if (sizesframe > 0) {
				Common.actionsKeyPress(Keys.PAGE_UP);

//					    Common.switchFrames("xpath", "//div[@class='preloaded_lightbox']/iframe");
				Sync.waitElementPresent("xpath", "//div[@class='sidebar-iframe-close']");
				Common.javascriptclickElement("xpath", "//div[@class='sidebar-iframe-close']");
			} else {
				int sizeofpopup = Common.findElements("id", "wpx-newsletter-popup").size();
				if (sizeofpopup > 0) {

					Sync.waitElementClickable("xpath", "//button[@aria-label='close']");
					Common.clickElement("xpath", "//button[@aria-label='close']");
				}
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
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
					Common.getscreenShot("Failed to Navigate to the MY account page after Clicking on my account CTA"));
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
			Common.clickElement("xpath", "//a[text()='Address Book']");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Address Book"),
					"validating the Navigation to the Address Book page",
					"After Clicking on Address Book CTA user should be navigate to the Address Book page",
					"Sucessfully User Navigates to the Address Book page after clicking on the Address Book CTA",
					"Failed to Navigate to the Address Book page after Clicking on Address Book CTA");

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
				Common.clickElement("xpath", "//select[@name='region_id']");
				Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT, region);
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
			Common.clickElement("xpath", "//select[@name='region_id']");
			Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT, region);
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
			Common.clickElement("xpath", "//button[@title='Save Address']");
			Sync.waitElementPresent("xpath", "//div[@data-ui-id='message-success']//div");
			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();
			String shippingaddress = Common.findElement("xpath", "//div[contains(@class,'box box-address-s')]//address")
					.getText();
			Common.assertionCheckwithReport(
					shippingaddress.equals(shipping) && message.equals("You saved the address."),
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

	public String ValidateAccountcreationeyeicon(String dataSet) {
		// TODO Auto-generated method stub
		String Email = "";
		try {

			Sync.waitPageLoad();
			Sync.waitElementPresent("id", "firstname");
			Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));

			Common.textBoxInput("id", "email_address", Utils.getEmailid());
			Email = Common.findElement("id", "email_address").getAttribute("value");
			System.out.println(Email);

			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			String classes = Common.findElement("id", "validation-classes").getAttribute("class");
			String textlength = Common.findElement("id", "validation-length").getAttribute("class");
			Common.actionsKeyPress(Keys.PAGE_DOWN);

			Common.assertionCheckwithReport(classes.contains("complete") && textlength.contains("complete"),
					"Password is validated", "password should be validate", "failed to validate password");
			Common.actionsKeyPress(Keys.UP);

			String eyeicon = Common.findElement("xpath", "(//span[contains(@class,'m-password')])[1]")
					.getAttribute("class");
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

			String eyeicon = Common.findElement("xpath", "(//span[contains(@class,'m-password')])[2]")
					.getAttribute("class");
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
			ExtenantReportUtils.addFailedLog("validating confirm password eyeicon", "password able to display",
					"unable to display password", Common.getscreenShotPathforReport("faield to gdisplay password"));
			Assert.fail();

		}

	}

	public void Validate_Createaccount_signup_newsletter() {
		// TODO Auto-generated method stub

		try {

			Sync.waitPageLoad();
			int size = Common.findElements("xpath", "//input[@name='is_subscribed']").size();
			Common.assertionCheckwithReport(size > 0, "validating signup newsletter checkbox",
					"signup newsletter check box should be display", "able to display signup newsletter checkbox",
					"faield to signup news letter checkbox");

			Common.clickElement("xpath", "//input[@name='is_subscribed']");

			Sync.waitElementClickable("xpath", "//button[@title='Sign Up']");
			Common.clickElement("xpath", "//button[@title='Sign Up']");
			Sync.waitPageLoad();
			Sync.waitElementVisible("xpath", "//h1[@class='page-title-wrapper h2']");

			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"),
					"validating the  my Account page",
					"User should able to navigate to the my account page after clicking on submit button",
					"Sucessfully navigate to the My account page ", "failed to navigates to My Account Page");
			ClosADD();

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
			Common.textBoxInput("xpath", "//input[@name='email']", Dataset);
			Common.actionsKeyPress(Keys.ENTER);
			Common.clickElement("xpath", "//button[@data-action='grid-filter-expand']");
			String records = Common.findElement("xpath", "//div[@class='admin__control-support-text']").getText();
			String EMAIL = Common.findElement("xpath", "(//div[@class='data-grid-cell-content'])[3]").getText();

			Common.assertionCheckwithReport(records.contains("1") && EMAIL.equals(Dataset),
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
			Common.assertionCheckwithReport(Marketing.equals("Marketing"), "To Validate the Marketing is displayed",
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

	public void Admin_newsletter_validation(String Dataset) {
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

			Common.textBoxInput("xpath", "//input[@name='email']", Dataset);
			Common.actionsKeyPress(Keys.ENTER);
			String subscribe = Common.findElement("xpath", "//td[@class=' col-status col-status  ']").getText();

			Common.assertionCheckwithReport(subscribe.equals("Subscribed"), "Validating customer subscribtion ",
					"customer should be subscribed for new letter subscrpition",
					"Successfully subscribed new letter subscrption ", "Failed to subscribe new letter subscription");
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
			Common.clickElement("xpath", "//a[text()='Address Book']");
			Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Address Book"),
					"validating the Navigation to the Address Book page",
					"After Clicking on Address Book CTA user should be navigate to the Address Book page",
					"Sucessfully User Navigates to the Address Book page after clicking on the Address Book CTA",
					"Failed to Navigate to the Address Book page after Clicking on Address Book CTA");

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
				Common.clickElement("xpath", "//select[@name='region_id']");
				Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT, region);
				Common.clickElement("xpath", "//input[@name='postcode']");
				Common.textBoxInput("xpath", "//input[@name='postcode']", zipcode);
				Common.clickElement("xpath", "//label[@for='primary_billing']");
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
			Common.clickElement("xpath", "//select[@name='region_id']");
			Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT, region);
			Common.clickElement("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']", zipcode);
			String checkbox = Common.findElement("xpath", "//input[@id='primary_shipping']").getAttribute("type");
			String text = Common.findElement("xpath", "//div[@class='message info']//span").getText();
			System.out.println(text);
			Common.assertionCheckwithReport(
					checkbox.equals("checkbox") && text.equals("This is your default billing address."),
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

	public void remove_Allproducts_minicart() {

		try {
			Sync.waitElementClickable("xpath", "//span[contains(@class,'icon-header__cart')]");
			Common.javascriptclickElement("xpath", "//span[contains(@class,'icon-header__cart')]");

			int numberofproducts = Common.findElements("xpath", "//span[contains(@class,'cart__remove')]").size();

			if (numberofproducts > 0) {
				for (int i = 0; i < numberofproducts; i++) {

					String productscount = Common
							.findElement("xpath", "//p[@class='c-mini-cart__total-counter']/strong").getText();
					int count = Integer.parseInt(productscount);
					System.out.println(count);
					String productname = Common.findElement("xpath", "//div[@class='m-mini-product-card__name']/a")
							.getText();
					System.out.println(productname);
					Common.scrollIntoView("xpath", "//span[contains(@class,'cart__remove')]");
					Common.javascriptclickElement("xpath", "//span[contains(@class,'cart__remove')]");
					Sync.waitElementVisible("xpath", "//aside[@class='modal-popup confirm _show']");
					Common.javascriptclickElement("xpath", "//button[contains(@class,'action-accept')]");
					Sync.waitElementInvisible("xpath", "//a[text()='" + productname + "']");
					Thread.sleep(4000);
					int updatedproductcount = Common
							.findElements("xpath", "//p[@class='c-mini-cart__total-counter']/strong").size();
					if (updatedproductcount <= 0) {
						break;
					}
					String updatedprdctcount = Common
							.findElement("xpath", "//p[@class='c-mini-cart__total-counter']/strong").getText();
					int updatedcount = Integer.parseInt(updatedprdctcount);
					System.out.println(updatedcount);
					Common.assertionCheckwithReport(updatedcount == (count - 1) || updatedcount == 0,
							"To validate the products are deleted from the mini cart",
							"Product should be deleted from the minicart", "product deleted from mini cart",
							"Failed to remove product from mini cart");

				}

			}
			Common.javascriptclickElement("xpath", "//span[contains(@class,'minicart__close ')]");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the products are deleted from the mini cart",
					"Product should be deleted from the minicart", "Not able to delete the product form minicarrt",
					Common.getscreenShot("Failed to delete the product form minicart"));
			Assert.fail();
		}

	}

	public void setup_DefaultShipping_Billingaddress() {
		try {
			Common.scrollIntoView("xpath", "//div[contains(@class,'dashboard-addresses')]");

			String Defaultshipping = Common.findElement("xpath", "//div[contains(@class,'shipping')]//address")
					.getText();
			// System.out.println(Defaultshipping);
			String DefaultBilling = Common.findElement("xpath", "//div[contains(@class,'billing')]//address").getText();
			// System.out.println(DefaultBilling);
			if (DefaultBilling.contains(Defaultshipping)) {
				Common.assertionCheckwithReport(true, "To valifdate the default shipping and Billing address are same",
						"Default Billing and Shipping address Should be same",
						"Default Billing and Shipping address are same",
						"default Billing and shipping addresses are not same");
			} else {

				Sync.waitElementVisible("xpath", "//div[contains(@class,'billing')]//a/span");
				Common.mouseOverClick("xpath", "//div[contains(@class,'billing')]//a/span");

				Sync.waitPageLoad();
				Sync.waitElementVisible(30, "id", "form-validate");
				Common.scrollIntoView("xpath", "//div[contains(@class,'shipping a-checkbox')]");
				Common.mouseOverClick("xpath", "//div[contains(@class,'shipping a-checkbox')]//span");

				Common.clickElement("xpath", "//button[contains(@class,'submit primary')]");
				Sync.waitPageLoad();
				Sync.waitElementVisible("xpath", "//div[contains(@class,'message-success')]/div");
				String sucessmessage = Common.findElement("xpath", "//div[contains(@class,'message-success')]/div")
						.getText();
				Common.scrollIntoView("xpath", "//div[contains(@class,'dashboard-addresses')]");
				String updatedDefaultshipping = Common
						.findElement("xpath", "//div[contains(@class,'shipping')]//address").getText();
				String updatedDefaultBilling = Common.findElement("xpath", "//div[contains(@class,'billing')]//address")
						.getText();

				Common.assertionCheckwithReport(
						sucessmessage.contains("You saved the address.")
								&& updatedDefaultshipping.contains(updatedDefaultBilling),
						"To validate the default shipping and Billing address are same",
						"Default Billing and Shipping address Should be same",
						"Default Billing and Shipping address are same",
						"default Billing and shipping addresses are not same");
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

	public void Updatebillingaddress(String dataSet) {
		String FirstName = data.get(dataSet).get("FirstName");
		String LastName = data.get(dataSet).get("LastName");
		String Street = data.get(dataSet).get("Street");
		String City = data.get(dataSet).get("City");
		String Region = data.get(dataSet).get("Region");
		String postcode = data.get(dataSet).get("postcode");
		String phone = data.get(dataSet).get("phone");
		String Country = data.get(dataSet).get("Country");

		try {

			Sync.waitElementClickable("xpath", "//label[@for='stripe_payments']");
			int sizes = Common.findElements("xpath", "//label[@for='stripe_payments']").size();

			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section",
					"payment ,methods should be displayed", "User unabel to land opaymentpage");
			Common.clickElement("xpath", "//label[@for='stripe_payments']");

			/*
			 * Sync.waitElementVisible("xpath",
			 * "//div[@class='payment-method stripe-payments']");
			 * Common.javascriptclickElement("xpath",
			 * "//div[@class='payment-method stripe-payments']");
			 * 
			 * 
			 * if (!Common.findElement(By.
			 * xpath("//div[@class='items payment-methods']/div/div[2]")) .isSelected()) {
			 * System.out.println("The Payment method is selected");
			 * 
			 * }else { Common.javascriptclickElement("xpath",
			 * "//div[@class='payment-method stripe-payments']");
			 * 
			 * }
			 */
			Sync.waitElementInvisible(30, "xpath", "//div[@class='loader']/img[@alt='Loading...']");
			String active = Common.findElement("xpath", "//div[@class='items payment-methods']/div/div[2]")
					.getAttribute("class");

			Common.assertionCheckwithReport(active.contains("active"),
					"To validate the default payment section is expanded", "default payment section should be expanded",
					"default payment section is expanded", "Failed to expand the default payment section");
			if (!Common.findElement(By.xpath("//div[contains(@class,'billing-address-same-as-shipping')]//span"))
					.isSelected()) {
				Common.findElement(By.xpath("//div[contains(@class,'billing-address-same-as-shipping')]//span"))
						.click();

				Sync.waitElementVisible("name", "billing_address_id");
				Common.dropdown("name", "billing_address_id", SelectBy.TEXT, "New Address");
				populate_Shippingaddress_fields("AccountDetails");

				Common.scrollIntoView("id", "billing-save-in-address-book-stripe_payments");
				Common.assertionCheckwithReport(
						Common.findElement(By.id("billing-save-in-address-book-stripe_payments")).isSelected() == true,
						"To validate the Save in address book checkbox is selected",
						"Save in address book checkbox should be selected", "Save in address book checkbox is selected",
						"Failed to select the Save in address book checkbox is selected");

				Common.javascriptclickElement("xpath", "//button[contains(@class,'action-update')]");

			} else {
				System.out.println("the shipping and billing address is not same");
				Assert.fail();
			}

			String updatedfullname = Common
					.findElement("xpath", "//div[contains(@class,'billing-address-details')]/p/strong").getText();
			System.out.println(updatedfullname);

			String updateddaddress = Common
					.findElement("xpath", "//div[contains(@class,'billing-address-details')]/p[2]").getText();
			System.out.println(updateddaddress);
			String updatedtelephone = Common
					.findElement("xpath", "//div[contains(@class,'billing-address-details')]/p[3]").getText();
			System.out.println(updatedtelephone);

			Common.assertionCheckwithReport(
					updatedfullname.equals(FirstName + " " + LastName)
							&& updateddaddress
									.equals(Street + "\n" + City + ", " + Region + " " + postcode + "\n" + Country)
							&& updatedtelephone.contains(phone),

					"validate the user is able to update the new address in the shipping page",
					"user should able to update the new address in the shipping page",
					"user successfuly update the new address in the shipping page",
					"failed to update the new address in the shipping page");
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

			String emailsection = Common.findElement("xpath", "//div[contains(@class,'collapsible-email')]")
					.getAttribute("class");
			System.out.println(emailsection);

			Common.assertionCheckwithReport(emailsection.contains("active"),
					"validating the email section when we click on edit",
					"email section field should be displayed when we click on edit",
					"Sucessfully emailsection  is displayed when we click on edit",
					"Failed to display section when we click on edit");
			// div[contains(@class,'collapsible-email')]
			String Newemail = Common.findElement("xpath", "//input[@name='email']").getAttribute("type");
			System.out.println(Newemail);
			String Currentpassword = Common.findElement("xpath", "(//input[@name='current_password'])[1]")
					.getAttribute("type");
			System.out.println(Currentpassword);
			Common.assertionCheckwithReport(Newemail.equals("email") && Currentpassword.equals("password"),
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

			String passworddots = Common.findElement("xpath", "(//input[@name='current_password'])[1]")
					.getAttribute("type");
			System.out.println(passworddots);

			Common.assertionCheckwithReport(passworddots.contains("password"), "validating the passoword dot display",
					"the password should be displed in dots", "Sucessfully password disply in dots",
					"Failed to display password in dots");
			Common.actionsKeyPress(Keys.UP);

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the passoword dot display",
					"the password should be displed in dots", "Sucessfully password disply in dots",
					Common.getscreenShotPathforReport("Failed to navigate newsletter filed page"));
			Assert.fail();

		}

	}

	public void validate_Accountinformation_invalid_email(String Dataset) {
		// TODO Auto-generated method stub

		String errormessage = data.get(Dataset).get("errormessage");
		try {
			Sync.waitElementPresent("xpath", "//input[@title='Email']");
			// Common.textBoxInputClear("xpath", "//input[@title='Email']");
			Common.textBoxInput("xpath", "//input[@title='Email']", data.get(Dataset).get("Email"));
			Common.javascriptclickElement("xpath",
					"//div[contains(@class,'collapsible-email')]//button[contains(@class,'save primary')]");
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
			String EMAIL = Common.findElement("xpath", "//input[@title='Email']").getAttribute("value");
			System.out.println(EMAIL);
			Common.javascriptclickElement("xpath",
					"//div[contains(@class,'collapsible-email')]//button[contains(@class,'save primary')]");

			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();

			Common.assertionCheckwithReport(message.contains("You saved the account information."),
					"validating the saved message after changing email",
					"Save email message should be displayed after the email is saved",
					"Sucessfully changed email has been saved ", "Failed to save the change email");
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
		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public void cookiebanner() {
		Sync.waitElementClickable("xpath", "//button[@id='truste-consent-button']");
		Common.javascriptclickElement("xpath", "//button[@id='truste-consent-button']");
	}

	public void ProdelerPage(String dataSet) throws Exception {
		ClickProDealguest();

		String prodealname = Common.getCurrentURL();
		{
			/*
			 * String expectedResult="User is redirected to the Apply For Pro Deal page" ;
			 * Common.assertionCheckwithReport(prodealname.contains("prodeal"),
			 * "Successfully redirected prodeal page",
			 * expectedResult,"User unabel to land on prodeal page");
			 */
			String expectedResult = "User is redirected to login page";

			Common.clickElement("xpath", "//a[@title='Sign in or register']");
			Sync.waitElementPresent("id", "email");
			int sizeemeil = Common.findElements("id", "email").size();
			Common.assertionCheckwithReport(sizeemeil > 0, "Successfully redirected to login page", expectedResult,
					"User unabel to land on login page");

			try {

				Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
				Sync.waitElementPresent("id", "pass");
				Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
				Sync.waitElementPresent("xpath", "//button[@class='action login primary a-btn a-btn--primary']");
				Common.clickElement("xpath", "//button[@class='action login primary a-btn a-btn--primary']");

				Common.assertionCheckwithReport(Common.getPageTitle().contains("Pro Deal Application"),
						"Successfully land on pro deal application page", expectedResult,
						"User unabel to land on pro deal application page");
				Validate_Register_ProDealpage();

				Sync.waitElementPresent("xpath", "//a[@class='pro-deal-link a-btn a-btn--tertiary']");
				Common.clickElement("xpath", "//a[@class='pro-deal-link a-btn a-btn--tertiary']");
				String link = Common.findElement("xpath", "//a[@class='a-btn a-btn--tertiary']").getAttribute("href");
				System.out.println(link);
				Common.assertionCheckwithReport(link.contains("application"),
						"successfully land on pro deal registration page", expectedResult,
						"User abel to land on pro deal registration page");

			} catch (Exception | Error e) {
				ExtenantReportUtils.addFailedLog("successfully land on pro deal registration page",
						"User unabel to land on pro deal registration page",
						Common.getscreenShotPathforReport("Failed to land on prodeal aplication "));
				Assert.fail();

			}

			Thread.sleep(3000);
			Common.switchWindows();
			Thread.sleep(3000);
			expectedResult = "User is redirected to Pro Deal application page";
			try {

				Sync.waitElementPresent("id", "first_name");
				int fistnamesize = Common.findElements("id", "first_name").size();
				Common.assertionCheckwithReport(fistnamesize > 0,
						"Successfully User is redirected to Pro Deal application page", expectedResult,
						"User unabel to redirected to Pro Deal application page");
				Thread.sleep(3000);
				Common.textBoxInput("id", "first_name", data.get(dataSet).get("FirstName"));
				Sync.waitElementPresent("id", "last_name");
				Common.textBoxInput("id", "last_name", data.get(dataSet).get("LastName"));
				Sync.waitElementPresent("id", "association");
				Common.textBoxInput("id", "association", data.get(dataSet).get("Company Name"));
				Sync.waitElementPresent("id", "association_email");
				Common.textBoxInput("id", "association_email", data.get(dataSet).get("CompanyEmail"));
				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(6000);
				String path = System.getProperty("user.dir")
						+ ("\\src\\test\\resources\\TestData\\Hydroflask\\TestScreen.png");
				// Sync.waitElementInvisible("xpath",
				// "//input[@id='supporting_document']");
				Common.fileUpLoad("xpath", "//input[@id='supporting_document']", path);

				Sync.waitElementClickable("xpath", "//select[@id='group_id']");

				System.out.println(data.get(dataSet).get("GropName"));
				Common.dropdown("id", "group_id", SelectBy.VALUE, "4");

				Sync.waitElementPresent("id", "comment");
				Common.textBoxInput("id", "comment", data.get(dataSet).get("Comments"));

				Sync.waitElementPresent("xpath", "//button[@title='Submit']");
				Common.clickElement("xpath", "//button[@title='Submit']");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("ProDeal application from filling",
						"User faield to fill the prodeal aplication ",
						Common.getscreenShotPathforReport("prodeal aplication "));
				Assert.fail();

			}

			// String text=Common.getText("xpath",
			// "//div[@class='pro-deal-header']/h4");

			String text = Common.getText("xpath", "//div[@class='success']");
			// Assert.assertEquals(text, "Pro Deal Application Complete", "pro
			// Deal application completed");

			expectedResult = "User gets redirected to confirmation page and email is sent to email provided.";

			Common.assertionCheckwithReport(text.contains("Thank you for applying"), "verifying Pro Deal conformation",
					expectedResult, "User redirected to confirmation page",
					"User unabel to redirected to confirmation page");

		}

		try {
			Sync.waitElementPresent("xpath", "//a[@title='Sign in or register']");

		} catch (Exception e) {
			Validate_Register_ProDealpage();
			Thread.sleep(3000);
		}
		// String prodealname = Common.getCurrentURL();

		// expectedResult = "User is redirected to the Apply For Pro Deal page";
		// Common.assertionCheckwithReport(prodealname.contains("prodeal"), "verifying
		// Pro Deal page ", expectedResult,
		// "Successfully redirected prodeal page", "User unabel to land on prodeal
		// page");

		//

	}

	public void ClickProDealguest() throws Exception {

		try {
			Sync.waitElementPresent("xpath", "//a[text()='Pro Deal']");
			Common.clickElement("xpath", "//a[text()='Pro Deal']");
			Sync.waitElementVisible("xpath", "//h1[@class='page-title-wrapper']");
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Pro Deal Application"),
					"To validate the Pro Deal", "Should be display the Pro Deal Application ",
					"Successfully display the Pro Deal Application", "Failed to  display the Pro Deal Application");
		} catch (Exception | Error e) {

			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Pro Deal Application",
					"Should display the Pro Deal Application ", "Unable to displays the Pro Deal Application",
					Common.getscreenShot("Failed to  display the Pro Deal Application"));

			Assert.fail();
		}
	}

	public void Validate_Register_ProDealpage() throws Exception {
		try {
			Common.actionsKeyPress(Keys.END);
			Sync.waitElementPresent("xpath", "//a[text()='Pro Deal']");
			Common.clickElement("xpath", "//a[text()='Pro Deal']");
//Sync.waitElementVisible("xpath", "//h1[@class='page-title-wrapper']");	
			String deal = Common.findElement("xpath", "//a[@class='pro-deal-link a-btn a-btn--tertiary']").getText();
			System.out.println(deal);
			String text = Common.findElement("xpath", "//h2[text()='Apply For Pro Deal']").getText();
			System.out.println(text);
			String Access = Common.findElement("xpath", "//h2[text()='Enter Access Code']").getText();
			System.out.println(Access);
			String code = Common.findElement("xpath", "//input[@name='access_code']").getAttribute("title");
			System.out.println(code);
			String Enter = Common.findElement("id", "pro-deal-form").getAttribute("action");
			System.out.println(Enter);
			Common.assertionCheckwithReport(
					deal.contains("Apply to pro deal") && text.contains("Apply For Pro Deal")
							&& Access.contains("Enter Access Code") && code.contains("Access Code")
							&& Enter.contains("prodeal/index/enter/"),
					"To validate the Pro Deal", "Should be display the Pro Deal Application ",
					"Successfully display the Pro Deal Application", "Failed to  display the Pro Deal Application");
		} catch (Exception | Error e) {

			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the Pro Deal Application",
					"Should display the Pro Deal Application ", "Unable to displays the Pro Deal Application",
					Common.getscreenShot("Failed to  display the Pro Deal Application"));

			Assert.fail();

		}
	}

	public void Prodeal_invalid_access_code(String dataSet) {
		try {
			Sync.waitPageLoad();

			Common.textBoxInput("id", "access_code", data.get(dataSet).get("prodealaccesscode"));
			Common.clickElement("xpath", "//button[@title='Submit']");
			String message = Common.findElement("xpath", "//div[contains(@class,'message-error')]/div").getText();
			System.out.println(message);
			Common.assertionCheckwithReport(message.contains("Access code was not found."),
					"validating the displays error message ", "should display the error message",
					"successfully display the error message", "failed to display the error message");
		} catch (Exception | Error e) {

			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the displays error message",
					"Should display the error message", "Unable to displays the error message",
					Common.getscreenShot("Failed to  display the error message"));
			Assert.fail();

		}

		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//button[@title='Submit']");
			Sync.waitElementVisible(40, "id", "access_code-error");
			String field = Common.findElement("id", "access_code-error").getText();
			System.out.println(field);
			Common.assertionCheckwithReport(field.contains("This is a required field."),
					"validating the Displays error messaging beneath the missing fields",
					"should Displays error messaging beneath the missing fields",
					"successfullyDisplays error messaging beneath the missing fields",
					"failed to Displays error messaging beneath the missing fields");

		}

		catch (Exception | Error e) {

			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Displays error messaging beneath the missing fields",
					"Should display the error messaging beneath the missing fields",
					"Unable to displays the error messaging beneath the missing fields",
					Common.getscreenShot("Failed to displays the error messaging beneath the missing fields"));

			Assert.fail();
		}
	}

	public void prodeal_access_code(String dataSet) throws Exception {
		try {
			Sync.waitPageLoad();

			Common.textBoxInput("id", "access_code", data.get(dataSet).get("prodealaccesscode"));
			Common.clickElement("xpath", "//button[@title='Submit']");
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

	public void MyAccount_Prodeal() {
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "(//a[text()='Pro Deal'])[1]");
			// String information = Common.findElement("xpath", "//h1[text()='Pro deal
			// information']").getText();

			Common.assertionCheckwithReport(Common.getPageTitle().contains("Pro deal information"),

					"To validate the page Pro Deal is displayed in my account",
					"Should display the Pro Deal in My account", "Successfully display the Pro Deal in my account",
					"Failed to  display the Pro Deal");
		} catch (Exception | Error e) {

			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To validate the page Pro Deal is displayed in my account",
					"Should display the Pro Deal in My account", "Unable to displays the Pro Deal in My account",
					Common.getscreenShot("Failed to  display the Pro Deal in my account"));

			Assert.fail();

		}
	}

	public void Validate_OrderConfirmationPage(String dataSet) throws Exception {
		// TODO Auto-generated method stub

		String expectedResult = "It redirects to order confirmation page";

		try {
			String sucessMessage = Common.getText("xpath", "//h1[@class='page-title-wrapper']").trim();
			int sizes = Common.findElements("xpath", "//h1[@class='page-title-wrapper']").size();

			String order = Common.findElement("xpath", "//a[@class='order-number']").getAttribute("href");
			System.out.println(order);

			String Right_rail = Common.getText("xpath", "//div[@class='c-icon-card-list__headers']");
			System.out.println(Right_rail);

			int ordersuccesspagecategorylinks = Common
					.findElements("xpath", "//div[@class='actions-toolbar']//a[contains(@class,'button-secondary')]")
					.size();

			for (int i = 1; i <= ordersuccesspagecategorylinks; i++) {
				String links[] = data.get(dataSet).get("categorylinks").split(",");

				String categoryink = Common
						.findElement("xpath",
								"(//div[@class='actions-toolbar']//a[contains(@class,'button-secondary')])[" + i + "]")
						.getAttribute("href");
				System.out.println(categoryink);
				Common.assertionCheckwithReport(categoryink.contains(links[i - 1]),
						"verify the category links in order success page",
						"category links should peresent in order success page",
						"In order success page successfully displayed category links",
						"failed to display category links in order success page");
			}

			Common.assertionCheckwithReport(
					sucessMessage.contains("Thank you for your purchase!") && order.contains("view/order_id/")
							&& Right_rail.contains("We are here for you"),
					"verifying the order confirmation confirmation page", expectedResult,
					"Successfully It redirects to order confirmation page Order Placed",
					"User unabel to go orderconformation page");

		} catch (Exception | Error e) {
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
			int content = Common.findElements("xpath", "//span[@class='m-icon-card__text']").size();
			System.out.println(content);
			for (int i = 1; i <= content; i++) {
				String text[] = data.get(dataSet).get("Content").split(",");
				String Rightrail_Content = Common
						.findElement("xpath", "(//span[@class='m-icon-card__text'])[" + i + "]").getText();
				System.out.println(Rightrail_Content);
				Common.assertionCheckwithReport(Rightrail_Content.contains(text[i - 1]),
						"verify the Content in the We are here for you section  ",
						"Content should present in the We are here for you section",
						"Content successfully present in the We are here for you section",
						"failed to  present content in the We are here for you section");

			}

		} catch (Exception | Error e) {
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
			int right_rail = Common.findElements("xpath", "//a[@class='m-icon-card__link']").size();
			System.out.println(right_rail);
			for (int i = 1; i <= right_rail; i++) {
				String link[] = data.get(dataSet).get("hyperlink").split(",");
				System.out.println(link[i - 1]);
				String Rightrail_hyperlink = Common.findElement("xpath", "(//a[@class='m-icon-card__link'])[" + i + "]")
						.getAttribute("href");
				System.out.println(Rightrail_hyperlink);
				Common.assertionCheckwithReport(Rightrail_hyperlink.contains(link[i - 1]),
						"verify the chatnow , telephone , email address links  in We are here for you section",
						"chatnow , telephone , email address links should present in We are here for you section",
						"chatnow , telephone , email address links successfully present in We are here for you section",
						"failed to present chatnow , telephone , email address links in We are here for you section");

			}
			Common.clickElement("xpath", "//span[contains(text(),'Chat Now')]");
			Thread.sleep(5000);
			Common.switchFrames("id", "kustomer-ui-sdk-iframe");
			Sync.waitElementVisible(30, "xpath", "//div[@class='widget__widget___uExal']");
			int chatbotvisible = Common.findElements("xpath", "//div[@class='widget__widget___uExal']").size();
			System.out.println(chatbotvisible);
			Common.assertionCheckwithReport(chatbotvisible > 0, "To validate chatnow link",
					"chatbot should open after clicking on chat now link",
					"chatbot successfully open after clicking on chat now link",
					"failed to open chatbot after clicking on chat now link");
			Common.switchToDefault();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"verify the chatnow , telephone , email address links  in We are here for you section",
					"chatnow , telephone , email address links should present in We are here for you section",

					"failed to present chatnow , telephone , email address links in We are here for you section",

					Common.getscreenShotPathforReport(
							"failed to present chatnow , telephone , email address links in We are here for you section"));

			Assert.fail();
		}

	}

	public void Validate_Retailers() {
		// TODO Auto-generated method stub

		Sync.waitPageLoad();
		try {
			Common.switchFrames("xpath", "//iframe[@id='lcly-embedded-iframe-inner-0']");
			Sync.waitElementPresent("xpath", "//div[@class='conversion-column-inner']");
			int retail = Common.findElements("xpath", "//div[@class='conversion-column-inner']").size();
			System.out.println(retail);
			Common.assertionCheckwithReport(retail > 0, "Validating retailers information",
					"validated retailers information", "Successfully validated retailers information ",
					"Failed to validated retailers information");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating retailers information ",
					"Retailers information should be validated", "unable to validate retailers information ",

					Common.getscreenShotPathforReport("Failed to navigate retailers information"));
			Assert.fail();

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

			String emailsection = Common.findElement("xpath", "//div[contains(@class,'collapsible-password')]")
					.getAttribute("class");
			System.out.println(emailsection);

			Common.assertionCheckwithReport(emailsection.contains("active"),
					"validating the confirm password section when we click on edit",
					"confirm password section field should be displayed when we click on edit",
					"Sucessfully confirm password  is displayed when we click on edit",
					"Failed to display  confirm password section when we click on edit");

			String currentpassword = Common.findElement("xpath", "(//input[@name='current_password'])[2]")
					.getAttribute("name");
			System.out.println(currentpassword);
			String Newpassword = Common.findElement("xpath", "(//input[@name='password'])[1]").getAttribute("type");
			System.out.println(Newpassword);
			String confirmpassword = Common.findElement("xpath", "(//input[@name='password_confirmation'])")
					.getAttribute("name");
			System.out.println(confirmpassword);

			Common.assertionCheckwithReport(
					currentpassword.contains("password") && Newpassword.contains("password")
							&& confirmpassword.contains("confirmation"),
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
		}
	}

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
			Common.textBoxInput("xpath", "(//input[@name='password_confirmation'])",
					data.get(Dataset).get("Newpassword"));
			Common.javascriptclickElement("xpath",
					"//div[contains(@class,'collapsible-password')]//button[contains(@class,'save primary')]");
			Sync.waitElementVisible(30, "xpath", "//div[contains(@class,'message-error')]");
			String invalidcurrentpassword = Common.findElement("xpath", "//div[contains(@class,'message-error')]/div")
					.getText();
			System.out.println(invalidcurrentpassword);
			Common.assertionCheckwithReport(invalidcurrentpassword.equals(errormessage),
					"To validate the error message when given invalid email or password",
					"Should dispaly the error message " + errormessage,
					invalidcurrentpassword + "error message is displayed", "Failed to display error message");

			Sync.waitElementPresent("xpath", "//button[contains(@class,'toggle-password')]//span//span");
			Common.clickElement("xpath", "//button[contains(@class,'toggle-password')]//span//span");

			Sync.waitElementPresent("xpath", "(//input[@name='current_password'])[2]");
			Common.textBoxInput("xpath", "(//input[@name='current_password'])[2]",
					data.get(Dataset).get("CurrentPassword"));
			Sync.waitElementPresent("xpath", "(//input[@name='password'])[1]");
			Common.textBoxInput("xpath", "(//input[@name='password'])[1]", data.get(Dataset).get("Password"));
			Sync.waitElementPresent("xpath", "(//input[@name='password_confirmation'])");
			Common.textBoxInput("xpath", "(//input[@name='password_confirmation'])",
					data.get(Dataset).get("Newpassword"));
			Common.javascriptclickElement("xpath",
					"//div[contains(@class,'collapsible-password')]//button[contains(@class,'save primary')]");
			Sync.waitElementVisible(30, "xpath", "//div[@id='password-error']");
			String invalidnewpassword = Common.findElement("xpath", "//div[@id='password-error']").getText();
			System.out.println(invalidnewpassword);
			Common.assertionCheckwithReport(invalidnewpassword.equals(invalidnewpassworderror),
					"To validate the error message when given invalid email or password",
					"Should dispaly the error message " + invalidnewpassworderror,
					invalidnewpassword + "error message is displayed", "Failed to display error message");

			Common.scrollIntoView("xpath", "(//input[@name='password'])[1]");
			Sync.waitElementPresent("xpath", "(//input[@name='password'])[1]");

			Common.textBoxInput("xpath", "(//input[@name='password'])[1]", data.get(Dataset).get("Newpassword"));
			Sync.waitElementPresent("xpath", "(//input[@name='password_confirmation'])");
			Common.textBoxInput("xpath", "(//input[@name='password_confirmation'])", data.get(Dataset).get("Password"));
			Common.javascriptclickElement("xpath",
					"//div[contains(@class,'collapsible-password')]//button[contains(@class,'save primary')]");
			Sync.waitElementVisible(30, "xpath", "//div[contains(@class,'message-error')]/div");
			String nonmatchpassword = Common.findElement("xpath", "//div[contains(@class,'message-error')]/div")
					.getText();
			System.out.println(nonmatchpassword);
			Common.assertionCheckwithReport(nonmatchpassword.equals(confirmpassword),
					"To validate the error message when given invalid email or password",
					"Should dispaly the error message " + confirmpassword,
					nonmatchpassword + "error message is displayed", "Failed to display error message");

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

			// p[@class='text-email']
			String Email = Common.findElement("xpath", "//p[@class='text-email']").getText();
			System.out.println(Email);
			Sync.waitElementPresent("xpath", "//button[contains(@class,'toggle-password')]//span//span");
			Common.clickElement("xpath", "//button[contains(@class,'toggle-password')]//span//span");

			Sync.waitElementPresent("xpath", "(//input[@name='current_password'])[2]");
			Common.textBoxInput("xpath", "(//input[@name='current_password'])[2]",
					data.get(Dataset).get("CurrentPassword"));
			Sync.waitElementPresent("xpath", "(//input[@name='password'])[1]");
			Common.textBoxInput("xpath", "(//input[@name='password'])[1]", data.get(Dataset).get("Newpassword"));
			Sync.waitElementPresent("xpath", "(//input[@name='password_confirmation'])");
			Common.textBoxInput("xpath", "(//input[@name='password_confirmation'])",
					data.get(Dataset).get("Newpassword"));
			Common.javascriptclickElement("xpath",
					"//div[contains(@class,'collapsible-password')]//button[contains(@class,'save primary')]");

			String message = Common.findElement("xpath", "//div[@data-ui-id='message-success']//div").getText();

			Common.assertionCheckwithReport(message.contains("You saved the account information."),
					"validating the saved message after changing email",
					"Save email message should be displayed after the email is saved",
					"Sucessfully changed email has been saved ", "Failed to save the change email");
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

	public void My_Orders_Page() {
		try {
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
			String message1 = Common.findElement("xpath", "//span[contains(text(),'You have placed no orders.')]")
					.getText();
			System.out.println(message1);
			Common.assertionCheckwithReport(message1.equals("You have placed no orders."),
					"validating the  message after navigating to the My Orders page ",
					" message should be displayed in My Orders page ",
					"Sucessfully message should be displayed in My Orders page",
					"Failed to display message in my orders page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the  My Myorders page with message",
					"After Clicking on My Orders CTA user should be navigate to the My Orders page with message",
					"Unable to Display the message in the My orders page",
					Common.getscreenShot("Failed to Display message in the My orders page"));
			Assert.fail();
		}
	}

	public void myOrders_Shopcategory() {
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//span[@class='icon-carousel__right']");

			Common.clickElement("xpath", "//span[@class='icon-carousel__left']");
			Sync.waitPageLoad();
			String MyOrder = Common.findElement("xpath", "//h2[contains(text(),'Shop By Category')]").getText();
			Common.assertionCheckwithReport(MyOrder.contains("Shop By Category"),
					"validating the Shop By Category in the My Orders page",
					"verifying Shop By Category in the My Orders page",
					"Sucessfully User Navigates to Shop By Category in the My Orders page",
					"Failed to Navigate to Shop By Category in the My Orders page");

			Common.clickElement("xpath", "(//a[@class='m-category-card m-category-card--carousel'])[2]");

			Common.assertionCheckwithReport(Common.getPageTitle().contains("Shop"),
					"validating the Shop By Category navigating from the My Orders page ",
					"Shop By Category should be navigated from the My Orders page ",
					"Sucessfully Shop By Category navigated from the My Orders page after selecting category",
					"Failed to display message in my orders page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Shop By Category in the My Orders page",
					"verifying Shop By Category in the My Orders page",
					"Sucessfully User Navigates to Shop By Category in the My Orders page",
					Common.getscreenShot("Failed to Display Shop By Category in the My orders page"));
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
			Common.textBoxInput("id", "customer-email", Utils.getEmailid());

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

			if (errorsize <= 0) {
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
			String shopping=Common.findElement("xpath", "//span[text()='Shop Bottles & Drinkware']//parent::a").getAttribute("href");
			String kitchen=Common.findElement("xpath", "//span[text()='Shop Kitchenware']//parent::a").getAttribute("href");
			Common.clickElement("xpath", "//input[@name='password']");
			Common.textBoxInput("xpath", "//input[@name='password']", data.get(Dataset).get("Password"));
			Common.clickElement("xpath", "(//span[@class='sr-only'])[1]");
			Sync.waitElementPresent(30, "xpath", "//input[@name='password_confirmation']");
			Common.clickElement("xpath", "//input[@name='password_confirmation']");
			Common.textBoxInput("xpath", "//input[@name='password_confirmation']",
					data.get(Dataset).get("Confirm Password"));
			Common.clickElement("xpath", "(//span[@class='sr-only'])[2]");
			String accounttext=Common.findElement("xpath", "//div[@data-appearance='full-bleed']//p").getText();
			String confirmpassword=Common.findElement("xpath", "//input[@name='password_confirmation']").getAttribute("type");
			String password=Common.findElement("xpath", "//input[@name='password_confirmation']").getAttribute("type");	
			String Message = Common.findElement("id", "validation-classes").getCssValue("color");
			String Greencolor=Color.fromString(Message).asHex();
			String Message1 = Common.findElement("id", "validation-length").getAttribute("class");
			  Common.assertionCheckwithReport(Greencolor.equals("#067a63") &&
			  Message1.contains("complete")&&shopping.contains("/shop/bottles")&&kitchen.
			  contains("/shop/kitchenware")&&confirmpassword.equals("text")&&password.equals("text")&&accounttext.contains("Create an account"),
			  "validating the order confirmation page",
			  "User should able to view all details in the order confirmation page",
			  "Sucessfully all details has been displayed in the order confirmation",
			  "Failed to display all details in the order confirmation page");
			  Sync.waitElementPresent(30, "xpath", "(//span[@class='sr-only'])[1]");
			  Common.clickElement("xpath", "(//span[@class='sr-only'])[1]");
			  Sync.waitElementPresent(30, "xpath", "(//span[@class='sr-only'])[2]");
			  Common.clickElement("xpath", "(//span[@class='sr-only'])[2]");
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
	public void Retailers_address(String DataSet) {
		// TODO Auto-generated method stub

	try {
		ClosADD();
			Common.switchFrames("xpath", "//iframe[contains(@id,'lcly-embedded-iframe')]");
			Common.clickElement("xpath", "//input[@name='location']");
			Common.textBoxInput("xpath", "//input[@class='locally-search location-autocomplete']",data.get(DataSet).get("Country"));
			Thread.sleep(8000);
			Common.javascriptclickElement("xpath", "(//div[@class='autocomplete-suggestion'])[1]");
			//Common.switchToDefault();
			
			/*String Store=Common.getText("xpath", "(//h3[text()='Tradehome Shoes '])[1]");
			System.out.println(Store);
			Assert.assertEquals(Store, "Tradehome Shoes");*/
			
			/*String A=Common.getText("xpath", "(//h3[text()='Tradehome Shoes '])[1]");
				System.out.println(A);*/
				int size = Common.findElements("id", "conversion-sidebar").size();

				
				System.out.println(size);
				Common.assertionCheckwithReport(size > 0, "validating Retailers page", "user navigates to Retailers page",
						"Sucessfully user navigate to Retailers page",
						"faield to navigate to Retailers page");
			
				
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating Retailers page",
						"System directs the user back to Retailers page", "unable to user go back to Retailers page");

			}
	}
	public void Click_filter() {
			// TODO Auto-generated method stub
			try {
		
				Thread.sleep(4000);
				Common.clickElement("xpath", "(//h4[@class='filter-header-title'])[1]");
				Thread.sleep(4000);
				Common.clickElement("xpath", "//label[@for='filter-item-top_level_categories-935-company_location']");
				Thread.sleep(4000);
				
				String A=Common.getText("xpath", "//h5[text()='8L Lunch Tote']");
				System.out.println(A);
				Assert.assertEquals(A, "8L Lunch Tote");
				
				Common.assertionCheckwithReport(A.equals("8L Lunch Tote"),
						"Validating the luggage and bags filter product",
						"customer should check luggage and bags filter product",
						"Successfully shows luggage and bags filter product ",
						Common.getscreenShotPathforReport("succesfully show luggage and bags filter product"));
			
		}catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating luggage and bags filter product ",
					"customer should view luggage and bags filter product",
					"unable to view luggage and bags filter product  ",
			
					Common.getscreenShotPathforReport("Failed to show luggage and bags filter product"));
			Assert.fail();
		}
	}
	public void selectproduct_filter() {
			// TODO Auto-generated method stub
			try {

				Sync.waitElementPresent(40, "xpath", "//h5[text()='8L Lunch Tote']");
				Common.clickElement("xpath", "//h5[text()='8L Lunch Tote']");
				Thread.sleep(8000);
		
				String product=Common.getText("xpath", "//h3[@class='pdp-product-name pdp-product-title']");
				System.out.println(product);
				Assert.assertEquals(product, "8L Lunch Tote");
				Common.assertionCheckwithReport(product.equals("8L Lunch Tote"),
						"user navigates to product details page", "Sucessfully user navigate to product details page",
						"faield to navigate to product details page and unable to see error message");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating product details page",
						"System directs the user back to the product details page",
						"unable user back to product details page",
						Common.getscreenShotPathforReport("failed to get back product details page"));
				Assert.fail();
			}
	}
	public void click_Retailer1() {
			// TODO Auto-generated method stub
		String store = "DICK'S Sporting Goods  - San Antonio | Curbside Contactless Pickup Available - 5.3 mi";
			try {
				Sync.waitPageLoad();
				Common.clickElement("xpath", "//span[text()='Retailers']");
				Thread.sleep(5000);
				//Common.switchFrames("xpath", "//iframe[contains(@id,'lcly-embedded-iframe')]"); 
				int storeSize = Common.findElements("xpath", "//div[@id='conversion-locations']").size();
				System.out.println(storeSize);
				Common.assertionCheckwithReport(storeSize > 0, "validating the Retailers page",
						"user navigates to Retailers page", "Sucessfully user open Retailers page",
						"faield to navigate to Retailers page");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating Retailers page",
						"System directs the user back to Retailers page", "unable to user go back to Retailers page",
						Common.getscreenShotPathforReport("faield to get back to Retailers page"));
				Assert.fail();

						Common.getscreenShotPathforReport("faield to get back to Retailers page");
}
	}
			}
