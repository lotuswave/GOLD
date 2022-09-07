package TestExecute.Hydroflask.unitTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_UT_AccountRegistration_043_ExceptionPaths_AccountregistrationPage_errormessagesvalidation {
	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile, "DataSet");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void AccountRegistrationPage_errormessagesvalidation() throws Exception {

		try {
			Hydro.validate_Homepage();
			Hydro.validateCreateAccountpageNavigation();
			Hydro.createNewAccount_Required_Fieldsvalidation();
			Hydro.validate_Createnewaccount_email("Invalid Email");
			Hydro.validate_Createnewaccount_invalidpasssword("invalidpassword");
			Hydro.validate_Createnewaccount_passswordFields("AccountDetails");
			Hydro.validate_Createnewaccount_existingemail("AccountDetails");
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
