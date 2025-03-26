package TestExecute.Hydroflask.Prod_Smoke_TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyva_PRODHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HYF_ST_094_Create_Account_and_My_Account_Page_Validation {

	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyva_PRODHelper Hydro = new GoldHydroHyva_PRODHelper(datafile,"DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validating_Create_Account() throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.click_Createaccount();
			Hydro.createaccount_verfication("Invalid details");
            Hydro.create_account("New Account Details");
            Hydro.Account_page_Validation("Account");
            Hydro.signout();
			
			
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
		System.setProperty("configFile", "Hydroflask\\prodconfig.properties");
         Login.signIn();
         Hydro.close_add();
         Hydro.acceptPrivacy();

	}

}
