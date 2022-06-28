package TestComponent.Hydroflask;

import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.util.SystemOutLogger;
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
		Common.assertionCheckwithReport(Common.getPageTitle().equals("Home Page"),"validating store logo", "System directs the user back to the Homepage", "Sucessfully user back to home page", "faield to get back to homepage");
		}
		catch(Exception |Error e) {
			report.addFailedLog("validating store logo", "System directs the user back to the Homepage", "Sucessfully user back to home page", Common.getscreenShotPathforReport("faield to get back to homepage"));
			Assert.fail();
		}
		
		
		
	}
	
	
	public void click_singinButton() {
		try {
		Sync.waitElementClickable("xpath", "//div[@class='m-account-nav__content']/button");
		Common.clickElement("xpath", "//div[@class='m-account-nav__content']/button");
		Sync.waitElementClickable("xpath", "//div[@class='m-account-nav__content']/button");
		Common.clickElement("xpath","//li[@class='m-account-nav__log-in']/a");
		Common.assertionCheckwithReport(Common.getText("xpath", "//h1[@class='page-title-wrapper']").equals("Customer Login"),"Validate the signIn-button","Successfully click singIn button", "User unabel to click singup button");
		
		}
		catch(Exception |Error e) {
			e.printStackTrace();
			report.addFailedLog("Validate the signIn-button ", "User navigating signin page", "Successfully click singIn button ",Common.getscreenShotPathforReport("User unabel to click singup button"));
			Assert.fail();
		}
	}
	
	
	public void login_Hydroflask() {
		
		
		try {
		    Sync.waitPageLoad();
			Common.textBoxInput("id", "email", "qatesting.lotuswave+1@gmail.com");
			Common.textBoxInput("id", "pass","Lotuswave@123");
			Common.clickElement("xpath", "//button[@id='send2']");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			}
		catch(Exception |Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating Create New Customer Account page navigation ", "after clinking Create New Customer Account page it will navigate account creation page", "Successfully click singIn button ",Common.getscreenShotPathforReport("Failed to navigate account create page"));
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
		}
		catch(Exception |Error e) {
			e.printStackTrace();
			report.addFailedLog("Validating Create New Customer Account page navigation ", "after clinking Create New Customer Account page it will navigate account creation page", "Successfully click singIn button ",Common.getscreenShotPathforReport("Failed to navigate account create page"));
			Assert.fail();
		}
		
	}
 
	 

	public void validate_accountoptions() {

		WebElement account = Common.findElement("xpath", "//div[@class='m-account-nav__content']/button");
		account.click();
		List<WebElement> accountoptions = Common.findElements("xpath", "//ul[@class='m-account-nav__links']/li/a");
		
		ArrayList<String> names=new ArrayList<String>();
		for(int i=0;i<accountoptions.size();i++) {
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



	public void Navigate_MYAccoun(String dataSet) {
		String pagetitle = data.get(dataSet).get("title");
		 String[] Pagetitle = pagetitle.split(",");
		 String pagename = data.get(dataSet).get("MYAccountlinks");
		 String[] Pagename = pagename.split(",");
		 
		 
		 System.out.println(pagetitle);
		 System.out.println(pagename);
		int i=1;
		try {
			//Common.clickElement("xpath", "//div[@class='m-account-nav__content']");
			int size = Common.findElements("xpath", "//ul[@class='m-account-nav__links']/li").size();
			 
		for(i=1;i<=Pagetitle.length;i++){
			
			Common.mouseOverClick("xpath", "//div[@class='m-account-nav__content']");
			Common.getscreenShotPathforReport("Clicked My Account icon in header");
			Sync.waitElementClickable(30, "xpath", "(//a[text()='"+Pagename[i-1]+"'])[2]");
	        Common.javascriptclickElement("xpath", "(//a[text()='"+Pagename[i-1]+"'])[2]");
	        Sync.waitPageLoad();
	        Sync.waitElementVisible(30, "xpath", "//h1[@class='page-title-wrapper']");
	        String PageTitle = Common.findElement("xpath", "//h1[@class='page-title-wrapper']").getText();
	       System.out.println(PageTitle);
	        Common.assertionCheckwithReport(Common.getPageTitle().equals(Pagetitle[i-1]), Pagetitle[i-1] +  "the page is dispalyed " +Pagetitle[i-1], "Should land on the "+Pagetitle[i-1] +"page" , "Failed to dispaly the page" + Pagetitle[i-1]);
		
		}	
			
			
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail();
			
		}
		
	}




	public void minicart() {
		try {
			Sync.waitElementClickable("xpath", "//span[@class='icon-header__cart--desktop a-icon-text-btn__icon c-mini-cart__icon']");
			Common.clickElement("xpath", "//span[@class='icon-header__cart--desktop a-icon-text-btn__icon c-mini-cart__icon']");
	        Common.isElementDisplayed("id", "mini-cart-panel");
			int size = Common.findElements("id", "mini-cart-panel").size();
			
			 Common.assertionCheckwithReport(size>0, "the mini cart is displayed", "Should dislay the mini cart" , "mini cart is not displayed");
				
			
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail();
			
		}
		}
	
	
	
	
	
	
	}
