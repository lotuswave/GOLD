package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_OSP_US_BCST_052_Change_Password_and_Email_from_AccountInformation_Page {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile,"Checkout payments");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_the_Change_Password_and_Email_from_AccountInformation_Page () throws Exception {

		try {
       
			 Osprey_ReEu.verifingHomePage();
		        Osprey_ReEu.click_Createaccount();
		        String NewDetail=Osprey_ReEu.create_account("Create Account");
		        Osprey_ReEu.edit_Account_info("NewDetails");
		        Osprey_ReEu.changed_password(NewDetail);
		        String newemail=Osprey_ReEu.change_email("NewDetails");
		        
      
		} catch (Exception e) {
			   
			Assert.fail(e.getMessage(), e);
		}
	}


	@AfterTest
	public void clearBrowser() {
		Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Osprey_US\\config.properties");
		Login.signIn();

	}

}
