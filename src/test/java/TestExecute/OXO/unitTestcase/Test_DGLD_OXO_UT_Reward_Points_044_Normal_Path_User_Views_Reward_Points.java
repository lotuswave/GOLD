package TestExecute.OXO.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.OXO.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OXO_UT_Reward_Points_044_Normal_Path_User_Views_Reward_Points {
	String datafile = "oxo//OXOTestData.xlsx";	
	OxoHelper Oxo = new OxoHelper(datafile,"DataSet");
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Views_RewardPoints() throws Exception {

		try {
			Oxo.click_singinButton();
			Oxo.user_SignIn("AccountDetails");
			Oxo.navigate_To_MyAccount();
			Oxo.verify_RewardPoints();
			Oxo.verify_BalanceHistroy();
			Oxo.verify_EmailNotification();
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


