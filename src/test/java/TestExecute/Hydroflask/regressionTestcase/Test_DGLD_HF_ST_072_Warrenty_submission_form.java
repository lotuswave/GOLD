package TestExecute.Hydroflask.regressionTestcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.GoldHydroHyvaHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_DGLD_HF_ST_072_Warrenty_submission_form {
	String datafile = "Hydroflask//GoldHydroTestData.xlsx";
	GoldHydroHyvaHelper Hydro = new GoldHydroHyvaHelper(datafile,"Forms");

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validate_the_Warranty_form_page () throws Exception {

		try {
			Hydro.verifingHomePage();
			Hydro.clickWarranty();
			Hydro.WarrantySubmission("WarrantyDetails");
			
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
