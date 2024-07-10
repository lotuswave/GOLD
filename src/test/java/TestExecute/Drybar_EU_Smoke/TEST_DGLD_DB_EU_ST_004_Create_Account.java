package TestExecute.Drybar_EU_Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_EU_ST_004_Create_Account {

	String datafile = "Drybar_EU//GoldDrybarEUTestData.xlsx";
	GoldDrybarUSHelper Drybar = new GoldDrybarUSHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Create_Account  () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.click_Createaccount();
			Drybar.createaccount_verfication("Invalid details");
			Drybar.create_account("NewAccountDetails");

			

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
		System.setProperty("configFile", "Drybar_EU\\config.properties");
        Login.signIn();
        Drybar.close_add();
        

	}

}
