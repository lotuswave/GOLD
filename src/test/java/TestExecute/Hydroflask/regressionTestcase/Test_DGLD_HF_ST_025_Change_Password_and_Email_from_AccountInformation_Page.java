package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHelper;
import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_025_Change_Password_and_Email_from_AccountInformation_Page {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Change_Password_from_AccountInformation_Page() throws Exception {

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
		System.setProperty("configFile", "Hydroflask\\config.properties");
         Login.signIn();
         Hydro.close_add();
         Hydro.acceptPrivacy();

	}

}
