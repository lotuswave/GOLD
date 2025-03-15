package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_124_Newsletter_Subcription_and_Un_Subcription_from_The_My_Account_Page {

	
	public class TEST_DGLD_DB_US_ST_013_Communication_Preferences_Subscription {

		String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
		GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"My AccountPage");

		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		public void Validate_Newsletter_Subscription () throws Exception {

			try {
			
				Drybar.Verify_Homepage();
				Drybar.click_singinButton();
				Drybar.login_Drybar("AccountDetails");
				Drybar.Newsletter_subscription_MyAccount(); 
				
				

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
			System.setProperty("configFile", "Drybar_US\\config.properties");
	        Login.signIn();
	        Drybar.close_add();
	        

		}

	}
}
