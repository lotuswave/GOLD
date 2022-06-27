package TestComponent.Hydroflask;

import java.util.HashMap;
import java.util.Map;

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
}