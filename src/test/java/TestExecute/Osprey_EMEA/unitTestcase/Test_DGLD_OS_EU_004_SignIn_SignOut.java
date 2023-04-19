package TestExecute.Osprey_EMEA.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.GoldOspreyEMEAHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_EU_004_SignIn_SignOut {

	String datafile = "Osprey_EMEA//GoldOspreyemea.xlsx";
	GoldOspreyEMEAHelper Osprey_Eu = new GoldOspreyEMEAHelper(datafile,"CreateAccount");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_SignIn_SignOut_Functionality () throws Exception {

		try {
        Osprey_Eu.verifingHomePage();
        Osprey_Eu.click_SignIn();
//        Osprey_Eu.login_Missingfields_Errormsg();
//        Osprey_Eu.login_Invalid_Email_Errormsg("Invalid Email");
//        Osprey_Eu.login_Invalidcrdentials_Errormsg("Invalid Password");
//        Osprey_Eu.login_Invalidcrdentials_Errormsg("Unregistered Email");
        Osprey_Eu.login("Account");
        Osprey_Eu.signout();
        
        
			
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
