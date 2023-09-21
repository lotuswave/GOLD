package TestExecute.Osprey_EMEA.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyRegressionEMEA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_COMMON_004_My_Account_Page_Validation {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	
	OspreyRegressionEMEA Osprey_ReEu = new OspreyRegressionEMEA(datafile,"My AccountPage");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_the_My_Account_Page_Validation () throws Exception {

		try {
        Osprey_ReEu.verifingHomePage();
        
       // Osprey_ReEu.click_UGC();
        Osprey_ReEu.click_singinButton();
        Osprey_ReEu.Login_Account("Account");
        Osprey_ReEu.Account_page_Validation("Account validation");
        Osprey_ReEu.signout();
        Osprey_ReEu.Loginpage_validation("Invalid Details");
        
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
		System.setProperty("configFile", "Osprey_EMEA\\config.properties");
        Login.signIn();
        

	}

}
