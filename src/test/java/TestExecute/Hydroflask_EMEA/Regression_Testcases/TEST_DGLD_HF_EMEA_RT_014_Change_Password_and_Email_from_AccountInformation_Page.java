package TestExecute.Hydroflask_EMEA.Regression_Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask_EMEA.GoldHydro_EMEA_Helper;
import TestLib.Common;
import TestLib.Login;

public class TEST_DGLD_HF_EMEA_RT_014_Change_Password_and_Email_from_AccountInformation_Page {

	String datafile = "Hydroflask_EMEA//GoldHydroEMEA_TestData.xlsx";
	GoldHydro_EMEA_Helper Hydro = new GoldHydro_EMEA_Helper(datafile,"DataSet");
	

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Change_Password_and_Email_from_AccountInformation_Page () throws Exception {

		try {
			
			Hydro.verifingHomePage();
			Hydro.click_Createaccount();
            String NewDetail=Hydro.create_account("AccountDetails");
            Hydro.edit_Account_info("NewDetails");
            Hydro.changed_password(NewDetail);
            String newemail=Hydro.change_Email("NewDetails");
            Hydro.Change_to_Existingemail(newemail);
			
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
		System.setProperty("configFile", "Hydroflask_EMEA\\config.properties");
		Login.signIn();
		Hydro.close_add();
        Hydro.acceptPrivacy();
	}

}

