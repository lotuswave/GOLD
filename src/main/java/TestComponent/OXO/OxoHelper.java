package TestComponent.OXO;

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
	public void verifingHomePage() {
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
	public void verifingContactUSErrorMessage() {
		try {
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.END);
			Common.clickElement("xpath", "//a[text()='Contact Us']");
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Contact"), "validating store logo",
					"System directs the user back to the Homepage", "Sucessfully user back to home page",
					"faield to get back to homepage");
			Sync.waitElementPresent(40, "xpath", "//iframe[contains(@src,'https://oxo')]");
			
			Common.switchFrames("xpath","//iframe[contains(@src,'https://oxo')]");
			
			//Common.scrollIntoView("xpath", "//span[@id='recaptcha-anchor']");
			//Sync.waitElementPresent(40, "xpath", "//span[@id='recaptcha-anchor']");
			//Common.clickElement("xpath","//span[@id='recaptcha-anchor']");
			
			Common.scrollIntoView("xpath", "//button[text()='Submit']");
			Common.clickElement("xpath", "//button[text()='Submit']");
			
			Common.switchToDefault();
		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("validating store logo", "System directs the user back to the Homepage",
					"Sucessfully user back to home page",
					Common.getscreenShotPathforReport("faield to get back to homepage"));
			Assert.fail();
		}
	}
	public void UserViewsContactUsPageandSubmits(String dataSet) throws Exception{
		String country="United States";
		try {
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.END);
			Common.clickElement("xpath", "//a[text()='Contact Us']");
			/*Common.assertionCheckwithReport(Common.getPageTitle().equals("Contact"), "validating store logo",
					"System directs the user back to the Homepage", "Sucessfully user back to home page",
					"faield to get back to homepage");*/
			Sync.waitElementPresent(40, "xpath", "//iframe[contains(@src,'https://oxo')]");
			Common.switchFrames("xpath","//iframe[contains(@src,'https://oxo')]");
			
			Sync.waitElementPresent("xpath", "//input[@id='customerEmail']");
			Common.textBoxInput("xpath", "//input[@id='customerEmail']",data.get(dataSet).get("Email"));
			

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
			Common.clickElement("xpath","//div[@id='country']");
			
			
			Sync.waitElementPresent("xpath", "//div[@value='united-states']//span[contains(text(),'United States')]");
			Common.clickElement("xpath","//div[@value='united-states']//span[contains(text(),'United States')]");
			

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
			int Contactussuccessmessage = Common
                    .findElements("xpath", "//div[@class='form-wrap']").size();
            Common.assertionCheckwithReport(Contactussuccessmessage > 0,
                    "verifying Contact us Success message ", "Success message should be Displayed",
                    "Contact us Success message displayed ", "failed to dispaly success message");
       

        } catch (Exception e) {
            e.printStackTrace();
            ExtenantReportUtils.addFailedLog("Validation  of Contact us Page",
                    "Success message should be Displayed", "Contact us Success message displayed",
                    "failed to dispaly success message");
            AssertJUnit.fail();
        }
	}

	public void Usersignin(String dataSet) throws Exception {
		// TODO Auto-generated method stub
	//	Common.findElement("xpath", "");
		Thread.sleep(3000);
		Common.textBoxInput("id", "email", data.get(dataSet).get("Email1"));
		Common.textBoxInput("id", "pass", data.get(dataSet).get("Password1"));
	//	Sync.waitElementClickable("xpath", "/button[@class='action login primary a-btn a-btn--primary']");
		Common.clickElement("xpath", "//button[@class='action login primary a-btn a-btn--primary']");
		Common.assertionCheckwithReport(Common.getText("xpath", "//span[contains(text(),'Welcome, test')]").equals("Welcome, test"),
				"Validating My Account page navigation",
				"user sign in and navigate to my account page",
				"Successfully navigate to my account page", "Failed to navigate my account page ");
		
		
	}
	public void MyFavoritespage(String DataSet) {
		// TODO Auto-generated method stub
		
		
		try {
			RegClickaccount();
			Sync.waitPageLoad();
		  //   Common.scrollIntoView("xpath", "//small[text()='" + image + "']");
	       //  Common.javascriptclickElement("xpath", "//small[text()='" + image + "']");
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
			report.addFailedLog("Validating My favorites page navigation ",
					"after clinking my favorites page it will navigate My favorites page",
					"Successfully navigate to My favorites page ",
					Common.getscreenShotPathforReport("Failed to navigate My favorites page"));
			Assert.fail();
		}	
	
	}
	public void Myorderspage(String DataSet) {
		// TODO Auto-generated method stub
		
		try {
			RegClickaccount();
			Sync.waitPageLoad();
		//	Common.javascriptclickElement("xpath", "//small[text()='" + image + "']");
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
			report.addFailedLog("Validating My orders page navigation ",
					"after clinking my favorites page it will navigate My orders page",
					"Successfully navigate to My orders page ",
					Common.getscreenShotPathforReport("Failed to navigate My orders page"));
			Assert.fail();
		}}
	public void MyProductSubscription(String DataSet) {
		// TODO Auto-generated method stub
		
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
			report.addFailedLog("Validating My Subscriptions page navigation ",
					"after clinking My Subscriptions page it will navigate My Subscriptions page",
					"Successfully navigate to My Subscriptions page ",
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
			report.addFailedLog("Validating Create New Customer Account page navigation ",
					"after clinking Create New Customer Account page it will navigate account creation page",
					"Successfully click singIn button ",
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
	public void minicart() {
		try {
			Sync.waitPageLoad();
			Common.javascriptclickElement("xpath", "//span[contains(@class,'c-mini-cart__icon')]");
			String openminicart = Common.findElement("xpath", "//div[@data-block='minicart']").getAttribute("class");

			Common.assertionCheckwithReport(openminicart.contains("active"),"To validate the minicart popup" ,"the mini cart is displayed",
					"Should dislay the mini cart", "mini cart is not displayed");

			Sync.waitElementPresent(30, "xpath", "//span[contains(@class,'minicart__close')]");
			Common.javascriptclickElement("xpath", "//span[contains(@class,'minicart__close')]");

		//	Common.isElementNotDisplayed("xpath", "//div[contains(@class,'mini-cart--active')]");
			int closeminicart = Common.findElements("xpath", "//div[contains(@class,'mini-cart--active')]").size();
			System.out.println(closeminicart);
			Common.assertionCheckwithReport(closeminicart >= 0, "To verify the mini cart is closed or not",
					"the mini cart is closed", "Should close the mini cart", "mini cart is not Closed");

		} catch (Exception e) {
			e.printStackTrace();
			report.addFailedLog("To verify the mini cart is closed or not",
					"the mini cart is closed", "Unable to close the  mini cart",
					Common.getscreenShotPathforReport("Failed to navigate account create page"));
		
			Assert.fail();

		}
	}
	public void ValidateMyAccountpage(String DataSet) {
		// TODO Auto-generated method stub
	
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
			report.addFailedLog("Validating Create New Customer Account page navigation ",
					"after clinking Create New Customer Account page it will navigate account creation page",
					"Successfully click singIn button ",
					Common.getscreenShotPathforReport("Failed to navigate account create page"));
			Assert.fail();
		}	
	}
	public void Signout(String DataSet) {
		// TODO Auto-generated method stub
		
		try {
			RegClickaccount();
			Sync.waitPageLoad();
			 String signout = data.get(DataSet).get("MYAccountlinks");
			Common.javascriptclickElement("xpath", "(//a[text()='" + signout + "'])[2]");
			//Thread.sleep(2000);
			//System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getText("xpath", "//h1[contains(text(),'You are signed out')]").equals("You are signed out"),
					"Validating My Account page navigation",
					"user sign in and navigate to my account page",
					"Successfully navigate to my account page", "Failed to navigate my account page ");
		
		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating My Subscriptions page navigation ",
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
					" it should clik the Header links and navigate to the My account menu", "Top level navigation unsuccessfull", "Failed to navigate through the header menu");
			Assert.fail();

		}
	}



	public void Toplevelnavigation(String DataSet) {
		// TODO Auto-generated method stub
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
			report.addFailedLog("Validating top level navigation ",
					"after clinking top level page it will navigate CLP page",
					"Successfully navigate to My Subscriptions page ",
					Common.getscreenShotPathforReport("Failed to navigate CLP page"));
			Assert.fail();
		}	
	}
	public void Toplevelnavigation2() {
		// TODO Auto-generated method stub
		try {
		
			
		//	Sync.waitElementClickable("xpath", "//span[text()=' Shop']");
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
			report.addFailedLog("Validating top level navigation ",
					"after clinking top level page it will navigate CLP page",
					"Successfully navigate to My Subscriptions page ",
					Common.getscreenShotPathforReport("Failed to navigate CLP page"));
			Assert.fail();
		}	
	}

	public void RegClickaccount() {
		// TODO Auto-generated method stub
		
		try{
			Sync.waitElementClickable("xpath", "//button[contains(@data-bind,'scope: ')]");
			Common.clickElement("xpath", "//button[contains(@data-bind,'scope: ')]");
			String account=Common.findElement("xpath","//div[contains(@class,'m-account-nav ')]").getAttribute("class");	
System.out.println(account);
			Common.assertionCheckwithReport(account.contains("--active"),
					"Validating  my account button",
					"after clicking My account should display contents",
					"Successfully displays content", "Failed todisplay content ");
		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating  my account button",
					"after clinking My my account should display contents",
					"Successfully displays content",
					Common.getscreenShotPathforReport("Failed to navigate CLP page"));
			Assert.fail();
		}
		}
	public void GuestClickaccount() {
		// TODO Auto-generated method stub
		
		try{
			Sync.waitElementClickable("xpath", "(//span[@class='icon-header__account a-icon-text-btn__icon'])[2]");
			Common.clickElement("xpath", "(//span[@class='icon-header__account a-icon-text-btn__icon'])[2]");
			String account=Common.findElement("xpath","//div[contains(@class,'m-account-nav ')]").getAttribute("class");	
System.out.println(account);
			Common.assertionCheckwithReport(account.contains("--active"),
					"Validating  my account button",
					"after clicking My account should display contents",
					"Successfully displays content", "Failed todisplay content ");
		} catch (Exception | Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating  my account button",
					"after clinking My my account should display contents",
					"Successfully displays content",
					Common.getscreenShotPathforReport("Failed to navigate CLP page"));
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
			report.addFailedLog("validate the ChatBot", "Open the ChatBot", "Unable click on the ChatBot",
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
					"Should dispaly the error message Please enter a valid email address (Ex johndoe@domain.com).", "Please enter a valid email address (Ex johndoe@domain.com).error message is displayed",
					"Failed to display error message");
			clickStoreLogo();
			Sync.waitPageLoad();
		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog(
					"To validate the error message when given invalid email ",
					"Should dispaly the error message Please enter a valid email address (Ex johndoe@domain.com).", "Error message dispaly Failed ",
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
	
	public void click_ChatBot(String dataSet) {

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
			
			String items[] = data.get(dataSet).get("OXOAnswers").split(",");
			
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

	

	public void clickcountryselector() {
		try {

			Common.scrollIntoView("xpath", "//button[@class='a-icon-text-btn c-footer__country-selector action']");

			// Thread.sleep(1500);
			Common.clickElement("xpath", "//button[@class='a-icon-text-btn c-footer__country-selector action']");
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

		String available = data.get(dataSet).get("Options");
		String[] Select = available.split(",");
		int i = 0;

		try {

			for (i = 1; i <= Select.length; i++) {
				System.out.println(Select[i - 1]);
				Common.scrollIntoView("xpath", "(//input[contains(@name,'countrySelector')])[" + i + "]");

				String Country = Common
						.findElement("xpath", "(//label[contains(@class,'country-item__country-label')])[" + i + "]")
						.getText();
			
				Sync.waitElementClickable("xpath",
						"(//label[contains(@class,'country-item__country-label')])[" + i + "]");
				Common.clickElement("xpath", "(//label[contains(@class,'country-item__country-label')])[" + i + "]");
				System.out.println(Country);
				Common.assertionCheckwithReport(Country.equals(Select[i - 1]), "verifying the Country" + Select[i - 1],
						"user Selects " + Select[i - 1] + "Country",
						"user successfully Selects the country " + Select[i - 1],
						"Failed to select the country " + Select[i - 1]);
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Country Selector" + Select[i-1],
					"user Selects the " + Select[i-1] + "Country", "User unabel to Select Country " + Select[i-1],
					Common.getscreenShotPathforReport("user failed to Select the Country"));
			System.out.println(Select[i-1] + " is Missing");
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
			
			report.addFailedLog("Validate the Popup of Free Ground Shipping", "User Opens the Free Ground Shipping Pop up",
					"Successfully displays Free Ground Shipping Pop up",
					Common.getscreenShotPathforReport("User unabel to display Free Ground Shipping Pop up"));
			
			Assert.fail();

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
	
}
