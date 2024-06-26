package TestExecute.Drybar_UK.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarUSHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_UK_ST_022_Change_Email_and_password_of_the_Registered_User {
	String datafile = "Drybar_UK//GoldDrybarUKTestData.xlsx";
	GoldDrybarUSHelper Drybar = new GoldDrybarUSHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Change_Email_and_password_of_the_Registered_User () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
		    Drybar.click_Createaccount();
		    String NewDetail=Drybar.create_account("NewAccountDetails");
		    Drybar.edit_Account_info("NewDetails");
            Drybar.changed_password(NewDetail);
           String newemail=Drybar.change_Email("NewDetails");
            Drybar.Change_to_Existingemail(newemail);

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
		System.setProperty("configFile", "Drybar_UK\\config.properties");
        Login.signIn();
        Drybar.close_add();
        

	}
}

