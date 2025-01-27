package TestExecute.Osprey_US.SmokeTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_EMEA.OspreyRegressionEMEA;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OS_054_Validate_Footer_Links {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	OspreyRegressionEMEA Osprey_ReEu = new OspreyRegressionEMEA(datafile,"Footer Links");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_the_Footer_Links_Funtionality () throws Exception {

		try {
          Osprey_ReEu.verifingHomePage();
          Osprey_ReEu.Footer_Links_Company("Footer");
          Osprey_ReEu.Footer_Links_CustomerSupport("Footer");
          Osprey_ReEu.Footer_Links_Resources("Footer");
          Osprey_ReEu.Footer_Links_BrandTeam("Footer");
           
          
          //For Need repair and Replacement 
//          Osprey_ReEu.click_singinButton();
//          Osprey_ReEu.Login_Account("Account");
//          Osprey_ReEu.Footer_Links_Repari_And_Replacement("Footer");
          
        
        
        
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
