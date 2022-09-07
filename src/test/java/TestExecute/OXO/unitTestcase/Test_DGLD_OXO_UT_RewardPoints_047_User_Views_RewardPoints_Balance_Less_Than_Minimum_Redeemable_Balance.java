package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_UT_RewardPoints_047_User_Views_RewardPoints_Balance_Less_Than_Minimum_Redeemable_Balance {

	String datafile = "oxo//OXOTestData.xlsx";	
	OxoHelper Oxo = new OxoHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_RewardPoints() throws Exception {

		try {
			Oxo.click_singinButton();
			Oxo.user_SignIn("AccountDetails");
			Oxo.navigate_To_MyAccount();
			Oxo.verify_RewardPoints();
			Oxo.verify_LearnMore_CTA();
			
			
			
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
		 System.setProperty("configFile", "oxo\\config.properties");
		  Login.signIn();

	}

}
