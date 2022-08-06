package TestComponent.Hydroflask;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import TestLib.Automation_properties;
import TestLib.Common;
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
					Common.getText("xpath", "//h3[@id='block-customer-login-heading']").equals("Sign In"),
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
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Home Page"),
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
			WebElement account = Common.findElement("xpath", "//div[@class='m-account-nav__welcome']/button/span");
			account.click();
			List<WebElement> Myaccountoptions = Common.findElements("xpath", "//ul[@class='m-account-nav__links']/li/a");

			ArrayList<String> names = new ArrayList<String>();
			for (int i = 0; i < Myaccountoptions.size(); i++) {
				names.add(Myaccountoptions.get(i).getText());
				System.out.println(names);
			}

			for (int i = 0; i < names.size(); i++) {
				Sync.waitElementClickable("xpath", "//div[@class='m-account-nav__welcome']/button");

				Common.javascriptclickElement("xpath", "//div[@class='m-account-nav__welcome']/button");
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
			Thread.sleep(2000);
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
			Common.mouseOverClick("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
			String openminicart = Common.findElement("xpath", "//div[@data-block='minicart']").getAttribute("class");
            System.out.println(openminicart);
			Common.assertionCheckwithReport(openminicart.contains("active"), "To validate the minicart popup",
					"the mini cart is displayed", "Should display the mini cart", "mini cart is not displayed");

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
report.addFailedLog("validating the Country Selector Pop up",
		"User successfully open Country Selector Pop up", "User unabel to open country Selector Pop up",
		Common.getscreenShotPathforReport("user failed to open the Country Selector Pop up"));
Assert.fail();
}

}

public void Verify_Available_Selections(String dataSet) {

String available = data.get(dataSet).get("CountryOptions");
String[] Select = available.split(",");
String[] url= data.get(dataSet).get("url").split(",");

int i = 0;

try {

    for (i = 1; i <= Select.length && i<=url.length; i++) {
        System.out.println(Select[i - 1]);

        Common.scrollIntoView("xpath", "(//input[contains(@name,'countrySelector')])[" + i + "]");

        String Country = Common
                .findElement("xpath", "(//label[contains(@class,'country-item__country-label')])[" + i + "]")
                .getText();

        Sync.waitElementClickable("xpath",
                "(//label[contains(@class,'country-item__country-label')])[" + i + "]");
        Common.mouseOverClick("xpath", "(//label[contains(@class,'country-item__country-label')])[" + i + "]");
        System.out.println(Country);
        Common.assertionCheckwithReport(Country.contains(Select[i - 1]), "verifying the Country" + Select[i - 1],
                "user Selects " + Select[i - 1] + "Country",
                "user successfully Selects the country " + Select[i - 1],
                "Failed to select the country " + Select[i - 1]);

        Common.scrollIntoView("xpath", "//button[contains(@class,'primary action')]");
        Common.mouseOverClick("xpath", "//button[contains(@class,'primary action')]");
        Sync.waitPageLoad();

        String Websiteurl = Common.getCurrentURL();
        System.out.println(url[i-1]);
        System.out.println(Websiteurl);


        Common.assertionCheckwithReport(Websiteurl.contains(url[i-1]), "To validate the store logo" + Select[i - 1],
                "Should Navigated to the Hydroflask website"+ Select[i - 1], "Navigated to the Hydroflask US website"+ Select[i - 1],
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
		Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().equals("Home Page"),
				"validating store logo", "System directs the user to the Homepage",
				"Sucessfully user navigates to the home page", "Failed to navigate to the homepage");
	} catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating store logo", "System directs the user to the Homepage",
				" user unable navigates to the home page", "Failed to navigate to the homepage");
		Assert.fail();
	}

}

public void UserViewsContactUsPageandSubmits(String DataSet) throws Exception{
	String country="United States";
	try {
		Sync.waitPageLoad();
		Common.actionsKeyPress(Keys.END);
		Common.clickElement("xpath", "//a[text()='Contact Us']");
		/*Common.assertionCheckwithReport(Common.getPageTitle().equals("Contact"), "validating store logo",
				"System directs the user back to the Homepage", "Sucessfully user back to home page",
				"faield to get back to homepage");*/
		Sync.waitElementPresent(40, "xpath", "//iframe[contains(@src,'https://hydroflask')]");
		Common.switchFrames("xpath","//iframe[contains(@src,'https://hydroflask')]");
		
		Sync.waitElementPresent("xpath", "//input[@id='customerEmail']");
		Common.textBoxInput("xpath", "//input[@id='customerEmail']",data.get(DataSet).get("Email"));
		

		Sync.waitElementPresent("xpath", "//input[@id='messageSubject']");
		Common.textBoxInput("xpath", "//input[@id='messageSubject']", data.get(DataSet).get("FirstName"));
		
		Sync.waitElementPresent("xpath", "//input[@id='lastName']");
		Common.textBoxInput("xpath", "//input[@id='lastName']", data.get(DataSet).get("LastName"));
		
		Sync.waitElementPresent("xpath", "//input[@name='company']");
		Common.textBoxInput("xpath", "//input[@name='company']", data.get(DataSet).get("Company"));
		
		Sync.waitElementPresent("xpath", "//input[@id='primary']");
		Common.textBoxInput("xpath", "//input[@id='primary']", data.get(DataSet).get("phone"));
		
		Sync.waitElementPresent("xpath", "//input[@name='street']");
		Common.textBoxInput("xpath", "//input[@name='street']", data.get(DataSet).get("Street"));
		
		Sync.waitElementPresent("xpath", "//input[@name='city']");
		Common.textBoxInput("xpath", "//input[@name='city']", data.get(DataSet).get("City"));
		
		Sync.waitElementPresent("xpath", "//input[@name='city']");
		Common.clickElement("xpath","//div[@id='country']");
		
		
		Sync.waitElementPresent("xpath", "//div[@value='united-states']//span[contains(text(),'United States')]");
		Common.clickElement("xpath","//div[@value='united-states']//span[contains(text(),'United States')]");
		

		Sync.waitElementPresent("xpath", "//input[@id='zipPostalCode']");
		Common.textBoxInput("xpath", "//input[@id='zipPostalCode']", data.get(DataSet).get("postcode"));
		
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
		Common.textBoxInput("xpath", "//textarea[@id='messagePreview']", data.get(DataSet).get("CommetsHydroflask"));
		
				
		Common.scrollIntoView("xpath", "//button[text()='Submit']");
		Common.clickElement("xpath", "//button[text()='Submit']");
		
		Sync.waitElementPresent("xpath", "//div[@class='form-modal-success']");
		int Contactussuccessmessage = Common
                .findElements("xpath", "//div[@class='form-modal-success']").size();
        Common.assertionCheckwithReport(Contactussuccessmessage > 0,
                "verifying Contact us Success message ", "Success message should be Displayed",
                "Contact us Success message displayed ", "failed to dispaly success message");
        ExtenantReportUtils.addPassLog("New Contact us", "Contact us Should be successfully submission",
                "Contact us Successfully", Common.getscreenShotPathforReport("Contact us"));

    } catch (Exception e) {
        e.printStackTrace();
        ExtenantReportUtils.addFailedLog("Validating  of Contact us Page",
                "Expected text should not be obtained", "Expected text is not obtained",
                "Link Validation Contact us");
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
		Common.assertionCheckwithReport(answers.contains("Answers") , "validate the ChatBot",
				"Open the ChatBot", "Sucessfully click on the ChatBot",
				"Unable to click the ChatBot");
		
		
		Common.javascriptclickElement("xpath", "//div[contains(@class,'footer__itemContainer')]/p");
		
		int elements = Common.findElements("xpath", "//div[contains(@class,'SearchListItem__details')]").size();
		
		String items[] = data.get(DataSet).get("OXOAnswers").split(",");
		
		for(int i=1; i<=elements && i<=items.length; i++) {
			System.out.println(items[i-1]);
			String searchitems = Common.findElement("xpath", "(//div[contains(@class,'SearchListItem__title')])["+i+"]").getText();
			System.out.println(searchitems);
			Assert.assertEquals(searchitems, items[i-1]);
			
		}
		
		String chat = Common.findElement("xpath", "//div[contains(@class,'footer__chatContainer')]/p").getText();
		Common.javascriptclickElement("xpath", "//div[contains(@class,'footer__chatContainer')]");
		Sync.waitElementClickable(30, "xpath", "//button[contains(@class,'CLMcd button')]");
		Common.mouseOverClick("xpath", "//button[contains(@class,'CLMcd button')]");
		//Common.isElementDisplayedonPage(30,  "xpath", "//div[contains(@class,'Thread__message')]");
		Sync.waitElementVisible("xpath", "(//div[contains(@class,'markdownBody')])[1]");
		String welcomemsg = Common.findElement("xpath", "(//div[contains(@class,'markdownBody')])[1]").getText();
		System.out.println(welcomemsg);
		
		Common.assertionCheckwithReport(chat.contains("Chat") || welcomemsg.contains("Welcome to Hydro Flask") , "validate the Chat display",
				"Open the Chat conversation in ChatBot", "Sucessfully click on the ChatBot and display the Chat conversation ",
				"Unable to dispaly the chat conversation");
		

		
		Common.switchToDefault();
		
	} catch (Exception | Error e) {
		report.addFailedLog("validate the ChatBot", "Open the ChatBot",
				"Unable click on the ChatBot",
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
		report.addFailedLog("validate the ChatBot", "Open the ChatBot", "Unable click on the ChatBot",
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
			System.out.println(Select[i-1]);

			String chat = Common
					.findElement("xpath", "(//div[contains(@class,'ListItem__itemContainer')])[" + i + "]")
					.getText();
			System.out.println(chat);
			Common.assertionCheckwithReport(chat.contains(Select[i-1]), "verifying chatbot of " + Select[i-1],
					"chatbot  " + Select[i-1] + " display", "chatbot option successfully displays " + Select[i-1],
					"Failed to display chatbot " + Select[i-1]);
		}

		Common.switchToDefault();

	} catch (Exception | Error e) {
		e.printStackTrace();
		report.addFailedLog("validate the ChatBot", "Open the ChatBot", "Unable click on the ChatBot",
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
		
		Common.switchFrames("xpath","//iframe[contains(@src,'https://hydroflask')]");
		
		Common.scrollIntoView("xpath", "//button[text()='Submit']");
		Common.clickElement("xpath", "//button[text()='Submit']");
		
		
		String errorpopup=Common.findElement("xpath","//script[@id='initial-data']").getAttribute("data-json");
		System.out.println(errorpopup);
		
		Common.assertionCheckwithReport(errorpopup.contains("Please fill out this field")&&Common.getPageTitle().equals("Contact"), "validating contactus error message and contact us page",
				"user navigates to contactus page user able to see error message", "Sucessfully user navigate to contact us page and able to seeerror message",
				"faield to navigate to contactus page and unable to see error message");

//		Common.assertionCheckwithReport(errorpopup.equals("display)),"vlaidating the pop up message after submittion","After submit button user able see the error popup","Sucessfully popup message has been diplayed after subit button","Failed to get error message after click on submit button");
		
		Common.switchToDefault();
	} catch (Exception | Error e) {
		e.printStackTrace();
		report.addFailedLog("validating store logo", "System directs the user back to the Homepage",
				"Sucessfully user back to home page",
				Common.getscreenShotPathforReport("faield to get back to homepage"));
		Assert.fail();
	}
}

public void validate_Homepage() {
	try {
		Sync.waitPageLoad();
		int size =Common.findElements("xpath", "//a[@class='a-logo']").size();
		Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().equals("Home Page"), "validating store logo",
				"System directs the user back to the Homepage", "Sucessfully user back to home page",
				"faield to get back to homepage");
	} catch (Exception | Error e) {
		e.printStackTrace();
		report.addFailedLog("validating store logo", "System directs the user back to the Homepage",
				"Sucessfully user back to home page",
				Common.getscreenShotPathforReport("faield to get back to homepage"));
		Assert.fail();
	}


	
}


public void headerlinks(String category) {
	// TODO Auto-generated method stub
	String expectedResult = "User should click the" + category;
	try
	{
	
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


	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the product category as" + category + "from navigation menu ",expectedResult ,
				"Unable to Selected the " + category + " category",
                Common.getscreenShot("Failed to click on the"+ category +""));
		
		Assert.fail();
	}
	
	
}

public void minicart_freeshipping() {
	// TODO Auto-generated method stub
	try
	{
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
		Common.mouseOver("xpath", "(//img[@class='m-product-card__image'])[1]");
		Sync.waitElementPresent("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
		List<WebElement> element = Common.findElements("xpath", "//span[text()='Add to Bag']");
		element.get(0).click();
		Sync.waitPageLoad();
		Common.mouseOver("xpath", "(//img[@class='m-product-card__image'])[1]");
		Sync.waitElementPresent("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
		Common.clickElement("xpath", "//span[text()='Add to Bag']");
		Sync.waitPageLoad();
		click_minicart();
		String shipping=Common.findElement("xpath", "//div[contains(@class,'label-')]").getText();
		if(shipping.contains("left for Free Shipping."))
		{
			Sync.waitElementPresent(30, "xpath", "//span[contains(@class,'minicart__close')]");
			Common.javascriptclickElement("xpath", "//span[contains(@class,'minicart__close')]");
			shop_bottle("Bottles & Drinkware");
			Sync.waitPageLoad();
			Common.clickElement("xpath", "(//img[@class='m-product-card__image'])[1]");
			Common.assertionCheckwithReport(Common.getPageTitle().contains("18 oz Standard Mouth"), "validating the product should navigate to the PDP page",
					"When we click on the product is should navigate to the PDP page", "Sucessfully Product navigate to the PDP page",
					"Failed product to the PDP page");
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//div[@aria-label='Black']");
            Common.clickElement("xpath", "//button[@title='Add to Cart']");
			click_minicart();

					
			
		}
		else
		{
			Assert.fail();
		}
		String Freeshipping=Common.findElement("xpath", "//div[contains(@class,'label-')]").getText();
		Common.assertionCheckwithReport(Freeshipping.equals("Good news: your order will be delivered for Free."),
				"validating the free shipping in mini cart", "Free shipping should be avaliable for selected products",
				"Successfully free shipping is appiled for selected products", "Failed to see free shipping");
		
		
	
		
		
	}
	catch(Exception | Error e)
	{ 
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the free shipping in mini cart", "Free shipping should be avaliable for selected products",
				"unable to apply free shipping for the selected products",
                Common.getscreenShot("Failed to see free shipping"));
		Assert.fail();
	}
	
}
public void click_minicart()
{
	try
	{
		Thread.sleep(8000);
		Common.actionsKeyPress(Keys.ARROW_UP);
		Sync.waitElementPresent("xpath", "//a[contains(@class,'c-mini')]");
		Common.mouseOverClick("xpath", "//a[contains(@class,'c-mini')]");
		String openminicart = Common.findElement("xpath", "//div[@data-block='minicart']").getAttribute("class");
        System.out.println(openminicart);
		Common.assertionCheckwithReport(openminicart.contains("active"), "To validate the minicart popup",
				"the mini cart is displayed", "Should display the mini cart", "mini cart is not displayed");
		
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To validate the minicart popup",
				"the mini cart is displayed", "unable to  dislay the mini cart",
                Common.getscreenShot("Failed to display the mini cart"));
		Assert.fail();
	
	}
}

public void minicart_delete(String Dataset) {
	// TODO Auto-generated method stub
	String deleteproduct = data.get(Dataset).get("Products");
	try {
		Sync.waitElementPresent(30, "xpath","//div[@class='m-mini-product-card']//span[contains(@class,'icon-cart__remove')]");
	Common.clickElement("xpath",
				"//div[@class='m-mini-product-card']//span[contains(@class,'icon-cart__remove')]");
		Sync.waitElementPresent("xpath", "//button[contains(@class,'a-btn a-btn--primary action-p')]//span");
		Common.clickElement("xpath", "//button[contains(@class,'a-btn a-btn--primary action-p')]//span");
		Thread.sleep(4000);
		String subtotal = Common.getText("xpath", "//span[@class='c-mini-cart__subtotal-amount']//span")
				.replace("$", "");
		Float subtotalvalue = Float.parseFloat(subtotal);
		String productname = Common.findElement("xpath", "(//div[@class='m-mini-product-card__info']//a[@class='a-product-name'])[2]").getText();
		String productamount1 = Common.getText("xpath", "(//span[@class='minicart-price']//span)[2]")
				.replace("$", "");
		Float productamount1value = Float.parseFloat(productamount1);
		if (productname.equals(deleteproduct)) {
			Sync.waitElementPresent(30, "xpath","(//div[@class='m-mini-product-card__info']//span[contains(@class,'icon-cart__remove')])[2]");
			Common.clickElement("xpath",
					"(//div[@class='m-mini-product-card__info']//span[contains(@class,'icon-cart__remove')])[2]");
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

public void minicart_update(String Dataset) {
	// TODO Auto-generated method stub
	String quantity = data.get(Dataset).get("ProductQuantity");
	try {

		String Subtotal = Common.getText("xpath", "//span[@class='c-mini-cart__subtotal-amount']//span")
				.replace("$", "");
		Float subtotalvalue = Float.parseFloat(Subtotal);
		Sync.waitElementPresent("xpath", "//select[@class='a-select-menu cart-item-qty']");
		Common.clickElement("xpath", "//select[@class='a-select-menu cart-item-qty']");
		Common.dropdown("xpath", "//select[@class='a-select-menu cart-item-qty']", Common.SelectBy.VALUE,data.get(Dataset).get("ProductQuantity"));
		Common.clickElement("xpath", "//span[text()='Update']");
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//p[@class='c-mini-cart__total-counter']//strong");
		String cart = Common.findElement("xpath", "//p[@class='c-mini-cart__total-counter']//strong").getText();
		System.out.println(cart);
		String Subtotal2 = Common.getText("xpath", "//span[@class='c-mini-cart__subtotal-amount']//span")
				.replace("$", "");
		Float subtotalvalue2 = Float.parseFloat(Subtotal2);
		Float Total = subtotalvalue * 2;
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
				"The product Should be navigates to the PDP page",
				"Successfully product navigates to the PDP page",
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
		String minicart = Common.findElement("xpath", "//p[@class='c-mini-cart__total-counter']//strong").getText();
		Sync.waitElementPresent("xpath", "//button[@title='Checkout']");
		Common.clickElement("xpath", "//button[@title='Checkout']");
		String checkout = Common.findElement("xpath", "//strong[@role='heading']//span").getText();
		Sync.waitElementInvisible(30, "xpath", "//div[@data-role='spinner' and @style='display: none;']");
		Common.assertionCheckwithReport(
				checkout.equals(minicart) && Common.getCurrentURL().contains("checkout/#shipping"),
				"validating the navigation to the shipping page when we click on the checkout",
				"User should able to navigate to the shipping  page", "Successfully navigate to the shipping page",
				"Failed to navigate to the shipping page");

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the navigation to the shipping page when we click on the checkout ",
				"User should able to navigate to the shipping  page", "unable to navigate to the shipping page",
				Common.getscreenShot("Failed to navigate to the shipping page"));

		Assert.fail();
	}

}
public void verifypromobanner() {
	// TODO Auto-generated method stub
	try {
		Sync.waitPageLoad();
		int size =Common.findElements("xpath", "(//div[@class='m-promo-banner__container'])[1]").size();
		Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().equals("Home Page"), "validating CMS promobanner",
				"System directs the CMS promobanner", "Sucessfully directs the cms promobanner ",
				"Failed to get CMS promobanner");
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating CMS promobanner",
				"System directs the CMS promobanner", "Unable to  directs the cms promobanner ",
				Common.getscreenShot("Failed to get CMS promobanne"));
		Assert.fail();
}
	}

public void CMSpromobanner() {
	// TODO Auto-generated method stub
	
	
	
	
	try {
		Sync.waitElementPresent("xpath", "(//div[@class='slick-initialized slick-slider']//a//span)[1]");           
           String message1=Common.findElement("xpath", "(//div[@class='slick-initialized slick-slider']//a//span)[1]").getText();
           System.out.println(message1);
           String message3=Common.findElement("xpath", "(//div[@class='slick-initialized slick-slider']//a)[1]").getAttribute("href");
           System.out.println(message3);
           if(message3.equals("https://mcloud-na-stage.hydroflask.com/#")||message1.equals("Visit Here!"))
           {
        	   Common.clickElement("xpath", "//span[text()='Visit Here!']");
        	   Common.assertionCheckwithReport(
   					Common.getText("xpath", "//strong[text()='Free Ground Shipping']").equals("Free Ground Shipping"),
   					"To validate the Popup of Free Ground Shipping",
   					"Validate the Pop up of Free Ground Shipping ", "Successfully displays Free Ground Shipping Pop up",
  					"Failed to  display the Free Ground Shipping Pop up");
        	   }
           else
           {
        	   Assert.fail();
           }
			
	
		

	} catch (Exception | Error e) {

		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To validate the Popup of Free Ground Shipping",
					"Validate the Pop up of Free Ground Shipping ", "Unable to displays the Free Ground Shipping Pop up",
				Common.getscreenShot("Failed to  display the Free Ground Shipping Pop up"));
		
		Assert.fail();	
	
	
	
}
}

public void closepromobanner() {
	// TODO Auto-generated method stub
	
	try {
		Common.clickElement("xpath", "//span[@aria-label='Close']");
		int size =Common.findElements("xpath", "//a[@class='a-logo']").size();
		Common.assertionCheckwithReport(size > 0 && Common.getPageTitle().equals("Home Page"), "validating store logo",
				"System directs the user to the Homepage", "Sucessfully user navigate to home page",
				"Failed to navigate homepage");
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating store logo",
				"System directs the user to the Homepage", " user unable to  navigate to the home page",
			Common.getscreenShot("Failed to navigate homepage"));
		Assert.fail();
}
}

public void minicart_crosssell(String Dataset) {
	// TODO Auto-generated method stub
	String product=data.get(Dataset).get("Products");
	String sku=data.get(Dataset).get("GropName");
	try
	{
       Sync.waitPageLoad();
		Common.scrollIntoView("xpath", "//img[@alt='" +product+ "']");
		Common.clickElement("xpath", "//img[@alt='" +product+ "']");
		Common.assertionCheckwithReport(Common.getPageTitle().equals(product), "validating the product should navigate to the PDP page",
				"When we click on the product is should navigate to the PDP page", "Sucessfully Product navigate to the PDP page",
				"Failed product to the PDP page");
		Sync.waitPageLoad();
		Common.clickElement("xpath", "//select[@name='options[3]']");
		Common.dropdown("xpath", "//select[@name='options[3]']", Common.SelectBy.TEXT, data.get(Dataset).get("ProductQuantity"));
        Common.clickElement("xpath", "//button[@title='Add to Cart']");
        click_minicart();
       int minicartscroll= Common.findElements("xpath", "//div[@class='m-product-upsell__item']").size();
       String subtotal = Common.getText("xpath", "//span[@class='c-mini-cart__subtotal-amount']//span")
				.replace("$", "");
		Float subtotalvalue = Float.parseFloat(subtotal);

    	   Common.clickElement("xpath", "//form[@data-product-sku='"+ sku +"']//button");
    	   String productamount=Common.getText("xpath", "//div[@data-price-box='product-id-8']//span[@class='price']").replace("$", "");
    	   Float productamountvalue = Float.parseFloat(productamount);
    	   Float Total=subtotalvalue+productamountvalue;
    	   String ExpectedTotalAmmount = new BigDecimal(Total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    	   Thread.sleep(4000);
    	   String subtotal2 = Common.getText("xpath", "//span[@class='c-mini-cart__subtotal-amount']//span").replace("$", "");
    		Common.assertionCheckwithReport(subtotal2.equals(ExpectedTotalAmmount), "validating the mini cart crosssell, add to cart and subtotal",
    				"User should able to see the crosssell in mincart product should be add to cart and subtotal should be change", "Sucessfully crosssell dispalyed product added to cart and subtotal changed ",
    				"Failed to see the crossell");
    	  
       Common.scrollIntoView("xpath", "//span[contains(@class,'minicart__close')]");
       Sync.waitElementPresent(30, "xpath", "//span[contains(@class,'minicart__close')]");
		Common.javascriptclickElement("xpath", "//span[contains(@class,'minicart__close')]");  
        

		
	}
	catch(Exception | Error e)
{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the mini cart crosssell, add to cart and subtotal",
				"User should able to see the crosssell in mincart product should be add to cart and subtotal should be change", " unable to display the crossell and products",
			Common.getscreenShot("Failed to see the crossell"));
		Assert.fail();
	
}

}

public void search_product(String Dataset) {
	// TODO Auto-generated method stub
	String product=data.get(Dataset).get("Products");
	try
	{
		Common.clickElement("xpath", "//span[contains(@class,'icon-header__s')]");
		String open = Common.findElement("xpath", "//div[contains(@class,'m-search ')]").getAttribute("class");
		Common.assertionCheckwithReport(open.contains("active"), "User searches using the search field",
				"User should able to click on the search button", "Search expands to the full page",
				"Sucessfully search bar should be expand");
		Common.textBoxInput("xpath", "//input[@id='search']", product);
		Common.actionsKeyPress(Keys.ENTER);
		Sync.waitPageLoad();
		String productsearch=Common.findElement("xpath", "//p[@class='m-breadcrumb__text']").getText();
		Common.assertionCheckwithReport(productsearch.contains(product), "validating the search functionality",
				"enter product name will display in the search box","user enter the product name in  search box",
				"Failed to see the product name");
		
	}
	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the search functionality",
				"enter product name will display in the search box"," unable to enter the product name in  search box",
			Common.getscreenShot("Failed to see the product name"));
		Assert.fail();
	}
	
}

public void shop_bottle(String category) {
	// TODO Auto-generated method stub
	String expectedResult = "User should click the" + category;
	try
	{
	
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


	catch(Exception | Error e)
	{
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the product category as" + category + "from navigation menu ",expectedResult ,
				"Unable to Selected the " + category + " category",
                Common.getscreenShot("Failed to click on the"+ category +""));
		
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
		
		Common.assertionCheckwithReport(classes.contains("complete")&&textlength.contains("complete"), "Password is validated","password should be validate","failed to validate password");
		Common.actionsKeyPress(Keys.UP);
        Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));
		
        Sync.waitElementClickable("xpath", "//button[@title='Sign Up']");
		Common.clickElement("xpath", "//button[@title='Sign Up']");
		Sync.waitPageLoad();
		Sync.waitElementVisible("xpath","//h1[@class='page-title-wrapper h2']");
		Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"), "validating the  my Account page",
				"User should able to navigate to the my account page after clicking on submit button", "Sucessfully navigate to the My account page ",
				"failed to navigates to My Account Page");
}	
	
		catch (Exception | Error e) {
			e.printStackTrace();
			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"), "validating the  my Account page",
					"User should able to navigate to the my account page after clicking on submit button", "Sucessfully navigate to the My account page ",
					"failed to navigates to My Account Page");

			Assert.fail();
}


	
}
}