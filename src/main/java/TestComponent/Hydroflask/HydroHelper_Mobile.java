package TestComponent.Hydroflask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;

public class HydroHelper_Mobile {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();

	public HydroHelper_Mobile(String datafile,String sheetname) {
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
			
			Common.clickElement("xpath", "//span[@class='icon-header__menu a-icon-text-btn__icon']");
			Sync.processSync(5000);
			//WebElement account = Common.findElement("xpath", "//div[@class='m-account-nav__welcome']/button/span");
			try {
				WebElement account = Common.findElement("xpath", "//a[@id='ui-id-5']");
				account.click();
				}catch(Exception e) {
					e.printStackTrace();
					Sync.processSync(5000);
					Common.clickElement("xpath", "//span[@class='icon-header__menu a-icon-text-btn__icon']");
					WebElement	account = Common.findElement("xpath", "//a[@id='ui-id-5']");
					account.click();
				}
			Sync.processSync(3000);
			//Common.findElement("xpath", "//a[@id='ui-id-48']").click();
			
			String device=System.getProperty("dev","ios");
			if(device.equalsIgnoreCase("ios")) {
			Common.findElement("xpath", "//a[text()='Sign In']").click();
			}
			Sync.processSync(2000);
			
			/*
			 * Sync.waitElementClickable("xpath",
			 * "//div[@class='m-account-nav__content']/button");
			 * Common.clickElement("xpath",
			 * "//div[@class='m-account-nav__content']/button");
			 * Sync.waitElementClickable("xpath",
			 * "//div[@class='m-account-nav__content']/button");
			 * Common.clickElement("xpath", "//li[@class='m-account-nav__log-in']/a");
			 */
			Common.assertionCheckwithReport(
					Common.getText("xpath", "//h3[text()='Sign In']").equals("Sign In"),
					"To validate the signIn-buton", "Validate the signIn-button", "Successfully click singIn button",
					"User unabel to click singup button");

		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validate the signIn-button ", "User navigating signin page",
					"Successfully click singIn button ",
					Common.getscreenShotPathforReport("User unabel to click singup button"));
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
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Customer Login"),
					"To validate the user lands on Home page after successfull login", "Should land on Home Page",
					"User lands on Home Page", "User failed to login");

		} catch (Exception e) {
			e.printStackTrace();
			report.addFailedLog("Validate the User is able to login", "Should login into user Account sucessfully",
					" sucessfully login to the account", Common.getscreenShotPathforReport("User is unable to login"));

			Assert.fail();
		}
	}

public void Validate_Myaccountoptions(String string) {
		
		try {
			Common.clickElement("xpath", "//span[@class='icon-header__menu a-icon-text-btn__icon']");
			Sync.processSync(6000);
			//WebElement account = Common.findElement("xpath", "//div[@class='m-account-nav__welcome']/button/span");
			WebElement account = Common.findElement("xpath", "//a[@id='ui-id-5']");
			account.click();
			Sync.processSync(3000);
			List<WebElement> Myaccountoptions = Common.findElements("xpath", "//ul[@class='m-account-nav__links']/li/a");

			ArrayList<String> names = new ArrayList<String>();
			for (int i = 0; i < Myaccountoptions.size(); i++) {
				names.add(Myaccountoptions.get(i).getText());
				System.out.println(names);
			}

			for (int i = 0; i < names.size(); i++) {
			//	Sync.waitElementClickable("xpath", "//div[@class='m-account-nav__welcome']/button");

			//	Common.javascriptclickElement("xpath", "//div[@class='m-account-nav__welcome']/button");
				if (names.get(i).equals("MY ACCOUNT")) {

					Common.javascriptclickElement("xpath", "//ul[@class='m-account-nav__links']/li[1]/a");
					Sync.waitPageLoad();
					Sync.waitElementVisible(30, "xpath", "//h1[@class='page-title-wrapper h2']");
					Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"),
							"Validating Muy account page navigation", "after clinking My account CTA it will navigate My Account page",
							"Successfully navigate to My Account page", "Failed to navigate to my account page ");
				}

				else if (names.get(i).contains("MY FAVORITES")) {
					Sync.waitElementClickable("xpath", "//ul[@class='m-account-nav__links']/li[1]/a");
					Common.javascriptclickElement("xpath", "//ul[@class='m-account-nav__links']/li[2]/a");
					Sync.waitPageLoad();
					Sync.waitElementVisible(30, "xpath", "//h1[@class='page-title-wrapper h2']");
					Common.assertionCheckwithReport(Common.getPageTitle().contains("My Favorites"),
							"Validating My favourites page navigation",
							"after clinking My favourites CTA it will navigate My favourites page",
							"Successfully navigate to My favourites page", "Failed to navigate My favourites page ");
				}

				else if (names.get(i).contains("MY ORDERS")) {
					Sync.waitElementClickable("xpath", "//ul[@class='m-account-nav__links']/li[1]/a");
					Common.javascriptclickElement("xpath", "//ul[@class='m-account-nav__links']/li[3]/a");
					Sync.waitPageLoad();
					Sync.waitElementVisible(30, "xpath", "//h1[@class='page-title-wrapper h2']");

					Common.assertionCheckwithReport(Common.getPageTitle().contains("My Orders"),
							"Validating MyOrders navigation",
							"after clinking MyOrders CTA it will navigate MyOrders page",
							"Successfully navigate to MyOrderspage", "Failed to navigate MyOrders page");
				}
				else if (names.get(i).contains("MY PRODUCTS SUBSCRIPTIONS")) {
					Sync.waitElementClickable("xpath", "//ul[@class='m-account-nav__links']/li[1]/a");
					Common.javascriptclickElement("xpath", "//ul[@class='m-account-nav__links']/li[4]/a");
					Sync.waitPageLoad();
					Sync.waitElementVisible(30, "xpath", "//h1[@class='page-title-wrapper h2']");

					Common.assertionCheckwithReport(Common.getPageTitle().contains("My Subscriptions"),
							"Validating Myproductssubscriptions CTA navigation",
							"after clinking Myproductssubscriptions CTA it will navigate Myproductssubscriptions page",
							"Successfully navigate to Myproductssubscriptions page", "Failed to navigate Myproductssubscriptions page");
				}
				else if (names.get(i).contains("SIGN OUT")) {
					Sync.waitElementClickable("xpath", "//ul[@class='m-account-nav__links']/li[1]/a");
					Common.javascriptclickElement("xpath", "//ul[@class='m-account-nav__links']/li[5]/a");
					Sync.waitPageLoad(30);
					Sync.waitElementVisible("xpath", "//div[contains(@class,'c-hero-block')]");

					Common.assertionCheckwithReport(Common.getPageTitle().contains("Home Page"),
							"Validating Customer logout functionality",
							"after clinking SignOut CTA it should successfully logout the customer ",
							"Successfully logout from the Account", "Failed to logout from the customer account");
				}

			}
		
		}
		catch(Exception e) {
			e.printStackTrace();
			
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
			Sync.waitElementPresent("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
			String openminicart = Common.findElement("xpath", "//div[@data-block='minicart']").getAttribute("class");
			System.out.println(openminicart);
			Common.assertionCheckwithReport(openminicart.contains("mini-cart"), "To validate the minicart popup",
					"the mini cart is displayed", "Should dislay the mini cart", "mini cart is not displayed");

			Sync.waitElementPresent(30, "xpath", "//span[contains(@class,'minicart__close')]");
			Common.javascriptclickElement("xpath", "//span[contains(@class,'minicart__close')]");
			Thread.sleep(3000);
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
			// Common.scrollToElementAndClick("xpath",
			// "//input[@id='newsletter-signup_email']");
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
			report.addFailedLog("To validate the error message for Invalid Email",
					"Should display error Please enter a valid email address.", "Failed to display the error message",
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
			Thread.sleep(6000);
			/*
			 * RemoteWebElement parent =
			 * (RemoteWebElement)Common.getDriver().findElement(By.name("login[username]"));
			 * String parentID = parent.getId(); HashMap<String, String> scrollObject = new
			 * HashMap<String, String>(); scrollObject.put("element", parentID);
			 * scrollObject.put("name", "elementName"); Common.executeJS("mobile:scroll",
			 * scrollObject);
			 */
		//	Common.getDriver().executeScript("mobile:scroll", scrollObject);
			
			Sync.waitElementPresent("name", "login[username]");
			Common.textBoxInput("name", "login[username]", data.get(dataSet).get("Email"));
			Common.javascriptclickElement("xpath", "//button[contains(@class,'action login primary')]");
			Sync.waitElementVisible(30, "id", "email-error");
			String emailerror = Common.findElement("id", "email-error").getText().trim();
			System.out.println(emailerror);
			System.out.println(errormessage);
			Common.assertionCheckwithReport(emailerror.equals(errormessage.trim()),
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
			report.addFailedLog(
					"verify the error message when any required fields are missinng will throw an error message",
					"Should display the error message as This is a required field.",
					"Failed to display the required fields Error message",
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
					" it should clik the Header links and navigate to the My account menu", "Top level navigation unsuccessfull", "Failed to navigate through the header menu");
			Assert.fail();

		}
	}



	
public void Promobanner() {
		
		try {
			Common.clickElement("xpath", "//span[text()='See Details']");
			Thread.sleep(2000);
			
            Common.assertionCheckwithReport(
					Common.getText("xpath", "//strong[text()='Free Ground Shipping']").equals("Free Ground Shipping"),
					"To validate the Popup of Free Ground Shipping",
					"Validate the Pop up of Free Ground Shipping ", "Successfully displays Free Ground Shipping Pop up",
					"User unabel display Free Ground Shipping Pop up");
		} 
		catch (Exception e) {

			e.printStackTrace();
			
			report.addFailedLog("Validate the See Details link", "User Opens the Free Ground Shipping Pop up",
					"Successfully displays Free Ground Shipping Pop up",
					Common.getscreenShotPathforReport("User unabel to display Free Ground Shipping Pop up"));
			
			Assert.fail();

		}

	}

}
