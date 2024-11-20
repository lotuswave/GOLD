package TestExecute.Osprey_US.RegressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Osprey_US.GoldOspreyUSHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_OSP_US_001_Create_Account_And_MyAccount_Funtionality {

	String datafile = "Osprey_US//GoldOspreyus.xlsx";
	GoldOspreyUSHyvaHelper Osprey_ReEu = new GoldOspreyUSHyvaHelper(datafile, "CreateAccount");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Verifying_the_Create_account_Funtionality() throws Exception {

		try {
			Osprey_ReEu.verifingHomePage();
			Osprey_ReEu.click_Createaccount();
			Osprey_ReEu.createaccount_verfication("Invalid Email");
			Osprey_ReEu.create_account("Create Account");
			Osprey_ReEu.Account_page_Validation("Account validation");
			Osprey_ReEu.signout();

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
		System.setProperty("configFile", "Osprey_US\\config.properties");
		Login.signIn();

	}

}
