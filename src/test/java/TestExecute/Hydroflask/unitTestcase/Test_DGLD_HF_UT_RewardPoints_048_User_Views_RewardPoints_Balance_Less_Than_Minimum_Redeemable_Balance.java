package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;
public class Test_DGLD_HF_UT_RewardPoints_048_User_Views_RewardPoints_Balance_Less_Than_Minimum_Redeemable_Balance {
	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_RewardPoints() throws Exception {

		try {
			Hydro.click_singinButton();
			Hydro.login_Hydroflask("AccountDetails");
			Hydro.navigate_To_MyAccount();
			Hydro.verify_RewardPoints();
			
//			Hydro.verify_BalanceHistroy();
//			Hydro.verify_EmailNotification();
			
			Hydro.verify_LearnMore_CTA();
			
			

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
			System.setProperty("configFile", "Hydroflask\\config.properties");
			Login.signIn();
			 Hydro.ClosADD();
          
	      }


}

