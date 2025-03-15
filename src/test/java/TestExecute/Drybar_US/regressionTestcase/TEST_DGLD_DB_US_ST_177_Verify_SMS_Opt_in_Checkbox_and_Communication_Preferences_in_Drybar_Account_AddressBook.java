package TestExecute.Drybar_US.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Drybar_US.GoldDrybarusHelper2;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_DB_US_ST_177_Verify_SMS_Opt_in_Checkbox_and_Communication_Preferences_in_Drybar_Account_AddressBook {

	String datafile = "Drybar_US//GoldDrybarTestData.xlsx";
	GoldDrybarusHelper2 Drybar = new GoldDrybarusHelper2(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_Verify_SMS_Opt_in_Checkbox_and_Communication_Preferences_in_Drybar_Account_AddressBook  () throws Exception {

		try {
		
			Drybar.Verify_Homepage();
			Drybar.click_singinButton();
			Drybar.login_Drybar("MobileDetails");
			String Phonenumber=Drybar.Add_New_AddressBook("MobileDetails");
			Drybar.communication_Prefrence(Phonenumber);
			String Number=Drybar.edit_MobileNumber("MobileDetails");
			Drybar.communication_Prefrence(Number);
			Drybar.Delete_Address();
			

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
