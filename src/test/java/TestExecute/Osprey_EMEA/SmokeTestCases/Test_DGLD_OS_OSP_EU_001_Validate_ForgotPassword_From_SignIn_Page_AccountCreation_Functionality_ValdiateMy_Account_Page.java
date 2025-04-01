package TestExecute.Osprey_EMEA.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyEMEA_PRODHYVA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_OSP_EU_001_Validate_ForgotPassword_From_SignIn_Page_AccountCreation_Functionality_ValdiateMy_Account_Page {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	OspreyEMEA_PRODHYVA Osprey_ReEu = new OspreyEMEA_PRODHYVA(datafile,"CreateAccount");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_ForgotPassword_From_SignIn_Page_AccountCreation_Functionality_ValdiateMy_Account_Page () throws Exception {

		try {
			Osprey_ReEu.verifingHomePage();
	        Osprey_ReEu.click_singinButton();
	        Osprey_ReEu.Forgot_password("Account");
	        Osprey_ReEu.click_Createaccount();
	        Osprey_ReEu.Create_Account("Create Account");
	        Osprey_ReEu.Account_page_Validation("Account validation");
	        Osprey_ReEu.signout();
        
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
